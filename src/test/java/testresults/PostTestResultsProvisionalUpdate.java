package testresults;

import data.GenericData;
import model.vehicles.VehicleTechnicalRecordStatus;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import net.thucydides.core.annotations.WithTag;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import steps.*;
import util.JsonPathAlteration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@RunWith(SerenityRunner.class)
public class PostTestResultsProvisionalUpdate {

    @Steps
    TestResultsSteps testResultsSteps;

    @Steps
    VehicleTechnicalRecordsSteps vehicleTechnicalRecordsSteps;


    @Title("CVSB-4867 - AC2 - VSA submits notifiable alteration test = PASS")
    @Test
    public void testResultsNotifiableAlteration_Pass() {

        //generate random Vin
        String randomVin = RandomStringUtils.randomAlphanumeric(14).toUpperCase();
        String systemNumber;
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

        // initialize the alterations list with both declared alteration
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(
                alterationVin,
                alterationVrm,
                alterationPartialVin
        ));

        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBody, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(201);
        // retrieve the vehicle and check the status code and the techRecord size
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsByStatus(randomVin, VehicleTechnicalRecordStatus.ALL);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].vin", randomVin);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].statusCode", "provisional");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord.size()", 1);
        systemNumber = vehicleTechnicalRecordsSteps.getValueFromBody("[0].systemNumber");

        // build and post a notifiable alteration test results

        // read the base test result JSON.
        String testResultRecord = GenericData.readJsonValueFromFile("test-results_notifiable_alteration_hgv.json","$");

        String testResultId = UUID.randomUUID().toString();

        // Create alteration to add one more tech record to in the request body
        JsonPathAlteration trAlterationVin = new JsonPathAlteration("$.vin", randomVin,"","REPLACE");
        JsonPathAlteration trAlterationVrm = new JsonPathAlteration("$.vrm", randomVrm,"","REPLACE");
        JsonPathAlteration trAlterationSystemNumber = new JsonPathAlteration("$.systemNumber", systemNumber,"","REPLACE");
        JsonPathAlteration trAlterationTestResultId = new JsonPathAlteration("$.testResultId", testResultId,"","REPLACE");


        // Collate the list of alterations.
        List<JsonPathAlteration> trAlterations = new ArrayList<>(Arrays.asList(
                trAlterationVin,
                trAlterationVrm,
                trAlterationTestResultId,
                trAlterationSystemNumber
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
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].vin", randomVin);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].systemNumber", systemNumber);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord.size()", 2);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe
                ("[0].techRecord.findAll { it.statusCode == 'archived' }.size()", 1);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe
                ("[0].techRecord.findAll { it.statusCode == 'current' }.size()", 1);

    }

    @Title("CVSB-11333 - 'EU vehicle category' updated - NULL - HGV")
    @Test
    public void testVehicleTechRecordHgvEuVehicleCategoryNull(){

        // Tech record exists already in dynamoDb with a null euVehicleCategory
        String systemNumber = "XYZEP5JYOMM00001";
        String vin = "DP76UMK4DQLTOT400001";

        // Get the created technical record, verify the status code and the fields
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsBySystemNumber(systemNumber);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].euVehicleCategory", null);


        // Read test result base json + Generate random values
        String testResultRecord = GenericData.readJsonValueFromFile("test-results_roadworthiness_hgv_pass_7675.json", "$");
        String randomTestResultId = UUID.randomUUID().toString();
        JsonPathAlteration alterationTestResultVin = new JsonPathAlteration("$.vin", vin, "", "REPLACE");
        JsonPathAlteration alterationTestResultId = new JsonPathAlteration("$.testResultId", randomTestResultId, "", "REPLACE");
        JsonPathAlteration trAlterationSystemNumber = new JsonPathAlteration("$.systemNumber", systemNumber,"","REPLACE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterationsTestResult = new ArrayList<>(Arrays.asList(
                alterationTestResultVin,
                alterationTestResultId,
                trAlterationSystemNumber));

        // Post the results, together with any alterations, and verify that they are accepted.
        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, alterationsTestResult);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");

        // Wait for the vehicle tech records to be updated
        vehicleTechnicalRecordsSteps.waitForVehicleTechRecordsToBeUpdated(vin, 10);


        // Get the tech record, and verify that the fields are present.
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsBySystemNumber(systemNumber);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].euVehicleCategory", "n2");
    }

    @Title("CVSB-11333 - 'EU vehicle category' updated - NULL - PSV")
    @Test
    public void testVehicleTechRecordPsvEuVehicleCategoryNull(){

        // Tech record exists already in dynamoDb with a null euVehicleCategory
        String systemNumber = "XYZEP5JYOMM00002";
        String vin = "DP76UMK4DQLTOT400002";

        // Get the created technical record, verify the status code and the fields
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsBySystemNumber(systemNumber);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].euVehicleCategory", null);


        // Read test result base json + Generate random values
        String testResultRecord = GenericData.readJsonValueFromFile("test-results_psv.json", "$");
        String randomTestResultId = UUID.randomUUID().toString();
        JsonPathAlteration alterationTestResultVin = new JsonPathAlteration("$.vin", vin, "", "REPLACE");
        JsonPathAlteration alterationTestResultId = new JsonPathAlteration("$.testResultId", randomTestResultId, "", "REPLACE");
        JsonPathAlteration trAlterationSystemNumber = new JsonPathAlteration("$.systemNumber", systemNumber,"","REPLACE");
        JsonPathAlteration trEuVehicleCategory = new JsonPathAlteration("$.euVehicleCategory", "m2","","REPLACE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterationsTestResult = new ArrayList<>(Arrays.asList(
                alterationTestResultVin,
                alterationTestResultId,
                trAlterationSystemNumber,
                trEuVehicleCategory));

        // Post the results, together with any alterations, and verify that they are accepted.
        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, alterationsTestResult);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");

        // Wait for the vehicle tech records to be updated
        vehicleTechnicalRecordsSteps.waitForVehicleTechRecordsToBeUpdated(vin, 10);


        // Get the tech record, and verify that the fields are present.
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsBySystemNumber(systemNumber);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].euVehicleCategory", "m2");
    }

    @Title("CVSB-11333 - 'EU vehicle category' updated - NULL - TRL")
    @Test
    public void testVehicleTechRecordTrlEuVehicleCategoryNull(){

        // Tech record exists already in dynamoDb with a null euVehicleCategory
        String systemNumber = "XYZEP5JYOMM00003";
        String vin = "DP76UMK4DQLTOT400003";

        // Get the created technical record, verify the status code and the fields
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsBySystemNumber(systemNumber);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].euVehicleCategory", null);


        // Read test result base json + Generate random values
        String testResultRecord = GenericData.readJsonValueFromFile("test-results_roadworthiness_trl_pass_7675.json", "$");
        String randomTestResultId = UUID.randomUUID().toString();
        JsonPathAlteration alterationTestResultVin = new JsonPathAlteration("$.vin", vin, "", "REPLACE");
        JsonPathAlteration alterationTestResultId = new JsonPathAlteration("$.testResultId", randomTestResultId, "", "REPLACE");
        JsonPathAlteration trAlterationSystemNumber = new JsonPathAlteration("$.systemNumber", systemNumber,"","REPLACE");
        JsonPathAlteration trEuVehicleCategory = new JsonPathAlteration("$.euVehicleCategory", "o2","","REPLACE");


        // Collate the list of alterations.
        List<JsonPathAlteration> alterationsTestResult = new ArrayList<>(Arrays.asList(
                alterationTestResultVin,
                alterationTestResultId,
                trAlterationSystemNumber,
                trEuVehicleCategory));

        // Post the results, together with any alterations, and verify that they are accepted.
        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, alterationsTestResult);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");

        // Wait for the vehicle tech records to be updated
        vehicleTechnicalRecordsSteps.waitForVehicleTechRecordsToBeUpdated(vin, 10);


        // Get the tech record, and verify that the fields are present.
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsBySystemNumber(systemNumber);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].euVehicleCategory", "o2");
    }

    @WithTag("In_test")
    @Title("CVSB-11333 - 'EU vehicle category' updated - NULL - LGV")
    @Test
    public void testVehicleTechRecordLgvEuVehicleCategoryNull(){

        // Tech record exists already in dynamoDb with a null euVehicleCategory
        String systemNumber = "XYZEP5JYOMM00004";
        String vin = "DP76UMK4DQLTOT400004";

        // Get the created technical record, verify the status code and the fields
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsBySystemNumber(systemNumber);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].euVehicleCategory", null);


        // Read test result base json + Generate random values
        String testResultRecord = GenericData.readJsonValueFromFile("test-results_lgv.json", "$");
        String randomTestResultId = UUID.randomUUID().toString();
        JsonPathAlteration alterationTestResultVin = new JsonPathAlteration("$.vin", vin, "", "REPLACE");
        JsonPathAlteration alterationTestResultId = new JsonPathAlteration("$.testResultId", randomTestResultId, "", "REPLACE");
        JsonPathAlteration trAlterationSystemNumber = new JsonPathAlteration("$.systemNumber", systemNumber,"","REPLACE");
        JsonPathAlteration trEuVehicleCategory = new JsonPathAlteration("$.euVehicleCategory", "n1","","REPLACE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterationsTestResult = new ArrayList<>(Arrays.asList(
                alterationTestResultVin,
                alterationTestResultId,
                trAlterationSystemNumber,
                trEuVehicleCategory));

        // Post the results, together with any alterations, and verify that they are accepted.
        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, alterationsTestResult);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");

        // Wait for the vehicle tech records to be updated
        vehicleTechnicalRecordsSteps.waitForVehicleTechRecordsToBeUpdated(vin, 10);


        // Get the tech record, and verify that the fields are present.
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsBySystemNumber(systemNumber);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].euVehicleCategory", "n1");
    }

    @Title("CVSB-11333 - 'EU vehicle category' updated - NULL - Car")
    @Test
    public void testVehicleTechRecordCarEuVehicleCategoryNull(){

        // Tech record exists already in dynamoDb with a null euVehicleCategory
        String systemNumber = "XYZEP5JYOMM00005";
        String vin = "DP76UMK4DQLTOT400005";

        // Get the created technical record, verify the status code and the fields
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsBySystemNumber(systemNumber);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].euVehicleCategory", null);


        // Read test result base json + Generate random values
        String testResultRecord = GenericData.readJsonValueFromFile("test-results_car.json", "$");
        String randomTestResultId = UUID.randomUUID().toString();
        JsonPathAlteration alterationTestResultVin = new JsonPathAlteration("$.vin", vin, "", "REPLACE");
        JsonPathAlteration alterationTestResultId = new JsonPathAlteration("$.testResultId", randomTestResultId, "", "REPLACE");
        JsonPathAlteration trAlterationSystemNumber = new JsonPathAlteration("$.systemNumber", systemNumber,"","REPLACE");
        JsonPathAlteration trEuVehicleCategory = new JsonPathAlteration("$.euVehicleCategory", "m1","","REPLACE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterationsTestResult = new ArrayList<>(Arrays.asList(
                alterationTestResultVin,
                alterationTestResultId,
                trAlterationSystemNumber,
                trEuVehicleCategory));

        // Post the results, together with any alterations, and verify that they are accepted.
        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, alterationsTestResult);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");

        // Wait for the vehicle tech records to be updated
        vehicleTechnicalRecordsSteps.waitForVehicleTechRecordsToBeUpdated(vin, 10);


        // Get the tech record, and verify that the fields are present.
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsBySystemNumber(systemNumber);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].euVehicleCategory", "m1");
    }

    @Title("CVSB-11333 - 'EU vehicle category' updated - NULL - Motorcycle")
    @Test
    public void testVehicleTechRecordMotorcycleEuVehicleCategoryNull(){

        // Tech record exists already in dynamoDb with a null euVehicleCategory
        String systemNumber = "XYZEP5JYOMM00006";
        String vin = "DP76UMK4DQLTOT400006";

        // Get the created technical record, verify the status code and the fields
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsBySystemNumber(systemNumber);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].euVehicleCategory", null);


        // Read test result base json + Generate random values
        String testResultRecord = GenericData.readJsonValueFromFile("test-results_motorcycle.json", "$");
        String randomTestResultId = UUID.randomUUID().toString();
        JsonPathAlteration alterationTestResultVin = new JsonPathAlteration("$.vin", vin, "", "REPLACE");
        JsonPathAlteration alterationTestResultId = new JsonPathAlteration("$.testResultId", randomTestResultId, "", "REPLACE");
        JsonPathAlteration trAlterationSystemNumber = new JsonPathAlteration("$.systemNumber", systemNumber,"","REPLACE");
        JsonPathAlteration trEuVehicleCategory = new JsonPathAlteration("$.euVehicleCategory", "l1e-a","","REPLACE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterationsTestResult = new ArrayList<>(Arrays.asList(
                alterationTestResultVin,
                alterationTestResultId,
                trAlterationSystemNumber,
                trEuVehicleCategory));

        // Post the results, together with any alterations, and verify that they are accepted.
        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, alterationsTestResult);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");

        // Wait for the vehicle tech records to be updated
        vehicleTechnicalRecordsSteps.waitForVehicleTechRecordsToBeUpdated(vin, 10);


        // Get the tech record, and verify that the fields are present.
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsBySystemNumber(systemNumber);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].euVehicleCategory", "l1e-a");
    }

    @Title("CVSB-11333 - 'EU vehicle category' NOT updated - HGV")
    @Test
    public void testVehicleTechRecordHgvEuVehicleCategoryNotUpdatedM1(){

        // Tech record exists already in dynamoDb with a populated euVehicleCategory (which shouldn't change).
        String systemNumber = "XYZEP5JYOMM00007";
        String vin = "DP76UMK4DQLTOT400007";

        // Get the created technical record, verify the status code and the fields
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsBySystemNumber(systemNumber);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].euVehicleCategory", "m1");


        // Read test result base json + Generate random values
        String testResultRecord = GenericData.readJsonValueFromFile("test-results_roadworthiness_hgv_pass_7675.json", "$");
        String randomTestResultId = UUID.randomUUID().toString();
        JsonPathAlteration alterationTestResultVin = new JsonPathAlteration("$.vin", vin, "", "REPLACE");
        JsonPathAlteration alterationTestResultId = new JsonPathAlteration("$.testResultId", randomTestResultId, "", "REPLACE");
        JsonPathAlteration trAlterationSystemNumber = new JsonPathAlteration("$.systemNumber", systemNumber,"n2","REPLACE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterationsTestResult = new ArrayList<>(Arrays.asList(
                alterationTestResultVin,
                alterationTestResultId,
                trAlterationSystemNumber));

        // Post the results, together with any alterations, and verify that they are accepted.
        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, alterationsTestResult);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");

        // Wait for the vehicle tech records to be updated
        vehicleTechnicalRecordsSteps.waitForVehicleTechRecordsToBeUpdated(vin, 10);


        // Get the tech record, and verify that the fields are present.
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsBySystemNumber(systemNumber);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].euVehicleCategory", "m1");
    }

    @Title("CVSB-11333 - 'EU vehicle category' NOT updated - PSV")
    @Test
    public void testVehicleTechRecordPsvEuVehicleCategoryNotUpdated(){

        // Tech record exists already in dynamoDb with a null euVehicleCategory
        String systemNumber = "XYZEP5JYOMM00008";
        String vin = "DP76UMK4DQLTOT400008";

        // Get the created technical record, verify the status code and the fields
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsBySystemNumber(systemNumber);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].euVehicleCategory", "m3");


        // Read test result base json + Generate random values
        String testResultRecord = GenericData.readJsonValueFromFile("test-results_psv.json", "$");
        String randomTestResultId = UUID.randomUUID().toString();
        JsonPathAlteration alterationTestResultVin = new JsonPathAlteration("$.vin", vin, "", "REPLACE");
        JsonPathAlteration alterationTestResultId = new JsonPathAlteration("$.testResultId", randomTestResultId, "", "REPLACE");
        JsonPathAlteration trAlterationSystemNumber = new JsonPathAlteration("$.systemNumber", systemNumber,"","REPLACE");
        JsonPathAlteration trEuVehicleCategory = new JsonPathAlteration("$.euVehicleCategory", "m2","","REPLACE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterationsTestResult = new ArrayList<>(Arrays.asList(
                alterationTestResultVin,
                alterationTestResultId,
                trAlterationSystemNumber,
                trEuVehicleCategory));

        // Post the results, together with any alterations, and verify that they are accepted.
        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, alterationsTestResult);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");

        // Wait for the vehicle tech records to be updated
        vehicleTechnicalRecordsSteps.waitForVehicleTechRecordsToBeUpdated(vin, 10);


        // Get the tech record, and verify that the fields are present.
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsBySystemNumber(systemNumber);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].euVehicleCategory", "m3");
    }

    @Title("CVSB-11333 - 'EU vehicle category' NOT updated - TRL")
    @Test
    public void testVehicleTechRecordTrlEuVehicleCategoryNotUpdatedO4(){

        // Tech record exists already in dynamoDb with a pre-populated euVehicleCategory
        String systemNumber = "XYZEP5JYOMM00009";
        String vin = "DP76UMK4DQLTOT400009";

        // Get the created technical record, verify the status code and the fields
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsBySystemNumber(systemNumber);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].euVehicleCategory", "o2");


        // Read test result base json + Generate random values
        String testResultRecord = GenericData.readJsonValueFromFile("test-results_roadworthiness_trl_pass_7675.json", "$");
        String randomTestResultId = UUID.randomUUID().toString();
        JsonPathAlteration alterationTestResultVin = new JsonPathAlteration("$.vin", vin, "", "REPLACE");
        JsonPathAlteration alterationTestResultId = new JsonPathAlteration("$.testResultId", randomTestResultId, "", "REPLACE");
        JsonPathAlteration trAlterationSystemNumber = new JsonPathAlteration("$.systemNumber", systemNumber,"","REPLACE");
        JsonPathAlteration trEuVehicleCategory = new JsonPathAlteration("$.euVehicleCategory", "o3","","REPLACE");


        // Collate the list of alterations.
        List<JsonPathAlteration> alterationsTestResult = new ArrayList<>(Arrays.asList(
                alterationTestResultVin,
                alterationTestResultId,
                trAlterationSystemNumber,
                trEuVehicleCategory));

        // Post the results, together with any alterations, and verify that they are accepted.
        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, alterationsTestResult);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");

        // Wait for the vehicle tech records to be updated
        vehicleTechnicalRecordsSteps.waitForVehicleTechRecordsToBeUpdated(vin, 10);


        // Get the tech record, and verify that the fields are present.
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsBySystemNumber(systemNumber);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].euVehicleCategory", "o2");
    }

    @Title("CVSB-11333 - 'EU vehicle category' NOT updated - LGV")
    @Test
    public void testVehicleTechRecordLgvEuVehicleCategoryNotUpdated(){

        // Tech record exists already in dynamoDb with a pre-populated euVehicleCategory
        String systemNumber = "1000076";
        String vin = "P0123010951264";

        // Get the created technical record, verify the status code and the fields
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecords(vin);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].euVehicleCategory", "n1");


        // Read test result base json + Generate random values
        String testResultRecord = GenericData.readJsonValueFromFile("test-results_lgv.json", "$");
        String randomTestResultId = UUID.randomUUID().toString();
        JsonPathAlteration alterationTestResultVin = new JsonPathAlteration("$.vin", vin, "", "REPLACE");
        JsonPathAlteration alterationTestResultId = new JsonPathAlteration("$.testResultId", randomTestResultId, "", "REPLACE");
        JsonPathAlteration trAlterationSystemNumber = new JsonPathAlteration("$.systemNumber", systemNumber,"","REPLACE");
        JsonPathAlteration trEuVehicleCategory = new JsonPathAlteration("$.euVehicleCategory", "n2","","REPLACE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterationsTestResult = new ArrayList<>(Arrays.asList(
                alterationTestResultVin,
                alterationTestResultId,
                trAlterationSystemNumber,
                trEuVehicleCategory));

        // Post the results, together with any alterations, and verify that they are accepted.
        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, alterationsTestResult);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");

        // Wait for the vehicle tech records to be updated
        vehicleTechnicalRecordsSteps.waitForVehicleTechRecordsToBeUpdated(vin, 10);


        // Get the tech record, and verify that the fields are present.
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecords(vin);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].euVehicleCategory", "n1");
    }

    @Title("CVSB-11333 - 'EU vehicle category' NOT updated - Car")
    @Test
    public void testVehicleTechRecordCarEuVehicleCategoryNotUpdated(){

        // Tech record exists already in dynamoDb with a pre-populated euVehicleCategory
        String systemNumber = "XYZEP5JYOMM00011";
        String vin = "DP76UMK4DQLTOT400011";

        // Get the created technical record, verify the status code and the fields
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecords(vin);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].euVehicleCategory", "m1");


        // Read test result base json + Generate random values
        String testResultRecord = GenericData.readJsonValueFromFile("test-results_car.json", "$");
        String randomTestResultId = UUID.randomUUID().toString();
        JsonPathAlteration alterationTestResultVin = new JsonPathAlteration("$.vin", vin, "", "REPLACE");
        JsonPathAlteration alterationTestResultId = new JsonPathAlteration("$.testResultId", randomTestResultId, "", "REPLACE");
        JsonPathAlteration trAlterationSystemNumber = new JsonPathAlteration("$.systemNumber", systemNumber,"","REPLACE");
        JsonPathAlteration trEuVehicleCategory = new JsonPathAlteration("$.euVehicleCategory", "m2","","REPLACE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterationsTestResult = new ArrayList<>(Arrays.asList(
                alterationTestResultVin,
                alterationTestResultId,
                trAlterationSystemNumber,
                trEuVehicleCategory));

        // Post the results, together with any alterations, and verify that they are accepted.
        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, alterationsTestResult);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");

        // Wait for the vehicle tech records to be updated
        vehicleTechnicalRecordsSteps.waitForVehicleTechRecordsToBeUpdated(vin, 10);


        // Get the tech record, and verify that the fields are present.
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecords(vin);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].euVehicleCategory", "m1");
    }

    @Title("CVSB-11333 - 'EU vehicle category' NOT updated - Motorcycle")
    @Test
    public void testVehicleTechRecordMotorcycleEuVehicleCategoryNotUpdated(){

        // Tech record exists already in dynamoDb with a prep-populated euVehicleCategory
        String systemNumber = "XYZEP5JYOMM00012";
        String vin = "DP76UMK4DQLTOT400012";

        // Get the created technical record, verify the status code and the fields
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsBySystemNumber(systemNumber);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].euVehicleCategory", "l1e-a");


        // Read test result base json + Generate random values
        String testResultRecord = GenericData.readJsonValueFromFile("test-results_motorcycle.json", "$");
        String randomTestResultId = UUID.randomUUID().toString();
        JsonPathAlteration alterationTestResultVin = new JsonPathAlteration("$.vin", vin, "", "REPLACE");
        JsonPathAlteration alterationTestResultId = new JsonPathAlteration("$.testResultId", randomTestResultId, "", "REPLACE");
        JsonPathAlteration trAlterationSystemNumber = new JsonPathAlteration("$.systemNumber", systemNumber,"","REPLACE");
        JsonPathAlteration trEuVehicleCategory = new JsonPathAlteration("$.euVehicleCategory", "l2e","","REPLACE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterationsTestResult = new ArrayList<>(Arrays.asList(
                alterationTestResultVin,
                alterationTestResultId,
                trAlterationSystemNumber,
                trEuVehicleCategory));

        // Post the results, together with any alterations, and verify that they are accepted.
        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, alterationsTestResult);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");

        // Wait for the vehicle tech records to be updated
        vehicleTechnicalRecordsSteps.waitForVehicleTechRecordsToBeUpdated(vin, 10);


        // Get the tech record, and verify that the fields are present.
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsBySystemNumber(systemNumber);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].euVehicleCategory", "l1e-a");
    }


    @Ignore("Test dropped out until duplicate-vin issue is dealt with")
    @Title("CVSB-12449 - 'EU vehicle category' updated based on systemNumber")
    public void testVehicleTechRecordeuVehicleCategoryUpdatedBasedOnSystemNumber(){

        // Given 2 technical records, such that:
        // - both have different system numbers
        // - both have the same VIN
        // - both have a null euVehicleCategory
        // When the test result is submitted for one of the tech records
        // - with the test result containing an euVehicleCategory
        // Then the tech records are updated so that
        // - the tech record with the matching systemNumber is updated to use the new euVehicleCategory
        // - the tech record which doesn't match systemNumber remains with a null euVehicleCategory

        // Tech records 1 and 2 pre-exist in the db:
        // - Car 1: systemNumber XYZEP5JYOMM00020
        // - Car 2: systemNumber XYZEP5JYOMM00021
        // - Car1 and Car2 both have VIN of DP76UMK4DQLTOT400020

        String systemNumber_1 = "XYZEP5JYOMM00020";
        String systemNumber_2 = "XYZEP5JYOMM00021";
        String vin = "DP76UMK4DQLTOT400020";

        // Verify tech record 1
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsBySystemNumber(systemNumber_1);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].euVehicleCategory", null);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].vin", vin);

        // Verify tech record 2
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsBySystemNumber(systemNumber_2);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].euVehicleCategory", null);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].vin", vin);


        // Post the update for record 1
        // Read test result base json + Generate random values
        String testResultRecord = GenericData.readJsonValueFromFile("test-results_car.json", "$");
        String randomTestResultId = UUID.randomUUID().toString();
        JsonPathAlteration alterationTestResultVin = new JsonPathAlteration("$.vin", vin, "", "REPLACE");
        JsonPathAlteration alterationTestResultId = new JsonPathAlteration("$.testResultId", randomTestResultId, "", "REPLACE");
        JsonPathAlteration trAlterationSystemNumber = new JsonPathAlteration("$.systemNumber", systemNumber_1,"","REPLACE");
        JsonPathAlteration trEuVehicleCategory = new JsonPathAlteration("$.euVehicleCategory", "m1","","REPLACE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterationsTestResult = new ArrayList<>(Arrays.asList(
                alterationTestResultVin,
                alterationTestResultId,
                trAlterationSystemNumber,
                trEuVehicleCategory));

        // Post the results, together with any alterations, and verify that they are accepted.
        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, alterationsTestResult);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");

        // Wait for the vehicle tech records to be updated
        vehicleTechnicalRecordsSteps.waitForVehicleTechRecordsToBeUpdated(vin, 10);


        // Get both tech records, and verify that ONLY record 1 has been updated.
        // Verify tech record 1
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsBySystemNumber(systemNumber_1);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].euVehicleCategory", "m1");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].vin", vin);

        // Verify tech record 2
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsBySystemNumber(systemNumber_2);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].euVehicleCategory", null);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].vin", vin);
    }
}
