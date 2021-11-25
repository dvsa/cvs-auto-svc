package clients;

import clients.model.TestTypes;
import clients.util.ToTypeConvertor;
import clients.util.testresult.TestResultsLevel;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import data.GenericData;
import exceptions.AutomationException;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import net.minidev.json.JSONArray;
import org.apache.http.HttpStatus;
import model.testresults.TestResults;
import model.testresults.TestResultsGet;
import model.testresults.TestTypesGet;
import org.apache.commons.exec.environment.EnvironmentUtils;
import org.junit.Assert;
import util.BasePathFilter;
import util.EnvironmentType;
import util.JsonPathAlteration;
import util.TypeLoader;

import java.io.IOException;
import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static io.restassured.RestAssured.given;
import static util.WriterReader.saveUtils;

public class TestResultsClient {

    public Response getTestResults(String systemNumber) {

        Response response = callGetTestResults(systemNumber);

        if (response.getStatusCode() == 401 || response.getStatusCode() == 403) {
            saveUtils();
            response = callGetTestResults(systemNumber);
        }

        return response;

    }

    public Response getTestResultsNo404(String systemNumber, int numberOfRetries) {

        Response response = callGetTestResults(systemNumber);

        if (response.getStatusCode() == 401 || response.getStatusCode() == 403) {
            saveUtils();
            response = callGetTestResults(systemNumber);
        }
        int i = 0;
        while (i < numberOfRetries) {
            if (response.getStatusCode() == 404) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                response = callGetTestResults(systemNumber);
                i++;
            }
            else if (response.getStatusCode() == 200) {
                i = numberOfRetries;
            }

        }
        return response;

    }

    public Response getTestResultsSysNumber(String systemNumber) {

        Response response = callGetTestResults(systemNumber);

        if (response.getStatusCode() == 401 || response.getStatusCode() == 403) {
            saveUtils();
            response = callGetTestResults(systemNumber);
        }
        return response;
    }

    public Response getTestResultsWithStatus(String systemNumber, String status) {

        Response response = callGetTestResultsWithStatus(systemNumber, status);

        if (response.getStatusCode() == 401 || response.getStatusCode() == 403) {
            saveUtils();
            response = callGetTestResultsWithStatus(systemNumber, status);
        }

        return response;
    }

    public Response getTestResultsWithStatusAndSysNumber(String systemNumber, String status) {

        Response response = callGetTestResultsWithStatusAndSysNumber(systemNumber, status);

        if (response.getStatusCode() == 401 || response.getStatusCode() == 403) {
            saveUtils();
            response = callGetTestResultsWithStatusAndSysNumber(systemNumber, status);
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


    public Response getTestResultsFromDateTime(String systemNumber, String fromDateTime) {

        Response response = callGetTestResultsFromDateTime(systemNumber, fromDateTime);

        if (response.getStatusCode() == 401 || response.getStatusCode() == 403) {
            saveUtils();
            response = callGetTestResultsFromDateTime(systemNumber, fromDateTime);
        }

        return response;
    }

    public Response getTestResultsFromDateTimeAndSysNum(String systemNumber, String fromDateTime) {

        Response response = callGetTestResultsFromSystemNumberDateTime(systemNumber, fromDateTime);

        if (response.getStatusCode() == 401 || response.getStatusCode() == 403) {
            saveUtils();
            response = callGetTestResultsFromDateTime(systemNumber, fromDateTime);
        }

        return response;
    }




    public Response getTestResultsFromDateTime(String systemNumber, String fromDateTime, String status) {

        Response response = callGetTestResultsFromDateTime(systemNumber, fromDateTime, status);

        if (response.getStatusCode() == 401 || response.getStatusCode() == 403) {
            saveUtils();
            response = callGetTestResultsFromDateTime(systemNumber, fromDateTime, status);
        }

        return response;
    }


    public Response getTestResultsToDateTime(String systemNumber, String toDateTime) {

        Response response = callGetTestResultsToDateTime(systemNumber, toDateTime);

        if (response.getStatusCode() == 401 || response.getStatusCode() == 403) {
            saveUtils();
            response = callGetTestResultsToDateTime(systemNumber, toDateTime);
        }

        return response;
    }

    public Response getTestResultsToDateTime(String systemNumber, String toDateTime, String status) {

        Response response = callGetTestResultsToDateTime(systemNumber, toDateTime, status);

        if (response.getStatusCode() == 401 || response.getStatusCode() == 403) {
            saveUtils();
            response = callGetTestResultsToDateTime(systemNumber, toDateTime, status);
        }

        return response;
    }

    public Response getTestResultsBetweenDate(String systemNumber, String fromDateTime, String toDateTime) {

        Response response = callGetTestResultsBetweenDate(systemNumber, fromDateTime, toDateTime);

        if (response.getStatusCode() == 401 || response.getStatusCode() == 403) {
            saveUtils();
            response = callGetTestResultsBetweenDate(systemNumber, fromDateTime, toDateTime);
        }

        return response;
    }

    public Response getTestResultsBetweenDate(String systemNumber, String fromDateTime, String toDateTime, String status) {

        Response response = callGetTestResultsBetweenDate(systemNumber, fromDateTime, toDateTime, status);

        if (response.getStatusCode() == 401 || response.getStatusCode() == 403) {
            saveUtils();
            response = callGetTestResultsBetweenDate(systemNumber, fromDateTime, toDateTime, status);
        }

        return response;
    }


    public Response callPostTestResults(Object object) {

        Response response = given()
                .filters(new BasePathFilter())
                .contentType(ContentType.JSON)
                .body(object)
//                .log().all()
                .log().method().log().uri().log().body()
                .post("/test-results");

        return response;
    }


    public Response callGetTestResults(String systemNumber) {

        Response response = given()
                .filters(new BasePathFilter())
                .contentType(ContentType.JSON)
                .pathParam("systemNumber", systemNumber)
//                .log().all()
                .log().method().log().uri().log().body()
                .get("/test-results/{systemNumber}");

        return response;
    }

    public Response callGetTestResultsSysNum(String systemNumber) {

        Response response = given()
                .filters(new BasePathFilter())
                .contentType(ContentType.JSON)
                .pathParam("systemNumber", systemNumber)
//                .log().all()
                .log().method().log().uri().log().body()
                .get("/test-results/{systemNumber}");

        return response;
    }

    private Response callGetTestResultsWithStatus(String systemNumber, String status) {

        Response response = given().filters(new BasePathFilter())
                .contentType(ContentType.JSON)
                .pathParam("systemNumber", systemNumber)
                .queryParam("status", status)
//                .log().all()
                .log().method().log().uri().log().body()
                .get("/test-results/{systemNumber}");


        return response;
    }


    private Response callGetTestResultsWithStatusAndSysNumber(String systemNumber, String status) {

        Response response = given().filters(new BasePathFilter())
                .contentType(ContentType.JSON)
                .pathParam("systemNumber", systemNumber)
                .queryParam("status", status)
//                .log().all()
                .log().method().log().uri().log().body()
                .get("/test-results/{systemNumber}");


        return response;
    }

    private Response callGetTestResultsFromDateTime(String systemNumber, String fromDateTime) {

        Response response = given().filters(new BasePathFilter())
                .contentType(ContentType.JSON)
                .pathParam("systemNumber", systemNumber)
                .queryParam("fromDateTime", fromDateTime)
                .get("/test-results/{systemNumber}");

        return response;
    }

    private Response callGetTestResultsFromSystemNumberDateTime(String systemNumber, String fromDateTime) {

        Response response = given().filters(new BasePathFilter())
                .contentType(ContentType.JSON)
                .pathParam("systemNumber", systemNumber)
                .queryParam("fromDateTime", fromDateTime)
                .get("/test-results/{systemNumber}");

        return response;
    }

    private Response callGetTestResultsFromDateTime(String systemNumber, String fromDateTime, String status) {

        Response response = given().filters(new BasePathFilter())
                .contentType(ContentType.JSON)
                .pathParam("systemNumber", systemNumber)
                .queryParam("status", status)
                .queryParam("fromDateTime", fromDateTime)
                .get("/test-results/{systemNumber}");

        return response;
    }


    private Response callGetTestResultsToDateTime(String systemNumber, String toDateTime) {

        Response response = given().filters(new BasePathFilter())
                .contentType(ContentType.JSON)
                .pathParam("systemNumber", systemNumber)
                .queryParam("toDateTime", toDateTime)
                .get("/test-results/{systemNumber}");

        return response;
    }

    private Response callGetTestResultsToDateTime(String systemNumber, String toDateTime, String status) {

        Response response = given().filters(new BasePathFilter())
                .contentType(ContentType.JSON)
                .pathParam("systemNumber", systemNumber)
                .queryParam("status", status)
                .queryParam("toDateTime", toDateTime)
                .get("/test-results/{systemNumber}");

        return response;
    }

    private Response callGetTestResultsBetweenDate(String systemNumber, String fromDateTime, String toDateTime) {

        Response response = given().filters(new BasePathFilter())
                .contentType(ContentType.JSON)
                .pathParam("systemNumber", systemNumber)
                .queryParam("fromDateTime", fromDateTime)
                .queryParam("toDateTime", toDateTime)
                .log().method().log().uri().log().body()
                .get("/test-results/{systemNumber}");

        return response;
    }

    private Response callGetTestResultsBetweenDate(String systemNumber, String fromDateTime, String toDateTime, String status) {

        Response response = given().filters(new BasePathFilter())
                .contentType(ContentType.JSON)
                .pathParam("systemNumber", systemNumber)
                .queryParam("status", status)
                .queryParam("fromDateTime", fromDateTime)
                .queryParam("toDateTime", toDateTime)
//                .log().all()
                .log().method().log().uri().log().body()
                .get("/test-results/{systemNumber}");

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

    private Response callPostVehicleTestResultsWithAlterations(String body, List<JsonPathAlteration> alterations) {

        //the only actions accepted are ADD_FIELD, ADD_VALUE, DELETE and REPLACE
        String alteredBody = GenericData.applyJsonAlterations(body, alterations);

        Response response = given().filters(new BasePathFilter())
                .contentType(ContentType.JSON)
                .body(alteredBody)
//                .log().all()
                .log().method().log().uri().log().body()
                .post("/test-results");

        return response;
    }

    public Response callPostVehicleTestResultsWithNoAuthorization(String body) {

        Response response = given().filters(new BasePathFilter())
                .contentType(ContentType.JSON)
//                .log().all()
                .log().method().log().uri().log().body()
                .post("/test-results");

        return response;
    }


    public Response postVehicleTestResultsWithAlterations(String body, List<JsonPathAlteration> alterations) {
        Response response = callPostVehicleTestResultsWithAlterations(body, alterations);

        if (response.getStatusCode() == 401 || response.getStatusCode() == 403) {
            saveUtils();
            response = callPostVehicleTestResultsWithAlterations(body, alterations);
        }

        return response;
    }

    public Response getTestResultsWithVersion(String systemNumber, String testVersion , String testResultId) {

        Response response = callGetTestResultsWithVersion(systemNumber, testVersion, testResultId);

        if (response.getStatusCode() == HttpStatus.SC_UNAUTHORIZED || response.getStatusCode() == HttpStatus.SC_FORBIDDEN) {
            saveUtils();
            response = callGetTestResultsWithVersion(systemNumber, testVersion, testResultId);
        }
        return response;
    }

    private Response callGetTestResultsWithVersion(String systemNumber, String testVersion , String testResultId) {

        Response response = given().filters(new BasePathFilter())
                .contentType(ContentType.JSON)
                .pathParam("systemNumber", systemNumber)
                .queryParam("version", testVersion)
                .queryParam("testResultId", testResultId)
                .log().method().log().uri().log().body()
                .get("/test-results/{systemNumber}");


        return response;
    }

    public Response getTestResultsWithVersionAndStatus(String systemNumber, String testVersion , String testResultId , String status) {

        Response response = callGetTestResultsWithVersionAndStatus(systemNumber, testVersion, testResultId , status);

        if (response.getStatusCode() == HttpStatus.SC_UNAUTHORIZED || response.getStatusCode() == HttpStatus.SC_FORBIDDEN) {
            saveUtils();
            response = callGetTestResultsWithVersionAndStatus(systemNumber, testVersion, testResultId, status);
        }
        return response;
    }

    private Response callGetTestResultsWithVersionAndStatus(String systemNumber, String testVersion , String testResultId , String status) {

        Response response = given().filters(new BasePathFilter())
                .contentType(ContentType.JSON)
                .pathParam("systemNumber", systemNumber)
                .queryParam("version", testVersion)
                .queryParam("status", status)
                .queryParam("testResultId", testResultId)
                .log().method().log().uri().log().body()
                .get("/test-results/{systemNumber}");


        return response;
    }

    public Response putTestResultsWithAlterations(String systemNumber, String requestBody, List<JsonPathAlteration> alterations) {
        Response response = callPutTestResultsWithAlterations(systemNumber, requestBody, alterations);

        if (response.getStatusCode() == HttpStatus.SC_UNAUTHORIZED || response.getStatusCode() == HttpStatus.SC_FORBIDDEN) {
            saveUtils();
            response = callPutTestResultsWithAlterations(systemNumber, requestBody, alterations);
        }

        return response;
    }

    public Response putTestResultsWithAlterationsNo400(String systemNumber, String requestBody, List<JsonPathAlteration> alterations) {
        Response response = callPutTestResultsWithAlterations(systemNumber, requestBody, alterations);
        ArrayList<String> notApplicableFields;

        if (response.getStatusCode() == HttpStatus.SC_UNAUTHORIZED || response.getStatusCode() == HttpStatus.SC_FORBIDDEN) {
            saveUtils();
            response = callPutTestResultsWithAlterations(systemNumber, requestBody, alterations);
        }
        int i =0;
        while ((response.getStatusCode() == HttpStatus.SC_BAD_REQUEST) && (i < 3)) {
            DocumentContext jsonContext = JsonPath.parse(response.asString());
            HashMap errorMessage = jsonContext.read("$");
            if (errorMessage.containsKey("errors")) {
                Object[] errors = ((JSONArray) errorMessage.get("errors")).toArray();
                if ((errors[0] != null) && (errors[0] instanceof String)) {
                    notApplicableFields = GenericData.extractArrayListStringFromJsonString(response.asString(), "$.errors");
                    for (String error : notApplicableFields) {
                        if (error.contains("is not allowed")) {
                            String fieldName = error.split("\"")[1];
                            JsonPathAlteration alterationDeleteTestResultField = new JsonPathAlteration(
                                    "$.testResult." + fieldName, "", "", "DELETE");
                            alterations.add(alterationDeleteTestResultField);
                            JsonPathAlteration alterationDeleteTestTypeField = new JsonPathAlteration(
                                    "$.testResult.testTypes[0]." + fieldName, "", "", "DELETE");
                            alterations.add(alterationDeleteTestTypeField);
                        }
                        if (error.contains("is required")) {
                            String fieldName = error.split("\"")[1];
                            if (fieldName.contentEquals("trailerId")) {
                                JsonPathAlteration alterationAddTestResultField = new JsonPathAlteration(
                                        "$.testResult", GenericData.generateRandomTrailerId(), fieldName, "ADD_FIELD");
                                alterations.add(alterationAddTestResultField);
                            }
                        }
                    }
                }
            }
            response = callPutTestResultsWithAlterations(systemNumber, requestBody, alterations);
            i++;
        }

        if (response.getStatusCode() == HttpStatus.SC_UNAUTHORIZED || response.getStatusCode() == HttpStatus.SC_FORBIDDEN) {
            saveUtils();
            response = callPutTestResultsWithAlterations(systemNumber, requestBody, alterations);
        }
        return response;
    }

    private Response callPutTestResultsWithAlterations(String systemNumber, String requestBody, List<JsonPathAlteration> alterations) {
        //the only actions accepted are ADD_FIELD, ADD_VALUE, DELETE and REPLACE
        String alteredBody = GenericData.applyJsonAlterations(requestBody, alterations);

        Response response = given().filters(new BasePathFilter())
                .contentType(ContentType.JSON)
                .body(alteredBody)
                .pathParam("systemNumber", systemNumber)
                .log().method().log().uri().log().body()
                .put("/test-results/{systemNumber}");
        return response;
    }

    public String getOutlookEmailAddress() {
        EnvironmentType envType = TypeLoader.getType();
        String emailAddress = "";
        switch (envType) {
            case CI_DEVELOP:
                emailAddress = System.getProperty("EMAIL_USERNAME");
                break;
            case LOCAL:
                try {
                    Properties properties = new Properties();
                    properties.load(Objects.requireNonNull(EnvironmentUtils.class.getClassLoader().getResourceAsStream("conf/environment.properties")));
                    emailAddress = properties.getProperty("microsoftonline.username");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            default:
                throw new AutomationException("Environment configuration not found");
        }
        return emailAddress;
    }

    public String createTestRecord(String testStatus, String testResult, String testCode, boolean withWithoutDefects,
                                   Map<String, Object> testResultAttributes) {

        List<JsonPathAlteration> testResultAlterations = new ArrayList<>();

        String testResultPayloadFile = "test-results_" + testResultAttributes.get("vehicleType").toString() + ".json";


        for (TestTypes testType : TestTypes.values()) {
            if (testType.getTestCode().contentEquals(testCode.toLowerCase())) {
                String testTypeId = testType.getId();
                // create test result alteration to change testTypeId
                JsonPathAlteration alterationTestTypeId =
                        new JsonPathAlteration("$.testTypes[0].testTypeId", testTypeId,"","REPLACE");
                testResultAlterations.add(alterationTestTypeId);
                String testTypeName = GenericData.readJsonValueFromFile("test-type.json", "$..[?(@.id =='" +
                        testTypeId + "')].testTypeName");
                // create test result alteration to change testTypeName
                JsonPathAlteration alterationTestTypeName =
                        new JsonPathAlteration("$.testTypes[0].testTypeName", testTypeName,"","REPLACE");
                testResultAlterations.add(alterationTestTypeName);
                String testName = GenericData.readJsonValueFromFile("test-type.json", "$..[?(@.id =='" +
                        testTypeId + "')].name");
                // create test result alteration to change testName
                JsonPathAlteration alterationTestName =
                        new JsonPathAlteration("$.testTypes[0].name", testName,"","REPLACE");
                testResultAlterations.add(alterationTestName);
                break;
            }
        }

        // CREATE TEST RESULT RECORD
        String postTestResultBody = GenericData.readJsonValueFromFile(testResultPayloadFile,"$");
        String testResultId = UUID.randomUUID().toString();
        // create alteration to change testStatus
        JsonPathAlteration alterationTestStatus =
                new JsonPathAlteration("$.testStatus", testStatus,"","REPLACE");
        // create alteration to change testResultId
        JsonPathAlteration alterationTestResultId =
                new JsonPathAlteration("$.testResultId", testResultId,"","REPLACE");
        // create alteration to change testResult
        JsonPathAlteration alterationTestResult =
                new JsonPathAlteration("$.testTypes[0].testResult", testResult,"","REPLACE");

        JsonPathAlteration alterationPreparerName =
                new JsonPathAlteration("$.preparerName", "test","","REPLACE");

        LocalDateTime currentDate = LocalDateTime.now(ZoneId.of("Europe/London"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        String endDate = currentDate.plusMinutes(50).format(formatter);
        String expiryDate = currentDate.plusYears(1).format(formatter);
        // create alteration to change testStartTimestamp
        JsonPathAlteration alterationTestStartTimestamp =
                new JsonPathAlteration("$.testStartTimestamp", currentDate.format(formatter),"","REPLACE");
        // create alteration to change testEndTimestamp
        JsonPathAlteration alterationTestEndTimestamp =
                new JsonPathAlteration("$.testEndTimestamp", endDate,"","REPLACE");
        JsonPathAlteration alterationTestTypeStartTimestamp =
                new JsonPathAlteration("$.testTypes[0].testTypeStartTimestamp", currentDate.format(formatter),"","REPLACE");
        // create alteration to change testTypeEndTimestamp
        JsonPathAlteration alterationTestTypeEndTimestamp =
                new JsonPathAlteration("$.testTypes[0].testTypeEndTimestamp", endDate,"","REPLACE");
        // create alteration to change testExpiryDate
        JsonPathAlteration alterationTestExpiryDate =
                new JsonPathAlteration("$.testTypes[0].testExpiryDate", expiryDate,"","REPLACE");
        if (!(testResult.toLowerCase().contentEquals("pass"))) {
            alterationTestExpiryDate = new JsonPathAlteration("$.testTypes[0].testExpiryDate",
                    "","","DELETE");
        }

        // Iterate over the HashMap to add all alteration for common attributes on tech records and test results
        for (Map.Entry<String, Object> entry : testResultAttributes.entrySet()) {
            JsonPathAlteration additionalTestResultAlteration = new JsonPathAlteration("$." + entry.getKey(),
                    entry.getValue(), "", "REPLACE");
            testResultAlterations.add(additionalTestResultAlteration);
        }

        // add remaining alterations to list of test result alterations
        testResultAlterations.addAll(new ArrayList<>(Arrays.asList(alterationTestStatus, alterationTestResultId,
                alterationTestResult, alterationTestExpiryDate, alterationTestStartTimestamp, alterationTestEndTimestamp,
                alterationTestTypeStartTimestamp, alterationTestTypeEndTimestamp, alterationPreparerName)));

        // add alteration for adding
        DocumentContext jsonContext = JsonPath.parse(postTestResultBody);
        HashMap testType = jsonContext.read("$.testTypes[0]");
        if (!(testType.containsKey("certficateNumber"))) {
            if ((testCode.toLowerCase().contentEquals("tiv")) || (testCode.toLowerCase().contentEquals("tit")) ||
                    (testCode.toLowerCase().contentEquals("trv")) || (testCode.toLowerCase().contentEquals("trt")) ||
                    (testCode.toLowerCase().contentEquals("rft")) || (testCode.toLowerCase().contentEquals("ddv")) ||
                    (testCode.toLowerCase().contentEquals("ddt")) || (testCode.toLowerCase().contentEquals("arv")) ||
                    (testCode.toLowerCase().contentEquals("art")) || (testCode.toLowerCase().contentEquals("drv")) ||
                    (testCode.toLowerCase().contentEquals("drt"))) {
                JsonPathAlteration alterationAddCertificateNumber =
                        new JsonPathAlteration("$.testTypes[0]", "12345678", "certificateNumber", "ADD_FIELD");
                testResultAlterations.add(alterationAddCertificateNumber);
            }
        }

        if (testResult.toLowerCase().contentEquals("abandoned")) {
            // create alteration to change reasonForAbandoning
            JsonPathAlteration alterationReasonForAbandoning = new JsonPathAlteration("$.testTypes[0].reasonForAbandoning",
                    "reason for abandoning","","REPLACE");
            // create alteration to change additionalCommentsForAbandon
            JsonPathAlteration alterationAdditionalCommentsForAbandon = new JsonPathAlteration("$.testTypes[0].additionalCommentsForAbandon",
                    "additional comments for abandon","","REPLACE");
            testResultAlterations.add(alterationReasonForAbandoning);
            testResultAlterations.add(alterationAdditionalCommentsForAbandon);
        }
        if (!withWithoutDefects) {
            // create alteration to remove defects
            JsonPathAlteration alterationDefects = new JsonPathAlteration("$.testTypes[0].defects",
                    "[]","","REPLACE");
            testResultAlterations.add(alterationDefects);
        }

        Response responsePostTestResults = postVehicleTestResultsWithAlterations(postTestResultBody, testResultAlterations);
        if (responsePostTestResults.statusCode() != 201) {
            responsePostTestResults.prettyPrint();
            throw new AutomationException("The post test results request was not successful, status code was "
                    + responsePostTestResults.statusCode());
        }
        return testResultId;
    }

    public Response postTestResultsWithAlterations(String token, String requestBody, List<JsonPathAlteration> alterations) {

        Response response = callPostTestResultsWithAlterations(token, requestBody, alterations);

        if (response.getStatusCode() == HttpStatus.SC_UNAUTHORIZED || response.getStatusCode() == HttpStatus.SC_FORBIDDEN) {
            response = callPostTestResultsWithAlterations(token, requestBody, alterations);
        }
        Assert.assertEquals(HttpStatus.SC_CREATED, response.statusCode());
        return response;
    }

    private Response callPostTestResultsWithAlterations(String token, String requestBody, List<JsonPathAlteration> alterations) {
        //the only actions accepted are ADD_FIELD, ADD_VALUE, DELETE and REPLACE
        String alteredBody = GenericData.applyJsonAlterations(requestBody, alterations);

        Response response = given()
                .headers(
                        "Authorization",
                        "Bearer " + token)
                .body(alteredBody)
                .log().method().log().uri().log().body()
                .post("/test-results");
        return response;
    }

}
