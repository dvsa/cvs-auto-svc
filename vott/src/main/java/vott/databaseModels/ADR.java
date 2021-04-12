package vott.databaseModels;

import lombok.Data;
import vott.DataMethods;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

@Data
public class ADR {

//    private Integer technicalRecordID;
//    private String type;
//    private String approvalDate;
//    private Integer listStatementApplicable;
//    private String batteryListNumber;
//    private Integer declarationsSeen;
//    private Integer brakeDeclarationsSeen;
//    private Integer brakeDeclarationIssuer;
//    private Integer brakeEndurance;
//    private String weight;
//    private Integer compatibilityGroupJ;
//    private String additionalExaminerNotes;
//    private String applicantDetailsName;
//    private String street;
//    private String town;
//    private String city;
//    private String postcode;
//    private String memosApply;
//    private String adrTypeApprovalNo;
//    private String adrCertificateNotes;
//    private String tankManufacturer;
//    private String yearOfManufacture;
//    private String tankCode;
//    private String specialProvisions;
//    private String tankManufacturerSerialNo;
//    private String tankTypeAppNo;
//    private String tc2Type;
//    private String tc2IntermediateApprovalNo;
//    private String tc2IntermediateExpiryDate;
//    private String substancesPermitted;
//    private String statement;
//    private String productListRefNo;
//    private String productList;

    private String technicalRecordID;
    private String type;
    private String approvalDate;
    private String listStatementApplicable;
    private String batteryListNumber;
    private String declarationsSeen;
    private String brakeDeclarationsSeen;
    private String brakeDeclarationIssuer;
    private String brakeEndurance;
    private String weight;
    private String compatibilityGroupJ;
    private String additionalExaminerNotes;
    private String applicantDetailsName;
    private String street;
    private String town;
    private String city;
    private String postcode;
    private String memosApply;
    private String adrTypeApprovalNo;
    private String adrCertificateNotes;
    private String tankManufacturer;
    private String yearOfManufacture;
    private String tankCode;
    private String specialProvisions;
    private String tankManufacturerSerialNo;
    private String tankTypeAppNo;
    private String tc2Type;
    private String tc2IntermediateApprovalNo;
    private String tc2IntermediateExpiryDate;
    private String substancesPermitted;
    private String statement;
    private String productListRefNo;
    private String productList;

    public void setADR(ResultSet rs) throws SQLException {
//        this.technicalRecordID = DataMethods.getInteger(rs, "technical_record_id");
//        this.type = Objects.toString(rs.getString("type"), "");
//        this.approvalDate = Objects.toString(rs.getString("approvalDate"), "");
//        this.listStatementApplicable = DataMethods.getInteger(rs, "listStatementApplicable");
//        this.batteryListNumber = Objects.toString(rs.getString("batteryListNumber"), "");
//        this.declarationsSeen = DataMethods.getInteger(rs, "declarationsSeen");
//        this.brakeDeclarationsSeen = DataMethods.getInteger(rs, "brakeDeclarationsSeen");
//        this.brakeDeclarationIssuer = DataMethods.getInteger(rs, "brakeDeclarationIssuer");
//        this.brakeEndurance = DataMethods.getInteger(rs, "brakeEndurance");
//        this.weight = Objects.toString(rs.getString("weight"), "");
//        this.compatibilityGroupJ = DataMethods.getInteger(rs, "compatibilityGroupJ");
//        this.additionalExaminerNotes = Objects.toString(rs.getString("additionalExaminerNotes"), "");
//        this.applicantDetailsName = Objects.toString(rs.getString("applicantDetailsName"), "");
//        this.street = Objects.toString(rs.getString("street"), "");
//        this.town = Objects.toString(rs.getString("town"), "");
//        this.city = Objects.toString(rs.getString("city"), "");
//        this.postcode = Objects.toString(rs.getString("postcode"), "");
//        this.memosApply = Objects.toString(rs.getString("memosApply"), "");
//        this.adrTypeApprovalNo = Objects.toString(rs.getString("adrTypeApprovalNo"), "");
//        this.adrCertificateNotes = Objects.toString(rs.getString("adrCertificateNotes"), "");
//        this.tankManufacturer = Objects.toString(rs.getString("tankManufacturer"), "");
//        this.yearOfManufacture = Objects.toString(rs.getString("yearOfManufacture"), "");
//        this.tankCode = Objects.toString(rs.getString("tankCode"), "");
//        this.specialProvisions = Objects.toString(rs.getString("specialProvisions"), "");
//        this.tankManufacturerSerialNo = Objects.toString(rs.getString("tankManufacturerSerialNo"), "");
//        this.tankTypeAppNo = Objects.toString(rs.getString("tankTypeAppNo"), "");
//        this.tc2Type = Objects.toString(rs.getString("tc2Type"), "");
//        this.tc2IntermediateApprovalNo = Objects.toString(rs.getString("tc2IntermediateApprovalNo"), "");
//        this.tc2IntermediateExpiryDate = Objects.toString(rs.getString("tc2IntermediateExpiryDate"), "");
//        this.substancesPermitted = Objects.toString(rs.getString("substancesPermitted"), "");
//        this.statement = Objects.toString(rs.getString("statement"), "");
//        this.productListRefNo = Objects.toString(rs.getString("productListRefNo"), "");
//        this.productList = Objects.toString(rs.getString("productList"), "");
    }

    public String createInsertQuery(){
        return "INSERT INTO adr (`technical_record_id`,`type`,`approvalDate`,`listStatementApplicable`,`batteryListNumber`,`declarationsSeen`,`brakeDeclarationsSeen`,`brakeDeclarationIssuer`,`brakeEndurance`,`weight`,`compatibilityGroupJ`,`additionalExaminerNotes`,`applicantDetailsName`,`street`,`town`,`city`,`postcode`,`memosApply`,`adrTypeApprovalNo`,`adrCertificateNotes`,`tankManufacturer`,`yearOfManufacture`,`tankCode`,`specialProvisions`,`tankManufacturerSerialNo`,`tankTypeAppNo`,`tc2Type`,`tc2IntermediateApprovalNo`,`tc2IntermediateExpiryDate`,`substancesPermitted`,`statement`,`productListRefNo`,`productList`) " +
                "VALUES ("+technicalRecordID+", '"+type+"', '"+approvalDate+"', "+listStatementApplicable+", '"+batteryListNumber+"', "+declarationsSeen+", "+brakeDeclarationsSeen+", "+brakeDeclarationIssuer+", "+brakeEndurance+", '"+weight+"', "+compatibilityGroupJ+", '"+additionalExaminerNotes+"', '"+applicantDetailsName+"', '"+street+"', '"+town+"', '"+city+"', '"+postcode+"', '"+memosApply+"', '"+adrTypeApprovalNo+"', '"+adrCertificateNotes+"', '"+tankManufacturer+"', '"+yearOfManufacture+"', '"+tankCode+"', '"+specialProvisions+"', '"+tankManufacturerSerialNo+"', '"+tankTypeAppNo+"', '"+tc2Type+"', '"+tc2IntermediateApprovalNo+"', '"+tc2IntermediateExpiryDate+"', '"+substancesPermitted+"', '"+statement+"', '"+productListRefNo+"', '"+productList+"') " +
                "ON DUPLICATE KEY UPDATE id=LAST_INSERT_ID(id)";
    }
}
