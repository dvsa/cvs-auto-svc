package model.testresults;


public class AdditionalInformation {

    private Location location;
    private String notes;

    public Location getLocation() {
        return location;
    }

    public AdditionalInformation setLocation(Location location) {
        this.location = location;
        return this;
    }

    public String getNotes() {
        return notes;
    }

    public AdditionalInformation setNotes(String notes) {
        this.notes = notes;
        return this;
    }

    @Override
    public String toString() {
        return "AdditionalInformation{" +
                "location=" + location +
                ", notes='" + notes + '\'' +
                '}';
    }
}
