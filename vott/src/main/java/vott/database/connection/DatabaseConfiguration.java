package vott.database.connection;

import lombok.Builder;
import lombok.Data;
import vott.aws.AWSSecrets;

@Data
@Builder
public class DatabaseConfiguration {

    private String host;
    private int port;

    private String username;
    private String password;

    private String dbName;

    private AWSSecrets secrets;

    public static DatabaseConfiguration connectionBuilder() {
        return DatabaseConfiguration.builder()
            .host("cvs-data-migration-staging-db-cluster-nonprod.cibmebkudson.eu-west-1.rds.amazonaws.com")
            .port(3306)
            .username("whitehouser")
            .password("password123")
            .dbName("CVSNOPCVSB19155")
            .build();
    }

    public String toJdbcUrl() {
        return "jdbc:mysql://" + host + ":" + port + "/" + dbName;
    }
}
