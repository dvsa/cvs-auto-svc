package vott.database;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import vott.database.connection.ConnectionFactory;
import vott.database.connection.DatabaseConfiguration;
import vott.models.dao.VehicleClass;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class VehicleClassRepositoryTest {
    private List<Integer> deleteOnExit;

    private VehicleClassRepository vehicleClassRepository;

    @Before
    public void setUp() {
        ConnectionFactory connectionFactory = new ConnectionFactory(
                DatabaseConfiguration.provide()
        );

        vehicleClassRepository = new VehicleClassRepository(connectionFactory);

        deleteOnExit = new ArrayList<>();
    }

    @After
    public void tearDown() {
        for (int primaryKey : deleteOnExit) {
            vehicleClassRepository.delete(primaryKey);
        }
    }

    @Test
    public void upsertingIdenticalDataReturnsSamePk() {
        int primaryKey1 = vehicleClassRepository.partialUpsert(newTestVehicleClass());
        int primaryKey2 = vehicleClassRepository.partialUpsert(newTestVehicleClass());

        deleteOnExit.add(primaryKey1);
        deleteOnExit.add(primaryKey2);

        assertEquals(primaryKey1, primaryKey2);
    }

    @Test
    public void upsertingNewDataReturnsDifferentPk() {
        VehicleClass vc1 = newTestVehicleClass();

        VehicleClass vc2 = newTestVehicleClass();
        vc2.setVehicleSize("88888");

        int primaryKey1 = vehicleClassRepository.partialUpsert(vc1);
        int primaryKey2 = vehicleClassRepository.partialUpsert(vc2);

        deleteOnExit.add(primaryKey1);
        deleteOnExit.add(primaryKey2);

        assertNotEquals(primaryKey1, primaryKey2);
    }

    private VehicleClass newTestVehicleClass() {
        VehicleClass vc = new VehicleClass();

        vc.setCode("1");
        vc.setDescription("Test Description");
        vc.setVehicleType("Test Type");
        vc.setVehicleSize("55555");
        vc.setVehicleConfiguration("Test Configuration");
        vc.setEuVehicleCategory("ABC");

        return vc;
    }
}
