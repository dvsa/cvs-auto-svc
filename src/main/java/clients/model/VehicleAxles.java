package clients.model;

import org.apache.commons.lang3.RandomUtils;

import java.util.Random;

public enum VehicleAxles implements PropertyValued {

    ONE("1"), TWO("2"), THREE("3"), FOUR("4"), FIVE("5"), EMPTY(""),
    NULL("null"), ANY(Integer.toString(new Random().nextInt(11-6) + 6)), INVALID("invalid") {
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
