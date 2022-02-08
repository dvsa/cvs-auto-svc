package vehicle;


import data.GenericData;
import data.VehicleTechRecordsData;
import model.vehicles.Vehicle;
import model.vehicles.VehicleTechnicalRecordStatus;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import net.thucydides.core.annotations.WithTag;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import steps.*;
import util.JsonPathAlteration;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;


@RunWith(SerenityRunner.class)
public class GetVehicleTechnicalRecords {

    @Steps
    VehicleTechnicalRecordsSteps vehicleTechnicalRecordsSteps;

    private Vehicle vehicleCurrentData = VehicleTechRecordsData.buildVehicleTechRecordsCurrentData();
    private Vehicle vehicleArchivedData = VehicleTechRecordsData.buildVehicleTechRecordsArchivedData();
    private Vehicle vehicleProvisionalData = VehicleTechRecordsData.buildVehicleTechRecordsProvisionalData();


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
    public void testVehicleTechnicalRecordsSearchPartialVin() {

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

        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsByPartialVin(randomVin);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord.size()", 1);
    }

    @WithTag("Vtm")
    @Title("CVSB-1057 / CVSB-1159 - AC3 - API Consumer retrieve the Vehicle Technical Records for the input searchIdentifier - full VIN")
    @Test
    public void testVehicleTechnicalRecordsSearchFullVin() {

        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecords("1B7GG36N12S678410");
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].statusCode", "current");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord.size()", 1);
    }

    @WithTag("Vtm")
    @Title("CVSB-1057 / CVSB-1160 - AC4 - No data returned")
    @Test
    public void testVehicleTechnicalRecordsSearchNonExisting() {
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecords(RandomStringUtils.randomAlphanumeric(15));
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(404);
        vehicleTechnicalRecordsSteps.validateData("No resources match the search criteria.");
    }


    @WithTag("Vtm")
    @Title("CVSB-1057 / CVSB-1162 - API Consumer when the input searchIdentifier has less than 3 characters")
    @Test
    public void testVehicleTechnicalRecordsSearchLessThanThreeCharacters() {
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecords(RandomStringUtils.randomAlphanumeric(2));
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(400);
        vehicleTechnicalRecordsSteps.validateData("The search identifier should be between 3 and 21 characters.");
    }

    @WithTag("Vtm")
    @Title("CVSB-1057 / CVSB-1163 - API Consumer when the input searchIdentifier has more than 21 characters")
    @Test
    public void testVehicleTechnicalRecordsSearchMoreThanTwentyOneCharacters() {
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecords(RandomStringUtils.randomAlphanumeric(22));
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(400);
        vehicleTechnicalRecordsSteps.validateData("The search identifier should be between 3 and 21 characters.");
    }

    @WithTag("Vtm")
    @Title("CVSB-1057 / CVSB-1264 - API Consumer retrieve the Vehicle Technical Records for the input searchIdentifier - VRM when the statusCode is archived")
    @Test
    public void testVehicleTechnicalRecordsSearchVrmAndStatusArchived() {
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsByStatus(vehicleArchivedData.getVrms().get(0).getVrm(), VehicleTechnicalRecordStatus.ARCHIVED);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.validateData(vehicleArchivedData, VehicleTechnicalRecordStatus.ARCHIVED);
    }

    @WithTag("Vtm")
    @Title("CVSB-1057 / CVSB-1265 - API Consumer retrieve the Vehicle Technical Records for the input searchIdentifier - last 6 digits of the VIN and the statusCode is archived")
    @Test
    public void testVehicleTechnicalRecordsSearchPartialVinAndStatusArchived() {
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsByPartialVinAndStatus("012461", VehicleTechnicalRecordStatus.ARCHIVED);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].vehicleType", "psv" );
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].statusCode","archived");
    }

    @WithTag("Vtm")
    @Title("CVSB-1057 / CVSB-1266 - API Consumer retrieve the Vehicle Technical Records for the input searchIdentifier - full VIN and the statusCode is archived")
    @Test
    public void testVehicleTechnicalRecordsSearchFullVinAndStatusArchived() {
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsByStatus("XMGDE02FS0H012461", VehicleTechnicalRecordStatus.ARCHIVED);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].vehicleType", "psv" );
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].statusCode","archived");
    }

    @WithTag("Vtm")
    @Title("CVSB-1057 / CVSB-1267 - API Consumer retrieve the Vehicle Technical Records for the input searchIdentifier - VRM when the statusCode is provisional")
    @Test
    public void testVehicleTechnicalRecordsSearchVrmAndStatusProvisional() {
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsByStatus(vehicleProvisionalData.getVrms().get(0).getVrm(), VehicleTechnicalRecordStatus.PROVISIONAL);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.validateData(vehicleProvisionalData, VehicleTechnicalRecordStatus.PROVISIONAL);
    }

    @WithTag("Vtm")
    @Title("CVSB-1057 / CVSB-1268 - API Consumer retrieve the Vehicle Technical Records for the input searchIdentifier - last 6 digits of the VIN and the statusCode is provisional")
    @Test
    public void testVehicleTechnicalRecordsSearchPartialVinAndStatusProvisional() {
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsByPartialVinAndStatus(vehicleProvisionalData.getVin(), VehicleTechnicalRecordStatus.PROVISIONAL);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.validateData(vehicleProvisionalData, VehicleTechnicalRecordStatus.PROVISIONAL);
    }

    @WithTag("Vtm")
    @Title("CVSB-1057 / CVSB-1269 - API Consumer retrieve the Vehicle Technical Records for the input searchIdentifier - full VIN and the statusCode is provisional")
    @Test
    public void testVehicleTechnicalRecordsSearchFullVinAndStatusProvisional() {
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsByStatus(vehicleProvisionalData.getVin(), VehicleTechnicalRecordStatus.PROVISIONAL);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.validateData(vehicleProvisionalData, VehicleTechnicalRecordStatus.PROVISIONAL);
    }

    @WithTag("Vtm")
    @Title("CVSB-1057 / CVSB-1270 - API Consumer retrieve the Vehicle Technical Records for the input searchIdentifier - VRM and the statusCode is invalid")
    @Test
    public void testVehicleTechnicalRecordsSearchVrmAndStatusInvalid() {
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsByStatus(vehicleCurrentData.getVrms().get(0).getVrm(), VehicleTechnicalRecordStatus.INVALID);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(404);
        vehicleTechnicalRecordsSteps.validateData("No resources match the search criteria.");
    }

    @WithTag("Vtm")
    @Title("CVSB-1057 / CVSB-1271 - API Consumer retrieve the Vehicle Technical Records for the input searchIdentifier - last 6 digits of the VIN and the statusCode is invalid")
    @Test
    public void testVehicleTechnicalRecordsSearchPartialVinAndStatusInvalid() {
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsByPartialVinAndStatus(vehicleCurrentData.getVin(), VehicleTechnicalRecordStatus.INVALID);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(404);
        vehicleTechnicalRecordsSteps.validateData("No resources match the search criteria.");
    }

    @WithTag("Vtm")
    @Title("CVSB-1057 / CVSB-1272 - API Consumer retrieve the Vehicle Technical Records for the input searchIdentifier - full VIN and the statusCode is invalid")
    @Test
    public void testVehicleTechnicalRecordsSearchFullVinAndStatusInvalid() {
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsByStatus(vehicleCurrentData.getVin(), VehicleTechnicalRecordStatus.INVALID);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(404);
        vehicleTechnicalRecordsSteps.validateData("No resources match the search criteria.");
    }

    @WithTag("Vtm")
    @Title("CVSB-1057 / CVSB-1281 - API Consumer retrieve the Vehicle Technical Records for the input searchIdentifier - VRM and the statusCode is current")
    @Test
    public void testVehicleTechnicalRecordsSearchVrmAndStatusCurrent() {

        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsByStatus("BQ91YHQ",VehicleTechnicalRecordStatus.CURRENT);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].statusCode", "current");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord.size()", 1);
    }

    @WithTag("Vtm")
    @Title("CVSB-1057 / CVSB-1282 - API Consumer retrieve the Vehicle Technical Records for the input searchIdentifier - last 6 digits of the VIN and the statusCode is current")
    @Test
    public void testVehicleTechnicalRecordsSearchPartialVinAndStatusCurrent() {

        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsByStatus("678410",VehicleTechnicalRecordStatus.CURRENT);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].statusCode", "current");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord.size()", 1);
    }

    @WithTag("Vtm")
    @Title("CVSB-1057 / CVSB-1283 - API Consumer retrieve the Vehicle Technical Records for the input searchIdentifier - full VIN and the statusCode is current")
    @Test
    public void testVehicleTechnicalRecordsSearchFullVinAndStatusCurrent() {

        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsByStatus("1B7GG36N12S678410",VehicleTechnicalRecordStatus.CURRENT);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].statusCode", "current");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord.size()", 1);
    }

    @WithTag("Vtm")
    @Title("CVSB-7390 / CVSB-7933 - AC1 - API Consumer retrieve the Vehicle Technical Records")
    @Test
    public void testVehicleTechnicalRecordsHgvtDataMigration() {
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecords("P012301000000");
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.fieldInPathShouldExist("[0].techRecord[0].brakes","dtpNumber");
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecords("C000001");
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.fieldInPathShouldExist("[0].techRecord[0].axles[1].brakes", "leverLength");
    }

    @WithTag("Vtm")
    @Title("CVSB-7051 - TC - AC1.1 API Consumer retrieve the Vehicle Technical Records for - query parameter 'status' not provided & vehicle has both 'current' and 'provisional' technical records - PSV")
    @Test
    public void testVehicleTechnicalRecordsStatusNotProvidedCurrentProvisionalPsv() {
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecords("YV31MEC18GA011911");
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].vehicleType", "psv" );
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].statusCode","provisional");
    }

    @WithTag("Vtm")
    @Title("CVSB-7051 - TC - AC1.1 API Consumer retrieve the Vehicle Technical Records for - query parameter 'status' not provided & vehicle has both 'current' and 'provisional' technical records HGV")
    @Test
    public void testVehicleTechnicalRecordsStatusNotProvidedCurrentProvisionalHgv() {
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecords("P012301270556");
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].vehicleType", "hgv" );
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].statusCode","provisional");
    }

    @WithTag("Vtm")
    @Title("CVSB-7051 - TC - AC1.1 API Consumer retrieve the Vehicle Technical Records for - query parameter 'status' not provided & vehicle has both 'current' and 'provisional' technical records TRL")
    @Test
    public void testVehicleTechnicalRecordsStatusNotProvidedCurrentProvisionalTrl() {
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecords("T72741999");
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].vehicleType", "trl" );
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].statusCode","provisional");
    }

    @WithTag("Vtm")
    @Title("CVSB-7051 - TC - AC1.2 API Consumer retrieve the Vehicle Technical Records for - query parameter 'status' not provided & vehicle has only one 'current' OR 'provisional' technical record (CURRENT) PSV")
    @Test
    public void testVehicleTechnicalRecordsStatusNotProvidedCurrentPsv() {
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecords("XMGDE02FS0H012345");
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].vehicleType", "psv" );
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].statusCode","current");
    }

    @WithTag("Vtm")
    @Title("CVSB-7051 - TC - AC1.2 API Consumer retrieve the Vehicle Technical Records for - query parameter 'status' not provided & vehicle has only one 'current' OR 'provisional' technical record (CURRENT) HGV")
    @Test
    public void testVehicleTechnicalRecordsStatusNotProvidedCurrentHgv() {
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecords("P012301012938");
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].vehicleType", "hgv" );
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].statusCode","current");
    }

    @WithTag("Vtm")
    @Title("CVSB-7051 - TC - AC1.2 API Consumer retrieve the Vehicle Technical Records for - query parameter 'status' not provided & vehicle has only one 'current' OR 'provisional' technical record (CURRENT) TRL")
    @Test
    public void testVehicleTechnicalRecordsStatusNotProvidedCurrentTrl() {
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecords("T12765432");
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].vehicleType", "trl" );
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].statusCode","current");
    }

    @WithTag("Vtm")
    @Title("CVSB-7051 - TC - AC1.2 API Consumer retrieve the Vehicle Technical Records for - query parameter 'status' not provided & vehicle has only one 'current' OR 'provisional' technical record (PROVISIONAL) PSV")
    @Test
    public void testVehicleTechnicalRecordsStatusNotProvidedProvisionalPsv() {
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecords("YV31MEC18GA011944");
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].vehicleType", "psv" );
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].statusCode","provisional");

    }

    @WithTag("Vtm")
    @Title("CVSB-7051 - TC - AC1.2 API Consumer retrieve the Vehicle Technical Records for - query parameter 'status' not provided & vehicle has only one 'current' OR 'provisional' technical record (PROVISIONAL) HGV")
    @Test
    public void testVehicleTechnicalRecordsStatusNotProvidedProvisionalHgv() {
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecords("P012301270123");
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].vehicleType", "hgv" );
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].statusCode","provisional");

    }

    @WithTag("Vtm")
    @Title("CVSB-7051 - TC - AC1.2 API Consumer retrieve the Vehicle Technical Records for - query parameter 'status' not provided & vehicle has only one 'current' OR 'provisional' technical record (PROVISIONAL) TRL")
    @Test
    public void testVehicleTechnicalRecordsStatusNotProvidedProvisionalTrl() {
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecords("T72741234");
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].vehicleType", "trl" );
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].statusCode","provisional");
    }

    @WithTag("Vtm")
    @Title("CVSB-7051 - TC - AC2.1 API Consumer retrieve the Vehicle Technical Records for - query parameter 'status' is 'provisional_over_current' & vehicle has both 'current' and 'provisional' technical records PSV")
    @Test
    public void testVehicleTechnicalRecordsStatusProvidedCurrentProvisionalPsv() {
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsByStatus("YV31MEC18GA011911", VehicleTechnicalRecordStatus.PROVISIONAL_OVER_CURRENT);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].vehicleType", "psv" );
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].statusCode","provisional");
    }

    @WithTag("Vtm")
    @Title("CVSB-7051 - TC - AC2.2 API Consumer retrieve the Vehicle Technical Records for - query parameter 'status' is 'provisional_over_current' & vehicle has only one 'current' OR 'provisional' technical record (CURRENT) PSV")
    @Test
    public void testVehicleTechnicalRecordsStatusProvidedCurrentPsv() {
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsByStatus("XMGDE02FS0H012345", VehicleTechnicalRecordStatus.PROVISIONAL_OVER_CURRENT);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].vehicleType", "psv" );
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].statusCode","current");
    }

    @WithTag("Vtm")
    @Title("CVSB-7051 - TC - AC2.2 API Consumer retrieve the Vehicle Technical Records for - query parameter 'status' is 'provisional_over_current' & vehicle has only one 'current' OR 'provisional' technical record (CURRENT) HGV")
    @Test
    public void testVehicleTechnicalRecordsStatusProvidedCurrentHgv() {
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsByStatus("P012301012938", VehicleTechnicalRecordStatus.PROVISIONAL_OVER_CURRENT);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].vehicleType", "hgv" );
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].statusCode","current");
    }

    @WithTag("Vtm")
    @Title("CVSB-7051 - TC - AC2.2 API Consumer retrieve the Vehicle Technical Records for - query parameter 'status' is 'provisional_over_current' & vehicle has only one 'current' OR 'provisional' technical record (CURRENT) TRL")
    @Test
    public void testVehicleTechnicalRecordsStatusProvidedCurrentTrl() {
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsByStatus("T12765432", VehicleTechnicalRecordStatus.PROVISIONAL_OVER_CURRENT);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].vehicleType", "trl" );
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].statusCode","current");
    }

    @WithTag("Vtm")
    @Title("CVSB-7051 - TC - AC2.2 API Consumer retrieve the Vehicle Technical Records for - query parameter 'status' is 'provisional_over_current' & vehicle has only one 'current' OR 'provisional' technical record (CURRENT) PSV")
    @Test
    public void testVehicleTechnicalRecordsStatusProvidedProvisionalPsv() {
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsByStatus("YV31MEC18GA011944", VehicleTechnicalRecordStatus.PROVISIONAL_OVER_CURRENT);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].vehicleType", "psv" );
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].statusCode","provisional");
    }

    @WithTag("Vtm")
    @Title("CVSB-7051 - TC - AC2.2 API Consumer retrieve the Vehicle Technical Records for - query parameter 'status' is 'provisional_over_current' & vehicle has only one 'current' OR 'provisional' technical record (CURRENT) HGV")
    @Test
    public void testVehicleTechnicalRecordsStatusProvidedProvisionalHgv() {
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsByStatus("P012301270123", VehicleTechnicalRecordStatus.PROVISIONAL_OVER_CURRENT);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].vehicleType", "hgv" );
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].statusCode","provisional");
    }

    @WithTag("Vtm")
    @Title("CVSB-7051 - TC - AC2.2 API Consumer retrieve the Vehicle Technical Records for - query parameter 'status' is 'provisional_over_current' & vehicle has only one 'current' OR 'provisional' technical record (CURRENT) TRL")
    @Test
    public void testVehicleTechnicalRecordsStatusProvidedProvisionalTrl() {
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsByStatus("T72741234", VehicleTechnicalRecordStatus.PROVISIONAL_OVER_CURRENT);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].vehicleType", "trl" );
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].statusCode","provisional");
    }

    @WithTag("Vtm")
    @Title("CVSB-3963 - TC - AC1 - VSA identifies a vehicle with a provisional tech record (TRL)")
    @Test
    public void testVehicleTechnicalRecordsStatusProvisionalTrl() {
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsByStatus("T72741999", VehicleTechnicalRecordStatus.PROVISIONAL);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].vehicleType", "trl" );
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].statusCode","provisional");
    }

    @WithTag("Vtm")
    @Title("CVSB-3963 - TC - AC1 - VSA identifies a vehicle with a provisional tech record (HGV)")
    @Test
    public void testVehicleTechnicalRecordsStatusProvisionalHgv() {

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

        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsByStatus(randomVin, VehicleTechnicalRecordStatus.PROVISIONAL);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].vehicleType", "hgv" );
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].statusCode","provisional");
    }

    @WithTag("Vtm")
    @Title("CVSB-7743 / CVSB-8623 - AC1 - API Consumer retrieves all Vehicle Technical Records for a specific vehicle, no matter the status")
    @Test
    public void testAllVehicleTechnicalRecordsSearchVin() {
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsByStatus("YV31MEC18GA011900", VehicleTechnicalRecordStatus.ALL);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].statusCode", VehicleTechnicalRecordStatus.CURRENT.getStatus());
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[1].statusCode", VehicleTechnicalRecordStatus.ARCHIVED.getStatus());
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord.size", 10);
    }

    @WithTag("Vtm")
    @Title("CVSB-7743 / CVSB-8623 - AC1 - API Consumer retrieves all Vehicle Technical Records for a specific vehicle, no matter the status")
    @Test
    public void testAllVehicleTechnicalRecordsSearchVrm() {
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsByStatus("C47WLL", VehicleTechnicalRecordStatus.ALL);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].statusCode", VehicleTechnicalRecordStatus.CURRENT.getStatus());
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[1].statusCode", VehicleTechnicalRecordStatus.ARCHIVED.getStatus());
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord.size", 10);
    }

    @WithTag("Vtm")
    @Title("CVSB-4924 - AC1 API Consumer retrieve the Vehicle Technical Records - adrDetails")
    @Test
    public void testVehicleTechnicalRecordsSearchAdrDetails() {
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecords("ABCDEFGH777777");
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.validateTechRecordContainsField("adrDetails");
    }

    @WithTag("Vtm")
    @Title("CVSB-4924 - AC1 API Consumer retrieve the Vehicle Technical Records - euroStandard")
    @Test
    public void testVehicleTechnicalRecordsSearchEuroStandard() {
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecords("230123");
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.validateTechRecordContainsField("euroStandard");
        vehicleTechnicalRecordsSteps.validateTechRecordFieldIsOfType("euroStandard", String.class);
    }

    @WithTag("Vtm")
    @Title("CVSB-9425 - API Consumer retrieve all vrms for a specific vehicle, primary and secondary")
    @Test
    public void testVehicleTechnicalRecordsGetAllVrms() {
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecords("P012301000000");
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].vrms.size", 2);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].vrms[0].vrm", "AA00AAA");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].vrms[0].isPrimary", true);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].vrms[1].vrm", "CT96DRG");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].vrms[1].isPrimary", false);
    }

    @WithTag("Vtm")
    @Title("CVSB-10209 - AC1 - All attributes applicable to HGVs are returned " +
            "AC2 - HGV vehicle is created, and the appropriate attributes are automatically set")
    @Test
    public void testVehicleTechnicalRecordsGetAllHgvAttributes() {
        // TEST SETUP
        //generate random system number
        String randomSystemNumber = GenericData.generateRandomSystemNumber();
        //generate random Vin
        String randomVin = GenericData.generateRandomVin();
        //generate random Vrm
        String randomVrm = GenericData.generateRandomVrm();
        // read post request body from file
        String postRequestBody = GenericData.readJsonValueFromFile("technical-records_hgv_all_fields.json","$");
        String techRecord = GenericData.readJsonValueFromFile("technical-records_hgv_all_fields.json", "$.techRecord[0]");
        String userId = GenericData.readJsonValueFromFile("technical-records_hgv_all_fields.json", "$.msUserDetails.msOid");
        String name = GenericData.readJsonValueFromFile("technical-records_hgv_all_fields.json", "$.msUserDetails.msUser");
        String secondaryVrm =  GenericData.readJsonValueFromFile("technical-records_hgv_all_fields.json", "$.secondaryVrms[0]");

        // create alteration to change systemNumber in the request body with the random generated systemNumber
        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$.systemNumber", randomSystemNumber,"","REPLACE");
        // create alteration to change Vin in the request body with the random generated Vin
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin,"","REPLACE");
        // create alteration to change primary vrm in the request body with the random generated primary vrm
        JsonPathAlteration alterationVrm = new JsonPathAlteration("$.primaryVrm", randomVrm,"","REPLACE");
        // initialize the alterations list with both declared alteration
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(alterationVin, alterationVrm, alterationSystemNumber));

        // TEST
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBody, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(201);
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsByStatus(GenericData.getPartialVinFromVin(randomVin), VehicleTechnicalRecordStatus.ALL);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.validateResponseContainsJson("[0].techRecord[0]", techRecord);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].statusCode", "provisional");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].createdById", userId);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].createdByName", name);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].vin", randomVin);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].vrms[0].vrm", randomVrm);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].vrms[1].vrm", secondaryVrm);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].vrms.size()", 2);
    }


    @Title("CVSB-10217 - TC - AC1 - API Consumer retrieves HGV vehicle technical records altered for specialist tests")
    @Test
    public void testVehicleTechnicalRecordsHgvSpecialistTests() {
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsByStatus("P012301091180", VehicleTechnicalRecordStatus.ALL);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord.size()", 1);
        vehicleTechnicalRecordsSteps.fieldInPathShouldExist("[0].techRecord[0]", "vehicleSubclass");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].vehicleSubclass[0]", "string");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].vehicleType", "hgv");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].euVehicleCategory", "n2");
    }

    @Title("CVSB-10217 - TC - AC1 - API Consumer retrieves PSV vehicle technical records altered for specialist tests")
    @Test
    public void testVehicleTechnicalRecordsPsvSpecialistTests() {
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsByStatus("XMGDE02FS0H012345", VehicleTechnicalRecordStatus.ALL);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord.size()", 1);
        vehicleTechnicalRecordsSteps.fieldInPathShouldExist("[0].techRecord[0]", "vehicleSubclass");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].vehicleSubclass[0]", "string");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].vehicleType", "psv");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].euVehicleCategory", "m2");
    }

    @Title("CVSB-10217 - TC - AC1 - API Consumer retrieves TRL vehicle technical records altered for specialist tests")
    @Test
    public void testVehicleTechnicalRecordsTrlSpecialistTests() {
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsByStatus("PDY3222222203", VehicleTechnicalRecordStatus.ALL);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord.size()", 2);
        vehicleTechnicalRecordsSteps.fieldInPathShouldExist("[0].techRecord[0]", "vehicleSubclass");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].vehicleSubclass[0]", "string");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].vehicleType", "trl");
        vehicleTechnicalRecordsSteps.fieldInPathShouldNotExist("[0].techRecord[1]", "vehicleSubclass");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[1].vehicleType", "trl");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[1].euVehicleCategory", "o1");
    }

    @Title("CVSB-10217 - TC - AC1 - API Consumer retrieves LGV vehicle technical records altered for specialist tests")
    @Test
    public void testVehicleTechnicalRecordsLgvSpecialistTests() {
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsByStatus("P0123010951264", VehicleTechnicalRecordStatus.ALL);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord.size()", 1);
        vehicleTechnicalRecordsSteps.fieldInPathShouldExist("[0].techRecord[0]", "vehicleSubclass");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].vehicleSubclass[0]", "a");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].vehicleType", "lgv");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].euVehicleCategory", "n1");
    }

    @Title("CVSB-10217 - TC - AC1 - API Consumer retrieves Car vehicle technical records altered for specialist tests")
    @Test
    public void testVehicleTechnicalRecordsCarSpecialistTests() {
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsByStatus("P0123010911250", VehicleTechnicalRecordStatus.ALL);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord.size()", 1);
        vehicleTechnicalRecordsSteps.fieldInPathShouldExist("[0].techRecord[0]", "vehicleSubclass");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].vehicleSubclass[0]", "a");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].vehicleType", "car");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].euVehicleCategory", "m1");
    }

    @Title("CVSB-10217 - TC - AC1 - API Consumer retrieves Motorcycle vehicle technical records altered for specialist tests")
    @Test
    public void testVehicleTechnicalRecordsMotorcycleSpecialistTests() {
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecords("P0123010956789");
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord.size()", 1);
        vehicleTechnicalRecordsSteps.fieldInPathShouldExist("[0].techRecord[0]", "vehicleSubclass");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].vehicleSubclass[0]", "string");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].vehicleType", "motorcycle");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].euVehicleCategory", "l1e-a");
    }

    @Title("CVSB-11546 - TC - AC1 API Consumer retrieve the Vehicle Technical Records (recordCompleteness)")
    @Test
    public void testVehicleTechnicalRecords_recordCompleteness() {
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsByStatus("DP76UMK4DQLTOT778899", VehicleTechnicalRecordStatus.ALL);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord.size()", 1);
        vehicleTechnicalRecordsSteps.fieldInPathShouldExist("[0].techRecord[0]", "recordCompleteness");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].recordCompleteness", "skeleton");
    }

    @Title("CVSB-12013 - TC - AC2 BE API consumer performs a GET call for tech records microservice")
    @Test
    public void testVehicleTechnicalRecords_VinSpecialCharacters() {
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsByStatus("YV31ME00000 1/\\*-1", VehicleTechnicalRecordStatus.ALL);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].vin", "YV31ME00000 1/\\*-1");
    }

    @WithTag("Vtm")
    @Title("CVSB-10239 - AC1 - All attributes applicable to PSVs are returned " +
            "AC2 - PSV vehicle is created, and the appropriate attributes are automatically set")
    @Test
    public void testVehicleTechnicalRecordsGetAllPsvAttributes() {
        // TEST SETUP
        //generate random system number
        String randomSystemNumber = GenericData.generateRandomSystemNumber();
        //generate random Vin
        String randomVin = GenericData.generateRandomVin();
        //generate random Vrm
        String randomVrm = GenericData.generateRandomVrm();
        // read post request body from file
        String postRequestBody = GenericData.readJsonValueFromFile("technical-records_psv_all_fields.json","$");
        String techRecord = GenericData.readJsonValueFromFile("technical-records_psv_all_fields.json", "$.techRecord[0]");
        String userId = GenericData.readJsonValueFromFile("technical-records_psv_all_fields.json", "$.msUserDetails.msOid");
        String name = GenericData.readJsonValueFromFile("technical-records_psv_all_fields.json", "$.msUserDetails.msUser");
        String secondaryVrm =  GenericData.readJsonValueFromFile("technical-records_psv_all_fields.json", "$.secondaryVrms[0]");

        // create alteration to change systemNumber in the request body with the random generated systemNumber
        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$.systemNumber", randomSystemNumber,"","REPLACE");
        // create alteration to change Vin in the request body with the random generated Vin
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin,"","REPLACE");
        // create alteration to change primary vrm in the request body with the random generated primary vrm
        JsonPathAlteration alterationVrm = new JsonPathAlteration("$.primaryVrm", randomVrm,"","REPLACE");
        // initialize the alterations list with both declared alteration
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(alterationVin, alterationVrm, alterationSystemNumber));

        // TEST
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBody, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(201);
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsByStatus(GenericData.getPartialVinFromVin(randomVin), VehicleTechnicalRecordStatus.ALL);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.validateResponseContainsJson("[0].techRecord[0]", techRecord);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].statusCode", "provisional");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].createdById", userId);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].createdByName", name);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].vin", randomVin);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].vrms[0].vrm", randomVrm);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].vrms[1].vrm", secondaryVrm);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].vrms.size()", 2);
    }

    @WithTag("Vtm")
    @Title("CVSB-10245 - AC1 - All attributes applicable to TRLs are returned " +
            "AC2 - TRL vehicle is created, and the appropriate attributes are automatically set " +
            "CVSB-10131 - AC1 - TRL vehicle is created, and the next trailerID is assigned")
    @Test
    public void testVehicleTechnicalRecordsGetAllTrlAttributes() {
        // TEST SETUP
        //generate random systemNumber
        String randomSystemNumber = GenericData.generateRandomSystemNumber();
        //generate random Vin
        String randomVin = GenericData.generateRandomVin();
        //generate random Vrm
        String randomVrm = GenericData.generateRandomVrm();
        // read post request body from file
        String postRequestBody = GenericData.readJsonValueFromFile("technical-records_trl_all_fields.json","$");
        String techRecord = GenericData.readJsonValueFromFile("technical-records_trl_all_fields.json", "$.techRecord[0]");
        String userId = GenericData.readJsonValueFromFile("technical-records_trl_all_fields.json", "$.msUserDetails.msOid");
        String name = GenericData.readJsonValueFromFile("technical-records_trl_all_fields.json", "$.msUserDetails.msUser");
        String secondaryVrm =  GenericData.readJsonValueFromFile("technical-records_trl_all_fields.json", "$.secondaryVrms[0]");
        String trailerId =  GenericData.readJsonValueFromFile("technical-records_trl_all_fields.json", "$.trailerId");


        // create alteration to change systemNumber in the request body with the random generated systemNumber
        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$.systemNumber", randomSystemNumber,"","REPLACE");
        // create alteration to change Vin in the request body with the random generated Vin
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin,"","REPLACE");
        // create alteration to change primary vrm in the request body with the random generated primary vrm
        JsonPathAlteration alterationVrm = new JsonPathAlteration("$.primaryVrm", randomVrm,"","REPLACE");
        // initialize the alterations list with both declared alteration
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(alterationVin, alterationVrm, alterationSystemNumber));

        // TEST
        String generatedTrailerId = vehicleTechnicalRecordsSteps.getNextTrailerIdInSequence();
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBody, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(201);
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsByStatus(randomVin, VehicleTechnicalRecordStatus.ALL);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.validateResponseContainsJson("[0].techRecord[0]", techRecord);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].statusCode", "provisional");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].createdById", userId);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].createdByName", name);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].vin", randomVin);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldNotBe("[0].trailerId", trailerId);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].vrms[0].vrm", randomVrm);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].vrms[1].vrm", secondaryVrm);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].vrms.size()", 2);
        // for CVSB-10131 we validate that the generated trailerId from the backend is unique
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldNotBe("[0].trailerId", trailerId);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].trailerId", generatedTrailerId);
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsByStatus(generatedTrailerId, VehicleTechnicalRecordStatus.ALL);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("size()", 1);
    }

    @WithTag("Vtm")
    @Title("CVSB-10255 - AC1 - All attributes applicable to PSVs are returned")
    @Test
    public void testVehicleTechnicalRecordsGetManufacturer() throws IOException {
        // TEST
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsByStatusWithMetadata("C123456", VehicleTechnicalRecordStatus.ALL);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        ArrayList<String> bodyMake = GenericData.readFile("body_make_list.txt");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].metadata.bodyMakeFe.size()", 613);
        for (String make : bodyMake) {
            vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe
                    ("[0].metadata.bodyMakeFe.find { it == '" + make.replace("'","\\'") + "' }", make);
        }
        ArrayList<String> vehicleManufacturer = GenericData.readFile("manufacturers_list.txt");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].metadata.makeAndChassisMakeFe.size()", 408);
        for (String manufacturer : vehicleManufacturer) {
            vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe
                    ("[0].metadata.makeAndChassisMakeFe.find { it == '" + manufacturer.replace("'","\\'") + "' }", manufacturer);
        }

    }

    @Title("CVSB-9335 - TC - AC1 API consumer does not use the “searchCriteria” query parameter")
    @Test
    public void testVehicleTechnicalRecordsNoSearchCriteria() {
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecords("T72741234");
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].vehicleType", "trl" );
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].statusCode","provisional");
    }

    @Title("CVSB-9335 - TC - AC1 API consumer does not use the “searchCriteria” query parameter")
    @Test
    public void testVehicleTechnicalRecordsAllSearchCriteria() {
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsCriteria("230123", "all");
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].vehicleType", "hgv" );
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].statusCode","current");
    }
}
