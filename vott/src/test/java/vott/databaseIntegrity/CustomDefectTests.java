package vott.databaseIntegrity;

import org.junit.Before;
import org.junit.Test;
import vott.DataMethods;
import vott.DatabaseConnection;
import vott.databaseModels.CustomDefect;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;


public class CustomDefectTests {

    private DatabaseConnection db =  new DatabaseConnection();

    //Setup Test Container
    @Before
    public void before() throws SQLException {
        db.AWSConnection();
    }

    @Test
    public void CustomDefectInsertExistingDataTest() throws SQLException {

        CustomDefect cd = new CustomDefect();

        String allDataQuery = "SELECT * FROM custom_defect";
        ResultSet startingRS = db.dbQuery(allDataQuery);
        int startingRowCount = DataMethods.getResultSetLength(startingRS);

        startingRS.first();
        cd.setCustomDefect(startingRS);

        String insertQuery = cd.createInsertQuery();
        int update = db.dbUpdate(insertQuery);

        ResultSet endRS = db.dbQuery(allDataQuery);
        int endRowCount = DataMethods.getResultSetLength(endRS);

        assertThat(startingRowCount, equalTo(endRowCount));
        assertThat(update, equalTo(1));
    }

    @Test
    public void CustomDefectInsertNewDataTest() throws SQLException {

        String query = "SELECT * FROM custom_defect";

        ResultSet startingRS = db.dbQuery(query);
        int startingRowCount = DataMethods.getResultSetLength(startingRS);
        startingRS.first();

        String insertQuery = "INSERT INTO custom_defect( test_result_id, referenceNumber, defectName, defectNotes ) " +
                "VALUES (1, 'ABC123', 'Test Custom Defect', 'Test Custom Defect Notes') " +
                "ON DUPLICATE KEY UPDATE id=LAST_INSERT_ID(id)";

        int update = db.dbUpdate(insertQuery);
        ResultSet endRS = db.dbQuery(query);
        int endRowCount = DataMethods.getResultSetLength(endRS);

        assertThat(startingRowCount+1, equalTo(endRowCount));
        assertThat(update, equalTo(1));

        //data Clean Up
        if (update == 1){
            db.deleteLastEntry("custom_defect");
        }

    }
}