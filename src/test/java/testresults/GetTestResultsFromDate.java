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
public class GetTestResultsFromDate {
    @Steps
    TestResultsSteps testResultsSteps;

    private TestResults vehicleSubmittedData = TestResultsData.buildTestResultsSubmittedData();
    private TestResults vehicleCancelledData = TestResultsData.buildTestResultsCancelledData();


    @Title("")
    @Test
    public void testResultsSubmittedFromDateExisting() {

        testResultsSteps.getTestResultsFromDate(vehicleSubmittedData.getVin(), DataUtil.buildDate(vehicleSubmittedData.getTestTypes().get(0).getCreatedAt(), -1));
        testResultsSteps.statusCodeShouldBe(200);
        testResultsSteps.validateData(vehicleSubmittedData);
    }

    @Title("")
    @Test
    public void testResultsSubmittedFromDateNotExisting() {

        testResultsSteps.getTestResultsFromDate(vehicleSubmittedData.getVin(), DataUtil.buildDate(vehicleSubmittedData.getTestTypes().get(0).getCreatedAt(), 1));
        testResultsSteps.statusCodeShouldBe(404);
        testResultsSteps.validateData("No resources match the search criteria");
    }

    @Title("")
    @Test
    public void testResultsSubmittedFromDateInvalid() {

        testResultsSteps.getTestResultsFromDate(vehicleSubmittedData.getVin(), RandomStringUtils.randomAlphanumeric(4));
        testResultsSteps.statusCodeShouldBe(404);
        testResultsSteps.validateData("No resources match the search criteria");
    }

    @Title("")
    @Test
    public void testResultsSubmittedFromDateExistingWithStatusSubmitted() {

        testResultsSteps.getTestResultsFromDate(vehicleSubmittedData.getVin(), DataUtil.buildDate(vehicleSubmittedData.getTestTypes().get(0).getCreatedAt(), -1), TestResultsStatus.SUBMITTED);
        testResultsSteps.statusCodeShouldBe(200);
        testResultsSteps.validateData(vehicleSubmittedData);
    }

    @Title("")
    @Test
    public void testResultsSubmittedFromDateNotExistingWithStatusSubmitted() {

        testResultsSteps.getTestResultsFromDate(vehicleSubmittedData.getVin(), DataUtil.buildDate(vehicleSubmittedData.getTestTypes().get(0).getCreatedAt(), 1), TestResultsStatus.SUBMITTED);
        testResultsSteps.statusCodeShouldBe(404);
        testResultsSteps.validateData("No resources match the search criteria");
    }

    @Title("")
    @Test
    public void testResultsSubmittedFromDateInvalidWithStatusSubmitted() {

        testResultsSteps.getTestResultsFromDate(vehicleSubmittedData.getVin(), RandomStringUtils.randomAlphanumeric(4), TestResultsStatus.SUBMITTED);
        testResultsSteps.statusCodeShouldBe(404);
        testResultsSteps.validateData("No resources match the search criteria");
    }

    @Title("")
    @Test
    public void testResultsSubmittedFromDateExistingWithStatusCancelled() {

        testResultsSteps.getTestResultsFromDate(vehicleCancelledData.getVin(), DataUtil.buildDate(vehicleCancelledData.getTestTypes().get(0).getCreatedAt(), -1), TestResultsStatus.CANCELLED);
        testResultsSteps.statusCodeShouldBe(200);
        testResultsSteps.validateData(vehicleCancelledData);
    }

    @Title("")
    @Test
    public void testResultsSubmittedFromDateNotExistingWithStatusCancelled() {

        testResultsSteps.getTestResultsFromDate(vehicleCancelledData.getVin(), DataUtil.buildDate(vehicleCancelledData.getTestTypes().get(0).getCreatedAt(), 1), TestResultsStatus.CANCELLED);
        testResultsSteps.statusCodeShouldBe(404);
        testResultsSteps.validateData("No resources match the search criteria");
    }

    @Title("")
    @Test
    public void testResultsSubmittedFromDateInvalidWithStatusCancelled() {

        testResultsSteps.getTestResultsFromDate(vehicleCancelledData.getVin(), RandomStringUtils.randomAlphanumeric(4), TestResultsStatus.CANCELLED);
        testResultsSteps.statusCodeShouldBe(404);
        testResultsSteps.validateData("No resources match the search criteria");
    }

}
