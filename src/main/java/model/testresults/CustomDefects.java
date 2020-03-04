package model.testresults;


public class CustomDefects {

    private String referenceNumber;
    private String defectName;
    private String defectNotes;

    public String getReferenceNumber() {
        return referenceNumber;
    }

    public CustomDefects setReferenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber;
        return this;
    }

    public String getDefectName() {
        return defectName;
    }

    public CustomDefects setDefectName(String defectName) {
        this.defectName = defectName;
        return this;
    }

    public String getDefectNotes() {
        return defectNotes;
    }

    public CustomDefects setDefectNotes(String defectNotes) {
        this.defectNotes = defectNotes;
        return this;
    }

    @Override
    public String toString() {
        return "Defects{" +
                "referenceNumber=" + referenceNumber +
                ", defectName='" + defectName + '\'' +
                ", defectNotes=" + defectNotes +
                '}';
    }
}
