package vott.database;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import vott.database.connection.ConnectionFactory;
import vott.database.connection.DatabaseConfiguration;
import vott.models.dao.TestDefect;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotEquals;

public class TestDefectRepositoryTest {

    private List<Integer> deleteOnExit;

    private TestDefectRepository testDefectRepository;

    @Before
    public void setUp() {
        ConnectionFactory connectionFactory = new ConnectionFactory(
                DatabaseConfiguration.provide()
        );

        testDefectRepository = new TestDefectRepository(connectionFactory);

        deleteOnExit = new ArrayList<>();
    }

    @After
    public void tearDown() {
        for (int primaryKey : deleteOnExit) {
            testDefectRepository.delete(primaryKey);
        }
    }

    @Test
    public void upsertingIdenticalDataReturnsNewPk() {
        int primaryKey1 = testDefectRepository.fullUpsert(newTestTestDefect());
        int primaryKey2 = testDefectRepository.fullUpsert(newTestTestDefect());

        deleteOnExit.add(primaryKey1);
        deleteOnExit.add(primaryKey2);

        assertNotEquals(primaryKey1, primaryKey2);
    }

    @Test
    public void upsertingNewTestResultIDReturnsDifferentPk() {
        TestDefect td1 = newTestTestDefect();

        TestDefect td2 = newTestTestDefect();
        td2.setTestResultID("2");

        int primaryKey1 = testDefectRepository.fullUpsert(td1);
        int primaryKey2 = testDefectRepository.fullUpsert(td2);

        deleteOnExit.add(primaryKey1);
        deleteOnExit.add(primaryKey2);

        assertNotEquals(primaryKey1, primaryKey2);
    }

    @Test
    public void upsertingNewDefectIDReturnsDifferentPk() {
        TestDefect td1 = newTestTestDefect();

        TestDefect td2 = newTestTestDefect();
        td2.setDefectID("2");

        int primaryKey1 = testDefectRepository.fullUpsert(td1);
        int primaryKey2 = testDefectRepository.fullUpsert(td2);

        deleteOnExit.add(primaryKey1);
        deleteOnExit.add(primaryKey2);

        assertNotEquals(primaryKey1, primaryKey2);
    }

    @Test
    public void upsertingNewLocationIDReturnsDifferentPk() {
        TestDefect td1 = newTestTestDefect();

        TestDefect td2 = newTestTestDefect();
        td2.setLocationID("2");

        int primaryKey1 = testDefectRepository.fullUpsert(td1);
        int primaryKey2 = testDefectRepository.fullUpsert(td2);

        deleteOnExit.add(primaryKey1);
        deleteOnExit.add(primaryKey2);

        assertNotEquals(primaryKey1, primaryKey2);
    }

    @Test
    public void upsertingNewNonIndexDataReturnsDifferentPk() {
        TestDefect td1 = newTestTestDefect();

        TestDefect td2 = newTestTestDefect();
        td2.setNotes("Test Notes Updated");

        int primaryKey1 = testDefectRepository.fullUpsert(td1);
        int primaryKey2 = testDefectRepository.fullUpsert(td2);

        deleteOnExit.add(primaryKey1);
        deleteOnExit.add(primaryKey2);

        assertNotEquals(primaryKey1, primaryKey2);
    }

    private TestDefect newTestTestDefect() {
        TestDefect td = new TestDefect();

        td.setTestResultID("1");
        td.setDefectID("1");
        td.setLocationID("1");
        td.setNotes("Test Notes");
        td.setPrs("1");
        td.setProhibitionIssued("1");

        return td;
    }
}
