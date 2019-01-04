package steps;

import clients.AtfClient;
import io.restassured.response.Response;
import model.Atf;
import net.thucydides.core.annotations.Step;

import static org.hamcrest.Matchers.*;

public class AtfSteps {

    AtfClient atfClient = new AtfClient();
    Response response;

    @Step
    public void getATFsWithData() {
        response = atfClient.getATFsWithData();
    }

    @Step
    public void getATFsWithNoData() {
        response = atfClient.getATFsWithNoData();
    }

    @Step
    public void statusCodeShouldBe(int statusCode) {
        response.then().statusCode(statusCode);
    }

    @Step
    public void validateData(Atf atf) {

        response.then().body("atfId", hasItem(equalTo(atf.getAtfId())));
        response.then().body("atfNumber", hasItem(equalTo(atf.getAtfNumber())));
        response.then().body("atfName", hasItem(equalTo(atf.getAtfName())));
        response.then().body("atfContactNumber", hasItem(equalTo(atf.getAtfContactNumber())));
        response.then().body("atfAccessNotes", hasItem(equalTo(atf.getAtfAccessNotes())));
        response.then().body("atfGeneralNotes", hasItem(equalTo(atf.getAtfGeneralNotes())));
        response.then().body("atfTown", hasItem(equalTo(atf.getAtfTown())));
        response.then().body("atfAddress", hasItem(equalTo(atf.getAtfAddress())));
        response.then().body("atfPostcode", hasItem(equalTo(atf.getAtfPostcode())));
        response.then().body("atfLongitude", hasItem(comparesEqualTo(new Float(atf.getAtfLongitude()))));
        response.then().body("atfLatitude", hasItem(comparesEqualTo(new Float(atf.getAtfLatitude()))));
        response.then().body("atfType", hasItem(equalTo(atf.getAtfType())));
    }

    @Step
    public void validateData(String stringData) {
        response.then().body("", is(stringData));
    }
}
