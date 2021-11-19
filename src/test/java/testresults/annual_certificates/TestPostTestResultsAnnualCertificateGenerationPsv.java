package testresults.annual_certificates;

import data.GenericData;
import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.junit.annotations.TestData;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import steps.TestResultsSteps;
import util.JsonPathAlteration;

import java.util.*;

@RunWith(SerenityParameterizedRunner.class)
public class TestPostTestResultsAnnualCertificateGenerationPsv {

    @Steps
    TestResultsSteps testResultsSteps;

    @TestData
    public static Collection<Object[]> testData(){
        return Arrays.asList(new Object[][]{
                {"Annual test", "Annual test", "1", "large", "rigid", "pass", "aal"},
                {"Annual test", "Annual test", "1", "small", "rigid", "pass", "aas"},
                {"Annual test", "Annual test", "1", "large", "articulated", "pass", "adl"},
                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "large", "rigid", "pass", "wdl"},
                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "small", "rigid", "pass", "wds"},
                {"First test", "Class 6A seatbelt installation check (first test)", "4", "large", "rigid", "pass", "wbl"},
                {"First test", "Class 6A seatbelt installation check (first test)", "4", "small", "rigid", "pass", "wbs"},
                {"Any PSV", "Paid retest", "7", "large", "articulated", "pass", "rhl"},
                {"Any PSV", "Paid retest", "7", "small", "rigid", "pass", "rps"},
                {"Any PSV", "Paid retest", "7", "large", "rigid", "pass", "rpl"},
                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "large", "rigid", "pass", "whl"},
                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "small", "rigid", "pass", "whs"},
                {"Any PSV", "Part-paid retest", "10", "large", "articulated", "pass", "rgl"},
                {"Any PSV", "Part-paid retest", "10", "large", "rigid", "pass", "rsl"},
                {"Any PSV", "Part-paid retest", "10", "small", "rigid", "pass", "rss"},
                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "large", "rigid", "pass", "p1l"},
                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "small", "rigid", "pass", "p1s"},
                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "large", "rigid", "pass", "p8l"},
                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "small", "rigid", "pass", "p8s"},
                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "large", "rigid", "pass", "p6l"},
                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "small", "rigid", "pass", "p6s"},
                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "small", "rigid", "pass", "wis"},
                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "large", "rigid", "pass", "wil"},
                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "large", "rigid", "pass", "wfl"},
                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "small", "rigid", "pass", "wfs"},
                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "large", "rigid", "pass", "wel"},
                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "small", "rigid", "pass", "wes"},
                {"Annual test", "Annual test", "1", "large", "rigid", "prs", "aal"},
                {"Annual test", "Annual test", "1", "small", "rigid", "prs", "aas"},
                {"Annual test", "Annual test", "1", "large", "articulated", "prs", "adl"},
                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "large", "rigid", "prs", "wdl"},
                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "small", "rigid", "prs", "wds"},
                {"First test", "Class 6A seatbelt installation check (first test)", "4", "large", "rigid", "prs", "wbl"},
                {"First test", "Class 6A seatbelt installation check (first test)", "4", "small", "rigid", "prs", "wbs"},
                {"Any PSV", "Paid retest", "7", "large", "articulated", "prs", "rhl"},
                {"Any PSV", "Paid retest", "7", "small", "rigid", "prs", "rps"},
                {"Any PSV", "Paid retest", "7", "large", "rigid", "prs", "rpl"},
                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "large", "rigid", "prs", "whl"},
                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "small", "rigid", "prs", "whs"},
                {"Any PSV", "Part-paid retest", "10", "large", "articulated", "prs", "rgl"},
                {"Any PSV", "Part-paid retest", "10", "large", "rigid", "prs", "rsl"},
                {"Any PSV", "Part-paid retest", "10", "small", "rigid", "prs", "rss"},
                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "large", "rigid", "prs", "p1l"},
                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "small", "rigid", "prs", "p1s"},
                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "large", "rigid", "prs", "p8l"},
                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "small", "rigid", "prs", "p8s"},
                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "large", "rigid", "prs", "p6l"},
                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "small", "rigid", "prs", "p6s"},
                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "small", "rigid", "prs", "wis"},
                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "large", "rigid", "prs", "wil"},
                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "large", "rigid", "prs", "wfl"},
                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "small", "rigid", "prs", "wfs"},
                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "large", "rigid", "prs", "wel"},
                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "small", "rigid", "prs", "wes"}
        });
    }

    private String name;
    private String testTypeName;
    private String testTypeId;
    private String vehicleSize;
    private String vehicleConfiguration;
    private String testResult;
    private String testCode;

    public TestPostTestResultsAnnualCertificateGenerationPsv(String name, String testTypeName, String testTypeId, String vehicleSize, String vehicleConfiguration, String testResult, String testCode) {
        this.name = name;
        this.testTypeName = testTypeName;
        this.testTypeId = testTypeId;
        this.vehicleSize = vehicleSize;
        this.vehicleConfiguration = vehicleConfiguration;
        this.testResult = testResult;
        this.testCode = testCode;
    }

    
//    @WithTag("annual_certificates")
    @Title("CVSB-8798 - Annual certificate is generate for all Psv tests ")
    @Test
    public void testResults_Annual_Certificate_Generation_Psv() {

        // Read the base test result JSON.
        String testResultRecord = GenericData.readJsonValueFromFile("test-results_post_expiry_date_psv_8798.json", "$");

        String randomVin = GenericData.generateRandomVin();
        String randomSystemNumber = GenericData.generateRandomSystemNumber();
        String randomTestResultId = UUID.randomUUID().toString();


        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$.systemNumber", randomSystemNumber, "", "REPLACE");
        JsonPathAlteration alterationTestResultId = new JsonPathAlteration("$.testResultId", randomTestResultId, "", "REPLACE");
        JsonPathAlteration alterationTestName = new JsonPathAlteration("$.testTypes[0].name", name,"","REPLACE");
        JsonPathAlteration alterationTestTypeId = new JsonPathAlteration("$.testTypes[0].testTypeId", testTypeId,"","REPLACE");
        JsonPathAlteration alterationTestTypeName = new JsonPathAlteration("$.testTypes[0].testTypeName", testTypeName,"","REPLACE");
        JsonPathAlteration alterationTestResult = new JsonPathAlteration("$.testTypes[0].testResult", testResult,"","REPLACE");
        JsonPathAlteration alterationVehicleSize = new JsonPathAlteration("$.vehicleSize", vehicleSize,"","REPLACE");
        JsonPathAlteration alterationVehicleConfiguration = new JsonPathAlteration("$.vehicleConfiguration", vehicleConfiguration,"","REPLACE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(
                alterationVin,
                alterationTestResultId,
                alterationTestName,
                alterationTestTypeId,
                alterationTestTypeName,
                alterationVehicleSize,
                alterationVehicleConfiguration,
                alterationTestResult,
                alterationSystemNumber
        ));

        // Post the results, together with any alterations, and verify that they are accepted.
        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, alterations);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        testResultsSteps.getTestResults(randomSystemNumber);
        testResultsSteps.statusCodeShouldBe(200);
        String testNumber = testResultsSteps.getTestNumber();
        testResultsSteps.valueForFieldInPathShouldBe("[0].testTypes[0].testCode", testCode);
        Assert.assertTrue(testResultsSteps.validateCertificateNumberLength());

        //Verify that the certificate is generated in S3 bucket
        testResultsSteps.validateCertificateIsGenerated(testNumber,randomVin);

    }
}
