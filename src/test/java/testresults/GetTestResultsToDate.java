package testresults;

import data.TestResultsData;
import model.testresults.TestResultsGet;
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

    private TestResultsGet vehicleSubmittedData = TestResultsData.buildTestResultsSubmittedDataWithCalculated().build();
    private TestResultsGet vehicleCancelledData = TestResultsData.buildTestResultsCancelleddDataWithCalculated().build();


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

    @Title("CVSB-416 - CVSB-949 / CVSB-2457 - To Date data invalid and status default - random")
    @Test
    public void testResultsSubmittedToDateInvalid() {

        testResultsSteps.getTestResultsToDate(vehicleSubmittedData.getVin(), RandomStringUtils.randomAlphanumeric(4));
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validateData("Bad request");
    }

    @Title("CVSB-416 - CVSB-949 / CVSB-2457 - To Date data invalid and status default - empty")
    @Test
    public void testResultsSubmittedToDateInvalidEmpty() {

        testResultsSteps.getTestResultsToDate(vehicleSubmittedData.getVin(), "");
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validateData("Bad Request");
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

    @Title("CVSB-416 - CVSB-949 / CVSB-2460 - To Date data invalid and status submitted - random")
    @Test
    public void testResultsSubmittedToDateInvalidWithStatusSubmitted() {

        testResultsSteps.getTestResultsToDate(vehicleSubmittedData.getVin(), RandomStringUtils.randomAlphanumeric(4), TestResultsStatus.SUBMITTED);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validateData("Bad request");
    }

    @Title("CVSB-416 - CVSB-949 / CVSB-2460 - To Date data invalid and status submitted - empty")
    @Test
    public void testResultsSubmittedToDateInvalidEmptyWithStatusSubmitted() {

        testResultsSteps.getTestResultsToDate(vehicleSubmittedData.getVin(), "", TestResultsStatus.SUBMITTED);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validateData("Bad Request");
    }

    @Title("CVSB-416 - CVSB-949 / CVSB-2461 - To Date data found and status canceled")
    @Test
    public void testResultsSubmittedToDateExistingWithStatusCancelled() {

        testResultsSteps.getTestResultsToDate(vehicleCancelledData.getVin(), DataUtil.buildDate(vehicleCancelledData.getTestTypes().get(0).getCreatedAt(), 1), TestResultsStatus.CANCELED);
        testResultsSteps.statusCodeShouldBe(200);
        testResultsSteps.validateData(vehicleCancelledData);
    }

    @Title("CVSB-416 - CVSB-949 / CVSB-2462 - To Date data not found and status canceled")
    @Test
    public void testResultsSubmittedToDateNotExistingWithStatusCancelled() {

        testResultsSteps.getTestResultsToDate(vehicleCancelledData.getVin(), DataUtil.buildDate(vehicleCancelledData.getTestTypes().get(0).getCreatedAt(), -1), TestResultsStatus.CANCELED);
        testResultsSteps.statusCodeShouldBe(404);
        testResultsSteps.validateData("No resources match the search criteria");
    }

    @Title("CVSB-416 - CVSB-949 / CVSB-2463 - To Date data invalid and status canceled - random")
    @Test
    public void testResultsSubmittedToDateInvalidWithStatusCancelled() {

        testResultsSteps.getTestResultsToDate(vehicleCancelledData.getVin(), RandomStringUtils.randomAlphanumeric(4), TestResultsStatus.CANCELED);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validateData("Bad request");
    }

    @Title("CVSB-416 - CVSB-949 / CVSB-2463 - To Date data invalid and status canceled - empty")
    @Test
    public void testResultsSubmittedToDateInvalidEmptyWithStatusCancelled() {

        testResultsSteps.getTestResultsToDate(vehicleCancelledData.getVin(), "", TestResultsStatus.CANCELED);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validateData("Bad Request");
    }

}
