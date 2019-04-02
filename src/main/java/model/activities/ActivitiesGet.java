package model.activities;

public class ActivitiesGet extends Activities {

    private String id;
    private String startTime;
    private String endTime;

    public static class Builder extends Activities.Builder<Builder> {

        private String id;
        private String startTime;
        private String endTime;

        public Builder() {
        }

        public Builder setId(String id) {
            this.id = id;
            return this;
        }

        public Builder setStartTime(String startTime) {
            this.startTime = startTime;
            return this;
        }

        public Builder setEndTime(String endTime) {
            this.endTime = endTime;
            return this;
        }

        public ActivitiesGet build() {
            return new ActivitiesGet(this);
        }
    }

    protected ActivitiesGet(Builder builder) {
        super(builder);
        this.id = builder.id;
        this.startTime = builder.startTime;
        this.endTime = builder.endTime;
    }

    public String getId() {
        return id;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    @Override
    public String toString() {
        return "ActivitiesGet{" +
                "id='" + id + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                '}';
    }
}
