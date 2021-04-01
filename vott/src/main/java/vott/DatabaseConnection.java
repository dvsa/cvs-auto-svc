package vott;

import java.sql.*;

public class DatabaseConnection {

    private String hostname = "cvs-data-migration-staging-db-cluster-nonprod.cibmebkudson.eu-west-1.rds.amazonaws.com";
    private String port = "3306" ;
    private String dbName = "CVSNOPCVSB19155";
    private String userName = "whitehouser";
    private String password = "password123";

    public Statement stmt = null;
    public Connection conn = null;

    public void AWSConnection() throws SQLException {

        // Database URL
        String jdbcUrl = "jdbc:mysql://" + hostname + ":" + port + "/" + dbName + "?user=" + userName + "&password=" + password;

        // Create connection to RDS DB instance
        conn = DriverManager.getConnection(jdbcUrl);
        // Create a table and write two rows
        stmt = conn.createStatement();
    }

    public ResultSet dbQuery(String query) throws SQLException {
        return stmt.executeQuery(query);
    }


}
