package model.testresults;

public class VehicleClass {

    private String code;
    private String description;

    public String getCode() {
        return code;
    }

    public VehicleClass setCode(String code) {
        this.code = code;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public VehicleClass setDescription(String description) {
        this.description = description;
        return this;
    }

    @Override
    public String toString() {
        return "VehicleClass{" +
                "code='" + code + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}