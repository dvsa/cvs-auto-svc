package vott.database;

import vott.database.connection.ConnectionFactory;
import vott.models.dao.ContactDetails;
import vott.sqlgeneration.TableDetails;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ContactDetailsRepository extends AbstractRepository<ContactDetails>{
    public ContactDetailsRepository(ConnectionFactory connectionFactory) { super(connectionFactory); }

    @Override
    protected TableDetails getTableDetails() {

        TableDetails tableDetails = new TableDetails();

        tableDetails.setTableName("contact_details");
        tableDetails.setColumnNames(new String[] {
                "name",
                "address1",
                "address2",
                "postTown",
                "address3",
                "postCode",
                "emailAddress",
                "telephoneNumber",
                "faxNumber",
        });

        return tableDetails;

    }

    @Override
    protected void setParameters(PreparedStatement preparedStatement, ContactDetails entity) throws SQLException {
        // 1-indexed
        preparedStatement.setString(1, entity.getName());
        preparedStatement.setString(2, entity.getAddress1());
        preparedStatement.setString(3, entity.getAddress2());
        preparedStatement.setString(4, entity.getPostTown());
        preparedStatement.setString(5, entity.getAddress3());
        preparedStatement.setString(6, entity.getPostCode());
        preparedStatement.setString(7, entity.getEmailAddress());
        preparedStatement.setString(8, entity.getTelephoneNumber());
        preparedStatement.setString(9, entity.getFaxNumber());
    }

    @Override
    protected void setParametersFull(PreparedStatement preparedStatement, ContactDetails entity) throws SQLException {
        setParameters(preparedStatement, entity);

        preparedStatement.setString(10, entity.getName());
        preparedStatement.setString(11, entity.getAddress1());
        preparedStatement.setString(12, entity.getAddress2());
        preparedStatement.setString(13, entity.getPostTown());
        preparedStatement.setString(14, entity.getAddress3());
        preparedStatement.setString(15, entity.getPostCode());
        preparedStatement.setString(16, entity.getEmailAddress());
        preparedStatement.setString(17, entity.getTelephoneNumber());
        preparedStatement.setString(18, entity.getFaxNumber());
    }

    @Override
    protected ContactDetails mapToEntity(ResultSet rs) throws SQLException {
        ContactDetails cd = new ContactDetails();

        cd.setName(rs.getString("name"));
        cd.setAddress1(rs.getString("address1"));
        cd.setAddress2(rs.getString("address2"));
        cd.setPostTown(rs.getString("postTown"));
        cd.setAddress3(rs.getString("address3"));
        cd.setPostCode(rs.getString("postCode"));
        cd.setEmailAddress(rs.getString("emailAddress"));
        cd.setTelephoneNumber(rs.getString("telephoneNumber"));
        cd.setFaxNumber(rs.getString("faxNumber"));

        return cd;
    }
}
