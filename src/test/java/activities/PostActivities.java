package activities;

import data.ActivitiesData;
import data.GenericData;
import model.activities.Activities;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import steps.ActivitiesSteps;
import util.JsonPathAlteration;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


@RunWith(SerenityRunner.class)
public class PostActivities {
    @Steps
    ActivitiesSteps activitiesSteps;

    @Title("CVSB-163 / CVSB-2874 - AC8 API Consumer creates a new activity - activity type: visit")
    @Test
    public void postActivitiesActivityTypeVisit() {
        activitiesSteps.postActivities(ActivitiesData.buildActivitiesIdData().setActivityType("visit").build());
        activitiesSteps.statusCodeShouldBe(201);
        activitiesSteps.responseShouldContainId();
    }

    @Title("CVSB-163 / CVSB-2874 - AC8 API Consumer creates a new activity - activity type: wait")
    @Test
    public void postActivitiesActivityTypeWait() {
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
                ("yyyy-MM-dd HH:mm:ss.SSS").format(date),"","REPLACE");
        // create alteration to change endTime
        JsonPathAlteration alterationEndTime = new JsonPathAlteration("$.endTime",
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(date),
                "","REPLACE");
        // initialize the alterations list with both declared alteration
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(alterationParentId, alterationStartTime,
                alterationEndTime, alterationTestStationPNumber));
        activitiesSteps.postActivitiesParentIdWithAlterations(postRequestBody, alterations);
        activitiesSteps.statusCodeShouldBe(201);
        activitiesSteps.responseShouldContainId();
    }

    @Title("CVSB-179 / CVSB-4545 / CVSB-4547 - Save wait time in the BE (time is Equal to 5 minutes)")
    @Test
    public void postActivitiesActivityTypeWaitTimeIsEqualTo5Minutes() {
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
                ("yyyy-MM-dd HH:mm:ss.SSS").format(date),"","REPLACE");
        // create alteration to change endTime
        JsonPathAlteration alterationEndTime = new JsonPathAlteration("$.endTime",
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(DateUtils.addMinutes(date, 5)),
                "","REPLACE");
        // initialize the alterations list with both declared alteration
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(alterationParentId, alterationStartTime,
                alterationEndTime, alterationTestStationPNumber));
        activitiesSteps.postActivitiesParentIdWithAlterations(postRequestBody, alterations);
        activitiesSteps.statusCodeShouldBe(201);
        activitiesSteps.responseShouldContainId();
    }

    @Title("CVSB-179 / CVSB-4544 / CVSB-4543 - Save wait time in the BE (time is Greater than 5 minutes)")
    @Test
    public void postActivitiesActivityTypeWaitTimeIsGreaterThan5Minutes() {
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
                ("yyyy-MM-dd HH:mm:ss.SSS").format(date),"","REPLACE");
        // create alteration to change endTime
        JsonPathAlteration alterationEndTime = new JsonPathAlteration("$.endTime",
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(DateUtils.addMinutes(date, 6)),
                "","REPLACE");
        // initialize the alterations list with both declared alteration
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(alterationParentId, alterationStartTime,
                alterationEndTime, alterationTestStationPNumber));
        activitiesSteps.postActivitiesParentIdWithAlterations(postRequestBody, alterations);
        activitiesSteps.statusCodeShouldBe(201);
        activitiesSteps.responseShouldContainId();
    }

    @Title("CVSB-179 / CVSB-4545 / CVSB-4547 / CVSB-4551 / CVSB-4552 / CVSB-4554 / CVSB-4556 - Save wait time in the BE (time is Equal to 5 minutes)")
    @Test
    public void postActivitiesActivityTypeWaitTimeIsEqualTo5MinutesPreviousTime() {
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
                ("yyyy-MM-dd HH:mm:ss.SSS").format(date),"","REPLACE");
        // create alteration to change endTime
        JsonPathAlteration alterationEndTime = new JsonPathAlteration("$.endTime",
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(DateUtils.addMinutes(date, 5)),
                "","REPLACE");
        // initialize the alterations list with both declared alteration
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(alterationParentId, alterationStartTime,
                alterationEndTime, alterationTestStationPNumber));
        activitiesSteps.postActivitiesParentIdWithAlterations(postRequestBody, alterations);
        activitiesSteps.statusCodeShouldBe(201);
        activitiesSteps.responseShouldContainId();
    }

    @Title("CVSB-179 / CVSB-4548 / CVSB-4543 / CVSB-4549 / CVSB-4553 / CVSB-4555 - Save wait time in the BE (time is Greater than 5 minutes)")
    @Test
    public void postActivitiesActivityTypeWaitTimeIsGreaterThan5MinutesPreviousTime() {
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
                ("yyyy-MM-dd HH:mm:ss.SSS").format(date),"","REPLACE");
        // create alteration to change endTime
        JsonPathAlteration alterationEndTime = new JsonPathAlteration("$.endTime",
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(DateUtils.addMinutes(date, 6)),
                "","REPLACE");
        // initialize the alterations list with both declared alteration
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(alterationParentId, alterationStartTime,
                alterationEndTime, alterationTestStationPNumber));
        activitiesSteps.postActivitiesParentIdWithAlterations(postRequestBody, alterations);
        activitiesSteps.statusCodeShouldBe(201);
        activitiesSteps.responseShouldContainId();
    }

    @Title("CVSB-179 / CVSB-4557 / CVSB-4558 / CVSB-4562 - AC M5 Save wait time in the BE")
    @Test
    public void postActivitiesActivityTypeUnaccountableTime() {
        activitiesSteps.postActivities(ActivitiesData.buildActivitiesIdData().setActivityType("visit").build());
        String parentId =  activitiesSteps.checkAndGetResponseId();
        // read post request body from file
        String postRequestBody = GenericData.readJsonValueFromFile("activities_parent_id.json","$");
        // create alteration to change activityType
        JsonPathAlteration alterationActivityType = new JsonPathAlteration("$.activityType",
                "unaccountable time","","REPLACE");
        // create alteration to change parentId
        JsonPathAlteration alterationParentId = new JsonPathAlteration("$.parentId", parentId,"","REPLACE");
        // create alteration to change testStationPNumber
        JsonPathAlteration alterationTestStationPNumber = new JsonPathAlteration("$.testStationPNumber",
                "35-7138320","","REPLACE");
        // create alteration to change startTime
        JsonPathAlteration alterationStartTime = new JsonPathAlteration("$.startTime", new SimpleDateFormat
                ("yyyy-MM-dd HH:mm:ss.SSS").format(new Date()),"","REPLACE");
        // create alteration to change endTime
        JsonPathAlteration alterationEndTime = new JsonPathAlteration("$.endTime", new SimpleDateFormat
                ("yyyy-MM-dd HH:mm:ss.SSS").format(new Date()),"","REPLACE");
        // initialize the alterations list with both declared alteration
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(alterationParentId, alterationStartTime,
                alterationEndTime, alterationActivityType, alterationTestStationPNumber));
        activitiesSteps.postActivitiesParentIdWithAlterations(postRequestBody, alterations);
        activitiesSteps.statusCodeShouldBe(201);
        activitiesSteps.responseShouldContainId();
    }

    @Title("CVSB-179 / CVSB-4559 / CVSB-4560 / CVSB-4561 /  - AC M6 Save wait time in the BE")
    @Test
    public void postActivitiesActivityTypeUnaccountableTimePreviousTime() {
        activitiesSteps.postActivities(ActivitiesData.buildActivitiesIdData().setActivityType("visit").build());
        String parentId =  activitiesSteps.checkAndGetResponseId();
        // read post request body from file
        String postRequestBody = GenericData.readJsonValueFromFile("activities_parent_id.json","$");
        // create alteration to change activityType
        JsonPathAlteration alterationActivityType = new JsonPathAlteration("$.activityType", "unaccountable time","","REPLACE");
        // create alteration to change parentId
        JsonPathAlteration alterationParentId = new JsonPathAlteration("$.parentId", parentId,"","REPLACE");
        // create alteration to change testStationPNumber
        JsonPathAlteration alterationTestStationPNumber = new JsonPathAlteration("$.testStationPNumber",
                "35-7138320","","REPLACE");
        Date date  = new Date();
        // create alteration to change startTime
        JsonPathAlteration alterationStartTime = new JsonPathAlteration("$.startTime", new SimpleDateFormat
                ("yyyy-MM-dd HH:mm:ss.SSS").format(date),"","REPLACE");
        // create alteration to change endTime
        JsonPathAlteration alterationEndTime = new JsonPathAlteration("$.endTime",
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(DateUtils.addMinutes(date, 1)),
                "","REPLACE");
        // initialize the alterations list with both declared alteration
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(alterationParentId, alterationStartTime,
                alterationEndTime, alterationActivityType, alterationTestStationPNumber));
        activitiesSteps.postActivitiesParentIdWithAlterations(postRequestBody, alterations);
        activitiesSteps.statusCodeShouldBe(201);
        activitiesSteps.responseShouldContainId();
    }

    @Title("CVSB-163 / CVSB-2874 - AC8 API Consumer creates a new activity - test station type: atf")
    @Test
    public void postActivitiesTestStationTypeAtf() {
        activitiesSteps.postActivities(ActivitiesData.buildActivitiesIdData().setTestStationType("atf").build());
        activitiesSteps.statusCodeShouldBe(201);
        activitiesSteps.responseShouldContainId();
    }

    @Title("CVSB-163 / CVSB-2874 - AC8 API Consumer creates a new activity - test station type: gvts")
    @Test
    public void postActivitiesTestStationTypeGvts() {
        activitiesSteps.postActivities(ActivitiesData.buildActivitiesIdData().setTestStationType("gvts").build());
        activitiesSteps.statusCodeShouldBe(201);
        activitiesSteps.responseShouldContainId();
    }

    @Title("CVSB-163 / CVSB-2874 - AC8 API Consumer creates a new activity - test station type: hq")
    @Test
    public void postActivitiesTestStationTypeHq() {
        activitiesSteps.postActivities(ActivitiesData.buildActivitiesIdData().setTestStationType("hq").build());
        activitiesSteps.statusCodeShouldBe(201);
        activitiesSteps.responseShouldContainId();
    }


    @Title("CVSB-163 / CVSB-2928 -  API Consumer with ended activity creates a new activity")
    @Test
    public void postActivitiesAfterActivityEnded() {

        Activities activitiesData = ActivitiesData.buildActivitiesIdData().build();

        activitiesSteps.postActivities(activitiesData);
        activitiesSteps.statusCodeShouldBe(201);
        activitiesSteps.responseShouldContainId();
        String id = activitiesSteps.checkAndGetResponseId();
        activitiesSteps.putActivitiesEnd(id);
        activitiesSteps.statusCodeShouldBe(204);
        activitiesSteps.postActivities(activitiesData);
        activitiesSteps.statusCodeShouldBe(201);
        activitiesSteps.responseShouldContainId();

    }

}
