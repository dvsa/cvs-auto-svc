package testresults;


import data.GenericData;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import junit.framework.TestCase;
import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.junit.annotations.TestData;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.http.HttpStatus;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import steps.TestResultsSteps;
import util.BasePathFilter;
import util.JsonPathAlteration;

import java.text.SimpleDateFormat;
import java.util.*;

import static io.restassured.RestAssured.given;
import static util.WriterReader.saveUtils;


@RunWith(SerenityParameterizedRunner.class)
public class PutTestResultsFieldRestrictionsForMSVA extends TestCase {


    static String randomVin;
    static String randomSystemNumber;
    static String randomTestResultId;

    @BeforeClass
    public static void createRecord() {

        Date date = new Date();

        // Read the base test result JSON.
        String postRequestBody = GenericData.readJsonValueFromFile("technical-records_post_payload_motorcycle_10328.json", "$");

        // Create alteration to add one more tech record to in the request body
        randomSystemNumber = GenericData.generateRandomSystemNumber();
        randomVin = GenericData.generateRandomVin();
        JsonPathAlteration alterationSystemNumberVehicle = new JsonPathAlteration("$.systemNumber", randomSystemNumber, "", "REPLACE");
        JsonPathAlteration alterationVinVehicle = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterationsVehicle = new ArrayList<>(Arrays.asList(alterationSystemNumberVehicle,
                alterationVinVehicle));

        String alteredBody = GenericData.applyJsonAlterations(postRequestBody, alterationsVehicle);
        Response response = given().filters(new BasePathFilter())
                .contentType(ContentType.JSON)
                .body(alteredBody)
                .log().method().log().uri().log().body()
                .post("/vehicles");
        if (response.getStatusCode() == HttpStatus.SC_UNAUTHORIZED || response.getStatusCode() == HttpStatus.SC_FORBIDDEN) {
            saveUtils();
            response = given().filters(new BasePathFilter())
                    .contentType(ContentType.JSON)
                    .body(alteredBody)
                    .log().method().log().uri().log().body()
                    .post("/vehicles");
        }

        Assert.assertEquals(HttpStatus.SC_CREATED, response.getStatusCode());


        // Read base JSON to create test-results
        String testResultRecord = GenericData.readJsonValueFromFile("test-results_motorcycle_post_payload_10372.json", "$");

        //Create alteration to add one more tech record to in the request body
        randomTestResultId = UUID.randomUUID().toString();
        JsonPathAlteration alterationSystemNumberTestResults = new JsonPathAlteration("$.systemNumber", randomSystemNumber, "", "REPLACE");
        JsonPathAlteration alterationVinTestResults = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationTestResultIdPost = new JsonPathAlteration("$.testResultId", randomTestResultId, "", "REPLACE");
        JsonPathAlteration alterationTestStartTimestamp = new JsonPathAlteration("$.testStartTimestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 1)), "", "REPLACE");
        JsonPathAlteration alterationTestEndTimestamp = new JsonPathAlteration("$.testEndTimestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 4)), "", "REPLACE");
        JsonPathAlteration alterationTestTypeStartTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeStartTimestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 2)), "", "REPLACE");
        JsonPathAlteration alterationTestTypeEndTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeEndTimestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 3)), "", "REPLACE");


        // Collate the list of alterations.
        List<JsonPathAlteration> alterationsTestResults = new ArrayList<>(Arrays.asList(
                alterationSystemNumberTestResults,
                alterationVinTestResults,
                alterationTestResultIdPost,
                alterationTestStartTimestamp,
                alterationTestEndTimestamp,
                alterationTestTypeStartTimestamp,
                alterationTestTypeEndTimestamp));

        String alteredBodyTestResult = GenericData.applyJsonAlterations(testResultRecord, alterationsTestResults);
        Response responseTestResult = given().filters(new BasePathFilter())
                .contentType(ContentType.JSON)
                .body(alteredBodyTestResult)
                .log().method().log().uri().log().body()
                .post("/test-results");
        if (responseTestResult.getStatusCode() == HttpStatus.SC_UNAUTHORIZED || responseTestResult.getStatusCode() == HttpStatus.SC_FORBIDDEN) {
            saveUtils();
            responseTestResult = given().filters(new BasePathFilter())
                    .contentType(ContentType.JSON)
                    .body(alteredBodyTestResult)
                    .log().method().log().uri().log().body()
                    .post("/test-results");
        }
        Assert.assertEquals(HttpStatus.SC_CREATED, responseTestResult.getStatusCode());

    }

    @Steps
    TestResultsSteps testResultsSteps;

    @TestData
    public static Collection<Object[]> testData(){
        return Arrays.asList(new Object[][]{
                {"$.testResult.testTypes[0].testTypeName",456},
                {"$.testResult.testTypes[0].testCode", 456},
                {"$.testResult.testTypes[0].testTypeName",456},
                {"$.testResult.testTypes[0].testNumber",456},
                {"$.testResult.testTypes[0].certificateNumber",999},
                {"$.testResult.testTypes[0].testTypeStartTimestamp","21/08/1987T25:61:41.224Z"},
                {"$.testResult.testTypes[0].testTypeStartTimestamp",false},
                {"$.testResult.testTypes[0].testTypeEndTimestamp","21/08/1987T25:61:41.224Z"},
                {"$.testResult.testTypes[0].testTypeEndTimestamp",false},
                {"$.testResult.testTypes[0].testResult", 100},
                {"$.testResult.testTypes[0].testResult", RandomStringUtils.randomAlphanumeric(10)},
                {"$.testResult.testTypes[0].prohibitionIssued", 100},
                {"$.testResult.testTypes[0].prohibitionIssued","String"},
                {"$.testResult.testTypes[0].reasonForAbandoning",100},
                {"$.testResult.testTypes[0].reasonForAbandoning",true},
                {"$.testResult.testTypes[0].additionalCommentsForAbandon",100},
                {"$.testResult.testTypes[0].additionalCommentsForAbandon",RandomStringUtils.randomAlphanumeric(501)},
                {"$.testResult.testTypes[0].additionalNotesRecorded",100},
                {"$.testResult.testTypes[0].additionalNotesRecorded",RandomStringUtils.randomAlphanumeric(501)},
                {"$.testResult.testTypes[0].customDefects[0].referenceNumber",999},
                {"$.testResult.testTypes[0].customDefects[0].referenceNumber",true},
                {"$.testResult.testTypes[0].customDefects[0].defectName",999},
                {"$.testResult.testTypes[0].customDefects[0].defectName",true},
                {"$.testResult.testTypes[0].customDefects[0].defectNotes",999},
                {"$.testResult.testTypes[0].customDefects[0].defectNotes",true},
        });
    }
    private String jsonPath;
    private final Object value;

    public PutTestResultsFieldRestrictionsForMSVA(String jsonPath, Object value) {
        this.jsonPath = jsonPath;
        this.value = value;
    }

    @WithTag("Vtm")
    @Title("CVSB-10372 - AC3. PUT: Attempt to update a test record with invalid values" +
            "AC4. PUT: Attempt to update a test record, using a field which has a field length outside of the min/max length for that field")
    @Test
    public void PutTestResultsForMVSA() {

        Date date = new Date();

        // Read the base JSON for PUT test-results
        String putRequestBody = GenericData.readJsonValueFromFile("test-results_motorcycle_put_payload_10372.json", "$");

        JsonPathAlteration alterationSystemNumberPutTestResults = new JsonPathAlteration("$.testResult.systemNumber", randomSystemNumber, "", "REPLACE");
        JsonPathAlteration alterationVinPutTestResults = new JsonPathAlteration("$.testResult.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationTestResultIdPut = new JsonPathAlteration("$.testResult.testResultId", randomTestResultId, "", "REPLACE");
        JsonPathAlteration alterationPutTestStartTimestamp = new JsonPathAlteration("$.testResult.testStartTimestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 1)), "", "REPLACE");
        JsonPathAlteration alterationPutTestEndTimestamp = new JsonPathAlteration("$.testResult.testEndTimestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 4)), "", "REPLACE");
        JsonPathAlteration alterationPutTestTypeStartTimestamp = new JsonPathAlteration("$.testResult.testTypes[0].testTypeStartTimestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 2)), "", "REPLACE");
        JsonPathAlteration alterationPutTestTypeEndTimestamp = new JsonPathAlteration("$.testResult.testTypes[0].testTypeEndTimestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 3)), "", "REPLACE");
        JsonPathAlteration restriction = new JsonPathAlteration(jsonPath, value, "", "REPLACE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterationsPutTestResults = new ArrayList<>(Arrays.asList(
                alterationSystemNumberPutTestResults,
                alterationVinPutTestResults,
                alterationTestResultIdPut,
                alterationPutTestStartTimestamp,
                alterationPutTestEndTimestamp,
                alterationPutTestTypeStartTimestamp,
                alterationPutTestTypeEndTimestamp,
                restriction
        ));

        testResultsSteps.putTestResultsWithAlterations(randomTestResultId, putRequestBody, alterationsPutTestResults);
        testResultsSteps.statusCodeShouldBe(HttpStatus.SC_BAD_REQUEST);
    }
}
