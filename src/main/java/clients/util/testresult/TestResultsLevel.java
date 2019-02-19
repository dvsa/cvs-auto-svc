package clients.util.testresult;

public enum TestResultsLevel {

    MAIN_LEVEL("mainLevel"),TEST_TYPES("testTypes"), DEFECTS("defects"), ADDITIONAL_INFORMATION("additionalInformation"), LOCATION("location"), ITEM("item"), DEFICIENCY("deficiency");

    private String level;

    TestResultsLevel(String level) {
        this.level = level;
    }

    public String getLevel() {
        return level;
    }
}
