package vott.database;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import vott.database.connection.ConnectionFactory;
import vott.database.connection.DatabaseConfiguration;
import vott.models.dao.Microfilm;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class MicrofilmRepositoryTest {

    private List<Integer> deleteOnExit;

    private MicrofilmRepository microfilmRepository;

    @Before
    public void setUp() {
        ConnectionFactory connectionFactory = new ConnectionFactory(
                DatabaseConfiguration.provide()
        );

        microfilmRepository = new MicrofilmRepository(connectionFactory);

        deleteOnExit = new ArrayList<>();
    }

    @After
    public void tearDown() {
        for (int primaryKey : deleteOnExit) {
            microfilmRepository.delete(primaryKey);
        }
    }

    @Test
    public void upsertingIdenticalDataReturnsSamePk() {
        int primaryKey1 = microfilmRepository.fullUpsert(newTestMicrofilm());
        int primaryKey2 = microfilmRepository.fullUpsert(newTestMicrofilm());

        deleteOnExit.add(primaryKey1);
        deleteOnExit.add(primaryKey2);

        assertEquals(primaryKey1, primaryKey2);
    }

    @Test
    public void upsertingNewTechRecordIDReturnsDifferentPk() {
        Microfilm mf1 = newTestMicrofilm();

        Microfilm mf2 = newTestMicrofilm();
        mf2.setTechnicalRecordID("2");

        int primaryKey1 = microfilmRepository.fullUpsert(mf1);
        int primaryKey2 = microfilmRepository.fullUpsert(mf2);

        deleteOnExit.add(primaryKey1);
        deleteOnExit.add(primaryKey2);

        assertNotEquals(primaryKey1, primaryKey2);
    }

    @Test
    public void upsertingIdenticalIndexValuesReturnsSamePk() {
        Microfilm mf1 = newTestMicrofilm();

        Microfilm mf2 = newTestMicrofilm();
        mf2.setMicrofilmRollNumber("7777");

        int primaryKey1 = microfilmRepository.fullUpsert(mf1);
        int primaryKey2 = microfilmRepository.fullUpsert(mf2);

        deleteOnExit.add(primaryKey1);
        deleteOnExit.add(primaryKey2);

        assertEquals(primaryKey1, primaryKey2);
    }

    private Microfilm newTestMicrofilm() {
        Microfilm mf = new Microfilm();

        mf.setTechnicalRecordID("1");
        mf.setMicrofilmDocumentType("Test Document Type");
        mf.setMicrofilmRollNumber("8888");
        mf.setMicrofilmSerialNumber("1234");

        return mf;
    }
}
