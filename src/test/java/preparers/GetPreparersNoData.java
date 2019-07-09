package preparers;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.core.annotations.WithTags;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import steps.PreparersSteps;


@WithTags(
        {
                @WithTag(type = "Preparers", name = "All"),
                @WithTag(type = "Preparers", name = "Negative"),
                @WithTag(type = "Service", name = "Two"),

        }
)

@RunWith(SerenityRunner.class)
public class GetPreparersNoData {

    @Steps
    PreparersSteps preparersSteps;

    @Ignore ("NoData Filter")
    @Title("CVSB-579 / CVSB-743 - AC2 - The preparers IDs are not retrieved")
    @Test
    public void preparersNoData() {
        preparersSteps.getPreparersWithNoData();
        preparersSteps.statusCodeShouldBe(404);
        preparersSteps.validateData("Preparers not found");
    }


}

