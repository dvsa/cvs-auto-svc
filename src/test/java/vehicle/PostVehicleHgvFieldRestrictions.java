package vehicle;

import data.GenericData;
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
public class PostVehicleHgvFieldRestrictions {

    @Steps
    VehicleTechnicalRecordsSteps vehicleTechnicalRecordsSteps;

    @TestData
    public static Collection<Object[]> testData(){
        return Arrays.asList(new Object[][]{
                {"$.primaryVrm", RandomStringUtils.randomAlphanumeric(10)},
                {"$.primaryVrm", ""},
                {"$.primaryVrm", 100},
                {"$.secondaryVrms[0]", RandomStringUtils.randomAlphanumeric(10)},
                {"$.secondaryVrms[0]", ""},
                {"$.secondaryVrms[0]", 100},
                {"$.vin", RandomStringUtils.randomAlphanumeric(22)},
                {"$.vin", RandomStringUtils.randomAlphanumeric(2)},
                {"$.vin", 100},
                {"$.techRecord[0].vehicleType", RandomStringUtils.randomAlphanumeric(10)},
                {"$.techRecord[0].vehicleType", 100},
                {"$.techRecord[0].regnDate", "21/08/1987"},
                {"$.techRecord[0].regnDate", false},
                {"$.techRecord[0].manufactureYear", 10000},
                {"$.techRecord[0].manufactureYear", -1},
                {"$.techRecord[0].manufactureYear", RandomStringUtils.randomAlphabetic(4)},
                {"$.techRecord[0].noOfAxles", 100},
                {"$.techRecord[0].noOfAxles", -1},
                {"$.techRecord[0].noOfAxles", RandomStringUtils.randomAlphabetic(2)},
                {"$.techRecord[0].brakes.dtpNumber", RandomStringUtils.randomAlphanumeric(7)},
                {"$.techRecord[0].brakes.dtpNumber", 99},
                {"$.techRecord[0].axles[0].parkingBrakeMrk", "99"},
                {"$.techRecord[0].speedLimiterMrk", "99"},
                {"$.techRecord[0].tachoExemptMrk", "99"},
                {"$.techRecord[0].euroStandard", 10},
                {"$.techRecord[0].euroStandard", -1},
                {"$.techRecord[0].euroStandard", false},
                {"$.techRecord[0].fuelPropulsionSystem", RandomStringUtils.randomAlphanumeric(10)},
                {"$.techRecord[0].fuelPropulsionSystem", 10},
                {"$.techRecord[0].roadFriendly", "99"},
                {"$.techRecord[0].drawbarCouplingFitted", "99"},
                {"$.techRecord[0].vehicleClass.description", RandomStringUtils.randomAlphanumeric(10)},
                {"$.techRecord[0].vehicleClass.description", 10},
                {"$.techRecord[0].offRoad", 10},
                {"$.techRecord[0].numberOfWheelsDriven", 10000},
                {"$.techRecord[0].numberOfWheelsDriven", RandomStringUtils.randomAlphabetic(4)},
                {"$.techRecord[0].euVehicleCategory", RandomStringUtils.randomAlphanumeric(3)},
                {"$.techRecord[0].euVehicleCategory", 10},
                {"$.techRecord[0].emissionsLimit", 100},
                {"$.techRecord[0].emissionsLimit", -1},
                {"$.techRecord[0].emissionsLimit", RandomStringUtils.randomAlphabetic(2)},
                {"$.techRecord[0].departmentalVehicleMarker", 10},
                {"$.techRecord[0].alterationMarker", 10},
                {"$.techRecord[0].approvalType", "nta"},
                {"$.techRecord[0].approvalType", 10},
                {"$.techRecord[0].approvalTypeNumber", RandomStringUtils.randomAlphanumeric(26)},
                {"$.techRecord[0].approvalTypeNumber", 10},
                {"$.techRecord[0].ntaNumber", RandomStringUtils.randomAlphanumeric(41)},
                {"$.techRecord[0].ntaNumber", 10},
                {"$.techRecord[0].variantNumber", RandomStringUtils.randomAlphanumeric(26)},
                {"$.techRecord[0].variantNumber", 10},
                {"$.techRecord[0].variantVersionNumber", RandomStringUtils.randomAlphanumeric(36)},
                {"$.techRecord[0].variantVersionNumber", 10},
                {"$.techRecord[0].make", RandomStringUtils.randomAlphanumeric(31)},
                {"$.techRecord[0].make", 10},
                {"$.techRecord[0].model", RandomStringUtils.randomAlphanumeric(31)},
                {"$.techRecord[0].model", 10},
                {"$.techRecord[0].bodyType.description", RandomStringUtils.randomNumeric(6)},
                {"$.techRecord[0].bodyType.description", 10},
                {"$.techRecord[0].functionCode", "10"},
                {"$.techRecord[0].functionCode", 10},
                {"$.techRecord[0].conversionRefNo", RandomStringUtils.randomAlphanumeric(11)},
                {"$.techRecord[0].conversionRefNo", 10},
                {"$.techRecord[0].grossGbWeight", 100000},
                {"$.techRecord[0].grossGbWeight", -1},
                {"$.techRecord[0].grossGbWeight", RandomStringUtils.randomAlphabetic(5)},
                {"$.techRecord[0].grossEecWeight", 100000},
                {"$.techRecord[0].grossEecWeight", -1},
                {"$.techRecord[0].grossEecWeight", RandomStringUtils.randomAlphabetic(5)},
                {"$.techRecord[0].grossDesignWeight", 100000},
                {"$.techRecord[0].grossDesignWeight", -1},
                {"$.techRecord[0].grossDesignWeight", RandomStringUtils.randomAlphabetic(5)},
                {"$.techRecord[0].trainGbWeight", 100000},
                {"$.techRecord[0].trainGbWeight", -1},
                {"$.techRecord[0].trainGbWeight", RandomStringUtils.randomAlphabetic(5)},
                {"$.techRecord[0].trainEecWeight", 100000},
                {"$.techRecord[0].trainEecWeight", -1},
                {"$.techRecord[0].trainEecWeight", RandomStringUtils.randomAlphabetic(5)},
                {"$.techRecord[0].trainDesignWeight", 100000},
                {"$.techRecord[0].trainDesignWeight", -1},
                {"$.techRecord[0].trainDesignWeight", RandomStringUtils.randomAlphabetic(5)},
                {"$.techRecord[0].maxTrainGbWeight", 100000},
                {"$.techRecord[0].maxTrainGbWeight", -1},
                {"$.techRecord[0].maxTrainGbWeight", RandomStringUtils.randomAlphabetic(5)},
                {"$.techRecord[0].maxTrainEecWeight", 100000},
                {"$.techRecord[0].maxTrainEecWeight", -1},
                {"$.techRecord[0].maxTrainEecWeight", RandomStringUtils.randomAlphabetic(5)},
                {"$.techRecord[0].maxTrainDesignWeight", 100000},
                {"$.techRecord[0].maxTrainDesignWeight", -1},
                {"$.techRecord[0].maxTrainDesignWeight", RandomStringUtils.randomAlphabetic(5)},
                {"$.techRecord[0].axles[0].axleNumber", 100000},
                {"$.techRecord[0].axles[0].axleNumber", -1},
                {"$.techRecord[0].axles[0].axleNumber", RandomStringUtils.randomAlphabetic(5)},
                {"$.techRecord[0].axles[0].weights.gbWeight", 100000},
                {"$.techRecord[0].axles[0].weights.gbWeight", -1},
                {"$.techRecord[0].axles[0].weights.gbWeight", RandomStringUtils.randomAlphabetic(5)},
                {"$.techRecord[0].axles[0].weights.eecWeight", 100000},
                {"$.techRecord[0].axles[0].weights.eecWeight", -1},
                {"$.techRecord[0].axles[0].weights.eecWeight", RandomStringUtils.randomAlphabetic(5)},
                {"$.techRecord[0].axles[0].weights.designWeight", 100000},
                {"$.techRecord[0].axles[0].weights.designWeight", -1},
                {"$.techRecord[0].axles[0].weights.designWeight", RandomStringUtils.randomAlphabetic(5)},
                {"$.techRecord[0].tyreUseCode", RandomStringUtils.randomAlphabetic(3)},
                {"$.techRecord[0].tyreUseCode", ""},
                {"$.techRecord[0].tyreUseCode", 10},
                {"$.techRecord[0].axles[0].tyres.tyreCode", 100000},
                {"$.techRecord[0].axles[0].tyres.tyreCode", -1},
                {"$.techRecord[0].axles[0].tyres.tyreCode", RandomStringUtils.randomAlphabetic(5)},
                {"$.techRecord[0].axles[0].tyres.tyreSize", RandomStringUtils.randomAlphanumeric(13)},
                {"$.techRecord[0].axles[0].tyres.tyreSize", ""},
                {"$.techRecord[0].axles[0].tyres.tyreSize", 10},
                {"$.techRecord[0].axles[0].tyres.plyRating", RandomStringUtils.randomAlphanumeric(3)},
                {"$.techRecord[0].axles[0].tyres.plyRating", ""},
                {"$.techRecord[0].axles[0].tyres.plyRating", 10},
                {"$.techRecord[0].axles[0].tyres.fitmentCode", RandomStringUtils.randomAlphanumeric(7)},
                {"$.techRecord[0].axles[0].tyres.fitmentCode", 10},
                {"$.techRecord[0].axles[0].tyres.dataTrAxles", 1000},
                {"$.techRecord[0].axles[0].tyres.dataTrAxles", -1},
                {"$.techRecord[0].axles[0].tyres.dataTrAxles", RandomStringUtils.randomAlphabetic(3)},
                {"$.techRecord[0].dimensions.length", 100000},
                {"$.techRecord[0].dimensions.length", -1},
                {"$.techRecord[0].dimensions.length", RandomStringUtils.randomAlphabetic(5)},
                {"$.techRecord[0].dimensions.width", 100000},
                {"$.techRecord[0].dimensions.width", -1},
                {"$.techRecord[0].dimensions.width", RandomStringUtils.randomAlphabetic(5)},
                {"$.techRecord[0].dimensions.axleSpacing[0].axles", 10},
                {"$.techRecord[0].dimensions.axleSpacing[0].value", 100000},
                {"$.techRecord[0].dimensions.axleSpacing[0].value", -1},
                {"$.techRecord[0].dimensions.axleSpacing[0].value", RandomStringUtils.randomAlphabetic(5)},
                {"$.techRecord[0].frontAxleToRearAxle", 100000},
                {"$.techRecord[0].frontAxleToRearAxle", -1},
                {"$.techRecord[0].frontAxleToRearAxle", RandomStringUtils.randomAlphabetic(5)},
                {"$.techRecord[0].frontAxleTo5thWheelCouplingMin", 100000},
                {"$.techRecord[0].frontAxleTo5thWheelCouplingMin", -1},
                {"$.techRecord[0].frontAxleTo5thWheelCouplingMin", RandomStringUtils.randomAlphabetic(5)},
                {"$.techRecord[0].frontAxleTo5thWheelCouplingMax", 100000},
                {"$.techRecord[0].frontAxleTo5thWheelCouplingMax", -1},
                {"$.techRecord[0].frontAxleTo5thWheelCouplingMax", RandomStringUtils.randomAlphabetic(5)},
                {"$.techRecord[0].frontAxleTo5thWheelMin", 100000},
                {"$.techRecord[0].frontAxleTo5thWheelMin", -1},
                {"$.techRecord[0].frontAxleTo5thWheelMin", RandomStringUtils.randomAlphabetic(5)},
                {"$.techRecord[0].frontAxleTo5thWheelMax", 100000},
                {"$.techRecord[0].frontAxleTo5thWheelMax", -1},
                {"$.techRecord[0].frontAxleTo5thWheelMax", RandomStringUtils.randomAlphabetic(5)},
                {"$.techRecord[0].applicantDetails.name", RandomStringUtils.randomAlphanumeric(151)},
                {"$.techRecord[0].applicantDetails.name", ""},
                {"$.techRecord[0].applicantDetails.name", 10},
                {"$.techRecord[0].applicantDetails.address1", RandomStringUtils.randomAlphanumeric(61)},
                {"$.techRecord[0].applicantDetails.address1", ""},
                {"$.techRecord[0].applicantDetails.address1", 10},
                {"$.techRecord[0].applicantDetails.address2", RandomStringUtils.randomAlphanumeric(61)},
                {"$.techRecord[0].applicantDetails.address2", ""},
                {"$.techRecord[0].applicantDetails.address2", 10},
                {"$.techRecord[0].applicantDetails.address3", RandomStringUtils.randomAlphanumeric(61)},
                {"$.techRecord[0].applicantDetails.address3", ""},
                {"$.techRecord[0].applicantDetails.address3", 10},
                {"$.techRecord[0].applicantDetails.postTown", RandomStringUtils.randomAlphanumeric(61)},
                {"$.techRecord[0].applicantDetails.postTown", ""},
                {"$.techRecord[0].applicantDetails.postTown", 10},
                {"$.techRecord[0].applicantDetails.postCode", RandomStringUtils.randomAlphanumeric(13)},
                {"$.techRecord[0].applicantDetails.postCode", ""},
                {"$.techRecord[0].applicantDetails.postCode", 10},
                {"$.techRecord[0].applicantDetails.telephoneNumber", RandomStringUtils.randomAlphanumeric(26)},
                {"$.techRecord[0].applicantDetails.telephoneNumber", ""},
                {"$.techRecord[0].applicantDetails.telephoneNumber", 10},
                {"$.techRecord[0].applicantDetails.emailAddress", RandomStringUtils.randomAlphanumeric(256)},
                {"$.techRecord[0].applicantDetails.emailAddress", ""},
                {"$.techRecord[0].applicantDetails.emailAddress", 10},
                {"$.techRecord[0].microfilm.microfilmDocumentType", RandomStringUtils.randomAlphanumeric(10)},
                {"$.techRecord[0].microfilm.microfilmDocumentType", 10},
                {"$.techRecord[0].microfilm.microfilmRollNumber", RandomStringUtils.randomAlphanumeric(6)},
                {"$.techRecord[0].microfilm.microfilmRollNumber", ""},
                {"$.techRecord[0].microfilm.microfilmRollNumber", 10},
                {"$.techRecord[0].microfilm.microfilmSerialNumber", RandomStringUtils.randomAlphanumeric(5)},
                {"$.techRecord[0].microfilm.microfilmSerialNumber", ""},
                {"$.techRecord[0].plates[0].plateSerialNumber", RandomStringUtils.randomAlphanumeric(13)},
                {"$.techRecord[0].plates[0].plateSerialNumber", ""},
                {"$.techRecord[0].plates[0].plateSerialNumber", 10},
                {"$.techRecord[0].plates[0].plateIssueDate", "31/12/2100"},
                {"$.techRecord[0].plates[0].plateIssueDate", false},
                {"$.techRecord[0].plates[0].plateReasonForIssue", RandomStringUtils.randomAlphanumeric(10)},
                {"$.techRecord[0].plates[0].plateReasonForIssue", 10},
                {"$.techRecord[0].plates[0].plateIssuer", RandomStringUtils.randomAlphanumeric(151)},
                {"$.techRecord[0].plates[0].plateIssuer", ""},
                {"$.techRecord[0].plates[0].plateIssuer", 10},
                {"$.techRecord[0].plates[0].plateIssuer", 10},
                {"$.techRecord[0].plates[0].plateIssuer", 10},
                {"$.techRecord[0].notes", 10},
                {"$.techRecord[0].reasonForCreation", RandomStringUtils.randomAlphanumeric(101)},
                {"$.techRecord[0].reasonForCreation", ""},
                {"$.techRecord[0].reasonForCreation", 10}
        });
    }

    private final String jsonPath;
    private final Object value;

    public PostVehicleHgvFieldRestrictions(String jsonPath, Object value) {
        this.jsonPath = jsonPath;
        this.value = value;
    }

    @WithTag("Vtm")
    @Title("CVSB-10210 - AC3 - Attempt to create a new vehicle with unexpected values for a field that accepts only specific values" +
            "AC4 - Attempt to create a new vehicle, using a field with its value outside of the min/max length for that field")
    @Test
    public void testValidateHgvFieldsDataTypesAndRestrictions() {
        // TEST SETUP
        // generate random Vin
        String randomVin = GenericData.generateRandomVin();
        // generate random Vrm
        String randomVrm = GenericData.generateRandomVrm();
        // read post request body from file
        String postRequestBodyHgv = GenericData.readJsonValueFromFile("technical-records_hgv_all_fields.json", "$");
        // create alteration to change Vin in the post request body with the random generated Vin
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        // create alteration to change primary vrm in the request body with the random generated primary vrm
        JsonPathAlteration alterationVrm = new JsonPathAlteration("$.primaryVrm", randomVrm, "", "REPLACE");

        // initialize the alterations list with both declared alterations
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(alterationVin, alterationVrm));


        // validate each field for its restrictions and data type
        JsonPathAlteration restriction = new JsonPathAlteration(jsonPath, value, "", "REPLACE");
        alterations.add(restriction);
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBodyHgv, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(400);
    }
}
