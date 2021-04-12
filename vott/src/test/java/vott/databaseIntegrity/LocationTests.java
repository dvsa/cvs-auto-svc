package vott.databaseIntegrity;

import org.junit.Before;
import org.junit.Test;
import vott.DataMethods;
import vott.DatabaseConnection;
import vott.databaseModels.Location;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;


public class LocationTests {

    private DatabaseConnection db =  new DatabaseConnection();

    //Setup Test Container
    @Before
    public void before() throws SQLException {
        db.AWSConnection();
    }

    @Test
    public void LocationInsertExistingDataTest() throws SQLException {

        Location location = new Location();

        ResultSet startingRS = db.startingResultSet("location");
        int startingRowCount = DataMethods.getResultSetLength(startingRS);

        //Capture data from first row of results
        startingRS.first();
        ResultSetMetaData rsmd = startingRS.getMetaData();
//        location.setLocation(startingRS);

        //create insert query using first row from the DB
        String insertQuery = location.createInsertQuery();
        int update = db.dbUpdate(insertQuery);

        ResultSet endRS = db.endResultSet("location");
        int endRowCount = DataMethods.getResultSetLength(endRS);

        startingRS.first();
        endRS.first();
        int colCount = rsmd.getColumnCount();
        for (int i = 1; i <= colCount; i++){
            assertThat(startingRS.getString(i), equalTo(endRS.getString(i)));
        }

        startingRS.last();
        endRS.last();
        for (int i = 1; i <= colCount; i++){
            assertThat(startingRS.getString(i), equalTo(endRS.getString(i)));
        }

        assertThat(startingRowCount, equalTo(endRowCount));
        assertThat(update, equalTo(1));

    }

    @Test
    public void LocationInsertNewDataTest() throws SQLException {

        String query = "SELECT * FROM location";

        ResultSet startingRS = db.dbQuery(query);
        int startingRowCount = DataMethods.getResultSetLength(startingRS);
        startingRS.first();

        String insertQuery = "INSERT INTO location( vertical, horizontal, lateral, longitudinal, rowNumber, seatNumber, axleNumber ) " +
                "VALUES ('Test', 'Test', 'Test', 'Test', 12, 45, 78) " +
                "ON DUPLICATE KEY UPDATE id=LAST_INSERT_ID(id)";

        int update = db.dbUpdate(insertQuery);
        ResultSet endRS = db.dbQuery(query);
        int endRowCount = DataMethods.getResultSetLength(endRS);

        assertThat(startingRowCount+1, equalTo(endRowCount));
        assertThat(update, equalTo(1));

        //data Clean Up
        if (update == 1){
            db.deleteLastEntry("location");
        }

    }
}