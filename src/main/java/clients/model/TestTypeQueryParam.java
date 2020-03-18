package clients.model;

import model.testresults.TestResults;
import model.vehicles.TechRecord;

import java.util.List;

public class TestTypeQueryParam {

    private VehicleType vehicleType;
    private VehicleSize vehicleSize;
    private VehicleConfiguration vehicleConfiguration;
    private VehicleAxles vehicleAxles;
    private EuVehicleCategory euVehicleCategory;
    private VehicleClass vehicleClass;
    private VehicleSubClass vehicleSubClass;
    private VehicleWheels vehicleWheels;



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

    public TestTypeQueryParam setVehicleWheels(VehicleWheels vehicleWheels ) {
        this.vehicleWheels = vehicleWheels;
        return this;
    }

    public TestTypeQueryParam setVehicleSubClass(VehicleSubClass vehicleSubClass ) {
        this.vehicleSubClass = vehicleSubClass;
        return this;
    }


    public TestTypeQueryParam setVehicleClass(VehicleClass vehicleClass ) {
        this.vehicleClass = vehicleClass;
        return this;
    }

    public TestTypeQueryParam setEuVehicleCategory(EuVehicleCategory euVehicleCategory ) {
        this.euVehicleCategory = euVehicleCategory;
        return this;
    }

    public String getVehicleWheels() {
        return vehicleWheels != null ? vehicleWheels.getValue() : null;
    }

    public String getVehicleSubClass() {
        return vehicleSubClass != null ? vehicleSubClass.getValue() : null;
    }

    public String getVehicleClass() {
        return vehicleClass != null ? vehicleClass.getCode() : null;
    }

    public String getEuVehicleCategory() {
        return euVehicleCategory != null ? euVehicleCategory.getValue() : null;
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
                ", vehicleWheels='" + vehicleWheels + '\'' +
                ", vehicleClass='" + vehicleClass + '\'' +
                ", vehicleSubclass='" + vehicleSubClass + '\'' +
                ", euVehicleCategory='" + euVehicleCategory + '\'' +
                ", fields=" + fields +
                '}';
    }

}
