package model.testresults;

import util.PropertyNotPresent;

import java.util.List;

public class TestResults {

    private String vrm;
    private String vin;
    private String systemNumber;
    private String testStationName;
    private String testStationPNumber;
    private String testStationType;
    private String testerName;
    private String testerStaffId;
    private String testerEmailAddress;
    private String testStartTimestamp;
    private String testEndTimestamp;
    private String testStatus;
    private String reasonForCancellation;
    private VehicleClass vehicleClass;
    private String vehicleType;
    private Integer numberOfSeats;
    private Integer noOfAxles;
    private String vehicleConfiguration;
    private Integer odometerReading;
    private String odometerReadingUnits;
    private String preparerId;
    private String preparerName;
    private String euVehicleCategory;
    private String countryOfRegistration;
    private String vehicleSize;

    @PropertyNotPresent
    private String testResultId;

    private List<TestTypesGet> testTypes;

    public TestResults() {

    }

    public static class Builder<T extends Builder<T>> {

        private String vrm;
        private String vin;
        private String systemNumber;
        private String testResultId;
        private String testStationName;
        private String testStationPNumber;
        private String testStationType;
        private String testerName;
        private String testerStaffId;
        private String testerEmailAddress;
        private String testStartTimestamp;
        private String testEndTimestamp;
        private String testStatus;
        private String reasonForCancellation;
        private VehicleClass vehicleClass;
        private String vehicleType;
        private Integer numberOfSeats;
        private Integer noOfAxles;
        private String vehicleConfiguration;
        private Integer odometerReading;
        private String odometerReadingUnits;
        private String preparerId;
        private String preparerName;
        private String euVehicleCategory;
        private String countryOfRegistration;
        private String vehicleSize;
        private List<TestTypesGet> testTypes;

        public Builder() {
        }

        public T setVrm(String vrm) {
            this.vrm = vrm;
            return (T) this;
        }

        public T setVin(String vin) {
            this.vin = vin;
            return (T) this;
        }

        public T setSystemNumber(String systemNumber) {
            this.systemNumber = systemNumber;
            return (T) this;
        }

        public T setTestResultId(String testResultId) {
            this.testResultId = testResultId;
            return (T) this;
        }

        public T setTestStationName(String testStationName) {
            this.testStationName = testStationName;
            return (T) this;
        }

        public T setTestStationPNumber(String testStationPNumber) {
            this.testStationPNumber = testStationPNumber;
            return (T) this;
        }

        public T setTestStationType(String testStationType) {
            this.testStationType = testStationType;
            return (T) this;
        }

        public T setTesterName(String testerName) {
            this.testerName = testerName;
            return (T) this;
        }

        public T setTesterStaffId(String testerStaffId) {
            this.testerStaffId = testerStaffId;
            return (T) this;
        }

        public T setTesterEmailAddress(String testerEmailAddress) {
            this.testerEmailAddress = testerEmailAddress;
            return (T) this;
        }

        public T setTestStartTimestamp(String testStartTimestamp) {
            this.testStartTimestamp = testStartTimestamp;
            return (T) this;
        }

        public T setTestEndTimestamp(String testEndTimestamp) {
            this.testEndTimestamp = testEndTimestamp;
            return (T) this;
        }

        public T setTestStatus(String testStatus) {
            this.testStatus = testStatus;
            return (T) this;
        }

        public T setReasonForCancellation(String reasonForCancellation) {
            this.reasonForCancellation = reasonForCancellation;
            return (T) this;
        }

        public T setVehicleClass(VehicleClass vehicleClass) {
            this.vehicleClass = vehicleClass;
            return (T) this;
        }

        public T setVehicleType(String vehicleType) {
            this.vehicleType = vehicleType;
            return (T) this;
        }

        public T setNumberOfSeats(Integer numberOfSeats) {
            this.numberOfSeats = numberOfSeats;
            return (T) this;
        }

        public T setNoOfAxles(Integer noOfAxles) {
            this.noOfAxles = noOfAxles;
            return (T) this;
        }


        public T setVehicleConfiguration(String vehicleConfiguration) {
            this.vehicleConfiguration = vehicleConfiguration;
            return (T) this;
        }

        public T setOdometerReading(Integer odometerReading) {
            this.odometerReading = odometerReading;
            return (T) this;
        }

        public T setOdometerReadingUnits(String odometerReadingUnits) {
            this.odometerReadingUnits = odometerReadingUnits;
            return (T) this;
        }

        public T setPreparerId(String preparerId) {
            this.preparerId = preparerId;
            return (T) this;
        }

        public T setPreparerName(String preparerName) {
            this.preparerName = preparerName;
            return (T) this;
        }

        public T setEuVehicleCategory(String euVehicleCategory) {
            this.euVehicleCategory = euVehicleCategory;
            return (T) this;
        }

        public T setCountryOfRegistration(String countryOfRegistration) {
            this.countryOfRegistration = countryOfRegistration;
            return (T) this;
        }

        public T setVehicleSize(String vehicleSize) {
            this.vehicleSize = vehicleSize;
            return (T) this;
        }

        public T setTestTypes(List<TestTypesGet> testTypes) {
            this.testTypes = testTypes;
            return (T) this;
        }

        public List<TestTypesGet> getTestTypes() {
            return testTypes;
        }

        public VehicleClass getVehicleClass() {
            return vehicleClass;
        }


        public TestResults build() {
            return new TestResults(this);
        }
    }

    protected TestResults(Builder<?> builder) {
        this.vrm = builder.vrm;
        this.vin = builder.vin;
        this.systemNumber = builder.systemNumber;
        this.testResultId = builder.testResultId;
        this.testStationName = builder.testStationName;
        this.testStationPNumber = builder.testStationPNumber;
        this.testStationType = builder.testStationType;
        this.testerName = builder.testerName;
        this.testerStaffId = builder.testerStaffId;
        this.testerEmailAddress = builder.testerEmailAddress;
        this.testStartTimestamp = builder.testStartTimestamp;
        this.testEndTimestamp = builder.testEndTimestamp;
        this.testStatus = builder.testStatus;
        this.reasonForCancellation = builder.reasonForCancellation;
        this.vehicleClass = builder.vehicleClass;
        this.vehicleType = builder.vehicleType;
        this.numberOfSeats = builder.numberOfSeats;
        this.noOfAxles = builder.noOfAxles;
        this.vehicleConfiguration = builder.vehicleConfiguration;
        this.odometerReading = builder.odometerReading;
        this.odometerReadingUnits = builder.odometerReadingUnits;
        this.preparerId = builder.preparerId;
        this.preparerName = builder.preparerName;
        this.euVehicleCategory = builder.euVehicleCategory;
        this.countryOfRegistration = builder.countryOfRegistration;
        this.vehicleSize = builder.vehicleSize;
        this.testTypes = builder.testTypes;
    }


    public String getVrm() {
        return vrm;
    }

    public String getVin() {
        return vin;
    }

    public String getSystemNumber() {
        return systemNumber;
    }

    public String getTestResultId() {
        return testResultId;
    }

    public String getTestStationName() {
        return testStationName;
    }

    public String getTestStationPNumber() {
        return testStationPNumber;
    }

    public String getTestStationType() {
        return testStationType;
    }

    public String getTesterName() {
        return testerName;
    }

    public String getTesterStaffId() {
        return testerStaffId;
    }

    public String getTesterEmailAddress() {
        return testerEmailAddress;
    }

    public String getTestStartTimestamp() {
        return testStartTimestamp;
    }

    public String getTestEndTimestamp() {
        return testEndTimestamp;
    }

    public String getTestStatus() {
        return testStatus;
    }

    public String getReasonForCancellation() {
        return reasonForCancellation;
    }

    public VehicleClass getVehicleClass() {
        return vehicleClass;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public Integer getNumberOfSeats() {
        return numberOfSeats;
    }

    public Integer getNoOfAxles() {
        return noOfAxles;
    }

    public String getVehicleConfiguration() {
        return vehicleConfiguration;
    }

    public Integer getOdometerReading() {
        return odometerReading;
    }

    public String getOdometerReadingUnits() {
        return odometerReadingUnits;
    }

    public String getPreparerId() {
        return preparerId;
    }

    public String getPreparerName() {
        return preparerName;
    }

    public String getEuVehicleCategory() {
        return euVehicleCategory;
    }

    public String getCountryOfRegistration() {
        return countryOfRegistration;
    }

    public String getVehicleSize() {
        return vehicleSize;
    }

    public List<TestTypesGet> getTestTypes() {
        return testTypes;
    }

    @Override
    public String toString() {
        return "TestResults{" +
                "vrm='" + vrm + '\'' +
                ", vin='" + vin + '\'' +
                ", testStationName='" + testStationName + '\'' +
                ", testStationPNumber='" + testStationPNumber + '\'' +
                ", testStationType='" + testStationType + '\'' +
                ", testerName='" + testerName + '\'' +
                ", testerStaffId='" + testerStaffId + '\'' +
                ", testerEmailAddress='" + testerEmailAddress + '\'' +
                ", testStartTimestamp='" + testStartTimestamp + '\'' +
                ", testEndTimestamp='" + testEndTimestamp + '\'' +
                ", testStatus='" + testStatus + '\'' +
                ", reasonForCancellation='" + reasonForCancellation + '\'' +
                ", vehicleClass=" + vehicleClass +
                ", vehicleType='" + vehicleType + '\'' +
                ", numberOfSeats=" + numberOfSeats +
                ", vehicleConfiguration='" + vehicleConfiguration + '\'' +
                ", odometerReading=" + odometerReading +
                ", odometerReadingUnits='" + odometerReadingUnits + '\'' +
                ", preparerId='" + preparerId + '\'' +
                ", preparerName='" + preparerName + '\'' +
                ", euVehicleCategory='" + euVehicleCategory + '\'' +
                ", countryOfRegistration='" + countryOfRegistration + '\'' +
                ", vehicleSize='" + vehicleSize + '\'' +
                ", testTypes=" + testTypes +
                '}';
    }

}
