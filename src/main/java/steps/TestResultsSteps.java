package steps;

import clients.TestResultsClient;
import clients.util.ToTypeConvertor;
import clients.util.testresult.TestResultsLevel;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.restassured.response.Response;
import model.testresults.*;
import net.thucydides.core.annotations.Step;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static util.DataFilter.getElementsToRemove;
import static util.TestNumberUtils.computeNextTestNumber;
import static util.TestNumberUtils.isTestNumberChecksumValid;
import static util.TypeLoader.*;

public class TestResultsSteps {

    TestResultsClient testResultsClient = new TestResultsClient();
    Response response;
    private static String nextTestNumber = "";

    @Step
    public void getTestResults(String vin) {
        response = testResultsClient.getTestResults(vin);
    }

    @Step
    public void getTestResultsNotAuthenticated(String vin) {
        setMissingAtuh();
        response = testResultsClient.callGetTestResults(vin);
        setRightAtuh();
    }

    @Step
    public void getTestResultsNotAuthorised(String vin) {
        setWrongAtuh();
        response = testResultsClient.callGetTestResults(vin);
        setRightAtuh();
    }

    @Step
    public void getTestResults(String vin, TestResultsStatus testResultsStatus) {
        response = testResultsClient.getTestResultsWithStatus(vin, testResultsStatus.getStatus());
    }

    @Step
    public void postTestResults(TestResults testResults) {
        response = testResultsClient.postTestResults(testResults);
    }

    @Step
    public void postTestResultsNotAuthorised(TestResults testResults) {
        setWrongAtuh();
        response = testResultsClient.callPostTestResults(testResults);
        setRightAtuh();
    }

    @Step
    public void postTestResultsNotAuthenticated(TestResults testResults) {
        setMissingAtuh();
        response = testResultsClient.callPostTestResults(testResults);
        setRightAtuh();
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
        response.then()
                .log().all()
                .statusCode(statusCode);
    }

    @Step
    public void testTypeLengthShouldBe(int length) {
        response.then().body("testTypes[0].size()", is(length));
    }

    @Step
    public void validateData(String stringData) {
        response.then().body(is("\"" + stringData + "\""));
    }

    @Step
    public void validateMessage(String stringData) {
        response.then()
                .log().all()
                .body("message", equalTo(stringData));
    }

    @Step
    public void validateErrorText(String stringData) {
       response.equals(stringData);
    }

    @Step
    public void validateData(TestResultsGet testResults) {

        validateTestResultsData(testResults);
        validateTestTypesData(testResults);
        validateDefectsData(testResults);
        validateAdditionalInformation(testResults);
        validateLocation(testResults);
    }

    @Step
    public void validateDataForAnniversary(List<String> expectedDataList) {
        if (expectedDataList != null) {
            for (String expectedData : expectedDataList) {
                if (expectedData != null) {
                    response.then().body("testTypes.testAnniversaryDate", hasItem(hasItem(startsWith(expectedData))));
                } else {
                    response.then().body("testTypes.testAnniversaryDate", hasItem(hasItem(nullValue())));
                }
                response.then().body("testTypes.size()", is(expectedDataList.size()));
            }
        } else {
            response.then().body("testTypes.testAnniversaryDate", hasItem(hasItem(nullValue())));
            response.then().body("testTypes.size()", is(1));
        }
    }

    @Step
    public void validateDataForExpiry(List<String> expectedDataList) {
        if (expectedDataList != null) {
            for (String expectedData : expectedDataList) {
                if (expectedData != null) {
                    response.then().body("testTypes.testExpiryDate", hasItem(hasItem(startsWith(expectedData))));
                } else {
                    response.then().body("testTypes.testExpiryDate", hasItem(hasItem(nullValue())));
                }
                response.then().body("testTypes.size()", is(expectedDataList.size()));
            }
        } else {
            response.then().body("testTypes.testExpiryDate", hasItem(hasItem(nullValue())));
            response.then().body("testTypes.size()", is(1));
        }
    }

    @Step
    private void validateTestResultsData(TestResultsGet testResults) {

        int record = 0;
        for (int i = 0; i < (Integer) response.jsonPath().get("size()"); i++) {
            System.out.println("i = " + i);
            List<String> startTimeList = response.jsonPath().getList("testStartTimestamp");
            System.out.println("testStartTimestamp is: " + startTimeList.get(i));
            if (startTimeList.get(i).equals(testResults.getTestStartTimestamp())) {
                System.out.println("testStartTimestamp: " + startTimeList.get(i) + "activities id: " + testResults.getTestStartTimestamp());
                record = i;
                break;
            }
        }

        List<String> fieldsNotInGet = getElementsToRemove(testResults.getClass().getSuperclass());
        response.then().body("[" + record + "].size()", is(TestResultsGet.class.getDeclaredFields().length + TestResultsGet.class.getSuperclass().getDeclaredFields().length - fieldsNotInGet.size()));
        response.then().body("vrm", hasItem(equalTo(testResults.getVrm())));
        response.then().body("vin", hasItem(equalTo(testResults.getVin())));
        response.then().body("testStationName", hasItem(equalTo(testResults.getTestStationName())));
        response.then().body("testStationPNumber", hasItem(equalTo(testResults.getTestStationPNumber())));
        response.then().body("testStationType", hasItem(equalTo(testResults.getTestStationType())));
        response.then().body("testerName", hasItem(equalTo(testResults.getTesterName())));
        response.then().body("testerStaffId", hasItem(equalTo(testResults.getTesterStaffId())));
        response.then().body("testerEmailAddress", hasItem(equalTo(testResults.getTesterEmailAddress())));
        response.then().body("testStartTimestamp", hasItem(equalTo(testResults.getTestStartTimestamp())));
        response.then().body("testEndTimestamp", hasItem(equalTo(testResults.getTestEndTimestamp())));
        response.then().body("testStatus", hasItem(equalTo(testResults.getTestStatus())));
        response.then().body("reasonForCancellation", hasItem(equalTo(testResults.getReasonForCancellation())));

        response.then().body("[" + record + "].vehicleClass.size()", is(VehicleClass.class.getDeclaredFields().length));
        response.then().body("vehicleClass.description", hasItem(equalTo(testResults.getVehicleClass().getDescription())));
        response.then().body("vehicleClass.code", hasItem(equalTo(testResults.getVehicleClass().getCode())));

        response.then().body("vehicleType", hasItem(equalTo(testResults.getVehicleType())));
        response.then().body("numberOfSeats", hasItem(equalTo(testResults.getNumberOfSeats())));
        response.then().body("noOfAxles", hasItem(equalTo(testResults.getNoOfAxles())));
        response.then().body("vehicleConfiguration", hasItem(equalTo(testResults.getVehicleConfiguration())));
        response.then().body("odometerReading", hasItem(equalTo(testResults.getOdometerReading())));
        response.then().body("odometerReadingUnits", hasItem(equalTo(testResults.getOdometerReadingUnits())));
        response.then().body("preparerId", hasItem(equalTo(testResults.getPreparerId())));
        response.then().body("preparerName", hasItem(equalTo(testResults.getPreparerName())));
        response.then().body("euVehicleCategory", hasItem(equalTo(testResults.getEuVehicleCategory())));
        response.then().body("countryOfRegistration", hasItem(equalTo(testResults.getCountryOfRegistration())));
        response.then().body("vehicleSize", hasItem(equalTo(testResults.getVehicleSize())));
    }

    @Step
    private void validateTestTypesData(TestResultsGet testResults) {

        int record = 0;
        for (int i = 0; i < (Integer) response.jsonPath().get("size()"); i++) {
            System.out.println("i = " + i);
            List<String> startTimeList = response.jsonPath().getList("testStartTimestamp");
            System.out.println("testStartTimestamp is: " + startTimeList.get(i));
            if (startTimeList.get(i).equals(testResults.getTestStartTimestamp())) {
                System.out.println("testStartTimestamp: " + startTimeList.get(i) + "activities id: " + testResults.getTestStartTimestamp());
                record = i;
                break;
            }
        }

        response.then().body("[" + record +"].testTypes.size()", is(1));

        List<String> createdAt = testResults.getTestTypes().stream().map(TestTypesGet::getCreatedAt).collect(toList());
        List<String> lastUpdatedAt = testResults.getTestTypes().stream().map(TestTypesGet::getLastUpdatedAt).collect(toList());
        List<String> testCode = testResults.getTestTypes().stream().map(TestTypesGet::getTestCode).collect(toList());
        List<String> testNumber = testResults.getTestTypes().stream().map(TestTypesGet::getTestNumber).collect(toList());
        List<String> certificateLink = testResults.getTestTypes().stream().map(TestTypesGet::getCertificateLink).collect(toList());
        List<String> testExpiryDate = testResults.getTestTypes().stream().map(TestTypesGet::getTestExpiryDate).collect(toList());
        List<String> testAnniversaryDate = testResults.getTestTypes().stream().map(TestTypesGet::getTestAnniversaryDate).collect(toList());

        List<String> testTypeName = testResults.getTestTypes().stream().map(TestTypes::getTestTypeName).collect(toList());
        List<String> testTypeId = testResults.getTestTypes().stream().map(TestTypes::getTestTypeId).collect(toList());
        List<String> certificateNumber = testResults.getTestTypes().stream().map(TestTypes::getCertificateNumber).collect(toList());
        List<String> testTypeStartTimestamp = testResults.getTestTypes().stream().map(TestTypes::getTestTypeStartTimestamp).collect(toList());
        List<String> testTypeEndTimestamp = testResults.getTestTypes().stream().map(TestTypes::getTestTypeEndTimestamp).collect(toList());
        List<Integer> numberOfSeatbeltsFitted = testResults.getTestTypes().stream().map(TestTypes::getNumberOfSeatbeltsFitted).collect(toList());
        List<String> lastSeatbeltInstallationCheckDate = testResults.getTestTypes().stream().map(TestTypes::getLastSeatbeltInstallationCheckDate).collect(toList());
        List<Boolean> seatbeltInstallationCheckDate = testResults.getTestTypes().stream().map(TestTypes::getSeatbeltInstallationCheckDate).collect(toList());
        List<Boolean> prohibitionIssued = testResults.getTestTypes().stream().map(TestTypes::getProhibitionIssued).collect(toList());
        List<String> reasonForAbandoning = testResults.getTestTypes().stream().map(TestTypes::getReasonForAbandoning).collect(toList());
        List<String> additionalNotesRecorded = testResults.getTestTypes().stream().map(TestTypes::getAdditionalNotesRecorded).collect(toList());
        List<String> name = testResults.getTestTypes().stream().map(TestTypes::getName).collect(toList());
        List<String> testResult = testResults.getTestTypes().stream().map(TestTypes::getTestResult).collect(toList());
        List<String> additionalCommentsForAbandon = testResults.getTestTypes().stream().map(TestTypes::getAdditionalCommentsForAbandon).collect(toList());

        response.then().body("[" + record +"].testTypes[0]", hasKey("createdAt"));
        response.then().body("[" + record +"].testTypes[0]", hasKey("lastUpdatedAt"));
        response.then().body("[" + record +"].testTypes[0]", hasKey("testCode"));
        response.then().body("[" + record +"].testTypes[0]", hasKey("testNumber"));


        // TODO: separate Tests for below properties
        //        response.then().body("[0].testTypes[0]", hasKey("certificateLink"));
//        response.then().body("testTypes.certificateLink", hasItem(contains(certificateLink.toArray())));
//        response.then().body("testTypes.certificateNumber", hasItem(contains(certificateNumber.toArray())));


        response.then().body("testTypes.testTypeName", hasItem(contains(testTypeName.toArray())));
        response.then().body("testTypes.testTypeId", hasItem(contains(testTypeId.toArray())));
        response.then().body("[" + record + "].testTypes[0]", hasKey("certificateNumber"));
        response.then().body("testTypes.testTypeStartTimestamp", hasItem(contains(testTypeStartTimestamp.toArray())));
        response.then().body("testTypes.testTypeEndTimestamp", hasItem(contains(testTypeEndTimestamp.toArray())));
        response.then().body("testTypes.numberOfSeatbeltsFitted", hasItem(contains(numberOfSeatbeltsFitted.toArray())));
        response.then().body("testTypes.lastSeatbeltInstallationCheckDate", hasItem(contains(lastSeatbeltInstallationCheckDate.toArray())));
        response.then().body("testTypes.seatbeltInstallationCheckDate", hasItem(contains(seatbeltInstallationCheckDate.toArray())));
        response.then().body("testTypes.prohibitionIssued", hasItem(contains(prohibitionIssued.toArray())));
        response.then().body("testTypes.testResult", hasItem(contains(testResult.toArray())));
        response.then().body("testTypes.reasonForAbandoning", hasItem(contains(reasonForAbandoning.toArray())));
        response.then().body("testTypes.additionalNotesRecorded", hasItem(contains(additionalNotesRecorded.toArray())));
        response.then().body("testTypes.name", hasItem(contains(name.toArray())));
        response.then().body("testTypes.additionalCommentsForAbandon", hasItem(contains(additionalCommentsForAbandon.toArray())));
    }

    @Step
    private void validateDefectsData(TestResults testResults) {
        int record = 0;
        for (int i = 0; i < (Integer) response.jsonPath().get("size()"); i++) {
            System.out.println("i = " + i);
            List<String> startTimeList = response.jsonPath().getList("testStartTimestamp");
            System.out.println("testStartTimestamp is: " + startTimeList.get(i));
            if (startTimeList.get(i).equals(testResults.getTestStartTimestamp())) {
                System.out.println("testStartTimestamp: " + startTimeList.get(i) + "activities id: " + testResults.getTestStartTimestamp());
                record = i;
                break;
            }
        }
            response.then().body("[" + record + "].testTypes.defects.size()", is(1));
            response.then().body("[" + record + "].testTypes[0].defects[0].size()", is(Defects.class.getDeclaredFields().length));

            List<List<Integer>> imNumber = testResults.getTestTypes().stream().map(s -> s.getDefects().stream().map(Defects::getImNumber).collect(toList())).collect(toList());
            List<List<String>> imDescription = testResults.getTestTypes().stream().map(s -> s.getDefects().stream().map(Defects::getImDescription).collect(toList())).collect(toList());
            List<List<Integer>> itemNumber = testResults.getTestTypes().stream().map(s -> s.getDefects().stream().map(Defects::getItemNumber).collect(toList())).collect(toList());
            List<List<String>> itemDescription = testResults.getTestTypes().stream().map(s -> s.getDefects().stream().map(Defects::getItemDescription).collect(toList())).collect(toList());
            List<List<String>> deficiencyRef = testResults.getTestTypes().stream().map(s -> s.getDefects().stream().map(Defects::getDeficiencyRef).collect(toList())).collect(toList());
            List<List<String>> deficiencyId = testResults.getTestTypes().stream().map(s -> s.getDefects().stream().map(Defects::getDeficiencyId).collect(toList())).collect(toList());
            List<List<String>> deficiencySubId = testResults.getTestTypes().stream().map(s -> s.getDefects().stream().map(Defects::getDeficiencySubId).collect(toList())).collect(toList());
            List<List<String>> deficiencyCategory = testResults.getTestTypes().stream().map(s -> s.getDefects().stream().map(Defects::getDeficiencyCategory).collect(toList())).collect(toList());
            List<List<String>> deficiencyText = testResults.getTestTypes().stream().map(s -> s.getDefects().stream().map(Defects::getDeficiencyText).collect(toList())).collect(toList());
            List<List<Boolean>> stdForProhibition = testResults.getTestTypes().stream().map(s -> s.getDefects().stream().map(Defects::getStdForProhibition).collect(toList())).collect(toList());
            List<List<Boolean>> prs = testResults.getTestTypes().stream().map(s -> s.getDefects().stream().map(Defects::getPrs).collect(toList())).collect(toList());
            List<List<Boolean>> prohibitionIssued = testResults.getTestTypes().stream().map(s -> s.getDefects().stream().map(Defects::getProhibitionIssued).collect(toList())).collect(toList());

            response.then().body("testTypes.defects.imNumber", hasItem(contains(imNumber.toArray())));
            response.then().body("testTypes.defects.imDescription", hasItem(contains(imDescription.toArray())));
            response.then().body("testTypes.defects.itemNumber", hasItem(contains(itemNumber.toArray())));
            response.then().body("testTypes.defects.itemDescription", hasItem(contains(itemDescription.toArray())));
            response.then().body("testTypes.defects.deficiencyRef", hasItem(contains(deficiencyRef.toArray())));
            response.then().body("testTypes.defects.deficiencyId", hasItem(contains(deficiencyId.toArray())));
            response.then().body("testTypes.defects.deficiencySubId", hasItem(contains(deficiencySubId.toArray())));
            response.then().body("testTypes.defects.deficiencyCategory", hasItem(contains(deficiencyCategory.toArray())));
            response.then().body("testTypes.defects.deficiencyText", hasItem(contains(deficiencyText.toArray())));
            response.then().body("testTypes.defects.stdForProhibition", hasItem(contains(stdForProhibition.toArray())));
            response.then().body("testTypes.defects.prs", hasItem(contains(prs.toArray())));
            response.then().body("testTypes.defects.prohibitionIssued", hasItem(contains(prohibitionIssued.toArray())));
    }

    @Step
    private void validateAdditionalInformation(TestResults testResults) {
        int record = 0;
        for (int i = 0; i < (Integer) response.jsonPath().get("size()"); i++) {
            System.out.println("i = " + i);
            List<String> startTimeList = response.jsonPath().getList("testStartTimestamp");
            System.out.println("testStartTimestamp is: " + startTimeList.get(i));
            if (startTimeList.get(i).equals(testResults.getTestStartTimestamp())) {
                System.out.println("testStartTimestamp: " + startTimeList.get(i) + "activities id: " + testResults.getTestStartTimestamp());
                record = i;
                break;
            }
        }
        response.then().body("[" + record + "].testTypes.defects.additionalInformation.size()", is(1));
        response.then().body("[" + record + "].testTypes[0].defects[0].additionalInformation.size()", is(AdditionalInformation.class.getDeclaredFields().length));

        List<List<String>> additionalInformation = testResults.getTestTypes().stream().map(s -> s.getDefects().stream().map(y -> y.getAdditionalInformation().getNotes()).collect(toList())).collect(toList());
        response.then().body("testTypes.defects.additionalInformation.notes", hasItem(contains(additionalInformation.toArray())));
    }

    @Step
    private void validateLocation(TestResults testResults) {
        int record = 0;
        for (int i = 0; i < (Integer) response.jsonPath().get("size()"); i++) {
            System.out.println("i = " + i);
            List<String> startTimeList = response.jsonPath().getList("testStartTimestamp");
            System.out.println("testStartTimestamp is: " + startTimeList.get(i));
            if (startTimeList.get(i).equals(testResults.getTestStartTimestamp())) {
                System.out.println("testStartTimestamp: " + startTimeList.get(i) + "activities id: " + testResults.getTestStartTimestamp());
                record = i;
                break;
            }
        }
        response.then().body("["+ record +"].testTypes.defects.additionalInformation.location.size()", is(1));

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
    public void validatePostErrorData(String field, String errorMessage) {
        response.then().body("size()", is(1));
        response.then().body("errors.size()", greaterThanOrEqualTo(1));
        response.then().body("errors[0]", equalTo("\"" + field + "\" " + errorMessage));
    }

    @Step
    public void validatePostErrorDataContains(String field, String errorMessage) {
        response.then().body("size()", is(1));
        response.then().body("errors.size()", greaterThanOrEqualTo(1));
        response.then().content( "errors", hasItem("\"" + field + "\" " + errorMessage));
    }

    @Step
    public void validateTestCode(TestResultsGet data, String... expectedTestCodes) {
        for (int i = 0; i < data.getTestTypes().size(); i++) {
            String testCode = response.jsonPath().setRoot("[0].testTypes").getList(" findAll { it.testTypeId == '" + data.getTestTypes().get(i).getTestTypeId() + "' }.testCode ").get(0).toString();
            assertThat(testCode, is(equalTo(expectedTestCodes[i])));
        }

    }

    @Step
    public void validateTestNumberEqualsCertificateNumber() {
        String testNumber = response.jsonPath().getString("[0].testTypes[0].testNumber");
        String certificateNumber = response.jsonPath().getString("[0].testTypes[0].certificateNumber");
        assertThat(certificateNumber.equals(testNumber)).isTrue();
    }

    @Step
    public void validateTestNumberNotEqualCertificateNumber() {
        String testNumber = response.jsonPath().getString("[0].testTypes[0].testNumber");
        String certificateNumber = response.jsonPath().getString("[0].testTypes[0].certificateNumber");
        assertThat(certificateNumber.equals(testNumber)).isFalse();
    }

    @Step
    public void validateTestNumberIsDifferentForTwoTestTypes(){
        String testNumberOne = response.jsonPath().getString("[0].testTypes[0].testNumber");
        String testNumberTwo = response.jsonPath().getString("[0].testTypes[1].testNumber");
        assertThat(testNumberOne.equals(testNumberTwo)).isFalse();

    }

    @Step
    public void validateTestNumberIsNotNull() {
        assertThat(validateTestNumber()).isTrue();
    }

    @Step
    public void validateTestNumberIsNull() {
        assertThat(validateTestNumber()).isFalse();
    }

    private boolean validateTestNumber() {
        String testNumber = response.jsonPath().getString("[0].testTypes[0].testNumber");
        if (testNumber != null && !testNumber.isEmpty()) {
            assertThat(isTestNumberChecksumValid(testNumber)).isTrue();
            assertThat(testNumber.length() == 9).isTrue();
            return true;
        } else return false;
    }

    @Step
    public void validateCertificateNumberIsNotNull() {
        assertThat(validateCertificateNumber()).isTrue();
    }

    @Step
    public void validateCertificateNumberIsNull() {
        assertThat(validateCertificateNumber()).isFalse();
    }

    private boolean validateCertificateNumber() {
        String certificateNumber = response.jsonPath().getString("[0].testTypes[0].certificateNumber");
        if (certificateNumber != null && !certificateNumber.isEmpty()) {
            return true;
        } else return false;
    }

    @Step
    public void nextTestNumber() {
        String testNumber = response.jsonPath().getString("[0].testTypes[0].testNumber");
        nextTestNumber = computeNextTestNumber(testNumber.substring(0,3),testNumber.substring(3,4),testNumber.substring(4,7));
    }

    @Step
    public void checkNextTestNumberIsValid(){
        String testNumber = response.jsonPath().getString("[0].testTypes[0].testNumber");
        assertThat(testNumber.equals(nextTestNumber)).isTrue();
    }

    @Step
    public void validateVehicleFieldValue(String key, String value) {
        response.then().log().all();
        validateVehicleFieldExists(key);
        assertThat(response.then().body("$", anyOf(hasItem(hasEntry(key,value)))));
    }

    @Step
    public void validateVehicleFieldValue(String key, int value) {
//        response.then().log().all();
        validateVehicleFieldExists(key);
        assertThat(response.then().body("$", anyOf(hasItem(hasEntry(key,value)))));
    }

    @Step
    public void validateTestTypeFieldValue(int testNumber, String key, String value) {
//        response.then().log().all();
        String returnedValue = response.jsonPath().getString("[0]." + key);
        assertThat(returnedValue.equals(value)).isTrue();
    }

    @Step
    public void validateVehicleFieldExists(String fieldName) {
//        response.then().log().all();
        assertThat(response.then().body("$", everyItem(hasKey(fieldName))));
    }

    @Step
    public void validateTestFieldExists(String fieldName) {
//        response.then().log().all();
        hasTests();
        assertThat(response.then().body("[0].testTypes[0]", hasKey(fieldName)));
    }

    @Step
    public void hasTests(){
        assertThat(response.then().body("[0].testTypes", hasItems()));
    }


    @Step
    public void postTestResultsTestTypesFieldsRemoved(TestResults testResults, String ...removeFields) {
        response = testResultsClient.postTestResultsTestTypeFieldsRemoved(testResults, removeFields);
    }

    @Step
    public void addAdditionalTestResultsFieldValue(ObjectNode payload, String fieldName, String fieldValue) {
            payload.put(fieldName, fieldValue);
    }

    @Step
    public void addAdditionalTestResultsFieldValue(ObjectNode payload, String fieldName, int fieldValue) {
            payload.put(fieldName, fieldValue);
    }

    @Step
    public void addAdditionalTestResultsTestTypesFields(ObjectNode payload, int testInArray, String field, String value) {
        ((ObjectNode)payload.get("testTypes").get(testInArray)).put(field,value);
    }

    @Step
    public void addAdditionalTestResultsTestTypesFields(ObjectNode payload, int testInArray, String field, int value) {
        ((ObjectNode)payload.get("testTypes").get(testInArray)).put(field,value);
    }

    @Step
    public void removeTestResultsFields(ObjectNode payload, String ...fields) {
        payload.remove(Arrays.asList(fields));
    }

    @Step
    public void removeTestResultsTestTypesFields(ObjectNode payload, int testInArray, String ...fields) {
        ((ObjectNode)payload.get("testTypes").get(testInArray)).remove(Arrays.asList(fields));
    }

    @Step
    public void postTestResultsPayload(ObjectNode payload) {
        response = testResultsClient.postTestResults(payload);
    }

    @Step
    public void changeTestResultsFieldValue(ObjectNode payload, String field, int valueAsInt) {
        addAdditionalTestResultsFieldValue(payload, field, valueAsInt);
    }

    @Step
    public void changeTestResultsFieldValue(ObjectNode payload, String field, String valueAsString) {
        addAdditionalTestResultsFieldValue(payload, field, valueAsString);
    }

    @Step
    public void changeTestResultsTestTypesFields(ObjectNode payload, int testInArray, String field, String value) {
        addAdditionalTestResultsTestTypesFields(payload,testInArray,field,value);
    }

    @Step
    public void changeTestResultsTestTypesFields(ObjectNode payload, int testInArray, String field, int value) {
        addAdditionalTestResultsTestTypesFields(payload,testInArray,field,value);
    }


}
