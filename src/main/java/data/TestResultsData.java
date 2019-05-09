package data;

import model.testresults.*;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestResultsData {


    public static TestResultsGet.Builder buildTestResultsSubmittedDataWithCalculated() {
        TestResultsGet.Builder testResults = buildTestResultsSubmittedData();

        testResults.setVehicleId("BQ91YHQ").getTestTypes().get(0)
                .setCreatedAt("2019-02-22T08:50:16.706Z")
                .setLastUpdatedAt("2019-02-22T08:50:16.706Z")
                .setTestCode("pms")
                .setTestNumber("2")
                .setCertificateLink("http://dvsagov.co.uk");

        return testResults;
    }


    public static TestResultsGet.Builder buildTestResultsCancelleddDataWithCalculated() {
        TestResultsGet.Builder testResults = buildTestResultsCancelledData();

        testResults.setVehicleId("AA12BCD").getTestTypes().get(0)
                .setCreatedAt("2019-02-22T08:52:10.255Z")
                .setLastUpdatedAt("2019-02-22T08:52:10.255Z")
                .setTestCode("pms")
                .setTestNumber("3")
                .setCertificateLink("http://dvsagov.co.uk");

        return testResults;
    }

    public static TestResultsGet.Builder buildTestResultsSubmittedData() {


        Location location = new Location()
                .setVertical("upper")
                .setHorizontal(null)
                .setLateral("centre")
                .setLongitudinal(null)
                .setRowNumber(1)
                .setSeatNumber(2)
                .setAxleNumber(null);

        AdditionalInformation additionalInformation = new AdditionalInformation()
                .setLocation(location)
                .setNotes("seatbelt missing");


        Defects defects = new Defects()
                .setImNumber(3)
                .setImDescription("Seat Belts & Supplementary Restraint Systems")
                .setAdditionalInformation(additionalInformation)
                .setItemNumber(1)
                .setItemDescription("Obligatory Seat Belt:")
                .setDeficiencyRef("3.1.a")
                .setDeficiencyId("a")
                .setDeficiencySubId(null)
                .setDeficiencyCategory("major")
                .setDeficiencyText("missing.")
                .setStdForProhibition(false)
                .setPrs(false);


        TestTypesGet.Builder testTypes = new TestTypesGet.Builder()
                .setTestTypeName("Annual test")
                .setTestTypeId("19")
                .setCertificateNumber("12334")
                .setTestTypeStartTimestamp("2019-01-14T10:36:33.987Z")
                .setTestTypeEndTimestamp("2019-01-14T10:36:33.987Z")
                .setNumberOfSeatbeltsFitted(2)
                .setLastSeatbeltInstallationCheckDate("2019-01-14")
                .setSeatbeltInstallationCheckDate(true)
                .setTestResult("fail")
                .setProhibitionIssued(false)
                .setReasonForAbandoning("none")
                .setAdditionalNotesRecorded("VEHICLE FRONT ROW SECOND SEAT HAS MISSING SEATBELT")
                .setDefects(Arrays.asList(defects))
                .setName("Annual test")
                .setAdditionalCommentsForAbandon("none");

        VehicleClass vehicleClass = new VehicleClass()
                .setDescription("motorbikes over 200cc or with a sidecar")
                .setCode("2");

        return new TestResultsGet.Builder()
                .setVrm("BQ91YHQ")
                .setVin("1B7GG36N12S678410")
                .setTestResultId(RandomStringUtils.randomAlphanumeric(30))
                .setTestStationName("Larson, Nader and Okuneva")
                .setTestStationPNumber("84-926821")
                .setTestStationType("atf")
                .setTesterName("Gica")
                .setTesterStaffId("15")
                .setTesterEmailAddress("gica.petrescuf@dvsagov.uk")
                .setTestStartTimestamp("2019-01-14T10:36:33.987Z")
                .setTestEndTimestamp("2019-01-14T10:36:33.987Z")
                .setTestStatus("submitted")
                .setReasonForCancellation("none")
                .setVehicleClass(vehicleClass)
                .setVehicleType("psv")
                .setNumberOfSeats(45)
                .setNoOfAxles(2)
                .setVehicleConfiguration("rigid")
                .setOdometerReading(350000)
                .setOdometerReadingUnits("kilometres")
                .setPreparerId("bl5545")
                .setPreparerName("Durrell Vehicles Limited")
                .setEuVehicleCategory("m1")
                .setCountryOfRegistration("united kingdom")
                .setVehicleSize("small")
                .setTestTypes(Arrays.asList(testTypes.build()));

    }

    public static TestResultsGet.Builder buildTestResultsCancelledData() {
        Location location = new Location()
                .setVertical("upper")
                .setHorizontal(null)
                .setLateral("centre")
                .setLongitudinal(null)
                .setRowNumber(1)
                .setSeatNumber(2)
                .setAxleNumber(null);

        AdditionalInformation additionalInformation = new AdditionalInformation()
                .setLocation(location)
                .setNotes("seatbelt missing");

        Defects defects = new Defects()
                .setImNumber(3)
                .setImDescription("Seat Belts & Supplementary Restraint Systems")
                .setAdditionalInformation(additionalInformation)
                .setItemNumber(1)
                .setItemDescription("Obligatory Seat Belt:")
                .setDeficiencyRef("3.1.a")
                .setDeficiencyId("a")
                .setDeficiencySubId(null)
                .setDeficiencyCategory("major")
                .setDeficiencyText("missing.")
                .setStdForProhibition(false)
                .setPrs(false);

        TestTypesGet.Builder testTypes = new TestTypesGet.Builder()
                .setTestTypeName("Annual test")
                .setTestTypeId("19")
                .setCertificateNumber("12334")
                .setTestTypeStartTimestamp("2019-01-14T10:36:33.987Z")
                .setTestTypeEndTimestamp("2019-01-14T10:36:33.987Z")
                .setNumberOfSeatbeltsFitted(2)
                .setLastSeatbeltInstallationCheckDate("2019-01-14")
                .setSeatbeltInstallationCheckDate(true)
                .setTestResult("fail")
                .setProhibitionIssued(false)
                .setReasonForAbandoning("none")
                .setAdditionalNotesRecorded("VEHICLE FRONT ROW SECOND SEAT HAS MISSING SEATBELT")
                .setDefects(Arrays.asList(defects))
                .setName("Annual test")
                .setAdditionalCommentsForAbandon("none");

        VehicleClass vehicleClass = new VehicleClass()
                .setDescription("motorbikes over 200cc or with a sidecar")
                .setCode("2");

        return new TestResultsGet.Builder()
                .setVrm("AA12BCD")
                .setVin("XMGDE02FS0H012461")
                .setTestResultId(RandomStringUtils.randomAlphanumeric(30))
                .setTestStationName("Larson, Nader and Okuneva")
                .setTestStationPNumber("84-926821")
                .setTestStationType("atf")
                .setTesterName("Dorel")
                .setTesterStaffId("15")
                .setTesterEmailAddress("dorel.ionescu@dvsagov.uk")
                .setTestStartTimestamp("2019-01-14T10:36:33.987Z")
                .setTestEndTimestamp("2019-01-14T10:36:33.987Z")
                .setTestStatus("cancelled")
                .setReasonForCancellation("mistake")
                .setVehicleClass(vehicleClass)
                .setVehicleType("psv")
                .setNumberOfSeats(45)
                .setNoOfAxles(2)
                .setVehicleConfiguration("rigid")
                .setOdometerReading(350000)
                .setOdometerReadingUnits("kilometres")
                .setPreparerId("bl5545")
                .setPreparerName("Durrell Vehicles Limited")
                .setEuVehicleCategory("m1")
                .setCountryOfRegistration("united kingdom")
                .setVehicleSize("small")
                .setTestTypes(Arrays.asList(testTypes.build()));
    }
}
