package defects;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import steps.DefectsSteps;

@Ignore ("no data test")
@RunWith(SerenityRunner.class)
public class GetDefectsNoData {

    @Steps
    DefectsSteps defectsSteps;

    @Ignore ("no data test")
    @Title("CVSB-279 / CVSB-741 - AC2 - The defects reference data are not retrieved")
    public void defectsNoData() {
        defectsSteps.getDefectsWithNoData();
        defectsSteps.statusCodeShouldBe(404);
        defectsSteps.validateData("Defects not found");
    }


}
