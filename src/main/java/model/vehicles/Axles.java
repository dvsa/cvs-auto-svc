package model.vehicles;

public class Axles {

    private Integer axleNumber;
    private Weights weights;
    private Boolean parkingBrakeMrk;
    private Tyres tyres;

    public Integer getAxleNumber() {
        return axleNumber;
    }

    public Axles setAxleNumber(Integer axleNumber) {
        this.axleNumber = axleNumber;
        return this;
    }

    public Weights getWeights() {
        return weights;
    }

    public Axles setWeights(Weights weights) {
        this.weights = weights;
        return this;
    }

    public Boolean getParkingBrakeMrk() {
        return parkingBrakeMrk;
    }

    public Axles setParkingBrakeMrk(Boolean parkingBrakeMrk) {
        this.parkingBrakeMrk = parkingBrakeMrk;
        return this;
    }

    public Tyres getTyres() {
        return tyres;
    }

    public Axles setTyres(Tyres tyres) {
        this.tyres = tyres;
        return this;
    }

    @Override
    public String toString() {
        return "Axles{" +
                "parkingBrakeMrk=" + parkingBrakeMrk +
                "axleNumber=" + axleNumber +
                ", weights=" + weights +
                ", tyres=" + tyres +
                '}';
    }
}
