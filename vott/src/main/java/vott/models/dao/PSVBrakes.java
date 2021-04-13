package vott.models.dao;

import lombok.Data;

@Data
public class PSVBrakes {

    private String technicalRecordID;
    private String BrakeCodeOriginal;
    private String brakeCode;
    private String dataTrBrakeOne;
    private String dataTrBrakeTwo;
    private String dataTrBrakeThree;
    private String retarderBrakeOne;
    private String retarderBrakeTwo;
    private String serviceBrakeForceA;
    private String secondaryBrakeForceA;
    private String parkingBrakeForceA;
    private String serviceBrakeForceB;
    private String secondaryBrakeForceB;
    private String parkingBrakeForceB;

}