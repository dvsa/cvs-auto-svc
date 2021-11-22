package vehicle;

import data.GenericData;
import model.vehicles.VehicleTechnicalRecordSearchCriteria;
import model.vehicles.VehicleTechnicalRecordStatus;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import net.thucydides.core.annotations.WithTag;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.http.HttpStatus;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.Ignore;
import steps.TestResultsSteps;
import steps.VehicleTechnicalRecordsSteps;
import util.JsonPathAlteration;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;


@RunWith(SerenityRunner.class)
@WithTag("In_test")
public class PutVehicleTechnicalRecords {

    @Steps
    VehicleTechnicalRecordsSteps vehicleTechnicalRecordsSteps;

    @Steps
    TestResultsSteps testResultsSteps;


    Date date  = new Date();

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
        // initialize the alterations list with only the alterations for changing the Vin and the primary vrm
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(alterationVin, alterationVrm));
        // read the adr details from the file used for put request body with tank adr details
        String adrDetailsTank = GenericData.readJsonValueFromFile("technical-records_adr_details_tank.json","$.techRecord[0].adrDetails");

        //TEST
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(requestBodyHgv, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(201);
        String systemNumber = vehicleTechnicalRecordsSteps.getSystemNumberUsingVin(randomVin);

        JsonPathAlteration alterationAStatusCode = new JsonPathAlteration("$.techRecord[0]", "provisional","statusCode","ADD_FIELD");
        alterations.add(alterationAStatusCode);

        // create alteration to add adr details
        JsonPathAlteration alterationAdrDetails = new JsonPathAlteration("$.techRecord[0]", adrDetailsBattery,"adrDetails","ADD_FIELD");
        alterations.add(alterationAdrDetails);

        // validate AC1
        vehicleTechnicalRecordsSteps.putVehicleTechnicalRecordsForVehicleWithAlterations(systemNumber, requestBodyHgv, alterations);
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
        alterationAdrDetails = new JsonPathAlteration("$.techRecord[0]", adrDetailsTank,"adrDetails","ADD_FIELD");
        alterations.remove(alterations.size()-1);
        alterations.add(alterationAdrDetails);
        vehicleTechnicalRecordsSteps.putVehicleTechnicalRecordsForVehicleWithAlterations(systemNumber, requestBodyHgv, alterations);
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
        //generate random Vrm
        String randomVrm = GenericData.generateRandomVrm();
        // read post request body from file
        String requestBodyHgv = GenericData.readJsonValueFromFile("technical-records_hgv_all_fields.json","$");
        // create alteration to change Vin in the request body with the random generated Vin
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin,"","REPLACE");
        // create alteration to change primary vrm in the request body with the random generated primary vrm
        JsonPathAlteration alterationVrm = new JsonPathAlteration("$.primaryVrm", randomVrm,"","REPLACE");
        // initialize the alterations list with both declared alteration
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(alterationVin, alterationVrm));

        // TEST
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(requestBodyHgv, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(201);
        String systemNumber = vehicleTechnicalRecordsSteps.getSystemNumberUsingVin(randomVin);

        // create alteration to force change to vehicle class code by changing vehicle class description
        JsonPathAlteration alterationVehicleClassDescription = new JsonPathAlteration("$.techRecord[0].vehicleClass.description", "not applicable","","REPLACE");
        alterations.add(alterationVehicleClassDescription);
        JsonPathAlteration alterationVehicleClassCode = new JsonPathAlteration("$.techRecord[0].vehicleClass", "y","code","ADD_FIELD");
        alterations.add(alterationVehicleClassCode);
        JsonPathAlteration alterationStatusCode = new JsonPathAlteration("$.techRecord[0]", "provisional","statusCode","ADD_FIELD");
        alterations.add(alterationStatusCode);
        // create alteration to force change to body type code by changing body type description
        JsonPathAlteration alterationBodyTypeDescription = new JsonPathAlteration("$.techRecord[0].bodyType.description", "articulated","","REPLACE");
        alterations.add(alterationBodyTypeDescription);
        JsonPathAlteration alterationBodyTypeCode = new JsonPathAlteration("$.techRecord[0].bodyType", "y","code","ADD_FIELD");
        alterations.add(alterationBodyTypeCode);
        vehicleTechnicalRecordsSteps.putVehicleTechnicalRecordsForVehicleWithAlterations(systemNumber, requestBodyHgv, alterations);
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
        String systemNumber = vehicleTechnicalRecordsSteps.getSystemNumberUsingVin(randomVin);

        // Validate AC1 + AC2
        JsonPathAlteration alterationAddAdrDetails = new JsonPathAlteration("$.techRecord[0]", adrDetails,"adrDetails","ADD_FIELD");
        alterations.add(alterationAddAdrDetails);
        JsonPathAlteration alterationStatusCode = new JsonPathAlteration("$.techRecord[0]", "provisional","statusCode","ADD_FIELD");
        alterations.add(alterationStatusCode);
        vehicleTechnicalRecordsSteps.putVehicleTechnicalRecordsForVehicleWithAlterations(systemNumber, requestBody, alterations);
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
        String systemNumber = vehicleTechnicalRecordsSteps.getSystemNumberUsingVin(randomVin);

        // Validate AC1 + AC2
        JsonPathAlteration alterationAddAdrDetails = new JsonPathAlteration("$.techRecord[0]", adrDetails,"adrDetails","ADD_FIELD");
        alterations.add(alterationAddAdrDetails);
        JsonPathAlteration alterationStatusCode = new JsonPathAlteration("$.techRecord[0]", "provisional","statusCode","ADD_FIELD");
        alterations.add(alterationStatusCode);

        vehicleTechnicalRecordsSteps.putVehicleTechnicalRecordsForVehicleWithAlterations(systemNumber, requestBody, alterations);
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
        String systemNumber = vehicleTechnicalRecordsSteps.getSystemNumberUsingVin(randomVin);

        // Validate AC1 + AC2
        JsonPathAlteration alterationAddAdrDetails = new JsonPathAlteration("$.techRecord[0]", adrDetails,"adrDetails","ADD_FIELD");
        alterations.add(alterationAddAdrDetails);
        JsonPathAlteration alterationAddStatusCode = new JsonPathAlteration("$.techRecord[0]", "provisional","statusCode","ADD_FIELD");
        alterations.add(alterationAddStatusCode);
        vehicleTechnicalRecordsSteps.putVehicleTechnicalRecordsForVehicleWithAlterations(systemNumber, requestBody, alterations);
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
        // generate random Vrm
        String randomVrm = GenericData.generateRandomVrm();
        // read post request body from file
        String postRequestBodyHgv = GenericData.readJsonValueFromFile("technical-records_hgv_all_fields.json", "$");
        // create alteration to change Vin in the post request body with the random generated Vin
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        // create alteration to change primary vrm in the request body with the random generated primary vrm
        JsonPathAlteration alterationVrm = new JsonPathAlteration("$.primaryVrm", randomVrm, "", "REPLACE");

        // initialize the alterations list with both declared alterations
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(alterationVin, alterationVrm));

        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBodyHgv, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(201);
        String systemNumber = vehicleTechnicalRecordsSteps.getSystemNumberUsingVin(randomVin);

        String putRequestBodyHgv = GenericData.readJsonValueFromFile("technical-records_hgv_all_fields_put.json", "$");
        String techRecord = GenericData.readJsonValueFromFile("technical-records_hgv_all_fields_put.json", "$.techRecord[0]");
        vehicleTechnicalRecordsSteps.putVehicleTechnicalRecordsForVehicleWithAlterations(systemNumber, putRequestBodyHgv, alterations);
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
        // generate random Vrm
        String randomVrm = GenericData.generateRandomVrm();
        // read post request body from file
        String postRequestBodyPsv = GenericData.readJsonValueFromFile("technical-records_psv_all_fields.json", "$");
        // create alteration to change Vin in the post request body with the random generated Vin
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        // create alteration to change primary vrm in the request body with the random generated primary vrm
        JsonPathAlteration alterationVrm = new JsonPathAlteration("$.primaryVrm", randomVrm, "", "REPLACE");

        // initialize the alterations list with both declared alterations
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(alterationVin, alterationVrm));

        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBodyPsv, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(201);
        String systemNumber = vehicleTechnicalRecordsSteps.getSystemNumberUsingVin(randomVin);

        String putRequestBodyPsv = GenericData.readJsonValueFromFile("technical-records_psv_all_fields_put.json", "$");
        String techRecord = GenericData.readJsonValueFromFile("technical-records_psv_all_fields_put.json", "$.techRecord[0]");
        vehicleTechnicalRecordsSteps.putVehicleTechnicalRecordsForVehicleWithAlterations(systemNumber, putRequestBodyPsv, alterations);
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

        String alteredTechRecord = GenericData.applyJsonAlterations(techRecord, alterations);
        vehicleTechnicalRecordsSteps.validateResponseContainsJson("[0].techRecord.find { it.statusCode == 'provisional' }", alteredTechRecord);
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
        //generate random Vrm
        String randomVrm = GenericData.generateRandomVrm();
        // read post request body from file
        String requestBodyPsv = GenericData.readJsonValueFromFile("technical-records_psv_all_fields.json","$");
        // create alteration to change Vin in the request body with the random generated Vin
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin,"","REPLACE");
        // create alteration to change primary vrm in the request body with the random generated primary vrm
        JsonPathAlteration alterationVrm = new JsonPathAlteration("$.primaryVrm", randomVrm,"","REPLACE");
        // initialize the alterations list with both declared alteration
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(alterationVin, alterationVrm));

        // TEST
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(requestBodyPsv, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(201);
        String systemNumber = vehicleTechnicalRecordsSteps.getSystemNumberUsingVin(randomVin);

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
        JsonPathAlteration alterationStatusCode = new JsonPathAlteration("$.techRecord[0]", "provisional","statusCode","ADD_FIELD");
        alterations.add(alterationStatusCode);
        vehicleTechnicalRecordsSteps.putVehicleTechnicalRecordsForVehicleWithAlterations(systemNumber, requestBodyPsv, alterations);
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

    @Title("CVSB-10478 - AC1 - Vehicles PUT endpoint is updated to now use systemNumber, instead of Vin")
    @Test
    public void testVehicleTechnicalRecords_Put() {
        // TEST SETUP
        // generate random Vin
        String randomVin = GenericData.generateRandomVin();
        // generate random Vrm
        String randomVrm = GenericData.generateRandomVrm();
        // read post request body from file
        String postRequestBodyHgv = GenericData.readJsonValueFromFile("technical-records_hgv_all_fields.json", "$");
        // create alteration to change Vin in the post request body with the random generated Vin
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        // create alteration to change primary vrm in the request body with the random generated primary vrm
        JsonPathAlteration alterationVrm = new JsonPathAlteration("$.primaryVrm", randomVrm, "", "REPLACE");

        // initialize the alterations list with both declared alterations
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(alterationVin, alterationVrm));

        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBodyHgv, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(201);

        // Retrieve the systemNumber of the newly created record, for use in the update.
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecords(randomVin);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].bodyType.description", "other");
        String systemNumber = vehicleTechnicalRecordsSteps.getValueFromBody("[0].systemNumber");

        String putRequestBodyHgv = GenericData.readJsonValueFromFile("technical-records_hgv_all_fields_put.json", "$");
        vehicleTechnicalRecordsSteps.putVehicleTechnicalRecordsForVehicleWithAlterations(systemNumber, putRequestBodyHgv, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);

        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecords(randomVin);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].bodyType.description", "single decker");
    }

    @Title("CVSB-10478 - AC4 - PUT: Permit the PUT of a primaryVrm which has special characters")
    @Test
    public void testVehicleTechnicalRecords_Put_SpecialCharacters_VRM() {
        // TEST SETUP
        String specialCharacters = "/ \\*-";

        // generate random Vin
        String randomVin = GenericData.generateRandomVin();
        // generate random Vrm
        String randomVrm = GenericData.generateRandomVrm();
        // read post request body from file
        String postRequestBodyHgv = GenericData.readJsonValueFromFile("technical-records_hgv_all_fields.json", "$");
        // create alteration to change Vin in the post request body with the random generated Vin
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        // create alteration to change primary vrm in the request body with the random generated primary vrm
        JsonPathAlteration alterationVrm = new JsonPathAlteration("$.primaryVrm", randomVrm, "", "REPLACE");

        // initialize the alterations list with both declared alterations
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(alterationVin, alterationVrm));

        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBodyHgv, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(201);

        // Retrieve the systemNumber of the newly created record, for use in the update.
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecords(randomVin);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        String systemNumber = vehicleTechnicalRecordsSteps.getValueFromBody("[0].systemNumber");

        // PUT the update record.
        String putRequestBodyHgv = GenericData.readJsonValueFromFile("technical-records_hgv_all_fields_put.json", "$");
        String specialCharsVrm = RandomStringUtils.randomAlphanumeric(4,4) + specialCharacters;
        JsonPathAlteration alterationVrm2 = new JsonPathAlteration("$.primaryVrm", specialCharsVrm, "", "REPLACE");
        alterations = new ArrayList<>(Arrays.asList(alterationVin, alterationVrm2));
        vehicleTechnicalRecordsSteps.putVehicleTechnicalRecordsForVehicleWithAlterations(systemNumber, putRequestBodyHgv, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);

        // Verify the updated record contents.
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecords(randomVin);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].vrms[0].vrm", specialCharsVrm.toUpperCase());
    }

    @Title("CVSB-10478 - AC5 - PUT - Permit the PUT of a trailerId which has special characters")
    @Test
    public void testVehicleTechnicalRecords_Put_SpecialCharacters_TrailerId() {
        // TEST SETUP
        String specialCharacters = "/ \\*-";

        // generate random Vin
        String randomVin = GenericData.generateRandomVin();
        // generate random Vrm
        String randomVrm = GenericData.generateRandomVrm();
        // read post request body from file
        String postRequestBodyTrl = GenericData.readJsonValueFromFile("technical-records_hgv_all_fields.json", "$");
        // create alteration to change Vin in the post request body with the random generated Vin
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        // create alteration to change primary vrm in the request body with the random generated primary vrm
        JsonPathAlteration alterationVrm = new JsonPathAlteration("$.primaryVrm", randomVrm, "", "REPLACE");

        // initialize the alterations list with both declared alterations
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(alterationVin, alterationVrm));

        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBodyTrl, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(201);

        // Retrieve the systemNumber of the newly created record, for use in the update.
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecords(randomVin);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        String systemNumber = vehicleTechnicalRecordsSteps.getValueFromBody("[0].systemNumber");

        // PUT the update record.
        String putRequestBodyTrl = GenericData.readJsonValueFromFile("technical-records_trl_mandatory_fields_put.json", "$");
        String specialCharsTrailerId = RandomStringUtils.randomAlphanumeric(3,3) + specialCharacters;
        JsonPathAlteration alterationTrailerId = new JsonPathAlteration("$.trailerId", specialCharsTrailerId, "", "REPLACE");
        alterations = new ArrayList<>(Arrays.asList(alterationVin, alterationTrailerId));
        vehicleTechnicalRecordsSteps.putVehicleTechnicalRecordsForVehicleWithAlterations(systemNumber, putRequestBodyTrl, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);

        // Verify the updated record contents.
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecords(randomVin);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].trailerId", specialCharsTrailerId.toUpperCase());
    }

    @Title("CVSB-10478 - AC6 - PUT - primaryVrm is updated to a primaryVrm which DOES NOT currently exist in DynamoDB")
    @Test
    public void testVehicleTechnicalRecords_Put_PrimaryVrmUpdates_NotExisting() {
        // TEST SETUP
        // generate random Vin
        String randomVin = GenericData.generateRandomVin();
        // generate random Vrm 1 and 2
        String randomVrm1 = GenericData.generateRandomVrm();
        String randomVrm2 = GenericData.generateRandomVrm();

        // read post request body from file
        String postRequestBodyHgv = GenericData.readJsonValueFromFile("technical-records_hgv_all_fields.json", "$");
        // create alteration to change Vin in the post request body with the random generated Vin
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        // create alteration to change primary vrm in the request body with the random generated primary vrm
        JsonPathAlteration alterationVrm = new JsonPathAlteration("$.primaryVrm", randomVrm1, "", "REPLACE");
        JsonPathAlteration alternationNotes = new JsonPathAlteration("$.techRecord[0].notes", "Tech record v1", "", "REPLACE");

        // initialize the alterations list with both declared alterations
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(alterationVin, alterationVrm, alternationNotes));

        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBodyHgv, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(201);

        // Retrieve the systemNumber of the newly created record, for use in the update.
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecords(randomVin);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        String systemNumber = vehicleTechnicalRecordsSteps.getValueFromBody("[0].systemNumber");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].statusCode", "provisional");

        // PUT the update record.
        String putRequestBodyHgv = GenericData.readJsonValueFromFile("technical-records_hgv_all_fields_put.json", "$");
        JsonPathAlteration alterationNotes2 = new JsonPathAlteration("$.techRecord[0].notes", "Tech record v2", "", "REPLACE");
        JsonPathAlteration alterationVrm2 = new JsonPathAlteration("$.primaryVrm", randomVrm2, "", "REPLACE");
        alterations = new ArrayList<>(Arrays.asList(alterationVin, alterationVrm2, alterationNotes2));
        vehicleTechnicalRecordsSteps.putVehicleTechnicalRecordsForVehicleWithAlterations(systemNumber, putRequestBodyHgv, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);

        // Verify that the record is updated with the new VRM (at vehicle level).
        // Verify the updated record contents.
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecords(randomVin);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].notes", "Tech record v2");
        vehicleTechnicalRecordsSteps.validateResponseContainsJson("[0].vrms[0]", "{\"vrm\": \"" + randomVrm2 + "\", \"isPrimary\": true}");

        // Verify that the original VRM is now in the secondaryVrm[] array.
        vehicleTechnicalRecordsSteps.validateResponseContainsJson("[0].vrms[2]", "{\"vrm\": \"" + randomVrm1 + "\", \"isPrimary\": false}");

        // Verify that the new record is there with the same status as the original.
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].statusCode", "provisional");

        // Verify that the new record has audit attributes set.
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].createdByName", "dragos");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].createdById", "123456");
        String createdAt = vehicleTechnicalRecordsSteps.getValueFromBody("[0].techRecord[0].createdAt");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date dateobj = new Date();
        Assert.assertTrue(createdAt.contains(df.format(dateobj)));
        String expectedAuditMessage = "VRM updated from " + randomVrm1 + " to " + randomVrm2 + ". no reason";
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].reasonForCreation", expectedAuditMessage);

        // Verify that the old record still exists, with an archived status.
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsByStatus(randomVin, VehicleTechnicalRecordStatus.ARCHIVED);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].statusCode", "archived");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].notes", "Tech record v1");

        // Verify that the old record has audit attributes set.
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].lastUpdatedByName", "dragos");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].lastUpdatedById", "123456");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].updateType", "techRecordUpdate");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].lastUpdatedAt", createdAt);
    }

    @Title("CVSB-10478 - AC7 - PUT - primaryVrm is updated to a primaryVrm which DOES currently exist in DynamoDB")
    @Test
    public void testVehicleTechnicalRecords_Put_PrimaryVrmUpdates_Existing() {
        // TEST SETUP
        // generate random Vin
        String randomVin1 = GenericData.generateRandomVin();
        String randomVin2 = GenericData.generateRandomVin();
        // generate random Vrm 1 and 2
        String randomVrm1 = GenericData.generateRandomVrm();
        String randomVrm2 = GenericData.generateRandomVrm();

        // Create record #1 with a specific VRM.
        // read post request body from file
        String postRequestBodyHgv = GenericData.readJsonValueFromFile("technical-records_hgv_all_fields.json", "$");
        // create alteration to change Vin in the post request body with the random generated Vin
        JsonPathAlteration alterationVin1 = new JsonPathAlteration("$.vin", randomVin1, "", "REPLACE");
        // create alteration to change primary vrm in the request body with the random generated primary vrm
        JsonPathAlteration alterationVrm1 = new JsonPathAlteration("$.primaryVrm", randomVrm1, "", "REPLACE");
        // initialize the alterations list with both declared alterations
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(alterationVin1, alterationVrm1));
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBodyHgv, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(201);

        // Create record #2 with a different specific VRM.
        JsonPathAlteration alterationVin2 = new JsonPathAlteration("$.vin", randomVin2, "", "REPLACE");
        JsonPathAlteration alterationVrm2 = new JsonPathAlteration("$.primaryVrm", randomVrm2, "", "REPLACE");
        alterations = new ArrayList<>(Arrays.asList(alterationVin2, alterationVrm2));
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBodyHgv, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(201);

        // Retrieve the systemNumber of the newly created record, for use in the update.
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecords(randomVin2);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        String systemNumber = vehicleTechnicalRecordsSteps.getValueFromBody("[0].systemNumber");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].statusCode", "provisional");

        // PUT the update record, with the VRM from the original record (i.e. clashing).
        String putRequestBodyHgv = GenericData.readJsonValueFromFile("technical-records_hgv_all_fields_put.json", "$");
        alterations = new ArrayList<>(Arrays.asList(alterationVin2, alterationVrm1));
        vehicleTechnicalRecordsSteps.putVehicleTechnicalRecordsForVehicleWithAlterations(systemNumber, putRequestBodyHgv, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(400);
        vehicleTechnicalRecordsSteps.validateData("Primary VRM " + randomVrm1 + " already exists");
    }

    @Title("CVSB-10478 - AC8 - PUT - trailerId is updated to a trailerId which does not currently exist in DynamoDB")
    @Test
    public void testVehicleTechnicalRecords_Put_TrailerIdUpdates_NotExisting() {
        // TEST SETUP
        // generate random Vin
        String randomVin = GenericData.generateRandomVin();
        // generate random Vrm 1 and 2
        String randomVrm1 = GenericData.generateRandomVrm();
        String randomVrm2 = GenericData.generateRandomVrm();

        // read post request body from file
        String postRequestBodyTrl = GenericData.readJsonValueFromFile("technical-records_trl_all_fields.json", "$");
        // create alteration to change Vin in the post request body with the random generated Vin
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        // create alteration to change primary vrm in the request body with the random generated primary vrm
        JsonPathAlteration alterationVrm = new JsonPathAlteration("$.primaryVrm", randomVrm1, "", "REPLACE");
        JsonPathAlteration alternationNotes = new JsonPathAlteration("$.techRecord[0].notes", "Tech record v1", "", "REPLACE");

        // initialize the alterations list with both declared alterations
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(alterationVin, alterationVrm, alternationNotes));

        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBodyTrl, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(201);

        // Retrieve the systemNumber of the newly created record, for use in the update.
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecords(randomVin);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        String systemNumber = vehicleTechnicalRecordsSteps.getValueFromBody("[0].systemNumber");
        String trailerId1 = vehicleTechnicalRecordsSteps.getValueFromBody("[0].trailerId");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].statusCode", "provisional");

        // PUT the update record.
        String putRequestBodyTrl = GenericData.readJsonValueFromFile("technical-records_trl_mandatory_fields_put.json", "$");
        String randomTrailerId2 = GenericData.generateRandomTrailerId();
        JsonPathAlteration alterationNotes2 = new JsonPathAlteration("$.techRecord[0].notes", "Tech record v2", "", "REPLACE");
        JsonPathAlteration alterationVrm2 = new JsonPathAlteration("$.primaryVrm", randomVrm2, "", "REPLACE");
        JsonPathAlteration alterationTrailerId2 = new JsonPathAlteration("$.trailerId", randomTrailerId2, "", "REPLACE");
        alterations = new ArrayList<>(Arrays.asList(alterationVin, alterationVrm2, alterationNotes2, alterationTrailerId2));
        vehicleTechnicalRecordsSteps.putVehicleTechnicalRecordsForVehicleWithAlterations(systemNumber, putRequestBodyTrl, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);

        // Verify that the record is updated with the new trailerId (at vehicle level).
        // Verify the updated record contents.
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecords(randomVin);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].notes", "Tech record v2");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].trailerId", randomTrailerId2);

        // Verify that the new record is there with the same status as the original.
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].statusCode", "provisional");

        // Verify that the new record has audit attributes set.
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].createdByName", "catalin");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].createdById", "123243424-234234245");
        String createdAt = vehicleTechnicalRecordsSteps.getValueFromBody("[0].techRecord[0].createdAt");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date dateobj = new Date();
        Assert.assertTrue(createdAt.contains(df.format(dateobj)));
        String expectedAuditMessage = "Trailer Id updated from " + trailerId1 + " to " + randomTrailerId2 + ". Update test.";
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].reasonForCreation", expectedAuditMessage);

        // Verify that the old record still exists, with an archived status.
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsByStatus(randomVin, VehicleTechnicalRecordStatus.ARCHIVED);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].statusCode", "archived");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].notes", "Tech record v1");

        // Verify that the old record has audit attributes set.
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].lastUpdatedByName", "catalin");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].lastUpdatedById", "123243424-234234245");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].updateType", "techRecordUpdate");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].lastUpdatedAt", createdAt);
    }

    @Title("CVSB-10478 - AC9 - PUT - trailerId is updated to a trailerId which DOES currently exist in DynamoDB")
    @Test
    public void testVehicleTechnicalRecords_Put_TrailerIdUpdates_Existing() {
        // TEST SETUP
        // generate random Vin
        String randomVin1 = GenericData.generateRandomVin();
        String randomVin2 = GenericData.generateRandomVin();
        String trailerId1;

        // Create record #1 with an auto-generated (unique) trailerId.
        // read post request body from file
        String postRequestBodyTrl = GenericData.readJsonValueFromFile("technical-records_trl_all_fields.json", "$");
        // create alteration to change Vin in the post request body with the random generated Vin
        JsonPathAlteration alterationVin1 = new JsonPathAlteration("$.vin", randomVin1, "", "REPLACE");
        // initialize the alterations list with both declared alterations
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(alterationVin1));
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBodyTrl, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(201);

        // Retrieve the trailerId of the newly created record, for use in the update test.
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecords(randomVin1);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        trailerId1 = vehicleTechnicalRecordsSteps.getValueFromBody("[0].trailerId");

        // Create record #2 with a different system-generated trailerId.
        JsonPathAlteration alterationVin2 = new JsonPathAlteration("$.vin", randomVin2, "", "REPLACE");
        alterations = new ArrayList<>(Arrays.asList(alterationVin2));
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBodyTrl, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(201);

        // Retrieve the systemNumber of the newly created record, for use in the update.
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecords(randomVin2);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        String systemNumber = vehicleTechnicalRecordsSteps.getValueFromBody("[0].systemNumber");

        // PUT the update record, with the trailerId from the original record (i.e. clashing).
        String putRequestBodyTrl = GenericData.readJsonValueFromFile("technical-records_trl_mandatory_fields_put.json", "$");
        JsonPathAlteration alterationTrailerId1 = new JsonPathAlteration("$.trailerId", trailerId1, "", "REPLACE");
        alterations = new ArrayList<>(Arrays.asList(alterationVin2, alterationTrailerId1));
        vehicleTechnicalRecordsSteps.putVehicleTechnicalRecordsForVehicleWithAlterations(systemNumber, putRequestBodyTrl, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(400);
        vehicleTechnicalRecordsSteps.validateData("TrailerId " + trailerId1 + " already exists");
    }

    @WithTag("Vtm")
    @Title("CVSB-10830 - AC1 - TRL vehicle is updated, and the appropriate attributes are automatically set")
    @Test
    public void testVehicleTechnicalRecordsUpdateAllTrlAttributes() {
        // TEST SETUP
        // generate random Vin
        String randomVin = GenericData.generateRandomVin();
        // generate random Vrm
        String randomVrm = GenericData.generateRandomVrm();
        // read post request body from file
        String postRequestBodyTrl = GenericData.readJsonValueFromFile("technical-records_trl_all_fields.json", "$");
        // create alteration to change Vin in the post request body with the random generated Vin
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        // create alteration to change primary vrm in the request body with the random generated primary vrm
        JsonPathAlteration alterationVrm = new JsonPathAlteration("$.primaryVrm", randomVrm, "", "REPLACE");

        // initialize the alterations list with both declared alterations
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(alterationVin, alterationVrm));

        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBodyTrl, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(201);
        String systemNumber = vehicleTechnicalRecordsSteps.getSystemNumberUsingVin(randomVin);
        String trailerId = vehicleTechnicalRecordsSteps.getValueFromBody("[0].trailerId");

        String putRequestBodyTrl = GenericData.readJsonValueFromFile("technical-records_trl_all_fields_put.json", "$");
        String techRecord = GenericData.readJsonValueFromFile("technical-records_trl_all_fields_put.json", "$.techRecord[0]");
        JsonPathAlteration alterationTrailerId = new JsonPathAlteration("$.trailerId", trailerId, "", "REPLACE");
        List<JsonPathAlteration> alterations2 = new ArrayList<>(Arrays.asList(alterationVin, alterationVrm, alterationTrailerId));
        vehicleTechnicalRecordsSteps.putVehicleTechnicalRecordsForVehicleWithAlterations(systemNumber, putRequestBodyTrl, alterations2);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);

        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsByStatus(randomVin, VehicleTechnicalRecordStatus.ALL);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord.size()", 2);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].statusCode", "archived");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[1].statusCode", "provisional");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[1].axles.size()", 2);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].lastUpdatedById", "123456");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].lastUpdatedByName", "dragos");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].updateType", "techRecordUpdate");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[1].createdById", "123456");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[1].createdByName", "dragos");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[1].vehicleClass.code", "t");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[1].bodyType.code", "p");
        vehicleTechnicalRecordsSteps.validateResponseContainsJson("[0].techRecord.find { it.statusCode == 'provisional' }", techRecord);
    }

    @WithTag("Vtm")
    @Title("CVSB-10249 - AC1 - Vehicle class code is auto-populated when updating a trl " +
            "AC2 - Body type code is auto-populated when updating a trl")
    @Test
    public void testUpdateVehicleTrlAutoPopulateFields() {
        // TEST SETUP
        //generate random Vin
        String randomVin = GenericData.generateRandomVin();
        //generate random Vrm
        String randomVrm = GenericData.generateRandomVrm();
        // read post request body from file
        String requestBodyTrl = GenericData.readJsonValueFromFile("technical-records_trl_all_fields.json","$");
        // create alteration to change Vin in the request body with the random generated Vin
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin,"","REPLACE");
        // create alteration to change primary vrm in the request body with the random generated primary vrm
        JsonPathAlteration alterationVrm = new JsonPathAlteration("$.primaryVrm", randomVrm,"","REPLACE");
        // initialize the alterations list with both declared alteration
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(alterationVin, alterationVrm));

        // TEST
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(requestBodyTrl, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(201);
        String systemNumber = vehicleTechnicalRecordsSteps.getSystemNumberUsingVin(randomVin);
        String trailerId = vehicleTechnicalRecordsSteps.getValueFromBody("[0].trailerId");

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
        JsonPathAlteration alterationTrailerId = new JsonPathAlteration("$.trailerId", trailerId, "", "REPLACE");
        alterations.add(alterationTrailerId);

        JsonPathAlteration alterationStatusCode = new JsonPathAlteration("$.techRecord[0]", "provisional","statusCode","ADD_FIELD");
        alterations.add(alterationStatusCode);

        vehicleTechnicalRecordsSteps.putVehicleTechnicalRecordsForVehicleWithAlterations(systemNumber, requestBodyTrl, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord.size()", 2);
        // validate AC2
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[1].vehicleClass.code", "n");
        // validated AC3
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[1].bodyType.code", "a");
    }

    @WithTag("Vtm")
    @Title("CVSB-14486 - AC4 - All error messages are returned")
    @Test
    public void testUpdateVehicleErrorOnMultipleAdrFields() {
        // TEST SETUP
        //generate random Vin
        String randomVin = GenericData.generateRandomVin();
        //generate random Vrm
        String randomVrm = GenericData.generateRandomVrm();
        // read post request body from file
        String requestBodyAdr = GenericData.readJsonValueFromFile(
                "technical-records_hgv_all_fields_with_adr_details.json","$");
        // create alteration to change Vin in the request body with the random generated Vin
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin,"","REPLACE");
        // create alteration to change primary vrm in the request body with the random generated primary vrm
        JsonPathAlteration alterationVrm = new JsonPathAlteration("$.primaryVrm", randomVrm,"","REPLACE");
        // initialize the alterations list with both declared alteration
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(alterationVin, alterationVrm));

        // TEST
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(requestBodyAdr, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(201);
        // create alteration to remove mandatory adr field
        JsonPathAlteration alterationRemoveMandatoryAdrField = new JsonPathAlteration(
                "$.techRecord[0].adrDetails.applicantDetails.name", "","","DELETE");
        alterations.add(alterationRemoveMandatoryAdrField);
        // create alteration for adr field to have empty string value
        JsonPathAlteration alterationAdrFielEmptyStringValue = new JsonPathAlteration(
                "$.techRecord[0].adrDetails.applicantDetails.street", "","","REPLACE");
        alterations.add(alterationAdrFielEmptyStringValue);
        // create alteration for adr field to have different data type than expected
        JsonPathAlteration alterationAdrFieldDifferentDataTypeThanExpected = new JsonPathAlteration(
                "$.techRecord[0].adrDetails.applicantDetails.town", 20,"","REPLACE");
        alterations.add(alterationAdrFieldDifferentDataTypeThanExpected);
        // create alteration for adr field to have value that is not in the accepted values
        JsonPathAlteration alterationAdrFieldNotAcceptedValue = new JsonPathAlteration(
                "$.techRecord[0].adrDetails.tank.tankDetails.tc2Details.tc2Type", "new","","REPLACE");
        alterations.add(alterationAdrFieldNotAcceptedValue);
        // create alteration for adr field to have value with length bigger and the maximum accepted length
        JsonPathAlteration alterationAdrFieldValueLengthBiggerThanMax = new JsonPathAlteration(
                "$.techRecord[0].adrDetails.applicantDetails.postcode", RandomStringUtils.randomAlphanumeric(26),
                "","REPLACE");
        alterations.add(alterationAdrFieldValueLengthBiggerThanMax);
        vehicleTechnicalRecordsSteps.putVehicleTechnicalRecordsForVehicleWithAlterations(randomVin, requestBodyAdr, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(400);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("errors.size()", 5);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("errors.findAll " +
                "{ it.contains('adrDetails.applicantDetails.name') }.size()", 1);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("errors.findAll " +
                "{ it.contains('adrDetails.applicantDetails.street') }.size()", 1);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("errors.findAll " +
                "{ it.contains('adrDetails.applicantDetails.town') }.size()", 1);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("errors.findAll " +
                "{ it.contains('adrDetails.applicantDetails.postcode') }.size()", 1);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("errors.findAll " +
                "{ it.contains('adrDetails.tank.tankDetails.tc2Details.tc2Type') }.size()", 1);
    }

    @Title("CVSB-11814 - AC1. PUT: Successfully update a vehicle - CAR")
    @Test
    public void testUpdateVehicleTechnicalRecordForCar() {

        // Generate random Vin
        String randomVin = GenericData.generateRandomVin();

        // Generate random Vrm
        String randomVrm = GenericData.generateRandomVrm();

        // Read post request body from file
        String postRequestBody = GenericData.readJsonValueFromFile("technical-records_post_payload_car_10328.json","$");

        // Create alteration to change one more more fields in the request body
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin,"","REPLACE");
        JsonPathAlteration alterationVrm = new JsonPathAlteration("$.primaryVrm", randomVrm,"","REPLACE");

        // Collate the alterations
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(alterationVin, alterationVrm));

        // POST tech-records and verify the expected response
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBody, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(HttpStatus.SC_CREATED);
        vehicleTechnicalRecordsSteps.validateData("Technical Record created");

        // GET tech-records and verify the expected response
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecords(randomVin);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(HttpStatus.SC_OK);
        vehicleTechnicalRecordsSteps.fieldInPathShouldExist("[0]","systemNumber");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].recordCompleteness","complete" );
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].statusCode","provisional" );

        String systemNumber = vehicleTechnicalRecordsSteps.getSystemNumber();

        // Read post request body from file
        String putRequestBody = GenericData.readJsonValueFromFile("technical-records_put_payload_car_11814.json","$");

        // PUT tech-records and verify the expected response
        vehicleTechnicalRecordsSteps.putVehicleTechnicalRecordsForVehicleWithAlterations(systemNumber,putRequestBody,alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(HttpStatus.SC_OK);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[1].euVehicleCategory","m1" );
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[1].recordCompleteness","complete" );

        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[0].statusCode","archived" );
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[0].recordCompleteness","complete" );
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[0].lastUpdatedByName","Tue" );
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[0].lastUpdatedById","12345" );
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldContains("techRecord[0].lastUpdatedAt",new SimpleDateFormat("yyyy-MM-dd").format(date));
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[0].updateType","techRecordUpdate" );
    }

    @Title("CVSB-11814 - AC1. PUT: Successfully update a vehicle - LGV")
    @Test
    public void testUpdateVehicleTechnicalRecordForLgv() {

        // Generate random Vin
        String randomVin = GenericData.generateRandomVin();

        // Generate random Vrm
        String randomVrm = GenericData.generateRandomVrm();

        // Read post request body from file
        String postRequestBody = GenericData.readJsonValueFromFile("technical-records_post_payload_lgv_10328.json","$");

        // Create alteration to change one more more fields in the request body
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin,"","REPLACE");
        JsonPathAlteration alterationVrm = new JsonPathAlteration("$.primaryVrm", randomVrm,"","REPLACE");

        // Collate the alterations
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(alterationVin, alterationVrm));

        // POST tech-records and verify the expected response
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBody, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(HttpStatus.SC_CREATED);
        vehicleTechnicalRecordsSteps.validateData("Technical Record created");

        // GET tech-records and verify the expected response
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecords(randomVin);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(HttpStatus.SC_OK);
        vehicleTechnicalRecordsSteps.fieldInPathShouldExist("[0]","systemNumber");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].recordCompleteness","complete" );
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].statusCode","provisional" );

        String systemNumber = vehicleTechnicalRecordsSteps.getSystemNumber();

        // Read post request body from file
        String putRequestBody = GenericData.readJsonValueFromFile("technical-records_put_payload_lgv_11814.json","$");

        // PUT tech-records and verify the expected response
        vehicleTechnicalRecordsSteps.putVehicleTechnicalRecordsForVehicleWithAlterations(systemNumber,putRequestBody,alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(HttpStatus.SC_OK);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[1].euVehicleCategory","n1" );
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[1].recordCompleteness","complete" );

        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[0].statusCode","archived" );
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[0].recordCompleteness","complete" );
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[0].lastUpdatedByName","Tue" );
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[0].lastUpdatedById","12345" );
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldContains("techRecord[0].lastUpdatedAt",new SimpleDateFormat("yyyy-MM-dd").format(date));
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[0].updateType","techRecordUpdate" );

    }

    @Title("CVSB-11814 - AC1. PUT: Successfully update a vehicle - MOTORCYCLE")
    @Test
    public void testUpdateVehicleTechnicalRecordForMotorcycle() {

        // Generate random Vin
        String randomVin = GenericData.generateRandomVin();

        // Generate random Vrm
        String randomVrm = GenericData.generateRandomVrm();

        // Read post request body from file
        String postRequestBody = GenericData.readJsonValueFromFile("technical-records_post_payload_motorcycle_10328.json","$");

        // Create alteration to change one more more fields in the request body
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin,"","REPLACE");
        JsonPathAlteration alterationVrm = new JsonPathAlteration("$.primaryVrm", randomVrm,"","REPLACE");

        // Collate the alterations
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(alterationVin, alterationVrm));

        // POST tech-records and verify the expected response
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBody, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(HttpStatus.SC_CREATED);
        vehicleTechnicalRecordsSteps.validateData("Technical Record created");

        // GET tech-records and verify the expected response
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecords(randomVin);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(HttpStatus.SC_OK);
        vehicleTechnicalRecordsSteps.fieldInPathShouldExist("[0]","systemNumber");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].recordCompleteness","complete" );
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].statusCode","provisional" );

        String systemNumber = vehicleTechnicalRecordsSteps.getSystemNumber();

        // Read post request body from file
        String putRequestBody = GenericData.readJsonValueFromFile("technical-records_put_payload_motorcycle_11814.json","$");

        // PUT tech-records and verify the expected response
        vehicleTechnicalRecordsSteps.putVehicleTechnicalRecordsForVehicleWithAlterations(systemNumber,putRequestBody,alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(HttpStatus.SC_OK);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[1].euVehicleCategory","l7e" );
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[1].recordCompleteness","complete" );

        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[0].statusCode","archived" );
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[0].recordCompleteness","complete" );
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[0].lastUpdatedByName","Tue" );
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[0].lastUpdatedById","12345" );
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldContains("techRecord[0].lastUpdatedAt",new SimpleDateFormat("yyyy-MM-dd").format(date));
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[0].updateType","techRecordUpdate" );
    }

    @Title("CVSB-11814 - AC1. PUT: Successfully update a vehicle - Optional VehicleClass - CAR")
    @Test
    public void testUpdateVehicleTechnicalRecordOptionalVehicleClassForCar() {

        // Generate random Vin
        String randomVin = GenericData.generateRandomVin();

        // Generate random Vrm
        String randomVrm = GenericData.generateRandomVrm();

        // Read post request body from file
        String postRequestBody = GenericData.readJsonValueFromFile("technical-records_post_payload_car_10328.json","$");

        // Create alteration to change one more more fields in the request body
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin,"","REPLACE");
        JsonPathAlteration alterationVrm = new JsonPathAlteration("$.primaryVrm", randomVrm,"","REPLACE");
        JsonPathAlteration alterationVehicleClass = new JsonPathAlteration("$.techRecord[0].vehicleClass", "", "", "DELETE");

        // Collate the alterations
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(alterationVin, alterationVrm,alterationVehicleClass));

        // POST tech-records and verify the expected response
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBody, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(HttpStatus.SC_CREATED);
        vehicleTechnicalRecordsSteps.validateData("Technical Record created");

        // GET tech-records and verify the expected response
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecords(randomVin);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(HttpStatus.SC_OK);
        vehicleTechnicalRecordsSteps.fieldInPathShouldExist("[0]","systemNumber");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].recordCompleteness","complete" );
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].statusCode","provisional" );
        vehicleTechnicalRecordsSteps.fieldInPathShouldNotExist("[0].techRecord[0]","vehicleClass");

        String systemNumber = vehicleTechnicalRecordsSteps.getSystemNumber();

        // Read post request body from file
        String putRequestBody = GenericData.readJsonValueFromFile("technical-records_put_payload_car_11814.json","$");

        // PUT tech-records and verify the expected response
        vehicleTechnicalRecordsSteps.putVehicleTechnicalRecordsForVehicleWithAlterations(systemNumber,putRequestBody,alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(HttpStatus.SC_OK);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[1].euVehicleCategory","m1" );
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[1].recordCompleteness","complete" );

        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[0].statusCode","archived" );
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[0].recordCompleteness","complete" );
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[0].lastUpdatedByName","Tue" );
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[0].lastUpdatedById","12345" );
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldContains("techRecord[0].lastUpdatedAt",new SimpleDateFormat("yyyy-MM-dd").format(date));
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[0].updateType","techRecordUpdate" );
        vehicleTechnicalRecordsSteps.fieldInPathShouldNotExist("[0].techRecord[0]","vehicleClass");
    }

    @Title("CVSB-11814 - AC1. PUT: Successfully update a vehicle - Optional VehicleClass - LGV")
    @Test
    public void testUpdateVehicleTechnicalRecordWithOptionalVehicleClassForLgv() {

        // Generate random Vin
        String randomVin = GenericData.generateRandomVin();

        // Generate random Vrm
        String randomVrm = GenericData.generateRandomVrm();

        // Read post request body from file
        String postRequestBody = GenericData.readJsonValueFromFile("technical-records_post_payload_lgv_10328.json","$");

        // Create alteration to change one more more fields in the request body
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin,"","REPLACE");
        JsonPathAlteration alterationVrm = new JsonPathAlteration("$.primaryVrm", randomVrm,"","REPLACE");
        JsonPathAlteration alterationVehicleClass = new JsonPathAlteration("$.techRecord[0].vehicleClass", "", "", "DELETE");

        // Collate the alterations
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(alterationVin, alterationVrm,alterationVehicleClass));

        // POST tech-records and verify the expected response
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBody, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(HttpStatus.SC_CREATED);
        vehicleTechnicalRecordsSteps.validateData("Technical Record created");

        // GET tech-records and verify the expected response
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecords(randomVin);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(HttpStatus.SC_OK);
        vehicleTechnicalRecordsSteps.fieldInPathShouldExist("[0]","systemNumber");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].recordCompleteness","complete" );
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].statusCode","provisional" );
        vehicleTechnicalRecordsSteps.fieldInPathShouldNotExist("[0].techRecord[0]","vehicleClass");

        String systemNumber = vehicleTechnicalRecordsSteps.getSystemNumber();

        // Read post request body from file
        String putRequestBody = GenericData.readJsonValueFromFile("technical-records_put_payload_lgv_11814.json","$");

        // PUT tech-records and verify the expected response
        vehicleTechnicalRecordsSteps.putVehicleTechnicalRecordsForVehicleWithAlterations(systemNumber,putRequestBody,alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(HttpStatus.SC_OK);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[1].euVehicleCategory","n1" );
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[1].recordCompleteness","complete" );

        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[0].statusCode","archived" );
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[0].recordCompleteness","complete" );
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[0].lastUpdatedByName","Tue" );
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[0].lastUpdatedById","12345" );
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldContains("techRecord[0].lastUpdatedAt",new SimpleDateFormat("yyyy-MM-dd").format(date));
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[0].updateType","techRecordUpdate" );
        vehicleTechnicalRecordsSteps.fieldInPathShouldNotExist("[0].techRecord[0]","vehicleClass");

    }

    @WithTag("Vtm")
    @Title("CVSB-10316 - AC7 -statusCode updated in vehicle API + AC6 - testResult updated in the test Results API - HGV - PRS - First Test")
    @Test
    public void testPostVehicleTechRecordHgvPrsFirstTest(){

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

        System.out.println(systemNumber);

        // read base JSON for POST test-results
        String testResultRecord = GenericData.readJsonValueFromFile("test-results_first_test_hgv_payload_10316.json","$");

        String randomTestResultId = UUID.randomUUID().toString();
        JsonPathAlteration alterationSystemNumberTestResults = new JsonPathAlteration("$.systemNumber", systemNumber, "", "REPLACE");
        JsonPathAlteration alterationVinTestResults = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationTestResultIdPost = new JsonPathAlteration("$.testResultId", randomTestResultId, "", "REPLACE");
        JsonPathAlteration alterationTestResult = new JsonPathAlteration("$.testTypes[0].testResult", "prs", "", "REPLACE");
        JsonPathAlteration alterationTestStartTimestamp = new JsonPathAlteration("$.testStartTimestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 1)), "", "REPLACE");
        JsonPathAlteration alterationTestEndTimestamp = new JsonPathAlteration("$.testEndTimestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 4)), "", "REPLACE");
        JsonPathAlteration alterationTestTypeStartTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeStartTimestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 2)), "", "REPLACE");
        JsonPathAlteration alterationTestTypeEndTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeEndTimestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 3)), "", "REPLACE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterationsTestResults = new ArrayList<>(Arrays.asList(
                alterationSystemNumberTestResults,
                alterationVinTestResults,
                alterationTestResultIdPost,
                alterationTestResult,
                alterationTestStartTimestamp,
                alterationTestEndTimestamp,
                alterationTestTypeStartTimestamp,
                alterationTestTypeEndTimestamp));

        // Post the test-results, together with any alterations, and verify that they are accepted.
        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, alterationsTestResults);
        testResultsSteps.statusCodeShouldBe(HttpStatus.SC_CREATED);
        testResultsSteps.validateData("Test records created");

        // Read the base JSON for PUT test-results
        String putRequestBody = GenericData.readJsonValueFromFile("test-results_first_test_hgv_put_payload_10316.json","$");

        JsonPathAlteration alterationSystemNumberPutTestResults = new JsonPathAlteration("$.testResult.systemNumber", systemNumber, "", "REPLACE");
        JsonPathAlteration alterationVinPutTestResults = new JsonPathAlteration("$.testResult.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationTestResultIdPut = new JsonPathAlteration("$.testResult.testResultId", randomTestResultId, "", "REPLACE");
        JsonPathAlteration alterationTestResultPut = new JsonPathAlteration("$.testResult.testTypes[0].testResult", "prs", "", "REPLACE");
        JsonPathAlteration alterationPutTestStartTimestamp = new JsonPathAlteration("$.testResult.testStartTimestamp",new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 1)) , "", "REPLACE");
        JsonPathAlteration alterationPutTestEndTimestamp = new JsonPathAlteration("$.testResult.testEndTimestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 4)), "", "REPLACE");
        JsonPathAlteration alterationPutTestTypeStartTimestamp = new JsonPathAlteration("$.testResult.testTypes[0].testTypeStartTimestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 2)), "", "REPLACE");
        JsonPathAlteration alterationPutTestTypeEndTimestamp = new JsonPathAlteration("$.testResult.testTypes[0].testTypeEndTimestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 3)), "", "REPLACE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterationsTestResultsPut = new ArrayList<>(Arrays.asList(
                alterationSystemNumberPutTestResults,
                alterationVinPutTestResults,
                alterationTestResultIdPut,
                alterationTestResultPut,
                alterationPutTestStartTimestamp,
                alterationPutTestEndTimestamp,
                alterationPutTestTypeStartTimestamp,
                alterationPutTestTypeEndTimestamp
        ));

        testResultsSteps.putTestResultsWithAlterations(systemNumber,putRequestBody,alterationsTestResultsPut);
        testResultsSteps.statusCodeShouldBe(HttpStatus.SC_OK);

        // wait until the tech-record is updated
        vehicleTechnicalRecordsSteps.waitForVehicleTechRecordsToBeUpdated(randomVin, 20, 3);

        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsByStatusAndSearchCriteria(randomVin,VehicleTechnicalRecordStatus.ALL,VehicleTechnicalRecordSearchCriteria.VIN);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(HttpStatus.SC_OK);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord.size()", 3);

        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord.findAll { it.statusCode == 'archived'}.size()", 2);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord.findAll { it.statusCode == 'current'}.size()", 1);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe
                ("[0].techRecord.findAll { it.statusCode == 'archived' && it.lastUpdatedByName == 'Gica'}.size()", 1);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe
                ("[0].techRecord.find { it.statusCode == 'archived' && it.lastUpdatedByName == 'Gica'}.lastUpdatedById", "133");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldContains
                ("[0].techRecord.find { it.statusCode == 'archived' && it.lastUpdatedByName == 'Gica'}.lastUpdatedAt", new SimpleDateFormat("yyyy-MM-dd").format(date));
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldContains
                ("[0].techRecord.find { it.statusCode == 'archived' && it.lastUpdatedByName == 'Gica'}.updateType", "techRecordUpdate");

        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe
                ("[0].techRecord.findAll { it.statusCode == 'archived' && it.lastUpdatedByName == 'catalin'}.size()", 1);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord.find { it.statusCode == 'archived' && it.lastUpdatedByName == 'catalin'}.lastUpdatedById", "123243424-234234245");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldContains("[0].techRecord.find { it.statusCode == 'archived' && it.lastUpdatedByName == 'catalin'}.lastUpdatedAt", new SimpleDateFormat("yyyy-MM-dd").format(date));
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldContains("[0].techRecord.find { it.statusCode == 'archived' && it.lastUpdatedByName == 'catalin'}.updateType", "techRecordUpdate");

        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe
                ("[0].techRecord.find { it.statusCode == 'current' }.createdByName", "catalin");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord.find { it.statusCode == 'current' }.createdById", "123243424-234234245");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldContains("[0].techRecord.find { it.statusCode == 'current' }.createdAt", new SimpleDateFormat("yyyy-MM-dd").format(date));
    }

    @WithTag("Vtm")
    @Title("CVSB-10316 - AC7 -statusCode updated in vehicle API + AC6 - testResult updated in the test Results API - HGV - Pass - First Test")
    @Test
    public void testPostVehicleTechRecordHgvPassFirstTest(){

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

        System.out.println(systemNumber);

        // read base JSON for POST test-results
        String testResultRecord = GenericData.readJsonValueFromFile("test-results_first_test_hgv_payload_10316.json","$");

        String randomTestResultId = UUID.randomUUID().toString();
        JsonPathAlteration alterationSystemNumberTestResults = new JsonPathAlteration("$.systemNumber", systemNumber, "", "REPLACE");
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

        // Read the base JSON for PUT test-results
        String putRequestBody = GenericData.readJsonValueFromFile("test-results_first_test_hgv_put_payload_10316.json","$");

        JsonPathAlteration alterationSystemNumberPutTestResults = new JsonPathAlteration("$.testResult.systemNumber", systemNumber, "", "REPLACE");
        JsonPathAlteration alterationVinPutTestResults = new JsonPathAlteration("$.testResult.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationTestResultIdPut = new JsonPathAlteration("$.testResult.testResultId", randomTestResultId, "", "REPLACE");
        JsonPathAlteration alterationPutTestStartTimestamp = new JsonPathAlteration("$.testResult.testStartTimestamp",new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 1)) , "", "REPLACE");
        JsonPathAlteration alterationPutTestEndTimestamp = new JsonPathAlteration("$.testResult.testEndTimestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 4)), "", "REPLACE");
        JsonPathAlteration alterationPutTestTypeStartTimestamp = new JsonPathAlteration("$.testResult.testTypes[0].testTypeStartTimestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 2)), "", "REPLACE");
        JsonPathAlteration alterationPutTestTypeEndTimestamp = new JsonPathAlteration("$.testResult.testTypes[0].testTypeEndTimestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 3)), "", "REPLACE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterationsTestResultsPut = new ArrayList<>(Arrays.asList(
                alterationSystemNumberPutTestResults,
                alterationVinPutTestResults,
                alterationTestResultIdPut,
                alterationPutTestStartTimestamp,
                alterationPutTestEndTimestamp,
                alterationPutTestTypeStartTimestamp,
                alterationPutTestTypeEndTimestamp
        ));

        testResultsSteps.putTestResultsWithAlterations(systemNumber,putRequestBody,alterationsTestResultsPut);
        testResultsSteps.statusCodeShouldBe(HttpStatus.SC_OK);

        // wait until the tech-record is updated
        vehicleTechnicalRecordsSteps.waitForVehicleTechRecordsToBeUpdated(randomVin, 20, 3);

        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsByStatusAndSearchCriteria(randomVin,VehicleTechnicalRecordStatus.ALL,VehicleTechnicalRecordSearchCriteria.VIN);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(HttpStatus.SC_OK);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord.size()", 3);

        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord.findAll { it.statusCode == 'archived'}.size()", 2);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord.findAll { it.statusCode == 'current'}.size()", 1);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord.findAll { it.statusCode == 'archived' && it.lastUpdatedByName == 'Gica'}.size()", 1);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord.find { it.statusCode == 'archived' && it.lastUpdatedByName == 'Gica'}.lastUpdatedById", "133");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldContains("[0].techRecord.find { it.statusCode == 'archived' && it.lastUpdatedByName == 'Gica'}.lastUpdatedAt", new SimpleDateFormat("yyyy-MM-dd").format(date));
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldContains("[0].techRecord.find { it.statusCode == 'archived' && it.lastUpdatedByName == 'Gica'}.updateType", "techRecordUpdate");

        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord.findAll { it.statusCode == 'archived' && it.lastUpdatedByName == 'catalin'}.size()", 1);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord.find { it.statusCode == 'archived' && it.lastUpdatedByName == 'catalin'}.lastUpdatedById", "123243424-234234245");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldContains("[0].techRecord.find { it.statusCode == 'archived' && it.lastUpdatedByName == 'catalin'}.lastUpdatedAt", new SimpleDateFormat("yyyy-MM-dd").format(date));
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldContains("[0].techRecord.find { it.statusCode == 'archived' && it.lastUpdatedByName == 'catalin'}.updateType", "techRecordUpdate");

        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord.find { it.statusCode == 'current' }.createdByName", "catalin");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord.find { it.statusCode == 'current' }.createdById", "123243424-234234245");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldContains("[0].techRecord.find { it.statusCode == 'current' }.createdAt", new SimpleDateFormat("yyyy-MM-dd").format(date));
    }

    @WithTag("Vtm")
    @Title("CVSB-10316 - AC7 -statusCode updated in vehicle API + AC6 - testResult updated in the test Results API - TRL - PRS - First Test")
    @Test
    public void testPostVehicleTechRecordTrlPrsFirstTest(){

        // POST tech-record,read the base tech-record JSON.
        String postRequestBody = GenericData.readJsonValueFromFile("technical-records_trl_all_fields.json","$");

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

        System.out.println(systemNumber);

        // read base JSON for POST test-results
        String testResultRecord = GenericData.readJsonValueFromFile("test-results_first_test_trl_payload_10316.json","$");

        String randomTestResultId = UUID.randomUUID().toString();
        JsonPathAlteration alterationSystemNumberTestResults = new JsonPathAlteration("$.systemNumber", systemNumber, "", "REPLACE");
        JsonPathAlteration alterationVinTestResults = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationTestResultIdPost = new JsonPathAlteration("$.testResultId", randomTestResultId, "", "REPLACE");
        JsonPathAlteration alterationTestResult = new JsonPathAlteration("$.testTypes[0].testResult", "prs", "", "REPLACE");
        JsonPathAlteration alterationTestStartTimestamp = new JsonPathAlteration("$.testStartTimestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 1)), "", "REPLACE");
        JsonPathAlteration alterationTestEndTimestamp = new JsonPathAlteration("$.testEndTimestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 4)), "", "REPLACE");
        JsonPathAlteration alterationTestTypeStartTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeStartTimestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 2)), "", "REPLACE");
        JsonPathAlteration alterationTestTypeEndTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeEndTimestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 3)), "", "REPLACE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterationsTestResults = new ArrayList<>(Arrays.asList(
                alterationSystemNumberTestResults,
                alterationVinTestResults,
                alterationTestResultIdPost,
                alterationTestResult,
                alterationTestStartTimestamp,
                alterationTestEndTimestamp,
                alterationTestTypeStartTimestamp,
                alterationTestTypeEndTimestamp));

        // Post the test-results, together with any alterations, and verify that they are accepted.
        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, alterationsTestResults);
        testResultsSteps.statusCodeShouldBe(HttpStatus.SC_CREATED);
        testResultsSteps.validateData("Test records created");

        // Read the base JSON for PUT test-results
        String putRequestBody = GenericData.readJsonValueFromFile("test-results_first_test_trl_put_payload_10316.json","$");

        JsonPathAlteration alterationSystemNumberPutTestResults = new JsonPathAlteration("$.testResult.systemNumber", systemNumber, "", "REPLACE");
        JsonPathAlteration alterationVinPutTestResults = new JsonPathAlteration("$.testResult.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationTestResultIdPut = new JsonPathAlteration("$.testResult.testResultId", randomTestResultId, "", "REPLACE");
        JsonPathAlteration alterationTestResultPut = new JsonPathAlteration("$.testResult.testTypes[0].testResult", "prs", "", "REPLACE");
        JsonPathAlteration alterationPutTestStartTimestamp = new JsonPathAlteration("$.testResult.testStartTimestamp",new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 1)) , "", "REPLACE");
        JsonPathAlteration alterationPutTestEndTimestamp = new JsonPathAlteration("$.testResult.testEndTimestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 4)), "", "REPLACE");
        JsonPathAlteration alterationPutTestTypeStartTimestamp = new JsonPathAlteration("$.testResult.testTypes[0].testTypeStartTimestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 2)), "", "REPLACE");
        JsonPathAlteration alterationPutTestTypeEndTimestamp = new JsonPathAlteration("$.testResult.testTypes[0].testTypeEndTimestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 3)), "", "REPLACE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterationsTestResultsPut = new ArrayList<>(Arrays.asList(
                alterationSystemNumberPutTestResults,
                alterationVinPutTestResults,
                alterationTestResultIdPut,
                alterationTestResultPut,
                alterationPutTestStartTimestamp,
                alterationPutTestEndTimestamp,
                alterationPutTestTypeStartTimestamp,
                alterationPutTestTypeEndTimestamp
        ));

        testResultsSteps.putTestResultsWithAlterations(systemNumber,putRequestBody,alterationsTestResultsPut);
        testResultsSteps.statusCodeShouldBe(HttpStatus.SC_OK);

        // wait until the tech-record is updated
        vehicleTechnicalRecordsSteps.waitForVehicleTechRecordsToBeUpdated(randomVin, 20, 3);

        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsByStatusAndSearchCriteria(randomVin,VehicleTechnicalRecordStatus.ALL,VehicleTechnicalRecordSearchCriteria.VIN);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(HttpStatus.SC_OK);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord.size()", 3);

        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord.findAll { it.statusCode == 'archived'}.size()", 2);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord.findAll { it.statusCode == 'current'}.size()", 1);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe
                ("[0].techRecord.findAll { it.statusCode == 'archived' && it.lastUpdatedByName == 'Gica'}.size()", 1);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe
                ("[0].techRecord.find { it.statusCode == 'archived' && it.lastUpdatedByName == 'Gica'}.lastUpdatedById", "133");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldContains
                ("[0].techRecord.find { it.statusCode == 'archived' && it.lastUpdatedByName == 'Gica'}.lastUpdatedAt", new SimpleDateFormat("yyyy-MM-dd").format(date));
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldContains
                ("[0].techRecord.find { it.statusCode == 'archived' && it.lastUpdatedByName == 'Gica'}.updateType", "techRecordUpdate");

        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe
                ("[0].techRecord.findAll { it.statusCode == 'archived' && it.lastUpdatedByName == 'catalin'}.size()", 1);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord.find { it.statusCode == 'archived' && it.lastUpdatedByName == 'catalin'}.lastUpdatedById", "123243424-234234245");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldContains("[0].techRecord.find { it.statusCode == 'archived' && it.lastUpdatedByName == 'catalin'}.lastUpdatedAt", new SimpleDateFormat("yyyy-MM-dd").format(date));
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldContains("[0].techRecord.find { it.statusCode == 'archived' && it.lastUpdatedByName == 'catalin'}.updateType", "techRecordUpdate");

        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe
                ("[0].techRecord.find { it.statusCode == 'current' }.createdByName", "catalin");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord.find { it.statusCode == 'current' }.createdById", "123243424-234234245");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldContains("[0].techRecord.find { it.statusCode == 'current' }.createdAt", new SimpleDateFormat("yyyy-MM-dd").format(date));
    }

    @WithTag("Vtm")
    @Title("CVSB-10316 - AC7 -statusCode updated in vehicle API + AC6 - testResult updated in the test Results API - TRL - Pass - First Test")
    @Test
    public void testPostVehicleTechRecordTrlPassFirstTest(){

        // POST tech-record,read the base tech-record JSON.
        String postRequestBody = GenericData.readJsonValueFromFile("technical-records_trl_all_fields.json","$");

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

        System.out.println(systemNumber);

        // read base JSON for POST test-results
        String testResultRecord = GenericData.readJsonValueFromFile("test-results_first_test_trl_payload_10316.json","$");

        String randomTestResultId = UUID.randomUUID().toString();
        JsonPathAlteration alterationSystemNumberTestResults = new JsonPathAlteration("$.systemNumber", systemNumber, "", "REPLACE");
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

        // Read the base JSON for PUT test-results
        String putRequestBody = GenericData.readJsonValueFromFile("test-results_first_test_trl_put_payload_10316.json","$");

        JsonPathAlteration alterationSystemNumberPutTestResults = new JsonPathAlteration("$.testResult.systemNumber", systemNumber, "", "REPLACE");
        JsonPathAlteration alterationVinPutTestResults = new JsonPathAlteration("$.testResult.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationTestResultIdPut = new JsonPathAlteration("$.testResult.testResultId", randomTestResultId, "", "REPLACE");
        JsonPathAlteration alterationPutTestStartTimestamp = new JsonPathAlteration("$.testResult.testStartTimestamp",new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 1)) , "", "REPLACE");
        JsonPathAlteration alterationPutTestEndTimestamp = new JsonPathAlteration("$.testResult.testEndTimestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 4)), "", "REPLACE");
        JsonPathAlteration alterationPutTestTypeStartTimestamp = new JsonPathAlteration("$.testResult.testTypes[0].testTypeStartTimestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 2)), "", "REPLACE");
        JsonPathAlteration alterationPutTestTypeEndTimestamp = new JsonPathAlteration("$.testResult.testTypes[0].testTypeEndTimestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 3)), "", "REPLACE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterationsTestResultsPut = new ArrayList<>(Arrays.asList(
                alterationSystemNumberPutTestResults,
                alterationVinPutTestResults,
                alterationTestResultIdPut,
                alterationPutTestStartTimestamp,
                alterationPutTestEndTimestamp,
                alterationPutTestTypeStartTimestamp,
                alterationPutTestTypeEndTimestamp
        ));

        testResultsSteps.putTestResultsWithAlterations(systemNumber,putRequestBody,alterationsTestResultsPut);
        testResultsSteps.statusCodeShouldBe(HttpStatus.SC_OK);

        // wait until the tech-record is updated
        vehicleTechnicalRecordsSteps.waitForVehicleTechRecordsToBeUpdated(randomVin, 60, 3);

        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsByStatusAndSearchCriteria(randomVin,VehicleTechnicalRecordStatus.ALL,VehicleTechnicalRecordSearchCriteria.VIN);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(HttpStatus.SC_OK);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord.size()", 3);

        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord.findAll { it.statusCode == 'archived'}.size()", 2);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord.findAll { it.statusCode == 'current'}.size()", 1);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe
                ("[0].techRecord.findAll { it.statusCode == 'archived' && it.lastUpdatedByName == 'Gica'}.size()", 1);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe
                ("[0].techRecord.find { it.statusCode == 'archived' && it.lastUpdatedByName == 'Gica'}.lastUpdatedById", "133");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldContains
                ("[0].techRecord.find { it.statusCode == 'archived' && it.lastUpdatedByName == 'Gica'}.lastUpdatedAt", new SimpleDateFormat("yyyy-MM-dd").format(date));
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldContains
                ("[0].techRecord.find { it.statusCode == 'archived' && it.lastUpdatedByName == 'Gica'}.updateType", "techRecordUpdate");

        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe
                ("[0].techRecord.findAll { it.statusCode == 'archived' && it.lastUpdatedByName == 'catalin'}.size()", 1);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord.find { it.statusCode == 'archived' && it.lastUpdatedByName == 'catalin'}.lastUpdatedById", "123243424-234234245");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldContains("[0].techRecord.find { it.statusCode == 'archived' && it.lastUpdatedByName == 'catalin'}.lastUpdatedAt", new SimpleDateFormat("yyyy-MM-dd").format(date));
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldContains("[0].techRecord.find { it.statusCode == 'archived' && it.lastUpdatedByName == 'catalin'}.updateType", "techRecordUpdate");

        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe
                ("[0].techRecord.find { it.statusCode == 'current' }.createdByName", "catalin");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord.find { it.statusCode == 'current' }.createdById", "123243424-234234245");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldContains("[0].techRecord.find { it.statusCode == 'current' }.createdAt", new SimpleDateFormat("yyyy-MM-dd").format(date));
    }

    @WithTag("Vtm")
    @Title("CVSB-10316 - AC7 -statusCode updated in vehicle API + AC6 - testResult updated in the test Results API - HGV - PRS - Notifiable Alteration")
    @Test
    public void testPostVehicleTechRecordHgvPrsNotifiableAlteration(){

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

        System.out.println(systemNumber);

        // read base JSON for POST test-results
        String testResultRecord = GenericData.readJsonValueFromFile("test-results_notifiable_alteration_hgv_payload_10316.json","$");

        String randomTestResultId = UUID.randomUUID().toString();
        JsonPathAlteration alterationSystemNumberTestResults = new JsonPathAlteration("$.systemNumber", systemNumber, "", "REPLACE");
        JsonPathAlteration alterationVinTestResults = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationTestResultIdPost = new JsonPathAlteration("$.testResultId", randomTestResultId, "", "REPLACE");
        JsonPathAlteration alterationTestResult = new JsonPathAlteration("$.testTypes[0].testResult", "prs", "", "REPLACE");
        JsonPathAlteration alterationTestStartTimestamp = new JsonPathAlteration("$.testStartTimestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 1)), "", "REPLACE");
        JsonPathAlteration alterationTestEndTimestamp = new JsonPathAlteration("$.testEndTimestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 4)), "", "REPLACE");
        JsonPathAlteration alterationTestTypeStartTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeStartTimestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 2)), "", "REPLACE");
        JsonPathAlteration alterationTestTypeEndTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeEndTimestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 3)), "", "REPLACE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterationsTestResults = new ArrayList<>(Arrays.asList(
                alterationSystemNumberTestResults,
                alterationVinTestResults,
                alterationTestResultIdPost,
                alterationTestResult,
                alterationTestStartTimestamp,
                alterationTestEndTimestamp,
                alterationTestTypeStartTimestamp,
                alterationTestTypeEndTimestamp));

        // Post the test-results, together with any alterations, and verify that they are accepted.
        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, alterationsTestResults);
        testResultsSteps.statusCodeShouldBe(HttpStatus.SC_CREATED);
        testResultsSteps.validateData("Test records created");

        // Read the base JSON for PUT test-results
        String putRequestBody = GenericData.readJsonValueFromFile("test-results_notifiable_alteration_hgv_put_payload_10316.json","$");

        JsonPathAlteration alterationSystemNumberPutTestResults = new JsonPathAlteration("$.testResult.systemNumber", systemNumber, "", "REPLACE");
        JsonPathAlteration alterationVinPutTestResults = new JsonPathAlteration("$.testResult.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationTestResultIdPut = new JsonPathAlteration("$.testResult.testResultId", randomTestResultId, "", "REPLACE");
        JsonPathAlteration alterationTestResultPut = new JsonPathAlteration("$.testResult.testTypes[0].testResult", "prs", "", "REPLACE");
        JsonPathAlteration alterationPutTestStartTimestamp = new JsonPathAlteration("$.testResult.testStartTimestamp",new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 1)) , "", "REPLACE");
        JsonPathAlteration alterationPutTestEndTimestamp = new JsonPathAlteration("$.testResult.testEndTimestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 4)), "", "REPLACE");
        JsonPathAlteration alterationPutTestTypeStartTimestamp = new JsonPathAlteration("$.testResult.testTypes[0].testTypeStartTimestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 2)), "", "REPLACE");
        JsonPathAlteration alterationPutTestTypeEndTimestamp = new JsonPathAlteration("$.testResult.testTypes[0].testTypeEndTimestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 3)), "", "REPLACE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterationsTestResultsPut = new ArrayList<>(Arrays.asList(
                alterationSystemNumberPutTestResults,
                alterationVinPutTestResults,
                alterationTestResultIdPut,
                alterationTestResultPut,
                alterationPutTestStartTimestamp,
                alterationPutTestEndTimestamp,
                alterationPutTestTypeStartTimestamp,
                alterationPutTestTypeEndTimestamp
        ));

        testResultsSteps.putTestResultsWithAlterations(systemNumber, putRequestBody, alterationsTestResultsPut);
        testResultsSteps.statusCodeShouldBe(HttpStatus.SC_OK);

        // wait until the tech-record is updated
        vehicleTechnicalRecordsSteps.waitForVehicleTechRecordsToBeUpdated(randomVin, 20, 3);

        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsByStatusAndSearchCriteria(randomVin,VehicleTechnicalRecordStatus.ALL,VehicleTechnicalRecordSearchCriteria.VIN);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(HttpStatus.SC_OK);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord.size()", 3);

        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord.findAll { it.statusCode == 'archived'}.size()", 2);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord.findAll { it.statusCode == 'current'}.size()", 1);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe
                ("[0].techRecord.findAll { it.statusCode == 'archived' && it.lastUpdatedByName == 'Gica'}.size()", 1);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe
                ("[0].techRecord.find { it.statusCode == 'archived' && it.lastUpdatedByName == 'Gica'}.lastUpdatedById", "133");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldContains
                ("[0].techRecord.find { it.statusCode == 'archived' && it.lastUpdatedByName == 'Gica'}.lastUpdatedAt", new SimpleDateFormat("yyyy-MM-dd").format(date));
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldContains
                ("[0].techRecord.find { it.statusCode == 'archived' && it.lastUpdatedByName == 'Gica'}.updateType", "techRecordUpdate");

        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe
                ("[0].techRecord.findAll { it.statusCode == 'archived' && it.lastUpdatedByName == 'catalin'}.size()", 1);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord.find { it.statusCode == 'archived' && it.lastUpdatedByName == 'catalin'}.lastUpdatedById", "123243424-234234245");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldContains("[0].techRecord.find { it.statusCode == 'archived' && it.lastUpdatedByName == 'catalin'}.lastUpdatedAt", new SimpleDateFormat("yyyy-MM-dd").format(date));
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldContains("[0].techRecord.find { it.statusCode == 'archived' && it.lastUpdatedByName == 'catalin'}.updateType", "techRecordUpdate");

        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe
                ("[0].techRecord.find { it.statusCode == 'current' }.createdByName", "catalin");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord.find { it.statusCode == 'current' }.createdById", "123243424-234234245");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldContains("[0].techRecord.find { it.statusCode == 'current' }.createdAt", new SimpleDateFormat("yyyy-MM-dd").format(date));
    }

    @WithTag("Vtm")
    @Title("CVSB-10316 - AC7 -statusCode updated in vehicle API + AC6 - testResult updated in the test Results API - HGV - Pass - Notifiable Alteration")
    @Test
    public void testPostVehicleTechRecordHgvPassNotifiableAlteration(){

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

        System.out.println(systemNumber);

        // read base JSON for POST test-results
        String testResultRecord = GenericData.readJsonValueFromFile("test-results_notifiable_alteration_hgv_payload_10316.json","$");

        String randomTestResultId = UUID.randomUUID().toString();
        JsonPathAlteration alterationSystemNumberTestResults = new JsonPathAlteration("$.systemNumber", systemNumber, "", "REPLACE");
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

        // Read the base JSON for PUT test-results
        String putRequestBody = GenericData.readJsonValueFromFile("test-results_notifiable_alteration_hgv_put_payload_10316.json","$");

        JsonPathAlteration alterationSystemNumberPutTestResults = new JsonPathAlteration("$.testResult.systemNumber", systemNumber, "", "REPLACE");
        JsonPathAlteration alterationVinPutTestResults = new JsonPathAlteration("$.testResult.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationTestResultIdPut = new JsonPathAlteration("$.testResult.testResultId", randomTestResultId, "", "REPLACE");
        JsonPathAlteration alterationPutTestStartTimestamp = new JsonPathAlteration("$.testResult.testStartTimestamp",new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 1)) , "", "REPLACE");
        JsonPathAlteration alterationPutTestEndTimestamp = new JsonPathAlteration("$.testResult.testEndTimestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 4)), "", "REPLACE");
        JsonPathAlteration alterationPutTestTypeStartTimestamp = new JsonPathAlteration("$.testResult.testTypes[0].testTypeStartTimestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 2)), "", "REPLACE");
        JsonPathAlteration alterationPutTestTypeEndTimestamp = new JsonPathAlteration("$.testResult.testTypes[0].testTypeEndTimestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 3)), "", "REPLACE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterationsTestResultsPut = new ArrayList<>(Arrays.asList(
                alterationSystemNumberPutTestResults,
                alterationVinPutTestResults,
                alterationTestResultIdPut,
                alterationPutTestStartTimestamp,
                alterationPutTestEndTimestamp,
                alterationPutTestTypeStartTimestamp,
                alterationPutTestTypeEndTimestamp
        ));

        testResultsSteps.putTestResultsWithAlterations(systemNumber,putRequestBody,alterationsTestResultsPut);
        testResultsSteps.statusCodeShouldBe(HttpStatus.SC_OK);

        // wait until the tech-record is updated
        vehicleTechnicalRecordsSteps.waitForVehicleTechRecordsToBeUpdated(randomVin, 20, 3);

        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsByStatusAndSearchCriteria(randomVin,VehicleTechnicalRecordStatus.ALL,VehicleTechnicalRecordSearchCriteria.VIN);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(HttpStatus.SC_OK);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord.size()", 3);

        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord.findAll { it.statusCode == 'archived'}.size()", 2);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord.findAll { it.statusCode == 'current'}.size()", 1);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe
                ("[0].techRecord.findAll { it.statusCode == 'archived' && it.lastUpdatedByName == 'Gica'}.size()", 1);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe
                ("[0].techRecord.find { it.statusCode == 'archived' && it.lastUpdatedByName == 'Gica'}.lastUpdatedById", "133");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldContains
                ("[0].techRecord.find { it.statusCode == 'archived' && it.lastUpdatedByName == 'Gica'}.lastUpdatedAt", new SimpleDateFormat("yyyy-MM-dd").format(date));
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldContains
                ("[0].techRecord.find { it.statusCode == 'archived' && it.lastUpdatedByName == 'Gica'}.updateType", "techRecordUpdate");

        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe
                ("[0].techRecord.findAll { it.statusCode == 'archived' && it.lastUpdatedByName == 'catalin'}.size()", 1);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord.find { it.statusCode == 'archived' && it.lastUpdatedByName == 'catalin'}.lastUpdatedById", "123243424-234234245");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldContains("[0].techRecord.find { it.statusCode == 'archived' && it.lastUpdatedByName == 'catalin'}.lastUpdatedAt", new SimpleDateFormat("yyyy-MM-dd").format(date));
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldContains("[0].techRecord.find { it.statusCode == 'archived' && it.lastUpdatedByName == 'catalin'}.updateType", "techRecordUpdate");

        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe
                ("[0].techRecord.find { it.statusCode == 'current' }.createdByName", "catalin");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord.find { it.statusCode == 'current' }.createdById", "123243424-234234245");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldContains("[0].techRecord.find { it.statusCode == 'current' }.createdAt", new SimpleDateFormat("yyyy-MM-dd").format(date));
    }

    @WithTag("Vtm")
    @Title("CVSB-10316 - AC7 -statusCode updated in vehicle API + AC6 - testResult updated in the test Results API - TRL - PRS - Notifiable Alteration")
    @Test
    public void testPostVehicleTechRecordTrlPrsNotifiableAlteration(){

        // POST tech-record,read the base tech-record JSON.
        String postRequestBody = GenericData.readJsonValueFromFile("technical-records_trl_all_fields.json","$");

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

        System.out.println(systemNumber);

        // read base JSON for POST test-results
        String testResultRecord = GenericData.readJsonValueFromFile("test-results_notifiable_alteration_trl_payload_10316.json","$");

        String randomTestResultId = UUID.randomUUID().toString();
        JsonPathAlteration alterationSystemNumberTestResults = new JsonPathAlteration("$.systemNumber", systemNumber, "", "REPLACE");
        JsonPathAlteration alterationVinTestResults = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationTestResultIdPost = new JsonPathAlteration("$.testResultId", randomTestResultId, "", "REPLACE");
        JsonPathAlteration alterationTestResult = new JsonPathAlteration("$.testTypes[0].testResult", "prs", "", "REPLACE");
        JsonPathAlteration alterationTestStartTimestamp = new JsonPathAlteration("$.testStartTimestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 1)), "", "REPLACE");
        JsonPathAlteration alterationTestEndTimestamp = new JsonPathAlteration("$.testEndTimestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 4)), "", "REPLACE");
        JsonPathAlteration alterationTestTypeStartTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeStartTimestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 2)), "", "REPLACE");
        JsonPathAlteration alterationTestTypeEndTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeEndTimestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 3)), "", "REPLACE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterationsTestResults = new ArrayList<>(Arrays.asList(
                alterationSystemNumberTestResults,
                alterationVinTestResults,
                alterationTestResultIdPost,
                alterationTestResult,
                alterationTestStartTimestamp,
                alterationTestEndTimestamp,
                alterationTestTypeStartTimestamp,
                alterationTestTypeEndTimestamp));

        // Post the test-results, together with any alterations, and verify that they are accepted.
        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, alterationsTestResults);
        testResultsSteps.statusCodeShouldBe(HttpStatus.SC_CREATED);
        testResultsSteps.validateData("Test records created");

        // Read the base JSON for PUT test-results
        String putRequestBody = GenericData.readJsonValueFromFile("test-results_notifiable_alteration_trl_put_payload_10316.json","$");

        JsonPathAlteration alterationSystemNumberPutTestResults = new JsonPathAlteration("$.testResult.systemNumber", systemNumber, "", "REPLACE");
        JsonPathAlteration alterationVinPutTestResults = new JsonPathAlteration("$.testResult.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationTestResultIdPut = new JsonPathAlteration("$.testResult.testResultId", randomTestResultId, "", "REPLACE");
        JsonPathAlteration alterationTestResultPut = new JsonPathAlteration("$.testResult.testTypes[0].testResult", "prs", "", "REPLACE");
        JsonPathAlteration alterationPutTestStartTimestamp = new JsonPathAlteration("$.testResult.testStartTimestamp",new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 1)) , "", "REPLACE");
        JsonPathAlteration alterationPutTestEndTimestamp = new JsonPathAlteration("$.testResult.testEndTimestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 4)), "", "REPLACE");
        JsonPathAlteration alterationPutTestTypeStartTimestamp = new JsonPathAlteration("$.testResult.testTypes[0].testTypeStartTimestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 2)), "", "REPLACE");
        JsonPathAlteration alterationPutTestTypeEndTimestamp = new JsonPathAlteration("$.testResult.testTypes[0].testTypeEndTimestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 3)), "", "REPLACE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterationsTestResultsPut = new ArrayList<>(Arrays.asList(
                alterationSystemNumberPutTestResults,
                alterationVinPutTestResults,
                alterationTestResultIdPut,
                alterationTestResultPut,
                alterationPutTestStartTimestamp,
                alterationPutTestEndTimestamp,
                alterationPutTestTypeStartTimestamp,
                alterationPutTestTypeEndTimestamp
        ));

        testResultsSteps.putTestResultsWithAlterations(systemNumber,putRequestBody,alterationsTestResultsPut);
        testResultsSteps.statusCodeShouldBe(HttpStatus.SC_OK);

        // wait until the tech-record is updated
        vehicleTechnicalRecordsSteps.waitForVehicleTechRecordsToBeUpdated(randomVin, 120, 3);

        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsByStatusAndSearchCriteria(randomVin,VehicleTechnicalRecordStatus.ALL,VehicleTechnicalRecordSearchCriteria.VIN);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(HttpStatus.SC_OK);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord.size()", 3);

        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord.findAll { it.statusCode == 'archived'}.size()", 2);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord.findAll { it.statusCode == 'current'}.size()", 1);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe
                ("[0].techRecord.findAll { it.statusCode == 'archived' && it.lastUpdatedByName == 'Gica'}.size()", 1);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe
                ("[0].techRecord.find { it.statusCode == 'archived' && it.lastUpdatedByName == 'Gica'}.lastUpdatedById", "133");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldContains
                ("[0].techRecord.find { it.statusCode == 'archived' && it.lastUpdatedByName == 'Gica'}.lastUpdatedAt", new SimpleDateFormat("yyyy-MM-dd").format(date));
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldContains
                ("[0].techRecord.find { it.statusCode == 'archived' && it.lastUpdatedByName == 'Gica'}.updateType", "techRecordUpdate");

        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe
                ("[0].techRecord.findAll { it.statusCode == 'archived' && it.lastUpdatedByName == 'catalin'}.size()", 1);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord.find { it.statusCode == 'archived' && it.lastUpdatedByName == 'catalin'}.lastUpdatedById", "123243424-234234245");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldContains("[0].techRecord.find { it.statusCode == 'archived' && it.lastUpdatedByName == 'catalin'}.lastUpdatedAt", new SimpleDateFormat("yyyy-MM-dd").format(date));
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldContains("[0].techRecord.find { it.statusCode == 'archived' && it.lastUpdatedByName == 'catalin'}.updateType", "techRecordUpdate");

        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe
                ("[0].techRecord.find { it.statusCode == 'current' }.createdByName", "catalin");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord.find { it.statusCode == 'current' }.createdById", "123243424-234234245");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldContains("[0].techRecord.find { it.statusCode == 'current' }.createdAt", new SimpleDateFormat("yyyy-MM-dd").format(date));
    }

    @WithTag("Vtm")
    @Title("CVSB-10316 - AC7 - statusCode updated in vehicle API + AC6 - testResult updated in the test Results API - TRL - Pass - Notifiable Alteration")
    @Test
    public void testPostVehicleTechRecordTrlPassNotifiableAlteration(){

        // POST tech-record,read the base tech-record JSON.
        String postRequestBody = GenericData.readJsonValueFromFile("technical-records_trl_all_fields.json","$");

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

        System.out.println(systemNumber);

        // read base JSON for POST test-results
        String testResultRecord = GenericData.readJsonValueFromFile("test-results_notifiable_alteration_trl_payload_10316.json","$");

        String randomTestResultId = UUID.randomUUID().toString();
        JsonPathAlteration alterationSystemNumberTestResults = new JsonPathAlteration("$.systemNumber", systemNumber, "", "REPLACE");
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

        // Read the base JSON for PUT test-results
        String putRequestBody = GenericData.readJsonValueFromFile("test-results_notifiable_alteration_trl_put_payload_10316.json","$");

        JsonPathAlteration alterationSystemNumberPutTestResults = new JsonPathAlteration("$.testResult.systemNumber", systemNumber, "", "REPLACE");
        JsonPathAlteration alterationVinPutTestResults = new JsonPathAlteration("$.testResult.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationTestResultIdPut = new JsonPathAlteration("$.testResult.testResultId", randomTestResultId, "", "REPLACE");
        JsonPathAlteration alterationPutTestStartTimestamp = new JsonPathAlteration("$.testResult.testStartTimestamp",new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 1)) , "", "REPLACE");
        JsonPathAlteration alterationPutTestEndTimestamp = new JsonPathAlteration("$.testResult.testEndTimestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 4)), "", "REPLACE");
        JsonPathAlteration alterationPutTestTypeStartTimestamp = new JsonPathAlteration("$.testResult.testTypes[0].testTypeStartTimestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 2)), "", "REPLACE");
        JsonPathAlteration alterationPutTestTypeEndTimestamp = new JsonPathAlteration("$.testResult.testTypes[0].testTypeEndTimestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 3)), "", "REPLACE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterationsTestResultsPut = new ArrayList<>(Arrays.asList(
                alterationSystemNumberPutTestResults,
                alterationVinPutTestResults,
                alterationTestResultIdPut,
                alterationPutTestStartTimestamp,
                alterationPutTestEndTimestamp,
                alterationPutTestTypeStartTimestamp,
                alterationPutTestTypeEndTimestamp
        ));

        testResultsSteps.putTestResultsWithAlterations(systemNumber,putRequestBody,alterationsTestResultsPut);
        testResultsSteps.statusCodeShouldBe(HttpStatus.SC_OK);

        // wait until the tech-record is updated
        vehicleTechnicalRecordsSteps.waitForVehicleTechRecordsToBeUpdated(randomVin, 20, 3);

        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsByStatusAndSearchCriteria(randomVin,VehicleTechnicalRecordStatus.ALL,VehicleTechnicalRecordSearchCriteria.VIN);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(HttpStatus.SC_OK);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord.size()", 3);

        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord.findAll { it.statusCode == 'archived'}.size()", 2);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord.findAll { it.statusCode == 'current'}.size()", 1);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe
                ("[0].techRecord.findAll { it.statusCode == 'archived' && it.lastUpdatedByName == 'Gica'}.size()", 1);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe
                ("[0].techRecord.find { it.statusCode == 'archived' && it.lastUpdatedByName == 'Gica'}.lastUpdatedById", "133");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldContains
                ("[0].techRecord.find { it.statusCode == 'archived' && it.lastUpdatedByName == 'Gica'}.lastUpdatedAt", new SimpleDateFormat("yyyy-MM-dd").format(date));
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldContains
                ("[0].techRecord.find { it.statusCode == 'archived' && it.lastUpdatedByName == 'Gica'}.updateType", "techRecordUpdate");

        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe
                ("[0].techRecord.findAll { it.statusCode == 'archived' && it.lastUpdatedByName == 'catalin'}.size()", 1);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord.find { it.statusCode == 'archived' && it.lastUpdatedByName == 'catalin'}.lastUpdatedById", "123243424-234234245");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldContains("[0].techRecord.find { it.statusCode == 'archived' && it.lastUpdatedByName == 'catalin'}.lastUpdatedAt", new SimpleDateFormat("yyyy-MM-dd").format(date));
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldContains("[0].techRecord.find { it.statusCode == 'archived' && it.lastUpdatedByName == 'catalin'}.updateType", "techRecordUpdate");

        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe
                ("[0].techRecord.find { it.statusCode == 'current' }.createdByName", "catalin");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord.find { it.statusCode == 'current' }.createdById", "123243424-234234245");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldContains("[0].techRecord.find { it.statusCode == 'current' }.createdAt", new SimpleDateFormat("yyyy-MM-dd").format(date));
    }

    @Title("CVSB-11794 - AC1 - Record completeness = complete (core and non-core attributes) - Testable - Complete - PSV")
    @Test
    public void testVehicleTechnicalRecordForTestablePsv() {

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
        JsonPathAlteration alterationParkingBreakeMrk = new JsonPathAlteration("$.techRecord[0].axles[0].parkingBrakeMrk", "", "", "DELETE");

        // initialize the alterations list with both declared alteration
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(alterationVin,
                alterationVrm,
                alterationSystemNumber,
                alterationParkingBreakeMrk));

        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBody, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(201);

        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsBySearchCriteria(randomVin, VehicleTechnicalRecordSearchCriteria.VIN);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(HttpStatus.SC_OK);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].recordCompleteness", "testable");

        String systemNumber = vehicleTechnicalRecordsSteps.getSystemNumber();

        // Read post request body from file
        String putRequestBody = GenericData.readJsonValueFromFile("technical-records_psv_all_fields_put.json","$");

        JsonPathAlteration alterationSystemNumberPut = new JsonPathAlteration("$", systemNumber, "systemNumber", "ADD_FIELD");

        List<JsonPathAlteration> alterationsPut = new ArrayList<>(Arrays.asList(alterationVin,
                alterationVrm,
                alterationSystemNumberPut
        ));

        // PUT tech-records and verify the expected response
        vehicleTechnicalRecordsSteps.putVehicleTechnicalRecordsForVehicleWithAlterations(systemNumber,putRequestBody,alterationsPut);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(HttpStatus.SC_OK);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[1].statusCode","provisional" );
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[1].recordCompleteness","complete" );

        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[0].statusCode","archived" );
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[0].recordCompleteness","testable" );
    }

    @WithTag("Vtm")
    @Title("CVSB-11794 - AC1 - Record completeness = complete (core and non-core attributes) - Testable - Complete - HGV")
    @Test
    public void testVehicleTechnicalRecordForTestableHgv() {

        // POST tech-record,read the base tech-record JSON.
        String postRequestBody = GenericData.readJsonValueFromFile("technical-records_hgv_all_fields.json", "$");

        // Create alteration to edit one or more fields in the request body
        String randomVin = GenericData.generateRandomVin();
        JsonPathAlteration alterationVinVehicle = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationTyreUseCode = new JsonPathAlteration("$.techRecord[0].tyreUseCode", "", "", "DELETE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterationsVehicle = new ArrayList<>(Arrays.asList(alterationVinVehicle,
                alterationTyreUseCode));

        // Post the tech-record, together with any alterations, and verify that they are accepted.
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBody, alterationsVehicle);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(HttpStatus.SC_CREATED);
        vehicleTechnicalRecordsSteps.validateData("Technical Record created");

        //GET tech-records
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsBySearchCriteria(randomVin, VehicleTechnicalRecordSearchCriteria.VIN);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(HttpStatus.SC_OK);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].recordCompleteness", "testable");

        String systemNumber = vehicleTechnicalRecordsSteps.getSystemNumber();

        // Read post request body from file
        String putRequestBody = GenericData.readJsonValueFromFile("technical-records_hgv_all_fields_put.json","$");

        JsonPathAlteration alterationSystemNumberPut = new JsonPathAlteration("$", systemNumber, "systemNumber", "ADD_FIELD");

        List<JsonPathAlteration> alterationsPut = new ArrayList<>(Arrays.asList(alterationVinVehicle,
                alterationSystemNumberPut
        ));

        // PUT tech-records and verify the expected response
        vehicleTechnicalRecordsSteps.putVehicleTechnicalRecordsForVehicleWithAlterations(systemNumber,putRequestBody,alterationsPut);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(HttpStatus.SC_OK);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[1].statusCode","provisional" );
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[1].recordCompleteness","complete" );

        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[0].statusCode","archived" );
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[0].recordCompleteness","testable" );
    }

    @WithTag("Vtm")
    @Title("CVSB-11794 - AC1 - Record completeness = complete (core and non-core attributes) - Testable - Complete - Trailer")
    @Test
    public void testVehicleTechnicalRecordForTestableTrailer() {
        // TEST SETUP
        //generate random system number
        String randomSystemNumber = GenericData.generateRandomSystemNumber();
        //generate random Vin
        String randomVin = GenericData.generateRandomVin();
        //generate random Vrm
        String randomVrm = GenericData.generateRandomVrm();
        //generate random trailerID
        String trailerID = GenericData.generateRandomTrailerId();
        // read post request body from file
        String postRequestBody = GenericData.readJsonValueFromFile("technical-records_trl_all_fields.json", "$");
        // create alteration to change system number in the request body with the random system number
        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$.systemNumber", randomSystemNumber, "", "REPLACE");
        // create alteration to change Vin in the request body with the random generated Vin
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        // create alteration to change primary vrm in the request body with the random generated primary vrm
        JsonPathAlteration alterationVrm = new JsonPathAlteration("$.primaryVrm", randomVrm, "", "REPLACE");
        JsonPathAlteration alterationTrailerId = new JsonPathAlteration("$.trailerId", trailerID, "", "REPLACE");
        JsonPathAlteration alterationFirstUseDate = new JsonPathAlteration("$.techRecord[0].firstUseDate", "", "", "DELETE");

        // initialize the alterations list with both declared alteration
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(alterationVin, alterationVrm,
                alterationSystemNumber,
                alterationTrailerId,
                alterationFirstUseDate));

        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBody, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(201);

        //GET tech-records
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsBySearchCriteria(randomVin, VehicleTechnicalRecordSearchCriteria.VIN);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(HttpStatus.SC_OK);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].recordCompleteness", "testable");

        String systemNumber = vehicleTechnicalRecordsSteps.getSystemNumber();

        // Read post request body from file
        String putRequestBody = GenericData.readJsonValueFromFile("technical-records_trl_all_fields_put.json","$");

        JsonPathAlteration alterationSystemNumberPut = new JsonPathAlteration("$", systemNumber, "systemNumber", "ADD_FIELD");

        List<JsonPathAlteration> alterationsPut = new ArrayList<>(Arrays.asList(alterationVin,
                alterationVrm,
                alterationTrailerId,
                alterationSystemNumberPut
        ));

        // PUT tech-records and verify the expected response
        vehicleTechnicalRecordsSteps.putVehicleTechnicalRecordsForVehicleWithAlterations(systemNumber,putRequestBody,alterationsPut);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(HttpStatus.SC_OK);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[1].statusCode","provisional" );
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[1].recordCompleteness","complete" );

        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[0].statusCode","archived" );
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[0].recordCompleteness","testable" );
    }

    @WithTag("Vtm")
    @Title("CVSB-11794 - AC1 - Record completeness = complete (core and non-core attributes) - Skeleton - Complete - PSV")
    @Test
    public void testVehicleTechnicalRecordForSkeletonPsv() {

        // POST tech-record,read the base tech-record JSON.
        String postRequestBody = GenericData.readJsonValueFromFile("technical-records_psv_all_fields_skeleton_11794.json", "$");

        // Create alteration to edit one or more fields in the request body
        String randomVin = GenericData.generateRandomVin();
        String randomSystemNumber = GenericData.generateRandomSystemNumber();
        String randomVrm = GenericData.generateRandomVrm();
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationVrm = new JsonPathAlteration("$.primaryVrm", randomVrm, "", "REPLACE");
        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$.systemNumber", randomSystemNumber, "", "REPLACE");

        // Collate the alterations
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(alterationVin,
                alterationVrm,
                alterationSystemNumber));

        String alteredJson = GenericData.applyJsonAlterations(postRequestBody, alterations);
        vehicleTechnicalRecordsSteps.insertRecordInDynamo(alteredJson,"technical-records","systemNumber");

        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsBySearchCriteria(randomVin, VehicleTechnicalRecordSearchCriteria.VIN);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(HttpStatus.SC_OK);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].recordCompleteness", "Skeleton");


        // Read post request body from file
        String putRequestBody = GenericData.readJsonValueFromFile("technical-records_psv_all_fields_put.json","$");

        JsonPathAlteration alterationSystemNumberPut = new JsonPathAlteration("$", randomSystemNumber, "systemNumber", "ADD_FIELD");

        List<JsonPathAlteration> alterationsPut = new ArrayList<>(Arrays.asList(alterationVin,
                alterationVrm,
                alterationSystemNumberPut
        ));

        // PUT tech-records and verify the expected response
        vehicleTechnicalRecordsSteps.putVehicleTechnicalRecordsForVehicleWithAlterations(randomSystemNumber,putRequestBody,alterationsPut);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(HttpStatus.SC_OK);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[1].statusCode","provisional" );
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[1].recordCompleteness","complete" );

        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[0].statusCode","archived" );
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[0].recordCompleteness","Skeleton" );
    }

    @WithTag("Vtm")
    @Title("CVSB-11794 - AC1 - Record completeness = complete (core and non-core attributes) - Skeleton - Complete - HGV")
    @Test
    public void testVehicleTechnicalRecordForSkeletonHgv() {


        // POST tech-record,read the base tech-record JSON.
        String postRequestBody = GenericData.readJsonValueFromFile("technical-records_hgv_all_fields_skeleton_11794.json", "$");

        // Create alteration to edit one or more fields in the request body
        String randomVin = GenericData.generateRandomVin();
        String randomSystemNumber = GenericData.generateRandomSystemNumber();
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$.systemNumber", randomSystemNumber, "", "REPLACE");

        // Collate the alterations
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(alterationVin,alterationSystemNumber));

        String alteredJson = GenericData.applyJsonAlterations(postRequestBody, alterations);
        vehicleTechnicalRecordsSteps.insertRecordInDynamo(alteredJson,"technical-records","systemNumber");

        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsBySearchCriteria(randomVin, VehicleTechnicalRecordSearchCriteria.VIN);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(HttpStatus.SC_OK);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].recordCompleteness", "Skeleton");


        // Read post request body from file
        String putRequestBody = GenericData.readJsonValueFromFile("technical-records_hgv_all_fields_put.json","$");

        JsonPathAlteration alterationSystemNumberPut = new JsonPathAlteration("$", randomSystemNumber, "systemNumber", "ADD_FIELD");

        List<JsonPathAlteration> alterationsPut = new ArrayList<>(Arrays.asList(alterationVin,
                alterationSystemNumberPut
        ));

        // PUT tech-records and verify the expected response
        vehicleTechnicalRecordsSteps.putVehicleTechnicalRecordsForVehicleWithAlterations(randomSystemNumber,putRequestBody,alterationsPut);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(HttpStatus.SC_OK);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[1].statusCode","provisional" );
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[1].recordCompleteness","complete" );

        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[0].statusCode","archived" );
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[0].recordCompleteness","Skeleton" );
    }

    @WithTag("Vtm")
    @Title("CVSB-11794 - AC1 - Record completeness = complete (core and non-core attributes) - Skeleton - Complete - Trailer")
    @Test
    public void testVehicleTechnicalRecordForSkeletonTrailer() {

        // POST tech-record,read the base tech-record JSON.
        String postRequestBody = GenericData.readJsonValueFromFile("technical-records_trl_all_fields_skeleton_11794.json", "$");

        // Create alteration to edit one or more fields in the request body
        String randomVin = GenericData.generateRandomVin();
        String randomSystemNumber = GenericData.generateRandomSystemNumber();
        String randomVrm = GenericData.generateRandomVrm();
        String trailerID = GenericData.generateRandomTrailerId();
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$.systemNumber", randomSystemNumber, "", "REPLACE");
        JsonPathAlteration alterationVrm = new JsonPathAlteration("$.primaryVrm", randomVrm, "", "REPLACE");
        JsonPathAlteration alterationTrailerId = new JsonPathAlteration("$.trailerId", trailerID, "", "REPLACE");

        // Collate the alterations
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(alterationVin,
                alterationSystemNumber,
                alterationVrm,
                alterationTrailerId));

        String alteredJson = GenericData.applyJsonAlterations(postRequestBody, alterations);
        vehicleTechnicalRecordsSteps.insertRecordInDynamo(alteredJson,"technical-records","systemNumber");

        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsBySearchCriteria(randomVin, VehicleTechnicalRecordSearchCriteria.VIN);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(HttpStatus.SC_OK);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].recordCompleteness", "Skeleton");


        // Read post request body from file
        String putRequestBody = GenericData.readJsonValueFromFile("technical-records_trl_all_fields_put.json","$");

        JsonPathAlteration alterationSystemNumberPut = new JsonPathAlteration("$", randomSystemNumber, "systemNumber", "ADD_FIELD");

        List<JsonPathAlteration> alterationsPut = new ArrayList<>(Arrays.asList(alterationVin,
                alterationVrm,
                alterationTrailerId,
                alterationSystemNumberPut
        ));

        // PUT tech-records and verify the expected response
        vehicleTechnicalRecordsSteps.putVehicleTechnicalRecordsForVehicleWithAlterations(randomSystemNumber,putRequestBody,alterationsPut);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(HttpStatus.SC_OK);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[1].statusCode","provisional" );
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[1].recordCompleteness","complete" );

        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[0].statusCode","archived" );
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[0].recordCompleteness","Skeleton" );
    }

    @WithTag("Vtm")
    @Title("CVSB-11794 - AC2 - Record completeness = complete (only core attributes) - Skeleton - Complete - Car")
    @Test
    public void testVehicleTechnicalRecordForSkeletonCar() {

        // POST tech-record,read the base tech-record JSON.
        String postRequestBody = GenericData.readJsonValueFromFile("technical-records_car_skeleton_11794.json", "$");

        // Generate random Vin
        String randomVin = GenericData.generateRandomVin();

        // Generate random SystemNumber
        String randomSystemNumber = GenericData.generateRandomSystemNumber();

        // Generate random Vrm
        String randomVrm = GenericData.generateRandomVrm();

        // Create alteration to change one more more fields in the request body
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$.systemNumber", randomSystemNumber, "", "REPLACE");
        JsonPathAlteration alterationVrm = new JsonPathAlteration("$.primaryVrm", randomVrm, "", "REPLACE");

        // Collate the alterations
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(alterationVin,
                alterationSystemNumber,
                alterationVrm));

        String alteredJson = GenericData.applyJsonAlterations(postRequestBody, alterations);
        vehicleTechnicalRecordsSteps.insertRecordInDynamo(alteredJson,"technical-records","systemNumber");

        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsBySearchCriteria(randomVin, VehicleTechnicalRecordSearchCriteria.VIN);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(HttpStatus.SC_OK);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].recordCompleteness", "Skeleton");


        // Read post request body from file
        String putRequestBody = GenericData.readJsonValueFromFile("technical-records_put_payload_car_11814.json","$");

        JsonPathAlteration alterationSystemNumberPut = new JsonPathAlteration("$", randomSystemNumber, "systemNumber", "ADD_FIELD");

        List<JsonPathAlteration> alterationsPut = new ArrayList<>(Arrays.asList(alterationVin,
                alterationVrm,
                alterationSystemNumberPut
        ));

        // PUT tech-records and verify the expected response
        vehicleTechnicalRecordsSteps.putVehicleTechnicalRecordsForVehicleWithAlterations(randomSystemNumber,putRequestBody,alterationsPut);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(HttpStatus.SC_OK);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[1].statusCode","provisional" );
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[1].recordCompleteness","complete" );

        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[0].statusCode","archived" );
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[0].recordCompleteness","Skeleton" );
    }

    @WithTag("Vtm")
    @Title("CVSB-11794 - AC2 - Record completeness = complete (only core attributes) - Skeleton - Complete - LGV")
    @Test
    public void testVehicleTechnicalRecordForSkeletonLgv() {

        // POST tech-record,read the base tech-record JSON.
        String postRequestBody = GenericData.readJsonValueFromFile("technical-records_lgv_skeleton_11794.json", "$");

        // Generate random Vin
        String randomVin = GenericData.generateRandomVin();

        // Generate random SystemNumber
        String randomSystemNumber = GenericData.generateRandomSystemNumber();

        // Generate random Vrm
        String randomVrm = GenericData.generateRandomVrm();

        // Create alteration to change one more more fields in the request body
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$.systemNumber", randomSystemNumber, "", "REPLACE");
        JsonPathAlteration alterationVrm = new JsonPathAlteration("$.primaryVrm", randomVrm, "", "REPLACE");

        // Collate the alterations
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(alterationVin,
                alterationSystemNumber,
                alterationVrm));

        String alteredJson = GenericData.applyJsonAlterations(postRequestBody, alterations);
        vehicleTechnicalRecordsSteps.insertRecordInDynamo(alteredJson,"technical-records","systemNumber");

        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsBySearchCriteria(randomVin, VehicleTechnicalRecordSearchCriteria.VIN);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(HttpStatus.SC_OK);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].recordCompleteness", "Skeleton");


        // Read post request body from file
        String putRequestBody = GenericData.readJsonValueFromFile("technical-records_put_payload_lgv_11814.json","$");

        JsonPathAlteration alterationSystemNumberPut = new JsonPathAlteration("$", randomSystemNumber, "systemNumber", "ADD_FIELD");

        List<JsonPathAlteration> alterationsPut = new ArrayList<>(Arrays.asList(alterationVin,
                alterationVrm,
                alterationSystemNumberPut
        ));

        // PUT tech-records and verify the expected response
        vehicleTechnicalRecordsSteps.putVehicleTechnicalRecordsForVehicleWithAlterations(randomSystemNumber,putRequestBody,alterationsPut);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(HttpStatus.SC_OK);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[1].statusCode","provisional" );
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[1].recordCompleteness","complete" );

        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[0].statusCode","archived" );
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[0].recordCompleteness","Skeleton" );
    }

    @WithTag("Vtm")
    @Title("CVSB-11794 - AC2 - Record completeness = complete (only core attributes) - Skeleton - Complete - Motorcycle")
    @Test
    public void testVehicleTechnicalRecordForSkeletonMotorcycle() {

        // POST tech-record,read the base tech-record JSON.
        String postRequestBody = GenericData.readJsonValueFromFile("technical-records_motorcycle_skeleton_11794.json", "$");

        // Generate random Vin
        String randomVin = GenericData.generateRandomVin();

        // Generate random SystemNumber
        String randomSystemNumber = GenericData.generateRandomSystemNumber();

        // Generate random Vrm
        String randomVrm = GenericData.generateRandomVrm();

        // Create alteration to change one more more fields in the request body
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$.systemNumber", randomSystemNumber, "", "REPLACE");
        JsonPathAlteration alterationVrm = new JsonPathAlteration("$.primaryVrm", randomVrm, "", "REPLACE");

        // Collate the alterations
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(alterationVin,
                alterationSystemNumber,
                alterationVrm));

        String alteredJson = GenericData.applyJsonAlterations(postRequestBody, alterations);
        vehicleTechnicalRecordsSteps.insertRecordInDynamo(alteredJson,"technical-records","systemNumber");

        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsBySearchCriteria(randomVin, VehicleTechnicalRecordSearchCriteria.VIN);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(HttpStatus.SC_OK);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].recordCompleteness", "Skeleton");


        // Read post request body from file
        String putRequestBody = GenericData.readJsonValueFromFile("technical-records_put_payload_motorcycle_11814.json","$");

        JsonPathAlteration alterationSystemNumberPut = new JsonPathAlteration("$", randomSystemNumber, "systemNumber", "ADD_FIELD");

        List<JsonPathAlteration> alterationsPut = new ArrayList<>(Arrays.asList(alterationVin,
                alterationVrm,
                alterationSystemNumberPut
        ));

        // PUT tech-records and verify the expected response
        vehicleTechnicalRecordsSteps.putVehicleTechnicalRecordsForVehicleWithAlterations(randomSystemNumber,putRequestBody,alterationsPut);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(HttpStatus.SC_OK);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[1].statusCode","provisional" );
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[1].recordCompleteness","complete" );

        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[0].statusCode","archived" );
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[0].recordCompleteness","Skeleton" );
    }

    @Ignore("Wait untill CVSB-15252 / CVSB-15896 is merged")
    @WithTag("Vtm")
    @Title("CVSB-15252 / CVSB-15896 - Updating the Vehicles API to support plate generation + saving of the new tech record")
    public void testVehicleTechnicalRecordPlateGeneration_Trl() {

        // TEST SETUP
        // generate random Vin
        String randomVin = GenericData.generateRandomVin();
        // generate random Vrm
        String randomVrm = GenericData.generateRandomVrm();
        // generate random plateSerialNumber
        String randomPlateSerialNumber = RandomStringUtils.randomNumeric(6);
        // read post request body from file
        String postRequestBodyTrl = GenericData.readJsonValueFromFile("technical-records_trl_all_fields_15252.json", "$");
        // create alteration to change Vin in the post request body with the random generated Vin
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        // create alteration to change primary vrm in the request body with the random generated primary vrm
        JsonPathAlteration alterationVrm = new JsonPathAlteration("$.primaryVrm", randomVrm, "", "REPLACE");
        // initialize the alterations list with both declared alterations
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(
                alterationVin,
                alterationVrm
        ));

        // POST a plate serial number
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBodyTrl, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(201);

        // GET the tech record
        String systemNumber = vehicleTechnicalRecordsSteps.getSystemNumberUsingVin(randomVin);
        String trailerId = vehicleTechnicalRecordsSteps.getValueFromBody("[0].trailerId");

        String putRequestBodyTrl = GenericData.readJsonValueFromFile("technical-records_trl_all_fields_put_15252.json", "$");
        JsonPathAlteration alterationTrailerId = new JsonPathAlteration("$.trailerId", trailerId, "", "REPLACE");
        List<JsonPathAlteration> alterations2 = new ArrayList<>(Arrays.asList(
                alterationVin,
                alterationVrm,
                alterationTrailerId
        ));

        // PUT a tech record without plate serial number
        vehicleTechnicalRecordsSteps.putVehicleTechnicalRecordsForVehicleWithAlterations(systemNumber, putRequestBodyTrl, alterations2);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);

        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsByStatus(randomVin, VehicleTechnicalRecordStatus.ALL);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        // CVSB-15896 - AC1 Vehicles API is updated to include the new attributes required for plate generation:
        vehicleTechnicalRecordsSteps.fieldInPathShouldExist("[0].techRecord[1].plates[0]","toEmailAddress");
        vehicleTechnicalRecordsSteps.fieldInPathShouldExist("[0].techRecord[1].plates[0]","plateSerialNumber");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[1].reasonForCreation","VTG6 and VTG7 Plates Generated");
        String plateSerialNumber = vehicleTechnicalRecordsSteps.getValueFromBody("[0].techRecord[1].plates[0].plateSerialNumber");
        System.out.println("Generated Plate Serial number: " + plateSerialNumber);

        String expectedPlateSerialNumber = String.valueOf(Integer.parseInt(plateSerialNumber)+1);
        System.out.println("Expected Plate Serial number: " + expectedPlateSerialNumber);

        // PUT the same plate serial number again without the plate number
        vehicleTechnicalRecordsSteps.putVehicleTechnicalRecordsForVehicleWithAlterations(systemNumber, putRequestBodyTrl, alterations2);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);

        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsByStatus(randomVin, VehicleTechnicalRecordStatus.ALL);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.fieldInPathShouldExist("[0].techRecord[2].plates[0]","plateSerialNumber");
        //  CVSB-15252 - AC2 - PUT request: The next sequential plate serial number is assigned for this plate
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[2].plates[0].plateSerialNumber", expectedPlateSerialNumber);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[2].reasonForCreation","VTG6 and VTG7 Plates Generated");

        // PUT the same plate serial number again with a plate number
        JsonPathAlteration alterationPlate = new JsonPathAlteration(".techRecord[0].plates[0]", plateSerialNumber, "plateSerialNumber", "ADD_FIELD");
        List<JsonPathAlteration> alterations3 = new ArrayList<>(Arrays.asList(
                alterationVin,
                alterationVrm,
                alterationTrailerId,
                alterationPlate
        ));
        vehicleTechnicalRecordsSteps.putVehicleTechnicalRecordsForVehicleWithAlterations(systemNumber, putRequestBodyTrl, alterations3);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        System.out.println("Expected Plate Serial number: " + plateSerialNumber);
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsByStatus(randomVin, VehicleTechnicalRecordStatus.ALL);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.fieldInPathShouldExist("[0].techRecord[3].plates[0]","plateSerialNumber");
        // CVSB-15896 - AC2. PUT request is submitted against tech records
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[3].plates[0].plateSerialNumber", plateSerialNumber);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[3].reasonForCreation","no reason");
    }

}
