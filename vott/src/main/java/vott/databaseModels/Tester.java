package vott.databaseModels;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class Tester {

    private String staffID;
    private String name;
    private String emailAddress;


    public String getStaffID(){ return staffID;}
    public String getName(){ return name;}
    public String getEmailAddress(){ return emailAddress;}


    public void setMakeModel(ResultSet rs) throws SQLException {
        this.staffID = Objects.toString(rs.getString("staffId"), "");
        this.name = Objects.toString(rs.getString("name"), "");
        this.emailAddress = Objects.toString(rs.getString("email_address"), "");
    }

    public String createInsertQuery(){
        return "INSERT INTO tester( staffId, name, email_address ) " +
                "VALUES ('"+staffID+"', '"+name+"', '"+emailAddress+"') " +
                "ON DUPLICATE KEY UPDATE id=LAST_INSERT_ID(id)";
    }

}
