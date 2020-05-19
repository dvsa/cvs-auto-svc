package integration;

import data.ActivitiesData;
import data.GenericData;
import model.activities.ActivitiesGet;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import net.thucydides.core.annotations.WithTag;
import org.apache.commons.lang3.time.DateUtils;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.junit.Test;
import org.junit.runner.RunWith;
import steps.ActivitiesSteps;
import util.JsonPathAlteration;

import java.text.SimpleDateFormat;
import java.util.*;


@RunWith(SerenityRunner.class)
public class PostActivitiesCloudWatchLogs {
    @Steps
    ActivitiesSteps activitiesSteps;
    ActivitiesGet.Builder activitiesData = ActivitiesData.buildActivitiesIdData();


    @WithTag("integration")
    @Title("CVSB-10767 CVS to EDH (Open Site Visits) POST - AC1 - http status code 202")
    public void insertPostActivityVisitHttpCode202() {
        // read post request body from file
        String postRequestBody = GenericData.readJsonValueFromFile("activities_10767.json","$");
        // generate random ID
        String randomId = UUID.randomUUID().toString();
        // generate random TesterStaffId
        String randomTesterStaffId = UUID.randomUUID().toString();

        DateTime currentTimestamp = DateTime.now().withZone(DateTimeZone.UTC);
        DateTime startTimestamp = currentTimestamp;
        DateTime endTimestamp = currentTimestamp.plusHours(1);
        String startTime = startTimestamp.toInstant().toString();
        String endTime = endTimestamp.toInstant().toString();

        String startTimeGet = startTimestamp.minusSeconds(1).toInstant().toString();
        String endTimeGet = endTimestamp.plusSeconds(1).toInstant().toString();

        // generate endTime

        // create alterations
        JsonPathAlteration alterationId = new JsonPathAlteration("$.id", randomId,"","REPLACE");
        JsonPathAlteration alterationTesterStaffId = new JsonPathAlteration("$.testerStaffId",
                randomTesterStaffId,"","REPLACE");
        JsonPathAlteration alterationStartTime = new JsonPathAlteration("$.startTime",startTime ,"","REPLACE");
        JsonPathAlteration alterationEndTime = new JsonPathAlteration("$.endTime", endTime,"","REPLACE");

        // initialize the alterations list with both declared alteration
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(
                alterationId,
                alterationStartTime,
                alterationEndTime,
                alterationTesterStaffId));


        activitiesSteps.insertActivityWithAlterations(postRequestBody, alterations);
        activitiesSteps.getActivities("visit", randomTesterStaffId, null, startTimeGet, endTimeGet);
        activitiesSteps.statusCodeShouldBe(200);
        activitiesSteps.checkAwsMarshallerLogContains("id", randomId);
        activitiesSteps.checkAwsDispatcherLogStatusCodeForSystemNumber(randomId, 202);
        activitiesSteps.deleteActivity(randomId);

    }

    @WithTag("integration")
    @Title("CVSB-10767 CVS to EDH (Open Site Visits) POST - AC1 - http status code 400")
    public void insertPostActivityVisitHttpCode400() {
        // read post request body from file
        String postRequestBody = GenericData.readJsonValueFromFile("activities_10767.json","$");
        // generate random ID
        String randomId = "e4404400-7e57-c0de-e400-e4404c0de400";
        // generate random TesterStaffId
        String randomTesterStaffId = UUID.randomUUID().toString();

        DateTime currentTimestamp = DateTime.now().withZone(DateTimeZone.UTC);
        DateTime startTimestamp = currentTimestamp;
        DateTime endTimestamp = currentTimestamp.plusHours(1);
        String startTime = startTimestamp.toInstant().toString();
        String endTime = endTimestamp.toInstant().toString();

        String startTimeGet = startTimestamp.minusSeconds(1).toInstant().toString();
        String endTimeGet = endTimestamp.plusSeconds(1).toInstant().toString();

        // generate endTime

        // create alterations
        JsonPathAlteration alterationId = new JsonPathAlteration("$.id", randomId,"","REPLACE");
        JsonPathAlteration alterationTesterStaffId = new JsonPathAlteration("$.testerStaffId",
                randomTesterStaffId,"","REPLACE");
        JsonPathAlteration alterationStartTime = new JsonPathAlteration("$.startTime",startTime ,"","REPLACE");
        JsonPathAlteration alterationEndTime = new JsonPathAlteration("$.endTime", endTime,"","REPLACE");

        // initialize the alterations list with both declared alteration
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(
                alterationId,
                alterationStartTime,
                alterationEndTime,
                alterationTesterStaffId));

        activitiesSteps.deleteActivity(randomId);
        activitiesSteps.insertActivityWithAlterations(postRequestBody, alterations);
        activitiesSteps.getActivities("visit", randomTesterStaffId, null, startTimeGet, endTimeGet);
        activitiesSteps.statusCodeShouldBe(200);
        activitiesSteps.checkAwsMarshallerLogContains("id", randomId);
        activitiesSteps.checkAwsDispatcherLogStatusCodeForSystemNumber(randomId, 400);
        activitiesSteps.deleteActivity(randomId);

    }

    @WithTag("integration")
    @Title("CVSB-10767 CVS to EDH (Open Site Visits) POST - AC1 - http status code 401")
    public void insertPostActivityVisitHttpCode401() {
        // read post request body from file
        String postRequestBody = GenericData.readJsonValueFromFile("activities_10767.json","$");
        // generate random ID
        String randomId = "e4404401-7e57-c0de-e401-e4404c0de401";
        // generate random TesterStaffId
        String randomTesterStaffId = UUID.randomUUID().toString();

        DateTime currentTimestamp = DateTime.now().withZone(DateTimeZone.UTC);
        DateTime startTimestamp = currentTimestamp;
        DateTime endTimestamp = currentTimestamp.plusHours(1);
        String startTime = startTimestamp.toInstant().toString();
        String endTime = endTimestamp.toInstant().toString();

        String startTimeGet = startTimestamp.minusSeconds(1).toInstant().toString();
        String endTimeGet = endTimestamp.plusSeconds(1).toInstant().toString();

        // generate endTime

        // create alterations
        JsonPathAlteration alterationId = new JsonPathAlteration("$.id", randomId,"","REPLACE");
        JsonPathAlteration alterationTesterStaffId = new JsonPathAlteration("$.testerStaffId",
                randomTesterStaffId,"","REPLACE");
        JsonPathAlteration alterationStartTime = new JsonPathAlteration("$.startTime",startTime ,"","REPLACE");
        JsonPathAlteration alterationEndTime = new JsonPathAlteration("$.endTime", endTime,"","REPLACE");

        // initialize the alterations list with both declared alteration
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(
                alterationId,
                alterationStartTime,
                alterationEndTime,
                alterationTesterStaffId));

        activitiesSteps.deleteActivity(randomId);
        activitiesSteps.insertActivityWithAlterations(postRequestBody, alterations);
        activitiesSteps.getActivities("visit", randomTesterStaffId, null, startTimeGet, endTimeGet);
        activitiesSteps.statusCodeShouldBe(200);
        activitiesSteps.checkAwsMarshallerLogContains("id", randomId);
        activitiesSteps.checkAwsDispatcherLogStatusCodeForSystemNumber(randomId, 401);
        activitiesSteps.deleteActivity(randomId);

    }

    @WithTag("integration")
    @Title("CVSB-10767 CVS to EDH (Open Site Visits) POST - AC1 - http status code 403")
    public void insertPostActivityVisitHttpCode403() {
        // read post request body from file
        String postRequestBody = GenericData.readJsonValueFromFile("activities_10767.json","$");
        // generate random ID
        String randomId = "e4404403-7e57-c0de-e403-e4404c0de403";
        // generate random TesterStaffId
        String randomTesterStaffId = UUID.randomUUID().toString();

        DateTime currentTimestamp = DateTime.now().withZone(DateTimeZone.UTC);
        DateTime startTimestamp = currentTimestamp;
        DateTime endTimestamp = currentTimestamp.plusHours(1);
        String startTime = startTimestamp.toInstant().toString();
        String endTime = endTimestamp.toInstant().toString();

        String startTimeGet = startTimestamp.minusSeconds(1).toInstant().toString();
        String endTimeGet = endTimestamp.plusSeconds(1).toInstant().toString();

        // generate endTime

        // create alterations
        JsonPathAlteration alterationId = new JsonPathAlteration("$.id", randomId,"","REPLACE");
        JsonPathAlteration alterationTesterStaffId = new JsonPathAlteration("$.testerStaffId",
                randomTesterStaffId,"","REPLACE");
        JsonPathAlteration alterationStartTime = new JsonPathAlteration("$.startTime",startTime ,"","REPLACE");
        JsonPathAlteration alterationEndTime = new JsonPathAlteration("$.endTime", endTime,"","REPLACE");

        // initialize the alterations list with both declared alteration
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(
                alterationId,
                alterationStartTime,
                alterationEndTime,
                alterationTesterStaffId));


        activitiesSteps.deleteActivity(randomId);
        activitiesSteps.insertActivityWithAlterations(postRequestBody, alterations);
        activitiesSteps.getActivities("visit", randomTesterStaffId, null, startTimeGet, endTimeGet);
        activitiesSteps.statusCodeShouldBe(200);
        activitiesSteps.checkAwsMarshallerLogContains("id", randomId);
        activitiesSteps.checkAwsDispatcherLogStatusCodeForSystemNumber(randomId, 403);
        activitiesSteps.deleteActivity(randomId);

    }

    @WithTag("integration")
    @Title("CVSB-10767 CVS to EDH (Open Site Visits) POST - AC1 - http status code 404")
    public void insertPostActivityVisitHttpCode404() {
        // read post request body from file
        String postRequestBody = GenericData.readJsonValueFromFile("activities_10767.json","$");
        // generate random ID
        String randomId = "e4404404-7e57-c0de-e404-e4404c0de404";
        // generate random TesterStaffId
        String randomTesterStaffId = UUID.randomUUID().toString();

        DateTime currentTimestamp = DateTime.now().withZone(DateTimeZone.UTC);
        DateTime startTimestamp = currentTimestamp;
        DateTime endTimestamp = currentTimestamp.plusHours(1);
        String startTime = startTimestamp.toInstant().toString();
        String endTime = endTimestamp.toInstant().toString();

        String startTimeGet = startTimestamp.minusSeconds(1).toInstant().toString();
        String endTimeGet = endTimestamp.plusSeconds(1).toInstant().toString();

        // generate endTime

        // create alterations
        JsonPathAlteration alterationId = new JsonPathAlteration("$.id", randomId,"","REPLACE");
        JsonPathAlteration alterationTesterStaffId = new JsonPathAlteration("$.testerStaffId",
                randomTesterStaffId,"","REPLACE");
        JsonPathAlteration alterationStartTime = new JsonPathAlteration("$.startTime",startTime ,"","REPLACE");
        JsonPathAlteration alterationEndTime = new JsonPathAlteration("$.endTime", endTime,"","REPLACE");

        // initialize the alterations list with both declared alteration
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(
                alterationId,
                alterationStartTime,
                alterationEndTime,
                alterationTesterStaffId));

        activitiesSteps.deleteActivity(randomId);
        activitiesSteps.insertActivityWithAlterations(postRequestBody, alterations);
        activitiesSteps.getActivities("visit", randomTesterStaffId, null, startTimeGet, endTimeGet);
        activitiesSteps.statusCodeShouldBe(200);
        activitiesSteps.checkAwsMarshallerLogContains("id", randomId);
        activitiesSteps.checkAwsDispatcherLogStatusCodeForSystemNumber(randomId, 404);
        activitiesSteps.deleteActivity(randomId);

    }

    @WithTag("integration")
    @Title("CVSB-10767 CVS to EDH (Open Site Visits) POST - AC1 - http status code 429")
    public void insertPostActivityVisitHttpCode429() {
        // read post request body from file
        String postRequestBody = GenericData.readJsonValueFromFile("activities_10767.json","$");
        // generate random ID
        String randomId = "e4404429-7e57-c0de-e429-e4404c0de429";
        // generate random TesterStaffId
        String randomTesterStaffId = UUID.randomUUID().toString();

        DateTime currentTimestamp = DateTime.now().withZone(DateTimeZone.UTC);
        DateTime startTimestamp = currentTimestamp;
        DateTime endTimestamp = currentTimestamp.plusHours(1);
        String startTime = startTimestamp.toInstant().toString();
        String endTime = endTimestamp.toInstant().toString();

        String startTimeGet = startTimestamp.minusSeconds(1).toInstant().toString();
        String endTimeGet = endTimestamp.plusSeconds(1).toInstant().toString();

        // generate endTime

        // create alterations
        JsonPathAlteration alterationId = new JsonPathAlteration("$.id", randomId,"","REPLACE");
        JsonPathAlteration alterationTesterStaffId = new JsonPathAlteration("$.testerStaffId",
                randomTesterStaffId,"","REPLACE");
        JsonPathAlteration alterationStartTime = new JsonPathAlteration("$.startTime",startTime ,"","REPLACE");
        JsonPathAlteration alterationEndTime = new JsonPathAlteration("$.endTime", endTime,"","REPLACE");

        // initialize the alterations list with both declared alteration
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(
                alterationId,
                alterationStartTime,
                alterationEndTime,
                alterationTesterStaffId));

        activitiesSteps.deleteActivity(randomId);
        activitiesSteps.insertActivityWithAlterations(postRequestBody, alterations);
        activitiesSteps.getActivities("visit", randomTesterStaffId, null, startTimeGet, endTimeGet);
        activitiesSteps.statusCodeShouldBe(200);
        activitiesSteps.checkAwsMarshallerLogContains("id", randomId);
        activitiesSteps.checkAwsDispatcherLogStatusCodeForSystemNumber(randomId, 429);
        activitiesSteps.deleteActivity(randomId);

    }

    @WithTag("integration")
    @Title("CVSB-10767 CVS to EDH (Open Site Visits) POST - AC1 - http status code 500")
    public void insertPostActivityVisitHttpCode500() {
        // read post request body from file
        String postRequestBody = GenericData.readJsonValueFromFile("activities_10767.json","$");
        // generate random ID
        String randomId = "e4404500-7e57-c0de-e500-e4404c0de500";
        // generate random TesterStaffId
        String randomTesterStaffId = UUID.randomUUID().toString();

        DateTime currentTimestamp = DateTime.now().withZone(DateTimeZone.UTC);
        DateTime startTimestamp = currentTimestamp;
        DateTime endTimestamp = currentTimestamp.plusHours(1);
        String startTime = startTimestamp.toInstant().toString();
        String endTime = endTimestamp.toInstant().toString();

        String startTimeGet = startTimestamp.minusSeconds(1).toInstant().toString();
        String endTimeGet = endTimestamp.plusSeconds(1).toInstant().toString();

        // generate endTime

        // create alterations
        JsonPathAlteration alterationId = new JsonPathAlteration("$.id", randomId,"","REPLACE");
        JsonPathAlteration alterationTesterStaffId = new JsonPathAlteration("$.testerStaffId",
                randomTesterStaffId,"","REPLACE");
        JsonPathAlteration alterationStartTime = new JsonPathAlteration("$.startTime",startTime ,"","REPLACE");
        JsonPathAlteration alterationEndTime = new JsonPathAlteration("$.endTime", endTime,"","REPLACE");

        // initialize the alterations list with both declared alteration
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(
                alterationId,
                alterationStartTime,
                alterationEndTime,
                alterationTesterStaffId));

        activitiesSteps.deleteActivity(randomId);
        activitiesSteps.insertActivityWithAlterations(postRequestBody, alterations);
        activitiesSteps.getActivities("visit", randomTesterStaffId, null, startTimeGet, endTimeGet);
        activitiesSteps.statusCodeShouldBe(200);
        activitiesSteps.checkAwsMarshallerLogContains("id", randomId);
        activitiesSteps.checkAwsDispatcherLogStatusCodeForSystemNumber(randomId, 500);
        activitiesSteps.deleteActivity(randomId);

    }



    @WithTag("integration")
    @Title("CVSB-10779 - CVS to EDH (Wait times) POST - AC1 - http status code 202")
    public void insertPostActivityTimeHttpCode202() {
        activitiesSteps.postActivities(ActivitiesData.buildActivitiesIdData().setActivityType("visit").build());
        String parentId =  activitiesSteps.checkAndGetResponseId();
        // read post request body from file
        String postRequestBody = GenericData.readJsonValueFromFile("activities_parent_id.json","$");
        // create alteration to change parentId
        JsonPathAlteration alterationParentId = new JsonPathAlteration("$.parentId", parentId,"","REPLACE");
        // create alteration to change testStationPNumber
        JsonPathAlteration alterationTestStationPNumber = new JsonPathAlteration("$.testStationPNumber",
                "35-7138320","","REPLACE");
        Date date  = new Date();
        // create alteration to change startTime
        JsonPathAlteration alterationStartTime = new JsonPathAlteration("$.startTime", new SimpleDateFormat
                ("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(date),"","REPLACE");
        // create alteration to change endTime
        JsonPathAlteration alterationEndTime = new JsonPathAlteration("$.endTime",
                new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 6)),
                "","REPLACE");
        // initialize the alterations list with both declared alteration
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(alterationParentId, alterationStartTime,
                alterationEndTime, alterationTestStationPNumber));

        activitiesSteps.postActivitiesParentIdWithAlterations(postRequestBody, alterations);
        activitiesSteps.statusCodeShouldBe(201);
        activitiesSteps.responseShouldContainId();
        String id =  activitiesSteps.checkAndGetResponseId();
        activitiesSteps.checkAwsDispatcherLogStatusCodeForSystemNumber("POST", id, 202);
        activitiesSteps.deleteActivity(parentId);
        activitiesSteps.deleteActivity(id);
    }

    @WithTag("integration")
    @Title("CVSB-10779 - CVS to EDH (Wait times) POST - AC2 - http status code 400")
    public void insertPostActivityTimeHttpCode400() {
        activitiesSteps.postActivities(ActivitiesData.buildActivitiesIdData().setActivityType("visit").build());
        String parentId =  activitiesSteps.checkAndGetResponseId();
        String id =  "e4404400-7e57-c0de-e400-e4404c0de400";
        // read post request body from file
        String postRequestBody = GenericData.readJsonValueFromFile("activities_parent_id.json","$");
        // create alteration to change parentId
        JsonPathAlteration alterationParentId = new JsonPathAlteration("$.parentId", parentId,"","REPLACE");
        // create alteration to change testStationPNumber
        JsonPathAlteration alterationTestStationPNumber = new JsonPathAlteration("$.testStationPNumber",
                "35-7138320","","REPLACE");
        Date date  = new Date();
        // create alteration to change startTime
        JsonPathAlteration alterationStartTime = new JsonPathAlteration("$.startTime", new SimpleDateFormat
                ("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(date),"","REPLACE");
        // create alteration to change endTime
        JsonPathAlteration alterationEndTime = new JsonPathAlteration("$.endTime",
                new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 6)),
                "","REPLACE");
        JsonPathAlteration alterationId = new JsonPathAlteration("$", id,"id","ADD_FIELD");

        // initialize the alterations list with both declared alteration
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(alterationParentId, alterationStartTime,
                alterationEndTime, alterationTestStationPNumber, alterationId));

        activitiesSteps.insertActivityWithAlterations(postRequestBody, alterations);
        activitiesSteps.checkAwsDispatcherLogStatusCodeForSystemNumber("POST", id, 400);
        activitiesSteps.deleteActivity(parentId);
        activitiesSteps.deleteActivity(id);

    }

    @WithTag("integration")
    @Title("CVSB-10779 - CVS to EDH (Wait times) POST - AC2 - http status code 401")
    public void insertPostActivityTimeHttpCode401() {
        activitiesSteps.postActivities(ActivitiesData.buildActivitiesIdData().setActivityType("visit").build());
        String parentId =  activitiesSteps.checkAndGetResponseId();
        String id =  "e4404401-7e57-c0de-e401-e4404c0de401";
        // read post request body from file
        String postRequestBody = GenericData.readJsonValueFromFile("activities_parent_id.json","$");
        // create alteration to change parentId
        JsonPathAlteration alterationParentId = new JsonPathAlteration("$.parentId", parentId,"","REPLACE");
        // create alteration to change testStationPNumber
        JsonPathAlteration alterationTestStationPNumber = new JsonPathAlteration("$.testStationPNumber",
                "35-7138320","","REPLACE");
        Date date  = new Date();
        // create alteration to change startTime
        JsonPathAlteration alterationStartTime = new JsonPathAlteration("$.startTime", new SimpleDateFormat
                ("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(date),"","REPLACE");
        // create alteration to change endTime
        JsonPathAlteration alterationEndTime = new JsonPathAlteration("$.endTime",
                new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 6)),
                "","REPLACE");
        JsonPathAlteration alterationId = new JsonPathAlteration("$", id,"id","ADD_FIELD");

        // initialize the alterations list with both declared alteration
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(alterationParentId, alterationStartTime,
                alterationEndTime, alterationTestStationPNumber, alterationId));

        activitiesSteps.insertActivityWithAlterations(postRequestBody, alterations);
        activitiesSteps.checkAwsDispatcherLogStatusCodeForSystemNumber("POST", id, 401);
        activitiesSteps.deleteActivity(parentId);
        activitiesSteps.deleteActivity(id);

    }

    @WithTag("integration")
    @Title("CVSB-10779 - CVS to EDH (Wait times) POST - AC2 - http status code 403")
    public void insertPostActivityTimeHttpCode403() {
        activitiesSteps.postActivities(ActivitiesData.buildActivitiesIdData().setActivityType("visit").build());
        String parentId =  activitiesSteps.checkAndGetResponseId();
        String id =  "e4404403-7e57-c0de-e403-e4404c0de403";
        // read post request body from file
        String postRequestBody = GenericData.readJsonValueFromFile("activities_parent_id.json","$");
        // create alteration to change parentId
        JsonPathAlteration alterationParentId = new JsonPathAlteration("$.parentId", parentId,"","REPLACE");
        // create alteration to change testStationPNumber
        JsonPathAlteration alterationTestStationPNumber = new JsonPathAlteration("$.testStationPNumber",
                "35-7138320","","REPLACE");
        Date date  = new Date();
        // create alteration to change startTime
        JsonPathAlteration alterationStartTime = new JsonPathAlteration("$.startTime", new SimpleDateFormat
                ("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(date),"","REPLACE");
        // create alteration to change endTime
        JsonPathAlteration alterationEndTime = new JsonPathAlteration("$.endTime",
                new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 6)),
                "","REPLACE");
        JsonPathAlteration alterationId = new JsonPathAlteration("$", id,"id","ADD_FIELD");

        // initialize the alterations list with both declared alteration
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(alterationParentId, alterationStartTime,
                alterationEndTime, alterationTestStationPNumber, alterationId));

        activitiesSteps.insertActivityWithAlterations(postRequestBody, alterations);
        activitiesSteps.checkAwsDispatcherLogStatusCodeForSystemNumber("POST", id, 403);
        activitiesSteps.deleteActivity(parentId);
        activitiesSteps.deleteActivity(id);

    }

    @WithTag("integration")
    @Title("CVSB-10779 - CVS to EDH (Wait times) POST - AC2 - http status code 404")
    public void insertPostActivityTimeHttpCode404() {
        activitiesSteps.postActivities(ActivitiesData.buildActivitiesIdData().setActivityType("visit").build());
        String parentId =  activitiesSteps.checkAndGetResponseId();
        String id =  "e4404404-7e57-c0de-e404-e4404c0de404";
        // read post request body from file
        String postRequestBody = GenericData.readJsonValueFromFile("activities_parent_id.json","$");
        // create alteration to change parentId
        JsonPathAlteration alterationParentId = new JsonPathAlteration("$.parentId", parentId,"","REPLACE");
        // create alteration to change testStationPNumber
        JsonPathAlteration alterationTestStationPNumber = new JsonPathAlteration("$.testStationPNumber",
                "35-7138320","","REPLACE");
        Date date  = new Date();
        // create alteration to change startTime
        JsonPathAlteration alterationStartTime = new JsonPathAlteration("$.startTime", new SimpleDateFormat
                ("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(date),"","REPLACE");
        // create alteration to change endTime
        JsonPathAlteration alterationEndTime = new JsonPathAlteration("$.endTime",
                new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 6)),
                "","REPLACE");
        JsonPathAlteration alterationId = new JsonPathAlteration("$", id,"id","ADD_FIELD");

        // initialize the alterations list with both declared alteration
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(alterationParentId, alterationStartTime,
                alterationEndTime, alterationTestStationPNumber, alterationId));

        activitiesSteps.insertActivityWithAlterations(postRequestBody, alterations);
        activitiesSteps.checkAwsDispatcherLogStatusCodeForSystemNumber("POST", id, 404);
        activitiesSteps.deleteActivity(parentId);
        activitiesSteps.deleteActivity(id);

    }

    @WithTag("integration")
    @Title("CVSB-10779 - CVS to EDH (Wait times) POST - AC3 - http status code 429")
    public void insertPostActivityTimeHttpCode429() {
        activitiesSteps.postActivities(ActivitiesData.buildActivitiesIdData().setActivityType("visit").build());
        String parentId =  activitiesSteps.checkAndGetResponseId();
        String id =  "e4404429-7e57-c0de-e429-e4404c0de429";
        // read post request body from file
        String postRequestBody = GenericData.readJsonValueFromFile("activities_parent_id.json","$");
        // create alteration to change parentId
        JsonPathAlteration alterationParentId = new JsonPathAlteration("$.parentId", parentId,"","REPLACE");
        // create alteration to change testStationPNumber
        JsonPathAlteration alterationTestStationPNumber = new JsonPathAlteration("$.testStationPNumber",
                "35-7138320","","REPLACE");
        Date date  = new Date();
        // create alteration to change startTime
        JsonPathAlteration alterationStartTime = new JsonPathAlteration("$.startTime", new SimpleDateFormat
                ("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(date),"","REPLACE");
        // create alteration to change endTime
        JsonPathAlteration alterationEndTime = new JsonPathAlteration("$.endTime",
                new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 6)),
                "","REPLACE");
        JsonPathAlteration alterationId = new JsonPathAlteration("$", id,"id","ADD_FIELD");

        // initialize the alterations list with both declared alteration
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(alterationParentId, alterationStartTime,
                alterationEndTime, alterationTestStationPNumber, alterationId));

        activitiesSteps.insertActivityWithAlterations(postRequestBody, alterations);
        activitiesSteps.checkAwsDispatcherLogStatusCodeForSystemNumber("POST", id, 429);
        activitiesSteps.deleteActivity(parentId);
        activitiesSteps.deleteActivity(id);

    }

    @WithTag("integration")
    @Title("CVSB-10779 - CVS to EDH (Wait times) POST - AC3 - http status code 500")
    public void insertPostActivityTimeHttpCode500() {
        activitiesSteps.postActivities(ActivitiesData.buildActivitiesIdData().setActivityType("visit").build());
        String parentId =  activitiesSteps.checkAndGetResponseId();
        String id =  "e4404500-7e57-c0de-e500-e4404c0de500";
        // read post request body from file
        String postRequestBody = GenericData.readJsonValueFromFile("activities_parent_id.json","$");
        // create alteration to change parentId
        JsonPathAlteration alterationParentId = new JsonPathAlteration("$.parentId", parentId,"","REPLACE");
        // create alteration to change testStationPNumber
        JsonPathAlteration alterationTestStationPNumber = new JsonPathAlteration("$.testStationPNumber",
                "35-7138320","","REPLACE");
        Date date  = new Date();
        // create alteration to change startTime
        JsonPathAlteration alterationStartTime = new JsonPathAlteration("$.startTime", new SimpleDateFormat
                ("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(date),"","REPLACE");
        // create alteration to change endTime
        JsonPathAlteration alterationEndTime = new JsonPathAlteration("$.endTime",
                new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 6)),
                "","REPLACE");
        JsonPathAlteration alterationId = new JsonPathAlteration("$", id,"id","ADD_FIELD");

        // initialize the alterations list with both declared alteration
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(alterationParentId, alterationStartTime,
                alterationEndTime, alterationTestStationPNumber, alterationId));

        activitiesSteps.insertActivityWithAlterations(postRequestBody, alterations);
        activitiesSteps.checkAwsDispatcherLogStatusCodeForSystemNumber("POST", id, 500);
        activitiesSteps.deleteActivity(parentId);
        activitiesSteps.deleteActivity(id);

    }

    @WithTag("integration")
    @Title("CVSB-10779 - CVS to EDH (Wait times) PUT - AC4 - http status code 202")
    public void insertPutActivityTimeHttpCode202() {
        activitiesSteps.postActivities(ActivitiesData.buildActivitiesIdData().setActivityType("visit").build());
        String parentId =  activitiesSteps.checkAndGetResponseId();
        // read post request body from file
        String postRequestBody = GenericData.readJsonValueFromFile("activities_parent_id.json","$");
        // create alteration to change parentId
        JsonPathAlteration alterationParentId = new JsonPathAlteration("$.parentId", parentId,"","REPLACE");
        // create alteration to change testStationPNumber
        JsonPathAlteration alterationTestStationPNumber = new JsonPathAlteration("$.testStationPNumber",
                "35-7138320","","REPLACE");
        Date date  = new Date();
        // create alteration to change startTime
        JsonPathAlteration alterationStartTime = new JsonPathAlteration("$.startTime", new SimpleDateFormat
                ("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(date),"","REPLACE");
        // create alteration to change endTime
        JsonPathAlteration alterationEndTime = new JsonPathAlteration("$.endTime",
                new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 6)),
                "","REPLACE");
        // initialize the alterations list with both declared alteration
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(alterationParentId, alterationStartTime,
                alterationEndTime, alterationTestStationPNumber));

        activitiesSteps.postActivitiesParentIdWithAlterations(postRequestBody, alterations);
        activitiesSteps.statusCodeShouldBe(201);
        activitiesSteps.responseShouldContainId();
        String id =  activitiesSteps.checkAndGetResponseId();

        ArrayList<String> reason = new ArrayList<String>();

        reason.add("Waiting for vehicle");

        activitiesSteps.putActivitiesUpdate(ActivitiesData.buildActivitiesUpdateData()
                .setId(id)
                .setWaitReason(reason)
                .setNotes("updated")
                .build()
        );
        activitiesSteps.statusCodeShouldBe(204);
        activitiesSteps.checkAwsDispatcherLogStatusCodeForSystemNumber("PUT", id, 202);
        activitiesSteps.deleteActivity(parentId);
        activitiesSteps.deleteActivity(id);
    }

    @WithTag("integration")
    @Title("CVSB-10779 - CVS to EDH (Wait times) PUT - AC5 - http status code 400")
    public void insertPutActivityTimeHttpCode400() {
        activitiesSteps.postActivities(ActivitiesData.buildActivitiesIdData().setActivityType("visit").build());
        String parentId =  activitiesSteps.checkAndGetResponseId();
        String id =  "e4404400-7e57-c0de-e400-e4404c0de400";
        // read post request body from file
        String postRequestBody = GenericData.readJsonValueFromFile("activities_parent_id.json","$");
        // create alteration to change parentId
        JsonPathAlteration alterationParentId = new JsonPathAlteration("$.parentId", parentId,"","REPLACE");
        // create alteration to change testStationPNumber
        JsonPathAlteration alterationTestStationPNumber = new JsonPathAlteration("$.testStationPNumber",
                "35-7138320","","REPLACE");
        Date date  = new Date();
        // create alteration to change startTime
        JsonPathAlteration alterationStartTime = new JsonPathAlteration("$.startTime", new SimpleDateFormat
                ("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(date),"","REPLACE");
        // create alteration to change endTime
        JsonPathAlteration alterationEndTime = new JsonPathAlteration("$.endTime",
                new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 6)),
                "","REPLACE");
        JsonPathAlteration alterationId = new JsonPathAlteration("$", id,"id","ADD_FIELD");

        // initialize the alterations list with both declared alteration
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(alterationParentId, alterationStartTime,
                alterationEndTime, alterationTestStationPNumber, alterationId));

        activitiesSteps.insertActivityWithAlterations(postRequestBody, alterations);

        ArrayList<String> reason = new ArrayList<String>();

        reason.add("Waiting for vehicle");

        activitiesSteps.putActivitiesUpdate(ActivitiesData.buildActivitiesUpdateData()
                .setId(id)
                .setWaitReason(reason)
                .setNotes("updated")
                .build()
        );
        activitiesSteps.statusCodeShouldBe(204);
        activitiesSteps.checkAwsDispatcherLogStatusCodeForSystemNumber("PUT", id, 400);
        activitiesSteps.deleteActivity(parentId);
        activitiesSteps.deleteActivity(id);
    }

    @WithTag("integration")
    @Title("CVSB-10779 - CVS to EDH (Wait times) PUT - AC5 - http status code 401")
    public void insertPutActivityTimeHttpCode401() {
        activitiesSteps.postActivities(ActivitiesData.buildActivitiesIdData().setActivityType("visit").build());
        String parentId =  activitiesSteps.checkAndGetResponseId();
        String id =  "e4404401-7e57-c0de-e401-e4404c0de401";
        // read post request body from file
        String postRequestBody = GenericData.readJsonValueFromFile("activities_parent_id.json","$");
        // create alteration to change parentId
        JsonPathAlteration alterationParentId = new JsonPathAlteration("$.parentId", parentId,"","REPLACE");
        // create alteration to change testStationPNumber
        JsonPathAlteration alterationTestStationPNumber = new JsonPathAlteration("$.testStationPNumber",
                "35-7138320","","REPLACE");
        Date date  = new Date();
        // create alteration to change startTime
        JsonPathAlteration alterationStartTime = new JsonPathAlteration("$.startTime", new SimpleDateFormat
                ("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(date),"","REPLACE");
        // create alteration to change endTime
        JsonPathAlteration alterationEndTime = new JsonPathAlteration("$.endTime",
                new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 6)),
                "","REPLACE");
        JsonPathAlteration alterationId = new JsonPathAlteration("$", id,"id","ADD_FIELD");

        // initialize the alterations list with both declared alteration
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(alterationParentId, alterationStartTime,
                alterationEndTime, alterationTestStationPNumber, alterationId));

        activitiesSteps.insertActivityWithAlterations(postRequestBody, alterations);

        ArrayList<String> reason = new ArrayList<String>();

        reason.add("Waiting for vehicle");

        activitiesSteps.putActivitiesUpdate(ActivitiesData.buildActivitiesUpdateData()
                .setId(id)
                .setWaitReason(reason)
                .setNotes("updated")
                .build()
        );
        activitiesSteps.statusCodeShouldBe(204);

        activitiesSteps.checkAwsDispatcherLogStatusCodeForSystemNumber("PUT", id, 401);
        activitiesSteps.deleteActivity(parentId);
        activitiesSteps.deleteActivity(id);

    }

    @WithTag("integration")
    @Title("CVSB-10779 - CVS to EDH (Wait times) PUT - AC5 - http status code 403")
    public void insertPutActivityTimeHttpCode403() {
        activitiesSteps.postActivities(ActivitiesData.buildActivitiesIdData().setActivityType("visit").build());
        String parentId =  activitiesSteps.checkAndGetResponseId();
        String id =  "e4404403-7e57-c0de-e403-e4404c0de403";
        // read post request body from file
        String postRequestBody = GenericData.readJsonValueFromFile("activities_parent_id.json","$");
        // create alteration to change parentId
        JsonPathAlteration alterationParentId = new JsonPathAlteration("$.parentId", parentId,"","REPLACE");
        // create alteration to change testStationPNumber
        JsonPathAlteration alterationTestStationPNumber = new JsonPathAlteration("$.testStationPNumber",
                "35-7138320","","REPLACE");
        Date date  = new Date();
        // create alteration to change startTime
        JsonPathAlteration alterationStartTime = new JsonPathAlteration("$.startTime", new SimpleDateFormat
                ("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(date),"","REPLACE");
        // create alteration to change endTime
        JsonPathAlteration alterationEndTime = new JsonPathAlteration("$.endTime",
                new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 6)),
                "","REPLACE");
        JsonPathAlteration alterationId = new JsonPathAlteration("$", id,"id","ADD_FIELD");

        // initialize the alterations list with both declared alteration
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(alterationParentId, alterationStartTime,
                alterationEndTime, alterationTestStationPNumber, alterationId));

        activitiesSteps.insertActivityWithAlterations(postRequestBody, alterations);
        ArrayList<String> reason = new ArrayList<String>();

        reason.add("Waiting for vehicle");

        activitiesSteps.putActivitiesUpdate(ActivitiesData.buildActivitiesUpdateData()
                .setId(id)
                .setWaitReason(reason)
                .setNotes("updated")
                .build()
        );
        activitiesSteps.statusCodeShouldBe(204);

        activitiesSteps.checkAwsDispatcherLogStatusCodeForSystemNumber("PUT", id, 403);
        activitiesSteps.deleteActivity(parentId);
        activitiesSteps.deleteActivity(id);

    }

    @WithTag("integration")
    @Title("CVSB-10779 - CVS to EDH (Wait times) PUT - AC5 - http status code 404")
    public void insertPutActivityTimeHttpCode404() {
        activitiesSteps.postActivities(ActivitiesData.buildActivitiesIdData().setActivityType("visit").build());
        String parentId =  activitiesSteps.checkAndGetResponseId();
        String id =  "e4404404-7e57-c0de-e404-e4404c0de404";
        // read post request body from file
        String postRequestBody = GenericData.readJsonValueFromFile("activities_parent_id.json","$");
        // create alteration to change parentId
        JsonPathAlteration alterationParentId = new JsonPathAlteration("$.parentId", parentId,"","REPLACE");
        // create alteration to change testStationPNumber
        JsonPathAlteration alterationTestStationPNumber = new JsonPathAlteration("$.testStationPNumber",
                "35-7138320","","REPLACE");
        Date date  = new Date();
        // create alteration to change startTime
        JsonPathAlteration alterationStartTime = new JsonPathAlteration("$.startTime", new SimpleDateFormat
                ("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(date),"","REPLACE");
        // create alteration to change endTime
        JsonPathAlteration alterationEndTime = new JsonPathAlteration("$.endTime",
                new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 6)),
                "","REPLACE");
        JsonPathAlteration alterationId = new JsonPathAlteration("$", id,"id","ADD_FIELD");

        // initialize the alterations list with both declared alteration
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(alterationParentId, alterationStartTime,
                alterationEndTime, alterationTestStationPNumber, alterationId));

        activitiesSteps.insertActivityWithAlterations(postRequestBody, alterations);
        ArrayList<String> reason = new ArrayList<String>();

        reason.add("Waiting for vehicle");

        activitiesSteps.putActivitiesUpdate(ActivitiesData.buildActivitiesUpdateData()
                .setId(id)
                .setWaitReason(reason)
                .setNotes("updated")
                .build()
        );
        activitiesSteps.statusCodeShouldBe(204);
        activitiesSteps.checkAwsDispatcherLogStatusCodeForSystemNumber("PUT", id, 404);
        activitiesSteps.deleteActivity(parentId);
        activitiesSteps.deleteActivity(id);

    }

    @WithTag("integration")
    @Title("CVSB-10779 - CVS to EDH (Wait times) PUT - AC6 - http status code 429")
    public void insertPutActivityTimeHttpCode429() {
        activitiesSteps.postActivities(ActivitiesData.buildActivitiesIdData().setActivityType("visit").build());
        String parentId =  activitiesSteps.checkAndGetResponseId();
        String id =  "e4404429-7e57-c0de-e429-e4404c0de429";
        // read post request body from file
        String postRequestBody = GenericData.readJsonValueFromFile("activities_parent_id.json","$");
        // create alteration to change parentId
        JsonPathAlteration alterationParentId = new JsonPathAlteration("$.parentId", parentId,"","REPLACE");
        // create alteration to change testStationPNumber
        JsonPathAlteration alterationTestStationPNumber = new JsonPathAlteration("$.testStationPNumber",
                "35-7138320","","REPLACE");
        Date date  = new Date();
        // create alteration to change startTime
        JsonPathAlteration alterationStartTime = new JsonPathAlteration("$.startTime", new SimpleDateFormat
                ("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(date),"","REPLACE");
        // create alteration to change endTime
        JsonPathAlteration alterationEndTime = new JsonPathAlteration("$.endTime",
                new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 6)),
                "","REPLACE");
        JsonPathAlteration alterationId = new JsonPathAlteration("$", id,"id","ADD_FIELD");

        // initialize the alterations list with both declared alteration
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(alterationParentId, alterationStartTime,
                alterationEndTime, alterationTestStationPNumber, alterationId));

        activitiesSteps.insertActivityWithAlterations(postRequestBody, alterations);
        ArrayList<String> reason = new ArrayList<String>();

        reason.add("Waiting for vehicle");

        activitiesSteps.putActivitiesUpdate(ActivitiesData.buildActivitiesUpdateData()
                .setId(id)
                .setWaitReason(reason)
                .setNotes("updated")
                .build()
        );
        activitiesSteps.statusCodeShouldBe(204);
        activitiesSteps.checkAwsDispatcherLogStatusCodeForSystemNumber("PUT", id, 429);
        activitiesSteps.deleteActivity(parentId);
        activitiesSteps.deleteActivity(id);

    }

    @WithTag("integration")
    @Title("CVSB-10779 - CVS to EDH (Wait times) PUT - AC6 - http status code 500")
    public void insertPutActivityTimeHttpCode500() {
        activitiesSteps.postActivities(ActivitiesData.buildActivitiesIdData().setActivityType("visit").build());
        String parentId =  activitiesSteps.checkAndGetResponseId();
        String id =  "e4404500-7e57-c0de-e500-e4404c0de500";
        // read post request body from file
        String postRequestBody = GenericData.readJsonValueFromFile("activities_parent_id.json","$");
        // create alteration to change parentId
        JsonPathAlteration alterationParentId = new JsonPathAlteration("$.parentId", parentId,"","REPLACE");
        // create alteration to change testStationPNumber
        JsonPathAlteration alterationTestStationPNumber = new JsonPathAlteration("$.testStationPNumber",
                "35-7138320","","REPLACE");
        Date date  = new Date();
        // create alteration to change startTime
        JsonPathAlteration alterationStartTime = new JsonPathAlteration("$.startTime", new SimpleDateFormat
                ("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(date),"","REPLACE");
        // create alteration to change endTime
        JsonPathAlteration alterationEndTime = new JsonPathAlteration("$.endTime",
                new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(DateUtils.addMinutes(date, 6)),
                "","REPLACE");
        JsonPathAlteration alterationId = new JsonPathAlteration("$", id,"id","ADD_FIELD");

        // initialize the alterations list with both declared alteration
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(alterationParentId, alterationStartTime,
                alterationEndTime, alterationTestStationPNumber, alterationId));

        activitiesSteps.insertActivityWithAlterations(postRequestBody, alterations);
        ArrayList<String> reason = new ArrayList<String>();

        reason.add("Waiting for vehicle");

        activitiesSteps.putActivitiesUpdate(ActivitiesData.buildActivitiesUpdateData()
                .setId(id)
                .setWaitReason(reason)
                .setNotes("updated")
                .build()
        );
        activitiesSteps.statusCodeShouldBe(204);
        activitiesSteps.checkAwsDispatcherLogStatusCodeForSystemNumber("PUT", id, 500);
        activitiesSteps.deleteActivity(parentId);
        activitiesSteps.deleteActivity(id);

    }
}
