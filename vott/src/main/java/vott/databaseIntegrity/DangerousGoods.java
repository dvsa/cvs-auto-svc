package vott.databaseIntegrity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class DangerousGoods {

    private String name;

    public String getName(){ return name;}

    public void setDangerousGoods(ResultSet rs) throws SQLException {
        this.name = Objects.toString(rs.getString("name"), "");
    }

    public String createInsertQuery(){
        return "INSERT INTO dangerous_goods( name ) " +
                "VALUES ('"+name+"') " +
                "ON DUPLICATE KEY UPDATE id=LAST_INSERT_ID(id)";
    }

}