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
public class PostTestResultsAdrCertificate {



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

    public PostTestResultsAdrCertificate(String testTypeId) {
        this.testTypeId = testTypeId;
    }



    private String testTypeId;
    private TestResults.Builder vehicleSubmittedData = TestResultsData.buildTestResultsSubmittedData();


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

}
