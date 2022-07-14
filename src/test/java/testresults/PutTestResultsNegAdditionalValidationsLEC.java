//package testresults;
//
//import data.GenericData;
//import net.serenitybdd.junit.runners.SerenityRunner;
//import net.thucydides.core.annotations.Steps;
//import net.thucydides.core.annotations.Title;
//import net.thucydides.core.annotations.WithTag;
//import org.apache.http.HttpStatus;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import steps.*;
//import util.JsonPathAlteration;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//import java.util.UUID;
//
//
//@RunWith(SerenityRunner.class)
//public class PutTestResultsNegAdditionalValidationsLEC {
//
//    @Steps
//    TestResultsSteps testResultsSteps;
//
//    @Steps
//    VehicleTechnicalRecordsSteps vehicleTechnicalRecordsSteps;
//
//
//    @WithTag("Vtm")
//    @Title("CVSB-10300 - AC1 PUT: Attempt to update a test record with a not applicable field - LEC - Negative")
//    @Test
//    public void testResultsPutNotApplicableLec() {
//        // Read the base test result JSON.
//        String postRequestBody = GenericData.readJsonValueFromFile("technical-records_psv_all_fields.json","$");
//
//        // Create alteration to add one more tech record to in the request body
//        String randomSystemNumber = GenericData.generateRandomSystemNumber();
//        String randomVin = GenericData.generateRandomVin();
//        JsonPathAlteration alterationSystemNumberVehicle = new JsonPathAlteration("$.systemNumber", randomSystemNumber, "", "REPLACE");
//        JsonPathAlteration alterationVinVehicle = new JsonPathAlteration("$.vin", randomVin,"","REPLACE");
//
//        // Collate the list of alterations.
//        List<JsonPathAlteration> alterationsVehicle = new ArrayList<>(Arrays.asList(alterationSystemNumberVehicle, alterationVinVehicle));
//
//        // Post the results, together with any alterations, and verify that they are accepted.
//        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBody, alterationsVehicle);
//        vehicleTechnicalRecordsSteps.statusCodeShouldBe(HttpStatus.SC_CREATED);
//        vehicleTechnicalRecordsSteps.validateData("Technical Record created");
//
//        String testResultRecord = GenericData.readJsonValueFromFile("test-results_post_payload_psv_10300.json", "$");
//
//        // Create alteration to add one more tech record to in the request body
//
//        String randomTestResultId = UUID.randomUUID().toString();
//        JsonPathAlteration alterationSystemNumberTestResults = new JsonPathAlteration("$.systemNumber", randomSystemNumber, "", "REPLACE");
//        JsonPathAlteration alterationVinTestResults = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
//        JsonPathAlteration alterationTestResultIdPost = new JsonPathAlteration("$.testResultId", randomTestResultId, "", "REPLACE");
//
//        // Collate the list of alterations.
//        List<JsonPathAlteration> alterationsTestResults = new ArrayList<>(Arrays.asList(
//                alterationSystemNumberTestResults,
//                alterationVinTestResults,
//                alterationTestResultIdPost));
//
//        // Post the results, together with any alterations, and verify that they are accepted.
//        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, alterationsTestResults);
//        testResultsSteps.statusCodeShouldBe(HttpStatus.SC_CREATED);
//        testResultsSteps.validateData("Test records created");
//
//
//        // Read the base JSON for PUT test-results
//        String putRequestBody = GenericData.readJsonValueFromFile("test-results_put_payload_psv_10300.json","$");
//
//        JsonPathAlteration alterationSystemNumberPutTestResults = new JsonPathAlteration("$.testResult.systemNumber", randomSystemNumber, "", "REPLACE");
//        JsonPathAlteration alterationVinPutTestResults = new JsonPathAlteration("$.testResult.vin", randomVin, "", "REPLACE");
//        JsonPathAlteration alterationTestResultIdPut = new JsonPathAlteration("$.testResult.testResultId", randomTestResultId, "", "REPLACE");
//
//        // Collate the list of alterations.
//        List<JsonPathAlteration> alterationsPutTestResults = new ArrayList<>(Arrays.asList(
//                alterationSystemNumberPutTestResults,
//                alterationVinPutTestResults,
//                alterationTestResultIdPut));
//
//        testResultsSteps.putTestResultsWithAlterations(randomSystemNumber,putRequestBody,alterationsPutTestResults);
//        testResultsSteps.statusCodeShouldBe(HttpStatus.SC_BAD_REQUEST);
//        testResultsSteps.validatePostErrorDataContains("numberOfSeatbeltsFitted", "is not allowed");
//        testResultsSteps.validatePostErrorDataContains("seatbeltInstallationCheckDate", "is not allowed");
//        testResultsSteps.validatePostErrorDataContains("lastSeatbeltInstallationCheckDate", "is not allowed");
//        testResultsSteps.validatePostErrorDataContains("testAnniversaryDate", "is not allowed");
//        testResultsSteps.validatePostErrorDataContains("defects", "is not allowed");
//
//
//    }
//}
