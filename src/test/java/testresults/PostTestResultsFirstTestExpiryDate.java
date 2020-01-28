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
import org.junit.Test;
import org.junit.runner.RunWith;
import steps.TestResultsSteps;
import util.JsonPathAlteration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@RunWith(SerenityParameterizedRunner.class)
public class PostTestResultsFirstTestExpiryDate {

    @Steps
    TestResultsSteps testResultsSteps;

    @TestData
    public static Collection<Object[]> testData(){
        return Arrays.asList(new Object[][]{
                {"First test", "95", "First test", "pass", 1},
                {"Retest", "65", "Paid first test retest", "pass", 1},
                {"Retest", "66", "Paid prohibition clearance on first test (full inspection with certification)", "pass", 1},
                {"Prohibition clearance", "83", "Part paid first test retest", "pass", 1},
                {"Prohibition clearance", "82", "Paid prohibition clearance on first test (full inspection with certification)", "pass", 1},
                {"First test", "95", "First test", "prs", 1},
                {"Retest", "65", "Paid first test retest", "prs", 1},
                {"Retest", "66", "Paid prohibition clearance on first test (full inspection with certification)", "prs", 1},
                {"Prohibition clearance", "83", "Part paid first test retest", "prs", 1},
                {"Prohibition clearance", "82", "Paid prohibition clearance on first test (full inspection with certification)", "prs", 1},
                {"First test", "95", "First test", "pass", -1},
                {"Retest", "65", "Paid first test retest", "pass", -1},
                {"Retest", "66", "Paid prohibition clearance on first test (full inspection with certification)", "pass", -1},
                {"Prohibition clearance", "83", "Part paid first test retest", "pass", -1},
                {"Prohibition clearance", "82", "Paid prohibition clearance on first test (full inspection with certification)", "pass", -1},
                {"First test", "95", "First test", "prs", -1},
                {"Retest", "65", "Paid first test retest", "prs", -1},
                {"Retest", "66", "Paid prohibition clearance on first test (full inspection with certification)", "prs", -1},
                {"Prohibition clearance", "83", "Part paid first test retest", "prs", -1},
                {"Prohibition clearance", "82", "Paid prohibition clearance on first test (full inspection with certification)", "prs", -1}
        });
    }

    private String name;
    private String testTypeId;
    private String testTypeName;
    private String testResult;
    //sets the firstUseDate / regnDate one year before the current date, plus/minus one day
    private int dayOffset;

    public PostTestResultsFirstTestExpiryDate(String name, String testTypeId, String testTypeName, String testResult, int dayOffset) {
        this.name = name;
        this.testTypeId = testTypeId;
        this.testTypeName = testTypeName;
        this.testResult = testResult;
        this.dayOffset = dayOffset;
    }

    @WithTag("Expiry_Dates")
    @Title("CVSB-9187 - TC1 - AC1 - FIRST TEST/FIRST TEST RETEST CONDUCTED AFTER THE ANNIVERSARY DATE - TRL")
    @Test
    public void testResultsFirstTestExpiryTrl() {

        // Read the base test result JSON.
        String testResultRecord = GenericData.readJsonValueFromFile("test-results_first_expiry_TRL.json", "$");

        DateTime submittedTestStartTimestamp = DateTime.now().withZone(DateTimeZone.UTC);
        DateTime submittedTestTypeStartTimestamp = submittedTestStartTimestamp.plusMinutes(5);
        DateTime submittedTypeEndTimestamp = submittedTestTypeStartTimestamp.plusMinutes(10);
        DateTime submittedEndTimestamp = submittedTypeEndTimestamp.plusMinutes(5);

        // Create alteration to add one more tech record to in the request body
        String firstUseDate = submittedEndTimestamp.toDateTime().minusYears(1).plusDays(dayOffset).toString("yyyy-MM-dd");
        String testStartTimestamp = submittedTestStartTimestamp.toInstant().toString();
        String testTypeStartTimestamp = submittedTestTypeStartTimestamp.toInstant().toString();
        String testTypeEndTimestamp = submittedTypeEndTimestamp.toInstant().toString();
        String testEndTimestamp = submittedEndTimestamp.toInstant().toString();
        String testExpectedDate = submittedTestStartTimestamp.dayOfMonth().withMaximumValue().plusYears(1).withTimeAtStartOfDay().toInstant().toString();
        String randomVin = GenericData.generateRandomVin();
        String randomTestResultId = RandomStringUtils.randomNumeric(5);


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

        JsonPathAlteration alterationFirstUseDate = new JsonPathAlteration("$.firstUseDate", firstUseDate, "", "REPLACE");
        JsonPathAlteration alterationTestStartTimestamp = new JsonPathAlteration("$.testStartTimestamp", testStartTimestamp, "", "REPLACE");
        JsonPathAlteration alterationTestEndTimestamp = new JsonPathAlteration("$.testEndTimestamp", testEndTimestamp, "", "REPLACE");
        JsonPathAlteration alterationTestTypeStartTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeStartTimestamp", testTypeStartTimestamp, "", "REPLACE");
        JsonPathAlteration alterationTestTypeEndTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeEndTimestamp", testTypeEndTimestamp, "", "REPLACE");
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationTestResultId = new JsonPathAlteration("$.testResultId", randomTestResultId, "", "REPLACE");
        JsonPathAlteration alterationTestName = new JsonPathAlteration("$.testTypes[0].name", name,"","REPLACE");
        JsonPathAlteration alterationTestTypeId = new JsonPathAlteration("$.testTypes[0].testTypeId", testTypeId,"","REPLACE");
        JsonPathAlteration alterationTestTypeName = new JsonPathAlteration("$.testTypes[0].testTypeName", testTypeName,"","REPLACE");
        JsonPathAlteration alterationTestResult = new JsonPathAlteration("$.testTypes[0].testResult", testResult,"","REPLACE");

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
                alterationTestResult
                ));

        // Post the results, together with any alterations, and verify that they are accepted.
        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, alterations);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");

        // Retrieve the created record, and verify that the fields are present.
        testResultsSteps.getTestResults(randomVin);
        testResultsSteps.statusCodeShouldBe(200);

        // Verify testAnniversaryDate field has the expected value
        testResultsSteps.valueForFieldInPathShouldBe("[0].testTypes[0].testAnniversaryDate", testExpectedDate);
        testResultsSteps.valueForFieldInPathShouldBe("[0].testTypes[0].testExpiryDate", testExpectedDate);
    }

    @WithTag("Expiry_Dates")
    @Title("CVSB-9187 - TC1 - AC2 - FIRST TEST/FIRST TEST RETEST CONDUCTED AFTER THE ANNIVERSARY DATE - HGV")
    @Test
    public void testResultsFirstTestExpiryHgv() {

        // Read the base test result JSON.
        String testResultRecord = GenericData.readJsonValueFromFile("test-results_first_expiry_HGV.json", "$");

        DateTime submittedTestStartTimestamp = DateTime.now().withZone(DateTimeZone.UTC);
        DateTime submittedTestTypeStartTimestamp = submittedTestStartTimestamp.plusMinutes(5);
        DateTime submittedTypeEndTimestamp = submittedTestTypeStartTimestamp.plusMinutes(10);
        DateTime submittedEndTimestamp = submittedTypeEndTimestamp.plusMinutes(5);

        // Create alteration to add one more tech record to in the request body
        String regnDate = submittedEndTimestamp.toDateTime().minusYears(1).minusDays(dayOffset).toString("yyyy-MM-dd");
        String testStartTimestamp = submittedTestStartTimestamp.toInstant().toString();
        String testTypeStartTimestamp = submittedTestTypeStartTimestamp.toInstant().toString();
        String testTypeEndTimestamp = submittedTypeEndTimestamp.toInstant().toString();
        String testEndTimestamp = submittedEndTimestamp.toInstant().toString();
        String testExpectedDate = submittedTestStartTimestamp.dayOfMonth().withMaximumValue().plusYears(1).withTimeAtStartOfDay().toInstant().toString();
        String randomVin = GenericData.generateRandomVin();
        String randomTestResultId = RandomStringUtils.randomNumeric(5);

//        String returned_testTypeEndTimestamp = "";
//        String returned_testExpiryDate = "";
//        String returned_createdAt = "";
//        String returned_testTypeStartTimestamp = "";

//        System.out.println("submittedRegnDate: " + regnDate);
//        System.out.println("submittedTestStartTimestamp: " + testStartTimestamp);
//        System.out.println("submittedTestTypeStartTimestamp: " + testTypeStartTimestamp);
//        System.out.println("submittedTestTypeEndTimestamp: " + testTypeEndTimestamp);
//        System.out.println("submittedTestEndTimestamp: " + testEndTimestamp);
//        System.out.println("returned_testAnniversaryDate: " + testExpectedDate);

        JsonPathAlteration alterationFirstUseDate = new JsonPathAlteration("$.regnDate", regnDate, "", "REPLACE");
        JsonPathAlteration alterationTestStartTimestamp = new JsonPathAlteration("$.testStartTimestamp", testStartTimestamp, "", "REPLACE");
        JsonPathAlteration alterationTestEndTimestamp = new JsonPathAlteration("$.testEndTimestamp", testEndTimestamp, "", "REPLACE");
        JsonPathAlteration alterationTestTypeStartTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeStartTimestamp", testTypeStartTimestamp, "", "REPLACE");
        JsonPathAlteration alterationTestTypeEndTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeEndTimestamp", testTypeEndTimestamp, "", "REPLACE");
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationTestResultId = new JsonPathAlteration("$.testResultId", randomTestResultId, "", "REPLACE");
        JsonPathAlteration alterationTestName = new JsonPathAlteration("$.testTypes[0].name", name,"","REPLACE");
        JsonPathAlteration alterationTestTypeId = new JsonPathAlteration("$.testTypes[0].testTypeId", testTypeId,"","REPLACE");
        JsonPathAlteration alterationTestTypeName = new JsonPathAlteration("$.testTypes[0].testTypeName", testTypeName,"","REPLACE");
        JsonPathAlteration alterationTestResult = new JsonPathAlteration("$.testTypes[0].testResult", testResult,"","REPLACE");

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
                alterationTestResult
                ));

        // Post the results, together with any alterations, and verify that they are accepted.
        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, alterations);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");

        // Retrieve the created record, and verify that the fields are present.
        testResultsSteps.getTestResults(randomVin);
        testResultsSteps.statusCodeShouldBe(200);

        // Verify testAnniversaryDate field has the expected value
        testResultsSteps.valueForFieldInPathShouldBe("[0].testTypes[0].testAnniversaryDate", testExpectedDate);
        testResultsSteps.valueForFieldInPathShouldBe("[0].testTypes[0].testExpiryDate", testExpectedDate);
    }

}
