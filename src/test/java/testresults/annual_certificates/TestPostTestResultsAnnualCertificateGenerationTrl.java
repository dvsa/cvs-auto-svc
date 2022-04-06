package testresults.annual_certificates;

import data.GenericData;
import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.junit.annotations.TestData;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import steps.TestResultsSteps;
import util.JsonPathAlteration;
import java.util.*;

@RunWith(SerenityParameterizedRunner.class)
public class TestPostTestResultsAnnualCertificateGenerationTrl {

    @Steps
    TestResultsSteps testResultsSteps;

    @TestData
    public static Collection<Object[]> testData(){
        return Arrays.asList(new Object[][]{
                {"warmup test", "warmup test", "41", 1, "pass", "fft1"},
                {"First test", "First test", "41", 1, "pass", "fft1"},
                {"Paid", "Paid first test retest", "65", 2, "pass", "rgt2"},
                {"Part paid", "Part paid first test retest", "66", 3, "pass", "rit3"},
                {"Free", "Free first test retest", "67", 3, "pass", "rht"},
                {"Full Inspection/ Fee - With certification", "Paid prohibition clearance on first test (full inspection with certification)", "82", 4, "pass", "p7t4"},
                {"Retest - Paid", "Paid retest prohibition clearance on first test", "83", 5, "pass", "p4t5"},
                {"Annual test", "Annual test", "94", 6, "pass", "aat5"},
                {"Paid", "Paid annual test retest", "53", 7, "pass", "rpt5"},
                {"Part paid", "Part paid annual test retest", "54", 8, "pass", "rst5"},
                {"With certification", "Paid prohibition clearance (full inspection with certification)", "70", 9, "pass", "p1t5"},
                {"With certification", "Paid prohibition clearance (retest with certification)", "76", 10, "pass", "p3t5"},
                {"With certification", "Part paid prohibition clearance (retest with certification)", "116", 1, "pass", "p6t1"},
                {"First test", "First test", "95", 2, "prs", "fft2"},
                {"Paid", "Paid first test retest", "65", 3, "prs", "rgt3"},
                {"Part paid", "Part paid first test retest", "66", 4, "prs", "rit4"},
                {"Full Inspection/ Fee - With certification", "Paid prohibition clearance on first test (full inspection with certification)", "82", 5, "prs", "p7t5"},
                {"Retest - Paid", "Paid retest prohibition clearance on first test", "83", 6, "prs", "p4t5"},
                {"Annual test", "Annual test", "94", 7, "prs", "aat5"},
                {"Paid", "Paid annual test retest", "53", 8, "prs", "rpt5"},
                {"Part paid", "Part paid annual test retest", "54", 9, "prs", "rst5"},
                {"With certification", "Paid prohibition clearance (full inspection with certification)", "107", 1, "prs", "p1t1"},
                {"With certification", "Paid prohibition clearance (retest with certification)", "76", 2, "prs", "p3t2"},
                {"With certification", "Part paid prohibition clearance (retest with certification)", "79", 3, "prs", "p6t3"}
        });
    }

    private String name;
    private String testTypeName;
    private String testTypeId;
    private int noOfAxles;
    private String testResult;
    private String testCode;

    public TestPostTestResultsAnnualCertificateGenerationTrl(String name, String testTypeName, String testTypeId, int noOfAxles, String testResult, String testCode) {
        this.name = name;
        this.testTypeName = testTypeName;
        this.testTypeId = testTypeId;
        this.noOfAxles = noOfAxles;
        this.testResult = testResult;
        this.testCode = testCode;
    }

    @WithTag("annual_certificates")
    @Title("CVSB-8798 - Annual certificate is generate for all Trl tests")
    @Test
    public void testResults_Annual_Certificate_Generation_Trl() {
        // Read the base test result JSON.
        String jsonFileName = "test-results_post_expiry_date_trl_8798.json";
        String testResultRecord = GenericData.updateJson(jsonFileName,false);

        String randomVin = GenericData.generateRandomVin();
        String randomSystemNumber = GenericData.generateRandomSystemNumber();
        String randomTestResultId = UUID.randomUUID().toString();

        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$.systemNumber", randomSystemNumber, "", "REPLACE");
        JsonPathAlteration alterationTestResultId = new JsonPathAlteration("$.testResultId", randomTestResultId, "", "REPLACE");
        JsonPathAlteration alterationTestName = new JsonPathAlteration("$.testTypes[0].name", name, "", "REPLACE");
        JsonPathAlteration alterationTestTypeId = new JsonPathAlteration("$.testTypes[0].testTypeId", testTypeId, "", "REPLACE");
        JsonPathAlteration alterationTestTypeName = new JsonPathAlteration("$.testTypes[0].testTypeName", testTypeName, "", "REPLACE");
        JsonPathAlteration alterationTestResult = new JsonPathAlteration("$.testTypes[0].testResult", testResult, "", "REPLACE");
        JsonPathAlteration alterationNoOfAxles = new JsonPathAlteration("$.noOfAxles", noOfAxles, "", "REPLACE");

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
        if ("warmup test".equals(name)) {
            try {
                System.out.println("Warm up complete");
            } catch (Exception e) {
                System.out.println("Retry" + " " + e);
                testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, alterations);
            }
        }
        else {
            testResultsSteps.statusCodeShouldBe(201);
            testResultsSteps.validateData("Test records created");

            testResultsSteps.getTestResults(randomSystemNumber);
            testResultsSteps.statusCodeShouldBe(200);
            String testNumber = testResultsSteps.getTestNumber();
            testResultsSteps.valueForFieldInPathShouldBe("[0].testTypes[0].testCode", testCode);
            Assert.assertTrue(testResultsSteps.validateCertificateNumberLength());

            //Verify that the certificate is generated in S3 bucket
            testResultsSteps.validateCertificateIsGenerated(testNumber, randomVin);
        }
    }
}
