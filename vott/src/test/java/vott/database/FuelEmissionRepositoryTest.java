package vott.database;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import vott.database.connection.ConnectionFactory;
import vott.database.connection.DatabaseConfiguration;
import vott.models.dao.FuelEmission;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class FuelEmissionRepositoryTest {

    private List<Integer> deleteOnExit;

    private FuelEmissionRepository fuelEmissionRepository;

    @Before
    public void setUp() {
        ConnectionFactory connectionFactory = new ConnectionFactory(
                DatabaseConfiguration.provide()
        );

        fuelEmissionRepository = new FuelEmissionRepository(connectionFactory);

        deleteOnExit = new ArrayList<>();
    }

    @After
    public void tearDown() {
        for (int primaryKey : deleteOnExit) {
            fuelEmissionRepository.delete(primaryKey);
        }
    }

    @Test
    public void upsertingIdenticalDataReturnsSamePk() {
        int primaryKey1 = fuelEmissionRepository.partialUpsert(newTestFuelEmission());
        int primaryKey2 = fuelEmissionRepository.partialUpsert(newTestFuelEmission());

        deleteOnExit.add(primaryKey1);
        deleteOnExit.add(primaryKey2);

        assertEquals(primaryKey1, primaryKey2);
    }

    @Test
    public void upsertingNewDataReturnsDifferentPk() {
        FuelEmission fe1 = newTestFuelEmission();

        FuelEmission fe2 = newTestFuelEmission();
        fe2.setEmissionStandard("Another Standard");

        int primaryKey1 = fuelEmissionRepository.partialUpsert(fe1);
        int primaryKey2 = fuelEmissionRepository.partialUpsert(fe2);

        deleteOnExit.add(primaryKey1);
        deleteOnExit.add(primaryKey2);

        assertNotEquals(primaryKey1, primaryKey2);
    }

    private FuelEmission newTestFuelEmission() {
        FuelEmission fe = new FuelEmission();

        fe.setModTypeCode("a");
        fe.setDescription("Test Description");
        fe.setEmissionStandard("Test Standard");
        fe.setFuelType("Petrol");

        return fe;
    }
}
