package testresults;

import data.GenericData;
import data.TestResultsData;
import model.testresults.TestResults;
import model.testresults.TestResultsGet;
import model.testresults.TestResultsStatus;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Test;
import org.junit.runner.RunWith;
import steps.TestResultsSteps;
import util.JsonPathAlteration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(SerenityRunner.class)
public class PostTestResults_LEC_731 {

    @Steps
    TestResultsSteps testResultsSteps;



    @Title("CVSB-8380 - Iteration on test results API specs to cover the additional LEC test details fields")
    @Test
    public void testResultsAPIConsumerCreatesTestResultsForSubmittedTest_LEC() {

        // Read the base test result JSON.
        String testResultRecord = GenericData.readJsonValueFromFile("test-results_LEC_731.json","$");

        // AC4 API Consumer creates a new test results for the submitted test
        // Create alteration to add one more tech record to in the request body
        String randomVin = GenericData.generateRandomVin();
        String particulateTrapFitted = "My particulate trap";
        String particulateTrapSerialNumber = "PTSerialNo";
        String modificationTypeUsed = "My modification type";
        String smokeTestKLimitApplied = "123.456";

        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin,"","REPLACE");
        JsonPathAlteration alterationParticulateTrapFitted = new JsonPathAlteration("$.testTypes[0].particulateTrapFitted", particulateTrapFitted,"","REPLACE");
        JsonPathAlteration alterationParticulateTrapSerialNumber = new JsonPathAlteration("$.testTypes[0].particulateTrapSerialNumber", particulateTrapSerialNumber,"","REPLACE");
        JsonPathAlteration alterationModificationTypeUsed = new JsonPathAlteration("$.testTypes[0].modificationTypeUsed", modificationTypeUsed,"","REPLACE");
        JsonPathAlteration alterationSmokeTestKLimitApplied = new JsonPathAlteration("$.testTypes[0].smokeTestKLimitApplied", smokeTestKLimitApplied,"","REPLACE");

        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(
                alterationVin,
                alterationParticulateTrapFitted,
                alterationParticulateTrapSerialNumber,
                alterationModificationTypeUsed,
                alterationSmokeTestKLimitApplied));

        // Post the results, together with any alterations, and verify that they are accepted.
        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, alterations);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");

        // AC1 API Consumer retrieve the Test results for the input Vin
        // Retrieve the created record, and verify that the fields are present.
        testResultsSteps.getTestResults(randomVin);
        testResultsSteps.statusCodeShouldBe(200);

        // Verify that the new LEC fields are returned.
        testResultsSteps.validateTestFieldExists("particulateTrapFitted");
        testResultsSteps.validateTestFieldExists("particulateTrapSerialNumber");
        testResultsSteps.validateTestFieldExists("modificationTypeUsed");
        testResultsSteps.validateTestFieldExists("smokeTestKLimitApplied");

        // Verify LEC test field values match the expected values.
        testResultsSteps.valueForFieldInPathShouldBe("[0].testTypes[0].testTypeId", "39");
        testResultsSteps.valueForFieldInPathShouldBe("[0].testTypes[0].testTypeName", "Low Emissions Certificate (LEC) with annual test");

        testResultsSteps.valueForFieldInPathShouldBe("[0].testTypes[0].particulateTrapFitted", particulateTrapFitted);
        testResultsSteps.valueForFieldInPathShouldBe("[0].testTypes[0].particulateTrapSerialNumber", particulateTrapSerialNumber);
        testResultsSteps.valueForFieldInPathShouldBe("[0].testTypes[0].modificationTypeUsed", modificationTypeUsed);
        testResultsSteps.valueForFieldInPathShouldBe("[0].testTypes[0].smokeTestKLimitApplied", smokeTestKLimitApplied);
    }
}