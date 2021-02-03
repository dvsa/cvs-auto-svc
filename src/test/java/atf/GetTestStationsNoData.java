package atf;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import steps.TestStationSteps;

@Ignore ("no data test")
@RunWith(SerenityRunner.class)
public class GetTestStationsNoData {

    @Steps
    TestStationSteps testStationSteps;

    @Ignore ("no data test")
    @Title("CVSB-1323 / CVSB-2390 - CVSB-507 /  CVSB-745 - AC2 - No data returned")
    public void testStationNoData() {
        testStationSteps.getTestStationsWithNoData();
        testStationSteps.statusCodeShouldBe(404);
        testStationSteps.validateData("Test stations not found");
    }


}
