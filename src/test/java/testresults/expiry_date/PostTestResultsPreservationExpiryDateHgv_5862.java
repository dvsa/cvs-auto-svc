package testresults.expiry_date;

import data.GenericData;
import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.junit.annotations.UseTestDataFrom;
import org.apache.commons.lang3.RandomStringUtils;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
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
@UseTestDataFrom(value="loader/testdata/test_data_expiry_date_annual_hgv_5862.csv")
public class PostTestResultsPreservationExpiryDateHgv_5862 {

    @Steps
    TestResultsSteps testResultsSteps;

    private String name;
    private String testTypeName;
    private String testTypeId;
    private int noOfAxles;
    private String testResult;
    private String insertedTestCode;
    private String testCode;
    private boolean isAnnualWithCertificate;

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

    public void setInsertedTestCode(String insertedTestCode) {
        this.insertedTestCode = insertedTestCode;
    }

    public void setTestCode(String testCode) {
        this.testCode = testCode;
    }

    public void setAnnualWithCertificate(boolean annualWithCertificate) {
        isAnnualWithCertificate = annualWithCertificate;
    }

    @WithTag("expiry_dates")
    @Title("CVSB-5862 - Expiry and anniversary dates for HGV & TRL certificates - HGV - Today is more than 2 months before previous expiryDate (Early for test)")
    @Test
    public void testResultsFirstTestExpiryHgvTodayMoreThanTwoMonthsBeforePreviousExpiry() {

        // Read the base INSERT test result JSON.
        String insertedTestResultRecord = GenericData.readJsonValueFromFile("test-results_insert_expiry_date_hgv_5862.json", "$");
        // Read the base POST test result JSON.
        String postTestResultRecord = GenericData.readJsonValueFromFile("test-results_post_expiry_date_hgv_5862.json", "$");

        DateTime currentTimestamp = DateTime.now().withZone(DateTimeZone.UTC);

        // Change the vin testResultsId and SystemNumber to get a unique record
        String randomVin = GenericData.generateRandomVin();
        String randomTestResultId = UUID.randomUUID().toString();
        String randomSystemNo = GenericData.generateRandomSystemNumber();

        // Create the inserted record.
        DateTime insertedTestExpiryDate = currentTimestamp.plusMonths(3).dayOfMonth().withMaximumValue();

        DateTime insertedTestStartTimestamp = insertedTestExpiryDate.minusYears(1).minusMinutes(15);
        DateTime insertedTestTypeStartTimestamp = insertedTestExpiryDate.minusYears(1).minusMinutes(10);
        DateTime insertedLastUpdatedAt = insertedTestExpiryDate.minusYears(1).minusMinutes(5);
        DateTime insertedCreatedAt = insertedTestExpiryDate.minusYears(1).minusMinutes(5);
        DateTime insertedTestTypeEndTimestamp = insertedTestExpiryDate.minusYears(1).minusMinutes(5);
        DateTime insertedTestEndTimestamp = insertedTestExpiryDate.minusYears(1);

        String insertableTestStartTimestamp = insertedTestStartTimestamp.toInstant().toString();
        String insertableTestTypeStartTimestamp = insertedTestTypeStartTimestamp.toInstant().toString();
        String insertableLastUpdatedAt = insertedLastUpdatedAt.toInstant().toString();
        String insertableTestExpiryDate = insertedTestExpiryDate.toInstant().toString();
        String insertableCreatedAt = insertedCreatedAt.toInstant().toString();
        String insertableTestTypeEndTimestamp = insertedTestTypeEndTimestamp.toInstant().toString();
        String insertableTestEndTimestamp = insertedTestEndTimestamp.toInstant().toString();

        // Create alteration to add one more tech record to in the inserted data
        JsonPathAlteration alterationInsertVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationInsertSysNo = new JsonPathAlteration("$.systemNumber", randomSystemNo, "", "REPLACE");
        JsonPathAlteration alterationInsertTestResultId = new JsonPathAlteration("$.testResultId", UUID.randomUUID().toString(), "", "REPLACE");
        JsonPathAlteration alterationInsertTestStartTimestamp = new JsonPathAlteration("$.testStartTimestamp", insertableTestStartTimestamp, "", "REPLACE");
        JsonPathAlteration alterationInsertTestTypeStartTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeStartTimestamp", insertableTestTypeStartTimestamp, "", "REPLACE");
        JsonPathAlteration alterationInsertLastUpdatedAt = new JsonPathAlteration("$.testTypes[0].lastUpdatedAt", insertableLastUpdatedAt, "", "REPLACE");
        JsonPathAlteration alterationInsertTestExpiryDate = new JsonPathAlteration("$.testTypes[0].testExpiryDate", insertableTestExpiryDate, "", "REPLACE");
        JsonPathAlteration alterationInsertCreatedAt = new JsonPathAlteration("$.testTypes[0].createdAt", insertableCreatedAt, "", "REPLACE");
        JsonPathAlteration alterationInsertTestTypeEndTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeEndTimestamp", insertableTestTypeEndTimestamp, "", "REPLACE");
        JsonPathAlteration alterationInsertTestEndTimestamp = new JsonPathAlteration("$.testEndTimestamp", insertableTestEndTimestamp, "", "REPLACE");
        JsonPathAlteration alterationInsertTestCode = new JsonPathAlteration("$.testTypes[0].testCode", insertedTestCode, "", "REPLACE");

        // Collate the list of alterations for the inserted record.
        List<JsonPathAlteration> insertAlterations = new ArrayList<>(Arrays.asList(
                alterationInsertVin,
                alterationInsertTestResultId,
                alterationInsertTestStartTimestamp,
                alterationInsertTestTypeStartTimestamp,
                alterationInsertLastUpdatedAt,
                alterationInsertTestExpiryDate,
                alterationInsertCreatedAt,
                alterationInsertTestTypeEndTimestamp,
                alterationInsertTestCode,
                alterationInsertSysNo,
                alterationInsertTestEndTimestamp
        ));

        if (isAnnualWithCertificate) {
            insertAlterations.add(new JsonPathAlteration("$.testTypes[0]", "Annual With Certificate", "testTypeClassification", "ADD_FIELD"));
        }

        // Insert the altered record
        String alteredJson = GenericData.applyJsonAlterations(insertedTestResultRecord, insertAlterations);
        testResultsSteps.insertRecordInDynamo(alteredJson, "test-results", "vin");

        // Create submitted
        DateTime submittedTestStartTimestamp = currentTimestamp.minusMinutes(15);
        DateTime submittedTestTypeStartTimestamp = currentTimestamp.minusMinutes(10);
        DateTime submittedTypeEndTimestamp = currentTimestamp.minusMinutes(5);
        DateTime submittedEndTimestamp = currentTimestamp;

        String testStartTimestamp = submittedTestStartTimestamp.toInstant().toString();
        String testTypeStartTimestamp = submittedTestTypeStartTimestamp.toInstant().toString();
        String testTypeEndTimestamp = submittedTypeEndTimestamp.toInstant().toString();
        String testEndTimestamp = submittedEndTimestamp.toInstant().toString();

        // Expected recalculated testExpiryDate
        String testExpiryDate = submittedEndTimestamp.dayOfMonth().withMaximumValue().plusYears(1).dayOfMonth().withMaximumValue().toInstant().toString();

        System.out.println("\n******************************************************");
        System.out.println("Inserted testCode: " + insertedTestCode);
        System.out.println("Inserted previous ExpiryDate: " + insertedTestExpiryDate.toInstant().toString().substring(0,10));
        System.out.println("POST-ed testCode: " + testCode);
        System.out.println("Today: " + currentTimestamp.toInstant().toString().substring(0,10));
        System.out.println("expected ExpiryDate: " + testExpiryDate.substring(0,10));
        System.out.println("******************************************************\n");

        // Create alteration to add one more tech record to in the request body
        JsonPathAlteration alterationTestStartTimestamp = new JsonPathAlteration("$.testStartTimestamp", testStartTimestamp, "", "REPLACE");
        JsonPathAlteration alterationTestEndTimestamp = new JsonPathAlteration("$.testEndTimestamp", testEndTimestamp, "", "REPLACE");
        JsonPathAlteration alterationTestTypeStartTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeStartTimestamp", testTypeStartTimestamp, "", "REPLACE");
        JsonPathAlteration alterationTestTypeEndTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeEndTimestamp", testTypeEndTimestamp, "", "REPLACE");
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationSysNo = new JsonPathAlteration("$.systemNumber", randomSystemNo, "", "REPLACE");
        JsonPathAlteration alterationTestResultId = new JsonPathAlteration("$.testResultId", randomTestResultId, "", "REPLACE");
        JsonPathAlteration alterationTestName = new JsonPathAlteration("$.testTypes[0].name", name, "", "REPLACE");
        JsonPathAlteration alterationTestTypeId = new JsonPathAlteration("$.testTypes[0].testTypeId", testTypeId, "", "REPLACE");
        JsonPathAlteration alterationTestTypeName = new JsonPathAlteration("$.testTypes[0].testTypeName", testTypeName, "", "REPLACE");
        JsonPathAlteration alterationTestResult = new JsonPathAlteration("$.testTypes[0].testResult", testResult, "", "REPLACE");
        JsonPathAlteration alterationNoOfAxles = new JsonPathAlteration("$.noOfAxles", noOfAxles, "", "REPLACE");

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
                alterationNoOfAxles,
                alterationSysNo,
                alterationTestResult
        ));


        // Post the results, together with any alterations, and verify that they are accepted.
        testResultsSteps.postVehicleTestResultsWithAlterations(postTestResultRecord, alterations);

        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");

        // Retrieve the created record, and verify that the fields are present.
        testResultsSteps.getTestResultsBetweenDate(randomSystemNo, submittedTestStartTimestamp.minusMinutes(10).toInstant().toString(), submittedEndTimestamp.plusMinutes(10).toInstant().toString());
        testResultsSteps.statusCodeShouldBe(200);

        // Verify testCode field has the expected value
        testResultsSteps.valueForFieldInPathShouldBe("[0].testTypes[0].testCode", testCode);

        // Verify testExpiryDate field has the expected value
        testResultsSteps.valueForFieldInPathShouldStartWith("[0].testTypes[0].testExpiryDate", testExpiryDate.substring(0, 10));

    }

    @WithTag("expiry_dates")
    @Title("CVSB-5862 - Expiry and anniversary dates for HGV & TRL certificates - HGV - Today is within one month before previous expiryDate")
    @Test
    public void testResultsFirstTestExpiryHgvTodayMoreThanOneMonthBeforePreviousExpiry() {

        // Read the base INSERT test result JSON.
        String insertedTestResultRecord = GenericData.readJsonValueFromFile("test-results_insert_expiry_date_hgv_5862.json", "$");
        // Read the base POST test result JSON.
        String postTestResultRecord = GenericData.readJsonValueFromFile("test-results_post_expiry_date_hgv_5862.json", "$");

        DateTime currentTimestamp = DateTime.now().withZone(DateTimeZone.UTC);

        // Change the vin testResultsId and SystemNumber to get a unique record
        String randomVin = GenericData.generateRandomVin();
        String randomTestResultId = UUID.randomUUID().toString();
        String randomSystemNo = GenericData.generateRandomSystemNumber();

        // Create the inserted record.
        DateTime insertedTestExpiryDate = currentTimestamp.plusMonths(1).dayOfMonth().withMaximumValue();

        DateTime insertedTestStartTimestamp = insertedTestExpiryDate.minusYears(1).minusMinutes(15);
        DateTime insertedTestTypeStartTimestamp = insertedTestExpiryDate.minusYears(1).minusMinutes(10);
        DateTime insertedLastUpdatedAt = insertedTestExpiryDate.minusYears(1).minusMinutes(5);
        DateTime insertedCreatedAt = insertedTestExpiryDate.minusYears(1).minusMinutes(5);
        DateTime insertedTestTypeEndTimestamp = insertedTestExpiryDate.minusYears(1).minusMinutes(5);
        DateTime insertedTestEndTimestamp = insertedTestExpiryDate.minusYears(1);

        String insertableTestStartTimestamp = insertedTestStartTimestamp.toInstant().toString();
        String insertableTestTypeStartTimestamp = insertedTestTypeStartTimestamp.toInstant().toString();
        String insertableLastUpdatedAt = insertedLastUpdatedAt.toInstant().toString();
        String insertableTestExpiryDate = insertedTestExpiryDate.toInstant().toString();
        String insertableCreatedAt = insertedCreatedAt.toInstant().toString();
        String insertableTestTypeEndTimestamp = insertedTestTypeEndTimestamp.toInstant().toString();
        String insertableTestEndTimestamp = insertedTestEndTimestamp.toInstant().toString();

        // Create alteration to add one more tech record to in the inserted data
        JsonPathAlteration alterationInsertVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationInsertSysNo = new JsonPathAlteration("$.systemNumber", randomSystemNo, "", "REPLACE");
        JsonPathAlteration alterationInsertTestResultId = new JsonPathAlteration("$.testResultId", UUID.randomUUID().toString(), "", "REPLACE");
        JsonPathAlteration alterationInsertTestStartTimestamp = new JsonPathAlteration("$.testStartTimestamp", insertableTestStartTimestamp, "", "REPLACE");
        JsonPathAlteration alterationInsertTestTypeStartTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeStartTimestamp", insertableTestTypeStartTimestamp, "", "REPLACE");
        JsonPathAlteration alterationInsertLastUpdatedAt = new JsonPathAlteration("$.testTypes[0].lastUpdatedAt", insertableLastUpdatedAt, "", "REPLACE");
        JsonPathAlteration alterationInsertTestExpiryDate = new JsonPathAlteration("$.testTypes[0].testExpiryDate", insertableTestExpiryDate, "", "REPLACE");
        JsonPathAlteration alterationInsertCreatedAt = new JsonPathAlteration("$.testTypes[0].createdAt", insertableCreatedAt, "", "REPLACE");
        JsonPathAlteration alterationInsertTestTypeEndTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeEndTimestamp", insertableTestTypeEndTimestamp, "", "REPLACE");
        JsonPathAlteration alterationInsertTestEndTimestamp = new JsonPathAlteration("$.testEndTimestamp", insertableTestEndTimestamp, "", "REPLACE");
        JsonPathAlteration alterationInsertTestCode = new JsonPathAlteration("$.testTypes[0].testCode", insertedTestCode, "", "REPLACE");

        // Collate the list of alterations for the inserted record.
        List<JsonPathAlteration> insertAlterations = new ArrayList<>(Arrays.asList(
                alterationInsertVin,
                alterationInsertTestResultId,
                alterationInsertTestStartTimestamp,
                alterationInsertTestTypeStartTimestamp,
                alterationInsertLastUpdatedAt,
                alterationInsertTestExpiryDate,
                alterationInsertCreatedAt,
                alterationInsertTestTypeEndTimestamp,
                alterationInsertTestCode,
                alterationInsertSysNo,
                alterationInsertTestEndTimestamp
        ));

        if (isAnnualWithCertificate) {
            insertAlterations.add(new JsonPathAlteration("$.testTypes[0]", "Annual With Certificate", "testTypeClassification", "ADD_FIELD"));
        }

        // Insert the altered record
        String alteredJson = GenericData.applyJsonAlterations(insertedTestResultRecord, insertAlterations);
        testResultsSteps.insertRecordInDynamo(alteredJson, "test-results", "vin");

        // Create submitted
        DateTime submittedTestStartTimestamp = currentTimestamp.minusMinutes(15);
        DateTime submittedTestTypeStartTimestamp = currentTimestamp.minusMinutes(10);
        DateTime submittedTypeEndTimestamp = currentTimestamp.minusMinutes(5);
        DateTime submittedEndTimestamp = currentTimestamp;

        String testStartTimestamp = submittedTestStartTimestamp.toInstant().toString();
        String testTypeStartTimestamp = submittedTestTypeStartTimestamp.toInstant().toString();
        String testTypeEndTimestamp = submittedTypeEndTimestamp.toInstant().toString();
        String testEndTimestamp = submittedEndTimestamp.toInstant().toString();

        // Expected recalculated testExpiryDate
        String testExpiryDate = insertedTestExpiryDate.plusYears(1).dayOfMonth().withMaximumValue().toInstant().toString();

        System.out.println("\n******************************************************");
        System.out.println("Inserted testCode: " + insertedTestCode);
        System.out.println("Inserted previous ExpiryDate: " + insertedTestExpiryDate.toInstant().toString().substring(0,10));
        System.out.println("POST-ed testCode: " + testCode);
        System.out.println("Today: " + currentTimestamp.toInstant().toString().substring(0,10));
        System.out.println("expected ExpiryDate: " + testExpiryDate.substring(0,10));
        System.out.println("******************************************************\n");

        // Create alteration to add one more tech record to in the request body
        JsonPathAlteration alterationTestStartTimestamp = new JsonPathAlteration("$.testStartTimestamp", testStartTimestamp, "", "REPLACE");
        JsonPathAlteration alterationTestEndTimestamp = new JsonPathAlteration("$.testEndTimestamp", testEndTimestamp, "", "REPLACE");
        JsonPathAlteration alterationTestTypeStartTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeStartTimestamp", testTypeStartTimestamp, "", "REPLACE");
        JsonPathAlteration alterationTestTypeEndTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeEndTimestamp", testTypeEndTimestamp, "", "REPLACE");
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationSysNo = new JsonPathAlteration("$.systemNumber", randomSystemNo, "", "REPLACE");
        JsonPathAlteration alterationTestResultId = new JsonPathAlteration("$.testResultId", randomTestResultId, "", "REPLACE");
        JsonPathAlteration alterationTestName = new JsonPathAlteration("$.testTypes[0].name", name, "", "REPLACE");
        JsonPathAlteration alterationTestTypeId = new JsonPathAlteration("$.testTypes[0].testTypeId", testTypeId, "", "REPLACE");
        JsonPathAlteration alterationTestTypeName = new JsonPathAlteration("$.testTypes[0].testTypeName", testTypeName, "", "REPLACE");
        JsonPathAlteration alterationTestResult = new JsonPathAlteration("$.testTypes[0].testResult", testResult, "", "REPLACE");
        JsonPathAlteration alterationNoOfAxles = new JsonPathAlteration("$.noOfAxles", noOfAxles, "", "REPLACE");

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
                alterationNoOfAxles,
                alterationSysNo,
                alterationTestResult
        ));


        // Post the results, together with any alterations, and verify that they are accepted.
        testResultsSteps.postVehicleTestResultsWithAlterations(postTestResultRecord, alterations);

        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");

        // Retrieve the created record, and verify that the fields are present.
        testResultsSteps.getTestResultsBetweenDate(randomSystemNo, submittedTestStartTimestamp.minusMinutes(10).toInstant().toString(), submittedEndTimestamp.plusMinutes(10).toInstant().toString());
        testResultsSteps.statusCodeShouldBe(200);

        // Verify testCode field has the expected value
        testResultsSteps.valueForFieldInPathShouldBe("[0].testTypes[0].testCode", testCode);

        // Verify testExpiryDate field has the expected value
        testResultsSteps.valueForFieldInPathShouldStartWith("[0].testTypes[0].testExpiryDate", testExpiryDate.substring(0, 10));

    }

    @WithTag("expiry_dates")
    @Title("CVSB-5862 - Expiry and anniversary dates for HGV & TRL certificates - HGV - Today is within the month of previous expiryDate")
    @Test
    public void testResultsFirstTestExpiryHgvTodayIsWithinTheMonthsOfPreviousExpiry() {

        // Read the base INSERT test result JSON.
        String insertedTestResultRecord = GenericData.readJsonValueFromFile("test-results_insert_expiry_date_hgv_5862.json", "$");
        // Read the base POST test result JSON.
        String postTestResultRecord = GenericData.readJsonValueFromFile("test-results_post_expiry_date_hgv_5862.json", "$");

        DateTime currentTimestamp = DateTime.now().withZone(DateTimeZone.UTC);

        // Change the vin testResultsId and SystemNumber to get a unique record
        String randomVin = GenericData.generateRandomVin();
        String randomTestResultId = UUID.randomUUID().toString();
        String randomSystemNo = GenericData.generateRandomSystemNumber();

        // Create the inserted record.
        DateTime insertedTestExpiryDate = currentTimestamp.dayOfMonth().withMaximumValue();

        DateTime insertedTestStartTimestamp = insertedTestExpiryDate.minusYears(1).minusMinutes(15);
        DateTime insertedTestTypeStartTimestamp = insertedTestExpiryDate.minusYears(1).minusMinutes(10);
        DateTime insertedLastUpdatedAt = insertedTestExpiryDate.minusYears(1).minusMinutes(5);
        DateTime insertedCreatedAt = insertedTestExpiryDate.minusYears(1).minusMinutes(5);
        DateTime insertedTestTypeEndTimestamp = insertedTestExpiryDate.minusYears(1).minusMinutes(5);
        DateTime insertedTestEndTimestamp = insertedTestExpiryDate.minusYears(1);

        String insertableTestStartTimestamp = insertedTestStartTimestamp.toInstant().toString();
        String insertableTestTypeStartTimestamp = insertedTestTypeStartTimestamp.toInstant().toString();
        String insertableLastUpdatedAt = insertedLastUpdatedAt.toInstant().toString();
        String insertableTestExpiryDate = insertedTestExpiryDate.toInstant().toString();
        String insertableCreatedAt = insertedCreatedAt.toInstant().toString();
        String insertableTestTypeEndTimestamp = insertedTestTypeEndTimestamp.toInstant().toString();
        String insertableTestEndTimestamp = insertedTestEndTimestamp.toInstant().toString();

        // Create alteration to add one more tech record to in the inserted data
        JsonPathAlteration alterationInsertVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationInsertSysNo = new JsonPathAlteration("$.systemNumber", randomSystemNo, "", "REPLACE");
        JsonPathAlteration alterationInsertTestResultId = new JsonPathAlteration("$.testResultId", UUID.randomUUID().toString(), "", "REPLACE");
        JsonPathAlteration alterationInsertTestStartTimestamp = new JsonPathAlteration("$.testStartTimestamp", insertableTestStartTimestamp, "", "REPLACE");
        JsonPathAlteration alterationInsertTestTypeStartTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeStartTimestamp", insertableTestTypeStartTimestamp, "", "REPLACE");
        JsonPathAlteration alterationInsertLastUpdatedAt = new JsonPathAlteration("$.testTypes[0].lastUpdatedAt", insertableLastUpdatedAt, "", "REPLACE");
        JsonPathAlteration alterationInsertTestExpiryDate = new JsonPathAlteration("$.testTypes[0].testExpiryDate", insertableTestExpiryDate, "", "REPLACE");
        JsonPathAlteration alterationInsertCreatedAt = new JsonPathAlteration("$.testTypes[0].createdAt", insertableCreatedAt, "", "REPLACE");
        JsonPathAlteration alterationInsertTestTypeEndTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeEndTimestamp", insertableTestTypeEndTimestamp, "", "REPLACE");
        JsonPathAlteration alterationInsertTestEndTimestamp = new JsonPathAlteration("$.testEndTimestamp", insertableTestEndTimestamp, "", "REPLACE");
        JsonPathAlteration alterationInsertTestCode = new JsonPathAlteration("$.testTypes[0].testCode", insertedTestCode, "", "REPLACE");

        // Collate the list of alterations for the inserted record.
        List<JsonPathAlteration> insertAlterations = new ArrayList<>(Arrays.asList(
                alterationInsertVin,
                alterationInsertTestResultId,
                alterationInsertTestStartTimestamp,
                alterationInsertTestTypeStartTimestamp,
                alterationInsertLastUpdatedAt,
                alterationInsertTestExpiryDate,
                alterationInsertCreatedAt,
                alterationInsertTestTypeEndTimestamp,
                alterationInsertTestCode,
                alterationInsertSysNo,
                alterationInsertTestEndTimestamp
        ));

        if (isAnnualWithCertificate) {
            insertAlterations.add(new JsonPathAlteration("$.testTypes[0]", "Annual With Certificate", "testTypeClassification", "ADD_FIELD"));
        }

        // Insert the altered record
        String alteredJson = GenericData.applyJsonAlterations(insertedTestResultRecord, insertAlterations);
        testResultsSteps.insertRecordInDynamo(alteredJson, "test-results", "vin");

        // Create submitted
        DateTime submittedTestStartTimestamp = currentTimestamp.minusMinutes(15);
        DateTime submittedTestTypeStartTimestamp = currentTimestamp.minusMinutes(10);
        DateTime submittedTypeEndTimestamp = currentTimestamp.minusMinutes(5);
        DateTime submittedEndTimestamp = currentTimestamp;

        String testStartTimestamp = submittedTestStartTimestamp.toInstant().toString();
        String testTypeStartTimestamp = submittedTestTypeStartTimestamp.toInstant().toString();
        String testTypeEndTimestamp = submittedTypeEndTimestamp.toInstant().toString();
        String testEndTimestamp = submittedEndTimestamp.toInstant().toString();

        // Expected recalculated testExpiryDate
        String testExpiryDate = insertedTestExpiryDate.plusYears(1).dayOfMonth().withMaximumValue().toInstant().toString();

        System.out.println("\n******************************************************");
        System.out.println("Inserted testCode: " + insertedTestCode);
        System.out.println("Inserted previous ExpiryDate: " + insertedTestExpiryDate.toInstant().toString().substring(0,10));
        System.out.println("POST-ed testCode: " + testCode);
        System.out.println("Today: " + currentTimestamp.toInstant().toString().substring(0,10));
        System.out.println("expected ExpiryDate: " + testExpiryDate.substring(0,10));
        System.out.println("******************************************************\n");

        // Create alteration to add one more tech record to in the request body
        JsonPathAlteration alterationTestStartTimestamp = new JsonPathAlteration("$.testStartTimestamp", testStartTimestamp, "", "REPLACE");
        JsonPathAlteration alterationTestEndTimestamp = new JsonPathAlteration("$.testEndTimestamp", testEndTimestamp, "", "REPLACE");
        JsonPathAlteration alterationTestTypeStartTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeStartTimestamp", testTypeStartTimestamp, "", "REPLACE");
        JsonPathAlteration alterationTestTypeEndTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeEndTimestamp", testTypeEndTimestamp, "", "REPLACE");
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationSysNo = new JsonPathAlteration("$.systemNumber", randomSystemNo, "", "REPLACE");
        JsonPathAlteration alterationTestResultId = new JsonPathAlteration("$.testResultId", randomTestResultId, "", "REPLACE");
        JsonPathAlteration alterationTestName = new JsonPathAlteration("$.testTypes[0].name", name, "", "REPLACE");
        JsonPathAlteration alterationTestTypeId = new JsonPathAlteration("$.testTypes[0].testTypeId", testTypeId, "", "REPLACE");
        JsonPathAlteration alterationTestTypeName = new JsonPathAlteration("$.testTypes[0].testTypeName", testTypeName, "", "REPLACE");
        JsonPathAlteration alterationTestResult = new JsonPathAlteration("$.testTypes[0].testResult", testResult, "", "REPLACE");
        JsonPathAlteration alterationNoOfAxles = new JsonPathAlteration("$.noOfAxles", noOfAxles, "", "REPLACE");

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
                alterationNoOfAxles,
                alterationSysNo,
                alterationTestResult
        ));


        // Post the results, together with any alterations, and verify that they are accepted.
        testResultsSteps.postVehicleTestResultsWithAlterations(postTestResultRecord, alterations);

        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");

        // Retrieve the created record, and verify that the fields are present.
        testResultsSteps.getTestResultsBetweenDate(randomSystemNo, submittedTestStartTimestamp.minusMinutes(10).toInstant().toString(), submittedEndTimestamp.plusMinutes(10).toInstant().toString());
        testResultsSteps.statusCodeShouldBe(200);

        // Verify testCode field has the expected value
        testResultsSteps.valueForFieldInPathShouldBe("[0].testTypes[0].testCode", testCode);

        // Verify testExpiryDate field has the expected value
        testResultsSteps.valueForFieldInPathShouldStartWith("[0].testTypes[0].testExpiryDate", testExpiryDate.substring(0, 10));

    }

    @WithTag("expiry_dates")
    @Title("CVSB-5862 - Expiry and anniversary dates for HGV & TRL certificates - HGV - Today is after the previous expiryDate")
    @Test
    public void testResultsFirstTestExpiryHgvTodayIsAfterThePreviousExpiry() {

        // Read the base INSERT test result JSON.
        String insertedTestResultRecord = GenericData.readJsonValueFromFile("test-results_insert_expiry_date_hgv_5862.json", "$");
        // Read the base POST test result JSON.
        String postTestResultRecord = GenericData.readJsonValueFromFile("test-results_post_expiry_date_hgv_5862.json", "$");

        DateTime currentTimestamp = DateTime.now().withZone(DateTimeZone.UTC);

        // Change the vin testResultsId and SystemNumber to get a unique record
        String randomVin = GenericData.generateRandomVin();
        String randomTestResultId = UUID.randomUUID().toString();
        String randomSystemNo = GenericData.generateRandomSystemNumber();

        // Create the inserted record.
        DateTime insertedTestExpiryDate = currentTimestamp.minusMonths(1).dayOfMonth().withMaximumValue();

        DateTime insertedTestStartTimestamp = insertedTestExpiryDate.minusYears(1).minusMinutes(15);
        DateTime insertedTestTypeStartTimestamp = insertedTestExpiryDate.minusYears(1).minusMinutes(10);
        DateTime insertedLastUpdatedAt = insertedTestExpiryDate.minusYears(1).minusMinutes(5);
        DateTime insertedCreatedAt = insertedTestExpiryDate.minusYears(1).minusMinutes(5);
        DateTime insertedTestTypeEndTimestamp = insertedTestExpiryDate.minusYears(1).minusMinutes(5);
        DateTime insertedTestEndTimestamp = insertedTestExpiryDate.minusYears(1);

        String insertableTestStartTimestamp = insertedTestStartTimestamp.toInstant().toString();
        String insertableTestTypeStartTimestamp = insertedTestTypeStartTimestamp.toInstant().toString();
        String insertableLastUpdatedAt = insertedLastUpdatedAt.toInstant().toString();
        String insertableTestExpiryDate = insertedTestExpiryDate.toInstant().toString();
        String insertableCreatedAt = insertedCreatedAt.toInstant().toString();
        String insertableTestTypeEndTimestamp = insertedTestTypeEndTimestamp.toInstant().toString();
        String insertableTestEndTimestamp = insertedTestEndTimestamp.toInstant().toString();

        // Create alteration to add one more tech record to in the inserted data
        JsonPathAlteration alterationInsertVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationInsertSysNo = new JsonPathAlteration("$.systemNumber", randomSystemNo, "", "REPLACE");
        JsonPathAlteration alterationInsertTestResultId = new JsonPathAlteration("$.testResultId", UUID.randomUUID().toString(), "", "REPLACE");
        JsonPathAlteration alterationInsertTestStartTimestamp = new JsonPathAlteration("$.testStartTimestamp", insertableTestStartTimestamp, "", "REPLACE");
        JsonPathAlteration alterationInsertTestTypeStartTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeStartTimestamp", insertableTestTypeStartTimestamp, "", "REPLACE");
        JsonPathAlteration alterationInsertLastUpdatedAt = new JsonPathAlteration("$.testTypes[0].lastUpdatedAt", insertableLastUpdatedAt, "", "REPLACE");
        JsonPathAlteration alterationInsertTestExpiryDate = new JsonPathAlteration("$.testTypes[0].testExpiryDate", insertableTestExpiryDate, "", "REPLACE");
        JsonPathAlteration alterationInsertCreatedAt = new JsonPathAlteration("$.testTypes[0].createdAt", insertableCreatedAt, "", "REPLACE");
        JsonPathAlteration alterationInsertTestTypeEndTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeEndTimestamp", insertableTestTypeEndTimestamp, "", "REPLACE");
        JsonPathAlteration alterationInsertTestEndTimestamp = new JsonPathAlteration("$.testEndTimestamp", insertableTestEndTimestamp, "", "REPLACE");
        JsonPathAlteration alterationInsertTestCode = new JsonPathAlteration("$.testTypes[0].testCode", insertedTestCode, "", "REPLACE");

        // Collate the list of alterations for the inserted record.
        List<JsonPathAlteration> insertAlterations = new ArrayList<>(Arrays.asList(
                alterationInsertVin,
                alterationInsertTestResultId,
                alterationInsertTestStartTimestamp,
                alterationInsertTestTypeStartTimestamp,
                alterationInsertLastUpdatedAt,
                alterationInsertTestExpiryDate,
                alterationInsertCreatedAt,
                alterationInsertTestTypeEndTimestamp,
                alterationInsertTestCode,
                alterationInsertSysNo,
                alterationInsertTestEndTimestamp
        ));

        if (isAnnualWithCertificate) {
            insertAlterations.add(new JsonPathAlteration("$.testTypes[0]", "Annual With Certificate", "testTypeClassification", "ADD_FIELD"));
        }

        // Insert the altered record
        String alteredJson = GenericData.applyJsonAlterations(insertedTestResultRecord, insertAlterations);
        testResultsSteps.insertRecordInDynamo(alteredJson, "test-results", "vin");

        // Create submitted
        DateTime submittedTestStartTimestamp = currentTimestamp.minusMinutes(15);
        DateTime submittedTestTypeStartTimestamp = currentTimestamp.minusMinutes(10);
        DateTime submittedTypeEndTimestamp = currentTimestamp.minusMinutes(5);
        DateTime submittedEndTimestamp = currentTimestamp;

        String testStartTimestamp = submittedTestStartTimestamp.toInstant().toString();
        String testTypeStartTimestamp = submittedTestTypeStartTimestamp.toInstant().toString();
        String testTypeEndTimestamp = submittedTypeEndTimestamp.toInstant().toString();
        String testEndTimestamp = submittedEndTimestamp.toInstant().toString();

        // Expected recalculated testExpiryDate
        String testExpiryDate = submittedEndTimestamp.plusYears(1).dayOfMonth().withMaximumValue().toInstant().toString();

        System.out.println("\n******************************************************");
        System.out.println("Inserted testCode: " + insertedTestCode);
        System.out.println("Inserted previous ExpiryDate: " + insertedTestExpiryDate.toInstant().toString().substring(0,10));
        System.out.println("POST-ed testCode: " + testCode);
        System.out.println("Today: " + currentTimestamp.toInstant().toString().substring(0,10));
        System.out.println("expected ExpiryDate: " + testExpiryDate.substring(0,10));
        System.out.println("******************************************************\n");

        // Create alteration to add one more tech record to in the request body
        JsonPathAlteration alterationTestStartTimestamp = new JsonPathAlteration("$.testStartTimestamp", testStartTimestamp, "", "REPLACE");
        JsonPathAlteration alterationTestEndTimestamp = new JsonPathAlteration("$.testEndTimestamp", testEndTimestamp, "", "REPLACE");
        JsonPathAlteration alterationTestTypeStartTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeStartTimestamp", testTypeStartTimestamp, "", "REPLACE");
        JsonPathAlteration alterationTestTypeEndTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeEndTimestamp", testTypeEndTimestamp, "", "REPLACE");
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationSysNo = new JsonPathAlteration("$.systemNumber", randomSystemNo, "", "REPLACE");
        JsonPathAlteration alterationTestResultId = new JsonPathAlteration("$.testResultId", randomTestResultId, "", "REPLACE");
        JsonPathAlteration alterationTestName = new JsonPathAlteration("$.testTypes[0].name", name, "", "REPLACE");
        JsonPathAlteration alterationTestTypeId = new JsonPathAlteration("$.testTypes[0].testTypeId", testTypeId, "", "REPLACE");
        JsonPathAlteration alterationTestTypeName = new JsonPathAlteration("$.testTypes[0].testTypeName", testTypeName, "", "REPLACE");
        JsonPathAlteration alterationTestResult = new JsonPathAlteration("$.testTypes[0].testResult", testResult, "", "REPLACE");
        JsonPathAlteration alterationNoOfAxles = new JsonPathAlteration("$.noOfAxles", noOfAxles, "", "REPLACE");

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
                alterationNoOfAxles,
                alterationSysNo,
                alterationTestResult
        ));


        // Post the results, together with any alterations, and verify that they are accepted.
        testResultsSteps.postVehicleTestResultsWithAlterations(postTestResultRecord, alterations);

        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");

        // Retrieve the created record, and verify that the fields are present.
        testResultsSteps.getTestResultsBetweenDate(randomSystemNo, submittedTestStartTimestamp.minusMinutes(10).toInstant().toString(), submittedEndTimestamp.plusMinutes(10).toInstant().toString());
        testResultsSteps.statusCodeShouldBe(200);

        // Verify testCode field has the expected value
        testResultsSteps.valueForFieldInPathShouldBe("[0].testTypes[0].testCode", testCode);

        // Verify testExpiryDate field has the expected value
        testResultsSteps.valueForFieldInPathShouldStartWith("[0].testTypes[0].testExpiryDate", testExpiryDate.substring(0, 10));

    }

}
