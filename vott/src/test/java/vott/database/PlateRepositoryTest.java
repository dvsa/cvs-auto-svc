package vott.database;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import vott.database.connection.ConnectionFactory;
import vott.database.connection.DatabaseConfiguration;
import vott.models.dao.Plate;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class PlateRepositoryTest {
    private List<Integer> deleteOnExit;

    private PlateRepository plateRepository;

    @Before
    public void setUp() {
        ConnectionFactory connectionFactory = new ConnectionFactory(
                DatabaseConfiguration.provide()
        );

        plateRepository = new PlateRepository(connectionFactory);

        deleteOnExit = new ArrayList<>();
    }

    @After
    public void tearDown() {
        for (int primaryKey : deleteOnExit) {
            plateRepository.delete(primaryKey);
        }
    }

    @Test
    public void upsertingIdenticalDataReturnsSamePk() {
        int primaryKey1 = plateRepository.fullUpsert(newTestPlate());
        int primaryKey2 = plateRepository.fullUpsert(newTestPlate());

        deleteOnExit.add(primaryKey1);
        deleteOnExit.add(primaryKey2);

        assertEquals(primaryKey1, primaryKey2);
    }

    @Test
    public void upsertingNewTechRecordIDReturnsDifferentPk() {
        Plate plate1 = newTestPlate();

        Plate plate2 = newTestPlate();
        plate2.setTechnicalRecordID("2");

        int primaryKey1 = plateRepository.fullUpsert(plate1);
        int primaryKey2 = plateRepository.fullUpsert(plate2);

        deleteOnExit.add(primaryKey1);
        deleteOnExit.add(primaryKey2);

        assertNotEquals(primaryKey1, primaryKey2);
    }

    @Test
    public void upsertingNewPlateSerialNumReturnsDifferentPk() {
        Plate plate1 = newTestPlate();

        Plate plate2 = newTestPlate();
        plate2.setPlateSerialNumber("777777");

        int primaryKey1 = plateRepository.fullUpsert(plate1);
        int primaryKey2 = plateRepository.fullUpsert(plate2);

        deleteOnExit.add(primaryKey1);
        deleteOnExit.add(primaryKey2);

        assertNotEquals(primaryKey1, primaryKey2);
    }

    @Test
    public void upsertingNewPlateIssueDateReturnsDifferentPk() {
        Plate plate1 = newTestPlate();

        Plate plate2 = newTestPlate();
        plate2.setPlateIssueDate("2100-01-01");

        int primaryKey1 = plateRepository.fullUpsert(plate1);
        int primaryKey2 = plateRepository.fullUpsert(plate2);

        deleteOnExit.add(primaryKey1);
        deleteOnExit.add(primaryKey2);

        assertNotEquals(primaryKey1, primaryKey2);
    }

    @Test
    public void upsertingIdenticalIndexValuesReturnsSamePk() {
        Plate plate1 = newTestPlate();

        Plate plate2 = newTestPlate();
        plate2.setPlateIssuer("Test Update");

        int primaryKey1 = plateRepository.fullUpsert(plate1);
        int primaryKey2 = plateRepository.fullUpsert(plate2);

        deleteOnExit.add(primaryKey1);
        deleteOnExit.add(primaryKey2);

        assertEquals(primaryKey1, primaryKey2);
    }

    private Plate newTestPlate() {
        Plate plate = new Plate();

        plate.setTechnicalRecordID("1");
        plate.setPlateSerialNumber("666666");
        plate.setPlateIssueDate("2100-12-31");
        plate.setPlateReasonForIssue("Test Reason");
        plate.setPlateIssuer("Auto Test");

        return plate;
    }
}
