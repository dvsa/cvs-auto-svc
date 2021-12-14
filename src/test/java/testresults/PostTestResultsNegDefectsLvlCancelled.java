package testresults;

import clients.util.ToTypeConvertor;
import clients.util.testresult.TestResultsLevel;
import data.GenericData;
import data.TestResultsData;
import model.testresults.TestResults;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import net.thucydides.core.annotations.WithTag;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import steps.TestResultsSteps;
import util.JsonPathAlteration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static util.DataUtil.generateRandomExcludingValues;


@RunWith(SerenityRunner.class)
public class PostTestResultsNegDefectsLvlCancelled {

    @Steps
    TestResultsSteps testResultsSteps;

    private TestResults.Builder vehicleCancelledData = TestResultsData.buildTestResultsCancelledData();
    private static final String VRM = "SL72XD";



    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3505 - API Consumer tries to create a new test result for submitted/canceled with missing property - imNumber")
    @Test
    public void testResultsMissingImNumber() {

        testResultsSteps.postTestResultsFieldChange(vehicleCancelledData.setVrm(VRM).build(), "imNumber", ToTypeConvertor.MISSING, TestResultsLevel.DEFECTS);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("imNumber", "is required");
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3506 - API Consumer tries to create a new test result for submitted/canceled with null value for not nullable - imNumber")
    @Test
    public void testResultsNullImNumber() {

        testResultsSteps.postTestResultsFieldChange(vehicleCancelledData.setVrm(VRM).build(), "imNumber", ToTypeConvertor.NULL, TestResultsLevel.DEFECTS);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("imNumber", "must be a number");
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3508 API Consumer tries to create a new test result for submitted/canceled with different property type - imNumber")
    @Test
    public void testResultsStringImNumber() {

        testResultsSteps.postTestResultsFieldChange(vehicleCancelledData.setVrm(VRM).build(), "imNumber", RandomStringUtils.randomAlphanumeric(9), ToTypeConvertor.STRING, TestResultsLevel.DEFECTS);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("imNumber", "must be a number");
    }


    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3505 - API Consumer tries to create a new test result for submitted/canceled with missing property - imDescription")
    @Test
    public void testResultsMissingImDescription() {

        testResultsSteps.postTestResultsFieldChange(vehicleCancelledData.setVrm(VRM).build(), "imDescription", ToTypeConvertor.MISSING, TestResultsLevel.DEFECTS);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("imDescription", "is required");
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3506 - API Consumer tries to create a new test result for submitted/canceled with null value for not nullable - imDescription")
    @Test
    public void testResultsNullImDescription() {

        testResultsSteps.postTestResultsFieldChange(vehicleCancelledData.setVrm(VRM).build(), "imDescription", ToTypeConvertor.NULL, TestResultsLevel.DEFECTS);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("imDescription", "must be a string");
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3508 API Consumer tries to create a new test result for submitted/canceled with different property type - imDescription")
    @Test
    public void testResultsIntegerImDescription() {

        testResultsSteps.postTestResultsFieldChange(vehicleCancelledData.setVrm(VRM).build(), "imDescription", RandomStringUtils.randomNumeric(9), ToTypeConvertor.INTEGER, TestResultsLevel.DEFECTS);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("imDescription", "must be a string");
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3505 - API Consumer tries to create a new test result for submitted/canceled with missing property - itemDescription")
    @Test
    public void testResultsMissingItemDescription() {

        testResultsSteps.postTestResultsFieldChange(vehicleCancelledData.setVrm(VRM).build(), "itemDescription", ToTypeConvertor.MISSING, TestResultsLevel.DEFECTS);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("itemDescription", "is required");
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3506 - API Consumer tries to create a new test result for submitted/canceled with null value for not nullable - itemDescription")
    @Test
    public void testResultsNullItemDescription() {

        testResultsSteps.postTestResultsFieldChange(vehicleCancelledData.setVrm(VRM).build(), "itemDescription", ToTypeConvertor.NULL, TestResultsLevel.DEFECTS);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("itemDescription", "must be a string");
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3508 API Consumer tries to create a new test result for submitted/canceled with different property type - itemDescription")
    @Test
    public void testResultsIntegerItemDescription() {

        testResultsSteps.postTestResultsFieldChange(vehicleCancelledData.setVrm(VRM).build(), "itemDescription", RandomStringUtils.randomNumeric(9), ToTypeConvertor.INTEGER, TestResultsLevel.DEFECTS);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("itemDescription", "must be a string");
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3505 - API Consumer tries to create a new test result for submitted/canceled with missing property - deficiencyRef")
    @Test
    public void testResultsMissingDeficiencyRef() {

        testResultsSteps.postTestResultsFieldChange(vehicleCancelledData.setVrm(VRM).build(), "deficiencyRef", ToTypeConvertor.MISSING, TestResultsLevel.DEFECTS);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("deficiencyRef", "is required");
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3506 - API Consumer tries to create a new test result for submitted/canceled with null value for not nullable - deficiencyRef")
    @Test
    public void testResultsNullDeficiencyRef() {

        testResultsSteps.postTestResultsFieldChange(vehicleCancelledData.setVrm(VRM).build(), "deficiencyRef", ToTypeConvertor.NULL, TestResultsLevel.DEFECTS);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("deficiencyRef", "must be a string");
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3508 API Consumer tries to create a new test result for submitted/canceled with different property type - deficiencyRef")
    @Test
    public void testResultsIntegerDeficiencyRef() {

        testResultsSteps.postTestResultsFieldChange(vehicleCancelledData.setVrm(VRM).build(), "deficiencyRef", RandomStringUtils.randomNumeric(9), ToTypeConvertor.INTEGER, TestResultsLevel.DEFECTS);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("deficiencyRef", "must be a string");
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3505 - API Consumer tries to create a new test result for submitted/canceled with missing property - deficiencyId")
    @Test
    public void testResultsMissingDeficiencyId() {

        testResultsSteps.postTestResultsFieldChange(vehicleCancelledData.setVrm(VRM).build(), "deficiencyId", ToTypeConvertor.MISSING, TestResultsLevel.DEFECTS);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("deficiencyId", "is required");
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3508 API Consumer tries to create a new test result for submitted/canceled with different property type - deficiencyId")
    @Test
    public void testResultsIntegerDeficiencyId() {

        testResultsSteps.postTestResultsFieldChange(vehicleCancelledData.setVrm(VRM).build(), "deficiencyId", RandomStringUtils.randomNumeric(9), ToTypeConvertor.INTEGER, TestResultsLevel.DEFECTS);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("deficiencyId", "must be a string");
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3507 - API Consumer tries to create a new test result for submitted/canceled with value/characters exceeding max length - deficiencyId")
    @Test
    public void testResultsRandomAlphabeticStringDeficiencyId() {

        String propertyValue = RandomStringUtils.randomAlphabetic(2).toLowerCase();

        testResultsSteps.postTestResultsFieldChange(vehicleCancelledData.setVrm(VRM).build(), "deficiencyId", propertyValue, ToTypeConvertor.STRING, TestResultsLevel.DEFECTS);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("deficiencyId", "length must be less than or equal to 1 characters long");
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3509 - API Consumer tries to create a new test result for submitted/canceled with different format or allowed values - deficiencyId")
    @Test
    public void testResultsRandomAlphabeticUpperCaseStringDeficiencyId() {

        String propertyValue = RandomStringUtils.randomAlphabetic(1).toUpperCase();

        testResultsSteps.postTestResultsFieldChange(vehicleCancelledData.setVrm(VRM).build(), "deficiencyId", propertyValue, ToTypeConvertor.STRING, TestResultsLevel.DEFECTS);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("deficiencyId", "with value \"" + propertyValue + "\" fails to match the required pattern: /^[a-z]+$/");
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3509 - API Consumer tries to create a new test result for submitted/canceled with different format or allowed values - deficiencyId random")
    @Test
    public void testResultsRandomAlphanumericStringDeficiencyId() {

        String propertyValue = RandomStringUtils.randomAlphanumeric(1).toLowerCase() + RandomStringUtils.randomNumeric(1);

        testResultsSteps.postTestResultsFieldChange(vehicleCancelledData.setVrm(VRM).build(), "deficiencyId", propertyValue, ToTypeConvertor.STRING, TestResultsLevel.DEFECTS);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("deficiencyId", "with value \"" + propertyValue + "\" fails to match the required pattern: /^[a-z]+$/");
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3509 - API Consumer tries to create a new test result for submitted/canceled with different format or allowed values - deficiencyId empty")
    @Test
    public void testResultsEmptyDeficiencyId() {

        testResultsSteps.postTestResultsFieldChange(vehicleCancelledData.setVrm(VRM).build(), "deficiencyId", "", ToTypeConvertor.STRING, TestResultsLevel.DEFECTS);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("deficiencyId", "is not allowed to be empty");
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3505 - API Consumer tries to create a new test result for submitted/canceled with missing property - deficiencySubId")
    @Test
    public void testResultsMissingDeficiencySubId() {

        testResultsSteps.postTestResultsFieldChange(vehicleCancelledData.setVrm(VRM).build(), "deficiencySubId", ToTypeConvertor.MISSING, TestResultsLevel.DEFECTS);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("deficiencySubId", "is required");
    }


    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3508 API Consumer tries to create a new test result for submitted/canceled with different property type - deficiencySubId")
    @Test
    public void testResultsIntegerDeficiencySubId() {

        testResultsSteps.postTestResultsFieldChange(vehicleCancelledData.setVrm(VRM).build(), "deficiencySubId", RandomStringUtils.randomNumeric(9), ToTypeConvertor.INTEGER, TestResultsLevel.DEFECTS);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("deficiencySubId", "must be a string");
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3509 - API Consumer tries to create a new test result for submitted/canceled with different format or allowed values - deficiencySubId random")
    @Test
    public void testResultsRandomAlphabeticStringDeficiencySubId() {

        String testResultRecord = GenericData.readJsonValueFromFile("test-results_cancelled.json", "$");

        String propertyValue = generateRandomExcludingValues(2, "m", "d", "c", "l", "x", "v", "i").toLowerCase();

        String randomVrm = GenericData.generateRandomVrm();

        JsonPathAlteration alterationVrm = new JsonPathAlteration("$.vrm", randomVrm, "", "REPLACE");
        JsonPathAlteration alterationDeficiencySubId = new JsonPathAlteration("$.testTypes[0].defects[0].deficiencySubId", propertyValue, "", "REPLACE");

        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(
                alterationVrm,
                alterationDeficiencySubId));

        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, alterations);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("deficiencySubId", "with value \"" + propertyValue + "\" fails to match the required pattern: /^[mdclxvi]+$/");
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3509 - API Consumer tries to create a new test result for submitted/canceled with different format or allowed values - deficiencySubId random")
    @Test
    public void testResultsEmptyDeficiencySubId() {

        testResultsSteps.postTestResultsFieldChange(vehicleCancelledData.setVrm(VRM).build(), "deficiencySubId", "", ToTypeConvertor.STRING, TestResultsLevel.DEFECTS);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("deficiencySubId", "is not allowed to be empty");
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3509 - API Consumer tries to create a new test result for submitted/canceled with different format or allowed values - invalid and lowercase")
    @Test
    public void testResultsRandomInvalidLowerCaseDeficiencySubId() {

        String testResultRecord = GenericData.readJsonValueFromFile("test-results_cancelled.json", "$");

        String propertyValue = generateRandomExcludingValues(1, "m", "d", "c", "l", "x", "v", "i").toLowerCase();

        String randomVrm = GenericData.generateRandomVrm();

        JsonPathAlteration alterationVrm = new JsonPathAlteration("$.vrm", randomVrm, "", "REPLACE");
        JsonPathAlteration alterationDeficiencySubId = new JsonPathAlteration("$.testTypes[0].defects[0].deficiencySubId", propertyValue, "", "REPLACE");

        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(
                alterationVrm,
                alterationDeficiencySubId));

        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, alterations);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("deficiencySubId", "with value \"" + propertyValue + "\" fails to match the required pattern: /^[mdclxvi]+$/");
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3509 - API Consumer tries to create a new test result for submitted/canceled with different format or allowed values - invalid and uppercase")
    @Test
    public void testResultsRandomInvalidUpperCaseDeficiencySubId() {

        String propertyValue = generateRandomExcludingValues(1,"m","d","c","l","x","v","i").toUpperCase();

        testResultsSteps.postTestResultsFieldChange(vehicleCancelledData.setVrm(VRM).build(), "deficiencySubId", propertyValue, ToTypeConvertor.STRING, TestResultsLevel.DEFECTS);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("deficiencySubId", "with value \"" + propertyValue + "\" fails to match the required pattern: /^[mdclxvi]+$/");
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3509 - API Consumer tries to create a new test result for submitted/canceled with different format or allowed values - valid but uppercase m")
    @Test
    public void testResultsValidButUpperCaseDeficiencySubIdValueOne() {

        String propertyValue = "M";

        testResultsSteps.postTestResultsFieldChange(vehicleCancelledData.setVrm(VRM).build(), "deficiencySubId", propertyValue, ToTypeConvertor.STRING, TestResultsLevel.DEFECTS);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("deficiencySubId", "with value \"" + propertyValue + "\" fails to match the required pattern: /^[mdclxvi]+$/");
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3509 - API Consumer tries to create a new test result for submitted/canceled with different format or allowed values - valid but uppercase d")
    @Test
    public void testResultsValidButUpperCaseDeficiencySubIdValueTwo() {

        String propertyValue = "D";

        testResultsSteps.postTestResultsFieldChange(vehicleCancelledData.setVrm(VRM).build(), "deficiencySubId", propertyValue, ToTypeConvertor.STRING, TestResultsLevel.DEFECTS);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("deficiencySubId", "with value \"" + propertyValue + "\" fails to match the required pattern: /^[mdclxvi]+$/");
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3509 - API Consumer tries to create a new test result for submitted/canceled with different format or allowed values - valid but uppercase c")
    @Test
    public void testResultsValidButUpperCaseDeficiencySubIdValueThree() {

        String propertyValue = "C";

        testResultsSteps.postTestResultsFieldChange(vehicleCancelledData.setVrm(VRM).build(), "deficiencySubId", propertyValue, ToTypeConvertor.STRING, TestResultsLevel.DEFECTS);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("deficiencySubId", "with value \"" + propertyValue + "\" fails to match the required pattern: /^[mdclxvi]+$/");
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3509 - API Consumer tries to create a new test result for submitted/canceled with different format or allowed values - valid but uppercase l")
    @Test
    public void testResultsValidButUpperCaseDeficiencySubIdValueFour() {

        String propertyValue = "L";

        testResultsSteps.postTestResultsFieldChange(vehicleCancelledData.setVrm(VRM).build(), "deficiencySubId", propertyValue, ToTypeConvertor.STRING, TestResultsLevel.DEFECTS);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("deficiencySubId", "with value \"" + propertyValue + "\" fails to match the required pattern: /^[mdclxvi]+$/");
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3509 - API Consumer tries to create a new test result for submitted/canceled with different format or allowed values - valid but uppercase x")
    @Test
    public void testResultsValidButUpperCaseDeficiencySubIdValueFive() {

        String propertyValue = "X";

        testResultsSteps.postTestResultsFieldChange(vehicleCancelledData.setVrm(VRM).build(), "deficiencySubId", propertyValue, ToTypeConvertor.STRING, TestResultsLevel.DEFECTS);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("deficiencySubId", "with value \"" + propertyValue + "\" fails to match the required pattern: /^[mdclxvi]+$/");
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3509 - API Consumer tries to create a new test result for submitted/canceled with different format or allowed values - valid but uppercase v")
    @Test
    public void testResultsValidButUpperCaseDeficiencySubIdValueSix() {

        String propertyValue = "V";

        testResultsSteps.postTestResultsFieldChange(vehicleCancelledData.setVrm(VRM).build(), "deficiencySubId", propertyValue, ToTypeConvertor.STRING, TestResultsLevel.DEFECTS);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("deficiencySubId", "with value \"" + propertyValue + "\" fails to match the required pattern: /^[mdclxvi]+$/");
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3509 - API Consumer tries to create a new test result for submitted/canceled with different format or allowed values - valid but uppercase i")
    @Test
    public void testResultsValidButUpperCaseDeficiencySubIdValueSeven() {

        String propertyValue = "I";

        testResultsSteps.postTestResultsFieldChange(vehicleCancelledData.setVrm(VRM).build(), "deficiencySubId", propertyValue, ToTypeConvertor.STRING, TestResultsLevel.DEFECTS);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("deficiencySubId", "with value \"" + propertyValue + "\" fails to match the required pattern: /^[mdclxvi]+$/");
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3505 - API Consumer tries to create a new test result for submitted/canceled with missing property - deficiencyCategory")
    @Test
    public void testResultsMissingDeficiencyCategory() {

        testResultsSteps.postTestResultsFieldChange(vehicleCancelledData.setVrm(VRM).build(), "deficiencyCategory", ToTypeConvertor.MISSING, TestResultsLevel.DEFECTS);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("deficiencyCategory", "is required");
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3506 - API Consumer tries to create a new test result for submitted/canceled with null value for not nullable - deficiencyCategory")
    @Test
    public void testResultsNullDeficiencyCategory() {

        testResultsSteps.postTestResultsFieldChange(vehicleCancelledData.setVrm(VRM).build(), "deficiencyCategory", ToTypeConvertor.NULL, TestResultsLevel.DEFECTS);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("deficiencyCategory", "must be one of [advisory, dangerous, major, minor]");
    }


    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3508 API Consumer tries to create a new test result for submitted/canceled with different property type - deficiencyCategory")
    @Test
    public void testResultsIntegerDeficiencyCategory() {

        testResultsSteps.postTestResultsFieldChange(vehicleCancelledData.setVrm(VRM).build(), "deficiencyCategory", RandomStringUtils.randomNumeric(1, 9), ToTypeConvertor.INTEGER, TestResultsLevel.DEFECTS);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("deficiencyCategory", "must be one of [advisory, dangerous, major, minor]");
    }


    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3509 - API Consumer tries to create a new test result for submitted/canceled with different format or allowed values - deficiencyCategory random")
    @Test
    public void testResultsValueDeficiencyCategory() {

        testResultsSteps.postTestResultsFieldChange(vehicleCancelledData.setVrm(VRM).build(), "deficiencyCategory", RandomStringUtils.randomAlphanumeric(1, 9), ToTypeConvertor.STRING, TestResultsLevel.DEFECTS);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("deficiencyCategory", "must be one of [advisory, dangerous, major, minor]");
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3509 - API Consumer tries to create a new test result for submitted/canceled with different format or allowed values - deficiencyCategory empty")
    @Test
    public void testResultsValueDeficiencyCategoryEmpty() {

        testResultsSteps.postTestResultsFieldChange(vehicleCancelledData.setVrm(VRM).build(), "deficiencyCategory", "", ToTypeConvertor.STRING, TestResultsLevel.DEFECTS);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("deficiencyCategory", "must be one of [advisory, dangerous, major, minor]");
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3505 - API Consumer tries to create a new test result for submitted/canceled with missing property - deficiencyText")
    @Test
    public void testResultsMissingDeficiencyText() {

        testResultsSteps.postTestResultsFieldChange(vehicleCancelledData.setVrm(VRM).build(), "deficiencyText", ToTypeConvertor.MISSING, TestResultsLevel.DEFECTS);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("deficiencyText", "is required");
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3506 - API Consumer tries to create a new test result for submitted/canceled with null value for not nullable - deficiencyText with setDeficiencyCategory dangerous")
    @Test
    public void testResultsNullDangerousDeficiencyText() {

        vehicleCancelledData.setVrm(VRM).build().getTestTypes().get(0).getDefects().get(0).setDeficiencyCategory("dangerous");

        testResultsSteps.postTestResultsFieldChange(vehicleCancelledData.setVrm(VRM).build(), "deficiencyText", ToTypeConvertor.NULL, TestResultsLevel.DEFECTS);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validateData("/deficiencyText are null for a defect with deficiency category other than advisory");

    }


    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3506 - API Consumer tries to create a new test result for submitted/canceled with null value for not nullable - deficiencyText with setDeficiencyCategory major")
    @Test
    public void testResultsNullMajorDeficiencyText() {

        vehicleCancelledData.setVrm(VRM).build().getTestTypes().get(0).getDefects().get(0).setDeficiencyCategory("major");


        testResultsSteps.postTestResultsFieldChange(vehicleCancelledData.setVrm(VRM).build(), "deficiencyText", ToTypeConvertor.NULL, TestResultsLevel.DEFECTS);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validateData("/deficiencyText are null for a defect with deficiency category other than advisory");

    }


    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3506 - API Consumer tries to create a new test result for submitted/canceled with null value for not nullable - deficiencyText with setDeficiencyCategory minor")
    @Test
    public void testResultsNullMinorDeficiencyText() {

        vehicleCancelledData.setVrm(VRM).build().getTestTypes().get(0).getDefects().get(0).setDeficiencyCategory("minor");


        testResultsSteps.postTestResultsFieldChange(vehicleCancelledData.setVrm(VRM).build(), "deficiencyText", ToTypeConvertor.NULL, TestResultsLevel.DEFECTS);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validateData("/deficiencyText are null for a defect with deficiency category other than advisory");

    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3506 - API Consumer tries to create a new test result for submitted/canceled with null value for not nullable - deficiencyText with setDeficiencyCategory prs")
    @Test
    public void testResultsNullPrsDeficiencyText() {

        vehicleCancelledData.setVrm(VRM).build().getTestTypes().get(0).getDefects().get(0).setDeficiencyCategory("prs");


        testResultsSteps.postTestResultsFieldChange(vehicleCancelledData.setVrm(VRM).build(), "deficiencyText", ToTypeConvertor.NULL, TestResultsLevel.DEFECTS);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validateData("/deficiencyText are null for a defect with deficiency category other than advisory");

    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3508 API Consumer tries to create a new test result for submitted/canceled with different property type - deficiencyText")
    @Test
    public void testResultsIntegerDeficiencyText() {

        testResultsSteps.postTestResultsFieldChange(vehicleCancelledData.setVrm(VRM).build(), "deficiencyText", RandomStringUtils.randomNumeric(9), ToTypeConvertor.INTEGER, TestResultsLevel.DEFECTS);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("deficiencyText", "must be a string");
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3505 - API Consumer tries to create a new test result for submitted/canceled with missing property - stdForProhibition")
    @Test
    public void testResultsMissingStdForProhibition() {

        testResultsSteps.postTestResultsFieldChange(vehicleCancelledData.setVrm(VRM).build(), "stdForProhibition", ToTypeConvertor.MISSING, TestResultsLevel.DEFECTS);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("stdForProhibition", "is required");
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3508 API Consumer tries to create a new test result for submitted/canceled with different property type - stdForProhibition")
    @Test
    public void testResultsRandomStringStdForProhibition() {

        testResultsSteps.postTestResultsFieldChange(vehicleCancelledData.setVrm(VRM).build(), "stdForProhibition", RandomStringUtils.randomAlphanumeric(9), ToTypeConvertor.STRING, TestResultsLevel.DEFECTS);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("stdForProhibition", "must be a boolean");
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3508 API Consumer tries to create a new test result for submitted/canceled with different property type - stdForProhibition")
    @Test
    public void testResultsEmptyStdForProhibition() {

        testResultsSteps.postTestResultsFieldChange(vehicleCancelledData.setVrm(VRM).build(), "stdForProhibition", "", ToTypeConvertor.STRING, TestResultsLevel.DEFECTS);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("stdForProhibition", "must be a boolean");
    }

    @Title("CVSB-3205 - API Consumer tries to create a new test result for submitted/canceled with missing property - prohibitionIssued")
    @Test
    public void testResultsMissingProhibitionIssued() {

        testResultsSteps.postTestResultsFieldChange(vehicleCancelledData.setVrm(VRM).build(), "prohibitionIssued", ToTypeConvertor.MISSING, TestResultsLevel.DEFECTS);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("prohibitionIssued", "is required");
    }

    @Title("CVSB-3205 - API Consumer tries to create a new test result for submitted/canceled with different property type - prohibitionIssued")
    @Test
    public void testResultsRandomStringProhibitionIssued() {

        testResultsSteps.postTestResultsFieldChange(vehicleCancelledData.setVrm(VRM).build(), "prohibitionIssued", RandomStringUtils.randomAlphanumeric(9), ToTypeConvertor.STRING, TestResultsLevel.DEFECTS);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("prohibitionIssued", "must be a boolean");
    }

    @Title("CVSB-3205 - API Consumer tries to create a new test result for submitted/canceled with different property type - prohibitionIssued")
    @Test
    public void testResultsEmptyProhibitionIssued() {

        testResultsSteps.postTestResultsFieldChange(vehicleCancelledData.setVrm(VRM).build(), "prohibitionIssued", "", ToTypeConvertor.STRING, TestResultsLevel.DEFECTS);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("prohibitionIssued", "must be a boolean");
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3505 - API Consumer tries to create a new test result for submitted/canceled with missing property - prs")
    @Test
    public void testResultsMissingSeatbeltInstallationCheckDate() {

        testResultsSteps.postTestResultsFieldChange(vehicleCancelledData.setVrm(VRM).build(), "prs", ToTypeConvertor.MISSING, TestResultsLevel.DEFECTS);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("prs", "is required");
    }


    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3508 API Consumer tries to create a new test result for submitted/canceled with different property type - prs")
    @Test
    public void testResultsRandomStringSeatbeltInstallationCheckDate() {

        testResultsSteps.postTestResultsFieldChange(vehicleCancelledData.setVrm(VRM).build(), "prs", RandomStringUtils.randomAlphanumeric(9), ToTypeConvertor.STRING, TestResultsLevel.DEFECTS);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("prs", "must be a boolean");
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3508 API Consumer tries to create a new test result for submitted/canceled with different property type - prs")
    @Test
    public void testResultsEmptySeatbeltInstallationCheckDate() {

        testResultsSteps.postTestResultsFieldChange(vehicleCancelledData.setVrm(VRM).build(), "prs", "", ToTypeConvertor.STRING, TestResultsLevel.DEFECTS);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("prs", "must be a boolean");
    }

    @Ignore ("Logic is implemented in the frontend - not possible to submit without 'additionalInformation' - defect CVSB-9010")
    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3505 - API Consumer tries to create a new test result for submitted/canceled with missing property - additionalInformation")
    public void testResultsAdditionalInformationMissing() {

        testResultsSteps.postTestResultsFieldChange(vehicleCancelledData.setVrm(VRM).build(), "additionalInformation", ToTypeConvertor.MISSING, TestResultsLevel.DEFECTS);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("additionalInformation", "is required");
    }
}
