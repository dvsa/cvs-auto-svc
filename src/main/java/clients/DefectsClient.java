package clients;

import io.restassured.filter.Filter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import util.BasePathFilter;
import util.NoDataPathFilter;

import static io.restassured.RestAssured.given;
import static util.WriterReader.saveUtils;

public class DefectsClient {


    public Response getDefectsWithData() {
        return getDefects(new BasePathFilter());
    }

    public Response getDefectsWithNoData() {
        return getDefects(new NoDataPathFilter());
    }

    private Response getDefects(Filter filter) {
        Response response = callGetDefects(filter);

        if (response.getStatusCode() == 401 || response.getStatusCode() == 403) {
            saveUtils();
            response = callGetDefects(filter);
        }

        return response;

    }

    private Response callGetDefects(Filter filter) {

        Response response = given().filters(filter)
                .contentType(ContentType.JSON)
                .log().all()
                .get("/defects");

        return response;
    }


}
