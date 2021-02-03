package testtypes;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import steps.TestTypeSteps;

@Ignore ("no data test")
@RunWith(SerenityRunner.class)
public class GetTestTypesNoData {

    @Steps
    TestTypeSteps testTypeSteps;

    @Ignore ("no data test")
    @Title("CVSB-996 / CVSB-1869 - CVSB-579 / CVSB-747 - AC2 - No data returned")
    public void testTypeNoData() {
        testTypeSteps.getTestTypesWithNoData();
        testTypeSteps.statusCodeShouldBe(404);
        testTypeSteps.validateData("Test types not found");
    }


}
