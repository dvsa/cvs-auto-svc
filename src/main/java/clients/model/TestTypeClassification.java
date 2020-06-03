package clients.model;

public enum TestTypeClassification {

    CERTIFICATE("Annual With Certificate"), NO_CERTIFICATE("Annual NO CERTIFICATE"),
    NON_ANUAL("NON ANNUAL"), NULL("null");

    private String value;

    TestTypeClassification(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
