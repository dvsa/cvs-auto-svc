package vottdatabase;

import org.apache.ibatis.jdbc.ScriptRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.testcontainers.containers.MySQLContainer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class DatabaseTest{

    //Variable Config
    static Connection conn = null;

    @Rule
    public MySQLContainer<?> Mysqldb = new MySQLContainer<>("mysql:latest");

    //Setup Test Container
    @Before
    public void setUp() throws Exception{
        try{
            conn = DriverManager.getConnection(Mysqldb.getJdbcUrl(),"test","test"); //Test Container address needed
            Statement stmt = conn.createStatement();

            //Read Schema and  run against DB connection
            ScriptRunner sr = new ScriptRunner(conn);
            Reader sqlFile = new BufferedReader(new FileReader("C:\\Users\\robert.whitehouse\\Downloads\\CVS_DW_MOTH_schema (1).sql"));
            sr.runScript(sqlFile);


        }
        catch( Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void test1(){

        try {
            String query = "Select * from table";
            ResultSet results = stmt.executeQuery(query);

            while (results.next()) {
                String name = results.getString("NAME");
                BigDecimal salary = results.getBigDecimal("SALARY");

                assertThat(name, equalTo("Rob"));
                assertThat(salary, equalTo(123.0));

            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    //Test Container Teardown
    @After
    public void tearDown() throws Exception{
        if (conn != null){
            conn.close();
        }
    }
}