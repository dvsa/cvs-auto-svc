package model.vehicles;

public class BodyType {

    private String code;
    private String description;

    public String getCode() {
        return code;
    }

    public BodyType setCode(String code) {
        this.code = code;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public BodyType setDescription(String description) {
        this.description = description;
        return this;
    }

    @Override
    public String toString() {
        return "BodyType{" +
                "code='" + code + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
