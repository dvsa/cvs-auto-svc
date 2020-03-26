package testresults;

import data.GenericData;
import model.testresults.TestResultsStatus;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import steps.TestResultsSteps;
import util.JsonPathAlteration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@RunWith(SerenityRunner.class)
public class PostTestResultsSystemNumber {

    @Steps
    TestResultsSteps testResultsSteps;


    @Title("CVSB-10754 - TC - AC1 - AC2 API Consumer retrieve the Test results for the input systemNumber - Submitted")
    @Test
    public void testResultsAPIConsumerCreatesAndGetTestResultsWithSysNumSubmitted() {

        // Read the base test result JSON.
        String testResultRecord = GenericData.readJsonValueFromFile("test-results_sys_number_10754.json", "$");

        // Create alteration to add one more tech record to in the request body
        String randomSystemNumber = GenericData.generateRandomSystemNumber();
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
        String randomSystemNumber = GenericData.generateRandomSystemNumber();
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
        String randomSystemNumber = GenericData.generateRandomSystemNumber();
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
}
