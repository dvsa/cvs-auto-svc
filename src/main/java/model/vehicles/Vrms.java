package model.vehicles;


public class Vrms {

    private String vrm;
    private Boolean isPrimary;

    public String getVrm() {
        return vrm;
    }

    public Vrms setVrm(String vrm) {
        this.vrm = vrm;
        return this;
    }

    public Boolean getPrimary() {
        return isPrimary;
    }

    public Vrms setPrimary(Boolean primary) {
        isPrimary = primary;
        return this;
    }

    @Override
    public String toString() {
        return "Vrms{" +
                "vrm='" + vrm + '\'' +
                ", isPrimary=" + isPrimary +
                '}';
    }
}
