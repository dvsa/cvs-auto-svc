package trailerRegistration;

import data.GenericData;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Test;
import org.junit.runner.RunWith;
import steps.TrailerRegistrationSteps;
import util.JsonPathAlteration;
import java.util.*;

@RunWith(SerenityRunner.class)
public class PostTrailerRegistration {

    @Steps
    TrailerRegistrationSteps trailerRegistrationSteps;

    @Title("CVSB-18919 - AC1 - Saving 17 digit vin")
    @Test
    public void testTrailerRegistrationWithSeventeenDigitVin() {

        // Read the base test result JSON
        String trailerRegistrationRecord = GenericData.readJsonValueFromFile("trailer-registration_18919.json","$");

        // Create alteration to add one more tech record to in the request body
        String randomVin = GenericData.generateRandomVinForTrailerRegistration();
        String randomTrn = GenericData.generateRandomVinForTrailerRegistration();
        randomTrn = randomTrn.substring(0, randomTrn.length() - 9);
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin,"","REPLACE");
        JsonPathAlteration alterationTrn = new JsonPathAlteration("$.trn", randomTrn,"","REPLACE");

        // Collate the list of alterations
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(
                alterationVin,alterationTrn));

        // Post the results, together with any alterations, and verify that they are accepted.
        trailerRegistrationSteps.postTrailerRegistrationWithAlterations(trailerRegistrationRecord, alterations);
        trailerRegistrationSteps.statusCodeShouldBe(200);
        trailerRegistrationSteps.valueForFieldInPathShouldBe("vinOrChassisWithMake",randomVin);
    }

    @Title("CVSB-18919 - AC2 - Saving non-17 digit vin - Less than 17 digits ")
    @Test
    public void testTrailerRegistrationWithLessThanSeventeenDigitVin() {

        // Read the base test result JSON
        String trailerRegistrationRecord = GenericData.readJsonValueFromFile("trailer-registration_18919.json","$");

        // Create alteration to add one more tech record to in the request body
        String randomVin = GenericData.generateRandomVinForTrailerRegistration();
        randomVin = randomVin.substring(0, randomVin.length() - 2);

        String randomTrn = GenericData.generateRandomVinForTrailerRegistration();
        randomTrn = randomTrn.substring(0, randomTrn.length() - 9);
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin,"","REPLACE");
        JsonPathAlteration alterationTrn = new JsonPathAlteration("$.trn", randomTrn,"","REPLACE");

        // Collate the list of alterations
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(
                alterationVin,alterationTrn));

        // Post the results, together with any alterations, and verify that they are accepted.
        trailerRegistrationSteps.postTrailerRegistrationWithAlterations(trailerRegistrationRecord, alterations);
        trailerRegistrationSteps.statusCodeShouldBe(200);
        trailerRegistrationSteps.valueForFieldInPathShouldBe("vinOrChassisWithMake",randomVin +"Doepker Industries");
    }

    @Title("CVSB-18919 - AC2 - Saving non-17 digit vin - More than 17 digits")
    @Test
    public void testTrailerRegistrationWithMoreThanSeventeenDigitVin() {

        // Read the base test result JSON
        String trailerRegistrationRecord = GenericData.readJsonValueFromFile("trailer-registration_18919.json","$");

        String specialChars = "HGY";

        // Create alteration to add one more tech record to in the request body
        String randomVin = GenericData.generateRandomVinForTrailerRegistration() + specialChars ;
        String randomTrn = GenericData.generateRandomVinForTrailerRegistration();
        randomTrn = randomTrn.substring(0, randomTrn.length() - 9);
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin,"","REPLACE");
        JsonPathAlteration alterationTrn = new JsonPathAlteration("$.trn", randomTrn,"","REPLACE");


        // Collate the list of alterations
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(
                alterationVin,alterationTrn));

        // Post the results, together with any alterations, and verify that they are accepted.
        trailerRegistrationSteps.postTrailerRegistrationWithAlterations(trailerRegistrationRecord, alterations);
        trailerRegistrationSteps.statusCodeShouldBe(200);
        trailerRegistrationSteps.valueForFieldInPathShouldBe("vinOrChassisWithMake",randomVin +"Doepker Industries");
    }

    @Title("CVSB-18919 - AC3 - Update an Existing TRN during insert")
    @Test
    public void testTrailerRegistrationWithAnExistingVin() {


        // Read the base test result JSON
        String trailerRegistrationRecord = GenericData.readJsonValueFromFile("trailer-registration_18919.json","$");

        // Create alteration to add one more tech record to in the request body
        String randomVin = GenericData.generateRandomVinForTrailerRegistration();
        String randomTrn = GenericData.generateRandomVinForTrailerRegistration();
        randomTrn = randomTrn.substring(0, randomTrn.length() - 9);
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin,"","REPLACE");
        JsonPathAlteration alterationTrn = new JsonPathAlteration("$.trn", randomTrn,"","REPLACE");


        // Collate the list of alterations
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(
                alterationVin,alterationTrn));

        // Post the results, together with any alterations, and verify that they are accepted.
        trailerRegistrationSteps.postTrailerRegistrationWithAlterations(trailerRegistrationRecord, alterations);
        trailerRegistrationSteps.statusCodeShouldBe(200);
        trailerRegistrationSteps.valueForFieldInPathShouldBe("vinOrChassisWithMake",randomVin);


        // Create alteration to add one more tech record to in the request body
        JsonPathAlteration alterationUpdateVin = new JsonPathAlteration("$.vin", randomVin,"","REPLACE");

        // Collate the list of alterations
        List<JsonPathAlteration> UpdateAlterations = new ArrayList<>(Arrays.asList(
                alterationUpdateVin));

        // Post the results, together with any alterations, and verify that they are accepted.
        trailerRegistrationSteps.postTrailerRegistrationWithAlterations(trailerRegistrationRecord, UpdateAlterations);
        trailerRegistrationSteps.statusCodeShouldBe(200);
        trailerRegistrationSteps.valueForFieldInPathShouldBe("vinOrChassisWithMake",randomVin);
        trailerRegistrationSteps.valueForFieldInPathShouldBe("archive[0].reasonForDeregistration","New certificate received.");
        trailerRegistrationSteps.valueForFieldInPathShouldBe("archive[0].deregisterDate","2021-01-14T15:49:30.881Z");
    }

    @Title("CVSB-18919 - AC4 - Unregister when TRN is found")
    @Test
    public void testTrailerUnRegistrationForAnExistingVin() {


        // Read the base test result JSON
        String trailerRegistrationRecord = GenericData.readJsonValueFromFile("trailer-registration_18919.json","$");

        // Create alteration to add one more tech record to in the request body
        String randomVin = GenericData.generateRandomVinForTrailerRegistration();
        String randomTrn = GenericData.generateRandomVinForTrailerRegistration();
        randomTrn = randomTrn.substring(0, randomTrn.length() - 9);
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin,"","REPLACE");
        JsonPathAlteration alterationTrn = new JsonPathAlteration("$.trn", randomTrn,"","REPLACE");


        // Collate the list of alterations
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(
                alterationVin,alterationTrn));

        // Post the results, together with any alterations, and verify that they are accepted.
        trailerRegistrationSteps.postTrailerRegistrationWithAlterations(trailerRegistrationRecord, alterations);
        trailerRegistrationSteps.statusCodeShouldBe(200);
        trailerRegistrationSteps.valueForFieldInPathShouldBe("vinOrChassisWithMake",randomVin);


        // Read the base test result JSON
        String trailerDeRegistrationRecord = GenericData.readJsonValueFromFile("trailer-deregistration_18919.json","$");

        // Create alteration to add one more tech record to in the request body
        JsonPathAlteration alterationReasonForDeRegistration = new JsonPathAlteration("$.reasonForDeregistration", "For testing purpose only","","REPLACE");

        // Collate the list of alterations
        List<JsonPathAlteration> DeRegisterAlterations = new ArrayList<>(Arrays.asList(
                alterationReasonForDeRegistration));

        // Put the results, together with any alterations, and verify that they are accepted.
        trailerRegistrationSteps.putTrailerRegistrationWithAlterations(randomTrn,trailerDeRegistrationRecord,DeRegisterAlterations);
        trailerRegistrationSteps.statusCodeShouldBe(200);
        trailerRegistrationSteps.valueForFieldInPathShouldBe("vinOrChassisWithMake",randomVin);
        trailerRegistrationSteps.valueForFieldInPathShouldBe("vin",randomVin);
        trailerRegistrationSteps.valueForFieldInPathShouldBe("trn",randomTrn);
        trailerRegistrationSteps.valueForFieldInPathShouldBe("reasonForDeregistration","For testing purpose only");
        trailerRegistrationSteps.valueForFieldInPathShouldBe("deregisterDate","2021-06-11T15:10:01+0000");

    }

    @Title("CVSB-18919 - AC5 - Unregister when TRN is not found")
    @Test
    public void testTrailerUnRegistrationForInvalidVin() {

        // Read the base test result JSON
        String trailerDeRegistrationRecord = GenericData.readJsonValueFromFile("trailer-deregistration_18919.json","$");

        // Create alteration to add one more tech record to in the request body
        String randomTrn = GenericData.generateRandomVinForTrailerRegistration();
        randomTrn = randomTrn.substring(0, randomTrn.length() - 9);
        JsonPathAlteration alterationReasonForDeRegistration = new JsonPathAlteration("$.reasonForDeregistration", "For testing purpose only","","REPLACE");

        // Collate the list of alterations
        List<JsonPathAlteration> DeRegisterAlterations = new ArrayList<>(Arrays.asList(
                alterationReasonForDeRegistration));

        // Put the results, together with any alterations, and verify that an error response status code is retrieved
        trailerRegistrationSteps.putTrailerRegistrationWithAlterations(randomTrn,trailerDeRegistrationRecord,DeRegisterAlterations);
        trailerRegistrationSteps.statusCodeShouldBe(204);

    }

    @Title("CVSB-18919 - AC6 - Bad request on invalid payload - Invalid DeregisterDate")
    @Test
    public void testTrailerUnRegistrationForInvalidDeRegisterDate() {

        // Read the base test result JSON
        String trailerDeRegistrationRecord = GenericData.readJsonValueFromFile("trailer-deregistration_18919.json","$");

        // Create alteration to add one more tech record to in the request body
        String randomTrn = GenericData.generateRandomVinForTrailerRegistration();
        randomTrn = randomTrn.substring(0, randomTrn.length() - 9);
        JsonPathAlteration alterationDeRegisterDate = new JsonPathAlteration("$.deregisterDate", "2021-06-11T15:10:01:0000","","REPLACE");

        // Collate the list of alterations
        List<JsonPathAlteration> DeRegisterAlterations = new ArrayList<>(Arrays.asList(
                alterationDeRegisterDate));

        // Put the results, together with any alterations, and verify that an error response status code is retrieved
        trailerRegistrationSteps.putTrailerRegistrationWithAlterations(randomTrn,trailerDeRegistrationRecord,DeRegisterAlterations);
        trailerRegistrationSteps.statusCodeShouldBe(400);
    }

    @Title("CVSB-18919 - AC6 - Bad request on invalid payload - Boolean reasonForDeregistration")
    @Test
    public void testTrailerUnRegistrationForBooleanReasonForDeRegistration() {

        // Read the base test result JSON
        String trailerDeRegistrationRecord = GenericData.readJsonValueFromFile("trailer-deregistration_18919.json","$");

        // Create alteration to add one more tech record to in the request body
        String randomTrn = GenericData.generateRandomVinForTrailerRegistration();
        randomTrn = randomTrn.substring(0, randomTrn.length() - 9);
        JsonPathAlteration alterationReasonForDeRegistration = new JsonPathAlteration("$.reasonForDeregistration", true,"","REPLACE");

        // Collate the list of alterations
        List<JsonPathAlteration> DeRegisterAlterations = new ArrayList<>(Arrays.asList(
                alterationReasonForDeRegistration));

        // Put the results, together with any alterations, and verify that an error response status code is retrieved
        trailerRegistrationSteps.putTrailerRegistrationWithAlterations(randomTrn,trailerDeRegistrationRecord,DeRegisterAlterations);
        trailerRegistrationSteps.statusCodeShouldBe(400);
    }

    @Title("CVSB-18919 - AC6 - Bad request on invalid payload - Int reasonForDeregistration")
    @Test
    public void testTrailerUnRegistrationForIntReasonForDeRegistration() {

        // Read the base test result JSON
        String trailerDeRegistrationRecord = GenericData.readJsonValueFromFile("trailer-deregistration_18919.json","$");

        // Create alteration to add one more tech record to in the request body
        String randomTrn = GenericData.generateRandomVinForTrailerRegistration();
        randomTrn = randomTrn.substring(0, randomTrn.length() - 9);
        JsonPathAlteration alterationReasonForDeRegistration = new JsonPathAlteration("$.reasonForDeregistration", 123,"","REPLACE");

        // Collate the list of alterations
        List<JsonPathAlteration> DeRegisterAlterations = new ArrayList<>(Arrays.asList(
                alterationReasonForDeRegistration));

        // Put the results, together with any alterations, and verify that an error response status code is retrieved
        trailerRegistrationSteps.putTrailerRegistrationWithAlterations(randomTrn,trailerDeRegistrationRecord,DeRegisterAlterations);
        trailerRegistrationSteps.statusCodeShouldBe(400);
    }
}
