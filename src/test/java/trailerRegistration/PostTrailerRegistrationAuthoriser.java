package trailerRegistration;

import data.GenericData;
import model.vehicles.VehicleTechnicalRecordSearchCriteria;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.apache.http.HttpStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import steps.TrailerRegistrationSteps;
import steps.VehicleTechnicalRecordsSteps;
import util.JsonPathAlteration;
import java.util.*;

@RunWith(SerenityRunner.class)
public class PostTrailerRegistrationAuthoriser {

    @Steps
    TrailerRegistrationSteps trailerRegistrationSteps;

    @Steps
    VehicleTechnicalRecordsSteps vehicleTechnicalRecordsSteps;

    @Title("CVSB-18927 - AC1. Deny clients without a token from accessing the new endpoint - Trailer Registration")
    @Test
    public void testTrailerRegistrationWithNoAuthorization() {

        // Read the base test result JSON
        String trailerRegistrationRecord = GenericData.readJsonValueFromFile("trailer-registration_18927.json","$");

        // Post the results, together with any alterations, and verify that they are accepted.
        trailerRegistrationSteps.postTrailerRegistrationWithNoAuthorization(trailerRegistrationRecord);
        trailerRegistrationSteps.statusCodeShouldBe(401);
        trailerRegistrationSteps.validateMessage("Unauthorized");
    }

    @Title("CVSB-18927 - AC1. Deny clients without a token from accessing the new endpoint - VTA EndPoint")
    @Test
    public void testGetTechnicalRecordsWithoutAuthorization() {

        // POST tech-record,read the base tech-record JSON.
        String postRequestBody = GenericData.readJsonValueFromFile("technical-records_hgv_all_fields.json", "$");

        // Create alteration to edit one or more fields in the request body
        String randomVin = GenericData.generateRandomVin();
        JsonPathAlteration alterationVinVehicle = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterationsVehicle = new ArrayList<>(Arrays.asList(alterationVinVehicle));

        // Post the tech-record, together with any alterations, and verify that they are accepted.
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBody, alterationsVehicle);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(HttpStatus.SC_CREATED);
        vehicleTechnicalRecordsSteps.validateData("Technical Record created");

        vehicleTechnicalRecordsSteps.getTechnicalRecordsWithNoAuthorization(randomVin, VehicleTechnicalRecordSearchCriteria.VIN);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(401);
        vehicleTechnicalRecordsSteps.validateMessage("Unauthorized");
    }

    @Title("CVSB-18927 - AC2. Allow VSAs accessing the new endpoint")
    @Test
    public void testTrailerRegistrationWithVTAToken() {

        // Read the base test result JSON
        String trailerRegistrationRecord = GenericData.readJsonValueFromFile("trailer-registration_18927.json","$");

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
    }

    @Title("CVSB-18927 - AC3. Permit DVLA accessing the new endpoint")
    @Test
    public void testTrailerRegistrationWithDVLAToken() {

        // Read the base test result JSON
        String trailerRegistrationRecord = GenericData.readJsonValueFromFile("trailer-registration_18927.json","$");

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
        trailerRegistrationSteps.postTrailerRegistrationWithAlterationsDVLA(trailerRegistrationRecord, alterations);
        trailerRegistrationSteps.statusCodeShouldBe(200);
    }

    @Title("CVSB-18927 - AC4. Deny DVLA accessing existing endpoints")
    @Test
    public void testGetTechnicalRecordsWithDVLAToken() {

        // POST tech-record,read the base tech-record JSON.
        String postRequestBody = GenericData.readJsonValueFromFile("technical-records_hgv_all_fields.json", "$");

        // Create alteration to edit one or more fields in the request body
        String randomVin = GenericData.generateRandomVin();
        JsonPathAlteration alterationVinVehicle = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterationsVehicle = new ArrayList<>(Arrays.asList(alterationVinVehicle));

        // Post the tech-record, together with any alterations, and verify that they are accepted.
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBody, alterationsVehicle);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(HttpStatus.SC_CREATED);
        vehicleTechnicalRecordsSteps.validateData("Technical Record created");

        vehicleTechnicalRecordsSteps.getTechnicalRecordsWithNoAuthorizationDVLAToken(randomVin, VehicleTechnicalRecordSearchCriteria.VIN);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(403);
        vehicleTechnicalRecordsSteps.validateMessage("User is not authorized to access this resource");
    }

    @Title("CVSB-19442 - AC4. Deny DVLA accessing VTA endpoint")
    @Test

    public void testGetTechnicalRecordWithDVLAToken(){
        // POST tech-record,read the base tech-record JSON.
        String postRequestBody = GenericData.readJsonValueFromFile("technical-records_hgv_all_fields.json", "$");

        // Create alteration to edit one or more fields in the request body
        String randomVin = GenericData.generateRandomVin();
        JsonPathAlteration alterationVinVehicle = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterationsVehicle = new ArrayList<>(Arrays.asList(alterationVinVehicle));

        // Post the tech-record, together with any alterations, and verify that they are accepted.
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBody, alterationsVehicle);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(HttpStatus.SC_CREATED);
        vehicleTechnicalRecordsSteps.validateData("Technical Record created");

        //get systemNumber from post tech-record to use in put request
        String systemNumber = vehicleTechnicalRecordsSteps.getSystemNumberUsingVin(randomVin);

        vehicleTechnicalRecordsSteps.putTechnicalRecordsWithNoAuthorizationDVLAToken(systemNumber, postRequestBody);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(403);
        vehicleTechnicalRecordsSteps.validateMessage("User is not authorized to access this resource");
    }

}
