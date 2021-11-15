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

    @Title("CVSB-19924 - Get open visit return 200 OK, false")
    @Test
    public void getOpenVisitFalse() {
        activitiesSteps.postActivities(activitiesData.setActivityType("visit").build());
        activitiesSteps.statusCodeShouldBe(201);
        activitiesSteps.getActivitiesOpenVisitFalse("visit", "113");
        activitiesSteps.statusCodeShouldBe(200);

    }

    @Title("CVSB-19924 - Get open visit return 200 OK, true")
    @Test
    public void getOpenVisitTrue() {
        activitiesSteps.postActivities(activitiesData.setActivityType("visit").build());
        activitiesSteps.statusCodeShouldBe(201);
        activitiesSteps.getActivitiesOpenVisitTrue("visit", "132");
        activitiesSteps.statusCodeShouldBe(200);
    }

    @Title("CVSB-19924 - Get open visit with empty string testerStaffId return 400 Bad Request")
    @Test
    public void getOpenVisitWithEmptyStringStaffId() {
        activitiesSteps.postActivities(activitiesData.setActivityType("visit").build());
        activitiesSteps.statusCodeShouldBe(201);
        activitiesSteps.getActivitiesOpenVisitTrue("visit", "");
        activitiesSteps.statusCodeShouldBe(400);
    }

    @Title("CVSB-19924 - Get open visit with null testerStaffId return 400 Bad Request\"")
    @Test
    public void getOpenVisitWithNullStaffId() {
        activitiesSteps.postActivities(activitiesData.setActivityType("visit").build());
        activitiesSteps.statusCodeShouldBe(201);
        activitiesSteps.getActivitiesOpenVisitTrue("visit", null);
        activitiesSteps.statusCodeShouldBe(400);
    }

    @Title("CVSB-19924 - Get open visit with undefined testerStaffId return 400 Bad Request\"")
    @Test
    public void getOpenVisitWithUndefinedStaffId() {
        activitiesSteps.postActivities(activitiesData.setActivityType("visit").build());
        activitiesSteps.statusCodeShouldBe(201);
        activitiesSteps.getActivitiesOpenVisitTrue("visit", "undefined");
        activitiesSteps.statusCodeShouldBe(400);
    }
}
