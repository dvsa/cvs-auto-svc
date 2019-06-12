package model.testresults;

public class TestResultsGet extends TestResults {

    private String vehicleId;

    public static class Builder extends TestResults.Builder<Builder> {

        private String vehicleId;

        public Builder() {}

        public Builder setVehicleId(String vehicleId) {
            this.vehicleId = vehicleId;
            return this;
        }

        public TestResultsGet build() { return new TestResultsGet(this); }
    }

    protected TestResultsGet(Builder builder) {
        super(builder);
        vehicleId = builder.vehicleId;
    }

    public TestResultsGet() {
        super();
    }

    public String getVehicleId() {
        return vehicleId;
    }

}
