package vott.e2e;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

public class E2eProperties {
    @Getter
    @Setter(AccessLevel.PRIVATE)
    private EnquiryServiceProperties enquiryServiceProperties;

    private E2eProperties() {

    }

    public static E2eProperties load(Path path) {
        Properties properties = new Properties();
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            properties.load(reader);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }

        E2eProperties e2eProperties = new E2eProperties();

        EnquiryServiceProperties enquiryServiceProperties = new EnquiryServiceProperties();
        enquiryServiceProperties.setApiKey(properties.getProperty("enquiry-service.api-key"));

        e2eProperties.setEnquiryServiceProperties(enquiryServiceProperties);

        return e2eProperties;
    }

    public static class EnquiryServiceProperties {
        @Getter
        @Setter(AccessLevel.PRIVATE)
        private String apiKey;
    }
}
