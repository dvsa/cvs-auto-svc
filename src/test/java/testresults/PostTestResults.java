package testresults;

import data.TestResultsData;
import model.testresults.TestResults;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.core.annotations.WithTags;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import steps.TestResultsSteps;

@WithTags(
        {
                @WithTag(type = "TestResults", name = "All"),
                @WithTag(type = "TestResults", name = "Positive"),
                @WithTag(type = "Service", name = "One"),

        }
)

@RunWith(SerenityRunner.class)
public class PostTestResults {

    @Steps
    TestResultsSteps testResultsSteps;

    private TestResults vehicleData = TestResultsData.buildTestResultsSubmittedData();

    @Ignore("continue once internal server error is fixed")
    @Title("")
    @Test
    public void testTypesReferenceData() {

        testResultsSteps.postTestResults(vehicleData  .setVrm("SO79TAR")
                .setVin("1B7GG36N12S936184"));
        testResultsSteps.statusCodeShouldBe(200);
        testResultsSteps.validateData(vehicleData);
    }


}

