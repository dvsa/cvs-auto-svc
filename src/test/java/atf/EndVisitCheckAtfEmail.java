package atf;

import data.GenericData;
import exceptions.AutomationException;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import net.thucydides.core.annotations.WithTag;
import org.apache.commons.exec.environment.EnvironmentUtils;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import steps.ActivitiesSteps;
import steps.TestStationSteps;
import util.*;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SerenityRunner.class)
public class EndVisitCheckAtfEmail {

    @Steps
    TestStationSteps testStationSteps;

    private static final String FILE_PATH = "conf/environment.properties";


    @Title("CVSB-10530 - Check email to ATF lands in inbox")
    @Test
    public void endVisit_Check_Atf_Email() {

        //update station with proper email address
        testStationSteps.getTestStationsWithData();

        //insert visit in dynamo

//        String randomVin = GenericData.generateRandomVin();
//        String randomVrm = GenericData.generateRandomVrm();
//        String randomTestResultId = String.valueOf(UUID.randomUUID());
//        String json = GenericData.readJsonValueFromFile("test-results_roadworthiness_hgv_fail_7675.json", "$");
//        // create alteration to change Vin in the post request body with the random generated Vin
//        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
//        // create alteration to change primary vrm in the request body with the random generated primary vrm
//        JsonPathAlteration alterationVrm = new JsonPathAlteration("$.vrm", randomVrm, "", "REPLACE");
//        // create alteration to change test result id in the request body with the random generated test result id
//        JsonPathAlteration alterationTestResultId = new JsonPathAlteration("$.testResultId", randomTestResultId, "", "REPLACE");
//        // initialize the alterations list with only the alterations for changing the Vin and the primary vrm
//        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(alterationVin, alterationVrm, alterationTestResultId));
//        String alteredJson = GenericData.applyJsonAlterations(json, alterations);
//        activitiesSteps.insertRecordInDynamo(alteredJson, "test-results");
//
//
////        // Read the base test result JSON.
////        String testResultRecord = GenericData.readJsonValueFromFile("test-results_post_expiry_date_hgv_8684.json", "$");
////
////        String randomVin = GenericData.generateRandomVin();
////        String randomTestResultId = UUID.randomUUID().toString();
////
////        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
////        JsonPathAlteration alterationTestResultId = new JsonPathAlteration("$.testResultId", randomTestResultId, "", "REPLACE");
//        JsonPathAlteration alterationTestName = new JsonPathAlteration("$.testTypes[0].name", "First test","","REPLACE");
//        JsonPathAlteration alterationTestTypeId = new JsonPathAlteration("$.testTypes[0].testTypeId", "95","","REPLACE");
//        JsonPathAlteration alterationTestTypeName = new JsonPathAlteration("$.testTypes[0].testTypeName", "First Test","","REPLACE");
//        JsonPathAlteration alterationTestResult = new JsonPathAlteration("$.testTypes[0].testResult", "pass","","REPLACE");
//        JsonPathAlteration alterationNoOfAxles = new JsonPathAlteration("$.noOfAxles", 2,"","REPLACE");
//
//        EnvironmentType envType = TypeLoader.getType();
//        String emailAddress = "";
//        switch (envType) {
//            case CI_DEVELOP:
//                emailAddress = System.getenv("EMAIL_USERNAME");
//                break;
//            case LOCAL:
//                try {
//                    Properties properties = new Properties();
//                    properties.load(EnvironmentUtils.class.getClassLoader().getResourceAsStream(FILE_PATH));
//                    emailAddress = properties.getProperty("email.username");
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                break;
//            default:
//                throw new AutomationException("Environment configuration not found");
//        }
//        JsonPathAlteration alterationTesterEmailAddress = new JsonPathAlteration("$.testerEmailAddress", emailAddress,"","REPLACE");
//
//
//
//        // Collate the list of alterations.
////        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(
////                alterationVin,
////                alterationTestResultId,
////                alterationTestName,
////                alterationTestTypeId,
////                alterationTestTypeName,
////                alterationTestResult,
////                alterationNoOfAxles,
////                alterationTesterEmailAddress
////        ));
//
////        // Post the results, together with any alterations, and verify that they are accepted.
////        activitiesSteps.postVehicleTestResultsWithAlterations(testResultRecord, alterations);
////        activitiesSteps.statusCodeShouldBe(201);
////        activitiesSteps.validateData("Test records created");
////        activitiesSteps.getTestResults(randomVin);
////        activitiesSteps.statusCodeShouldBe(200);
////        activitiesSteps.valueForFieldInPathShouldBe("[0].testTypes[0].testCode", "ffv2");
//
//        //Verify email is sent to VSA
//        WebDriver driver  = activitiesSteps.validateAtfEmail(randomVin);
//
//        String pattern = "d MMMM yyyy";
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
//        String date = simpleDateFormat.format(new Date());
//
//        String actualString = driver.findElement(By.cssSelector("div[role='main']>div:nth-of-type(1)")).getText();
//        String expectedString = randomVin + " Annual test|" + date + " (Certificate 1 of 1)";
//        assertEquals(expectedString, actualString);
//
//        String actualString1 = driver.findElement(By.cssSelector("div[role='main']>div:nth-of-type(2)")).getText();
//        String expectedString1 = "Please see the link below to access the test certificate for vehicle(s) " + randomVin + " conducted on " + date;
//        assertTrue(actualString1.contains(expectedString1));
//        driver.quit();
    }
}
