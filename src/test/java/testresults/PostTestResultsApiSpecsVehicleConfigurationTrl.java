package testresults;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import data.TestResultsData;
import model.testresults.TestResults;
import model.testresults.TestResultsStatus;
import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import net.thucydides.junit.annotations.TestData;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.junit.Test;
import org.junit.runner.RunWith;
import steps.TestResultsSteps;

import java.util.Arrays;
import java.util.Collection;

import static util.DataUtil.generateRandomExcludingValues;


@RunWith(SerenityParameterizedRunner.class)
public class PostTestResultsApiSpecsVehicleConfigurationTrl {

    @Steps
    TestResultsSteps testResultsSteps;

    @TestData
    public static Collection<Object[]> testData(){
        return Arrays.asList(new Object[][]{
                {"centre axle drawbar"},
                {"semi-car transporter"},
                {"semi-trailer"},
                {"long semi-trailer"},
                {"low loader"},
                {"other"},
                {"drawbar"},
                {"four-in-line"},
                {"dolly"},
                {"rigid"},
                {"articulated"},
                {"full drawbar"}
//                {"wrong", 400}
        });
    }

    public PostTestResultsApiSpecsVehicleConfigurationTrl(String vehicleConfiguration) {
        this.vehicleConfiguration = vehicleConfiguration;
    }

    private TestResults.Builder vehicleSubmittedDataOld = TestResultsData.buildTestResultsSubmittedDataOld();
    private String vehicleConfiguration;

    private DateTime currentTimestamp = DateTime.now().withZone(DateTimeZone.UTC);
    private String testStartTimestamp = currentTimestamp.minusYears(1).minusHours(2).toString();
    private String testEndTimestamp = currentTimestamp.minusYears(1).minusHours(1).toString();

    @Title("CVSB-7391 - TC - POST values for vehicleConfiguration (TRL) - submitted")
    @Test

    public void testTestResultsPostVehicleConfigurationSubmittedTrl() {

        vehicleSubmittedDataOld
                .setVin(generateRandomExcludingValues(21, vehicleSubmittedDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(16, vehicleSubmittedDataOld.build().getSystemNumber()))
                .setTestResultId(generateRandomExcludingValues(3, vehicleSubmittedDataOld.build().getTestResultId()))
                .setTestStartTimestamp(vehicleSubmittedDataOld.build().getTestStartTimestamp())
                .setCountryOfRegistration("a")
                .setEuVehicleCategory("o3")
                .setNoOfAxles(6)
                .setReasonForCancellation(null)
                .setTestStartTimestamp(testStartTimestamp)
                .setTestEndTimestamp(testEndTimestamp)
                .setTesterEmailAddress("cvs.automation3@dvsagov.onmicrosoft.com")
                .setTesterName("cvs.automation3@dvsagov.onmicrosoft.com")
                .setTestStationName("Abshire-Kub")
                .setTestStationPNumber("09-4129632")
                .setTestStationType("gvts")
                .setVehicleType("trl")
                .setVehicleConfiguration(vehicleConfiguration)
                .setTestStatus("submitted").build()
                .getTestTypes().get(0).setAdditionalCommentsForAbandon(null);

        vehicleSubmittedDataOld.build().getTestTypes().get(0).setAdditionalNotesRecorded("a note");
        vehicleSubmittedDataOld.build().getTestTypes().get(0).setCertificateNumber(null);
        vehicleSubmittedDataOld.build().getTestTypes().get(0).setName("Retest");
        vehicleSubmittedDataOld.build().getTestTypes().get(0).setProhibitionIssued(false);
        vehicleSubmittedDataOld.build().getTestTypes().get(0).setReasonForAbandoning(null);
        vehicleSubmittedDataOld.build().getTestTypes().get(0).setTestResult("fail");
        vehicleSubmittedDataOld.build().getTestTypes().get(0).setTestTypeId("");
        vehicleSubmittedDataOld.build().getTestTypes().get(0).setTestTypeName("Paid roadworthiness retest");
        vehicleSubmittedDataOld.build().getVehicleClass().setCode("t").setDescription("trailer");

        vehicleSubmittedDataOld.build().getTestTypes().get(0).getDefects().get(0)
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
        ObjectNode payload = objectMapper.valueToTree(vehicleSubmittedDataOld.build());

        testResultsSteps.addAdditionalTestResultsFieldValue(payload, "trailerId", "C234567");
        testResultsSteps.removeTestResultsFields(payload, "numberOfSeats", "odometerReading", "odometerReadingUnits", "vehicleId", "vehicleSize", "vrm");
        testResultsSteps.removeTestResultsTestTypesFields(payload, 0, "certificateLink", "createdAt", "lastSeatbeltInstallationCheckDate", "lastUpdatedAt", "numberOfSeatbeltsFitted", "seatbeltInstallationCheckDate", "testCode", "testNumber", "testExpiryDate", "testAnniversaryDate");

        testResultsSteps.postTestResultsPayload(payload);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");

        testResultsSteps.getTestResults(vehicleSubmittedDataOld.build().getSystemNumber(), TestResultsStatus.SUBMITTED);
        testResultsSteps.statusCodeShouldBe(200);

        testResultsSteps.validateVehicleFieldValue("vehicleType", "trl");
        testResultsSteps.validateVehicleFieldValue("vehicleConfiguration", vehicleConfiguration);

    }

    @Title("CVSB-7391 - TC - POST values for vehicleConfiguration (TRL) - cancelled")
    @Test
    public void testTestResultsPostVehicleConfigurationCancelledTrl() {

        vehicleSubmittedDataOld
                .setVin(generateRandomExcludingValues(21, vehicleSubmittedDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(16, vehicleSubmittedDataOld.build().getSystemNumber()))
                .setTestResultId(generateRandomExcludingValues(3, vehicleSubmittedDataOld.build().getTestResultId()))
                .setCountryOfRegistration("a")
                .setEuVehicleCategory("o3")
                .setNoOfAxles(6)
                .setReasonForCancellation("era naspa")
                .setTestStartTimestamp(testStartTimestamp)
                .setTestEndTimestamp(testEndTimestamp)
                .setTesterEmailAddress("cvs.automation3@dvsagov.onmicrosoft.com")
                .setTesterName("cvs.automation3@dvsagov.onmicrosoft.com")
                .setTestStationName("Abshire-Kub")
                .setTestStationPNumber("09-4129632")
                .setTestStationType("gvts")
                .setVehicleType("trl")
                .setVehicleConfiguration(vehicleConfiguration)
                .setTestStatus("cancelled").build()
                .getTestTypes().get(0).setAdditionalCommentsForAbandon(null);

        vehicleSubmittedDataOld.build().getTestTypes().get(0).setAdditionalNotesRecorded("a note");
        vehicleSubmittedDataOld.build().getTestTypes().get(0).setCertificateNumber(null);
        vehicleSubmittedDataOld.build().getTestTypes().get(0).setName("Retest");
        vehicleSubmittedDataOld.build().getTestTypes().get(0).setProhibitionIssued(false);
        vehicleSubmittedDataOld.build().getTestTypes().get(0).setReasonForAbandoning(null);
        vehicleSubmittedDataOld.build().getTestTypes().get(0).setTestResult("fail");
        vehicleSubmittedDataOld.build().getTestTypes().get(0).setTestTypeId("");
        vehicleSubmittedDataOld.build().getTestTypes().get(0).setTestTypeName("Paid roadworthiness retest");
        vehicleSubmittedDataOld.build().getVehicleClass().setCode("t").setDescription("trailer");

        vehicleSubmittedDataOld.build().getTestTypes().get(0).getDefects().get(0)
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
        ObjectNode payload = objectMapper.valueToTree(vehicleSubmittedDataOld.build());

        testResultsSteps.addAdditionalTestResultsFieldValue(payload, "trailerId", "C234567");
        testResultsSteps.removeTestResultsFields(payload, "numberOfSeats", "odometerReading", "odometerReadingUnits", "vehicleId", "vehicleSize", "vrm");
        testResultsSteps.removeTestResultsTestTypesFields(payload, 0, "certificateLink", "createdAt", "lastSeatbeltInstallationCheckDate", "lastUpdatedAt", "numberOfSeatbeltsFitted", "seatbeltInstallationCheckDate", "testCode", "testNumber", "testExpiryDate", "testAnniversaryDate");

        testResultsSteps.postTestResultsPayload(payload);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");

        testResultsSteps.getTestResults(vehicleSubmittedDataOld.build().getSystemNumber(), TestResultsStatus.CANCELED);
        testResultsSteps.statusCodeShouldBe(200);

        testResultsSteps.validateVehicleFieldValue("vehicleType", "trl");
        testResultsSteps.validateVehicleFieldValue("vehicleConfiguration", vehicleConfiguration);

    }

}
