package clients;

import io.restassured.filter.Filter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import util.BasePathFilter;
import util.NoDataPathFilter;

import static io.restassured.RestAssured.given;

public class PreparersClient {

    public Response getPreparersWithData() {
        return getPreparers(new BasePathFilter());
    }

    public Response getPreparersWithNoData() {
        return getPreparers(new NoDataPathFilter());
    }

    private Response getPreparers(Filter filter) {
        Response response = given().filters(filter)
                .contentType(ContentType.JSON)
                .get("/preparers");

        return response;

    }
}
