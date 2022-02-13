package testresults;

import data.GenericData;
import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import net.thucydides.junit.annotations.TestData;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import steps.TestResultsSteps;
import util.JsonPathAlteration;

import java.util.*;

@RunWith(SerenityParameterizedRunner.class)
public class PostTestResultsTirNegativeHgv {

    @Steps
    TestResultsSteps testResultsSteps;

    @TestData
    public static Collection<Object[]> testData(){
        return Arrays.asList(new Object[][]{
                {"49", "Technical test", "TIR test", ""},
                {"56", "Retest", "Paid TIR retest", ""},
        });
    }

    public PostTestResultsTirNegativeHgv(String testTypeId, String name, String testTypeName, String certificateNumber) {
        this.testTypeId = testTypeId;
        this.name = name;
        this.testTypeName = testTypeName;
        this.certificateNumber = certificateNumber;
    }

    private String testTypeId;
    private String name;
    private String testTypeName;
    private String certificateNumber;

    private String technical_records_tir_hgv_json;
    @Before
    @Test
    public void updateJson(){
        String jsonFileName = "technical-records_tir_hgv.json";
        technical_records_tir_hgv_json = GenericData.updateJson(testResultsSteps, jsonFileName, "$");
    }

    @Title("CVSB-3946 - API changes related to test records to enable TIR testing - negative - empty certificateNumber - HGV")
    @Test
    public void testTirCertificateHgv() {

        // Read the base test result JSON.
        String testResultRecord = technical_records_tir_hgv_json;

        // Create alteration to add one more tech record to in the request body
        String randomVin = GenericData.generateRandomVin();
        String randomTestResultId = UUID.randomUUID().toString();
        String randomSystemNumber = GenericData.generateRandomSystemNumber();
        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$.systemNumber",randomSystemNumber ,"","REPLACE");
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin,"","REPLACE");
        JsonPathAlteration alterationTestResultId = new JsonPathAlteration("$.testResultId", randomTestResultId,"","REPLACE");
        JsonPathAlteration alterationTestTypeId = new JsonPathAlteration("$.testTypes[0].testTypeId", testTypeId,"","REPLACE");
        JsonPathAlteration alterationName = new JsonPathAlteration("$.testTypes[0].name", name,"","REPLACE");
        JsonPathAlteration alterationTestTypeName = new JsonPathAlteration("$.testTypes[0].testTypeName", testTypeName,"","REPLACE");
        JsonPathAlteration alterationCertificateNumber = new JsonPathAlteration("$.testTypes[0].certificateNumber", certificateNumber,"","REPLACE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(
                alterationVin,
                alterationSystemNumber,
                alterationTestResultId,
                alterationTestTypeId,
                alterationName,
                alterationTestTypeName,
                alterationCertificateNumber));

        // Post the results, together with any alterations, and verify that they are handled correctly.
        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, alterations);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validateData("Certificate number not present on TIR test type");

    }

}
