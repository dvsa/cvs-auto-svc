package testresults;

import data.GenericData;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import junit.framework.TestCase;
import model.testresults.TestResultsStatus;
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
import steps.VehicleTechnicalRecordsSteps;
import util.BasePathFilter;
import util.JsonPathAlteration;

import java.util.*;

import static io.restassured.RestAssured.given;
import static util.WriterReader.saveUtils;


@RunWith(SerenityParameterizedRunner.class)
public class PutTestResultsCertificateNotGenerated extends TestCase {

    static String randomVin;
    static String randomSystemNumber;
    static String randomTestResultId;

    @BeforeClass
    public static void createRecord() {
        // Read the base test result JSON.
        String postRequestBody = GenericData.readJsonValueFromFile("technical-records_hgv_all_fields.json","$");

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

        String testResultRecord = GenericData.readJsonValueFromFile("test-results_payload_10711.json", "$");

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

    @Steps
    VehicleTechnicalRecordsSteps vehicleTechnicalRecordsSteps;

    @TestData
    public static Collection<Object[]> testData(){
        return Arrays.asList(new Object[][]{
                {"fail","cancelled","Annual With Certificate"},
                {"fail","cancelled","Annual No Certificate"},
                {"pass","cancelled","Annual With Certificate"},
                {"pass","cancelled","Annual No Certificate"},
                {"prs","cancelled","Annual With Certificate"},
                {"prs","cancelled","Annual No Certificate"},
                {"abandoned","cancelled","Annual With Certificate"},
                {"abandoned","cancelled","Annual No Certificate"},
        });
    }

    private String testResult;
    private String testStatus;
    private String testTypeClassification;

    public PutTestResultsCertificateNotGenerated(String testResult, String testStatus, String testTypeClassification) {
        this.testResult = testResult;
        this.testStatus = testStatus;
        this.testTypeClassification = testTypeClassification;

    }

    @WithTag("annual_certificates")
    @Title("CVSB-10711 - AC1: PUT: Trigger certificate generation process")
    @Test
    public void testResultsPut() {

       String putRequestBody = GenericData.readJsonValueFromFile("test-results_put_payload_10711.json","$");

        JsonPathAlteration alterationSystemNumberPutTestResults = new JsonPathAlteration("$.testResult.systemNumber", randomSystemNumber, "", "REPLACE");
        JsonPathAlteration alterationVinPutTestResults = new JsonPathAlteration("$.testResult.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationTestResultIdPutTestResults = new JsonPathAlteration("$.testResult.testResultId", randomTestResultId, "", "REPLACE");


        JsonPathAlteration alterationPutTestResult = new JsonPathAlteration("$.testResult.testTypes[0].testResult", testResult, "", "REPLACE");
        JsonPathAlteration alterationPutTestStatus = new JsonPathAlteration("$.testResult.testStatus", testStatus, "", "REPLACE");
        JsonPathAlteration alterationPutTestTypeClassification = new JsonPathAlteration("$.testResult.testTypes[0].testTypeClassification", testTypeClassification, "", "REPLACE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterationsPutTestResults = new ArrayList<>(Arrays.asList(
                alterationSystemNumberPutTestResults,
                alterationVinPutTestResults,
                alterationTestResultIdPutTestResults,
                alterationPutTestResult,
                alterationPutTestStatus,
                alterationPutTestTypeClassification
        ));

        testResultsSteps.putTestResultsWithAlterations(randomSystemNumber,putRequestBody,alterationsPutTestResults);
        testResultsSteps.statusCodeShouldBe(HttpStatus.SC_OK);

        testResultsSteps.getTestResults(randomSystemNumber, TestResultsStatus.CANCELED);
        testResultsSteps.statusCodeShouldBe(HttpStatus.SC_OK);
        String testNumber = testResultsSteps.getTestNumber();

        // verify that the certificate is created in the S3 bucket
        testResultsSteps.validateCertificateIsNotGenerated(testNumber,randomVin);
    }
}
