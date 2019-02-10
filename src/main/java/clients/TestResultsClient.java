package clients;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import model.testresults.TestResults;
import util.BasePathFilter;

import static io.restassured.RestAssured.given;

public class TestResultsClient {

    public Response getTestResults(String vin) {

        Response response = given().filters(new BasePathFilter())
                .contentType(ContentType.JSON)
                .pathParam("vin", vin)
                .get("/test-results/{vin}");

        return response;
    }

    public Response getVehicleTechnicalRecords(String vin, String status) {

        Response response = given().filters(new BasePathFilter())
                .contentType(ContentType.JSON)
                .pathParam("vin", vin)
                .queryParam("status", status)
                .get("/test-results/{vin}");

        return response;
    }


    public Response postTestResults(TestResults testResults) {

        Response response = given().filters(new BasePathFilter())
                .contentType(ContentType.JSON)
                .body(testResults)
                .post("/test-results");

        response.prettyPrint();
        return response;
    }

    public Response getTestResultsFromDateTime(String vin, String fromDateTime) {

        Response response = given().filters(new BasePathFilter())
                .contentType(ContentType.JSON)
                .pathParam("vin", vin)
                .queryParam("fromDateTime", fromDateTime)
                .get("/test-results/{vin}");

        return response;
    }

    public Response getTestResultsFromDateTime(String vin, String fromDateTime, String status) {

        Response response = given().filters(new BasePathFilter())
                .contentType(ContentType.JSON)
                .pathParam("vin", vin)
                .queryParam("status", status)
                .queryParam("fromDateTime", fromDateTime)
                .get("/test-results/{vin}");

        return response;
    }


    public Response getTestResultsToDateTime(String vin, String toDateTime) {

        Response response = given().filters(new BasePathFilter())
                .contentType(ContentType.JSON)
                .pathParam("vin", vin)
                .queryParam("toDateTime", toDateTime)
                .get("/test-results/{vin}");

        return response;
    }

    public Response getTestResultsToDateTime(String vin, String toDateTime, String status) {

        Response response = given().filters(new BasePathFilter())
                .contentType(ContentType.JSON)
                .pathParam("vin", vin)
                .queryParam("status", status)
                .queryParam("toDateTime", toDateTime)
                .get("/test-results/{vin}");

        return response;
    }

    public Response getTestResultsBetweenDate(String vin, String fromDateTime, String toDateTime) {

        Response response = given().filters(new BasePathFilter())
                .contentType(ContentType.JSON)
                .pathParam("vin", vin)
                .queryParam("fromDateTime", fromDateTime)
                .queryParam("toDateTime", toDateTime)
                .get("/test-results/{vin}");

        return response;
    }

    public Response getTestResultsBetweenDate(String vin, String fromDateTime, String toDateTime, String status) {

        Response response = given().filters(new BasePathFilter())
                .contentType(ContentType.JSON)
                .pathParam("vin", vin)
                .queryParam("status", status)
                .queryParam("fromDateTime", fromDateTime)
                .queryParam("toDateTime", toDateTime)
                .get("/test-results/{vin}");

        return response;
    }

}
