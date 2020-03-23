package clients.model;

import org.apache.commons.lang3.RandomStringUtils;

public enum EuVehicleCategory implements PropertyValued {

    N2("n2"), N3("n3"), O1("o1"), O2("o2"), O3("o3"), O4("o4"), NULL("null"),
    EMPTY("") {
        @Override
        public String getValue() {
            return RandomStringUtils.randomAlphanumeric(3);
        }
    };

    private String value;

    EuVehicleCategory(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
