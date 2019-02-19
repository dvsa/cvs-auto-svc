package model.vehicles;

public class VehicleClass {

    private String description;
    private String code;

    public String getDescription() {
        return description;
    }

    public VehicleClass setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getCode() {
        return code;
    }

    public VehicleClass setCode(String code) {
        this.code = code;
        return this;
    }

    @Override
    public String toString() {
        return "VehicleClass{" +
                "description='" + description + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
