package vott.databaseIntegrity;

import org.junit.Before;
import org.junit.Test;
import vott.DataMethods;
import vott.DatabaseConnection;
import vott.databaseModels.Vehicle;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;


public class VehicleTests {

    private DatabaseConnection db =  new DatabaseConnection();

    //Setup Test Container
    @Before
    public void before() throws SQLException {
        db.AWSConnection();
    }

    @Test
    public void VehicleInsertExistingDataTest() throws SQLException {

        //Create new MakeModel data object
        Vehicle vehicle = new Vehicle();

        String allDataQuery = "SELECT * FROM vehicle";
        ResultSet startingRS = db.dbQuery(allDataQuery);
        int startingRowCount = DataMethods.getResultSetLength(startingRS);

        //Capture data from first row of results
        startingRS.first();
        vehicle.setVehicle(startingRS);

        //create insert query using first row from the DB
        String insertQuery = vehicle.createInsertQuery();
        int update = db.dbUpdate(insertQuery);

        ResultSet endRS = db.dbQuery(allDataQuery);
        int endRowCount = DataMethods.getResultSetLength(endRS);

        assertThat(startingRowCount, equalTo(endRowCount));
        assertThat(update, equalTo(1));

    }

    @Test
    public void VehicleInsertNewDataTest() throws SQLException {

        String query = "SELECT * FROM vehicle";

        ResultSet startingRS = db.dbQuery(query);
        int startingRowCount = DataMethods.getResultSetLength(startingRS);

        String insertQuery = "INSERT INTO vehicle( system_number, vin, vrm_trm, trailer_id ) " +
                "VALUES ('test number', '123456', '', 'ABC123') " +
                "ON DUPLICATE KEY UPDATE id=LAST_INSERT_ID(id)";

        int update = db.dbUpdate(insertQuery);
        ResultSet endRS = db.dbQuery(query);
        int endRowCount = DataMethods.getResultSetLength(endRS);

        assertThat(startingRowCount+1, equalTo(endRowCount));
        assertThat(update, equalTo(1));

        //data Clean Up
        if (update == 1){
            db.deleteLastEntry("vehicle");
        }

    }
}