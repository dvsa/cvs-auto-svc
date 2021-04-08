package vott.databaseIntegrity;

import vott.DataMethods;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class Defect {

    private Integer imNumber;
    private String imDescription;
    private Integer itemNumber;
    private String itemDescription;
    private String deficiencyRef;
    private String deficiencyID;
    private String deficiencySubID;
    private String deficiencyCategory;
    private String deficiencyText;
    private Integer stdForProhibition;

    public Integer getImNumber(){ return imNumber;}
    public String getImDescription(){ return imDescription;}
    public Integer getItemNumber(){ return itemNumber;}
    public String getItemDescription(){ return itemDescription;}
    public String getDeficiencyRef(){ return deficiencyRef;}
    public String getDeficiencyID(){ return deficiencyID;}
    public String getDeficiencySubID(){ return deficiencySubID;}
    public String getDeficiencyCategory(){ return deficiencyCategory;}
    public String getDeficiencyText(){ return deficiencyText;}
    public Integer getStdForProhibition(){ return stdForProhibition;}

    public void setDefect(ResultSet rs) throws SQLException {
        this.imNumber = DataMethods.getInteger(rs, "imNumber");
        this.imDescription = Objects.toString(rs.getString("imDescription"), "");
        this.itemNumber = DataMethods.getInteger(rs, "itemNumber");
        this.itemDescription = Objects.toString(rs.getString("itemDescription"), "");
        this.deficiencyRef = Objects.toString(rs.getString("deficiencyRef"), "");
        this.deficiencyID = Objects.toString(rs.getString("deficiencyId"), "");
        this.deficiencySubID = Objects.toString(rs.getString("deficiencySubId"), "");
        this.deficiencyCategory = Objects.toString(rs.getString("deficiencyCategory"), "");
        this.deficiencyText = Objects.toString(rs.getString("deficiencyText"), "");
        this.stdForProhibition = DataMethods.getInteger(rs, "stdForProhibition");
    }

    public String createInsertQuery(){
        return "INSERT INTO defect( imNumber, imDescription, itemNumber, itemDescription, deficiencyRef, deficiencyId, deficiencySubId, deficiencyCategory, deficiencyText, stdForProhibition ) " +
                "VALUES ('"+imNumber+"', '"+imDescription+"', '"+itemNumber+"', '"+itemDescription+"', '"+deficiencyRef+"', '"+deficiencyID+"', '"+deficiencySubID+"', '"+deficiencyCategory+"', '"+deficiencyText+"', '"+stdForProhibition+"') " +
                "ON DUPLICATE KEY UPDATE id=LAST_INSERT_ID(id)";
    }
}
