package atf;

import data.AtfData;
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
                @WithTag(type = "Atf", name = "Positive"),
                @WithTag(type = "Suite", name = "Positive"),

        }
)
@RunWith(SerenityRunner.class)
public class GetAtf {

    @Steps
    AtfSteps atfSteps;


    @Title("CVSB-507 /  CVSB-744 - AC1 - API Consumer retrieve all the ATFs reference data")
    @Test
    public void atfReferenceData() {
        atfSteps.getATFsWithData();
        atfSteps.statusCodeShouldBe(200);
        atfSteps.validateData(AtfData.buildAtfData());
    }

}
