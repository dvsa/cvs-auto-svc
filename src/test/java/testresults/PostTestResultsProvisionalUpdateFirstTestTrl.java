package testresults;

import data.GenericData;
import model.vehicles.VehicleTechnicalRecordStatus;
import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import net.thucydides.junit.annotations.TestData;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import steps.*;
import util.JsonPathAlteration;

import java.util.*;

@Ignore("Ignoring these tests until there is support to making post request on tech records endpoint for trailers")
@RunWith(SerenityParameterizedRunner.class)
public class PostTestResultsProvisionalUpdateFirstTestTrl {

    @Steps
    TestResultsSteps testResultsSteps;

    @Steps
    VehicleTechnicalRecordsSteps vehicleTechnicalRecordsSteps;

    @TestData
    public static Collection<Object[]> testData(){
        return Arrays.asList(new Object[][]{
                {"First test", "95", "First test"},
                {"Retest", "65", "Paid first test retest"},
                {"Retest", "66", "Paid prohibition clearance on first test (full inspection with certification)"},
                {"Prohibition clearance", "83", "Part paid first test retest"},
                {"Prohibition clearance", "82", "Paid prohibition clearance on first test (full inspection with certification)"},
        });
    }

    private String name;
    private String testTypeName;
    private String testTypeId;

    public PostTestResultsProvisionalUpdateFirstTestTrl(String name, String testTypeId, String testTypeName) {
        this.name = name;
        this.testTypeName = testTypeName;
        this.testTypeId = testTypeId;
    }

    @Title("CVSB-7049 - AC2 - VSA submits first test = PASS - TRL")
    public void testResultsProvisionalUpdateTrl() {

        //generate random Vin
        String randomVin = RandomStringUtils.randomAlphanumeric(14).toUpperCase();
        //generate random system number
        String randomSystemNumber = GenericData.generateRandomSystemNumber();
        //generate random Vrm
        String randomTrailerId = (RandomStringUtils.randomAlphabetic(1) + RandomStringUtils.randomNumeric(6)).toUpperCase();
        // read post request body from file
        String postRequestBody = GenericData.readJsonValueFromFile("technical-records-trl-provisional.json","$");
        // create alteration to change Vin in the request body with the random generated Vin
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin,"","REPLACE");
        // create alteration to change systemNumber in the request body with the random generated Vin
        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$.systemNumber", randomSystemNumber,"","REPLACE");
        // create alteration to change trailer id in the request body with the random generated trailer id
        JsonPathAlteration alterationVrm = new JsonPathAlteration("$.trailerId", randomTrailerId,"","REPLACE");
         // create alteration to change partial vin in the request body with the random generated primary vrm
        JsonPathAlteration alterationPartialVin = new JsonPathAlteration("$.partialVin", randomVin.substring(randomVin.length() - 6),"","REPLACE");

        // initialize the alterations list with both declared alteration
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(
                alterationVin,
                alterationVrm,
                alterationPartialVin,
                alterationSystemNumber
        ));

        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBody, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(201);
        // retrieve the vehicle and check the status code and the techRecord size
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsByStatus(randomSystemNumber, VehicleTechnicalRecordStatus.ALL);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].vin", randomVin);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].statusCode", "provisional");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord.size()", 1);
        // build and post a notifiable alteration test results


        // read the base test result JSON.
        String testResultRecord = GenericData.readJsonValueFromFile("test-results_provisional_update_trl.json","$");

        String testResultId = UUID.randomUUID().toString();

        // Create alteration to add one more tech record to in the request body
        JsonPathAlteration trAlterationVin = new JsonPathAlteration("$.vin", randomVin,"","REPLACE");
        JsonPathAlteration trAlterationVrm = new JsonPathAlteration("$.trailerId", randomTrailerId,"","REPLACE");
        JsonPathAlteration trAlterationName = new JsonPathAlteration("$.testTypes[0].name", name,"","REPLACE");
        JsonPathAlteration trAlterationTestTypeId = new JsonPathAlteration("$.testTypes[0].testTypeId", testTypeId,"","REPLACE");
        JsonPathAlteration trAlterationTestTypeName = new JsonPathAlteration("$.testTypes[0].testTypeName", testTypeName,"","REPLACE");
        JsonPathAlteration trAlterationTestResultId = new JsonPathAlteration("$.testResultId", testResultId,"","REPLACE");


        // Collate the list of alterations.
        List<JsonPathAlteration> trAlterations = new ArrayList<>(Arrays.asList(
                trAlterationVin,
                trAlterationVrm,
                trAlterationTestResultId,
                trAlterationName,
                trAlterationTestTypeId,
                trAlterationTestTypeName
        ));

        // Post the results, together with any alterations, and verify that they are accepted.
        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, trAlterations);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");

        // wait a maximum of seconds for the vehicle tech records to be updated
        // vehicleTechnicalRecordsSteps.waitForVehicleTechRecordsToBeUpdated(randomVin, 20);

        // retrieve the tech record of the vehicle and verify whether the status has changed to current
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsByStatus(randomVin, VehicleTechnicalRecordStatus.ALL);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("vin", randomVin);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord.size()", 2);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[0].statusCode", "archived");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[1].statusCode", "current");


    }
}
