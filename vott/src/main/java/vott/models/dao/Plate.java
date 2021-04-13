package vott.models.dao;

import lombok.Data;

@Data
public class Plate {

    private String technicalRecordID;
    private String plateSerialNumber;
    private String plateIssueDate;
    private String plateReasonForIssue;
    private String plateIssuer;

}
