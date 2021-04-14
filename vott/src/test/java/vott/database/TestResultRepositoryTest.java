package vott.database;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import vott.database.connection.ConnectionFactory;
import vott.database.connection.DatabaseConfiguration;
import vott.models.dao.TestResult;
import vott.models.dao.TestType;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class TestResultRepositoryTest {

    private List<Integer> deleteOnExit;

    private TestResultRepository testResultRepository;
    private TestTypeRepository testTypeRepository;

    @Before
    public void setUp() {
        ConnectionFactory connectionFactory = new ConnectionFactory(
                DatabaseConfiguration.provide()
        );

        testResultRepository = new TestResultRepository(connectionFactory);
        testTypeRepository = new TestTypeRepository(connectionFactory);

        deleteOnExit = new ArrayList<>();
    }

    @After
    public void tearDown() {
        for (int primaryKey : deleteOnExit) {
            testResultRepository.delete(primaryKey);
        }
    }

    @Test
    public void upsertingIdenticalDataReturnsSamePk() {
        int primaryKey1 = testResultRepository.fullUpsert(newTestTestResult());
        int primaryKey2 = testResultRepository.fullUpsert(newTestTestResult());

        deleteOnExit.add(primaryKey1);
        deleteOnExit.add(primaryKey2);

        assertEquals(primaryKey1, primaryKey2);
    }

    @Test
    public void upsertingNewVehicleIDReturnsDifferentPk() {
        TestResult tr1 = newTestTestResult();

        TestResult tr2 = newTestTestResult();
        tr2.setVehicleID("2");

        int primaryKey1 = testResultRepository.fullUpsert(tr1);
        int primaryKey2 = testResultRepository.fullUpsert(tr2);

        deleteOnExit.add(primaryKey1);
        deleteOnExit.add(primaryKey2);

        assertNotEquals(primaryKey1, primaryKey2);
    }

    @Test
    public void upsertingNewTestTypeIDReturnsDifferentPk() {
        TestType tt = newTestTestType();

        int ttpk = testTypeRepository.partialUpsert(tt);

        TestResult tr1 = newTestTestResult();

        TestResult tr2 = newTestTestResult();
        tr2.setTestTypeID(Integer.toString(ttpk));

        int primaryKey1 = testResultRepository.fullUpsert(tr1);
        int primaryKey2 = testResultRepository.fullUpsert(tr2);

        deleteOnExit.add(primaryKey1);
        deleteOnExit.add(primaryKey2);

        for (int primaryKey : deleteOnExit) {
            testResultRepository.delete(primaryKey);
        }

        testTypeRepository.delete(ttpk);

        assertNotEquals(primaryKey1, primaryKey2);
    }

    @Test
    public void upsertingNewCreatedAtReturnsDifferentPk() {
        TestResult tr1 = newTestTestResult();

        TestResult tr2 = newTestTestResult();
        tr2.setCreatedAt("2021-12-31 00:00:00");

        int primaryKey1 = testResultRepository.fullUpsert(tr1);
        int primaryKey2 = testResultRepository.fullUpsert(tr2);

        deleteOnExit.add(primaryKey1);
        deleteOnExit.add(primaryKey2);

        assertNotEquals(primaryKey1, primaryKey2);
    }

    @Test
    public void upsertingIdenticalIndexValuesReturnsSamePk() {
        TestResult tr1 = newTestTestResult();

        TestResult tr2 = newTestTestResult();
        tr2.setTestNumber("55555");

        int primaryKey1 = testResultRepository.fullUpsert(tr1);
        int primaryKey2 = testResultRepository.fullUpsert(tr2);

        deleteOnExit.add(primaryKey1);
        deleteOnExit.add(primaryKey2);

        assertEquals(primaryKey1, primaryKey2);
    }

    private TestResult newTestTestResult() {
        TestResult tr = new TestResult();

        tr.setVehicleID("1");
        tr.setFuelEmissionID("1");
        tr.setTestStationID("1");
        tr.setTesterID("1");
        tr.setPreparerID("1");
        tr.setVehicleClassID("1");
        tr.setTestTypeID("1");
        tr.setTestStatus("Test Pass");
        tr.setReasonForCancellation("Automation Test Run");
        tr.setNumberOfSeats("3");
        tr.setOdometerReading("900");
        tr.setOdometerReadingUnits("Test Units");
        tr.setCountryOfRegistration("Test Country");
        tr.setNoOfAxles("4");
        tr.setRegnDate("2100-12-31");
        tr.setFirstUseDate("2100-12-31");
        tr.setCreatedAt("2021-01-01 00:00:00");
        tr.setLastUpdatedAt("2021-01-01 00:00:00");
        tr.setTestCode("111");
        tr.setTestNumber("A111B222");
        tr.setCertificateNumber("A111B222");
        tr.setSecondaryCertificateNumber("A111B222");
        tr.setTestExpiryDate("2022-01-01");
        tr.setTestAnniversaryDate("2022-01-01");
        tr.setTestTypeStartTimestamp("2022-01-01 00:00:00");
        tr.setTestTypeEndTimestamp("2022-01-01 00:00:00");
        tr.setNumberOfSeatbeltsFitted("2");
        tr.setLastSeatbeltInstallationCheckDate("2022-01-01");
        tr.setSeatbeltInstallationCheckDate("1");
        tr.setTestResult("Auto Test");
        tr.setReasonForAbandoning("Test Automation Run");
        tr.setAdditionalNotesRecorded("Additional Test Notes");
        tr.setAdditionalCommentsForAbandon("Additional Test Comments");
        tr.setParticulateTrapFitted("Particulate Test");
        tr.setParticulateTrapSerialNumber("ABC123");
        tr.setModificationTypeUsed("Test Modification");
        tr.setSmokeTestKLimitApplied("Smoke Test");
        tr.setCreatedByID("1");
        tr.setLastUpdatedByID("1");

        return tr;
    }

    private TestType newTestTestType() {
        TestType tt = new TestType();

        tt.setTestTypeClassification("Test Test Type");
        tt.setTestTypeName("Test Name");

        return tt;
    }
}
