package vott.database;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import vott.database.connection.ConnectionFactory;
import vott.database.connection.DatabaseConfiguration;
import vott.models.dao.PSVBrakes;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class PSVBrakesRepositoryTest {

    private List<Integer> deleteOnExit;

    private PSVBrakesRepository psvBrakesRepository;

    @Before
    public void setUp() {
        ConnectionFactory connectionFactory = new ConnectionFactory(
                DatabaseConfiguration.provide()
        );

        psvBrakesRepository = new PSVBrakesRepository(connectionFactory);

        deleteOnExit = new ArrayList<>();
    }

    @After
    public void tearDown() {
        for (int primaryKey : deleteOnExit) {
            psvBrakesRepository.delete(primaryKey);
        }
    }

    @Test
    public void upsertingIdenticalDataReturnsSamePk() {
        int primaryKey1 = psvBrakesRepository.fullUpsert(newTestPSVBrakes());
        int primaryKey2 = psvBrakesRepository.fullUpsert(newTestPSVBrakes());

        deleteOnExit.add(primaryKey1);
        deleteOnExit.add(primaryKey2);

        assertEquals(primaryKey1, primaryKey2);
    }

    @Test
    public void upsertingDifferentTechRecordIDReturnsDifferentPk() {
        PSVBrakes vs1 = newTestPSVBrakes();

        PSVBrakes vs2 = newTestPSVBrakes();
        vs2.setTechnicalRecordID("2");

        int primaryKey1 = psvBrakesRepository.fullUpsert(vs1);
        int primaryKey2 = psvBrakesRepository.fullUpsert(vs2);

        deleteOnExit.add(primaryKey1);
        deleteOnExit.add(primaryKey2);

        assertNotEquals(primaryKey1, primaryKey2);
    }

    @Test
    public void upsertingIdenticalIndexValuesReturnsSamePk() {
        PSVBrakes vs1 = newTestPSVBrakes();

        PSVBrakes vs2 = newTestPSVBrakes();
        vs2.setBrakeCode("Code");

        int primaryKey1 = psvBrakesRepository.fullUpsert(vs1);
        int primaryKey2 = psvBrakesRepository.fullUpsert(vs2);

        deleteOnExit.add(primaryKey1);
        deleteOnExit.add(primaryKey2);

        assertEquals(primaryKey1, primaryKey2);
    }

    private PSVBrakes newTestPSVBrakes() {
        PSVBrakes psv = new PSVBrakes();

        psv.setTechnicalRecordID("1");
        psv.setBrakeCodeOriginal("222");
        psv.setBrakeCode("Test");
        psv.setDataTrBrakeOne("Test Data");
        psv.setDataTrBrakeTwo("Test Data");
        psv.setDataTrBrakeThree("Test Data");
        psv.setRetarderBrakeOne("Test Data");
        psv.setRetarderBrakeTwo("Test Data");
        psv.setServiceBrakeForceA("11");
        psv.setSecondaryBrakeForceA("22");
        psv.setParkingBrakeForceA("33");
        psv.setServiceBrakeForceB("44");
        psv.setSecondaryBrakeForceB("55");
        psv.setParkingBrakeForceB("66");

        return psv;
    }
}
