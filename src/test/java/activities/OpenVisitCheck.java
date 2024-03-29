package activities;

import data.ActivitiesData;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Test;
import org.junit.runner.RunWith;
import steps.ActivitiesSteps;

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

    @Title("VTA-300 - Get open visit with empty string testerStaffId return 400 Missing parameter value.")
    @Test
    public void getOpenVisitWithMissingStaffId() {
        activitiesSteps.checkOpenVisit( "", false);
        activitiesSteps.statusCodeShouldBe(400);
        activitiesSteps.validateResp("\"Missing parameter value.\"");
        activitiesSteps.checkOpenVisit(null, false);
        activitiesSteps.statusCodeShouldBe(400);
        activitiesSteps.validateResp("\"Missing parameter value.\"");
        activitiesSteps.checkOpenVisit("undefined", false);
        activitiesSteps.statusCodeShouldBe(400);
        activitiesSteps.validateResp("\"Missing parameter value.\"");
    }
}
