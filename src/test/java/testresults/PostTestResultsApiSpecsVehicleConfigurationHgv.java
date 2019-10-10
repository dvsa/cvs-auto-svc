package testresults;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import data.TestResultsData;
import model.testresults.TestResults;
import model.testresults.TestResultsStatus;
import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.core.annotations.WithTags;
import net.thucydides.junit.annotations.TestData;
import org.junit.Test;
import org.junit.runner.RunWith;
import steps.TestResultsSteps;

import java.util.Arrays;
import java.util.Collection;

import static util.DataUtil.generateRandomExcludingValues;

@WithTags(
        {
                @WithTag(type = "TestResults", name = "All"),
                @WithTag(type = "TestResults", name = "Positive"),
                @WithTag(type = "Service", name = "One"),

        }
)

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

    private TestResults.Builder vehicleSubmittedData = TestResultsData.buildTestResultsSubmittedData();
    private String vehicleConfiguration;

    @Title("CVSB-7391 - TC - POST values for vehicleConfiguration (HGV) - cancelled")
    @Test
    public void testTestResultsPostVehicleConfigurationCancelledHgv() {

        vehicleSubmittedData
                .setVin(generateRandomExcludingValues(21, vehicleSubmittedData.build().getVin()))
                .setVrm(generateRandomExcludingValues(7, vehicleSubmittedData.build().getVrm()))
                .setTestResultId(generateRandomExcludingValues(3,vehicleSubmittedData.build().getTestResultId()))
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

        vehicleSubmittedData.build().getTestTypes().get(0).setName("Annual test");
        vehicleSubmittedData.build().getTestTypes().get(0).setTestTypeName("Annual test");
        vehicleSubmittedData.build().getTestTypes().get(0).setTestTypeId("94");
        vehicleSubmittedData.build().getTestTypes().get(0).setCertificateNumber(null);
        vehicleSubmittedData.build().getTestTypes().get(0).setTestResult("fail");
        vehicleSubmittedData.build().getTestTypes().get(0).setProhibitionIssued(false);
        vehicleSubmittedData.build().getTestTypes().get(0).setReasonForAbandoning(null);
        vehicleSubmittedData.build().getTestTypes().get(0).setAdditionalCommentsForAbandon(null);
        vehicleSubmittedData.build().getTestTypes().get(0).setAdditionalNotesRecorded(null);

        vehicleSubmittedData.build().getVehicleClass().setCode("v").setDescription("heavy goods vehicle");
        vehicleSubmittedData.build().getTestTypes().get(0).getDefects().get(0)
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

        testResultsSteps.getTestResults(vehicleSubmittedData.build().getVin(), TestResultsStatus.CANCELED);
        testResultsSteps.statusCodeShouldBe(200);

        testResultsSteps.validateVehicleFieldExists("vrm");
        testResultsSteps.validateVehicleFieldValue("vehicleType", "hgv");
        testResultsSteps.validateVehicleFieldValue("vehicleConfiguration", vehicleConfiguration);
    }
    @Title("CVSB-7391 - TC - POST values for vehicleConfiguration (HGV) - Submitted")
    @Test
    public void testTestResultsPostVehicleConfigurationSubmittedHgv() {

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
                .setVehicleConfiguration(vehicleConfiguration)
                .setTestStatus("submitted")
                .build();

        vehicleSubmittedData.build().getTestTypes().get(0).setName("Annual test");
        vehicleSubmittedData.build().getTestTypes().get(0).setTestTypeName("Annual test");
        vehicleSubmittedData.build().getTestTypes().get(0).setTestTypeId("94");
        vehicleSubmittedData.build().getTestTypes().get(0).setCertificateNumber(null);
        vehicleSubmittedData.build().getTestTypes().get(0).setTestResult("fail");
        vehicleSubmittedData.build().getTestTypes().get(0).setProhibitionIssued(false);
        vehicleSubmittedData.build().getTestTypes().get(0).setReasonForAbandoning(null);
        vehicleSubmittedData.build().getTestTypes().get(0).setAdditionalCommentsForAbandon(null);
        vehicleSubmittedData.build().getTestTypes().get(0).setAdditionalNotesRecorded(null);

        vehicleSubmittedData.build().getVehicleClass().setCode("v").setDescription("heavy goods vehicle");
        vehicleSubmittedData.build().getTestTypes().get(0).getDefects().get(0)
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

        testResultsSteps.getTestResults(vehicleSubmittedData.build().getVin(), TestResultsStatus.SUBMITTED);
        testResultsSteps.statusCodeShouldBe(200);

        testResultsSteps.validateVehicleFieldExists("vrm");
        testResultsSteps.validateVehicleFieldValue("vehicleType", "hgv");
        testResultsSteps.validateVehicleFieldValue("vehicleConfiguration", vehicleConfiguration);
    }
}
