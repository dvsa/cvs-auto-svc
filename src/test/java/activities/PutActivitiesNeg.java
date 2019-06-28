package activities;

import data.ActivitiesData;
import model.activities.Activities;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.core.annotations.WithTags;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import steps.ActivitiesSteps;

@WithTags(
        {
                @WithTag(type = "PutActivities", name = "All"),
                @WithTag(type = "PutActivities", name = "Negative"),
                @WithTag(type = "Service", name = "One"),

        }
)
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
}
