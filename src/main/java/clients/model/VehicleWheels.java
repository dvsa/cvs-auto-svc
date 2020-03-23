package clients.model;

import org.apache.commons.lang3.RandomStringUtils;

public enum VehicleWheels implements PropertyValued {

    TWO("2"), THREE("3"), FOUR("4"),NULL("null"), EMPTY(""),INVALID("8") {
        @Override
        public String getValue() {
            return RandomStringUtils.randomNumeric(1);
        }
    };

    private String value;

    VehicleWheels(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
