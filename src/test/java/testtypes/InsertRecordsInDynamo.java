package testtypes;

import clients.model.*;
import data.GenericData;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Test;
import org.junit.runner.RunWith;
import steps.TestResultsSteps;

import java.util.Arrays;

@RunWith(SerenityRunner.class)
public class InsertRecordsInDynamo {

    @Steps
    TestResultsSteps testResultsSteps;

    @Title("CVSB-11991 - Insert previous test-results into DynamoDB using Java AWS sdk")
    @Test
    public void insertTestResultsInDynamo() {
        // TEST SETUP
        //generate random Vin
        String randomVin = GenericData.generateRandomVin();
        //generate random Vrm
        String randomVrm = GenericData.generateRandomVrm();
        // read post request body from file
        String json = GenericData.readJsonValueFromFile("test-results_ADR_HGV.json","$");
        testResultsSteps.insertJsonInDynamo("test-results", json);
        testResultsSteps.getTestResults(randomVin);
        testResultsSteps.statusCodeShouldBe(200);
        testResultsSteps.valueForFieldInPathShouldBe("vin", "1B7GG36N12S123456");
    }


}
