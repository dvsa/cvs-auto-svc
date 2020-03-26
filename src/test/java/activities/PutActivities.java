package activities;

import data.ActivitiesData;
import model.activities.Activities;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Test;
import org.junit.runner.RunWith;
import steps.ActivitiesSteps;


@RunWith(SerenityRunner.class)
public class PutActivities {

    @Steps
    ActivitiesSteps activitiesSteps;

    @Title("CVSB-163 / CVSB-2877 - AC10 API Consumer ends an activity")
    @Test
    public void putActivities() {

        Activities activitiesData = ActivitiesData.buildActivitiesIdData().build();

        activitiesSteps.postActivities(activitiesData);
        activitiesSteps.statusCodeShouldBe(201);
        activitiesSteps.responseShouldContainId();
        String id = activitiesSteps.checkAndGetResponseId();
        activitiesSteps.putActivitiesEnd(id);
        activitiesSteps.statusCodeShouldBe(204);

    }

    @Title("CVSB-163 / CVSB-2946 - API Consumer with ended activity ends a new activity")
    @Test
    public void putActivitiesOnNewActivitySameTesterStaffId() {

        Activities activitiesData = ActivitiesData.buildActivitiesIdData().build();

        activitiesSteps.postActivities(activitiesData);
        activitiesSteps.statusCodeShouldBe(201);
        activitiesSteps.responseShouldContainId();
        String id = activitiesSteps.checkAndGetResponseId();
        activitiesSteps.putActivitiesEnd(id);
        activitiesSteps.statusCodeShouldBe(204);
        activitiesSteps.postActivities(activitiesData);
        activitiesSteps.statusCodeShouldBe(201);
        id = activitiesSteps.checkAndGetResponseId();
        activitiesSteps.putActivitiesEnd(id);
        activitiesSteps.statusCodeShouldBe(204);

    }

    @Title("CVSB-179 / CVSB-4563 - API Consumer with ended activity ends a new activity")
    @Test
    public void putActivitiesUpdate() {
        activitiesSteps.postActivities(ActivitiesData.buildActivitiesIdData().setActivityType("visit").build());
        String id =  activitiesSteps.checkAndGetResponseId();
        activitiesSteps.putActivitiesUpdate(ActivitiesData.buildActivitiesUpdateData().setId(id).build());
        activitiesSteps.statusCodeShouldBe(204);

    }
}
