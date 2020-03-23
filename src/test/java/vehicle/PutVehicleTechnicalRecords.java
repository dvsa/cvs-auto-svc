package vehicle;

import data.GenericData;
import model.vehicles.VehicleTechnicalRecordStatus;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import net.thucydides.core.annotations.WithTag;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.Ignore;
import steps.VehicleTechnicalRecordsSteps;
import util.JsonPathAlteration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(SerenityRunner.class)
public class PutVehicleTechnicalRecords {

    @Steps
    VehicleTechnicalRecordsSteps vehicleTechnicalRecordsSteps;


    @Ignore("Remove the ignore annotation when the updates on tech record will be enabled for vehicles without adr details")
    @WithTag("Vtm")
    @Title("CVSB-7885 - AC6 - Can update all fields for a vehicle entry, except the VIN, partial VIN, trailer id, primary and secondary VRM " +
            "AC7 - Can not update VIN, partial vin or primary/secondary vrm " +
            "AC9 - Can add or remove tech records for a vehicle entry")
    public void testUpdateVehicleTechnicalRecord() {
        // TEST SETUP
        // generate random Vin
        String randomVin = GenericData.generateRandomVin();
        //generate random Vrm
        String randomVrm = GenericData.generateRandomVrm();
        // read post request body from file
        String postRequestBody = GenericData.readJsonValueFromFile("technical-records_hgv_all_fields.json","$");
        // read put request body from file
        String putRequestBody = GenericData.readJsonValueFromFile("technical-records_hgv_mandatory_fields.json","$");
        // read the tech record from the file used for post request body
        String oldTechRecord = GenericData.readJsonValueFromFile("technical-records_hgv_all_fields.json","$.techRecord[0]");
        // read the tech record from the file used for post request body
        String newTechRecord = GenericData.readJsonValueFromFile("technical-records_hgv_mandatory_fields.json","$.techRecord[0]");
        // create alteration to change Vin in the post request body with the random generated Vin
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin,"","REPLACE");
        // create alteration to change primary vrm in the request body with the random generated primary vrm
        JsonPathAlteration alterationVrm = new JsonPathAlteration("$.primaryVrm", randomVrm,"","REPLACE");
        // create alteration to add one more tech record to in the put request body
        JsonPathAlteration alterationAddTechRecord = new JsonPathAlteration("$.techRecord", oldTechRecord,"","ADD_VALUE");
        // initialize the alterations list with only the alterations for changing the Vin and the primary vrm
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(alterationVin, alterationVrm));

        // TEST
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBody, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(201);
        // validate AC6
        vehicleTechnicalRecordsSteps.putVehicleTechnicalRecordsForVehicle(randomVin, putRequestBody);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsByStatus(randomVin, VehicleTechnicalRecordStatus.ALL);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord.size()", 2);
        vehicleTechnicalRecordsSteps.validateResponseContainsJson("techRecord[0]", oldTechRecord);
        vehicleTechnicalRecordsSteps.validateResponseContainsJson("techRecord[1]", newTechRecord);
        // validate AC7
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("vin", randomVin);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("vrms[0].vrm", randomVrm);
        // add alteration for adding one more tech record for the randomVin used above
        alterations.add(alterationAddTechRecord);
        // validate AC9
        // add tech record to vehicle with random Vin
        vehicleTechnicalRecordsSteps.putVehicleTechnicalRecordsForVehicleWithAlterations(randomVin, putRequestBody, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsByStatus(randomVin, VehicleTechnicalRecordStatus.ALL);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord.size()", 2);
        vehicleTechnicalRecordsSteps.validateResponseContainsJson("techRecord[0]", newTechRecord);
        vehicleTechnicalRecordsSteps.validateResponseContainsJson("techRecord[1]", oldTechRecord);
        // remove tech record to vehicle with random Vin
        vehicleTechnicalRecordsSteps.putVehicleTechnicalRecordsForVehicle(randomVin, putRequestBody);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsByStatus(randomVin, VehicleTechnicalRecordStatus.ALL);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord.size()", 1);
        vehicleTechnicalRecordsSteps.validateResponseContainsJson("techRecord[0]", oldTechRecord);
    }

    @WithTag("Vtm")
    @Title("CVSB-8677 - AC1 - Add adrDetails object onto an existing tech record with status provisional " +
            "AC2 - Update adrDetails object on an existing tech record " +
            "AC3 - All attributes are returned " +
            "AC4 - Adding of adrDetails is audited " +
            "AC5 - Updating of adrDetails is audited")
    @Test
    public void testAddAdrDetailsObjectVehicleTechnicalRecord() {
        //TEST SETUP
        // generate random Vin
        String randomVin = GenericData.generateRandomVin();
        // generate random systemNumber
        String randomSystemNumber = GenericData.generateRandomSystemNumber();
        //generate random Vrm
        String randomVrm = GenericData.generateRandomVrm();
        // read post request body from file
        String requestBodyHgv = GenericData.readJsonValueFromFile("technical-records_hgv_all_fields.json","$");
        // read the adr details from the file used for put request body with battery adr details
        String adrDetailsBattery = GenericData.readJsonValueFromFile("technical-records_adr_details_battery.json","$.techRecord[0].adrDetails");
        // create alteration to change Vin in the post request body with the random generated Vin
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin,"","REPLACE");
        // create alteration to change systemNumber in the post request body with the random generated systemNumber
        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$.systemNumber", randomSystemNumber,"","REPLACE");
        // create alteration to change primary vrm in the request body with the random generated primary vrm
        JsonPathAlteration alterationVrm = new JsonPathAlteration("$.primaryVrm", randomVrm,"","REPLACE");
        // initialize the alterations list with only the alterations for changing the Vin and the primary vrm
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(alterationVin, alterationVrm, alterationSystemNumber));
        // read the adr details from the file used for put request body with tank adr details
        String adrDetailsTank = GenericData.readJsonValueFromFile("technical-records_adr_details_tank.json","$.techRecord[0].adrDetails");

        //TEST
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(requestBodyHgv, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(201);
        // create alteration to add adr details
        JsonPathAlteration alterationAdrDetails = new JsonPathAlteration("$.techRecord[0]", adrDetailsBattery,"adrDetails","ADD_FIELD");
        alterations.add(alterationAdrDetails);
        // validate AC1
        vehicleTechnicalRecordsSteps.putVehicleTechnicalRecordsForVehicleWithAlterations(randomVin, requestBodyHgv, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsByStatus(randomVin, VehicleTechnicalRecordStatus.ALL);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord.size()", 2);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].statusCode", "archived");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].updateType", "adrUpdate");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].lastUpdatedByName", "sean");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].lastUpdatedById", "12345");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[1].statusCode", "provisional");
        vehicleTechnicalRecordsSteps.valuesForFieldsInPathShouldEqual("[0].techRecord[0].lastUpdatedAt","[0].techRecord[1].createdAt");
        vehicleTechnicalRecordsSteps.fieldInPathShouldExist("[0].techRecord[1]", "adrDetails");
        // validated AC3
        vehicleTechnicalRecordsSteps.validateResponseContainsJson("[0].techRecord[1].adrDetails", adrDetailsBattery);
        // Validate AC4
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[1].createdByName", "sean");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[1].createdById", "12345");
        // Validate AC2
        alterationAdrDetails = new JsonPathAlteration("$.techRecord[0]", adrDetailsTank,"adrDetails","ADD_FIELD");
        alterations.remove(alterations.size()-1);
        alterations.add(alterationAdrDetails);
        vehicleTechnicalRecordsSteps.putVehicleTechnicalRecordsForVehicleWithAlterations(randomVin, requestBodyHgv, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsByStatus(randomVin, VehicleTechnicalRecordStatus.ALL);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord.size()", 3);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].statusCode", "archived");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[1].statusCode", "archived");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[1].updateType", "adrUpdate");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[1].lastUpdatedByName", "sean");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[1].lastUpdatedById", "12345");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[2].statusCode", "provisional");
        vehicleTechnicalRecordsSteps.valuesForFieldsInPathShouldEqual("[0].techRecord[1].lastUpdatedAt","[0].techRecord[2].createdAt");
        // validate AC5
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[2].createdByName", "sean");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[2].createdById", "12345");
        vehicleTechnicalRecordsSteps.validateResponseContainsJson("[0].techRecord[2].adrDetails", adrDetailsTank);
    }

    @Ignore("No longer a valid test")
    @Title("CVSB-9317 - AC1 - Document is attached to the ADR record" +
            "AC2 - Document is removed from the ADR record")
    public void testAddAdrDetailsUploadDocumentAdrRecord() {
        //TEST SETUP
        // generate random Vin
        String randomVin = GenericData.generateRandomVin();
        //generate random Vrm
        String randomVrm = GenericData.generateRandomVrm();
        // read post request body from file
        String requestBodyHgv = GenericData.readJsonValueFromFile("technical-records_hgv_all_fields.json","$");
        // read the adr details from the file used for put request body with battery adr details
        String adrDetailsBattery = GenericData.readJsonValueFromFile("technical-records_adr_details_battery.json","$.techRecord[0].adrDetails");
        // create alteration to change Vin in the post request body with the random generated Vin
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin,"","REPLACE");
        // create alteration to change primary vrm in the request body with the random generated primary vrm
        JsonPathAlteration alterationVrm = new JsonPathAlteration("$.primaryVrm", randomVrm,"","REPLACE");
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(alterationVin, alterationVrm));
        // get pdf content as base64 encoded string
        String encodedFileContent = GenericData.readBytesFromFile("sample.pdf");
        // create alteration to add files field in the request body as array with an element the previously encoded string
        JsonPathAlteration alterationAddFiles = new JsonPathAlteration("$","[" + encodedFileContent + "]","files","ADD_FIELD");
        JsonPathAlteration alterationRemoveFiles = new JsonPathAlteration("$.techRecord[0].adrDetails.documents","[]","","REPLACE");

        //TEST
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(requestBodyHgv, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(201);
        // create alteration to add adr details
        JsonPathAlteration alterationAdrDetails = new JsonPathAlteration("$.techRecord[0]", adrDetailsBattery,"adrDetails","ADD_FIELD");
        alterations.add(alterationAdrDetails);
        alterations.add(alterationAddFiles);
        // validate AC1
        vehicleTechnicalRecordsSteps.putVehicleTechnicalRecordsForVehicleWithAlterations(randomVin, requestBodyHgv, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord.size()", 2);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[1].adrDetails.documents.size()", 2);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldEndWith("techRecord[1].adrDetails.documents[1]", ".pdf");
        // validate AC2
        alterations.remove(alterations.size()-1);
        alterations.add(alterationRemoveFiles);
        vehicleTechnicalRecordsSteps.putVehicleTechnicalRecordsForVehicleWithAlterations(randomVin, requestBodyHgv, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord.size()", 3);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[2].adrDetails.documents.size()", 0);
    }

    @WithTag("Vtm")
    @Title("CVSB-10214 - AC2 - Vehicle class code is auto-populated when updating a hgv " +
            "AC3 - Body type code is auto-populated when updating a hgv")
    @Test
    public void testUpdateVehicleHgvAutoPopulateFields() {
        // TEST SETUP
        //generate random Vin
        String randomVin = GenericData.generateRandomVin();
        //generate random systemNumber
        String randomSystemNumber = GenericData.generateRandomVin();
        //generate random Vrm
        String randomVrm = GenericData.generateRandomVrm();
        // read post request body from file
        String requestBodyHgv = GenericData.readJsonValueFromFile("technical-records_hgv_all_fields.json","$");
        // create alteration to change Vin in the request body with the random generated Vin
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin,"","REPLACE");
        // create alteration to change systemNumber in the request body with the random generated systemNumber
        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$.systemNumber", randomSystemNumber,"","REPLACE");
        // create alteration to change primary vrm in the request body with the random generated primary vrm
        JsonPathAlteration alterationVrm = new JsonPathAlteration("$.primaryVrm", randomVrm,"","REPLACE");
        // initialize the alterations list with both declared alteration
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(alterationVin, alterationVrm, alterationSystemNumber));

        // TEST
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(requestBodyHgv, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(201);
        // create alteration to force change to vehicle class code by changing vehicle class description
        JsonPathAlteration alterationVehicleClassDescription = new JsonPathAlteration("$.techRecord[0].vehicleClass.description", "not applicable","","REPLACE");
        alterations.add(alterationVehicleClassDescription);
        JsonPathAlteration alterationVehicleClassCode = new JsonPathAlteration("$.techRecord[0].vehicleClass", "y","code","ADD_FIELD");
        alterations.add(alterationVehicleClassCode);
        // create alteration to force change to body type code by changing body type description
        JsonPathAlteration alterationBodyTypeDescription = new JsonPathAlteration("$.techRecord[0].bodyType.description", "articulated","","REPLACE");
        alterations.add(alterationBodyTypeDescription);
        JsonPathAlteration alterationBodyTypeCode = new JsonPathAlteration("$.techRecord[0].bodyType", "y","code","ADD_FIELD");
        alterations.add(alterationBodyTypeCode);
        vehicleTechnicalRecordsSteps.putVehicleTechnicalRecordsForVehicleWithAlterations(randomVin, requestBodyHgv, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord.size()", 2);
        // validate AC2
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[1].vehicleClass.code", "n");
        // validated AC3
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[1].bodyType.code", "a");
    }

    @WithTag("Vtm")
    @Title("CVSB-11487 - AC1 - JSON document contains 'nulls' for all not applicable fields for non battery/tank " +
            "AC2 - JSON document contains 'nulls' for all optional fields for non battery/tank")
    @Test
    public void testValidateAdrDetailsNullsOptionalNotApplicableFieldsNonTankBattery() {
        // TEST SETUP
        // generate random Vin
        String randomVin = GenericData.generateRandomVin();
        // generate random Vrm
        String randomVrm = GenericData.generateRandomVrm();
        // read post request body from file
        String requestBody = GenericData.readJsonValueFromFile("technical-records_hgv_all_fields.json", "$");
        // read the adr details from the file used for put request body with battery adr details
        String adrDetails = GenericData.readJsonValueFromFile("technical-records_adr_details_other_nulls.json","$.techRecord[0].adrDetails");
        // create alteration to change Vin in the post request body with the random generated Vin
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        // create alteration to change primary vrm in the request body with the random generated primary vrm
        JsonPathAlteration alterationVrm = new JsonPathAlteration("$.primaryVrm", randomVrm, "", "REPLACE");
        // initialize the alterations list with only the alterations for changing the Vin and the primary vrm
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(alterationVin, alterationVrm));

        //TEST
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(requestBody, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(201);
        // Validate AC1 + AC2
        JsonPathAlteration alterationAddAdrDetails = new JsonPathAlteration("$.techRecord[0]", adrDetails,"adrDetails","ADD_FIELD");
        alterations.add(alterationAddAdrDetails);
        vehicleTechnicalRecordsSteps.putVehicleTechnicalRecordsForVehicleWithAlterations(randomVin, requestBody, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.validateResponseContainsJson("techRecord[1].adrDetails", adrDetails);
    }

    @WithTag("Vtm")
    @Title("CVSB-11487 - AC1 - JSON document contains 'nulls' for all not applicable fields for battery " +
            "AC2 - JSON document contains 'nulls' for all optional fields for battery")
    @Test
    public void testValidateAdrDetailsNullsOptionalNotApplicableFieldsBattery() {
        // TEST SETUP
        // generate random Vin
        String randomVin = GenericData.generateRandomVin();
        // generate random Vrm
        String randomVrm = GenericData.generateRandomVrm();
        // read post request body from file
        String requestBody = GenericData.readJsonValueFromFile("technical-records_hgv_all_fields.json", "$");
        // read the adr details from the file used for put request body with battery adr details
        String adrDetails = GenericData.readJsonValueFromFile("technical-records_adr_details_battery_nulls.json","$.techRecord[0].adrDetails");
        // create alteration to change Vin in the post request body with the random generated Vin
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        // create alteration to change primary vrm in the request body with the random generated primary vrm
        JsonPathAlteration alterationVrm = new JsonPathAlteration("$.primaryVrm", randomVrm, "", "REPLACE");
        // initialize the alterations list with only the alterations for changing the Vin and the primary vrm
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(alterationVin, alterationVrm));

        //TEST
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(requestBody, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(201);
        // Validate AC1 + AC2
        JsonPathAlteration alterationAddAdrDetails = new JsonPathAlteration("$.techRecord[0]", adrDetails,"adrDetails","ADD_FIELD");
        alterations.add(alterationAddAdrDetails);
        vehicleTechnicalRecordsSteps.putVehicleTechnicalRecordsForVehicleWithAlterations(randomVin, requestBody, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.validateResponseContainsJson("techRecord[1].adrDetails", adrDetails);
    }

    @WithTag("Vtm")
    @Title("CVSB-11487 - AC1 - JSON document contains 'nulls' for all not applicable fields for tank " +
            "AC2 - JSON document contains 'nulls' for all optional fields for tank")
    @Test
    public void testValidateAdrDetailsNullsOptionalNotApplicableFieldsTank() {
        // TEST SETUP
        // generate random Vin
        String randomVin = GenericData.generateRandomVin();
        // generate random Vrm
        String randomVrm = GenericData.generateRandomVrm();
        // read post request body from file
        String requestBody = GenericData.readJsonValueFromFile("technical-records_hgv_all_fields.json", "$");
        // read the adr details from the file used for put request body with battery adr details
        String adrDetails = GenericData.readJsonValueFromFile("technical-records_adr_details_tank_nulls.json", "$.techRecord[0].adrDetails");
        // create alteration to change Vin in the post request body with the random generated Vin
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        // create alteration to change primary vrm in the request body with the random generated primary vrm
        JsonPathAlteration alterationVrm = new JsonPathAlteration("$.primaryVrm", randomVrm, "", "REPLACE");
        // initialize the alterations list with only the alterations for changing the Vin and the primary vrm
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(alterationVin, alterationVrm));

        //TEST
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(requestBody, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(201);
        // Validate AC1 + AC2
        JsonPathAlteration alterationAddAdrDetails = new JsonPathAlteration("$.techRecord[0]", adrDetails,"adrDetails","ADD_FIELD");
        alterations.add(alterationAddAdrDetails);
        vehicleTechnicalRecordsSteps.putVehicleTechnicalRecordsForVehicleWithAlterations(randomVin, requestBody, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.validateResponseContainsJson("techRecord[1].adrDetails", adrDetails);
    }

    @WithTag("Vtm")
    @Title("CVSB-10209 - AC3 - HGV vehicle is updated, and the appropriate attributes are automatically set")
    @Test
    public void testVehicleTechnicalRecordsUpdateAllHgvAttributes() {
        // TEST SETUP
        // generate random Vin
        String randomVin = GenericData.generateRandomVin();
        // generate random systemNumber
        String randomSystemNumber = GenericData.generateRandomSystemNumber();
        // generate random Vrm
        String randomVrm = GenericData.generateRandomVrm();
        // read post request body from file
        String postRequestBodyHgv = GenericData.readJsonValueFromFile("technical-records_hgv_all_fields.json", "$");
        // create alteration to change Vin in the post request body with the random generated Vin
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        // create alteration to change systemNumber in the post request body with the random generated systemNumber
        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$.systemNumber", randomSystemNumber, "", "REPLACE");
        // create alteration to change primary vrm in the request body with the random generated primary vrm
        JsonPathAlteration alterationVrm = new JsonPathAlteration("$.primaryVrm", randomVrm, "", "REPLACE");

        // initialize the alterations list with both declared alterations
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(alterationVin, alterationVrm, alterationSystemNumber));

        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBodyHgv, alterations);

        String putRequestBodyHgv = GenericData.readJsonValueFromFile("technical-records_hgv_all_fields_put.json", "$");
        String techRecord = GenericData.readJsonValueFromFile("technical-records_hgv_all_fields_put.json", "$.techRecord[0]");
        vehicleTechnicalRecordsSteps.putVehicleTechnicalRecordsForVehicle(randomVin, putRequestBodyHgv);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsByStatus(randomVin, VehicleTechnicalRecordStatus.ALL);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord.size()", 2);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].statusCode", "archived");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[1].statusCode", "provisional");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[1].axles.size()", 1);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].lastUpdatedById", "123456");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].lastUpdatedByName", "dragos");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].updateType", "techRecordUpdate");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[1].createdById", "123456");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[1].createdByName", "dragos");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[1].vehicleClass.code", "t");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[1].bodyType.code", "s");
        vehicleTechnicalRecordsSteps.validateResponseContainsJson("[0].techRecord.find { it.statusCode == 'provisional' }", techRecord);
    }

    @WithTag("Vtm")
    @Title("CVSB-10813 - AC1 - PSV vehicle is updated, and the appropriate attributes are automatically set")
    @Test
    public void testVehicleTechnicalRecordsUpdateAllPsvAttributes() {
        // TEST SETUP
        // generate random Vin
        String randomVin = GenericData.generateRandomVin();
        // generate random systemNumber
        String randomSystemNumber = GenericData.generateRandomSystemNumber();
        // generate random Vrm
        String randomVrm = GenericData.generateRandomVrm();
        // read post request body from file
        String postRequestBodyPsv = GenericData.readJsonValueFromFile("technical-records_psv_all_fields.json", "$");
        // create alteration to change Vin in the post request body with the random generated Vin
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        // create alteration to change systemNumber in the post request body with the random generated systemNumber
        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$.systemNumber", randomSystemNumber, "", "REPLACE");
        // create alteration to change primary vrm in the request body with the random generated primary vrm
        JsonPathAlteration alterationVrm = new JsonPathAlteration("$.primaryVrm", randomVrm, "", "REPLACE");

        // initialize the alterations list with both declared alterations
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(alterationVin, alterationVrm, alterationSystemNumber));

        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBodyPsv, alterations);

        String putRequestBodyPsv = GenericData.readJsonValueFromFile("technical-records_psv_all_fields_put.json", "$");
        String techRecord = GenericData.readJsonValueFromFile("technical-records_psv_all_fields_put.json", "$.techRecord[0]");
        vehicleTechnicalRecordsSteps.putVehicleTechnicalRecordsForVehicle(randomVin, putRequestBodyPsv);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsByStatus(randomVin, VehicleTechnicalRecordStatus.ALL);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord.size()", 2);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].statusCode", "archived");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[1].statusCode", "provisional");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[1].axles.size()", 1);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].lastUpdatedById", "123456");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].lastUpdatedByName", "dragos");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].updateType", "techRecordUpdate");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[1].createdById", "123456");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[1].createdByName", "dragos");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[1].vehicleClass.code", "t");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[1].bodyType.code", "s");
        vehicleTechnicalRecordsSteps.validateResponseContainsJson("[0].techRecord.find { it.statusCode == 'provisional' }", techRecord);
    }

    @WithTag("Vtm")
    @Title("CVSB-10244 - AC2 - Vehicle class code is auto-populated when updating a psv " +
            "AC3 - Body type code is auto-populated when updating a psv " +
            "AC4 - Brake code fields are auto-populated")
    @Test
    public void testUpdateVehiclePsvAutoPopulateFields() {
        // TEST SETUP
        //generate random Vin
        String randomVin = GenericData.generateRandomVin();
        //generate random systemNumber
        String randomSystemNumber = GenericData.generateRandomSystemNumber();
        //generate random Vrm
        String randomVrm = GenericData.generateRandomVrm();
        // read post request body from file
        String requestBodyPsv = GenericData.readJsonValueFromFile("technical-records_psv_all_fields.json","$");
        // create alteration to change Vin in the request body with the random generated Vin
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin,"","REPLACE");
        // create alteration to change systemNumber in the request body with the random generated systemNumber
        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$.systemNumber", randomSystemNumber,"","REPLACE");
        // create alteration to change primary vrm in the request body with the random generated primary vrm
        JsonPathAlteration alterationVrm = new JsonPathAlteration("$.primaryVrm", randomVrm,"","REPLACE");
        // initialize the alterations list with both declared alteration
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(alterationVin, alterationVrm, alterationSystemNumber));

        // TEST
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(requestBodyPsv, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(201);
        // create alteration to force change to vehicle class code by changing vehicle class description
        JsonPathAlteration alterationVehicleClassDescription = new JsonPathAlteration("$.techRecord[0].vehicleClass.description", "not applicable","","REPLACE");
        alterations.add(alterationVehicleClassDescription);
        JsonPathAlteration alterationVehicleClassCode = new JsonPathAlteration("$.techRecord[0].vehicleClass", "y","code","ADD_FIELD");
        alterations.add(alterationVehicleClassCode);
        // create alteration to force change to body type code by changing body type description
        JsonPathAlteration alterationBodyTypeDescription = new JsonPathAlteration("$.techRecord[0].bodyType.description", "articulated","","REPLACE");
        alterations.add(alterationBodyTypeDescription);
        JsonPathAlteration alterationBodyTypeCode = new JsonPathAlteration("$.techRecord[0].bodyType", "y","code","ADD_FIELD");
        alterations.add(alterationBodyTypeCode);
        // create alteration to force change to body type code by changing body type description
        JsonPathAlteration alterationBrakeCode = new JsonPathAlteration("$.techRecord[0].brakes.brakeCode", "123456","","REPLACE");
        alterations.add(alterationBrakeCode);
        vehicleTechnicalRecordsSteps.putVehicleTechnicalRecordsForVehicleWithAlterations(randomVin, requestBodyPsv, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord.size()", 2);
        // validate AC2
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[1].vehicleClass.code", "n");
        // validated AC3
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[1].bodyType.code", "a");
        // validated AC4
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[1].brakeCode", "123456");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[1].brakes.brakeCodeOriginal",
                "456");
    }
}
