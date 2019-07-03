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
import org.junit.Ignore;
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
public class GetTestResultsFromDate {
    @Steps
    TestResultsSteps testResultsSteps;

    private TestResultsGet vehicleSubmittedData = TestResultsData.buildTestResultsSubmittedDataWithCalculated().build();
    private TestResultsGet vehicleCancelledData = TestResultsData.buildTestResultsCancelleddDataWithCalculated().build();


//    @Title("CVSB-416 - CVSB-949 / CVSB-2446 - From Date data found and status default")
//    @Test
//    public void testResultsSubmittedFromDateExisting() {
//
//        testResultsSteps.getTestResultsFromDate(vehicleSubmittedData.getVin(), DataUtil.buildDate(vehicleSubmittedData.getTestTypes().get(0).getCreatedAt(), -1));
//        testResultsSteps.statusCodeShouldBe(200);
//        testResultsSteps.validateData(vehicleSubmittedData);
//    }

    @Title("CVSB-416 - CVSB-949 / CVSB-2447 - From Date data not found and status default")
    @Test
    public void testResultsSubmittedFromDateNotExisting() {

        testResultsSteps.getTestResultsFromDate(vehicleSubmittedData.getVin(), DataUtil.buildDate(vehicleSubmittedData.getTestTypes().get(0).getCreatedAt(), 1));
        testResultsSteps.statusCodeShouldBe(404);
        testResultsSteps.validateData("No resources match the search criteria");
    }

    @Title("CVSB-416 - CVSB-949 / CVSB-2448 - From Date data invalid and status default")
    @Test
    public void testResultsSubmittedFromDateInvalid() {

        testResultsSteps.getTestResultsFromDate(vehicleSubmittedData.getVin(), RandomStringUtils.randomAlphanumeric(4));
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validateData("Bad request");
    }

    @Title("CVSB-416 - CVSB-949 / CVSB-2448 - From Date data invalid and status default")
    @Test
    public void testResultsSubmittedFromDateInvalidEmpty() {

        testResultsSteps.getTestResultsFromDate(vehicleSubmittedData.getVin(), "");
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validateData("Bad request");
    }

    @Ignore
    @Title("CVSB-416 - CVSB-949 / CVSB-2449 - From Date data found and status submitted")
    @Test
    public void testResultsSubmittedFromDateExistingWithStatusSubmitted() {

        testResultsSteps.getTestResultsFromDate(vehicleSubmittedData.getVin(), DataUtil.buildDate(vehicleSubmittedData.getTestTypes().get(0).getCreatedAt(), -1), TestResultsStatus.SUBMITTED);
        testResultsSteps.statusCodeShouldBe(200);
        testResultsSteps.validateData(vehicleSubmittedData);
    }

    @Title("CVSB-416 - CVSB-949 / CVSB-2450 - From Date data not found and status submitted")
    @Test
    public void testResultsSubmittedFromDateNotExistingWithStatusSubmitted() {

        testResultsSteps.getTestResultsFromDate(vehicleSubmittedData.getVin(), DataUtil.buildDate(vehicleSubmittedData.getTestTypes().get(0).getCreatedAt(), 1), TestResultsStatus.SUBMITTED);
        testResultsSteps.statusCodeShouldBe(404);
        testResultsSteps.validateData("No resources match the search criteria");
    }

    @Title("CVSB-416 - CVSB-949 / CVSB-2451 - From Date data invalid and status submitted - random")
    @Test
    public void testResultsSubmittedFromDateInvalidWithStatusSubmitted() {

        testResultsSteps.getTestResultsFromDate(vehicleSubmittedData.getVin(), RandomStringUtils.randomAlphanumeric(4), TestResultsStatus.SUBMITTED);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validateData("Bad request");
    }

    @Title("CVSB-416 - CVSB-949 / CVSB-2451 - From Date data invalid and status submitted - empty")
    @Test
    public void testResultsSubmittedFromDateInvalidEmptyWithStatusSubmitted() {

        testResultsSteps.getTestResultsFromDate(vehicleSubmittedData.getVin(), "", TestResultsStatus.SUBMITTED);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validateData("Bad request");
    }

    @Title("CVSB-416 - CVSB-949 / CVSB-2452 - From Date data found and status canceled")
    @Test
    public void testResultsSubmittedFromDateExistingWithStatusCancelled() {

        testResultsSteps.getTestResultsFromDate(vehicleCancelledData.getVin(), DataUtil.buildDate(vehicleCancelledData.getTestTypes().get(0).getCreatedAt(), -1), TestResultsStatus.CANCELED);
        testResultsSteps.statusCodeShouldBe(200);
        testResultsSteps.validateData(vehicleCancelledData);
    }

    @Title("CVSB-416 - CVSB-949 / CVSB-2453 - From Date data not found and status canceled")
    @Test
    public void testResultsSubmittedFromDateNotExistingWithStatusCancelled() {

        testResultsSteps.getTestResultsFromDate(vehicleCancelledData.getVin(), DataUtil.buildDate(vehicleCancelledData.getTestTypes().get(0).getCreatedAt(), 1), TestResultsStatus.CANCELED);
        testResultsSteps.statusCodeShouldBe(404);
        testResultsSteps.validateData("No resources match the search criteria");
    }

    @Title("CVSB-416 - CVSB-949 / CVSB-2454 - From Date data invalid and status canceled - random")
    @Test
    public void testResultsSubmittedFromDateInvalidWithStatusCancelled() {

        testResultsSteps.getTestResultsFromDate(vehicleCancelledData.getVin(), RandomStringUtils.randomAlphanumeric(4), TestResultsStatus.CANCELED);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validateData("Bad request");
    }

    @Title("CVSB-416 - CVSB-949 / CVSB-2454 - From Date data invalid and status canceled - empty")
    @Test
    public void testResultsSubmittedFromDateInvalidEmptyWithStatusCancelled() {

        testResultsSteps.getTestResultsFromDate(vehicleCancelledData.getVin(), "", TestResultsStatus.CANCELED);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validateData("Bad request");
    }

}
