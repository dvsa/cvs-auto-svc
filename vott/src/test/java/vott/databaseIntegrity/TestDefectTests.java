package vott.databaseIntegrity;

import org.junit.Before;
import org.junit.Test;
import vott.DataMethods;
import vott.DatabaseConnection;
import vott.databaseModels.TestDefect;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;


public class TestDefectTests {

    private DatabaseConnection db =  new DatabaseConnection();

    //Setup Test Container
    @Before
    public void before() throws SQLException {
        db.AWSConnection();
    }

    @Test
    public void TestDefectInsertExistingDataTest() throws SQLException {

        //Create new MakeModel data object
        TestDefect td = new TestDefect();

        String allDataQuery = "SELECT * FROM test_defect";
        ResultSet startingRS = db.dbQuery(allDataQuery);
        int startingRowCount = DataMethods.getResultSetLength(startingRS);

        //Capture data from first row of results
        startingRS.first();
        td.setTestDefect(startingRS);

        //create insert query using first row from the DB
        String insertQuery = td.createInsertQuery();
        int update = db.dbUpdate(insertQuery);

        ResultSet endRS = db.dbQuery(allDataQuery);
        int endRowCount = DataMethods.getResultSetLength(endRS);

        assertThat(startingRowCount, equalTo(endRowCount));
        assertThat(update, equalTo(1));

    }

    @Test
    public void TestDefectInsertNewDataTest() throws SQLException {

        String query = "SELECT * FROM test_defect";

        ResultSet startingRS = db.dbQuery(query);
        int startingRowCount = DataMethods.getResultSetLength(startingRS);

        String insertQuery = "INSERT INTO test_defect( test_result_id, defect_id, location_id, notes, prs, prohibitionIssued ) " +
                "VALUES (1, 1, 1, 'Test Notes', null , 1 ) " +
                "ON DUPLICATE KEY UPDATE id=LAST_INSERT_ID(id)";
        int update = db.dbUpdate(insertQuery);
        ResultSet endRS = db.dbQuery(query);
        int endRowCount = DataMethods.getResultSetLength(endRS);

        assertThat(startingRowCount+1, equalTo(endRowCount));
        assertThat(update, equalTo(1));

        //data Clean Up
        if (update == 1){
            db.deleteLastEntry("test_defect");
        }

    }
}