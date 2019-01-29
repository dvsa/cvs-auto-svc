package model.vehicles;

public class BrakeForceWheelsUpToHalfLocked {

    private Integer serviceBrakeForceB;
    private Integer secondaryBrakeForceB;
    private Integer parkingBrakeForceB;

    public Integer getServiceBrakeForceB() {
        return serviceBrakeForceB;
    }

    public BrakeForceWheelsUpToHalfLocked setServiceBrakeForceB(Integer serviceBrakeForceB) {
        this.serviceBrakeForceB = serviceBrakeForceB;
        return this;
    }

    public Integer getSecondaryBrakeForceB() {
        return secondaryBrakeForceB;
    }

    public BrakeForceWheelsUpToHalfLocked setSecondaryBrakeForceB(Integer secondaryBrakeForceB) {
        this.secondaryBrakeForceB = secondaryBrakeForceB;
        return this;
    }

    public Integer getParkingBrakeForceB() {
        return parkingBrakeForceB;
    }

    public BrakeForceWheelsUpToHalfLocked setParkingBrakeForceB(Integer parkingBrakeForceB) {
        this.parkingBrakeForceB = parkingBrakeForceB;
        return this;
    }

    @Override
    public String toString() {
        return "BrakeForceWheelsUpToHalfLocked{" +
                "serviceBrakeForceB=" + serviceBrakeForceB +
                ", secondaryBrakeForceB=" + secondaryBrakeForceB +
                ", parkingBrakeForceB=" + parkingBrakeForceB +
                '}';
    }
}
