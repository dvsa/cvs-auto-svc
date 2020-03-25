package model.activities;

import java.util.List;

public class Activities {

    private String activityType;
    private String testStationName;
    private String testStationPNumber;
    private String testStationEmail;
    private String testStationType;
    private String testerName;
    private String testerEmail;
    private String testerStaffId;
    private List<String> waitReason;
    private String notes;


    public static class Builder<T extends Activities.Builder<T>> {

        private String activityType;
        private String testStationName;
        private String testStationPNumber;
        private String testStationEmail;
        private String testStationType;
        private String testerName;
        private String testerEmail;
        private String testerStaffId;
        private List<String> waitReason;
        private String notes;


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

        public T setTesterEmail(String testerEmail) {
            this.testerEmail = testerEmail;
            return (T) this;
        }


        public T setTesterEmail(String testerEmail) {
            this.testerEmail = testerEmail;
            return (T) this;
        }

        public T setTesterStaffId(String testerStaffId) {
            this.testerStaffId = testerStaffId;
            return (T) this;
        }

        public T setWaitReason(List<String> waitReason) {
            this.waitReason = waitReason;
            return (T) this;
        }

        public T setNotes(String notes) {
            this.notes = notes;
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
        this.testerEmail = builder.testerEmail;
        this.testerStaffId = builder.testerStaffId;
        this.waitReason = builder.waitReason;
        this.notes = builder.notes;
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

    public String getTesterEmail() {
        return testerEmail;
    }

    public String getTesterStaffId() {
        return testerStaffId;
    }

    public List<String> getWaitReason() {
        return waitReason;
    }

    public String getNotes() {
        return notes;
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
                ", testerEmail='" + testerEmail + '\'' +
                ", testerStaffId='" + testerStaffId + '\'' +
                ", waitReason='" + waitReason + '\'' +
                ", notes='" + notes + '\'' +
                '}';
    }
}
