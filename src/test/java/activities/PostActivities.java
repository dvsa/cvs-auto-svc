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
        activitiesSteps.postActivities(ActivitiesData.buildActivitiesIdData().setActivityType("visit").build());
        activitiesSteps.statusCodeShouldBe(201);
        activitiesSteps.responseShouldContainId();
    }

    @Title("CVSB-163 / CVSB-2874 - AC8 API Consumer creates a new activity - activity type: wait")
    @Test
    public void postActivitiesActivityTypeWait() {
        activitiesSteps.postActivities(ActivitiesData.buildActivitiesIdData().setActivityType("visit").build());
        String parentId =  activitiesSteps.checkAndGetResponseId();
        activitiesSteps.postActivitiesWithWaitReason(ActivitiesData.buildActivitiesParentIdData().setParentId(parentId).setActivityType("wait").build());
        activitiesSteps.statusCodeShouldBe(201);
        activitiesSteps.responseShouldContainId();
    }

    @Title("CVSB-179 / CVSB-4545 / CVSB-4547 - Save wait time in the BE (time is Equal to 5 minutes)")
    @Test
    public void postActivitiesActivityTypeWaitTimeIsEqualTo5Minutes() {
        activitiesSteps.postActivities(ActivitiesData.buildActivitiesIdData().setActivityType("visit").build());
        String parentId =  activitiesSteps.checkAndGetResponseId();
        activitiesSteps.postActivitiesWithWaitReason(ActivitiesData.buildActivitiesParentIdData().setParentId(parentId).setActivityType("wait").setStartTime("2019-03-19T20:03:38.113Z").setEndTime("2019-03-19T20:08:38.113Z").build());
        activitiesSteps.statusCodeShouldBe(201);
        activitiesSteps.responseShouldContainId();
    }

    @Title("CVSB-179 / CVSB-4544 / CVSB-4543 - Save wait time in the BE (time is Greater than 5 minutes)")
    @Test
    public void postActivitiesActivityTypeWaitTimeIsGreaterThan5Minutes() {
        activitiesSteps.postActivities(ActivitiesData.buildActivitiesIdData().setActivityType("visit").build());
        String parentId =  activitiesSteps.checkAndGetResponseId();
        activitiesSteps.postActivitiesWithWaitReason(ActivitiesData.buildActivitiesParentIdData().setParentId(parentId).setActivityType("wait").setStartTime("2019-03-18T20:03:38.113Z").setEndTime("2019-03-19T20:03:38.113Z").build());
        activitiesSteps.statusCodeShouldBe(201);
        activitiesSteps.responseShouldContainId();
    }

    @Title("CVSB-179 / CVSB-4545 / CVSB-4547 / CVSB-4551 / CVSB-4552 / CVSB-4554 / CVSB-4556 - Save wait time in the BE (time is Equal to 5 minutes)")
    @Test
    public void postActivitiesActivityTypeWaitTimeIsEqualTo5MinutesPreviousTime() {
        activitiesSteps.postActivities(ActivitiesData.buildActivitiesIdData().setActivityType("visit").build());
        String parentId =  activitiesSteps.checkAndGetResponseId();
        activitiesSteps.postActivitiesWithWaitReason(ActivitiesData.buildActivitiesParentIdData().setParentId(parentId).setActivityType("wait").setStartTime("2019-03-19T20:08:38.113Z").setEndTime("2019-03-19T20:13:38.113Z").build());
        activitiesSteps.statusCodeShouldBe(201);
        activitiesSteps.responseShouldContainId();
    }

    @Title("CVSB-179 / CVSB-4548 / CVSB-4543 / CVSB-4549 / CVSB-4553 / CVSB-4555 - Save wait time in the BE (time is Greater than 5 minutes)")
    @Test
    public void postActivitiesActivityTypeWaitTimeIsGreaterThan5MinutesPreviousTime() {
        activitiesSteps.postActivities(ActivitiesData.buildActivitiesIdData().setActivityType("visit").build());
        String parentId =  activitiesSteps.checkAndGetResponseId();
        activitiesSteps.postActivitiesWithWaitReason(ActivitiesData.buildActivitiesParentIdData().setParentId(parentId).setActivityType("wait").setStartTime("2019-03-19T20:03:38.113Z").setEndTime("2019-03-20T20:03:38.113Z").build());
        activitiesSteps.statusCodeShouldBe(201);
        activitiesSteps.responseShouldContainId();
    }

    @Title("CVSB-179 / CVSB-4557 / CVSB-4558 / CVSB-4562 - AC M5 Save wait time in the BE")
    @Test
    public void postActivitiesActivityTypeUnaccountableTime() {
        activitiesSteps.postActivities(ActivitiesData.buildActivitiesIdData().setActivityType("visit").build());
        String parentId =  activitiesSteps.checkAndGetResponseId();
        activitiesSteps.postActivitiesWithWaitReason(ActivitiesData.buildActivitiesParentIdData().setParentId(parentId).setActivityType("unaccountable time").build());
        activitiesSteps.statusCodeShouldBe(201);
        activitiesSteps.responseShouldContainId();
    }

    @Title("CVSB-179 / CVSB-4559 / CVSB-4560 / CVSB-4561 /  - AC M6 Save wait time in the BE")
    @Test
    public void postActivitiesActivityTypeUnaccountableTimePreviousTime() {
        activitiesSteps.postActivities(ActivitiesData.buildActivitiesIdData().setActivityType("visit").build());
        String parentId =  activitiesSteps.checkAndGetResponseId();
        activitiesSteps.postActivitiesWithWaitReason(ActivitiesData.buildActivitiesParentIdData().setParentId(parentId).setActivityType("unaccountable time").setStartTime("2019-03-19T20:03:38.113Z").setEndTime("2019-03-19T20:04:38.113Z").build());
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
