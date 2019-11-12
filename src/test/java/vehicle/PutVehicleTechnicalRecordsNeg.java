package vehicle;

import data.GenericData;
import model.vehicles.VehicleTechnicalRecordStatus;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import net.thucydides.core.annotations.WithTag;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import steps.VehicleTechnicalRecordsSteps;
import util.JsonPathAlteration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@RunWith(SerenityRunner.class)
public class PutVehicleTechnicalRecordsNeg {

    @Steps
    VehicleTechnicalRecordsSteps vehicleTechnicalRecordsSteps;

    @WithTag("Vtm")
    @Title("CVSB-7885 - AC8 - can not update an existing vehicle entry when the request body doesn't contain at least one element in the `techRecord` array")
    @Test
    public void testUpdateVehicleTechnicalRecordTechRecordEmptyArray() {
        // TEST SETUP
        // generate random Vin
        String randomVin = GenericData.generateRandomVin();
        //generate random Vrm
        String randomVrm = GenericData.generateRandomVrm();
        //read post request body from file
        String postRequestBody = GenericData.readJsonValueFromFile("technical-records_current.json","$");
        // create alteration make the techRecord array from the body request empty
        JsonPathAlteration emptyTechRecordArrayAlteration = new JsonPathAlteration("$.techRecord","[]","","REPLACE");
        // create alteration to change Vin in the request body with the random generated Vin
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin,"","REPLACE");
        // create alteration to change primary vrm in the request body with the random generated primary vrm
        JsonPathAlteration alterationVrm = new JsonPathAlteration("$.primaryVrm", randomVrm,"","REPLACE");
        // initialize the alterations list with only the alterations for changing the Vin and the primary vrm
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(alterationVin, alterationVrm));

        //TEST
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBody, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(201);
        // add alteration for setting the TechRecord array to be empty
        alterations.add(emptyTechRecordArrayAlteration);
        vehicleTechnicalRecordsSteps.putVehicleTechnicalRecordsForVehicleWithAlterations(randomVin, postRequestBody, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(400);
    }

    @WithTag("Vtm")
    @Title("CVSB-8677 - AC1 - Add adrDetails{} object onto an existing tech record with status current" +
            "AC2 - Update adrDetails{} object on an existing tech record")
    @Test
    public void testErrorForMissingMandatoryAdrFields() {
        // TEST SETUP
        // generate random Vin
        String randomVin = GenericData.generateRandomVin();
        //generate random Vrm
        String randomVrm = GenericData.generateRandomVrm();
        // read post request body from file
        String postRequestBodyHgv = GenericData.readJsonValueFromFile("technical-records_hgv.json","$");
        // read put request body from file for adding adr details
        String putRequestBodyAdrDetails = GenericData.readJsonValueFromFile("technical-records_adr_details_battery.json","$");
        // read the adr details from the file used for put request body with adr details
        String adrDetails = GenericData.readJsonValueFromFile("technical-records_adr_details_battery.json","$.techRecord[0].adrDetails");
        // create alteration to change Vin in the post request body with the random generated Vin
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin,"","REPLACE");
        // create alteration to change primary vrm in the request body with the random generated primary vrm
        JsonPathAlteration alterationVrm = new JsonPathAlteration("$.primaryVrm", randomVrm,"","REPLACE");
        // initialize the alterations list with only the alterations for changing the Vin and the primary vrm
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(alterationVin, alterationVrm));
        List<JsonPathAlteration> adrAlterations = new ArrayList<>(Arrays.asList());

        //TEST
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBodyHgv, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(201);
        // Validate that making PUT request without mandatory adr fields will give error
        // vehicleDetails.type
        JsonPathAlteration alterationDeleteAdrVehicleDetailsType = new JsonPathAlteration("$.techRecord[0].adrDetails.vehicleDetails.type", "","","DELETE");
        adrAlterations.add(alterationDeleteAdrVehicleDetailsType);
//        vehicleTechnicalRecordsSteps.putVehicleTechnicalRecordsForVehicleWithAlterations(randomVin, putRequestBodyAdrDetails, adrAlterations);
//        vehicleTechnicalRecordsSteps.statusCodeShouldBe(500);
        adrAlterations.clear();
        // vehicleDetails.approvalDate
        JsonPathAlteration alterationDeleteAdrVehicleDetailsApprovalDate = new JsonPathAlteration("$.techRecord[0].adrDetails.vehicleDetails.approvalDate", "","","DELETE");
        adrAlterations.add(alterationDeleteAdrVehicleDetailsApprovalDate);
        vehicleTechnicalRecordsSteps.putVehicleTechnicalRecordsForVehicleWithAlterations(randomVin, putRequestBodyAdrDetails, adrAlterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(500);
        adrAlterations.clear();
        // listStatementApplicable
        JsonPathAlteration alterationDeleteAdrListStatementApplicable = new JsonPathAlteration("$.techRecord[0].adrDetails.listStatementApplicable", "","","DELETE");
        adrAlterations.add(alterationDeleteAdrListStatementApplicable);
        vehicleTechnicalRecordsSteps.putVehicleTechnicalRecordsForVehicleWithAlterations(randomVin, putRequestBodyAdrDetails, adrAlterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(500);
        adrAlterations.clear();
        // batteryListNumber
        JsonPathAlteration alterationDeleteAdrBatteryListNumber = new JsonPathAlteration("$.techRecord[0].adrDetails.batteryListNumber", "","","DELETE");
        adrAlterations.add(alterationDeleteAdrBatteryListNumber);
        vehicleTechnicalRecordsSteps.putVehicleTechnicalRecordsForVehicleWithAlterations(randomVin, putRequestBodyAdrDetails, adrAlterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(500);
        adrAlterations.clear();
        // weight
        JsonPathAlteration alterationDeleteAdrWeight = new JsonPathAlteration("$.techRecord[0].adrDetails.weight", "","","DELETE");
        adrAlterations.add(alterationDeleteAdrWeight);
        vehicleTechnicalRecordsSteps.putVehicleTechnicalRecordsForVehicleWithAlterations(randomVin, putRequestBodyAdrDetails, adrAlterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(500);
        adrAlterations.clear();
        // permittedDangerousGoods
        JsonPathAlteration alterationDeleteAdrPermittedDangerousGoods = new JsonPathAlteration("$.techRecord[0].adrDetails.permittedDangerousGoods", "","","DELETE");
        adrAlterations.add(alterationDeleteAdrPermittedDangerousGoods);
        vehicleTechnicalRecordsSteps.putVehicleTechnicalRecordsForVehicleWithAlterations(randomVin, putRequestBodyAdrDetails, adrAlterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(500);
        adrAlterations.clear();
        // applicantDetails.name
        JsonPathAlteration alterationDeleteAdrApplicantDetailsName = new JsonPathAlteration("$.techRecord[0].adrDetails.applicantDetails.name", "","","DELETE");
        adrAlterations.add(alterationDeleteAdrApplicantDetailsName);
        vehicleTechnicalRecordsSteps.putVehicleTechnicalRecordsForVehicleWithAlterations(randomVin, putRequestBodyAdrDetails, adrAlterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(500);
        adrAlterations.clear();
        // applicantDetails.street
        JsonPathAlteration alterationDeleteAdrApplicantDetailsStreet = new JsonPathAlteration("$.techRecord[0].adrDetails.applicantDetails.street", "","","DELETE");
        adrAlterations.add(alterationDeleteAdrApplicantDetailsStreet);
        vehicleTechnicalRecordsSteps.putVehicleTechnicalRecordsForVehicleWithAlterations(randomVin, putRequestBodyAdrDetails, adrAlterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(500);
        adrAlterations.clear();
        // applicantDetails.town
        JsonPathAlteration alterationDeleteAdrApplicantDetailsTown = new JsonPathAlteration("$.techRecord[0].adrDetails.applicantDetails.town", "","","DELETE");
        adrAlterations.add(alterationDeleteAdrApplicantDetailsTown);
        vehicleTechnicalRecordsSteps.putVehicleTechnicalRecordsForVehicleWithAlterations(randomVin, putRequestBodyAdrDetails, adrAlterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(500);
        adrAlterations.clear();
        // applicantDetails.city
        JsonPathAlteration alterationDeleteAdrApplicantDetailsCity = new JsonPathAlteration("$.techRecord[0].adrDetails.applicantDetails.city", "","","DELETE");
        adrAlterations.add(alterationDeleteAdrApplicantDetailsCity);
        vehicleTechnicalRecordsSteps.putVehicleTechnicalRecordsForVehicleWithAlterations(randomVin, putRequestBodyAdrDetails, adrAlterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(500);
        adrAlterations.clear();
        // applicantDetails.postcode
        JsonPathAlteration alterationDeleteAdrApplicantDetailsPostcode = new JsonPathAlteration("$.techRecord[0].adrDetails.applicantDetails.postcode", "","","DELETE");
        adrAlterations.add(alterationDeleteAdrApplicantDetailsPostcode);
        vehicleTechnicalRecordsSteps.putVehicleTechnicalRecordsForVehicleWithAlterations(randomVin, putRequestBodyAdrDetails, adrAlterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(500);
        adrAlterations.clear();
        // tank.tankDetails.tankManufacturer
        JsonPathAlteration alterationDeleteAdrTankManufacturer = new JsonPathAlteration("$.techRecord[0].adrDetails.tank.tankDetails.tankManufacturer", "","","DELETE");
        adrAlterations.add(alterationDeleteAdrTankManufacturer);
        vehicleTechnicalRecordsSteps.putVehicleTechnicalRecordsForVehicleWithAlterations(randomVin, putRequestBodyAdrDetails, adrAlterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(500);
        adrAlterations.clear();
        // tank.tankDetails.yearOfManufacture
        JsonPathAlteration alterationDeleteAdrYearOfManufacture = new JsonPathAlteration("$.techRecord[0].adrDetails.tank.tankDetails.yearOfManufacture", "","","DELETE");
        adrAlterations.add(alterationDeleteAdrYearOfManufacture);
        vehicleTechnicalRecordsSteps.putVehicleTechnicalRecordsForVehicleWithAlterations(randomVin, putRequestBodyAdrDetails, adrAlterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(500);
        adrAlterations.clear();
        // tank.tankDetails.tankCode
        JsonPathAlteration alterationDeleteAdrTankCode = new JsonPathAlteration("$.techRecord[0].adrDetails.tank.tankDetails.tankCode", "","","DELETE");
        adrAlterations.add(alterationDeleteAdrTankCode);
        vehicleTechnicalRecordsSteps.putVehicleTechnicalRecordsForVehicleWithAlterations(randomVin, putRequestBodyAdrDetails, adrAlterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(500);
        adrAlterations.clear();
        // tank.tankDetails.tankManufacturerSerialNo
        JsonPathAlteration alterationDeleteAdrTankManufacturerSerialNo = new JsonPathAlteration("$.techRecord[0].adrDetails.tank.tankDetails.tankManufacturerSerialNo", "","","DELETE");
        adrAlterations.add(alterationDeleteAdrTankManufacturerSerialNo);
        vehicleTechnicalRecordsSteps.putVehicleTechnicalRecordsForVehicleWithAlterations(randomVin, putRequestBodyAdrDetails, adrAlterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(500);
        adrAlterations.clear();
        // tank.tankDetails.tankTypeAppNo
        JsonPathAlteration alterationDeleteAdrTankTypeAppNo = new JsonPathAlteration("$.techRecord[0].adrDetails.tank.tankDetails.tankTypeAppNo", "","","DELETE");
        adrAlterations.add(alterationDeleteAdrTankTypeAppNo);
        vehicleTechnicalRecordsSteps.putVehicleTechnicalRecordsForVehicleWithAlterations(randomVin, putRequestBodyAdrDetails, adrAlterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(500);
        adrAlterations.clear();
        // tank.tankStatement.substancesPermitted
        JsonPathAlteration alterationDeleteAdrTankStatementSubstancesPermitted = new JsonPathAlteration("$.techRecord[0].adrDetails.tank.tankStatement.substancesPermitted", "","","DELETE");
        adrAlterations.add(alterationDeleteAdrTankStatementSubstancesPermitted);
        vehicleTechnicalRecordsSteps.putVehicleTechnicalRecordsForVehicleWithAlterations(randomVin, putRequestBodyAdrDetails, adrAlterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(500);
        adrAlterations.clear();
    }

    @WithTag("Vtm")
    @Title("CVSB-8677 - AC1 - Add adrDetails{} object onto an existing tech record with status current" +
            "AC2 - Update adrDetails{} object on an existing tech record")
    @Test
    public void testErrorForNotApplicableAdrFields() {
        // TEST SETUP
        // generate random Vin
        String randomVin = GenericData.generateRandomVin();
        //generate random Vrm
        String randomVrm = GenericData.generateRandomVrm();
        // read post request body from file
        String postRequestBodyHgv = GenericData.readJsonValueFromFile("technical-records_hgv.json","$");
        // read put request body from file for adding adr details
        String putRequestBodyAdrDetails = GenericData.readJsonValueFromFile("technical-records_adr_details_battery.json","$");
        // read the adr details from the file used for put request body with adr details
        String adrDetails = GenericData.readJsonValueFromFile("technical-records_adr_details_battery.json","$.techRecord[0].adrDetails");
        // create alteration to change Vin in the post request body with the random generated Vin
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin,"","REPLACE");
        // create alteration to change primary vrm in the request body with the random generated primary vrm
        JsonPathAlteration alterationVrm = new JsonPathAlteration("$.primaryVrm", randomVrm,"","REPLACE");
        // initialize the alterations list with only the alterations for changing the Vin and the primary vrm
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(alterationVin, alterationVrm));


        //TEST
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBodyHgv, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(201);
        // validate that making PUT request with N/A adr fields will give error
        JsonPathAlteration alterationDeleteAdrTank = new JsonPathAlteration("$.techRecord[0].adrDetails.tank", "","","DELETE");
        JsonPathAlteration alterationDeleteAdrDocuments = new JsonPathAlteration("$.techRecord[0].adrDetails.documents", "","","DELETE");
        JsonPathAlteration alterationDeleteAdrListStatementApplicable = new JsonPathAlteration("$.techRecord[0].adrDetails.listStatementApplicable", "","","DELETE");
        JsonPathAlteration alterationDeleteAdrBatteryListNumber = new JsonPathAlteration("$.techRecord[0].adrDetails.batteryListNumber", "","","DELETE");
        JsonPathAlteration alterationDeleteAdrMemosApply = new JsonPathAlteration("$.techRecord[0].adrDetails.memosApply", "","","DELETE");
        JsonPathAlteration alterationChangeAdrVehicleTypeNonBatteryTank = new JsonPathAlteration("$.techRecord[0].adrDetails.vehicleDetails.type", "car","","REPLACE");
        List<JsonPathAlteration> adrAlterations = new ArrayList<>(Arrays.asList());
        // tank
        adrAlterations = new ArrayList<>(Arrays.asList(
                alterationDeleteAdrDocuments,
                alterationDeleteAdrListStatementApplicable,
                alterationDeleteAdrBatteryListNumber,
                alterationDeleteAdrMemosApply,
                alterationChangeAdrVehicleTypeNonBatteryTank));
        vehicleTechnicalRecordsSteps.putVehicleTechnicalRecordsForVehicleWithAlterations(randomVin, putRequestBodyAdrDetails, adrAlterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(500);
        // documents
        adrAlterations = Arrays.asList(
                alterationDeleteAdrTank,
                alterationDeleteAdrListStatementApplicable,
                alterationDeleteAdrBatteryListNumber,
                alterationDeleteAdrMemosApply,
                alterationChangeAdrVehicleTypeNonBatteryTank);
        vehicleTechnicalRecordsSteps.putVehicleTechnicalRecordsForVehicleWithAlterations(randomVin, putRequestBodyAdrDetails, adrAlterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(500);
        // listStatementApplicable
        adrAlterations = Arrays.asList(
                alterationDeleteAdrTank,
                alterationDeleteAdrDocuments,
                alterationDeleteAdrBatteryListNumber,
                alterationDeleteAdrMemosApply,
                alterationChangeAdrVehicleTypeNonBatteryTank);
        vehicleTechnicalRecordsSteps.putVehicleTechnicalRecordsForVehicleWithAlterations(randomVin, putRequestBodyAdrDetails, adrAlterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(500);
        // batteryListNumber
        adrAlterations = Arrays.asList(
                alterationDeleteAdrTank,
                alterationDeleteAdrDocuments,
                alterationDeleteAdrListStatementApplicable,
                alterationDeleteAdrMemosApply,
                alterationChangeAdrVehicleTypeNonBatteryTank);
        vehicleTechnicalRecordsSteps.putVehicleTechnicalRecordsForVehicleWithAlterations(randomVin, putRequestBodyAdrDetails, adrAlterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(500);
        // memosApply
        adrAlterations = Arrays.asList(
                alterationDeleteAdrTank,
                alterationDeleteAdrDocuments,
                alterationDeleteAdrListStatementApplicable,
                alterationDeleteAdrBatteryListNumber,
                alterationChangeAdrVehicleTypeNonBatteryTank);
        vehicleTechnicalRecordsSteps.putVehicleTechnicalRecordsForVehicleWithAlterations(randomVin, putRequestBodyAdrDetails, adrAlterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(500);
        // validate request return 200 when all not applicable fields are removed
        adrAlterations = Arrays.asList(
                alterationDeleteAdrTank,
                alterationDeleteAdrDocuments,
                alterationDeleteAdrListStatementApplicable,
                alterationDeleteAdrBatteryListNumber,
                alterationDeleteAdrMemosApply,
                alterationChangeAdrVehicleTypeNonBatteryTank);
        vehicleTechnicalRecordsSteps.putVehicleTechnicalRecordsForVehicleWithAlterations(randomVin, putRequestBodyAdrDetails, adrAlterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
    }
}
