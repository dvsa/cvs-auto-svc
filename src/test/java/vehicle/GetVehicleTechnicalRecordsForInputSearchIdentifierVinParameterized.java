package vehicle;


import clients.model.VehicleType;
import model.vehicles.VehicleTechnicalRecordStatus;
import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.core.annotations.WithTags;
import net.thucydides.junit.annotations.TestData;
import org.junit.Test;
import org.junit.runner.RunWith;
import steps.VehicleTechnicalRecordsSteps;

import java.util.Arrays;
import java.util.Collection;

@WithTags(
        {
                @WithTag(type = "VehicleTechnicalRecords", name = "All"),
                @WithTag(type = "VehicleTechnicalRecords", name = "Positive"),
                @WithTag(type = "Service", name = "One"),

        }
)
@RunWith(SerenityParameterizedRunner.class)
public class GetVehicleTechnicalRecordsForInputSearchIdentifierVinParameterized {


    @TestData
    public static Collection<Object[]> testData() {
        return Arrays.asList(new Object[][]{
                {"012432", VehicleType.PSV, VehicleTechnicalRecordStatus.CURRENT, 200},
                {"230123", VehicleType.HGV, VehicleTechnicalRecordStatus.CURRENT, 200},
                {"341234", VehicleType.TRL, VehicleTechnicalRecordStatus.CURRENT, 200},
                {"012461", VehicleType.PSV, VehicleTechnicalRecordStatus.ARCHIVED, 200},
                {"021430", VehicleType.PSV, VehicleTechnicalRecordStatus.PROVISIONAL, 200},
                {"YV31MEC18GA011900", VehicleType.PSV, VehicleTechnicalRecordStatus.CURRENT, 200},
                {"P012301230123", VehicleType.HGV, VehicleTechnicalRecordStatus.CURRENT, 200},
                {"T12341234", VehicleType.TRL, VehicleTechnicalRecordStatus.CURRENT, 200},
                {"XMGDE02FS0H012461", VehicleType.PSV, VehicleTechnicalRecordStatus.ARCHIVED, 200},
                {"1B7GG36N12S021430", VehicleType.PSV, VehicleTechnicalRecordStatus.PROVISIONAL, 200}

        });
    }

    @Steps
    VehicleTechnicalRecordsSteps vehicleTechnicalRecordsSteps;

    private final String searchIdentifier;
    private final VehicleTechnicalRecordStatus statusCode;
    private final VehicleType vehicleType;
    private final int status;

    public GetVehicleTechnicalRecordsForInputSearchIdentifierVinParameterized(String searchIdentifier, VehicleType vehicleType, VehicleTechnicalRecordStatus statusCode, int status){
        this.searchIdentifier = searchIdentifier;
        this.statusCode = statusCode;
        this.status = status;
        this.vehicleType = vehicleType;
    }

    @Title("CVSB-1057 / CVSB-1159 / CVSB-4608 - AC3 - API Consumer retrieve the Vehicle Technical Records for the input searchIdentifier - all vehicle types, full and partial VIN")
    @Test
    public void testVehicleTechnicalRecordsSearchVin() {
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsByStatus(searchIdentifier, statusCode);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(status);
        vehicleTechnicalRecordsSteps.validateFieldHasValue("techRecord.statusCode",statusCode.getStatus());
        vehicleTechnicalRecordsSteps.validateFieldHasValue("techRecord.vehicleType",vehicleType.getValue());
        vehicleTechnicalRecordsSteps.validateFieldEndsWithValue("vin", searchIdentifier);
        vehicleTechnicalRecordsSteps.validateFieldIs("vrms.isPrimary", true);
        vehicleTechnicalRecordsSteps.validateFieldHasSize("techRecord", 1);
    }

}
