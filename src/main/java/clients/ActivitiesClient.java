package clients;

import clients.util.ToTypeConvertor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import exceptions.AutomationException;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import model.Activities;
import util.BasePathFilter;

import static io.restassured.RestAssured.given;

public class ActivitiesClient {

    public Response postActivities(Activities activities) {

        Response response;
        if (activities != null) {
            response = given().filters(new BasePathFilter())
                    .contentType(ContentType.JSON)
                    .body(activities)
                    .post("/activities");

        } else {
            response = given().filters(new BasePathFilter())
                    .contentType(ContentType.JSON)
                    .body("{ }")
                    .post("/activities");
        }

        response.prettyPrint();
        return response;
    }


    public Response postActivitiesFieldChange(Activities activities, String propertyField, String value, ToTypeConvertor toType) {

        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode node = objectMapper.valueToTree(activities);


        switch (toType) {
            case INTEGER:
                node.put(propertyField, Integer.valueOf(value));
                break;
            case NULL:
                node.putNull(propertyField);
                break;
            case MISSING:
                node.remove(propertyField);
                break;
            case NEW_PROPERTY:
                node.put(propertyField,value);
                break;
            default:
                throw new AutomationException("Convert type not supported for Activities");

        }

        Response response = given().filters(new BasePathFilter())
                .contentType(ContentType.JSON)
                .body(node)
                .post("/activities");


        return response;
    }


    public Response putActivities(String id) {

        Response response = given().filters(new BasePathFilter())
                .contentType(ContentType.JSON)
                .pathParam("id", id)
                .put("/activities/{id}/end");


        return response;
    }


}
