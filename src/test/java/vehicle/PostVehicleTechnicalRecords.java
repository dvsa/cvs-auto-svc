package vehicle;


import data.GenericData;
import model.vehicles.VehicleTechnicalRecordSearchCriteria;
import model.vehicles.VehicleTechnicalRecordStatus;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import net.thucydides.core.annotations.WithTag;
import org.apache.http.HttpStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import steps.*;
import util.JsonPathAlteration;
import steps.TestResultsSteps;

import java.text.SimpleDateFormat;
import java.util.*;

@RunWith(SerenityRunner.class)
public class PostVehicleTechnicalRecords {

    @Steps
    VehicleTechnicalRecordsSteps vehicleTechnicalRecordsSteps;

    @Steps
    TestResultsSteps testResultsSteps;

    Date date = new Date();

    @WithTag("Vtm")
    @Title("CVSB-7885 - AC1 - API Consumer creates a technical record for a vehicle with a specific vin " +
            "AC5 - Can create a new vehicle entry using a unique VIN and multiple entries in the `techRecord` array of the request body")
    @Test
    public void testCreateVehicleTechnicalRecord() {
        // TEST SETUP
        //generate random system number
        String randomSystemNumber = GenericData.generateRandomSystemNumber();
        //generate random Vin
        String randomVin = GenericData.generateRandomVin();
        //generate random Vrm
        String randomVrm = GenericData.generateRandomVrm();
        // read post request body from file
        String postRequestBody = GenericData.readJsonValueFromFile("technical-records_hgv_all_fields.json", "$");
        //  read another tech record entry from different file
        String additionalTechRecord = GenericData.readJsonValueFromFile("technical-records_archived.json", "$.techRecord[0]");
        // create alteration to add one more tech record to in the request body
        JsonPathAlteration alterationTechRecord = new JsonPathAlteration("$.techRecord", additionalTechRecord, "", "ADD_VALUE");
        // create alteration to change systemNumber in the request body with the random generated Vin
        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$.systemNumber", randomSystemNumber, "", "REPLACE");
        // create alteration to change Vin in the request body with the random generated Vin
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        // create alteration to change primary vrm in the request body with the random generated primary vrm
        JsonPathAlteration alterationVrm = new JsonPathAlteration("$.primaryVrm", randomVrm, "", "REPLACE");
        // initialize the alterations list with both declared alteration
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(alterationTechRecord, alterationVin, alterationVrm, alterationSystemNumber));

        // TEST
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBody, alterations);
        // validate AC1
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(201);
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsByStatus(randomVin, VehicleTechnicalRecordStatus.ALL);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].vin", randomVin);
        // validated AC5
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].statusCode", "provisional");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[1].statusCode", "archived");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord.size()", 2);
    }

    @WithTag("Vtm")
    @Title("CVSB-10209 - AC2 -  HGV vehicle is created, and the appropriate attributes are automatically set")
    @Test
    public void testCreateHgvWithOnlyMandatoryFields() {
        // TEST SETUP
        //generate random system number
        String randomSystemNumber = GenericData.generateRandomSystemNumber();
        //generate random Vin
        String randomVin = GenericData.generateRandomVin();
        //generate random Vrm
        String randomVrm = GenericData.generateRandomVrm();
        // read post request body from file
        String postRequestBody = GenericData.readJsonValueFromFile("technical-records_hgv_mandatory_fields.json", "$");
        // read the tech record from the file used for post request body
        String techRecord = GenericData.readJsonValueFromFile("technical-records_hgv_mandatory_fields.json", "$.techRecord[0]");
        // create alteration to change system number in the request body with the random generated Vin
        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$.systemNumber", randomSystemNumber, "", "REPLACE");
        // create alteration to change Vin in the request body with the random generated Vin
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        // create alteration to change primary vrm in the request body with the random generated primary vrm
        JsonPathAlteration alterationVrm = new JsonPathAlteration("$.primaryVrm", randomVrm, "", "REPLACE");
        // initialize the alterations list with both declared alteration
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(alterationVin, alterationVrm, alterationSystemNumber));

        // TEST
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBody, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(201);
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsByStatus(GenericData.getPartialVinFromVin(randomVin), VehicleTechnicalRecordStatus.ALL);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        // validate AC2
        vehicleTechnicalRecordsSteps.validateResponseContainsJson("[0].techRecord[0]", techRecord);
    }

    @WithTag("Vtm")
    @Title("CVSB-10213 - AC1 - Partial VIN is auto-populated when creating a new hgv " +
            "AC2 - Vehicle class code is auto-populated when creating a new hgv " +
            "AC3 - Body type code is auto-populated when creating a new hgv")
    @Test
    public void testCreateVehicleHgvAutoPopulateFields() {
        // TEST SETUP
        //generate random system number
        String randomSystemNumber = GenericData.generateRandomSystemNumber();
        //generate random Vin
        String randomVin = GenericData.generateRandomVin();
        //generate random Vrm
        String randomVrm = GenericData.generateRandomVrm();
        // read post request body from file
        String postRequestBody = GenericData.readJsonValueFromFile("technical-records_hgv_all_fields.json", "$");
        String vehicleClassDescription = GenericData.extractStringValueFromJsonString(postRequestBody, "$.techRecord[0].vehicleClass.description");
        String bodyTypeDescription = GenericData.extractStringValueFromJsonString(postRequestBody, "$.techRecord[0].bodyType.description");
        // create alteration to change system number in the request body with the random system number
        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$.systemNumber", randomSystemNumber, "", "REPLACE");
        // create alteration to change Vin in the request body with the random generated Vin
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        // create alteration to change primary vrm in the request body with the random generated primary vrm
        JsonPathAlteration alterationVrm = new JsonPathAlteration("$.primaryVrm", randomVrm, "", "REPLACE");
        // initialize the alterations list with both declared alteration
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(alterationVin, alterationVrm, alterationSystemNumber));

        // TEST
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBody, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(201);
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsByStatus(GenericData.getPartialVinFromVin(randomVin), VehicleTechnicalRecordStatus.ALL);
        // validate AC1
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        // validate AC2
        String vehicleClassCode = vehicleTechnicalRecordsSteps.getVehicleClassCodeFromDescription(vehicleClassDescription);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].vehicleClass.code", vehicleClassCode);
        // validate AC3
        String bodyTypeCode = vehicleTechnicalRecordsSteps.getBodyTypeCodeFromDescription(bodyTypeDescription);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].bodyType.code", bodyTypeCode);
    }

    @Title("CVSB-10752 - AC1 API Consumer retrieve the Vehicle Technical Records - Single Vehicle")
    @Test
    public void testTechnicalRecordForSingleVehicle() {

        // Read the base test result JSON.
        String postRequestBody = GenericData.readJsonValueFromFile("technical-records_hgv_all_fields.json", "$");

        // Create alteration to add one more tech record to in the request body
        String randomVin = GenericData.generateRandomVin();
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        String systemNumber = vehicleTechnicalRecordsSteps.getNextSystemNumberInSequence();

        // Collate the list of alterations.
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(alterationVin));

        // Post the results, together with any alterations, and verify that they are accepted.
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBody, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(201);
        vehicleTechnicalRecordsSteps.validateData("Technical Record created");

        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecords(randomVin);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord.size()", 1);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].systemNumber", systemNumber);
    }

    @Title("CVSB - 10752 - AC1 API Consumer retrieve the Vehicle Technical Records - Multiple Vehicles + AC3 Certificate generation")
    @Test
    public void testMultipleVehiclesTestResults() {

        // Read the base test record json
        String postRequestBody = GenericData.readJsonValueFromFile("technical-records_hgv_all_fields.json", "$");

        // Generate random System Number for first vehicle
        String randomSysNumVehicleOne = GenericData.generateRandomSystemNumber();

        // Generate random Vin
        String randomVin = GenericData.generateRandomVin();

        // Create alteration in the post request with System Number and Vin for first vehicle
        JsonPathAlteration altSysNumVehicleOne = new JsonPathAlteration("$.systemNumber", randomSysNumVehicleOne, "", "REPLACE");
        JsonPathAlteration altVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");

        // Collate the list of alterations.
        List<JsonPathAlteration> altVehicleOne = new ArrayList<>(Arrays.asList(altSysNumVehicleOne, altVin));

        // Post the tech record, together with alterations, and verify that they are accepted.
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBody, altVehicleOne);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(201);
        vehicleTechnicalRecordsSteps.validateData("Technical Record created");

        // Generate random System Number for second vehicle
        String randomSysNumVehicleTwo = GenericData.generateRandomSystemNumber();

        // Create alteration in the post request with System Number and Vin for second vehicle
        JsonPathAlteration altSysNumVehicleTwo = new JsonPathAlteration("$.systemNumber", randomSysNumVehicleTwo, "", "REPLACE");

        // Collate the list of alterations.
        List<JsonPathAlteration> altVehicleTwo = new ArrayList<>(Arrays.asList(altSysNumVehicleTwo, altVin));

        // Post the tech record, together with alterations, and verify that they are accepted.
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBody, altVehicleTwo);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(201);
        vehicleTechnicalRecordsSteps.validateData("Technical Record created");

        // Get the tech record , and verify the expected fields
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecords(randomVin);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord.size()", 2);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("systemNumber.size()", 2);
//
        // Read the base test result JSON.
        String testResultRecord = GenericData.readJsonValueFromFile("test-results_duplicate_chassis_10752.json", "$");

        // Generate random TestResultId for first vehicle
        String randomTestResultIdVehicleOne = UUID.randomUUID().toString();

        // Create alteration in the post request with SystemNumber, Vin and TestResultId
        JsonPathAlteration altTestResultSysNumVehicleOne = new JsonPathAlteration("$.systemNumber", randomSysNumVehicleOne, "", "REPLACE");
        JsonPathAlteration altTestResultVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationTestResultIdVehicleOne = new JsonPathAlteration("$.testResultId", randomTestResultIdVehicleOne, "", "REPLACE");

        // Collate the list of alterations.
        List<JsonPathAlteration> altTestResultsVehicleOne = new ArrayList<>(Arrays.asList(
                altTestResultSysNumVehicleOne,
                altTestResultVin,
                alterationTestResultIdVehicleOne));

        // Post the test result, verify expected response is retrieved
        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, altTestResultsVehicleOne);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");

        // Generate random TestResultId for second vehicle
        String randomTestResultIdVehicleTwo = UUID.randomUUID().toString();

        // Create alteration in the post request with SystemNumber, Vin and TestResultId
        JsonPathAlteration altTestResultSysNumVehicleTwo = new JsonPathAlteration("$.systemNumber", randomSysNumVehicleTwo, "", "REPLACE");
        JsonPathAlteration altTestResultVinVehicleTwo = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationTestResultIdVehicleTwo = new JsonPathAlteration("$.testResultId", randomTestResultIdVehicleTwo, "", "REPLACE");

        // Collate the list of alterations.
        List<JsonPathAlteration> altTestResultsVehicleTwo = new ArrayList<>(Arrays.asList(
                altTestResultSysNumVehicleTwo,
                altTestResultVinVehicleTwo,
                alterationTestResultIdVehicleTwo));

        // Post the test result, verify expected response is retrieved
        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, altTestResultsVehicleTwo);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
    }

    @WithTag("Vtm")
    @Title("CVSB-10239 - AC2 -  PSV vehicle is created, and the appropriate attributes are automatically set")
    @Test
    public void testCreatePsvWithOnlyMandatoryFields() {
        // TEST SETUP
        //generate random system number
        String randomSystemNumber = GenericData.generateRandomSystemNumber();
        //generate random Vin
        String randomVin = GenericData.generateRandomVin();
        //generate random Vrm
        String randomVrm = GenericData.generateRandomVrm();
        // read post request body from file
        String postRequestBody = GenericData.readJsonValueFromFile("technical-records_psv_mandatory_fields.json", "$");
        // read the tech record from the file used for post request body
        String techRecord = GenericData.readJsonValueFromFile("technical-records_psv_mandatory_fields.json", "$.techRecord[0]");
        // create alteration to change system number in the request body with the random generated Vin
        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$.systemNumber", randomSystemNumber, "", "REPLACE");
        // create alteration to change Vin in the request body with the random generated Vin
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        // create alteration to change primary vrm in the request body with the random generated primary vrm
        JsonPathAlteration alterationVrm = new JsonPathAlteration("$.primaryVrm", randomVrm, "", "REPLACE");
        // initialize the alterations list with both declared alteration
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(alterationVin, alterationVrm, alterationSystemNumber));

        // TEST
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBody, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(201);
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsByStatus(GenericData.getPartialVinFromVin(randomVin), VehicleTechnicalRecordStatus.ALL);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        // validate AC2
        vehicleTechnicalRecordsSteps.validateResponseContainsJson("[0].techRecord[0]", techRecord);
    }

    @WithTag("Vtm")
    @Title("CVSB-10243 - AC1 - Partial VIN is auto-populated when creating a new psv " +
            "AC2 - Vehicle class code is auto-populated when creating a new psv " +
            "AC3 - Body type code is auto-populated when creating a new psv " +
            "AC4 - Brake code fields are auto-populated when creating a new psv")
    @Test
    public void testCreateVehiclePsvAutoPopulateFields() {
        // TEST SETUP
        //generate random system number
        String randomSystemNumber = GenericData.generateRandomSystemNumber();
        //generate random Vin
        String randomVin = GenericData.generateRandomVin();
        //generate random Vrm
        String randomVrm = GenericData.generateRandomVrm();
        // read post request body from file
        String postRequestBody = GenericData.readJsonValueFromFile("technical-records_psv_all_fields.json", "$");
        String vehicleClassDescription = GenericData.extractStringValueFromJsonString(postRequestBody, "$.techRecord[0].vehicleClass.description");
        String bodyTypeDescription = GenericData.extractStringValueFromJsonString(postRequestBody, "$.techRecord[0].bodyType.description");
        String brakeCode = GenericData.extractStringValueFromJsonString(postRequestBody, "$.techRecord[0].brakes.brakeCode");
        // create alteration to change system number in the request body with the random system number
        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$.systemNumber", randomSystemNumber, "", "REPLACE");
        // create alteration to change Vin in the request body with the random generated Vin
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        // create alteration to change primary vrm in the request body with the random generated primary vrm
        JsonPathAlteration alterationVrm = new JsonPathAlteration("$.primaryVrm", randomVrm, "", "REPLACE");
        // initialize the alterations list with both declared alteration
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(alterationVin, alterationVrm, alterationSystemNumber));

        // TEST
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBody, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(201);
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsByStatus(GenericData.getPartialVinFromVin(randomVin), VehicleTechnicalRecordStatus.ALL);
        // validate AC1
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        // validate AC2
        String vehicleClassCode = vehicleTechnicalRecordsSteps.getVehicleClassCodeFromDescription(vehicleClassDescription);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].vehicleClass.code", vehicleClassCode);
        // validate AC3
        String bodyTypeCode = vehicleTechnicalRecordsSteps.getBodyTypeCodeFromDescription(bodyTypeDescription);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].bodyType.code", bodyTypeCode);
        // validated AC4
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].brakeCode", brakeCode);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].brakes.brakeCodeOriginal",
                brakeCode.substring(brakeCode.length() - 3));
    }

    @Title("CVSB-10478 - AC2 - POST: Permit the POST of a vin which has special characters (i.e, remove the restriction that vins can only contain letters + numbers) " +
            "CVSB-10478 - AC3 - POST: Permit the POST of a primaryVrm which has special characters")
    @Test
    public void testCreatePsvWitSpecialCharacters_VIN() {
        // TEST SETUP
        String specialChars = "/ \\*";

        //generate random system number
        String randomSystemNumber = GenericData.generateRandomSystemNumber();
        //generate random Vin
        String randomVin = GenericData.generateRandomVin();
        randomVin = randomVin.substring(0, randomVin.length() - 5) + specialChars;
        //generate random Vrm
        String randomVrm = specialChars;
        // read post request body from file
        String postRequestBody = GenericData.readJsonValueFromFile("technical-records_psv_mandatory_fields.json", "$");
        // read the tech record from the file used for post request body
        String techRecord = GenericData.readJsonValueFromFile("technical-records_psv_mandatory_fields.json", "$.techRecord[0]");
        // create alteration to change system number in the request body with the random generated Vin
        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$.systemNumber", randomSystemNumber, "", "REPLACE");
        // create alteration to change Vin in the request body with the random generated Vin
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        // create alteration to change primary vrm in the request body with the random generated primary vrm
        JsonPathAlteration alterationVrm = new JsonPathAlteration("$.primaryVrm", randomVrm, "", "REPLACE");
        // initialize the alterations list with both declared alteration
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(alterationVin, alterationVrm, alterationSystemNumber));

        // TEST
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBody, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(201);
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecords(randomVin);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);

        // Validate VIN and VRM.
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].vin", randomVin);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].vrms[0].vrm", randomVrm);
    }

    @WithTag("Vtm")
    @Title("CVSB-10248 - AC1 - Partial VIN is autopopulated " +
            "AC2 - Vehicle class code is autopopulated " +
            "AC3 - Body type code is auto-populated")
    @Test
    public void testCreateVehicleTrlAutoPopulateFields() {
        // TEST SETUP
        //generate random Vin
        String randomVin = GenericData.generateRandomVin();
        //generate random Vrm
        String randomVrm = GenericData.generateRandomVrm();
        // read post request body from file
        String postRequestBody = GenericData.readJsonValueFromFile("technical-records_trl_all_fields.json", "$");
        String vehicleClassDescription = GenericData.extractStringValueFromJsonString(postRequestBody, "$.techRecord[0].vehicleClass.description");
        String bodyTypeDescription = GenericData.extractStringValueFromJsonString(postRequestBody, "$.techRecord[0].bodyType.description");
        // create alteration to change Vin in the request body with the random generated Vin
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        // create alteration to change primary vrm in the request body with the random generated primary vrm
        JsonPathAlteration alterationVrm = new JsonPathAlteration("$.primaryVrm", randomVrm, "", "REPLACE");
        // initialize the alterations list with both declared alteration
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(alterationVin, alterationVrm));

        // TEST
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBody, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(201);
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsByStatus(GenericData.getPartialVinFromVin(randomVin),
                VehicleTechnicalRecordStatus.ALL);
        // validate AC1
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        // validate AC2
        String vehicleClassCode = vehicleTechnicalRecordsSteps.getVehicleClassCodeFromDescription(vehicleClassDescription);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].vehicleClass.code", vehicleClassCode);
        // validate AC3
        String bodyTypeCode = vehicleTechnicalRecordsSteps.getBodyTypeCodeFromDescription(bodyTypeDescription);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].bodyType.code", bodyTypeCode);
    }

    @WithTag("Vtm")
    @Title("CVSB-10598 - AC1 - HGV vehicle is created, and the next systemNumber is assigned")
    @Test
    public void testCreateVehicleHgvNewSystemNumberAssigned() {
        // TEST SETUP
        //generate random system number
        String randomSystemNumber = GenericData.generateRandomSystemNumber();
        //generate random Vin
        String randomVin = GenericData.generateRandomVin();
        //generate random Vrm
        String randomVrm = GenericData.generateRandomVrm();
        // read post request body from file
        String postRequestBody = GenericData.readJsonValueFromFile("technical-records_hgv_all_fields.json", "$");
        // create alteration to change system number in the request body with the random system number
        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$.systemNumber", randomSystemNumber, "", "REPLACE");
        // create alteration to change Vin in the request body with the random generated Vin
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        // create alteration to change primary vrm in the request body with the random generated primary vrm
        JsonPathAlteration alterationVrm = new JsonPathAlteration("$.primaryVrm", randomVrm, "", "REPLACE");
        // initialize the alterations list with both declared alteration
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(alterationVin, alterationVrm, alterationSystemNumber));

        // TEST
        String systemNumber = vehicleTechnicalRecordsSteps.getNextSystemNumberInSequence();
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBody, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(201);
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsByStatus(randomVin, VehicleTechnicalRecordStatus.ALL);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldNotBe("[0].systemNumber", randomSystemNumber);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].systemNumber", systemNumber);
    }

    @WithTag("Vtm")
    @Title("CVSB-10598 - AC2 - PSV vehicle is created, and the next systemNumber is assigned")
    @Test
    public void testCreateVehiclePsvNewSystemNumberAssigned() {
        // TEST SETUP
        //generate random system number
        String randomSystemNumber = GenericData.generateRandomSystemNumber();
        //generate random Vin
        String randomVin = GenericData.generateRandomVin();
        //generate random Vrm
        String randomVrm = GenericData.generateRandomVrm();
        // read post request body from file
        String postRequestBody = GenericData.readJsonValueFromFile("technical-records_psv_all_fields.json", "$");
        // create alteration to change system number in the request body with the random system number
        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$.systemNumber", randomSystemNumber, "", "REPLACE");
        // create alteration to change Vin in the request body with the random generated Vin
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        // create alteration to change primary vrm in the request body with the random generated primary vrm
        JsonPathAlteration alterationVrm = new JsonPathAlteration("$.primaryVrm", randomVrm, "", "REPLACE");
        // initialize the alterations list with both declared alteration
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(alterationVin, alterationVrm, alterationSystemNumber));

        // TEST
        String systemNumber = vehicleTechnicalRecordsSteps.getNextSystemNumberInSequence();
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBody, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(201);
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsByStatus(randomVin, VehicleTechnicalRecordStatus.ALL);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldNotBe("[0].systemNumber", randomSystemNumber);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].systemNumber", systemNumber);
    }

    @WithTag("Vtm")
    @Title("CVSB-10598 - AC3 - TRL vehicle is created, and the next systemNumber is assigned")
    @Test
    public void testCreateVehicleTrlNewSystemNumberAssigned() {
        // TEST SETUP
        //generate random system number
        String randomSystemNumber = GenericData.generateRandomSystemNumber();
        //generate random Vin
        String randomVin = GenericData.generateRandomVin();
        //generate random Vrm
        String randomVrm = GenericData.generateRandomVrm();
        // read post request body from file
        String postRequestBody = GenericData.readJsonValueFromFile("technical-records_trl_all_fields.json", "$");
        // create alteration to change system number in the request body with the random system number
        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$.systemNumber", randomSystemNumber, "", "REPLACE");
        // create alteration to change Vin in the request body with the random generated Vin
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        // create alteration to change primary vrm in the request body with the random generated primary vrm
        JsonPathAlteration alterationVrm = new JsonPathAlteration("$.primaryVrm", randomVrm, "", "REPLACE");
        // initialize the alterations list with both declared alteration
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(alterationVin, alterationVrm, alterationSystemNumber));

        // TEST
        String systemNumber = vehicleTechnicalRecordsSteps.getNextSystemNumberInSequence();
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBody, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(201);
    }

    @Title("CVSB-10328 - AC1. POST: Successfully create a new vehicle - CAR")
    @Test
    public void testCreateVehicleTechnicalRecordForCar() {

        // Generate random Vin
        String randomVin = GenericData.generateRandomVin();

        // Generate random Vrm
        String randomVrm = GenericData.generateRandomVrm();

        // Read post request body from file
        String postRequestBody = GenericData.readJsonValueFromFile("technical-records_post_payload_car_10328.json", "$");

        // Create alteration to change one more more fields in the request body
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationVrm = new JsonPathAlteration("$.primaryVrm", randomVrm, "", "REPLACE");

        // Collate the alterations
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(alterationVin, alterationVrm));

        // POST tech-records and verify the expected response
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBody, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(HttpStatus.SC_CREATED);
        vehicleTechnicalRecordsSteps.validateData("Technical Record created");

        // GET tech-records and verify the expected response
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecords(randomVin);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(HttpStatus.SC_OK);
        vehicleTechnicalRecordsSteps.fieldInPathShouldExist("[0]", "systemNumber");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].recordCompleteness", "complete");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].statusCode", "provisional");

    }

    @Title("CVSB-10328 - AC1. POST: Successfully create a new vehicle - LGV")
    @Test
    public void testCreateVehicleTechnicalRecordForLgv() {

        // Generate random Vin
        String randomVin = GenericData.generateRandomVin();

        // Generate random Vrm
        String randomVrm = GenericData.generateRandomVrm();

        // Read post request body from file
        String postRequestBody = GenericData.readJsonValueFromFile("technical-records_post_payload_lgv_10328.json", "$");

        // Create alteration to change one more more fields in the request body
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationVrm = new JsonPathAlteration("$.primaryVrm", randomVrm, "", "REPLACE");


        // Collate the alterations
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(alterationVin, alterationVrm));

        // POST tech-records and verify the expected response
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBody, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(HttpStatus.SC_CREATED);
        vehicleTechnicalRecordsSteps.validateData("Technical Record created");

        // GET tech-records and verify the expected response
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecords(randomVin);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(HttpStatus.SC_OK);
        vehicleTechnicalRecordsSteps.fieldInPathShouldExist("[0]", "systemNumber");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].recordCompleteness", "complete");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].statusCode", "provisional");

    }

    @Title("CVSB-10328 - AC1. POST: Successfully create a new vehicle - MOTORCYCLE")
    @Test
    public void testCreateVehicleTechnicalRecordForMotorcycle() {

        // Generate random Vin
        String randomVin = GenericData.generateRandomVin();

        // Generate random Vrm
        String randomVrm = GenericData.generateRandomVrm();

        // Read post request body from file
        String postRequestBody = GenericData.readJsonValueFromFile("technical-records_post_payload_motorcycle_10328.json", "$");

        // Create alteration to change one more more fields in the request body
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationVrm = new JsonPathAlteration("$.primaryVrm", randomVrm, "", "REPLACE");

        // Collate the alterations
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(alterationVin, alterationVrm));

        // POST tech-records and verify the expected response
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBody, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(HttpStatus.SC_CREATED);
        vehicleTechnicalRecordsSteps.validateData("Technical Record created");

        // GET tech-records and verify the expected response
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecords(randomVin);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(HttpStatus.SC_OK);
        vehicleTechnicalRecordsSteps.fieldInPathShouldExist("[0]", "systemNumber");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].recordCompleteness", "complete");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].statusCode", "provisional");
    }

    @Title("CVSB-10328 - AC1. POST: Successfully create a new vehicle - Optional VehicleClass - CAR")
    @Test
    public void testCreateVehicleTechnicalRecordWithOptionalVehicleClassForCar() {

        // Generate random Vin
        String randomVin = GenericData.generateRandomVin();

        // Generate random Vrm
        String randomVrm = GenericData.generateRandomVrm();

        // Read post request body from file
        String postRequestBody = GenericData.readJsonValueFromFile("technical-records_post_payload_car_10328.json", "$");

        // Create alteration to change one more more fields in the request body
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationVrm = new JsonPathAlteration("$.primaryVrm", randomVrm, "", "REPLACE");
        JsonPathAlteration alterationVehicleClass = new JsonPathAlteration("$.techRecord[0].vehicleClass", "", "", "DELETE");

        // Collate the alterations
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(alterationVin, alterationVrm, alterationVehicleClass));

        // POST tech-records and verify the expected response
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBody, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(HttpStatus.SC_CREATED);
        vehicleTechnicalRecordsSteps.validateData("Technical Record created");

        // GET tech-records and verify the expected response
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecords(randomVin);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(HttpStatus.SC_OK);
        vehicleTechnicalRecordsSteps.fieldInPathShouldExist("[0]", "systemNumber");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].recordCompleteness", "complete");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].statusCode", "provisional");
        vehicleTechnicalRecordsSteps.fieldInPathShouldNotExist("[0].techRecord[0]", "vehicleClass");
    }

    @Title("CVSB-10328 - AC1. POST: Successfully create a new vehicle - Optional VehicleClass - LGV")
    @Test
    public void testCreateVehicleTechnicalRecordWithOptionalVehicleClassForLgv() {

        // Generate random Vin
        String randomVin = GenericData.generateRandomVin();

        // Generate random Vrm
        String randomVrm = GenericData.generateRandomVrm();

        // Read post request body from file
        String postRequestBody = GenericData.readJsonValueFromFile("technical-records_post_payload_lgv_10328.json", "$");

        // Create alteration to change one more more fields in the request body
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationVrm = new JsonPathAlteration("$.primaryVrm", randomVrm, "", "REPLACE");
        JsonPathAlteration alterationVehicleClass = new JsonPathAlteration("$.techRecord[0].vehicleClass", "", "", "DELETE");

        // Collate the alterations
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(alterationVin, alterationVrm, alterationVehicleClass));

        // POST tech-records and verify the expected response
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBody, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(HttpStatus.SC_CREATED);
        vehicleTechnicalRecordsSteps.validateData("Technical Record created");

        // GET tech-records and verify the expected response
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecords(randomVin);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(HttpStatus.SC_OK);
        vehicleTechnicalRecordsSteps.fieldInPathShouldExist("[0]", "systemNumber");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].recordCompleteness", "complete");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].statusCode", "provisional");
        vehicleTechnicalRecordsSteps.fieldInPathShouldNotExist("[0].techRecord[0]", "vehicleClass");
    }

    @WithTag("Vtm")
    @Title("CVSB-10316 - AC4 - statusCode updated in vehicle API + AC3 - testResults created in test results API - HGV - PRS - First Test")
    @Test
    public void testPostVehicleTechRecordHgvPrsFirstTest() {

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

        System.out.println(systemNumber);

        // read base JSON for POST test-results
        String testResultRecord = GenericData.readJsonValueFromFile("test-results_first_test_hgv_payload_10316.json", "$");

        String randomTestResultId = UUID.randomUUID().toString();
        JsonPathAlteration alterationSystemNumberTestResults = new JsonPathAlteration("$.systemNumber", systemNumber, "", "REPLACE");
        JsonPathAlteration alterationVinTestResults = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationTestResultIdPost = new JsonPathAlteration("$.testResultId", randomTestResultId, "", "REPLACE");
        JsonPathAlteration alterationTestResult = new JsonPathAlteration("$.testTypes[0].testResult", "prs", "", "REPLACE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterationsTestResults = new ArrayList<>(Arrays.asList(
                alterationSystemNumberTestResults,
                alterationVinTestResults,
                alterationTestResultIdPost,
                alterationTestResult));

        // Post the test-results, together with any alterations, and verify that they are accepted.
        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, alterationsTestResults);
        testResultsSteps.statusCodeShouldBe(HttpStatus.SC_CREATED);
        testResultsSteps.validateData("Test records created");

        // wait until the tech-record is updated
        vehicleTechnicalRecordsSteps.waitForVehicleTechRecordsToBeUpdated(randomVin, 10);

        // GET test - results to validate the expected response
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsByStatusAndSearchCriteria(randomVin, VehicleTechnicalRecordStatus.ALL, VehicleTechnicalRecordSearchCriteria.VIN);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(HttpStatus.SC_OK);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].statusCode", "archived");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].lastUpdatedByName", "Gica");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].lastUpdatedById", "133");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldContains("[0].techRecord[0].lastUpdatedAt", new SimpleDateFormat("yyyy-MM-dd").format(date));
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldContains("[0].techRecord[0].updateType", "techRecordUpdate");

        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[1].statusCode", "current");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[1].createdByName", "Gica");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[1].createdById", "133");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldContains("[0].techRecord[1].createdAt", new SimpleDateFormat("yyyy-MM-dd").format(date));

    }

    @WithTag("Vtm")
    @Title("CVSB-10316 - AC4 - statusCode updated in vehicle API + AC3 - testResults created in test results API - HGV - PASS - First Test")
    @Test
    public void testPostVehicleTechRecordHgvPassFirstTest() {

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

        System.out.println(systemNumber);

        // read base JSON for POST test-results
        String testResultRecord = GenericData.readJsonValueFromFile("test-results_first_test_hgv_payload_10316.json", "$");

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
        vehicleTechnicalRecordsSteps.waitForVehicleTechRecordsToBeUpdated(randomVin, 10);

        // GET test - results to validate the expected response
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsByStatusAndSearchCriteria(randomVin, VehicleTechnicalRecordStatus.ALL, VehicleTechnicalRecordSearchCriteria.VIN);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(HttpStatus.SC_OK);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord.size()", 2);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].statusCode", "archived");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].lastUpdatedByName", "Gica");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].lastUpdatedById", "133");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldContains("[0].techRecord[0].lastUpdatedAt", new SimpleDateFormat("yyyy-MM-dd").format(date));
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldContains("[0].techRecord[0].updateType", "techRecordUpdate");

        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[1].statusCode", "current");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[1].createdByName", "Gica");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[1].createdById", "133");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldContains("[0].techRecord[1].createdAt", new SimpleDateFormat("yyyy-MM-dd").format(date));
    }

    @WithTag("Vtm")
    @Title("CVSB-10316 - AC2 - statusCode updated in vehicle API + AC1 - testResults created in test results API - HGV - PASS - Notifiable Alteration")
    @Test
    public void testPostVehicleTechRecordHgvPassNotifiableAlteration() {

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

        System.out.println(systemNumber);

        // read base JSON for POST test-results
        String testResultRecord = GenericData.readJsonValueFromFile("test-results_notifiable_alteration_hgv_payload_10316.json", "$");

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
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord.size()", 2);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].statusCode", "archived");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].lastUpdatedByName", "Gica");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].lastUpdatedById", "133");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldContains("[0].techRecord[0].lastUpdatedAt", new SimpleDateFormat("yyyy-MM-dd").format(date));
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldContains("[0].techRecord[0].updateType", "techRecordUpdate");

        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[1].statusCode", "current");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[1].createdByName", "Gica");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[1].createdById", "133");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldContains("[0].techRecord[1].createdAt", new SimpleDateFormat("yyyy-MM-dd").format(date));
    }

    @WithTag("Vtm")
    @Title("CVSB-10316 - AC2 - statusCode updated in vehicle API + AC1 - testResults created in test results API - HGV - PRS - Notifiable Alteration")
    @Test
    public void testPostVehicleTechRecordHgvPrsNotifiableAlteration() {

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

        System.out.println(systemNumber);

        // read base JSON for POST test-results
        String testResultRecord = GenericData.readJsonValueFromFile("test-results_notifiable_alteration_hgv_payload_10316.json", "$");

        String randomTestResultId = UUID.randomUUID().toString();
        JsonPathAlteration alterationSystemNumberTestResults = new JsonPathAlteration("$.systemNumber", systemNumber, "", "REPLACE");
        JsonPathAlteration alterationVinTestResults = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationTestResultIdPost = new JsonPathAlteration("$.testResultId", randomTestResultId, "", "REPLACE");
        JsonPathAlteration alterationTestResult = new JsonPathAlteration("$.testTypes[0].testResult", "prs", "", "REPLACE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterationsTestResults = new ArrayList<>(Arrays.asList(
                alterationSystemNumberTestResults,
                alterationVinTestResults,
                alterationTestResultIdPost,
                alterationTestResult));

        // Post the test-results, together with any alterations, and verify that they are accepted.
        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, alterationsTestResults);
        testResultsSteps.statusCodeShouldBe(HttpStatus.SC_CREATED);
        testResultsSteps.validateData("Test records created");

        // wait until the tech-record is updated
        vehicleTechnicalRecordsSteps.waitForVehicleTechRecordsToBeUpdated(randomVin, 20);

        // GET test - results to validate the expected response
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsByStatusAndSearchCriteria(randomVin, VehicleTechnicalRecordStatus.ALL, VehicleTechnicalRecordSearchCriteria.VIN);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(HttpStatus.SC_OK);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord.size()", 2);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].statusCode", "archived");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].lastUpdatedByName", "Gica");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].lastUpdatedById", "133");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldContains("[0].techRecord[0].lastUpdatedAt", new SimpleDateFormat("yyyy-MM-dd").format(date));
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldContains("[0].techRecord[0].updateType", "techRecordUpdate");

        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[1].statusCode", "current");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[1].createdByName", "Gica");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[1].createdById", "133");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldContains("[0].techRecord[1].createdAt", new SimpleDateFormat("yyyy-MM-dd").format(date));
    }

    @WithTag("Vtm")
    @Title("CVSB-10316 - AC4 - statusCode updated in vehicle API + AC3 - testResults created in test results API - TRL - PASS - First Test")
    @Test
    public void testPostVehicleTechRecordTrlPassFirstTest() {

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
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].statusCode", "provisional");

        String systemNumber = vehicleTechnicalRecordsSteps.getSystemNumber();

        System.out.println(systemNumber);

        // read base JSON for POST test-results
        String testResultRecord = GenericData.readJsonValueFromFile("test-results_first_test_trl_payload_10316.json", "$");

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
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord.size()", 2);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].statusCode", "archived");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].lastUpdatedByName", "Gica");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].lastUpdatedById", "133");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldContains("[0].techRecord[0].lastUpdatedAt", new SimpleDateFormat("yyyy-MM-dd").format(date));
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldContains("[0].techRecord[0].updateType", "techRecordUpdate");

        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[1].statusCode", "current");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[1].createdByName", "Gica");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[1].createdById", "133");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldContains("[0].techRecord[1].createdAt", new SimpleDateFormat("yyyy-MM-dd").format(date));
    }


    @WithTag("Vtm")
    @Title("CVSB-10316 - AC4 - statusCode updated in vehicle API + AC3 - testResults created in test results API - TRL - PRS - First Test")
    @Test
    public void testPostVehicleTechRecordTrlPrsFirstTest() {

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
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].statusCode", "provisional");

        String systemNumber = vehicleTechnicalRecordsSteps.getSystemNumber();

        System.out.println(systemNumber);

        // read base JSON for POST test-results
        String testResultRecord = GenericData.readJsonValueFromFile("test-results_first_test_trl_payload_10316.json", "$");

        String randomTestResultId = UUID.randomUUID().toString();
        JsonPathAlteration alterationSystemNumberTestResults = new JsonPathAlteration("$.systemNumber", systemNumber, "", "REPLACE");
        JsonPathAlteration alterationVinTestResults = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationTestResultIdPost = new JsonPathAlteration("$.testResultId", randomTestResultId, "", "REPLACE");
        JsonPathAlteration alterationTestResult = new JsonPathAlteration("$.testTypes[0].testResult", "prs", "", "REPLACE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterationsTestResults = new ArrayList<>(Arrays.asList(
                alterationSystemNumberTestResults,
                alterationVinTestResults,
                alterationTestResultIdPost,
                alterationTestResult));

        // Post the test-results, together with any alterations, and verify that they are accepted.
        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, alterationsTestResults);
        testResultsSteps.statusCodeShouldBe(HttpStatus.SC_CREATED);
        testResultsSteps.validateData("Test records created");

        // wait until the tech-record is updated
        vehicleTechnicalRecordsSteps.waitForVehicleTechRecordsToBeUpdated(randomVin, 20);

        // GET test - results to validate the expected response
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsByStatusAndSearchCriteria(randomVin, VehicleTechnicalRecordStatus.ALL, VehicleTechnicalRecordSearchCriteria.VIN);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(HttpStatus.SC_OK);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord.size()", 2);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].statusCode", "archived");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].lastUpdatedByName", "Gica");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].lastUpdatedById", "133");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldContains("[0].techRecord[0].lastUpdatedAt", new SimpleDateFormat("yyyy-MM-dd").format(date));
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldContains("[0].techRecord[0].updateType", "techRecordUpdate");

        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[1].statusCode", "current");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[1].createdByName", "Gica");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[1].createdById", "133");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldContains("[0].techRecord[1].createdAt", new SimpleDateFormat("yyyy-MM-dd").format(date));
    }

    @WithTag("Vtm")
    @Title("CVSB-10316 - AC2 - statusCode updated in vehicle API + AC1 - testResults created in test results API - TRL - PASS - Notifiable Alteration")
    @Test
    public void testPostVehicleTechRecordTrlPassNotifiableAlteration() {

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
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].statusCode", "provisional");

        String systemNumber = vehicleTechnicalRecordsSteps.getSystemNumber();

        System.out.println(systemNumber);

        // read base JSON for POST test-results
        String testResultRecord = GenericData.readJsonValueFromFile("test-results_notifiable_alteration_trl_payload_10316.json", "$");

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
        vehicleTechnicalRecordsSteps.waitForVehicleTechRecordsToBeUpdated(randomVin, 10);

        // GET test - results to validate the expected response
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsByStatusAndSearchCriteria(randomVin, VehicleTechnicalRecordStatus.ALL, VehicleTechnicalRecordSearchCriteria.VIN);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(HttpStatus.SC_OK);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord.size()", 2);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].statusCode", "archived");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].lastUpdatedByName", "Gica");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].lastUpdatedById", "133");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldContains("[0].techRecord[0].lastUpdatedAt", new SimpleDateFormat("yyyy-MM-dd").format(date));
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldContains("[0].techRecord[0].updateType", "techRecordUpdate");

        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[1].statusCode", "current");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[1].createdByName", "Gica");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[1].createdById", "133");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldContains("[0].techRecord[1].createdAt", new SimpleDateFormat("yyyy-MM-dd").format(date));
    }

    @WithTag("Vtm")
    @Title("CVSB-10316 - AC2 - statusCode updated in vehicle API + AC1 - testResults created in test results API  - TRL - PRS - Notifiable Alteration")
    @Test
    public void testPostVehicleTechRecordTrlPrsNotifiableAlteration() {

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
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].statusCode", "provisional");

        String systemNumber = vehicleTechnicalRecordsSteps.getSystemNumber();

        System.out.println(systemNumber);

        // read base JSON for POST test-results
        String testResultRecord = GenericData.readJsonValueFromFile("test-results_notifiable_alteration_trl_payload_10316.json", "$");

        String randomTestResultId = UUID.randomUUID().toString();
        JsonPathAlteration alterationSystemNumberTestResults = new JsonPathAlteration("$.systemNumber", systemNumber, "", "REPLACE");
        JsonPathAlteration alterationVinTestResults = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationTestResultIdPost = new JsonPathAlteration("$.testResultId", randomTestResultId, "", "REPLACE");
        JsonPathAlteration alterationTestResult = new JsonPathAlteration("$.testTypes[0].testResult", "prs", "", "REPLACE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterationsTestResults = new ArrayList<>(Arrays.asList(
                alterationSystemNumberTestResults,
                alterationVinTestResults,
                alterationTestResultIdPost,
                alterationTestResult));

        // Post the test-results, together with any alterations, and verify that they are accepted.
        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, alterationsTestResults);
        testResultsSteps.statusCodeShouldBe(HttpStatus.SC_CREATED);
        testResultsSteps.validateData("Test records created");

        // wait until the tech-record is updated
        vehicleTechnicalRecordsSteps.waitForVehicleTechRecordsToBeUpdated(randomVin, 10);

        // GET test - results to validate the expected response
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsByStatusAndSearchCriteria(randomVin, VehicleTechnicalRecordStatus.ALL, VehicleTechnicalRecordSearchCriteria.VIN);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(HttpStatus.SC_OK);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord.size()", 2);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].statusCode", "archived");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].lastUpdatedByName", "Gica");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].lastUpdatedById", "133");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldContains("[0].techRecord[0].lastUpdatedAt", new SimpleDateFormat("yyyy-MM-dd").format(date));
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldContains("[0].techRecord[0].updateType", "techRecordUpdate");

        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[1].statusCode", "current");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[1].createdByName", "Gica");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[1].createdById", "133");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldContains("[0].techRecord[1].createdAt", new SimpleDateFormat("yyyy-MM-dd").format(date));
    }

    @WithTag("Vtm")
    @Title("CVSB-10316 - AC5 - testResults created in test results API - HGV - FAIL - First Test")
    @Test
    public void testPostVehicleTechRecordHgvFailFirstTest() {

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

        System.out.println(systemNumber);

        // read base JSON for POST test-results
        String testResultRecord = GenericData.readJsonValueFromFile("test-results_first_test_hgv_payload_10316.json", "$");

        String randomTestResultId = UUID.randomUUID().toString();
        JsonPathAlteration alterationSystemNumberTestResults = new JsonPathAlteration("$.systemNumber", systemNumber, "", "REPLACE");
        JsonPathAlteration alterationVinTestResults = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationTestResultIdPost = new JsonPathAlteration("$.testResultId", randomTestResultId, "", "REPLACE");
        JsonPathAlteration alterationTestResult = new JsonPathAlteration("$.testTypes[0].testResult", "fail", "", "REPLACE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterationsTestResults = new ArrayList<>(Arrays.asList(
                alterationSystemNumberTestResults,
                alterationVinTestResults,
                alterationTestResultIdPost,
                alterationTestResult));

        // Post the test-results, together with any alterations, and verify that they are accepted.
        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, alterationsTestResults);
        testResultsSteps.statusCodeShouldBe(HttpStatus.SC_CREATED);
        testResultsSteps.validateData("Test records created");

        // wait until the tech-record is updated
        vehicleTechnicalRecordsSteps.waitForVehicleTechRecordsToBeUpdated(randomVin, 5);

        // GET test - results to validate the expected response
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsByStatusAndSearchCriteria(randomVin, VehicleTechnicalRecordStatus.ALL, VehicleTechnicalRecordSearchCriteria.VIN);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(HttpStatus.SC_OK);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord.size()", 1);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].statusCode", "provisional");

    }

    @WithTag("Vtm")
    @Title("CVSB-10316 - AC5 - testResults created in test results API - HGV - FAIL - Notifiable Alteration")
    @Test
    public void testPostVehicleTechRecordHgvFailNotifiableAlteration() {

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

        System.out.println(systemNumber);

        // read base JSON for POST test-results
        String testResultRecord = GenericData.readJsonValueFromFile("test-results_notifiable_alteration_hgv_payload_10316.json", "$");

        String randomTestResultId = UUID.randomUUID().toString();
        JsonPathAlteration alterationSystemNumberTestResults = new JsonPathAlteration("$.systemNumber", systemNumber, "", "REPLACE");
        JsonPathAlteration alterationVinTestResults = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationTestResultIdPost = new JsonPathAlteration("$.testResultId", randomTestResultId, "", "REPLACE");
        JsonPathAlteration alterationTestResult = new JsonPathAlteration("$.testTypes[0].testResult", "fail", "", "REPLACE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterationsTestResults = new ArrayList<>(Arrays.asList(
                alterationSystemNumberTestResults,
                alterationVinTestResults,
                alterationTestResultIdPost,
                alterationTestResult));

        // Post the test-results, together with any alterations, and verify that they are accepted.
        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, alterationsTestResults);
        testResultsSteps.statusCodeShouldBe(HttpStatus.SC_CREATED);
        testResultsSteps.validateData("Test records created");

        // wait until the tech-record is updated
        vehicleTechnicalRecordsSteps.waitForVehicleTechRecordsToBeUpdated(randomVin, 5);

        // GET test - results to validate the expected response
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsByStatusAndSearchCriteria(randomVin, VehicleTechnicalRecordStatus.ALL, VehicleTechnicalRecordSearchCriteria.VIN);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(HttpStatus.SC_OK);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord.size()", 1);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].statusCode", "provisional");
    }

    @WithTag("Vtm")
    @Title("CVSB-10316 - CVSB-10316 - AC5 - testResults created in test results API - TRL - FAIL - First Test")
    @Test
    public void testPostVehicleTechRecordTrlFailFirstTest() {

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
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].statusCode", "provisional");

        String systemNumber = vehicleTechnicalRecordsSteps.getSystemNumber();

        System.out.println(systemNumber);

        // read base JSON for POST test-results
        String testResultRecord = GenericData.readJsonValueFromFile("test-results_first_test_trl_payload_10316.json", "$");

        String randomTestResultId = UUID.randomUUID().toString();
        JsonPathAlteration alterationSystemNumberTestResults = new JsonPathAlteration("$.systemNumber", systemNumber, "", "REPLACE");
        JsonPathAlteration alterationVinTestResults = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationTestResultIdPost = new JsonPathAlteration("$.testResultId", randomTestResultId, "", "REPLACE");
        JsonPathAlteration alterationTestResult = new JsonPathAlteration("$.testTypes[0].testResult", "fail", "", "REPLACE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterationsTestResults = new ArrayList<>(Arrays.asList(
                alterationSystemNumberTestResults,
                alterationVinTestResults,
                alterationTestResultIdPost,
                alterationTestResult));

        // Post the test-results, together with any alterations, and verify that they are accepted.
        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, alterationsTestResults);
        testResultsSteps.statusCodeShouldBe(HttpStatus.SC_CREATED);
        testResultsSteps.validateData("Test records created");

        // wait until the tech-record is updated
        vehicleTechnicalRecordsSteps.waitForVehicleTechRecordsToBeUpdated(randomVin, 5);

        // GET test - results to validate the expected response
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsByStatusAndSearchCriteria(randomVin, VehicleTechnicalRecordStatus.ALL, VehicleTechnicalRecordSearchCriteria.VIN);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(HttpStatus.SC_OK);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord.size()", 1);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].statusCode", "provisional");
    }

    @WithTag("Vtm")
    @Title("CVSB-10316 - AC5 - testResults created in test results API - TRL - FAIL - Notifiable Alteration")
    @Test
    public void testPostVehicleTechRecordTrlFailNotifiableAlteration() {

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
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].statusCode", "provisional");

        String systemNumber = vehicleTechnicalRecordsSteps.getSystemNumber();

        System.out.println(systemNumber);

        // read base JSON for POST test-results
        String testResultRecord = GenericData.readJsonValueFromFile("test-results_notifiable_alteration_trl_payload_10316.json", "$");

        String randomTestResultId = UUID.randomUUID().toString();
        JsonPathAlteration alterationSystemNumberTestResults = new JsonPathAlteration("$.systemNumber", systemNumber, "", "REPLACE");
        JsonPathAlteration alterationVinTestResults = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationTestResultIdPost = new JsonPathAlteration("$.testResultId", randomTestResultId, "", "REPLACE");
        JsonPathAlteration alterationTestResult = new JsonPathAlteration("$.testTypes[0].testResult", "fail", "", "REPLACE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterationsTestResults = new ArrayList<>(Arrays.asList(
                alterationSystemNumberTestResults,
                alterationVinTestResults,
                alterationTestResultIdPost,
                alterationTestResult));

        // Post the test-results, together with any alterations, and verify that they are accepted.
        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, alterationsTestResults);
        testResultsSteps.statusCodeShouldBe(HttpStatus.SC_CREATED);
        testResultsSteps.validateData("Test records created");

        // wait until the tech-record is updated
        vehicleTechnicalRecordsSteps.waitForVehicleTechRecordsToBeUpdated(randomVin, 10);

        // GET test - results to validate the expected response
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsByStatusAndSearchCriteria(randomVin, VehicleTechnicalRecordStatus.ALL, VehicleTechnicalRecordSearchCriteria.VIN);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(HttpStatus.SC_OK);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord.size()", 1);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].statusCode", "provisional");
    }

    @WithTag("Vtm")
    @Title("CVSB-10316 - AC8 - POST method attributes updated")
    @Test
    public void testPostVehicleTechRecordHgv() {

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

        System.out.println(systemNumber);

        // read base JSON for POST test-results
        String testResultRecord = GenericData.readJsonValueFromFile("test-results_notifiable_alteration_hgv_payload_10316.json", "$");

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

        testResultsSteps.waitForTestResultsToBeUpdated(systemNumber, 60);

        testResultsSteps.getTestResults(systemNumber);
        testResultsSteps.statusCodeShouldBe(HttpStatus.SC_OK);

        testResultsSteps.valueForFieldInPathShouldBe("[0].testVersion", "current");
        testResultsSteps.valueForFieldInPathShouldBe("[0].reasonForCreation", "Test conducted");
        testResultsSteps.valueForFieldInPathShouldBe("[0].createdByName", testResultsSteps.getTesterName());
        testResultsSteps.valueForFieldInPathShouldBe("[0].createdById", testResultsSteps.getTesterStaffId());
    }

    @Title("CVSB-11795 - AC1 - Record completeness = complete (core and non-core attributes) - PSV")
    @Test
    public void testVehicleTechnicalRecordForRecordCompletenessPsv() {

        // TEST SETUP
        //generate random system number
        String randomSystemNumber = GenericData.generateRandomSystemNumber();
        //generate random Vin
        String randomVin = GenericData.generateRandomVin();
        //generate random Vrm
        String randomVrm = GenericData.generateRandomVrm();
        // read post request body from file
        String postRequestBody = GenericData.readJsonValueFromFile("technical-records_psv_all_fields.json", "$");
        // create alteration to change system number in the request body with the random system number
        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$.systemNumber", randomSystemNumber, "", "REPLACE");
        // create alteration to change Vin in the request body with the random generated Vin
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        // create alteration to change primary vrm in the request body with the random generated primary vrm
        JsonPathAlteration alterationVrm = new JsonPathAlteration("$.primaryVrm", randomVrm, "", "REPLACE");

        // initialize the alterations list with both declared alteration
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(alterationVin,
                alterationVrm,
                alterationSystemNumber));

        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBody, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(201);

        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsBySearchCriteria(randomVin, VehicleTechnicalRecordSearchCriteria.VIN);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(HttpStatus.SC_OK);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].recordCompleteness", "complete");
    }

    @WithTag("Vtm")
    @Title("CVSB-11795 - AC1 - Record completeness = complete (core and non-core attributes) - HGV")
    @Test
    public void testVehicleTechnicalRecordForRecordCompletenessHgv() {

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
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].recordCompleteness", "complete");
    }

    @WithTag("Vtm")
    @Title("CVSB-11795 - AC1 - Record completeness = complete (core and non-core attributes) - Trailer")
    @Test
    public void testVehicleTechnicalRecordForRecordCompletenessTrailer() {
        // TEST SETUP
        //generate random system number
        String randomSystemNumber = GenericData.generateRandomSystemNumber();
        //generate random Vin
        String randomVin = GenericData.generateRandomVin();
        //generate random Vrm
        String randomVrm = GenericData.generateRandomVrm();
        // read post request body from file
        String postRequestBody = GenericData.readJsonValueFromFile("technical-records_trl_all_fields.json", "$");
        // create alteration to change system number in the request body with the random system number
        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$.systemNumber", randomSystemNumber, "", "REPLACE");
        // create alteration to change Vin in the request body with the random generated Vin
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        // create alteration to change primary vrm in the request body with the random generated primary vrm
        JsonPathAlteration alterationVrm = new JsonPathAlteration("$.primaryVrm", randomVrm, "", "REPLACE");

        // initialize the alterations list with both declared alteration
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(alterationVin, alterationVrm, alterationSystemNumber));
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBody, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(201);

        //GET tech-records
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsBySearchCriteria(randomVin, VehicleTechnicalRecordSearchCriteria.VIN);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(HttpStatus.SC_OK);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].recordCompleteness", "complete");
    }

    @Title("CVSB-11795 - AC2 - Record completeness = complete (only core attributes) - CAR")
    @Test
    public void testVehicleTechnicalRecordForRecordCompletenessCar() {

        // Generate random SystemNumber
        String randomSystemNumber = GenericData.generateRandomSystemNumber();

        // Generate random Vin
        String randomVin = GenericData.generateRandomVin();

        // Generate random Vrm
        String randomVrm = GenericData.generateRandomVrm();

        // Read post request body from file
        String postRequestBody = GenericData.readJsonValueFromFile("technical-records_post_payload_car_10328.json", "$");

        // Create alteration to change one more more fields in the request body
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationVrm = new JsonPathAlteration("$.primaryVrm", randomVrm, "", "REPLACE");
        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$", randomSystemNumber, "systemNumber", "ADD_FIELD");

        // Collate the alterations
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(alterationVin, alterationVrm, alterationSystemNumber));

        // POST tech-records and verify the expected response
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBody, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(HttpStatus.SC_CREATED);
        vehicleTechnicalRecordsSteps.validateData("Technical Record created");

        //GET tech-records
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsBySearchCriteria(randomVin, VehicleTechnicalRecordSearchCriteria.VIN);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(HttpStatus.SC_OK);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].recordCompleteness", "complete");
    }

    @Title("CVSB-11795 - AC2 - Record completeness = complete (only core attributes) - LGV")
    @Test
    public void testVehicleTechnicalRecordForRecordCompletenessLgv() {

        // Generate random SystemNumber
        String randomSystemNumber = GenericData.generateRandomSystemNumber();

        // Generate random Vin
        String randomVin = GenericData.generateRandomVin();

        // Generate random Vrm
        String randomVrm = GenericData.generateRandomVrm();

        // Read post request body from file
        String postRequestBody = GenericData.readJsonValueFromFile("technical-records_post_payload_lgv_10328.json", "$");

        // Create alteration to change one more more fields in the request body
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationVrm = new JsonPathAlteration("$.primaryVrm", randomVrm, "", "REPLACE");
        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$", randomSystemNumber, "systemNumber", "ADD_FIELD");

        // Collate the alterations
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(alterationVin, alterationVrm, alterationSystemNumber));

        // POST tech-records and verify the expected response
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBody, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(HttpStatus.SC_CREATED);
        vehicleTechnicalRecordsSteps.validateData("Technical Record created");

        //GET tech-records
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsBySearchCriteria(randomVin, VehicleTechnicalRecordSearchCriteria.VIN);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(HttpStatus.SC_OK);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].recordCompleteness", "complete");
    }

    @Title("CVSB-11795 - AC2 - Record completeness = complete (only core attributes) - MOTORCYCLE")
    @Test
    public void testVehicleTechnicalRecordForRecordCompletenessMotorcycle() {

        // Generate random SystemNumber
        String randomSystemNumber = GenericData.generateRandomSystemNumber();

        // Generate random Vin
        String randomVin = GenericData.generateRandomVin();

        // Generate random Vrm
        String randomVrm = GenericData.generateRandomVrm();

        // Read post request body from file
        String postRequestBody = GenericData.readJsonValueFromFile("technical-records_post_payload_motorcycle_10328.json", "$");

        // Create alteration to change one more more fields in the request body
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationVrm = new JsonPathAlteration("$.primaryVrm", randomVrm, "", "REPLACE");
        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$", randomSystemNumber, "systemNumber", "ADD_FIELD");

        // Collate the alterations
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(alterationVin, alterationVrm, alterationSystemNumber));

        // POST tech-records and verify the expected response
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBody, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(HttpStatus.SC_CREATED);
        vehicleTechnicalRecordsSteps.validateData("Technical Record created");

        //GET tech-records
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsBySearchCriteria(randomVin, VehicleTechnicalRecordSearchCriteria.VIN);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(HttpStatus.SC_OK);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].recordCompleteness", "complete");
    }
}
