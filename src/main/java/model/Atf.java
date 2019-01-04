package model;

public class Atf {

    private String atfId;
    private String atfNumber;
    private String atfName;
    private String atfContactNumber;
    private String atfAccessNotes;
    private String atfGeneralNotes;
    private String atfTown;
    private String atfAddress;
    private String atfPostcode;
    private String atfLongitude;
    private String atfLatitude;
    private String atfType;

    public String getAtfId() {
        return atfId;
    }

    public Atf setAtfId(String atfId) {
        this.atfId = atfId;
        return this;
    }

    public String getAtfNumber() {
        return atfNumber;
    }

    public Atf setAtfNumber(String atfNumber) {
        this.atfNumber = atfNumber;
        return this;
    }

    public String getAtfName() {
        return atfName;
    }

    public Atf setAtfName(String atfName) {
        this.atfName = atfName;
        return this;
    }

    public String getAtfContactNumber() {
        return atfContactNumber;
    }

    public Atf setAtfContactNumber(String atfContactNumber) {
        this.atfContactNumber = atfContactNumber;
        return this;
    }

    public String getAtfAccessNotes() {
        return atfAccessNotes;
    }

    public Atf setAtfAccessNotes(String atfAccessNotes) {
        this.atfAccessNotes = atfAccessNotes;
        return this;
    }

    public String getAtfGeneralNotes() {
        return atfGeneralNotes;
    }

    public Atf setAtfGeneralNotes(String atfGeneralNotes) {
        this.atfGeneralNotes = atfGeneralNotes;
        return this;
    }

    public String getAtfTown() {
        return atfTown;
    }

    public Atf setAtfTown(String atfTown) {
        this.atfTown = atfTown;
        return this;
    }

    public String getAtfAddress() {
        return atfAddress;
    }

    public Atf setAtfAddress(String atfAddress) {
        this.atfAddress = atfAddress;
        return this;
    }

    public String getAtfPostcode() {
        return atfPostcode;
    }

    public Atf setAtfPostcode(String atfPostcode) {
        this.atfPostcode = atfPostcode;
        return this;
    }

    public String getAtfLongitude() {
        return atfLongitude;
    }

    public Atf setAtfLongitude(String atfLongitude) {
        this.atfLongitude = atfLongitude;
        return this;
    }

    public String getAtfLatitude() {
        return atfLatitude;
    }

    public Atf setAtfLatitude(String atfLatitude) {
        this.atfLatitude = atfLatitude;
        return this;
    }

    public String getAtfType() {
        return atfType;
    }

    public Atf setAtfType(String atfType) {
        this.atfType = atfType;
        return this;
    }

    @Override
    public String toString() {
        return "Atf{" +
                "atfId='" + atfId + '\'' +
                ", atfNumber='" + atfNumber + '\'' +
                ", atfName='" + atfName + '\'' +
                ", atfContactNumber='" + atfContactNumber + '\'' +
                ", atfAccessNotes='" + atfAccessNotes + '\'' +
                ", atfGeneralNotes='" + atfGeneralNotes + '\'' +
                ", atfTown='" + atfTown + '\'' +
                ", atfAddress='" + atfAddress + '\'' +
                ", atfPostcode='" + atfPostcode + '\'' +
                ", atfLongitude='" + atfLongitude + '\'' +
                ", atfLatitude='" + atfLatitude + '\'' +
                ", atfType='" + atfType + '\'' +
                '}';
    }
}
