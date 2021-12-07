package clients;

import clients.model.VehicleClass;
import data.GenericData;
import exceptions.AutomationException;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import util.*;

import java.io.IOException;
import java.util.*;

import static io.restassured.RestAssured.given;
import static util.WriterReader.saveUtils;

public class VehicleTechnicalRecordsClient {

    public Response getVehicleTechnicalRecords(String searchIdentifier) {

        Response response = callGetVehicleTechnicalRecords(searchIdentifier);

        if (response.getStatusCode() == 401 || response.getStatusCode() == 403) {
            saveUtils();
            response = callGetVehicleTechnicalRecords(searchIdentifier);
        }

        return response;

    }

    public Response getVehicleTechnicalRecordsByStatus(String searchIdentifier, String status) {

        Response response = callGetVehicleTechnicalRecordsByStatus(searchIdentifier, status);

        if (response.getStatusCode() == 401 || response.getStatusCode() == 403) {
            saveUtils();
            response = callGetVehicleTechnicalRecordsByStatus(searchIdentifier, status);
        }

        return response;

    }

    public Response getVehicleTechnicalRecordsByStatusAndSearchCriteria(String searchIdentifier, String status,
                                                                          String searchCriteria) {
        Response response = callGetVehicleTechnicalRecordsByStatusAndSearchCriteria(searchIdentifier, status, searchCriteria);

        if (response.getStatusCode() == 401 || response.getStatusCode() == 403) {
            saveUtils();
            response = callGetVehicleTechnicalRecordsByStatusAndSearchCriteria(searchIdentifier, status, searchCriteria);
        }

        return response;
    }

    public Response getVehicleTechnicalRecordsBySearchCriteria(String searchIdentifier, String searchCriteria) {
        Response response = callGetVehicleTechnicalRecordsBySearchCriteria(searchIdentifier, searchCriteria);

        if (response.getStatusCode() == 401 || response.getStatusCode() == 403) {
            saveUtils();
            response = callGetVehicleTechnicalRecordsBySearchCriteria(searchIdentifier, searchCriteria);
        }

        return response;
    }

    public Response getVehicleTechnicalRecordsBySearchCriteriaWithDVLAToken(String searchIdentifier, String searchCriteria) {
        Response response = callGetVehicleTechnicalRecordsBySearchCriteriaWithDVLAToken(searchIdentifier, searchCriteria);

        if (response.getStatusCode() == 401 || response.getStatusCode() == 403) {
            saveUtils();
            response = callGetVehicleTechnicalRecordsBySearchCriteriaWithDVLAToken(searchIdentifier, searchCriteria);
        }

        return response;
    }

    public Response getVehicleTechnicalRecordsBySystemNumber(String searchIdentifier) {

        Response response = callGetVehicleTechnicalRecordsBySystemNumber(searchIdentifier);

        if (response.getStatusCode() == 401 || response.getStatusCode() == 403) {
            saveUtils();
            response = callGetVehicleTechnicalRecordsBySystemNumber(searchIdentifier);
        }

        return response;
    }

    private Response callGetVehicleTechnicalRecords(String searchIdentifier) {

        Response response = given().filters(new BasePathFilter())
                .contentType(ContentType.JSON)
                .pathParam("searchIdentifier", searchIdentifier)
                .queryParam("status", "provisional_over_current")
                .queryParam("status", "provisional_over_current")

//                .log().all()
                .log().method().log().uri().log().body()
                .get("/vehicles/{searchIdentifier}/tech-records");

        return response;
    }

    private Response callGetVehicleTechnicalRecordsBySystemNumber(String searchIdentifier) {

        Response response = given().filters(new BasePathFilter())
                .contentType(ContentType.JSON)
                .pathParam("searchIdentifier", searchIdentifier)
                .queryParam("status", "provisional")

//                .log().all()
                .log().method().log().uri().log().body()
                .get("/vehicles/{searchIdentifier}/tech-records?searchCriteria=systemNumber");

        return response;
    }

    public Response callGetVehicleTechnicalRecordsByStatus(String searchIdentifier, String status) {

        Response response = given().filters(new BasePathFilter())
                .contentType(ContentType.JSON)
                .pathParam("searchIdentifier", searchIdentifier)
                .queryParam("status", status)
//                .log().all()
                .log().method().log().uri().log().body()
                .get("/vehicles/{searchIdentifier}/tech-records");

        return response;
    }

    private Response callGetVehicleTechnicalRecordsByStatusAndSearchCriteria(String searchIdentifier, String status, String searchCriteria) {

        Response response = given().filters(new BasePathFilter())
                .contentType(ContentType.JSON)
                .pathParam("searchIdentifier", searchIdentifier)
                .queryParam("status", status)
                .queryParam("searchCriteria", searchCriteria)
//                .log().all()
                .log().method().log().uri().log().body()
                .get("/vehicles/{searchIdentifier}/tech-records");

        return response;

    }

    private Response callGetVehicleTechnicalRecordsBySearchCriteria(String searchIdentifier,String searchCriteria) {

        Response response = given().filters(new BasePathFilter())
                .contentType(ContentType.JSON)
                .pathParam("searchIdentifier", searchIdentifier)
                .queryParam("searchCriteria", searchCriteria)
                .log().method().log().uri().log().body()
                .get("/vehicles/{searchIdentifier}/tech-records");

        return response;

    }

    private Response callGetVehicleTechnicalRecordsBySearchCriteriaWithDVLAToken(String searchIdentifier,String searchCriteria) {

        Response response = given().filters(new DVLABasePathFilter())
                .contentType(ContentType.JSON)
                .pathParam("searchIdentifier", searchIdentifier)
                .queryParam("searchCriteria", searchCriteria)
                .log().method().log().uri().log().body()
                .get("/vehicles/{searchIdentifier}/tech-records");

        return response;

    }

    public String getBodyFromFile(String fileName) {
        String body = null;
        try {
            body = GenericData.readJsonBodyFromFile(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return body;
    }

    private Response callPostVehicleTechnicalRecords(String requestBody) {

        Response response = given().filters(new BasePathFilter())
                .contentType(ContentType.JSON)
                .body(requestBody)
                .post("/vehicles");

        return response;
    }

    private Response callPostVehicleTechnicalRecordsWithAlterations(String body, List<JsonPathAlteration> alterations) {

        //the only actions accepted are ADD_FIELD, ADD_VALUE, DELETE and REPLACE
        String alteredBody = GenericData.applyJsonAlterations(body, alterations);


        Response response = given().filters(new BasePathFilter())
                .contentType(ContentType.JSON)
                .body(alteredBody)
//                .log().all()
                .log().method().log().uri().log().body()
                .post("/vehicles");

        return response;
    }

    private Response callPutVehicleTechnicalRecordsWithAlterations(String systemNumber, String requestBody, List<JsonPathAlteration> alterations) {
        //the only actions accepted are ADD_FIELD, ADD_VALUE, DELETE and REPLACE
        String alteredBody = GenericData.applyJsonAlterations(requestBody, alterations);


        Response response = given().filters(new BasePathFilter())
                .contentType(ContentType.JSON)
                .body(alteredBody)
                .pathParam("systemNumber", systemNumber)
                .log().method().log().uri().log().body()
                .put("/vehicles/{systemNumber}");
        return response;
    }

    private Response callPutVehicleTechnicalRecordsForArchivedWithAlterations(String systemNumber, String requestBody, List<JsonPathAlteration> alterations) {
        //the only actions accepted are ADD_FIELD, ADD_VALUE, DELETE and REPLACE
        String alteredBody = GenericData.applyJsonAlterations(requestBody, alterations);


        Response response = given().filters(new BasePathFilter())
                .contentType(ContentType.JSON)
                .body(alteredBody)
                .pathParam("systemNumber", systemNumber)
                .log().method().log().uri().log().body()
                .put("/vehicles/archive/{systemNumber}");
        return response;
    }

    public Response postVehicleTechnicalRecordsForProvisionalWithAlterations(String systemNumber, String requestBody, List<JsonPathAlteration> alterations) {
        Response response = callPostVehicleTechnicalRecordsForProvisionalWithAlterations(systemNumber, requestBody,alterations);

        if (response.getStatusCode() == HttpStatus.SC_UNAUTHORIZED || response.getStatusCode() == HttpStatus.SC_FORBIDDEN) {
            saveUtils();
            response = callPostVehicleTechnicalRecordsForProvisionalWithAlterations(systemNumber, requestBody,alterations);
        }

        return response;
    }

    private Response callPostVehicleTechnicalRecordsForProvisionalWithAlterations(String systemNumber, String requestBody, List<JsonPathAlteration> alterations) {
        //the only actions accepted are ADD_FIELD, ADD_VALUE, DELETE and REPLACE
        String alteredBody = GenericData.applyJsonAlterations(requestBody, alterations);


        Response response = given().filters(new BasePathFilter())
                .contentType(ContentType.JSON)
                .body(alteredBody)
                .pathParam("systemNumber", systemNumber)
                .log().method().log().uri().log().body()
                .post("/vehicles/add-provisional/{systemNumber}");
        return response;
    }

    private Response callPutVehicleTechnicalRecordsForCurrentWithAlterations(String systemNumber,String oldStatusCode, String requestBody, List<JsonPathAlteration> alterations) {
        //the only actions accepted are ADD_FIELD, ADD_VALUE, DELETE and REPLACE
        String alteredBody = GenericData.applyJsonAlterations(requestBody, alterations);


        Response response = given().filters(new BasePathFilter())
                .contentType(ContentType.JSON)
                .body(alteredBody)
                .pathParam("systemNumber", systemNumber)
                .queryParam("oldStatusCode", oldStatusCode)
                .log().method().log().uri().log().body()
                .put("/vehicles/{systemNumber}");
        return response;
    }

    public Response putVehicleTechnicalRecordsForCurrentWithAlterations(String systemNumber,String oldStatusCode, String requestBody, List<JsonPathAlteration> alterations) {
        Response response = callPutVehicleTechnicalRecordsForCurrentWithAlterations(systemNumber,oldStatusCode, requestBody, alterations);

        if (response.getStatusCode() == HttpStatus.SC_UNAUTHORIZED || response.getStatusCode() == HttpStatus.SC_FORBIDDEN) {
            saveUtils();
            response = callPutVehicleTechnicalRecordsForCurrentWithAlterations(systemNumber,oldStatusCode,requestBody, alterations);
        }

        return response;
    }

    public Response putVehicleTechnicalRecordsForArchivedWithAlterations(String systemNumber, String requestBody, List<JsonPathAlteration> alterations) {
        Response response = callPutVehicleTechnicalRecordsForArchivedWithAlterations(systemNumber, requestBody, alterations);

        if (response.getStatusCode() == HttpStatus.SC_UNAUTHORIZED || response.getStatusCode() == HttpStatus.SC_FORBIDDEN) {
            saveUtils();
            response = callPutVehicleTechnicalRecordsForArchivedWithAlterations(systemNumber, requestBody, alterations);
        }

        return response;
    }

    private Response callPutVehicleTechnicalRecordsForVehicle(String vin, String body) {
        Response response = given().filters(new BasePathFilter())
                .contentType(ContentType.JSON)
                .body(body)
                .pathParam("vin", vin)
                .log().method().log().uri().log().body()
                .put("/vehicles/{vin}");
        return response;
    }

    public Response postVehicleTechnicalRecords(String requestBody) {
        Response response = callPostVehicleTechnicalRecords(requestBody);

        if (response.getStatusCode() == 401 || response.getStatusCode() == 403) {
            saveUtils();
            response = callPostVehicleTechnicalRecords(requestBody);
        }

        return response;
    }

    public Response postVehicleTechnicalRecordsWithAlterations(String body, List<JsonPathAlteration> alterations) {
        Response response = callPostVehicleTechnicalRecordsWithAlterations(body, alterations);

        if (response.getStatusCode() == 401 || response.getStatusCode() == 403) {
            saveUtils();
            response = callPostVehicleTechnicalRecordsWithAlterations(body, alterations);
        }

        return response;
    }

    public Response putVehicleTechnicalRecordsForVehicle(String vin, String requestBody) {
        Response response = callPutVehicleTechnicalRecordsForVehicle(vin, requestBody);

        if (response.getStatusCode() == 401 || response.getStatusCode() == 403) {
            saveUtils();
            response = callPutVehicleTechnicalRecordsForVehicle(vin, requestBody);
        }

        return response;
    }

    public Response putVehicleTechnicalRecordsWithAlterations(String vin, String requestBody, List<JsonPathAlteration> alterations) {
        Response response = callPutVehicleTechnicalRecordsWithAlterations(vin, requestBody, alterations);

        if (response.getStatusCode() == 401 || response.getStatusCode() == 403) {
            saveUtils();
            response = callPutVehicleTechnicalRecordsWithAlterations(vin, requestBody, alterations);
        }

        return response;
    }

    public Response downloadFile(String searchIdentifier, String fileName) {
        Response response = given().filters(new BasePathFilter())
                .contentType(ContentType.JSON)
                .pathParam("searchIdentifier", searchIdentifier)
                .pathParam("fileName", fileName)
//                .log().all()
                .log().method().log().uri().log().body()
                .get("/vehicles/{searchIdentifier}/download-file/{fileName}");

        return response;
    }

    public Response getVehicleTechnicalRecordsByStatusWithMetadata(String searchIdentifier, String status) {
        Response response = callGetVehicleTechnicalRecordsByStatusWithMetadata(searchIdentifier, status);

        if (response.getStatusCode() == 401 || response.getStatusCode() == 403) {
            saveUtils();
            response = callGetVehicleTechnicalRecordsByStatusWithMetadata(searchIdentifier, status);
        }

        return response;
    }

    private Response callGetVehicleTechnicalRecordsByStatusWithMetadata(String searchIdentifier, String status) {

        Response response = given().filters(new BasePathFilter())
                .contentType(ContentType.JSON)
                .pathParam("searchIdentifier", searchIdentifier)
                .queryParam("status", status)
                .queryParam("metadata", true)
//                .log().all()
                .log().method().log().uri().log().body()
                .get("/vehicles/{searchIdentifier}/tech-records");

        return response;
    }

    public void insertVehicle(String body, List<JsonPathAlteration> alterations) {
        String alteredBody = GenericData.applyJsonAlterations(body, alterations);
        AwsUtil.insertVehicle(alteredBody);
    }

    public Map<String, Object> createTechRecord(String vehicleType) {
        List<JsonPathAlteration> techRecordAlterations = new ArrayList<>();
        String techRecordPayloadFile = "technical-records_" + vehicleType + ".json";

        // CREATE TECH RECORD
        Map<String, Object> testResultAttributes = new HashMap<>();
        //generate random Vin
        String randomVin = GenericData.generateRandomVin();
        //generate random Vrm
        String randomVrm = GenericData.generateRandomVrm();
        if (!vehicleType.equals("hgv") && !vehicleType.equals("psv") && !vehicleType.equals("trl")) {
            throw new AutomationException("Invalid vehicle type");
        }
        // read post request body from file
        String postTechRecordBody = GenericData.readJsonValueFromFile(techRecordPayloadFile,"$");
        // create alteration to change Vin in the request body with the random generated Vin
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin,"","REPLACE");
        // create alteration to change primary vrm in the request body with the random generated primary vrm
        JsonPathAlteration alterationVrm = new JsonPathAlteration("$.primaryVrm", randomVrm,"","REPLACE");
        // additional alterations for tech record
        techRecordAlterations.add(alterationVin);
        techRecordAlterations.add(alterationVrm);

//        String alteredTechRecordBody = GenericData.applyJsonAlterations(postTechRecordBody, techRecordAlterations);

        Response postTechRecordsResponse = postVehicleTechnicalRecordsWithAlterations(postTechRecordBody,techRecordAlterations);


//        Response postTechRecordsResponse = given().filters(new BasePathFilter())
//                .contentType(ContentType.JSON)
//                .body(alteredTechRecordBody)
//                .log().method().log().uri().log().body()
//                .post("/vehicles");
//
//        if (postTechRecordsResponse.getStatusCode() == 401 || postTechRecordsResponse.getStatusCode() == 403) {
//            saveUtils();
//            postTechRecordsResponse = given().filters(new BasePathFilter())
//                    .contentType(ContentType.JSON)
//                    .body(alteredTechRecordBody)
//                    .log().method().log().uri().log().body()
//                    .post("/vehicles");
//        }

        Assert.assertEquals(201, postTechRecordsResponse.getStatusCode());

        Response getTechRecordsResponse = getVehicleTechnicalRecords(randomVin);
//        Response getTechRecordsResponse = given().filters(new BasePathFilter())
//                .contentType(ContentType.JSON)
//                .pathParam("searchIdentifier", randomVin)
//                .queryParam("status", "all")
//                .queryParam("searchCriteria", "vin")
//                .log().method().log().uri().log().body()
//                .get("/vehicles/{searchIdentifier}/tech-records");
//        if (getTechRecordsResponse.getStatusCode() == 401 || getTechRecordsResponse.getStatusCode() == 403) {
//            getTechRecordsResponse = given().filters(new BasePathFilter())
//                    .contentType(ContentType.JSON)
//                    .pathParam("searchIdentifier", randomVin)
//                    .queryParam("status", "all")
//                    .queryParam("searchCriteria", "vin")
//                    .log().method().log().uri().log().body()
//                    .get("/vehicles/{searchIdentifier}/tech-records");
//        }
//        getTechRecordsResponse.prettyPrint();
        Assert.assertEquals(getTechRecordsResponse.statusCode(), 200);


        String systemNumber = GenericData.extractStringValueFromJsonString
                (getTechRecordsResponse.prettyPrint(), "$[0].systemNumber");
        String trailerId;
        String firstUseDate;
        if (vehicleType.equals("trl")) {
            trailerId = GenericData.extractStringValueFromJsonString
                    (getTechRecordsResponse.prettyPrint(), "$[0].trailerId");
            firstUseDate = GenericData.extractStringValueFromJsonString
                    (getTechRecordsResponse.prettyPrint(), "$[0].techRecord[0].firstUseDate");
        }
        else {
            trailerId = null;
            firstUseDate = null;
        }
        int noOfAxles = GenericData.extractIntegerValueFromJsonString
                (getTechRecordsResponse.prettyPrint(), "$[0].techRecord[0].noOfAxles");
        String vehicleClassCode = GenericData.extractStringValueFromJsonString
                (getTechRecordsResponse.prettyPrint(), "$[0].techRecord[0].vehicleClass.code");
        String vehicleClassDescription = GenericData.extractStringValueFromJsonString
                (getTechRecordsResponse.prettyPrint(), "$[0].techRecord[0].vehicleClass.description");
        ArrayList<String> vehicleSubclass;
        if (vehicleType.contentEquals("car") || vehicleType.contentEquals("lgv")) {
            vehicleSubclass = GenericData.extractArrayListStringFromJsonString
                    (getTechRecordsResponse.prettyPrint(), "$[0].techRecord[0].vehicleSubclass");
        }
        else {
            vehicleSubclass = null;
        }
        String vehicleConfiguration;
        if (!(vehicleType.contentEquals("car") || vehicleType.contentEquals("motorcycle") || vehicleType.contentEquals("lgv"))) {
            vehicleConfiguration = GenericData.extractStringValueFromJsonString
                    (getTechRecordsResponse.prettyPrint(), "$[0].techRecord[0].vehicleConfiguration");
        }
        else {
            vehicleConfiguration = null;
        }
        String vehicleSize;
        if (vehicleType.contentEquals("psv")) {
            vehicleSize = GenericData.extractStringValueFromJsonString
                    (getTechRecordsResponse.prettyPrint(), "$[0].techRecord[0].vehicleSize");
        }
        else {
            vehicleSize = null;
        }
        String euVehicleCategory = GenericData.extractStringValueFromJsonString
                (getTechRecordsResponse.prettyPrint(), "$[0].techRecord[0].euVehicleCategory");
        String regnDate;
        if (!vehicleType.contentEquals("trl")) {
            regnDate = GenericData.extractStringValueFromJsonString
                    (getTechRecordsResponse.prettyPrint(), "$[0].techRecord[0].regnDate");
        }
        else {
            regnDate = null;
        }
        int numberOfWheelsDriven;
        if (vehicleType.contentEquals("psv") || vehicleType.contentEquals("hgv") || vehicleType.contentEquals("motorcycle")) {
            numberOfWheelsDriven = GenericData.extractIntegerValueFromJsonString
                    (getTechRecordsResponse.prettyPrint(), "$[0].techRecord[0].numberOfWheelsDriven");
        }
        else {
            numberOfWheelsDriven = -1;
        }

        // add in map all attributes that we need to change in the test results payload so they match the values on the
        // newly created tech record
        testResultAttributes.put("vin", randomVin);
        testResultAttributes.put("vrm", randomVrm);
        testResultAttributes.put("systemNumber", systemNumber);
        if (trailerId != null) {
            testResultAttributes.put("trailerId", trailerId);
        }
        testResultAttributes.put("noOfAxles", noOfAxles);
        testResultAttributes.put("vehicleClass.code", vehicleClassCode);
        testResultAttributes.put("vehicleClass.description", vehicleClassDescription);
        if (vehicleSubclass != null) {
            testResultAttributes.put("vehicleSubclass", vehicleSubclass);
        }
        if (vehicleConfiguration != null) {
            testResultAttributes.put("vehicleConfiguration", vehicleConfiguration);
        }
        if (vehicleSize != null) {
            testResultAttributes.put("vehicleSize", vehicleSize);
        }
        testResultAttributes.put("euVehicleCategory", euVehicleCategory);
        if (firstUseDate != null) {
            testResultAttributes.put("firstUseDate", firstUseDate);
        }
        if (regnDate != null) {
            testResultAttributes.put("regnDate", regnDate);
        }
        if (numberOfWheelsDriven != -1) {
            testResultAttributes.put("numberOfWheelsDriven", numberOfWheelsDriven);
        }

        return testResultAttributes;
    }

    public Map<String, Object> createTechRecord(JSONObject restrictions) {
        List<JsonPathAlteration> techRecordAlterations = new ArrayList<>();
        String vehicleType = "";
        try {
            int length = restrictions.length();
            if (length != 0) {
                Iterator iterator = restrictions.keys();
                Object[] names = new Object[length];
                int i = 0;
                while (iterator.hasNext()) {
                    names[i] = iterator.next();
                    if (names[i].toString().contentEquals("vehicleType")) {
                        vehicleType = restrictions.get(names[i].toString()).toString();
                    }
                    if (names[i].toString().contentEquals("vehicleClass")) {
                        String vehicleClassFields = "";
                        for (VehicleClass vehicleClass : VehicleClass.values()) {
                            if (vehicleClass.getCode().contentEquals(restrictions.get(names[i].toString()).toString())) {
                                vehicleClassFields = "{\"description\":\"" + vehicleClass.getDescription
                                        (restrictions.get(names[i].toString()).toString()) + "\"}";
                                break;
                            }
                        }
                        // add tech record alteration for each restriction set on the test type to be created
                        JsonPathAlteration additionalTechRecordAlteration =
                                new JsonPathAlteration("$.techRecord[0]." + names[i].toString(), vehicleClassFields, "", "REPLACE");
                        techRecordAlterations.add(additionalTechRecordAlteration);
                    }
                    else {
                        // add tech record alteration for each restriction set on the test type to be created
                        JsonPathAlteration additionalTechRecordAlteration =
                                new JsonPathAlteration("$.techRecord[0]." + names[i].toString(), restrictions.get(names[i].toString()), "", "REPLACE");
                        techRecordAlterations.add(additionalTechRecordAlteration);
                    }
                    i += 1;
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        String techRecordPayloadFile = "technical-records_" + vehicleType + ".json";

        // CREATE TECH RECORD
        Map<String, Object> testResultAttributes = new HashMap<>();
        //generate random Vin
        String randomVin = GenericData.generateRandomVin();
        //generate random Vrm
        String randomVrm = GenericData.generateRandomVrm();
        if (!(vehicleType.contentEquals("hgv") || vehicleType.contentEquals("psv") ||
                vehicleType.contentEquals("trl") || vehicleType.contentEquals("lgv") ||
                vehicleType.contentEquals("car") || vehicleType.contentEquals("motorcycle"))) {
            throw new AutomationException("Invalid vehicle type");
        }
        // read post request body from file
        String postTechRecordBody = GenericData.readJsonValueFromFile(techRecordPayloadFile,"$");
        // create alteration to change Vin in the request body with the random generated Vin
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin,"","REPLACE");
        // create alteration to change primary vrm in the request body with the random generated primary vrm
        JsonPathAlteration alterationVrm = new JsonPathAlteration("$.primaryVrm", randomVrm,"","REPLACE");
        // additional alterations for tech record
        techRecordAlterations.add(alterationVin);
        techRecordAlterations.add(alterationVrm);


        Response postTechRecordsResponse = postVehicleTechnicalRecordsWithAlterations(postTechRecordBody,techRecordAlterations);


        postTechRecordsResponse.prettyPrint();
        if (postTechRecordsResponse.statusCode() != 201) {
            postTechRecordsResponse.prettyPrint();
            throw new AutomationException("The post tech records request was not successful, status code was "
                    + postTechRecordsResponse.statusCode());
        }

        Response getTechRecordsResponse = getVehicleTechnicalRecords(randomVin);
        getTechRecordsResponse.prettyPrint();
        if (getTechRecordsResponse.statusCode() != 200) {
            postTechRecordsResponse.prettyPrint();
            throw new AutomationException("The get tech records request was not successful, status code was "
                    + getTechRecordsResponse.statusCode());
        }


        String systemNumber = GenericData.extractStringValueFromJsonString
                (getTechRecordsResponse.prettyPrint(), "$[0].systemNumber");
        String trailerId;
        String firstUseDate;
        if (vehicleType.equals("trl")) {
            trailerId = GenericData.extractStringValueFromJsonString
                    (getTechRecordsResponse.prettyPrint(), "$[0].trailerId");
            firstUseDate = GenericData.extractStringValueFromJsonString
                    (getTechRecordsResponse.prettyPrint(), "$[0].techRecord[0].firstUseDate");
        }
        else {
            trailerId = null;
            firstUseDate = null;
        }
        int noOfAxles = GenericData.extractIntegerValueFromJsonString
                (getTechRecordsResponse.prettyPrint(), "$[0].techRecord[0].noOfAxles");
        String vehicleClassCode = GenericData.extractStringValueFromJsonString
                (getTechRecordsResponse.prettyPrint(), "$[0].techRecord[0].vehicleClass.code");
        String vehicleClassDescription = GenericData.extractStringValueFromJsonString
                (getTechRecordsResponse.prettyPrint(), "$[0].techRecord[0].vehicleClass.description");
        JSONArray vehicleSubclass;
        if (vehicleType.contentEquals("car") || vehicleType.contentEquals("lgv")) {
            try {
                vehicleSubclass = new JSONArray(GenericData.extractArrayListStringFromJsonString
                        (getTechRecordsResponse.prettyPrint(), "$[0].techRecord[0].vehicleSubclass").toString());
            } catch (JSONException e) {
                throw new AutomationException(e.getMessage());
            }
        }
        else {
            vehicleSubclass = null;
        }
        String vehicleConfiguration;
        if (!(vehicleType.contentEquals("car") || vehicleType.contentEquals("motorcycle") || vehicleType.contentEquals("lgv"))) {
            vehicleConfiguration = GenericData.extractStringValueFromJsonString
                    (getTechRecordsResponse.prettyPrint(), "$[0].techRecord[0].vehicleConfiguration");
        }
        else {
            vehicleConfiguration = null;
        }
        String vehicleSize;
        if (vehicleType.contentEquals("psv")) {
            vehicleSize = GenericData.extractStringValueFromJsonString
                    (getTechRecordsResponse.prettyPrint(), "$[0].techRecord[0].vehicleSize");
        }
        else {
            vehicleSize = null;
        }
        String euVehicleCategory = GenericData.extractStringValueFromJsonString
                (getTechRecordsResponse.prettyPrint(), "$[0].techRecord[0].euVehicleCategory");
        String regnDate;
        if (!vehicleType.contentEquals("trl")) {
            regnDate = GenericData.extractStringValueFromJsonString
                    (getTechRecordsResponse.prettyPrint(), "$[0].techRecord[0].regnDate");
        }
        else {
            regnDate = null;
        }
        int numberOfWheelsDriven;
        if (vehicleType.contentEquals("psv") || vehicleType.contentEquals("hgv") || vehicleType.contentEquals("motorcycle")) {
            numberOfWheelsDriven = GenericData.extractIntegerValueFromJsonString
                    (getTechRecordsResponse.prettyPrint(), "$[0].techRecord[0].numberOfWheelsDriven");
        }
        else {
            numberOfWheelsDriven = -1;
        }

        // add in map all attributes that we need to change in the test results payload so they match the values on the
        // newly created tech record
        testResultAttributes.put("vin", randomVin);
        testResultAttributes.put("vrm", randomVrm);
        testResultAttributes.put("systemNumber", systemNumber);
        if (trailerId != null) {
            testResultAttributes.put("trailerId", trailerId);
        }
        testResultAttributes.put("noOfAxles", noOfAxles);
        testResultAttributes.put("vehicleClass.code", vehicleClassCode);
        testResultAttributes.put("vehicleClass.description", vehicleClassDescription);
        if (vehicleSubclass != null) {
            testResultAttributes.put("vehicleSubclass", vehicleSubclass);
        }
        if (vehicleConfiguration != null) {
            testResultAttributes.put("vehicleConfiguration", vehicleConfiguration);
        }
        if (vehicleSize != null) {
            testResultAttributes.put("vehicleSize", vehicleSize);
        }
        testResultAttributes.put("euVehicleCategory", euVehicleCategory);
        if (firstUseDate != null) {
            testResultAttributes.put("firstUseDate", firstUseDate);
        }
        if (regnDate != null) {
            testResultAttributes.put("regnDate", regnDate);
        }
        if (numberOfWheelsDriven != -1) {
            testResultAttributes.put("numberOfWheelsDriven", numberOfWheelsDriven);
        }
        testResultAttributes.put("vehicleType", vehicleType);

        return testResultAttributes;
    }

    public Response postTechRecordsWithAlterations(String token, String requestBody, List<JsonPathAlteration> alterations) {
        Response response = callPostTechRecordsWithAlterations(token, requestBody, alterations);

        if (response.getStatusCode() == HttpStatus.SC_UNAUTHORIZED || response.getStatusCode() == HttpStatus.SC_FORBIDDEN) {
            response = callPostTechRecordsWithAlterations(token, requestBody, alterations);
        }
        Assert.assertEquals(HttpStatus.SC_CREATED, response.statusCode());
        return response;
    }

    private Response callPostTechRecordsWithAlterations(String token, String requestBody, List<JsonPathAlteration> alterations) {
        //the only actions accepted are ADD_FIELD, ADD_VALUE, DELETE and REPLACE
        String alteredBody = GenericData.applyJsonAlterations(requestBody, alterations);

        Response response = given().headers(
                "Authorization",
                "Bearer " + token)
                .contentType(ContentType.JSON)
                .body(alteredBody)
                .log().method().log().uri().log().body()
                .post("/vehicles");
        return response;
    }

    public Response getTechRecords(String token, String vin) {
        Response response = callGetTechRecords(token, vin);

        if (response.getStatusCode() == HttpStatus.SC_UNAUTHORIZED || response.getStatusCode() == HttpStatus.SC_FORBIDDEN) {
            response = callGetTechRecords(token, vin);
        }
        Assert.assertEquals(HttpStatus.SC_OK, response.statusCode());
        return response;
    }

    public Response callGetTechRecords(String token, String vin) {
        Response response = given()
                .headers(
                        "Authorization",
                        "Bearer " + token)
                .contentType(ContentType.JSON)
                .pathParam("searchIdentifier", vin)
                .queryParam("status", "all")
                .queryParam("searchCriteria", "vin")
                .log().method().log().uri().log().body()
                .get("/vehicles/{searchIdentifier}/tech-records");
        return response;
    }

    public Response getVehicleTechnicalRecords(String searchIdentifier, String searchCriteria) {
        Response response = given().filters(new BasePathFilter())
                .contentType(ContentType.JSON)
                .pathParam("searchIdentifier", searchIdentifier)
                .queryParam("searchCriteria", searchCriteria)
//               .log().all()
                .log().method().log().uri().log().body()
                .get("/vehicles/{searchIdentifier}/tech-records");

        return response;
    }

    private Response callPutVehicleTechnicalRecordsWithDVLAToken(String systemNumber, String postRequestBody) {
        //the only actions accepted are ADD_FIELD, ADD_VALUE, DELETE and REPLACE


        Response response = given().filters(new DVLABasePathFilter())
                .contentType(ContentType.JSON)
                .body(postRequestBody)
                .pathParam("systemNumber", systemNumber)
                .log().method().log().uri().log().body()
                .put("/vehicles/{systemNumber}");
        return response;
    }

    public Response putVehicleTechnicalRecordsWithDVLAToken(String searchIdentifier, String searchCriteria) {
        Response response = callPutVehicleTechnicalRecordsWithDVLAToken(searchIdentifier, searchCriteria);

        if (response.getStatusCode() == 401 || response.getStatusCode() == 403) {
            saveUtils();
            response = callPutVehicleTechnicalRecordsWithDVLAToken(searchIdentifier, searchCriteria);
        }

        return response;
    }
}
