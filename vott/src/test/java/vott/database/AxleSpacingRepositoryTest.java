package vott.database;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import vott.database.connection.ConnectionFactory;
import vott.database.connection.DatabaseConfiguration;
import vott.models.dao.AxleSpacing;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class AxleSpacingRepositoryTest {

    private List<Integer> deleteOnExit;

    private AxleSpacingRepository axleSpacingRepository;

    @Before
    public void setUp() {
        ConnectionFactory connectionFactory = new ConnectionFactory(
                DatabaseConfiguration.provide()
        );

        axleSpacingRepository = new AxleSpacingRepository(connectionFactory);

        deleteOnExit = new ArrayList<>();
    }

    @After
    public void tearDown() {
        for (int primaryKey : deleteOnExit) {
            axleSpacingRepository.delete(primaryKey);
        }
    }

    @Test
    public void upsertingIdenticalDataReturnsSamePk() {
        int primaryKey1 = axleSpacingRepository.fullUpsert(newTestAxleSpacing());
        int primaryKey2 = axleSpacingRepository.fullUpsert(newTestAxleSpacing());

        deleteOnExit.add(primaryKey1);
        deleteOnExit.add(primaryKey2);

        assertEquals(primaryKey1, primaryKey2);
    }

    @Test
    public void upsertingDifferentTechRecordIDReturnsDifferentPk() {
        AxleSpacing as1 = newTestAxleSpacing();

        AxleSpacing as2 = newTestAxleSpacing();
        as2.setTechnicalRecordID("2");

        int primaryKey1 = axleSpacingRepository.fullUpsert(as1);
        int primaryKey2 = axleSpacingRepository.fullUpsert(as2);

        deleteOnExit.add(primaryKey1);
        deleteOnExit.add(primaryKey2);

        assertNotEquals(primaryKey1, primaryKey2);
    }

    @Test
    public void upsertingDifferentAxlesReturnsDifferentPk() {
        AxleSpacing as1 = newTestAxleSpacing();

        AxleSpacing as2 = newTestAxleSpacing();
        as2.setAxles("Test2");

        int primaryKey1 = axleSpacingRepository.fullUpsert(as1);
        int primaryKey2 = axleSpacingRepository.fullUpsert(as2);

        deleteOnExit.add(primaryKey1);
        deleteOnExit.add(primaryKey2);

        assertNotEquals(primaryKey1, primaryKey2);
    }

    @Test
    public void upsertingIdenticalIndexValuesReturnsSamePk() {
        AxleSpacing as1 = newTestAxleSpacing();

        AxleSpacing as2 = newTestAxleSpacing();
        as2.setValue("50");

        int primaryKey1 = axleSpacingRepository.fullUpsert(as1);
        int primaryKey2 = axleSpacingRepository.fullUpsert(as2);

        deleteOnExit.add(primaryKey1);
        deleteOnExit.add(primaryKey2);

        assertEquals(primaryKey1, primaryKey2);
    }

    private AxleSpacing newTestAxleSpacing() {
        AxleSpacing as = new AxleSpacing();

        as.setTechnicalRecordID("1");
        as.setAxles("Test");
        as.setValue("120");

        return as;
    }
}
