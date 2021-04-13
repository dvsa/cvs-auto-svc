package vott.aws;

import lombok.Data;

@Data
public class Secret {

    private String username;
    private String password;
    private String engine;
    private String host;
    private int port;
    private String dbName;
    private String dbClusterIdentifer;
}
