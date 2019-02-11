package model;

public class TestTypeById {

    private String id;
    private String testTypeClassification;
    private String defaultTestCode;
    private String linkedTestCode;

    public String getId() {
        return id;
    }

    public TestTypeById setId(String id) {
        this.id = id;
        return this;
    }

    public String getTestTypeClassification() {
        return testTypeClassification;
    }

    public TestTypeById setTestTypeClassification(String testTypeClassification) {
        this.testTypeClassification = testTypeClassification;
        return this;
    }

    public String getDefaultTestCode() {
        return defaultTestCode;
    }

    public TestTypeById setDefaultTestCode(String defaultTestCode) {
        this.defaultTestCode = defaultTestCode;
        return this;
    }

    public String getLinkedTestCode() {
        return linkedTestCode;
    }

    public TestTypeById setLinkedTestCode(String linkedTestCode) {
        this.linkedTestCode = linkedTestCode;
        return this;
    }

    @Override
    public String toString() {
        return "TestTypeById{" +
                "id='" + id + '\'' +
                ", testTypeClassification='" + testTypeClassification + '\'' +
                ", defaultTestCode='" + defaultTestCode + '\'' +
                ", linkedTestCode='" + linkedTestCode + '\'' +
                '}';
    }
}
