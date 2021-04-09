package vott.databaseModels;

import lombok.Data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

@Data
public class Plate {

    private String technicalRecordID;
    private String plateSerialNumber;
    private String plateIssueDate;
    private String plateReasonForIssue;
    private String plateIssuer;

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
