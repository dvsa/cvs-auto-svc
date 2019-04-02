package model.activities;

public class Activities {

    private String activityType;
    private String testStationName;
    private String testStationPNumber;
    private String testStationEmail;
    private String testStationType;
    private String testerName;
    private String testerStaffId;


    public static class Builder<T extends Activities.Builder<T>> {

        private String activityType;
        private String testStationName;
        private String testStationPNumber;
        private String testStationEmail;
        private String testStationType;
        private String testerName;
        private String testerStaffId;


        public Builder() {
        }

        public T setActivityType(String activityType) {
            this.activityType = activityType;
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

        public T setTestStationEmail(String testStationEmail) {
            this.testStationEmail = testStationEmail;
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


        public Activities build() {
            return new Activities(this);
        }
    }


    protected Activities(Activities.Builder<?> builder) {
        this.activityType = builder.activityType;
        this.testStationName = builder.testStationName;
        this.testStationPNumber = builder.testStationPNumber;
        this.testStationEmail = builder.testStationEmail;
        this.testStationType = builder.testStationType;
        this.testerName = builder.testerName;
        this.testerStaffId = builder.testerStaffId;
    }


    public String getActivityType() {
        return activityType;
    }

    public String getTestStationName() {
        return testStationName;
    }

    public String getTestStationPNumber() {
        return testStationPNumber;
    }

    public String getTestStationEmail() {
        return testStationEmail;
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
