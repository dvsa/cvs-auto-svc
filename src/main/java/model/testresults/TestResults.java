package model.testresults;

import java.util.List;

public class TestResults {

    private String vrm;
    private String vin;
    private String testStationName;
    private String testStationPNumber;
    private String locationType;
    private String testerName;
    private String testerStaffId;
    private String testStartTimestamp;
    private String testEndTimestamp;
    private String testStatus;
    private String reasonForCancellation;
    private String vehicleClass;
    private String vehicleType;
    private Integer numberOfSeats;
    private String vehicleStatus;
    private String vehicleConfiguration;
    private Integer odometerReading;
    private String odometerReadingUnits;
    private String preparerId;
    private String preparerName;
    private String euVehicleCategory;
    private String countryOfRegistration;
    private List<TestTypes> testTypes;

    public String getVrm() {
        return vrm;
    }

    public TestResults setVrm(String vrm) {
        this.vrm = vrm;
        return this;
    }

    public String getVin() {
        return vin;
    }

    public TestResults setVin(String vin) {
        this.vin = vin;
        return this;
    }

    public String getTestStationName() {
        return testStationName;
    }

    public TestResults setTestStationName(String testStationName) {
        this.testStationName = testStationName;
        return this;
    }

    public String getTestStationPNumber() {
        return testStationPNumber;
    }

    public TestResults setTestStationPNumber(String testStationPNumber) {
        this.testStationPNumber = testStationPNumber;
        return this;
    }

    public String getLocationType() {
        return locationType;
    }

    public TestResults setLocationType(String locationType) {
        this.locationType = locationType;
        return this;
    }

    public String getTesterName() {
        return testerName;
    }

    public TestResults setTesterName(String testerName) {
        this.testerName = testerName;
        return this;
    }

    public String getTesterStaffId() {
        return testerStaffId;
    }

    public TestResults setTesterStaffId(String testerStaffId) {
        this.testerStaffId = testerStaffId;
        return this;
    }

    public String getTestStartTimestamp() {
        return testStartTimestamp;
    }

    public TestResults setTestStartTimestamp(String testStartTimestamp) {
        this.testStartTimestamp = testStartTimestamp;
        return this;
    }

    public String getTestEndTimestamp() {
        return testEndTimestamp;
    }

    public TestResults setTestEndTimestamp(String testEndTimestamp) {
        this.testEndTimestamp = testEndTimestamp;
        return this;
    }

    public String getTestStatus() {
        return testStatus;
    }

    public TestResults setTestStatus(String testStatus) {
        this.testStatus = testStatus;
        return this;
    }

    public String getReasonForCancellation() {
        return reasonForCancellation;
    }

    public TestResults setReasonForCancellation(String reasonForCancellation) {
        this.reasonForCancellation = reasonForCancellation;
        return this;
    }

    public String getVehicleClass() {
        return vehicleClass;
    }

    public TestResults setVehicleClass(String vehicleClass) {
        this.vehicleClass = vehicleClass;
        return this;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public TestResults setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
        return this;
    }

    public Integer getNumberOfSeats() {
        return numberOfSeats;
    }

    public TestResults setNumberOfSeats(Integer numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
        return this;
    }

    public String getVehicleStatus() {
        return vehicleStatus;
    }

    public TestResults setVehicleStatus(String vehicleStatus) {
        this.vehicleStatus = vehicleStatus;
        return this;
    }

    public String getVehicleConfiguration() {
        return vehicleConfiguration;
    }

    public TestResults setVehicleConfiguration(String vehicleConfiguration) {
        this.vehicleConfiguration = vehicleConfiguration;
        return this;
    }

    public Integer getOdometerReading() {
        return odometerReading;
    }

    public TestResults setOdometerReading(Integer odometerReading) {
        this.odometerReading = odometerReading;
        return this;
    }

    public String getOdometerReadingUnits() {
        return odometerReadingUnits;
    }

    public TestResults setOdometerReadingUnits(String odometerReadingUnits) {
        this.odometerReadingUnits = odometerReadingUnits;
        return this;
    }

    public String getPreparerId() {
        return preparerId;
    }

    public TestResults setPreparerId(String preparerId) {
        this.preparerId = preparerId;
        return this;
    }

    public String getPreparerName() {
        return preparerName;
    }

    public TestResults setPreparerName(String preparerName) {
        this.preparerName = preparerName;
        return this;
    }

    public String getEuVehicleCategory() {
        return euVehicleCategory;
    }

    public TestResults setEuVehicleCategory(String euVehicleCategory) {
        this.euVehicleCategory = euVehicleCategory;
        return this;
    }

    public String getCountryOfRegistration() {
        return countryOfRegistration;
    }

    public TestResults setCountryOfRegistration(String countryOfRegistration) {
        this.countryOfRegistration = countryOfRegistration;
        return this;
    }

    public List<TestTypes> getTestTypes() {
        return testTypes;
    }

    public TestResults setTestTypes(List<TestTypes> testTypes) {
        this.testTypes = testTypes;
        return this;
    }


    @Override
    public String toString() {
        return "TestResults{" +
                "vrm='" + vrm + '\'' +
                ", vin='" + vin + '\'' +
                ", testStationName='" + testStationName + '\'' +
                ", testStationPNumber='" + testStationPNumber + '\'' +
                ", locationType='" + locationType + '\'' +
                ", testerName='" + testerName + '\'' +
                ", testerStaffId='" + testerStaffId + '\'' +
                ", testStartTimestamp='" + testStartTimestamp + '\'' +
                ", testEndTimestamp='" + testEndTimestamp + '\'' +
                ", testStatus='" + testStatus + '\'' +
                ", reasonForCancellation='" + reasonForCancellation + '\'' +
                ", vehicleClass=" + vehicleClass +
                ", vehicleType='" + vehicleType + '\'' +
                ", numberOfSeats=" + numberOfSeats +
                ", vehicleStatus=" + vehicleStatus +
                ", vehicleConfiguration='" + vehicleConfiguration + '\'' +
                ", odometerReading=" + odometerReading +
                ", odometerReadingUnits='" + odometerReadingUnits + '\'' +
                ", preparerId='" + preparerId + '\'' +
                ", preparerName='" + preparerName + '\'' +
                ", euVehicleCategory='" + euVehicleCategory + '\'' +
                ", countryOfRegistration='" + countryOfRegistration + '\'' +
                ", testTypes=" + testTypes +
                '}';
    }

}
