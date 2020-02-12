package testresults;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import net.thucydides.core.annotations.WithTag;
import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import steps.TestResultsSteps;

@RunWith(SerenityRunner.class)
public class InsertTestResultsInDynamo {

    @Steps
    TestResultsSteps testResultsSteps;

    @WithTag("In_Test")
    @Title("CVSB-11991 - Insert previous test-results into DynamoDB using Java AWS sdk")
    @Test
    public void testResultsSubmittedToDateExisting() throws JSONException {
        testResultsSteps.insertRecordInDynamo("test-results_roadworthiness_hgv_fail_7675.json", "test-results");
    }
}
