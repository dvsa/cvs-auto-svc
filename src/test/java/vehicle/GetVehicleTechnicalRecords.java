package vehicle;


import data.VehicleTechRecordsData;
import model.vehicles.Vehicle;
import model.vehicles.VehicleTechnicalRecordStatus;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import net.thucydides.core.annotations.WithTag;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import steps.VehicleTechnicalRecordsSteps;


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


        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecords(vehicleCurrentData.getVrms().get(0).getVrm());
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.validateData(vehicleCurrentData, VehicleTechnicalRecordStatus.CURRENT);
    }

    @WithTag("Vtm")
    @Title("CVSB-1057 / CVSB-1158 - AC2 - API Consumer retrieve the Vehicle Technical Records for the input searchIdentifier - last 6 digits of the VIN")
    @Test
    public void testVehicleTechnicalRecordsSearchPartialVim() {
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsByPartialVim(vehicleCurrentData.getVin());
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.validateData(vehicleCurrentData, VehicleTechnicalRecordStatus.CURRENT);
    }

    @WithTag("Vtm")
    @Title("CVSB-1057 / CVSB-1159 - AC3 - API Consumer retrieve the Vehicle Technical Records for the input searchIdentifier - full VIN")
    @Test
    public void testVehicleTechnicalRecordsSearchFullVim() {
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecords(vehicleCurrentData.getVin());
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.validateData(vehicleCurrentData, VehicleTechnicalRecordStatus.CURRENT);
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
    @Title("CVSB-1057 / CVSB-1161 - AC5 - Multiple results returned")
    @Test
    public void testVehicleTechnicalRecordsSearchMultipleResults() {
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecords("678413");
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(422);
        vehicleTechnicalRecordsSteps.validateData("The provided partial VIN returned more than one match.");
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
    public void testVehicleTechnicalRecordsSearchPartialVimAndStatusArchived() {
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsByPartialVimAndStatus("012461", VehicleTechnicalRecordStatus.ARCHIVED);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[0].vehicleType", "psv" );
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[0].statusCode","archived");
    }

    @WithTag("Vtm")
    @Title("CVSB-1057 / CVSB-1266 - API Consumer retrieve the Vehicle Technical Records for the input searchIdentifier - full VIN and the statusCode is archived")
    @Test
    public void testVehicleTechnicalRecordsSearchFullVimAndStatusArchived() {
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsByStatus("XMGDE02FS0H012461", VehicleTechnicalRecordStatus.ARCHIVED);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[0].vehicleType", "psv" );
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[0].statusCode","archived");
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
    public void testVehicleTechnicalRecordsSearchPartialVimAndStatusProvisional() {
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsByPartialVimAndStatus(vehicleProvisionalData.getVin(), VehicleTechnicalRecordStatus.PROVISIONAL);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.validateData(vehicleProvisionalData, VehicleTechnicalRecordStatus.PROVISIONAL);
    }

    @WithTag("Vtm")
    @Title("CVSB-1057 / CVSB-1269 - API Consumer retrieve the Vehicle Technical Records for the input searchIdentifier - full VIN and the statusCode is provisional")
    @Test
    public void testVehicleTechnicalRecordsSearchFullVimAndStatusProvisional() {
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
    public void testVehicleTechnicalRecordsSearchPartialVimAndStatusInvalid() {
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsByPartialVimAndStatus(vehicleCurrentData.getVin(), VehicleTechnicalRecordStatus.INVALID);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(404);
        vehicleTechnicalRecordsSteps.validateData("No resources match the search criteria.");
    }

    @WithTag("Vtm")
    @Title("CVSB-1057 / CVSB-1272 - API Consumer retrieve the Vehicle Technical Records for the input searchIdentifier - full VIN and the statusCode is invalid")
    @Test
    public void testVehicleTechnicalRecordsSearchFullVimAndStatusInvalid() {
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsByStatus(vehicleCurrentData.getVin(), VehicleTechnicalRecordStatus.INVALID);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(404);
        vehicleTechnicalRecordsSteps.validateData("No resources match the search criteria.");
    }

    @WithTag("Vtm")
    @Title("CVSB-1057 / CVSB-1281 - API Consumer retrieve the Vehicle Technical Records for the input searchIdentifier - VRM and the statusCode is current")
    @Test
    public void testVehicleTechnicalRecordsSearchVrmAndStatusCurrent() {
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsByStatus(vehicleCurrentData.getVrms().get(0).getVrm(), VehicleTechnicalRecordStatus.CURRENT);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.validateData(vehicleCurrentData, VehicleTechnicalRecordStatus.CURRENT);
    }

    @WithTag("Vtm")
    @Title("CVSB-1057 / CVSB-1282 - API Consumer retrieve the Vehicle Technical Records for the input searchIdentifier - last 6 digits of the VIN and the statusCode is current")
    @Test
    public void testVehicleTechnicalRecordsSearchPartialVimAndStatusCurrent() {
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsByPartialVimAndStatus(vehicleCurrentData.getVin(), VehicleTechnicalRecordStatus.CURRENT);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.validateData(vehicleCurrentData, VehicleTechnicalRecordStatus.CURRENT);
    }

    @WithTag("Vtm")
    @Title("CVSB-1057 / CVSB-1283 - API Consumer retrieve the Vehicle Technical Records for the input searchIdentifier - full VIN and the statusCode is current")
    @Test
    public void testVehicleTechnicalRecordsSearchFullVimAndStatusCurrent() {
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsByStatus(vehicleCurrentData.getVin(), VehicleTechnicalRecordStatus.CURRENT);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.validateData(vehicleCurrentData, VehicleTechnicalRecordStatus.CURRENT);
    }

    @WithTag("Vtm")
    @Title("CVSB-7390 / CVSB-7933 - AC1 - API Consumer retrieve the Vehicle Technical Records")
    @Test
    public void testVehicleTechnicalRecordsHgvtDataMigration() {
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecords("P012301000000");
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.fieldInPathShouldExist("techRecord[0].brakes","dtpNumber");
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecords("C000001");
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.fieldInPathShouldExist("techRecord[0].axles[1].brakes", "leverLength");
    }

    @WithTag("Vtm")
    @Title("CVSB-7051 - TC - AC1.1 API Consumer retrieve the Vehicle Technical Records for - query parameter 'status' not provided & vehicle has both 'current' and 'provisional' technical records - PSV")
    @Test
    public void testVehicleTechnicalRecordsStatusNotProvidedCurrentProvisionalPsv() {
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecords("YV31MEC18GA011911");
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[0].vehicleType", "psv" );
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[0].statusCode","provisional");
    }

    @WithTag("Vtm")
    @Title("CVSB-7051 - TC - AC1.1 API Consumer retrieve the Vehicle Technical Records for - query parameter 'status' not provided & vehicle has both 'current' and 'provisional' technical records HGV")
    @Test
    public void testVehicleTechnicalRecordsStatusNotProvidedCurrentProvisionalHgv() {
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecords("P012301270556");
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[0].vehicleType", "hgv" );
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[0].statusCode","provisional");
    }

    @WithTag("Vtm")
    @Title("CVSB-7051 - TC - AC1.1 API Consumer retrieve the Vehicle Technical Records for - query parameter 'status' not provided & vehicle has both 'current' and 'provisional' technical records TRL")
    @Test
    public void testVehicleTechnicalRecordsStatusNotProvidedCurrentProvisionalTrl() {
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecords("T72741999");
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[0].vehicleType", "trl" );
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[0].statusCode","provisional");
    }

    @WithTag("Vtm")
    @Title("CVSB-7051 - TC - AC1.2 API Consumer retrieve the Vehicle Technical Records for - query parameter 'status' not provided & vehicle has only one 'current' OR 'provisional' technical record (CURRENT) PSV")
    @Test
    public void testVehicleTechnicalRecordsStatusNotProvidedCurrentPsv() {
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecords("XMGDE02FS0H012345");
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[0].vehicleType", "psv" );
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[0].statusCode","current");
    }

    @WithTag("Vtm")
    @Title("CVSB-7051 - TC - AC1.2 API Consumer retrieve the Vehicle Technical Records for - query parameter 'status' not provided & vehicle has only one 'current' OR 'provisional' technical record (CURRENT) HGV")
    @Test
    public void testVehicleTechnicalRecordsStatusNotProvidedCurrentHgv() {
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecords("P012301012938");
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[0].vehicleType", "hgv" );
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[0].statusCode","current");
    }

    @WithTag("Vtm")
    @Title("CVSB-7051 - TC - AC1.2 API Consumer retrieve the Vehicle Technical Records for - query parameter 'status' not provided & vehicle has only one 'current' OR 'provisional' technical record (CURRENT) TRL")
    @Test
    public void testVehicleTechnicalRecordsStatusNotProvidedCurrentTrl() {
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecords("T12765432");
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[0].vehicleType", "trl" );
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[0].statusCode","current");
    }

    @WithTag("Vtm")
    @Title("CVSB-7051 - TC - AC1.2 API Consumer retrieve the Vehicle Technical Records for - query parameter 'status' not provided & vehicle has only one 'current' OR 'provisional' technical record (PROVISIONAL) PSV")
    @Test
    public void testVehicleTechnicalRecordsStatusNotProvidedProvisionalPsv() {
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecords("YV31MEC18GA011944");
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[0].vehicleType", "psv" );
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[0].statusCode","provisional");

    }

    @WithTag("Vtm")
    @Title("CVSB-7051 - TC - AC1.2 API Consumer retrieve the Vehicle Technical Records for - query parameter 'status' not provided & vehicle has only one 'current' OR 'provisional' technical record (PROVISIONAL) HGV")
    @Test
    public void testVehicleTechnicalRecordsStatusNotProvidedProvisionalHgv() {
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecords("P012301270123");
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[0].vehicleType", "hgv" );
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[0].statusCode","provisional");

    }

    @WithTag("Vtm")
    @Title("CVSB-7051 - TC - AC1.2 API Consumer retrieve the Vehicle Technical Records for - query parameter 'status' not provided & vehicle has only one 'current' OR 'provisional' technical record (PROVISIONAL) TRL")
    @Test
    public void testVehicleTechnicalRecordsStatusNotProvidedProvisionalTrl() {
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecords("T72741234");
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[0].vehicleType", "trl" );
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[0].statusCode","provisional");
    }

    @WithTag("Vtm")
    @Title("CVSB-7051 - TC - AC2.1 API Consumer retrieve the Vehicle Technical Records for - query parameter 'status' is 'provisional_over_current' & vehicle has both 'current' and 'provisional' technical records PSV")
    @Test
    public void testVehicleTechnicalRecordsStatusProvidedCurrentProvisionalPsv() {
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsByStatus("YV31MEC18GA011911", VehicleTechnicalRecordStatus.PROVISIONAL_OVER_CURRENT);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[0].vehicleType", "psv" );
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[0].statusCode","provisional");
    }

    //TODO HGV with BOTH Current and Provisional
//    @WithTag("Vtm")
//    @Title("CVSB-7051 - TC - AC2.1 API Consumer retrieve the Vehicle Technical Records for - query parameter 'status' is 'provisional_over_current' & vehicle has both 'current' and 'provisional' technical records HGV")
//    @Test
//    public void testVehicleTechnicalRecordsStatusProvidedCurrentProvisionalHgv() {
//        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsByStatus("270123", VehicleTechnicalRecordStatus.PROVISIONAL_OVER_CURRENT);
//        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
//        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[0].vehicleType", "hgv" );
//        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[0].statusCode","provisional");
//    }

    //TODO TRL with BOTH Current and Provisional
//    @WithTag("Vtm")
//    @Title("CVSB-7051 - TC - AC2.1 API Consumer retrieve the Vehicle Technical Records for - query parameter 'status' is 'provisional_over_current' & vehicle has both 'current' and 'provisional' technical records TRL")
//    @Test
//    public void testVehicleTechnicalRecordsStatusProvidedCurrentProvisionalTrl() {
//        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsByStatus("270123", VehicleTechnicalRecordStatus.PROVISIONAL_OVER_CURRENT);
//        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
//        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[0].vehicleType", "trl" );
//        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[0].statusCode","provisional");
//    }

    @WithTag("Vtm")
    @Title("CVSB-7051 - TC - AC2.2 API Consumer retrieve the Vehicle Technical Records for - query parameter 'status' is 'provisional_over_current' & vehicle has only one 'current' OR 'provisional' technical record (CURRENT) PSV")
    @Test
    public void testVehicleTechnicalRecordsStatusProvidedCurrentPsv() {
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsByStatus("XMGDE02FS0H012345", VehicleTechnicalRecordStatus.PROVISIONAL_OVER_CURRENT);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[0].vehicleType", "psv" );
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[0].statusCode","current");
    }

    @WithTag("Vtm")
    @Title("CVSB-7051 - TC - AC2.2 API Consumer retrieve the Vehicle Technical Records for - query parameter 'status' is 'provisional_over_current' & vehicle has only one 'current' OR 'provisional' technical record (CURRENT) HGV")
    @Test
    public void testVehicleTechnicalRecordsStatusProvidedCurrentHgv() {
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsByStatus("P012301012938", VehicleTechnicalRecordStatus.PROVISIONAL_OVER_CURRENT);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[0].vehicleType", "hgv" );
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[0].statusCode","current");
    }

    @WithTag("Vtm")
    @Title("CVSB-7051 - TC - AC2.2 API Consumer retrieve the Vehicle Technical Records for - query parameter 'status' is 'provisional_over_current' & vehicle has only one 'current' OR 'provisional' technical record (CURRENT) TRL")
    @Test
    public void testVehicleTechnicalRecordsStatusProvidedCurrentTrl() {
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsByStatus("T12765432", VehicleTechnicalRecordStatus.PROVISIONAL_OVER_CURRENT);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[0].vehicleType", "trl" );
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[0].statusCode","current");
    }

    @WithTag("Vtm")
    @Title("CVSB-7051 - TC - AC2.2 API Consumer retrieve the Vehicle Technical Records for - query parameter 'status' is 'provisional_over_current' & vehicle has only one 'current' OR 'provisional' technical record (CURRENT) PSV")
    @Test
    public void testVehicleTechnicalRecordsStatusProvidedProvisionalPsv() {
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsByStatus("YV31MEC18GA011944", VehicleTechnicalRecordStatus.PROVISIONAL_OVER_CURRENT);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[0].vehicleType", "psv" );
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[0].statusCode","provisional");
    }

    @WithTag("Vtm")
    @Title("CVSB-7051 - TC - AC2.2 API Consumer retrieve the Vehicle Technical Records for - query parameter 'status' is 'provisional_over_current' & vehicle has only one 'current' OR 'provisional' technical record (CURRENT) HGV")
    @Test
    public void testVehicleTechnicalRecordsStatusProvidedProvisionalHgv() {
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsByStatus("P012301270123", VehicleTechnicalRecordStatus.PROVISIONAL_OVER_CURRENT);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[0].vehicleType", "hgv" );
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[0].statusCode","provisional");
    }

    @WithTag("Vtm")
    @Title("CVSB-7051 - TC - AC2.2 API Consumer retrieve the Vehicle Technical Records for - query parameter 'status' is 'provisional_over_current' & vehicle has only one 'current' OR 'provisional' technical record (CURRENT) TRL")
    @Test
    public void testVehicleTechnicalRecordsStatusProvidedProvisionalTrl() {
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsByStatus("T72741234", VehicleTechnicalRecordStatus.PROVISIONAL_OVER_CURRENT);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[0].vehicleType", "trl" );
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[0].statusCode","provisional");
    }

    @WithTag("Vtm")
    @Title("CVSB-3963 - TC - AC1 - VSA identifies a vehicle with a provisional tech record (TRL)")
    @Test
    public void testVehicleTechnicalRecordsStatusProvisionalTrl() {
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsByStatus("T72741999", VehicleTechnicalRecordStatus.PROVISIONAL);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[0].vehicleType", "trl" );
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[0].statusCode","provisional");
    }

    @WithTag("Vtm")
    @Title("CVSB-3963 - TC - AC1 - VSA identifies a vehicle with a provisional tech record (HGV)")
    @Test
    public void testVehicleTechnicalRecordsStatusProvisionalHgv() {
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsByStatus("270556", VehicleTechnicalRecordStatus.PROVISIONAL);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[0].vehicleType", "hgv" );
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[0].statusCode","provisional");
    }

    @WithTag("Vtm")
    @Title("CVSB-7743 / CVSB-8623 - AC1 - API Consumer retrieves all Vehicle Technical Records for a specific vehicle, no matter the status")
    @Test
    public void testAllVehicleTechnicalRecordsSearchVin() {
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsByStatus("YV31MEC18GA011900", VehicleTechnicalRecordStatus.ALL);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[0].statusCode", VehicleTechnicalRecordStatus.CURRENT.getStatus());
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[1].statusCode", VehicleTechnicalRecordStatus.ARCHIVED.getStatus());
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord.size", 10);
    }

    @WithTag("Vtm")
    @Title("CVSB-7743 / CVSB-8623 - AC1 - API Consumer retrieves all Vehicle Technical Records for a specific vehicle, no matter the status")
    @Test
    public void testAllVehicleTechnicalRecordsSearchVrm() {
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsByStatus("C47WLL", VehicleTechnicalRecordStatus.ALL);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[0].statusCode", VehicleTechnicalRecordStatus.CURRENT.getStatus());
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[1].statusCode", VehicleTechnicalRecordStatus.ARCHIVED.getStatus());
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord.size", 10);
    }

    @WithTag("Vtm")
    @Title("CVSB-4924 - AC1 API Consumer retrieve the Vehicle Technical Records - adrDetails")
    @Test
    public void testVehicleTechnicalRecordsSearchAdrDetails() {
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecords("012999");
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.validateTechRecordContainsField("adrDetails");
    }

    @WithTag("Vtm")
    @Title("CVSB-4924 - AC1 API Consumer retrieve the Vehicle Technical Records - euroStandard")
    @Test
    public void testVehicleTechnicalRecordsSearchEuroStandard() {
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecords("012999");
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
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("vrms.size", 2);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("vrms[0].vrm", "AA00AAA");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("vrms[0].isPrimary", true);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("vrms[1].vrm", "CT96DRG");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("vrms[1].isPrimary", false);
    }
}
