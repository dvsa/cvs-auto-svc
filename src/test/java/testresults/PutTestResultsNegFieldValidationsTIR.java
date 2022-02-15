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
import org.apache.http.HttpStatus;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import steps.TestResultsSteps;
import util.BasePathFilter;
import util.JsonPathAlteration;
import java.util.*;
import static io.restassured.RestAssured.given;
import static util.WriterReader.saveUtils;


@RunWith(SerenityParameterizedRunner.class)
public class PutTestResultsNegFieldValidationsTIR extends TestCase {

    static String randomVin;
    static String randomSystemNumber;
    static String randomTestResultId;

    @BeforeClass
    public static void createRecord() {
        // Read the base test result JSON.
        String postRequestBody = GenericData.readJsonValueFromFile("technical-records_trl_all_fields.json","$");

        // Create alteration to add one more tech record to in the request body
        randomSystemNumber = GenericData.generateRandomSystemNumber();
        randomVin = GenericData.generateRandomVin();
        JsonPathAlteration alterationSystemNumberVehicle = new JsonPathAlteration("$.systemNumber", randomSystemNumber, "", "REPLACE");
        JsonPathAlteration alterationVinVehicle = new JsonPathAlteration("$.vin", randomVin,"","REPLACE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterationsVehicle = new ArrayList<>(Arrays.asList(alterationSystemNumberVehicle, alterationVinVehicle));

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

        String testResultRecord = GenericData.readJsonValueFromFile("test-results_post_payload_trl_10300.json", "$");

        randomTestResultId = UUID.randomUUID().toString();
        JsonPathAlteration alterationSystemNumberTestResults = new JsonPathAlteration("$.systemNumber", randomSystemNumber, "", "REPLACE");
        JsonPathAlteration alterationVinTestResults = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationTestResultIdPost = new JsonPathAlteration("$.testResultId", randomTestResultId, "", "REPLACE");

        List<JsonPathAlteration> alterationsTestResults = new ArrayList<>(Arrays.asList(alterationSystemNumberTestResults, alterationVinTestResults,alterationTestResultIdPost));

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
                {"$.testResult.testTypes[0].testTypeId", "49"},
                {"$.testResult.testTypes[0].testTypeId", "56"},
                {"$.testResult.testTypes[0].testTypeId", "57"},
        });
    }

    private String jsonPath;
    private Object value;


    public PutTestResultsNegFieldValidationsTIR(String jsonPath, Object value) {
        this.jsonPath = jsonPath;
        this.value = value;
    }

    @WithTag("Vtm")
    @Title("CVSB-10300 - AC1 PUT: Attempt to update a test record with a not applicable field - TRL")
    @Test
    public void testResultsPutWithNotApplicableFields() {

        String jsonFileName = "test-results_put_payload_trl_10300.json";
        String putRequestBody = GenericData.updateJson(testResultsSteps,jsonFileName,"$");

        JsonPathAlteration alterationSystemNumberPutTestResults = new JsonPathAlteration("$.testResult.systemNumber", randomSystemNumber, "", "REPLACE");
        JsonPathAlteration alterationVinPutTestResults = new JsonPathAlteration("$.testResult.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationTestResultIdPutTestResults = new JsonPathAlteration("$.testResult.testResultId", randomTestResultId, "", "REPLACE");
        JsonPathAlteration restriction = new JsonPathAlteration(jsonPath, value, "", "REPLACE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterationsPutTestResults = new ArrayList<>(Arrays.asList(
                alterationSystemNumberPutTestResults,
                alterationVinPutTestResults,
                alterationTestResultIdPutTestResults,
                restriction
        ));

        testResultsSteps.putTestResultsWithAlterations(randomSystemNumber,putRequestBody,alterationsPutTestResults);
        testResultsSteps.statusCodeShouldBe(HttpStatus.SC_BAD_REQUEST);
        testResultsSteps.validatePostErrorDataContains("testExpiryDate", "is not allowed");
        testResultsSteps.validatePostErrorDataContains("testAnniversaryDate", "is not allowed");
        testResultsSteps.validatePostErrorDataContains("modType", "is not allowed");
        testResultsSteps.validatePostErrorDataContains("particulateTrapSerialNumber", "is not allowed");
        testResultsSteps.validatePostErrorDataContains("smokeTestKLimitApplied", "is not allowed");
        testResultsSteps.validatePostErrorDataContains("emissionStandard", "is not allowed");
        testResultsSteps.validatePostErrorDataContains("modificationTypeUsed", "is not allowed");
        testResultsSteps.validatePostErrorDataContains("particulateTrapFitted", "is not allowed");
        testResultsSteps.validatePostErrorDataContains("fuelType", "is not allowed");
        testResultsSteps.validatePostErrorDataContains("numberOfSeatbeltsFitted", "is not allowed");
        testResultsSteps.validatePostErrorDataContains("seatbeltInstallationCheckDate", "is not allowed");
        testResultsSteps.validatePostErrorDataContains("defects", "is not allowed");
        testResultsSteps.validatePostErrorDataContains("lastSeatbeltInstallationCheckDate", "is not allowed");
    }
}
