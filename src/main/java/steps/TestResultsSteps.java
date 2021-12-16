package steps;

import clients.TestResultsClient;
import clients.TestTypesClient;
import clients.VehicleTechnicalRecordsClient;
import clients.util.ToTypeConvertor;
import clients.util.testresult.TestResultsLevel;
import com.fasterxml.jackson.databind.node.ObjectNode;
import data.GenericData;
import exceptions.AutomationException;
import io.restassured.response.Response;
import model.testresults.*;
import net.thucydides.core.annotations.Step;
import org.json.JSONObject;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.json.JSONException;
import org.skyscreamer.jsonassert.JSONAssert;
import util.AwsUtil;
import util.JsonPathAlteration;
import util.WebDriverBrowsertack;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    VehicleTechnicalRecordsClient vehicleTechnicalRecordsClient = new VehicleTechnicalRecordsClient();
    TestTypesClient testTypesClient = new TestTypesClient();
    Response response;
    private static String nextTestNumber = "";

    LocalDateTime testStartDate;
    @Before
    public void beforeTest() {
        this.testStartDate = LocalDateTime.now();
    }

    @Step
    public Response getTestResults(String systemNumber) {
        return response = testResultsClient.getTestResults(systemNumber);
    }

    @Step
    public Response getTestResultsNo404(String systemNumber, int numberOfRetries) {
        return response = testResultsClient.getTestResultsNo404(systemNumber, numberOfRetries);
    }

    @Step
    public void getTestResultsSysNumber(String systemNumber) {
        response = testResultsClient.getTestResultsSysNumber(systemNumber);
    }

    @Step
    public void getTestResultsNotAuthenticated(String systemNumber) {
        setMissingAuth();
        response = testResultsClient.callGetTestResults(systemNumber);
        setRightAuth();
    }

    @Step
    public void getTestResultsNotAuthorised(String systemNumber) {
        setWrongAuth();
        response = testResultsClient.callGetTestResults(systemNumber);
        setRightAuth();
    }

    @Step
    public void getTestResults(String systemNumber, TestResultsStatus testResultsStatus) {
        response = testResultsClient.getTestResultsWithStatus(systemNumber, testResultsStatus.getStatus());
    }

    @Step
    public void getTestResults(String systemNumber, TestVersion testVersion , String testResultId ) {
        response = testResultsClient.getTestResultsWithVersion(systemNumber, testVersion.getTestVersion(), testResultId);
    }

    @Step
    public void getTestResultsWithStatusAndSysNumber(String systemNumber, TestResultsStatus testResultsStatus) {
        response = testResultsClient.getTestResultsWithStatusAndSysNumber(systemNumber, testResultsStatus.getStatus());
    }

    @Step
    public void postTestResults(TestResults testResults) {
        response = testResultsClient.postTestResults(testResults);
    }

    @Step
    public void postTestResultsNotAuthorised(TestResults testResults) {
        setWrongAuth();
        response = testResultsClient.callPostTestResults(testResults);
        setRightAuth();
    }

    @Step
    public void postTestResultsNotAuthenticated(TestResults testResults) {
        setMissingAuth();
        response = testResultsClient.callPostTestResults(testResults);
        setRightAuth();
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
    public void getTestResultsFromDate(String systemNumber, String fromDate) {
        response = testResultsClient.getTestResultsFromDateTime(systemNumber, fromDate);
    }

    @Step
    public void getTestResultsFromDateAndSysNum(String systemNumber, String fromDate) {
        response = testResultsClient.getTestResultsFromDateTimeAndSysNum(systemNumber, fromDate);
    }

    @Step
    public void getTestResultsFromDate(String systemNumber, String fromDate, TestResultsStatus testResultsStatus) {
        response = testResultsClient.getTestResultsFromDateTime(systemNumber, fromDate, testResultsStatus.getStatus());
    }

    @Step
    public void getTestResultsToDate(String systemNumber, String toDate) {
        response = testResultsClient.getTestResultsToDateTime(systemNumber, toDate);
    }

    @Step
    public void getTestResultsToDate(String systemNumber, String toDate, TestResultsStatus testResultsStatus) {
        response = testResultsClient.getTestResultsToDateTime(systemNumber, toDate, testResultsStatus.getStatus());
    }

    @Step
    public void getTestResultsBetweenDate(String systemNumber, String fromDate, String toDate) {
        response = testResultsClient.getTestResultsBetweenDate(systemNumber, fromDate, toDate);
    }

    @Step
    public void getTestResultsBetweenDate(String systemNumber, String fromDate, String toDate, TestResultsStatus testResultsStatus) {
        response = testResultsClient.getTestResultsBetweenDate(systemNumber, fromDate, toDate, testResultsStatus.getStatus());
    }

    @Step
    public void statusCodeShouldBe(int statusCode) {
        response.then().log().all()
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
        response.then().log().all()
                .body("message", equalTo(stringData));
    }

    @Step
    public void validateErrorText(String stringData) {
        response.then().equals(stringData);
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
        // Commenting this out as the number of fields for tech record is constantly changing
        //response.then().body("[" + record + "].size()", is(TestResultsGet.class.getDeclaredFields().length + TestResultsGet.class.getSuperclass().getDeclaredFields().length - fieldsNotInGet.size()));
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
        // Commenting this out as the number of fields for tech record is constantly changing
        //response.then().body("[" + record + "][0].testTypes[0]", hasKey("certificateNumber"));
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
             // Commenting this out as the number of fields for tech record is constantly changing
            //response.then().body("[" + record + "].testTypes[0].defects[0].size()", is(Defects.class.getDeclaredFields().length));
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
        Object fieldName = "\"" + field + "\" ";
        response.then().body("size()", is(1));
        System.out.println("RESPONSE ERRORS" + " " + " " +response.then().body("errors.size()", greaterThanOrEqualTo(1)));
        response.then().body("errors.size()", greaterThanOrEqualTo(1));
        response.then().body("errors[0]", equalTo( fieldName + errorMessage));
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
    public void valueForFieldInPathShouldContains(String path, String expectedValue) {
        response.then().body(path, containsString(expectedValue));
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
        System.out.println("TestNumber: " + testNumber);
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
        return certificateNumber != null && !certificateNumber.isEmpty();
    }

    @Step
    public boolean validateCertificateNumberLength() {
        String certificateNumber = response.jsonPath().getString("[0].testTypes[0].certificateNumber");
        return certificateNumber.length() == 9;
    }

    @Step
    public String nextTestNumber() {
        String testNumber = response.jsonPath().getString("[0].testTypes[0].testNumber");
        System.out.println("\ntestNumber is: " + testNumber +"\n");
        nextTestNumber = computeNextTestNumber(testNumber.substring(0,3),testNumber.substring(3,4),testNumber.substring(4,7));
        return nextTestNumber;
    }

    @Step
    public void checkNextTestNumberIsValid(String nextTestNumber){
        String testNumber = response.jsonPath().getString("[0].testTypes[0].testNumber");
        System.out.println("nextTestNumber: " + nextTestNumber + " and the actual is: " + testNumber);
        assertThat(testNumber.equals(nextTestNumber)).isTrue();
    }
    @Step
    public void validateVehicleFieldValue(String key, String value) {
        response.then().log().all();
        validateVehicleFieldExists(key);
        assertThat(response.then().body("$", anyOf(hasItem(hasEntry(key,value)))));
    }
    @Step
    public void valueForFieldInPathShouldBe(String path, String expectedValue) {
        System.out.println("Verifying that " + path + " has value " + expectedValue);
        response.then().body(path, equalTo(expectedValue));
    }

    @Step
    public void valueForFieldInPathShouldContain(String path, String expectedValue) {
        System.out.println("Verifying that " + path + " has value " + expectedValue);
        response.then().body(path, containsString(expectedValue));
    }

    @Step
    public void fieldInPathShouldNotExist(String parentElementPath, String key) {
        response.then().body(parentElementPath,not(hasKey(key)));
    }

    @Step
    public void valueForFieldInPathShouldBe(String path, int expectedValue) {
        response.then().body(path, equalTo(expectedValue));
    }

    @Step
    public void valueForFieldInPathShouldNotBe(String path, Object expectedValue) {
        response.then().body(path, not(equalTo(expectedValue)));
    }

    @Step
    public void validateResponseContainsJson(String jsonPathOfResponseExtractedField, String expectedJson) {
        String actualJson = GenericData.getJsonStringFromHashMap(response.then().extract().path(jsonPathOfResponseExtractedField));
        try {
            JSONAssert.assertEquals("The response does not contain the required data", expectedJson,
                    actualJson, false);
        } catch (final JSONException exc) {
            throw new RuntimeException(exc);
        }

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
    public void validateVehicleFieldMayExist(String fieldName) {
//        response.then().log().all();
        assertThat(response.then().body("$", anyOf(hasItem(hasKey(fieldName)))));
    }

    @Step
    public void validateTestFieldExists(String fieldName) {
//        response.then().log().all();
        hasTests();
        assertThat(response.then().body("[0].testTypes[0]", hasKey(fieldName)));
    }

    @Step
    public void validateTestFieldNotPresent(String fieldName) {
//        response.then().log().all();
        hasTests();
        assertThat(response.then().body("[0].testTypes[0]", not(hasKey(fieldName))));
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

    @Step
    public void validateCertificateNumberIsNotNull(TestResultsGet testResults) {

        int record = 0;
        for (int i = 0; i < (Integer) response.jsonPath().get("size()"); i++) {
            System.out.println("i = " + i);
            List<String> startTimeList = response.jsonPath().getList("testStartTimestamp");
            System.out.println("testStartTimestamp is: " + startTimeList.get(i));
            if (startTimeList.get(i).equals(testResults.getTestStartTimestamp())) {
                System.out.println("testStartTimestamp: " + startTimeList.get(i) + " activities id: " + testResults.getTestStartTimestamp());
                record = i;
                break;
            }
        }

        response.then().body("[" + record + "].testTypes[0].certificateNumber", not(equalTo(nullValue())));
    }

    @Step
    public void validateExpiryDateIsNotNull(TestResultsGet testResults) {
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
        response.then().body("[" + record + "].testTypes[0].testExpiryDate", not(equalTo(nullValue())));
    }

    @Step
    public void postVehicleTestResultsWithAlterations(String requestBody, List<JsonPathAlteration> alterations) {
        this.response = testResultsClient.postVehicleTestResultsWithAlterations(requestBody, alterations);
    }

    @Step
    public String putTestResultsWithAlterations(String systemNumber, String putRequestBody, List<JsonPathAlteration> alterations) {
        response = testResultsClient.putTestResultsWithAlterations(systemNumber, putRequestBody, alterations);
        return response.prettyPrint();
    }

    @Step
    public String putTestResultsWithAlterationsNo400(String systemNumber, String putRequestBody, List<JsonPathAlteration> alterations) {
        response = testResultsClient.putTestResultsWithAlterationsNo400(systemNumber, putRequestBody, alterations);
        return response.prettyPrint();
    }

    @Step
    public void getTestResults(String systemNumber, TestVersion testVersion , String testResultId , TestResultsStatus testResultsStatus) {
        response = testResultsClient.getTestResultsWithVersionAndStatus(systemNumber,testVersion.getTestVersion(),testResultId,testResultsStatus.getStatus());
    }

    @Step
    public void postVehicleTestResultsWithNoAuthorization(String requestBody) {
        setWrongAuth();
        this.response = testResultsClient.callPostVehicleTestResultsWithNoAuthorization(requestBody);
        setRightAuth();
    }

    @Step
    public String getTestNumber() {
        return response.jsonPath().getString("[0].testTypes[0].testNumber");
    }

    @Step
    public String getTestNumberForPut() {
        return response.jsonPath().getString("testTypes[0].testNumber");
    }

    @Step
    public String getTestCode() {
        return response.jsonPath().getString("[0].testTypes[0].testCode");
    }

    @Step
    public void validateCertificateIsGenerated(String testNumber, String vin) {
        assertThat(AwsUtil.isCertificateCreated(testNumber, vin)).isTrue();
    }

    @Step
    public void validateCertificateIsNotGenerated(String testNumber, String vin) {
        assertThat(AwsUtil.isCertificateCreated(testNumber,vin)).isFalse();
    }

    @Step
    @Deprecated
    public void insertRecordInDynamo(String json, String table) {
        AwsUtil.insertJsonInTable(json, table);
    }


    @Step
    public void insertRecordInDynamo(String json, String table, String primaryKey) {
        AwsUtil.insertJsonInTable(json, table, primaryKey);
    }

    @Step
    public void deleteActivitiesForUser(String user) {
        AwsUtil.deleteActivitiesForUser(user);
    }

    @Step
    public WebDriver validateVsaEmail(String randomVrm) {
        return WebDriverBrowsertack.checkVsaEmail(randomVrm);
    }

    @Step
    public String getOutlookEmailAddress() {
        return testResultsClient.getOutlookEmailAddress();
    }

    @Step
    public void validateVsaEmailDetails(WebDriver driver, String randomVrm, String testName, String date) {
        WebDriverBrowsertack.checkVsaEmailDetails(driver, randomVrm, testName, date);
    }

    @Step
    public void valueForFieldInPathShouldStartWith(String path, String expectedValue) {
        System.out.println("Verifying that " + path + " starts with value " + expectedValue);
        response.then().body(path, startsWith(expectedValue));
    }
    @Step
    public void valueForFieldInPathShouldEndWith(String path, String expectedValue) {
        System.out.println("Verifying that " + path + " ends with value " + expectedValue);
        response.then().body(path, endsWith(expectedValue));
    }

    @Step
    public void checkAwsDispatcherLogContains(String key, String value) {
        String keyValuePair = "\""+key+"\"" + ":{\"S\":\"" + value + "\"}";
        assertThat(AwsUtil.checkLogsFor("/aws/lambda/edh-dispatcher", keyValuePair)).isTrue();
    }

    @Step
    public void checkAwsMarshallerLogContains(String key, String value) {
        String keyValuePair = key+": { S: '" + value + "' }";
        assertThat(AwsUtil.checkLogsFor("/aws/lambda/edh-marshaller", keyValuePair)).isTrue();
    }

    @Step
    public void checkAwsDispatcherLogStatusCodeForSystemNumber(String systemNumber, int httpCode) {
        String keyValuePair1 = "\"systemNumber\":\"" + systemNumber + "\"";
        String keyValuePair2 = "statusCode: " + httpCode;
        assertThat(AwsUtil.checkDispatcherLogsForData(keyValuePair1, keyValuePair2)).isTrue();
    }

    @Step
    public void cleanUpTestResultsOfTestTypeId(String testResultId) {
        AwsUtil.deleteTestResultId(testResultId);
    }

    @Step
    public String  createTestRecord(String testStatus, String testResult, String testCode, boolean withWithoutDefects,
                                    Map<String, Object> testResultAttributes) {
        return testResultsClient.createTestRecord(testStatus, testResult, testCode, withWithoutDefects, testResultAttributes);
    }

    @Step
    public void waitForTestResultsToBeUpdated(String sn, int iteration) {

        System.out.println("...waiting " + iteration + " iterations for the record to be updated...\n");

        for (int i = 0; i < iteration; i++) {
            response = testResultsClient.getTestResults(sn);

            int status = response.getStatusCode();

            if (status == 200) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return;
            } else {
                System.out.println("\n...waiting one more second (" + i + ")...\n");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Step
    public String getTesterName() {
        return response.jsonPath().getString("[0].testerName");
    }

    @Step
    public String getTesterStaffId() {
        return response.jsonPath().getString("[0].testerStaffId");
    }

    @Step
    public void sleep() {
        try {
            Thread.sleep(6000);
        } catch(Exception e) {
            System.out.println(e);
        }
    }

    @Step
    public void waitForTestResultsUpdate(String systemNumber, int iteration, LocalDateTime testStartDate) {
        System.out.println("...waiting " + iteration + " iterations for test results to be updated...\n");

        for(int i=0; i < iteration; i++) {
            response = testResultsClient.getTestResults(systemNumber);

            int status = response.getStatusCode();
            int noTestResults = response.then().extract().jsonPath().getInt("$.size()");
            for (int j = 0; j < noTestResults; j++) {
                int recordsNumber = response.then().log().all().extract().jsonPath().get("[" + j + "].testTypes.size()");
                for (int k = 0; k < recordsNumber; k++) {
                    String testTypeEndTimeStampString = response.then().log().all().extract().jsonPath().get("[" + j +"].testTypes[" + k + "].testTypeEndTypeStamp");

                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
                    LocalDateTime testTypeEndTypeStamp = LocalDateTime.parse(testTypeEndTimeStampString, formatter);
                    System.out.println("TestTypeEndTimeStamp" + " " + testTypeEndTypeStamp);

                    System.out.println(" for vehicle [" + j + "] status is: " + status + " and number of records: " + noTestResults);

                    if (status == 200 && testTypeEndTypeStamp.isAfter(testStartDate) ) {
                        return;
                    }
                }


            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("\n...waiting 1 more second (" + i + ")...\n");

        }
        System.out.println("\n...Vehicle status has not been updated in " + iteration +" seconds...");
    }
}
