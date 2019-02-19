package testresults;

import clients.util.ToTypeConvertor;
import clients.util.testresult.TestResultsLevel;
import data.TestResultsData;
import model.testresults.TestResults;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.core.annotations.WithTags;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import steps.TestResultsSteps;

import java.util.Arrays;

@WithTags(
        {
                @WithTag(type = "TestResults", name = "All"),
                @WithTag(type = "TestResults", name = "Positive"),
                @WithTag(type = "Service", name = "One"),

        }
)

@Ignore("Work in progress - new development changes require big refactoring")
@RunWith(SerenityRunner.class)
public class PostTestResultsNegMainLevel {

    @Steps
    TestResultsSteps testResultsSteps;

    private TestResults vehicleData = TestResultsData.buildTestResultsSubmittedData();
    private static final String VRM = "SL72XD";

    @Title("")
    @Test
    public void testResultsEmptyBody() {

        testResultsSteps.postTestResults(null);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("vrm", "is required");

    }

    @Title("")
    @Test
    public void testResultsMissingVrm() {

        testResultsSteps.postTestResultsFieldChange(vehicleData.setVrm(VRM), "vrm", ToTypeConvertor.MISSING, TestResultsLevel.MAIN_LEVEL);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("vrm", "is required");
    }

    @Title("")
    @Test
    public void testResultsNullVrm() {

        testResultsSteps.postTestResultsFieldChange(vehicleData.setVrm(VRM), "vrm", ToTypeConvertor.NULL, TestResultsLevel.MAIN_LEVEL);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("vrm", "must be a string");
    }



    @Title("")
    @Test
    public void testResultsIntegerVrm() {

        testResultsSteps.postTestResultsFieldChange(vehicleData.setVrm(VRM), "vrm", RandomStringUtils.randomNumeric(8), ToTypeConvertor.INTEGER, TestResultsLevel.MAIN_LEVEL);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("vrm", "must be a string");
    }

    @Title("")
    @Test
    public void testResultsLengthMaxVrm() {

        testResultsSteps.postTestResults(vehicleData.setVrm(RandomStringUtils.randomAlphanumeric(9)));
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("vrm", "length must be less than or equal to 8 characters long");
    }

    @Title("")
    @Test
    public void testResultsLengthMinVrm() {

        testResultsSteps.postTestResults(vehicleData.setVrm(RandomStringUtils.randomAlphanumeric(0)));
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("vrm", "is not allowed to be empty");
    }

    //vin

    @Title("")
    @Test
    public void testResultsMissingVin() {

        testResultsSteps.postTestResultsFieldChange(vehicleData.setVrm(VRM), "vin", ToTypeConvertor.MISSING, TestResultsLevel.MAIN_LEVEL);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("vin", "is required");
    }

    @Title("")
    @Test
    public void testResultsNullVin() {

        testResultsSteps.postTestResultsFieldChange(vehicleData.setVrm(VRM), "vin", ToTypeConvertor.NULL, TestResultsLevel.MAIN_LEVEL);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("vin", "must be a string");
    }



    @Title("")
    @Test
    public void testResultsIntegerVin() {

        testResultsSteps.postTestResultsFieldChange(vehicleData.setVrm(VRM), "vin", RandomStringUtils.randomNumeric(9), ToTypeConvertor.INTEGER, TestResultsLevel.MAIN_LEVEL);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("vin", "must be a string");
    }

    @Title("")
    @Test
    public void testResultsLengthMaxVin() {

        testResultsSteps.postTestResults(vehicleData.setVrm(VRM).setVin(RandomStringUtils.randomAlphanumeric(22)));
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("vin", "length must be less than or equal to 21 characters long");
    }

    @Title("")
    @Test
    public void testResultsLengthMinVin() {

        testResultsSteps.postTestResults(vehicleData.setVrm(VRM).setVin(RandomStringUtils.randomAlphanumeric(0)));
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("vin", "is not allowed to be empty");
    }


    //testStationName

    @Title("")
    @Test
    public void testResultsMissingTestStationName() {

        testResultsSteps.postTestResultsFieldChange(vehicleData.setVrm(VRM), "testStationName", ToTypeConvertor.MISSING, TestResultsLevel.MAIN_LEVEL);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("testStationName", "is required");
    }

    @Title("")
    @Test
    public void testResultsNullTestStationName() {

        testResultsSteps.postTestResultsFieldChange(vehicleData.setVrm(VRM), "testStationName", ToTypeConvertor.NULL, TestResultsLevel.MAIN_LEVEL);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("testStationName", "must be a string");
    }



    @Title("")
    @Test
    public void testResultsIntegerTestStationName() {

        testResultsSteps.postTestResultsFieldChange(vehicleData.setVrm(VRM), "testStationName", RandomStringUtils.randomNumeric(9), ToTypeConvertor.INTEGER, TestResultsLevel.MAIN_LEVEL);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("testStationName", "must be a string");
    }

    @Title("")
    @Test
    public void testResultsLengthMaxTestStationName() {

        testResultsSteps.postTestResults(vehicleData.setVrm(VRM).setTestStationName(RandomStringUtils.randomAlphanumeric(1000)));
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("testStationName", "length must be less than or equal to 999 characters long");
    }

    @Title("")
    @Test
    public void testResultsLengthMinTestStationName() {

        testResultsSteps.postTestResults(vehicleData.setVrm(VRM).setTestStationName(RandomStringUtils.randomAlphanumeric(0)));
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("testStationName", "is not allowed to be empty");
    }

    // testStationPNumber

    @Title("")
    @Test
    public void testResultsMissingTestStationPNumber() {

        testResultsSteps.postTestResultsFieldChange(vehicleData.setVrm(VRM), "testStationPNumber", ToTypeConvertor.MISSING, TestResultsLevel.MAIN_LEVEL);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("testStationPNumber", "is required");
    }

    @Title("")
    @Test
    public void testResultsNullTestStationPNumber() {

        testResultsSteps.postTestResultsFieldChange(vehicleData.setVrm(VRM), "testStationPNumber", ToTypeConvertor.NULL, TestResultsLevel.MAIN_LEVEL);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("testStationPNumber", "must be a string");
    }



    @Title("")
    @Test
    public void testResultsIntegerTestStationPNumber() {

        testResultsSteps.postTestResultsFieldChange(vehicleData.setVrm(VRM), "testStationPNumber", RandomStringUtils.randomNumeric(9), ToTypeConvertor.INTEGER, TestResultsLevel.MAIN_LEVEL);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("testStationPNumber", "must be a string");
    }

    @Title("")
    @Test
    public void testResultsLengthMaxTestStationPNumber() {

        testResultsSteps.postTestResults(vehicleData.setVrm(VRM).setTestStationPNumber(RandomStringUtils.randomAlphanumeric(21)));
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("testStationPNumber", "length must be less than or equal to 20 characters long");
    }


    @Title("")
    @Test
    public void testResultsLengthMinTestStationPNumber() {

        testResultsSteps.postTestResults(vehicleData.setVrm(VRM).setTestStationPNumber(RandomStringUtils.randomAlphanumeric(0)));
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("testStationPNumber", "is not allowed to be empty");
    }

    //locationType

    @Title("")
    @Test
    public void testResultsMissingLocationType() {

        testResultsSteps.postTestResultsFieldChange(vehicleData.setVrm(VRM), "locationType", ToTypeConvertor.MISSING, TestResultsLevel.MAIN_LEVEL);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("locationType", "is required");
    }

    @Title("")
    @Test
    public void testResultsNullLocationType() {

        testResultsSteps.postTestResultsFieldChange(vehicleData.setVrm(VRM), "locationType", ToTypeConvertor.NULL, TestResultsLevel.MAIN_LEVEL);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("locationType", "must be one of [atf, gvts, tass, potf, other]");
    }



    @Title("")
    @Test
    public void testResultsIntegerLocationType() {

        testResultsSteps.postTestResultsFieldChange(vehicleData.setVrm(VRM), "locationType", RandomStringUtils.randomNumeric(1,9), ToTypeConvertor.INTEGER, TestResultsLevel.MAIN_LEVEL);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("locationType", "must be one of [atf, gvts, tass, potf, other]");
    }


    @Title("")
    @Test
    public void testResultsValueLocationType() {

        testResultsSteps.postTestResults(vehicleData.setVrm(VRM).setLocationType(RandomStringUtils.randomAlphanumeric(10)));
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("locationType", "must be one of [atf, gvts, tass, potf, other]");
    }

    @Title("")
    @Test
    public void testResultsValueLocationTypeEmpty() {

        testResultsSteps.postTestResults(vehicleData.setVrm(VRM).setLocationType(RandomStringUtils.randomAlphanumeric(0)));
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("locationType", "must be one of [atf, gvts, tass, potf, other]");
    }


    //testerName

    @Title("")
    @Test
    public void testResultsMissingTesterName() {

        testResultsSteps.postTestResultsFieldChange(vehicleData.setVrm(VRM), "testerName", ToTypeConvertor.MISSING, TestResultsLevel.MAIN_LEVEL);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("testerName", "is required");
    }

    @Title("")
    @Test
    public void testResultsNullTesterName() {

        testResultsSteps.postTestResultsFieldChange(vehicleData.setVrm(VRM), "testerName", ToTypeConvertor.NULL, TestResultsLevel.MAIN_LEVEL);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("testerName", "must be a string");
    }



    @Title("")
    @Test
    public void testResultsIntegerTesterName() {

        testResultsSteps.postTestResultsFieldChange(vehicleData.setVrm(VRM), "testerName", RandomStringUtils.randomNumeric(9), ToTypeConvertor.INTEGER, TestResultsLevel.MAIN_LEVEL);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("testerName", "must be a string");
    }

    @Title("")
    @Test
    public void testResultsLengthMaxTesterName() {

        testResultsSteps.postTestResults(vehicleData.setVrm(VRM).setTesterName(RandomStringUtils.randomAlphanumeric(61)));
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("testerName", "length must be less than or equal to 60 characters long");
    }

    @Title("")
    @Test
    public void testResultsLengthMinTesterName() {

        testResultsSteps.postTestResults(vehicleData.setVrm(VRM).setTesterName(RandomStringUtils.randomAlphanumeric(0)));
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("testerName", "is not allowed to be empty");
    }

    //testerStaffId

    @Title("")
    @Test
    public void testResultsMissingTesterStaffId() {

        testResultsSteps.postTestResultsFieldChange(vehicleData.setVrm(VRM), "testerStaffId", ToTypeConvertor.MISSING, TestResultsLevel.MAIN_LEVEL);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("testerStaffId", "is required");
    }

    @Title("")
    @Test
    public void testResultsNullTesterStaffId() {

        testResultsSteps.postTestResultsFieldChange(vehicleData.setVrm(VRM), "testerStaffId", ToTypeConvertor.NULL, TestResultsLevel.MAIN_LEVEL);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("testerStaffId", "must be a string");
    }



    @Title("")
    @Test
    public void testResultsIntegerTesterStaffId() {

        testResultsSteps.postTestResultsFieldChange(vehicleData.setVrm(VRM), "testerStaffId", RandomStringUtils.randomNumeric(9), ToTypeConvertor.INTEGER, TestResultsLevel.MAIN_LEVEL);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("testerStaffId", "must be a string");
    }

    @Title("")
    @Test
    public void testResultsLengthMaxTesterStaffId() {

        testResultsSteps.postTestResults(vehicleData.setVrm(VRM).setTesterStaffId(RandomStringUtils.randomAlphanumeric(10)));
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("testerStaffId", "length must be less than or equal to 9 characters long");
    }

    @Title("")
    @Test
    public void testResultsLengthMinTesterStaffId() {

        testResultsSteps.postTestResults(vehicleData.setVrm(VRM).setTesterStaffId(RandomStringUtils.randomAlphanumeric(0)));
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("testerStaffId", "is not allowed to be empty");
    }

    //testStartTimestamp

    @Title("")
    @Test
    public void testResultsMissingTestStartTimestamp() {

        testResultsSteps.postTestResultsFieldChange(vehicleData.setVrm(VRM), "testStartTimestamp", ToTypeConvertor.MISSING, TestResultsLevel.MAIN_LEVEL);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("testStartTimestamp", "is required");
    }

    @Title("")
    @Test
    public void testResultsNullTestStartTimestamp() {

        testResultsSteps.postTestResultsFieldChange(vehicleData.setVrm(VRM), "testStartTimestamp", ToTypeConvertor.NULL, TestResultsLevel.MAIN_LEVEL);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("testStartTimestamp", "must be a valid ISO 8601 date");
    }



    @Title("")
    @Test
    public void testResultsIntegerTestStartTimestamp() {

        testResultsSteps.postTestResultsFieldChange(vehicleData.setVrm(VRM), "testStartTimestamp", RandomStringUtils.randomNumeric(9), ToTypeConvertor.INTEGER, TestResultsLevel.MAIN_LEVEL);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("testStartTimestamp", "must be a valid ISO 8601 date");
    }

    @Title("")
    @Test
    public void testResultsRandomStringTestStartTimestamp() {

        testResultsSteps.postTestResults(vehicleData.setVrm(VRM).setTestStartTimestamp(RandomStringUtils.randomAlphanumeric(10)));
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("testStartTimestamp", "must be a valid ISO 8601 date");
    }


    @Title("")
    @Test
    public void testResultsEmptyTestStartTimestamp() {

        testResultsSteps.postTestResults(vehicleData.setVrm(VRM).setTestStartTimestamp(RandomStringUtils.randomAlphanumeric(0)));
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("testStartTimestamp", "must be a valid ISO 8601 date");
    }


    //testEndTimestamp

    @Title("")
    @Test
    public void testResultsMissingTestEndTimestamp() {

        testResultsSteps.postTestResultsFieldChange(vehicleData.setVrm(VRM), "testEndTimestamp", ToTypeConvertor.MISSING, TestResultsLevel.MAIN_LEVEL);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("testEndTimestamp", "is required");
    }

    @Title("")
    @Test
    public void testResultsNullTestEndTimestamp() {

        testResultsSteps.postTestResultsFieldChange(vehicleData.setVrm(VRM), "testEndTimestamp", ToTypeConvertor.NULL, TestResultsLevel.MAIN_LEVEL);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("testEndTimestamp", "must be a valid ISO 8601 date");
    }



    @Title("")
    @Test
    public void testResultsIntegerTestEndTimestamp() {

        testResultsSteps.postTestResultsFieldChange(vehicleData.setVrm(VRM), "testEndTimestamp", RandomStringUtils.randomNumeric(9), ToTypeConvertor.INTEGER, TestResultsLevel.MAIN_LEVEL);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("testEndTimestamp", "must be a valid ISO 8601 date");
    }

    @Title("")
    @Test
    public void testResultsRandomStringTestEndTimestamp() {

        testResultsSteps.postTestResults(vehicleData.setVrm(VRM).setTestEndTimestamp(RandomStringUtils.randomAlphanumeric(5)));
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("testEndTimestamp", "must be a valid ISO 8601 date");
    }


    @Title("")
    @Test
    public void testResultsEmptyTestEndTimestamp() {

        testResultsSteps.postTestResults(vehicleData.setVrm(VRM).setTestEndTimestamp(RandomStringUtils.randomAlphanumeric(0)));
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("testEndTimestamp", "must be a valid ISO 8601 date");
    }


    //testStatus

    @Title("")
    @Test
    public void testResultsMissingTestStatus() {

        testResultsSteps.postTestResultsFieldChange(vehicleData.setVrm(VRM), "testStatus", ToTypeConvertor.MISSING, TestResultsLevel.MAIN_LEVEL);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("testStatus", "is required");
    }

    @Title("")
    @Test
    public void testResultsNullTestStatus() {

        testResultsSteps.postTestResultsFieldChange(vehicleData.setVrm(VRM), "testStatus", ToTypeConvertor.NULL, TestResultsLevel.MAIN_LEVEL);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("testStatus", "must be one of [Submitted, Canceled]");
    }



    @Title("")
    @Test
    public void testResultsIntegerTestStatus() {

        testResultsSteps.postTestResultsFieldChange(vehicleData.setVrm(VRM), "testStatus", RandomStringUtils.randomNumeric(1,9), ToTypeConvertor.INTEGER, TestResultsLevel.MAIN_LEVEL);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("testStatus", "must be one of [Submitted, Canceled]");
    }


    @Title("")
    @Test
    public void testResultsValueTestStatus() {

        testResultsSteps.postTestResults(vehicleData.setVrm(VRM).setTestStatus(RandomStringUtils.randomAlphanumeric(10)));
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("testStatus", "must be one of [Submitted, Canceled]");
    }

    @Title("")
    @Test
    public void testResultsValueTestStatusEmpty() {

        testResultsSteps.postTestResults(vehicleData.setVrm(VRM).setTestStatus(RandomStringUtils.randomAlphanumeric(0)));
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("testStatus", "must be one of [Submitted, Canceled]");
    }


    //reasonForCancellation

    @Title("")
    @Test
    public void testResultsMissingReasonForCancellation() {

        testResultsSteps.postTestResultsFieldChange(vehicleData.setVrm(VRM), "reasonForCancellation", ToTypeConvertor.MISSING, TestResultsLevel.MAIN_LEVEL);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("reasonForCancellation", "is required");
    }

    @Title("")
    @Test
    public void testResultsNullReasonForCancellation() {

        testResultsSteps.postTestResultsFieldChange(vehicleData.setVrm(VRM), "reasonForCancellation", ToTypeConvertor.NULL, TestResultsLevel.MAIN_LEVEL);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("reasonForCancellation", "must be a string");
    }



    @Title("")
    @Test
    public void testResultsIntegerReasonForCancellation() {

        testResultsSteps.postTestResultsFieldChange(vehicleData.setVrm(VRM), "reasonForCancellation", RandomStringUtils.randomNumeric(9), ToTypeConvertor.INTEGER, TestResultsLevel.MAIN_LEVEL);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("reasonForCancellation", "must be a string");
    }

    @Title("")
    @Test
    public void testResultsLengthMaxReasonForCancellation() {

        testResultsSteps.postTestResults(vehicleData.setVrm(VRM).setReasonForCancellation(RandomStringUtils.randomAlphanumeric(501)));
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("reasonForCancellation", "length must be less than or equal to 500 characters long");
    }

    @Ignore
    @Title("")
    @Test
    public void testResultsLengthMinReasonForCancellation() {

        testResultsSteps.postTestResults(vehicleData.setVrm(VRM).setReasonForCancellation(RandomStringUtils.randomAlphanumeric(0)));
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("reasonForCancellation", "is not allowed to be empty TO be decided if expected to fail or pass");
    }


    //vehicleClass

    @Title("")
    @Test
    public void testResultsMissingVehicleClass() {

        testResultsSteps.postTestResultsFieldChange(vehicleData.setVrm(VRM), "vehicleClass", ToTypeConvertor.MISSING, TestResultsLevel.MAIN_LEVEL);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("vehicleClass", "is required");
    }

    @Title("")
    @Test
    public void testResultsNullVehicleClass() {

        testResultsSteps.postTestResultsFieldChange(vehicleData.setVrm(VRM), "vehicleClass", ToTypeConvertor.NULL, TestResultsLevel.MAIN_LEVEL);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("vehicleClass", "must be one of [2, S, 1, T, L, 3, V]");
    }



    @Title("")
    @Test
    public void testResultsIntegerVehicleClass() {

        testResultsSteps.postTestResultsFieldChange(vehicleData.setVrm(VRM), "vehicleClass", RandomStringUtils.randomNumeric(1,9), ToTypeConvertor.INTEGER, TestResultsLevel.MAIN_LEVEL);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("vehicleClass", "must be one of [2, S, 1, T, L, 3, V]");
    }


    @Title("")
    @Test
    public void testResultsValueVehicleClass() {

        testResultsSteps.postTestResults(vehicleData.setVrm(VRM).setVehicleClass(RandomStringUtils.randomAlphanumeric(10)));
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("vehicleClass", "must be one of [2, S, 1, T, L, 3, V]");
    }

    @Title("")
    @Test
    public void testResultsValueVehicleClassEmpty() {

        testResultsSteps.postTestResults(vehicleData.setVrm(VRM).setVehicleClass(RandomStringUtils.randomAlphanumeric(0)));
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("vehicleClass", "must be one of [2, S, 1, T, L, 3, V]");
    }


    @Ignore
    @Test
    public void testToFigureOutVehicleClass() {

        testResultsSteps.postTestResults(vehicleData.setVrm(VRM).setVehicleClass("N"));
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("vehicleClass", "this should be accepted");
    }

    //vehicleType

    @Title("")
    @Test
    public void testResultsMissingVehicleType() {

        testResultsSteps.postTestResultsFieldChange(vehicleData.setVrm(VRM), "vehicleType", ToTypeConvertor.MISSING, TestResultsLevel.MAIN_LEVEL);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("vehicleType", "is required");
    }

    @Title("")
    @Test
    public void testResultsNullVehicleType() {

        testResultsSteps.postTestResultsFieldChange(vehicleData.setVrm(VRM), "vehicleType", ToTypeConvertor.NULL, TestResultsLevel.MAIN_LEVEL);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("vehicleType", "must be one of [psv, hgv, trl, others]");
    }


    @Title("")
    @Test
    public void testResultsIntegerVehicleType() {

        testResultsSteps.postTestResultsFieldChange(vehicleData.setVrm(VRM), "vehicleType", RandomStringUtils.randomNumeric(1,9), ToTypeConvertor.INTEGER, TestResultsLevel.MAIN_LEVEL);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("vehicleType", "must be one of [psv, hgv, trl, others]");
    }


    @Title("")
    @Test
    public void testResultsValueVehicleType() {

        testResultsSteps.postTestResults(vehicleData.setVrm(VRM).setVehicleType(RandomStringUtils.randomAlphanumeric(10)));
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("vehicleType", "must be one of [psv, hgv, trl, others]");
    }

    @Title("")
    @Test
    public void testResultsValueVehicleTypeEmpty() {

        testResultsSteps.postTestResults(vehicleData.setVrm(VRM).setVehicleType(""));
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("vehicleType", "must be one of [psv, hgv, trl, others]");
    }

    //numberOfSeats

    @Title("")
    @Test
    public void testResultsMissingNumberOFSeats() {

        testResultsSteps.postTestResultsFieldChange(vehicleData.setVrm(VRM), "numberOfSeats", ToTypeConvertor.MISSING, TestResultsLevel.MAIN_LEVEL);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("numberOfSeats", "is required");
    }

    @Title("")
    @Test
    public void testResultsNullNumberOfSeats() {

        testResultsSteps.postTestResultsFieldChange(vehicleData.setVrm(VRM), "numberOfSeats", ToTypeConvertor.NULL, TestResultsLevel.MAIN_LEVEL);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("numberOfSeats", "must be a number");
    }



    @Title("")
    @Test
    public void testResultsStringNumberOfSeats() {

        testResultsSteps.postTestResultsFieldChange(vehicleData.setVrm(VRM), "numberOfSeats", RandomStringUtils.randomAlphanumeric(4), ToTypeConvertor.STRING, TestResultsLevel.MAIN_LEVEL);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("numberOfSeats", "must be a number");
    }

    @Ignore("Bug - no validation on number of seats max range 5 - ...")
    @Title("")
    @Test
    public void testResultsLengthMaxNumberOfSeats() {

        testResultsSteps.postTestResults(vehicleData.setVrm(VRM).setNumberOfSeats(9));
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("numberOfSeats", "must be smaller than or equal to 4");
    }

    @Title("")
    @Test
    public void testResultsLengthMinNumberOfSeats() {

        testResultsSteps.postTestResults(vehicleData.setVrm(VRM).setNumberOfSeats(0));
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("numberOfSeats", "must be larger than or equal to 1");
    }


    //vehicleStatus

    @Title("")
    @Test
    public void testResultsMissingVehicleStatus() {

        testResultsSteps.postTestResultsFieldChange(vehicleData.setVrm(VRM), "vehicleStatus", ToTypeConvertor.MISSING, TestResultsLevel.MAIN_LEVEL);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("vehicleStatus", "is required");
    }

    @Title("")
    @Test
    public void testResultsNullVehicleStatus() {

        testResultsSteps.postTestResultsFieldChange(vehicleData.setVrm(VRM), "vehicleStatus", ToTypeConvertor.NULL, TestResultsLevel.MAIN_LEVEL);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("vehicleStatus", "must be one of [1, 2, 3]");
    }



    @Title("")
    @Test
    public void testResultsRandomIntegerVehicleStatus() {

        testResultsSteps.postTestResultsFieldChange(vehicleData.setVrm(VRM), "vehicleStatus", RandomStringUtils.randomNumeric(5), ToTypeConvertor.INTEGER, TestResultsLevel.MAIN_LEVEL);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("vehicleStatus", "must be one of [1, 2, 3]");
    }

    @Title("")
    @Test
    public void testResultsUpperBoundValuesVehicleStatus() {

        testResultsSteps.postTestResults(vehicleData.setVrm(VRM).setVehicleStatus("4"));
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("vehicleStatus", "must be one of [1, 2, 3]");
    }

    @Title("")
    @Test
    public void testResultsEmptyVehicleStatus() {

        testResultsSteps.postTestResults(vehicleData.setVrm(VRM).setVehicleStatus(""));
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("vehicleStatus", "must be one of [1, 2, 3]");
    }

    //vehicleConfiguration

    @Title("")
    @Test
    public void testResultsMissingVehicleConfiguration() {

        testResultsSteps.postTestResultsFieldChange(vehicleData.setVrm(VRM), "vehicleConfiguration", ToTypeConvertor.MISSING, TestResultsLevel.MAIN_LEVEL);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("vehicleConfiguration", "is required");
    }

    @Title("")
    @Test
    public void testResultsNullVehicleConfiguration() {

        testResultsSteps.postTestResultsFieldChange(vehicleData.setVrm(VRM), "vehicleConfiguration", ToTypeConvertor.NULL, TestResultsLevel.MAIN_LEVEL);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("vehicleConfiguration", "must be one of [rigid, articulated]");
    }



    @Title("")
    @Test
    public void testResultsIntegerVehicleConfiguration() {

        testResultsSteps.postTestResultsFieldChange(vehicleData.setVrm(VRM), "vehicleConfiguration", RandomStringUtils.randomNumeric(9), ToTypeConvertor.INTEGER, TestResultsLevel.MAIN_LEVEL);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("vehicleConfiguration", "must be one of [rigid, articulated]");
    }

    @Title("")
    @Test
    public void testResultsValueVehicleConfiguration() {

        testResultsSteps.postTestResults(vehicleData.setVrm(VRM).setVehicleConfiguration(RandomStringUtils.randomAlphanumeric(9)));
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("vehicleConfiguration", "must be one of [rigid, articulated]");
    }

    @Title("")
    @Test
    public void testResultsEmptyVehicleConfiguration() {

        testResultsSteps.postTestResults(vehicleData.setVrm(VRM).setVehicleConfiguration(""));
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("vehicleConfiguration", "must be one of [rigid, articulated]");
    }


    //odometerReading

    @Title("")
    @Test
    public void testResultsMissingOdometerReading() {

        testResultsSteps.postTestResultsFieldChange(vehicleData.setVrm(VRM), "odometerReading", ToTypeConvertor.MISSING, TestResultsLevel.MAIN_LEVEL);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("odometerReading", "is required");
    }

    @Title("")
    @Test
    public void testResultsNullOdometerReading() {

        testResultsSteps.postTestResultsFieldChange(vehicleData.setVrm(VRM), "odometerReading", ToTypeConvertor.NULL, TestResultsLevel.MAIN_LEVEL);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("odometerReading", "must be a number");
    }



    @Title("")
    @Test
    public void testResultsStringOdometerReading() {

        testResultsSteps.postTestResultsFieldChange(vehicleData.setVrm(VRM), "odometerReading", RandomStringUtils.randomAlphanumeric(4), ToTypeConvertor.STRING, TestResultsLevel.MAIN_LEVEL);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("odometerReading", "must be a number");
    }

    @Ignore("Bug - no validation on max Odometer reading")
    @Title("")
    @Test
    public void testResultsLengthMaxOdometerReading() {

        testResultsSteps.postTestResults(vehicleData.setVrm(VRM).setOdometerReading(8));
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("odometerReading", "must be smaller than or equal to 7");
    }

    @Ignore("Bug - no validation on min Odometer reading")
    @Title("")
    @Test
    public void testResultsLengthMinOdometerReading() {

        testResultsSteps.postTestResults(vehicleData.setVrm(VRM).setOdometerReading(0));
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("odometerReading", "must be larger than or equal to 1");
    }

    //odometerReadingUnits

    @Title("")
    @Test
    public void testResultsMissingOdometerReadingUnits() {

        testResultsSteps.postTestResultsFieldChange(vehicleData.setVrm(VRM), "odometerReadingUnits", ToTypeConvertor.MISSING, TestResultsLevel.MAIN_LEVEL);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("odometerReadingUnits", "is required");
    }

    @Title("")
    @Test
    public void testResultsNullOdometerReadingUnits() {

        testResultsSteps.postTestResultsFieldChange(vehicleData.setVrm(VRM), "odometerReadingUnits", ToTypeConvertor.NULL, TestResultsLevel.MAIN_LEVEL);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("odometerReadingUnits", "must be one of [kilometers, miles]");
    }



    @Title("")
    @Test
    public void testResultsIntegerOdometerReadingUnits() {

        testResultsSteps.postTestResultsFieldChange(vehicleData.setVrm(VRM), "odometerReadingUnits", RandomStringUtils.randomNumeric(1,9), ToTypeConvertor.INTEGER, TestResultsLevel.MAIN_LEVEL);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("odometerReadingUnits", "must be one of [kilometers, miles]");
    }


    @Title("")
    @Test
    public void testResultsValueOdometerReadingUnits() {

        testResultsSteps.postTestResults(vehicleData.setVrm(VRM).setOdometerReadingUnits(RandomStringUtils.randomAlphanumeric(10)));
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("odometerReadingUnits", "must be one of [kilometers, miles]");
    }

    @Title("")
    @Test
    public void testResultsOdometerReadingUnitsEmpty() {

        testResultsSteps.postTestResults(vehicleData.setVrm(VRM).setOdometerReadingUnits(""));
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("odometerReadingUnits", "must be one of [kilometers, miles]");
    }

    //preparerId


    @Title("")
    @Test
    public void testResultsMissingPreparerId() {

        testResultsSteps.postTestResultsFieldChange(vehicleData.setVrm(VRM), "preparerId", ToTypeConvertor.MISSING, TestResultsLevel.MAIN_LEVEL);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("preparerId", "is required");
    }

    @Title("")
    @Test
    public void testResultsNullPreparerId() {

        testResultsSteps.postTestResultsFieldChange(vehicleData.setVrm(VRM), "preparerId", ToTypeConvertor.NULL, TestResultsLevel.MAIN_LEVEL);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("preparerId", "must be a string");
    }



    @Title("")
    @Test
    public void testResultsIntegerPreparerId() {

        testResultsSteps.postTestResultsFieldChange(vehicleData.setVrm(VRM), "preparerId", RandomStringUtils.randomNumeric(1,9), ToTypeConvertor.INTEGER, TestResultsLevel.MAIN_LEVEL);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("preparerId", "must be a string");
    }


    @Title("")
    @Test
    public void testResultsPreparerIdEmpty() {

        testResultsSteps.postTestResults(vehicleData.setVrm(VRM).setPreparerId(""));
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("preparerId", "is not allowed to be empty");
    }


    //preparerName

    @Title("")
    @Test
    public void testResultsMissingPreparerName() {

        testResultsSteps.postTestResultsFieldChange(vehicleData.setVrm(VRM), "preparerName", ToTypeConvertor.MISSING, TestResultsLevel.MAIN_LEVEL);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("preparerName", "is required");
    }

    @Title("")
    @Test
    public void testResultsNullPreparerName() {

        testResultsSteps.postTestResultsFieldChange(vehicleData.setVrm(VRM), "preparerName", ToTypeConvertor.NULL, TestResultsLevel.MAIN_LEVEL);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("preparerName", "must be a string");
    }



    @Title("")
    @Test
    public void testResultsIntegerPreparerName() {

        testResultsSteps.postTestResultsFieldChange(vehicleData.setVrm(VRM), "preparerName", RandomStringUtils.randomNumeric(1,9), ToTypeConvertor.INTEGER, TestResultsLevel.MAIN_LEVEL);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("preparerName", "must be a string");
    }


    @Title("")
    @Test
    public void testResultsPreparerNameEmpty() {

        testResultsSteps.postTestResults(vehicleData.setVrm(VRM).setPreparerName(""));
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("preparerName", "is not allowed to be empty");
    }


    //euVehicleCategory

    @Title("")
    @Test
    public void testResultsMissingEuVehicleCategory() {

        testResultsSteps.postTestResultsFieldChange(vehicleData.setVrm(VRM), "euVehicleCategory", ToTypeConvertor.MISSING, TestResultsLevel.MAIN_LEVEL);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("euVehicleCategory", "is required");
    }

    @Title("")
    @Test
    public void testResultsNullEuVehicleCategory() {

        testResultsSteps.postTestResultsFieldChange(vehicleData.setVrm(VRM), "euVehicleCategory", ToTypeConvertor.NULL, TestResultsLevel.MAIN_LEVEL);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("euVehicleCategory", "must be one of [M1, M2, M3, N1, N2, N3, O1, O2, O3, O4]");
    }



    @Title("")
    @Test
    public void testResultsIntegerEuVehicleCategory() {

        testResultsSteps.postTestResultsFieldChange(vehicleData.setVrm(VRM), "euVehicleCategory", RandomStringUtils.randomNumeric(1,9), ToTypeConvertor.INTEGER, TestResultsLevel.MAIN_LEVEL);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("euVehicleCategory", "must be one of [M1, M2, M3, N1, N2, N3, O1, O2, O3, O4]");
    }


    @Title("")
    @Test
    public void testResultsValueEuVehicleCategory() {

        testResultsSteps.postTestResults(vehicleData.setVrm(VRM).setEuVehicleCategory(RandomStringUtils.randomAlphanumeric(10)));
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("euVehicleCategory", "must be one of [M1, M2, M3, N1, N2, N3, O1, O2, O3, O4]");
    }

    @Title("")
    @Test
    public void testResultsValueEuVehicleCategoryEmpty() {

        testResultsSteps.postTestResults(vehicleData.setVrm(VRM).setEuVehicleCategory(RandomStringUtils.randomAlphanumeric(0)));
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("euVehicleCategory", "must be one of [M1, M2, M3, N1, N2, N3, O1, O2, O3, O4]");
    }

    //countryOfRegistration

    @Title("")
    @Test
    public void testResultsMissingCountryOfRegistration() {

        testResultsSteps.postTestResultsFieldChange(vehicleData.setVrm(VRM), "countryOfRegistration", ToTypeConvertor.MISSING, TestResultsLevel.MAIN_LEVEL);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("countryOfRegistration", "is required");
    }

    @Title("")
    @Test
    public void testResultsNullCountryOfRegistration() {

        testResultsSteps.postTestResultsFieldChange(vehicleData.setVrm(VRM), "countryOfRegistration", ToTypeConvertor.NULL, TestResultsLevel.MAIN_LEVEL);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("countryOfRegistration", "must be a string");
    }



    @Title("")
    @Test
    public void testResultsIntegerCountryOfRegistration() {

        testResultsSteps.postTestResultsFieldChange(vehicleData.setVrm(VRM), "countryOfRegistration", RandomStringUtils.randomNumeric(1,9), ToTypeConvertor.INTEGER, TestResultsLevel.MAIN_LEVEL);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("countryOfRegistration", "must be a string");
    }


    @Title("")
    @Test
    public void testResultsCountryOfRegistrationEmpty() {

        testResultsSteps.postTestResults(vehicleData.setVrm(VRM).setCountryOfRegistration(""));
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("countryOfRegistration", "is not allowed to be empty");
    }


    @Title("")
    @Test
    public void testResultsTestTypesAsNull() {

        testResultsSteps.postTestResults(vehicleData.setVrm(VRM).setTestTypes(null));
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("testTypes", "must be an array");

    }

    @Ignore("bug high - empty array can be added")
    @Title("")
    @Test
    public void testResultsTestTypesAsEmptyArray() {

        testResultsSteps.postTestResults(vehicleData.setVrm(VRM).setTestTypes(Arrays.asList()));
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("testTypes", "must be an array");

    }

    @Title("")
    @Test
    public void testResultsTestTypesMissing() {

        testResultsSteps.postTestResultsFieldChange(vehicleData.setVrm(VRM), "testTypes", ToTypeConvertor.MISSING, TestResultsLevel.MAIN_LEVEL);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("testTypes", "is required");
    }


    /*

    @Title("")
    @Test
    public void testResultsLogerVrm() {

        testResultsSteps.postTestResults(vehicleData.setVrm(RandomStringUtils.randomAlphanumeric(9)));
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("vrm", "length must be less than or equal to 8 characters long");

    }

    // this
    @Title("")
    @Test
    public void testResultsLogerVrms() {

        testResultsSteps.postTestResultsFieldChange(vehicleData.setVrm("SL72XD"), "testTypeName", "1234", ToTypeConvertor.INTEGER, TestResultsLevel.TEST_TYPES);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("vrm", "must be a string");

    }


    @Title("")
    @Test
    public void testResultsDefectss() {

        testResultsSteps.postTestResultsFieldChange(vehicleData.setVrm("SL72XD"), "forVehicleType", "1234", ToTypeConvertor.INTEGER, TestResultsLevel.DEFECTS);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("vrm", "must be a string");

    }


    @Title("")
    @Test
    public void testResultsDefectsss() {

        testResultsSteps.postTestResultsFieldChange(vehicleData.setVrm("SL72XD"), "forVehicleType", "1234", ToTypeConvertor.INTEGER_ARRAY, TestResultsLevel.DEFECTS);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("vrm", "must be a string");

    }


    @Title("")
    @Test
    public void testResultsDefectsshh() {

        testResultsSteps.postTestResultsFieldChange(vehicleData.setVrm("SL72XD"), "forVehicleType", ToTypeConvertor.NULL_ARRAY, TestResultsLevel.DEFICIENCY);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("vrm", "must be a string");

    }


    @Title("")
    @Test
    public void testResultsDefectsshsh() {

        testResultsSteps.postTestResultsFieldChange(vehicleData.setVrm("SL72XD"), "forVehicleType", "1234", ToTypeConvertor.INTEGER_ARRAY, TestResultsLevel.DEFICIENCY);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("vrm", "must be a string");

    }


    // hereFOR_VEHICLE_TYPE

    @Title("")
    @Test
    public void testResultsLogerSeatss() {

//        testResultsSteps.postTestResultsFieldChange(vehicleData, "numberOfSeats", "1234", ToTypeConvertor.STRING);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("vrm", "must be a string");

    }


    @Title("")
    @Test
    public void testResultsMissingVin() {

        testResultsSteps.postTestResults(vehicleData.setVrm("SL72XC").setVin(null));
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("vin", "is required");

    }

*/
}

