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
@UseTestDataFrom(value="loader/testdata/test_data_expiry_date_trl_first_test.csv")
public class PostTestResultsExpiryDateLogicTrlFirstTest {

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
    @Title("CVSB-8684 - TC1 - AC1 - TRL First Test - NO Previous Expiry Date - firstUseDate = null")
    @Test
    public void testResultsFirstTestExpiryTrlFirstDateNull() {

        // Read the base test result JSON.
        String testResultRecord = GenericData.readJsonValueFromFile("test-results_post_expiry_date_trl_8684.json", "$");

        DateTime submittedTestStartTimestamp = DateTime.now().withZone(DateTimeZone.UTC);
        DateTime submittedTestTypeStartTimestamp = submittedTestStartTimestamp.plusMinutes(5);
        DateTime submittedTypeEndTimestamp = submittedTestTypeStartTimestamp.plusMinutes(10);
        DateTime submittedEndTimestamp = submittedTypeEndTimestamp.plusMinutes(5);
        DateTime submittedFirstUseDate = submittedTypeEndTimestamp.dayOfMonth().withMaximumValue().toDateTime().minusYears(1).plusMonths(2);

        // Create alteration to add one more tech record to in the request body
        String firstUseDate = null;
        String testStartTimestamp = submittedTestStartTimestamp.toInstant().toString();
        String testTypeStartTimestamp = submittedTestTypeStartTimestamp.toInstant().toString();
        String testTypeEndTimestamp = submittedTypeEndTimestamp.toInstant().toString();
        String testEndTimestamp = submittedEndTimestamp.toInstant().toString();
        String testExpectedDate = submittedTypeEndTimestamp.dayOfMonth().withMaximumValue().plusYears(1).withTimeAtStartOfDay().toInstant().toString();
        String randomVin = GenericData.generateRandomVin();
        String randomSystemNo = GenericData.generateRandomSystemNumber();
        String randomTestResultId = UUID.randomUUID().toString();

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
        testResultsSteps.valueForFieldInPathShouldBe("[0].testTypes[0].testAnniversaryDate", testExpectedDate);

        // Verify testExpiryDate field has the expected value
        testResultsSteps.valueForFieldInPathShouldBe("[0].testTypes[0].testExpiryDate", testExpectedDate);

    }

    @WithTag("expiry_dates")
    @Title("CVSB-8684 - TC1 - AC1 - TRL First Test - NO Previous Expiry Date - firstUseDate is missing")
    @Test
    public void testResultsFirstTestExpiryTrlFirstDateMissing() {

        // Read the base test result JSON.
        String testResultRecord = GenericData.readJsonValueFromFile("test-results_post_expiry_date_trl_8684.json", "$");

        DateTime submittedTestStartTimestamp = DateTime.now().withZone(DateTimeZone.UTC);
        DateTime submittedTestTypeStartTimestamp = submittedTestStartTimestamp.plusMinutes(5);
        DateTime submittedTypeEndTimestamp = submittedTestTypeStartTimestamp.plusMinutes(10);
        DateTime submittedEndTimestamp = submittedTypeEndTimestamp.plusMinutes(5);

        // Create alteration to add one more tech record to in the request body
        String testStartTimestamp = submittedTestStartTimestamp.toInstant().toString();
        String testTypeStartTimestamp = submittedTestTypeStartTimestamp.toInstant().toString();
        String testTypeEndTimestamp = submittedTypeEndTimestamp.toInstant().toString();
        String testEndTimestamp = submittedEndTimestamp.toInstant().toString();
        String testExpectedDate = submittedTypeEndTimestamp.dayOfMonth().withMaximumValue().plusYears(1).withTimeAtStartOfDay().toInstant().toString();
        String randomVin = GenericData.generateRandomVin();
        String randomSystemNo = GenericData.generateRandomSystemNumber();
        String randomTestResultId = UUID.randomUUID().toString();


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
        testResultsSteps.valueForFieldInPathShouldBe("[0].testTypes[0].testAnniversaryDate", testExpectedDate);

        // Verify testExpiryDate field has the expected value
        testResultsSteps.valueForFieldInPathShouldBe("[0].testTypes[0].testExpiryDate", testExpectedDate);

    }

    @WithTag("expiry_dates")
    @Title("CVSB-8684 - TC1 - AC1 - TRL First Test - NO Previous Expiry Date - firstUseDate = Today(-1 year, +1 month)")
    @Test
    public void testResultFirstTestExpiryTrlFirstUseDateLessThanOneYear() {

        // Read the base test result JSON.
        String testResultRecord = GenericData.readJsonValueFromFile("test-results_post_expiry_date_trl_8684.json", "$");

        DateTime currentTime = DateTime.now().withZone(DateTimeZone.UTC);
        DateTime submittedTestStartTimestamp = currentTime.minusMinutes(15);
        DateTime submittedTestTypeStartTimestamp = currentTime.minusMinutes(10);
        DateTime submittedTypeEndTimestamp = currentTime.minusMinutes(5);
        DateTime submittedEndTimestamp = currentTime;

        DateTime submittedFirstUseDate = currentTime.toDateTime().minusYears(1).plusMonths(1);

        // Create alteration to add one more tech record to in the request body
        String testStartTimestamp = submittedTestStartTimestamp.toInstant().toString();
        String testFirstUseDateTimestamp = submittedFirstUseDate.toDateTime().toString("yyyy-MM-dd");
        String testTypeStartTimestamp = submittedTestTypeStartTimestamp.toInstant().toString();
        String testTypeEndTimestamp = submittedTypeEndTimestamp.toInstant().toString();
        String testEndTimestamp = submittedEndTimestamp.toInstant().toString();
        String randomVin = GenericData.generateRandomVin();
        String randomSystemNo = GenericData.generateRandomSystemNumber();
        String randomTestResultId = UUID.randomUUID().toString();

        String testExpectedExpiryDate = submittedFirstUseDate.dayOfMonth().withMaximumValue().plusYears(2).toInstant().toString();

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

        JsonPathAlteration alterationFirstUseDate = new JsonPathAlteration("$.firstUseDate", testFirstUseDateTimestamp, "", "REPLACE");
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
        testResultsSteps.valueForFieldInPathShouldStartWith("[0].testTypes[0].testAnniversaryDate", testExpectedExpiryDate.substring(0,10));

        // Verify testExpiryDate field has the expected value
        testResultsSteps.valueForFieldInPathShouldStartWith("[0].testTypes[0].testExpiryDate", testExpectedExpiryDate.substring(0,10));

    }

    @WithTag("expiry_dates")
    @Title("CVSB-8684 - TC1 - AC1 - TRL First Test - NO Previous Expiry Date - firstUseDate = Today(-1 year, -1 month)")
    @Test
    public void testResultsFirstTestExpiryTrlFirstUseDateMoreThanOneYear() {

        // Read the base test result JSON.
        String testResultRecord = GenericData.readJsonValueFromFile("test-results_post_expiry_date_trl_8684.json", "$");

        DateTime currentTime = DateTime.now().withZone(DateTimeZone.UTC);
        DateTime submittedTestStartTimestamp = currentTime.minusMinutes(15);
        DateTime submittedTestTypeStartTimestamp = currentTime.minusMinutes(10);
        DateTime submittedTypeEndTimestamp = currentTime.minusMinutes(5);
        DateTime submittedEndTimestamp = currentTime;

        DateTime submittedFirstUseDate = currentTime.toDateTime().minusYears(1).minusMonths(1);

        // Create alteration to add one more tech record to in the request body
        String testStartTimestamp = submittedTestStartTimestamp.toInstant().toString();
        String testFirstUseDateTimestamp = submittedFirstUseDate.toDateTime().toString("yyyy-MM-dd");
        String testTypeStartTimestamp = submittedTestTypeStartTimestamp.toInstant().toString();
        String testTypeEndTimestamp = submittedTypeEndTimestamp.toInstant().toString();
        String testEndTimestamp = submittedEndTimestamp.toInstant().toString();
        String randomVin = GenericData.generateRandomVin();
        String randomSystemNo = GenericData.generateRandomSystemNumber();
        String randomTestResultId = UUID.randomUUID().toString();

        String testExpectedExpiryDate = submittedEndTimestamp.dayOfMonth().withMaximumValue().plusYears(1).toInstant().toString();

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

        JsonPathAlteration alterationFirstUseDate = new JsonPathAlteration("$.firstUseDate", testFirstUseDateTimestamp, "", "REPLACE");
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
        testResultsSteps.valueForFieldInPathShouldStartWith("[0].testTypes[0].testAnniversaryDate", testExpectedExpiryDate.substring(0,10));

        // Verify testExpiryDate field has the expected value
        testResultsSteps.valueForFieldInPathShouldStartWith("[0].testTypes[0].testExpiryDate", testExpectedExpiryDate.substring(0,10));

    }

//    @Ignore
//    @WithTag("expiry_dates")
//    @Title("CVSB-8684 - Include expiry date calculation validation in BE test suite - TRL - testTypeEndTimestamp (date only) is less than, but not including, 2 months before (last day in the month of firstUseDate + 1 year)")
//    @Test
//    public void testResultsNoFirstTestExpiryTrlLessThanTwoMonthsOrMoreBefore() {
//
//        // Read the base test result JSON.
//        String testResultRecord = GenericData.readJsonValueFromFile("test-results_expiry_date_trl_8684.json", "$");
//
//        DateTime submittedTestStartTimestamp = DateTime.now().withZone(DateTimeZone.UTC);
//        DateTime submittedTestTypeStartTimestamp = submittedTestStartTimestamp.plusMinutes(5);
//        DateTime submittedTypeEndTimestamp = submittedTestTypeStartTimestamp.plusMinutes(10);
//        DateTime submittedEndTimestamp = submittedTypeEndTimestamp.plusMinutes(5);
//
//        // Create alteration to add one more tech record to in the request body
//        String firstUseDate = submittedTypeEndTimestamp.toDateTime().minusYears(1).plusMonths(2).plusDays(1).toString("yyyy-MM-dd");
//        String testStartTimestamp = submittedTestStartTimestamp.toInstant().toString();
//        String testTypeStartTimestamp = submittedTestTypeStartTimestamp.toInstant().toString();
//        String testTypeEndTimestamp = submittedTypeEndTimestamp.toInstant().toString();
//        String testEndTimestamp = submittedEndTimestamp.toInstant().toString();
//        String testExpectedDate = submittedTestStartTimestamp.dayOfMonth().withMaximumValue().plusYears(1).withTimeAtStartOfDay().toInstant().toString();
//        String randomVin = GenericData.generateRandomVin();
//        String randomTestResultId = UUID.randomUUID().toString();
//
//
////        String returned_testTypeEndTimestamp = "";
////        String returned_testExpiryDate = "";
////        String returned_createdAt = "";
////        String returned_testTypeStartTimestamp = "";
//
////        System.out.println("submittedFirstUseDate: " + firstUseDate);
////        System.out.println("submittedTestStartTimestamp: " + testStartTimestamp);
////        System.out.println("submittedTestTypeStartTimestamp: " + testTypeStartTimestamp);
////        System.out.println("submittedTestTypeEndTimestamp: " + testTypeEndTimestamp);
////        System.out.println("submittedTestEndTimestamp: " + testEndTimestamp);
////        System.out.println("returned_testAnniversaryDate: " + testExpectedDate);
//
//        JsonPathAlteration alterationFirstUseDate = new JsonPathAlteration("$.firstUseDate", firstUseDate, "", "REPLACE");
//        JsonPathAlteration alterationTestStartTimestamp = new JsonPathAlteration("$.testStartTimestamp", testStartTimestamp, "", "REPLACE");
//        JsonPathAlteration alterationTestEndTimestamp = new JsonPathAlteration("$.testEndTimestamp", testEndTimestamp, "", "REPLACE");
//        JsonPathAlteration alterationTestTypeStartTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeStartTimestamp", testTypeStartTimestamp, "", "REPLACE");
//        JsonPathAlteration alterationTestTypeEndTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeEndTimestamp", testTypeEndTimestamp, "", "REPLACE");
//        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
//        JsonPathAlteration alterationTestResultId = new JsonPathAlteration("$.testResultId", randomTestResultId, "", "REPLACE");
//
//        JsonPathAlteration alterationTestName = new JsonPathAlteration("$.testTypes[0].name", name,"","REPLACE");
//        JsonPathAlteration alterationTestTypeId = new JsonPathAlteration("$.testTypes[0].testTypeId", testTypeId,"","REPLACE");
//        JsonPathAlteration alterationTestTypeName = new JsonPathAlteration("$.testTypes[0].testTypeName", testTypeName,"","REPLACE");
//        JsonPathAlteration alterationTestResult = new JsonPathAlteration("$.testTypes[0].testResult", testResult,"","REPLACE");
//        JsonPathAlteration alterationNoOfAxles = new JsonPathAlteration("$.noOfAxles", noOfAxles,"","REPLACE");
//        JsonPathAlteration alterationTestExpiryDate = new JsonPathAlteration("$.testTypes[0].testExpiryDate", "", "", "DELETE");
//
//        // Collate the list of alterations.
//        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(
//                alterationFirstUseDate,
//                alterationTestStartTimestamp,
//                alterationTestEndTimestamp,
//                alterationTestTypeStartTimestamp,
//                alterationTestTypeEndTimestamp,
//                alterationVin,
//                alterationTestResultId,
//                alterationTestName,
//                alterationTestTypeId,
//                alterationTestTypeName,
//                alterationNoOfAxles,
//                alterationTestExpiryDate,
//                alterationTestResult
//        ));
//
//        // Post the results, together with any alterations, and verify that they are accepted.
//        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, alterations);
//        testResultsSteps.statusCodeShouldBe(201);
//        testResultsSteps.validateData("Test records created");
//
//        // Retrieve the created record, and verify that the fields are present.
//        testResultsSteps.getTestResults(randomVin);
//        testResultsSteps.statusCodeShouldBe(200);
//
//        // Verify testCode field has the expected value
//        testResultsSteps.valueForFieldInPathShouldBe("[0].testTypes[0].testCode", testCode);
//
//        // Verify firstUseDate is
//        testResultsSteps.valueForFieldInPathShouldBe("[0].firstUseDate", firstUseDate);
//
//        // Verify testTypeEndTimestamp is
//        testResultsSteps.valueForFieldInPathShouldBe("[0].testTypes[0].testTypeEndTimestamp", testTypeEndTimestamp);
//
//        // Verify testAnniversaryDate field has the expected value
//        testResultsSteps.valueForFieldInPathShouldBe("[0].testTypes[0].testAnniversaryDate", testExpectedDate);
//
//        // Verify testExpiryDate field has the expected value
//        testResultsSteps.valueForFieldInPathShouldStartWith("[0].testTypes[0].testExpiryDate", testExpectedDate.substring(0,10));
//
//    }
//
//
//    @Ignore
//    @WithTag("expiry_dates")
//    @Title("CVSB-8684 - Include expiry date calculation validation in BE test suite - TRL - testTypeEndTimestamp (date only) is less than, but not including, 2 months before (last day in the month of firstUseDate + 1 year) AND no existing testExpiryDate")
//    @Test
//    public void testResultsNoFirstTestExpiryTrlLessThanTwoMonthsOrLessBefore() {
//
//        // Read the base test result JSON.
//        String testResultRecord = GenericData.readJsonValueFromFile("test-results_expiry_date_trl_8684.json", "$");
//
//        DateTime submittedTestStartTimestamp = DateTime.now().withZone(DateTimeZone.UTC);
//        DateTime submittedTestTypeStartTimestamp = submittedTestStartTimestamp.plusMinutes(5);
//        DateTime submittedTypeEndTimestamp = submittedTestTypeStartTimestamp.plusMinutes(10);
//        DateTime submittedEndTimestamp = submittedTypeEndTimestamp.plusMinutes(5);
//        DateTime submittedFirstUseDate = submittedTypeEndTimestamp.dayOfMonth().withMaximumValue().toDateTime().minusYears(1).plusMonths(2);
//
//        // Create alteration to add one more tech record to in the request body
//        String firstUseDate = submittedFirstUseDate.toString("yyyy-MM-dd");
//        String testStartTimestamp = submittedTestStartTimestamp.toInstant().toString();
//        String testTypeStartTimestamp = submittedTestTypeStartTimestamp.toInstant().toString();
//        String testTypeEndTimestamp = submittedTypeEndTimestamp.toInstant().toString();
//        String testEndTimestamp = submittedEndTimestamp.toInstant().toString();
//        String testExpectedDate = submittedTestStartTimestamp.dayOfMonth().withMaximumValue().plusYears(1).withTimeAtStartOfDay().toInstant().toString();
//        String randomVin = GenericData.generateRandomVin();
//        String randomTestResultId = UUID.randomUUID().toString();
//
//
////        String returned_testTypeEndTimestamp = "";
////        String returned_testExpiryDate = "";
////        String returned_createdAt = "";
////        String returned_testTypeStartTimestamp = "";
//
////        System.out.println("submittedFirstUseDate: " + firstUseDate);
////        System.out.println("submittedTestStartTimestamp: " + testStartTimestamp);
////        System.out.println("submittedTestTypeStartTimestamp: " + testTypeStartTimestamp);
////        System.out.println("submittedTestTypeEndTimestamp: " + testTypeEndTimestamp);
////        System.out.println("submittedTestEndTimestamp: " + testEndTimestamp);
////        System.out.println("returned_testAnniversaryDate: " + testExpectedDate);
//
//        JsonPathAlteration alterationFirstUseDate = new JsonPathAlteration("$.firstUseDate", firstUseDate, "", "REPLACE");
//        JsonPathAlteration alterationTestStartTimestamp = new JsonPathAlteration("$.testStartTimestamp", testStartTimestamp, "", "REPLACE");
//        JsonPathAlteration alterationTestEndTimestamp = new JsonPathAlteration("$.testEndTimestamp", testEndTimestamp, "", "REPLACE");
//        JsonPathAlteration alterationTestTypeStartTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeStartTimestamp", testTypeStartTimestamp, "", "REPLACE");
//        JsonPathAlteration alterationTestTypeEndTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeEndTimestamp", testTypeEndTimestamp, "", "REPLACE");
//        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
//        JsonPathAlteration alterationTestResultId = new JsonPathAlteration("$.testResultId", randomTestResultId, "", "REPLACE");
//
//        JsonPathAlteration alterationTestName = new JsonPathAlteration("$.testTypes[0].name", name,"","REPLACE");
//        JsonPathAlteration alterationTestTypeId = new JsonPathAlteration("$.testTypes[0].testTypeId", testTypeId,"","REPLACE");
//        JsonPathAlteration alterationTestTypeName = new JsonPathAlteration("$.testTypes[0].testTypeName", testTypeName,"","REPLACE");
//        JsonPathAlteration alterationTestResult = new JsonPathAlteration("$.testTypes[0].testResult", testResult,"","REPLACE");
//        JsonPathAlteration alterationNoOfAxles = new JsonPathAlteration("$.noOfAxles", noOfAxles,"","REPLACE");
//        JsonPathAlteration alterationTestExpiryDate = new JsonPathAlteration("$.testTypes[0].testExpiryDate", "", "", "DELETE");
//
//        // Collate the list of alterations.
//        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(
//                alterationFirstUseDate,
//                alterationTestStartTimestamp,
//                alterationTestEndTimestamp,
//                alterationTestTypeStartTimestamp,
//                alterationTestTypeEndTimestamp,
//                alterationVin,
//                alterationTestResultId,
//                alterationTestName,
//                alterationTestTypeId,
//                alterationTestTypeName,
//                alterationNoOfAxles,
//                alterationTestExpiryDate,
//                alterationTestResult
//        ));
//
//        // Post the results, together with any alterations, and verify that they are accepted.
//        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, alterations);
//        testResultsSteps.statusCodeShouldBe(201);
//        testResultsSteps.validateData("Test records created");
//
//        // Retrieve the created record, and verify that the fields are present.
//        testResultsSteps.getTestResults(randomVin);
//        testResultsSteps.statusCodeShouldBe(200);
//
//        // Verify testCode field has the expected value
//        testResultsSteps.valueForFieldInPathShouldBe("[0].testTypes[0].testCode", testCode);
//
//        // Verify firstUseDate is
//        testResultsSteps.valueForFieldInPathShouldBe("[0].firstUseDate", firstUseDate);
//
//        // Verify testTypeEndTimestamp is
//        testResultsSteps.valueForFieldInPathShouldBe("[0].testTypes[0].testTypeEndTimestamp", testTypeEndTimestamp);
//
//        // Verify testAnniversaryDate field has the expected value
//        testResultsSteps.valueForFieldInPathShouldBe("[0].testTypes[0].testAnniversaryDate", testExpectedDate);
//
//        // Verify testExpiryDate field has the expected value
//        testResultsSteps.valueForFieldInPathShouldStartWith("[0].testTypes[0].testExpiryDate", testExpectedDate.substring(0,10));
//
//    }

}
