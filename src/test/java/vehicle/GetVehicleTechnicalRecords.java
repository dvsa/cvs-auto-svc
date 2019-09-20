package vehicle;


import data.VehicleTechRecordsData;
import model.vehicles.Vehicle;
import model.vehicles.VehicleTechnicalRecordStatus;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.core.annotations.WithTags;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import steps.VehicleTechnicalRecordsSteps;


@WithTags(
        {
                @WithTag(type = "VehicleTechnicalRecords", name = "All"),
                @WithTag(type = "VehicleTechnicalRecords", name = "Positive"),
                @WithTag(type = "Service", name = "One"),

        }
)
@RunWith(SerenityRunner.class)
public class GetVehicleTechnicalRecords {

    @Steps
    VehicleTechnicalRecordsSteps vehicleTechnicalRecordsSteps;

    private Vehicle vehicleCurrentData = VehicleTechRecordsData.buildVehicleTechRecordsCurrentData();
    private Vehicle vehicleArchivedData = VehicleTechRecordsData.buildVehicleTechRecordsArchivedData();
    private Vehicle vehicleProvisionalData = VehicleTechRecordsData.buildVehicleTechRecordsProvisionalData();

    @Title("CVSB-1057 / CVSB-1157 - AC1 - API Consumer retrieve the Vehicle Technical Records for the input searchIdentifier - VRM")
    @Test
    public void testVehicleTechnicalRecordsSearchVrm() {


        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecords(vehicleCurrentData.getVrms().get(0).getVrm());
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.validateData(vehicleCurrentData, VehicleTechnicalRecordStatus.CURRENT);
    }


    @Title("CVSB-1057 / CVSB-1158 - AC2 - API Consumer retrieve the Vehicle Technical Records for the input searchIdentifier - last 6 digits of the VIN")
    @Test
    public void testVehicleTechnicalRecordsSearchPartialVim() {
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsByPartialVim(vehicleCurrentData.getVin());
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.validateData(vehicleCurrentData, VehicleTechnicalRecordStatus.CURRENT);
    }

    @Title("CVSB-1057 / CVSB-1159 - AC3 - API Consumer retrieve the Vehicle Technical Records for the input searchIdentifier - full VIN")
    @Test
    public void testVehicleTechnicalRecordsSearchFullVim() {
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecords(vehicleCurrentData.getVin());
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.validateData(vehicleCurrentData, VehicleTechnicalRecordStatus.CURRENT);
    }

    @Title("CVSB-1057 / CVSB-1160 - AC4 - No data returned")
    @Test
    public void testVehicleTechnicalRecordsSearchNonExisting() {
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecords(RandomStringUtils.randomAlphanumeric(15));
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(404);
        vehicleTechnicalRecordsSteps.validateData("No resources match the search criteria.");
    }


    @Title("CVSB-1057 / CVSB-1161 - AC5 - Multiple results returned")
    @Test
    public void testVehicleTechnicalRecordsSearchMultipleResults() {
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecords("678413");
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(422);
        vehicleTechnicalRecordsSteps.validateData("The provided partial VIN returned more than one match.");
    }

    @Title("CVSB-1057 / CVSB-1162 - API Consumer when the input searchIdentifier has less than 3 characters")
    @Test
    public void testVehicleTechnicalRecordsSearchLessThanThreeCharacters() {
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecords(RandomStringUtils.randomAlphanumeric(2));
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(400);
        vehicleTechnicalRecordsSteps.validateData("The search identifier should be between 3 and 21 characters.");
    }


    @Title("CVSB-1057 / CVSB-1163 - API Consumer when the input searchIdentifier has more than 21 characters")
    @Test
    public void testVehicleTechnicalRecordsSearchMoreThanTwentyOneCharacters() {
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecords(RandomStringUtils.randomAlphanumeric(22));
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(400);
        vehicleTechnicalRecordsSteps.validateData("The search identifier should be between 3 and 21 characters.");
    }

    @Title("CVSB-1057 / CVSB-1264 - API Consumer retrieve the Vehicle Technical Records for the input searchIdentifier - VRM when the statusCode is archived")
    @Test
    public void testVehicleTechnicalRecordsSearchVrmAndStatusArchived() {
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsByStatus(vehicleArchivedData.getVrms().get(0).getVrm(), VehicleTechnicalRecordStatus.ARCHIVED);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.validateData(vehicleArchivedData, VehicleTechnicalRecordStatus.ARCHIVED);
    }

    @Title("CVSB-1057 / CVSB-1265 - API Consumer retrieve the Vehicle Technical Records for the input searchIdentifier - last 6 digits of the VIN and the statusCode is archived")
    @Test
    public void testVehicleTechnicalRecordsSearchPartialVimAndStatusArchived() {
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsByPartialVimAndStatus(vehicleArchivedData.getVin(), VehicleTechnicalRecordStatus.ARCHIVED);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.validateData(vehicleArchivedData, VehicleTechnicalRecordStatus.ARCHIVED);
    }


    @Title("CVSB-1057 / CVSB-1266 - API Consumer retrieve the Vehicle Technical Records for the input searchIdentifier - full VIN and the statusCode is archived")
    @Test
    public void testVehicleTechnicalRecordsSearchFullVimAndStatusArchived() {
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsByStatus(vehicleArchivedData.getVin(), VehicleTechnicalRecordStatus.ARCHIVED);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.validateData(vehicleArchivedData, VehicleTechnicalRecordStatus.ARCHIVED);
    }

    @Title("CVSB-1057 / CVSB-1267 - API Consumer retrieve the Vehicle Technical Records for the input searchIdentifier - VRM when the statusCode is provisional")
    @Test
    public void testVehicleTechnicalRecordsSearchVrmAndStatusProvisional() {
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsByStatus(vehicleProvisionalData.getVrms().get(0).getVrm(), VehicleTechnicalRecordStatus.PROVISIONAL);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.validateData(vehicleProvisionalData, VehicleTechnicalRecordStatus.PROVISIONAL);
    }

    @Title("CVSB-1057 / CVSB-1268 - API Consumer retrieve the Vehicle Technical Records for the input searchIdentifier - last 6 digits of the VIN and the statusCode is provisional")
    @Test
    public void testVehicleTechnicalRecordsSearchPartialVimAndStatusProvisional() {
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsByPartialVimAndStatus(vehicleProvisionalData.getVin(), VehicleTechnicalRecordStatus.PROVISIONAL);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.validateData(vehicleProvisionalData, VehicleTechnicalRecordStatus.PROVISIONAL);
    }


    @Title("CVSB-1057 / CVSB-1269 - API Consumer retrieve the Vehicle Technical Records for the input searchIdentifier - full VIN and the statusCode is provisional")
    @Test
    public void testVehicleTechnicalRecordsSearchFullVimAndStatusProvisional() {
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsByStatus(vehicleProvisionalData.getVin(), VehicleTechnicalRecordStatus.PROVISIONAL);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.validateData(vehicleProvisionalData, VehicleTechnicalRecordStatus.PROVISIONAL);
    }

    @Title("CVSB-1057 / CVSB-1270 - API Consumer retrieve the Vehicle Technical Records for the input searchIdentifier - VRM and the statusCode is invalid")
    @Test
    public void testVehicleTechnicalRecordsSearchVrmAndStatusInvalid() {
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsByStatus(vehicleCurrentData.getVrms().get(0).getVrm(), VehicleTechnicalRecordStatus.INVALID);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(404);
        vehicleTechnicalRecordsSteps.validateData("No resources match the search criteria.");
    }

    @Title("CVSB-1057 / CVSB-1271 - API Consumer retrieve the Vehicle Technical Records for the input searchIdentifier - last 6 digits of the VIN and the statusCode is invalid")
    @Test
    public void testVehicleTechnicalRecordsSearchPartialVimAndStatusInvalid() {
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsByPartialVimAndStatus(vehicleCurrentData.getVin(), VehicleTechnicalRecordStatus.INVALID);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(404);
        vehicleTechnicalRecordsSteps.validateData("No resources match the search criteria.");
    }


    @Title("CVSB-1057 / CVSB-1272 - API Consumer retrieve the Vehicle Technical Records for the input searchIdentifier - full VIN and the statusCode is invalid")
    @Test
    public void testVehicleTechnicalRecordsSearchFullVimAndStatusInvalid() {
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsByStatus(vehicleCurrentData.getVin(), VehicleTechnicalRecordStatus.INVALID);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(404);
        vehicleTechnicalRecordsSteps.validateData("No resources match the search criteria.");
    }

    @Title("CVSB-1057 / CVSB-1281 - API Consumer retrieve the Vehicle Technical Records for the input searchIdentifier - VRM and the statusCode is current")
    @Test
    public void testVehicleTechnicalRecordsSearchVrmAndStatusCurrent() {
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsByStatus(vehicleCurrentData.getVrms().get(0).getVrm(), VehicleTechnicalRecordStatus.CURRENT);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.validateData(vehicleCurrentData, VehicleTechnicalRecordStatus.CURRENT);
    }

    @Title("CVSB-1057 / CVSB-1282 - API Consumer retrieve the Vehicle Technical Records for the input searchIdentifier - last 6 digits of the VIN and the statusCode is current")
    @Test
    public void testVehicleTechnicalRecordsSearchPartialVimAndStatusCurrent() {
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsByPartialVimAndStatus(vehicleCurrentData.getVin(), VehicleTechnicalRecordStatus.CURRENT);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.validateData(vehicleCurrentData, VehicleTechnicalRecordStatus.CURRENT);
    }


    @Title("CVSB-1057 / CVSB-1283 - API Consumer retrieve the Vehicle Technical Records for the input searchIdentifier - full VIN and the statusCode is current")
    @Test
    public void testVehicleTechnicalRecordsSearchFullVimAndStatusCurrent() {
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsByStatus(vehicleCurrentData.getVin(), VehicleTechnicalRecordStatus.CURRENT);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.validateData(vehicleCurrentData, VehicleTechnicalRecordStatus.CURRENT);
    }

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

}
