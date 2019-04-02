package model.testresults;

import java.util.List;

public class TestTypes {

    private String testTypeName;
    private String name;
    private String testTypeId;
    private String certificateNumber;
    private String testTypeStartTimestamp;
    private String testTypeEndTimestamp;
    private Integer numberOfSeatbeltsFitted;
    private String lastSeatbeltInstallationCheckDate;
    private Boolean seatbeltInstallationCheckDate;
    private Boolean prohibitionIssued;
    private String reasonForAbandoning;
    private String additionalNotesRecorded;
    private List<Defects> defects;
    private String testResult;
    private String additionalCommentsForAbandon;

    public static class Builder<T extends TestTypes.Builder<T>> {

        private String testTypeName;
        private String name;
        private String testTypeId;
        private String certificateNumber;
        private String testTypeStartTimestamp;
        private String testTypeEndTimestamp;
        private Integer numberOfSeatbeltsFitted;
        private String lastSeatbeltInstallationCheckDate;
        private Boolean seatbeltInstallationCheckDate;
        private String testResult;
        private Boolean prohibitionIssued;
        private String reasonForAbandoning;
        private String additionalNotesRecorded;
        private List<Defects> defects;
        private String additionalCommentsForAbandon;


        public Builder() {
        }

        public T setTestTypeName(String testTypeName) {
            this.testTypeName = testTypeName;
            return (T) this;
        }

        public T setTestTypeId(String testTypeId) {
            this.testTypeId = testTypeId;
            return (T) this;
        }

        public T setCertificateNumber(String certificateNumber) {
            this.certificateNumber = certificateNumber;
            return (T) this;
        }

        public T setTestTypeStartTimestamp(String testTypeStartTimestamp) {
            this.testTypeStartTimestamp = testTypeStartTimestamp;
            return (T) this;
        }

        public T setTestTypeEndTimestamp(String testTypeEndTimestamp) {
            this.testTypeEndTimestamp = testTypeEndTimestamp;
            return (T) this;
        }

        public T setNumberOfSeatbeltsFitted(Integer numberOfSeatbeltsFitted) {
            this.numberOfSeatbeltsFitted = numberOfSeatbeltsFitted;
            return (T) this;
        }

        public T setLastSeatbeltInstallationCheckDate(String lastSeatbeltInstallationCheckDate) {
            this.lastSeatbeltInstallationCheckDate = lastSeatbeltInstallationCheckDate;
            return (T) this;
        }

        public T setSeatbeltInstallationCheckDate(Boolean seatbeltInstallationCheckDate) {
            this.seatbeltInstallationCheckDate = seatbeltInstallationCheckDate;
            return (T) this;
        }

        public T setTestResult(String testResult) {
            this.testResult = testResult;
            return (T) this;
        }

        public T setProhibitionIssued(Boolean prohibitionIssued) {
            this.prohibitionIssued = prohibitionIssued;
            return (T) this;
        }

        public T setReasonForAbandoning(String reasonForAbandoning) {
            this.reasonForAbandoning = reasonForAbandoning;
            return (T) this;
        }

        public T setAdditionalNotesRecorded(String additionalNotesRecorded) {
            this.additionalNotesRecorded = additionalNotesRecorded;
            return (T) this;
        }

        public T setDefects(List<Defects> defects) {
            this.defects = defects;
            return (T) this;
        }

        public T setName(String name) {
            this.name = name;
            return (T) this;
        }

        public T setAdditionalCommentsForAbandon(String additionalCommentsForAbandon) {
            this.additionalCommentsForAbandon = additionalCommentsForAbandon;
            return (T) this;
        }

        public TestTypes build() {
            return new TestTypes(this);
        }
    }

    protected TestTypes(TestTypes.Builder<?> builder) {
        this.testTypeName = builder.testTypeName;
        this.testTypeId = builder.testTypeId;
        this.certificateNumber = builder.certificateNumber;
        this.testTypeStartTimestamp = builder.testTypeStartTimestamp;
        this.testTypeEndTimestamp = builder.testTypeEndTimestamp;
        this.numberOfSeatbeltsFitted = builder.numberOfSeatbeltsFitted;
        this.lastSeatbeltInstallationCheckDate = builder.lastSeatbeltInstallationCheckDate;
        this.seatbeltInstallationCheckDate = builder.seatbeltInstallationCheckDate;
        this.testResult = builder.testResult;
        this.prohibitionIssued = builder.prohibitionIssued;
        this.reasonForAbandoning = builder.reasonForAbandoning;
        this.additionalNotesRecorded = builder.additionalNotesRecorded;
        this.defects = builder.defects;
        this.name = builder.name;
        this.additionalCommentsForAbandon = builder.additionalCommentsForAbandon;
    }

    public String getTestTypeName() {
        return testTypeName;
    }

    public String getTestTypeId() {
        return testTypeId;
    }

    public String getCertificateNumber() {
        return certificateNumber;
    }

    public String getTestTypeStartTimestamp() {
        return testTypeStartTimestamp;
    }

    public String getTestTypeEndTimestamp() {
        return testTypeEndTimestamp;
    }

    public Integer getNumberOfSeatbeltsFitted() {
        return numberOfSeatbeltsFitted;
    }

    public String getLastSeatbeltInstallationCheckDate() {
        return lastSeatbeltInstallationCheckDate;
    }

    public Boolean getSeatbeltInstallationCheckDate() {
        return seatbeltInstallationCheckDate;
    }

    public String getTestResult() {
        return testResult;
    }

    public Boolean getProhibitionIssued() {
        return prohibitionIssued;
    }

    public String getReasonForAbandoning() {
        return reasonForAbandoning;
    }

    public String getAdditionalNotesRecorded() {
        return additionalNotesRecorded;
    }

    public String getAdditionalCommentsForAbandon() {
        return additionalCommentsForAbandon;
    }

    public List<Defects> getDefects() {
        return defects;
    }

    public String getName() {
        return name;
    }

    public void setTestTypeName(String testTypeName) {
        this.testTypeName = testTypeName;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TestTypes setTestTypeId(String testTypeId) {
        this.testTypeId = testTypeId;
        return this;
    }

    public void setCertificateNumber(String certificateNumber) {
        this.certificateNumber = certificateNumber;
    }

    public void setTestTypeStartTimestamp(String testTypeStartTimestamp) {
        this.testTypeStartTimestamp = testTypeStartTimestamp;
    }

    public void setTestTypeEndTimestamp(String testTypeEndTimestamp) {
        this.testTypeEndTimestamp = testTypeEndTimestamp;
    }

    public void setNumberOfSeatbeltsFitted(Integer numberOfSeatbeltsFitted) {
        this.numberOfSeatbeltsFitted = numberOfSeatbeltsFitted;
    }

    public void setLastSeatbeltInstallationCheckDate(String lastSeatbeltInstallationCheckDate) {
        this.lastSeatbeltInstallationCheckDate = lastSeatbeltInstallationCheckDate;
    }

    public void setSeatbeltInstallationCheckDate(Boolean seatbeltInstallationCheckDate) {
        this.seatbeltInstallationCheckDate = seatbeltInstallationCheckDate;
    }

    public void setProhibitionIssued(Boolean prohibitionIssued) {
        this.prohibitionIssued = prohibitionIssued;
    }

    public void setReasonForAbandoning(String reasonForAbandoning) {
        this.reasonForAbandoning = reasonForAbandoning;
    }

    public void setAdditionalNotesRecorded(String additionalNotesRecorded) {
        this.additionalNotesRecorded = additionalNotesRecorded;
    }

    public void setDefects(List<Defects> defects) {
        this.defects = defects;
    }

    public TestTypes setTestResult(String testResult) {
        this.testResult = testResult;
        return this;
    }

    public void setAdditionalCommentsForAbandon(String additionalCommentsForAbandon) {
        this.additionalCommentsForAbandon = additionalCommentsForAbandon;
    }

    @Override
    public String toString() {
        return "TestTypes{" +
                "testTypeName='" + testTypeName + '\'' +
                ", testTypeId='" + testTypeId + '\'' +
                ", certificateNumber='" + certificateNumber + '\'' +
                ", testTypeStartTimestamp='" + testTypeStartTimestamp + '\'' +
                ", testTypeEndTimestamp='" + testTypeEndTimestamp + '\'' +
                ", numberOfSeatbeltsFitted=" + numberOfSeatbeltsFitted +
                ", lastSeatbeltInstallationCheckDate='" + lastSeatbeltInstallationCheckDate + '\'' +
                ", seatbeltInstallationCheckDate=" + seatbeltInstallationCheckDate +
                ", prohibitionIssued=" + prohibitionIssued +
                ", reasonForAbandoning='" + reasonForAbandoning + '\'' +
                ", additionalNotesRecorded='" + additionalNotesRecorded + '\'' +
                ", defects=" + defects +
                ", name='" + name + '\'' +
                ", testResult='" + testResult + '\'' +
                ", additionalCommentsForAbandon='" + additionalCommentsForAbandon + '\'' +
                '}';
    }
}
