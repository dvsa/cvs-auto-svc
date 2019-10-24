package activities;


import data.ActivitiesData;
import model.activities.ActivitiesGet;
import model.activities.ActivitiesPost;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import steps.ActivitiesSteps;
import util.DataUtil;


@RunWith(SerenityRunner.class)
public class GetActivities {

    @Steps
    ActivitiesSteps activitiesSteps;

    ActivitiesGet.Builder activitiesData = ActivitiesData.buildActivitiesIdData();


    @Title("CVSB- / CVSB- - AC7 ")
    @Test
    public void postActivitiesActivityTypeVisiRt() {
        activitiesSteps.getActivities(null, null, null, null, null);
        activitiesSteps.statusCodeShouldBe(400);
        activitiesSteps.validateData("Bad Request");
    }


    @Title("CVSB- / CVSB- - AC7 ")
    @Test
    public void postActivitiesActivityTypeVisiRts() {
        activitiesSteps.getActivities(RandomStringUtils.randomAlphanumeric(6), null, null, DataUtil.buildCurrentDateTime(-1), null);
        activitiesSteps.statusCodeShouldBe(404);
        activitiesSteps.validateData("No resources match the search criteria");
    }


    @Title("CVSB- / CVSB- - AC7 ")
    @Test
    public void postActivitiesActivityTypeVisiRt2s() {
        activitiesSteps.getActivities(null, RandomStringUtils.randomAlphanumeric(45), null, DataUtil.buildCurrentDateTime(-1), null);
        activitiesSteps.statusCodeShouldBe(404);
        activitiesSteps.validateData("No resources match the search criteria");
    }


    @Title("CVSB- / CVSB- - AC7 ")
    @Test
    public void postActivitiesActivityTypeVisit() {
        activitiesSteps.getActivities(null, null, RandomStringUtils.randomAlphanumeric(45), DataUtil.buildCurrentDateTime(-1), null);
        activitiesSteps.statusCodeShouldBe(404);
        activitiesSteps.validateData("No resources match the search criteria");
    }

    @Title("CVSB- / CVSB- - AC7 ")
    @Test
    public void postActivitiesActivityTypeVisit2() {
        activitiesSteps.getActivities(null, null, null, DataUtil.buildCurrentDateTime(-1), RandomStringUtils.randomAlphanumeric(45));
        activitiesSteps.statusCodeShouldBe(404);
        activitiesSteps.validateData("No resources match the search criteria");
    }

    @Title("CVSB- / CVSB- - AC7 ")
    @Test
    public void postActivitiesActivityTypeVisit222() {
        activitiesSteps.getActivities(null, null, null, RandomStringUtils.randomAlphanumeric(45), RandomStringUtils.randomAlphanumeric(45));
        activitiesSteps.statusCodeShouldBe(404);
        activitiesSteps.validateData("No resources match the search criteria");
    }

    @Title("CVSB- / CVSB- - AC7 ")
    @Test
    public void postActivitiesActivityTypeVisit212() {
        activitiesSteps.postActivities(activitiesData.setActivityType("visit").build());
        activitiesSteps.statusCodeShouldBe(201);
        activitiesSteps.getActivities("visit", null, null, DataUtil.buildCurrentDateTime(-1), null);
        activitiesSteps.statusCodeShouldBe(200);
    }

    @Title("CVSB- / CVSB- - AC7 ")
    @Test
    public void postActivitiesActivityTypeWait() {
        activitiesSteps.postActivities(ActivitiesData.buildActivitiesIdData().setActivityType("visit").build());
        String parentId =  activitiesSteps.checkAndGetResponseId();
        activitiesSteps.postActivitiesWithWaitReason(ActivitiesData.buildActivitiesParentIdData().setParentId(parentId).setActivityType("wait").build());
        activitiesSteps.statusCodeShouldBe(201);
        activitiesSteps.getActivities("wait", null, null, DataUtil.buildDate(DataUtil.buildCurrentDateTime(),-1), DataUtil.buildCurrentDateTime());
        activitiesSteps.statusCodeShouldBe(200);
        activitiesSteps.responseElementsShouldContainField("parentId");
        activitiesSteps.responseShouldContainFieldValue("activityType","wait");
    }

    @Title("CVSB- / CVSB- - AC7 ")
    @Test
    public void postActivitiesActivityTypeVisitNotVisibleForGetWait() {
        activitiesSteps.postActivities(activitiesData.setActivityType("visit").build());
        activitiesSteps.statusCodeShouldBe(201);
        String id = activitiesSteps.checkAndGetResponseId();
        activitiesData.setId(id);
        activitiesSteps.getActivities("wait", null, null, DataUtil.buildDate(DataUtil.buildCurrentDateTime(),-1), DataUtil.buildCurrentDateTime());
        activitiesSteps.statusCodeShouldBe(200);
        activitiesSteps.validateNotExistingId(activitiesData.build());
    }

    @Title("CVSB- / CVSB- - AC7 ")
    @Test
    public void postActivitiesActivityTypeWaitNotVisibleForGetVisit() {
        activitiesSteps.postActivities(ActivitiesData.buildActivitiesIdData().setActivityType("visit").build());
        String parentId =  activitiesSteps.checkAndGetResponseId();
        activitiesSteps.postActivitiesWithWaitReason(ActivitiesData.buildActivitiesParentIdData().setParentId(parentId).setActivityType("wait").build());
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
        activitiesSteps.getActivities(null, activitiesData.build().getTesterStaffId(), null, DataUtil.buildCurrentDateTime(-1), null);
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
        activitiesSteps.statusCodeShouldBe(404);
        activitiesSteps.validateData("No resources match the search criteria");
    }




    @Title("CVSB- / CVSB- - AC7 ")
    @Test
    public void postActivitiesActivityTypeWaitNotVisibleForGetVisit22312() {
        activitiesSteps.postActivities(activitiesData.setActivityType("visit").build());
        activitiesSteps.statusCodeShouldBe(201);
        String id = activitiesSteps.checkAndGetResponseId();
        activitiesData.setId(id);
        activitiesSteps.getActivities(null, null, activitiesData.build().getTestStationPNumber(), DataUtil.buildCurrentDateTime(-1), null);
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
        activitiesSteps.getActivities(null, null, DataUtil.generateRandomExcludingValues(activitiesData.build().getTestStationPNumber().length(), activitiesData.build().getTestStationPNumber()), DataUtil.buildCurrentDateTime(-1), null);
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
        activitiesSteps.getActivities(null, null, null, DataUtil.buildCurrentDateTime(-1), DataUtil.buildCurrentDateTime(1));
        activitiesSteps.statusCodeShouldBe(200);
    }

    @Title("CVSB- / CVSB- - AC7 ")
    @Test
    public void postActivitiesActivityTypeWaitNotVisibleForGetVisit223222() {
        activitiesSteps.postActivities(activitiesData.setActivityType("visit").build());
        activitiesSteps.statusCodeShouldBe(201);
        String id = activitiesSteps.checkAndGetResponseId();
        activitiesData.setId(id);
        activitiesSteps.getActivities(null, null, null, DataUtil.buildCurrentDateTime(-1), RandomStringUtils.randomAlphanumeric(45));
        activitiesSteps.statusCodeShouldBe(404);
        activitiesSteps.validateData("No resources match the search criteria");
    }

}
