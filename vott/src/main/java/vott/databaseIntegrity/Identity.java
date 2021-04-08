package vott.databaseIntegrity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class Identity {

    private String identityID;
    private String name;

    public String getIdentityID(){ return identityID;}
    public String getName(){ return name;}

    public void setIdentity(ResultSet rs) throws SQLException {
        this.identityID = Objects.toString(rs.getString("identityId"), "");
        this.name = Objects.toString(rs.getString("name"), "");
    }

    public String createInsertQuery(){
        return "INSERT INTO identity( identityId, name ) " +
                "VALUES ('"+identityID+"', '"+name+"') " +
                "ON DUPLICATE KEY UPDATE id=LAST_INSERT_ID(id)";
    }

}
