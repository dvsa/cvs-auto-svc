package testresults;

import data.GenericData;
import model.vehicles.VehicleTechnicalRecordSearchCriteria;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import steps.TestResultsSteps;
import steps.VehicleTechnicalRecordsSteps;
import util.JsonPathAlteration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;


@RunWith(SerenityRunner.class)
public class PostTestResultsRoadworthiness {

    @Steps
    TestResultsSteps testResultsSteps;

    @Steps
    VehicleTechnicalRecordsSteps vehicleTechnicalRecordsSteps;

    private String test_results_roadworthiness_hgv_pass_7675_json;
    private String test_results_roadworthiness_trl_pass_7675_json;
    private String test_results_roadworthiness_hgv_fail_7675_json;
    private String test_results_roadworthiness_trl_fail_7675_json;
    @Before
    @Test
    public void updateJson(){
        String jsonFileName = "test-results_roadworthiness_hgv_pass_7675.json";
        String jsonFileName2 = "test-results_roadworthiness_trl_pass_7675.json";
        String jsonFileName3 = "test-results_roadworthiness_hgv_fail_7675.json";
        String jsonFileName4 = "test-results_roadworthiness_trl_fail_7675.json";
        test_results_roadworthiness_hgv_pass_7675_json = GenericData.updateJson(jsonFileName,false);
        test_results_roadworthiness_trl_pass_7675_json = GenericData.updateJson(jsonFileName2,false);
        test_results_roadworthiness_hgv_fail_7675_json = GenericData.updateJson(jsonFileName3,false);
        test_results_roadworthiness_trl_fail_7675_json = GenericData.updateJson(jsonFileName4,false);
    }

    @Title("CVSB-7675 - TC1 - AC1 - ROADWORTHINESS certificate number generated (HGV) - PASS")
    @Test
    public void testResults_Roadworthiness_HGV_Pass_Certificate_Number() {

        // Read the base test result JSON.
        String testResultRecord = test_results_roadworthiness_hgv_pass_7675_json;

        // Create alteration to add one more tech record to in the request body
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

        // Retrieve the created record, and verify that the fields are present.
        testResultsSteps.getTestResults(randomSystemNumber);
        testResultsSteps.statusCodeShouldBe(200);

        // Verify TestNumber/CertificateNumber field have the same value
        testResultsSteps.valueForFieldInPathShouldBe("[0].testTypes[0].certificateNumber", testResultsSteps.getTestNumber());
        String testNumber = testResultsSteps.getTestNumber();

        //Verify that the certificate is generated in S3 bucket
        testResultsSteps.validateCertificateIsGenerated(testNumber,randomVin);
    }

    @Title("CVSB-7675 - TC2 - AC1 - ROADWORTHINESS certificate number generated (TRL) - PASS")
    @Test
    public void testResults_Roadworthiness_TRL_Pass_Certificate_Number() {

        // Read the base test result JSON.
        String testResultRecord = test_results_roadworthiness_trl_pass_7675_json;

        // Create alteration to add one more tech record to in the request body
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

        // Retrieve the created record, and verify that the fields are present.
        testResultsSteps.getTestResults(randomSystemNumber);
        testResultsSteps.statusCodeShouldBe(200);

        // Verify TestNumber/CertificateNumber are  test field values match the expected values.
        testResultsSteps.valueForFieldInPathShouldBe("[0].testTypes[0].certificateNumber", testResultsSteps.getTestNumber());
        String testNumber = testResultsSteps.getTestNumber();

        //Verify that the certificate is generated in S3 bucket
        testResultsSteps.validateCertificateIsGenerated(testNumber,randomVin);
    }

    @Title("CVSB-7675 - TC3 - Negative (HGV) - FAIL")
    @Test
    public void testResults_Roadworthiness_HGV_Fail_Certificate_Number() {


        // POST tech-record,read the base tech-record JSON.
        String postRequestBody = GenericData.readJsonValueFromFile("technical-records_hgv_all_fields.json", "$");

        // Create alteration to edit one or more fields in the request body
        String randomVin = GenericData.generateRandomVin();
        JsonPathAlteration alterationVinVehicle = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterationsVehicle = new ArrayList<>(Arrays.asList(alterationVinVehicle));

        // Post the tech-record, together with any alterations, and verify that they are accepted.
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBody, alterationsVehicle);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(HttpStatus.SC_CREATED);
        vehicleTechnicalRecordsSteps.validateData("Technical Record created");

        //GET tech-records
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsBySearchCriteria(randomVin, VehicleTechnicalRecordSearchCriteria.VIN);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(HttpStatus.SC_OK);

        String systemNumber = vehicleTechnicalRecordsSteps.getSystemNumber();

        // Read the base test result JSON.
        String testResultRecord = test_results_roadworthiness_hgv_fail_7675_json;

        // Create alteration to add one more tech record to in the request body
        String randomTestResultId = UUID.randomUUID().toString();
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$", systemNumber,"systemNumber","ADD_FIELD");
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

        // Retrieve the created record, and verify that the fields are present.
        testResultsSteps.getTestResults(systemNumber);
        testResultsSteps.statusCodeShouldBe(200);

        // Verify CertificateNumber field have the null value
        testResultsSteps.valueForFieldInPathShouldBe("[0].testTypes[0].certificateNumber", null);
        String testNumber = testResultsSteps.getTestNumber();

        //Verify that the certificate is generated in S3 bucket
        testResultsSteps.validateCertificateIsGenerated(testNumber,randomVin);
    }

    @Title("CVSB-7675 - TC4 - Negative (TRL) - FAIL")
    @Test
    public void testResults_Roadworthiness_TRL_Fail_Certificate_Number() {

        // POST tech-record,read the base tech-record JSON.
        String postRequestBody = GenericData.readJsonValueFromFile("technical-records_trl_all_fields.json", "$");

        // Create alteration to edit one or more fields in the request body
        String randomVin = GenericData.generateRandomVin();
        String randomVrm = GenericData.generateRandomVrm();
        JsonPathAlteration alterationVinVehicle = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationVrmVehicle = new JsonPathAlteration("$.primaryVrm", randomVrm, "", "REPLACE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterationsVehicle = new ArrayList<>(Arrays.asList(alterationVinVehicle, alterationVrmVehicle));

        // Post the tech-record, together with any alterations, and verify that they are accepted.
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBody, alterationsVehicle);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(HttpStatus.SC_CREATED);
        vehicleTechnicalRecordsSteps.validateData("Technical Record created");

        //GET tech-records
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsBySearchCriteria(randomVin, VehicleTechnicalRecordSearchCriteria.VIN);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(HttpStatus.SC_OK);

        String systemNumber = vehicleTechnicalRecordsSteps.getSystemNumber();

        // Read the base test result JSON.
        String testResultRecord = test_results_roadworthiness_trl_fail_7675_json;

        // Create alteration to add one more tech record to in the request body
        String randomTestResultId = UUID.randomUUID().toString();
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$", systemNumber,"systemNumber","ADD_FIELD");
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

        // Retrieve the created record, and verify that the fields are present.
        testResultsSteps.getTestResults(systemNumber);
        testResultsSteps.statusCodeShouldBe(200);

        // Verify CertificateNumber field have the null value
        testResultsSteps.valueForFieldInPathShouldBe("[0].testTypes[0].certificateNumber", null);
        String testNumber = testResultsSteps.getTestNumber();

        //Verify that the certificate is generated in S3 bucket
        testResultsSteps.validateCertificateIsGenerated(testNumber,randomVin);
    }
}
