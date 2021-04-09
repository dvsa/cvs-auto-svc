package vott.databaseModels;

import lombok.Data;
import vott.DataMethods;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

@Data
public class Axle {

    private Integer technicalRecordID;
    private Integer tyreID;
    private Integer axleNumber;
    private Integer parkingBrakeMrk;
    private Integer kerbWeight;
    private Integer ladenWeight;
    private Integer gbWeight;
    private Integer eecWeight;
    private Integer designWeight;
    private Integer brakeActuator;
    private Integer leverLength;
    private Integer springBrakeParking;

    public void setAxle(ResultSet rs) throws SQLException {
        this.technicalRecordID = DataMethods.getInteger(rs, "technical_record_id");
        this.tyreID = DataMethods.getInteger(rs, "tyre_id");
        this.axleNumber = DataMethods.getInteger(rs, "axleNumber");
        this.parkingBrakeMrk = DataMethods.getInteger(rs, "parkingBrakeMrk");
        this.kerbWeight = DataMethods.getInteger(rs, "kerbWeight");
        this.ladenWeight = DataMethods.getInteger(rs, "ladenWeight");
        this.gbWeight = DataMethods.getInteger(rs, "gbWeight");
        this.eecWeight = DataMethods.getInteger(rs, "eecWeight");
        this.designWeight = DataMethods.getInteger(rs, "designWeight");
        this.brakeActuator = DataMethods.getInteger(rs, "brakeActuator");
        this.leverLength = DataMethods.getInteger(rs, "leverLength");
        this.springBrakeParking = DataMethods.getInteger(rs, "springBrakeParking");
    }

    public String createInsertQuery(){
        return "INSERT INTO axles( technical_record_id, tyre_id, axleNumber, parkingBrakeMrk, kerbWeight, ladenWeight, gbWeight, eecWeight, designWeight, brakeActuator, leverLength, springBrakeParking ) " +
                "VALUES ("+technicalRecordID+", "+tyreID+", "+axleNumber+", "+parkingBrakeMrk+", "+kerbWeight+", "+ladenWeight+", "+gbWeight+", "+eecWeight+", "+designWeight+", "+brakeActuator+", "+leverLength+", "+springBrakeParking+") " +
                "ON DUPLICATE KEY UPDATE id=LAST_INSERT_ID(id)";
    }
}
