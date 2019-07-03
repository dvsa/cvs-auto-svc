package atf;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.core.annotations.WithTags;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import steps.TestStationSteps;


@WithTags(
        {
                @WithTag(type = "TestStations", name = "All"),
                @WithTag(type = "TestStations", name = "Negative"),
                @WithTag(type = "Service", name = "Two"),

        }
)
@RunWith(SerenityRunner.class)
public class GetTestStationsNoData {

    @Steps
    TestStationSteps testStationSteps;

    @Ignore
    @Title("CVSB-1323 / CVSB-2390 - CVSB-507 /  CVSB-745 - AC2 - No data returned")
    @Test
    public void testStationNoData() {
        testStationSteps.getTestStationsWithNoData();
        testStationSteps.statusCodeShouldBe(404);
        testStationSteps.validateData("Test stations not found");
    }


}
