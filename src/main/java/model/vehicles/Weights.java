package model.vehicles;

public class Weights {
    
    private Integer kerbWeight;
    private Integer ladenWeight;
    private Integer gbWeight;
    private Integer designWeight;
    private Boolean parkingBrakeMrk;

    public Integer getKerbWeight() {
        return kerbWeight;
    }

    public Weights setKerbWeight(Integer kerbWeight) {
        this.kerbWeight = kerbWeight;
        return this;
    }

    public Integer getLadenWeight() {
        return ladenWeight;
    }

    public Weights setLadenWeight(Integer ladenWeight) {
        this.ladenWeight = ladenWeight;
        return this;
    }

    public Integer getGbWeight() {
        return gbWeight;
    }

    public Weights setGbWeight(Integer gbWeight) {
        this.gbWeight = gbWeight;
        return this;
    }

    public Integer getDesignWeight() {
        return designWeight;
    }

    public Weights setDesignWeight(Integer designWeight) {
        this.designWeight = designWeight;
        return this;
    }

    public Boolean getParkingBrakeMrk() {
        return parkingBrakeMrk;
    }

    public Weights setParkingBrakeMrk(Boolean parkingBrakeMrk) {
        this.parkingBrakeMrk = parkingBrakeMrk;
        return this;
    }

    @Override
    public String toString() {
        return "Weights{" +
                "kerbWeight=" + kerbWeight +
                ", ladenWeight=" + ladenWeight +
                ", gbWeight=" + gbWeight +
                ", designWeight=" + designWeight +
                ", parkingBrakeMrk=" + parkingBrakeMrk +
                '}';
    }
}
