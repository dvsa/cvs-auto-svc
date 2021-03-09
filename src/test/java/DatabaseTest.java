import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.testcontainers.containers.MySQLContainer;

import java.math.BigDecimal;
import java.sql.*;


public class DatabaseTest{

    @Rule
    public MySQLContainer<?> Mysqldb = new MySQLContainer<>("mysql:latest");

    //Setup Test Container
    @Before
    public void before(){

    }

    @Test
    public void VOTT8Tests() throws SQLException {

        //Data Setup
        String createTables = "CREATE TABLE EMPLOYEE"
                + "("
                + " ID serial,"
                + " NAME varchar(100) NOT NULL,"
                + " SALARY numeric(15, 2) NOT NULL,"
                + " PRIMARY KEY (ID)"
                + ")";

        String insertData = "INSERT INTO EMPLOYEE (NAME, SALARY) VALUES ('Rob', '123' ), ('Emanuel', '456' )";

        String query = "SELECT * FROM EMPLOYEE";

        //Configure Connection
        Connection conn = null;
        conn = DriverManager.getConnection(Mysqldb.getJdbcUrl(),"test","test"); //Test Container address needed

        Statement stmt = conn.createStatement();

        //Run Query and catch results
        stmt.execute(createTables);
        System.out.println("Table Created");
        stmt.executeUpdate(insertData);
        System.out.println("Data inserted");
        ResultSet results = stmt.executeQuery(query);

        System.out.println("SQL Queries Ran");

        while (results.next()){
            String name = results.getString("NAME");
            BigDecimal salary = results.getBigDecimal("SALARY");
            System.out.println(name + " earns " + salary);

            //assertEquals("Rob", name);
            //assertEquals(123, salary);
        }

        //assertEquals(1,anInt);
    }

    //Test Container Teardown
    //@After
    //   void after(){
    //   sqldb.stop();
    //}
}