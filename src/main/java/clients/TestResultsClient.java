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
import model.testresults.TestResultsGet;
import model.testresults.TestTypesGet;
import util.BasePathFilter;

import java.lang.reflect.Field;

import static io.restassured.RestAssured.given;
import static util.WriterReader.saveUtils;

public class TestResultsClient {

    public Response getTestResults(String vin) {

        Response response = callGetTestResults(vin);

        if (response.getStatusCode() == 401 || response.getStatusCode() == 403) {
            saveUtils();
            response = callGetTestResults(vin);
        }

        return response;

    }

    public Response getTestResultsWithStatus(String vin, String status) {

        Response response = callFetTestResultsWithStatus(vin, status);

        if (response.getStatusCode() == 401 || response.getStatusCode() == 403) {
            saveUtils();
            response = callFetTestResultsWithStatus(vin, status);
        }

        return response;
    }


    public Response postTestResults(TestResults testResults) {

        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode node = objectMapper.valueToTree(testResults);


        Response response;
        if (testResults != null) {

            for (Field field : TestResultsGet.class.getDeclaredFields()) {
                node.remove(field.getName());
            }

            for (int i = 0; i < node.get("testTypes").size(); i++) {
                for (Field field : TestTypesGet.class.getDeclaredFields()) {
                    JsonNode nodeToUpdate = node.get("testTypes").get(i);
                    if (nodeToUpdate != null) {
                        ((ObjectNode) nodeToUpdate).remove(field.getName());
                    }
                }
            }

            response = callPostTestResults(node);

            if (response.getStatusCode() == 401 || response.getStatusCode() == 403) {
                saveUtils();
                response = callPostTestResults(node);
            }


        } else {
            response = callPostTestResults("{ }");

            if (response.getStatusCode() == 401 || response.getStatusCode() == 403) {
                saveUtils();
                response = callPostTestResults("{ }");
            }
        }

        return response;
    }


    public Response postTestResultsFieldChange(TestResults testResults, String propertyField, String value, ToTypeConvertor toType, TestResultsLevel testResultsLevel) {


        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode node = objectMapper.valueToTree(testResults);

        JsonNode nodeToUpdate;

        for (Field field : TestResultsGet.class.getDeclaredFields()) {
            node.remove(field.getName());
        }

        for (Field field : TestTypesGet.class.getDeclaredFields()) {
            nodeToUpdate = node.get("testTypes").get(0);
            ((ObjectNode) nodeToUpdate).remove(field.getName());
        }

        switch (testResultsLevel) {
            case MAIN_LEVEL:
                nodeToUpdate = node;
                break;
            case VEHICLE_CLASS:
                nodeToUpdate = node.get("vehicleClass");
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
            case EMPTY_ARRAY:
                ((ObjectNode) nodeToUpdate).putArray(propertyField);
                break;
            case MISSING:
                ((ObjectNode) nodeToUpdate).remove(propertyField);
                break;
            default:
                throw new AutomationException("Convert type not supported");

        }


        Response response = callPostTestResults(node);

        if (response.getStatusCode() == 401 || response.getStatusCode() == 403) {
            saveUtils();
            response = callPostTestResults(node);
        }

        return response;
    }


    public Response getTestResultsFromDateTime(String vin, String fromDateTime) {

        Response response = callGetTestResultsFromDateTime(vin, fromDateTime);

        if (response.getStatusCode() == 401 || response.getStatusCode() == 403) {
            saveUtils();
            response = callGetTestResultsFromDateTime(vin, fromDateTime);
        }

        return response;
    }

    public Response getTestResultsFromDateTime(String vin, String fromDateTime, String status) {

        Response response = callGetTestResultsFromDateTime(vin, fromDateTime, status);

        if (response.getStatusCode() == 401 || response.getStatusCode() == 403) {
            saveUtils();
            response = callGetTestResultsFromDateTime(vin, fromDateTime, status);
        }

        return response;
    }


    public Response getTestResultsToDateTime(String vin, String toDateTime) {

        Response response = callGetTestResultsToDateTime(vin, toDateTime);

        if (response.getStatusCode() == 401 || response.getStatusCode() == 403) {
            saveUtils();
            response = callGetTestResultsToDateTime(vin, toDateTime);
        }

        return response;
    }

    public Response getTestResultsToDateTime(String vin, String toDateTime, String status) {

        Response response = callGetTestResultsToDateTime(vin, toDateTime, status);

        if (response.getStatusCode() == 401 || response.getStatusCode() == 403) {
            saveUtils();
            response = callGetTestResultsToDateTime(vin, toDateTime, status);
        }

        return response;
    }

    public Response getTestResultsBetweenDate(String vin, String fromDateTime, String toDateTime) {

        Response response = callGetTestResultsBetweenDate(vin, fromDateTime, toDateTime);

        if (response.getStatusCode() == 401 || response.getStatusCode() == 403) {
            saveUtils();
            response = callGetTestResultsBetweenDate(vin, fromDateTime, toDateTime);
        }

        return response;
    }

    public Response getTestResultsBetweenDate(String vin, String fromDateTime, String toDateTime, String status) {

        Response response = callGetTestResultsBetweenDate(vin, fromDateTime, toDateTime, status);

        if (response.getStatusCode() == 401 || response.getStatusCode() == 403) {
            saveUtils();
            response = callGetTestResultsBetweenDate(vin, fromDateTime, toDateTime, status);
        }

        return response;
    }


    public Response callPostTestResults(Object object) {

        Response response = given()
                .filters(new BasePathFilter())
                .contentType(ContentType.JSON)
                .body(object)
//                .log().all()
                .post("/test-results");

        return response;
    }


    public Response callGetTestResults(String vin) {

        Response response = given()
//                .log().all()
                .filters(new BasePathFilter())
                .contentType(ContentType.JSON)
                .pathParam("vin", vin)
                .get("/test-results/{vin}");

        return response;
    }

    private Response callFetTestResultsWithStatus(String vin, String status) {

        Response response = given().filters(new BasePathFilter())
                .contentType(ContentType.JSON)
                .pathParam("vin", vin)
                .queryParam("status", status)
//                .log().all()
                .get("/test-results/{vin}");


        return response;
    }

    private Response callGetTestResultsFromDateTime(String vin, String fromDateTime) {

        Response response = given().filters(new BasePathFilter())
                .contentType(ContentType.JSON)
                .pathParam("vin", vin)
                .queryParam("fromDateTime", fromDateTime)
                .get("/test-results/{vin}");

        return response;
    }

    private Response callGetTestResultsFromDateTime(String vin, String fromDateTime, String status) {

        Response response = given().filters(new BasePathFilter())
                .contentType(ContentType.JSON)
                .pathParam("vin", vin)
                .queryParam("status", status)
                .queryParam("fromDateTime", fromDateTime)
                .get("/test-results/{vin}");

        return response;
    }


    private Response callGetTestResultsToDateTime(String vin, String toDateTime) {

        Response response = given().filters(new BasePathFilter())
                .contentType(ContentType.JSON)
                .pathParam("vin", vin)
                .queryParam("toDateTime", toDateTime)
                .get("/test-results/{vin}");

        return response;
    }

    private Response callGetTestResultsToDateTime(String vin, String toDateTime, String status) {

        Response response = given().filters(new BasePathFilter())
                .contentType(ContentType.JSON)
                .pathParam("vin", vin)
                .queryParam("status", status)
                .queryParam("toDateTime", toDateTime)
                .get("/test-results/{vin}");

        return response;
    }

    private Response callGetTestResultsBetweenDate(String vin, String fromDateTime, String toDateTime) {

        Response response = given().filters(new BasePathFilter())
                .contentType(ContentType.JSON)
                .pathParam("vin", vin)
                .queryParam("fromDateTime", fromDateTime)
                .queryParam("toDateTime", toDateTime)
                .get("/test-results/{vin}");

        return response;
    }

    private Response callGetTestResultsBetweenDate(String vin, String fromDateTime, String toDateTime, String status) {

        Response response = given().filters(new BasePathFilter())
                .contentType(ContentType.JSON)
                .pathParam("vin", vin)
                .queryParam("status", status)
                .queryParam("fromDateTime", fromDateTime)
                .queryParam("toDateTime", toDateTime)
//                .log().all()
                .get("/test-results/{vin}");

        return response;
    }

    public Response postTestResultsTestTypeFieldsRemoved(TestResults testResults, String[] removeFields) {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode node = objectMapper.valueToTree(testResults);

        for(String removeField: removeFields) {
            ((ObjectNode)node.get("testTypes").get(0)).remove(removeField);
                System.out.println(removeField + " removed");
            }

        Response response = callPostTestResults(node);

        if (response.getStatusCode() == 401 || response.getStatusCode() == 403) {
            saveUtils();
            response = callPostTestResults(node);
        }

        return response;
    }

    public Response postTestResults(ObjectNode payload) {
        Response response = callPostTestResults(payload);

        if (response.getStatusCode() == 401 || response.getStatusCode() == 403) {
            saveUtils();
            response = callPostTestResults(payload);
        }
        return response;
    }
}
