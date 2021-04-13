package vott.database;

import vott.database.connection.ConnectionFactory;
import vott.models.dao.Microfilm;
import vott.sqlgeneration.TableDetails;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MicrofilmRepository extends AbstractRepository<Microfilm> {
    public MicrofilmRepository(ConnectionFactory connectionFactory) { super(connectionFactory); }

    @Override
    protected TableDetails getTableDetails() {

        TableDetails tableDetails = new TableDetails();

        tableDetails.setTableName("microfilm");
        tableDetails.setColumnNames(new String[] {
                "technical_record_id",
                "microfilmDocumentType",
                "microfilmRollNumber",
                "microfilmSerialNumber",
        });

        return tableDetails;
    }

    @Override
    protected void setParameters(PreparedStatement preparedStatement, Microfilm entity) throws SQLException {
        preparedStatement.setString(1, entity.getTechnicalRecordID());
        preparedStatement.setString(2, entity.getMicrofilmDocumentType());
        preparedStatement.setString(3, entity.getMicrofilmRollNumber());
        preparedStatement.setString(4, entity.getMicrofilmSerialNumber());
    }

    @Override
    protected void setParametersFull(PreparedStatement preparedStatement, Microfilm entity) throws SQLException {
        setParameters(preparedStatement, entity);

        preparedStatement.setString(5, entity.getTechnicalRecordID());
        preparedStatement.setString(6, entity.getMicrofilmDocumentType());
        preparedStatement.setString(7, entity.getMicrofilmRollNumber());
        preparedStatement.setString(8, entity.getMicrofilmSerialNumber());
    }

    @Override
    protected Microfilm mapToEntity(ResultSet rs) throws SQLException {
        Microfilm mf = new Microfilm();

        mf.setTechnicalRecordID(rs.getString("technical_record_id"));
        mf.setMicrofilmDocumentType(rs.getString("microfilmDocumentType"));
        mf.setMicrofilmRollNumber(rs.getString("microfilmRollNumber"));
        mf.setMicrofilmSerialNumber(rs.getString("microfilmSerialNumber"));

        return mf;
    }
}
