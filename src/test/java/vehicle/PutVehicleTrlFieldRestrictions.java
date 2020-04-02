package vehicle;

import data.GenericData;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.junit.annotations.TestData;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
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
public class PutVehicleTrlFieldRestrictions {

    static String randomVin;

    @BeforeClass
    public static void createRecord() {
        // TEST SETUP
        // generate random Vin
        randomVin = GenericData.generateRandomVin();
        // generate random Vrm
        String randomVrm = GenericData.generateRandomVrm();
        // read post request body from file
        String postRequestBodyHgv = GenericData.readJsonValueFromFile("technical-records_trl_all_fields.json", "$");
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
                {"$.techRecord[0].vehicleType", RandomStringUtils.randomAlphanumeric(10)},
                {"$.techRecord[0].regnDate", "21/08/1987"},
                {"$.techRecord[0].regnDate", false},
                {"$.techRecord[0].firstUseDate", "21/08/1987"},
                {"$.techRecord[0].firstUseDate", false},
                {"$.techRecord[0].manufactureYear", 10000},
                {"$.techRecord[0].manufactureYear", -1},
                {"$.techRecord[0].manufactureYear", RandomStringUtils.randomAlphabetic(4)},
                {"$.techRecord[0].noOfAxles", 100},
                {"$.techRecord[0].noOfAxles", -1},
                {"$.techRecord[0].noOfAxles", RandomStringUtils.randomAlphabetic(2)},
                {"$.techRecord[0].brakes.dtpNumber", RandomStringUtils.randomAlphanumeric(7)},
                {"$.techRecord[0].brakes.dtpNumber", 99},
                {"$.techRecord[0].axles[0].parkingBrakeMrk", "99"},
                {"$.techRecord[0].suspensionType", "99"},
                {"$.techRecord[0].suspensionType", false},
                {"$.techRecord[0].roadFriendly", "99"},
                {"$.techRecord[0].vehicleClass.description", RandomStringUtils.randomAlphanumeric(10)},
                {"$.techRecord[0].vehicleClass.description", 10},
                {"$.techRecord[0].vehicleConfiguration", RandomStringUtils.randomAlphanumeric(10)},
                {"$.techRecord[0].vehicleConfiguration", 10},
                {"$.techRecord[0].couplingType", 99},
                {"$.techRecord[0].couplingType", "99"},
                {"$.techRecord[0].maxLoadOnCoupling", -1},
                {"$.techRecord[0].maxLoadOnCoupling", 100000},
                {"$.techRecord[0].maxLoadOnCoupling", false},
                {"$.techRecord[0].frameDescription", false},
                {"$.techRecord[0].frameDescription", RandomStringUtils.randomAlphanumeric(10)},
                {"$.techRecord[0].euVehicleCategory", RandomStringUtils.randomAlphanumeric(3)},
                {"$.techRecord[0].euVehicleCategory", 10},
                {"$.techRecord[0].alterationMarker", 10},
                {"$.techRecord[0].departmentalVehicleMarker", 10},
                {"$.techRecord[0].authIntoService.cocIssueDate", "21/08/1987"},
                {"$.techRecord[0].authIntoService.dateReceived", "21/08/1987"},
                {"$.techRecord[0].authIntoService.datePending", "21/08/1987"},
                {"$.techRecord[0].authIntoService.dateAuthorised", "21/08/1987"},
                {"$.techRecord[0].authIntoService.dateRejected", "21/08/1987"},
                {"$.techRecord[0].lettersOfAuth.letterType", RandomStringUtils.randomAlphanumeric(10)},
                {"$.techRecord[0].lettersOfAuth.letterType", true},
                {"$.techRecord[0].lettersOfAuth.letterDateRequested", "21/08/1987"},
                {"$.techRecord[0].lettersOfAuth.letterDateRequested", true},
                {"$.techRecord[0].lettersOfAuth.letterContents", true},
                {"$.techRecord[0].lettersOfAuth.letterContents", 10},
                {"$.techRecord[0].approvalType", "nta"},
                {"$.techRecord[0].approvalType", 10},
                {"$.techRecord[0].approvalTypeNumber", RandomStringUtils.randomAlphanumeric(26)},
                {"$.techRecord[0].approvalTypeNumber", 10},
                {"$.techRecord[0].ntaNumber", RandomStringUtils.randomAlphanumeric(41)},
                {"$.techRecord[0].ntaNumber", 10},
                {"$.techRecord[0].variantNumber", RandomStringUtils.randomAlphanumeric(26)},
                {"$.techRecord[0].variantNumber", 10},
                {"$.techRecord[0].variantVersionNumber", RandomStringUtils.randomAlphanumeric(36)},
                {"$.techRecord[0].variantVersionNumber", 10},
                {"$.techRecord[0].make", RandomStringUtils.randomAlphanumeric(31)},
                {"$.techRecord[0].make", 10},
                {"$.techRecord[0].model", RandomStringUtils.randomAlphanumeric(31)},
                {"$.techRecord[0].model", 10},
                {"$.techRecord[0].bodyType.description", RandomStringUtils.randomNumeric(6)},
                {"$.techRecord[0].bodyType.description", 10},
                {"$.techRecord[0].functionCode", "10"},
                {"$.techRecord[0].functionCode", 10},
                {"$.techRecord[0].conversionRefNo", RandomStringUtils.randomAlphanumeric(11)},
                {"$.techRecord[0].conversionRefNo", 10},
                {"$.techRecord[0].grossGbWeight", 100000},
                {"$.techRecord[0].grossGbWeight", -1},
                {"$.techRecord[0].grossGbWeight", RandomStringUtils.randomAlphabetic(5)},
                {"$.techRecord[0].grossEecWeight", 100000},
                {"$.techRecord[0].grossEecWeight", -1},
                {"$.techRecord[0].grossEecWeight", RandomStringUtils.randomAlphabetic(5)},
                {"$.techRecord[0].grossDesignWeight", 100000},
                {"$.techRecord[0].grossDesignWeight", -1},
                {"$.techRecord[0].grossDesignWeight", RandomStringUtils.randomAlphabetic(5)},
                {"$.techRecord[0].tyreUseCode", RandomStringUtils.randomAlphabetic(3)},
                {"$.techRecord[0].tyreUseCode", ""},
                {"$.techRecord[0].tyreUseCode", 10},
                {"$.techRecord[0].axles[0].tyres.tyreCode", 100000},
                {"$.techRecord[0].axles[0].tyres.tyreCode", -1},
                {"$.techRecord[0].axles[0].tyres.tyreCode", RandomStringUtils.randomAlphabetic(5)},
                {"$.techRecord[0].axles[0].tyres.tyreSize", RandomStringUtils.randomAlphanumeric(13)},
                {"$.techRecord[0].axles[0].tyres.tyreSize", ""},
                {"$.techRecord[0].axles[0].tyres.tyreSize", 10},
                {"$.techRecord[0].axles[0].tyres.plyRating", RandomStringUtils.randomAlphanumeric(3)},
                {"$.techRecord[0].axles[0].tyres.plyRating", ""},
                {"$.techRecord[0].axles[0].tyres.plyRating", 10},
                {"$.techRecord[0].axles[0].tyres.fitmentCode", RandomStringUtils.randomAlphanumeric(7)},
                {"$.techRecord[0].axles[0].tyres.fitmentCode", 10},
                {"$.techRecord[0].axles[0].tyres.dataTrAxles", 1000},
                {"$.techRecord[0].axles[0].tyres.dataTrAxles", -1},
                {"$.techRecord[0].axles[0].tyres.dataTrAxles", RandomStringUtils.randomAlphabetic(3)},
                {"$.techRecord[0].brakes.loadSensingValve", -1},
                {"$.techRecord[0].brakes.loadSensingValve", RandomStringUtils.randomAlphanumeric(7)},
                {"$.techRecord[0].brakes.antilockBrakingSystem", -1},
                {"$.techRecord[0].brakes.antilockBrakingSystem", RandomStringUtils.randomAlphanumeric(7)},
                {"$.techRecord[0].axles[0].axleNumber", 100000},
                {"$.techRecord[0].axles[0].axleNumber", -1},
                {"$.techRecord[0].axles[0].axleNumber", RandomStringUtils.randomAlphabetic(5)},
                {"$.techRecord[0].axles[0].brakes.brakeActuator", 1000},
                {"$.techRecord[0].axles[0].brakes.brakeActuator", -1},
                {"$.techRecord[0].axles[0].brakes.brakeActuator", RandomStringUtils.randomAlphabetic(5)},
                {"$.techRecord[0].axles[0].brakes.leverLength", 1000},
                {"$.techRecord[0].axles[0].brakes.leverLength", -1},
                {"$.techRecord[0].axles[0].brakes.leverLength", RandomStringUtils.randomAlphabetic(5)},
                {"$.techRecord[0].axles[0].brakes.springBrakeParking", -1},
                {"$.techRecord[0].axles[0].brakes.springBrakeParking", RandomStringUtils.randomAlphanumeric(7)},
                {"$.techRecord[0].dimensions.length", 100000},
                {"$.techRecord[0].dimensions.length", -1},
                {"$.techRecord[0].dimensions.length", RandomStringUtils.randomAlphabetic(5)},
                {"$.techRecord[0].dimensions.width", 100000},
                {"$.techRecord[0].dimensions.width", -1},
                {"$.techRecord[0].dimensions.width", RandomStringUtils.randomAlphabetic(5)},
                {"$.techRecord[0].frontAxleToRearAxle", 100000},
                {"$.techRecord[0].frontAxleToRearAxle", -1},
                {"$.techRecord[0].frontAxleToRearAxle", RandomStringUtils.randomAlphabetic(5)},
                {"$.techRecord[0].rearAxleToRearTrl", 100000},
                {"$.techRecord[0].rearAxleToRearTrl", -1},
                {"$.techRecord[0].rearAxleToRearTrl", RandomStringUtils.randomAlphabetic(5)},
                {"$.techRecord[0].couplingCenterToRearAxleMin", 100000},
                {"$.techRecord[0].couplingCenterToRearAxleMin", -1},
                {"$.techRecord[0].couplingCenterToRearAxleMin", RandomStringUtils.randomAlphabetic(5)},
                {"$.techRecord[0].couplingCenterToRearAxleMax", 100000},
                {"$.techRecord[0].couplingCenterToRearAxleMax", -1},
                {"$.techRecord[0].couplingCenterToRearAxleMax", RandomStringUtils.randomAlphabetic(5)},
                {"$.techRecord[0].couplingCenterToRearTrlMin", 100000},
                {"$.techRecord[0].couplingCenterToRearTrlMin", -1},
                {"$.techRecord[0].couplingCenterToRearTrlMin", RandomStringUtils.randomAlphabetic(5)},
                {"$.techRecord[0].couplingCenterToRearTrlMax", 100000},
                {"$.techRecord[0].couplingCenterToRearTrlMax", -1},
                {"$.techRecord[0].couplingCenterToRearTrlMax", RandomStringUtils.randomAlphabetic(5)},
                {"$.techRecord[0].centreOfRearmostAxleToRearOfTrl", 100000},
                {"$.techRecord[0].centreOfRearmostAxleToRearOfTrl", -1},
                {"$.techRecord[0].centreOfRearmostAxleToRearOfTrl", RandomStringUtils.randomAlphabetic(5)},
                {"$.techRecord[0].dimensions.axleSpacing[0].axles", 10},
                {"$.techRecord[0].dimensions.axleSpacing[0].value", 100000},
                {"$.techRecord[0].dimensions.axleSpacing[0].value", -1},
                {"$.techRecord[0].dimensions.axleSpacing[0].value", RandomStringUtils.randomAlphabetic(5)},
                {"$.techRecord[0].applicantDetails.name", RandomStringUtils.randomAlphanumeric(151)},
                {"$.techRecord[0].applicantDetails.name", ""},
                {"$.techRecord[0].applicantDetails.name", 10},
                {"$.techRecord[0].applicantDetails.address1", RandomStringUtils.randomAlphanumeric(61)},
                {"$.techRecord[0].applicantDetails.address1", ""},
                {"$.techRecord[0].applicantDetails.address1", 10},
                {"$.techRecord[0].applicantDetails.address2", RandomStringUtils.randomAlphanumeric(61)},
                {"$.techRecord[0].applicantDetails.address2", ""},
                {"$.techRecord[0].applicantDetails.address2", 10},
                {"$.techRecord[0].applicantDetails.address3", RandomStringUtils.randomAlphanumeric(61)},
                {"$.techRecord[0].applicantDetails.address3", ""},
                {"$.techRecord[0].applicantDetails.address3", 10},
                {"$.techRecord[0].applicantDetails.postTown", RandomStringUtils.randomAlphanumeric(61)},
                {"$.techRecord[0].applicantDetails.postTown", ""},
                {"$.techRecord[0].applicantDetails.postTown", 10},
                {"$.techRecord[0].applicantDetails.postCode", RandomStringUtils.randomAlphanumeric(13)},
                {"$.techRecord[0].applicantDetails.postCode", ""},
                {"$.techRecord[0].applicantDetails.postCode", 10},
                {"$.techRecord[0].applicantDetails.telephoneNumber", RandomStringUtils.randomAlphanumeric(26)},
                {"$.techRecord[0].applicantDetails.telephoneNumber", ""},
                {"$.techRecord[0].applicantDetails.telephoneNumber", 10},
                {"$.techRecord[0].applicantDetails.emailAddress", RandomStringUtils.randomAlphanumeric(256)},
                {"$.techRecord[0].applicantDetails.emailAddress", ""},
                {"$.techRecord[0].applicantDetails.emailAddress", 10},
                {"$.techRecord[0].purchaserDetails.name", RandomStringUtils.randomAlphanumeric(151)},
                {"$.techRecord[0].purchaserDetails.name", ""},
                {"$.techRecord[0].purchaserDetails.name", 10},
                {"$.techRecord[0].purchaserDetails.address1", RandomStringUtils.randomAlphanumeric(61)},
                {"$.techRecord[0].purchaserDetails.address1", ""},
                {"$.techRecord[0].purchaserDetails.address1", 10},
                {"$.techRecord[0].purchaserDetails.address2", RandomStringUtils.randomAlphanumeric(61)},
                {"$.techRecord[0].purchaserDetails.address2", ""},
                {"$.techRecord[0].purchaserDetails.address2", 10},
                {"$.techRecord[0].purchaserDetails.address3", RandomStringUtils.randomAlphanumeric(61)},
                {"$.techRecord[0].purchaserDetails.address3", ""},
                {"$.techRecord[0].purchaserDetails.address3", 10},
                {"$.techRecord[0].purchaserDetails.postTown", RandomStringUtils.randomAlphanumeric(61)},
                {"$.techRecord[0].purchaserDetails.postTown", ""},
                {"$.techRecord[0].purchaserDetails.postTown", 10},
                {"$.techRecord[0].purchaserDetails.postCode", RandomStringUtils.randomAlphanumeric(13)},
                {"$.techRecord[0].purchaserDetails.postCode", ""},
                {"$.techRecord[0].purchaserDetails.postCode", 10},
                {"$.techRecord[0].purchaserDetails.telephoneNumber", RandomStringUtils.randomAlphanumeric(26)},
                {"$.techRecord[0].purchaserDetails.telephoneNumber", ""},
                {"$.techRecord[0].purchaserDetails.telephoneNumber", 10},
                {"$.techRecord[0].purchaserDetails.emailAddress", RandomStringUtils.randomAlphanumeric(256)},
                {"$.techRecord[0].purchaserDetails.emailAddress", ""},
                {"$.techRecord[0].purchaserDetails.emailAddress", 10},
                {"$.techRecord[0].purchaserDetails.faxNumber", RandomStringUtils.randomAlphanumeric(26)},
                {"$.techRecord[0].purchaserDetails.faxNumber", ""},
                {"$.techRecord[0].purchaserDetails.faxNumber", 10},
                {"$.techRecord[0].purchaserDetails.purchaserNotes", RandomStringUtils.randomAlphanumeric(1025)},
                {"$.techRecord[0].purchaserDetails.purchaserNotes", ""},
                {"$.techRecord[0].purchaserDetails.purchaserNotes", 10},
                {"$.techRecord[0].manufacturerDetails.name", RandomStringUtils.randomAlphanumeric(151)},
                {"$.techRecord[0].manufacturerDetails.name", ""},
                {"$.techRecord[0].manufacturerDetails.name", 10},
                {"$.techRecord[0].manufacturerDetails.address1", RandomStringUtils.randomAlphanumeric(61)},
                {"$.techRecord[0].manufacturerDetails.address1", ""},
                {"$.techRecord[0].manufacturerDetails.address1", 10},
                {"$.techRecord[0].manufacturerDetails.address2", RandomStringUtils.randomAlphanumeric(61)},
                {"$.techRecord[0].manufacturerDetails.address2", ""},
                {"$.techRecord[0].manufacturerDetails.address2", 10},
                {"$.techRecord[0].manufacturerDetails.address3", RandomStringUtils.randomAlphanumeric(61)},
                {"$.techRecord[0].manufacturerDetails.address3", ""},
                {"$.techRecord[0].manufacturerDetails.address3", 10},
                {"$.techRecord[0].manufacturerDetails.postTown", RandomStringUtils.randomAlphanumeric(61)},
                {"$.techRecord[0].manufacturerDetails.postTown", ""},
                {"$.techRecord[0].manufacturerDetails.postTown", 10},
                {"$.techRecord[0].manufacturerDetails.postCode", RandomStringUtils.randomAlphanumeric(13)},
                {"$.techRecord[0].manufacturerDetails.postCode", ""},
                {"$.techRecord[0].manufacturerDetails.postCode", 10},
                {"$.techRecord[0].manufacturerDetails.telephoneNumber", RandomStringUtils.randomAlphanumeric(26)},
                {"$.techRecord[0].manufacturerDetails.telephoneNumber", ""},
                {"$.techRecord[0].manufacturerDetails.telephoneNumber", 10},
                {"$.techRecord[0].manufacturerDetails.emailAddress", RandomStringUtils.randomAlphanumeric(256)},
                {"$.techRecord[0].manufacturerDetails.emailAddress", ""},
                {"$.techRecord[0].manufacturerDetails.emailAddress", 10},
                {"$.techRecord[0].manufacturerDetails.faxNumber", RandomStringUtils.randomAlphanumeric(26)},
                {"$.techRecord[0].manufacturerDetails.faxNumber", ""},
                {"$.techRecord[0].manufacturerDetails.faxNumber", 10},
                {"$.techRecord[0].manufacturerDetails.manufacturerNotes", RandomStringUtils.randomAlphanumeric(1025)},
                {"$.techRecord[0].manufacturerDetails.manufacturerNotes", 10},
                {"$.techRecord[0].microfilm.microfilmDocumentType", RandomStringUtils.randomAlphanumeric(10)},
                {"$.techRecord[0].microfilm.microfilmDocumentType", 10},
                {"$.techRecord[0].microfilm.microfilmRollNumber", RandomStringUtils.randomAlphanumeric(6)},
                {"$.techRecord[0].microfilm.microfilmRollNumber", ""},
                {"$.techRecord[0].microfilm.microfilmRollNumber", 10},
                {"$.techRecord[0].microfilm.microfilmSerialNumber", RandomStringUtils.randomAlphanumeric(5)},
                {"$.techRecord[0].microfilm.microfilmSerialNumber", ""},
                {"$.techRecord[0].plates[0].plateSerialNumber", RandomStringUtils.randomAlphanumeric(13)},
                {"$.techRecord[0].plates[0].plateSerialNumber", ""},
                {"$.techRecord[0].plates[0].plateSerialNumber", 10},
                {"$.techRecord[0].plates[0].plateIssueDate", "31/12/2100"},
                {"$.techRecord[0].plates[0].plateIssueDate", false},
                {"$.techRecord[0].plates[0].plateReasonForIssue", RandomStringUtils.randomAlphanumeric(10)},
                {"$.techRecord[0].plates[0].plateReasonForIssue", 10},
                {"$.techRecord[0].plates[0].plateIssuer", RandomStringUtils.randomAlphanumeric(151)},
                {"$.techRecord[0].plates[0].plateIssuer", ""},
                {"$.techRecord[0].plates[0].plateIssuer", 10},
                {"$.techRecord[0].notes", 10},
                {"$.techRecord[0].reasonForCreation", RandomStringUtils.randomAlphanumeric(101)},
                {"$.techRecord[0].reasonForCreation", ""},
                {"$.techRecord[0].reasonForCreation", 10},
        });
    }

    private final String jsonPath;
    private final Object value;

    public PutVehicleTrlFieldRestrictions(String jsonPath, Object value) {
        this.jsonPath = jsonPath;
        this.value = value;
    }

    @WithTag("Vtm")
    @Title("CVSB-10247 - AC2 - PUT: Attempt to update a vehicle with a not applicable field " +
           "CVSB-10247 - AC3 - PUT: Attempt to update a vehicle with unexpected values for a field that accepts only specific values " +
           "CVSB-10247 - AC4 - PUT: Attempt to update a vehicle, using a field which has a field length outside of the min/max length for that field")
    @Test
    public void testValidateTrlAttributesDataTypesAndRestrictions() {
        String putRequestBodyHgv = GenericData.readJsonValueFromFile("technical-records_trl_all_fields.json", "$");
        JsonPathAlteration restriction = new JsonPathAlteration(jsonPath, value, "", "REPLACE");
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(restriction));
        vehicleTechnicalRecordsSteps.putVehicleTechnicalRecordsForVehicleWithAlterations(randomVin, putRequestBodyHgv, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(400);
    }
}
