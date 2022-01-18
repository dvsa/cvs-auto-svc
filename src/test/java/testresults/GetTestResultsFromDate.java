package testresults;

import data.GenericData;
import data.TestResultsData;
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
import util.JsonPathAlteration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;


@RunWith(SerenityRunner.class)
public class GetTestResultsFromDate {
    @Steps
    TestResultsSteps testResultsSteps;

    private TestResultsGet vehicleSubmittedData = TestResultsData.buildTestResultsSubmittedDataWithCalculated().build();
    private TestResultsGet vehicleCancelledData = TestResultsData.buildTestResultsCancelleddDataWithCalculated().build();

    @Title("CVSB-416 - CVSB-949 / CVSB-2447 - From Date data not found and status default")
    @Test
    public void testResultsSubmittedFromDateNotExisting() {

        testResultsSteps.getTestResultsFromDate(vehicleSubmittedData.getVin(), DataUtil.buildDate(vehicleSubmittedData.getTestTypes().get(0).getCreatedAt(), 1));
        testResultsSteps.statusCodeShouldBe(404);
        testResultsSteps.validateData("No resources match the search criteria");
    }

    @Title("CVSB-416 - CVSB-949 / CVSB-2448 - From Date data invalid and status default")
    @Test
    public void testResultsSubmittedFromDateInvalid() {

        testResultsSteps.getTestResultsFromDate(vehicleSubmittedData.getVin(), RandomStringUtils.randomAlphanumeric(4));
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validateData("Bad request");
    }

    @Title("CVSB-416 - CVSB-949 / CVSB-2448 - From Date data invalid and status default")
    @Test
    public void testResultsSubmittedFromDateInvalidEmpty() {

        testResultsSteps.getTestResultsFromDate(vehicleSubmittedData.getVin(), "");
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validateData("Bad request");
    }

    @Title("CVSB-416 - CVSB-949 / CVSB-2449 - From Date data found and status submitted")
    @Test
    public void testResultsSubmittedFromDateExistingWithStatusSubmitted() {

        String testResultRecord = GenericData.readJsonValueFromFile("test-results_roadworthiness_hgv_pass_7675.json", "$");

        // Create alteration to add one more tech record to in the request body
        String randomSystemNumber = GenericData.generateRandomSystemNumber();
        String randomVin = GenericData.generateRandomVin();
        String randomTestResultId = UUID.randomUUID().toString();
        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$.systemNumber", randomSystemNumber, "", "REPLACE");
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationTestResultId = new JsonPathAlteration("$.testResultId", randomTestResultId, "", "REPLACE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(
                alterationVin,
                alterationSystemNumber,
                alterationTestResultId));

        // Post the results, together with any alterations, and verify that they are accepted.
        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, alterations);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");

        testResultsSteps.getTestResultsFromDate(randomSystemNumber, DataUtil.buildDate(vehicleSubmittedData.getTestTypes().get(0).getCreatedAt(), -1), TestResultsStatus.SUBMITTED);
        testResultsSteps.statusCodeShouldBe(200);

        testResultsSteps.valueForFieldInPathShouldBe("testTypes.size()", 1);
        testResultsSteps.valueForFieldInPathShouldBe("[0].vin",randomVin);
        testResultsSteps.valueForFieldInPathShouldBe("[0].systemNumber", randomSystemNumber);
    }

    @Title("CVSB-416 - CVSB-949 / CVSB-2450 - From Date data not found and status submitted")
    @Test
    public void testResultsSubmittedFromDateNotExistingWithStatusSubmitted() {

        testResultsSteps.getTestResultsFromDate(vehicleSubmittedData.getVin(), DataUtil.buildDate(vehicleSubmittedData.getTestTypes().get(0).getCreatedAt(), 1), TestResultsStatus.SUBMITTED);
        testResultsSteps.statusCodeShouldBe(404);
        testResultsSteps.validateData("No resources match the search criteria");
    }

    @Title("CVSB-416 - CVSB-949 / CVSB-2451 - From Date data invalid and status submitted - random")
    @Test
    public void testResultsSubmittedFromDateInvalidWithStatusSubmitted() {

        testResultsSteps.getTestResultsFromDate(vehicleSubmittedData.getVin(), RandomStringUtils.randomAlphanumeric(4), TestResultsStatus.SUBMITTED);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validateData("Bad request");
    }

    @Title("CVSB-416 - CVSB-949 / CVSB-2451 - From Date data invalid and status submitted - empty")
    @Test
    public void testResultsSubmittedFromDateInvalidEmptyWithStatusSubmitted() {

        testResultsSteps.getTestResultsFromDate(vehicleSubmittedData.getVin(), "", TestResultsStatus.SUBMITTED);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validateData("Bad request");
    }

    @WithTag("In_test")
    @Title("CVSB-416 - CVSB-949 / CVSB-2452 - From Date data found and status canceled")
    @Test
    public void testResultsSubmittedFromDateExistingWithStatusCancelled() {

        testResultsSteps.getTestResultsFromDate(vehicleCancelledData.getSystemNumber(), DataUtil.buildDate(vehicleCancelledData.getTestTypes().get(0).getCreatedAt(), -1), TestResultsStatus.CANCELED);
        testResultsSteps.statusCodeShouldBe(200);
        testResultsSteps.validateData(vehicleCancelledData);
    }

    @Title("CVSB-416 - CVSB-949 / CVSB-2453 - From Date data not found and status canceled")
    @Test
    public void testResultsSubmittedFromDateNotExistingWithStatusCancelled() {

        testResultsSteps.getTestResultsFromDate(vehicleCancelledData.getVin(), DataUtil.buildDate(vehicleCancelledData.getTestTypes().get(0).getCreatedAt(), 1), TestResultsStatus.CANCELED);
        testResultsSteps.statusCodeShouldBe(404);
        testResultsSteps.validateData("No resources match the search criteria");
    }

    @Title("CVSB-416 - CVSB-949 / CVSB-2454 - From Date data invalid and status canceled - random")
    @Test
    public void testResultsSubmittedFromDateInvalidWithStatusCancelled() {

        testResultsSteps.getTestResultsFromDate(vehicleCancelledData.getVin(), RandomStringUtils.randomAlphanumeric(4), TestResultsStatus.CANCELED);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validateData("Bad request");
    }

    @Title("CVSB-416 - CVSB-949 / CVSB-2454 - From Date data invalid and status canceled - empty")
    @Test
    public void testResultsSubmittedFromDateInvalidEmptyWithStatusCancelled() {

        testResultsSteps.getTestResultsFromDate(vehicleCancelledData.getVin(), "", TestResultsStatus.CANCELED);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validateData("Bad request");
    }

}
