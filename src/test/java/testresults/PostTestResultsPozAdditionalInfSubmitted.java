package testresults;

import data.TestResultsData;
import model.testresults.TestResults;
import model.testresults.TestResultsGet;
import model.testresults.TestResultsStatus;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import steps.TestResultsSteps;

import static util.DataUtil.generateRandomExcludingValues;


@RunWith(SerenityRunner.class)
public class PostTestResultsPozAdditionalInfSubmitted {

    @Steps
    TestResultsSteps testResultsSteps;

    private TestResults.Builder vehicleSubmittedDataOld = TestResultsData.buildTestResultsSubmittedDataOld();

    private void validateSavedDataOld() {

        testResultsSteps.getTestResults(vehicleSubmittedDataOld.build().getSystemNumber(), TestResultsStatus.SUBMITTED);
        testResultsSteps.statusCodeShouldBe(200);
        testResultsSteps.validateData((TestResultsGet) vehicleSubmittedDataOld.build());
    }



    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - notes")
    @Test
    public void testResultsRandomNotes() {

        vehicleSubmittedDataOld.setVin(generateRandomExcludingValues(21, vehicleSubmittedDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(16, vehicleSubmittedDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleSubmittedDataOld.build().getVrm())).build()
                .getTestTypes().get(0).getDefects().get(0).getAdditionalInformation().setNotes(RandomStringUtils.randomAlphanumeric(500));

        testResultsSteps.postTestResults(vehicleSubmittedDataOld.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld();
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3504 - TCD - API Consumer creates a new test result for submitted/canceled that allows null values - notes")
    @Test
    public void testResultsNullNotes() {

        vehicleSubmittedDataOld.setVin(generateRandomExcludingValues(21, vehicleSubmittedDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(16, vehicleSubmittedDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleSubmittedDataOld.build().getVrm())).build()
                .getTestTypes().get(0).getDefects().get(0).getAdditionalInformation().setNotes(null);

        testResultsSteps.postTestResults(vehicleSubmittedDataOld.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld();
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3486 - API Consumer creates a new test results for submitted/canceled with no min restriction - notes")
    @Test
    public void testResultsEmptyNotes() {

        vehicleSubmittedDataOld.setVin(generateRandomExcludingValues(21, vehicleSubmittedDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(16, vehicleSubmittedDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleSubmittedDataOld.build().getVrm())).build()
                .getTestTypes().get(0).getDefects().get(0).getAdditionalInformation().setNotes("");

        testResultsSteps.postTestResults(vehicleSubmittedDataOld.build());
        vehicleSubmittedDataOld.build().getTestTypes().get(0).getDefects().get(0).getAdditionalInformation().setNotes(null);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld();
    }


    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3504 - TCD - API Consumer creates a new test result for submitted/canceled that allows null values - location")
    @Test
    public void testResultsAdvisoryAndNullLocation() {

        vehicleSubmittedDataOld.setVin(generateRandomExcludingValues(21, vehicleSubmittedDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(16, vehicleSubmittedDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleSubmittedDataOld.build().getVrm())).build()
                .getTestTypes().get(0).getDefects().get(0).setDeficiencyCategory("advisory").getAdditionalInformation().setLocation(null);

        testResultsSteps.postTestResults(vehicleSubmittedDataOld.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");

    }
}
