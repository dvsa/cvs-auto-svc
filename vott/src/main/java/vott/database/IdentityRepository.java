package vott.database;

import vott.database.connection.ConnectionFactory;
import vott.models.dao.Identity;
import vott.sqlgeneration.TableDetails;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class IdentityRepository extends AbstractRepository<Identity>{


    public IdentityRepository(ConnectionFactory connectionFactory) { super(connectionFactory); }

    @Override
    protected TableDetails getTableDetails() {

        TableDetails tableDetails = new TableDetails();

        tableDetails.setTableName("identity");
        tableDetails.setColumnNames(new String[] {
                "identityId",
                "name",
        });

        return tableDetails;

    }

    @Override
    protected void setParameters(PreparedStatement preparedStatement, Identity entity) throws SQLException {
        preparedStatement.setString(1, entity.getIdentityID());
        preparedStatement.setString(2, entity.getName());
    }

    @Override
    protected void setParametersFull(PreparedStatement preparedStatement, Identity entity) throws SQLException {
        setParameters(preparedStatement, entity);

        preparedStatement.setString(3, entity.getIdentityID());
        preparedStatement.setString(4, entity.getName());
    }


    @Override
    protected Identity mapToEntity(ResultSet rs) throws SQLException {

        Identity identity = new Identity();

        identity.setIdentityID(rs.getString("system_number"));
        identity.setName(rs.getString("vin"));

        return identity;
    }
}
