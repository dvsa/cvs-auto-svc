package vott.databaseIntegrity;

import org.junit.Before;
import org.junit.Test;
import vott.DataMethods;
import vott.DatabaseConnection;
import vott.databaseModels.Preparer;
import vott.databaseModels.Tyre;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;


public class PreparerTests {

    private DatabaseConnection db =  new DatabaseConnection();

    //Setup Test Container
    @Before
    public void before() throws SQLException {
        db.AWSConnection();
    }

    @Test
    public void PreparerInsertExistingDataTest() throws SQLException {

        //Create new data object
        Preparer preparer = new Preparer();

        ResultSet startingRS = db.startingResultSet("preparer");
        int startingRowCount = DataMethods.getResultSetLength(startingRS);

        //Capture data from first row of results
        startingRS.first();
        ResultSetMetaData rsmd = startingRS.getMetaData();
        preparer.setPreparer(startingRS);

        //create insert query using first row from the DB
        String insertQuery = preparer.createInsertQuery();
        int update = db.dbUpdate(insertQuery);

        ResultSet endRS = db.endResultSet("preparer");
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
    public void PreparerInsertNewDataTest() throws SQLException {

        String query = "SELECT * FROM preparer";

        ResultSet startingRS = db.dbQuery(query);
        int startingRowCount = DataMethods.getResultSetLength(startingRS);

        String insertQuery = "INSERT INTO preparer( preparerId, name ) " +
                "VALUES ('12345', 'Automation Tester' ) " +
                "ON DUPLICATE KEY UPDATE id=LAST_INSERT_ID(id)";

        int update = db.dbUpdate(insertQuery);
        ResultSet endRS = db.dbQuery(query);
        int endRowCount = DataMethods.getResultSetLength(endRS);

        assertThat(startingRowCount+1, equalTo(endRowCount));
        assertThat(update, equalTo(1));

        //data Clean Up
        if (update == 1){
            db.deleteLastEntry("preparer");
        }

    }
}