package clients;

import clients.util.ToTypeConvertor;
import clients.util.testresult.TestResultsLevel;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import exceptions.AutomationException;
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

        Response response;
        if (testResults != null) {
            response = given().filters(new BasePathFilter())
                    .contentType(ContentType.JSON)
                    .body(testResults)
                    .post("/test-results");

        } else {
            response = given().filters(new BasePathFilter())
                    .contentType(ContentType.JSON)
                    .body("{ }")
                    .post("/test-results");
        }


        response.prettyPrint();
        return response;
    }


    public Response postTestResultsFieldChange(TestResults testResults, String propertyField, String value, ToTypeConvertor toType, TestResultsLevel testResultsLevel) {


        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode node = objectMapper.valueToTree(testResults);

        JsonNode nodeToUpdate;


        switch (testResultsLevel) {
            case MAIN_LEVEL:
                nodeToUpdate = node;
                break;
            case TEST_TYPES:
                nodeToUpdate = node.get("testTypes").get(0);
                break;
            case DEFECTS:
                nodeToUpdate = node.get("testTypes").get(0).get("defects").get(0);
                break;
            case ADDITIONAL_INFORMATION:
                nodeToUpdate = node.get("testTypes").get(0).get("defects").get(0).get("additionalInformation");
                break;
            case LOCATION:
                nodeToUpdate = node.get("testTypes").get(0).get("defects").get(0).get("additionalInformation").get("location");
                break;
            case ITEM:
                nodeToUpdate = node.get("testTypes").get(0).get("defects").get(0).get("item");
                break;
            case DEFICIENCY:
                nodeToUpdate = node.get("testTypes").get(0).get("defects").get(0).get("item").get("deficiency");
                break;
            default:
                throw new AutomationException("Level not recognized");
        }



        switch (toType) {
            case INTEGER:
                ((ObjectNode) nodeToUpdate).put(propertyField, Integer.valueOf(value));
                break;
            case STRING:
                ((ObjectNode) nodeToUpdate).put(propertyField, value);
                break;
            case INTEGER_ARRAY:
                ((ObjectNode) nodeToUpdate).putArray(propertyField).add(Integer.valueOf(value));
                break;
            case NULL:
                ((ObjectNode) nodeToUpdate).putNull(propertyField);
                break;
            case NULL_ARRAY:
                ((ObjectNode) nodeToUpdate).putArray(propertyField).addNull();
                break;
            case MISSING:
                ((ObjectNode) nodeToUpdate).remove(propertyField);
                break;
            default:
                throw new AutomationException("Convert type not supported");

        }

        Response response = given().filters(new BasePathFilter())
                .contentType(ContentType.JSON)
                .body(node)
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
