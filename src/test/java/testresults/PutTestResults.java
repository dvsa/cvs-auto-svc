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

    @WithTag("Vtm")
    @Title("CVSB-10300 - AC1 PUT: Attempt to update a test record with a not applicable field - TestTypeId = 50")
    @Test
    public void testResultsPutApplicableAdr() {
        // Read the base test result JSON.
        String postRequestBody = GenericData.readJsonValueFromFile("technical-records_hgv_all_fields.json","$");

        // Create alteration to add one more tech record to in the request body
        String randomSystemNumber = GenericData.generateRandomSystemNumber();
        String randomVin = GenericData.generateRandomVin();
        JsonPathAlteration alterationSystemNumberVehicle = new JsonPathAlteration("$.systemNumber", randomSystemNumber, "", "REPLACE");
        JsonPathAlteration alterationVinVehicle = new JsonPathAlteration("$.vin", randomVin,"","REPLACE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterationsVehicle = new ArrayList<>(Arrays.asList(alterationSystemNumberVehicle,
                alterationVinVehicle));

        // Post the results, together with any alterations, and verify that they are accepted.
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBody, alterationsVehicle);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(HttpStatus.SC_CREATED);
        vehicleTechnicalRecordsSteps.validateData("Technical Record created");

        String testResultRecord = GenericData.readJsonValueFromFile("test-results_post_payload_hgv_10300.json", "$");

        // Create alteration to add one more tech record to in the request body

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


        // Read the base JSON for PUT test-results
        String putRequestBody = GenericData.readJsonValueFromFile("test-results_put_payload_hgv_10300.json","$");

        JsonPathAlteration alterationSystemNumberPutTestResults = new JsonPathAlteration("$.testResult.systemNumber", randomSystemNumber, "", "REPLACE");
        JsonPathAlteration alterationVinPutTestResults = new JsonPathAlteration("$.testResult.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationTestResultIdPut = new JsonPathAlteration("$.testResult.testResultId", randomTestResultId, "", "REPLACE");
        JsonPathAlteration alterationPutTestStartTimestamp = new JsonPathAlteration("$.testResult.testStartTimestamp",new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 1)) , "", "REPLACE");
        JsonPathAlteration alterationPutTestEndTimestamp = new JsonPathAlteration("$.testResult.testEndTimestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 4)), "", "REPLACE");
        JsonPathAlteration alterationPutTestTypeStartTimestamp = new JsonPathAlteration("$.testResult.testTypes[0].testTypeStartTimestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 2)), "", "REPLACE");
        JsonPathAlteration alterationPutTestTypeEndTimestamp = new JsonPathAlteration("$.testResult.testTypes[0].testTypeEndTimestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 3)), "", "REPLACE");
        JsonPathAlteration alterationNumberOfSeatBeltsFitted = new JsonPathAlteration("$.testResult.testTypes[0].numberOfSeatbeltsFitted", "", "", "DELETE");
        JsonPathAlteration alterationSeatBeltInstallationCheckDate = new JsonPathAlteration("$.testResult.testTypes[0].seatbeltInstallationCheckDate", "", "", "DELETE");
        JsonPathAlteration alterationLastSeatBeltInstallationCheckDate = new JsonPathAlteration("$.testResult.testTypes[0].lastSeatbeltInstallationCheckDate", "", "", "DELETE");
        JsonPathAlteration alterationTestAnniversaryDate = new JsonPathAlteration("$.testResult.testTypes[0].testAnniversaryDate", "", "", "DELETE");
        JsonPathAlteration alterationModType = new JsonPathAlteration("$.testResult.testTypes[0].modType", "", "", "DELETE");
        JsonPathAlteration alterationParticulateTrapSerialNumber = new JsonPathAlteration("$.testResult.testTypes[0].particulateTrapSerialNumber", "", "", "DELETE");
        JsonPathAlteration alterationSmokeTestKLimitApplied = new JsonPathAlteration("$.testResult.testTypes[0].smokeTestKLimitApplied", "", "", "DELETE");
        JsonPathAlteration alterationEmissionStandard = new JsonPathAlteration("$.testResult.testTypes[0].emissionStandard", "", "", "DELETE");
        JsonPathAlteration alterationModificationTypeUsed = new JsonPathAlteration("$.testResult.testTypes[0].modificationTypeUsed", "", "", "DELETE");
        JsonPathAlteration alterationParticulateTrapFitted = new JsonPathAlteration("$.testResult.testTypes[0].particulateTrapFitted", "", "", "DELETE");
        JsonPathAlteration alterationFuelType = new JsonPathAlteration("$.testResult.testTypes[0].fuelType", "", "", "DELETE");
        JsonPathAlteration alterationDefects = new JsonPathAlteration("$.testResult.testTypes[0].defects", "", "", "DELETE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterationsPutTestResults = new ArrayList<>(Arrays.asList(
                alterationSystemNumberPutTestResults,
                alterationVinPutTestResults,
                alterationTestResultIdPut,
                alterationPutTestStartTimestamp,
                alterationPutTestEndTimestamp,
                alterationPutTestTypeStartTimestamp,
                alterationPutTestTypeEndTimestamp,
                alterationNumberOfSeatBeltsFitted,
                alterationSeatBeltInstallationCheckDate,
                alterationLastSeatBeltInstallationCheckDate,
                alterationTestAnniversaryDate,
                alterationModType,
                alterationParticulateTrapSerialNumber,
                alterationSmokeTestKLimitApplied,
                alterationEmissionStandard,
                alterationModificationTypeUsed,
                alterationParticulateTrapFitted,
                alterationFuelType,
                alterationDefects
        ));

        testResultsSteps.putTestResultsWithAlterations(randomSystemNumber,putRequestBody,alterationsPutTestResults);
        testResultsSteps.statusCodeShouldBe(HttpStatus.SC_OK);
    }

    @WithTag("Vtm")
    @Title("CVSB-10300 - AC1 PUT: Attempt to update a test record with a not applicable field - TestTypeId = 59")
    @Test
    public void testResultsPutApplicablePaidAdrRetest() {
        // Read the base test result JSON.
        String postRequestBody = GenericData.readJsonValueFromFile("technical-records_hgv_all_fields.json","$");

        // Create alteration to add one more tech record to in the request body
        String randomSystemNumber = GenericData.generateRandomSystemNumber();
        String randomVin = GenericData.generateRandomVin();
        JsonPathAlteration alterationSystemNumberVehicle = new JsonPathAlteration("$.systemNumber", randomSystemNumber, "", "REPLACE");
        JsonPathAlteration alterationVinVehicle = new JsonPathAlteration("$.vin", randomVin,"","REPLACE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterationsVehicle = new ArrayList<>(Arrays.asList(alterationSystemNumberVehicle,
                alterationVinVehicle));

        // Post the results, together with any alterations, and verify that they are accepted.
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBody, alterationsVehicle);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(HttpStatus.SC_CREATED);
        vehicleTechnicalRecordsSteps.validateData("Technical Record created");

        String testResultRecord = GenericData.readJsonValueFromFile("test-results_post_payload_hgv_10300.json", "$");

        // Create alteration to add one more tech record to in the request body

        String randomTestResultId = UUID.randomUUID().toString();
        JsonPathAlteration alterationTestTypeId = new JsonPathAlteration("$.testTypes[0].testTypeId", "59", "", "REPLACE");
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
                alterationTestTypeId,
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


        // Read the base JSON for PUT test-results
        String putRequestBody = GenericData.readJsonValueFromFile("test-results_put_payload_hgv_10300.json","$");

        JsonPathAlteration alterationTestTypeID = new JsonPathAlteration("$.testResult.testTypes[0].testTypeId", "59", "", "REPLACE");
        JsonPathAlteration alterationSystemNumberPutTestResults = new JsonPathAlteration("$.testResult.systemNumber", randomSystemNumber, "", "REPLACE");
        JsonPathAlteration alterationVinPutTestResults = new JsonPathAlteration("$.testResult.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationTestResultIdPut = new JsonPathAlteration("$.testResult.testResultId", randomTestResultId, "", "REPLACE");
        JsonPathAlteration alterationPutTestStartTimestamp = new JsonPathAlteration("$.testResult.testStartTimestamp",new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 1)) , "", "REPLACE");
        JsonPathAlteration alterationPutTestEndTimestamp = new JsonPathAlteration("$.testResult.testEndTimestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 4)), "", "REPLACE");
        JsonPathAlteration alterationPutTestTypeStartTimestamp = new JsonPathAlteration("$.testResult.testTypes[0].testTypeStartTimestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 2)), "", "REPLACE");
        JsonPathAlteration alterationPutTestTypeEndTimestamp = new JsonPathAlteration("$.testResult.testTypes[0].testTypeEndTimestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 3)), "", "REPLACE");
        JsonPathAlteration alterationNumberOfSeatBeltsFitted = new JsonPathAlteration("$.testResult.testTypes[0].numberOfSeatbeltsFitted", "", "", "DELETE");
        JsonPathAlteration alterationSeatBeltInstallationCheckDate = new JsonPathAlteration("$.testResult.testTypes[0].seatbeltInstallationCheckDate", "", "", "DELETE");
        JsonPathAlteration alterationLastSeatBeltInstallationCheckDate = new JsonPathAlteration("$.testResult.testTypes[0].lastSeatbeltInstallationCheckDate", "", "", "DELETE");
        JsonPathAlteration alterationTestAnniversaryDate = new JsonPathAlteration("$.testResult.testTypes[0].testAnniversaryDate", "", "", "DELETE");
        JsonPathAlteration alterationModType = new JsonPathAlteration("$.testResult.testTypes[0].modType", "", "", "DELETE");
        JsonPathAlteration alterationParticulateTrapSerialNumber = new JsonPathAlteration("$.testResult.testTypes[0].particulateTrapSerialNumber", "", "", "DELETE");
        JsonPathAlteration alterationSmokeTestKLimitApplied = new JsonPathAlteration("$.testResult.testTypes[0].smokeTestKLimitApplied", "", "", "DELETE");
        JsonPathAlteration alterationEmissionStandard = new JsonPathAlteration("$.testResult.testTypes[0].emissionStandard", "", "", "DELETE");
        JsonPathAlteration alterationModificationTypeUsed = new JsonPathAlteration("$.testResult.testTypes[0].modificationTypeUsed", "", "", "DELETE");
        JsonPathAlteration alterationParticulateTrapFitted = new JsonPathAlteration("$.testResult.testTypes[0].particulateTrapFitted", "", "", "DELETE");
        JsonPathAlteration alterationFuelType = new JsonPathAlteration("$.testResult.testTypes[0].fuelType", "", "", "DELETE");
        JsonPathAlteration alterationDefects = new JsonPathAlteration("$.testResult.testTypes[0].defects", "", "", "DELETE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterationsPutTestResults = new ArrayList<>(Arrays.asList(
                alterationTestTypeID,
                alterationSystemNumberPutTestResults,
                alterationVinPutTestResults,
                alterationTestResultIdPut,
                alterationPutTestStartTimestamp,
                alterationPutTestEndTimestamp,
                alterationPutTestTypeStartTimestamp,
                alterationPutTestTypeEndTimestamp,
                alterationNumberOfSeatBeltsFitted,
                alterationSeatBeltInstallationCheckDate,
                alterationLastSeatBeltInstallationCheckDate,
                alterationTestAnniversaryDate,
                alterationModType,
                alterationParticulateTrapSerialNumber,
                alterationSmokeTestKLimitApplied,
                alterationEmissionStandard,
                alterationModificationTypeUsed,
                alterationParticulateTrapFitted,
                alterationFuelType,
                alterationDefects
        ));

        testResultsSteps.putTestResultsWithAlterations(randomSystemNumber,putRequestBody,alterationsPutTestResults);
        testResultsSteps.statusCodeShouldBe(HttpStatus.SC_OK);
    }

    @WithTag("Vtm")
    @Title("CVSB-10300 - AC1 PUT: Attempt to update a test record with a not applicable field - TestTypeId = 60")
    @Test
    public void testResultsPutApplicableFreeAdrRetest() {
        // Read the base test result JSON.
        String postRequestBody = GenericData.readJsonValueFromFile("technical-records_hgv_all_fields.json","$");

        // Create alteration to add one more tech record to in the request body
        String randomSystemNumber = GenericData.generateRandomSystemNumber();
        String randomVin = GenericData.generateRandomVin();
        JsonPathAlteration alterationSystemNumberVehicle = new JsonPathAlteration("$.systemNumber", randomSystemNumber, "", "REPLACE");
        JsonPathAlteration alterationVinVehicle = new JsonPathAlteration("$.vin", randomVin,"","REPLACE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterationsVehicle = new ArrayList<>(Arrays.asList(alterationSystemNumberVehicle,
                alterationVinVehicle));

        // Post the results, together with any alterations, and verify that they are accepted.
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBody, alterationsVehicle);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(HttpStatus.SC_CREATED);
        vehicleTechnicalRecordsSteps.validateData("Technical Record created");

        String testResultRecord = GenericData.readJsonValueFromFile("test-results_post_payload_hgv_10300.json", "$");

        // Create alteration to add one more tech record to in the request body

        String randomTestResultId = UUID.randomUUID().toString();
        JsonPathAlteration alterationTestTypeId = new JsonPathAlteration("$.testTypes[0].testTypeId", "60", "", "REPLACE");
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
                alterationTestTypeId,
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


        // Read the base JSON for PUT test-results
        String putRequestBody = GenericData.readJsonValueFromFile("test-results_put_payload_hgv_10300.json","$");

        JsonPathAlteration alterationTestTypeID = new JsonPathAlteration("$.testResult.testTypes[0].testTypeId", "60", "", "REPLACE");
        JsonPathAlteration alterationSystemNumberPutTestResults = new JsonPathAlteration("$.testResult.systemNumber", randomSystemNumber, "", "REPLACE");
        JsonPathAlteration alterationVinPutTestResults = new JsonPathAlteration("$.testResult.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationTestResultIdPut = new JsonPathAlteration("$.testResult.testResultId", randomTestResultId, "", "REPLACE");
        JsonPathAlteration alterationPutTestStartTimestamp = new JsonPathAlteration("$.testResult.testStartTimestamp",new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 1)) , "", "REPLACE");
        JsonPathAlteration alterationPutTestEndTimestamp = new JsonPathAlteration("$.testResult.testEndTimestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 4)), "", "REPLACE");
        JsonPathAlteration alterationPutTestTypeStartTimestamp = new JsonPathAlteration("$.testResult.testTypes[0].testTypeStartTimestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 2)), "", "REPLACE");
        JsonPathAlteration alterationPutTestTypeEndTimestamp = new JsonPathAlteration("$.testResult.testTypes[0].testTypeEndTimestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 3)), "", "REPLACE");
        JsonPathAlteration alterationNumberOfSeatBeltsFitted = new JsonPathAlteration("$.testResult.testTypes[0].numberOfSeatbeltsFitted", "", "", "DELETE");
        JsonPathAlteration alterationSeatBeltInstallationCheckDate = new JsonPathAlteration("$.testResult.testTypes[0].seatbeltInstallationCheckDate", "", "", "DELETE");
        JsonPathAlteration alterationLastSeatBeltInstallationCheckDate = new JsonPathAlteration("$.testResult.testTypes[0].lastSeatbeltInstallationCheckDate", "", "", "DELETE");
        JsonPathAlteration alterationTestAnniversaryDate = new JsonPathAlteration("$.testResult.testTypes[0].testAnniversaryDate", "", "", "DELETE");
        JsonPathAlteration alterationModType = new JsonPathAlteration("$.testResult.testTypes[0].modType", "", "", "DELETE");
        JsonPathAlteration alterationParticulateTrapSerialNumber = new JsonPathAlteration("$.testResult.testTypes[0].particulateTrapSerialNumber", "", "", "DELETE");
        JsonPathAlteration alterationSmokeTestKLimitApplied = new JsonPathAlteration("$.testResult.testTypes[0].smokeTestKLimitApplied", "", "", "DELETE");
        JsonPathAlteration alterationEmissionStandard = new JsonPathAlteration("$.testResult.testTypes[0].emissionStandard", "", "", "DELETE");
        JsonPathAlteration alterationModificationTypeUsed = new JsonPathAlteration("$.testResult.testTypes[0].modificationTypeUsed", "", "", "DELETE");
        JsonPathAlteration alterationParticulateTrapFitted = new JsonPathAlteration("$.testResult.testTypes[0].particulateTrapFitted", "", "", "DELETE");
        JsonPathAlteration alterationFuelType = new JsonPathAlteration("$.testResult.testTypes[0].fuelType", "", "", "DELETE");
        JsonPathAlteration alterationDefects = new JsonPathAlteration("$.testResult.testTypes[0].defects", "", "", "DELETE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterationsPutTestResults = new ArrayList<>(Arrays.asList(
                alterationTestTypeID,
                alterationSystemNumberPutTestResults,
                alterationVinPutTestResults,
                alterationTestResultIdPut,
                alterationPutTestStartTimestamp,
                alterationPutTestEndTimestamp,
                alterationPutTestTypeStartTimestamp,
                alterationPutTestTypeEndTimestamp,
                alterationNumberOfSeatBeltsFitted,
                alterationSeatBeltInstallationCheckDate,
                alterationLastSeatBeltInstallationCheckDate,
                alterationTestAnniversaryDate,
                alterationModType,
                alterationParticulateTrapSerialNumber,
                alterationSmokeTestKLimitApplied,
                alterationEmissionStandard,
                alterationModificationTypeUsed,
                alterationParticulateTrapFitted,
                alterationFuelType,
                alterationDefects
        ));

        testResultsSteps.putTestResultsWithAlterations(randomSystemNumber,putRequestBody,alterationsPutTestResults);
        testResultsSteps.statusCodeShouldBe(HttpStatus.SC_OK);
    }

    @WithTag("Vtm")
    @Title("CVSB-10300 - AC1 PUT: Attempt to update a test record with a not applicable field - TestTypeId = 49")
    @Test
    public void testResultsPutApplicableTir() {
        // Read the base test result JSON.
        String postRequestBody = GenericData.readJsonValueFromFile("technical-records_trl_all_fields.json","$");

        // Create alteration to add one more tech record to in the request body
        String randomSystemNumber = GenericData.generateRandomSystemNumber();
        String randomVin = GenericData.generateRandomVin();
        JsonPathAlteration alterationSystemNumberVehicle = new JsonPathAlteration("$.systemNumber", randomSystemNumber, "", "REPLACE");
        JsonPathAlteration alterationVinVehicle = new JsonPathAlteration("$.vin", randomVin,"","REPLACE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterationsVehicle = new ArrayList<>(Arrays.asList(alterationSystemNumberVehicle,
                alterationVinVehicle));

        // Post the results, together with any alterations, and verify that they are accepted.
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBody, alterationsVehicle);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(HttpStatus.SC_CREATED);
        vehicleTechnicalRecordsSteps.validateData("Technical Record created");

        String testResultRecord = GenericData.readJsonValueFromFile("test-results_post_payload_trl_10300.json", "$");

        // Create alteration to add one more tech record to in the request body

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


        // Read the base JSON for PUT test-results
        String putRequestBody = GenericData.readJsonValueFromFile("test-results_put_payload_trl_10300.json","$");

        JsonPathAlteration alterationSystemNumberPutTestResults = new JsonPathAlteration("$.testResult.systemNumber", randomSystemNumber, "", "REPLACE");
        JsonPathAlteration alterationVinPutTestResults = new JsonPathAlteration("$.testResult.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationTestResultIdPut = new JsonPathAlteration("$.testResult.testResultId", randomTestResultId, "", "REPLACE");
        JsonPathAlteration alterationPutTestStartTimestamp = new JsonPathAlteration("$.testResult.testStartTimestamp",new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 1)) , "", "REPLACE");
        JsonPathAlteration alterationPutTestEndTimestamp = new JsonPathAlteration("$.testResult.testEndTimestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 4)), "", "REPLACE");
        JsonPathAlteration alterationPutTestTypeStartTimestamp = new JsonPathAlteration("$.testResult.testTypes[0].testTypeStartTimestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 2)), "", "REPLACE");
        JsonPathAlteration alterationPutTestTypeEndTimestamp = new JsonPathAlteration("$.testResult.testTypes[0].testTypeEndTimestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 3)), "", "REPLACE");


        JsonPathAlteration alterationTestExpiryDate = new JsonPathAlteration("$.testResult.testTypes[0].testExpiryDate", "", "", "DELETE");
        JsonPathAlteration alterationTestAnniversaryDate = new JsonPathAlteration("$.testResult.testTypes[0].testAnniversaryDate", "", "", "DELETE");
        JsonPathAlteration alterationModType = new JsonPathAlteration("$.testResult.testTypes[0].modType", "", "", "DELETE");
        JsonPathAlteration alterationParticulateTrapSerialNumber = new JsonPathAlteration("$.testResult.testTypes[0].particulateTrapSerialNumber", "", "", "DELETE");
        JsonPathAlteration alterationSmokeTestKLimitApplied = new JsonPathAlteration("$.testResult.testTypes[0].smokeTestKLimitApplied", "", "", "DELETE");
        JsonPathAlteration alterationEmissionStandard = new JsonPathAlteration("$.testResult.testTypes[0].emissionStandard", "", "", "DELETE");
        JsonPathAlteration alterationModificationTypeUsed = new JsonPathAlteration("$.testResult.testTypes[0].modificationTypeUsed", "", "", "DELETE");
        JsonPathAlteration alterationParticulateTrapFitted = new JsonPathAlteration("$.testResult.testTypes[0].particulateTrapFitted", "", "", "DELETE");
        JsonPathAlteration alterationFuelType = new JsonPathAlteration("$.testResult.testTypes[0].fuelType", "", "", "DELETE");
        JsonPathAlteration alterationNumberOfSeatBeltsFitted = new JsonPathAlteration("$.testResult.testTypes[0].numberOfSeatbeltsFitted", "", "", "DELETE");
        JsonPathAlteration alterationSeatBeltInstallationCheckDate = new JsonPathAlteration("$.testResult.testTypes[0].seatbeltInstallationCheckDate", "", "", "DELETE");
        JsonPathAlteration alterationLastSeatBeltInstallationCheckDate = new JsonPathAlteration("$.testResult.testTypes[0].lastSeatbeltInstallationCheckDate", "", "", "DELETE");
        JsonPathAlteration alterationDefects = new JsonPathAlteration("$.testResult.testTypes[0].defects", "", "", "DELETE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterationsPutTestResults = new ArrayList<>(Arrays.asList(
                alterationSystemNumberPutTestResults,
                alterationVinPutTestResults,
                alterationTestResultIdPut,
                alterationPutTestStartTimestamp,
                alterationPutTestEndTimestamp,
                alterationPutTestTypeStartTimestamp,
                alterationPutTestTypeEndTimestamp,
                alterationTestExpiryDate,
                alterationNumberOfSeatBeltsFitted,
                alterationSeatBeltInstallationCheckDate,
                alterationLastSeatBeltInstallationCheckDate,
                alterationTestAnniversaryDate,
                alterationModType,
                alterationParticulateTrapSerialNumber,
                alterationSmokeTestKLimitApplied,
                alterationEmissionStandard,
                alterationModificationTypeUsed,
                alterationParticulateTrapFitted,
                alterationFuelType,
                alterationDefects
        ));

        testResultsSteps.putTestResultsWithAlterations(randomSystemNumber,putRequestBody,alterationsPutTestResults);
        testResultsSteps.statusCodeShouldBe(HttpStatus.SC_OK);
    }

    @WithTag("Vtm")
    @Title("CVSB-10300 - AC1 PUT: Attempt to update a test record with a not applicable field - Positive - TestTypeId = 56")
    @Test
    public void testResultsPutApplicablePaidTirRetest() {
        // Read the base test result JSON.
        String postRequestBody = GenericData.readJsonValueFromFile("technical-records_trl_all_fields.json","$");

        // Create alteration to add one more tech record to in the request body
        String randomSystemNumber = GenericData.generateRandomSystemNumber();
        String randomVin = GenericData.generateRandomVin();
        JsonPathAlteration alterationSystemNumberVehicle = new JsonPathAlteration("$.systemNumber", randomSystemNumber, "", "REPLACE");
        JsonPathAlteration alterationVinVehicle = new JsonPathAlteration("$.vin", randomVin,"","REPLACE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterationsVehicle = new ArrayList<>(Arrays.asList(alterationSystemNumberVehicle,
                alterationVinVehicle));

        // Post the results, together with any alterations, and verify that they are accepted.
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBody, alterationsVehicle);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(HttpStatus.SC_CREATED);
        vehicleTechnicalRecordsSteps.validateData("Technical Record created");

        String testResultRecord = GenericData.readJsonValueFromFile("test-results_post_payload_trl_10300.json", "$");

        // Create alteration to add one more tech record to in the request body

        String randomTestResultId = UUID.randomUUID().toString();
        JsonPathAlteration alterationTestTypeId = new JsonPathAlteration("$.testTypes[0].testTypeId", "56", "", "REPLACE");
        JsonPathAlteration alterationSystemNumberTestResults = new JsonPathAlteration("$.systemNumber", randomSystemNumber, "", "REPLACE");
        JsonPathAlteration alterationVinTestResults = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationTestResultIdPost = new JsonPathAlteration("$.testResultId", randomTestResultId, "", "REPLACE");
        JsonPathAlteration alterationTestStartTimestamp = new JsonPathAlteration("$.testStartTimestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 1)), "", "REPLACE");
        JsonPathAlteration alterationTestEndTimestamp = new JsonPathAlteration("$.testEndTimestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 4)), "", "REPLACE");
        JsonPathAlteration alterationTestTypeStartTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeStartTimestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 2)), "", "REPLACE");
        JsonPathAlteration alterationTestTypeEndTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeEndTimestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 3)), "", "REPLACE");


        // Collate the list of alterations.
        List<JsonPathAlteration> alterationsTestResults = new ArrayList<>(Arrays.asList(
                alterationTestTypeId,
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


        // Read the base JSON for PUT test-results
        String putRequestBody = GenericData.readJsonValueFromFile("test-results_put_payload_trl_10300.json","$");

        JsonPathAlteration alterationTestTypeID = new JsonPathAlteration("$.testResult.testTypes[0].testTypeId", "56", "", "REPLACE");
        JsonPathAlteration alterationSystemNumberPutTestResults = new JsonPathAlteration("$.testResult.systemNumber", randomSystemNumber, "", "REPLACE");
        JsonPathAlteration alterationVinPutTestResults = new JsonPathAlteration("$.testResult.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationTestResultIdPut = new JsonPathAlteration("$.testResult.testResultId", randomTestResultId, "", "REPLACE");
        JsonPathAlteration alterationPutTestStartTimestamp = new JsonPathAlteration("$.testResult.testStartTimestamp",new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 1)) , "", "REPLACE");
        JsonPathAlteration alterationPutTestEndTimestamp = new JsonPathAlteration("$.testResult.testEndTimestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 4)), "", "REPLACE");
        JsonPathAlteration alterationPutTestTypeStartTimestamp = new JsonPathAlteration("$.testResult.testTypes[0].testTypeStartTimestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 2)), "", "REPLACE");
        JsonPathAlteration alterationPutTestTypeEndTimestamp = new JsonPathAlteration("$.testResult.testTypes[0].testTypeEndTimestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 3)), "", "REPLACE");


        JsonPathAlteration alterationTestExpiryDate = new JsonPathAlteration("$.testResult.testTypes[0].testExpiryDate", "", "", "DELETE");
        JsonPathAlteration alterationTestAnniversaryDate = new JsonPathAlteration("$.testResult.testTypes[0].testAnniversaryDate", "", "", "DELETE");
        JsonPathAlteration alterationModType = new JsonPathAlteration("$.testResult.testTypes[0].modType", "", "", "DELETE");
        JsonPathAlteration alterationParticulateTrapSerialNumber = new JsonPathAlteration("$.testResult.testTypes[0].particulateTrapSerialNumber", "", "", "DELETE");
        JsonPathAlteration alterationSmokeTestKLimitApplied = new JsonPathAlteration("$.testResult.testTypes[0].smokeTestKLimitApplied", "", "", "DELETE");
        JsonPathAlteration alterationEmissionStandard = new JsonPathAlteration("$.testResult.testTypes[0].emissionStandard", "", "", "DELETE");
        JsonPathAlteration alterationModificationTypeUsed = new JsonPathAlteration("$.testResult.testTypes[0].modificationTypeUsed", "", "", "DELETE");
        JsonPathAlteration alterationParticulateTrapFitted = new JsonPathAlteration("$.testResult.testTypes[0].particulateTrapFitted", "", "", "DELETE");
        JsonPathAlteration alterationFuelType = new JsonPathAlteration("$.testResult.testTypes[0].fuelType", "", "", "DELETE");
        JsonPathAlteration alterationNumberOfSeatBeltsFitted = new JsonPathAlteration("$.testResult.testTypes[0].numberOfSeatbeltsFitted", "", "", "DELETE");
        JsonPathAlteration alterationSeatBeltInstallationCheckDate = new JsonPathAlteration("$.testResult.testTypes[0].seatbeltInstallationCheckDate", "", "", "DELETE");
        JsonPathAlteration alterationLastSeatBeltInstallationCheckDate = new JsonPathAlteration("$.testResult.testTypes[0].lastSeatbeltInstallationCheckDate", "", "", "DELETE");
        JsonPathAlteration alterationDefects = new JsonPathAlteration("$.testResult.testTypes[0].defects", "", "", "DELETE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterationsPutTestResults = new ArrayList<>(Arrays.asList(
                alterationTestTypeID,
                alterationSystemNumberPutTestResults,
                alterationVinPutTestResults,
                alterationTestResultIdPut,
                alterationPutTestStartTimestamp,
                alterationPutTestEndTimestamp,
                alterationPutTestTypeStartTimestamp,
                alterationPutTestTypeEndTimestamp,
                alterationTestExpiryDate,
                alterationNumberOfSeatBeltsFitted,
                alterationSeatBeltInstallationCheckDate,
                alterationLastSeatBeltInstallationCheckDate,
                alterationTestAnniversaryDate,
                alterationModType,
                alterationParticulateTrapSerialNumber,
                alterationSmokeTestKLimitApplied,
                alterationEmissionStandard,
                alterationModificationTypeUsed,
                alterationParticulateTrapFitted,
                alterationFuelType,
                alterationDefects
        ));

        testResultsSteps.putTestResultsWithAlterations(randomSystemNumber,putRequestBody,alterationsPutTestResults);
        testResultsSteps.statusCodeShouldBe(HttpStatus.SC_OK);
    }

    @WithTag("Vtm")
    @Title("CVSB-10300 - AC1 PUT: Attempt to update a test record with a not applicable field - TestTypeId = 57")
    @Test
    public void testResultsPutApplicableFreeTirRetest() {
        // Read the base test result JSON.
        String postRequestBody = GenericData.readJsonValueFromFile("technical-records_trl_all_fields.json","$");

        // Create alteration to add one more tech record to in the request body
        String randomSystemNumber = GenericData.generateRandomSystemNumber();
        String randomVin = GenericData.generateRandomVin();
        JsonPathAlteration alterationSystemNumberVehicle = new JsonPathAlteration("$.systemNumber", randomSystemNumber, "", "REPLACE");
        JsonPathAlteration alterationVinVehicle = new JsonPathAlteration("$.vin", randomVin,"","REPLACE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterationsVehicle = new ArrayList<>(Arrays.asList(alterationSystemNumberVehicle,
                alterationVinVehicle));

        // Post the results, together with any alterations, and verify that they are accepted.
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBody, alterationsVehicle);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(HttpStatus.SC_CREATED);
        vehicleTechnicalRecordsSteps.validateData("Technical Record created");

        String testResultRecord = GenericData.readJsonValueFromFile("test-results_post_payload_trl_10300.json", "$");

        // Create alteration to add one more tech record to in the request body

        String randomTestResultId = UUID.randomUUID().toString();
        JsonPathAlteration alterationTestTypeId = new JsonPathAlteration("$.testTypes[0].testTypeId", "57", "", "REPLACE");
        JsonPathAlteration alterationNoOfAxles = new JsonPathAlteration("$.noOfAxles", "3", "", "REPLACE");
        JsonPathAlteration alterationSystemNumberTestResults = new JsonPathAlteration("$.systemNumber", randomSystemNumber, "", "REPLACE");
        JsonPathAlteration alterationVinTestResults = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationTestResultIdPost = new JsonPathAlteration("$.testResultId", randomTestResultId, "", "REPLACE");
        JsonPathAlteration alterationTestStartTimestamp = new JsonPathAlteration("$.testStartTimestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 1)), "", "REPLACE");
        JsonPathAlteration alterationTestEndTimestamp = new JsonPathAlteration("$.testEndTimestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 4)), "", "REPLACE");
        JsonPathAlteration alterationTestTypeStartTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeStartTimestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 2)), "", "REPLACE");
        JsonPathAlteration alterationTestTypeEndTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeEndTimestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 3)), "", "REPLACE");


        // Collate the list of alterations.
        List<JsonPathAlteration> alterationsTestResults = new ArrayList<>(Arrays.asList(
                alterationTestTypeId,
                alterationNoOfAxles,
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


        // Read the base JSON for PUT test-results
        String putRequestBody = GenericData.readJsonValueFromFile("test-results_put_payload_trl_10300.json","$");

        JsonPathAlteration alterationTestTypeID = new JsonPathAlteration("$.testResult.testTypes[0].testTypeId", "57", "", "REPLACE");
        JsonPathAlteration alterationNoOfAxlesPut = new JsonPathAlteration("$.testResult.noOfAxles", "3", "", "REPLACE");
        JsonPathAlteration alterationSystemNumberPutTestResults = new JsonPathAlteration("$.testResult.systemNumber", randomSystemNumber, "", "REPLACE");
        JsonPathAlteration alterationVinPutTestResults = new JsonPathAlteration("$.testResult.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationTestResultIdPut = new JsonPathAlteration("$.testResult.testResultId", randomTestResultId, "", "REPLACE");
        JsonPathAlteration alterationPutTestStartTimestamp = new JsonPathAlteration("$.testResult.testStartTimestamp",new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 1)) , "", "REPLACE");
        JsonPathAlteration alterationPutTestEndTimestamp = new JsonPathAlteration("$.testResult.testEndTimestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 4)), "", "REPLACE");
        JsonPathAlteration alterationPutTestTypeStartTimestamp = new JsonPathAlteration("$.testResult.testTypes[0].testTypeStartTimestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 2)), "", "REPLACE");
        JsonPathAlteration alterationPutTestTypeEndTimestamp = new JsonPathAlteration("$.testResult.testTypes[0].testTypeEndTimestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 3)), "", "REPLACE");


        JsonPathAlteration alterationTestExpiryDate = new JsonPathAlteration("$.testResult.testTypes[0].testExpiryDate", "", "", "DELETE");
        JsonPathAlteration alterationTestAnniversaryDate = new JsonPathAlteration("$.testResult.testTypes[0].testAnniversaryDate", "", "", "DELETE");
        JsonPathAlteration alterationModType = new JsonPathAlteration("$.testResult.testTypes[0].modType", "", "", "DELETE");
        JsonPathAlteration alterationParticulateTrapSerialNumber = new JsonPathAlteration("$.testResult.testTypes[0].particulateTrapSerialNumber", "", "", "DELETE");
        JsonPathAlteration alterationSmokeTestKLimitApplied = new JsonPathAlteration("$.testResult.testTypes[0].smokeTestKLimitApplied", "", "", "DELETE");
        JsonPathAlteration alterationEmissionStandard = new JsonPathAlteration("$.testResult.testTypes[0].emissionStandard", "", "", "DELETE");
        JsonPathAlteration alterationModificationTypeUsed = new JsonPathAlteration("$.testResult.testTypes[0].modificationTypeUsed", "", "", "DELETE");
        JsonPathAlteration alterationParticulateTrapFitted = new JsonPathAlteration("$.testResult.testTypes[0].particulateTrapFitted", "", "", "DELETE");
        JsonPathAlteration alterationFuelType = new JsonPathAlteration("$.testResult.testTypes[0].fuelType", "", "", "DELETE");
        JsonPathAlteration alterationNumberOfSeatBeltsFitted = new JsonPathAlteration("$.testResult.testTypes[0].numberOfSeatbeltsFitted", "", "", "DELETE");
        JsonPathAlteration alterationSeatBeltInstallationCheckDate = new JsonPathAlteration("$.testResult.testTypes[0].seatbeltInstallationCheckDate", "", "", "DELETE");
        JsonPathAlteration alterationLastSeatBeltInstallationCheckDate = new JsonPathAlteration("$.testResult.testTypes[0].lastSeatbeltInstallationCheckDate", "", "", "DELETE");
        JsonPathAlteration alterationDefects = new JsonPathAlteration("$.testResult.testTypes[0].defects", "", "", "DELETE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterationsPutTestResults = new ArrayList<>(Arrays.asList(
                alterationTestTypeID,
                alterationNoOfAxlesPut,
                alterationSystemNumberPutTestResults,
                alterationVinPutTestResults,
                alterationTestResultIdPut,
                alterationPutTestStartTimestamp,
                alterationPutTestEndTimestamp,
                alterationPutTestTypeStartTimestamp,
                alterationPutTestTypeEndTimestamp,
                alterationTestExpiryDate,
                alterationNumberOfSeatBeltsFitted,
                alterationSeatBeltInstallationCheckDate,
                alterationLastSeatBeltInstallationCheckDate,
                alterationTestAnniversaryDate,
                alterationModType,
                alterationParticulateTrapSerialNumber,
                alterationSmokeTestKLimitApplied,
                alterationEmissionStandard,
                alterationModificationTypeUsed,
                alterationParticulateTrapFitted,
                alterationFuelType,
                alterationDefects
        ));

        testResultsSteps.putTestResultsWithAlterations(randomSystemNumber,putRequestBody,alterationsPutTestResults);
        testResultsSteps.statusCodeShouldBe(HttpStatus.SC_OK);
    }


    @WithTag("Vtm")
    @Title("CVSB-10300 - AC1 PUT: Attempt to update a test record with a not applicable field - testTypeID=39")
    @Test
    public void testResultsPutApplicableAnnualLecTest() {
        // Read the base test result JSON.
        String postRequestBody = GenericData.readJsonValueFromFile("technical-records_psv_all_fields.json","$");

        // Create alteration to add one more tech record to in the request body
        String randomSystemNumber = GenericData.generateRandomSystemNumber();
        String randomVin = GenericData.generateRandomVin();
        JsonPathAlteration alterationSystemNumberVehicle = new JsonPathAlteration("$.systemNumber", randomSystemNumber, "", "REPLACE");
        JsonPathAlteration alterationVinVehicle = new JsonPathAlteration("$.vin", randomVin,"","REPLACE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterationsVehicle = new ArrayList<>(Arrays.asList(alterationSystemNumberVehicle,
                alterationVinVehicle));

        // Post the results, together with any alterations, and verify that they are accepted.
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBody, alterationsVehicle);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(HttpStatus.SC_CREATED);
        vehicleTechnicalRecordsSteps.validateData("Technical Record created");

        String testResultRecord = GenericData.readJsonValueFromFile("test-results_post_payload_psv_10300.json", "$");

        // Create alteration to add one more tech record to in the request body

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


        // Read the base JSON for PUT test-results
        String putRequestBody = GenericData.readJsonValueFromFile("test-results_put_payload_psv_10300.json","$");

        JsonPathAlteration alterationSystemNumberPutTestResults = new JsonPathAlteration("$.testResult.systemNumber", randomSystemNumber, "", "REPLACE");
        JsonPathAlteration alterationVinPutTestResults = new JsonPathAlteration("$.testResult.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationTestResultIdPut = new JsonPathAlteration("$.testResult.testResultId", randomTestResultId, "", "REPLACE");
        JsonPathAlteration alterationPutTestStartTimestamp = new JsonPathAlteration("$.testResult.testStartTimestamp",new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 1)) , "", "REPLACE");
        JsonPathAlteration alterationPutTestEndTimestamp = new JsonPathAlteration("$.testResult.testEndTimestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 4)), "", "REPLACE");
        JsonPathAlteration alterationPutTestTypeStartTimestamp = new JsonPathAlteration("$.testResult.testTypes[0].testTypeStartTimestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 2)), "", "REPLACE");
        JsonPathAlteration alterationPutTestTypeEndTimestamp = new JsonPathAlteration("$.testResult.testTypes[0].testTypeEndTimestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 3)), "", "REPLACE");
        JsonPathAlteration alterationTestAnniversaryDate = new JsonPathAlteration("$.testResult.testTypes[0].testAnniversaryDate", "", "", "DELETE");
        JsonPathAlteration alterationNumberOfSeatBeltsFitted = new JsonPathAlteration("$.testResult.testTypes[0].numberOfSeatbeltsFitted", "", "", "DELETE");
        JsonPathAlteration alterationSeatBeltInstallationCheckDate = new JsonPathAlteration("$.testResult.testTypes[0].seatbeltInstallationCheckDate", "", "", "DELETE");
        JsonPathAlteration alterationLastSeatBeltInstallationCheckDate = new JsonPathAlteration("$.testResult.testTypes[0].lastSeatbeltInstallationCheckDate", "", "", "DELETE");
        JsonPathAlteration alterationDefects = new JsonPathAlteration("$.testResult.testTypes[0].defects", "", "", "DELETE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterationsPutTestResults = new ArrayList<>(Arrays.asList(
                alterationSystemNumberPutTestResults,
                alterationVinPutTestResults,
                alterationTestResultIdPut,
                alterationPutTestStartTimestamp,
                alterationPutTestEndTimestamp,
                alterationPutTestTypeStartTimestamp,
                alterationPutTestTypeEndTimestamp,
                alterationNumberOfSeatBeltsFitted,
                alterationSeatBeltInstallationCheckDate,
                alterationLastSeatBeltInstallationCheckDate,
                alterationTestAnniversaryDate,
                alterationDefects
        ));

        testResultsSteps.putTestResultsWithAlterations(randomSystemNumber,putRequestBody,alterationsPutTestResults);
        testResultsSteps.statusCodeShouldBe(HttpStatus.SC_OK);
    }

    @WithTag("Vtm")
    @Title("CVSB-10300 - AC1 PUT: Attempt to update a test record with a not applicable field - TestTypeId = 44")
    @Test
    public void testResultsPutApplicableWithLinkedLecTest() {
        // Read the base test result JSON.
        String postRequestBody = GenericData.readJsonValueFromFile("technical-records_hgv_all_fields.json","$");

        // Create alteration to add one more tech record to in the request body
        String randomSystemNumber = GenericData.generateRandomSystemNumber();
        String randomVin = GenericData.generateRandomVin();
        JsonPathAlteration alterationSystemNumberVehicle = new JsonPathAlteration("$.systemNumber", randomSystemNumber, "", "REPLACE");
        JsonPathAlteration alterationVinVehicle = new JsonPathAlteration("$.vin", randomVin,"","REPLACE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterationsVehicle = new ArrayList<>(Arrays.asList(alterationSystemNumberVehicle,
                alterationVinVehicle));

        // Post the results, together with any alterations, and verify that they are accepted.
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBody, alterationsVehicle);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(HttpStatus.SC_CREATED);
        vehicleTechnicalRecordsSteps.validateData("Technical Record created");

        String testResultRecord = GenericData.readJsonValueFromFile("test-results_post_payload_hgv_10300.json", "$");

        // Create alteration to add one more tech record to in the request body

        String randomTestResultId = UUID.randomUUID().toString();
        JsonPathAlteration alterationTestTypeId = new JsonPathAlteration("$.testTypes[0].testTypeId", "44", "", "REPLACE");
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
                alterationTestTypeId,
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


        // Read the base JSON for PUT test-results
        String putRequestBody = GenericData.readJsonValueFromFile("test-results_put_payload_hgv_10300.json","$");

        JsonPathAlteration alterationTestTypeID = new JsonPathAlteration("$.testResult.testTypes[0].testTypeId", "44", "", "REPLACE");
        JsonPathAlteration alterationSystemNumberPutTestResults = new JsonPathAlteration("$.testResult.systemNumber", randomSystemNumber, "", "REPLACE");
        JsonPathAlteration alterationVinPutTestResults = new JsonPathAlteration("$.testResult.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationTestResultIdPut = new JsonPathAlteration("$.testResult.testResultId", randomTestResultId, "", "REPLACE");
        JsonPathAlteration alterationPutTestStartTimestamp = new JsonPathAlteration("$.testResult.testStartTimestamp",new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 1)) , "", "REPLACE");
        JsonPathAlteration alterationPutTestEndTimestamp = new JsonPathAlteration("$.testResult.testEndTimestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 4)), "", "REPLACE");
        JsonPathAlteration alterationPutTestTypeStartTimestamp = new JsonPathAlteration("$.testResult.testTypes[0].testTypeStartTimestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 2)), "", "REPLACE");
        JsonPathAlteration alterationPutTestTypeEndTimestamp = new JsonPathAlteration("$.testResult.testTypes[0].testTypeEndTimestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 3)), "", "REPLACE");


        JsonPathAlteration alterationTestAnniversaryDate = new JsonPathAlteration("$.testResult.testTypes[0].testAnniversaryDate", "", "", "DELETE");
        JsonPathAlteration alterationNumberOfSeatBeltsFitted = new JsonPathAlteration("$.testResult.testTypes[0].numberOfSeatbeltsFitted", "", "", "DELETE");
        JsonPathAlteration alterationSeatBeltInstallationCheckDate = new JsonPathAlteration("$.testResult.testTypes[0].seatbeltInstallationCheckDate", "", "", "DELETE");
        JsonPathAlteration alterationLastSeatBeltInstallationCheckDate = new JsonPathAlteration("$.testResult.testTypes[0].lastSeatbeltInstallationCheckDate", "", "", "DELETE");
        JsonPathAlteration alterationDefects = new JsonPathAlteration("$.testResult.testTypes[0].defects", "", "", "DELETE");
        JsonPathAlteration alterationStatusCode = new JsonPathAlteration("$.testResult.testTypes[0]","Big Filter", "modificationTypeUsed", "ADD_FIELD");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterationsPutTestResults = new ArrayList<>(Arrays.asList(
                alterationTestTypeID,
                alterationStatusCode,
                alterationSystemNumberPutTestResults,
                alterationVinPutTestResults,
                alterationTestResultIdPut,
                alterationPutTestStartTimestamp,
                alterationPutTestEndTimestamp,
                alterationPutTestTypeStartTimestamp,
                alterationPutTestTypeEndTimestamp,
                alterationNumberOfSeatBeltsFitted,
                alterationSeatBeltInstallationCheckDate,
                alterationLastSeatBeltInstallationCheckDate,
                alterationTestAnniversaryDate,
                alterationDefects
        ));

        testResultsSteps.putTestResultsWithAlterations(randomSystemNumber,putRequestBody,alterationsPutTestResults);
        testResultsSteps.statusCodeShouldBe(HttpStatus.SC_OK);
    }

    @WithTag("Vtm")
    @Title("CVSB-10300 - AC1 PUT: Attempt to update a test record with a not applicable field - TestTypeId = 45")
    @Test
    public void testResultsPutApplicableWithoutLinkedLecTest() {
        // Read the base test result JSON.
        String postRequestBody = GenericData.readJsonValueFromFile("technical-records_hgv_all_fields.json","$");

        // Create alteration to add one more tech record to in the request body
        String randomSystemNumber = GenericData.generateRandomSystemNumber();
        String randomVin = GenericData.generateRandomVin();
        JsonPathAlteration alterationSystemNumberVehicle = new JsonPathAlteration("$.systemNumber", randomSystemNumber, "", "REPLACE");
        JsonPathAlteration alterationVinVehicle = new JsonPathAlteration("$.vin", randomVin,"","REPLACE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterationsVehicle = new ArrayList<>(Arrays.asList(alterationSystemNumberVehicle,
                alterationVinVehicle));

        // Post the results, together with any alterations, and verify that they are accepted.
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBody, alterationsVehicle);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(HttpStatus.SC_CREATED);
        vehicleTechnicalRecordsSteps.validateData("Technical Record created");

        String testResultRecord = GenericData.readJsonValueFromFile("test-results_post_payload_hgv_10300.json", "$");

        // Create alteration to add one more tech record to in the request body

        String randomTestResultId = UUID.randomUUID().toString();
        JsonPathAlteration alterationTestTypeId = new JsonPathAlteration("$.testTypes[0].testTypeId", "45", "", "REPLACE");
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
                alterationTestTypeId,
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


        // Read the base JSON for PUT test-results
        String putRequestBody = GenericData.readJsonValueFromFile("test-results_put_payload_hgv_10300.json","$");

        JsonPathAlteration alterationTestTypeID = new JsonPathAlteration("$.testResult.testTypes[0].testTypeId", "45", "", "REPLACE");
        JsonPathAlteration alterationSystemNumberPutTestResults = new JsonPathAlteration("$.testResult.systemNumber", randomSystemNumber, "", "REPLACE");
        JsonPathAlteration alterationVinPutTestResults = new JsonPathAlteration("$.testResult.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationTestResultIdPut = new JsonPathAlteration("$.testResult.testResultId", randomTestResultId, "", "REPLACE");
        JsonPathAlteration alterationPutTestStartTimestamp = new JsonPathAlteration("$.testResult.testStartTimestamp",new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 1)) , "", "REPLACE");
        JsonPathAlteration alterationPutTestEndTimestamp = new JsonPathAlteration("$.testResult.testEndTimestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 4)), "", "REPLACE");
        JsonPathAlteration alterationPutTestTypeStartTimestamp = new JsonPathAlteration("$.testResult.testTypes[0].testTypeStartTimestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 2)), "", "REPLACE");
        JsonPathAlteration alterationPutTestTypeEndTimestamp = new JsonPathAlteration("$.testResult.testTypes[0].testTypeEndTimestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 3)), "", "REPLACE");


        JsonPathAlteration alterationTestAnniversaryDate = new JsonPathAlteration("$.testResult.testTypes[0].testAnniversaryDate", "", "", "DELETE");
        JsonPathAlteration alterationNumberOfSeatBeltsFitted = new JsonPathAlteration("$.testResult.testTypes[0].numberOfSeatbeltsFitted", "", "", "DELETE");
        JsonPathAlteration alterationSeatBeltInstallationCheckDate = new JsonPathAlteration("$.testResult.testTypes[0].seatbeltInstallationCheckDate", "", "", "DELETE");
        JsonPathAlteration alterationLastSeatBeltInstallationCheckDate = new JsonPathAlteration("$.testResult.testTypes[0].lastSeatbeltInstallationCheckDate", "", "", "DELETE");
        JsonPathAlteration alterationDefects = new JsonPathAlteration("$.testResult.testTypes[0].defects", "", "", "DELETE");
        JsonPathAlteration alterationStatusCode = new JsonPathAlteration("$.testResult.testTypes[0]","Big Filter", "modificationTypeUsed", "ADD_FIELD");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterationsPutTestResults = new ArrayList<>(Arrays.asList(
                alterationTestTypeID,
                alterationStatusCode,
                alterationSystemNumberPutTestResults,
                alterationVinPutTestResults,
                alterationTestResultIdPut,
                alterationPutTestStartTimestamp,
                alterationPutTestEndTimestamp,
                alterationPutTestTypeStartTimestamp,
                alterationPutTestTypeEndTimestamp,
                alterationNumberOfSeatBeltsFitted,
                alterationSeatBeltInstallationCheckDate,
                alterationLastSeatBeltInstallationCheckDate,
                alterationTestAnniversaryDate,
                alterationDefects
        ));

        testResultsSteps.putTestResultsWithAlterations(randomSystemNumber,putRequestBody,alterationsPutTestResults);
        testResultsSteps.statusCodeShouldBe(HttpStatus.SC_OK);
    }

    @WithTag("Vtm")
    @Title("CVSB-10300 - AC1 PUT: Attempt to update a test record with a not applicable field - TestTypeId = 62")
    @Test
    public void testResultsPutApplicablePaidRwRetest() {
        // Read the base test result JSON.
        String postRequestBody = GenericData.readJsonValueFromFile("technical-records_trl_all_fields.json","$");

        // Create alteration to add one more tech record to in the request body
        String randomSystemNumber = GenericData.generateRandomSystemNumber();
        String randomVin = GenericData.generateRandomVin();
        JsonPathAlteration alterationSystemNumberVehicle = new JsonPathAlteration("$.systemNumber", randomSystemNumber, "", "REPLACE");
        JsonPathAlteration alterationVinVehicle = new JsonPathAlteration("$.vin", randomVin,"","REPLACE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterationsVehicle = new ArrayList<>(Arrays.asList(alterationSystemNumberVehicle,
                alterationVinVehicle));

        // Post the results, together with any alterations, and verify that they are accepted.
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBody, alterationsVehicle);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(HttpStatus.SC_CREATED);
        vehicleTechnicalRecordsSteps.validateData("Technical Record created");

        String testResultRecord = GenericData.readJsonValueFromFile("test-results_post_payload_trl_10300.json", "$");

        // Create alteration to add one more tech record to in the request body

        String randomTestResultId = UUID.randomUUID().toString();
        JsonPathAlteration alterationTestTypeId = new JsonPathAlteration("$.testTypes[0].testTypeId", "62", "", "REPLACE");
        JsonPathAlteration alterationSystemNumberTestResults = new JsonPathAlteration("$.systemNumber", randomSystemNumber, "", "REPLACE");
        JsonPathAlteration alterationVinTestResults = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationNoOfAxles = new JsonPathAlteration("$.noOfAxles", "3", "", "REPLACE");
        JsonPathAlteration alterationTestResultIdPost = new JsonPathAlteration("$.testResultId", randomTestResultId, "", "REPLACE");
        JsonPathAlteration alterationTestStartTimestamp = new JsonPathAlteration("$.testStartTimestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 1)), "", "REPLACE");
        JsonPathAlteration alterationTestEndTimestamp = new JsonPathAlteration("$.testEndTimestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 4)), "", "REPLACE");
        JsonPathAlteration alterationTestTypeStartTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeStartTimestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 2)), "", "REPLACE");
        JsonPathAlteration alterationTestTypeEndTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeEndTimestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 3)), "", "REPLACE");


        // Collate the list of alterations.
        List<JsonPathAlteration> alterationsTestResults = new ArrayList<>(Arrays.asList(
                alterationTestTypeId,
                alterationNoOfAxles,
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


        // Read the base JSON for PUT test-results
        String putRequestBody = GenericData.readJsonValueFromFile("test-results_put_payload_trl_10300.json","$");

        JsonPathAlteration alterationTestTypeID = new JsonPathAlteration("$.testResult.testTypes[0].testTypeId", "62", "", "REPLACE");
        JsonPathAlteration alterationSystemNumberPutTestResults = new JsonPathAlteration("$.testResult.systemNumber", randomSystemNumber, "", "REPLACE");
        JsonPathAlteration alterationVinPutTestResults = new JsonPathAlteration("$.testResult.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationNoOfAxlesPut = new JsonPathAlteration("$.testResult.noOfAxles", "3", "", "REPLACE");
        JsonPathAlteration alterationTestResultIdPut = new JsonPathAlteration("$.testResult.testResultId", randomTestResultId, "", "REPLACE");
        JsonPathAlteration alterationPutTestStartTimestamp = new JsonPathAlteration("$.testResult.testStartTimestamp",new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 1)) , "", "REPLACE");
        JsonPathAlteration alterationPutTestEndTimestamp = new JsonPathAlteration("$.testResult.testEndTimestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 4)), "", "REPLACE");
        JsonPathAlteration alterationPutTestTypeStartTimestamp = new JsonPathAlteration("$.testResult.testTypes[0].testTypeStartTimestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 2)), "", "REPLACE");
        JsonPathAlteration alterationPutTestTypeEndTimestamp = new JsonPathAlteration("$.testResult.testTypes[0].testTypeEndTimestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 3)), "", "REPLACE");


        JsonPathAlteration alterationTestExpiryDate = new JsonPathAlteration("$.testResult.testTypes[0].testExpiryDate", "", "", "DELETE");
        JsonPathAlteration alterationTestAnniversaryDate = new JsonPathAlteration("$.testResult.testTypes[0].testAnniversaryDate", "", "", "DELETE");
        JsonPathAlteration alterationModType = new JsonPathAlteration("$.testResult.testTypes[0].modType", "", "", "DELETE");
        JsonPathAlteration alterationParticulateTrapSerialNumber = new JsonPathAlteration("$.testResult.testTypes[0].particulateTrapSerialNumber", "", "", "DELETE");
        JsonPathAlteration alterationSmokeTestKLimitApplied = new JsonPathAlteration("$.testResult.testTypes[0].smokeTestKLimitApplied", "", "", "DELETE");
        JsonPathAlteration alterationEmissionStandard = new JsonPathAlteration("$.testResult.testTypes[0].emissionStandard", "", "", "DELETE");
        JsonPathAlteration alterationModificationTypeUsed = new JsonPathAlteration("$.testResult.testTypes[0].modificationTypeUsed", "", "", "DELETE");
        JsonPathAlteration alterationParticulateTrapFitted = new JsonPathAlteration("$.testResult.testTypes[0].particulateTrapFitted", "", "", "DELETE");
        JsonPathAlteration alterationFuelType = new JsonPathAlteration("$.testResult.testTypes[0].fuelType", "", "", "DELETE");
        JsonPathAlteration alterationNumberOfSeatBeltsFitted = new JsonPathAlteration("$.testResult.testTypes[0].numberOfSeatbeltsFitted", "", "", "DELETE");
        JsonPathAlteration alterationSeatBeltInstallationCheckDate = new JsonPathAlteration("$.testResult.testTypes[0].seatbeltInstallationCheckDate", "", "", "DELETE");
        JsonPathAlteration alterationLastSeatBeltInstallationCheckDate = new JsonPathAlteration("$.testResult.testTypes[0].lastSeatbeltInstallationCheckDate", "", "", "DELETE");
        JsonPathAlteration alterationProhibitionIssued = new JsonPathAlteration("$.testResult.testTypes[0].prohibitionIssued", "", "", "DELETE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterationsPutTestResults = new ArrayList<>(Arrays.asList(
                alterationTestTypeID,
                alterationSystemNumberPutTestResults,
                alterationVinPutTestResults,
                alterationNoOfAxlesPut,
                alterationTestResultIdPut,
                alterationPutTestStartTimestamp,
                alterationPutTestEndTimestamp,
                alterationPutTestTypeStartTimestamp,
                alterationPutTestTypeEndTimestamp,
                alterationTestExpiryDate,
                alterationNumberOfSeatBeltsFitted,
                alterationSeatBeltInstallationCheckDate,
                alterationLastSeatBeltInstallationCheckDate,
                alterationTestAnniversaryDate,
                alterationModType,
                alterationParticulateTrapSerialNumber,
                alterationSmokeTestKLimitApplied,
                alterationEmissionStandard,
                alterationModificationTypeUsed,
                alterationParticulateTrapFitted,
                alterationFuelType,
                alterationProhibitionIssued
        ));

        testResultsSteps.putTestResultsWithAlterations(randomSystemNumber,putRequestBody,alterationsPutTestResults);
        testResultsSteps.statusCodeShouldBe(HttpStatus.SC_OK);
    }

    @WithTag("Vtm")
    @Title("CVSB-10300 - AC1 PUT: Attempt to update a test record with a not applicable field - TestTypeId = 63")
    @Test
    public void testResultsPutApplicableRoadPartPaidRwRetest() {
        // Read the base test result JSON.
        String postRequestBody = GenericData.readJsonValueFromFile("technical-records_trl_all_fields.json","$");

        // Create alteration to add one more tech record to in the request body
        String randomSystemNumber = GenericData.generateRandomSystemNumber();
        String randomVin = GenericData.generateRandomVin();
        JsonPathAlteration alterationSystemNumberVehicle = new JsonPathAlteration("$.systemNumber", randomSystemNumber, "", "REPLACE");
        JsonPathAlteration alterationVinVehicle = new JsonPathAlteration("$.vin", randomVin,"","REPLACE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterationsVehicle = new ArrayList<>(Arrays.asList(alterationSystemNumberVehicle,
                alterationVinVehicle));

        // Post the results, together with any alterations, and verify that they are accepted.
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBody, alterationsVehicle);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(HttpStatus.SC_CREATED);
        vehicleTechnicalRecordsSteps.validateData("Technical Record created");

        String testResultRecord = GenericData.readJsonValueFromFile("test-results_post_payload_trl_10300.json", "$");

        // Create alteration to add one more tech record to in the request body

        String randomTestResultId = UUID.randomUUID().toString();
        JsonPathAlteration alterationTestTypeId = new JsonPathAlteration("$.testTypes[0].testTypeId", "63", "", "REPLACE");
        JsonPathAlteration alterationSystemNumberTestResults = new JsonPathAlteration("$.systemNumber", randomSystemNumber, "", "REPLACE");
        JsonPathAlteration alterationVinTestResults = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationTestResultIdPost = new JsonPathAlteration("$.testResultId", randomTestResultId, "", "REPLACE");
        JsonPathAlteration alterationTestStartTimestamp = new JsonPathAlteration("$.testStartTimestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 1)), "", "REPLACE");
        JsonPathAlteration alterationTestEndTimestamp = new JsonPathAlteration("$.testEndTimestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 4)), "", "REPLACE");
        JsonPathAlteration alterationTestTypeStartTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeStartTimestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 2)), "", "REPLACE");
        JsonPathAlteration alterationTestTypeEndTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeEndTimestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 3)), "", "REPLACE");


        // Collate the list of alterations.
        List<JsonPathAlteration> alterationsTestResults = new ArrayList<>(Arrays.asList(
                alterationTestTypeId,
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


        // Read the base JSON for PUT test-results
        String putRequestBody = GenericData.readJsonValueFromFile("test-results_put_payload_trl_10300.json","$");

        JsonPathAlteration alterationTestTypeID = new JsonPathAlteration("$.testResult.testTypes[0].testTypeId", "63", "", "REPLACE");
        JsonPathAlteration alterationSystemNumberPutTestResults = new JsonPathAlteration("$.testResult.systemNumber", randomSystemNumber, "", "REPLACE");
        JsonPathAlteration alterationVinPutTestResults = new JsonPathAlteration("$.testResult.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationTestResultIdPut = new JsonPathAlteration("$.testResult.testResultId", randomTestResultId, "", "REPLACE");
        JsonPathAlteration alterationPutTestStartTimestamp = new JsonPathAlteration("$.testResult.testStartTimestamp",new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 1)) , "", "REPLACE");
        JsonPathAlteration alterationPutTestEndTimestamp = new JsonPathAlteration("$.testResult.testEndTimestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 4)), "", "REPLACE");
        JsonPathAlteration alterationPutTestTypeStartTimestamp = new JsonPathAlteration("$.testResult.testTypes[0].testTypeStartTimestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 2)), "", "REPLACE");
        JsonPathAlteration alterationPutTestTypeEndTimestamp = new JsonPathAlteration("$.testResult.testTypes[0].testTypeEndTimestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 3)), "", "REPLACE");


        JsonPathAlteration alterationTestExpiryDate = new JsonPathAlteration("$.testResult.testTypes[0].testExpiryDate", "", "", "DELETE");
        JsonPathAlteration alterationTestAnniversaryDate = new JsonPathAlteration("$.testResult.testTypes[0].testAnniversaryDate", "", "", "DELETE");
        JsonPathAlteration alterationModType = new JsonPathAlteration("$.testResult.testTypes[0].modType", "", "", "DELETE");
        JsonPathAlteration alterationParticulateTrapSerialNumber = new JsonPathAlteration("$.testResult.testTypes[0].particulateTrapSerialNumber", "", "", "DELETE");
        JsonPathAlteration alterationSmokeTestKLimitApplied = new JsonPathAlteration("$.testResult.testTypes[0].smokeTestKLimitApplied", "", "", "DELETE");
        JsonPathAlteration alterationEmissionStandard = new JsonPathAlteration("$.testResult.testTypes[0].emissionStandard", "", "", "DELETE");
        JsonPathAlteration alterationModificationTypeUsed = new JsonPathAlteration("$.testResult.testTypes[0].modificationTypeUsed", "", "", "DELETE");
        JsonPathAlteration alterationParticulateTrapFitted = new JsonPathAlteration("$.testResult.testTypes[0].particulateTrapFitted", "", "", "DELETE");
        JsonPathAlteration alterationFuelType = new JsonPathAlteration("$.testResult.testTypes[0].fuelType", "", "", "DELETE");
        JsonPathAlteration alterationNumberOfSeatBeltsFitted = new JsonPathAlteration("$.testResult.testTypes[0].numberOfSeatbeltsFitted", "", "", "DELETE");
        JsonPathAlteration alterationSeatBeltInstallationCheckDate = new JsonPathAlteration("$.testResult.testTypes[0].seatbeltInstallationCheckDate", "", "", "DELETE");
        JsonPathAlteration alterationLastSeatBeltInstallationCheckDate = new JsonPathAlteration("$.testResult.testTypes[0].lastSeatbeltInstallationCheckDate", "", "", "DELETE");
        JsonPathAlteration alterationProhibitionIssued = new JsonPathAlteration("$.testResult.testTypes[0].prohibitionIssued", "", "", "DELETE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterationsPutTestResults = new ArrayList<>(Arrays.asList(
                alterationTestTypeID,
                alterationSystemNumberPutTestResults,
                alterationVinPutTestResults,
                alterationTestResultIdPut,
                alterationPutTestStartTimestamp,
                alterationPutTestEndTimestamp,
                alterationPutTestTypeStartTimestamp,
                alterationPutTestTypeEndTimestamp,
                alterationTestExpiryDate,
                alterationNumberOfSeatBeltsFitted,
                alterationSeatBeltInstallationCheckDate,
                alterationLastSeatBeltInstallationCheckDate,
                alterationTestAnniversaryDate,
                alterationModType,
                alterationParticulateTrapSerialNumber,
                alterationSmokeTestKLimitApplied,
                alterationEmissionStandard,
                alterationModificationTypeUsed,
                alterationParticulateTrapFitted,
                alterationFuelType,
                alterationProhibitionIssued
        ));

        testResultsSteps.putTestResultsWithAlterations(randomSystemNumber,putRequestBody,alterationsPutTestResults);
        testResultsSteps.statusCodeShouldBe(HttpStatus.SC_OK);
    }

    @WithTag("Vtm")
    @Title("CVSB-10300 - AC1 PUT: Attempt to update a test record with a not applicable field - TestTypeId = 101")
    @Test
    public void testResultsPutApplicableFieldsPaidRwRetest() {
        // Read the base test result JSON.
        String postRequestBody = GenericData.readJsonValueFromFile("technical-records_trl_all_fields.json","$");

        // Create alteration to add one more tech record to in the request body
        String randomSystemNumber = GenericData.generateRandomSystemNumber();
        String randomVin = GenericData.generateRandomVin();
        JsonPathAlteration alterationSystemNumberVehicle = new JsonPathAlteration("$.systemNumber", randomSystemNumber, "", "REPLACE");
        JsonPathAlteration alterationVinVehicle = new JsonPathAlteration("$.vin", randomVin,"","REPLACE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterationsVehicle = new ArrayList<>(Arrays.asList(alterationSystemNumberVehicle,
                alterationVinVehicle));

        // Post the results, together with any alterations, and verify that they are accepted.
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBody, alterationsVehicle);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(HttpStatus.SC_CREATED);
        vehicleTechnicalRecordsSteps.validateData("Technical Record created");

        String testResultRecord = GenericData.readJsonValueFromFile("test-results_post_payload_trl_10300.json", "$");

        // Create alteration to add one more tech record to in the request body

        String randomTestResultId = UUID.randomUUID().toString();
        JsonPathAlteration alterationTestTypeId = new JsonPathAlteration("$.testTypes[0].testTypeId", "101", "", "REPLACE");

        JsonPathAlteration alterationSystemNumberTestResults = new JsonPathAlteration("$.systemNumber", randomSystemNumber, "", "REPLACE");
        JsonPathAlteration alterationVinTestResults = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationTestResultIdPost = new JsonPathAlteration("$.testResultId", randomTestResultId, "", "REPLACE");
        JsonPathAlteration alterationTestStartTimestamp = new JsonPathAlteration("$.testStartTimestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 1)), "", "REPLACE");
        JsonPathAlteration alterationTestEndTimestamp = new JsonPathAlteration("$.testEndTimestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 4)), "", "REPLACE");
        JsonPathAlteration alterationTestTypeStartTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeStartTimestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 2)), "", "REPLACE");
        JsonPathAlteration alterationTestTypeEndTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeEndTimestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 3)), "", "REPLACE");


        // Collate the list of alterations.
        List<JsonPathAlteration> alterationsTestResults = new ArrayList<>(Arrays.asList(
                alterationTestTypeId,
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


        // Read the base JSON for PUT test-results
        String putRequestBody = GenericData.readJsonValueFromFile("test-results_put_payload_trl_10300.json","$");

        JsonPathAlteration alterationTestTypeID = new JsonPathAlteration("$.testResult.testTypes[0].testTypeId", "101", "", "REPLACE");
        JsonPathAlteration alterationSystemNumberPutTestResults = new JsonPathAlteration("$.testResult.systemNumber", randomSystemNumber, "", "REPLACE");
        JsonPathAlteration alterationVinPutTestResults = new JsonPathAlteration("$.testResult.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationTestResultIdPut = new JsonPathAlteration("$.testResult.testResultId", randomTestResultId, "", "REPLACE");
        JsonPathAlteration alterationPutTestStartTimestamp = new JsonPathAlteration("$.testResult.testStartTimestamp",new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 1)) , "", "REPLACE");
        JsonPathAlteration alterationPutTestEndTimestamp = new JsonPathAlteration("$.testResult.testEndTimestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 4)), "", "REPLACE");
        JsonPathAlteration alterationPutTestTypeStartTimestamp = new JsonPathAlteration("$.testResult.testTypes[0].testTypeStartTimestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 2)), "", "REPLACE");
        JsonPathAlteration alterationPutTestTypeEndTimestamp = new JsonPathAlteration("$.testResult.testTypes[0].testTypeEndTimestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 3)), "", "REPLACE");


        JsonPathAlteration alterationTestExpiryDate = new JsonPathAlteration("$.testResult.testTypes[0].testExpiryDate", "", "", "DELETE");
        JsonPathAlteration alterationTestAnniversaryDate = new JsonPathAlteration("$.testResult.testTypes[0].testAnniversaryDate", "", "", "DELETE");
        JsonPathAlteration alterationModType = new JsonPathAlteration("$.testResult.testTypes[0].modType", "", "", "DELETE");
        JsonPathAlteration alterationParticulateTrapSerialNumber = new JsonPathAlteration("$.testResult.testTypes[0].particulateTrapSerialNumber", "", "", "DELETE");
        JsonPathAlteration alterationSmokeTestKLimitApplied = new JsonPathAlteration("$.testResult.testTypes[0].smokeTestKLimitApplied", "", "", "DELETE");
        JsonPathAlteration alterationEmissionStandard = new JsonPathAlteration("$.testResult.testTypes[0].emissionStandard", "", "", "DELETE");
        JsonPathAlteration alterationModificationTypeUsed = new JsonPathAlteration("$.testResult.testTypes[0].modificationTypeUsed", "", "", "DELETE");
        JsonPathAlteration alterationParticulateTrapFitted = new JsonPathAlteration("$.testResult.testTypes[0].particulateTrapFitted", "", "", "DELETE");
        JsonPathAlteration alterationFuelType = new JsonPathAlteration("$.testResult.testTypes[0].fuelType", "", "", "DELETE");
        JsonPathAlteration alterationNumberOfSeatBeltsFitted = new JsonPathAlteration("$.testResult.testTypes[0].numberOfSeatbeltsFitted", "", "", "DELETE");
        JsonPathAlteration alterationSeatBeltInstallationCheckDate = new JsonPathAlteration("$.testResult.testTypes[0].seatbeltInstallationCheckDate", "", "", "DELETE");
        JsonPathAlteration alterationLastSeatBeltInstallationCheckDate = new JsonPathAlteration("$.testResult.testTypes[0].lastSeatbeltInstallationCheckDate", "", "", "DELETE");
        JsonPathAlteration alterationProhibitionIssued = new JsonPathAlteration("$.testResult.testTypes[0].prohibitionIssued", "", "", "DELETE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterationsPutTestResults = new ArrayList<>(Arrays.asList(
                alterationTestTypeID,
                alterationSystemNumberPutTestResults,
                alterationVinPutTestResults,
                alterationTestResultIdPut,
                alterationPutTestStartTimestamp,
                alterationPutTestEndTimestamp,
                alterationPutTestTypeStartTimestamp,
                alterationPutTestTypeEndTimestamp,
                alterationTestExpiryDate,
                alterationNumberOfSeatBeltsFitted,
                alterationSeatBeltInstallationCheckDate,
                alterationLastSeatBeltInstallationCheckDate,
                alterationTestAnniversaryDate,
                alterationModType,
                alterationParticulateTrapSerialNumber,
                alterationSmokeTestKLimitApplied,
                alterationEmissionStandard,
                alterationModificationTypeUsed,
                alterationParticulateTrapFitted,
                alterationFuelType,
                alterationProhibitionIssued
        ));

        testResultsSteps.putTestResultsWithAlterations(randomSystemNumber,putRequestBody,alterationsPutTestResults);
        testResultsSteps.statusCodeShouldBe(HttpStatus.SC_OK);
    }

    @WithTag("Vtm")
    @Title("CVSB-10280 - AC8: Backend Service Correctly Interprets The 'testVersion' value of 'all'" +
            "AC7: PUT request: Original Test Record is updated and attributes are automatically set - Current and Archived" +
            "AC2: Update the GET request in testResults API with testVersion attribute" + "AC3: Update the GET request in testResults API with a 'version' query parameter" +
            "AC5: GET request returns all 'current' test records + AC6: GET request returns all \"archived\" test records ")
    @Test
    public void testResultsPutCurrentAndArchived() {
        // Read the base test result JSON.
        String postRequestBody = GenericData.readJsonValueFromFile("technical-records_hgv_all_fields.json","$");

        // Create alteration to add one more tech record to in the request body
        String randomSystemNumber = GenericData.generateRandomSystemNumber();
        String randomVin = GenericData.generateRandomVin();
        JsonPathAlteration alterationSystemNumberVehicle = new JsonPathAlteration("$.systemNumber", randomSystemNumber, "", "REPLACE");
        JsonPathAlteration alterationVinVehicle = new JsonPathAlteration("$.vin", randomVin,"","REPLACE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterationsVehicle = new ArrayList<>(Arrays.asList(alterationSystemNumberVehicle, alterationVinVehicle));

        // Post the results, together with any alterations, and verify that they are accepted.
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBody, alterationsVehicle);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(201);
        vehicleTechnicalRecordsSteps.validateData("Technical Record created");

        String testResultRecord = GenericData.readJsonValueFromFile("test-results_sys_number_10280.json", "$");

        // Create alteration to add one more tech record to in the request body

        String randomTestResultId = UUID.randomUUID().toString();
        JsonPathAlteration alterationSystemNumberTestResults = new JsonPathAlteration("$.systemNumber", randomSystemNumber, "", "REPLACE");
        JsonPathAlteration alterationVinTestResults = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationTestResultIdPost = new JsonPathAlteration("$.testResultId", randomTestResultId, "", "REPLACE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterationsTestResults = new ArrayList<>(Arrays.asList(
                alterationSystemNumberTestResults,
                alterationVinTestResults,
                alterationTestResultIdPost));

        // Post the results, together with any alterations, and verify that they are accepted.
        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, alterationsTestResults);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");


        // Read the base JSON for PUT test-results
        String putRequestBody = GenericData.readJsonValueFromFile("test-results_put_payload_10280.json","$");

        JsonPathAlteration alterationSystemNumberPutTestResults = new JsonPathAlteration("$.testResult.systemNumber", randomSystemNumber, "", "REPLACE");
        JsonPathAlteration alterationVinPutTestResults = new JsonPathAlteration("$.testResult.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationTestResultIdPut = new JsonPathAlteration("$.testResult.testResultId", randomTestResultId, "", "REPLACE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterationsPutTestResults = new ArrayList<>(Arrays.asList(
                alterationSystemNumberPutTestResults,
                alterationVinPutTestResults,
                alterationTestResultIdPut));

        testResultsSteps.putTestResultsWithAlterations(randomSystemNumber,putRequestBody,alterationsPutTestResults);
        testResultsSteps.statusCodeShouldBe(200);

        //AC7 , AC3
        //AC5
        testResultsSteps.getTestResults(randomSystemNumber);
        testResultsSteps.statusCodeShouldBe(200);
        testResultsSteps.valueForFieldInPathShouldBe("[0].testVersion","current");
        testResultsSteps.valueForFieldInPathShouldBe("size", 1);
        testResultsSteps.fieldInPathShouldNotExist("[0]","testHistory");

        testResultsSteps.getTestResults(randomSystemNumber);
        testResultsSteps.statusCodeShouldBe(200);
        testResultsSteps.valueForFieldInPathShouldNotBe("[0].testVersion","archived");
        testResultsSteps.fieldInPathShouldNotExist("[0]","testHistory");

        // AC8
        testResultsSteps.getTestResults(randomSystemNumber, TestVersion.ALL ,randomTestResultId);
        testResultsSteps.valueForFieldInPathShouldBe("[0].testVersion","current");
        testResultsSteps.valueForFieldInPathShouldBe("[0].testHistory[0].testVersion","archived");

        // AC2
        // AC6
        testResultsSteps.getTestResults(randomSystemNumber, TestVersion.ARCHIVED ,randomTestResultId);
        testResultsSteps.statusCodeShouldBe(200);
        testResultsSteps.valueForFieldInPathShouldBe("[0].testVersion","archived");
        testResultsSteps.valueForFieldInPathShouldNotBe("[0].testVersion","current");
        testResultsSteps.fieldInPathShouldNotExist("[0]","testHistory");

        testResultsSteps.getTestResults(randomSystemNumber, TestVersion.CURRENT ,randomTestResultId);
        testResultsSteps.statusCodeShouldBe(200);
        testResultsSteps.valueForFieldInPathShouldBe("[0].testVersion","current");
        testResultsSteps.fieldInPathShouldNotExist("[0]","testHistory");
    }
}

