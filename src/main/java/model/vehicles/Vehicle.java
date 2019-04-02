package model.vehicles;

import java.util.List;

public class Vehicle {

    private List<Vrms> vrms;
    private String vim;
    private List<TechRecord> techRecord;

    public List<Vrms> getVrms() {
        return vrms;
    }

    public Vehicle setVrms(List<Vrms> vrms) {
        this.vrms = vrms;
        return this;
    }

    public String getVim() {
        return vim;
    }

    public Vehicle setVim(String vim) {
        this.vim = vim;
        return this;
    }

    public List<TechRecord> getTechRecord() {
        return techRecord;
    }

    public Vehicle setTechRecord(List<TechRecord> techRecord) {
        this.techRecord = techRecord;
        return this;
    }


    @Override
    public String toString() {
        return "Vehicle{" +
                "vrms=" + vrms +
                ", vim='" + vim + '\'' +
                ", techRecord=" + techRecord +
                '}';
    }
}
