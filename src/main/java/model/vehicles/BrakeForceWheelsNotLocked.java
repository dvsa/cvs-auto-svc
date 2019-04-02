package model.vehicles;

public class BrakeForceWheelsNotLocked {
    
    private Integer serviceBrakeForceA;
    private Integer secondaryBrakeForceA;
    private Integer parkingBrakeForceA;

    public Integer getServiceBrakeForceA() {
        return serviceBrakeForceA;
    }

    public BrakeForceWheelsNotLocked setServiceBrakeForceA(Integer serviceBrakeForceA) {
        this.serviceBrakeForceA = serviceBrakeForceA;
        return this;
    }

    public Integer getSecondaryBrakeForceA() {
        return secondaryBrakeForceA;
    }

    public BrakeForceWheelsNotLocked setSecondaryBrakeForceA(Integer secondaryBrakeForceA) {
        this.secondaryBrakeForceA = secondaryBrakeForceA;
        return this;
    }

    public Integer getParkingBrakeForceA() {
        return parkingBrakeForceA;
    }

    public BrakeForceWheelsNotLocked setParkingBrakeForceA(Integer parkingBrakeForceA) {
        this.parkingBrakeForceA = parkingBrakeForceA;
        return this;
    }


    @Override
    public String toString() {
        return "BrakeForceWheelsNotLocked{" +
                "serviceBrakeForceA=" + serviceBrakeForceA +
                ", secondaryBrakeForceA=" + secondaryBrakeForceA +
                ", secondaryBrakeForceA=" + secondaryBrakeForceA +
                '}';
    }
}
