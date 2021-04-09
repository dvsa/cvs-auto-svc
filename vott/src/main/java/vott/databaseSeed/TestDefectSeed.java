package vott.databaseSeed;

import java.sql.*;

public class TestDefectSeed {

    public ResultSet readDataFromCSV(String filename) throws SQLException {
        String query = "";
        Connection conn = null;

        String jdbcUrl = "jdbc:relique:csv:/loader/databaseSeed/"+filename;
        conn = DriverManager.getConnection(jdbcUrl);

        Statement stmt = conn.createStatement();

        ResultSet rs = stmt.executeQuery(query);
        return rs;
    }
}
