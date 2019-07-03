package testtypes;


import data.TestTypeData;
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
                @WithTag(type = "TestTypes", name = "Positive"),
                @WithTag(type = "Service", name = "One"),

        }
)

@RunWith(SerenityRunner.class)
public class GetTestTypes {

    @Steps
    TestTypeSteps testTypeSteps;

    @Ignore
    @Title("CVSB-579 / CVSB-746 - CVSB-996 / CVSB-2391 AC1 - API Consumer retrieve all the test types and test codes reference data")
    @Test
    public void testTypesReferenceData() {
        testTypeSteps.getTestTypesWithData();
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.validateData(TestTypeData.buildTestTypeData());
    }

    @Title("CVSB-1073 / CVSB-2206 - AC5 The endpoint to retrieve all the test types reference data does not return the 'testTypeClassification' 'defaultTestCode' and 'linkedTestCode' attributes")
    @Test
    public void testTypesAttributesFromTestTypeIdNotPresent() {
        testTypeSteps.getTestTypesWithData();
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.validateTestTypeDataNotExisting();
    }


}
