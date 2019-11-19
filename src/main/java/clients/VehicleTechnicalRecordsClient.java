package clients;

import data.GenericData;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import util.BasePathFilter;
import util.JsonPathAlteration;

import java.io.IOException;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
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


    private Response callGetVehicleTechnicalRecords(String searchIdentifier) {

        Response response = given().filters(new BasePathFilter())
                .contentType(ContentType.JSON)
                .pathParam("searchIdentifier", searchIdentifier)

//                .log().all()
                .log().method().log().uri().log().body()
                .get("/vehicles/{searchIdentifier}/tech-records");

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
                .put("/vehicles/{vin}");
        return response;
    }

    private Response callPutVehicleTechnicalRecordsForVehicle(String vin, String body) {
        Response response = given().filters(new BasePathFilter())
                .contentType(ContentType.JSON)
                .body(body)
                .pathParam("vin", vin)
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
                .queryParam("filename", fileName)
//                .log().all()
                .log().method().log().uri().log().body()
                .get("/vehicles/{searchIdentifier}/tech-records");

        return response;
    }
}
