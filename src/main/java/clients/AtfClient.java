package clients;

import io.restassured.filter.Filter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import util.BasePathFilter;
import util.NoDataPathFilter;

import static io.restassured.RestAssured.given;

public class AtfClient {

    public Response getATFsWithData() {
        return callGetATFs(new BasePathFilter());
    }

    public Response getATFsWithNoData() {
        return callGetATFs(new NoDataPathFilter());
    }


    private Response callGetATFs(Filter filter) {
        Response response = given().filters(filter)
                .contentType(ContentType.JSON)
                .get("/atf");

        return response;

    }
}
