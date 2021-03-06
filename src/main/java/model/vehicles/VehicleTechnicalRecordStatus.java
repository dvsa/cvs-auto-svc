package model.vehicles;

import org.apache.commons.lang3.RandomStringUtils;

public enum VehicleTechnicalRecordStatus {

    CURRENT("current"), ARCHIVED("archived"), PROVISIONAL("provisional"), ALL("all"),
    PROVISIONAL_OVER_CURRENT("provisional_over_current"), INVALID("invalid")
            {
        @Override
        public String getStatus() {
            return RandomStringUtils.randomAlphanumeric(20);
        }
    };

    private String status;

    VehicleTechnicalRecordStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
