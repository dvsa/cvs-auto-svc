package vott.databaseModels;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class Plate {

    private String technicalRecordID;
    private String plateSerialNumber;
    private String plateIssueDate;
    private String plateReasonForIssue;
    private String plateIssuer;

    public String getTechnicalRecordID(){ return technicalRecordID;}
    public String getPlateSerialNumber(){ return plateSerialNumber;}
    public String getPlateIssueDate(){ return plateIssueDate;}
    public String getPlateReasonForIssue(){ return plateReasonForIssue;}
    public String getPlateIssuer(){ return plateIssuer;}

    public void setPlate(ResultSet rs) throws SQLException {
        this.technicalRecordID = Objects.toString(rs.getString("technical_record_id"), "");
        this.plateSerialNumber = Objects.toString(rs.getString("plateSerialNumber"), "");
        this.plateIssueDate = Objects.toString(rs.getString("plateIssueDate"), "");
        this.plateReasonForIssue = Objects.toString(rs.getString("plateReasonForIssue"), "");
        this.plateIssuer = Objects.toString(rs.getString("plateIssuer"), "");
    }

    public String createInsertQuery(){
        return "INSERT INTO plate( technical_record_id, plateSerialNumber, plateIssueDate, plateReasonForIssue, plateIssuer) " +
                "VALUES ('"+technicalRecordID+"', '"+plateSerialNumber+"', '"+plateIssueDate+"', '"+plateReasonForIssue+"', '"+plateIssuer+"') " +
                "ON DUPLICATE KEY UPDATE id=LAST_INSERT_ID(id)";
    }
}
