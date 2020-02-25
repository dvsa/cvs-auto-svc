package vehicle;


import clients.model.VehicleClass;
import clients.model.BodyType;
import data.GenericData;
import model.vehicles.VehicleTechnicalRecordStatus;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import net.thucydides.core.annotations.WithTag;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import steps.VehicleTechnicalRecordsSteps;
import util.JsonPathAlteration;
import steps.TestResultsSteps;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@RunWith(SerenityRunner.class)
public class PostVehicleTechnicalRecords {

    @Steps
    VehicleTechnicalRecordsSteps vehicleTechnicalRecordsSteps;

    @Steps
    TestResultsSteps testResultsSteps;

    @WithTag("Vtm")
    @Title("CVSB-7885 - AC1 - API Consumer creates a technical record for a vehicle with a specific vin" +
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
        String postRequestBody = GenericData.readJsonValueFromFile("technical-records_hgv_all_fields.json","$");
        //  read another tech record entry from different file
        String additionalTechRecord = GenericData.readJsonValueFromFile("technical-records_archived.json","$.techRecord[0]");
        // create alteration to add one more tech record to in the request body
        JsonPathAlteration alterationTechRecord = new JsonPathAlteration("$.techRecord", additionalTechRecord,"","ADD_VALUE");
        // create alteration to change systemNumber in the request body with the random generated Vin
        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$.systemNumber", randomSystemNumber,"","REPLACE");
        // create alteration to change Vin in the request body with the random generated Vin
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin,"","REPLACE");
        // create alteration to change primary vrm in the request body with the random generated primary vrm
        JsonPathAlteration alterationVrm = new JsonPathAlteration("$.primaryVrm", randomVrm,"","REPLACE");
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
    public void testCreateVehicleWithOnlyMandatoryFields() {
        // TEST SETUP
        //generate random system number
        String randomSystemNumber = GenericData.generateRandomSystemNumber();
        //generate random Vin
        String randomVin = GenericData.generateRandomVin();
        //generate random Vrm
        String randomVrm = GenericData.generateRandomVrm();
        // read post request body from file
        String postRequestBody = GenericData.readJsonValueFromFile("technical-records_hgv_mandatory_fields.json","$");
        // read the tech record from the file used for post request body
        String techRecord = GenericData.readJsonValueFromFile("technical-records_hgv_mandatory_fields.json","$.techRecord[0]");
        // create alteration to change system number in the request body with the random generated Vin
        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$.systemNumber", randomSystemNumber,"","REPLACE");
        // create alteration to change Vin in the request body with the random generated Vin
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin,"","REPLACE");
        // create alteration to change primary vrm in the request body with the random generated primary vrm
        JsonPathAlteration alterationVrm = new JsonPathAlteration("$.primaryVrm", randomVrm,"","REPLACE");
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
    @Title("CVSB-10213 - AC1 - Partial VIN is auto-populated when creating a new vehicle" +
            "AC2 - Vehicle class code is auto-populated when creating a new vehicle" +
            "AC3 - Body type code is auto-populated when creating a new vehicle")
    @Test
    public void testCreateVehicleAutoPopulateFields() {
        // TEST SETUP
        //generate random system number
        String randomSystemNumber = GenericData.generateRandomSystemNumber();
        //generate random Vin
        String randomVin = GenericData.generateRandomVin();
        //generate random Vrm
        String randomVrm = GenericData.generateRandomVrm();
        // read post request body from file
        String postRequestBody = GenericData.readJsonValueFromFile("technical-records_hgv_all_fields.json","$");
        String vehicleClassDescription = GenericData.extractStringValueFromJsonString(postRequestBody, "$.techRecord[0].vehicleClass.description");
        String bodyTypeDescription = GenericData.extractStringValueFromJsonString(postRequestBody, "$.techRecord[0].bodyType.description");
        // create alteration to change system number in the request body with the random system number
        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$.systemNumber", randomSystemNumber,"","REPLACE");
        // create alteration to change Vin in the request body with the random generated Vin
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin,"","REPLACE");
        // create alteration to change primary vrm in the request body with the random generated primary vrm
        JsonPathAlteration alterationVrm = new JsonPathAlteration("$.primaryVrm", randomVrm,"","REPLACE");
        // initialize the alterations list with both declared alteration
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(alterationVin, alterationVrm, alterationSystemNumber));

        // TEST
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBody, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(201);
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsByStatus(GenericData.getPartialVinFromVin(randomVin), VehicleTechnicalRecordStatus.ALL);
        // validate AC1
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        // validate AC2
        VehicleClass vehicleClass = VehicleClass.MOTORBIKE_OVER_200CC;
        for (VehicleClass v : VehicleClass.values()) {
            if (v.getDescription().equals(vehicleClassDescription)) {
                vehicleClass = v;
            }
        }
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].vehicleClass.code", vehicleClass.getCode());
        // validated AC3
        BodyType bodyType = BodyType.ARTICULATED;
        for (BodyType b : BodyType.values()) {
            if (b.getDescription().equals(bodyTypeDescription)) {
                bodyType = b;
            }
        }
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].bodyType.code", bodyType.getCode());
    }

    @Title("CVSB-10752 - AC1 API Consumer retrieve the Vehicle Technical Records - Single Vehicle")
    @Test
    public void testTechnicalRecordForSingleVehicle() {

        // Read the base test result JSON.
        String postRequestBody = GenericData.readJsonValueFromFile("technical-records_duplicate_chassis_10752.json","$");

        // Create alteration to add one more tech record to in the request body
        String randomSystemNumber = GenericData.generateRandomSystemNumber();
        String randomVin = GenericData.generateRandomVin();
        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$.systemNumber", randomSystemNumber, "", "REPLACE");
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin,"","REPLACE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(alterationSystemNumber, alterationVin));

        // Post the results, together with any alterations, and verify that they are accepted.
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBody, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(201);
        vehicleTechnicalRecordsSteps.validateData("Technical Record created");

        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecords(randomVin);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord.size()", 1);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].systemNumber", randomSystemNumber);
    }

    @Title("CVSB - 10752 - AC1 API Consumer retrieve the Vehicle Technical Records - Multiple Vehicles + AC3 Certificate generation")
    @Test
    public void testMultipleVehiclesTestResults(){

        // Read the base test record json
        String postRequestBody = GenericData.readJsonValueFromFile("technical-records_duplicate_chassis_10752.json","$");

        // Generate random System Number for first vehicle
        String randomSysNumVehicleOne = GenericData.generateRandomSystemNumber();

        // Generate random Vin
        String randomVin = GenericData.generateRandomVin();

        // Create alteration in the post request with System Number and Vin for first vehicle
        JsonPathAlteration altSysNumVehicleOne = new JsonPathAlteration("$.systemNumber", randomSysNumVehicleOne, "", "REPLACE");
        JsonPathAlteration altVin = new JsonPathAlteration("$.vin", randomVin,"","REPLACE");

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
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("systemNumber.size()",2);
//
        // Read the base test result JSON.
        String testResultRecord = GenericData.readJsonValueFromFile("test-results_duplicate_chassis_10752.json","$");

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
}
