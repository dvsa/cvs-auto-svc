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
                @WithTag(type = "Service", name = "One"),

        }
)

@RunWith(SerenityRunner.class)
public class GetTestResultsToDate {
    @Steps
    TestResultsSteps testResultsSteps;

    private TestResults vehicleSubmittedData = TestResultsData.buildTestResultsSubmittedData();
    private TestResults vehicleCancelledData = TestResultsData.buildTestResultsCancelledData();


    @Title("CVSB-416 - CVSB-949 / CVSB-2455 - To Date data found and status default")
    @Test
    public void testResultsSubmittedToDateExisting() {

        testResultsSteps.getTestResultsToDate(vehicleSubmittedData.getVin(), DataUtil.buildDate(vehicleSubmittedData.getTestTypes().get(0).getCreatedAt(), 1));
        testResultsSteps.statusCodeShouldBe(200);
        testResultsSteps.validateData(vehicleSubmittedData);
    }

    @Title("CVSB-416 - CVSB-949 / CVSB-2456 - To Date data not found and status default")
    @Test
    public void testResultsSubmittedToDateNotExisting() {

        testResultsSteps.getTestResultsToDate(vehicleSubmittedData.getVin(), DataUtil.buildDate(vehicleSubmittedData.getTestTypes().get(0).getCreatedAt(), -1));
        testResultsSteps.statusCodeShouldBe(404);
        testResultsSteps.validateData("No resources match the search criteria");
    }

    @Title("CVSB-416 - CVSB-949 / CVSB-2457 - To Date data invalid and status default")
    @Test
    public void testResultsSubmittedToDateInvalid() {

        testResultsSteps.getTestResultsToDate(vehicleSubmittedData.getVin(), RandomStringUtils.randomAlphanumeric(4));
        testResultsSteps.statusCodeShouldBe(404);
        testResultsSteps.validateData("No resources match the search criteria");
    }

    @Title("CVSB-416 - CVSB-949 / CVSB-2458 - To Date data found and status submitted")
    @Test
    public void testResultsSubmittedToDateExistingWithStatusSubmitted() {

        testResultsSteps.getTestResultsToDate(vehicleSubmittedData.getVin(), DataUtil.buildDate(vehicleSubmittedData.getTestTypes().get(0).getCreatedAt(), 1), TestResultsStatus.SUBMITTED);
        testResultsSteps.statusCodeShouldBe(200);
        testResultsSteps.validateData(vehicleSubmittedData);
    }

    @Title("CVSB-416 - CVSB-949 / CVSB-2459 - To Date data not found and status submitted")
    @Test
    public void testResultsSubmittedToDateNotExistingWithStatusSubmitted() {

        testResultsSteps.getTestResultsToDate(vehicleSubmittedData.getVin(), DataUtil.buildDate(vehicleSubmittedData.getTestTypes().get(0).getCreatedAt(), -1), TestResultsStatus.SUBMITTED);
        testResultsSteps.statusCodeShouldBe(404);
        testResultsSteps.validateData("No resources match the search criteria");
    }

    @Title("CVSB-416 - CVSB-949 / CVSB-2460 - To Date data invalid and status submitted")
    @Test
    public void testResultsSubmittedToDateInvalidWithStatusSubmitted() {

        testResultsSteps.getTestResultsToDate(vehicleSubmittedData.getVin(), RandomStringUtils.randomAlphanumeric(4), TestResultsStatus.SUBMITTED);
        testResultsSteps.statusCodeShouldBe(404);
        testResultsSteps.validateData("No resources match the search criteria");
    }

    @Title("CVSB-416 - CVSB-949 / CVSB-2461 - To Date data found and status canceled")
    @Test
    public void testResultsSubmittedToDateExistingWithStatusCancelled() {

        testResultsSteps.getTestResultsToDate(vehicleCancelledData.getVin(), DataUtil.buildDate(vehicleCancelledData.getTestTypes().get(0).getCreatedAt(), 1), TestResultsStatus.CANCELLED);
        testResultsSteps.statusCodeShouldBe(200);
        testResultsSteps.validateData(vehicleCancelledData);
    }

    @Title("CVSB-416 - CVSB-949 / CVSB-2462 - To Date data not found and status canceled")
    @Test
    public void testResultsSubmittedToDateNotExistingWithStatusCancelled() {

        testResultsSteps.getTestResultsToDate(vehicleCancelledData.getVin(), DataUtil.buildDate(vehicleCancelledData.getTestTypes().get(0).getCreatedAt(), -1), TestResultsStatus.CANCELLED);
        testResultsSteps.statusCodeShouldBe(404);
        testResultsSteps.validateData("No resources match the search criteria");
    }

    @Title("CVSB-416 - CVSB-949 / CVSB-2463 - To Date data invalid and status canceled")
    @Test
    public void testResultsSubmittedToDateInvalidWithStatusCancelled() {

        testResultsSteps.getTestResultsToDate(vehicleCancelledData.getVin(), RandomStringUtils.randomAlphanumeric(4), TestResultsStatus.CANCELLED);
        testResultsSteps.statusCodeShouldBe(404);
        testResultsSteps.validateData("No resources match the search criteria");
    }

}
