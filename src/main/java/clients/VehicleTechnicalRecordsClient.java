package clients;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import util.BasePathFilter;

import static io.restassured.RestAssured.given;

public class VehicleTechnicalRecordsClient {

    public Response getVehicleTechnicalRecords(String searchIdentifier) {

        Response response = given().filters(new BasePathFilter())
                .contentType(ContentType.JSON)
                .pathParam("searchIdentifier", searchIdentifier)
                .get("/vehicles/{searchIdentifier}/tech-records");

        return response;
    }


    public Response getVehicleTechnicalRecordsByStatus(String searchIdentifier, String status) {

        Response response = given().filters(new BasePathFilter())
                .contentType(ContentType.JSON)
                .pathParam("searchIdentifier", searchIdentifier)
                .pathParam("status", status)
                .get("/vehicles/{searchIdentifier}/tech-records/{status}");

        return response;
    }

}
