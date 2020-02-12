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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(SerenityRunner.class)
public class PostVehicleTechnicalRecords {

    @Steps
    VehicleTechnicalRecordsSteps vehicleTechnicalRecordsSteps;

    @WithTag("Vtm")
    @Title("CVSB-7885 - AC1 - API Consumer creates a technical record for a vehicle with a specific vin" +
            "AC5 - Can create a new vehicle entry using a unique VIN and multiple entries in the `techRecord` array of the request body")
    @Test
    public void testCreateVehicleTechnicalRecord() {
        // TEST SETUP
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
        // create alteration to change Vin in the request body with the random generated Vin
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin,"","REPLACE");
        // create alteration to change primary vrm in the request body with the random generated primary vrm
        JsonPathAlteration alterationVrm = new JsonPathAlteration("$.primaryVrm", randomVrm,"","REPLACE");
        // initialize the alterations list with both declared alteration
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(alterationTechRecord, alterationVin, alterationVrm));

        // TEST
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBody, alterations);
        // validate AC1
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(201);
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsByStatus(randomVin, VehicleTechnicalRecordStatus.ALL);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("vin", randomVin);
        // validated AC5
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[0].statusCode", "provisional");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[1].statusCode", "archived");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord.size()", 2);
    }

    @WithTag("Vtm")
    @Title("CVSB-10209 - AC2 -  HGV vehicle is created, and the appropriate attributes are automatically set")
    @Test
    public void testCreateVehicleWithOnlyMandatoryFields() {
        // TEST SETUP
        //generate random Vin
        String randomVin = GenericData.generateRandomVin();
        //generate random Vrm
        String randomVrm = GenericData.generateRandomVrm();
        // read post request body from file
        String postRequestBody = GenericData.readJsonValueFromFile("technical-records_hgv_mandatory_fields.json","$");
        // read the tech record from the file used for post request body
        String techRecord = GenericData.readJsonValueFromFile("technical-records_hgv_mandatory_fields.json","$.techRecord[0]");
        // create alteration to change Vin in the request body with the random generated Vin
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin,"","REPLACE");
        // create alteration to change primary vrm in the request body with the random generated primary vrm
        JsonPathAlteration alterationVrm = new JsonPathAlteration("$.primaryVrm", randomVrm,"","REPLACE");
        // initialize the alterations list with both declared alteration
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(alterationVin, alterationVrm));

        // TEST
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBody, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(201);
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsByStatus(GenericData.getPartialVinFromVin(randomVin), VehicleTechnicalRecordStatus.ALL);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        // validate AC2
        vehicleTechnicalRecordsSteps.validateResponseContainsJson("techRecord[0]", techRecord);
    }

    @WithTag("Vtm")
    @Title("CVSB-10213 - AC1 - Partial VIN is auto-populated when creating a new vehicle" +
            "AC2 - Vehicle class code is auto-populated when creating a new vehicle" +
            "AC3 - Body type code is auto-populated when creating a new vehicle")
    @Test
    public void testCreateVehicleAutoPopulateFields() {
        // TEST SETUP
        //generate random Vin
        String randomVin = GenericData.generateRandomVin();
        //generate random Vrm
        String randomVrm = GenericData.generateRandomVrm();
        // read post request body from file
        String postRequestBody = GenericData.readJsonValueFromFile("technical-records_hgv_all_fields.json","$");
        String vehicleClassDescription = GenericData.extractStringValueFromJsonString(postRequestBody, "$.techRecord[0].vehicleClass.description");
        String bodyTypeDescription = GenericData.extractStringValueFromJsonString(postRequestBody, "$.techRecord[0].bodyType.description");
        // create alteration to change Vin in the request body with the random generated Vin
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin,"","REPLACE");
        // create alteration to change primary vrm in the request body with the random generated primary vrm
        JsonPathAlteration alterationVrm = new JsonPathAlteration("$.primaryVrm", randomVrm,"","REPLACE");
        // initialize the alterations list with both declared alteration
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(alterationVin, alterationVrm));

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
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[0].vehicleClass.code", vehicleClass.getCode());
        // validated AC3
        BodyType bodyType = BodyType.ARTICULATED;
        for (BodyType b : BodyType.values()) {
            if (b.getDescription().equals(bodyTypeDescription)) {
                bodyType = b;
            }
        }
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[0].bodyType.code", bodyType.getCode());
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

    @Title("CVSB-10752 - AC1 API Consumer retrieve the Vehicle Technical Records - Multiple Vehicles")
    @Test
    public void testTechnicalRecordForMultipleVehicles() {

        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecords("484009");
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord.size()", 3);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].systemNumber", "10044326");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[1].systemNumber", "10044320");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[2].systemNumber", "12055422");
    }
}
