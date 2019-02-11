package defects;

import data.DefectsData;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.core.annotations.WithTags;
import org.junit.Test;
import org.junit.runner.RunWith;
import steps.DefectsSteps;

@WithTags(
        {
                @WithTag(type = "Defects", name = "All"),
                @WithTag(type = "Defects", name = "Positive"),
                @WithTag(type = "Service", name = "One"),

        }
)
@RunWith(SerenityRunner.class)
public class GetDefects {

    @Steps
    DefectsSteps defectsSteps;


    @Title("CVSB-279 / CVSB-740 - AC1 - API Consumer retrieve all the defects reference data")
    @Test
    public void defectsReferenceData() {
        defectsSteps.callDefectsWithData();
        defectsSteps.statusCodeShouldBe(200);
        defectsSteps.validateData(DefectsData.buildDefectsData());
    }


}

