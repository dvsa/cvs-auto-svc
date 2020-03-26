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

    @WithTag("Vtm")
    @Title("CVSB-7885 - AC2 - Can not create a new vehicle entry using an existing VIN from the DB " +
            "AC4 - can not create a new vehicle entry when the request body doesn't contain at least one element in the `techRecord` array")
    @Test
    public void testCreateVehicleTechnicalRecordExistingVinEmptyTechRecordArray() {
        // TEST SETUP
        // generate random Vin
        String randomVin = GenericData.generateRandomVin();
        //generate random Vrm
        String randomVrm = GenericData.generateRandomVrm();
        // read post request body from file
        String requestBody = GenericData.readJsonValueFromFile("technical-records_hgv_all_fields.json","$");
        // create alteration make the techRecord array from the body request empty
        JsonPathAlteration emptyTechRecordArrayAlteration = new JsonPathAlteration("$.techRecord","[]","","REPLACE");
        // create alteration to change Vin in the request body with the random generated Vin
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin,"","REPLACE");
        // create alteration to change primary vrm in the request body with the random generated primary vrm
        JsonPathAlteration alterationVrm = new JsonPathAlteration("$.primaryVrm", randomVrm,"","REPLACE");
        // initialize the alterations list with only the alterations for changing the Vin and primay vrm
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(alterationVin, alterationVrm));

        // TEST
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

    @WithTag("Vtm")
    @Title("CVSB-10210 - AC2 - Attempt to create a new hgv with a not applicable field")
    @Test
    public void testValidateRequestNotApplicableHgvField() {
        // TEST SETUP
        // generate random Vin
        String randomVin = GenericData.generateRandomVin();
        // generate random systemNumber
        String randomSystemNumber = GenericData.generateRandomVin();

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


        // validate 400 response when making POST request with a not applicable field
        JsonPathAlteration notApplicableField = new JsonPathAlteration("$.techRecord[0]", "1234", "brakeCode", "ADD_FIELD");
        alterations.add(notApplicableField);
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBodyHgv, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(400);
    }

    @WithTag("Vtm")
    @Title("CVSB-10241 - AC2 - Attempt to create a new psv with a not applicable field")
    @Test
    public void testValidateRequestNotApplicablePsvField() {
        // TEST SETUP
        // generate random Vin
        String randomVin = GenericData.generateRandomVin();
        // generate random systemNumber
        String randomSystemNumber = GenericData.generateRandomSystemNumber();
        // generate random Vrm
        String randomVrm = GenericData.generateRandomVrm();
        // read post request body from file
        String postRequestBodyHgv = GenericData.readJsonValueFromFile("technical-records_psv_all_fields.json", "$");
        // create alteration to change Vin in the post request body with the random generated Vin
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        // create alteration to change systemNumber in the post request body with the random generated systemNumber
        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$.systemNumber", randomSystemNumber, "", "REPLACE");
        // create alteration to change primary vrm in the request body with the random generated primary vrm
        JsonPathAlteration alterationVrm = new JsonPathAlteration("$.primaryVrm", randomVrm, "", "REPLACE");

        // initialize the alterations list with both declared alterations
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(alterationVin, alterationVrm, alterationSystemNumber));


        // validate 400 response when making POST request with a not applicable field
        JsonPathAlteration notApplicableField = new JsonPathAlteration("$.techRecord[0]", 1234, "maxTrainEecWeight", "ADD_FIELD");
        alterations.add(notApplicableField);
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBodyHgv, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(400);
    }
}
