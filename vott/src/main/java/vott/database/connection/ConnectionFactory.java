package vott.database.connection;

import lombok.RequiredArgsConstructor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@RequiredArgsConstructor
public class ConnectionFactory {

    private final DatabaseConfiguration databaseConfiguration;

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
            databaseConfiguration.toJdbcUrl(),
            databaseConfiguration.getUsername(),
            databaseConfiguration.getPassword()
        );
    }
}
