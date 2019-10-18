package preparers;

import data.PreparerData;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Test;
import org.junit.runner.RunWith;
import steps.PreparersSteps;


@RunWith(SerenityRunner.class)
public class GetPreparers {

    @Steps
    PreparersSteps preparersSteps;


    @Title("CVSB-297 / CVSB-742 - AC1 - API Consumer retrieve all the preparers IDs")
    @Test
    public void preparersReferenceData() {
        preparersSteps.getPreparersWithData();
        preparersSteps.statusCodeShouldBe(200);
        preparersSteps.validateData(PreparerData.buildPreparerData());
    }


}
