package testresults;

import data.GenericData;
import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.junit.annotations.TestData;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import steps.TestResultsSteps;
import steps.VehicleTechnicalRecordsSteps;
import util.JsonPathAlteration;

import java.util.*;

@RunWith(SerenityParameterizedRunner.class)
public class PostTestResultsIntegrationAdr {



    @Steps
    TestResultsSteps testResultsSteps;

    @Steps
    VehicleTechnicalRecordsSteps vehicleTechnicalRecordsSteps;

    @TestData
    public static Collection<Object[]> testData(){
        return Arrays.asList(new Object[][]{
//                {"50", "Technical test", "ADR test", "pass"},
//                {"59", "Retest", "Paid ADR retest", "prs"},
//                {"60", "Retest", "Free ADR retest", "fail"},
                {"60", "Retest", "Free ADR retest", "abandoned"},
        });
    }

    public PostTestResultsIntegrationAdr(String testTypeId, String name, String testTypeName, String testResult) {
        this.testTypeId = testTypeId;
        this.name = name;
        this.testTypeName = testTypeName;
        this.testResult = testResult;
    }

    private String testTypeId;
    private String name;
    private String testTypeName;
    private String testResult;

    @Title("CVSB-8798 / CVSB-3952 - As a VSA I want to be able to generate the ADR certificate so that I can receive it via email - TRL")
    @Test
    public void testResultsADRCertificateTrl() {

        //generate random Vin
        String randomVin = GenericData.generateRandomVin();
        //get the next systemNumber
        String systemNumber = vehicleTechnicalRecordsSteps.getNextSystemNumberInSequence();
        //generate random Vrm
        String randomVrm = (RandomStringUtils.randomAlphabetic(1) + RandomStringUtils.randomNumeric(2) + RandomStringUtils.randomAlphabetic(3)).toUpperCase();
        //get the next trailerId
        String trailerId = vehicleTechnicalRecordsSteps.getNextTrailerIdInSequence();
        //generate a random UUID for testResultId
        String testResultId = UUID.randomUUID().toString();

        //create a new TRL vehicle in the tech-record

        // read post request body from file
        String postRequestBody = GenericData.readJsonValueFromFile("technical-records_trl_all_fields.json","$");

        // create alteration to change Vin in the request body with the random generated Vin
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin,"","REPLACE");
        // create alteration to change partial vin in the request body with the random generated primary vrm
        JsonPathAlteration alterationPartialVin = new JsonPathAlteration("$.partialVin", randomVin.substring(randomVin.length() - 6),"","REPLACE");
        // create alteration to change systemNumber in the request body with the random generated Vin
        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$.systemNumber", systemNumber,"","REPLACE");
        // create alteration to change primary vrm in the request body with the random generated primary vrm
        JsonPathAlteration alterationVrm = new JsonPathAlteration("$.primaryVrm", randomVrm,"","REPLACE");
        // create alteration to change trailerId in the request body with the next generated trailerId
        JsonPathAlteration alterationTrailerId = new JsonPathAlteration("$.trailerId", trailerId,"","REPLACE");


        // initialize the alterations lists with declared alteration
        List<JsonPathAlteration> alterationsOnCreate = new ArrayList<>(Arrays.asList(
                alterationVin,
                alterationPartialVin,
                alterationSystemNumber,
                alterationVrm,
                alterationTrailerId
        ));

        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBody, alterationsOnCreate);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(201);

        //POST a test-result for the newly created TRL vehicle (use an ADR test - pass)

        // Read the base test result JSON.
        String testResultRecord = GenericData.readJsonValueFromFile("test-results_integration_trl_10773.json","$");

        // Create alteration to add one more tech record to in the request body
        JsonPathAlteration postAlterationVin = new JsonPathAlteration("$.vin", randomVin,"","REPLACE");
        JsonPathAlteration postAlterationTestResultId = new JsonPathAlteration("$.testResultId", testResultId,"","REPLACE");
        JsonPathAlteration postAlterationTestTypeId = new JsonPathAlteration("$.testTypes[0].testTypeId", testTypeId,"","REPLACE");
        JsonPathAlteration postAlterationName = new JsonPathAlteration("$.testTypes[0].name", name,"","REPLACE");
        JsonPathAlteration postAlterationTestTypeName = new JsonPathAlteration("$.testTypes[0].testTypeName", testTypeName,"","REPLACE");
        JsonPathAlteration postAlterationTestResult = new JsonPathAlteration("$.testTypes[0].testResult", testResult,"","REPLACE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterationsOnPost = new ArrayList<>(Arrays.asList(
                postAlterationVin,
                postAlterationTestResultId,
                postAlterationTestTypeId,
                postAlterationName,
                postAlterationTestTypeName,
                postAlterationTestResult
        ));

        // Post the results, together with any alterations, and verify that they are handled correctly.
        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, alterationsOnPost);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
    }

}
