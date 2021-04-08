package vott.databaseIntegrity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class FuelEmissions {

    private String modTypeCode;
    private String description;
    private String emissionStandard;
    private String fuelType;

    public String getModTypeCode(){ return modTypeCode;}
    public String getDescription(){ return description;}
    public String getEmissionStandard(){ return emissionStandard;}
    public String getFuelType(){ return fuelType;}

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
