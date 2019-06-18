package model.testtypeid;

import java.util.List;

public class TestTypeById {

    private String id;
    private String testTypeClassification;
    private String defaultTestCode;
    private String linkedTestCode;

    private List<TestCodes> testCodes;
    private List<TestTypeById> nextTestTypesOrCategories;


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

    public List<TestTypeById> getNextTestTypesOrCategories() {
        return nextTestTypesOrCategories;
    }

    public TestTypeById setNextTestTypesOrCategories(List<TestTypeById> nextTestTypesOrCategories) {
        this.nextTestTypesOrCategories = nextTestTypesOrCategories;
        return this;
    }

    public List<TestCodes> getTestCodes() {
        return testCodes;
    }

    public TestTypeById setTestCodes(List<TestCodes> testCodes) {
        this.testCodes = testCodes;
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
