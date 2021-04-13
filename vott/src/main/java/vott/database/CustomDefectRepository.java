package vott.database;

import vott.database.connection.ConnectionFactory;
import vott.models.dao.CustomDefect;
import vott.sqlgeneration.TableDetails;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomDefectRepository extends AbstractRepository<CustomDefect> {
    public CustomDefectRepository(ConnectionFactory connectionFactory) { super(connectionFactory); }

    @Override
    protected TableDetails getTableDetails() {

        TableDetails tableDetails = new TableDetails();

        tableDetails.setTableName("custom_defect");
        tableDetails.setColumnNames(new String[] {
                "test_result_id",
                "referenceNumber",
                "defectName",
                "defectNotes",
        });

        return tableDetails;
    }

    @Override
    protected void setParameters(PreparedStatement preparedStatement, CustomDefect entity) throws SQLException {
        // 1-indexed
        preparedStatement.setString(1, entity.getTestResultID());
        preparedStatement.setString(2, entity.getReferenceNumber());
        preparedStatement.setString(3, entity.getDefectName());
        preparedStatement.setString(4, entity.getDefectNotes());
    }

    @Override
    protected void setParametersFull(PreparedStatement preparedStatement, CustomDefect entity) throws SQLException {
        setParameters(preparedStatement, entity);

        preparedStatement.setString(5, entity.getTestResultID());
        preparedStatement.setString(6, entity.getReferenceNumber());
        preparedStatement.setString(7, entity.getDefectName());
        preparedStatement.setString(8, entity.getDefectNotes());
    }

    @Override
    protected CustomDefect mapToEntity(ResultSet rs) throws SQLException {
        CustomDefect cd = new CustomDefect();

        cd.setTestResultID(rs.getString("test_result_id"));
        cd.setReferenceNumber(rs.getString("referenceNumber"));
        cd.setDefectName(rs.getString("defectName"));
        cd.setDefectNotes(rs.getString("defectNotes"));

        return cd;
    }
}
