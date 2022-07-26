package steps;

import clients.TrailerRegistrationClient;
import io.restassured.response.Response;
import net.thucydides.core.annotations.Step;
import util.JsonPathAlteration;
import java.util.List;
import static util.TypeLoader.*;
import static org.hamcrest.Matchers.*;

public class TrailerRegistrationSteps {

    TrailerRegistrationClient trailerRegistrationClient = new TrailerRegistrationClient();
    Response response;

    @Step
    public void postTrailerRegistrationWithAlterations(String requestBody, List<JsonPathAlteration> alterations) {
        this.response = trailerRegistrationClient.postTrailerRegistrationWithAlterations(requestBody, alterations);
    }

    @Step
    public void statusCodeShouldBe(int statusCode) {
        response.then().log().all()
                .statusCode(statusCode);
    }

    @Step
    public void valueForFieldInPathShouldBe(String path, String expectedValue) {
        System.out.println("Verifying that " + path + " has value " + expectedValue);
        response.then().body(path, equalTo(expectedValue));
    }

    @Step
    public void putTrailerRegistrationWithAlterations(String trn,String requestBody,List<JsonPathAlteration> alterations) {
        this.response = trailerRegistrationClient.putTrailerRegistrationWithAlterations(trn,requestBody,alterations);
    }

    @Step
    public void postTrailerRegistrationWithNoAuthorization(String requestBody) {
        setMissingAuth();
        this.response = trailerRegistrationClient.callPostTrailerRegistrationWithNoAuthorization(requestBody);
        setRightAuth();
    }

    @Step
    public void validateMessage(String stringData) {
        if (response.getBody().asString().contains("message")) {
            response.then().body("message", equalTo(stringData));
        } else {
            response.then().body("Message", equalTo(stringData));
        }
    }

    @Step
    public void postTrailerRegistrationWithAlterationsDVLA(String requestBody, List<JsonPathAlteration> alterations) {
        this.response = trailerRegistrationClient.postTrailerRegistrationWithAlterationsDVLA(requestBody, alterations);
    }

    @Step
    public void putTrailerRegistrationWithNoAuthorization(String trn, String requestBody) {
        setMissingAuth();
        this.response = trailerRegistrationClient.callPutTrailerRegistrationWithNoAuthorization(trn, requestBody);
        setRightAuth();
    }

    @Step
    public void putTrailerRegistrationWithDVLAToken(String trn, String requestBody) {
        this.response = trailerRegistrationClient.putTrailerRegistrationWithDVLAToken(trn, requestBody);
    }
}
