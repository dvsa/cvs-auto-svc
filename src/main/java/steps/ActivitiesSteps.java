package steps;

import clients.ActivitiesClient;
import clients.util.ToTypeConvertor;
import io.restassured.response.Response;
import model.activities.Activities;
import model.activities.ActivitiesGet;
import model.activities.ActivitiesPost;
import model.activities.ActivitiesPut;
import net.thucydides.core.annotations.Step;
import org.openqa.selenium.WebDriver;
import util.AwsUtil;
import util.JsonPathAlteration;
import util.WebDriverBrowsertack;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Assert;
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
    public Response postActivitiesGetResponse(Activities activities) {
        response = activitiesClient.postActivities(activities);
        return response;
    }

    @Step
    public void postActivitiesWithWaitReason(ActivitiesPost activities) {
        response = activitiesClient.postActivitiesWithWaitReason(activities);
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
    public void putActivitiesEnd(String id) {
        response = activitiesClient.putActivitiesEnd(id);
    }

    @Step
    public void putActivitiesUpdate(ActivitiesPut activities) {
        response = activitiesClient.putActivitiesUpdate(activities);
    }

    @Step
    public void getActivities(String activityType, String testerStaffId, String testStationPNumber, String fromStartTime, String toStartTime) {
        response = activitiesClient.getActivities(activityType, testerStaffId, testStationPNumber, fromStartTime, toStartTime);
    }

    @Step
    public void statusCodeShouldBe(int statusCode) {
        response.then().log().all()
                .statusCode(statusCode);
    }

    @Step
    public void valueForFieldInPathShouldBe(String path, boolean expectedValue) {
        System.out.println("Verifying that " + path + " has value " + expectedValue);
        response.then().body(path, equalTo(expectedValue));
    }

    @Step
    public int statusCodeShouldBeOkOrNotFound() {
        response.then().log().all()
                .statusCode(anyOf(is(200),is(404)));
        return response.statusCode();
    }

    @Step
    public void validateData(ActivitiesGet activities) {
        int record = 0;
        for (int i = 0; i < (Integer) response.jsonPath().get("size()"); i++) {
            System.out.println("i = " + i );
            List<String> id = response.jsonPath().getList("id");
            System.out.println("id is: " + id.get(i));
            if(id.get(i).equals(activities.getId())){
                System.out.println("id: " + id.get(i) + "activities id: " + activities.getId());
                record = i;
                break;
            }
        }
        response.then().body("activityType[" + record + "]", is(equalTo(activities.getActivityType())));
        response.then().body("testStationName[" + record + "]", is(equalTo(activities.getTestStationName())));
        response.then().body("testStationPNumber[" + record + "]", is(equalTo(activities.getTestStationPNumber())));
        response.then().body("testStationEmail[" + record + "]", is(equalTo(activities.getTestStationEmail())));
        response.then().body("testStationType[" + record + "]", is(equalTo(activities.getTestStationType())));
        response.then().body("testerName[" + record + "]", is(equalTo(activities.getTesterName())));
        response.then().body("testerStaffId[" + record + "]", is(equalTo(activities.getTesterStaffId())));
        response.then().body("waitReason", hasItem(contains(activities.getWaitReason().toArray())));
        response.then().body("notes[" + record + "]", is(equalTo(activities.getNotes())));
        response.then().body("[" + record + "]", hasKey("id"));
        response.then().body("[" + record + "]", hasKey("startTime"));
        response.then().body("[" + record + "]", hasKey("endTime"));
    }

    @Step
    public void validateData(ActivitiesPost activities) {

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
        response.then().body("waitReason", hasItem(contains(activities.getWaitReason().toArray())));
        response.then().body("notes[0]", is(equalTo(activities.getNotes())));
        response.then().body("[0]", hasKey("parentId"));
        response.then().body("[0]", hasKey("startTime"));
        response.then().body("[0]", hasKey("endTime"));
    }

    @Step
    public void validateData(String stringData) {
        response.then().body(is("\"\\\"" + stringData + "\\\"\""));
    }

    @Step
    public void validateNotExistingId(ActivitiesGet activities) {
        response.then().body("$", everyItem(hasKey("id")));
        response.then().body("id", anyOf(not(hasItem(activities.getId()))));
    }

    @Step
    public void responseShouldContainId() {
        response.then().body("$", hasKey("id"));
    }

    @Step
    public void responseShouldContainParentId() {
        response.then().body("$", everyItem(hasKey("parentId")));
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

    @Step
    public void responseShouldContainField(String field) {
        assertThat(response.then().body("$", hasKey(field)));
    }

    @Step
    public void responseElementsShouldContainField(String field) {
        assertThat(response.then().body("$", everyItem(hasKey(field))));
    }

    @Step
    public void responseShouldContainFieldValue(String field, String value) {
        assertThat(response.then().body("$", anyOf(hasItem(hasEntry(field,value)))));
    }

    @Step
    public void insertRecordInDynamo(String json, String table, String primaryKey) {
        AwsUtil.insertJsonInTable(json, table, primaryKey);
    }

    @Step
    public void deleteActivitiesForUser(String user) {
        AwsUtil.deleteActivitiesForUser(user);
    }

    @Step
    public WebDriver validateAtfEmail(String testerName) {
        return WebDriverBrowsertack.checkAtfEmail(testerName);
    }

    @Step
    public void validateAtfEmailDetails(WebDriver driver, String testStationName, String testStationPNumber,
                                        String testerName, String startDate, String startTime) {
        WebDriverBrowsertack.checkAtfEmailDetails(driver, testStationName, testStationPNumber, testerName, startDate,
                startTime);
    }

    @Step
    public void postActivitiesParentIdWithAlterations(String requestBody, List<JsonPathAlteration> alterations) {
        this.response = activitiesClient.postActivitiesWithAlterations(requestBody, alterations);
    }

    @Step
    public void insertActivityWithAlterations(String requestBody, List<JsonPathAlteration> alterations) {
        activitiesClient.insertActivityWithAlterations(requestBody, alterations);
    }

    @Step
    public void deleteActivity(String id) {
        activitiesClient.deleteActivity(id);
    }

    @Step
    public void checkAwsDispatcherLogContains(String id, String value) {
        String keyValuePair = "\""+id+"\"" + ":{\"S\":\"" + value + "\"}";
        assertThat(AwsUtil.checkLogsFor("/aws/lambda/edh-dispatcher", keyValuePair)).isTrue();
    }

    @Step
    public void checkAwsMarshallerLogContains(String key, String value) {
        String keyValuePair = key+": { S: '" + value + "' }";
        assertThat(AwsUtil.checkLogsFor("/aws/lambda/edh-marshaller", keyValuePair)).isTrue();
    }

    @Step
    public void checkAwsDispatcherLogStatusCodeForSystemNumber(String id, int httpCode) {
        String keyValuePair1 = "\"id\":\"" + id + "\"";
        String keyValuePair2 = "statusCode: " + httpCode;
        assertThat(AwsUtil.checkDispatcherLogsForData(keyValuePair1, keyValuePair2)).isTrue();
    }

    @Step
    public void checkAwsDispatcherLogStatusCodeForSystemNumber(String httpMethod, String id, int httpCode) {
        String keyValuePair1 = "\"id\":\"" + id + "\"";
        String keyValuePair2 = "statusCode: " + httpCode;
        String keyValuePair3 = "method: '" + httpMethod +"'";
        assertThat(AwsUtil.checkDispatcherLogsForData(keyValuePair1, keyValuePair2, keyValuePair3)).isTrue();
    }

    @Step
    public boolean checkOpenVisit(String testerStaffId,  boolean expectedValue) {
        response = activitiesClient.openVisitCheck(testerStaffId);
        if(response.statusCode() == 200) {
            assertThat(response.getBody().equals(expectedValue));
            System.out.println("THIS IS THE EXPECTED VALUE" + " " + expectedValue);
        }
        else {
            System.out.println(response.statusCode());
        }
        return expectedValue;
    }

    @Step
    public void closeOpenVisit(String activityId) {
        response = activitiesClient.putActivitiesEnd(activityId);
        if(response.statusCode() == 200) {
            assertThat(response.then().body("$", hasKey("wasVisitAlreadyClosed")));
        }
        else {
            System.out.println(response.statusCode());
        }
    }

    @Step
    public void validateResp(String stringData) {
        Assert.assertEquals(response.body().asString(), stringData);
    }
}
