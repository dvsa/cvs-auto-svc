package testresults;

import data.GenericData;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import net.thucydides.core.annotations.WithTag;
import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import steps.TestResultsSteps;
import util.JsonPathAlteration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@RunWith(SerenityRunner.class)
public class InsertTestResultsInDynamo {

    @Steps
    TestResultsSteps testResultsSteps;

    @WithTag("In_Test")
    @Title("CVSB-11991 - Insert previous test-results into DynamoDB using Java AWS sdk")
    @Test
    public void testResultsSubmittedToDateExisting() {
        String randomVin = GenericData.generateRandomVin();
        String randomVrm = GenericData.generateRandomVrm();
        String randomTestResultId = String.valueOf(UUID.randomUUID());
        String json = GenericData.readJsonValueFromFile("test-results_roadworthiness_hgv_fail_7675.json", "$");
        // create alteration to change Vin in the post request body with the random generated Vin
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        // create alteration to change primary vrm in the request body with the random generated primary vrm
        JsonPathAlteration alterationVrm = new JsonPathAlteration("$.vrm", randomVrm, "", "REPLACE");
        // create alteration to change test result id in the request body with the random generated test result id
        JsonPathAlteration alterationTestResultId = new JsonPathAlteration("$.testResultId", randomTestResultId, "", "REPLACE");
        // initialize the alterations list with only the alterations for changing the Vin and the primary vrm
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(alterationVin, alterationVrm, alterationTestResultId));
        String alteredJson = GenericData.applyJsonAlterations(json, alterations);
        testResultsSteps.insertRecordInDynamo(alteredJson, "test-results");
    }
}