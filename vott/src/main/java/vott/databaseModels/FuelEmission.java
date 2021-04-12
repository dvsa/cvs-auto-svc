package vott.databaseModels;

import lombok.Data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

@Data
public class FuelEmission {

    private String modTypeCode;
    private String description;
    private String emissionStandard;
    private String fuelType;

    public void setFuelEmissions(ResultSet rs) throws SQLException {
        this.modTypeCode = Objects.toString(rs.getString("modTypeCode"), "");
        this.description = Objects.toString(rs.getString("description"), "");
        this.emissionStandard = Objects.toString(rs.getString("emissionStandard"), "");
        this.fuelType = Objects.toString(rs.getString("fuelType"), "");
    }

    public String createInsertQuery(){
        return "INSERT INTO fuel_emission( modTypeCode, description, emissionStandard, fuelType ) " +
                "VALUES ('"+modTypeCode+"', '"+description+"', '"+emissionStandard+"', '"+fuelType+"') " +
                "ON DUPLICATE KEY UPDATE id=LAST_INSERT_ID(id)";
    }
}
