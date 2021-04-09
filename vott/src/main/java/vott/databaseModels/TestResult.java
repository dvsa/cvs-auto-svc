package vott.databaseModels;

import vott.DataMethods;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class TestResult {

    private Integer vehicleID;
    private Integer fuelEmissionID;
    private Integer testStationID;
    private Integer testerID;
    private Integer preparerID;
    private Integer vehicleClassID;
    private Integer testTypeID;
    private String testStatus;
    private String reasonForCancellation;
    private Integer numberOfSeats;
    private Integer odometerReading;
    private String odometerReadingUnits;
    private String countryOfRegistration;
    private Integer noOfAxles;
    private String regnDate;
    private String firstUseDate;
    private String createdAt;
    private String lastUpdatedAt;
    private String testCode;
    private String testNumber;
    private String certificateNumber;
    private String secondaryCertificateNumber;
    private String testExpiryDate;
    private String testAnniversaryDate;
    private String testTypeStartTimestamp;
    private String testTypeEndTimestamp;
    private Integer numberOfSeatbeltsFitted;
    private String lastSeatbeltInstallationCheckDate;
    private Integer seatbeltInstallationCheckDate;
    private String testResult;
    private String reasonForAbandoning;
    private String additionalNotesRecorded;
    private String additionalCommentsForAbandon;
    private String particulateTrapFitted;
    private String particulateTrapSerialNumber;
    private String modificationTypeUsed;
    private String smokeTestKLimitApplied;
    private Integer createdByID;
    private Integer lastUpdatedByID;

    public void setTestResult(ResultSet rs) throws SQLException {
        this.vehicleID = DataMethods.getInteger(rs, "vehicle_id");
        this.fuelEmissionID = DataMethods.getInteger(rs, "fuel_emission_id");
        this.testStationID = DataMethods.getInteger(rs, "test_station_id");
        this.testerID = DataMethods.getInteger(rs, "tester_id");
        this.preparerID = DataMethods.getInteger(rs, "preparer_id");
        this.vehicleClassID = DataMethods.getInteger(rs, "vehicle_class_id");
        this.testTypeID = DataMethods.getInteger(rs, "test_type_id");
        this.testStatus = Objects.toString(rs.getString("testStatus"), "");
        this.reasonForCancellation = Objects.toString(rs.getString("reasonForCancellation"), "");
        this.numberOfSeats = DataMethods.getInteger(rs, "numberOfSeats");
        this.odometerReading = DataMethods.getInteger(rs, "odometerReading");
        this.odometerReadingUnits = Objects.toString(rs.getString("odometerReadingUnits"), "");
        this.countryOfRegistration = Objects.toString(rs.getString("countryOfRegistration"), "");
        this.noOfAxles = DataMethods.getInteger(rs, "noOfAxles");
        this.regnDate = DataMethods.formatDate(rs.getString("regnDate"));
        this.firstUseDate = DataMethods.formatDate(rs.getString("firstUseDate"));
        this.createdAt = DataMethods.formatDate(rs.getString("createdAt"));
        this.lastUpdatedAt = DataMethods.formatDate(rs.getString("lastUpdatedAt"));
        this.testCode = Objects.toString(rs.getString("testCode"), "");
        this.testNumber = Objects.toString(rs.getString("testNumber"), "");
        this.certificateNumber = Objects.toString(rs.getString("certificateNumber"), "");
        this.secondaryCertificateNumber = Objects.toString(rs.getString("secondaryCertificateNumber"), "");
        this.testExpiryDate = DataMethods.formatDate(rs.getString("testExpiryDate"));
        this.testAnniversaryDate = DataMethods.formatDate(rs.getString("testAnniversaryDate"));
        this.testTypeStartTimestamp = DataMethods.formatDate(rs.getString("testTypeStartTimestamp"));
        this.testTypeEndTimestamp = DataMethods.formatDate(rs.getString("testTypeEndTimestamp"));
        this.numberOfSeatbeltsFitted = DataMethods.getInteger(rs, "numberOfSeatbeltsFitted");
        this.lastSeatbeltInstallationCheckDate = DataMethods.formatDate(rs.getString("lastSeatbeltInstallationCheckDate"));
        this.seatbeltInstallationCheckDate = DataMethods.getInteger(rs, "seatbeltInstallationCheckDate");
        this.testResult = Objects.toString(rs.getString("testResult"), "");
        this.reasonForAbandoning = Objects.toString(rs.getString("reasonForAbandoning"), "");
        this.additionalNotesRecorded = Objects.toString(rs.getString("additionalNotesRecorded"), "");
        this.additionalCommentsForAbandon = Objects.toString(rs.getString("additionalCommentsForAbandon"), "");
        this.particulateTrapFitted = Objects.toString(rs.getString("particulateTrapFitted"), "");
        this.particulateTrapSerialNumber = Objects.toString(rs.getString("particulateTrapSerialNumber"), "");
        this.modificationTypeUsed = Objects.toString(rs.getString("modificationTypeUsed"), "");
        this.smokeTestKLimitApplied = Objects.toString(rs.getString("smokeTestKLimitApplied"), "");
        this.createdByID = DataMethods.getInteger(rs, "createdBy_Id");
        this.lastUpdatedByID = DataMethods.getInteger(rs, "lastUpdatedBy_Id");
    }

    public String createInsertQuery(){
        return "INSERT INTO .`test_result` (`vehicle_id`,`fuel_emission_id`,`test_station_id`,`tester_id`,`preparer_id`,`vehicle_class_id`,`test_type_id`,`testStatus`,`reasonForCancellation`,`numberOfSeats`,`odometerReading`,`odometerReadingUnits`,`countryOfRegistration`,`noOfAxles`,`regnDate`,`firstUseDate`,`createdAt`,`lastUpdatedAt`,`testCode`,`testNumber`,`certificateNumber`,`secondaryCertificateNumber`,`testExpiryDate`,`testAnniversaryDate`,`testTypeStartTimestamp`,`testTypeEndTimestamp`,`numberOfSeatbeltsFitted`,`lastSeatbeltInstallationCheckDate`,`seatbeltInstallationCheckDate`,`testResult`,`reasonForAbandoning`,`additionalNotesRecorded`,`additionalCommentsForAbandon`,`particulateTrapFitted`,`particulateTrapSerialNumber`,`modificationTypeUsed`,`smokeTestKLimitApplied`,`createdBy_Id`,`lastUpdatedBy_Id`) " +
                "VALUES ("+vehicleID+","+fuelEmissionID+","+testStationID+","+testerID+","+preparerID+","+vehicleClassID+","+testTypeID+",'"+testStatus+"','"+reasonForCancellation+"',"+numberOfSeats+","+odometerReading+",'"+odometerReadingUnits+"','"+countryOfRegistration+"',"+noOfAxles+","+regnDate+","+firstUseDate+","+createdAt+","+lastUpdatedAt+",'"+testCode+"','"+testNumber+"','"+certificateNumber+"','"+secondaryCertificateNumber+"',"+testExpiryDate+","+testAnniversaryDate+","+testTypeStartTimestamp+","+testTypeEndTimestamp+","+numberOfSeatbeltsFitted+","+lastSeatbeltInstallationCheckDate+","+seatbeltInstallationCheckDate+",'"+testResult+"','"+reasonForAbandoning+"','"+additionalNotesRecorded+"','"+additionalCommentsForAbandon+"','"+particulateTrapFitted+"','"+particulateTrapSerialNumber+"','"+modificationTypeUsed+"','"+smokeTestKLimitApplied+"',"+createdByID+","+lastUpdatedByID+") " +
        "ON DUPLICATE KEY UPDATE id=LAST_INSERT_ID(id)";
    }
}
