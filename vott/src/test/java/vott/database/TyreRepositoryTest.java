package vott.database;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import vott.database.connection.ConnectionFactory;
import vott.database.connection.DatabaseConfiguration;
import vott.models.dao.Tyre;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class TyreRepositoryTest {

    private List<Integer> deleteOnExit;

    private TyreRepository tyreRepository;

    @Before
    public void setUp() {
        ConnectionFactory connectionFactory = new ConnectionFactory(
                DatabaseConfiguration.provide()
        );

        tyreRepository = new TyreRepository(connectionFactory);

        deleteOnExit = new ArrayList<>();
    }

    @After
    public void tearDown() {
        for (int primaryKey : deleteOnExit) {
            tyreRepository.delete(primaryKey);
        }
    }

    @Test
    public void upsertingIdenticalDataReturnsSamePk() {
        int primaryKey1 = tyreRepository.partialUpsert(newTestTyre());
        int primaryKey2 = tyreRepository.partialUpsert(newTestTyre());

        deleteOnExit.add(primaryKey1);
        deleteOnExit.add(primaryKey2);

        assertEquals(primaryKey1, primaryKey2);
    }

    @Test
    public void upsertingNewDataReturnsDifferentPk() {
        Tyre tyre1 = newTestTyre();

        Tyre tyre2 = newTestTyre();
        tyre2.setTyreSize("222");

        int primaryKey1 = tyreRepository.partialUpsert(tyre1);
        int primaryKey2 = tyreRepository.partialUpsert(tyre2);

        deleteOnExit.add(primaryKey1);
        deleteOnExit.add(primaryKey2);

        assertNotEquals(primaryKey1, primaryKey2);
    }

    private Tyre newTestTyre() {
        Tyre tyre = new Tyre();

        tyre.setTyreSize("456");
        tyre.setPlyRating("10");
        tyre.setFitmentCode("55555");
        tyre.setDataTrAxles("Test Data");
        tyre.setSpeedCategorySymbol("1");
        tyre.setTyreCode("88888");

        return tyre;
    }
}
