package testresults;

import data.TestResultsData;
import model.testresults.TestResults;
import model.testresults.TestResultsGet;
import model.testresults.TestResultsStatus;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import steps.TestResultsSteps;
import util.DataUtil;

import java.util.Arrays;
import java.util.List;

import static util.DataUtil.generateRandomExcludingValues;


@RunWith(SerenityRunner.class)
public class PostTestResultsAnniversaryDate {
    @Steps
    TestResultsSteps testResultsSteps;

    private TestResults.Builder vehicleSubmittedData = TestResultsData.buildTestResultsSubmittedData();

    private void validateSavedData(List<String> data) {

        testResultsSteps.getTestResults(vehicleSubmittedData.build().getVin(), TestResultsStatus.SUBMITTED);
        testResultsSteps.statusCodeShouldBe(200);
        testResultsSteps.validateData((TestResultsGet) vehicleSubmittedData.build());
        testResultsSteps.validateDataForAnniversary(data);
    }

    @Title("CVSB-197 / CVSB-3119 C1. VSA submits test results for a 'Annual with certificate' test type which has a test type result of PASS and therefore necessitates the calculation of anniversary date (anniversary date is calculated and populated)")
    @Test
    public void testResultsPassWithTestTypeAnnualCertNoPrevRec() {

        vehicleSubmittedData.setVin(generateRandomExcludingValues(21, vehicleSubmittedData.build().getVin()))
                .setVrm(generateRandomExcludingValues(7, vehicleSubmittedData.build().getVrm())).build()
                .getTestTypes().get(0).setTestTypeId("10").setTestResult("pass");

        testResultsSteps.postTestResults(vehicleSubmittedData.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedData(Arrays.asList(DataUtil.buildDate(DataUtil.buildCurrentDateTime(), 1, -2, 0)));
    }


    @Title("CVSB-197 / CVSB-3120 C1. VSA submits test results for a 'Annual with certificate' test type which has a test type result of PRS and therefore necessitates the calculation of anniversary date (anniversary date is calculated and populated)")
    @Test
    public void testResultsPrsWithTestTypeAnnualCertNoPrevRec() {

        vehicleSubmittedData.setVin(generateRandomExcludingValues(21, vehicleSubmittedData.build().getVin()))
                .setVrm(generateRandomExcludingValues(7, vehicleSubmittedData.build().getVrm())).build()
                .getTestTypes().get(0).setTestTypeId("1").setTestResult("prs");

        testResultsSteps.postTestResults(vehicleSubmittedData.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedData(Arrays.asList(DataUtil.buildDate(DataUtil.buildCurrentDateTime(), 1, -2, 0)));
    }


    @Title("CVSB-197 / CVSB-3875 VSA submits results for a test type with test type classification 'Annual With Certificate' when the test type result is NOT PASSED or PRS")
    @Test
    public void testResultsFailWithTestTypeAnnualCertNoPrevRec() {

        vehicleSubmittedData.setVin(generateRandomExcludingValues(21, vehicleSubmittedData.build().getVin()))
                .setVrm(generateRandomExcludingValues(7, vehicleSubmittedData.build().getVrm())).build()
                .getTestTypes().get(0).setTestTypeId("1").setTestResult("fail");

        testResultsSteps.postTestResults(vehicleSubmittedData.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedData(null);
    }


    @Title("CVSB-197 / CVSB-3875 VSA submits results for a test type with test type classification 'Annual With Certificate' when the test type result is NOT PASSED or PRS")
    @Test
    public void testResultsAbandonedWithTestTypeAnnualCertNoPrevRec() {

        vehicleSubmittedData.setVin(generateRandomExcludingValues(21, vehicleSubmittedData.build().getVin()))
                .setVrm(generateRandomExcludingValues(7, vehicleSubmittedData.build().getVrm())).build()
                .getTestTypes().get(0).setTestTypeId("1").setTestResult("abandoned");

        testResultsSteps.postTestResults(vehicleSubmittedData.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedData(null);
    }


    @Title("CVSB-197 / CVSB-3876 - VSA submits results for a test type which does NOT have test type classification 'Annual With Certificate'")
    @Test
    public void testResultsPassWithTestTypeNotAnnualCertNoPrevRec() {

        vehicleSubmittedData.setVin(generateRandomExcludingValues(21, vehicleSubmittedData.build().getVin()))
                .setVrm(generateRandomExcludingValues(7, vehicleSubmittedData.build().getVrm())).build()
                .getTestTypes().get(0).setTestTypeId("15").setTestResult("pass");

        testResultsSteps.postTestResults(vehicleSubmittedData.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedData(null);
    }


    @Title("CVSB-197 / CVSB-3876 - VSA submits results for a test type which does NOT have test type classification 'Annual With Certificate'")
    @Test
    public void testResultsPrsWithTestTypeNotAnnualCertNoPrevRec() {

        vehicleSubmittedData.setVin(generateRandomExcludingValues(21, vehicleSubmittedData.build().getVin()))
                .setVrm(generateRandomExcludingValues(7, vehicleSubmittedData.build().getVrm())).build()
                .getTestTypes().get(0).setTestTypeId("15").setTestResult("prs");

        testResultsSteps.postTestResults(vehicleSubmittedData.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedData(null);
    }


    @Title("CVSB-197 / CVSB-3876 - VSA submits results for a test type which does NOT have test type classification 'Annual With Certificate'")
    @Test
    public void testResultsFailWithTestTypeNotAnnualCertNoPrevRec() {

        vehicleSubmittedData.setVin(generateRandomExcludingValues(21, vehicleSubmittedData.build().getVin()))
                .setVrm(generateRandomExcludingValues(7, vehicleSubmittedData.build().getVrm())).build()
                .getTestTypes().get(0).setTestTypeId("15").setTestResult("fail");

        testResultsSteps.postTestResults(vehicleSubmittedData.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedData(null);
    }


    @Title("CVSB-197 / CVSB-3876 - VSA submits results for a test type which does NOT have test type classification 'Annual With Certificate'")
    @Test
    public void testResultsAbandonedWithTestTypeNotAnnualCertNoPrevRec() {

        vehicleSubmittedData.setVin(generateRandomExcludingValues(21, vehicleSubmittedData.build().getVin()))
                .setVrm(generateRandomExcludingValues(7, vehicleSubmittedData.build().getVrm())).build()
                .getTestTypes().get(0).setTestTypeId("15").setTestResult("abandoned");

        testResultsSteps.postTestResults(vehicleSubmittedData.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedData(null);
    }


    @Title("CVSB-197 / CVSB-3119 C1. VSA submits test results for a 'Annual with certificate' test type which has a test type result of PASS and therefore necessitates the calculation of anniversary date (anniversary date is calculated and populated)")
    @Test
    public void testResultsPassWithTestTypeAnnualCertPrevRec() {

        vehicleSubmittedData.setVin(generateRandomExcludingValues(21, vehicleSubmittedData.build().getVin()))
                .setVrm(generateRandomExcludingValues(7, vehicleSubmittedData.build().getVrm())).build()
                .getTestTypes().get(0).setTestTypeId("1").setTestResult("abandoned");

        testResultsSteps.postTestResults(vehicleSubmittedData.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedData(null);

        vehicleSubmittedData.build()
                .getTestTypes().get(0).setTestTypeId("1").setTestResult("pass");

        testResultsSteps.postTestResults(vehicleSubmittedData.setTestResultId(RandomStringUtils.randomAlphanumeric(30)).build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedData(Arrays.asList(null, DataUtil.buildDate(DataUtil.buildCurrentDateTime(), 1, -2, 0)));
    }

    @Title("CVSB-197 / CVSB-3120 C1. VSA submits test results for a 'Annual with certificate' test type which has a test type result of PRS and therefore necessitates the calculation of anniversary date (anniversary date is calculated and populated)")
    @Test
    public void testResultsPrsWithTestTypeAnnualCertPrevRec() {

        vehicleSubmittedData.setVin(generateRandomExcludingValues(21, vehicleSubmittedData.build().getVin()))
                .setVrm(generateRandomExcludingValues(7, vehicleSubmittedData.build().getVrm())).build()
                .getTestTypes().get(0).setTestTypeId("1").setTestResult("abandoned");

        testResultsSteps.postTestResults(vehicleSubmittedData.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedData(null);

        vehicleSubmittedData.build()
                .getTestTypes().get(0).setTestTypeId("1").setTestResult("prs");

        testResultsSteps.postTestResults(vehicleSubmittedData.setTestResultId(RandomStringUtils.randomAlphanumeric(30)).build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedData(Arrays.asList(null, DataUtil.buildDate(DataUtil.buildCurrentDateTime(), 1, -2, 0)));
    }


    @Title("CVSB-197 / CVSB-3119 C1. VSA submits test results for a 'Annual with certificate' test type which has a test type result of PASS and therefore necessitates the calculation of anniversary date (anniversary date is calculated and populated)")
    @Test
    public void testResultsPassWithTestTypeAnnualCertPrevRecWithExpiryDate() {

        vehicleSubmittedData.setVin(generateRandomExcludingValues(21, vehicleSubmittedData.build().getVin()))
                .setVrm(generateRandomExcludingValues(7, vehicleSubmittedData.build().getVrm())).build()
                .getTestTypes().get(0).setTestTypeId("1").setTestResult("pass");

        testResultsSteps.postTestResults(vehicleSubmittedData.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedData(Arrays.asList(DataUtil.buildDate(DataUtil.buildCurrentDateTime(), 1, -2, 0)));

        vehicleSubmittedData.build()
                .getTestTypes().get(0).setTestTypeId("1").setTestResult("pass");

        testResultsSteps.postTestResults(vehicleSubmittedData.setTestResultId(RandomStringUtils.randomAlphanumeric(30)).build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedData(Arrays.asList(DataUtil.buildDate(DataUtil.buildCurrentDateTime(), 1, -2, 0), DataUtil.buildDate(DataUtil.buildCurrentDateTime(), 1, -2, 0)));
    }

    @Title("CVSB-197 / CVSB-3120 C1. VSA submits test results for a 'Annual with certificate' test type which has a test type result of PRS and therefore necessitates the calculation of anniversary date (anniversary date is calculated and populated)")
    @Test
    public void testResultsPrsWithTestTypeAnnualCertPrevRecWithExpiryDate() {

        vehicleSubmittedData.setVin(generateRandomExcludingValues(21, vehicleSubmittedData.build().getVin()))
                .setVrm(generateRandomExcludingValues(7, vehicleSubmittedData.build().getVrm())).build()
                .getTestTypes().get(0).setTestTypeId("1").setTestResult("pass");

        testResultsSteps.postTestResults(vehicleSubmittedData.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedData(Arrays.asList(DataUtil.buildDate(DataUtil.buildCurrentDateTime(), 1, -2, 0)));

        vehicleSubmittedData.build()
                .getTestTypes().get(0).setTestTypeId("1").setTestResult("prs");

        testResultsSteps.postTestResults(vehicleSubmittedData.setTestResultId(RandomStringUtils.randomAlphanumeric(30)).build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedData(Arrays.asList(DataUtil.buildDate(DataUtil.buildCurrentDateTime(), 1, -2, 0), DataUtil.buildDate(DataUtil.buildCurrentDateTime(), 1, -2, 0)));
    }


    @Title("CVSB-197 / CVSB-3876 - VSA submits results for a test type which does NOT have test type classification 'Annual With Certificate'")
    @Test
    public void testResultsAbandonedWithTestTypeAnnualCertPrevRecWithExpiryDate() {

        vehicleSubmittedData.setVin(generateRandomExcludingValues(21, vehicleSubmittedData.build().getVin()))
                .setVrm(generateRandomExcludingValues(7, vehicleSubmittedData.build().getVrm())).build()
                .getTestTypes().get(0).setTestTypeId("1").setTestResult("pass");

        testResultsSteps.postTestResults(vehicleSubmittedData.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedData(Arrays.asList(DataUtil.buildDate(DataUtil.buildCurrentDateTime(), 1, -2, 0)));

        vehicleSubmittedData.build()
                .getTestTypes().get(0).setTestTypeId("1").setTestResult("abandoned");

        testResultsSteps.postTestResults(vehicleSubmittedData.setTestResultId(RandomStringUtils.randomAlphanumeric(30)).build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedData(Arrays.asList(DataUtil.buildDate(DataUtil.buildCurrentDateTime(), 1, -2, 0), null));
    }

    @Title("CVSB-197 / CVSB-3875 VSA submits results for a test type with test type classification 'Annual With Certificate' when the test type result is NOT PASSED or PRS")
    @Test
    public void testResultsFailWithTestTypeAnnualCertPrevRecWithExpiryDate() {

        vehicleSubmittedData.setVin(generateRandomExcludingValues(21, vehicleSubmittedData.build().getVin()))
                .setVrm(generateRandomExcludingValues(7, vehicleSubmittedData.build().getVrm())).build()
                .getTestTypes().get(0).setTestTypeId("1").setTestResult("pass");

        testResultsSteps.postTestResults(vehicleSubmittedData.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedData(Arrays.asList(DataUtil.buildDate(DataUtil.buildCurrentDateTime(), 1, -2, 0)));

        vehicleSubmittedData.build()
                .getTestTypes().get(0).setTestTypeId("1").setTestResult("fail");

        testResultsSteps.postTestResults(vehicleSubmittedData.setTestResultId(RandomStringUtils.randomAlphanumeric(30)).build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedData(Arrays.asList(DataUtil.buildDate(DataUtil.buildCurrentDateTime(), 1, -2, 0), null));
    }

    @Title("CVSB-197 / CVSB-3876 - VSA submits results for a test type which does NOT have test type classification 'Annual With Certificate'")
    @Test
    public void testResultsPassWithTestTypeNotAnnualCertPrevRecWithExpiryDate() {

        vehicleSubmittedData.setVin(generateRandomExcludingValues(21, vehicleSubmittedData.build().getVin()))
                .setVrm(generateRandomExcludingValues(7, vehicleSubmittedData.build().getVrm())).build()
                .getTestTypes().get(0).setTestTypeId("1").setTestResult("pass");

        testResultsSteps.postTestResults(vehicleSubmittedData.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedData(Arrays.asList(DataUtil.buildDate(DataUtil.buildCurrentDateTime(), 1, -2, 0)));

        vehicleSubmittedData.build()
                .getTestTypes().get(0).setTestTypeId("15").setTestResult("pass");

        testResultsSteps.postTestResults(vehicleSubmittedData.setTestResultId(RandomStringUtils.randomAlphanumeric(30)).build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedData(Arrays.asList(DataUtil.buildDate(DataUtil.buildCurrentDateTime(), 1, -2, 0), null));
    }

    @Title("CVSB-197 / CVSB-3876 - VSA submits results for a test type which does NOT have test type classification 'Annual With Certificate'")
    @Test
    public void testResultsPrsWithTestTypeNotAnnualCertPrevRecWithExpiryDate() {

        vehicleSubmittedData.setVin(generateRandomExcludingValues(21, vehicleSubmittedData.build().getVin()))
                .setVrm(generateRandomExcludingValues(7, vehicleSubmittedData.build().getVrm())).build()
                .getTestTypes().get(0).setTestTypeId("1").setTestResult("pass");

        testResultsSteps.postTestResults(vehicleSubmittedData.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedData(Arrays.asList(DataUtil.buildDate(DataUtil.buildCurrentDateTime(), 1, -2, 0)));

        vehicleSubmittedData.build()
                .getTestTypes().get(0).setTestTypeId("15").setTestResult("prs");

        testResultsSteps.postTestResults(vehicleSubmittedData.setTestResultId(RandomStringUtils.randomAlphanumeric(30)).build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedData(Arrays.asList(DataUtil.buildDate(DataUtil.buildCurrentDateTime(), 1, -2, 0), null));
    }


    @Title("CVSB-197 / CVSB-3876 - VSA submits results for a test type which does NOT have test type classification 'Annual With Certificate'")
    @Test
    public void testResultsPrsWithTestTypeNotAnnualCertNoPrevRecWithExpiryDate() {

        vehicleSubmittedData.setVin(generateRandomExcludingValues(21, vehicleSubmittedData.build().getVin()))
                .setVrm(generateRandomExcludingValues(7, vehicleSubmittedData.build().getVrm())).build()
                .getTestTypes().get(0).setTestTypeId("15").setTestResult("abandoned");

        testResultsSteps.postTestResults(vehicleSubmittedData.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedData(null);

        vehicleSubmittedData.build()
                .getTestTypes().get(0).setTestTypeId("15").setTestResult("prs");

        testResultsSteps.postTestResults(vehicleSubmittedData.setTestResultId(RandomStringUtils.randomAlphanumeric(30)).build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedData(Arrays.asList(null, null));
    }


    @Title("CVSB-197 / CVSB-3876 - VSA submits results for a test type which does NOT have test type classification 'Annual With Certificate'")
    @Test
    public void testResultsPassWithTestTypeNotAnnualCertNoPrevRecWithExpiryDate() {

        vehicleSubmittedData.setVin(generateRandomExcludingValues(21, vehicleSubmittedData.build().getVin()))
                .setVrm(generateRandomExcludingValues(7, vehicleSubmittedData.build().getVrm())).build()
                .getTestTypes().get(0).setTestTypeId("15").setTestResult("abandoned");

        testResultsSteps.postTestResults(vehicleSubmittedData.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedData(null);

        vehicleSubmittedData.build()
                .getTestTypes().get(0).setTestTypeId("15").setTestResult("pass");

        testResultsSteps.postTestResults(vehicleSubmittedData.setTestResultId(RandomStringUtils.randomAlphanumeric(30)).build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedData(Arrays.asList(null, null));
    }

    @Title("CVSB-197 / CVSB-3876 - VSA submits results for a test type which does NOT have test type classification 'Annual With Certificate'")
    @Test
    public void testResultsPrsWithTestTypeAnnualCertNoPrevRecWithExpiryDate() {

        vehicleSubmittedData.setVin(generateRandomExcludingValues(21, vehicleSubmittedData.build().getVin()))
                .setVrm(generateRandomExcludingValues(7, vehicleSubmittedData.build().getVrm())).build()
                .getTestTypes().get(0).setTestTypeId("15").setTestResult("abandoned");

        testResultsSteps.postTestResults(vehicleSubmittedData.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedData(null);

        vehicleSubmittedData.build()
                .getTestTypes().get(0).setTestTypeId("1").setTestResult("prs");

        testResultsSteps.postTestResults(vehicleSubmittedData.setTestResultId(RandomStringUtils.randomAlphanumeric(30)).build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedData(Arrays.asList(null, null));
    }

}
