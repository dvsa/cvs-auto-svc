package clients.model;

import org.apache.commons.lang3.RandomStringUtils;

public enum VehicleConfiguration implements PropertyValued {

    RIGID("rigid"), ARTICULATED("articulated"), EMPTY(""),
    INVALID("invalid") {
        @Override
        public String getValue() {
            return RandomStringUtils.randomAlphanumeric(3);
        }
    };

    private String value;

    VehicleConfiguration(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
