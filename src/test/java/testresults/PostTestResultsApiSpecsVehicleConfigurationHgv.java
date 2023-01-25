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
public class PostTestResultsApiSpecsVehicleConfigurationHgv {

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

    public PostTestResultsApiSpecsVehicleConfigurationHgv(String vehicleConfiguration) {
        this.vehicleConfiguration = vehicleConfiguration;
    }

    private TestResults.Builder vehicleSubmittedDataOld = TestResultsData.buildTestResultsSubmittedDataOld();
    private String vehicleConfiguration;
    private DateTime currentTimestamp = DateTime.now().withZone(DateTimeZone.UTC);
    private String testStartTimestamp = currentTimestamp.minusYears(1).minusHours(2).toString();
    private String testEndTimestamp = currentTimestamp.minusYears(1).minusHours(1).toString();

    @Title("CVSB-7391 - TC - POST values for vehicleConfiguration (HGV) - cancelled")
    @Test
    public void testTestResultsPostVehicleConfigurationCancelledHgv() {

        vehicleSubmittedDataOld
                .setVin(generateRandomExcludingValues(21, vehicleSubmittedDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(16, vehicleSubmittedDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleSubmittedDataOld.build().getVrm()))
                .setTestResultId(generateRandomExcludingValues(3, vehicleSubmittedDataOld.build().getTestResultId()))
                .setTestStartTimestamp(testStartTimestamp)
                .setTestEndTimestamp(testEndTimestamp)
                .setCountryOfRegistration("a")
                .setEuVehicleCategory("n2")
                .setNoOfAxles(2)
                .setReasonForCancellation("era naspa")
                .setTestStationName("Abshire-Kub")
                .setTestStationPNumber("09-4129632")
                .setTestStationType("gvts")
                .setVehicleType("hgv")
                .setVehicleConfiguration(vehicleConfiguration)
                .setTestStatus("cancelled")
                .build();

        vehicleSubmittedDataOld.build().getTestTypes().get(0).setName("Annual test");
        vehicleSubmittedDataOld.build().getTestTypes().get(0).setTestTypeName("Annual test");
        vehicleSubmittedDataOld.build().getTestTypes().get(0).setTestTypeId("94");
        vehicleSubmittedDataOld.build().getTestTypes().get(0).setCertificateNumber(null);
        vehicleSubmittedDataOld.build().getTestTypes().get(0).setTestResult("fail");
        vehicleSubmittedDataOld.build().getTestTypes().get(0).setProhibitionIssued(false);
        vehicleSubmittedDataOld.build().getTestTypes().get(0).setReasonForAbandoning(null);
        vehicleSubmittedDataOld.build().getTestTypes().get(0).setAdditionalCommentsForAbandon(null);
        vehicleSubmittedDataOld.build().getTestTypes().get(0).setAdditionalNotesRecorded(null);

        vehicleSubmittedDataOld.build().getVehicleClass().setCode("v").setDescription("heavy goods vehicle");
        vehicleSubmittedDataOld.build().getTestTypes().get(0).getDefects().get(0)
                .setDeficiencyCategory("major")
                .setDeficiencyId("a")
                .setDeficiencyRef("3.1.b")
                .setDeficiencySubId(null)
                .setDeficiencyText("missing")
                .setImDescription("Registration Plate")
                .setImNumber(3)
                .setItemDescription("A registration plate:")
                .setItemNumber(1)
                .setProhibitionIssued(true)
                .setPrs(false)
                .setStdForProhibition(false);

        vehicleSubmittedDataOld.build().getTestTypes().get(0).getDefects().get(0).getAdditionalInformation()
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
        ObjectNode payload = objectMapper.valueToTree(vehicleSubmittedDataOld.build());

        testResultsSteps.removeTestResultsFields(payload, "vehicleSize", "numberOfSeats", "vehicleId");
        testResultsSteps.removeTestResultsTestTypesFields(payload, 0, "numberOfSeatbeltsFitted", "lastSeatbeltInstallationCheckDate", "seatbeltInstallationCheckDate", "createdAt", "lastUpdatedAt", "testCode", "testNumber", "certificateLink", "testExpiryDate", "testAnniversaryDate");

        testResultsSteps.postTestResultsPayload(payload);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");

        testResultsSteps.getTestResults(vehicleSubmittedDataOld.build().getSystemNumber(), TestResultsStatus.CANCELED);
        testResultsSteps.statusCodeShouldBe(200);

        testResultsSteps.validateVehicleFieldExists("vrm");
        testResultsSteps.validateVehicleFieldValue("vehicleType", "hgv");
        testResultsSteps.validateVehicleFieldValue("vehicleConfiguration", vehicleConfiguration);
    }

    @Title("CVSB-7391 - TC - POST values for vehicleConfiguration (HGV) - Submitted")
    @Test
    public void testTestResultsPostVehicleConfigurationSubmittedHgv() {

        vehicleSubmittedDataOld
                .setVin(generateRandomExcludingValues(21, vehicleSubmittedDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(16, vehicleSubmittedDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleSubmittedDataOld.build().getVrm()))
                .setTestResultId(generateRandomExcludingValues(3, vehicleSubmittedDataOld.build().getTestResultId()))
                .setTestStartTimestamp(testStartTimestamp)
                .setTestEndTimestamp(testEndTimestamp)
                .setCountryOfRegistration("a")
                .setEuVehicleCategory("n2")
                .setNoOfAxles(2)
                .setReasonForCancellation(null)
                .setTestStationName("Abshire-Kub")
                .setTestStationPNumber("09-4129632")
                .setTestStationType("gvts")
                .setVehicleType("hgv")
                .setVehicleConfiguration(vehicleConfiguration)
                .setTestStatus("submitted")
                .build();

        vehicleSubmittedDataOld.build().getTestTypes().get(0).setName("Annual test");
        vehicleSubmittedDataOld.build().getTestTypes().get(0).setTestTypeName("Annual test");
        vehicleSubmittedDataOld.build().getTestTypes().get(0).setTestTypeId("94");
        vehicleSubmittedDataOld.build().getTestTypes().get(0).setCertificateNumber(null);
        vehicleSubmittedDataOld.build().getTestTypes().get(0).setTestResult("fail");
        vehicleSubmittedDataOld.build().getTestTypes().get(0).setProhibitionIssued(false);
        vehicleSubmittedDataOld.build().getTestTypes().get(0).setReasonForAbandoning(null);
        vehicleSubmittedDataOld.build().getTestTypes().get(0).setAdditionalCommentsForAbandon(null);
        vehicleSubmittedDataOld.build().getTestTypes().get(0).setAdditionalNotesRecorded(null);

        vehicleSubmittedDataOld.build().getVehicleClass().setCode("v").setDescription("heavy goods vehicle");
        vehicleSubmittedDataOld.build().getTestTypes().get(0).getDefects().get(0)
                .setDeficiencyCategory("major")
                .setDeficiencyId("a")
                .setDeficiencyRef("3.1.b")
                .setDeficiencySubId(null)
                .setDeficiencyText("missing")
                .setImDescription("Registration Plate")
                .setImNumber(3)
                .setItemDescription("A registration plate:")
                .setItemNumber(1)
                .setProhibitionIssued(true)
                .setPrs(false)
                .setStdForProhibition(false);

        vehicleSubmittedDataOld.build().getTestTypes().get(0).getDefects().get(0).getAdditionalInformation()
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
        ObjectNode payload = objectMapper.valueToTree(vehicleSubmittedDataOld.build());

        testResultsSteps.removeTestResultsFields(payload, "vehicleSize", "numberOfSeats", "vehicleId");
        testResultsSteps.removeTestResultsTestTypesFields(payload, 0, "numberOfSeatbeltsFitted", "lastSeatbeltInstallationCheckDate", "seatbeltInstallationCheckDate", "createdAt", "lastUpdatedAt", "testCode", "testNumber", "certificateLink", "testExpiryDate", "testAnniversaryDate");

        testResultsSteps.postTestResultsPayload(payload);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");

        testResultsSteps.getTestResults(vehicleSubmittedDataOld.build().getSystemNumber(), TestResultsStatus.SUBMITTED);
        testResultsSteps.statusCodeShouldBe(200);

        testResultsSteps.validateVehicleFieldExists("vrm");
        testResultsSteps.validateVehicleFieldValue("vehicleType", "hgv");
        testResultsSteps.validateVehicleFieldValue("vehicleConfiguration", vehicleConfiguration);
    }
}
