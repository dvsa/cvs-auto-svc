package util;

import steps.ActivitiesSteps;

public class TestUtils {
    public void postActivitiesNegTestParams(ActivitiesSteps activitiesSteps, Integer statusCode, String field, String errorMessage) {
        activitiesSteps.statusCodeShouldBe(statusCode);
        activitiesSteps.validateActivityErrorTypeWithProperty(field, errorMessage);
    }

    public void getActivitiesTestParams(ActivitiesSteps activitiesSteps, Integer statusCode, String stringData) {
        activitiesSteps.statusCodeShouldBe(statusCode);
        activitiesSteps.validateData(stringData);
    }

    public void postActivitiesTestParams(ActivitiesSteps activitiesSteps) {
        activitiesSteps.statusCodeShouldBe(201);
        activitiesSteps.responseShouldContainId();

    }
}
