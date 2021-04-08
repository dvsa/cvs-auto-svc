package vott.databaseIntegrity;

import vott.DataMethods;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class ADR {

    private Integer technicalRecordID;
    private String type;
    private String approvalDate;
    private String listStatementApplicable;
    private String batteryListNumber;
    private Integer declarationsSeen;
    private Integer brakeDeclarationsSeen;
    private Integer brakeDeclarationIssuer;
    private Integer brakeEndurance;
    private String weight;
    private Integer compatibilityGroupJ;
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

    public Integer getTechnicalRecordID(){ return technicalRecordID; };
    public String getType(){ return type; };
    public String getApprovalDate(){ return approvalDate; };
    public String getListStatementApplicable(){ return listStatementApplicable; };
    public String getBatteryListNumber(){ return batteryListNumber; };
    public Integer getDeclarationsSeen(){ return declarationsSeen; };
    public Integer getBrakeDeclarationsSeen(){ return brakeDeclarationsSeen; };
    public Integer getBrakeDeclarationIssuer(){ return brakeDeclarationIssuer; };
    public Integer getBrakeEndurance(){ return brakeEndurance; };
    public String getWeight(){ return weight; };
    public Integer getCompatibilityGroupJ(){ return compatibilityGroupJ; };
    public String getAdditionalExaminerNotes(){ return additionalExaminerNotes; };
    public String getApplicantDetailsName(){ return applicantDetailsName; };
    public String getStreet(){ return street; };
    public String getTown(){ return town; };
    public String getCity(){ return city; };
    public String getPostcode(){ return postcode; };
    public String getMemosApply(){ return memosApply; };
    public String getADRTypeApprovalNo(){ return adrTypeApprovalNo; };
    public String getADRCertificateNotes(){ return adrCertificateNotes; };
    public String getTankManufacturer(){ return tankManufacturer; };
    public String getYearOfManufacture(){ return yearOfManufacture;};
    public String getTankCode(){ return tankCode; };
    public String getSpecialProvisions(){ return specialProvisions; };
    public String getTankManufacturerSerialNo(){ return tankManufacturerSerialNo; };
    public String getTankTypeAppNo(){ return tankTypeAppNo; };
    public String getTC2Type(){ return tc2Type; };
    public String getTC2IntermediateApprovalNo(){ return tc2IntermediateApprovalNo; };
    public String getTC2IntermediateExpiryDate(){ return tc2IntermediateExpiryDate; };
    public String getSubstancesPermitted(){ return substancesPermitted; };
    public String getStatement(){ return statement; };
    public String getProductListRefNo(){ return productListRefNo; };
    public String getProductList(){ return productList; };

    public void setADR(ResultSet rs) throws SQLException {
        this.technicalRecordID = DataMethods.getInteger(rs, "technical_record_id");
        this.type = Objects.toString(rs.getString("type"), "");
        this.approvalDate = Objects.toString(rs.getString("approvalDate"), "");
        this.listStatementApplicable = Objects.toString(rs.getString("listStatementApplicable"), "");
        this.batteryListNumber = Objects.toString(rs.getString("batteryListNumber"), "");
        this.declarationsSeen = DataMethods.getInteger(rs, "declarationsSeen");
        this.brakeDeclarationsSeen = DataMethods.getInteger(rs, "brakeDeclarationsSeen");
        this.brakeDeclarationIssuer = DataMethods.getInteger(rs, "brakeDeclarationIssuer");
        this.brakeEndurance = DataMethods.getInteger(rs, "brakeEndurance");
        this.weight = Objects.toString(rs.getString("weight"), "");
        this.compatibilityGroupJ = DataMethods.getInteger(rs, "compatibilityGroupJ");
        this.additionalExaminerNotes = Objects.toString(rs.getString("additionalExaminerNotes"), "");
        this.applicantDetailsName = Objects.toString(rs.getString("applicantDetailsName"), "");
        this.street = Objects.toString(rs.getString("street"), "");
        this.town = Objects.toString(rs.getString("town"), "");
        this.city = Objects.toString(rs.getString("city"), "");
        this.postcode = Objects.toString(rs.getString("postcode"), "");
        this.memosApply = Objects.toString(rs.getString("memosApply"), "");
        this.adrTypeApprovalNo = Objects.toString(rs.getString("adrTypeApprovalNo"), "");
        this.adrCertificateNotes = Objects.toString(rs.getString("adrCertificateNotes"), "");
        this.tankManufacturer = Objects.toString(rs.getString("tankManufacturer"), "");
        this.yearOfManufacture = Objects.toString(rs.getString("yearOfManufacture"), "");
        this.tankCode = Objects.toString(rs.getString("tankCode"), "");
        this.specialProvisions = Objects.toString(rs.getString("specialProvisions"), "");
        this.tankManufacturerSerialNo = Objects.toString(rs.getString("tankManufacturerSerialNo"), "");
        this.tankTypeAppNo = Objects.toString(rs.getString("tankTypeAppNo"), "");
        this.tc2Type = Objects.toString(rs.getString("tc2Type"), "");
        this.tc2IntermediateApprovalNo = Objects.toString(rs.getString("tc2IntermediateApprovalNo"), "");
        this.tc2IntermediateExpiryDate = Objects.toString(rs.getString("tc2IntermediateExpiryDate"), "");
        this.substancesPermitted = Objects.toString(rs.getString("substancesPermitted"), "");
        this.statement = Objects.toString(rs.getString("statement"), "");
        this.productListRefNo = Objects.toString(rs.getString("productListRefNo"), "");
        this.productList = Objects.toString(rs.getString("productList"), "");

    }

    public String createInsertQuery(){
        return "INSERT INTO adr (`technical_record_id`,`type`,`approvalDate`,`listStatementApplicable`,`batteryListNumber`,`declarationsSeen`,`brakeDeclarationsSeen`,`brakeDeclarationIssuer`,`brakeEndurance`,`weight`,`compatibilityGroupJ`,`additionalExaminerNotes`,`applicantDetailsName`,`street`,`town`,`city`,`postcode`,`memosApply`,`adrTypeApprovalNo`,`adrCertificateNotes`,`tankManufacturer`,`yearOfManufacture`,`tankCode`,`specialProvisions`,`tankManufacturerSerialNo`,`tankTypeAppNo`,`tc2Type`,`tc2IntermediateApprovalNo`,`tc2IntermediateExpiryDate`,`substancesPermitted`,`statement`,`productListRefNo`,`productList`) " +
                "VALUES ("+technicalRecordID+", '"+type+"', '"+approvalDate+"', '"+listStatementApplicable+"', '"+batteryListNumber+"', "+declarationsSeen+", "+brakeDeclarationsSeen+", "+brakeDeclarationIssuer+", "+brakeEndurance+", '"+weight+"', "+compatibilityGroupJ+", '"+additionalExaminerNotes+"', '"+applicantDetailsName+"', '"+street+"', '"+town+"', '"+city+"', '"+postcode+"', '"+memosApply+"', '"+adrTypeApprovalNo+"', '"+adrCertificateNotes+"', '"+tankManufacturer+"', '"+yearOfManufacture+"', '"+tankCode+"', '"+specialProvisions+"', '"+tankManufacturerSerialNo+"', '"+tankTypeAppNo+"', '"+tc2Type+"', '"+tc2IntermediateApprovalNo+"', '"+tc2IntermediateExpiryDate+"', '"+substancesPermitted+"', '"+statement+"', '"+productListRefNo+"', '"+productList+"') " +
                "ON DUPLICATE KEY UPDATE id=LAST_INSERT_ID(id)";
    }
}
