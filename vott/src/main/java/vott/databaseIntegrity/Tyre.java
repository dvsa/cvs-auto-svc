package vott.databaseIntegrity;

import vott.DataMethods;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class Tyre {

    private String tyreSize;
    private String plyRating;
    private String fitmentCode;
    private String dataTrAxles;
    private String speedCategorySymbol;
    private Integer tyreCode;

    public String getTyreSize(){ return tyreSize;}
    public String getPlyRating(){ return plyRating;}
    public String getFitmentCode(){ return fitmentCode;}
    public String getDataTrAxles(){ return dataTrAxles;}
    public String getSpeedCategorySymbol(){ return speedCategorySymbol;}
    public Integer getTyreCode(){ return tyreCode;}

    public void setTyre(ResultSet rs) throws SQLException {
        this.tyreSize = Objects.toString(rs.getString("tyreSize"), "");
        this.plyRating = Objects.toString(rs.getString("plyRating"), "");
        this.fitmentCode = Objects.toString(rs.getString("fitmentCode"), "");
        this.dataTrAxles = Objects.toString(rs.getString("dataTrAxles"), "");
        this.speedCategorySymbol = Objects.toString(rs.getString("speedCategorySymbol"), "");
        this.tyreCode = DataMethods.getInteger(rs, "tyreCode");

    }

    public String createInsertQuery(){
        return "INSERT INTO tyre( tyreSize, plyRating, fitmentCode, dataTrAxles, speedCategorySymbol, tyreCode ) " +
                "VALUES ('"+tyreSize+"', '"+plyRating+"', '"+fitmentCode+"', '"+dataTrAxles+"', '"+speedCategorySymbol+"', "+tyreCode+") " +
                "ON DUPLICATE KEY UPDATE id=LAST_INSERT_ID(id)";
    }
}
