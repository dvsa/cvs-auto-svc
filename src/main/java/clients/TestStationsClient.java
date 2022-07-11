package clients;

import io.restassured.filter.Filter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import util.BasePathFilter;
import util.NoDataPathFilter;
import static io.restassured.RestAssured.given;
import static util.WriterReader.saveUtils;

public class TestStationsClient {

    public Response getTestStationsWithData() {
        return getTestStations(new BasePathFilter());
    }

    public Response getTestStationsWithNoData() {
        return getTestStations(new NoDataPathFilter());
    }

    private Response getTestStations(Filter filter) {
        Response response = callGetTestStations(filter);

        if (response.getStatusCode() == 401 || response.getStatusCode() == 403) {
            saveUtils();
            response = callGetTestStations(filter);
        }

        return response;
    }

    public Response getTestStationsEmail(String p_number) {
        Response response = callGetTestStationsEmail(p_number);

        if (response.getStatusCode() == 401 || response.getStatusCode() == 403) {
            saveUtils();
            response = callGetTestStationsEmail(p_number);
        }

        return response;
    }



    private Response callGetTestStations(Filter filter) {
        Response response = given().filters(filter)
                .contentType(ContentType.JSON)
//                .log().all()
                .log().method().log().uri().log().body()
                .get("/test-stations");

        return response;
    }

    private Response callGetTestStationsEmail(String p_umber) {
        Response response = given().filters(new BasePathFilter())
//                .filters(new BasePathFilter())
                .contentType(ContentType.JSON)
                .pathParam("p_umber", p_umber)
                .log().method().log().uri().log().body()
                .get("/test-stations/{p_umber}");

        return response;
    }
}
