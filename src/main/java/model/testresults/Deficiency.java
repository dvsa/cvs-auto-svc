package model.testresults;

import java.util.List;

public class Deficiency {

    private String ref;
    private String deficiencyId;
    private String deficiencySubId;
    private String deficiencyCategory;
    private String deficiencyText;
    private Boolean stdForProhibition;
    private Boolean prs;
    private List<String> forVehicleType;

    public String getRef() {
        return ref;
    }

    public Deficiency setRef(String ref) {
        this.ref = ref;
        return this;
    }

    public String getDeficiencyId() {
        return deficiencyId;
    }

    public Deficiency setDeficiencyId(String deficiencyId) {
        this.deficiencyId = deficiencyId;
        return this;
    }

    public String getDeficiencySubId() {
        return deficiencySubId;
    }

    public Deficiency setDeficiencySubId(String deficiencySubId) {
        this.deficiencySubId = deficiencySubId;
        return this;
    }

    public String getDeficiencyCategory() {
        return deficiencyCategory;
    }

    public Deficiency setDeficiencyCategory(String deficiencyCategory) {
        this.deficiencyCategory = deficiencyCategory;
        return this;
    }

    public String getDeficiencyText() {
        return deficiencyText;
    }

    public Deficiency setDeficiencyText(String deficiencyText) {
        this.deficiencyText = deficiencyText;
        return this;
    }

    public Boolean getStdForProhibition() {
        return stdForProhibition;
    }

    public Deficiency setStdForProhibition(Boolean stdForProhibition) {
        this.stdForProhibition = stdForProhibition;
        return this;
    }

    public Boolean getPrs() {
        return prs;
    }

    public Deficiency setPrs(Boolean prs) {
        this.prs = prs;
        return this;
    }

    public List<String> getForVehicleType() {
        return forVehicleType;
    }

    public Deficiency setForVehicleType(List<String> forVehicleType) {
        this.forVehicleType = forVehicleType;
        return this;
    }

    @Override
    public String toString() {
        return "Deficiency{" +
                "ref='" + ref + '\'' +
                ", deficiencyId='" + deficiencyId + '\'' +
                ", deficiencySubId='" + deficiencySubId + '\'' +
                ", deficiencyCategory='" + deficiencyCategory + '\'' +
                ", deficiencyText='" + deficiencyText + '\'' +
                ", stdForProhibition=" + stdForProhibition +
                ", prs=" + prs +
                ", forVehicleType=" + forVehicleType +
                '}';
    }
}
