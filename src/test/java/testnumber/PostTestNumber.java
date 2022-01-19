package testnumber;

import clients.util.ToTypeConvertor;
import clients.util.testresult.TestResultsLevel;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import data.GenericData;
import data.TestResultsData;
import model.testresults.TestResults;
import model.testresults.TestResultsStatus;
import model.testresults.TestTypes;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import net.thucydides.core.annotations.WithTag;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import steps.TestResultsSteps;
import util.DataUtil;
import util.JsonPathAlteration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static util.DataUtil.generateRandomExcludingValues;

@WithTag("In_test")
@RunWith(SerenityRunner.class)
public class PostTestNumber {

    @Steps
    TestResultsSteps testResultsSteps;
    private TestResults.Builder vehicleSubmittedData = TestResultsData.buildTestResultsSubmittedData();
    private TestResults.Builder vehicleSubmittedDataTwo = TestResultsData.buildTestResultsSubmittedData();
    private TestResults.Builder vehicleCancelledData = TestResultsData.buildTestResultsCancelledData();

    @Before
    @Title("warm up test")
    @Test
    public void testResultsWarmUpTest() {
        testResultsSteps.postTestResults(vehicleCancelledData.build());
    }

    @Title("CVSB-2157/CVSB-3287 AC B1. VSA submits test results where at least one test type has test type classification 'Annual With Certificate' and the test type result is PASSED (testNumber is generated)")
    @Test
    public void validTestNumberGeneratedForAnnualWithCertificate() {

        String testResultRecord = GenericData.readJsonValueFromFile("test-results_LEC_PSV.json", "$");

        // Create alteration to add one more tech record to in the request body
        String randomSystemNumber = GenericData.generateRandomSystemNumber();
        String randomVin = GenericData.generateRandomVin();
        String randomTestResultId = UUID.randomUUID().toString();
        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$.systemNumber", randomSystemNumber, "", "REPLACE");
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationTestResultId = new JsonPathAlteration("$.testResultId", randomTestResultId, "", "REPLACE");
        JsonPathAlteration alterationTestName = new JsonPathAlteration("$.testTypes[0].name", "Retest", "", "REPLACE");
        JsonPathAlteration alterationTestTypeName = new JsonPathAlteration("$.testTypes[0].testTypeName", "Part-paid retest", "", "REPLACE");
        JsonPathAlteration alterationTestTypeId = new JsonPathAlteration("$.testTypes[0].testTypeId", "1", "", "REPLACE");


        // Collate the list of alterations.
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(
                alterationVin,
                alterationSystemNumber,
                alterationTestName,
                alterationTestTypeName,
                alterationTestTypeId,
                alterationTestResultId));

        // Post the results, together with any alterations, and verify that they are accepted.
        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, alterations);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");

        testResultsSteps.getTestResults(randomSystemNumber, TestResultsStatus.SUBMITTED);
        testResultsSteps.statusCodeShouldBe(200);
        testResultsSteps.valueForFieldInPathShouldBe("[0].vin",randomVin);
        testResultsSteps.valueForFieldInPathShouldBe("[0].systemNumber", randomSystemNumber);
        testResultsSteps.validateTestNumberIsNotNull();
    }

    @Title("CVSB-2157/CVSB-3278 AC B3. VSA submits test results which contain an LEC Test Type (testNumber generated)")
    @Test
    public void validTestNumberGeneratedForLecTestType() {

        String testResultRecord = GenericData.readJsonValueFromFile("test-results_LEC_PSV.json", "$");

        // Create alteration to add one more tech record to in the request body
        String randomSystemNumber = GenericData.generateRandomSystemNumber();
        String randomVin = GenericData.generateRandomVin();
        String randomTestResultId = UUID.randomUUID().toString();
        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$.systemNumber", randomSystemNumber, "", "REPLACE");
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationTestResultId = new JsonPathAlteration("$.testResultId", randomTestResultId, "", "REPLACE");
        JsonPathAlteration alterationTestName = new JsonPathAlteration("$.testTypes[0].name", "Technical test", "", "REPLACE");
        JsonPathAlteration alterationTestTypeName = new JsonPathAlteration("$.testTypes[0].testTypeName", "Low Emissions Certificate (LEC) with annual test", "", "REPLACE");
        JsonPathAlteration alterationTestTypeId = new JsonPathAlteration("$.testTypes[0].testTypeId", "39", "", "REPLACE");


        // Collate the list of alterations.
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(
                alterationVin,
                alterationSystemNumber,
                alterationTestName,
                alterationTestTypeName,
                alterationTestTypeId,
                alterationTestResultId));

        // Post the results, together with any alterations, and verify that they are accepted.
        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, alterations);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");

        testResultsSteps.getTestResults(randomSystemNumber, TestResultsStatus.SUBMITTED);
        testResultsSteps.statusCodeShouldBe(200);
        testResultsSteps.validateTestNumberIsNotNull();
    }

    @Title("CVSB-2157/CVSB-3291 AC B1. VSA submits test results where at least one test type has test type classification 'Annual With Certificate' and the test type result is PRS (testNumber is generated)")
    @Test
    public void validTestNumberGeneratedForAnnualWithCertificatePRS() {


        String testResultRecord = GenericData.readJsonValueFromFile("test-results_LEC_PSV.json", "$");

        // Create alteration to add one more tech record to in the request body
        String randomSystemNumber = GenericData.generateRandomSystemNumber();
        String randomVin = GenericData.generateRandomVin();
        String randomTestResultId = UUID.randomUUID().toString();
        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$.systemNumber", randomSystemNumber, "", "REPLACE");
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationTestResultId = new JsonPathAlteration("$.testResultId", randomTestResultId, "", "REPLACE");
        JsonPathAlteration alterationTestName = new JsonPathAlteration("$.testTypes[0].name", "Retest", "", "REPLACE");
        JsonPathAlteration alterationTestTypeName = new JsonPathAlteration("$.testTypes[0].testTypeName", "Part-paid retest", "", "REPLACE");
        JsonPathAlteration alterationTestTypeId = new JsonPathAlteration("$.testTypes[0].testTypeId", "1", "", "REPLACE");
        JsonPathAlteration alterationTestResult = new JsonPathAlteration("$.testTypes[0].testResult", "prs", "", "REPLACE");
        JsonPathAlteration alterationPrs = new JsonPathAlteration("$.testTypes[0].defects[0].prs", "true", "", "REPLACE");
        JsonPathAlteration alterationExpiryDate = new JsonPathAlteration("$.testTypes[0].testExpiryDate", "", "", "DELETE");



        // Collate the list of alterations.
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(
                alterationVin,
                alterationSystemNumber,
                alterationTestName,
                alterationTestTypeName,
                alterationTestTypeId,
                alterationTestResult,
                alterationPrs,
                alterationExpiryDate,
                alterationTestResultId));

        // Post the results, together with any alterations, and verify that they are accepted.
        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, alterations);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");

        testResultsSteps.getTestResults(randomSystemNumber, TestResultsStatus.SUBMITTED);
        testResultsSteps.statusCodeShouldBe(200);
        testResultsSteps.validateTestNumberIsNotNull();
    }

    @Title("CVSB-2157CVSB-3293 AC B2. VSA submits test results for a test type where the test type result is abandoned (testNumber generated)")
    @Test
    public void validTestNumberGeneratedForAbandonedTestType() {

        String testResultRecord = GenericData.readJsonValueFromFile("test-results_LEC_PSV.json", "$");

        // Create alteration to add one more tech record to in the request body
        String randomSystemNumber = GenericData.generateRandomSystemNumber();
        String randomVin = GenericData.generateRandomVin();
        String randomTestResultId = UUID.randomUUID().toString();
        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$.systemNumber", randomSystemNumber, "", "REPLACE");
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationTestResultId = new JsonPathAlteration("$.testResultId", randomTestResultId, "", "REPLACE");
        JsonPathAlteration alterationTestName = new JsonPathAlteration("$.testTypes[0].name", "Retest", "", "REPLACE");
        JsonPathAlteration alterationTestTypeName = new JsonPathAlteration("$.testTypes[0].testTypeName", "Part-paid retest", "", "REPLACE");
        JsonPathAlteration alterationTestTypeId = new JsonPathAlteration("$.testTypes[0].testTypeId", "1", "", "REPLACE");
        JsonPathAlteration alterationTestResult = new JsonPathAlteration("$.testTypes[0].testResult", "abandoned", "", "REPLACE");
        JsonPathAlteration alterationExpiryDate = new JsonPathAlteration("$.testTypes[0].testExpiryDate", "", "", "DELETE");
        JsonPathAlteration alterationReasonForAbandon = new JsonPathAlteration("$.testTypes[0].reasonForAbandoning", "The vehicle was not submitted for test at the appointed time.", "", "REPLACE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(
                alterationVin,
                alterationSystemNumber,
                alterationTestName,
                alterationTestTypeName,
                alterationTestTypeId,
                alterationTestResult,
                alterationExpiryDate,
                alterationReasonForAbandon,
                alterationTestResultId));

        // Post the results, together with any alterations, and verify that they are accepted.
        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, alterations);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");

        testResultsSteps.getTestResults(randomSystemNumber, TestResultsStatus.SUBMITTED);
        testResultsSteps.statusCodeShouldBe(200);
        testResultsSteps.validateTestNumberIsNotNull();
    }

    @Title("CVSB-2157/CVSB-3294 AC B2. VSA submits test results for a test type where the test type result is abandoned (certificateNumber attribute is null)")
    @Test
    public void validateCertificateNumberIsNotGeneratedForAbandonedTestType() {

        String testResultRecord = GenericData.readJsonValueFromFile("test-results_LEC_PSV.json", "$");

        // Create alteration to add one more tech record to in the request body
        String randomSystemNumber = GenericData.generateRandomSystemNumber();
        String randomVin = GenericData.generateRandomVin();
        String randomTestResultId = UUID.randomUUID().toString();
        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$.systemNumber", randomSystemNumber, "", "REPLACE");
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationTestResultId = new JsonPathAlteration("$.testResultId", randomTestResultId, "", "REPLACE");
        JsonPathAlteration alterationTestName = new JsonPathAlteration("$.testTypes[0].name", "Retest", "", "REPLACE");
        JsonPathAlteration alterationTestTypeName = new JsonPathAlteration("$.testTypes[0].testTypeName", "Part-paid retest", "", "REPLACE");
        JsonPathAlteration alterationTestTypeId = new JsonPathAlteration("$.testTypes[0].testTypeId", "1", "", "REPLACE");
        JsonPathAlteration alterationTestResult = new JsonPathAlteration("$.testTypes[0].testResult", "abandoned", "", "REPLACE");
        JsonPathAlteration alterationExpiryDate = new JsonPathAlteration("$.testTypes[0].testExpiryDate", "", "", "DELETE");
        JsonPathAlteration alterationCert = new JsonPathAlteration("$.testTypes[0].certificateNumber", "", "", "REPLACE");
        JsonPathAlteration alterationReasonForAbandon = new JsonPathAlteration("$.testTypes[0].reasonForAbandoning", "The vehicle was not submitted for test at the appointed time.", "", "REPLACE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(
                alterationVin,
                alterationSystemNumber,
                alterationTestName,
                alterationTestTypeName,
                alterationTestTypeId,
                alterationTestResult,
                alterationCert,
                alterationExpiryDate,
                alterationReasonForAbandon,
                alterationTestResultId));

        // Post the results, together with any alterations, and verify that they are accepted.
        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, alterations);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");

        testResultsSteps.getTestResults(randomSystemNumber, TestResultsStatus.SUBMITTED);
        testResultsSteps.statusCodeShouldBe(200);
        testResultsSteps.validateCertificateNumberIsNull();
    }

    @Title("CVSB-2157/CVSB-3298 AC B5. VSA cancels a test that contains at least one test type (testNumber generated)")
    @Test
    public void validTestNumberGeneratedForCancelledTestType() {
        vehicleCancelledData.setVin(generateRandomExcludingValues(21, vehicleCancelledData.build().getVin()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledData.build().getVrm()))
                .setSystemNumber(generateRandomExcludingValues(7,vehicleCancelledData.build().getSystemNumber())).build();

        testResultsSteps.postTestResults(vehicleCancelledData.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        testResultsSteps.getTestResults(vehicleCancelledData.build().getSystemNumber(), TestResultsStatus.CANCELED);
        testResultsSteps.statusCodeShouldBe(200);
        testResultsSteps.validateTestNumberIsNotNull();
    }

    @Title("CVSB-2157/CVSB-3297 VSA cancels a test that contains at least one test type (certificateNumber attribute is null)")
    @Test
    public void validCertificateNumberNotGeneratedForCancelledTestType() {
        vehicleCancelledData.setVin(generateRandomExcludingValues(21, vehicleCancelledData.build().getVin()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledData.build().getVrm()))
                .setSystemNumber(generateRandomExcludingValues(7,vehicleCancelledData.build().getSystemNumber())).build();

        testResultsSteps.postTestResults(vehicleCancelledData.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        testResultsSteps.getTestResults(vehicleCancelledData.build().getSystemNumber(), TestResultsStatus.CANCELED);
        testResultsSteps.statusCodeShouldBe(200);
        testResultsSteps.validateTestNumberIsNotNull();
    }

    @Title("CVSB-2157/CVSB-3299 AC B4. VSA cancels a test that does not contain test types (testNumber not generated)")
    @Test
    public void validTestNumberNotGeneratedForCancelledTWithoutTestType() {
        vehicleCancelledData.setVin(generateRandomExcludingValues(21, vehicleCancelledData.build().getVin()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledData.build().getVrm()))
                .setSystemNumber(generateRandomExcludingValues(7,vehicleCancelledData.build().getSystemNumber())).build();

        testResultsSteps.postTestResultsFieldChange(vehicleCancelledData.build(),"testTypes","[]", ToTypeConvertor.EMPTY_ARRAY, TestResultsLevel.MAIN_LEVEL);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        testResultsSteps.getTestResults(vehicleCancelledData.build().getSystemNumber(), TestResultsStatus.CANCELED);
        testResultsSteps.statusCodeShouldBe(200);
        testResultsSteps.validateTestNumberIsNull();
    }

    @Title("CVSB-2157/CVSB-3295 AC B5. VSA cancels a test that contains at least one test type (certificateNumber attribute is null)")
    @Test
    public void validCertificateNumberNotGeneratedForCancelledTWithoutTestType() {

        vehicleCancelledData.setVin(generateRandomExcludingValues(21, vehicleCancelledData.build().getVin()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledData.build().getVrm()))
                .setSystemNumber(generateRandomExcludingValues(7,vehicleCancelledData.build().getSystemNumber())).build();

        testResultsSteps.postTestResultsFieldChange(vehicleCancelledData.build(),"testTypes","[]", ToTypeConvertor.EMPTY_ARRAY, TestResultsLevel.MAIN_LEVEL);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        testResultsSteps.getTestResults(vehicleCancelledData.build().getSystemNumber(), TestResultsStatus.CANCELED);
        testResultsSteps.statusCodeShouldBe(200);
        testResultsSteps.validateCertificateNumberIsNull();
    }

    @Title("CVSB-2157/CVSB-3290 AC B1. VSA submits test results where at least one test type has test type classification 'Annual With Certificate' and the test type result is PASSED (testNumber populated into certificateNumber attribute)")
    @Test
    public void validTestNumberGeneratedForAnnualWithCertificateIsEqualToCertificateNumber() {

        String testResultRecord = GenericData.readJsonValueFromFile("test-results_LEC_PSV.json", "$");

        // Create alteration to add one more tech record to in the request body
        String randomSystemNumber = GenericData.generateRandomSystemNumber();
        String randomVin = GenericData.generateRandomVin();
        String randomTestResultId = UUID.randomUUID().toString();
        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$.systemNumber", randomSystemNumber, "", "REPLACE");
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationTestResultId = new JsonPathAlteration("$.testResultId", randomTestResultId, "", "REPLACE");
        JsonPathAlteration alterationTestName = new JsonPathAlteration("$.testTypes[0].name", "Retest", "", "REPLACE");
        JsonPathAlteration alterationTestTypeName = new JsonPathAlteration("$.testTypes[0].testTypeName", "Part-paid retest", "", "REPLACE");
        JsonPathAlteration alterationTestTypeId = new JsonPathAlteration("$.testTypes[0].testTypeId", "1", "", "REPLACE");
        JsonPathAlteration alterationTestResult = new JsonPathAlteration("$.testTypes[0].testResult", "pass", "", "REPLACE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(
                alterationVin,
                alterationSystemNumber,
                alterationTestName,
                alterationTestTypeName,
                alterationTestTypeId,
                alterationTestResult,
                alterationTestResultId));

        // Post the results, together with any alterations, and verify that they are accepted.
        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, alterations);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");

        testResultsSteps.getTestResults(randomSystemNumber, TestResultsStatus.SUBMITTED);
        testResultsSteps.statusCodeShouldBe(200);
        testResultsSteps.valueForFieldInPathShouldBe("[0].testTypes[0].certificateNumber", testResultsSteps.getTestNumber());
    }

    @Title("CVSB-2157/CVSB-3292 AC B1. VSA submits test results where at least one test type has test type classification 'Annual With Certificate' and the test type result is PRS (testNumber populated into certificateNumber attribute)")
    @Test
    public void validTestNumberGeneratedForAnnualWithCertificatePRSIsEqualToCertificateNumber() {

        String testResultRecord = GenericData.readJsonValueFromFile("test-results_LEC_PSV.json", "$");

        // Create alteration to add one more tech record to in the request body
        String randomSystemNumber = GenericData.generateRandomSystemNumber();
        String randomVin = GenericData.generateRandomVin();
        String randomTestResultId = UUID.randomUUID().toString();
        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$.systemNumber", randomSystemNumber, "", "REPLACE");
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationTestResultId = new JsonPathAlteration("$.testResultId", randomTestResultId, "", "REPLACE");
        JsonPathAlteration alterationTestName = new JsonPathAlteration("$.testTypes[0].name", "Retest", "", "REPLACE");
        JsonPathAlteration alterationTestTypeName = new JsonPathAlteration("$.testTypes[0].testTypeName", "Part-paid retest", "", "REPLACE");
        JsonPathAlteration alterationTestTypeId = new JsonPathAlteration("$.testTypes[0].testTypeId", "1", "", "REPLACE");
        JsonPathAlteration alterationTestResult = new JsonPathAlteration("$.testTypes[0].testResult", "prs", "", "REPLACE");
        JsonPathAlteration alterationPrs = new JsonPathAlteration("$.testTypes[0].defects[0].prs", "true", "", "REPLACE");
        JsonPathAlteration alterationExpiryDate = new JsonPathAlteration("$.testTypes[0].testExpiryDate", "", "", "DELETE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(
                alterationVin,
                alterationSystemNumber,
                alterationTestName,
                alterationTestTypeName,
                alterationTestTypeId,
                alterationTestResult,
                alterationPrs,
                alterationExpiryDate,
                alterationTestResultId));

        // Post the results, together with any alterations, and verify that they are accepted.
        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, alterations);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");

        testResultsSteps.getTestResults(randomSystemNumber, TestResultsStatus.SUBMITTED);
        testResultsSteps.statusCodeShouldBe(200);
        testResultsSteps.valueForFieldInPathShouldBe("[0].testTypes[0].certificateNumber", testResultsSteps.getTestNumber());
    }

    @Ignore("Deprecated by CVSB-731")
    @Title("CVSB-2157/CVSB-3279 AC B3. VSA submits test results which contain an LEC Test Type (testNumber and certificate number are not the same)")
    public void validTestNumberGeneratedForLecTestTypeIsNotEqualToCertificateNumber() {

        String testResultRecord = GenericData.readJsonValueFromFile("test-results_LEC_PSV.json", "$");

        String randomSystemNumber = GenericData.generateRandomSystemNumber();
        String randomVin = GenericData.generateRandomVin();
        String randomTestResultId = UUID.randomUUID().toString();
        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$.systemNumber", randomSystemNumber, "", "REPLACE");
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationTestResultId = new JsonPathAlteration("$.testResultId", randomTestResultId, "", "REPLACE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(
                alterationVin,
                alterationSystemNumber,
                alterationTestResultId));

        // Post the results, together with any alterations, and verify that they are accepted.
        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, alterations);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");

        testResultsSteps.getTestResults(randomSystemNumber, TestResultsStatus.SUBMITTED);
        testResultsSteps.statusCodeShouldBe(200);
        testResultsSteps.validateTestNumberNotEqualCertificateNumber();
    }

    @Title("CVSB-2157/CVSB-3287 AC B1. VSA submits test results where at least one test type has test type classification 'Annual With Certificate' and the test type result is PASSED (testNumber is generated)")
    @Test
    public void validTestNumberGenerationForAtLeastTwoTestTypes(){

        String testResultRecordOne = GenericData.readJsonValueFromFile("test-results_LEC_PSV.json", "$");

        String randomSystemNumber1 = GenericData.generateRandomSystemNumber();
        String randomVin1 = GenericData.generateRandomVin();
        String randomTestResultId1 = UUID.randomUUID().toString();
        JsonPathAlteration alterationSystemNumber1 = new JsonPathAlteration("$.systemNumber", randomSystemNumber1, "", "REPLACE");
        JsonPathAlteration alterationVin1 = new JsonPathAlteration("$.vin", randomVin1, "", "REPLACE");
        JsonPathAlteration alterationTestResultId1 = new JsonPathAlteration("$.testResultId", randomTestResultId1, "", "REPLACE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterations1 = new ArrayList<>(Arrays.asList(
                alterationVin1,
                alterationSystemNumber1,
                alterationTestResultId1));

        // Post the results, together with any alterations, and verify that they are accepted.
        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecordOne, alterations1);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");

        String testResultRecord = GenericData.readJsonValueFromFile("test-results_LEC_PSV.json", "$");

        // Create alteration to add one more tech record to in the request body
        String randomSystemNumber2 = GenericData.generateRandomSystemNumber();
        String randomVin2 = GenericData.generateRandomVin();
        String randomTestResultId2 = UUID.randomUUID().toString();
        JsonPathAlteration alterationSystemNumber2 = new JsonPathAlteration("$.systemNumber", randomSystemNumber2, "", "REPLACE");
        JsonPathAlteration alterationVin2 = new JsonPathAlteration("$.vin", randomVin2, "", "REPLACE");
        JsonPathAlteration alterationTestResultId2 = new JsonPathAlteration("$.testResultId", randomTestResultId2, "", "REPLACE");
        JsonPathAlteration alterationTestName2 = new JsonPathAlteration("$.testTypes[0].name", "Retest", "", "REPLACE");
        JsonPathAlteration alterationTestTypeName2 = new JsonPathAlteration("$.testTypes[0].testTypeName", "Part-paid retest", "", "REPLACE");
        JsonPathAlteration alterationTestTypeId2 = new JsonPathAlteration("$.testTypes[0].testTypeId", "1", "", "REPLACE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterations2 = new ArrayList<>(Arrays.asList(
                alterationVin2,
                alterationSystemNumber2,
                alterationTestName2,
                alterationTestTypeName2,
                alterationTestTypeId2,
                alterationTestResultId2));

        // Post the results, together with any alterations, and verify that they are accepted.
        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, alterations2);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");


        testResultsSteps.getTestResults(randomSystemNumber1, TestResultsStatus.SUBMITTED);
        testResultsSteps.statusCodeShouldBe(200);
        testResultsSteps.getTestResults(randomSystemNumber2, TestResultsStatus.SUBMITTED);
        testResultsSteps.statusCodeShouldBe(200);
        testResultsSteps.validateTestNumberIsDifferentForTwoTestTypes();
    }

    @Title("CVSB-2157/CVSB-3246 AC A1. VSA submits test results (when current cert letter in database is not 'Z', and current sequence number in database is not '999') (testNumber generated)")
    @Test
    public void verifyNextTestNumberGeneration() {

        String testResultRecord1 = GenericData.readJsonValueFromFile("test-results_LEC_PSV.json", "$");

        // Create alteration to add one more tech record to in the request body
        String randomSystemNumber1 = GenericData.generateRandomSystemNumber();
        String randomVin1 = GenericData.generateRandomVin();
        String randomTestResultId1 = UUID.randomUUID().toString();
        JsonPathAlteration alterationSystemNumber1 = new JsonPathAlteration("$.systemNumber", randomSystemNumber1, "", "REPLACE");
        JsonPathAlteration alterationVin1 = new JsonPathAlteration("$.vin", randomVin1, "", "REPLACE");
        JsonPathAlteration alterationTestResultId1 = new JsonPathAlteration("$.testResultId", randomTestResultId1, "", "REPLACE");
        JsonPathAlteration alterationTestName1 = new JsonPathAlteration("$.testTypes[0].name", "Retest", "", "REPLACE");
        JsonPathAlteration alterationTestTypeName1 = new JsonPathAlteration("$.testTypes[0].testTypeName", "Part-paid retest", "", "REPLACE");
        JsonPathAlteration alterationTestTypeId1 = new JsonPathAlteration("$.testTypes[0].testTypeId", "1", "", "REPLACE");
        JsonPathAlteration alterationTestResult1 = new JsonPathAlteration("$.testTypes[0].testResult", "prs", "", "REPLACE");
        JsonPathAlteration alterationPrs1 = new JsonPathAlteration("$.testTypes[0].defects[0].prs", "true", "", "REPLACE");
        JsonPathAlteration alterationExpiryDate1 = new JsonPathAlteration("$.testTypes[0].testExpiryDate", "", "", "DELETE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterations1 = new ArrayList<>(Arrays.asList(
                alterationVin1,
                alterationSystemNumber1,
                alterationTestName1,
                alterationTestTypeName1,
                alterationTestTypeId1,
                alterationTestResult1,
                alterationPrs1,
                alterationExpiryDate1,
                alterationTestResultId1));

        // Post the results, together with any alterations, and verify that they are accepted.
        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord1, alterations1);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");

        testResultsSteps.getTestResults(randomSystemNumber1, TestResultsStatus.SUBMITTED);
        testResultsSteps.statusCodeShouldBe(200);
        String nextTestNumber = testResultsSteps.nextTestNumber();

        String testResultRecord2 = GenericData.readJsonValueFromFile("test-results_LEC_PSV.json", "$");

        String randomSystemNumber2 = GenericData.generateRandomSystemNumber();
        String randomVin2 = GenericData.generateRandomVin();
        String randomTestResultId2 = UUID.randomUUID().toString();
        JsonPathAlteration alterationSystemNumber2 = new JsonPathAlteration("$.systemNumber", randomSystemNumber2, "", "REPLACE");
        JsonPathAlteration alterationVin2 = new JsonPathAlteration("$.vin", randomVin2, "", "REPLACE");
        JsonPathAlteration alterationTestResultId2 = new JsonPathAlteration("$.testResultId", randomTestResultId2, "", "REPLACE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterations2 = new ArrayList<>(Arrays.asList(
                alterationVin2,
                alterationSystemNumber2,
                alterationTestResultId2));

        // Post the results, together with any alterations, and verify that they are accepted.
        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord2, alterations2);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");

        testResultsSteps.getTestResults(randomSystemNumber2, TestResultsStatus.SUBMITTED);
        testResultsSteps.statusCodeShouldBe(200);
        testResultsSteps.checkNextTestNumberIsValid(nextTestNumber);
    }
}
