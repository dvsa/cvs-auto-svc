package vott.databaseModels;

import lombok.Data;
import vott.DataMethods;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

@Data
public class AxleSpacing {

    private String technicalRecordID;
    private String axles;
//    private Integer value;
    private String value;

//    public void setAxleSpacing(ResultSet rs) throws SQLException {
//        this.technicalRecordID = Objects.toString(rs.getString("technical_record_id"), "");
//        this.axles = Objects.toString(rs.getString("axles"), "");
//        this.value = DataMethods.getInteger(rs, "value");
//
//    }

    public String createInsertQuery(){
        return "INSERT INTO axle_spacing( technical_record_id, axles, value ) " +
                "VALUES ('"+technicalRecordID+"', '"+axles+"', "+value+") " +
                "ON DUPLICATE KEY UPDATE id=LAST_INSERT_ID(id)";
    }
}



