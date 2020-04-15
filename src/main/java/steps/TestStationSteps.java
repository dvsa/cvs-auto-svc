package steps;

import clients.TestStationsClient;
import exceptions.AutomationException;
import io.restassured.response.Response;
import model.TestStations;
import net.thucydides.core.annotations.Step;
import util.AwsUtil;

import static org.hamcrest.Matchers.*;

public class TestStationSteps {

    TestStationsClient testStationsClient = new TestStationsClient();
    Response response;

    @Step
    public void getTestStationsWithData() {
        response = testStationsClient.getTestStationsWithData();
    }

    @Step
    public void getTestStationsWithNoData() {
        response = testStationsClient.getTestStationsWithNoData();
    }

    @Step
    public void statusCodeShouldBe(int statusCode) {

        response.then().log().all()
                .statusCode(statusCode);
    }

    @Step
    public void updateEmailsForTestStation(String primaryKeyValue,
                                           String... updateValue) {
        AwsUtil.updateEmailsForTestStation(primaryKeyValue, updateValue);
    }


    @Step
    public void validateData(TestStations testStation) {

        String index;

        if (response.jsonPath().get("find { it.testStationName == '" + testStation.getTestStationName() + "'}.testStationId") != null) {
            index = response.jsonPath().get("findIndexOf { it.testStationName == '" + testStation.getTestStationName() + "'}").toString();
        } else {
            throw new AutomationException("Test Station " + testStation.getTestStationName() + " was not found ! Please check data.");
        }
        response.then().body("[" + index + "].size()", is(TestStations.class.getDeclaredFields().length));
        response.then().body("testStationId", hasItem(equalTo(testStation.getTestStationId())));
        response.then().body("testStationPNumber", hasItem(equalTo(testStation.getTestStationPNumber())));
        response.then().body("testStationName", hasItem(equalTo(testStation.getTestStationName())));
        response.then().body("testStationContactNumber", hasItem(equalTo(testStation.getTestStationContactNumber())));
        response.then().body("testStationAccessNotes", hasItem(equalTo(testStation.getTestStationAccessNotes())));
        response.then().body("testStationGeneralNotes", hasItem(equalTo(testStation.getTestStationGeneralNotes())));
        response.then().body("testStationTown", hasItem(equalTo(testStation.getTestStationTown())));
        response.then().body("testStationAddress", hasItem(equalTo(testStation.getTestStationAddress())));
        response.then().body("testStationPostcode", hasItem(equalTo(testStation.getTestStationPostcode())));
        response.then().body("testStationLongitude", hasItem(equalTo(new Float(testStation.getTestStationLongitude()))));
        response.then().body("testStationLatitude", hasItem(equalTo(new Float(testStation.getTestStationLatitude()))));
        response.then().body("testStationType", hasItem(equalTo(testStation.getTestStationType())));
        response.then().body("testStationStatus", hasItem(equalTo(testStation.getTestStationStatus())));
    }

    @Step
    public void validateData(String stringData) {
        response.then().body("", is(stringData));
    }

    @Step
    public void validateEveryRecordHasField(String field) {
        response.then().body("$", everyItem(hasKey(field)));
    }

    @Step
    public void validateEveryRecordHasFieldValue(String field, String value) {
        response.then().body("$", everyItem(hasEntry(field,value)));
    }
}
