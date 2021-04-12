package vott.databaseIntegrity;

import org.junit.Before;
import org.junit.Test;
import vott.DataMethods;
import vott.DatabaseConnection;
import vott.databaseModels.AxleSpacing;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;


public class AxleSpacingTests {

    private DatabaseConnection db =  new DatabaseConnection();

    //Setup Test Container
    @Before
    public void before() throws SQLException {
        db.AWSConnection();
    }

    @Test
    public void AxleSpacingInsertExistingDataTest() throws SQLException {

        //Create new MakeModel data object
        AxleSpacing as = new AxleSpacing();

        ResultSet startingRS = db.startingResultSet("axle_spacing");
        int startingRowCount = DataMethods.getResultSetLength(startingRS);

        //Capture data from first row of results
        startingRS.first();
        ResultSetMetaData rsmd = startingRS.getMetaData();
//        as.setAxleSpacing(startingRS);

        //create insert query using first row from the DB
        String insertQuery = as.createInsertQuery();
        int update = db.dbUpdate(insertQuery);

        ResultSet endRS = db.endResultSet("axle_spacing");
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
    public void AxleSpacingInsertNewDataTest() throws SQLException {

        String query = "SELECT * FROM axle_spacing";

        ResultSet startingRS = db.dbQuery(query);
        int startingRowCount = DataMethods.getResultSetLength(startingRS);

        String insertQuery = "INSERT INTO axle_spacing( technical_record_id, axles, value ) " +
                "VALUES ('1', 'test', 820) " +
                "ON DUPLICATE KEY UPDATE id=LAST_INSERT_ID(id)";

        int update = db.dbUpdate(insertQuery);
        ResultSet endRS = db.dbQuery(query);
        int endRowCount = DataMethods.getResultSetLength(endRS);

        assertThat(startingRowCount+1, equalTo(endRowCount));
        assertThat(update, equalTo(1));

        //data Clean Up
        if (update == 1){
            db.deleteLastEntry("axle_spacing");
        }

    }
}