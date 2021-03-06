package model.vehicles;

import java.util.List;

public class TechRecord {

    private String createdAt;
    private String lastUpdatedAt;
    private String chassisMake;
    private String chassisModel;
    private String bodyMake;
    private String bodyModel;
    private BodyType bodyType;
    private Integer manufactureYear;
    private String regnDate;
    private String coifDate;
    private String ntaNumber;
    private String conversionRefNo;
    private Integer seatsLowerDeck;
    private Integer seatsUpperDeck;
    private Integer standingCapacity;
    private Integer speedRestriction;
    private Boolean speedLimiterMrk;
    private Boolean tachoExemptMrk;
    private String dispensations;
    private String remarks;
    private String reasonForCreation;
    private String statusCode;
    private Integer unladenWeight;
    private Integer grossKerbWeight;
    private Integer grossLadenWeight;
    private Integer grossGbWeight;
    private Integer grossDesignWeight;
    private Integer noOfAxles;
    private String brakeCode;
    private VehicleClass vehicleClass;
    private String vehicleType;
    private String vehicleSize;
    private String vehicleConfiguration;
    private Brakes brakes;
    private List<Axles> axles;


    public String getCreatedAt() {
        return createdAt;
    }

    public TechRecord setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public String getLastUpdatedAt() {
        return lastUpdatedAt;
    }

    public TechRecord setLastUpdatedAt(String lastUpdatedAt) {
        this.lastUpdatedAt = lastUpdatedAt;
        return this;
    }

    public String getChassisMake() {
        return chassisMake;
    }

    public TechRecord setChassisMake(String chassisMake) {
        this.chassisMake = chassisMake;
        return this;
    }

    public String getChassisModel() {
        return chassisModel;
    }

    public TechRecord setChassisModel(String chassisModel) {
        this.chassisModel = chassisModel;
        return this;
    }

    public String getBodyMake() {
        return bodyMake;
    }

    public TechRecord setBodyMake(String bodyMake) {
        this.bodyMake = bodyMake;
        return this;
    }

    public String getBodyModel() {
        return bodyModel;
    }

    public TechRecord setBodyModel(String bodyModel) {
        this.bodyModel = bodyModel;
        return this;
    }

    public BodyType getBodyType() {
        return bodyType;
    }

    public TechRecord setBodyType(BodyType bodyType) {
        this.bodyType = bodyType;
        return this;
    }

    public Integer getManufactureYear() {
        return manufactureYear;
    }

    public TechRecord setManufactureYear(Integer manufactureYear) {
        this.manufactureYear = manufactureYear;
        return this;
    }

    public String getRegnDate() {
        return regnDate;
    }

    public TechRecord setRegnDate(String regnDate) {
        this.regnDate = regnDate;
        return this;
    }

    public String getCoifDate() {
        return coifDate;
    }

    public TechRecord setCoifDate(String coifDate) {
        this.coifDate = coifDate;
        return this;
    }

    public String getNtaNumber() {
        return ntaNumber;
    }

    public TechRecord setNtaNumber(String ntaNumber) {
        this.ntaNumber = ntaNumber;
        return this;
    }

    public String getConversionRefNo() {
        return conversionRefNo;
    }

    public TechRecord setConversionRefNo(String conversionRefNo) {
        this.conversionRefNo = conversionRefNo;
        return this;
    }

    public Integer getSeatsLowerDeck() {
        return seatsLowerDeck;
    }

    public TechRecord setSeatsLowerDeck(Integer seatsLowerDeck) {
        this.seatsLowerDeck = seatsLowerDeck;
        return this;
    }

    public Integer getSeatsUpperDeck() {
        return seatsUpperDeck;
    }

    public TechRecord setSeatsUpperDeck(Integer seatsUpperDeck) {
        this.seatsUpperDeck = seatsUpperDeck;
        return this;
    }

    public Integer getStandingCapacity() {
        return standingCapacity;
    }

    public TechRecord setStandingCapacity(Integer standingCapacity) {
        this.standingCapacity = standingCapacity;
        return this;
    }

    public Integer getSpeedRestriction() {
        return speedRestriction;
    }

    public TechRecord setSpeedRestriction(Integer speedRestriction) {
        this.speedRestriction = speedRestriction;
        return this;
    }

    public Boolean getSpeedLimiterMrk() {
        return speedLimiterMrk;
    }

    public TechRecord setSpeedLimiterMrk(Boolean speedLimiterMrk) {
        this.speedLimiterMrk = speedLimiterMrk;
        return this;
    }

    public Boolean getTachoExemptMrk() {
        return tachoExemptMrk;
    }

    public TechRecord setTachoExemptMrk(Boolean tachoExemptMrk) {
        this.tachoExemptMrk = tachoExemptMrk;
        return this;
    }

    public String getDispensations() {
        return dispensations;
    }

    public TechRecord setDispensations(String dispensations) {
        this.dispensations = dispensations;
        return this;
    }

    public String getRemarks() {
        return remarks;
    }

    public TechRecord setRemarks(String remarks) {
        this.remarks = remarks;
        return this;
    }

    public String getReasonForCreation() {
        return reasonForCreation;
    }

    public TechRecord setReasonForCreation(String reasonForCreation) {
        this.reasonForCreation = reasonForCreation;
        return this;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public TechRecord setStatusCode(String statusCode) {
        this.statusCode = statusCode;
        return this;
    }

    public Integer getUnladenWeight() {
        return unladenWeight;
    }

    public TechRecord setUnladenWeight(Integer unladenWeight) {
        this.unladenWeight = unladenWeight;
        return this;
    }

    public Integer getGrossKerbWeight() {
        return grossKerbWeight;
    }

    public TechRecord setGrossKerbWeight(Integer grossKerbWeight) {
        this.grossKerbWeight = grossKerbWeight;
        return this;
    }

    public Integer getGrossLadenWeight() {
        return grossLadenWeight;
    }

    public TechRecord setGrossLadenWeight(Integer grossLadenWeight) {
        this.grossLadenWeight = grossLadenWeight;
        return this;
    }

    public Integer getGrossGbWeight() {
        return grossGbWeight;
    }

    public TechRecord setGrossGbWeight(Integer grossGbWeight) {
        this.grossGbWeight = grossGbWeight;
        return this;
    }

    public Integer getGrossDesignWeight() {
        return grossDesignWeight;
    }

    public TechRecord setGrossDesignWeight(Integer grossDesignWeight) {
        this.grossDesignWeight = grossDesignWeight;
        return this;
    }


    public Integer getNoOfAxles() {
        return noOfAxles;
    }

    public TechRecord setNoOfAxles(Integer noOfAxles) {
        this.noOfAxles = noOfAxles;
        return this;
    }

    public String getBrakeCode() {
        return brakeCode;
    }

    public TechRecord setBrakeCode(String brakeCode) {
        this.brakeCode = brakeCode;
        return this;
    }

    public VehicleClass getVehicleClass() {
        return vehicleClass;
    }

    public TechRecord setVehicleClass(VehicleClass vehicleClass) {
        this.vehicleClass = vehicleClass;
        return this;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public TechRecord setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
        return this;
    }

    public String getVehicleSize() {
        return vehicleSize;
    }

    public TechRecord setVehicleSize(String vehicleSize) {
        this.vehicleSize = vehicleSize;
        return this;
    }

    public String getVehicleConfiguration() {
        return vehicleConfiguration;
    }

    public TechRecord setVehicleConfiguration(String vehicleConfiguration) {
        this.vehicleConfiguration = vehicleConfiguration;
        return this;
    }

    public Brakes getBrakes() {
        return brakes;
    }

    public TechRecord setBrakes(Brakes brakes) {
        this.brakes = brakes;
        return this;
    }

    public List<Axles> getAxles() {
        return axles;
    }

    public TechRecord setAxles(List<Axles> axles) {
        this.axles = axles;
        return this;
    }

    @Override
    public String toString() {
        return "TechRecord{" +
                "createdAt='" + createdAt + '\'' +
                "lastUpdatedAt='" + lastUpdatedAt + '\'' +
                "chassisMake='" + chassisMake + '\'' +
                ", chassisModel='" + chassisModel + '\'' +
                ", bodyMake='" + bodyMake + '\'' +
                ", bodyModel='" + bodyModel + '\'' +
                ", bodyType='" + bodyType + '\'' +
                ", manufactureYear=" + manufactureYear +
                ", regnDate='" + regnDate + '\'' +
                ", coifDate='" + coifDate + '\'' +
                ", ntaNumber='" + ntaNumber + '\'' +
                ", convrersationRefNo='" + conversionRefNo + '\'' +
                ", seatsLowerDeck=" + seatsLowerDeck +
                ", seatsUpperDeck=" + seatsUpperDeck +
                ", standingCapacity=" + standingCapacity +
                ", speedRestriction=" + speedRestriction +
                ", speedLimiterMrk=" + speedLimiterMrk +
                ", tachoExemptMrk=" + tachoExemptMrk +
                ", dispensations='" + dispensations + '\'' +
                ", remarks='" + remarks + '\'' +
                ", reasonForCreation='" + reasonForCreation + '\'' +
                ", statusCode='" + statusCode + '\'' +
                ", unladenWeight=" + unladenWeight +
                ", grossKerbWeight=" + grossKerbWeight +
                ", grossLadenWeight=" + grossLadenWeight +
                ", grossGbWeight=" + grossGbWeight +
                ", grossDesignWeight=" + grossDesignWeight +
                ", noOfAxles=" + noOfAxles +
                ", brakeCode='" + brakeCode + '\'' +
                ", vehicleClass='" + vehicleClass + '\'' +
                ", vehicleType='" + vehicleType + '\'' +
                ", vehicleSize='" + vehicleSize + '\'' +
                ", vehicleConfiguration='" + vehicleConfiguration + '\'' +
                ", brakes=" + brakes +
                ", axels=" + axles +
                '}';
    }
}
