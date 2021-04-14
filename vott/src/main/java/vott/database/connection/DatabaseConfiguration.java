package vott.database.connection;

import lombok.Builder;
import lombok.Data;
import vott.aws.SecretsManagerClient;

@Data
@Builder
public class DatabaseConfiguration {

    private String engine;

    private String host;
    private int port;

    private String username;
    private String password;

    private String dbName;

    public static DatabaseConfiguration provide() {
        DatabaseConfigurationProvider provider;
        if (System.getenv("ENVIRONMENT").equals("local")){
            provider = new LocalDatabaseConfigurationProvider();
        } else {
            provider = new AwsDatabaseConfigurationProvider(new SecretsManagerClient());
        }

        return provider.provide();
    }

    public String toJdbcUrl() {
        return "jdbc:" + engine + "://" + host + ":" + port + "/" + dbName;
    }
}
