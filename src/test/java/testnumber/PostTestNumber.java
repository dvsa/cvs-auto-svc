package testnumber;

import data.TestResultsData;
import model.testresults.TestResults;
import model.testresults.TestResultsStatus;
import model.testresults.TestTypes;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.core.annotations.WithTags;
import org.junit.Test;
import org.junit.runner.RunWith;
import steps.TestResultsSteps;
import java.util.Arrays;
import static util.DataUtil.generateRandomExcludingValues;

@WithTags(
        {
                @WithTag(type = "TestResults", name = "All"),
                @WithTag(type = "TestResults", name = "Positive"),
                @WithTag(type = "Service", name = "One"),
        }
)

@RunWith(SerenityRunner.class)
public class PostTestNumber {

    @Steps
    TestResultsSteps testResultsSteps;
    private TestResults.Builder vehicleSubmittedData = TestResultsData.buildTestResultsSubmittedData();
    private TestResults.Builder vehicleSubmittedDataTwo = TestResultsData.buildTestResultsSubmittedData();
    private TestResults.Builder vehicleCancelledData = TestResultsData.buildTestResultsCancelledData();
    private TestResults.Builder buildTestResultsCancelledDataWithoutTestType = TestResultsData.buildTestResultsCancelledDataWithoutTestType();

    @Title("CVSB-2157 - TCA - AC B1. VSA submits test results where at least one test type has test type classification 'Annual With Certificate' and the test type result is PASSED (testNumber is generated)")
    @Test
    public void validTestNumberGeneratedForAnnualWithCertificate() {
        vehicleSubmittedData.setVin(generateRandomExcludingValues(21, vehicleSubmittedData.build().getVin()))
                .setVrm(generateRandomExcludingValues(7, vehicleSubmittedData.build().getVrm())).build()
                .getTestTypes().get(0).setTestResult("pass").setName("Retest").setTestTypeName("Part-paid retest").setTestTypeId("1");

        testResultsSteps.postTestResults(vehicleSubmittedData.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        testResultsSteps.getTestResults(vehicleSubmittedData.build().getVin(), TestResultsStatus.SUBMITTED);
        testResultsSteps.statusCodeShouldBe(200);
        testResultsSteps.validateTestNumberIsNotNull();
    }

    @Title("CVSB-2157 - TCA - AC B3. VSA submits test results which contain an LEC Test Type (testNumber generated)")
    @Test
    public void validTestNumberGeneratedForLecTestType() {
        vehicleSubmittedData.setVin(generateRandomExcludingValues(21, vehicleSubmittedData.build().getVin()))
                .setVrm(generateRandomExcludingValues(7, vehicleSubmittedData.build().getVrm())).build()
                .getTestTypes().get(0).setTestResult("pass").setName("Technical test").setTestTypeName("Low Emissions Certificate (LEC) with annual test")
                .setTestTypeId("39");

        testResultsSteps.postTestResults(vehicleSubmittedData.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        testResultsSteps.getTestResults(vehicleSubmittedData.build().getVin(), TestResultsStatus.SUBMITTED);
        testResultsSteps.statusCodeShouldBe(200);
        testResultsSteps.validateTestNumberIsNotNull();
    }

    @Title("CVSB-2157 - TCA - AC B1. VSA submits test results where at least one test type has test type classification 'Annual With Certificate' and the test type result is PRS (testNumber is generated)")
    @Test
    public void validTestNumberGeneratedForAnnualWithCertificatePRS() {
        vehicleSubmittedData.setVin(generateRandomExcludingValues(21, vehicleSubmittedData.build().getVin()))
                .setVrm(generateRandomExcludingValues(7, vehicleSubmittedData.build().getVrm())).build()
                .getTestTypes().get(0).setTestTypeId("1").setTestResult("prs").setName("Retest").setTestTypeName("Part-paid retest")
                .getDefects().get(0).setPrs(true);

        testResultsSteps.postTestResults(vehicleSubmittedData.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        testResultsSteps.getTestResults(vehicleSubmittedData.build().getVin(), TestResultsStatus.SUBMITTED);
        testResultsSteps.statusCodeShouldBe(200);
        testResultsSteps.validateTestNumberIsNotNull();
    }

    @Title("CVSB-2157 - TCA - AC B2. VSA submits test results for a test type where the test type result is abandoned (testNumber generated)")
    @Test
    public void validTestNumberGeneratedForAbandonedTestType() {
        vehicleSubmittedData.setVin(generateRandomExcludingValues(21, vehicleSubmittedData.build().getVin()))
                .setVrm(generateRandomExcludingValues(7, vehicleSubmittedData.build().getVrm())).build()
                .getTestTypes().get(0).setTestResult("abandoned").setName("Retest").setTestTypeName("Part-paid retest")
                .setReasonForAbandoning("The vehicle was not submitted for test at the appointed time.");

        testResultsSteps.postTestResults(vehicleSubmittedData.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        testResultsSteps.getTestResults(vehicleSubmittedData.build().getVin(), TestResultsStatus.SUBMITTED);
        testResultsSteps.statusCodeShouldBe(200);
        testResultsSteps.validateTestNumberIsNotNull();
    }

    @Title("CVSB-2157 - TCA - AC B5. VSA cancels a test that contains at least one test type (testNumber generated)")
    @Test
    public void validTestNumberGeneratedForCancelledTestType() {
        vehicleCancelledData.setVin(generateRandomExcludingValues(21, vehicleCancelledData.build().getVin()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledData.build().getVrm())).build();

        testResultsSteps.postTestResults(vehicleSubmittedData.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        testResultsSteps.getTestResults(vehicleSubmittedData.build().getVin(), TestResultsStatus.CANCELED);
        testResultsSteps.statusCodeShouldBe(200);
        testResultsSteps.validateTestNumberIsNotNull();
    }

    @Title("CVSB-2157 - TCA - AC B4. VSA cancels a test that does not contain test types (testNumber not generated)")
    @Test
    public void validTestNumberNotGeneratedForCancelledTWithoutTestType() {
        buildTestResultsCancelledDataWithoutTestType.setVin(generateRandomExcludingValues(21, buildTestResultsCancelledDataWithoutTestType.build().getVin()))
                .setVrm(generateRandomExcludingValues(7, buildTestResultsCancelledDataWithoutTestType.build().getVrm())).build();

        testResultsSteps.postTestResults(buildTestResultsCancelledDataWithoutTestType.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        testResultsSteps.getTestResults(buildTestResultsCancelledDataWithoutTestType.build().getVin(), TestResultsStatus.CANCELED);
        testResultsSteps.statusCodeShouldBe(200);
        testResultsSteps.validateTestNumberIsNull();
    }

    @Title("CVSB-2157 - TCA - AC B5. VSA cancels a test that contains at least one test type (certificateNumber attribute is null)")
    @Test
    public void validCertificateNumberNotGeneratedForCancelledTWithoutTestType() {

        buildTestResultsCancelledDataWithoutTestType.setVin(generateRandomExcludingValues(21, buildTestResultsCancelledDataWithoutTestType.build().getVin()))
                .setVrm(generateRandomExcludingValues(7, buildTestResultsCancelledDataWithoutTestType.build().getVrm())).build();

        testResultsSteps.postTestResults(buildTestResultsCancelledDataWithoutTestType.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        testResultsSteps.getTestResults(buildTestResultsCancelledDataWithoutTestType.build().getVin(), TestResultsStatus.CANCELED);
        testResultsSteps.statusCodeShouldBe(200);
        testResultsSteps.validateCertificateNumberIsNull();
    }

    @Title("CVSB-2157 - TCA - AC B1. VSA submits test results where at least one test type has test type classification 'Annual With Certificate' and the test type result is PASSED (testNumber populated into certificateNumber attribute)")
    @Test
    public void validTestNumberGeneratedForAnnualWithCertificateIsEqualToCertificateNumber() {
        vehicleSubmittedData.setVin(generateRandomExcludingValues(21, vehicleSubmittedData.build().getVin()))
                .setVrm(generateRandomExcludingValues(7, vehicleSubmittedData.build().getVrm())).build()
                .getTestTypes().get(0).setTestTypeId("1").setTestResult("pass").setName("Retest").setTestTypeName("Part-paid retest");

        testResultsSteps.postTestResults(vehicleSubmittedData.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        testResultsSteps.getTestResults(vehicleSubmittedData.build().getVin(), TestResultsStatus.SUBMITTED);
        testResultsSteps.statusCodeShouldBe(200);
        testResultsSteps.validateTestNumberEqualsCertificateNumber();
    }

    @Title("CVSB-2157 - TCA - AC B1. VSA submits test results where at least one test type has test type classification 'Annual With Certificate' and the test type result is PRS (testNumber populated into certificateNumber attribute)")
    @Test
    public void validTestNumberGeneratedForAnnualWithCertificatePRSIsEqualToCertificateNumber() {
        vehicleSubmittedData.setVin(generateRandomExcludingValues(21, vehicleSubmittedData.build().getVin()))
                .setVrm(generateRandomExcludingValues(7, vehicleSubmittedData.build().getVrm())).build()
                .getTestTypes().get(0).setTestTypeId("1").setTestResult("prs").setName("Retest").setTestTypeName("Part-paid retest")
                .getDefects().get(0).setPrs(true);

        testResultsSteps.postTestResults(vehicleSubmittedData.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        testResultsSteps.getTestResults(vehicleSubmittedData.build().getVin(), TestResultsStatus.SUBMITTED);
        testResultsSteps.statusCodeShouldBe(200);
        testResultsSteps.validateTestNumberEqualsCertificateNumber();
    }

    @Title("CVSB-2157 - TCA - AC B3. VSA submits test results which contain an LEC Test Type (testNumber and certificate number are not the same)")
    @Test
    public void validTestNumberGeneratedForLecTestTypeIsNotEqualToCertificateNumber() {
        vehicleSubmittedData.setVin(generateRandomExcludingValues(21, vehicleSubmittedData.build().getVin()))
                .setVrm(generateRandomExcludingValues(7, vehicleSubmittedData.build().getVrm())).build()
                .getTestTypes().get(0).setTestResult("pass").setName("Technical test").setTestTypeName("Low Emissions Certificate (LEC) with annual test")
                .setTestTypeId("39");

        testResultsSteps.postTestResults(vehicleSubmittedData.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        testResultsSteps.getTestResults(vehicleSubmittedData.build().getVin(), TestResultsStatus.SUBMITTED);
        testResultsSteps.statusCodeShouldBe(200);
        testResultsSteps.validateTestNumberNotEqualCertificateNumber();
    }

    @Title("CVSB-2157 - TCA - AC B1. VSA submits test results where at least one test type has test type classification 'Annual With Certificate' and the test type result is PASSED (testNumber is generated)\"")
    @Test
    public void validTestNumberGenerationForAtLeastTwoTestTypes(){
        TestTypes testTypesOne = ((TestTypes) vehicleSubmittedData.getTestTypes().get(0)).setTestTypeId("39");
        TestTypes testTypesTwo = ((TestTypes) vehicleSubmittedDataTwo.getTestTypes().get(0)).setTestTypeId("1");

        vehicleSubmittedData.setVin(generateRandomExcludingValues(21, vehicleSubmittedData.build().getVin()))
                .setTestTypes(Arrays.asList(testTypesOne, testTypesTwo))
                .setVrm(generateRandomExcludingValues(7, vehicleSubmittedData.build().getVrm())).build();
        testResultsSteps.postTestResults(vehicleSubmittedData.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        testResultsSteps.getTestResults(vehicleSubmittedData.build().getVin(), TestResultsStatus.SUBMITTED);
        testResultsSteps.statusCodeShouldBe(200);
        testResultsSteps.validateTestNumberIsDifferentForTwoTestTypes();
    }

    @Title("AC A1. VSA submits test results (when current cert letter in database is not 'Z', and current sequence number in database is not '999') (testNumber generation database values updated)")
    @Test
    public void verifyNextTestNumberGeneration() {
        vehicleSubmittedData.setVin(generateRandomExcludingValues(21, vehicleSubmittedData.build().getVin()))
                .setVrm(generateRandomExcludingValues(7, vehicleSubmittedData.build().getVrm())).build()
                .getTestTypes().get(0).setTestTypeId("1").setTestResult("prs").setName("Retest").setTestTypeName("Part-paid retest")
                .getDefects().get(0).setPrs(true);

        testResultsSteps.postTestResults(vehicleSubmittedData.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        testResultsSteps.getTestResults(vehicleSubmittedData.build().getVin(), TestResultsStatus.SUBMITTED);
        testResultsSteps.statusCodeShouldBe(200);
        testResultsSteps.nextTestNumber();

        vehicleSubmittedData.setVin(generateRandomExcludingValues(21, vehicleSubmittedData.build().getVin()))
                .setVrm(generateRandomExcludingValues(7, vehicleSubmittedData.build().getVrm())).build()
                .getTestTypes().get(0).setTestResult("pass").setName("Technical test").setTestTypeName("Low Emissions Certificate (LEC) with annual test")
                .setTestTypeId("39");

        testResultsSteps.postTestResults(vehicleSubmittedData.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        testResultsSteps.getTestResults(vehicleSubmittedData.build().getVin(), TestResultsStatus.SUBMITTED);
        testResultsSteps.statusCodeShouldBe(200);
        testResultsSteps.checkNextTestNumberIsValid();
    }
}
