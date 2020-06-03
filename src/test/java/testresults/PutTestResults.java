package testresults;

import data.GenericData;
import io.restassured.http.ContentType;
import junit.framework.TestCase;
import model.testresults.TestResultsStatus;
import model.testresults.TestVersion;
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
import java.util.*;

@RunWith(SerenityRunner.class)
public class PutTestResults extends TestCase{

    @Steps
    VehicleTechnicalRecordsSteps vehicleTechnicalRecordsSteps;

    @Steps
    TestResultsSteps testResultsSteps;

    Date date  = new Date();


    @WithTag("Vtm")
    @Title("CVSB-12378 - AC2. PUT: Attempt to update the defect array when a defect is deleted/removed from a test record")
    @Test
    public void testPutTestResultsDeleteDefects() {

        // Read Payload for POST tech-records
        String postRequestBody = GenericData.readJsonValueFromFile("technical-records_hgv_all_fields.json", "$");

        // Create alteration for SystemNumber and vin
        String randomSystemNumber = GenericData.generateRandomSystemNumber();
        String randomVin = GenericData.generateRandomVin();
        JsonPathAlteration alterationSystemNumberVehicle = new JsonPathAlteration("$.systemNumber", randomSystemNumber, "", "REPLACE");
        JsonPathAlteration alterationVinVehicle = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterationsVehicle = new ArrayList<>(Arrays.asList(alterationSystemNumberVehicle, alterationVinVehicle));

        // Post the results, together with any alterations, and verify that they are accepted.
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBody, alterationsVehicle);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(HttpStatus.SC_CREATED);
        vehicleTechnicalRecordsSteps.validateData("Technical Record created");

        // Read Payload for POST test-results
        String testResultRecord = GenericData.readJsonValueFromFile("test-results_post_payload_12378.json", "$");

        // Create alteration for timestamps and SystemNumber and vin
        String randomTestResultId = UUID.randomUUID().toString();
        JsonPathAlteration alterationSystemNumberTestResults = new JsonPathAlteration("$.systemNumber", randomSystemNumber, "", "REPLACE");
        JsonPathAlteration alterationVinTestResults = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationTestResultIdPost = new JsonPathAlteration("$.testResultId", randomTestResultId, "", "REPLACE");
        JsonPathAlteration alterationTestStartTimestamp = new JsonPathAlteration("$.testStartTimestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 1)), "", "REPLACE");
        JsonPathAlteration alterationTestEndTimestamp = new JsonPathAlteration("$.testEndTimestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 4)), "", "REPLACE");
        JsonPathAlteration alterationTestTypeStartTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeStartTimestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 2)), "", "REPLACE");
        JsonPathAlteration alterationTestTypeEndTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeEndTimestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 3)), "", "REPLACE");


        // Collate the list of alterations.
        List<JsonPathAlteration> alterationsTestResults = new ArrayList<>(Arrays.asList(
                alterationSystemNumberTestResults,
                alterationVinTestResults,
                alterationTestResultIdPost,
                alterationTestStartTimestamp,
                alterationTestEndTimestamp,
                alterationTestTypeStartTimestamp,
                alterationTestTypeEndTimestamp));

        // Post the test-results, together with any alterations, and verify that they are accepted.
        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, alterationsTestResults);
        testResultsSteps.statusCodeShouldBe(HttpStatus.SC_CREATED);
        testResultsSteps.validateData("Test records created");

        // GET test-results
        testResultsSteps.getTestResults(randomSystemNumber);
        testResultsSteps.statusCodeShouldBe(HttpStatus.SC_OK);

        //Read the payload for PUT test-results
        String putRequestBody = GenericData.readJsonValueFromFile("test-results_put_payload_12378.json","$");

        // Create alterations to edit one or more fields
        JsonPathAlteration alterationSystemNumberPutTestResults = new JsonPathAlteration("$.testResult.systemNumber", randomSystemNumber, "", "REPLACE");
        JsonPathAlteration alterationVinPutTestResults = new JsonPathAlteration("$.testResult.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationTestResultIdPut = new JsonPathAlteration("$.testResult.testResultId", randomTestResultId, "", "REPLACE");
        JsonPathAlteration alterationPutTestStartTimestamp = new JsonPathAlteration("$.testResult.testStartTimestamp",new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 1)) , "", "REPLACE");
        JsonPathAlteration alterationPutTestEndTimestamp = new JsonPathAlteration("$.testResult.testEndTimestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 4)), "", "REPLACE");
        JsonPathAlteration alterationPutTestTypeStartTimestamp = new JsonPathAlteration("$.testResult.testTypes[0].testTypeStartTimestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 2)), "", "REPLACE");
        JsonPathAlteration alterationPutTestTypeEndTimestamp = new JsonPathAlteration("$.testResult.testTypes[0].testTypeEndTimestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 3)), "", "REPLACE");


        // Collate the list of alterations.
        List<JsonPathAlteration> alterationsPutTestResults = new ArrayList<>(Arrays.asList(
                alterationSystemNumberPutTestResults,
                alterationVinPutTestResults,
                alterationTestResultIdPut,
                alterationPutTestStartTimestamp,
                alterationPutTestEndTimestamp,
                alterationPutTestTypeStartTimestamp,
                alterationPutTestTypeEndTimestamp
        ));

        // PUT test-results and verify that they are accepted
        testResultsSteps.putTestResultsWithAlterations(randomSystemNumber,putRequestBody,alterationsPutTestResults);
        testResultsSteps.statusCodeShouldBe(HttpStatus.SC_OK);


        testResultsSteps.valueForFieldInPathShouldBe("testTypes[0].defects.size()", 1);
        testResultsSteps.valueForFieldInPathShouldBe("testVersion","current");
        testResultsSteps.valueForFieldInPathShouldBe("testHistory[0].testVersion","archived");

        testResultsSteps.getTestResults(randomSystemNumber,TestVersion.ALL,randomTestResultId, TestResultsStatus.CANCELED);
        testResultsSteps.statusCodeShouldBe(HttpStatus.SC_OK);
        testResultsSteps.valueForFieldInPathShouldBe("[0].testVersion","current");
        testResultsSteps.valueForFieldInPathShouldBe("[0].testHistory[0].testVersion","archived");

        testResultsSteps.getTestResults(randomSystemNumber, TestVersion.ARCHIVED ,randomTestResultId,TestResultsStatus.CANCELED);
        testResultsSteps.statusCodeShouldBe(HttpStatus.SC_OK);
        testResultsSteps.valueForFieldInPathShouldBe("[0].testVersion","archived");
        testResultsSteps.fieldInPathShouldNotExist("[0]","testHistory");

        testResultsSteps.getTestResults(randomSystemNumber, TestVersion.CURRENT ,randomTestResultId,TestResultsStatus.CANCELED);
        testResultsSteps.statusCodeShouldBe(HttpStatus.SC_OK);
        testResultsSteps.valueForFieldInPathShouldBe("[0].testVersion","current");
        testResultsSteps.fieldInPathShouldNotExist("[0]","testHistory");
    }

    @WithTag("Vtm")
    @Title("CVSB-12378 - AC3. PUT: Attempt to update the defect array when a new defect is added to a test record")
    @Test
    public void testPutTestResultsForAddDefects() {

        // Read Payload for POST tech-records
        String postRequestBody = GenericData.readJsonValueFromFile("technical-records_hgv_all_fields.json", "$");

        // Create alteration for SystemNumber and vin
        String randomSystemNumber = GenericData.generateRandomSystemNumber();
        String randomVin = GenericData.generateRandomVin();
        JsonPathAlteration alterationSystemNumberVehicle = new JsonPathAlteration("$.systemNumber", randomSystemNumber, "", "REPLACE");
        JsonPathAlteration alterationVinVehicle = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterationsVehicle = new ArrayList<>(Arrays.asList(alterationSystemNumberVehicle, alterationVinVehicle));

        // Post tech-records, together with any alterations, and verify that they are accepted.
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBody, alterationsVehicle);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(HttpStatus.SC_CREATED);
        vehicleTechnicalRecordsSteps.validateData("Technical Record created");

        // Read Payload for POST test-results
        String testResultRecord = GenericData.readJsonValueFromFile("test-results_post_payload_12378.json", "$");

        // Create alteration for timestamps and SystemNumber and vin
        String randomTestResultId = UUID.randomUUID().toString();
        JsonPathAlteration alterationSystemNumberTestResults = new JsonPathAlteration("$.systemNumber", randomSystemNumber, "", "REPLACE");
        JsonPathAlteration alterationVinTestResults = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationTestResultIdPost = new JsonPathAlteration("$.testResultId", randomTestResultId, "", "REPLACE");
        JsonPathAlteration alterationTestStartTimestamp = new JsonPathAlteration("$.testStartTimestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 1)), "", "REPLACE");
        JsonPathAlteration alterationTestEndTimestamp = new JsonPathAlteration("$.testEndTimestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 4)), "", "REPLACE");
        JsonPathAlteration alterationTestTypeStartTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeStartTimestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 2)), "", "REPLACE");
        JsonPathAlteration alterationTestTypeEndTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeEndTimestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 3)), "", "REPLACE");


        // Collate the list of alterations.
        List<JsonPathAlteration> alterationsTestResults = new ArrayList<>(Arrays.asList(
                alterationSystemNumberTestResults,
                alterationVinTestResults,
                alterationTestResultIdPost,
                alterationTestStartTimestamp,
                alterationTestEndTimestamp,
                alterationTestTypeStartTimestamp,
                alterationTestTypeEndTimestamp));

        // Post the test-results, together with any alterations, and verify that they are accepted.
        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, alterationsTestResults);
        testResultsSteps.statusCodeShouldBe(HttpStatus.SC_CREATED);
        testResultsSteps.validateData("Test records created");

        // GET test-results
        testResultsSteps.getTestResults(randomSystemNumber);
        testResultsSteps.statusCodeShouldBe(HttpStatus.SC_OK);


        //Read the payload for PUT test-results
        String putRequestBody = GenericData.readJsonValueFromFile("test-results_put_payload_defect_12378.json","$");

        // Create alterations to edit the required fields in the json
        JsonPathAlteration alterationSystemNumberPutTestResults = new JsonPathAlteration("$.testResult.systemNumber", randomSystemNumber, "", "REPLACE");
        JsonPathAlteration alterationVinPutTestResults = new JsonPathAlteration("$.testResult.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationTestResultIdPut = new JsonPathAlteration("$.testResult.testResultId", randomTestResultId, "", "REPLACE");
        JsonPathAlteration alterationPutTestStartTimestamp = new JsonPathAlteration("$.testResult.testStartTimestamp",new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 1)) , "", "REPLACE");
        JsonPathAlteration alterationPutTestEndTimestamp = new JsonPathAlteration("$.testResult.testEndTimestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 4)), "", "REPLACE");
        JsonPathAlteration alterationPutTestTypeStartTimestamp = new JsonPathAlteration("$.testResult.testTypes[0].testTypeStartTimestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 2)), "", "REPLACE");
        JsonPathAlteration alterationPutTestTypeEndTimestamp = new JsonPathAlteration("$.testResult.testTypes[0].testTypeEndTimestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 3)), "", "REPLACE");


        // Collate the list of alterations.
        List<JsonPathAlteration> alterationsPutTestResults = new ArrayList<>(Arrays.asList(
                alterationSystemNumberPutTestResults,
                alterationVinPutTestResults,
                alterationTestResultIdPut,
                alterationPutTestStartTimestamp,
                alterationPutTestEndTimestamp,
                alterationPutTestTypeStartTimestamp,
                alterationPutTestTypeEndTimestamp
        ));

        // PUT test -results and verify that they are accepted
        testResultsSteps.putTestResultsWithAlterations(randomSystemNumber,putRequestBody,alterationsPutTestResults);
        testResultsSteps.statusCodeShouldBe(HttpStatus.SC_OK);

        testResultsSteps.valueForFieldInPathShouldBe("testTypes[0].defects.size()", 3);
        testResultsSteps.valueForFieldInPathShouldBe("testVersion","current");
        testResultsSteps.valueForFieldInPathShouldBe("testHistory[0].testVersion","archived");

        testResultsSteps.getTestResults(randomSystemNumber,TestVersion.ALL,randomTestResultId, TestResultsStatus.CANCELED);
        testResultsSteps.statusCodeShouldBe(HttpStatus.SC_OK);
        testResultsSteps.valueForFieldInPathShouldBe("[0].testVersion","current");
        testResultsSteps.valueForFieldInPathShouldBe("[0].testHistory[0].testVersion","archived");

        testResultsSteps.getTestResults(randomSystemNumber, TestVersion.ARCHIVED ,randomTestResultId,TestResultsStatus.CANCELED);
        testResultsSteps.statusCodeShouldBe(HttpStatus.SC_OK);
        testResultsSteps.valueForFieldInPathShouldBe("[0].testVersion","archived");
        testResultsSteps.fieldInPathShouldNotExist("[0]","testHistory");

        testResultsSteps.getTestResults(randomSystemNumber, TestVersion.CURRENT ,randomTestResultId,TestResultsStatus.CANCELED);
        testResultsSteps.statusCodeShouldBe(HttpStatus.SC_OK);
        testResultsSteps.valueForFieldInPathShouldBe("[0].testVersion","current");
        testResultsSteps.fieldInPathShouldNotExist("[0]","testHistory");
    }

    @WithTag("Vtm")
    @Title("CVSB-12378 - AC4. PUT request: testNumber attribute remains the same when the test record is updated with a new Test Type +" +
            "AC1: PUT+ request: Original Test Record is updated and attributes are automatically set")
    @Test
    public void testPutTestResultsUpdateTestType() {

        // Read the base tech-records JSON.
        String postRequestBody = GenericData.readJsonValueFromFile("technical-records_hgv_all_fields.json", "$");

        // Create alteration to add one more fields to the request body
        String randomSystemNumber = GenericData.generateRandomSystemNumber();
        String randomVin = GenericData.generateRandomVin();
        JsonPathAlteration alterationSystemNumberVehicle = new JsonPathAlteration("$.systemNumber", randomSystemNumber, "", "REPLACE");
        JsonPathAlteration alterationVinVehicle = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterationsVehicle = new ArrayList<>(Arrays.asList(alterationSystemNumberVehicle, alterationVinVehicle));

        // Post tech-records, together with any alterations, and verify that they are accepted.
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBody, alterationsVehicle);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(HttpStatus.SC_CREATED);
        vehicleTechnicalRecordsSteps.validateData("Technical Record created");

        // Read the base test-results JSON.
        String testResultRecord = GenericData.readJsonValueFromFile("test-results_post_payload_12378.json", "$");

        // Create alteration to add one more fields in the request body
        String randomTestResultId = UUID.randomUUID().toString();
        JsonPathAlteration alterationSystemNumberTestResults = new JsonPathAlteration("$.systemNumber", randomSystemNumber, "", "REPLACE");
        JsonPathAlteration alterationVinTestResults = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationTestResultIdPost = new JsonPathAlteration("$.testResultId", randomTestResultId, "", "REPLACE");
        JsonPathAlteration alterationTestStartTimestamp = new JsonPathAlteration("$.testStartTimestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 1)), "", "REPLACE");
        JsonPathAlteration alterationTestEndTimestamp = new JsonPathAlteration("$.testEndTimestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 4)), "", "REPLACE");
        JsonPathAlteration alterationTestTypeStartTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeStartTimestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 2)), "", "REPLACE");
        JsonPathAlteration alterationTestTypeEndTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeEndTimestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 3)), "", "REPLACE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterationsTestResults = new ArrayList<>(Arrays.asList(
                alterationSystemNumberTestResults,
                alterationVinTestResults,
                alterationTestResultIdPost,
                alterationTestStartTimestamp,
                alterationTestEndTimestamp,
                alterationTestTypeStartTimestamp,
                alterationTestTypeEndTimestamp
        ));

        // Post the tech-records, together with any alterations, and verify that they are accepted.
        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, alterationsTestResults);
        testResultsSteps.statusCodeShouldBe(HttpStatus.SC_CREATED);
        testResultsSteps.validateData("Test records created");


        //Read the base JSON for PUT test-results
        String putRequestBody = GenericData.readJsonValueFromFile("test-results_put_payload_12378.json","$");

        // Create alteration to add one more fields to the request body
        JsonPathAlteration alterationSystemNumberPutTestResults = new JsonPathAlteration("$.testResult.systemNumber", randomSystemNumber, "", "REPLACE");
        JsonPathAlteration alterationVinPutTestResults = new JsonPathAlteration("$.testResult.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationTestResultIdPut = new JsonPathAlteration("$.testResult.testResultId", randomTestResultId, "", "REPLACE");
        JsonPathAlteration alterationTestTypeName = new JsonPathAlteration("$.testResult.testTypes[0].testTypeName", "Low Emissions Certificate (LEC) with annual test", "", "REPLACE");
        JsonPathAlteration alterationPutTestStartTimestamp = new JsonPathAlteration("$.testResult.testStartTimestamp",new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 1)) , "", "REPLACE");
        JsonPathAlteration alterationPutTestEndTimestamp = new JsonPathAlteration("$.testResult.testEndTimestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 4)), "", "REPLACE");
        JsonPathAlteration alterationPutTestTypeStartTimestamp = new JsonPathAlteration("$.testResult.testTypes[0].testTypeStartTimestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 2)), "", "REPLACE");
        JsonPathAlteration alterationPutTestTypeEndTimestamp = new JsonPathAlteration("$.testResult.testTypes[0].testTypeEndTimestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 3)), "", "REPLACE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterationsPutTestResults = new ArrayList<>(Arrays.asList(
                alterationSystemNumberPutTestResults,
                alterationVinPutTestResults,
                alterationTestResultIdPut,
                alterationPutTestStartTimestamp,
                alterationPutTestEndTimestamp,
                alterationPutTestTypeStartTimestamp,
                alterationPutTestTypeEndTimestamp,
                alterationTestTypeName
        ));

        //AC1+ AC4
        // PUT test-results and verify that the desired status code and response is retrieved
        testResultsSteps.putTestResultsWithAlterations(randomSystemNumber,putRequestBody,alterationsPutTestResults);
        testResultsSteps.statusCodeShouldBe(HttpStatus.SC_OK);
        testResultsSteps.valueForFieldInPathShouldBe("testTypes[0].testNumber", "3");
        testResultsSteps.valueForFieldInPathShouldBe("testVersion","current");
        testResultsSteps.valueForFieldInPathShouldBe("reasonForCreation","Testing");
        testResultsSteps.valueForFieldInPathShouldContain("createdAt",new SimpleDateFormat("yyyy-MM-dd").format(date));
        testResultsSteps.valueForFieldInPathShouldBe("createdByName","catalin");
        testResultsSteps.valueForFieldInPathShouldBe("createdById","123243424-234234245");

        testResultsSteps.valueForFieldInPathShouldBe("testHistory[0].testVersion","archived");
        testResultsSteps.valueForFieldInPathShouldContain("testHistory[0].lastUpdatedAt",new SimpleDateFormat("yyyy-MM-dd").format(date));
        testResultsSteps.valueForFieldInPathShouldBe("testHistory[0].lastUpdatedByName","catalin");
        testResultsSteps.valueForFieldInPathShouldBe("testHistory[0].lastUpdatedById","123243424-234234245");


        // GET test-results with different TestVersions
        testResultsSteps.getTestResults(randomSystemNumber,TestVersion.ALL,randomTestResultId, TestResultsStatus.CANCELED);
        testResultsSteps.statusCodeShouldBe(HttpStatus.SC_OK);
        testResultsSteps.valueForFieldInPathShouldBe("[0].testVersion","current");
        testResultsSteps.valueForFieldInPathShouldBe("[0].testHistory[0].testVersion","archived");

        testResultsSteps.getTestResults(randomSystemNumber, TestVersion.ARCHIVED ,randomTestResultId,TestResultsStatus.CANCELED);
        testResultsSteps.statusCodeShouldBe(HttpStatus.SC_OK);
        testResultsSteps.valueForFieldInPathShouldBe("[0].testVersion","archived");
        testResultsSteps.fieldInPathShouldNotExist("[0]","testHistory");

        testResultsSteps.getTestResults(randomSystemNumber, TestVersion.CURRENT ,randomTestResultId,TestResultsStatus.CANCELED);
        testResultsSteps.statusCodeShouldBe(HttpStatus.SC_OK);
        testResultsSteps.valueForFieldInPathShouldBe("[0].testVersion","current");
        testResultsSteps.fieldInPathShouldNotExist("[0]","testHistory");
    }

    @WithTag("Vtm")
    @Title("CVSB-12378 - AC5. PUT request: testResult and testExpiryDate attributes are manually updated +" +
            "AC7. Expiry date and Anniversary date updates are mutually exclusive")
    @Test
    public void testPutTestResultsUpdateTestResultAndExpiryDate() {

        // Read the base tech-records JSON.
        String postRequestBody = GenericData.readJsonValueFromFile("technical-records_hgv_all_fields.json", "$");

        // Create alteration to edit the fields in the request body
        String randomSystemNumber = GenericData.generateRandomSystemNumber();
        String randomVin = GenericData.generateRandomVin();
        JsonPathAlteration alterationSystemNumberVehicle = new JsonPathAlteration("$.systemNumber", randomSystemNumber, "", "REPLACE");
        JsonPathAlteration alterationVinVehicle = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterationsVehicle = new ArrayList<>(Arrays.asList(alterationSystemNumberVehicle, alterationVinVehicle));

        // Post the tech-records, together with any alterations, and verify that they are accepted.
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBody, alterationsVehicle);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(HttpStatus.SC_CREATED);
        vehicleTechnicalRecordsSteps.validateData("Technical Record created");

        // Read the base test result JSON.
        String testResultRecord = GenericData.readJsonValueFromFile("test-results_post_payload_12378.json", "$");

        // Create alteration to edit the fields in the request body
        String randomTestResultId = UUID.randomUUID().toString();
        JsonPathAlteration alterationSystemNumberTestResults = new JsonPathAlteration("$.systemNumber", randomSystemNumber, "", "REPLACE");
        JsonPathAlteration alterationVinTestResults = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationTestResultIdPost = new JsonPathAlteration("$.testResultId", randomTestResultId, "", "REPLACE");
        JsonPathAlteration alterationTestStartTimestamp = new JsonPathAlteration("$.testStartTimestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 1)), "", "REPLACE");
        JsonPathAlteration alterationTestEndTimestamp = new JsonPathAlteration("$.testEndTimestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 4)), "", "REPLACE");
        JsonPathAlteration alterationTestTypeStartTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeStartTimestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 2)), "", "REPLACE");
        JsonPathAlteration alterationTestTypeEndTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeEndTimestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 3)), "", "REPLACE");


        // Collate the list of alterations.
        List<JsonPathAlteration> alterationsTestResults = new ArrayList<>(Arrays.asList(
                alterationSystemNumberTestResults,
                alterationVinTestResults,
                alterationTestResultIdPost,
                alterationTestStartTimestamp,
                alterationTestEndTimestamp,
                alterationTestTypeStartTimestamp,
                alterationTestTypeEndTimestamp));

        // Post the results, together with any alterations, and verify that they are accepted.
        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, alterationsTestResults);
        testResultsSteps.statusCodeShouldBe(HttpStatus.SC_CREATED);
        testResultsSteps.validateData("Test records created");


        //Read the base JSON for PUT test-results
        String putRequestBody = GenericData.readJsonValueFromFile("test-results_put_payload_12378.json","$");

        // Create alteration to edit the fields in the request body
        JsonPathAlteration alterationSystemNumberPutTestResults = new JsonPathAlteration("$.testResult.systemNumber", randomSystemNumber, "", "REPLACE");
        JsonPathAlteration alterationVinPutTestResults = new JsonPathAlteration("$.testResult.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationTestResultIdPut = new JsonPathAlteration("$.testResult.testResultId", randomTestResultId, "", "REPLACE");
        JsonPathAlteration alterationTestResult = new JsonPathAlteration("$.testResult.testTypes[0].testResult", "pass", "", "REPLACE");
        JsonPathAlteration alterationTestAnnDate = new JsonPathAlteration("$.testResult.testTypes[0].testAnniversaryDate", "2020-05-25", "", "REPLACE");
        JsonPathAlteration alterationPutTestStartTimestamp = new JsonPathAlteration("$.testResult.testStartTimestamp",new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 1)) , "", "REPLACE");
        JsonPathAlteration alterationPutTestEndTimestamp = new JsonPathAlteration("$.testResult.testEndTimestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 4)), "", "REPLACE");
        JsonPathAlteration alterationPutTestTypeStartTimestamp = new JsonPathAlteration("$.testResult.testTypes[0].testTypeStartTimestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 2)), "", "REPLACE");
        JsonPathAlteration alterationPutTestTypeEndTimestamp = new JsonPathAlteration("$.testResult.testTypes[0].testTypeEndTimestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 3)), "", "REPLACE");


        // Collate the list of alterations.
        List<JsonPathAlteration> alterationsPutTestResults = new ArrayList<>(Arrays.asList(
                alterationSystemNumberPutTestResults,
                alterationVinPutTestResults,
                alterationTestResultIdPut,
                alterationPutTestStartTimestamp,
                alterationPutTestEndTimestamp,
                alterationPutTestTypeStartTimestamp,
                alterationPutTestTypeEndTimestamp,
                alterationTestResult,
                alterationTestAnnDate

        ));

        //AC5 + AC7
        // PUT test-results together with any alterations, and verify that they are accepted
        testResultsSteps.putTestResultsWithAlterations(randomSystemNumber,putRequestBody,alterationsPutTestResults);
        testResultsSteps.statusCodeShouldBe(HttpStatus.SC_OK);
        testResultsSteps.valueForFieldInPathShouldBe("testTypes[0].testAnniversaryDate", "2020-05-25");
        testResultsSteps.valueForFieldInPathShouldBe("testTypes[0].testResult", "pass");
        testResultsSteps.valueForFieldInPathShouldBe("testVersion","current");
        testResultsSteps.valueForFieldInPathShouldBe("testHistory[0].testVersion","archived");


        // GET test-results as per various TestVersions
        testResultsSteps.getTestResults(randomSystemNumber,TestVersion.ALL,randomTestResultId, TestResultsStatus.CANCELED);
        testResultsSteps.statusCodeShouldBe(HttpStatus.SC_OK);
        testResultsSteps.valueForFieldInPathShouldBe("[0].testVersion","current");
        testResultsSteps.valueForFieldInPathShouldBe("[0].testHistory[0].testVersion","archived");

        testResultsSteps.getTestResults(randomSystemNumber, TestVersion.ARCHIVED ,randomTestResultId,TestResultsStatus.CANCELED);
        testResultsSteps.statusCodeShouldBe(HttpStatus.SC_OK);
        testResultsSteps.valueForFieldInPathShouldBe("[0].testVersion","archived");
        testResultsSteps.fieldInPathShouldNotExist("[0]","testHistory");

        testResultsSteps.getTestResults(randomSystemNumber, TestVersion.CURRENT ,randomTestResultId,TestResultsStatus.CANCELED);
        testResultsSteps.statusCodeShouldBe(HttpStatus.SC_OK);
        testResultsSteps.valueForFieldInPathShouldBe("[0].testVersion","current");
        testResultsSteps.fieldInPathShouldNotExist("[0]","testHistory");
    }

    @WithTag("Vtm")
    @Title("CVSB-12378 - AC8. PUT request: Test result is updated with a new test code")
    @Test
    public void testPutTestResultsUpdateFields() {

        // Read the base tech-records JSON.
        String postRequestBody = GenericData.readJsonValueFromFile("technical-records_hgv_all_fields.json", "$");

        // Create alteration to edit the fields in the request body
        String randomSystemNumber = GenericData.generateRandomSystemNumber();
        String randomVin = GenericData.generateRandomVin();
        JsonPathAlteration alterationSystemNumberVehicle = new JsonPathAlteration("$.systemNumber", randomSystemNumber, "", "REPLACE");
        JsonPathAlteration alterationVinVehicle = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterationsVehicle = new ArrayList<>(Arrays.asList(alterationSystemNumberVehicle, alterationVinVehicle));

        // Post the tech-records, together with any alterations, and verify that they are accepted.
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBody, alterationsVehicle);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(HttpStatus.SC_CREATED);
        vehicleTechnicalRecordsSteps.validateData("Technical Record created");

        // Read the base test result JSON.
        String testResultRecord = GenericData.readJsonValueFromFile("test-results_post_payload_12378.json", "$");

        // Create alteration to edit the fields in the request body
        String randomTestResultId = UUID.randomUUID().toString();
        JsonPathAlteration alterationSystemNumberTestResults = new JsonPathAlteration("$.systemNumber", randomSystemNumber, "", "REPLACE");
        JsonPathAlteration alterationVinTestResults = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationTestResultIdPost = new JsonPathAlteration("$.testResultId", randomTestResultId, "", "REPLACE");
        JsonPathAlteration alterationTestStartTimestamp = new JsonPathAlteration("$.testStartTimestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 1)), "", "REPLACE");
        JsonPathAlteration alterationTestEndTimestamp = new JsonPathAlteration("$.testEndTimestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 4)), "", "REPLACE");
        JsonPathAlteration alterationTestTypeStartTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeStartTimestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 2)), "", "REPLACE");
        JsonPathAlteration alterationTestTypeEndTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeEndTimestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 3)), "", "REPLACE");


        // Collate the list of alterations.
        List<JsonPathAlteration> alterationsTestResults = new ArrayList<>(Arrays.asList(
                alterationSystemNumberTestResults,
                alterationVinTestResults,
                alterationTestResultIdPost,
                alterationTestStartTimestamp,
                alterationTestEndTimestamp,
                alterationTestTypeStartTimestamp,
                alterationTestTypeEndTimestamp));

        // Post the test results, together with any alterations, and verify that they are accepted.
        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, alterationsTestResults);
        testResultsSteps.statusCodeShouldBe(HttpStatus.SC_CREATED);
        testResultsSteps.validateData("Test records created");

        // GET test-results
        testResultsSteps.getTestResults(randomSystemNumber);
        testResultsSteps.statusCodeShouldBe(HttpStatus.SC_OK);
        String testCode = testResultsSteps.getTestCode();

        // Read the base for PUT test result JSON
        String putRequestBody = GenericData.readJsonValueFromFile("test-results_put_payload_12378.json","$");

        // Create alteration to edit the fields in the request body
        JsonPathAlteration alterationSystemNumberPutTestResults = new JsonPathAlteration("$.testResult.systemNumber", randomSystemNumber, "", "REPLACE");
        JsonPathAlteration alterationVinPutTestResults = new JsonPathAlteration("$.testResult.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationTestResultIdPut = new JsonPathAlteration("$.testResult.testResultId", randomTestResultId, "", "REPLACE");
        JsonPathAlteration alterationTestTypeId = new JsonPathAlteration("$.testResult.testTypes[0].testTypeId", "3", "", "REPLACE");
        JsonPathAlteration alterationEuVehicleCategory = new JsonPathAlteration("$.testResult.euVehicleCategory", "n2", "", "REPLACE");
        JsonPathAlteration alterationVehicleSize = new JsonPathAlteration("$.testResult.vehicleSize", "large", "", "REPLACE");
        JsonPathAlteration alterationVehicleConfiguration = new JsonPathAlteration("$.testResult.vehicleConfiguration", "articulated", "", "REPLACE");
        JsonPathAlteration alterationNoOfAxles = new JsonPathAlteration("$.testResult.noOfAxles", "3", "", "REPLACE");
        JsonPathAlteration alterationNoOfWheels = new JsonPathAlteration("$.testResult.numberOfWheelsDriven", "4", "", "REPLACE");
        JsonPathAlteration alterationPutTestStartTimestamp = new JsonPathAlteration("$.testResult.testStartTimestamp",new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 1)) , "", "REPLACE");
        JsonPathAlteration alterationPutTestEndTimestamp = new JsonPathAlteration("$.testResult.testEndTimestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 4)), "", "REPLACE");
        JsonPathAlteration alterationPutTestTypeStartTimestamp = new JsonPathAlteration("$.testResult.testTypes[0].testTypeStartTimestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 2)), "", "REPLACE");
        JsonPathAlteration alterationPutTestTypeEndTimestamp = new JsonPathAlteration("$.testResult.testTypes[0].testTypeEndTimestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 3)), "", "REPLACE");


        // Collate the list of alterations.
        List<JsonPathAlteration> alterationsPutTestResults = new ArrayList<>(Arrays.asList(
                alterationSystemNumberPutTestResults,
                alterationVinPutTestResults,
                alterationTestResultIdPut,
                alterationTestTypeId,
                alterationPutTestStartTimestamp,
                alterationPutTestEndTimestamp,
                alterationPutTestTypeStartTimestamp,
                alterationPutTestTypeEndTimestamp,
                alterationEuVehicleCategory,
                alterationVehicleSize,
                alterationVehicleConfiguration,
                alterationNoOfAxles,
                alterationNoOfWheels

        ));

        // PUT test-results together with any alterations, and verify that they are accepted
        testResultsSteps.putTestResultsWithAlterations(randomSystemNumber,putRequestBody,alterationsPutTestResults);
        testResultsSteps.statusCodeShouldBe(HttpStatus.SC_OK);
        testResultsSteps.valueForFieldInPathShouldNotBe("testResult.testTypes[0].testCode",testCode);
        testResultsSteps.valueForFieldInPathShouldBe("testVersion","current");
        testResultsSteps.valueForFieldInPathShouldBe("testHistory[0].testVersion","archived");

        // GET test-results as per various TestVersions
        testResultsSteps.getTestResults(randomSystemNumber,TestVersion.ALL,randomTestResultId, TestResultsStatus.CANCELED);
        testResultsSteps.statusCodeShouldBe(HttpStatus.SC_OK);
        testResultsSteps.valueForFieldInPathShouldBe("[0].testVersion","current");
        testResultsSteps.valueForFieldInPathShouldBe("[0].testHistory[0].testVersion","archived");

        testResultsSteps.getTestResults(randomSystemNumber, TestVersion.ARCHIVED ,randomTestResultId,TestResultsStatus.CANCELED);
        testResultsSteps.statusCodeShouldBe(HttpStatus.SC_OK);
        testResultsSteps.valueForFieldInPathShouldBe("[0].testVersion","archived");
        testResultsSteps.fieldInPathShouldNotExist("[0]","testHistory");

        testResultsSteps.getTestResults(randomSystemNumber, TestVersion.CURRENT ,randomTestResultId,TestResultsStatus.CANCELED);
        testResultsSteps.statusCodeShouldBe(HttpStatus.SC_OK);
        testResultsSteps.valueForFieldInPathShouldBe("[0].testVersion","current");
        testResultsSteps.fieldInPathShouldNotExist("[0]","testHistory");
    }

    @WithTag("Vtm")
    @Title("CVSB-12378 - AC6. PUT request: Test record is updated and existing business logic is not triggered")
    @Test
    public void testPutTestResultsLogic() {

        // Read the base tesch-records JSON.
        String postRequestBody = GenericData.readJsonValueFromFile("technical-records_hgv_all_fields.json", "$");

        // Create alteration to edit one or more fields in the request body
        String randomSystemNumber = GenericData.generateRandomSystemNumber();
        String randomVin = GenericData.generateRandomVin();
        JsonPathAlteration alterationSystemNumberVehicle = new JsonPathAlteration("$.systemNumber", randomSystemNumber, "", "REPLACE");
        JsonPathAlteration alterationVinVehicle = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterationsVehicle = new ArrayList<>(Arrays.asList(alterationSystemNumberVehicle, alterationVinVehicle));

        // Post the test-results, together with any alterations, and verify that they are accepted.
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBody, alterationsVehicle);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(HttpStatus.SC_CREATED);
        vehicleTechnicalRecordsSteps.validateData("Technical Record created");

        // Read the base test result JSON.
        String testResultRecord = GenericData.readJsonValueFromFile("test-results_post_payload_12378.json", "$");

        // Create alteration to edit one or more fields in the request body
        String randomTestResultId = UUID.randomUUID().toString();
        JsonPathAlteration alterationSystemNumberTestResults = new JsonPathAlteration("$.systemNumber", randomSystemNumber, "", "REPLACE");
        JsonPathAlteration alterationVinTestResults = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationTestResultIdPost = new JsonPathAlteration("$.testResultId", randomTestResultId, "", "REPLACE");
        JsonPathAlteration alterationTestStartTimestamp = new JsonPathAlteration("$.testStartTimestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 1)), "", "REPLACE");JsonPathAlteration alterationTestEndTimestamp = new JsonPathAlteration("$.testEndTimestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 4)), "", "REPLACE");JsonPathAlteration alterationTestTypeStartTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeStartTimestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 2)), "", "REPLACE");JsonPathAlteration alterationTestTypeEndTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeEndTimestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 3)), "", "REPLACE");


        // Collate the list of alterations.
        List<JsonPathAlteration> alterationsTestResults = new ArrayList<>(Arrays.asList(
                alterationSystemNumberTestResults,
                alterationVinTestResults,
                alterationTestResultIdPost,
                alterationTestStartTimestamp,
                alterationTestEndTimestamp,
                alterationTestTypeStartTimestamp,
                alterationTestTypeEndTimestamp));

        // Post the test-results, together with any alterations, and verify that they are accepted.
        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, alterationsTestResults);
        testResultsSteps.statusCodeShouldBe(HttpStatus.SC_CREATED);
        testResultsSteps.validateData("Test records created");

        // GET test-results
        testResultsSteps.getTestResults(randomSystemNumber);
        testResultsSteps.statusCodeShouldBe(HttpStatus.SC_OK);

        // Read the base for PUT test result JSON
        String putRequestBody = GenericData.readJsonValueFromFile("test-results_put_payload_12378.json","$");

        // Create alteration to edit the fields in the request body
        JsonPathAlteration alterationSystemNumberPutTestResults = new JsonPathAlteration("$.testResult.systemNumber", randomSystemNumber, "", "REPLACE");
        JsonPathAlteration alterationVinPutTestResults = new JsonPathAlteration("$.testResult.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationTestResultIdPut = new JsonPathAlteration("$.testResult.testResultId", randomTestResultId, "", "REPLACE");
        JsonPathAlteration alterationTestResult = new JsonPathAlteration("$.testResult.testTypes[0].testResult", "pass", "", "REPLACE");
        JsonPathAlteration alterationPutTestStartTimestamp = new JsonPathAlteration("$.testResult.testStartTimestamp",new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 1)) , "", "REPLACE");
        JsonPathAlteration alterationPutTestEndTimestamp = new JsonPathAlteration("$.testResult.testEndTimestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 4)), "", "REPLACE");
        JsonPathAlteration alterationPutTestTypeStartTimestamp = new JsonPathAlteration("$.testResult.testTypes[0].testTypeStartTimestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 2)), "", "REPLACE");
        JsonPathAlteration alterationPutTestTypeEndTimestamp = new JsonPathAlteration("$.testResult.testTypes[0].testTypeEndTimestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 3)), "", "REPLACE");
        JsonPathAlteration alterationCertificateNumber = new JsonPathAlteration("$.testResult.testTypes[0].certificateNumber", "78910", "", "REPLACE");


        // Collate the list of alterations.
        List<JsonPathAlteration> alterationsPutTestResults = new ArrayList<>(Arrays.asList(
                alterationSystemNumberPutTestResults,
                alterationVinPutTestResults,
                alterationTestResultIdPut,
                alterationTestResult,
                alterationPutTestStartTimestamp,
                alterationPutTestEndTimestamp,
                alterationPutTestTypeStartTimestamp,
                alterationPutTestTypeEndTimestamp,
                alterationCertificateNumber

        ));

        // PUT test-results together with any alterations, and verify that they are accepted
        testResultsSteps.putTestResultsWithAlterations(randomSystemNumber,putRequestBody,alterationsPutTestResults);
        testResultsSteps.statusCodeShouldBe(HttpStatus.SC_OK);
        testResultsSteps.valueForFieldInPathShouldBe("testVersion","current");
        testResultsSteps.valueForFieldInPathShouldBe("testHistory[0].testVersion","archived");
        testResultsSteps.valueForFieldInPathShouldBe("testTypes[0].certificateNumber","78910");

        String testNumber = testResultsSteps.getTestNumber();

        // Read the base for PUT test result JSON
        String secondPutRequestBody = GenericData.readJsonValueFromFile("test-results_put_payload_12378.json","$");

        // Create alteration to edit the fields in the request body
        JsonPathAlteration alterationSecSystemNumberPutTestResults = new JsonPathAlteration("$.testResult.systemNumber", randomSystemNumber, "", "REPLACE");
        JsonPathAlteration alterationSecVinPutTestResults = new JsonPathAlteration("$.testResult.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationSecTestResultIdPut = new JsonPathAlteration("$.testResult.testResultId", randomTestResultId, "", "REPLACE");
        JsonPathAlteration alterationSecTestResult = new JsonPathAlteration("$.testResult.testTypes[0].testResult", "pass", "", "REPLACE");
        JsonPathAlteration alterationSecPutTestStartTimestamp = new JsonPathAlteration("$.testResult.testStartTimestamp",new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 1)) , "", "REPLACE");
        JsonPathAlteration alterationSecPutTestEndTimestamp = new JsonPathAlteration("$.testResult.testEndTimestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 4)), "", "REPLACE");
        JsonPathAlteration alterationSecPutTestTypeStartTimestamp = new JsonPathAlteration("$.testResult.testTypes[0].testTypeStartTimestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 2)), "", "REPLACE");
        JsonPathAlteration alterationSecPutTestTypeEndTimestamp = new JsonPathAlteration("$.testResult.testTypes[0].testTypeEndTimestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 3)), "", "REPLACE");
        JsonPathAlteration alterationSecCertificateNumber = new JsonPathAlteration("$.testResult.testTypes[0].certificateNumber", "78910", "", "REPLACE");
        JsonPathAlteration alterationSecNoOfSeats = new JsonPathAlteration("$.testResult.numberOfSeats", "46", "", "REPLACE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterationsSecPutTestResults = new ArrayList<>(Arrays.asList(
                alterationSecSystemNumberPutTestResults,
                alterationSecVinPutTestResults,
                alterationSecTestResultIdPut,
                alterationSecTestResult,
                alterationSecPutTestStartTimestamp,
                alterationSecPutTestEndTimestamp,
                alterationSecPutTestTypeStartTimestamp,
                alterationSecPutTestTypeEndTimestamp,
                alterationSecCertificateNumber,
                alterationSecNoOfSeats
        ));

        // PUT test-results together with any alterations, and verify that they are accepted
        testResultsSteps.putTestResultsWithAlterations(randomSystemNumber,secondPutRequestBody,alterationsSecPutTestResults);
        testResultsSteps.valueForFieldInPathShouldBe("testVersion","current");
        testResultsSteps.valueForFieldInPathShouldBe("testHistory[0].testVersion","archived");
        testResultsSteps.valueForFieldInPathShouldBe("testTypes[0].certificateNumber","78910");
        testResultsSteps.valueForFieldInPathShouldNotBe("testTypes[0].certificateNumber",testNumber);

        // GET test-results as per various TestVersions
        testResultsSteps.getTestResults(randomSystemNumber,TestVersion.ALL,randomTestResultId, TestResultsStatus.CANCELED);
        testResultsSteps.statusCodeShouldBe(HttpStatus.SC_OK);
        testResultsSteps.valueForFieldInPathShouldBe("[0].testVersion","current");
        testResultsSteps.valueForFieldInPathShouldBe("[0].testHistory[0].testVersion","archived");

        testResultsSteps.getTestResults(randomSystemNumber, TestVersion.ARCHIVED ,randomTestResultId,TestResultsStatus.CANCELED);
        testResultsSteps.statusCodeShouldBe(HttpStatus.SC_OK);
        testResultsSteps.valueForFieldInPathShouldBe("[0].testVersion","archived");
        testResultsSteps.fieldInPathShouldNotExist("[0]","testHistory");

        testResultsSteps.getTestResults(randomSystemNumber, TestVersion.CURRENT ,randomTestResultId,TestResultsStatus.CANCELED);
        testResultsSteps.statusCodeShouldBe(HttpStatus.SC_OK);
        testResultsSteps.valueForFieldInPathShouldBe("[0].testVersion","current");
        testResultsSteps.fieldInPathShouldNotExist("[0]","testHistory");
    }

    @WithTag("Vtm")
    @Title("CVSB-12378 - AC7. Expiry date and Anniversary date updates are mutually exclusive + " +
            "AC5. PUT request: testResult and testExpiryDate attributes are manually updated +" +
            "AC6. PUT request: Test record is updated and existing business logic is not triggered")
    @Test
    public void testPutTestResultsExpiryAnniversary() {

        // Read the base test-results JSON.
        String postRequestBody = GenericData.readJsonValueFromFile("technical-records_hgv_all_fields.json", "$");

        // Create alteration to edit the fields in the request body
        String randomSystemNumber = GenericData.generateRandomSystemNumber();
        String randomVin = GenericData.generateRandomVin();
        JsonPathAlteration alterationSystemNumberVehicle = new JsonPathAlteration("$.systemNumber", randomSystemNumber, "", "REPLACE");
        JsonPathAlteration alterationVinVehicle = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterationsVehicle = new ArrayList<>(Arrays.asList(alterationSystemNumberVehicle, alterationVinVehicle));

        // Post the test-results, together with any alterations, and verify that they are accepted.
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBody, alterationsVehicle);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(HttpStatus.SC_CREATED);
        vehicleTechnicalRecordsSteps.validateData("Technical Record created");

        // Read the base test result JSON.
        String testResultRecord = GenericData.readJsonValueFromFile("test-results_post_payload_12378.json", "$");

        // Create alteration to edit the fields in the request body
        String randomTestResultId = UUID.randomUUID().toString();
        JsonPathAlteration alterationSystemNumberTestResults = new JsonPathAlteration("$.systemNumber", randomSystemNumber, "", "REPLACE");
        JsonPathAlteration alterationVinTestResults = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationTestResultIdPost = new JsonPathAlteration("$.testResultId", randomTestResultId, "", "REPLACE");
        JsonPathAlteration alterationTestStartTimestamp = new JsonPathAlteration("$.testStartTimestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 1)), "", "REPLACE");
        JsonPathAlteration alterationTestEndTimestamp = new JsonPathAlteration("$.testEndTimestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 4)), "", "REPLACE");
        JsonPathAlteration alterationTestTypeStartTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeStartTimestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 2)), "", "REPLACE");
        JsonPathAlteration alterationTestTypeEndTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeEndTimestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 3)), "", "REPLACE");


        // Collate the list of alterations.
        List<JsonPathAlteration> alterationsTestResults = new ArrayList<>(Arrays.asList(
                alterationSystemNumberTestResults,
                alterationVinTestResults,
                alterationTestResultIdPost,
                alterationTestStartTimestamp,
                alterationTestEndTimestamp,
                alterationTestTypeStartTimestamp,
                alterationTestTypeEndTimestamp));

        // Post the test-results, together with any alterations, and verify that they are accepted.
        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, alterationsTestResults);
        testResultsSteps.statusCodeShouldBe(HttpStatus.SC_CREATED);
        testResultsSteps.validateData("Test records created");

        // Read the base for PUT test result JSON
        String putRequestBody = GenericData.readJsonValueFromFile("test-results_put_payload_12378.json","$");

        // Create alteration to edit the fields in the request body
        JsonPathAlteration alterationSystemNumberPutTestResults = new JsonPathAlteration("$.testResult.systemNumber", randomSystemNumber, "", "REPLACE");
        JsonPathAlteration alterationVinPutTestResults = new JsonPathAlteration("$.testResult.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationTestResultIdPut = new JsonPathAlteration("$.testResult.testResultId", randomTestResultId, "", "REPLACE");
        JsonPathAlteration alterationPutTestStartTimestamp = new JsonPathAlteration("$.testResult.testStartTimestamp",new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 1)) , "", "REPLACE");
        JsonPathAlteration alterationPutTestEndTimestamp = new JsonPathAlteration("$.testResult.testEndTimestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 4)), "", "REPLACE");
        JsonPathAlteration alterationPutTestTypeStartTimestamp = new JsonPathAlteration("$.testResult.testTypes[0].testTypeStartTimestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 2)), "", "REPLACE");
        JsonPathAlteration alterationPutTestTypeEndTimestamp = new JsonPathAlteration("$.testResult.testTypes[0].testTypeEndTimestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 3)), "", "REPLACE");
        JsonPathAlteration alterationPutTestStatus = new JsonPathAlteration("$.testResult.testStatus", "submitted", "", "REPLACE");
        //AC5
        JsonPathAlteration alterationPutTestExpiry = new JsonPathAlteration("$.testResult.testExpiryDate", "2020-04-29", "", "REPLACE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterationsPutTestResults = new ArrayList<>(Arrays.asList(
                alterationSystemNumberPutTestResults,
                alterationVinPutTestResults,
                alterationTestResultIdPut,
                alterationPutTestStartTimestamp,
                alterationPutTestEndTimestamp,
                alterationPutTestTypeStartTimestamp,
                alterationPutTestTypeEndTimestamp,
                alterationPutTestStatus,
                alterationPutTestExpiry));

        //AC5 + AC6 + AC7
        // PUT test-results together with any alterations, and verify that they are accepted
        testResultsSteps.putTestResultsWithAlterations(randomSystemNumber,putRequestBody,alterationsPutTestResults);
        testResultsSteps.statusCodeShouldBe(HttpStatus.SC_OK);
        testResultsSteps.valueForFieldInPathShouldBe("testTypes[0].testExpiryDate", "2020-04-29");
        testResultsSteps.valueForFieldInPathShouldBe("testVersion","current");
        testResultsSteps.valueForFieldInPathShouldBe("testHistory[0].testVersion","archived");

        // GET test-results as per various TestVersions
        testResultsSteps.getTestResults(randomSystemNumber,TestVersion.ALL,randomTestResultId);
        testResultsSteps.statusCodeShouldBe(HttpStatus.SC_OK);
        testResultsSteps.valueForFieldInPathShouldBe("[0].testVersion","current");
        testResultsSteps.valueForFieldInPathShouldBe("[0].testHistory[0].testVersion","archived");

        testResultsSteps.getTestResults(randomSystemNumber, TestVersion.ARCHIVED ,randomTestResultId);
        testResultsSteps.statusCodeShouldBe(HttpStatus.SC_OK);
        testResultsSteps.valueForFieldInPathShouldBe("[0].testVersion","archived");
        testResultsSteps.fieldInPathShouldNotExist("[0]","testHistory");

        testResultsSteps.getTestResults(randomSystemNumber, TestVersion.CURRENT ,randomTestResultId);
        testResultsSteps.statusCodeShouldBe(HttpStatus.SC_OK);
        testResultsSteps.valueForFieldInPathShouldBe("[0].testVersion","current");
        testResultsSteps.fieldInPathShouldNotExist("[0]","testHistory");
    }
}
