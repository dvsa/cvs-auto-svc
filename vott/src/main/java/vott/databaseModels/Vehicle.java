package vott.databaseModels;

import lombok.Data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

@Data
public class Vehicle {

    private String systemNumber;
    private String vin;
    private String vrm_trm;
    private String trailerID;

    public void setVehicle(ResultSet rs) throws SQLException {
        this.systemNumber = Objects.toString(rs.getString("system_number"), "");
        this.vin = Objects.toString(rs.getString("vin"), "");
        this.vrm_trm = Objects.toString(rs.getString("vrm_trm"), "");
        this.trailerID = Objects.toString(rs.getString("trailer_id"), "");

    }

    public String createInsertQuery(){
        return "INSERT INTO vehicle( system_number, vin, vrm_trm, trailer_id ) " +
                "VALUES ('"+systemNumber+"', '"+vin+"', '"+vrm_trm+"', '"+trailerID+"') " +
                "ON DUPLICATE KEY UPDATE id=LAST_INSERT_ID(id)";
    }
}
