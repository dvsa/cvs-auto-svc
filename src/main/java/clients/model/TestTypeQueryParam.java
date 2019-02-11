package clients.model;

import java.util.List;

public class TestTypeQueryParam {

    private VehicleType vehicleType;
    private VehicleSize vehicleSize;
    private VehicleConfiguration vehicleConfiguration;
    private VehicleAxles vehicleAxles;
    private List<TestTypeField> fields;

    public String getVehicleType() {
        return vehicleType != null ? vehicleType.getValue() : null;
    }

    public TestTypeQueryParam setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
        return this;
    }

    public String getVehicleSize() {
        return vehicleSize != null ? vehicleSize.getValue() : null;
    }

    public TestTypeQueryParam setVehicleSize(VehicleSize vehicleSize) {
        this.vehicleSize = vehicleSize;
        return this;
    }

    public String getVehicleConfiguration() {
        return vehicleConfiguration != null ? vehicleConfiguration.getValue() : null;
    }

    public TestTypeQueryParam setVehicleConfiguration(VehicleConfiguration vehicleConfiguration) {
        this.vehicleConfiguration = vehicleConfiguration;
        return this;
    }

    public String getVehicleAxles() {
        return vehicleAxles !=null ? vehicleAxles.getValue() : null;
    }

    public TestTypeQueryParam setVehicleAxles(VehicleAxles vehicleAxles) {
        this.vehicleAxles = vehicleAxles;
        return this;
    }

    public List<TestTypeField> getFields() {
        return fields;
    }

    public TestTypeQueryParam setFields(List<TestTypeField> fields) {
        this.fields = fields;
        return this;
    }

    @Override
    public String toString() {
        return "TestTypeQueryParam{" +
                "vehicleType='" + vehicleType + '\'' +
                ", vehicleSize='" + vehicleSize + '\'' +
                ", vehicleConfiguration='" + vehicleConfiguration + '\'' +
                ", vehicleAxles='" + vehicleAxles + '\'' +
                ", fields=" + fields +
                '}';
    }
}
