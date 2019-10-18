package vehicle;


import data.GenericData;
import model.vehicles.VehicleTechnicalRecordStatus;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import net.thucydides.core.annotations.WithTag;
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

    //generate random Vin
    private String randomVin = GenericData.generateRandomVin();
    // read post request body from file
    private String postRequestBody = GenericData.readJsonValueFromFile("technical-records_current.json","$");
    //  read another tech record entry from different file
    private String additionalTechRecord = GenericData.readJsonValueFromFile("technical-records_archived.json","$.techRecord[0]");
    // create alteration to add one more tech record to in the request body
    private JsonPathAlteration alterationTechRecord = new JsonPathAlteration("$.techRecord", additionalTechRecord,"","ADD_VALUE");
    // create alteration to change Vin in the request body with the random generated Vin
    private JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin,"","REPLACE");
    // initialize the alterations list with both declared alteration
    private List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(alterationTechRecord, alterationVin));


    @WithTag("Vtm")
    @Title("CVSB-7885 - AC1 - API Consumer creates a technical record for a vehicle with a specific vin" +
            "AC5 - Can create a new vehicle entry using a unique VIN and multiple entries in the `techRecord` array of the request body")
    @Test
    public void testCreateVehicleTechnicalRecord() {
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
}
