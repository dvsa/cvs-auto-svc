package testresults;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import data.TestResultsData;
import model.testresults.TestResults;
import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.junit.annotations.TestData;
import org.junit.Test;
import org.junit.runner.RunWith;
import steps.TestResultsSteps;
import util.DataUtil;

import java.util.Arrays;
import java.util.Collection;
import java.util.UUID;

import static util.DataUtil.generateRandomExcludingValues;

@WithTag("In_test")
@RunWith(SerenityParameterizedRunner.class)
public class PostTestResultsAdrCertificate {



    @Steps
    TestResultsSteps testResultsSteps;

    @TestData
    public static Collection<Object[]> testData(){
        return Arrays.asList(new Object[][]{
//                {"50"},
//                {"59"},
                {"60"}
        });
    }

    public PostTestResultsAdrCertificate(String testTypeId) {
        this.testTypeId = testTypeId;
    }



    private String testTypeId;
    private TestResults.Builder vehicleSubmittedData = TestResultsData.buildTestResultsSubmittedData();


    @Title("CVSB-8798 - Include checking certificates have generated in BE suite")
    @Test
    public void testCertificateGenerationHgv() {

        String uuid = String.valueOf(UUID.randomUUID());
        String vin = generateRandomExcludingValues(21, vehicleSubmittedData.build().getVin());

        vehicleSubmittedData
                .setVin(vin)
                .setVrm("HG999HG")
                .setTestResultId(uuid)
                .setCountryOfRegistration("RO")
                .setEuVehicleCategory("n2")
                .setNoOfAxles(2)
                .setReasonForCancellation(null)
                .setTestStationName("Abshire-Kub")
                .setTestStationPNumber("09-4129632")
                .setTestStationType("gvts")
                .setVehicleType("hgv")
                .setVehicleConfiguration("semi-car transporter")
                .setTestStatus("submitted")
                .build();

        vehicleSubmittedData.build().getTestTypes().get(0).setName("Annual test");
        vehicleSubmittedData.build().getTestTypes().get(0).setTestTypeName("Annual test");
        vehicleSubmittedData.build().getTestTypes().get(0).setTestTypeId(testTypeId);
        vehicleSubmittedData.build().getTestTypes().get(0).setCertificateNumber("50CERT");
        vehicleSubmittedData.build().getTestTypes().get(0).setTestResult("pass");
        vehicleSubmittedData.build().getTestTypes().get(0).setProhibitionIssued(false);
        vehicleSubmittedData.build().getTestTypes().get(0).setReasonForAbandoning(null);
        vehicleSubmittedData.build().getTestTypes().get(0).setAdditionalCommentsForAbandon(null);
        vehicleSubmittedData.build().getTestTypes().get(0).setAdditionalNotesRecorded(null);
        vehicleSubmittedData.build().getTestTypes().get(0).setTestExpiryDate(null);
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


        testResultsSteps.addAdditionalTestResultsTestTypesFields(payload,0, "testExpiryDate", DataUtil.buildDate(DataUtil.buildCurrentDateTime(), 1, -1));
        testResultsSteps.removeTestResultsFields(payload, "vehicleSize", "numberOfSeats", "vehicleId");
        testResultsSteps.removeTestResultsTestTypesFields(payload, 0, "numberOfSeatbeltsFitted", "lastSeatbeltInstallationCheckDate", "seatbeltInstallationCheckDate", "createdAt", "lastUpdatedAt", "testCode", "testNumber", "certificateLink", "testAnniversaryDate");

        testResultsSteps.postTestResultsPayload(payload);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        testResultsSteps.validateCertificateIsGenerated(uuid,vin);

    }


//    @WithTag("In_Test")
//    @Title("CVSB-6805 - CVSB-7256 - API Consumer creates a new test results for the submitted test (HGV)")
//    @Test
//    public void testTestResultsPostValidHgv() {
//
//        vehicleSubmittedData
//                .setVin(generateRandomExcludingValues(21, vehicleSubmittedData.build().getVin()))
//                .setVrm(generateRandomExcludingValues(7, vehicleSubmittedData.build().getVrm()))
//                .setTestResultId(generateRandomExcludingValues(3,vehicleSubmittedData.build().getTestResultId()))
//                .setCountryOfRegistration("a")
//                .setEuVehicleCategory("n2")
//                .setNoOfAxles(2)
//                .setReasonForCancellation(null)
//                .setTestStationName("Abshire-Kub")
//                .setTestStationPNumber("09-4129632")
//                .setTestStationType("gvts")
//                .setVehicleType("hgv")
//                .setVehicleConfiguration("articulated")
//                .setTestStatus("submitted")
//                .build();
//
//        vehicleSubmittedData.build().getTestTypes().get(0).setName("Retest");
//        vehicleSubmittedData.build().getTestTypes().get(0).setTestTypeName("ADR Test");
//        vehicleSubmittedData.build().getTestTypes().get(0).setTestTypeId(testTypeId);
//        vehicleSubmittedData.build().getTestTypes().get(0).setCertificateNumber(null);
//        vehicleSubmittedData.build().getTestTypes().get(0).setTestResult("fail");
//        vehicleSubmittedData.build().getTestTypes().get(0).setProhibitionIssued(false);
//        vehicleSubmittedData.build().getTestTypes().get(0).setReasonForAbandoning(null);
//        vehicleSubmittedData.build().getTestTypes().get(0).setAdditionalCommentsForAbandon(null);
//        vehicleSubmittedData.build().getTestTypes().get(0).setAdditionalNotesRecorded(null);
//
//        vehicleSubmittedData.build().getVehicleClass().setCode("v").setDescription("heavy goods vehicle");
//        vehicleSubmittedData.build().getTestTypes().get(0).getDefects().get(0)
//                .setDeficiencyCategory("major")
//                .setDeficiencyId("b")
//                .setDeficiencyRef("3.1.b")
//                .setDeficiencySubId(null)
//                .setDeficiencyText("of an incorrect type.")
//                .setImDescription("Seat Belts & Supplementary Restraint Systems")
//                .setImNumber(3)
//                .setItemDescription("Obligatory Seat Belt:")
//                .setItemNumber(1)
//                .setProhibitionIssued(true)
//                .setPrs(false)
//                .setStdForProhibition(false);
//
//        vehicleSubmittedData.build().getTestTypes().get(0).getDefects().get(0).getAdditionalInformation()
//                .setNotes("not working")
//                .getLocation()
//                .setVertical("lower")
//                .setHorizontal(null)
//                .setLateral("offside")
//                .setLongitudinal(null)
//                .setRowNumber(null)
//                .setSeatNumber(null)
//                .setAxleNumber(null);
//
//        ObjectMapper objectMapper = new ObjectMapper();
//        ObjectNode payload = objectMapper.valueToTree(vehicleSubmittedData.build());
//
//        testResultsSteps.removeTestResultsFields(payload, "vehicleSize", "numberOfSeats", "vehicleId");
//        testResultsSteps.removeTestResultsTestTypesFields(payload, 0, "numberOfSeatbeltsFitted", "lastSeatbeltInstallationCheckDate", "seatbeltInstallationCheckDate", "createdAt", "lastUpdatedAt", "testCode", "testNumber", "certificateLink", "testExpiryDate", "testAnniversaryDate");
//
//        testResultsSteps.postTestResultsPayload(payload);
//        testResultsSteps.statusCodeShouldBe(201);
//        testResultsSteps.validateData("Test records created");
//
//        testResultsSteps.getTestResults(vehicleSubmittedData.build().getVin());
//        testResultsSteps.statusCodeShouldBe(200);
//
//        testResultsSteps.validateVehicleFieldExists("vrm");
//        testResultsSteps.validateVehicleFieldValue("vehicleType", "hgv");
//        testResultsSteps.validateVehicleFieldValue("odometerReading", 350000);
//        testResultsSteps.validateVehicleFieldValue("odometerReadingUnits", "kilometres");
//    }


}
