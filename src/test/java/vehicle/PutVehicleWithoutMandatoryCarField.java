//package vehicle;
//
//import data.GenericData;
//import io.restassured.http.ContentType;
//import io.restassured.response.Response;
//import junit.framework.TestCase;
//import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
//import net.thucydides.core.annotations.Steps;
//import net.thucydides.core.annotations.Title;
//import net.thucydides.core.annotations.WithTag;
//import net.thucydides.junit.annotations.TestData;
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
//@RunWith(SerenityParameterizedRunner.class)
//public class PutVehicleWithoutMandatoryCarField extends TestCase {
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
//                {"$.techRecord[0].vehicleType"},
//                {"$.techRecord[0].noOfAxles"},
//                {"$.techRecord[0].vehicleSubclass"},
//                {"$.techRecord[0].reasonForCreation"},
//        });
//    }
//
//    private final String jsonPath;
//
//    public PutVehicleWithoutMandatoryCarField(String jsonPath) {
//        this.jsonPath = jsonPath;
//    }
//
//    @WithTag("Vtm")
//    @Title("CVSB-11814 - AC2. PUT: Attempt to update a vehicle without a mandatory field - CAR")
//    @Test
//    public void testValidatePutRequestWithoutMandatoryCarAttribute() {
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
//        JsonPathAlteration restriction = new JsonPathAlteration(jsonPath, "", "", "DELETE");
//        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(restriction,alterationVin,alterationVrm));
//
//        //PUT tech-records and verify the error response
//        vehicleTechnicalRecordsSteps.putVehicleTechnicalRecordsForVehicleWithAlterations(systemNumber, putRequestBody, alterations);
//        vehicleTechnicalRecordsSteps.statusCodeShouldBe(400);
//    }
//}
