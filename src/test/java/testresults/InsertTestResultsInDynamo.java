package testresults;

import data.GenericData;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Ignore;
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

    @Ignore("This is just an example of how to read json data from external file and insert it in a specific table using AWS SDK")
    @Title("CVSB-11991 - Insert previous test-results into DynamoDB using Java AWS sdk")
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

    @Ignore("This is just an example of how to read json data from external file and insert it in a specific table using AWS SDK")
    @Title("CVSB-12181 - Delete entries fro activities table in DynamoDB using Java AWS sdk")
    public void testDeleteActivities() {
        testResultsSteps.deleteActivitiesForUser("a53ae740-eac4-4eb6-99ef-42afb63ce819");
    }
}
