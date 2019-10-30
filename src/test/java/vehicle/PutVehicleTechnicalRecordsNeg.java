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
public class PutVehicleTechnicalRecordsNeg {

    @Steps
    VehicleTechnicalRecordsSteps vehicleTechnicalRecordsSteps;

    // generate random Vin
    private String randomVin = GenericData.generateRandomVin();
    //read post request body from file
    private String postRequestBody = GenericData.readJsonValueFromFile("technical-records_current.json","$");
    // create alteration make the techRecord array from the body request empty
    private JsonPathAlteration emptyTechRecordArrayAlteration = new JsonPathAlteration("$.techRecord","[]","","REPLACE");
    // create alteration to change Vin in the request body with the random generated Vin
    private JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin,"","REPLACE");
    // initialize the alterations list with only the alteration for changing the Vin
    private List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(alterationVin));


    @WithTag("Vtm")
    @Title("CVSB-7885 - AC8 - can not update an existing vehicle entry when the request body doesn't contain at least one element in the `techRecord` array")
    @Test
    public void testUpdateVehicleTechnicalRecordTechRecordEmptyArray() {
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBody, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(201);
        // add alteration for setting the TechRecord array to be empty
        alterations.add(emptyTechRecordArrayAlteration);
        vehicleTechnicalRecordsSteps.putVehicleTechnicalRecordsForVehicleWithAlterations(randomVin, postRequestBody, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(400);
    }
}
