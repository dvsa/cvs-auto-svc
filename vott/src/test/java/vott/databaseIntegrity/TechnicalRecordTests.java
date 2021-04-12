package vott.databaseIntegrity;

import org.junit.Before;
import org.junit.Test;
import vott.DataMethods;
import vott.DatabaseConnection;
import vott.databaseModels.TechnicalRecord;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;


public class TechnicalRecordTests {

    private DatabaseConnection db =  new DatabaseConnection();

    //Setup Test Container
    @Before
    public void before() throws SQLException {
        db.AWSConnection();
    }

    @Test
    public void TechnicalRecordInsertExistingDataTest() throws SQLException {

        //Create new data object
        TechnicalRecord tr = new TechnicalRecord();

        ResultSet startingRS = db.startingResultSet("technical_record");
        int startingRowCount = DataMethods.getResultSetLength(startingRS);

        //Capture data from first row of results
        startingRS.first();
        ResultSetMetaData rsmd = startingRS.getMetaData();
//        tr.setTechnicalRecord(startingRS);

        //create insert query using first row from the DB
        String insertQuery = tr.createInsertQuery();
        int update = db.dbUpdate(insertQuery);

        ResultSet endRS = db.endResultSet("technical_record");
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


    public void TechnicalRecordInsertNewDataTest() throws SQLException {

        String query = "SELECT * FROM technical_record";

        ResultSet startingRS = db.dbQuery(query);
        int startingRowCount = DataMethods.getResultSetLength(startingRS);

        String insertQuery = "INSERT INTO `technical_record` (`vehicle_id`,`recordCompleteness`,`createdAt`,`lastUpdatedAt`,`make_model_id`,`functionCode`,`offRoad`,`numberOfWheelsDriven`,`emissionsLimit`,`departmentalVehicleMarker`,`alterationMarker`,`vehicle_class_id`,`variantVersionNumber`,`grossEecWeight`,`trainEecWeight`,`maxTrainEecWeight`,`applicant_detail_id`,`purchaser_detail_id`,`manufacturer_detail_id`,`manufactureYear`,`regnDate`,`firstUseDate`,`coifDate`,`ntaNumber`,`coifSerialNumber`,`coifCertifierName`,`approvalType`,`approvalTypeNumber`,`variantNumber`,`conversionRefNo`,`seatsLowerDeck`,`seatsUpperDeck`,`standingCapacity`,`speedRestriction`,`speedLimiterMrk`,`tachoExemptMrk`,`dispensations`,`remarks`,`reasonForCreation`,`statusCode`,`unladenWeight`,`grossKerbWeight`,`grossLadenWeight`,`grossGbWeight`,`grossDesignWeight`,`trainGbWeight`,`trainDesignWeight`,`maxTrainGbWeight`,`maxTrainDesignWeight`,`maxLoadOnCoupling`,`frameDescription`,`tyreUseCode`,`roadFriendly`,`drawbarCouplingFitted`,`euroStandard`,`suspensionType`,`couplingType`,`length`,`height`,`width`,`frontAxleTo5thWheelMin`,`frontAxleTo5thWheelMax`,`frontAxleTo5thWheelCouplingMin`,`frontAxleTo5thWheelCouplingMax`,`frontAxleToRearAxle`,`rearAxleToRearTrl`,`couplingCenterToRearAxleMin`,`couplingCenterToRearAxleMax`,`couplingCenterToRearTrlMin`,`couplingCenterToRearTrlMax`,`centreOfRearmostAxleToRearOfTrl`,`notes`,`purchaserNotes`,`manufacturerNotes`,`noOfAxles`,`brakeCode`,`brakes_dtpNumber`,`brakes_loadSensingValve`,`brakes_antilockBrakingSystem`,`createdBy_Id`,`lastUpdatedBy_Id`,`updateType`,`numberOfSeatbelts`,`seatbeltInstallationApprovalDate`) " +
//                "VALUES ('"+vehicleID+"', '"+recordCompleteness+"', '"+createdAt+"', '"+lastUpdatedAt+"', '"+makeModelID+"', '"+functionCode+"', '"+offRoad+"', '"+numberOfWheelsDriven+"', '"+emissionsLimit+"', '"+departmentalVehicleMarker+"', '"+alterationMarker+"', '"+vehicleClassID+"', '"+variantVersionNumber+"', '"+grossEecWeight+"', '"+trainEecWeight+"', '"+maxTrainEecWeight+"', '"+applicantDetailID+"', '"+purchaserDetailID+"', '"+manufacturerDetailID+"', '"+manufactureYear+"', '"+regnDate+"', '"+firstUseDate+"', '"+coifDate+"', '"+ntaNumber+"', '"+coifSerialNumber+"', '"+coifCertifierName+"', '"+approvalType+"', '"+approvalTypeNumber+"', '"+variantNumber+"', '"+conversionRefNo+"', '"+seatsLowerDeck+"', '"+seatsUpperDeck+"', '"+standingCapacity+"', '"+speedRestriction+"', '"+speedLimiterMrk+"', '"+tachoExemptMrk+"', '"+dispensations+"', '"+remarks+"', '"+reasonForCreation+"', '"+statusCode+"', '"+unladenWeight+"', '"+grossKerbWeight+"', '"+grossLadenWeight+"', '"+grossGbWeight+"', '"+grossDesignWeight+"', '"+trainGbWeight+"', '"+trainDesignWeight+"', '"+maxTrainGbWeight+"', '"+maxTrainDesignWeight+"', '"+maxLoadOnCoupling+"', '"+frameDescription+"', '"+tyreUseCode+"', '"+roadFriendly+"', '"+drawbarCouplingFitted+"', '"+euroStandard+"', '"+suspensionType+"', '"+couplingType+"', '"+length+"', '"+height+"', '"+width+"', '"+frontAxleTo5thWheelMin+"', '"+frontAxleTo5thWheelMax+"', '"+frontAxleTo5thWheelCouplingMin+"', '"+frontAxleTo5thWheelCouplingMax+"', '"+frontAxleToRearAxle+"', '"+rearAxleToRearTrl+"', '"+couplingCenterToRearAxleMin+"', '"+couplingCenterToRearAxleMax+"', '"+couplingCenterToRearTrlMin+"', '"+couplingCenterToRearTrlMax+"', '"+centreOfRearmostAxleToRearOfTrl+"', '"+notes+"', '"+purchaserNotes+"', '"+manufacturerNotes+"', '"+noOfAxles+"', '"+brakeCode+"', '"+brakes_dtpNumber+"', '"+brakes_loadSensingValve+"', '"+brakes_antilockBrakingSystem+"', '"+createdByID+"', '"+lastUpdatedByID+"', '"+updateType+"', '"+numberOfSeatbelts+"', '"+seatbeltInstallationApprovalDate+"') " +
                "ON DUPLICATE KEY UPDATE id=LAST_INSERT_ID(id)";

        int update = db.dbUpdate(insertQuery);
        ResultSet endRS = db.dbQuery(query);
        int endRowCount = DataMethods.getResultSetLength(endRS);

        assertThat(startingRowCount+1, equalTo(endRowCount));
        assertThat(update, equalTo(1));

        //data Clean Up
        if (update == 1){
            db.deleteLastEntry("technical_record");
        }

    }
}