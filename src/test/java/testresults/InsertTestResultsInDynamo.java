package testresults;

import data.GenericData;
import exceptions.AutomationException;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import net.thucydides.core.annotations.WithTag;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import steps.TestResultsSteps;
import steps.TestStationSteps;
import steps.VehicleTechnicalRecordsSteps;
import util.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@RunWith(SerenityRunner.class)
public class InsertTestResultsInDynamo {

    @Steps
    TestResultsSteps testResultsSteps;

    @Steps
    TestStationSteps testStationSteps;

    @Steps
    VehicleTechnicalRecordsSteps vehicleTechnicalRecordsSteps;

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
        testResultsSteps.insertRecordInDynamo(alteredJson, "test-results", "vin");
    }

    @Ignore("This is just an example of how to read json data from external file and insert it in a specific table using AWS SDK")
    @Title("CVSB-12181 - Delete entries fro activities table in DynamoDB using Java AWS sdk")
    public void testDeleteActivities() {
        testResultsSteps.deleteActivitiesForUser("a53ae740-eac4-4eb6-99ef-42afb63ce819");
    }

    @Ignore("This is just an example of how to update test station email address insert it in a specific table using AWS SDK")
    @Title("CVSB-10530 - Update entry from test-stations table in DynamoDB using Java AWS sdk")
    public void updateEmailTestStation() {
        String emailAddress = testResultsSteps.getOutlookEmailAddress();
        testStationSteps.updateEmailsForTestStation("20", emailAddress);
    }

    @Ignore("This is just an example of how to get the next systemNumber to be generated from the backend")
    @Title("CVSB-10598 - Get next systemNumber in sequence")
    public void getNextSystemNumberInSequence() {
        String systemNumber  = vehicleTechnicalRecordsSteps.getNextSystemNumberInSequence();
        System.out.println("Next systemNumber will be " + systemNumber);
    }

    @Ignore("This is just an example of how to get the next trailerId to be generated from the backend")
    @Title("CVSB-10131 - Get next trailerId in sequence")
    public void getNextTrailerIdInSequence() {
        String trailerId  = vehicleTechnicalRecordsSteps.getNextTrailerIdInSequence();
        System.out.println("Next trailerId will be " + trailerId);
    }
}
