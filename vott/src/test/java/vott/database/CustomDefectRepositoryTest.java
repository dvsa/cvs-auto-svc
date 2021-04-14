package vott.database;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import vott.database.connection.ConnectionFactory;
import vott.database.connection.DatabaseConfiguration;
import vott.models.dao.CustomDefect;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotEquals;

public class CustomDefectRepositoryTest {

    private List<Integer> deleteOnExit;

    private CustomDefectRepository customDefectRepository;

    @Before
    public void setUp() {
        ConnectionFactory connectionFactory = new ConnectionFactory(
                DatabaseConfiguration.provide()
        );

        customDefectRepository = new CustomDefectRepository(connectionFactory);

        deleteOnExit = new ArrayList<>();
    }

    @After
    public void tearDown() {
        for (int primaryKey : deleteOnExit) {
            customDefectRepository.delete(primaryKey);
        }
    }

    @Test
    public void upsertingIdenticalDataReturnsDifferentPk() {
        int primaryKey1 = customDefectRepository.fullUpsert(newTestCustomDefect());
        int primaryKey2 = customDefectRepository.fullUpsert(newTestCustomDefect());

        deleteOnExit.add(primaryKey1);
        deleteOnExit.add(primaryKey2);

        assertNotEquals(primaryKey1, primaryKey2);
    }

    @Test
    public void upsertingNewTestResultIDReturnsDifferentPk() {
        CustomDefect cd1 = newTestCustomDefect();

        CustomDefect cd2 = newTestCustomDefect();
        cd2.setTestResultID("2");

        int primaryKey1 = customDefectRepository.fullUpsert(cd1);
        int primaryKey2 = customDefectRepository.fullUpsert(cd2);

        deleteOnExit.add(primaryKey1);
        deleteOnExit.add(primaryKey2);

        assertNotEquals(primaryKey1, primaryKey2);
    }

    @Test
    public void upsertingIdenticalIndexReturnsSamePk() {
        CustomDefect cd1 = newTestCustomDefect();

        CustomDefect cd2 = newTestCustomDefect();
        cd2.setReferenceNumber("555555");

        int primaryKey1 = customDefectRepository.fullUpsert(cd1);
        int primaryKey2 = customDefectRepository.fullUpsert(cd2);

        deleteOnExit.add(primaryKey1);
        deleteOnExit.add(primaryKey2);

        assertNotEquals(primaryKey1, primaryKey2);
    }

    private CustomDefect newTestCustomDefect() {
        CustomDefect cd = new CustomDefect();

        cd.setTestResultID("1");
        cd.setReferenceNumber("444444");
        cd.setDefectName("Test Custom Defect");
        cd.setDefectNotes("Test Custom Defect Notes");

        return cd;
    }
}
