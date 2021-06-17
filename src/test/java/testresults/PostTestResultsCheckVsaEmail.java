package testresults;

import data.GenericData;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import steps.*;
import util.*;
import java.text.SimpleDateFormat;
import java.util.*;

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
        String testResultRecord = GenericData.readJsonValueFromFile("test-results_post_expiry_date_hgv_8798.json", "$");

        JsonPathAlteration alterationVrm = new JsonPathAlteration("$.vrm", randomVrm, "", "REPLACE");
        JsonPathAlteration alterationTestResultId = new JsonPathAlteration("$.testResultId", randomTestResultId, "", "REPLACE");
        JsonPathAlteration alterationTestName = new JsonPathAlteration("$.testTypes[0].name", testName,"","REPLACE");
        JsonPathAlteration alterationTestTypeId = new JsonPathAlteration("$.testTypes[0].testTypeId", "95","","REPLACE");
        JsonPathAlteration alterationTestTypeName = new JsonPathAlteration("$.testTypes[0].testTypeName", testName,"","REPLACE");
        JsonPathAlteration alterationTesterName = new JsonPathAlteration("$.testerName", "VSA Email Check","","REPLACE");
        JsonPathAlteration alterationTestTypeStartTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeStartTimestamp", DataUtil.buildCurrentDateTime(), "", "REPLACE");

        String emailAddress = testResultsSteps.getOutlookEmailAddress();
        JsonPathAlteration alterationTesterEmailAddress = new JsonPathAlteration("$.testerEmailAddress", emailAddress,"","REPLACE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterationsTr = new ArrayList<>(Arrays.asList(
                alterationVrm,
                alterationTestResultId,
                alterationTestName,
                alterationTestTypeId,
                alterationTestTypeName,
                alterationTesterEmailAddress,
                alterationTesterName,
                alterationTestTypeStartTimestamp
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

        String pattern = "d MMMM yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(new Date());

        //Verify email is sent to VSA
        WebDriver driver  = testResultsSteps.validateVsaEmail(randomVrm);
        testResultsSteps.validateVsaEmailDetails(driver, randomVrm, testName, date);
    }
}
