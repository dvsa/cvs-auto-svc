package testtypes;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.core.annotations.WithTags;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import steps.TestTypeSteps;


@WithTags(
        {
                @WithTag(type = "TestTypes", name = "All"),
                @WithTag(type = "TestTypes", name = "Negative"),
                @WithTag(type = "Service", name = "Two"),

        }
)

@RunWith(SerenityRunner.class)
public class GetTestTypesNoData {

    @Steps
    TestTypeSteps testTypeSteps;

    @Ignore ("NoData Filter")
    @Title("CVSB-996 / CVSB-1869 - CVSB-579 / CVSB-747 - AC2 - No data returned")
    @Test
    public void testTypeNoData() {
        testTypeSteps.getTestTypesWithNoData();
        testTypeSteps.statusCodeShouldBe(404);
        testTypeSteps.validateData("Test types not found");
    }


}
