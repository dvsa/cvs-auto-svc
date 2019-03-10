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
import static util.WriterReader.saveUtils;

public class ActivitiesClient {

    public Response postActivities(Activities activities) {

        Response response;
        if (activities != null) {
            response = callPostActivities(activities);

            if (response.getStatusCode() == 401 || response.getStatusCode() == 403) {
                saveUtils();
                response = callPostActivities(activities);
            }

        } else {
            response = callPostActivities("{ }");

            if (response.getStatusCode() == 401 || response.getStatusCode() == 403) {
                saveUtils();
                response = callPostActivities("{ }");
            }
        }

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
                node.put(propertyField, value);
                break;
            default:
                throw new AutomationException("Convert type not supported for Activities");

        }

        Response response = callPostActivities(node);

        if (response.getStatusCode() == 401 || response.getStatusCode() == 403) {
            saveUtils();
            response = callPostActivities(node);
        }

        return response;
    }


    public Response putActivities(String id) {

        Response response = callPutActivities(id);

        if (response.getStatusCode() == 401 || response.getStatusCode() == 403) {
            saveUtils();
            response = callPutActivities(id);
        }

        return response;

    }


    private Response callPostActivities(Object object) {

        Response response = given().filters(new BasePathFilter())
                .contentType(ContentType.JSON)
                .body(object)
                .post("/activities");

        return response;
    }

    private Response callPutActivities(String id) {

        Response response = given().filters(new BasePathFilter())
                .contentType(ContentType.JSON)
                .pathParam("id", id)
                .put("/activities/{id}/end");

        return response;
    }


}
