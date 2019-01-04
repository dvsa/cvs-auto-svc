package model.defects;

import java.util.List;

public class Defect {

    private Integer imNumber;
    private String imDescription;
    private List<String> forVehicleType;
    private AdditionalInfo additionalInfo;
    private List<Items> items;

    public Integer getImNumber() {
        return imNumber;
    }

    public Defect setImNumber(Integer imNumber) {
        this.imNumber = imNumber;
        return this;
    }

    public String getImDescription() {
        return imDescription;
    }

    public Defect setImDescription(String imDescription) {
        this.imDescription = imDescription;
        return this;
    }

    public List<String> getForVehicleType() {
        return forVehicleType;
    }

    public Defect setForVehicleType(List<String> forVehicleType) {
        this.forVehicleType = forVehicleType;
        return this;
    }

    public AdditionalInfo getAdditionalInfo() {
        return additionalInfo;
    }

    public Defect setAdditionalInfo(AdditionalInfo additionalInfo) {
        this.additionalInfo = additionalInfo;
        return this;
    }

    public List<Items> getItems() {
        return items;
    }

    public Defect setItems(List<Items> items) {
        this.items = items;
        return this;
    }

    @Override
    public String toString() {
        return "Defect{" +
                "imNumber=" + imNumber +
                ", imDescription='" + imDescription + '\'' +
                ", forVehicleType=" + forVehicleType +
                ", additionalInfo=" + additionalInfo +
                ", items=" + items +
                '}';
    }
}
