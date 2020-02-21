package testresults;

import data.GenericData;
import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.junit.annotations.TestData;
import org.apache.commons.lang3.RandomStringUtils;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import steps.TestResultsSteps;
import util.JsonPathAlteration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@RunWith(SerenityParameterizedRunner.class)
public class PostTestResultsExpiryDateLogicTrlPreservation {

    @Steps
    TestResultsSteps testResultsSteps;

    @TestData
    public static Collection<Object[]> testData(){
        return Arrays.asList(new Object[][]{

                {"Annual test", "Annual test", "40", 1, "pass", "aat1"},
//                {"Annual test", "Annual test", "94", 2, "pass", "aat2"},
//                {"Annual test", "Annual test", "94", 3, "pass", "aat3"},
//                {"Annual test", "Annual test", "94", 4, "pass", "aat4"},
//                {"Annual test", "Annual test", "94", 5, "pass", "aat5"},
//                {"Annual test", "Annual test", "94", 6, "pass", "aat5"},
//                {"Annual test", "Annual test", "94", 7, "pass", "aat5"},
//                {"Annual test", "Annual test", "94", 8, "pass", "aat5"},
//                {"Annual test", "Annual test", "94", 9, "pass", "aat5"},
//                {"Annual test", "Annual test", "94", 10, "pass", "aat5"},
//                {"Paid", "Paid annual test retest", "98", 1, "pass", "rpt1"},
//                {"Paid", "Paid annual test retest", "53", 2, "pass", "rpt2"},
//                {"Paid", "Paid annual test retest", "53", 3, "pass", "rpt3"},
//                {"Paid", "Paid annual test retest", "53", 4, "pass", "rpt4"},
//                {"Paid", "Paid annual test retest", "53", 5, "pass", "rpt5"},
//                {"Paid", "Paid annual test retest", "53", 6, "pass", "rpt5"},
//                {"Paid", "Paid annual test retest", "53", 7, "pass", "rpt5"},
//                {"Paid", "Paid annual test retest", "53", 8, "pass", "rpt5"},
//                {"Paid", "Paid annual test retest", "53", 9, "pass", "rpt5"},
//                {"Paid", "Paid annual test retest", "53", 10, "pass", "rpt5"},
//                {"Part paid", "Part paid annual test retest", "99", 1, "pass", "rst1"},
//                {"Part paid", "Part paid annual test retest", "54", 2, "pass", "rst2"},
//                {"Part paid", "Part paid annual test retest", "54", 3, "pass", "rst3"},
//                {"Part paid", "Part paid annual test retest", "54", 4, "pass", "rst4"},
//                {"Part paid", "Part paid annual test retest", "54", 5, "pass", "rst5"},
//                {"Part paid", "Part paid annual test retest", "54", 6, "pass", "rst5"},
//                {"Part paid", "Part paid annual test retest", "54", 7, "pass", "rst5"},
//                {"Part paid", "Part paid annual test retest", "54", 8, "pass", "rst5"},
//                {"Part paid", "Part paid annual test retest", "54", 9, "pass", "rst5"},
//                {"Part paid", "Part paid annual test retest", "54", 10, "pass", "rst5"},
//                {"With certification", "Paid prohibition clearance (full inspection with certification)", "107", 1, "pass", "p1t1"},
//                {"With certification", "Paid prohibition clearance (full inspection with certification)", "70", 2, "pass", "p1t2"},
//                {"With certification", "Paid prohibition clearance (full inspection with certification)", "70", 3, "pass", "p1t3"},
//                {"With certification", "Paid prohibition clearance (full inspection with certification)", "70", 4, "pass", "p1t4"},
//                {"With certification", "Paid prohibition clearance (full inspection with certification)", "70", 5, "pass", "p1t5"},
//                {"With certification", "Paid prohibition clearance (full inspection with certification)", "70", 6, "pass", "p1t5"},
//                {"With certification", "Paid prohibition clearance (full inspection with certification)", "70", 7, "pass", "p1t5"},
//                {"With certification", "Paid prohibition clearance (full inspection with certification)", "70", 8, "pass", "p1t5"},
//                {"With certification", "Paid prohibition clearance (full inspection with certification)", "70", 9, "pass", "p1t5"},
//                {"With certification", "Paid prohibition clearance (retest with certification)", "113", 1, "pass", "p3t1"},
//                {"With certification", "Paid prohibition clearance (retest with certification)", "76", 2, "pass", "p3t2"},
//                {"With certification", "Paid prohibition clearance (retest with certification)", "76", 3, "pass", "p3t3"},
//                {"With certification", "Paid prohibition clearance (retest with certification)", "76", 4, "pass", "p3t4"},
//                {"With certification", "Paid prohibition clearance (retest with certification)", "76", 5, "pass", "p3t5"},
//                {"With certification", "Paid prohibition clearance (retest with certification)", "76", 6, "pass", "p3t5"},
//                {"With certification", "Paid prohibition clearance (retest with certification)", "76", 7, "pass", "p3t5"},
//                {"With certification", "Paid prohibition clearance (retest with certification)", "76", 8, "pass", "p3t5"},
//                {"With certification", "Paid prohibition clearance (retest with certification)", "76", 9, "pass", "p3t5"},
//                {"With certification", "Paid prohibition clearance (retest with certification)", "76", 10, "pass", "p3t5"},

//                {"With certification", "Part paid prohibition clearance (retest with certification)", "116", 1, "pass", "p6t1"},
//                {"With certification", "Part paid prohibition clearance (retest with certification)", "79", 2, "pass", "p6t2"},
//                {"With certification", "Part paid prohibition clearance (retest with certification)", "79", 3, "pass", "p6t3"},
//                {"With certification", "Part paid prohibition clearance (retest with certification)", "79", 4, "pass", "p6t4"},
//                {"With certification", "Part paid prohibition clearance (retest with certification)", "79", 5, "pass", "p6t5"},
//                {"With certification", "Part paid prohibition clearance (retest with certification)", "79", 6, "pass", "p6t5"},
//                {"With certification", "Part paid prohibition clearance (retest with certification)", "79", 7, "pass", "p6t5"},
//                {"With certification", "Part paid prohibition clearance (retest with certification)", "79", 8, "pass", "p6t5"},
//                {"With certification", "Part paid prohibition clearance (retest with certification)", "79", 9, "pass", "p6t5"},
//                {"With certification", "Part paid prohibition clearance (retest with certification)", "79", 10, "pass", "p6t5"},

                {"Annual test", "Annual test", "40", 1, "prs", "aat1"},
//                {"Annual test", "Annual test", "94", 2, "prs", "aat2"},
//                {"Annual test", "Annual test", "94", 3, "prs", "aat3"},
//                {"Annual test", "Annual test", "94", 4, "prs", "aat4"},
//                {"Annual test", "Annual test", "94", 5, "prs", "aat5"},
//                {"Annual test", "Annual test", "94", 6, "prs", "aat5"},
//                {"Annual test", "Annual test", "94", 7, "prs", "aat5"},
//                {"Annual test", "Annual test", "94", 8, "prs", "aat5"},
//                {"Annual test", "Annual test", "94", 9, "prs", "aat5"},
//                {"Annual test", "Annual test", "94", 10, "prs", "aat5"},
//                {"Paid", "Paid annual test retest", "98", 1, "prs", "rpt1"},
//                {"Paid", "Paid annual test retest", "53", 2, "prs", "rpt2"},
//                {"Paid", "Paid annual test retest", "53", 3, "prs", "rpt3"},
//                {"Paid", "Paid annual test retest", "53", 4, "prs", "rpt4"},
//                {"Paid", "Paid annual test retest", "53", 5, "prs", "rpt5"},
//                {"Paid", "Paid annual test retest", "53", 6, "prs", "rpt5"},
//                {"Paid", "Paid annual test retest", "53", 7, "prs", "rpt5"},
//                {"Paid", "Paid annual test retest", "53", 8, "prs", "rpt5"},
//                {"Paid", "Paid annual test retest", "53", 9, "prs", "rpt5"},
//                {"Paid", "Paid annual test retest", "53", 10, "prs", "rpt5"},
//                {"Part paid", "Part paid annual test retest", "99", 1, "prs", "rst1"},
//                {"Part paid", "Part paid annual test retest", "54", 2, "prs", "rst2"},
//                {"Part paid", "Part paid annual test retest", "54", 3, "prs", "rst3"},
//                {"Part paid", "Part paid annual test retest", "54", 4, "prs", "rst4"},
//                {"Part paid", "Part paid annual test retest", "54", 5, "prs", "rst5"},
//                {"Part paid", "Part paid annual test retest", "54", 6, "prs", "rst5"},
//                {"Part paid", "Part paid annual test retest", "54", 7, "prs", "rst5"},
//                {"Part paid", "Part paid annual test retest", "54", 8, "prs", "rst5"},
//                {"Part paid", "Part paid annual test retest", "54", 9, "prs", "rst5"},
//                {"Part paid", "Part paid annual test retest", "54", 10, "prs", "rst5"},
//                {"With certification", "Paid prohibition clearance (full inspection with certification)", "107", 1, "prs", "p1t1"},
//                {"With certification", "Paid prohibition clearance (full inspection with certification)", "70", 2, "prs", "p1t2"},
//                {"With certification", "Paid prohibition clearance (full inspection with certification)", "70", 3, "prs", "p1t3"},
//                {"With certification", "Paid prohibition clearance (full inspection with certification)", "70", 4, "prs", "p1t4"},
//                {"With certification", "Paid prohibition clearance (full inspection with certification)", "70", 5, "prs", "p1t5"},
//                {"With certification", "Paid prohibition clearance (full inspection with certification)", "70", 6, "prs", "p1t5"},
//                {"With certification", "Paid prohibition clearance (full inspection with certification)", "70", 7, "prs", "p1t5"},
//                {"With certification", "Paid prohibition clearance (full inspection with certification)", "70", 8, "prs", "p1t5"},
//                {"With certification", "Paid prohibition clearance (full inspection with certification)", "70", 9, "prs", "p1t5"},
//                {"With certification", "Paid prohibition clearance (retest with certification)", "113", 1, "prs", "p3t1"},
//                {"With certification", "Paid prohibition clearance (retest with certification)", "76", 2, "prs", "p3t2"},
//                {"With certification", "Paid prohibition clearance (retest with certification)", "76", 3, "prs", "p3t3"},
//                {"With certification", "Paid prohibition clearance (retest with certification)", "76", 4, "prs", "p3t4"},
//                {"With certification", "Paid prohibition clearance (retest with certification)", "76", 5, "prs", "p3t5"},
//                {"With certification", "Paid prohibition clearance (retest with certification)", "76", 6, "prs", "p3t5"},
//                {"With certification", "Paid prohibition clearance (retest with certification)", "76", 7, "prs", "p3t5"},
//                {"With certification", "Paid prohibition clearance (retest with certification)", "76", 8, "prs", "p3t5"},
//                {"With certification", "Paid prohibition clearance (retest with certification)", "76", 9, "prs", "p3t5"},
//                {"With certification", "Paid prohibition clearance (retest with certification)", "76", 10, "prs", "p3t5"},
//                {"With certification", "Part paid prohibition clearance (retest with certification)", "116", 1, "prs", "p6t1"},
//                {"With certification", "Part paid prohibition clearance (retest with certification)", "79", 2, "prs", "p6t2"},
//                {"With certification", "Part paid prohibition clearance (retest with certification)", "79", 3, "prs", "p6t3"},
//                {"With certification", "Part paid prohibition clearance (retest with certification)", "79", 4, "prs", "p6t4"},
//                {"With certification", "Part paid prohibition clearance (retest with certification)", "79", 5, "prs", "p6t5"},
//                {"With certification", "Part paid prohibition clearance (retest with certification)", "79", 6, "prs", "p6t5"},
//                {"With certification", "Part paid prohibition clearance (retest with certification)", "79", 7, "prs", "p6t5"},
//                {"With certification", "Part paid prohibition clearance (retest with certification)", "79", 8, "prs", "p6t5"},
//                {"With certification", "Part paid prohibition clearance (retest with certification)", "79", 9, "prs", "p6t5"},
//                {"With certification", "Part paid prohibition clearance (retest with certification)", "79", 10, "prs", "p6t5"},

        });
    }

    private String name;
    private String testTypeName;
    private String testTypeId;
    private int noOfAxles;
    private String testResult;
    private String testCode;

    public PostTestResultsExpiryDateLogicTrlPreservation(String name, String testTypeName, String testTypeId, int noOfAxles, String testResult, String testCode) {
        this.name = name;
        this.testTypeName = testTypeName;
        this.testTypeId = testTypeId;
        this.noOfAxles = noOfAxles;
        this.testResult = testResult;
        this.testCode = testCode;
    }

    @WithTag("expiry_dates")
    @Title("CVSB-8684 - TC1 - AC1 - TRL Preservation Test - NO Previous Expiry Date")
    @Test
    public void testResultsNoFirstTestExpiryTrlMoreThanOneYear() {

        // Read the base test result JSON.
        String testResultRecord = GenericData.readJsonValueFromFile("test-results_expiry_date_trl_8684.json", "$");

        DateTime currentTime = DateTime.now().withZone(DateTimeZone.UTC);
        DateTime submittedTestStartTimestamp = currentTime.minusMinutes(15);
        DateTime submittedTestTypeStartTimestamp = currentTime.minusMinutes(10);
        DateTime submittedTypeEndTimestamp = currentTime.minus(5);
        DateTime submittedEndTimestamp = currentTime;

        // Create alteration to add one more tech record to in the request body
        String testStartTimestamp = submittedTestStartTimestamp.toInstant().toString();
        String testTypeStartTimestamp = submittedTestTypeStartTimestamp.toInstant().toString();
        String testTypeEndTimestamp = submittedTypeEndTimestamp.toInstant().toString();
        String testEndTimestamp = submittedTypeEndTimestamp.toInstant().toString();

        String randomVin = GenericData.generateRandomVin();
        String randomTestResultId = RandomStringUtils.randomNumeric(5);

        String testExpectedDate = currentTime.dayOfMonth().withMaximumValue().plusYears(1).toInstant().toString();


//        String returned_testTypeEndTimestamp = "";
//        String returned_testExpiryDate = "";
//        String returned_createdAt = "";
//        String returned_testTypeStartTimestamp = "";

//        System.out.println("submittedFirstUseDate: " + firstUseDate);
//        System.out.println("submittedTestStartTimestamp: " + testStartTimestamp);
//        System.out.println("submittedTestTypeStartTimestamp: " + testTypeStartTimestamp);
//        System.out.println("submittedTestTypeEndTimestamp: " + testTypeEndTimestamp);
//        System.out.println("submittedTestEndTimestamp: " + testEndTimestamp);
//        System.out.println("returned_testAnniversaryDate: " + testExpectedDate);

        JsonPathAlteration alterationFirstUseDate = new JsonPathAlteration("$.firstUseDate", "", "", "DELETE");
        JsonPathAlteration alterationTestStartTimestamp = new JsonPathAlteration("$.testStartTimestamp", testStartTimestamp, "", "REPLACE");
        JsonPathAlteration alterationTestEndTimestamp = new JsonPathAlteration("$.testEndTimestamp", testEndTimestamp, "", "REPLACE");
        JsonPathAlteration alterationTestTypeStartTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeStartTimestamp", testTypeStartTimestamp, "", "REPLACE");
        JsonPathAlteration alterationTestTypeEndTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeEndTimestamp", testTypeEndTimestamp, "", "REPLACE");
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationTestResultId = new JsonPathAlteration("$.testResultId", randomTestResultId, "", "REPLACE");
        JsonPathAlteration alterationTestExpiryDate = new JsonPathAlteration("$.testTypes[0].testExpiryDate", "", "", "DELETE");

        JsonPathAlteration alterationTestName = new JsonPathAlteration("$.testTypes[0].name", name,"","REPLACE");
        JsonPathAlteration alterationTestTypeId = new JsonPathAlteration("$.testTypes[0].testTypeId", testTypeId,"","REPLACE");
        JsonPathAlteration alterationTestTypeName = new JsonPathAlteration("$.testTypes[0].testTypeName", testTypeName,"","REPLACE");
        JsonPathAlteration alterationTestResult = new JsonPathAlteration("$.testTypes[0].testResult", testResult,"","REPLACE");
        JsonPathAlteration alterationNoOfAxles = new JsonPathAlteration("$.noOfAxles", noOfAxles,"","REPLACE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(
                alterationFirstUseDate,
                alterationTestStartTimestamp,
                alterationTestEndTimestamp,
                alterationTestTypeStartTimestamp,
                alterationTestTypeEndTimestamp,
                alterationVin,
                alterationTestResultId,
                alterationTestName,
                alterationTestTypeId,
                alterationTestTypeName,
                alterationNoOfAxles,
                alterationTestExpiryDate,
                alterationTestResult
        ));

        // Post the results, together with any alterations, and verify that they are accepted.
        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, alterations);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");

        // Retrieve the created record, and verify that the fields are present.
        testResultsSteps.getTestResults(randomVin);
        testResultsSteps.statusCodeShouldBe(200);

        // Verify testCode field has the expected value
        testResultsSteps.valueForFieldInPathShouldBe("[0].testTypes[0].testCode", testCode);

        // Verify firstUseDate is
//        testResultsSteps.valueForFieldInPathShouldBe("[0].firstUseDate", firstUseDate);

        // Verify testTypeEndTimestamp is
        testResultsSteps.valueForFieldInPathShouldBe("[0].testTypes[0].testTypeEndTimestamp", testTypeEndTimestamp);

        // Verify testAnniversaryDate field has the expected value
        testResultsSteps.valueForFieldInPathShouldBe("[0].testTypes[0].testAnniversaryDate", testExpectedDate);

        // Verify testExpiryDate field has the expected value
        testResultsSteps.valueForFieldInPathShouldStartWith("[0].testTypes[0].testExpiryDate", testExpectedDate.substring(0,10));

    }

}
