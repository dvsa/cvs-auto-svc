package steps;

import clients.LogsClient;
import clients.model.Logs;
import io.restassured.response.Response;
import net.thucydides.core.annotations.Step;

public class LogsSteps {

    LogsClient logsClient = new LogsClient();
    Response response;

    @Step
    public void postActivities(Object logs) {
        response = logsClient.postLogs(logs);
    }

    @Step
    public void statusCodeShouldBe(int statusCode) {
        response.then().statusCode(statusCode);
    }
}
