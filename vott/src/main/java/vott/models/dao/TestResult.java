package vott.models.dao;

import lombok.Data;

@Data
public class TestResult {

    private String vehicleID;
    private String fuelEmissionID;
    private String testStationID;
    private String testerID;
    private String preparerID;
    private String vehicleClassID;
    private String testTypeID;
    private String testStatus;
    private String reasonForCancellation;
    private String numberOfSeats;
    private String odometerReading;
    private String odometerReadingUnits;
    private String countryOfRegistration;
    private String noOfAxles;
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
    private String numberOfSeatbeltsFitted;
    private String lastSeatbeltInstallationCheckDate;
    private String seatbeltInstallationCheckDate;
    private String testResult;
    private String reasonForAbandoning;
    private String additionalNotesRecorded;
    private String additionalCommentsForAbandon;
    private String particulateTrapFitted;
    private String particulateTrapSerialNumber;
    private String modificationTypeUsed;
    private String smokeTestKLimitApplied;
    private String createdByID;
    private String lastUpdatedByID;

}
