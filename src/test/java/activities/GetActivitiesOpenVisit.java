package activities;

import data.ActivitiesData;
import model.activities.ActivitiesGet;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Test;
import org.junit.runner.RunWith;
import steps.ActivitiesSteps;

@RunWith(SerenityRunner.class)
public class GetActivitiesOpenVisit {
    @Steps
    ActivitiesSteps activitiesSteps;

    ActivitiesGet.Builder activitiesData = ActivitiesData.buildActivitiesIdData();

    @Title("CVSB-19924")
    @Test
    public void getOpenVisitFalse() {
        activitiesSteps.postActivities(activitiesData.setActivityType("visit").build());
        activitiesSteps.statusCodeShouldBe(201);
        activitiesSteps.getActivitiesOpenVisit("visit", "113");
        activitiesSteps.statusCodeShouldBe(200);

    }

    @Title("CVSB-19924")
    @Test
    public void getOpenVisitTrue() {
        activitiesSteps.postActivities(activitiesData.setActivityType("visit").build());
        activitiesSteps.statusCodeShouldBe(201);
        activitiesSteps.getActivitiesOpenVisit("visit", "132");
        activitiesSteps.statusCodeShouldBe(200);

    }


//
//    @Title("CVSB- / CVSB- - AC7 ")
//    @Test
//    public void postActivitiesActivityTypeVisiRt() {
//        activitiesSteps.getActivities("", null, null, "", "");
//        activitiesSteps.statusCodeShouldBe(400);
//        activitiesSteps.validateData("Bad Request");
//    }
//
//
//    @Title("CVSB- / CVSB- - AC7 ")
//    @Test
//    public void postActivitiesActivityTypeVisiRts() {
//        activitiesSteps.getActivities(RandomStringUtils.randomAlphanumeric(6), null, null, DataUtil.buildCurrentDateTime(-1), DataUtil.buildCurrentDateTime(-1));
//        activitiesSteps.statusCodeShouldBe(404);
//        activitiesSteps.validateData("No resources match the search criteria");
//    }
//
//
//
//
//
//    @Title("CVSB- / CVSB- - AC7 ")
//    @Test
//    public void postActivitiesActivityTypeVisit2() {
//        activitiesSteps.getActivities("visit", null, null, DataUtil.buildCurrentDateTime(-1), RandomStringUtils.randomAlphanumeric(45));
//        activitiesSteps.statusCodeShouldBe(400);
//        activitiesSteps.validateData("Bad Request");
//    }
//
//    @Title("CVSB- / CVSB- - AC7 ")
//    @Test
//    public void postActivitiesActivityTypeVisit222() {
//        activitiesSteps.getActivities("visit", null, null, RandomStringUtils.randomAlphanumeric(45), RandomStringUtils.randomAlphanumeric(45));
//        activitiesSteps.statusCodeShouldBe(400);
//        activitiesSteps.validateData("Bad Request");
//    }

}
