package vehicle;

import data.GenericData;
import io.restassured.response.Response;
import model.vehicles.VehicleTechnicalRecordStatus;
import net.minidev.json.JSONArray;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import net.thucydides.core.annotations.WithTag;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import  org.junit.Assert;
import org.junit.Ignore;
import org.springframework.util.Base64Utils;
import steps.VehicleTechnicalRecordsSteps;
import util.JsonPathAlteration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RunWith(SerenityRunner.class)
public class PutVehicleTechnicalRecords {

    @Steps
    VehicleTechnicalRecordsSteps vehicleTechnicalRecordsSteps;


    @Ignore("Remove the ignore annotation when the updates on tech record will be enabled for vehicles without adr details")
    @WithTag("Vtm")
    @Title("CVSB-7885 - AC6 - Can update all fields for a vehicle entry, except the VIN, partial VIN, trailer id, primary and secondary VRM" +
            "AC7 - Can not update VIN, partial vin or primary/secondary vrm" +
            "AC9 - Can add or remove tech records for a vehicle entry")
    @Test
    public void testUpdateVehicleTechnicalRecord() {
        // TEST SETUP
        // generate random Vin
        String randomVin = GenericData.generateRandomVin();
        //generate random Vrm
        String randomVrm = GenericData.generateRandomVrm();
        // read post request body from file
        String postRequestBody = GenericData.readJsonValueFromFile("technical-records_current.json","$");
        // read put request body from file
        String putRequestBody = GenericData.readJsonValueFromFile("technical-records_psv.json","$");
        // read the tech record from the file used for post request body
        String oldTechRecord = GenericData.readJsonValueFromFile("technical-records_current.json","$.techRecord[0]");
        // read the tech record from the file used for post request body
        String newTechRecord = GenericData.readJsonValueFromFile("technical-records_psv.json","$.techRecord[0]");
        // create alteration to change Vin in the post request body with the random generated Vin
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin,"","REPLACE");
        // create alteration to change primary vrm in the request body with the random generated primary vrm
        JsonPathAlteration alterationVrm = new JsonPathAlteration("$.primaryVrm", randomVrm,"","REPLACE");
        // create alteration to add one more tech record to in the put request body
        JsonPathAlteration alterationAddTechRecord = new JsonPathAlteration("$.techRecord", oldTechRecord,"","ADD_VALUE");
        // initialize the alterations list with only the alterations for changing the Vin and the primary vrm
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(alterationVin, alterationVrm));

        // TEST
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBody, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(201);
        // validate AC6
        vehicleTechnicalRecordsSteps.putVehicleTechnicalRecordsForVehicle(randomVin, putRequestBody);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsByStatus(randomVin, VehicleTechnicalRecordStatus.ALL);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord.size()", 1);
        vehicleTechnicalRecordsSteps.validateResponseContainsJson("techRecord[0]", newTechRecord);
        // validate AC7
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("vin", randomVin);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("vrms[0].vrm", randomVrm);
        // add alteration for adding one more tech record for the randomVin used above
        alterations.add(alterationAddTechRecord);
        // validate AC9
        // add tech record to vehicle with random Vin
        vehicleTechnicalRecordsSteps.putVehicleTechnicalRecordsForVehicleWithAlterations(randomVin, putRequestBody, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsByStatus(randomVin, VehicleTechnicalRecordStatus.ALL);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord.size()", 2);
        vehicleTechnicalRecordsSteps.validateResponseContainsJson("techRecord[0]", newTechRecord);
        vehicleTechnicalRecordsSteps.validateResponseContainsJson("techRecord[1]", oldTechRecord);
        // remove tech record to vehicle with random Vin
        vehicleTechnicalRecordsSteps.putVehicleTechnicalRecordsForVehicle(randomVin, putRequestBody);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsByStatus(randomVin, VehicleTechnicalRecordStatus.ALL);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord.size()", 1);
        vehicleTechnicalRecordsSteps.validateResponseContainsJson("techRecord[0]", newTechRecord);
    }

    @WithTag("Vtm")
    @Title("CVSB-8677 - AC1 - Add adrDetails object onto an existing tech record with status current" +
            "AC2 - Update adrDetails object on an existing tech record" +
            "AC3 - All attributes are returned" +
            "AC4 - Adding of adrDetails is audited" +
            "AC5 - Updating of adrDetails is audited")
    @Test
    public void testAddAdrDetailsObjectVehicleTechnicalRecord() {
        //TEST SETUP
        // generate random Vin
        String randomVin = GenericData.generateRandomVin();
        //generate random Vrm
        String randomVrm = GenericData.generateRandomVrm();
        // read post request body from file
        String postRequestBodyHgv = GenericData.readJsonValueFromFile("technical-records_hgv.json","$");
        // read put request body from file for adding battery adr details
        String putRequestBodyAdrDetailsBattery = GenericData.readJsonValueFromFile("technical-records_adr_details_battery.json","$");
        // read the adr details from the file used for put request body with battery adr details
        String adrDetailsBattery = GenericData.readJsonValueFromFile("technical-records_adr_details_battery.json","$.techRecord[0].adrDetails");
        // create alteration to change Vin in the post request body with the random generated Vin
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin,"","REPLACE");
        // create alteration to change primary vrm in the request body with the random generated primary vrm
        JsonPathAlteration alterationVrm = new JsonPathAlteration("$.primaryVrm", randomVrm,"","REPLACE");
        // initialize the alterations list with only the alterations for changing the Vin and the primary vrm
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(alterationVin, alterationVrm));
        // read put request body from file for adding tank adr details
        String putRequestBodyAdrDetailsTank = GenericData.readJsonValueFromFile("technical-records_adr_details_tank.json","$");
        // read the adr details from the file used for put request body with tank adr details
        String adrDetailsTank = GenericData.readJsonValueFromFile("technical-records_adr_details_tank.json","$.techRecord[0].adrDetails");

        //TEST
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBodyHgv, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(201);
        // validate AC1
        vehicleTechnicalRecordsSteps.putVehicleTechnicalRecordsForVehicle(randomVin, putRequestBodyAdrDetailsBattery);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsByStatus(randomVin, VehicleTechnicalRecordStatus.ALL);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord.size()", 3);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[0].statusCode", "provisional");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[1].statusCode", "archived");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[1].updateType", "adrUpdate");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[1].lastUpdatedByName", "catalin");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[1].lastUpdatedById", "123243424-234234245");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[2].statusCode", "current");
        vehicleTechnicalRecordsSteps.valuesForFieldsInPathShouldEqual("techRecord[1].lastUpdatedAt","techRecord[2].createdAt");
        vehicleTechnicalRecordsSteps.fieldInPathShouldExist("techRecord[2]", "adrDetails");
        // validated AC3
        vehicleTechnicalRecordsSteps.validateResponseContainsJson("techRecord[2].adrDetails", adrDetailsBattery);
        // Validate AC4
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[2].createdByName", "catalin");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[2].createdById", "123243424-234234245");
        // Validate AC2
        vehicleTechnicalRecordsSteps.putVehicleTechnicalRecordsForVehicle(randomVin, putRequestBodyAdrDetailsTank);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsByStatus(randomVin, VehicleTechnicalRecordStatus.ALL);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord.size()", 4);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[0].statusCode", "provisional");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[1].statusCode", "archived");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[2].statusCode", "archived");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[2].updateType", "adrUpdate");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[2].lastUpdatedByName", "dragos");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[2].lastUpdatedById", "11112222-33334444");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[3].statusCode", "current");
        vehicleTechnicalRecordsSteps.valuesForFieldsInPathShouldEqual("techRecord[2].lastUpdatedAt","techRecord[3].createdAt");
        // validate AC5
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[3].createdByName", "dragos");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[3].createdById", "11112222-33334444");
        vehicleTechnicalRecordsSteps.validateResponseContainsJson("techRecord[3].adrDetails", adrDetailsTank);
    }

    @WithTag("Vtm")
    @Title("CVSB-9317 - AC1 - Document is attached to the ADR record" +
            "AC2 - Document is removed from the ADR record")
    @Test
    public void testAddAdrDetailsUploadDocumentAdrRecord() {
        //TEST SETUP
        // generate random Vin
        String randomVin = GenericData.generateRandomVin();
        //generate random Vrm
        String randomVrm = GenericData.generateRandomVrm();
        // read post request body from file
        String postRequestBodyHgv = GenericData.readJsonValueFromFile("technical-records_hgv.json","$");
        // read put request body from file for adding battery adr details
        String putRequestBodyAdrDetailsBattery = GenericData.readJsonValueFromFile("technical-records_adr_details_battery.json","$");
        // read the adr details from the file used for put request body with battery adr details
        String adrDetailsBattery = GenericData.readJsonValueFromFile("technical-records_adr_details_battery.json","$.techRecord[0].adrDetails");
        // create alteration to change Vin in the post request body with the random generated Vin
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin,"","REPLACE");
        // create alteration to change primary vrm in the request body with the random generated primary vrm
        JsonPathAlteration alterationVrm = new JsonPathAlteration("$.primaryVrm", randomVrm,"","REPLACE");
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(alterationVin, alterationVrm));
        // get pdf content as base64 encoded string
        String encodedFileContent = GenericData.readBytesFromFile("sample.pdf");
        // create alteration to add files field in the request body as array with two elements, one for the new files and one to keep the existing file
        String addFileForUpload = GenericData.readJsonValueFromFile("file_attachment_pdf.json","$.files");
        int i1 = addFileForUpload.lastIndexOf(",");
        int i2 = addFileForUpload.lastIndexOf("\"");
        String newFileForUpload = addFileForUpload.replace(addFileForUpload.substring(i1+1, i2), encodedFileContent);
        String keepExistingFile = "{\"filename\":\"bla-bla.txt\",\"toUpload\":false}";
        JsonPathAlteration alterationAddFiles = new JsonPathAlteration("$", newFileForUpload,"files","ADD_FIELD");
        JsonPathAlteration alterationKeepExistingFile = new JsonPathAlteration("$.files", keepExistingFile,"files","ADD_VALUE");

        JsonPathAlteration alterationRemoveAddedFile = new JsonPathAlteration("$","[" + keepExistingFile + "]","files","ADD_FIELD");
        List<JsonPathAlteration> alterationsAdrFiles = new ArrayList<>(Arrays.asList(alterationAddFiles,alterationKeepExistingFile));

        //TEST
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBodyHgv, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(201);
        // validate AC1
        vehicleTechnicalRecordsSteps.putVehicleTechnicalRecordsForVehicleWithAlterations(randomVin, putRequestBodyAdrDetailsBattery, alterationsAdrFiles);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord.size()", 3);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[2].adrDetails.documents.size()", 2);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldEndWith("techRecord[2].adrDetails.documents[1]", ".pdf");
        String fileName = vehicleTechnicalRecordsSteps.extractFieldValueFromResponse("techRecord[2].adrDetails.documents[1]", randomVin, VehicleTechnicalRecordStatus.CURRENT);
        //validate AC2
        alterationsAdrFiles = new ArrayList<>(Arrays.asList(alterationRemoveAddedFile));
        vehicleTechnicalRecordsSteps.putVehicleTechnicalRecordsForVehicleWithAlterations(randomVin, putRequestBodyAdrDetailsBattery, alterationsAdrFiles);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord.size()", 4);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[3].adrDetails.documents.size()", 1);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[3].adrDetails.documents[0]", "bla-bla.txt");
        //previously uploaded file can be added again to the documents array without having "toUpload" to true
        JsonPathAlteration alterationToUploadFalse = new JsonPathAlteration("$.files[0].toUpload", false,"","REPLACE");
        JsonPathAlteration alterationAddAlreadyUploadedFileToDocuments = new JsonPathAlteration("$.files[0].filename", fileName,"","REPLACE");
        JsonPathAlteration alterationBase64StringNull = new JsonPathAlteration("$.files[0].base64String", "random string","","REPLACE");
        alterationsAdrFiles =  new ArrayList<>(Arrays.asList(alterationAddFiles, alterationToUploadFalse, alterationBase64StringNull, alterationAddAlreadyUploadedFileToDocuments));
        vehicleTechnicalRecordsSteps.putVehicleTechnicalRecordsForVehicleWithAlterations(randomVin, putRequestBodyAdrDetailsBattery, alterationsAdrFiles);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord.size()", 5);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[4].adrDetails.documents.size()", 1);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[4].adrDetails.documents[0]", fileName);

    }

    @WithTag("Vtm")
    @Title("CVSB-9657 - AC1 - Document from ADR record is downloaded")
    @Test
    public void testAddAdrDetailsDownloadDocumentAdrRecord() {
        //TEST SETUP
        // generate random Vin
        String randomVin = GenericData.generateRandomVin();
        //generate random Vrm
        String randomVrm = GenericData.generateRandomVrm();
        // read post request body from file
        String postRequestBodyHgv = GenericData.readJsonValueFromFile("technical-records_hgv.json","$");
        // read put request body from file for adding battery adr details
        String putRequestBodyAdrDetailsBattery = GenericData.readJsonValueFromFile("technical-records_adr_details_battery.json","$");
        // read the adr details from the file used for put request body with battery adr details
        String adrDetailsBattery = GenericData.readJsonValueFromFile("technical-records_adr_details_battery.json","$.techRecord[0].adrDetails");
        // create alteration to change Vin in the post request body with the random generated Vin
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin,"","REPLACE");
        // create alteration to change primary vrm in the request body with the random generated primary vrm
        JsonPathAlteration alterationVrm = new JsonPathAlteration("$.primaryVrm", randomVrm,"","REPLACE");
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(alterationVin, alterationVrm));
        // get pdf content as base64 encoded string
        String encodedFileContent = GenericData.readBytesFromFile("sample.pdf");
        // create alteration to add files field in the request body as array with two elements, one for the new files and one to keep the existing file
        String addFileForUpload = GenericData.readJsonValueFromFile("file_attachment_pdf.json","$.files");
        int i1 = addFileForUpload.lastIndexOf(",");
        int i2 = addFileForUpload.lastIndexOf("\"");
        String newFileForUpload = addFileForUpload.replace(addFileForUpload.substring(i1+1, i2), encodedFileContent);
        // create alteration to add files field in the request body as array with an element the previously encoded string
        String keepExistingFile = "{\"filename\":\"bla-bla.txt\",\"toUpload\":false}";
        JsonPathAlteration alterationAddFiles = new JsonPathAlteration("$", newFileForUpload,"files","ADD_FIELD");
        JsonPathAlteration alterationKeepExistingFile = new JsonPathAlteration("$.files", keepExistingFile,"files","ADD_VALUE");
        List<JsonPathAlteration> alterationsAdrFiles = new ArrayList<>(Arrays.asList(alterationAddFiles,alterationKeepExistingFile));

        //TEST
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBodyHgv, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(201);
        vehicleTechnicalRecordsSteps.putVehicleTechnicalRecordsForVehicleWithAlterations(randomVin, putRequestBodyAdrDetailsBattery, alterationsAdrFiles);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord.size()", 3);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[2].adrDetails.documents.size()", 2);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldEndWith("techRecord[2].adrDetails.documents[1]", ".pdf");
        String fileName = vehicleTechnicalRecordsSteps.extractFieldValueFromResponse("techRecord[2].adrDetails.documents[1]", randomVin, VehicleTechnicalRecordStatus.CURRENT);
        Response downloadFileResponse = vehicleTechnicalRecordsSteps.downloadFile(randomVin, fileName);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("fileBuffer.type", "Buffer");
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("contentType","application/pdf");

        JSONArray jsonArray = GenericData.extractJsonArrayValueFromJsonString(downloadFileResponse.asString(), "$.fileBuffer.data");
        byte[] bytes = new byte[jsonArray.size()];
        for (int i = 0; i < jsonArray.size(); i++) {
            bytes[i]=(byte)(((int)jsonArray.get(i)));
        }
        String downloadedFileContent = Base64Utils.encodeToString(bytes);
        Assert.assertEquals(200, downloadFileResponse.getStatusCode());
        Assert.assertEquals(encodedFileContent, downloadedFileContent);
    }

    @WithTag("Vtm")
    @Title("CVSB-10847 - Existing ADR document is removed if it is not in files array with 'toUpload' set to false")
    @Test
    public void testRemoveAdrDocument() {
        //TEST SETUP
        // generate random Vin
        String randomVin = GenericData.generateRandomVin();
        //generate random Vrm
        String randomVrm = GenericData.generateRandomVrm();
        // read post request body from file
        String postRequestBodyHgv = GenericData.readJsonValueFromFile("technical-records_hgv.json","$");
        // read put request body from file for adding battery adr details
        String putRequestBodyAdrDetailsBattery = GenericData.readJsonValueFromFile("technical-records_adr_details_battery.json","$");
        // read the adr details from the file used for put request body with battery adr details
        String adrDetailsBattery = GenericData.readJsonValueFromFile("technical-records_adr_details_battery.json","$.techRecord[0].adrDetails");
        // create alteration to change Vin in the post request body with the random generated Vin
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin,"","REPLACE");
        // create alteration to change primary vrm in the request body with the random generated primary vrm
        JsonPathAlteration alterationVrm = new JsonPathAlteration("$.primaryVrm", randomVrm,"","REPLACE");
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(alterationVin, alterationVrm));
        // create alteration to remove all adr documents
        JsonPathAlteration alterationNoFiles = new JsonPathAlteration("$", "[]","files","ADD_FIELD");
        List<JsonPathAlteration> alterationsAdrFiles = new ArrayList<>(Arrays.asList(alterationNoFiles));

        //TEST
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBodyHgv, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(201);
        vehicleTechnicalRecordsSteps.putVehicleTechnicalRecordsForVehicleWithAlterations(randomVin, putRequestBodyAdrDetailsBattery, alterationsAdrFiles);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord.size()", 3);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[2].adrDetails.documents.size()", 0);
    }

    @WithTag("Vtm")
    @Title("CVSB-10847 - User is allowed making GET request with the name of an existing file with 'toUpload' set to true")
    @Test
    public void testNoErrorForUploadingAlreadyExistingFile() {
        //TEST SETUP
        // generate random Vin
        String randomVin = GenericData.generateRandomVin();
        //generate random Vrm
        String randomVrm = GenericData.generateRandomVrm();
        // read post request body from file
        String postRequestBodyHgv = GenericData.readJsonValueFromFile("technical-records_hgv.json","$");
        // read put request body from file for adding battery adr details
        String putRequestBodyAdrDetailsBattery = GenericData.readJsonValueFromFile("technical-records_adr_details_battery.json","$");
        // read the adr details from the file used for put request body with battery adr details
        String adrDetailsBattery = GenericData.readJsonValueFromFile("technical-records_adr_details_battery.json","$.techRecord[0].adrDetails");
        // create alteration to change Vin in the post request body with the random generated Vin
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin,"","REPLACE");
        // create alteration to change primary vrm in the request body with the random generated primary vrm
        JsonPathAlteration alterationVrm = new JsonPathAlteration("$.primaryVrm", randomVrm,"","REPLACE");
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(alterationVin, alterationVrm));
        // get pdf content as base64 encoded string
        String encodedFileContent = GenericData.readBytesFromFile("sample.pdf");
        // create alterations to add files to keep the existing file and then upload a new file
        String addFileForUpload = GenericData.readJsonValueFromFile("file_attachment_pdf.json","$.files");
        int i1 = addFileForUpload.lastIndexOf(",");
        int i2 = addFileForUpload.lastIndexOf("\"");
        String newFileForUpload = addFileForUpload.replace(addFileForUpload.substring(i1+1, i2), encodedFileContent);
        String keepExistingFile = "{\"filename\":\"bla-bla.txt\",\"toUpload\":false}";
        JsonPathAlteration alterationAddFiles = new JsonPathAlteration("$", newFileForUpload,"files","ADD_FIELD");
        JsonPathAlteration alterationKeepExistingFile = new JsonPathAlteration("$.files", keepExistingFile,"files","ADD_VALUE");
        List<JsonPathAlteration> alterationsAdrFiles = new ArrayList<>(Arrays.asList(alterationAddFiles,alterationKeepExistingFile));

        //TEST
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBodyHgv, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(201);
        // upload file
        vehicleTechnicalRecordsSteps.putVehicleTechnicalRecordsForVehicleWithAlterations(randomVin, putRequestBodyAdrDetailsBattery, alterationsAdrFiles);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord.size()", 3);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[2].adrDetails.documents.size()", 2);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldEndWith("techRecord[2].adrDetails.documents[1]", ".pdf");
        //re-upload previous file
        String fileName = vehicleTechnicalRecordsSteps.extractFieldValueFromResponse("techRecord[2].adrDetails.documents[1]", randomVin, VehicleTechnicalRecordStatus.CURRENT);
        String existingFileUploadTrue = "{\"filename\":\"" + fileName + "\",\"base64String\":\"data:application/pdf;base64," + encodedFileContent + "\",\"toUpload\":true}";
        JsonPathAlteration existingFileUploadTrueAlteration = new JsonPathAlteration("$","[" + existingFileUploadTrue + "]","files","ADD_FIELD");
        alterationsAdrFiles = new ArrayList<>(Collections.singletonList(existingFileUploadTrueAlteration));
        vehicleTechnicalRecordsSteps.putVehicleTechnicalRecordsForVehicleWithAlterations(randomVin, putRequestBodyAdrDetailsBattery, alterationsAdrFiles);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsByStatus(randomVin, VehicleTechnicalRecordStatus.CURRENT);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord[0].adrDetails.documents.size()", 1);
    }
}
