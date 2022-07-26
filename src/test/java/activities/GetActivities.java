package activities;

import data.ActivitiesData;
import data.GenericData;
import model.activities.ActivitiesGet;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import steps.ActivitiesSteps;
import util.DataUtil;
import util.JsonPathAlteration;

import java.text.SimpleDateFormat;
import java.util.*;

@RunWith(SerenityRunner.class)
public class GetActivities {

    @Steps
    ActivitiesSteps activitiesSteps;

    ActivitiesGet.Builder activitiesData = ActivitiesData.buildActivitiesIdData();


    @Title("CVSB- / CVSB- - AC7 ")
    @Test
    public void postActivitiesActivityTypeVisiRt() {
        activitiesSteps.getActivities("", null, null, "", "");
        activitiesSteps.statusCodeShouldBe(400);
        activitiesSteps.validateData("Bad Request");
    }


    @Title("CVSB- / CVSB- - AC7 ")
    @Test
    public void postActivitiesActivityTypeVisiRts() {
        activitiesSteps.getActivities(RandomStringUtils.randomAlphanumeric(6), null, null, DataUtil.buildCurrentDateTime(-1), DataUtil.buildCurrentDateTime(-1));
        activitiesSteps.statusCodeShouldBe(404);
        activitiesSteps.validateData("No resources match the search criteria");
    }


    @Title("CVSB- / CVSB- - AC7 ")
    @Test
    public void postActivitiesActivityTypeVisiRt2s() {
        activitiesSteps.getActivities("visit", RandomStringUtils.randomAlphanumeric(45), null, DataUtil.buildCurrentDateTime(-1), DataUtil.buildCurrentDateTime(-1));
        activitiesSteps.statusCodeShouldBe(404);
        activitiesSteps.validateData("No resources match the search criteria");
    }


    @Title("CVSB- / CVSB- - AC7 ")
    @Test
    public void postActivitiesActivityTypeVisit() {
        activitiesSteps.getActivities("visit", null, RandomStringUtils.randomAlphanumeric(45), DataUtil.buildCurrentDateTime(-1), DataUtil.buildCurrentDateTime(-1));
        activitiesSteps.statusCodeShouldBe(404);
        activitiesSteps.validateData("No resources match the search criteria");
    }

    @Title("CVSB- / CVSB- - AC7 ")
    @Test
    public void postActivitiesActivityTypeVisit2() {
        activitiesSteps.getActivities("visit", null, null, DataUtil.buildCurrentDateTime(-1), RandomStringUtils.randomAlphanumeric(45));
        activitiesSteps.statusCodeShouldBe(400);
        activitiesSteps.validateData("Bad Request");
    }

    @Title("CVSB- / CVSB- - AC7 ")
    @Test
    public void postActivitiesActivityTypeVisit222() {
        activitiesSteps.getActivities("visit", null, null, RandomStringUtils.randomAlphanumeric(45), RandomStringUtils.randomAlphanumeric(45));
        activitiesSteps.statusCodeShouldBe(400);
        activitiesSteps.validateData("Bad Request");
    }

    @Title("CVSB- / CVSB- - AC7 ")
    @Test
    public void postActivitiesActivityTypeVisit212() {
        activitiesSteps.postActivities(activitiesData.setActivityType("visit").build());
        activitiesSteps.statusCodeShouldBe(201);
        activitiesSteps.getActivities("visit", null, null, DataUtil.buildCurrentDateTime(-1), DataUtil.buildCurrentDateTime());
        activitiesSteps.statusCodeShouldBe(200);
    }

    @Title("CVSB- / CVSB- - AC7 ")
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
        // create alteration to change startTime
        JsonPathAlteration alterationStartTime = new JsonPathAlteration("$.startTime", new SimpleDateFormat
                ("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(new Date()),"","REPLACE");
        // create alteration to change endTime
        JsonPathAlteration alterationEndTime = new JsonPathAlteration("$.endTime", new SimpleDateFormat
                ("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(new Date()),"","REPLACE");
        ;
        // initialize the alterations list with both declared alteration
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(alterationParentId, alterationStartTime,
                alterationEndTime, alterationTestStationPNumber));
        activitiesSteps.postActivitiesParentIdWithAlterations(postRequestBody, alterations);
        activitiesSteps.statusCodeShouldBe(201);
        activitiesSteps.getActivities("wait", null, null, DataUtil.buildDate(DataUtil.buildCurrentDateTime(),0, -1), DataUtil.buildCurrentDateTime());
        activitiesSteps.statusCodeShouldBe(200);
        activitiesSteps.responseElementsShouldContainField("parentId");
        activitiesSteps.responseShouldContainFieldValue("activityType","wait");
    }

    @Title("CVSB- / CVSB- - AC7 ")
    @Test
    public void postActivitiesActivityTypeVisitNotVisibleForGetWait() {
        // read post request body from file
        String postRequestBody = GenericData.readJsonValueFromFile("activities.json","$");
        // create alteration to remove id
        JsonPathAlteration alterationId = new JsonPathAlteration("$.id", "","","DELETE");
        // create alteration to change testerStaffId
        JsonPathAlteration alterationTesterStaffId = new JsonPathAlteration("$.testerStaffId",
                UUID.randomUUID().toString(),"","REPLACE");
        // create alteration to change testStationPNumber
        JsonPathAlteration alterationTestStationPNumber = new JsonPathAlteration("$.testStationPNumber",
                "35-7138320","","REPLACE");
        // create alteration to change startTime
        JsonPathAlteration alterationStartTime = new JsonPathAlteration("$.startTime", new SimpleDateFormat
                ("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(new Date()),"","REPLACE");
        // create alteration to change endTime
        JsonPathAlteration alterationEndTime = new JsonPathAlteration("$.endTime", new SimpleDateFormat
                ("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(new Date()),"","REPLACE");
        ;
        // initialize the alterations list with both declared alteration
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(alterationId, alterationStartTime,
                alterationEndTime, alterationTesterStaffId, alterationTestStationPNumber));
        activitiesSteps.postActivitiesParentIdWithAlterations(postRequestBody, alterations);
        activitiesSteps.statusCodeShouldBe(201);
        String id = activitiesSteps.checkAndGetResponseId();
        activitiesData.setId(id);
        activitiesSteps.getActivities("wait", null, null, DataUtil.buildDate(DataUtil.buildCurrentDateTime(),-1), DataUtil.buildCurrentDateTime());

        int status  = activitiesSteps.statusCodeShouldBeOkOrNotFound();
        if (status == 200) {
            activitiesSteps.validateNotExistingId(activitiesData.build());
        }
    }

    @Title("CVSB- / CVSB- - AC7 ")
    @Test
    public void postActivitiesActivityTypeWaitNotVisibleForGetVisit() {
        activitiesSteps.postActivities(ActivitiesData.buildActivitiesIdData().setActivityType("visit").build());
        String parentId =  activitiesSteps.checkAndGetResponseId();
        // read post request body from file
        String postRequestBody = GenericData.readJsonValueFromFile("activities_parent_id.json","$");
        // create alteration to change parentId
        JsonPathAlteration alterationParentId = new JsonPathAlteration("$.parentId", parentId,"","REPLACE");
        // create alteration to change testStationPNumber
        JsonPathAlteration alterationTestStationPNumber = new JsonPathAlteration("$.testStationPNumber",
                "35-7138320","","REPLACE");
        // create alteration to change startTime
        JsonPathAlteration alterationStartTime = new JsonPathAlteration("$.startTime", new SimpleDateFormat
                ("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(new Date()),"","REPLACE");
        // create alteration to change endTime
        JsonPathAlteration alterationEndTime = new JsonPathAlteration("$.endTime", new SimpleDateFormat
                ("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(new Date()),"","REPLACE");
        ;
        // initialize the alterations list with both declared alteration
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(alterationParentId, alterationStartTime,
                alterationEndTime, alterationTestStationPNumber));
        activitiesSteps.postActivitiesParentIdWithAlterations(postRequestBody, alterations);
        activitiesSteps.statusCodeShouldBe(201);
        String id = activitiesSteps.checkAndGetResponseId();
        System.out.println("id is : " + id);
        activitiesData.setId(id);
        activitiesSteps.getActivities("visit", null, null, DataUtil.buildCurrentDateTime(-1), DataUtil.buildCurrentDateTime());
        activitiesSteps.statusCodeShouldBe(200);
        activitiesSteps.validateNotExistingId(activitiesData.build());
    }


    @Title("CVSB- / CVSB- - AC7 ")
    @Test
    public void postActivitiesActivityTypeWaitNotVisibleForGetVisit22() {
        activitiesSteps.postActivities(activitiesData.setActivityType("visit").build());
        activitiesSteps.statusCodeShouldBe(201);
        String id = activitiesSteps.checkAndGetResponseId();
        activitiesData.setId(id);
        activitiesSteps.getActivities("visit", activitiesData.build().getTesterStaffId(), null, DataUtil.buildCurrentDateTime(-1), DataUtil.buildCurrentDateTime());
        activitiesSteps.statusCodeShouldBe(200);
        activitiesSteps.validateData(activitiesData.build());
    }


    @Title("CVSB- / CVSB- - AC7 ")
    @Test
    public void postActivitiesActivityTypeWaitNotVisibleForGetVisit2232() {
        activitiesSteps.postActivities(activitiesData.setActivityType("visit").build());
        activitiesSteps.statusCodeShouldBe(201);
        String id = activitiesSteps.checkAndGetResponseId();
        activitiesData.setId(id);
        activitiesSteps.getActivities(null, DataUtil.generateRandomExcludingValues(activitiesData.build().getTesterStaffId().length(), activitiesData.build().getTesterStaffId()), null, DataUtil.buildCurrentDateTime(-1), null);
        activitiesSteps.statusCodeShouldBe(400);
        activitiesSteps.validateData("Bad Request");
    }

    @Title("CVSB- / CVSB- - AC7 ")
    @Test
    public void postActivitiesActivityTypeWaitNotVisibleForGetVisit22312() {
        activitiesSteps.postActivities(activitiesData.setActivityType("visit").build());
        activitiesSteps.statusCodeShouldBe(201);
        String id = activitiesSteps.checkAndGetResponseId();
        activitiesData.setId(id);
        activitiesSteps.getActivities("visit", null, activitiesData.build().getTestStationPNumber(), DataUtil.buildCurrentDateTime(-1), DataUtil.buildCurrentDateTime());
        activitiesSteps.statusCodeShouldBe(200);
        activitiesSteps.validateData(activitiesData.build());
    }


    @Title("CVSB- / CVSB- - AC7 ")
    @Test
    public void postActivitiesActivityTypeWaitNotVisibleForGetVisit22322() {
        activitiesSteps.postActivities(activitiesData.setActivityType("visit").build());
        activitiesSteps.statusCodeShouldBe(201);
        String id = activitiesSteps.checkAndGetResponseId();
        activitiesData.setId(id);
        activitiesSteps.getActivities("visit", null, DataUtil.generateRandomExcludingValues(activitiesData.build().getTestStationPNumber().length(), activitiesData.build().getTestStationPNumber()), DataUtil.buildCurrentDateTime(-1), DataUtil.buildCurrentDateTime());
        activitiesSteps.statusCodeShouldBe(404);
        activitiesSteps.validateData("No resources match the search criteria");
    }

    @Title("CVSB- / CVSB- - AC7 ")
    @Test
    public void postActivitiesActivityTypeWaitNotVisibleForGetVisit223212() {
        activitiesSteps.postActivities(activitiesData.setActivityType("visit").build());
        activitiesSteps.statusCodeShouldBe(201);
        String id = activitiesSteps.checkAndGetResponseId();
        activitiesData.setId(id);
        activitiesSteps.getActivities("visit", null, null, DataUtil.buildCurrentDateTime(-1), DataUtil.buildCurrentDateTime(1));
        activitiesSteps.statusCodeShouldBe(200);
    }

    @Title("CVSB- / CVSB- - AC7 ")
    @Test
    public void postActivitiesActivityTypeWaitNotVisibleForGetVisit223222() {
        activitiesSteps.postActivities(activitiesData.setActivityType("visit").build());
        activitiesSteps.statusCodeShouldBe(201);
        String id = activitiesSteps.checkAndGetResponseId();
        activitiesData.setId(id);
        activitiesSteps.getActivities("visit", null, null, DataUtil.buildCurrentDateTime(-1), RandomStringUtils.randomAlphanumeric(45));
        activitiesSteps.statusCodeShouldBe(400);
        activitiesSteps.validateData("Bad Request");
    }

}
