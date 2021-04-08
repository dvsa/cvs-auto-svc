package vott.databaseIntegrity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class Preparer {

    private String preparerID;
    private String name;


    public String getPreparerID(){ return preparerID;}
    public String getName(){ return name;}


    public void setPreparer(ResultSet rs) throws SQLException {
        this.preparerID = Objects.toString(rs.getString("preparerId"), "");
        this.name = Objects.toString(rs.getString("name"), "");
    }

    public String createInsertQuery(){
        return "INSERT INTO preparer( preparerId, name ) " +
                "VALUES ('"+preparerID+"', '"+name+"' ) " +
                "ON DUPLICATE KEY UPDATE id=LAST_INSERT_ID(id)";
    }

}
