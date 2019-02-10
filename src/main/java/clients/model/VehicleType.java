package clients.model;

import org.apache.commons.lang3.RandomStringUtils;

public enum VehicleType implements PropertyValued {

    PSV("psv"), HGV("hgv"), TRL("trl"), EMPTY(""),
    INVALID("invalid") {
        @Override
        public String getValue() {
            return RandomStringUtils.randomAlphanumeric(3);
        }
    };

    private String value;

    VehicleType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
