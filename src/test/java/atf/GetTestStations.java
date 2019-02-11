package atf;

import data.TestStationsData;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.core.annotations.WithTags;
import org.junit.Test;
import org.junit.runner.RunWith;
import steps.TestStationSteps;


@WithTags(
        {
                @WithTag(type = "TestStations", name = "All"),
                @WithTag(type = "TestStations", name = "Positive"),
                @WithTag(type = "Service", name = "One"),

        }
)
@RunWith(SerenityRunner.class)
public class GetTestStations {

    @Steps
    TestStationSteps testStationSteps;


    @Title("CVSB-1323 / CVSB-2389 - CVSB-507 /  CVSB-744 - AC1 - API Consumer retrieve all the ATFs reference data")
    @Test
    public void testStationReferenceData() {
        testStationSteps.getTestStationsWithData();
        testStationSteps.statusCodeShouldBe(200);
        testStationSteps.validateData(TestStationsData.buildTestStationData());
    }

}
