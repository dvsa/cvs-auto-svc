package testresults;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import data.TestResultsData;
import model.testresults.TestResults;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.core.annotations.WithTags;
import org.junit.Test;
import org.junit.runner.RunWith;
import steps.TestResultsSteps;

import static util.DataUtil.generateRandomExcludingValues;

@WithTags(
        {
                @WithTag(type = "TestResults", name = "All"),
                @WithTag(type = "TestResults", name = "Positive"),
                @WithTag(type = "Service", name = "One"),

        }
)

@RunWith(SerenityRunner.class)
public class PostTestResultsApiSpecsVehicleConfigurationNegative {

    @Steps
    TestResultsSteps testResultsSteps;

    private TestResults.Builder vehicleSubmittedData = TestResultsData.buildTestResultsSubmittedData();

    @Title("CVSB-7391 - TC - POST values for vehicleConfiguration (HGV) - undefined value")
    @Test
    public void testTestResultsPostVehicleConfigurationHgvUndefined() {

        vehicleSubmittedData
                .setVin(generateRandomExcludingValues(21, vehicleSubmittedData.build().getVin()))
                .setVrm(generateRandomExcludingValues(7, vehicleSubmittedData.build().getVrm()))
                .setTestResultId(generateRandomExcludingValues(3,vehicleSubmittedData.build().getTestResultId()))
                .setCountryOfRegistration("a")
                .setEuVehicleCategory("n2")
                .setNoOfAxles(2)
                .setReasonForCancellation(null)
                .setTestStationName("Abshire-Kub")
                .setTestStationPNumber("09-4129632")
                .setTestStationType("gvts")
                .setVehicleType("hgv")
                .setVehicleConfiguration("undefined")
                .setTestStatus("submitted")
                .build();

        vehicleSubmittedData.build().getTestTypes().get(0).setName("First test");
        vehicleSubmittedData.build().getTestTypes().get(0).setTestTypeName("First test");
        vehicleSubmittedData.build().getTestTypes().get(0).setTestTypeId("41");
        vehicleSubmittedData.build().getTestTypes().get(0).setCertificateNumber(null);
        vehicleSubmittedData.build().getTestTypes().get(0).setTestResult("fail");
        vehicleSubmittedData.build().getTestTypes().get(0).setProhibitionIssued(false);
        vehicleSubmittedData.build().getTestTypes().get(0).setReasonForAbandoning(null);
        vehicleSubmittedData.build().getTestTypes().get(0).setAdditionalCommentsForAbandon(null);
        vehicleSubmittedData.build().getTestTypes().get(0).setAdditionalNotesRecorded(null);

        vehicleSubmittedData.build().getVehicleClass().setCode("v").setDescription("heavy goods vehicle");
        vehicleSubmittedData.build().getTestTypes().get(0).getDefects().get(0)
                .setDeficiencyCategory("major")
                .setDeficiencyId("b")
                .setDeficiencyRef("3.1.b")
                .setDeficiencySubId(null)
                .setDeficiencyText("of an incorrect type.")
                .setImDescription("Seat Belts & Supplementary Restraint Systems")
                .setImNumber(3)
                .setItemDescription("Obligatory Seat Belt:")
                .setItemNumber(1)
                .setProhibitionIssued(true)
                .setPrs(false)
                .setStdForProhibition(false);

        vehicleSubmittedData.build().getTestTypes().get(0).getDefects().get(0).getAdditionalInformation()
                .setNotes("not working")
                .getLocation()
                .setVertical("lower")
                .setHorizontal(null)
                .setLateral("offside")
                .setLongitudinal(null)
                .setRowNumber(null)
                .setSeatNumber(null)
                .setAxleNumber(null);

        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode payload = objectMapper.valueToTree(vehicleSubmittedData.build());

        testResultsSteps.removeTestResultsFields(payload, "vehicleSize", "numberOfSeats", "vehicleId");
        testResultsSteps.removeTestResultsTestTypesFields(payload, 0, "numberOfSeatbeltsFitted", "lastSeatbeltInstallationCheckDate", "seatbeltInstallationCheckDate", "createdAt", "lastUpdatedAt", "testCode", "testNumber", "certificateLink", "testExpiryDate", "testAnniversaryDate");

        testResultsSteps.postTestResultsPayload(payload);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("vehicleConfiguration", "must be one of [rigid, articulated, centre axle drawbar, semi-car transporter, semi-trailer, low loader, other, drawbar, four-in-line, dolly, full drawbar]");
    }

    @Title("CVSB-7391 - TC - POST values for vehicleConfiguration (HGV) - null value")
    @Test
    public void testTestResultsPostVehicleConfigurationHgvNull() {

        vehicleSubmittedData
                .setVin(generateRandomExcludingValues(21, vehicleSubmittedData.build().getVin()))
                .setVrm(generateRandomExcludingValues(7, vehicleSubmittedData.build().getVrm()))
                .setTestResultId(generateRandomExcludingValues(3,vehicleSubmittedData.build().getTestResultId()))
                .setCountryOfRegistration("a")
                .setEuVehicleCategory("n2")
                .setNoOfAxles(2)
                .setReasonForCancellation(null)
                .setTestStationName("Abshire-Kub")
                .setTestStationPNumber("09-4129632")
                .setTestStationType("gvts")
                .setVehicleType("hgv")
                .setVehicleConfiguration(null)
                .setTestStatus("submitted")
                .build();

        vehicleSubmittedData.build().getTestTypes().get(0).setName("First test");
        vehicleSubmittedData.build().getTestTypes().get(0).setTestTypeName("First test");
        vehicleSubmittedData.build().getTestTypes().get(0).setTestTypeId("41");
        vehicleSubmittedData.build().getTestTypes().get(0).setCertificateNumber(null);
        vehicleSubmittedData.build().getTestTypes().get(0).setTestResult("fail");
        vehicleSubmittedData.build().getTestTypes().get(0).setProhibitionIssued(false);
        vehicleSubmittedData.build().getTestTypes().get(0).setReasonForAbandoning(null);
        vehicleSubmittedData.build().getTestTypes().get(0).setAdditionalCommentsForAbandon(null);
        vehicleSubmittedData.build().getTestTypes().get(0).setAdditionalNotesRecorded(null);

        vehicleSubmittedData.build().getVehicleClass().setCode("v").setDescription("heavy goods vehicle");
        vehicleSubmittedData.build().getTestTypes().get(0).getDefects().get(0)
                .setDeficiencyCategory("major")
                .setDeficiencyId("b")
                .setDeficiencyRef("3.1.b")
                .setDeficiencySubId(null)
                .setDeficiencyText("of an incorrect type.")
                .setImDescription("Seat Belts & Supplementary Restraint Systems")
                .setImNumber(3)
                .setItemDescription("Obligatory Seat Belt:")
                .setItemNumber(1)
                .setProhibitionIssued(true)
                .setPrs(false)
                .setStdForProhibition(false);

        vehicleSubmittedData.build().getTestTypes().get(0).getDefects().get(0).getAdditionalInformation()
                .setNotes("not working")
                .getLocation()
                .setVertical("lower")
                .setHorizontal(null)
                .setLateral("offside")
                .setLongitudinal(null)
                .setRowNumber(null)
                .setSeatNumber(null)
                .setAxleNumber(null);

        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode payload = objectMapper.valueToTree(vehicleSubmittedData.build());

        testResultsSteps.removeTestResultsFields(payload, "vehicleSize", "numberOfSeats", "vehicleId");
        testResultsSteps.removeTestResultsTestTypesFields(payload, 0, "numberOfSeatbeltsFitted", "lastSeatbeltInstallationCheckDate", "seatbeltInstallationCheckDate", "createdAt", "lastUpdatedAt", "testCode", "testNumber", "certificateLink", "testExpiryDate", "testAnniversaryDate");

        testResultsSteps.postTestResultsPayload(payload);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorDataContains("vehicleConfiguration", "must be one of [rigid, articulated, centre axle drawbar, semi-car transporter, semi-trailer, low loader, other, drawbar, four-in-line, dolly, full drawbar]");
    }

@Title("CVSB-7391 - TC - POST values for vehicleConfiguration (HGV) - missing field")
    @Test
    public void testTestResultsPostVehicleConfigurationHgvMissing() {

        vehicleSubmittedData
                .setVin(generateRandomExcludingValues(21, vehicleSubmittedData.build().getVin()))
                .setVrm(generateRandomExcludingValues(7, vehicleSubmittedData.build().getVrm()))
                .setTestResultId(generateRandomExcludingValues(3,vehicleSubmittedData.build().getTestResultId()))
                .setCountryOfRegistration("a")
                .setEuVehicleCategory("n2")
                .setNoOfAxles(2)
                .setReasonForCancellation(null)
                .setTestStationName("Abshire-Kub")
                .setTestStationPNumber("09-4129632")
                .setTestStationType("gvts")
                .setVehicleType("hgv")
                .setVehicleConfiguration("dolly")
                .setTestStatus("submitted")
                .build();

        vehicleSubmittedData.build().getTestTypes().get(0).setName("First test");
        vehicleSubmittedData.build().getTestTypes().get(0).setTestTypeName("First test");
        vehicleSubmittedData.build().getTestTypes().get(0).setTestTypeId("41");
        vehicleSubmittedData.build().getTestTypes().get(0).setCertificateNumber(null);
        vehicleSubmittedData.build().getTestTypes().get(0).setTestResult("fail");
        vehicleSubmittedData.build().getTestTypes().get(0).setProhibitionIssued(false);
        vehicleSubmittedData.build().getTestTypes().get(0).setReasonForAbandoning(null);
        vehicleSubmittedData.build().getTestTypes().get(0).setAdditionalCommentsForAbandon(null);
        vehicleSubmittedData.build().getTestTypes().get(0).setAdditionalNotesRecorded(null);

        vehicleSubmittedData.build().getVehicleClass().setCode("v").setDescription("heavy goods vehicle");
        vehicleSubmittedData.build().getTestTypes().get(0).getDefects().get(0)
                .setDeficiencyCategory("major")
                .setDeficiencyId("b")
                .setDeficiencyRef("3.1.b")
                .setDeficiencySubId(null)
                .setDeficiencyText("of an incorrect type.")
                .setImDescription("Seat Belts & Supplementary Restraint Systems")
                .setImNumber(3)
                .setItemDescription("Obligatory Seat Belt:")
                .setItemNumber(1)
                .setProhibitionIssued(true)
                .setPrs(false)
                .setStdForProhibition(false);

        vehicleSubmittedData.build().getTestTypes().get(0).getDefects().get(0).getAdditionalInformation()
                .setNotes("not working")
                .getLocation()
                .setVertical("lower")
                .setHorizontal(null)
                .setLateral("offside")
                .setLongitudinal(null)
                .setRowNumber(null)
                .setSeatNumber(null)
                .setAxleNumber(null);

        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode payload = objectMapper.valueToTree(vehicleSubmittedData.build());

        testResultsSteps.removeTestResultsFields(payload, "vehicleSize", "numberOfSeats", "vehicleId", "vehicleConfiguration");
        testResultsSteps.removeTestResultsTestTypesFields(payload, 0, "numberOfSeatbeltsFitted", "lastSeatbeltInstallationCheckDate", "seatbeltInstallationCheckDate", "createdAt", "lastUpdatedAt", "testCode", "testNumber", "certificateLink", "testExpiryDate", "testAnniversaryDate");

        testResultsSteps.postTestResultsPayload(payload);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("vehicleConfiguration", "is required");

    }


    @Title("CVSB-7391 - TC - POST values for vehicleConfiguration (TRL) - undefined value")
    @Test
    public void testTestResultsPostVehicleConfigurationTrlUndefined() {

        vehicleSubmittedData
                .setVin(generateRandomExcludingValues(21, vehicleSubmittedData.build().getVin()))
                .setTestResultId(generateRandomExcludingValues(3,vehicleSubmittedData.build().getTestResultId()))
                .setCountryOfRegistration("a")
                .setEuVehicleCategory("o3")
                .setNoOfAxles(6)
                .setReasonForCancellation(null)
                .setTestEndTimestamp("2019-09-12T16:42:14.757Z")
                .setTesterEmailAddress("cvs.automation3@dvsagov.onmicrosoft.com")
                .setTesterName("cvs.automation3@dvsagov.onmicrosoft.com")
                .setTestStationName("Abshire-Kub")
                .setTestStationPNumber("09-4129632")
                .setTestStationType("gvts")
                .setVehicleType("trl")
                .setVehicleConfiguration("undefined")
                .setVin("T12111111")
                .setTestStatus("submitted").build()
                .getTestTypes().get(0).setAdditionalCommentsForAbandon(null);

        vehicleSubmittedData.build().getTestTypes().get(0).setAdditionalNotesRecorded("a note");
        vehicleSubmittedData.build().getTestTypes().get(0).setCertificateNumber(null);
        vehicleSubmittedData.build().getTestTypes().get(0).setName("Retest");
        vehicleSubmittedData.build().getTestTypes().get(0).setProhibitionIssued(false);
        vehicleSubmittedData.build().getTestTypes().get(0).setReasonForAbandoning(null);
        vehicleSubmittedData.build().getTestTypes().get(0).setTestResult("fail");
        vehicleSubmittedData.build().getTestTypes().get(0).setTestTypeId("");
        vehicleSubmittedData.build().getTestTypes().get(0).setTestTypeName("Paid roadworthiness retest");
        vehicleSubmittedData.build().getVehicleClass().setCode("t").setDescription("trailer");

        vehicleSubmittedData.build().getTestTypes().get(0).getDefects().get(0)
                .setDeficiencyCategory("dangerous")
                .setDeficiencyId("a")
                .setDeficiencyRef("6.2.a.ii")
                .setDeficiencySubId("ii")
                .setDeficiencyText("with any visible elongation of a stud hole where secure fixing of a wheel is affected.")
                .setImDescription("Road Wheels and Hubs")
                .setImNumber(6)
                .setItemDescription("A wheel:")
                .setItemNumber(2)
                .setProhibitionIssued(true)
                .setPrs(false)
                .setStdForProhibition(false);

        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode payload = objectMapper.valueToTree(vehicleSubmittedData.build());

        testResultsSteps.addAdditionalTestResultsFieldValue(payload, "trailerId", "C234567");
        testResultsSteps.removeTestResultsFields(payload, "numberOfSeats", "odometerReading", "odometerReadingUnits", "vehicleId", "vehicleSize", "vrm");
        testResultsSteps.removeTestResultsTestTypesFields(payload, 0, "certificateLink", "createdAt", "lastSeatbeltInstallationCheckDate", "lastUpdatedAt", "numberOfSeatbeltsFitted", "seatbeltInstallationCheckDate", "testCode", "testNumber", "testExpiryDate", "testAnniversaryDate");

        testResultsSteps.postTestResultsPayload(payload);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorDataContains("vehicleConfiguration", "must be one of [rigid, articulated, centre axle drawbar, semi-car transporter, semi-trailer, low loader, other, drawbar, four-in-line, dolly, full drawbar]");
    }

    @Title("CVSB-7391 - TC - POST values for vehicleConfiguration (TRL) - null value")
    @Test
    public void testTestResultsPostVehicleConfigurationTrlNull() {

        vehicleSubmittedData
                .setVin(generateRandomExcludingValues(21, vehicleSubmittedData.build().getVin()))
                .setTestResultId(generateRandomExcludingValues(3,vehicleSubmittedData.build().getTestResultId()))
                .setCountryOfRegistration("a")
                .setEuVehicleCategory("o3")
                .setNoOfAxles(6)
                .setReasonForCancellation(null)
                .setTestEndTimestamp("2019-09-12T16:42:14.757Z")
                .setTesterEmailAddress("cvs.automation3@dvsagov.onmicrosoft.com")
                .setTesterName("cvs.automation3@dvsagov.onmicrosoft.com")
                .setTestStationName("Abshire-Kub")
                .setTestStationPNumber("09-4129632")
                .setTestStationType("gvts")
                .setVehicleType("trl")
                .setVehicleConfiguration(null)
                .setVin("T12111111")
                .setTestStatus("submitted").build()
                .getTestTypes().get(0).setAdditionalCommentsForAbandon(null);

        vehicleSubmittedData.build().getTestTypes().get(0).setAdditionalNotesRecorded("a note");
        vehicleSubmittedData.build().getTestTypes().get(0).setCertificateNumber(null);
        vehicleSubmittedData.build().getTestTypes().get(0).setName("Retest");
        vehicleSubmittedData.build().getTestTypes().get(0).setProhibitionIssued(false);
        vehicleSubmittedData.build().getTestTypes().get(0).setReasonForAbandoning(null);
        vehicleSubmittedData.build().getTestTypes().get(0).setTestResult("fail");
        vehicleSubmittedData.build().getTestTypes().get(0).setTestTypeId("");
        vehicleSubmittedData.build().getTestTypes().get(0).setTestTypeName("Paid roadworthiness retest");
        vehicleSubmittedData.build().getVehicleClass().setCode("t").setDescription("trailer");

        vehicleSubmittedData.build().getTestTypes().get(0).getDefects().get(0)
                .setDeficiencyCategory("dangerous")
                .setDeficiencyId("a")
                .setDeficiencyRef("6.2.a.ii")
                .setDeficiencySubId("ii")
                .setDeficiencyText("with any visible elongation of a stud hole where secure fixing of a wheel is affected.")
                .setImDescription("Road Wheels and Hubs")
                .setImNumber(6)
                .setItemDescription("A wheel:")
                .setItemNumber(2)
                .setProhibitionIssued(true)
                .setPrs(false)
                .setStdForProhibition(false);

        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode payload = objectMapper.valueToTree(vehicleSubmittedData.build());

        testResultsSteps.addAdditionalTestResultsFieldValue(payload, "trailerId", "C234567");
        testResultsSteps.removeTestResultsFields(payload, "numberOfSeats", "odometerReading", "odometerReadingUnits", "vehicleId", "vehicleSize", "vrm");
        testResultsSteps.removeTestResultsTestTypesFields(payload, 0, "certificateLink", "createdAt", "lastSeatbeltInstallationCheckDate", "lastUpdatedAt", "numberOfSeatbeltsFitted", "seatbeltInstallationCheckDate", "testCode", "testNumber", "testExpiryDate", "testAnniversaryDate");

        testResultsSteps.postTestResultsPayload(payload);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorDataContains("vehicleConfiguration", "must be one of [rigid, articulated, centre axle drawbar, semi-car transporter, semi-trailer, low loader, other, drawbar, four-in-line, dolly, full drawbar]");
    }

    @Title("CVSB-7391 - TC - POST values for vehicleConfiguration (TRL) - missing")
    @Test
    public void testTestResultsPostVehicleConfigurationTrlMissing() {

        vehicleSubmittedData
                .setVin(generateRandomExcludingValues(21, vehicleSubmittedData.build().getVin()))
                .setTestResultId(generateRandomExcludingValues(3,vehicleSubmittedData.build().getTestResultId()))
                .setCountryOfRegistration("a")
                .setEuVehicleCategory("o3")
                .setNoOfAxles(6)
                .setReasonForCancellation(null)
                .setTestEndTimestamp("2019-09-12T16:42:14.757Z")
                .setTesterEmailAddress("cvs.automation3@dvsagov.onmicrosoft.com")
                .setTesterName("cvs.automation3@dvsagov.onmicrosoft.com")
                .setTestStationName("Abshire-Kub")
                .setTestStationPNumber("09-4129632")
                .setTestStationType("gvts")
                .setVehicleType("trl")
                .setVehicleConfiguration("drawbar")
                .setVin("T12111111")
                .setTestStatus("submitted").build()
                .getTestTypes().get(0).setAdditionalCommentsForAbandon(null);

        vehicleSubmittedData.build().getTestTypes().get(0).setAdditionalNotesRecorded("a note");
        vehicleSubmittedData.build().getTestTypes().get(0).setCertificateNumber(null);
        vehicleSubmittedData.build().getTestTypes().get(0).setName("Retest");
        vehicleSubmittedData.build().getTestTypes().get(0).setProhibitionIssued(false);
        vehicleSubmittedData.build().getTestTypes().get(0).setReasonForAbandoning(null);
        vehicleSubmittedData.build().getTestTypes().get(0).setTestResult("fail");
        vehicleSubmittedData.build().getTestTypes().get(0).setTestTypeId("");
        vehicleSubmittedData.build().getTestTypes().get(0).setTestTypeName("Paid roadworthiness retest");
        vehicleSubmittedData.build().getVehicleClass().setCode("t").setDescription("trailer");

        vehicleSubmittedData.build().getTestTypes().get(0).getDefects().get(0)
                .setDeficiencyCategory("dangerous")
                .setDeficiencyId("a")
                .setDeficiencyRef("6.2.a.ii")
                .setDeficiencySubId("ii")
                .setDeficiencyText("with any visible elongation of a stud hole where secure fixing of a wheel is affected.")
                .setImDescription("Road Wheels and Hubs")
                .setImNumber(6)
                .setItemDescription("A wheel:")
                .setItemNumber(2)
                .setProhibitionIssued(true)
                .setPrs(false)
                .setStdForProhibition(false);

        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode payload = objectMapper.valueToTree(vehicleSubmittedData.build());

        testResultsSteps.addAdditionalTestResultsFieldValue(payload, "trailerId", "C234567");
        testResultsSteps.removeTestResultsFields(payload, "numberOfSeats", "odometerReading", "odometerReadingUnits", "vehicleId", "vehicleSize", "vrm", "vehicleConfiguration");
        testResultsSteps.removeTestResultsTestTypesFields(payload, 0, "certificateLink", "createdAt", "lastSeatbeltInstallationCheckDate", "lastUpdatedAt", "numberOfSeatbeltsFitted", "seatbeltInstallationCheckDate", "testCode", "testNumber", "testExpiryDate", "testAnniversaryDate");
        testResultsSteps.postTestResultsPayload(payload);
        testResultsSteps.statusCodeShouldBe(400);
        testResultsSteps.validatePostErrorData("vehicleConfiguration", "is required");
    }
}
