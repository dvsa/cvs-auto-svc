package testresults;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import data.TestResultsData;
import model.testresults.TestResults;
import model.testresults.TestResultsGet;
import model.testresults.TestResultsStatus;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import net.thucydides.core.annotations.WithTag;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import steps.TestResultsSteps;
import util.DataUtil;

import java.util.Random;

import static util.DataUtil.generateRandomExcludingValues;


@RunWith(SerenityRunner.class)
public class PostTestResultsPozTestTypesCancelledLvl {

    @Steps
    TestResultsSteps testResultsSteps;

    private TestResults.Builder vehicleCancelledDataOld = TestResultsData.buildTestResultsCancelledDataOld();

    private void validateSavedDataOld() {
        testResultsSteps.getTestResults(vehicleCancelledDataOld.build().getSystemNumber(), TestResultsStatus.CANCELED);
        testResultsSteps.statusCodeShouldBe(200);
        testResultsSteps.validateData((TestResultsGet) vehicleCancelledDataOld.build());
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - testTypeName")
    @Test
    public void testResultsRandomTestTypeName() {

        vehicleCancelledDataOld.setVin(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(16, vehicleCancelledDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledDataOld.build().getVrm())).build()
                .getTestTypes().get(0).setTestTypeName(RandomStringUtils.randomAlphanumeric(50));

        testResultsSteps.postTestResults(vehicleCancelledDataOld.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld();
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3486 - API Consumer creates a new test results for submitted/canceled with no min restriction - testTypeName")
    @Test
    public void testResultsEmptyTestTypeName() {

        vehicleCancelledDataOld.setVin(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(16, vehicleCancelledDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledDataOld.build().getVrm())).build()
                .getTestTypes().get(0).setTestTypeName("");

        testResultsSteps.postTestResults(vehicleCancelledDataOld.build());
        vehicleCancelledDataOld.build().getTestTypes().get(0).setTestTypeName(null);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld();
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - name")
    @Test
    public void testResultsRandomName() {
        vehicleCancelledDataOld.setVin(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(16, vehicleCancelledDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledDataOld.build().getVrm())).build()
                .getTestTypes().get(0).setName(RandomStringUtils.randomAlphanumeric(50));

        testResultsSteps.postTestResults(vehicleCancelledDataOld.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld();
    }


    //TODO - possible problem with gateway
    @Ignore ("random testTypeId should have returned an error - instead returns bad Gateway - defect CVSB-9011")
    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - testTypeId")
    public void testResultsRandomTestTypeId() {

        vehicleCancelledDataOld.setVin(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(16, vehicleCancelledDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledDataOld.build().getVrm())).build()
                .getTestTypes().get(0).setTestTypeId(RandomStringUtils.randomAlphanumeric(50));

        testResultsSteps.postTestResults(vehicleCancelledDataOld.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld();
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3486 - API Consumer creates a new test results for submitted/canceled with no min restriction - testTypeId")
    @Test
    public void testResultsEmptyTestTypeId() {

        vehicleCancelledDataOld.setVin(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(16, vehicleCancelledDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledDataOld.build().getVrm())).build()
                .getTestTypes().get(0).setTestTypeId("");

        testResultsSteps.postTestResults(vehicleCancelledDataOld.build());
        vehicleCancelledDataOld.build().getTestTypes().get(0).setTestTypeId(null);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - certificateNumber")
    @Test
    public void testResultsRandomCertificateNumber() {

        vehicleCancelledDataOld.setVin(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(16, vehicleCancelledDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledDataOld.build().getVrm())).build()
                .getTestTypes().get(0).setCertificateNumber(RandomStringUtils.randomAlphanumeric(50));

        testResultsSteps.postTestResults(vehicleCancelledDataOld.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld();
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3486 - API Consumer creates a new test results for submitted/canceled with no min restriction - certificateNumber")
    @Test
    public void testResultsEmptyCertificateNumber() {

        vehicleCancelledDataOld.setVin(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(16, vehicleCancelledDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledDataOld.build().getVrm())).build()
                .getTestTypes().get(0).setTestTypeId("19").setCertificateNumber("");

        testResultsSteps.postTestResults(vehicleCancelledDataOld.build());
//        vehicleCancelledDataOld.build().getTestTypes().get(0).setCertificateNumber(null);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld();
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3504 - TCD - API Consumer creates a new test result for submitted/canceled that allows null values - certificateNumber")
    @Test
    public void testResultsNullCertificateNumber() {

        vehicleCancelledDataOld.setVin(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(16, vehicleCancelledDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledDataOld.build().getVrm())).build()
                .getTestTypes().get(0).setTestTypeId("19").setCertificateNumber(null);

        testResultsSteps.postTestResults(vehicleCancelledDataOld.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld();
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - testTypeStartTimestamp")
    @Test
    public void testResultsValidTestTypeStartTimestamp() {

        vehicleCancelledDataOld.setVin(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(16, vehicleCancelledDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledDataOld.build().getVrm())).build()
                .getTestTypes().get(0).setTestTypeStartTimestamp(DataUtil.buildCurrentDateTime());

        testResultsSteps.postTestResults(vehicleCancelledDataOld.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld();
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - testTypeEndTimestamp")
    @Test
    public void testResultsRandomTestTypeEndTimestamp() {

        vehicleCancelledDataOld.setVin(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(16, vehicleCancelledDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledDataOld.build().getVrm())).build()
                .getTestTypes().get(0).setTestTypeEndTimestamp(DataUtil.buildCurrentDateTime());

        testResultsSteps.postTestResults(vehicleCancelledDataOld.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld();
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3504 - TCD - API Consumer creates a new test result for submitted/canceled that allows null values - testTypeEndTimestamp")
    @Test
    public void testResultsNullTestTestTypeEndTimestamp() {

        vehicleCancelledDataOld.setVin(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(16, vehicleCancelledDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledDataOld.build().getVrm())).build()
                .getTestTypes().get(0).setTestTypeEndTimestamp(null);

        testResultsSteps.postTestResults(vehicleCancelledDataOld.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld();
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - numberOfSeatbeltsFitted")
    @Test
    public void testResultsRandomNumberOfSeatbeltsFitted() {

        vehicleCancelledDataOld.setVin(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(16, vehicleCancelledDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledDataOld.build().getVrm())).build()
                .getTestTypes().get(0).setNumberOfSeatbeltsFitted(new Random().nextInt(999) + 10);

        testResultsSteps.postTestResults(vehicleCancelledDataOld.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld();
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - numberOfSeatbeltsFitted zero")
    @Test
    public void testResultsMiNumberOfSeatbeltsFitted() {

        vehicleCancelledDataOld.setVin(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(16, vehicleCancelledDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledDataOld.build().getVrm())).build()
                .getTestTypes().get(0).setNumberOfSeatbeltsFitted(0);

        testResultsSteps.postTestResults(vehicleCancelledDataOld.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld();
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3504 - TCD - API Consumer creates a new test result for submitted/canceled that allows null values - numberOfSeatbeltsFitted")
    @Test
    public void testResultsNullNumberOfSeatbeltsFitted() {

        vehicleCancelledDataOld.setVin(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(16, vehicleCancelledDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledDataOld.build().getVrm())).build()
                .getTestTypes().get(0).setNumberOfSeatbeltsFitted(null);

        testResultsSteps.postTestResults(vehicleCancelledDataOld.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld();
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - lastSeatbeltInstallationCheckDate")
    @Test
    public void testResultsValidLastSeatbeltInstallationCheckDate() {

        vehicleCancelledDataOld.setVin(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(16, vehicleCancelledDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledDataOld.build().getVrm())).build()
                .getTestTypes().get(0).setLastSeatbeltInstallationCheckDate(DataUtil.buildCurrentDateTime());

        testResultsSteps.postTestResults(vehicleCancelledDataOld.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld();
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3504 - TCD - API Consumer creates a new test result for submitted/canceled that allows null values - lastSeatbeltInstallationCheckDate")
    @Test
    public void testResultsNullLastSeatbeltInstallationCheckDate() {

        vehicleCancelledDataOld.setVin(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(16, vehicleCancelledDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledDataOld.build().getVrm())).build()
                .getTestTypes().get(0).setLastSeatbeltInstallationCheckDate(null);

        testResultsSteps.postTestResults(vehicleCancelledDataOld.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld();
    }


    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - seatbeltInstallationCheckDate true")
    @Test
    public void testResultsTrueSeatbeltInstallationCheckDate() {

        vehicleCancelledDataOld.setVin(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(16, vehicleCancelledDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledDataOld.build().getVrm())).build()
                .getTestTypes().get(0).setSeatbeltInstallationCheckDate(true);

        testResultsSteps.postTestResults(vehicleCancelledDataOld.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld();
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - seatbeltInstallationCheckDate false")
    @Test
    public void testResultsFalseSeatbeltInstallationCheckDate() {

        vehicleCancelledDataOld.setVin(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(16, vehicleCancelledDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledDataOld.build().getVrm())).build()
                .getTestTypes().get(0).setSeatbeltInstallationCheckDate(false);

        testResultsSteps.postTestResults(vehicleCancelledDataOld.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld();
    }
    
    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3504 - TCD - API Consumer creates a new test result for submitted/canceled that allows null values - seatbeltInstallationCheckDate")
    @Test
    public void testResultsNullSeatbeltInstallationCheckDate() {

        vehicleCancelledDataOld.setVin(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(16, vehicleCancelledDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledDataOld.build().getVrm())).build()
                .getTestTypes().get(0).setSeatbeltInstallationCheckDate(null);

        testResultsSteps.postTestResults(vehicleCancelledDataOld.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld();
    }


    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3504 - TCD - API Consumer creates a new test result for submitted/canceled that allows null values - testResult")
    @Test
    public void testResultsNullTestResult() {

        vehicleCancelledDataOld.setVin(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(16, vehicleCancelledDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledDataOld.build().getVrm())).build()
                .getTestTypes().get(0).setTestResult(null);

        testResultsSteps.postTestResults(vehicleCancelledDataOld.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld();
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - testResult fail")
    @Test
    public void testResultsTestResultValueOne() {

        vehicleCancelledDataOld.setVin(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(16, vehicleCancelledDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledDataOld.build().getVrm())).build()
                .getTestTypes().get(0).setTestResult("fail");

        testResultsSteps.postTestResults(vehicleCancelledDataOld.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld();
    }


    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - testResult pass")
    @Test
    public void testResultsTestResultValueTwo() {

        vehicleCancelledDataOld.setVin(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(16, vehicleCancelledDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledDataOld.build().getVrm())).build()
                .getTestTypes().get(0).setTestResult("pass");

        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode payload = objectMapper.valueToTree(vehicleCancelledDataOld.build());

        testResultsSteps.addAdditionalTestResultsTestTypesFields(payload, 0, "particulateTrapFitted", "New improved particulateTrap");
        testResultsSteps.addAdditionalTestResultsTestTypesFields(payload, 0, "particulateTrapSerialNumber", "12481632");
        testResultsSteps.addAdditionalTestResultsTestTypesFields(payload, 0, "modificationTypeUsed", "Big filter");
        testResultsSteps.addAdditionalTestResultsTestTypesFields(payload, 0, "smokeTestKLimitApplied", "2.2");

        testResultsSteps.addAdditionalTestResultsTestTypesFields(payload, 0, "testExpiryDate", DataUtil.buildDate(DataUtil.buildCurrentDateTime(),-1));
        testResultsSteps.addAdditionalTestResultsTestTypesFields(payload, 0, "modType", "{}");
        testResultsSteps.addAdditionalTestResultsTestTypesFields(payload, 0, "emissionStandard", "Euro 3");
        testResultsSteps.addAdditionalTestResultsTestTypesFields(payload, 0, "fuelType", "petrol");
        testResultsSteps.removeTestResultsTestTypesFields(payload, 0, "testAnniversaryDate", "createdAt", "lastUpdatedAt", "testCode", "testNumber", "certificateLink");
        testResultsSteps.removeTestResultsFields(payload, "vehicleId");

        testResultsSteps.postTestResultsPayload(payload);

        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld();
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - testResult prs")
    @Test
    public void testResultsTestResultValueThree() {

        vehicleCancelledDataOld.setVin(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(16, vehicleCancelledDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledDataOld.build().getVrm())).build()
                .getTestTypes().get(0).setTestResult("prs");

        testResultsSteps.postTestResults(vehicleCancelledDataOld.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld();
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - testResult abandoned")
    @Test
    public void testResultsTestResultValueFour() {

        vehicleCancelledDataOld.setVin(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(16, vehicleCancelledDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledDataOld.build().getVrm())).build()
                .getTestTypes().get(0).setTestResult("abandoned");

        testResultsSteps.postTestResults(vehicleCancelledDataOld.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld();
    }


    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3504 - TCD - API Consumer creates a new test result for submitted/canceled that allows null values - prohibitionIssued")
    @Test
    public void testResultsNullProhibitionIssued() {

        vehicleCancelledDataOld.setVin(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(16, vehicleCancelledDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledDataOld.build().getVrm())).build()
                .getTestTypes().get(0).setProhibitionIssued(null);

        testResultsSteps.postTestResults(vehicleCancelledDataOld.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld();
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - prohibitionIssued false")
    @Test
    public void testResultsProhibitionIssuedFalse() {

        vehicleCancelledDataOld.setVin(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(16, vehicleCancelledDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledDataOld.build().getVrm())).build()
                .getTestTypes().get(0).setProhibitionIssued(false);

        testResultsSteps.postTestResults(vehicleCancelledDataOld.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld();
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - prohibitionIssued true")
    @Test
    public void testResultsProhibitionIssuedTrue() {

        vehicleCancelledDataOld.setVin(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(16, vehicleCancelledDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledDataOld.build().getVrm())).build()
                .getTestTypes().get(0).setProhibitionIssued(true);

        testResultsSteps.postTestResults(vehicleCancelledDataOld.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld();
    }


    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3486 - API Consumer creates a new test results for submitted/canceled with no min restriction - reasonForAbandoning with testResult abandoned")
    @Test
    public void testResultsReasonForAbandoningAbandonedEmpty() {

        vehicleCancelledDataOld.setVin(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(16, vehicleCancelledDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledDataOld.build().getVrm())).build().getTestTypes().get(0)
                .setTestResult("abandoned").setReasonForAbandoning("");

        testResultsSteps.postTestResults(vehicleCancelledDataOld.build());
        vehicleCancelledDataOld.build().getTestTypes().get(0).setReasonForAbandoning(null);
        testResultsSteps.statusCodeShouldBe(400);
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3486 - API Consumer creates a new test results for submitted/canceled with no min restriction - reasonForAbandoning with testResult fail")
    @Test
    public void testResultsReasonForAbandoningFailEmpty() {

        vehicleCancelledDataOld.setVin(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(16, vehicleCancelledDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledDataOld.build().getVrm())).build().getTestTypes().get(0)
                .setTestResult("fail").setReasonForAbandoning("");

        testResultsSteps.postTestResults(vehicleCancelledDataOld.build());
        vehicleCancelledDataOld.build().getTestTypes().get(0).setReasonForAbandoning(null);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld();
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3486 - API Consumer creates a new test results for submitted/canceled with no min restriction - reasonForAbandoning with testResult pass")
    @Test
    public void testResultsReasonForAbandoningPassEmpty() {

        vehicleCancelledDataOld.setVin(generateRandomExcludingValues(21, vehicleCancelledDataOld
                .setTestResultId(generateRandomExcludingValues(5,vehicleCancelledDataOld.build().getTestResultId()))
                .build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(16, vehicleCancelledDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledDataOld.build().getVrm())).build()
                .getTestTypes().get(0).setTestResult("pass").setReasonForAbandoning("");
        vehicleCancelledDataOld.build().getTestTypes().get(0).setReasonForAbandoning(null);

        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode payload = objectMapper.valueToTree(vehicleCancelledDataOld.build());

        testResultsSteps.addAdditionalTestResultsTestTypesFields(payload, 0, "particulateTrapFitted", "New improved particulateTrap");
        testResultsSteps.addAdditionalTestResultsTestTypesFields(payload, 0, "particulateTrapSerialNumber", "12481632");
        testResultsSteps.addAdditionalTestResultsTestTypesFields(payload, 0, "modificationTypeUsed", "Big filter");
        testResultsSteps.addAdditionalTestResultsTestTypesFields(payload, 0, "smokeTestKLimitApplied", "2.2");

        testResultsSteps.addAdditionalTestResultsTestTypesFields(payload, 0, "testExpiryDate", DataUtil.buildDate(DataUtil.buildCurrentDateTime(),-1));
        testResultsSteps.addAdditionalTestResultsTestTypesFields(payload, 0, "modType", "{}");
        testResultsSteps.addAdditionalTestResultsTestTypesFields(payload, 0, "emissionStandard", "Euro 3");
        testResultsSteps.addAdditionalTestResultsTestTypesFields(payload, 0, "fuelType", "petrol");
        testResultsSteps.removeTestResultsTestTypesFields(payload, 0, "testAnniversaryDate", "createdAt", "lastUpdatedAt", "testCode", "testNumber", "certificateLink");
        testResultsSteps.removeTestResultsFields(payload, "vehicleId");

        testResultsSteps.postTestResultsPayload(payload);

        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld();
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3486 - API Consumer creates a new test results for submitted/canceled with no min restriction - reasonForAbandoning with testResult prs")
    @Test
    public void testResultsReasonForAbandoningPrsEmpty() {

        vehicleCancelledDataOld.setVin(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(16, vehicleCancelledDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledDataOld.build().getVrm())).build()
                .getTestTypes().get(0).setTestResult("prs").setReasonForAbandoning("");

        testResultsSteps.postTestResults(vehicleCancelledDataOld.build());
        vehicleCancelledDataOld.build().getTestTypes().get(0).setReasonForAbandoning(null);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld();
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - reasonForAbandoning with testResult fail")
    @Test
    public void testResultsReasonForAbandoningFail() {

        vehicleCancelledDataOld.setVin(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(16, vehicleCancelledDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledDataOld.build().getVrm())).build().getTestTypes().get(0)
                .setTestResult("fail").setReasonForAbandoning(RandomStringUtils.randomAlphanumeric(1, 499));

        testResultsSteps.postTestResults(vehicleCancelledDataOld.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld();

    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - reasonForAbandoning with testResult pass")
    @Test
    public void testResultsReasonForAbandoningPass() {

        vehicleCancelledDataOld.setVin(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(16, vehicleCancelledDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledDataOld.build().getVrm())).build()
                .getTestTypes().get(0).setTestResult("pass").setReasonForAbandoning(RandomStringUtils.randomAlphanumeric(1, 499));

        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode payload = objectMapper.valueToTree(vehicleCancelledDataOld.build());

        testResultsSteps.addAdditionalTestResultsTestTypesFields(payload, 0, "particulateTrapFitted", "New improved particulateTrap");
        testResultsSteps.addAdditionalTestResultsTestTypesFields(payload, 0, "particulateTrapSerialNumber", "12481632");
        testResultsSteps.addAdditionalTestResultsTestTypesFields(payload, 0, "modificationTypeUsed", "Big filter");
        testResultsSteps.addAdditionalTestResultsTestTypesFields(payload, 0, "smokeTestKLimitApplied", "2.2");

        testResultsSteps.addAdditionalTestResultsTestTypesFields(payload, 0, "testExpiryDate", DataUtil.buildDate(DataUtil.buildCurrentDateTime(),-1));
        testResultsSteps.addAdditionalTestResultsTestTypesFields(payload, 0, "modType", "{}");
        testResultsSteps.addAdditionalTestResultsTestTypesFields(payload, 0, "emissionStandard", "Euro 3");
        testResultsSteps.addAdditionalTestResultsTestTypesFields(payload, 0, "fuelType", "petrol");
        testResultsSteps.removeTestResultsTestTypesFields(payload, 0, "testAnniversaryDate", "createdAt", "lastUpdatedAt", "testCode", "testNumber", "certificateLink");
        testResultsSteps.removeTestResultsFields(payload, "vehicleId");

        testResultsSteps.postTestResultsPayload(payload);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld();

    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - reasonForAbandoning with testResult prs")
    @Test
    public void testResultsReasonForAbandoningPrs() {

        vehicleCancelledDataOld.setVin(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(16, vehicleCancelledDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledDataOld.build().getVrm())).build()
                .getTestTypes().get(0).setTestResult("prs").setReasonForAbandoning(RandomStringUtils.randomAlphanumeric(1, 499));

        testResultsSteps.postTestResults(vehicleCancelledDataOld.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld();

    }


    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3504 - TCD - API Consumer creates a new test result for submitted/canceled that allows null values - reasonForAbandoning with testResult abandoned")
    @Test
    public void testResultsNullReasonForAbandoningFail() {

        vehicleCancelledDataOld.setVin(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(16, vehicleCancelledDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledDataOld.build().getVrm())).build()
                .getTestTypes().get(0).setTestResult("fail").setReasonForAbandoning(null);

        testResultsSteps.postTestResults(vehicleCancelledDataOld.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld();

    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3504 - TCD - API Consumer creates a new test result for submitted/canceled that allows null values - reasonForAbandoning with testResult pass")
    @Test
    public void testResultsNullReasonForAbandoningPass() {

        vehicleCancelledDataOld.setVin(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(16, vehicleCancelledDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledDataOld.build().getVrm())).build()
                .getTestTypes().get(0).setTestResult("pass").setReasonForAbandoning(null);

        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode payload = objectMapper.valueToTree(vehicleCancelledDataOld.build());

        testResultsSteps.addAdditionalTestResultsTestTypesFields(payload, 0, "particulateTrapFitted", "New improved particulateTrap");
        testResultsSteps.addAdditionalTestResultsTestTypesFields(payload, 0, "particulateTrapSerialNumber", "12481632");
        testResultsSteps.addAdditionalTestResultsTestTypesFields(payload, 0, "modificationTypeUsed", "Big filter");
        testResultsSteps.addAdditionalTestResultsTestTypesFields(payload, 0, "smokeTestKLimitApplied", "2.2");

        testResultsSteps.addAdditionalTestResultsTestTypesFields(payload, 0, "testExpiryDate", DataUtil.buildDate(DataUtil.buildCurrentDateTime(),-1));
        testResultsSteps.addAdditionalTestResultsTestTypesFields(payload, 0, "modType", "{}");
        testResultsSteps.addAdditionalTestResultsTestTypesFields(payload, 0, "emissionStandard", "Euro 3");
        testResultsSteps.addAdditionalTestResultsTestTypesFields(payload, 0, "fuelType", "petrol");
        testResultsSteps.removeTestResultsTestTypesFields(payload, 0, "testAnniversaryDate", "createdAt", "lastUpdatedAt", "testCode", "testNumber", "certificateLink");
        testResultsSteps.removeTestResultsFields(payload, "vehicleId");

        testResultsSteps.postTestResultsPayload(payload);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld();


    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3504 - TCD - API Consumer creates a new test result for submitted/canceled that allows null values - reasonForAbandoning with testResult prs")
    @Test
    public void testResultsNullReasonForAbandoningPrs() {

        vehicleCancelledDataOld.setVin(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(16, vehicleCancelledDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledDataOld.build().getVrm())).build()
                .getTestTypes().get(0).setTestResult("prs").setReasonForAbandoning(null);

        testResultsSteps.postTestResults(vehicleCancelledDataOld.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld();

    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - additionalNotesRecorded")
    @Test
    public void testResultsRandomAdditionalNotesRecorded() {

        vehicleCancelledDataOld.setVin(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(16, vehicleCancelledDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledDataOld.build().getVrm())).build().getTestTypes()
                .get(0).setAdditionalNotesRecorded(RandomStringUtils.randomAlphanumeric(1, 499));

        testResultsSteps.postTestResults(vehicleCancelledDataOld.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld();

    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3504 - TCD - API Consumer creates a new test result for submitted/canceled that allows null values - additionalNotesRecorded")
    @Test
    public void testResultsNullAdditionalNotesRecorded() {

        vehicleCancelledDataOld.setVin(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(16, vehicleCancelledDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledDataOld.build().getVrm())).build()
                .getTestTypes().get(0).setAdditionalNotesRecorded(null);

        testResultsSteps.postTestResults(vehicleCancelledDataOld.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld();

    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3486 - API Consumer creates a new test results for submitted/canceled with no min restriction - additionalNotesRecorded")
    @Test
    public void testResultsEmptyAdditionalNotesRecorded() {

        vehicleCancelledDataOld.setVin(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(16, vehicleCancelledDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledDataOld.build().getVrm())).build()
                .getTestTypes().get(0).setAdditionalNotesRecorded("");

        testResultsSteps.postTestResults(vehicleCancelledDataOld.build());
        vehicleCancelledDataOld.build().getTestTypes().get(0).setAdditionalNotesRecorded(null);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld();
    }


    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - additionalCommentsForAbandon")
    @Test
    public void testResultsRandomAdditionalCommentsForAbandon() {

        vehicleCancelledDataOld.setVin(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(16, vehicleCancelledDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledDataOld.build().getVrm())).build()
                .getTestTypes().get(0).setAdditionalCommentsForAbandon(RandomStringUtils.randomAlphanumeric(1, 499));

        testResultsSteps.postTestResults(vehicleCancelledDataOld.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld();

    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3504 - TCD - API Consumer creates a new test result for submitted/canceled that allows null values - additionalCommentsForAbandon")
    @Test
    public void testResultsNullAdditionalCommentsForAbandon() {

        vehicleCancelledDataOld.setVin(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(16, vehicleCancelledDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledDataOld.build().getVrm())).build()
                .getTestTypes().get(0).setAdditionalCommentsForAbandon(null);

        testResultsSteps.postTestResults(vehicleCancelledDataOld.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld();
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3486 - API Consumer creates a new test results for submitted/canceled with no min restriction - additionalCommentsForAbandon")
    @Test
    public void testResultsEmptyAdditionalCommentsForAbandon() {

        vehicleCancelledDataOld.setVin(generateRandomExcludingValues(21, vehicleCancelledDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(16, vehicleCancelledDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledDataOld.build().getVrm())).build()
                .getTestTypes().get(0).setAdditionalCommentsForAbandon("");

        testResultsSteps.postTestResults(vehicleCancelledDataOld.build());
        vehicleCancelledDataOld.build().getTestTypes().get(0).setAdditionalCommentsForAbandon(null);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld();
    }

}
