package testresults;

import data.TestResultsData;
import data.GenericData;
import model.testresults.TestResults;
import model.testresults.TestResultsGet;
import model.testresults.TestResultsStatus;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import steps.TestResultsSteps;
import java.util.UUID;
import util.JsonPathAlteration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import java.util.Random;

import static util.DataUtil.generateRandomExcludingValues;


@RunWith(SerenityRunner.class)
public class PostTestResultsPozLocationSubmitted {

    @Steps
    TestResultsSteps testResultsSteps;

    private TestResults.Builder vehicleSubmittedDataOld = TestResultsData.buildTestResultsSubmittedDataOld();

    private void validateSavedDataOld() {
        testResultsSteps.getTestResults(vehicleSubmittedDataOld.build().getSystemNumber(), TestResultsStatus.SUBMITTED);
        testResultsSteps.statusCodeShouldBe(200);
        testResultsSteps.validateData((TestResultsGet) vehicleSubmittedDataOld.build());
    }

    private String test_results_post_payload_psv_10300_json;
    @Before
    @Test
    public void updateJson(){
        String jsonFileName = "test-results_post_payload_psv_10300.json";
        test_results_post_payload_psv_10300_json = GenericData.updateJson(testResultsSteps, jsonFileName, "$");
    }
    
    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - vertical upper")
    @Test
    public void testResultsUpperVertical() {

        // Read the base test result JSON.
        String testResultRecord = test_results_post_payload_psv_10300_json;

        // Create alteration to add one more tech record to in the request body
        String randomVin = GenericData.generateRandomVin();
        String randomTestResultId = UUID.randomUUID().toString();
        String randomSystemNumber = GenericData.generateRandomSystemNumber();
        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$", randomSystemNumber,"systemNumber","ADD_FIELD");
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationTestResultId = new JsonPathAlteration("$.testResultId", randomTestResultId, "", "REPLACE");
        JsonPathAlteration alterationVertical = new JsonPathAlteration("$.testTypes[0].defects[0].additionalInformation.location.vertical", "upper", "", "REPLACE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(
                alterationVin,
                alterationSystemNumber,
                alterationVertical,
                alterationTestResultId));

        // Post the results, together with any alterations, and verify that they are accepted.
        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, alterations);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - vertical lower")
    @Test
    public void testResultsLowerVertical() {

        // Read the base test result JSON.
        String testResultRecord = test_results_post_payload_psv_10300_json;

        // Create alteration to add one more tech record to in the request body
        String randomVin = GenericData.generateRandomVin();
        String randomTestResultId = UUID.randomUUID().toString();
        String randomSystemNumber = GenericData.generateRandomSystemNumber();
        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$", randomSystemNumber,"systemNumber","ADD_FIELD");
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationTestResultId = new JsonPathAlteration("$.testResultId", randomTestResultId, "", "REPLACE");
        JsonPathAlteration alterationVertical = new JsonPathAlteration("$.testTypes[0].defects[0].additionalInformation.location.vertical", "lower", "", "REPLACE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(
                alterationVin,
                alterationSystemNumber,
                alterationVertical,
                alterationTestResultId));

        // Post the results, together with any alterations, and verify that they are accepted.
        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, alterations);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3504 - TCD - API Consumer creates a new test result for submitted/canceled that allows null values - vertical")
    @Test
    public void testResultsNullVertical() {

        // Read the base test result JSON.
        String testResultRecord = test_results_post_payload_psv_10300_json;

        // Create alteration to add one more tech record to in the request body
        String randomVin = GenericData.generateRandomVin();
        String randomTestResultId = UUID.randomUUID().toString();
        String randomSystemNumber = GenericData.generateRandomSystemNumber();
        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$", randomSystemNumber,"systemNumber","ADD_FIELD");
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationTestResultId = new JsonPathAlteration("$.testResultId", randomTestResultId, "", "REPLACE");
        JsonPathAlteration alterationVertical = new JsonPathAlteration("$.testTypes[0].defects[0].additionalInformation.location.vertical", null, "", "REPLACE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(
                alterationVin,
                alterationSystemNumber,
                alterationVertical,
                alterationTestResultId));

        // Post the results, together with any alterations, and verify that they are accepted.
        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, alterations);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");



    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - horizontal inner")
    @Test
    public void testResultsInnerHorizontal() {

        // Read the base test result JSON.
        String testResultRecord = test_results_post_payload_psv_10300_json;

        // Create alteration to add one more tech record to in the request body
        String randomVin = GenericData.generateRandomVin();
        String randomTestResultId = UUID.randomUUID().toString();
        String randomSystemNumber = GenericData.generateRandomSystemNumber();
        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$", randomSystemNumber,"systemNumber","ADD_FIELD");
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationTestResultId = new JsonPathAlteration("$.testResultId", randomTestResultId, "", "REPLACE");
        JsonPathAlteration alterationHorizontal = new JsonPathAlteration("$.testTypes[0].defects[0].additionalInformation.location.horizontal", "inner", "", "REPLACE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(
                alterationVin,
                alterationSystemNumber,
                alterationHorizontal,
                alterationTestResultId));

        // Post the results, together with any alterations, and verify that they are accepted.
        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, alterations);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");

    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - horizontal outer")
    @Test
    public void testResultsOuterHorizontal() {

        // Read the base test result JSON.
        String testResultRecord = test_results_post_payload_psv_10300_json;

        // Create alteration to add one more tech record to in the request body
        String randomVin = GenericData.generateRandomVin();
        String randomTestResultId = UUID.randomUUID().toString();
        String randomSystemNumber = GenericData.generateRandomSystemNumber();
        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$", randomSystemNumber,"systemNumber","ADD_FIELD");
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationTestResultId = new JsonPathAlteration("$.testResultId", randomTestResultId, "", "REPLACE");
        JsonPathAlteration alterationHorizontal = new JsonPathAlteration("$.testTypes[0].defects[0].additionalInformation.location.horizontal", "outer", "", "REPLACE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(
                alterationVin,
                alterationSystemNumber,
                alterationHorizontal,
                alterationTestResultId));

        // Post the results, together with any alterations, and verify that they are accepted.
        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, alterations);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");



    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3504 - TCD - API Consumer creates a new test result for submitted/canceled that allows null values - horizontal")
    @Test
    public void testResultsNullHorizontal() {

        // Read the base test result JSON.
        String testResultRecord = test_results_post_payload_psv_10300_json;

        // Create alteration to add one more tech record to in the request body
        String randomVin = GenericData.generateRandomVin();
        String randomTestResultId = UUID.randomUUID().toString();
        String randomSystemNumber = GenericData.generateRandomSystemNumber();
        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$", randomSystemNumber,"systemNumber","ADD_FIELD");
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationTestResultId = new JsonPathAlteration("$.testResultId", randomTestResultId, "", "REPLACE");
        JsonPathAlteration alterationHorizontal = new JsonPathAlteration("$.testTypes[0].defects[0].additionalInformation.location.horizontal", null, "", "REPLACE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(
                alterationVin,
                alterationSystemNumber,
                alterationHorizontal,
                alterationTestResultId));

        // Post the results, together with any alterations, and verify that they are accepted.
        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, alterations);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");

    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - lateral nearside")
    @Test
    public void testResultsNearsideLateral() {

        // Read the base test result JSON.
        String testResultRecord = test_results_post_payload_psv_10300_json;

        // Create alteration to add one more tech record to in the request body
        String randomVin = GenericData.generateRandomVin();
        String randomTestResultId = UUID.randomUUID().toString();
        String randomSystemNumber = GenericData.generateRandomSystemNumber();
        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$", randomSystemNumber,"systemNumber","ADD_FIELD");
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationTestResultId = new JsonPathAlteration("$.testResultId", randomTestResultId, "", "REPLACE");
        JsonPathAlteration alterationLateral = new JsonPathAlteration("$.testTypes[0].defects[0].additionalInformation.location.lateral", "nearside", "", "REPLACE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(
                alterationVin,
                alterationSystemNumber,
                alterationLateral,
                alterationTestResultId));

        // Post the results, together with any alterations, and verify that they are accepted.
        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, alterations);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");

    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - lateral centre")
    @Test
    public void testResultsCentreLateral() {

        // Read the base test result JSON.
        String testResultRecord = test_results_post_payload_psv_10300_json;

        // Create alteration to add one more tech record to in the request body
        String randomVin = GenericData.generateRandomVin();
        String randomTestResultId = UUID.randomUUID().toString();
        String randomSystemNumber = GenericData.generateRandomSystemNumber();
        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$", randomSystemNumber,"systemNumber","ADD_FIELD");
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationTestResultId = new JsonPathAlteration("$.testResultId", randomTestResultId, "", "REPLACE");
        JsonPathAlteration alterationLateral = new JsonPathAlteration("$.testTypes[0].defects[0].additionalInformation.location.lateral", "centre", "", "REPLACE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(
                alterationVin,
                alterationSystemNumber,
                alterationLateral,
                alterationTestResultId));

        // Post the results, together with any alterations, and verify that they are accepted.
        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, alterations);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");

    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - lateral offside")
    @Test
    public void testResultsOffsideLateral() {

        // Read the base test result JSON.
        String testResultRecord = test_results_post_payload_psv_10300_json;

        // Create alteration to add one more tech record to in the request body
        String randomVin = GenericData.generateRandomVin();
        String randomTestResultId = UUID.randomUUID().toString();
        String randomSystemNumber = GenericData.generateRandomSystemNumber();
        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$", randomSystemNumber,"systemNumber","ADD_FIELD");
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationTestResultId = new JsonPathAlteration("$.testResultId", randomTestResultId, "", "REPLACE");
        JsonPathAlteration alterationLateral = new JsonPathAlteration("$.testTypes[0].defects[0].additionalInformation.location.lateral", "offside", "", "REPLACE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(
                alterationVin,
                alterationSystemNumber,
                alterationLateral,
                alterationTestResultId));

        // Post the results, together with any alterations, and verify that they are accepted.
        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, alterations);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");

    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3504 - TCD - API Consumer creates a new test result for submitted/canceled that allows null values - lateral")
    @Test
    public void testResultsNullLateral() {

        // Read the base test result JSON.
        String testResultRecord = test_results_post_payload_psv_10300_json;

        // Create alteration to add one more tech record to in the request body
        String randomVin = GenericData.generateRandomVin();
        String randomTestResultId = UUID.randomUUID().toString();
        String randomSystemNumber = GenericData.generateRandomSystemNumber();
        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$", randomSystemNumber,"systemNumber","ADD_FIELD");
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationTestResultId = new JsonPathAlteration("$.testResultId", randomTestResultId, "", "REPLACE");
        JsonPathAlteration alterationLateral = new JsonPathAlteration("$.testTypes[0].defects[0].additionalInformation.location.lateral", null, "", "REPLACE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(
                alterationVin,
                alterationSystemNumber,
                alterationLateral,
                alterationTestResultId));

        // Post the results, together with any alterations, and verify that they are accepted.
        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, alterations);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");

    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - longitudinal front")
    @Test
    public void testResultsFrontLongitudinal() {

        // Read the base test result JSON.
        String testResultRecord = test_results_post_payload_psv_10300_json;

        // Create alteration to add one more tech record to in the request body
        String randomVin = GenericData.generateRandomVin();
        String randomTestResultId = UUID.randomUUID().toString();
        String randomSystemNumber = GenericData.generateRandomSystemNumber();
        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$", randomSystemNumber,"systemNumber","ADD_FIELD");
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationTestResultId = new JsonPathAlteration("$.testResultId", randomTestResultId, "", "REPLACE");
        JsonPathAlteration alterationLongitudinal = new JsonPathAlteration("$.testTypes[0].defects[0].additionalInformation.location.longitudinal", "front", "", "REPLACE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(
                alterationVin,
                alterationSystemNumber,
                alterationLongitudinal,
                alterationTestResultId));

        // Post the results, together with any alterations, and verify that they are accepted.
        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, alterations);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");

    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - longitudinal rear")
    @Test
    public void testResultsRearLongitudinal() {

        // Read the base test result JSON.
        String testResultRecord = test_results_post_payload_psv_10300_json;

        // Create alteration to add one more tech record to in the request body
        String randomVin = GenericData.generateRandomVin();
        String randomTestResultId = UUID.randomUUID().toString();
        String randomSystemNumber = GenericData.generateRandomSystemNumber();
        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$", randomSystemNumber,"systemNumber","ADD_FIELD");
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationTestResultId = new JsonPathAlteration("$.testResultId", randomTestResultId, "", "REPLACE");
        JsonPathAlteration alterationLongitudinal = new JsonPathAlteration("$.testTypes[0].defects[0].additionalInformation.location.longitudinal", "rear", "", "REPLACE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(
                alterationVin,
                alterationSystemNumber,
                alterationLongitudinal,
                alterationTestResultId));

        // Post the results, together with any alterations, and verify that they are accepted.
        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, alterations);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");

    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3504 - TCD - API Consumer creates a new test result for submitted/canceled that allows null values - longitudinal")
    @Test
    public void testResultsNullLongitudinal() {

        // Read the base test result JSON.
        String testResultRecord = test_results_post_payload_psv_10300_json;

        // Create alteration to add one more tech record to in the request body
        String randomVin = GenericData.generateRandomVin();
        String randomTestResultId = UUID.randomUUID().toString();
        String randomSystemNumber = GenericData.generateRandomSystemNumber();
        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$", randomSystemNumber,"systemNumber","ADD_FIELD");
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationTestResultId = new JsonPathAlteration("$.testResultId", randomTestResultId, "", "REPLACE");
        JsonPathAlteration alterationLongitudinal = new JsonPathAlteration("$.testTypes[0].defects[0].additionalInformation.location.longitudinal", null, "", "REPLACE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(
                alterationVin,
                alterationSystemNumber,
                alterationLongitudinal,
                alterationTestResultId));

        // Post the results, together with any alterations, and verify that they are accepted.
        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, alterations);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");

    }


    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - rowNumber")
    @Test
    public void testResultsRandomValidRowNumber() {

        // Read the base test result JSON.
        String testResultRecord = test_results_post_payload_psv_10300_json;

        // Create alteration to add one more tech record to in the request body
        String randomVin = GenericData.generateRandomVin();
        String randomTestResultId = UUID.randomUUID().toString();
        String randomSystemNumber = GenericData.generateRandomSystemNumber();
        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$", randomSystemNumber,"systemNumber","ADD_FIELD");
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationTestResultId = new JsonPathAlteration("$.testResultId", randomTestResultId, "", "REPLACE");
        JsonPathAlteration alterationRowNumber = new JsonPathAlteration("$.testTypes[0].defects[0].additionalInformation.location.rowNumber", new Random().nextInt(21), "", "REPLACE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(
                alterationVin,
                alterationSystemNumber,
                alterationRowNumber,
                alterationTestResultId));

        // Post the results, together with any alterations, and verify that they are accepted.
        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, alterations);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");

    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3504 - TCD - API Consumer creates a new test result for submitted/canceled that allows null values - rowNumber")
    @Test
    public void testResultsNullRowNumber() {

        // Read the base test result JSON.
        String testResultRecord = test_results_post_payload_psv_10300_json;

        // Create alteration to add one more tech record to in the request body
        String randomVin = GenericData.generateRandomVin();
        String randomTestResultId = UUID.randomUUID().toString();
        String randomSystemNumber = GenericData.generateRandomSystemNumber();
        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$", randomSystemNumber,"systemNumber","ADD_FIELD");
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationTestResultId = new JsonPathAlteration("$.testResultId", randomTestResultId, "", "REPLACE");
        JsonPathAlteration alterationRowNumber = new JsonPathAlteration("$.testTypes[0].defects[0].additionalInformation.location.rowNumber", null, "", "REPLACE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(
                alterationVin,
                alterationSystemNumber,
                alterationRowNumber,
                alterationTestResultId));

        // Post the results, together with any alterations, and verify that they are accepted.
        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, alterations);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");

    }


    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - seatNumber")
    @Test
    public void testResultsRandomValidSeatNumber() {

        // Read the base test result JSON.
        String testResultRecord = test_results_post_payload_psv_10300_json;

        // Create alteration to add one more tech record to in the request body
        String randomVin = GenericData.generateRandomVin();
        String randomTestResultId = UUID.randomUUID().toString();
        String randomSystemNumber = GenericData.generateRandomSystemNumber();
        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$", randomSystemNumber,"systemNumber","ADD_FIELD");
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationTestResultId = new JsonPathAlteration("$.testResultId", randomTestResultId, "", "REPLACE");
        JsonPathAlteration alterationSeatNumber = new JsonPathAlteration("$.testTypes[0].defects[0].additionalInformation.location.seatNumber", new Random().nextInt(7), "", "REPLACE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(
                alterationVin,
                alterationSystemNumber,
                alterationSeatNumber,
                alterationTestResultId));

        // Post the results, together with any alterations, and verify that they are accepted.
        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, alterations);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3504 - TCD - API Consumer creates a new test result for submitted/canceled that allows null values - seatNumber")
    @Test
    public void testResultsNullSeatNumber() {

        // Read the base test result JSON.
        String testResultRecord = test_results_post_payload_psv_10300_json;

        // Create alteration to add one more tech record to in the request body
        String randomVin = GenericData.generateRandomVin();
        String randomTestResultId = UUID.randomUUID().toString();
        String randomSystemNumber = GenericData.generateRandomSystemNumber();
        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$", randomSystemNumber,"systemNumber","ADD_FIELD");
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationTestResultId = new JsonPathAlteration("$.testResultId", randomTestResultId, "", "REPLACE");
        JsonPathAlteration alterationSeatNumber = new JsonPathAlteration("$.testTypes[0].defects[0].additionalInformation.location.seatNumber", null, "", "REPLACE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(
                alterationVin,
                alterationSystemNumber,
                alterationSeatNumber,
                alterationTestResultId));

        // Post the results, together with any alterations, and verify that they are accepted.
        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, alterations);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");

    }


    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - axleNumber")
    @Test
    public void testResultsRandomValidAxleNumber() {

        // Read the base test result JSON.
        String testResultRecord = test_results_post_payload_psv_10300_json;

        // Create alteration to add one more tech record to in the request body
        String randomVin = GenericData.generateRandomVin();
        String randomTestResultId = UUID.randomUUID().toString();
        String randomSystemNumber = GenericData.generateRandomSystemNumber();
        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$", randomSystemNumber,"systemNumber","ADD_FIELD");
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationTestResultId = new JsonPathAlteration("$.testResultId", randomTestResultId, "", "REPLACE");
        JsonPathAlteration alterationAxleNumber = new JsonPathAlteration("$.testTypes[0].defects[0].additionalInformation.location.axleNumber", new Random().nextInt(11), "", "REPLACE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(
                alterationVin,
                alterationSystemNumber,
                alterationAxleNumber,
                alterationTestResultId));

        // Post the results, together with any alterations, and verify that they are accepted.
        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, alterations);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");

    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3504 - TCD - API Consumer creates a new test result for submitted/canceled that allows null values - axleNumber")
    @Test
    public void testResultsNullAxleNumber() {

        // Read the base test result JSON.
        String testResultRecord = test_results_post_payload_psv_10300_json;

        // Create alteration to add one more tech record to in the request body
        String randomVin = GenericData.generateRandomVin();
        String randomTestResultId = UUID.randomUUID().toString();
        String randomSystemNumber = GenericData.generateRandomSystemNumber();
        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$", randomSystemNumber,"systemNumber","ADD_FIELD");
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationTestResultId = new JsonPathAlteration("$.testResultId", randomTestResultId, "", "REPLACE");
        JsonPathAlteration alterationAxleNumber = new JsonPathAlteration("$.testTypes[0].defects[0].additionalInformation.location.axleNumber", null, "", "REPLACE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(
                alterationVin,
                alterationSystemNumber,
                alterationAxleNumber,
                alterationTestResultId));

        // Post the results, together with any alterations, and verify that they are accepted.
        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, alterations);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");

    }
}
