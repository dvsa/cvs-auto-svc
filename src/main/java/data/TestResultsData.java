package data;

import model.testresults.*;

import java.util.Arrays;

public class TestResultsData {

    public static TestResults buildTestResultsData() {




        Deficiency deficiency = new Deficiency()
                .setRef("REFERENCELOL")
                .setDeficiencyId("a")
                .setDeficiencySubId("M")
                .setDeficiencyCategory("advisory")
                .setDeficiencyText("oops it doesn't work")
                .setStdForProhibition(true)
                .setPrs(true)
                .setForVehicleType(Arrays.asList("psv"));




        Item item = new Item()
                .setItemNumber(0)
                .setItemDescription("string")
                .setForVehicleType(Arrays.asList("psv"))
                .setDeficiency(deficiency);

        Location location = new Location()
                .setVertical("upper")
                .setHorizontal(null)
                .setLateral("nearside")
                .setLongitudinal("rear")
                .setRowNumber(2)
                .setSeatNumber(1)
                .setAxleNumber(1);

        AdditionalInformation additionalInformation = new AdditionalInformation()
                .setLocation(location)
                .setNotes("string");


        Defects defects = new Defects()
                .setImNumber(0)
                .setImDescription("string")
                .setForVehicleType(Arrays.asList("psv"))
                .setAdditionalInformation(additionalInformation)
                .setItem(item);


        TestTypes testTypes = new TestTypes()
                .setCreatedAt("2019-01-14T10:36:33.987Z")
                .setLastUpdatedAt("2019-01-14T10:36:33.987Z")
                .setTestCode("ASD")
                .setTestTypeName("string")
                .setTestId("string")
                .setCertificateNumber("string")
                .setTestExpiryDate("2019-01-14")
                .setTestTypeStartTimestamp("2019-01-14T10:36:33.987Z")
                .setTestTypeEndTimestamp("2019-01-14T10:36:33.987Z")
                .setNumberOfSeatbeltsFitted(0)
                .setLastSeatbeltInstallationCheckDate("2019-01-14")
                .setSeatbeltInstallationCheckDate(true)
                .setTestResult("Failure")
                .setProhibitionIssued("Yes")
                .setReasonForAbandoning("string")
                .setAdditionalNotesRecorded("string")
                .setDefects(Arrays.asList(defects));

        TestResults testResults = new TestResults()
                .setVrm("GO79FAT")
                .setVin("1B7GG36N12S678410")
                .setTestStationName("Station Name")
                .setTestStationPNumber("12345")
                .setLocationType("ATF")
                .setTesterName("George")
                .setTesterStaffId("1")
                .setTestStartTimestamp("2019-01-14T10:36:33.987Z")
                .setTestEndTimestamp("2019-01-14T10:36:33.987Z")
                .setTestStatus("submitted")
                .setReasonForCancellation("none")
                .setVehicleClass("2")
                .setVehicleType("PSV")
                .setNumberOfSeats(1)
                .setVehicleStatus("1")
                .setVehicleConfiguration("R (Rigid)")
                .setOdometerReading(7)
                .setOdometerReadingUnits("kilometers")
                .setPreparerId("string")
                .setPreparerName("string")
                .setEuVehicleCategory("M1")
                .setCountryOfRegistration("string")
                .setTestTypes(Arrays.asList(testTypes));

        return testResults;



    }
}
