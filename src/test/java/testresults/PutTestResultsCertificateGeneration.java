package testresults;

import data.GenericData;

import io.restassured.http.ContentType;
import junit.framework.TestCase;
import io.restassured.response.Response;
import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.junit.annotations.TestData;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import steps.*;
import util.BasePathFilter;
import util.JsonPathAlteration;
import org.apache.http.HttpStatus;

import java.text.SimpleDateFormat;
import java.util.*;

import static io.restassured.RestAssured.given;
import static util.WriterReader.saveUtils;
import org.junit.Assert;


@RunWith(SerenityParameterizedRunner.class)
public class PutTestResultsCertificateGeneration extends TestCase {

    static String randomVin;
    static String randomSystemNumber;
    static String randomTestResultId;



    @BeforeClass
    public static void createRecord() {

        Date date  = new Date();

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
        JsonPathAlteration alterationTestStartTimestamp = new JsonPathAlteration("$.testStartTimestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 1)), "", "REPLACE");
        JsonPathAlteration alterationTestEndTimestamp = new JsonPathAlteration("$.testEndTimestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 4)), "", "REPLACE");
        JsonPathAlteration alterationTestTypeStartTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeStartTimestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 2)), "", "REPLACE");
        JsonPathAlteration alterationTestTypeEndTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeEndTimestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 3)), "", "REPLACE");


        List<JsonPathAlteration> alterationsTestResults = new ArrayList<>(Arrays.asList(alterationSystemNumberTestResults,
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

    @Steps
    VehicleTechnicalRecordsSteps vehicleTechnicalRecordsSteps;

    @TestData
    public static Collection<Object[]> testData(){
        return Arrays.asList(new Object[][]{
                {"pass","submitted","Annual With Certificate"},
                {"fail","submitted","Annual With Certificate"},
                {"prs","submitted","Annual With Certificate"},
                {"abandoned","submitted","Annual With Certificate"},
                {"abandoned","submitted","Annual No Certificate"},
        });
    }

    private String testResult;
    private String testStatus;
    private String testTypeClassification;

    public PutTestResultsCertificateGeneration(String testResult, String testStatus, String testTypeClassification) {
        this.testResult = testResult;
        this.testStatus = testStatus;
        this.testTypeClassification = testTypeClassification;

    }

    @WithTag("annual_certificates")
    @Title("CVSB-10711 - AC1: PUT: Trigger certificate generation process")
    @Test
    public void testResultsPut() {

        Date date  = new Date();

       String putRequestBody = GenericData.readJsonValueFromFile("test-results_put_payload_10711.json","$");

        JsonPathAlteration alterationSystemNumberPutTestResults = new JsonPathAlteration("$.testResult.systemNumber", randomSystemNumber, "", "REPLACE");
        JsonPathAlteration alterationVinPutTestResults = new JsonPathAlteration("$.testResult.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationTestResultIdPutTestResults = new JsonPathAlteration("$.testResult.testResultId", randomTestResultId, "", "REPLACE");
        JsonPathAlteration alterationPutTestResult = new JsonPathAlteration("$.testResult.testTypes[0].testResult", testResult, "", "REPLACE");
        JsonPathAlteration alterationPutTestStatus = new JsonPathAlteration("$.testResult.testStatus", testStatus, "", "REPLACE");
        JsonPathAlteration alterationPutTestTypeClassification = new JsonPathAlteration("$.testResult.testTypes[0].testTypeClassification", testTypeClassification, "", "REPLACE");
        JsonPathAlteration alterationPutTestStartTimestamp = new JsonPathAlteration("$.testResult.testStartTimestamp",new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 1)) , "", "REPLACE");
        JsonPathAlteration alterationPutTestEndTimestamp = new JsonPathAlteration("$.testResult.testEndTimestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 4)), "", "REPLACE");
        JsonPathAlteration alterationPutTestTypeStartTimestamp = new JsonPathAlteration("$.testResult.testTypes[0].testTypeStartTimestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 2)), "", "REPLACE");
        JsonPathAlteration alterationPutTestTypeEndTimestamp = new JsonPathAlteration("$.testResult.testTypes[0].testTypeEndTimestamp", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 3)), "", "REPLACE");




        // Collate the list of alterations.
        List<JsonPathAlteration> alterationsPutTestResults = new ArrayList<>(Arrays.asList(
                alterationSystemNumberPutTestResults,
                alterationVinPutTestResults,
                alterationTestResultIdPutTestResults,
                alterationPutTestResult,
                alterationPutTestStatus,
                alterationPutTestTypeClassification,
                alterationPutTestStartTimestamp,
                alterationPutTestEndTimestamp,
                alterationPutTestTypeStartTimestamp,
                alterationPutTestTypeEndTimestamp
        ));

        testResultsSteps.putTestResultsWithAlterations(randomSystemNumber, putRequestBody, alterationsPutTestResults);
        testResultsSteps.statusCodeShouldBe(HttpStatus.SC_OK);

        testResultsSteps.getTestResults(randomSystemNumber);
        testResultsSteps.statusCodeShouldBe(HttpStatus.SC_OK);
        String testNumber = testResultsSteps.getTestNumber();

        System.out.println("TestNumber is " +testNumber);

        // verify that the certificate is created in the S3 bucket
        testResultsSteps.validateCertificateIsGenerated(testNumber,randomVin);
    }
}
