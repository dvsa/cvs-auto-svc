package vott.database;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import vott.database.connection.ConnectionFactory;
import vott.database.connection.DatabaseConfiguration;
import vott.models.dao.TestStation;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class TestStationRepositoryTest {
    private List<Integer> deleteOnExit;

    private TestStationRepository testStationRepository;

    @Before
    public void setUp() {
        ConnectionFactory connectionFactory = new ConnectionFactory(
                DatabaseConfiguration.provide()
        );

        testStationRepository = new TestStationRepository(connectionFactory);

        deleteOnExit = new ArrayList<>();
    }

    @After
    public void tearDown() {
        for (int primaryKey : deleteOnExit) {
            testStationRepository.delete(primaryKey);
        }
    }

    @Test
    public void upsertingIdenticalDataReturnsSamePk() {
        int primaryKey1 = testStationRepository.partialUpsert(newTestTestStation());
        int primaryKey2 = testStationRepository.partialUpsert(newTestTestStation());

        deleteOnExit.add(primaryKey1);
        deleteOnExit.add(primaryKey2);

        assertEquals(primaryKey1, primaryKey2);
    }

    @Test
    public void upsertingNewDataReturnsDifferentPk() {
        TestStation ts1 = newTestTestStation();

        TestStation ts2 = newTestTestStation();
        ts2.setPNumber("123456789");

        int primaryKey1 = testStationRepository.partialUpsert(ts1);
        int primaryKey2 = testStationRepository.partialUpsert(ts2);

        deleteOnExit.add(primaryKey1);
        deleteOnExit.add(primaryKey2);

        assertNotEquals(primaryKey1, primaryKey2);
    }

    private TestStation newTestTestStation() {
        TestStation ts = new TestStation();

        ts.setPNumber("987654321");
        ts.setName("Test Test Station");
        ts.setType("Test");

        return ts;
    }
}
