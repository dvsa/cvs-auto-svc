package testresults;

import clients.util.ToTypeConvertor;
import clients.util.testresult.TestResultsLevel;
import data.TestResultsData;
import model.testresults.TestResults;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.core.annotations.WithTags;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import steps.TestResultsSteps;

@WithTags(
        {
                @WithTag(type = "TestResults", name = "All"),
                @WithTag(type = "TestResults", name = "Negative"),
                @WithTag(type = "Service", name = "One"),

        }
)

@Ignore("Work in progress - new development changes require big refactoring")
@RunWith(SerenityRunner.class)
public class PostTestResultsNegTestTypesLevel {

    @Steps
    TestResultsSteps testResultsSteps;

    private TestResults vehicleData = TestResultsData.buildTestResultsSubmittedData();
    private static final String VRM = "SL72XD";



    //createdAt

    @Title("")
    @Test
    public void testResultsMissingCreatedAt() {

        testResultsSteps.postTestResultsFieldChange(vehicleData.setVrm(VRM), "createdAt", ToTypeConvertor.MISSING, TestResultsLevel.TEST_TYPES);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("createdAt", "is required");
    }

    @Title("")
    @Test
    public void testResultsNullCreatedAt() {

        testResultsSteps.postTestResultsFieldChange(vehicleData.setVrm(VRM), "createdAt", ToTypeConvertor.NULL, TestResultsLevel.TEST_TYPES);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("createdAt", "must be a valid ISO 8601 date");
    }



    @Title("")
    @Test
    public void testResultsIntegerCreatedAt() {

        testResultsSteps.postTestResultsFieldChange(vehicleData.setVrm(VRM), "createdAt", RandomStringUtils.randomNumeric(9), ToTypeConvertor.INTEGER, TestResultsLevel.TEST_TYPES);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("createdAt", "must be a valid ISO 8601 date");
    }

    @Title("")
    @Test
    public void testResultsRandomStringCreatedAt() {

        vehicleData.setVrm(VRM).getTestTypes().get(0).setCreatedAt(RandomStringUtils.randomAlphanumeric(10));

        testResultsSteps.postTestResults(vehicleData);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("createdAt", "must be a valid ISO 8601 date");
    }


    @Title("")
    @Test
    public void testResultsEmptyCreatedAt() {

        vehicleData.setVrm(VRM).getTestTypes().get(0).setCreatedAt("");

        testResultsSteps.postTestResults(vehicleData);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("createdAt", "must be a valid ISO 8601 date");
    }

    //lastUpdatedAt

    @Title("")
    @Test
    public void testResultsMissingLastUpdatedAt() {

        testResultsSteps.postTestResultsFieldChange(vehicleData.setVrm(VRM), "lastUpdatedAt", ToTypeConvertor.MISSING, TestResultsLevel.TEST_TYPES);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("lastUpdatedAt", "is required");
    }

    @Title("")
    @Test
    public void testResultsNullLastUpdatedAt() {

        testResultsSteps.postTestResultsFieldChange(vehicleData.setVrm(VRM), "lastUpdatedAt", ToTypeConvertor.NULL, TestResultsLevel.TEST_TYPES);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("lastUpdatedAt", "must be a valid ISO 8601 date");
    }



    @Title("")
    @Test
    public void testResultsIntegerLastUpdatedAt() {

        testResultsSteps.postTestResultsFieldChange(vehicleData.setVrm(VRM), "lastUpdatedAt", RandomStringUtils.randomNumeric(9), ToTypeConvertor.INTEGER, TestResultsLevel.TEST_TYPES);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("lastUpdatedAt", "must be a valid ISO 8601 date");
    }

    @Title("")
    @Test
    public void testResultsRandomStringLastUpdatedAt() {

        vehicleData.setVrm(VRM).getTestTypes().get(0).setLastUpdatedAt(RandomStringUtils.randomAlphanumeric(10));

        testResultsSteps.postTestResults(vehicleData);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("lastUpdatedAt", "must be a valid ISO 8601 date");
    }


    @Title("")
    @Test
    public void testResultsEmptyLastUpdatedAt() {

        vehicleData.setVrm(VRM).getTestTypes().get(0).setLastUpdatedAt("");

        testResultsSteps.postTestResults(vehicleData);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("lastUpdatedAt", "must be a valid ISO 8601 date");
    }

    //testCode

    @Title("")
    @Test
    public void testResultsMissingTesterName() {

        testResultsSteps.postTestResultsFieldChange(vehicleData.setVrm(VRM), "testCode", ToTypeConvertor.MISSING, TestResultsLevel.TEST_TYPES);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("testCode", "is required");
    }

    @Title("")
    @Test
    public void testResultsNullTesterName() {

        testResultsSteps.postTestResultsFieldChange(vehicleData.setVrm(VRM), "testCode", ToTypeConvertor.NULL, TestResultsLevel.TEST_TYPES);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("testCode", "must be a string");
    }



    @Title("")
    @Test
    public void testResultsIntegerTesterName() {

        testResultsSteps.postTestResultsFieldChange(vehicleData.setVrm(VRM), "testCode", RandomStringUtils.randomNumeric(9), ToTypeConvertor.INTEGER, TestResultsLevel.TEST_TYPES);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("testCode", "must be a string");
    }

    @Title("")
    @Test
    public void testResultsLengthMaxTesterName() {

        vehicleData.setVrm(VRM).getTestTypes().get(0).setTestCode(RandomStringUtils.randomAlphanumeric(4));

        testResultsSteps.postTestResults(vehicleData);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("testCode", "length must be less than or equal to 3 characters long");
    }

    @Title("")
    @Test
    public void testResultsLengthMinTesterName() {

        vehicleData.setVrm(VRM).getTestTypes().get(0).setTestCode(RandomStringUtils.randomAlphanumeric(2));

        testResultsSteps.postTestResults(vehicleData);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("testCode", "length must be at least 3 characters long");
    }

    @Title("")
    @Test
    public void testResultsTesterNameEmpty() {

        vehicleData.setVrm(VRM).getTestTypes().get(0).setTestCode("");

        testResultsSteps.postTestResults(vehicleData);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("testCode", "is not allowed to be empty");
    }

    //testTypeName


    @Title("")
    @Test
    public void testResultsMissingTestTypeName() {

        testResultsSteps.postTestResultsFieldChange(vehicleData.setVrm(VRM), "testTypeName", ToTypeConvertor.MISSING, TestResultsLevel.TEST_TYPES);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("testTypeName", "is required");
    }

    @Title("")
    @Test
    public void testResultsNullTestTypeName() {

        testResultsSteps.postTestResultsFieldChange(vehicleData.setVrm(VRM), "testTypeName", ToTypeConvertor.NULL, TestResultsLevel.TEST_TYPES);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("testTypeName", "must be a string");
    }



    @Title("")
    @Test
    public void testResultsIntegerTestTypeName() {

        testResultsSteps.postTestResultsFieldChange(vehicleData.setVrm(VRM), "testTypeName", RandomStringUtils.randomNumeric(1,9), ToTypeConvertor.INTEGER, TestResultsLevel.TEST_TYPES);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("testTypeName", "must be a string");
    }


    @Title("")
    @Test
    public void testResultsTestTypeNameEmpty() {

        vehicleData.setVrm(VRM).getTestTypes().get(0).setTestTypeName("");

        testResultsSteps.postTestResults(vehicleData);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("testTypeName", "is not allowed to be empty");
    }

    //testId

    @Title("")
    @Test
    public void testResultsMissingTestId() {

        testResultsSteps.postTestResultsFieldChange(vehicleData.setVrm(VRM), "testId", ToTypeConvertor.MISSING, TestResultsLevel.TEST_TYPES);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("testId", "is required");
    }

    @Title("")
    @Test
    public void testResultsNullTestId() {

        testResultsSteps.postTestResultsFieldChange(vehicleData.setVrm(VRM), "testId", ToTypeConvertor.NULL, TestResultsLevel.TEST_TYPES);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("testId", "must be a string");
    }



    @Title("")
    @Test
    public void testResultsIntegerTestId() {

        testResultsSteps.postTestResultsFieldChange(vehicleData.setVrm(VRM), "testId", RandomStringUtils.randomNumeric(1,9), ToTypeConvertor.INTEGER, TestResultsLevel.TEST_TYPES);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("testId", "must be a string");
    }


    @Title("")
    @Test
    public void testResultsTestIdEmpty() {

        vehicleData.setVrm(VRM).getTestTypes().get(0).setTestId("");

        testResultsSteps.postTestResults(vehicleData);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("testId", "is not allowed to be empty");
    }

    //certificateNumber

    @Title("")
    @Test
    public void testResultsMissingCertificateNumber() {

        testResultsSteps.postTestResultsFieldChange(vehicleData.setVrm(VRM), "certificateNumber", ToTypeConvertor.MISSING, TestResultsLevel.TEST_TYPES);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("certificateNumber", "is required");
    }

    @Title("")
    @Test
    public void testResultsNullCertificateNumber() {

        testResultsSteps.postTestResultsFieldChange(vehicleData.setVrm(VRM), "certificateNumber", ToTypeConvertor.NULL, TestResultsLevel.TEST_TYPES);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("certificateNumber", "must be a string");
    }



    @Title("")
    @Test
    public void testResultsIntegerCertificateNumber() {

        testResultsSteps.postTestResultsFieldChange(vehicleData.setVrm(VRM), "certificateNumber", RandomStringUtils.randomNumeric(1,9), ToTypeConvertor.INTEGER, TestResultsLevel.TEST_TYPES);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("certificateNumber", "must be a string");
    }


    @Title("")
    @Test
    public void testResultsCertificateNumberEmpty() {

        vehicleData.setVrm(VRM).getTestTypes().get(0).setCertificateNumber("");

        testResultsSteps.postTestResults(vehicleData);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("certificateNumber", "is not allowed to be empty");
    }

    //testExpiryDate

    @Title("")
    @Test
    public void testResultsMissingTestExpiryDate() {

        testResultsSteps.postTestResultsFieldChange(vehicleData.setVrm(VRM), "testExpiryDate", ToTypeConvertor.MISSING, TestResultsLevel.TEST_TYPES);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("testExpiryDate", "is required");
    }

    @Title("")
    @Test
    public void testResultsNullTestExpiryDate() {

        testResultsSteps.postTestResultsFieldChange(vehicleData.setVrm(VRM), "testExpiryDate", ToTypeConvertor.NULL, TestResultsLevel.TEST_TYPES);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("testExpiryDate", "must be a number of milliseconds or valid date string");
    }


    @Ignore("don't know if bug, figure out number of milliseconds what means")
    @Title("")
    @Test
    public void testResultsIntegerTestExpiryDate() {

        testResultsSteps.postTestResultsFieldChange(vehicleData.setVrm(VRM), "testExpiryDate", RandomStringUtils.randomNumeric(1), ToTypeConvertor.INTEGER, TestResultsLevel.TEST_TYPES);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("testExpiryDate", "must be a number of milliseconds or valid date string");
    }

    @Title("")
    @Test
    public void testResultsRandomStringTestExpiryDate() {

        vehicleData.setVrm(VRM).getTestTypes().get(0).setTestExpiryDate(RandomStringUtils.randomAlphanumeric(10));

        testResultsSteps.postTestResults(vehicleData);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("testExpiryDate", "must be a number of milliseconds or valid date string");
    }


    @Title("")
    @Test
    public void testResultsEmptyTestExpiryDate() {

        vehicleData.setVrm(VRM).getTestTypes().get(0).setTestExpiryDate("");

        testResultsSteps.postTestResults(vehicleData);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("testExpiryDate", "must be a number of milliseconds or valid date string");
    }


    //testTypeStartTimestamp

    @Title("")
    @Test
    public void testResultsMissingTestTypeStartTimestamp() {

        testResultsSteps.postTestResultsFieldChange(vehicleData.setVrm(VRM), "testTypeStartTimestamp", ToTypeConvertor.MISSING, TestResultsLevel.TEST_TYPES);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("testTypeStartTimestamp", "is required");
    }

    @Title("")
    @Test
    public void testResultsNullTestTypeStartTimestamp() {

        testResultsSteps.postTestResultsFieldChange(vehicleData.setVrm(VRM), "testTypeStartTimestamp", ToTypeConvertor.NULL, TestResultsLevel.TEST_TYPES);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("testTypeStartTimestamp", "must be a valid ISO 8601 date");
    }



    @Title("")
    @Test
    public void testResultsIntegerTestTypeStartTimestamp() {

        testResultsSteps.postTestResultsFieldChange(vehicleData.setVrm(VRM), "testTypeStartTimestamp", RandomStringUtils.randomNumeric(9), ToTypeConvertor.INTEGER, TestResultsLevel.TEST_TYPES);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("testTypeStartTimestamp", "must be a valid ISO 8601 date");
    }

    @Title("")
    @Test
    public void testResultsRandomStringTestTypeStartTimestamp() {

        vehicleData.setVrm(VRM).getTestTypes().get(0).setTestTypeStartTimestamp(RandomStringUtils.randomAlphanumeric(10));

        testResultsSteps.postTestResults(vehicleData);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("testTypeStartTimestamp", "must be a valid ISO 8601 date");
    }


    @Title("")
    @Test
    public void testResultsEmptyTestTypeStartTimestamp() {

        vehicleData.setVrm(VRM).getTestTypes().get(0).setTestTypeStartTimestamp("");

        testResultsSteps.postTestResults(vehicleData);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("testTypeStartTimestamp", "must be a valid ISO 8601 date");
    }

    //testTypeEndTimestamp

    @Title("")
    @Test
    public void testResultsMissingTestTypeEndTimestamp() {

        testResultsSteps.postTestResultsFieldChange(vehicleData.setVrm(VRM), "testTypeEndTimestamp", ToTypeConvertor.MISSING, TestResultsLevel.TEST_TYPES);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("testTypeEndTimestamp", "is required");
    }

    @Title("")
    @Test
    public void testResultsNullTestTypeEndTimestamp() {

        testResultsSteps.postTestResultsFieldChange(vehicleData.setVrm(VRM), "testTypeEndTimestamp", ToTypeConvertor.NULL, TestResultsLevel.TEST_TYPES);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("testTypeEndTimestamp", "must be a valid ISO 8601 date");
    }



    @Title("")
    @Test
    public void testResultsIntegerTestTypeEndTimestamp() {

        testResultsSteps.postTestResultsFieldChange(vehicleData.setVrm(VRM), "testTypeEndTimestamp", RandomStringUtils.randomNumeric(9), ToTypeConvertor.INTEGER, TestResultsLevel.TEST_TYPES);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("testTypeEndTimestamp", "must be a valid ISO 8601 date");
    }

    @Title("")
    @Test
    public void testResultsRandomStringTestTypeEndTimestamp() {

        vehicleData.setVrm(VRM).getTestTypes().get(0).setTestTypeEndTimestamp(RandomStringUtils.randomAlphanumeric(10));

        testResultsSteps.postTestResults(vehicleData);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("testTypeEndTimestamp", "must be a valid ISO 8601 date");
    }


    @Title("")
    @Test
    public void testResultsEmptyTestTypeEndTimestamp() {

        vehicleData.setVrm(VRM).getTestTypes().get(0).setTestTypeEndTimestamp("");

        testResultsSteps.postTestResults(vehicleData);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("testTypeEndTimestamp", "must be a valid ISO 8601 date");
    }

    //numberOfSeatbeltsFitted

    @Title("")
    @Test
    public void testResultsMissingNumberOfSeatbeltsFitted() {

        testResultsSteps.postTestResultsFieldChange(vehicleData.setVrm(VRM), "numberOfSeatbeltsFitted", ToTypeConvertor.MISSING, TestResultsLevel.TEST_TYPES);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("numberOfSeatbeltsFitted", "is required");
    }

    @Title("")
    @Test
    public void testResultsNullNumberOfSeatbeltsFitted() {

        testResultsSteps.postTestResultsFieldChange(vehicleData.setVrm(VRM), "numberOfSeatbeltsFitted", ToTypeConvertor.NULL, TestResultsLevel.TEST_TYPES);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("numberOfSeatbeltsFitted", "must be a number");
    }



    @Title("")
    @Test
    public void testResultsStringNumberOfSeatbeltsFitted() {

        testResultsSteps.postTestResultsFieldChange(vehicleData.setVrm(VRM), "numberOfSeatbeltsFitted", RandomStringUtils.randomAlphanumeric(4), ToTypeConvertor.STRING, TestResultsLevel.TEST_TYPES);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("numberOfSeatbeltsFitted", "must be a number");
    }

    @Title("")
    @Test
    public void testResultsLengthMaxNumberOfSeatbeltsFitted() {

        vehicleData.setVrm(VRM).getTestTypes().get(0).setNumberOfSeatbeltsFitted(5);

        testResultsSteps.postTestResults(vehicleData);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("numberOfSeatbeltsFitted", "must be less than or equal to 4");
    }


    //lastSeatbeltInstallationCheckDate

    @Title("")
    @Test
    public void testResultsMissingLastSeatbeltInstallationCheckDate() {

        testResultsSteps.postTestResultsFieldChange(vehicleData.setVrm(VRM), "lastSeatbeltInstallationCheckDate", ToTypeConvertor.MISSING, TestResultsLevel.TEST_TYPES);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("lastSeatbeltInstallationCheckDate", "is required");
    }

    @Title("")
    @Test
    public void testResultsNullLastSeatbeltInstallationCheckDate() {

        testResultsSteps.postTestResultsFieldChange(vehicleData.setVrm(VRM), "lastSeatbeltInstallationCheckDate", ToTypeConvertor.NULL, TestResultsLevel.TEST_TYPES);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("lastSeatbeltInstallationCheckDate", "must be a number of milliseconds or valid date string");
    }


    @Ignore("don't know if bug, figure out number of milliseconds what means")
    @Title("")
    @Test
    public void testResultsIntegerLastSeatbeltInstallationCheckDate() {

        testResultsSteps.postTestResultsFieldChange(vehicleData.setVrm(VRM), "lastSeatbeltInstallationCheckDate", RandomStringUtils.randomNumeric(1), ToTypeConvertor.INTEGER, TestResultsLevel.TEST_TYPES);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("lastSeatbeltInstallationCheckDate", "must be a number of milliseconds or valid date string");
    }

    @Title("")
    @Test
    public void testResultsRandomStringLastSeatbeltInstallationCheckDate() {

        vehicleData.setVrm(VRM).getTestTypes().get(0).setLastSeatbeltInstallationCheckDate(RandomStringUtils.randomAlphanumeric(10));

        testResultsSteps.postTestResults(vehicleData);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("lastSeatbeltInstallationCheckDate", "must be a number of milliseconds or valid date string");
    }


    @Title("")
    @Test
    public void testResultsEmptyLastSeatbeltInstallationCheckDate() {

        vehicleData.setVrm(VRM).getTestTypes().get(0).setLastSeatbeltInstallationCheckDate("");

        testResultsSteps.postTestResults(vehicleData);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("lastSeatbeltInstallationCheckDate", "must be a number of milliseconds or valid date string");
    }

    //seatbeltInstallationCheckDate

    @Title("")
    @Test
    public void testResultsMissingseatbeltInstallationCheckDate() {

        testResultsSteps.postTestResultsFieldChange(vehicleData.setVrm(VRM), "seatbeltInstallationCheckDate", ToTypeConvertor.MISSING, TestResultsLevel.TEST_TYPES);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("seatbeltInstallationCheckDate", "is required");
    }

    @Title("")
    @Test
    public void testResultsNullSeatBeltInstallationCheckDate() {

        testResultsSteps.postTestResultsFieldChange(vehicleData.setVrm(VRM), "seatbeltInstallationCheckDate", ToTypeConvertor.NULL, TestResultsLevel.TEST_TYPES);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("seatbeltInstallationCheckDate", "must be a number of milliseconds or valid date string");
    }

}
