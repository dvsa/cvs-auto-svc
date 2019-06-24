package steps;

import clients.LogsClient;
import io.restassured.response.Response;
import model.logs.Logs;
import net.thucydides.core.annotations.Step;

public class LogsSteps {

    LogsClient logsClient = new LogsClient();
    Response response;

    @Step
    public void postLogs(Object logs) {
        response = logsClient.postLogs(logs);
    }

    @Step
    public void postLogsWithoutBody(Logs logs) {
        String [] nodes = {"timestamp","message","type"};
        response = logsClient.postLogsWithoutFields(logs,nodes);
    }

    @Step
    public void putLogs(Object logs) {
        response = logsClient.putLogs(logs);
    }

    @Step
    public void deletLogs() {
        response = logsClient.deleteLogs();
    }

    @Step
    public void getLogs() {
        response = logsClient.getLogs();
    }

    @Step
    public void statusCodeShouldBe(int statusCode) {
        response.then().statusCode(statusCode);
    }
}
