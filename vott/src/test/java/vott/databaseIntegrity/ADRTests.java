package vott.databaseIntegrity;

import org.junit.Before;
import org.junit.Test;
import vott.DataMethods;
import vott.DatabaseConnection;
import vott.databaseModels.ADR;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;


public class ADRTests {

    private DatabaseConnection db =  new DatabaseConnection();

    //Setup Test Container
    @Before
    public void before() throws SQLException {
        db.AWSConnection();
    }

    @Test
    public void ADRInsertExistingDataTest() throws SQLException {

        //Create new MakeModel data object
        ADR adr = new ADR();

        String allDataQuery = "SELECT * FROM adr";
        ResultSet startingRS = db.dbQuery(allDataQuery);
        int startingRowCount = DataMethods.getResultSetLength(startingRS);

        //Capture data from first row of results
        startingRS.first();
        adr.setADR(startingRS);

        //create insert query using first row from the DB
        String insertQuery = adr.createInsertQuery();
        int update = db.dbUpdate(insertQuery);

        ResultSet endRS = db.dbQuery(allDataQuery);
        int endRowCount = DataMethods.getResultSetLength(endRS);

        assertThat(startingRowCount, equalTo(endRowCount));
        assertThat(update, equalTo(1));

    }

    @Test
    public void ADRInsertNewDataTest() throws SQLException {

        String query = "SELECT * FROM adr";

        ResultSet startingRS = db.dbQuery(query);
        int startingRowCount = DataMethods.getResultSetLength(startingRS);

        String insertQuery = "INSERT INTO adr (`technical_record_id`,`type`,`approvalDate`,`listStatementApplicable`,`batteryListNumber`,`declarationsSeen`,`brakeDeclarationsSeen`,`brakeDeclarationIssuer`,`brakeEndurance`,`weight`,`compatibilityGroupJ`,`additionalExaminerNotes`,`applicantDetailsName`,`street`,`town`,`city`,`postcode`,`memosApply`,`adrTypeApprovalNo`,`adrCertificateNotes`,`tankManufacturer`,`yearOfManufacture`,`tankCode`,`specialProvisions`,`tankManufacturerSerialNo`,`tankTypeAppNo`,`tc2Type`,`tc2IntermediateApprovalNo`,`tc2IntermediateExpiryDate`,`substancesPermitted`,`statement`,`productListRefNo`,`productList`) " +
                "VALUES (1, 'Test Type', '2100-12-31', NULL, NULL, 1, 1, 1, 1, '1400', NULL, 'Test Examiner Notes', 'Automation Tester', 'Test Street', 'Test Town', 'Test City', 'Test Code', 'test', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL) " +
                "ON DUPLICATE KEY UPDATE id=LAST_INSERT_ID(id)";

        int update = db.dbUpdate(insertQuery);
        ResultSet endRS = db.dbQuery(query);
        int endRowCount = DataMethods.getResultSetLength(endRS);

        assertThat(startingRowCount+1, equalTo(endRowCount));
        assertThat(update, equalTo(1));

        //data Clean Up
        if (update == 1){
            db.deleteLastEntry("adr");
        }

    }
}