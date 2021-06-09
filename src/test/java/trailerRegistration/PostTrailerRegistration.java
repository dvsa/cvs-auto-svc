package trailerRegistration;

import data.GenericData;
import data.TestResultsData;
import model.testresults.TestResults;
import model.testresults.TestResultsGet;
import model.testresults.TestResultsStatus;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import steps.TestResultsSteps;
import steps.TrailerRegistrationSteps;
import util.JsonPathAlteration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@RunWith(SerenityRunner.class)
public class PostTrailerRegistration {

    @Steps
    TrailerRegistrationSteps trailerRegistrationSteps;


    @Title("CVSB-18919 - AC1 - Saving 17 digit vin")
    @Test
    public void testResultsAPIConsumerCreatesANewTestResultLEC_PSV_Pass() {

        // Read the base test result JSON.
        String trailerRegistrationRecord = GenericData.readJsonValueFromFile("trailer-registration_18919.json","$");

        // Create alteration to add one more tech record to in the request body
        String randomVin = GenericData.generateRandomVin();
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin,"","REPLACE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(
                alterationVin));

        // Post the results, together with any alterations, and verify that they are accepted.
        trailerRegistrationSteps.postTrailerRegistrationWithAlterations(trailerRegistrationRecord, alterations);
        trailerRegistrationSteps.statusCodeShouldBe(200);
        trailerRegistrationSteps.valueForFieldInPathShouldContain("vinOrChassisWithMake",randomVin +"Doepker Industries");
    }
}
