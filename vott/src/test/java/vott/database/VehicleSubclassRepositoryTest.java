package vott.database;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import vott.database.connection.ConnectionFactory;
import vott.database.connection.DatabaseConfiguration;
import vott.models.dao.VehicleSubclass;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class VehicleSubclassRepositoryTest {

    private List<Integer> deleteOnExit;

    private VehicleSubclassRepository vehicleSubclassRepository;

    @Before
    public void setUp() {
        ConnectionFactory connectionFactory = new ConnectionFactory(
                DatabaseConfiguration.provide()
        );

        vehicleSubclassRepository = new VehicleSubclassRepository(connectionFactory);

        deleteOnExit = new ArrayList<>();
    }

    @After
    public void tearDown() {
        for (int primaryKey : deleteOnExit) {
            vehicleSubclassRepository.delete(primaryKey);
        }
    }

    @Test
    public void upsertingIdenticalDataReturnsSamePk() {
        int primaryKey1 = vehicleSubclassRepository.partialUpsert(newTestVehicleSubclass());
        int primaryKey2 = vehicleSubclassRepository.partialUpsert(newTestVehicleSubclass());

        deleteOnExit.add(primaryKey1);
        deleteOnExit.add(primaryKey2);

        assertEquals(primaryKey1, primaryKey2);
    }

    @Test
    public void upsertingNewDataReturnsDifferentPk() {
        VehicleSubclass vs1 = newTestVehicleSubclass();

        VehicleSubclass vs2 = newTestVehicleSubclass();
        vs2.setSubclass("y");

        int primaryKey1 = vehicleSubclassRepository.partialUpsert(vs1);
        int primaryKey2 = vehicleSubclassRepository.partialUpsert(vs2);

        deleteOnExit.add(primaryKey1);
        deleteOnExit.add(primaryKey2);

        assertNotEquals(primaryKey1, primaryKey2);
    }

    private VehicleSubclass newTestVehicleSubclass() {
        VehicleSubclass vs = new VehicleSubclass();

        vs.setVehicleClassID("1");
        vs.setSubclass("z");

        return vs;
    }
}
