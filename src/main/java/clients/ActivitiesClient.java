package clients;

import clients.util.ToTypeConvertor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import exceptions.AutomationException;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import model.activities.Activities;
import model.activities.ActivitiesGet;
import model.activities.ActivitiesPost;
import model.activities.ActivitiesPut;
import util.BasePathFilter;

import java.lang.reflect.Field;

import static io.restassured.RestAssured.given;
import static util.WriterReader.saveUtils;

public class ActivitiesClient {

    public Response postActivities(Activities activities) {

        Response response;

        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode node = objectMapper.valueToTree(activities);

        if (activities != null) {

            for (Field field : ActivitiesGet.class.getDeclaredFields()) {
                node.remove(field.getName());
            }
        }

        if (activities != null) {
            response = callPostActivities(node);

            if (response.getStatusCode() == 401 || response.getStatusCode() == 403) {
                saveUtils();
                response = callPostActivities(node);
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

    public Response postActivitiesWithWaitReason(ActivitiesPost activities) {

        Response response;

        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode node = objectMapper.valueToTree(activities);

        if (activities != null) {
            response = callPostActivities(node);

            if (response.getStatusCode() == 401 || response.getStatusCode() == 403) {
                saveUtils();
                response = callPostActivities(node);
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

    public Response putActivitiesUpdate(ActivitiesPut activities) {

        Response response;

        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode node = objectMapper.valueToTree(activities);

        if (activities != null) {
            response = callPutActivitiesUpdate(node);

            if (response.getStatusCode() == 401 || response.getStatusCode() == 403) {
                saveUtils();
                response = callPutActivitiesUpdate(node);
            }

        } else {
            response = callPutActivitiesUpdate("{ }");

            if (response.getStatusCode() == 401 || response.getStatusCode() == 403) {
                saveUtils();
                response = callPutActivitiesUpdate("{ }");
            }
        }

        return response;
    }


    public Response postActivitiesFieldChange(Activities activities, String propertyField, String value, ToTypeConvertor toType) {

        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode node = objectMapper.valueToTree(activities);

        if (activities != null) {

            for (Field field : ActivitiesGet.class.getDeclaredFields()) {
                node.remove(field.getName());
            }
        }

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


    public Response putActivitiesEnd(String id) {

        Response response = callPutActivitiesEnd(id);

        if (response.getStatusCode() == 401 || response.getStatusCode() == 403) {
            saveUtils();
            response = callPutActivitiesEnd(id);
        }

        return response;

    }

    public Response getActivities(String activityType, String testerStaffId, String testStationPNumber, String fromStartTime, String toStartTime) {

        Response response = callGetActivities(activityType, testerStaffId, testStationPNumber, fromStartTime, toStartTime);

        if (response.getStatusCode() == 401 || response.getStatusCode() == 403) {
            saveUtils();
            response = callGetActivities(activityType, testerStaffId, testStationPNumber, fromStartTime, toStartTime);
        }
        return response;

    }


    private Response callPostActivities(Object object) {

        Response response = given().filters(new BasePathFilter())
                .contentType(ContentType.JSON)
                .body(object)
//                .log().all()
                .log().method().log().uri().log().body()
                .post("/activities");

        return response;
    }

    private Response callPutActivitiesEnd(String id) {

        Response response = given().filters(new BasePathFilter())
                .contentType(ContentType.JSON)
                .pathParam("id", id)
                .put("/activities/{id}/end");

        return response;
    }

    private Response callPutActivitiesUpdate(Object object) {

        Response response = given().filters(new BasePathFilter())
                .contentType(ContentType.JSON)
                .body("[" + object + "]")
                .put("/activities/update");

        return response;
    }


    private Response callGetActivities(String activityType, String testerStaffId, String testStationPNumber, String fromStartTime, String toStartTime) {

        RequestSpecification requestSpecification = given().filters(new BasePathFilter())
                .contentType(ContentType.JSON);

        if (activityType != null) {
            requestSpecification.queryParam("activityType", activityType);
        }

        if (testerStaffId != null) {
            requestSpecification.queryParam("testerStaffId", testerStaffId);
        }

        if (testStationPNumber != null) {
            requestSpecification.queryParam("testStationPNumber", testStationPNumber);
        }

        if (fromStartTime != null) {
            requestSpecification.queryParam("fromStartTime", fromStartTime);
        }

        if (toStartTime != null) {
            requestSpecification.queryParam("toStartTime", toStartTime);
        }

        Response response = requestSpecification
//                .log().all()
                .log().method().log().uri().log().body()
                .get("/activities/details");

        return response;
    }

}
