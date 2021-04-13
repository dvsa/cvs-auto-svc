package vott.database;

import vott.database.connection.ConnectionFactory;
import vott.models.dao.AxleSpacing;
import vott.sqlgeneration.TableDetails;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AxleSpacingRepository extends AbstractRepository<AxleSpacing> {
    public AxleSpacingRepository(ConnectionFactory connectionFactory) { super(connectionFactory); }

    @Override
    protected TableDetails getTableDetails() {

        TableDetails tableDetails = new TableDetails();

        tableDetails.setTableName("axle_spacing");
        tableDetails.setColumnNames(new String[] {
                "technical_record_id",
                "axles",
                "value",
        });

        return tableDetails;
    }

    @Override
    protected void setParameters(PreparedStatement preparedStatement, AxleSpacing entity) throws SQLException {
        // 1-indexed
        preparedStatement.setString(1, entity.getTechnicalRecordID());
        preparedStatement.setString(2, entity.getAxles());
        preparedStatement.setString(3, entity.getValue());
    }

    @Override
    protected void setParametersFull(PreparedStatement preparedStatement, AxleSpacing entity) throws SQLException {
        setParameters(preparedStatement, entity);

        preparedStatement.setString(4, entity.getTechnicalRecordID());
        preparedStatement.setString(5, entity.getAxles());
        preparedStatement.setString(6, entity.getValue());
    }

    @Override
    protected AxleSpacing mapToEntity(ResultSet rs) throws SQLException {
        AxleSpacing as = new AxleSpacing();

        as.setTechnicalRecordID(rs.getString("system_number"));
        as.setAxles(rs.getString("vin"));
        as.setValue(rs.getString("vrm_trm"));

        return as;
    }
}
