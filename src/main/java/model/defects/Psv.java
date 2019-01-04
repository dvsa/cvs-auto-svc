package model.defects;

public class Psv {

    private Location location;
    private Boolean notes;

    public Location getLocation() {
        return location;
    }

    public Psv setLocation(Location location) {
        this.location = location;
        return this;
    }

    public Boolean getNotes() {
        return notes;
    }

    public Psv setNotes(Boolean notes) {
        this.notes = notes;
        return this;
    }

    @Override
    public String toString() {
        return "Psv{" +
                "location=" + location +
                ", notes=" + notes +
                '}';
    }
}
