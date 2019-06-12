package model.vehicles;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Vrms {

    private String vrm;
    private Boolean isPrimary;

    public String getVrm() {
        return vrm;
    }

//    @JsonProperty("vrms")
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
