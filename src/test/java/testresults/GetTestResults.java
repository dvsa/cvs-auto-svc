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

@WithTags(
        {
                @WithTag(type = "TestResults", name = "All"),
                @WithTag(type = "TestResults", name = "Positive"),
                @WithTag(type = "Suite", name = "Positive"),

        }
)

@RunWith(SerenityRunner.class)
public class GetTestResults {

    @Steps
    TestResultsSteps testResultsSteps;

    private TestResults vehicleSubmittedData = TestResultsData.buildTestResultsSubmittedData();
    private TestResults vehicleCancelledData = TestResultsData.buildTestResultsCancelledData();


    @Title("CVSB-416 - CVSB-949 / CVSB-2215 - API Consumer retrieve the Test results for the input Vin (DEFAULT)")
    @Test
    public void testResultsSubmittedReferenceData() {

        testResultsSteps.getTestResults(vehicleSubmittedData.getVin());
        testResultsSteps.statusCodeShouldBe(200);
        testResultsSteps.validateData(vehicleSubmittedData);
    }

    @Title("CVSB-416 - CVSB-949 / CVSB-2213 - API Consumer retrieve the Test results for the input Vin (SUBMITTED)")
    @Test
    public void testResultsWithStatusSubmittedReferenceData() {

        testResultsSteps.getTestResults(vehicleSubmittedData.getVin(), TestResultsStatus.SUBMITTED);
        testResultsSteps.statusCodeShouldBe(200);
        testResultsSteps.validateData(vehicleSubmittedData);
    }

    @Title("CVSB-416 - CVSB-949 / CVSB-2213 - API Consumer retrieve the Test results for the input Vin (CANCELED)")
    @Test
    public void testResultsWithStatusCanceledReferenceData() {

        testResultsSteps.getTestResults(vehicleCancelledData.getVin(), TestResultsStatus.CANCELLED);
        testResultsSteps.statusCodeShouldBe(200);
        testResultsSteps.validateData(vehicleCancelledData);
    }

    @Title("CVSB-416 - CVSB-949 / CVSB-2213 - API Consumer retrieve the Test results for the input Vin (INVALID)")
    @Test
    public void testResultsWithStatusInvalidReferenceData() {

        testResultsSteps.getTestResults(vehicleCancelledData.getVin(), TestResultsStatus.INVALID);
        testResultsSteps.statusCodeShouldBe(404);
        testResultsSteps.validateData("No resources match the search criteria");
    }

    @Title("CVSB-416 - CVSB-949 / CVSB-2216 - No data found")
    @Test
    public void testResultsSubmittedAndNotExistingVin() {

        testResultsSteps.getTestResults(RandomStringUtils.randomAlphanumeric(17));
        testResultsSteps.statusCodeShouldBe(404);
        testResultsSteps.validateData("No resources match the search criteria");
    }

    @Title("CVSB-416 - CVSB-949 / CVSB-2431 - Status submitted and no data found")
    @Test
    public void testResultsWithStatusSubmittedAndNotExistingVin() {

        testResultsSteps.getTestResults(RandomStringUtils.randomAlphanumeric(17), TestResultsStatus.SUBMITTED);
        testResultsSteps.statusCodeShouldBe(404);
        testResultsSteps.validateData("No resources match the search criteria");
    }

    @Title("CVSB-416 - CVSB-949 / CVSB-2432 - Status canceled and no data found")
    @Test
    public void testResultsWithStatusCanceledAndNotExistingVin() {

        testResultsSteps.getTestResults(RandomStringUtils.randomAlphanumeric(17), TestResultsStatus.CANCELLED);
        testResultsSteps.statusCodeShouldBe(404);
        testResultsSteps.validateData("No resources match the search criteria");
    }

    @Title("CVSB-416 - CVSB-949 / CVSB-2433 - Status invalid and no data found")
    @Test
    public void testResultsWithStatusInvalidAndNotExistingVin() {

        testResultsSteps.getTestResults(RandomStringUtils.randomAlphanumeric(17), TestResultsStatus.INVALID);
        testResultsSteps.statusCodeShouldBe(404);
        testResultsSteps.validateData("No resources match the search criteria");
    }

}
