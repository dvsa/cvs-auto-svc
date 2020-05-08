package model.vehicles;

import org.apache.commons.lang3.RandomStringUtils;

public enum OldStatusCode {

        PROVISIONAL("provisional"), INVALID("invalid")
            {
        @Override
        public String getOldStatusCode() {
            return RandomStringUtils.randomAlphanumeric(20);
        }
    };

    private String oldStatusCode;

    OldStatusCode(String oldStatusCode) {
        this.oldStatusCode = oldStatusCode;
    }

    public String getOldStatusCode() {
        return oldStatusCode;
    }
}

