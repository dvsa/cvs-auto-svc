package model.testresults;

import org.apache.commons.lang3.RandomStringUtils;

public enum TestResultsStatus {

    SUBMITTED("submitted"), CANCELLED("cancelled"),
    INVALID("invalid") {
        @Override
        public String getStatus() {
            return RandomStringUtils.randomAlphanumeric(20);
        }
    };

    private String status;

    TestResultsStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

}
