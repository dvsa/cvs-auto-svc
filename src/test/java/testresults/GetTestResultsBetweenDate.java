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
public class GetTestResultsBetweenDate {

    @Steps
    TestResultsSteps testResultsSteps;

    private TestResultsGet vehicleSubmittedData = TestResultsData.buildTestResultsSubmittedDataWithCalculated().build();
    private TestResultsGet vehicleCancelledData = TestResultsData.buildTestResultsCancelleddDataWithCalculated().build();


    @Title("CVSB-416 - CVSB-949 / CVSB-2434 - Between Date data found and status default")
    @Test
    public void testResultsBetweenDateExisting() {

        testResultsSteps.getTestResultsBetweenDate(vehicleSubmittedData.getVin(), DataUtil.buildDate(vehicleSubmittedData.getTestTypes().get(0).getCreatedAt(), -1), DataUtil.buildDate(vehicleSubmittedData.getTestTypes().get(0).getCreatedAt(), 1));
        testResultsSteps.statusCodeShouldBe(200);
        testResultsSteps.validateData(vehicleSubmittedData);
    }

    @Title("CVSB-416 - CVSB-949 / CVSB-2435 - Between Date data not found (date higher than existing data) and status default")
    @Test
    public void testResultsBetweenDateNotExistingHigher() {

        testResultsSteps.getTestResultsBetweenDate(vehicleSubmittedData.getVin(), DataUtil.buildDate(vehicleSubmittedData.getTestTypes().get(0).getCreatedAt(), 1), DataUtil.buildDate(vehicleSubmittedData.getTestTypes().get(0).getCreatedAt(), 2));
        testResultsSteps.statusCodeShouldBe(404);
        testResultsSteps.validateData("No resources match the search criteria");
    }

    @Title("CVSB-416 - CVSB-949 / CVSB-2436 - Between Date data not found (date lower than existing data) and status default")
    @Test
    public void testResultsBetweenDateNotExistingLower() {

        testResultsSteps.getTestResultsBetweenDate(vehicleSubmittedData.getVin(), DataUtil.buildDate(vehicleSubmittedData.getTestTypes().get(0).getCreatedAt(), -2), DataUtil.buildDate(vehicleSubmittedData.getTestTypes().get(0).getCreatedAt(), -1));
        testResultsSteps.statusCodeShouldBe(404);
        testResultsSteps.validateData("No resources match the search criteria");
    }


    @Title("CVSB-416 - CVSB-949 / CVSB-2437 - Between Date invalid (fromDateTime, toDateTime invalid values) and status default - random")
    @Test
    public void testResultsBetweenDateInvalid() {

        testResultsSteps.getTestResultsBetweenDate(vehicleSubmittedData.getVin(), RandomStringUtils.randomAlphanumeric(4), RandomStringUtils.randomAlphanumeric(4));
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validateData("Bad request");
    }

    @Title("CVSB-416 - CVSB-949 / CVSB-2437 - Between Date invalid (fromDateTime, toDateTime invalid values) and status default - empty")
    @Test
    public void testResultsBetweenDateInvalidEmpty() {

        testResultsSteps.getTestResultsBetweenDate(vehicleSubmittedData.getVin(), "", "");
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validateData("Bad request");
    }


    @Title("CVSB-416 - CVSB-949 / CVSB-2438 - Between Date data found and status submitted")
    @Test
    public void testResultsBetweenDateExistingWithStatusSubmitted() {

        testResultsSteps.getTestResultsBetweenDate(vehicleSubmittedData.getVin(), DataUtil.buildDate(vehicleSubmittedData.getTestTypes().get(0).getCreatedAt(), -1), DataUtil.buildDate(vehicleSubmittedData.getTestTypes().get(0).getCreatedAt(), 1), TestResultsStatus.SUBMITTED);
        testResultsSteps.statusCodeShouldBe(200);
        testResultsSteps.validateData(vehicleSubmittedData);
    }

    @Title("CVSB-416 - CVSB-949 / CVSB-2439 - Between Date data not found (date higher than existing data) and status submitted")
    @Test
    public void testResultsBetweenDateNotExistingWithStatusSubmittedHigher() {

        testResultsSteps.getTestResultsBetweenDate(vehicleSubmittedData.getVin(), DataUtil.buildDate(vehicleSubmittedData.getTestTypes().get(0).getCreatedAt(), 1), DataUtil.buildDate(vehicleSubmittedData.getTestTypes().get(0).getCreatedAt(), 2), TestResultsStatus.SUBMITTED);
        testResultsSteps.statusCodeShouldBe(404);
        testResultsSteps.validateData("No resources match the search criteria");
    }

    @Title("CVSB-416 - CVSB-949 / CVSB-2440 - Between Date data not found (date lower than existing data) and status submitted")
    @Test
    public void testResultsBetweenDateNotExistingWithStatusSubmittedLower() {

        testResultsSteps.getTestResultsBetweenDate(vehicleSubmittedData.getVin(), DataUtil.buildDate(vehicleSubmittedData.getTestTypes().get(0).getCreatedAt(), -2), DataUtil.buildDate(vehicleSubmittedData.getTestTypes().get(0).getCreatedAt(), -1), TestResultsStatus.SUBMITTED);
        testResultsSteps.statusCodeShouldBe(404);
        testResultsSteps.validateData("No resources match the search criteria");
    }

    @Title("CVSB-416 - CVSB-949 / CVSB-2441 - Between Date invalid (fromDateTime, toDateTime invalid values) and status submitted - random")
    @Test
    public void testResultsBetweenDateInvalidWithStatusSubmitted() {

        testResultsSteps.getTestResultsBetweenDate(vehicleSubmittedData.getVin(), RandomStringUtils.randomAlphanumeric(4), RandomStringUtils.randomAlphanumeric(4), TestResultsStatus.SUBMITTED);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validateData("Bad request");
    }

    @Title("CVSB-416 - CVSB-949 / CVSB-2441 - Between Date invalid (fromDateTime, toDateTime invalid values) and status submitted - empty")
    @Test
    public void testResultsBetweenDateInvalidRandomWithStatusSubmitted() {

        testResultsSteps.getTestResultsBetweenDate(vehicleSubmittedData.getVin(), "", "", TestResultsStatus.SUBMITTED);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validateData("Bad request");
    }

    @Title("CVSB-416 - CVSB-949 / CVSB-2442 - Between Date data found and status cancelled")
    @Test
    public void testResultsBetweenDateExistingWithStatusCancelled() {

        testResultsSteps.getTestResultsBetweenDate(vehicleCancelledData.getVin(), DataUtil.buildDate(vehicleCancelledData.getTestTypes().get(0).getCreatedAt(), -1), DataUtil.buildDate(vehicleCancelledData.getTestTypes().get(0).getCreatedAt(), 1), TestResultsStatus.CANCELED);
        testResultsSteps.statusCodeShouldBe(200);
        testResultsSteps.validateData(vehicleCancelledData);
    }

    @Title("CVSB-416 - CVSB-949 / CVSB-2443 - Between Date data not found (date higher than existing data) and status canceled")
    @Test
    public void testResultsBetweenDateNotExistingWithStatusCancelledHigher() {

        testResultsSteps.getTestResultsBetweenDate(vehicleCancelledData.getVin(), DataUtil.buildDate(vehicleCancelledData.getTestTypes().get(0).getCreatedAt(), 1), DataUtil.buildDate(vehicleCancelledData.getTestTypes().get(0).getCreatedAt(), 2), TestResultsStatus.CANCELED);
        testResultsSteps.statusCodeShouldBe(404);
        testResultsSteps.validateData("No resources match the search criteria");
    }

    @Title("CVSB-416 - CVSB-949 / CVSB-2444 - Between Date data not found (date lower than existing data) and status canceled")
    @Test
    public void testResultsBetweenDateNotExistingWithStatusCancelledLower() {

        testResultsSteps.getTestResultsBetweenDate(vehicleCancelledData.getVin(), DataUtil.buildDate(vehicleCancelledData.getTestTypes().get(0).getCreatedAt(), -2), DataUtil.buildDate(vehicleCancelledData.getTestTypes().get(0).getCreatedAt(), -1), TestResultsStatus.CANCELED);
        testResultsSteps.statusCodeShouldBe(404);
        testResultsSteps.validateData("No resources match the search criteria");
    }

    @Title("CVSB-416 - CVSB-949 / CVSB-2445 - Between Date invalid (fromDateTime, toDateTime invalid values) and status canceled - random")
    @Test
    public void testResultsBetweenDateInvalidWithStatusCancelled() {

        testResultsSteps.getTestResultsBetweenDate(vehicleCancelledData.getVin(), RandomStringUtils.randomAlphanumeric(4), RandomStringUtils.randomAlphanumeric(4), TestResultsStatus.CANCELED);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validateData("Bad request");
    }

    @Title("CVSB-416 - CVSB-949 / CVSB-2445 - Between Date invalid (fromDateTime, toDateTime invalid values) and status canceled - empty")
    @Test
    public void testResultsBetweenDateInvalidEmptyWithStatusCancelled() {

        testResultsSteps.getTestResultsBetweenDate(vehicleCancelledData.getVin(), "", "", TestResultsStatus.CANCELED);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validateData("Bad request");
    }

}
