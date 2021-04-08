package vott.databaseIntegrity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class ContactDetails {

    private String name;
    private String address1;
    private String address2;
    private String postTown;
    private String address3;
    private String postCode;
    private String emailAddress;
    private String telephoneNumber;
    private String faxNumber;

    public String getName(){ return name;}
    public String getAddress1(){ return address1;}
    public String getAddress2(){ return address2;}
    public String getPostTown(){ return postTown;}
    public String getAddress3(){ return address3;}
    public String getPostCode(){ return postCode;}
    public String getEmailAddress(){ return emailAddress;}
    public String getTelephoneNumber(){ return telephoneNumber;}
    public String getFaxNumber(){ return faxNumber;}

    public void setContactDetails(ResultSet rs) throws SQLException {
        this.name = Objects.toString(rs.getString("name"), "");
        this.address1 = Objects.toString(rs.getString("address1"), "");
        this.address2 = Objects.toString(rs.getString("address2"), "");
        this.postTown = Objects.toString(rs.getString("postTown"), "");
        this.address3 = Objects.toString(rs.getString("address3"), "");
        this.postCode = Objects.toString(rs.getString("postCode"), "");
        this.emailAddress = Objects.toString(rs.getString("emailAddress"), "");
        this.telephoneNumber = Objects.toString(rs.getString("telephoneNumber"), "");
        this.faxNumber = Objects.toString(rs.getString("faxNumber"), "");

    }

    public String createInsertQuery(){
        return "INSERT INTO contact_details( name, address1, address2, postTown, address3, postCode, emailAddress, telephoneNumber, faxNumber ) " +
                "VALUES ('"+name+"', '"+address1+"', '"+address2+"', '"+postTown+"', '"+address3+"', '"+postCode+"', '"+emailAddress+"', '"+telephoneNumber+"', '"+faxNumber+"') " +
                "ON DUPLICATE KEY UPDATE id=LAST_INSERT_ID(id)";
    }
}
