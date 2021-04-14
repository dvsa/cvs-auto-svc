package vott.database;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import vott.database.connection.ConnectionFactory;
import vott.database.connection.DatabaseConfiguration;
import vott.models.dao.MakeModel;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class MakeModelRepositoryTest {
    private List<Integer> deleteOnExit;

    private MakeModelRepository makeModelRepository;

    @Before
    public void setUp() {
        ConnectionFactory connectionFactory = new ConnectionFactory(
                DatabaseConfiguration.provide()
        );

        makeModelRepository = new MakeModelRepository(connectionFactory);

        deleteOnExit = new ArrayList<>();
    }

    @After
    public void tearDown() {
        for (int primaryKey : deleteOnExit) {
            makeModelRepository.delete(primaryKey);
        }
    }

    @Test
    public void upsertingIdenticalDataReturnsSamePk() {
        int primaryKey1 = makeModelRepository.partialUpsert(newTestMakeModel());
        int primaryKey2 = makeModelRepository.partialUpsert(newTestMakeModel());

        deleteOnExit.add(primaryKey1);
        deleteOnExit.add(primaryKey2);

        assertEquals(primaryKey1, primaryKey2);
    }

    @Test
    public void upsertingNewDataReturnsDifferentPk() {
        MakeModel mm1 = newTestMakeModel();

        MakeModel mm2 = newTestMakeModel();
        mm2.setMake("Test Make mk2");

        int primaryKey1 = makeModelRepository.partialUpsert(mm1);
        int primaryKey2 = makeModelRepository.partialUpsert(mm2);

        deleteOnExit.add(primaryKey1);
        deleteOnExit.add(primaryKey2);

        assertNotEquals(primaryKey1, primaryKey2);
    }

    private MakeModel newTestMakeModel() {
        MakeModel mm = new MakeModel();

        mm.setMake("Test Make");
        mm.setModel("Test Model");
        mm.setChassisMake("Test Chassis Make");
        mm.setChassisModel("Test Chassis Model");
        mm.setBodyMake("Test Body Make");
        mm.setBodyModel("Test Body Model");
        mm.setModelLiteral("Test Model Literal");
        mm.setBodyTypeCode("1");
        mm.setBodyTypeDescription("Test Description");
        mm.setFuelPropulsionSystem("Test Fuel");
        mm.setDtpCode("888888");

        return mm;
    }
}
