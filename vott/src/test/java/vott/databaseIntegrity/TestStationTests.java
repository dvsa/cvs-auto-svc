package vott.databaseIntegrity;

import org.junit.Before;
import org.junit.Test;
import vott.DataMethods;
import vott.DatabaseConnection;
import vott.databaseModels.TestStation;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;


public class TestStationTests {

    private DatabaseConnection db =  new DatabaseConnection();

    //Setup Test Container
    @Before
    public void before() throws SQLException {
        db.AWSConnection();
    }

    @Test
    public void TestStationInsertExistingDataTest() throws SQLException {

        //Create new MakeModel data object
        TestStation ts = new TestStation();

        String allDataQuery = "SELECT * FROM test_station";
        ResultSet startingRS = db.dbQuery(allDataQuery);
        int startingRowCount = DataMethods.getResultSetLength(startingRS);

        //Capture data from first row of results
        startingRS.first();
        ts.setTestStation(startingRS);

        //create insert query using first row from the DB
        String insertQuery = ts.createInsertQuery();
        int update = db.dbUpdate(insertQuery);

        ResultSet endRS = db.dbQuery(allDataQuery);
        int endRowCount = DataMethods.getResultSetLength(endRS);

        assertThat(startingRowCount, equalTo(endRowCount));
        assertThat(update, equalTo(1));

    }

    @Test
    public void TestStationInsertNewDataTest() throws SQLException {

        String query = "SELECT * FROM test_station";

        ResultSet startingRS = db.dbQuery(query);
        int startingRowCount = DataMethods.getResultSetLength(startingRS);

        String insertQuery = "INSERT INTO test_station( pNumber, name, type ) " +
                "VALUES ('0123456', 'Test Test Station', 'auto') " +
                "ON DUPLICATE KEY UPDATE id=LAST_INSERT_ID(id)";

        int update = db.dbUpdate(insertQuery);
        ResultSet endRS = db.dbQuery(query);
        int endRowCount = DataMethods.getResultSetLength(endRS);

        assertThat(startingRowCount+1, equalTo(endRowCount));
        assertThat(update, equalTo(1));

        //data Clean Up
        if (update == 1){
            db.deleteLastEntry("test_station");
        }

    }
}