package vott.database;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import vott.database.connection.ConnectionFactory;
import vott.database.connection.DatabaseConfiguration;
import vott.models.dao.Preparer;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class PreparerRepositoryTest {

    private List<Integer> deleteOnExit;

    private PreparerRepository preparerRepository;

    @Before
    public void setUp() {
        ConnectionFactory connectionFactory = new ConnectionFactory(
                DatabaseConfiguration.provide()
        );

        preparerRepository = new PreparerRepository(connectionFactory);

        deleteOnExit = new ArrayList<>();
    }

    @After
    public void tearDown() {
        for (int primaryKey : deleteOnExit) {
            preparerRepository.delete(primaryKey);
        }
    }

    @Test
    public void upsertingIdenticalDataReturnsSamePk() {
        int primaryKey1 = preparerRepository.partialUpsert(newTestPreparer());
        int primaryKey2 = preparerRepository.partialUpsert(newTestPreparer());

        deleteOnExit.add(primaryKey1);
        deleteOnExit.add(primaryKey2);

        assertEquals(primaryKey1, primaryKey2);
    }

    @Test
    public void upsertingNewDataReturnsDifferentPk() {
        Preparer preparer1 = newTestPreparer();

        Preparer preparer2 = newTestPreparer();
        preparer2.setName("Auto Test Name");

        int primaryKey1 = preparerRepository.partialUpsert(preparer1);
        int primaryKey2 = preparerRepository.partialUpsert(preparer2);

        deleteOnExit.add(primaryKey1);
        deleteOnExit.add(primaryKey2);

        assertNotEquals(primaryKey1, primaryKey2);
    }

    private Preparer newTestPreparer() {
        Preparer preparer = new Preparer();

        preparer.setPreparerID("1");
        preparer.setName("Test Name");

        return preparer;
    }
}
