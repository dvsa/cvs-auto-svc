package model.testresults;

import org.apache.commons.lang3.RandomStringUtils;

public enum TestVersion {

    CURRENT("current"), ARCHIVED("archived"),
    ALL("all"), INVALID("invalid") {
        @Override
        public String getTestVersion() {
            return RandomStringUtils.randomAlphanumeric(20);
        }
    };

    private String testVersion;

    TestVersion(String testVersion) {
        this.testVersion = testVersion;
    }

    public String getTestVersion() {
        return testVersion;
    }

}
