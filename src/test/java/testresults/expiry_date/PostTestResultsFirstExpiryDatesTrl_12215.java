package testresults.expiry_date;

import data.GenericData;
import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.junit.annotations.TestData;
import net.thucydides.junit.annotations.UseTestDataFrom;
import org.apache.commons.lang3.RandomStringUtils;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import steps.TestResultsSteps;
import util.JsonPathAlteration;

import java.util.*;

@WithTag("expiry_dates")
@RunWith(SerenityParameterizedRunner.class)
@UseTestDataFrom(value="loader/testdata/test_data_expiry_date_first_test_trl_12215.csv")
public class PostTestResultsFirstExpiryDatesTrl_12215 {

    @Steps
    TestResultsSteps testResultsSteps;

    private String name;
    private String testTypeName;
    private String testTypeId;
    private int noOfAxles;
    private String testResult;
    private String testCode;

    public void setName(String name) {
        this.name = name;
    }

    public void setTestTypeName(String testTypeName) {
        this.testTypeName = testTypeName;
    }

    public void setTestTypeId(String testTypeId) {
        this.testTypeId = testTypeId;
    }

    public void setNoOfAxles(int noOfAxles) {
        this.noOfAxles = noOfAxles;
    }

    public void setTestResult(String testResult) {
        this.testResult = testResult;
    }

    public void setTestCode(String testCode) {
        this.testCode = testCode;
    }

    @WithTag("expiry_dates")
    @Title("CVSB-12215 - AS a VSA I want the first expiry date to be calculated using reg/ first use anniversary so that it is accurately recorded for annual tests - TRL - NO Previous Expiry Date - firstUseDate = null")
    @Test
    public void testResultsFirstTestExpiryTrlFirstDateNull() {

        // Read the base test result JSON.
        String testResultRecord = GenericData.readJsonValueFromFile("test-results_post_expiry_date_trl_12215.json", "$");

        DateTime currentTime = DateTime.now().withZone(DateTimeZone.UTC);
        DateTime submittedTestStartTimestamp = currentTime.minusMinutes(15);
        DateTime submittedTestTypeStartTimestamp = currentTime.minusMinutes(10);
        DateTime submittedTypeEndTimestamp = currentTime.minusMinutes(5);
        DateTime submittedEndTimestamp = currentTime;

        // Create alteration to add one more tech record to in the request body
        String firstUseDate = null;
        String testStartTimestamp = submittedTestStartTimestamp.toInstant().toString();
        String testTypeStartTimestamp = submittedTestTypeStartTimestamp.toInstant().toString();
        String testTypeEndTimestamp = submittedTypeEndTimestamp.toInstant().toString();
        String testEndTimestamp = submittedEndTimestamp.toInstant().toString();
        String randomVin = GenericData.generateRandomVin();
        String randomSystemNo = GenericData.generateRandomSystemNumber();
        String randomTestResultId = UUID.randomUUID().toString();

        //String expectedTestExpiryDate = submittedEndTimestamp.plusYears(1).dayOfMonth().withMaximumValue().withTimeAtStartOfDay().toInstant().toString();
        String expectedTestExpiryDate = submittedEndTimestamp.plusYears(1).dayOfMonth().withMaximumValue().toInstant().toString();

        JsonPathAlteration alterationFirstUseDate = new JsonPathAlteration("$.firstUseDate", firstUseDate, "", "REPLACE");
        JsonPathAlteration alterationTestStartTimestamp = new JsonPathAlteration("$.testStartTimestamp", testStartTimestamp, "", "REPLACE");
        JsonPathAlteration alterationTestEndTimestamp = new JsonPathAlteration("$.testEndTimestamp", testEndTimestamp, "", "REPLACE");
        JsonPathAlteration alterationTestTypeStartTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeStartTimestamp", testTypeStartTimestamp, "", "REPLACE");
        JsonPathAlteration alterationTestTypeEndTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeEndTimestamp", testTypeEndTimestamp, "", "REPLACE");
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationSysNo = new JsonPathAlteration("$.systemNumber", randomSystemNo, "", "REPLACE");
        JsonPathAlteration alterationTestResultId = new JsonPathAlteration("$.testResultId", randomTestResultId, "", "REPLACE");
        JsonPathAlteration alterationTestName = new JsonPathAlteration("$.testTypes[0].name", name,"","REPLACE");
        JsonPathAlteration alterationTestTypeId = new JsonPathAlteration("$.testTypes[0].testTypeId", testTypeId,"","REPLACE");
        JsonPathAlteration alterationTestTypeName = new JsonPathAlteration("$.testTypes[0].testTypeName", testTypeName,"","REPLACE");
        JsonPathAlteration alterationTestResult = new JsonPathAlteration("$.testTypes[0].testResult", testResult,"","REPLACE");
        JsonPathAlteration alterationNoOfAxles = new JsonPathAlteration("$.noOfAxles", noOfAxles,"","REPLACE");
        JsonPathAlteration alterationTestExpiryDate = new JsonPathAlteration("$.testTypes[0].testExpiryDate", "", "", "DELETE");

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
                alterationSysNo,
                alterationTestResult
        ));

        // Printing the scenario to the console
        System.out.println("\n******************************************************");
        System.out.println("Test code: " + testCode);
        System.out.println("First Use Date: " + "null");
        System.out.println("First Use Anniversary: " + "null");
        System.out.println("Today: " + currentTime.toInstant().toString().substring(0,10));
        System.out.println("Expected expiryDate: " + expectedTestExpiryDate.substring(0,10));
        System.out.println("******************************************************\n");

        // Post the results, together with any alterations, and verify that they are accepted.
        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, alterations);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");

        // Retrieve the created record, and verify that the fields are present.
        testResultsSteps.getTestResultsSysNumber(randomSystemNo);
        testResultsSteps.statusCodeShouldBe(200);

        // Verify testCode field has the expected value
        testResultsSteps.valueForFieldInPathShouldBe("[0].testTypes[0].testCode", testCode);

        // Verify firstUseDate is
        testResultsSteps.valueForFieldInPathShouldBe("[0].firstUseDate", firstUseDate);

        // Verify testTypeEndTimestamp is
        testResultsSteps.valueForFieldInPathShouldBe("[0].testTypes[0].testTypeEndTimestamp", testTypeEndTimestamp);

        // Verify testAnniversaryDate field has the expected value
        testResultsSteps.valueForFieldInPathShouldStartWith("[0].testTypes[0].testAnniversaryDate", expectedTestExpiryDate.substring(0,10));

        // Verify testExpiryDate field has the expected value
        testResultsSteps.valueForFieldInPathShouldStartWith("[0].testTypes[0].testExpiryDate", expectedTestExpiryDate.substring(0,10));
    }

    @WithTag("expiry_dates")
    @Title("CVSB-12215 - AS a VSA I want the first expiry date to be calculated using reg/ first use anniversary so that it is accurately recorded for annual tests - TRL - NO Previous Expiry Date - firstUseDate = missing")
    @Test
    public void testResultsFirstTestExpiryTrlFirstDateMissing() {

        // Read the base test result JSON.
        String testResultRecord = GenericData.readJsonValueFromFile("test-results_post_expiry_date_trl_12215.json", "$");

        DateTime currentTime = DateTime.now().withZone(DateTimeZone.UTC);
        DateTime submittedTestStartTimestamp = currentTime.minusMinutes(15);
        DateTime submittedTestTypeStartTimestamp = currentTime.minusMinutes(10);
        DateTime submittedTypeEndTimestamp = currentTime.minusMinutes(5);
        DateTime submittedEndTimestamp = currentTime;

        // Create alteration to add one more tech record to in the request body
        String testStartTimestamp = submittedTestStartTimestamp.toInstant().toString();
        String testTypeStartTimestamp = submittedTestTypeStartTimestamp.toInstant().toString();
        String testTypeEndTimestamp = submittedTypeEndTimestamp.toInstant().toString();
        String testEndTimestamp = submittedEndTimestamp.toInstant().toString();
        String randomVin = GenericData.generateRandomVin();
        String randomSystemNo = GenericData.generateRandomSystemNumber();
        String randomTestResultId = UUID.randomUUID().toString();

        //String expectedTestExpiryDate = submittedEndTimestamp.plusYears(1).dayOfMonth().withMaximumValue().withTimeAtStartOfDay().toInstant().toString();
        String expectedTestExpiryDate = submittedEndTimestamp.plusYears(1).dayOfMonth().withMaximumValue().toInstant().toString();

        JsonPathAlteration alterationFirstUseDate = new JsonPathAlteration("$.firstUseDate", "", "", "DELETE");
        JsonPathAlteration alterationTestStartTimestamp = new JsonPathAlteration("$.testStartTimestamp", testStartTimestamp, "", "REPLACE");
        JsonPathAlteration alterationTestEndTimestamp = new JsonPathAlteration("$.testEndTimestamp", testEndTimestamp, "", "REPLACE");
        JsonPathAlteration alterationTestTypeStartTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeStartTimestamp", testTypeStartTimestamp, "", "REPLACE");
        JsonPathAlteration alterationTestTypeEndTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeEndTimestamp", testTypeEndTimestamp, "", "REPLACE");
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationSysNo = new JsonPathAlteration("$.systemNumber", randomSystemNo, "", "REPLACE");
        JsonPathAlteration alterationTestResultId = new JsonPathAlteration("$.testResultId", randomTestResultId, "", "REPLACE");
        JsonPathAlteration alterationTestName = new JsonPathAlteration("$.testTypes[0].name", name,"","REPLACE");
        JsonPathAlteration alterationTestTypeId = new JsonPathAlteration("$.testTypes[0].testTypeId", testTypeId,"","REPLACE");
        JsonPathAlteration alterationTestTypeName = new JsonPathAlteration("$.testTypes[0].testTypeName", testTypeName,"","REPLACE");
        JsonPathAlteration alterationTestResult = new JsonPathAlteration("$.testTypes[0].testResult", testResult,"","REPLACE");
        JsonPathAlteration alterationNoOfAxles = new JsonPathAlteration("$.noOfAxles", noOfAxles,"","REPLACE");
        JsonPathAlteration alterationTestExpiryDate = new JsonPathAlteration("$.testTypes[0].testExpiryDate", "", "", "DELETE");

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
                alterationSysNo,
                alterationTestResult
        ));

        // Printing the scenario to the console
        System.out.println("\n******************************************************");
        System.out.println("Test code: " + testCode);
        System.out.println("First Use Date: " + "null");
        System.out.println("First Use Anniversary: " + "null");
        System.out.println("Today: " + currentTime.toInstant().toString().substring(0,10));
        System.out.println("Expected expiryDate: " + expectedTestExpiryDate.substring(0,10));
        System.out.println("******************************************************\n");

        // Post the results, together with any alterations, and verify that they are accepted.
        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, alterations);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");

        // Retrieve the created record, and verify that the fields are present.
        testResultsSteps.getTestResultsSysNumber(randomSystemNo);
        testResultsSteps.statusCodeShouldBe(200);

        // Verify testCode field has the expected value
        testResultsSteps.valueForFieldInPathShouldBe("[0].testTypes[0].testCode", testCode);

        // Verify testTypeEndTimestamp is
        testResultsSteps.valueForFieldInPathShouldBe("[0].testTypes[0].testTypeEndTimestamp", testTypeEndTimestamp);

        // Verify testAnniversaryDate field has the expected value
        testResultsSteps.valueForFieldInPathShouldStartWith("[0].testTypes[0].testAnniversaryDate", expectedTestExpiryDate.substring(0,10));

        // Verify testExpiryDate field has the expected value
        testResultsSteps.valueForFieldInPathShouldStartWith("[0].testTypes[0].testExpiryDate", expectedTestExpiryDate.substring(0,10));
    }

    @WithTag("expiry_dates")
    @Title("CVSB-12215 - AS a VSA I want the first expiry date to be calculated using reg/ first use anniversary so that it is accurately recorded for annual tests - TRL - NO Previous Expiry Date - Today is 2 months before FirstUseDate Anniversary")
    @Test
    public void testResultsFirstTestExpiryTrlTodayIsMoreThanTwoMonthBeforeAnniversary() {

        // Read the base test result JSON.
        String testResultRecord = GenericData.readJsonValueFromFile("test-results_post_expiry_date_trl_12215.json", "$");

        DateTime currentTime = DateTime.now().withZone(DateTimeZone.UTC);
        DateTime submittedTestStartTimestamp = currentTime.minusMinutes(15);
        DateTime submittedTestTypeStartTimestamp = currentTime.minusMinutes(10);
        DateTime submittedTypeEndTimestamp = currentTime.minusMinutes(5);
        DateTime submittedEndTimestamp = currentTime;

        DateTime submittedFirstUseDate = currentTime.minusYears(1).plusMonths(3);
        //DateTime firstAnniversary = submittedFirstUseDate.plusYears(1).dayOfMonth().withMaximumValue().withTimeAtStartOfDay();
        DateTime firstAnniversary = submittedFirstUseDate.plusYears(1).dayOfMonth().withMaximumValue();

        // Create alteration to add one more tech record to in the request body
        String testStartTimestamp = submittedTestStartTimestamp.toInstant().toString();
        String testTypeStartTimestamp = submittedTestTypeStartTimestamp.toInstant().toString();
        String testTypeEndTimestamp = submittedTypeEndTimestamp.toInstant().toString();
        String testEndTimestamp = submittedEndTimestamp.toInstant().toString();
        String randomVin = GenericData.generateRandomVin();
        String randomSystemNo = GenericData.generateRandomSystemNumber();
        String randomTestResultId = UUID.randomUUID().toString();
        String firstUseDate = submittedFirstUseDate.toInstant().toString().substring(0,10);

        String expectedTestExpiryDate = submittedEndTimestamp.dayOfMonth().withMaximumValue().plusYears(1).toInstant().toString();

        JsonPathAlteration alterationFirstUseDate = new JsonPathAlteration("$.firstUseDate", firstUseDate, "", "REPLACE");
        JsonPathAlteration alterationTestStartTimestamp = new JsonPathAlteration("$.testStartTimestamp", testStartTimestamp, "", "REPLACE");
        JsonPathAlteration alterationTestEndTimestamp = new JsonPathAlteration("$.testEndTimestamp", testEndTimestamp, "", "REPLACE");
        JsonPathAlteration alterationTestTypeStartTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeStartTimestamp", testTypeStartTimestamp, "", "REPLACE");
        JsonPathAlteration alterationTestTypeEndTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeEndTimestamp", testTypeEndTimestamp, "", "REPLACE");
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationSysNo = new JsonPathAlteration("$.systemNumber", randomSystemNo, "", "REPLACE");
        JsonPathAlteration alterationTestResultId = new JsonPathAlteration("$.testResultId", randomTestResultId, "", "REPLACE");
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
                alterationSysNo,
                alterationTestResult
        ));

        // Printing the scenario to the console
        System.out.println("\n******************************************************");
        System.out.println("Test code: " + testCode);
        System.out.println("First Use Date: " + firstUseDate);
        System.out.println("First Use Anniversary: " + firstAnniversary.toInstant().toString().substring(0,10));
        System.out.println("Today: " + currentTime.toInstant().toString().substring(0,10));
        System.out.println("Expected expiryDate: " + expectedTestExpiryDate.substring(0,10));
        System.out.println("******************************************************\n");

        // Post the results, together with any alterations, and verify that they are accepted.
        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, alterations);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");

        // Retrieve the created record, and verify that the fields are present.
        testResultsSteps.getTestResultsSysNumber(randomSystemNo);
        testResultsSteps.statusCodeShouldBe(200);

        // Verify testCode field has the expected value
        testResultsSteps.valueForFieldInPathShouldBe("[0].testTypes[0].testCode", testCode);

        // Verify testTypeEndTimestamp is
        testResultsSteps.valueForFieldInPathShouldBe("[0].testTypes[0].testTypeEndTimestamp", testTypeEndTimestamp);

        // Verify testAnniversaryDate field has the expected value
        testResultsSteps.valueForFieldInPathShouldStartWith("[0].testTypes[0].testAnniversaryDate", expectedTestExpiryDate.substring(0,10));

        // Verify testExpiryDate field has the expected value
        testResultsSteps.valueForFieldInPathShouldStartWith("[0].testTypes[0].testExpiryDate", expectedTestExpiryDate.substring(0,10));

    }

    @WithTag("expiry_dates")
    @Title("(Defect CVSB-17637 for p4t4, p7t2) CVSB-12215 - AS a VSA I want the first expiry date to be calculated using reg/ first use anniversary so that it is accurately recorded for annual tests - TRL - NO Previous Expiry Date - Today is one month before FirstUseDate Anniversary")
    @Test
    public void testResultsFirstTestExpiryTrlTodayIsInMonthBeforeRegAnniversary() {

        // Read the base test result JSON.
        String testResultRecord = GenericData.readJsonValueFromFile("test-results_post_expiry_date_trl_12215.json", "$");

        DateTime currentTime = DateTime.now().withZone(DateTimeZone.UTC);
        DateTime submittedTestStartTimestamp = currentTime.minusMinutes(15);
        DateTime submittedTestTypeStartTimestamp = currentTime.minusMinutes(10);
        DateTime submittedTypeEndTimestamp = currentTime.minusMinutes(5);
        DateTime submittedEndTimestamp = currentTime;

        DateTime submittedFirstUseDate = currentTime.minusYears(1).plusMonths(1);
        DateTime firstAnniversary = submittedFirstUseDate.plusYears(1).dayOfMonth().withMaximumValue();

        // Create alteration to add one more tech record to in the request body
        String testStartTimestamp = submittedTestStartTimestamp.toInstant().toString();
        String testTypeStartTimestamp = submittedTestTypeStartTimestamp.toInstant().toString();
        String testTypeEndTimestamp = submittedTypeEndTimestamp.toInstant().toString();
        String testEndTimestamp = submittedEndTimestamp.toInstant().toString();
        String randomVin = GenericData.generateRandomVin();
        String randomSystemNo = GenericData.generateRandomSystemNumber();
        String randomTestResultId = UUID.randomUUID().toString();
        String firstUseDate = submittedFirstUseDate.toInstant().toString().substring(0,10);

        String expectedTestExpiryDate = firstAnniversary.dayOfMonth().withMaximumValue().plusYears(1).toInstant().toString();

        JsonPathAlteration alterationFirstUseDate = new JsonPathAlteration("$.firstUseDate", firstUseDate, "", "REPLACE");
        JsonPathAlteration alterationTestStartTimestamp = new JsonPathAlteration("$.testStartTimestamp", testStartTimestamp, "", "REPLACE");
        JsonPathAlteration alterationTestEndTimestamp = new JsonPathAlteration("$.testEndTimestamp", testEndTimestamp, "", "REPLACE");
        JsonPathAlteration alterationTestTypeStartTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeStartTimestamp", testTypeStartTimestamp, "", "REPLACE");
        JsonPathAlteration alterationTestTypeEndTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeEndTimestamp", testTypeEndTimestamp, "", "REPLACE");
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationSysNo = new JsonPathAlteration("$.systemNumber", randomSystemNo, "", "REPLACE");
        JsonPathAlteration alterationTestResultId = new JsonPathAlteration("$.testResultId", randomTestResultId, "", "REPLACE");
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
                alterationSysNo,
                alterationTestResult
        ));

        // Printing the scenario to the console
        System.out.println("\n******************************************************");
        System.out.println("Test code: " + testCode);
        System.out.println("First Use Date: " + firstUseDate);
        System.out.println("First Use Anniversary: " + firstAnniversary.toInstant().toString().substring(0,10));
        System.out.println("Today: " + currentTime.toInstant().toString().substring(0,10));
        System.out.println("Expected expiryDate: " + expectedTestExpiryDate.substring(0,10));
        System.out.println("******************************************************\n");

        // Post the results, together with any alterations, and verify that they are accepted.
        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, alterations);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");

        // Retrieve the created record, and verify that the fields are present.
        testResultsSteps.getTestResultsSysNumber(randomSystemNo);
        testResultsSteps.statusCodeShouldBe(200);

        // Verify testCode field has the expected value
        testResultsSteps.valueForFieldInPathShouldBe("[0].testTypes[0].testCode", testCode);

        // Verify testTypeEndTimestamp is
        testResultsSteps.valueForFieldInPathShouldBe("[0].testTypes[0].testTypeEndTimestamp", testTypeEndTimestamp);

        // Verify testAnniversaryDate field has the expected value
        testResultsSteps.valueForFieldInPathShouldStartWith("[0].testTypes[0].testAnniversaryDate", expectedTestExpiryDate.substring(0,10));

        // Verify testExpiryDate field has the expected value
        testResultsSteps.valueForFieldInPathShouldStartWith("[0].testTypes[0].testExpiryDate", expectedTestExpiryDate.substring(0,10));

    }

    @WithTag("expiry_dates")
    @Title("CVSB-12215 - AS a VSA I want the first expiry date to be calculated using reg/ first use anniversary so that it is accurately recorded for annual tests - TRL - NO Previous Expiry Date - Today is in the same month as FirstUseDate Anniversary")
    @Test
    public void testResultsFirstTestExpiryTrlTodayIsInTheSameMonthAsAnniversary() {

        // Read the base test result JSON.
        String testResultRecord = GenericData.readJsonValueFromFile("test-results_post_expiry_date_trl_12215.json", "$");

        DateTime currentTime = DateTime.now().withZone(DateTimeZone.UTC);
        DateTime submittedTestStartTimestamp = currentTime.minusMinutes(15);
        DateTime submittedTestTypeStartTimestamp = currentTime.minusMinutes(10);
        DateTime submittedTypeEndTimestamp = currentTime.minusMinutes(5);
        DateTime submittedEndTimestamp = currentTime;

        DateTime submittedFirstUseDate = currentTime.minusYears(1);
        //DateTime firstAnniversary = submittedFirstUseDate.plusYears(1).dayOfMonth().withMaximumValue().withTimeAtStartOfDay();
        DateTime firstAnniversary = submittedFirstUseDate.plusYears(1).dayOfMonth().withMaximumValue();

        // Create alteration to add one more tech record to in the request body
        String testStartTimestamp = submittedTestStartTimestamp.toInstant().toString();
        String testTypeStartTimestamp = submittedTestTypeStartTimestamp.toInstant().toString();
        String testTypeEndTimestamp = submittedTypeEndTimestamp.toInstant().toString();
        String testEndTimestamp = submittedEndTimestamp.toInstant().toString();
        String randomVin = GenericData.generateRandomVin();
        String randomSystemNo = GenericData.generateRandomSystemNumber();
        String randomTestResultId = UUID.randomUUID().toString();
        String firstUseDate = submittedFirstUseDate.toInstant().toString().substring(0,10);

        String expectedTestExpiryDate = firstAnniversary.dayOfMonth().withMaximumValue().plusYears(1).toInstant().toString();

        JsonPathAlteration alterationFirstUseDate = new JsonPathAlteration("$.firstUseDate", firstUseDate, "", "REPLACE");
        JsonPathAlteration alterationTestStartTimestamp = new JsonPathAlteration("$.testStartTimestamp", testStartTimestamp, "", "REPLACE");
        JsonPathAlteration alterationTestEndTimestamp = new JsonPathAlteration("$.testEndTimestamp", testEndTimestamp, "", "REPLACE");
        JsonPathAlteration alterationTestTypeStartTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeStartTimestamp", testTypeStartTimestamp, "", "REPLACE");
        JsonPathAlteration alterationTestTypeEndTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeEndTimestamp", testTypeEndTimestamp, "", "REPLACE");
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationSysNo = new JsonPathAlteration("$.systemNumber", randomSystemNo, "", "REPLACE");
        JsonPathAlteration alterationTestResultId = new JsonPathAlteration("$.testResultId", randomTestResultId, "", "REPLACE");
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
                alterationSysNo,
                alterationTestResult
        ));

        // Printing the scenario to the console
        System.out.println("\n******************************************************");
        System.out.println("Test code: " + testCode);
        System.out.println("First Use Date: " + firstUseDate);
        System.out.println("First Use Anniversary: " + firstAnniversary.toInstant().toString().substring(0,10));
        System.out.println("Today: " + currentTime.toInstant().toString().substring(0,10));
        System.out.println("Expected expiryDate: " + expectedTestExpiryDate.substring(0,10));
        System.out.println("******************************************************\n");

        // Post the results, together with any alterations, and verify that they are accepted.
        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, alterations);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");

        // Retrieve the created record, and verify that the fields are present.
        testResultsSteps.getTestResultsSysNumber(randomSystemNo);
        testResultsSteps.statusCodeShouldBe(200);

        // Verify testCode field has the expected value
        testResultsSteps.valueForFieldInPathShouldBe("[0].testTypes[0].testCode", testCode);

        // Verify testTypeEndTimestamp is
        testResultsSteps.valueForFieldInPathShouldBe("[0].testTypes[0].testTypeEndTimestamp", testTypeEndTimestamp);

        // Verify testAnniversaryDate field has the expected value
        testResultsSteps.valueForFieldInPathShouldStartWith("[0].testTypes[0].testAnniversaryDate", expectedTestExpiryDate.substring(0,10));

        // Verify testExpiryDate field has the expected value
        testResultsSteps.valueForFieldInPathShouldStartWith("[0].testTypes[0].testExpiryDate", expectedTestExpiryDate.substring(0,10));

    }

    @WithTag("expiry_dates")
    @Title("CVSB-12215 - AS a VSA I want the first expiry date to be calculated using reg/ first use anniversary so that it is accurately recorded for annual tests - TRL - NO Previous Expiry Date - Today is after the FirstUseDate Anniversary")
    @Test
    public void testResultsFirstTestExpiryTrlTodayIsAfterTheAnniversary() {

        // Read the base test result JSON.
        String testResultRecord = GenericData.readJsonValueFromFile("test-results_post_expiry_date_trl_12215.json", "$");

        DateTime currentTime = DateTime.now().withZone(DateTimeZone.UTC);
        DateTime submittedTestStartTimestamp = currentTime.minusMinutes(15);
        DateTime submittedTestTypeStartTimestamp = currentTime.minusMinutes(10);
        DateTime submittedTypeEndTimestamp = currentTime.minusMinutes(5);
        DateTime submittedEndTimestamp = currentTime;

        DateTime submittedFirstUseDate = currentTime.minusYears(1).minusMonths(1);
        //DateTime firstAnniversary = submittedFirstUseDate.plusYears(1).dayOfMonth().withMaximumValue().withTimeAtStartOfDay();
        DateTime firstAnniversary = submittedFirstUseDate.plusYears(1).dayOfMonth().withMaximumValue();

        // Create alteration to add one more tech record to in the request body
        String testStartTimestamp = submittedTestStartTimestamp.toInstant().toString();
        String testTypeStartTimestamp = submittedTestTypeStartTimestamp.toInstant().toString();
        String testTypeEndTimestamp = submittedTypeEndTimestamp.toInstant().toString();
        String testEndTimestamp = submittedEndTimestamp.toInstant().toString();
        String randomVin = GenericData.generateRandomVin();
        String randomSystemNo = GenericData.generateRandomSystemNumber();
        String randomTestResultId = UUID.randomUUID().toString();
        String firstUseDate = submittedFirstUseDate.toInstant().toString().substring(0,10);

        String expectedTestExpiryDate = submittedEndTimestamp.dayOfMonth().withMaximumValue().plusYears(1).toInstant().toString();

        JsonPathAlteration alterationFirstUseDate = new JsonPathAlteration("$.firstUseDate", firstUseDate, "", "REPLACE");
        JsonPathAlteration alterationTestStartTimestamp = new JsonPathAlteration("$.testStartTimestamp", testStartTimestamp, "", "REPLACE");
        JsonPathAlteration alterationTestEndTimestamp = new JsonPathAlteration("$.testEndTimestamp", testEndTimestamp, "", "REPLACE");
        JsonPathAlteration alterationTestTypeStartTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeStartTimestamp", testTypeStartTimestamp, "", "REPLACE");
        JsonPathAlteration alterationTestTypeEndTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeEndTimestamp", testTypeEndTimestamp, "", "REPLACE");
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationSysNo = new JsonPathAlteration("$.systemNumber", randomSystemNo, "", "REPLACE");
        JsonPathAlteration alterationTestResultId = new JsonPathAlteration("$.testResultId", randomTestResultId, "", "REPLACE");
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
                alterationSysNo,
                alterationTestResult
        ));

        // Printing the scenario to the console
        System.out.println("\n******************************************************");
        System.out.println("Test code: " + testCode);
        System.out.println("First Use Date: " + firstUseDate);
        System.out.println("First Use Anniversary: " + firstAnniversary.toInstant().toString().substring(0,10));
        System.out.println("Today: " + currentTime.toInstant().toString().substring(0,10));
        System.out.println("Expected expiryDate: " + expectedTestExpiryDate.substring(0,10));
        System.out.println("******************************************************\n");

        // Post the results, together with any alterations, and verify that they are accepted.
        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, alterations);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");

        // Retrieve the created record, and verify that the fields are present.
        testResultsSteps.getTestResultsSysNumber(randomSystemNo);
        testResultsSteps.statusCodeShouldBe(200);

        // Verify testCode field has the expected value
        testResultsSteps.valueForFieldInPathShouldBe("[0].testTypes[0].testCode", testCode);

        // Verify testTypeEndTimestamp is
        testResultsSteps.valueForFieldInPathShouldStartWith("[0].testTypes[0].testTypeEndTimestamp", testTypeEndTimestamp.substring(0,10));

        // Verify testAnniversaryDate field has the expected value
        testResultsSteps.valueForFieldInPathShouldStartWith("[0].testTypes[0].testAnniversaryDate", expectedTestExpiryDate.substring(0,10));

        // Verify testExpiryDate field has the expected value
        testResultsSteps.valueForFieldInPathShouldStartWith("[0].testTypes[0].testExpiryDate", expectedTestExpiryDate.substring(0,10));

    }
}
