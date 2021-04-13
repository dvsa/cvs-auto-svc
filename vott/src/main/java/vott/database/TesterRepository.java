package vott.database;

import vott.database.connection.ConnectionFactory;
import vott.models.dao.Tester;
import vott.sqlgeneration.TableDetails;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TesterRepository extends AbstractRepository<Tester> {
    public TesterRepository(ConnectionFactory connectionFactory) { super(connectionFactory); }

    @Override
    protected TableDetails getTableDetails() {

        TableDetails tableDetails = new TableDetails();

        tableDetails.setTableName("tester");
        tableDetails.setColumnNames(new String[] {
                "staffId",
                "name",
                "email_address",
        });

        return tableDetails;
    }

    @Override
    protected void setParameters(PreparedStatement preparedStatement, Tester entity) throws SQLException {
        // 1-indexed
        preparedStatement.setString(1, entity.getStaffID());
        preparedStatement.setString(2, entity.getName());
        preparedStatement.setString(3, entity.getEmailAddress());
    }

    @Override
    protected void setParametersFull(PreparedStatement preparedStatement, Tester entity) throws SQLException {
        setParameters(preparedStatement, entity);

        preparedStatement.setString(4, entity.getStaffID());
        preparedStatement.setString(5, entity.getName());
        preparedStatement.setString(6, entity.getEmailAddress());
    }

    @Override
    protected Tester mapToEntity(ResultSet rs) throws SQLException {
        Tester tester = new Tester();

        tester.setStaffID(rs.getString("staffId"));
        tester.setName(rs.getString("name"));
        tester.setEmailAddress(rs.getString("email_address"));

        return tester;
    }
}
