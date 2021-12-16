package testresults;

import data.GenericData;
import io.restassured.http.ContentType;
import junit.framework.TestCase;
import model.testresults.TestResultsStatus;
import model.testresults.TestVersion;
import model.vehicles.VehicleTechnicalRecordSearchCriteria;
import model.vehicles.VehicleTechnicalRecordStatus;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import io.restassured.response.Response;
import net.thucydides.core.annotations.Title;
import net.thucydides.core.annotations.WithTag;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.http.HttpStatus;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import steps.VehicleTechnicalRecordsSteps;
import util.BasePathFilter;
import util.JsonPathAlteration;
import steps.TestResultsSteps;
import static io.restassured.RestAssured.given;
import static util.WriterReader.saveUtils;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

@RunWith(SerenityRunner.class)
public class PutTestResultsCheckVisitTimeBoundaries extends TestCase{

    static String testerStaffId;

    @BeforeClass
    public static void createActivity() {

        Date date  = new Date();

        // Read POST json for activities
        String postActivitiesBody = GenericData.readJsonValueFromFile("activities-post_payload_12378.json", "$");

        // Create alterations for the timestamps
        JsonPathAlteration alterationStartTime = new JsonPathAlteration("$.startTime",
                new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(date),"","REPLACE");
        JsonPathAlteration alterationEndTime = new JsonPathAlteration("$.endTime", new SimpleDateFormat(
                "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 540)),"","REPLACE");
        testerStaffId = UUID.randomUUID().toString();
        JsonPathAlteration alterationTesterStaffId = new JsonPathAlteration("$.testerStaffId", testerStaffId,
                "","REPLACE");

        // Collate the alterations
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(alterationStartTime, alterationEndTime,
                alterationTesterStaffId));

        String alteredBody = GenericData.applyJsonAlterations(postActivitiesBody, alterations);
        Response response = given().filters(new BasePathFilter())
                .contentType(ContentType.JSON)
                .body(alteredBody)
                .log().method().log().uri().log().body()
                .post("/activities");
        if (response.getStatusCode() == HttpStatus.SC_UNAUTHORIZED || response.getStatusCode() == HttpStatus.SC_FORBIDDEN) {
            saveUtils();
            response = given().filters(new BasePathFilter())
                    .contentType(ContentType.JSON)
                    .body(alteredBody)
                    .log().method().log().uri().log().body()
                    .post("/activities");

        }
        Assert.assertEquals(HttpStatus.SC_CREATED, response.getStatusCode());
    }

    @Steps
    VehicleTechnicalRecordsSteps vehicleTechnicalRecordsSteps;

    @Steps
    TestResultsSteps testResultsSteps;

    Date date  = new Date();


    @WithTag("Vtm")
    @Title("CVSB-12378 - AC9. PUT: Attempt to update a test record with a testTypeStartTimestamp outside of the visit time boundaries")
    @Test
    public void testPutTestResultsInvalidTestTypeStartTimestamp() {

        LocalDateTime testStartDate = LocalDateTime.now();

        // Read the base tech-record JSON.
        String postRequestBody = GenericData.readJsonValueFromFile("technical-records_hgv_all_fields.json", "$");

        // Create alteration to edit the fields in json
        String randomSystemNumber = GenericData.generateRandomSystemNumber();
        String randomVin = GenericData.generateRandomVin();
        JsonPathAlteration alterationSystemNumberVehicle = new JsonPathAlteration("$.systemNumber", randomSystemNumber,
                "", "REPLACE");
        JsonPathAlteration alterationVinVehicle = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterationsVehicle = new ArrayList<>(Arrays.asList(alterationSystemNumberVehicle, alterationVinVehicle));

        // Post the tech-records, together with any alterations, and verify that they are accepted.
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBody, alterationsVehicle);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(HttpStatus.SC_CREATED);
        vehicleTechnicalRecordsSteps.validateData("Technical Record created");
        vehicleTechnicalRecordsSteps.waitForVehicleRecordUpdate(randomVin, 25, testStartDate);

        // Read the base test-result JSON.
        String testResultRecord = GenericData.readJsonValueFromFile("test-results_post_payload_12378.json", "$");

        // Create alteration to edit the fields in json
        String randomTestResultId = UUID.randomUUID().toString();
        JsonPathAlteration alterationSystemNumberTestResults = new JsonPathAlteration("$.systemNumber", randomSystemNumber,
                "", "REPLACE");
        JsonPathAlteration alterationVinTestResults = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationTestResultIdPost = new JsonPathAlteration("$.testResultId", randomTestResultId,
                "", "REPLACE");
        JsonPathAlteration alterationTestStartTimestamp = new JsonPathAlteration("$.testStartTimestamp", new SimpleDateFormat(
                "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 1)), "", "REPLACE");
        JsonPathAlteration alterationTestEndTimestamp = new JsonPathAlteration("$.testEndTimestamp", new SimpleDateFormat(
                "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 4)), "", "REPLACE");
        JsonPathAlteration alterationTestTypeStartTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeStartTimestamp",
                new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 6)),
                "", "REPLACE");
        JsonPathAlteration alterationTestTypeEndTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeEndTimestamp",
                new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 3)),
                "", "REPLACE");
        JsonPathAlteration alterationTesterStaffId = new JsonPathAlteration("$.testerStaffId", testerStaffId, "",
                "REPLACE");


        // Collate the list of alterations.
        List<JsonPathAlteration> alterationsTestResults = new ArrayList<>(Arrays.asList(
                alterationSystemNumberTestResults,
                alterationVinTestResults,
                alterationTestResultIdPost,
                alterationTestStartTimestamp,
                alterationTestEndTimestamp,
                alterationTestTypeStartTimestamp,
                alterationTestTypeEndTimestamp,
                alterationTesterStaffId));

        // Post the results, together with any alterations, and verify that they are accepted.
        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, alterationsTestResults);
        testResultsSteps.statusCodeShouldBe(HttpStatus.SC_CREATED);
        testResultsSteps.validateData("Test records created");

        //Read the base json for PUT test-results
        String putRequestBody = GenericData.readJsonValueFromFile("test-results_put_payload_12378.json","$");

        // Create alterations to edit required fields in json
        JsonPathAlteration alterationSystemNumberPutTestResults = new JsonPathAlteration("$.testResult.systemNumber",
                randomSystemNumber, "", "REPLACE");
        JsonPathAlteration alterationVinPutTestResults = new JsonPathAlteration("$.testResult.vin", randomVin, "",
                "REPLACE");
        JsonPathAlteration alterationTestResultIdPut = new JsonPathAlteration("$.testResult.testResultId", randomTestResultId,
                "", "REPLACE");
        JsonPathAlteration alterationPutTestStartTimestamp = new JsonPathAlteration("$.testResult.testStartTimestamp",
                new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 1)) ,
                "", "REPLACE");
        JsonPathAlteration alterationPutTestEndTimestamp = new JsonPathAlteration("$.testResult.testEndTimestamp",
                new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 4)),
                "", "REPLACE");
        JsonPathAlteration alterationPutTestTypeStartTimestamp = new JsonPathAlteration("$.testResult.testTypes[0].testTypeStartTimestamp",
                new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 6)),
                "", "REPLACE");
        JsonPathAlteration alterationPutTestTypeEndTimestamp = new JsonPathAlteration("$.testResult.testTypes[0].testTypeEndTimestamp",
                new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 3)),
                "", "REPLACE");
        JsonPathAlteration alterationTesterStaffID = new JsonPathAlteration("$.testResult.testerStaffId", testerStaffId, "",
                "REPLACE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterationsPutTestResults = new ArrayList<>(Arrays.asList(
                alterationSystemNumberPutTestResults,
                alterationVinPutTestResults,
                alterationTestResultIdPut,
                alterationPutTestStartTimestamp,
                alterationPutTestEndTimestamp,
                alterationPutTestTypeStartTimestamp,
                alterationPutTestTypeEndTimestamp,
                alterationTesterStaffID
        ));

        // PUT test-results and verify the error status code is retrieved
        testResultsSteps.putTestResultsWithAlterations(randomSystemNumber,putRequestBody,alterationsPutTestResults);
        testResultsSteps.statusCodeShouldBe(400);
    }

    @WithTag("Vtm")
    @Title("CVSB-12378 - AC10. PUT: Attempt to update a test record with a testTypeEndTimestamp outside of the visit time boundaries")
    @Test
    public void testPutTestResultsInvalidTestTypeEndTimestamp() {

        // Read the base tech-records JSON.
        String postRequestBody = GenericData.readJsonValueFromFile("technical-records_hgv_all_fields.json", "$");

        // Create alteration to edit the required fields in the payload
        String randomSystemNumber = GenericData.generateRandomSystemNumber();
        String randomVin = GenericData.generateRandomVin();
        JsonPathAlteration alterationSystemNumberVehicle = new JsonPathAlteration("$.systemNumber", randomSystemNumber,
                "", "REPLACE");
        JsonPathAlteration alterationVinVehicle = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterationsVehicle = new ArrayList<>(Arrays.asList(alterationSystemNumberVehicle, alterationVinVehicle));

        // Post the tech-records, together with any alterations, and verify that they are accepted.
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBody, alterationsVehicle);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(HttpStatus.SC_CREATED);
        vehicleTechnicalRecordsSteps.validateData("Technical Record created");

        // Read the base test result JSON.
        String testResultRecord = GenericData.readJsonValueFromFile("test-results_post_payload_12378.json", "$");

        // Create alteration to edit the required fields in the payload
        String randomTestResultId = UUID.randomUUID().toString();
        JsonPathAlteration alterationSystemNumberTestResults = new JsonPathAlteration("$.systemNumber", randomSystemNumber,
                "", "REPLACE");
        JsonPathAlteration alterationVinTestResults = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationTestResultIdPost = new JsonPathAlteration("$.testResultId", randomTestResultId,
                "", "REPLACE");
        JsonPathAlteration alterationTestStartTimestamp = new JsonPathAlteration("$.testStartTimestamp",
                new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 1)),
                "", "REPLACE");
        JsonPathAlteration alterationTestEndTimestamp = new JsonPathAlteration("$.testEndTimestamp",
                new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 4)),
                "", "REPLACE");
        JsonPathAlteration alterationTestTypeStartTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeStartTimestamp",
                new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 2)),
                "", "REPLACE");
        JsonPathAlteration alterationTestTypeEndTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeEndTimestamp",
                new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 541)),
                "", "REPLACE");
        JsonPathAlteration alterationTesterStaffId = new JsonPathAlteration("$.testerStaffId", testerStaffId, "",
                "REPLACE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterationsTestResults = new ArrayList<>(Arrays.asList(
                alterationSystemNumberTestResults,
                alterationVinTestResults,
                alterationTestResultIdPost,
                alterationTestStartTimestamp,
                alterationTestEndTimestamp,
                alterationTestTypeStartTimestamp,
                alterationTestTypeEndTimestamp,
                alterationTesterStaffId));

        // Post the results, together with any alterations, and verify that they are accepted.
        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, alterationsTestResults);
        testResultsSteps.statusCodeShouldBe(HttpStatus.SC_CREATED);
        testResultsSteps.validateData("Test records created");


        //Read the base JSON for PUT test-results
        String putRequestBody = GenericData.readJsonValueFromFile("test-results_put_payload_12378.json","$");

        // Create alterations to edit one or more fields in the payload
        JsonPathAlteration alterationSystemNumberPutTestResults = new JsonPathAlteration("$.testResult.systemNumber",
                randomSystemNumber, "", "REPLACE");
        JsonPathAlteration alterationVinPutTestResults = new JsonPathAlteration("$.testResult.vin", randomVin,
                "", "REPLACE");
        JsonPathAlteration alterationTestResultIdPut = new JsonPathAlteration("$.testResult.testResultId", randomTestResultId,
                "", "REPLACE");
        JsonPathAlteration alterationPutTestStartTimestamp = new JsonPathAlteration("$.testResult.testStartTimestamp",
                new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 1)) ,
                "", "REPLACE");
        JsonPathAlteration alterationPutTestEndTimestamp = new JsonPathAlteration("$.testResult.testEndTimestamp",
                new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 4)),
                "", "REPLACE");
        JsonPathAlteration alterationPutTestTypeStartTimestamp = new JsonPathAlteration("$.testResult.testTypes[0].testTypeStartTimestamp",
                new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 2)),
                "", "REPLACE");
        JsonPathAlteration alterationPutTestTypeEndTimestamp = new JsonPathAlteration("$.testResult.testTypes[0].testTypeEndTimestamp",
                new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 541)),
                "", "REPLACE");
        JsonPathAlteration alterationTesterStaffID = new JsonPathAlteration("$.testResult.testerStaffId", testerStaffId, "",
                "REPLACE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterationsPutTestResults = new ArrayList<>(Arrays.asList(
                alterationSystemNumberPutTestResults,
                alterationVinPutTestResults,
                alterationTestResultIdPut,
                alterationPutTestStartTimestamp,
                alterationPutTestEndTimestamp,
                alterationPutTestTypeStartTimestamp,
                alterationPutTestTypeEndTimestamp,
                alterationTesterStaffID
        ));

        // PUT test-results and verify the error code is retrieved
        testResultsSteps.putTestResultsWithAlterations(randomSystemNumber,putRequestBody,alterationsPutTestResults);
        testResultsSteps.statusCodeShouldBe(400);
    }

    @Title("CVSB-10372 - AC6. PUT: Attempt to update a test record with a testTypeStartTimestamp outside of the visit time boundaries")
    @Test
    public void PutTestResultsForOutsideStartTime() {

        Date date = new Date();

        // Read the base test result JSON.
        String postRequestBody = GenericData.readJsonValueFromFile("technical-records_psv_all_fields.json", "$");

        // Create alteration to add one more tech record to in the request body
        String randomSystemNumber = GenericData.generateRandomSystemNumber();
        String randomVin = GenericData.generateRandomVin();
        JsonPathAlteration alterationSystemNumberVehicle = new JsonPathAlteration("$.systemNumber", randomSystemNumber, "", "REPLACE");
        JsonPathAlteration alterationVinVehicle = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterationsVehicle = new ArrayList<>(Arrays.asList(alterationSystemNumberVehicle,
                alterationVinVehicle));

        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBody, alterationsVehicle);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(HttpStatus.SC_CREATED);
        vehicleTechnicalRecordsSteps.validateData("Technical Record created");

        //GET tech-records
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsByStatusAndSearchCriteria(randomVin, VehicleTechnicalRecordStatus.ALL, VehicleTechnicalRecordSearchCriteria.VIN);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(HttpStatus.SC_OK);

        String systemNumber = vehicleTechnicalRecordsSteps.getSystemNumber();

        // Read base JSON to create test-results
        String testResultRecord = GenericData.readJsonValueFromFile("test-results_psv_post_payload_10372.json", "$");

        //Create alteration to add one more tech record to in the request body
        String randomTestResultId = UUID.randomUUID().toString();
        JsonPathAlteration alterationSystemNumberTestResults = new JsonPathAlteration("$.systemNumber", systemNumber, "", "REPLACE");
        JsonPathAlteration alterationVinTestResults = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationTestResultIdPost = new JsonPathAlteration("$.testResultId", randomTestResultId, "", "REPLACE");
        JsonPathAlteration alterationTestStartTimestamp = new JsonPathAlteration("$.testStartTimestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 1)), "", "REPLACE");
        JsonPathAlteration alterationTestEndTimestamp = new JsonPathAlteration("$.testEndTimestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 4)), "", "REPLACE");
        JsonPathAlteration alterationTestTypeStartTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeStartTimestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 6)), "", "REPLACE");
        JsonPathAlteration alterationTestTypeEndTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeEndTimestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 3)), "", "REPLACE");
        JsonPathAlteration alterationTesterStaffId = new JsonPathAlteration("$.testerStaffId", testerStaffId, "", "REPLACE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterationsTestResults = new ArrayList<>(Arrays.asList(
                alterationSystemNumberTestResults,
                alterationVinTestResults,
                alterationTestResultIdPost,
                alterationTestStartTimestamp,
                alterationTestEndTimestamp,
                alterationTestTypeStartTimestamp,
                alterationTestTypeEndTimestamp,
                alterationTesterStaffId));

        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, alterationsTestResults);
        testResultsSteps.statusCodeShouldBe(HttpStatus.SC_CREATED);
        testResultsSteps.validateData("Test records created");

        // Read the base JSON for PUT test-results
        String putRequestBody = GenericData.readJsonValueFromFile("test-results_psv_put_payload_10372.json", "$");

        JsonPathAlteration alterationSystemNumberPutTestResults = new JsonPathAlteration("$.testResult.systemNumber", systemNumber, "", "REPLACE");
        JsonPathAlteration alterationVinPutTestResults = new JsonPathAlteration("$.testResult.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationTestResultIdPut = new JsonPathAlteration("$.testResult.testResultId", randomTestResultId, "", "REPLACE");
        JsonPathAlteration alterationPutTestStartTimestamp = new JsonPathAlteration("$.testResult.testStartTimestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 1)), "", "REPLACE");
        JsonPathAlteration alterationPutTestEndTimestamp = new JsonPathAlteration("$.testResult.testEndTimestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 4)), "", "REPLACE");
        JsonPathAlteration alterationPutTestTypeStartTimestamp = new JsonPathAlteration("$.testResult.testTypes[0].testTypeStartTimestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 6)), "", "REPLACE");
        JsonPathAlteration alterationPutTestTypeEndTimestamp = new JsonPathAlteration("$.testResult.testTypes[0].testTypeEndTimestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 3)), "", "REPLACE");
        JsonPathAlteration alterationTesterStaffID = new JsonPathAlteration("$.testResult.testerStaffId", testerStaffId, "", "REPLACE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterationsPutTestResults = new ArrayList<>(Arrays.asList(
                alterationSystemNumberPutTestResults,
                alterationVinPutTestResults,
                alterationTestResultIdPut,
                alterationPutTestStartTimestamp,
                alterationPutTestEndTimestamp,
                alterationPutTestTypeStartTimestamp,
                alterationPutTestTypeEndTimestamp,
                alterationTesterStaffID
        ));

        testResultsSteps.putTestResultsWithAlterations(systemNumber, putRequestBody, alterationsPutTestResults);
        testResultsSteps.statusCodeShouldBe(HttpStatus.SC_BAD_REQUEST);

    }

    @Title("CVSB-10372 - AC7. PUT: Attempt to update a test record with a testTypeEndTimestamp outside of the visit time boundaries")
    @Test
    public void PutTestResultsForOutsideEndTime() {

        Date date = new Date();

        // Read the base test result JSON.
        String postRequestBody = GenericData.readJsonValueFromFile("technical-records_psv_all_fields.json", "$");

        // Create alteration to add one more tech record to in the request body
        String randomSystemNumber = GenericData.generateRandomSystemNumber();
        String randomVin = GenericData.generateRandomVin();
        JsonPathAlteration alterationSystemNumberVehicle = new JsonPathAlteration("$.systemNumber", randomSystemNumber, "", "REPLACE");
        JsonPathAlteration alterationVinVehicle = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterationsVehicle = new ArrayList<>(Arrays.asList(alterationSystemNumberVehicle,
                alterationVinVehicle));

        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBody, alterationsVehicle);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(HttpStatus.SC_CREATED);
        vehicleTechnicalRecordsSteps.validateData("Technical Record created");

        //GET tech-records
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsByStatusAndSearchCriteria(randomVin, VehicleTechnicalRecordStatus.ALL, VehicleTechnicalRecordSearchCriteria.VIN);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(HttpStatus.SC_OK);

        String systemNumber = vehicleTechnicalRecordsSteps.getSystemNumber();

        // Read base JSON to create test-results
        String testResultRecord = GenericData.readJsonValueFromFile("test-results_psv_post_payload_10372.json", "$");

        //Create alteration to add one more tech record to in the request body
        String randomTestResultId = UUID.randomUUID().toString();
        JsonPathAlteration alterationSystemNumberTestResults = new JsonPathAlteration("$.systemNumber", systemNumber, "", "REPLACE");
        JsonPathAlteration alterationVinTestResults = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationTestResultIdPost = new JsonPathAlteration("$.testResultId", randomTestResultId, "", "REPLACE");
        JsonPathAlteration alterationTestStartTimestamp = new JsonPathAlteration("$.testStartTimestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 1)), "", "REPLACE");
        JsonPathAlteration alterationTestEndTimestamp = new JsonPathAlteration("$.testEndTimestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 4)), "", "REPLACE");
        JsonPathAlteration alterationTestTypeStartTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeStartTimestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 2)), "", "REPLACE");
        JsonPathAlteration alterationTestTypeEndTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeEndTimestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 541)), "", "REPLACE");
        JsonPathAlteration alterationTesterStaffId = new JsonPathAlteration("$.testerStaffId", testerStaffId, "", "REPLACE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterationsTestResults = new ArrayList<>(Arrays.asList(
                alterationSystemNumberTestResults,
                alterationVinTestResults,
                alterationTestResultIdPost,
                alterationTestStartTimestamp,
                alterationTestEndTimestamp,
                alterationTestTypeStartTimestamp,
                alterationTestTypeEndTimestamp,
                alterationTesterStaffId));

        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, alterationsTestResults);
        testResultsSteps.statusCodeShouldBe(HttpStatus.SC_CREATED);
        testResultsSteps.validateData("Test records created");

        // Read the base JSON for PUT test-results
        String putRequestBody = GenericData.readJsonValueFromFile("test-results_psv_put_payload_10372.json", "$");

        JsonPathAlteration alterationSystemNumberPutTestResults = new JsonPathAlteration("$.testResult.systemNumber", systemNumber, "", "REPLACE");
        JsonPathAlteration alterationVinPutTestResults = new JsonPathAlteration("$.testResult.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationTestResultIdPut = new JsonPathAlteration("$.testResult.testResultId", randomTestResultId, "", "REPLACE");
        JsonPathAlteration alterationPutTestStartTimestamp = new JsonPathAlteration("$.testResult.testStartTimestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 1)), "", "REPLACE");
        JsonPathAlteration alterationPutTestEndTimestamp = new JsonPathAlteration("$.testResult.testEndTimestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 4)), "", "REPLACE");
        JsonPathAlteration alterationPutTestTypeStartTimestamp = new JsonPathAlteration("$.testResult.testTypes[0].testTypeStartTimestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 2)), "", "REPLACE");
        JsonPathAlteration alterationPutTestTypeEndTimestamp = new JsonPathAlteration("$.testResult.testTypes[0].testTypeEndTimestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 541)), "", "REPLACE");
        JsonPathAlteration alterationTesterStaffID = new JsonPathAlteration("$.testResult.testerStaffId", testerStaffId, "", "REPLACE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterationsPutTestResults = new ArrayList<>(Arrays.asList(
                alterationSystemNumberPutTestResults,
                alterationVinPutTestResults,
                alterationTestResultIdPut,
                alterationPutTestStartTimestamp,
                alterationPutTestEndTimestamp,
                alterationPutTestTypeStartTimestamp,
                alterationPutTestTypeEndTimestamp,
                alterationTesterStaffID
        ));

        testResultsSteps.putTestResultsWithAlterations(systemNumber, putRequestBody, alterationsPutTestResults);
        testResultsSteps.statusCodeShouldBe(HttpStatus.SC_BAD_REQUEST);

    }
}
