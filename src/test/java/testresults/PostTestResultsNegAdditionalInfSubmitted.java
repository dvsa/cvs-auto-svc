package testresults;

import clients.util.ToTypeConvertor;
import clients.util.testresult.TestResultsLevel;
import data.TestResultsData;
import model.testresults.TestResults;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import steps.TestResultsSteps;


@RunWith(SerenityRunner.class)
public class PostTestResultsNegAdditionalInfSubmitted {

    @Steps
    TestResultsSteps testResultsSteps;

    private TestResults.Builder vehicleSubmittedData = TestResultsData.buildTestResultsSubmittedData();
    private static final String VRM = "SL72XD";


    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3505 - API Consumer tries to create a new test result for submitted/canceled with missing property - notes")
    @Test
    public void testResultsMissingNotes() {

        testResultsSteps.postTestResultsFieldChange(vehicleSubmittedData.setVrm(VRM).build(), "notes", ToTypeConvertor.MISSING, TestResultsLevel.ADDITIONAL_INFORMATION);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("notes", "is required");
    }


    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3508 API Consumer tries to create a new test result for submitted/canceled with different property type - tstas")
    @Test
    public void testResultsIntegerNotes() {

        testResultsSteps.postTestResultsFieldChange(vehicleSubmittedData.setVrm(VRM).build(), "notes", RandomStringUtils.randomNumeric(9), ToTypeConvertor.INTEGER, TestResultsLevel.ADDITIONAL_INFORMATION);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("notes", "must be a string");
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3507 - API Consumer tries to create a new test result for submitted/canceled with value/characters exceeding max length - notes")
    @Test
    public void testResultsLengthMaxNotes() {

        vehicleSubmittedData.setVrm(VRM).build().getTestTypes().get(0).getDefects().get(0).getAdditionalInformation().setNotes(RandomStringUtils.randomAlphanumeric(501));

        testResultsSteps.postTestResults(vehicleSubmittedData.build());
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("notes", "length must be less than or equal to 500 characters long");
    }


    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3505 - API Consumer tries to create a new test result for submitted/canceled with missing property - location")
    @Test
    public void testResultsMissingLocation() {

        testResultsSteps.postTestResultsFieldChange(vehicleSubmittedData.setVrm(VRM).build(), "location", ToTypeConvertor.MISSING, TestResultsLevel.ADDITIONAL_INFORMATION);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("location", "is required");
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3508 API Consumer tries to create a new test result for submitted/canceled with different property type - location")
    @Test
    public void testResultsIntegerLocation() {

        testResultsSteps.postTestResultsFieldChange(vehicleSubmittedData.setVrm(VRM).build(), "location", RandomStringUtils.randomNumeric(9), ToTypeConvertor.INTEGER, TestResultsLevel.ADDITIONAL_INFORMATION);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("location", "must be an object");
    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3506 - API Consumer tries to create a new test result for submitted/canceled with null value for not nullable - location with deficiencyCategory dangerous")
    @Test
    public void testResultsNullDangerousLocation() {

        vehicleSubmittedData.setVrm(VRM).build().getTestTypes().get(0).getDefects().get(0).setDeficiencyCategory("dangerous");


        testResultsSteps.postTestResultsFieldChange(vehicleSubmittedData.setVrm(VRM).build(), "location", ToTypeConvertor.NULL, TestResultsLevel.ADDITIONAL_INFORMATION);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validateData("/location are null for a defect with deficiency category other than advisory");

    }


    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3506 - API Consumer tries to create a new test result for submitted/canceled with null value for not nullable - location with deficiencyCategory major")
    @Test
    public void testResultsNullMajorLocation() {

        vehicleSubmittedData.setVrm(VRM).build().getTestTypes().get(0).getDefects().get(0).setDeficiencyCategory("major");


        testResultsSteps.postTestResultsFieldChange(vehicleSubmittedData.setVrm(VRM).build(), "location", ToTypeConvertor.NULL, TestResultsLevel.ADDITIONAL_INFORMATION);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validateData("/location are null for a defect with deficiency category other than advisory");

    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3506 - API Consumer tries to create a new test result for submitted/canceled with null value for not nullable - location with deficiencyCategory minor")
    @Test
    public void testResultsNullMinorLocation() {

        vehicleSubmittedData.setVrm(VRM).build().getTestTypes().get(0).getDefects().get(0).setDeficiencyCategory("minor");


        testResultsSteps.postTestResultsFieldChange(vehicleSubmittedData.setVrm(VRM).build(), "location", ToTypeConvertor.NULL, TestResultsLevel.ADDITIONAL_INFORMATION);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validateData("/location are null for a defect with deficiency category other than advisory");

    }

    @Title("CVSB-417 - CVSB-949 - CVSB-1140 / CVSB-3506 - API Consumer tries to create a new test result for submitted/canceled with null value for not nullable - location with deficiencyCategory prs")
    @Test
    public void testResultsNullPrsLocation() {

        vehicleSubmittedData.setVrm(VRM).build().getTestTypes().get(0).getDefects().get(0).setDeficiencyCategory("prs");


        testResultsSteps.postTestResultsFieldChange(vehicleSubmittedData.setVrm(VRM).build(), "location", ToTypeConvertor.NULL, TestResultsLevel.ADDITIONAL_INFORMATION);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validateData("/location are null for a defect with deficiency category other than advisory");

    }

}
