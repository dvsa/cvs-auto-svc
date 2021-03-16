package vott;

import org.apache.ibatis.jdbc.ScriptRunner;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.testcontainers.containers.MySQLContainer;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.math.BigDecimal;
import java.sql.*;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class DatabaseTest {

    @Rule
    public MySQLContainer<?> Mysqldb = new MySQLContainer<>("mysql:latest");

    //Setup Test Container
    @Before
    public void before(){

    }

    @Test
    public void VOTT8Tests() throws SQLException, FileNotFoundException {

        String insertData = "INSERT INTO vott_db.make_model (make, model) VALUES ('Ford', 'Focus' )";

        String query = "SELECT * FROM vott_db.make_model";

        //Configure Connection
        Connection conn = null;
        conn = DriverManager.getConnection(Mysqldb.getJdbcUrl(),"test","test"); //Test Container address needed

        ScriptRunner sr = new ScriptRunner(conn);
        Reader sqlFile = new BufferedReader(new FileReader("C:\\Users\\robert.whitehouse\\Downloads\\CVS_DW_MOTH_schema (1).sql"));
        Statement stmt = conn.createStatement();

        //Run Query and catch results
//        stmt.execute(createTables);
        sr.runScript(sqlFile);
        stmt.executeUpdate(insertData);
        ResultSet results = stmt.executeQuery(query);

        while (results.next()){
            String name = results.getString("NAME");
            BigDecimal salary = results.getBigDecimal("SALARY");

            assertThat(name, equalTo("Rob"));
//            assertThat(salary, equalTo(123.0));

        }
    }

    //Test Container Teardown
    //@After
    //   void after(){
    //   sqldb.stop();
    //}
}