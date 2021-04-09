package vott.databaseModels;

import lombok.Data;
import vott.DataMethods;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

@Data
public class Location {

    private String vertical;
    private String horizontal;
    private String lateral;
    private String longitudinal;
    private Integer rowNumber;
    private Integer seatNumber;
    private Integer axleNumber;

    public void setLocation(ResultSet rs) throws SQLException {
        this.vertical = Objects.toString(rs.getString("vertical"), "");
        this.horizontal = Objects.toString(rs.getString("horizontal"), "");
        this.lateral = Objects.toString(rs.getString("lateral"), "");
        this.longitudinal = Objects.toString(rs.getString("longitudinal"), "");
        this.rowNumber = DataMethods.getInteger(rs, "rowNumber");
        this.seatNumber = DataMethods.getInteger(rs, "seatNumber");
        this.axleNumber = DataMethods.getInteger(rs, "axleNumber");
    }

    public String createInsertQuery(){
        return "INSERT INTO location( vertical, horizontal, lateral, longitudinal, rowNumber, seatNumber, axleNumber ) " +
                "VALUES ('"+vertical+"', '"+horizontal+"', '"+lateral+"', '"+longitudinal+"', "+rowNumber+", "+seatNumber+", "+axleNumber+") " +
                "ON DUPLICATE KEY UPDATE id=LAST_INSERT_ID(id)";
    }
}
