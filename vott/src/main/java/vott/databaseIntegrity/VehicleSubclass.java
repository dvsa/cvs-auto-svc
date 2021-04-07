package vott.databaseIntegrity;

import java.sql.ResultSet;
import java.sql.SQLException;

public class VehicleSubclass {

    private String make;

    public String getMake(){ return make;}

    public void setAxleSpacing(ResultSet rs) throws SQLException {
        make = rs.getString("make");

    }
}
