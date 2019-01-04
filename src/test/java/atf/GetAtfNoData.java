package atf;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.core.annotations.WithTags;
import org.junit.Test;
import org.junit.runner.RunWith;
import steps.AtfSteps;


@WithTags(
        {
                @WithTag(type = "Atf", name = "All"),
                @WithTag(type = "Atf", name = "Negative"),
                @WithTag(type = "Suite", name = "Negative"),

        }
)
@RunWith(SerenityRunner.class)
public class GetAtfNoData {

    @Steps
    AtfSteps atfSteps;


    @Title("CVSB-507 /  CVSB-745 - AC2 - No data returned")
    @Test
    public void atfNoData() {
        atfSteps.getATFsWithNoData();
        atfSteps.statusCodeShouldBe(404);
        atfSteps.validateData("ATFs not found");
    }


}
