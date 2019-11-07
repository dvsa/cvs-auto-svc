package preparers;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import steps.PreparersSteps;


@RunWith(SerenityRunner.class)
public class GetPreparersNoData {

    @Steps
    PreparersSteps preparersSteps;

    @Ignore ("no data test")
    @Title("CVSB-579 / CVSB-743 - AC2 - The preparers IDs are not retrieved")
    @Test
    public void preparersNoData() {
        preparersSteps.getPreparersWithNoData();
        preparersSteps.statusCodeShouldBe(404);
        preparersSteps.validateData("Preparers not found");
    }


}

