package model.vehicles;

public class Tyres {

    private String tyreSize;
    private String plyRating;
    private String fitmentCode;
    private Integer dataTrAxles;
    private String speedCategorySymbol;
    private Integer tyreCode;

    public String getTyreSize() {
        return tyreSize;
    }

    public Tyres setTyreSize(String tyreSize) {
        this.tyreSize = tyreSize;
        return this;
    }

    public String getPlyRating() {
        return plyRating;
    }

    public Tyres setPlyRating(String plyRating) {
        this.plyRating = plyRating;
        return this;
    }

    public String getFitmentCode() {
        return fitmentCode;
    }

    public Tyres setFitmentCode(String fitmentCode) {
        this.fitmentCode = fitmentCode;
        return this;
    }

    public Integer getDataTrAxles() {
        return dataTrAxles;
    }

    public Tyres setDataTrAxles(Integer dataTrAxles) {
        this.dataTrAxles = dataTrAxles;
        return this;
    }

    public String getSpeedCategorySymbol() {
        return speedCategorySymbol;
    }

    public Tyres setSpeedCategorySymbol(String speedCategorySymbol) {
        this.speedCategorySymbol = speedCategorySymbol;
        return this;
    }

    public Integer getTyreCode() {
        return tyreCode;
    }

    public Tyres setTyreCode(Integer tyreCode) {
        this.tyreCode = tyreCode;
        return this;
    }

    @Override
    public String toString() {
        return "Tyres{" +
                "tyreSize='" + tyreSize + '\'' +
                ", plyRating='" + plyRating + '\'' +
                ", fitmentCode='" + fitmentCode + '\'' +
                ", dataTrAxles=" + dataTrAxles +
                ", speedCategorySymbol='" + speedCategorySymbol + '\'' +
                ", tyreCode=" + tyreCode +
                '}';
    }
}
