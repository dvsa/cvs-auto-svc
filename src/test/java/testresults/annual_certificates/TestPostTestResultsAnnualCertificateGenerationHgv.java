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
public class TestPostTestResultsAnnualCertificateGenerationHgv {

    @Steps
    TestResultsSteps testResultsSteps;

    @TestData
    public static Collection<Object[]> testData(){
        return Arrays.asList(new Object[][]{
                {"First test", "First test", "95", 2, "pass", "ffv2"},
                {"Paid", "Paid first test retest", "65", 10, "pass", "rgv5"},
                {"Part paid", "Part paid first test retest", "66", 3, "pass", "riv3"},
                {"Full Inspection/ Fee - With certification", "Paid prohibition clearance on first test (full inspection with certification)", "82", 4, "pass", "p7v4"},
                {"Retest - Paid", "Paid retest prohibition clearance on first test", "83", 5, "pass", "p4v5"},
                {"Annual test", "Annual test", "94", 6, "pass", "aav5"},
                {"Paid", "Paid annual test retest", "53", 7, "pass", "rpv5"},
                {"Part paid", "Part paid annual test retest", "54", 8, "pass", "rsv5"},
                {"With certification", "Paid prohibition clearance (full inspection with certification)", "70", 9, "pass", "p1v5"},
                {"With certification", "Paid prohibition clearance (retest with certification)", "76", 10, "pass", "p3v5"},
                {"With certification", "Part paid prohibition clearance (retest with certification)", "79", 2, "pass", "p6v2"},
                {"First test", "First test", "95", 3, "prs", "ffv3"},
                {"Paid", "Paid first test retest", "65", 4, "prs", "rgv4"},
                {"Part paid", "Part paid first test retest", "66", 5, "prs", "riv5"},
                {"Full Inspection/ Fee - With certification", "Paid prohibition clearance on first test (full inspection with certification)", "82", 6, "prs", "p7v5"},
                {"Retest - Paid", "Paid retest prohibition clearance on first test", "83", 7, "prs", "p4v5"},
                {"Annual test", "Annual test", "94", 8, "prs", "aav5"},
                {"Paid", "Paid annual test retest", "53", 9, "prs", "rpv5"},
                {"Part paid", "Part paid annual test retest", "54", 10, "prs", "rsv5"},
                {"With certification", "Paid prohibition clearance (full inspection with certification)", "70", 2, "prs", "p1v2"},
                {"With certification", "Paid prohibition clearance (retest with certification)", "76", 3, "prs", "p3v3"},
                {"With certification", "Part paid prohibition clearance (retest with certification)", "79", 4, "prs", "p6v4"}

        });
    }

    private String name;
    private String testTypeName;
    private String testTypeId;
    private int noOfAxles;
    private String testResult;
    private String testCode;

    public TestPostTestResultsAnnualCertificateGenerationHgv(String name, String testTypeName, String testTypeId, int noOfAxles, String testResult, String testCode) {
        this.name = name;
        this.testTypeName = testTypeName;
        this.testTypeId = testTypeId;
        this.noOfAxles = noOfAxles;
        this.testResult = testResult;
        this.testCode = testCode;
    }

    @WithTag("annual_certificates")
    @Title("CVSB-8798 - Annual certificate is generate for all Hgv tests ")
    @Test
    public void testResults_Annual_Certificate_Generation_Hgv() {

        // Read the base test result JSON.
        String jsonFileName = "test-results_post_expiry_date_hgv_8798.json";
        String testResultRecord = GenericData.updateJson(jsonFileName,false);

        String randomVin = GenericData.generateRandomVin();
        String randomSystemNumber = GenericData.generateRandomSystemNumber();
        String randomTestResultId = UUID.randomUUID().toString();


        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$.systemNumber", randomSystemNumber, "", "REPLACE");
        JsonPathAlteration alterationTestResultId = new JsonPathAlteration("$.testResultId", randomTestResultId, "", "REPLACE");
        JsonPathAlteration alterationTestName = new JsonPathAlteration("$.testTypes[0].name", name,"","REPLACE");
        JsonPathAlteration alterationTestTypeId = new JsonPathAlteration("$.testTypes[0].testTypeId", testTypeId,"","REPLACE");
        JsonPathAlteration alterationTestTypeName = new JsonPathAlteration("$.testTypes[0].testTypeName", testTypeName,"","REPLACE");
        JsonPathAlteration alterationTestResult = new JsonPathAlteration("$.testTypes[0].testResult", testResult,"","REPLACE");
        JsonPathAlteration alterationNoOfAxles = new JsonPathAlteration("$.noOfAxles", noOfAxles,"","REPLACE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(
                alterationVin,
                alterationTestResultId,
                alterationTestName,
                alterationTestTypeId,
                alterationTestTypeName,
                alterationTestResult,
                alterationNoOfAxles,
                alterationSystemNumber
        ));

        // Post the results, together with any alterations, and verify that they are accepted.
        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, alterations);
        if ("warmup test".equals(name)) {
            try {
                testResultsSteps.statusCodeShouldBe(504);
            } catch (Exception e) {
                System.out.println("Retry" + " " + e);
                testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, alterations);
            }
        }
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        testResultsSteps.getTestResults(randomSystemNumber);
        testResultsSteps.statusCodeShouldBe(200);
        String testNumber = testResultsSteps.getTestNumber();
        testResultsSteps.valueForFieldInPathShouldBe("[0].testTypes[0].testCode", testCode);
        Assert.assertTrue(testResultsSteps.validateCertificateNumberLength());

        //Verify that the certificate is generated in S3 bucket
        testResultsSteps.validateCertificateIsGenerated(testNumber,randomVin);

    }
}
