package vehicle;


import data.GenericData;
import model.vehicles.VehicleTechnicalRecordStatus;
import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.junit.annotations.TestData;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import steps.VehicleTechnicalRecordsSteps;
import util.JsonPathAlteration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@RunWith(SerenityParameterizedRunner.class)
public class PostGetPutCapitaliseVehicleTechnicalRecords {

    private String vin;
    private String vrm1;
    private String vrm2;
    private String trailerId;


    @Steps
    VehicleTechnicalRecordsSteps vehicleTechnicalRecordsSteps;

    @TestData
    public static Collection<Object[]> testData(){
        return Arrays.asList(new Object[][]{
                {RandomStringUtils.randomAlphanumeric(9), RandomStringUtils.randomAlphanumeric(7), RandomStringUtils.randomAlphanumeric(7), RandomStringUtils.randomAlphanumeric(7)},
                {RandomStringUtils.randomAlphanumeric(9).toLowerCase(), RandomStringUtils.randomAlphanumeric(7).toLowerCase(), RandomStringUtils.randomAlphanumeric(7).toLowerCase(), RandomStringUtils.randomAlphanumeric(7).toLowerCase()},
                {RandomStringUtils.randomAlphanumeric(9).toUpperCase(), RandomStringUtils.randomAlphanumeric(7).toUpperCase(), RandomStringUtils.randomAlphanumeric(7).toUpperCase(), RandomStringUtils.randomAlphanumeric(7).toUpperCase()},
        });
    }

    public PostGetPutCapitaliseVehicleTechnicalRecords(String vin , String vrm1, String vrm2, String trailerId) {
        this.vin = vin;
        this.vrm1 = vrm1;
        this.vrm2 = vrm2;
        this.trailerId = trailerId;
    }

    @WithTag("Vtm")
    @Title("CVSB-10185 - AC1 - [BE] Vehicles Backend Service - GET/POST/PUT upper case conversion")
    @Test
    public void testCreateUpdateRetrieveVehicleTechnicalRecordUppercase() {

        // read post request body from file
        String postRequestBody = GenericData.readJsonValueFromFile("technical-records_trl_all_fields.json","$");
        // create alteration to change Vin in the request body with the random generated Vin
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", vin,"","REPLACE");
        // create alteration to change primary vrm in the request body with the random generated vrm
        JsonPathAlteration alterationVrm = new JsonPathAlteration("$.primaryVrm", vrm1,"","REPLACE");
        // initialize the alterations list with both declared alteration
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(
                alterationVin,
                alterationVrm
        ));

        // TEST
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBody, alterations);
        // validate AC1
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(201);
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsByStatus(vin, VehicleTechnicalRecordStatus.ALL);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].vin", vin.toUpperCase());
        vehicleTechnicalRecordsSteps.validateResponseContainsJson("[0].vrms[0]", "{\"vrm\": \"" + vrm1.toUpperCase() + "\", \"isPrimary\": true}");

        // read put request body from file
        String putRequestBody = GenericData.readJsonValueFromFile("technical-records_trl_all_fields_put.json","$");
        // create alteration to change Vin in the request body with the random generated Vin
        JsonPathAlteration alterationVinPut = new JsonPathAlteration("$.vin", vin,"","REPLACE");
        // create alteration to change primary vrm in the request body with the random generated primary vrm
        JsonPathAlteration alterationVrmPut = new JsonPathAlteration("$.primaryVrm", vrm2,"","REPLACE");
        // create alteration to change trailer Id in the request body with a random generated value
        JsonPathAlteration alterationTrailerIdPut = new JsonPathAlteration("$.trailerId", trailerId,"","REPLACE");
        // initialize the alterations list with both declared alteration
        List<JsonPathAlteration> alterationsPut = new ArrayList<>(Arrays.asList(
                alterationVinPut,
                alterationVrmPut,
                alterationTrailerIdPut
        ));
        // Get SystemNumber
        String systemNumber = vehicleTechnicalRecordsSteps.getSystemNumberUsingVin(vin);
        // TEST
        vehicleTechnicalRecordsSteps.putVehicleTechnicalRecordsForVehicleWithAlterations(systemNumber, putRequestBody, alterationsPut);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsByStatus(vin, VehicleTechnicalRecordStatus.ALL);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].vin", vin.toUpperCase());
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].trailerId", trailerId.toUpperCase());
        vehicleTechnicalRecordsSteps.validateResponseContainsJson("[0].vrms[0]", "{\"vrm\": \"" + vrm2.toUpperCase() + "\", \"isPrimary\": true}");


    }

}
