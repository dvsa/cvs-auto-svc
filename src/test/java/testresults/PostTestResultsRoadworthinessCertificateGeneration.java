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
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;


@RunWith(SerenityRunner.class)
public class PostTestResultsRoadworthinessCertificateGeneration {

    @Steps
    VehicleTechnicalRecordsSteps vehicleTechnicalRecordsSteps;

    LocalDateTime testStartDate;
    @Before
    public void beforeTest() {
        this.testStartDate = LocalDateTime.now();
    }

    @Steps
    TestResultsSteps testResultsSteps;

    private String test_results_roadworthiness_trl_pass_qjt1_7677_json;
    private String test_results_roadworthiness_trl_pass_qjt2_7677_json;
    private String test_results_roadworthiness_trl_pass_qjt4_7677_json;
    private String test_results_roadworthiness_hgv_pass_qjv2_7677_json;
    private String test_results_roadworthiness_hgv_pass_qjv3_7677_json;
    private String test_results_roadworthiness_hgv_pass_qjv4_7677_json;
    private String test_results_roadworthiness_hgv_pass_qjv5_7677_json;
    private String test_results_roadworthiness_hgv_pass_qkv_7677_json;
    private String test_results_roadworthiness_trl_pass_qkt_7677_json;
    private String test_results_roadworthiness_hgv_pass_qpv_7677_json;
    private String test_results_roadworthiness_trl_pass_qpt_7677_json;
    private String test_results_roadworthiness_hgv_pass_qqv_7677_json;
    private String test_results_roadworthiness_trl_pass_qqt_7677_json;
    private String test_results_roadworthiness_trl_pass_rut_7677_json;
    private String test_results_roadworthiness_trl_fail_qjt1_7677_json;
    private String test_results_roadworthiness_trl_fail_qjt2_7677_json;
    private String test_results_roadworthiness_trl_fail_qjt4_7677_json;
    private String test_results_roadworthiness_hgv_fail_qjv2_7677_json;
    private String test_results_roadworthiness_hgv_fail_qjv3_7677_json;
    private String test_results_roadworthiness_hgv_fail_qjv4_7677_json;
    private String test_results_roadworthiness_hgv_fail_qjv5_7677_json;
    private String test_results_roadworthiness_hgv_fail_qkv_7677_json;
    private String test_results_roadworthiness_trl_fail_qkt_7677_json;
    private String test_results_roadworthiness_hgv_fail_qpv_7677_json;
    private String test_results_roadworthiness_trl_fail_qpt_7677_json;
    private String test_results_roadworthiness_hgv_fail_qqv_7677_json;
    private String test_results_roadworthiness_trl_fail_qqt_7677_json;
    private String test_results_roadworthiness_trl_fail_rut_7677_json;
    private String test_results_roadworthiness_hgv_fail_ruv_7677_json;
    @Before
    @Test
    public void updateJson(){
        String jsonFileName = "test-results_roadworthiness_trl_pass_qjt1_7677.json";
        String jsonFileName2 = "test-results_roadworthiness_trl_pass_qjt2_7677.json";
        String jsonFileName3 = "test-results_roadworthiness_trl_pass_qjt4_7677.json";
        String jsonFileName4 = "test-results_roadworthiness_hgv_pass_qjv2_7677.json";
        String jsonFileName5 = "test-results_roadworthiness_hgv_pass_qjv3_7677.json";
        String jsonFileName6 = "test-results_roadworthiness_hgv_pass_qjv4_7677.json";
        String jsonFileName7 = "test-results_roadworthiness_hgv_pass_qjv5_7677.json";
        String jsonFileName8 = "test-results_roadworthiness_hgv_pass_qkv_7677.json";
        String jsonFileName9 = "test-results_roadworthiness_trl_pass_qkt_7677.json";
        String jsonFileName10 = "test-results_roadworthiness_hgv_pass_qpv_7677.json";
        String jsonFileName11 = "test-results_roadworthiness_trl_pass_qpt_7677.json";
        String jsonFileName12 = "test-results_roadworthiness_hgv_pass_qqv_7677.json";
        String jsonFileName13 = "test-results_roadworthiness_trl_pass_qqt_7677.json";
        String jsonFileName14 = "test-results_roadworthiness_trl_pass_rut_7677.json";
        String jsonFileName15 = "test-results_roadworthiness_trl_fail_qjt1_7677.json";
        String jsonFileName16 = "test-results_roadworthiness_trl_fail_qjt2_7677.json";
        String jsonFileName17 = "test-results_roadworthiness_trl_fail_qjt4_7677.json";
        String jsonFileName18 = "test-results_roadworthiness_hgv_fail_qjv2_7677.json";
        String jsonFileName19 = "test-results_roadworthiness_hgv_fail_qjv3_7677.json";
        String jsonFileName20 = "test-results_roadworthiness_hgv_fail_qjv4_7677.json";
        String jsonFileName21 = "test-results_roadworthiness_hgv_fail_qjv5_7677.json";
        String jsonFileName22 = "test-results_roadworthiness_hgv_fail_qkv_7677.json";
        String jsonFileName23 = "test-results_roadworthiness_trl_fail_qkt_7677.json";
        String jsonFileName24 = "test-results_roadworthiness_hgv_fail_qpv_7677.json";
        String jsonFileName25 = "test-results_roadworthiness_trl_fail_qpt_7677.json";
        String jsonFileName26 = "test-results_roadworthiness_hgv_fail_qqv_7677.json";
        String jsonFileName27 = "test-results_roadworthiness_trl_fail_qqt_7677.json";
        String jsonFileName28 = "test-results_roadworthiness_trl_fail_rut_7677.json";
        String jsonFileName29 = "test-results_roadworthiness_hgv_fail_ruv_7677.json";

        test_results_roadworthiness_trl_pass_qjt1_7677_json = GenericData.updateJson(jsonFileName,false);
        test_results_roadworthiness_trl_pass_qjt2_7677_json = GenericData.updateJson(jsonFileName2,false);
        test_results_roadworthiness_trl_pass_qjt4_7677_json = GenericData.updateJson(jsonFileName3,false);
        test_results_roadworthiness_hgv_pass_qjv2_7677_json = GenericData.updateJson(jsonFileName4,false);
        test_results_roadworthiness_hgv_pass_qjv3_7677_json = GenericData.updateJson(jsonFileName5,false);
        test_results_roadworthiness_hgv_pass_qjv4_7677_json = GenericData.updateJson(jsonFileName6,false);
        test_results_roadworthiness_hgv_pass_qjv5_7677_json = GenericData.updateJson(jsonFileName7,false);
        test_results_roadworthiness_hgv_pass_qkv_7677_json = GenericData.updateJson(jsonFileName8,false);
        test_results_roadworthiness_trl_pass_qkt_7677_json = GenericData.updateJson(jsonFileName9,false);
        test_results_roadworthiness_hgv_pass_qpv_7677_json = GenericData.updateJson(jsonFileName10,false);
        test_results_roadworthiness_trl_pass_qpt_7677_json = GenericData.updateJson(jsonFileName11,false);
        test_results_roadworthiness_hgv_pass_qqv_7677_json = GenericData.updateJson(jsonFileName12,false);
        test_results_roadworthiness_trl_pass_qqt_7677_json = GenericData.updateJson(jsonFileName13,false);
        test_results_roadworthiness_trl_pass_rut_7677_json = GenericData.updateJson(jsonFileName14,false);
        test_results_roadworthiness_trl_fail_qjt1_7677_json = GenericData.updateJson(jsonFileName15,false);
        test_results_roadworthiness_trl_fail_qjt2_7677_json = GenericData.updateJson(jsonFileName16,false);
        test_results_roadworthiness_trl_fail_qjt4_7677_json = GenericData.updateJson(jsonFileName17,false);
        test_results_roadworthiness_hgv_fail_qjv2_7677_json = GenericData.updateJson(jsonFileName18,false);
        test_results_roadworthiness_hgv_fail_qjv3_7677_json = GenericData.updateJson(jsonFileName19,false);
        test_results_roadworthiness_hgv_fail_qjv4_7677_json = GenericData.updateJson(jsonFileName20,false);
        test_results_roadworthiness_hgv_fail_qjv5_7677_json = GenericData.updateJson(jsonFileName21,false);
        test_results_roadworthiness_hgv_fail_qkv_7677_json = GenericData.updateJson(jsonFileName22,false);
        test_results_roadworthiness_trl_fail_qkt_7677_json = GenericData.updateJson(jsonFileName23,false);
        test_results_roadworthiness_hgv_fail_qpv_7677_json = GenericData.updateJson(jsonFileName24,false);
        test_results_roadworthiness_trl_fail_qpt_7677_json = GenericData.updateJson(jsonFileName25,false);
        test_results_roadworthiness_hgv_fail_qqv_7677_json = GenericData.updateJson(jsonFileName26,false);
        test_results_roadworthiness_trl_fail_qqt_7677_json = GenericData.updateJson(jsonFileName27,false);
        test_results_roadworthiness_trl_fail_rut_7677_json = GenericData.updateJson(jsonFileName28,false);
        test_results_roadworthiness_hgv_fail_ruv_7677_json = GenericData.updateJson(jsonFileName29,false);
    }

    @Title("CVSB-7677 - TC - AC1 - CERTIFICATE GENERATED ON CORRECT TEST CODES (TRL)(QJT1) - PASS ")
    @Test
    public void testResults_Roadworthiness_TRL_QJT1_Pass_Certificate_Generation() {

        // Read the base test result JSON.
        String testResultRecord = test_results_roadworthiness_trl_pass_qjt1_7677_json;

        // Create alteration to add one more tech record to in the request body
        String randomVin = GenericData.generateRandomVin();
        String randomSystemNumber = GenericData.generateRandomSystemNumber();
        String randomTestResultId = UUID.randomUUID().toString();
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationTestResultId = new JsonPathAlteration("$.testResultId", randomTestResultId, "", "REPLACE");
        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$", randomSystemNumber,"systemNumber","ADD_FIELD");
        // Collate the list of alterations.
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(
                alterationVin,
                alterationTestResultId,
                alterationSystemNumber));

        // Post the results, together with any alterations, and verify that they are accepted.
        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, alterations);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        testResultsSteps.sleep();

        testResultsSteps.getTestResults(randomSystemNumber);
        testResultsSteps.statusCodeShouldBe(200);
        String testNumber = testResultsSteps.getTestNumber();

        //Verify that the certificate is generated in S3 bucket
        testResultsSteps.validateCertificateIsGenerated(testNumber,randomVin);
    }

    @Title("CVSB-7677 - TC - AC1 - CERTIFICATE GENERATED ON CORRECT TEST CODES (TRL)(QJT2) - PASS ")
    @Test
    public void testResults_Roadworthiness_TRL_QJT2_Pass_Certificate_Generation() {

        // Read the base test result JSON.
        String testResultRecord = test_results_roadworthiness_trl_pass_qjt2_7677_json;

        // Create alteration to add one more tech record to in the request body
        String randomVin = GenericData.generateRandomVin();
        String randomSystemNumber = GenericData.generateRandomSystemNumber();
        String randomTestResultId = UUID.randomUUID().toString();
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationTestResultId = new JsonPathAlteration("$.testResultId", randomTestResultId, "", "REPLACE");
        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$", randomSystemNumber,"systemNumber","ADD_FIELD");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(
                alterationVin,
                alterationTestResultId,
                alterationSystemNumber));

        // Post the results, together with any alterations, and verify that they are accepted.
        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, alterations);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        testResultsSteps.sleep();

        testResultsSteps.getTestResults(randomSystemNumber);
        testResultsSteps.statusCodeShouldBe(200);
        String testNumber = testResultsSteps.getTestNumber();

        //Verify that the certificate is generated in S3 bucket
        testResultsSteps.validateCertificateIsGenerated(testNumber,randomVin);
    }

    @Title("CVSB-7677 - TC - AC1 - CERTIFICATE GENERATED ON CORRECT TEST CODES (TRL)(QJT4) - PASS ")
    @Test
    public void testResults_Roadworthiness_TRL_QJT4_Pass_Certificate_Generation() {

        // POST tech-record,read the base tech-record JSON.
        String postRequestBody = GenericData.readJsonValueFromFile("technical-records_trl_all_fields.json","$");

        // Create alteration to edit one or more fields in the request body
        String randomVin = GenericData.generateRandomVin();
        String randomVrm = GenericData.generateRandomVrm();
        String randomTrailerId = GenericData.generateRandomTrailerId();
        JsonPathAlteration alterationVinVehicle = new JsonPathAlteration("$.vin", randomVin,"","REPLACE");
        JsonPathAlteration alterationVrmVehicle = new JsonPathAlteration("$.primaryVrm", randomVrm,"","REPLACE");
        JsonPathAlteration alterationTrailerId = new JsonPathAlteration("$.trailerId", randomTrailerId,"","REPLACE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterationsVehicle = new ArrayList<>(Arrays.asList(alterationVinVehicle,
                alterationVrmVehicle,
                alterationTrailerId));

        // Post the tech-record, together with any alterations, and verify that they are accepted.
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBody, alterationsVehicle);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(HttpStatus.SC_CREATED);
        vehicleTechnicalRecordsSteps.validateData("Technical Record created");

        //GET tech-records
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsBySearchCriteria(randomVin, VehicleTechnicalRecordSearchCriteria.VIN);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(HttpStatus.SC_OK);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].statusCode", "provisional");

        String systemNumber = vehicleTechnicalRecordsSteps.getSystemNumber();

        System.out.println(systemNumber);

        // read base JSON for POST test-results
        String testResultRecord = test_results_roadworthiness_trl_pass_qjt4_7677_json;

        String randomTestResultId = UUID.randomUUID().toString();
        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$", systemNumber,"systemNumber","ADD_FIELD");
        JsonPathAlteration alterationVinTestResults = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationTestResultIdPost = new JsonPathAlteration("$.testResultId", randomTestResultId, "", "REPLACE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterationsTestResults = new ArrayList<>(Arrays.asList(
                alterationSystemNumber,
                alterationVinTestResults,
                alterationTestResultIdPost));

        // Post the test-results, together with any alterations, and verify that they are accepted.
        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, alterationsTestResults);
        testResultsSteps.statusCodeShouldBe(HttpStatus.SC_CREATED);
        testResultsSteps.validateData("Test records created");

        testResultsSteps.getTestResults(systemNumber);
        testResultsSteps.statusCodeShouldBe(200);
        String testNumber = testResultsSteps.getTestNumber();

        //Verify that the certificate is generated in S3 bucket
        testResultsSteps.validateCertificateIsGenerated(testNumber,randomVin);
    }

    @Title("CVSB-7677 - TC - AC1 - CERTIFICATE GENERATED ON CORRECT TEST CODES (HGV)(QJV2) - PASS ")
    @Test
    public void testResults_Roadworthiness_HGV_QJV2_Pass_Certificate_Generation() {

        // POST tech-record,read the base tech-record JSON.
        String postRequestBody = GenericData.readJsonValueFromFile("technical-records_hgv_all_fields.json","$");

        // Create alteration to edit one or more fields in the request body
        String randomVin = GenericData.generateRandomVin();
        JsonPathAlteration alterationVinVehicle = new JsonPathAlteration("$.vin", randomVin,"","REPLACE");

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

        String testResultRecord = test_results_roadworthiness_hgv_pass_qjv2_7677_json;
        String randomTestResultId = UUID.randomUUID().toString();
        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$", systemNumber,"systemNumber","ADD_FIELD");
        JsonPathAlteration alterationVinTestResults = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationTestResultIdPost = new JsonPathAlteration("$.testResultId", randomTestResultId, "", "REPLACE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterationsTestResults = new ArrayList<>(Arrays.asList(
                alterationSystemNumber,
                alterationVinTestResults,
                alterationTestResultIdPost));

        // Post the test-results, together with any alterations, and verify that they are accepted.
        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, alterationsTestResults);
        testResultsSteps.statusCodeShouldBe(HttpStatus.SC_CREATED);
        testResultsSteps.validateData("Test records created");

        testResultsSteps.getTestResults(systemNumber);
        testResultsSteps.statusCodeShouldBe(200);
        String testNumber = testResultsSteps.getTestNumber();

        //Verify that the certificate is generated in S3 bucket
        testResultsSteps.validateCertificateIsGenerated(testNumber,randomVin);
    }

    @Title("CVSB-7677 - TC - AC1 - CERTIFICATE GENERATED ON CORRECT TEST CODES (HGV)(QJV3) - PASS ")
    @Test
    public void testResults_Roadworthiness_HGV_QJV3_Pass_Certificate_Generation() {

        // Read the base test result JSON.
        String testResultRecord = test_results_roadworthiness_hgv_pass_qjv3_7677_json;

        // Create alteration to add one more tech record to in the request body
        String randomVin = GenericData.generateRandomVin();
        String randomSystemNumber = GenericData.generateRandomSystemNumber();
        String randomTestResultId = UUID.randomUUID().toString();
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationTestResultId = new JsonPathAlteration("$.testResultId", randomTestResultId, "", "REPLACE");
        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$", randomSystemNumber,"systemNumber","ADD_FIELD");
        // Collate the list of alterations.
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(
                alterationVin,
                alterationTestResultId,
                alterationSystemNumber));

        // Post the results, together with any alterations, and verify that they are accepted.
        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, alterations);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");

        testResultsSteps.getTestResults(randomSystemNumber);
        testResultsSteps.statusCodeShouldBe(200);
        String testNumber = testResultsSteps.getTestNumber();

        //Verify that the certificate is generated in S3 bucket
        testResultsSteps.validateCertificateIsGenerated(testNumber,randomVin);
    }

    @Title("CVSB-7677 - TC - AC1 - CERTIFICATE GENERATED ON CORRECT TEST CODES (HGV)(QJV4) - PASS ")
    @Test
    public void testResults_Roadworthiness_HGV_QJV4_Pass_Certificate_Generation() {

        // Read the base test result JSON.
        String testResultRecord = test_results_roadworthiness_hgv_pass_qjv4_7677_json;

        // Create alteration to add one more tech record to in the request body
        String randomVin = GenericData.generateRandomVin();
        String randomSystemNumber = GenericData.generateRandomSystemNumber();
        String randomTestResultId = UUID.randomUUID().toString();
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationTestResultId = new JsonPathAlteration("$.testResultId", randomTestResultId, "", "REPLACE");
        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$", randomSystemNumber,"systemNumber","ADD_FIELD");
        // Collate the list of alterations.
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(
                alterationVin,
                alterationTestResultId,
                alterationSystemNumber));

        // Post the results, together with any alterations, and verify that they are accepted.
        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, alterations);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");

        testResultsSteps.getTestResults(randomSystemNumber);
        testResultsSteps.statusCodeShouldBe(200);
        String testNumber = testResultsSteps.getTestNumber();

        //Verify that the certificate is generated in S3 bucket
        testResultsSteps.validateCertificateIsGenerated(testNumber,randomVin);
    }

    @Title("CVSB-7677 - TC - AC1 - CERTIFICATE GENERATED ON CORRECT TEST CODES (HGV)(QJV5) - PASS ")
    @Test
    public void testResults_Roadworthiness_HGV_QJV5_Pass_Certificate_Generation() {

        // Read the base test result JSON.
        String testResultRecord = test_results_roadworthiness_hgv_pass_qjv5_7677_json;

        // Create alteration to add one more tech record to in the request body
        String randomVin = GenericData.generateRandomVin();
        String randomSystemNumber = GenericData.generateRandomSystemNumber();
        String randomTestResultId = UUID.randomUUID().toString();
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationTestResultId = new JsonPathAlteration("$.testResultId", randomTestResultId, "", "REPLACE");
        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$", randomSystemNumber,"systemNumber","ADD_FIELD");
        // Collate the list of alterations.
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(
                alterationVin,
                alterationTestResultId,
                alterationSystemNumber));

        // Post the results, together with any alterations, and verify that they are accepted.
        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, alterations);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");

        testResultsSteps.getTestResults(randomSystemNumber);
        testResultsSteps.statusCodeShouldBe(200);
        String testNumber = testResultsSteps.getTestNumber();

        //Verify that the certificate is generated in S3 bucket
        testResultsSteps.validateCertificateIsGenerated(testNumber,randomVin);
    }

    @Title("CVSB-7677 - TC - AC1 - CERTIFICATE GENERATED ON CORRECT TEST CODES (HGV)(QKV) - PASS ")
    @Test
    public void testResults_Roadworthiness_HGV_QKV_Pass_Certificate_Generation() {

        // POST tech-record,read the base tech-record JSON.
        String postRequestBody = GenericData.readJsonValueFromFile("technical-records_hgv_all_fields.json","$");

        // Create alteration to edit one or more fields in the request body
        String randomVin = GenericData.generateRandomVin();
        JsonPathAlteration alterationVinVehicle = new JsonPathAlteration("$.vin", randomVin,"","REPLACE");

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

        String testResultRecord = test_results_roadworthiness_hgv_pass_qkv_7677_json;
        String randomTestResultId = UUID.randomUUID().toString();
        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$", systemNumber,"systemNumber","ADD_FIELD");
        JsonPathAlteration alterationVinTestResults = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationTestResultIdPost = new JsonPathAlteration("$.testResultId", randomTestResultId, "", "REPLACE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterationsTestResults = new ArrayList<>(Arrays.asList(
                alterationSystemNumber,
                alterationVinTestResults,
                alterationTestResultIdPost));

        // Post the test-results, together with any alterations, and verify that they are accepted.
        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, alterationsTestResults);
        testResultsSteps.statusCodeShouldBe(HttpStatus.SC_CREATED);
        testResultsSteps.validateData("Test records created");

        testResultsSteps.getTestResults(systemNumber);
        testResultsSteps.statusCodeShouldBe(200);
        String testNumber = testResultsSteps.getTestNumber();

        //Verify that the certificate is generated in S3 bucket
        testResultsSteps.validateCertificateIsGenerated(testNumber,randomVin);
    }

    @Title("CVSB-7677 - TC - AC1 - CERTIFICATE GENERATED ON CORRECT TEST CODES (TRL)(QKT) - PASS ")
    @Test
    public void testResults_Roadworthiness_TRL_QKT_Pass_Certificate_Generation() {

        // Read the base test result JSON.
        String testResultRecord = test_results_roadworthiness_trl_pass_qkt_7677_json;

        // Create alteration to add one more tech record to in the request body
        String randomVin = GenericData.generateRandomVin();
        String randomSystemNumber = GenericData.generateRandomSystemNumber();
        String randomTestResultId = UUID.randomUUID().toString();
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationTestResultId = new JsonPathAlteration("$.testResultId", randomTestResultId, "", "REPLACE");
        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$", randomSystemNumber,"systemNumber","ADD_FIELD");
        // Collate the list of alterations.
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(
                alterationVin,
                alterationTestResultId,
                alterationSystemNumber));

        // Post the results, together with any alterations, and verify that they are accepted.
        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, alterations);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");

        testResultsSteps.getTestResults(randomSystemNumber);
        testResultsSteps.statusCodeShouldBe(200);
        String testNumber = testResultsSteps.getTestNumber();

        //Verify that the certificate is generated in S3 bucket
        testResultsSteps.validateCertificateIsGenerated(testNumber,randomVin);
    }

    @Title("CVSB-7677 - TC - AC1 - CERTIFICATE GENERATED ON CORRECT TEST CODES (HGV)(QPV) - PASS ")
    @Test
    public void testResults_Roadworthiness_HGV_QPV_Pass_Certificate_Generation() {

        // Read the base test result JSON.
        String testResultRecord = test_results_roadworthiness_hgv_pass_qpv_7677_json;

        // Create alteration to add one more tech record to in the request body
        String randomVin = GenericData.generateRandomVin();
        String randomSystemNumber = GenericData.generateRandomSystemNumber();
        String randomTestResultId = UUID.randomUUID().toString();
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationTestResultId = new JsonPathAlteration("$.testResultId", randomTestResultId, "", "REPLACE");
        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$", randomSystemNumber,"systemNumber","ADD_FIELD");
        // Collate the list of alterations.
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(
                alterationVin,
                alterationTestResultId,
                alterationSystemNumber));

        // Post the results, together with any alterations, and verify that they are accepted.
        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, alterations);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");

        testResultsSteps.getTestResults(randomSystemNumber);
        testResultsSteps.statusCodeShouldBe(200);
        String testNumber = testResultsSteps.getTestNumber();

        //Verify that the certificate is generated in S3 bucket
        testResultsSteps.validateCertificateIsGenerated(testNumber,randomVin);
    }

    @Title("CVSB-7677 - TC - AC1 - CERTIFICATE GENERATED ON CORRECT TEST CODES (TRL)(QPT) - PASS ")
    @Test
    public void testResults_Roadworthiness_TRL_QPT_Pass_Certificate_Generation() {

        // Read the base test result JSON.
        String testResultRecord = test_results_roadworthiness_trl_pass_qpt_7677_json;

        // Create alteration to add one more tech record to in the request body
        String randomVin = GenericData.generateRandomVin();
        String randomSystemNumber = GenericData.generateRandomSystemNumber();
        String randomTestResultId = UUID.randomUUID().toString();
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationTestResultId = new JsonPathAlteration("$.testResultId", randomTestResultId, "", "REPLACE");
        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$", randomSystemNumber,"systemNumber","ADD_FIELD");
        // Collate the list of alterations.
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(
                alterationVin,
                alterationTestResultId,
                alterationSystemNumber));

        // Post the results, together with any alterations, and verify that they are accepted.
        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, alterations);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");

        testResultsSteps.getTestResults(randomSystemNumber);
        testResultsSteps.statusCodeShouldBe(200);
        String testNumber = testResultsSteps.getTestNumber();

        //Verify that the certificate is generated in S3 bucket
        testResultsSteps.validateCertificateIsGenerated(testNumber,randomVin);
    }

    @Title("CVSB-7677 - TC - AC1 - CERTIFICATE GENERATED ON CORRECT TEST CODES (HGV)(QQV) - PASS ")
    @Test
    public void testResults_Roadworthiness_HGV_QQV_Pass_Certificate_Generation() {

        // Read the base test result JSON.
        String testResultRecord = test_results_roadworthiness_hgv_pass_qqv_7677_json;

        // Create alteration to add one more tech record to in the request body
        String randomVin = GenericData.generateRandomVin();
        String randomSystemNumber = GenericData.generateRandomSystemNumber();
        String randomTestResultId = UUID.randomUUID().toString();
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationTestResultId = new JsonPathAlteration("$.testResultId", randomTestResultId, "", "REPLACE");
        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$", randomSystemNumber,"systemNumber","ADD_FIELD");
        // Collate the list of alterations.
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(
                alterationVin,
                alterationTestResultId,
                alterationSystemNumber));

        // Post the results, together with any alterations, and verify that they are accepted.
        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, alterations);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        vehicleTechnicalRecordsSteps.waitForVehicleRecordUpdate(randomVin, 25, this.testStartDate);
        this.testStartDate = LocalDateTime.now();

        testResultsSteps.getTestResults(randomSystemNumber);
        testResultsSteps.statusCodeShouldBe(200);
        String testNumber = testResultsSteps.getTestNumber();

        //Verify that the certificate is generated in S3 bucket
        testResultsSteps.validateCertificateIsGenerated(testNumber,randomVin);
    }

    @Title("CVSB-7677 - TC - AC1 - CERTIFICATE GENERATED ON CORRECT TEST CODES (TRL)(QQT) - PASS ")
    @Test
    public void testResults_Roadworthiness_TRL_QQT_Pass_Certificate_Generation() {

        // Read the base test result JSON.
        String testResultRecord = test_results_roadworthiness_trl_pass_qqt_7677_json;

        // Create alteration to add one more tech record to in the request body
        String randomVin = GenericData.generateRandomVin();
        String randomSystemNumber = GenericData.generateRandomSystemNumber();
        String randomTestResultId = UUID.randomUUID().toString();
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationTestResultId = new JsonPathAlteration("$.testResultId", randomTestResultId, "", "REPLACE");
        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$", randomSystemNumber,"systemNumber","ADD_FIELD");
        // Collate the list of alterations.
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(
                alterationVin,
                alterationTestResultId,
                alterationSystemNumber));

        // Post the results, together with any alterations, and verify that they are accepted.
        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, alterations);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");

        testResultsSteps.getTestResults(randomSystemNumber);
        testResultsSteps.statusCodeShouldBe(200);
        String testNumber = testResultsSteps.getTestNumber();

        //Verify that the certificate is generated in S3 bucket
        testResultsSteps.validateCertificateIsGenerated(testNumber,randomVin);
    }

    @Title("CVSB-7677 - TC - AC1 - CERTIFICATE GENERATED ON CORRECT TEST CODES (TRL)(RUT) - PASS ")
    @Test
    public void testResults_Roadworthiness_TRL_RUT_Pass_Certificate_Generation() {

        // Read the base test result JSON.
        String testResultRecord = test_results_roadworthiness_trl_pass_rut_7677_json;

        // Create alteration to add one more tech record to in the request body
        String randomVin = GenericData.generateRandomVin();
        String randomSystemNumber = GenericData.generateRandomSystemNumber();
        String randomTestResultId = UUID.randomUUID().toString();
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationTestResultId = new JsonPathAlteration("$.testResultId", randomTestResultId, "", "REPLACE");
        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$", randomSystemNumber,"systemNumber","ADD_FIELD");
        // Collate the list of alterations.
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(
                alterationVin,
                alterationTestResultId,
                alterationSystemNumber));

        // Post the results, together with any alterations, and verify that they are accepted.
        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, alterations);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");

        testResultsSteps.getTestResults(randomSystemNumber);
        testResultsSteps.statusCodeShouldBe(200);
        String testNumber = testResultsSteps.getTestNumber();

        //Verify that the certificate is generated in S3 bucket
        testResultsSteps.validateCertificateIsGenerated(testNumber,randomVin);
    }

    @Title("CVSB-7677 - TC - AC1 - CERTIFICATE GENERATED ON CORRECT TEST CODES (HGV)(RUV) - PASS ")
    @Test
    public void testResults_Roadworthiness_HGV_RUV_Pass_Certificate_Generation() {

        // Read the base test result JSON.
        String testResultRecord = test_results_roadworthiness_trl_pass_rut_7677_json;

        // Create alteration to add one more tech record to in the request body
        String randomVin = GenericData.generateRandomVin();
        String randomSystemNumber = GenericData.generateRandomSystemNumber();
        String randomTestResultId = UUID.randomUUID().toString();
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationTestResultId = new JsonPathAlteration("$.testResultId", randomTestResultId, "", "REPLACE");
        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$", randomSystemNumber,"systemNumber","ADD_FIELD");
        // Collate the list of alterations.
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(
                alterationVin,
                alterationTestResultId,
                alterationSystemNumber));

        // Post the results, together with any alterations, and verify that they are accepted.
        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, alterations);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");

        testResultsSteps.getTestResults(randomSystemNumber);
        testResultsSteps.statusCodeShouldBe(200);
        String testNumber = testResultsSteps.getTestNumber();

        //Verify that the certificate is generated in S3 bucket
        testResultsSteps.validateCertificateIsGenerated(testNumber,randomVin);
    }

    @Title("CVSB-7677 - TC - AC2 - CERTIFICATE GENERATED ON CORRECT TEST CODES (TRL)(QJT1) - FAIL ")
    @Test
    public void testResults_Roadworthiness_TRL_QJT1_Fail_Certificate_Generation() {

        // Read the base test result JSON.
        String testResultRecord = test_results_roadworthiness_trl_fail_qjt1_7677_json;

        // Create alteration to add one more tech record to in the request body
        String randomVin = GenericData.generateRandomVin();
        String randomSystemNumber = GenericData.generateRandomSystemNumber();
        String randomTestResultId = UUID.randomUUID().toString();
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationTestResultId = new JsonPathAlteration("$.testResultId", randomTestResultId, "", "REPLACE");
        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$", randomSystemNumber,"systemNumber","ADD_FIELD");
        // Collate the list of alterations.
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(
                alterationVin,
                alterationTestResultId,
                alterationSystemNumber));

        // Post the results, together with any alterations, and verify that they are accepted.
        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, alterations);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");

        testResultsSteps.getTestResults(randomSystemNumber);
        testResultsSteps.statusCodeShouldBe(200);
        String testNumber = testResultsSteps.getTestNumber();

        //Verify that the certificate is generated in S3 bucket
        testResultsSteps.validateCertificateIsGenerated(testNumber,randomVin);
    }

    @Title("CVSB-7677 - TC - AC2 - CERTIFICATE GENERATED ON CORRECT TEST CODES (TRL)(QJT2) - FAIL ")
    @Test
    public void testResults_Roadworthiness_TRL_QJT2_Fail_Certificate_Generation() {

        // Read the base test result JSON.
        String testResultRecord = test_results_roadworthiness_trl_fail_qjt2_7677_json;

        // Create alteration to add one more tech record to in the request body
        String randomVin = GenericData.generateRandomVin();
        String randomSystemNumber = GenericData.generateRandomSystemNumber();
        String randomTestResultId = UUID.randomUUID().toString();
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationTestResultId = new JsonPathAlteration("$.testResultId", randomTestResultId, "", "REPLACE");
        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$", randomSystemNumber,"systemNumber","ADD_FIELD");
        // Collate the list of alterations.
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(
                alterationVin,
                alterationTestResultId,
                alterationSystemNumber));

        // Post the results, together with any alterations, and verify that they are accepted.
        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, alterations);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");

        testResultsSteps.getTestResults(randomSystemNumber);
        testResultsSteps.statusCodeShouldBe(200);
        String testNumber = testResultsSteps.getTestNumber();

        //Verify that the certificate is generated in S3 bucket
        testResultsSteps.validateCertificateIsGenerated(testNumber,randomVin);
    }

    @Title("CVSB-7677 - TC - AC2 - CERTIFICATE GENERATED ON CORRECT TEST CODES (TRL)(QJT4) - FAIL ")
    @Test
    public void testResults_Roadworthiness_TRL_QJT4_Fail_Certificate_Generation() {

        // POST tech-record,read the base tech-record JSON.
        String postRequestBody = GenericData.readJsonValueFromFile("technical-records_trl_all_fields.json","$");

        // Create alteration to edit one or more fields in the request body
        String randomVin = GenericData.generateRandomVin();
        String randomVrm = GenericData.generateRandomVrm();
        String randomTrailerId = GenericData.generateRandomTrailerId();
        JsonPathAlteration alterationVinVehicle = new JsonPathAlteration("$.vin", randomVin,"","REPLACE");
        JsonPathAlteration alterationVrmVehicle = new JsonPathAlteration("$.primaryVrm", randomVrm,"","REPLACE");
        JsonPathAlteration alterationTrailerId = new JsonPathAlteration("$.trailerId", randomTrailerId,"","REPLACE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterationsVehicle = new ArrayList<>(Arrays.asList(alterationVinVehicle,
                alterationVrmVehicle,
                alterationTrailerId));

        // Post the tech-record, together with any alterations, and verify that they are accepted.
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBody, alterationsVehicle);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(HttpStatus.SC_CREATED);
        vehicleTechnicalRecordsSteps.validateData("Technical Record created");

        //GET tech-records
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsBySearchCriteria(randomVin, VehicleTechnicalRecordSearchCriteria.VIN);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(HttpStatus.SC_OK);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].statusCode", "provisional");
        String systemNumber = vehicleTechnicalRecordsSteps.getSystemNumber();

        // read base JSON for POST test-results
        String testResultRecord = test_results_roadworthiness_trl_fail_qjt4_7677_json;

        String randomTestResultId = UUID.randomUUID().toString();
        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$", systemNumber,"systemNumber","ADD_FIELD");
        JsonPathAlteration alterationVinTestResults = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationTestResultIdPost = new JsonPathAlteration("$.testResultId", randomTestResultId, "", "REPLACE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterationsTestResults = new ArrayList<>(Arrays.asList(
                alterationSystemNumber,
                alterationVinTestResults,
                alterationTestResultIdPost));

        // Post the test-results, together with any alterations, and verify that they are accepted.
        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, alterationsTestResults);
        testResultsSteps.statusCodeShouldBe(HttpStatus.SC_CREATED);
        testResultsSteps.validateData("Test records created");

        testResultsSteps.getTestResults(systemNumber);
        testResultsSteps.statusCodeShouldBe(200);
        String testNumber = testResultsSteps.getTestNumber();

        //Verify that the certificate is generated in S3 bucket
        testResultsSteps.validateCertificateIsGenerated(testNumber,randomVin);
    }

    @Title("CVSB-7677 - TC - AC2 - CERTIFICATE GENERATED ON CORRECT TEST CODES (HGV)(QJV2) - FAIL ")
    @Test
    public void testResults_Roadworthiness_HGV_QJV2_Fail_Certificate_Generation() {

        // Read the base test result JSON.
        String testResultRecord = test_results_roadworthiness_hgv_fail_qjv2_7677_json;

        // Create alteration to add one more tech record to in the request body
        String randomVin = GenericData.generateRandomVin();
        String randomSystemNumber = GenericData.generateRandomSystemNumber();
        String randomTestResultId = UUID.randomUUID().toString();
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationTestResultId = new JsonPathAlteration("$.testResultId", randomTestResultId, "", "REPLACE");
        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$", randomSystemNumber,"systemNumber","ADD_FIELD");
        // Collate the list of alterations.
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(
                alterationVin,
                alterationTestResultId,
                alterationSystemNumber));

        // Post the results, together with any alterations, and verify that they are accepted.
        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, alterations);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");

        testResultsSteps.getTestResults(randomSystemNumber);
        testResultsSteps.statusCodeShouldBe(200);
        String testNumber = testResultsSteps.getTestNumber();

        //Verify that the certificate is generated in S3 bucket
        testResultsSteps.validateCertificateIsGenerated(testNumber,randomVin);
    }

    @Title("CVSB-7677 - TC - AC2 - CERTIFICATE GENERATED ON CORRECT TEST CODES (HGV)(QJV3) - FAIL ")
    @Test
    public void testResults_Roadworthiness_HGV_QJV3_Fail_Certificate_Generation() {

        // Read the base test result JSON.
        String testResultRecord = test_results_roadworthiness_hgv_fail_qjv3_7677_json;

        // Create alteration to add one more tech record to in the request body
        String randomVin = GenericData.generateRandomVin();
        String randomSystemNumber = GenericData.generateRandomSystemNumber();
        String randomTestResultId = UUID.randomUUID().toString();
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationTestResultId = new JsonPathAlteration("$.testResultId", randomTestResultId, "", "REPLACE");
        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$", randomSystemNumber,"systemNumber","ADD_FIELD");
        // Collate the list of alterations.
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(
                alterationVin,
                alterationTestResultId,
                alterationSystemNumber));

        // Post the results, together with any alterations, and verify that they are accepted.
        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, alterations);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        testResultsSteps.sleep();

        testResultsSteps.getTestResults(randomSystemNumber);
        testResultsSteps.statusCodeShouldBe(200);
        String testNumber = testResultsSteps.getTestNumber();

        //Verify that the certificate is generated in S3 bucket
        testResultsSteps.validateCertificateIsGenerated(testNumber,randomVin);
    }

    @Title("CVSB-7677 - TC - AC2 - CERTIFICATE GENERATED ON CORRECT TEST CODES (HGV)(QJV4) - FAIL ")
    @Test
    public void testResults_Roadworthiness_HGV_QJV4_Fail_Certificate_Generation() {

        // Read the base test result JSON.
        String testResultRecord = test_results_roadworthiness_hgv_fail_qjv4_7677_json;

        // Create alteration to add one more tech record to in the request body
        String randomVin = GenericData.generateRandomVin();
        String randomSystemNumber = GenericData.generateRandomSystemNumber();
        String randomTestResultId = UUID.randomUUID().toString();
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationTestResultId = new JsonPathAlteration("$.testResultId", randomTestResultId, "", "REPLACE");
        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$", randomSystemNumber,"systemNumber","ADD_FIELD");
        // Collate the list of alterations.
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(
                alterationVin,
                alterationTestResultId,
                alterationSystemNumber));

        // Post the results, together with any alterations, and verify that they are accepted.
        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, alterations);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");

        testResultsSteps.getTestResults(randomSystemNumber);
        testResultsSteps.statusCodeShouldBe(200);
        String testNumber = testResultsSteps.getTestNumber();

        //Verify that the certificate is generated in S3 bucket
        testResultsSteps.validateCertificateIsGenerated(testNumber,randomVin);
    }

    @Title("CVSB-7677 - TC - AC2 - CERTIFICATE GENERATED ON CORRECT TEST CODES (HGV)(QJV5) - FAIL ")
    @Test
    public void testResults_Roadworthiness_HGV_QJV5_Fail_Certificate_Generation() {

        // Read the base test result JSON.
        String testResultRecord = test_results_roadworthiness_hgv_fail_qjv5_7677_json;

        // Create alteration to add one more tech record to in the request body
        String randomVin = GenericData.generateRandomVin();
        String randomSystemNumber = GenericData.generateRandomSystemNumber();
        String randomTestResultId = UUID.randomUUID().toString();
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationTestResultId = new JsonPathAlteration("$.testResultId", randomTestResultId, "", "REPLACE");
        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$", randomSystemNumber,"systemNumber","ADD_FIELD");
        // Collate the list of alterations.
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(
                alterationVin,
                alterationTestResultId,
                alterationSystemNumber));

        // Post the results, together with any alterations, and verify that they are accepted.
        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, alterations);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");

        testResultsSteps.getTestResults(randomSystemNumber);
        testResultsSteps.statusCodeShouldBe(200);
        String testNumber = testResultsSteps.getTestNumber();

        //Verify that the certificate is generated in S3 bucket
        testResultsSteps.validateCertificateIsGenerated(testNumber,randomVin);
    }

    
    @Title("CVSB-7677 - TC - AC2 - CERTIFICATE GENERATED ON CORRECT TEST CODES (HGV)(QKV) - FAIL ")
    @Test
    public void testResults_Roadworthiness_HGV_QKV_Fail_Certificate_Generation() {

        // POST tech-record,read the base tech-record JSON.
        String postRequestBody = GenericData.readJsonValueFromFile("technical-records_hgv_all_fields.json","$");

        // Create alteration to edit one or more fields in the request body
        String randomVin = GenericData.generateRandomVin();
        JsonPathAlteration alterationVinVehicle = new JsonPathAlteration("$.vin", randomVin,"","REPLACE");

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

        String testResultRecord = test_results_roadworthiness_hgv_fail_qkv_7677_json;
        String randomTestResultId = UUID.randomUUID().toString();
        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$", systemNumber,"systemNumber","ADD_FIELD");
        JsonPathAlteration alterationVinTestResults = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationTestResultIdPost = new JsonPathAlteration("$.testResultId", randomTestResultId, "", "REPLACE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterationsTestResults = new ArrayList<>(Arrays.asList(
                alterationSystemNumber,
                alterationVinTestResults,
                alterationTestResultIdPost));

        // Post the test-results, together with any alterations, and verify that they are accepted.
        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, alterationsTestResults);
        testResultsSteps.statusCodeShouldBe(HttpStatus.SC_CREATED);
        testResultsSteps.validateData("Test records created");

        testResultsSteps.getTestResults(systemNumber);
        testResultsSteps.statusCodeShouldBe(200);
        String testNumber = testResultsSteps.getTestNumber();

        //Verify that the certificate is generated in S3 bucket
        testResultsSteps.validateCertificateIsGenerated(testNumber,randomVin);
    }

    @Title("CVSB-7677 - TC - AC2 - CERTIFICATE GENERATED ON CORRECT TEST CODES (TRL)(QKT) - FAIL ")
    @Test
    public void testResults_Roadworthiness_TRL_QKT_Fail_Certificate_Generation() {

        // Read the base test result JSON.
        String testResultRecord = test_results_roadworthiness_trl_fail_qkt_7677_json;

        // Create alteration to add one more tech record to in the request body
        String randomVin = GenericData.generateRandomVin();
        String randomSystemNumber = GenericData.generateRandomSystemNumber();
        String randomTestResultId = UUID.randomUUID().toString();
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationTestResultId = new JsonPathAlteration("$.testResultId", randomTestResultId, "", "REPLACE");
        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$", randomSystemNumber,"systemNumber","ADD_FIELD");
        // Collate the list of alterations.
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(
                alterationVin,
                alterationTestResultId,
                alterationSystemNumber));

        // Post the results, together with any alterations, and verify that they are accepted.
        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, alterations);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");

        testResultsSteps.getTestResults(randomSystemNumber);
        testResultsSteps.statusCodeShouldBe(200);
        String testNumber = testResultsSteps.getTestNumber();

        //Verify that the certificate is generated in S3 bucket
        testResultsSteps.validateCertificateIsGenerated(testNumber,randomVin);
    }

    @Title("CVSB-7677 - TC - AC2 - CERTIFICATE GENERATED ON CORRECT TEST CODES (HGV)(QPV) - FAIL ")
    @Test
    public void testResults_Roadworthiness_HGV_QPV_Fail_Certificate_Generation() {

        // Read the base test result JSON.
        String testResultRecord = test_results_roadworthiness_hgv_fail_qpv_7677_json;

        // Create alteration to add one more tech record to in the request body
        String randomVin = GenericData.generateRandomVin();
        String randomSystemNumber = GenericData.generateRandomSystemNumber();
        String randomTestResultId = UUID.randomUUID().toString();
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationTestResultId = new JsonPathAlteration("$.testResultId", randomTestResultId, "", "REPLACE");
        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$", randomSystemNumber,"systemNumber","ADD_FIELD");
        // Collate the list of alterations.
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(
                alterationVin,
                alterationTestResultId,
                alterationSystemNumber));

        // Post the results, together with any alterations, and verify that they are accepted.
        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, alterations);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");

        testResultsSteps.getTestResults(randomSystemNumber);
        testResultsSteps.statusCodeShouldBe(200);
        String testNumber = testResultsSteps.getTestNumber();

        //Verify that the certificate is generated in S3 bucket
//        testResultsSteps.validateCertificateIsGenerated(testNumber,randomVin);
    }

    @Title("CVSB-7677 - TC - AC2 - CERTIFICATE GENERATED ON CORRECT TEST CODES (TRL)(QPT) - FAIL ")
    @Test
    public void testResults_Roadworthiness_TRL_QPT_Fail_Certificate_Generation() {

        // Read the base test result JSON.
        String testResultRecord = test_results_roadworthiness_trl_fail_qpt_7677_json;

        // Create alteration to add one more tech record to in the request body
        String randomVin = GenericData.generateRandomVin();
        String randomSystemNumber = GenericData.generateRandomSystemNumber();
        String randomTestResultId = UUID.randomUUID().toString();
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationTestResultId = new JsonPathAlteration("$.testResultId", randomTestResultId, "", "REPLACE");
        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$", randomSystemNumber,"systemNumber","ADD_FIELD");
        // Collate the list of alterations.
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(
                alterationVin,
                alterationTestResultId,
                alterationSystemNumber));

        // Post the results, together with any alterations, and verify that they are accepted.
        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, alterations);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");

        testResultsSteps.getTestResults(randomSystemNumber);
        testResultsSteps.statusCodeShouldBe(200);
        String testNumber = testResultsSteps.getTestNumber();

        //Verify that the certificate is generated in S3 bucket
        testResultsSteps.validateCertificateIsGenerated(testNumber,randomVin);
    }

    @Title("CVSB-7677 - TC - AC2 - CERTIFICATE GENERATED ON CORRECT TEST CODES (HGV)(QQV) - FAIL ")
    @Test
    public void testResults_Roadworthiness_HGV_QQV_Fail_Certificate_Generation() {

        // Read the base test result JSON.
        String testResultRecord = test_results_roadworthiness_hgv_fail_qqv_7677_json;

        // Create alteration to add one more tech record to in the request body
        String randomVin = GenericData.generateRandomVin();
        String randomSystemNumber = GenericData.generateRandomSystemNumber();
        String randomTestResultId = UUID.randomUUID().toString();
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationTestResultId = new JsonPathAlteration("$.testResultId", randomTestResultId, "", "REPLACE");
        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$", randomSystemNumber,"systemNumber","ADD_FIELD");
        // Collate the list of alterations.
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(
                alterationVin,
                alterationTestResultId,
                alterationSystemNumber));

        // Post the results, together with any alterations, and verify that they are accepted.
        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, alterations);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");

        testResultsSteps.getTestResults(randomSystemNumber);
        testResultsSteps.statusCodeShouldBe(200);
        String testNumber = testResultsSteps.getTestNumber();

        //Verify that the certificate is generated in S3 bucket
        testResultsSteps.validateCertificateIsGenerated(testNumber,randomVin);
    }

    @Title("CVSB-7677 - TC - AC2 - CERTIFICATE GENERATED ON CORRECT TEST CODES (TRL)(QQT) - FAIL ")
    @Test
    public void testResults_Roadworthiness_TRL_QQT_Fail_Certificate_Generation() {

        // POST tech-record,read the base tech-record JSON.
        String postRequestBody = GenericData.readJsonValueFromFile("technical-records_trl_all_fields.json","$");

        // Create alteration to edit one or more fields in the request body
        String randomVin = GenericData.generateRandomVin();
        String randomVrm = GenericData.generateRandomVrm();
        String randomTrailerId = GenericData.generateRandomTrailerId();
        JsonPathAlteration alterationVinVehicle = new JsonPathAlteration("$.vin", randomVin,"","REPLACE");
        JsonPathAlteration alterationVrmVehicle = new JsonPathAlteration("$.primaryVrm", randomVrm,"","REPLACE");
        JsonPathAlteration alterationTrailerId = new JsonPathAlteration("$.trailerId", randomTrailerId,"","REPLACE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterationsVehicle = new ArrayList<>(Arrays.asList(alterationVinVehicle,
                alterationVrmVehicle,
                alterationTrailerId));

        // Post the tech-record, together with any alterations, and verify that they are accepted.
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBody, alterationsVehicle);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(HttpStatus.SC_CREATED);
        vehicleTechnicalRecordsSteps.validateData("Technical Record created");

        //GET tech-records
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsBySearchCriteria(randomVin, VehicleTechnicalRecordSearchCriteria.VIN);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(HttpStatus.SC_OK);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].statusCode", "provisional");

        String systemNumber = vehicleTechnicalRecordsSteps.getSystemNumber();

        System.out.println(systemNumber);

        // read base JSON for POST test-results
        String testResultRecord = test_results_roadworthiness_trl_fail_qqt_7677_json;

        String randomTestResultId = UUID.randomUUID().toString();
        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$", systemNumber,"systemNumber","ADD_FIELD");
        JsonPathAlteration alterationVinTestResults = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationTestResultIdPost = new JsonPathAlteration("$.testResultId", randomTestResultId, "", "REPLACE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterationsTestResults = new ArrayList<>(Arrays.asList(
                alterationSystemNumber,
                alterationVinTestResults,
                alterationTestResultIdPost));

        // Post the test-results, together with any alterations, and verify that they are accepted.
        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, alterationsTestResults);
        testResultsSteps.statusCodeShouldBe(HttpStatus.SC_CREATED);
        testResultsSteps.validateData("Test records created");

        testResultsSteps.getTestResults(systemNumber);
        testResultsSteps.statusCodeShouldBe(200);
        String testNumber = testResultsSteps.getTestNumber();

        //Verify that the certificate is generated in S3 bucket
        testResultsSteps.validateCertificateIsGenerated(testNumber,randomVin);
    }

    @Title("CVSB-7677 - TC - AC2 - CERTIFICATE GENERATED ON CORRECT TEST CODES (TRL)(RUT) - FAIL ")
    @Test
    public void testResults_Roadworthiness_TRL_RUT_Fail_Certificate_Generation() {

        // POST tech-record,read the base tech-record JSON.
        String postRequestBody = GenericData.readJsonValueFromFile("technical-records_trl_all_fields.json","$");

        // Create alteration to edit one or more fields in the request body
        String randomVin = GenericData.generateRandomVin();
        String randomVrm = GenericData.generateRandomVrm();
        String randomTrailerId = GenericData.generateRandomTrailerId();
        JsonPathAlteration alterationVinVehicle = new JsonPathAlteration("$.vin", randomVin,"","REPLACE");
        JsonPathAlteration alterationVrmVehicle = new JsonPathAlteration("$.primaryVrm", randomVrm,"","REPLACE");
        JsonPathAlteration alterationTrailerId = new JsonPathAlteration("$.trailerId", randomTrailerId,"","REPLACE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterationsVehicle = new ArrayList<>(Arrays.asList(alterationVinVehicle,
                alterationVrmVehicle,
                alterationTrailerId));

        // Post the tech-record, together with any alterations, and verify that they are accepted.
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBody, alterationsVehicle);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(HttpStatus.SC_CREATED);
        vehicleTechnicalRecordsSteps.validateData("Technical Record created");

        //GET tech-records
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsBySearchCriteria(randomVin, VehicleTechnicalRecordSearchCriteria.VIN);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(HttpStatus.SC_OK);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].statusCode", "provisional");
        String systemNumber = vehicleTechnicalRecordsSteps.getSystemNumber();

        // read base JSON for POST test-results
        String testResultRecord = test_results_roadworthiness_trl_fail_rut_7677_json;

        String randomTestResultId = UUID.randomUUID().toString();
        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$", systemNumber,"systemNumber","ADD_FIELD");
        JsonPathAlteration alterationVinTestResults = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationTestResultIdPost = new JsonPathAlteration("$.testResultId", randomTestResultId, "", "REPLACE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterationsTestResults = new ArrayList<>(Arrays.asList(
                alterationSystemNumber,
                alterationVinTestResults,
                alterationTestResultIdPost));

        // Post the test-results, together with any alterations, and verify that they are accepted.
        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, alterationsTestResults);
        testResultsSteps.statusCodeShouldBe(HttpStatus.SC_CREATED);
        testResultsSteps.validateData("Test records created");

        testResultsSteps.getTestResults(systemNumber);
        testResultsSteps.statusCodeShouldBe(200);
        String testNumber = testResultsSteps.getTestNumber();

        //Verify that the certificate is generated in S3 bucket
        testResultsSteps.validateCertificateIsGenerated(testNumber,randomVin);
    }

    @Title("CVSB-7677 - TC - AC2 - CERTIFICATE GENERATED ON CORRECT TEST CODES (HGV)(RUV) - FAIL ")
    @Test
    public void testResults_Roadworthiness_HGV_RUV_Fail_Certificate_Generation() {

        // Read the base test result JSON.
        String testResultRecord = test_results_roadworthiness_hgv_fail_ruv_7677_json;

        // Create alteration to add one more tech record to in the request body
        String randomVin = GenericData.generateRandomVin();
        String randomSystemNumber = GenericData.generateRandomSystemNumber();
        String randomTestResultId = UUID.randomUUID().toString();
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationTestResultId = new JsonPathAlteration("$.testResultId", randomTestResultId, "", "REPLACE");
        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$", randomSystemNumber,"systemNumber","ADD_FIELD");
        // Collate the list of alterations.
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(
                alterationVin,
                alterationTestResultId,
                alterationSystemNumber));

        // Post the results, together with any alterations, and verify that they are accepted.
        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, alterations);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        testResultsSteps.sleep();

        testResultsSteps.getTestResults(randomSystemNumber);
        testResultsSteps.statusCodeShouldBe(200);
        String testNumber = testResultsSteps.getTestNumber();

        //Verify that the certificate is generated in S3 bucket
        testResultsSteps.validateCertificateIsGenerated(testNumber,randomVin);
    }
}
