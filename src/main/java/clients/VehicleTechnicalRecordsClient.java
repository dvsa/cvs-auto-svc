package clients;

import data.GenericData;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import util.AwsUtil;
import util.BasePathFilter;
import util.JsonPathAlteration;

import java.io.IOException;
import java.util.List;

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
                .log().method().log().uri().log().body()
                .post("/vehicles");

        return response;
    }

    private Response callPutVehicleTechnicalRecordsWithAlterations(String vin, String requestBody, List<JsonPathAlteration> alterations) {
        //the only actions accepted are ADD_FIELD, ADD_VALUE, DELETE and REPLACE
        String alteredBody = GenericData.applyJsonAlterations(requestBody, alterations);


        Response response = given().filters(new BasePathFilter())
                .contentType(ContentType.JSON)
                .body(alteredBody)
                .pathParam("vin", vin)
                .log().method().log().uri().log().body()
                .put("/vehicles/{vin}");
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

    public void insertVehicle(String body, List<JsonPathAlteration> alterations) {
        String alteredBody = GenericData.applyJsonAlterations(body, alterations);
        AwsUtil.insertVehicle(alteredBody);
    }
}
