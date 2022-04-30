package testresults;

import clients.util.ToTypeConvertor;
import clients.util.testresult.TestResultsLevel;
import data.TestResultsData;
import model.testresults.TestResults;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.*;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import steps.TestResultsSteps;

import static util.DataUtil.generateRandomExcludingValues;


@RunWith(SerenityRunner.class)
public class PostTestResultsNegMainLvlSubmitted {

    @Steps
    TestResultsSteps testResultsSteps;

    private TestResults.Builder vehicleSubmittedDataOld = TestResultsData.buildTestResultsSubmittedDataOld();
    private static final String VRM = "SL72XD";


    @Title("CVSB-416 - CVSB-949 / CVSB-3512 - Un-authorised consumer creates a new test results")
    @Test
    public void testResultsNoAuthorised() {
        testResultsSteps.postTestResultsNotAuthorised(vehicleSubmittedDataOld.build());
        testResultsSteps.statusCodeShouldBe(403);
        testResultsSteps.validateMessage("User is not authorized to access this resource with an explicit deny");
    }

    @Title("CVSB-416 - CVSB-949 / CVSB-1575 - Un-authenticated consumer creates a new test results")
    @Test
    public void testResultsNoAuthenticated() {
        testResultsSteps.postTestResultsNotAuthenticated(vehicleSubmittedDataOld.build());
        testResultsSteps.statusCodeShouldBe(401);
        testResultsSteps.validateMessage("Unauthorized");
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3510 API Consumer tries to create a new test result for submitted/canceled with extra field defined as calculated - vehicleId")
    @Test
    public void testResultsExtraFieldVehicleId() {

        testResultsSteps.postTestResultsFieldChange(vehicleSubmittedDataOld.setVrm(VRM).build(), "vehicleId", RandomStringUtils.randomAlphanumeric(10), ToTypeConvertor.STRING, TestResultsLevel.TEST_TYPES);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("vehicleId", "is not allowed");
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3506 - API Consumer tries to create a new test result for submitted/canceled with null value for not nullable - all body")
    @Test
    public void testResultsEmptyBody() {

        testResultsSteps.postTestResults(null);
        testResultsSteps.statusCodeShouldBe(400);
//        testResultsSteps.validatePostErrorData("testStatus", "should be one of [\"submitted\", \"cancelled\"]");
        testResultsSteps.validateErrorText("Payload cannot be empty");

    }

    @WithTagValuesOf({"Smoke_1", "Smoke_2"})
    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3505 - API Consumer tries to create a new test result for submitted/canceled with missing property - vrm")
    @Test
    public void testResultsMissingVrm() {

        testResultsSteps.postTestResultsFieldChange(vehicleSubmittedDataOld.setVrm(VRM).build(), "vrm", ToTypeConvertor.MISSING, TestResultsLevel.MAIN_LEVEL);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("vrm", "is required");
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3506 - API Consumer tries to create a new test result for submitted/canceled with null value for not nullable - vrm")
    @Test
    public void testResultsNullVrm() {

        testResultsSteps.postTestResultsFieldChange(vehicleSubmittedDataOld.setVrm(VRM).build(), "vrm", ToTypeConvertor.NULL, TestResultsLevel.MAIN_LEVEL);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("vrm", "must be a string");
    }


    @Title("nullCVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3508 API Consumer tries to create a new test result for submitted/canceled with different property type - vrm")
    @Test
    public void testResultsIntegerVrm() {

        testResultsSteps.postTestResultsFieldChange(vehicleSubmittedDataOld.setVrm(VRM).build(), "vrm", RandomStringUtils.randomNumeric(8), ToTypeConvertor.INTEGER, TestResultsLevel.MAIN_LEVEL);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("vrm", "must be a string");
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3507 - API Consumer tries to create a new test result for submitted/canceled with value/characters exceeding max length - vrm")
    @Test
    public void testResultsLengthMaxVrm() {

        testResultsSteps.postTestResults(vehicleSubmittedDataOld.setVrm(RandomStringUtils.randomAlphanumeric(9)).build());
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("vrm", "length must be less than or equal to 8 characters long");
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3511 API Consumer tries to create a new test result for submitted/canceled with value/characters lower min length - vrm")
    @Test
    public void testResultsLengthMinVrm() {

        testResultsSteps.postTestResults(vehicleSubmittedDataOld.setVrm("").build());
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("vrm", "is not allowed to be empty");
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3505 - API Consumer tries to create a new test result for submitted/canceled with missing property - vin")
    @Test
    public void testResultsMissingVin() {

        testResultsSteps.postTestResultsFieldChange(vehicleSubmittedDataOld.setVrm(VRM).build(), "vin", ToTypeConvertor.MISSING, TestResultsLevel.MAIN_LEVEL);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("vin", "is required");
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3506 - API Consumer tries to create a new test result for submitted/canceled with null value for not nullable - vin")
    @Test
    public void testResultsNullVin() {

        testResultsSteps.postTestResultsFieldChange(vehicleSubmittedDataOld.setVrm(VRM).build(), "vin", ToTypeConvertor.NULL, TestResultsLevel.MAIN_LEVEL);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("vin", "must be a string");
    }


    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3508 API Consumer tries to create a new test result for submitted/canceled with different property type - vin")
    @Test
    public void testResultsIntegerVin() {

        testResultsSteps.postTestResultsFieldChange(vehicleSubmittedDataOld.setVrm(VRM).build(), "vin", RandomStringUtils.randomNumeric(9), ToTypeConvertor.INTEGER, TestResultsLevel.MAIN_LEVEL);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("vin", "must be a string");
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3507 - API Consumer tries to create a new test result for submitted/canceled with value/characters exceeding max length - vin")
    @Test
    public void testResultsLengthMaxVin() {

        testResultsSteps.postTestResults(vehicleSubmittedDataOld.setVrm(VRM).setVin(RandomStringUtils.randomAlphanumeric(22)).build());
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("vin", "length must be less than or equal to 21 characters long");
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3511 API Consumer tries to create a new test result for submitted/canceled with value/characters lower min length - vin")
    @Test
    public void testResultsLengthMinVin() {

        testResultsSteps.postTestResults(vehicleSubmittedDataOld.setVrm(VRM).setVin("").build());
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("vin", "is not allowed to be empty");
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3505 - API Consumer tries to create a new test result for submitted/canceled with missing property - testStationName")
    @Test
    public void testResultsMissingTestStationName() {

        testResultsSteps.postTestResultsFieldChange(vehicleSubmittedDataOld.setVrm(VRM).build(), "testStationName", ToTypeConvertor.MISSING, TestResultsLevel.MAIN_LEVEL);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("testStationName", "is required");
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3506 - API Consumer tries to create a new test result for submitted/canceled with null value for not nullable - testStationName")
    @Test
    public void testResultsNullTestStationName() {

        testResultsSteps.postTestResultsFieldChange(vehicleSubmittedDataOld.setVrm(VRM).build(), "testStationName", ToTypeConvertor.NULL, TestResultsLevel.MAIN_LEVEL);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("testStationName", "must be a string");
    }


    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3508 API Consumer tries to create a new test result for submitted/canceled with different property type - testStationName")
    @Test
    public void testResultsIntegerTestStationName() {

        testResultsSteps.postTestResultsFieldChange(vehicleSubmittedDataOld.setVrm(VRM).build(), "testStationName", RandomStringUtils.randomNumeric(9), ToTypeConvertor.INTEGER, TestResultsLevel.MAIN_LEVEL);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("testStationName", "must be a string");
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3507 - API Consumer tries to create a new test result for submitted/canceled with value/characters exceeding max length - testStationName")
    @Test
    public void testResultsLengthMaxTestStationName() {

        testResultsSteps.postTestResults(vehicleSubmittedDataOld.setVrm(VRM).setTestStationName(RandomStringUtils.randomAlphanumeric(1000)).build());
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("testStationName", "length must be less than or equal to 999 characters long");
    }


    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3505 - API Consumer tries to create a new test result for submitted/canceled with missing property - testStationPNumber")
    @Test
    public void testResultsMissingTestStationPNumber() {

        testResultsSteps.postTestResultsFieldChange(vehicleSubmittedDataOld.setVrm(VRM).build(), "testStationPNumber", ToTypeConvertor.MISSING, TestResultsLevel.MAIN_LEVEL);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("testStationPNumber", "is required");
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3506 - API Consumer tries to create a new test result for submitted/canceled with null value for not nullable - testStationPNumber")
    @Test
    public void testResultsNullTestStationPNumber() {

        testResultsSteps.postTestResultsFieldChange(vehicleSubmittedDataOld.setVrm(VRM).build(), "testStationPNumber", ToTypeConvertor.NULL, TestResultsLevel.MAIN_LEVEL);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("testStationPNumber", "must be a string");
    }


    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3508 API Consumer tries to create a new test result for submitted/canceled with different property type - testStationPNumber")
    @Test
    public void testResultsIntegerTestStationPNumber() {

        testResultsSteps.postTestResultsFieldChange(vehicleSubmittedDataOld.setVrm(VRM).build(), "testStationPNumber", RandomStringUtils.randomNumeric(9), ToTypeConvertor.INTEGER, TestResultsLevel.MAIN_LEVEL);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("testStationPNumber", "must be a string");
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3507 - API Consumer tries to create a new test result for submitted/canceled with value/characters exceeding max length - testStationPNumber")
    @Test
    public void testResultsLengthMaxTestStationPNumber() {

        testResultsSteps.postTestResults(vehicleSubmittedDataOld.setVrm(VRM).setTestStationPNumber(RandomStringUtils.randomAlphanumeric(21)).build());
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("testStationPNumber", "length must be less than or equal to 20 characters long");
    }


    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3505 - API Consumer tries to create a new test result for submitted/canceled with missing property - testStationType")
    @Test
    public void testResultsMissingTestStationType() {

        testResultsSteps.postTestResultsFieldChange(vehicleSubmittedDataOld.setVrm(VRM).build(), "testStationType", ToTypeConvertor.MISSING, TestResultsLevel.MAIN_LEVEL);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("testStationType", "is required");
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3506 - API Consumer tries to create a new test result for submitted/canceled with null value for not nullable - testStationType")
    @Test
    public void testResultsNullTestStationType() {

        testResultsSteps.postTestResultsFieldChange(vehicleSubmittedDataOld.setVrm(VRM).build(), "testStationType", ToTypeConvertor.NULL, TestResultsLevel.MAIN_LEVEL);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("testStationType", "must be one of [atf, gvts, hq]");
    }


    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3508 API Consumer tries to create a new test result for submitted/canceled with different property type - testStationType")
    @Test
    public void testResultsIntegerTestStationType() {

        testResultsSteps.postTestResultsFieldChange(vehicleSubmittedDataOld.setVrm(VRM).build(), "testStationType", RandomStringUtils.randomNumeric(1, 9), ToTypeConvertor.INTEGER, TestResultsLevel.MAIN_LEVEL);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("testStationType", "must be one of [atf, gvts, hq]");
    }


    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3509 - API Consumer tries to create a new test result for submitted/canceled with different format or allowed values - testStationType random")
    @Test
    public void testResultsValueTestStationType() {

        testResultsSteps.postTestResults(vehicleSubmittedDataOld.setVrm(VRM).setTestStationType(RandomStringUtils.randomAlphanumeric(10)).build());
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("testStationType", "must be one of [atf, gvts, hq]");
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3509 - API Consumer tries to create a new test result for submitted/canceled with different format or allowed values - testStationType empty")
    @Test
    public void testResultsValueTestStationTypeEmpty() {

        testResultsSteps.postTestResults(vehicleSubmittedDataOld.setVrm(VRM).setTestStationType("").build());
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("testStationType", "must be one of [atf, gvts, hq]");
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3505 - API Consumer tries to create a new test result for submitted/canceled with missing property - testerName")
    @Test
    public void testResultsMissingTesterName() {

        testResultsSteps.postTestResultsFieldChange(vehicleSubmittedDataOld.setVrm(VRM).build(), "testerName", ToTypeConvertor.MISSING, TestResultsLevel.MAIN_LEVEL);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("testerName", "is required");
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3506 - API Consumer tries to create a new test result for submitted/canceled with null value for not nullable - testerName")
    @Test
    public void testResultsNullTesterName() {

        testResultsSteps.postTestResultsFieldChange(vehicleSubmittedDataOld.setVrm(VRM).build(), "testerName", ToTypeConvertor.NULL, TestResultsLevel.MAIN_LEVEL);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("testerName", "must be a string");
    }


    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3508 API Consumer tries to create a new test result for submitted/canceled with different property type - testerName")
    @Test
    public void testResultsIntegerTesterName() {

        testResultsSteps.postTestResultsFieldChange(vehicleSubmittedDataOld.setVrm(VRM).build(), "testerName", RandomStringUtils.randomNumeric(9), ToTypeConvertor.INTEGER, TestResultsLevel.MAIN_LEVEL);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("testerName", "must be a string");
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3507 - API Consumer tries to create a new test result for submitted/canceled with value/characters exceeding max length - testerName")
    @Test
    public void testResultsLengthMaxTesterName() {

        testResultsSteps.postTestResults(vehicleSubmittedDataOld.setVrm(VRM).setTesterName(RandomStringUtils.randomAlphanumeric(61)).build());
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("testerName", "length must be less than or equal to 60 characters long");
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3505 - API Consumer tries to create a new test result for submitted/canceled with missing property - testerStaffId")
    @Test
    public void testResultsMissingTesterStaffId() {

        testResultsSteps.postTestResultsFieldChange(vehicleSubmittedDataOld.setVrm(VRM).build(), "testerStaffId", ToTypeConvertor.MISSING, TestResultsLevel.MAIN_LEVEL);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("testerStaffId", "is required");
    }


    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3506 - API Consumer tries to create a new test result for submitted/canceled with null value for not nullable - testerStaffId")
    @Test
    public void testResultsNullTesterStaffId() {

        testResultsSteps.postTestResultsFieldChange(vehicleSubmittedDataOld.setVrm(VRM).build(), "testerStaffId", ToTypeConvertor.NULL, TestResultsLevel.MAIN_LEVEL);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("testerStaffId", "must be a string");
    }


    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3508 API Consumer tries to create a new test result for submitted/canceled with different property type - testerStaffId")
    @Test
    public void testResultsIntegerTesterStaffId() {

        testResultsSteps.postTestResultsFieldChange(vehicleSubmittedDataOld.setVrm(VRM).build(), "testerStaffId", RandomStringUtils.randomNumeric(9), ToTypeConvertor.INTEGER, TestResultsLevel.MAIN_LEVEL);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("testerStaffId", "must be a string");
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3507 - API Consumer tries to create a new test result for submitted/canceled with value/characters exceeding max length - testerStaffId")
    @Test
    public void testResultsLengthMaxTesterStaffId() {

        testResultsSteps.postTestResults(vehicleSubmittedDataOld.setVrm(VRM).setTesterStaffId(RandomStringUtils.randomAlphanumeric(37)).build());
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("testerStaffId", "length must be less than or equal to 36 characters long");
    }


    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3505 - API Consumer tries to create a new test result for submitted/canceled with missing property - testerEmailAddress")
    @Test
    public void testResultsMissingTesterEmailAddress() {

        testResultsSteps.postTestResultsFieldChange(vehicleSubmittedDataOld.setVrm(VRM).build(), "testerEmailAddress", ToTypeConvertor.MISSING, TestResultsLevel.MAIN_LEVEL);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("testerEmailAddress", "is required");
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3506 - API Consumer tries to create a new test result for submitted/canceled with null value for not nullable - testerEmailAddress")
    @Test
    public void testResultsNullTesterEmailAddress() {

        testResultsSteps.postTestResultsFieldChange(vehicleSubmittedDataOld.setVrm(VRM).build(), "testerEmailAddress", ToTypeConvertor.NULL, TestResultsLevel.MAIN_LEVEL);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("testerEmailAddress", "must be a string");
    }


    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3508 API Consumer tries to create a new test result for submitted/canceled with different property type - testerEmailAddress")
    @Test
    public void testResultsIntegerTesterEmailAddress() {

        testResultsSteps.postTestResultsFieldChange(vehicleSubmittedDataOld.setVrm(VRM).build(), "testerEmailAddress", RandomStringUtils.randomNumeric(9), ToTypeConvertor.INTEGER, TestResultsLevel.MAIN_LEVEL);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("testerEmailAddress", "must be a string");
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3507 - API Consumer tries to create a new test result for submitted/canceled with value/characters exceeding max length - testerEmailAddress")
    @Test
    public void testResultsLengthMaxTesterEmailAddress() {

        testResultsSteps.postTestResults(vehicleSubmittedDataOld.setVrm(VRM).setTesterEmailAddress(RandomStringUtils.randomAlphanumeric(61)).build());
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("testerEmailAddress", "length must be less than or equal to 60 characters long");
    }


    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3505 - API Consumer tries to create a new test result for submitted/canceled with missing property - testStartTimestamp")
    @Test
    public void testResultsMissingTestStartTimestamp() {

        testResultsSteps.postTestResultsFieldChange(vehicleSubmittedDataOld.setVrm(VRM).build(), "testStartTimestamp", ToTypeConvertor.MISSING, TestResultsLevel.MAIN_LEVEL);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("testStartTimestamp", "is required");
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3506 - API Consumer tries to create a new test result for submitted/canceled with null value for not nullable - testStartTimestamp")
    @Test
    public void testResultsNullTestStartTimestamp() {

        testResultsSteps.postTestResultsFieldChange(vehicleSubmittedDataOld.setVrm(VRM).build(), "testStartTimestamp", ToTypeConvertor.NULL, TestResultsLevel.MAIN_LEVEL);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("testStartTimestamp", "must be a valid ISO 8601 date");
    }


    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3508 API Consumer tries to create a new test result for submitted/canceled with different property type - testStartTimestamp")
    @Test
    public void testResultsIntegerTestStartTimestamp() {

        testResultsSteps.postTestResultsFieldChange(vehicleSubmittedDataOld.setVrm(VRM).build(), "testStartTimestamp", RandomStringUtils.randomNumeric(9), ToTypeConvertor.INTEGER, TestResultsLevel.MAIN_LEVEL);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("testStartTimestamp", "must be a valid ISO 8601 date");
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3509 - API Consumer tries to create a new test result for submitted/canceled with different format or allowed values - testStartTimestamp random")
    @Test
    public void testResultsRandomStringTestStartTimestamp() {

        testResultsSteps.postTestResults(vehicleSubmittedDataOld.setVrm(VRM).setTestStartTimestamp(RandomStringUtils.randomAlphanumeric(10)).build());
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("testStartTimestamp", "must be a valid ISO 8601 date");
    }


    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3509 - API Consumer tries to create a new test result for submitted/canceled with different format or allowed values - testStartTimestamp empty")
    @Test
    public void testResultsEmptyTestStartTimestamp() {

        testResultsSteps.postTestResults(vehicleSubmittedDataOld.setVrm(VRM).setTestStartTimestamp("").build());
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("testStartTimestamp", "must be a valid ISO 8601 date");
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3505 - API Consumer tries to create a new test result for submitted/canceled with missing property - testEndTimestamp")
    @Test
    public void testResultsMissingTestEndTimestamp() {

        testResultsSteps.postTestResultsFieldChange(vehicleSubmittedDataOld.setVrm(VRM).build(), "testEndTimestamp", ToTypeConvertor.MISSING, TestResultsLevel.MAIN_LEVEL);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("testEndTimestamp", "is required");
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3506 - API Consumer tries to create a new test result for submitted/canceled with null value for not nullable - testEndTimestamp")
    @Test
    public void testResultsNullTestEndTimestamp() {

        testResultsSteps.postTestResultsFieldChange(vehicleSubmittedDataOld.setVrm(VRM).build(), "testEndTimestamp", ToTypeConvertor.NULL, TestResultsLevel.MAIN_LEVEL);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("testEndTimestamp", "must be a valid ISO 8601 date");
    }


    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3508 API Consumer tries to create a new test result for submitted/canceled with different property type - testEndTimestamp")
    @Test
    public void testResultsIntegerTestEndTimestamp() {

        testResultsSteps.postTestResultsFieldChange(vehicleSubmittedDataOld.setVrm(VRM).build(), "testEndTimestamp", RandomStringUtils.randomNumeric(9), ToTypeConvertor.INTEGER, TestResultsLevel.MAIN_LEVEL);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("testEndTimestamp", "must be a valid ISO 8601 date");
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3509 - API Consumer tries to create a new test result for submitted/canceled with different format or allowed values - testEndTimestamp random")
    @Test
    public void testResultsRandomStringTestEndTimestamp() {

        testResultsSteps.postTestResults(vehicleSubmittedDataOld.setVrm(VRM).setTestEndTimestamp(RandomStringUtils.randomAlphanumeric(5)).build());
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("testEndTimestamp", "must be a valid ISO 8601 date");
    }


    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3509 - API Consumer tries to create a new test result for submitted/canceled with different format or allowed values - testEndTimestamp empty")
    @Test
    public void testResultsEmptyTestEndTimestamp() {

        testResultsSteps.postTestResults(vehicleSubmittedDataOld.setVrm(VRM).setTestEndTimestamp("").build());
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("testEndTimestamp", "must be a valid ISO 8601 date");
    }


    //@Ignore("Ignored until CVSB-9015 is fixed")
    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3505 - API Consumer tries to create a new test result for submitted/canceled with missing property - testStatus")
    public void testResultsMissingTestStatus() {

        testResultsSteps.postTestResultsFieldChange(vehicleSubmittedDataOld.setVrm(VRM).build(), "testStatus", ToTypeConvertor.MISSING, TestResultsLevel.MAIN_LEVEL);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("testStatus", "should be one of [\"submitted\", \"cancelled\"]");
    }


   // @Ignore("Ignored until CVSB-9015 is fixed")
    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3506 - API Consumer tries to create a new test result for submitted/canceled with null value for not nullable - testStatus")
    public void testResultsNullTestStatus() {

        testResultsSteps.postTestResultsFieldChange(vehicleSubmittedDataOld.setVrm(VRM).build(), "testStatus", ToTypeConvertor.NULL, TestResultsLevel.MAIN_LEVEL);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("testStatus", "should be one of [\"submitted\", \"cancelled\"]");
    }



    //@Ignore ("integer testStatus should have returned an error - instead returns 502 - defect Id CVSB-9015")
    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3508 API Consumer tries to create a new test result for submitted/canceled with different property type - testStatus")
    public void testResultsIntegerTestStatus() {

        testResultsSteps.postTestResultsFieldChange(vehicleSubmittedDataOld.setVrm(VRM).build(), "testStatus", RandomStringUtils.randomNumeric(1, 9), ToTypeConvertor.INTEGER, TestResultsLevel.MAIN_LEVEL);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("testStatus", "should be one of [\"submitted\", \"cancelled\"]");
    }

    //@Ignore ("random testStatus should have returned an error - instead returns 502 - defect Id CVSB-9015")
    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3509 - API Consumer tries to create a new test result for submitted/canceled with different format or allowed values - testStatus random")
    public void testResultsValueTestStatus() {

        testResultsSteps.postTestResults(vehicleSubmittedDataOld.setVrm(VRM).setTestStatus(RandomStringUtils.randomAlphanumeric(10)).build());
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("testStatus", "should be one of [\"submitted\", \"cancelled\"]");
    }

    //@Ignore ("empty testStatus should have returned an error - instead returns bad Gateway - defect Id CVSB-9015")
    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3509 - API Consumer tries to create a new test result for submitted/canceled with different format or allowed values - testStatus empty")
    public void testResultsValueTestStatusEmpty() {

        testResultsSteps.postTestResults(vehicleSubmittedDataOld.setVrm(VRM).setTestStatus("").build());
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("testStatus", "should be one of [\"submitted\", \"cancelled\"]");
    }


    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3505 - API Consumer tries to create a new test result for submitted/canceled with missing property - reasonForCancellation")
    @Test
    public void testResultsMissingReasonForCancellation() {

        testResultsSteps.postTestResultsFieldChange(vehicleSubmittedDataOld.setVrm(VRM).build(), "reasonForCancellation", ToTypeConvertor.MISSING, TestResultsLevel.MAIN_LEVEL);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("reasonForCancellation", "is required");
    }


    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3508 API Consumer tries to create a new test result for submitted/canceled with different property type - reasonForCancellation")
    @Test
    public void testResultsIntegerReasonForCancellation() {

        testResultsSteps.postTestResultsFieldChange(vehicleSubmittedDataOld.setVrm(VRM).build(), "reasonForCancellation", RandomStringUtils.randomNumeric(9), ToTypeConvertor.INTEGER, TestResultsLevel.MAIN_LEVEL);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("reasonForCancellation", "must be a string");
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3507 - API Consumer tries to create a new test result for submitted/canceled with value/characters exceeding max length - reasonForCancellation")
    @Test
    public void testResultsLengthMaxReasonForCancellation() {

        testResultsSteps.postTestResults(vehicleSubmittedDataOld.setVrm(VRM).setReasonForCancellation(RandomStringUtils.randomAlphanumeric(501)).build());
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("reasonForCancellation", "length must be less than or equal to 500 characters long");
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3508 API Consumer tries to create a new test result for submitted/canceled with different property type - vehicleClass description")
    @Test
    public void testResultsIntegerVehicleClassDescription() {

        testResultsSteps.postTestResultsFieldChange(vehicleSubmittedDataOld.setVrm(VRM).build(), "description", RandomStringUtils.randomNumeric(1, 9), ToTypeConvertor.INTEGER, TestResultsLevel.VEHICLE_CLASS);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("description", "must be one of [motorbikes up to 200cc, motorbikes over 200cc or with a sidecar, 3 wheelers, not applicable, small psv (ie: less than or equal to 22 seats), trailer, large psv(ie: greater than 23 seats), heavy goods vehicle, MOT class 4, MOT class 5, MOT class 7, PSV of unknown or unspecified size, Not Known, null]");
    }


    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3509 - API Consumer tries to create a new test result for submitted/canceled with different format or allowed values - vehicle class description random")
    @Test
    public void testResultsValueVehicleClassDescription() {

        vehicleSubmittedDataOld.setVrm(VRM).getVehicleClass().setDescription(RandomStringUtils.randomAlphanumeric(10));
        testResultsSteps.postTestResults(vehicleSubmittedDataOld.build());
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("description", "must be one of [motorbikes up to 200cc, motorbikes over 200cc or with a sidecar, 3 wheelers, not applicable, small psv (ie: less than or equal to 22 seats), trailer, large psv(ie: greater than 23 seats), heavy goods vehicle, MOT class 4, MOT class 5, MOT class 7, PSV of unknown or unspecified size, Not Known, null]");
    }

    @Test
    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3509 - API Consumer tries to create a new test result for submitted/canceled with different format or allowed values - vehicle class description empty")
    public void testResultsValueVehicleClassDescriptionEmpty() {

        vehicleSubmittedDataOld.setVrm(VRM).getVehicleClass().setDescription("");
        testResultsSteps.postTestResults(vehicleSubmittedDataOld.build());
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("description", "must be one of [motorbikes up to 200cc, motorbikes over 200cc or with a sidecar, 3 wheelers, not applicable, small psv (ie: less than or equal to 22 seats), trailer, large psv(ie: greater than 23 seats), heavy goods vehicle, MOT class 4, MOT class 5, MOT class 7, PSV of unknown or unspecified size, Not Known, null]");
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3508 API Consumer tries to create a new test result for submitted/canceled with different property type - vehicleClass code")
    @Test
    public void testResultsIntegerVehicleClassCode() {

        testResultsSteps.postTestResultsFieldChange(vehicleSubmittedDataOld.setVrm(VRM).build(), "code", RandomStringUtils.randomNumeric(1, 9), ToTypeConvertor.INTEGER, TestResultsLevel.VEHICLE_CLASS);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("code", "must be one of [1, 2, 3, n, s, t, l, v, 4, 5, 7, p, u, null]");
    }

    @Test
    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3509 - API Consumer tries to create a new test result for submitted/canceled with different format or allowed values - vehicle class code random")
    public void testResultsValueVehicleClassCode() {

        vehicleSubmittedDataOld.setVrm(VRM).getVehicleClass().setCode(RandomStringUtils.randomAlphanumeric(10));
        testResultsSteps.postTestResults(vehicleSubmittedDataOld.build());
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("code", "must be one of [1, 2, 3, n, s, t, l, v, 4, 5, 7, p, u, null]");
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3509 - API Consumer tries to create a new test result for submitted/canceled with different format or allowed values - vehicle class code empty")
    @Test
    public void testResultsValueVehicleClassCodeEmpty() {

        vehicleSubmittedDataOld.setVrm(VRM).getVehicleClass().setCode("");
        testResultsSteps.postTestResults(vehicleSubmittedDataOld.build());
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("code", "must be one of [1, 2, 3, n, s, t, l, v, 4, 5, 7, p, u, null]");
    }

    //@Ignore ("missing vehicleType should have returned an error - instead returns bad Gateway - defect Id CVSB-9015")
    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3505 - API Consumer tries to create a new test result for submitted/canceled with missing property - vehicleType")
    public void testResultsMissingVehicleType() {

        testResultsSteps.postTestResultsFieldChange(vehicleSubmittedDataOld.setVrm(VRM).build(), "vehicleType", ToTypeConvertor.MISSING, TestResultsLevel.MAIN_LEVEL);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("vehicleType", "is required");
    }

    //@Ignore ("null vehicleType should have returned an error - instead returns bad Gateway - defect Id CVSB-9015")
    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3506 - API Consumer tries to create a new test result for submitted/canceled with null value for not nullable - vehicleType")
    public void testResultsNullVehicleType() {

        testResultsSteps.postTestResultsFieldChange(vehicleSubmittedDataOld.setVrm(VRM).build(), "vehicleType", ToTypeConvertor.NULL, TestResultsLevel.MAIN_LEVEL);
        testResultsSteps.statusCodeShouldBe(502);
        testResultsSteps.validateData("must be one of [psv, hgv, trl]");
    }

    @Ignore ("integer vehicleType should have returned an error - instead returns bad Gateway - defect Id CVSB-9015")
    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3508 API Consumer tries to create a new test result for submitted/canceled with different property type - vehicleType")
    public void testResultsIntegerVehicleType() {

        testResultsSteps.postTestResultsFieldChange(vehicleSubmittedDataOld.setVrm(VRM).build(), "vehicleType", RandomStringUtils.randomNumeric(1,9), ToTypeConvertor.INTEGER, TestResultsLevel.MAIN_LEVEL);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("vehicleType", "must be one of [psv, hgv, trl]");
    }

    //@Ignore ("random vehicleType should have returned an error - instead returns bad Gateway - defect Id CVSB-9015")
    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3509 - API Consumer tries to create a new test result for submitted/canceled with different format or allowed values - vehicleType random")
    public void testResultsValueVehicleType() {

        testResultsSteps.postTestResults(vehicleSubmittedDataOld.setVrm(VRM).setVehicleType(RandomStringUtils.randomAlphanumeric(10)).build());
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("vehicleType", "must be one of [psv, hgv, trl]");
    }

    //@Ignore ("empty vehicleType should have returned an error - instead returns bad Gateway - defect Id CVSB-9015")
    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3509 - API Consumer tries to create a new test result for submitted/canceled with different format or allowed values - vehicleType empty")
    public void testResultsValueVehicleTypeEmpty() {

        testResultsSteps.postTestResults(vehicleSubmittedDataOld.setVrm(VRM).setVehicleType("").build());
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("vehicleType", "must be one of [psv, hgv, trl]");
    }


    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3505 - API Consumer tries to create a new test result for submitted/canceled with missing property - numberOfSeats")
    @Test
    public void testResultsMissingNumberOFSeats() {

        testResultsSteps.postTestResultsFieldChange(vehicleSubmittedDataOld.setVrm(VRM).build(), "numberOfSeats", ToTypeConvertor.MISSING, TestResultsLevel.MAIN_LEVEL);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("numberOfSeats", "is required");
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3506 - API Consumer tries to create a new test result for submitted/canceled with null value for not nullable - numberOfSeats")
    @Test
    public void testResultsNullNumberOfSeats() {

        testResultsSteps.postTestResultsFieldChange(vehicleSubmittedDataOld.setVrm(VRM).build(), "numberOfSeats", ToTypeConvertor.NULL, TestResultsLevel.MAIN_LEVEL);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("numberOfSeats", "must be a number");
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3508 API Consumer tries to create a new test result for submitted/canceled with different property type - numberOfSeats")
    @Test
    public void testResultsStringNumberOfSeats() {

        testResultsSteps.postTestResultsFieldChange(vehicleSubmittedDataOld.setVrm(VRM).build(), "numberOfSeats", RandomStringUtils.randomAlphanumeric(4), ToTypeConvertor.STRING, TestResultsLevel.MAIN_LEVEL);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("numberOfSeats", "must be a number");
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - noOfAxles")
    @Test
    public void testResultsRandomNumberNoOfAxles() {

        testResultsSteps.postTestResults(vehicleSubmittedDataOld.setVin(generateRandomExcludingValues(21, vehicleSubmittedDataOld.build().getVin()))
                .setVrm(generateRandomExcludingValues(7, vehicleSubmittedDataOld.build().getVrm()))
                .setNoOfAxles(123).build());

        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("noOfAxles", "must be less than or equal to 99");
    }


    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3505 - API Consumer tries to create a new test result for submitted/canceled with missing property - vehicleConfiguration")
    @Test
    public void testResultsMissingVehicleConfiguration() {

        testResultsSteps.postTestResultsFieldChange(vehicleSubmittedDataOld.setVrm(VRM).build(), "vehicleConfiguration", ToTypeConvertor.MISSING, TestResultsLevel.MAIN_LEVEL);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("vehicleConfiguration", "is required");
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3506 - API Consumer tries to create a new test result for submitted/canceled with null value for not nullable - vehicleConfiguration")
    @Test
    public void testResultsNullVehicleConfiguration() {

        testResultsSteps.postTestResultsFieldChange(vehicleSubmittedDataOld.setVrm(VRM).build(), "vehicleConfiguration", ToTypeConvertor.NULL, TestResultsLevel.MAIN_LEVEL);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("vehicleConfiguration", "must be one of [rigid, articulated]");
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3508 API Consumer tries to create a new test result for submitted/canceled with different property type - vehicleConfiguration")
    @Test
    public void testResultsIntegerVehicleConfiguration() {

        testResultsSteps.postTestResultsFieldChange(vehicleSubmittedDataOld.setVrm(VRM).build(), "vehicleConfiguration", RandomStringUtils.randomNumeric(9), ToTypeConvertor.INTEGER, TestResultsLevel.MAIN_LEVEL);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("vehicleConfiguration", "must be one of [rigid, articulated]");
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3509 - API Consumer tries to create a new test result for submitted/canceled with different format or allowed values - vehicleConfiguration random")
    @Test
    public void testResultsValueVehicleConfiguration() {

        testResultsSteps.postTestResults(vehicleSubmittedDataOld.setVrm(VRM).setVehicleConfiguration(RandomStringUtils.randomAlphanumeric(9)).build());
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("vehicleConfiguration", "must be one of [rigid, articulated]");
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3509 - API Consumer tries to create a new test result for submitted/canceled with different format or allowed values - vehicleConfiguration empty")
    @Test
    public void testResultsEmptyVehicleConfiguration() {

        testResultsSteps.postTestResults(vehicleSubmittedDataOld.setVrm(VRM).setVehicleConfiguration("").build());
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("vehicleConfiguration", "must be one of [rigid, articulated]");
    }


    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3505 - API Consumer tries to create a new test result for submitted/canceled with missing property - odometerReading")
    @Test
    public void testResultsMissingOdometerReading() {

        testResultsSteps.postTestResultsFieldChange(vehicleSubmittedDataOld.setVrm(VRM).build(), "odometerReading", ToTypeConvertor.MISSING, TestResultsLevel.MAIN_LEVEL);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("odometerReading", "is mandatory");
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3506 - API Consumer tries to create a new test result for submitted/canceled with null value for not nullable - odometerReading")
    public void testResultsNullOdometerReading() {

        testResultsSteps.postTestResultsFieldChange(vehicleSubmittedDataOld.setVrm(VRM).build(), "odometerReading", ToTypeConvertor.NULL, TestResultsLevel.MAIN_LEVEL);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("odometerReading", "must be a number");
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3508 API Consumer tries to create a new test result for submitted/canceled with different property type - odometerReading")
    @Test
    public void testResultsStringOdometerReading() {

        testResultsSteps.postTestResultsFieldChange(vehicleSubmittedDataOld.setVrm(VRM).build(), "odometerReading", RandomStringUtils.randomAlphanumeric(4), ToTypeConvertor.STRING, TestResultsLevel.MAIN_LEVEL);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("odometerReading", "must be a number");
    }


    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3505 - API Consumer tries to create a new test result for submitted/canceled with missing property - odometerReadingUnits")
    @Test
    public void testResultsMissingOdometerReadingUnits() {

        testResultsSteps.postTestResultsFieldChange(vehicleSubmittedDataOld.setVrm(VRM).build(), "odometerReadingUnits", ToTypeConvertor.MISSING, TestResultsLevel.MAIN_LEVEL);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("odometerReadingUnits", "is mandatory");
    }


    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3506 - API Consumer tries to create a new test result for submitted/canceled with null value for not nullable - odometerReadingUnits")
    public void testResultsNullOdometerReadingUnits() {

        testResultsSteps.postTestResultsFieldChange(vehicleSubmittedDataOld.setVrm(VRM).build(), "odometerReadingUnits", ToTypeConvertor.NULL, TestResultsLevel.MAIN_LEVEL);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("odometerReadingUnits", "must be one of [kilometres, miles]");
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3508 API Consumer tries to create a new test result for submitted/canceled with different property type - odometerReadingUnits")
    @Test
    public void testResultsIntegerOdometerReadingUnits() {

        testResultsSteps.postTestResultsFieldChange(vehicleSubmittedDataOld.setVrm(VRM).build(), "odometerReadingUnits", RandomStringUtils.randomNumeric(1,9), ToTypeConvertor.INTEGER, TestResultsLevel.MAIN_LEVEL);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("odometerReadingUnits", "must be one of [kilometres, miles, null]");
    }


    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3509 - API Consumer tries to create a new test result for submitted/canceled with different format or allowed values - odometerReadingUnits random")
    @Test
    public void testResultsValueOdometerReadingUnits() {

        testResultsSteps.postTestResults(vehicleSubmittedDataOld.setVrm(VRM).setOdometerReadingUnits(RandomStringUtils.randomAlphanumeric(10)).build());
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("odometerReadingUnits", "must be one of [kilometres, miles, null]");
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3509 - API Consumer tries to create a new test result for submitted/canceled with different format or allowed values - odometerReadingUnits empty")
    public void testResultsOdometerReadingUnitsEmpty() {

        testResultsSteps.postTestResults(vehicleSubmittedDataOld.setVrm(VRM).setOdometerReadingUnits("").build());
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("odometerReadingUnits", "must be one of [kilometres, miles]");
    }


    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3505 - API Consumer tries to create a new test result for submitted/canceled with missing property - preparerId")
    @Test
    public void testResultsMissingPreparerId() {

        testResultsSteps.postTestResultsFieldChange(vehicleSubmittedDataOld.setVrm(VRM).build(), "preparerId", ToTypeConvertor.MISSING, TestResultsLevel.MAIN_LEVEL);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("preparerId", "is required");
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3506 - API Consumer tries to create a new test result for submitted/canceled with null value for not nullable - preparerId")
    @Test
    public void testResultsNullPreparerId() {

        testResultsSteps.postTestResultsFieldChange(vehicleSubmittedDataOld.setVrm(VRM).build(), "preparerId", ToTypeConvertor.NULL, TestResultsLevel.MAIN_LEVEL);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("preparerId", "must be a string");
    }



    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3508 API Consumer tries to create a new test result for submitted/canceled with different property type - preparerId")
    @Test
    public void testResultsIntegerPreparerId() {

        testResultsSteps.postTestResultsFieldChange(vehicleSubmittedDataOld.setVrm(VRM).build(), "preparerId", RandomStringUtils.randomNumeric(1,9), ToTypeConvertor.INTEGER, TestResultsLevel.MAIN_LEVEL);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("preparerId", "must be a string");
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3505 - API Consumer tries to create a new test result for submitted/canceled with missing property - preparerName")
    @Test
    public void testResultsMissingPreparerName() {

        testResultsSteps.postTestResultsFieldChange(vehicleSubmittedDataOld.setVrm(VRM).build(), "preparerName", ToTypeConvertor.MISSING, TestResultsLevel.MAIN_LEVEL);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("preparerName", "is required");
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3506 - API Consumer tries to create a new test result for submitted/canceled with null value for not nullable - preparerName")
    @Test
    public void testResultsNullPreparerName() {

        testResultsSteps.postTestResultsFieldChange(vehicleSubmittedDataOld.setVrm(VRM).build(), "preparerName", ToTypeConvertor.NULL, TestResultsLevel.MAIN_LEVEL);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("preparerName", "must be a string");
    }


    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3508 API Consumer tries to create a new test result for submitted/canceled with different property type - preparerName")
    @Test
    public void testResultsIntegerPreparerName() {

        testResultsSteps.postTestResultsFieldChange(vehicleSubmittedDataOld.setVrm(VRM).build(), "preparerName", RandomStringUtils.randomNumeric(1,9), ToTypeConvertor.INTEGER, TestResultsLevel.MAIN_LEVEL);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("preparerName", "must be a string");
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3505 - API Consumer tries to create a new test result for submitted/canceled with missing property - euVehicleCategory")
    @Test
    public void testResultsMissingEuVehicleCategory() {

        testResultsSteps.postTestResultsFieldChange(vehicleSubmittedDataOld.setVrm(VRM).build(), "euVehicleCategory", ToTypeConvertor.MISSING, TestResultsLevel.MAIN_LEVEL);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("euVehicleCategory", "is mandatory");
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3506 - API Consumer tries to create a new test result for submitted/canceled with null value for not nullable - euVehicleCategory")
    public void testResultsNullEuVehicleCategory() {

        testResultsSteps.postTestResultsFieldChange(vehicleSubmittedDataOld.setVrm(VRM).build(), "euVehicleCategory", ToTypeConvertor.NULL, TestResultsLevel.MAIN_LEVEL);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("euVehicleCategory", "must be one of [m1, m2, m3, n1, n2, n3, o1, o2, o3, o4]");
    }


    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3508 API Consumer tries to create a new test result for submitted/canceled with different property type - euVehicleCategory")
    @Test
    public void testResultsIntegerEuVehicleCategory() {

        testResultsSteps.postTestResultsFieldChange(vehicleSubmittedDataOld.setVrm(VRM).build(), "euVehicleCategory", RandomStringUtils.randomNumeric(1,9), ToTypeConvertor.INTEGER, TestResultsLevel.MAIN_LEVEL);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("euVehicleCategory", "must be one of [m1, m2, m3, n1, " +
                "n2, n3, o1, o2, o3, o4, l1e-a, l1e, l2e, l3e, l4e, l5e, l6e, l7e, null]");
    }


    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3509 - API Consumer tries to create a new test result for submitted/canceled with different format or allowed values - euVehicleCategory random")
    @Test
    public void testResultsValueEuVehicleCategory() {

        testResultsSteps.postTestResults(vehicleSubmittedDataOld.setVrm(VRM).setEuVehicleCategory(RandomStringUtils.randomAlphanumeric(10)).build());
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("euVehicleCategory", "must be one of [m1, m2, m3, n1, " +
                "n2, n3, o1, o2, o3, o4, l1e-a, l1e, l2e, l3e, l4e, l5e, l6e, l7e, null]");
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3509 - API Consumer tries to create a new test result for submitted/canceled with different format or allowed values - euVehicleCategory empty")
    public void testResultsValueEuVehicleCategoryEmpty() {

        testResultsSteps.postTestResults(vehicleSubmittedDataOld.setVrm(VRM).setEuVehicleCategory("").build());
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("euVehicleCategory", "must be one of [m1, m2, m3, n1, n2, n3, o1, o2, o3, o4]");
    }


    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3505 - API Consumer tries to create a new test result for submitted/canceled with missing property - countryOfRegistration")
    @Test
    public void testResultsMissingCountryOfRegistration() {

        testResultsSteps.postTestResultsFieldChange(vehicleSubmittedDataOld.setVrm(VRM).build(), "countryOfRegistration", ToTypeConvertor.MISSING, TestResultsLevel.MAIN_LEVEL);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("countryOfRegistration", "is mandatory");
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3506 - API Consumer tries to create a new test result for submitted/canceled with null value for not nullable - countryOfRegistration")
    public void testResultsNullCountryOfRegistration() {

        testResultsSteps.postTestResultsFieldChange(vehicleSubmittedDataOld.setVrm(VRM).build(), "countryOfRegistration", ToTypeConvertor.NULL, TestResultsLevel.MAIN_LEVEL);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("countryOfRegistration", "must be a string");
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3508 API Consumer tries to create a new test result for submitted/canceled with different property type - countryOfRegistration")
    public void testResultsIntegerCountryOfRegistration() {

        testResultsSteps.postTestResultsFieldChange(vehicleSubmittedDataOld.setVrm(VRM).build(), "countryOfRegistration", RandomStringUtils.randomNumeric(1,9), ToTypeConvertor.INTEGER, TestResultsLevel.MAIN_LEVEL);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("countryOfRegistration", "must be a string");
    }


    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3505 - API Consumer tries to create a new test result for submitted/canceled with missing property - vehicleSize")
    @Test
    public void testResultsMissingVehicleSize() {

        testResultsSteps.postTestResultsFieldChange(vehicleSubmittedDataOld.setVrm(VRM).build(), "vehicleSize", ToTypeConvertor.MISSING, TestResultsLevel.MAIN_LEVEL);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("vehicleSize", "is required");
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3506 - API Consumer tries to create a new test result for submitted/canceled with null value for not nullable - vehicleSize")
    @Test
    public void testResultsNullVehicleSize() {

        testResultsSteps.postTestResultsFieldChange(vehicleSubmittedDataOld.setVrm(VRM).build(), "vehicleSize", ToTypeConvertor.NULL, TestResultsLevel.MAIN_LEVEL);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("vehicleSize", "must be one of [small, large]");
    }


    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3508 API Consumer tries to create a new test result for submitted/canceled with different property type - vehicleSize")
    @Test
    public void testResultsIntegerVehicleSize() {

        testResultsSteps.postTestResultsFieldChange(vehicleSubmittedDataOld.setVrm(VRM).build(), "vehicleSize", RandomStringUtils.randomNumeric(1,9), ToTypeConvertor.INTEGER, TestResultsLevel.MAIN_LEVEL);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("vehicleSize", "must be one of [small, large]");
    }


    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3509 - API Consumer tries to create a new test result for submitted/canceled with different format or allowed values - vehicleSize random")
    @Test
    public void testResultsValueVehicleSize() {

        testResultsSteps.postTestResults(vehicleSubmittedDataOld.setVrm(VRM).setVehicleSize(RandomStringUtils.randomAlphanumeric(10)).build());
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("vehicleSize", "must be one of [small, large]");
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3509 - API Consumer tries to create a new test result for submitted/canceled with different format or allowed values - vehicleSize empty")
    @Test
    public void testResultsVehicleSizeEmpty() {

        testResultsSteps.postTestResults(vehicleSubmittedDataOld.setVrm(VRM).setVehicleSize("").build());
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("vehicleSize", "must be one of [small, large]");
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3506 - API Consumer tries to create a new test result for submitted/canceled with null value for not nullable - testTypes")
    public void testResultsTestTypesAsNull() {

        testResultsSteps.postTestResults(vehicleSubmittedDataOld.setVrm(VRM).setTestTypes(null).build());
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("testTypes", "must be an array");
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3505 - API Consumer tries to create a new test result for submitted/canceled with missing property - testTypes")
    public void testResultsTestTypesMissing() {

        testResultsSteps.postTestResultsFieldChange(vehicleSubmittedDataOld.setVrm(VRM).build(), "testTypes", ToTypeConvertor.MISSING, TestResultsLevel.MAIN_LEVEL);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("testTypes", "is required");
    }

}

