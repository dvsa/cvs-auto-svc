package testresults;

import data.GenericData;
import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import net.thucydides.junit.annotations.TestData;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import steps.TestResultsSteps;
import util.JsonPathAlteration;

import java.util.*;

@Ignore("Cert generation switched off for ADR, wait untill CVSB-9134 is merged")
@RunWith(SerenityParameterizedRunner.class)
public class PostTestResultsAdrCertificate {



    @Steps
    TestResultsSteps testResultsSteps;

    @TestData
    public static Collection<Object[]> testData(){
        return Arrays.asList(new Object[][]{
                {"50", "Technical test", "ADR test"},
                {"59", "Retest", "Paid ADR retest"},
                {"60", "Retest", "Free ADR retest"}
        });
    }

    public PostTestResultsAdrCertificate(String testTypeId, String name, String testTypeName) {
        this.testTypeId = testTypeId;
        this.name = name;
        this.testTypeName = testTypeName;
    }



    private String testTypeId;
    private String name;
    private String testTypeName;

    private String test_results_ADR_TRL_json;
    private String test_results_ADR_HGV_json;
    @Before
    @Test
    public void updateJson(){
        String jsonFileName = "test-results_ADR_TRL.json";
        String jsonFileName2 = "test-results_ADR_HGV.json";
        test_results_ADR_TRL_json = GenericData.updateJson(testResultsSteps, jsonFileName, "$");
        test_results_ADR_HGV_json = GenericData.updateJson(testResultsSteps, jsonFileName2, "$");
    }

    @Title("CVSB-8798 / CVSB-3952 - As a VSA I want to be able to generate the ADR certificate so that I can receive it via email - TRL")
    public void testResultsADRCertificateTrl() {

        // Read the base test result JSON.
        String testResultRecord = test_results_ADR_TRL_json;

        // Create alteration to add one more tech record to in the request body
        String randomVin = GenericData.generateRandomVin();
        String randomSystemNumber = GenericData.generateRandomSystemNumber();
        String uuid = String.valueOf(UUID.randomUUID());
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin,"","REPLACE");
        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$", randomSystemNumber,"systemNumber","ADD_FIELD");
        JsonPathAlteration alterationTestResultId = new JsonPathAlteration("$.testResultId", uuid,"","REPLACE");
        JsonPathAlteration alterationTestTypeId = new JsonPathAlteration("$.testTypes[0].testTypeId", testTypeId,"","REPLACE");
        JsonPathAlteration alterationName = new JsonPathAlteration("$.testTypes[0].name", name,"","REPLACE");
        JsonPathAlteration alterationTestTypeName = new JsonPathAlteration("$.testTypes[0].testTypeName", testTypeName,"","REPLACE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(
                alterationVin,
                alterationSystemNumber,
                alterationTestResultId,
                alterationTestTypeId,
                alterationName,
                alterationTestTypeName
        ));

        // Post the results, together with any alterations, and verify that they are handled correctly.
        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, alterations);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");

        testResultsSteps.getTestResults(randomSystemNumber);
        testResultsSteps.statusCodeShouldBe(200);
        String testNumber = testResultsSteps.getTestNumber();

        //Verify that the certificate is generated in S3 bucket
        testResultsSteps.validateCertificateIsGenerated(testNumber,randomVin);
    }

    @Ignore("Cert generation switched off for ADR, wait untill CVSB-9134 is merged")
    @Title("CVSB-8798 / CVSB-3952 - As a VSA I want to be able to generate the ADR certificate so that I can receive it via email - HGV")
    public void testResultsADRCertificateHgv() {

        // Read the base test result JSON.
        String testResultRecord = test_results_ADR_HGV_json;

        // Create alteration to add one more tech record to in the request body
        String randomVin = GenericData.generateRandomVin();
        String randomSystemNumber = GenericData.generateRandomSystemNumber();
        String uuid = String.valueOf(UUID.randomUUID());
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin,"","REPLACE");
        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$", randomSystemNumber,"systemNumber","ADD_FIELD");
        JsonPathAlteration alterationTestResultId = new JsonPathAlteration("$.testResultId", uuid,"","REPLACE");
        JsonPathAlteration alterationTestTypeId = new JsonPathAlteration("$.testTypes[0].testTypeId", testTypeId,"","REPLACE");
        JsonPathAlteration alterationName = new JsonPathAlteration("$.testTypes[0].name", name,"","REPLACE");
        JsonPathAlteration alterationTestTypeName = new JsonPathAlteration("$.testTypes[0].testTypeName", testTypeName,"","REPLACE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(
                alterationVin,
                alterationSystemNumber,
                alterationTestResultId,
                alterationTestTypeId,
                alterationName,
                alterationTestTypeName
        ));

        // Post the results, together with any alterations, and verify that they are handled correctly.
        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, alterations);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");

        testResultsSteps.getTestResults(randomSystemNumber);
        testResultsSteps.statusCodeShouldBe(200);
        String testNumber = testResultsSteps.getTestNumber();

        //Verify that the certificate is generated in S3 bucket
        testResultsSteps.validateCertificateIsGenerated(testNumber,randomVin);
    }
}
