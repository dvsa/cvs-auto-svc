package vott.databaseIntegrity;

import org.junit.Before;
import org.junit.Test;
import vott.DataMethods;
import vott.DatabaseConnection;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;


public class ContactDetailsTests {

    private DatabaseConnection db =  new DatabaseConnection();

    //Setup Test Container
    @Before
    public void before() throws SQLException {
        db.AWSConnection();
    }

    @Test
    public void ContactDetailsInsertExistingDataTest() throws SQLException {

        ContactDetails cd = new ContactDetails();

        String query = "SELECT * FROM contact_details";
        ResultSet startingRS = db.dbQuery(query);
        int startingLength = DataMethods.getResultSetLength(startingRS);

        startingRS.first();
        cd.setContactDetails(startingRS);

        String insertQuery = cd.createInsertQuery();
        int update = db.dbUpdate(insertQuery);

        ResultSet endRS = db.dbQuery(query);
        int endLength = DataMethods.getResultSetLength(endRS);

        assertThat(startingLength, equalTo(endLength));
        assertThat(update, equalTo(1));

    }

    @Test
    public void ContactDetailsInsertNewDataTest() throws SQLException {

        String query = "SELECT * FROM contact_details";

        ResultSet startingRS = db.dbQuery(query);
        int startingLength = DataMethods.getResultSetLength(startingRS);
        startingRS.first();

        String fingerprintQuery = "INSERT INTO contact_details( name, address1, address2, postTown, address3, postCode, emailAddress, telephoneNumber, faxNumber ) " +
                                    "VALUES ('test name', '123 fake street', '', '', '', '', 'abc@test.com', '0123456', '') " +
                                    "ON DUPLICATE KEY UPDATE id=LAST_INSERT_ID(id)";
        int update = db.dbUpdate(fingerprintQuery);
        ResultSet endRS = db.dbQuery(query);
        int endLength = DataMethods.getResultSetLength(endRS);

        assertThat(startingLength+1, equalTo(endLength));
        assertThat(update, equalTo(1));

        //data Clean Up
        if (update == 1){
            db.deleteLastEntry("contact_details");
        }

    }
}