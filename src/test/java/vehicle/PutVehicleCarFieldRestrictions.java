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
//import org.apache.http.HttpStatus;
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
//public class PutVehicleCarFieldRestrictions {
//
//    static String randomVin;
//    static String randomVrm;
//
//    @BeforeClass
//    public static void createRecord() {
//        // TEST SETUP
//        // generate random Vin
//        randomVin = GenericData.generateRandomVin();
//        // generate random Vrm
//        randomVrm = GenericData.generateRandomVrm();
//        // read post request body from file
//        String postRequestBody = GenericData.readJsonValueFromFile("technical-records_post_payload_car_10328.json", "$");
//        // create alteration to change Vin in the post request body with the random generated Vin
//        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
//        // create alteration to change primary vrm in the request body with the random generated primary vrm
//        JsonPathAlteration alterationVrm = new JsonPathAlteration("$.primaryVrm", randomVrm, "", "REPLACE");
//
//        // initialize the alterations list with both declared alterations
//        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(alterationVin, alterationVrm));
//
//        String alteredBody = GenericData.applyJsonAlterations(postRequestBody, alterations);
//        Response response = given().filters(new BasePathFilter())
//                .contentType(ContentType.JSON)
//                .body(alteredBody)
//                .log().method().log().uri().log().body()
//                .post("/vehicles");
//
//        if (response.getStatusCode() == HttpStatus.SC_UNAUTHORIZED || response.getStatusCode() == HttpStatus.SC_FORBIDDEN) {
//            saveUtils();
//            response = given().filters(new BasePathFilter())
//                    .contentType(ContentType.JSON)
//                    .body(alteredBody)
//                    .log().method().log().uri().log().body()
//                    .post("/vehicles");
//        }
//
//        Assert.assertEquals(HttpStatus.SC_CREATED, response.getStatusCode());
//    }
//
//    @Steps
//    VehicleTechnicalRecordsSteps vehicleTechnicalRecordsSteps;
//
//    @TestData
//    public static Collection<Object[]> testData(){
//        return Arrays.asList(new Object[][]{
//                {"$.techRecord[0].statusCode", 100},
//                {"$.techRecord[0].statusCode", RandomStringUtils.randomAlphanumeric(10)},
//                {"$.primaryVrm", 100},
//                {"$.primaryVrm", RandomStringUtils.randomAlphanumeric(10)},
//                {"$.techRecord[0].vehicleType", 100},
//                {"$.techRecord[0].vehicleType", RandomStringUtils.randomAlphanumeric(10)},
//                {"$.techRecord[0].regnDate", "21/08/1987"},
//                {"$.techRecord[0].regnDate", false},
//                {"$.techRecord[0].manufactureYear", 10000},
//                {"$.techRecord[0].manufactureYear", -1},
//                {"$.techRecord[0].manufactureYear", RandomStringUtils.randomAlphabetic(4)},
//                {"$.techRecord[0].noOfAxles", 100},
//                {"$.techRecord[0].noOfAxles", -1},
//                {"$.techRecord[0].noOfAxles", RandomStringUtils.randomAlphabetic(2)},
//                {"$.techRecord[0].vehicleClass.description", RandomStringUtils.randomAlphanumeric(10)},
//                {"$.techRecord[0].vehicleClass.description", 10},
//                {"$.techRecord[0].vehicleClass.code", 10},
//                {"$.techRecord[0].vehicleSubclass", 999},
//                {"$.techRecord[0].vehicleSubclass", RandomStringUtils.randomAlphanumeric(10)},
//                {"$.techRecord[0].euVehicleCategory", RandomStringUtils.randomAlphanumeric(3)},
//                {"$.techRecord[0].euVehicleCategory", 10},
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
//                {"$.techRecord[0].remarks", 10},
//                {"$.techRecord[0].remarks", RandomStringUtils.randomAlphanumeric(1025)},
//                {"$.techRecord[0].reasonForCreation", RandomStringUtils.randomAlphanumeric(101)},
//                {"$.techRecord[0].reasonForCreation", ""},
//                {"$.techRecord[0].reasonForCreation", 10}
//
//        });
//    }
//
//    private final String jsonPath;
//    private final Object value;
//
//    public PutVehicleCarFieldRestrictions(String jsonPath, Object value) {
//        this.jsonPath = jsonPath;
//        this.value = value;
//    }
//
//    @WithTag("Vtm")
//    @Title("CVSB-11814 - AC4. PUT: Attempt to update a vehicle with unexpected values for a field that accepts only specific values " +
//            "AC5. PUT: Attempt to update a vehicle, using a field which has a field value outside of the min/max length for that field - CAR")
//    @Test
//    public void testPutValidateCarAttributesDataTypesAndRestrictions() {
//
//        // Get tech-records and retrieve System Number
//        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecords(randomVin);
//        String systemNumber = vehicleTechnicalRecordsSteps.getSystemNumber();
//
//        // read PUT request body from file
//        String putRequestBody = GenericData.readJsonValueFromFile("technical-records_put_payload_car_11814.json", "$");
//
//        // Create alterations to edit one more more fields in the request body
//        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
//        JsonPathAlteration alterationVrm = new JsonPathAlteration("$.primaryVrm", randomVrm, "", "REPLACE");
//
//        //Collate the alterations
//        JsonPathAlteration restriction = new JsonPathAlteration(jsonPath, value, "", "REPLACE");
//        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(alterationVin,alterationVrm,restriction));
//
//        //PUT tech-records and verify the error response
//        vehicleTechnicalRecordsSteps.putVehicleTechnicalRecordsForVehicleWithAlterations(systemNumber, putRequestBody, alterations);
//        vehicleTechnicalRecordsSteps.statusCodeShouldBe(HttpStatus.SC_BAD_REQUEST);
//    }
//}
