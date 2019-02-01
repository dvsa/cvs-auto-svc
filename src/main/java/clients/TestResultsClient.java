package clients;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
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

}
