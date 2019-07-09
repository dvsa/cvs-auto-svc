package defects;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.core.annotations.WithTags;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import steps.DefectsSteps;


@WithTags(
        {
                @WithTag(type = "Defects", name = "All"),
                @WithTag(type = "Defects", name = "Negative"),
                @WithTag(type = "Service", name = "Two"),

        }
)

@RunWith(SerenityRunner.class)
public class GetDefectsNoData {

    @Steps
    DefectsSteps defectsSteps;

    @Ignore ("NoData Filter")
    @Title("CVSB-279 / CVSB-741 - AC2 - The defects reference data are not retrieved")
    @Test
    public void defectsNoData() {
        defectsSteps.getDefectsWithNoData();
        defectsSteps.statusCodeShouldBe(404);
        defectsSteps.validateData("Defects not found");
    }


}
