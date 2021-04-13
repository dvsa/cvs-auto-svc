package vott.models.adapter;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Serializes null fields annotated with {@link ExplicitNull} as the JSON primitive {@code null}, and removes all other
 * null fields entirely.
 *
 * This is, unfortunately, a by-product of certain APIs requiring nullable fields to be present even if null.
 */
public class ExplicitNullAdapterFactory implements TypeAdapterFactory {

    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
        Field[] fields = typeToken.getRawType().getDeclaredFields();

        // list of names for fields to write as explicit nulls
        List<String> serializeEvenIfNull = Arrays.stream(fields)
            .filter(f -> f.getDeclaredAnnotation(ExplicitNull.class) != null)
            .map(this::getSerializedName)
            .collect(Collectors.toList());

        if (serializeEvenIfNull.isEmpty()) {
            // if there's no explicit nulls, no custom logic is needed
            return null;
        }

        // list of names for fields to remove from the JSON tree
        List<String> doNotSerializeIfNull = Arrays.stream(fields)
            .map(this::getSerializedName)
            .filter(name -> !serializeEvenIfNull.contains(name))
            .collect(Collectors.toList());

        return new TypeAdapter<T>() {

            private final TypeAdapter<T> delegateAdapter = gson.getDelegateAdapter(
                ExplicitNullAdapterFactory.this,
                typeToken
            );

            private final TypeAdapter<JsonElement> elementAdapter = gson.getAdapter(JsonElement.class);

            @Override
            public void write(JsonWriter out, T value) throws IOException {
                // serialize as normal
                JsonObject jsonObject = delegateAdapter.toJsonTree(value).getAsJsonObject();

                // remove all nullable entries, NOT annotated with @ExplicitNull, which are actually null
                jsonObject.entrySet().removeIf(
                    entry -> doNotSerializeIfNull.contains(entry.getKey()) && entry.getValue().isJsonNull()
                );

                // save original null serialization setting
                boolean originalSerializeNulls = out.getSerializeNulls();

                // force all remaining null fields (those with @ExplicitNull) to produce JSON primitive null
                out.setSerializeNulls(true);

                // write
                elementAdapter.write(out, jsonObject);

                // restore copy of original null serialization setting
                out.setSerializeNulls(originalSerializeNulls);
            }

            @Override
            public T read(JsonReader in) throws IOException {
                // deserialize as normal
                return delegateAdapter.read(in);
            }
        };
    }

    private String getSerializedName(Field field) {
        SerializedName serializedName = field.getDeclaredAnnotation(SerializedName.class);
        return serializedName == null ? field.getName() : serializedName.value();
    }
}
