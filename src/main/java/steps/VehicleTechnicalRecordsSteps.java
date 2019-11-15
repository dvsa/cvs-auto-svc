package steps;

import clients.VehicleTechnicalRecordsClient;
import data.GenericData;
import exceptions.AutomationException;
import io.restassured.response.Response;
import model.vehicles.*;
import net.thucydides.core.annotations.Step;
import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.skyscreamer.jsonassert.JSONAssert;
import util.JsonPathAlteration;

import java.util.List;

import static org.hamcrest.Matchers.*;

public class VehicleTechnicalRecordsSteps {

    VehicleTechnicalRecordsClient vehicleTechnicalRecordsClient = new VehicleTechnicalRecordsClient();
    Response response;

    @Step
    public void getVehicleTechnicalRecords(String searchIdentifier) {
        this.response = vehicleTechnicalRecordsClient.getVehicleTechnicalRecords(searchIdentifier);
    }

    @Step
    public void getVehicleTechnicalRecordsByStatus(String searchIdentifier, @NotNull VehicleTechnicalRecordStatus status) {
        this.response = vehicleTechnicalRecordsClient.getVehicleTechnicalRecordsByStatus(searchIdentifier, status.getStatus());
    }

    @Step
    public void getVehicleTechnicalRecordsByPartialVim(String searchIdentifier) {
        String partialVim = searchIdentifier.substring(searchIdentifier.length() - 6);
        getVehicleTechnicalRecords(partialVim);
    }


    @Step
    public void getVehicleTechnicalRecordsByPartialVimAndStatus(String searchIdentifier, VehicleTechnicalRecordStatus status) {
        String partialVim = searchIdentifier.substring(searchIdentifier.length() - 6);
        getVehicleTechnicalRecordsByStatus(partialVim, status);
    }


    @Step
    public void statusCodeShouldBe(int statusCode) {
        response.then()
                .log().all()
                .statusCode(statusCode);
    }

    @Step
    public void valueForFieldInPathShouldBe(String path, String expectedValue) {
        response.then().body(path, equalTo(expectedValue));
    }

    @Step
    public void valueForFieldInPathShouldEndWith(String path, String expectedValue) {
        response.then().body(path, endsWith(expectedValue));
    }

    @Step
    public void valueForFieldInPathShouldBe(String path, int expectedValue) {
        response.then().body(path, equalTo(expectedValue));
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
            throw new AutomationException("Vehicle with vim " + vehicleTechnicalRecordsData.getVin() + " has't got expected status " + vehicleTechnicalRecordStatus.getStatus() + " please check data");
        }
    }

    private void validateDataByIndex(Vehicle vehicle, int index) {
        response.then().body("size()", is(Vehicle.class.getDeclaredFields().length));
        response.then().body("vrms.vrm", hasItem(equalTo(vehicle.getVrms().get(0).getVrm())));
        response.then().body("vrms.isPrimary", hasItem(equalTo(vehicle.getVrms().get(0).getPrimary())));

        response.then().body("vin", equalTo(vehicle.getVin()));


        response.then().body("techRecord.size", equalTo(1));
        response.then().body("techRecord[0].size()", is(TechRecord.class.getDeclaredFields().length));
        response.then().body("techRecord.createdAt", hasItem(equalTo(vehicle.getTechRecord().get(index).getCreatedAt())));
        response.then().body("techRecord.lastUpdatedAt", hasItem(equalTo(vehicle.getTechRecord().get(index).getLastUpdatedAt())));
        response.then().body("techRecord.chassisMake", hasItem(equalTo(vehicle.getTechRecord().get(index).getChassisMake())));
        response.then().body("techRecord.chassisModel", hasItem(equalTo(vehicle.getTechRecord().get(index).getChassisModel())));
        response.then().body("techRecord.bodyMake", hasItem(equalTo(vehicle.getTechRecord().get(index).getBodyMake())));
        response.then().body("techRecord.bodyModel", hasItem(equalTo(vehicle.getTechRecord().get(index).getBodyModel())));
        response.then().body("techRecord.manufactureYear", hasItem(equalTo(vehicle.getTechRecord().get(index).getManufactureYear())));
        response.then().body("techRecord.regnDate", hasItem(equalTo(vehicle.getTechRecord().get(index).getRegnDate())));
        response.then().body("techRecord.coifDate", hasItem(equalTo(vehicle.getTechRecord().get(index).getCoifDate())));
        response.then().body("techRecord.ntaNumber", hasItem(equalTo(vehicle.getTechRecord().get(index).getNtaNumber())));
        response.then().body("techRecord.conversionRefNo", hasItem(equalTo(vehicle.getTechRecord().get(index).getConversionRefNo())));
        response.then().body("techRecord.seatsLowerDeck", hasItem(equalTo(vehicle.getTechRecord().get(index).getSeatsLowerDeck())));
        response.then().body("techRecord.seatsUpperDeck", hasItem(equalTo(vehicle.getTechRecord().get(index).getSeatsUpperDeck())));
        response.then().body("techRecord.standingCapacity", hasItem(equalTo(vehicle.getTechRecord().get(index).getStandingCapacity())));
        response.then().body("techRecord.speedRestriction", hasItem(equalTo(vehicle.getTechRecord().get(index).getSpeedRestriction())));
        response.then().body("techRecord.speedLimiterMrk", hasItem(equalTo(vehicle.getTechRecord().get(index).getSpeedLimiterMrk())));
        response.then().body("techRecord.tachoExemptMrk", hasItem(equalTo(vehicle.getTechRecord().get(index).getTachoExemptMrk())));
        response.then().body("techRecord.dispensations", hasItem(equalTo(vehicle.getTechRecord().get(index).getDispensations())));
        response.then().body("techRecord.remarks", hasItem(equalTo(vehicle.getTechRecord().get(index).getRemarks())));
        response.then().body("techRecord.reasonForCreation", hasItem(equalTo(vehicle.getTechRecord().get(index).getReasonForCreation())));
        response.then().body("techRecord.statusCode", hasItem(equalTo(vehicle.getTechRecord().get(index).getStatusCode())));
        response.then().body("techRecord.unladenWeight", hasItem(equalTo(vehicle.getTechRecord().get(index).getUnladenWeight())));
        response.then().body("techRecord.grossKerbWeight", hasItem(equalTo(vehicle.getTechRecord().get(index).getGrossKerbWeight())));
        response.then().body("techRecord.grossLadenWeight", hasItem(equalTo(vehicle.getTechRecord().get(index).getGrossLadenWeight())));
        response.then().body("techRecord.grossGbWeight", hasItem(equalTo(vehicle.getTechRecord().get(index).getGrossGbWeight())));
        response.then().body("techRecord.grossDesignWeight", hasItem(equalTo(vehicle.getTechRecord().get(index).getGrossDesignWeight())));
        response.then().body("techRecord.noOfAxles", hasItem(equalTo(vehicle.getTechRecord().get(index).getNoOfAxles())));
        response.then().body("techRecord.brakeCode", hasItem(equalTo(vehicle.getTechRecord().get(index).getBrakeCode())));
        response.then().body("techRecord.vehicleType", hasItem(equalTo(vehicle.getTechRecord().get(index).getVehicleType())));
        response.then().body("techRecord.vehicleSize", hasItem(equalTo(vehicle.getTechRecord().get(index).getVehicleSize())));
        response.then().body("techRecord.vehicleConfiguration", hasItem(equalTo(vehicle.getTechRecord().get(index).getVehicleConfiguration())));

        response.then().body("techRecord[0].vehicleClass.size()", is(VehicleClass.class.getDeclaredFields().length));
        response.then().body("techRecord.vehicleClass.code", hasItem(equalTo(vehicle.getTechRecord().get(index).getVehicleClass().getCode())));
        response.then().body("techRecord.vehicleClass.description", hasItem(equalTo(vehicle.getTechRecord().get(index).getVehicleClass().getDescription())));

        response.then().body("techRecord[0].bodyType.size()", is(BodyType.class.getDeclaredFields().length));
        response.then().body("techRecord.bodyType.code", hasItem(equalTo(vehicle.getTechRecord().get(index).getBodyType().getCode())));
        response.then().body("techRecord.bodyType.description", hasItem(equalTo(vehicle.getTechRecord().get(index).getBodyType().getDescription())));


        response.then().body("techRecord.brakes[0].size()", is(Brakes.class.getDeclaredFields().length));
        response.then().body("techRecord.brakes.brakeCodeOriginal", hasItem(equalTo(vehicle.getTechRecord().get(index).getBrakes().getBrakeCodeOriginal())));
        response.then().body("techRecord.brakes.brakeCode", hasItem(equalTo(vehicle.getTechRecord().get(index).getBrakes().getBrakeCode())));
        response.then().body("techRecord.brakes.dataTrBrakeOne", hasItem(equalTo(vehicle.getTechRecord().get(index).getBrakes().getDataTrBrakeOne())));
        response.then().body("techRecord.brakes.dataTrBrakeTwo", hasItem(equalTo(vehicle.getTechRecord().get(index).getBrakes().getDataTrBrakeTwo())));
        response.then().body("techRecord.brakes.dataTrBrakeThree", hasItem(equalTo(vehicle.getTechRecord().get(index).getBrakes().getDataTrBrakeThree())));
        response.then().body("techRecord.brakes.retarderBrakeOne", hasItem(equalTo(vehicle.getTechRecord().get(index).getBrakes().getRetarderBrakeOne())));
        response.then().body("techRecord.brakes.retarderBrakeTwo", hasItem(equalTo(vehicle.getTechRecord().get(index).getBrakes().getRetarderBrakeTwo())));

        response.then().body("techRecord.brakes.brakeForceWheelsNotLocked[0].size()", is(BrakeForceWheelsNotLocked.class.getDeclaredFields().length));
        response.then().body("techRecord.brakes.brakeForceWheelsNotLocked.serviceBrakeForceA", hasItem(equalTo(vehicle.getTechRecord().get(index).getBrakes().getBrakeForceWheelsNotLocked().getServiceBrakeForceA())));
        response.then().body("techRecord.brakes.brakeForceWheelsNotLocked.secondaryBrakeForceA", hasItem(equalTo(vehicle.getTechRecord().get(index).getBrakes().getBrakeForceWheelsNotLocked().getSecondaryBrakeForceA())));
        response.then().body("techRecord.brakes.brakeForceWheelsNotLocked.parkingBrakeForceA", hasItem(equalTo(vehicle.getTechRecord().get(index).getBrakes().getBrakeForceWheelsNotLocked().getParkingBrakeForceA())));

        response.then().body("techRecord.brakes.brakeForceWheelsUpToHalfLocked[0].size()", is(BrakeForceWheelsUpToHalfLocked.class.getDeclaredFields().length));
        response.then().body("techRecord.brakes.brakeForceWheelsUpToHalfLocked.serviceBrakeForceB", hasItem(equalTo(vehicle.getTechRecord().get(index).getBrakes().getBrakeForceWheelsUpToHalfLocked().getServiceBrakeForceB())));
        response.then().body("techRecord.brakes.brakeForceWheelsUpToHalfLocked.secondaryBrakeForceB", hasItem(equalTo(vehicle.getTechRecord().get(index).getBrakes().getBrakeForceWheelsUpToHalfLocked().getSecondaryBrakeForceB())));
        response.then().body("techRecord.brakes.brakeForceWheelsUpToHalfLocked.parkingBrakeForceB", hasItem(equalTo(vehicle.getTechRecord().get(index).getBrakes().getBrakeForceWheelsUpToHalfLocked().getParkingBrakeForceB())));

        response.then().body("techRecord[0].axles.size()", is(vehicle.getTechRecord().get(index).getAxles().size()));
        int listIndex = 0;
        for (Axles axle : vehicle.getTechRecord().get(index).getAxles()) {
            response.then().body("techRecord[0].axles[" + listIndex + "].size()", is(Axles.class.getDeclaredFields().length));
            response.then().body("techRecord.axles.axleNumber", hasItem(hasItem(equalTo(axle.getAxleNumber()))));
            response.then().body("techRecord.axles.parkingBrakeMrk", hasItem(hasItem(equalTo(axle.getParkingBrakeMrk()))));

            response.then().body("techRecord[0].axles[" + listIndex + "].tyres.size()", is(Tyres.class.getDeclaredFields().length));
            response.then().body("techRecord.axles.tyres.tyreSize", hasItem(hasItem(equalTo(axle.getTyres().getTyreSize()))));
            response.then().body("techRecord.axles.tyres.plyRating", hasItem(hasItem(equalTo(axle.getTyres().getPlyRating()))));
            response.then().body("techRecord.axles.tyres.fitmentCode", hasItem(hasItem(equalTo(axle.getTyres().getFitmentCode()))));
            response.then().body("techRecord.axles.tyres.dataTrAxles", hasItem(hasItem(equalTo(axle.getTyres().getDataTrAxles()))));
            response.then().body("techRecord.axles.tyres.speedCategorySymbol", hasItem(hasItem(equalTo(axle.getTyres().getSpeedCategorySymbol()))));
            response.then().body("techRecord.axles.tyres.tyreCode", hasItem(hasItem(equalTo(axle.getTyres().getTyreCode()))));

            response.then().body("techRecord[0].axles[" + listIndex + "].weights.size()", is(Weights.class.getDeclaredFields().length));
            response.then().body("techRecord.axles.weights.kerbWeight", hasItem(hasItem(equalTo(vehicle.getTechRecord().get(index).getAxles().get(0).getWeights().getKerbWeight()))));
            response.then().body("techRecord.axles.weights.ladenWeight", hasItem(hasItem(equalTo(vehicle.getTechRecord().get(index).getAxles().get(0).getWeights().getLadenWeight()))));
            response.then().body("techRecord.axles.weights.gbWeight", hasItem(hasItem(equalTo(vehicle.getTechRecord().get(index).getAxles().get(0).getWeights().getGbWeight()))));
            response.then().body("techRecord.axles.weights.designWeight", hasItem(hasItem(equalTo(vehicle.getTechRecord().get(index).getAxles().get(0).getWeights().getDesignWeight()))));

        }

    }

    @Step
    public void validateData(String stringData) {
        response.then().body(is("\"" + stringData + "\""));
    }


    @Step
    public void postVehicleTechnicalRecords(String requestBody) {
        this.response = vehicleTechnicalRecordsClient.postVehicleTechnicalRecords(requestBody);
    }

    @Step
    public void postVehicleTechnicalRecordsWithAlterations(String requestBody, List<JsonPathAlteration> alterations) {
        this.response = vehicleTechnicalRecordsClient.postVehicleTechnicalRecordsWithAlterations(requestBody, alterations);
    }

    @Step
    public void putVehicleTechnicalRecordsForVehicle(String vin, String requestBody) {
        this.response = vehicleTechnicalRecordsClient.putVehicleTechnicalRecordsForVehicle(vin, requestBody);
    }

    @Step
    public void validateFields(List<String> techRecordFields) {
        for (String field : techRecordFields) {
            System.out.println("Checking value at json path techRecord[0]." + field + "\n");
            this.valueForFieldInPathShouldBe("techRecord[0]." + field,
                    (GenericData.getJsonValueFromFile("technical-records_psv.json","$.techRecord[0]." + field).toString()));
        }
    }

    @Step
    public void putVehicleTechnicalRecordsForVehicleWithAlterations(String vin, String putRequestBody, List<JsonPathAlteration> alterations) {
        this.response = vehicleTechnicalRecordsClient.putVehicleTechnicalRecordsWithAlterations(vin, putRequestBody, alterations);
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
        response.then().body("techRecord[0]", hasKey(field));
    }

    @Step
    public void validateTechRecordFieldIsOfType(String field, Class type) {
        response.then().body("techRecord[0]." + field, instanceOf(type));
    }

}
