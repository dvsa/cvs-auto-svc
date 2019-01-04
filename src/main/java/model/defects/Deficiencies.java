package model.defects;

import java.util.List;

public class Deficiencies {
    private String ref;
    private String deficiencyId;
    private String deficiencySubId;
    private String deficiencyCategory;
    private String deficiencyText;
    private Boolean stdForProhibition;
    private List<String> forVehicleType;

    public String getRef() {
        return ref;
    }

    public Deficiencies setRef(String ref) {
        this.ref = ref;
        return this;
    }

    public String getDeficiencyId() {
        return deficiencyId;
    }

    public Deficiencies setDeficiencyId(String deficiencyId) {
        this.deficiencyId = deficiencyId;
        return this;
    }

    public String getDeficiencySubId() {
        return deficiencySubId;
    }

    public Deficiencies setDeficiencySubId(String deficiencySubId) {
        this.deficiencySubId = deficiencySubId;
        return this;
    }

    public String getDeficiencyCategory() {
        return deficiencyCategory;
    }

    public Deficiencies setDeficiencyCategory(String deficiencyCategory) {
        this.deficiencyCategory = deficiencyCategory;
        return this;
    }

    public String getDeficiencyText() {
        return deficiencyText;
    }

    public Deficiencies setDeficiencyText(String deficiencyText) {
        this.deficiencyText = deficiencyText;
        return this;
    }

    public Boolean getStdForProhibition() {
        return stdForProhibition;
    }

    public Deficiencies setStdForProhibition(Boolean stdForProhibition) {
        this.stdForProhibition = stdForProhibition;
        return this;
    }

    public List<String> getForVehicleType() {
        return forVehicleType;
    }

    public Deficiencies setForVehicleType(List<String> forVehicleType) {
        this.forVehicleType = forVehicleType;
        return this;
    }

    @Override
    public String toString() {
        return "Deficiencies{" +
                "ref='" + ref + '\'' +
                ", deficiencyId='" + deficiencyId + '\'' +
                ", deficiencySubId='" + deficiencySubId + '\'' +
                ", deficiencyCategory='" + deficiencyCategory + '\'' +
                ", deficiencyText='" + deficiencyText + '\'' +
                ", stdForProhibition=" + stdForProhibition +
                ", forVehicleType=" + forVehicleType +
                '}';
    }
}
