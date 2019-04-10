package testnumber;

import data.TestResultsData;
import model.testresults.TestResults;
import model.testresults.TestResultsStatus;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.core.annotations.WithTags;
import org.junit.Test;
import org.junit.runner.RunWith;
import steps.TestResultsSteps;

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

    private TestResults.Builder vehicleSubmittedDataAnnualWithCertificate = TestResultsData.buildTestResultsSubmittedDataForSpecificTestType("Retest", "Part-paid retest", false);
    private TestResults.Builder vehicleSubmittedDataAnnualWithCertificatePRS = TestResultsData.buildTestResultsSubmittedDataForSpecificTestType("Retest", "Part-paid retest", true);
    private TestResults.Builder vehicleSubmittedDataLEC = TestResultsData.buildTestResultsSubmittedDataForSpecificTestType("Technical test", "Low Emissions Certificate (LEC) with annual test", false);
    private TestResults.Builder buildTestResultsSubmittedDataForAbandonedTestType = TestResultsData.buildTestResultsSubmittedDataForAbandonedTestType();
    private TestResults.Builder buildTestResultsCancelledData = TestResultsData.buildTestResultsCancelledData();
    private TestResults.Builder buildTestResultsCancelledDataWithoutTestType = TestResultsData.buildTestResultsCancelledDataWithoutTestType();


    @Title("TCD - AC B1. VSA submits test results where at least one test type has test type classification 'Annual With Certificate' and the test type result is PASSED (testNumber is generated)")
    @Test
    public void validTestNumberGeneratedForAnnualWithCertificate() {
        vehicleSubmittedDataAnnualWithCertificate.setVin(generateRandomExcludingValues(21, vehicleSubmittedDataAnnualWithCertificate.build().getVin()))
                .setVrm(generateRandomExcludingValues(7, vehicleSubmittedDataAnnualWithCertificate.build().getVrm())).build();

        testResultsSteps.postTestResults(vehicleSubmittedDataAnnualWithCertificate.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        testResultsSteps.getTestResults(vehicleSubmittedDataAnnualWithCertificate.build().getVin(), TestResultsStatus.SUBMITTED);
        testResultsSteps.statusCodeShouldBe(200);
        testResultsSteps.validateTestNumberIsNotNull();
    }

    @Title("TCD - AC B3. VSA submits test results which contain an LEC Test Type (testNumber generated)")
    @Test
    public void validTestNumberGeneratedForLecTestType() {
        vehicleSubmittedDataLEC.setVin(generateRandomExcludingValues(21, vehicleSubmittedDataLEC.build().getVin()))
                .setVrm(generateRandomExcludingValues(7, vehicleSubmittedDataLEC.build().getVrm())).build();

        testResultsSteps.postTestResults(vehicleSubmittedDataLEC.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        testResultsSteps.getTestResults(vehicleSubmittedDataLEC.build().getVin(), TestResultsStatus.SUBMITTED);
        testResultsSteps.statusCodeShouldBe(200);
        testResultsSteps.validateTestNumberIsNotNull();
    }

    @Title("TTCD - AC B1. VSA submits test results where at least one test type has test type classification 'Annual With Certificate' and the test type result is PRS (testNumber is generated)")
    @Test
    public void validTestNumberGeneratedForAnnualWithCertificatePRS() {
        vehicleSubmittedDataAnnualWithCertificatePRS.setVin(generateRandomExcludingValues(21, vehicleSubmittedDataAnnualWithCertificatePRS.build().getVin()))
                .setVrm(generateRandomExcludingValues(7, vehicleSubmittedDataAnnualWithCertificatePRS.build().getVrm())).build();

        testResultsSteps.postTestResults(vehicleSubmittedDataAnnualWithCertificatePRS.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        testResultsSteps.getTestResults(vehicleSubmittedDataAnnualWithCertificatePRS.build().getVin(), TestResultsStatus.SUBMITTED);
        testResultsSteps.statusCodeShouldBe(200);
        testResultsSteps.validateTestNumberIsNotNull();
    }

    @Title("TCD - AC B2. VSA submits test results for a test type where the test type result is abandoned (testNumber generated)")
    @Test
    public void validTestNumberGeneratedForAbandonedTestType() {
        buildTestResultsSubmittedDataForAbandonedTestType.setVin(generateRandomExcludingValues(21, buildTestResultsSubmittedDataForAbandonedTestType.build().getVin()))
                .setVrm(generateRandomExcludingValues(7, buildTestResultsSubmittedDataForAbandonedTestType.build().getVrm())).build();

        testResultsSteps.postTestResults(buildTestResultsSubmittedDataForAbandonedTestType.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        testResultsSteps.getTestResults(buildTestResultsSubmittedDataForAbandonedTestType.build().getVin(), TestResultsStatus.SUBMITTED);
        testResultsSteps.statusCodeShouldBe(200);
        testResultsSteps.validateTestNumberIsNotNull();
    }

    @Title("TCD - AC B5. VSA cancels a test that contains at least one test type (testNumber generated)")
    @Test
    public void validTestNumberGeneratedForCancelledTestType() {
        buildTestResultsCancelledData.setVin(generateRandomExcludingValues(21, buildTestResultsCancelledData.build().getVin()))
                .setVrm(generateRandomExcludingValues(7, buildTestResultsCancelledData.build().getVrm())).build();

        testResultsSteps.postTestResults(buildTestResultsCancelledData.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        testResultsSteps.getTestResults(buildTestResultsCancelledData.build().getVin(), TestResultsStatus.CANCELED);
        testResultsSteps.statusCodeShouldBe(200);
        testResultsSteps.validateTestNumberIsNotNull();
    }

    @Title("TCD - AC B4. VSA cancels a test that does not contain test types (testNumber not generated)")
    @Test
    public void validTestNumberGeneratedForCancelledTWithoutTestType() {
        buildTestResultsCancelledDataWithoutTestType.setVin(generateRandomExcludingValues(21, buildTestResultsCancelledDataWithoutTestType.build().getVin()))
                .setVrm(generateRandomExcludingValues(7, buildTestResultsCancelledDataWithoutTestType.build().getVrm())).build();

        testResultsSteps.postTestResults(buildTestResultsCancelledDataWithoutTestType.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        testResultsSteps.getTestResults(buildTestResultsCancelledDataWithoutTestType.build().getVin(), TestResultsStatus.CANCELED);
        testResultsSteps.statusCodeShouldBe(200);
        testResultsSteps.validateTestNumberIsNull();
    }
}
