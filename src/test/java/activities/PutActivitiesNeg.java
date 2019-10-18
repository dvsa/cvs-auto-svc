package activities;

import data.ActivitiesData;
import model.activities.Activities;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import steps.ActivitiesSteps;

import java.util.ArrayList;


@RunWith(SerenityRunner.class)
public class PutActivitiesNeg {
    @Steps
    ActivitiesSteps activitiesSteps;

    private Activities activitiesData = ActivitiesData.buildActivitiesIdData().build();
    private String id;


    @Title("CVSB-163 / CVSB-2883 - AC11 API Consumer fails to end an activity")
    @Test
    public void testPutActivityNonExistingAlphanumeric() {

        id = RandomStringUtils.randomAlphanumeric(15);

        activitiesSteps.putActivitiesEnd(id);
        activitiesSteps.statusCodeShouldBe(404);
        activitiesSteps.validateActivityErrorMessage("Activity id does not exist");

    }


    @Title("CVSB-163 / CVSB-2883 - AC11 API Consumer fails to end an activity")
    @Test
    public void testPutActivityNonExistingNumeric() {

        id = RandomStringUtils.randomNumeric(15);

        activitiesSteps.putActivitiesEnd(id);
        activitiesSteps.statusCodeShouldBe(404);
        activitiesSteps.validateActivityErrorMessage("Activity id does not exist");

    }


    @Title("CVSB-163 / CVSB-2947 - API Consumer tries to end an already ended activity")
    @Test
    public void putActivitiesTwiceExisting() {
        activitiesSteps.postActivities(activitiesData);
        activitiesSteps.statusCodeShouldBe(201);
        String id = activitiesSteps.checkAndGetResponseId();
        activitiesSteps.putActivitiesEnd(id);
        activitiesSteps.statusCodeShouldBe(204);
        activitiesSteps.putActivitiesEnd(id);
        activitiesSteps.statusCodeShouldBe(403);
        activitiesSteps.validateActivityErrorMessage("Activity already ended");

    }

    @Title("CVSB-179 / CVSB-4563 - API Consumer with ended activity ends a new activity")
    @Test
    public void putActivitiesUpdateInvalidReason() {
        activitiesSteps.postActivities(ActivitiesData.buildActivitiesIdData().setActivityType("visit").build());
        String id =  activitiesSteps.checkAndGetResponseId();
        ArrayList<String> reason = new ArrayList<String>();
        reason.add("ASdw");
        activitiesSteps.putActivitiesUpdate(ActivitiesData.buildActivitiesUpdateData().setId(id).setWaitReason(reason).build());
        activitiesSteps.statusCodeShouldBe(400);
        activitiesSteps.validateActivityErrorTypeWithProperty("waitReason", "at position 0 does not match any of the allowed types");

    }

    @Title("CVSB-179 / CVSB-4563 - API Consumer with ended activity ends a new activity")
    @Test
    public void putActivitiesUpdateNullReason() {
        activitiesSteps.postActivities(ActivitiesData.buildActivitiesIdData().setActivityType("visit").build());
        String id =  activitiesSteps.checkAndGetResponseId();
        activitiesSteps.putActivitiesUpdate(ActivitiesData.buildActivitiesUpdateData().setId(id).setWaitReason(null).build());
        activitiesSteps.statusCodeShouldBe(400);
        activitiesSteps.validateActivityErrorTypeWithProperty("waitReason", "must be an array");

    }
}
