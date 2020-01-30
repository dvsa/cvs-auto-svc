package vehicle;

import data.GenericData;
import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.junit.annotations.TestData;
import org.junit.Test;
import org.junit.runner.RunWith;
import steps.VehicleTechnicalRecordsSteps;
import util.JsonPathAlteration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@RunWith(SerenityParameterizedRunner.class)
public class HgvMandatoryFieldsOnPostRequest {

    @Steps
    VehicleTechnicalRecordsSteps vehicleTechnicalRecordsSteps;

    @TestData
    public static Collection<Object[]> testData(){
        return Arrays.asList(new Object[][]{
                {"$.primaryVrm"},
        });
    }

    private final String jsonPath;

    public HgvMandatoryFieldsOnPostRequest(String jsonPath) {
        this.jsonPath = jsonPath;
    }

    @WithTag("Vtm")
    @Title("CVSB-10209 - AC2 - making POST request without a mandatory field")
    @Test
    public void testValidateRequestWithoutMandatoryHgvAttribute() {
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


        // validate 400 response when making POST request without a mandatory field
        JsonPathAlteration restriction = new JsonPathAlteration(jsonPath, "", "", "DELETE");
        alterations.add(restriction);
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBodyHgv, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(400);
    }
}
