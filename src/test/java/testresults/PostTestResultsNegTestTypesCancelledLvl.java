package testresults;

import clients.util.ToTypeConvertor;
import clients.util.testresult.TestResultsLevel;
import data.TestResultsData;
import model.testresults.TestResults;
import model.testresults.TestTypes;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import steps.TestResultsSteps;


@RunWith(SerenityRunner.class)
public class PostTestResultsNegTestTypesCancelledLvl {

    @Steps
    TestResultsSteps testResultsSteps;

    private TestResults.Builder vehicleCancelledData = TestResultsData.buildTestResultsCancelledData();
    private static final String VRM = "SL72XD";


    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3510 API Consumer tries to create a new test result for submitted/canceled with extra field defined as calculated - createdAt")
    @Test
    public void testResultsExtraFieldCreatedAt() {

        testResultsSteps.postTestResultsFieldChange(vehicleCancelledData.setVrm(VRM).build(), "createdAt", RandomStringUtils.randomAlphanumeric(10), ToTypeConvertor.STRING, TestResultsLevel.TEST_TYPES);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("createdAt", "is not allowed");
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3506 - API Consumer tries to create a new test result for submitted/canceled with null value for not nullable - createdAt")
    @Test
    public void testResultsNullCreatedAt() {

        testResultsSteps.postTestResultsFieldChange(vehicleCancelledData.setVrm(VRM).build(), "createdAt", ToTypeConvertor.NULL, TestResultsLevel.TEST_TYPES);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("createdAt", "is not allowed");
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3510 API Consumer tries to create a new test result for submitted/canceled with extra field defined as calculated - lastUpdatedAt")
    @Test
    public void testResultsExtraFieldLastUpdatedAt() {

        testResultsSteps.postTestResultsFieldChange(vehicleCancelledData.setVrm(VRM).build(), "lastUpdatedAt", RandomStringUtils.randomAlphanumeric(10), ToTypeConvertor.STRING, TestResultsLevel.TEST_TYPES);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("lastUpdatedAt", "is not allowed");
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3506 - API Consumer tries to create a new test result for submitted/canceled with null value for not nullable - lastUpdatedAt")
    @Test
    public void testResultsNullLastUpdatedAt() {

        testResultsSteps.postTestResultsFieldChange(vehicleCancelledData.setVrm(VRM).build(), "lastUpdatedAt", ToTypeConvertor.NULL, TestResultsLevel.TEST_TYPES);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("lastUpdatedAt", "is not allowed");
    }


    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3510 API Consumer tries to create a new test result for submitted/canceled with extra field defined as calculated - testCode")
    @Test
    public void testResultsExtraFieldTestCode() {

        testResultsSteps.postTestResultsFieldChange(vehicleCancelledData.setVrm(VRM).build(), "testCode", RandomStringUtils.randomAlphanumeric(10), ToTypeConvertor.STRING, TestResultsLevel.TEST_TYPES);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("testCode", "is not allowed");
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3506 - API Consumer tries to create a new test result for submitted/canceled with null value for not nullable - testCode")
    @Test
    public void testResultsNullTestCode() {

        testResultsSteps.postTestResultsFieldChange(vehicleCancelledData.setVrm(VRM).build(), "testCode", ToTypeConvertor.NULL, TestResultsLevel.TEST_TYPES);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("testCode", "is not allowed");
    }


    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3510 API Consumer tries to create a new test result for submitted/canceled with extra field defined as calculated - testNumber")
    @Test
    public void testResultsExtraFieldTestNumber() {

        testResultsSteps.postTestResultsFieldChange(vehicleCancelledData.setVrm(VRM).build(), "testNumber", RandomStringUtils.randomAlphanumeric(10), ToTypeConvertor.STRING, TestResultsLevel.TEST_TYPES);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("testNumber", "is not allowed");
    }


    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3510 API Consumer tries to create a new test result for submitted/canceled with extra field defined as calculated - certificateLink")
    @Test
    public void testResultsExtraFieldCertificateLink() {

        testResultsSteps.postTestResultsFieldChange(vehicleCancelledData.setVrm(VRM).build(), "certificateLink", RandomStringUtils.randomAlphanumeric(10), ToTypeConvertor.STRING, TestResultsLevel.TEST_TYPES);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("certificateLink", "is not allowed");
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3506 - API Consumer tries to create a new test result for submitted/canceled with null value for not nullable - certificateLink")
    @Test
    public void testResultsNullCertificateLink() {

        testResultsSteps.postTestResultsFieldChange(vehicleCancelledData.setVrm(VRM).build(), "certificateLink", ToTypeConvertor.NULL, TestResultsLevel.TEST_TYPES);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("certificateLink", "is not allowed");
    }


    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3510 API Consumer tries to create a new test result for submitted/canceled with extra field defined as calculated - testExpiryDate")
    @Test
    public void testResultsExtraFieldTestExpiryDate() {

        testResultsSteps.postTestResultsFieldChange(vehicleCancelledData.setVrm(VRM).build(), "testExpiryDate", RandomStringUtils.randomAlphanumeric(10), ToTypeConvertor.STRING, TestResultsLevel.TEST_TYPES);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("testExpiryDate", "is not allowed");
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3506 - API Consumer tries to create a new test result for submitted/canceled with null value for not nullable - testExpiryDate")
    @Test
    public void testResultsNullTestExpiryDate() {

        testResultsSteps.postTestResultsFieldChange(vehicleCancelledData.setVrm(VRM).build(), "testExpiryDate", ToTypeConvertor.NULL, TestResultsLevel.TEST_TYPES);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("testExpiryDate", "is not allowed");
    }


    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3510 API Consumer tries to create a new test result for submitted/canceled with extra field defined as calculated - testAnniversaryDate")
    @Test
    public void testResultsExtraFieldTestAnniversaryDate() {

        testResultsSteps.postTestResultsFieldChange(vehicleCancelledData.setVrm(VRM).build(), "testAnniversaryDate", RandomStringUtils.randomAlphanumeric(10), ToTypeConvertor.STRING, TestResultsLevel.TEST_TYPES);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("testAnniversaryDate", "is not allowed");
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3506 - API Consumer tries to create a new test result for submitted/canceled with null value for not nullable - testAnniversaryDate")
    @Test
    public void testResultsNullTestAnniversaryDate() {

        testResultsSteps.postTestResultsFieldChange(vehicleCancelledData.setVrm(VRM).build(), "testAnniversaryDate", ToTypeConvertor.NULL, TestResultsLevel.TEST_TYPES);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("testAnniversaryDate", "is not allowed");
    }


    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3505 - API Consumer tries to create a new test result for submitted/canceled with missing property - testTypeName")
    @Test
    public void testResultsMissingTestTypeName() {

        testResultsSteps.postTestResultsFieldChange(vehicleCancelledData.setVrm(VRM).build(), "testTypeName", ToTypeConvertor.MISSING, TestResultsLevel.TEST_TYPES);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("testTypeName", "is required");
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3506 - API Consumer tries to create a new test result for submitted/canceled with null value for not nullable - testTypeName")
    @Test
    public void testResultsNullTestTypeName() {

        testResultsSteps.postTestResultsFieldChange(vehicleCancelledData.setVrm(VRM).build(), "testTypeName", ToTypeConvertor.NULL, TestResultsLevel.TEST_TYPES);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("testTypeName", "must be a string");
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3508 API Consumer tries to create a new test result for submitted/canceled with different property type - testTypeName")
    @Test
    public void testResultsIntegerTestTypeName() {

        testResultsSteps.postTestResultsFieldChange(vehicleCancelledData.setVrm(VRM).build(), "testTypeName", RandomStringUtils.randomNumeric(9), ToTypeConvertor.INTEGER, TestResultsLevel.TEST_TYPES);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("testTypeName", "must be a string");
    }


    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3505 - API Consumer tries to create a new test result for submitted/canceled with missing property - name")
    @Test
    public void testResultsMissingName() {

        testResultsSteps.postTestResultsFieldChange(vehicleCancelledData.setVrm(VRM).build(), "name", ToTypeConvertor.MISSING, TestResultsLevel.TEST_TYPES);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("name", "is required");
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3506 - API Consumer tries to create a new test result for submitted/canceled with null value for not nullable - name")
    @Test
    public void testResultsNullName() {

        testResultsSteps.postTestResultsFieldChange(vehicleCancelledData.setVrm(VRM).build(), "name", ToTypeConvertor.NULL, TestResultsLevel.TEST_TYPES);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("name", "must be a string");
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3508 API Consumer tries to create a new test result for submitted/canceled with different property type - name")
    @Test
    public void testResultsIntegerName() {

        testResultsSteps.postTestResultsFieldChange(vehicleCancelledData.setVrm(VRM).build(), "name", RandomStringUtils.randomNumeric(9), ToTypeConvertor.INTEGER, TestResultsLevel.TEST_TYPES);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("name", "must be a string");
    }


    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3505 - API Consumer tries to create a new test result for submitted/canceled with missing property - testTypeId")
    @Test
    public void testResultsMissingTestTypeId() {

        testResultsSteps.postTestResultsFieldChange(vehicleCancelledData.setVrm(VRM).build(), "testTypeId", ToTypeConvertor.MISSING, TestResultsLevel.TEST_TYPES);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("testTypeId", "is required");
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3506 - API Consumer tries to create a new test result for submitted/canceled with null value for not nullable - testTypeId")
    @Test
    public void testResultsNullTestTypeId() {

        testResultsSteps.postTestResultsFieldChange(vehicleCancelledData.setVrm(VRM).build(), "testTypeId", ToTypeConvertor.NULL, TestResultsLevel.TEST_TYPES);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("testTypeId", "must be a string");
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3508 API Consumer tries to create a new test result for submitted/canceled with different property type - testTypeId")
    @Test
    public void testResultsIntegerTestTypeId() {

        testResultsSteps.postTestResultsFieldChange(vehicleCancelledData.setVrm(VRM).build(), "testTypeId", RandomStringUtils.randomNumeric(9), ToTypeConvertor.INTEGER, TestResultsLevel.TEST_TYPES);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("testTypeId", "must be a string");
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3505 - API Consumer tries to create a new test result for submitted/canceled with missing property - certificateNumber")
    @Test
    public void testResultsCertificateNumber() {

        testResultsSteps.postTestResultsFieldChange(vehicleCancelledData.setVrm(VRM).build(), "certificateNumber", ToTypeConvertor.MISSING, TestResultsLevel.TEST_TYPES);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validateErrorText("Certificate number not present on LEC test type");
    }


    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3508 API Consumer tries to create a new test result for submitted/canceled with different property type - certificateNumber")
    @Test
    public void testResultsIntegerCertificateNumber() {

        testResultsSteps.postTestResultsFieldChange(vehicleCancelledData.setVrm(VRM).build(), "certificateNumber", RandomStringUtils.randomNumeric(9), ToTypeConvertor.INTEGER, TestResultsLevel.TEST_TYPES);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("certificateNumber", "must be a string");
    }


    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3505 - API Consumer tries to create a new test result for submitted/canceled with missing property - testTypeStartTimestamp")
    @Test
    public void testResultsMissingTestTypeStartTimestamp() {

        testResultsSteps.postTestResultsFieldChange(vehicleCancelledData.setVrm(VRM).build(), "testTypeStartTimestamp", ToTypeConvertor.MISSING, TestResultsLevel.TEST_TYPES);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("testTypeStartTimestamp", "is required");
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3506 - API Consumer tries to create a new test result for submitted/canceled with null value for not nullable - testTypeStartTimestamp")
    @Test
    public void testResultsNullTestTypeStartTimestamp() {

        testResultsSteps.postTestResultsFieldChange(vehicleCancelledData.setVrm(VRM).build(), "testTypeStartTimestamp", ToTypeConvertor.NULL, TestResultsLevel.TEST_TYPES);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("testTypeStartTimestamp", "must be a valid ISO 8601 date");
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3508 API Consumer tries to create a new test result for submitted/canceled with different property type - testTypeStartTimestamp")
    @Test
    public void testResultsIntegerTestTypeStartTimestamp() {

        testResultsSteps.postTestResultsFieldChange(vehicleCancelledData.setVrm(VRM).build(), "testTypeStartTimestamp", RandomStringUtils.randomNumeric(9), ToTypeConvertor.INTEGER, TestResultsLevel.TEST_TYPES);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("testTypeStartTimestamp", "must be a valid ISO 8601 date");
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3509 - API Consumer tries to create a new test result for submitted/canceled with different format or allowed values - testTypeStartTimestamp random")
    @Test
    public void testResultsRandomStringTestTypeStartTimestamp() {

        testResultsSteps.postTestResultsFieldChange(vehicleCancelledData.setVrm(VRM).build(), "testTypeStartTimestamp", RandomStringUtils.randomAlphanumeric(9), ToTypeConvertor.STRING, TestResultsLevel.TEST_TYPES);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("testTypeStartTimestamp", "must be a valid ISO 8601 date");
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3509 - API Consumer tries to create a new test result for submitted/canceled with different format or allowed values - testTypeStartTimestamp empty")
    @Test
    public void testResultsEmptyTestTypeStartTimestamp() {

        testResultsSteps.postTestResultsFieldChange(vehicleCancelledData.setVrm(VRM).build(), "testTypeStartTimestamp", "", ToTypeConvertor.STRING, TestResultsLevel.TEST_TYPES);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("testTypeStartTimestamp", "must be a valid ISO 8601 date");
    }


    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3505 - API Consumer tries to create a new test result for submitted/canceled with missing property - testTypeEndTimestamp")
    @Test
    public void testResultsMissingTestTypeEndTimestamp() {

        testResultsSteps.postTestResultsFieldChange(vehicleCancelledData.setVrm(VRM).build(), "testTypeEndTimestamp", ToTypeConvertor.MISSING, TestResultsLevel.TEST_TYPES);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("testTypeEndTimestamp", "is required");
    }


    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3508 API Consumer tries to create a new test result for submitted/canceled with different property type - testTypeEndTimestamp")
    @Test
    public void testResultsIntegerTestTypeEndTimestamp() {

        testResultsSteps.postTestResultsFieldChange(vehicleCancelledData.setVrm(VRM).build(), "testTypeEndTimestamp", RandomStringUtils.randomNumeric(9), ToTypeConvertor.INTEGER, TestResultsLevel.TEST_TYPES);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("testTypeEndTimestamp", "must be a valid ISO 8601 date");
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3509 - API Consumer tries to create a new test result for submitted/canceled with different format or allowed values - testTypeEndTimestamp random")
    @Test
    public void testResultsRandomStringTestTypeEndTimestamp() {

        testResultsSteps.postTestResultsFieldChange(vehicleCancelledData.setVrm(VRM).build(), "testTypeEndTimestamp", RandomStringUtils.randomAlphanumeric(9), ToTypeConvertor.STRING, TestResultsLevel.TEST_TYPES);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("testTypeEndTimestamp", "must be a valid ISO 8601 date");
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3509 - API Consumer tries to create a new test result for submitted/canceled with different format or allowed values - testTypeEndTimestamp empty")
    @Test
    public void testResultsEmptyTestTypeEndTimestamp() {

        testResultsSteps.postTestResultsFieldChange(vehicleCancelledData.setVrm(VRM).build(), "testTypeEndTimestamp", "", ToTypeConvertor.STRING, TestResultsLevel.TEST_TYPES);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("testTypeEndTimestamp", "must be a valid ISO 8601 date");
    }


    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3505 - API Consumer tries to create a new test result for submitted/canceled with missing property - numberOfSeatbeltsFitted")
    @Test
    public void testResultsMissingNumberOfSeatBeltsFitted() {

        testResultsSteps.postTestResultsFieldChange(vehicleCancelledData.setVrm(VRM).build(), "numberOfSeatbeltsFitted", ToTypeConvertor.MISSING, TestResultsLevel.TEST_TYPES);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("numberOfSeatbeltsFitted", "is required");
    }


    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3508 API Consumer tries to create a new test result for submitted/canceled with different property type - numberOfSeatbeltsFitted")
    @Test
    public void testResultsRandomStringNumberOfSeatBeltsFitted() {

        testResultsSteps.postTestResultsFieldChange(vehicleCancelledData.setVrm(VRM).build(), "numberOfSeatbeltsFitted", RandomStringUtils.randomAlphanumeric(9), ToTypeConvertor.STRING, TestResultsLevel.TEST_TYPES);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("numberOfSeatbeltsFitted", "must be a number");
    }


    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3505 - API Consumer tries to create a new test result for submitted/canceled with missing property - lastSeatbeltInstallationCheckDate")
    @Test
    public void testResultsMissingLastSeatbeltInstallationCheckDate() {

        testResultsSteps.postTestResultsFieldChange(vehicleCancelledData.setVrm(VRM).build(), "lastSeatbeltInstallationCheckDate", ToTypeConvertor.MISSING, TestResultsLevel.TEST_TYPES);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("lastSeatbeltInstallationCheckDate", "is required");
    }


    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3509 - API Consumer tries to create a new test result for submitted/canceled with different format or allowed values - lastSeatbeltInstallationCheckDate random")
    @Test
    public void testResultsRandomStringLastSeatbeltInstallationCheckDate() {

        testResultsSteps.postTestResultsFieldChange(vehicleCancelledData.setVrm(VRM).build(), "lastSeatbeltInstallationCheckDate", RandomStringUtils.randomAlphanumeric(9), ToTypeConvertor.STRING, TestResultsLevel.TEST_TYPES);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("lastSeatbeltInstallationCheckDate", "must be a number of milliseconds or valid date string");
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3509 - API Consumer tries to create a new test result for submitted/canceled with different format or allowed values - lastSeatbeltInstallationCheckDate empty")
    @Test
    public void testResultsEmptyLastSeatbeltInstallationCheckDate() {

        testResultsSteps.postTestResultsFieldChange(vehicleCancelledData.setVrm(VRM).build(), "lastSeatbeltInstallationCheckDate", "", ToTypeConvertor.STRING, TestResultsLevel.TEST_TYPES);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("lastSeatbeltInstallationCheckDate", "must be a number of milliseconds or valid date string");
    }


    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3505 - API Consumer tries to create a new test result for submitted/canceled with missing property - seatbeltInstallationCheckDate")
    @Test
    public void testResultsMissingSeatbeltInstallationCheckDate() {

        testResultsSteps.postTestResultsFieldChange(vehicleCancelledData.setVrm(VRM).build(), "seatbeltInstallationCheckDate", ToTypeConvertor.MISSING, TestResultsLevel.TEST_TYPES);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("seatbeltInstallationCheckDate", "is required");
    }


    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3508 API Consumer tries to create a new test result for submitted/canceled with different property type - seatbeltInstallationCheckDate random string")
    @Test
    public void testResultsRandomStringSeatbeltInstallationCheckDate() {

        testResultsSteps.postTestResultsFieldChange(vehicleCancelledData.setVrm(VRM).build(), "seatbeltInstallationCheckDate", RandomStringUtils.randomAlphanumeric(9), ToTypeConvertor.STRING, TestResultsLevel.TEST_TYPES);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("seatbeltInstallationCheckDate", "must be a boolean");
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3508 API Consumer tries to create a new test result for submitted/canceled with different property type - seatbeltInstallationCheckDate empty string")
    @Test
    public void testResultsEmptySeatbeltInstallationCheckDate() {

        testResultsSteps.postTestResultsFieldChange(vehicleCancelledData.setVrm(VRM).build(), "seatbeltInstallationCheckDate", "", ToTypeConvertor.STRING, TestResultsLevel.TEST_TYPES);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("seatbeltInstallationCheckDate", "must be a boolean");
    }


    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3505 - API Consumer tries to create a new test result for submitted/canceled with missing property - testResult")
    @Test
    public void testResultsMissingTestResult() {

        testResultsSteps.postTestResultsFieldChange(vehicleCancelledData.setVrm(VRM).build(), "testResult", ToTypeConvertor.MISSING, TestResultsLevel.TEST_TYPES);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("testResult", "is required");
    }


    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3508 API Consumer tries to create a new test result for submitted/canceled with different property type - testResult")
    @Test
    public void testResultsIntegerTestResult() {

        testResultsSteps.postTestResultsFieldChange(vehicleCancelledData.setVrm(VRM).build(), "testResult", RandomStringUtils.randomNumeric(9), ToTypeConvertor.INTEGER, TestResultsLevel.TEST_TYPES);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("testResult", "must be one of [fail, pass, prs, abandoned, null]");
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3509 - API Consumer tries to create a new test result for submitted/canceled with different format or allowed values - testResult random")
    @Test
    public void testResultsRandomStringTestResult() {

        testResultsSteps.postTestResultsFieldChange(vehicleCancelledData.setVrm(VRM).build(), "testResult", RandomStringUtils.randomAlphanumeric(9), ToTypeConvertor.STRING, TestResultsLevel.TEST_TYPES);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("testResult", "must be one of [fail, pass, prs, abandoned, null]");
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3509 - API Consumer tries to create a new test result for submitted/canceled with different format or allowed values - testResult empty")
    @Test
    public void testResultsEmptyTestResult() {

        testResultsSteps.postTestResultsFieldChange(vehicleCancelledData.setVrm(VRM).build(), "testResult", "", ToTypeConvertor.STRING, TestResultsLevel.TEST_TYPES);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("testResult", "must be one of [fail, pass, prs, abandoned, null]");
    }


    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3505 - API Consumer tries to create a new test result for submitted/canceled with missing property - prohibitionIssued")
    @Test
    public void testResultsMissingProhibitionIssued() {

        testResultsSteps.postTestResultsFieldChange(vehicleCancelledData.setVrm(VRM).build(), "prohibitionIssued", ToTypeConvertor.MISSING, TestResultsLevel.TEST_TYPES);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("prohibitionIssued", "is required");
    }


    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3508 API Consumer tries to create a new test result for submitted/canceled with different property type - prohibitionIssued random string")
    @Test
    public void testResultsRandomStringProhibitionIssued() {

        testResultsSteps.postTestResultsFieldChange(vehicleCancelledData.setVrm(VRM).build(), "prohibitionIssued", RandomStringUtils.randomAlphanumeric(9), ToTypeConvertor.STRING, TestResultsLevel.TEST_TYPES);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("prohibitionIssued", "must be a boolean");
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3508 API Consumer tries to create a new test result for submitted/canceled with different property type - prohibitionIssued empty string")
    @Test
    public void testResultsEmptyProhibitionIssued() {

        testResultsSteps.postTestResultsFieldChange(vehicleCancelledData.setVrm(VRM).build(), "prohibitionIssued", "", ToTypeConvertor.STRING, TestResultsLevel.TEST_TYPES);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("prohibitionIssued", "must be a boolean");
    }


    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3505 - API Consumer tries to create a new test result for submitted/canceled with missing property - reasonForAbandoning")
    @Test
    public void testResultsMissingReasonForAbandoning() {

        testResultsSteps.postTestResultsFieldChange(vehicleCancelledData.setVrm(VRM).build(), "reasonForAbandoning", ToTypeConvertor.MISSING, TestResultsLevel.TEST_TYPES);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("reasonForAbandoning", "is required");
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3506 - API Consumer tries to create a new test result for submitted/canceled with null value for not nullable - reasonForAbandoning with testResult abandoned")
    @Test
    public void testResultsNullReasonForAbandoningAbandoned() {

        ((TestTypes) vehicleCancelledData.getTestTypes().get(0)).setTestResult("abandoned");

        testResultsSteps.postTestResultsFieldChange(vehicleCancelledData.setVrm(VRM).build(), "reasonForAbandoning",  ToTypeConvertor.NULL, TestResultsLevel.TEST_TYPES);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validateData("Reason for Abandoning not present on all abandoned tests");
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3508 API Consumer tries to create a new test result for submitted/canceled with different property type - reasonForAbandoning")
    @Test
    public void testResultsIntegerReasonForAbandoning() {

        testResultsSteps.postTestResultsFieldChange(vehicleCancelledData.setVrm(VRM).build(), "reasonForAbandoning", RandomStringUtils.randomNumeric(9), ToTypeConvertor.INTEGER, TestResultsLevel.TEST_TYPES);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("reasonForAbandoning", "must be a string");
    }


    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3507 - API Consumer tries to create a new test result for submitted/canceled with value/characters exceeding max length - reasonForAbandoning")
    @Test
    public void testResultsLengthMaxReasonForAbandoning() {

        testResultsSteps.postTestResultsFieldChange(vehicleCancelledData.setVrm(VRM).build(), "reasonForAbandoning", RandomStringUtils.randomAlphanumeric(501), ToTypeConvertor.STRING, TestResultsLevel.TEST_TYPES);
        testResultsSteps.statusCodeShouldBe(201);
    }


    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3505 - API Consumer tries to create a new test result for submitted/canceled with missing property - additionalNotesRecorded")
    @Test
    public void testResultsMissingAdditionalNotesRecorded() {

        testResultsSteps.postTestResultsFieldChange(vehicleCancelledData.setVrm(VRM).build(), "additionalNotesRecorded", ToTypeConvertor.MISSING, TestResultsLevel.TEST_TYPES);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("additionalNotesRecorded", "is required");
    }


    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3508 API Consumer tries to create a new test result for submitted/canceled with different property type - additionalNotesRecorded")
    @Test
    public void testResultsIntegerAdditionalNotesRecorded() {

        testResultsSteps.postTestResultsFieldChange(vehicleCancelledData.setVrm(VRM).build(), "additionalNotesRecorded", RandomStringUtils.randomNumeric(9), ToTypeConvertor.INTEGER, TestResultsLevel.TEST_TYPES);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("additionalNotesRecorded", "must be a string");
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3507 - API Consumer tries to create a new test result for submitted/canceled with value/characters exceeding max length - additionalNotesRecorded")
    @Test
    public void testResultsLengthMaxAdditionalNotesRecorded() {

        testResultsSteps.postTestResultsFieldChange(vehicleCancelledData.setVrm(VRM).build(), "additionalNotesRecorded", RandomStringUtils.randomNumeric(501), ToTypeConvertor.STRING, TestResultsLevel.TEST_TYPES);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("additionalNotesRecorded", "length must be less than or equal to 500 characters long");
    }


    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3505 - API Consumer tries to create a new test result for submitted/canceled with missing property - additionalCommentsForAbandon")
    @Test
    public void testResultsMissingAdditionalCommentsForAbandon() {

        testResultsSteps.postTestResultsFieldChange(vehicleCancelledData.setVrm(VRM).build(), "additionalCommentsForAbandon", ToTypeConvertor.MISSING, TestResultsLevel.TEST_TYPES);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("additionalCommentsForAbandon", "is required");
    }


    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3508 API Consumer tries to create a new test result for submitted/canceled with different property type - additionalCommentsForAbandon")
    @Test
    public void testResultsIntegerAdditionalCommentsForAbandon() {

        testResultsSteps.postTestResultsFieldChange(vehicleCancelledData.setVrm(VRM).build(), "additionalCommentsForAbandon", RandomStringUtils.randomNumeric(9), ToTypeConvertor.INTEGER, TestResultsLevel.TEST_TYPES);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("additionalCommentsForAbandon", "must be a string");
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3507 - API Consumer tries to create a new test result for submitted/canceled with value/characters exceeding max length - additionalCommentsForAbandon")
    @Test
    public void testResultsLengthMaxAdditionalCommentsForAbandon() {

        testResultsSteps.postTestResultsFieldChange(vehicleCancelledData.setVrm(VRM).build(), "additionalCommentsForAbandon", RandomStringUtils.randomNumeric(501), ToTypeConvertor.STRING, TestResultsLevel.TEST_TYPES);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("additionalCommentsForAbandon", "length must be less than or equal to 500 characters long");
    }


    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3506 - API Consumer tries to create a new test result for submitted/canceled with null value for not nullable - defects")
    @Test
    public void testResultsDefectsAsNull() {

        testResultsSteps.postTestResultsFieldChange(vehicleCancelledData.setVrm(VRM).build(), "defects", ToTypeConvertor.NULL, TestResultsLevel.TEST_TYPES);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("defects", "must be an array");

    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3505 - API Consumer tries to create a new test result for submitted/canceled with missing property - defects")
    @Test
    public void testResultsTestTypesMissing() {

        testResultsSteps.postTestResultsFieldChange(vehicleCancelledData.setVrm(VRM).build(), "defects", ToTypeConvertor.MISSING, TestResultsLevel.TEST_TYPES);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("defects", "is required");
    }

}
