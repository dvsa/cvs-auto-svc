package vott.databaseIntegrity;

import org.junit.Before;
import org.junit.Test;
import vott.DataMethods;
import vott.DatabaseConnection;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;


public class DefectTests {

    private DatabaseConnection db =  new DatabaseConnection();

    //Setup Test Container
    @Before
    public void before() throws SQLException {
        db.AWSConnection();
    }

    @Test
    public void DefectInsertExistingDataTest() throws SQLException {

        //Create new MakeModel data object
        Defect defect = new Defect();

        String allDataQuery = "SELECT * FROM defect";
        ResultSet startingRS = db.dbQuery(allDataQuery);
        int startingRowCount = DataMethods.getResultSetLength(startingRS);

        //Capture data from first row of results
        startingRS.first();
        defect.setDefect(startingRS);

        //create insert query using first row from the DB
        String insertQuery = defect.createInsertQuery();
        int update = db.dbUpdate(insertQuery);

        ResultSet endRS = db.dbQuery(allDataQuery);
        int endRowCount = DataMethods.getResultSetLength(endRS);

        assertThat(startingRowCount, equalTo(endRowCount));
        assertThat(update, equalTo(1));

    }

    @Test
    public void DefectInsertNewDataTest() throws SQLException {

        String query = "SELECT * FROM defect";

        ResultSet startingRS = db.dbQuery(query);
        int startingRowCount = DataMethods.getResultSetLength(startingRS);

        String insertQuery = "INSERT INTO defect( imNumber, imDescription, itemNumber, itemDescription, deficiencyRef, deficiencyId, deficiencySubId, deficiencyCategory, deficiencyText, stdForProhibition ) " +
                "VALUES ('12345', 'Test Description', '2', 'Test Description', NULL, NULL, NULL, NULL, NULL, '1') " +
                "ON DUPLICATE KEY UPDATE id=LAST_INSERT_ID(id)";
        int update = db.dbUpdate(insertQuery);
        ResultSet endRS = db.dbQuery(query);
        int endRowCount = DataMethods.getResultSetLength(endRS);

        assertThat(startingRowCount+1, equalTo(endRowCount));
        assertThat(update, equalTo(1));

        //data Clean Up
        if (update == 1){
            db.deleteLastEntry("defect");
        }

    }
}