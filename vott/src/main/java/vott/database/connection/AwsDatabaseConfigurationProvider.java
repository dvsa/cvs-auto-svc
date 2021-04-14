package vott.database.connection;

import lombok.RequiredArgsConstructor;
import vott.aws.SecretsManagerClient;
import vott.json.GsonInstance;

@RequiredArgsConstructor
public class AwsDatabaseConfigurationProvider implements DatabaseConfigurationProvider {

    private final SecretsManagerClient secretsManagerClient;

    @Override
    public DatabaseConfiguration provide() {
        String dbConfig = secretsManagerClient.getSecret(System.getenv("SECRET"));

        DatabaseProperties properties = GsonInstance.get().fromJson(dbConfig, DatabaseProperties.class);

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
