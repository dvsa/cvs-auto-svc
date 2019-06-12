package model.testresults;

public class TestTypesGet extends TestTypes {

    private String createdAt;
    private String lastUpdatedAt;
    private String testCode;
    private String testNumber;
    private String certificateLink;
    private String testExpiryDate;
    private String testAnniversaryDate;

    public TestTypesGet() {
        super();
    }

    public static class Builder extends TestTypes.Builder<Builder> {

        private String createdAt;
        private String lastUpdatedAt;
        private String testCode;
        private String testNumber;
        private String certificateLink;
        private String testExpiryDate;
        private String testAnniversaryDate;

        public Builder() {
        }

        public TestTypesGet build() {
            return new TestTypesGet(this);
        }
    }

    protected TestTypesGet(Builder builder) {
        super(builder);
        this.createdAt = builder.createdAt;
        this.lastUpdatedAt = builder.lastUpdatedAt;
        this.testCode = builder.testCode;
        this.testNumber = builder.testNumber;
        this.certificateLink = builder.certificateLink;
        this.testExpiryDate = builder.testExpiryDate;
        this.testAnniversaryDate = builder.testAnniversaryDate;
    }


    public TestTypesGet setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public TestTypesGet setLastUpdatedAt(String lastUpdatedAt) {
        this.lastUpdatedAt = lastUpdatedAt;
        return this;
    }

    public TestTypesGet setTestCode(String testCode) {
        this.testCode = testCode;
        return this;
    }

    public TestTypesGet setTestNumber(String testNumber) {
        this.testNumber = testNumber;
        return this;
    }

    public TestTypesGet setCertificateLink(String certificateLink) {
        this.certificateLink = certificateLink;
        return this;
    }

    public TestTypesGet setTestExpiryDate(String testExpiryDate) {
        this.testExpiryDate = testExpiryDate;
        return this;
    }

    public TestTypesGet setTestAnniversaryDate(String testAnniversaryDate) {
        this.testAnniversaryDate = testAnniversaryDate;
        return this;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getLastUpdatedAt() {
        return lastUpdatedAt;
    }

    public String getTestCode() {
        return testCode;
    }

    public String getTestNumber() {
        return testNumber;
    }

    public String getCertificateLink() {
        return certificateLink;
    }

    public String getTestExpiryDate() {
        return testExpiryDate;
    }

    public String getTestAnniversaryDate() {
        return testAnniversaryDate;
    }


    @Override
    public String toString() {
        return "TestTypesGet{" +
                "createdAt='" + createdAt + '\'' +
                ", lastUpdatedAt='" + lastUpdatedAt + '\'' +
                ", testCode='" + testCode + '\'' +
                ", testNumber='" + testNumber + '\'' +
                ", certificateLink='" + certificateLink + '\'' +
                ", testExpiryDate='" + testExpiryDate + '\'' +
                ", testAnniversaryDate='" + testAnniversaryDate + '\'' +
                '}';
    }
}
