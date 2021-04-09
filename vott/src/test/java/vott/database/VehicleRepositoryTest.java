package vott.database;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import vott.databaseModels.Vehicle;
import vott.repository.ConnectionFactory;
import vott.repository.DatabaseConfiguration;
import vott.repository.VehicleRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class VehicleRepositoryTest {

    private List<Integer> deleteOnExit;

    private VehicleRepository vehicleRepository;

    @Before
    public void setUp() {
        ConnectionFactory connectionFactory = new ConnectionFactory(
            DatabaseConfiguration.robertWhitehouse()
        );

        vehicleRepository = new VehicleRepository(connectionFactory);

        deleteOnExit = new ArrayList<>();
    }

    @After
    public void tearDown() {
        for (int primaryKey : deleteOnExit) {
            vehicleRepository.delete(primaryKey);
        }
    }

    @Test
    public void upsertingIdenticalDataReturnsSamePk() {
        int primaryKey1 = vehicleRepository.partialUpsert(newTestVehicle());
        int primaryKey2 = vehicleRepository.partialUpsert(newTestVehicle());

        deleteOnExit.add(primaryKey1);
        deleteOnExit.add(primaryKey2);

        assertEquals(primaryKey1, primaryKey2);
    }

    @Test
    public void upsertingNewDataReturnsDifferentPk() {
        Vehicle vehicle1 = newTestVehicle();

        Vehicle vehicle2 = newTestVehicle();
        vehicle2.setSystemNumber("A B C D E F");

        int primaryKey1 = vehicleRepository.partialUpsert(vehicle1);
        int primaryKey2 = vehicleRepository.partialUpsert(vehicle2);

        deleteOnExit.add(primaryKey1);
        deleteOnExit.add(primaryKey2);

        assertNotEquals(primaryKey1, primaryKey2);
    }

    private Vehicle newTestVehicle() {
        Vehicle vehicle = new Vehicle();

        vehicle.setSystemNumber("SYSTEM-NUMBER");
        vehicle.setVin("VIN");
        vehicle.setVrm_trm("999999999");
        vehicle.setTrailerID("88888888");

        return vehicle;
    }
}
