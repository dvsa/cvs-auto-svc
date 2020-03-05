package clients.model;

import org.apache.commons.lang3.RandomStringUtils;

public enum VehicleType implements PropertyValued {

    HGV("hgv"), PSV("psv"), TRL("trl"), CAR("car"), LGV("lgv"), MOTORCYCLE("motorcycle"), EMPTY(""),
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
