package model.defects;


public class AdditionalInfo {
    private Psv psv;
    private Hgv hgv;
    private Trl trl;

    public Psv getPsv() {
        return psv;
    }

    public AdditionalInfo setPsv(Psv psv) {
        this.psv = psv;
        return this;
    }

    public Hgv getHgv() {
        return hgv;
    }

    public AdditionalInfo setHgv(Hgv hgv) {
        this.hgv = hgv;
        return this;
    }

    public Trl getTrl() {
        return trl;
    }

    public AdditionalInfo setTrl(Trl trl) {
        this.trl = trl;
        return this;
    }

    @Override
    public String toString() {
        return "AdditionalInfo{" +
                "psv=" + psv +
                ", hgv=" + hgv +
                ", trl=" + trl +
                '}';
    }
}
