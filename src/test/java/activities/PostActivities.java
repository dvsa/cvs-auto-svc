package activities;

import data.ActivitiesData;
import model.activities.Activities;
import model.activities.ActivitiesGet;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.core.annotations.WithTags;
import org.junit.Test;
import org.junit.runner.RunWith;
import steps.ActivitiesSteps;
import util.DataUtil;

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

    private ActivitiesGet.Builder activitiesData = ActivitiesData.buildActivitiesData();

    @Title("CVSB-163 / CVSB-2874 - AC8 API Consumer creates a new activity - activity type: visit")
    @Test
    public void postActivitiesActivityTypeVisit() {
        int i = 0;

        while (true) {
            i ++;
            System.out.println("Iteration " + i);
            activitiesSteps.postActivities(activitiesData.setActivityType("visit").build());
//            activitiesSteps.postActivities(activitiesData.setActivityType(“visit”)
//                    .setTesterStaffId(RandomStringUtils.randomAlphanumeric(20) + RandomStringUtils.randomNumeric(21))
//                    .build());
            int statuscode = activitiesSteps.statusCodeShouldBe(201);
            if (statuscode != 201) {
                activitiesSteps.printStatusAndBody();
                break;
            }

            String id = activitiesSteps.checkAndGetResponseId();


            activitiesSteps.getActivities("visit", activitiesData.build().getTesterStaffId(), null, DataUtil.buildCurrentDateTime(-1), null);

            statuscode = activitiesSteps.statusCodeShouldBe(200);
            if (statuscode != 200) {
                activitiesSteps.printStatusAndBody();
                break;
            }
            activitiesSteps.validateData(activitiesData.build());

//            try {
//                Thread.sleep(4000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }

            activitiesSteps.putActivities(id);
            statuscode = activitiesSteps.statusCodeShouldBe(204);

            if (statuscode != 204) {
                activitiesSteps.printStatusAndBody();
                break;
            }
        }


//        activitiesSteps.postActivities(ActivitiesData.buildActivitiesData().setActivityType("visit").build());
//        activitiesSteps.statusCodeShouldBe(201);
//        activitiesSteps.responseShouldContainId();
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
