package model.vehicles;

public class Axles {

    private Integer axleNumber;
    private Weights weights;
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
                "axleNumber=" + axleNumber +
                ", weights=" + weights +
                ", tyres=" + tyres +
                '}';
    }
}
