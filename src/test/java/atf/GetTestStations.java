package atf;

import data.TestStationsData;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Test;
import org.junit.runner.RunWith;
import steps.ActivitiesSteps;
import steps.TestStationSteps;

@RunWith(SerenityRunner.class)
public class GetTestStations {

    @Steps
    TestStationSteps testStationSteps;
    @Steps
    ActivitiesSteps activitiesSteps;


    @Title("CVSB-1323 / CVSB-2389 - CVSB-507 /  CVSB-744 - AC1 - API Consumer retrieve all the ATFs reference data")
    @Test
    public void testStationReferenceData() {
        testStationSteps.getTestStationsWithData();
        testStationSteps.statusCodeShouldBe(200);
        testStationSteps.validateData(TestStationsData.buildTestStationData());
    }

    @Title("CVSB-7949 - AC1 - API Consumer retrieve test stations reference data - testStationStatus : active or termination requested")
    @Test
    public void testStationReferenceDataStationStatus() {
        testStationSteps.getTestStationsWithData();
        testStationSteps.statusCodeShouldBe(200);
        testStationSteps.validateData(TestStationsData.buildTestStationData());
        testStationSteps.validateEveryRecordHasField("testStationStatus");
        testStationSteps.validateEveryRecordHasFieldValue("testStationStatus", TestStationsData.buildTestStationData());
    }

    @Title("VTA-695 - Get test station email Missing parameter value check")
    @Test
    public void testStationsEmailMissingParameter() {

        testStationSteps.getTestStationsEmail(" ");
        testStationSteps.statusCodeShouldBe(400);
        testStationSteps.validateData("\"Missing parameter value.\"");

        testStationSteps.getTestStationsEmail("null");
        testStationSteps.statusCodeShouldBe(400);
        testStationSteps.validateData("\"Missing parameter value.\"");

        testStationSteps.getTestStationsEmail("undefined");
        testStationSteps.statusCodeShouldBe(400);
        testStationSteps.validateData("\"Missing parameter value.\"");
    }
}
