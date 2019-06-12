package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(value = { "linkedIds", "testTypeClassification", "testCodes" })
public class TestType {

    private String id;
    private String name;
    private String testTypeName;
    private List<String> forVehicleType;
    private List<String> forVehicleSize;
    private List<String> forVehicleConfiguration;
    private List<Integer> forVehicleAxles;
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

    public String getTestTypeName() {
        return testTypeName;
    }

    public TestType setTestTypeName(String testTypeName) {
        this.testTypeName = testTypeName;
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

    public List<Integer> getForVehicleAxles() {
        return forVehicleAxles;
    }

    public TestType setForVehicleAxles(List<Integer> forVehicleAxles) {
        this.forVehicleAxles = forVehicleAxles;
        return this;
    }

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
                ", testTypeName='" + testTypeName + '\'' +
                ", forVehicleType=" + forVehicleType +
                ", forVehicleSize=" + forVehicleSize +
                ", forVehicleConfiguration=" + forVehicleConfiguration +
                ", forVehicleAxles=" + forVehicleAxles +
                ", nextTestTypesOrCategories=" + nextTestTypesOrCategories +
                '}';
    }
}
