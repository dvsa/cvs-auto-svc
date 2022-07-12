//package vehicle;
//
//import data.GenericData;
//import io.restassured.http.ContentType;
//import io.restassured.response.Response;
//import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
//import net.thucydides.core.annotations.Steps;
//import net.thucydides.core.annotations.Title;
//import net.thucydides.core.annotations.WithTag;
//import net.thucydides.junit.annotations.TestData;
//import org.apache.commons.lang3.RandomStringUtils;
//import org.junit.Assert;
//import org.junit.BeforeClass;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import steps.*;
//import util.BasePathFilter;
//import util.JsonPathAlteration;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Collection;
//import java.util.List;
//
//import static io.restassured.RestAssured.given;
//import static util.WriterReader.saveUtils;
//
//
//@RunWith(SerenityParameterizedRunner.class)
//public class PutVehiclePsvFieldRestrictions {
//
//    static String randomSystemNumber;
//
//    @BeforeClass
//    public static void createRecord() {
//        // TEST SETUP
//        // generate random Vin
//        String randomVin = GenericData.generateRandomVin();
//        // generate random systemNumber
//        randomSystemNumber = GenericData.generateRandomSystemNumber();
//        // generate random Vrm
//        String randomVrm = GenericData.generateRandomVrm();
//        // read post request body from file
//        String postRequestBodyPsv = GenericData.readJsonValueFromFile("technical-records_psv_all_fields.json", "$");
//        // create alteration to change systemNumber in the post request body with the random generated systemNumber
//        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$.systemNumber", randomSystemNumber, "", "REPLACE");
//        // create alteration to change Vin in the post request body with the random generated Vin
//        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
//        // create alteration to change primary vrm in the request body with the random generated primary vrm
//        JsonPathAlteration alterationVrm = new JsonPathAlteration("$.primaryVrm", randomVrm, "", "REPLACE");
//
//        // initialize the alterations list with both declared alterations
//        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(alterationVin, alterationVrm, alterationSystemNumber));
//
//        String alteredBody = GenericData.applyJsonAlterations(postRequestBodyPsv, alterations);
//        Response response = given().filters(new BasePathFilter())
//                .contentType(ContentType.JSON)
//                .body(alteredBody)
//                .log().method().log().uri().log().body()
//                .post("/vehicles");
//
//        if (response.getStatusCode() == 401 || response.getStatusCode() == 403) {
//            saveUtils();
//            response = given().filters(new BasePathFilter())
//                    .contentType(ContentType.JSON)
//                    .body(alteredBody)
//                    .log().method().log().uri().log().body()
//                    .post("/vehicles");
//        }
//
//        Assert.assertEquals(201, response.getStatusCode());
//    }
//
//
//    @Steps
//    VehicleTechnicalRecordsSteps vehicleTechnicalRecordsSteps;
//
//    @TestData
//    public static Collection<Object[]> testData(){
//        return Arrays.asList(new Object[][]{
//                {"$.techRecord[0].vehicleType", RandomStringUtils.randomAlphanumeric(10)},
//                {"$.techRecord[0].vehicleType", 100},
//                {"$.techRecord[0].regnDate", "21/08/1987"},
//                {"$.techRecord[0].regnDate", false},
//                {"$.techRecord[0].manufactureYear", 10000},
//                {"$.techRecord[0].manufactureYear", -1},
//                {"$.techRecord[0].manufactureYear", RandomStringUtils.randomAlphabetic(4)},
//                {"$.techRecord[0].noOfAxles", 100},
//                {"$.techRecord[0].noOfAxles", -1},
//                {"$.techRecord[0].noOfAxles", RandomStringUtils.randomAlphabetic(2)},
//                {"$.techRecord[0].brakes.dtpNumber", RandomStringUtils.randomAlphanumeric(7)},
//                {"$.techRecord[0].brakes.dtpNumber", 99},
//                {"$.techRecord[0].axles[0].parkingBrakeMrk", "99"},
//                {"$.techRecord[0].speedLimiterMrk", "99"},
//                {"$.techRecord[0].tachoExemptMrk", "99"},
//                {"$.techRecord[0].euroStandard", false},
//                {"$.techRecord[0].fuelPropulsionSystem", RandomStringUtils.randomAlphanumeric(10)},
//                {"$.techRecord[0].fuelPropulsionSystem", 10},
//                {"$.techRecord[0].vehicleClass.description", RandomStringUtils.randomAlphanumeric(10)},
//                {"$.techRecord[0].vehicleClass.description", 10},
//                {"$.techRecord[0].vehicleConfiguration", RandomStringUtils.randomAlphanumeric(10)},
//                {"$.techRecord[0].vehicleConfiguration", 10},
//                {"$.techRecord[0].numberOfWheelsDriven", 10000},
//                {"$.techRecord[0].numberOfWheelsDriven", -1},
//                {"$.techRecord[0].numberOfWheelsDriven", RandomStringUtils.randomAlphabetic(4)},
//                {"$.techRecord[0].euVehicleCategory", RandomStringUtils.randomAlphanumeric(3)},
//                {"$.techRecord[0].euVehicleCategory", 10},
//                {"$.techRecord[0].emissionsLimit", 100},
//                {"$.techRecord[0].emissionsLimit", -1},
//                {"$.techRecord[0].emissionsLimit", RandomStringUtils.randomAlphabetic(2)},
//                {"$.techRecord[0].seatsLowerDeck", 1000},
//                {"$.techRecord[0].seatsLowerDeck", -1},
//                {"$.techRecord[0].seatsLowerDeck", RandomStringUtils.randomAlphabetic(2)},
//                {"$.techRecord[0].seatsUpperDeck", 100},
//                {"$.techRecord[0].seatsUpperDeck", -1},
//                {"$.techRecord[0].seatsUpperDeck", RandomStringUtils.randomAlphabetic(2)},
//                {"$.techRecord[0].standingCapacity", 1000},
//                {"$.techRecord[0].standingCapacity", -1},
//                {"$.techRecord[0].standingCapacity", "test"},
//                {"$.techRecord[0].vehicleSize", RandomStringUtils.randomAlphanumeric(10)},
//                {"$.techRecord[0].vehicleSize", 20},
//                {"$.techRecord[0].numberOfSeatbelts", 100},
//                {"$.techRecord[0].numberOfSeatbelts", RandomStringUtils.randomAlphanumeric(100)},
//                {"$.techRecord[0].seatbeltInstallationApprovalDate", 20},
//                {"$.techRecord[0].departmentalVehicleMarker", 20},
//                {"$.techRecord[0].alterationMarker", 20},
//                {"$.techRecord[0].approvalType", "nta"},
//                {"$.techRecord[0].approvalType", 10},
//                {"$.techRecord[0].approvalTypeNumber", RandomStringUtils.randomAlphanumeric(26)},
//                {"$.techRecord[0].approvalTypeNumber", 10},
//                {"$.techRecord[0].ntaNumber", RandomStringUtils.randomAlphanumeric(41)},
//                {"$.techRecord[0].ntaNumber", 10},
//                {"$.techRecord[0].coifSerialNumber", 10},
//                {"$.techRecord[0].coifSerialNumber", RandomStringUtils.randomAlphanumeric(9)},
//                {"$.techRecord[0].coifCertifierName", 10},
//                {"$.techRecord[0].coifCertifierName", RandomStringUtils.randomAlphanumeric(21)},
//                {"$.techRecord[0].coifDate", "test"},
//                {"$.techRecord[0].coifDate", false},
//                {"$.techRecord[0].variantNumber", RandomStringUtils.randomAlphanumeric(26)},
//                {"$.techRecord[0].variantNumber", 10},
//                {"$.techRecord[0].variantVersionNumber", RandomStringUtils.randomAlphanumeric(36)},
//                {"$.techRecord[0].variantVersionNumber", 10},
//                {"$.techRecord[0].bodyMake", RandomStringUtils.randomAlphanumeric(21)},
//                {"$.techRecord[0].bodyMake", 10},
//                {"$.techRecord[0].bodyModel", RandomStringUtils.randomAlphanumeric(21)},
//                {"$.techRecord[0].bodyModel", 10},
//                {"$.techRecord[0].modelLiteral", RandomStringUtils.randomAlphanumeric(31)},
//                {"$.techRecord[0].chassisMake", RandomStringUtils.randomAlphanumeric(21)},
//                {"$.techRecord[0].chassisMake", 10},
//                {"$.techRecord[0].chassisModel", RandomStringUtils.randomAlphanumeric(21)},
//                {"$.techRecord[0].chassisModel", 10},
//                {"$.techRecord[0].bodyType.description", RandomStringUtils.randomNumeric(6)},
//                {"$.techRecord[0].bodyType.description", 10},
//                {"$.techRecord[0].functionCode", "10"},
//                {"$.techRecord[0].functionCode", 10},
//                {"$.techRecord[0].conversionRefNo", RandomStringUtils.randomAlphanumeric(11)},
//                {"$.techRecord[0].conversionRefNo", 10},
//                {"$.techRecord[0].grossKerbWeight", 100000},
//                {"$.techRecord[0].grossKerbWeight", -1},
//                {"$.techRecord[0].grossKerbWeight", "test"},
//                {"$.techRecord[0].grossLadenWeight", 100000},
//                {"$.techRecord[0].grossLadenWeight", -1},
//                {"$.techRecord[0].grossLadenWeight", "test"},
//                {"$.techRecord[0].grossGbWeight", 100000},
//                {"$.techRecord[0].grossGbWeight", -1},
//                {"$.techRecord[0].grossGbWeight", "test"},
//                {"$.techRecord[0].grossDesignWeight", 100000},
//                {"$.techRecord[0].grossDesignWeight", -1},
//                {"$.techRecord[0].grossDesignWeight", "test"},
//                {"$.techRecord[0].unladenWeight", 100000},
//                {"$.techRecord[0].unladenWeight", -1},
//                {"$.techRecord[0].unladenWeight", "test"},
//                {"$.techRecord[0].maxTrainGbWeight", 100000},
//                {"$.techRecord[0].maxTrainGbWeight", -1},
//                {"$.techRecord[0].maxTrainGbWeight", "test"},
//                {"$.techRecord[0].trainDesignWeight", 100000},
//                {"$.techRecord[0].trainDesignWeight", -1},
//                {"$.techRecord[0].trainDesignWeight", "test"},
//                {"$.techRecord[0].axles[0].axleNumber", -1},
//                {"$.techRecord[0].axles[0].axleNumber", "test"},
//                {"$.techRecord[0].axles[0].weights.kerbWeight", 100000},
//                {"$.techRecord[0].axles[0].weights.kerbWeight", -1},
//                {"$.techRecord[0].axles[0].weights.kerbWeight", "test"},
//                {"$.techRecord[0].axles[0].weights.ladenWeight", 100000},
//                {"$.techRecord[0].axles[0].weights.ladenWeight", -1},
//                {"$.techRecord[0].axles[0].weights.ladenWeight", "test"},
//                {"$.techRecord[0].axles[0].weights.gbWeight", 100000},
//                {"$.techRecord[0].axles[0].weights.gbWeight", -1},
//                {"$.techRecord[0].axles[0].weights.gbWeight", "test"},
//                {"$.techRecord[0].axles[0].weights.designWeight", 100000},
//                {"$.techRecord[0].axles[0].weights.designWeight", -1},
//                {"$.techRecord[0].axles[0].weights.designWeight", "test"},
//                {"$.techRecord[0].speedRestriction", 100},
//                {"$.techRecord[0].speedRestriction", -1},
//                {"$.techRecord[0].speedRestriction", "test"},
//                {"$.techRecord[0].axles[0].tyres.tyreCode", 10000},
//                {"$.techRecord[0].axles[0].tyres.tyreCode", -1},
//                {"$.techRecord[0].axles[0].tyres.tyreCode", "test"},
//                {"$.techRecord[0].axles[0].tyres.tyreSize", RandomStringUtils.randomAlphanumeric(13)},
//                {"$.techRecord[0].axles[0].tyres.tyreSize", ""},
//                {"$.techRecord[0].axles[0].tyres.tyreSize", 10},
//                {"$.techRecord[0].axles[0].tyres.plyRating", RandomStringUtils.randomAlphanumeric(3)},
//                {"$.techRecord[0].axles[0].tyres.plyRating", ""},
//                {"$.techRecord[0].axles[0].tyres.plyRating", 10},
//                {"$.techRecord[0].axles[0].tyres.speedCategorySymbol", RandomStringUtils.randomAlphanumeric(3)},
//                {"$.techRecord[0].axles[0].tyres.speedCategorySymbol", 10},
//                {"$.techRecord[0].axles[0].tyres.fitmentCode", RandomStringUtils.randomAlphanumeric(7)},
//                {"$.techRecord[0].axles[0].tyres.fitmentCode", 10},
//                {"$.techRecord[0].axles[0].tyres.dataTrAxles", 1000},
//                {"$.techRecord[0].axles[0].tyres.dataTrAxles", -1},
//                {"$.techRecord[0].axles[0].tyres.dataTrAxles", RandomStringUtils.randomAlphabetic(3)},
//                {"$.techRecord[0].brakes.brakeCode", RandomStringUtils.randomAlphanumeric(7)},
//                {"$.techRecord[0].brakes.brakeCode", 99},
//                {"$.techRecord[0].brakes.brakeCode", RandomStringUtils.randomAlphanumeric(61)},
//                {"$.techRecord[0].brakes.brakeCode", 99},
//                {"$.techRecord[0].brakes.brakeCode", RandomStringUtils.randomAlphanumeric(61)},
//                {"$.techRecord[0].brakes.brakeCode", 99},
//                {"$.techRecord[0].brakes.brakeCode", RandomStringUtils.randomAlphanumeric(61)},
//                {"$.techRecord[0].brakes.brakeCode", 99},
//                {"$.techRecord[0].brakes.brakeCode", RandomStringUtils.randomAlphanumeric(10)},
//                {"$.techRecord[0].brakes.brakeCode", 99},
//                {"$.techRecord[0].brakes.brakeCode", RandomStringUtils.randomAlphanumeric(10)},
//                {"$.techRecord[0].brakes.brakeCode", 99},
//                {"$.techRecord[0]brakes.brakeForceWheelsNotLocked.parkingBrakeForceA", "test"},
//                {"$.techRecord[0].brakes.brakeForceWheelsNotLocked.parkingBrakeForceA", 100000},
//                {"$.techRecord[0].brakes.brakeForceWheelsNotLocked.parkingBrakeForceA", -1},
//                {"$.techRecord[0]brakes.brakeForceWheelsNotLocked.secondaryBrakeForceA", "test"},
//                {"$.techRecord[0].brakes.brakeForceWheelsNotLocked.secondaryBrakeForceA", 100000},
//                {"$.techRecord[0].brakes.brakeForceWheelsNotLocked.secondaryBrakeForceA", -1},
//                {"$.techRecord[0]brakes.brakeForceWheelsNotLocked.serviceBrakeForceA", "test"},
//                {"$.techRecord[0].brakes.brakeForceWheelsNotLocked.serviceBrakeForceA", 100000},
//                {"$.techRecord[0].brakes.brakeForceWheelsNotLocked.serviceBrakeForceA", -1},
//                {"$.techRecord[0]brakes.brakeForceWheelsUpToHalfLocked.parkingBrakeForceB", "test"},
//                {"$.techRecord[0].brakes.brakeForceWheelsUpToHalfLocked.parkingBrakeForceB", 100000},
//                {"$.techRecord[0].brakes.brakeForceWheelsUpToHalfLocked.parkingBrakeForceB", -1},
//                {"$.techRecord[0]brakes.brakeForceWheelsUpToHalfLocked.secondaryBrakeForceB", "test"},
//                {"$.techRecord[0].brakes.brakeForceWheelsUpToHalfLocked.secondaryBrakeForceB", 100000},
//                {"$.techRecord[0].brakes.brakeForceWheelsUpToHalfLocked.secondaryBrakeForceB", -1},
//                {"$.techRecord[0]brakes.brakeForceWheelsUpToHalfLocked.serviceBrakeForceB", "test"},
//                {"$.techRecord[0].brakes.brakeForceWheelsUpToHalfLocked.serviceBrakeForceB", 100000},
//                {"$.techRecord[0].brakes.brakeForceWheelsUpToHalfLocked.serviceBrakeForceB", -1},
//                {"$.techRecord[0].dda.certificateIssued", 2},
//                {"$.techRecord[0].dda.wheelchairCapacity", -1},
//                {"$.techRecord[0].dda.wheelchairCapacity", 100},
//                {"$.techRecord[0].dda.wheelchairCapacity", "test"},
//                {"$.techRecord[0].dda.wheelchairFittings", RandomStringUtils.randomAlphanumeric(251)},
//                {"$.techRecord[0].dda.wheelchairFittings", 2},
//                {"$.techRecord[0].dda.wheelchairLiftPresent", 2},
//                {"$.techRecord[0].dda.wheelchairLiftInformation", RandomStringUtils.randomAlphanumeric(251)},
//                {"$.techRecord[0].dda.wheelchairLiftInformation", 2},
//                {"$.techRecord[0].dda.wheelchairRampPresent", 2},
//                {"$.techRecord[0].dda.wheelchairRampInformation", RandomStringUtils.randomAlphanumeric(251)},
//                {"$.techRecord[0].dda.wheelchairRampInformation", 2},
//                {"$.techRecord[0].dda.minEmergencyExits", -1},
//                {"$.techRecord[0].dda.minEmergencyExits", 100},
//                {"$.techRecord[0].dda.minEmergencyExits", "test"},
//                {"$.techRecord[0].dda.outswing", RandomStringUtils.randomAlphanumeric(251)},
//                {"$.techRecord[0].dda.outswing", 2},
//                {"$.techRecord[0].dda.ddaSchedules", RandomStringUtils.randomAlphanumeric(251)},
//                {"$.techRecord[0].dda.ddaSchedules", 2},
//                {"$.techRecord[0].dda.seatbeltsFitted", -1},
//                {"$.techRecord[0].dda.seatbeltsFitted", 1000},
//                {"$.techRecord[0].dda.seatbeltsFitted", "test"},
//                {"$.techRecord[0].dda.ddaNotes", RandomStringUtils.randomAlphanumeric(1025)},
//                {"$.techRecord[0].dda.ddaNotes", 10},
//                {"$.techRecord[0].dimensions.height", 100000},
//                {"$.techRecord[0].dimensions.height", -1},
//                {"$.techRecord[0].dimensions.height", "test"},
//                {"$.techRecord[0].dimensions.length", 100000},
//                {"$.techRecord[0].dimensions.length", -1},
//                {"$.techRecord[0].dimensions.length", "test"},
//                {"$.techRecord[0].dimensions.width", 100000},
//                {"$.techRecord[0].dimensions.width", -1},
//                {"$.techRecord[0].dimensions.width", "test"},
//                {"$.techRecord[0].applicantDetails.name", RandomStringUtils.randomAlphanumeric(151)},
//                {"$.techRecord[0].applicantDetails.name", ""},
//                {"$.techRecord[0].applicantDetails.name", 10},
//                {"$.techRecord[0].applicantDetails.address1", RandomStringUtils.randomAlphanumeric(61)},
//                {"$.techRecord[0].applicantDetails.address1", ""},
//                {"$.techRecord[0].applicantDetails.address1", 10},
//                {"$.techRecord[0].applicantDetails.address2", RandomStringUtils.randomAlphanumeric(61)},
//                {"$.techRecord[0].applicantDetails.address2", ""},
//                {"$.techRecord[0].applicantDetails.address2", 10},
//                {"$.techRecord[0].applicantDetails.address3", RandomStringUtils.randomAlphanumeric(61)},
//                {"$.techRecord[0].applicantDetails.address3", ""},
//                {"$.techRecord[0].applicantDetails.address3", 10},
//                {"$.techRecord[0].applicantDetails.postTown", RandomStringUtils.randomAlphanumeric(61)},
//                {"$.techRecord[0].applicantDetails.postTown", ""},
//                {"$.techRecord[0].applicantDetails.postTown", 10},
//                {"$.techRecord[0].applicantDetails.postCode", RandomStringUtils.randomAlphanumeric(13)},
//                {"$.techRecord[0].applicantDetails.postCode", ""},
//                {"$.techRecord[0].applicantDetails.postCode", 10},
//                {"$.techRecord[0].applicantDetails.telephoneNumber", RandomStringUtils.randomAlphanumeric(26)},
//                {"$.techRecord[0].applicantDetails.telephoneNumber", ""},
//                {"$.techRecord[0].applicantDetails.telephoneNumber", 10},
//                {"$.techRecord[0].applicantDetails.emailAddress", RandomStringUtils.randomAlphanumeric(256)},
//                {"$.techRecord[0].applicantDetails.emailAddress", ""},
//                {"$.techRecord[0].applicantDetails.emailAddress", 10},
//                {"$.techRecord[0].microfilm.microfilmDocumentType", RandomStringUtils.randomAlphanumeric(10)},
//                {"$.techRecord[0].microfilm.microfilmDocumentType", 10},
//                {"$.techRecord[0].microfilm.microfilmRollNumber", RandomStringUtils.randomAlphanumeric(6)},
//                {"$.techRecord[0].microfilm.microfilmRollNumber", ""},
//                {"$.techRecord[0].microfilm.microfilmRollNumber", 10},
//                {"$.techRecord[0].microfilm.microfilmSerialNumber", RandomStringUtils.randomAlphanumeric(5)},
//                {"$.techRecord[0].microfilm.microfilmSerialNumber", ""},
//                {"$.techRecord[0].plates[0].plateSerialNumber", RandomStringUtils.randomAlphanumeric(13)},
//                {"$.techRecord[0].plates[0].plateSerialNumber", ""},
//                {"$.techRecord[0].plates[0].plateSerialNumber", 10},
//                {"$.techRecord[0].plates[0].plateIssueDate", "31/12/2100"},
//                {"$.techRecord[0].plates[0].plateIssueDate", false},
//                {"$.techRecord[0].plates[0].plateReasonForIssue", RandomStringUtils.randomAlphanumeric(10)},
//                {"$.techRecord[0].plates[0].plateReasonForIssue", 10},
//                {"$.techRecord[0].plates[0].plateIssuer", RandomStringUtils.randomAlphanumeric(151)},
//                {"$.techRecord[0].plates[0].plateIssuer", ""},
//                {"$.techRecord[0].plates[0].plateIssuer", 10},
//                {"$.techRecord[0].plates[0].plateIssuer", 10},
//                {"$.techRecord[0].plates[0].plateIssuer", 10},
//                {"$.techRecord[0].remarks", RandomStringUtils.randomAlphanumeric(1025)},
//                {"$.techRecord[0].remarks", 99},
//                {"$.techRecord[0].dispensations", RandomStringUtils.randomAlphanumeric(161)},
//                {"$.techRecord[0].dispensations", 99},
//                {"$.techRecord[0].reasonForCreation", RandomStringUtils.randomAlphanumeric(101)},
//                {"$.techRecord[0].reasonForCreation", ""},
//                {"$.techRecord[0].reasonForCreation", 10}
//        });
//    }
//
//    private final String jsonPath;
//    private final Object value;
//
//    public PutVehiclePsvFieldRestrictions(String jsonPath, Object value) {
//        this.jsonPath = jsonPath;
//        this.value = value;
//    }
//
//    @WithTag("Vtm")
//    @Title("CVSB-10242 - AC3 - Attempt to update a vehicle with unexpected values for a field that accepts only specific values " +
//            "AC4 - Attempt to update a vehicle, using a field with its value outside of the min/max length for that field")
//    @Test
//    public void testValidatePsvAttributesDataTypesAndRestrictions() {
//        String putRequestBodyPsv = GenericData.readJsonValueFromFile("technical-records_psv_all_fields.json", "$");
//        JsonPathAlteration restriction = new JsonPathAlteration(jsonPath, value, "", "REPLACE");
//        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(restriction));
//        vehicleTechnicalRecordsSteps.putVehicleTechnicalRecordsForVehicleWithAlterations(randomSystemNumber, putRequestBodyPsv, alterations);
//        vehicleTechnicalRecordsSteps.statusCodeShouldBe(400);
//    }
//}
