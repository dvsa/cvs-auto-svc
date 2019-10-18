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

    private TestResults.Builder vehicleCancelledData = TestResultsData.buildTestResultsCancelledData();

    private void validateSavedData() {
        testResultsSteps.getTestResults(vehicleCancelledData.build().getVin(), TestResultsStatus.CANCELED);
        testResultsSteps.statusCodeShouldBe(200);
        testResultsSteps.validateData((TestResultsGet) vehicleCancelledData.build());

    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - testStationName")
    @Test
    public void testResultsTestStationNameRandString() {

        testResultsSteps.postTestResults(vehicleCancelledData.setVin(generateRandomExcludingValues(21, vehicleCancelledData.build().getVin()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledData.build().getVrm()))
                .setTestStationName(RandomStringUtils.randomAlphanumeric(1, 998)).build());

        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedData();
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3486 - API Consumer creates a new test results for submitted/canceled with no min restriction - testStationName")
    @Test
    public void testResultsTestStationNameEmpty() {

        testResultsSteps.postTestResults(vehicleCancelledData.setVin(generateRandomExcludingValues(21, vehicleCancelledData.build().getVin()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledData.build().getVrm()))
                .setTestStationName("").build());

        testResultsSteps.statusCodeShouldBe(201);
        vehicleCancelledData.setTestStationName(null);
        testResultsSteps.validateData("Test records created");
        validateSavedData();
    }


    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - testStationPNumber")
    @Test
    public void testResultsTestStationPNumberRandString() {

        testResultsSteps.postTestResults(vehicleCancelledData.setVin(generateRandomExcludingValues(21, vehicleCancelledData.build().getVin()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledData.build().getVrm()))
                .setTestStationPNumber(RandomStringUtils.randomAlphanumeric(1, 19)).build());

        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedData();
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3486 - API Consumer creates a new test results for submitted/canceled with no min restriction - testStationPNumber")
    @Test
    public void testResultsTestStationPNumberEmpty() {

        testResultsSteps.postTestResults(vehicleCancelledData.setVin(generateRandomExcludingValues(21, vehicleCancelledData.build().getVin()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledData.build().getVrm()))
                .setTestStationPNumber("").build());

        testResultsSteps.statusCodeShouldBe(201);
        vehicleCancelledData.setTestStationPNumber(null);
        testResultsSteps.validateData("Test records created");
        validateSavedData();
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - testStationType atf")
    @Test
    public void testResultsValueTestStationTypeAtf() {

        testResultsSteps.postTestResults(vehicleCancelledData.setVin(generateRandomExcludingValues(21, vehicleCancelledData.build().getVin()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledData.build().getVrm()))
                .setTestStationType("atf").build());

        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedData();
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - testStationType gvts")
    @Test
    public void testResultsValueTestStationTypeGvts() {

        testResultsSteps.postTestResults(vehicleCancelledData.setVin(generateRandomExcludingValues(21, vehicleCancelledData.build().getVin()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledData.build().getVrm()))
                .setTestStationType("gvts").build());

        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedData();
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - testStationType hq")
    @Test
    public void testResultsValueTestStationTypeHq() {

        testResultsSteps.postTestResults(vehicleCancelledData.setVin(generateRandomExcludingValues(21, vehicleCancelledData.build().getVin()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledData.build().getVrm()))
                .setTestStationType("hq").build());

        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedData();
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - testerName")
    @Test
    public void testResultsRandomTesterName() {

        testResultsSteps.postTestResults(vehicleCancelledData.setVin(generateRandomExcludingValues(21, vehicleCancelledData.build().getVin()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledData.build().getVrm()))
                .setTesterName(RandomStringUtils.randomAlphanumeric(60)).build());

        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedData();
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3486 - API Consumer creates a new test results for submitted/canceled with no min restriction - testerName")
    @Test
    public void testResultsLengthEmptyTesterName() {

        testResultsSteps.postTestResults(vehicleCancelledData.setVin(generateRandomExcludingValues(21, vehicleCancelledData.build().getVin()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledData.build().getVrm()))
                .setTesterName("").build());

        testResultsSteps.statusCodeShouldBe(201);
        vehicleCancelledData.setTesterName(null);
        testResultsSteps.validateData("Test records created");
        validateSavedData();
    }


    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - testerStaffId")
    @Test
    public void testResultsRandomTesterStaffId() {

        testResultsSteps.postTestResults(vehicleCancelledData.setVin(generateRandomExcludingValues(21, vehicleCancelledData.build().getVin()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledData.build().getVrm()))
                .setTesterStaffId(RandomStringUtils.randomAlphanumeric(9)).build());

        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedData();
    }

    //TODO - possible problem with gateway
    @Ignore ("empty testerStaffId should be acceptes but returned an error - instead returns bad Gateway")
    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3486 - API Consumer creates a new test results for submitted/canceled with no min restriction - testerStaffId")
    @Test
    public void testResultsLengthEmptyTesterStaffId() {

        testResultsSteps.postTestResults(vehicleCancelledData.setVin(generateRandomExcludingValues(21, vehicleCancelledData.build().getVin()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledData.build().getVrm()))
                .setTesterStaffId("").build());

        testResultsSteps.statusCodeShouldBe(201);
        vehicleCancelledData.setTesterStaffId(null);
        testResultsSteps.validateData("Test records created");
        validateSavedData();
    }


    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - testerEmailAddress")
    @Test
    public void testResultsRandomTesterEmailAddress() {

        testResultsSteps.postTestResults(vehicleCancelledData.setVin(generateRandomExcludingValues(21, vehicleCancelledData.build().getVin()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledData.build().getVrm()))
                .setTesterEmailAddress(RandomStringUtils.randomAlphanumeric(60)).build());

        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedData();
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3486 - API Consumer creates a new test results for submitted/canceled with no min restriction - testerEmailAddress")
    @Test
    public void testResultsLengthEmptyTesterEmailAddress() {

        testResultsSteps.postTestResults(vehicleCancelledData.setVin(generateRandomExcludingValues(21, vehicleCancelledData.build().getVin()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledData.build().getVrm()))
                .setTesterEmailAddress("").build());

        testResultsSteps.statusCodeShouldBe(201);
        vehicleCancelledData.setTesterEmailAddress(null);
        testResultsSteps.validateData("Test records created");
        validateSavedData();
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - testStartTimestamp")
    @Test
    public void testResultsTestStartTimestamp() {

        testResultsSteps.postTestResults(vehicleCancelledData.setVin(generateRandomExcludingValues(21, vehicleCancelledData.build().getVin()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledData.build().getVrm()))
                .setTestStartTimestamp(DataUtil.buildCurrentDateTime()).build());

        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedData();
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - testEndTimestamp")
    @Test
    public void testResultsTestEndTimestamp() {

        testResultsSteps.postTestResults(vehicleCancelledData.setVin(generateRandomExcludingValues(21, vehicleCancelledData.build().getVin()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledData.build().getVrm()))
                .setTestEndTimestamp(DataUtil.buildCurrentDateTime()).build());

        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedData();
    }


    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - reasonForCancellation")
    @Test
    public void testResultsLengthMaxReasonForCancellation() {

        testResultsSteps.postTestResults(vehicleCancelledData.setVin(generateRandomExcludingValues(21, vehicleCancelledData.build().getVin()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledData.build().getVrm()))
                .setReasonForCancellation(RandomStringUtils.randomAlphanumeric(500)).build());

        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedData();
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3486 - API Consumer creates a new test results for submitted/canceled with no min restriction - reasonForCancellation")
    @Test
    public void testResultsEmptyReasonForCancellation() {

        testResultsSteps.postTestResults(vehicleCancelledData.setVin(generateRandomExcludingValues(21, vehicleCancelledData.build().getVin()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledData.build().getVrm()))
                .setReasonForCancellation("").build());

        testResultsSteps.statusCodeShouldBe(201);
        vehicleCancelledData.setReasonForCancellation(null);
        testResultsSteps.validateData("Test records created");
        validateSavedData();
    }


    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - vehicleClass code 1")
    @Test
    public void testResultsValueVehicleClassCode1() {

        vehicleCancelledData.setVin(generateRandomExcludingValues(21, vehicleCancelledData.build().getVin()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledData.build().getVrm()))
                .getVehicleClass().setCode("1");
        testResultsSteps.postTestResults(vehicleCancelledData.build());

        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedData();
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - vehicleClass code 2")
    @Test
    public void testResultsValueVehicleClassCode2() {

        vehicleCancelledData.setVin(generateRandomExcludingValues(21, vehicleCancelledData.build().getVin()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledData.build().getVrm()))
                .getVehicleClass().setCode("2");
        testResultsSteps.postTestResults(vehicleCancelledData.build());

        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedData();
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - vehicleClass code 3")
    @Test
    public void testResultsValueVehicleClassCode3() {

        vehicleCancelledData.setVin(generateRandomExcludingValues(21, vehicleCancelledData.build().getVin()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledData.build().getVrm()))
                .getVehicleClass().setCode("3");
        testResultsSteps.postTestResults(vehicleCancelledData.build());

        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedData();
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - vehicleClass code n")
    @Test
    public void testResultsValueVehicleClassCoden() {

        vehicleCancelledData.setVin(generateRandomExcludingValues(21, vehicleCancelledData.build().getVin()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledData.build().getVrm()))
                .getVehicleClass().setCode("n");
        testResultsSteps.postTestResults(vehicleCancelledData.build());

        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedData();
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - vehicleClass code t")
    @Test
    public void testResultsValueVehicleClassCodet() {

        vehicleCancelledData.setVin(generateRandomExcludingValues(21, vehicleCancelledData.build().getVin()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledData.build().getVrm()))
                .getVehicleClass().setCode("t");
        testResultsSteps.postTestResults(vehicleCancelledData.build());

        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedData();
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - vehicleClass code l")
    @Test
    public void testResultsValueVehicleClassCodel() {

        vehicleCancelledData.setVin(generateRandomExcludingValues(21, vehicleCancelledData.build().getVin()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledData.build().getVrm()))
                .getVehicleClass().setCode("l");
        testResultsSteps.postTestResults(vehicleCancelledData.build());

        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedData();
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - vehicleClass code s")
    @Test
    public void testResultsValueVehicleClassCodes() {

        vehicleCancelledData.setVin(generateRandomExcludingValues(21, vehicleCancelledData.build().getVin()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledData.build().getVrm()))
                .getVehicleClass().setCode("s");
        testResultsSteps.postTestResults(vehicleCancelledData.build());

        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedData();
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - vehicleClass code v")
    @Test
    public void testResultsValueVehicleClassCodev() {

        vehicleCancelledData.setVin(generateRandomExcludingValues(21, vehicleCancelledData.build().getVin()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledData.build().getVrm()))
                .getVehicleClass().setCode("v");
        testResultsSteps.postTestResults(vehicleCancelledData.build());

        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedData();
    }


    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - vehicleClass description over 200cc or with a sidecar")
    @Test
    public void testResultsValueVehicleClassDescriptionValuesOne() {

        vehicleCancelledData.setVin(generateRandomExcludingValues(21, vehicleCancelledData.build().getVin()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledData.build().getVrm()))
                .getVehicleClass().setDescription("motorbikes over 200cc or with a sidecar");
        testResultsSteps.postTestResults(vehicleCancelledData.build());

        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedData();
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - vehicleClass description small psv (ie: less than or equal to 22 seats)")
    @Test
    public void testResultsValueVehicleClassDescriptionValuesTwo() {

        vehicleCancelledData.setVin(generateRandomExcludingValues(21, vehicleCancelledData.build().getVin()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledData.build().getVrm()))
                .getVehicleClass().setDescription("small psv (ie: less than or equal to 22 seats)");
        testResultsSteps.postTestResults(vehicleCancelledData.build());

        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedData();
    }


    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - vehicleClass description not applicable")
    @Test
    public void testResultsValueVehicleClassDescriptionValuesThree() {

        vehicleCancelledData.setVin(generateRandomExcludingValues(21, vehicleCancelledData.build().getVin()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledData.build().getVrm()))
                .getVehicleClass().setDescription("not applicable");
        testResultsSteps.postTestResults(vehicleCancelledData.build());

        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedData();
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - vehicleClass description motorbikes up to 200cc")
    @Test
    public void testResultsValueVehicleClassDescriptionValuesFour() {

        vehicleCancelledData.setVin(generateRandomExcludingValues(21, vehicleCancelledData.build().getVin()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledData.build().getVrm()))
                .getVehicleClass().setDescription("motorbikes up to 200cc");
        testResultsSteps.postTestResults(vehicleCancelledData.build());

        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedData();
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - vehicleClass description trailer")
    @Test
    public void testResultsValueVehicleClassDescriptionValuesFive() {

        vehicleCancelledData.setVin(generateRandomExcludingValues(21, vehicleCancelledData.build().getVin()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledData.build().getVrm()))
                .getVehicleClass().setDescription("trailer");
        testResultsSteps.postTestResults(vehicleCancelledData.build());

        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedData();
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - vehicleClass description arge psv(ie: greater than 23 seats)")
    @Test
    public void testResultsValueVehicleClassDescriptionValuesSix() {

        vehicleCancelledData.setVin(generateRandomExcludingValues(21, vehicleCancelledData.build().getVin()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledData.build().getVrm()))
                .getVehicleClass().setDescription("large psv(ie: greater than 23 seats)");
        testResultsSteps.postTestResults(vehicleCancelledData.build());

        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedData();
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - vehicleClass description 3 wheelers")
    @Test
    public void testResultsValueVehicleClassDescriptionValuesSeven() {

        vehicleCancelledData.setVin(generateRandomExcludingValues(21, vehicleCancelledData.build().getVin()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledData.build().getVrm()))
                .getVehicleClass().setDescription("3 wheelers");
        testResultsSteps.postTestResults(vehicleCancelledData.build());

        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedData();
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - vehicleClass description heavy goods vehicle")
    @Test
    public void testResultsValueVehicleClassDescriptionValuesEight() {

        vehicleCancelledData.setVin(generateRandomExcludingValues(21, vehicleCancelledData.build().getVin()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledData.build().getVrm()))
                .getVehicleClass().setDescription("heavy goods vehicle");
        testResultsSteps.postTestResults(vehicleCancelledData.build());

        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedData();
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - vehicleType psv")
    @Test
    public void testResultsValueVehicleTypeValueOne() {

        testResultsSteps.postTestResults(vehicleCancelledData.setVin(generateRandomExcludingValues(21, vehicleCancelledData.build().getVin()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledData.build().getVrm()))
                .setVehicleType("psv").build());

        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedData();
    }


    //TODO may not be needed
    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - vehicleType hgv")
    @Test
    public void testResultsValueVehicleTypeValueTwo() {

        testResultsSteps.postTestResults(vehicleCancelledData.setVin(generateRandomExcludingValues(21, vehicleCancelledData.build().getVin()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledData.build().getVrm()))
                .setVehicleType("hgv").build());

        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorDataContains("numberOfSeatbeltsFitted", "is not allowed");
        testResultsSteps.validatePostErrorDataContains("lastSeatbeltInstallationCheckDate", "is not allowed");
        testResultsSteps.validatePostErrorDataContains("seatbeltInstallationCheckDate", "is not allowed");

    }


    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - vehicleType trl")
    @Test
    public void testResultsValueVehicleTypeValueThree() {

        testResultsSteps.postTestResults(vehicleCancelledData.setVin(generateRandomExcludingValues(21, vehicleCancelledData.build().getVin()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledData.build().getVrm()))
                .setVehicleType("trl").build());
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorDataContains("trailerId", "is required");

    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - numberOfSeats")
    @Test
    public void testResultsRandomNumberOfSeats() {

        testResultsSteps.postTestResults(vehicleCancelledData.setVin(generateRandomExcludingValues(21, vehicleCancelledData.build().getVin()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledData.build().getVrm()))
                .setNumberOfSeats(Integer.valueOf(RandomStringUtils.randomNumeric(5))).build());

        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedData();
    }


    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - noOfAxles")
    @Test
    public void testResultsRandomNumberNoOfAxles2() {

        testResultsSteps.postTestResults(vehicleCancelledData.setVin(generateRandomExcludingValues(21, vehicleCancelledData.build().getVin()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledData.build().getVrm()))
                .setNoOfAxles(2).build());

        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedData();
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - noOfAxles")
    @Test
    public void testResultsRandomNumberNoOfAxles3() {

        testResultsSteps.postTestResults(vehicleCancelledData.setVin(generateRandomExcludingValues(21, vehicleCancelledData.build().getVin()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledData.build().getVrm()))
                .setNoOfAxles(3).build());

        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedData();
    }


    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - vehicleConfiguration rigid")
    @Test
    public void testResultsVehicleConfigurationValueOne() {

        testResultsSteps.postTestResults(vehicleCancelledData.setVin(generateRandomExcludingValues(21, vehicleCancelledData.build().getVin()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledData.build().getVrm()))
                .setVehicleConfiguration("rigid").build());

        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedData();
    }

    //TODO - possible problem with gateway
    @Ignore ("integer odometerReading should have returned an error - instead returns bad Gateway")
    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - vehicleConfiguration articulated")
    @Test
    public void testResultsVehicleConfigurationValueTwo() {

        testResultsSteps.postTestResults(vehicleCancelledData.setVin(generateRandomExcludingValues(21, vehicleCancelledData.build().getVin()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledData.build().getVrm()))
                .setVehicleConfiguration("articulated").build());

        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedData();
    }


    //TODO - possible problem with gateway
    @Ignore ("integer odometerReading should have returned an error - instead returns bad Gateway")
    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - odometerReading")
    @Test
    public void testResultsRandomOdometerReading() {

        testResultsSteps.postTestResults(vehicleCancelledData.setVin(generateRandomExcludingValues(21, vehicleCancelledData.build().getVin()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledData.build().getVrm()))
                .setOdometerReading(Integer.valueOf(RandomStringUtils.randomNumeric(5))).build());

        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedData();
    }


    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3504 - TCD - API Consumer creates a new test result for submitted/canceled that allows null values - odometerReading")
    @Test
    public void testResultsNullOdometerReading() {

        testResultsSteps.postTestResults(vehicleCancelledData.setVin(generateRandomExcludingValues(21, vehicleCancelledData.build().getVin()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledData.build().getVrm()))
                .setOdometerReading(null).build());

        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedData();
    }


    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3504 - TCD - API Consumer creates a new test result for submitted/canceled that allows null values - odometerReadingUnits")
    @Test
    public void testResultsNullOdometerReadingUnits() {

        testResultsSteps.postTestResults(vehicleCancelledData.setVin(generateRandomExcludingValues(21, vehicleCancelledData.build().getVin()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledData.build().getVrm()))
                .setOdometerReadingUnits(null).build());

        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedData();
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - odometerReadingUnits kilometres")
    @Test
    public void testResultsOdometerReadingUnitsValueOne() {

        testResultsSteps.postTestResults(vehicleCancelledData.setVin(generateRandomExcludingValues(21, vehicleCancelledData.build().getVin()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledData.build().getVrm()))
                .setOdometerReadingUnits("kilometres").build());

        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedData();
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - odometerReadingUnits miles")
    @Test
    public void testResultsOdometerReadingUnitsValueTwo() {

        testResultsSteps.postTestResults(vehicleCancelledData.setVin(generateRandomExcludingValues(21, vehicleCancelledData.build().getVin()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledData.build().getVrm()))
                .setOdometerReadingUnits("miles").build());

        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedData();
    }


    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - preparerId")
    @Test
    public void testResultsRandomStringPreparerId() {

        testResultsSteps.postTestResults(vehicleCancelledData.setVin(generateRandomExcludingValues(21, vehicleCancelledData.build().getVin()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledData.build().getVrm()))
                .setPreparerId(RandomStringUtils.randomAlphanumeric(14)).build());

        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedData();
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3486 - API Consumer creates a new test results for submitted/canceled with no min restriction - preparerId")
    @Test
    public void testResultsEmptyPreparerId() {

        testResultsSteps.postTestResults(vehicleCancelledData.setVin(generateRandomExcludingValues(21, vehicleCancelledData.build().getVin()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledData.build().getVrm()))
                .setPreparerId("").build());

        testResultsSteps.statusCodeShouldBe(201);
        vehicleCancelledData.setPreparerId(null);
        testResultsSteps.validateData("Test records created");
        validateSavedData();
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - preparerName")
    @Test
    public void testResultsRandomStringPreparerName() {

        testResultsSteps.postTestResults(vehicleCancelledData.setVin(generateRandomExcludingValues(21, vehicleCancelledData.build().getVin()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledData.build().getVrm()))
                .setPreparerName(RandomStringUtils.randomAlphanumeric(14)).build());

        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedData();
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3486 - API Consumer creates a new test results for submitted/canceled with no min restriction - preparerName")
    @Test
    public void testResultsEmptyPreparerName() {

        testResultsSteps.postTestResults(vehicleCancelledData.setVin(generateRandomExcludingValues(21, vehicleCancelledData.build().getVin()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledData.build().getVrm()))
                .setPreparerName("").build());

        testResultsSteps.statusCodeShouldBe(201);
        vehicleCancelledData.setPreparerName(null);
        testResultsSteps.validateData("Test records created");
        validateSavedData();
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3504 - TCD - API Consumer creates a new test result for submitted/canceled that allows null values - euVehicleCategory")
    @Test
    public void testResultsValueEuVehicleCategoryNull() {

        testResultsSteps.postTestResults(vehicleCancelledData.setVin(generateRandomExcludingValues(21, vehicleCancelledData.build().getVin()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledData.build().getVrm()))
                .setEuVehicleCategory(null).build());

        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedData();
    }


    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - euVehicleCategory m1")
    @Test
    public void testResultsValueEuVehicleCategoryValueOne() {

        testResultsSteps.postTestResults(vehicleCancelledData.setVin(generateRandomExcludingValues(21, vehicleCancelledData.build().getVin()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledData.build().getVrm()))
                .setEuVehicleCategory("m1").build());

        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedData();
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - euVehicleCategory m2")
    @Test
    public void testResultsValueEuVehicleCategoryValueTwo() {

        testResultsSteps.postTestResults(vehicleCancelledData.setVin(generateRandomExcludingValues(21, vehicleCancelledData.build().getVin()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledData.build().getVrm()))
                .setEuVehicleCategory("m2").build());

        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedData();
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - euVehicleCategory m3")
    @Test
    public void testResultsValueEuVehicleCategoryValueThree() {

        testResultsSteps.postTestResults(vehicleCancelledData.setVin(generateRandomExcludingValues(21, vehicleCancelledData.build().getVin()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledData.build().getVrm()))
                .setEuVehicleCategory("m3").build());

        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedData();
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - euVehicleCategory n1")
    @Test
    public void testResultsValueEuVehicleCategoryValueFour() {

        testResultsSteps.postTestResults(vehicleCancelledData.setVin(generateRandomExcludingValues(21, vehicleCancelledData.build().getVin()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledData.build().getVrm()))
                .setEuVehicleCategory("n1").build());

        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedData();
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - euVehicleCategory n2")
    @Test
    public void testResultsValueEuVehicleCategoryValueFive() {

        testResultsSteps.postTestResults(vehicleCancelledData.setVin(generateRandomExcludingValues(21, vehicleCancelledData.build().getVin()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledData.build().getVrm()))
                .setEuVehicleCategory("n2").build());

        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedData();
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - euVehicleCategory n3")
    @Test
    public void testResultsValueEuVehicleCategoryValueSix() {

        testResultsSteps.postTestResults(vehicleCancelledData.setVin(generateRandomExcludingValues(21, vehicleCancelledData.build().getVin()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledData.build().getVrm()))
                .setEuVehicleCategory("n3").build());

        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedData();
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - euVehicleCategory o1")
    @Test
    public void testResultsValueEuVehicleCategoryValueSeven() {

        testResultsSteps.postTestResults(vehicleCancelledData.setVin(generateRandomExcludingValues(21, vehicleCancelledData.build().getVin()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledData.build().getVrm()))
                .setEuVehicleCategory("o1").build());

        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedData();
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - euVehicleCategory o2")
    @Test
    public void testResultsValueEuVehicleCategoryValueEight() {

        testResultsSteps.postTestResults(vehicleCancelledData.setVin(generateRandomExcludingValues(21, vehicleCancelledData.build().getVin()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledData.build().getVrm()))
                .setEuVehicleCategory("o2").build());

        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedData();
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - euVehicleCategory o3")
    @Test
    public void testResultsValueEuVehicleCategoryValueNine() {

        testResultsSteps.postTestResults(vehicleCancelledData.setVin(generateRandomExcludingValues(21, vehicleCancelledData.build().getVin()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledData.build().getVrm()))
                .setEuVehicleCategory("o3").build());

        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedData();
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - euVehicleCategory o4")
    @Test
    public void testResultsValueEuVehicleCategoryValueTen() {

        testResultsSteps.postTestResults(vehicleCancelledData.setVin(generateRandomExcludingValues(21, vehicleCancelledData.build().getVin()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledData.build().getVrm()))
                .setEuVehicleCategory("o4").build());

        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedData();
    }


    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3504 - TCD - API Consumer creates a new test result for submitted/canceled that allows null values - countryOfRegistration")
    @Test
    public void testResultsNullValueCountryOfRegistration() {

        testResultsSteps.postTestResults(vehicleCancelledData.setVin(generateRandomExcludingValues(21, vehicleCancelledData.build().getVin()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledData.build().getVrm()))
                .setCountryOfRegistration(null).build());

        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedData();
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - countryOfRegistration")
    @Test
    public void testResultsRandomValueCountryOfRegistration() {

        testResultsSteps.postTestResults(vehicleCancelledData.setVin(generateRandomExcludingValues(21, vehicleCancelledData.build().getVin()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledData.build().getVrm()))
                .setCountryOfRegistration(RandomStringUtils.randomAlphanumeric(14)).build());

        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedData();
    }


    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3486 - API Consumer creates a new test results for submitted/canceled with no min restriction - countryOfRegistration")
    @Test
    public void testResultsEmptyValueCountryOfRegistration() {

        testResultsSteps.postTestResults(vehicleCancelledData.setVin(generateRandomExcludingValues(21, vehicleCancelledData.build().getVin()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledData.build().getVrm()))
                .setCountryOfRegistration("").build());

        testResultsSteps.statusCodeShouldBe(201);
        vehicleCancelledData.setCountryOfRegistration(null);
        testResultsSteps.validateData("Test records created");
        validateSavedData();
    }


    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - vehicleSize large")
    @Test
    public void testResultsValueEuVehicleSizeValueOne() {

        testResultsSteps.postTestResults(vehicleCancelledData.setVin(generateRandomExcludingValues(21, vehicleCancelledData.build().getVin()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledData.build().getVrm()))
                .setVehicleSize("large").build());

        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedData();
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - vehicleSize small")
    @Test
    public void testResultsValueEuVehicleSizeValueTwo() {

        testResultsSteps.postTestResults(vehicleCancelledData.setVin(generateRandomExcludingValues(21, vehicleCancelledData.build().getVin()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledData.build().getVrm()))
                .setVehicleSize("small").build());

        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedData();
    }

}
