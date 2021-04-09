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

    public static Integer getInteger(ResultSet rs, String columnName) throws SQLException {
        int columnValue = rs.getInt(columnName);
        if (rs.wasNull()) {
            return null;
        }
        return columnValue;
    }

    public static String formatDate(String date) {
        if(date != null){
            return "'"+date+"'";
        }
        return null;
    }

    public static String trimYear(String date){
        if(date.length() > 4 ){
            return date.substring(0, 4);
        }
        return date;
    }
}
