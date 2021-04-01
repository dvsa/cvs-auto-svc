package vott;

import org.junit.Before;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;


public class DatabaseFingerprintTests {

    DatabaseConnection db =  new DatabaseConnection();

    //Setup Test Container
    @Before
    public void before() throws SQLException {
        db.AWSConnection();
    }

    @Test
    public void FingerprintTest1() throws SQLException {

        String query = "SELECT * FROM technical_record";

        ResultSet results = db.dbQuery(query);

        System.out.println(db.conn);

//        while(results.next()){
//            String vehicle = results.getString("vehicle_id");
//            System.out.println(vehicle);
//        }

    }

    //Test Container Teardown
    //@After
    //   void after(){
    //   sqldb.stop();
    //}
}