package testresults;

import data.GenericData;
import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.junit.annotations.TestData;
import org.junit.Test;
import org.junit.runner.RunWith;
import steps.*;
import util.JsonPathAlteration;

import java.util.*;

@WithTag("In_test")
@RunWith(SerenityParameterizedRunner.class)
public class PostTestResultsVehicleClass {

    @Steps
    TestResultsSteps testResultsSteps;

    @Steps
    VehicleTechnicalRecordsSteps vehicleTechnicalRecordsSteps;

    @TestData
    public static Collection<Object[]> testData() {
        return Arrays.asList(new Object[][]{
                {"psv", 201},
                {"hgv", 201},
                {"trl", 201},
                {"lgv", 201},
                {"car", 201}
        });
    }

        private String vehicle;

    public PostTestResultsVehicleClass(String vehicle, int statusCode) {
            this.vehicle = vehicle;
        }

    @Title("CVSB-13903 - (IMPROVEMENT)[BE] Specialist tests, updating the test results API specs - vehicleClass mandatory only for motorcycles - AC1 - vehicleClass - optional for other than motorcycle")
    @Test
    public void testVehicleTechRecordMotorcycleVehicleCategoryOptional() {

        // Tech record exists already in dynamoDb with a prep-populated euVehicleCategory
        String systemNumber = vehicleTechnicalRecordsSteps.getNextSystemNumberInSequence();
        String randomVin = GenericData.generateRandomVin();
        String randomTestResultId = UUID.randomUUID().toString();

        // Read test result base json + Generate random values
        String testResultRecord = GenericData.readJsonValueFromFile("test-results_" + vehicle + ".json", "$");
        JsonPathAlteration alterationTestResultVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationTestResultId = new JsonPathAlteration("$.testResultId", randomTestResultId, "", "REPLACE");
        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$.systemNumber", systemNumber, "", "REPLACE");
        JsonPathAlteration alterationVehicleClass = new JsonPathAlteration("$.vehicleClass", "", "", "DELETE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterationsTestResult = new ArrayList<>(Arrays.asList(
                alterationTestResultVin,
                alterationTestResultId,
                alterationSystemNumber,
                alterationVehicleClass));

        // Post the results, together with any alterations, and verify that they are accepted.
        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, alterationsTestResult);
        testResultsSteps.waitForTestResultsToBeUpdated(systemNumber, 50);
        testResultsSteps.statusCodeShouldBe(200);
        testResultsSteps.getTestResultsSysNumber(systemNumber);
        testResultsSteps.statusCodeShouldBe(200);
        testResultsSteps.validateTestFieldNotPresent("vehicleClass");
    }
}
