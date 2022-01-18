package testresults;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import data.GenericData;
import data.TestResultsData;
import model.testresults.TestResults;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Test;
import org.junit.runner.RunWith;
import steps.TestResultsSteps;
import util.DataUtil;
import util.JsonPathAlteration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static util.DataUtil.generateRandomExcludingValues;


@RunWith(SerenityRunner.class)
public class PostTestResultsPsvHgvTrlSubmitted {

    @Steps
    TestResultsSteps testResultsSteps;

    private TestResults.Builder vehicleSubmittedData = TestResultsData.buildTestResultsSubmittedData();
    private TestResults.Builder vehicleSubmittedDataOld = TestResultsData.buildTestResultsSubmittedDataOld();

    @Title("CVSB-6805 CVSB-7259 - API Consumer creates a new test results for the submitted test (TRL)")
    @Test
    public void testTestResultsPostValidTrl() {

        vehicleSubmittedData
                .setVin(generateRandomExcludingValues(21, vehicleSubmittedData.build().getVin()))
                .setTestResultId(generateRandomExcludingValues(3,vehicleSubmittedData.build().getTestResultId()))
                .setCountryOfRegistration("a")
                .setEuVehicleCategory("o3")
                .setNoOfAxles(6)
                .setReasonForCancellation(null)
                .setTestEndTimestamp("2021-01-16T16:42:14.757Z")
                .setTesterEmailAddress("cvs.automation3@dvsagov.onmicrosoft.com")
                .setTesterName("cvs.automation3@dvsagov.onmicrosoft.com")
                .setTestStationName("Abshire-Kub")
                .setTestStationPNumber("09-4129632")
                .setTestStationType("gvts")
                .setVehicleType("trl")
                .setVehicleConfiguration("articulated")
                .setTestStatus("submitted").build()
                .getTestTypes().get(0).setAdditionalCommentsForAbandon(null);

        vehicleSubmittedData.build().getTestTypes().get(0).setAdditionalNotesRecorded("a note");
        vehicleSubmittedData.build().getTestTypes().get(0).setCertificateNumber(null);
        vehicleSubmittedData.build().getTestTypes().get(0).setName("Retest");
        vehicleSubmittedData.build().getTestTypes().get(0).setProhibitionIssued(false);
        vehicleSubmittedData.build().getTestTypes().get(0).setReasonForAbandoning(null);
        vehicleSubmittedData.build().getTestTypes().get(0).setTestResult("fail");
        vehicleSubmittedData.build().getTestTypes().get(0).setTestTypeId("");
        vehicleSubmittedData.build().getTestTypes().get(0).setTestTypeName("Paid roadworthiness retest");
        vehicleSubmittedData.build().getVehicleClass().setCode("t").setDescription("trailer");

        vehicleSubmittedData.build().getTestTypes().get(0).getDefects().get(0)
                .setDeficiencyCategory("dangerous")
                .setDeficiencyId("a")
                .setDeficiencyRef("6.2.a.ii")
                .setDeficiencySubId("ii")
                .setDeficiencyText("with any visible elongation of a stud hole where secure fixing of a wheel is affected.")
                .setImDescription("Road Wheels and Hubs")
                .setImNumber(6)
                .setItemDescription("A wheel:")
                .setItemNumber(2)
                .setProhibitionIssued(true)
                .setPrs(false)
                .setStdForProhibition(false);

        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode payload = objectMapper.valueToTree(vehicleSubmittedData.build());

        testResultsSteps.addAdditionalTestResultsFieldValue(payload, "trailerId", "C234567");
        testResultsSteps.removeTestResultsFields(payload, "numberOfSeats", "odometerReading", "odometerReadingUnits", "vehicleId", "vehicleSize", "vrm");
        testResultsSteps.removeTestResultsTestTypesFields(payload, 0, "certificateLink", "createdAt", "lastSeatbeltInstallationCheckDate", "lastUpdatedAt", "numberOfSeatbeltsFitted", "seatbeltInstallationCheckDate", "testCode", "testNumber", "testExpiryDate", "testAnniversaryDate");

        testResultsSteps.postTestResultsPayload(payload);

        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
    }

    @Title("CVSB-6805 CVSB-7260 - API Consumer creates a new test results for the submitted test (TRL - missing mandatory fields)")
    @Test
    public void testTestResultsPostMissingMandatoryFieldsTrl() {

        vehicleSubmittedData
                .setVin(generateRandomExcludingValues(21, vehicleSubmittedData.build().getVin()))
                .setTestResultId(generateRandomExcludingValues(3,vehicleSubmittedData.build().getTestResultId()))
                .setCountryOfRegistration("a")
                .setEuVehicleCategory("o3")
                .setNoOfAxles(6)
                .setReasonForCancellation(null)
                .setTestEndTimestamp("2021-01-16T16:42:14.757Z")
                .setTesterEmailAddress("cvs.automation3@dvsagov.onmicrosoft.com")
                .setTesterName("cvs.automation3@dvsagov.onmicrosoft.com")
                .setTestStationName("Abshire-Kub")
                .setTestStationPNumber("09-4129632")
                .setTestStationType("gvts")
                .setVehicleType("trl")
                .setVehicleConfiguration("articulated")
                .setTestStatus("submitted").build()
                .getTestTypes().get(0).setAdditionalCommentsForAbandon(null);

        vehicleSubmittedData.build().getTestTypes().get(0).setAdditionalNotesRecorded("a note");
        vehicleSubmittedData.build().getTestTypes().get(0).setCertificateNumber(null);
        vehicleSubmittedData.build().getTestTypes().get(0).setName("Retest");
        vehicleSubmittedData.build().getTestTypes().get(0).setProhibitionIssued(false);
        vehicleSubmittedData.build().getTestTypes().get(0).setReasonForAbandoning(null);
        vehicleSubmittedData.build().getTestTypes().get(0).setTestResult("fail");
        vehicleSubmittedData.build().getTestTypes().get(0).setTestTypeId("");
        vehicleSubmittedData.build().getTestTypes().get(0).setTestTypeName("Paid roadworthiness retest");
        vehicleSubmittedData.build().getVehicleClass().setCode("t").setDescription("trailer");

        vehicleSubmittedData.build().getTestTypes().get(0).getDefects().get(0)
                .setDeficiencyCategory("dangerous")
                .setDeficiencyId("a")
                .setDeficiencyRef("6.2.a.ii")
                .setDeficiencySubId("ii")
                .setDeficiencyText("with any visible elongation of a stud hole where secure fixing of a wheel is affected.")
                .setImDescription("Road Wheels and Hubs")
                .setImNumber(6)
                .setItemDescription("A wheel:")
                .setItemNumber(2)
                .setProhibitionIssued(true)
                .setPrs(false)
                .setStdForProhibition(false);

        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode payload = objectMapper.valueToTree(vehicleSubmittedData.build());

        testResultsSteps.removeTestResultsFields(payload, "numberOfSeats", "odometerReading", "odometerReadingUnits", "vehicleId", "vehicleSize", "vrm");
        testResultsSteps.removeTestResultsTestTypesFields(payload, 0, "certificateLink", "createdAt", "lastSeatbeltInstallationCheckDate", "lastUpdatedAt", "numberOfSeatbeltsFitted", "seatbeltInstallationCheckDate", "testCode", "testNumber", "testExpiryDate", "testAnniversaryDate");

        testResultsSteps.postTestResultsPayload(payload);

        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("trailerId", "is required");
    }

    @Title("CVSB-6805 CVSB-7261 - API Consumer creates a new test results for the submitted test (TRL - additional fields - Seatbelt Installation)")
    @Test
    public void testTestResultsPostAdditionalFieldsTestTypesTrl() {

        vehicleSubmittedData
                .setVin(generateRandomExcludingValues(21, vehicleSubmittedData.build().getVin()))
                .setTestResultId(generateRandomExcludingValues(3,vehicleSubmittedData.build().getTestResultId()))
                .setCountryOfRegistration("a")
                .setEuVehicleCategory("o3")
                .setNoOfAxles(6)
                .setReasonForCancellation(null)
                .setTestEndTimestamp("2021-01-16T16:42:14.757Z")
                .setTesterEmailAddress("cvs.automation3@dvsagov.onmicrosoft.com")
                .setTesterName("cvs.automation3@dvsagov.onmicrosoft.com")
                .setTestStationName("Abshire-Kub")
                .setTestStationPNumber("09-4129632")
                .setTestStationType("gvts")
                .setVehicleType("trl")
                .setVehicleConfiguration("articulated")
                .setTestStatus("submitted").build()
                .getTestTypes().get(0).setAdditionalCommentsForAbandon(null);

        vehicleSubmittedData.build().getTestTypes().get(0).setAdditionalNotesRecorded("a note");
        vehicleSubmittedData.build().getTestTypes().get(0).setCertificateNumber(null);
        vehicleSubmittedData.build().getTestTypes().get(0).setName("Retest");
        vehicleSubmittedData.build().getTestTypes().get(0).setProhibitionIssued(false);
        vehicleSubmittedData.build().getTestTypes().get(0).setReasonForAbandoning(null);
        vehicleSubmittedData.build().getTestTypes().get(0).setTestResult("fail");
        vehicleSubmittedData.build().getTestTypes().get(0).setTestTypeId("");
        vehicleSubmittedData.build().getTestTypes().get(0).setTestTypeName("Paid roadworthiness retest");
        vehicleSubmittedData.build().getVehicleClass().setCode("t").setDescription("trailer");

        vehicleSubmittedData.build().getTestTypes().get(0).getDefects().get(0)
                .setDeficiencyCategory("dangerous")
                .setDeficiencyId("a")
                .setDeficiencyRef("6.2.a.ii")
                .setDeficiencySubId("ii")
                .setDeficiencyText("with any visible elongation of a stud hole where secure fixing of a wheel is affected.")
                .setImDescription("Road Wheels and Hubs")
                .setImNumber(6)
                .setItemDescription("A wheel:")
                .setItemNumber(2)
                .setProhibitionIssued(true)
                .setPrs(false)
                .setStdForProhibition(false);

        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode payload = objectMapper.valueToTree(vehicleSubmittedData.build());

        testResultsSteps.addAdditionalTestResultsFieldValue(payload, "trailerId", "C234567");
        testResultsSteps.removeTestResultsFields(payload, "vehicleId");
        testResultsSteps.removeTestResultsTestTypesFields(payload, 0, "certificateLink", "createdAt", "lastUpdatedAt", "numberOfSeatbeltsFitted", "testCode", "testNumber", "testExpiryDate", "testAnniversaryDate");

        testResultsSteps.postTestResultsPayload(payload);

        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorDataContains("lastSeatbeltInstallationCheckDate", "is not allowed");
        testResultsSteps.validatePostErrorDataContains("seatbeltInstallationCheckDate", "is not allowed");
    }

    @Title("CVSB-6805 CVSB-7261 - API Consumer creates a new test results for the submitted test (TRL - additional fields - Odometer, vrm, vehicle size, number of seats)")
    @Test
    public void testTestResultsPostAdditionalFieldsTrl() {

        vehicleSubmittedData
                .setVin(generateRandomExcludingValues(21, vehicleSubmittedData.build().getVin()))
                .setTestResultId(generateRandomExcludingValues(3,vehicleSubmittedData.build().getTestResultId()))
                .setCountryOfRegistration("a")
                .setEuVehicleCategory("o3")
                .setNoOfAxles(6)
                .setReasonForCancellation(null)
                .setTestEndTimestamp("2021-01-16T16:42:14.757Z")
                .setTesterEmailAddress("cvs.automation3@dvsagov.onmicrosoft.com")
                .setTesterName("cvs.automation3@dvsagov.onmicrosoft.com")
                .setTestStationName("Abshire-Kub")
                .setTestStationPNumber("09-4129632")
                .setTestStationType("gvts")
                .setVehicleType("trl")
                .setVehicleConfiguration("articulated")
                .setTestStatus("submitted").build()
                .getTestTypes().get(0).setAdditionalCommentsForAbandon(null);

        vehicleSubmittedData.build().getTestTypes().get(0).setAdditionalNotesRecorded("a note");
        vehicleSubmittedData.build().getTestTypes().get(0).setCertificateNumber(null);
        vehicleSubmittedData.build().getTestTypes().get(0).setName("Retest");
        vehicleSubmittedData.build().getTestTypes().get(0).setProhibitionIssued(false);
        vehicleSubmittedData.build().getTestTypes().get(0).setReasonForAbandoning(null);
        vehicleSubmittedData.build().getTestTypes().get(0).setTestResult("fail");
        vehicleSubmittedData.build().getTestTypes().get(0).setTestTypeId("");
        vehicleSubmittedData.build().getTestTypes().get(0).setTestTypeName("Paid roadworthiness retest");
        vehicleSubmittedData.build().getVehicleClass().setCode("t").setDescription("trailer");

        vehicleSubmittedData.build().getTestTypes().get(0).getDefects().get(0)
                .setDeficiencyCategory("dangerous")
                .setDeficiencyId("a")
                .setDeficiencyRef("6.2.a.ii")
                .setDeficiencySubId("ii")
                .setDeficiencyText("with any visible elongation of a stud hole where secure fixing of a wheel is affected.")
                .setImDescription("Road Wheels and Hubs")
                .setImNumber(6)
                .setItemDescription("A wheel:")
                .setItemNumber(2)
                .setProhibitionIssued(true)
                .setPrs(false)
                .setStdForProhibition(false);

        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode payload = objectMapper.valueToTree(vehicleSubmittedData.build());

        testResultsSteps.addAdditionalTestResultsFieldValue(payload, "trailerId", "C234567");
        testResultsSteps.removeTestResultsFields(payload, "vehicleId");
        testResultsSteps.removeTestResultsTestTypesFields(payload, 0, "certificateLink", "createdAt", "lastUpdatedAt", "numberOfSeatbeltsFitted", "testCode", "testNumber", "testExpiryDate", "testAnniversaryDate", "lastSeatbeltInstallationCheckDate", "seatbeltInstallationCheckDate");

        testResultsSteps.postTestResultsPayload(payload);

        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorDataContains("odometerReading", "is not allowed");
        testResultsSteps.validatePostErrorDataContains("odometerReadingUnits", "is not allowed");
        testResultsSteps.validatePostErrorDataContains("vrm", "is not allowed");
        testResultsSteps.validatePostErrorDataContains("numberOfSeats", "is not allowed");
        testResultsSteps.validatePostErrorDataContains("vehicleSize", "is not allowed");
    }

    @Title("CVSB-6805 - CVSB-7253 - API Consumer creates a new test results for the submitted test (PSV)")
    @Test
    public void testTestResultsPostValidPsv() {

        vehicleSubmittedDataOld
                .setVin(generateRandomExcludingValues(21, vehicleSubmittedDataOld.build().getVin()))
                .setVrm(generateRandomExcludingValues(7, vehicleSubmittedDataOld.build().getVrm()))
                .setSystemNumber(generateRandomExcludingValues(16, vehicleSubmittedDataOld.build().getSystemNumber()))
                .setTestResultId(generateRandomExcludingValues(16, vehicleSubmittedDataOld.build().getTestResultId()))
                .build();

        vehicleSubmittedDataOld.build().getTestTypes().get(0).setTestTypeId("1");

        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode payload = objectMapper.valueToTree(vehicleSubmittedDataOld.build());

        testResultsSteps.removeTestResultsFields(payload, "vehicleId");
        testResultsSteps.removeTestResultsTestTypesFields(payload, 0, "createdAt", "lastUpdatedAt",
                "testCode", "testNumber", "certificateLink", "testExpiryDate", "testAnniversaryDate");
        testResultsSteps.changeTestResultsFieldValue(payload, "vehicleType", "psv");
        testResultsSteps.changeTestResultsFieldValue(payload, "testResultId", UUID.randomUUID().toString());
        testResultsSteps.postTestResultsPayload(payload);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");

        testResultsSteps.getTestResults(vehicleSubmittedDataOld.build().getSystemNumber());
        testResultsSteps.statusCodeShouldBe(200);

        testResultsSteps.validateVehicleFieldExists("vrm");
        testResultsSteps.validateVehicleFieldValue("numberOfSeats", 45);
        testResultsSteps.validateVehicleFieldValue("odometerReading", 350000);
        testResultsSteps.validateVehicleFieldValue("odometerReadingUnits", "kilometres");
        testResultsSteps.validateVehicleFieldValue("vehicleSize", "small");
        testResultsSteps.validateTestFieldExists("lastSeatbeltInstallationCheckDate");
        testResultsSteps.validateTestFieldExists("numberOfSeatbeltsFitted");
        testResultsSteps.validateTestFieldExists("seatbeltInstallationCheckDate");
    }

    @Title("CVSB-6805 - CVSB-7256 - API Consumer creates a new test results for the submitted test (HGV)")
    @Test
    public void testTestResultsPostValidHgv() {

        vehicleSubmittedData
                .setVin(generateRandomExcludingValues(21, vehicleSubmittedData.build().getVin()))
                .setVrm(generateRandomExcludingValues(7, vehicleSubmittedData.build().getVrm()))
                .setSystemNumber(generateRandomExcludingValues(16, vehicleSubmittedData.build().getSystemNumber()))
                .setTestResultId(generateRandomExcludingValues(3,vehicleSubmittedData.build().getTestResultId()))
                .setCountryOfRegistration("a")
                .setEuVehicleCategory("n2")
                .setNoOfAxles(2)
                .setReasonForCancellation(null)
                .setTestStationName("Abshire-Kub")
                .setTestStationPNumber("09-4129632")
                .setTestStationType("gvts")
                .setVehicleConfiguration("articulated")
                .setTestStatus("submitted")
                .build();

        vehicleSubmittedData.build().getTestTypes().get(0).setName("First test");
        vehicleSubmittedData.build().getTestTypes().get(0).setTestTypeName("First test");
        vehicleSubmittedData.build().getTestTypes().get(0).setTestTypeId("95");
        vehicleSubmittedData.build().getTestTypes().get(0).setCertificateNumber(null);
        vehicleSubmittedData.build().getTestTypes().get(0).setTestResult("fail");
        vehicleSubmittedData.build().getTestTypes().get(0).setProhibitionIssued(false);
        vehicleSubmittedData.build().getTestTypes().get(0).setReasonForAbandoning(null);
        vehicleSubmittedData.build().getTestTypes().get(0).setAdditionalCommentsForAbandon(null);
        vehicleSubmittedData.build().getTestTypes().get(0).setAdditionalNotesRecorded(null);

        vehicleSubmittedData.build().getVehicleClass().setCode("v").setDescription("heavy goods vehicle");
        vehicleSubmittedData.build().getTestTypes().get(0).getDefects().get(0)
                .setDeficiencyCategory("major")
                .setDeficiencyId("b")
                .setDeficiencyRef("3.1.b")
                .setDeficiencySubId(null)
                .setDeficiencyText("of an incorrect type.")
                .setImDescription("Seat Belts & Supplementary Restraint Systems")
                .setImNumber(3)
                .setItemDescription("Obligatory Seat Belt:")
                .setItemNumber(1)
                .setProhibitionIssued(true)
                .setPrs(false)
                .setStdForProhibition(false);

        vehicleSubmittedData.build().getTestTypes().get(0).getDefects().get(0).getAdditionalInformation()
                .setNotes("not working")
                .getLocation()
                .setVertical("lower")
                .setHorizontal(null)
                .setLateral("offside")
                .setLongitudinal(null)
                .setRowNumber(null)
                .setSeatNumber(null)
                .setAxleNumber(null);

        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode payload = objectMapper.valueToTree(vehicleSubmittedData.build());

        testResultsSteps.removeTestResultsFields(payload, "vehicleSize", "numberOfSeats", "vehicleId");
        testResultsSteps.removeTestResultsTestTypesFields(payload, 0, "numberOfSeatbeltsFitted", "lastSeatbeltInstallationCheckDate", "seatbeltInstallationCheckDate", "createdAt", "lastUpdatedAt", "testCode", "testNumber", "certificateLink", "testExpiryDate", "testAnniversaryDate");

        testResultsSteps.postTestResultsPayload(payload);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");

        testResultsSteps.getTestResults(vehicleSubmittedData.build().getSystemNumber());
        testResultsSteps.statusCodeShouldBe(200);

        testResultsSteps.validateVehicleFieldExists("vrm");
        testResultsSteps.validateVehicleFieldValue("vehicleType", "hgv");
        testResultsSteps.validateVehicleFieldValue("odometerReading", 100000);
        testResultsSteps.validateVehicleFieldValue("odometerReadingUnits", "kilometres");
    }

    @Title("CVSB-6805 - CVSB-7254 - API Consumer creates a new test results for the submitted test (PSV - missing mandatory fields - vrm )")
    @Test
    public void testTestResultsPostMissingMandatoryFieldsVrmPsv() {

        vehicleSubmittedData
                .setVin(generateRandomExcludingValues(21, vehicleSubmittedData.build().getVin()))
                .setVrm(generateRandomExcludingValues(7, vehicleSubmittedData.build().getVrm()))
                .build();

        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode payload = objectMapper.valueToTree(vehicleSubmittedData.build());

        testResultsSteps.removeTestResultsFields(payload, "vehicleId", "vrm");
        testResultsSteps.removeTestResultsTestTypesFields(payload, 0,  "createdAt", "lastUpdatedAt", "testCode", "testNumber", "certificateLink", "testExpiryDate", "testAnniversaryDate");
        testResultsSteps.changeTestResultsFieldValue(payload, "vehicleType", "psv");

        testResultsSteps.postTestResultsPayload(payload);
        testResultsSteps.statusCodeShouldBe(400);

        testResultsSteps.validatePostErrorDataContains("vrm", "is required");

    }

    @Title("CVSB-6805 - CVSB-7254 - API Consumer creates a new test results for the submitted test (PSV - missing mandatory fields - numberOfSeats )")
    @Test
    public void testTestResultsPostMissingMandatoryFieldsNoSeatsPsv() {

        vehicleSubmittedData
                .setVin(generateRandomExcludingValues(21, vehicleSubmittedData.build().getVin()))
                .setVrm(generateRandomExcludingValues(7, vehicleSubmittedData.build().getVrm()))
                .build();

        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode payload = objectMapper.valueToTree(vehicleSubmittedData.build());

        testResultsSteps.removeTestResultsFields(payload, "vehicleId", "numberOfSeats");
        testResultsSteps.removeTestResultsTestTypesFields(payload, 0,  "createdAt", "lastUpdatedAt", "testCode", "testNumber", "certificateLink", "testExpiryDate", "testAnniversaryDate");
        testResultsSteps.changeTestResultsFieldValue(payload, "vehicleType", "psv");

        testResultsSteps.postTestResultsPayload(payload);
        testResultsSteps.statusCodeShouldBe(400);

        testResultsSteps.validatePostErrorDataContains("numberOfSeats", "is required");
    }

    @Title("CVSB-6805 - CVSB-7254 - API Consumer creates a new test results for the submitted test (PSV - missing mandatory fields - odometerReading )")
    @Test
    public void testTestResultsPostMissingMandatoryOdometerFieldsPsv() {

        vehicleSubmittedData
                .setVin(generateRandomExcludingValues(21, vehicleSubmittedData.build().getVin()))
                .setVrm(generateRandomExcludingValues(7, vehicleSubmittedData.build().getVrm()))
                .build();

        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode payload = objectMapper.valueToTree(vehicleSubmittedData.build());

        testResultsSteps.removeTestResultsFields(payload, "vehicleId", "odometerReading");
        testResultsSteps.removeTestResultsTestTypesFields(payload, 0,  "createdAt", "lastUpdatedAt", "testCode", "testNumber", "certificateLink", "testExpiryDate", "testAnniversaryDate");
        testResultsSteps.changeTestResultsFieldValue(payload, "vehicleType", "psv");

        testResultsSteps.postTestResultsPayload(payload);
        testResultsSteps.statusCodeShouldBe(400);

        testResultsSteps.validatePostErrorDataContains("odometerReading", "is mandatory");
    }

    @Title("CVSB-6805 - CVSB-7254 - API Consumer creates a new test results for the submitted test (PSV - missing mandatory fields - odometerReadingUnits )")
    @Test
    public void testTestResultsPostMissingMandatoryOdometerUnitsFieldsPsv() {

        vehicleSubmittedData
                .setVin(generateRandomExcludingValues(21, vehicleSubmittedData.build().getVin()))
                .setVrm(generateRandomExcludingValues(7, vehicleSubmittedData.build().getVrm()))
                .build();

        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode payload = objectMapper.valueToTree(vehicleSubmittedData.build());

        testResultsSteps.removeTestResultsFields(payload, "vehicleId", "odometerReadingUnits");
        testResultsSteps.removeTestResultsTestTypesFields(payload, 0,  "createdAt", "lastUpdatedAt", "testCode", "testNumber", "certificateLink", "testExpiryDate", "testAnniversaryDate");
        testResultsSteps.changeTestResultsFieldValue(payload, "vehicleType", "psv");

        testResultsSteps.postTestResultsPayload(payload);
        testResultsSteps.statusCodeShouldBe(400);

        testResultsSteps.validatePostErrorDataContains("odometerReadingUnits", "is mandatory");
    }

    @Title("CVSB-6805 - CVSB-7254 - API Consumer creates a new test results for the submitted test (PSV - missing mandatory fields - vehicleSize )")
    @Test
    public void testTestResultsPostMissingMandatoryFieldsVehicleSizePsv() {

        vehicleSubmittedData
                .setVin(generateRandomExcludingValues(21, vehicleSubmittedData.build().getVin()))
                .setVrm(generateRandomExcludingValues(7, vehicleSubmittedData.build().getVrm()))
                .build();

        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode payload = objectMapper.valueToTree(vehicleSubmittedData.build());

        testResultsSteps.removeTestResultsFields(payload, "vehicleId", "vehicleSize");
        testResultsSteps.removeTestResultsTestTypesFields(payload, 0,  "createdAt", "lastUpdatedAt", "testCode", "testNumber", "certificateLink", "testExpiryDate", "testAnniversaryDate");
        testResultsSteps.changeTestResultsFieldValue(payload, "vehicleType", "psv");

        testResultsSteps.postTestResultsPayload(payload);
        testResultsSteps.statusCodeShouldBe(400);

        testResultsSteps.validatePostErrorDataContains("vehicleSize", "is required");
    }

    @Title("CVSB-6805 - CVSB-7254 - API Consumer creates a new test results for the submitted test (PSV - missing mandatory fields - numberOfSeatbeltsFitted )")
    @Test
    public void testTestResultsPostMissingMandatoryFieldsNoSeatbeltsPsv() {

        vehicleSubmittedData
                .setVin(generateRandomExcludingValues(21, vehicleSubmittedData.build().getVin()))
                .setVrm(generateRandomExcludingValues(7, vehicleSubmittedData.build().getVrm()))
                .build();

        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode payload = objectMapper.valueToTree(vehicleSubmittedData.build());

        testResultsSteps.removeTestResultsFields(payload, "vehicleId");
        testResultsSteps.removeTestResultsTestTypesFields(payload, 0,  "createdAt", "lastUpdatedAt", "testCode", "testNumber", "certificateLink", "testExpiryDate", "testAnniversaryDate", "numberOfSeatbeltsFitted");
        testResultsSteps.changeTestResultsFieldValue(payload, "vehicleType", "psv");

        testResultsSteps.postTestResultsPayload(payload);
        testResultsSteps.statusCodeShouldBe(400);

        testResultsSteps.validatePostErrorDataContains("numberOfSeatbeltsFitted", "is required");

    }

    @Title("CVSB-6805 - CVSB-7254 - API Consumer creates a new test results for the submitted test (PSV - missing mandatory fields - seatbeltInstallationCheckDate )")
    @Test
    public void testTestResultsPostMissingMandatoryFieldsSeatbeltInstallDatePsv() {

        vehicleSubmittedData
                .setVin(generateRandomExcludingValues(21, vehicleSubmittedData.build().getVin()))
                .setVrm(generateRandomExcludingValues(7, vehicleSubmittedData.build().getVrm()))
                .build();

        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode payload = objectMapper.valueToTree(vehicleSubmittedData.build());

        testResultsSteps.removeTestResultsFields(payload, "vehicleId");
        testResultsSteps.removeTestResultsTestTypesFields(payload, 0,  "createdAt", "lastUpdatedAt", "testCode", "testNumber", "certificateLink", "testExpiryDate", "testAnniversaryDate", "seatbeltInstallationCheckDate");
        testResultsSteps.changeTestResultsFieldValue(payload, "vehicleType", "psv");

        testResultsSteps.postTestResultsPayload(payload);
        testResultsSteps.statusCodeShouldBe(400);

        testResultsSteps.validatePostErrorDataContains("seatbeltInstallationCheckDate", "is required");
    }

    @Title("CVSB-6805 - CVSB-7254 - API Consumer creates a new test results for the submitted test (PSV - missing mandatory fields - lastSeatbeltInstallationCheckDate )")
    @Test
    public void testTestResultsPostMissingMandatoryFieldsLastSeatbeltInstallDatePsv() {

        vehicleSubmittedData
                .setVin(generateRandomExcludingValues(21, vehicleSubmittedData.build().getVin()))
                .setVrm(generateRandomExcludingValues(7, vehicleSubmittedData.build().getVrm()))
                .build();

        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode payload = objectMapper.valueToTree(vehicleSubmittedData.build());

        testResultsSteps.removeTestResultsFields(payload, "vehicleId");
        testResultsSteps.removeTestResultsTestTypesFields(payload, 0,  "createdAt", "lastUpdatedAt", "testCode", "testNumber", "certificateLink", "testExpiryDate", "testAnniversaryDate", "lastSeatbeltInstallationCheckDate");
        testResultsSteps.changeTestResultsFieldValue(payload, "vehicleType", "psv");

        testResultsSteps.postTestResultsPayload(payload);
        testResultsSteps.statusCodeShouldBe(400);

        testResultsSteps.validatePostErrorDataContains("lastSeatbeltInstallationCheckDate", "is required");
    }

    @Title("CVSB-6805 - CVSB-7255 - API Consumer creates a new test results for the submitted test (PSV - additional fields)")
    @Test
    public void testTestResultsPostAdditionalFieldsPsv() {

        vehicleSubmittedData
                .setVin(generateRandomExcludingValues(21, vehicleSubmittedData.build().getVin()))
                .setVrm(generateRandomExcludingValues(7, vehicleSubmittedData.build().getVrm()))
                .build();

        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode payload = objectMapper.valueToTree(vehicleSubmittedData.build());

        testResultsSteps.removeTestResultsFields(payload, "vehicleId");
        testResultsSteps.removeTestResultsTestTypesFields(payload, 0,  "createdAt", "lastUpdatedAt", "testCode", "testNumber", "certificateLink", "testExpiryDate", "testAnniversaryDate");
        testResultsSteps.addAdditionalTestResultsFieldValue(payload,"trailerId","jkflds");
        testResultsSteps.changeTestResultsFieldValue(payload, "vehicleType", "psv");

        testResultsSteps.postTestResultsPayload(payload);
        testResultsSteps.statusCodeShouldBe(400);

        testResultsSteps.validatePostErrorDataContains("trailerId", "is not allowed");
    }


    @Title("CVSB-6805 - CVSB-7257 - API Consumer creates a new test results for the submitted test (HGV - missing mandatory fields - vrm)")
    @Test
    public void testTestResultsPostMissingMandatoryFieldsVrmHgv() {

        vehicleSubmittedData
                .setVin(generateRandomExcludingValues(21, vehicleSubmittedData.build().getVin()))
                .setVrm(generateRandomExcludingValues(7, vehicleSubmittedData.build().getVrm()))
                .setTestResultId(generateRandomExcludingValues(3,vehicleSubmittedData.build().getTestResultId()))
                .setCountryOfRegistration("a")
                .setEuVehicleCategory("n2")
                .setNoOfAxles(2)
                .setReasonForCancellation(null)
                .setTestStationName("Abshire-Kub")
                .setTestStationPNumber("09-4129632")
                .setTestStationType("gvts")
                .setVehicleType("hgv")
                .setVehicleConfiguration("articulated")
                .setTestStatus("submitted")
                .build();

        vehicleSubmittedData.build().getTestTypes().get(0).setName("First test");
        vehicleSubmittedData.build().getTestTypes().get(0).setTestTypeName("First test");
        vehicleSubmittedData.build().getTestTypes().get(0).setTestTypeId("41");
        vehicleSubmittedData.build().getTestTypes().get(0).setCertificateNumber(null);
        vehicleSubmittedData.build().getTestTypes().get(0).setTestResult("fail");
        vehicleSubmittedData.build().getTestTypes().get(0).setProhibitionIssued(false);
        vehicleSubmittedData.build().getTestTypes().get(0).setReasonForAbandoning(null);
        vehicleSubmittedData.build().getTestTypes().get(0).setAdditionalCommentsForAbandon(null);
        vehicleSubmittedData.build().getTestTypes().get(0).setAdditionalNotesRecorded(null);

        vehicleSubmittedData.build().getVehicleClass().setCode("v").setDescription("heavy goods vehicle");
        vehicleSubmittedData.build().getTestTypes().get(0).getDefects().get(0)
                .setDeficiencyCategory("major")
                .setDeficiencyId("b")
                .setDeficiencyRef("3.1.b")
                .setDeficiencySubId(null)
                .setDeficiencyText("of an incorrect type.")
                .setImDescription("Seat Belts & Supplementary Restraint Systems")
                .setImNumber(3)
                .setItemDescription("Obligatory Seat Belt:")
                .setItemNumber(1)
                .setProhibitionIssued(true)
                .setPrs(false)
                .setStdForProhibition(false);

        vehicleSubmittedData.build().getTestTypes().get(0).getDefects().get(0).getAdditionalInformation()
                .setNotes("not working")
                .getLocation()
                .setVertical("lower")
                .setHorizontal(null)
                .setLateral("offside")
                .setLongitudinal(null)
                .setRowNumber(null)
                .setSeatNumber(null)
                .setAxleNumber(null);

        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode payload = objectMapper.valueToTree(vehicleSubmittedData.build());

        testResultsSteps.removeTestResultsFields(payload, "vehicleSize", "numberOfSeats", "vehicleId", "vrm");
        testResultsSteps.removeTestResultsTestTypesFields(payload, 0, "numberOfSeatbeltsFitted", "lastSeatbeltInstallationCheckDate", "seatbeltInstallationCheckDate", "createdAt", "lastUpdatedAt", "testCode", "testNumber", "certificateLink", "testExpiryDate", "testAnniversaryDate");

        testResultsSteps.postTestResultsPayload(payload);
        testResultsSteps.statusCodeShouldBe(400);

        testResultsSteps.validatePostErrorDataContains("vrm", "is required");

    }

    @Title("CVSB-6805 - CVSB-7257 - API Consumer creates a new test results for the submitted test (HGV - missing mandatory fields - odometerReading)")
    @Test
    public void testTestResultsPostMissingMandatoryFieldsOdometerHgv() {

        vehicleSubmittedData
                .setVin(generateRandomExcludingValues(21, vehicleSubmittedData.build().getVin()))
                .setVrm(generateRandomExcludingValues(7, vehicleSubmittedData.build().getVrm()))
                .setTestResultId(generateRandomExcludingValues(3,vehicleSubmittedData.build().getTestResultId()))
                .setCountryOfRegistration("a")
                .setEuVehicleCategory("n2")
                .setNoOfAxles(2)
                .setReasonForCancellation(null)
                .setTestStationName("Abshire-Kub")
                .setTestStationPNumber("09-4129632")
                .setTestStationType("gvts")
                .setVehicleType("hgv")
                .setVehicleConfiguration("articulated")
                .setTestStatus("submitted")
                .build();

        vehicleSubmittedData.build().getTestTypes().get(0).setName("First test");
        vehicleSubmittedData.build().getTestTypes().get(0).setTestTypeName("First test");
        vehicleSubmittedData.build().getTestTypes().get(0).setTestTypeId("41");
        vehicleSubmittedData.build().getTestTypes().get(0).setCertificateNumber(null);
        vehicleSubmittedData.build().getTestTypes().get(0).setTestResult("fail");
        vehicleSubmittedData.build().getTestTypes().get(0).setProhibitionIssued(false);
        vehicleSubmittedData.build().getTestTypes().get(0).setReasonForAbandoning(null);
        vehicleSubmittedData.build().getTestTypes().get(0).setAdditionalCommentsForAbandon(null);
        vehicleSubmittedData.build().getTestTypes().get(0).setAdditionalNotesRecorded(null);

        vehicleSubmittedData.build().getVehicleClass().setCode("v").setDescription("heavy goods vehicle");
        vehicleSubmittedData.build().getTestTypes().get(0).getDefects().get(0)
                .setDeficiencyCategory("major")
                .setDeficiencyId("b")
                .setDeficiencyRef("3.1.b")
                .setDeficiencySubId(null)
                .setDeficiencyText("of an incorrect type.")
                .setImDescription("Seat Belts & Supplementary Restraint Systems")
                .setImNumber(3)
                .setItemDescription("Obligatory Seat Belt:")
                .setItemNumber(1)
                .setProhibitionIssued(true)
                .setPrs(false)
                .setStdForProhibition(false);

        vehicleSubmittedData.build().getTestTypes().get(0).getDefects().get(0).getAdditionalInformation()
                .setNotes("not working")
                .getLocation()
                .setVertical("lower")
                .setHorizontal(null)
                .setLateral("offside")
                .setLongitudinal(null)
                .setRowNumber(null)
                .setSeatNumber(null)
                .setAxleNumber(null);

        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode payload = objectMapper.valueToTree(vehicleSubmittedData.build());

        testResultsSteps.removeTestResultsFields(payload, "vehicleSize", "numberOfSeats", "vehicleId", "odometerReading");
        testResultsSteps.removeTestResultsTestTypesFields(payload, 0, "numberOfSeatbeltsFitted", "lastSeatbeltInstallationCheckDate", "seatbeltInstallationCheckDate", "createdAt", "lastUpdatedAt", "testCode", "testNumber", "certificateLink", "testExpiryDate", "testAnniversaryDate");

        testResultsSteps.postTestResultsPayload(payload);
        testResultsSteps.statusCodeShouldBe(400);

        testResultsSteps.validatePostErrorDataContains("odometerReading", "is mandatory");

    }

 @Title("CVSB-6805 - CVSB-7257 - API Consumer creates a new test results for the submitted test (HGV - missing mandatory fields - odometerReadingUnits)")
    @Test
    public void testTestResultsPostMissingMandatoryFieldsOdometerUnitsHgv() {

        vehicleSubmittedData
                .setVin(generateRandomExcludingValues(21, vehicleSubmittedData.build().getVin()))
                .setVrm(generateRandomExcludingValues(7, vehicleSubmittedData.build().getVrm()))
                .setTestResultId(generateRandomExcludingValues(3,vehicleSubmittedData.build().getTestResultId()))
                .setCountryOfRegistration("a")
                .setEuVehicleCategory("n2")
                .setNoOfAxles(2)
                .setReasonForCancellation(null)
                .setTestStationName("Abshire-Kub")
                .setTestStationPNumber("09-4129632")
                .setTestStationType("gvts")
                .setVehicleType("hgv")
                .setVehicleConfiguration("articulated")
                .setTestStatus("submitted")
                .build();

        vehicleSubmittedData.build().getTestTypes().get(0).setName("First test");
        vehicleSubmittedData.build().getTestTypes().get(0).setTestTypeName("First test");
        vehicleSubmittedData.build().getTestTypes().get(0).setTestTypeId("41");
        vehicleSubmittedData.build().getTestTypes().get(0).setCertificateNumber(null);
        vehicleSubmittedData.build().getTestTypes().get(0).setTestResult("fail");
        vehicleSubmittedData.build().getTestTypes().get(0).setProhibitionIssued(false);
        vehicleSubmittedData.build().getTestTypes().get(0).setReasonForAbandoning(null);
        vehicleSubmittedData.build().getTestTypes().get(0).setAdditionalCommentsForAbandon(null);
        vehicleSubmittedData.build().getTestTypes().get(0).setAdditionalNotesRecorded(null);

        vehicleSubmittedData.build().getVehicleClass().setCode("v").setDescription("heavy goods vehicle");
        vehicleSubmittedData.build().getTestTypes().get(0).getDefects().get(0)
                .setDeficiencyCategory("major")
                .setDeficiencyId("b")
                .setDeficiencyRef("3.1.b")
                .setDeficiencySubId(null)
                .setDeficiencyText("of an incorrect type.")
                .setImDescription("Seat Belts & Supplementary Restraint Systems")
                .setImNumber(3)
                .setItemDescription("Obligatory Seat Belt:")
                .setItemNumber(1)
                .setProhibitionIssued(true)
                .setPrs(false)
                .setStdForProhibition(false);

        vehicleSubmittedData.build().getTestTypes().get(0).getDefects().get(0).getAdditionalInformation()
                .setNotes("not working")
                .getLocation()
                .setVertical("lower")
                .setHorizontal(null)
                .setLateral("offside")
                .setLongitudinal(null)
                .setRowNumber(null)
                .setSeatNumber(null)
                .setAxleNumber(null);

        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode payload = objectMapper.valueToTree(vehicleSubmittedData.build());

        testResultsSteps.removeTestResultsFields(payload, "vehicleSize", "numberOfSeats", "vehicleId", "odometerReadingUnits");
        testResultsSteps.removeTestResultsTestTypesFields(payload, 0, "numberOfSeatbeltsFitted", "lastSeatbeltInstallationCheckDate", "seatbeltInstallationCheckDate", "createdAt", "lastUpdatedAt", "testCode", "testNumber", "certificateLink", "testExpiryDate", "testAnniversaryDate");

        testResultsSteps.postTestResultsPayload(payload);
        testResultsSteps.statusCodeShouldBe(400);

        testResultsSteps.validatePostErrorDataContains("odometerReadingUnits", "is mandatory");

    }

@Title("CVSB-6805 - CVSB-7258 - API Consumer creates a new test results for the submitted test (HGV - additional fields - trailerId, numberOfSeats, vehicleSize)")
    @Test
    public void testTestResultsPostAdditionalFieldsTrailerIdVehicleSizeNoSeatsHgv() {

        vehicleSubmittedData
                .setVin(generateRandomExcludingValues(21, vehicleSubmittedData.build().getVin()))
                .setVrm(generateRandomExcludingValues(7, vehicleSubmittedData.build().getVrm()))
                .setTestResultId(generateRandomExcludingValues(3,vehicleSubmittedData.build().getTestResultId()))
                .setCountryOfRegistration("a")
                .setEuVehicleCategory("n2")
                .setNoOfAxles(2)
                .setReasonForCancellation(null)
                .setTestStationName("Abshire-Kub")
                .setTestStationPNumber("09-4129632")
                .setTestStationType("gvts")
                .setVehicleType("hgv")
                .setVehicleConfiguration("articulated")
                .setTestStatus("submitted")
                .build();

        vehicleSubmittedData.build().getTestTypes().get(0).setName("First test");
        vehicleSubmittedData.build().getTestTypes().get(0).setTestTypeName("First test");
        vehicleSubmittedData.build().getTestTypes().get(0).setTestTypeId("41");
        vehicleSubmittedData.build().getTestTypes().get(0).setCertificateNumber(null);
        vehicleSubmittedData.build().getTestTypes().get(0).setTestResult("fail");
        vehicleSubmittedData.build().getTestTypes().get(0).setProhibitionIssued(false);
        vehicleSubmittedData.build().getTestTypes().get(0).setReasonForAbandoning(null);
        vehicleSubmittedData.build().getTestTypes().get(0).setAdditionalCommentsForAbandon(null);
        vehicleSubmittedData.build().getTestTypes().get(0).setAdditionalNotesRecorded(null);

        vehicleSubmittedData.build().getVehicleClass().setCode("v").setDescription("heavy goods vehicle");
        vehicleSubmittedData.build().getTestTypes().get(0).getDefects().get(0)
                .setDeficiencyCategory("major")
                .setDeficiencyId("b")
                .setDeficiencyRef("3.1.b")
                .setDeficiencySubId(null)
                .setDeficiencyText("of an incorrect type.")
                .setImDescription("Seat Belts & Supplementary Restraint Systems")
                .setImNumber(3)
                .setItemDescription("Obligatory Seat Belt:")
                .setItemNumber(1)
                .setProhibitionIssued(true)
                .setPrs(false)
                .setStdForProhibition(false);

        vehicleSubmittedData.build().getTestTypes().get(0).getDefects().get(0).getAdditionalInformation()
                .setNotes("not working")
                .getLocation()
                .setVertical("lower")
                .setHorizontal(null)
                .setLateral("offside")
                .setLongitudinal(null)
                .setRowNumber(null)
                .setSeatNumber(null)
                .setAxleNumber(null);

        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode payload = objectMapper.valueToTree(vehicleSubmittedData.build());

        testResultsSteps.removeTestResultsFields(payload,  "vehicleId");
        testResultsSteps.removeTestResultsTestTypesFields(payload, 0, "numberOfSeatbeltsFitted", "lastSeatbeltInstallationCheckDate", "seatbeltInstallationCheckDate", "createdAt", "lastUpdatedAt", "testCode", "testNumber", "certificateLink", "testExpiryDate", "testAnniversaryDate");

        testResultsSteps.addAdditionalTestResultsFieldValue(payload, "trailerId", "C234567");

        testResultsSteps.postTestResultsPayload(payload);
        testResultsSteps.statusCodeShouldBe(400);

        testResultsSteps.validatePostErrorDataContains("trailerId", "is not allowed");
        testResultsSteps.validatePostErrorDataContains("numberOfSeats", "is not allowed");
        testResultsSteps.validatePostErrorDataContains("vehicleSize", "is not allowed");

    }

    @Title("CVSB-6805 - CVSB-7258 - API Consumer creates a new test results for the submitted test (HGV - additional fields - numberOfSeatbeltsFitted, lastSeatbeltInstallationCheckDate, seatbeltInstallationCheckDate)")
    @Test
    public void testTestResultsPostAdditionalFieldsSeatbeltsHgv() {

        vehicleSubmittedData
                .setVin(generateRandomExcludingValues(21, vehicleSubmittedData.build().getVin()))
                .setVrm(generateRandomExcludingValues(7, vehicleSubmittedData.build().getVrm()))
                .setTestResultId(generateRandomExcludingValues(3,vehicleSubmittedData.build().getTestResultId()))
                .setCountryOfRegistration("a")
                .setEuVehicleCategory("n2")
                .setNoOfAxles(2)
                .setReasonForCancellation(null)
                .setTestStationName("Abshire-Kub")
                .setTestStationPNumber("09-4129632")
                .setTestStationType("gvts")
                .setVehicleType("hgv")
                .setVehicleConfiguration("articulated")
                .setTestStatus("submitted")
                .build();

        vehicleSubmittedData.build().getTestTypes().get(0).setName("First test");
        vehicleSubmittedData.build().getTestTypes().get(0).setTestTypeName("First test");
        vehicleSubmittedData.build().getTestTypes().get(0).setTestTypeId("41");
        vehicleSubmittedData.build().getTestTypes().get(0).setCertificateNumber(null);
        vehicleSubmittedData.build().getTestTypes().get(0).setTestResult("fail");
        vehicleSubmittedData.build().getTestTypes().get(0).setProhibitionIssued(false);
        vehicleSubmittedData.build().getTestTypes().get(0).setReasonForAbandoning(null);
        vehicleSubmittedData.build().getTestTypes().get(0).setAdditionalCommentsForAbandon(null);
        vehicleSubmittedData.build().getTestTypes().get(0).setAdditionalNotesRecorded(null);

        vehicleSubmittedData.build().getVehicleClass().setCode("v").setDescription("heavy goods vehicle");
        vehicleSubmittedData.build().getTestTypes().get(0).getDefects().get(0)
                .setDeficiencyCategory("major")
                .setDeficiencyId("b")
                .setDeficiencyRef("3.1.b")
                .setDeficiencySubId(null)
                .setDeficiencyText("of an incorrect type.")
                .setImDescription("Seat Belts & Supplementary Restraint Systems")
                .setImNumber(3)
                .setItemDescription("Obligatory Seat Belt:")
                .setItemNumber(1)
                .setProhibitionIssued(true)
                .setPrs(false)
                .setStdForProhibition(false);

        vehicleSubmittedData.build().getTestTypes().get(0).getDefects().get(0).getAdditionalInformation()
                .setNotes("not working")
                .getLocation()
                .setVertical("lower")
                .setHorizontal(null)
                .setLateral("offside")
                .setLongitudinal(null)
                .setRowNumber(null)
                .setSeatNumber(null)
                .setAxleNumber(null);

        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode payload = objectMapper.valueToTree(vehicleSubmittedData.build());

        testResultsSteps.removeTestResultsFields(payload,  "vehicleId", "numberOfSeats", "vehicleSize");
        testResultsSteps.removeTestResultsTestTypesFields(payload, 0, "createdAt", "lastUpdatedAt", "testCode", "testNumber", "certificateLink", "testExpiryDate", "testAnniversaryDate");

        testResultsSteps.postTestResultsPayload(payload);
        testResultsSteps.statusCodeShouldBe(400);

        testResultsSteps.validatePostErrorDataContains("numberOfSeatbeltsFitted", "is not allowed");
        testResultsSteps.validatePostErrorDataContains("lastSeatbeltInstallationCheckDate", "is not allowed");
        testResultsSteps.validatePostErrorDataContains("seatbeltInstallationCheckDate", "is not allowed");

    }

    @Title("CVSB-6805 - CVSB-7258 - API Consumer creates a new test results for the submitted test (HGV - attributes defined incorrectly)")
    @Test
    public void testTestResultsPostIncorrectValuesVrmHgv() {

        vehicleSubmittedData
                .setVin(generateRandomExcludingValues(21, vehicleSubmittedData.build().getVin()))
                .setVrm(generateRandomExcludingValues(7, vehicleSubmittedData.build().getVrm()))
                .setTestResultId(generateRandomExcludingValues(3,vehicleSubmittedData.build().getTestResultId()))
                .setCountryOfRegistration("a")
                .setEuVehicleCategory("n2")
                .setNoOfAxles(2)
                .setReasonForCancellation(null)
                .setTestStationName("Abshire-Kub")
                .setTestStationPNumber("09-4129632")
                .setTestStationType("gvts")
                .setVehicleType("hgv")
                .setVehicleConfiguration("articulated")
                .setTestStatus("submitted")
                .build();

        vehicleSubmittedData.build().getTestTypes().get(0).setName("First test");
        vehicleSubmittedData.build().getTestTypes().get(0).setTestTypeName("First test");
        vehicleSubmittedData.build().getTestTypes().get(0).setTestTypeId("41");
        vehicleSubmittedData.build().getTestTypes().get(0).setCertificateNumber(null);
        vehicleSubmittedData.build().getTestTypes().get(0).setTestResult("fail");
        vehicleSubmittedData.build().getTestTypes().get(0).setProhibitionIssued(false);
        vehicleSubmittedData.build().getTestTypes().get(0).setReasonForAbandoning(null);
        vehicleSubmittedData.build().getTestTypes().get(0).setAdditionalCommentsForAbandon(null);
        vehicleSubmittedData.build().getTestTypes().get(0).setAdditionalNotesRecorded(null);

        vehicleSubmittedData.build().getVehicleClass().setCode("v").setDescription("heavy goods vehicle");
        vehicleSubmittedData.build().getTestTypes().get(0).getDefects().get(0)
                .setDeficiencyCategory("major")
                .setDeficiencyId("b")
                .setDeficiencyRef("3.1.b")
                .setDeficiencySubId(null)
                .setDeficiencyText("of an incorrect type.")
                .setImDescription("Seat Belts & Supplementary Restraint Systems")
                .setImNumber(3)
                .setItemDescription("Obligatory Seat Belt:")
                .setItemNumber(1)
                .setProhibitionIssued(true)
                .setPrs(false)
                .setStdForProhibition(false);

        vehicleSubmittedData.build().getTestTypes().get(0).getDefects().get(0).getAdditionalInformation()
                .setNotes("not working")
                .getLocation()
                .setVertical("lower")
                .setHorizontal(null)
                .setLateral("offside")
                .setLongitudinal(null)
                .setRowNumber(null)
                .setSeatNumber(null)
                .setAxleNumber(null);

        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode payload = objectMapper.valueToTree(vehicleSubmittedData.build());

        testResultsSteps.removeTestResultsFields(payload,  "vehicleId", "numberOfSeats", "vehicleSize");
        testResultsSteps.removeTestResultsTestTypesFields(payload, 0, "createdAt", "lastUpdatedAt", "testCode", "testNumber", "certificateLink", "testExpiryDate", "testAnniversaryDate");
        testResultsSteps.changeTestResultsFieldValue(payload, "vrm", 15);
        testResultsSteps.postTestResultsPayload(payload);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorDataContains("vrm", "must be a string");

    }

    @Title("CVSB-6805 - CVSB-7258 - API Consumer creates a new test results for the submitted test (HGV - attributes defined incorrectly)")
    @Test
    public void testTestResultsPostIncorrectValuesOdometerUnitsHgv() {

        vehicleSubmittedData
                .setVin(generateRandomExcludingValues(21, vehicleSubmittedData.build().getVin()))
                .setVrm(generateRandomExcludingValues(7, vehicleSubmittedData.build().getVrm()))
                .setTestResultId(generateRandomExcludingValues(3,vehicleSubmittedData.build().getTestResultId()))
                .setCountryOfRegistration("a")
                .setEuVehicleCategory("n2")
                .setNoOfAxles(2)
                .setReasonForCancellation(null)
                .setTestStationName("Abshire-Kub")
                .setTestStationPNumber("09-4129632")
                .setTestStationType("gvts")
                .setVehicleType("hgv")
                .setVehicleConfiguration("articulated")
                .setTestStatus("submitted")
                .build();

        vehicleSubmittedData.build().getTestTypes().get(0).setName("First test");
        vehicleSubmittedData.build().getTestTypes().get(0).setTestTypeName("First test");
        vehicleSubmittedData.build().getTestTypes().get(0).setTestTypeId("41");
        vehicleSubmittedData.build().getTestTypes().get(0).setCertificateNumber(null);
        vehicleSubmittedData.build().getTestTypes().get(0).setTestResult("fail");
        vehicleSubmittedData.build().getTestTypes().get(0).setProhibitionIssued(false);
        vehicleSubmittedData.build().getTestTypes().get(0).setReasonForAbandoning(null);
        vehicleSubmittedData.build().getTestTypes().get(0).setAdditionalCommentsForAbandon(null);
        vehicleSubmittedData.build().getTestTypes().get(0).setAdditionalNotesRecorded(null);

        vehicleSubmittedData.build().getVehicleClass().setCode("v").setDescription("heavy goods vehicle");
        vehicleSubmittedData.build().getTestTypes().get(0).getDefects().get(0)
                .setDeficiencyCategory("major")
                .setDeficiencyId("b")
                .setDeficiencyRef("3.1.b")
                .setDeficiencySubId(null)
                .setDeficiencyText("of an incorrect type.")
                .setImDescription("Seat Belts & Supplementary Restraint Systems")
                .setImNumber(3)
                .setItemDescription("Obligatory Seat Belt:")
                .setItemNumber(1)
                .setProhibitionIssued(true)
                .setPrs(false)
                .setStdForProhibition(false);

        vehicleSubmittedData.build().getTestTypes().get(0).getDefects().get(0).getAdditionalInformation()
                .setNotes("not working")
                .getLocation()
                .setVertical("lower")
                .setHorizontal(null)
                .setLateral("offside")
                .setLongitudinal(null)
                .setRowNumber(null)
                .setSeatNumber(null)
                .setAxleNumber(null);

        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode payload = objectMapper.valueToTree(vehicleSubmittedData.build());

        testResultsSteps.removeTestResultsFields(payload,  "vehicleId", "numberOfSeats", "vehicleSize");
        testResultsSteps.removeTestResultsTestTypesFields(payload, 0, "createdAt", "lastUpdatedAt", "testCode", "testNumber", "certificateLink", "testExpiryDate", "testAnniversaryDate");
        testResultsSteps.changeTestResultsFieldValue(payload, "odometerReadingUnits", "litre");
        testResultsSteps.postTestResultsPayload(payload);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorDataContains("odometerReadingUnits", "must be one of [kilometres, miles, null]");

    }

    @Title("CVSB-6805 - CVSB-7258 - API Consumer creates a new test results for the submitted test (HGV - attributes defined incorrectly)")
    @Test
    public void testTestResultsPostIncorrectValuesOdometerValueTypeHgv() {

        vehicleSubmittedData
                .setVin(generateRandomExcludingValues(21, vehicleSubmittedData.build().getVin()))
                .setVrm(generateRandomExcludingValues(7, vehicleSubmittedData.build().getVrm()))
                .setTestResultId(generateRandomExcludingValues(3,vehicleSubmittedData.build().getTestResultId()))
                .setCountryOfRegistration("a")
                .setEuVehicleCategory("n2")
                .setNoOfAxles(2)
                .setReasonForCancellation(null)
                .setTestStationName("Abshire-Kub")
                .setTestStationPNumber("09-4129632")
                .setTestStationType("gvts")
                .setVehicleType("hgv")
                .setVehicleConfiguration("articulated")
                .setTestStatus("submitted")
                .build();

        vehicleSubmittedData.build().getTestTypes().get(0).setName("First test");
        vehicleSubmittedData.build().getTestTypes().get(0).setTestTypeName("First test");
        vehicleSubmittedData.build().getTestTypes().get(0).setTestTypeId("41");
        vehicleSubmittedData.build().getTestTypes().get(0).setCertificateNumber(null);
        vehicleSubmittedData.build().getTestTypes().get(0).setTestResult("fail");
        vehicleSubmittedData.build().getTestTypes().get(0).setProhibitionIssued(false);
        vehicleSubmittedData.build().getTestTypes().get(0).setReasonForAbandoning(null);
        vehicleSubmittedData.build().getTestTypes().get(0).setAdditionalCommentsForAbandon(null);
        vehicleSubmittedData.build().getTestTypes().get(0).setAdditionalNotesRecorded(null);

        vehicleSubmittedData.build().getVehicleClass().setCode("v").setDescription("heavy goods vehicle");
        vehicleSubmittedData.build().getTestTypes().get(0).getDefects().get(0)
                .setDeficiencyCategory("major")
                .setDeficiencyId("b")
                .setDeficiencyRef("3.1.b")
                .setDeficiencySubId(null)
                .setDeficiencyText("of an incorrect type.")
                .setImDescription("Seat Belts & Supplementary Restraint Systems")
                .setImNumber(3)
                .setItemDescription("Obligatory Seat Belt:")
                .setItemNumber(1)
                .setProhibitionIssued(true)
                .setPrs(false)
                .setStdForProhibition(false);

        vehicleSubmittedData.build().getTestTypes().get(0).getDefects().get(0).getAdditionalInformation()
                .setNotes("not working")
                .getLocation()
                .setVertical("lower")
                .setHorizontal(null)
                .setLateral("offside")
                .setLongitudinal(null)
                .setRowNumber(null)
                .setSeatNumber(null)
                .setAxleNumber(null);

        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode payload = objectMapper.valueToTree(vehicleSubmittedData.build());

        testResultsSteps.removeTestResultsFields(payload,  "vehicleId", "numberOfSeats", "vehicleSize");
        testResultsSteps.removeTestResultsTestTypesFields(payload, 0, "createdAt", "lastUpdatedAt", "testCode", "testNumber", "certificateLink", "testExpiryDate", "testAnniversaryDate");
        testResultsSteps.changeTestResultsFieldValue(payload, "odometerReading", "one thousand");
        testResultsSteps.postTestResultsPayload(payload);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorDataContains("odometerReading", "must be a number");

    }

    @Title("CVSB-6805 CVSB-7329 - API Consumer creates a new test results for the submitted test (TRL - attributes defined incorrectly)")
    @Test
    public void testTestResultsPostIncorrectValuesOdometerValueTypeTrl() {

        vehicleSubmittedData
                .setVin(generateRandomExcludingValues(21, vehicleSubmittedData.build().getVin()))
                .setTestResultId(generateRandomExcludingValues(3,vehicleSubmittedData.build().getTestResultId()))
                .setCountryOfRegistration("a")
                .setEuVehicleCategory("o3")
                .setNoOfAxles(6)
                .setReasonForCancellation(null)
                .setTestEndTimestamp("2021-01-16T16:42:14.757Z")
                .setTesterEmailAddress("cvs.automation3@dvsagov.onmicrosoft.com")
                .setTesterName("cvs.automation3@dvsagov.onmicrosoft.com")
                .setTestStationName("Abshire-Kub")
                .setTestStationPNumber("09-4129632")
                .setTestStationType("gvts")
                .setVehicleType("trl")
                .setVehicleConfiguration("articulated")
                .setTestStatus("submitted").build()
                .getTestTypes().get(0).setAdditionalCommentsForAbandon(null);

        vehicleSubmittedData.build().getTestTypes().get(0).setAdditionalNotesRecorded("a note");
        vehicleSubmittedData.build().getTestTypes().get(0).setCertificateNumber(null);
        vehicleSubmittedData.build().getTestTypes().get(0).setName("Retest");
        vehicleSubmittedData.build().getTestTypes().get(0).setProhibitionIssued(false);
        vehicleSubmittedData.build().getTestTypes().get(0).setReasonForAbandoning(null);
        vehicleSubmittedData.build().getTestTypes().get(0).setTestResult("fail");
        vehicleSubmittedData.build().getTestTypes().get(0).setTestTypeId("");
        vehicleSubmittedData.build().getTestTypes().get(0).setTestTypeName("Paid roadworthiness retest");
        vehicleSubmittedData.build().getVehicleClass().setCode("t").setDescription("trailer");

        vehicleSubmittedData.build().getTestTypes().get(0).getDefects().get(0)
                .setDeficiencyCategory("dangerous")
                .setDeficiencyId("a")
                .setDeficiencyRef("6.2.a.ii")
                .setDeficiencySubId("ii")
                .setDeficiencyText("with any visible elongation of a stud hole where secure fixing of a wheel is affected.")
                .setImDescription("Road Wheels and Hubs")
                .setImNumber(6)
                .setItemDescription("A wheel:")
                .setItemNumber(2)
                .setProhibitionIssued(true)
                .setPrs(false)
                .setStdForProhibition(false);

        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode payload = objectMapper.valueToTree(vehicleSubmittedData.build());

        testResultsSteps.addAdditionalTestResultsFieldValue(payload, "trailerId", "C234567");
        testResultsSteps.removeTestResultsFields(payload, "vehicleId");
        testResultsSteps.removeTestResultsTestTypesFields(payload, 0, "certificateLink", "createdAt", "lastUpdatedAt", "numberOfSeatbeltsFitted", "testCode", "testNumber", "testExpiryDate", "testAnniversaryDate", "lastSeatbeltInstallationCheckDate", "seatbeltInstallationCheckDate");
        testResultsSteps.changeTestResultsFieldValue(payload, "trailerId", 55498783);
        testResultsSteps.postTestResultsPayload(payload);

        testResultsSteps.statusCodeShouldBe(400);

    }

    @Title("CVSB-6805 - CVSB-7331 - API Consumer creates a new test results for the submitted test (PSV - attributes defined incorrectly)")
    @Test
    public void testTestResultsPostIncorrectValuesVrmPsv() {

        vehicleSubmittedData
                .setVin(generateRandomExcludingValues(21, vehicleSubmittedData.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(16, vehicleSubmittedData.build().getVin()))
                .setVrm(generateRandomExcludingValues(7, vehicleSubmittedData.build().getVrm()))
                .build();

        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode payload = objectMapper.valueToTree(vehicleSubmittedData.build());

        testResultsSteps.removeTestResultsFields(payload, "vehicleId");
        testResultsSteps.removeTestResultsTestTypesFields(payload, 0, "createdAt", "lastUpdatedAt", "testCode", "testNumber", "certificateLink", "testExpiryDate", "testAnniversaryDate");
        testResultsSteps.changeTestResultsFieldValue(payload, "vrm", 15);
        testResultsSteps.changeTestResultsFieldValue(payload, "vehicleType", "psv");
        testResultsSteps.postTestResultsPayload(payload);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorDataContains("vrm", "must be a string");
    }

    @Title("CVSB-6805 - CVSB-7331 - API Consumer creates a new test results for the submitted test (PSV - attributes defined incorrectly)")
    @Test
    public void testTestResultsPostIncorrectValuesNoSeatsPsv() {

        vehicleSubmittedData
                .setVin(generateRandomExcludingValues(21, vehicleSubmittedData.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(16, vehicleSubmittedData.build().getVin()))
                .setVrm(generateRandomExcludingValues(7, vehicleSubmittedData.build().getVrm()))
                .build();

        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode payload = objectMapper.valueToTree(vehicleSubmittedData.build());

        testResultsSteps.removeTestResultsFields(payload, "vehicleId");
        testResultsSteps.removeTestResultsTestTypesFields(payload, 0, "createdAt", "lastUpdatedAt", "testCode", "testNumber", "certificateLink", "testExpiryDate", "testAnniversaryDate");
        testResultsSteps.changeTestResultsFieldValue(payload, "numberOfSeats", "seven");
        testResultsSteps.changeTestResultsFieldValue(payload, "vehicleType", "psv");
        testResultsSteps.postTestResultsPayload(payload);
        testResultsSteps.statusCodeShouldBe(400);
    }

    @Title("CVSB-6805 - CVSB-7331 - API Consumer creates a new test results for the submitted test (PSV - attributes defined incorrectly)")
    @Test
    public void testTestResultsPostIncorrectValuesOdometerValueTypePsv() {

        vehicleSubmittedData
                .setVin(generateRandomExcludingValues(21, vehicleSubmittedData.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(16, vehicleSubmittedData.build().getVin()))
                .setVrm(generateRandomExcludingValues(7, vehicleSubmittedData.build().getVrm()))
                .build();

        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode payload = objectMapper.valueToTree(vehicleSubmittedData.build());

        testResultsSteps.removeTestResultsFields(payload, "vehicleId");
        testResultsSteps.removeTestResultsTestTypesFields(payload, 0, "createdAt", "lastUpdatedAt", "testCode", "testNumber", "certificateLink", "testExpiryDate", "testAnniversaryDate");
        testResultsSteps.changeTestResultsFieldValue(payload, "odometerReading", "seven");
        testResultsSteps.changeTestResultsFieldValue(payload, "vehicleType", "psv");
        testResultsSteps.postTestResultsPayload(payload);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorDataContains("odometerReading", "must be a number");
    }

    @Title("CVSB-6805 - CVSB-7331 - API Consumer creates a new test results for the submitted test (PSV - attributes defined incorrectly)")
    @Test
    public void testTestResultsPostIncorrectValuesOdometerUnitsPsv() {

        vehicleSubmittedData
                .setVin(generateRandomExcludingValues(21, vehicleSubmittedData.build().getVin()))
                .setVrm(generateRandomExcludingValues(7, vehicleSubmittedData.build().getVrm()))
                .build();

        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode payload = objectMapper.valueToTree(vehicleSubmittedData.build());

        testResultsSteps.removeTestResultsFields(payload, "vehicleId");
        testResultsSteps.removeTestResultsTestTypesFields(payload, 0, "createdAt", "lastUpdatedAt", "testCode", "testNumber", "certificateLink", "testExpiryDate", "testAnniversaryDate");
        testResultsSteps.changeTestResultsFieldValue(payload, "odometerReadingUnits", "seven");
        testResultsSteps.changeTestResultsFieldValue(payload, "vehicleType", "psv");
        testResultsSteps.postTestResultsPayload(payload);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorDataContains("odometerReadingUnits", "must be one of [kilometres, miles, null]");
    }

    @Title("CVSB-6805 - CVSB-7331 - API Consumer creates a new test results for the submitted test (PSV - attributes defined incorrectly)")
    @Test
    public void testTestResultsPostIncorrectValuesVehicleSizePsv() {

        vehicleSubmittedData
                .setVin(generateRandomExcludingValues(21, vehicleSubmittedData.build().getVin()))
                .setVrm(generateRandomExcludingValues(7, vehicleSubmittedData.build().getVrm()))
                .build();

        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode payload = objectMapper.valueToTree(vehicleSubmittedData.build());

        testResultsSteps.removeTestResultsFields(payload, "vehicleId");
        testResultsSteps.removeTestResultsTestTypesFields(payload, 0, "createdAt", "lastUpdatedAt", "testCode", "testNumber", "certificateLink", "testExpiryDate", "testAnniversaryDate");
        testResultsSteps.changeTestResultsFieldValue(payload, "vehicleType", "psv");
        testResultsSteps.changeTestResultsFieldValue(payload, "vehicleSize", "tiny");
        testResultsSteps.postTestResultsPayload(payload);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorDataContains("vehicleSize", "must be one of [small, large]");
    }

    @Title("CVSB-6805 - CVSB-7331 - API Consumer creates a new test results for the submitted test (PSV - attributes defined incorrectly)")
    @Test
    public void testTestResultsPostIncorrectValuesNoSeatbeltsTypePsv() {

        vehicleSubmittedData
                .setVin(generateRandomExcludingValues(21, vehicleSubmittedData.build().getVin()))
                .setVrm(generateRandomExcludingValues(7, vehicleSubmittedData.build().getVrm()))
                .build();

        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode payload = objectMapper.valueToTree(vehicleSubmittedData.build());

        testResultsSteps.removeTestResultsFields(payload, "vehicleId");
        testResultsSteps.removeTestResultsTestTypesFields(payload, 0, "createdAt", "lastUpdatedAt", "testCode", "testNumber", "certificateLink", "testExpiryDate", "testAnniversaryDate");
        testResultsSteps.changeTestResultsTestTypesFields(payload, 0,"numberOfSeatbeltsFitted", "seven");
        testResultsSteps.changeTestResultsFieldValue(payload, "vehicleType", "psv");
        testResultsSteps.postTestResultsPayload(payload);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorDataContains("numberOfSeatbeltsFitted", "must be a number");
    }

    @Title("CVSB-6805 - CVSB-7331 - API Consumer creates a new test results for the submitted test (PSV - attributes defined incorrectly)")
    @Test
    public void testTestResultsPostIncorrectValuesLastSeatbeltsInstallDateTypePsv() {

        vehicleSubmittedData
                .setVin(generateRandomExcludingValues(21, vehicleSubmittedData.build().getVin()))
                .setVrm(generateRandomExcludingValues(7, vehicleSubmittedData.build().getVrm()))
                .build();

        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode payload = objectMapper.valueToTree(vehicleSubmittedData.build());

        testResultsSteps.removeTestResultsFields(payload, "vehicleId");
        testResultsSteps.removeTestResultsTestTypesFields(payload, 0, "createdAt", "lastUpdatedAt", "testCode", "testNumber", "certificateLink", "testExpiryDate", "testAnniversaryDate");
        testResultsSteps.changeTestResultsTestTypesFields(payload, 0,"lastSeatbeltInstallationCheckDate", "right now");
        testResultsSteps.changeTestResultsFieldValue(payload, "vehicleType", "psv");
        testResultsSteps.postTestResultsPayload(payload);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorDataContains("lastSeatbeltInstallationCheckDate", "must be a number of milliseconds or valid date string");
    }

    @Title("CVSB-6805 - CVSB-7331 - API Consumer creates a new test results for the submitted test (PSV - attributes defined incorrectly)")
    @Test
    public void testTestResultsPostIncorrectValuesSeatbeltsInstallDateTypePsv() {

        vehicleSubmittedData
                .setVin(generateRandomExcludingValues(21, vehicleSubmittedData.build().getVin()))
                .setVrm(generateRandomExcludingValues(7, vehicleSubmittedData.build().getVrm()))
                .build();

        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode payload = objectMapper.valueToTree(vehicleSubmittedData.build());

        testResultsSteps.removeTestResultsFields(payload, "vehicleId");
        testResultsSteps.removeTestResultsTestTypesFields(payload, 0, "createdAt", "lastUpdatedAt", "testCode", "testNumber", "certificateLink", "testExpiryDate", "testAnniversaryDate");
        testResultsSteps.changeTestResultsTestTypesFields(payload, 0,"seatbeltInstallationCheckDate", "never");
        testResultsSteps.changeTestResultsFieldValue(payload, "vehicleType", "psv");
        testResultsSteps.postTestResultsPayload(payload);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorDataContains("seatbeltInstallationCheckDate", "must be a boolean");
    }

    @Title("CVSB-8703 - Iteration on test results API specs to cover the logic of First test expiry date generation for HGV/TRL certificates (TRL)")
    @Test
    public void testTestResultsFirstUseDateTrl() {

        vehicleSubmittedData
                .setVin(generateRandomExcludingValues(21, vehicleSubmittedData.build().getVin()))
                .setTestResultId(generateRandomExcludingValues(3,vehicleSubmittedData.build().getTestResultId()))
                .setSystemNumber(generateRandomExcludingValues(16, vehicleSubmittedData.build().getVrm()))
                .setCountryOfRegistration("a")
                .setEuVehicleCategory("o3")
                .setNoOfAxles(6)
                .setReasonForCancellation(null)
                .setTestEndTimestamp("2021-01-16T16:42:14.757Z")
                .setTesterEmailAddress("cvs.automation3@dvsagov.onmicrosoft.com")
                .setTesterName("cvs.automation3@dvsagov.onmicrosoft.com")
                .setTestStationName("Abshire-Kub")
                .setTestStationPNumber("09-4129632")
                .setTestStationType("gvts")
                .setVehicleType("trl")
                .setVehicleConfiguration("articulated")
                .setTestStatus("submitted").build()
                .getTestTypes().get(0).setAdditionalCommentsForAbandon(null);

        vehicleSubmittedData.build().getTestTypes().get(0).setAdditionalNotesRecorded("a note");
        vehicleSubmittedData.build().getTestTypes().get(0).setCertificateNumber(null);
        vehicleSubmittedData.build().getTestTypes().get(0).setName("Retest");
        vehicleSubmittedData.build().getTestTypes().get(0).setProhibitionIssued(false);
        vehicleSubmittedData.build().getTestTypes().get(0).setReasonForAbandoning(null);
        vehicleSubmittedData.build().getTestTypes().get(0).setTestResult("fail");
        vehicleSubmittedData.build().getTestTypes().get(0).setTestTypeId("");
        vehicleSubmittedData.build().getTestTypes().get(0).setTestTypeName("Paid roadworthiness retest");
        vehicleSubmittedData.build().getVehicleClass().setCode("t").setDescription("trailer");

        vehicleSubmittedData.build().getTestTypes().get(0).getDefects().get(0)
                .setDeficiencyCategory("dangerous")
                .setDeficiencyId("a")
                .setDeficiencyRef("6.2.a.ii")
                .setDeficiencySubId("ii")
                .setDeficiencyText("with any visible elongation of a stud hole where secure fixing of a wheel is affected.")
                .setImDescription("Road Wheels and Hubs")
                .setImNumber(6)
                .setItemDescription("A wheel:")
                .setItemNumber(2)
                .setProhibitionIssued(true)
                .setPrs(false)
                .setStdForProhibition(false);

        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode payload = objectMapper.valueToTree(vehicleSubmittedData.build());
        String firstUseDate = DataUtil.buildDate(DataUtil.buildCurrentDateTime(),0);

        testResultsSteps.addAdditionalTestResultsFieldValue(payload, "trailerId", "C234567");
        testResultsSteps.addAdditionalTestResultsFieldValue(payload, "firstUseDate", firstUseDate);
        testResultsSteps.removeTestResultsFields(payload, "numberOfSeats", "odometerReading", "odometerReadingUnits", "vehicleId", "vehicleSize", "vrm");
        testResultsSteps.removeTestResultsTestTypesFields(payload, 0, "certificateLink", "createdAt", "lastSeatbeltInstallationCheckDate", "lastUpdatedAt", "numberOfSeatbeltsFitted", "seatbeltInstallationCheckDate", "testCode", "testNumber", "testExpiryDate", "testAnniversaryDate");

        testResultsSteps.postTestResultsPayload(payload);

        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");

        testResultsSteps.getTestResults(vehicleSubmittedData.build().getSystemNumber());
        testResultsSteps.statusCodeShouldBe(200);
        testResultsSteps.validateVehicleFieldValue("firstUseDate", firstUseDate);

    }

    @Title("CVSB-8703 - Iteration on test results API specs to cover the logic of First test expiry date generation for HGV/TRL certificates (HGV)")
    @Test
    public void testTestResultsRegnDateHgv() {

        vehicleSubmittedData
                .setVin(generateRandomExcludingValues(21, vehicleSubmittedData.build().getVin()))
                .setVrm(generateRandomExcludingValues(7, vehicleSubmittedData.build().getVrm()))
                .setSystemNumber(generateRandomExcludingValues(16, vehicleSubmittedData.build().getVrm()))
                .setTestResultId(generateRandomExcludingValues(3,vehicleSubmittedData.build().getTestResultId()))
                .setCountryOfRegistration("a")
                .setEuVehicleCategory("n2")
                .setNoOfAxles(2)
                .setReasonForCancellation(null)
                .setTestStationName("Abshire-Kub")
                .setTestStationPNumber("09-4129632")
                .setTestStationType("gvts")
                .setVehicleType("hgv")
                .setVehicleConfiguration("articulated")
                .setTestStatus("submitted")
                .build();

        vehicleSubmittedData.build().getTestTypes().get(0).setName("First test");
        vehicleSubmittedData.build().getTestTypes().get(0).setTestTypeName("First test");
        vehicleSubmittedData.build().getTestTypes().get(0).setTestTypeId("95");
        vehicleSubmittedData.build().getTestTypes().get(0).setCertificateNumber(null);
        vehicleSubmittedData.build().getTestTypes().get(0).setTestResult("fail");
        vehicleSubmittedData.build().getTestTypes().get(0).setProhibitionIssued(false);
        vehicleSubmittedData.build().getTestTypes().get(0).setReasonForAbandoning(null);
        vehicleSubmittedData.build().getTestTypes().get(0).setAdditionalCommentsForAbandon(null);
        vehicleSubmittedData.build().getTestTypes().get(0).setAdditionalNotesRecorded(null);

        vehicleSubmittedData.build().getVehicleClass().setCode("v").setDescription("heavy goods vehicle");
        vehicleSubmittedData.build().getTestTypes().get(0).getDefects().get(0)
                .setDeficiencyCategory("major")
                .setDeficiencyId("b")
                .setDeficiencyRef("3.1.b")
                .setDeficiencySubId(null)
                .setDeficiencyText("of an incorrect type.")
                .setImDescription("Seat Belts & Supplementary Restraint Systems")
                .setImNumber(3)
                .setItemDescription("Obligatory Seat Belt:")
                .setItemNumber(1)
                .setProhibitionIssued(true)
                .setPrs(false)
                .setStdForProhibition(false);

        vehicleSubmittedData.build().getTestTypes().get(0).getDefects().get(0).getAdditionalInformation()
                .setNotes("not working")
                .getLocation()
                .setVertical("lower")
                .setHorizontal(null)
                .setLateral("offside")
                .setLongitudinal(null)
                .setRowNumber(null)
                .setSeatNumber(null)
                .setAxleNumber(null);

        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode payload = objectMapper.valueToTree(vehicleSubmittedData.build());
        String regnDate = DataUtil.buildDate(DataUtil.buildCurrentDateTime(),0);

        testResultsSteps.addAdditionalTestResultsFieldValue(payload, "regnDate", regnDate);
        testResultsSteps.removeTestResultsFields(payload, "vehicleSize", "numberOfSeats", "vehicleId");
        testResultsSteps.removeTestResultsTestTypesFields(payload, 0, "numberOfSeatbeltsFitted", "lastSeatbeltInstallationCheckDate", "seatbeltInstallationCheckDate", "createdAt", "lastUpdatedAt", "testCode", "testNumber", "certificateLink", "testExpiryDate", "testAnniversaryDate");

        testResultsSteps.postTestResultsPayload(payload);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");

        testResultsSteps.getTestResults(vehicleSubmittedData.build().getSystemNumber());
        testResultsSteps.statusCodeShouldBe(200);

        testResultsSteps.validateVehicleFieldValue("regnDate", regnDate);

    }

    @Title("CVSB-8703 - Iteration on test results API specs to cover the logic of First test expiry date generation for HGV/TRL certificates (TRL - null)")
    @Test
    public void testTestResultsFirstUseDateNullTrl() {

        vehicleSubmittedData
                .setVin(generateRandomExcludingValues(21, vehicleSubmittedData.build().getVin()))
                .setTestResultId(generateRandomExcludingValues(3,vehicleSubmittedData.build().getTestResultId()))
                .setSystemNumber(generateRandomExcludingValues(16, vehicleSubmittedData.build().getVrm()))
                .setCountryOfRegistration("a")
                .setEuVehicleCategory("o3")
                .setNoOfAxles(6)
                .setReasonForCancellation(null)
                .setTestEndTimestamp("2021-01-16T16:42:14.757Z")
                .setTesterEmailAddress("cvs.automation3@dvsagov.onmicrosoft.com")
                .setTesterName("cvs.automation3@dvsagov.onmicrosoft.com")
                .setTestStationName("Abshire-Kub")
                .setTestStationPNumber("09-4129632")
                .setTestStationType("gvts")
                .setVehicleType("trl")
                .setVehicleConfiguration("articulated")
                .setTestStatus("submitted").build()
                .getTestTypes().get(0).setAdditionalCommentsForAbandon(null);

        vehicleSubmittedData.build().getTestTypes().get(0).setAdditionalNotesRecorded("a note");
        vehicleSubmittedData.build().getTestTypes().get(0).setCertificateNumber(null);
        vehicleSubmittedData.build().getTestTypes().get(0).setName("Retest");
        vehicleSubmittedData.build().getTestTypes().get(0).setProhibitionIssued(false);
        vehicleSubmittedData.build().getTestTypes().get(0).setReasonForAbandoning(null);
        vehicleSubmittedData.build().getTestTypes().get(0).setTestResult("fail");
        vehicleSubmittedData.build().getTestTypes().get(0).setTestTypeId("");
        vehicleSubmittedData.build().getTestTypes().get(0).setTestTypeName("Paid roadworthiness retest");
        vehicleSubmittedData.build().getVehicleClass().setCode("t").setDescription("trailer");

        vehicleSubmittedData.build().getTestTypes().get(0).getDefects().get(0)
                .setDeficiencyCategory("dangerous")
                .setDeficiencyId("a")
                .setDeficiencyRef("6.2.a.ii")
                .setDeficiencySubId("ii")
                .setDeficiencyText("with any visible elongation of a stud hole where secure fixing of a wheel is affected.")
                .setImDescription("Road Wheels and Hubs")
                .setImNumber(6)
                .setItemDescription("A wheel:")
                .setItemNumber(2)
                .setProhibitionIssued(true)
                .setPrs(false)
                .setStdForProhibition(false);

        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode payload = objectMapper.valueToTree(vehicleSubmittedData.build());

        testResultsSteps.addAdditionalTestResultsFieldValue(payload, "trailerId", "C234567");
        testResultsSteps.addAdditionalTestResultsFieldValue(payload, "firstUseDate", null);
        testResultsSteps.removeTestResultsFields(payload, "numberOfSeats", "odometerReading", "odometerReadingUnits", "vehicleId", "vehicleSize", "vrm");
        testResultsSteps.removeTestResultsTestTypesFields(payload, 0, "certificateLink", "createdAt", "lastSeatbeltInstallationCheckDate", "lastUpdatedAt", "numberOfSeatbeltsFitted", "seatbeltInstallationCheckDate", "testCode", "testNumber", "testExpiryDate", "testAnniversaryDate");

        testResultsSteps.postTestResultsPayload(payload);

        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");

        testResultsSteps.getTestResults(vehicleSubmittedData.build().getSystemNumber());
        testResultsSteps.statusCodeShouldBe(200);
        testResultsSteps.validateVehicleFieldValue("firstUseDate", null);

    }

    @Title("CVSB-8703 - Iteration on test results API specs to cover the logic of First test expiry date generation for HGV/TRL certificates (HGV - null)")
    @Test
    public void testTestResultsRegnDateNullHgv() {

        vehicleSubmittedData
                .setVin(generateRandomExcludingValues(21, vehicleSubmittedData.build().getVin()))
                .setVrm(generateRandomExcludingValues(7, vehicleSubmittedData.build().getVrm()))
                .setSystemNumber(generateRandomExcludingValues(16, vehicleSubmittedData.build().getVrm()))
                .setTestResultId(generateRandomExcludingValues(3,vehicleSubmittedData.build().getTestResultId()))
                .setCountryOfRegistration("a")
                .setEuVehicleCategory("n2")
                .setNoOfAxles(2)
                .setReasonForCancellation(null)
                .setTestStationName("Abshire-Kub")
                .setTestStationPNumber("09-4129632")
                .setTestStationType("gvts")
                .setVehicleType("hgv")
                .setVehicleConfiguration("articulated")
                .setTestStatus("submitted")
                .build();

        vehicleSubmittedData.build().getTestTypes().get(0).setName("First test");
        vehicleSubmittedData.build().getTestTypes().get(0).setTestTypeName("First test");
        vehicleSubmittedData.build().getTestTypes().get(0).setTestTypeId("95");
        vehicleSubmittedData.build().getTestTypes().get(0).setCertificateNumber(null);
        vehicleSubmittedData.build().getTestTypes().get(0).setTestResult("fail");
        vehicleSubmittedData.build().getTestTypes().get(0).setProhibitionIssued(false);
        vehicleSubmittedData.build().getTestTypes().get(0).setReasonForAbandoning(null);
        vehicleSubmittedData.build().getTestTypes().get(0).setAdditionalCommentsForAbandon(null);
        vehicleSubmittedData.build().getTestTypes().get(0).setAdditionalNotesRecorded(null);

        vehicleSubmittedData.build().getVehicleClass().setCode("v").setDescription("heavy goods vehicle");
        vehicleSubmittedData.build().getTestTypes().get(0).getDefects().get(0)
                .setDeficiencyCategory("major")
                .setDeficiencyId("b")
                .setDeficiencyRef("3.1.b")
                .setDeficiencySubId(null)
                .setDeficiencyText("of an incorrect type.")
                .setImDescription("Seat Belts & Supplementary Restraint Systems")
                .setImNumber(3)
                .setItemDescription("Obligatory Seat Belt:")
                .setItemNumber(1)
                .setProhibitionIssued(true)
                .setPrs(false)
                .setStdForProhibition(false);

        vehicleSubmittedData.build().getTestTypes().get(0).getDefects().get(0).getAdditionalInformation()
                .setNotes("not working")
                .getLocation()
                .setVertical("lower")
                .setHorizontal(null)
                .setLateral("offside")
                .setLongitudinal(null)
                .setRowNumber(null)
                .setSeatNumber(null)
                .setAxleNumber(null);

        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode payload = objectMapper.valueToTree(vehicleSubmittedData.build());

        testResultsSteps.addAdditionalTestResultsFieldValue(payload, "regnDate", null);
        testResultsSteps.removeTestResultsFields(payload, "vehicleSize", "numberOfSeats", "vehicleId");
        testResultsSteps.removeTestResultsTestTypesFields(payload, 0, "numberOfSeatbeltsFitted", "lastSeatbeltInstallationCheckDate", "seatbeltInstallationCheckDate", "createdAt", "lastUpdatedAt", "testCode", "testNumber", "certificateLink", "testExpiryDate", "testAnniversaryDate");

        testResultsSteps.postTestResultsPayload(payload);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");

        testResultsSteps.getTestResults(vehicleSubmittedData.build().getSystemNumber());
        testResultsSteps.statusCodeShouldBe(200);

        testResultsSteps.validateVehicleFieldValue("regnDate", null);

    }

    @Title("CVSB-8703 - Iteration on test results API specs to cover the logic of First test expiry date generation for HGV/TRL certificates (TRL - wrong type)")
    @Test
    public void testTestResultsFirstUseDateWrongTrl() {

        vehicleSubmittedData
                .setVin(generateRandomExcludingValues(21, vehicleSubmittedData.build().getVin()))
                .setTestResultId(generateRandomExcludingValues(3,vehicleSubmittedData.build().getTestResultId()))
                .setCountryOfRegistration("a")
                .setEuVehicleCategory("o3")
                .setNoOfAxles(6)
                .setReasonForCancellation(null)
                .setTestEndTimestamp("2021-01-16T16:42:14.757Z")
                .setTesterEmailAddress("cvs.automation3@dvsagov.onmicrosoft.com")
                .setTesterName("cvs.automation3@dvsagov.onmicrosoft.com")
                .setTestStationName("Abshire-Kub")
                .setTestStationPNumber("09-4129632")
                .setTestStationType("gvts")
                .setVehicleType("trl")
                .setVehicleConfiguration("articulated")
                .setTestStatus("submitted").build()
                .getTestTypes().get(0).setAdditionalCommentsForAbandon(null);

        vehicleSubmittedData.build().getTestTypes().get(0).setAdditionalNotesRecorded("a note");
        vehicleSubmittedData.build().getTestTypes().get(0).setCertificateNumber(null);
        vehicleSubmittedData.build().getTestTypes().get(0).setName("Retest");
        vehicleSubmittedData.build().getTestTypes().get(0).setProhibitionIssued(false);
        vehicleSubmittedData.build().getTestTypes().get(0).setReasonForAbandoning(null);
        vehicleSubmittedData.build().getTestTypes().get(0).setTestResult("fail");
        vehicleSubmittedData.build().getTestTypes().get(0).setTestTypeId("");
        vehicleSubmittedData.build().getTestTypes().get(0).setTestTypeName("Paid roadworthiness retest");
        vehicleSubmittedData.build().getVehicleClass().setCode("t").setDescription("trailer");

        vehicleSubmittedData.build().getTestTypes().get(0).getDefects().get(0)
                .setDeficiencyCategory("dangerous")
                .setDeficiencyId("a")
                .setDeficiencyRef("6.2.a.ii")
                .setDeficiencySubId("ii")
                .setDeficiencyText("with any visible elongation of a stud hole where secure fixing of a wheel is affected.")
                .setImDescription("Road Wheels and Hubs")
                .setImNumber(6)
                .setItemDescription("A wheel:")
                .setItemNumber(2)
                .setProhibitionIssued(true)
                .setPrs(false)
                .setStdForProhibition(false);

        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode payload = objectMapper.valueToTree(vehicleSubmittedData.build());

        testResultsSteps.addAdditionalTestResultsFieldValue(payload, "trailerId", "C234567");
        testResultsSteps.addAdditionalTestResultsFieldValue(payload, "firstUseDate", 15);
        testResultsSteps.removeTestResultsFields(payload, "numberOfSeats", "odometerReading", "odometerReadingUnits", "vehicleId", "vehicleSize", "vrm");
        testResultsSteps.removeTestResultsTestTypesFields(payload, 0, "certificateLink", "createdAt", "lastSeatbeltInstallationCheckDate", "lastUpdatedAt", "numberOfSeatbeltsFitted", "seatbeltInstallationCheckDate", "testCode", "testNumber", "testExpiryDate", "testAnniversaryDate");

        testResultsSteps.postTestResultsPayload(payload);

        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorDataContains("firstUseDate", "must be a string");

    }


    @Title("CVSB-8703 - Iteration on test results API specs to cover the logic of First test expiry date generation for HGV/TRL certificates (HGV - wrong type)")
    @Test
    public void testTestResultsRegnDateWrongHgv() {

        vehicleSubmittedData
                .setVin(generateRandomExcludingValues(21, vehicleSubmittedData.build().getVin()))
                .setVrm(generateRandomExcludingValues(7, vehicleSubmittedData.build().getVrm()))
                .setTestResultId(generateRandomExcludingValues(3,vehicleSubmittedData.build().getTestResultId()))
                .setCountryOfRegistration("a")
                .setEuVehicleCategory("n2")
                .setNoOfAxles(2)
                .setReasonForCancellation(null)
                .setTestStationName("Abshire-Kub")
                .setTestStationPNumber("09-4129632")
                .setTestStationType("gvts")
                .setVehicleType("hgv")
                .setVehicleConfiguration("articulated")
                .setTestStatus("submitted")
                .build();

        vehicleSubmittedData.build().getTestTypes().get(0).setName("First test");
        vehicleSubmittedData.build().getTestTypes().get(0).setTestTypeName("First test");
        vehicleSubmittedData.build().getTestTypes().get(0).setTestTypeId("95");
        vehicleSubmittedData.build().getTestTypes().get(0).setCertificateNumber(null);
        vehicleSubmittedData.build().getTestTypes().get(0).setTestResult("fail");
        vehicleSubmittedData.build().getTestTypes().get(0).setProhibitionIssued(false);
        vehicleSubmittedData.build().getTestTypes().get(0).setReasonForAbandoning(null);
        vehicleSubmittedData.build().getTestTypes().get(0).setAdditionalCommentsForAbandon(null);
        vehicleSubmittedData.build().getTestTypes().get(0).setAdditionalNotesRecorded(null);

        vehicleSubmittedData.build().getVehicleClass().setCode("v").setDescription("heavy goods vehicle");
        vehicleSubmittedData.build().getTestTypes().get(0).getDefects().get(0)
                .setDeficiencyCategory("major")
                .setDeficiencyId("b")
                .setDeficiencyRef("3.1.b")
                .setDeficiencySubId(null)
                .setDeficiencyText("of an incorrect type.")
                .setImDescription("Seat Belts & Supplementary Restraint Systems")
                .setImNumber(3)
                .setItemDescription("Obligatory Seat Belt:")
                .setItemNumber(1)
                .setProhibitionIssued(true)
                .setPrs(false)
                .setStdForProhibition(false);

        vehicleSubmittedData.build().getTestTypes().get(0).getDefects().get(0).getAdditionalInformation()
                .setNotes("not working")
                .getLocation()
                .setVertical("lower")
                .setHorizontal(null)
                .setLateral("offside")
                .setLongitudinal(null)
                .setRowNumber(null)
                .setSeatNumber(null)
                .setAxleNumber(null);

        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode payload = objectMapper.valueToTree(vehicleSubmittedData.build());

        testResultsSteps.addAdditionalTestResultsFieldValue(payload, "regnDate", 15);
        testResultsSteps.removeTestResultsFields(payload, "vehicleSize", "numberOfSeats", "vehicleId");
        testResultsSteps.removeTestResultsTestTypesFields(payload, 0, "numberOfSeatbeltsFitted", "lastSeatbeltInstallationCheckDate", "seatbeltInstallationCheckDate", "createdAt", "lastUpdatedAt", "testCode", "testNumber", "certificateLink", "testExpiryDate", "testAnniversaryDate");

        testResultsSteps.postTestResultsPayload(payload);

        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorDataContains("regnDate", "must be a string");

    }

    @Title("CVSB-3946 - API changes related to test records to enable TIR testing - negative - missing certificateNumber - TRL")
    @Test
    public void testTirCertificateMissingTrl() {

        // Read the base test result JSON.
        String testResultRecord = GenericData.readJsonValueFromFile("technical-records_tir_trl.json","$");

        // Create alteration to add one more tech record to in the request body
        String randomVin = GenericData.generateRandomVin();
        String randomTestResultId = UUID.randomUUID().toString();
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin,"","REPLACE");
        JsonPathAlteration alterationTestResultId = new JsonPathAlteration("$.testResultId", randomTestResultId,"","REPLACE");
        JsonPathAlteration alterationTestTypeId = new JsonPathAlteration("$.testTypes[0].testTypeId", "56","","REPLACE");
        JsonPathAlteration alterationName = new JsonPathAlteration("$.testTypes[0].name", "Retest","","REPLACE");
        JsonPathAlteration alterationTestTypeName = new JsonPathAlteration("$.testTypes[0].testTypeName", "Paid TIR retest","","REPLACE");
        JsonPathAlteration alterationCertificateNumber = new JsonPathAlteration("$.testTypes[0].certificateNumber", "certificateNumber","","DELETE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(
                alterationVin,
                alterationTestResultId,
                alterationTestTypeId,
                alterationName,
                alterationTestTypeName,
                alterationCertificateNumber));

        // Post the results, together with any alterations, and verify that they are handled correctly.
        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, alterations);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validateErrorText("Certificate number not present on TIR test type");

    }

    @Title("CVSB-3946 - API changes related to test records to enable TIR testing - negative - missing certificateNumber - HGV")
    @Test
    public void testTirCertificateMissingHgv() {

        // Read the base test result JSON.
        String testResultRecord = GenericData.readJsonValueFromFile("technical-records_tir_hgv.json","$");

        // Create alteration to add one more tech record to in the request body
        String randomVin = GenericData.generateRandomVin();
        String randomTestResultId = UUID.randomUUID().toString();
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin,"","REPLACE");
        JsonPathAlteration alterationTestResultId = new JsonPathAlteration("$.testResultId", randomTestResultId,"","REPLACE");
        JsonPathAlteration alterationTestTypeId = new JsonPathAlteration("$.testTypes[0].testTypeId", "49","","REPLACE");
        JsonPathAlteration alterationName = new JsonPathAlteration("$.testTypes[0].name", "Technical test","","REPLACE");
        JsonPathAlteration alterationTestTypeName = new JsonPathAlteration("$.testTypes[0].testTypeName", "TIR test","","REPLACE");
        JsonPathAlteration alterationCertificateNumber = new JsonPathAlteration("$.testTypes[0].certificateNumber", "certificateNumber","","DELETE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(
                alterationVin,
                alterationTestResultId,
                alterationTestTypeId,
                alterationName,
                alterationTestTypeName,
                alterationCertificateNumber));

        // Post the results, together with any alterations, and verify that they are handled correctly.
        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, alterations);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validateData("Certificate number not present on TIR test type");

    }

    @Title("CVSB-3946 - API changes related to test records to enable TIR testing - Free TIR - TRL")
    @Test
    public void testTirCertificateFreeTirTrl() {

        // Read the base test result JSON.
        String testResultRecord = GenericData.readJsonValueFromFile("technical-records_tir_57_trl.json","$");

        // Create alteration to add one more tech record to in the request body
        String randomVin = GenericData.generateRandomVin();
        String randomTestResultId = UUID.randomUUID().toString();
        String randomSystemNumber = GenericData.generateRandomSystemNumber();
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin,"","REPLACE");
        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$.systemNumber",randomSystemNumber ,"","REPLACE");
        JsonPathAlteration alterationTestResultId = new JsonPathAlteration("$.testResultId", randomTestResultId,"","REPLACE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(
                alterationVin,
                alterationSystemNumber,
                alterationTestResultId));

        // Post the results, together with any alterations, and verify that they are handled correctly.
        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, alterations);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");

        // Retrieve the created record, and verify that the fields are present.
        testResultsSteps.getTestResults(randomSystemNumber);
        testResultsSteps.statusCodeShouldBe(200);

        // Verify TIR test field values match the expected values.
        testResultsSteps.valueForFieldInPathShouldBe("[0].testTypes[0].testTypeId", "57");
        testResultsSteps.valueForFieldInPathShouldBe("[0].testTypes[0].certificateNumber", "GB/T22222");
    }

}
