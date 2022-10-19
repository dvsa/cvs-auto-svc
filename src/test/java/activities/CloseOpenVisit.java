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
public class CloseOpenVisit {
    @Steps
    ActivitiesSteps activitiesSteps;

    @Title("VTA-470 - Close open visit return 200 OK, false/true")
    @Test
    public void closeOpenVisitOk() {
        activitiesSteps.postActivities(ActivitiesData.buildActivitiesIdData().setActivityType("visit").build());
        String activityId = activitiesSteps.checkAndGetResponseId();
        activitiesSteps.responseShouldContainId();
        activitiesSteps.closeOpenVisit(activityId);
        activitiesSteps.statusCodeShouldBe(200);
        activitiesSteps.valueForFieldInPathShouldBe("wasVisitAlreadyClosed",false);
        activitiesSteps.closeOpenVisit(activityId);
        activitiesSteps.statusCodeShouldBe(200);
        activitiesSteps.valueForFieldInPathShouldBe("wasVisitAlreadyClosed",true);
    }

    @Title("VTA-470 - Close open visit call with undefined/empty string/null id return 400 status code and return Missing parameter value.")
    @Test
    public void closeOpenVisitMissingParameter() {
        activitiesSteps.closeOpenVisit(" ");
        activitiesSteps.statusCodeShouldBe(400);
        activitiesSteps.validateResp("\"Missing parameter value.\"");
        activitiesSteps.closeOpenVisit("undefined");
        activitiesSteps.statusCodeShouldBe(400);
        activitiesSteps.validateResp("\"Missing parameter value.\"");
        activitiesSteps.closeOpenVisit("null");
        activitiesSteps.statusCodeShouldBe(400);
        activitiesSteps.validateResp("\"Missing parameter value.\"");
    }
}
