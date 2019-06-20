package logs;

import data.LogsData;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.core.annotations.WithTags;
import org.junit.Test;
import org.junit.runner.RunWith;
import steps.LogsSteps;

@WithTags(
        {
                @WithTag(type = "TestResults", name = "All"),
                @WithTag(type = "TestResults", name = "Positive"),
                @WithTag(type = "Service", name = "One"),
        }
)

@RunWith(SerenityRunner.class)
public class PostLogs {

    @Steps
    LogsSteps logsSteps;

    @Title("CVSB-5558 logs created positivie scenario")
    @Test
    public void postLogsPositiveScenario() {
        logsSteps.postActivities(LogsData.buildLogsData());
        logsSteps.statusCodeShouldBe(200);
    }

    @Title("CVSB-5558 bad request scenario")
    @Test
    public void postLogsNegativeScenario() {
      logsSteps.postActivities("");
      logsSteps.statusCodeShouldBe(400);
    }
}
