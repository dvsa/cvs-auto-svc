package activities;

import data.ActivitiesData;
import model.activities.Activities;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.core.annotations.WithTags;
import org.junit.Test;
import org.junit.runner.RunWith;
import steps.ActivitiesSteps;

@WithTags(
        {
                @WithTag(type = "PostActivities", name = "All"),
                @WithTag(type = "PostActivities", name = "Positive"),
                @WithTag(type = "Service", name = "One"),

        }
)
@RunWith(SerenityRunner.class)
public class PostActivities {
    @Steps
    ActivitiesSteps activitiesSteps;

    @Title("CVSB-163 / CVSB-2874 - AC8 API Consumer creates a new activity - activity type: visit")
    @Test
    public void postActivitiesActivityTypeVisit() {
        activitiesSteps.postActivities(ActivitiesData.buildActivitiesData().setActivityType("visit").build());
        activitiesSteps.statusCodeShouldBe(201);
        activitiesSteps.responseShouldContainId();
    }

    @Title("CVSB-163 / CVSB-2874 - AC8 API Consumer creates a new activity - activity type: wait")
    @Test
    public void postActivitiesActivityTypeWait() {
        activitiesSteps.postActivities(ActivitiesData.buildActivitiesData().setActivityType("wait").build());
        activitiesSteps.statusCodeShouldBe(201);
        activitiesSteps.responseShouldContainId();
    }


    @Title("CVSB-163 / CVSB-2874 - AC8 API Consumer creates a new activity - test station type: atf")
    @Test
    public void postActivitiesTestStationTypeAtf() {
        activitiesSteps.postActivities(ActivitiesData.buildActivitiesData().setTestStationType("atf").build());
        activitiesSteps.statusCodeShouldBe(201);
        activitiesSteps.responseShouldContainId();
    }

    @Title("CVSB-163 / CVSB-2874 - AC8 API Consumer creates a new activity - test station type: gvts")
    @Test
    public void postActivitiesTestStationTypeGvts() {
        activitiesSteps.postActivities(ActivitiesData.buildActivitiesData().setTestStationType("gvts").build());
        activitiesSteps.statusCodeShouldBe(201);
        activitiesSteps.responseShouldContainId();
    }

    @Title("CVSB-163 / CVSB-2874 - AC8 API Consumer creates a new activity - test station type: hq")
    @Test
    public void postActivitiesTestStationTypeHq() {
        activitiesSteps.postActivities(ActivitiesData.buildActivitiesData().setTestStationType("hq").build());
        activitiesSteps.statusCodeShouldBe(201);
        activitiesSteps.responseShouldContainId();
    }


    @Title("CVSB-163 / CVSB-2928 -  API Consumer with ended activity creates a new activity")
    @Test
    public void postActivitiesAfterActivityEnded() {

        Activities activitiesData = ActivitiesData.buildActivitiesData().build();

        activitiesSteps.postActivities(activitiesData);
        activitiesSteps.statusCodeShouldBe(201);
        activitiesSteps.responseShouldContainId();
        String id = activitiesSteps.checkAndGetResponseId();
        activitiesSteps.putActivities(id);
        activitiesSteps.statusCodeShouldBe(204);
        activitiesSteps.postActivities(activitiesData);
        activitiesSteps.statusCodeShouldBe(201);
        activitiesSteps.responseShouldContainId();

    }

}
