package testresults.annual_certificates;

import data.GenericData;
import exceptions.AutomationException;
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
                {"First test", "First test", "95", 2, "pass", "fft2"},
                {"First test", "First test", "95", 3, "pass", "fft3"},
                {"First test", "First test", "95", 4, "pass", "fft4"},
                {"First test", "First test", "95", 5, "pass", "fft5"},
                {"First test", "First test", "95", 6, "pass", "fft5"},
                {"First test", "First test", "95", 7, "pass", "fft5"},
                {"First test", "First test", "95", 8, "pass", "fft5"},
                {"First test", "First test", "95", 9, "pass", "fft5"},
                {"First test", "First test", "95", 10, "pass", "fft5"},
                {"Paid", "Paid first test retest", "103", 1, "pass", "rgt1"},
                {"Paid", "Paid first test retest", "65", 2, "pass", "rgt2"},
                {"Paid", "Paid first test retest", "65", 3, "pass", "rgt3"},
                {"Paid", "Paid first test retest", "65", 4, "pass", "rgt4"},
                {"Paid", "Paid first test retest", "65", 5, "pass", "rgt5"},
                {"Paid", "Paid first test retest", "65", 6, "pass", "rgt5"},
                {"Paid", "Paid first test retest", "65", 7, "pass", "rgt5"},
                {"Paid", "Paid first test retest", "65", 8, "pass", "rgt5"},
                {"Paid", "Paid first test retest", "65", 9, "pass", "rgt5"},
                {"Paid", "Paid first test retest", "65", 10, "pass", "rgt5"},
                {"Part paid", "Part paid first test retest", "104", 1, "pass", "rit1"},
                {"Part paid", "Part paid first test retest", "66", 2, "pass", "rit2"},
                {"Part paid", "Part paid first test retest", "66", 3, "pass", "rit3"},
                {"Part paid", "Part paid first test retest", "66", 4, "pass", "rit4"},
                {"Part paid", "Part paid first test retest", "66", 5, "pass", "rit5"},
                {"Part paid", "Part paid first test retest", "66", 6, "pass", "rit5"},
                {"Part paid", "Part paid first test retest", "66", 7, "pass", "rit5"},
                {"Part paid", "Part paid first test retest", "66", 8, "pass", "rit5"},
                {"Part paid", "Part paid first test retest", "66", 9, "pass", "rit5"},
                {"Part paid", "Part paid first test retest", "66", 10, "pass", "rit5"},
                {"Free", "Free first test retest", "67", 3, "pass", "rht"},
                {"Full Inspection/ Fee - With certification", "Paid prohibition clearance on first test (full inspection with certification)", "119", 1, "pass", "p7t1"},
                {"Full Inspection/ Fee - With certification", "Paid prohibition clearance on first test (full inspection with certification)", "82", 2, "pass", "p7t2"},
                {"Full Inspection/ Fee - With certification", "Paid prohibition clearance on first test (full inspection with certification)", "82", 3, "pass", "p7t3"},
                {"Full Inspection/ Fee - With certification", "Paid prohibition clearance on first test (full inspection with certification)", "82", 4, "pass", "p7t4"},
                {"Full Inspection/ Fee - With certification", "Paid prohibition clearance on first test (full inspection with certification)", "82", 5, "pass", "p7t5"},
                {"Full Inspection/ Fee - With certification", "Paid prohibition clearance on first test (full inspection with certification)", "82", 6, "pass", "p7t5"},
                {"Full Inspection/ Fee - With certification", "Paid prohibition clearance on first test (full inspection with certification)", "82", 7, "pass", "p7t5"},
                {"Full Inspection/ Fee - With certification", "Paid prohibition clearance on first test (full inspection with certification)", "82", 8, "pass", "p7t5"},
                {"Full Inspection/ Fee - With certification", "Paid prohibition clearance on first test (full inspection with certification)", "82", 9, "pass", "p7t5"},
                {"Full Inspection/ Fee - With certification", "Paid prohibition clearance on first test (full inspection with certification)", "82", 10, "pass", "p7t5"},
                {"Retest - Paid", "Paid retest prohibition clearance on first test", "120", 1, "pass", "p4t1"},
                {"Retest - Paid", "Paid retest prohibition clearance on first test", "83", 2, "pass", "p4t2"},
                {"Retest - Paid", "Paid retest prohibition clearance on first test", "83", 3, "pass", "p4t3"},
                {"Retest - Paid", "Paid retest prohibition clearance on first test", "83", 4, "pass", "p4t4"},
                {"Retest - Paid", "Paid retest prohibition clearance on first test", "83", 5, "pass", "p4t5"},
                {"Retest - Paid", "Paid retest prohibition clearance on first test", "83", 6, "pass", "p4t5"},
                {"Retest - Paid", "Paid retest prohibition clearance on first test", "83", 7, "pass", "p4t5"},
                {"Retest - Paid", "Paid retest prohibition clearance on first test", "83", 8, "pass", "p4t5"},
                {"Retest - Paid", "Paid retest prohibition clearance on first test", "83", 9, "pass", "p4t5"},
                {"Retest - Paid", "Paid retest prohibition clearance on first test", "83", 10, "pass", "p4t5"},
                {"Annual test", "Annual test", "40", 1, "pass", "aat1"},
                {"Annual test", "Annual test", "94", 2, "pass", "aat2"},
                {"Annual test", "Annual test", "94", 3, "pass", "aat3"},
                {"Annual test", "Annual test", "94", 4, "pass", "aat4"},
                {"Annual test", "Annual test", "94", 5, "pass", "aat5"},
                {"Annual test", "Annual test", "94", 6, "pass", "aat5"},
                {"Annual test", "Annual test", "94", 7, "pass", "aat5"},
                {"Annual test", "Annual test", "94", 8, "pass", "aat5"},
                {"Annual test", "Annual test", "94", 9, "pass", "aat5"},
                {"Annual test", "Annual test", "94", 10, "pass", "aat5"},
                {"Paid", "Paid annual test retest", "98", 1, "pass", "rpt1"},
                {"Paid", "Paid annual test retest", "53", 2, "pass", "rpt2"},
                {"Paid", "Paid annual test retest", "53", 3, "pass", "rpt3"},
                {"Paid", "Paid annual test retest", "53", 4, "pass", "rpt4"},
                {"Paid", "Paid annual test retest", "53", 5, "pass", "rpt5"},
                {"Paid", "Paid annual test retest", "53", 6, "pass", "rpt5"},
                {"Paid", "Paid annual test retest", "53", 7, "pass", "rpt5"},
                {"Paid", "Paid annual test retest", "53", 8, "pass", "rpt5"},
                {"Paid", "Paid annual test retest", "53", 9, "pass", "rpt5"},
                {"Paid", "Paid annual test retest", "53", 10, "pass", "rpt5"},
                {"Part paid", "Part paid annual test retest", "99", 1, "pass", "rst1"},
                {"Part paid", "Part paid annual test retest", "54", 2, "pass", "rst2"},
                {"Part paid", "Part paid annual test retest", "54", 3, "pass", "rst3"},
                {"Part paid", "Part paid annual test retest", "54", 4, "pass", "rst4"},
                {"Part paid", "Part paid annual test retest", "54", 5, "pass", "rst5"},
                {"Part paid", "Part paid annual test retest", "54", 6, "pass", "rst5"},
                {"Part paid", "Part paid annual test retest", "54", 7, "pass", "rst5"},
                {"Part paid", "Part paid annual test retest", "54", 8, "pass", "rst5"},
                {"Part paid", "Part paid annual test retest", "54", 9, "pass", "rst5"},
                {"Part paid", "Part paid annual test retest", "54", 10, "pass", "rst5"},
                {"With certification", "Paid prohibition clearance (full inspection with certification)", "107", 1, "pass", "p1t1"},
                {"With certification", "Paid prohibition clearance (full inspection with certification)", "70", 2, "pass", "p1t2"},
                {"With certification", "Paid prohibition clearance (full inspection with certification)", "70", 3, "pass", "p1t3"},
                {"With certification", "Paid prohibition clearance (full inspection with certification)", "70", 4, "pass", "p1t4"},
                {"With certification", "Paid prohibition clearance (full inspection with certification)", "70", 5, "pass", "p1t5"},
                {"With certification", "Paid prohibition clearance (full inspection with certification)", "70", 6, "pass", "p1t5"},
                {"With certification", "Paid prohibition clearance (full inspection with certification)", "70", 7, "pass", "p1t5"},
                {"With certification", "Paid prohibition clearance (full inspection with certification)", "70", 8, "pass", "p1t5"},
                {"With certification", "Paid prohibition clearance (full inspection with certification)", "70", 9, "pass", "p1t5"},
                {"With certification", "Paid prohibition clearance (retest with certification)", "113", 1, "pass", "p3t1"},
                {"With certification", "Paid prohibition clearance (retest with certification)", "76", 2, "pass", "p3t2"},
                {"With certification", "Paid prohibition clearance (retest with certification)", "76", 3, "pass", "p3t3"},
                {"With certification", "Paid prohibition clearance (retest with certification)", "76", 4, "pass", "p3t4"},
                {"With certification", "Paid prohibition clearance (retest with certification)", "76", 5, "pass", "p3t5"},
                {"With certification", "Paid prohibition clearance (retest with certification)", "76", 6, "pass", "p3t5"},
                {"With certification", "Paid prohibition clearance (retest with certification)", "76", 7, "pass", "p3t5"},
                {"With certification", "Paid prohibition clearance (retest with certification)", "76", 8, "pass", "p3t5"},
                {"With certification", "Paid prohibition clearance (retest with certification)", "76", 9, "pass", "p3t5"},
                {"With certification", "Paid prohibition clearance (retest with certification)", "76", 10, "pass", "p3t5"},
                {"With certification", "Part paid prohibition clearance (retest with certification)", "116", 1, "pass", "p6t1"},
                {"With certification", "Part paid prohibition clearance (retest with certification)", "79", 2, "pass", "p6t2"},
                {"With certification", "Part paid prohibition clearance (retest with certification)", "79", 3, "pass", "p6t3"},
                {"With certification", "Part paid prohibition clearance (retest with certification)", "79", 4, "pass", "p6t4"},
                {"With certification", "Part paid prohibition clearance (retest with certification)", "79", 5, "pass", "p6t5"},
                {"With certification", "Part paid prohibition clearance (retest with certification)", "79", 6, "pass", "p6t5"},
                {"With certification", "Part paid prohibition clearance (retest with certification)", "79", 7, "pass", "p6t5"},
                {"With certification", "Part paid prohibition clearance (retest with certification)", "79", 8, "pass", "p6t5"},
                {"With certification", "Part paid prohibition clearance (retest with certification)", "79", 9, "pass", "p6t5"},
                {"With certification", "Part paid prohibition clearance (retest with certification)", "79", 10, "pass", "p6t5"},

                {"First test", "First test", "41", 1, "prs", "fft1"},
                {"First test", "First test", "95", 2, "prs", "fft2"},
                {"First test", "First test", "95", 3, "prs", "fft3"},
                {"First test", "First test", "95", 4, "prs", "fft4"},
                {"First test", "First test", "95", 5, "prs", "fft5"},
                {"First test", "First test", "95", 6, "prs", "fft5"},
                {"First test", "First test", "95", 7, "prs", "fft5"},
                {"First test", "First test", "95", 8, "prs", "fft5"},
                {"First test", "First test", "95", 9, "prs", "fft5"},
                {"First test", "First test", "95", 10, "prs", "fft5"},
                {"Paid", "Paid first test retest", "103", 1, "prs", "rgt1"},
                {"Paid", "Paid first test retest", "65", 2, "prs", "rgt2"},
                {"Paid", "Paid first test retest", "65", 3, "prs", "rgt3"},
                {"Paid", "Paid first test retest", "65", 4, "prs", "rgt4"},
                {"Paid", "Paid first test retest", "65", 5, "prs", "rgt5"},
                {"Paid", "Paid first test retest", "65", 6, "prs", "rgt5"},
                {"Paid", "Paid first test retest", "65", 7, "prs", "rgt5"},
                {"Paid", "Paid first test retest", "65", 8, "prs", "rgt5"},
                {"Paid", "Paid first test retest", "65", 9, "prs", "rgt5"},
                {"Paid", "Paid first test retest", "65", 10, "prs", "rgt5"},
                {"Part paid", "Part paid first test retest", "104", 1, "prs", "rit1"},
                {"Part paid", "Part paid first test retest", "66", 2, "prs", "rit2"},
                {"Part paid", "Part paid first test retest", "66", 3, "prs", "rit3"},
                {"Part paid", "Part paid first test retest", "66", 4, "prs", "rit4"},
                {"Part paid", "Part paid first test retest", "66", 5, "prs", "rit5"},
                {"Part paid", "Part paid first test retest", "66", 6, "prs", "rit5"},
                {"Part paid", "Part paid first test retest", "66", 7, "prs", "rit5"},
                {"Part paid", "Part paid first test retest", "66", 8, "prs", "rit5"},
                {"Part paid", "Part paid first test retest", "66", 9, "prs", "rit5"},
                {"Part paid", "Part paid first test retest", "66", 10, "prs", "rit5"},
                {"Free", "Free first test retest", "67", 3, "prs", "rht"},
                {"Full Inspection/ Fee - With certification", "Paid prohibition clearance on first test (full inspection with certification)", "119", 1, "prs", "p7t1"},
                {"Full Inspection/ Fee - With certification", "Paid prohibition clearance on first test (full inspection with certification)", "82", 2, "prs", "p7t2"},
                {"Full Inspection/ Fee - With certification", "Paid prohibition clearance on first test (full inspection with certification)", "82", 3, "prs", "p7t3"},
                {"Full Inspection/ Fee - With certification", "Paid prohibition clearance on first test (full inspection with certification)", "82", 4, "prs", "p7t4"},
                {"Full Inspection/ Fee - With certification", "Paid prohibition clearance on first test (full inspection with certification)", "82", 5, "prs", "p7t5"},
                {"Full Inspection/ Fee - With certification", "Paid prohibition clearance on first test (full inspection with certification)", "82", 6, "prs", "p7t5"},
                {"Full Inspection/ Fee - With certification", "Paid prohibition clearance on first test (full inspection with certification)", "82", 7, "prs", "p7t5"},
                {"Full Inspection/ Fee - With certification", "Paid prohibition clearance on first test (full inspection with certification)", "82", 8, "prs", "p7t5"},
                {"Full Inspection/ Fee - With certification", "Paid prohibition clearance on first test (full inspection with certification)", "82", 9, "prs", "p7t5"},
                {"Full Inspection/ Fee - With certification", "Paid prohibition clearance on first test (full inspection with certification)", "82", 10, "prs", "p7t5"},
                {"Retest - Paid", "Paid retest prohibition clearance on first test", "120", 1, "prs", "p4t1"},
                {"Retest - Paid", "Paid retest prohibition clearance on first test", "83", 2, "prs", "p4t2"},
                {"Retest - Paid", "Paid retest prohibition clearance on first test", "83", 3, "prs", "p4t3"},
                {"Retest - Paid", "Paid retest prohibition clearance on first test", "83", 4, "prs", "p4t4"},
                {"Retest - Paid", "Paid retest prohibition clearance on first test", "83", 5, "prs", "p4t5"},
                {"Retest - Paid", "Paid retest prohibition clearance on first test", "83", 6, "prs", "p4t5"},
                {"Retest - Paid", "Paid retest prohibition clearance on first test", "83", 7, "prs", "p4t5"},
                {"Retest - Paid", "Paid retest prohibition clearance on first test", "83", 8, "prs", "p4t5"},
                {"Retest - Paid", "Paid retest prohibition clearance on first test", "83", 9, "prs", "p4t5"},
                {"Retest - Paid", "Paid retest prohibition clearance on first test", "83", 10, "prs", "p4t5"},
                {"Annual test", "Annual test", "40", 1, "prs", "aat1"},
                {"Annual test", "Annual test", "94", 2, "prs", "aat2"},
                {"Annual test", "Annual test", "94", 3, "prs", "aat3"},
                {"Annual test", "Annual test", "94", 4, "prs", "aat4"},
                {"Annual test", "Annual test", "94", 5, "prs", "aat5"},
                {"Annual test", "Annual test", "94", 6, "prs", "aat5"},
                {"Annual test", "Annual test", "94", 7, "prs", "aat5"},
                {"Annual test", "Annual test", "94", 8, "prs", "aat5"},
                {"Annual test", "Annual test", "94", 9, "prs", "aat5"},
                {"Annual test", "Annual test", "94", 10, "prs", "aat5"},
                {"Paid", "Paid annual test retest", "98", 1, "prs", "rpt1"},
                {"Paid", "Paid annual test retest", "53", 2, "prs", "rpt2"},
                {"Paid", "Paid annual test retest", "53", 3, "prs", "rpt3"},
                {"Paid", "Paid annual test retest", "53", 4, "prs", "rpt4"},
                {"Paid", "Paid annual test retest", "53", 5, "prs", "rpt5"},
                {"Paid", "Paid annual test retest", "53", 6, "prs", "rpt5"},
                {"Paid", "Paid annual test retest", "53", 7, "prs", "rpt5"},
                {"Paid", "Paid annual test retest", "53", 8, "prs", "rpt5"},
                {"Paid", "Paid annual test retest", "53", 9, "prs", "rpt5"},
                {"Paid", "Paid annual test retest", "53", 10, "prs", "rpt5"},
                {"Part paid", "Part paid annual test retest", "99", 1, "prs", "rst1"},
                {"Part paid", "Part paid annual test retest", "54", 2, "prs", "rst2"},
                {"Part paid", "Part paid annual test retest", "54", 3, "prs", "rst3"},
                {"Part paid", "Part paid annual test retest", "54", 4, "prs", "rst4"},
                {"Part paid", "Part paid annual test retest", "54", 5, "prs", "rst5"},
                {"Part paid", "Part paid annual test retest", "54", 6, "prs", "rst5"},
                {"Part paid", "Part paid annual test retest", "54", 7, "prs", "rst5"},
                {"Part paid", "Part paid annual test retest", "54", 8, "prs", "rst5"},
                {"Part paid", "Part paid annual test retest", "54", 9, "prs", "rst5"},
                {"Part paid", "Part paid annual test retest", "54", 10, "prs", "rst5"},
                {"With certification", "Paid prohibition clearance (full inspection with certification)", "107", 1, "prs", "p1t1"},
                {"With certification", "Paid prohibition clearance (full inspection with certification)", "70", 2, "prs", "p1t2"},
                {"With certification", "Paid prohibition clearance (full inspection with certification)", "70", 3, "prs", "p1t3"},
                {"With certification", "Paid prohibition clearance (full inspection with certification)", "70", 4, "prs", "p1t4"},
                {"With certification", "Paid prohibition clearance (full inspection with certification)", "70", 5, "prs", "p1t5"},
                {"With certification", "Paid prohibition clearance (full inspection with certification)", "70", 6, "prs", "p1t5"},
                {"With certification", "Paid prohibition clearance (full inspection with certification)", "70", 7, "prs", "p1t5"},
                {"With certification", "Paid prohibition clearance (full inspection with certification)", "70", 8, "prs", "p1t5"},
                {"With certification", "Paid prohibition clearance (full inspection with certification)", "70", 9, "prs", "p1t5"},
                {"With certification", "Paid prohibition clearance (retest with certification)", "113", 1, "prs", "p3t1"},
                {"With certification", "Paid prohibition clearance (retest with certification)", "76", 2, "prs", "p3t2"},
                {"With certification", "Paid prohibition clearance (retest with certification)", "76", 3, "prs", "p3t3"},
                {"With certification", "Paid prohibition clearance (retest with certification)", "76", 4, "prs", "p3t4"},
                {"With certification", "Paid prohibition clearance (retest with certification)", "76", 5, "prs", "p3t5"},
                {"With certification", "Paid prohibition clearance (retest with certification)", "76", 6, "prs", "p3t5"},
                {"With certification", "Paid prohibition clearance (retest with certification)", "76", 7, "prs", "p3t5"},
                {"With certification", "Paid prohibition clearance (retest with certification)", "76", 8, "prs", "p3t5"},
                {"With certification", "Paid prohibition clearance (retest with certification)", "76", 9, "prs", "p3t5"},
                {"With certification", "Paid prohibition clearance (retest with certification)", "76", 10, "prs", "p3t5"},
                {"With certification", "Part paid prohibition clearance (retest with certification)", "116", 1, "prs", "p6t1"},
                {"With certification", "Part paid prohibition clearance (retest with certification)", "79", 2, "prs", "p6t2"},
                {"With certification", "Part paid prohibition clearance (retest with certification)", "79", 3, "prs", "p6t3"},
                {"With certification", "Part paid prohibition clearance (retest with certification)", "79", 4, "prs", "p6t4"},
                {"With certification", "Part paid prohibition clearance (retest with certification)", "79", 5, "prs", "p6t5"},
                {"With certification", "Part paid prohibition clearance (retest with certification)", "79", 6, "prs", "p6t5"},
                {"With certification", "Part paid prohibition clearance (retest with certification)", "79", 7, "prs", "p6t5"},
                {"With certification", "Part paid prohibition clearance (retest with certification)", "79", 8, "prs", "p6t5"},
                {"With certification", "Part paid prohibition clearance (retest with certification)", "79", 9, "prs", "p6t5"},
                {"With certification", "Part paid prohibition clearance (retest with certification)", "79", 10, "prs", "p6t5"}
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
        String testResultRecord = GenericData.updateJson(testResultsSteps,jsonFileName,"$");

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
                testResultsSteps.statusCodeShouldBe(504);
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
