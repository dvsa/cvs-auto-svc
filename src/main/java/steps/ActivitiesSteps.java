package steps;

import clients.ActivitiesClient;
import clients.util.ToTypeConvertor;
import io.restassured.response.Response;
import model.activities.Activities;
import model.activities.ActivitiesGet;
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
    public void getActivities(String activityType, String testerStaffId, String testStationPNumber, String fromStartTime, String toStartTime) {
        response = activitiesClient.getActivities(activityType, testerStaffId, testStationPNumber, fromStartTime, toStartTime);
    }

    @Step
    public int statusCodeShouldBe(int statusCode) {
        response.then().statusCode(statusCode);
        return response.statusCode();
    }

    @Step
    public void validateData(ActivitiesGet activities) {

        for (int i = 0; i < (Integer) response.jsonPath().get("size()"); i++) {
            response.then().body("[" + i + "].size()", is(equalTo(Activities.class.getDeclaredFields().length + 3)));
        }

        response.then().body("activityType[0]", is(equalTo(activities.getActivityType())));
        response.then().body("testStationName[0]", is(equalTo(activities.getTestStationName())));
        response.then().body("testStationPNumber[0]", is(equalTo(activities.getTestStationPNumber())));
        response.then().body("testStationEmail[0]", is(equalTo(activities.getTestStationEmail())));
        response.then().body("testStationType[0]", is(equalTo(activities.getTestStationType())));
        response.then().body("testerName[0]", is(equalTo(activities.getTesterName())));
        response.then().body("testerStaffId[0]", is(equalTo(activities.getTesterStaffId())));
        response.then().body("[0]", hasKey("id"));
        response.then().body("[0]", hasKey("startTime"));
        response.then().body("[0]", hasKey("endTime"));
    }

    @Step
    public void validateData(String stringData) {
        response.then().body(is("\"\\\"" + stringData + "\\\"\""));
    }

    @Step
    public void validateNotExistingId(ActivitiesGet activities) {
        response.then().body("id", not(hasItem(activities.getId())));
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
    public void validateActivityErrorMessage(String errorMessage) {
        response.then().body("size()", is(1));
        response.then().body("error", equalTo(errorMessage));
    }

    @Step
    public String checkAndGetResponseId() {
        response.then().body("$", hasKey("id"));
        return response.jsonPath().get("id");
    }

}
