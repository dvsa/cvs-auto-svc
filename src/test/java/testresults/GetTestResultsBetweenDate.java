package testresults;

import data.TestResultsData;
import model.testresults.TestResults;
import model.testresults.TestResultsStatus;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.core.annotations.WithTags;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import steps.TestResultsSteps;
import util.DataUtil;

@WithTags(
        {
                @WithTag(type = "TestResults", name = "All"),
                @WithTag(type = "TestResults", name = "Positive"),
                @WithTag(type = "Suite", name = "Positive"),

        }
)

@RunWith(SerenityRunner.class)
public class GetTestResultsBetweenDate {

    @Steps
    TestResultsSteps testResultsSteps;

    private TestResults vehicleSubmittedData = TestResultsData.buildTestResultsSubmittedData();
    private TestResults vehicleCancelledData = TestResultsData.buildTestResultsCancelledData();


    @Title("CVSB-416 - CVSB-949 / CVSB- - Between Date data found and status default")
    @Test
    public void testResultsBetweenDateExisting() {

        testResultsSteps.getTestResultsBetweenDate(vehicleSubmittedData.getVin(), DataUtil.buildDate(vehicleSubmittedData.getTestTypes().get(0).getCreatedAt(), -1), DataUtil.buildDate(vehicleSubmittedData.getTestTypes().get(0).getCreatedAt(), 1));
        testResultsSteps.statusCodeShouldBe(200);
        testResultsSteps.validateData(vehicleSubmittedData);
    }

    @Title("CVSB-416 - CVSB-949 / CVSB- - Between Date data not found (date higher than existing data) and status default")
    @Test
    public void testResultsBetweenDateNotExistingHigher() {

        testResultsSteps.getTestResultsBetweenDate(vehicleSubmittedData.getVin(), DataUtil.buildDate(vehicleSubmittedData.getTestTypes().get(0).getCreatedAt(), 1), DataUtil.buildDate(vehicleSubmittedData.getTestTypes().get(0).getCreatedAt(), 2));
        testResultsSteps.statusCodeShouldBe(404);
        testResultsSteps.validateData("No resources match the search criteria");
    }

    @Title("CVSB-416 - CVSB-949 / CVSB- - Between Date data not found (date lower than existing data) and status default")
    @Test
    public void testResultsBetweenDateNotExistingLower() {

        testResultsSteps.getTestResultsBetweenDate(vehicleSubmittedData.getVin(), DataUtil.buildDate(vehicleSubmittedData.getTestTypes().get(0).getCreatedAt(), -2), DataUtil.buildDate(vehicleSubmittedData.getTestTypes().get(0).getCreatedAt(), -1));
        testResultsSteps.statusCodeShouldBe(404);
        testResultsSteps.validateData("No resources match the search criteria");
    }


    @Title("CVSB-416 - CVSB-949 / CVSB- - Between Date invalid (fromDateTime, toDateTime invalid values) and status default")
    @Test
    public void testResultsBetweenDateInvalid() {

        testResultsSteps.getTestResultsBetweenDate(vehicleSubmittedData.getVin(), RandomStringUtils.randomAlphanumeric(4), RandomStringUtils.randomAlphanumeric(4));
        testResultsSteps.statusCodeShouldBe(404);
        testResultsSteps.validateData("No resources match the search criteria");
    }


    @Title("CVSB-416 - CVSB-949 / CVSB- - Between Date data found and status submitted")
    @Test
    public void testResultsBetweenDateExistingWithStatusSubmitted() {

        testResultsSteps.getTestResultsBetweenDate(vehicleSubmittedData.getVin(), DataUtil.buildDate(vehicleSubmittedData.getTestTypes().get(0).getCreatedAt(), -1), DataUtil.buildDate(vehicleSubmittedData.getTestTypes().get(0).getCreatedAt(), 1), TestResultsStatus.SUBMITTED);
        testResultsSteps.statusCodeShouldBe(200);
        testResultsSteps.validateData(vehicleSubmittedData);
    }

    @Title("CVSB-416 - CVSB-949 / CVSB- - Between Date data not found (date higher than existing data) and status submitted")
    @Test
    public void testResultsBetweenDateNotExistingWithStatusSubmittedHigher() {

        testResultsSteps.getTestResultsBetweenDate(vehicleSubmittedData.getVin(), DataUtil.buildDate(vehicleSubmittedData.getTestTypes().get(0).getCreatedAt(), 1), DataUtil.buildDate(vehicleSubmittedData.getTestTypes().get(0).getCreatedAt(), 2), TestResultsStatus.SUBMITTED);
        testResultsSteps.statusCodeShouldBe(404);
        testResultsSteps.validateData("No resources match the search criteria");
    }

    @Title("CVSB-416 - CVSB-949 / CVSB- - Between Date data not found (date lower than existing data) and status submitted")
    @Test
    public void testResultsBetweenDateNotExistingWithStatusSubmittedLower() {

        testResultsSteps.getTestResultsBetweenDate(vehicleSubmittedData.getVin(), DataUtil.buildDate(vehicleSubmittedData.getTestTypes().get(0).getCreatedAt(), -2), DataUtil.buildDate(vehicleSubmittedData.getTestTypes().get(0).getCreatedAt(), -1), TestResultsStatus.SUBMITTED);
        testResultsSteps.statusCodeShouldBe(404);
        testResultsSteps.validateData("No resources match the search criteria");
    }

    @Title("CVSB-416 - CVSB-949 / CVSB- - Between Date invalid (fromDateTime, toDateTime invalid values) and status submitted")
    @Test
    public void testResultsBetweenDateInvalidWithStatusSubmitted() {

        testResultsSteps.getTestResultsBetweenDate(vehicleSubmittedData.getVin(), RandomStringUtils.randomAlphanumeric(4), RandomStringUtils.randomAlphanumeric(4), TestResultsStatus.SUBMITTED);
        testResultsSteps.statusCodeShouldBe(404);
        testResultsSteps.validateData("No resources match the search criteria");
    }

    @Title("CVSB-416 - CVSB-949 / CVSB- - Between Date data found and status cancelled")
    @Test
    public void testResultsBetweenDateExistingWithStatusCancelled() {

        testResultsSteps.getTestResultsBetweenDate(vehicleCancelledData.getVin(), DataUtil.buildDate(vehicleCancelledData.getTestTypes().get(0).getCreatedAt(), -1), DataUtil.buildDate(vehicleCancelledData.getTestTypes().get(0).getCreatedAt(), 1), TestResultsStatus.CANCELLED);
        testResultsSteps.statusCodeShouldBe(200);
        testResultsSteps.validateData(vehicleCancelledData);
    }

    @Title("CVSB-416 - CVSB-949 / CVSB- - Between Date data not found (date higher than existing data) and status canceled")
    @Test
    public void testResultsBetweenDateNotExistingWithStatusCancelledHigher() {

        testResultsSteps.getTestResultsBetweenDate(vehicleCancelledData.getVin(), DataUtil.buildDate(vehicleCancelledData.getTestTypes().get(0).getCreatedAt(), 1), DataUtil.buildDate(vehicleCancelledData.getTestTypes().get(0).getCreatedAt(), 2), TestResultsStatus.CANCELLED);
        testResultsSteps.statusCodeShouldBe(404);
        testResultsSteps.validateData("No resources match the search criteria");
    }

    @Title("CVSB-416 - CVSB-949 / CVSB- - Between Date data not found (date lower than existing data) and status canceled")
    @Test
    public void testResultsBetweenDateNotExistingWithStatusCancelledLower() {

        testResultsSteps.getTestResultsBetweenDate(vehicleCancelledData.getVin(), DataUtil.buildDate(vehicleCancelledData.getTestTypes().get(0).getCreatedAt(), -2), DataUtil.buildDate(vehicleCancelledData.getTestTypes().get(0).getCreatedAt(), -1), TestResultsStatus.CANCELLED);
        testResultsSteps.statusCodeShouldBe(404);
        testResultsSteps.validateData("No resources match the search criteria");
    }

    @Title("CVSB-416 - CVSB-949 / CVSB- - Between Date invalid (fromDateTime, toDateTime invalid values) and status canceled")
    @Test
    public void testResultsBetweenDateInvalidWithStatusCancelled() {

        testResultsSteps.getTestResultsBetweenDate(vehicleCancelledData.getVin(), RandomStringUtils.randomAlphanumeric(4), RandomStringUtils.randomAlphanumeric(4), TestResultsStatus.CANCELLED);
        testResultsSteps.statusCodeShouldBe(404);
        testResultsSteps.validateData("No resources match the search criteria");
    }

}
