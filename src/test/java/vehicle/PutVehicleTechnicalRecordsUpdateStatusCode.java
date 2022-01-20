package vehicle;

import data.GenericData;
import model.vehicles.VehicleTechnicalRecordSearchCriteria;
import model.vehicles.VehicleTechnicalRecordStatus;
import model.vehicles.OldStatusCode;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import net.thucydides.core.annotations.WithTag;
import org.apache.http.HttpStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import steps.TestResultsSteps;
import steps.VehicleTechnicalRecordsSteps;
import util.JsonPathAlteration;
import java.text.SimpleDateFormat;
import java.util.*;


@RunWith(SerenityRunner.class)
public class PutVehicleTechnicalRecordsUpdateStatusCode {

    @Steps
    VehicleTechnicalRecordsSteps vehicleTechnicalRecordsSteps;

    @Steps
    TestResultsSteps testResultsSteps;

    Date date  = new Date();

    @WithTag("Vtm")
    @Title("CVSB-10195 - AC1: Change current or provisional status to archived - Provisional - Archived")
    @Test
    public void testPutVehicleTechRecordStatusCodeProvToArchive(){

        // POST tech-record,read the base tech-record JSON.
        String postRequestBody = GenericData.readJsonValueFromFile("technical-records_hgv_all_fields.json","$");

        // Create alteration to edit one or more fields in the request body
        String randomVin = GenericData.generateRandomVin();
        JsonPathAlteration alterationVinVehicle = new JsonPathAlteration("$.vin", randomVin,"","REPLACE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterationsVehicle = new ArrayList<>(Arrays.asList(alterationVinVehicle));

        // Post the tech-record, together with any alterations, and verify that they are accepted.
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBody, alterationsVehicle);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(201);
        vehicleTechnicalRecordsSteps.validateData("Technical Record created");

        //GET tech-records
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsBySearchCriteria(randomVin, VehicleTechnicalRecordSearchCriteria.VIN);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].statusCode", "provisional");


        String systemNumber = vehicleTechnicalRecordsSteps.getSystemNumber();

        String createdAt = vehicleTechnicalRecordsSteps.getCreatedAt();

        // PUT tech-record,read the base tech-record JSON.
        String putRequestBody = GenericData.readJsonValueFromFile("technical-records_put_archive_payload_10915.json","$");

        // Create alteration to edit one or more fields in the request body
        JsonPathAlteration alterationPutSystemNumber = new JsonPathAlteration("$.systemNumber", systemNumber, "", "REPLACE");
        JsonPathAlteration alterationPutVin = new JsonPathAlteration("$.vin", randomVin,"","REPLACE");
        JsonPathAlteration alterationCreatedAt = new JsonPathAlteration("$.techRecord[0].createdAt", createdAt,"","REPLACE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterationsPutVehicle = new ArrayList<>(Arrays.asList(alterationPutSystemNumber, alterationPutVin,alterationCreatedAt));

        // PUT the tech-record, together with any alterations, and verify that they are accepted.
        vehicleTechnicalRecordsSteps.putVehicleTechnicalRecordsForArchivedWithAlterations(systemNumber,putRequestBody,alterationsPutVehicle);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord.size()", 1);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[0].statusCode", "archived");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[0].lastUpdatedByName", "Dec");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[0].lastUpdatedById", "12345");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldContains("techRecord[0].lastUpdatedAt", new SimpleDateFormat("yyyy-MM-dd").format(date));
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldContains("techRecord[0].updateType", "techRecordUpdate");

    }

    @WithTag("Vtm")
    @Title("CVSB-10195 - AC1: Change current or provisional status to archived - Current - Archived")
    @Test
    public void testPutVehicleTechRecordStatusCodeCurrentToArchive(){

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
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].statusCode", "provisional");

        String systemNumber = vehicleTechnicalRecordsSteps.getSystemNumber();

        // read base JSON for POST test-results
        String testResultRecord = GenericData.readJsonValueFromFile("test-results_first_test_payload_10915.json","$");

        String randomTestResultId = UUID.randomUUID().toString();
        JsonPathAlteration alterationSystemNumberTestResults = new JsonPathAlteration("$.systemNumber", systemNumber, "", "REPLACE");
        JsonPathAlteration alterationVinTestResults = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationTestResultIdPost = new JsonPathAlteration("$.testResultId", randomTestResultId, "", "REPLACE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterationsTestResults = new ArrayList<>(Arrays.asList(
                alterationSystemNumberTestResults,
                alterationVinTestResults,
                alterationTestResultIdPost));

        // Post the test-results, together with any alterations, and verify that they are accepted.
        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, alterationsTestResults);
        testResultsSteps.statusCodeShouldBe(HttpStatus.SC_CREATED);
        testResultsSteps.validateData("Test records created");

        // wait until the tech-record is updated
        vehicleTechnicalRecordsSteps.waitForVehicleTechRecordsToBeUpdated(randomVin, 20);

        // GET test - results to validate the expected response
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsByStatusAndSearchCriteria(randomVin,VehicleTechnicalRecordStatus.ALL,VehicleTechnicalRecordSearchCriteria.VIN);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(HttpStatus.SC_OK);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].statusCode", "archived");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[1].statusCode", "current");

        // GET test - results to validate the expected response
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsBySearchCriteria(randomVin, VehicleTechnicalRecordSearchCriteria.VIN);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(HttpStatus.SC_OK);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].statusCode", "current");

        String createdAt = vehicleTechnicalRecordsSteps.getCreatedAt();

        // PUT tech-record,read the base tech-record JSON.
        String putRequestBody = GenericData.readJsonValueFromFile("technical-records_put_payload_archive_10915.json","$");

        // Create alteration to edit one or more fields in the request body
        JsonPathAlteration alterationPutSystemNumber = new JsonPathAlteration("$.systemNumber", systemNumber, "", "REPLACE");
        JsonPathAlteration alterationPutVin = new JsonPathAlteration("$.vin", randomVin,"","REPLACE");
        JsonPathAlteration alterationCreatedAt = new JsonPathAlteration("$.techRecord[0].createdAt", createdAt,"","REPLACE");
        JsonPathAlteration alterationStatusCode = new JsonPathAlteration("$.techRecord[0].statusCode", "current","","REPLACE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterationsPutVehicle = new ArrayList<>(Arrays.asList(alterationPutSystemNumber,
                alterationPutVin,
                alterationCreatedAt,
                alterationStatusCode));

        // PUT the tech-record, together with any alterations, and verify that they are accepted.
        vehicleTechnicalRecordsSteps.putVehicleTechnicalRecordsForArchivedWithAlterations(systemNumber,putRequestBody,alterationsPutVehicle);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord.size()", 2);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[0].statusCode", "archived");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[1].statusCode", "archived");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[1].lastUpdatedByName", "Dec");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[1].lastUpdatedById", "12345");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldContains("techRecord[1].lastUpdatedAt", new SimpleDateFormat("yyyy-MM-dd").format(date));
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldContains("techRecord[1].updateType", "techRecordUpdate");

    }

    @WithTag("Vtm")
    @Title("CVSB-10195 - AC2: Add a provisional tech record, to a vehicle - Archived - Add prov")
    @Test
    public void testPostVehicleTechRecordToAddProvisional() {

        // POST tech-record,read the base tech-record JSON.
        String postRequestBody = GenericData.readJsonValueFromFile("technical-records_hgv_all_fields.json","$");

        // Create alteration to edit one or more fields in the request body
        String randomVin = GenericData.generateRandomVin();
        JsonPathAlteration alterationVinVehicle = new JsonPathAlteration("$.vin", randomVin,"","REPLACE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterationsVehicle = new ArrayList<>(Arrays.asList(alterationVinVehicle));

        // Post the tech-record, together with any alterations, and verify that they are accepted.
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBody, alterationsVehicle);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(201);
        vehicleTechnicalRecordsSteps.validateData("Technical Record created");

        //GET tech-records
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsBySearchCriteria(randomVin, VehicleTechnicalRecordSearchCriteria.VIN);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].statusCode", "provisional");
        String systemNumber = vehicleTechnicalRecordsSteps.getSystemNumber();
        String createdAt = vehicleTechnicalRecordsSteps.getCreatedAt();

        // PUT tech-record,read the base tech-record JSON.
        String putRequestBody = GenericData.readJsonValueFromFile("technical-records_put_archive_payload_10915.json","$");

        // Create alteration to edit one or more fields in the request body
        JsonPathAlteration alterationPutSystemNumber = new JsonPathAlteration("$.systemNumber", systemNumber, "", "REPLACE");
        JsonPathAlteration alterationPutVin = new JsonPathAlteration("$.vin", randomVin,"","REPLACE");
        JsonPathAlteration alterationCreatedAt = new JsonPathAlteration("$.techRecord[0].createdAt", createdAt,"","REPLACE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterationsPutVehicle = new ArrayList<>(Arrays.asList(alterationPutSystemNumber, alterationPutVin,alterationCreatedAt));

        // PUT the tech-record, together with any alterations, and verify that they are accepted.
        vehicleTechnicalRecordsSteps.putVehicleTechnicalRecordsForArchivedWithAlterations(systemNumber,putRequestBody,alterationsPutVehicle);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord.size()", 1);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[0].statusCode", "archived");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[0].lastUpdatedByName", "Dec");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[0].lastUpdatedById", "12345");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldContains("techRecord[0].lastUpdatedAt", new SimpleDateFormat("yyyy-MM-dd").format(date));
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldContains("techRecord[0].updateType", "techRecordUpdate");

        //GET tech-records
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsByStatusAndSearchCriteria(randomVin,VehicleTechnicalRecordStatus.ALL,VehicleTechnicalRecordSearchCriteria.VIN);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        String createdAt2 = vehicleTechnicalRecordsSteps.getCreatedAt();

        // POST tech-records to add a tech-record with statusCode as provisional
        String postProvBody = GenericData.readJsonValueFromFile("technical-records_post_prov_payload_10915.json","$");

        // Create alteration to edit a field in the request body
        JsonPathAlteration alterationCreatedAt2 = new JsonPathAlteration("$.techRecord[0].createdAt", createdAt2,"","REPLACE");

        //Collate the alterations
        List<JsonPathAlteration> alterationsPostVehicle = new ArrayList<>(Arrays.asList(alterationCreatedAt2));

        // POST tech-records to add a provisional tech-record
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsForProvisionalWithAlterations(systemNumber,postProvBody,alterationsPostVehicle);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord.size()", 2);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[0].statusCode", "archived");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[1].statusCode", "provisional");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[1].createdByName", "deep");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[1].createdById", "09876");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldContains("techRecord[1].createdAt", new SimpleDateFormat("yyyy-MM-dd").format(date));
    }

    @WithTag("Vtm")
    @Title("CVSB-10195 - AC2: Add a provisional tech record, to a vehicle - Prov - add Prov")
    @Test
    public void testPostVehicleTechRecordToNotAddProvProvisional() {

        // POST tech-record,read the base tech-record JSON.
        String postRequestBody = GenericData.readJsonValueFromFile("technical-records_hgv_all_fields.json", "$");

        // Create alteration to edit one or more fields in the request body
        String randomVin = GenericData.generateRandomVin();
        JsonPathAlteration alterationVinVehicle = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterationsVehicle = new ArrayList<>(Arrays.asList(alterationVinVehicle));

        // Post the tech-record, together with any alterations, and verify that they are accepted.
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBody, alterationsVehicle);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(201);
        vehicleTechnicalRecordsSteps.validateData("Technical Record created");

        //GET tech-records
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsBySearchCriteria(randomVin, VehicleTechnicalRecordSearchCriteria.VIN);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].statusCode", "provisional");

        //GET tech-records
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsByStatusAndSearchCriteria(randomVin,VehicleTechnicalRecordStatus.ALL,VehicleTechnicalRecordSearchCriteria.VIN);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        String systemNumber = vehicleTechnicalRecordsSteps.getSystemNumber();
        String createdAt = vehicleTechnicalRecordsSteps.getCreatedAt();

        // POST tech-records to add a tech-record with statusCode as provisional
        String postProvBody = GenericData.readJsonValueFromFile("technical-records_post_prov_payload_10915.json","$");

        // Create alteration to edit a field in the request body
        JsonPathAlteration alterationCreatedAt2 = new JsonPathAlteration("$.techRecord[0].createdAt", createdAt,"","REPLACE");

        //Collate the alterations
        List<JsonPathAlteration> alterationsPostVehicle = new ArrayList<>(Arrays.asList(alterationCreatedAt2));

        // POST tech-records to add a provisional tech-record
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsForProvisionalWithAlterations(systemNumber,postProvBody,alterationsPostVehicle);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(400);
    }

    @WithTag("Vtm")
    @Title("CVSB-10195 - AC2: Add a provisional tech record, to a vehicle  - Current - Add Prov")
    @Test
    public void testPostVehicleTechRecordToAddCurrentProvisional() {

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
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].statusCode", "provisional");

        String systemNumber = vehicleTechnicalRecordsSteps.getSystemNumber();

        // read base JSON for POST test-results
        String testResultRecord = GenericData.readJsonValueFromFile("test-results_first_test_payload_10915.json", "$");

        //Create alteration to edit one more more field in the request body
        String randomTestResultId = UUID.randomUUID().toString();
        JsonPathAlteration alterationSystemNumberTestResults = new JsonPathAlteration("$.systemNumber", systemNumber, "", "REPLACE");
        JsonPathAlteration alterationVinTestResults = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationTestResultIdPost = new JsonPathAlteration("$.testResultId", randomTestResultId, "", "REPLACE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterationsTestResults = new ArrayList<>(Arrays.asList(
                alterationSystemNumberTestResults,
                alterationVinTestResults,
                alterationTestResultIdPost));

        // Post the test-results, together with any alterations, and verify that they are accepted.
        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, alterationsTestResults);
        testResultsSteps.statusCodeShouldBe(HttpStatus.SC_CREATED);
        testResultsSteps.validateData("Test records created");

        // wait until the tech-record is updated
        vehicleTechnicalRecordsSteps.waitForVehicleTechRecordsToBeUpdated(randomVin, 20);

        // GET test - results to validate the expected response
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsByStatusAndSearchCriteria(randomVin, VehicleTechnicalRecordStatus.ALL, VehicleTechnicalRecordSearchCriteria.VIN);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(HttpStatus.SC_OK);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].statusCode", "archived");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[1].statusCode", "current");

        String createdAt = vehicleTechnicalRecordsSteps.getCreatedAt();

        // POST tech-records to add a tech-record with statusCode as provisional
        String postProvBody = GenericData.readJsonValueFromFile("technical-records_post_prov_payload_10915.json","$");

        // Create alteration to edit a field in the request body
        JsonPathAlteration alterationCreatedAt2 = new JsonPathAlteration("$.techRecord[0].createdAt", createdAt,"","REPLACE");

        //Collate the alterations
        List<JsonPathAlteration> alterationsPostVehicle = new ArrayList<>(Arrays.asList(alterationCreatedAt2));

        // POST tech-records to add a provisional tech-record and verify the response
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsForProvisionalWithAlterations(systemNumber,postProvBody,alterationsPostVehicle);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord.size()", 3);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[0].statusCode", "archived");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[1].statusCode", "current");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[2].statusCode", "provisional");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[2].createdByName", "deep");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[2].createdById", "09876");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldContains("techRecord[2].createdAt", new SimpleDateFormat("yyyy-MM-dd").format(date));
    }

    @WithTag("Vtm")
    @Title("CVSB-10195 - AC3: Vehicle has a CURRENT tech record and a provisional tech record. " +
            "The provisional is updated to current, hence, the existing CURRENT, is updated to archived automatically")
    @Test
    public void testPutVehicleTechRecordMultipleStatusUpdate(){

        // POST tech-record,read the base tech-record JSON.
        String postRequestBody = GenericData.readJsonValueFromFile("technical-records_hgv_all_fields.json","$");

        // Create alteration to edit one or more fields in the request body
        String randomVin = GenericData.generateRandomVin();
        JsonPathAlteration alterationVinVehicle = new JsonPathAlteration("$.vin", randomVin,"","REPLACE");
        JsonPathAlteration alterationMake = new JsonPathAlteration("$.techRecord[0].make", "VW","","REPLACE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterationsVehicle = new ArrayList<>(Arrays.asList(alterationVinVehicle,alterationMake));

        // Post the tech-record, together with any alterations, and verify that they are accepted.
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBody, alterationsVehicle);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(201);
        vehicleTechnicalRecordsSteps.validateData("Technical Record created");

        //GET tech-records
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsBySearchCriteria(randomVin, VehicleTechnicalRecordSearchCriteria.VIN);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].statusCode", "provisional");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].make", "VW");

        String systemNumber = vehicleTechnicalRecordsSteps.getSystemNumber();
        String createdAt = vehicleTechnicalRecordsSteps.getCreatedAt();

        // Read base JSON for PUT tech-record
        String putRequestBody = GenericData.readJsonValueFromFile("technical-records_put_current_payload_10915.json","$");

        // Create alteration to edit a field in the request body
        JsonPathAlteration alterationCreatedAt = new JsonPathAlteration("$.techRecord[0].createdAt", createdAt,"","REPLACE");

        //Collate the alterations
        List<JsonPathAlteration> alterationsPutVehicle = new ArrayList<>(Arrays.asList(alterationCreatedAt));

        // PUT tech-record and verify the response
        vehicleTechnicalRecordsSteps.putVehicleTechnicalRecordsForCurrentWithAlterations(systemNumber, OldStatusCode.PROVISIONAL,putRequestBody,alterationsPutVehicle);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);

        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord.size()", 2);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[0].statusCode", "archived");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[0].make", "VW");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[0].lastUpdatedByName", "deep");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[0].lastUpdatedById", "12345");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldContains("techRecord[0].lastUpdatedAt", new SimpleDateFormat("yyyy-MM-dd").format(date));
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldContains("techRecord[0].updateType", "techRecordUpdate");

        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[1].statusCode", "current");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[1].createdByName", "deep");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[1].createdById", "12345");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldContains("techRecord[1].createdAt", new SimpleDateFormat("yyyy-MM-dd").format(date));

        // POST tech-records to add a tech-record with statusCode as provisional
        String postProvBody = GenericData.readJsonValueFromFile("technical-records_post_prov_payload_10915.json","$");

        // Create alteration to edit a field in the request body
        JsonPathAlteration alterationCreatedAt2 = new JsonPathAlteration("$.techRecord[0].createdAt", createdAt,"","REPLACE");
        JsonPathAlteration alterationModel = new JsonPathAlteration("$.techRecord[0].model", "F15-55","","REPLACE");

        //Collate the alterations
        List<JsonPathAlteration> alterationsPostVehicle = new ArrayList<>(Arrays.asList(alterationCreatedAt2,alterationModel));

        // POST tech-records to add a provisional tech-record and verify the response
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsForProvisionalWithAlterations(systemNumber,postProvBody,alterationsPostVehicle);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);

        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord.size()", 3);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[0].statusCode", "archived");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[0].make", "VW");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[1].statusCode", "current");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[1].model", "F12-33");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[2].statusCode", "provisional");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[2].model", "F15-55");

        // Create alteration to edit a field in the request body
        JsonPathAlteration alterationMake2 = new JsonPathAlteration("$.techRecord[0].make", "MERCEDES","","REPLACE");

        //Collate the alterations
        List<JsonPathAlteration> alterationsPutVehicle2 = new ArrayList<>(Arrays.asList(alterationCreatedAt,alterationMake2));

        //PUT tech-records and verify the response
        vehicleTechnicalRecordsSteps.putVehicleTechnicalRecordsForCurrentWithAlterations(systemNumber, OldStatusCode.PROVISIONAL,putRequestBody,alterationsPutVehicle2);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);

        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord.size()", 4);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[0].statusCode", "archived");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[0].make", "VW");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[0].lastUpdatedByName", "deep");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[0].lastUpdatedById", "12345");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldContains("techRecord[0].lastUpdatedAt", new SimpleDateFormat("yyyy-MM-dd").format(date));
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldContains("techRecord[0].updateType", "techRecordUpdate");

        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[1].statusCode", "archived");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[1].model", "F12-33");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[0].lastUpdatedByName", "deep");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[0].lastUpdatedById", "12345");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldContains("techRecord[0].lastUpdatedAt", new SimpleDateFormat("yyyy-MM-dd").format(date));
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldContains("techRecord[0].updateType", "techRecordUpdate");

        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[2].statusCode", "archived");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[2].model", "F15-55");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[0].lastUpdatedByName", "deep");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[0].lastUpdatedById", "12345");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldContains("techRecord[0].lastUpdatedAt", new SimpleDateFormat("yyyy-MM-dd").format(date));
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldContains("techRecord[0].updateType", "techRecordUpdate");

        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[3].statusCode", "current");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[3].make", "MERCEDES");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[1].createdByName", "deep");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[1].createdById", "12345");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldContains("techRecord[1].createdAt", new SimpleDateFormat("yyyy-MM-dd").format(date));
 }

    @WithTag("Vtm")
    @Title("CVSB-10195 - AC4: Vehicle DOES NOT have a CURRENT tech record, but it DOES have a provisional tech record. " +
            "The provisional is updated to current.")
    @Test
    public void testPutVehicleTechRecordUpdateProvToCurrent(){

        // POST tech-record,read the base tech-record JSON.
        String postRequestBody = GenericData.readJsonValueFromFile("technical-records_hgv_all_fields.json","$");

        // Create alteration to edit one or more fields in the request body
        String randomVin = GenericData.generateRandomVin();
        JsonPathAlteration alterationVinVehicle = new JsonPathAlteration("$.vin", randomVin,"","REPLACE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterationsVehicle = new ArrayList<>(Arrays.asList(alterationVinVehicle));

        // Post the tech-record, together with any alterations, and verify that they are accepted.
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBody, alterationsVehicle);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(201);
        vehicleTechnicalRecordsSteps.validateData("Technical Record created");

        //GET tech-records
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsBySearchCriteria(randomVin, VehicleTechnicalRecordSearchCriteria.VIN);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].statusCode", "provisional");
        String systemNumber = vehicleTechnicalRecordsSteps.getSystemNumber();
        String createdAt = vehicleTechnicalRecordsSteps.getCreatedAt();

        // Read the base JSON for PUT tech-record
        String putRequestBody = GenericData.readJsonValueFromFile("technical-records_put_current_payload_10915.json","$");

        // Create alteration to edit a field in the request body
        JsonPathAlteration alterationCreatedAt = new JsonPathAlteration("$.techRecord[0].createdAt", createdAt,"","REPLACE");

        //Collate the alterations
        List<JsonPathAlteration> alterationsPutVehicle = new ArrayList<>(Arrays.asList(alterationCreatedAt));

        //PUT tech-record and verify the response
        vehicleTechnicalRecordsSteps.putVehicleTechnicalRecordsForCurrentWithAlterations(systemNumber, OldStatusCode.PROVISIONAL,putRequestBody,alterationsPutVehicle);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);

        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord.size()", 2);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[0].statusCode", "archived");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[0].lastUpdatedByName", "deep");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[0].lastUpdatedById", "12345");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldContains("techRecord[0].lastUpdatedAt", new SimpleDateFormat("yyyy-MM-dd").format(date));
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldContains("techRecord[0].updateType", "techRecordUpdate");

        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[1].statusCode", "current");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[1].createdByName", "deep");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[1].createdById", "12345");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldContains("techRecord[1].createdAt", new SimpleDateFormat("yyyy-MM-dd").format(date));
    }

    @WithTag("Vtm")
    @Title("CVSB-10195 - AC4: Vehicle DOES NOT have a CURRENT tech record, but it DOES have a provisional tech record. " +
            "The provisional is updated to current.- Multiple current not allowed")
    @Test
    public void testPutVehicleTechRecordToNotAddMoreCurrent(){

        // POST tech-record,read the base tech-record JSON.
        String postRequestBody = GenericData.readJsonValueFromFile("technical-records_hgv_all_fields.json","$");

        // Create alteration to edit one or more fields in the request body
        String randomVin = GenericData.generateRandomVin();
        JsonPathAlteration alterationVinVehicle = new JsonPathAlteration("$.vin", randomVin,"","REPLACE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterationsVehicle = new ArrayList<>(Arrays.asList(alterationVinVehicle));

        // Post the tech-record, together with any alterations, and verify that they are accepted.
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBody, alterationsVehicle);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(201);
        vehicleTechnicalRecordsSteps.validateData("Technical Record created");

        //GET tech-records
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsBySearchCriteria(randomVin, VehicleTechnicalRecordSearchCriteria.VIN);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].statusCode", "provisional");
        String systemNumber = vehicleTechnicalRecordsSteps.getSystemNumber();
        String createdAt = vehicleTechnicalRecordsSteps.getCreatedAt();

        // Read the base JSON for PUT tech-record
        String putRequestBody = GenericData.readJsonValueFromFile("technical-records_put_current_payload_10915.json","$");

        // Create alteration to edit a field in the request body
        JsonPathAlteration alterationCreatedAt = new JsonPathAlteration("$.techRecord[0].createdAt", createdAt,"","REPLACE");

        //Collate the alterations
        List<JsonPathAlteration> alterationsPutVehicle = new ArrayList<>(Arrays.asList(alterationCreatedAt));

        //PUT tech-record and verify the response
        vehicleTechnicalRecordsSteps.putVehicleTechnicalRecordsForCurrentWithAlterations(systemNumber, OldStatusCode.PROVISIONAL,putRequestBody,alterationsPutVehicle);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);

        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord.size()", 2);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[0].statusCode", "archived");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[0].lastUpdatedByName", "deep");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[0].lastUpdatedById", "12345");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldContains("techRecord[0].lastUpdatedAt", new SimpleDateFormat("yyyy-MM-dd").format(date));
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldContains("techRecord[0].updateType", "techRecordUpdate");

        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[1].statusCode", "current");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[1].createdByName", "deep");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[1].createdById", "12345");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldContains("techRecord[1].createdAt", new SimpleDateFormat("yyyy-MM-dd").format(date));

        // Read the base JSON for PUT tech-record
        String putFieldRequestBody = GenericData.readJsonValueFromFile("technical-records_put_current_payload_10915.json","$");

        // Create alteration to edit a field in the request body
        JsonPathAlteration alterationMake = new JsonPathAlteration("$.techRecord[0].make", "MERCEDES","","REPLACE");

        //Collate the alterations
        List<JsonPathAlteration> alterationsPutFieldVehicle = new ArrayList<>(Arrays.asList(alterationMake));

        //PUT tech-record and verify the response
        vehicleTechnicalRecordsSteps.putVehicleTechnicalRecordsForVehicleWithAlterations(systemNumber,putFieldRequestBody,alterationsPutFieldVehicle);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord.size()", 3);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[0].statusCode", "archived");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[0].lastUpdatedByName", "deep");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[0].lastUpdatedById", "12345");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldContains("techRecord[0].lastUpdatedAt", new SimpleDateFormat("yyyy-MM-dd").format(date));
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldContains("techRecord[0].updateType", "techRecordUpdate");

        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[1].statusCode", "archived");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[0].lastUpdatedByName", "deep");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[0].lastUpdatedById", "12345");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldContains("techRecord[0].lastUpdatedAt", new SimpleDateFormat("yyyy-MM-dd").format(date));
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldContains("techRecord[0].updateType", "techRecordUpdate");

        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[2].statusCode", "current");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[2].make", "MERCEDES");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[1].createdByName", "deep");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[1].createdById", "12345");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldContains("techRecord[1].createdAt", new SimpleDateFormat("yyyy-MM-dd").format(date));
    }
}
