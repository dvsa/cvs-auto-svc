package testtypes;


import data.TestTypeData;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.core.annotations.WithTags;
import org.junit.Test;
import org.junit.runner.RunWith;
import steps.TestTypeSteps;


@WithTags(
        {
                @WithTag(type = "TestTypes", name = "All"),
                @WithTag(type = "TestTypes", name = "Positive"),
                @WithTag(type = "Suite", name = "Positive"),

        }
)

@RunWith(SerenityRunner.class)
public class GetTestTypes {

    @Steps
    TestTypeSteps testTypeSteps;

    @Title("CVSB-579 / CVSB-746 - AC1 - API Consumer retrieve all the test types and test codes reference data")
    @Test
    public void testTypesReferenceData() {
        testTypeSteps.getTestTypesWithData();
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.validateData(TestTypeData.buildTestTypeData());
    }


}
