package vott.database;

import vott.databaseModels.Preparer;
import vott.databaseModels.TestType;
import vott.sqlgeneration.TableDetails;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PreparerRepository extends AbstractRepository<Preparer> {
    public PreparerRepository(ConnectionFactory connectionFactory) { super(connectionFactory); }

    @Override
    protected TableDetails getTableDetails() {

        TableDetails tableDetails = new TableDetails();

        tableDetails.setTableName("preparer");
        tableDetails.setColumnNames(new String[] {
                "preparerId",
                "name",
        });

        return tableDetails;
    }

    @Override
    protected void setParameters(PreparedStatement preparedStatement, Preparer entity) throws SQLException {
        // 1-indexed
        preparedStatement.setString(1, entity.getPreparerID());
        preparedStatement.setString(2, entity.getName());
    }

    @Override
    protected Preparer mapToEntity(ResultSet rs) throws SQLException {
        Preparer preparer = new Preparer();

        preparer.setPreparerID(rs.getString("preparerId"));
        preparer.setName(rs.getString("name"));

        return preparer;
    }
}
