package vehicle;


import data.GenericData;
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
public class PostVehicleTechnicalRecordsNeg {

    @Steps
    VehicleTechnicalRecordsSteps vehicleTechnicalRecordsSteps;

    @WithTag("Vtm")
    @Title("CVSB-7885 - AC2 - Can not create a new vehicle entry using an existing VIN from the DB" +
            "AC4 - can not create a new vehicle entry when the request body doesn't contain at least one element in the `techRecord` array")
    @Test
    public void testCreateVehicleTechnicalRecordExistingVinEmptyTechRecordArray() {
        // TEST SETUP
        // generate random Vin
        String randomVin = GenericData.generateRandomVin();
        //generate random Vrm
        String randomVrm = GenericData.generateRandomVrm();
        // read post request body from file
        String requestBody = GenericData.readJsonValueFromFile("technical-records_current.json","$");
        // create alteration make the techRecord array from the body request empty
        JsonPathAlteration emptyTechRecordArrayAlteration = new JsonPathAlteration("$.techRecord","[]","","REPLACE");
        // create alteration to change Vin in the request body with the random generated Vin
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin,"","REPLACE");
        // create alteration to change primary vrm in the request body with the random generated primary vrm
        JsonPathAlteration alterationVrm = new JsonPathAlteration("$.primaryVrm", randomVrm,"","REPLACE");
        // initialize the alterations list with only the alterations for changing the Vin and primay vrm
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(alterationVin, alterationVrm));

        // TEST
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(requestBody, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(201);
        // validate AC2
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(requestBody, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(400);
        //add in the alterations list the alteration for the empty techRecord array
        alterations.add(emptyTechRecordArrayAlteration);
        // validate AC4
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(requestBody, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(400);
    }

    @WithTag("Vtm")
    @Title("CVSB-9213 - adr field mandatory fields for vehicle type containing battery")
    @Test
    public void testValidateAdrMandatoryFieldsBattery() {
        // TEST SETUP
        // generate random Vin
        String randomVin = GenericData.generateRandomVin();
        // generate random Vrm
        String randomVrm = GenericData.generateRandomVrm();
        // read post request body from file
        String postRequestBodyHgv = GenericData.readJsonValueFromFile("technical-records_hgv.json", "$");
        // read put request body from file for adding battery adr details
        String putRequestBodyAdrDetailsBattery = GenericData.readJsonValueFromFile("technical-records_adr_details_battery.json", "$");
        // read the adr details from the file used for put request body with battery adr details
        String adrDetailsBattery = GenericData.readJsonValueFromFile("technical-records_adr_details_battery.json", "$.techRecord[0].adrDetails");
        String reasonForCreation = GenericData.readJsonValueFromFile("technical-records_adr_details_battery.json", "$.techRecord[0].reasonForCreation");
        // create alteration to change Vin in the post request body with the random generated Vin
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        // create alteration to change primary vrm in the request body with the random generated primary vrm
        JsonPathAlteration alterationVrm = new JsonPathAlteration("$.primaryVrm", randomVrm, "", "REPLACE");
        // create alteration add reasonForCreationField
        JsonPathAlteration alterationAddReasonForCreation = new JsonPathAlteration("$.techRecord[0]", reasonForCreation, "reasonForCreation", "ADD_FIELD");
        // create alteration to add adr details
        JsonPathAlteration alterationAddAdrDetails = new JsonPathAlteration("$.techRecord[0]", adrDetailsBattery, "adrDetails", "ADD_FIELD");

        // initialize the alterations list with both declared alterations
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(alterationVin, alterationVrm, alterationAddReasonForCreation, alterationAddAdrDetails));


        // validate mandatory adr fields
        // applicantDetails.name
        JsonPathAlteration applicantDetailsName = new JsonPathAlteration("$.techRecord[0].adrDetails.applicantDetails.name", "", "", "DELETE");
        alterations.add(applicantDetailsName);
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBodyHgv, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(400);
        // applicantDetails.street
        JsonPathAlteration applicantDetailsStreet = new JsonPathAlteration("$.techRecord[0].adrDetails.applicantDetails.street", "", "", "DELETE");
        alterations.set(alterations.size() - 1, applicantDetailsStreet);
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBodyHgv, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(400);
        // applicantDetails.town
        JsonPathAlteration applicantDetailsTown = new JsonPathAlteration("$.techRecord[0].adrDetails.applicantDetails.town", "", "", "DELETE");
        alterations.set(alterations.size() - 1, applicantDetailsTown);
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBodyHgv, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(400);
        // applicantDetails.city
        JsonPathAlteration applicantDetailsCity = new JsonPathAlteration("$.techRecord[0].adrDetails.applicantDetails.city", "", "", "DELETE");
        alterations.set(alterations.size() - 1, applicantDetailsCity);
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBodyHgv, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(400);
        // applicantDetails.postcode
        JsonPathAlteration applicantDetailsPostcode = new JsonPathAlteration("$.techRecord[0].adrDetails.applicantDetails.postcode", "", "", "DELETE");
        alterations.set(alterations.size() - 1, applicantDetailsPostcode);
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBodyHgv, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(400);
        // vehicleDetails.type
        JsonPathAlteration vehicleDetailsType = new JsonPathAlteration("$.techRecord[0].adrDetails.vehicleDetails.type", "", "", "DELETE");
        alterations.set(alterations.size() - 1, vehicleDetailsType);
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBodyHgv, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(400);
        // vehicleDetails.approvalDate
        JsonPathAlteration vehicleDetailsApprovalDate = new JsonPathAlteration("$.techRecord[0].adrDetails.vehicleDetails.approvalDate", "", "", "DELETE");
        alterations.set(alterations.size() - 1, vehicleDetailsApprovalDate);
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBodyHgv, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(400);
        // permittedDangerousGoods
        JsonPathAlteration permittedDangerousGoods = new JsonPathAlteration("$.techRecord[0].adrDetails.permittedDangerousGoods", "", "", "DELETE");
        alterations.set(alterations.size() - 1, permittedDangerousGoods);
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBodyHgv, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(400);
        // tankDetails.tankManufacturer
        JsonPathAlteration tankDetailsTankManufacturer = new JsonPathAlteration("$.techRecord[0].adrDetails.tank.tankDetails.tankManufacturer", "", "", "DELETE");
        alterations.set(alterations.size() - 1, tankDetailsTankManufacturer);
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBodyHgv, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(400);
        // tankDetails.yearOfManufacture
        JsonPathAlteration tankDetailsYearOfManufacture = new JsonPathAlteration("$.techRecord[0].adrDetails.tank.tankDetails.yearOfManufacture", "", "", "DELETE");
        alterations.set(alterations.size() - 1, tankDetailsYearOfManufacture);
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBodyHgv, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(400);
        // tankDetails.tankManufacturerSerialNo
        JsonPathAlteration tankManufacturerSerialNo = new JsonPathAlteration("$.techRecord[0].adrDetails.tank.tankDetails.tankManufacturerSerialNo", "", "", "DELETE");
        alterations.set(alterations.size() - 1, tankManufacturerSerialNo);
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBodyHgv, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(400);
        // tankDetails.tankTypeAppNo
        JsonPathAlteration tankTypeAppNo = new JsonPathAlteration("$.techRecord[0].adrDetails.tank.tankDetails.tankTypeAppNo", "", "", "DELETE");
        alterations.set(alterations.size() - 1, tankTypeAppNo);
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBodyHgv, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(400);
        // tankDetails.tankCode
        JsonPathAlteration tankCode = new JsonPathAlteration("$.techRecord[0].adrDetails.tank.tankDetails.tankCode", "", "", "DELETE");
        alterations.set(alterations.size() - 1, tankCode);
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBodyHgv, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(400);
        // tankStatement.substancesPermitted
        JsonPathAlteration substancesPermitted = new JsonPathAlteration("$.techRecord[0].adrDetails.tank.tankStatement.substancesPermitted", "", "", "DELETE");
        alterations.set(alterations.size() - 1, substancesPermitted);
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBodyHgv, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(400);
    }

    @WithTag("Vtm")
    @Title("CVSB-9213 - adr field mandatory fields for vehicle type containing tank")
    @Test
    public void testValidateAdrMandatoryFieldsTank() {
        // TEST SETUP
        // generate random Vin
        String randomVin = GenericData.generateRandomVin();
        // generate random Vrm
        String randomVrm = GenericData.generateRandomVrm();
        // read post request body from file
        String postRequestBodyHgv = GenericData.readJsonValueFromFile("technical-records_hgv.json", "$");
        // read put request body from file for adding battery adr details
        String putRequestBodyAdrDetailsBattery = GenericData.readJsonValueFromFile("technical-records_adr_details_tank.json", "$");
        // read the adr details from the file used for put request body with battery adr details
        String adrDetailsBattery = GenericData.readJsonValueFromFile("technical-records_adr_details_tank.json", "$.techRecord[0].adrDetails");
        String reasonForCreation = GenericData.readJsonValueFromFile("technical-records_adr_details_tank.json", "$.techRecord[0].reasonForCreation");
        // create alteration to change Vin in the post request body with the random generated Vin
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        // create alteration to change primary vrm in the request body with the random generated primary vrm
        JsonPathAlteration alterationVrm = new JsonPathAlteration("$.primaryVrm", randomVrm, "", "REPLACE");
        // create alteration add reasonForCreationField
        JsonPathAlteration alterationAddReasonForCreation = new JsonPathAlteration("$.techRecord[0]", reasonForCreation, "reasonForCreation", "ADD_FIELD");
        // create alteration to add adr details
        JsonPathAlteration alterationAddAdrDetails = new JsonPathAlteration("$.techRecord[0]", adrDetailsBattery, "adrDetails", "ADD_FIELD");

        // initialize the alterations list with both declared alterations
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(alterationVin, alterationVrm, alterationAddReasonForCreation, alterationAddAdrDetails));


        // validate mandatory adr fields
        // applicantDetails.name
        JsonPathAlteration applicantDetailsName = new JsonPathAlteration("$.techRecord[0].adrDetails.applicantDetails.name", "", "", "DELETE");
        alterations.add(applicantDetailsName);
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBodyHgv, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(400);
        // applicantDetails.street
        JsonPathAlteration applicantDetailsStreet = new JsonPathAlteration("$.techRecord[0].adrDetails.applicantDetails.street", "", "", "DELETE");
        alterations.set(alterations.size() - 1, applicantDetailsStreet);
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBodyHgv, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(400);
        // applicantDetails.town
        JsonPathAlteration applicantDetailsTown = new JsonPathAlteration("$.techRecord[0].adrDetails.applicantDetails.town", "", "", "DELETE");
        alterations.set(alterations.size() - 1, applicantDetailsTown);
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBodyHgv, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(400);
        // applicantDetails.city
        JsonPathAlteration applicantDetailsCity = new JsonPathAlteration("$.techRecord[0].adrDetails.applicantDetails.city", "", "", "DELETE");
        alterations.set(alterations.size() - 1, applicantDetailsCity);
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBodyHgv, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(400);
        // applicantDetails.postcode
        JsonPathAlteration applicantDetailsPostcode = new JsonPathAlteration("$.techRecord[0].adrDetails.applicantDetails.postcode", "", "", "DELETE");
        alterations.set(alterations.size() - 1, applicantDetailsPostcode);
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBodyHgv, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(400);
        // vehicleDetails.type
        JsonPathAlteration vehicleDetailsType = new JsonPathAlteration("$.techRecord[0].adrDetails.vehicleDetails.type", "", "", "DELETE");
        alterations.set(alterations.size() - 1, vehicleDetailsType);
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBodyHgv, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(400);
        // vehicleDetails.approvalDate
        JsonPathAlteration vehicleDetailsApprovalDate = new JsonPathAlteration("$.techRecord[0].adrDetails.vehicleDetails.approvalDate", "", "", "DELETE");
        alterations.set(alterations.size() - 1, vehicleDetailsApprovalDate);
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBodyHgv, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(400);
        // permittedDangerousGoods
        JsonPathAlteration permittedDangerousGoods = new JsonPathAlteration("$.techRecord[0].adrDetails.permittedDangerousGoods", "", "", "DELETE");
        alterations.set(alterations.size() - 1, permittedDangerousGoods);
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBodyHgv, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(400);
        // tankDetails.tankManufacturer
        JsonPathAlteration tankDetailsTankManufacturer = new JsonPathAlteration("$.techRecord[0].adrDetails.tank.tankDetails.tankManufacturer", "", "", "DELETE");
        alterations.set(alterations.size() - 1, tankDetailsTankManufacturer);
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBodyHgv, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(400);
        // tankDetails.yearOfManufacture
        JsonPathAlteration tankDetailsYearOfManufacture = new JsonPathAlteration("$.techRecord[0].adrDetails.tank.tankDetails.yearOfManufacture", "", "", "DELETE");
        alterations.set(alterations.size() - 1, tankDetailsYearOfManufacture);
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBodyHgv, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(400);
        // tankDetails.tankManufacturerSerialNo
        JsonPathAlteration tankManufacturerSerialNo = new JsonPathAlteration("$.techRecord[0].adrDetails.tank.tankDetails.tankManufacturerSerialNo", "", "", "DELETE");
        alterations.set(alterations.size() - 1, tankManufacturerSerialNo);
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBodyHgv, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(400);
        // tankDetails.tankTypeAppNo
        JsonPathAlteration tankTypeAppNo = new JsonPathAlteration("$.techRecord[0].adrDetails.tank.tankDetails.tankTypeAppNo", "", "", "DELETE");
        alterations.set(alterations.size() - 1, tankTypeAppNo);
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBodyHgv, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(400);
        // tankDetails.tankCode
        JsonPathAlteration tankCode = new JsonPathAlteration("$.techRecord[0].adrDetails.tank.tankDetails.tankCode", "", "", "DELETE");
        alterations.set(alterations.size() - 1, tankCode);
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBodyHgv, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(400);
        // tankStatement.substancesPermitted
        JsonPathAlteration substancesPermitted = new JsonPathAlteration("$.techRecord[0].adrDetails.tank.tankStatement.substancesPermitted", "", "", "DELETE");
        alterations.set(alterations.size() - 1, substancesPermitted);
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBodyHgv, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(400);
    }

    @WithTag("Vtm")
    @Title("CVSB-9213 - adr field data types and restrictions")
    @Test
    public void testValidateAdrAttributesDataTypesAndRestrictions() {
        // TEST SETUP
        // generate random Vin
        String randomVin = GenericData.generateRandomVin();
        // generate random Vrm
        String randomVrm = GenericData.generateRandomVrm();
        // read post request body from file
        String postRequestBodyHgv = GenericData.readJsonValueFromFile("technical-records_hgv.json", "$");
        // read put request body from file for adding battery adr details
        String putRequestBodyAdrDetailsBattery = GenericData.readJsonValueFromFile("technical-records_adr_details_battery.json", "$");
        // read the adr details from the file used for put request body with battery adr details
        String adrDetailsBattery = GenericData.readJsonValueFromFile("technical-records_adr_details_battery.json", "$.techRecord[0].adrDetails");
        String reasonForCreation = GenericData.readJsonValueFromFile("technical-records_adr_details_battery.json", "$.techRecord[0].reasonForCreation");
        // create alteration to change Vin in the post request body with the random generated Vin
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        // create alteration to change primary vrm in the request body with the random generated primary vrm
        JsonPathAlteration alterationVrm = new JsonPathAlteration("$.primaryVrm", randomVrm, "", "REPLACE");
        // create alteration add reasonForCreationField
        JsonPathAlteration alterationAddReasonForCreation = new JsonPathAlteration("$.techRecord[0]", reasonForCreation, "reasonForCreation", "ADD_FIELD");
        // create alteration to add adr details
        JsonPathAlteration alterationAddAdrDetails = new JsonPathAlteration("$.techRecord[0]", adrDetailsBattery, "adrDetails", "ADD_FIELD");

        // initialize the alterations list with both declared alterations
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(alterationVin, alterationVrm, alterationAddReasonForCreation, alterationAddAdrDetails));


        // validate each mandatory field
        // applicantDetails.name
        JsonPathAlteration applicantDetailsName = new JsonPathAlteration("$.techRecord[0].adrDetails.applicantDetails.name", RandomStringUtils.random(151, true, true), "", "REPLACE");
        alterations.add(applicantDetailsName);
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBodyHgv, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(400);
        // applicantDetails.street
        JsonPathAlteration applicantDetailsStreet = new JsonPathAlteration("$.techRecord[0].adrDetails.applicantDetails.street", RandomStringUtils.random(151, true, true), "", "REPLACE");
        alterations.set(alterations.size() - 1, applicantDetailsStreet);
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBodyHgv, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(400);
        // applicantDetails.town
        JsonPathAlteration applicantDetailsTown = new JsonPathAlteration("$.techRecord[0].adrDetails.applicantDetails.town", RandomStringUtils.random(151, true, true), "", "REPLACE");
        alterations.set(alterations.size() - 1, applicantDetailsTown);
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBodyHgv, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(400);
        // applicantDetails.city
        JsonPathAlteration applicantDetailsCity = new JsonPathAlteration("$.techRecord[0].adrDetails.applicantDetails.city", RandomStringUtils.random(151, true, true), "", "REPLACE");
        alterations.set(alterations.size() - 1, applicantDetailsCity);
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBodyHgv, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(400);
        // applicantDetails.postcode
        JsonPathAlteration applicantDetailsPostcode = new JsonPathAlteration("$.techRecord[0].adrDetails.applicantDetails.postcode", RandomStringUtils.random(26, true, true), "", "REPLACE");
        alterations.set(alterations.size() - 1, applicantDetailsPostcode);
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBodyHgv, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(400);
        // vehicleDetails.type
        JsonPathAlteration vehicleDetailsType = new JsonPathAlteration("$.techRecord[0].adrDetails.vehicleDetails.type", "randomString", "", "REPLACE");
        alterations.set(alterations.size() - 1, vehicleDetailsType);
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBodyHgv, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(400);
        // vehicleDetails.approvalDate
        JsonPathAlteration vehicleDetailsApprovalDate = new JsonPathAlteration("$.techRecord[0].adrDetails.vehicleDetails.approvalDate", "21/08/1987", "", "REPLACE");
        alterations.set(alterations.size() - 1, vehicleDetailsApprovalDate);
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBodyHgv, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(400);
        // permittedDangerousGoods
        JsonPathAlteration permittedDangerousGoods = new JsonPathAlteration("$.techRecord[0].adrDetails.permittedDangerousGoods", "randomString", "", "REPLACE");
        alterations.set(alterations.size() - 1, permittedDangerousGoods);
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBodyHgv, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(400);
        // compatibilityGroupJ
        JsonPathAlteration compatibilityGroupJ = new JsonPathAlteration("$.techRecord[0].adrDetails.compatibilityGroupJ", "notBoolean", "", "REPLACE");
        alterations.set(alterations.size() - 1, compatibilityGroupJ);
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBodyHgv, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(400);
        // additionalNotes.number[]
        JsonPathAlteration additionalNotesNumber = new JsonPathAlteration("$.techRecord[0].adrDetails.additionalNotes.number[0]", "random string", "", "REPLACE");
        alterations.set(alterations.size() - 1, additionalNotesNumber);
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBodyHgv, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(400);
        // adrTypeApprovalNo
        JsonPathAlteration adrTypeApprovalNo = new JsonPathAlteration("$.techRecord[0].adrDetails.adrTypeApprovalNo", 12345, "", "REPLACE");
        alterations.set(alterations.size() - 1, adrTypeApprovalNo);
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBodyHgv, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(400);
        // tankDetails.tankManufacturer
        JsonPathAlteration tankDetailsTankManufacturer = new JsonPathAlteration("$.techRecord[0].adrDetails.tank.tankDetails.tankManufacturer", RandomStringUtils.random(71, true, true), "", "REPLACE");
        alterations.set(alterations.size() - 1, tankDetailsTankManufacturer);
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBodyHgv, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(400);
        // tankDetails.yearOfManufacture
        JsonPathAlteration tankDetailsYearOfManufacture = new JsonPathAlteration("$.techRecord[0].adrDetails.tank.tankDetails.yearOfManufacture", 10000, "", "REPLACE");
        alterations.set(alterations.size() - 1, tankDetailsYearOfManufacture);
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBodyHgv, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(400);
        // tankDetails.tankManufacturerSerialNo
        JsonPathAlteration tankManufacturerSerialNo = new JsonPathAlteration("$.techRecord[0].adrDetails.tank.tankDetails.tankManufacturerSerialNo", RandomStringUtils.random(51, true, true), "", "REPLACE");
        alterations.set(alterations.size() - 1, tankManufacturerSerialNo);
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBodyHgv, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(400);
        // tankDetails.tankTypeAppNo
        JsonPathAlteration tankTypeAppNo = new JsonPathAlteration("$.techRecord[0].adrDetails.tank.tankDetails.tankTypeAppNo", RandomStringUtils.random(66, true, true), "", "REPLACE");
        alterations.set(alterations.size() - 1, tankTypeAppNo);
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBodyHgv, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(400);
        // tankDetails.tankCode
        JsonPathAlteration tankCode = new JsonPathAlteration("$.techRecord[0].adrDetails.tank.tankDetails.tankCode", RandomStringUtils.random(31, true, true), "", "REPLACE");
        alterations.set(alterations.size() - 1, tankCode);
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBodyHgv, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(400);
        // tankDetails.specialProvisions
        JsonPathAlteration specialProvisions = new JsonPathAlteration("$.techRecord[0].adrDetails.tank.tankDetails.specialProvisions", RandomStringUtils.random(1025, true, true), "", "REPLACE");
        alterations.set(alterations.size() - 1, specialProvisions);
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBodyHgv, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(400);
        // tankDetails.tc2Details.tc2Type
        JsonPathAlteration tc2Type = new JsonPathAlteration("$.techRecord[0].adrDetails.tank.tankDetails.tc2Details.tc2Type", "random string", "", "REPLACE");
        alterations.set(alterations.size() - 1, tc2Type);
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBodyHgv, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(400);
        // tankDetails.tc2Details.tc2IntermediateApprovalNo
        JsonPathAlteration tc2IntermediateApprovalNo = new JsonPathAlteration("$.techRecord[0].adrDetails.tank.tankDetails.tc2Details.tc2IntermediateApprovalNo", RandomStringUtils.random(71, true, true), "", "REPLACE");
        alterations.set(alterations.size() - 1, tankCode);
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBodyHgv, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(400);
        // tankDetails.tc2Details.tc2IntermediateExpiryDate
        JsonPathAlteration tc2IntermediateExpiryDate = new JsonPathAlteration("$.techRecord[0].adrDetails.tank.tankDetails.tc2Details.tc2IntermediateExpiryDate", "21/08/2087", "", "REPLACE");
        alterations.set(alterations.size() - 1, tc2IntermediateExpiryDate);
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBodyHgv, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(400);
        // tankDetails.tc3Details[].tc3Type
        JsonPathAlteration tc3Type = new JsonPathAlteration("$.techRecord[0].adrDetails.tank.tankDetails.tc3Details[0].tc3Type", "random string", "", "REPLACE");
        alterations.set(alterations.size() - 1, tankCode);
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBodyHgv, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(400);
        // tankDetails.tc3Details[].tc3PeriodicNumber
        JsonPathAlteration tc3PeriodicNumber = new JsonPathAlteration("$.techRecord[0].adrDetails.tank.tankDetails.tc3Details[0].tc3PeriodicNumber", RandomStringUtils.random(76, true, true), "", "REPLACE");
        alterations.set(alterations.size() - 1, tc3PeriodicNumber);
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBodyHgv, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(400);
        // tankDetails.tc3Details[].tc3PeriodicExpiryDate
        JsonPathAlteration tc3PeriodicExpiryDate = new JsonPathAlteration("$.techRecord[0].adrDetails.tank.tankDetails.tc3Details[0].tc3PeriodicExpiryDate", "21/08/2087", "", "REPLACE");
        alterations.set(alterations.size() - 1, tc3PeriodicExpiryDate);
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBodyHgv, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(400);
        // tankStatement.substancesPermitted
        JsonPathAlteration substancesPermitted = new JsonPathAlteration("$.techRecord[0].adrDetails.tank.tankStatement.substancesPermitted", "random string", "", "REPLACE");
        alterations.set(alterations.size() - 1, substancesPermitted);
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBodyHgv, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(400);
        // tankStatement.statement
        JsonPathAlteration statement = new JsonPathAlteration("$.techRecord[0].adrDetails.tank.tankStatement", RandomStringUtils.random(1501, true, true), "statement", "ADD_FIELD");
        alterations.set(alterations.size() - 1, statement);
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBodyHgv, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(400);
        // tankStatement.productListRefNo
        JsonPathAlteration productListRefNo = new JsonPathAlteration("$.techRecord[0].adrDetails.tank.tankStatement.productListRefNo", 123, "", "REPLACE");
        alterations.set(alterations.size() - 1, productListRefNo);
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBodyHgv, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(400);
        // tankStatement.productListUnNo[]
        JsonPathAlteration productListUnNo = new JsonPathAlteration("$.techRecord[0].adrDetails.tank.tankStatement.productListUnNo[0]", 123, "", "REPLACE");
        alterations.set(alterations.size() - 1, productListUnNo);
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBodyHgv, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(400);
        // tankStatement.productList
        JsonPathAlteration productList = new JsonPathAlteration("$.techRecord[0].adrDetails.tank.tankStatement.productList", RandomStringUtils.random(1501, true, true), "", "REPLACE");
        alterations.set(alterations.size() - 1, productList);
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBodyHgv, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(400);
        // memosApply[]
        JsonPathAlteration memosApply = new JsonPathAlteration("$.techRecord[0].adrDetails.memosApply[0]", "random string", "", "REPLACE");
        alterations.set(alterations.size() - 1, memosApply);
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBodyHgv, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(400);
        // documents[]
        JsonPathAlteration documents = new JsonPathAlteration("$.techRecord[0].adrDetails.documents[0]", 123, "", "REPLACE");
        alterations.set(alterations.size() - 1, documents);
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBodyHgv, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(400);
        // listStatementApplicable
        JsonPathAlteration listStatementApplicable = new JsonPathAlteration("$.techRecord[0].adrDetails.listStatementApplicable", "non boolean", "", "REPLACE");
        alterations.set(alterations.size() - 1, listStatementApplicable);
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBodyHgv, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(400);
        // batteryListNumber
        JsonPathAlteration batteryListNumber = new JsonPathAlteration("$.techRecord[0].adrDetails.batteryListNumber", RandomStringUtils.random(9, true, true), "", "REPLACE");
        alterations.set(alterations.size() - 1, batteryListNumber);
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBodyHgv, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(400);
        // brakeDeclarationsSeen
        JsonPathAlteration brakeDeclarationsSeen = new JsonPathAlteration("$.techRecord[0].adrDetails.brakeDeclarationsSeen", "non boolean", "", "REPLACE");
        alterations.set(alterations.size() - 1, brakeDeclarationsSeen);
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBodyHgv, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(400);
        // brakeDeclarationIssuer
        JsonPathAlteration brakeDeclarationIssuer = new JsonPathAlteration("$.techRecord[0].adrDetails.brakeDeclarationIssuer", 123, "", "REPLACE");
        alterations.set(alterations.size() - 1, brakeDeclarationIssuer);
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBodyHgv, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(400);
        // brakeEndurance
        JsonPathAlteration brakeEndurance = new JsonPathAlteration("$.techRecord[0].adrDetails.brakeEndurance", "non boolean", "", "REPLACE");
        alterations.set(alterations.size() - 1, brakeEndurance);
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBodyHgv, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(400);
        // weight
        JsonPathAlteration weight = new JsonPathAlteration("$.techRecord[0].adrDetails.weight", RandomStringUtils.random(9, true, true), "", "REPLACE");
        alterations.set(alterations.size() - 1, weight);
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBodyHgv, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(400);
        // declarationsSeen
        JsonPathAlteration declarationsSeen = new JsonPathAlteration("$.techRecord[0].adrDetails.declarationsSeen", "non boolean", "", "REPLACE");
        alterations.set(alterations.size() - 1, declarationsSeen);
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBodyHgv, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(400);
        // additionalNotes.guidanceNotes[]
        JsonPathAlteration guidanceNotes = new JsonPathAlteration("$.techRecord[0].adrDetails.additionalNotes.guidanceNotes[0]", "random string", "", "REPLACE");
        alterations.set(alterations.size() - 1, guidanceNotes);
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBodyHgv, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(400);
        // additionalExaminerNotes
        JsonPathAlteration additionalExaminerNotes = new JsonPathAlteration("$.techRecord[0].adrDetails.additionalExaminerNotes", RandomStringUtils.random(1025, true, true), "", "REPLACE");
        alterations.set(alterations.size() - 1, additionalExaminerNotes);
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBodyHgv, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(400);
    }



}
