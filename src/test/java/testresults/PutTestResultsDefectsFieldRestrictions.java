//package testresults;
//
//import data.GenericData;
//import io.restassured.http.ContentType;
//import junit.framework.TestCase;
//import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
//import net.thucydides.core.annotations.Steps;
//import net.thucydides.core.annotations.Title;
//import net.thucydides.core.annotations.WithTag;
//import net.thucydides.junit.annotations.TestData;
//import org.apache.commons.lang3.RandomStringUtils;
//import org.apache.commons.lang3.time.DateUtils;
//import org.apache.http.HttpStatus;
//import org.junit.BeforeClass;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import steps.TestResultsSteps;
//import util.BasePathFilter;
//import util.JsonPathAlteration;
//import io.restassured.response.Response;
//import static io.restassured.RestAssured.given;
//import java.text.SimpleDateFormat;
//import java.util.*;
//import static util.WriterReader.saveUtils;
//import org.junit.Assert;
//
//
//@RunWith(SerenityParameterizedRunner.class)
//public class PutTestResultsDefectsFieldRestrictions extends TestCase {
//
//    static String randomVin;
//    static String randomSystemNumber;
//    static String randomTestResultId;
//
//    @BeforeClass
//    public static void createRecord(){
//
//        Date date  = new Date();
//
//        // Read Payload for POST tech-records
//        String postRequestBody = GenericData.readJsonValueFromFile("technical-records_hgv_all_fields.json", "$");
//
//        // Create alteration for SystemNumber and vin
//        randomSystemNumber = GenericData.generateRandomSystemNumber();
//        randomVin = GenericData.generateRandomVin();
//        JsonPathAlteration alterationSystemNumberVehicle = new JsonPathAlteration("$.systemNumber", randomSystemNumber, "", "REPLACE");
//        JsonPathAlteration alterationVinVehicle = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
//
//        // Collate the alterations
//        List<JsonPathAlteration> alterationsVehicle =
//                new ArrayList<>(Arrays.asList(alterationSystemNumberVehicle, alterationVinVehicle));
//
//        String alteredBody = GenericData.applyJsonAlterations(postRequestBody, alterationsVehicle);
//        Response response = given().filters(new BasePathFilter())
//                .contentType(ContentType.JSON)
//                .body(alteredBody)
//                .log().method().log().uri().log().body()
//                .post("/vehicles");
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
//        System.out.println("status code for tech-records" + response.getStatusCode());
//
//        // Read Payload for POST test-results
//        String testResultRecord = GenericData.readJsonValueFromFile("test-results_post_payload_12378.json", "$");
//
//        // Create alteration for timestamps and SystemNumber and vin
//        randomTestResultId = UUID.randomUUID().toString();
//        JsonPathAlteration alterationSystemNumberTestResults = new JsonPathAlteration("$.systemNumber", randomSystemNumber, "", "REPLACE");
//        JsonPathAlteration alterationVinTestResults = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
//        JsonPathAlteration alterationTestResultIdPost = new JsonPathAlteration("$.testResultId", randomTestResultId, "", "REPLACE");
//
//        JsonPathAlteration alterationTestStartTimestamp = new JsonPathAlteration
//                ("$.testStartTimestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 1)), "", "REPLACE");
//
//        JsonPathAlteration alterationTestEndTimestamp = new JsonPathAlteration
//                ("$.testEndTimestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 4)), "", "REPLACE");
//
//        JsonPathAlteration alterationTestTypeStartTimestamp = new JsonPathAlteration
//                ("$.testTypes[0].testTypeStartTimestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 2)), "", "REPLACE");
//
//        JsonPathAlteration alterationTestTypeEndTimestamp = new JsonPathAlteration
//                ("$.testTypes[0].testTypeEndTimestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 3)), "", "REPLACE");
//
//
//        // Collate the list of alterations.
//        List<JsonPathAlteration> alterationsTestResults = new ArrayList<>(Arrays.asList(
//                alterationSystemNumberTestResults,
//                alterationVinTestResults,
//                alterationTestResultIdPost,
//                alterationTestStartTimestamp,
//                alterationTestEndTimestamp,
//                alterationTestTypeStartTimestamp,
//                alterationTestTypeEndTimestamp));
//
//        String alteredBodyTestResult = GenericData.applyJsonAlterations(testResultRecord, alterationsTestResults);
//
//        Response responseTestResult = given().filters(new BasePathFilter())
//                .contentType(ContentType.JSON)
//                .body(alteredBodyTestResult)
//                .log().method().log().uri().log().body()
//                .post("/test-results");
//        if (responseTestResult.getStatusCode() == HttpStatus.SC_UNAUTHORIZED || responseTestResult.getStatusCode() == HttpStatus.SC_FORBIDDEN) {
//            saveUtils();
//            responseTestResult = given().filters(new BasePathFilter())
//                    .contentType(ContentType.JSON)
//                    .body(alteredBodyTestResult)
//                    .log().method().log().uri().log().body()
//                    .post("/test-results");
//        }
//        Assert.assertEquals(HttpStatus.SC_CREATED, responseTestResult.getStatusCode());
//        System.out.println("status code for test result" + responseTestResult.getStatusCode());
//    }
//
//    @Steps
//    TestResultsSteps testResultsSteps;
//
//    @TestData
//    public static Collection<Object[]> testData(){
//        return Arrays.asList(new Object[][]{
//                {"$.testResult.testTypes[0].defects[0].imNumber", "test"},
//                {"$.testResult.testTypes[0].defects[0].itemNumber", "testing"},
//                {"$.testResult.testTypes[0].defects[0].deficiencyId", 1 },
//                {"$.testResult.testTypes[0].defects[0].deficiencyId", "A" },
//                {"$.testResult.testTypes[0].defects[0].deficiencyId", "" },
//                {"$.testResult.testTypes[0].defects[0].deficiencyId", RandomStringUtils.randomAlphanumeric(2)},
//                {"$.testResult.testTypes[0].defects[0].deficiencyCategory", 1 },
//                {"$.testResult.testTypes[0].defects[0].deficiencyCategory", RandomStringUtils.randomAlphanumeric(7)},
//                {"$.testResult.testTypes[0].defects[0].deficiencyText", 4 },
//                {"$.testResult.testTypes[0].defects[0].additionalInformation.location.vertical", 4},
//                {"$.testResult.testTypes[0].defects[0].additionalInformation.location.vertical", RandomStringUtils.randomAlphanumeric(7)},
//                {"$.testResult.testTypes[0].defects[0].additionalInformation.location.horizontal", 4},
//                {"$.testResult.testTypes[0].defects[0].additionalInformation.location.horizontal", RandomStringUtils.randomAlphanumeric(7)},
//                {"$.testResult.testTypes[0].defects[0].additionalInformation.location.lateral", 99},
//                {"$.testResult.testTypes[0].defects[0].additionalInformation.location.lateral", RandomStringUtils.randomAlphanumeric(6)},
//                {"$.testResult.testTypes[0].defects[0].additionalInformation.location.longitudinal", 99},
//                {"$.testResult.testTypes[0].defects[0].additionalInformation.location.longitudinal", RandomStringUtils.randomAlphanumeric(6)},
//                {"$.testResult.testTypes[0].defects[0].additionalInformation.location.rowNumber", "Test"},
//                {"$.testResult.testTypes[0].defects[0].additionalInformation.location.rowNumber", RandomStringUtils.randomNumeric(21)},
//                {"$.testResult.testTypes[0].defects[0].additionalInformation.location.seatNumber", "Test"},
//                {"$.testResult.testTypes[0].defects[0].additionalInformation.location.seatNumber", RandomStringUtils.randomNumeric(7)},
//                {"$.testResult.testTypes[0].defects[0].additionalInformation.location.axleNumber", "Test"},
//                {"$.testResult.testTypes[0].defects[0].additionalInformation.location.axleNumber", RandomStringUtils.randomNumeric(11)},
//
//        });
//    }
//
//    private final String jsonPath;
//    private final Object value;
//
//    public PutTestResultsDefectsFieldRestrictions(String jsonPath, Object value) {
//        this.jsonPath = jsonPath;
//        this.value = value;
//    }
//
//    @WithTag("Vtm")
//    @Title("AC1: PUT+ request: Original Test Record is updated and attributes are automatically set")
//    @Test
//    public void testValidateDefectFieldValidations(){
//        // PUT test-results
//        String putRequestBody = GenericData.readJsonValueFromFile("test-results_put_payload_12378.json","$");
//        JsonPathAlteration restriction = new JsonPathAlteration(jsonPath, value, "", "REPLACE");
//        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(restriction));
//        testResultsSteps.putTestResultsWithAlterations(randomSystemNumber,putRequestBody,alterations);
//        testResultsSteps.statusCodeShouldBe(HttpStatus.SC_BAD_REQUEST);
//    }
//}
