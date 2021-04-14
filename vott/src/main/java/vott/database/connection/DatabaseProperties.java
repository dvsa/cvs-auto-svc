package vott.database.connection;

import lombok.Getter;
import lombok.Setter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

public class DatabaseProperties {
    @Getter
    @Setter
    private String engine;

    @Getter
    @Setter
    private String host;

    @Getter
    @Setter
    private int port;

    @Getter
    @Setter
    private String username;

    @Getter
    @Setter
    private String password;

    @Getter
    @Setter
    private String dbName;

    @Getter
    @Setter
    private String dbClusterIdentifier;

    private DatabaseProperties() {

    }

    public static DatabaseProperties load(Path path) {
        Properties properties = new Properties();
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            properties.load(reader);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }

        DatabaseProperties databaseProperties = new DatabaseProperties();

        databaseProperties.setEngine(properties.getProperty("database.engine"));
        databaseProperties.setHost(properties.getProperty("database.host"));

        String port = properties.getProperty("database.port");
        databaseProperties.setPort(port == null ? 0 : Integer.parseInt(port));

        databaseProperties.setUsername(properties.getProperty("database.username"));
        databaseProperties.setPassword(properties.getProperty("database.password"));
        databaseProperties.setDbName(properties.getProperty("database.db-name"));
        databaseProperties.setDbClusterIdentifier(properties.getProperty("database.db-cluster-identifier"));

        return databaseProperties;
    }
}
