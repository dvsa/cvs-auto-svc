package model.testresults;


import java.util.List;

public class Defects {

    private Integer imNumber;
    private String imDescription;
    private List<String> forVehicleType;
    private AdditionalInformation additionalInformation;
    private Item item;

    public Integer getImNumber() {
        return imNumber;
    }

    public Defects setImNumber(Integer imNumber) {
        this.imNumber = imNumber;
        return this;
    }

    public String getImDescription() {
        return imDescription;
    }

    public Defects setImDescription(String imDescription) {
        this.imDescription = imDescription;
        return this;
    }

    public List<String> getForVehicleType() {
        return forVehicleType;
    }

    public Defects setForVehicleType(List<String> forVehicleType) {
        this.forVehicleType = forVehicleType;
        return this;
    }

    public AdditionalInformation getAdditionalInformation() {
        return additionalInformation;
    }

    public Defects setAdditionalInformation(AdditionalInformation additionalInformation) {
        this.additionalInformation = additionalInformation;
        return this;
    }

    public Item getItem() {
        return item;
    }

    public Defects setItem(Item item) {
        this.item = item;
        return this;
    }

    @Override
    public String toString() {
        return "Defects{" +
                "imNumber=" + imNumber +
                ", imDescription='" + imDescription + '\'' +
                ", forVehicleType=" + forVehicleType +
                ", additionalInformation=" + additionalInformation +
                ", item=" + item +
                '}';
    }
}
