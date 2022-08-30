package activities;

import data.ActivitiesData;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import net.thucydides.core.annotations.WithTag;
import org.junit.Test;
import org.junit.runner.RunWith;
import steps.ActivitiesSteps;

@WithTag("In_test")
@RunWith(SerenityRunner.class)
public class OpenVisitCheck {
    @Steps
    ActivitiesSteps activitiesSteps;

    @Title("VTA-300 - Get open visit return 200 OK, false/true")
    @Test
    public void getOpenVisitOk() {
        activitiesSteps.postActivities(ActivitiesData.buildActivitiesIdData().setActivityType("visit").build());
        activitiesSteps.checkOpenVisit("113", false);
        activitiesSteps.statusCodeShouldBe(200);
        activitiesSteps.checkOpenVisit("132", true);
        activitiesSteps.statusCodeShouldBe(200);
    }

    @Title("VTA-300 - Get open visit with empty string testerStaffId return 400 Bad Request")
    @Test
    public void getOpenVisitWithEmptyStringStaffId() {
        activitiesSteps.checkOpenVisit( "", false);
        activitiesSteps.statusCodeShouldBe(400);
        activitiesSteps.validateResp("\"Bad Request\"");
        activitiesSteps.checkOpenVisit(null, false);
        activitiesSteps.statusCodeShouldBe(400);
        activitiesSteps.validateResp("\"Bad Request\"");
        activitiesSteps.checkOpenVisit("undefined", false);
        activitiesSteps.statusCodeShouldBe(400);
        activitiesSteps.validateResp("\"Bad Request\"");
    }

}
