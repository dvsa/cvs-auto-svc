package testresults;

import data.TestResultsData;
import model.testresults.TestResults;
import model.testresults.TestResultsGet;
import model.testresults.TestResultsStatus;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Test;
import org.junit.runner.RunWith;
import steps.TestResultsSteps;

import java.util.Random;

import static util.DataUtil.generateRandomExcludingValues;


@RunWith(SerenityRunner.class)
public class PostTestResultsPozLocationCancelled {

    @Steps
    TestResultsSteps testResultsSteps;

    private TestResults.Builder vehicleCancelledDataOld = TestResultsData.buildTestResultsCancelledDataOld();

    private void validateSavedDataOld() {
        testResultsSteps.getTestResults(vehicleCancelledDataOld.build().getSystemNumber(), TestResultsStatus.CANCELED);
        testResultsSteps.statusCodeShouldBe(200);
        testResultsSteps.validateData((TestResultsGet) vehicleCancelledDataOld.build());
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - vertical upper")
    @Test
    public void testResultsUpperVertical() {

        vehicleCancelledDataOld.setVin(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(16, vehicleCancelledDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledDataOld.build().getVrm()))
                .build().getTestTypes().get(0).getDefects().get(0).getAdditionalInformation().getLocation().setVertical("upper");

        testResultsSteps.postTestResults(vehicleCancelledDataOld.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld();
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - vertical lower")
    @Test
    public void testResultsLowerVertical() {

        vehicleCancelledDataOld.setVin(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(16, vehicleCancelledDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledDataOld.build().getVrm()))
                .build().getTestTypes().get(0).getDefects().get(0).getAdditionalInformation().getLocation().setVertical("lower");

        testResultsSteps.postTestResults(vehicleCancelledDataOld.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld();

    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3504 - TCD - API Consumer creates a new test result for submitted/canceled that allows null values - vertical")
    @Test
    public void testResultsNullVertical() {

        vehicleCancelledDataOld.setVin(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(16, vehicleCancelledDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledDataOld.build().getVrm()))
                .build().getTestTypes().get(0).getDefects().get(0).getAdditionalInformation().getLocation().setVertical(null);

        testResultsSteps.postTestResults(vehicleCancelledDataOld.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld();

    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - horizontal inner")
    @Test
    public void testResultsInnerHorizontal() {

        vehicleCancelledDataOld.setVin(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(16, vehicleCancelledDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledDataOld.build().getVrm()))
                .build().getTestTypes().get(0).getDefects().get(0).getAdditionalInformation().getLocation().setHorizontal("inner");

        testResultsSteps.postTestResults(vehicleCancelledDataOld.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld();

    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - horizontal outer")
    @Test
    public void testResultsOuterHorizontal() {

        vehicleCancelledDataOld.setVin(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(16, vehicleCancelledDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledDataOld.build().getVrm()))
                .build().getTestTypes().get(0).getDefects().get(0).getAdditionalInformation().getLocation().setHorizontal("outer");

        testResultsSteps.postTestResults(vehicleCancelledDataOld.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld();

    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3504 - TCD - API Consumer creates a new test result for submitted/canceled that allows null values - horizontal")
    @Test
    public void testResultsNullHorizontal() {

        vehicleCancelledDataOld.setVin(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(16, vehicleCancelledDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledDataOld.build().getVrm()))
                .build().getTestTypes().get(0).getDefects().get(0).getAdditionalInformation().getLocation().setHorizontal(null);

        testResultsSteps.postTestResults(vehicleCancelledDataOld.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld();

    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - lateral nearside")
    @Test
    public void testResultsNearsideLateral() {

        vehicleCancelledDataOld.setVin(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(16, vehicleCancelledDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledDataOld.build().getVrm()))
                .build().getTestTypes().get(0).getDefects().get(0).getAdditionalInformation().getLocation().setLateral("nearside");

        testResultsSteps.postTestResults(vehicleCancelledDataOld.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld();

    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - lateral centre")
    @Test
    public void testResultsCentreLateral() {

        vehicleCancelledDataOld.setVin(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(16, vehicleCancelledDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledDataOld.build().getVrm()))
                .build().getTestTypes().get(0).getDefects().get(0).getAdditionalInformation().getLocation().setLateral("centre");

        testResultsSteps.postTestResults(vehicleCancelledDataOld.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld();

    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - lateral offside")
    @Test
    public void testResultsOffsideLateral() {

        vehicleCancelledDataOld.setVin(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(16, vehicleCancelledDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledDataOld.build().getVrm()))
                .build().getTestTypes().get(0).getDefects().get(0).getAdditionalInformation().getLocation().setLateral("offside");

        testResultsSteps.postTestResults(vehicleCancelledDataOld.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld();

    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3504 - TCD - API Consumer creates a new test result for submitted/canceled that allows null values - lateral")
    @Test
    public void testResultsNullLateral() {

        vehicleCancelledDataOld.setVin(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(16, vehicleCancelledDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledDataOld.build().getVrm()))
                .build().getTestTypes().get(0).getDefects().get(0).getAdditionalInformation().getLocation().setLateral(null);

        testResultsSteps.postTestResults(vehicleCancelledDataOld.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld();

    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - longitudinal front")
    @Test
    public void testResultsFrontLongitudinal() {

        vehicleCancelledDataOld.setVin(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(16, vehicleCancelledDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledDataOld.build().getVrm()))
                .build().getTestTypes().get(0).getDefects().get(0).getAdditionalInformation().getLocation().setLongitudinal("front");

        testResultsSteps.postTestResults(vehicleCancelledDataOld.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld();

    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - longitudinal rear")
    @Test
    public void testResultsRearLongitudinal() {

        vehicleCancelledDataOld.setVin(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(16, vehicleCancelledDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledDataOld.build().getVrm()))
                .build().getTestTypes().get(0).getDefects().get(0).getAdditionalInformation().getLocation().setLongitudinal("rear");

        testResultsSteps.postTestResults(vehicleCancelledDataOld.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld();

    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3504 - TCD - API Consumer creates a new test result for submitted/canceled that allows null values - longitudinal")
    @Test
    public void testResultsNullLongitudinal() {

        vehicleCancelledDataOld.setVin(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(16, vehicleCancelledDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledDataOld.build().getVrm()))
                .build().getTestTypes().get(0).getDefects().get(0).getAdditionalInformation().getLocation().setLongitudinal(null);

        testResultsSteps.postTestResults(vehicleCancelledDataOld.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld();

    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - rowNumber")
    @Test
    public void testResultsRandomValidRowNumber() {

        vehicleCancelledDataOld.setVin(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(16, vehicleCancelledDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledDataOld.build().getVrm()))
                .build().getTestTypes().get(0).getDefects().get(0).getAdditionalInformation().getLocation().setRowNumber(new Random().nextInt(21));

        testResultsSteps.postTestResults(vehicleCancelledDataOld.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld();

    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3504 - TCD - API Consumer creates a new test result for submitted/canceled that allows null values - rowNumber")
    @Test
    public void testResultsNullRowNumber() {

        vehicleCancelledDataOld.setVin(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(16, vehicleCancelledDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledDataOld.build().getVrm()))
                .build().getTestTypes().get(0).getDefects().get(0).getAdditionalInformation().getLocation().setRowNumber(null);

        testResultsSteps.postTestResults(vehicleCancelledDataOld.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld();

    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - seatNumber")
    @Test
    public void testResultsRandomValidSeatNumber() {

        vehicleCancelledDataOld.setVin(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(16, vehicleCancelledDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledDataOld.build().getVrm()))
                .build().getTestTypes().get(0).getDefects().get(0).getAdditionalInformation().getLocation().setSeatNumber(new Random().nextInt(7));

        testResultsSteps.postTestResults(vehicleCancelledDataOld.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld();

    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3504 - TCD - API Consumer creates a new test result for submitted/canceled that allows null values - seatNumber")
    @Test
    public void testResultsNullSeatNumber() {

        vehicleCancelledDataOld.setVin(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(16, vehicleCancelledDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledDataOld.build().getVrm()))
                .build().getTestTypes().get(0).getDefects().get(0).getAdditionalInformation().getLocation().setSeatNumber(null);

        testResultsSteps.postTestResults(vehicleCancelledDataOld.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld();

    }


    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - axleNumber")
    @Test
    public void testResultsRandomValidAxleNumber() {

        vehicleCancelledDataOld.setVin(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(16, vehicleCancelledDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledDataOld.build().getVrm()))
                .build().getTestTypes().get(0).getDefects().get(0).getAdditionalInformation().getLocation().setAxleNumber(new Random().nextInt(11));

        testResultsSteps.postTestResults(vehicleCancelledDataOld.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld();

    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3504 - TCD - API Consumer creates a new test result for submitted/canceled that allows null values - axleNumber")
    @Test
    public void testResultsNullAxleNumber() {

        vehicleCancelledDataOld.setVin(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(16, vehicleCancelledDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledDataOld.build().getVrm()))
                .build().getTestTypes().get(0).getDefects().get(0).getAdditionalInformation().getLocation().setAxleNumber(null);

        testResultsSteps.postTestResults(vehicleCancelledDataOld.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld();

    }
}


