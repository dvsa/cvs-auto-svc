package clients;


import data.GenericData;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import util.BasePathFilter;
import util.DVLABasePathFilter;
import util.JsonPathAlteration;
import java.util.*;

import static io.restassured.RestAssured.given;
import static util.WriterReader.saveUtils;

public class TrailerRegistrationClient {

    private Response callPostTrailerRegistrationWithAlterations(String body, List<JsonPathAlteration> alterations) {

        //the only actions accepted are ADD_FIELD, ADD_VALUE, DELETE and REPLACE
        String alteredBody = GenericData.applyJsonAlterations(body, alterations);

        Response response = given().filters(new BasePathFilter())
                .contentType(ContentType.JSON)
                .body(alteredBody)
                .log().method().log().uri().log().body()
                .post("/v1/trailers");

        return response;
    }

    public Response postTrailerRegistrationWithAlterations(String body, List<JsonPathAlteration> alterations) {
        Response response = callPostTrailerRegistrationWithAlterations(body, alterations);

        if (response.getStatusCode() == 401 || response.getStatusCode() == 403) {
            saveUtils();
            response = callPostTrailerRegistrationWithAlterations(body, alterations);
        }

        return response;
    }

    private Response callPutTrailerRegistrationWithAlterations(String trn,String body,List<JsonPathAlteration> alterations) {

        String alteredBody = GenericData.applyJsonAlterations(body, alterations);
        //the only actions accepted are ADD_FIELD, ADD_VALUE, DELETE and REPLACE

        Response response = given().filters(new BasePathFilter())
                .contentType(ContentType.JSON)
                .pathParam("trn", trn)
                .body(alteredBody)
                .log().method().log().uri().log().body()
                .put("/v1/trailers/deregister/{trn}");

        return response;
    }

    public Response putTrailerRegistrationWithAlterations(String trn,String body, List<JsonPathAlteration> alterations) {
        Response response = callPutTrailerRegistrationWithAlterations(trn,body,alterations);

        if (response.getStatusCode() == 401 || response.getStatusCode() == 403) {
            saveUtils();
            response = callPutTrailerRegistrationWithAlterations(trn,body,alterations);
        }

        return response;
    }

    public Response callPostTrailerRegistrationWithNoAuthorization(String body) {

        Response response = given().filters(new BasePathFilter())
                .contentType(ContentType.JSON)
                .body(body)
                .log().method().log().uri().log().body()
                .post("/v1/trailers");

        return response;
    }

    private Response callPostTrailerRegistrationWithAlterationsDVLA(String body, List<JsonPathAlteration> alterations) {

        //the only actions accepted are ADD_FIELD, ADD_VALUE, DELETE and REPLACE
        String alteredBody = GenericData.applyJsonAlterations(body, alterations);

        Response response = given().filters(new DVLABasePathFilter())
                .contentType(ContentType.JSON)
                .body(alteredBody)
                .log().method().log().uri().log().body()
                .post("/v1/trailers");

        return response;
    }

    public Response postTrailerRegistrationWithAlterationsDVLA(String body, List<JsonPathAlteration> alterations) {
        Response response = callPostTrailerRegistrationWithAlterationsDVLA(body, alterations);

        if (response.getStatusCode() == 401 || response.getStatusCode() == 403) {
            saveUtils();
            response = callPostTrailerRegistrationWithAlterationsDVLA(body, alterations);
        }

        return response;
    }

}
