package vott.databaseModels;

import lombok.Data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

@Data
public class MakeModel {

    private String make;
    private String model;
    private String chassisMake;
    private String chassisModel;
    private String bodyMake;
    private String bodyModel;
    private String modelLiteral;
    private String bodyTypeCode;
    private String bodyTypeDescription;
    private String fuelPropulsionSystem;
    private String dtpCode;

    public void setMakeModel(ResultSet rs) throws SQLException {
        this.make = Objects.toString(rs.getString("make"), "");
        this.model = Objects.toString(rs.getString("model"), "");
        this.chassisMake = Objects.toString(rs.getString("chassisMake"), "");
        this.chassisModel = Objects.toString(rs.getString("chassisModel"), "");
        this.bodyMake = Objects.toString(rs.getString("bodyMake"), "");
        this.bodyModel = Objects.toString(rs.getString("bodyModel"), "");
        this.modelLiteral = Objects.toString(rs.getString("modelLiteral"), "");
        this.bodyTypeCode = Objects.toString(rs.getString("bodyTypeCode"), "");
        this.bodyTypeDescription = Objects.toString(rs.getString("bodyTypeDescription"), "");
        this.fuelPropulsionSystem = Objects.toString(rs.getString("fuelPropulsionSystem"), "");
        this.dtpCode = Objects.toString(rs.getString("dtpCode"), "");
    }

    public String createInsertQuery(){
        return "INSERT INTO make_model( make, model, chassisMake, chassisModel, bodyMake, bodyModel, modelLiteral, bodyTypeCode, bodyTypeDescription, fuelPropulsionSystem, dtpCode ) " +
                "VALUES ('"+make+"', '"+model+"', '"+chassisMake+"', '"+chassisModel+"', '"+bodyMake+"', '"+bodyModel+"', '"+modelLiteral+"', '"+bodyTypeCode+"', '"+bodyTypeDescription+"', '"+fuelPropulsionSystem+"', '"+dtpCode+"') " +
                "ON DUPLICATE KEY UPDATE id=LAST_INSERT_ID(id)";
    }

}
