//package testresults;
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
//import org.junit.Assert;
//import org.junit.BeforeClass;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import steps.*;
//import util.BasePathFilter;
//import util.JsonPathAlteration;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Collection;
//import java.util.List;
//import static io.restassured.RestAssured.given;
//import static util.WriterReader.saveUtils;
//
//@RunWith(SerenityParameterizedRunner.class)
//public class PutTestResultsWithoutMandatoryField extends TestCase {
//
//    static String randomVin;
//
//    @BeforeClass
//    public static void createRecord() {
//        // TEST SETUP
//        // generate random Vin
//        randomVin = GenericData.generateRandomVin();
//        // generate random Vrm
//        String randomVrm = GenericData.generateRandomVrm();
//        // read post request body from file
//        String postRequestBody = GenericData.readJsonValueFromFile("test-results_duplicate_chassis_10752.json", "$");
//        // create alteration to change Vin in the post request body with the random generated Vin
//        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
//        // create alteration to change primary vrm in the request body with the random generated primary vrm
//        JsonPathAlteration alterationVrm = new JsonPathAlteration("$.vrm", randomVrm, "", "REPLACE");
//
//        // initialize the alterations list with both declared alterations
//        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(alterationVin, alterationVrm));
//
//
//        String alteredBody = GenericData.applyJsonAlterations(postRequestBody, alterations);
//        Response response = given().filters(new BasePathFilter())
//                .contentType(ContentType.JSON)
//                .body(alteredBody)
//                .log().method().log().uri().log().body()
//                .post("/test-results");
//
//        if (response.getStatusCode() == 401 || response.getStatusCode() == 403) {
//            saveUtils();
//            response = given().filters(new BasePathFilter())
//                    .contentType(ContentType.JSON)
//                    .body(alteredBody)
//                    .log().method().log().uri().log().body()
//                    .post("/test-results");
//        }
//
//        Assert.assertEquals(201, response.getStatusCode());
//    }
//
//    @Steps
//    TestResultsSteps testResultsSteps;
//
//    @TestData
//    public static Collection<Object[]> testData(){
//        return Arrays.asList(new Object[][]{
//                {"$.vin"},
//                {"$.vrm"},
//                {"$.countryOfRegistration"},
//                {"$.euVehicleCategory"},
//                {"$.odometerReading"},
//                {"$.odometerReadingUnits"},
//                {"$.testTypes[0].testResult"},
//                {"$.testTypes[0].reasonForAbandoning"},
//                {"$.testTypes[0].certificateNumber"},
//                {"$.testTypes[0].testExpiryDate"},
//                {"$.testTypes[0].testTypeStartTimestamp"},
//                {"$.testTypes[0].testTypeEndTimestamp"},
//                {"$.testTypes[0].prohibitionIssued"},
//                {"$.testTypes[0].emissionStandard"},
//                {"$.testTypes[0].smokeTestKLimitApplied"},
//                {"$.testTypes[0].fuelType"},
//                {"$.testTypes[0].modType.code"},
//                {"$.testTypes[0].modType.description"},
//                {"$.testTypes[0].modificationTypeUsed"},
//                {"$.testTypes[0].particulateTrapFitted"},
//                {"$.testTypes[0].particulateTrapSerialNumber"},
//                {"$.testStationName"},
//                {"$.testStationPNumber"},
//                {"$.testStationType"},
//                {"$.testerName"},
//                {"$.testerEmailAddress"},
//        });
//    }
//
//    private final String jsonPath;
//
//    public PutTestResultsWithoutMandatoryField(String jsonPath) {
//        this.jsonPath = jsonPath;
//    }
//
//    @WithTag("Vtm")
//    @Title("CVSB-10284 - AC1. PUT: Attempt to update a test record without a mandatory field + " +
//            "CVSB-10280 - AC4: Update the PUT request in testResults API ")
//    @Test
//    public void testValidatePutRequestWithoutMandatoryAttribute() {
//        String jsonFileName = "test-results_duplicate_chassis_10752.json";
//        String putRequestBody = GenericData.updateJson(jsonFileName,false);
//
//        JsonPathAlteration restriction = new JsonPathAlteration(jsonPath, "", "", "DELETE");
//        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(restriction));
//        testResultsSteps.putTestResultsWithAlterations(randomVin,putRequestBody,alterations);
//        testResultsSteps.statusCodeShouldBe(400);
//    }
//}
