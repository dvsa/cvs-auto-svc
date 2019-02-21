package steps;

import clients.ActivitiesClient;
import clients.util.ToTypeConvertor;
import io.restassured.response.Response;
import model.Activities;
import net.thucydides.core.annotations.Reported;
import net.thucydides.core.annotations.Step;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.hasKey;

public class ActivitiesSteps {

    private ActivitiesClient activitiesClient = new ActivitiesClient();
    private Response response;

    @Step
    public void postActivities(Activities activities) {
        response = activitiesClient.postActivities(activities);
    }

    @Step
    public void postActivities(Activities activities, String propertyField, String value, ToTypeConvertor toType) {
        response = activitiesClient.postActivitiesFieldChange(activities, propertyField, value, toType);
    }

    @Step
    public void postActivities(Activities activities, String propertyField, ToTypeConvertor toType) {
        response = activitiesClient.postActivitiesFieldChange(activities, propertyField, null, toType);
    }

    @Step
    public void putActivities(String id) {
        response = activitiesClient.putActivities(id);
    }

    @Step
    public void statusCodeShouldBe(int statusCode) {
        response.then().statusCode(statusCode);
    }

    @Step
    public void responseShouldContainId() {
        response.then().body("$", hasKey("id"));
    }

    @Step
    public void validateActivityErrorTypeWithProperty(String field, String errorMessage) {
        response.then().body("size()", is(1));
        response.then().body("error", equalTo("\"" + field + "\" " + errorMessage));
    }

    @Step
    public void validateActivityErrorTypeWithoutProperty(String field, String errorMessage) {
        response.then().body("size()", is(1));
        response.then().body("error", equalTo(field + " " + errorMessage));
    }

    @Step
    public String checkAndGetResponseId() {
        response.then().body("$", hasKey("id"));
        return response.jsonPath().get("id");
    }

}
