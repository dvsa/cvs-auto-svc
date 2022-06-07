package util;

import steps.ActivitiesSteps;

public class TestUtils {
    public void postActivitiesNegTestParams(ActivitiesSteps activitiesSteps, Integer statusCode, String field, String errorMessage) {
        activitiesSteps.statusCodeShouldBe(statusCode);
        activitiesSteps.validateActivityErrorTypeWithProperty(field, errorMessage);
    }

}
