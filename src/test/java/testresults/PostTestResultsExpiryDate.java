package testresults;

import data.TestResultsData;
import model.testresults.TestResults;
import model.testresults.TestResultsGet;
import model.testresults.TestResultsStatus;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import net.thucydides.core.annotations.WithTag;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import steps.TestResultsSteps;
import util.DataUtil;

import java.util.Arrays;
import java.util.List;

import static util.DataUtil.generateRandomExcludingValues;

@WithTag("In_test")
@RunWith(SerenityRunner.class)
public class PostTestResultsExpiryDate {

    @Steps
    TestResultsSteps testResultsSteps;

    private TestResults.Builder vehicleSubmittedDataOld = TestResultsData.buildTestResultsSubmittedDataOld();

    @Before
    @Title("warm up test")
    @Test
    public void testResultsWarmUpTest() {
        testResultsSteps.postTestResults(vehicleSubmittedDataOld.build());
        vehicleSubmittedDataOld.build()
                .getTestTypes().get(0).setTestTypeId("16").setTestResult("pass");
    }

    private void validateSavedDataOld(List<String> data) {

        testResultsSteps.getTestResults(vehicleSubmittedDataOld.build().getSystemNumber(), TestResultsStatus.SUBMITTED);
        testResultsSteps.statusCodeShouldBe(200);
        testResultsSteps.validateData((TestResultsGet) vehicleSubmittedDataOld.build());
        testResultsSteps.validateDataForExpiry(data);
    }

    @Title("CVSB-197 / CVSB-3089 - AC B1. VSA submits results for a test type with test type classification 'Annual With Certificate' when the test type result is PASSED (no previous test type records)")
    @Test
    public void testResultsPassWithTestTypeAnnualCertNoPrevRec() {

        vehicleSubmittedDataOld.setVin(generateRandomExcludingValues(21, vehicleSubmittedDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(21, vehicleSubmittedDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleSubmittedDataOld.build().getVrm())).build()
                .getTestTypes().get(0).setTestTypeId("10").setTestResult("pass");

        testResultsSteps.postTestResults(vehicleSubmittedDataOld.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld(Arrays.asList(DataUtil.buildDate(DataUtil.buildCurrentDateTime(), 1, -1)));
    }


    @Title("CVSB-197 / CVSB-3090 - AC B1. VSA submits results for a test type with test type classification 'Annual With Certificate' when the test type result is PRS (no previous test type records)")
    @Test
    public void testResultsPrsWithTestTypeAnnualCertNoPrevRec() {

        vehicleSubmittedDataOld.setVin(generateRandomExcludingValues(21, vehicleSubmittedDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(21, vehicleSubmittedDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleSubmittedDataOld.build().getVrm())).build()
                .getTestTypes().get(0).setTestTypeId("1").setTestResult("prs");

        testResultsSteps.postTestResults(vehicleSubmittedDataOld.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld(Arrays.asList(DataUtil.buildDate(DataUtil.buildCurrentDateTime(), 1, -1)));
    }


    @Title("CVSB-197 / CVSB-3875 - VSA submits results for a test type with test type classification 'Annual With Certificate' when the test type result is NOT PASSED or PRS")
    @Test
    public void testResultsFailWithTestTypeAnnualCertNoPrevRec() {

        vehicleSubmittedDataOld.setVin(generateRandomExcludingValues(21, vehicleSubmittedDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(21, vehicleSubmittedDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleSubmittedDataOld.build().getVrm())).build()
                .getTestTypes().get(0).setTestTypeId("1").setTestResult("fail");

        testResultsSteps.postTestResults(vehicleSubmittedDataOld.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld(null);
    }


    @Title("CVSB-197 / CVSB-3875 - VSA submits results for a test type with test type classification 'Annual With Certificate' when the test type result is NOT PASSED or PRS")
    @Test
    public void testResultsAbandonedWithTestTypeAnnualCertNoPrevRec() {

        vehicleSubmittedDataOld.setVin(generateRandomExcludingValues(21, vehicleSubmittedDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(21, vehicleSubmittedDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleSubmittedDataOld.build().getVrm())).build()
                .getTestTypes().get(0).setTestTypeId("1").setTestResult("abandoned");

        testResultsSteps.postTestResults(vehicleSubmittedDataOld.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld(null);
    }


    @Title("CVSB-197 / CVSB-3876 - VSA submits results for a test type which does NOT have test type classification 'Annual With Certificate'")
    @Test
    public void testResultsPassWithTestTypeNotAnnualCertNoPrevRec() {

        vehicleSubmittedDataOld.setVin(generateRandomExcludingValues(21, vehicleSubmittedDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(21, vehicleSubmittedDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleSubmittedDataOld.build().getVrm())).build()
                .getTestTypes().get(0).setTestTypeId("15").setTestResult("pass");

        testResultsSteps.postTestResults(vehicleSubmittedDataOld.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld(null);
    }


    @Title("CVSB-197 / CVSB-3876 - VSA submits results for a test type which does NOT have test type classification 'Annual With Certificate'")
    @Test
    public void testResultsPrsWithTestTypeNotAnnualCertNoPrevRec() {

        vehicleSubmittedDataOld.setVin(generateRandomExcludingValues(21, vehicleSubmittedDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(21, vehicleSubmittedDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleSubmittedDataOld.build().getVrm())).build()
                .getTestTypes().get(0).setTestTypeId("15").setTestResult("prs");

        testResultsSteps.postTestResults(vehicleSubmittedDataOld.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld(null);
    }


    @Title("CVSB-197 / CVSB-3875 - VSA submits results for a test type with test type classification 'Annual With Certificate' when the test type result is NOT PASSED or PRS")
    @Test
    public void testResultsFailWithTestTypeNotAnnualCertNoPrevRec() {

        vehicleSubmittedDataOld.setVin(generateRandomExcludingValues(21, vehicleSubmittedDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(21, vehicleSubmittedDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleSubmittedDataOld.build().getVrm())).build()
                .getTestTypes().get(0).setTestTypeId("15").setTestResult("fail");

        testResultsSteps.postTestResults(vehicleSubmittedDataOld.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld(null);
    }


    @Title("CVSB-197 / CVSB-3875 - VSA submits results for a test type with test type classification 'Annual With Certificate' when the test type result is NOT PASSED or PRS")
    @Test
    public void testResultsAbandonedWithTestTypeNotAnnualCertNoPrevRec() {

        vehicleSubmittedDataOld.setVin(generateRandomExcludingValues(21, vehicleSubmittedDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(21, vehicleSubmittedDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleSubmittedDataOld.build().getVrm())).build()
                .getTestTypes().get(0).setTestTypeId("15").setTestResult("abandoned");

        testResultsSteps.postTestResults(vehicleSubmittedDataOld.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld(null);
    }


    @Title("CVSB-197 / CVSB-3089 - AC B1. VSA submits results for a test type with test type classification 'Annual With Certificate' when the test type result is PASSED (no previous test type records)")
    @Test
    public void testResultsPassWithTestTypeAnnualCertPrevRec() {

        vehicleSubmittedDataOld.setVin(generateRandomExcludingValues(21, vehicleSubmittedDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(21, vehicleSubmittedDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleSubmittedDataOld.build().getVrm())).build()
                .getTestTypes().get(0).setTestTypeId("1").setTestResult("abandoned");

        testResultsSteps.postTestResults(vehicleSubmittedDataOld.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld(null);

        vehicleSubmittedDataOld.build()
                .getTestTypes().get(0).setTestTypeId("1").setTestResult("pass");

        testResultsSteps.postTestResults(vehicleSubmittedDataOld.setTestResultId(RandomStringUtils.randomAlphanumeric(30)).build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld(Arrays.asList(null, DataUtil.buildDate(DataUtil.buildCurrentDateTime(), 1, -1)));
    }

    @Title("CVSB-197 / CVSB-3090 - AC B1. VSA submits results for a test type with test type classification 'Annual With Certificate' when the test type result is PRS (no previous test type records)")
    @Test
    public void testResultsPrsWithTestTypeAnnualCertPrevRec() {

        vehicleSubmittedDataOld.setVin(generateRandomExcludingValues(21, vehicleSubmittedDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(21, vehicleSubmittedDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleSubmittedDataOld.build().getVrm())).build()
                .getTestTypes().get(0).setTestTypeId("1").setTestResult("abandoned");

        testResultsSteps.postTestResults(vehicleSubmittedDataOld.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld(null);

        vehicleSubmittedDataOld.build()
                .getTestTypes().get(0).setTestTypeId("1").setTestResult("prs");

        testResultsSteps.postTestResults(vehicleSubmittedDataOld.setTestResultId(RandomStringUtils.randomAlphanumeric(30)).build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld(Arrays.asList(null, DataUtil.buildDate(DataUtil.buildCurrentDateTime(), 1, -1)));
    }



    @Title("CVSB-197 / CVSB-3093 - B3. VSA submits results for a test type with test type classification 'Annual With Certificate' when the test type result is PASSED (at least 1 previous test type record + expired certificate more than 2 months after today's date) ")
    @Test
    public void testResultsPassWithTestTypeAnnualCertPrevRecWithExpiryDate() {

        vehicleSubmittedDataOld.setVin(generateRandomExcludingValues(21, vehicleSubmittedDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(21, vehicleSubmittedDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleSubmittedDataOld.build().getVrm())).build()
                .getTestTypes().get(0).setTestTypeId("1").setTestResult("pass");

        testResultsSteps.postTestResults(vehicleSubmittedDataOld.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld(Arrays.asList(DataUtil.buildDate(DataUtil.buildCurrentDateTime(), 1, -1)));

        vehicleSubmittedDataOld.build()
                .getTestTypes().get(0).setTestTypeId("1").setTestResult("pass");

        testResultsSteps.postTestResults(vehicleSubmittedDataOld.setTestResultId(RandomStringUtils.randomAlphanumeric(30)).build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld(Arrays.asList(DataUtil.buildDate(DataUtil.buildCurrentDateTime(), 1, -1), DataUtil.buildDate(DataUtil.buildCurrentDateTime(), 1, -1)));
    }

    @Title("CVSB-197 / CVSB-3095 - B3. VSA submits results for a test type with test type classification 'Annual With Certificate' when the test type result is PRS (at least 1 previous test type record + expired certificate more than 2 months after today's date) ")
    @Test
    public void testResultsPrsWithTestTypeAnnualCertPrevRecWithExpiryDate() {

        vehicleSubmittedDataOld.setVin(generateRandomExcludingValues(21, vehicleSubmittedDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(21, vehicleSubmittedDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleSubmittedDataOld.build().getVrm())).build()
                .getTestTypes().get(0).setTestTypeId("1").setTestResult("pass");

        testResultsSteps.postTestResults(vehicleSubmittedDataOld.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld(Arrays.asList(DataUtil.buildDate(DataUtil.buildCurrentDateTime(), 1, -1)));

        vehicleSubmittedDataOld.build()
                .getTestTypes().get(0).setTestTypeId("1").setTestResult("prs");

        testResultsSteps.postTestResults(vehicleSubmittedDataOld.setTestResultId(RandomStringUtils.randomAlphanumeric(30)).build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld(Arrays.asList(DataUtil.buildDate(DataUtil.buildCurrentDateTime(), 1, -1), DataUtil.buildDate(DataUtil.buildCurrentDateTime(), 1, -1)));
    }


    @Title("CVSB-197 / CVSB-3875 - VSA submits results for a test type with test type classification 'Annual With Certificate' when the test type result is NOT PASSED or PRS ")
    @Test
    public void testResultsAbandonedWithTestTypeAnnualCertPrevRecWithExpiryDate() {

        vehicleSubmittedDataOld.setVin(generateRandomExcludingValues(21, vehicleSubmittedDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(21, vehicleSubmittedDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleSubmittedDataOld.build().getVrm())).build()
                .getTestTypes().get(0).setTestTypeId("1").setTestResult("pass");

        testResultsSteps.postTestResults(vehicleSubmittedDataOld.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld(Arrays.asList(DataUtil.buildDate(DataUtil.buildCurrentDateTime(), 1, -1)));

        vehicleSubmittedDataOld.build()
                .getTestTypes().get(0).setTestTypeId("1").setTestResult("abandoned");

        testResultsSteps.postTestResults(vehicleSubmittedDataOld.setTestResultId(RandomStringUtils.randomAlphanumeric(30)).build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld(Arrays.asList(DataUtil.buildDate(DataUtil.buildCurrentDateTime(), 1, -1), null));
    }

    @Title("CVSB-197 / CVSB-3875 - VSA submits results for a test type with test type classification 'Annual With Certificate' when the test type result is NOT PASSED or PRS ")
    @Test
    public void testResultsFailWithTestTypeAnnualCertPrevRecWithExpiryDate() {

        vehicleSubmittedDataOld.setVin(generateRandomExcludingValues(21, vehicleSubmittedDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(21, vehicleSubmittedDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleSubmittedDataOld.build().getVrm())).build()
                .getTestTypes().get(0).setTestTypeId("1").setTestResult("pass");

        testResultsSteps.postTestResults(vehicleSubmittedDataOld.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld(Arrays.asList(DataUtil.buildDate(DataUtil.buildCurrentDateTime(), 1, -1)));

        vehicleSubmittedDataOld.build()
                .getTestTypes().get(0).setTestTypeId("1").setTestResult("fail");

        testResultsSteps.postTestResults(vehicleSubmittedDataOld.setTestResultId(RandomStringUtils.randomAlphanumeric(30)).build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld(Arrays.asList(DataUtil.buildDate(DataUtil.buildCurrentDateTime(), 1, -1), null));
    }

    @Title("CVSB-197 / CVSB-3876 - VSA submits results for a test type which does NOT have test type classification 'Annual With Certificate'")
    @Test
    public void testResultsPassWithTestTypeNotAnnualCertPrevRecWithExpiryDate() {

        vehicleSubmittedDataOld.setVin(generateRandomExcludingValues(21, vehicleSubmittedDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(21, vehicleSubmittedDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleSubmittedDataOld.build().getVrm())).build()
                .getTestTypes().get(0).setTestTypeId("1").setTestResult("pass");

        testResultsSteps.postTestResults(vehicleSubmittedDataOld.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld(Arrays.asList(DataUtil.buildDate(DataUtil.buildCurrentDateTime(), 1, -1)));

        vehicleSubmittedDataOld.build()
                .getTestTypes().get(0).setTestTypeId("15").setTestResult("pass");

        testResultsSteps.postTestResults(vehicleSubmittedDataOld.setTestResultId(RandomStringUtils.randomAlphanumeric(30)).build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld(Arrays.asList(DataUtil.buildDate(DataUtil.buildCurrentDateTime(), 1, -1), null));
    }

    @Title("CVSB-197 / CVSB-3876 - VSA submits results for a test type which does NOT have test type classification 'Annual With Certificate'")
    @Test
    public void testResultsPrsWithTestTypeNotAnnualCertPrevRecWithExpiryDate() {

        vehicleSubmittedDataOld.setVin(generateRandomExcludingValues(21, vehicleSubmittedDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(21, vehicleSubmittedDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleSubmittedDataOld.build().getVrm())).build()
                .getTestTypes().get(0).setTestTypeId("1").setTestResult("pass");

        testResultsSteps.postTestResults(vehicleSubmittedDataOld.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld(Arrays.asList(DataUtil.buildDate(DataUtil.buildCurrentDateTime(), 1, -1)));

        vehicleSubmittedDataOld.build()
                .getTestTypes().get(0).setTestTypeId("15").setTestResult("prs");

        testResultsSteps.postTestResults(vehicleSubmittedDataOld.setTestResultId(RandomStringUtils.randomAlphanumeric(30)).build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld(Arrays.asList(DataUtil.buildDate(DataUtil.buildCurrentDateTime(), 1, -1), null));
    }

    @Title("CVSB-197 / CVSB-3876 - VSA submits results for a test type which does NOT have test type classification 'Annual With Certificate'")
    @Test
    public void testResultsPrsWithTestTypeNotAnnualCertNoPrevRecWithExpiryDate() {

        vehicleSubmittedDataOld.setVin(generateRandomExcludingValues(21, vehicleSubmittedDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(21, vehicleSubmittedDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleSubmittedDataOld.build().getVrm())).build()
                .getTestTypes().get(0).setTestTypeId("15").setTestResult("abandoned");

        testResultsSteps.postTestResults(vehicleSubmittedDataOld.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld(null);

        vehicleSubmittedDataOld.build()
                .getTestTypes().get(0).setTestTypeId("15").setTestResult("prs");

        testResultsSteps.postTestResults(vehicleSubmittedDataOld.setTestResultId(RandomStringUtils.randomAlphanumeric(30)).build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld(Arrays.asList(null,null));
    }


    @Title("CVSB-197 / CVSB-3876 - VSA submits results for a test type which does NOT have test type classification 'Annual With Certificate'")
    @Test
    public void testResultsPassWithTestTypeNotAnnualCertNoPrevRecWithExpiryDate() {

        vehicleSubmittedDataOld.setVin(generateRandomExcludingValues(21, vehicleSubmittedDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(21, vehicleSubmittedDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleSubmittedDataOld.build().getVrm())).build()
                .getTestTypes().get(0).setTestTypeId("15").setTestResult("abandoned");

        testResultsSteps.postTestResults(vehicleSubmittedDataOld.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld(null);

        vehicleSubmittedDataOld.build()
                .getTestTypes().get(0).setTestTypeId("15").setTestResult("pass");

        testResultsSteps.postTestResults(vehicleSubmittedDataOld.setTestResultId(RandomStringUtils.randomAlphanumeric(30)).build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld(Arrays.asList(null,null));
    }

    @Title("CVSB-197 / CVSB-3875 - VSA submits results for a test type with test type classification 'Annual With Certificate' when the test type result is NOT PASSED or PRS")
    @Test
    public void testResultsPrsWithTestTypeAnnualCertNoPrevRecWithExpiryDate() {

        vehicleSubmittedDataOld.setVin(generateRandomExcludingValues(21, vehicleSubmittedDataOld.build().getVin()))
                .setSystemNumber(generateRandomExcludingValues(21, vehicleSubmittedDataOld.build().getSystemNumber()))
                .setVrm(generateRandomExcludingValues(7, vehicleSubmittedDataOld.build().getVrm())).build()
                .getTestTypes().get(0).setTestTypeId("15").setTestResult("abandoned");

        testResultsSteps.postTestResults(vehicleSubmittedDataOld.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld(null);

        vehicleSubmittedDataOld.build()
                .getTestTypes().get(0).setTestTypeId("1").setTestResult("prs");

        testResultsSteps.postTestResults(vehicleSubmittedDataOld.setTestResultId(RandomStringUtils.randomAlphanumeric(30)).build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedDataOld(Arrays.asList(null,null));
    }

}
