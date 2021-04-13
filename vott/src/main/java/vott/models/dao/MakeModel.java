package vott.models.dao;

import lombok.Data;

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

}
