package clients;
import io.restassured.filter.Filter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import util.BasePathFilter;
import util.NoDataPathFilter;

import static io.restassured.RestAssured.given;
import static util.WriterReader.saveUtils;

public class LogsClient {

    public Response postLogs(Object logs) {
        return postDefects(new BasePathFilter(), logs);
    }

    public Response postLogsNoDataFilter(Object logs) {
        return postDefects(new NoDataPathFilter(), logs);
    }

    private Response postDefects(Filter filter, Object logs) {
        Response response = callPostLogs(filter, logs);

        if (response.getStatusCode() == 401 || response.getStatusCode() == 403) {
            saveUtils();
            response = callPostLogs(filter, logs);
        }

        return response;
    }

    private Response callPostLogs(Filter filter, Object object) {

        Response response = given().filters(filter)
                .contentType(ContentType.JSON)
                .body(object)
                .post("/logs");

        return response;
    }
}
