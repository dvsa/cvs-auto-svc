package trailerRegistration;

import data.GenericData;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import model.vehicles.VehicleTechnicalRecordStatus;
import net.thucydides.core.annotations.Title;
import net.thucydides.core.annotations.WithTag;
import org.apache.http.HttpStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import steps.TestResultsSteps;
import steps.TrailerRegistrationSteps;
import steps.VehicleTechnicalRecordsSteps;
import util.JsonPathAlteration;
import java.util.*;

@RunWith(SerenityRunner.class)
public class PostTrailerRegistration {

    @Steps
    TrailerRegistrationSteps trailerRegistrationSteps;

    @Steps
    VehicleTechnicalRecordsSteps vehicleTechnicalRecordsSteps;

    @Steps
    TestResultsSteps testResultsSteps;

    @Title("CVSB-18919 - AC1 - Saving 17 digit vin")
    @Test
    public void testTrailerRegistrationWithSeventeenDigitVin() {

        // Read the base test result JSON
        String trailerRegistrationRecord = GenericData.readJsonValueFromFile("trailer-registration_18919.json","$");

        // Create alteration to add one more tech record to in the request body
        String randomVin = GenericData.generateRandomVinForTrailerRegistration();
        String randomTrn = GenericData.generateRandomVinForTrailerRegistration();
        randomTrn = randomTrn.substring(0, randomTrn.length() - 9);
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin,"","REPLACE");
        JsonPathAlteration alterationTrn = new JsonPathAlteration("$.trn", randomTrn,"","REPLACE");

        // Collate the list of alterations
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(
                alterationVin,alterationTrn));

        // Post the results, together with any alterations, and verify that they are accepted.
        trailerRegistrationSteps.postTrailerRegistrationWithAlterations(trailerRegistrationRecord, alterations);
        trailerRegistrationSteps.statusCodeShouldBe(200);
        trailerRegistrationSteps.valueForFieldInPathShouldBe("vinOrChassisWithMake",randomVin);
    }

    @Title("CVSB-18919 - AC2 - Saving non-17 digit vin - Less than 17 digits ")
    @Test
    public void testTrailerRegistrationWithLessThanSeventeenDigitVin() {

        // Read the base test result JSON
        String trailerRegistrationRecord = GenericData.readJsonValueFromFile("trailer-registration_18919.json","$");

        // Create alteration to add one more tech record to in the request body
        String randomVin = GenericData.generateRandomVinForTrailerRegistration();
        randomVin = randomVin.substring(0, randomVin.length() - 2);

        String randomTrn = GenericData.generateRandomVinForTrailerRegistration();
        randomTrn = randomTrn.substring(0, randomTrn.length() - 9);
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin,"","REPLACE");
        JsonPathAlteration alterationTrn = new JsonPathAlteration("$.trn", randomTrn,"","REPLACE");

        // Collate the list of alterations
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(
                alterationVin,alterationTrn));

        // Post the results, together with any alterations, and verify that they are accepted.
        trailerRegistrationSteps.postTrailerRegistrationWithAlterations(trailerRegistrationRecord, alterations);
        trailerRegistrationSteps.statusCodeShouldBe(200);
        trailerRegistrationSteps.valueForFieldInPathShouldBe("vinOrChassisWithMake",randomVin +"Doepker Industries");
    }

    @Title("CVSB-18919 - AC2 - Saving non-17 digit vin - More than 17 digits")
    @Test
    public void testTrailerRegistrationWithMoreThanSeventeenDigitVin() {

        // Read the base test result JSON
        String trailerRegistrationRecord = GenericData.readJsonValueFromFile("trailer-registration_18919.json","$");

        String specialChars = "HGY";

        // Create alteration to add one more tech record to in the request body
        String randomVin = GenericData.generateRandomVinForTrailerRegistration() + specialChars ;
        String randomTrn = GenericData.generateRandomVinForTrailerRegistration();
        randomTrn = randomTrn.substring(0, randomTrn.length() - 9);
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin,"","REPLACE");
        JsonPathAlteration alterationTrn = new JsonPathAlteration("$.trn", randomTrn,"","REPLACE");


        // Collate the list of alterations
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(
                alterationVin,alterationTrn));

        // Post the results, together with any alterations, and verify that they are accepted.
        trailerRegistrationSteps.postTrailerRegistrationWithAlterations(trailerRegistrationRecord, alterations);
        trailerRegistrationSteps.statusCodeShouldBe(200);
        trailerRegistrationSteps.valueForFieldInPathShouldBe("vinOrChassisWithMake",randomVin +"Doepker Industries");
    }

    @Title("CVSB-18919 - AC3 - Update an Existing TRN during insert")
    @Test
    public void testTrailerRegistrationWithAnExistingVin() {


        // Read the base test result JSON
        String trailerRegistrationRecord = GenericData.readJsonValueFromFile("trailer-registration_18919.json","$");

        // Create alteration to add one more tech record to in the request body
        String randomVin = GenericData.generateRandomVinForTrailerRegistration();
        String randomTrn = GenericData.generateRandomVinForTrailerRegistration();
        randomTrn = randomTrn.substring(0, randomTrn.length() - 9);
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin,"","REPLACE");
        JsonPathAlteration alterationTrn = new JsonPathAlteration("$.trn", randomTrn,"","REPLACE");


        // Collate the list of alterations
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(
                alterationVin,alterationTrn));

        // Post the results, together with any alterations, and verify that they are accepted.
        trailerRegistrationSteps.postTrailerRegistrationWithAlterations(trailerRegistrationRecord, alterations);
        trailerRegistrationSteps.statusCodeShouldBe(200);
        trailerRegistrationSteps.valueForFieldInPathShouldBe("vinOrChassisWithMake",randomVin);


        // Create alteration to add one more tech record to in the request body
        JsonPathAlteration alterationUpdateVin = new JsonPathAlteration("$.vin", randomVin,"","REPLACE");

        // Collate the list of alterations
        List<JsonPathAlteration> UpdateAlterations = new ArrayList<>(Arrays.asList(
                alterationUpdateVin));

        // Post the results, together with any alterations, and verify that they are accepted.
        trailerRegistrationSteps.postTrailerRegistrationWithAlterations(trailerRegistrationRecord, UpdateAlterations);
        trailerRegistrationSteps.statusCodeShouldBe(200);
        trailerRegistrationSteps.valueForFieldInPathShouldBe("vinOrChassisWithMake",randomVin);
        trailerRegistrationSteps.valueForFieldInPathShouldBe("archive[0].reasonForDeregistration","New certificate received.");
        trailerRegistrationSteps.valueForFieldInPathShouldBe("archive[0].deregisterDate","2021-01-14T15:49:30.881Z");
    }

    @Title("CVSB-18919 - AC4 - Unregister when TRN is found")
    @Test
    public void testTrailerUnRegistrationForAnExistingVin() {


        // Read the base test result JSON
        String trailerRegistrationRecord = GenericData.readJsonValueFromFile("trailer-registration_18919.json","$");

        // Create alteration to add one more tech record to in the request body
        String randomVin = GenericData.generateRandomVinForTrailerRegistration();
        String randomTrn = GenericData.generateRandomVinForTrailerRegistration();
        randomTrn = randomTrn.substring(0, randomTrn.length() - 9);
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin,"","REPLACE");
        JsonPathAlteration alterationTrn = new JsonPathAlteration("$.trn", randomTrn,"","REPLACE");


        // Collate the list of alterations
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(
                alterationVin,alterationTrn));

        // Post the results, together with any alterations, and verify that they are accepted.
        trailerRegistrationSteps.postTrailerRegistrationWithAlterations(trailerRegistrationRecord, alterations);
        trailerRegistrationSteps.statusCodeShouldBe(200);
        trailerRegistrationSteps.valueForFieldInPathShouldBe("vinOrChassisWithMake",randomVin);


        // Read the base test result JSON
        String trailerDeRegistrationRecord = GenericData.readJsonValueFromFile("trailer-deregistration_18919.json","$");

        // Create alteration to add one more tech record to in the request body
        JsonPathAlteration alterationReasonForDeRegistration = new JsonPathAlteration("$.reasonForDeregistration", "For testing purpose only","","REPLACE");

        // Collate the list of alterations
        List<JsonPathAlteration> DeRegisterAlterations = new ArrayList<>(Arrays.asList(
                alterationReasonForDeRegistration));

        // Put the results, together with any alterations, and verify that they are accepted.
        trailerRegistrationSteps.putTrailerRegistrationWithAlterations(randomTrn,trailerDeRegistrationRecord,DeRegisterAlterations);
        trailerRegistrationSteps.statusCodeShouldBe(200);
        trailerRegistrationSteps.valueForFieldInPathShouldBe("vinOrChassisWithMake",randomVin);
        trailerRegistrationSteps.valueForFieldInPathShouldBe("vin",randomVin);
        trailerRegistrationSteps.valueForFieldInPathShouldBe("trn",randomTrn);
        trailerRegistrationSteps.valueForFieldInPathShouldBe("reasonForDeregistration","For testing purpose only");
        trailerRegistrationSteps.valueForFieldInPathShouldBe("deregisterDate","2021-06-11T15:10:01+0000");

    }

    @Title("CVSB-18919 - AC5 - Unregister when TRN is not found")
    @Test
    public void testTrailerUnRegistrationForInvalidVin() {

        // Read the base test result JSON
        String trailerDeRegistrationRecord = GenericData.readJsonValueFromFile("trailer-deregistration_18919.json","$");

        // Create alteration to add one more tech record to in the request body
        String randomTrn = GenericData.generateRandomVinForTrailerRegistration();
        randomTrn = randomTrn.substring(0, randomTrn.length() - 9);
        JsonPathAlteration alterationReasonForDeRegistration = new JsonPathAlteration("$.reasonForDeregistration", "For testing purpose only","","REPLACE");

        // Collate the list of alterations
        List<JsonPathAlteration> DeRegisterAlterations = new ArrayList<>(Arrays.asList(
                alterationReasonForDeRegistration));

        // Put the results, together with any alterations, and verify that an error response status code is retrieved
        trailerRegistrationSteps.putTrailerRegistrationWithAlterations(randomTrn,trailerDeRegistrationRecord,DeRegisterAlterations);
        trailerRegistrationSteps.statusCodeShouldBe(204);

    }

    @Title("CVSB-18919 - AC6 - Bad request on invalid payload - Invalid DeregisterDate")
    @Test
    public void testTrailerUnRegistrationForInvalidDeRegisterDate() {

        // Read the base test result JSON
        String trailerDeRegistrationRecord = GenericData.readJsonValueFromFile("trailer-deregistration_18919.json","$");

        // Create alteration to add one more tech record to in the request body
        String randomTrn = GenericData.generateRandomVinForTrailerRegistration();
        randomTrn = randomTrn.substring(0, randomTrn.length() - 9);
        JsonPathAlteration alterationDeRegisterDate = new JsonPathAlteration("$.deregisterDate", "2021-06-11T15:10:01:0000","","REPLACE");

        // Collate the list of alterations
        List<JsonPathAlteration> DeRegisterAlterations = new ArrayList<>(Arrays.asList(
                alterationDeRegisterDate));

        // Put the results, together with any alterations, and verify that an error response status code is retrieved
        trailerRegistrationSteps.putTrailerRegistrationWithAlterations(randomTrn,trailerDeRegistrationRecord,DeRegisterAlterations);
        trailerRegistrationSteps.statusCodeShouldBe(400);
    }

    @Title("CVSB-18919 - AC6 - Bad request on invalid payload - Boolean reasonForDeregistration")
    @Test
    public void testTrailerUnRegistrationForBooleanReasonForDeRegistration() {

        // Read the base test result JSON
        String trailerDeRegistrationRecord = GenericData.readJsonValueFromFile("trailer-deregistration_18919.json","$");

        // Create alteration to add one more tech record to in the request body
        String randomTrn = GenericData.generateRandomVinForTrailerRegistration();
        randomTrn = randomTrn.substring(0, randomTrn.length() - 9);
        JsonPathAlteration alterationReasonForDeRegistration = new JsonPathAlteration("$.reasonForDeregistration", true,"","REPLACE");

        // Collate the list of alterations
        List<JsonPathAlteration> DeRegisterAlterations = new ArrayList<>(Arrays.asList(
                alterationReasonForDeRegistration));

        // Put the results, together with any alterations, and verify that an error response status code is retrieved
        trailerRegistrationSteps.putTrailerRegistrationWithAlterations(randomTrn,trailerDeRegistrationRecord,DeRegisterAlterations);
        trailerRegistrationSteps.statusCodeShouldBe(400);
    }

    @Title("CVSB-18919 - AC6 - Bad request on invalid payload - Numeric reasonForDeregistration")
    @Test
    public void testTrailerUnRegistrationForIntReasonForDeRegistration() {

        // Read the base test result JSON
        String trailerDeRegistrationRecord = GenericData.readJsonValueFromFile("trailer-deregistration_18919.json","$");

        // Create alteration to add one more tech record to in the request body
        String randomTrn = GenericData.generateRandomVinForTrailerRegistration();
        randomTrn = randomTrn.substring(0, randomTrn.length() - 9);
        JsonPathAlteration alterationReasonForDeRegistration = new JsonPathAlteration("$.reasonForDeregistration", 123,"","REPLACE");

        // Collate the list of alterations
        List<JsonPathAlteration> DeRegisterAlterations = new ArrayList<>(Arrays.asList(
                alterationReasonForDeRegistration));

        // Put the results, together with any alterations, and verify that an error response status code is retrieved
        trailerRegistrationSteps.putTrailerRegistrationWithAlterations(randomTrn,trailerDeRegistrationRecord,DeRegisterAlterations);
        trailerRegistrationSteps.statusCodeShouldBe(400);
    }

    @Title("CVSB-18921 - AC1 -  VTG5A TRN PRESENT - TRL Annual Test Pass - With TRN")
    @Test
    public void testPostTestResultPassTrailerRegistrationCertificateWithTrn() {

        // Read the base test result JSON
        String postTechnicalRecord = GenericData.readJsonValueFromFile("technical-records_trl_all_fields.json","$");

        String randomVin = GenericData.generateRandomVinForTrailerRegistration();
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin,"","REPLACE");

        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(
                alterationVin));

        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postTechnicalRecord, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(HttpStatus.SC_CREATED);
        vehicleTechnicalRecordsSteps.validateData("Technical Record created");
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsByStatus(randomVin, VehicleTechnicalRecordStatus.ALL);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        String systemNumber = vehicleTechnicalRecordsSteps.getSystemNumber();

        String postTrailerRecord = GenericData.readJsonValueFromFile("trailer-registration_18919.json","$");

        // Create alteration to add one more tech record to in the request body
        String randomTrn = GenericData.generateRandomVinForTrailerRegistration();
        randomTrn = randomTrn.substring(0, randomTrn.length() - 9);
        JsonPathAlteration alterationTrailerVin = new JsonPathAlteration("$.vin", randomVin,"","REPLACE");
        JsonPathAlteration alterationTrailerTrn = new JsonPathAlteration("$.trn", randomTrn,"","REPLACE");

        // Collate the list of alterations
        List<JsonPathAlteration> RegisterAlterations = new ArrayList<>(Arrays.asList(
                alterationTrailerVin,
                alterationTrailerTrn));

        // Post the results, together with any alterations, and verify that they are accepted.
        trailerRegistrationSteps.postTrailerRegistrationWithAlterations(postTrailerRecord, RegisterAlterations);
        trailerRegistrationSteps.statusCodeShouldBe(200);

        String testResultRecord = GenericData.readJsonValueFromFile("test-results_post_expiry_date_trl_8798.json","$");

        String testResultId = UUID.randomUUID().toString();

        JsonPathAlteration alterationTestResultVin = new JsonPathAlteration("$.vin", randomVin,"","REPLACE");
        JsonPathAlteration alterationTestResultId = new JsonPathAlteration("$.testResultId", testResultId,"","REPLACE");
        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$.systemNumber", systemNumber,"","REPLACE");

        List<JsonPathAlteration> TestResultAlterations = new ArrayList<>(Arrays.asList(
                alterationTestResultVin,
                alterationTestResultId,
                alterationSystemNumber
        ));

        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, TestResultAlterations);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");

        testResultsSteps.getTestResults(systemNumber);
        testResultsSteps.statusCodeShouldBe(200);
        String testNumber = testResultsSteps.getTestNumber();

        testResultsSteps.validateCertificateIsGenerated(testNumber,randomVin);
    }

    @Title("CVSB-18921 - AC2 -  VTG5A NO TRN PRESENT - TRL First Test Pass - Without TRN")
    @Test
    public void testPostTestResultPassTrailerRegistrationCertificateWithoutTrn() {

        // Read the base test result JSON
        String postTechnicalRecord = GenericData.readJsonValueFromFile("technical-records_trl_all_fields.json","$");

        String randomVin = GenericData.generateRandomVinForTrailerRegistration();
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin,"","REPLACE");

        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(
                alterationVin));

        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postTechnicalRecord, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(HttpStatus.SC_CREATED);
        vehicleTechnicalRecordsSteps.validateData("Technical Record created");
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsByStatus(randomVin, VehicleTechnicalRecordStatus.ALL);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        String systemNumber = vehicleTechnicalRecordsSteps.getSystemNumber();

        String testResultRecord = GenericData.readJsonValueFromFile("test-results_first_test_trl.json","$");

        String testResultId = UUID.randomUUID().toString();

        JsonPathAlteration alterationTestResultVin = new JsonPathAlteration("$.vin", randomVin,"","REPLACE");
        JsonPathAlteration alterationTestResultId = new JsonPathAlteration("$.testResultId", testResultId,"","REPLACE");
        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$.systemNumber", systemNumber,"","REPLACE");

        List<JsonPathAlteration> TestResultAlterations = new ArrayList<>(Arrays.asList(
                alterationTestResultVin,
                alterationTestResultId,
                alterationSystemNumber
        ));

        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, TestResultAlterations);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");

        testResultsSteps.getTestResults(systemNumber);
        testResultsSteps.statusCodeShouldBe(200);
        String testNumber = testResultsSteps.getTestNumber();

        testResultsSteps.validateCertificateIsGenerated(testNumber,randomVin);
    }

    @Title("CVSB-18921 - AC3 - VTG30 TRN PRESENT - TRL Annual Test Fail - With TRN")
    @Test
    public void testPostTestResultFailTrailerRegistrationCertificateWithTrn() {

        // Read the base test result JSON
        String postTechnicalRecord = GenericData.readJsonValueFromFile("technical-records_trl_all_fields.json","$");

        String randomVin = GenericData.generateRandomVinForTrailerRegistration();
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin,"","REPLACE");

        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(
                alterationVin));

        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postTechnicalRecord, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(HttpStatus.SC_CREATED);
        vehicleTechnicalRecordsSteps.validateData("Technical Record created");
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsByStatus(randomVin, VehicleTechnicalRecordStatus.ALL);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        String systemNumber = vehicleTechnicalRecordsSteps.getSystemNumber();


        String postTrailerRecord = GenericData.readJsonValueFromFile("trailer-registration_18919.json","$");

        // Create alteration to add one more tech record to in the request body
        String randomTrn = GenericData.generateRandomVinForTrailerRegistration();
        randomTrn = randomTrn.substring(0, randomTrn.length() - 9);
        JsonPathAlteration alterationTrailerVin = new JsonPathAlteration("$.vin", randomVin,"","REPLACE");
        JsonPathAlteration alterationTrailerTrn = new JsonPathAlteration("$.trn", randomTrn,"","REPLACE");

        // Collate the list of alterations
        List<JsonPathAlteration> RegisterAlterations = new ArrayList<>(Arrays.asList(
                alterationTrailerVin,
                alterationTrailerTrn));

        // Post the results, together with any alterations, and verify that they are accepted.
        trailerRegistrationSteps.postTrailerRegistrationWithAlterations(postTrailerRecord, RegisterAlterations);
        trailerRegistrationSteps.statusCodeShouldBe(200);

        String testResultRecord = GenericData.readJsonValueFromFile("test-results_post_expiry_date_trl_8798.json","$");

        String testResultId = UUID.randomUUID().toString();

        JsonPathAlteration alterationTestResultVin = new JsonPathAlteration("$.vin", randomVin,"","REPLACE");
        JsonPathAlteration alterationTestResultId = new JsonPathAlteration("$.testResultId", testResultId,"","REPLACE");
        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$.systemNumber", systemNumber,"","REPLACE");
        JsonPathAlteration alterationTestResult = new JsonPathAlteration("$.testTypes[0].testResult", "fail","","REPLACE");

        List<JsonPathAlteration> TestResultAlterations = new ArrayList<>(Arrays.asList(
                alterationTestResultVin,
                alterationTestResultId,
                alterationSystemNumber,
                alterationTestResult
        ));

        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, TestResultAlterations);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");

        testResultsSteps.getTestResults(systemNumber);
        testResultsSteps.statusCodeShouldBe(200);
        String testNumber = testResultsSteps.getTestNumber();

        testResultsSteps.validateCertificateIsGenerated(testNumber,randomVin);
    }

    @Title("CVSB-18921 - AC4 -  VTG30 NO TRN PRESENT - TRL First Test Fail - Without TRN")
    @Test
    public void testPostTestResultFailTrailerRegistrationCertificateWithoutTrn() {

        // Read the base test result JSON
        String postTechnicalRecord = GenericData.readJsonValueFromFile("technical-records_trl_all_fields.json","$");

        String randomVin = GenericData.generateRandomVinForTrailerRegistration();
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin,"","REPLACE");

        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(
                alterationVin));

        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postTechnicalRecord, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(HttpStatus.SC_CREATED);
        vehicleTechnicalRecordsSteps.validateData("Technical Record created");
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsByStatus(randomVin, VehicleTechnicalRecordStatus.ALL);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        String systemNumber = vehicleTechnicalRecordsSteps.getSystemNumber();

        String testResultRecord = GenericData.readJsonValueFromFile("test-results_first_test_trl.json","$");

        String testResultId = UUID.randomUUID().toString();

        JsonPathAlteration alterationTestResultVin = new JsonPathAlteration("$.vin", randomVin,"","REPLACE");
        JsonPathAlteration alterationTestResultId = new JsonPathAlteration("$.testResultId", testResultId,"","REPLACE");
        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$.systemNumber", systemNumber,"","REPLACE");
        JsonPathAlteration alterationTestResult = new JsonPathAlteration("$.testTypes[0].testResult", "fail","","REPLACE");

        List<JsonPathAlteration> TestResultAlterations = new ArrayList<>(Arrays.asList(
                alterationTestResultVin,
                alterationTestResultId,
                alterationSystemNumber,
                alterationTestResult
        ));

        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, TestResultAlterations);

        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");

        testResultsSteps.getTestResults(systemNumber);
        testResultsSteps.statusCodeShouldBe(200);
        String testNumber = testResultsSteps.getTestNumber();

        testResultsSteps.validateCertificateIsGenerated(testNumber,randomVin);
    }

    @Title("CVSB-18921 - AC4 -  VTG30 NO TRN PRESENT - HGV Annual Test Fail - Without TRN")
    @Test
    public void testPostTestResultFailTrailerRegistrationCertificateWithoutTrnHgv() {

        // Read the base test result JSON
        String postTechnicalRecord = GenericData.readJsonValueFromFile("technical-records_hgv_all_fields.json","$");

        String randomVin = GenericData.generateRandomVinForTrailerRegistration();
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin,"","REPLACE");

        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(
                alterationVin));

        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postTechnicalRecord, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(HttpStatus.SC_CREATED);
        vehicleTechnicalRecordsSteps.validateData("Technical Record created");
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsByStatus(randomVin, VehicleTechnicalRecordStatus.ALL);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        String systemNumber = vehicleTechnicalRecordsSteps.getSystemNumber();

        String testResultRecord = GenericData.readJsonValueFromFile("test-results_post_expiry_date_hgv_8798.json","$");

        String testResultId = UUID.randomUUID().toString();

        JsonPathAlteration alterationTestResultVin = new JsonPathAlteration("$.vin", randomVin,"","REPLACE");
        JsonPathAlteration alterationTestResultId = new JsonPathAlteration("$.testResultId", testResultId,"","REPLACE");
        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$.systemNumber", systemNumber,"","REPLACE");
        JsonPathAlteration alterationTestResult = new JsonPathAlteration("$.testTypes[0].testResult", "fail","","REPLACE");

        List<JsonPathAlteration> TestResultAlterations = new ArrayList<>(Arrays.asList(
                alterationTestResultVin,
                alterationTestResultId,
                alterationSystemNumber,
                alterationTestResult
        ));

        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, TestResultAlterations);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");

        testResultsSteps.getTestResults(systemNumber);
        testResultsSteps.statusCodeShouldBe(200);
        String testNumber = testResultsSteps.getTestNumber();

        testResultsSteps.validateCertificateIsGenerated(testNumber,randomVin);
    }

    @WithTag("In_test")
    @Title("CVSB-18921 - VTG5 NO TRN PRESENT - HGV Annual Test Pass - Without TRN")
    @Test
    public void testPostTestResultPassTrailerRegistrationCertificateWithoutTrnHgv() {

        // Read the base test result JSON
        String postTechnicalRecord = GenericData.readJsonValueFromFile("technical-records_hgv_all_fields.json","$");

        String randomVin = GenericData.generateRandomVinForTrailerRegistration();
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin,"","REPLACE");

        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(
                alterationVin));

        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postTechnicalRecord, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(HttpStatus.SC_CREATED);
        vehicleTechnicalRecordsSteps.validateData("Technical Record created");
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsByStatus(randomVin, VehicleTechnicalRecordStatus.ALL);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        String systemNumber = vehicleTechnicalRecordsSteps.getSystemNumber();

        String testResultRecord = GenericData.readJsonValueFromFile("test-results_post_expiry_date_hgv_8798.json","$");

        String testResultId = UUID.randomUUID().toString();

        JsonPathAlteration alterationTestResultVin = new JsonPathAlteration("$.vin", randomVin,"","REPLACE");
        JsonPathAlteration alterationTestResultId = new JsonPathAlteration("$.testResultId", testResultId,"","REPLACE");
        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$.systemNumber", systemNumber,"","REPLACE");


        List<JsonPathAlteration> TestResultAlterations = new ArrayList<>(Arrays.asList(
                alterationTestResultVin,
                alterationTestResultId,
                alterationSystemNumber
        ));

        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, TestResultAlterations);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");

        testResultsSteps.getTestResults(systemNumber);
        testResultsSteps.statusCodeShouldBe(200);
        String testNumber = testResultsSteps.getTestNumber();

        testResultsSteps.validateCertificateIsGenerated(testNumber,randomVin);
    }
}
