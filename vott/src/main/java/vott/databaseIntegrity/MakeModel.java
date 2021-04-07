package vott.databaseIntegrity;

import java.sql.ResultSet;
import java.sql.SQLException;

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

    public String getMake(){ return make;}
    public String getModel(){ return model;}
    public String getChassisMake(){ return chassisMake;}
    public String getChassisModel(){ return chassisModel;}
    public String getBodyMake(){ return bodyMake;}
    public String getBodyModel(){ return bodyModel;}
    public String getModelLiteral(){ return modelLiteral;}
    public String getBodyTypeCode(){ return bodyTypeCode;}
    public String getBodyTypeDescription(){ return bodyTypeDescription;}
    public String getFuelPropulsionSystem(){ return fuelPropulsionSystem;}
    public String getDtpCode(){ return dtpCode;}

    public void setMakeModel(ResultSet rs) throws SQLException {
        this.make = rs.getString("make");
        this.model = rs.getString("model");
        this.chassisMake = rs.getString("chassisMake");
        this.chassisModel = rs.getString("chassisModel");
        this.bodyMake = rs.getString("bodyMake");
        this.bodyModel = rs.getString("bodyModel");
        this.modelLiteral = rs.getString("modelLiteral");
        this.bodyTypeCode = rs.getString("bodyTypeCode");
        this.bodyTypeDescription = rs.getString("bodyTypeDescription");
        this.fuelPropulsionSystem = rs.getString("fuelPropulsionSystem");
        this.dtpCode = rs.getString("dtpCode");
    }

    public String createInsertQuery(){
        return "INSERT INTO make_model( make, model, chassisMake, chassisModel, bodyMake, bodyModel, modelLiteral, bodyTypeCode, bodyTypeDescription, fuelPropulsionSystem, dtpCode ) " +
                "VALUES ('"+make+"', '"+model+"', '"+chassisMake+"', '"+chassisModel+"', '"+bodyMake+"', '"+bodyModel+"', '"+modelLiteral+"', '"+bodyTypeCode+"', '"+bodyTypeDescription+"', '"+fuelPropulsionSystem+"', '"+dtpCode+"') " +
                "ON DUPLICATE KEY UPDATE id=LAST_INSERT_ID(id)";
    }

}
