package testresults;

import data.TestResultsData;
import model.testresults.TestResults;
import data.GenericData;
import util.JsonPathAlteration;
import model.testresults.TestResultsGet;
import model.testresults.TestResultsStatus;
import model.testresults.TestTypes;
import java.util.*;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import steps.TestResultsSteps;

import static util.DataUtil.generateRandomExcludingValues;


@RunWith(SerenityRunner.class)
public class PostTestResultsPozDefectsLvlCancelled {

    @Steps
    TestResultsSteps testResultsSteps;

    private TestResults.Builder vehicleCancelledDataOld = TestResultsData.buildTestResultsCancelledDataOld();

    private void validateSavedDataOld() {

        testResultsSteps.getTestResults(vehicleCancelledDataOld.build().getSystemNumber(), TestResultsStatus.CANCELED);
        testResultsSteps.statusCodeShouldBe(200);
        testResultsSteps.validateData((TestResultsGet) vehicleCancelledDataOld.build());
    }


    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - imNumber")
    @Test
    public void testResultsRandomImNumber() {

        String testResultRecord = GenericData.readJsonValueFromFile("test-results_cancelled.json", "$");

        String randomVin = GenericData.generateRandomVin();
        String randomVrm = GenericData.generateRandomVrm();
        String randomTestResultId = UUID.randomUUID().toString();
        String randomSystemNumber = GenericData.generateRandomSystemNumber();

        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationVrm = new JsonPathAlteration("$.vrm", randomVrm, "", "REPLACE");
        JsonPathAlteration alterationTestStatus = new JsonPathAlteration("$.testStatus", "cancelled", "", "REPLACE");
        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$.systemNumber", randomSystemNumber,"","REPLACE");
        JsonPathAlteration alterationTestResultId = new JsonPathAlteration("$.testResultId", randomTestResultId, "", "REPLACE");
        JsonPathAlteration alterationImDescription = new JsonPathAlteration("$.testTypes[0].defects[0].imNumber", RandomStringUtils.randomNumeric(5), "", "REPLACE");
        JsonPathAlteration alterationTestCode = new JsonPathAlteration("$.testTypes[0].testCode", "","","DELETE");
        JsonPathAlteration alterationTestNumber = new JsonPathAlteration("$.testTypes[0].testNumber", "","","DELETE");
        JsonPathAlteration alterationLastUpdatedAt = new JsonPathAlteration("$.testTypes[0].lastUpdatedAt", "","","DELETE");
        JsonPathAlteration alterationCreatedAt = new JsonPathAlteration("$.testTypes[0].createdAt", "","","DELETE");
        JsonPathAlteration alterationCertificateLink = new JsonPathAlteration("$.testTypes[0].certificateLink", "","","DELETE");
        JsonPathAlteration alterationVehicleId = new JsonPathAlteration("$.vehicleId", "","","DELETE");


        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(
                alterationVrm,
                alterationTestStatus,
                alterationVin,
                alterationImDescription,
                alterationSystemNumber,
                alterationTestResultId,
                alterationTestCode,
                alterationTestNumber,
                alterationLastUpdatedAt,
                alterationCreatedAt,
                alterationVehicleId,
                alterationCertificateLink
        ));

        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord,alterations);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
    }


    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - imDescription")
    @Test
    public void testResultsRandomImDescription() {

        String testResultRecord = GenericData.readJsonValueFromFile("test-results_cancelled.json", "$");

        String randomVin = GenericData.generateRandomVin();
        String randomVrm = GenericData.generateRandomVrm();
        String randomTestResultId = UUID.randomUUID().toString();
        String randomSystemNumber = GenericData.generateRandomSystemNumber();

        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationVrm = new JsonPathAlteration("$.vrm", randomVrm, "", "REPLACE");
        JsonPathAlteration alterationTestStatus = new JsonPathAlteration("$.testStatus", "cancelled", "", "REPLACE");
        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$.systemNumber", randomSystemNumber,"","REPLACE");
        JsonPathAlteration alterationTestResultId = new JsonPathAlteration("$.testResultId", randomTestResultId, "", "REPLACE");
        JsonPathAlteration alterationImDescription = new JsonPathAlteration("$.testTypes[0].defects[0].imDescription", RandomStringUtils.randomAlphanumeric(14), "", "REPLACE");
        JsonPathAlteration alterationTestCode = new JsonPathAlteration("$.testTypes[0].testCode", "","","DELETE");
        JsonPathAlteration alterationTestNumber = new JsonPathAlteration("$.testTypes[0].testNumber", "","","DELETE");
        JsonPathAlteration alterationLastUpdatedAt = new JsonPathAlteration("$.testTypes[0].lastUpdatedAt", "","","DELETE");
        JsonPathAlteration alterationCreatedAt = new JsonPathAlteration("$.testTypes[0].createdAt", "","","DELETE");
        JsonPathAlteration alterationCertificateLink = new JsonPathAlteration("$.testTypes[0].certificateLink", "","","DELETE");
        JsonPathAlteration alterationVehicleId = new JsonPathAlteration("$.vehicleId", "","","DELETE");


        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(
                alterationVrm,
                alterationTestStatus,
                alterationVin,
                alterationImDescription,
                alterationSystemNumber,
                alterationTestResultId,
                alterationTestCode,
                alterationTestNumber,
                alterationLastUpdatedAt,
                alterationCreatedAt,
                alterationVehicleId,
                alterationCertificateLink
        ));

        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord,alterations);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3486 - API Consumer creates a new test results for submitted/canceled with no min restriction - imDescription - empty")
    @Test
    public void testResultsEmptyImDescription() {

        ((TestTypes) vehicleCancelledDataOld.getTestTypes().get(0)).getDefects().get(0).setImDescription("");

        testResultsSteps.postTestResults(vehicleCancelledDataOld.setVin(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(16, vehicleCancelledDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledDataOld.build().getVrm())).build());
        testResultsSteps.statusCodeShouldBe(201);
        vehicleCancelledDataOld.build().getTestTypes().get(0).getDefects().get(0).setImDescription(null);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld();
    }


    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - itemDescription")
    @Test
    public void testResultsRandomItemDescription() {

        ((TestTypes) vehicleCancelledDataOld.getTestTypes().get(0)).getDefects().get(0).setItemDescription(RandomStringUtils.randomAlphanumeric(14));

        testResultsSteps.postTestResults(vehicleCancelledDataOld.setVin(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(16, vehicleCancelledDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledDataOld.build().getVrm())).build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld();
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3486 - API Consumer creates a new test results for submitted/canceled with no min restriction - itemDescription")
    @Test
    public void testResultsEmptyItemDescription() {

        ((TestTypes) vehicleCancelledDataOld.getTestTypes().get(0)).getDefects().get(0).setItemDescription("");

        testResultsSteps.postTestResults(vehicleCancelledDataOld.setVin(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(16, vehicleCancelledDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledDataOld.build().getVrm())).build());
        vehicleCancelledDataOld.build().getTestTypes().get(0).getDefects().get(0).setItemDescription(null);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld();
    }


    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - deficiencyRef")
    @Test
    public void testResultsRandomDeficiencyRef() {

        ((TestTypes) vehicleCancelledDataOld.getTestTypes().get(0)).getDefects().get(0).setDeficiencyRef(RandomStringUtils.randomAlphanumeric(14));

        testResultsSteps.postTestResults(vehicleCancelledDataOld.setVin(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(16, vehicleCancelledDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledDataOld.build().getVrm())).build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld();
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3486 - API Consumer creates a new test results for submitted/canceled with no min restriction - deficiencyRef")
    @Test
    public void testResultsEmptyDeficiencyRef() {

        ((TestTypes) vehicleCancelledDataOld.getTestTypes().get(0)).getDefects().get(0).setDeficiencyRef("");

        testResultsSteps.postTestResults(vehicleCancelledDataOld.setVin(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(16, vehicleCancelledDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledDataOld.build().getVrm())).build());
        testResultsSteps.statusCodeShouldBe(201);
        vehicleCancelledDataOld.build().getTestTypes().get(0).getDefects().get(0).setDeficiencyRef(null);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld();
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - deficiencyId")
    @Test
    public void testResultsRandomAlphabeticLowerCaseStringDeficiencyId() {

        String propertyValue = RandomStringUtils.randomAlphabetic(1).toLowerCase();
        vehicleCancelledDataOld.build().getTestTypes().get(0).getDefects().get(0).setDeficiencyId(propertyValue);

        testResultsSteps.postTestResults(vehicleCancelledDataOld.setVin(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(16, vehicleCancelledDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledDataOld.build().getVrm())).build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld();
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3504 - TCD - API Consumer creates a new test result for submitted/canceled that allows null values - deficiencyId")
    @Test
    public void testResultsNullDeficiencyId() {

        vehicleCancelledDataOld.build().getTestTypes().get(0).getDefects().get(0).setDeficiencyId(null);

        testResultsSteps.postTestResults(vehicleCancelledDataOld.setVin(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(16, vehicleCancelledDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledDataOld.build().getVrm())).build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld();
    }


    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3504 - TCD - API Consumer creates a new test result for submitted/canceled that allows null values - deficiencySubId")
    @Test
    public void testResultsMissingDeficiencySubId() {

        ((TestTypes) vehicleCancelledDataOld.getTestTypes().get(0)).getDefects().get(0).setDeficiencySubId(null);


        testResultsSteps.postTestResults(vehicleCancelledDataOld.setVin(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(16, vehicleCancelledDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledDataOld.build().getVrm())).build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld();
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - deficiencySubId m")
    @Test
    public void testResultsValidDeficiencySubIdValueOne() {

        ((TestTypes) vehicleCancelledDataOld.getTestTypes().get(0)).getDefects().get(0).setDeficiencySubId("m");

        testResultsSteps.postTestResults(vehicleCancelledDataOld.setVin(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(16, vehicleCancelledDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledDataOld.build().getVrm())).build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld();
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - deficiencySubId d")
    @Test
    public void testResultsValidDeficiencySubIdValueTwo() {

        ((TestTypes) vehicleCancelledDataOld.getTestTypes().get(0)).getDefects().get(0).setDeficiencySubId("d");

        testResultsSteps.postTestResults(vehicleCancelledDataOld.setVin(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(16, vehicleCancelledDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledDataOld.build().getVrm())).build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld();
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - deficiencySubId c")
    @Test
    public void testResultsValidDeficiencySubIdValueThree() {

        ((TestTypes) vehicleCancelledDataOld.getTestTypes().get(0)).getDefects().get(0).setDeficiencySubId("c");

        testResultsSteps.postTestResults(vehicleCancelledDataOld.setVin(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(16, vehicleCancelledDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledDataOld.build().getVrm())).build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld();
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - deficiencySubId l")
    @Test
    public void testResultsValidDeficiencySubIdValueFour() {

        ((TestTypes) vehicleCancelledDataOld.getTestTypes().get(0)).getDefects().get(0).setDeficiencySubId("l");

        testResultsSteps.postTestResults(vehicleCancelledDataOld.setVin(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(16, vehicleCancelledDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledDataOld.build().getVrm())).build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld();
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - deficiencySubId x")
    @Test
    public void testResultsValidDeficiencySubIdValueFive() {

        String testResultRecord = GenericData.readJsonValueFromFile("test-results_cancelled.json", "$");

        String randomVin = GenericData.generateRandomVin();
        String randomVrm = GenericData.generateRandomVrm();
        String randomTestResultId = UUID.randomUUID().toString();
        String randomSystemNumber = GenericData.generateRandomSystemNumber();

        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationVrm = new JsonPathAlteration("$.vrm", randomVrm, "", "REPLACE");
        JsonPathAlteration alterationTestStatus = new JsonPathAlteration("$.testStatus", "cancelled", "", "REPLACE");
        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$.systemNumber", randomSystemNumber,"","REPLACE");
        JsonPathAlteration alterationTestResultId = new JsonPathAlteration("$.testResultId", randomTestResultId, "", "REPLACE");
        JsonPathAlteration alterationDeficiencySubId = new JsonPathAlteration("$.testTypes[0].defects[0].deficiencySubId", "x", "", "REPLACE");
        JsonPathAlteration alterationTestCode = new JsonPathAlteration("$.testTypes[0].testCode", "","","DELETE");
        JsonPathAlteration alterationTestNumber = new JsonPathAlteration("$.testTypes[0].testNumber", "","","DELETE");
        JsonPathAlteration alterationLastUpdatedAt = new JsonPathAlteration("$.testTypes[0].lastUpdatedAt", "","","DELETE");
        JsonPathAlteration alterationCreatedAt = new JsonPathAlteration("$.testTypes[0].createdAt", "","","DELETE");
        JsonPathAlteration alterationCertificateLink = new JsonPathAlteration("$.testTypes[0].certificateLink", "","","DELETE");
        JsonPathAlteration alterationVehicleId = new JsonPathAlteration("$.vehicleId", "","","DELETE");

        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(
                alterationVrm,
                alterationTestStatus,
                alterationVin,
                alterationSystemNumber,
                alterationTestResultId,
                alterationDeficiencySubId,
                alterationTestCode,
                alterationTestNumber,
                alterationLastUpdatedAt,
                alterationCreatedAt,
                alterationVehicleId,
                alterationCertificateLink
        ));

        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord,alterations);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - deficiencySubId v")
    @Test
    public void testResultsValidDeficiencySubIdValueSix() {

        ((TestTypes) vehicleCancelledDataOld.getTestTypes().get(0)).getDefects().get(0).setDeficiencySubId("v");

        testResultsSteps.postTestResults(vehicleCancelledDataOld.setVin(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(16, vehicleCancelledDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledDataOld.build().getVrm())).build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld();
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - deficiencySubId i")
    @Test
    public void testResultsValidDeficiencySubIdValueSeven() {

        ((TestTypes) vehicleCancelledDataOld.getTestTypes().get(0)).getDefects().get(0).setDeficiencySubId("i");

        testResultsSteps.postTestResults(vehicleCancelledDataOld.setVin(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(16, vehicleCancelledDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledDataOld.build().getVrm())).build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld();
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - deficiencyCategory advisory")
    @Test
    public void testResultsDeficiencyCategoryValueOne() {

        ((TestTypes) vehicleCancelledDataOld.getTestTypes().get(0)).getDefects().get(0).setDeficiencyCategory("advisory");

        testResultsSteps.postTestResults(vehicleCancelledDataOld.setVin(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(16, vehicleCancelledDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledDataOld.build().getVrm())).build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld();
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - deficiencyCategory dangerous")
    @Test
    public void testResultsDeficiencyCategoryValueTwo() {

        ((TestTypes) vehicleCancelledDataOld.getTestTypes().get(0)).getDefects().get(0).setDeficiencyCategory("dangerous");

        testResultsSteps.postTestResults(vehicleCancelledDataOld.setVin(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(16, vehicleCancelledDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledDataOld.build().getVrm())).build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld();
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - deficiencyCategory major")
    @Test
    public void testResultsDeficiencyCategoryValueThree() {

        ((TestTypes) vehicleCancelledDataOld.getTestTypes().get(0)).getDefects().get(0).setDeficiencyCategory("major");

        testResultsSteps.postTestResults(vehicleCancelledDataOld.setVin(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(16, vehicleCancelledDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledDataOld.build().getVrm())).build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld();
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - deficiencyCategory minor")
    @Test
    public void testResultsValueDeficiencyCategoryFour() {

        ((TestTypes) vehicleCancelledDataOld.getTestTypes().get(0)).getDefects().get(0).setDeficiencyCategory("minor");

        testResultsSteps.postTestResults(vehicleCancelledDataOld.setVin(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(16, vehicleCancelledDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledDataOld.build().getVrm())).build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld();
    }


    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3504 - TCD - API Consumer creates a new test result for submitted/canceled that allows null values - deficiencyText")
    @Test
    public void testResultsNullAdvisoryDeficiencyText() {

        vehicleCancelledDataOld.setVin(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(16, vehicleCancelledDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledDataOld.build().getVrm())).build().getTestTypes().get(0).getDefects().get(0)
                .setDeficiencyCategory("advisory").setDeficiencyText(null);

        testResultsSteps.postTestResults(vehicleCancelledDataOld.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld();

    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3486 - API Consumer creates a new test results for submitted/canceled with no min restriction - deficiencyText")
    @Test
    public void testResultsEmptyDeficiencyText() {

        vehicleCancelledDataOld.setVin(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(16, vehicleCancelledDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledDataOld.build().getVrm())).build()
                .getTestTypes().get(0).getDefects().get(0).setDeficiencyText("");

        testResultsSteps.postTestResults(vehicleCancelledDataOld.build());
        vehicleCancelledDataOld.build().getTestTypes().get(0).getDefects().get(0).setDeficiencyText(null);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld();

    }


    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - stdForProhibition false")
    @Test
    public void testResultsStdForProhibitionFalse() {

        vehicleCancelledDataOld.setVin(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(16, vehicleCancelledDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledDataOld.build().getVrm())).build()
                .getTestTypes().get(0).getDefects().get(0).setStdForProhibition(false);

        testResultsSteps.postTestResults(vehicleCancelledDataOld.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld();

    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - stdForProhibition true")
    @Test
    public void testResultsStdForProhibitionTrue() {

        vehicleCancelledDataOld.setVin(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(16, vehicleCancelledDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledDataOld.build().getVrm())).build()
                .getTestTypes().get(0).getDefects().get(0).setStdForProhibition(true);

        testResultsSteps.postTestResults(vehicleCancelledDataOld.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld();

    }

    @Title("CVSB-3205 - Consumer creates a new test results for the submitted/cancelled test - prohibitionIssued false")
    @Test
    public void testResultsProhibitionIssuedFalse() {

        vehicleCancelledDataOld.setVin(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(16, vehicleCancelledDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledDataOld.build().getVrm())).build()
                .getTestTypes().get(0).getDefects().get(0).setProhibitionIssued(false);

        testResultsSteps.postTestResults(vehicleCancelledDataOld.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld();

    }

    @Title("CVSB-3205 - Consumer creates a new test results for the submitted/cancelled test - prohibitionIssued true")
    @Test
    public void testResultsProhibitionIssuedTrue() {

        vehicleCancelledDataOld.setVin(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(16, vehicleCancelledDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledDataOld.build().getVrm())).build()
                .getTestTypes().get(0).getDefects().get(0).setProhibitionIssued(true);

        testResultsSteps.postTestResults(vehicleCancelledDataOld.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld();

    }



    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - prs false")
    @Test
    public void testResultsPrsFalse() {

        vehicleCancelledDataOld.setVin(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(16, vehicleCancelledDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledDataOld.build().getVrm())).build()
                .getTestTypes().get(0).getDefects().get(0).setPrs(false);

        testResultsSteps.postTestResults(vehicleCancelledDataOld.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld();

    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - prs true")
    @Test
    public void testResultsPrsTrue() {

        vehicleCancelledDataOld.setVin(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(16, vehicleCancelledDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledDataOld.build().getVrm())).build()
                .getTestTypes().get(0).getDefects().get(0).setPrs(true);

        testResultsSteps.postTestResults(vehicleCancelledDataOld.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld();

    }


}