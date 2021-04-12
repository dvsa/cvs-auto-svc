package vott.database;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import vott.databaseModels.Location;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class LocationRepositoryTest {

    private List<Integer> deleteOnExit;

    private LocationRepository locationRepository;

    @Before
    public void setUp() {
        ConnectionFactory connectionFactory = new ConnectionFactory(
                DatabaseConfiguration.robertWhitehouse()
        );

        locationRepository = new LocationRepository(connectionFactory);

        deleteOnExit = new ArrayList<>();
    }

    @After
    public void tearDown() {
        for (int primaryKey : deleteOnExit) {
            locationRepository.delete(primaryKey);
        }
    }

    @Test
    public void upsertingIdenticalDataReturnsSamePk() {
        int primaryKey1 = locationRepository.partialUpsert(newTestLocation());
        int primaryKey2 = locationRepository.partialUpsert(newTestLocation());

        deleteOnExit.add(primaryKey1);
        deleteOnExit.add(primaryKey2);

        assertEquals(primaryKey1, primaryKey2);
    }

    @Test
    public void upsertingNewDataReturnsDifferentPk() {
        Location location1 = newTestLocation();

        Location location2 = newTestLocation();
        location2.setVertical("Vert");

        int primaryKey1 = locationRepository.partialUpsert(location1);
        int primaryKey2 = locationRepository.partialUpsert(location2);

        deleteOnExit.add(primaryKey1);
        deleteOnExit.add(primaryKey2);

        assertNotEquals(primaryKey1, primaryKey2);
    }

    private Location newTestLocation() {
        Location location = new Location();

        location.setVertical("Test V");
        location.setHorizontal("Test H");
        location.setLateral("Test Lat");
        location.setLongitudinal("Test Long");
        location.setRowNumber("10");
        location.setSeatNumber("20");
        location.setAxleNumber("30");

        return location;
    }
}
