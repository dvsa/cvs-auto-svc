package clients;

import io.restassured.filter.Filter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import util.BasePathFilter;
import util.NoDataPathFilter;

import static io.restassured.RestAssured.given;

public class TestStationsClient {

    public Response getTestStationsWithData() {
        return callGetTestStations(new BasePathFilter());
    }

    public Response getTestStationsWithNoData() {
        return callGetTestStations(new NoDataPathFilter());
    }


    private Response callGetTestStations(Filter filter) {
        Response response = given().filters(filter)
                .contentType(ContentType.JSON)
                .get("/test-stations");

        return response;

    }
}
