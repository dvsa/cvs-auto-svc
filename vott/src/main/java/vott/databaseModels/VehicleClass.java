package vott.databaseModels;

import lombok.Data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

@Data
public class VehicleClass {
    private String code;
    private String description;
    private String vehicleType;
    private String vehicleSize;
    private String vehicleConfiguration;
    private String euVehicleCategory;

    public void setVehicleClass(ResultSet rs) throws SQLException {
        this.code = Objects.toString(rs.getString("code"), "");
        this.description = Objects.toString(rs.getString("description"), "");
        this.vehicleType = Objects.toString(rs.getString("vehicleType"), "");
        this.vehicleSize = Objects.toString(rs.getString("vehicleSize"), "");
        this.vehicleConfiguration = Objects.toString(rs.getString("vehicleConfiguration"), "");
        this.euVehicleCategory = Objects.toString(rs.getString("euVehicleCategory"), "");
    }

    public String createInsertQuery(){
        return "INSERT INTO vehicle_class( code, description, vehicleType, vehicleSize, vehicleConfiguration, euVehicleCategory ) " +
                "VALUES ('"+code+"', '"+description+"', '"+vehicleType+"', '"+vehicleSize+"', '"+vehicleConfiguration+"', '"+euVehicleCategory+"') " +
                "ON DUPLICATE KEY UPDATE id=LAST_INSERT_ID(id)";
    }
}
