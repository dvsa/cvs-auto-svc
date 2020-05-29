package vehicle;

import data.GenericData;
import io.restassured.response.Response;
import model.vehicles.VehicleTechnicalRecordStatus;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import net.thucydides.core.annotations.WithTag;
import org.apache.commons.collections.ListUtils;
import org.apache.http.HttpStatus;
import org.junit.Assert;
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
        String postRequestBody = GenericData.readJsonValueFromFile("technical-records_hgv_all_fields.json","$");
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
    @Title("CVSB-8677 - AC6 - Add or update adrDetails{} object onto an existing tech record without a mandatory adr field")
    @Test
    public void testErrorForMissingMandatoryAdrFields() {
        // TEST SETUP
        // generate random Vin
        String randomVin = GenericData.generateRandomVin();
        //generate random Vrm
        String randomVrm = GenericData.generateRandomVrm();

        // read post request body from file
        String requestBodyHgv = GenericData.readJsonValueFromFile("technical-records_hgv_all_fields.json","$");
        // read the adr details from the file used for put request body with adr details
        String adrDetails = GenericData.readJsonValueFromFile("technical-records_adr_details_battery.json","$.techRecord[0].adrDetails");
        // create alteration to change Vin in the post request body with the random generated Vin
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin,"","REPLACE");
        // create alteration to change primary vrm in the request body with the random generated primary vrm
        JsonPathAlteration alterationVrm = new JsonPathAlteration("$.primaryVrm", randomVrm,"","REPLACE");
        // initialize the alterations list with only the alterations for changing the Vin and the primary vrm
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(alterationVin, alterationVrm));

        //TEST
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(requestBodyHgv, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(201);
        //generate random System Number
        String randomSysNo = vehicleTechnicalRecordsSteps.getSystemNumberUsingVin(randomVin);
        // PUT the update record.
        String putRequestBodyHgv = GenericData.readJsonValueFromFile("technical-records_hgv_all_fields_put.json", "$");
        // Validate that making PUT request without mandatory adr fields will give error
        // create alteration to change Vin in the post request body with the random generated System Number
        JsonPathAlteration alterationSysNo = new JsonPathAlteration("$.systemNumber", randomSysNo,"","REPLACE");
        JsonPathAlteration alterationAddAdrDetails = new JsonPathAlteration("$.techRecord[0]", adrDetails,"adrDetails","ADD_FIELD");
        alterations.add(alterationSysNo);
        alterations.add(alterationAddAdrDetails);

        JsonPathAlteration alterationDeleteAdrVehicleDetailsType = new JsonPathAlteration("$.techRecord[0].adrDetails.vehicleDetails.type", "","","DELETE");
        alterations.add(alterationDeleteAdrVehicleDetailsType);
        vehicleTechnicalRecordsSteps.putVehicleTechnicalRecordsForVehicleWithAlterations(randomSysNo, putRequestBodyHgv, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(400);
        // vehicleDetails.approvalDate
        JsonPathAlteration alterationDeleteAdrVehicleDetailsApprovalDate = new JsonPathAlteration("$.techRecord[0].adrDetails.vehicleDetails.approvalDate", "","","DELETE");
        alterations.remove(alterations.size()-1);
        alterations.add(alterationDeleteAdrVehicleDetailsApprovalDate);
        vehicleTechnicalRecordsSteps.putVehicleTechnicalRecordsForVehicleWithAlterations(randomSysNo, putRequestBodyHgv, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(400);
        // listStatementApplicable
        JsonPathAlteration alterationDeleteAdrListStatementApplicable = new JsonPathAlteration("$.techRecord[0].adrDetails.listStatementApplicable", "","","DELETE");
        alterations.remove(alterations.size()-1);
        alterations.add(alterationDeleteAdrListStatementApplicable);
        vehicleTechnicalRecordsSteps.putVehicleTechnicalRecordsForVehicleWithAlterations(randomSysNo, putRequestBodyHgv, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(400);
        // batteryListNumber
        JsonPathAlteration alterationDeleteAdrBatteryListNumber = new JsonPathAlteration("$.techRecord[0].adrDetails.batteryListNumber", "","","DELETE");
        alterations.remove(alterations.size()-1);
        alterations.add(alterationDeleteAdrBatteryListNumber);
        vehicleTechnicalRecordsSteps.putVehicleTechnicalRecordsForVehicleWithAlterations(randomSysNo, putRequestBodyHgv, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(400);
        // weight
        JsonPathAlteration alterationDeleteAdrWeight = new JsonPathAlteration("$.techRecord[0].adrDetails.weight", "","","DELETE");
        alterations.remove(alterations.size()-1);
        alterations.add(alterationDeleteAdrWeight);
        vehicleTechnicalRecordsSteps.putVehicleTechnicalRecordsForVehicleWithAlterations(randomSysNo, putRequestBodyHgv, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(400);
        // permittedDangerousGoods
        JsonPathAlteration alterationDeleteAdrPermittedDangerousGoods = new JsonPathAlteration("$.techRecord[0].adrDetails.permittedDangerousGoods", "","","DELETE");
        alterations.remove(alterations.size()-1);
        alterations.add(alterationDeleteAdrPermittedDangerousGoods);
        vehicleTechnicalRecordsSteps.putVehicleTechnicalRecordsForVehicleWithAlterations(randomSysNo, putRequestBodyHgv, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(400);
        // applicantDetails.name
        JsonPathAlteration alterationDeleteAdrApplicantDetailsName = new JsonPathAlteration("$.techRecord[0].adrDetails.applicantDetails.name", "","","DELETE");
        alterations.remove(alterations.size()-1);
        alterations.add(alterationDeleteAdrApplicantDetailsName);
        vehicleTechnicalRecordsSteps.putVehicleTechnicalRecordsForVehicleWithAlterations(randomSysNo, putRequestBodyHgv, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(400);
        // applicantDetails.street
        JsonPathAlteration alterationDeleteAdrApplicantDetailsStreet = new JsonPathAlteration("$.techRecord[0].adrDetails.applicantDetails.street", "","","DELETE");
        alterations.remove(alterations.size()-1);
        alterations.add(alterationDeleteAdrApplicantDetailsStreet);
        vehicleTechnicalRecordsSteps.putVehicleTechnicalRecordsForVehicleWithAlterations(randomSysNo, putRequestBodyHgv, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(400);
        // applicantDetails.town
        JsonPathAlteration alterationDeleteAdrApplicantDetailsTown = new JsonPathAlteration("$.techRecord[0].adrDetails.applicantDetails.town", "","","DELETE");
        alterations.remove(alterations.size()-1);
        alterations.add(alterationDeleteAdrApplicantDetailsTown);
        vehicleTechnicalRecordsSteps.putVehicleTechnicalRecordsForVehicleWithAlterations(randomSysNo, putRequestBodyHgv, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(400);
        // applicantDetails.city
        JsonPathAlteration alterationDeleteAdrApplicantDetailsCity = new JsonPathAlteration("$.techRecord[0].adrDetails.applicantDetails.city", "","","DELETE");
        alterations.remove(alterations.size()-1);
        alterations.add(alterationDeleteAdrApplicantDetailsCity);
        vehicleTechnicalRecordsSteps.putVehicleTechnicalRecordsForVehicleWithAlterations(randomSysNo, putRequestBodyHgv, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(400);
        // applicantDetails.postcode
        JsonPathAlteration alterationDeleteAdrApplicantDetailsPostcode = new JsonPathAlteration("$.techRecord[0].adrDetails.applicantDetails.postcode", "","","DELETE");
        alterations.remove(alterations.size()-1);
        alterations.add(alterationDeleteAdrApplicantDetailsPostcode);
        vehicleTechnicalRecordsSteps.putVehicleTechnicalRecordsForVehicleWithAlterations(randomSysNo, putRequestBodyHgv, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(400);
        // tank.tankDetails.tankManufacturer
        JsonPathAlteration alterationDeleteAdrTankManufacturer = new JsonPathAlteration("$.techRecord[0].adrDetails.tank.tankDetails.tankManufacturer", "","","DELETE");
        alterations.remove(alterations.size()-1);
        alterations.add(alterationDeleteAdrTankManufacturer);
        vehicleTechnicalRecordsSteps.putVehicleTechnicalRecordsForVehicleWithAlterations(randomSysNo, putRequestBodyHgv, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(400);
        // tank.tankStatement.substancesPermitted
        JsonPathAlteration alterationDeleteAdrTankStatementSubstancesPermitted = new JsonPathAlteration("$.techRecord[0].adrDetails.tank.tankStatement.substancesPermitted", "","","DELETE");
        alterations.remove(alterations.size()-1);
        alterations.add(alterationDeleteAdrTankStatementSubstancesPermitted);
        vehicleTechnicalRecordsSteps.putVehicleTechnicalRecordsForVehicleWithAlterations(randomSysNo, putRequestBodyHgv, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(400);
        // tank.tankDetails.yearOfManufacture
        JsonPathAlteration alterationDeleteAdrYearOfManufacture = new JsonPathAlteration("$.techRecord[0].adrDetails.tank.tankDetails.yearOfManufacture", "","","DELETE");
        alterations.remove(alterations.size()-1);
        alterations.add(alterationDeleteAdrYearOfManufacture);
        vehicleTechnicalRecordsSteps.putVehicleTechnicalRecordsForVehicleWithAlterations(randomSysNo, putRequestBodyHgv, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(400);
        // tank.tankDetails.tankCode
        JsonPathAlteration alterationDeleteAdrTankCode = new JsonPathAlteration("$.techRecord[0].adrDetails.tank.tankDetails.tankCode", "","","DELETE");
        alterations.remove(alterations.size()-1);
        alterations.add(alterationDeleteAdrTankCode);
        vehicleTechnicalRecordsSteps.putVehicleTechnicalRecordsForVehicleWithAlterations(randomSysNo, putRequestBodyHgv, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(400);
        // tank.tankDetails.tankManufacturerSerialNo
        JsonPathAlteration alterationDeleteAdrTankManufacturerSerialNo = new JsonPathAlteration("$.techRecord[0].adrDetails.tank.tankDetails.tankManufacturerSerialNo", "","","DELETE");
        alterations.remove(alterations.size()-1);
        alterations.add(alterationDeleteAdrTankManufacturerSerialNo);
        vehicleTechnicalRecordsSteps.putVehicleTechnicalRecordsForVehicleWithAlterations(randomSysNo, putRequestBodyHgv, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(400);


    }

    @WithTag("Vtm")
    @Title("CVSB-8677 - AC7 - Add or update adrDetails{} object onto an existing tech record with at least one not applicable field")
    @Test
    public void testErrorForNotApplicableAdrFields() {
        // TEST SETUP
        // generate random Vin
        String randomVin = GenericData.generateRandomVin();
        //generate random Vrm
        String randomVrm = GenericData.generateRandomVrm();
        // read post request body from file
        String requestBodyHgv = GenericData.readJsonValueFromFile("technical-records_hgv_all_fields.json","$");
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
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(requestBodyHgv, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(201);
        String systemNumber = vehicleTechnicalRecordsSteps.getSystemNumberUsingVin(randomVin);
        JsonPathAlteration alterationStatusCode = new JsonPathAlteration("$.techRecord[0]", "provisional", "statusCode", "ADD_FIELD");

        // validate that making PUT request with N/A adr fields will give error
        JsonPathAlteration alterationAddAdrDetails = new JsonPathAlteration("$.techRecord[0]", adrDetails,"adrDetails","ADD_FIELD");
        alterations.add(alterationAddAdrDetails);
        JsonPathAlteration alterationDeleteAdrTank = new JsonPathAlteration("$.techRecord[0].adrDetails.tank", "","","DELETE");
        JsonPathAlteration alterationDeleteAdrDocuments = new JsonPathAlteration("$.techRecord[0].adrDetails.documents", "","","DELETE");
        JsonPathAlteration alterationDeleteAdrListStatementApplicable = new JsonPathAlteration("$.techRecord[0].adrDetails.listStatementApplicable", "","","DELETE");
        JsonPathAlteration alterationDeleteAdrBatteryListNumber = new JsonPathAlteration("$.techRecord[0].adrDetails.batteryListNumber", "","","DELETE");
        JsonPathAlteration alterationDeleteAdrMemosApply = new JsonPathAlteration("$.techRecord[0].adrDetails.memosApply", "","","DELETE");
        JsonPathAlteration alterationChangeAdrVehicleTypeNonBatteryTank = new JsonPathAlteration("$.techRecord[0].adrDetails.vehicleDetails.type", "car","","REPLACE");

        // tank
        List<JsonPathAlteration> adrAlterations = new ArrayList<>(Arrays.asList(
                alterationDeleteAdrDocuments,
                alterationDeleteAdrListStatementApplicable,
                alterationDeleteAdrBatteryListNumber,
                alterationDeleteAdrMemosApply,
                alterationChangeAdrVehicleTypeNonBatteryTank,
                alterationStatusCode));
        alterations.addAll(adrAlterations);
        vehicleTechnicalRecordsSteps.putVehicleTechnicalRecordsForVehicleWithAlterations(systemNumber, requestBodyHgv, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(400);
//        // documents
//        alterations = new ArrayList<>(Arrays.asList(alterationVin, alterationVrm, alterationAddAdrDetails));
//        adrAlterations = new ArrayList<>(Arrays.asList(
//                alterationDeleteAdrTank,
//                alterationDeleteAdrListStatementApplicable,
//                alterationDeleteAdrBatteryListNumber,
//                alterationDeleteAdrMemosApply,
//                alterationChangeAdrVehicleTypeNonBatteryTank));
//        alterations.addAll(adrAlterations);
//        vehicleTechnicalRecordsSteps.putVehicleTechnicalRecordsForVehicleWithAlterations(randomVin, requestBodyHgv, alterations);
//        vehicleTechnicalRecordsSteps.statusCodeShouldBe(400);
        // listStatementApplicable
        alterations = new ArrayList<>(Arrays.asList(alterationVin, alterationVrm, alterationAddAdrDetails));
        adrAlterations = Arrays.asList(
                alterationDeleteAdrTank,
                alterationDeleteAdrBatteryListNumber,
                alterationChangeAdrVehicleTypeNonBatteryTank,
                alterationStatusCode);
        alterations.addAll(adrAlterations);
        vehicleTechnicalRecordsSteps.putVehicleTechnicalRecordsForVehicleWithAlterations(systemNumber, putRequestBodyAdrDetails, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(400);
        // batteryListNumber
        alterations = new ArrayList<>(Arrays.asList(alterationVin, alterationVrm, alterationAddAdrDetails));
        adrAlterations = Arrays.asList(
                alterationDeleteAdrTank,
                alterationDeleteAdrListStatementApplicable,
                alterationChangeAdrVehicleTypeNonBatteryTank,
                alterationStatusCode);
        alterations.addAll(adrAlterations);
        vehicleTechnicalRecordsSteps.putVehicleTechnicalRecordsForVehicleWithAlterations(systemNumber, requestBodyHgv, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(400);
//        // memosApply
//        alterations = new ArrayList<>(Arrays.asList(alterationVin, alterationVrm, alterationAddAdrDetails));
//        adrAlterations = Arrays.asList(
//                alterationDeleteAdrTank,
//                alterationDeleteAdrDocuments,
//                alterationDeleteAdrListStatementApplicable,
//                alterationDeleteAdrBatteryListNumber,
//                alterationChangeAdrVehicleTypeNonBatteryTank);
//        alterations.addAll(adrAlterations);
//        vehicleTechnicalRecordsSteps.putVehicleTechnicalRecordsForVehicleWithAlterations(randomVin, requestBodyHgv, alterations);
//        vehicleTechnicalRecordsSteps.statusCodeShouldBe(400);
        // validate request return 200 when all not applicable fields are removed
        alterations = new ArrayList<>(Arrays.asList(alterationVin, alterationVrm, alterationAddAdrDetails));
        adrAlterations = Arrays.asList(
                alterationDeleteAdrTank,
                alterationDeleteAdrListStatementApplicable,
                alterationDeleteAdrBatteryListNumber,
                alterationChangeAdrVehicleTypeNonBatteryTank,
                alterationStatusCode);
        alterations.addAll(adrAlterations);
        vehicleTechnicalRecordsSteps.putVehicleTechnicalRecordsForVehicleWithAlterations(systemNumber, requestBodyHgv, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
    }

    @WithTag("Vtm")
    @Title("CVSB-8677 - AC8 - Add or update adrDetails{} object onto an existing tech record with random values for a field that accepts only specific values")
    @Test
    public void testErrorForNotAcceptedValuesAdrField() {
        // TEST SETUP
        // generate random Vin
        String randomVin = GenericData.generateRandomVin();
        //generate random Vrm
        String randomVrm = GenericData.generateRandomVrm();
        // read post request body from file
        String requestBodyHgv = GenericData.readJsonValueFromFile("technical-records_hgv_all_fields.json","$");
        // read the adr details from the file used for put request body with adr details
        String adrDetails = GenericData.readJsonValueFromFile("technical-records_adr_details_battery.json","$.techRecord[0].adrDetails");
        // create alteration to change Vin in the post request body with the random generated Vin
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin,"","REPLACE");
        // create alteration to change primary vrm in the request body with the random generated primary vrm
        JsonPathAlteration alterationVrm = new JsonPathAlteration("$.primaryVrm", randomVrm,"","REPLACE");
        // initialize the alterations list with only the alterations for changing the Vin and the primary vrm
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(alterationVin, alterationVrm));


        //TEST
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(requestBodyHgv, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(201);
        JsonPathAlteration alterationAddAdrDetails = new JsonPathAlteration("$.techRecord[0]", adrDetails,"adrDetails","ADD_FIELD");
        alterations.add(alterationAddAdrDetails);
        // validate that making PUT request with N/A adr fields will give error
        JsonPathAlteration alterationRandomTc2Type = new JsonPathAlteration("$.techRecord[0].adrDetails.tank.tankDetails.tc2Details.tc2Type", "random","","REPLACE");
        JsonPathAlteration alterationRandomTc3Type = new JsonPathAlteration("$.techRecord[0].adrDetails.tank.tankDetails.tc3Details[0].tc3Type", "random","","REPLACE");

        // tc2Type
        alterations.add(alterationRandomTc2Type);
        vehicleTechnicalRecordsSteps.putVehicleTechnicalRecordsForVehicleWithAlterations(randomVin, requestBodyHgv, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(400);
        // tc3Type
        alterations.remove(alterations.size()-1);
        alterations.add(alterationRandomTc3Type);
        vehicleTechnicalRecordsSteps.putVehicleTechnicalRecordsForVehicleWithAlterations(randomVin, requestBodyHgv, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(400);
    }

    @Ignore("Remove the ignore annotation when the download and upload backend enpoints are re-implemented")
    @WithTag("Vtm")
    @Title("CVSB-9657 - AC2 - Error is given when making GET request with invalid file name")
    public void testErrorForDownloadingRandomFile() {
        //TEST SETUP
        // generate random Vin
        String randomVin = GenericData.generateRandomVin();
        //generate random Vrm
        String randomVrm = GenericData.generateRandomVrm();
        // read post request body from file
        String requestBodyHgv = GenericData.readJsonValueFromFile("technical-records_hgv_all_fields.json","$");
        // read the adr details from the file used for put request body with battery adr details
        String adrDetails = GenericData.readJsonValueFromFile("technical-records_adr_details_battery.json","$.techRecord[0].adrDetails");
        // create alteration to change Vin in the post request body with the random generated Vin
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin,"","REPLACE");
        // create alteration to change primary vrm in the request body with the random generated primary vrm
        JsonPathAlteration alterationVrm = new JsonPathAlteration("$.primaryVrm", randomVrm,"","REPLACE");
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(alterationVin, alterationVrm));
        // get pdf content as base64 encoded string
        String encodedFileContent = GenericData.readBytesFromFile("sample.pdf");
        // create alteration to add files field in the request body as array with an element the previously encoded string
        JsonPathAlteration alterationAddFiles = new JsonPathAlteration("$","[" + encodedFileContent + "]","files","ADD_FIELD");
//        List<JsonPathAlteration> alterationsAdrFiles = new ArrayList<>(Arrays.asList(alterationAddFiles));

        //TEST
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(requestBodyHgv, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(201);
        JsonPathAlteration alterationAddAdrDetails = new JsonPathAlteration("$.techRecord[0]", adrDetails,"adrDetails","ADD_FIELD");
        alterations.add(alterationAddAdrDetails);
        alterations.add(alterationAddFiles);
        vehicleTechnicalRecordsSteps.putVehicleTechnicalRecordsForVehicleWithAlterations(randomVin, requestBodyHgv, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        Response downloadFileResponse = vehicleTechnicalRecordsSteps.downloadFile(randomVin, "bla-bla.txt");
        String downloadedFileContent = downloadFileResponse.asString().substring(1, downloadFileResponse.asString().length()-1);
        Assert.assertEquals(500, downloadFileResponse.getStatusCode());
        Assert.assertEquals("Cannot download document from S3", downloadedFileContent);
    }

    @WithTag("Vtm")
    @Title("CVSB-10211 - AC2 - Attempt to update a vehicle with a not applicable field")
    @Test
    public void testValidateRequestNotApplicableHgvField() {
        // TEST SETUP
        // generate random Vin
        String randomVin = GenericData.generateRandomVin();
        // generate random Vrm
        String randomVrm = GenericData.generateRandomVrm();
        // read post request body from file
        String requestBodyHgv = GenericData.readJsonValueFromFile("technical-records_hgv_all_fields.json", "$");
        // create alteration to change Vin in the post request body with the random generated Vin
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        // create alteration to change primary vrm in the request body with the random generated primary vrm
        JsonPathAlteration alterationVrm = new JsonPathAlteration("$.primaryVrm", randomVrm, "", "REPLACE");

        // initialize the alterations list with both declared alterations
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(alterationVin, alterationVrm));

        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(requestBodyHgv, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(201);

        // validate 400 response when making PUT request with a not applicable field
        JsonPathAlteration notApplicableField = new JsonPathAlteration("$.techRecord[0]", "1234", "brakeCode", "ADD_FIELD");
        alterations.add(notApplicableField);
        vehicleTechnicalRecordsSteps.putVehicleTechnicalRecordsForVehicleWithAlterations(randomVin, requestBodyHgv, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(400);
    }

    @WithTag("Vtm")
    @Title("CVSB-10242 - AC2 - Attempt to update a vehicle with a not applicable field")
    @Test
    public void testValidateRequestNotApplicablePsvField() {
        // TEST SETUP
        // generate random Vin
        String randomVin = GenericData.generateRandomVin();
        // generate random systemNumber
        String randomSystemNumber = GenericData.generateRandomSystemNumber();
        // generate random Vrm
        String randomVrm = GenericData.generateRandomVrm();
        // read post request body from file
        String requestBodyPsv = GenericData.readJsonValueFromFile("technical-records_psv_all_fields.json", "$");
        // create alteration to change Vin in the post request body with the random generated Vin
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        // create alteration to change systemNumber in the post request body with the random generated Vin
        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$.systemNumber", randomSystemNumber, "", "REPLACE");
        // create alteration to change primary vrm in the request body with the random generated primary vrm
        JsonPathAlteration alterationVrm = new JsonPathAlteration("$.primaryVrm", randomVrm, "", "REPLACE");

        // initialize the alterations list with both declared alterations
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(alterationVin, alterationVrm, alterationSystemNumber));

        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(requestBodyPsv, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(201);

        // validate 400 response when making PUT request with a not applicable field
        JsonPathAlteration notApplicableField = new JsonPathAlteration("$.techRecord[0]", true, "roadFriendly", "ADD_FIELD");
        alterations.add(notApplicableField);
        vehicleTechnicalRecordsSteps.putVehicleTechnicalRecordsForVehicleWithAlterations(randomVin, requestBodyPsv, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(400);
    }

    @WithTag("Vtm")
    @Title("CVSB-11814 - AC3. PUT: Attempt to update a vehicle with a not applicable field - CAR")
    @Test
    public void testUpdateVehicleTechnicalRecordNotApplicableFieldForCar() {

        // Generate random Vin
        String randomVin = GenericData.generateRandomVin();

        // Generate random Vrm
        String randomVrm = GenericData.generateRandomVrm();

        // Read post request body from file
        String postRequestBody = GenericData.readJsonValueFromFile("technical-records_post_payload_car_10328.json","$");

        // Create alteration to change one more more fields in the request body
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin,"","REPLACE");
        JsonPathAlteration alterationVrm = new JsonPathAlteration("$.primaryVrm", randomVrm,"","REPLACE");

        // Collate the alterations
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(alterationVin, alterationVrm));

        // POST tech-records and verify the expected response
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBody, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(HttpStatus.SC_CREATED);
        vehicleTechnicalRecordsSteps.validateData("Technical Record created");

        // GET tech-records and verify the expected response
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecords(randomVin);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(HttpStatus.SC_OK);
        vehicleTechnicalRecordsSteps.fieldInPathShouldExist("[0]","systemNumber");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].recordCompleteness","skeleton" );
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].statusCode","provisional" );

        String systemNumber = vehicleTechnicalRecordsSteps.getSystemNumber();

        // Read post request body from file
        String putRequestBody = GenericData.readJsonValueFromFile("technical-records_put_payload_car_11814.json","$");

        // Add a not applicable field
        JsonPathAlteration notApplicableField = new JsonPathAlteration("$.techRecord[0]", 2, "euroStandard", "ADD_FIELD");
        alterations.add(notApplicableField);

        // PUT tech-records and verify the expected response
        vehicleTechnicalRecordsSteps.putVehicleTechnicalRecordsForVehicleWithAlterations(systemNumber,putRequestBody,alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(HttpStatus.SC_BAD_REQUEST);
    }

    @WithTag("Vtm")
    @Title("CVSB-11814 - AC3. PUT: Attempt to update a vehicle with a not applicable field - LGV")
    @Test
    public void testUpdateVehicleTechnicalRecordNotApplicableFieldForLgv() {

        // Generate random Vin
        String randomVin = GenericData.generateRandomVin();

        // Generate random Vrm
        String randomVrm = GenericData.generateRandomVrm();

        // Read post request body from file
        String postRequestBody = GenericData.readJsonValueFromFile("technical-records_post_payload_lgv_10328.json","$");

        // Create alteration to change one more more fields in the request body
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin,"","REPLACE");
        JsonPathAlteration alterationVrm = new JsonPathAlteration("$.primaryVrm", randomVrm,"","REPLACE");

        // Collate the alterations
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(alterationVin, alterationVrm));

        // POST tech-records and verify the expected response
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBody, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(HttpStatus.SC_CREATED);
        vehicleTechnicalRecordsSteps.validateData("Technical Record created");

        // GET tech-records and verify the expected response
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecords(randomVin);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(HttpStatus.SC_OK);
        vehicleTechnicalRecordsSteps.fieldInPathShouldExist("[0]","systemNumber");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].recordCompleteness","skeleton" );
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].statusCode","provisional" );

        String systemNumber = vehicleTechnicalRecordsSteps.getSystemNumber();

        // Read post request body from file
        String putRequestBody = GenericData.readJsonValueFromFile("technical-records_put_payload_lgv_11814.json","$");

        // Add a not applicable field
        JsonPathAlteration notApplicableField = new JsonPathAlteration("$.techRecord[0]", 2, "euroStandard", "ADD_FIELD");
        alterations.add(notApplicableField);

        // PUT tech-records and verify the expected response
        vehicleTechnicalRecordsSteps.putVehicleTechnicalRecordsForVehicleWithAlterations(systemNumber,putRequestBody,alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(HttpStatus.SC_BAD_REQUEST);
    }

    @WithTag("Vtm")
    @Title("CVSB-11814 - AC3. PUT: Attempt to update a vehicle with a not applicable field - MOTORCYCLE")
    @Test
    public void testUpdateVehicleTechnicalRecordNotApplicableFieldForMotorcycle() {

        // Generate random Vin
        String randomVin = GenericData.generateRandomVin();

        // Generate random Vrm
        String randomVrm = GenericData.generateRandomVrm();

        // Read post request body from file
        String postRequestBody = GenericData.readJsonValueFromFile("technical-records_post_payload_motorcycle_10328.json","$");

        // Create alteration to change one more more fields in the request body
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin,"","REPLACE");
        JsonPathAlteration alterationVrm = new JsonPathAlteration("$.primaryVrm", randomVrm,"","REPLACE");

        // Collate the alterations
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(alterationVin, alterationVrm));

        // POST tech-records and verify the expected response
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBody, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(HttpStatus.SC_CREATED);
        vehicleTechnicalRecordsSteps.validateData("Technical Record created");

        // GET tech-records and verify the expected response
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecords(randomVin);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(HttpStatus.SC_OK);
        vehicleTechnicalRecordsSteps.fieldInPathShouldExist("[0]","systemNumber");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].recordCompleteness","skeleton" );
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].statusCode","provisional" );

        String systemNumber = vehicleTechnicalRecordsSteps.getSystemNumber();

        // Read post request body from file
        String putRequestBody = GenericData.readJsonValueFromFile("technical-records_put_payload_motorcycle_11814.json","$");

        // Add a not applicable field
        JsonPathAlteration notApplicableField = new JsonPathAlteration("$.techRecord[0]", 2, "euroStandard", "ADD_FIELD");
        alterations.add(notApplicableField);

        // PUT tech-records and verify the expected response
        vehicleTechnicalRecordsSteps.putVehicleTechnicalRecordsForVehicleWithAlterations(systemNumber,putRequestBody,alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(HttpStatus.SC_BAD_REQUEST);
    }

    @WithTag("Vtm")
    @Title("CVSB-11814 - AC3. PUT: Attempt to update a vehicle with a not applicable field - VehicleClass is missing - MOTORCYCLE")
    @Test
    public void testUpdateVehicleTechnicalRecordWithVehicleClassMissingForMotorcycle() {

        // Generate random Vin
        String randomVin = GenericData.generateRandomVin();

        // Generate random Vrm
        String randomVrm = GenericData.generateRandomVrm();

        // Read post request body from file
        String postRequestBody = GenericData.readJsonValueFromFile("technical-records_post_payload_motorcycle_10328.json","$");

        // Create alteration to change one more more fields in the request body
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin,"","REPLACE");
        JsonPathAlteration alterationVrm = new JsonPathAlteration("$.primaryVrm", randomVrm,"","REPLACE");

        // Collate the alterations
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(alterationVin, alterationVrm));

        // POST tech-records and verify the expected response
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBody, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(HttpStatus.SC_CREATED);
        vehicleTechnicalRecordsSteps.validateData("Technical Record created");

        // GET tech-records and verify the expected response
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecords(randomVin);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(HttpStatus.SC_OK);
        vehicleTechnicalRecordsSteps.fieldInPathShouldExist("[0]","systemNumber");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].recordCompleteness","skeleton" );
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].statusCode","provisional" );

        String systemNumber = vehicleTechnicalRecordsSteps.getSystemNumber();

        // Read post request body from file
        String putRequestBody = GenericData.readJsonValueFromFile("technical-records_put_payload_motorcycle_11814.json","$");

        // Create alteration by removing VehicleClass
        JsonPathAlteration alterationVehicleClass = new JsonPathAlteration("$.techRecord[0].vehicleClass", "","","DELETE");
        alterations.add(alterationVehicleClass);

        // PUT tech-records and verify the expected response
        vehicleTechnicalRecordsSteps.putVehicleTechnicalRecordsForVehicleWithAlterations(systemNumber,putRequestBody,alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(HttpStatus.SC_BAD_REQUEST);
        vehicleTechnicalRecordsSteps.validatePostErrorData("vehicleClass", "is required");
    }

    @WithTag("Vtm")
    @Title("CVSB-11814 - AC3. PUT: Attempt to update a vehicle with a not applicable field - VehicleClass is invalid - MOTORCYCLE")
    @Test
    public void testUpdateVehicleTechnicalRecordWithVehicleClassInvalidForMotorcycle() {

        // Generate random Vin
        String randomVin = GenericData.generateRandomVin();

        // Generate random Vrm
        String randomVrm = GenericData.generateRandomVrm();

        // Read post request body from file
        String postRequestBody = GenericData.readJsonValueFromFile("technical-records_post_payload_motorcycle_10328.json","$");

        // Create alteration to change one more more fields in the request body
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin,"","REPLACE");
        JsonPathAlteration alterationVrm = new JsonPathAlteration("$.primaryVrm", randomVrm,"","REPLACE");

        // Collate the alterations
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(alterationVin, alterationVrm));

        // POST tech-records and verify the expected response
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBody, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(HttpStatus.SC_CREATED);
        vehicleTechnicalRecordsSteps.validateData("Technical Record created");

        // GET tech-records and verify the expected response
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecords(randomVin);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(HttpStatus.SC_OK);
        vehicleTechnicalRecordsSteps.fieldInPathShouldExist("[0]","systemNumber");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].recordCompleteness","skeleton" );
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord[0].statusCode","provisional" );

        String systemNumber = vehicleTechnicalRecordsSteps.getSystemNumber();

        // Read post request body from file
        String putRequestBody = GenericData.readJsonValueFromFile("technical-records_put_payload_motorcycle_11814.json","$");

        // Create alteration by removing VehicleClass
        JsonPathAlteration alterationVehicleClass = new JsonPathAlteration("$.techRecord[0].vehicleClass", null,"","REPLACE");
        alterations.add(alterationVehicleClass);

        // PUT tech-records and verify the expected response
        vehicleTechnicalRecordsSteps.putVehicleTechnicalRecordsForVehicleWithAlterations(systemNumber,putRequestBody,alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(HttpStatus.SC_BAD_REQUEST);
        vehicleTechnicalRecordsSteps.validatePostErrorData("vehicleClass", "must be of type object");
    }


}
