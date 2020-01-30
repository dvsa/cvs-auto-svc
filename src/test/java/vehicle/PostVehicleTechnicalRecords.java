package vehicle;


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
        String postRequestBody = GenericData.readJsonValueFromFile("technical-records_current.json","$");
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
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[0].statusCode", "current");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[1].statusCode", "archived");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord.size()", 2);
    }

    @WithTag("Vtm")
    @Title("CVSB-10209 - AC2 - A new tech record is created when making POST request with all hgv attributes, optional and mandatory")
    @Test
    public void testVehicleTechnicalRecordsPostAllHgvAttributes() {
        // TEST SETUP
        // generate random Vin
        String randomVin = GenericData.generateRandomVin();
        //generate random Vrm
        String randomPrimaryVrm = GenericData.generateRandomVrm();
        //generate random Vrm
        String randomSecondaryVrm = GenericData.generateRandomVrm();
        // read user name making the request
        String userName = GenericData.readJsonValueFromFile("technical-records_hgv_all_fields.json","$.msUserDetails.msUser");
        // read user id making the request
        String userId = GenericData.readJsonValueFromFile("technical-records_hgv_all_fields.json","$.msUserDetails.msOid");
        // read post request body from file
        String postRequestBody = GenericData.readJsonValueFromFile("technical-records_hgv_all_fields.json","$");
        String techRecord = GenericData.readJsonValueFromFile("technical-records_hgv_all_fields.json","$.techRecord[0]");
        // create alteration to change Vin in the request body with the random generated Vin
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin,"","REPLACE");
        // create alteration to change primary vrm in the request body with the random generated primary vrm
        JsonPathAlteration alterationVrm = new JsonPathAlteration("$.primaryVrm", randomPrimaryVrm,"","REPLACE");
        // create alteration to change the secondary vrm in the request body with the random generated secondary vrm
        JsonPathAlteration alterationVrms = new JsonPathAlteration("$.secondaryVrms[0]", randomSecondaryVrm,"","REPLACE");
        // initialize the alterations list with only the alterations for changing the Vin and primay vrm
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(alterationVin, alterationVrm, alterationVrms));

        // TEST
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBody, alterations);
        // validate AC2
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(201);
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsByStatus(randomVin, VehicleTechnicalRecordStatus.ALL);

        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("vrms.size", 2);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("vrms[0].vrm", randomPrimaryVrm);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("vrms[0].isPrimary", true);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("vrms[1].vrm", randomSecondaryVrm);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("vrms[1].isPrimary", false);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord.size()", 1);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[0].statusCode", "provisional");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[0].createdByName", userName);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[0].createdById", userId);

        vehicleTechnicalRecordsSteps.validateResponseContainsJson("techRecord[0]", techRecord);
    }
}
