package vott;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DataMethods {

    public static int getResultSetLength(ResultSet rs) throws SQLException {
        int rowcount = 0;
        if (rs.last()) {
            rowcount = rs.getRow();
            rs.beforeFirst();
        }
        return rowcount;
    }

}
