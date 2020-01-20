package clients.model;

import org.apache.commons.lang3.RandomStringUtils;

public enum VehicleSubClass implements PropertyValued {

    A("a"), C("c"), S("s"), L("l"),M("m"),N("n"),P("p"),
    T("t"),R("r"),NULL("null"), EMPTY("") {
        @Override
        public String getValue() {
            return RandomStringUtils.randomAlphanumeric(1);
        }
    };

    private String value;

    VehicleSubClass(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
