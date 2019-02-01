package testresults;


import data.TestResultsData;
import data.TestTypeData;
import data.VehicleTechnicalRecordsData;
import model.testresults.TestResults;
import model.vehicles.Vehicle;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.core.annotations.WithTags;
import org.junit.Test;
import org.junit.runner.RunWith;
import steps.TestResultsSteps;
import steps.TestTypeSteps;

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

    private TestResults vehicleData = TestResultsData.buildTestResultsData();


    @Title("CVSB-579 / CVSB-746 - AC1 - API Consumer retrieve all the test types and test codes reference data")
    @Test
    public void testTypesReferenceData() {

        testResultsSteps.getTestResults(vehicleData.getVin());
        testResultsSteps.statusCodeShouldBe(200);
        testResultsSteps.validateData(vehicleData);
    }


}
