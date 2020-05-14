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
public class PostVehicleWithoutMandatoryPsvField {

    @Steps
    VehicleTechnicalRecordsSteps vehicleTechnicalRecordsSteps;

    @TestData
    public static Collection<Object[]> testData(){
        return Arrays.asList(new Object[][]{
                {"$.primaryVrm"},
                {"$.vin"},
                {"$.techRecord[0].vehicleType"},
                {"$.techRecord[0].manufactureYear"},
                {"$.techRecord[0].noOfAxles"},
                {"$.techRecord[0].brakes.dtpNumber"},
                {"$.techRecord[0].speedLimiterMrk"},
                {"$.techRecord[0].tachoExemptMrk"},
                {"$.techRecord[0].euroStandard"},
                {"$.techRecord[0].fuelPropulsionSystem"},
                {"$.techRecord[0].vehicleClass.description"},
                {"$.techRecord[0].vehicleConfiguration"},
                {"$.techRecord[0].numberOfWheelsDriven"},
                {"$.techRecord[0].euVehicleCategory"},
                {"$.techRecord[0].seatsUpperDeck"},
                {"$.techRecord[0].seatsUpperDeck"},
                {"$.techRecord[0].standingCapacity"},
                {"$.techRecord[0].vehicleSize"},
                {"$.techRecord[0].numberOfSeatbelts"},
                {"$.techRecord[0].approvalType"},
                {"$.techRecord[0].bodyMake"},
                {"$.techRecord[0].bodyModel"},
                {"$.techRecord[0].chassisMake"},
                {"$.techRecord[0].chassisModel"},
                {"$.techRecord[0].bodyType.description"},
                {"$.techRecord[0].grossKerbWeight"},
                {"$.techRecord[0].grossLadenWeight"},
                {"$.techRecord[0].grossGbWeight"},
                {"$.techRecord[0].grossDesignWeight"},
                {"$.techRecord[0].axles[0].weights.kerbWeight"},
                {"$.techRecord[0].axles[0].weights.ladenWeight"},
                {"$.techRecord[0].axles[0].weights.gbWeight"},
                {"$.techRecord[0].axles[0].weights.designWeight"},
                {"$.techRecord[0].axles[0].axleNumber"},
                {"$.techRecord[0].axles[0].tyres.tyreCode"},
                {"$.techRecord[0].axles[0].tyres.tyreSize"},
                {"$.techRecord[0].axles[0].tyres.speedCategorySymbol"},
                {"$.techRecord[0].axles[0].tyres.fitmentCode"},
                {"$.techRecord[0].brakes.brakeCode"},
                {"$.techRecord[0].brakes.dataTrBrakeOne"},
                {"$.techRecord[0].brakes.dataTrBrakeTwo"},
                {"$.techRecord[0].brakes.dataTrBrakeThree"},
                {"$.techRecord[0].brakes.brakeForceWheelsNotLocked.parkingBrakeForceA"},
                {"$.techRecord[0].brakes.brakeForceWheelsNotLocked.secondaryBrakeForceA"},
                {"$.techRecord[0].brakes.brakeForceWheelsNotLocked.serviceBrakeForceA"},
                {"$.techRecord[0].brakes.brakeForceWheelsUpToHalfLocked.parkingBrakeForceB"},
                {"$.techRecord[0].brakes.brakeForceWheelsUpToHalfLocked.secondaryBrakeForceB"},
                {"$.techRecord[0].brakes.brakeForceWheelsUpToHalfLocked.serviceBrakeForceB"},
                {"$.techRecord[0].dda.certificateIssued"},
                {"$.techRecord[0].reasonForCreation"}
        });
    }

    private final String jsonPath;

    public PostVehicleWithoutMandatoryPsvField(String jsonPath) {
        this.jsonPath = jsonPath;
    }

    @WithTag("Vtm")
    @Title("CVSB-10241 - AC1 - Attempt to create a new psv without a mandatory field")
    @Test
    public void testValidateRequestWithoutMandatoryPsvField() {
        // TEST SETUP
        // generate random Vin
        String randomVin = GenericData.generateRandomVin();
        // generate random Vrm
        String randomVrm = GenericData.generateRandomVrm();
        // read post request body from file
        String postRequestBodyHgv = GenericData.readJsonValueFromFile("technical-records_psv_all_fields.json", "$");
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
