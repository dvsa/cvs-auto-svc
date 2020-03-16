package testresults.expiry_date;

import data.GenericData;
import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.junit.annotations.UseTestDataFrom;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import steps.TestResultsSteps;
import util.JsonPathAlteration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@WithTag("expiry_dates")
@RunWith(SerenityParameterizedRunner.class)
@UseTestDataFrom(value="loader/testdata/test_data_expiry_date_psv_11509.csv")
public class PostTestResultsFirstExpiryDatesPsv_11509 {

    @Steps
    TestResultsSteps testResultsSteps;

    private String name;
    private String testTypeName;
    private String testTypeId;
    private String vehicleSize;
    private String vehicleConfiguration;
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

    public void setVehicleSize(String vehicleSize) {
        this.vehicleSize = vehicleSize;
    }

    public void setVehicleConfiguration(String vehicleConfiguration) {
        this.vehicleConfiguration = vehicleConfiguration;
    }

    public void setTestResult(String testResult) {
        this.testResult = testResult;
    }

    public void setTestCode(String testCode) {
        this.testCode = testCode;
    }


    @WithTag("expiry_dates")
    @Title("CVSB-11509 - As a VSA/SVSA I want first expiry dates (PSV) and COIF + annual expiry dates to be calculated so that the certificates/records get accurately populated - expiryDate = Today + 1 year, - 1 day ")
    @Test
    public void testResultsFirstTestExpiryNoPreviousExpiryNoRegDate() {

        // Read the base test result JSON.
        String testResultRecord = GenericData.readJsonValueFromFile("test-results_post_expiry_date_psv_11509.json", "$");

        // Create random vin, systemNumber and testResultId
        String randomVin = GenericData.generateRandomVin();
        String randomSystemNo = GenericData.generateRandomSystemNumber();
        String randomTestResultId = UUID.randomUUID().toString();

        // Create timestamps based on current time
        DateTime currentTime = DateTime.now().withZone(DateTimeZone.UTC);
        DateTime submittedTestStartTimestamp = currentTime.minusMinutes(15);
        DateTime submittedTestTypeStartTimestamp = currentTime.minus(10);
        DateTime submittedTestTypeEndTimestamp = currentTime.minusMinutes(5);
        DateTime submittedTestEndTimestamp = currentTime;

        // Create alteration to add one more tech record to in the request body
        String testStartTimestamp = submittedTestStartTimestamp.toInstant().toString();
        String testTypeStartTimestamp = submittedTestTypeStartTimestamp.toInstant().toString();
        String testTypeEndTimestamp = submittedTestTypeEndTimestamp.toInstant().toString();
        String testEndTimestamp = submittedTestEndTimestamp.toInstant().toString();

        // Create expected testExpiryDate based on submitted test end timestamp
        String expectedTestExpiryDate = currentTime.plusYears(1).minusDays(1).toInstant().toString();

        JsonPathAlteration alterationTestStartTimestamp = new JsonPathAlteration("$.testStartTimestamp", testStartTimestamp, "", "REPLACE");
        JsonPathAlteration alterationTestEndTimestamp = new JsonPathAlteration("$.testEndTimestamp", testEndTimestamp, "", "REPLACE");
        JsonPathAlteration alterationTestTypeStartTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeStartTimestamp", testTypeStartTimestamp, "", "REPLACE");
        JsonPathAlteration alterationTestTypeEndTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeEndTimestamp", testTypeEndTimestamp, "", "REPLACE");
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationSysNo = new JsonPathAlteration("$.systemNumber", randomSystemNo, "", "REPLACE");
        JsonPathAlteration alterationTestResultId = new JsonPathAlteration("$.testResultId", randomTestResultId, "", "REPLACE");
        JsonPathAlteration alterationTestExpiryDate = new JsonPathAlteration("$.testTypes[0].testExpiryDate", "", "", "DELETE");
        JsonPathAlteration alterationTestName = new JsonPathAlteration("$.testTypes[0].name", name, "", "REPLACE");
        JsonPathAlteration alterationTestTypeId = new JsonPathAlteration("$.testTypes[0].testTypeId", testTypeId, "", "REPLACE");
        JsonPathAlteration alterationTestTypeName = new JsonPathAlteration("$.testTypes[0].testTypeName", testTypeName, "", "REPLACE");
        JsonPathAlteration alterationTestResult = new JsonPathAlteration("$.testTypes[0].testResult", testResult, "", "REPLACE");
        JsonPathAlteration alterationVehicleSize = new JsonPathAlteration("$.vehicleSize", vehicleSize, "", "REPLACE");
        JsonPathAlteration alterationVehicleConfiguration = new JsonPathAlteration("$.vehicleConfiguration", vehicleConfiguration, "", "REPLACE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(
                alterationTestStartTimestamp,
                alterationTestEndTimestamp,
                alterationTestTypeStartTimestamp,
                alterationTestTypeEndTimestamp,
                alterationVin,
                alterationTestResultId,
                alterationTestName,
                alterationTestTypeId,
                alterationTestTypeName,
                alterationVehicleSize,
                alterationVehicleConfiguration,
                alterationTestExpiryDate,
                alterationSysNo,
                alterationTestResult
        ));

        // Printing the scenario to the console
        System.out.println("\nTest code: " + testCode);
        System.out.println("Registration Date: " + "missing");
        System.out.println("Registration Anniversary: " + "missing");
        System.out.println("Today: " + currentTime.toInstant().toString().substring(0,10));
        System.out.println(("Expected expiryDate: " + expectedTestExpiryDate.substring(0,10) + "\n"));

        // Post the results, together with any alterations, and verify that they are accepted.
        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, alterations);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");

        // Retrieve the created record, and verify that the fields are present.
        testResultsSteps.getTestResults(randomSystemNo);
        testResultsSteps.statusCodeShouldBe(200);

        // Verify testCode field has the expected value
        testResultsSteps.valueForFieldInPathShouldBe("[0].testTypes[0].testCode", testCode);

        // Verify testExpiryDate field has the expected value
        testResultsSteps.valueForFieldInPathShouldStartWith("[0].testTypes[0].testExpiryDate", expectedTestExpiryDate.substring(0, 10));

    }

    @WithTag("expiry_dates")
    @Title("CVSB-11509 - As a VSA/SVSA I want first expiry dates (PSV) and COIF + annual expiry dates to be calculated so that the certificates/records get accurately populated (vehicle has come in early for test) - expiryDate = Today + 1 year, - 1 day ")
    @Test
    public void testResultsFirstTestExpiryNoPreviousExpiryMoreThanTwoMonthBeforeAnniversary() {

        // Read the base test result JSON.
        String testResultRecord = GenericData.readJsonValueFromFile("test-results_post_expiry_date_psv_11509.json", "$");

        // Create random vin, systemNumber and testResultId
        String randomVin = GenericData.generateRandomVin();
        String randomSystemNo = GenericData.generateRandomSystemNumber();
        String randomTestResultId = UUID.randomUUID().toString();

        // Create timestamps based on current time
        DateTime currentTime = DateTime.now().withZone(DateTimeZone.UTC);
        DateTime submittedTestStartTimestamp = currentTime.minusMinutes(15);
        DateTime submittedTestTypeStartTimestamp = currentTime.minus(10);
        DateTime submittedTestTypeEndTimestamp = currentTime.minusMinutes(5);
        DateTime submittedTestEndTimestamp = currentTime;

        // Calculate registration date based on regDateAnniversary
        DateTime regDateAnniversary = currentTime.plusMonths(2).plusDays(1);
        DateTime regDateTimestamp = regDateAnniversary.minusYears(1);

        // Create alteration to add one more tech record to in the request body
        String testStartTimestamp = submittedTestStartTimestamp.toInstant().toString();
        String testTypeStartTimestamp = submittedTestTypeStartTimestamp.toInstant().toString();
        String testTypeEndTimestamp = submittedTestTypeEndTimestamp.toInstant().toString();
        String testEndTimestamp = submittedTestEndTimestamp.toInstant().toString();
        String regnDate = regDateTimestamp.toInstant().toString().substring(0, 10);

        // Create expected testExpiryDate based on submitted test end timestamp
        String expectedTestExpiryDate = submittedTestEndTimestamp.plusYears(1).minusDays(1).toInstant().toString();

        JsonPathAlteration alterationTestStartTimestamp = new JsonPathAlteration("$.testStartTimestamp", testStartTimestamp, "", "REPLACE");
        JsonPathAlteration alterationTestEndTimestamp = new JsonPathAlteration("$.testEndTimestamp", testEndTimestamp, "", "REPLACE");
        JsonPathAlteration alterationTestTypeStartTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeStartTimestamp", testTypeStartTimestamp, "", "REPLACE");
        JsonPathAlteration alterationTestTypeEndTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeEndTimestamp", testTypeEndTimestamp, "", "REPLACE");
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationSysNo = new JsonPathAlteration("$.systemNumber", randomSystemNo, "", "REPLACE");
        JsonPathAlteration alterationTestResultId = new JsonPathAlteration("$.testResultId", randomTestResultId, "", "REPLACE");
        JsonPathAlteration alterationTestExpiryDate = new JsonPathAlteration("$.testTypes[0].testExpiryDate", "", "", "DELETE");
        JsonPathAlteration alterationTestName = new JsonPathAlteration("$.testTypes[0].name", name, "", "REPLACE");
        JsonPathAlteration alterationTestTypeId = new JsonPathAlteration("$.testTypes[0].testTypeId", testTypeId, "", "REPLACE");
        JsonPathAlteration alterationTestTypeName = new JsonPathAlteration("$.testTypes[0].testTypeName", testTypeName, "", "REPLACE");
        JsonPathAlteration alterationTestResult = new JsonPathAlteration("$.testTypes[0].testResult", testResult, "", "REPLACE");
        JsonPathAlteration alterationVehicleSize = new JsonPathAlteration("$.vehicleSize", vehicleSize, "", "REPLACE");
        JsonPathAlteration alterationVehicleConfiguration = new JsonPathAlteration("$.vehicleConfiguration", vehicleConfiguration, "", "REPLACE");
        JsonPathAlteration alterationRegnDate = new JsonPathAlteration("$", regnDate , "regnDate", "ADD_FIELD");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(
                alterationTestStartTimestamp,
                alterationTestEndTimestamp,
                alterationTestTypeStartTimestamp,
                alterationTestTypeEndTimestamp,
                alterationVin,
                alterationTestResultId,
                alterationTestName,
                alterationTestTypeId,
                alterationTestTypeName,
                alterationVehicleSize,
                alterationVehicleConfiguration,
                alterationTestExpiryDate,
                alterationSysNo,
                alterationTestResult,
                alterationRegnDate
        ));

        // Printing the scenario to the console
        System.out.println("\nTest code: " + testCode);
        System.out.println("Registration Date: " + regnDate);
        System.out.println("Registration Anniversary: " + regDateAnniversary.toInstant().toString().substring(0,10));
        System.out.println("Today: " + currentTime.toInstant().toString().substring(0,10));
        System.out.println(("Expected expiryDate: " + expectedTestExpiryDate.substring(0,10) + "\n"));

        // Post the results, together with any alterations, and verify that they are accepted.
        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, alterations);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");

        // Retrieve the created record, and verify that the fields are present.
        testResultsSteps.getTestResults(randomSystemNo);
        testResultsSteps.statusCodeShouldBe(200);

        // Verify testCode field has the expected value
        testResultsSteps.valueForFieldInPathShouldBe("[0].testTypes[0].testCode", testCode);

        // Verify testExpiryDate field has the expected value
        testResultsSteps.valueForFieldInPathShouldStartWith("[0].testTypes[0].testExpiryDate", expectedTestExpiryDate.substring(0, 10));

    }

    @WithTag("expiry_dates")
    @Title("CVSB-11509 - As a VSA/SVSA I want first expiry dates (PSV) and COIF + annual expiry dates to be calculated so that the certificates/records get accurately populated (vehicle has come in early for test) - expiryDate = Today + 1 year, - 1 day ")
    @Test
    public void testResultsFirstTestExpiryNoPreviousExpiryExactlyTwoMonthBeforeAnniversary() {

        // Read the base test result JSON.
        String testResultRecord = GenericData.readJsonValueFromFile("test-results_post_expiry_date_psv_11509.json", "$");

        // Create random vin, systemNumber and testResultId
        String randomVin = GenericData.generateRandomVin();
        String randomSystemNo = GenericData.generateRandomSystemNumber();
        String randomTestResultId = UUID.randomUUID().toString();

        // Create timestamps based on current time
        DateTime currentTime = DateTime.now().withZone(DateTimeZone.UTC);
        DateTime submittedTestStartTimestamp = currentTime.minusMinutes(15);
        DateTime submittedTestTypeStartTimestamp = currentTime.minus(10);
        DateTime submittedTestTypeEndTimestamp = currentTime.minusMinutes(5);
        DateTime submittedTestEndTimestamp = currentTime;

        // Calculate registration date based on regDateAnniversary
        DateTime regDateAnniversary = currentTime.plusMonths(2);
        DateTime regDateTimestamp = regDateAnniversary.minusYears(1);

        // Create alteration to add one more tech record to in the request body
        String testStartTimestamp = submittedTestStartTimestamp.toInstant().toString();
        String testTypeStartTimestamp = submittedTestTypeStartTimestamp.toInstant().toString();
        String testTypeEndTimestamp = submittedTestTypeEndTimestamp.toInstant().toString();
        String testEndTimestamp = submittedTestEndTimestamp.toInstant().toString();
        String regnDate = regDateTimestamp.toInstant().toString().substring(0, 10);

        // Create expected testExpiryDate based on submitted test end timestamp
        String expectedTestExpiryDate = currentTime.plusYears(1).minusDays(1).toInstant().toString();

        JsonPathAlteration alterationTestStartTimestamp = new JsonPathAlteration("$.testStartTimestamp", testStartTimestamp, "", "REPLACE");
        JsonPathAlteration alterationTestEndTimestamp = new JsonPathAlteration("$.testEndTimestamp", testEndTimestamp, "", "REPLACE");
        JsonPathAlteration alterationTestTypeStartTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeStartTimestamp", testTypeStartTimestamp, "", "REPLACE");
        JsonPathAlteration alterationTestTypeEndTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeEndTimestamp", testTypeEndTimestamp, "", "REPLACE");
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationSysNo = new JsonPathAlteration("$.systemNumber", randomSystemNo, "", "REPLACE");
        JsonPathAlteration alterationTestResultId = new JsonPathAlteration("$.testResultId", randomTestResultId, "", "REPLACE");
        JsonPathAlteration alterationTestExpiryDate = new JsonPathAlteration("$.testTypes[0].testExpiryDate", "", "", "DELETE");
        JsonPathAlteration alterationTestName = new JsonPathAlteration("$.testTypes[0].name", name, "", "REPLACE");
        JsonPathAlteration alterationTestTypeId = new JsonPathAlteration("$.testTypes[0].testTypeId", testTypeId, "", "REPLACE");
        JsonPathAlteration alterationTestTypeName = new JsonPathAlteration("$.testTypes[0].testTypeName", testTypeName, "", "REPLACE");
        JsonPathAlteration alterationTestResult = new JsonPathAlteration("$.testTypes[0].testResult", testResult, "", "REPLACE");
        JsonPathAlteration alterationVehicleSize = new JsonPathAlteration("$.vehicleSize", vehicleSize, "", "REPLACE");
        JsonPathAlteration alterationVehicleConfiguration = new JsonPathAlteration("$.vehicleConfiguration", vehicleConfiguration, "", "REPLACE");
        JsonPathAlteration alterationRegnDate = new JsonPathAlteration("$", regnDate , "regnDate", "ADD_FIELD");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(
                alterationTestStartTimestamp,
                alterationTestEndTimestamp,
                alterationTestTypeStartTimestamp,
                alterationTestTypeEndTimestamp,
                alterationVin,
                alterationTestResultId,
                alterationTestName,
                alterationTestTypeId,
                alterationTestTypeName,
                alterationVehicleSize,
                alterationVehicleConfiguration,
                alterationTestExpiryDate,
                alterationSysNo,
                alterationTestResult,
                alterationRegnDate
        ));

        // Printing the scenario to the console
        System.out.println("\nTest code: " + testCode);
        System.out.println("Registration Date: " + regnDate);
        System.out.println("Registration Anniversary: " + regDateAnniversary.toInstant().toString().substring(0,10));
        System.out.println("Today: " + currentTime.toInstant().toString().substring(0,10));
        System.out.println(("Expected expiryDate: " + expectedTestExpiryDate.substring(0,10) + "\n"));

        // Post the results, together with any alterations, and verify that they are accepted.
        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, alterations);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");

        // Retrieve the created record, and verify that the fields are present.
        testResultsSteps.getTestResults(randomSystemNo);
        testResultsSteps.statusCodeShouldBe(200);

        // Verify testCode field has the expected value
        testResultsSteps.valueForFieldInPathShouldBe("[0].testTypes[0].testCode", testCode);

        // Verify testExpiryDate field has the expected value
        testResultsSteps.valueForFieldInPathShouldStartWith("[0].testTypes[0].testExpiryDate", expectedTestExpiryDate.substring(0, 10));

    }

    @WithTag("expiry_dates")
    @Title("CVSB-11509 - As a VSA/SVSA I want first expiry dates (PSV) and COIF + annual expiry dates to be calculated so that the certificates/records get accurately populated (vehicle has come in late for test) - expiryDate = RegistrationDate + 2 years  ")
    @Test
    public void testResultsFirstTestExpiryNoPreviousExpiryLessThanTwoMonthBeforeAnniversary() {

        // Read the base test result JSON.
        String testResultRecord = GenericData.readJsonValueFromFile("test-results_post_expiry_date_psv_11509.json", "$");

        // Create random vin, systemNumber and testResultId
        String randomVin = GenericData.generateRandomVin();
        String randomSystemNo = GenericData.generateRandomSystemNumber();
        String randomTestResultId = UUID.randomUUID().toString();

        // Create timestamps based on current time
        DateTime currentTime = DateTime.now().withZone(DateTimeZone.UTC);
        DateTime submittedTestStartTimestamp = currentTime.minusMinutes(15);
        DateTime submittedTestTypeStartTimestamp = currentTime.minus(10);
        DateTime submittedTestTypeEndTimestamp = currentTime.minusMinutes(5);
        DateTime submittedTestEndTimestamp = currentTime;

        // Calculate registration date based on regDateAnniversary
        DateTime regDateAnniversary = currentTime.plusMonths(2).minusDays(1);
        DateTime regDateTimestamp = regDateAnniversary.minusYears(1);

        // Create alteration to add one more tech record to in the request body
        String testStartTimestamp = submittedTestStartTimestamp.toInstant().toString();
        String testTypeStartTimestamp = submittedTestTypeStartTimestamp.toInstant().toString();
        String testTypeEndTimestamp = submittedTestTypeEndTimestamp.toInstant().toString();
        String testEndTimestamp = submittedTestEndTimestamp.toInstant().toString();
        String regnDate = regDateTimestamp.toInstant().toString().substring(0, 10);

        // Create expected testExpiryDate based on submitted test end timestamp
        String expectedTestExpiryDate = submittedTestEndTimestamp.plusYears(1).minusDays(1).toInstant().toString();

        JsonPathAlteration alterationTestStartTimestamp = new JsonPathAlteration("$.testStartTimestamp", testStartTimestamp, "", "REPLACE");
        JsonPathAlteration alterationTestEndTimestamp = new JsonPathAlteration("$.testEndTimestamp", testEndTimestamp, "", "REPLACE");
        JsonPathAlteration alterationTestTypeStartTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeStartTimestamp", testTypeStartTimestamp, "", "REPLACE");
        JsonPathAlteration alterationTestTypeEndTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeEndTimestamp", testTypeEndTimestamp, "", "REPLACE");
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationSysNo = new JsonPathAlteration("$.systemNumber", randomSystemNo, "", "REPLACE");
        JsonPathAlteration alterationTestResultId = new JsonPathAlteration("$.testResultId", randomTestResultId, "", "REPLACE");
        JsonPathAlteration alterationTestExpiryDate = new JsonPathAlteration("$.testTypes[0].testExpiryDate", "", "", "DELETE");
        JsonPathAlteration alterationTestName = new JsonPathAlteration("$.testTypes[0].name", name, "", "REPLACE");
        JsonPathAlteration alterationTestTypeId = new JsonPathAlteration("$.testTypes[0].testTypeId", testTypeId, "", "REPLACE");
        JsonPathAlteration alterationTestTypeName = new JsonPathAlteration("$.testTypes[0].testTypeName", testTypeName, "", "REPLACE");
        JsonPathAlteration alterationTestResult = new JsonPathAlteration("$.testTypes[0].testResult", testResult, "", "REPLACE");
        JsonPathAlteration alterationVehicleSize = new JsonPathAlteration("$.vehicleSize", vehicleSize, "", "REPLACE");
        JsonPathAlteration alterationVehicleConfiguration = new JsonPathAlteration("$.vehicleConfiguration", vehicleConfiguration, "", "REPLACE");
        JsonPathAlteration alterationRegnDate = new JsonPathAlteration("$", regnDate , "regnDate", "ADD_FIELD");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(
                alterationTestStartTimestamp,
                alterationTestEndTimestamp,
                alterationTestTypeStartTimestamp,
                alterationTestTypeEndTimestamp,
                alterationVin,
                alterationTestResultId,
                alterationTestName,
                alterationTestTypeId,
                alterationTestTypeName,
                alterationVehicleSize,
                alterationVehicleConfiguration,
                alterationTestExpiryDate,
                alterationSysNo,
                alterationTestResult,
                alterationRegnDate
        ));

        // Printing the scenario to the console
        System.out.println("\nTest code: " + testCode);
        System.out.println("Registration Date: " + regnDate);
        System.out.println("Registration Anniversary: " + regDateAnniversary.toInstant().toString().substring(0,10));
        System.out.println("Today: " + currentTime.toInstant().toString().substring(0,10));
        System.out.println(("Expected expiryDate: " + expectedTestExpiryDate.substring(0,10) + "\n"));

        // Post the results, together with any alterations, and verify that they are accepted.
        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, alterations);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");

        // Retrieve the created record, and verify that the fields are present.
        testResultsSteps.getTestResults(randomSystemNo);
        testResultsSteps.statusCodeShouldBe(200);

        // Verify testCode field has the expected value
        testResultsSteps.valueForFieldInPathShouldBe("[0].testTypes[0].testCode", testCode);

        // Verify testExpiryDate field has the expected value
        testResultsSteps.valueForFieldInPathShouldStartWith("[0].testTypes[0].testExpiryDate", expectedTestExpiryDate.substring(0, 10));

    }

    @WithTag("expiry_dates")
    @Title("CVSB-11509 - As a VSA/SVSA I want first expiry dates (PSV) and COIF + annual expiry dates to be calculated so that the certificates/records get accurately populated (today is registration anniversary day) - expiryDate = RegistrationDate + 2 years  ")
    @Test
    public void testResultsFirstTestExpiryNoPreviousExpiryTodayIsRegAnniversary() {

        // Read the base test result JSON.
        String testResultRecord = GenericData.readJsonValueFromFile("test-results_post_expiry_date_psv_11509.json", "$");

        // Create random vin, systemNumber and testResultId
        String randomVin = GenericData.generateRandomVin();
        String randomSystemNo = GenericData.generateRandomSystemNumber();
        String randomTestResultId = UUID.randomUUID().toString();

        // Create timestamps based on current time
        DateTime currentTime = DateTime.now().withZone(DateTimeZone.UTC);
        DateTime submittedTestStartTimestamp = currentTime.minusMinutes(15);
        DateTime submittedTestTypeStartTimestamp = currentTime.minus(10);
        DateTime submittedTestTypeEndTimestamp = currentTime.minusMinutes(5);
        DateTime submittedTestEndTimestamp = currentTime;

        // Calculate registration date based on regDateAnniversary
        DateTime regDateAnniversary = currentTime;
        DateTime regDateTimestamp = regDateAnniversary.minusYears(1);

        // Create alteration to add one more tech record to in the request body
        String testStartTimestamp = submittedTestStartTimestamp.toInstant().toString();
        String testTypeStartTimestamp = submittedTestTypeStartTimestamp.toInstant().toString();
        String testTypeEndTimestamp = submittedTestTypeEndTimestamp.toInstant().toString();
        String testEndTimestamp = submittedTestEndTimestamp.toInstant().toString();
        String regnDate = regDateTimestamp.toInstant().toString().substring(0, 10);

        // Create expected testExpiryDate based on submitted test end timestamp
        String expectedTestExpiryDate = currentTime.plusYears(1).toInstant().toString();

        JsonPathAlteration alterationTestStartTimestamp = new JsonPathAlteration("$.testStartTimestamp", testStartTimestamp, "", "REPLACE");
        JsonPathAlteration alterationTestEndTimestamp = new JsonPathAlteration("$.testEndTimestamp", testEndTimestamp, "", "REPLACE");
        JsonPathAlteration alterationTestTypeStartTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeStartTimestamp", testTypeStartTimestamp, "", "REPLACE");
        JsonPathAlteration alterationTestTypeEndTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeEndTimestamp", testTypeEndTimestamp, "", "REPLACE");
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationSysNo = new JsonPathAlteration("$.systemNumber", randomSystemNo, "", "REPLACE");
        JsonPathAlteration alterationTestResultId = new JsonPathAlteration("$.testResultId", randomTestResultId, "", "REPLACE");
        JsonPathAlteration alterationTestExpiryDate = new JsonPathAlteration("$.testTypes[0].testExpiryDate", "", "", "DELETE");
        JsonPathAlteration alterationTestName = new JsonPathAlteration("$.testTypes[0].name", name, "", "REPLACE");
        JsonPathAlteration alterationTestTypeId = new JsonPathAlteration("$.testTypes[0].testTypeId", testTypeId, "", "REPLACE");
        JsonPathAlteration alterationTestTypeName = new JsonPathAlteration("$.testTypes[0].testTypeName", testTypeName, "", "REPLACE");
        JsonPathAlteration alterationTestResult = new JsonPathAlteration("$.testTypes[0].testResult", testResult, "", "REPLACE");
        JsonPathAlteration alterationVehicleSize = new JsonPathAlteration("$.vehicleSize", vehicleSize, "", "REPLACE");
        JsonPathAlteration alterationVehicleConfiguration = new JsonPathAlteration("$.vehicleConfiguration", vehicleConfiguration, "", "REPLACE");
        JsonPathAlteration alterationRegnDate = new JsonPathAlteration("$", regnDate , "regnDate", "ADD_FIELD");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(
                alterationTestStartTimestamp,
                alterationTestEndTimestamp,
                alterationTestTypeStartTimestamp,
                alterationTestTypeEndTimestamp,
                alterationVin,
                alterationTestResultId,
                alterationTestName,
                alterationTestTypeId,
                alterationTestTypeName,
                alterationVehicleSize,
                alterationVehicleConfiguration,
                alterationTestExpiryDate,
                alterationSysNo,
                alterationTestResult,
                alterationRegnDate
        ));

        // Printing the scenario to the console
        System.out.println("\nTest code: " + testCode);
        System.out.println("Registration Date: " + regnDate);
        System.out.println("Registration Anniversary: " + regDateAnniversary.toInstant().toString().substring(0,10));
        System.out.println("Today: " + currentTime.toInstant().toString().substring(0,10));
        System.out.println(("Expected expiryDate: " + expectedTestExpiryDate.substring(0,10) + "\n"));

        // Post the results, together with any alterations, and verify that they are accepted.
        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, alterations);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");

        // Retrieve the created record, and verify that the fields are present.
        testResultsSteps.getTestResults(randomSystemNo);
        testResultsSteps.statusCodeShouldBe(200);

        // Verify testCode field has the expected value
        testResultsSteps.valueForFieldInPathShouldBe("[0].testTypes[0].testCode", testCode);

        // Verify testExpiryDate field has the expected value
        testResultsSteps.valueForFieldInPathShouldStartWith("[0].testTypes[0].testExpiryDate", expectedTestExpiryDate.substring(0, 10));

    }

    @WithTag("expiry_dates")
    @Title("CVSB-11509 - As a VSA/SVSA I want first expiry dates (PSV) and COIF + annual expiry dates to be calculated so that the certificates/records get accurately populated (today is registration day) - expiryDate = RegistrationDate + 2 years  ")
    @Test
    public void testResultsFirstTestExpiryNoPreviousExpiryTodayIsAfterRegAnniversary() {

        // Read the base test result JSON.
        String testResultRecord = GenericData.readJsonValueFromFile("test-results_post_expiry_date_psv_11509.json", "$");

        // Create random vin, systemNumber and testResultId
        String randomVin = GenericData.generateRandomVin();
        String randomSystemNo = GenericData.generateRandomSystemNumber();
        String randomTestResultId = UUID.randomUUID().toString();

        // Create timestamps based on current time
        DateTime currentTime = DateTime.now().withZone(DateTimeZone.UTC);
        DateTime submittedTestStartTimestamp = currentTime.minusMinutes(15);
        DateTime submittedTestTypeStartTimestamp = currentTime.minus(10);
        DateTime submittedTestTypeEndTimestamp = currentTime.minusMinutes(5);
        DateTime submittedTestEndTimestamp = currentTime;

        // Calculate registration date based on regDateAnniversary
        DateTime regDateAnniversary = currentTime.plusDays(1);
        DateTime regDateTimestamp = regDateAnniversary.minusYears(1);

        // Create alteration to add one more tech record to in the request body
        String testStartTimestamp = submittedTestStartTimestamp.toInstant().toString();
        String testTypeStartTimestamp = submittedTestTypeStartTimestamp.toInstant().toString();
        String testTypeEndTimestamp = submittedTestTypeEndTimestamp.toInstant().toString();
        String testEndTimestamp = submittedTestEndTimestamp.toInstant().toString();
        String regnDate = regDateTimestamp.toInstant().toString().substring(0, 10);

        // Create expected testExpiryDate based on submitted test end timestamp
        String expectedTestExpiryDate = currentTime.plusYears(1).minusDays(1).toInstant().toString();

        JsonPathAlteration alterationTestStartTimestamp = new JsonPathAlteration("$.testStartTimestamp", testStartTimestamp, "", "REPLACE");
        JsonPathAlteration alterationTestEndTimestamp = new JsonPathAlteration("$.testEndTimestamp", testEndTimestamp, "", "REPLACE");
        JsonPathAlteration alterationTestTypeStartTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeStartTimestamp", testTypeStartTimestamp, "", "REPLACE");
        JsonPathAlteration alterationTestTypeEndTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeEndTimestamp", testTypeEndTimestamp, "", "REPLACE");
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationSysNo = new JsonPathAlteration("$.systemNumber", randomSystemNo, "", "REPLACE");
        JsonPathAlteration alterationTestResultId = new JsonPathAlteration("$.testResultId", randomTestResultId, "", "REPLACE");
        JsonPathAlteration alterationTestExpiryDate = new JsonPathAlteration("$.testTypes[0].testExpiryDate", "", "", "DELETE");
        JsonPathAlteration alterationTestName = new JsonPathAlteration("$.testTypes[0].name", name, "", "REPLACE");
        JsonPathAlteration alterationTestTypeId = new JsonPathAlteration("$.testTypes[0].testTypeId", testTypeId, "", "REPLACE");
        JsonPathAlteration alterationTestTypeName = new JsonPathAlteration("$.testTypes[0].testTypeName", testTypeName, "", "REPLACE");
        JsonPathAlteration alterationTestResult = new JsonPathAlteration("$.testTypes[0].testResult", testResult, "", "REPLACE");
        JsonPathAlteration alterationVehicleSize = new JsonPathAlteration("$.vehicleSize", vehicleSize, "", "REPLACE");
        JsonPathAlteration alterationVehicleConfiguration = new JsonPathAlteration("$.vehicleConfiguration", vehicleConfiguration, "", "REPLACE");
        JsonPathAlteration alterationRegnDate = new JsonPathAlteration("$", regnDate , "regnDate", "ADD_FIELD");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(
                alterationTestStartTimestamp,
                alterationTestEndTimestamp,
                alterationTestTypeStartTimestamp,
                alterationTestTypeEndTimestamp,
                alterationVin,
                alterationTestResultId,
                alterationTestName,
                alterationTestTypeId,
                alterationTestTypeName,
                alterationVehicleSize,
                alterationVehicleConfiguration,
                alterationTestExpiryDate,
                alterationSysNo,
                alterationTestResult,
                alterationRegnDate
        ));

        // Printing the scenario to the console
        System.out.println("\nTest code: " + testCode);
        System.out.println("Registration Date: " + regnDate);
        System.out.println("Registration Anniversary: " + regDateAnniversary.toInstant().toString().substring(0,10));
        System.out.println("Today: " + currentTime.toInstant().toString().substring(0,10));
        System.out.println(("Expected expiryDate: " + expectedTestExpiryDate.substring(0,10) + "\n"));

        // Post the results, together with any alterations, and verify that they are accepted.
        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, alterations);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");

        // Retrieve the created record, and verify that the fields are present.
        testResultsSteps.getTestResults(randomSystemNo);
        testResultsSteps.statusCodeShouldBe(200);

        // Verify testCode field has the expected value
        testResultsSteps.valueForFieldInPathShouldBe("[0].testTypes[0].testCode", testCode);

        // Verify testExpiryDate field has the expected value
        testResultsSteps.valueForFieldInPathShouldStartWith("[0].testTypes[0].testExpiryDate", expectedTestExpiryDate.substring(0, 10));

    }

//
//    @WithTag("expiry_dates")
//    @Title("CVSB-8684 - TC1 - AC1 - PSV Annual test WITH PREVIOUS Expiry Date - Previous testExpiryDate = Today - 1 day")
//    @Test
//    public void testResultsFirstTestExpiryPsvTodayMinusOneDay() {
//
//        // Read the base INSERT test result JSON.
//        String insertedTestResultRecord = GenericData.readJsonValueFromFile("test-results_insert_expiry_date_psv_8684.json", "$");
//        // Read the base POST test result JSON.
//        String postTestResultRecord = GenericData.readJsonValueFromFile("test-results_post_expiry_date_psv_8684.json", "$");
//
//        DateTime currentTimestamp = DateTime.now().withZone(DateTimeZone.UTC);
//
//        //
//        String randomVin = GenericData.generateRandomVin();
//        String randomTestResultId = UUID.randomUUID().toString();
//        String randomSystemNo = GenericData.generateRandomSystemNumber();
//
//        // Create inserted record.
//        DateTime insertedTestStartTimestamp = currentTimestamp.minusYears(1).minusMinutes(15);
//        DateTime insertedTestTypeStartTimestamp = currentTimestamp.minusYears(1).minusMinutes(10);
//        DateTime insertedLastUpdatedAt = currentTimestamp.minusYears(1).minusMinutes(5);
//
//        DateTime insertedTestAnniversaryDate = currentTimestamp.minusMonths(2).minusDays(1);
//        DateTime insertedTestExpiryDate = currentTimestamp.minusDays(1);
//
//        DateTime insertedCreatedAt = currentTimestamp.minusYears(1).minusMinutes(5);
//        DateTime insertedTestTypeEndTimestamp = currentTimestamp.minusYears(1).minusMinutes(5);
//        DateTime insertedTestEndTimestamp = currentTimestamp.minusYears(1);
//
//        String insertableTestStartTimestamp = insertedTestStartTimestamp.toInstant().toString();
//        String insertableTestTypeStartTimestamp = insertedTestTypeStartTimestamp.toInstant().toString();
//        String insertableLastUpdatedAt = insertedLastUpdatedAt.toInstant().toString();
//        String insertableTestAnniversaryDate = insertedTestAnniversaryDate.toInstant().toString();
//        String insertableTestExpiryDate = insertedTestExpiryDate.toInstant().toString();
//        String insertableCreatedAt = insertedCreatedAt.toInstant().toString();
//        String insertableTestTypeEndTimestamp = insertedTestTypeEndTimestamp.toInstant().toString();
//        String insertableTestEndTimestamp = insertedTestEndTimestamp.toInstant().toString();
//
//        // Create alteration to add one more tech record to in the inserted data
//        JsonPathAlteration alterationInsertVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
//        JsonPathAlteration alterationInsertSysNo = new JsonPathAlteration("$.systemNumber", randomSystemNo, "", "REPLACE");
//        JsonPathAlteration alterationInsertTestResultId = new JsonPathAlteration("$.testResultId", UUID.randomUUID().toString(), "", "REPLACE");
//        JsonPathAlteration alterationInsertTestStartTimestamp = new JsonPathAlteration("$.testStartTimestamp", insertableTestStartTimestamp, "", "REPLACE");
//        JsonPathAlteration alterationInsertTestTypeStartTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeStartTimestamp", insertableTestTypeStartTimestamp, "", "REPLACE");
//        JsonPathAlteration alterationInsertLastUpdatedAt = new JsonPathAlteration("$.testTypes[0].lastUpdatedAt", insertableLastUpdatedAt, "", "REPLACE");
//        JsonPathAlteration alterationInsertTestAnniversaryDate = new JsonPathAlteration("$.testTypes[0].testAnniversaryDate", insertableTestAnniversaryDate, "", "REPLACE");
//        JsonPathAlteration alterationInsertTestExpiryDate = new JsonPathAlteration("$.testTypes[0].testExpiryDate", insertableTestExpiryDate, "", "REPLACE");
//        JsonPathAlteration alterationInsertCreatedAt = new JsonPathAlteration("$.testTypes[0].createdAt", insertableCreatedAt, "", "REPLACE");
//        JsonPathAlteration alterationInsertTestTypeEndTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeEndTimestamp", insertableTestTypeEndTimestamp, "", "REPLACE");
//        JsonPathAlteration alterationInsertTestEndTimestamp = new JsonPathAlteration("$.testEndTimestamp", insertableTestEndTimestamp, "", "REPLACE");
////        System.out.println("######################### INSERTED TEST CODE: " + insertedTestCode + "  #########################");
//        JsonPathAlteration alterationInsertTestCode = new JsonPathAlteration("$.testTypes[0].testCode", insertedTestCode, "", "REPLACE");
//
//        // Collate the list of alterations for the inserted record.
//        List<JsonPathAlteration> insertAlterations = new ArrayList<>(Arrays.asList(
//                alterationInsertVin,
//                alterationInsertTestResultId,
//                alterationInsertTestStartTimestamp,
//                alterationInsertTestTypeStartTimestamp,
//                alterationInsertLastUpdatedAt,
//                alterationInsertTestAnniversaryDate,
//                alterationInsertTestExpiryDate,
//                alterationInsertCreatedAt,
//                alterationInsertTestTypeEndTimestamp,
//                alterationInsertTestCode,
//                alterationInsertSysNo,
//                alterationInsertTestEndTimestamp
//        ));
//
//        if (isAnnualWithCertificate) {
//            insertAlterations.add(new JsonPathAlteration("$.testTypes[0]", "Annual With Certificate", "testTypeClassification", "ADD_FIELD"));
//        }
//
//        // Insert the altered record
//        String alteredJson = GenericData.applyJsonAlterations(insertedTestResultRecord, insertAlterations);
//        testResultsSteps.insertRecordInDynamo(alteredJson, "test-results");
//
//        // Create submitted
//        DateTime submittedTestStartTimestamp = currentTimestamp.minusMinutes(15);
//        DateTime submittedTestTypeStartTimestamp = currentTimestamp.plusMinutes(10);
//        DateTime submittedTypeEndTimestamp = currentTimestamp.plusMinutes(5);
//        DateTime submittedEndTimestamp = currentTimestamp;
//
//        String testStartTimestamp = submittedTestStartTimestamp.toInstant().toString();
//        String testTypeStartTimestamp = submittedTestTypeStartTimestamp.toInstant().toString();
//        String testTypeEndTimestamp = submittedTypeEndTimestamp.toInstant().toString();
//        String testEndTimestamp = submittedEndTimestamp.toInstant().toString();
//
//        String testExpectedDate = submittedTypeEndTimestamp.plusYears(1).minusDays(1).toInstant().toString();
//
//
//        // Create alteration to add one more tech record to in the request body
//        JsonPathAlteration alterationTestStartTimestamp = new JsonPathAlteration("$.testStartTimestamp", testStartTimestamp, "", "REPLACE");
//        JsonPathAlteration alterationTestEndTimestamp = new JsonPathAlteration("$.testEndTimestamp", testEndTimestamp, "", "REPLACE");
//        JsonPathAlteration alterationTestTypeStartTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeStartTimestamp", testTypeStartTimestamp, "", "REPLACE");
//        JsonPathAlteration alterationTestTypeEndTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeEndTimestamp", testTypeEndTimestamp, "", "REPLACE");
//        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
//        JsonPathAlteration alterationSysNo = new JsonPathAlteration("$.systemNumber", randomSystemNo, "", "REPLACE");
//        JsonPathAlteration alterationTestResultId = new JsonPathAlteration("$.testResultId", randomTestResultId, "", "REPLACE");
//        JsonPathAlteration alterationTestName = new JsonPathAlteration("$.testTypes[0].name", name, "", "REPLACE");
//        JsonPathAlteration alterationTestTypeId = new JsonPathAlteration("$.testTypes[0].testTypeId", testTypeId, "", "REPLACE");
//        JsonPathAlteration alterationTestTypeName = new JsonPathAlteration("$.testTypes[0].testTypeName", testTypeName, "", "REPLACE");
//        JsonPathAlteration alterationTestResult = new JsonPathAlteration("$.testTypes[0].testResult", testResult, "", "REPLACE");
//        JsonPathAlteration alterationVehicleSize = new JsonPathAlteration("$.vehicleSize", vehicleSize, "", "REPLACE");
//        JsonPathAlteration alterationVehicleConfiguration = new JsonPathAlteration("$.vehicleConfiguration", vehicleConfiguration, "", "REPLACE");
//
//        // Collate the list of alterations.
//        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(
//                alterationTestStartTimestamp,
//                alterationTestEndTimestamp,
//                alterationTestTypeStartTimestamp,
//                alterationTestTypeEndTimestamp,
//                alterationVin,
//                alterationTestResultId,
//                alterationTestName,
//                alterationTestTypeId,
//                alterationTestTypeName,
//                alterationVehicleSize,
//                alterationVehicleConfiguration,
//                alterationSysNo,
//                alterationTestResult
//        ));
//
//        // Post the results, together with any alterations, and verify that they are accepted.
//        testResultsSteps.postVehicleTestResultsWithAlterations(postTestResultRecord, alterations);
//
//        testResultsSteps.statusCodeShouldBe(201);
//        testResultsSteps.validateData("Test records created");
//
//        // Retrieve the created record, and verify that the fields are present.
//        testResultsSteps.getTestResultsBetweenDate(randomSystemNo, submittedTestStartTimestamp.minusMinutes(10).toInstant().toString(), submittedEndTimestamp.plusMinutes(10).toInstant().toString());
//        testResultsSteps.statusCodeShouldBe(200);
//
//        // Verify testCode field has the expected value
//        testResultsSteps.valueForFieldInPathShouldBe("[0].testTypes[0].testCode", testCode);
//
//        // Verify testExpiryDate field has the expected value
//        testResultsSteps.valueForFieldInPathShouldStartWith("[0].testTypes[0].testExpiryDate", testExpectedDate.substring(0, 10));
//
////        // Verify testAnniversaryDate field has the expected value
////        testResultsSteps.valueForFieldInPathShouldStartWith("[0].testTypes[0].testAnniversaryDate", testExpectedDate.substring(0,10));
//
//    }
//
//    @WithTag("expiry_dates")
//    @Title("CVSB-8684 - TC1 - AC1 - PSV Annual test WITH PREVIOUS Expiry Date - Previous testExpiryDate = Today")
//    @Test
//    public void testResultsFirstTestExpiryPsvToday() {
//
//        // Read the base INSERT test result JSON.
//        String insertedTestResultRecord = GenericData.readJsonValueFromFile("test-results_insert_expiry_date_psv_8684.json", "$");
//        // Read the base POST test result JSON.
//        String postTestResultRecord = GenericData.readJsonValueFromFile("test-results_post_expiry_date_psv_8684.json", "$");
//
//        DateTime currentTimestamp = DateTime.now().withZone(DateTimeZone.UTC);
//
//        //
//        String randomVin = GenericData.generateRandomVin();
//        String randomTestResultId = UUID.randomUUID().toString();
//        String randomSystemNo = GenericData.generateRandomSystemNumber();
//
//        // Create inserted record.
//        DateTime insertedTestStartTimestamp = currentTimestamp.minusYears(1).plusDays(1).minusMinutes(15);
//        DateTime insertedTestTypeStartTimestamp = currentTimestamp.minusYears(1).plusDays(1).minusMinutes(10);
//        DateTime insertedLastUpdatedAt = currentTimestamp.minusYears(1).plusDays(1).minusMinutes(5);
//
//        DateTime insertedTestAnniversaryDate = currentTimestamp.minusMonths(2);
//        DateTime insertedTestExpiryDate = currentTimestamp;
//
//        DateTime insertedCreatedAt = currentTimestamp.minusYears(1).plusDays(1).minusMinutes(5);
//        DateTime insertedTestTypeEndTimestamp = currentTimestamp.minusYears(1).plusDays(1).minusMinutes(5);
//        DateTime insertedTestEndTimestamp = currentTimestamp.minusYears(1).plusDays(1);
//
//        String insertableTestStartTimestamp = insertedTestStartTimestamp.toInstant().toString();
//        String insertableTestTypeStartTimestamp = insertedTestTypeStartTimestamp.toInstant().toString();
//        String insertableLastUpdatedAt = insertedLastUpdatedAt.toInstant().toString();
//        String insertableTestAnniversaryDate = insertedTestAnniversaryDate.toInstant().toString();
//        String insertableTestExpiryDate = insertedTestExpiryDate.toInstant().toString();
//        String insertableCreatedAt = insertedCreatedAt.toInstant().toString();
//        String insertableTestTypeEndTimestamp = insertedTestTypeEndTimestamp.toInstant().toString();
//        String insertableTestEndTimestamp = insertedTestEndTimestamp.toInstant().toString();
//
//        // Create alteration to add one more tech record to in the inserted data
//        JsonPathAlteration alterationInsertVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
//        JsonPathAlteration alterationInsertSysNo = new JsonPathAlteration("$.systemNumber", randomSystemNo, "", "REPLACE");
//        JsonPathAlteration alterationInsertTestResultId = new JsonPathAlteration("$.testResultId", UUID.randomUUID().toString(), "", "REPLACE");
//        JsonPathAlteration alterationInsertTestStartTimestamp = new JsonPathAlteration("$.testStartTimestamp", insertableTestStartTimestamp, "", "REPLACE");
//        JsonPathAlteration alterationInsertTestTypeStartTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeStartTimestamp", insertableTestTypeStartTimestamp, "", "REPLACE");
//        JsonPathAlteration alterationInsertLastUpdatedAt = new JsonPathAlteration("$.testTypes[0].lastUpdatedAt", insertableLastUpdatedAt, "", "REPLACE");
//        JsonPathAlteration alterationInsertTestAnniversaryDate = new JsonPathAlteration("$.testTypes[0].testAnniversaryDate", insertableTestAnniversaryDate, "", "REPLACE");
//        JsonPathAlteration alterationInsertTestExpiryDate = new JsonPathAlteration("$.testTypes[0].testExpiryDate", insertableTestExpiryDate, "", "REPLACE");
//        JsonPathAlteration alterationInsertCreatedAt = new JsonPathAlteration("$.testTypes[0].createdAt", insertableCreatedAt, "", "REPLACE");
//        JsonPathAlteration alterationInsertTestTypeEndTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeEndTimestamp", insertableTestTypeEndTimestamp, "", "REPLACE");
//        JsonPathAlteration alterationInsertTestEndTimestamp = new JsonPathAlteration("$.testEndTimestamp", insertableTestEndTimestamp, "", "REPLACE");
//        JsonPathAlteration alterationInsertTestCode = new JsonPathAlteration("$.testTypes[0].testCode", insertedTestCode, "", "REPLACE");
//
//        // Collate the list of alterations for the inserted record.
//        List<JsonPathAlteration> insertAlterations = new ArrayList<>(Arrays.asList(
//                alterationInsertVin,
//                alterationInsertTestResultId,
//                alterationInsertTestStartTimestamp,
//                alterationInsertTestTypeStartTimestamp,
//                alterationInsertLastUpdatedAt,
//                alterationInsertTestAnniversaryDate,
//                alterationInsertTestExpiryDate,
//                alterationInsertCreatedAt,
//                alterationInsertTestTypeEndTimestamp,
//                alterationInsertTestEndTimestamp,
//                alterationInsertSysNo,
//                alterationInsertTestCode
//        ));
//
//        if (isAnnualWithCertificate) {
//            insertAlterations.add(new JsonPathAlteration("$.testTypes[0]", "Annual With Certificate", "testTypeClassification", "ADD_FIELD"));
//        }
//
//        // Insert the altered record
//        String alteredJson = GenericData.applyJsonAlterations(insertedTestResultRecord, insertAlterations);
//        testResultsSteps.insertRecordInDynamo(alteredJson, "test-results");
//
//        // Create submitted
//        DateTime submittedTestStartTimestamp = currentTimestamp.minusMinutes(15);
//        DateTime submittedTestTypeStartTimestamp = currentTimestamp.plusMinutes(10);
//        DateTime submittedTypeEndTimestamp = currentTimestamp.plusMinutes(5);
//        DateTime submittedEndTimestamp = currentTimestamp;
//
//        String testStartTimestamp = submittedTestStartTimestamp.toInstant().toString();
//        String testTypeStartTimestamp = submittedTestTypeStartTimestamp.toInstant().toString();
//        String testTypeEndTimestamp = submittedTypeEndTimestamp.toInstant().toString();
//        String testEndTimestamp = submittedEndTimestamp.toInstant().toString();
//
//        String testExpectedDate = submittedTypeEndTimestamp.plusYears(1).toInstant().toString();
//
//
//        // Create alteration to add one more tech record to in the request body
//        JsonPathAlteration alterationTestStartTimestamp = new JsonPathAlteration("$.testStartTimestamp", testStartTimestamp, "", "REPLACE");
//        JsonPathAlteration alterationTestEndTimestamp = new JsonPathAlteration("$.testEndTimestamp", testEndTimestamp, "", "REPLACE");
//        JsonPathAlteration alterationTestTypeStartTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeStartTimestamp", testTypeStartTimestamp, "", "REPLACE");
//        JsonPathAlteration alterationTestTypeEndTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeEndTimestamp", testTypeEndTimestamp, "", "REPLACE");
//        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
//        JsonPathAlteration alterationSysNo = new JsonPathAlteration("$.systemNumber", randomSystemNo, "", "REPLACE");
//        JsonPathAlteration alterationTestResultId = new JsonPathAlteration("$.testResultId", randomTestResultId, "", "REPLACE");
//        JsonPathAlteration alterationTestName = new JsonPathAlteration("$.testTypes[0].name", name, "", "REPLACE");
//        JsonPathAlteration alterationTestTypeId = new JsonPathAlteration("$.testTypes[0].testTypeId", testTypeId, "", "REPLACE");
//        JsonPathAlteration alterationTestTypeName = new JsonPathAlteration("$.testTypes[0].testTypeName", testTypeName, "", "REPLACE");
//        JsonPathAlteration alterationTestResult = new JsonPathAlteration("$.testTypes[0].testResult", testResult, "", "REPLACE");
//        JsonPathAlteration alterationVehicleSize = new JsonPathAlteration("$.vehicleSize", vehicleSize, "", "REPLACE");
//        JsonPathAlteration alterationVehicleConfiguration = new JsonPathAlteration("$.vehicleConfiguration", vehicleConfiguration, "", "REPLACE");
//
//        // Collate the list of alterations.
//        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(
//                alterationTestStartTimestamp,
//                alterationTestEndTimestamp,
//                alterationTestTypeStartTimestamp,
//                alterationTestTypeEndTimestamp,
//                alterationVin,
//                alterationTestResultId,
//                alterationTestName,
//                alterationTestTypeId,
//                alterationTestTypeName,
//                alterationVehicleSize,
//                alterationVehicleConfiguration,
//                alterationSysNo,
//                alterationTestResult
//        ));
//
//        // Post the results, together with any alterations, and verify that they are accepted.
//        testResultsSteps.postVehicleTestResultsWithAlterations(postTestResultRecord, alterations);
//
////        System.out.println("\n######################## POSTED ########################\n\n");
////        System.out.println(new GsonBuilder().setPrettyPrinting().create().toJson(new JsonParser().parse(GenericData.applyJsonAlterations(postTestResultRecord, alterations)).getAsJsonObject()));
////        System.out.println("\n########################   END  ########################\n\n");
//
//        testResultsSteps.statusCodeShouldBe(201);
//        testResultsSteps.validateData("Test records created");
//
//        // Retrieve the created record, and verify that the fields are present.
//        testResultsSteps.getTestResultsBetweenDate(randomSystemNo, submittedTestStartTimestamp.minusMinutes(10).toInstant().toString(), submittedEndTimestamp.plusMinutes(10).toInstant().toString());
//        testResultsSteps.statusCodeShouldBe(200);
//
//        // Verify testCode field has the expected value
//        testResultsSteps.valueForFieldInPathShouldBe("[0].testTypes[0].testCode", testCode);
//
//        // Verify testExpiryDate field has the expected value
//        testResultsSteps.valueForFieldInPathShouldStartWith("[0].testTypes[0].testExpiryDate", testExpectedDate.substring(0, 10));
//
////        // Verify testAnniversaryDate field has the expected value
////        testResultsSteps.valueForFieldInPathShouldStartWith("[0].testTypes[0].testAnniversaryDate", testExpectedDate.substring(0,10));
//
//    }
//
//    @WithTag("expiry_dates")
//    @Title("CVSB-8684 - TC1 - AC1 - PSV Annual test WITH PREVIOUS Expiry Date - Previous testExpiryDate = Today + 1 day")
//    @Test
//    public void testResultsFirstTestExpiryPsvTodayPlusOneDay() {
//
//        // Read the base INSERT test result JSON.
//        String insertedTestResultRecord = GenericData.readJsonValueFromFile("test-results_insert_expiry_date_psv_8684.json", "$");
//        // Read the base POST test result JSON.
//        String postTestResultRecord = GenericData.readJsonValueFromFile("test-results_post_expiry_date_psv_8684.json", "$");
//
//        DateTime currentTimestamp = DateTime.now().withZone(DateTimeZone.UTC);
//
//        //
//        String randomVin = GenericData.generateRandomVin();
//        String randomTestResultId = UUID.randomUUID().toString();
//        String randomSystemNo = GenericData.generateRandomSystemNumber();
//
//        // Create inserted record.
//        DateTime insertedTestStartTimestamp = currentTimestamp.minusYears(1).plusDays(2).minusMinutes(15);
//        DateTime insertedTestTypeStartTimestamp = currentTimestamp.minusYears(1).plusDays(2).minusMinutes(10);
//        DateTime insertedLastUpdatedAt = currentTimestamp.minusYears(1).plusDays(2).minusMinutes(5);
//
//        DateTime insertedTestAnniversaryDate = currentTimestamp.minusMonths(2).plusDays(1);
//        DateTime insertedTestExpiryDate = currentTimestamp.plusDays(1);
//
//        DateTime insertedCreatedAt = currentTimestamp.minusYears(1).plusDays(2).minusMinutes(5);
//        DateTime insertedTestTypeEndTimestamp = currentTimestamp.minusYears(1).plusDays(2).minusMinutes(5);
//        DateTime insertedTestEndTimestamp = currentTimestamp.minusYears(1).plusDays(2);
//
//        String insertableTestStartTimestamp = insertedTestStartTimestamp.toInstant().toString();
//        String insertableTestTypeStartTimestamp = insertedTestTypeStartTimestamp.toInstant().toString();
//        String insertableLastUpdatedAt = insertedLastUpdatedAt.toInstant().toString();
//        String insertableTestAnniversaryDate = insertedTestAnniversaryDate.toInstant().toString();
//        String insertableTestExpiryDate = insertedTestExpiryDate.toInstant().toString();
//        String insertableCreatedAt = insertedCreatedAt.toInstant().toString();
//        String insertableTestTypeEndTimestamp = insertedTestTypeEndTimestamp.toInstant().toString();
//        String insertableTestEndTimestamp = insertedTestEndTimestamp.toInstant().toString();
//
//        // Create alteration to add one more tech record to in the inserted data
//        JsonPathAlteration alterationInsertVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
//        JsonPathAlteration alterationInsertSysNo = new JsonPathAlteration("$.systemNumber", randomSystemNo, "", "REPLACE");
//        JsonPathAlteration alterationInsertTestResultId = new JsonPathAlteration("$.testResultId", UUID.randomUUID().toString(), "", "REPLACE");
//        JsonPathAlteration alterationInsertTestStartTimestamp = new JsonPathAlteration("$.testStartTimestamp", insertableTestStartTimestamp, "", "REPLACE");
//        JsonPathAlteration alterationInsertTestTypeStartTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeStartTimestamp", insertableTestTypeStartTimestamp, "", "REPLACE");
//        JsonPathAlteration alterationInsertLastUpdatedAt = new JsonPathAlteration("$.testTypes[0].lastUpdatedAt", insertableLastUpdatedAt, "", "REPLACE");
//        JsonPathAlteration alterationInsertTestAnniversaryDate = new JsonPathAlteration("$.testTypes[0].testAnniversaryDate", insertableTestAnniversaryDate, "", "REPLACE");
//        JsonPathAlteration alterationInsertTestExpiryDate = new JsonPathAlteration("$.testTypes[0].testExpiryDate", insertableTestExpiryDate, "", "REPLACE");
//        JsonPathAlteration alterationInsertCreatedAt = new JsonPathAlteration("$.testTypes[0].createdAt", insertableCreatedAt, "", "REPLACE");
//        JsonPathAlteration alterationInsertTestTypeEndTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeEndTimestamp", insertableTestTypeEndTimestamp, "", "REPLACE");
//        JsonPathAlteration alterationInsertTestEndTimestamp = new JsonPathAlteration("$.testEndTimestamp", insertableTestEndTimestamp, "", "REPLACE");
//        JsonPathAlteration alterationInsertTestCode = new JsonPathAlteration("$.testTypes[0].testCode", insertedTestCode, "", "REPLACE");
//
//        // Collate the list of alterations for the inserted record.
//        List<JsonPathAlteration> insertAlterations = new ArrayList<>(Arrays.asList(
//                alterationInsertVin,
//                alterationInsertTestResultId,
//                alterationInsertTestStartTimestamp,
//                alterationInsertTestTypeStartTimestamp,
//                alterationInsertLastUpdatedAt,
//                alterationInsertTestAnniversaryDate,
//                alterationInsertTestExpiryDate,
//                alterationInsertCreatedAt,
//                alterationInsertTestTypeEndTimestamp,
//                alterationInsertTestCode,
//                alterationInsertSysNo,
//                alterationInsertTestEndTimestamp
//        ));
//
//        if (isAnnualWithCertificate) {
//            insertAlterations.add(new JsonPathAlteration("$.testTypes[0]", "Annual With Certificate", "testTypeClassification", "ADD_FIELD"));
//        }
//
//        // Insert the altered record
//        String alteredJson = GenericData.applyJsonAlterations(insertedTestResultRecord, insertAlterations);
//        testResultsSteps.insertRecordInDynamo(alteredJson, "test-results");
//
//        // Create submitted
//        DateTime submittedTestStartTimestamp = currentTimestamp.minusMinutes(15);
//        DateTime submittedTestTypeStartTimestamp = currentTimestamp.plusMinutes(10);
//        DateTime submittedTypeEndTimestamp = currentTimestamp.plusMinutes(5);
//        DateTime submittedEndTimestamp = currentTimestamp;
//
//        String testStartTimestamp = submittedTestStartTimestamp.toInstant().toString();
//        String testTypeStartTimestamp = submittedTestTypeStartTimestamp.toInstant().toString();
//        String testTypeEndTimestamp = submittedTypeEndTimestamp.toInstant().toString();
//        String testEndTimestamp = submittedEndTimestamp.toInstant().toString();
//
//        String testExpectedDate = insertedTestExpiryDate.plusYears(1).toInstant().toString();
//
//
//        // Create alteration to add one more tech record to in the request body
//        JsonPathAlteration alterationTestStartTimestamp = new JsonPathAlteration("$.testStartTimestamp", testStartTimestamp, "", "REPLACE");
//        JsonPathAlteration alterationTestEndTimestamp = new JsonPathAlteration("$.testEndTimestamp", testEndTimestamp, "", "REPLACE");
//        JsonPathAlteration alterationTestTypeStartTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeStartTimestamp", testTypeStartTimestamp, "", "REPLACE");
//        JsonPathAlteration alterationTestTypeEndTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeEndTimestamp", testTypeEndTimestamp, "", "REPLACE");
//        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
//        JsonPathAlteration alterationSysNo = new JsonPathAlteration("$.systemNumber", randomSystemNo, "", "REPLACE");
//        JsonPathAlteration alterationTestResultId = new JsonPathAlteration("$.testResultId", randomTestResultId, "", "REPLACE");
//        JsonPathAlteration alterationTestName = new JsonPathAlteration("$.testTypes[0].name", name, "", "REPLACE");
//        JsonPathAlteration alterationTestTypeId = new JsonPathAlteration("$.testTypes[0].testTypeId", testTypeId, "", "REPLACE");
//        JsonPathAlteration alterationTestTypeName = new JsonPathAlteration("$.testTypes[0].testTypeName", testTypeName, "", "REPLACE");
//        JsonPathAlteration alterationTestResult = new JsonPathAlteration("$.testTypes[0].testResult", testResult, "", "REPLACE");
//        JsonPathAlteration alterationVehicleSize = new JsonPathAlteration("$.vehicleSize", vehicleSize, "", "REPLACE");
//        JsonPathAlteration alterationVehicleConfiguration = new JsonPathAlteration("$.vehicleConfiguration", vehicleConfiguration, "", "REPLACE");
//
//        // Collate the list of alterations.
//        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(
//                alterationTestStartTimestamp,
//                alterationTestEndTimestamp,
//                alterationTestTypeStartTimestamp,
//                alterationTestTypeEndTimestamp,
//                alterationVin,
//                alterationTestResultId,
//                alterationTestName,
//                alterationTestTypeId,
//                alterationTestTypeName,
//                alterationVehicleSize,
//                alterationVehicleConfiguration,
//                alterationSysNo,
//                alterationTestResult
//        ));
//
//        // Post the results, together with any alterations, and verify that they are accepted.
//        testResultsSteps.postVehicleTestResultsWithAlterations(postTestResultRecord, alterations);
//
//        testResultsSteps.statusCodeShouldBe(201);
//        testResultsSteps.validateData("Test records created");
//
//        // Retrieve the created record, and verify that the fields are present.
//        testResultsSteps.getTestResultsBetweenDate(randomSystemNo, submittedTestStartTimestamp.minusMinutes(10).toInstant().toString(), submittedEndTimestamp.plusMinutes(10).toInstant().toString());
//        testResultsSteps.statusCodeShouldBe(200);
//
//        // Verify testCode field has the expected value
//        testResultsSteps.valueForFieldInPathShouldBe("[0].testTypes[0].testCode", testCode);
//
//        // Verify testExpiryDate field has the expected value
//        testResultsSteps.valueForFieldInPathShouldStartWith("[0].testTypes[0].testExpiryDate", testExpectedDate.substring(0, 10));
//
////        // Verify testAnniversaryDate field has the expected value
////        testResultsSteps.valueForFieldInPathShouldStartWith("[0].testTypes[0].testAnniversaryDate", testExpectedDate.substring(0,10));
//
//    }
//
//    @WithTag("expiry_dates")
//    @Title("CVSB-8684 - TC1 - AC1 - PSV Annual test WITH PREVIOUS Expiry Date - Previous testExpiryDate = Today + 2 months")
//    @Test
//    public void testResultsFirstTestExpiryPsvTodayPlusTwoMonths() {
//
//        // Read the base INSERT test result JSON.
//        String insertedTestResultRecord = GenericData.readJsonValueFromFile("test-results_insert_expiry_date_psv_8684.json", "$");
//        // Read the base POST test result JSON.
//        String postTestResultRecord = GenericData.readJsonValueFromFile("test-results_post_expiry_date_psv_8684.json", "$");
//
//        DateTime currentTimestamp = DateTime.now().withZone(DateTimeZone.UTC);
//
//        //
//        String randomVin = GenericData.generateRandomVin();
//        String randomTestResultId = UUID.randomUUID().toString();
//        String randomSystemNo = GenericData.generateRandomSystemNumber();
//
//        // Create inserted record.
//        DateTime insertedTestStartTimestamp = currentTimestamp.minusYears(1).plusMonths(2).plusDays(1).minusMinutes(15);
//        DateTime insertedTestTypeStartTimestamp = currentTimestamp.minusYears(1).plusMonths(2).plusDays(1).minusMinutes(10);
//        DateTime insertedLastUpdatedAt = currentTimestamp.minusYears(1).plusMonths(2).plusDays(1).minusMinutes(5);
//
//        DateTime insertedTestAnniversaryDate = currentTimestamp;
//        DateTime insertedTestExpiryDate = currentTimestamp.plusMonths(2);
//
//        DateTime insertedCreatedAt = currentTimestamp.minusYears(1).plusMonths(2).plusDays(1).minusMinutes(5);
//        DateTime insertedTestTypeEndTimestamp = currentTimestamp.minusYears(1).plusMonths(2).plusDays(1).minusMinutes(5);
//        DateTime insertedTestEndTimestamp = currentTimestamp.minusYears(1).plusMonths(2).plusDays(1);
//
//        String insertableTestStartTimestamp = insertedTestStartTimestamp.toInstant().toString();
//        String insertableTestTypeStartTimestamp = insertedTestTypeStartTimestamp.toInstant().toString();
//        String insertableLastUpdatedAt = insertedLastUpdatedAt.toInstant().toString();
//        String insertableTestAnniversaryDate = insertedTestAnniversaryDate.toInstant().toString();
//        String insertableTestExpiryDate = insertedTestExpiryDate.toInstant().toString();
//        String insertableCreatedAt = insertedCreatedAt.toInstant().toString();
//        String insertableTestTypeEndTimestamp = insertedTestTypeEndTimestamp.toInstant().toString();
//        String insertableTestEndTimestamp = insertedTestEndTimestamp.toInstant().toString();
//
//        // Create alteration to add one more tech record to in the inserted data
//        JsonPathAlteration alterationInsertVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
//        JsonPathAlteration alterationInsertSysNo = new JsonPathAlteration("$.systemNumber", randomSystemNo, "", "REPLACE");
//        JsonPathAlteration alterationInsertTestResultId = new JsonPathAlteration("$.testResultId", UUID.randomUUID().toString(), "", "REPLACE");
//        JsonPathAlteration alterationInsertTestStartTimestamp = new JsonPathAlteration("$.testStartTimestamp", insertableTestStartTimestamp, "", "REPLACE");
//        JsonPathAlteration alterationInsertTestTypeStartTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeStartTimestamp", insertableTestTypeStartTimestamp, "", "REPLACE");
//        JsonPathAlteration alterationInsertLastUpdatedAt = new JsonPathAlteration("$.testTypes[0].lastUpdatedAt", insertableLastUpdatedAt, "", "REPLACE");
//        JsonPathAlteration alterationInsertTestAnniversaryDate = new JsonPathAlteration("$.testTypes[0].testAnniversaryDate", insertableTestAnniversaryDate, "", "REPLACE");
//        JsonPathAlteration alterationInsertTestExpiryDate = new JsonPathAlteration("$.testTypes[0].testExpiryDate", insertableTestExpiryDate, "", "REPLACE");
//        JsonPathAlteration alterationInsertCreatedAt = new JsonPathAlteration("$.testTypes[0].createdAt", insertableCreatedAt, "", "REPLACE");
//        JsonPathAlteration alterationInsertTestTypeEndTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeEndTimestamp", insertableTestTypeEndTimestamp, "", "REPLACE");
//        JsonPathAlteration alterationInsertTestEndTimestamp = new JsonPathAlteration("$.testEndTimestamp", insertableTestEndTimestamp, "", "REPLACE");
//        JsonPathAlteration alterationInsertTestCode = new JsonPathAlteration("$.testTypes[0].testCode", insertedTestCode, "", "REPLACE");
//
//        // Collate the list of alterations for the inserted record.
//        List<JsonPathAlteration> insertAlterations = new ArrayList<>(Arrays.asList(
//                alterationInsertVin,
//                alterationInsertTestResultId,
//                alterationInsertTestStartTimestamp,
//                alterationInsertTestTypeStartTimestamp,
//                alterationInsertLastUpdatedAt,
//                alterationInsertTestAnniversaryDate,
//                alterationInsertTestExpiryDate,
//                alterationInsertCreatedAt,
//                alterationInsertTestTypeEndTimestamp,
//                alterationInsertTestCode,
//                alterationInsertSysNo,
//                alterationInsertTestEndTimestamp
//        ));
//
//        if (isAnnualWithCertificate) {
//            insertAlterations.add(new JsonPathAlteration("$.testTypes[0]", "Annual With Certificate", "testTypeClassification", "ADD_FIELD"));
//        }
//
//        // Insert the altered record
//        String alteredJson = GenericData.applyJsonAlterations(insertedTestResultRecord, insertAlterations);
//        testResultsSteps.insertRecordInDynamo(alteredJson, "test-results");
//
//        // Create submitted
//        DateTime submittedTestStartTimestamp = currentTimestamp.minusMinutes(15);
//        DateTime submittedTestTypeStartTimestamp = currentTimestamp.plusMinutes(10);
//        DateTime submittedTypeEndTimestamp = currentTimestamp.plusMinutes(5);
//        DateTime submittedEndTimestamp = currentTimestamp;
//
//        String testStartTimestamp = submittedTestStartTimestamp.toInstant().toString();
//        String testTypeStartTimestamp = submittedTestTypeStartTimestamp.toInstant().toString();
//        String testTypeEndTimestamp = submittedTypeEndTimestamp.toInstant().toString();
//        String testEndTimestamp = submittedEndTimestamp.toInstant().toString();
//
//        String testExpectedDate = insertedTestExpiryDate.plusYears(1).toInstant().toString();
//
//
//        // Create alteration to add one more tech record to in the request body
//        JsonPathAlteration alterationTestStartTimestamp = new JsonPathAlteration("$.testStartTimestamp", testStartTimestamp, "", "REPLACE");
//        JsonPathAlteration alterationTestEndTimestamp = new JsonPathAlteration("$.testEndTimestamp", testEndTimestamp, "", "REPLACE");
//        JsonPathAlteration alterationTestTypeStartTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeStartTimestamp", testTypeStartTimestamp, "", "REPLACE");
//        JsonPathAlteration alterationTestTypeEndTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeEndTimestamp", testTypeEndTimestamp, "", "REPLACE");
//        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
//        JsonPathAlteration alterationSysNo = new JsonPathAlteration("$.systemNumber", randomSystemNo, "", "REPLACE");
//        JsonPathAlteration alterationTestResultId = new JsonPathAlteration("$.testResultId", randomTestResultId, "", "REPLACE");
//        JsonPathAlteration alterationTestName = new JsonPathAlteration("$.testTypes[0].name", name, "", "REPLACE");
//        JsonPathAlteration alterationTestTypeId = new JsonPathAlteration("$.testTypes[0].testTypeId", testTypeId, "", "REPLACE");
//        JsonPathAlteration alterationTestTypeName = new JsonPathAlteration("$.testTypes[0].testTypeName", testTypeName, "", "REPLACE");
//        JsonPathAlteration alterationTestResult = new JsonPathAlteration("$.testTypes[0].testResult", testResult, "", "REPLACE");
//        JsonPathAlteration alterationVehicleSize = new JsonPathAlteration("$.vehicleSize", vehicleSize, "", "REPLACE");
//        JsonPathAlteration alterationVehicleConfiguration = new JsonPathAlteration("$.vehicleConfiguration", vehicleConfiguration, "", "REPLACE");
//
//        // Collate the list of alterations.
//        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(
//                alterationTestStartTimestamp,
//                alterationTestEndTimestamp,
//                alterationTestTypeStartTimestamp,
//                alterationTestTypeEndTimestamp,
//                alterationVin,
//                alterationTestResultId,
//                alterationTestName,
//                alterationTestTypeId,
//                alterationTestTypeName,
//                alterationVehicleSize,
//                alterationVehicleConfiguration,
//                alterationSysNo,
//                alterationTestResult
//        ));
//
//        // Post the results, together with any alterations, and verify that they are accepted.
//        testResultsSteps.postVehicleTestResultsWithAlterations(postTestResultRecord, alterations);
//
//        testResultsSteps.statusCodeShouldBe(201);
//        testResultsSteps.validateData("Test records created");
//
//        // Retrieve the created record, and verify that the fields are present.
//        testResultsSteps.getTestResultsBetweenDate(randomSystemNo, submittedTestStartTimestamp.minusMinutes(10).toInstant().toString(), submittedEndTimestamp.plusMinutes(10).toInstant().toString());
//        testResultsSteps.statusCodeShouldBe(200);
//
//        // Verify testCode field has the expected value
//        testResultsSteps.valueForFieldInPathShouldBe("[0].testTypes[0].testCode", testCode);
//
//        // Verify testExpiryDate field has the expected value
//        testResultsSteps.valueForFieldInPathShouldStartWith("[0].testTypes[0].testExpiryDate", testExpectedDate.substring(0, 10));
//
////        // Verify testAnniversaryDate field has the expected value
////        testResultsSteps.valueForFieldInPathShouldStartWith("[0].testTypes[0].testAnniversaryDate", testExpectedDate.substring(0,10));
//
//    }
//
//    @WithTag("expiry_dates")
//    @Title("CVSB-8684 - TC1 - AC1 - PSV Annual test WITH PREVIOUS Expiry Date - Previous testExpiryDate = Today + 2 months + 1 day")
//    @Test
//    public void testResultsFirstTestExpiryPsvTodayPlusTwoMonthsPlusOneDay() {
//
//        // Read the base INSERT test result JSON.
//        String insertedTestResultRecord = GenericData.readJsonValueFromFile("test-results_insert_expiry_date_psv_8684.json", "$");
//        // Read the base POST test result JSON.
//        String postTestResultRecord = GenericData.readJsonValueFromFile("test-results_post_expiry_date_psv_8684.json", "$");
//
//        DateTime currentTimestamp = DateTime.now().withZone(DateTimeZone.UTC);
//
//        //
//        String randomVin = GenericData.generateRandomVin();
//        String randomTestResultId = UUID.randomUUID().toString();
//        String randomSystemNo = GenericData.generateRandomSystemNumber();
//
//        // Create inserted record.
//        DateTime insertedTestStartTimestamp = currentTimestamp.minusYears(1).plusMonths(2).plusDays(2).minusMinutes(15);
//        DateTime insertedTestTypeStartTimestamp = currentTimestamp.minusYears(1).plusMonths(2).plusDays(2).minusMinutes(10);
//        DateTime insertedLastUpdatedAt = currentTimestamp.minusYears(1).plusMonths(2).plusDays(2).minusMinutes(5);
//
//        DateTime insertedTestAnniversaryDate = currentTimestamp.plusDays(1);
//        DateTime insertedTestExpiryDate = currentTimestamp.plusMonths(2).plusDays(1);
//
//        DateTime insertedCreatedAt = currentTimestamp.minusYears(1).plusMonths(2).plusDays(2).minusMinutes(5);
//        DateTime insertedTestTypeEndTimestamp = currentTimestamp.minusYears(1).plusMonths(2).plusDays(2).minusMinutes(5);
//        DateTime insertedTestEndTimestamp = currentTimestamp.minusYears(1).plusMonths(2).plusDays(2);
//
//        String insertableTestStartTimestamp = insertedTestStartTimestamp.toInstant().toString();
//        String insertableTestTypeStartTimestamp = insertedTestTypeStartTimestamp.toInstant().toString();
//        String insertableLastUpdatedAt = insertedLastUpdatedAt.toInstant().toString();
//        String insertableTestAnniversaryDate = insertedTestAnniversaryDate.toInstant().toString();
//        String insertableTestExpiryDate = insertedTestExpiryDate.toInstant().toString();
//        String insertableCreatedAt = insertedCreatedAt.toInstant().toString();
//        String insertableTestTypeEndTimestamp = insertedTestTypeEndTimestamp.toInstant().toString();
//        String insertableTestEndTimestamp = insertedTestEndTimestamp.toInstant().toString();
//
//        // Create alteration to add one more tech record to in the inserted data
//        JsonPathAlteration alterationInsertVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
//        JsonPathAlteration alterationInsertSysNo = new JsonPathAlteration("$.systemNumber", randomSystemNo, "", "REPLACE");
//        JsonPathAlteration alterationInsertTestResultId = new JsonPathAlteration("$.testResultId", UUID.randomUUID().toString(), "", "REPLACE");
//        JsonPathAlteration alterationInsertTestStartTimestamp = new JsonPathAlteration("$.testStartTimestamp", insertableTestStartTimestamp, "", "REPLACE");
//        JsonPathAlteration alterationInsertTestTypeStartTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeStartTimestamp", insertableTestTypeStartTimestamp, "", "REPLACE");
//        JsonPathAlteration alterationInsertLastUpdatedAt = new JsonPathAlteration("$.testTypes[0].lastUpdatedAt", insertableLastUpdatedAt, "", "REPLACE");
//        JsonPathAlteration alterationInsertTestAnniversaryDate = new JsonPathAlteration("$.testTypes[0].testAnniversaryDate", insertableTestAnniversaryDate, "", "REPLACE");
//        JsonPathAlteration alterationInsertTestExpiryDate = new JsonPathAlteration("$.testTypes[0].testExpiryDate", insertableTestExpiryDate, "", "REPLACE");
//        JsonPathAlteration alterationInsertCreatedAt = new JsonPathAlteration("$.testTypes[0].createdAt", insertableCreatedAt, "", "REPLACE");
//        JsonPathAlteration alterationInsertTestTypeEndTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeEndTimestamp", insertableTestTypeEndTimestamp, "", "REPLACE");
//        JsonPathAlteration alterationInsertTestEndTimestamp = new JsonPathAlteration("$.testEndTimestamp", insertableTestEndTimestamp, "", "REPLACE");
//        JsonPathAlteration alterationInsertTestCode = new JsonPathAlteration("$.testTypes[0].testCode", insertedTestCode, "", "REPLACE");
//
//        // Collate the list of alterations for the inserted record.
//        List<JsonPathAlteration> insertAlterations = new ArrayList<>(Arrays.asList(
//                alterationInsertVin,
//                alterationInsertTestResultId,
//                alterationInsertTestStartTimestamp,
//                alterationInsertTestTypeStartTimestamp,
//                alterationInsertLastUpdatedAt,
//                alterationInsertTestAnniversaryDate,
//                alterationInsertTestExpiryDate,
//                alterationInsertCreatedAt,
//                alterationInsertTestTypeEndTimestamp,
//                alterationInsertTestCode,
//                alterationInsertSysNo,
//                alterationInsertTestEndTimestamp
//        ));
//
//        if (isAnnualWithCertificate) {
//            insertAlterations.add(new JsonPathAlteration("$.testTypes[0]", "Annual With Certificate", "testTypeClassification", "ADD_FIELD"));
//        }
//
//        // Insert the altered record
//        String alteredJson = GenericData.applyJsonAlterations(insertedTestResultRecord, insertAlterations);
//        testResultsSteps.insertRecordInDynamo(alteredJson, "test-results");
//
//        // Create submitted
//        DateTime submittedTestStartTimestamp = currentTimestamp.minusMinutes(15);
//        DateTime submittedTestTypeStartTimestamp = currentTimestamp.plusMinutes(10);
//        DateTime submittedTypeEndTimestamp = currentTimestamp.plusMinutes(5);
//        DateTime submittedEndTimestamp = currentTimestamp;
//
//        String testStartTimestamp = submittedTestStartTimestamp.toInstant().toString();
//        String testTypeStartTimestamp = submittedTestTypeStartTimestamp.toInstant().toString();
//        String testTypeEndTimestamp = submittedTypeEndTimestamp.toInstant().toString();
//        String testEndTimestamp = submittedEndTimestamp.toInstant().toString();
//
//        String testExpectedDate = currentTimestamp.plusYears(1).minusDays(1).toInstant().toString();
//
//
//        // Create alteration to add one more tech record to in the request body
//        JsonPathAlteration alterationTestStartTimestamp = new JsonPathAlteration("$.testStartTimestamp", testStartTimestamp, "", "REPLACE");
//        JsonPathAlteration alterationTestEndTimestamp = new JsonPathAlteration("$.testEndTimestamp", testEndTimestamp, "", "REPLACE");
//        JsonPathAlteration alterationTestTypeStartTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeStartTimestamp", testTypeStartTimestamp, "", "REPLACE");
//        JsonPathAlteration alterationTestTypeEndTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeEndTimestamp", testTypeEndTimestamp, "", "REPLACE");
//        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
//        JsonPathAlteration alterationSysNo = new JsonPathAlteration("$.systemNumber", randomSystemNo, "", "REPLACE");
//        JsonPathAlteration alterationTestResultId = new JsonPathAlteration("$.testResultId", randomTestResultId, "", "REPLACE");
//        JsonPathAlteration alterationTestName = new JsonPathAlteration("$.testTypes[0].name", name, "", "REPLACE");
//        JsonPathAlteration alterationTestTypeId = new JsonPathAlteration("$.testTypes[0].testTypeId", testTypeId, "", "REPLACE");
//        JsonPathAlteration alterationTestTypeName = new JsonPathAlteration("$.testTypes[0].testTypeName", testTypeName, "", "REPLACE");
//        JsonPathAlteration alterationTestResult = new JsonPathAlteration("$.testTypes[0].testResult", testResult, "", "REPLACE");
//        JsonPathAlteration alterationVehicleSize = new JsonPathAlteration("$.vehicleSize", vehicleSize, "", "REPLACE");
//        JsonPathAlteration alterationVehicleConfiguration = new JsonPathAlteration("$.vehicleConfiguration", vehicleConfiguration, "", "REPLACE");
//
//        // Collate the list of alterations.
//        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(
//                alterationTestStartTimestamp,
//                alterationTestEndTimestamp,
//                alterationTestTypeStartTimestamp,
//                alterationTestTypeEndTimestamp,
//                alterationVin,
//                alterationTestResultId,
//                alterationTestName,
//                alterationTestTypeId,
//                alterationTestTypeName,
//                alterationVehicleSize,
//                alterationVehicleConfiguration,
//                alterationSysNo,
//                alterationTestResult
//        ));
//
//        // Post the results, together with any alterations, and verify that they are accepted.
//        testResultsSteps.postVehicleTestResultsWithAlterations(postTestResultRecord, alterations);
//
//        testResultsSteps.statusCodeShouldBe(201);
//        testResultsSteps.validateData("Test records created");
//
//        // Retrieve the created record, and verify that the fields are present.
//        testResultsSteps.getTestResultsBetweenDate(randomSystemNo, submittedTestStartTimestamp.minusMinutes(10).toInstant().toString(), submittedEndTimestamp.plusMinutes(10).toInstant().toString());
//        testResultsSteps.statusCodeShouldBe(200);
//
//        // Verify testCode field has the expected value
//        testResultsSteps.valueForFieldInPathShouldBe("[0].testTypes[0].testCode", testCode);
//
//        // Verify testExpiryDate field has the expected value
//        testResultsSteps.valueForFieldInPathShouldStartWith("[0].testTypes[0].testExpiryDate", testExpectedDate.substring(0, 10));
//
////        // Verify testAnniversaryDate field has the expected value
////        testResultsSteps.valueForFieldInPathShouldStartWith("[0].testTypes[0].testAnniversaryDate", testExpectedDate.substring(0,10));
//
//    }
//
//    @WithTag("expiry_dates")
//    @Title("CVSB-8684 - TC1 - AC1 - PSV Annual test WITH PREVIOUS Expiry Date - Previous testExpiryDate = Today + 2 months - 1 day")
//    @Test
//    public void testResultsFirstTestExpiryPsvTodayPlusTwoMonthsMinusOneDay() {
//
//        // Read the base INSERT test result JSON.
//        String insertedTestResultRecord = GenericData.readJsonValueFromFile("test-results_insert_expiry_date_psv_8684.json", "$");
//        // Read the base POST test result JSON.
//        String postTestResultRecord = GenericData.readJsonValueFromFile("test-results_post_expiry_date_psv_8684.json", "$");
//
//        DateTime currentTimestamp = DateTime.now().withZone(DateTimeZone.UTC);
//
//        //
//        String randomVin = GenericData.generateRandomVin();
//        String randomTestResultId = UUID.randomUUID().toString();
//        String randomSystemNo = GenericData.generateRandomSystemNumber();
//
//        // Create inserted record.
//        DateTime insertedTestStartTimestamp = currentTimestamp.minusYears(1).plusMonths(2).minusMinutes(15);
//        DateTime insertedTestTypeStartTimestamp = currentTimestamp.minusYears(1).plusMonths(2).minusMinutes(10);
//        DateTime insertedLastUpdatedAt = currentTimestamp.minusYears(1).plusMonths(2).minusMinutes(5);
//
//        DateTime insertedTestAnniversaryDate = currentTimestamp.minusDays(1);
//        DateTime insertedTestExpiryDate = currentTimestamp.plusMonths(2).minusDays(1);
//
//        DateTime insertedCreatedAt = currentTimestamp.minusYears(1).plusMonths(2).minusMinutes(5);
//        DateTime insertedTestTypeEndTimestamp = currentTimestamp.minusYears(1).plusMonths(2).minusMinutes(5);
//        DateTime insertedTestEndTimestamp = currentTimestamp.minusYears(1).plusMonths(2);
//
//        String insertableTestStartTimestamp = insertedTestStartTimestamp.toInstant().toString();
//        String insertableTestTypeStartTimestamp = insertedTestTypeStartTimestamp.toInstant().toString();
//        String insertableLastUpdatedAt = insertedLastUpdatedAt.toInstant().toString();
//        String insertableTestAnniversaryDate = insertedTestAnniversaryDate.toInstant().toString();
//        String insertableTestExpiryDate = insertedTestExpiryDate.toInstant().toString();
//        String insertableCreatedAt = insertedCreatedAt.toInstant().toString();
//        String insertableTestTypeEndTimestamp = insertedTestTypeEndTimestamp.toInstant().toString();
//        String insertableTestEndTimestamp = insertedTestEndTimestamp.toInstant().toString();
//
//        // Create alteration to add one more tech record to in the inserted data
//        JsonPathAlteration alterationInsertVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
//        JsonPathAlteration alterationInsertSysNo = new JsonPathAlteration("$.systemNumber", randomSystemNo, "", "REPLACE");
//        JsonPathAlteration alterationInsertTestResultId = new JsonPathAlteration("$.testResultId", UUID.randomUUID().toString(), "", "REPLACE");
//        JsonPathAlteration alterationInsertTestStartTimestamp = new JsonPathAlteration("$.testStartTimestamp", insertableTestStartTimestamp, "", "REPLACE");
//        JsonPathAlteration alterationInsertTestTypeStartTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeStartTimestamp", insertableTestTypeStartTimestamp, "", "REPLACE");
//        JsonPathAlteration alterationInsertLastUpdatedAt = new JsonPathAlteration("$.testTypes[0].lastUpdatedAt", insertableLastUpdatedAt, "", "REPLACE");
//        JsonPathAlteration alterationInsertTestAnniversaryDate = new JsonPathAlteration("$.testTypes[0].testAnniversaryDate", insertableTestAnniversaryDate, "", "REPLACE");
//        JsonPathAlteration alterationInsertTestExpiryDate = new JsonPathAlteration("$.testTypes[0].testExpiryDate", insertableTestExpiryDate, "", "REPLACE");
//        JsonPathAlteration alterationInsertCreatedAt = new JsonPathAlteration("$.testTypes[0].createdAt", insertableCreatedAt, "", "REPLACE");
//        JsonPathAlteration alterationInsertTestTypeEndTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeEndTimestamp", insertableTestTypeEndTimestamp, "", "REPLACE");
//        JsonPathAlteration alterationInsertTestEndTimestamp = new JsonPathAlteration("$.testEndTimestamp", insertableTestEndTimestamp, "", "REPLACE");
//        JsonPathAlteration alterationInsertTestCode = new JsonPathAlteration("$.testTypes[0].testCode", insertedTestCode, "", "REPLACE");
//
//        // Collate the list of alterations for the inserted record.
//        List<JsonPathAlteration> insertAlterations = new ArrayList<>(Arrays.asList(
//                alterationInsertVin,
//                alterationInsertTestResultId,
//                alterationInsertTestStartTimestamp,
//                alterationInsertTestTypeStartTimestamp,
//                alterationInsertLastUpdatedAt,
//                alterationInsertTestAnniversaryDate,
//                alterationInsertTestExpiryDate,
//                alterationInsertCreatedAt,
//                alterationInsertTestTypeEndTimestamp,
//                alterationInsertTestCode,
//                alterationInsertSysNo,
//                alterationInsertTestEndTimestamp
//        ));
//
//        if (isAnnualWithCertificate) {
//            insertAlterations.add(new JsonPathAlteration("$.testTypes[0]", "Annual With Certificate", "testTypeClassification", "ADD_FIELD"));
//        }
//
//        // Insert the altered record
//        String alteredJson = GenericData.applyJsonAlterations(insertedTestResultRecord, insertAlterations);
//        testResultsSteps.insertRecordInDynamo(alteredJson, "test-results");
//
//        // Create submitted
//        DateTime submittedTestStartTimestamp = currentTimestamp.minusMinutes(15);
//        DateTime submittedTestTypeStartTimestamp = currentTimestamp.plusMinutes(10);
//        DateTime submittedTypeEndTimestamp = currentTimestamp.plusMinutes(5);
//        DateTime submittedEndTimestamp = currentTimestamp;
//
//        String testStartTimestamp = submittedTestStartTimestamp.toInstant().toString();
//        String testTypeStartTimestamp = submittedTestTypeStartTimestamp.toInstant().toString();
//        String testTypeEndTimestamp = submittedTypeEndTimestamp.toInstant().toString();
//        String testEndTimestamp = submittedEndTimestamp.toInstant().toString();
//
//        String testExpectedDate = insertedTestExpiryDate.plusYears(1).toInstant().toString();
//
//
//        // Create alteration to add one more tech record to in the request body
//        JsonPathAlteration alterationTestStartTimestamp = new JsonPathAlteration("$.testStartTimestamp", testStartTimestamp, "", "REPLACE");
//        JsonPathAlteration alterationTestEndTimestamp = new JsonPathAlteration("$.testEndTimestamp", testEndTimestamp, "", "REPLACE");
//        JsonPathAlteration alterationTestTypeStartTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeStartTimestamp", testTypeStartTimestamp, "", "REPLACE");
//        JsonPathAlteration alterationTestTypeEndTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeEndTimestamp", testTypeEndTimestamp, "", "REPLACE");
//        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
//        JsonPathAlteration alterationSysNo = new JsonPathAlteration("$.systemNumber", randomSystemNo, "", "REPLACE");
//        JsonPathAlteration alterationTestResultId = new JsonPathAlteration("$.testResultId", randomTestResultId, "", "REPLACE");
//        JsonPathAlteration alterationTestName = new JsonPathAlteration("$.testTypes[0].name", name, "", "REPLACE");
//        JsonPathAlteration alterationTestTypeId = new JsonPathAlteration("$.testTypes[0].testTypeId", testTypeId, "", "REPLACE");
//        JsonPathAlteration alterationTestTypeName = new JsonPathAlteration("$.testTypes[0].testTypeName", testTypeName, "", "REPLACE");
//        JsonPathAlteration alterationTestResult = new JsonPathAlteration("$.testTypes[0].testResult", testResult, "", "REPLACE");
//        JsonPathAlteration alterationVehicleSize = new JsonPathAlteration("$.vehicleSize", vehicleSize, "", "REPLACE");
//        JsonPathAlteration alterationVehicleConfiguration = new JsonPathAlteration("$.vehicleConfiguration", vehicleConfiguration, "", "REPLACE");
//
//        // Collate the list of alterations.
//        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(
//                alterationTestStartTimestamp,
//                alterationTestEndTimestamp,
//                alterationTestTypeStartTimestamp,
//                alterationTestTypeEndTimestamp,
//                alterationVin,
//                alterationTestResultId,
//                alterationTestName,
//                alterationTestTypeId,
//                alterationTestTypeName,
//                alterationVehicleSize,
//                alterationVehicleConfiguration,
//                alterationSysNo,
//                alterationTestResult
//        ));
//
//        // Post the results, together with any alterations, and verify that they are accepted.
//        testResultsSteps.postVehicleTestResultsWithAlterations(postTestResultRecord, alterations);
//
//        testResultsSteps.statusCodeShouldBe(201);
//        testResultsSteps.validateData("Test records created");
//
//        // Retrieve the created record, and verify that the fields are present.
//        testResultsSteps.getTestResultsBetweenDate(randomSystemNo, submittedTestStartTimestamp.minusMinutes(10).toInstant().toString(), submittedEndTimestamp.plusMinutes(10).toInstant().toString());
//        testResultsSteps.statusCodeShouldBe(200);
//
//        // Verify testCode field has the expected value
//        testResultsSteps.valueForFieldInPathShouldBe("[0].testTypes[0].testCode", testCode);
//
//        // Verify testExpiryDate field has the expected value
//        testResultsSteps.valueForFieldInPathShouldStartWith("[0].testTypes[0].testExpiryDate", testExpectedDate.substring(0, 10));
//
////        // Verify testAnniversaryDate field has the expected value
////        testResultsSteps.valueForFieldInPathShouldStartWith("[0].testTypes[0].testAnniversaryDate", testExpectedDate.substring(0,10));
//
//    }
}
