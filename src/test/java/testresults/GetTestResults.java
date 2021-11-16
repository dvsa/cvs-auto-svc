package testresults;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import data.GenericData;
import data.TestResultsData;
import model.testresults.TestResults;
import model.testresults.TestResultsGet;
import model.testresults.TestResultsStatus;
import model.testresults.TestVersion;
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

import static util.DataUtil.generateRandomExcludingValues;


@RunWith(SerenityRunner.class)
public class GetTestResults {

    @Steps
    TestResultsSteps testResultsSteps;

    @Steps
    VehicleTechnicalRecordsSteps vehicleTechnicalRecordsSteps;

    private TestResults.Builder vehicleDefaultSubmittedData = TestResultsData.buildTestResultsSubmittedData();

    private TestResultsGet vehicleSubmittedData = TestResultsData.buildTestResultsSubmittedDataWithCalculated().build();
    private TestResultsGet vehicleCancelledData = TestResultsData.buildTestResultsCancelleddDataWithCalculated().build();

    @Title("CVSB-416 - CVSB-949 / CVSB-3513 - Un-authorised consumer retrieves results for submitted tests.")
    @Test
    public void testResultsNoAuthorised() {
        testResultsSteps.getTestResultsNotAuthorised(vehicleSubmittedData.getVin());
        testResultsSteps.statusCodeShouldBe(403);
        testResultsSteps.validateMessage("User is not authorized to access this resource with an explicit deny");
    }

    @Title("CVSB-416 - CVSB-949 / CVSB-1578 - Un-authenticated consumer retrieves results for submitted tests.")
    @Test
    public void testResultsNoAuth() {
        testResultsSteps.getTestResultsNotAuthenticated(vehicleSubmittedData.getVin());
        testResultsSteps.statusCodeShouldBe(401);
        testResultsSteps.validateMessage("Unauthorized");
    }

    @Title("CVSB-416 - CVSB-949 / CVSB-2215 - API Consumer retrieve the Test results for the input Vin (DEFAULT)")
    @Test
    public void testResultsSubmittedReferenceData() {

        String testResultRecord = GenericData.readJsonValueFromFile("test-results_roadworthiness_hgv_pass_7675.json", "$");

        // Create alteration to add one more tech record to in the request body
        String randomSystemNumber = GenericData.generateRandomSystemNumber();
        String randomVin = GenericData.generateRandomVin();
        String randomTestResultId = UUID.randomUUID().toString();
        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$.systemNumber", randomSystemNumber, "", "REPLACE");
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationTestResultId = new JsonPathAlteration("$.testResultId", randomTestResultId, "", "REPLACE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(
                alterationVin,
                alterationSystemNumber,
                alterationTestResultId));

        // Post the results, together with any alterations, and verify that they are accepted.
        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, alterations);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");

        testResultsSteps.getTestResults(randomSystemNumber);
        testResultsSteps.statusCodeShouldBe(200);
        testResultsSteps.valueForFieldInPathShouldBe("[0].vin",randomVin);
        testResultsSteps.valueForFieldInPathShouldBe("[0].systemNumber", randomSystemNumber);
    }

    @Title("CVSB-416 - CVSB-949 / CVSB-2213 - API Consumer retrieve the Test results for the input Vin (SUBMITTED)")
    @Test
    public void testResultsWithStatusSubmittedReferenceData() {
        String testResultRecord = GenericData.readJsonValueFromFile("test-results_roadworthiness_hgv_pass_7675.json", "$");

        // Create alteration to add one more tech record to in the request body
        String randomSystemNumber = GenericData.generateRandomSystemNumber();
        String randomVin = GenericData.generateRandomVin();
        String randomTestResultId = UUID.randomUUID().toString();
        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$.systemNumber", randomSystemNumber, "", "REPLACE");
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationTestResultId = new JsonPathAlteration("$.testResultId", randomTestResultId, "", "REPLACE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(
                alterationVin,
                alterationSystemNumber,
                alterationTestResultId));

        // Post the results, together with any alterations, and verify that they are accepted.
        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, alterations);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");

        testResultsSteps.getTestResults(randomSystemNumber, TestResultsStatus.SUBMITTED);
        testResultsSteps.statusCodeShouldBe(200);
        testResultsSteps.valueForFieldInPathShouldBe("[0].vin",randomVin);
        testResultsSteps.valueForFieldInPathShouldBe("[0].systemNumber", randomSystemNumber);
    }

    @Title("CVSB-416 - CVSB-949 / CVSB-2213 - API Consumer retrieve the Test results for the input Vin (CANCELED)")
    @Test
    public void testResultsWithStatusCanceledReferenceData() {

        String testResultRecord = GenericData.readJsonValueFromFile("test-results_cancelled.json", "$");

        // Create alteration to add one more tech record to in the request body
        String randomSystemNumber = GenericData.generateRandomSystemNumber();
        String randomVin = GenericData.generateRandomVin();
        String randomTestResultId = UUID.randomUUID().toString();
        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$.systemNumber", randomSystemNumber, "", "REPLACE");
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationTestResultId = new JsonPathAlteration("$.testResultId", randomTestResultId, "", "REPLACE");
        JsonPathAlteration alterationTestCode = new JsonPathAlteration("$.testTypes[0].testCode", "", "", "DELETE");
        JsonPathAlteration alterationTestNumber = new JsonPathAlteration("$.testTypes[0].testNumber", "", "", "DELETE");
        JsonPathAlteration alterationLastUpdatedAt = new JsonPathAlteration("$.testTypes[0].lastUpdatedAt", "", "", "DELETE");
        JsonPathAlteration alterationCreatedAt = new JsonPathAlteration("$.testTypes[0].createdAt", "", "", "DELETE");
        JsonPathAlteration alterationCertificateLink = new JsonPathAlteration("$.testTypes[0].certificateLink", "", "", "DELETE");
        JsonPathAlteration alterationVehicleId = new JsonPathAlteration("$.vehicleId", "", "", "DELETE");


        // Collate the list of alterations.
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(
                alterationVin,
                alterationSystemNumber,
                alterationTestCode,
                alterationTestNumber,
                alterationLastUpdatedAt,
                alterationCreatedAt,
                alterationCertificateLink,
                alterationVehicleId,
                alterationTestResultId));

        // Post the results, together with any alterations, and verify that they are accepted.
        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, alterations);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");

        testResultsSteps.getTestResults(randomSystemNumber,TestResultsStatus.CANCELED);
        testResultsSteps.statusCodeShouldBe(200);
        testResultsSteps.valueForFieldInPathShouldBe("[0].vin",randomVin);
        testResultsSteps.valueForFieldInPathShouldBe("[0].systemNumber", randomSystemNumber);
    }

    @Title("CVSB-416 - CVSB-949 / CVSB-2213 - API Consumer retrieve the Test results for the input Vin (INVALID)")
    @Test
    public void testResultsWithStatusInvalidReferenceData() {

        testResultsSteps.getTestResults(vehicleCancelledData.getVin(), TestResultsStatus.INVALID);
        testResultsSteps.statusCodeShouldBe(404);
        testResultsSteps.validateData("No resources match the search criteria");
    }

    @Title("CVSB-416 - CVSB-949 / CVSB-2216 - No data found")
    @Test
    public void testResultsSubmittedAndNotExistingVin() {

        testResultsSteps.getTestResults(RandomStringUtils.randomAlphanumeric(17));
        testResultsSteps.statusCodeShouldBe(404);
        testResultsSteps.validateData("No resources match the search criteria");
    }

    @Title("CVSB-416 - CVSB-949 / CVSB-2431 - Status submitted and no data found")
    @Test
    public void testResultsWithStatusSubmittedAndNotExistingVin() {

        testResultsSteps.getTestResults(RandomStringUtils.randomAlphanumeric(17), TestResultsStatus.SUBMITTED);
        testResultsSteps.statusCodeShouldBe(404);
        testResultsSteps.validateData("No resources match the search criteria");
    }

    @Title("CVSB-416 - CVSB-949 / CVSB-2432 - Status canceled and no data found")
    @Test
    public void testResultsWithStatusCanceledAndNotExistingVin() {

        testResultsSteps.getTestResults(RandomStringUtils.randomAlphanumeric(17), TestResultsStatus.CANCELED);
        testResultsSteps.statusCodeShouldBe(404);
        testResultsSteps.validateData("No resources match the search criteria");
    }

    @Title("CVSB-416 - CVSB-949 / CVSB-2433 - Status invalid and no data found")
    @Test
    public void testResultsWithStatusInvalidAndNotExistingVin() {

        testResultsSteps.getTestResults(RandomStringUtils.randomAlphanumeric(17), TestResultsStatus.INVALID);
        testResultsSteps.statusCodeShouldBe(404);
        testResultsSteps.validateData("No resources match the search criteria");
    }

    @Title("CVSB-6805 - API Consumer retrieve the Test results for the input Vin (PSV)")
    @Test
    public void testResultsForVinPsv() {
        testResultsSteps.getTestResults("11000002");
        testResultsSteps.statusCodeShouldBe(200);
        testResultsSteps.valueForFieldInPathShouldBe("[0].vin","1B7GG36N12S678410");
        testResultsSteps.validateVehicleFieldValue("vehicleType", "psv");
        testResultsSteps.validateVehicleFieldExists("vrm");
        testResultsSteps.validateVehicleFieldExists("numberOfSeats");
        testResultsSteps.validateVehicleFieldExists("odometerReading");
        testResultsSteps.validateVehicleFieldExists("odometerReadingUnits");
        testResultsSteps.validateVehicleFieldExists("vehicleSize");
        testResultsSteps.validateTestFieldExists("numberOfSeatbeltsFitted");
        testResultsSteps.validateTestFieldExists("lastSeatbeltInstallationCheckDate");
        testResultsSteps.validateTestFieldExists("seatbeltInstallationCheckDate");
    }

    @Title("CVSB-6805 - API Consumer retrieve the Test results for the input Vin (HGV)")
    @Test
    public void testResultsForVinHgv() {
        testResultsSteps.getTestResults("11000018");
        testResultsSteps.statusCodeShouldBe(200);
        testResultsSteps.valueForFieldInPathShouldBe("[0].vin","P012301293847");
        testResultsSteps.validateVehicleFieldValue("vehicleType", "hgv");
        testResultsSteps.validateVehicleFieldExists("vrm");
        testResultsSteps.validateVehicleFieldExists("odometerReading");
        testResultsSteps.validateVehicleFieldExists("odometerReadingUnits");
    }

    @Title("CVSB-6805 - API Consumer retrieve the Test results for the input Vin (TRL)")
    @Test
    public void testResultsForVinTrl() {

    vehicleDefaultSubmittedData
            .setSystemNumber(generateRandomExcludingValues(16, vehicleDefaultSubmittedData.build().getSystemNumber()))
            .setTestResultId(generateRandomExcludingValues(3,vehicleDefaultSubmittedData.build().getTestResultId()))
            .setCountryOfRegistration("a")
            .setEuVehicleCategory("o3")
            .setNoOfAxles(6)
            .setReasonForCancellation(null)
            .setTestEndTimestamp("2019-09-12T16:42:14.757Z")
            .setTesterEmailAddress("cvs.automation3@dvsagov.onmicrosoft.com")
            .setTesterName("cvs.automation3@dvsagov.onmicrosoft.com")
            .setTestStationName("Abshire-Kub")
            .setTestStationPNumber("09-4129632")
            .setTestStationType("gvts")
            .setVehicleType("trl")
            .setVehicleConfiguration("semi-trailer")
            .setTestStatus("submitted").build()
            .getTestTypes().get(0).setAdditionalCommentsForAbandon(null);

    vehicleDefaultSubmittedData.build().getTestTypes().get(0).setAdditionalNotesRecorded("a note");
    vehicleDefaultSubmittedData.build().getTestTypes().get(0).setCertificateNumber(null);
    vehicleDefaultSubmittedData.build().getTestTypes().get(0).setName("Retest");
    vehicleDefaultSubmittedData.build().getTestTypes().get(0).setProhibitionIssued(false);
    vehicleDefaultSubmittedData.build().getTestTypes().get(0).setReasonForAbandoning(null);
    vehicleDefaultSubmittedData.build().getTestTypes().get(0).setTestResult("fail");
    vehicleDefaultSubmittedData.build().getTestTypes().get(0).setTestTypeId("");
    vehicleDefaultSubmittedData.build().getTestTypes().get(0).setTestTypeName("Paid roadworthiness retest");
    vehicleDefaultSubmittedData.build().getVehicleClass().setCode("t").setDescription("trailer");

    vehicleDefaultSubmittedData.build().getTestTypes().get(0).getDefects().get(0)
            .setDeficiencyCategory("dangerous")
            .setDeficiencyId("a")
            .setDeficiencyRef("6.2.a.ii")
            .setDeficiencySubId("ii")
            .setDeficiencyText("with any visible elongation of a stud hole where secure fixing of a wheel is affected.")
            .setImDescription("Road Wheels and Hubs")
            .setImNumber(6)
            .setItemDescription("A wheel:")
            .setItemNumber(2)
            .setProhibitionIssued(true)
            .setPrs(false)
            .setStdForProhibition(false);

    ObjectMapper objectMapper = new ObjectMapper();
    ObjectNode payload = objectMapper.valueToTree(vehicleDefaultSubmittedData.build());

    testResultsSteps.addAdditionalTestResultsFieldValue(payload, "trailerId", "C234567");
    testResultsSteps.removeTestResultsFields(payload, "numberOfSeats", "odometerReading", "odometerReadingUnits", "vehicleId", "vehicleSize", "vrm");
    testResultsSteps.removeTestResultsTestTypesFields(payload, 0, "certificateLink", "createdAt", "lastSeatbeltInstallationCheckDate", "lastUpdatedAt", "numberOfSeatbeltsFitted", "seatbeltInstallationCheckDate", "testCode", "testNumber", "testExpiryDate", "testAnniversaryDate");

    testResultsSteps.postTestResultsPayload(payload);
    testResultsSteps.statusCodeShouldBe(201);
    testResultsSteps.validateData("Test records created");

    testResultsSteps.getTestResults(vehicleDefaultSubmittedData.build().getSystemNumber(), TestResultsStatus.SUBMITTED);
    testResultsSteps.statusCodeShouldBe(200);

    testResultsSteps.validateVehicleFieldValue("vehicleType", "trl");
    testResultsSteps.validateVehicleFieldValue("vehicleConfiguration", "semi-trailer");
    testResultsSteps.validateVehicleFieldValue("vin", vehicleDefaultSubmittedData.build().getVin());
    testResultsSteps.validateVehicleFieldValue("vehicleType", "trl");
    }

    @Title("CVSB-8703 - Iteration on test results API specs to cover the logic of First test expiry date generation for HGV/TRL certificates - hgv")
    @Test
    public void testResultsForRegnDateHgv() {
        testResultsSteps.getTestResults("11000018");
        testResultsSteps.statusCodeShouldBe(200);
        testResultsSteps.validateVehicleFieldValue("vin", "P012301293847");
        testResultsSteps.validateVehicleFieldValue("vehicleType", "hgv");
        testResultsSteps.validateVehicleFieldExists("regnDate");
    }

    @Title("CVSB-8703 - Iteration on test results API specs to cover the logic of First test expiry date generation for HGV/TRL certificates - trl")
    @Test
    public void testResultsForFirstUseDateTrl() {
        testResultsSteps.getTestResults("11000019");
        testResultsSteps.statusCodeShouldBe(200);
        testResultsSteps.validateVehicleFieldValue("vin", "T12111111");
        testResultsSteps.validateVehicleFieldValue("vehicleType", "trl");
        testResultsSteps.validateVehicleFieldMayExist("firstUseDate");
    }


    @Ignore("VTM")
    @Title("CVSB-10280 - AC1: Update the GET request in testResults API")
    @Test
    public void testResultsContains() {
        testResultsSteps.getTestResults("10000013");
        testResultsSteps.statusCodeShouldBe(200);
        testResultsSteps.validateVehicleFieldExists("testVersion");
        testResultsSteps.validateVehicleFieldExists("reasonForCreation");
        testResultsSteps.validateVehicleFieldExists("createdByName");
        testResultsSteps.validateVehicleFieldExists("createdById");
        testResultsSteps.validateVehicleFieldExists("createdAt");
        testResultsSteps.validateVehicleFieldExists("lastUpdatedByName");
        testResultsSteps.validateVehicleFieldExists("lastUpdatedByID");
        testResultsSteps.validateVehicleFieldExists("lastUpdatedAt");

    }

    @WithTag("Vtm")
    @Title("CVSB-10280 - AC7: PUT request: Original Test Record is updated and attributes are automatically set - Archived and Archived " +
            "AC6: GET request returns all 'archived' test records")
    @Test
    public void testResultsWithTestVersionAutomaticallySetSame() {
        testResultsSteps.getTestResults("1000000114", TestVersion.ARCHIVED ,"114");
        testResultsSteps.statusCodeShouldBe(200);
        testResultsSteps.valueForFieldInPathShouldBe("[0].testVersion","archived");
        testResultsSteps.valueForFieldInPathShouldBe("[1].testVersion","archived");
        testResultsSteps.fieldInPathShouldNotExist("[0]","testHistory");

        testResultsSteps.getTestResults("1000000114", TestVersion.CURRENT ,"114");
        testResultsSteps.statusCodeShouldBe(404);
        testResultsSteps.validateData("No resources match the search criteria");

        testResultsSteps.getTestResults("1000000114", TestVersion.ALL ,"114");
        testResultsSteps.statusCodeShouldBe(200);
        testResultsSteps.valueForFieldInPathShouldBe("[0].testVersion","archived");
        testResultsSteps.valueForFieldInPathShouldBe("[0].testHistory[0].testVersion","archived");
    }
}
