package testresults;

import data.TestResultsData;
import model.testresults.TestResults;
import model.testresults.TestResultsGet;
import model.testresults.TestResultsStatus;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.core.annotations.WithTags;
import org.junit.Test;
import org.junit.runner.RunWith;
import steps.TestResultsSteps;
import util.DataUtil;

import java.util.Arrays;
import java.util.List;

import static util.DataUtil.generateRandomExcludingValues;

@WithTags(
        {
                @WithTag(type = "TestResults", name = "All"),
                @WithTag(type = "TestResults", name = "Positive"),
                @WithTag(type = "Service", name = "One"),

        }
)

@RunWith(SerenityRunner.class)
public class PostTestResultsExpiryDate {

    @Steps
    TestResultsSteps testResultsSteps;

    private TestResults.Builder vehicleSubmittedData = TestResultsData.buildTestResultsSubmittedData();

    private void validateSavedData(List<String> data) {

        testResultsSteps.getTestResults(vehicleSubmittedData.build().getVin(), TestResultsStatus.SUBMITTED);
        testResultsSteps.statusCodeShouldBe(200);
        testResultsSteps.validateData((TestResultsGet) vehicleSubmittedData.build());
        testResultsSteps.validateDataForExpiry(data);
    }

    @Title("CVSB-197 / CVSB-3089 - AC B1. VSA submits results for a test type with test type classification 'Annual With Certificate' when the test type result is PASSED (no previous test type records)")
    @Test
    public void testResultsPassWithTestTypeAnnualCertNoPrevRec() {

        vehicleSubmittedData.setVin(generateRandomExcludingValues(21, vehicleSubmittedData.build().getVin()))
                .setVrm(generateRandomExcludingValues(7, vehicleSubmittedData.build().getVrm())).build()
                .getTestTypes().get(0).setTestTypeId("10").setTestResult("pass");

        testResultsSteps.postTestResults(vehicleSubmittedData.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedData(Arrays.asList(DataUtil.buildDate(DataUtil.buildCurrentDateTime(), 1, -1)));
    }


    @Title("CVSB-197 / CVSB-3090 - AC B1. VSA submits results for a test type with test type classification 'Annual With Certificate' when the test type result is PRS (no previous test type records)")
    @Test
    public void testResultsPrsWithTestTypeAnnualCertNoPrevRec() {

        vehicleSubmittedData.setVin(generateRandomExcludingValues(21, vehicleSubmittedData.build().getVin()))
                .setVrm(generateRandomExcludingValues(7, vehicleSubmittedData.build().getVrm())).build()
                .getTestTypes().get(0).setTestTypeId("1").setTestResult("prs");

        testResultsSteps.postTestResults(vehicleSubmittedData.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedData(Arrays.asList(DataUtil.buildDate(DataUtil.buildCurrentDateTime(), 1, -1)));
    }


    @Title("CVSB-197 / CVSB-3875 - VSA submits results for a test type with test type classification 'Annual With Certificate' when the test type result is NOT PASSED or PRS")
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


    @Title("CVSB-197 / CVSB-3875 - VSA submits results for a test type with test type classification 'Annual With Certificate' when the test type result is NOT PASSED or PRS")
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


    @Title("CVSB-197 / CVSB-3875 - VSA submits results for a test type with test type classification 'Annual With Certificate' when the test type result is NOT PASSED or PRS")
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


    @Title("CVSB-197 / CVSB-3875 - VSA submits results for a test type with test type classification 'Annual With Certificate' when the test type result is NOT PASSED or PRS")
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


    @Title("CVSB-197 / CVSB-3089 - AC B1. VSA submits results for a test type with test type classification 'Annual With Certificate' when the test type result is PASSED (no previous test type records)")
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

        testResultsSteps.postTestResults(vehicleSubmittedData.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedData(Arrays.asList(null, DataUtil.buildDate(DataUtil.buildCurrentDateTime(), 1, -1)));
    }

    @Title("CVSB-197 / CVSB-3090 - AC B1. VSA submits results for a test type with test type classification 'Annual With Certificate' when the test type result is PRS (no previous test type records)")
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

        testResultsSteps.postTestResults(vehicleSubmittedData.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedData(Arrays.asList(null, DataUtil.buildDate(DataUtil.buildCurrentDateTime(), 1, -1)));
    }



    @Title("CVSB-197 / CVSB-3093 - B3. VSA submits results for a test type with test type classification 'Annual With Certificate' when the test type result is PASSED (at least 1 previous test type record + expired certificate more than 2 months after today's date) ")
    @Test
    public void testResultsPassWithTestTypeAnnualCertPrevRecWithExpiryDate() {

        vehicleSubmittedData.setVin(generateRandomExcludingValues(21, vehicleSubmittedData.build().getVin()))
                .setVrm(generateRandomExcludingValues(7, vehicleSubmittedData.build().getVrm())).build()
                .getTestTypes().get(0).setTestTypeId("1").setTestResult("pass");

        testResultsSteps.postTestResults(vehicleSubmittedData.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedData(Arrays.asList(DataUtil.buildDate(DataUtil.buildCurrentDateTime(), 1, -1)));

        vehicleSubmittedData.build()
                .getTestTypes().get(0).setTestTypeId("1").setTestResult("pass");

        testResultsSteps.postTestResults(vehicleSubmittedData.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedData(Arrays.asList(DataUtil.buildDate(DataUtil.buildCurrentDateTime(), 1, -1), DataUtil.buildDate(DataUtil.buildCurrentDateTime(), 1, -1)));
    }

    @Title("CVSB-197 / CVSB-3095 - B3. VSA submits results for a test type with test type classification 'Annual With Certificate' when the test type result is PRS (at least 1 previous test type record + expired certificate more than 2 months after today's date) ")
    @Test
    public void testResultsPrsWithTestTypeAnnualCertPrevRecWithExpiryDate() {

        vehicleSubmittedData.setVin(generateRandomExcludingValues(21, vehicleSubmittedData.build().getVin()))
                .setVrm(generateRandomExcludingValues(7, vehicleSubmittedData.build().getVrm())).build()
                .getTestTypes().get(0).setTestTypeId("1").setTestResult("pass");

        testResultsSteps.postTestResults(vehicleSubmittedData.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedData(Arrays.asList(DataUtil.buildDate(DataUtil.buildCurrentDateTime(), 1, -1)));

        vehicleSubmittedData.build()
                .getTestTypes().get(0).setTestTypeId("1").setTestResult("prs");

        testResultsSteps.postTestResults(vehicleSubmittedData.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedData(Arrays.asList(DataUtil.buildDate(DataUtil.buildCurrentDateTime(), 1, -1), DataUtil.buildDate(DataUtil.buildCurrentDateTime(), 1, -1)));
    }


    @Title("CVSB-197 / CVSB-3875 - VSA submits results for a test type with test type classification 'Annual With Certificate' when the test type result is NOT PASSED or PRS ")
    @Test
    public void testResultsAbandonedWithTestTypeAnnualCertPrevRecWithExpiryDate() {

        vehicleSubmittedData.setVin(generateRandomExcludingValues(21, vehicleSubmittedData.build().getVin()))
                .setVrm(generateRandomExcludingValues(7, vehicleSubmittedData.build().getVrm())).build()
                .getTestTypes().get(0).setTestTypeId("1").setTestResult("pass");

        testResultsSteps.postTestResults(vehicleSubmittedData.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedData(Arrays.asList(DataUtil.buildDate(DataUtil.buildCurrentDateTime(), 1, -1)));

        vehicleSubmittedData.build()
                .getTestTypes().get(0).setTestTypeId("1").setTestResult("abandoned");

        testResultsSteps.postTestResults(vehicleSubmittedData.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedData(Arrays.asList(DataUtil.buildDate(DataUtil.buildCurrentDateTime(), 1, -1), null));
    }

    @Title("CVSB-197 / CVSB-3875 - VSA submits results for a test type with test type classification 'Annual With Certificate' when the test type result is NOT PASSED or PRS ")
    @Test
    public void testResultsFailWithTestTypeAnnualCertPrevRecWithExpiryDate() {

        vehicleSubmittedData.setVin(generateRandomExcludingValues(21, vehicleSubmittedData.build().getVin()))
                .setVrm(generateRandomExcludingValues(7, vehicleSubmittedData.build().getVrm())).build()
                .getTestTypes().get(0).setTestTypeId("1").setTestResult("pass");

        testResultsSteps.postTestResults(vehicleSubmittedData.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedData(Arrays.asList(DataUtil.buildDate(DataUtil.buildCurrentDateTime(), 1, -1)));

        vehicleSubmittedData.build()
                .getTestTypes().get(0).setTestTypeId("1").setTestResult("fail");

        testResultsSteps.postTestResults(vehicleSubmittedData.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedData(Arrays.asList(DataUtil.buildDate(DataUtil.buildCurrentDateTime(), 1, -1), null));
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
        validateSavedData(Arrays.asList(DataUtil.buildDate(DataUtil.buildCurrentDateTime(), 1, -1)));

        vehicleSubmittedData.build()
                .getTestTypes().get(0).setTestTypeId("15").setTestResult("pass");

        testResultsSteps.postTestResults(vehicleSubmittedData.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedData(Arrays.asList(DataUtil.buildDate(DataUtil.buildCurrentDateTime(), 1, -1), null));
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
        validateSavedData(Arrays.asList(DataUtil.buildDate(DataUtil.buildCurrentDateTime(), 1, -1)));

        vehicleSubmittedData.build()
                .getTestTypes().get(0).setTestTypeId("15").setTestResult("prs");

        testResultsSteps.postTestResults(vehicleSubmittedData.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedData(Arrays.asList(DataUtil.buildDate(DataUtil.buildCurrentDateTime(), 1, -1), null));
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

        testResultsSteps.postTestResults(vehicleSubmittedData.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedData(Arrays.asList(null,null));
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

        testResultsSteps.postTestResults(vehicleSubmittedData.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedData(Arrays.asList(null,null));
    }

    @Title("CVSB-197 / CVSB-3875 - VSA submits results for a test type with test type classification 'Annual With Certificate' when the test type result is NOT PASSED or PRS")
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

        testResultsSteps.postTestResults(vehicleSubmittedData.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedData(Arrays.asList(null,null));
    }

}
