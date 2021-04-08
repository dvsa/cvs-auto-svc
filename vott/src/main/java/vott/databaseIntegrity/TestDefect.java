package vott.databaseIntegrity;

import vott.DataMethods;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class TestDefect {

    private Integer testResultID;
    private Integer defectID;
    private Integer locationID;
    private String notes;
    private Integer prs;
    private Integer prohibitionIssued;

    public Integer getTestResultID(){ return testResultID;}
    public Integer getDefectID(){ return defectID;}
    public Integer getLocationID(){ return locationID;}
    public String getNotes(){ return notes;}
    public Integer getPrs(){ return prs;}
    public Integer getProhibitionIssued(){ return prohibitionIssued;}

    public void setTestDefect(ResultSet rs) throws SQLException {
        this.testResultID = DataMethods.getInteger(rs, "test_result_id");
        this.defectID = DataMethods.getInteger(rs, "defect_id");
        this.locationID = DataMethods.getInteger(rs, "location_id");
        this.notes = Objects.toString(rs.getString("notes"), "");
        this.prs = DataMethods.getInteger(rs, "prs");
        this.prohibitionIssued = DataMethods.getInteger(rs, "prohibitionIssued");
    }

    public String createInsertQuery(){
        return "INSERT INTO test_defect( test_result_id, defect_id, location_id, notes, prs, prohibitionIssued ) " +
                "VALUES ("+testResultID+", "+defectID+", "+locationID+", '"+notes+"', "+prs+", "+prohibitionIssued+" ) " +
                "ON DUPLICATE KEY UPDATE id=LAST_INSERT_ID(id)";
    }
}
