package testresults;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import data.GenericData;
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
import util.JsonPathAlteration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static util.DataUtil.generateRandomExcludingValues;


@RunWith(SerenityRunner.class)
public class GetTestResults {

    @Steps
    TestResultsSteps testResultsSteps;

    private TestResults.Builder vehicleDefaultSubmittedData = TestResultsData.buildTestResultsSubmittedData();

    private TestResultsGet vehicleSubmittedData = TestResultsData.buildTestResultsSubmittedDataWithCalculated().build();
    private TestResultsGet vehicleCancelledData = TestResultsData.buildTestResultsCancelleddDataWithCalculated().build();

    @Title("CVSB-416 - CVSB-949 / CVSB-3513 - Un-authorised consumer retrieves results for submitted tests.")
    @Test
    public void testResultsNoAuthorised() {
        testResultsSteps.getTestResultsNotAuthorised(vehicleSubmittedData.getVin());
        testResultsSteps.statusCodeShouldBe(403);
        testResultsSteps.validateMessage("User is not authorized to access this resource with an explicit deny");
    }

    @Title("CVSB-416 - CVSB-949 / CVSB-1578 - Un-authenticated consumer retrieves results for submitted tests.")
    @Test
    public void testResultsNoAuth() {
        testResultsSteps.getTestResultsNotAuthenticated(vehicleSubmittedData.getVin());
        testResultsSteps.statusCodeShouldBe(401);
        testResultsSteps.validateMessage("Unauthorized");
    }

}
