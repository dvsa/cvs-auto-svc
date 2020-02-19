package steps;

import clients.model.VehicleClass;
import clients.model.BodyType;
import clients.VehicleTechnicalRecordsClient;
import data.GenericData;
import exceptions.AutomationException;
import io.restassured.response.Response;
import model.vehicles.*;
import net.thucydides.core.annotations.Step;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.junit.Assert;
import org.skyscreamer.jsonassert.JSONAssert;
import util.AwsUtil;
import util.JsonPathAlteration;

import java.util.ArrayList;
import java.util.List;

import static data.GenericData.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.*;

public class VehicleTechnicalRecordsSteps {

    VehicleTechnicalRecordsClient vehicleTechnicalRecordsClient = new VehicleTechnicalRecordsClient();
    Response response;

    @Step
    public String getVehicleTechnicalRecords(String searchIdentifier) {
        response = vehicleTechnicalRecordsClient.getVehicleTechnicalRecords(searchIdentifier);
        return response.prettyPrint();
    }

    @Step
    public String getVehicleTechnicalRecordsByStatus(String searchIdentifier, @NotNull VehicleTechnicalRecordStatus status) {
        response = vehicleTechnicalRecordsClient.getVehicleTechnicalRecordsByStatus(searchIdentifier, status.getStatus());
        return response.prettyPrint();
    }

    @Step
    public String getVehicleTechnicalRecordsByStatusAndSearchCriteria(String searchIdentifier,
                                                                    @NotNull VehicleTechnicalRecordStatus status,
                                                                    @NotNull VehicleTechnicalRecordSearchCriteria criteria) {
        response = vehicleTechnicalRecordsClient.
                getVehicleTechnicalRecordsByStatusAndSearchCriteria(searchIdentifier, status.getStatus(), criteria.getSearchCriteria());
        return response.prettyPrint();
    }

    @Step
    public String getVehicleTechnicalRecordsBySystemNumber(String searchIdentifier) {
        response = vehicleTechnicalRecordsClient.getVehicleTechnicalRecordsBySystemNumber(searchIdentifier);
        return response.prettyPrint();
    }

    @Step
    public String getVehicleTechnicalRecordsByPartialVin(String searchIdentifier) {
        String partialVin = searchIdentifier.substring(searchIdentifier.length() - 6);
        return getVehicleTechnicalRecords(partialVin);
    }


    @Step
    public String getVehicleTechnicalRecordsByPartialVinAndStatus(String searchIdentifier, VehicleTechnicalRecordStatus status) {
        String partialVin = searchIdentifier.substring(searchIdentifier.length() - 6);
        return getVehicleTechnicalRecordsByStatus(partialVin, status);
    }


    @Step
    public void statusCodeShouldBe(int statusCode) {
        response.then().log().all()
                .statusCode(statusCode);
    }

    @Step
    public void valueForFieldInPathShouldBe(String path, String expectedValue) {
        response.then().body(path, equalTo(expectedValue));
    }

    @Step
    public void valueForFieldInPathShouldBe(String path, boolean expectedValue) {
        response.then().body(path, equalTo(expectedValue));
    }

    @Step
    public void valueForFieldInPathShouldBe(String path, int expectedValue) {
        response.then().body(path, equalTo(expectedValue));
    }

    @Step
    public void valueForFieldInPathShouldBe(String path, Object expectedValue) {
        response.then().body(path, equalTo(expectedValue));
    }

    @Step
    public void valueForFieldInPathShouldNotBe(String path, Object expectedValue) {
        response.then().body(path, not(equalTo(expectedValue)));
    }

    @Step
    public void valueForFieldInPathShouldEndWith(String path, String expectedValue) {
        response.then().body(path, endsWith(expectedValue));
    }

    @Step
    public void valuesForFieldsInPathShouldEqual(String jsonPath1, String jsonPath2) {
        response.then().body(jsonPath1, equalTo(response.then().extract().path(jsonPath2)));
    }

    @Step
    public void valuesForFieldsInPathShouldNotEqual(String jsonPath1, String jsonPath2) {
        response.then().body(jsonPath1, not(equalTo(response.then().extract().path(jsonPath2))));
    }

    @Step
    public void fieldInPathShouldExist(String parentElementPath, String key) {
        response.then().body(parentElementPath,hasKey(key));
    }

    @Step
    public void fieldInPathShouldNotExist(String parentElementPath, String key) {
        response.then().body(parentElementPath,not(hasKey(key)));
    }

    @Step
    public void validateData(Vehicle vehicleTechnicalRecordsData, VehicleTechnicalRecordStatus vehicleTechnicalRecordStatus) {

        int index = 0;
        boolean found = false;

        for (TechRecord techRecord : vehicleTechnicalRecordsData.getTechRecord()) {
            if (techRecord.getStatusCode().equalsIgnoreCase(vehicleTechnicalRecordStatus.getStatus())) {
                validateDataByIndex(vehicleTechnicalRecordsData, index);
                found = true;
            }
            index++;
        }
        if (!found) {
            throw new AutomationException("Vehicle with vin " + vehicleTechnicalRecordsData.getVin() + " has't got expected status " + vehicleTechnicalRecordStatus.getStatus() + " please check data");
        }
    }

    private void validateDataByIndex(Vehicle vehicle, int index) {
        response.then().body("[0].size()", is(Vehicle.class.getDeclaredFields().length-1));
        response.then().body("[0].vrms.vrm", hasItem(equalTo(vehicle.getVrms().get(0).getVrm())));
        response.then().body("[0].vrms.isPrimary", hasItem(equalTo(vehicle.getVrms().get(0).getPrimary())));

        response.then().body("[0].vin", equalTo(vehicle.getVin()));


        response.then().body("[0].techRecord.size", equalTo(1));
        //response.then().body("[0].techRecord[0].size()", is(TechRecord.class.getDeclaredFields().length));
        response.then().body("[0].techRecord.createdAt", hasItem(equalTo(vehicle.getTechRecord().get(index).getCreatedAt())));
        response.then().body("[0].techRecord.lastUpdatedAt", hasItem(equalTo(vehicle.getTechRecord().get(index).getLastUpdatedAt())));
        response.then().body("[0].techRecord.chassisMake", hasItem(equalTo(vehicle.getTechRecord().get(index).getChassisMake())));
        response.then().body("[0].techRecord.chassisModel", hasItem(equalTo(vehicle.getTechRecord().get(index).getChassisModel())));
        response.then().body("[0].techRecord.bodyMake", hasItem(equalTo(vehicle.getTechRecord().get(index).getBodyMake())));
        response.then().body("[0].techRecord.bodyModel", hasItem(equalTo(vehicle.getTechRecord().get(index).getBodyModel())));
        response.then().body("[0].techRecord.manufactureYear", hasItem(equalTo(vehicle.getTechRecord().get(index).getManufactureYear())));
        response.then().body("[0].techRecord.regnDate", hasItem(equalTo(vehicle.getTechRecord().get(index).getRegnDate())));
        response.then().body("[0].techRecord.coifDate", hasItem(equalTo(vehicle.getTechRecord().get(index).getCoifDate())));
        response.then().body("[0].techRecord.ntaNumber", hasItem(equalTo(vehicle.getTechRecord().get(index).getNtaNumber())));
        response.then().body("[0].techRecord.conversionRefNo", hasItem(equalTo(vehicle.getTechRecord().get(index).getConversionRefNo())));
        response.then().body("[0].techRecord.seatsLowerDeck", hasItem(equalTo(vehicle.getTechRecord().get(index).getSeatsLowerDeck())));
        response.then().body("[0].techRecord.seatsUpperDeck", hasItem(equalTo(vehicle.getTechRecord().get(index).getSeatsUpperDeck())));
        response.then().body("[0].techRecord.standingCapacity", hasItem(equalTo(vehicle.getTechRecord().get(index).getStandingCapacity())));
        response.then().body("[0].techRecord.speedRestriction", hasItem(equalTo(vehicle.getTechRecord().get(index).getSpeedRestriction())));
        response.then().body("[0].techRecord.speedLimiterMrk", hasItem(equalTo(vehicle.getTechRecord().get(index).getSpeedLimiterMrk())));
        response.then().body("[0].techRecord.tachoExemptMrk", hasItem(equalTo(vehicle.getTechRecord().get(index).getTachoExemptMrk())));
        response.then().body("[0].techRecord.dispensations", hasItem(equalTo(vehicle.getTechRecord().get(index).getDispensations())));
        response.then().body("[0].techRecord.remarks", hasItem(equalTo(vehicle.getTechRecord().get(index).getRemarks())));
        response.then().body("[0].techRecord.reasonForCreation", hasItem(equalTo(vehicle.getTechRecord().get(index).getReasonForCreation())));
        response.then().body("[0].techRecord.statusCode", hasItem(equalTo(vehicle.getTechRecord().get(index).getStatusCode())));
        response.then().body("[0].techRecord.unladenWeight", hasItem(equalTo(vehicle.getTechRecord().get(index).getUnladenWeight())));
        response.then().body("[0].techRecord.grossKerbWeight", hasItem(equalTo(vehicle.getTechRecord().get(index).getGrossKerbWeight())));
        response.then().body("[0].techRecord.grossLadenWeight", hasItem(equalTo(vehicle.getTechRecord().get(index).getGrossLadenWeight())));
        response.then().body("[0].techRecord.grossGbWeight", hasItem(equalTo(vehicle.getTechRecord().get(index).getGrossGbWeight())));
        response.then().body("[0].techRecord.grossDesignWeight", hasItem(equalTo(vehicle.getTechRecord().get(index).getGrossDesignWeight())));
        response.then().body("[0].techRecord.noOfAxles", hasItem(equalTo(vehicle.getTechRecord().get(index).getNoOfAxles())));
        response.then().body("[0].techRecord.brakeCode", hasItem(equalTo(vehicle.getTechRecord().get(index).getBrakeCode())));
        response.then().body("[0].techRecord.vehicleType", hasItem(equalTo(vehicle.getTechRecord().get(index).getVehicleType())));
        response.then().body("[0].techRecord.vehicleSize", hasItem(equalTo(vehicle.getTechRecord().get(index).getVehicleSize())));
        response.then().body("[0].techRecord.vehicleConfiguration", hasItem(equalTo(vehicle.getTechRecord().get(index).getVehicleConfiguration())));

        response.then().body("[0].techRecord[0].vehicleClass.size()", is(VehicleClass.class.getDeclaredFields().length - VehicleClass.class.getFields().length - 1));
        response.then().body("[0].techRecord.vehicleClass.code", hasItem(equalTo(vehicle.getTechRecord().get(index).getVehicleClass().getCode())));
        response.then().body("[0].techRecord.vehicleClass.description", hasItem(equalTo(vehicle.getTechRecord().get(index).getVehicleClass().getDescription())));

        response.then().body("[0].techRecord[0].bodyType.size()", is(BodyType.class.getDeclaredFields().length - BodyType.class.getFields().length - 1));
        response.then().body("[0].techRecord.bodyType.code", hasItem(equalTo(vehicle.getTechRecord().get(index).getBodyType().getCode())));
        response.then().body("[0].techRecord.bodyType.description", hasItem(equalTo(vehicle.getTechRecord().get(index).getBodyType().getDescription())));


        response.then().body("[0].techRecord.brakes[0].size()", is(Brakes.class.getDeclaredFields().length));
        response.then().body("[0].techRecord.brakes.brakeCodeOriginal", hasItem(equalTo(vehicle.getTechRecord().get(index).getBrakes().getBrakeCodeOriginal())));
        response.then().body("[0].techRecord.brakes.brakeCode", hasItem(equalTo(vehicle.getTechRecord().get(index).getBrakes().getBrakeCode())));
        response.then().body("[0].techRecord.brakes.dataTrBrakeOne", hasItem(equalTo(vehicle.getTechRecord().get(index).getBrakes().getDataTrBrakeOne())));
        response.then().body("[0].techRecord.brakes.dataTrBrakeTwo", hasItem(equalTo(vehicle.getTechRecord().get(index).getBrakes().getDataTrBrakeTwo())));
        response.then().body("[0].techRecord.brakes.dataTrBrakeThree", hasItem(equalTo(vehicle.getTechRecord().get(index).getBrakes().getDataTrBrakeThree())));
        response.then().body("[0].techRecord.brakes.retarderBrakeOne", hasItem(equalTo(vehicle.getTechRecord().get(index).getBrakes().getRetarderBrakeOne())));
        response.then().body("[0].techRecord.brakes.retarderBrakeTwo", hasItem(equalTo(vehicle.getTechRecord().get(index).getBrakes().getRetarderBrakeTwo())));

        response.then().body("[0].techRecord.brakes.brakeForceWheelsNotLocked[0].size()", is(BrakeForceWheelsNotLocked.class.getDeclaredFields().length));
        response.then().body("[0].techRecord.brakes.brakeForceWheelsNotLocked.serviceBrakeForceA", hasItem(equalTo(vehicle.getTechRecord().get(index).getBrakes().getBrakeForceWheelsNotLocked().getServiceBrakeForceA())));
        response.then().body("[0].techRecord.brakes.brakeForceWheelsNotLocked.secondaryBrakeForceA", hasItem(equalTo(vehicle.getTechRecord().get(index).getBrakes().getBrakeForceWheelsNotLocked().getSecondaryBrakeForceA())));
        response.then().body("[0].techRecord.brakes.brakeForceWheelsNotLocked.parkingBrakeForceA", hasItem(equalTo(vehicle.getTechRecord().get(index).getBrakes().getBrakeForceWheelsNotLocked().getParkingBrakeForceA())));

        response.then().body("[0].techRecord.brakes.brakeForceWheelsUpToHalfLocked[0].size()", is(BrakeForceWheelsUpToHalfLocked.class.getDeclaredFields().length));
        response.then().body("[0].techRecord.brakes.brakeForceWheelsUpToHalfLocked.serviceBrakeForceB", hasItem(equalTo(vehicle.getTechRecord().get(index).getBrakes().getBrakeForceWheelsUpToHalfLocked().getServiceBrakeForceB())));
        response.then().body("[0].techRecord.brakes.brakeForceWheelsUpToHalfLocked.secondaryBrakeForceB", hasItem(equalTo(vehicle.getTechRecord().get(index).getBrakes().getBrakeForceWheelsUpToHalfLocked().getSecondaryBrakeForceB())));
        response.then().body("[0].techRecord.brakes.brakeForceWheelsUpToHalfLocked.parkingBrakeForceB", hasItem(equalTo(vehicle.getTechRecord().get(index).getBrakes().getBrakeForceWheelsUpToHalfLocked().getParkingBrakeForceB())));

        response.then().body("[0].techRecord[0].axles.size()", is(vehicle.getTechRecord().get(index).getAxles().size()));
        int listIndex = 0;
        for (Axles axle : vehicle.getTechRecord().get(index).getAxles()) {
            response.then().body("[0].techRecord[0].axles[" + listIndex + "].size()", is(Axles.class.getDeclaredFields().length));
            response.then().body("[0].techRecord.axles.axleNumber", hasItem(hasItem(equalTo(axle.getAxleNumber()))));
            response.then().body("[0].techRecord.axles.parkingBrakeMrk", hasItem(hasItem(equalTo(axle.getParkingBrakeMrk()))));

            response.then().body("[0].techRecord[0].axles[" + listIndex + "].tyres.size()", is(Tyres.class.getDeclaredFields().length));
            response.then().body("[0].techRecord.axles.tyres.tyreSize", hasItem(hasItem(equalTo(axle.getTyres().getTyreSize()))));
            response.then().body("[0].techRecord.axles.tyres.plyRating", hasItem(hasItem(equalTo(axle.getTyres().getPlyRating()))));
            response.then().body("[0].techRecord.axles.tyres.fitmentCode", hasItem(hasItem(equalTo(axle.getTyres().getFitmentCode()))));
            response.then().body("[0].techRecord.axles.tyres.dataTrAxles", hasItem(hasItem(equalTo(axle.getTyres().getDataTrAxles()))));
            response.then().body("[0].techRecord.axles.tyres.speedCategorySymbol", hasItem(hasItem(equalTo(axle.getTyres().getSpeedCategorySymbol()))));
            response.then().body("[0].techRecord.axles.tyres.tyreCode", hasItem(hasItem(equalTo(axle.getTyres().getTyreCode()))));

            response.then().body("[0].techRecord[0].axles[" + listIndex + "].weights.size()", is(Weights.class.getDeclaredFields().length));
            response.then().body("[0].techRecord.axles.weights.kerbWeight", hasItem(hasItem(equalTo(vehicle.getTechRecord().get(index).getAxles().get(0).getWeights().getKerbWeight()))));
            response.then().body("[0].techRecord.axles.weights.ladenWeight", hasItem(hasItem(equalTo(vehicle.getTechRecord().get(index).getAxles().get(0).getWeights().getLadenWeight()))));
            response.then().body("[0].techRecord.axles.weights.gbWeight", hasItem(hasItem(equalTo(vehicle.getTechRecord().get(index).getAxles().get(0).getWeights().getGbWeight()))));
            response.then().body("[0].techRecord.axles.weights.designWeight", hasItem(hasItem(equalTo(vehicle.getTechRecord().get(index).getAxles().get(0).getWeights().getDesignWeight()))));

        }

    }

    @Step
    public void validateData(String stringData) {
        response.then().body(is("\"" + stringData + "\""));
    }

    @Step
    public String postVehicleTechnicalRecords(String requestBody) {
        response = vehicleTechnicalRecordsClient.postVehicleTechnicalRecords(requestBody);
        return response.prettyPrint();
    }

    @Step
    public String postVehicleTechnicalRecordsWithAlterations(String requestBody, List<JsonPathAlteration> alterations) {
        response = vehicleTechnicalRecordsClient.postVehicleTechnicalRecordsWithAlterations(requestBody, alterations);
        return response.prettyPrint();
    }

    @Step
    public String putVehicleTechnicalRecordsForVehicle(String vin, String requestBody) {
        response = vehicleTechnicalRecordsClient.putVehicleTechnicalRecordsForVehicle(vin, requestBody);
        return response.prettyPrint();
    }

    @Step
    public String putVehicleTechnicalRecordsForVehicleWithAlterations(String vin, String putRequestBody, List<JsonPathAlteration> alterations) {
        response = vehicleTechnicalRecordsClient.putVehicleTechnicalRecordsWithAlterations(vin, putRequestBody, alterations);
        return response.prettyPrint();
    }

    @Step
    public void validateResponseContainsJson(String jsonPathOfResponseExtractedField, String expectedJson) {
        String actualJson = GenericData.getJsonStringFromHashMap(response.then().extract().path(jsonPathOfResponseExtractedField));
        try {
            JSONAssert.assertEquals("The response does not contain the required data", expectedJson,
                    actualJson, false);
        } catch (final JSONException exc) {
            throw new RuntimeException(exc);
        }

    }

    @Step
    public void validateTechRecordContainsField(String field) {
        response.then().body("[0].techRecord[0]", hasKey(field));
    }

    @Step
    public void validateTechRecordFieldIsOfType(String field, Class type) {
        response.then().body("[0].techRecord[0]." + field, instanceOf(type));
    }

    @Step
    public Response downloadFile(String searchIdentifier, String fileName) {
        return vehicleTechnicalRecordsClient.downloadFile(searchIdentifier, fileName);
    }

    @Step
    public void waitForVehicleTechRecordsToBeUpdated(String vin, int seconds) {

        System.out.println("...waiting " + seconds + " seconds for the vehicle tech record to be updated...\n");

        for(int i=0; i < seconds; i++) {
            response = vehicleTechnicalRecordsClient.getVehicleTechnicalRecordsByStatus(vin, "all");

            int status = response.getStatusCode();
            int noVehicles = response.then().extract().jsonPath().getInt("$.size()");
            for (int j = 0; j < noVehicles; j++) {

                int recordsNumber = response.then().log().all().extract().jsonPath().get("[" + j + "].techRecord.size()");

                System.out.println(" for vehicle [" + j + "] status is: " + status + " and number of records: " + recordsNumber);

                if (status == 200 && recordsNumber > 1) {
                    try {
                        Thread.sleep(2500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return;
                } else {
                    System.out.println("\n...waiting one more second (" + i + ")...\n");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        System.out.println("\n...Vehicle status has not been updated in " + seconds +" seconds...");
    }

    @Step
    public void valueForFieldInTechRecordShouldBe(String systemNumber, String vin, int techRecordNo, String field, String value) {
        try {
            JSONArray jsonArray = new JSONArray(response.body().asString());
            for (int i = 0; i < jsonArray.length(); i++) {
                if (jsonArray.getJSONObject(i).get("systemNumber").equals(systemNumber) && jsonArray.getJSONObject(i).get("vin").equals(vin)) {
//                    System.out.println("i: "+ i + "  " + jsonArray.getJSONObject(i).getJSONArray("techRecord").getJSONObject(techRecordNo).get(field).toString());
                    Assert.assertEquals(value, jsonArray.getJSONObject(i).getJSONArray("techRecord").getJSONObject(techRecordNo).get(field).toString());
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Step
    public boolean checkValueForFieldInAnyTechRecord(String systemNumber, String vin, String field, String value) {
        try {
            JSONArray jsonArray = new JSONArray(response.body().asString());
            for (int i = 0; i < jsonArray.length(); i++) {
                if (jsonArray.getJSONObject(i).get("systemNumber").equals(systemNumber) && jsonArray.getJSONObject(i).get("vin").equals(vin)) {
                    JSONArray techRecords = jsonArray.getJSONObject(i).getJSONArray("techRecord");
                    for (int j = 0; j < techRecords.length(); j++) {
                        if (techRecords.getJSONObject(j).get(field).toString().equals(value)) {
                            return true;
                        }
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Step
    public void valueForFieldInAnyTechRecordShouldBe2(String systemNumber, String vin, String field, String value) {
        Assert.assertTrue(checkValueForFieldInAnyTechRecord(systemNumber, vin, field, value));
    }

    @Step
    public void valueForFieldInAnyTechRecordShouldBe(String systemNumber, String vin, String fieldName, String value){
        String jsonString = response.body().asString();
        ArrayList<String> list = extractArrayListStringFromJsonString(jsonString,"$[?(@.systemNumber=='" + systemNumber + "' && @.vin=='" + vin + "') ].techRecord[*]." + fieldName);
        Assert.assertTrue(list.contains(value));
    }

    @Step
    public void valueForFieldInAnyTechRecordShouldBe(String systemNumber, String vin, String fieldName, int value){
        String jsonString = response.body().asString();
        ArrayList<Integer> list = extractArrayListIntegerFromJsonString(jsonString,"$[?(@.systemNumber=='" + systemNumber + "' && @.vin=='" + vin + "') ].techRecord[*]." + fieldName);
        Assert.assertTrue(list.contains(value));
    }

    @Step
    public String getVehicleClassCodeFromDescription(String vehicleClassDescription) {
        String vehicleClassCode = null;
        for (VehicleClass v : VehicleClass.values()) {
            if (v.getDescription().equals(vehicleClassDescription)) {
                vehicleClassCode = v.getCode();
                break;
            }
        }
        if (vehicleClassCode == null) {
            throw new AutomationException("Vehicle class description is not valid");
        }
        return vehicleClassCode;
    }

    @Step
    public String getBodyTypeCodeFromDescription(String bodyTypeDescription) {
        String bodyTypeCode = null;
        for (BodyType b : BodyType.values()) {
            if (b.getDescription().equals(bodyTypeDescription)) {
                bodyTypeCode = b.getCode();
            }
        }
        if (bodyTypeCode == null) {
            throw new AutomationException("Body type description is not valid");
        }
        return bodyTypeCode;
    }

    @Step
    public String getNextSystemNumberInSequence() {
        return AwsUtil.getNextSystemNumberInSequence();
    }

    @Step
    public String getNextTrailerIdInSequence() {
        return AwsUtil.getNextTrailerIdInSequence();
    }

    public void checkAwsDispatcherLogStatusCodeForSystemNumber(String httpMethod, String systemNumber, int httpCode) {
        String keyValuePair1 = "\"systemNumber\":\"" + systemNumber + "\"";
        String keyValuePair2 = "statusCode: " + httpCode;
        String keyValuePair3 = "method: '" + httpMethod +"'";
        assertThat(AwsUtil.checkDispatcherLogsForData(keyValuePair1, keyValuePair2, keyValuePair3)).isTrue();
    }

    @Step
    public void deleteRecords(String systemNumber) {
        AwsUtil.deleteVehicleById(systemNumber);
    }


    @Step
    public void insertVehicleWithAlterations(String requestBody, List<JsonPathAlteration> alterations) {
        vehicleTechnicalRecordsClient.insertVehicle(requestBody, alterations);
    }

}
