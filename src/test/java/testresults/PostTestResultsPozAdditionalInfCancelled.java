package testresults;

import data.TestResultsData;
import model.testresults.TestResults;
import model.testresults.TestResultsGet;
import model.testresults.TestResultsStatus;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import steps.TestResultsSteps;

import static util.DataUtil.generateRandomExcludingValues;

@RunWith(SerenityRunner.class)
public class PostTestResultsPozAdditionalInfCancelled {

    @Steps
    TestResultsSteps testResultsSteps;

    private TestResults.Builder vehicleCancelledDataOld = TestResultsData.buildTestResultsCancelledDataOld();

    private void validateSavedDataOld() {

        testResultsSteps.getTestResults(vehicleCancelledDataOld.build().getSystemNumber(), TestResultsStatus.CANCELED);
        testResultsSteps.statusCodeShouldBe(200);
        testResultsSteps.validateData((TestResultsGet) vehicleCancelledDataOld.build());
    }

    @Before
    @Title("warm up test")
    @Test
    public void testResultsWarmUpTest() {
        testResultsSteps.postTestResults(vehicleCancelledDataOld.build());
    }
    

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - notes")
    @Test
    public void testResultsRandomNotes() {

        vehicleCancelledDataOld.setVin(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(16, vehicleCancelledDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledDataOld.build().getVrm())).build().
                getTestTypes().get(0).getDefects().get(0).getAdditionalInformation().setNotes(RandomStringUtils.randomAlphanumeric(500));

        testResultsSteps.postTestResults(vehicleCancelledDataOld.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld();
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3504 - TCD - API Consumer creates a new test result for submitted/canceled that allows null values - notes")
    @Test
    public void testResultsNullNotes() {

        vehicleCancelledDataOld.setVin(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(16, vehicleCancelledDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledDataOld.build().getVrm())).build()
                .getTestTypes().get(0).getDefects().get(0).getAdditionalInformation().setNotes(null);

        testResultsSteps.postTestResults(vehicleCancelledDataOld.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld();
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3486 - API Consumer creates a new test results for submitted/canceled with no min restriction - notes")
    @Test
    public void testResultsEmptyNotes() {

        vehicleCancelledDataOld.setVin(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(16, vehicleCancelledDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledDataOld.build().getVrm())).build()
                .getTestTypes().get(0).getDefects().get(0).getAdditionalInformation().setNotes("");

        testResultsSteps.postTestResults(vehicleCancelledDataOld.build());
        testResultsSteps.statusCodeShouldBe(201);
        vehicleCancelledDataOld.build().getTestTypes().get(0).getDefects().get(0).getAdditionalInformation().setNotes(null);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld();
    }


    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3504 - TCD - API Consumer creates a new test result for submitted/canceled that allows null values - location")
    @Test
    public void testResultsAdvisoryAndNullLocation() {

        vehicleCancelledDataOld.setVin(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(16, vehicleCancelledDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledDataOld.build().getVrm())).build()
                .getTestTypes().get(0).getDefects().get(0).setDeficiencyCategory("advisory").getAdditionalInformation().setLocation(null);

        testResultsSteps.postTestResults(vehicleCancelledDataOld.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");

    }
}
