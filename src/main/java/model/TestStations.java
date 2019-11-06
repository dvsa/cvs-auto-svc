package model;

import java.util.List;

public class TestStations {

    private String testStationId;
    private String testStationPNumber;
    private String testStationName;
    private String testStationContactNumber;
    private String testStationAccessNotes;
    private String testStationGeneralNotes;
    private String testStationTown;
    private String testStationAddress;
    private String testStationPostcode;
    private String testStationLongitude;
    private String testStationLatitude;
    private String testStationType;
    private String testStationStatus;
    private List<String> testStationEmails;

    public String getTestStationId() {
        return testStationId;
    }

    public TestStations setTestStationId(String testStationId) {
        this.testStationId = testStationId;
        return this;
    }

    public String getTestStationPNumber() {
        return testStationPNumber;
    }

    public TestStations setTestStationPNumber(String testStationPNumber) {
        this.testStationPNumber = testStationPNumber;
        return this;
    }

    public String getTestStationStatus() {
        return testStationStatus;
    }

    public String getTestStationName() {
        return testStationName;
    }

    public TestStations setTestStationName(String testStationName) {
        this.testStationName = testStationName;
        return this;
    }

    public String getTestStationContactNumber() {
        return testStationContactNumber;
    }

    public TestStations setTestStationContactNumber(String testStationContactNumber) {
        this.testStationContactNumber = testStationContactNumber;
        return this;
    }

    public String getTestStationAccessNotes() {
        return testStationAccessNotes;
    }

    public TestStations setTestStationAccessNotes(String testStationAccessNotes) {
        this.testStationAccessNotes = testStationAccessNotes;
        return this;
    }

    public String getTestStationGeneralNotes() {
        return testStationGeneralNotes;
    }

    public TestStations setTestStationGeneralNotes(String testStationGeneralNotes) {
        this.testStationGeneralNotes = testStationGeneralNotes;
        return this;
    }

    public String getTestStationTown() {
        return testStationTown;
    }

    public TestStations setTestStationTown(String testStationTown) {
        this.testStationTown = testStationTown;
        return this;
    }

    public String getTestStationAddress() {
        return testStationAddress;
    }

    public TestStations setTestStationAddress(String testStationAddress) {
        this.testStationAddress = testStationAddress;
        return this;
    }

    public String getTestStationPostcode() {
        return testStationPostcode;
    }

    public TestStations setTestStationPostcode(String testStationPostcode) {
        this.testStationPostcode = testStationPostcode;
        return this;
    }

    public String getTestStationLongitude() {
        return testStationLongitude;
    }

    public TestStations setTestStationLongitude(String testStationLongitude) {
        this.testStationLongitude = testStationLongitude;
        return this;
    }

    public String getTestStationLatitude() {
        return testStationLatitude;
    }

    public TestStations setTestStationLatitude(String testStationLatitude) {
        this.testStationLatitude = testStationLatitude;
        return this;
    }

    public String getTestStationType() {
        return testStationType;
    }

    public TestStations setTestStationType(String testStationType) {
        this.testStationType = testStationType;
        return this;
    }

    public List<String> getTestStationEmails() {
        return testStationEmails;
    }

    public TestStations setTestStationEmails(List<String> testStationEmails) {
        this.testStationEmails = testStationEmails;
        return this;
    }


    @Override
    public String toString() {
        return "TestStations{" +
                "testStationId='" + testStationId + '\'' +
                ", testStationPNumber='" + testStationPNumber + '\'' +
                ", testStationName='" + testStationName + '\'' +
                ", testStationContactNumber='" + testStationContactNumber + '\'' +
                ", testStationAccessNotes='" + testStationAccessNotes + '\'' +
                ", testStationGeneralNotes='" + testStationGeneralNotes + '\'' +
                ", testStationTown='" + testStationTown + '\'' +
                ", testStationAddress='" + testStationAddress + '\'' +
                ", testStationPostcode='" + testStationPostcode + '\'' +
                ", testStationLongitude='" + testStationLongitude + '\'' +
                ", testStationLatitude='" + testStationLatitude + '\'' +
                ", testStationType='" + testStationType + '\'' +
                ", testStationStatus='" + testStationStatus + '\'' +
                ", testStationEmails=" + testStationEmails +
                '}';
    }
}
