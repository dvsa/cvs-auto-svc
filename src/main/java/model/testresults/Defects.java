package model.testresults;


public class Defects {

    private Integer imNumber;
    private String imDescription;
    private AdditionalInformation additionalInformation;
    private Integer itemNumber;
    private String itemDescription;
    private String deficiencyRef;
    private String deficiencyId;
    private String deficiencySubId;
    private String deficiencyCategory;
    private String deficiencyText;
    private Boolean stdForProhibition;
    private Boolean prs;
    private Boolean prohibitionIssued;

    public Boolean getProhibitionIssued() {
        return prohibitionIssued;
    }

    public Defects setProhibitionIssued(Boolean prohibitionIssued) {
        this.prohibitionIssued = prohibitionIssued;
        return this;
    }

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

    public AdditionalInformation getAdditionalInformation() {
        return additionalInformation;
    }

    public Defects setAdditionalInformation(AdditionalInformation additionalInformation) {
        this.additionalInformation = additionalInformation;
        return this;
    }

    public Integer getItemNumber() {
        return itemNumber;
    }

    public Defects setItemNumber(Integer itemNumber) {
        this.itemNumber = itemNumber;
        return this;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public Defects setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
        return this;
    }

    public String getDeficiencyRef() {
        return deficiencyRef;
    }

    public Defects setDeficiencyRef(String deficiencyRef) {
        this.deficiencyRef = deficiencyRef;
        return this;
    }

    public String getDeficiencyId() {
        return deficiencyId;
    }

    public Defects setDeficiencyId(String deficiencyId) {
        this.deficiencyId = deficiencyId;
        return this;
    }

    public String getDeficiencySubId() {
        return deficiencySubId;
    }

    public Defects setDeficiencySubId(String deficiencySubId) {
        this.deficiencySubId = deficiencySubId;
        return this;
    }

    public String getDeficiencyCategory() {
        return deficiencyCategory;
    }

    public Defects setDeficiencyCategory(String deficiencyCategory) {
        this.deficiencyCategory = deficiencyCategory;
        return this;
    }

    public String getDeficiencyText() {
        return deficiencyText;
    }

    public Defects setDeficiencyText(String deficiencyText) {
        this.deficiencyText = deficiencyText;
        return this;
    }

    public Boolean getStdForProhibition() {
        return stdForProhibition;
    }

    public Defects setStdForProhibition(Boolean stdForProhibition) {
        this.stdForProhibition = stdForProhibition;
        return this;
    }

    public Boolean getPrs() {
        return prs;
    }

    public Defects setPrs(Boolean prs) {
        this.prs = prs;
        return this;
    }

    @Override
    public String toString() {
        return "Defects{" +
                "prohibitionIssued=" + prohibitionIssued +
                ", imNumber=" + imNumber +
                ", imDescription='" + imDescription + '\'' +
                ", additionalInformation=" + additionalInformation +
                ", itemNumber=" + itemNumber +
                ", itemDescription='" + itemDescription + '\'' +
                ", deficiencyRef='" + deficiencyRef + '\'' +
                ", deficiencyId='" + deficiencyId + '\'' +
                ", deficiencySubId='" + deficiencySubId + '\'' +
                ", deficiencyCategory='" + deficiencyCategory + '\'' +
                ", deficiencyText='" + deficiencyText + '\'' +
                ", stdForProhibition=" + stdForProhibition +
                ", prs=" + prs +
                '}';
    }
}
