package vott.database;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import vott.database.connection.ConnectionFactory;
import vott.database.connection.DatabaseConfiguration;
import vott.models.dao.Axles;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class AxlesRepositoryTest {

    private List<Integer> deleteOnExit;

    private AxlesRepository axlesRepository;

    @Before
    public void setUp() {
        ConnectionFactory connectionFactory = new ConnectionFactory(
                DatabaseConfiguration.provide()
        );

        axlesRepository = new AxlesRepository(connectionFactory);

        deleteOnExit = new ArrayList<>();
    }

    @After
    public void tearDown() {
        for (int primaryKey : deleteOnExit) {
            axlesRepository.delete(primaryKey);
        }
    }

    @Test
    public void upsertingIdenticalDataReturnsSamePk() {
        int primaryKey1 = axlesRepository.fullUpsert(newTestAxles());
        int primaryKey2 = axlesRepository.fullUpsert(newTestAxles());

        deleteOnExit.add(primaryKey1);
        deleteOnExit.add(primaryKey2);

        assertEquals(primaryKey1, primaryKey2);
    }

    @Test
    public void upsertingNewTechRecordIDReturnsDifferentPk() {
        Axles axles1 = newTestAxles();

        Axles axles2 = newTestAxles();
        axles2.setTechnicalRecordID("2");

        int primaryKey1 = axlesRepository.fullUpsert(axles1);
        int primaryKey2 = axlesRepository.fullUpsert(axles2);

        deleteOnExit.add(primaryKey1);
        deleteOnExit.add(primaryKey2);

        assertNotEquals(primaryKey1, primaryKey2);
    }

    @Test
    public void upsertingNewTyreIDReturnsDifferentPk() {
        Axles axles1 = newTestAxles();

        Axles axles2 = newTestAxles();
        axles2.setTyreID("2");

        int primaryKey1 = axlesRepository.fullUpsert(axles1);
        int primaryKey2 = axlesRepository.fullUpsert(axles2);

        deleteOnExit.add(primaryKey1);
        deleteOnExit.add(primaryKey2);

        assertNotEquals(primaryKey1, primaryKey2);
    }

    @Test
    public void upsertingNewAxleNumberReturnsDifferentPk() {
        Axles axles1 = newTestAxles();

        Axles axles2 = newTestAxles();
        axles2.setAxleNumber("333");

        int primaryKey1 = axlesRepository.fullUpsert(axles1);
        int primaryKey2 = axlesRepository.fullUpsert(axles2);

        deleteOnExit.add(primaryKey1);
        deleteOnExit.add(primaryKey2);

        assertNotEquals(primaryKey1, primaryKey2);
    }

    @Test
    public void upsertingIdenticalIndexValuesReturnsSamePk() {
        Axles axles1 = newTestAxles();

        Axles axles2 = newTestAxles();
        axles2.setParkingBrakeMrk("8");

        int primaryKey1 = axlesRepository.fullUpsert(axles1);
        int primaryKey2 = axlesRepository.fullUpsert(axles2);

        deleteOnExit.add(primaryKey1);
        deleteOnExit.add(primaryKey2);

        assertEquals(primaryKey1, primaryKey2);
    }

    private Axles newTestAxles() {
        Axles axles = new Axles();

        axles.setTechnicalRecordID("1");
        axles.setTyreID("1");
        axles.setAxleNumber("222");
        axles.setParkingBrakeMrk("5");
        axles.setKerbWeight("1200");
        axles.setLadenWeight("1500");
        axles.setGbWeight("1200");
        axles.setEecWeight("1500");
        axles.setDesignWeight("1200");
        axles.setBrakeActuator("10");
        axles.setLeverLength("10");
        axles.setSpringBrakeParking("123");

        return axles;
    }
}
