package testresults;

import data.GenericData;
import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.core.annotations.WithTags;
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

@WithTag("In_Test")
@RunWith(SerenityParameterizedRunner.class)
public class PostTestResultsExpiryDateLogicPsv {

    @Steps
    TestResultsSteps testResultsSteps;

    @TestData
    public static Collection<Object[]> testData(){
        return Arrays.asList(new Object[][]{
                {"Annual test", "Annual test", "1", "large", "rigid", "pass", 1, "aal", true},
                {"Annual test", "Annual test", "1", "large", "rigid", "pass", 1, "aal", false},
//                {"Annual test", "Annual test", "1", "small", "rigid", "pass", 1, "aas"},
//                {"Annual test", "Annual test", "1", "large", "articulated", "pass", 1, "adl"},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "large", "rigid", "pass", 1, "wdl"},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "small", "rigid", "pass", 1, "wds"},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "large", "rigid", "pass", 1, "wbl"},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "small", "rigid", "pass", 1, "wbs"},
//                {"Any PSV", "Paid retest", "7", "large", "articulated", "pass", 1, "rhl"},
//                {"Any PSV", "Paid retest", "7", "small", "rigid", "pass", 1, "rps"},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "large", "rigid", "pass", 1, "whl"},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "small", "rigid", "pass", 1, "whs"},
//                {"Any PSV", "Part-paid retest", "10", "large", "articulated", "pass", 1, "rgl"},
//                {"Any PSV", "Part-paid retest", "10", "large", "rigid", "pass", 1, "rsl"},
//                {"Any PSV", "Part-paid retest", "10", "small", "rigid", "pass", 1, "rss"},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "large", "rigid", "pass", 1, "p1l"},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "small", "rigid", "pass", 1, "p1s"},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "large", "rigid", "pass", 1, "p8l"},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "small", "rigid", "pass", 1, "p8s"},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "large", "rigid", "pass", 1, "p6l"},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "small", "rigid", "pass", 1, "p6s"},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "small", "rigid", "pass", 1, "wis"},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "large", "rigid", "pass", 1, "wil"},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "large", "rigid", "pass", 1, "wfl"},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "small", "rigid", "pass", 1, "wfs"},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "large", "rigid", "pass", 1, "wel"},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "small", "rigid", "pass", 1, "wes"},
//
//                {"Annual test", "Annual test", "1", "large", "rigid", "prs", 1, "aal"},
//                {"Annual test", "Annual test", "1", "small", "rigid", "prs", 1, "aas"},
//                {"Annual test", "Annual test", "1", "large", "articulated", "prs", 1, "adl"},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "large", "rigid", "prs", 1, "wdl"},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "small", "rigid", "prs", 1, "wds"},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "large", "rigid", "prs", 1, "wbl"},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "small", "rigid", "prs", 1, "wbs"},
//                {"Any PSV", "Paid retest", "7", "large", "articulated", "prs", 1, "rhl"},
//                {"Any PSV", "Paid retest", "7", "small", "rigid", "prs", 1, "rps"},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "large", "rigid", "prs", 1, "whl"},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "small", "rigid", "prs", 1, "whs"},
//                {"Any PSV", "Part-paid retest", "10", "large", "articulated", "prs", 1, "rgl"},
//                {"Any PSV", "Part-paid retest", "10", "large", "rigid", "prs", 1, "rsl"},
//                {"Any PSV", "Part-paid retest", "10", "small", "rigid", "prs", 1, "rss"},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "large", "rigid", "prs", 1, "p1l"},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "small", "rigid", "prs", 1, "p1s"},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "large", "rigid", "prs", 1, "p8l"},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "small", "rigid", "prs", 1, "p8s"},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "large", "rigid", "prs", 1, "p6l"},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "small", "rigid", "prs", 1, "p6s"},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "small", "rigid", "prs", 1, "wis"},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "large", "rigid", "prs", 1, "wil"},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "large", "rigid", "prs", 1, "wfl"},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "small", "rigid", "prs", 1, "wfs"},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "large", "rigid", "prs", 1, "wel"},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "small", "rigid", "prs", 1, "wes"},
        });
    }

    private String name;
    private String testTypeName;
    private String testTypeId;
    private String vehicleSize;
    private String vehicleConfiguration;
    private String testResult;
    private String testCode;
    private boolean isAnnualWithCertificate;

    //sets the firstUseDate / regnDate one year before the current date, plus/minus one day
    private int dayOffset;

    public PostTestResultsExpiryDateLogicPsv(String name, String testTypeName, String testTypeId, String vehicleSize, String vehicleConfiguration, String testResult, int dayOffset, String testCode, boolean isAnnualWithCertificate) {
        this.name = name;
        this.testTypeName = testTypeName;
        this.testTypeId = testTypeId;
        this.vehicleSize = vehicleSize;
        this.vehicleConfiguration = vehicleConfiguration;
        this.testResult = testResult;
        this.dayOffset = dayOffset;
        this.testCode = testCode;
        this.isAnnualWithCertificate = isAnnualWithCertificate;
    }

//    @WithTag("Expiry_Dates")
//    @Title("CVSB-9187 - TC1 - AC1 - FIRST TEST/FIRST TEST RETEST CONDUCTED AFTER THE ANNIVERSARY DATE - TRL - If no testExpiryDate")
//    @Test
//    public void testResultsFirstTestExpiryTrl() {
//
//        // Read the base test result JSON.
//        String testResultRecord = GenericData.readJsonValueFromFile("test-results_post_expiry_date_psv_8684.json", "$");
//
//        DateTime submittedTestStartTimestamp = DateTime.now().withZone(DateTimeZone.UTC);
//        DateTime submittedTestTypeStartTimestamp = submittedTestStartTimestamp.plusMinutes(5);
//        DateTime submittedTypeEndTimestamp = submittedTestTypeStartTimestamp.plusMinutes(10);
//        DateTime submittedEndTimestamp = submittedTypeEndTimestamp.plusMinutes(5);
//
//        // Create alteration to add one more tech record to in the request body
//        String firstUseDate = submittedEndTimestamp.toDateTime().minusYears(1).plusDays(dayOffset).toString("yyyy-MM-dd");
//        String testStartTimestamp = submittedTestStartTimestamp.toInstant().toString();
//        String testTypeStartTimestamp = submittedTestTypeStartTimestamp.toInstant().toString();
//
//        String testTypeEndTimestamp = submittedTypeEndTimestamp.toInstant().toString();
//        String testEndTimestamp = submittedTypeEndTimestamp.toInstant().toString();
//        String testExpectedDate = submittedEndTimestamp.plusYears(1).minusDays(1).toInstant().toString();
//        String randomVin = GenericData.generateRandomVin();
//        String randomTestResultId = RandomStringUtils.randomNumeric(5);
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
//        JsonPathAlteration alterationVehicleSize = new JsonPathAlteration("$.vehicleSize", vehicleSize,"","REPLACE");
//        JsonPathAlteration alterationVehicleConfiguration = new JsonPathAlteration("$.vehicleConfiguration", vehicleConfiguration,"","REPLACE");
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
//                alterationVehicleSize,
//                alterationVehicleConfiguration,
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
//        // Verify testAnniversaryDate field has the expected value
////        testResultsSteps.valueForFieldInPathShouldBe("[0].testTypes[0].testTypeStartTimestamp", expectedTestTypeStartTimestamp);
//
//        // Verify testExpiryDate field has the expected value
////        testResultsSteps.valueForFieldInPathShouldBe("[0].testTypes[0].testExpiryDate", testExpectedDate);
//        testResultsSteps.valueForFieldInPathShouldStartWith("[0].testTypes[0].testExpiryDate", testExpectedDate.substring(0,10));
//
//    }

    @WithTag("In_Test")
    @Title("CVSB-9187 - TC1 - AC1 - FIRST TEST/FIRST TEST RETEST CONDUCTED AFTER THE ANNIVERSARY DATE - TRL - testTypeEndTimestamp (date only) is more than 2 months before previous testExpiryDate")
    @Test
    public void testResultsFirstTestExpiryTrlGenerated() {

        // Read the base INSERT test result JSON.
        String insertedTestResultRecord = GenericData.readJsonValueFromFile("test-results_insert_expiry_date_psv_8684.json", "$");
        // Read the base POST test result JSON.
        String postTestResultRecord = GenericData.readJsonValueFromFile("test-results_post_expiry_date_psv_8684.json", "$");

        DateTime currentTimestamp = DateTime.now().withZone(DateTimeZone.UTC);

        //
        String randomVin = GenericData.generateRandomVin();
        String randomTestResultId = RandomStringUtils.randomNumeric(5);

        // Create inserted record.
        DateTime insertedTestStartTimestamp = currentTimestamp.minusYears(1).minusMonths(2).plusDays(1).minusMinutes(15);
        DateTime insertedTestTypeStartTimestamp = currentTimestamp.minusYears(1).minusMonths(2).plusDays(1).minusMinutes(10);
        DateTime insertedLastUpdatedAt = currentTimestamp.minusYears(1).minusMonths(2).plusDays(1).minusMinutes(5);
        DateTime insertedTestAnniversaryDate = currentTimestamp.minusMonths(2).plusDays(1);
        DateTime insertedTestExpiryDate = currentTimestamp.minusMonths(2).plusDays(1);
        DateTime insertedCreatedAt = currentTimestamp.minusYears(1).minusMonths(2).plusDays(1).minusMinutes(5);
        DateTime insertedTestTypeEndTimestamp = currentTimestamp.minusYears(1).minusMonths(2).plusDays(1).minusMinutes(5);
        DateTime insertedTestEndTimestamp = currentTimestamp.minusYears(1).minusMonths(2).plusDays(1);

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
        JsonPathAlteration alterationInsertTestStartTimestamp = new JsonPathAlteration("$.testStartTimestamp", insertableTestStartTimestamp, "", "REPLACE");
        JsonPathAlteration alterationInsertTestTypeStartTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeStartTimestamp", insertableTestTypeStartTimestamp, "", "REPLACE");
        JsonPathAlteration alterationInsertLastUpdatedAt = new JsonPathAlteration("$.testTypes[0].lastUpdatedAt", insertableLastUpdatedAt, "", "REPLACE");
        JsonPathAlteration alterationInsertTestAnniversaryDate = new JsonPathAlteration("$.testTypes[0].testAnniversaryDate", insertableTestAnniversaryDate, "", "REPLACE");
        JsonPathAlteration alterationInsertTestExpiryDate = new JsonPathAlteration("$.testTypes[0].testExpiryDate", insertableTestExpiryDate, "", "REPLACE");
        JsonPathAlteration alterationInsertCreatedAt = new JsonPathAlteration("$.testTypes[0].createdAt", insertableCreatedAt, "", "REPLACE");
        JsonPathAlteration alterationInsertTestTypeEndTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeEndTimestamp", insertableTestTypeEndTimestamp, "", "REPLACE");
        JsonPathAlteration alterationInsertTestEndTimestamp = new JsonPathAlteration("$.testEndTimestamp", insertableTestEndTimestamp, "", "REPLACE");

        // Collate the list of alterations for the inserted record.
        List<JsonPathAlteration> insertAlterations = new ArrayList<>(Arrays.asList(
                alterationInsertVin,
                alterationInsertTestStartTimestamp,
                alterationInsertTestTypeStartTimestamp,
                alterationInsertLastUpdatedAt,
                alterationInsertTestAnniversaryDate,
                alterationInsertTestExpiryDate,
                alterationInsertCreatedAt,
                alterationInsertTestTypeEndTimestamp,
                alterationInsertTestEndTimestamp
        ));

        if(isAnnualWithCertificate){
            insertAlterations.add(new JsonPathAlteration("$.testTypes[0]", "Annual With Certification", "testTypeClassification", "ADD_FIELD"));
        }

        // Insert the altered record
        String alteredJson = GenericData.applyJsonAlterations(insertedTestResultRecord, insertAlterations);
        testResultsSteps.insertRecordInDynamo(alteredJson, "test-results");

        // Create submitted
        DateTime submittedTestStartTimestamp = currentTimestamp.minusMinutes(15);
        DateTime submittedTestTypeStartTimestamp = currentTimestamp.plusMinutes(10);
        DateTime submittedTypeEndTimestamp = currentTimestamp.plusMinutes(5);
        DateTime submittedEndTimestamp = currentTimestamp;

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
        JsonPathAlteration alterationTestResultId = new JsonPathAlteration("$.testResultId", randomTestResultId, "", "REPLACE");

        JsonPathAlteration alterationTestName = new JsonPathAlteration("$.testTypes[0].name", name,"","REPLACE");
        JsonPathAlteration alterationTestTypeId = new JsonPathAlteration("$.testTypes[0].testTypeId", testTypeId,"","REPLACE");
        JsonPathAlteration alterationTestTypeName = new JsonPathAlteration("$.testTypes[0].testTypeName", testTypeName,"","REPLACE");
        JsonPathAlteration alterationTestResult = new JsonPathAlteration("$.testTypes[0].testResult", testResult,"","REPLACE");
        JsonPathAlteration alterationVehicleSize = new JsonPathAlteration("$.vehicleSize", vehicleSize,"","REPLACE");
        JsonPathAlteration alterationVehicleConfiguration = new JsonPathAlteration("$.vehicleConfiguration", vehicleConfiguration,"","REPLACE");

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
                alterationTestResult
        ));

        // Post the results, together with any alterations, and verify that they are accepted.
        testResultsSteps.postVehicleTestResultsWithAlterations(postTestResultRecord, alterations);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");

        // Retrieve the created record, and verify that the fields are present.
        testResultsSteps.getTestResults(randomVin);
        testResultsSteps.statusCodeShouldBe(200);

        // Verify testCode field has the expected value
        testResultsSteps.valueForFieldInPathShouldBe("[0].testTypes[0].testCode", testCode);

//        // Verify testAnniversaryDate field has the expected value
//        testResultsSteps.valueForFieldInPathShouldStartWith("[0].testTypes[0].testAnniversaryDate", testExpectedDate.substring(0,10));

        // Verify testExpiryDate field has the expected value
        testResultsSteps.valueForFieldInPathShouldStartWith("[0].testTypes[0].testExpiryDate", testExpectedDate.substring(0,10));

    }

}
