package vott.database;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import vott.database.connection.ConnectionFactory;
import vott.database.connection.DatabaseConfiguration;
import vott.models.dao.TestType;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class TestTypeRepositoryTest {

    private List<Integer> deleteOnExit;

    private TestTypeRepository testTypeRepository;

    @Before
    public void setUp() {
        ConnectionFactory connectionFactory = new ConnectionFactory(
                DatabaseConfiguration.provide()
        );

        testTypeRepository = new TestTypeRepository(connectionFactory);

        deleteOnExit = new ArrayList<>();
    }

    @After
    public void tearDown() {
        for (int primaryKey : deleteOnExit) {
            testTypeRepository.delete(primaryKey);
        }
    }

    @Test
    public void upsertingIdenticalDataReturnsSamePk() {
        int primaryKey1 = testTypeRepository.partialUpsert(newTestTestType());
        int primaryKey2 = testTypeRepository.partialUpsert(newTestTestType());

        deleteOnExit.add(primaryKey1);
        deleteOnExit.add(primaryKey2);

        assertEquals(primaryKey1, primaryKey2);
    }

    @Test
    public void upsertingNewDataReturnsDifferentPk() {
        TestType tt1 = newTestTestType();

        TestType tt2 = newTestTestType();
        tt2.setTestTypeClassification("Auto Test Type");

        int primaryKey1 = testTypeRepository.partialUpsert(tt1);
        int primaryKey2 = testTypeRepository.partialUpsert(tt2);

        deleteOnExit.add(primaryKey1);
        deleteOnExit.add(primaryKey2);

        assertNotEquals(primaryKey1, primaryKey2);
    }

    private TestType newTestTestType() {
        TestType tt = new TestType();

        tt.setTestTypeClassification("Test Test Type");
        tt.setTestTypeName("Test Name");

        return tt;
    }
}
