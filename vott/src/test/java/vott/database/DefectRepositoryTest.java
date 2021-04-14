package vott.database;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import vott.database.connection.ConnectionFactory;
import vott.database.connection.DatabaseConfiguration;
import vott.models.dao.Defect;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class DefectRepositoryTest {

    private List<Integer> deleteOnExit;

    private DefectRepository defectRepository;

    @Before
    public void setUp() {
        ConnectionFactory connectionFactory = new ConnectionFactory(
                DatabaseConfiguration.provide()
        );

        defectRepository = new DefectRepository(connectionFactory);

        deleteOnExit = new ArrayList<>();
    }

    @After
    public void tearDown() {
        for (int primaryKey : deleteOnExit) {
            defectRepository.delete(primaryKey);
        }
    }

    @Test
    public void upsertingIdenticalDataReturnsSamePk() {
        int primaryKey1 = defectRepository.partialUpsert(newTestDefect());
        int primaryKey2 = defectRepository.partialUpsert(newTestDefect());

        deleteOnExit.add(primaryKey1);
        deleteOnExit.add(primaryKey2);

        assertEquals(primaryKey1, primaryKey2);
    }

    @Test
    public void upsertingNewDataReturnsDifferentPk() {
        Defect defect1 = newTestDefect();

        Defect defect2 = newTestDefect();
        defect2.setImNumber("456");

        int primaryKey1 = defectRepository.partialUpsert(defect1);
        int primaryKey2 = defectRepository.partialUpsert(defect2);

        deleteOnExit.add(primaryKey1);
        deleteOnExit.add(primaryKey2);

        assertNotEquals(primaryKey1, primaryKey2);
    }

    private Defect newTestDefect() {
        Defect defect = new Defect();

        defect.setImNumber("123");
        defect.setImDescription("Test IM Description");
        defect.setItemNumber("5555");
        defect.setItemDescription("Test Item Description");
        defect.setDeficiencyRef("Test Reference");
        defect.setDeficiencyID("1");
        defect.setDeficiencySubID("444");
        defect.setDeficiencyCategory("Category");
        defect.setDeficiencyText("Test Test");
        defect.setStdForProhibition("1");

        return defect;
    }
}
