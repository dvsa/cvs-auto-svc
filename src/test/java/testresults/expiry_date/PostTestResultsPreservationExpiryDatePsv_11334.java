package testresults.expiry_date;

import data.GenericData;
import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.junit.annotations.UseTestDataFrom;
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
@UseTestDataFrom(value="loader/testdata/test_data_expiry_date_annual_psv_11334.csv")
public class PostTestResultsPreservationExpiryDatePsv_11334 {

    @Steps
    TestResultsSteps testResultsSteps;

    private String name;
    private String testTypeName;
    private String testTypeId;
    private String vehicleSize;
    private String vehicleConfiguration;
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

    public void setVehicleSize(String vehicleSize) {
        this.vehicleSize = vehicleSize;
    }

    public void setVehicleConfiguration(String vehicleConfiguration) {
        this.vehicleConfiguration = vehicleConfiguration;
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
    @Title("CVSB-11334 - As an SVSA/VSA I want to take into consideration the expiry date of COIF + annual test types so that the most recent expiry date is accurately referenced in the future - PSV - Today is more than 2 months before the previousExpiryDate")
    @Test
    public void testResultsExpiryDateTodayIsMoreThanTwoMonthsBeforeAnniversary() {

        // Read the base INSERT test result JSON.
        String insertedTestResultRecord = GenericData.readJsonValueFromFile("test-results_insert_expiry_date_psv_11334.json", "$");
        // Read the base POST test result JSON.
        String postTestResultRecord = GenericData.readJsonValueFromFile("test-results_post_expiry_date_psv_11334.json", "$");

        DateTime currentTimestamp = DateTime.now().withZone(DateTimeZone.UTC);

        //
        String randomVin = GenericData.generateRandomVin();
        String randomTestResultId = UUID.randomUUID().toString();
        String randomSystemNo = GenericData.generateRandomSystemNumber();

        // Create inserted record.
        DateTime insertedTestExpiryDate = currentTimestamp.plusMonths(2).plusDays(1);

        DateTime insertedTestStartTimestamp = insertedTestExpiryDate.minusYears(1).minusMinutes(15);
        DateTime insertedTestTypeStartTimestamp = insertedTestExpiryDate.minusYears(1).minusMinutes(10);
        DateTime insertedTestTypeEndTimestamp = insertedTestExpiryDate.minusYears(1).minusMinutes(5);
        DateTime insertedTestEndTimestamp = insertedTestExpiryDate.minusYears(1);
        DateTime insertedTestAnniversaryDate = insertedTestExpiryDate;
        DateTime insertedCreatedAt = currentTimestamp.minusYears(1).minusMinutes(5);
        DateTime insertedLastUpdatedAt = currentTimestamp.minusYears(1).minusMinutes(5);

        String insertableTestStartTimestamp = insertedTestStartTimestamp.toInstant().toString();
        String insertableTestTypeStartTimestamp = insertedTestTypeStartTimestamp.toInstant().toString();
        String insertableLastUpdatedAt = insertedLastUpdatedAt.toInstant().toString();
        String insertableTestAnniversaryDate = insertedTestAnniversaryDate.toInstant().toString();
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
        JsonPathAlteration alterationInsertTestAnniversaryDate = new JsonPathAlteration("$.testTypes[0].testAnniversaryDate", insertableTestAnniversaryDate, "", "REPLACE");
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
                alterationInsertTestAnniversaryDate,
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
        DateTime submittedEndTimestamp = currentTimestamp;
        DateTime submittedTestStartTimestamp = currentTimestamp.minusMinutes(15);
        DateTime submittedTestTypeStartTimestamp = currentTimestamp.minusMinutes(10);
        DateTime submittedTypeEndTimestamp = currentTimestamp.minusMinutes(5);

        String testStartTimestamp = submittedTestStartTimestamp.toInstant().toString();
        String testTypeStartTimestamp = submittedTestTypeStartTimestamp.toInstant().toString();
        String testTypeEndTimestamp = submittedTypeEndTimestamp.toInstant().toString();
        String testEndTimestamp = submittedEndTimestamp.toInstant().toString();

        String testExpectedDate = submittedTypeEndTimestamp.plusYears(1).minusDays(1).toInstant().toString();

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
                alterationSysNo,
                alterationTestResult
        ));

        System.out.println("\n******************************************************");
        System.out.println("Inserted testCode: " + insertedTestCode);
        System.out.println("Inserted previous ExpiryDate: " + insertedTestExpiryDate.toInstant().toString().substring(0,10));
        System.out.println("POST-ed testCode: " + testCode);
        System.out.println("Today: " + currentTimestamp.toInstant().toString().substring(0,10));
        System.out.println("expected ExpiryDate: " + testExpectedDate.substring(0,10));
        System.out.println("******************************************************\n");

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
        testResultsSteps.valueForFieldInPathShouldStartWith("[0].testTypes[0].testExpiryDate", testExpectedDate.substring(0, 10));

    }

    @WithTag("expiry_dates")
    @Title("CVSB-11334 - As an SVSA/VSA I want to take into consideration the expiry date of COIF + annual test types so that the most recent expiry date is accurately referenced in the future - PSV - Today is exactly 2 months before the previousExpiryDate")
    @Test
    public void testResultsExpiryDateTodayIsExactlyTwoMonthsBeforeAnniversary() {

        // Read the base INSERT test result JSON.
        String insertedTestResultRecord = GenericData.readJsonValueFromFile("test-results_insert_expiry_date_psv_11334.json", "$");
        // Read the base POST test result JSON.
        String postTestResultRecord = GenericData.readJsonValueFromFile("test-results_post_expiry_date_psv_11334.json", "$");

        DateTime currentTimestamp = DateTime.now().withZone(DateTimeZone.UTC);

        //
        String randomVin = GenericData.generateRandomVin();
        String randomTestResultId = UUID.randomUUID().toString();
        String randomSystemNo = GenericData.generateRandomSystemNumber();

        // Create inserted record.
        DateTime insertedTestExpiryDate = currentTimestamp.plusMonths(2);

        DateTime insertedTestStartTimestamp = insertedTestExpiryDate.minusYears(1).minusMinutes(15);
        DateTime insertedTestTypeStartTimestamp = insertedTestExpiryDate.minusYears(1).minusMinutes(10);
        DateTime insertedTestTypeEndTimestamp = insertedTestExpiryDate.minusYears(1).minusMinutes(5);
        DateTime insertedTestEndTimestamp = insertedTestExpiryDate.minusYears(1);
        DateTime insertedTestAnniversaryDate = insertedTestExpiryDate;
        DateTime insertedCreatedAt = currentTimestamp.minusYears(1).minusMinutes(5);
        DateTime insertedLastUpdatedAt = currentTimestamp.minusYears(1).minusMinutes(5);

        String insertableTestStartTimestamp = insertedTestStartTimestamp.toInstant().toString();
        String insertableTestTypeStartTimestamp = insertedTestTypeStartTimestamp.toInstant().toString();
        String insertableLastUpdatedAt = insertedLastUpdatedAt.toInstant().toString();
        String insertableTestAnniversaryDate = insertedTestAnniversaryDate.toInstant().toString();
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
        JsonPathAlteration alterationInsertTestAnniversaryDate = new JsonPathAlteration("$.testTypes[0].testAnniversaryDate", insertableTestAnniversaryDate, "", "REPLACE");
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
                alterationInsertTestAnniversaryDate,
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
        DateTime submittedEndTimestamp = currentTimestamp;
        DateTime submittedTestStartTimestamp = currentTimestamp.minusMinutes(15);
        DateTime submittedTestTypeStartTimestamp = currentTimestamp.minusMinutes(10);
        DateTime submittedTypeEndTimestamp = currentTimestamp.minusMinutes(5);

        String testStartTimestamp = submittedTestStartTimestamp.toInstant().toString();
        String testTypeStartTimestamp = submittedTestTypeStartTimestamp.toInstant().toString();
        String testTypeEndTimestamp = submittedTypeEndTimestamp.toInstant().toString();
        String testEndTimestamp = submittedEndTimestamp.toInstant().toString();

        //String testExpectedDate = insertedTestExpiryDate.plusYears(1).toInstant().toString();

        String testExpectedDate = currentTimestamp.plusYears(1).minusDays(1).toInstant().toString();

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
                alterationSysNo,
                alterationTestResult
        ));

        System.out.println("\n******************************************************");
        System.out.println("Inserted testCode: " + insertedTestCode);
        System.out.println("Inserted previous ExpiryDate: " + insertedTestExpiryDate.toInstant().toString().substring(0,10));
        System.out.println("POST-ed testCode: " + testCode);
        System.out.println("Today: " + currentTimestamp.toInstant().toString().substring(0,10));
        System.out.println("expected ExpiryDate: " + testExpectedDate.substring(0,10));
        System.out.println("******************************************************\n");

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
        testResultsSteps.valueForFieldInPathShouldStartWith("[0].testTypes[0].testExpiryDate", testExpectedDate.substring(0, 10));

    }


    @WithTag("expiry_dates")
    @Title("CVSB-11334 - As an SVSA/VSA I want to take into consideration the expiry date of COIF + annual test types so that the most recent expiry date is accurately referenced in the future - PSV - Today is less than 2 months before the previousExpiryDate")
    @Test
    public void testResultsExpiryDateTodayIsLessThanTwoMonthsBeforeAnniversary() {

        // Read the base INSERT test result JSON.
        String insertedTestResultRecord = GenericData.readJsonValueFromFile("test-results_insert_expiry_date_psv_11334.json", "$");
        // Read the base POST test result JSON.
        String postTestResultRecord = GenericData.readJsonValueFromFile("test-results_post_expiry_date_psv_11334.json", "$");

        DateTime currentTimestamp = DateTime.now().withZone(DateTimeZone.UTC);

        //
        String randomVin = GenericData.generateRandomVin();
        String randomTestResultId = UUID.randomUUID().toString();
        String randomSystemNo = GenericData.generateRandomSystemNumber();

        // Create inserted record.
        DateTime insertedTestExpiryDate = currentTimestamp.plusMonths(2).minusDays(1);

        DateTime insertedTestStartTimestamp = insertedTestExpiryDate.minusYears(1).minusMinutes(15);
        DateTime insertedTestTypeStartTimestamp = insertedTestExpiryDate.minusYears(1).minusMinutes(10);
        DateTime insertedTestTypeEndTimestamp = insertedTestExpiryDate.minusYears(1).minusMinutes(5);
        DateTime insertedTestEndTimestamp = insertedTestExpiryDate.minusYears(1);
        DateTime insertedTestAnniversaryDate = insertedTestExpiryDate;
        DateTime insertedCreatedAt = currentTimestamp.minusYears(1).minusMinutes(5);
        DateTime insertedLastUpdatedAt = currentTimestamp.minusYears(1).minusMinutes(5);

        String insertableTestStartTimestamp = insertedTestStartTimestamp.toInstant().toString();
        String insertableTestTypeStartTimestamp = insertedTestTypeStartTimestamp.toInstant().toString();
        String insertableLastUpdatedAt = insertedLastUpdatedAt.toInstant().toString();
        String insertableTestAnniversaryDate = insertedTestAnniversaryDate.toInstant().toString();
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
        JsonPathAlteration alterationInsertTestAnniversaryDate = new JsonPathAlteration("$.testTypes[0].testAnniversaryDate", insertableTestAnniversaryDate, "", "REPLACE");
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
                alterationInsertTestAnniversaryDate,
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
        DateTime submittedEndTimestamp = currentTimestamp;
        DateTime submittedTestStartTimestamp = currentTimestamp.minusMinutes(15);
        DateTime submittedTestTypeStartTimestamp = currentTimestamp.minusMinutes(10);
        DateTime submittedTypeEndTimestamp = currentTimestamp.minusMinutes(5);

        String testStartTimestamp = submittedTestStartTimestamp.toInstant().toString();
        String testTypeStartTimestamp = submittedTestTypeStartTimestamp.toInstant().toString();
        String testTypeEndTimestamp = submittedTypeEndTimestamp.toInstant().toString();
        String testEndTimestamp = submittedEndTimestamp.toInstant().toString();

        String testExpectedDate = insertedTestExpiryDate.plusYears(1).toInstant().toString();

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
                alterationSysNo,
                alterationTestResult
        ));

        System.out.println("\n******************************************************");
        System.out.println("Inserted testCode: " + insertedTestCode);
        System.out.println("Inserted previous ExpiryDate: " + insertedTestExpiryDate.toInstant().toString().substring(0,10));
        System.out.println("POST-ed testCode: " + testCode);
        System.out.println("Today: " + currentTimestamp.toInstant().toString().substring(0,10));
        System.out.println("expected ExpiryDate: " + testExpectedDate.substring(0,10));
        System.out.println("******************************************************\n");

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
        testResultsSteps.valueForFieldInPathShouldStartWith("[0].testTypes[0].testExpiryDate", testExpectedDate.substring(0, 10));

    }


    @WithTag("expiry_dates")
    @Title("CVSB-11334 - As an SVSA/VSA I want to take into consideration the expiry date of COIF + annual test types so that the most recent expiry date is accurately referenced in the future - PSV - Today is the previousExpiryDate")
    @Test
    public void testResultsExpiryDateTodayIsThePreviousAnniversary() {

        // Read the base INSERT test result JSON.
        String insertedTestResultRecord = GenericData.readJsonValueFromFile("test-results_insert_expiry_date_psv_11334.json", "$");
        // Read the base POST test result JSON.
        String postTestResultRecord = GenericData.readJsonValueFromFile("test-results_post_expiry_date_psv_11334.json", "$");

        DateTime currentTimestamp = DateTime.now().withZone(DateTimeZone.UTC);

        //
        String randomVin = GenericData.generateRandomVin();
        String randomTestResultId = UUID.randomUUID().toString();
        String randomSystemNo = GenericData.generateRandomSystemNumber();

        // Create inserted record.
        DateTime insertedTestExpiryDate = currentTimestamp;

        DateTime insertedTestStartTimestamp = insertedTestExpiryDate.minusYears(1).minusMinutes(15);
        DateTime insertedTestTypeStartTimestamp = insertedTestExpiryDate.minusYears(1).minusMinutes(10);
        DateTime insertedTestTypeEndTimestamp = insertedTestExpiryDate.minusYears(1).minusMinutes(5);
        DateTime insertedTestEndTimestamp = insertedTestExpiryDate.minusYears(1);
        DateTime insertedTestAnniversaryDate = insertedTestExpiryDate;
        DateTime insertedCreatedAt = currentTimestamp.minusYears(1).minusMinutes(5);
        DateTime insertedLastUpdatedAt = currentTimestamp.minusYears(1).minusMinutes(5);

        String insertableTestStartTimestamp = insertedTestStartTimestamp.toInstant().toString();
        String insertableTestTypeStartTimestamp = insertedTestTypeStartTimestamp.toInstant().toString();
        String insertableLastUpdatedAt = insertedLastUpdatedAt.toInstant().toString();
        String insertableTestAnniversaryDate = insertedTestAnniversaryDate.toInstant().toString();
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
        JsonPathAlteration alterationInsertTestAnniversaryDate = new JsonPathAlteration("$.testTypes[0].testAnniversaryDate", insertableTestAnniversaryDate, "", "REPLACE");
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
                alterationInsertTestAnniversaryDate,
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
        DateTime submittedEndTimestamp = currentTimestamp;
        DateTime submittedTestStartTimestamp = currentTimestamp.minusMinutes(15);
        DateTime submittedTestTypeStartTimestamp = currentTimestamp.minusMinutes(10);
        DateTime submittedTypeEndTimestamp = currentTimestamp.minusMinutes(5);

        String testStartTimestamp = submittedTestStartTimestamp.toInstant().toString();
        String testTypeStartTimestamp = submittedTestTypeStartTimestamp.toInstant().toString();
        String testTypeEndTimestamp = submittedTypeEndTimestamp.toInstant().toString();
        String testEndTimestamp = submittedEndTimestamp.toInstant().toString();

        String testExpectedDate = insertedTestExpiryDate.plusYears(1).toInstant().toString();

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
                alterationSysNo,
                alterationTestResult
        ));

        System.out.println("\n******************************************************");
        System.out.println("Inserted testCode: " + insertedTestCode);
        System.out.println("Inserted previous ExpiryDate: " + insertedTestExpiryDate.toInstant().toString().substring(0,10));
        System.out.println("POST-ed testCode: " + testCode);
        System.out.println("Today: " + currentTimestamp.toInstant().toString().substring(0,10));
        System.out.println("expected ExpiryDate: " + testExpectedDate.substring(0,10));
        System.out.println("******************************************************\n");

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
        testResultsSteps.valueForFieldInPathShouldStartWith("[0].testTypes[0].testExpiryDate", testExpectedDate.substring(0, 10));

    }


    @WithTag("expiry_dates")
    @Title("CVSB-11334 - As an SVSA/VSA I want to take into consideration the expiry date of COIF + annual test types so that the most recent expiry date is accurately referenced in the future - PSV - Today is after the previousExpiryDate")
    @Test
    public void testResultsExpiryDateTodayIsAfterThePreviousAnniversary() {

        // Read the base INSERT test result JSON.
        String insertedTestResultRecord = GenericData.readJsonValueFromFile("test-results_insert_expiry_date_psv_11334.json", "$");
        // Read the base POST test result JSON.
        String postTestResultRecord = GenericData.readJsonValueFromFile("test-results_post_expiry_date_psv_11334.json", "$");

        DateTime currentTimestamp = DateTime.now().withZone(DateTimeZone.UTC);

        //
        String randomVin = GenericData.generateRandomVin();
        String randomTestResultId = UUID.randomUUID().toString();
        String randomSystemNo = GenericData.generateRandomSystemNumber();

        // Create inserted record.
        DateTime insertedTestExpiryDate = currentTimestamp.minusDays(1);

        DateTime insertedTestStartTimestamp = insertedTestExpiryDate.minusYears(1).minusMinutes(15);
        DateTime insertedTestTypeStartTimestamp = insertedTestExpiryDate.minusYears(1).minusMinutes(10);
        DateTime insertedTestTypeEndTimestamp = insertedTestExpiryDate.minusYears(1).minusMinutes(5);
        DateTime insertedTestEndTimestamp = insertedTestExpiryDate.minusYears(1);
        DateTime insertedTestAnniversaryDate = insertedTestExpiryDate;
        DateTime insertedCreatedAt = currentTimestamp.minusYears(1).minusMinutes(5);
        DateTime insertedLastUpdatedAt = currentTimestamp.minusYears(1).minusMinutes(5);

        String insertableTestStartTimestamp = insertedTestStartTimestamp.toInstant().toString();
        String insertableTestTypeStartTimestamp = insertedTestTypeStartTimestamp.toInstant().toString();
        String insertableLastUpdatedAt = insertedLastUpdatedAt.toInstant().toString();
        String insertableTestAnniversaryDate = insertedTestAnniversaryDate.toInstant().toString();
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
        JsonPathAlteration alterationInsertTestAnniversaryDate = new JsonPathAlteration("$.testTypes[0].testAnniversaryDate", insertableTestAnniversaryDate, "", "REPLACE");
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
                alterationInsertTestAnniversaryDate,
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
        DateTime submittedEndTimestamp = currentTimestamp;
        DateTime submittedTestStartTimestamp = currentTimestamp.minusMinutes(15);
        DateTime submittedTestTypeStartTimestamp = currentTimestamp.minusMinutes(10);
        DateTime submittedTypeEndTimestamp = currentTimestamp.minusMinutes(5);

        String testStartTimestamp = submittedTestStartTimestamp.toInstant().toString();
        String testTypeStartTimestamp = submittedTestTypeStartTimestamp.toInstant().toString();
        String testTypeEndTimestamp = submittedTypeEndTimestamp.toInstant().toString();
        String testEndTimestamp = submittedEndTimestamp.toInstant().toString();

        String testExpectedDate = currentTimestamp.plusYears(1).minusDays(1).toInstant().toString();

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
                alterationSysNo,
                alterationTestResult
        ));

        System.out.println("\n******************************************************");
        System.out.println("Inserted testCode: " + insertedTestCode);
        System.out.println("Inserted previous ExpiryDate: " + insertedTestExpiryDate.toInstant().toString().substring(0,10));
        System.out.println("POST-ed testCode: " + testCode);
        System.out.println("Today: " + currentTimestamp.toInstant().toString().substring(0,10));
        System.out.println("expected ExpiryDate: " + testExpectedDate.substring(0,10));
        System.out.println("******************************************************\n");

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
        testResultsSteps.valueForFieldInPathShouldStartWith("[0].testTypes[0].testExpiryDate", testExpectedDate.substring(0, 10));

    }

}
