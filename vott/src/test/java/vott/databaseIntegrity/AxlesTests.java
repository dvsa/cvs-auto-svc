package vott.databaseIntegrity;

import org.junit.Before;
import org.junit.Test;
import vott.DataMethods;
import vott.DatabaseConnection;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;


public class AxlesTests {

    private DatabaseConnection db =  new DatabaseConnection();

    //Setup Test Container
    @Before
    public void before() throws SQLException {
        db.AWSConnection();
    }

    @Test
    public void AxlesInsertExistingDataTest() throws SQLException {

        Axle axle = new Axle();
        String query = "SELECT * FROM axles";

        ResultSet startingRS = db.dbQuery(query);
        int startingRowCount = DataMethods.getResultSetLength(startingRS);

        startingRS.first();
        axle.setAxle(startingRS);

        String insertQuery = axle.createInsertQuery();
        int update = db.dbUpdate(insertQuery);

        ResultSet endRS = db.dbQuery(query);
        int endRowCount = DataMethods.getResultSetLength(endRS);

         assertThat(startingRowCount, equalTo(endRowCount));
        assertThat(update, equalTo(1));

    }

    @Test
    public void AxlesInsertNewDataTest() throws SQLException {

        String query = "SELECT * FROM axles";

        ResultSet startingRS = db.dbQuery(query);
        int startingLength = DataMethods.getResultSetLength(startingRS);
        startingRS.first();

        String insertQuery = "INSERT INTO axles( technical_record_id, tyre_id, axleNumber, parkingBrakeMrk, kerbWeight, ladenWeight, gbWeight, eecWeight, designWeight, brakeActuator, leverLength, springBrakeParking ) " +
                "VALUES (1, 2, 1234, 1, NULL, NULL, 1000, NULL, 1200, 100, 50, 1) " +
                "ON DUPLICATE KEY UPDATE id=LAST_INSERT_ID(id)";

        int update = db.dbUpdate(insertQuery);
        ResultSet endRS = db.dbQuery(query);
        int endLength = DataMethods.getResultSetLength(endRS);

        assertThat(startingLength+1, equalTo(endLength));
        assertThat(update, equalTo(1));

        //data Clean Up
        if (update == 1){
            db.deleteLastEntry("axles");
        }

    }
}