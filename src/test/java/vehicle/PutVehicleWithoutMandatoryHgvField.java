package vehicle;

import data.GenericData;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import junit.framework.TestCase;
import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.junit.annotations.TestData;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import steps.*;
import util.BasePathFilter;
import util.JsonPathAlteration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static io.restassured.RestAssured.given;
import static util.WriterReader.saveUtils;

@RunWith(SerenityParameterizedRunner.class)
public class PutVehicleWithoutMandatoryHgvField extends TestCase {

    static String randomVin;

    @BeforeClass
    public static void createRecord() {
        // TEST SETUP
        // generate random Vin
        randomVin = GenericData.generateRandomVin();
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

        String alteredBody = GenericData.applyJsonAlterations(postRequestBodyHgv, alterations);
        Response response = given().filters(new BasePathFilter())
                .contentType(ContentType.JSON)
                .body(alteredBody)
                .log().method().log().uri().log().body()
                .post("/vehicles");

        if (response.getStatusCode() == 401 || response.getStatusCode() == 403) {
            saveUtils();
            response = given().filters(new BasePathFilter())
                    .contentType(ContentType.JSON)
                    .body(alteredBody)
                    .log().method().log().uri().log().body()
                    .post("/vehicles");
        }

        Assert.assertEquals(201, response.getStatusCode());
    }

    @Steps
    VehicleTechnicalRecordsSteps vehicleTechnicalRecordsSteps;

    @TestData
    public static Collection<Object[]> testData(){
        return Arrays.asList(new Object[][]{
                {"$.techRecord[0].vehicleType"},
                {"$.techRecord[0].manufactureYear"},
                {"$.techRecord[0].noOfAxles"},
                {"$.techRecord[0].brakes.dtpNumber"},
                {"$.techRecord[0].speedLimiterMrk"},
                {"$.techRecord[0].tachoExemptMrk"},
                {"$.techRecord[0].euroStandard"},
                {"$.techRecord[0].fuelPropulsionSystem"},
                {"$.techRecord[0].roadFriendly"},
                {"$.techRecord[0].drawbarCouplingFitted"},
                {"$.techRecord[0].vehicleClass.description"},
                {"$.techRecord[0].vehicleConfiguration"},
                {"$.techRecord[0].offRoad"},
                {"$.techRecord[0].numberOfWheelsDriven"},
                {"$.techRecord[0].euVehicleCategory"},
                {"$.techRecord[0].approvalType"},
                {"$.techRecord[0].make"},
                {"$.techRecord[0].model"},
                {"$.techRecord[0].bodyType.description"},
                {"$.techRecord[0].grossGbWeight"},
                {"$.techRecord[0].grossDesignWeight"},
                {"$.techRecord[0].trainGbWeight"},
                {"$.techRecord[0].maxTrainGbWeight"},
                {"$.techRecord[0].axles[0].axleNumber"},
                {"$.techRecord[0].axles[0].weights.gbWeight"},
                {"$.techRecord[0].axles[0].weights.designWeight"},
                {"$.techRecord[0].axles[0].tyres.tyreCode"},
                {"$.techRecord[0].axles[0].tyres.tyreSize"},
                {"$.techRecord[0].axles[0].tyres.fitmentCode"},
                {"$.techRecord[0].dimensions.width"},
                {"$.techRecord[0].dimensions.length"},
                {"$.techRecord[0].frontAxleToRearAxle"},
                {"$.techRecord[0].applicantDetails.name"},
                {"$.techRecord[0].applicantDetails.address1"},
                {"$.techRecord[0].applicantDetails.address2"},
                {"$.techRecord[0].applicantDetails.postTown"},
                {"$.techRecord[0].reasonForCreation"}
        });
    }

    private final String jsonPath;

    public PutVehicleWithoutMandatoryHgvField(String jsonPath) {
        this.jsonPath = jsonPath;
    }

    @WithTag("Vtm")
    @Title("CVSB-10211 - AC1 - Attempt to update a vehicle without a mandatory field")
    @Test
    public void testValidatePutRequestWithoutMandatoryHgvAttribute() {
        String putRequestBodyHgv = GenericData.readJsonValueFromFile("technical-records_hgv_all_fields.json", "$");
        JsonPathAlteration restriction = new JsonPathAlteration(jsonPath, "", "", "DELETE");
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(restriction));
        vehicleTechnicalRecordsSteps.putVehicleTechnicalRecordsForVehicleWithAlterations(randomVin, putRequestBodyHgv, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(400);
    }
}
