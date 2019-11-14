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
    @Title("CVSB-7885 - AC6 - Can update all fields for a vehicle entry, except the VIN, partial VIN, trailer id, primary and secondary VRM" +
            "AC7 - Can not update VIN, partial vin or primary/secondary vrm" +
            "AC9 - Can add or remove tech records for a vehicle entry")
    @Test
    public void testUpdateVehicleTechnicalRecord() {
        // TEST SETUP
        // generate random Vin
        String randomVin = GenericData.generateRandomVin();
        //generate random Vrm
        String randomVrm = GenericData.generateRandomVrm();
        // read post request body from file
        String postRequestBody = GenericData.readJsonValueFromFile("technical-records_current.json","$");
        // read put request body from file
        String putRequestBody = GenericData.readJsonValueFromFile("technical-records_psv.json","$");
        // read the tech record from the file used for post request body
        String oldTechRecord = GenericData.readJsonValueFromFile("technical-records_current.json","$.techRecord[0]");
        // read the tech record from the file used for post request body
        String newTechRecord = GenericData.readJsonValueFromFile("technical-records_psv.json","$.techRecord[0]");
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
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord.size()", 1);
        vehicleTechnicalRecordsSteps.validateResponseContainsJson("techRecord[0]", newTechRecord);
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
        vehicleTechnicalRecordsSteps.validateResponseContainsJson("techRecord[0]", newTechRecord);
    }

    @WithTag("Vtm")
    @Title("CVSB-8677 - AC1 - Add adrDetails{} object onto an existing tech record with status current" +
            "AC2 - Update adrDetails{} object on an existing tech record" +
            "AC3 - All attributes are returned" +
            "AC4 - Adding of adrDetails{} is audited" +
            "AC5 - Updating of adrDetails{} is audited")
    @Test
    public void testAddAdrDetailsObjectVehicleTechnicalRecord() {
        //TEST SETUP
        // generate random Vin
        String randomVin = GenericData.generateRandomVin();
        //generate random Vrm
        String randomVrm = GenericData.generateRandomVrm();
        // read post request body from file
        String postRequestBodyHgv = GenericData.readJsonValueFromFile("technical-records_hgv.json","$");
        // read put request body from file for adding battery adr details
        String putRequestBodyAdrDetailsBattery = GenericData.readJsonValueFromFile("technical-records_adr_details_battery.json","$");
        // read the adr details from the file used for put request body with battery adr details
        String adrDetailsBattery = GenericData.readJsonValueFromFile("technical-records_adr_details_battery.json","$.techRecord[0].adrDetails");
        // create alteration to change Vin in the post request body with the random generated Vin
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin,"","REPLACE");
        // create alteration to change primary vrm in the request body with the random generated primary vrm
        JsonPathAlteration alterationVrm = new JsonPathAlteration("$.primaryVrm", randomVrm,"","REPLACE");
        // initialize the alterations list with only the alterations for changing the Vin and the primary vrm
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(alterationVin, alterationVrm));
        // read put request body from file for adding tank adr details
        String putRequestBodyAdrDetailsTank = GenericData.readJsonValueFromFile("technical-records_adr_details_tank.json","$");
        // read the adr details from the file used for put request body with tank adr details
        String adrDetailsTank = GenericData.readJsonValueFromFile("technical-records_adr_details_tank.json","$.techRecord[0].adrDetails");

        //TEST
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBodyHgv, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(201);
        // validate AC1
        vehicleTechnicalRecordsSteps.putVehicleTechnicalRecordsForVehicle(randomVin, putRequestBodyAdrDetailsBattery);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsByStatus(randomVin, VehicleTechnicalRecordStatus.ALL);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord.size()", 3);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[0].statusCode", "provisional");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[1].statusCode", "archived");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[1].updateType", "adrUpdate");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[1].lastUpdatedByName", "catalin");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[1].lastUpdatedById", "123243424-234234245");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[2].statusCode", "current");
        vehicleTechnicalRecordsSteps.valuesForFieldsInPathShouldEqual("techRecord[1].lastUpdatedAt","techRecord[2].createdAt");
        vehicleTechnicalRecordsSteps.fieldInPathShouldExist("techRecord[2]", "adrDetails");
        // validated AC3
        vehicleTechnicalRecordsSteps.validateResponseContainsJson("techRecord[2].adrDetails", adrDetailsBattery);
        // Validate AC4
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[2].createdByName", "catalin");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[2].createdById", "123243424-234234245");
        // Validate AC2
        vehicleTechnicalRecordsSteps.putVehicleTechnicalRecordsForVehicle(randomVin, putRequestBodyAdrDetailsTank);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsByStatus(randomVin, VehicleTechnicalRecordStatus.ALL);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord.size()", 4);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[0].statusCode", "provisional");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[1].statusCode", "archived");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[2].statusCode", "archived");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[2].updateType", "adrUpdate");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[2].lastUpdatedByName", "dragos");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[2].lastUpdatedById", "11112222-33334444");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[3].statusCode", "current");
        vehicleTechnicalRecordsSteps.valuesForFieldsInPathShouldEqual("techRecord[2].lastUpdatedAt","techRecord[3].createdAt");
        // validate AC5
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[3].createdByName", "dragos");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[3].createdById", "11112222-33334444");
        vehicleTechnicalRecordsSteps.validateResponseContainsJson("techRecord[3].adrDetails", adrDetailsTank);
    }
}
