package integration;

import data.GenericData;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import net.thucydides.core.annotations.WithTag;
import org.junit.Test;
import org.junit.runner.RunWith;
import steps.VehicleTechnicalRecordsSteps;
import util.AwsUtil;
import util.JsonPathAlteration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@RunWith(SerenityRunner.class)
public class PostVehicleCloudWatchLogs {

    @Steps
    VehicleTechnicalRecordsSteps vehicleTechnicalRecordsSteps;

    @WithTag("integration")
    @Title("CVSB-10775 - CVS to EDH (Technical records) - TC1 - AC1 - POST request is made and EDH responds back with HTTP 202 Accepted status")
    public void testVehiclePostHttpCode202() {
        // TEST SETUP
        // generate random systemNumber
        String randomSystemNumber = AwsUtil.getNextSystemNumberInSequence();
        // generate random Vin
        String randomVin = GenericData.generateRandomVin();
        // generate random Vrm
        String randomVrm = GenericData.generateRandomVrm();

        // read post request body from file
        String requestBody = GenericData.readJsonValueFromFile("technical-records_integration_hgv_10775.json", "$");

        // create alterations to change attributes
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationVrm = new JsonPathAlteration("$.primaryVrm", randomVrm, "", "REPLACE");
        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$.systemNumber", randomSystemNumber, "", "REPLACE");

        // initialize the alterations list
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(
                alterationVin,
                alterationVrm,
                alterationSystemNumber
        ));

        //TEST
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(requestBody, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(201);
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecords(randomVin);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord.size()", 1);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].systemNumber", randomSystemNumber);
        vehicleTechnicalRecordsSteps.checkAwsDispatcherLogStatusCodeForSystemNumber("POST", randomSystemNumber, 202);
        vehicleTechnicalRecordsSteps.deleteRecords(randomSystemNumber);
    }

    @WithTag("integration")
    @Title("CVSB-10775 - CVS to EDH (Technical records) - TC2 - AC2 - POST request is made and EDH responds back with HTTP Error code 400")
    public void testVehiclePostHttpCode400() {
        // TEST SETUP
        // generate random systemNumber
        String randomSystemNumber = "e4404400-7e57-c0de-e400-e4404c0de400";
        // generate random Vin
        String randomVin = GenericData.generateRandomVin();
        // generate random Vrm
        String randomVrm = GenericData.generateRandomVrm();

        // read post request body from file
        String requestBody = GenericData.readJsonValueFromFile("technical-records_hgv_insert_10775.json", "$");

        // create alterations to change attributes
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationVrm = new JsonPathAlteration("$.primaryVrm", randomVrm, "", "REPLACE");
        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$.systemNumber", randomSystemNumber, "", "REPLACE");

        // initialize the alterations list
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(
                alterationVin,
                alterationVrm,
                alterationSystemNumber
        ));

        //TEST
        vehicleTechnicalRecordsSteps.insertVehicleWithAlterations(requestBody, alterations);
        vehicleTechnicalRecordsSteps.checkAwsDispatcherLogStatusCodeForSystemNumber("POST", randomSystemNumber, 400);
        vehicleTechnicalRecordsSteps.deleteRecords(randomSystemNumber);
    }

    @WithTag("integration")
    @Title("CVSB-10775 - CVS to EDH (Technical records) - TC2 - AC2 - POST request is made and EDH responds back with HTTP Error code 401")
    public void testVehiclePostHttpCode401() {
        // TEST SETUP
        // generate random systemNumber
        String randomSystemNumber = "e4404401-7e57-c0de-e401-e4404c0de401";
        // generate random Vin
        String randomVin = GenericData.generateRandomVin();
        // generate random Vrm
        String randomVrm = GenericData.generateRandomVrm();

        // read post request body from file
        String requestBody = GenericData.readJsonValueFromFile("technical-records_hgv_insert_10775.json", "$");

        // create alterations to change attributes
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationVrm = new JsonPathAlteration("$.primaryVrm", randomVrm, "", "REPLACE");
        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$.systemNumber", randomSystemNumber, "", "REPLACE");

        // initialize the alterations list
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(
                alterationVin,
                alterationVrm,
                alterationSystemNumber
        ));

        //TEST
        vehicleTechnicalRecordsSteps.insertVehicleWithAlterations(requestBody, alterations);
        vehicleTechnicalRecordsSteps.checkAwsDispatcherLogStatusCodeForSystemNumber("POST", randomSystemNumber, 401);
        vehicleTechnicalRecordsSteps.deleteRecords(randomSystemNumber);
    }

    @WithTag("integration")
    @Title("CVSB-10775 - CVS to EDH (Technical records) - TC2 - AC2 - POST request is made and EDH responds back with HTTP Error code 403")
    public void testVehiclePostHttpCode403() {
        // TEST SETUP
        // generate random systemNumber
        String randomSystemNumber = "e4404403-7e57-c0de-e403-e4404c0de403";
        // generate random Vin
        String randomVin = GenericData.generateRandomVin();
        // generate random Vrm
        String randomVrm = GenericData.generateRandomVrm();

        // read post request body from file
        String requestBody = GenericData.readJsonValueFromFile("technical-records_hgv_insert_10775.json", "$");

        // create alterations to change attributes
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationVrm = new JsonPathAlteration("$.primaryVrm", randomVrm, "", "REPLACE");
        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$.systemNumber", randomSystemNumber, "", "REPLACE");

        // initialize the alterations list
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(
                alterationVin,
                alterationVrm,
                alterationSystemNumber
        ));

        //TEST
        vehicleTechnicalRecordsSteps.insertVehicleWithAlterations(requestBody, alterations);
        vehicleTechnicalRecordsSteps.checkAwsDispatcherLogStatusCodeForSystemNumber("POST", randomSystemNumber, 403);
        vehicleTechnicalRecordsSteps.deleteRecords(randomSystemNumber);
    }

    @WithTag("integration")
    @Title("CVSB-10775 - CVS to EDH (Technical records) - TC2 - AC2 - POST request is made and EDH responds back with HTTP Error code 404")
    public void testVehiclePostHttpCode404() {
        // TEST SETUP
        // generate random systemNumber
        String randomSystemNumber = "e4404404-7e57-c0de-e404-e4404c0de404";
        // generate random Vin
        String randomVin = GenericData.generateRandomVin();
        // generate random Vrm
        String randomVrm = GenericData.generateRandomVrm();

        // read post request body from file
        String requestBody = GenericData.readJsonValueFromFile("technical-records_hgv_insert_10775.json", "$");

        // create alterations to change attributes
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationVrm = new JsonPathAlteration("$.primaryVrm", randomVrm, "", "REPLACE");
        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$.systemNumber", randomSystemNumber, "", "REPLACE");

        // initialize the alterations list
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(
                alterationVin,
                alterationVrm,
                alterationSystemNumber
        ));

        //TEST
        vehicleTechnicalRecordsSteps.insertVehicleWithAlterations(requestBody, alterations);
        vehicleTechnicalRecordsSteps.checkAwsDispatcherLogStatusCodeForSystemNumber("POST", randomSystemNumber, 404);
        vehicleTechnicalRecordsSteps.deleteRecords(randomSystemNumber);
    }

    @WithTag("integration")
    @Title("CVSB-10775 - CVS to EDH (Technical records) - TC3 - AC3 - POST request is made and EDH responds back with HTTP Error code 429")
    public void testVehiclePostHttpCode429() {
        // TEST SETUP
        // generate random systemNumber
        String randomSystemNumber = "e4404429-7e57-c0de-e429-e4404c0de429";
        // generate random Vin
        String randomVin = GenericData.generateRandomVin();
        // generate random Vrm
        String randomVrm = GenericData.generateRandomVrm();

        // read post request body from file
        String requestBody = GenericData.readJsonValueFromFile("technical-records_hgv_insert_10775.json", "$");

        // create alterations to change attributes
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationVrm = new JsonPathAlteration("$.primaryVrm", randomVrm, "", "REPLACE");
        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$.systemNumber", randomSystemNumber, "", "REPLACE");

        // initialize the alterations list
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(
                alterationVin,
                alterationVrm,
                alterationSystemNumber
        ));

        //TEST
        vehicleTechnicalRecordsSteps.insertVehicleWithAlterations(requestBody, alterations);
        vehicleTechnicalRecordsSteps.checkAwsDispatcherLogStatusCodeForSystemNumber("POST", randomSystemNumber, 429);
        vehicleTechnicalRecordsSteps.deleteRecords(randomSystemNumber);
    }

    @WithTag("integration")
    @Title("CVSB-10775 - CVS to EDH (Technical records) - TC3 - AC3 - POST request is made and EDH responds back with HTTP Error code 500")
    public void testVehiclePostHttpCode500() {
        // TEST SETUP
        // generate random systemNumber
        String randomSystemNumber = "e4404500-7e57-c0de-e500-e4404c0de500";
        // generate random Vin
        String randomVin = GenericData.generateRandomVin();
        // generate random Vrm
        String randomVrm = GenericData.generateRandomVrm();

        // read post request body from file
        String requestBody = GenericData.readJsonValueFromFile("technical-records_hgv_insert_10775.json", "$");

        // create alterations to change attributes
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationVrm = new JsonPathAlteration("$.primaryVrm", randomVrm, "", "REPLACE");
        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$.systemNumber", randomSystemNumber, "", "REPLACE");

        // initialize the alterations list
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(
                alterationVin,
                alterationVrm,
                alterationSystemNumber
        ));

        //TEST
        vehicleTechnicalRecordsSteps.insertVehicleWithAlterations(requestBody, alterations);
        vehicleTechnicalRecordsSteps.checkAwsDispatcherLogStatusCodeForSystemNumber("POST", randomSystemNumber, 500);
        vehicleTechnicalRecordsSteps.deleteRecords(randomSystemNumber);
    }

    @WithTag("integration")
    @Title("CVSB-10775 - CVS to EDH (Technical records) - TC4 - AC4 - PUT request is made and EDH responds back with HTTP 202 Accepted status")
    public void testVehiclePutHttpCode202() {
        // TEST SETUP
        // generate random systemNumber
        String randomSystemNumber = AwsUtil.getNextSystemNumberInSequence();
        // generate random Vin
        String randomVin = GenericData.generateRandomVin();
        // generate random Vrm
        String randomVrm = GenericData.generateRandomVrm();

        // read post request body from file
        String insertBody = GenericData.readJsonValueFromFile("technical-records_integration_hgv_10775.json", "$");
        String requestBody = GenericData.readJsonValueFromFile("technical-records_integration_hgv_10775.json", "$");

        // create alterations to change attributes
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationVrm = new JsonPathAlteration("$.primaryVrm", randomVrm, "", "REPLACE");
        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$.systemNumber", randomSystemNumber, "", "REPLACE");
        // read the adr details from the file used for put request body with battery adr details
        String adrDetails = GenericData.readJsonValueFromFile("technical-records_adr_details_other_nulls.json","$.techRecord[0].adrDetails");

        // initialize the alterations list
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(
                alterationVin,
                alterationVrm,
                alterationSystemNumber
        ));

        //TEST
        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(insertBody, alterations);
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecords(randomVin);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord.size()", 1);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].systemNumber", randomSystemNumber);

        JsonPathAlteration alterationAddAdrDetails = new JsonPathAlteration("$.techRecord[0]", adrDetails,"adrDetails","ADD_FIELD");
        alterations.add(alterationAddAdrDetails);

        vehicleTechnicalRecordsSteps.putVehicleTechnicalRecordsForVehicleWithAlterations(randomVin, requestBody, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.validateResponseContainsJson("techRecord[1].adrDetails", adrDetails);
        vehicleTechnicalRecordsSteps.checkAwsDispatcherLogStatusCodeForSystemNumber("PUT", randomSystemNumber, 202);
        vehicleTechnicalRecordsSteps.deleteRecords(randomSystemNumber);
    }

    @WithTag("integration")
    @Title("CVSB-10775 - CVS to EDH (Technical records) - TC5 - AC5 - PUT request is made and EDH responds back with HTTP Error code 400")
    public void testVehiclePutHttpCode400() {
        // TEST SETUP
        // generate random systemNumber
        String randomSystemNumber = "e4404400-7e57-c0de-e400-e4404c0de400";
        // generate random Vin
        String randomVin = GenericData.generateRandomVin();
        // generate random Vrm
        String randomVrm = GenericData.generateRandomVrm();

        // read post request body from file
        String insertBody = GenericData.readJsonValueFromFile("technical-records_hgv_insert_10775.json", "$");
        String requestBody = GenericData.readJsonValueFromFile("technical-records_integration_hgv_10775.json", "$");

        // create alterations to change attributes
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationVrm = new JsonPathAlteration("$.primaryVrm", randomVrm, "", "REPLACE");
        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$.systemNumber", randomSystemNumber, "", "REPLACE");
        // read the adr details from the file used for put request body with battery adr details
        String adrDetails = GenericData.readJsonValueFromFile("technical-records_adr_details_other_nulls.json","$.techRecord[0].adrDetails");

        // initialize the alterations list
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(
                alterationVin,
                alterationVrm,
                alterationSystemNumber
        ));

        //TEST
        vehicleTechnicalRecordsSteps.insertVehicleWithAlterations(insertBody, alterations);
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecords(randomVin);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord.size()", 1);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].systemNumber", randomSystemNumber);

        JsonPathAlteration alterationAddAdrDetails = new JsonPathAlteration("$.techRecord[0]", adrDetails,"adrDetails","ADD_FIELD");
        alterations.add(alterationAddAdrDetails);

        vehicleTechnicalRecordsSteps.putVehicleTechnicalRecordsForVehicleWithAlterations(randomVin, requestBody, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.validateResponseContainsJson("techRecord[1].adrDetails", adrDetails);
        vehicleTechnicalRecordsSteps.checkAwsDispatcherLogStatusCodeForSystemNumber("PUT", randomSystemNumber, 400);
        vehicleTechnicalRecordsSteps.deleteRecords(randomSystemNumber);

    }

    @WithTag("integration")
    @Title("CVSB-10775 - CVS to EDH (Technical records) - TC5 - AC5 - PUT request is made and EDH responds back with HTTP Error code 401")
    public void testVehiclePutHttpCode401() {
        // TEST SETUP
        // generate random systemNumber
        String randomSystemNumber = "e4404401-7e57-c0de-e401-e4404c0de401";
        // generate random Vin
        String randomVin = GenericData.generateRandomVin();
        // generate random Vrm
        String randomVrm = GenericData.generateRandomVrm();

        // read post request body from file
        String insertBody = GenericData.readJsonValueFromFile("technical-records_hgv_insert_10775.json", "$");
        String requestBody = GenericData.readJsonValueFromFile("technical-records_integration_hgv_10775.json", "$");

        // create alterations to change attributes
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationVrm = new JsonPathAlteration("$.primaryVrm", randomVrm, "", "REPLACE");
        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$.systemNumber", randomSystemNumber, "", "REPLACE");
        // read the adr details from the file used for put request body with battery adr details
        String adrDetails = GenericData.readJsonValueFromFile("technical-records_adr_details_other_nulls.json","$.techRecord[0].adrDetails");

        // initialize the alterations list
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(
                alterationVin,
                alterationVrm,
                alterationSystemNumber
        ));

        //TEST
        vehicleTechnicalRecordsSteps.insertVehicleWithAlterations(insertBody, alterations);
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecords(randomVin);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord.size()", 1);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].systemNumber", randomSystemNumber);

        JsonPathAlteration alterationAddAdrDetails = new JsonPathAlteration("$.techRecord[0]", adrDetails,"adrDetails","ADD_FIELD");
        alterations.add(alterationAddAdrDetails);

        vehicleTechnicalRecordsSteps.putVehicleTechnicalRecordsForVehicleWithAlterations(randomVin, requestBody, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.validateResponseContainsJson("techRecord[1].adrDetails", adrDetails);
        vehicleTechnicalRecordsSteps.checkAwsDispatcherLogStatusCodeForSystemNumber("PUT", randomSystemNumber, 401);
        vehicleTechnicalRecordsSteps.deleteRecords(randomSystemNumber);

    }

    @WithTag("integration")
    @Title("CVSB-10775 - CVS to EDH (Technical records) - TC5 - AC5 - PUT request is made and EDH responds back with HTTP Error code 403")
    public void testVehiclePutHttpCode403() {
        // TEST SETUP
        // generate random systemNumber
        String randomSystemNumber = "e4404403-7e57-c0de-e403-e4404c0de403";
        // generate random Vin
        String randomVin = GenericData.generateRandomVin();
        // generate random Vrm
        String randomVrm = GenericData.generateRandomVrm();

        // read post request body from file
        String insertBody = GenericData.readJsonValueFromFile("technical-records_hgv_insert_10775.json", "$");
        String requestBody = GenericData.readJsonValueFromFile("technical-records_integration_hgv_10775.json", "$");

        // create alterations to change attributes
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationVrm = new JsonPathAlteration("$.primaryVrm", randomVrm, "", "REPLACE");
        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$.systemNumber", randomSystemNumber, "", "REPLACE");
        // read the adr details from the file used for put request body with battery adr details
        String adrDetails = GenericData.readJsonValueFromFile("technical-records_adr_details_other_nulls.json","$.techRecord[0].adrDetails");

        // initialize the alterations list
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(
                alterationVin,
                alterationVrm,
                alterationSystemNumber
        ));

        //TEST
        vehicleTechnicalRecordsSteps.insertVehicleWithAlterations(insertBody, alterations);
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecords(randomVin);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord.size()", 1);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].systemNumber", randomSystemNumber);

        JsonPathAlteration alterationAddAdrDetails = new JsonPathAlteration("$.techRecord[0]", adrDetails,"adrDetails","ADD_FIELD");
        alterations.add(alterationAddAdrDetails);

        vehicleTechnicalRecordsSteps.putVehicleTechnicalRecordsForVehicleWithAlterations(randomVin, requestBody, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.validateResponseContainsJson("techRecord[1].adrDetails", adrDetails);
        vehicleTechnicalRecordsSteps.checkAwsDispatcherLogStatusCodeForSystemNumber("PUT", randomSystemNumber, 403);
        vehicleTechnicalRecordsSteps.deleteRecords(randomSystemNumber);

    }

    @WithTag("integration")
    @Title("CVSB-10775 - CVS to EDH (Technical records) - TC5 - AC5 - PUT request is made and EDH responds back with HTTP Error code 404")
    public void testVehiclePutHttpCode404() {
        // TEST SETUP
        // generate random systemNumber
        String randomSystemNumber = "e4404404-7e57-c0de-e404-e4404c0de404";
        // generate random Vin
        String randomVin = GenericData.generateRandomVin();
        // generate random Vrm
        String randomVrm = GenericData.generateRandomVrm();

        // read post request body from file
        String insertBody = GenericData.readJsonValueFromFile("technical-records_hgv_insert_10775.json", "$");
        String requestBody = GenericData.readJsonValueFromFile("technical-records_integration_hgv_10775.json", "$");

        // create alterations to change attributes
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationVrm = new JsonPathAlteration("$.primaryVrm", randomVrm, "", "REPLACE");
        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$.systemNumber", randomSystemNumber, "", "REPLACE");
        // read the adr details from the file used for put request body with battery adr details
        String adrDetails = GenericData.readJsonValueFromFile("technical-records_adr_details_other_nulls.json","$.techRecord[0].adrDetails");

        // initialize the alterations list
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(
                alterationVin,
                alterationVrm,
                alterationSystemNumber
        ));

        //TEST
        vehicleTechnicalRecordsSteps.insertVehicleWithAlterations(insertBody, alterations);
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecords(randomVin);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord.size()", 1);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].systemNumber", randomSystemNumber);

        JsonPathAlteration alterationAddAdrDetails = new JsonPathAlteration("$.techRecord[0]", adrDetails,"adrDetails","ADD_FIELD");
        alterations.add(alterationAddAdrDetails);

        vehicleTechnicalRecordsSteps.putVehicleTechnicalRecordsForVehicleWithAlterations(randomVin, requestBody, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.validateResponseContainsJson("techRecord[1].adrDetails", adrDetails);
        vehicleTechnicalRecordsSteps.checkAwsDispatcherLogStatusCodeForSystemNumber("PUT", randomSystemNumber, 404);
        vehicleTechnicalRecordsSteps.deleteRecords(randomSystemNumber);

    }

    @WithTag("integration")
    @Title("CVSB-10775 - CVS to EDH (Technical records) - TC6 - AC6 - PUT request is made and EDH responds back with HTTP Error code 429")
    public void testVehiclePutHttpCode429() {
        // TEST SETUP
        // generate random systemNumber
        String randomSystemNumber = "e4404429-7e57-c0de-e429-e4404c0de429";
        // generate random Vin
        String randomVin = GenericData.generateRandomVin();
        // generate random Vrm
        String randomVrm = GenericData.generateRandomVrm();

        // read post request body from file
        String insertBody = GenericData.readJsonValueFromFile("technical-records_hgv_insert_10775.json", "$");
        String requestBody = GenericData.readJsonValueFromFile("technical-records_integration_hgv_10775.json", "$");

        // create alterations to change attributes
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationVrm = new JsonPathAlteration("$.primaryVrm", randomVrm, "", "REPLACE");
        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$.systemNumber", randomSystemNumber, "", "REPLACE");
        // read the adr details from the file used for put request body with battery adr details
        String adrDetails = GenericData.readJsonValueFromFile("technical-records_adr_details_other_nulls.json","$.techRecord[0].adrDetails");

        // initialize the alterations list
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(
                alterationVin,
                alterationVrm,
                alterationSystemNumber
        ));

        //TEST
        vehicleTechnicalRecordsSteps.insertVehicleWithAlterations(insertBody, alterations);
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecords(randomVin);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord.size()", 1);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].systemNumber", randomSystemNumber);

        JsonPathAlteration alterationAddAdrDetails = new JsonPathAlteration("$.techRecord[0]", adrDetails,"adrDetails","ADD_FIELD");
        alterations.add(alterationAddAdrDetails);

        vehicleTechnicalRecordsSteps.putVehicleTechnicalRecordsForVehicleWithAlterations(randomVin, requestBody, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.validateResponseContainsJson("techRecord[1].adrDetails", adrDetails);
        vehicleTechnicalRecordsSteps.checkAwsDispatcherLogStatusCodeForSystemNumber("PUT", randomSystemNumber, 429);
        vehicleTechnicalRecordsSteps.deleteRecords(randomSystemNumber);

    }

    @WithTag("integration")
    @Title("CVSB-10775 - CVS to EDH (Technical records) - TC6 - AC6 - PUT request is made and EDH responds back with HTTP Error code 500")
    public void testVehiclePutHttpCode500() {
        // TEST SETUP
        // generate random systemNumber
        String randomSystemNumber = "e4404500-7e57-c0de-e500-e4404c0de500";
        // generate random Vin
        String randomVin = GenericData.generateRandomVin();
        // generate random Vrm
        String randomVrm = GenericData.generateRandomVrm();

        // read post request body from file
        String insertBody = GenericData.readJsonValueFromFile("technical-records_hgv_insert_10775.json", "$");
        String requestBody = GenericData.readJsonValueFromFile("technical-records_integration_hgv_10775.json", "$");

        // create alterations to change attributes
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationVrm = new JsonPathAlteration("$.primaryVrm", randomVrm, "", "REPLACE");
        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$.systemNumber", randomSystemNumber, "", "REPLACE");
        // read the adr details from the file used for put request body with battery adr details
        String adrDetails = GenericData.readJsonValueFromFile("technical-records_adr_details_other_nulls.json","$.techRecord[0].adrDetails");

        // initialize the alterations list
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(
                alterationVin,
                alterationVrm,
                alterationSystemNumber
        ));

        //TEST
        vehicleTechnicalRecordsSteps.insertVehicleWithAlterations(insertBody, alterations);
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecords(randomVin);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("techRecord.size()", 1);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].systemNumber", randomSystemNumber);

        JsonPathAlteration alterationAddAdrDetails = new JsonPathAlteration("$.techRecord[0]", adrDetails,"adrDetails","ADD_FIELD");
        alterations.add(alterationAddAdrDetails);

        vehicleTechnicalRecordsSteps.putVehicleTechnicalRecordsForVehicleWithAlterations(randomVin, requestBody, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.validateResponseContainsJson("techRecord[1].adrDetails", adrDetails);
        vehicleTechnicalRecordsSteps.checkAwsDispatcherLogStatusCodeForSystemNumber("PUT", randomSystemNumber, 500);
        vehicleTechnicalRecordsSteps.deleteRecords(randomSystemNumber);

    }

}
