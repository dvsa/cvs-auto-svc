package model.activities;

import java.util.List;

public class ActivitiesPut {

    private String id;
    private List<String> waitReason;
    private String notes;


    public static class Builder<T extends ActivitiesPut.Builder<T>> {

        private String id;
        private List<String> waitReason;
        private String notes;


        public Builder() {
        }

        public T setId(String id) {
            this.id = id;
            return (T) this;
        }

        public T setWaitReason(List<String> waitReason) {
            this.waitReason = waitReason;
            return (T) this;
        }

        public T setNotes(String notes) {
            this.notes = notes;
            return (T) this;
        }


        public ActivitiesPut build() {
            return new ActivitiesPut(this);
        }
    }


    protected ActivitiesPut(ActivitiesPut.Builder<?> builder) {
        this.id = builder.id;
        this.waitReason = builder.waitReason;
        this.notes = builder.notes;
    }


    public String getId() {
        return id;
    }

    public List<String> getWaitReason() {
        return waitReason;
    }

    public String getNotes() {
        return notes;
    }

    @Override
    public String toString() {
        return "Activities{" +
                "id='" + id + '\'' +
                ", waitReason='" + waitReason + '\'' +
                ", notes='" + notes + '\'' +
                '}';
    }
}
