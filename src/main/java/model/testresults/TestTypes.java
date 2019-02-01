package model.testresults;

import java.util.List;

public class TestTypes {

    private String createdAt;
    private String lastUpdatedAt;
    private String testCode;
    private String testTypeName;
    private String testId;
    private String certificateNumber;
    private String testExpiryDate;
    private String testTypeStartTimestamp;
    private String testTypeEndTimestamp;
    private Integer numberOfSeatbeltsFitted;
    private String lastSeatbeltInstallationCheckDate;
    private Boolean seatbeltInstallationCheckDate;
    private String testResult;
    private String prohibitionIssued;
    private String reasonForAbandoning;
    private String additionalNotesRecorded;
    private List<Defects> defects;


    public String getCreatedAt() {
        return createdAt;
    }

    public TestTypes setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public String getLastUpdatedAt() {
        return lastUpdatedAt;
    }

    public TestTypes setLastUpdatedAt(String lastUpdatedAt) {
        this.lastUpdatedAt = lastUpdatedAt;
        return this;
    }

    public String getTestCode() {
        return testCode;
    }

    public TestTypes setTestCode(String testCode) {
        this.testCode = testCode;
        return this;
    }

    public String getTestTypeName() {
        return testTypeName;
    }

    public TestTypes setTestTypeName(String testTypeName) {
        this.testTypeName = testTypeName;
        return this;
    }

    public String getTestId() {
        return testId;
    }

    public TestTypes setTestId(String testId) {
        this.testId = testId;
        return this;
    }

    public String getCertificateNumber() {
        return certificateNumber;
    }

    public TestTypes setCertificateNumber(String certificateNumber) {
        this.certificateNumber = certificateNumber;
        return this;
    }

    public String getTestExpiryDate() {
        return testExpiryDate;
    }

    public TestTypes setTestExpiryDate(String testExpiryDate) {
        this.testExpiryDate = testExpiryDate;
        return this;
    }

    public String getTestTypeStartTimestamp() {
        return testTypeStartTimestamp;
    }

    public TestTypes setTestTypeStartTimestamp(String testTypeStartTimestamp) {
        this.testTypeStartTimestamp = testTypeStartTimestamp;
        return this;
    }

    public String getTestTypeEndTimestamp() {
        return testTypeEndTimestamp;
    }

    public TestTypes setTestTypeEndTimestamp(String testTypeEndTimestamp) {
        this.testTypeEndTimestamp = testTypeEndTimestamp;
        return this;
    }

    public Integer getNumberOfSeatbeltsFitted() {
        return numberOfSeatbeltsFitted;
    }

    public TestTypes setNumberOfSeatbeltsFitted(Integer numberOfSeatbeltsFitted) {
        this.numberOfSeatbeltsFitted = numberOfSeatbeltsFitted;
        return this;
    }

    public String getLastSeatbeltInstallationCheckDate() {
        return lastSeatbeltInstallationCheckDate;
    }

    public TestTypes setLastSeatbeltInstallationCheckDate(String lastSeatbeltInstallationCheckDate) {
        this.lastSeatbeltInstallationCheckDate = lastSeatbeltInstallationCheckDate;
        return this;
    }

    public Boolean getSeatbeltInstallationCheckDate() {
        return seatbeltInstallationCheckDate;
    }

    public TestTypes setSeatbeltInstallationCheckDate(Boolean seatbeltInstallationCheckDate) {
        this.seatbeltInstallationCheckDate = seatbeltInstallationCheckDate;
        return this;
    }

    public String getTestResult() {
        return testResult;
    }

    public TestTypes setTestResult(String testResult) {
        this.testResult = testResult;
        return this;
    }

    public String getProhibitionIssued() {
        return prohibitionIssued;
    }

    public TestTypes setProhibitionIssued(String prohibitionIssued) {
        this.prohibitionIssued = prohibitionIssued;
        return this;
    }

    public String getReasonForAbandoning() {
        return reasonForAbandoning;
    }

    public TestTypes setReasonForAbandoning(String reasonForAbandoning) {
        this.reasonForAbandoning = reasonForAbandoning;
        return this;
    }

    public String getAdditionalNotesRecorded() {
        return additionalNotesRecorded;
    }

    public TestTypes setAdditionalNotesRecorded(String additionalNotesRecorded) {
        this.additionalNotesRecorded = additionalNotesRecorded;
        return this;
    }

    public List<Defects> getDefects() {
        return defects;
    }

    public TestTypes setDefects(List<Defects> defects) {
        this.defects = defects;
        return this;
    }

    @Override
    public String toString() {
        return "TestTypes{" +
                "createdAt='" + createdAt + '\'' +
                ", lastUpdatedAt='" + lastUpdatedAt + '\'' +
                ", testCode='" + testCode + '\'' +
                ", testTypeName='" + testTypeName + '\'' +
                ", testId='" + testId + '\'' +
                ", certificateNumber='" + certificateNumber + '\'' +
                ", testExpiryDate='" + testExpiryDate + '\'' +
                ", testTypeStartTimestamp='" + testTypeStartTimestamp + '\'' +
                ", testTypeEndTimestamp='" + testTypeEndTimestamp + '\'' +
                ", numberOfSeatbeltsFitted=" + numberOfSeatbeltsFitted +
                ", lastSeatbeltInstallationCheckDate='" + lastSeatbeltInstallationCheckDate + '\'' +
                ", seatbeltInstallationCheckDate=" + seatbeltInstallationCheckDate +
                ", testResult='" + testResult + '\'' +
                ", prohibitionIssued='" + prohibitionIssued + '\'' +
                ", reasonForAbandoning='" + reasonForAbandoning + '\'' +
                ", additionalNotesRecorded='" + additionalNotesRecorded + '\'' +
                ", defects=" + defects +
                '}';
    }
}
