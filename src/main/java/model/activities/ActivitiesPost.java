package model.activities;

public class ActivitiesPost extends Activities {

    private String parentId;
    private String startTime;
    private String endTime;

    public static class Builder extends Activities.Builder<Builder> {

        private String parentId;
        private String startTime;
        private String endTime;

        public Builder() {
        }

        public Builder setParentId(String parentId) {
            this.parentId = parentId;
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

        public ActivitiesPost build() {
            return new ActivitiesPost(this);
        }
    }

    protected ActivitiesPost(Builder builder) {
        super(builder);
        this.parentId = builder.parentId;
        this.startTime = builder.startTime;
        this.endTime = builder.endTime;
    }

    public String getParentId() {
        return parentId;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    @Override
    public String toString() {
        return "ActivitiesPost{" +
                "parentId='" + parentId + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                '}';
    }
}
