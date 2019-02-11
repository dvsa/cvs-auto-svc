package clients.model;

import org.apache.commons.lang3.RandomStringUtils;

public enum TestTypeField {

    TEST_TYPE_CLASSIFICATION("testTypeClassification"), DEFAULT_TEST_CODE("defaultTestCode"), LINKED_TEST_CODE("linkedTestCode"),
    INVALID("invalid") {
        @Override
        public String getField() {
            this.field = RandomStringUtils.randomAlphanumeric(20);
            return field;
        }

        @Override
        public String getCurrentField() {
            return this.field;
        }
    };

    protected String field;

    TestTypeField(String field) {
        this.field = field;
    }

    public String getField() {
        return field;
    }

    public String getCurrentField() {
        return field;
    }
}

