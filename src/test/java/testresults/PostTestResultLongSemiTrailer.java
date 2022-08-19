package testresults;

import data.GenericData;
import data.TestResultsData;
import model.testresults.TestResults;
import model.testresults.TestVersion;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import steps.TestResultsSteps;
import util.JsonPathAlteration;
import java.util.*;

@RunWith(SerenityRunner.class)
public class PostTestResultLongSemiTrailer {
    @Steps
    TestResultsSteps testResultsSteps;

    private TestResults.Builder vehicleCancelledData = TestResultsData.buildTestResultsCancelledData();

    public PostTestResultLongSemiTrailer() {
    }

    @Before
    @Title("warm up test")
    @Test
    public void testResultsWarmUpTest() {
        testResultsSteps.postTestResults(vehicleCancelledData.build());
    }@Title("VTA-716 - Adding a test for a long semi trailer to check a test result goes through with long semi trailer vehicle configuration")
    @Test
    public void testResultsLongSemiTrailer() {
        // Read the base test result JSON.
        String jsonFileName = "test-results_post_payload_trl_761.json";
        String testResultRecord = GenericData.updateJson(jsonFileName,false);
        String randomTestResultId = UUID.randomUUID().toString();
        JsonPathAlteration alterationTestResultId = new JsonPathAlteration("$.testResultId", randomTestResultId, "", "REPLACE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(
                alterationTestResultId
        ));
        // Post the results, together with any alterations, and verify that they are accepted.
        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, alterations);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");

        //Get test results to show the record in the database and to validate cert number generated is correct
        testResultsSteps.getTestResults("1110009", TestVersion.CURRENT, randomTestResultId);
        testResultsSteps.statusCodeShouldBe(200);
        String testNumber = testResultsSteps.getTestNumber();
        testResultsSteps.valueForFieldInPathShouldBe("[0].testTypes[0].testCode", "aat3");
        Assert.assertTrue(testResultsSteps.validateCertificateNumberLength());

        //Verify that the certificate is generated in S3 bucket
        testResultsSteps.validateCertificateIsGenerated(testNumber, "ABCDEF111223");
}}
