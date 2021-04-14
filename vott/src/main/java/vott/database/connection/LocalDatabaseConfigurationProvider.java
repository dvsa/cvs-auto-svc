package vott.database.connection;

import java.nio.file.Paths;

public class LocalDatabaseConfigurationProvider implements DatabaseConfigurationProvider {

    @Override
    public DatabaseConfiguration provide() {
        DatabaseProperties properties = DatabaseProperties.load(Paths.get("src/main/resources/database.properties"));

        return DatabaseConfiguration.builder()
            .host(properties.getHost())
            .port(properties.getPort())
            .username(properties.getUsername())
            .password(properties.getPassword())
            .dbName(properties.getDbName())
            .engine(properties.getEngine())
            .build();
    }
}
