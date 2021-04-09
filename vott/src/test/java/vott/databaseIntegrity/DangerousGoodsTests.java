package vott.databaseIntegrity;

import org.junit.Before;
import org.junit.Test;
import vott.DataMethods;
import vott.DatabaseConnection;
import vott.databaseModels.DangerousGoods;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;


public class DangerousGoodsTests {

    private DatabaseConnection db =  new DatabaseConnection();

    //Setup Test Container
    @Before
    public void before() throws SQLException {
        db.AWSConnection();
    }

    @Test
    public void DangerousGoodsInsertExistingDataTest() throws SQLException {

        //Create new MakeModel data object
        DangerousGoods dg = new DangerousGoods();

        String allDataQuery = "SELECT * FROM dangerous_goods";
        ResultSet startingRS = db.dbQuery(allDataQuery);
        int startingRowCount = DataMethods.getResultSetLength(startingRS);

        //Capture data from first row of results
        startingRS.first();
        dg.setDangerousGoods(startingRS);

        //create insert query using first row from the DB
        String insertQuery = dg.createInsertQuery();
        int update = db.dbUpdate(insertQuery);

        ResultSet endRS = db.dbQuery(allDataQuery);
        int endRowCount = DataMethods.getResultSetLength(endRS);

        assertThat(startingRowCount, equalTo(endRowCount));
        assertThat(update, equalTo(1));

    }

    @Test
    public void DangerousGoodsInsertNewDataTest() throws SQLException {

        String query = "SELECT * FROM dangerous_goods";

        ResultSet startingRS = db.dbQuery(query);
        int startingRowCount = DataMethods.getResultSetLength(startingRS);

        String insertQuery = "INSERT INTO dangerous_goods( name ) " +
                "VALUES ('Test Goods') " +
                "ON DUPLICATE KEY UPDATE id=LAST_INSERT_ID(id)";

        int update = db.dbUpdate(insertQuery);
        ResultSet endRS = db.dbQuery(query);
        int endRowCount = DataMethods.getResultSetLength(endRS);

        assertThat(startingRowCount+1, equalTo(endRowCount));
        assertThat(update, equalTo(1));

        //data Clean Up
        if (update == 1){
            db.deleteLastEntry("dangerous_goods");
        }

    }
}