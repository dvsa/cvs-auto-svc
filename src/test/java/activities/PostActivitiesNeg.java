package activities;


import clients.util.ToTypeConvertor;
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
import util.DataUtil;

@WithTags(
        {
                @WithTag(type = "PostActivities", name = "All"),
                @WithTag(type = "PostActivities", name = "Negative"),
                @WithTag(type = "Service", name = "One"),

        }
)
@RunWith(SerenityRunner.class)
public class PostActivitiesNeg {

    @Steps
    ActivitiesSteps activitiesSteps;

    private Activities.Builder activitiesData = ActivitiesData.buildActivitiesIdData();


    @Title("CVSB-163 / CVSB-2929 - API Consumer tries to create an activity with missing request body property - activityType")
    @Test
    public void activityActivityTypeMissing() {

        activitiesSteps.postActivities(activitiesData.build(), "activityType", ToTypeConvertor.MISSING);
        activitiesSteps.statusCodeShouldBe(400);
        activitiesSteps.validateActivityErrorTypeWithProperty("activityType", "is required");
    }


    @Title("CVSB-163 / CVSB-2930 - API Consumer tries to create an activity with null request body property value - activityType")
    @Test
    public void activityActivityTypeNull() {

        activitiesSteps.postActivities(activitiesData.build(), "activityType", ToTypeConvertor.NULL);
        activitiesSteps.statusCodeShouldBe(400);
        activitiesSteps.validateActivityErrorTypeWithProperty("activityType", "must be one of [visit, wait]");
    }

    @Title("CVSB-163 / CVSB-2931 - API Consumer tries to create an activity with different request body property type - activityType")
    @Test
    public void activityActivityTypeInteger() {

        activitiesSteps.postActivities(activitiesData.build(), "activityType", RandomStringUtils.randomNumeric(6), ToTypeConvertor.INTEGER);
        activitiesSteps.statusCodeShouldBe(400);
        activitiesSteps.validateActivityErrorTypeWithProperty("activityType", "must be one of [visit, wait]");
    }

    @Title("CVSB-163 / CVSB-2940 - API Consumer tries to create an activity with different range or format body property value - activityType")
    @Test
    public void activityActivityTypeRandomString() {

        activitiesSteps.postActivities(activitiesData.setActivityType(RandomStringUtils.randomAlphanumeric(6)).build());
        activitiesSteps.statusCodeShouldBe(400);
        activitiesSteps.validateActivityErrorTypeWithProperty("activityType", "must be one of [visit, wait]");
    }

    @Title("CVSB-163 / CVSB-2929 - API Consumer tries to create an activity with missing request body property - testStationName")
    @Test
    public void testStationNameMissing() {

        activitiesSteps.postActivities(activitiesData.build(), "testStationName", ToTypeConvertor.MISSING);
        activitiesSteps.statusCodeShouldBe(400);
        activitiesSteps.validateActivityErrorTypeWithProperty("testStationName", "is required");
    }


    @Title("CVSB-163 / CVSB-2930 - API Consumer tries to create an activity with null request body property value - testStationName")
    @Test
    public void testStationNameNull() {

        activitiesSteps.postActivities(activitiesData.build(), "testStationName", ToTypeConvertor.NULL);
        activitiesSteps.statusCodeShouldBe(400);
        activitiesSteps.validateActivityErrorTypeWithProperty("testStationName", "must be a string");
    }

    @Title("CVSB-163 / CVSB-2931 - API Consumer tries to create an activity with different request body property type - testStationName")
    @Test
    public void testStationNameInteger() {

        activitiesSteps.postActivities(activitiesData.build(), "testStationName", RandomStringUtils.randomNumeric(6), ToTypeConvertor.INTEGER);
        activitiesSteps.statusCodeShouldBe(400);
        activitiesSteps.validateActivityErrorTypeWithProperty("testStationName", "must be a string");
    }

    @Title("CVSB-163 / CVSB-2929 - API Consumer tries to create an activity with missing request body property - testStationPNumber")
    @Test
    public void testStationPNumberMissing() {

        activitiesSteps.postActivities(activitiesData.build(), "testStationPNumber", ToTypeConvertor.MISSING);
        activitiesSteps.statusCodeShouldBe(400);
        activitiesSteps.validateActivityErrorTypeWithProperty("testStationPNumber", "is required");
    }


    @Title("CVSB-163 / CVSB-2930 - API Consumer tries to create an activity with null request body property value - testStationPNumber")
    @Test
    public void testStationPNumberNull() {

        activitiesSteps.postActivities(activitiesData.build(), "testStationPNumber", ToTypeConvertor.NULL);
        activitiesSteps.statusCodeShouldBe(400);
        activitiesSteps.validateActivityErrorTypeWithProperty("testStationPNumber", "must be a string");
    }

    @Title("CVSB-163 / CVSB-2931 - API Consumer tries to create an activity with different request body property type - testStationPNumber")
    @Test
    public void testStationPNumberInteger() {

        activitiesSteps.postActivities(activitiesData.build(), "testStationPNumber", RandomStringUtils.randomNumeric(6), ToTypeConvertor.INTEGER);
        activitiesSteps.statusCodeShouldBe(400);
        activitiesSteps.validateActivityErrorTypeWithProperty("testStationPNumber", "must be a string");
    }

    @Title("CVSB-163 / CVSB-2929 - API Consumer tries to create an activity with missing request body property - testStationEmail")
    @Test
    public void testStationEmailMissing() {

        activitiesSteps.postActivities(activitiesData.build(), "testStationEmail", ToTypeConvertor.MISSING);
        activitiesSteps.statusCodeShouldBe(400);
        activitiesSteps.validateActivityErrorTypeWithProperty("testStationEmail", "is required");
    }


    @Title("CVSB-163 / CVSB-2930 - API Consumer tries to create an activity with null request body property value - testStationEmail")
    @Test
    public void testStationEmailNull() {

        activitiesSteps.postActivities(activitiesData.build(), "testStationEmail", ToTypeConvertor.NULL);
        activitiesSteps.statusCodeShouldBe(400);
        activitiesSteps.validateActivityErrorTypeWithProperty("testStationEmail", "must be a string");
    }

    @Title("CVSB-163 / CVSB-2931 - API Consumer tries to create an activity with different request body property type - testStationEmail")
    @Test
    public void testStationEmailInteger() {

        activitiesSteps.postActivities(activitiesData.build(), "testStationEmail", RandomStringUtils.randomNumeric(6), ToTypeConvertor.INTEGER);
        activitiesSteps.statusCodeShouldBe(400);
        activitiesSteps.validateActivityErrorTypeWithProperty("testStationEmail", "must be a string");
    }


    @Title("CVSB-163 / CVSB-2940 - API Consumer tries to create an activity with different range or format body property value - testStationEmail")
    @Test
    public void testStationEmailRandomString() {

        activitiesSteps.postActivities(activitiesData.setTestStationEmail(RandomStringUtils.randomAlphanumeric(6)).build());
        activitiesSteps.statusCodeShouldBe(400);
        activitiesSteps.validateActivityErrorTypeWithProperty("testStationEmail", "must be a valid email");
    }


    @Title("CVSB-163 / CVSB-2940 - API Consumer tries to create an activity with different range or format body property value - testStationEmail")
    @Test
    public void testStationEmailRandomString2() {

        activitiesSteps.postActivities(activitiesData.setTestStationEmail(RandomStringUtils.randomAlphanumeric(6) + "@" + RandomStringUtils.randomAlphanumeric(6) + ".").build());
        activitiesSteps.statusCodeShouldBe(400);
        activitiesSteps.validateActivityErrorTypeWithProperty("testStationEmail", "must be a valid email");
    }

    @Title("CVSB-163 / CVSB-2940 - API Consumer tries to create an activity with different range or format body property value - testStationEmail")
    @Test
    public void testStationEmailRandomString3() {

        activitiesSteps.postActivities(activitiesData.setTestStationEmail(RandomStringUtils.randomAlphanumeric(6) + "@." + RandomStringUtils.randomAlphanumeric(6)).build());
        activitiesSteps.statusCodeShouldBe(400);
        activitiesSteps.validateActivityErrorTypeWithProperty("testStationEmail", "must be a valid email");
    }

    @Title("CVSB-163 / CVSB-2929 - API Consumer tries to create an activity with missing request body property - activityType")
    @Test
    public void activityTestStationTypeMissing() {

        activitiesSteps.postActivities(activitiesData.build(), "testStationType", ToTypeConvertor.MISSING);
        activitiesSteps.statusCodeShouldBe(400);
        activitiesSteps.validateActivityErrorTypeWithProperty("testStationType", "is required");
    }


    @Title("CVSB-163 / CVSB-2930 - API Consumer tries to create an activity with null request body property value - testStationType")
    @Test
    public void activityTestStationTypeNull() {

        activitiesSteps.postActivities(activitiesData.build(), "testStationType", ToTypeConvertor.NULL);
        activitiesSteps.statusCodeShouldBe(400);
        activitiesSteps.validateActivityErrorTypeWithProperty("testStationType", "must be one of [atf, gvts, hq]");
    }

    @Title("CVSB-163 / CVSB-2931 - API Consumer tries to create an activity with different request body property type - testStationType")
    @Test
    public void activityTestStationTypeInteger() {

        activitiesSteps.postActivities(activitiesData.build(), "testStationType", RandomStringUtils.randomNumeric(6), ToTypeConvertor.INTEGER);
        activitiesSteps.statusCodeShouldBe(400);
        activitiesSteps.validateActivityErrorTypeWithProperty("testStationType", "must be one of [atf, gvts, hq]");
    }

    @Title("CVSB-163 / CVSB-2940 - API Consumer tries to create an activity with different range or format body property value - testStationType")
    @Test
    public void activityTestStationTypeString() {

        activitiesSteps.postActivities(activitiesData.setTestStationType(RandomStringUtils.randomAlphanumeric(6)).build());
        activitiesSteps.statusCodeShouldBe(400);
        activitiesSteps.validateActivityErrorTypeWithProperty("testStationType", "must be one of [atf, gvts, hq]");
    }

    @Title("CVSB-163 / CVSB-2929 - API Consumer tries to create an activity with missing request body property - testerName")
    @Test
    public void testerNameMissing() {

        activitiesSteps.postActivities(activitiesData.build(), "testerName", ToTypeConvertor.MISSING);
        activitiesSteps.statusCodeShouldBe(400);
        activitiesSteps.validateActivityErrorTypeWithProperty("testerName", "is required");
    }


    @Title("CVSB-163 / CVSB-2930 - API Consumer tries to create an activity with null request body property value - testerName")
    @Test
    public void testerNameNull() {

        activitiesSteps.postActivities(activitiesData.build(), "testerName", ToTypeConvertor.NULL);
        activitiesSteps.statusCodeShouldBe(400);
        activitiesSteps.validateActivityErrorTypeWithProperty("testerName", "must be a string");
    }

    @Title("CVSB-163 / CVSB-2931 - API Consumer tries to create an activity with different request body property type - testerName")
    @Test
    public void testerNameInteger() {

        activitiesSteps.postActivities(activitiesData.build(), "testerName", RandomStringUtils.randomNumeric(6), ToTypeConvertor.INTEGER);
        activitiesSteps.statusCodeShouldBe(400);
        activitiesSteps.validateActivityErrorTypeWithProperty("testerName", "must be a string");
    }

    @Title("CVSB-163 / CVSB-2929 - API Consumer tries to create an activity with missing request body property - testerStaffId")
    @Test
    public void testerStaffIdMissing() {

        activitiesSteps.postActivities(activitiesData.build(), "testerStaffId", ToTypeConvertor.MISSING);
        activitiesSteps.statusCodeShouldBe(400);
        activitiesSteps.validateActivityErrorTypeWithProperty("testerStaffId", "is required");
    }


    @Title("CVSB-163 / CVSB-2930 - API Consumer tries to create an activity with null request body property value - testerStaffId")
    @Test
    public void testerStaffIdNull() {

        activitiesSteps.postActivities(activitiesData.build(), "testerStaffId", ToTypeConvertor.NULL);
        activitiesSteps.statusCodeShouldBe(400);
        activitiesSteps.validateActivityErrorTypeWithProperty("testerStaffId", "must be a string");
    }

    @Title("CVSB-163 / CVSB-2931 - API Consumer tries to create an activity with different request body property type - testerStaffId")
    @Test
    public void testerStaffIdInteger() {

        activitiesSteps.postActivities(activitiesData.build(), "testerStaffId", RandomStringUtils.randomNumeric(6), ToTypeConvertor.INTEGER);
        activitiesSteps.statusCodeShouldBe(400);
        activitiesSteps.validateActivityErrorTypeWithProperty("testerStaffId", "must be a string");
    }


    @Title("CVSB-163 / CVSB-2941 - API Consumer tries to create an activity with a not defined body property - startTime")
    @Test
    public void extraPropertyStartTime() {

        activitiesSteps.postActivities(activitiesData.build(), "startTime", DataUtil.buildCurrentDateTime(), ToTypeConvertor.NEW_PROPERTY);
        activitiesSteps.statusCodeShouldBe(400);
        activitiesSteps.validateActivityErrorTypeWithProperty("startTime", "is not allowed");
    }


    @Title("CVSB-163 / CVSB-2941 - API Consumer tries to create an activity with a not defined body property - random alphanumeric")
    @Test
    public void extraPropertyRandom() {

        String propertyName = RandomStringUtils.randomAlphanumeric(10);

        activitiesSteps.postActivities(activitiesData.build(), propertyName, RandomStringUtils.randomAlphanumeric(6), ToTypeConvertor.NEW_PROPERTY);
        activitiesSteps.statusCodeShouldBe(400);
        activitiesSteps.validateActivityErrorTypeWithProperty(propertyName, "is not allowed");
    }

    @Title("CVSB-163 / CVSB-2943 - API Consumer tries to create an activity with different case for range body property value - activityType visit")
    @Test
    public void postActivitiesActivityTypeVisitCaseSensitive() {

        activitiesSteps.postActivities(ActivitiesData.buildActivitiesIdData().setActivityType("Visit").build());
        activitiesSteps.statusCodeShouldBe(400);
        activitiesSteps.validateActivityErrorTypeWithProperty("activityType", "must be one of [visit, wait]");

    }

    @Title("CVSB-163 / CVSB-2943 - API Consumer tries to create an activity with different case for range body property value - activityType wait")
    @Test
    public void postActivitiesActivityTypeWaitCaseSensitive() {

        activitiesSteps.postActivities(ActivitiesData.buildActivitiesIdData().setActivityType("Wait").build());
        activitiesSteps.statusCodeShouldBe(400);
        activitiesSteps.validateActivityErrorTypeWithProperty("activityType", "must be one of [visit, wait]");
    }

    @Title("CVSB-163 / CVSB-2943 - API Consumer tries to create an activity with different case for range body property value - testStationType atf")
    @Test
    public void postActivitiesTestStationTypeAtfCaseSensitive() {
        activitiesSteps.postActivities(ActivitiesData.buildActivitiesIdData().setTestStationType("Atf").build());
        activitiesSteps.statusCodeShouldBe(400);
        activitiesSteps.validateActivityErrorTypeWithProperty("testStationType", "must be one of [atf, gvts, hq]");
    }

    @Title("CVSB-163 / CVSB-2943 - API Consumer tries to create an activity with different case for range body property value - testStationType gvts")
    @Test
    public void postActivitiesTestStationTypeGvtsCaseSensitive() {
        activitiesSteps.postActivities(ActivitiesData.buildActivitiesIdData().setTestStationType("Gvts").build());
        activitiesSteps.statusCodeShouldBe(400);
        activitiesSteps.validateActivityErrorTypeWithProperty("testStationType", "must be one of [atf, gvts, hq]");
    }

    @Title("CVSB-163 / CVSB-2943 - API Consumer tries to create an activity with different case for range body property value - testStationType hq")
    @Test
    public void postActivitiesTestStationTypeHqCaseSensitive() {
        activitiesSteps.postActivities(ActivitiesData.buildActivitiesIdData().setTestStationType("Hq").build());
        activitiesSteps.statusCodeShouldBe(400);
        activitiesSteps.validateActivityErrorTypeWithProperty("testStationType", "must be one of [atf, gvts, hq]");
    }

    @Title("CVSB-163 / CVSB-2882 - AC9 API Consumer tries to create an already existent activity")
    @Test
    public void postActivitiesExisting() {
        Activities activities = activitiesData.build();
        activitiesSteps.postActivities(activities);
        activitiesSteps.statusCodeShouldBe(201);
        activitiesSteps.postActivities(activities);
        activitiesSteps.statusCodeShouldBe(403);
        activitiesSteps.validateActivityErrorTypeWithoutProperty("Staff ID " + activities.getTesterStaffId(), "already has an ongoing activity");
    }

}
