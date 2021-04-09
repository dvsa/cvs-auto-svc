package vott.databaseIntegrity;

import org.junit.Before;
import org.junit.Test;
import vott.DataMethods;
import vott.DatabaseConnection;
import vott.databaseModels.Plate;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;


public class PlateTests {

    private DatabaseConnection db =  new DatabaseConnection();

    //Setup Test Container
    @Before
    public void before() throws SQLException {
        db.AWSConnection();
    }

    @Test
    public void PlateInsertExistingDataTest() throws SQLException {

        Plate plate = new Plate();

        String allDataQuery = "SELECT * FROM plate";
        ResultSet startingRS = db.dbQuery(allDataQuery);
        int startingLength = DataMethods.getResultSetLength(startingRS);

        //Capture data from first row of results
        startingRS.first();
        plate.setPlate(startingRS);

        String insertQuery = plate.createInsertQuery();
        int update = db.dbUpdate(insertQuery);

        ResultSet endRS = db.dbQuery(allDataQuery);
        int endLength = DataMethods.getResultSetLength(endRS);

        assertThat(startingLength, equalTo(endLength));
        assertThat(update, equalTo(1));

    }

    @Test
    public void PlateInsertNewDataTest() throws SQLException {

        String query = "SELECT * FROM plate";

        ResultSet startingRS = db.dbQuery(query);
        int startingLength = DataMethods.getResultSetLength(startingRS);
        startingRS.first();

        String insertQuery = "INSERT INTO plate( technical_record_id, plateSerialNumber, plateIssueDate, plateReasonForIssue, plateIssuer) " +
                "VALUES ('1', '123456', '2100-12-31', 'Test Data', 'Tester') " +
                "ON DUPLICATE KEY UPDATE id=LAST_INSERT_ID(id)";

        int update = db.dbUpdate(insertQuery);
        ResultSet endRS = db.dbQuery(query);
        int endLength = DataMethods.getResultSetLength(endRS);

        assertThat(startingLength+1, equalTo(endLength));
        assertThat(update, equalTo(1));

        //data Clean Up
        if (update == 1){
            db.deleteLastEntry("plate");
        }

    }
}