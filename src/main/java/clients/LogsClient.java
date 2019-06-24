package clients;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.restassured.filter.Filter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import model.logs.Logs;
import util.BasePathFilter;
import util.NoDataPathFilter;

import static io.restassured.RestAssured.given;
import static util.WriterReader.saveUtils;

public class LogsClient {

    public Response postLogs(Object logs) {
        return postLogs(new BasePathFilter(), logs);
    }

    public Response postLogsNoDataFilter(Object logs) {
        return postLogs(new NoDataPathFilter(), logs);
    }

    public Response putLogs(Object logs) {
        return putLogs(new BasePathFilter(), logs);
    }

    public Response putLogsNoDataFilter(Object logs) {
        return putLogs(new NoDataPathFilter(), logs);
    }

    public Response getLogs() {
        return getLogs(new BasePathFilter());
    }

    public Response getLogsNoDataFilter(Object logs) {
        return deleteLogs(new NoDataPathFilter());
    }

    public Response deleteLogs() {
        return getLogs(new BasePathFilter());
    }

    public Response deleteLogsNoDataFilter(Object logs) {
        return deleteLogs(new NoDataPathFilter());
    }

    private Response getLogs(Filter filter) {
        Response response = callGetLogs(filter);

        if (response.getStatusCode() == 401 || response.getStatusCode() == 403) {
            saveUtils();
            response = callGetLogs(filter);
        }
        return response;
    }

    private Response callGetLogs(Filter filter) {

        Response response = given().filters(filter)
                .contentType(ContentType.JSON)
                .get("/logs");
        return response;
    }

    private Response callPostLogs(Filter filter, Object object) {

        Response response = given().filters(filter)
                .contentType(ContentType.JSON)
                .body(object)
                .log().all()
                .post("/logs");
        return response;
    }


    private Response postLogs(Filter filter, Object logs) {
        Response response = callPostLogs(filter, logs);

        if (response.getStatusCode() == 401 || response.getStatusCode() == 403) {
            saveUtils();
            response = callPostLogs(filter, logs);
        }
        return response;
    }

    private Response callPuttLogs(Filter filter, Object object) {

        Response response = given().filters(filter)
                .contentType(ContentType.JSON)
                .body(object)
                .put("/logs");
        return response;
    }


    private Response putLogs(Filter filter, Object logs) {
        Response response = callPostLogs(filter, logs);

        if (response.getStatusCode() == 401 || response.getStatusCode() == 403) {
            saveUtils();
            response = callPuttLogs(filter, logs);
        }

        return response;
    }

    private Response deleteLogs(Filter filter) {
        Response response = callGetLogs(filter);

        if (response.getStatusCode() == 401 || response.getStatusCode() == 403) {
            saveUtils();
            response = deleteGetLogs(filter);
        }

        return response;
    }

    private Response deleteGetLogs(Filter filter) {

        Response response = given().filters(filter)
                .contentType(ContentType.JSON)
                .delete("/logs");

        return response;
    }

    public Response postLogsWithoutFields(Logs logs, String... nodes) {
        return postLogsFieldRemove(new BasePathFilter(), logs, nodes);
    }

    private Response postLogsFieldRemove(Filter filter, Logs logs, String... nodes) {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode node = objectMapper.valueToTree(logs);

        for (int i = 0; i < nodes.length; i++) {
            node.remove(nodes[i]);
        }

        Response response = callPostLogs(filter, logs);

        if (response.getStatusCode() == 401 || response.getStatusCode() == 403) {
            saveUtils();
            response = callPostLogs(filter, logs);
        }
        return response;
    }
}
