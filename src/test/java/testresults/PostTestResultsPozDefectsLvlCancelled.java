package testresults;

import data.TestResultsData;
import model.testresults.TestResults;
import model.testresults.TestResultsGet;
import model.testresults.TestResultsStatus;
import model.testresults.TestTypes;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.core.annotations.WithTags;
import org.apache.commons.lang3.RandomStringUtils;
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
public class PostTestResultsPozDefectsLvlCancelled {

    @Steps
    TestResultsSteps testResultsSteps;

    private TestResults.Builder vehicleCancelledData = TestResultsData.buildTestResultsCancelledData();

    private void validateSavedData() {

        testResultsSteps.getTestResults(vehicleCancelledData.build().getVin(), TestResultsStatus.CANCELED);
        testResultsSteps.statusCodeShouldBe(200);
        testResultsSteps.validateData((TestResultsGet) vehicleCancelledData.build());
    }


    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - imNumber")
    @Test
    public void testResultsRandomImNumber() {

        ((TestTypes) vehicleCancelledData.getTestTypes().get(0)).getDefects().get(0).setImNumber(Integer.valueOf(RandomStringUtils.randomNumeric(5)));

        testResultsSteps.postTestResults(vehicleCancelledData.setVin(generateRandomExcludingValues(21, vehicleCancelledData.build().getVin()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledData.build().getVrm())).build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedData();
    }


    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - imDescription")
    @Test
    public void testResultsRandomImDescription() {

        ((TestTypes) vehicleCancelledData.getTestTypes().get(0)).getDefects().get(0).setImDescription(RandomStringUtils.randomAlphanumeric(14));

        testResultsSteps.postTestResults(vehicleCancelledData.setVin(generateRandomExcludingValues(21, vehicleCancelledData.build().getVin()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledData.build().getVrm())).build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedData();
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3486 - API Consumer creates a new test results for submitted/canceled with no min restriction - imDescription")
    @Test
    public void testResultsEmptyImDescription() {

        ((TestTypes) vehicleCancelledData.getTestTypes().get(0)).getDefects().get(0).setImDescription("");

        testResultsSteps.postTestResults(vehicleCancelledData.setVin(generateRandomExcludingValues(21, vehicleCancelledData.build().getVin()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledData.build().getVrm())).build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedData();
    }


    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - itemDescription")
    @Test
    public void testResultsRandomItemDescription() {

        ((TestTypes) vehicleCancelledData.getTestTypes().get(0)).getDefects().get(0).setItemDescription(RandomStringUtils.randomAlphanumeric(14));

        testResultsSteps.postTestResults(vehicleCancelledData.setVin(generateRandomExcludingValues(21, vehicleCancelledData.build().getVin()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledData.build().getVrm())).build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedData();
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3486 - API Consumer creates a new test results for submitted/canceled with no min restriction - itemDescription")
    @Test
    public void testResultsEmptyItemDescription() {

        ((TestTypes) vehicleCancelledData.getTestTypes().get(0)).getDefects().get(0).setItemDescription("");

        testResultsSteps.postTestResults(vehicleCancelledData.setVin(generateRandomExcludingValues(21, vehicleCancelledData.build().getVin()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledData.build().getVrm())).build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedData();
    }


    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - deficiencyRef")
    @Test
    public void testResultsRandomDeficiencyRef() {

        ((TestTypes) vehicleCancelledData.getTestTypes().get(0)).getDefects().get(0).setDeficiencyRef(RandomStringUtils.randomAlphanumeric(14));

        testResultsSteps.postTestResults(vehicleCancelledData.setVin(generateRandomExcludingValues(21, vehicleCancelledData.build().getVin()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledData.build().getVrm())).build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedData();
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3486 - API Consumer creates a new test results for submitted/canceled with no min restriction - deficiencyRef")
    @Test
    public void testResultsEmptyDeficiencyRef() {

        ((TestTypes) vehicleCancelledData.getTestTypes().get(0)).getDefects().get(0).setDeficiencyRef("");

        testResultsSteps.postTestResults(vehicleCancelledData.setVin(generateRandomExcludingValues(21, vehicleCancelledData.build().getVin()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledData.build().getVrm())).build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedData();
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - deficiencyId")
    @Test
    public void testResultsRandomAlphabeticLowerCaseStringDeficiencyId() {

        String propertyValue = RandomStringUtils.randomAlphabetic(1).toLowerCase();
        vehicleCancelledData.build().getTestTypes().get(0).getDefects().get(0).setDeficiencyId(propertyValue);

        testResultsSteps.postTestResults(vehicleCancelledData.setVin(generateRandomExcludingValues(21, vehicleCancelledData.build().getVin()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledData.build().getVrm())).build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedData();
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3504 - TCD - API Consumer creates a new test result for submitted/canceled that allows null values - deficiencyId")
    @Test
    public void testResultsNullDeficiencyId() {

        vehicleCancelledData.build().getTestTypes().get(0).getDefects().get(0).setDeficiencyId(null);

        testResultsSteps.postTestResults(vehicleCancelledData.setVin(generateRandomExcludingValues(21, vehicleCancelledData.build().getVin()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledData.build().getVrm())).build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedData();
    }


    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3504 - TCD - API Consumer creates a new test result for submitted/canceled that allows null values - deficiencySubId")
    @Test
    public void testResultsMissingDeficiencySubId() {

        ((TestTypes) vehicleCancelledData.getTestTypes().get(0)).getDefects().get(0).setDeficiencySubId(null);


        testResultsSteps.postTestResults(vehicleCancelledData.setVin(generateRandomExcludingValues(21, vehicleCancelledData.build().getVin()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledData.build().getVrm())).build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedData();
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - deficiencySubId m")
    @Test
    public void testResultsValidDeficiencySubIdValueOne() {

        ((TestTypes) vehicleCancelledData.getTestTypes().get(0)).getDefects().get(0).setDeficiencySubId("m");

        testResultsSteps.postTestResults(vehicleCancelledData.setVin(generateRandomExcludingValues(21, vehicleCancelledData.build().getVin()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledData.build().getVrm())).build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedData();
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - deficiencySubId d")
    @Test
    public void testResultsValidDeficiencySubIdValueTwo() {

        ((TestTypes) vehicleCancelledData.getTestTypes().get(0)).getDefects().get(0).setDeficiencySubId("d");

        testResultsSteps.postTestResults(vehicleCancelledData.setVin(generateRandomExcludingValues(21, vehicleCancelledData.build().getVin()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledData.build().getVrm())).build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedData();
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - deficiencySubId c")
    @Test
    public void testResultsValidDeficiencySubIdValueThree() {

        ((TestTypes) vehicleCancelledData.getTestTypes().get(0)).getDefects().get(0).setDeficiencySubId("c");

        testResultsSteps.postTestResults(vehicleCancelledData.setVin(generateRandomExcludingValues(21, vehicleCancelledData.build().getVin()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledData.build().getVrm())).build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedData();
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - deficiencySubId l")
    @Test
    public void testResultsValidDeficiencySubIdValueFour() {

        ((TestTypes) vehicleCancelledData.getTestTypes().get(0)).getDefects().get(0).setDeficiencySubId("l");

        testResultsSteps.postTestResults(vehicleCancelledData.setVin(generateRandomExcludingValues(21, vehicleCancelledData.build().getVin()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledData.build().getVrm())).build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedData();
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - deficiencySubId x")
    @Test
    public void testResultsValidDeficiencySubIdValueFive() {

        ((TestTypes) vehicleCancelledData.getTestTypes().get(0)).getDefects().get(0).setDeficiencySubId("x");

        testResultsSteps.postTestResults(vehicleCancelledData.setVin(generateRandomExcludingValues(21, vehicleCancelledData.build().getVin()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledData.build().getVrm())).build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedData();
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - deficiencySubId v")
    @Test
    public void testResultsValidDeficiencySubIdValueSix() {

        ((TestTypes) vehicleCancelledData.getTestTypes().get(0)).getDefects().get(0).setDeficiencySubId("v");

        testResultsSteps.postTestResults(vehicleCancelledData.setVin(generateRandomExcludingValues(21, vehicleCancelledData.build().getVin()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledData.build().getVrm())).build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedData();
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - deficiencySubId i")
    @Test
    public void testResultsValidDeficiencySubIdValueSeven() {

        ((TestTypes) vehicleCancelledData.getTestTypes().get(0)).getDefects().get(0).setDeficiencySubId("i");

        testResultsSteps.postTestResults(vehicleCancelledData.setVin(generateRandomExcludingValues(21, vehicleCancelledData.build().getVin()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledData.build().getVrm())).build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedData();
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - deficiencyCategory advisory")
    @Test
    public void testResultsDeficiencyCategoryValueOne() {

        ((TestTypes) vehicleCancelledData.getTestTypes().get(0)).getDefects().get(0).setDeficiencyCategory("advisory");

        testResultsSteps.postTestResults(vehicleCancelledData.setVin(generateRandomExcludingValues(21, vehicleCancelledData.build().getVin()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledData.build().getVrm())).build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedData();
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - deficiencyCategory dangerous")
    @Test
    public void testResultsDeficiencyCategoryValueTwo() {

        ((TestTypes) vehicleCancelledData.getTestTypes().get(0)).getDefects().get(0).setDeficiencyCategory("dangerous");

        testResultsSteps.postTestResults(vehicleCancelledData.setVin(generateRandomExcludingValues(21, vehicleCancelledData.build().getVin()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledData.build().getVrm())).build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedData();
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - deficiencyCategory major")
    @Test
    public void testResultsDeficiencyCategoryValueThree() {

        ((TestTypes) vehicleCancelledData.getTestTypes().get(0)).getDefects().get(0).setDeficiencyCategory("major");

        testResultsSteps.postTestResults(vehicleCancelledData.setVin(generateRandomExcludingValues(21, vehicleCancelledData.build().getVin()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledData.build().getVrm())).build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedData();
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - deficiencyCategory minor")
    @Test
    public void testResultsValueDeficiencyCategoryFour() {

        ((TestTypes) vehicleCancelledData.getTestTypes().get(0)).getDefects().get(0).setDeficiencyCategory("minor");

        testResultsSteps.postTestResults(vehicleCancelledData.setVin(generateRandomExcludingValues(21, vehicleCancelledData.build().getVin()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledData.build().getVrm())).build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedData();
    }


    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3504 - TCD - API Consumer creates a new test result for submitted/canceled that allows null values - deficiencyText")
    @Test
    public void testResultsNullAdvisoryDeficiencyText() {

        vehicleCancelledData.setVin(generateRandomExcludingValues(21, vehicleCancelledData.build().getVin()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledData.build().getVrm())).build().getTestTypes().get(0).getDefects().get(0).setDeficiencyCategory("advisory").setDeficiencyText(null);

        testResultsSteps.postTestResults(vehicleCancelledData.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedData();

    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3486 - API Consumer creates a new test results for submitted/canceled with no min restriction - deficiencyText")
    @Test
    public void testResultsEmptyDeficiencyText() {

        vehicleCancelledData.setVin(generateRandomExcludingValues(21, vehicleCancelledData.build().getVin()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledData.build().getVrm())).build()
                .getTestTypes().get(0).getDefects().get(0).setDeficiencyText("");

        testResultsSteps.postTestResults(vehicleCancelledData.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedData();

    }


    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - stdForProhibition false")
    @Test
    public void testResultsStdForProhibitionFalse() {

        vehicleCancelledData.setVin(generateRandomExcludingValues(21, vehicleCancelledData.build().getVin()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledData.build().getVrm())).build()
                .getTestTypes().get(0).getDefects().get(0).setStdForProhibition(false);

        testResultsSteps.postTestResults(vehicleCancelledData.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedData();

    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - stdForProhibition true")
    @Test
    public void testResultsStdForProhibitionTrue() {

        vehicleCancelledData.setVin(generateRandomExcludingValues(21, vehicleCancelledData.build().getVin()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledData.build().getVrm())).build()
                .getTestTypes().get(0).getDefects().get(0).setStdForProhibition(true);

        testResultsSteps.postTestResults(vehicleCancelledData.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedData();

    }


    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - prs false")
    @Test
    public void testResultsPrsFalse() {

        vehicleCancelledData.setVin(generateRandomExcludingValues(21, vehicleCancelledData.build().getVin()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledData.build().getVrm())).build()
                .getTestTypes().get(0).getDefects().get(0).setPrs(false);

        testResultsSteps.postTestResults(vehicleCancelledData.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedData();

    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-1573 - Consumer creates a new test results for the submitted/cancelled test - prs true")
    @Test
    public void testResultsPrsTrue() {

        vehicleCancelledData.setVin(generateRandomExcludingValues(21, vehicleCancelledData.build().getVin()))
                .setVrm(generateRandomExcludingValues(7, vehicleCancelledData.build().getVrm())).build()
                .getTestTypes().get(0).getDefects().get(0).setPrs(true);

        testResultsSteps.postTestResults(vehicleCancelledData.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedData();

    }


}