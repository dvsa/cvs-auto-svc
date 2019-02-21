package model;

public class Activities {

    private String activityType;
    private String testStationName;
    private String testStationPNumber;
    private String testStationEmail;
    private String testStationType;
    private String testerName;
    private String testerStaffId;

    public String getActivityType() {
        return activityType;
    }

    public Activities setActivityType(String activityType) {
        this.activityType = activityType;
        return this;
    }

    public String getTestStationName() {
        return testStationName;
    }

    public Activities setTestStationName(String testStationName) {
        this.testStationName = testStationName;
        return this;
    }

    public String getTestStationPNumber() {
        return testStationPNumber;
    }

    public Activities setTestStationPNumber(String testStationPNumber) {
        this.testStationPNumber = testStationPNumber;
        return this;
    }

    public String getTestStationEmail() {
        return testStationEmail;
    }

    public Activities setTestStationEmail(String testStationEmail) {
        this.testStationEmail = testStationEmail;
        return this;
    }

    public String getTestStationType() {
        return testStationType;
    }

    public Activities setTestStationType(String testStationType) {
        this.testStationType = testStationType;
        return this;
    }

    public String getTesterName() {
        return testerName;
    }

    public Activities setTesterName(String testerName) {
        this.testerName = testerName;
        return this;
    }

    public String getTesterStaffId() {
        return testerStaffId;
    }

    public Activities setTesterStaffId(String testerStaffId) {
        this.testerStaffId = testerStaffId;
        return this;
    }

    @Override
    public String toString() {
        return "Activities{" +
                "activityType='" + activityType + '\'' +
                ", testStationName='" + testStationName + '\'' +
                ", testStationPNumber='" + testStationPNumber + '\'' +
                ", testStationEmail='" + testStationEmail + '\'' +
                ", testStationType='" + testStationType + '\'' +
                ", testerName='" + testerName + '\'' +
                ", testerStaffId='" + testerStaffId + '\'' +
                '}';
    }
}
