package vott.databaseModels;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class TestType {

    private String testTypeClassification;
    private String testTypeName;

    public String getTestTypeClassification(){ return testTypeClassification;}
    public String getTestTypeName(){ return testTypeName;}

    public void setTestType(ResultSet rs) throws SQLException {
        this.testTypeClassification = Objects.toString(rs.getString("testTypeClassification"), "");
        this.testTypeName = Objects.toString(rs.getString("testTypeName"), "");
    }

    public String createInsertQuery(){
        return "INSERT INTO test_type( testTypeClassification, testTypeName ) " +
                "VALUES ('"+testTypeClassification+"', '"+testTypeName+"') " +
                "ON DUPLICATE KEY UPDATE id=LAST_INSERT_ID(id)";
    }
}
