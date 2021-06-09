package steps;


import clients.TrailerRegistrationClient;
import io.restassured.response.Response;
import net.thucydides.core.annotations.Step;
import util.JsonPathAlteration;
import java.util.List;
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
    public void valueForFieldInPathShouldContain(String path, String expectedValue) {
        System.out.println("Verifying that " + path + " has value " + expectedValue);
        response.then().body(path, containsString(expectedValue));
    }
}
