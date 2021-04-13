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

public class ExplicitNullAdapterFactory implements TypeAdapterFactory {

    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
        Field[] fields = typeToken.getRawType().getDeclaredFields();

        List<String> serializeEvenIfNull = Arrays.stream(fields)
            .filter(f -> f.getDeclaredAnnotation(ExplicitNull.class) != null)
            .map(this::getSerializedName)
            .collect(Collectors.toList());

        if (serializeEvenIfNull.isEmpty()) {
            return null;
        }

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
                JsonObject jsonObject = delegateAdapter.toJsonTree(value).getAsJsonObject();

                jsonObject.entrySet().removeIf(entry -> doNotSerializeIfNull.contains(entry.getKey()) && entry.getValue().isJsonNull());

                boolean originalSerializeNulls = out.getSerializeNulls();
                out.setSerializeNulls(true);
                elementAdapter.write(out, jsonObject);
                out.setSerializeNulls(originalSerializeNulls);
            }

            @Override
            public T read(JsonReader in) throws IOException {
                return delegateAdapter.read(in);
            }
        };
    }

    private String getSerializedName(Field field) {
        SerializedName serializedName = field.getDeclaredAnnotation(SerializedName.class);
        return serializedName == null ? field.getName() : serializedName.value();
    }
}
