package vott.databaseModels;

import vott.DataMethods;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class AxleSpacing {

    private String technicalRecordID;
    private String axles;
    private Integer value;

    public String getTechnicalRecordID(){ return technicalRecordID;}
    public String getAxles(){ return axles;}
    public Integer getValue(){ return value;}

    public void setAxleSpacing(ResultSet rs) throws SQLException {
        this.technicalRecordID = Objects.toString(rs.getString("technical_record_id"), "");
        this.axles = Objects.toString(rs.getString("axles"), "");
        this.value = DataMethods.getInteger(rs, "value");

    }

    public String createInsertQuery(){
        return "INSERT INTO axle_spacing( technical_record_id, axles, value ) " +
                "VALUES ('"+technicalRecordID+"', '"+axles+"', "+value+") " +
                "ON DUPLICATE KEY UPDATE id=LAST_INSERT_ID(id)";
    }
}



