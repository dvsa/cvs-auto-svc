package vott.database;


import vott.database.connection.ConnectionFactory;
import vott.models.dao.TestResult;
import vott.sqlgeneration.TableDetails;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestResultRepository extends AbstractRepository<TestResult>{
    public TestResultRepository(ConnectionFactory connectionFactory) { super(connectionFactory); }

    @Override
    protected TableDetails getTableDetails() {

        TableDetails tableDetails = new TableDetails();

        tableDetails.setTableName("test_result");
        tableDetails.setColumnNames(new String[] {
                "vehicle_id",
                "fuel_emission_id",
                "test_station_id",
                "tester_id",
                "preparer_id",
                "vehicle_class_id",
                "test_type_id",
                "testStatus",
                "reasonForCancellation",
                "numberOfSeats",
                "odometerReading",
                "odometerReadingUnits",
                "countryOfRegistration",
                "noOfAxles",
                "regnDate",
                "firstUseDate",
                "createdAt",
                "lastUpdatedAt",
                "testCode",
                "testNumber",
                "certificateNumber",
                "secondaryCertificateNumber",
                "testExpiryDate",
                "testAnniversaryDate",
                "testTypeStartTimestamp",
                "testTypeEndTimestamp",
                "numberOfSeatbeltsFitted",
                "lastSeatbeltInstallationCheckDate",
                "seatbeltInstallationCheckDate",
                "testResult",
                "reasonForAbandoning",
                "additionalNotesRecorded",
                "additionalCommentsForAbandon",
                "particulateTrapFitted",
                "particulateTrapSerialNumber",
                "modificationTypeUsed",
                "smokeTestKLimitApplied",
                "createdBy_Id",
                "lastUpdatedBy_Id",
        });

        return tableDetails;
    }

    @Override
    protected void setParameters(PreparedStatement preparedStatement, TestResult entity) throws SQLException {
        // 1-indexed
        preparedStatement.setString(1, entity.getVehicleID());
        preparedStatement.setString(2, entity.getFuelEmissionID());
        preparedStatement.setString(3, entity.getTestStationID());
        preparedStatement.setString(4, entity.getTesterID());
        preparedStatement.setString(5, entity.getPreparerID());
        preparedStatement.setString(6, entity.getVehicleClassID());
        preparedStatement.setString(7, entity.getTestTypeID());
        preparedStatement.setString(8, entity.getTestStatus());
        preparedStatement.setString(9, entity.getReasonForCancellation());
        preparedStatement.setString(10, entity.getNumberOfSeats());
        preparedStatement.setString(11, entity.getOdometerReading());
        preparedStatement.setString(12, entity.getOdometerReadingUnits());
        preparedStatement.setString(13, entity.getCountryOfRegistration());
        preparedStatement.setString(14, entity.getNoOfAxles());
        preparedStatement.setString(15, entity.getRegnDate());
        preparedStatement.setString(16, entity.getFirstUseDate());
        preparedStatement.setString(17, entity.getCreatedAt());
        preparedStatement.setString(18, entity.getLastUpdatedAt());
        preparedStatement.setString(19, entity.getTestCode());
        preparedStatement.setString(20, entity.getTestNumber());
        preparedStatement.setString(21, entity.getCertificateNumber());
        preparedStatement.setString(22, entity.getSecondaryCertificateNumber());
        preparedStatement.setString(23, entity.getTestExpiryDate());
        preparedStatement.setString(24, entity.getTestAnniversaryDate());
        preparedStatement.setString(25, entity.getTestTypeStartTimestamp());
        preparedStatement.setString(26, entity.getTestTypeEndTimestamp());
        preparedStatement.setString(27, entity.getNumberOfSeatbeltsFitted());
        preparedStatement.setString(28, entity.getLastSeatbeltInstallationCheckDate());
        preparedStatement.setString(29, entity.getSeatbeltInstallationCheckDate());
        preparedStatement.setString(30, entity.getTestResult());
        preparedStatement.setString(31, entity.getReasonForAbandoning());
        preparedStatement.setString(32, entity.getAdditionalNotesRecorded());
        preparedStatement.setString(33, entity.getAdditionalCommentsForAbandon());
        preparedStatement.setString(34, entity.getParticulateTrapFitted());
        preparedStatement.setString(35, entity.getParticulateTrapSerialNumber());
        preparedStatement.setString(36, entity.getModificationTypeUsed());
        preparedStatement.setString(37, entity.getSmokeTestKLimitApplied());
        preparedStatement.setString(38, entity.getCreatedByID());
        preparedStatement.setString(39, entity.getLastUpdatedByID());
    }

    @Override
    protected void setParametersFull(PreparedStatement preparedStatement, TestResult entity) throws SQLException {
        setParameters(preparedStatement, entity);

        preparedStatement.setString(40, entity.getVehicleID());
        preparedStatement.setString(41, entity.getFuelEmissionID());
        preparedStatement.setString(42, entity.getTestStationID());
        preparedStatement.setString(43, entity.getTesterID());
        preparedStatement.setString(44, entity.getPreparerID());
        preparedStatement.setString(45, entity.getVehicleClassID());
        preparedStatement.setString(46, entity.getTestTypeID());
        preparedStatement.setString(47, entity.getTestStatus());
        preparedStatement.setString(48, entity.getReasonForCancellation());
        preparedStatement.setString(49, entity.getNumberOfSeats());
        preparedStatement.setString(50, entity.getOdometerReading());
        preparedStatement.setString(51, entity.getOdometerReadingUnits());
        preparedStatement.setString(52, entity.getCountryOfRegistration());
        preparedStatement.setString(53, entity.getNoOfAxles());
        preparedStatement.setString(54, entity.getRegnDate());
        preparedStatement.setString(55, entity.getFirstUseDate());
        preparedStatement.setString(56, entity.getCreatedAt());
        preparedStatement.setString(57, entity.getLastUpdatedAt());
        preparedStatement.setString(58, entity.getTestCode());
        preparedStatement.setString(59, entity.getTestNumber());
        preparedStatement.setString(60, entity.getCertificateNumber());
        preparedStatement.setString(61, entity.getSecondaryCertificateNumber());
        preparedStatement.setString(62, entity.getTestExpiryDate());
        preparedStatement.setString(63, entity.getTestAnniversaryDate());
        preparedStatement.setString(64, entity.getTestTypeStartTimestamp());
        preparedStatement.setString(65, entity.getTestTypeEndTimestamp());
        preparedStatement.setString(66, entity.getNumberOfSeatbeltsFitted());
        preparedStatement.setString(67, entity.getLastSeatbeltInstallationCheckDate());
        preparedStatement.setString(68, entity.getSeatbeltInstallationCheckDate());
        preparedStatement.setString(69, entity.getTestResult());
        preparedStatement.setString(70, entity.getReasonForAbandoning());
        preparedStatement.setString(71, entity.getAdditionalNotesRecorded());
        preparedStatement.setString(72, entity.getAdditionalCommentsForAbandon());
        preparedStatement.setString(73, entity.getParticulateTrapFitted());
        preparedStatement.setString(74, entity.getParticulateTrapSerialNumber());
        preparedStatement.setString(75, entity.getModificationTypeUsed());
        preparedStatement.setString(76, entity.getSmokeTestKLimitApplied());
        preparedStatement.setString(77, entity.getCreatedByID());
        preparedStatement.setString(78, entity.getLastUpdatedByID());
    }

    @Override
    protected TestResult mapToEntity(ResultSet rs) throws SQLException {
        TestResult tr = new TestResult();

        tr.setVehicleID(rs.getString("vehicle_id"));
        tr.setFuelEmissionID(rs.getString("fuel_emission_id"));
        tr.setTestStationID(rs.getString("test_station_id"));
        tr.setTesterID(rs.getString("tester_id"));
        tr.setPreparerID(rs.getString("preparer_id"));
        tr.setVehicleClassID(rs.getString("vehicle_class_id"));
        tr.setTestTypeID(rs.getString("test_type_id"));
        tr.setTestStatus(rs.getString("testStatus"));
        tr.setReasonForCancellation(rs.getString("reasonForCancellation"));
        tr.setNumberOfSeats(rs.getString("numberOfSeats"));
        tr.setOdometerReading(rs.getString("odometerReading"));
        tr.setOdometerReadingUnits(rs.getString("odometerReadingUnits"));
        tr.setCountryOfRegistration(rs.getString("countryOfRegistration"));
        tr.setNoOfAxles(rs.getString("noOfAxles"));
        tr.setRegnDate(rs.getString("regnDate"));
        tr.setFirstUseDate(rs.getString("firstUseDate"));
        tr.setCreatedAt(rs.getString("createdAt"));
        tr.setLastUpdatedAt(rs.getString("lastUpdatedAt"));
        tr.setTestCode(rs.getString("testCode"));
        tr.setTestNumber(rs.getString("testNumber"));
        tr.setCertificateNumber(rs.getString("certificateNumber"));
        tr.setSecondaryCertificateNumber(rs.getString("secondaryCertificateNumber"));
        tr.setTestExpiryDate(rs.getString("testExpiryDate"));
        tr.setTestAnniversaryDate(rs.getString("testAnniversaryDate"));
        tr.setTestTypeStartTimestamp(rs.getString("testTypeStartTimestamp"));
        tr.setTestTypeEndTimestamp(rs.getString("testTypeEndTimestamp"));
        tr.setNumberOfSeatbeltsFitted(rs.getString("numberOfSeatbeltsFitted"));
        tr.setLastSeatbeltInstallationCheckDate(rs.getString("lastSeatbeltInstallationCheckDate"));
        tr.setSeatbeltInstallationCheckDate(rs.getString("seatbeltInstallationCheckDate"));
        tr.setTestResult(rs.getString("testResult"));
        tr.setReasonForAbandoning(rs.getString("reasonForAbandoning"));
        tr.setAdditionalNotesRecorded(rs.getString("additionalNotesRecorded"));
        tr.setAdditionalCommentsForAbandon(rs.getString("additionalCommentsForAbandon"));
        tr.setParticulateTrapFitted(rs.getString("particulateTrapFitted"));
        tr.setParticulateTrapSerialNumber(rs.getString("particulateTrapSerialNumber"));
        tr.setModificationTypeUsed(rs.getString("modificationTypeUsed"));
        tr.setSmokeTestKLimitApplied(rs.getString("smokeTestKLimitApplied"));
        tr.setCreatedByID(rs.getString("createdBy_Id"));
        tr.setLastUpdatedByID(rs.getString("lastUpdatedBy_Id"));

        return tr;
    }
}
