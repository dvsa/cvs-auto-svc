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
import steps.VehicleTechnicalRecordsSteps;
import util.BasePathFilter;
import util.JsonPathAlteration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static io.restassured.RestAssured.given;
import static util.WriterReader.saveUtils;

@RunWith(SerenityParameterizedRunner.class)
public class PutVehicleUpdateHgvFields extends TestCase {

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
                {"$.techRecord[0].axles[0].parkingBrakeMrk", true},
//                {"$.techRecord[0].axles[0].tyres.dataTrAxles", 1},
//                {"$.techRecord[0].axles[0].tyres.fitmentCode", "double"},
//                {"$.techRecord[0].axles[0].tyres.plyRating", "rating"},
//                {"$.techRecord[0].axles[0].tyres.tyreCode", 463},
//                {"$.techRecord[0].axles[0].tyres.tyreSize", "11-21.5"},
//                {"$.techRecord[0].axles[0].weights.designWeight", "6500"},
//                {"$.techRecord[0].axles[0].weights.eecWeight", "6500"},
//                {"$.techRecord[0].axles[0].weights.gbWeight", "6100"},
//                {"$.techRecord[0].bodyType.description", "single decker"},
//                {"$.techRecord[0].brakes.dtpNumber", "3798B"},
//                {"$.techRecord[0].conversionRefNo", "refNo"},
//                {"$.techRecord[0].dimensions.axleSpacing[0].axles", "2-1"},
//                {"$.techRecord[0].dimensions.axleSpacing[0].value", "11000"},
//                {"$.techRecord[0].drawbarCouplingFitted", true},
//                {"$.techRecord[0].euroStandard", "3"},
//                {"$.techRecord[0].fuelPropulsionSystem", "Hybrid"},
//                {"$.techRecord[0].frontAxleToRearAxle", 2000},
//                {"$.techRecord[0].frontAxleToRearAxle", 2000},
//                {"$.techRecord[0].frontAxleToRearAxle", 2000},
//                {"$.techRecord[0].frontAxleToRearAxle", 2000},
//                {"$.techRecord[0].frontAxleToRearAxle", 2000},
//                {"$.techRecord[0].functionCode", "B"},
//                {"$.techRecord[0].grossDesignWeight", 20000},
//                {"$.techRecord[0].grossGbWeight", 20000},
//                {"$.techRecord[0].grossEecWeight", 20000},
//                {"$.techRecord[0].make", "Chevy"},
//                {"$.techRecord[0].manufactureYear", 2000},
//                {"$.techRecord[0].maxTrainDesignWeight", 10000},
//                {"$.techRecord[0].maxTrainEecWeight", 10000},
//                {"$.techRecord[0].maxTrainGbWeight", 10000},
//                {"$.techRecord[0].model", "Mustang"},
//                {"$.techRecord[0].noOfAxles", 2},
//                {"$.techRecord[0].notes", "notes"},
//                {"$.techRecord[0].ntaNumber", "0000514900"},
//                {"$.techRecord[0].reasonForCreation", "no reason"},
//                {"$.techRecord[0].regnDate", 2000},
//                {"$.techRecord[0].roadFriendly", true},
//                {"$.techRecord[0].speedLimiterMrk", true},
//                {"$.techRecord[0].tachoExemptMrk", true},
//                {"$.techRecord[0].trainDesignWeight", 50000},
//                {"$.techRecord[0].trainGbWeight", 40000},
//                {"$.techRecord[0].trainEecWeight", 20000},
//                {"$.techRecord[0].tyreUseCode", "2A"},
//                {"$.techRecord[0].offRoad", true},
//                {"$.techRecord[0].numberOfWheelsDriven", 6},
//                {"$.techRecord[0].euVehicleCategory", "m2"},
//                {"$.techRecord[0].emissionsLimit", 100},
//                {"$.techRecord[0].departmentalVehicleMarker", true},
//                {"$.techRecord[0].alterationMarker", true},
//                {"$.techRecord[0].approvalType", "ECTA"},
//                {"$.techRecord[0].approvalTypeNumber", "123456"},
//                {"$.techRecord[0].variantNumber", "123456"},
//                {"$.techRecord[0].variantVersionNumber", "123"},
//                {"$.techRecord[0].applicantDetails.address1", "**"},
//                {"$.techRecord[0].applicantDetails.address1", "**"},
//                {"$.techRecord[0].applicantDetails.address1", "**"},
//                {"$.techRecord[0].applicantDetails.name", "**"},
//                {"$.techRecord[0].applicantDetails.postCode", "**"},
//                {"$.techRecord[0].applicantDetails.postTown", "**"},
//                {"$.techRecord[0].applicantDetails.telephoneNumber", "**"},
//                {"$.techRecord[0].applicantDetails.emailAddress", "**"},
//                {"$.techRecord[0].microfilm.microfilmDocumentType", "PSV Miscellaneous"},
//                {"$.techRecord[0].microfilm.microfilmRollNumber", "1234"},
//                {"$.techRecord[0].microfilm.microfilmSerialNumber", "123456"},
//                {"$.techRecord[0].plates[0].plateSerialNumber", "12345"},
//                {"$.techRecord[0].plates[0].plateIssueDate", "2030-12-31"},
//                {"$.techRecord[0].plates[0].plateReasonForIssue", "no reason"},
//                {"$.techRecord[0].plates[0].plateIssuer", "dvsa"},
//                {"$.techRecord[0].vehicleClass.description", "skeletal"},
//                {"$.techRecord[0].vehicleConfiguration", "rigid"}
        });
    }

    private final String jsonPath;
    private final Object value;


    public PutVehicleUpdateHgvFields(String jsonPath, Object value) {
        this.jsonPath = jsonPath;
        this.value = value;
    }

    @WithTag("Vtm")
    @Title("CVSB-10209 - AC3 - HGV vehicle is updated, and the appropriate attributes are automatically set")
    @Test
    public void testVehicleTechnicalRecordsGetAllHgvAttributes() {
        String putRequestBodyHgv = GenericData.readJsonValueFromFile("technical-records_hgv_all_fields.json", "$");
        JsonPathAlteration removeAxle = new JsonPathAlteration("$.techRecord[0].axles[1]", "","","DELETE");
        JsonPathAlteration removeAxleSpacing = new JsonPathAlteration("$.techRecord[0].dimensions.axleSpacing[1]", "","","DELETE");
        JsonPathAlteration changeUserId = new JsonPathAlteration("$.msUserDetails.msOid", "123456", "", "REPLACE");
        JsonPathAlteration changeUser = new JsonPathAlteration("$.msUserDetails.msUser", "dragos", "", "REPLACE");
        JsonPathAlteration updateField = new JsonPathAlteration(jsonPath, value, "", "REPLACE");
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(removeAxle, removeAxleSpacing, changeUser, changeUserId, updateField));
        vehicleTechnicalRecordsSteps.putVehicleTechnicalRecordsForVehicleWithAlterations(randomVin, putRequestBodyHgv, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord.size()", 2);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[0].statusCode", "archived");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[1].statusCode", "provisional");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[0].axles.size()", 1);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[0].dimensions.axleSpacing.size()", 1);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[0].lastUpdatedByID", "123456");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[0].lastUpdatedByName", "dragos");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[0].updateType", "techRecordUpdate");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[1].createdByID", "123456");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[1].createdByName", "dragos");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe(jsonPath.substring(2), value);
    }
}
