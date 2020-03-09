package testresults;

import data.GenericData;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import net.thucydides.core.annotations.WithTag;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import steps.TestResultsSteps;
import steps.VehicleTechnicalRecordsSteps;
import util.*;

import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SerenityRunner.class)
public class PostTestResultsCheckVsaEmail {

    @Steps
    TestResultsSteps testResultsSteps;

    @Steps
    VehicleTechnicalRecordsSteps vehicleTechnicalRecordsSteps;

    @Title("CVSB-9194 - Check email to VSA lands in inbox")
    @Test
    public void testResults_Check_Vsa_Email() {

        // Read the base tech record JSON.
        String techRecord = GenericData.readJsonValueFromFile("technical-records_hgv_all_fields.json", "$");

        String randomVin = GenericData.generateRandomVin();
        String randomSystemNumber = GenericData.generateRandomSystemNumber();
        String randomVrm = GenericData.generateRandomVrmForEmailValidations();
        String randomTestResultId = UUID.randomUUID().toString();
        String testName = "First Test";

        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$.systemNumber", randomSystemNumber, "", "REPLACE");
        JsonPathAlteration alterationPrimaryVrm = new JsonPathAlteration("$.primaryVrm", randomVrm, "", "REPLACE");
        // Collate the list of alterations.
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(
                alterationVin,
                alterationPrimaryVrm,
                alterationSystemNumber));

        // create tech record
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(techRecord, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(201);
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecords(randomVin);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);

        // Read the base test result JSON.
        String testResultRecord = GenericData.readJsonValueFromFile("test-results_post_expiry_date_hgv_8684.json", "$");

        JsonPathAlteration alterationVrm = new JsonPathAlteration("$.vrm", randomVrm, "", "REPLACE");
        JsonPathAlteration alterationTestResultId = new JsonPathAlteration("$.testResultId", randomTestResultId, "", "REPLACE");
        JsonPathAlteration alterationTestName = new JsonPathAlteration("$.testTypes[0].name", testName,"","REPLACE");
        JsonPathAlteration alterationTestTypeId = new JsonPathAlteration("$.testTypes[0].testTypeId", "95","","REPLACE");
        JsonPathAlteration alterationTestTypeName = new JsonPathAlteration("$.testTypes[0].testTypeName", testName,"","REPLACE");
        JsonPathAlteration alterationTestResult = new JsonPathAlteration("$.testTypes[0].testResult", "pass","","REPLACE");
        JsonPathAlteration alterationNoOfAxles = new JsonPathAlteration("$.noOfAxles", 2,"","REPLACE");
        JsonPathAlteration alterationTesterName = new JsonPathAlteration("$.testerName", "VSA Email Check","","REPLACE");

        String emailAddress = testResultsSteps.getOutlookEmailAddress();
        JsonPathAlteration alterationTesterEmailAddress = new JsonPathAlteration("$.testerEmailAddress", emailAddress,"","REPLACE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterationsTr = new ArrayList<>(Arrays.asList(
                alterationVrm,
                alterationTestResultId,
                alterationTestName,
                alterationTestTypeId,
                alterationTestTypeName,
                alterationTestResult,
                alterationNoOfAxles,
                alterationTesterEmailAddress,
                alterationTesterName
        ));
        alterations.remove(1);
        alterations.addAll(alterationsTr);

        // Post the results, together with any alterations, and verify that they are accepted.
        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, alterations);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        testResultsSteps.getTestResults(randomSystemNumber);
        testResultsSteps.statusCodeShouldBe(200);
        testResultsSteps.valueForFieldInPathShouldBe("[0].testTypes[0].testCode", "ffv2");

        //Verify email is sent to VSA
        WebDriver driver  = testResultsSteps.validateVsaEmail(randomVrm);

        String pattern = "d MMMM yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(new Date());

        System.out.println("Checking the email title details are correct");
        String actualString = driver.findElement(By.cssSelector("div[role='main']>div:nth-of-type(1)")).getText();
        String expectedString = randomVrm + " " + testName + "|" + date + " (Certificate 1 of 1)";
        assertEquals(expectedString, actualString);

        System.out.println("Checking the details in the email are correct");
        String actualString1 = driver.findElement(By.cssSelector("div[role='main']>div:nth-of-type(2)")).getText();
        String expectedString1 = "Please see the link below to access the test certificate for vehicle(s) " + randomVrm + " conducted on " + date;
        assertTrue(actualString1.contains(expectedString1));

        driver.close();
        driver.quit();
    }
}
