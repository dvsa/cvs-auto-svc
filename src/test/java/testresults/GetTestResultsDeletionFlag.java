package testresults;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Test;
import org.junit.runner.RunWith;
import steps.TestResultsSteps;


@RunWith(SerenityRunner.class)
public class GetTestResultsDeletionFlag {

    @Steps
    TestResultsSteps testResultsSteps;

    @Title("Deletion flag is true at test record level and test type level")
    @Test
    public void getDeletionFlagTrueTrue() {
        //testResultsSteps.getTestResults("XMGDE02FS0H012311");
        testResultsSteps.getTestResults("1122");
        testResultsSteps.statusCodeShouldBe(404);
    }

    @Title("Deletion flag is true at test type level and false at test record")
    @Test
    public void getDeletionFlagTrueFalse() {
        //testResultsSteps.getTestResults("XMGDE02FS0H012312");
        testResultsSteps.getTestResults("1123");
        testResultsSteps.statusCodeShouldBe(200);
        testResultsSteps.testTypeLengthShouldBe(0);
    }

    @Title("Deletion flag is true at test record level and false at test type level")
    @Test
    public void getDeletionFlagFalseTrue() {
        //testResultsSteps.getTestResults("XMGDE02FS0H012313");
        testResultsSteps.getTestResults("1124");
        testResultsSteps.statusCodeShouldBe(404);
    }

    @Title("Deletion flag is false at test record level and test type level")
    @Test
    public void getDeletionFlagFalseFalse() {
        //testResultsSteps.getTestResults("XMGDE02FS0H012314");
        testResultsSteps.getTestResults("1125");
        testResultsSteps.statusCodeShouldBe(200);
        testResultsSteps.testTypeLengthShouldBe(1);
    }
}
