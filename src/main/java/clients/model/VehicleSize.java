package clients.model;

import org.apache.commons.lang3.RandomStringUtils;

public enum VehicleSize implements PropertyValued {

    SMALL("small"), LARGE("large"), EMPTY(""),NULL(null),
    INVALID("invalid") {
        @Override
        public String getValue() {
            return RandomStringUtils.randomAlphanumeric(3);
        }
    };

    private String value;

    VehicleSize(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
