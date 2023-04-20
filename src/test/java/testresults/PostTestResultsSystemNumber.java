package testresults;

import data.GenericData;
import model.testresults.TestResultsStatus;
import model.testresults.TestVersion;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import steps.*;
import util.JsonPathAlteration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;


@RunWith(SerenityRunner.class)
public class PostTestResultsSystemNumber {

    @Steps
    TestResultsSteps testResultsSteps;

    @Steps
    VehicleTechnicalRecordsSteps vehicleTechnicalRecordsSteps;

    private String test_results_post_expiry_date_trl_8798_json;
    private String test_results_car_json;
    private String test_results_lgv_json;
    private String test_results_trl_json;
    private String test_results_insert_expiry_date_trl_5862_json;
    private String test_results_post_free_loaded_tests_18974_json;
    private String test_results_motorcycle_json;
    private String test_results_first_test_hgv_json;
    private String test_results_psv_annual_test_json;
    DateTime currentTimestamp = DateTime.now().withZone(DateTimeZone.UTC);
    DateTime testEndTimestamp = currentTimestamp.minusYears(1).minusHours(1);

    @Before
    @Test
    public void updateJson(){
        String jsonFileName = "test-results_post_expiry_date_trl_8798.json";
        String jsonFileName1 = "test-results_car.json";
        String jsonFileName2 = "test-results_lgv.json";
        String jsonFileName3 = "test-results_trl.json";
        String jsonFileName4 = "test-results_insert_expiry_date_trl_5862.json";
        String jsonFileName5 = "test-results_post_free_loaded_tests_18974.json";
        String jsonFileName6 = "test-results_motorcycle.json";
        String jsonFileName7 = "test-results_first_test_hgv.json";
        String jsonFileName8 = "test-results_psv_annual_test.json";
        test_results_post_expiry_date_trl_8798_json = GenericData.updateJson( jsonFileName, false);
        test_results_car_json = GenericData.updateJson( jsonFileName1, false);
        test_results_lgv_json = GenericData.updateJson( jsonFileName2, false);
        test_results_trl_json = GenericData.updateJson( jsonFileName3, false);
        test_results_insert_expiry_date_trl_5862_json = GenericData.updateJson( jsonFileName4, false);
        test_results_post_free_loaded_tests_18974_json = GenericData.updateJson( jsonFileName5, false);
        test_results_motorcycle_json = GenericData.updateJson( jsonFileName6, false);
        test_results_first_test_hgv_json = GenericData.updateJson( jsonFileName7, false);
        test_results_psv_annual_test_json = GenericData.updateJson( jsonFileName8, false);
    }

    @Title("CVSB-10754 - TC - AC1 - AC2 API Consumer retrieve the Test results for the input systemNumber - Submitted")
    @Test
    public void testResultsAPIConsumerCreatesAndGetTestResultsWithSysNumSubmitted() {

        // Read the base test result JSON.
        String testResultRecord = GenericData.readJsonValueFromFile("test-results_sys_number_10754.json", "$");

        // Create alteration to add one more tech record to in the request body
        String randomSystemNumber = vehicleTechnicalRecordsSteps.getNextSystemNumberInSequence();
        String randomVin = GenericData.generateRandomVin();
        String randomTestResultId = UUID.randomUUID().toString();
        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$.systemNumber", randomSystemNumber, "", "REPLACE");
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationTestResultId = new JsonPathAlteration("$.testResultId", randomTestResultId, "", "REPLACE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(
                alterationSystemNumber,
                alterationVin,
                alterationTestResultId));

        // Post the results, together with any alterations, and verify that they are accepted.
        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, alterations);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");

        // Get the results , verify the status code and verify the content
        testResultsSteps.getTestResultsSysNumber(randomSystemNumber);
        testResultsSteps.statusCodeShouldBe(200);
        testResultsSteps.valueForFieldInPathShouldBe("[0].testStatus", "submitted");
    }

    @Title("CVSB-10754 - TC - AC1 - AC2 API Consumer retrieve the Test results for the input systemNumber - Cancelled")
    @Test
    public void testResultsAPIConsumerCreatesAndGetTestResultsWithSysNumCancelled() {

        // Read the base test result JSON.
        String testResultRecord = GenericData.readJsonValueFromFile("test-results_sys_number_10754.json", "$");

        // Create alteration to add one more tech record to in the request body
        String randomSystemNumber = vehicleTechnicalRecordsSteps.getNextSystemNumberInSequence();
        String randomVin = GenericData.generateRandomVin();
        String randomTestResultId = UUID.randomUUID().toString();
        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$.systemNumber", randomSystemNumber, "", "REPLACE");
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationTestResultId = new JsonPathAlteration("$.testResultId", randomTestResultId, "", "REPLACE");
        JsonPathAlteration alterationTestStatus = new JsonPathAlteration("$.testStatus", "cancelled", "", "REPLACE");
        JsonPathAlteration alterationReasonForCancellation = new JsonPathAlteration("$.reasonForCancellation", "Testing", "", "REPLACE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(
                alterationSystemNumber,
                alterationVin,
                alterationTestStatus,
                alterationReasonForCancellation,
                alterationTestResultId));

        // Post the results, together with any alterations, and verify that they are accepted.
        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, alterations);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");

        // Get the results , verify the status code and verify the content
        testResultsSteps.getTestResultsWithStatusAndSysNumber(randomSystemNumber,TestResultsStatus.CANCELED);
        testResultsSteps.statusCodeShouldBe(200);
        testResultsSteps.valueForFieldInPathShouldBe("[0].testStatus", "cancelled");

    }

    @Title("CVSB-10754 - TC - AC3 No data found")
    @Test
    public void testResultsSubmittedAndNotExistingSysNumber() {

        // Get the results , verify the error code and error message
        testResultsSteps.getTestResultsSysNumber(RandomStringUtils.randomAlphanumeric(6));
        testResultsSteps.statusCodeShouldBe(404);
        testResultsSteps.validateData("No resources match the search criteria");
    }

    @Title("CVSB-10754 - TC - AC4 API Consumer creates a new test results for the submitted test")
    @Test
    public void testResultsAPIConsumerCreatesNewTestResultsWithSystemNumber() {

        // Read the base test result JSON.
        String testResultRecord = GenericData.readJsonValueFromFile("test-results_sys_number_10754.json", "$");

        // Create alteration to add one more tech record to in the request body
        String randomSystemNumber = vehicleTechnicalRecordsSteps.getNextSystemNumberInSequence();
        String randomVin = GenericData.generateRandomVin();
        String randomTestResultId = UUID.randomUUID().toString();
        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$.systemNumber", randomSystemNumber, "", "REPLACE");
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationTestResultId = new JsonPathAlteration("$.testResultId", randomTestResultId, "", "REPLACE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(
                alterationSystemNumber,
                alterationVin,
                alterationTestResultId));

        // Post the results, together with any alterations, and verify that they are accepted.
        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, alterations);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
    }

    @Title("CVSB-10754 - TC - AC6 When API is authenticated from the API gateway")
    @Test
    public void testResultsNoAuthorised() {

        // Read the base test result JSON.
        String testResultRecord = GenericData.readJsonValueFromFile("test-results_sys_number_10754.json", "$");

        // Post the results and verify the error code and error message
        testResultsSteps.postVehicleTestResultsWithNoAuthorization(testResultRecord);
        testResultsSteps.statusCodeShouldBe(403);
        testResultsSteps.validateMessage("User is not authorized to access this resource with an explicit deny");
    }

    @Title("CVSB-13903 - (IMPROVEMENT)[BE] Specialist tests, updating the test results API specs - vehicleClass mandatory only for motorcycles - AC2 - vehicleClass - mandatory for motorcycle")
    @Test
    public void testVehicleTechRecordMotorcycleVehicleCategoryMissing(){

        // Tech record exists already in dynamoDb with a prep-populated euVehicleCategory
        String systemNumber = vehicleTechnicalRecordsSteps.getNextSystemNumberInSequence();
        String randomVin = GenericData.generateRandomVin();
        String randomTestResultId = UUID.randomUUID().toString();

        // Read test result base json + Generate random values
        String testResultRecord = GenericData.readJsonValueFromFile("test-results_motorcycle.json", "$");
        JsonPathAlteration alterationTestResultVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationTestResultId = new JsonPathAlteration("$.testResultId", randomTestResultId, "", "REPLACE");
        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$.systemNumber", systemNumber,"","REPLACE");
        JsonPathAlteration alterationVehicleClass = new JsonPathAlteration("$.vehicleClass", "","","DELETE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterationsTestResult = new ArrayList<>(Arrays.asList(
                alterationTestResultVin,
                alterationTestResultId,
                alterationSystemNumber,
                alterationVehicleClass));

        // Post the results, together with any alterations, and verify that they are accepted.
        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, alterationsTestResult);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("vehicleClass", "is required");

    }

    @Title("CVSB-13903 - (IMPROVEMENT)[BE] Specialist tests, updating the test results API specs - vehicleClass mandatory only for motorcycles - AC2 - vehicleClass - mandatory for motorcycle")
    @Test
    public void testVehicleTechRecordMotorcycleVehicleCategoryInvalid() {

        // Tech record exists already in dynamoDb with a prep-populated euVehicleCategory
        String systemNumber = vehicleTechnicalRecordsSteps.getNextSystemNumberInSequence();
        String randomVin = GenericData.generateRandomVin();
        String randomTestResultId = UUID.randomUUID().toString();

        // Read test result base json + Generate random values
        String testResultRecord = GenericData.readJsonValueFromFile("test-results_motorcycle.json", "$");
        JsonPathAlteration alterationTestResultVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationTestResultId = new JsonPathAlteration("$.testResultId", randomTestResultId, "", "REPLACE");
        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$.systemNumber", systemNumber, "", "REPLACE");
        JsonPathAlteration alterationVehicleClass = new JsonPathAlteration("$.vehicleClass", null, "", "REPLACE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterationsTestResult = new ArrayList<>(Arrays.asList(
                alterationTestResultVin,
                alterationTestResultId,
                alterationSystemNumber,
                alterationVehicleClass));

        // Post the results, together with any alterations, and verify that they are accepted.
        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, alterationsTestResult);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("vehicleClass", "must be an object");
    }

//    @Title("CVSB-15036 - [BE] Backend service update, to allow testerEmailAddress and testStationType to be updated with new values")
//    @Test
//    public void testTestResultsUpdateTesterEmailAddressAndTestStationType() {
//
//        // CREATE A NEW VEHICLE
//        String postRequestBody = GenericData.readJsonValueFromFile("technical-records_hgv_post_10278.json","$");
//
//        String randomSystemNumber = GenericData.generateRandomSystemNumber();
//        String randomVin = GenericData.generateRandomVin();
//
//        // Create alteration
//        JsonPathAlteration alterationSystemNumberVehicle = new JsonPathAlteration("$.systemNumber", randomSystemNumber, "", "REPLACE");
//        JsonPathAlteration alterationVinVehicle = new JsonPathAlteration("$.vin", randomVin,"","REPLACE");
//
//        // Collate the list of alterations.
//        List<JsonPathAlteration> alterationsVehicle = new ArrayList<>(Arrays.asList(alterationSystemNumberVehicle, alterationVinVehicle));
//
//        // Post the results, together with any alterations, and verify that they are accepted.
//        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBody, alterationsVehicle);
//        vehicleTechnicalRecordsSteps.statusCodeShouldBe(201);
//        vehicleTechnicalRecordsSteps.validateData("Technical Record created");
//
//        // CREATE A TEST-RESULT FOR THE VEHICLE
//
//        String testResultRecord = GenericData.readJsonValueFromFile("test-results_hgv_post_10278.json", "$");
//
//        // Create alteration to add one more tech record to in the request body
//        String randomTestResultId = UUID.randomUUID().toString();
//        String testerEmailAddressPost = "namePost@mailPost.com";
//        String testStationNamePost = "Abshire-Kub";
//        String testStationPNumberPost = "09-4129632";
//        String testStationTypePost = "gvts";
//
//        JsonPathAlteration alterationSystemNumberTestResults = new JsonPathAlteration("$.systemNumber", randomSystemNumber, "", "REPLACE");
//        JsonPathAlteration alterationVinTestResults = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
//        JsonPathAlteration alterationTestResultIdPost = new JsonPathAlteration("$.testResultId", randomTestResultId, "", "REPLACE");
//        JsonPathAlteration alterationTestStationNamePost = new JsonPathAlteration("$.testStationName", testStationNamePost, "", "REPLACE");
//        JsonPathAlteration alterationTestStationPNumberPost = new JsonPathAlteration("$.testStationPNumber", testStationPNumberPost, "", "REPLACE");
//        JsonPathAlteration alterationTestStationTypePost = new JsonPathAlteration("$.testStationType", testStationTypePost, "", "REPLACE");
//        JsonPathAlteration alterationTesterEmailAddressPost = new JsonPathAlteration("$.testerEmailAddress", testerEmailAddressPost, "", "REPLACE");
//
//        // Collate the list of alterations.
//        List<JsonPathAlteration> alterationsTestResults = new ArrayList<>(Arrays.asList(
//                alterationSystemNumberTestResults,
//                alterationVinTestResults,
//                alterationTestResultIdPost,
//                alterationTestStationNamePost,
//                alterationTestStationPNumberPost,
//                alterationTestStationTypePost,
//                alterationTesterEmailAddressPost
//
//        ));
//
//        // Post the results, together with any alterations, and verify that they are accepted.
//        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, alterationsTestResults);
//        testResultsSteps.statusCodeShouldBe(201);
//        testResultsSteps.validateData("Test records created");
//
//
//        // UPDATE THE TEST-RESULT FOR THE VEHICLE
//
//        String putRequestBody = GenericData.readJsonValueFromFile("test-results_hgv_put_10278.json","$");
//        String testerEmailAddressPut = "namePut@mailPut.com";
//        String testStationNamePut = "Jacobson and Sons";
//        String testStationPNumberPut = "14-8817748";
//        String testStationTypePut = "atf";
//
//        JsonPathAlteration alterationSystemNumberPutTestResults = new JsonPathAlteration("$.testResult.systemNumber", randomSystemNumber, "", "REPLACE");
//        JsonPathAlteration alterationVinPutTestResults = new JsonPathAlteration("$.testResult.vin", randomVin, "", "REPLACE");
//        JsonPathAlteration alterationTestResultIdPut = new JsonPathAlteration("$.testResult.testResultId", randomTestResultId, "", "REPLACE");
//        JsonPathAlteration alterationTestStationNamePut = new JsonPathAlteration("$.testResult.testStationName", testStationNamePut, "", "REPLACE");
//        JsonPathAlteration alterationTestStationPNumberPut = new JsonPathAlteration("$.testResult.testStationPNumber", testStationPNumberPut, "", "REPLACE");
//        JsonPathAlteration alterationTestStationTypePut = new JsonPathAlteration("$.testResult.testStationType", testStationTypePut, "", "REPLACE");
//        JsonPathAlteration alterationTesterEmailAddressPut = new JsonPathAlteration("$.testResult.testerEmailAddress", testerEmailAddressPut, "", "REPLACE");
//        JsonPathAlteration alterationCertNumber = new JsonPathAlteration("$.testResult.testTypes[0]", "12345","certificateNumber","ADD_FIELD");
//
//        // Collate the list of alterations.
//        List<JsonPathAlteration> alterationsPutTestResults = new ArrayList<>(Arrays.asList(
//                alterationSystemNumberPutTestResults,
//                alterationVinPutTestResults,
//                alterationTestResultIdPut,
//                alterationTestStationNamePut,
//                alterationTestStationPNumberPut,
//                alterationTestStationTypePut,
//                alterationTesterEmailAddressPut,
//                alterationCertNumber
//                ));
//
//        testResultsSteps.putTestResultsWithAlterations(randomSystemNumber,putRequestBody,alterationsPutTestResults);
//        testResultsSteps.statusCodeShouldBe(200);
//
//        testResultsSteps.getTestResults(randomSystemNumber, TestVersion.ALL, randomTestResultId);
//        testResultsSteps.statusCodeShouldBe(200);
//        testResultsSteps.valueForFieldInPathShouldBe("[0].testVersion","current");
//        testResultsSteps.valueForFieldInPathShouldBe("[0].testStationName",testStationNamePut);
//        testResultsSteps.valueForFieldInPathShouldBe("[0].testStationPNumber", testStationPNumberPut);
//        testResultsSteps.valueForFieldInPathShouldBe("[0].testStationType",testStationTypePut);
//        testResultsSteps.valueForFieldInPathShouldBe("[0].testerEmailAddress",testerEmailAddressPut);
//
//        testResultsSteps.valueForFieldInPathShouldBe("[0].testHistory.size",1);
//        testResultsSteps.valueForFieldInPathShouldBe("[0].testHistory[0].testVersion","archived");
//        testResultsSteps.valueForFieldInPathShouldBe("[0].testHistory[0].testResultId",randomTestResultId);
//        testResultsSteps.valueForFieldInPathShouldBe("[0].testHistory[0].testStationName",testStationNamePost);
//        testResultsSteps.valueForFieldInPathShouldBe("[0].testHistory[0].testStationPNumber",testStationPNumberPost);
//        testResultsSteps.valueForFieldInPathShouldBe("[0].testHistory[0].testStationType",testStationTypePost);
//        testResultsSteps.valueForFieldInPathShouldBe("[0].testHistory[0].testerEmailAddress",testerEmailAddressPost);
//    }

    @Title("CVSB - 17727 - To verify the cert is generated for a TRL with vin having spaces")
    @Test
    public void testCertGenerationForVinWithSpaces() {

        // Read the base test result JSON.
        String testResultRecord = test_results_post_expiry_date_trl_8798_json;

        String randomVin = GenericData.generateRandomVin();
        String randomVinWithSpecialCharacters = "B " + randomVin.substring(5);
        String randomSystemNumber = GenericData.generateRandomSystemNumber();
        String randomTestResultId = UUID.randomUUID().toString();

        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVinWithSpecialCharacters, "", "REPLACE");
        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$.systemNumber", randomSystemNumber, "", "REPLACE");
        JsonPathAlteration alterationTestResultId = new JsonPathAlteration("$.testResultId", randomTestResultId, "", "REPLACE");
        JsonPathAlteration alterationTestName = new JsonPathAlteration("$.testTypes[0].name", "Annual test","","REPLACE");
        JsonPathAlteration alterationTestTypeId = new JsonPathAlteration("$.testTypes[0].testTypeId", "94","","REPLACE");
        JsonPathAlteration alterationTestTypeName = new JsonPathAlteration("$.testTypes[0].testTypeName", "Annual test","","REPLACE");
        JsonPathAlteration alterationTestResult = new JsonPathAlteration("$.testTypes[0].testResult", "pass","","REPLACE");
        JsonPathAlteration alterationNoOfAxles = new JsonPathAlteration("$.noOfAxles", "3","","REPLACE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(
                alterationVin,
                alterationTestResultId,
                alterationTestName,
                alterationTestTypeId,
                alterationTestTypeName,
                alterationNoOfAxles,
                alterationTestResult,
                alterationSystemNumber
        ));

        // Post the results, together with any alterations, and verify that they are accepted.
        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, alterations);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        testResultsSteps.getTestResults(randomSystemNumber);
        testResultsSteps.statusCodeShouldBe(200);
        String testNumber = testResultsSteps.getTestNumber();
        Assert.assertTrue(testResultsSteps.validateCertificateNumberLength());

        //Verify that the certificate is generated in S3 bucket
        testResultsSteps.validateCertificateIsGenerated(testNumber,randomVinWithSpecialCharacters);
    }

    @Title("CVSB-18974 - Not able to submit the test for Free loaded retest - Without History")
    @Test
    public void testTestResultForFreeLoadedTestsWithoutHistory() {

        String testResultRecord = test_results_post_free_loaded_tests_18974_json;

        DateTime currentTime = DateTime.now().withZone(DateTimeZone.UTC);
        String randomVin = GenericData.generateRandomVin();
        String randomSystemNo = GenericData.generateRandomSystemNumber();
        String randomTestResultId = UUID.randomUUID().toString();

        String expectedTestExpiryDate = testEndTimestamp.dayOfMonth().withMaximumValue().plusYears(1).dayOfMonth().withMaximumValue().toInstant().toString();

        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationSysNo = new JsonPathAlteration("$.systemNumber", randomSystemNo, "", "REPLACE");
        JsonPathAlteration alterationTestResultId = new JsonPathAlteration("$.testResultId", randomTestResultId, "", "REPLACE");

        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(
                alterationVin,
                alterationTestResultId,
                alterationSysNo
        ));

        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, alterations);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");

        testResultsSteps.getTestResultsSysNumber(randomSystemNo);
        testResultsSteps.statusCodeShouldBe(200);

        System.out.println("Expected expiryDate: " + expectedTestExpiryDate.substring(0,10));

        testResultsSteps.valueForFieldInPathShouldStartWith("[0].testTypes[0].testExpiryDate", expectedTestExpiryDate.substring(0,10));
    }

    @Title("CVSB-18974 - Not able to submit the test for Free loaded retest - With History")
    @Test
    public void testTestResultForFreeLoadedTestsWithHistory() {

        String insertedTestResultRecord = test_results_post_free_loaded_tests_18974_json;

        DateTime currentTime = DateTime.now().withZone(DateTimeZone.UTC);
        String randomVin = GenericData.generateRandomVin();
        String randomSystemNo = GenericData.generateRandomSystemNumber();
        String randomTestResultId = UUID.randomUUID().toString();

        DateTime insertedTestExpiryDate = testEndTimestamp.dayOfMonth().withMaximumValue().plusYears(1).dayOfMonth().withMaximumValue();
        String insertTestExpiryDate = insertedTestExpiryDate.toInstant().toString();

        JsonPathAlteration alterationInsertVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationInsertSysNo = new JsonPathAlteration("$.systemNumber", randomSystemNo, "", "REPLACE");
        JsonPathAlteration alterationInsertTestResultId = new JsonPathAlteration("$.testResultId", UUID.randomUUID().toString(), "", "REPLACE");
        JsonPathAlteration alterationInsertTestExpiryDate = new JsonPathAlteration("$.testTypes[0].testExpiryDate", insertTestExpiryDate, "", "REPLACE");

        // Collate the list of alterations for the inserted record.
        List<JsonPathAlteration> insertAlterations = new ArrayList<>(Arrays.asList(
                alterationInsertVin,
                alterationInsertTestResultId,
                alterationInsertTestExpiryDate,
                alterationInsertSysNo
        ));

        // Insert the altered record
        String alteredJson = GenericData.applyJsonAlterations(insertedTestResultRecord, insertAlterations);
        testResultsSteps.insertRecordInDynamo(alteredJson, "test-results", "vin");

        String expectedTestExpiryDate = testEndTimestamp.dayOfMonth().withMaximumValue().plusYears(1).dayOfMonth().withMaximumValue().toInstant().toString();


        String testResultRecord = GenericData.readJsonValueFromFile("test-results_post_free_loaded_tests_18974.json", "$");

        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationSysNo = new JsonPathAlteration("$.systemNumber", randomSystemNo, "", "REPLACE");
        JsonPathAlteration alterationTestResultId = new JsonPathAlteration("$.testResultId", randomTestResultId, "", "REPLACE");

        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(
                alterationVin,
                alterationTestResultId,
                alterationSysNo
        ));

        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, alterations);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");

        testResultsSteps.getTestResultsSysNumber(randomSystemNo);
        testResultsSteps.statusCodeShouldBe(200);
        System.out.println("Expected expiryDate: " + expectedTestExpiryDate.substring(0,10));

        testResultsSteps.valueForFieldInPathShouldStartWith("[0].testTypes[0].testExpiryDate", expectedTestExpiryDate.substring(0,10));
    }

    @Title("VOTT-15 - AC1 - When a test-result is created, I want a new certificate to be produced - HGV")
    @Test
    public void testCreateTestResultGenerateCertHgv() {

        // Read the base test result JSON.
        String testResultRecord = test_results_first_test_hgv_json;

        String randomVin = GenericData.generateRandomVin();
        String randomSystemNumber = GenericData.generateRandomSystemNumber();
        String randomTestResultId = UUID.randomUUID().toString();

        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$.systemNumber", randomSystemNumber, "", "REPLACE");
        JsonPathAlteration alterationTestResultId = new JsonPathAlteration("$.testResultId", randomTestResultId, "", "REPLACE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(
                alterationVin,
                alterationTestResultId,
                alterationSystemNumber
        ));

        // Post the results, together with any alterations, and verify that they are accepted.
        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, alterations);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        testResultsSteps.getTestResults(randomSystemNumber);
        testResultsSteps.statusCodeShouldBe(200);
        String testNumber = testResultsSteps.getTestNumber();

        System.out.println("testNumber is " +testNumber);

        //Verify that the certificate is generated in S3 bucket
        //testResultsSteps.validateCertificateIsGenerated(testNumber,randomVin);
    }


    @Title("VOTT-15 - AC1 - When a test-result is created, I want a new certificate to be produced - PSV")
    @Test
    public void testCreateTestResultGenerateCertPsv() {

        // Read the base test result JSON.
        String testResultRecord = test_results_psv_annual_test_json;

        String randomVin = GenericData.generateRandomVin();
        String randomSystemNumber = GenericData.generateRandomSystemNumber();
        String randomTestResultId = UUID.randomUUID().toString();

        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$.systemNumber", randomSystemNumber, "", "REPLACE");
        JsonPathAlteration alterationTestResultId = new JsonPathAlteration("$.testResultId", randomTestResultId, "", "REPLACE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(
                alterationVin,
                alterationTestResultId,
                alterationSystemNumber
        ));

        // Post the results, together with any alterations, and verify that they are accepted.
        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, alterations);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        testResultsSteps.getTestResults(randomSystemNumber);
        testResultsSteps.statusCodeShouldBe(200);
        String testNumber = testResultsSteps.getTestNumber();

        System.out.println("testNumber is " +testNumber);

        //Verify that the certificate is generated in S3 bucket
        testResultsSteps.validateCertificateIsGenerated(testNumber,randomVin);
    }

    @Title("VOTT-15 - AC1 - When a test-result is created, I want a new certificate to be produced - TRL")
    @Test
    public void testCreateTestResultGenerateCertTrl() {

        // Read the base test result JSON.
        String testResultRecord = test_results_trl_json;

        String randomVin = GenericData.generateRandomVin();
        String randomSystemNumber = GenericData.generateRandomSystemNumber();
        String randomTestResultId = UUID.randomUUID().toString();

        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$.systemNumber", randomSystemNumber, "", "REPLACE");
        JsonPathAlteration alterationTestResultId = new JsonPathAlteration("$.testResultId", randomTestResultId, "", "REPLACE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(
                alterationVin,
                alterationTestResultId,
                alterationSystemNumber
        ));

        // Post the results, together with any alterations, and verify that they are accepted.
        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, alterations);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        testResultsSteps.getTestResults(randomSystemNumber);
        testResultsSteps.statusCodeShouldBe(200);
        String testNumber = testResultsSteps.getTestNumber();

        System.out.println("testNumber is " +testNumber);

        //Verify that the certificate is generated in S3 bucket
        testResultsSteps.validateCertificateIsGenerated(testNumber,randomVin);
    }

    @Title("VOTT-15 - AC1 - When a test-result is created, I want a new certificate to be produced - Car")
    @Test
    public void testCreateTestResultGenerateCertCar() {

        // Read the base test result JSON.
        String testResultRecord = test_results_car_json;

        String randomVin = GenericData.generateRandomVin();
        String randomSystemNumber = GenericData.generateRandomSystemNumber();
        String randomTestResultId = UUID.randomUUID().toString();

        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$.systemNumber", randomSystemNumber, "", "REPLACE");
        JsonPathAlteration alterationTestResultId = new JsonPathAlteration("$.testResultId", randomTestResultId, "", "REPLACE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(
                alterationVin,
                alterationTestResultId,
                alterationSystemNumber
        ));

        // Post the results, together with any alterations, and verify that they are accepted.
        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, alterations);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        testResultsSteps.getTestResults(randomSystemNumber);
        testResultsSteps.statusCodeShouldBe(200);
        String testNumber = testResultsSteps.getTestNumber();

        System.out.println("testNumber is " +testNumber);

        //Verify that the certificate is generated in S3 bucket
        testResultsSteps.validateCertificateIsNotGenerated(testNumber,randomVin);
    }

    @Title("VOTT-15 - AC1 - When a test-result is created, I want a new certificate to be produced - Lgv")
    @Test
    public void testCreateTestResultGenerateCertLgv() {

        // Read the base test result JSON.
        String testResultRecord = test_results_lgv_json;

        String randomVin = GenericData.generateRandomVin();
        String randomSystemNumber = GenericData.generateRandomSystemNumber();
        String randomTestResultId = UUID.randomUUID().toString();

        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$.systemNumber", randomSystemNumber, "", "REPLACE");
        JsonPathAlteration alterationTestResultId = new JsonPathAlteration("$.testResultId", randomTestResultId, "", "REPLACE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(
                alterationVin,
                alterationTestResultId,
                alterationSystemNumber
        ));

        // Post the results, together with any alterations, and verify that they are accepted.
        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, alterations);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        testResultsSteps.getTestResults(randomSystemNumber);
        testResultsSteps.statusCodeShouldBe(200);
        String testNumber = testResultsSteps.getTestNumber();

        System.out.println("testNumber is " +testNumber);

        //Verify that the certificate is generated in S3 bucket
        testResultsSteps.validateCertificateIsNotGenerated(testNumber,randomVin);
    }

    @Title("VOTT-15 - AC1 - When a test-result is created, I want a new certificate to be produced - Motorcycle")
    @Test
    public void testCreateTestResultGenerateCertMotorcycle() {

        // Read the base test result JSON.
        String testResultRecord = test_results_motorcycle_json;

        String randomVin = GenericData.generateRandomVin();
        String randomSystemNumber = GenericData.generateRandomSystemNumber();
        String randomTestResultId = UUID.randomUUID().toString();

        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$.systemNumber", randomSystemNumber, "", "REPLACE");
        JsonPathAlteration alterationTestResultId = new JsonPathAlteration("$.testResultId", randomTestResultId, "", "REPLACE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(
                alterationVin,
                alterationTestResultId,
                alterationSystemNumber
        ));

        // Post the results, together with any alterations, and verify that they are accepted.
        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, alterations);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        testResultsSteps.getTestResults(randomSystemNumber);
        testResultsSteps.statusCodeShouldBe(200);
        String testNumber = testResultsSteps.getTestNumber();

        System.out.println("testNumber is " +testNumber);

        //Verify that the certificate is generated in S3 bucket
        testResultsSteps.validateCertificateIsNotGenerated(testNumber,randomVin);
    }
}