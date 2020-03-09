package testresults;

import data.GenericData;
import data.TestResultsData;
import model.testresults.TestResults;
import model.testresults.TestResultsGet;
import model.testresults.TestResultsStatus;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.junit.Test;
import org.junit.runner.RunWith;
import util.JsonPathAlteration;
import steps.TestResultsSteps;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@RunWith(SerenityRunner.class)
public class GetTestResultsApiSpecs {

    @Steps
    TestResultsSteps testResultsSteps;

    private TestResults.Builder vehicleSubmittedData = TestResultsData.buildTestResultsSubmittedData();

    private void validateSavedData(List<String> data) {

        testResultsSteps.getTestResults(vehicleSubmittedData.build().getVin(), TestResultsStatus.SUBMITTED);
        testResultsSteps.statusCodeShouldBe(200);
        testResultsSteps.validateData((TestResultsGet) vehicleSubmittedData.build());
        testResultsSteps.validateDataForExpiry(data);
    }

    @Title("CVSB-8380 - Iteration on test results API specs to cover the additional LEC test details fields")
    @Test
    public void testPostTestResultsUsingSystemNumberAndGetResults() {

        // Read the base test result JSON.
        String testResultRecord = GenericData.readJsonValueFromFile("test-results_CVSB-10220.json","$");
        String testStartTimestamp = DateTime.now().withZone(DateTimeZone.UTC).minusMinutes(1000).toString();

        // AC4 API Consumer creates a new test results for the submitted test
        // Create alteration to add one more tech record to in the request body
        String randomSystemNumber = GenericData.generateRandomSystemNumber();
        String randomVin = GenericData.generateRandomVin();
        String particulateTrapFitted = "My particulate trap";
        String particulateTrapSerialNumber = "PTSerialNo";
        String modificationTypeUsed = "My modification type";
        String smokeTestKLimitApplied = "123.456";

        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin,"","REPLACE");
        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$.systemNumber", randomSystemNumber,"","REPLACE");
        JsonPathAlteration alterationParticulateTrapFitted = new JsonPathAlteration("$.testTypes[0].particulateTrapFitted", particulateTrapFitted,"","REPLACE");
        JsonPathAlteration alterationParticulateTrapSerialNumber = new JsonPathAlteration("$.testTypes[0].particulateTrapSerialNumber", particulateTrapSerialNumber,"","REPLACE");
        JsonPathAlteration alterationModificationTypeUsed = new JsonPathAlteration("$.testTypes[0].modificationTypeUsed", modificationTypeUsed,"","REPLACE");
        JsonPathAlteration alterationSmokeTestKLimitApplied = new JsonPathAlteration("$.testTypes[0].smokeTestKLimitApplied", smokeTestKLimitApplied,"","REPLACE");
        JsonPathAlteration alterationTestStartTimestamp = new JsonPathAlteration("$.testStartTimestamp", DateTime.now().withZone(DateTimeZone.UTC).toString(), "", "REPLACE");
        JsonPathAlteration alterationTestEndTimestamp = new JsonPathAlteration("$.testEndTimestamp", DateTime.now().withZone(DateTimeZone.UTC).plusMinutes(10).toString(), "", "REPLACE");
        JsonPathAlteration alterationTestResultId = new JsonPathAlteration("$.testResultId", UUID.randomUUID().toString(), "", "REPLACE");

        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(
                alterationVin,
                alterationTestResultId,
                alterationSystemNumber,
                alterationParticulateTrapFitted,
                alterationTestStartTimestamp,
                alterationTestEndTimestamp,
                alterationParticulateTrapSerialNumber,
                alterationModificationTypeUsed,
                alterationSmokeTestKLimitApplied));

        // Post the results, together with any alterations, and verify that they are accepted.
        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, alterations);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");

        //AC1 Consumer able to retrieve Test Results by input systemNumber
        testResultsSteps.getTestResults(randomSystemNumber);
        testResultsSteps.statusCodeShouldBe(200);

        // AC1 API Consumer retrieve the Test results for the recently generated vin as well as specified time fr
        // Retrieve the created record, and verify that the test records are returned.
        testResultsSteps.getTestResultsFromDateAndSysNum(randomSystemNumber, testStartTimestamp);
        testResultsSteps.statusCodeShouldBe(200);

        //AC2 No data found with incorrect System Number
        testResultsSteps.getTestResults("126e758367432956473819543h158o431");
        testResultsSteps.statusCodeShouldBe(404);


    }


    @Title("CVSB-8380 - Iteration on test results API specs to cover the additional LEC test details fields")
    @Test
    public void testPostTestResultsUsingSystemNumberAndGetResultsAmendedConfig() {

        // Read the base test result JSON.
        //Amended JSON Config file contains 2 custom defect, vehicle subClass added, no of wheel driven removed and vehicleType and EUVehicleCategory values changed
        String testResultRecord = GenericData.readJsonValueFromFile("test-results_CVSB-10220-Amended.json","$");
        String testStartTimestamp = DateTime.now().withZone(DateTimeZone.UTC).minusMinutes(1000).toString();

        // Create alteration to add one more tech record to in the request body
        String randomSystemNumber = GenericData.generateRandomSystemNumber();
        String randomVin = GenericData.generateRandomVin();
        String particulateTrapFitted = "My particulate trap";
        String particulateTrapSerialNumber = "PTSerialNo";
        String modificationTypeUsed = "My modification type";
        String smokeTestKLimitApplied = "123.456";

        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin,"","REPLACE");
        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$.systemNumber", randomSystemNumber,"","REPLACE");
        JsonPathAlteration alterationParticulateTrapFitted = new JsonPathAlteration("$.testTypes[0].particulateTrapFitted", particulateTrapFitted,"","REPLACE");
        JsonPathAlteration alterationParticulateTrapSerialNumber = new JsonPathAlteration("$.testTypes[0].particulateTrapSerialNumber", particulateTrapSerialNumber,"","REPLACE");
        JsonPathAlteration alterationModificationTypeUsed = new JsonPathAlteration("$.testTypes[0].modificationTypeUsed", modificationTypeUsed,"","REPLACE");
        JsonPathAlteration alterationSmokeTestKLimitApplied = new JsonPathAlteration("$.testTypes[0].smokeTestKLimitApplied", smokeTestKLimitApplied,"","REPLACE");
        JsonPathAlteration alterationTestStartTimestamp = new JsonPathAlteration("$.testStartTimestamp", DateTime.now().withZone(DateTimeZone.UTC).toString(), "", "REPLACE");
        JsonPathAlteration alterationTestEndTimestamp = new JsonPathAlteration("$.testEndTimestamp", DateTime.now().withZone(DateTimeZone.UTC).plusMinutes(10).toString(), "", "REPLACE");
        JsonPathAlteration alterationTestResultId = new JsonPathAlteration("$.testResultId", UUID.randomUUID().toString(), "", "REPLACE");

        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(
                alterationVin,
                alterationTestResultId,
                alterationSystemNumber,
                alterationParticulateTrapFitted,
                alterationTestStartTimestamp,
                alterationTestEndTimestamp,
                alterationParticulateTrapSerialNumber,
                alterationModificationTypeUsed,
                alterationSmokeTestKLimitApplied));

        // AC3 Post the results, together with any alterations, and verify that they are accepted.
        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, alterations);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");

        //AC1 Consumer able to retrieve Test Results by input systemNumber
        testResultsSteps.getTestResults(randomSystemNumber);
        testResultsSteps.statusCodeShouldBe(200);

        // AC1 API Consumer retrieve the Test results for the recently generated vin as well as specified time fr
        // Retrieve the created record, and verify that the test records are returned.
        testResultsSteps.getTestResultsFromDateAndSysNum(randomSystemNumber, testStartTimestamp);
        testResultsSteps.statusCodeShouldBe(200);
    }
}
