package vott.databaseModels;

import lombok.Data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

@Data
public class VehicleSubclass {

    private String vehicleClassID;
    private String subclass;

    public void setVehicleSubclass(ResultSet rs) throws SQLException {
        this.vehicleClassID = Objects.toString(rs.getString("vehicle_class_id"), "");
        this.subclass = Objects.toString(rs.getString("subclass"), "");

    }

    public String createInsertQuery(){
        return "INSERT INTO vehicle_subclass( vehicle_class_id, subclass ) " +
                "VALUES ('"+vehicleClassID+"', '"+subclass+"') " +
                "ON DUPLICATE KEY UPDATE id=LAST_INSERT_ID(id)";
    }
}
