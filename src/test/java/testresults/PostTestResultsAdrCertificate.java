package testresults;

import data.GenericData;
import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import net.thucydides.junit.annotations.TestData;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import steps.TestResultsSteps;
import util.JsonPathAlteration;

import java.util.*;

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

    @Ignore("Ignored until Certificate generation is no longer suppressed")
    @Title("CVSB-8798 / CVSB-3952 - As a VSA I want to be able to generate the ADR certificate so that I can receive it via email - TRL")
    public void testResultsADRCertificateTrl() {

        // Read the base test result JSON.
        String testResultRecord = GenericData.readJsonValueFromFile("test-results_ADR_TRL.json","$");

        // Create alteration to add one more tech record to in the request body
        String randomVin = GenericData.generateRandomVin();
        String uuid = String.valueOf(UUID.randomUUID());
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin,"","REPLACE");
        JsonPathAlteration alterationTestResultId = new JsonPathAlteration("$.testResultId", uuid,"","REPLACE");
        JsonPathAlteration alterationTestTypeId = new JsonPathAlteration("$.testTypes[0].testTypeId", testTypeId,"","REPLACE");
        JsonPathAlteration alterationName = new JsonPathAlteration("$.testTypes[0].name", name,"","REPLACE");
        JsonPathAlteration alterationTestTypeName = new JsonPathAlteration("$.testTypes[0].testTypeName", testTypeName,"","REPLACE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(
                alterationVin,
                alterationTestResultId,
                alterationTestTypeId,
                alterationName,
                alterationTestTypeName
        ));

        // Post the results, together with any alterations, and verify that they are handled correctly.
        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, alterations);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        testResultsSteps.validateCertificateIsGenerated(uuid, randomVin);
    }

    @Ignore("Ignored until Certificate generation is no longer suppressed")
    @Title("CVSB-8798 / CVSB-3952 - As a VSA I want to be able to generate the ADR certificate so that I can receive it via email - HGV")
    public void testResultsADRCertificateHgv() {

        // Read the base test result JSON.
        String testResultRecord = GenericData.readJsonValueFromFile("test-results_ADR_HGV.json","$");

        // Create alteration to add one more tech record to in the request body
        String randomVin = GenericData.generateRandomVin();
        String uuid = String.valueOf(UUID.randomUUID());
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin,"","REPLACE");
        JsonPathAlteration alterationTestResultId = new JsonPathAlteration("$.testResultId", uuid,"","REPLACE");
        JsonPathAlteration alterationTestTypeId = new JsonPathAlteration("$.testTypes[0].testTypeId", testTypeId,"","REPLACE");
        JsonPathAlteration alterationName = new JsonPathAlteration("$.testTypes[0].name", name,"","REPLACE");
        JsonPathAlteration alterationTestTypeName = new JsonPathAlteration("$.testTypes[0].testTypeName", testTypeName,"","REPLACE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(
                alterationVin,
                alterationTestResultId,
                alterationTestTypeId,
                alterationName,
                alterationTestTypeName
        ));

        // Post the results, together with any alterations, and verify that they are handled correctly.
        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, alterations);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        testResultsSteps.validateCertificateIsGenerated(uuid, randomVin);
    }

}
