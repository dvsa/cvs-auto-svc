package testresults;

import data.GenericData;
import model.vehicles.VehicleTechnicalRecordStatus;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import steps.*;
import util.JsonPathAlteration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@RunWith(SerenityRunner.class)
public class PostTestResultsMoreVinFirstTest {

    @Steps
    TestResultsSteps testResultsSteps;

    @Steps
    VehicleTechnicalRecordsSteps vehicleTechnicalRecordsSteps;


    @Title("CVSB-12445 - TC - AC1 - VSA submits First test = FAIL - HGV")
    @Test
    public void testResultsFirstTestHgvFail() {

        //generate random Vin
        String randomVin = RandomStringUtils.randomAlphanumeric(14).toUpperCase();
        //generate two random systemNumber
        String systemNumberOne;
        String systemNumberTwo;
        //generate random Vrm
        String randomVrm = (RandomStringUtils.randomAlphabetic(1) + RandomStringUtils.randomNumeric(2) + RandomStringUtils.randomAlphabetic(3)).toUpperCase();
        // read post request body from file
        String postRequestBody = GenericData.readJsonValueFromFile("technical-records_hgv_mandatory_fields.json","$");
        // create alteration to change Vin in the request body with the random generated Vin
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin,"","REPLACE");
        // create alteration to change primary vrm in the request body with the random generated primary vrm
        JsonPathAlteration alterationVrm = new JsonPathAlteration("$.primaryVrm", randomVrm,"","REPLACE");
         // create alteration to change partial vin in the request body with the random generated primary vrm
        JsonPathAlteration alterationPartialVin = new JsonPathAlteration("$.partialVin", randomVin.substring(randomVin.length() - 6),"","REPLACE");

        // initialize the alterations lists with declared alteration
        List<JsonPathAlteration> alterationsOne = new ArrayList<>(Arrays.asList(
                alterationVin,
                alterationVrm,
                alterationPartialVin
        ));

        List<JsonPathAlteration> alterationsTwo = new ArrayList<>(Arrays.asList(
                alterationVin,
                alterationVrm,
                alterationPartialVin
        ));

        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBody, alterationsOne);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(201);
        systemNumberOne = vehicleTechnicalRecordsSteps.getSystemNumberUsingVin(randomVin);

        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBody, alterationsTwo);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(201);
        systemNumberTwo = vehicleTechnicalRecordsSteps.getSystemNumberUsingVin(randomVin);

        // retrieve the vehicle and check the status code and the techRecord size
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsByStatus(randomVin, VehicleTechnicalRecordStatus.ALL);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("$.size()", 2);
        vehicleTechnicalRecordsSteps.valueForFieldInTechRecordShouldBe(systemNumberOne, randomVin, 0, "statusCode", "provisional");
        vehicleTechnicalRecordsSteps.valueForFieldInTechRecordShouldBe(systemNumberTwo, randomVin, 0, "statusCode", "provisional");

        // build and post a notifiable alteration test results

        // read the base test result JSON.
        String testResultRecord = GenericData.readJsonValueFromFile("test-results_first_test_hgv.json","$");
        String testResultId = UUID.randomUUID().toString();
        String testResult = "fail";

        // Create alteration to add one more tech record to in the request body
        JsonPathAlteration trAlterationVin = new JsonPathAlteration("$.vin", randomVin,"","REPLACE");
        JsonPathAlteration trAlterationVrm = new JsonPathAlteration("$.vrm", randomVrm,"","REPLACE");
        JsonPathAlteration trAlterationSystemNumber = new JsonPathAlteration("$.systemNumber", systemNumberOne,"","REPLACE");
        JsonPathAlteration trAlterationTestResultId = new JsonPathAlteration("$.testResultId", testResultId,"","REPLACE");
        JsonPathAlteration trAlterationTestResult = new JsonPathAlteration("$.testTypes[0].testResult", testResult,"","REPLACE");


        // Collate the list of alterations.
        List<JsonPathAlteration> trAlterations = new ArrayList<>(Arrays.asList(
                trAlterationVin,
                trAlterationVrm,
                trAlterationTestResultId,
                trAlterationSystemNumber,
                trAlterationTestResult
        ));

        // Post the results, together with any alterations, and verify that they are accepted.
        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, trAlterations);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");

        // wait a maximum of seconds for the vehicle tech records to be updated
        vehicleTechnicalRecordsSteps.waitForVehicleTechRecordsToBeUpdated(randomVin, 20);

        // retrieve the tech record of the vehicle and verify whether the status has changed to current
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsByStatus(randomVin, VehicleTechnicalRecordStatus.ALL);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("$.size()", 2);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord.size()", 1);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[1].techRecord.size()", 1);

        vehicleTechnicalRecordsSteps.valueForFieldInTechRecordShouldBe(systemNumberOne, randomVin, 0, "statusCode", "provisional");
        vehicleTechnicalRecordsSteps.valueForFieldInAnyTechRecordShouldBe(systemNumberTwo, randomVin, "statusCode", "provisional");
    }


    @Title("CVSB-12445 - TC - AC1 - VSA submits First test = PASS - HGV")
    @Test
    public void testResultsFirstTestHgvPass() {

        //generate random Vin
        String randomVin = RandomStringUtils.randomAlphanumeric(14).toUpperCase();
        //generate two random systemNumber
        String systemNumberOne;
        String systemNumberTwo;
        //generate random Vrm
        String randomVrm = (RandomStringUtils.randomAlphabetic(1) + RandomStringUtils.randomNumeric(2) + RandomStringUtils.randomAlphabetic(3)).toUpperCase();
        // read post request body from file
        String postRequestBody = GenericData.readJsonValueFromFile("technical-records_hgv_mandatory_fields.json", "$");
        // create alteration to change Vin in the request body with the random generated Vin
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        // create alteration to change primary vrm in the request body with the random generated primary vrm
        JsonPathAlteration alterationVrm = new JsonPathAlteration("$.primaryVrm", randomVrm, "", "REPLACE");
        // create alteration to change partial vin in the request body with the random generated primary vrm
        JsonPathAlteration alterationPartialVin = new JsonPathAlteration("$.partialVin", randomVin.substring(randomVin.length() - 6), "", "REPLACE");

        // initialize the alterations lists with declared alteration
        List<JsonPathAlteration> alterationsOne = new ArrayList<>(Arrays.asList(
                alterationVin,
                alterationVrm,
                alterationPartialVin
        ));

        List<JsonPathAlteration> alterationsTwo = new ArrayList<>(Arrays.asList(
                alterationVin,
                alterationVrm,
                alterationPartialVin
        ));

        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBody, alterationsOne);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(201);
        systemNumberOne = vehicleTechnicalRecordsSteps.getSystemNumberUsingVin(randomVin);

        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBody, alterationsTwo);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(201);
        systemNumberTwo = vehicleTechnicalRecordsSteps.getSystemNumberUsingVin(randomVin);

        // retrieve the vehicle and check the status code and the techRecord size
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsByStatus(randomVin, VehicleTechnicalRecordStatus.ALL);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("$.size()", 2);
        vehicleTechnicalRecordsSteps.valueForFieldInTechRecordShouldBe(systemNumberOne, randomVin, 0, "statusCode", "provisional");
        vehicleTechnicalRecordsSteps.valueForFieldInTechRecordShouldBe(systemNumberTwo, randomVin, 0, "statusCode", "provisional");

        // build and post a notifiable alteration test results

        // read the base test result JSON.
        String testResultRecord = GenericData.readJsonValueFromFile("test-results_first_test_hgv.json", "$");
        String testResultId = UUID.randomUUID().toString();
        String testResult = "pass";

        // Create alteration to add one more tech record to in the request body
        JsonPathAlteration trAlterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration trAlterationVrm = new JsonPathAlteration("$.vrm", randomVrm, "", "REPLACE");
        JsonPathAlteration trAlterationSystemNumber = new JsonPathAlteration("$.systemNumber", systemNumberOne, "", "REPLACE");
        JsonPathAlteration trAlterationTestResultId = new JsonPathAlteration("$.testResultId", testResultId, "", "REPLACE");
        JsonPathAlteration trAlterationTestResult = new JsonPathAlteration("$.testTypes[0].testResult", testResult, "", "REPLACE");


        // Collate the list of alterations.
        List<JsonPathAlteration> trAlterations = new ArrayList<>(Arrays.asList(
                trAlterationVin,
                trAlterationVrm,
                trAlterationTestResultId,
                trAlterationSystemNumber,
                trAlterationTestResult
        ));

        // Post the results, together with any alterations, and verify that they are accepted.
        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, trAlterations);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");

        // wait a maximum of seconds for the vehicle tech records to be updated
        vehicleTechnicalRecordsSteps.waitForVehicleTechRecordsToBeUpdated(randomVin, 20);

        // retrieve the tech record of the vehicle and verify whether the status has changed to current
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsByStatus(randomVin, VehicleTechnicalRecordStatus.ALL);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("$.size()", 2);

        vehicleTechnicalRecordsSteps.valueForFieldInAnyTechRecordShouldBe(systemNumberOne, randomVin, "statusCode", "archived");
        vehicleTechnicalRecordsSteps.valueForFieldInAnyTechRecordShouldBe(systemNumberOne, randomVin, "statusCode", "current");
    }


    @Title("CVSB-12445 - TC - AC1 - VSA submits First test = FAIL - TRL")
    @Test
    public void testResultsFirstTestTrlFail() {

        //generate random Vin
        String randomVin = RandomStringUtils.randomAlphanumeric(14).toUpperCase();
        //generate random Vrm
        String randomVrm = (RandomStringUtils.randomAlphabetic(1) + RandomStringUtils.randomNumeric(2) + RandomStringUtils.randomAlphabetic(3)).toUpperCase();
        // read post request body from file
        String postRequestBody = GenericData.readJsonValueFromFile("technical-records_trl_all_fields.json","$");
        // create alteration to change Vin in the request body with the random generated Vin
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin,"","REPLACE");
        // create alteration to change primary vrm in the request body with the random generated primary vrm
        JsonPathAlteration alterationVrm = new JsonPathAlteration("$.primaryVrm", randomVrm,"","REPLACE");
        // create alteration to change partial vin in the request body with the random generated primary vrm
        JsonPathAlteration alterationPartialVin = new JsonPathAlteration("$.partialVin", randomVin.substring(randomVin.length() - 6),"","REPLACE");

        // initialize the alterations lists with declared alteration
        List<JsonPathAlteration> alterationsOne = new ArrayList<>(Arrays.asList(
                alterationVin,
                alterationVrm,
                alterationPartialVin
        ));

        List<JsonPathAlteration> alterationsTwo = new ArrayList<>(Arrays.asList(
                alterationVin,
                alterationVrm,
                alterationPartialVin
        ));

        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBody, alterationsOne);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(201);
        String systemNumberOne = vehicleTechnicalRecordsSteps.getSystemNumberUsingVin(randomVin);

        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBody, alterationsTwo);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(201);
        String systemNumberTwo = vehicleTechnicalRecordsSteps.getSystemNumberUsingVin(randomVin);

        // retrieve the vehicle and check the status code and the techRecord size
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsByStatus(randomVin, VehicleTechnicalRecordStatus.ALL);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("$.size()", 2);
        vehicleTechnicalRecordsSteps.valueForFieldInTechRecordShouldBe(systemNumberOne, randomVin, 0, "statusCode", "provisional");
        vehicleTechnicalRecordsSteps.valueForFieldInTechRecordShouldBe(systemNumberTwo, randomVin, 0, "statusCode", "provisional");

        // build and post a notifiable alteration test results

        // read the base test result JSON.
        String testResultRecord = GenericData.readJsonValueFromFile("test-results_first_test_trl.json","$");
        String testResultId = UUID.randomUUID().toString();
        String testResult = "fail";

        // Create alteration to add one more tech record to in the request body
        JsonPathAlteration trAlterationVin = new JsonPathAlteration("$.vin", randomVin,"","REPLACE");
        JsonPathAlteration trAlterationVrm = new JsonPathAlteration("$.vrm", randomVrm,"","REPLACE");
        JsonPathAlteration trAlterationSystemNumber = new JsonPathAlteration("$.systemNumber", systemNumberOne,"","REPLACE");
        JsonPathAlteration trAlterationTestResultId = new JsonPathAlteration("$.testResultId", testResultId,"","REPLACE");
        JsonPathAlteration trAlterationTestResult = new JsonPathAlteration("$.testTypes[0].testResult", testResult,"","REPLACE");


        // Collate the list of alterations.
        List<JsonPathAlteration> trAlterations = new ArrayList<>(Arrays.asList(
                trAlterationVin,
                trAlterationVrm,
                trAlterationTestResultId,
                trAlterationSystemNumber,
                trAlterationTestResult
        ));

        // Post the results, together with any alterations, and verify that they are accepted.
        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, trAlterations);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");

        // wait a maximum of seconds for the vehicle tech records to be updated
        vehicleTechnicalRecordsSteps.waitForVehicleTechRecordsToBeUpdated(randomVin, 20);

        // retrieve the tech record of the vehicle and verify whether the status has changed to current
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsByStatus(randomVin, VehicleTechnicalRecordStatus.ALL);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("$.size()", 2);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord.size()", 1);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[1].techRecord.size()", 1);

        vehicleTechnicalRecordsSteps.valueForFieldInTechRecordShouldBe(systemNumberOne, randomVin, 0, "statusCode", "provisional");
        vehicleTechnicalRecordsSteps.valueForFieldInAnyTechRecordShouldBe(systemNumberTwo, randomVin, "statusCode", "provisional");
    }


    @Title("CVSB-12445 - TC - AC1 - VSA submits First test = PASS - TRL")
    @Test
    public void testResultsFirstTestTrlPass() {

        //generate random Vin
        String randomVin = RandomStringUtils.randomAlphanumeric(14).toUpperCase();
        //generate random Vrm
        String randomVrm = (RandomStringUtils.randomAlphabetic(1) + RandomStringUtils.randomNumeric(2) + RandomStringUtils.randomAlphabetic(3)).toUpperCase();
        // read post request body from file
        String postRequestBody = GenericData.readJsonValueFromFile("technical-records_trl_all_fields.json","$");
        // create alteration to change Vin in the request body with the random generated Vin
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin,"","REPLACE");
        // create alteration to change primary vrm in the request body with the random generated primary vrm
        JsonPathAlteration alterationVrm = new JsonPathAlteration("$.primaryVrm", randomVrm,"","REPLACE");
        // create alteration to change partial vin in the request body with the random generated primary vrm
        JsonPathAlteration alterationPartialVin = new JsonPathAlteration("$.partialVin", randomVin.substring(randomVin.length() - 6),"","REPLACE");

        // initialize the alterations lists with declared alteration
        List<JsonPathAlteration> alterationsOne = new ArrayList<>(Arrays.asList(
                alterationVin,
                alterationVrm,
                alterationPartialVin
        ));

        List<JsonPathAlteration> alterationsTwo = new ArrayList<>(Arrays.asList(
                alterationVin,
                alterationVrm,
                alterationPartialVin
        ));

        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBody, alterationsOne);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(201);
        String systemNumberOne = vehicleTechnicalRecordsSteps.getSystemNumberUsingVin(randomVin);

        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBody, alterationsTwo);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(201);
        String systemNumberTwo = vehicleTechnicalRecordsSteps.getSystemNumberUsingVin(randomVin);

        // retrieve the vehicle and check the status code and the techRecord size
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsByStatus(randomVin, VehicleTechnicalRecordStatus.ALL);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("$.size()", 2);
        vehicleTechnicalRecordsSteps.valueForFieldInTechRecordShouldBe(systemNumberOne, randomVin, 0, "statusCode", "provisional");
        vehicleTechnicalRecordsSteps.valueForFieldInTechRecordShouldBe(systemNumberTwo, randomVin, 0, "statusCode", "provisional");

        // build and post a notifiable alteration test results

        // read the base test result JSON.
        String testResultRecord = GenericData.readJsonValueFromFile("test-results_first_test_trl.json","$");
        String testResultId = UUID.randomUUID().toString();
        String testResult = "pass";

        // Create alteration to add one more tech record to in the request body
        JsonPathAlteration trAlterationVin = new JsonPathAlteration("$.vin", randomVin,"","REPLACE");
        JsonPathAlteration trAlterationVrm = new JsonPathAlteration("$.vrm", randomVrm,"","REPLACE");
        JsonPathAlteration trAlterationSystemNumber = new JsonPathAlteration("$.systemNumber", systemNumberOne,"","REPLACE");
        JsonPathAlteration trAlterationTestResultId = new JsonPathAlteration("$.testResultId", testResultId,"","REPLACE");
        JsonPathAlteration trAlterationTestResult = new JsonPathAlteration("$.testTypes[0].testResult", testResult,"","REPLACE");


        // Collate the list of alterations.
        List<JsonPathAlteration> trAlterations = new ArrayList<>(Arrays.asList(
                trAlterationVin,
                trAlterationVrm,
                trAlterationTestResultId,
                trAlterationSystemNumber,
                trAlterationTestResult
        ));

        // Post the results, together with any alterations, and verify that they are accepted.
        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, trAlterations);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");

        // wait a maximum of seconds for the vehicle tech records to be updated
        vehicleTechnicalRecordsSteps.waitForVehicleTechRecordsToBeUpdated(randomVin, 20);

        // retrieve the tech record of the vehicle and verify whether the status has changed to current
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsByStatus(randomVin, VehicleTechnicalRecordStatus.ALL);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("$.size()", 2);

        vehicleTechnicalRecordsSteps.valueForFieldInAnyTechRecordShouldBe(systemNumberOne, randomVin, "statusCode", "archived");
        vehicleTechnicalRecordsSteps.valueForFieldInAnyTechRecordShouldBe(systemNumberOne, randomVin, "statusCode", "current");

    }
}
