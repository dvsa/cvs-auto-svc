package model.testresults;


public class ModType {

    private String code;
    private String description;

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public ModType setCode(String code) {
        this.code = code;
        return this;
    }

    public ModType setDescription(String description) {
        this.description = description;
        return this;
    }

    @Override
    public String toString() {
        return "ModType{" +
                "code=" + code +
                ", description='" + description +
                '}';
    }
}
