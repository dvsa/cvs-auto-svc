package vott.databaseIntegrity;

import org.junit.Before;
import org.junit.Test;
import vott.DataMethods;
import vott.DatabaseConnection;
import vott.databaseModels.VehicleClass;
import vott.databaseModels.VehicleSubclass;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;


public class VehicleSubclassTests {

    private DatabaseConnection db =  new DatabaseConnection();

    //Setup Test Container
    @Before
    public void before() throws SQLException {
        db.AWSConnection();
    }

    @Test
    public void VehicleSubclassInsertExistingDataTest() throws SQLException {

        //Create new data object
        VehicleSubclass vc = new VehicleSubclass();

        ResultSet startingRS = db.startingResultSet("vehicle_subclass");
        int startingRowCount = DataMethods.getResultSetLength(startingRS);

        //Capture data from first row of results
        startingRS.first();
        ResultSetMetaData rsmd = startingRS.getMetaData();
        vc.setVehicleSubclass(startingRS);

        //create insert query using first row from the DB
        String insertQuery = vc.createInsertQuery();
        int update = db.dbUpdate(insertQuery);

        ResultSet endRS = db.endResultSet("vehicle_subclass");
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
    public void VehicleSubclassInsertNewDataTest() throws SQLException {

        String query = "SELECT * FROM vehicle_subclass";

        ResultSet startingRS = db.dbQuery(query);
        int startingRowCount = DataMethods.getResultSetLength(startingRS);

        String insertQuery = "INSERT INTO vehicle_subclass( vehicle_class_id, subclass ) " +
                "VALUES ('1', 'a') " +
                "ON DUPLICATE KEY UPDATE id=LAST_INSERT_ID(id)";

        int update = db.dbUpdate(insertQuery);
        ResultSet endRS = db.dbQuery(query);
        int endRowCount = DataMethods.getResultSetLength(endRS);

        assertThat(startingRowCount+1, equalTo(endRowCount));
        assertThat(update, equalTo(1));

        //data Clean Up
        if (update == 1){
            db.deleteLastEntry("vehicle_subclass");
        }

    }
}