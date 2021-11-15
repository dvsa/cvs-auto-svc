package model.activities;

public class ActivitiesOpenVisit extends Activities{
    private String id;

    public static class Builder extends Activities.Builder<ActivitiesGet.Builder> {

        private String id;
        public Builder() {
        }

        public ActivitiesOpenVisit.Builder setId(String id) {
            this.id = id;
            return this;
        }

        public ActivitiesOpenVisit build() {
            return new ActivitiesOpenVisit(this);
        }
    }

    protected ActivitiesOpenVisit(ActivitiesOpenVisit.Builder builder) {
        super(builder);
        this.id = builder.id;

    }

    public String getId() {
        return id;
    }


    @Override
    public String toString() {
        return "ActivitiesGet{" +
                "id='" + id  + '}';
    }
}
