package vott.database;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import vott.database.connection.ConnectionFactory;
import vott.database.connection.DatabaseConfiguration;
import vott.models.dao.TechnicalRecord;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class TechnicalRecordRepositoryTest {

    private List<Integer> deleteOnExit;

    private TechnicalRecordRepository technicalRecordRepository;

    @Before
    public void setUp() {
        ConnectionFactory connectionFactory = new ConnectionFactory(
                DatabaseConfiguration.provide()
        );

        technicalRecordRepository = new TechnicalRecordRepository(connectionFactory);

        deleteOnExit = new ArrayList<>();
    }

    @After
    public void tearDown() {
        for (int primaryKey : deleteOnExit) {
            technicalRecordRepository.delete(primaryKey);
        }
    }

    @Test
    public void upsertingIdenticalDataReturnsSamePk() {
        int primaryKey1 = technicalRecordRepository.fullUpsert(newTestTechnicalRecord());
        int primaryKey2 = technicalRecordRepository.fullUpsert(newTestTechnicalRecord());

        deleteOnExit.add(primaryKey1);
        deleteOnExit.add(primaryKey2);

        assertEquals(primaryKey1, primaryKey2);
    }

    @Test
    public void upsertingDifferentVehicleIDValueReturnsDifferentPk() {
        TechnicalRecord tr1 = newTestTechnicalRecord();

        TechnicalRecord tr2 = newTestTechnicalRecord();
        tr2.setVehicleID("2");

        int primaryKey1 = technicalRecordRepository.fullUpsert(tr1);
        int primaryKey2 = technicalRecordRepository.fullUpsert(tr2);

        deleteOnExit.add(primaryKey1);
        deleteOnExit.add(primaryKey2);

        assertNotEquals(primaryKey1, primaryKey2);
    }

    @Test
    public void upsertingDifferentCreatedAtValueReturnsDifferentPk() {
        TechnicalRecord tr1 = newTestTechnicalRecord();

        TechnicalRecord tr2 = newTestTechnicalRecord();
        tr2.setCreatedAt("2021-12-31 00:00:00");

        int primaryKey1 = technicalRecordRepository.fullUpsert(tr1);
        int primaryKey2 = technicalRecordRepository.fullUpsert(tr2);

        deleteOnExit.add(primaryKey1);
        deleteOnExit.add(primaryKey2);

        assertNotEquals(primaryKey1, primaryKey2);
    }

    @Test
    public void upsertingIdenticalIndexValuesReturnsSamePk() {
        TechnicalRecord tr1 = newTestTechnicalRecord();

        TechnicalRecord tr2 = newTestTechnicalRecord();
        tr2.setRecordCompleteness("ABCD");

        int primaryKey1 = technicalRecordRepository.fullUpsert(tr1);
        int primaryKey2 = technicalRecordRepository.fullUpsert(tr2);

        deleteOnExit.add(primaryKey1);
        deleteOnExit.add(primaryKey2);

        assertEquals(primaryKey1, primaryKey2);
    }

    private TechnicalRecord newTestTechnicalRecord() {
        TechnicalRecord tr = new TechnicalRecord();

        tr.setVehicleID("1");
        tr.setRecordCompleteness("Complete");
        tr.setCreatedAt("2021-01-01 00:00:00");
        tr.setLastUpdatedAt("2021-01-01 00:00:00");
        tr.setMakeModelID("1");
        tr.setFunctionCode("A");
        tr.setOffRoad("1");
        tr.setNumberOfWheelsDriven("4");
        tr.setEmissionsLimit("Test Emission Limit");
        tr.setDepartmentalVehicleMarker("1");
        tr.setAlterationMarker("1");
        tr.setVehicleClassID("1");
        tr.setVariantVersionNumber("Test Variant Number");
        tr.setGrossEecWeight("1200");
        tr.setTrainEecWeight("1400");
        tr.setMaxTrainEecWeight("1400");
        tr.setApplicantDetailID("1");
        tr.setPurchaserDetailID("1");
        tr.setManufacturerDetailID("1");
        tr.setManufactureYear("2021");
        tr.setRegnDate("2021-01-01");
        tr.setFirstUseDate("2021-01-01");
        tr.setCoifDate("2021-01-01");
        tr.setNtaNumber("NTA Number");
        tr.setCoifSerialNumber("55555");
        tr.setCoifCertifierName("88888");
        tr.setApprovalType("111");
        tr.setApprovalTypeNumber("ABC11111");
        tr.setVariantNumber("Test Variant");
        tr.setConversionRefNo("10");
        tr.setSeatsLowerDeck("2");
        tr.setSeatsUpperDeck("3");
        tr.setStandingCapacity("15");
        tr.setSpeedRestriction("60");
        tr.setSpeedLimiterMrk("1");
        tr.setTachoExemptMrk("1");
        tr.setDispensations("Test Dispensations");
        tr.setRemarks("Automation Test Remarks");
        tr.setReasonForCreation("Automation Test ");
        tr.setStatusCode("B987");
        tr.setUnladenWeight("1400");
        tr.setGrossKerbWeight("1400");
        tr.setGrossLadenWeight("1400");
        tr.setGrossGbWeight("1400");
        tr.setGrossDesignWeight("1400");
        tr.setTrainGbWeight("1400");
        tr.setTrainDesignWeight("1400");
        tr.setMaxTrainGbWeight("1400");
        tr.setMaxTrainDesignWeight("1400");
        tr.setMaxLoadOnCoupling("1400");
        tr.setFrameDescription("Test Automation");
        tr.setTyreUseCode("A1");
        tr.setRoadFriendly("1");
        tr.setDrawbarCouplingFitted("1");
        tr.setEuroStandard("Y555");
        tr.setSuspensionType("Y");
        tr.setCouplingType("B");
        tr.setLength("100");
        tr.setHeight("50");
        tr.setWidth("50");
        tr.setFrontAxleTo5thWheelCouplingMin("55");
        tr.setFrontAxleTo5thWheelCouplingMax("65");
        tr.setFrontAxleTo5thWheelCouplingMin("45");
        tr.setFrontAxleTo5thWheelCouplingMax("65");
        tr.setFrontAxleToRearAxle("15");
        tr.setRearAxleToRearTrl("25");
        tr.setCouplingCenterToRearAxleMin("25");
        tr.setCouplingCenterToRearAxleMax("85");
        tr.setCouplingCenterToRearAxleMin("25");
        tr.setCouplingCenterToRearAxleMax("85");
        tr.setCentreOfRearmostAxleToRearOfTrl("25");
        tr.setNotes("Test Notes");
        tr.setPurchaserNotes("Purchaser Notes");
        tr.setManufacturerNotes("Manufactuer Notes");
        tr.setNoOfAxles("3");
        tr.setBrakeCode("XXXXX");
        tr.setBrakes_dtpNumber("DTP111");
        tr.setBrakes_loadSensingValve("1");
        tr.setBrakes_antilockBrakingSystem("1");
        tr.setCreatedByID("1");
        tr.setLastUpdatedByID("1");
        tr.setUpdateType("AutoTest");
        tr.setNumberOfSeatbelts("3");
        tr.setSeatbeltInstallationApprovalDate("2021-01-01");

        return tr;
    }
}
