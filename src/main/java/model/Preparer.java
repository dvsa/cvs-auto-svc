package model;

public class Preparer {

    private String preparerId;
    private String preparerName;

    public String getPreparerId() {
        return preparerId;
    }

    public Preparer setPreparerId(String preparerId) {
        this.preparerId = preparerId;
        return this;
    }

    public String getPreparerName() {
        return preparerName;
    }

    public Preparer setPreparerName(String preparerName) {
        this.preparerName = preparerName;
        return this;
    }

    @Override
    public String toString() {
        return "Preparer{" +
                "preparerId='" + preparerId + '\'' +
                ", preparerName='" + preparerName + '\'' +
                '}';
    }
}
