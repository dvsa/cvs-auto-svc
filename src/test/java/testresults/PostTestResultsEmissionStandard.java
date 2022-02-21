package testresults;

import data.GenericData;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import steps.TestResultsSteps;
import util.JsonPathAlteration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@RunWith(SerenityRunner.class)
public class PostTestResultsEmissionStandard {

    @Steps
    TestResultsSteps testResultsSteps;

    private String test_results_roadworthiness_hgv_pass_7675_json;
    @Before
    @Test
    public void updateJson(){
        String jsonFileName = "test-results_roadworthiness_hgv_pass_7675.json";
        test_results_roadworthiness_hgv_pass_7675_json = GenericData.updateJson( jsonFileName, false);
    }

    @Title("CVSB-12015 - AC3 API Consumer creates a new test results for the submitted test (emissionStandard)")
    @Test
    public void testTestResultsPostValidEmissionStandard() {
        // Read the base test result JSON.
        String testResultRecord = test_results_roadworthiness_hgv_pass_7675_json;

        // Create alteration to add one more tech record to in the request body
        String randomSystemNumber = GenericData.generateRandomSystemNumber();
        String randomVin = GenericData.generateRandomVin();
        String randomTestResultId = UUID.randomUUID().toString();
        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$.systemNumber", randomSystemNumber, "", "REPLACE");
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationTestResultId = new JsonPathAlteration("$.testResultId", randomTestResultId, "", "REPLACE");
        JsonPathAlteration alterationEmissionStandard = new JsonPathAlteration("$.testTypes[0].emissionStandard", "0.03 g/kWh Euro IV PM", "", "REPLACE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(
                alterationVin,
                alterationSystemNumber,
                alterationTestResultId,
                alterationEmissionStandard));

        // Post the results, together with any alterations, and verify that they are accepted.
        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, alterations);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");

        // Retrieve the created record, and verify that the fields are present.
        testResultsSteps.getTestResults(randomSystemNumber);
        testResultsSteps.statusCodeShouldBe(200);

        // Verify that the fuel type was as entered.
        testResultsSteps.valueForFieldInPathShouldBe("[0].testTypes[0].emissionStandard", "0.03 g/kWh Euro IV PM");
    }
}
