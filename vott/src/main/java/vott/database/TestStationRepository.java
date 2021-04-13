package vott.database;

import vott.database.connection.ConnectionFactory;
import vott.models.dao.TestStation;
import vott.sqlgeneration.TableDetails;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestStationRepository extends AbstractRepository<TestStation> {
    public TestStationRepository(ConnectionFactory connectionFactory) { super(connectionFactory); }

    @Override
    protected TableDetails getTableDetails() {
        TableDetails tableDetails = new TableDetails();
        tableDetails.setTableName("test_station");
        tableDetails.setColumnNames(new String[] {
                "pNumber",
                "name",
                "type",
        });
        return tableDetails;
    }

    @Override
    protected void setParameters(PreparedStatement preparedStatement, TestStation entity) throws SQLException {
        // 1-indexed
        preparedStatement.setString(1, entity.getPNumber());
        preparedStatement.setString(2, entity.getName());
        preparedStatement.setString(3, entity.getType());
    }

    @Override
    protected void setParametersFull(PreparedStatement preparedStatement, TestStation entity) throws SQLException {
        setParameters(preparedStatement, entity);

        preparedStatement.setString(4, entity.getPNumber());
        preparedStatement.setString(5, entity.getName());
        preparedStatement.setString(6, entity.getType());
    }

    @Override
    protected TestStation mapToEntity(ResultSet rs) throws SQLException {
        TestStation ts = new TestStation();

        ts.setPNumber(rs.getString("pNumber"));
        ts.setName(rs.getString("name"));
        ts.setType(rs.getString("type"));

        return ts;
    }
}
