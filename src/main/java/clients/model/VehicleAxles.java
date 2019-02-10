package clients.model;

import org.apache.commons.lang3.RandomUtils;

public enum VehicleAxles implements PropertyValued {

    TWO("2"), THREE("3"), EMPTY(""),
    INVALID("invalid") {
        @Override
        public String getValue() {
            return String.valueOf(RandomUtils.nextInt(4, 9));
        }
    };

    private String value;

    VehicleAxles(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
