package activities;

import data.GenericData;
import data.TestResultsData;
import exceptions.AutomationException;
import model.testresults.TestResults;
import model.testresults.TestResultsGet;
import model.testresults.TestResultsStatus;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import net.thucydides.core.annotations.WithTag;
import org.apache.commons.exec.environment.EnvironmentUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import steps.TestResultsSteps;
import util.*;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SerenityRunner.class)
public class PutActivitiesCheckAtfEmail {

    @Steps
    TestResultsSteps testResultsSteps;

    private static final String FILE_PATH = "conf/environment.properties";

    @WithTag("In_Test")
    @Title("CVSB-10530 - Check email to ATF lands in inbox")
    @Test
    public void testResults_Check_Vsa_Email() {

        // Read the base test result JSON.
        String testResultRecord = GenericData.readJsonValueFromFile("test-results_post_expiry_date_hgv_8684.json", "$");

        String randomVin = GenericData.generateRandomVin();
        String randomTestResultId = UUID.randomUUID().toString();

        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationTestResultId = new JsonPathAlteration("$.testResultId", randomTestResultId, "", "REPLACE");
        JsonPathAlteration alterationTestName = new JsonPathAlteration("$.testTypes[0].name", "First test","","REPLACE");
        JsonPathAlteration alterationTestTypeId = new JsonPathAlteration("$.testTypes[0].testTypeId", "95","","REPLACE");
        JsonPathAlteration alterationTestTypeName = new JsonPathAlteration("$.testTypes[0].testTypeName", "First Test","","REPLACE");
        JsonPathAlteration alterationTestResult = new JsonPathAlteration("$.testTypes[0].testResult", "pass","","REPLACE");
        JsonPathAlteration alterationNoOfAxles = new JsonPathAlteration("$.noOfAxles", 2,"","REPLACE");

        EnvironmentType envType = TypeLoader.getType();
        String emailAddress = "";
        switch (envType) {
            case CI_DEVELOP:
                emailAddress = System.getenv("EMAIL_USERNAME");
                break;
            case LOCAL:
                try {
                    Properties properties = new Properties();
                    properties.load(EnvironmentUtils.class.getClassLoader().getResourceAsStream(FILE_PATH));
                    emailAddress = properties.getProperty("email.username");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            default:
                throw new AutomationException("Environment configuration not found");
        }
        JsonPathAlteration alterationTesterEmailAddress = new JsonPathAlteration("$.testerEmailAddress", emailAddress,"","REPLACE");



        // Collate the list of alterations.
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(
                alterationVin,
                alterationTestResultId,
                alterationTestName,
                alterationTestTypeId,
                alterationTestTypeName,
                alterationTestResult,
                alterationNoOfAxles,
                alterationTesterEmailAddress
        ));

        // Post the results, together with any alterations, and verify that they are accepted.
        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, alterations);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        testResultsSteps.getTestResults(randomVin);
        testResultsSteps.statusCodeShouldBe(200);
        testResultsSteps.valueForFieldInPathShouldBe("[0].testTypes[0].testCode", "ffv2");

        //Verify email is sent to VSA
        WebDriver driver  = testResultsSteps.validateAtfEmail(randomVin);

        String pattern = "d MMMM yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(new Date());

        String actualString = driver.findElement(By.cssSelector("div[role='main']>div:nth-of-type(1)")).getText();
        String expectedString = randomVin + " Annual test|" + date + " (Certificate 1 of 1)";
        assertEquals(expectedString, actualString);

        String actualString1 = driver.findElement(By.cssSelector("div[role='main']>div:nth-of-type(2)")).getText();
        String expectedString1 = "Please see the link below to access the test certificate for vehicle(s) " + randomVin + " conducted on " + date;
        assertTrue(actualString1.contains(expectedString1));
        driver.quit();
    }
}
