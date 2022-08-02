package testresults;

import data.TestResultsData;
import model.testresults.TestResults;
import model.testresults.TestResultsGet;
import model.testresults.TestResultsStatus;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import steps.TestResultsSteps;
import util.DataUtil;
import static util.DataUtil.generateRandomExcludingValues;


@RunWith(SerenityRunner.class)
public class PostTestResultsPozMainLvlCancelled {

    @Steps
    TestResultsSteps testResultsSteps;

    private TestResults.Builder vehicleCancelledDataOld = TestResultsData.buildTestResultsCancelledDataOld();

    private void validateSavedDataOld() {
        testResultsSteps.getTestResults(vehicleCancelledDataOld.build().getSystemNumber(), TestResultsStatus.CANCELED);
        testResultsSteps.statusCodeShouldBe(200);
        testResultsSteps.validateData((TestResultsGet) vehicleCancelledDataOld.build());

    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - testStationName")
    @Test
    public void testResultsTestStationNameRandString() {

        testResultsSteps.postTestResults(vehicleCancelledDataOld.setVin(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledDataOld.build().getVrm()))
                .setTestStationName(RandomStringUtils.randomAlphanumeric(1, 998)).build());

        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld();
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3486 - API Consumer creates a new test results for submitted/canceled with no min restriction - testStationName")
    @Test
    public void testResultsTestStationNameEmpty() {

        testResultsSteps.postTestResults(vehicleCancelledDataOld.setVin(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledDataOld.build().getVrm()))
                .setTestStationName("").build());

        testResultsSteps.statusCodeShouldBe(201);
        vehicleCancelledDataOld.setTestStationName(null);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld();
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 - API Consumer creates a new test results for submitted/canceled with null value - testStationName")
    @Test
    public void testResultsTestStationNameNull() {

        testResultsSteps.postTestResults(vehicleCancelledDataOld.setVin(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledDataOld.build().getVrm()))
                .setTestStationName(null).build());

        testResultsSteps.statusCodeShouldBe(201);
        vehicleCancelledDataOld.setTestStationName(null);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld();
    }


    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - testStationPNumber")
    @Test
    public void testResultsTestStationPNumberRandString() {

        testResultsSteps.postTestResults(vehicleCancelledDataOld.setVin(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledDataOld.build().getVrm()))
                .setTestStationPNumber(RandomStringUtils.randomAlphanumeric(1, 19)).build());

        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld();
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3486 - API Consumer creates a new test results for submitted/canceled with no min restriction - testStationPNumber")
    @Test
    public void testResultsTestStationPNumberEmpty() {

        testResultsSteps.postTestResults(vehicleCancelledDataOld.setVin(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledDataOld.build().getVrm()))
                .setTestStationPNumber("").build());

        testResultsSteps.statusCodeShouldBe(201);
        vehicleCancelledDataOld.setTestStationPNumber(null);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld();
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3506 - API Consumer tries to create a new test result for submitted/canceled with null value - testStationPNumber")
    @Test
    public void testResultsNullTestStationPNumber() {

        testResultsSteps.postTestResults(vehicleCancelledDataOld.setVin(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledDataOld.build().getVrm()))
                .setTestStationPNumber(null).build());

        testResultsSteps.statusCodeShouldBe(201);
        vehicleCancelledDataOld.setTestStationPNumber(null);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld();
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - testStationType atf")
    @Test
    public void testResultsValueTestStationTypeAtf() {

        testResultsSteps.postTestResults(vehicleCancelledDataOld.setVin(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledDataOld.build().getVrm()))
                .setTestStationType("atf").build());

        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld();
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - testStationType gvts")
    @Test
    public void testResultsValueTestStationTypeGvts() {

        testResultsSteps.postTestResults(vehicleCancelledDataOld.setVin(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledDataOld.build().getVrm()))
                .setTestStationType("gvts").build());

        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld();
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - testStationType hq")
    @Test
    public void testResultsValueTestStationTypeHq() {

        testResultsSteps.postTestResults(vehicleCancelledDataOld.setVin(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledDataOld.build().getVrm()))
                .setTestStationType("hq").build());

        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld();
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - testerName")
    @Test
    public void testResultsRandomTesterName() {

        testResultsSteps.postTestResults(vehicleCancelledDataOld.setVin(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledDataOld.build().getVrm()))
                .setTesterName(RandomStringUtils.randomAlphanumeric(60)).build());

        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld();
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3486 - API Consumer creates a new test results for submitted/canceled with no min restriction - testerName")
    @Test
    public void testResultsLengthEmptyTesterName() {

        testResultsSteps.postTestResults(vehicleCancelledDataOld.setVin(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledDataOld.build().getVrm()))
                .setTesterName("").build());

        testResultsSteps.statusCodeShouldBe(201);
        vehicleCancelledDataOld.setTesterName(null);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld();
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3486 - API Consumer creates a new test results for submitted/canceled with null value - testerName")
    @Test
    public void testResultsNullTesterName() {

        testResultsSteps.postTestResults(vehicleCancelledDataOld.setVin(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledDataOld.build().getVrm()))
                .setTesterName(null).build());

        testResultsSteps.statusCodeShouldBe(201);
        vehicleCancelledDataOld.setTesterName(null);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld();
    }


    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - testerStaffId")
    @Test
    public void testResultsRandomTesterStaffId() {

        testResultsSteps.postTestResults(vehicleCancelledDataOld.setVin(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledDataOld.build().getVrm()))
                .setTesterStaffId(RandomStringUtils.randomAlphanumeric(9)).build());

        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld();
    }

    @Ignore ("empty testerStaffId should be accepted but returned an error - instead returns bad Gateway - defect id CVSB-9018")
    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3486 - API Consumer creates a new test results for submitted/canceled with no min restriction - testerStaffId")
    public void testResultsLengthEmptyTesterStaffId() {

        testResultsSteps.postTestResults(vehicleCancelledDataOld.setVin(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledDataOld.build().getVrm()))
                .setTesterStaffId("").build());

        testResultsSteps.statusCodeShouldBe(201);
        vehicleCancelledDataOld.setTesterStaffId(null);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld();
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - testerEmailAddress")
    @Test
    public void testResultsRandomTesterEmailAddress() {

        testResultsSteps.postTestResults(vehicleCancelledDataOld.setVin(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledDataOld.build().getVrm()))
                .setTesterEmailAddress(RandomStringUtils.randomAlphanumeric(60)).build());

        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld();
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3486 - API Consumer creates a new test results for submitted/canceled with no min restriction - testerEmailAddress")
    @Test
    public void testResultsLengthEmptyTesterEmailAddress() {

        testResultsSteps.postTestResults(vehicleCancelledDataOld.setVin(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledDataOld.build().getVrm()))
                .setTesterEmailAddress("").build());

        testResultsSteps.statusCodeShouldBe(201);
        vehicleCancelledDataOld.setTesterEmailAddress(null);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld();
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3506 - API Consumer tries to create a new test result for submitted/canceled with null value - testerEmailAddress")
    @Test
    public void testResultsNullTesterEmailAddress() {

        testResultsSteps.postTestResults(vehicleCancelledDataOld.setVin(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledDataOld.build().getVrm()))
                .setTesterEmailAddress(null).build());

        testResultsSteps.statusCodeShouldBe(201);
        vehicleCancelledDataOld.setTesterEmailAddress(null);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld();
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - testStartTimestamp")
    @Test
    public void testResultsTestStartTimestamp() {

        testResultsSteps.postTestResults(vehicleCancelledDataOld.setVin(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledDataOld.build().getVrm()))
                .setTestStartTimestamp(DataUtil.buildCurrentDateTime()).build());

        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld();
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - testEndTimestamp")
    @Test
    public void testResultsTestEndTimestamp() {

        testResultsSteps.postTestResults(vehicleCancelledDataOld.setVin(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledDataOld.build().getVrm()))
                .setTestEndTimestamp(DataUtil.buildCurrentDateTime()).build());

        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld();
    }


    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - reasonForCancellation")
    @Test
    public void testResultsLengthMaxReasonForCancellation() {

        testResultsSteps.postTestResults(vehicleCancelledDataOld.setVin(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledDataOld.build().getVrm()))
                .setReasonForCancellation(RandomStringUtils.randomAlphanumeric(500)).build());

        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld();
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3486 - API Consumer creates a new test results for submitted/canceled with no min restriction - reasonForCancellation")
    @Test
    public void testResultsEmptyReasonForCancellation() {

        testResultsSteps.postTestResults(vehicleCancelledDataOld.setVin(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledDataOld.build().getVrm()))
                .setReasonForCancellation("").build());

        testResultsSteps.statusCodeShouldBe(201);
        vehicleCancelledDataOld.setReasonForCancellation(null);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld();
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3506 - API Consumer tries to create a new test result for submitted/canceled with null value for not nullable - reasonForCancellation")
    @Test
    public void testResultsNullReasonForCancellation() {

        testResultsSteps.postTestResults(vehicleCancelledDataOld.setVin(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledDataOld.build().getVrm()))
                .setReasonForCancellation(null).build());

        testResultsSteps.statusCodeShouldBe(201);
        vehicleCancelledDataOld.setReasonForCancellation(null);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld();
    }


    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - vehicleClass code 1")
    @Test
    public void testResultsValueVehicleClassCode1() {

        vehicleCancelledDataOld.setVin(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledDataOld.build().getVrm()))
                .getVehicleClass().setCode("1");
        testResultsSteps.postTestResults(vehicleCancelledDataOld.build());

        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld();
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - vehicleClass code 2")
    @Test
    public void testResultsValueVehicleClassCode2() {

        vehicleCancelledDataOld.setVin(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledDataOld.build().getVrm()))
                .getVehicleClass().setCode("2");
        testResultsSteps.postTestResults(vehicleCancelledDataOld.build());

        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld();
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - vehicleClass code 3")
    @Test
    public void testResultsValueVehicleClassCode3() {

        vehicleCancelledDataOld.setVin(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledDataOld.build().getVrm()))
                .getVehicleClass().setCode("3");
        testResultsSteps.postTestResults(vehicleCancelledDataOld.build());

        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld();
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - vehicleClass code n")
    @Test
    public void testResultsValueVehicleClassCoden() {

        vehicleCancelledDataOld.setVin(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledDataOld.build().getVrm()))
                .getVehicleClass().setCode("n");
        testResultsSteps.postTestResults(vehicleCancelledDataOld.build());

        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld();
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - vehicleClass code t")
    @Test
    public void testResultsValueVehicleClassCodet() {

        vehicleCancelledDataOld.setVin(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledDataOld.build().getVrm()))
                .getVehicleClass().setCode("t");
        testResultsSteps.postTestResults(vehicleCancelledDataOld.build());

        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld();
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - vehicleClass code l")
    @Test
    public void testResultsValueVehicleClassCodel() {

        vehicleCancelledDataOld.setVin(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledDataOld.build().getVrm()))
                .getVehicleClass().setCode("l");
        testResultsSteps.postTestResults(vehicleCancelledDataOld.build());

        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld();
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - vehicleClass code s")
    @Test
    public void testResultsValueVehicleClassCodes() {

        vehicleCancelledDataOld.setVin(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledDataOld.build().getVrm()))
                .getVehicleClass().setCode("s");
        testResultsSteps.postTestResults(vehicleCancelledDataOld.build());

        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld();
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - vehicleClass code v")
    @Test
    public void testResultsValueVehicleClassCodev() {

        vehicleCancelledDataOld.setVin(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledDataOld.build().getVrm()))
                .getVehicleClass().setCode("v");
        testResultsSteps.postTestResults(vehicleCancelledDataOld.build());

        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld();
    }


    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - vehicleClass description over 200cc or with a sidecar")
    @Test
    public void testResultsValueVehicleClassDescriptionValuesOne() {

        vehicleCancelledDataOld.setVin(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledDataOld.build().getVrm()))
                .getVehicleClass().setDescription("motorbikes over 200cc or with a sidecar");
        testResultsSteps.postTestResults(vehicleCancelledDataOld.build());

        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld();
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - vehicleClass description small psv (ie: less than or equal to 22 seats)")
    @Test
    public void testResultsValueVehicleClassDescriptionValuesTwo() {

        vehicleCancelledDataOld.setVin(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledDataOld.build().getVrm()))
                .getVehicleClass().setDescription("small psv (ie: less than or equal to 22 seats)");
        testResultsSteps.postTestResults(vehicleCancelledDataOld.build());

        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld();
    }


    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - vehicleClass description not applicable")
    @Test
    public void testResultsValueVehicleClassDescriptionValuesThree() {

        vehicleCancelledDataOld.setVin(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledDataOld.build().getVrm()))
                .getVehicleClass().setDescription("not applicable");
        testResultsSteps.postTestResults(vehicleCancelledDataOld.build());

        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld();
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - vehicleClass description motorbikes up to 200cc")
    @Test
    public void testResultsValueVehicleClassDescriptionValuesFour() {

        vehicleCancelledDataOld.setVin(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledDataOld.build().getVrm()))
                .getVehicleClass().setDescription("motorbikes up to 200cc");
        testResultsSteps.postTestResults(vehicleCancelledDataOld.build());

        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld();
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - vehicleClass description trailer")
    @Test
    public void testResultsValueVehicleClassDescriptionValuesFive() {

        vehicleCancelledDataOld.setVin(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledDataOld.build().getVrm()))
                .getVehicleClass().setDescription("trailer");
        testResultsSteps.postTestResults(vehicleCancelledDataOld.build());

        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld();
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - vehicleClass description arge psv(ie: greater than 23 seats)")
    @Test
    public void testResultsValueVehicleClassDescriptionValuesSix() {

        vehicleCancelledDataOld.setVin(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledDataOld.build().getVrm()))
                .getVehicleClass().setDescription("large psv(ie: greater than 23 seats)");
        testResultsSteps.postTestResults(vehicleCancelledDataOld.build());

        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld();
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - vehicleClass description 3 wheelers")
    @Test
    public void testResultsValueVehicleClassDescriptionValuesSeven() {

        vehicleCancelledDataOld.setVin(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledDataOld.build().getVrm()))
                .getVehicleClass().setDescription("3 wheelers");
        testResultsSteps.postTestResults(vehicleCancelledDataOld.build());

        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld();
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - vehicleClass description heavy goods vehicle")
    @Test
    public void testResultsValueVehicleClassDescriptionValuesEight() {

        vehicleCancelledDataOld.setVin(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledDataOld.build().getVrm()))
                .getVehicleClass().setDescription("heavy goods vehicle");
        testResultsSteps.postTestResults(vehicleCancelledDataOld.build());

        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld();
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - vehicleType psv")
    @Test
    public void testResultsValueVehicleTypeValueOne() {

        testResultsSteps.postTestResults(vehicleCancelledDataOld.setVin(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledDataOld.build().getVrm()))
                .setVehicleType("psv").build());

        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld();
    }


    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - vehicleType hgv")
    @Test
    public void testResultsValueVehicleTypeValueTwo() {

        testResultsSteps.postTestResults(vehicleCancelledDataOld.setVin(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledDataOld.build().getVrm()))
                .setVehicleType("hgv").build());

        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorDataContains("numberOfSeatbeltsFitted", "is not allowed");
        testResultsSteps.validatePostErrorDataContains("lastSeatbeltInstallationCheckDate", "is not allowed");
        testResultsSteps.validatePostErrorDataContains("seatbeltInstallationCheckDate", "is not allowed");

    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - vehicleType trl")
    @Test
    public void testResultsValueVehicleTypeValueThree() {

        testResultsSteps.postTestResults(vehicleCancelledDataOld.setVin(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledDataOld.build().getVrm()))
                .setVehicleType("trl").build());
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorDataContains("trailerId", "is required");

    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - numberOfSeats")
    @Test
    public void testResultsRandomNumberOfSeats() {

        testResultsSteps.postTestResults(vehicleCancelledDataOld.setVin(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledDataOld.build().getVrm()))
                .setNumberOfSeats(Integer.valueOf(RandomStringUtils.randomNumeric(5))).build());

        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld();
    }


    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - noOfAxles")
    @Test
    public void testResultsRandomNumberNoOfAxles2() {

        testResultsSteps.postTestResults(vehicleCancelledDataOld.setVin(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledDataOld.build().getVrm()))
                .setNoOfAxles(2).build());

        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld();
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - noOfAxles")
    @Test
    public void testResultsRandomNumberNoOfAxles3() {

        testResultsSteps.postTestResults(vehicleCancelledDataOld.setVin(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledDataOld.build().getVrm()))
                .setNoOfAxles(3).build());

        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld();
    }


    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - vehicleConfiguration rigid")
    @Test
    public void testResultsVehicleConfigurationValueOne() {

        testResultsSteps.postTestResults(vehicleCancelledDataOld.setVin(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledDataOld.build().getVrm()))
                .setVehicleConfiguration("rigid").build());

        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld();
    }

    @Ignore ("vehicleConfiguration = articulated should have returned 201 - instead returns bad Gateway - defect Id CVSB-9017")
    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - vehicleConfiguration articulated")
    public void testResultsVehicleConfigurationValueTwo() {

        testResultsSteps.postTestResults(vehicleCancelledDataOld.setVin(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledDataOld.build().getVrm()))
                .setVehicleConfiguration("articulated").build());

        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld();
    }


    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - odometerReading")
    @Test
    public void testResultsRandomOdometerReading() {

        testResultsSteps.postTestResults(vehicleCancelledDataOld.setVin(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledDataOld.build().getVrm()))
                .setOdometerReading(Integer.valueOf(RandomStringUtils.randomNumeric(5))).build());

        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld();
    }


    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3504 - TCD - API Consumer creates a new test result for submitted/canceled that allows null values - odometerReading")
    @Test
    public void testResultsNullOdometerReading() {

        testResultsSteps.postTestResults(vehicleCancelledDataOld.setVin(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledDataOld.build().getVrm()))
                .setOdometerReading(null).build());

        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld();
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3504 - TCD - API Consumer creates a new test result for submitted/canceled that allows null values - odometerReadingUnits")
    @Test
    public void testResultsNullOdometerReadingUnits() {

        testResultsSteps.postTestResults(vehicleCancelledDataOld.setVin(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledDataOld.build().getVrm()))
                .setOdometerReadingUnits(null).build());

        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld();
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - odometerReadingUnits kilometres")
    @Test
    public void testResultsOdometerReadingUnitsValueOne() {

        testResultsSteps.postTestResults(vehicleCancelledDataOld.setVin(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledDataOld.build().getVrm()))
                .setOdometerReadingUnits("kilometres").build());

        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld();
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - odometerReadingUnits miles")
    @Test
    public void testResultsOdometerReadingUnitsValueTwo() {

        testResultsSteps.postTestResults(vehicleCancelledDataOld.setVin(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledDataOld.build().getVrm()))
                .setOdometerReadingUnits("miles").build());

        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld();
    }


    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - preparerId")
    @Test
    public void testResultsRandomStringPreparerId() {

        testResultsSteps.postTestResults(vehicleCancelledDataOld.setVin(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledDataOld.build().getVrm()))
                .setPreparerId(RandomStringUtils.randomAlphanumeric(14)).build());

        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld();

    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3486 - API Consumer creates a new test results for submitted/canceled with no min restriction - preparerId")
    @Test
    public void testResultsEmptyPreparerId() {

        testResultsSteps.postTestResults(vehicleCancelledDataOld.setVin(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledDataOld.build().getVrm()))
                .setPreparerId("").build());

        testResultsSteps.statusCodeShouldBe(201);
        vehicleCancelledDataOld.setPreparerId(null);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld();
    }

    @Title("CB2-4804 - API Consumer creates a new test results for submitted/canceled with null - preparerId")
    @Test
    public void testResultsNullPreparerId() {

        testResultsSteps.postTestResults(vehicleCancelledDataOld.setVin(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledDataOld.build().getVrm()))
                .setPreparerId(null).build());

        testResultsSteps.statusCodeShouldBe(201);
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - preparerName")
    @Test
    public void testResultsRandomStringPreparerName() {

        testResultsSteps.postTestResults(vehicleCancelledDataOld.setVin(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledDataOld.build().getVrm()))
                .setPreparerName(RandomStringUtils.randomAlphanumeric(14)).build());

        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld();
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3486 - API Consumer creates a new test results for submitted/canceled with no min restriction - preparerName")
    @Test
    public void testResultsEmptyPreparerName() {

        testResultsSteps.postTestResults(vehicleCancelledDataOld.setVin(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledDataOld.build().getVrm()))
                .setPreparerName("").build());

        testResultsSteps.statusCodeShouldBe(201);
        vehicleCancelledDataOld.setPreparerName(null);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld();
    }

    @Title("CB2-4804 - API Consumer creates a new test results for submitted/canceled with null - preparerName")
    @Test
    public void testResultsNullPreparerName() {

        testResultsSteps.postTestResults(vehicleCancelledDataOld.setVin(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledDataOld.build().getVrm()))
                .setPreparerName(null).build());

        testResultsSteps.statusCodeShouldBe(201);
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3504 - TCD - API Consumer creates a new test result for submitted/canceled that allows null values - euVehicleCategory")
    @Test
    public void testResultsValueEuVehicleCategoryNull() {

        testResultsSteps.postTestResults(vehicleCancelledDataOld.setVin(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledDataOld.build().getVrm()))
                .setEuVehicleCategory(null).build());

        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld();
    }


    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - euVehicleCategory m1")
    @Test
    public void testResultsValueEuVehicleCategoryValueOne() {

        testResultsSteps.postTestResults(vehicleCancelledDataOld.setVin(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledDataOld.build().getVrm()))
                .setEuVehicleCategory("m1").build());

        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld();
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - euVehicleCategory m2")
    @Test
    public void testResultsValueEuVehicleCategoryValueTwo() {

        testResultsSteps.postTestResults(vehicleCancelledDataOld.setVin(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledDataOld.build().getVrm()))
                .setEuVehicleCategory("m2").build());

        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld();
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - euVehicleCategory m3")
    @Test
    public void testResultsValueEuVehicleCategoryValueThree() {

        testResultsSteps.postTestResults(vehicleCancelledDataOld.setVin(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledDataOld.build().getVrm()))
                .setEuVehicleCategory("m3").build());

        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld();
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - euVehicleCategory n1")
    @Test
    public void testResultsValueEuVehicleCategoryValueFour() {

        testResultsSteps.postTestResults(vehicleCancelledDataOld.setVin(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledDataOld.build().getVrm()))
                .setEuVehicleCategory("n1").build());

        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld();
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - euVehicleCategory n2")
    @Test
    public void testResultsValueEuVehicleCategoryValueFive() {

        testResultsSteps.postTestResults(vehicleCancelledDataOld.setVin(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledDataOld.build().getVrm()))
                .setEuVehicleCategory("n2").build());

        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld();
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - euVehicleCategory n3")
    @Test
    public void testResultsValueEuVehicleCategoryValueSix() {

        testResultsSteps.postTestResults(vehicleCancelledDataOld.setVin(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledDataOld.build().getVrm()))
                .setEuVehicleCategory("n3").build());

        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld();
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - euVehicleCategory o1")
    @Test
    public void testResultsValueEuVehicleCategoryValueSeven() {

        testResultsSteps.postTestResults(vehicleCancelledDataOld.setVin(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledDataOld.build().getVrm()))
                .setEuVehicleCategory("o1").build());

        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld();
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - euVehicleCategory o2")
    @Test
    public void testResultsValueEuVehicleCategoryValueEight() {

        testResultsSteps.postTestResults(vehicleCancelledDataOld.setVin(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledDataOld.build().getVrm()))
                .setEuVehicleCategory("o2").build());

        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld();
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - euVehicleCategory o3")
    @Test
    public void testResultsValueEuVehicleCategoryValueNine() {

        testResultsSteps.postTestResults(vehicleCancelledDataOld.setVin(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledDataOld.build().getVrm()))
                .setEuVehicleCategory("o3").build());

        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld();
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - euVehicleCategory o4")
    @Test
    public void testResultsValueEuVehicleCategoryValueTen() {

        testResultsSteps.postTestResults(vehicleCancelledDataOld.setVin(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledDataOld.build().getVrm()))
                .setEuVehicleCategory("o4").build());

        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld();
    }


    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3504 - TCD - API Consumer creates a new test result for submitted/canceled that allows null values - countryOfRegistration")
    @Test
    public void testResultsNullValueCountryOfRegistration() {

        testResultsSteps.postTestResults(vehicleCancelledDataOld.setVin(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledDataOld.build().getVrm()))
                .setCountryOfRegistration(null).build());

        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld();
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - countryOfRegistration")
    @Test
    public void testResultsRandomValueCountryOfRegistration() {

        testResultsSteps.postTestResults(vehicleCancelledDataOld.setVin(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledDataOld.build().getVrm()))
                .setCountryOfRegistration(RandomStringUtils.randomAlphanumeric(14)).build());

        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld();
    }


    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3486 - API Consumer creates a new test results for submitted/canceled with no min restriction - countryOfRegistration")
    @Test
    public void testResultsEmptyValueCountryOfRegistration() {

        testResultsSteps.postTestResults(vehicleCancelledDataOld.setVin(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledDataOld.build().getVrm()))
                .setCountryOfRegistration("").build());

        testResultsSteps.statusCodeShouldBe(201);
        vehicleCancelledDataOld.setCountryOfRegistration(null);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld();
    }


    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - vehicleSize large")
    @Test
    public void testResultsValueEuVehicleSizeValueOne() {

        testResultsSteps.postTestResults(vehicleCancelledDataOld.setVin(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledDataOld.build().getVrm()))
                .setVehicleSize("large").build());

        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld();
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - vehicleSize small")
    @Test
    public void testResultsValueEuVehicleSizeValueTwo() {

        testResultsSteps.postTestResults(vehicleCancelledDataOld.setVin(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledDataOld.build().getVrm()))
                .setVehicleSize("small").build());

        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld();
    }

}
