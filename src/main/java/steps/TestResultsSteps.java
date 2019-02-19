package steps;

import clients.TestResultsClient;
import clients.util.ToTypeConvertor;
import clients.util.testresult.TestResultsLevel;
import io.restassured.response.Response;
import model.testresults.*;
import net.thucydides.core.annotations.Step;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.hamcrest.Matchers.*;

public class TestResultsSteps {

    TestResultsClient testResultsClient = new TestResultsClient();
    Response response;

    @Step
    public void getTestResults(String vin) {
        response = testResultsClient.getTestResults(vin);
    }

    @Step
    public void getTestResults(String vin, TestResultsStatus testResultsStatus) {
        response = testResultsClient.getVehicleTechnicalRecords(vin, testResultsStatus.getStatus());
    }

    @Step
    public void postTestResults(TestResults testResults) {
        response = testResultsClient.postTestResults(testResults);
    }

    @Step
    public void postTestResultsFieldChange(TestResults testResults, String propertyField, String value, ToTypeConvertor toType, TestResultsLevel testResultsLevel) {
        response = testResultsClient.postTestResultsFieldChange(testResults, propertyField, value, toType, testResultsLevel);
    }

    @Step
    public void postTestResultsFieldChange(TestResults testResults, String propertyField, ToTypeConvertor toType, TestResultsLevel testResultsLevel) {
        response = testResultsClient.postTestResultsFieldChange(testResults, propertyField, null, toType, testResultsLevel);
    }


    @Step
    public void getTestResultsFromDate(String vin, String fromDate) {
        response = testResultsClient.getTestResultsFromDateTime(vin, fromDate);
    }

    @Step
    public void getTestResultsFromDate(String vin, String fromDate, TestResultsStatus testResultsStatus) {
        response = testResultsClient.getTestResultsFromDateTime(vin, fromDate, testResultsStatus.getStatus());
    }

    @Step
    public void getTestResultsToDate(String vin, String toDate) {
        response = testResultsClient.getTestResultsToDateTime(vin, toDate);
    }

    @Step
    public void getTestResultsToDate(String vin, String toDate, TestResultsStatus testResultsStatus) {
        response = testResultsClient.getTestResultsToDateTime(vin, toDate, testResultsStatus.getStatus());
    }

    @Step
    public void getTestResultsBetweenDate(String vin, String fromDate, String toDate) {
        response = testResultsClient.getTestResultsBetweenDate(vin, fromDate, toDate);
    }

    @Step
    public void getTestResultsBetweenDate(String vin, String fromDate, String toDate, TestResultsStatus testResultsStatus) {
        response = testResultsClient.getTestResultsBetweenDate(vin, fromDate, toDate, testResultsStatus.getStatus());
    }


    @Step
    public void statusCodeShouldBe(int statusCode) {
        response.then().statusCode(statusCode);
    }

    @Step
    public void validateData(String stringData) {
        response.then().body(is("\"" + stringData + "\""));
    }

    @Step
    public void validateData(TestResults testResults) {

        validateTestResultsData(testResults);
        validateTestTypesData(testResults);
        validateDefectsData(testResults);
        validateAdditionalInformation(testResults);
        validateLocation(testResults);
        validateItem(testResults);
        validateDeficiency(testResults);

    }


    @Step
    private void validateTestResultsData(TestResults testResults) {

        response.then().body("size()", is(1));
        response.then().body("[0].size()", is(TestResults.class.getDeclaredFields().length));

        response.then().body("vrm", hasItem(equalTo(testResults.getVrm())));
        response.then().body("vin", hasItem(equalTo(testResults.getVin())));
        response.then().body("testStationName", hasItem(equalTo(testResults.getTestStationName())));
        response.then().body("testStationPNumber", hasItem(equalTo(testResults.getTestStationPNumber())));
        response.then().body("locationType", hasItem(equalTo(testResults.getLocationType())));
        response.then().body("testerName", hasItem(equalTo(testResults.getTesterName())));
        response.then().body("testerStaffId", hasItem(equalTo(testResults.getTesterStaffId())));
        response.then().body("testStartTimestamp", hasItem(equalTo(testResults.getTestStartTimestamp())));
        response.then().body("testEndTimestamp", hasItem(equalTo(testResults.getTestEndTimestamp())));
        response.then().body("testStatus", hasItem(equalTo(testResults.getTestStatus())));
        response.then().body("reasonForCancellation", hasItem(equalTo(testResults.getReasonForCancellation())));
        response.then().body("vehicleClass", hasItem(equalTo(testResults.getVehicleClass())));
        response.then().body("vehicleType", hasItem(equalTo(testResults.getVehicleType())));
        response.then().body("numberOfSeats", hasItem(equalTo(testResults.getNumberOfSeats())));
        response.then().body("vehicleStatus", hasItem(equalTo(testResults.getVehicleStatus())));
        response.then().body("vehicleConfiguration", hasItem(equalTo(testResults.getVehicleConfiguration())));
        response.then().body("odometerReading", hasItem(equalTo(testResults.getOdometerReading())));
        response.then().body("odometerReadingUnits", hasItem(equalTo(testResults.getOdometerReadingUnits())));
        response.then().body("preparerId", hasItem(equalTo(testResults.getPreparerId())));
        response.then().body("preparerName", hasItem(equalTo(testResults.getPreparerName())));
        response.then().body("euVehicleCategory", hasItem(equalTo(testResults.getEuVehicleCategory())));
        response.then().body("countryOfRegistration", hasItem(equalTo(testResults.getCountryOfRegistration())));

    }

    @Step
    private void validateTestTypesData(TestResults testResults) {
        response.then().body("[0].testTypes.size()", is(1));
        response.then().body("[0].testTypes[0].size()", is(TestTypes.class.getDeclaredFields().length));


        List<String> createdAt = testResults.getTestTypes().stream().map(TestTypes::getCreatedAt).collect(toList());
        List<String> lastUpdatedAt = testResults.getTestTypes().stream().map(TestTypes::getLastUpdatedAt).collect(toList());
        List<String> testCode = testResults.getTestTypes().stream().map(TestTypes::getTestCode).collect(toList());
        List<String> testTypeName = testResults.getTestTypes().stream().map(TestTypes::getTestTypeName).collect(toList());
        List<String> testId = testResults.getTestTypes().stream().map(TestTypes::getTestId).collect(toList());
        List<String> certificateNumber = testResults.getTestTypes().stream().map(TestTypes::getCertificateNumber).collect(toList());
        List<String> testExpiryDate = testResults.getTestTypes().stream().map(TestTypes::getTestExpiryDate).collect(toList());
        List<String> testTypeStartTimestamp = testResults.getTestTypes().stream().map(TestTypes::getTestTypeStartTimestamp).collect(toList());
        List<String> testTypeEndTimestamp = testResults.getTestTypes().stream().map(TestTypes::getTestTypeEndTimestamp).collect(toList());
        List<Integer> numberOfSeatbeltsFitted = testResults.getTestTypes().stream().map(TestTypes::getNumberOfSeatbeltsFitted).collect(toList());
        List<String> lastSeatbeltInstallationCheckDate = testResults.getTestTypes().stream().map(TestTypes::getLastSeatbeltInstallationCheckDate).collect(toList());
        List<Boolean> seatbeltInstallationCheckDate = testResults.getTestTypes().stream().map(TestTypes::getSeatbeltInstallationCheckDate).collect(toList());
        List<String> testResult = testResults.getTestTypes().stream().map(TestTypes::getTestResult).collect(toList());
        List<String> prohibitionIssued = testResults.getTestTypes().stream().map(TestTypes::getProhibitionIssued).collect(toList());
        List<String> reasonForAbandoning = testResults.getTestTypes().stream().map(TestTypes::getReasonForAbandoning).collect(toList());
        List<String> additionalNotesRecorded = testResults.getTestTypes().stream().map(TestTypes::getAdditionalNotesRecorded).collect(toList());

        response.then().body("testTypes.createdAt", hasItem(contains(createdAt.toArray())));
        response.then().body("testTypes.lastUpdatedAt", hasItem(contains(lastUpdatedAt.toArray())));
        response.then().body("testTypes.testCode", hasItem(contains(testCode.toArray())));
        response.then().body("testTypes.testTypeName", hasItem(contains(testTypeName.toArray())));
        response.then().body("testTypes.testId", hasItem(contains(testId.toArray())));
        response.then().body("testTypes.certificateNumber", hasItem(contains(certificateNumber.toArray())));
        response.then().body("testTypes.testExpiryDate", hasItem(contains(testExpiryDate.toArray())));
        response.then().body("testTypes.testTypeStartTimestamp", hasItem(contains(testTypeStartTimestamp.toArray())));
        response.then().body("testTypes.testTypeEndTimestamp", hasItem(contains(testTypeEndTimestamp.toArray())));
        response.then().body("testTypes.numberOfSeatbeltsFitted", hasItem(contains(numberOfSeatbeltsFitted.toArray())));
        response.then().body("testTypes.lastSeatbeltInstallationCheckDate", hasItem(contains(lastSeatbeltInstallationCheckDate.toArray())));
        response.then().body("testTypes.seatbeltInstallationCheckDate", hasItem(contains(seatbeltInstallationCheckDate.toArray())));
        response.then().body("testTypes.testResult", hasItem(contains(testResult.toArray())));
        response.then().body("testTypes.prohibitionIssued", hasItem(contains(prohibitionIssued.toArray())));
        response.then().body("testTypes.reasonForAbandoning", hasItem(contains(reasonForAbandoning.toArray())));
        response.then().body("testTypes.additionalNotesRecorded", hasItem(contains(additionalNotesRecorded.toArray())));

    }

    @Step
    private void validateDefectsData(TestResults testResults) {

        response.then().body("[0].testTypes.defects.size()", is(1));
        response.then().body("[0].testTypes[0].defects[0].size()", is(Defects.class.getDeclaredFields().length));

        List<List<Integer>> imNumber = testResults.getTestTypes().stream().map(s -> s.getDefects().stream().map(Defects::getImNumber).collect(toList())).collect(toList());
        List<List<String>> imDescription = testResults.getTestTypes().stream().map(s -> s.getDefects().stream().map(Defects::getImDescription).collect(toList())).collect(toList());
        List<List<List<String>>> forVehicleType = testResults.getTestTypes().stream().map(s -> s.getDefects().stream().map(Defects::getForVehicleType).collect(toList())).collect(toList());

        response.then().body("testTypes.defects.imNumber", hasItem(contains(imNumber.toArray())));
        response.then().body("testTypes.defects.imDescription", hasItem(contains(imDescription.toArray())));
        response.then().body("testTypes.defects.forVehicleType", hasItem(contains(forVehicleType.toArray())));
    }


    @Step
    private void validateAdditionalInformation(TestResults testResults) {

        response.then().body("[0].testTypes.defects.additionalInformation.size()", is(1));
        response.then().body("[0].testTypes[0].defects[0].additionalInformation.size()", is(AdditionalInformation.class.getDeclaredFields().length));

        List<List<String>> additionalInformation = testResults.getTestTypes().stream().map(s -> s.getDefects().stream().map(y -> y.getAdditionalInformation().getNotes()).collect(toList())).collect(toList());
        response.then().body("testTypes.defects.additionalInformation.notes", hasItem(contains(additionalInformation.toArray())));

    }


    @Step
    private void validateLocation(TestResults testResults) {

        response.then().body("[0].testTypes.defects.additionalInformation.location.size()", is(1));
        response.then().body("[0].testTypes[0].defects[0].additionalInformation.location.size()", is(Location.class.getDeclaredFields().length));

        List<List<String>> vertical = testResults.getTestTypes().stream().map(s -> s.getDefects().stream().map(y -> y.getAdditionalInformation().getLocation().getVertical()).collect(toList())).collect(toList());
        List<List<String>> horizontal = testResults.getTestTypes().stream().map(s -> s.getDefects().stream().map(y -> y.getAdditionalInformation().getLocation().getHorizontal()).collect(toList())).collect(toList());
        List<List<String>> lateral = testResults.getTestTypes().stream().map(s -> s.getDefects().stream().map(y -> y.getAdditionalInformation().getLocation().getLateral()).collect(toList())).collect(toList());
        List<List<String>> longitudinal = testResults.getTestTypes().stream().map(s -> s.getDefects().stream().map(y -> y.getAdditionalInformation().getLocation().getLongitudinal()).collect(toList())).collect(toList());
        List<List<Integer>> rowNumber = testResults.getTestTypes().stream().map(s -> s.getDefects().stream().map(y -> y.getAdditionalInformation().getLocation().getRowNumber()).collect(toList())).collect(toList());
        List<List<Integer>> seatNumber = testResults.getTestTypes().stream().map(s -> s.getDefects().stream().map(y -> y.getAdditionalInformation().getLocation().getSeatNumber()).collect(toList())).collect(toList());
        List<List<Integer>> axleNumber = testResults.getTestTypes().stream().map(s -> s.getDefects().stream().map(y -> y.getAdditionalInformation().getLocation().getAxleNumber()).collect(toList())).collect(toList());

        response.then().body("testTypes.defects.additionalInformation.location.vertical", hasItem(contains(vertical.toArray())));
        response.then().body("testTypes.defects.additionalInformation.location.horizontal", hasItem(contains(horizontal.toArray())));
        response.then().body("testTypes.defects.additionalInformation.location.lateral", hasItem(contains(lateral.toArray())));
        response.then().body("testTypes.defects.additionalInformation.location.longitudinal", hasItem(contains(longitudinal.toArray())));
        response.then().body("testTypes.defects.additionalInformation.location.rowNumber", hasItem(contains(rowNumber.toArray())));
        response.then().body("testTypes.defects.additionalInformation.location.seatNumber", hasItem(contains(seatNumber.toArray())));
        response.then().body("testTypes.defects.additionalInformation.location.axleNumber", hasItem(contains(axleNumber.toArray())));

    }

    @Step
    private void validateItem(TestResults testResults) {

        response.then().body("[0].testTypes.defects.item.size()", is(1));
        response.then().body("[0].testTypes[0].defects[0].item.size()", is(Item.class.getDeclaredFields().length));

        List<List<Integer>> item = testResults.getTestTypes().stream().map(s -> s.getDefects().stream().map(y -> y.getItem().getItemNumber()).collect(toList())).collect(toList());
        List<List<String>> itemDescription = testResults.getTestTypes().stream().map(s -> s.getDefects().stream().map(y -> y.getItem().getItemDescription()).collect(toList())).collect(toList());
        List<List<List<String>>> forVehicleTypeItem = testResults.getTestTypes().stream().map(s -> s.getDefects().stream().map(y -> y.getItem().getForVehicleType()).collect(toList())).collect(toList());

        response.then().body("testTypes.defects.item.itemNumber", hasItem(contains(item.toArray())));
        response.then().body("testTypes.defects.item.itemDescription", hasItem(contains(itemDescription.toArray())));
        response.then().body("testTypes.defects.item.forVehicleType", hasItem(contains(forVehicleTypeItem.toArray())));

    }

    @Step
    private void validateDeficiency(TestResults testResults) {

        List<List<String>> ref = testResults.getTestTypes().stream().map(s -> s.getDefects().stream().map(y -> y.getItem().getDeficiency().getRef()).collect(toList())).collect(toList());
        List<List<String>> deficiencyId = testResults.getTestTypes().stream().map(s -> s.getDefects().stream().map(y -> y.getItem().getDeficiency().getDeficiencyId()).collect(toList())).collect(toList());
        List<List<String>> deficiencySubId = testResults.getTestTypes().stream().map(s -> s.getDefects().stream().map(y -> y.getItem().getDeficiency().getDeficiencySubId()).collect(toList())).collect(toList());
        List<List<String>> deficiencyCategory = testResults.getTestTypes().stream().map(s -> s.getDefects().stream().map(y -> y.getItem().getDeficiency().getDeficiencyCategory()).collect(toList())).collect(toList());
        List<List<String>> deficiencyText = testResults.getTestTypes().stream().map(s -> s.getDefects().stream().map(y -> y.getItem().getDeficiency().getDeficiencyText()).collect(toList())).collect(toList());
        List<List<Boolean>> stdForProhibition = testResults.getTestTypes().stream().map(s -> s.getDefects().stream().map(y -> y.getItem().getDeficiency().getStdForProhibition()).collect(toList())).collect(toList());
        List<List<Boolean>> prs = testResults.getTestTypes().stream().map(s -> s.getDefects().stream().map(y -> y.getItem().getDeficiency().getPrs()).collect(toList())).collect(toList());
        List<List<List<String>>> forVehicleTypeDeficiency = testResults.getTestTypes().stream().map(s -> s.getDefects().stream().map(y -> y.getItem().getDeficiency().getForVehicleType()).collect(toList())).collect(toList());


        response.then().body("[0].testTypes.defects.item.deficiency.size()", is(1));
        response.then().body("[0].testTypes[0].defects[0].item.deficiency.size()", is(Deficiency.class.getDeclaredFields().length));

        response.then().body("testTypes.defects.item.deficiency.ref", hasItem(contains(ref.toArray())));
        response.then().body("testTypes.defects.item.deficiency.deficiencyId", hasItem(contains(deficiencyId.toArray())));
        response.then().body("testTypes.defects.item.deficiency.deficiencySubId", hasItem(contains(deficiencySubId.toArray())));
        response.then().body("testTypes.defects.item.deficiency.deficiencyCategory", hasItem(contains(deficiencyCategory.toArray())));
        response.then().body("testTypes.defects.item.deficiency.deficiencyText", hasItem(contains(deficiencyText.toArray())));
        response.then().body("testTypes.defects.item.deficiency.stdForProhibition", hasItem(contains(stdForProhibition.toArray())));
        response.then().body("testTypes.defects.item.deficiency.prs", hasItem(contains(prs.toArray())));
        response.then().body("testTypes.defects.item.deficiency.forVehicleType", hasItem(contains(forVehicleTypeDeficiency.toArray())));


    }

    @Step
    public void validatePostErrorData(String field, String errorMessage) {
        response.then().body("size()", is(1));
        response.then().body("errors.size()", is(1));
        response.then().body("errors[0]", equalTo("\"" + field + "\" " + errorMessage));
    }
}
