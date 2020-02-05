package testresults;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import data.TestResultsData;
import model.testresults.TestResults;
import model.testresults.TestResultsGet;
import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import net.thucydides.junit.annotations.TestData;
import org.junit.Test;
import org.junit.runner.RunWith;
import steps.TestResultsSteps;
import util.DataUtil;

import java.util.Arrays;
import java.util.Collection;

import static util.DataUtil.generateRandomExcludingValues;

@RunWith(SerenityParameterizedRunner.class)
public class PostTestResultsPozAdrInfSubmitted {



    @Steps
    TestResultsSteps testResultsSteps;

    @TestData
    public static Collection<Object[]> testData(){
        return Arrays.asList(new Object[][]{
                {"50"},
                {"59"},
                {"60"}
        });
    }

    public PostTestResultsPozAdrInfSubmitted(String testTypeId) {
        this.testTypeId = testTypeId;
    }



    private String testTypeId;
    private TestResults.Builder vehicleSubmittedData = TestResultsData.buildTestResultsSubmittedData();

    @Title("CVSB-4927 - TC - API Consumer receives error when submitting an ADR test without sending an expiryDate")
    @Test
    public void testResultsAdrNoExpiryDate() {

        vehicleSubmittedData
                .setVin(generateRandomExcludingValues(21, vehicleSubmittedData.build().getVin()))
                .setTestResultId(generateRandomExcludingValues(3,vehicleSubmittedData.build().getTestResultId()))
                .setCountryOfRegistration("a")
                .setEuVehicleCategory("o3")
                .setNoOfAxles(6)
                .setReasonForCancellation(null)
                .setTestEndTimestamp("2019-09-12T16:42:14.757Z")
                .setTesterEmailAddress("cvs.automation3@dvsagov.onmicrosoft.com")
                .setTesterName("cvs.automation3@dvsagov.onmicrosoft.com")
                .setTestStationName("Abshire-Kub")
                .setTestStationPNumber("09-4129632")
                .setTestStationType("gvts")
                .setVehicleType("trl")
                .setVehicleConfiguration("articulated")
                .setTestStatus("submitted").build()
                .getTestTypes().get(0).setAdditionalCommentsForAbandon(null);

        vehicleSubmittedData.build().getTestTypes().get(0).setAdditionalNotesRecorded(null);
        vehicleSubmittedData.build().getTestTypes().get(0).setCertificateNumber("50CERT");
        vehicleSubmittedData.build().getTestTypes().get(0).setName("Technical test");
        vehicleSubmittedData.build().getTestTypes().get(0).setProhibitionIssued(false);
        vehicleSubmittedData.build().getTestTypes().get(0).setReasonForAbandoning(null);
        vehicleSubmittedData.build().getTestTypes().get(0).setTestResult("pass");
        vehicleSubmittedData.build().getTestTypes().get(0).setTestTypeId(testTypeId);
        vehicleSubmittedData.build().getTestTypes().get(0).setTestTypeName("ADR Test");
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

        testResultsSteps.addAdditionalTestResultsFieldValue(payload, "trailerId", "C000001");
        testResultsSteps.removeTestResultsFields(payload, "numberOfSeats", "odometerReading", "odometerReadingUnits", "vehicleId", "vehicleSize", "vrm");
        testResultsSteps.removeTestResultsTestTypesFields(payload, 0, "certificateLink", "createdAt", "lastSeatbeltInstallationCheckDate", "lastUpdatedAt", "numberOfSeatbeltsFitted", "seatbeltInstallationCheckDate", "testCode", "testNumber", "testExpiryDate", "testAnniversaryDate");

        testResultsSteps.postTestResultsPayload(payload);

        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validateData("Expiry date not present on ADR test type");
    }


    @Title("CVSB-4927 - TC - API Consumer receives error when submitting an ADR test without sending a certificateNumber")
    @Test
    public void testResultsAdrNoCertificateNumber() {

        vehicleSubmittedData
                .setVin(generateRandomExcludingValues(21, vehicleSubmittedData.build().getVin()))
                .setTestResultId(generateRandomExcludingValues(3,vehicleSubmittedData.build().getTestResultId()))
                .setCountryOfRegistration("a")
                .setEuVehicleCategory("o3")
                .setNoOfAxles(6)
                .setReasonForCancellation(null)
                .setTestEndTimestamp("2019-09-12T16:42:14.757Z")
                .setTesterEmailAddress("cvs.automation3@dvsagov.onmicrosoft.com")
                .setTesterName("cvs.automation3@dvsagov.onmicrosoft.com")
                .setTestStationName("Abshire-Kub")
                .setTestStationPNumber("09-4129632")
                .setTestStationType("gvts")
                .setVehicleType("trl")
                .setVehicleConfiguration("articulated")
                .setTestStatus("submitted").build()
                .getTestTypes().get(0).setAdditionalCommentsForAbandon(null);

        vehicleSubmittedData.build().getTestTypes().get(0).setAdditionalNotesRecorded(null);
        vehicleSubmittedData.build().getTestTypes().get(0).setAdditionalCommentsForAbandon(null);
        vehicleSubmittedData.build().getTestTypes().get(0).setCertificateNumber(null);
        vehicleSubmittedData.build().getTestTypes().get(0).setName("Retest");
        vehicleSubmittedData.build().getTestTypes().get(0).setProhibitionIssued(false);
        vehicleSubmittedData.build().getTestTypes().get(0).setReasonForAbandoning(null);
        vehicleSubmittedData.build().getTestTypes().get(0).setTestResult("pass");
        vehicleSubmittedData.build().getTestTypes().get(0).setTestCode("arv");
        vehicleSubmittedData.build().getTestTypes().get(0).setTestTypeId(testTypeId);
        vehicleSubmittedData.build().getTestTypes().get(0).setTestTypeName("ADR Test");
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

        testResultsSteps.addAdditionalTestResultsFieldValue(payload, "trailerId", "C000001");
        testResultsSteps.removeTestResultsFields(payload, "numberOfSeats", "odometerReading", "odometerReadingUnits", "vehicleId", "vehicleSize", "vrm");
        testResultsSteps.removeTestResultsTestTypesFields(payload, 0, "certificateLink", "lastSeatbeltInstallationCheckDate", "numberOfSeatbeltsFitted", "seatbeltInstallationCheckDate", "testNumber", "testAnniversaryDate");

        testResultsSteps.postTestResultsPayload(payload);

        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validateData("Certificate number not present on ADR test type");
    }

    @Title("CVSB-4927 - TC - Negative - API Consumer creates a new test results for the fail ADR test")
    @Test
    public void testResultsFailAdr() {

        vehicleSubmittedData
                .setVin(generateRandomExcludingValues(21, vehicleSubmittedData.build().getVin()))
                .setTestResultId(generateRandomExcludingValues(3,vehicleSubmittedData.build().getTestResultId()))
                .setCountryOfRegistration("a")
                .setEuVehicleCategory("o3")
                .setNoOfAxles(6)
                .setReasonForCancellation(null)
                .setTestEndTimestamp("2019-09-12T16:42:14.757Z")
                .setTesterEmailAddress("cvs.automation3@dvsagov.onmicrosoft.com")
                .setTesterName("cvs.automation3@dvsagov.onmicrosoft.com")
                .setTestStationName("Abshire-Kub")
                .setTestStationPNumber("09-4129632")
                .setTestStationType("gvts")
                .setVehicleType("trl")
                .setVehicleConfiguration("articulated")
                .setTestStatus("submitted").build()
                .getTestTypes().get(0).setAdditionalCommentsForAbandon(null);

        vehicleSubmittedData.build().getTestTypes().get(0).setAdditionalNotesRecorded(null);
        vehicleSubmittedData.build().getTestTypes().get(0).setAdditionalCommentsForAbandon(null);
        vehicleSubmittedData.build().getTestTypes().get(0).setCertificateNumber("50CERT");
        vehicleSubmittedData.build().getTestTypes().get(0).setName("Retest");
        vehicleSubmittedData.build().getTestTypes().get(0).setProhibitionIssued(false);
        vehicleSubmittedData.build().getTestTypes().get(0).setReasonForAbandoning(null);
        vehicleSubmittedData.build().getTestTypes().get(0).setTestResult("fail");
        vehicleSubmittedData.build().getTestTypes().get(0).setTestCode("arv");
        vehicleSubmittedData.build().getTestTypes().get(0).setTestTypeId(testTypeId);
        vehicleSubmittedData.build().getTestTypes().get(0).setTestTypeName("ADR Test");
        vehicleSubmittedData.build().getTestTypes().get(0).setTestExpiryDate(null);
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

        testResultsSteps.addAdditionalTestResultsFieldValue(payload, "trailerId", "C000001");
        testResultsSteps.removeTestResultsFields(payload, "numberOfSeats", "odometerReading", "odometerReadingUnits", "vehicleId", "vehicleSize", "vrm");
        testResultsSteps.removeTestResultsTestTypesFields(payload, 0, "testExpiryDate", "certificateLink", "createdAt", "lastSeatbeltInstallationCheckDate", "lastUpdatedAt", "numberOfSeatbeltsFitted", "seatbeltInstallationCheckDate", "testCode", "testNumber", "testAnniversaryDate");

        testResultsSteps.postTestResultsPayload(payload);

        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
    }

    @Title("CVSB-4927 - TC - Negative - API Consumer creates a new test results")
    @Test
    public void testResultsNonAdrNewTestResults() {
        vehicleSubmittedData
                .setVin(generateRandomExcludingValues(21, vehicleSubmittedData.build().getVin()))
                .setTestResultId(generateRandomExcludingValues(3,vehicleSubmittedData.build().getTestResultId()))
                .setCountryOfRegistration("a")
                .setEuVehicleCategory("o3")
                .setNoOfAxles(1)
                .setReasonForCancellation(null)
                .setTestEndTimestamp("2019-09-12T16:42:14.757Z")
                .setTesterEmailAddress("cvs.automation3@dvsagov.onmicrosoft.com")
                .setTesterName("cvs.automation3@dvsagov.onmicrosoft.com")
                .setTestStationName("Abshire-Kub")
                .setTestStationPNumber("09-4129632")
                .setTestStationType("gvts")
                .setVehicleType("trl")
                .setVehicleConfiguration("articulated")
                .setTestStatus("submitted").build()
                .getTestTypes().get(0).setAdditionalCommentsForAbandon(null);

        vehicleSubmittedData.build().getTestTypes().get(0).setAdditionalNotesRecorded(null);
        vehicleSubmittedData.build().getTestTypes().get(0).setAdditionalCommentsForAbandon(null);
        vehicleSubmittedData.build().getTestTypes().get(0).setName("Annual test");
        vehicleSubmittedData.build().getTestTypes().get(0).setCertificateNumber(null);
        vehicleSubmittedData.build().getTestTypes().get(0).setProhibitionIssued(false);
        vehicleSubmittedData.build().getTestTypes().get(0).setReasonForAbandoning(null);
        vehicleSubmittedData.build().getTestTypes().get(0).setTestResult("pass");
        vehicleSubmittedData.build().getTestTypes().get(0).setTestCode("aat1");
        vehicleSubmittedData.build().getTestTypes().get(0).setTestTypeId("40");
        vehicleSubmittedData.build().getTestTypes().get(0).setTestTypeName("ADR Test");
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

        testResultsSteps.addAdditionalTestResultsFieldValue(payload, "trailerId", "C000001");
        testResultsSteps.removeTestResultsFields(payload, "numberOfSeats", "odometerReading", "odometerReadingUnits", "vehicleId", "vehicleSize", "vrm");
        testResultsSteps.removeTestResultsTestTypesFields(payload, 0,  "certificateLink", "createdAt", "lastSeatbeltInstallationCheckDate", "lastUpdatedAt", "numberOfSeatbeltsFitted", "seatbeltInstallationCheckDate", "testExpiryDate", "testCode", "testNumber", "testAnniversaryDate");

        testResultsSteps.postTestResultsPayload(payload);

        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");

        testResultsSteps.getTestResults(vehicleSubmittedData.build().getVin());
        testResultsSteps.statusCodeShouldBe(200);
        testResultsSteps.validateCertificateNumberIsNotNull((TestResultsGet) vehicleSubmittedData.build());
        testResultsSteps.validateExpiryDateIsNotNull((TestResultsGet) vehicleSubmittedData.build());

    }

    @Title("CVSB-4927 - TC - API Consumer creates a new test results for the pass ADR test with the expiryDate and certificateNumber pulled from request")
    @Test
    public void testResultsAdrNewTestResults() {

        String testResultId = generateRandomExcludingValues(4,vehicleSubmittedData.build().getTestResultId());

        vehicleSubmittedData
                .setVin(generateRandomExcludingValues(21, vehicleSubmittedData.build().getVin()))
                .setTestResultId(testResultId)
                .setCountryOfRegistration("XX")
                .setEuVehicleCategory("o3")
                .setNoOfAxles(1)
                .setReasonForCancellation(null)
                .setTestEndTimestamp("2019-09-12T16:42:14.757Z")
                .setTesterEmailAddress("cvs.automation3@dvsagov.onmicrosoft.com")
                .setTesterName("cvs.automation3@dvsagov.onmicrosoft.com")
                .setTestStationName("Abshire-Kub")
                .setTestStationPNumber("09-4129632")
                .setTestStationType("gvts")
                .setVehicleType("trl")
                .setVehicleConfiguration("articulated")
                .setTestStatus("submitted").build()
                .getTestTypes().get(0).setAdditionalCommentsForAbandon(null);

        vehicleSubmittedData.build().getTestTypes().get(0).setAdditionalNotesRecorded(null);
        vehicleSubmittedData.build().getTestTypes().get(0).setAdditionalCommentsForAbandon(null);
        vehicleSubmittedData.build().getTestTypes().get(0).setName("Annual test");
        vehicleSubmittedData.build().getTestTypes().get(0).setCertificateNumber("50CENT");
        vehicleSubmittedData.build().getTestTypes().get(0).setProhibitionIssued(false);
        vehicleSubmittedData.build().getTestTypes().get(0).setReasonForAbandoning(null);
        vehicleSubmittedData.build().getTestTypes().get(0).setTestResult("pass");
        vehicleSubmittedData.build().getTestTypes().get(0).setTestCode("aat1");
        vehicleSubmittedData.build().getTestTypes().get(0).setTestTypeId(testTypeId);
        vehicleSubmittedData.build().getTestTypes().get(0).setTestTypeName("ADR Test");
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

        testResultsSteps.addAdditionalTestResultsFieldValue(payload, "trailerId", "C000001");
        testResultsSteps.addAdditionalTestResultsTestTypesFields(payload,0, "testExpiryDate", DataUtil.buildDate(DataUtil.buildCurrentDateTime(), 1, -1));
        testResultsSteps.removeTestResultsFields(payload, "numberOfSeats", "odometerReading", "odometerReadingUnits", "vehicleId", "vehicleSize", "vrm");
        testResultsSteps.removeTestResultsTestTypesFields(payload, 0,  "certificateLink", "createdAt", "lastSeatbeltInstallationCheckDate", "lastUpdatedAt", "numberOfSeatbeltsFitted", "seatbeltInstallationCheckDate", "testCode", "testNumber", "testAnniversaryDate");

        testResultsSteps.postTestResultsPayload(payload);

        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");

        testResultsSteps.getTestResults(vehicleSubmittedData.build().getVin());
        testResultsSteps.statusCodeShouldBe(200);
        testResultsSteps.validateCertificateNumberIsNotNull((TestResultsGet) vehicleSubmittedData.build());
        testResultsSteps.validateExpiryDateIsNotNull((TestResultsGet) vehicleSubmittedData.build());

    }
}
