package vehicle;


import data.GenericData;
import data.VehicleTechRecordsData;
import model.vehicles.Vehicle;
import model.vehicles.VehicleTechnicalRecordSearchCriteria;
import model.vehicles.VehicleTechnicalRecordStatus;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import net.thucydides.core.annotations.WithTag;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import steps.VehicleTechnicalRecordsSteps;
import util.JsonPathAlteration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@RunWith(SerenityRunner.class)
public class GetVehicleTechnicalRecords {

    @Steps
    VehicleTechnicalRecordsSteps vehicleTechnicalRecordsSteps;

    private Vehicle vehicleCurrentData = VehicleTechRecordsData.buildVehicleTechRecordsCurrentData();
    private Vehicle vehicleArchivedData = VehicleTechRecordsData.buildVehicleTechRecordsArchivedData();
    private Vehicle vehicleProvisionalData = VehicleTechRecordsData.buildVehicleTechRecordsProvisionalData();

    //Failing due to multiple vins are returned

    @WithTag("Vtm")
    @Title("CVSB-1057 / CVSB-1157 - AC1 - API Consumer retrieve the Vehicle Technical Records for the input searchIdentifier - VRM")
    @Test
    public void testVehicleTechnicalRecordsSearchVrm() {

        // Read the base test result JSON.
        String postRequestBody = GenericData.readJsonValueFromFile("technical-records_hgv_all_fields.json","$");

        // Create alteration to add one more tech record to in the request body
        String randomSystemNumber = GenericData.generateRandomSystemNumber();
        String randomVin = GenericData.generateRandomVin();
        String randomVrm = GenericData.generateRandomVrm();

        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$.systemNumber", randomSystemNumber, "", "REPLACE");
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin,"","REPLACE");
        JsonPathAlteration alterationVrm = new JsonPathAlteration("$.primaryVrm", randomVrm,"","REPLACE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList
                (alterationSystemNumber,
                alterationVin, alterationVrm));

        // Post the results, together with any alterations, and verify that they are accepted.
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBody, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(201);
        vehicleTechnicalRecordsSteps.validateData("Technical Record created");

        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecords(randomVrm);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord.size()", 1);
    }

    @WithTag("Vtm")
    @Title("CVSB-1057 / CVSB-1158 - AC2 - API Consumer retrieve the Vehicle Technical Records for the input searchIdentifier - last 6 digits of the VIN")
    @Test
    public void testVehicleTechnicalRecordsSearchPartialVim() {

        // Read the base test result JSON.
        String postRequestBody = GenericData.readJsonValueFromFile("technical-records_hgv_all_fields.json","$");

        // Create alteration to add one more tech record to in the request body
        String randomSystemNumber = GenericData.generateRandomSystemNumber();
        String randomVin = GenericData.generateRandomVin();
        String randomVrm = GenericData.generateRandomVrm();

        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$.systemNumber", randomSystemNumber, "", "REPLACE");
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin,"","REPLACE");
        JsonPathAlteration alterationVrm = new JsonPathAlteration("$.primaryVrm", randomVrm,"","REPLACE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList
                (alterationSystemNumber,
                        alterationVin, alterationVrm));

        // Post the results, together with any alterations, and verify that they are accepted.
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBody, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(201);
        vehicleTechnicalRecordsSteps.validateData("Technical Record created");

        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsByPartialVim(randomVin);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord.size()", 1);
    }

}
