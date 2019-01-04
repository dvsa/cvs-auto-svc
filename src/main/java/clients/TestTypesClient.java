package clients;

import io.restassured.filter.Filter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import util.BasePathFilter;
import util.NoDataPathFilter;

import static io.restassured.RestAssured.given;

public class TestTypesClient {

    public Response getDefectsWithData() {
        return getDefects(new BasePathFilter());
    }

    public Response getDefectsWithNoData() {
        return getDefects(new NoDataPathFilter());
    }

    private Response getDefects(Filter filter) {
        Response response = given().filters(filter)
                .contentType(ContentType.JSON)
                .get("/test-types");

        return response;

    }

}
