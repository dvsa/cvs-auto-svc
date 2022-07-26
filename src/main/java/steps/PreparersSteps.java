package steps;

import clients.PreparersClient;
import io.restassured.response.Response;
import model.Preparer;
import net.thucydides.core.annotations.Step;
import static org.hamcrest.Matchers.*;

public class PreparersSteps {

    PreparersClient preparersClient = new PreparersClient();
    Response response;

    @Step
    public void getPreparersWithData() {
        response = preparersClient.getPreparersWithData();
    }

    @Step
    public void getPreparersWithNoData() {
        response = preparersClient.getPreparersWithNoData();
    }

    @Step
    public void statusCodeShouldBe(int statusCode) {
        response.then().statusCode(statusCode);
    }

    @Step
    public void validateData(String stringData) {
        response.then().body("", is(stringData));
    }

    @Step
    public void validateData(Preparer preparer) {

        for (int i = 0; i < (Integer) response.jsonPath().get("size()"); i++) {
                response.then().body("[" + i + "].size()", is(equalTo(Preparer.class.getDeclaredFields().length)));
        }

        response.then().body("preparerId", hasItem(equalTo(preparer.getPreparerId())));
        response.then().body("preparerName", hasItem(equalTo(preparer.getPreparerName())));
    }

}
