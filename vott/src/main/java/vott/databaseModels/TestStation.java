package vott.databaseModels;

import lombok.Data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

@Data
public class TestStation {

    private String pNumber;
    private String name;
    private String type;

    public void setTestStation(ResultSet rs) throws SQLException {
        this.pNumber = Objects.toString(rs.getString("pNumber"), "");
        this.name = Objects.toString(rs.getString("name"), "");
        this.type = Objects.toString(rs.getString("type"), "");
    }

    public String createInsertQuery(){
        return "INSERT INTO test_station( pNumber, name, type ) " +
                "VALUES ('"+pNumber+"', '"+name+"', '"+type+"') " +
                "ON DUPLICATE KEY UPDATE id=LAST_INSERT_ID(id)";
    }
}
