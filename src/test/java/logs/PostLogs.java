package logs;

import data.LogsData;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import steps.LogsSteps;


@RunWith(SerenityRunner.class)
public class PostLogs {

    @Steps
    LogsSteps logsSteps;

    @Title("CVSB-5558 logs created positivie scenario")
    @Test
    public void postLogsPositiveScenario() {
        logsSteps.postLogs(LogsData.buildLogsData());
        logsSteps.statusCodeShouldBe(200);
    }

    @Title("CVSB-5558 bad request scenario")
    @Test
    public void postLogsNegativeScenario() {
      logsSteps.postLogs("");
      logsSteps.statusCodeShouldBe(400);
    }

    @Title("CVSB-5558 bad request scenario")
    @Test
    public void tryGetActionOnService() {
        logsSteps.getLogs();
        logsSteps.statusCodeShouldBe(400);
    }

    @Title("CVSB-5558 bad request scenario")
    @Test
    public void tryDeleteActionOnService() {
        logsSteps.deletLogs();
        logsSteps.statusCodeShouldBe(400);
    }
}
