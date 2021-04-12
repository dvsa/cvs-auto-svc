package vott.databaseModels;

import lombok.Data;
import vott.DataMethods;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

@Data
public class Tyre {

    private String tyreSize;
    private String plyRating;
    private String fitmentCode;
    private String dataTrAxles;
    private String speedCategorySymbol;
    private String tyreCode;
//    private Integer tyreCode;

//    public void setTyre(ResultSet rs) throws SQLException {
//        this.tyreSize = Objects.toString(rs.getString("tyreSize"), "");
//        this.plyRating = Objects.toString(rs.getString("plyRating"), "");
//        this.fitmentCode = Objects.toString(rs.getString("fitmentCode"), "");
//        this.dataTrAxles = Objects.toString(rs.getString("dataTrAxles"), "");
//        this.speedCategorySymbol = Objects.toString(rs.getString("speedCategorySymbol"), "");
//        this.tyreCode = DataMethods.getInteger(rs, "tyreCode");
//
//    }

    public String createInsertQuery(){
        return "INSERT INTO tyre( tyreSize, plyRating, fitmentCode, dataTrAxles, speedCategorySymbol, tyreCode ) " +
                "VALUES ('"+tyreSize+"', '"+plyRating+"', '"+fitmentCode+"', '"+dataTrAxles+"', '"+speedCategorySymbol+"', "+tyreCode+") " +
                "ON DUPLICATE KEY UPDATE id=LAST_INSERT_ID(id)";
    }
}
