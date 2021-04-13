package vott.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import vott.models.adapter.ExplicitNullAdapterFactory;
import vott.models.adapter.LocalDateAdapter;
import vott.models.adapter.OffsetDateTimeAdapter;

import java.time.LocalDate;
import java.time.OffsetDateTime;

public final class GsonInstance {

    private static Gson INSTANCE;

    private GsonInstance() {

    }

    public static Gson get() {
        if (INSTANCE == null) {
            INSTANCE = new GsonBuilder()
                .registerTypeAdapter(OffsetDateTime.class, new OffsetDateTimeAdapter())
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .registerTypeAdapterFactory(new ExplicitNullAdapterFactory())
                .create();
        }

        return INSTANCE;
    }
}
