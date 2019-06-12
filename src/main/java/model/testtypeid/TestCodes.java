package model.testtypeid;

public class TestCodes {

    private String forVehicleType;
    private String forVehicleSize;
    private String forVehicleConfiguration;
    private String forVehicleAxles;

    private String defaultTestCode;
    private String linkedTestCode;

    public String getForVehicleType() {
        return forVehicleType;
    }

    public TestCodes setForVehicleType(String forVehicleType) {
        this.forVehicleType = forVehicleType;
        return this;
    }

    public String getForVehicleSize() {
        return forVehicleSize;
    }

    public TestCodes setForVehicleSize(String forVehicleSize) {
        this.forVehicleSize = forVehicleSize;
        return this;
    }

    public String getForVehicleConfiguration() {
        return forVehicleConfiguration;
    }

    public TestCodes setForVehicleConfiguration(String forVehicleConfiguration) {
        this.forVehicleConfiguration = forVehicleConfiguration;
        return this;
    }

    public String getForVehicleAxles() {
        return forVehicleAxles;
    }

    public TestCodes setForVehicleAxles(String forVehicleAxles) {
        this.forVehicleAxles = forVehicleAxles;
        return this;
    }

    public String getDefaultTestCode() {
        return defaultTestCode;
    }

    public TestCodes setDefaultTestCode(String defaultTestCode) {
        this.defaultTestCode = defaultTestCode;
        return this;
    }

    public String getLinkedTestCode() {
        return linkedTestCode;
    }

    public TestCodes setLinkedTestCode(String linkedTestCode) {
        this.linkedTestCode = linkedTestCode;
        return this;
    }

    @Override
    public String toString() {
        return "TestCodes{" +
                "forVehicleType='" + forVehicleType + '\'' +
                ", forVehicleSize='" + forVehicleSize + '\'' +
                ", forVehicleConfiguration='" + forVehicleConfiguration + '\'' +
                ", forVehicleAxles='" + forVehicleAxles + '\'' +
                ", defaultTestCode='" + defaultTestCode + '\'' +
                ", linkedTestCode='" + linkedTestCode + '\'' +
                '}';
    }
}
