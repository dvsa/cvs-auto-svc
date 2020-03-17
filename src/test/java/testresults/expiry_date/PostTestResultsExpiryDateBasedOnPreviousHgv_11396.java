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
@UseTestDataFrom(value="loader/testdata/test_data_expiry_date_based_on_previous_hgv_preservation.csv")
public class PostTestResultsExpiryDateBasedOnPreviousHgv_11396 {

    @Steps
    TestResultsSteps testResultsSteps;

    private String insertedTestCodeOne;
    private String insertedTestResultOne;
    private String insertedTestTypeIdOne;
    private String insertedTestCodeTwo;
    private String insertedTestResultTwo;
    private String insertedTestTypeIdTwo;
    private String postName;
    private String postTestTypeName;
    private String postTestTypeId;
    private String postTestResult;
    private String postTestCode;
    private int noOfAxles;
    private boolean isAnnualWithCertificate;


    public void setInsertedTestCodeOne(String insertedTestCodeOne) {
        this.insertedTestCodeOne = insertedTestCodeOne;
    }

    public void setInsertedTestResultOne(String insertedTestResultOne) {
        this.insertedTestResultOne = insertedTestResultOne;
    }

    public void setInsertedTestTypeIdOne(String insertedTestTypeIdOne) {
        this.insertedTestTypeIdOne = insertedTestTypeIdOne;
    }

    public void setInsertedTestCodeTwo(String insertedTestCodeTwo) {
        this.insertedTestCodeTwo = insertedTestCodeTwo;
    }

    public void setInsertedTestResultTwo(String insertedTestResultTwo) {
        this.insertedTestResultTwo = insertedTestResultTwo;
    }

    public void setInsertedTestTypeIdTwo(String insertedTestTypeIdTwo) {
        this.insertedTestTypeIdTwo = insertedTestTypeIdTwo;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }

    public void setPostTestTypeName(String postTestTypeName) {
        this.postTestTypeName = postTestTypeName;
    }

    public void setPostTestTypeId(String postTestTypeId) {
        this.postTestTypeId = postTestTypeId;
    }

    public void setPostTestResult(String postTestResult) {
        this.postTestResult = postTestResult;
    }

    public void setPostTestCode(String postTestCode) {
        this.postTestCode = postTestCode;
    }

    public void setNoOfAxles(int noOfAxles) {
        this.noOfAxles = noOfAxles;
    }

    public void setAnnualWithCertificate(boolean annualWithCertificate) {
        isAnnualWithCertificate = annualWithCertificate;
    }

    @WithTag("In_Test")
    @Title("CVSB-11396 - As a VSA I want to be able to submit a test for which the expiry date is automatically calculated based on the previous test types history - invalid testCode expiry date LATER than valid - HGV")
    @Test
    public void testResultsMostRecentExpiryInvalidLaterHgv() {

        // Read the base INSERT test result JSON.
        String insertedTestResultRecord = GenericData.readJsonValueFromFile("test-results_insert_two_expiry_date_hgv_11396.json", "$");
        // Read the base POST test result JSON.
        String postTestResultRecord = GenericData.readJsonValueFromFile("test-results_post_expiry_date_hgv_11396.json", "$");

        DateTime currentTimestamp = DateTime.now().withZone(DateTimeZone.UTC);

        // Generate a random VIN and a random System Number
        String randomVin = GenericData.generateRandomVin();
        String randomSystemNumber = GenericData.generateRandomSystemNumber();

        // Inserted expiryDate for test one
        DateTime insertedTestExpiryDateOne = currentTimestamp.dayOfMonth().withMaximumValue();

        // Create inserted record One.
        DateTime insertedTestStartTimestampOne = insertedTestExpiryDateOne.minusYears(1).minusMinutes(15);
        DateTime insertedTestTypeStartTimestampOne = insertedTestExpiryDateOne.minusYears(1).minusMinutes(10);
        DateTime insertedLastUpdatedAtOne = insertedTestExpiryDateOne.minusYears(1).minusMinutes(5);
        DateTime insertedCreatedAtOne = insertedTestExpiryDateOne.minusYears(1).minusMinutes(5);
        DateTime insertedTestTypeEndTimestampOne = insertedTestExpiryDateOne.minusYears(1).minusMinutes(5);
        DateTime insertedTestEndTimestampOne = insertedTestExpiryDateOne.minusYears(1);

        String insertableTestStartTimestampOne = insertedTestStartTimestampOne.toInstant().toString();
        String insertableTestTypeStartTimestampOne = insertedTestTypeStartTimestampOne.toInstant().toString();
        String insertableLastUpdatedAtOne = insertedLastUpdatedAtOne.toInstant().toString();
        String insertableTestExpiryDateOne = insertedTestExpiryDateOne.toInstant().toString();
        String insertableCreatedAtOne = insertedCreatedAtOne.toInstant().toString();
        String insertableTestTypeEndTimestampOne = insertedTestTypeEndTimestampOne.toInstant().toString();
        String insertableTestEndTimestampOne = insertedTestEndTimestampOne.toInstant().toString();
        String insertableTestResultIdOne = UUID.randomUUID().toString();

        // Create alteration to add one more tech record to in the inserted data
        JsonPathAlteration alterationInsertOneVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationInsertOneSystemNo = new JsonPathAlteration("$.systemNumber", randomSystemNumber, "", "REPLACE");
        JsonPathAlteration alterationInsertOneNoOfAxles = new JsonPathAlteration("$.noOfAxles", noOfAxles, "", "REPLACE");
        JsonPathAlteration alterationInsertOneTestResultId = new JsonPathAlteration("$.testResultId", insertableTestResultIdOne, "", "REPLACE");
        JsonPathAlteration alterationInsertOneTestStartTimestamp = new JsonPathAlteration("$.testStartTimestamp", insertableTestStartTimestampOne, "", "REPLACE");
        JsonPathAlteration alterationInsertOneTestTypeStartTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeStartTimestamp", insertableTestTypeStartTimestampOne, "", "REPLACE");
        JsonPathAlteration alterationInsertOneLastUpdatedAt = new JsonPathAlteration("$.testTypes[0].lastUpdatedAt", insertableLastUpdatedAtOne, "", "REPLACE");
        JsonPathAlteration alterationInsertOneTestExpiryDateOne = new JsonPathAlteration("$.testTypes[0].testExpiryDate", insertableTestExpiryDateOne, "", "REPLACE");
        JsonPathAlteration alterationInsertOneCreatedAt = new JsonPathAlteration("$.testTypes[0].createdAt", insertableCreatedAtOne, "", "REPLACE");
        JsonPathAlteration alterationInsertOneTestTypeEndTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeEndTimestamp", insertableTestTypeEndTimestampOne, "", "REPLACE");
        JsonPathAlteration alterationInsertOneTestEndTimestamp = new JsonPathAlteration("$.testEndTimestamp", insertableTestEndTimestampOne, "", "REPLACE");
        JsonPathAlteration alterationInsertOneTestCode = new JsonPathAlteration("$.testTypes[0].testCode", insertedTestCodeOne, "", "REPLACE");
        JsonPathAlteration alterationInsertOneTestTypeId = new JsonPathAlteration("$.testTypes[0].testTypeId", insertedTestTypeIdOne, "", "REPLACE");
        JsonPathAlteration alterationInsertOneTestResult = new JsonPathAlteration("$.testTypes[0].testResult", insertedTestResultOne, "", "REPLACE");

        // Collate the list of alterations for the inserted record.
        List<JsonPathAlteration> insertAlterationsOne = new ArrayList<>(Arrays.asList(
                alterationInsertOneVin,
                alterationInsertOneSystemNo,
                alterationInsertOneNoOfAxles,
                alterationInsertOneTestResultId,
                alterationInsertOneTestStartTimestamp,
                alterationInsertOneTestTypeStartTimestamp,
                alterationInsertOneLastUpdatedAt,
                alterationInsertOneTestExpiryDateOne,
                alterationInsertOneCreatedAt,
                alterationInsertOneTestTypeEndTimestamp,
                alterationInsertOneTestEndTimestamp,
                alterationInsertOneTestCode,
                alterationInsertOneTestTypeId,
                alterationInsertOneTestResult
        ));

        if (isAnnualWithCertificate) {
            insertAlterationsOne.add(new JsonPathAlteration("$.testTypes[0]", "Annual With Certificate", "testTypeClassification", "ADD_FIELD"));
        }

        // Insert the first altered record
        String alteredJsonOne = GenericData.applyJsonAlterations(insertedTestResultRecord, insertAlterationsOne);
        testResultsSteps.insertRecordInDynamo(alteredJsonOne, "test-results");

        // Inserted expiryDate for test two
        DateTime insertedTestExpiryDateTwo = currentTimestamp.plusMonths(1).dayOfMonth().withMaximumValue();

        // Create inserted record Two.
        DateTime insertedTestStartTimestampTwo = insertedTestExpiryDateTwo.minusYears(1).minusMinutes(15);
        DateTime insertedTestTypeStartTimestampTwo = insertedTestExpiryDateTwo.minusYears(1).minusMinutes(10);
        DateTime insertedLastUpdatedAtTwo = insertedTestExpiryDateTwo.minusYears(1).minusMinutes(5);
        DateTime insertedCreatedAtTwo = insertedTestExpiryDateTwo.minusYears(1).minusMinutes(5);
        DateTime insertedTestTypeEndTimestampTwo = insertedTestExpiryDateTwo.minusYears(1).minusMinutes(5);
        DateTime insertedTestEndTimestampTwo = insertedTestExpiryDateTwo.minusYears(1);

        String insertableTestStartTimestampTwo = insertedTestStartTimestampTwo.toInstant().toString();
        String insertableTestTypeStartTimestampTwo = insertedTestTypeStartTimestampTwo.toInstant().toString();
        String insertableLastUpdatedAtTwo = insertedLastUpdatedAtTwo.toInstant().toString();
        String insertableTestExpiryDateTwo = insertedTestExpiryDateTwo.toInstant().toString();
        String insertableCreatedAtTwo = insertedCreatedAtTwo.toInstant().toString();
        String insertableTestTypeEndTimestampTwo = insertedTestTypeEndTimestampTwo.toInstant().toString();
        String insertableTestEndTimestampTwo = insertedTestEndTimestampTwo.toInstant().toString();
        String insertableTestResultIdTwo = UUID.randomUUID().toString();

        // Create alteration to add one more tech record to in the inserted data
        JsonPathAlteration alterationInsertTwoVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationInsertTwoSystemNo = new JsonPathAlteration("$.systemNumber", randomSystemNumber, "", "REPLACE");
        JsonPathAlteration alterationInsertTwoNoOfAxles = new JsonPathAlteration("$.noOfAxles", noOfAxles, "", "REPLACE");
        JsonPathAlteration alterationInsertTwoTestResultId = new JsonPathAlteration("$.testResultId", insertableTestResultIdTwo, "", "REPLACE");
        JsonPathAlteration alterationInsertTwoTestStartTimestamp = new JsonPathAlteration("$.testStartTimestamp", insertableTestStartTimestampTwo, "", "REPLACE");
        JsonPathAlteration alterationInsertTwoTestTypeStartTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeStartTimestamp", insertableTestTypeStartTimestampTwo, "", "REPLACE");
        JsonPathAlteration alterationInsertTwoLastUpdatedAt = new JsonPathAlteration("$.testTypes[0].lastUpdatedAt", insertableLastUpdatedAtTwo, "", "REPLACE");
        JsonPathAlteration alterationInsertTwoTestExpiryDateOne = new JsonPathAlteration("$.testTypes[0].testExpiryDate", insertableTestExpiryDateTwo, "", "REPLACE");
        JsonPathAlteration alterationInsertTwoCreatedAt = new JsonPathAlteration("$.testTypes[0].createdAt", insertableCreatedAtTwo, "", "REPLACE");
        JsonPathAlteration alterationInsertTwoTestTypeEndTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeEndTimestamp", insertableTestTypeEndTimestampTwo, "", "REPLACE");
        JsonPathAlteration alterationInsertTwoTestEndTimestamp = new JsonPathAlteration("$.testEndTimestamp", insertableTestEndTimestampTwo, "", "REPLACE");
        JsonPathAlteration alterationInsertTwoTestCode = new JsonPathAlteration("$.testTypes[0].testCode", insertedTestCodeTwo, "", "REPLACE");
        JsonPathAlteration alterationInsertTwoTestTypeId = new JsonPathAlteration("$.testTypes[0].testTypeId", insertedTestTypeIdTwo, "", "REPLACE");
        JsonPathAlteration alterationInsertTwoTestResult = new JsonPathAlteration("$.testTypes[0].testResult", insertedTestResultTwo, "", "REPLACE");

        // Collate the list of alterations for the inserted record.
        List<JsonPathAlteration> insertAlterationsTwo = new ArrayList<>(Arrays.asList(
                alterationInsertTwoVin,
                alterationInsertTwoSystemNo,
                alterationInsertTwoNoOfAxles,
                alterationInsertTwoTestResultId,
                alterationInsertTwoTestStartTimestamp,
                alterationInsertTwoTestTypeStartTimestamp,
                alterationInsertTwoLastUpdatedAt,
                alterationInsertTwoTestExpiryDateOne,
                alterationInsertTwoCreatedAt,
                alterationInsertTwoTestTypeEndTimestamp,
                alterationInsertTwoTestEndTimestamp,
                alterationInsertTwoTestCode,
                alterationInsertTwoTestTypeId,
                alterationInsertTwoTestResult
        ));

        if (isAnnualWithCertificate) {
            insertAlterationsTwo.add(new JsonPathAlteration("$.testTypes[0]", "Annual With Certificate", "testTypeClassification", "ADD_FIELD"));
        }

        // Insert the first altered record
        String alteredJsonTwo = GenericData.applyJsonAlterations(insertedTestResultRecord, insertAlterationsTwo);
        testResultsSteps.insertRecordInDynamo(alteredJsonTwo, "test-results");

        // Create submitted
        DateTime submittedTestStartTimestamp = currentTimestamp.minusMinutes(15);
        DateTime submittedTestTypeStartTimestamp = currentTimestamp.plusMinutes(10);
        DateTime submittedTypeEndTimestamp = currentTimestamp.plusMinutes(5);
        DateTime submittedEndTimestamp = currentTimestamp;

        String testStartTimestamp = submittedTestStartTimestamp.toInstant().toString();
        String testTypeStartTimestamp = submittedTestTypeStartTimestamp.toInstant().toString();
        String testTypeEndTimestamp = submittedTypeEndTimestamp.toInstant().toString();
        String testEndTimestamp = submittedEndTimestamp.toInstant().toString();

        // Expected recalculated expiry date
        String testExpiryDate = insertedTestExpiryDateOne.plusYears(1).toInstant().toString();

        // Create alteration to add one more tech record to in the request body
        JsonPathAlteration alterationTestStartTimestamp = new JsonPathAlteration("$.testStartTimestamp", testStartTimestamp, "", "REPLACE");
        JsonPathAlteration alterationTestEndTimestamp = new JsonPathAlteration("$.testEndTimestamp", testEndTimestamp, "", "REPLACE");
        JsonPathAlteration alterationTestTypeStartTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeStartTimestamp", testTypeStartTimestamp, "", "REPLACE");
        JsonPathAlteration alterationTestTypeEndTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeEndTimestamp", testTypeEndTimestamp, "", "REPLACE");
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationSystemNo = new JsonPathAlteration("$.systemNumber", randomSystemNumber, "", "REPLACE");
        JsonPathAlteration alterationTestResultId = new JsonPathAlteration("$.testResultId", UUID.randomUUID().toString(), "", "REPLACE");
        JsonPathAlteration alterationTestName = new JsonPathAlteration("$.testTypes[0].name", postName, "", "REPLACE");
        JsonPathAlteration alterationTestTypeId = new JsonPathAlteration("$.testTypes[0].testTypeId", postTestTypeId, "", "REPLACE");
        JsonPathAlteration alterationTestTypeName = new JsonPathAlteration("$.testTypes[0].testTypeName", postTestTypeName, "", "REPLACE");
        JsonPathAlteration alterationTestResult = new JsonPathAlteration("$.testTypes[0].testResult", postTestResult, "", "REPLACE");
        JsonPathAlteration alterationNoOfAxles = new JsonPathAlteration("$.noOfAxles", noOfAxles, "", "REPLACE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(
                alterationTestStartTimestamp,
                alterationTestEndTimestamp,
                alterationTestTypeStartTimestamp,
                alterationTestTypeEndTimestamp,
                alterationVin,
                alterationSystemNo,
                alterationTestResultId,
                alterationTestName,
                alterationTestTypeId,
                alterationTestTypeName,
                alterationNoOfAxles,
                alterationTestResult
        ));

        // Printing the scenario details to the console
        System.out.println("\n******************************************************");
        System.out.println("Inserted testCode One:            " + insertedTestCodeOne);
        System.out.println("Inserted previous ExpiryDate One: " + insertedTestExpiryDateOne.toInstant().toString().substring(0,10));
        System.out.println("Inserted testCode Two:            " + insertedTestCodeTwo);
        System.out.println("Inserted previous ExpiryDate One: " + insertedTestExpiryDateTwo.toInstant().toString().substring(0,10));
        System.out.println("POST-ed testCode:                 " + postTestCode);
        System.out.println("Today:                            " + currentTimestamp.toInstant().toString().substring(0,10));
        System.out.println("expected ExpiryDate:              " + testExpiryDate.substring(0,10));
        System.out.println("******************************************************\n");

        // Post the results, together with any alterations, and verify that they are accepted.
        testResultsSteps.postVehicleTestResultsWithAlterations(postTestResultRecord, alterations);

        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");

        // Retrieve the created record, and verify that the fields are present.
        testResultsSteps.getTestResultsBetweenDate(randomSystemNumber, submittedTestStartTimestamp.minusMinutes(10).toInstant().toString(), submittedEndTimestamp.plusMinutes(10).toInstant().toString());
        testResultsSteps.statusCodeShouldBe(200);

        // Verify testCode field has the expected value
        testResultsSteps.valueForFieldInPathShouldBe("[0].testTypes[0].testCode", postTestCode);

        // Verify testExpiryDate field has the expected value
        testResultsSteps.valueForFieldInPathShouldStartWith("[0].testTypes[0].testExpiryDate", testExpiryDate.substring(0, 10));

    }

    @WithTag("In_Test")
    @Title("CVSB-11396 - As a VSA I want to be able to submit a test for which the expiry date is automatically calculated based on the previous test types history  - invalid testCode expiry date SOONER than valid - HGV")
    @Test
    public void testResultsMostRecentExpiryInvalidSoonerHgv() {

        // Read the base INSERT test result JSON.
        String insertedTestResultRecord = GenericData.readJsonValueFromFile("test-results_insert_two_expiry_date_hgv_11396.json", "$");
        // Read the base POST test result JSON.
        String postTestResultRecord = GenericData.readJsonValueFromFile("test-results_post_expiry_date_hgv_11396.json", "$");

        DateTime currentTimestamp = DateTime.now().withZone(DateTimeZone.UTC);

        // Generate a random VIN and a random System Number
        String randomVin = GenericData.generateRandomVin();
        String randomSystemNumber = GenericData.generateRandomSystemNumber();

        // Inserted expiryDate for test one
        DateTime insertedTestExpiryDateOne = currentTimestamp.plusMonths(1).dayOfMonth().withMaximumValue();

        // Create inserted record One.
        DateTime insertedTestStartTimestampOne = insertedTestExpiryDateOne.minusYears(1).minusMinutes(15);
        DateTime insertedTestTypeStartTimestampOne = insertedTestExpiryDateOne.minusYears(1).minusMinutes(10);
        DateTime insertedLastUpdatedAtOne = insertedTestExpiryDateOne.minusYears(1).minusMinutes(5);
        DateTime insertedCreatedAtOne = insertedTestExpiryDateOne.minusYears(1).minusMinutes(5);
        DateTime insertedTestTypeEndTimestampOne = insertedTestExpiryDateOne.minusYears(1).minusMinutes(5);
        DateTime insertedTestEndTimestampOne = insertedTestExpiryDateOne.minusYears(1);

        String insertableTestStartTimestampOne = insertedTestStartTimestampOne.toInstant().toString();
        String insertableTestTypeStartTimestampOne = insertedTestTypeStartTimestampOne.toInstant().toString();
        String insertableLastUpdatedAtOne = insertedLastUpdatedAtOne.toInstant().toString();
        String insertableTestExpiryDateOne = insertedTestExpiryDateOne.toInstant().toString();
        String insertableCreatedAtOne = insertedCreatedAtOne.toInstant().toString();
        String insertableTestTypeEndTimestampOne = insertedTestTypeEndTimestampOne.toInstant().toString();
        String insertableTestEndTimestampOne = insertedTestEndTimestampOne.toInstant().toString();
        String insertableTestResultIdOne = UUID.randomUUID().toString();

        // Create alteration to add one more tech record to in the inserted data
        JsonPathAlteration alterationInsertOneVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationInsertOneSystemNo = new JsonPathAlteration("$.systemNumber", randomSystemNumber, "", "REPLACE");
        JsonPathAlteration alterationInsertOneNoOfAxles = new JsonPathAlteration("$.noOfAxles", noOfAxles, "", "REPLACE");
        JsonPathAlteration alterationInsertOneTestResultId = new JsonPathAlteration("$.testResultId", insertableTestResultIdOne, "", "REPLACE");
        JsonPathAlteration alterationInsertOneTestStartTimestamp = new JsonPathAlteration("$.testStartTimestamp", insertableTestStartTimestampOne, "", "REPLACE");
        JsonPathAlteration alterationInsertOneTestTypeStartTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeStartTimestamp", insertableTestTypeStartTimestampOne, "", "REPLACE");
        JsonPathAlteration alterationInsertOneLastUpdatedAt = new JsonPathAlteration("$.testTypes[0].lastUpdatedAt", insertableLastUpdatedAtOne, "", "REPLACE");
        JsonPathAlteration alterationInsertOneTestExpiryDateOne = new JsonPathAlteration("$.testTypes[0].testExpiryDate", insertableTestExpiryDateOne, "", "REPLACE");
        JsonPathAlteration alterationInsertOneCreatedAt = new JsonPathAlteration("$.testTypes[0].createdAt", insertableCreatedAtOne, "", "REPLACE");
        JsonPathAlteration alterationInsertOneTestTypeEndTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeEndTimestamp", insertableTestTypeEndTimestampOne, "", "REPLACE");
        JsonPathAlteration alterationInsertOneTestEndTimestamp = new JsonPathAlteration("$.testEndTimestamp", insertableTestEndTimestampOne, "", "REPLACE");
        JsonPathAlteration alterationInsertOneTestCode = new JsonPathAlteration("$.testTypes[0].testCode", insertedTestCodeOne, "", "REPLACE");
        JsonPathAlteration alterationInsertOneTestTypeId = new JsonPathAlteration("$.testTypes[0].testTypeId", insertedTestTypeIdOne, "", "REPLACE");
        JsonPathAlteration alterationInsertOneTestResult = new JsonPathAlteration("$.testTypes[0].testResult", insertedTestResultOne, "", "REPLACE");

        // Collate the list of alterations for the inserted record.
        List<JsonPathAlteration> insertAlterationsOne = new ArrayList<>(Arrays.asList(
                alterationInsertOneVin,
                alterationInsertOneSystemNo,
                alterationInsertOneNoOfAxles,
                alterationInsertOneTestResultId,
                alterationInsertOneTestStartTimestamp,
                alterationInsertOneTestTypeStartTimestamp,
                alterationInsertOneLastUpdatedAt,
                alterationInsertOneTestExpiryDateOne,
                alterationInsertOneCreatedAt,
                alterationInsertOneTestTypeEndTimestamp,
                alterationInsertOneTestEndTimestamp,
                alterationInsertOneTestCode,
                alterationInsertOneTestTypeId,
                alterationInsertOneTestResult
        ));

        if (isAnnualWithCertificate) {
            insertAlterationsOne.add(new JsonPathAlteration("$.testTypes[0]", "Annual With Certificate", "testTypeClassification", "ADD_FIELD"));
        }

        // Insert the first altered record
        String alteredJsonOne = GenericData.applyJsonAlterations(insertedTestResultRecord, insertAlterationsOne);
        testResultsSteps.insertRecordInDynamo(alteredJsonOne, "test-results");

        // Inserted expiryDate for test two
        DateTime insertedTestExpiryDateTwo = currentTimestamp.dayOfMonth().withMaximumValue();

        // Create inserted record Two.
        DateTime insertedTestStartTimestampTwo = insertedTestExpiryDateTwo.minusYears(1).minusMinutes(15);
        DateTime insertedTestTypeStartTimestampTwo = insertedTestExpiryDateTwo.minusYears(1).minusMinutes(10);
        DateTime insertedLastUpdatedAtTwo = insertedTestExpiryDateTwo.minusYears(1).minusMinutes(5);
        DateTime insertedCreatedAtTwo = insertedTestExpiryDateTwo.minusYears(1).minusMinutes(5);
        DateTime insertedTestTypeEndTimestampTwo = insertedTestExpiryDateTwo.minusYears(1).minusMinutes(5);
        DateTime insertedTestEndTimestampTwo = insertedTestExpiryDateTwo.minusYears(1);

        String insertableTestStartTimestampTwo = insertedTestStartTimestampTwo.toInstant().toString();
        String insertableTestTypeStartTimestampTwo = insertedTestTypeStartTimestampTwo.toInstant().toString();
        String insertableLastUpdatedAtTwo = insertedLastUpdatedAtTwo.toInstant().toString();
        String insertableTestExpiryDateTwo = insertedTestExpiryDateTwo.toInstant().toString();
        String insertableCreatedAtTwo = insertedCreatedAtTwo.toInstant().toString();
        String insertableTestTypeEndTimestampTwo = insertedTestTypeEndTimestampTwo.toInstant().toString();
        String insertableTestEndTimestampTwo = insertedTestEndTimestampTwo.toInstant().toString();
        String insertableTestResultIdTwo = UUID.randomUUID().toString();

        // Create alteration to add one more tech record to in the inserted data
        JsonPathAlteration alterationInsertTwoVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationInsertTwoSystemNo = new JsonPathAlteration("$.systemNumber", randomSystemNumber, "", "REPLACE");
        JsonPathAlteration alterationInsertTwoNoOfAxles = new JsonPathAlteration("$.noOfAxles", noOfAxles, "", "REPLACE");
        JsonPathAlteration alterationInsertTwoTestResultId = new JsonPathAlteration("$.testResultId", insertableTestResultIdTwo, "", "REPLACE");
        JsonPathAlteration alterationInsertTwoTestStartTimestamp = new JsonPathAlteration("$.testStartTimestamp", insertableTestStartTimestampTwo, "", "REPLACE");
        JsonPathAlteration alterationInsertTwoTestTypeStartTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeStartTimestamp", insertableTestTypeStartTimestampTwo, "", "REPLACE");
        JsonPathAlteration alterationInsertTwoLastUpdatedAt = new JsonPathAlteration("$.testTypes[0].lastUpdatedAt", insertableLastUpdatedAtTwo, "", "REPLACE");
        JsonPathAlteration alterationInsertTwoTestExpiryDateOne = new JsonPathAlteration("$.testTypes[0].testExpiryDate", insertableTestExpiryDateTwo, "", "REPLACE");
        JsonPathAlteration alterationInsertTwoCreatedAt = new JsonPathAlteration("$.testTypes[0].createdAt", insertableCreatedAtTwo, "", "REPLACE");
        JsonPathAlteration alterationInsertTwoTestTypeEndTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeEndTimestamp", insertableTestTypeEndTimestampTwo, "", "REPLACE");
        JsonPathAlteration alterationInsertTwoTestEndTimestamp = new JsonPathAlteration("$.testEndTimestamp", insertableTestEndTimestampTwo, "", "REPLACE");
        JsonPathAlteration alterationInsertTwoTestCode = new JsonPathAlteration("$.testTypes[0].testCode", insertedTestCodeTwo, "", "REPLACE");
        JsonPathAlteration alterationInsertTwoTestTypeId = new JsonPathAlteration("$.testTypes[0].testTypeId", insertedTestTypeIdTwo, "", "REPLACE");
        JsonPathAlteration alterationInsertTwoTestResult = new JsonPathAlteration("$.testTypes[0].testResult", insertedTestResultTwo, "", "REPLACE");

        // Collate the list of alterations for the inserted record.
        List<JsonPathAlteration> insertAlterationsTwo = new ArrayList<>(Arrays.asList(
                alterationInsertTwoVin,
                alterationInsertTwoSystemNo,
                alterationInsertTwoNoOfAxles,
                alterationInsertTwoTestResultId,
                alterationInsertTwoTestStartTimestamp,
                alterationInsertTwoTestTypeStartTimestamp,
                alterationInsertTwoLastUpdatedAt,
                alterationInsertTwoTestExpiryDateOne,
                alterationInsertTwoCreatedAt,
                alterationInsertTwoTestTypeEndTimestamp,
                alterationInsertTwoTestEndTimestamp,
                alterationInsertTwoTestCode,
                alterationInsertTwoTestTypeId,
                alterationInsertTwoTestResult
        ));

        if (isAnnualWithCertificate) {
            insertAlterationsTwo.add(new JsonPathAlteration("$.testTypes[0]", "Annual With Certificate", "testTypeClassification", "ADD_FIELD"));
        }

        // Insert the first altered record
        String alteredJsonTwo = GenericData.applyJsonAlterations(insertedTestResultRecord, insertAlterationsTwo);
        testResultsSteps.insertRecordInDynamo(alteredJsonTwo, "test-results");

        // Create submitted
        DateTime submittedTestStartTimestamp = currentTimestamp.minusMinutes(15);
        DateTime submittedTestTypeStartTimestamp = currentTimestamp.plusMinutes(10);
        DateTime submittedTypeEndTimestamp = currentTimestamp.plusMinutes(5);
        DateTime submittedEndTimestamp = currentTimestamp;

        String testStartTimestamp = submittedTestStartTimestamp.toInstant().toString();
        String testTypeStartTimestamp = submittedTestTypeStartTimestamp.toInstant().toString();
        String testTypeEndTimestamp = submittedTypeEndTimestamp.toInstant().toString();
        String testEndTimestamp = submittedEndTimestamp.toInstant().toString();

        // Expected recalculated expiry date
        String testExpiryDate = insertedTestExpiryDateOne.plusYears(1).toInstant().toString();

        // Create alteration to add one more tech record to in the request body
        JsonPathAlteration alterationTestStartTimestamp = new JsonPathAlteration("$.testStartTimestamp", testStartTimestamp, "", "REPLACE");
        JsonPathAlteration alterationTestEndTimestamp = new JsonPathAlteration("$.testEndTimestamp", testEndTimestamp, "", "REPLACE");
        JsonPathAlteration alterationTestTypeStartTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeStartTimestamp", testTypeStartTimestamp, "", "REPLACE");
        JsonPathAlteration alterationTestTypeEndTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeEndTimestamp", testTypeEndTimestamp, "", "REPLACE");
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationSystemNo = new JsonPathAlteration("$.systemNumber", randomSystemNumber, "", "REPLACE");
        JsonPathAlteration alterationTestResultId = new JsonPathAlteration("$.testResultId", UUID.randomUUID().toString(), "", "REPLACE");
        JsonPathAlteration alterationTestName = new JsonPathAlteration("$.testTypes[0].name", postName, "", "REPLACE");
        JsonPathAlteration alterationTestTypeId = new JsonPathAlteration("$.testTypes[0].testTypeId", postTestTypeId, "", "REPLACE");
        JsonPathAlteration alterationTestTypeName = new JsonPathAlteration("$.testTypes[0].testTypeName", postTestTypeName, "", "REPLACE");
        JsonPathAlteration alterationTestResult = new JsonPathAlteration("$.testTypes[0].testResult", postTestResult, "", "REPLACE");
        JsonPathAlteration alterationNoOfAxles = new JsonPathAlteration("$.noOfAxles", noOfAxles, "", "REPLACE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(
                alterationTestStartTimestamp,
                alterationTestEndTimestamp,
                alterationTestTypeStartTimestamp,
                alterationTestTypeEndTimestamp,
                alterationVin,
                alterationSystemNo,
                alterationTestResultId,
                alterationTestName,
                alterationTestTypeId,
                alterationTestTypeName,
                alterationNoOfAxles,
                alterationTestResult
        ));

        // Printing the scenario details to the console
        System.out.println("\n******************************************************");
        System.out.println("Inserted testCode One:            " + insertedTestCodeOne);
        System.out.println("Inserted previous ExpiryDate One: " + insertedTestExpiryDateOne.toInstant().toString().substring(0,10));
        System.out.println("Inserted testCode Two:            " + insertedTestCodeTwo);
        System.out.println("Inserted previous ExpiryDate One: " + insertedTestExpiryDateTwo.toInstant().toString().substring(0,10));
        System.out.println("POST-ed testCode:                 " + postTestCode);
        System.out.println("Today:                            " + currentTimestamp.toInstant().toString().substring(0,10));
        System.out.println("expected ExpiryDate:              " + testExpiryDate.substring(0,10));
        System.out.println("******************************************************\n");

        // Post the results, together with any alterations, and verify that they are accepted.
        testResultsSteps.postVehicleTestResultsWithAlterations(postTestResultRecord, alterations);

        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");

        // Retrieve the created record, and verify that the fields are present.
        testResultsSteps.getTestResultsBetweenDate(randomSystemNumber, submittedTestStartTimestamp.minusMinutes(10).toInstant().toString(), submittedEndTimestamp.plusMinutes(10).toInstant().toString());
        testResultsSteps.statusCodeShouldBe(200);

        // Verify testCode field has the expected value
        testResultsSteps.valueForFieldInPathShouldBe("[0].testTypes[0].testCode", postTestCode);

        // Verify testExpiryDate field has the expected value
        testResultsSteps.valueForFieldInPathShouldStartWith("[0].testTypes[0].testExpiryDate", testExpiryDate.substring(0, 10));

    }

    @WithTag("In_Test")
    @Title("CVSB-11396 - As a VSA I want to be able to submit a test for which the expiry date is automatically calculated based on the previous test types history  - valid testCode expired - HGV")
    @Test
    public void testResultsMostRecentExpiryValidExpiredHgv() {

        // Read the base INSERT test result JSON.
        String insertedTestResultRecord = GenericData.readJsonValueFromFile("test-results_insert_two_expiry_date_hgv_11396.json", "$");
        // Read the base POST test result JSON.
        String postTestResultRecord = GenericData.readJsonValueFromFile("test-results_post_expiry_date_hgv_11396.json", "$");

        DateTime currentTimestamp = DateTime.now().withZone(DateTimeZone.UTC);

        // Generate a random VIN and a random System Number
        String randomVin = GenericData.generateRandomVin();
        String randomSystemNumber = GenericData.generateRandomSystemNumber();

        // Inserted expiryDate for test one
        DateTime insertedTestExpiryDateOne = currentTimestamp.minusMonths(1).dayOfMonth().withMaximumValue();

        // Create inserted record One.
        DateTime insertedTestStartTimestampOne = insertedTestExpiryDateOne.minusYears(1).minusMinutes(15);
        DateTime insertedTestTypeStartTimestampOne = insertedTestExpiryDateOne.minusYears(1).minusMinutes(10);
        DateTime insertedLastUpdatedAtOne = insertedTestExpiryDateOne.minusYears(1).minusMinutes(5);
        DateTime insertedCreatedAtOne = insertedTestExpiryDateOne.minusYears(1).minusMinutes(5);
        DateTime insertedTestTypeEndTimestampOne = insertedTestExpiryDateOne.minusYears(1).minusMinutes(5);
        DateTime insertedTestEndTimestampOne = insertedTestExpiryDateOne.minusYears(1);

        String insertableTestStartTimestampOne = insertedTestStartTimestampOne.toInstant().toString();
        String insertableTestTypeStartTimestampOne = insertedTestTypeStartTimestampOne.toInstant().toString();
        String insertableLastUpdatedAtOne = insertedLastUpdatedAtOne.toInstant().toString();
        String insertableTestExpiryDateOne = insertedTestExpiryDateOne.toInstant().toString();
        String insertableCreatedAtOne = insertedCreatedAtOne.toInstant().toString();
        String insertableTestTypeEndTimestampOne = insertedTestTypeEndTimestampOne.toInstant().toString();
        String insertableTestEndTimestampOne = insertedTestEndTimestampOne.toInstant().toString();
        String insertableTestResultIdOne = UUID.randomUUID().toString();

        // Create alteration to add one more tech record to in the inserted data
        JsonPathAlteration alterationInsertOneVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationInsertOneSystemNo = new JsonPathAlteration("$.systemNumber", randomSystemNumber, "", "REPLACE");
        JsonPathAlteration alterationInsertOneNoOfAxles = new JsonPathAlteration("$.noOfAxles", noOfAxles, "", "REPLACE");
        JsonPathAlteration alterationInsertOneTestResultId = new JsonPathAlteration("$.testResultId", insertableTestResultIdOne, "", "REPLACE");
        JsonPathAlteration alterationInsertOneTestStartTimestamp = new JsonPathAlteration("$.testStartTimestamp", insertableTestStartTimestampOne, "", "REPLACE");
        JsonPathAlteration alterationInsertOneTestTypeStartTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeStartTimestamp", insertableTestTypeStartTimestampOne, "", "REPLACE");
        JsonPathAlteration alterationInsertOneLastUpdatedAt = new JsonPathAlteration("$.testTypes[0].lastUpdatedAt", insertableLastUpdatedAtOne, "", "REPLACE");
        JsonPathAlteration alterationInsertOneTestExpiryDateOne = new JsonPathAlteration("$.testTypes[0].testExpiryDate", insertableTestExpiryDateOne, "", "REPLACE");
        JsonPathAlteration alterationInsertOneCreatedAt = new JsonPathAlteration("$.testTypes[0].createdAt", insertableCreatedAtOne, "", "REPLACE");
        JsonPathAlteration alterationInsertOneTestTypeEndTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeEndTimestamp", insertableTestTypeEndTimestampOne, "", "REPLACE");
        JsonPathAlteration alterationInsertOneTestEndTimestamp = new JsonPathAlteration("$.testEndTimestamp", insertableTestEndTimestampOne, "", "REPLACE");
        JsonPathAlteration alterationInsertOneTestCode = new JsonPathAlteration("$.testTypes[0].testCode", insertedTestCodeOne, "", "REPLACE");
        JsonPathAlteration alterationInsertOneTestTypeId = new JsonPathAlteration("$.testTypes[0].testTypeId", insertedTestTypeIdOne, "", "REPLACE");
        JsonPathAlteration alterationInsertOneTestResult = new JsonPathAlteration("$.testTypes[0].testResult", insertedTestResultOne, "", "REPLACE");

        // Collate the list of alterations for the inserted record.
        List<JsonPathAlteration> insertAlterationsOne = new ArrayList<>(Arrays.asList(
                alterationInsertOneVin,
                alterationInsertOneSystemNo,
                alterationInsertOneNoOfAxles,
                alterationInsertOneTestResultId,
                alterationInsertOneTestStartTimestamp,
                alterationInsertOneTestTypeStartTimestamp,
                alterationInsertOneLastUpdatedAt,
                alterationInsertOneTestExpiryDateOne,
                alterationInsertOneCreatedAt,
                alterationInsertOneTestTypeEndTimestamp,
                alterationInsertOneTestEndTimestamp,
                alterationInsertOneTestCode,
                alterationInsertOneTestTypeId,
                alterationInsertOneTestResult
        ));

        if (isAnnualWithCertificate) {
            insertAlterationsOne.add(new JsonPathAlteration("$.testTypes[0]", "Annual With Certificate", "testTypeClassification", "ADD_FIELD"));
        }

        // Insert the first altered record
        String alteredJsonOne = GenericData.applyJsonAlterations(insertedTestResultRecord, insertAlterationsOne);
        testResultsSteps.insertRecordInDynamo(alteredJsonOne, "test-results");

        // Inserted expiryDate for test two
        DateTime insertedTestExpiryDateTwo = currentTimestamp.plusMonths(1).dayOfMonth().withMaximumValue();

        // Create inserted record Two.
        DateTime insertedTestStartTimestampTwo = insertedTestExpiryDateTwo.minusYears(1).minusMinutes(15);
        DateTime insertedTestTypeStartTimestampTwo = insertedTestExpiryDateTwo.minusYears(1).minusMinutes(10);
        DateTime insertedLastUpdatedAtTwo = insertedTestExpiryDateTwo.minusYears(1).minusMinutes(5);
        DateTime insertedCreatedAtTwo = insertedTestExpiryDateTwo.minusYears(1).minusMinutes(5);
        DateTime insertedTestTypeEndTimestampTwo = insertedTestExpiryDateTwo.minusYears(1).minusMinutes(5);
        DateTime insertedTestEndTimestampTwo = insertedTestExpiryDateTwo.minusYears(1);

        String insertableTestStartTimestampTwo = insertedTestStartTimestampTwo.toInstant().toString();
        String insertableTestTypeStartTimestampTwo = insertedTestTypeStartTimestampTwo.toInstant().toString();
        String insertableLastUpdatedAtTwo = insertedLastUpdatedAtTwo.toInstant().toString();
        String insertableTestExpiryDateTwo = insertedTestExpiryDateTwo.toInstant().toString();
        String insertableCreatedAtTwo = insertedCreatedAtTwo.toInstant().toString();
        String insertableTestTypeEndTimestampTwo = insertedTestTypeEndTimestampTwo.toInstant().toString();
        String insertableTestEndTimestampTwo = insertedTestEndTimestampTwo.toInstant().toString();
        String insertableTestResultIdTwo = UUID.randomUUID().toString();

        // Create alteration to add one more tech record to in the inserted data
        JsonPathAlteration alterationInsertTwoVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationInsertTwoSystemNo = new JsonPathAlteration("$.systemNumber", randomSystemNumber, "", "REPLACE");
        JsonPathAlteration alterationInsertTwoNoOfAxles = new JsonPathAlteration("$.noOfAxles", noOfAxles, "", "REPLACE");
        JsonPathAlteration alterationInsertTwoTestResultId = new JsonPathAlteration("$.testResultId", insertableTestResultIdTwo, "", "REPLACE");
        JsonPathAlteration alterationInsertTwoTestStartTimestamp = new JsonPathAlteration("$.testStartTimestamp", insertableTestStartTimestampTwo, "", "REPLACE");
        JsonPathAlteration alterationInsertTwoTestTypeStartTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeStartTimestamp", insertableTestTypeStartTimestampTwo, "", "REPLACE");
        JsonPathAlteration alterationInsertTwoLastUpdatedAt = new JsonPathAlteration("$.testTypes[0].lastUpdatedAt", insertableLastUpdatedAtTwo, "", "REPLACE");
        JsonPathAlteration alterationInsertTwoTestExpiryDateOne = new JsonPathAlteration("$.testTypes[0].testExpiryDate", insertableTestExpiryDateTwo, "", "REPLACE");
        JsonPathAlteration alterationInsertTwoCreatedAt = new JsonPathAlteration("$.testTypes[0].createdAt", insertableCreatedAtTwo, "", "REPLACE");
        JsonPathAlteration alterationInsertTwoTestTypeEndTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeEndTimestamp", insertableTestTypeEndTimestampTwo, "", "REPLACE");
        JsonPathAlteration alterationInsertTwoTestEndTimestamp = new JsonPathAlteration("$.testEndTimestamp", insertableTestEndTimestampTwo, "", "REPLACE");
        JsonPathAlteration alterationInsertTwoTestCode = new JsonPathAlteration("$.testTypes[0].testCode", insertedTestCodeTwo, "", "REPLACE");
        JsonPathAlteration alterationInsertTwoTestTypeId = new JsonPathAlteration("$.testTypes[0].testTypeId", insertedTestTypeIdTwo, "", "REPLACE");
        JsonPathAlteration alterationInsertTwoTestResult = new JsonPathAlteration("$.testTypes[0].testResult", insertedTestResultTwo, "", "REPLACE");

        // Collate the list of alterations for the inserted record.
        List<JsonPathAlteration> insertAlterationsTwo = new ArrayList<>(Arrays.asList(
                alterationInsertTwoVin,
                alterationInsertTwoSystemNo,
                alterationInsertTwoNoOfAxles,
                alterationInsertTwoTestResultId,
                alterationInsertTwoTestStartTimestamp,
                alterationInsertTwoTestTypeStartTimestamp,
                alterationInsertTwoLastUpdatedAt,
                alterationInsertTwoTestExpiryDateOne,
                alterationInsertTwoCreatedAt,
                alterationInsertTwoTestTypeEndTimestamp,
                alterationInsertTwoTestEndTimestamp,
                alterationInsertTwoTestCode,
                alterationInsertTwoTestTypeId,
                alterationInsertTwoTestResult
        ));

        if (isAnnualWithCertificate) {
            insertAlterationsTwo.add(new JsonPathAlteration("$.testTypes[0]", "Annual With Certificate", "testTypeClassification", "ADD_FIELD"));
        }

        // Insert the first altered record
        String alteredJsonTwo = GenericData.applyJsonAlterations(insertedTestResultRecord, insertAlterationsTwo);
        testResultsSteps.insertRecordInDynamo(alteredJsonTwo, "test-results");

        // Create submitted
        DateTime submittedTestStartTimestamp = currentTimestamp.minusMinutes(15);
        DateTime submittedTestTypeStartTimestamp = currentTimestamp.plusMinutes(10);
        DateTime submittedTypeEndTimestamp = currentTimestamp.plusMinutes(5);
        DateTime submittedEndTimestamp = currentTimestamp;

        String testStartTimestamp = submittedTestStartTimestamp.toInstant().toString();
        String testTypeStartTimestamp = submittedTestTypeStartTimestamp.toInstant().toString();
        String testTypeEndTimestamp = submittedTypeEndTimestamp.toInstant().toString();
        String testEndTimestamp = submittedEndTimestamp.toInstant().toString();

        // Expected recalculated expiry date
        String testExpiryDate = submittedEndTimestamp.plusYears(1).dayOfMonth().withMaximumValue().toInstant().toString();

        // Create alteration to add one more tech record to in the request body
        JsonPathAlteration alterationTestStartTimestamp = new JsonPathAlteration("$.testStartTimestamp", testStartTimestamp, "", "REPLACE");
        JsonPathAlteration alterationTestEndTimestamp = new JsonPathAlteration("$.testEndTimestamp", testEndTimestamp, "", "REPLACE");
        JsonPathAlteration alterationTestTypeStartTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeStartTimestamp", testTypeStartTimestamp, "", "REPLACE");
        JsonPathAlteration alterationTestTypeEndTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeEndTimestamp", testTypeEndTimestamp, "", "REPLACE");
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationSystemNo = new JsonPathAlteration("$.systemNumber", randomSystemNumber, "", "REPLACE");
        JsonPathAlteration alterationTestResultId = new JsonPathAlteration("$.testResultId", UUID.randomUUID().toString(), "", "REPLACE");
        JsonPathAlteration alterationTestName = new JsonPathAlteration("$.testTypes[0].name", postName, "", "REPLACE");
        JsonPathAlteration alterationTestTypeId = new JsonPathAlteration("$.testTypes[0].testTypeId", postTestTypeId, "", "REPLACE");
        JsonPathAlteration alterationTestTypeName = new JsonPathAlteration("$.testTypes[0].testTypeName", postTestTypeName, "", "REPLACE");
        JsonPathAlteration alterationTestResult = new JsonPathAlteration("$.testTypes[0].testResult", postTestResult, "", "REPLACE");
        JsonPathAlteration alterationNoOfAxles = new JsonPathAlteration("$.noOfAxles", noOfAxles, "", "REPLACE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(
                alterationTestStartTimestamp,
                alterationTestEndTimestamp,
                alterationTestTypeStartTimestamp,
                alterationTestTypeEndTimestamp,
                alterationVin,
                alterationSystemNo,
                alterationTestResultId,
                alterationTestName,
                alterationTestTypeId,
                alterationTestTypeName,
                alterationNoOfAxles,
                alterationTestResult
        ));

        // Printing the scenario details to the console
        System.out.println("\n******************************************************");
        System.out.println("Inserted testCode One:            " + insertedTestCodeOne);
        System.out.println("Inserted previous ExpiryDate One: " + insertedTestExpiryDateOne.toInstant().toString().substring(0,10));
        System.out.println("Inserted testCode Two:            " + insertedTestCodeTwo);
        System.out.println("Inserted previous ExpiryDate One: " + insertedTestExpiryDateTwo.toInstant().toString().substring(0,10));
        System.out.println("POST-ed testCode:                 " + postTestCode);
        System.out.println("Today:                            " + currentTimestamp.toInstant().toString().substring(0,10));
        System.out.println("expected ExpiryDate:              " + testExpiryDate.substring(0,10));
        System.out.println("******************************************************\n");

        // Post the results, together with any alterations, and verify that they are accepted.
        testResultsSteps.postVehicleTestResultsWithAlterations(postTestResultRecord, alterations);

        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");

        // Retrieve the created record, and verify that the fields are present.
        testResultsSteps.getTestResultsBetweenDate(randomSystemNumber, submittedTestStartTimestamp.minusMinutes(10).toInstant().toString(), submittedEndTimestamp.plusMinutes(10).toInstant().toString());
        testResultsSteps.statusCodeShouldBe(200);

        // Verify testCode field has the expected value
        testResultsSteps.valueForFieldInPathShouldBe("[0].testTypes[0].testCode", postTestCode);

        // Verify testExpiryDate field has the expected value
        testResultsSteps.valueForFieldInPathShouldStartWith("[0].testTypes[0].testExpiryDate", testExpiryDate.substring(0, 10));

    }

    @WithTag("In_Test")
    @Title("CVSB-11396 - As a VSA I want to be able to submit a test for which the expiry date is automatically calculated based on the previous test types history  - invalid testCode expired - HGV")
    @Test
    public void testResultsMostRecentExpiryInvalidExpiredHgv() {

        // Read the base INSERT test result JSON.
        String insertedTestResultRecord = GenericData.readJsonValueFromFile("test-results_insert_two_expiry_date_hgv_11396.json", "$");
        // Read the base POST test result JSON.
        String postTestResultRecord = GenericData.readJsonValueFromFile("test-results_post_expiry_date_hgv_11396.json", "$");

        DateTime currentTimestamp = DateTime.now().withZone(DateTimeZone.UTC);

        // Generate a random VIN and a random System Number
        String randomVin = GenericData.generateRandomVin();
        String randomSystemNumber = GenericData.generateRandomSystemNumber();

        // Inserted expiryDate for test one
        DateTime insertedTestExpiryDateOne = currentTimestamp.plusMonths(1).dayOfMonth().withMaximumValue();

        // Create inserted record One.
        DateTime insertedTestStartTimestampOne = insertedTestExpiryDateOne.minusYears(1).minusMinutes(15);
        DateTime insertedTestTypeStartTimestampOne = insertedTestExpiryDateOne.minusYears(1).minusMinutes(10);
        DateTime insertedLastUpdatedAtOne = insertedTestExpiryDateOne.minusYears(1).minusMinutes(5);
        DateTime insertedCreatedAtOne = insertedTestExpiryDateOne.minusYears(1).minusMinutes(5);
        DateTime insertedTestTypeEndTimestampOne = insertedTestExpiryDateOne.minusYears(1).minusMinutes(5);
        DateTime insertedTestEndTimestampOne = insertedTestExpiryDateOne.minusYears(1);

        String insertableTestStartTimestampOne = insertedTestStartTimestampOne.toInstant().toString();
        String insertableTestTypeStartTimestampOne = insertedTestTypeStartTimestampOne.toInstant().toString();
        String insertableLastUpdatedAtOne = insertedLastUpdatedAtOne.toInstant().toString();
        String insertableTestExpiryDateOne = insertedTestExpiryDateOne.toInstant().toString();
        String insertableCreatedAtOne = insertedCreatedAtOne.toInstant().toString();
        String insertableTestTypeEndTimestampOne = insertedTestTypeEndTimestampOne.toInstant().toString();
        String insertableTestEndTimestampOne = insertedTestEndTimestampOne.toInstant().toString();
        String insertableTestResultIdOne = UUID.randomUUID().toString();

        // Create alteration to add one more tech record to in the inserted data
        JsonPathAlteration alterationInsertOneVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationInsertOneSystemNo = new JsonPathAlteration("$.systemNumber", randomSystemNumber, "", "REPLACE");
        JsonPathAlteration alterationInsertOneNoOfAxles = new JsonPathAlteration("$.noOfAxles", noOfAxles, "", "REPLACE");
        JsonPathAlteration alterationInsertOneTestResultId = new JsonPathAlteration("$.testResultId", insertableTestResultIdOne, "", "REPLACE");
        JsonPathAlteration alterationInsertOneTestStartTimestamp = new JsonPathAlteration("$.testStartTimestamp", insertableTestStartTimestampOne, "", "REPLACE");
        JsonPathAlteration alterationInsertOneTestTypeStartTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeStartTimestamp", insertableTestTypeStartTimestampOne, "", "REPLACE");
        JsonPathAlteration alterationInsertOneLastUpdatedAt = new JsonPathAlteration("$.testTypes[0].lastUpdatedAt", insertableLastUpdatedAtOne, "", "REPLACE");
        JsonPathAlteration alterationInsertOneTestExpiryDateOne = new JsonPathAlteration("$.testTypes[0].testExpiryDate", insertableTestExpiryDateOne, "", "REPLACE");
        JsonPathAlteration alterationInsertOneCreatedAt = new JsonPathAlteration("$.testTypes[0].createdAt", insertableCreatedAtOne, "", "REPLACE");
        JsonPathAlteration alterationInsertOneTestTypeEndTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeEndTimestamp", insertableTestTypeEndTimestampOne, "", "REPLACE");
        JsonPathAlteration alterationInsertOneTestEndTimestamp = new JsonPathAlteration("$.testEndTimestamp", insertableTestEndTimestampOne, "", "REPLACE");
        JsonPathAlteration alterationInsertOneTestCode = new JsonPathAlteration("$.testTypes[0].testCode", insertedTestCodeOne, "", "REPLACE");
        JsonPathAlteration alterationInsertOneTestTypeId = new JsonPathAlteration("$.testTypes[0].testTypeId", insertedTestTypeIdOne, "", "REPLACE");
        JsonPathAlteration alterationInsertOneTestResult = new JsonPathAlteration("$.testTypes[0].testResult", insertedTestResultOne, "", "REPLACE");

        // Collate the list of alterations for the inserted record.
        List<JsonPathAlteration> insertAlterationsOne = new ArrayList<>(Arrays.asList(
                alterationInsertOneVin,
                alterationInsertOneSystemNo,
                alterationInsertOneNoOfAxles,
                alterationInsertOneTestResultId,
                alterationInsertOneTestStartTimestamp,
                alterationInsertOneTestTypeStartTimestamp,
                alterationInsertOneLastUpdatedAt,
                alterationInsertOneTestExpiryDateOne,
                alterationInsertOneCreatedAt,
                alterationInsertOneTestTypeEndTimestamp,
                alterationInsertOneTestEndTimestamp,
                alterationInsertOneTestCode,
                alterationInsertOneTestTypeId,
                alterationInsertOneTestResult
        ));

        if (isAnnualWithCertificate) {
            insertAlterationsOne.add(new JsonPathAlteration("$.testTypes[0]", "Annual With Certificate", "testTypeClassification", "ADD_FIELD"));
        }

        // Insert the first altered record
        String alteredJsonOne = GenericData.applyJsonAlterations(insertedTestResultRecord, insertAlterationsOne);
        testResultsSteps.insertRecordInDynamo(alteredJsonOne, "test-results");

        // Inserted expiryDate for test two
        DateTime insertedTestExpiryDateTwo = currentTimestamp.minusMonths(1).dayOfMonth().withMaximumValue();

        // Create inserted record Two.
        DateTime insertedTestStartTimestampTwo = insertedTestExpiryDateTwo.minusYears(1).minusMinutes(15);
        DateTime insertedTestTypeStartTimestampTwo = insertedTestExpiryDateTwo.minusYears(1).minusMinutes(10);
        DateTime insertedLastUpdatedAtTwo = insertedTestExpiryDateTwo.minusYears(1).minusMinutes(5);
        DateTime insertedCreatedAtTwo = insertedTestExpiryDateTwo.minusYears(1).minusMinutes(5);
        DateTime insertedTestTypeEndTimestampTwo = insertedTestExpiryDateTwo.minusYears(1).minusMinutes(5);
        DateTime insertedTestEndTimestampTwo = insertedTestExpiryDateTwo.minusYears(1);

        String insertableTestStartTimestampTwo = insertedTestStartTimestampTwo.toInstant().toString();
        String insertableTestTypeStartTimestampTwo = insertedTestTypeStartTimestampTwo.toInstant().toString();
        String insertableLastUpdatedAtTwo = insertedLastUpdatedAtTwo.toInstant().toString();
        String insertableTestExpiryDateTwo = insertedTestExpiryDateTwo.toInstant().toString();
        String insertableCreatedAtTwo = insertedCreatedAtTwo.toInstant().toString();
        String insertableTestTypeEndTimestampTwo = insertedTestTypeEndTimestampTwo.toInstant().toString();
        String insertableTestEndTimestampTwo = insertedTestEndTimestampTwo.toInstant().toString();
        String insertableTestResultIdTwo = UUID.randomUUID().toString();

        // Create alteration to add one more tech record to in the inserted data
        JsonPathAlteration alterationInsertTwoVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationInsertTwoSystemNo = new JsonPathAlteration("$.systemNumber", randomSystemNumber, "", "REPLACE");
        JsonPathAlteration alterationInsertTwoNoOfAxles = new JsonPathAlteration("$.noOfAxles", noOfAxles, "", "REPLACE");
        JsonPathAlteration alterationInsertTwoTestResultId = new JsonPathAlteration("$.testResultId", insertableTestResultIdTwo, "", "REPLACE");
        JsonPathAlteration alterationInsertTwoTestStartTimestamp = new JsonPathAlteration("$.testStartTimestamp", insertableTestStartTimestampTwo, "", "REPLACE");
        JsonPathAlteration alterationInsertTwoTestTypeStartTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeStartTimestamp", insertableTestTypeStartTimestampTwo, "", "REPLACE");
        JsonPathAlteration alterationInsertTwoLastUpdatedAt = new JsonPathAlteration("$.testTypes[0].lastUpdatedAt", insertableLastUpdatedAtTwo, "", "REPLACE");
        JsonPathAlteration alterationInsertTwoTestExpiryDateOne = new JsonPathAlteration("$.testTypes[0].testExpiryDate", insertableTestExpiryDateTwo, "", "REPLACE");
        JsonPathAlteration alterationInsertTwoCreatedAt = new JsonPathAlteration("$.testTypes[0].createdAt", insertableCreatedAtTwo, "", "REPLACE");
        JsonPathAlteration alterationInsertTwoTestTypeEndTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeEndTimestamp", insertableTestTypeEndTimestampTwo, "", "REPLACE");
        JsonPathAlteration alterationInsertTwoTestEndTimestamp = new JsonPathAlteration("$.testEndTimestamp", insertableTestEndTimestampTwo, "", "REPLACE");
        JsonPathAlteration alterationInsertTwoTestCode = new JsonPathAlteration("$.testTypes[0].testCode", insertedTestCodeTwo, "", "REPLACE");
        JsonPathAlteration alterationInsertTwoTestTypeId = new JsonPathAlteration("$.testTypes[0].testTypeId", insertedTestTypeIdTwo, "", "REPLACE");
        JsonPathAlteration alterationInsertTwoTestResult = new JsonPathAlteration("$.testTypes[0].testResult", insertedTestResultTwo, "", "REPLACE");

        // Collate the list of alterations for the inserted record.
        List<JsonPathAlteration> insertAlterationsTwo = new ArrayList<>(Arrays.asList(
                alterationInsertTwoVin,
                alterationInsertTwoSystemNo,
                alterationInsertTwoNoOfAxles,
                alterationInsertTwoTestResultId,
                alterationInsertTwoTestStartTimestamp,
                alterationInsertTwoTestTypeStartTimestamp,
                alterationInsertTwoLastUpdatedAt,
                alterationInsertTwoTestExpiryDateOne,
                alterationInsertTwoCreatedAt,
                alterationInsertTwoTestTypeEndTimestamp,
                alterationInsertTwoTestEndTimestamp,
                alterationInsertTwoTestCode,
                alterationInsertTwoTestTypeId,
                alterationInsertTwoTestResult
        ));

        if (isAnnualWithCertificate) {
            insertAlterationsTwo.add(new JsonPathAlteration("$.testTypes[0]", "Annual With Certificate", "testTypeClassification", "ADD_FIELD"));
        }

        // Insert the first altered record
        String alteredJsonTwo = GenericData.applyJsonAlterations(insertedTestResultRecord, insertAlterationsTwo);
        testResultsSteps.insertRecordInDynamo(alteredJsonTwo, "test-results");

        // Create submitted
        DateTime submittedTestStartTimestamp = currentTimestamp.minusMinutes(15);
        DateTime submittedTestTypeStartTimestamp = currentTimestamp.plusMinutes(10);
        DateTime submittedTypeEndTimestamp = currentTimestamp.plusMinutes(5);
        DateTime submittedEndTimestamp = currentTimestamp;

        String testStartTimestamp = submittedTestStartTimestamp.toInstant().toString();
        String testTypeStartTimestamp = submittedTestTypeStartTimestamp.toInstant().toString();
        String testTypeEndTimestamp = submittedTypeEndTimestamp.toInstant().toString();
        String testEndTimestamp = submittedEndTimestamp.toInstant().toString();

        // Expected recalculated expiry date
        String testExpiryDate = insertedTestExpiryDateOne.plusYears(1).toInstant().toString();

        // Create alteration to add one more tech record to in the request body
        JsonPathAlteration alterationTestStartTimestamp = new JsonPathAlteration("$.testStartTimestamp", testStartTimestamp, "", "REPLACE");
        JsonPathAlteration alterationTestEndTimestamp = new JsonPathAlteration("$.testEndTimestamp", testEndTimestamp, "", "REPLACE");
        JsonPathAlteration alterationTestTypeStartTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeStartTimestamp", testTypeStartTimestamp, "", "REPLACE");
        JsonPathAlteration alterationTestTypeEndTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeEndTimestamp", testTypeEndTimestamp, "", "REPLACE");
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationSystemNo = new JsonPathAlteration("$.systemNumber", randomSystemNumber, "", "REPLACE");
        JsonPathAlteration alterationTestResultId = new JsonPathAlteration("$.testResultId", UUID.randomUUID().toString(), "", "REPLACE");
        JsonPathAlteration alterationTestName = new JsonPathAlteration("$.testTypes[0].name", postName, "", "REPLACE");
        JsonPathAlteration alterationTestTypeId = new JsonPathAlteration("$.testTypes[0].testTypeId", postTestTypeId, "", "REPLACE");
        JsonPathAlteration alterationTestTypeName = new JsonPathAlteration("$.testTypes[0].testTypeName", postTestTypeName, "", "REPLACE");
        JsonPathAlteration alterationTestResult = new JsonPathAlteration("$.testTypes[0].testResult", postTestResult, "", "REPLACE");
        JsonPathAlteration alterationNoOfAxles = new JsonPathAlteration("$.noOfAxles", noOfAxles, "", "REPLACE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(
                alterationTestStartTimestamp,
                alterationTestEndTimestamp,
                alterationTestTypeStartTimestamp,
                alterationTestTypeEndTimestamp,
                alterationVin,
                alterationSystemNo,
                alterationTestResultId,
                alterationTestName,
                alterationTestTypeId,
                alterationTestTypeName,
                alterationNoOfAxles,
                alterationTestResult
        ));

        // Printing the scenario details to the console
        System.out.println("\n******************************************************");
        System.out.println("Inserted testCode One:            " + insertedTestCodeOne);
        System.out.println("Inserted previous ExpiryDate One: " + insertedTestExpiryDateOne.toInstant().toString().substring(0,10));
        System.out.println("Inserted testCode Two:            " + insertedTestCodeTwo);
        System.out.println("Inserted previous ExpiryDate One: " + insertedTestExpiryDateTwo.toInstant().toString().substring(0,10));
        System.out.println("POST-ed testCode:                 " + postTestCode);
        System.out.println("Today:                            " + currentTimestamp.toInstant().toString().substring(0,10));
        System.out.println("expected ExpiryDate:              " + testExpiryDate.substring(0,10));
        System.out.println("******************************************************\n");

        // Post the results, together with any alterations, and verify that they are accepted.
        testResultsSteps.postVehicleTestResultsWithAlterations(postTestResultRecord, alterations);

        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");

        // Retrieve the created record, and verify that the fields are present.
        testResultsSteps.getTestResultsBetweenDate(randomSystemNumber, submittedTestStartTimestamp.minusMinutes(10).toInstant().toString(), submittedEndTimestamp.plusMinutes(10).toInstant().toString());
        testResultsSteps.statusCodeShouldBe(200);

        // Verify testCode field has the expected value
        testResultsSteps.valueForFieldInPathShouldBe("[0].testTypes[0].testCode", postTestCode);

        // Verify testExpiryDate field has the expected value
        testResultsSteps.valueForFieldInPathShouldStartWith("[0].testTypes[0].testExpiryDate", testExpiryDate.substring(0, 10));

    }

}

