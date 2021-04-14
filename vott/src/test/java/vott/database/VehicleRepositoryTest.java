package vott.database;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import vott.database.connection.ConnectionFactory;
import vott.database.connection.DatabaseConfiguration;
import vott.models.dao.Vehicle;

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
            DatabaseConfiguration.provide()
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
        int primaryKey1 = vehicleRepository.fullUpsert(newTestVehicle());
        int primaryKey2 = vehicleRepository.fullUpsert(newTestVehicle());

        deleteOnExit.add(primaryKey1);
        deleteOnExit.add(primaryKey2);

        assertEquals(primaryKey1, primaryKey2);
    }

    @Test
    public void upsertingDifferentSystemNumberReturnsDifferentPk() {
        Vehicle vehicle1 = newTestVehicle();

        Vehicle vehicle2 = newTestVehicle();
        vehicle2.setSystemNumber("A B C D E F");

        int primaryKey1 = vehicleRepository.fullUpsert(vehicle1);
        int primaryKey2 = vehicleRepository.fullUpsert(vehicle2);

        deleteOnExit.add(primaryKey1);
        deleteOnExit.add(primaryKey2);

        assertNotEquals(primaryKey1, primaryKey2);
    }

    @Test
    public void upsertingDifferentVINReturnsDifferentPk() {
        Vehicle vehicle1 = newTestVehicle();

        Vehicle vehicle2 = newTestVehicle();
        vehicle2.setVin("Vin Updated");

        int primaryKey1 = vehicleRepository.fullUpsert(vehicle1);
        int primaryKey2 = vehicleRepository.fullUpsert(vehicle2);

        deleteOnExit.add(primaryKey1);
        deleteOnExit.add(primaryKey2);

        assertNotEquals(primaryKey1, primaryKey2);
    }

    @Test
    public void upsertingIdenticalIndexValuesReturnsSamePk() {
        Vehicle vehicle1 = newTestVehicle();

        Vehicle vehicle2 = newTestVehicle();
        vehicle2.setVrm_trm("7777");
        vehicle2.setTrailerID("7777");

        int primaryKey1 = vehicleRepository.fullUpsert(vehicle1);
        int primaryKey2 = vehicleRepository.fullUpsert(vehicle2);

        deleteOnExit.add(primaryKey1);
        deleteOnExit.add(primaryKey2);

        assertEquals(primaryKey1, primaryKey2);
    }

    private Vehicle newTestVehicle() {
        Vehicle vehicle = new Vehicle();

        vehicle.setSystemNumber("SYSTEM-NUMBER");
        vehicle.setVin("Test VIN");
        vehicle.setVrm_trm("999999999");
        vehicle.setTrailerID("88888888");

        return vehicle;
    }
}
