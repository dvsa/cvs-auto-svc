package testresults;

import clients.util.ToTypeConvertor;
import clients.util.testresult.TestResultsLevel;
import data.TestResultsData;
import model.testresults.TestResults;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import steps.TestResultsSteps;

import java.util.Random;


@RunWith(SerenityRunner.class)
public class PostTestResultsNegLocationSubmitted {

    @Steps
    TestResultsSteps testResultsSteps;

    private TestResults.Builder vehicleSubmittedData = TestResultsData.buildTestResultsSubmittedData();
    private static final String VRM = "SL72XD";


    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3505 - API Consumer tries to create a new test result for submitted/canceled with missing property - vertical")
    @Test
    public void testResultsMissingVertical() {

        testResultsSteps.postTestResultsFieldChange(vehicleSubmittedData.setVrm(VRM).build(), "vertical", ToTypeConvertor.MISSING, TestResultsLevel.LOCATION);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("vertical", "is required");
    }


    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3508 API Consumer tries to create a new test result for submitted/canceled with different property type - vertical")
    @Test
    public void testResultsIntegerVertical() {

        testResultsSteps.postTestResultsFieldChange(vehicleSubmittedData.setVrm(VRM).build(), "vertical", RandomStringUtils.randomNumeric(1, 9), ToTypeConvertor.INTEGER, TestResultsLevel.LOCATION);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("vertical", "must be one of [upper, lower, null]");
    }


    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3509 - API Consumer tries to create a new test result for submitted/canceled with different format or allowed values - vertical random")
    @Test
    public void testResultsRandomValueVertical() {

        testResultsSteps.postTestResultsFieldChange(vehicleSubmittedData.setVrm(VRM).build(), "vertical", RandomStringUtils.randomAlphanumeric(1, 9), ToTypeConvertor.STRING, TestResultsLevel.LOCATION);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("vertical", "must be one of [upper, lower, null]");
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3509 - API Consumer tries to create a new test result for submitted/canceled with different format or allowed values - vertical empty")
    @Test
    public void testResultsVerticalEmpty() {

        testResultsSteps.postTestResultsFieldChange(vehicleSubmittedData.setVrm(VRM).build(), "vertical", "", ToTypeConvertor.STRING, TestResultsLevel.LOCATION);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("vertical", "must be one of [upper, lower, null]");
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3505 - API Consumer tries to create a new test result for submitted/canceled with missing property - horizontal")
    @Test
    public void testResultsMissingHorizontal() {

        testResultsSteps.postTestResultsFieldChange(vehicleSubmittedData.setVrm(VRM).build(), "horizontal", ToTypeConvertor.MISSING, TestResultsLevel.LOCATION);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("horizontal", "is required");
    }


    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3508 API Consumer tries to create a new test result for submitted/canceled with different property type - horizontal")
    @Test
    public void testResultsIntegerHorizontal() {

        testResultsSteps.postTestResultsFieldChange(vehicleSubmittedData.setVrm(VRM).build(), "horizontal", RandomStringUtils.randomNumeric(1, 9), ToTypeConvertor.INTEGER, TestResultsLevel.LOCATION);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("horizontal", "must be one of [inner, outer, null]");
    }


    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3509 - API Consumer tries to create a new test result for submitted/canceled with different format or allowed values - horizontal random")
    @Test
    public void testResultsRandomValueHorizontal() {

        testResultsSteps.postTestResultsFieldChange(vehicleSubmittedData.setVrm(VRM).build(), "horizontal", RandomStringUtils.randomAlphanumeric(1, 9), ToTypeConvertor.STRING, TestResultsLevel.LOCATION);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("horizontal", "must be one of [inner, outer, null]");
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3509 - API Consumer tries to create a new test result for submitted/canceled with different format or allowed values - horizontal empty")
    @Test
    public void testResultsHorizontalEmpty() {

        testResultsSteps.postTestResultsFieldChange(vehicleSubmittedData.setVrm(VRM).build(), "horizontal", "", ToTypeConvertor.STRING, TestResultsLevel.LOCATION);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("horizontal", "must be one of [inner, outer, null]");
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3505 - API Consumer tries to create a new test result for submitted/canceled with missing property - lateral")
    @Test
    public void testResultsMissingLateral() {

        testResultsSteps.postTestResultsFieldChange(vehicleSubmittedData.setVrm(VRM).build(), "lateral", ToTypeConvertor.MISSING, TestResultsLevel.LOCATION);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("lateral", "is required");
    }


    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3508 API Consumer tries to create a new test result for submitted/canceled with different property type - lateral")
    @Test
    public void testResultsIntegerLateral() {

        testResultsSteps.postTestResultsFieldChange(vehicleSubmittedData.setVrm(VRM).build(), "lateral", RandomStringUtils.randomNumeric(1, 9), ToTypeConvertor.INTEGER, TestResultsLevel.LOCATION);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("lateral", "must be one of [nearside, centre, offside, null]");
    }


    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3509 - API Consumer tries to create a new test result for submitted/canceled with different format or allowed values - lateral random")
    @Test
    public void testResultsRandomValueLateral() {

        testResultsSteps.postTestResultsFieldChange(vehicleSubmittedData.setVrm(VRM).build(), "lateral", RandomStringUtils.randomAlphanumeric(1, 9), ToTypeConvertor.STRING, TestResultsLevel.LOCATION);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("lateral", "must be one of [nearside, centre, offside, null]");
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3509 - API Consumer tries to create a new test result for submitted/canceled with different format or allowed values - lateral empty")
    @Test
    public void testResultsLateralEmpty() {

        testResultsSteps.postTestResultsFieldChange(vehicleSubmittedData.setVrm(VRM).build(), "lateral", "", ToTypeConvertor.STRING, TestResultsLevel.LOCATION);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("lateral", "must be one of [nearside, centre, offside, null]");
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3505 - API Consumer tries to create a new test result for submitted/canceled with missing property - longitudinal")
    @Test
    public void testResultsMissingLongitudinal() {

        testResultsSteps.postTestResultsFieldChange(vehicleSubmittedData.setVrm(VRM).build(), "longitudinal", ToTypeConvertor.MISSING, TestResultsLevel.LOCATION);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("longitudinal", "is required");
    }


    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3508 API Consumer tries to create a new test result for submitted/canceled with different property type - longitudinal")
    @Test
    public void testResultsIntegerLongitudinal() {

        testResultsSteps.postTestResultsFieldChange(vehicleSubmittedData.setVrm(VRM).build(), "longitudinal", RandomStringUtils.randomNumeric(1, 9), ToTypeConvertor.INTEGER, TestResultsLevel.LOCATION);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("longitudinal", "must be one of [front, rear, null]");
    }


    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3509 - API Consumer tries to create a new test result for submitted/canceled with different format or allowed values - longitudinal random")
    @Test
    public void testResultsRandomValueLongitudinal() {

        testResultsSteps.postTestResultsFieldChange(vehicleSubmittedData.setVrm(VRM).build(), "longitudinal", RandomStringUtils.randomAlphanumeric(1, 9), ToTypeConvertor.STRING, TestResultsLevel.LOCATION);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("longitudinal", "must be one of [front, rear, null]");
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3509 - API Consumer tries to create a new test result for submitted/canceled with different format or allowed values - longitudinal empty")
    @Test
    public void testResultsLongitudinalEmpty() {

        testResultsSteps.postTestResultsFieldChange(vehicleSubmittedData.setVrm(VRM).build(), "longitudinal", "", ToTypeConvertor.STRING, TestResultsLevel.LOCATION);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("longitudinal", "must be one of [front, rear, null]");
    }


    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3505 - API Consumer tries to create a new test result for submitted/canceled with missing property - rowNumber")
    @Test
    public void testResultsMissingRowNumber() {

        testResultsSteps.postTestResultsFieldChange(vehicleSubmittedData.setVrm(VRM).build(), "rowNumber", ToTypeConvertor.MISSING, TestResultsLevel.LOCATION);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("rowNumber", "is required");
    }


    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3508 API Consumer tries to create a new test result for submitted/canceled with different property type - rowNumber")
    @Test
    public void testResultsStringRowNumber() {

        testResultsSteps.postTestResultsFieldChange(vehicleSubmittedData.setVrm(VRM).build(), "rowNumber", RandomStringUtils.randomAlphanumeric(14), ToTypeConvertor.STRING, TestResultsLevel.LOCATION);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("rowNumber", "must be a number");
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3507 - API Consumer tries to create a new test result for submitted/canceled with value/characters exceeding max length - rowNumber")
    @Test
    public void testResultsLengthMaxRowNumber() {


        testResultsSteps.postTestResultsFieldChange(vehicleSubmittedData.setVrm(VRM).build(), "rowNumber", String.valueOf(new Random().nextInt(999) + 21), ToTypeConvertor.INTEGER, TestResultsLevel.LOCATION);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("rowNumber", "must be less than or equal to 20");
    }


    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3505 - API Consumer tries to create a new test result for submitted/canceled with missing property - seatNumber")
    @Test
    public void testResultsMissingSeatNumber() {

        testResultsSteps.postTestResultsFieldChange(vehicleSubmittedData.setVrm(VRM).build(), "seatNumber", ToTypeConvertor.MISSING, TestResultsLevel.LOCATION);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("seatNumber", "is required");
    }


    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3508 API Consumer tries to create a new test result for submitted/canceled with different property type - seatNumber")
    @Test
    public void testResultsStringSeatNumber() {

        testResultsSteps.postTestResultsFieldChange(vehicleSubmittedData.setVrm(VRM).build(), "seatNumber", RandomStringUtils.randomAlphanumeric(14), ToTypeConvertor.STRING, TestResultsLevel.LOCATION);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("seatNumber", "must be a number");
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3507 - API Consumer tries to create a new test result for submitted/canceled with value/characters exceeding max length - seatNumber")
    @Test
    public void testResultsLengthMaxSeatNumber() {


        testResultsSteps.postTestResultsFieldChange(vehicleSubmittedData.setVrm(VRM).build(), "seatNumber", String.valueOf(new Random().nextInt(999) + 7), ToTypeConvertor.INTEGER, TestResultsLevel.LOCATION);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("seatNumber", "must be less than or equal to 6");
    }


    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3505 - API Consumer tries to create a new test result for submitted/canceled with missing property - axleNumber")
    @Test
    public void testResultsMissingAxleNumber() {

        testResultsSteps.postTestResultsFieldChange(vehicleSubmittedData.setVrm(VRM).build(), "axleNumber", ToTypeConvertor.MISSING, TestResultsLevel.LOCATION);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("axleNumber", "is required");
    }


    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3508 API Consumer tries to create a new test result for submitted/canceled with different property type - axleNumber")
    @Test
    public void testResultsStringAxleNumber() {

        testResultsSteps.postTestResultsFieldChange(vehicleSubmittedData.setVrm(VRM).build(), "axleNumber", RandomStringUtils.randomAlphanumeric(14), ToTypeConvertor.STRING, TestResultsLevel.LOCATION);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("axleNumber", "must be a number");
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3507 - API Consumer tries to create a new test result for submitted/canceled with value/characters exceeding max length - axleNumber")
    @Test
    public void testResultsLengthMaxAxleNumber() {


        testResultsSteps.postTestResultsFieldChange(vehicleSubmittedData.setVrm(VRM).build(), "axleNumber", String.valueOf(new Random().nextInt(999) + 11), ToTypeConvertor.INTEGER, TestResultsLevel.LOCATION);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("axleNumber", "must be less than or equal to 10");
    }
}
