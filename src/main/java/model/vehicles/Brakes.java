package model.vehicles;

public class Brakes {

    private String brakeCode;
    private String dataTrBrakeOne;
    private String dataTrBrakeTwo;
    private String dataTrBrakeThree;
    private Boolean parkingBrakeMrk;
    private String retarderBrakeOne;
    private String retarderBrakeTwo;
    private BrakeForceWheelsNotLocked brakeForceWheelsNotLocked;
    private BrakeForceWheelsUpToHalfLocked brakeForceWheelsUpToHalfLocked;

    public String getBrakeCode() {
        return brakeCode;
    }

    public Brakes setBrakeCode(String brakeCode) {
        this.brakeCode = brakeCode;
        return this;
    }

    public String getDataTrBrakeOne() {
        return dataTrBrakeOne;
    }

    public Brakes setDataTrBrakeOne(String dataTrBrakeOne) {
        this.dataTrBrakeOne = dataTrBrakeOne;
        return this;
    }

    public String getDataTrBrakeTwo() {
        return dataTrBrakeTwo;
    }

    public Brakes setDataTrBrakeTwo(String dataTrBrakeTwo) {
        this.dataTrBrakeTwo = dataTrBrakeTwo;
        return this;
    }

    public String getDataTrBrakeThree() {
        return dataTrBrakeThree;
    }

    public Brakes setDataTrBrakeThree(String dataTrBrakeThree) {
        this.dataTrBrakeThree = dataTrBrakeThree;
        return this;
    }

    public Boolean getParkingBrakeMrk() {
        return parkingBrakeMrk;
    }

    public Brakes setParkingBrakeMrk(Boolean parkingBrakeMrk) {
        this.parkingBrakeMrk = parkingBrakeMrk;
        return this;
    }

    public String getRetarderBrakeOne() {
        return retarderBrakeOne;
    }

    public Brakes setRetarderBrakeOne(String retarderBrakeOne) {
        this.retarderBrakeOne = retarderBrakeOne;
        return this;
    }

    public String getRetarderBrakeTwo() {
        return retarderBrakeTwo;
    }

    public Brakes setRetarderBrakeTwo(String retarderBrakeTwo) {
        this.retarderBrakeTwo = retarderBrakeTwo;
        return this;
    }

    public BrakeForceWheelsNotLocked getBrakeForceWheelsNotLocked() {
        return brakeForceWheelsNotLocked;
    }

    public Brakes setBrakeForceWheelsNotLocked(BrakeForceWheelsNotLocked brakeForceWheelsNotLocked) {
        this.brakeForceWheelsNotLocked = brakeForceWheelsNotLocked;
        return this;
    }

    public BrakeForceWheelsUpToHalfLocked getBrakeForceWheelsUpToHalfLocked() {
        return brakeForceWheelsUpToHalfLocked;
    }

    public Brakes setBrakeForceWheelsUpToHalfLocked(BrakeForceWheelsUpToHalfLocked brakeForceWheelsUpToHalfLocked) {
        this.brakeForceWheelsUpToHalfLocked = brakeForceWheelsUpToHalfLocked;
        return this;
    }

    @Override
    public String toString() {
        return "Brakes{" +
                "brakeCode='" + brakeCode + '\'' +
                ", dataTrBrakeOne='" + dataTrBrakeOne + '\'' +
                ", dataTrBrakeTwo='" + dataTrBrakeTwo + '\'' +
                ", dataTrBrakeThree='" + dataTrBrakeThree + '\'' +
                ", parkingBrakeMrk='" + parkingBrakeMrk + '\'' +
                ", retarderBrakeOne='" + retarderBrakeOne + '\'' +
                ", retarderBrakeTwo='" + retarderBrakeTwo + '\'' +
                ", brakeForceWheelsNotLocked=" + brakeForceWheelsNotLocked +
                ", brakeForceWheelsUpToHalfLocked=" + brakeForceWheelsUpToHalfLocked +
                '}';
    }
}
