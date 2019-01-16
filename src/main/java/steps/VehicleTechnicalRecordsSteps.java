package steps;

import clients.VehicleTechnicalRecordsClient;
import io.restassured.response.Response;
import model.vehicles.TechRecord;
import model.vehicles.Vehicle;
import model.vehicles.VehicleTechnicalRecordStatus;
import net.thucydides.core.annotations.Step;

import static org.hamcrest.Matchers.*;

public class VehicleTechnicalRecordsSteps {

    VehicleTechnicalRecordsClient vehicleTechnicalRecordsClient = new VehicleTechnicalRecordsClient();
    Response response;

    @Step
    public void getVehicleTechnicalRecords(String searchIdentifier) {
        this.response = vehicleTechnicalRecordsClient.getVehicleTechnicalRecords(searchIdentifier);
    }

    @Step
    public void getVehicleTechnicalRecordsByStatus(String searchIdentifier, VehicleTechnicalRecordStatus status) {
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
        response.then().statusCode(statusCode);
    }

    @Step
    public void validateData(Vehicle vehicleTechnicalRecordsData, VehicleTechnicalRecordStatus vehicleTechnicalRecordStatus) {

        int index = 0;
        for (TechRecord techRecord : vehicleTechnicalRecordsData.getTechRecord()) {
            if (techRecord.getStatusCode().equalsIgnoreCase(vehicleTechnicalRecordStatus.getStatus())) {
                validateDataByIdex(vehicleTechnicalRecordsData, index);
            }
            index++;
        }
    }

    private void validateDataByIdex(Vehicle vehicle, int index) {
        response.then().body("vrms.vrm", hasItem(equalTo(vehicle.getVrms().get(0).getVrm())));
        response.then().body("vrms.isPrimary", hasItem(equalTo(vehicle.getVrms().get(0).getPrimary())));

        response.then().body("vin", equalTo(vehicle.getVim()));

        response.then().body("techRecord.size", equalTo(1));
        response.then().body("techRecord.chassisMake", hasItem(equalTo(vehicle.getTechRecord().get(index).getChassisMake())));
        response.then().body("techRecord.chassisModel", hasItem(equalTo(vehicle.getTechRecord().get(index).getChassisModel())));
        response.then().body("techRecord.bodyMake", hasItem(equalTo(vehicle.getTechRecord().get(index).getBodyMake())));
        response.then().body("techRecord.bodyModel", hasItem(equalTo(vehicle.getTechRecord().get(index).getBodyModel())));
        response.then().body("techRecord.bodyType", hasItem(equalTo(vehicle.getTechRecord().get(index).getBodyType())));
        response.then().body("techRecord.manufactureDate", hasItem(equalTo(vehicle.getTechRecord().get(index).getManufactureDate())));
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

        response.then().body("techRecord.grossUnladenWeight", hasItem(equalTo(vehicle.getTechRecord().get(index).getGrossUnladenWeight())));
        response.then().body("techRecord.noOfAxles", hasItem(equalTo(vehicle.getTechRecord().get(index).getNoOfAxles())));
        response.then().body("techRecord.brakeCode", hasItem(equalTo(vehicle.getTechRecord().get(index).getBrakeCode())));
        response.then().body("techRecord.vehicleClass", hasItem(equalTo(vehicle.getTechRecord().get(index).getVehicleClass())));
        response.then().body("techRecord.vehicleType", hasItem(equalTo(vehicle.getTechRecord().get(index).getVehicleType())));
        response.then().body("techRecord.vehicleSize", hasItem(equalTo(vehicle.getTechRecord().get(index).getVehicleSize())));
        response.then().body("techRecord.vehicleConfiguration", hasItem(equalTo(vehicle.getTechRecord().get(index).getVehicleConfiguration())));

        response.then().body("techRecord.brakes.brakeCode", hasItem(equalTo(vehicle.getTechRecord().get(index).getBrakes().getBrakeCode())));
        response.then().body("techRecord.brakes.dataTrBrakeOne", hasItem(equalTo(vehicle.getTechRecord().get(index).getBrakes().getDataTrBrakeOne())));
        response.then().body("techRecord.brakes.dataTrBrakeTwo", hasItem(equalTo(vehicle.getTechRecord().get(index).getBrakes().getDataTrBrakeTwo())));
        response.then().body("techRecord.brakes.dataTrBrakeThree", hasItem(equalTo(vehicle.getTechRecord().get(index).getBrakes().getDataTrBrakeThree())));
        response.then().body("techRecord.brakes.parkingBrakeMrk", hasItem(equalTo(vehicle.getTechRecord().get(index).getBrakes().getParkingBrakeMrk())));
        response.then().body("techRecord.brakes.retarderBrakeOne", hasItem(equalTo(vehicle.getTechRecord().get(index).getBrakes().getRetarderBrakeOne())));
        response.then().body("techRecord.brakes.retarderBrakeTwo", hasItem(equalTo(vehicle.getTechRecord().get(index).getBrakes().getRetarderBrakeTwo())));

        response.then().body("techRecord.brakes.brakeForceWheelsNotLocked.serviceBrakeForceA", hasItem(equalTo(vehicle.getTechRecord().get(index).getBrakes().getBrakeForceWheelsNotLocked().getServiceBrakeForceA())));
        response.then().body("techRecord.brakes.brakeForceWheelsNotLocked.secondaryBrakeForceA", hasItem(equalTo(vehicle.getTechRecord().get(index).getBrakes().getBrakeForceWheelsNotLocked().getSecondaryBrakeForceA())));
        response.then().body("techRecord.brakes.brakeForceWheelsNotLocked.parkingBrakeForceA", hasItem(equalTo(vehicle.getTechRecord().get(index).getBrakes().getBrakeForceWheelsNotLocked().getParkingBrakeForceA())));

        response.then().body("techRecord.brakes.brakeForceWheelsUpToHalfLocked.serviceBrakeForceB", hasItem(equalTo(vehicle.getTechRecord().get(index).getBrakes().getBrakeForceWheelsUpToHalfLocked().getServiceBrakeForceB())));
        response.then().body("techRecord.brakes.brakeForceWheelsUpToHalfLocked.secondaryBrakeForceB", hasItem(equalTo(vehicle.getTechRecord().get(index).getBrakes().getBrakeForceWheelsUpToHalfLocked().getSecondaryBrakeForceB())));
        response.then().body("techRecord.brakes.brakeForceWheelsUpToHalfLocked.parkingBrakeForceB", hasItem(equalTo(vehicle.getTechRecord().get(index).getBrakes().getBrakeForceWheelsUpToHalfLocked().getParkingBrakeForceB())));


        response.then().body("techRecord.axles.axleNumber", hasItem(hasItem(equalTo(vehicle.getTechRecord().get(index).getAxles().get(0).getAxleNumber()))));
        response.then().body("techRecord.axles.tyres.tyreSize", hasItem(hasItem(equalTo(vehicle.getTechRecord().get(index).getAxles().get(0).getTyres().getTyreSize()))));
        response.then().body("techRecord.axles.tyres.plyRating", hasItem(hasItem(equalTo(vehicle.getTechRecord().get(index).getAxles().get(0).getTyres().getPlyRating()))));
        response.then().body("techRecord.axles.tyres.fitmentCode", hasItem(hasItem(equalTo(vehicle.getTechRecord().get(index).getAxles().get(0).getTyres().getFitmentCode()))));
        response.then().body("techRecord.axles.tyres.dataTrPsvAxles", hasItem(hasItem(equalTo(vehicle.getTechRecord().get(index).getAxles().get(0).getTyres().getDataTrPsvAxles()))));
        response.then().body("techRecord.axles.tyres.speedCategorySymbol", hasItem(hasItem(equalTo(vehicle.getTechRecord().get(index).getAxles().get(0).getTyres().getSpeedCategorySymbol()))));
        response.then().body("techRecord.axles.tyres.tyreCode", hasItem(hasItem(equalTo(vehicle.getTechRecord().get(index).getAxles().get(0).getTyres().getTyreCode()))));


        response.then().body("techRecord.axles.weights.kerbWeight", hasItem(hasItem(equalTo(vehicle.getTechRecord().get(index).getAxles().get(0).getWeights().getKerbWeight()))));
        response.then().body("techRecord.axles.weights.ladenWeight", hasItem(hasItem(equalTo(vehicle.getTechRecord().get(index).getAxles().get(0).getWeights().getLadenWeight()))));
        response.then().body("techRecord.axles.weights.gbWeight", hasItem(hasItem(equalTo(vehicle.getTechRecord().get(index).getAxles().get(0).getWeights().getGbWeight()))));
        response.then().body("techRecord.axles.weights.designWeight", hasItem(hasItem(equalTo(vehicle.getTechRecord().get(index).getAxles().get(0).getWeights().getDesignWeight()))));

    }

    @Step
    public void validateData(String stringData) {
        response.then().body(is("\"" + stringData + "\""));
    }
}
