package testresults;

import data.GenericData;
import data.TestResultsData;
import model.testresults.TestResults;
import model.testresults.TestResultsGet;
import model.testresults.TestResultsStatus;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import steps.TestResultsSteps;
import util.JsonPathAlteration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@RunWith(SerenityRunner.class)
public class PostTestResultsLEC {

    @Steps
    TestResultsSteps testResultsSteps;

    private TestResults.Builder vehicleSubmittedData = TestResultsData.buildTestResultsSubmittedData();
    private String test_results_LEC_PSV_json;

    @Before
    @Test
    public void updateJson(){
        String jsonFileName = "test-results_LEC_PSV.json";
        test_results_LEC_PSV_json = GenericData.updateJson( jsonFileName, false);
    }

    private void validateSavedData(List<String> data) {

        testResultsSteps.getTestResults(vehicleSubmittedData.build().getVin(), TestResultsStatus.SUBMITTED);
        testResultsSteps.statusCodeShouldBe(200);
        testResultsSteps.validateData((TestResultsGet) vehicleSubmittedData.build());
        testResultsSteps.validateDataForExpiry(data);
    }


    @Title("CVSB-7964 - TC4 - AC4 - API Consumer creates a new test results for the submitted test (PSV, Pass)")
    @Test
    public void testResultsAPIConsumerCreatesANewTestResultLEC_PSV_Pass() {

        // Read the base test result JSON.
        String testResultRecord = test_results_LEC_PSV_json;

        // Create alteration to add one more tech record to in the request body
        String randomVin = GenericData.generateRandomVin();
        String randomSystemNumber = GenericData.generateRandomVin();
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin,"","REPLACE");
        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$.systemNumber", randomSystemNumber,"","REPLACE");
        JsonPathAlteration alterationResult = new JsonPathAlteration("$.testTypes[0].testResult", "pass","","REPLACE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(
                alterationVin,
                alterationResult,
                alterationSystemNumber));

        // Post the results, together with any alterations, and verify that they are accepted.
        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, alterations);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");

        // Retrieve the created record, and verify that the fields are present.
        testResultsSteps.getTestResults(randomSystemNumber);
        testResultsSteps.statusCodeShouldBe(200);

        // Verify that the new LEC fields are returned.
        testResultsSteps.validateTestFieldExists("modType");
        testResultsSteps.validateTestFieldExists("emissionStandard");
        testResultsSteps.validateTestFieldExists("fuelType");

        // Verify LEC test field values match the expected values.
        testResultsSteps.valueForFieldInPathShouldBe("[0].testTypes[0].testTypeId", "39");
        testResultsSteps.valueForFieldInPathShouldBe("[0].testTypes[0].testTypeName", "Low Emissions Certificate (LEC) with annual test");

        testResultsSteps.valueForFieldInPathShouldBe("[0].testTypes[0].fuelType", "diesel");
        testResultsSteps.valueForFieldInPathShouldBe("[0].testTypes[0].emissionStandard", "0.10 g/kWh Euro 3 PM");
        testResultsSteps.valueForFieldInPathShouldBe("[0].testTypes[0].modType.code", "p");
        testResultsSteps.valueForFieldInPathShouldBe("[0].testTypes[0].modType.description", "particulate trap");
    }

    @Title("CVSB-7964 - TC4 - AC4 - API Consumer creates a new test results for the submitted test (HGV, Pass)")
    @Test
    public void testResultsAPIConsumerCreatesANewTestResultLEC_HGV_Pass() {

        // Read the base test result JSON.
        String testResultRecord = GenericData.readJsonValueFromFile("test-results_LEC_HGV.json","$");

        // Create alteration to add one more tech record to in the request body
        String randomVin = GenericData.generateRandomVin();
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin,"","REPLACE");
        String randomSystemNumber = GenericData.generateRandomSystemNumber();
        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$.systemNumber", randomSystemNumber,"","REPLACE");
        JsonPathAlteration alterationResult = new JsonPathAlteration("$.testTypes[0].testResult", "pass","","REPLACE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(
                alterationVin,
                alterationResult,
                alterationSystemNumber));

        // Post the results, together with any alterations, and verify that they are accepted.
        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, alterations);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");

        // Retrieve the created record, and verify that the fields are present.
        testResultsSteps.getTestResults(randomSystemNumber);
        testResultsSteps.statusCodeShouldBe(200);

        // Verify that the new LEC fields are returned.
        testResultsSteps.validateTestFieldExists("modType");
        testResultsSteps.validateTestFieldExists("emissionStandard");
        testResultsSteps.validateTestFieldExists("fuelType");

        // Verify LEC test field values match the expected values.
        testResultsSteps.valueForFieldInPathShouldBe("[0].testTypes[0].testTypeId", "44");
        testResultsSteps.valueForFieldInPathShouldBe("[0].testTypes[0].testTypeName", "Low Emissions Certificate (LEC) with annual test");

        testResultsSteps.valueForFieldInPathShouldBe("[0].testTypes[0].fuelType", "diesel");
        testResultsSteps.valueForFieldInPathShouldBe("[0].testTypes[0].emissionStandard", "0.10 g/kWh Euro 3 PM");
        testResultsSteps.valueForFieldInPathShouldBe("[0].testTypes[0].modType.code", "p");
        testResultsSteps.valueForFieldInPathShouldBe("[0].testTypes[0].modType.description", "particulate trap");
    }

    @Title("CVSB-7964 - TC4 - AC4 - API Consumer creates a new test results for the submitted test (HGV2, Pass)")
    @Test
    public void testResultsAPIConsumerCreatesANewTestResultLEC_HGV2_Pass() {

        // Read the base test result JSON.
        String testResultRecord = GenericData.readJsonValueFromFile("test-results_LEC_HGV2.json","$");

        // Create alteration to add one more tech record to in the request body
        String randomVin = GenericData.generateRandomVin();
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin,"","REPLACE");
        String randomSystemNumber = GenericData.generateRandomSystemNumber();
        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$.systemNumber", randomSystemNumber,"","REPLACE");
        JsonPathAlteration alterationResult = new JsonPathAlteration("$.testTypes[0].testResult", "pass","","REPLACE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(
                alterationVin,
                alterationResult,
                alterationSystemNumber));

        // Post the results, together with any alterations, and verify that they are accepted.
        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, alterations);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");

        // Retrieve the created record, and verify that the fields are present.
        testResultsSteps.getTestResults(randomSystemNumber);
        testResultsSteps.statusCodeShouldBe(200);

        // Verify that the new LEC fields are returned.
        testResultsSteps.validateTestFieldExists("modType");
        testResultsSteps.validateTestFieldExists("emissionStandard");
        testResultsSteps.validateTestFieldExists("fuelType");

        // Verify LEC test field values match the expected values.
        testResultsSteps.valueForFieldInPathShouldBe("[0].testTypes[0].testTypeId", "45");
        testResultsSteps.valueForFieldInPathShouldBe("[0].testTypes[0].testTypeName", "Low Emissions Certificate (LEC) with annual test");

        testResultsSteps.valueForFieldInPathShouldBe("[0].testTypes[0].fuelType", "diesel");
        testResultsSteps.valueForFieldInPathShouldBe("[0].testTypes[0].emissionStandard", "0.10 g/kWh Euro 3 PM");
        testResultsSteps.valueForFieldInPathShouldBe("[0].testTypes[0].modType.code", "p");
        testResultsSteps.valueForFieldInPathShouldBe("[0].testTypes[0].modType.description", "particulate trap");
    }

    @Title("CVSB-7964 - TC4 - AC4 - API Consumer creates a new test results for the submitted test (PSV, Fail)")
    @Test
    public void testResultsAPIConsumerCreatesANewTestResultLEC_PSV_Fail() {

        // Read the base test result JSON.
        String testResultRecord = test_results_LEC_PSV_json;

        // Create alteration to add one more tech record to in the request body
        String randomVin = GenericData.generateRandomVin();
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin,"","REPLACE");
        String randomSystemNumber = GenericData.generateRandomSystemNumber();
        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$.systemNumber", randomSystemNumber,"","REPLACE");
        JsonPathAlteration alterationResult = new JsonPathAlteration("$.testTypes[0].testResult", "fail","","REPLACE");
        JsonPathAlteration removeModType = new JsonPathAlteration("$.testTypes[0].modType", "","","DELETE");
        JsonPathAlteration removeExpiryDate = new JsonPathAlteration("$.testTypes[0].testExpiryDate", "","","DELETE");
        JsonPathAlteration removeFuelType = new JsonPathAlteration("$.testTypes[0].fuelType", "","","DELETE");
        JsonPathAlteration removeEmissionStandard = new JsonPathAlteration("$.testTypes[0].emissionStandard", "","","DELETE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(
                alterationVin,
                alterationResult,
                removeModType,
                removeExpiryDate,
                removeFuelType,
                removeEmissionStandard,
                alterationSystemNumber));

        // Post the results, together with any alterations, and verify that they are accepted.
        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, alterations);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");

        // Retrieve the created record, and verify that the fields are present.
        testResultsSteps.getTestResults(randomSystemNumber);
        testResultsSteps.statusCodeShouldBe(200);

        // Verify that the new LEC fields are returned.
        testResultsSteps.validateTestFieldExists("certificateNumber");

        // Verify LEC test field values match the expected values.
        testResultsSteps.valueForFieldInPathShouldBe("[0].testTypes[0].testTypeId", "39");
        testResultsSteps.valueForFieldInPathShouldBe("[0].testTypes[0].testTypeName", "Low Emissions Certificate (LEC) with annual test");
    }

    @Title("CVSB-7964 - TC4 - AC4 - API Consumer creates a new test results for the submitted test (HGV, Fail)")
    @Test
    public void testResultsAPIConsumerCreatesANewTestResultLEC_HGV_Fail() {

        // Read the base test result JSON.
        String testResultRecord = GenericData.readJsonValueFromFile("test-results_LEC_HGV.json","$");

        // Create alteration to add one more tech record to in the request body
        String randomVin = GenericData.generateRandomVin();
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin,"","REPLACE");
        String randomSystemNumber = GenericData.generateRandomSystemNumber();
        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$.systemNumber", randomSystemNumber,"","REPLACE");
        JsonPathAlteration alterationResult = new JsonPathAlteration("$.testTypes[0].testResult", "fail","","REPLACE");
        JsonPathAlteration removeModType = new JsonPathAlteration("$.testTypes[0].modType", "","","DELETE");
        JsonPathAlteration removeExpiryDate = new JsonPathAlteration("$.testTypes[0].testExpiryDate", "","","DELETE");
        JsonPathAlteration removeFuelType = new JsonPathAlteration("$.testTypes[0].fuelType", "","","DELETE");
        JsonPathAlteration removeEmissionStandard = new JsonPathAlteration("$.testTypes[0].emissionStandard", "","","DELETE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(
                alterationVin,
                alterationResult,
                removeModType,
                removeExpiryDate,
                removeFuelType,
                removeEmissionStandard,
                alterationSystemNumber));

        // Post the results, together with any alterations, and verify that they are accepted.
        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, alterations);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");

        // Retrieve the created record, and verify that the fields are present.
        testResultsSteps.getTestResults(randomSystemNumber);
        testResultsSteps.statusCodeShouldBe(200);

        // Verify that the new LEC fields are returned.
        testResultsSteps.validateTestFieldExists("certificateNumber");

        // Verify LEC test field values match the expected values.
        testResultsSteps.valueForFieldInPathShouldBe("[0].testTypes[0].testTypeId", "44");
        testResultsSteps.valueForFieldInPathShouldBe("[0].testTypes[0].testTypeName", "Low Emissions Certificate (LEC) with annual test");
    }

    @Title("CVSB-7964 - TC4 - AC4 - API Consumer creates a new test results for the submitted test (HGV2, Fail)")
    @Test
    public void testResultsAPIConsumerCreatesANewTestResultLEC_HGV2_Fail() {

        // Read the base test result JSON.
        String testResultRecord = GenericData.readJsonValueFromFile("test-results_LEC_HGV2.json","$");

        // Create alteration to add one more tech record to in the request body
        String randomVin = GenericData.generateRandomVin();
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin,"","REPLACE");
        String randomSystemNumber = GenericData.generateRandomSystemNumber();
        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$.systemNumber", randomSystemNumber,"","REPLACE");
        JsonPathAlteration alterationResult = new JsonPathAlteration("$.testTypes[0].testResult", "fail","","REPLACE");
        JsonPathAlteration removeModType = new JsonPathAlteration("$.testTypes[0].modType", "","","DELETE");
        JsonPathAlteration removeExpiryDate = new JsonPathAlteration("$.testTypes[0].testExpiryDate", "","","DELETE");
        JsonPathAlteration removeFuelType = new JsonPathAlteration("$.testTypes[0].fuelType", "","","DELETE");
        JsonPathAlteration removeEmissionStandard = new JsonPathAlteration("$.testTypes[0].emissionStandard", "","","DELETE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(
                alterationVin,
                alterationResult,
                removeModType,
                removeExpiryDate,
                removeFuelType,
                removeEmissionStandard,
                alterationSystemNumber));

        // Post the results, together with any alterations, and verify that they are accepted.
        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, alterations);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");

        // Retrieve the created record, and verify that the fields are present.
        testResultsSteps.getTestResults(randomSystemNumber);
        testResultsSteps.statusCodeShouldBe(200);

        // Verify that the new LEC fields are returned.
        testResultsSteps.validateTestFieldExists("certificateNumber");

        // Verify LEC test field values match the expected values.
        testResultsSteps.valueForFieldInPathShouldBe("[0].testTypes[0].testTypeId", "45");
        testResultsSteps.valueForFieldInPathShouldBe("[0].testTypes[0].testTypeName", "Low Emissions Certificate (LEC) with annual test");
    }

    @Title("CVSB-7964 - TC5 - AC5.1 - API Consumer receives error when submitting an LEC test without sending an expiryDate")
    @Test
    public void testResultsAPIConsumerErrorLECNoExpiryDate() {

        // Read the base test result JSON.
        String testResultRecord = GenericData.readJsonValueFromFile("test-results_LEC_PSV.json","$");

        // Create alteration to add one more tech record to in the request body
        String randomVin = GenericData.generateRandomVin();
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin,"","REPLACE");

        // Remove the testExpiryDate.
        JsonPathAlteration removeExpiryDate = new JsonPathAlteration("$.testTypes[0].testExpiryDate", "","","DELETE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(alterationVin, removeExpiryDate));

        // Post the results, together with any alterations, and verify that they are handled correctly.
        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, alterations);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validateErrorText("Expiry Date not present on LEC test type");
    }

    @Title("CVSB-7964 - TC6 - AC5.2 - API Consumer receives error when submitting an LEC test without sending a certificateNumber")
    @Test
    public void testResultsAPIConsumerErrorLECNoCertificateNumber_Pass() {

        // Read the base test result JSON.
        String testResultRecord = GenericData.readJsonValueFromFile("test-results_LEC_PSV.json","$");

        // Create alteration to add one more tech record to in the request body
        String randomVin = GenericData.generateRandomVin();
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin,"","REPLACE");
        JsonPathAlteration alterationResult = new JsonPathAlteration("$.testTypes[0].testResult", "pass","","REPLACE");

        // Remove the certificateNumber.
        JsonPathAlteration removeCertificateNumber = new JsonPathAlteration("$.testTypes[0].certificateNumber", "","","DELETE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(
                alterationVin,
                alterationResult,
                removeCertificateNumber));

        // Post the results, together with any alterations, and verify that they are handled correctly.
        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, alterations);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validateErrorText("Certificate number not present on LEC test type");
    }

    @Title("CVSB-7964 - TC6 - AC5.2 - API Consumer receives error when submitting an LEC test without sending a certificateNumber")
    @Test
    public void testResultsAPIConsumerErrorLECNoCertificateNumber_Fail() {

        // Read the base test result JSON.
        String testResultRecord = GenericData.readJsonValueFromFile("test-results_LEC_PSV.json","$");

        // Create alteration to add one more tech record to in the request body
        String randomVin = GenericData.generateRandomVin();
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin,"","REPLACE");
        JsonPathAlteration alterationResult = new JsonPathAlteration("$.testTypes[0].testResult", "fail","","REPLACE");

        // Remove the certificateNumber.
        JsonPathAlteration removeCertificateNumber = new JsonPathAlteration("$.testTypes[0].certificateNumber", "","","DELETE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(
                alterationVin,
                alterationResult,
                removeCertificateNumber));

        // Post the results, together with any alterations, and verify that they are handled correctly.
        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, alterations);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validateErrorText("Certificate number not present on LEC test type");
    }

    @Title("CVSB-7964 - TC7 - AC5.3 - API Consumer receives error when submitting an LEC test without sending a modType")
    @Test
    public void testResultsAPIConsumerErrorLECNoModType() {

        // Read the base test result JSON.
        String testResultRecord = GenericData.readJsonValueFromFile("test-results_LEC_PSV.json","$");

        // Create alteration to add one more tech record to in the request body
        String randomVin = GenericData.generateRandomVin();
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin,"","REPLACE");

        // Remove the modType.
        JsonPathAlteration removeModType = new JsonPathAlteration("$.testTypes[0].modType", "","","DELETE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(alterationVin, removeModType));

        // Post the results, together with any alterations, and verify that they are handled correctly.
        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, alterations);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validateErrorText("Modification type not present on LEC test type");
    }

    @Title("CVSB-7964 - TC8 - AC5.4 - API Consumer receives error when submitting an LEC test without sending an emissionStandard")
    @Test
    public void testResultsAPIConsumerErrorLECNoEmissionStandard() {

        // Read the base test result JSON.
        String testResultRecord = GenericData.readJsonValueFromFile("test-results_LEC_PSV.json","$");

        // Create alteration to add one more tech record to in the request body
        String randomVin = GenericData.generateRandomVin();
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin,"","REPLACE");

        // Remove the emissionStandard.
        JsonPathAlteration removeEmissionStandard = new JsonPathAlteration("$.testTypes[0].emissionStandard", "","","DELETE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(alterationVin, removeEmissionStandard));

        // Post the results, together with any alterations, and verify that they are handled correctly.
        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, alterations);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validateErrorText("Emission standard not present on LEC test type");
    }

    @Title("CVSB-7964 - TC9 - AC5.5 - API Consumer receives error when submitting an LEC test without sending a fuelType")
    @Test
    public void testResultsAPIConsumerErrorLECNoFuelType() {

        // Read the base test result JSON.
        String testResultRecord = GenericData.readJsonValueFromFile("test-results_LEC_PSV.json","$");

        // Create alteration to add one more tech record to in the request body
        String randomVin = GenericData.generateRandomVin();
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin,"","REPLACE");

        // Remove the fuelType.
        JsonPathAlteration removeFuelType = new JsonPathAlteration("$.testTypes[0].fuelType", "","","DELETE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(alterationVin, removeFuelType));

        // Post the results, together with any alterations, and verify that they are handled correctly.
        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, alterations);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validateErrorText("Fuel Type not present on LEC test type");
    }
}
