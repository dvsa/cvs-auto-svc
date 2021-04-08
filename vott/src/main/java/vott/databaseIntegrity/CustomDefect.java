package vott.databaseIntegrity;

import vott.DataMethods;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class CustomDefect {

    private Integer testResultID;
    private String referenceNumber;
    private String defectName;
    private String defectNotes;

    public Integer getTestResultID(){ return testResultID;}
    public String getReferenceNumber(){ return referenceNumber;}
    public String getDefectName(){ return defectName;}
    public String getDefectNotes(){ return defectNotes;}

    public void setCustomDefect(ResultSet rs) throws SQLException {
        this.testResultID = DataMethods.getInteger(rs, "test_result_id");
        this.referenceNumber = Objects.toString(rs.getString("referenceNumber"), "");
        this.defectName = Objects.toString(rs.getString("defectName"), "");
        this.defectNotes = Objects.toString(rs.getString("defectNotes"), "");
    }

    public String createInsertQuery(){
        return "INSERT INTO custom_defect( test_result_id, referenceNumber, defectName, defectNotes ) " +
                "VALUES ("+testResultID+", '"+referenceNumber+"', '"+defectName+"', '"+defectNotes+"') " +
                "ON DUPLICATE KEY UPDATE id=LAST_INSERT_ID(id)";
    }
}
