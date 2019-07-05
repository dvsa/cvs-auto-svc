package vehicle;


import model.vehicles.Vehicle;
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
public class GetVehicleTechnicalRecordsForInputSearchIdentifierTrailerIdParameterized {


    @TestData
    public static Collection<Object[]> testData() {
        return Arrays.asList(new Object[][]{

                {"09876543", VehicleTechnicalRecordStatus.CURRENT, 200},
                {"C000001", VehicleTechnicalRecordStatus.CURRENT, 200},
                {"C000003", VehicleTechnicalRecordStatus.ARCHIVED, 200},
                {"C000002", VehicleTechnicalRecordStatus.PROVISIONAL, 200}

        });
    }

    @Steps
    VehicleTechnicalRecordsSteps vehicleTechnicalRecordsSteps;

    private final String searchIdentifier;
    private final VehicleTechnicalRecordStatus statusCode;
    private final int status;

    public GetVehicleTechnicalRecordsForInputSearchIdentifierTrailerIdParameterized(String searchIdentifier, VehicleTechnicalRecordStatus statusCode, int status){
        this.searchIdentifier = searchIdentifier;
        this.statusCode = statusCode;
        this.status = status;
    }

    @Title("CVSB-1057 / CVSB-1159 / CVSB-4608 - AC3 - API Consumer retrieve the Vehicle Technical Records for the input searchIdentifier - all vehicle types, full and partial VIN")
    @Test
    public void testVehicleTechnicalRecordsSearchVin() {
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsByStatus(searchIdentifier, statusCode);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(status);
        vehicleTechnicalRecordsSteps.validateFieldHasValue("techRecord.statusCode",statusCode.getStatus());
        vehicleTechnicalRecordsSteps.validateFieldEndsWithValue("trailerId", searchIdentifier);
        vehicleTechnicalRecordsSteps.validateFieldIs("vrms.isPrimary", true);
        vehicleTechnicalRecordsSteps.validateFieldHasSize("techRecord", 1);
    }

}
