package vott.database;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import vott.database.connection.ConnectionFactory;
import vott.database.connection.DatabaseConfiguration;
import vott.models.dao.Identity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class IdentityRepositoryTest {

    private List<Integer> deleteOnExit;

    private IdentityRepository identityRepository;

    @Before
    public void setUp() {
        ConnectionFactory connectionFactory = new ConnectionFactory(
                DatabaseConfiguration.provide()
        );

        identityRepository = new IdentityRepository(connectionFactory);

        deleteOnExit = new ArrayList<>();
    }

    @After
    public void tearDown() {
        for (int primaryKey : deleteOnExit) {
            identityRepository.delete(primaryKey);
        }
    }

    @Test
    public void upsertingIdenticalDataReturnsSamePk() {
        int primaryKey1 = identityRepository.partialUpsert(newTestIdentity());
        int primaryKey2 = identityRepository.partialUpsert(newTestIdentity());

        deleteOnExit.add(primaryKey1);
        deleteOnExit.add(primaryKey2);

        assertEquals(primaryKey1, primaryKey2);
    }

    @Test
    public void upsertingNewDataReturnsDifferentPk() {
        Identity identity1 = newTestIdentity();

        Identity identity2 = newTestIdentity();
        identity1.setName("Another Name");

        int primaryKey1 = identityRepository.partialUpsert(identity1);
        int primaryKey2 = identityRepository.partialUpsert(identity2);

        deleteOnExit.add(primaryKey1);
        deleteOnExit.add(primaryKey2);

        assertNotEquals(primaryKey1, primaryKey2);
    }

    private Identity newTestIdentity() {
        Identity identity = new Identity();

        identity.setIdentityID("55555");
        identity.setName("Test Name");

        return identity;
    }
}

