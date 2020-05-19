package integration;

import data.GenericData;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import net.thucydides.core.annotations.WithTag;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.junit.Test;
import org.junit.runner.RunWith;
import steps.ActivitiesSteps;
import util.JsonPathAlteration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@RunWith(SerenityRunner.class)
public class PutActivitiesCloudWatchLogs {
    @Steps
    ActivitiesSteps activitiesSteps;

    @WithTag("integration")
    @Title("CVSB-10767 CVS to EDH (Open Site Visits) PUT - AC1 - http status code 202")
    public void insertPutActivityVisitHttpCode202() {
        // read post request body from file
        String postRequestBody = GenericData.readJsonValueFromFile("activities_10769.json","$");
        // generate random ID
        String randomId = UUID.randomUUID().toString();
        // generate random TesterStaffId
        String randomTesterStaffId = UUID.randomUUID().toString();

        DateTime currentTimestamp = DateTime.now().withZone(DateTimeZone.UTC);
        DateTime startTimestamp = currentTimestamp;
        String startTime = startTimestamp.toInstant().toString();
        String startTimeGet = startTimestamp.minusSeconds(1).toInstant().toString();

        // create alterations
        JsonPathAlteration alterationId = new JsonPathAlteration("$.id", randomId,"","REPLACE");
        JsonPathAlteration alterationTesterStaffId = new JsonPathAlteration("$.testerStaffId",
                randomTesterStaffId,"","REPLACE");
        JsonPathAlteration alterationStartTime = new JsonPathAlteration("$.startTime",startTime ,"","REPLACE");

        // initialize the alterations list with both declared alteration
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(
                alterationId,
                alterationStartTime,
                alterationTesterStaffId));

        // inserting a new open visit
        activitiesSteps.insertActivityWithAlterations(postRequestBody, alterations);
        // check the inserted activity was inserted correctly
        activitiesSteps.getActivities("visit", randomTesterStaffId, null, startTimeGet , null);
        activitiesSteps.statusCodeShouldBe(200);
        // end the inserted activity (PUT to /activities/{id}/end)
        activitiesSteps.putActivitiesEnd(randomId);
        activitiesSteps.statusCodeShouldBe(204);
        // check the activity Id is recorded in the marshaller log
        activitiesSteps.checkAwsMarshallerLogContains("id", randomId);
        // check the dispatcher log events for method: 'PUT', id: {id} and statusCode: 202
        activitiesSteps.checkAwsDispatcherLogStatusCodeForSystemNumber("PUT", randomId, 202);
        // delete the test data from the data table
        activitiesSteps.deleteActivity(randomId);

    }

    @WithTag("integration")
    @Title("CVSB-10767 CVS to EDH (Open Site Visits) PUT - AC1 - http status code 400")
    public void insertPutActivityVisitHttpCode400() {
        // read post request body from file
        String postRequestBody = GenericData.readJsonValueFromFile("activities_10769.json","$");
        // generate random ID
        String randomId = "e4404400-7e57-c0de-e400-e4404c0de400";
        // generate random TesterStaffId
        String randomTesterStaffId = UUID.randomUUID().toString();

        DateTime currentTimestamp = DateTime.now().withZone(DateTimeZone.UTC);
        DateTime startTimestamp = currentTimestamp;
        String startTime = startTimestamp.toInstant().toString();
        String startTimeGet = startTimestamp.minusSeconds(1).toInstant().toString();

        // create alterations
        JsonPathAlteration alterationId = new JsonPathAlteration("$.id", randomId,"","REPLACE");
        JsonPathAlteration alterationTesterStaffId = new JsonPathAlteration("$.testerStaffId",
                randomTesterStaffId,"","REPLACE");
        JsonPathAlteration alterationStartTime = new JsonPathAlteration("$.startTime",startTime ,"","REPLACE");

        // initialize the alterations list with both declared alteration
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(
                alterationId,
                alterationStartTime,
                alterationTesterStaffId));

        // delete the test data from the data table
        activitiesSteps.deleteActivity(randomId);
        // inserting a new open visit
        activitiesSteps.insertActivityWithAlterations(postRequestBody, alterations);
        // check the inserted activity was inserted correctly
        activitiesSteps.getActivities("visit", randomTesterStaffId, null, startTimeGet , null);
        activitiesSteps.statusCodeShouldBe(200);
        // end the inserted activity (PUT to /activities/{id}/end)
        activitiesSteps.putActivitiesEnd(randomId);
        activitiesSteps.statusCodeShouldBe(204);
        // check the activity Id is recorded in the marshaller log
        activitiesSteps.checkAwsMarshallerLogContains("id", randomId);
        // check the dispatcher log events for method: 'PUT', id: {id} and statusCode: 400
        activitiesSteps.checkAwsDispatcherLogStatusCodeForSystemNumber("PUT", randomId, 400);
        // delete the test data from the data table
        activitiesSteps.deleteActivity(randomId);

    }

    @WithTag("integration")
    @Title("CVSB-10767 CVS to EDH (Open Site Visits) PUT - AC1 - http status code 401")
    public void insertPutActivityVisitHttpCode401() {
        // read post request body from file
        String postRequestBody = GenericData.readJsonValueFromFile("activities_10769.json","$");
        // generate random ID
        String randomId = "e4404401-7e57-c0de-e401-e4404c0de401";
        // generate random TesterStaffId
        String randomTesterStaffId = UUID.randomUUID().toString();

        DateTime currentTimestamp = DateTime.now().withZone(DateTimeZone.UTC);
        DateTime startTimestamp = currentTimestamp;
        String startTime = startTimestamp.toInstant().toString();
        String startTimeGet = startTimestamp.minusSeconds(1).toInstant().toString();

        // create alterations
        JsonPathAlteration alterationId = new JsonPathAlteration("$.id", randomId,"","REPLACE");
        JsonPathAlteration alterationTesterStaffId = new JsonPathAlteration("$.testerStaffId",
                randomTesterStaffId,"","REPLACE");
        JsonPathAlteration alterationStartTime = new JsonPathAlteration("$.startTime",startTime ,"","REPLACE");

        // initialize the alterations list with both declared alteration
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(
                alterationId,
                alterationStartTime,
                alterationTesterStaffId));

        // delete the test data from the data table
        activitiesSteps.deleteActivity(randomId);
        // inserting a new open visit
        activitiesSteps.insertActivityWithAlterations(postRequestBody, alterations);
        // check the inserted activity was inserted correctly
        activitiesSteps.getActivities("visit", randomTesterStaffId, null, startTimeGet , null);
        activitiesSteps.statusCodeShouldBe(200);
        // end the inserted activity (PUT to /activities/{id}/end)
        activitiesSteps.putActivitiesEnd(randomId);
        activitiesSteps.statusCodeShouldBe(204);
        // check the activity Id is recorded in the marshaller log
        activitiesSteps.checkAwsMarshallerLogContains("id", randomId);
        // check the dispatcher log events for method: 'PUT', id: {id} and statusCode: 401
        activitiesSteps.checkAwsDispatcherLogStatusCodeForSystemNumber("PUT", randomId, 401);
        // delete the test data from the data table
        activitiesSteps.deleteActivity(randomId);

    }

    @WithTag("integration")
    @Title("CVSB-10767 CVS to EDH (Open Site Visits) PUT - AC1 - http status code 403")
    public void insertPutActivityVisitHttpCode403() {
        // read post request body from file
        String postRequestBody = GenericData.readJsonValueFromFile("activities_10769.json","$");
        // generate random ID
        String randomId = "e4404403-7e57-c0de-e403-e4404c0de403";
        // generate random TesterStaffId
        String randomTesterStaffId = UUID.randomUUID().toString();

        DateTime currentTimestamp = DateTime.now().withZone(DateTimeZone.UTC);
        DateTime startTimestamp = currentTimestamp;
        String startTime = startTimestamp.toInstant().toString();
        String startTimeGet = startTimestamp.minusSeconds(1).toInstant().toString();

        // create alterations
        JsonPathAlteration alterationId = new JsonPathAlteration("$.id", randomId,"","REPLACE");
        JsonPathAlteration alterationTesterStaffId = new JsonPathAlteration("$.testerStaffId",
                randomTesterStaffId,"","REPLACE");
        JsonPathAlteration alterationStartTime = new JsonPathAlteration("$.startTime",startTime ,"","REPLACE");

        // initialize the alterations list with both declared alteration
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(
                alterationId,
                alterationStartTime,
                alterationTesterStaffId));

        // delete the test data from the data table
        activitiesSteps.deleteActivity(randomId);
        // inserting a new open visit
        activitiesSteps.insertActivityWithAlterations(postRequestBody, alterations);
        // check the inserted activity was inserted correctly
        activitiesSteps.getActivities("visit", randomTesterStaffId, null, startTimeGet , null);
        activitiesSteps.statusCodeShouldBe(200);
        // end the inserted activity (PUT to /activities/{id}/end)
        activitiesSteps.putActivitiesEnd(randomId);
        activitiesSteps.statusCodeShouldBe(204);
        // check the activity Id is recorded in the marshaller log
        activitiesSteps.checkAwsMarshallerLogContains("id", randomId);
        // check the dispatcher log events for method: 'PUT', id: {id} and statusCode: 403
        activitiesSteps.checkAwsDispatcherLogStatusCodeForSystemNumber("PUT", randomId, 403);
        // delete the test data from the data table
        activitiesSteps.deleteActivity(randomId);
    }

    @WithTag("integration")
    @Title("CVSB-10767 CVS to EDH (Open Site Visits) PUT - AC1 - http status code 404")
    public void insertPutActivityVisitHttpCode404() {
        // read post request body from file
        String postRequestBody = GenericData.readJsonValueFromFile("activities_10769.json","$");
        // generate random ID
        String randomId = "e4404404-7e57-c0de-e404-e4404c0de404";
        // generate random TesterStaffId
        String randomTesterStaffId = UUID.randomUUID().toString();

        DateTime currentTimestamp = DateTime.now().withZone(DateTimeZone.UTC);
        DateTime startTimestamp = currentTimestamp;
        String startTime = startTimestamp.toInstant().toString();
        String startTimeGet = startTimestamp.minusSeconds(1).toInstant().toString();

        // create alterations
        JsonPathAlteration alterationId = new JsonPathAlteration("$.id", randomId,"","REPLACE");
        JsonPathAlteration alterationTesterStaffId = new JsonPathAlteration("$.testerStaffId",
                randomTesterStaffId,"","REPLACE");
        JsonPathAlteration alterationStartTime = new JsonPathAlteration("$.startTime",startTime ,"","REPLACE");

        // initialize the alterations list with both declared alteration
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(
                alterationId,
                alterationStartTime,
                alterationTesterStaffId));

        // delete the test data from the data table
        activitiesSteps.deleteActivity(randomId);
        // inserting a new open visit
        activitiesSteps.insertActivityWithAlterations(postRequestBody, alterations);
        // check the inserted activity was inserted correctly
        activitiesSteps.getActivities("visit", randomTesterStaffId, null, startTimeGet , null);
        activitiesSteps.statusCodeShouldBe(200);
        // end the inserted activity (PUT to /activities/{id}/end)
        activitiesSteps.putActivitiesEnd(randomId);
        activitiesSteps.statusCodeShouldBe(204);
        // check the activity Id is recorded in the marshaller log
        activitiesSteps.checkAwsMarshallerLogContains("id", randomId);
        // check the dispatcher log events for method: 'PUT', id: {id} and statusCode: 404
        activitiesSteps.checkAwsDispatcherLogStatusCodeForSystemNumber("PUT", randomId, 404);
        // delete the test data from the data table
        activitiesSteps.deleteActivity(randomId);
    }

    @WithTag("integration")
    @Title("CVSB-10767 CVS to EDH (Open Site Visits) PUT - AC1 - http status code 429")
    public void insertPutActivityVisitHttpCode429() {
        // read post request body from file
        String postRequestBody = GenericData.readJsonValueFromFile("activities_10769.json","$");
        // generate random ID
        String randomId = "e4404429-7e57-c0de-e429-e4404c0de429";
        // generate random TesterStaffId
        String randomTesterStaffId = UUID.randomUUID().toString();

        DateTime currentTimestamp = DateTime.now().withZone(DateTimeZone.UTC);
        DateTime startTimestamp = currentTimestamp;
        String startTime = startTimestamp.toInstant().toString();
        String startTimeGet = startTimestamp.minusSeconds(1).toInstant().toString();

        // create alterations
        JsonPathAlteration alterationId = new JsonPathAlteration("$.id", randomId,"","REPLACE");
        JsonPathAlteration alterationTesterStaffId = new JsonPathAlteration("$.testerStaffId",
                randomTesterStaffId,"","REPLACE");
        JsonPathAlteration alterationStartTime = new JsonPathAlteration("$.startTime",startTime ,"","REPLACE");

        // initialize the alterations list with both declared alteration
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(
                alterationId,
                alterationStartTime,
                alterationTesterStaffId));

        // delete the test data from the data table
        activitiesSteps.deleteActivity(randomId);
        // inserting a new open visit
        activitiesSteps.insertActivityWithAlterations(postRequestBody, alterations);
        // check the inserted activity was inserted correctly
        activitiesSteps.getActivities("visit", randomTesterStaffId, null, startTimeGet , null);
        activitiesSteps.statusCodeShouldBe(200);
        // end the inserted activity (PUT to /activities/{id}/end)
        activitiesSteps.putActivitiesEnd(randomId);
        activitiesSteps.statusCodeShouldBe(204);
        // check the activity Id is recorded in the marshaller log
        activitiesSteps.checkAwsMarshallerLogContains("id", randomId);
        // check the dispatcher log events for method: 'PUT', id: {id} and statusCode: 429
        activitiesSteps.checkAwsDispatcherLogStatusCodeForSystemNumber("PUT", randomId, 429);
        // delete the test data from the data table
        activitiesSteps.deleteActivity(randomId);

    }

    @WithTag("integration")
    @Title("CVSB-10767 CVS to EDH (Open Site Visits) PUT - AC1 - http status code 500")
    public void insertPutActivityVisitHttpCode500() {
        // read post request body from file
        String postRequestBody = GenericData.readJsonValueFromFile("activities_10769.json","$");
        // generate random ID
        String randomId = "e4404500-7e57-c0de-e500-e4404c0de500";
        // generate random TesterStaffId
        String randomTesterStaffId = UUID.randomUUID().toString();

        DateTime currentTimestamp = DateTime.now().withZone(DateTimeZone.UTC);
        DateTime startTimestamp = currentTimestamp;
        String startTime = startTimestamp.toInstant().toString();
        String startTimeGet = startTimestamp.minusSeconds(1).toInstant().toString();

        // create alterations
        JsonPathAlteration alterationId = new JsonPathAlteration("$.id", randomId,"","REPLACE");
        JsonPathAlteration alterationTesterStaffId = new JsonPathAlteration("$.testerStaffId",
                randomTesterStaffId,"","REPLACE");
        JsonPathAlteration alterationStartTime = new JsonPathAlteration("$.startTime",startTime ,"","REPLACE");

        // initialize the alterations list with both declared alteration
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(
                alterationId,
                alterationStartTime,
                alterationTesterStaffId));

        // delete the test data from the data table
        activitiesSteps.deleteActivity(randomId);
        // inserting a new open visit
        activitiesSteps.insertActivityWithAlterations(postRequestBody, alterations);
        // check the inserted activity was inserted correctly
        activitiesSteps.getActivities("visit", randomTesterStaffId, null, startTimeGet , null);
        activitiesSteps.statusCodeShouldBe(200);
        // end the inserted activity (PUT to /activities/{id}/end)
        activitiesSteps.putActivitiesEnd(randomId);
        activitiesSteps.statusCodeShouldBe(204);
        // check the activity Id is recorded in the marshaller log
        activitiesSteps.checkAwsMarshallerLogContains("id", randomId);
        // check the dispatcher log events for method: 'PUT', id: {id} and statusCode: 500
        activitiesSteps.checkAwsDispatcherLogStatusCodeForSystemNumber("PUT", randomId, 500);
        // delete the test data from the data table
        activitiesSteps.deleteActivity(randomId);

    }

}
