package model;

import java.util.List;

public class TestType {

    private String id;
    private String name;
    private List<String> forVehicleType;
    private List<String> forVehicleSize;
    private List<String> forVehicleConfiguration;
    //TODO: update automation after data fix from development side
//    private List<Integer> forVehicleAxles;
    private List<TestType> nextTestTypesOrCategories;

    public String getId() {
        return id;
    }

    public TestType setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public TestType setName(String name) {
        this.name = name;
        return this;
    }

    public List<String> getForVehicleType() {
        return forVehicleType;
    }

    public TestType setForVehicleType(List<String> forVehicleType) {
        this.forVehicleType = forVehicleType;
        return this;
    }

    public List<String> getForVehicleSize() {
        return forVehicleSize;
    }

    public TestType setForVehicleSize(List<String> forVehicleSize) {
        this.forVehicleSize = forVehicleSize;
        return this;
    }

    public List<String> getForVehicleConfiguration() {
        return forVehicleConfiguration;
    }

    public TestType setForVehicleConfiguration(List<String> forVehicleConfiguration) {
        this.forVehicleConfiguration = forVehicleConfiguration;
        return this;
    }

//    public List<Integer> getForVehicleAxles() {
//        return forVehicleAxles;
//    }
//
//    public TestType setForVehicleAxles(List<Integer> forVehicleAxles) {
//        this.forVehicleAxles = forVehicleAxles;
//        return this;
//    }

    public List<TestType> getNextTestTypesOrCategories() {
        return nextTestTypesOrCategories;
    }

    public TestType setNextTestTypesOrCategories(List<TestType> nextTestTypesOrCategories) {
        this.nextTestTypesOrCategories = nextTestTypesOrCategories;
        return this;
    }

    @Override
    public String toString() {
        return "TestType{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", forVehicleType=" + forVehicleType +
                ", forVehicleSize=" + forVehicleSize +
                ", forVehicleConfiguration=" + forVehicleConfiguration +
                ", nextTestTypesOrCategories=" + nextTestTypesOrCategories +
                '}';
    }
}
