package vehicle;


import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import net.thucydides.junit.annotations.TestData;
import org.junit.Test;
import org.junit.runner.RunWith;
import steps.VehicleTechnicalRecordsSteps;

import java.util.Arrays;
import java.util.Collection;


@RunWith(SerenityParameterizedRunner.class)
public class GetVehicleSearchCriteria {

    @Steps
    VehicleTechnicalRecordsSteps vehicleTechnicalRecordsSteps;

    @TestData
    public static Collection<Object[]> testData(){
        return Arrays.asList(new Object[][]{
                //{"222202", "", 200},
                //{"222203", "", 422},
                {"222203", "", 200},
                {"C100002", "", 200},
                //{"P123403", "", 422},
                {"P123403", "", 200},
                {"VIN1","", 404},
                {"222222202", "", 404},
                {"LONGESTVRM", "", 404},
                {"1TRAILERID", "", 404},
                //{"PDY2222222203", "", 200},
                //{"X2PSV", "", 200},
                //{"222201", "all", 200},
                {"C100002", "all", 200},
                //{"PDY2222222201", "all", 200},
                //{"X2HGV", "all", 200},
                //{"PDY2222222201", "vin", 200},
                {"VIN1","vin", 200},
                {"222201", "vin", 404},
                {"X2HGV", "vin", 404},
                //{"X2PSV", "vrm", 200},
                {"222202", "vrm", 404},
                {"LONGESTVRM", "vrm", 200},
                {"PDY2222222202", "vrm", 404},
                {"P123403", "partialVin", 404},
                {"PDY2222222203", "partialVin", 404},
                {"2222222203", "partialVin", 404},
                {"22021979", "partialVin", 200},
                {"X2TRL", "partialVin", 404},
                //{"222202", "partialVin", 200},
                //{"222203", "partialVin", 422},
                {"222203", "partialVin", 200},
                {"222203", "trailerId", 404},
                {"X2TRL", "trailerId", 404},
                //{"1TRAILERID", "trailerId", 200},
                {"PDY2222222202", "trailerId", 404},
                {"C100002", "trailerId", 200},
        });
    }

    private String searchIdentifier;
    private String searchCriteria;
    private int status;

    public GetVehicleSearchCriteria(String searchIdentifier, String searchCriteria, int status) {
        this.searchIdentifier = searchIdentifier;
        this.searchCriteria = searchCriteria;
        this.status = status;
    }

    @Title("CVSB-9335 - Tech record API changes related to be able to search full VIN is less than 9 characters")
    @Test
    public void testVehicleTechnicalRecordsNoSearchCriteria() {
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsCriteria(searchIdentifier, searchCriteria);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(status);
    }
}
