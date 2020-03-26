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
public class PostTestResultsPozLocationSubmitted {

    @Steps
    TestResultsSteps testResultsSteps;

    private TestResults.Builder vehicleSubmittedDataOld = TestResultsData.buildTestResultsSubmittedDataOld();

    private void validateSavedDataOld() {
        testResultsSteps.getTestResults(vehicleSubmittedDataOld.build().getSystemNumber(), TestResultsStatus.SUBMITTED);
        testResultsSteps.statusCodeShouldBe(200);
        testResultsSteps.validateData((TestResultsGet) vehicleSubmittedDataOld.build());
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - vertical upper")
    @Test
    public void testResultsUpperVertical() {

        vehicleSubmittedDataOld.setVin(generateRandomExcludingValues(21, vehicleSubmittedDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(21, vehicleSubmittedDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleSubmittedDataOld.build().getVrm()))
                .build().getTestTypes().get(0).getDefects().get(0).getAdditionalInformation().getLocation().setVertical("upper");

        testResultsSteps.postTestResults(vehicleSubmittedDataOld.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld();

    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - vertical lower")
    @Test
    public void testResultsLowerVertical() {

        vehicleSubmittedDataOld.setVin(generateRandomExcludingValues(21, vehicleSubmittedDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(21, vehicleSubmittedDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleSubmittedDataOld.build().getVrm()))
                .build().getTestTypes().get(0).getDefects().get(0).getAdditionalInformation().getLocation().setVertical("lower");

        testResultsSteps.postTestResults(vehicleSubmittedDataOld.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld();

    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3504 - TCD - API Consumer creates a new test result for submitted/canceled that allows null values - vertical")
    @Test
    public void testResultsNullVertical() {

        vehicleSubmittedDataOld.setVin(generateRandomExcludingValues(21, vehicleSubmittedDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(21, vehicleSubmittedDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleSubmittedDataOld.build().getVrm()))
                .build().getTestTypes().get(0).getDefects().get(0).getAdditionalInformation().getLocation().setVertical(null);

        testResultsSteps.postTestResults(vehicleSubmittedDataOld.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld();

    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - horizontal inner")
    @Test
    public void testResultsInnerHorizontal() {

        vehicleSubmittedDataOld.setVin(generateRandomExcludingValues(21, vehicleSubmittedDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(21, vehicleSubmittedDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleSubmittedDataOld.build().getVrm()))
                .build().getTestTypes().get(0).getDefects().get(0).getAdditionalInformation().getLocation().setHorizontal("inner");

        testResultsSteps.postTestResults(vehicleSubmittedDataOld.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld();

    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - horizontal outer")
    @Test
    public void testResultsOuterHorizontal() {

        vehicleSubmittedDataOld.setVin(generateRandomExcludingValues(21, vehicleSubmittedDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(21, vehicleSubmittedDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleSubmittedDataOld.build().getVrm()))
                .build().getTestTypes().get(0).getDefects().get(0).getAdditionalInformation().getLocation().setHorizontal("outer");

        testResultsSteps.postTestResults(vehicleSubmittedDataOld.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld();

    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3504 - TCD - API Consumer creates a new test result for submitted/canceled that allows null values - horizontal")
    @Test
    public void testResultsNullHorizontal() {

        vehicleSubmittedDataOld.setVin(generateRandomExcludingValues(21, vehicleSubmittedDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(21, vehicleSubmittedDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleSubmittedDataOld.build().getVrm()))
                .build().getTestTypes().get(0).getDefects().get(0).getAdditionalInformation().getLocation().setHorizontal(null);

        testResultsSteps.postTestResults(vehicleSubmittedDataOld.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld();

    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - lateral nearside")
    @Test
    public void testResultsNearsideLateral() {

        vehicleSubmittedDataOld.setVin(generateRandomExcludingValues(21, vehicleSubmittedDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(21, vehicleSubmittedDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleSubmittedDataOld.build().getVrm()))
                .build().getTestTypes().get(0).getDefects().get(0).getAdditionalInformation().getLocation().setLateral("nearside");

        testResultsSteps.postTestResults(vehicleSubmittedDataOld.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld();

    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - lateral centre")
    @Test
    public void testResultsCentreLateral() {

        vehicleSubmittedDataOld.setVin(generateRandomExcludingValues(21, vehicleSubmittedDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(21, vehicleSubmittedDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleSubmittedDataOld.build().getVrm()))
                .build().getTestTypes().get(0).getDefects().get(0).getAdditionalInformation().getLocation().setLateral("centre");

        testResultsSteps.postTestResults(vehicleSubmittedDataOld.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld();

    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - lateral offside")
    @Test
    public void testResultsOffsideLateral() {

        vehicleSubmittedDataOld.setVin(generateRandomExcludingValues(21, vehicleSubmittedDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(21, vehicleSubmittedDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleSubmittedDataOld.build().getVrm()))
                .build().getTestTypes().get(0).getDefects().get(0).getAdditionalInformation().getLocation().setLateral("offside");

        testResultsSteps.postTestResults(vehicleSubmittedDataOld.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld();

    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3504 - TCD - API Consumer creates a new test result for submitted/canceled that allows null values - lateral")
    @Test
    public void testResultsNullLateral() {

        vehicleSubmittedDataOld.setVin(generateRandomExcludingValues(21, vehicleSubmittedDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(21, vehicleSubmittedDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleSubmittedDataOld.build().getVrm()))
                .build().getTestTypes().get(0).getDefects().get(0).getAdditionalInformation().getLocation().setLateral(null);

        testResultsSteps.postTestResults(vehicleSubmittedDataOld.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld();

    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - longitudinal front")
    @Test
    public void testResultsFrontLongitudinal() {

        vehicleSubmittedDataOld.setVin(generateRandomExcludingValues(21, vehicleSubmittedDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(21, vehicleSubmittedDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleSubmittedDataOld.build().getVrm()))
                .build().getTestTypes().get(0).getDefects().get(0).getAdditionalInformation().getLocation().setLongitudinal("front");

        testResultsSteps.postTestResults(vehicleSubmittedDataOld.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld();

    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - longitudinal rear")
    @Test
    public void testResultsRearLongitudinal() {

        vehicleSubmittedDataOld.setVin(generateRandomExcludingValues(21, vehicleSubmittedDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(21, vehicleSubmittedDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleSubmittedDataOld.build().getVrm()))
                .build().getTestTypes().get(0).getDefects().get(0).getAdditionalInformation().getLocation().setLongitudinal("rear");

        testResultsSteps.postTestResults(vehicleSubmittedDataOld.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld();

    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3504 - TCD - API Consumer creates a new test result for submitted/canceled that allows null values - longitudinal")
    @Test
    public void testResultsNullLongitudinal() {

        vehicleSubmittedDataOld.setVin(generateRandomExcludingValues(21, vehicleSubmittedDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(21, vehicleSubmittedDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleSubmittedDataOld.build().getVrm()))
                .build().getTestTypes().get(0).getDefects().get(0).getAdditionalInformation().getLocation().setLongitudinal(null);

        testResultsSteps.postTestResults(vehicleSubmittedDataOld.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld();

    }


    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - rowNumber")
    @Test
    public void testResultsRandomValidRowNumber() {

        vehicleSubmittedDataOld.setVin(generateRandomExcludingValues(21, vehicleSubmittedDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(21, vehicleSubmittedDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleSubmittedDataOld.build().getVrm()))
                .build().getTestTypes().get(0).getDefects().get(0).getAdditionalInformation().getLocation().setRowNumber(new Random().nextInt(21));

        testResultsSteps.postTestResults(vehicleSubmittedDataOld.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld();

    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3504 - TCD - API Consumer creates a new test result for submitted/canceled that allows null values - rowNumber")
    @Test
    public void testResultsNullRowNumber() {

        vehicleSubmittedDataOld.setVin(generateRandomExcludingValues(21, vehicleSubmittedDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(21, vehicleSubmittedDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleSubmittedDataOld.build().getVrm()))
                .build().getTestTypes().get(0).getDefects().get(0).getAdditionalInformation().getLocation().setRowNumber(null);

        testResultsSteps.postTestResults(vehicleSubmittedDataOld.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld();

    }


    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - seatNumber")
    @Test
    public void testResultsRandomValidSeatNumber() {

        vehicleSubmittedDataOld.setVin(generateRandomExcludingValues(21, vehicleSubmittedDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(21, vehicleSubmittedDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleSubmittedDataOld.build().getVrm()))
                .build().getTestTypes().get(0).getDefects().get(0).getAdditionalInformation().getLocation().setSeatNumber(new Random().nextInt(7));

        testResultsSteps.postTestResults(vehicleSubmittedDataOld.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld();

    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3504 - TCD - API Consumer creates a new test result for submitted/canceled that allows null values - seatNumber")
    @Test
    public void testResultsNullSeatNumber() {

        vehicleSubmittedDataOld.setVin(generateRandomExcludingValues(21, vehicleSubmittedDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(21, vehicleSubmittedDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleSubmittedDataOld.build().getVrm()))
                .build().getTestTypes().get(0).getDefects().get(0).getAdditionalInformation().getLocation().setSeatNumber(null);

        testResultsSteps.postTestResults(vehicleSubmittedDataOld.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld();

    }


    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - axleNumber")
    @Test
    public void testResultsRandomValidAxleNumber() {

        vehicleSubmittedDataOld.setVin(generateRandomExcludingValues(21, vehicleSubmittedDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(21, vehicleSubmittedDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleSubmittedDataOld.build().getVrm()))
                .build().getTestTypes().get(0).getDefects().get(0).getAdditionalInformation().getLocation().setAxleNumber(new Random().nextInt(11));

        testResultsSteps.postTestResults(vehicleSubmittedDataOld.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld();

    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3504 - TCD - API Consumer creates a new test result for submitted/canceled that allows null values - axleNumber")
    @Test
    public void testResultsNullAxleNumber() {

        vehicleSubmittedDataOld.setVin(generateRandomExcludingValues(21, vehicleSubmittedDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(21, vehicleSubmittedDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleSubmittedDataOld.build().getVrm()))
                .build().getTestTypes().get(0).getDefects().get(0).getAdditionalInformation().getLocation().setAxleNumber(null);

        testResultsSteps.postTestResults(vehicleSubmittedDataOld.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld();

    }
}
