package vott.database;

import vott.databaseModels.TestType;
import vott.databaseModels.Vehicle;
import vott.sqlgeneration.TableDetails;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestTypeRepository extends AbstractRepository<TestType> {
    public TestTypeRepository(ConnectionFactory connectionFactory) { super(connectionFactory); }

    @Override
    protected TableDetails getTableDetails() {

        TableDetails tableDetails = new TableDetails();

        tableDetails.setTableName("test_type");
        tableDetails.setColumnNames(new String[] {
                "testTypeClassification",
                "testTypeName",
        });

        return tableDetails;
    }

    @Override
    protected void setParameters(PreparedStatement preparedStatement, TestType entity) throws SQLException {
        // 1-indexed
        preparedStatement.setString(1, entity.getTestTypeClassification());
        preparedStatement.setString(2, entity.getTestTypeName());
    }

    @Override
    protected void setParametersFull(PreparedStatement preparedStatement, TestType entity) throws SQLException {

    }

    @Override
    protected TestType mapToEntity(ResultSet rs) throws SQLException {
        TestType tt = new TestType();

        tt.setTestTypeClassification(rs.getString("testTypeClassification"));
        tt.setTestTypeName(rs.getString("testTypeName"));

        return tt;
    }
}
