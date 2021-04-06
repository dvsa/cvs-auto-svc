package vott;

import com.amazonaws.services.dynamodbv2.xspec.S;
import org.junit.Before;
import org.junit.Test;

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

        String query = "SELECT * FROM make_model";

        ResultSet startingRS = db.dbQuery(query);
        int startingLength = DataMethods.getResultSetLength(startingRS);
        startingRS.first();

        MakeModel mm = new MakeModel();
        mm.setMakeModel(startingRS);

        String fingerprintQuery = "INSERT INTO make_model( make, model, chassisMake, chassisModel, bodyMake, bodyModel, modelLiteral, bodyTypeCode, bodyTypeDescription, fuelPropulsionSystem, dtpCode ) " +
                "VALUES ('"+mm.getMake()+"', '"+mm.getModel()+"', '"+mm.getChassisMake()+"', '"+mm.getChassisModel()+"', '"+mm.getBodyMake()+"', '"+mm.getBodyModel()+"', '"+mm.getModelLiteral()+"', '"+mm.getBodyTypeCode()+"', '"+mm.getBodyTypeDescription()+"', '"+mm.getFuelPropulsionSystem()+"', '"+mm.getDtpCode()+"') ON DUPLICATE KEY UPDATE id=LAST_INSERT_ID(id)";

        int update = db.dbUpdate(fingerprintQuery);
        ResultSet endRS = db.dbQuery(query);
        int endLength = DataMethods.getResultSetLength(endRS);

        assertThat(startingLength, equalTo(endLength));
        assertThat(update, equalTo(1));

    }

    @Test
    public void CustomDefectInsertNewDataTest() throws SQLException {

        String query = "SELECT * FROM make_model";

        ResultSet startingRS = db.dbQuery(query);
        int startingLength = DataMethods.getResultSetLength(startingRS);
        startingRS.first();

        String fingerprintQuery = "INSERT INTO make_model( make, model, chassisMake, chassisModel, bodyMake, bodyModel, modelLiteral, bodyTypeCode, bodyTypeDescription, fuelPropulsionSystem, dtpCode ) " +
                "VALUES ('test_make', 'test_model', '', '', '', '', '', '', 'test_description', '', '') ON DUPLICATE KEY UPDATE id=LAST_INSERT_ID(id)";

        int update = db.dbUpdate(fingerprintQuery);
        ResultSet endRS = db.dbQuery(query);
        int endLength = DataMethods.getResultSetLength(endRS);

        assertThat(startingLength+1, equalTo(endLength));
        assertThat(update, equalTo(1));

        //data Clean Up
        String deleteQuery = "DELETE FROM make_model WHERE make = 'test_make'";
        db.dbUpdate(deleteQuery);

    }
}