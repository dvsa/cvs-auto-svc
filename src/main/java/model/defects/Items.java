package model.defects;

import java.util.List;

public class Items {

    private Integer itemNumber;
    private String itemDescription;
    private List<String> forVehicleType;
    private List<Deficiencies> deficiencies;

    public Integer getItemNumber() {
        return itemNumber;
    }

    public Items setItemNumber(Integer itemNumber) {
        this.itemNumber = itemNumber;
        return this;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public Items setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
        return this;
    }

    public List<String> getForVehicleType() {
        return forVehicleType;
    }

    public Items setForVehicleType(List<String> forVehicleType) {
        this.forVehicleType = forVehicleType;
        return this;
    }

    public List<Deficiencies> getDeficiencies() {
        return deficiencies;
    }

    public Items setDeficiencies(List<Deficiencies> deficiencies) {
        this.deficiencies = deficiencies;
        return this;
    }

    @Override
    public String toString() {
        return "Items{" +
                "itemNumber=" + itemNumber +
                ", itemDescription='" + itemDescription + '\'' +
                ", forVehicleType=" + forVehicleType +
                ", deficiencies=" + deficiencies +
                '}';
    }
}
