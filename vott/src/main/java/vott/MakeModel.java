package vott;

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
        make = rs.getString("make");
        model = rs.getString("model");
        chassisMake = rs.getString("chassisMake");
        chassisModel = rs.getString("chassisModel");
        bodyMake = rs.getString("bodyMake");
        bodyModel = rs.getString("bodyModel");
        modelLiteral = rs.getString("modelLiteral");
        bodyTypeCode = rs.getString("bodyTypeCode");
        bodyTypeDescription = rs.getString("bodyTypeDescription");
        fuelPropulsionSystem = rs.getString("fuelPropulsionSystem");
        dtpCode = rs.getString("dtpCode");
    }

}
