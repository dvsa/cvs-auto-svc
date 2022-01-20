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
                {"warmup test", "warmup test", "95", 1, "pass", "ffv2"},
                {"First test", "First test", "95", 2, "pass", "ffv2"},
                {"First test", "First test", "95", 3, "pass", "ffv3"},
                {"First test", "First test", "95", 4, "pass", "ffv4"},
                {"First test", "First test", "95", 5, "pass", "ffv5"},
                {"First test", "First test", "95", 6, "pass", "ffv5"},
                {"First test", "First test", "95", 7, "pass", "ffv5"},
                {"First test", "First test", "95", 8, "pass", "ffv5"},
                {"First test", "First test", "95", 9, "pass", "ffv5"},
                {"First test", "First test", "95", 10, "pass", "ffv5"},
                {"Paid", "Paid first test retest", "65", 2, "pass", "rgv2"},
                {"Paid", "Paid first test retest", "65", 3, "pass", "rgv3"},
                {"Paid", "Paid first test retest", "65", 4, "pass", "rgv4"},
                {"Paid", "Paid first test retest", "65", 5, "pass", "rgv5"},
                {"Paid", "Paid first test retest", "65", 6, "pass", "rgv5"},
                {"Paid", "Paid first test retest", "65", 7, "pass", "rgv5"},
                {"Paid", "Paid first test retest", "65", 8, "pass", "rgv5"},
                {"Paid", "Paid first test retest", "65", 9, "pass", "rgv5"},
                {"Paid", "Paid first test retest", "65", 10, "pass", "rgv5"},
                {"Part paid", "Part paid first test retest", "66", 2, "pass", "riv2"},
                {"Part paid", "Part paid first test retest", "66", 3, "pass", "riv3"},
                {"Part paid", "Part paid first test retest", "66", 4, "pass", "riv4"},
                {"Part paid", "Part paid first test retest", "66", 5, "pass", "riv5"},
                {"Part paid", "Part paid first test retest", "66", 6, "pass", "riv5"},
                {"Part paid", "Part paid first test retest", "66", 7, "pass", "riv5"},
                {"Part paid", "Part paid first test retest", "66", 8, "pass", "riv5"},
                {"Part paid", "Part paid first test retest", "66", 9, "pass", "riv5"},
                {"Part paid", "Part paid first test retest", "66", 10, "pass", "riv5"},
                {"Full Inspection/ Fee - With certification", "Paid prohibition clearance on first test (full inspection with certification)", "82", 2, "pass", "p7v2"},
                {"Full Inspection/ Fee - With certification", "Paid prohibition clearance on first test (full inspection with certification)", "82", 3, "pass", "p7v3"},
                {"Full Inspection/ Fee - With certification", "Paid prohibition clearance on first test (full inspection with certification)", "82", 4, "pass", "p7v4"},
                {"Full Inspection/ Fee - With certification", "Paid prohibition clearance on first test (full inspection with certification)", "82", 5, "pass", "p7v5"},
                {"Full Inspection/ Fee - With certification", "Paid prohibition clearance on first test (full inspection with certification)", "82", 6, "pass", "p7v5"},
                {"Full Inspection/ Fee - With certification", "Paid prohibition clearance on first test (full inspection with certification)", "82", 7, "pass", "p7v5"},
                {"Full Inspection/ Fee - With certification", "Paid prohibition clearance on first test (full inspection with certification)", "82", 8, "pass", "p7v5"},
                {"Full Inspection/ Fee - With certification", "Paid prohibition clearance on first test (full inspection with certification)", "82", 9, "pass", "p7v5"},
                {"Full Inspection/ Fee - With certification", "Paid prohibition clearance on first test (full inspection with certification)", "82", 10, "pass", "p7v5"},
                {"Retest - Paid", "Paid retest prohibition clearance on first test", "83", 2, "pass", "p4v2"},
                {"Retest - Paid", "Paid retest prohibition clearance on first test", "83", 3, "pass", "p4v3"},
                {"Retest - Paid", "Paid retest prohibition clearance on first test", "83", 4, "pass", "p4v4"},
                {"Retest - Paid", "Paid retest prohibition clearance on first test", "83", 5, "pass", "p4v5"},
                {"Retest - Paid", "Paid retest prohibition clearance on first test", "83", 6, "pass", "p4v5"},
                {"Retest - Paid", "Paid retest prohibition clearance on first test", "83", 7, "pass", "p4v5"},
                {"Retest - Paid", "Paid retest prohibition clearance on first test", "83", 8, "pass", "p4v5"},
                {"Retest - Paid", "Paid retest prohibition clearance on first test", "83", 9, "pass", "p4v5"},
                {"Retest - Paid", "Paid retest prohibition clearance on first test", "83", 10, "pass", "p4v5"},
                {"Annual test", "Annual test", "94", 2, "pass", "aav2"},
                {"Annual test", "Annual test", "94", 3, "pass", "aav3"},
                {"Annual test", "Annual test", "94", 4, "pass", "aav4"},
                {"Annual test", "Annual test", "94", 5, "pass", "aav5"},
                {"Annual test", "Annual test", "94", 6, "pass", "aav5"},
                {"Annual test", "Annual test", "94", 7, "pass", "aav5"},
                {"Annual test", "Annual test", "94", 8, "pass", "aav5"},
                {"Annual test", "Annual test", "94", 9, "pass", "aav5"},
                {"Annual test", "Annual test", "94", 10, "pass", "aav5"},
                {"Paid", "Paid annual test retest", "53", 2, "pass", "rpv2"},
                {"Paid", "Paid annual test retest", "53", 3, "pass", "rpv3"},
                {"Paid", "Paid annual test retest", "53", 4, "pass", "rpv4"},
                {"Paid", "Paid annual test retest", "53", 5, "pass", "rpv5"},
                {"Paid", "Paid annual test retest", "53", 6, "pass", "rpv5"},
                {"Paid", "Paid annual test retest", "53", 7, "pass", "rpv5"},
                {"Paid", "Paid annual test retest", "53", 8, "pass", "rpv5"},
                {"Paid", "Paid annual test retest", "53", 9, "pass", "rpv5"},
                {"Paid", "Paid annual test retest", "53", 10, "pass", "rpv5"},
                {"Part paid", "Part paid annual test retest", "54", 2, "pass", "rsv2"},
                {"Part paid", "Part paid annual test retest", "54", 3, "pass", "rsv3"},
                {"Part paid", "Part paid annual test retest", "54", 4, "pass", "rsv4"},
                {"Part paid", "Part paid annual test retest", "54", 5, "pass", "rsv5"},
                {"Part paid", "Part paid annual test retest", "54", 6, "pass", "rsv5"},
                {"Part paid", "Part paid annual test retest", "54", 7, "pass", "rsv5"},
                {"Part paid", "Part paid annual test retest", "54", 8, "pass", "rsv5"},
                {"Part paid", "Part paid annual test retest", "54", 9, "pass", "rsv5"},
                {"Part paid", "Part paid annual test retest", "54", 10, "pass", "rsv5"},
                {"With certification", "Paid prohibition clearance (full inspection with certification)", "70", 2, "pass", "p1v2"},
                {"With certification", "Paid prohibition clearance (full inspection with certification)", "70", 3, "pass", "p1v3"},
                {"With certification", "Paid prohibition clearance (full inspection with certification)", "70", 4, "pass", "p1v4"},
                {"With certification", "Paid prohibition clearance (full inspection with certification)", "70", 5, "pass", "p1v5"},
                {"With certification", "Paid prohibition clearance (full inspection with certification)", "70", 6, "pass", "p1v5"},
                {"With certification", "Paid prohibition clearance (full inspection with certification)", "70", 7, "pass", "p1v5"},
                {"With certification", "Paid prohibition clearance (full inspection with certification)", "70", 8, "pass", "p1v5"},
                {"With certification", "Paid prohibition clearance (full inspection with certification)", "70", 9, "pass", "p1v5"},
                {"With certification", "Paid prohibition clearance (full inspection with certification)", "70", 10, "pass", "p1v5"},
                {"With certification", "Paid prohibition clearance (retest with certification)", "76", 2, "pass", "p3v2"},
                {"With certification", "Paid prohibition clearance (retest with certification)", "76", 3, "pass", "p3v3"},
                {"With certification", "Paid prohibition clearance (retest with certification)", "76", 4, "pass", "p3v4"},
                {"With certification", "Paid prohibition clearance (retest with certification)", "76", 5, "pass", "p3v5"},
                {"With certification", "Paid prohibition clearance (retest with certification)", "76", 6, "pass", "p3v5"},
                {"With certification", "Paid prohibition clearance (retest with certification)", "76", 7, "pass", "p3v5"},
                {"With certification", "Paid prohibition clearance (retest with certification)", "76", 8, "pass", "p3v5"},
                {"With certification", "Paid prohibition clearance (retest with certification)", "76", 9, "pass", "p3v5"},
                {"With certification", "Paid prohibition clearance (retest with certification)", "76", 10, "pass", "p3v5"},
                {"With certification", "Part paid prohibition clearance (retest with certification)", "79", 2, "pass", "p6v2"},
                {"With certification", "Part paid prohibition clearance (retest with certification)", "79", 3, "pass", "p6v3"},
                {"With certification", "Part paid prohibition clearance (retest with certification)", "79", 4, "pass", "p6v4"},
                {"With certification", "Part paid prohibition clearance (retest with certification)", "79", 5, "pass", "p6v5"},
                {"With certification", "Part paid prohibition clearance (retest with certification)", "79", 6, "pass", "p6v5"},
                {"With certification", "Part paid prohibition clearance (retest with certification)", "79", 7, "pass", "p6v5"},
                {"With certification", "Part paid prohibition clearance (retest with certification)", "79", 8, "pass", "p6v5"},
                {"With certification", "Part paid prohibition clearance (retest with certification)", "79", 9, "pass", "p6v5"},
                {"With certification", "Part paid prohibition clearance (retest with certification)", "79", 10, "pass", "p6v5"},

                {"First test", "First test", "95", 2, "prs", "ffv2"},
                {"First test", "First test", "95", 3, "prs", "ffv3"},
                {"First test", "First test", "95", 4, "prs", "ffv4"},
                {"First test", "First test", "95", 5, "prs", "ffv5"},
                {"First test", "First test", "95", 6, "prs", "ffv5"},
                {"First test", "First test", "95", 7, "prs", "ffv5"},
                {"First test", "First test", "95", 8, "prs", "ffv5"},
                {"First test", "First test", "95", 9, "prs", "ffv5"},
                {"First test", "First test", "95", 10, "prs", "ffv5"},
                {"Paid", "Paid first test retest", "65", 2, "prs", "rgv2"},
                {"Paid", "Paid first test retest", "65", 3, "prs", "rgv3"},
                {"Paid", "Paid first test retest", "65", 4, "prs", "rgv4"},
                {"Paid", "Paid first test retest", "65", 5, "prs", "rgv5"},
                {"Paid", "Paid first test retest", "65", 6, "prs", "rgv5"},
                {"Paid", "Paid first test retest", "65", 7, "prs", "rgv5"},
                {"Paid", "Paid first test retest", "65", 8, "prs", "rgv5"},
                {"Paid", "Paid first test retest", "65", 9, "prs", "rgv5"},
                {"Paid", "Paid first test retest", "65", 10, "prs", "rgv5"},
                {"Part paid", "Part paid first test retest", "66", 2, "prs", "riv2"},
                {"Part paid", "Part paid first test retest", "66", 3, "prs", "riv3"},
                {"Part paid", "Part paid first test retest", "66", 4, "prs", "riv4"},
                {"Part paid", "Part paid first test retest", "66", 5, "prs", "riv5"},
                {"Part paid", "Part paid first test retest", "66", 6, "prs", "riv5"},
                {"Part paid", "Part paid first test retest", "66", 7, "prs", "riv5"},
                {"Part paid", "Part paid first test retest", "66", 8, "prs", "riv5"},
                {"Part paid", "Part paid first test retest", "66", 9, "prs", "riv5"},
                {"Part paid", "Part paid first test retest", "66", 10, "prs", "riv5"},
                {"Full Inspection/ Fee - With certification", "Paid prohibition clearance on first test (full inspection with certification)", "82", 2, "prs", "p7v2"},
                {"Full Inspection/ Fee - With certification", "Paid prohibition clearance on first test (full inspection with certification)", "82", 3, "prs", "p7v3"},
                {"Full Inspection/ Fee - With certification", "Paid prohibition clearance on first test (full inspection with certification)", "82", 4, "prs", "p7v4"},
                {"Full Inspection/ Fee - With certification", "Paid prohibition clearance on first test (full inspection with certification)", "82", 5, "prs", "p7v5"},
                {"Full Inspection/ Fee - With certification", "Paid prohibition clearance on first test (full inspection with certification)", "82", 6, "prs", "p7v5"},
                {"Full Inspection/ Fee - With certification", "Paid prohibition clearance on first test (full inspection with certification)", "82", 7, "prs", "p7v5"},
                {"Full Inspection/ Fee - With certification", "Paid prohibition clearance on first test (full inspection with certification)", "82", 8, "prs", "p7v5"},
                {"Full Inspection/ Fee - With certification", "Paid prohibition clearance on first test (full inspection with certification)", "82", 9, "prs", "p7v5"},
                {"Full Inspection/ Fee - With certification", "Paid prohibition clearance on first test (full inspection with certification)", "82", 10, "prs", "p7v5"},
                {"Retest - Paid", "Paid retest prohibition clearance on first test", "83", 2, "prs", "p4v2"},
                {"Retest - Paid", "Paid retest prohibition clearance on first test", "83", 3, "prs", "p4v3"},
                {"Retest - Paid", "Paid retest prohibition clearance on first test", "83", 4, "prs", "p4v4"},
                {"Retest - Paid", "Paid retest prohibition clearance on first test", "83", 5, "prs", "p4v5"},
                {"Retest - Paid", "Paid retest prohibition clearance on first test", "83", 6, "prs", "p4v5"},
                {"Retest - Paid", "Paid retest prohibition clearance on first test", "83", 7, "prs", "p4v5"},
                {"Retest - Paid", "Paid retest prohibition clearance on first test", "83", 8, "prs", "p4v5"},
                {"Retest - Paid", "Paid retest prohibition clearance on first test", "83", 9, "prs", "p4v5"},
                {"Retest - Paid", "Paid retest prohibition clearance on first test", "83", 10, "prs", "p4v5"},
                {"Annual test", "Annual test", "94", 2, "prs", "aav2"},
                {"Annual test", "Annual test", "94", 3, "prs", "aav3"},
                {"Annual test", "Annual test", "94", 4, "prs", "aav4"},
                {"Annual test", "Annual test", "94", 5, "prs", "aav5"},
                {"Annual test", "Annual test", "94", 6, "prs", "aav5"},
                {"Annual test", "Annual test", "94", 7, "prs", "aav5"},
                {"Annual test", "Annual test", "94", 8, "prs", "aav5"},
                {"Annual test", "Annual test", "94", 9, "prs", "aav5"},
                {"Annual test", "Annual test", "94", 10, "prs", "aav5"},
                {"Paid", "Paid annual test retest", "53", 2, "prs", "rpv2"},
                {"Paid", "Paid annual test retest", "53", 3, "prs", "rpv3"},
                {"Paid", "Paid annual test retest", "53", 4, "prs", "rpv4"},
                {"Paid", "Paid annual test retest", "53", 5, "prs", "rpv5"},
                {"Paid", "Paid annual test retest", "53", 6, "prs", "rpv5"},
                {"Paid", "Paid annual test retest", "53", 7, "prs", "rpv5"},
                {"Paid", "Paid annual test retest", "53", 8, "prs", "rpv5"},
                {"Paid", "Paid annual test retest", "53", 9, "prs", "rpv5"},
                {"Paid", "Paid annual test retest", "53", 10, "prs", "rpv5"},
                {"Part paid", "Part paid annual test retest", "54", 2, "prs", "rsv2"},
                {"Part paid", "Part paid annual test retest", "54", 3, "prs", "rsv3"},
                {"Part paid", "Part paid annual test retest", "54", 4, "prs", "rsv4"},
                {"Part paid", "Part paid annual test retest", "54", 5, "prs", "rsv5"},
                {"Part paid", "Part paid annual test retest", "54", 6, "prs", "rsv5"},
                {"Part paid", "Part paid annual test retest", "54", 7, "prs", "rsv5"},
                {"Part paid", "Part paid annual test retest", "54", 8, "prs", "rsv5"},
                {"Part paid", "Part paid annual test retest", "54", 9, "prs", "rsv5"},
                {"Part paid", "Part paid annual test retest", "54", 10, "prs", "rsv5"},
                {"With certification", "Paid prohibition clearance (full inspection with certification)", "70", 2, "prs", "p1v2"},
                {"With certification", "Paid prohibition clearance (full inspection with certification)", "70", 3, "prs", "p1v3"},
                {"With certification", "Paid prohibition clearance (full inspection with certification)", "70", 4, "prs", "p1v4"},
                {"With certification", "Paid prohibition clearance (full inspection with certification)", "70", 5, "prs", "p1v5"},
                {"With certification", "Paid prohibition clearance (full inspection with certification)", "70", 6, "prs", "p1v5"},
                {"With certification", "Paid prohibition clearance (full inspection with certification)", "70", 7, "prs", "p1v5"},
                {"With certification", "Paid prohibition clearance (full inspection with certification)", "70", 8, "prs", "p1v5"},
                {"With certification", "Paid prohibition clearance (full inspection with certification)", "70", 9, "prs", "p1v5"},
                {"With certification", "Paid prohibition clearance (full inspection with certification)", "70", 10, "prs", "p1v5"},
                {"With certification", "Paid prohibition clearance (retest with certification)", "76", 2, "prs", "p3v2"},
                {"With certification", "Paid prohibition clearance (retest with certification)", "76", 3, "prs", "p3v3"},
                {"With certification", "Paid prohibition clearance (retest with certification)", "76", 4, "prs", "p3v4"},
                {"With certification", "Paid prohibition clearance (retest with certification)", "76", 5, "prs", "p3v5"},
                {"With certification", "Paid prohibition clearance (retest with certification)", "76", 6, "prs", "p3v5"},
                {"With certification", "Paid prohibition clearance (retest with certification)", "76", 7, "prs", "p3v5"},
                {"With certification", "Paid prohibition clearance (retest with certification)", "76", 8, "prs", "p3v5"},
                {"With certification", "Paid prohibition clearance (retest with certification)", "76", 9, "prs", "p3v5"},
                {"With certification", "Paid prohibition clearance (retest with certification)", "76", 10, "prs", "p3v5"},
                {"With certification", "Part paid prohibition clearance (retest with certification)", "79", 2, "prs", "p6v2"},
                {"With certification", "Part paid prohibition clearance (retest with certification)", "79", 3, "prs", "p6v3"},
                {"With certification", "Part paid prohibition clearance (retest with certification)", "79", 4, "prs", "p6v4"},
                {"With certification", "Part paid prohibition clearance (retest with certification)", "79", 5, "prs", "p6v5"},
                {"With certification", "Part paid prohibition clearance (retest with certification)", "79", 6, "prs", "p6v5"},
                {"With certification", "Part paid prohibition clearance (retest with certification)", "79", 7, "prs", "p6v5"},
                {"With certification", "Part paid prohibition clearance (retest with certification)", "79", 8, "prs", "p6v5"},
                {"With certification", "Part paid prohibition clearance (retest with certification)", "79", 9, "prs", "p6v5"},
                {"With certification", "Part paid prohibition clearance (retest with certification)", "79", 10, "prs", "p6v5"}
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

//    @WithTag("annual_certificates")
    @WithTag("In_test")
    @Title("CVSB-8798 - Annual certificate is generate for all Hgv tests ")
    @Test
    public void testResults_Annual_Certificate_Generation_Hgv() {

        // Read the base test result JSON.
        String testResultRecord = GenericData.readJsonValueFromFile("test-results_post_expiry_date_hgv_8798.json", "$");

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
                testResultsSteps.statusCodeShouldBe(201);
            } catch (Exception e) {
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
