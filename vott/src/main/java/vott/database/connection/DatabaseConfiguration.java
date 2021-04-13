package vott.database.connection;

import com.google.gson.Gson;
import lombok.Builder;
import lombok.Data;
import vott.aws.AWSSecrets;
import vott.aws.Secret;
import vott.json.GsonInstance;

import java.util.Map;

@Data
@Builder
public class DatabaseConfiguration {

    private String host;
    private int port;
    private String engine;

    private String username;
    private String password;

    private String dbName;

    public static DatabaseConfiguration connectionBuilder() {

        Map<String, String> env = System.getenv();

        AWSSecrets secrets = new AWSSecrets();
        String dbConfig = secrets.getSecret(env);

        Gson gson = GsonInstance.get();
        Secret authSecret = gson.fromJson(dbConfig, Secret.class);

        return DatabaseConfiguration.builder()
                .host(authSecret.getHost())
                .port(authSecret.getPort())
                .username(authSecret.getUsername())
                .password(authSecret.getPassword())
                .dbName(env.get("SCHEMA_NAME"))
                .engine(authSecret.getEngine())
                .build();
    }

    public String toJdbcUrl() {
        return "jdbc:"+engine+"://" + host + ":" + port + "/" + dbName;
    }
}
