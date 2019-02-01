package model.testresults;

import java.util.List;

public class Item {

    private Integer itemNumber;
    private String itemDescription;
    private List<String> forVehicleType;
    private Deficiency deficiency;

    public Integer getItemNumber() {
        return itemNumber;
    }

    public Item setItemNumber(Integer itemNumber) {
        this.itemNumber = itemNumber;
        return this;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public Item setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
        return this;
    }

    public List<String> getForVehicleType() {
        return forVehicleType;
    }

    public Item setForVehicleType(List<String> forVehicleType) {
        this.forVehicleType = forVehicleType;
        return this;
    }

    public Deficiency getDeficiency() {
        return deficiency;
    }

    public Item setDeficiency(Deficiency deficiency) {
        this.deficiency = deficiency;
        return this;
    }

    @Override
    public String toString() {
        return "Item{" +
                "itemNumber=" + itemNumber +
                ", itemDescription='" + itemDescription + '\'' +
                ", forVehicleType=" + forVehicleType +
                ", deficiency=" + deficiency +
                '}';
    }
}
