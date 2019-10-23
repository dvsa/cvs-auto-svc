package vehicle;


import data.GenericData;
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
public class PostVehicleTechnicalRecordsNeg {

    @Steps
    VehicleTechnicalRecordsSteps vehicleTechnicalRecordsSteps;

    // generate random Vin
    private String randomVin = GenericData.generateRandomVin();
    // read post request body from file
    private String requestBody = GenericData.readJsonValueFromFile("technical-records_current.json","$");
    // create alteration make the techRecord array from the body request empty
    private JsonPathAlteration emptyTechRecordArrayAlteration = new JsonPathAlteration("$.techRecord","[]","","REPLACE");
    // create alteration to change Vin in the request body with the random generated Vin
    private JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin,"","REPLACE");
    // initialize the alterations list with only the alteration for changing the Vin
    private List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(alterationVin));


    @Ignore
    @WithTag("Vtm")
    @Title("CVSB-7885 - AC2 - Can not create a new vehicle entry using an existing VIN from the DB" +
            "AC4 - can not create a new vehicle entry when the request body doesn't contain at least one element in the `techRecord` array")
    @Test
    public void testCreateVehicleTechnicalRecordExistingVinEmptyTechRecordArray() {
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(requestBody, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(201);
        // validate AC2
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(requestBody, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(400);
        //add in the alterations list the alteration for the empty techRecord array
        alterations.add(emptyTechRecordArrayAlteration);
        // validate AC4
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(requestBody, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(400);
    }
}
