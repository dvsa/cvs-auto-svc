package testresults;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import net.thucydides.core.annotations.WithTag;
import org.junit.Test;
import org.junit.runner.RunWith;
import steps.TestResultsSteps;
import util.DataUtil;

@RunWith(SerenityRunner.class)
public class InsertTestResultsInDynamo {

    @Steps
    TestResultsSteps testResultsSteps;

    @WithTag("In_Test")
    @Title("CVSB-11991 - Insert entries directly in test-results table in Dynamo")
    @Test
    public void testResultsSubmittedToDateExisting() {
        testResultsSteps.insertInDynamo("test-results", "");
    }
}
