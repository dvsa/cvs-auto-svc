package testresults.expiry_date;

import data.GenericData;
import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.junit.annotations.TestData;
import net.thucydides.junit.annotations.UseTestDataFrom;
import org.apache.commons.lang3.RandomStringUtils;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import steps.TestResultsSteps;
import util.JsonPathAlteration;

import java.util.*;

@WithTag("expiry_dates")
@RunWith(SerenityParameterizedRunner.class)
@UseTestDataFrom(value="loader/testdata/test_data_expiry_date_hgv_first_test.csv")
public class PostTestResultsExpiryDateLogicHgvFirstTest {

    @Steps
    TestResultsSteps testResultsSteps;

//    @TestData
//    public static Collection<Object[]> testData(){
//        return Arrays.asList(new Object[][]{
//                {"First test", "First test", "95", 2, "pass", "ffv2"},
//                {"First test", "First test", "95", 3, "pass", "ffv3"},
//                {"First test", "First test", "95", 4, "pass", "ffv4"},
//                {"First test", "First test", "95", 5, "pass", "ffv5"},
//                {"First test", "First test", "95", 6, "pass", "ffv5"},
//                {"First test", "First test", "95", 7, "pass", "ffv5"},
//                {"First test", "First test", "95", 8, "pass", "ffv5"},
//                {"First test", "First test", "95", 9, "pass", "ffv5"},
//                {"First test", "First test", "95", 10, "pass", "ffv5"},
//                {"Paid", "Paid first test retest", "65", 2, "pass", "rgv2"},
//                {"Paid", "Paid first test retest", "65", 3, "pass", "rgv3"},
//                {"Paid", "Paid first test retest", "65", 4, "pass", "rgv4"},
//                {"Paid", "Paid first test retest", "65", 5, "pass", "rgv5"},
//                {"Paid", "Paid first test retest", "65", 6, "pass", "rgv5"},
//                {"Paid", "Paid first test retest", "65", 7, "pass", "rgv5"},
//                {"Paid", "Paid first test retest", "65", 8, "pass", "rgv5"},
//                {"Paid", "Paid first test retest", "65", 9, "pass", "rgv5"},
//                {"Paid", "Paid first test retest", "65", 10, "pass", "rgv5"},
//                {"Part paid", "Part paid first test retest", "66", 2, "pass", "riv2"},
//                {"Part paid", "Part paid first test retest", "66", 3, "pass", "riv3"},
//                {"Part paid", "Part paid first test retest", "66", 4, "pass", "riv4"},
//                {"Part paid", "Part paid first test retest", "66", 5, "pass", "riv5"},
//                {"Part paid", "Part paid first test retest", "66", 6, "pass", "riv5"},
//                {"Part paid", "Part paid first test retest", "66", 7, "pass", "riv5"},
//                {"Part paid", "Part paid first test retest", "66", 8, "pass", "riv5"},
//                {"Part paid", "Part paid first test retest", "66", 9, "pass", "riv5"},
//                {"Part paid", "Part paid first test retest", "66", 10, "pass", "riv5"},
////                {"Full Inspection/ Fee - With certification", "Paid prohibition clearance on first test (full inspection with certification)", "82", 2, "pass", "p7v2"},
////                {"Full Inspection/ Fee - With certification", "Paid prohibition clearance on first test (full inspection with certification)", "82", 3, "pass", "p7v3"},
////                {"Full Inspection/ Fee - With certification", "Paid prohibition clearance on first test (full inspection with certification)", "82", 4, "pass", "p7v4"},
////                {"Full Inspection/ Fee - With certification", "Paid prohibition clearance on first test (full inspection with certification)", "82", 5, "pass", "p7v5"},
////                {"Full Inspection/ Fee - With certification", "Paid prohibition clearance on first test (full inspection with certification)", "82", 6, "pass", "p7v5"},
////                {"Full Inspection/ Fee - With certification", "Paid prohibition clearance on first test (full inspection with certification)", "82", 7, "pass", "p7v5"},
////                {"Full Inspection/ Fee - With certification", "Paid prohibition clearance on first test (full inspection with certification)", "82", 8, "pass", "p7v5"},
////                {"Full Inspection/ Fee - With certification", "Paid prohibition clearance on first test (full inspection with certification)", "82", 9, "pass", "p7v5"},
////                {"Full Inspection/ Fee - With certification", "Paid prohibition clearance on first test (full inspection with certification)", "82", 10, "pass", "p7v5"},
////                {"Retest - Paid", "Paid retest prohibition clearance on first test", "83", 2, "pass", "p4v2"},
////                {"Retest - Paid", "Paid retest prohibition clearance on first test", "83", 3, "pass", "p4v3"},
////                {"Retest - Paid", "Paid retest prohibition clearance on first test", "83", 4, "pass", "p4v4"},
////                {"Retest - Paid", "Paid retest prohibition clearance on first test", "83", 5, "pass", "p4v5"},
////                {"Retest - Paid", "Paid retest prohibition clearance on first test", "83", 6, "pass", "p4v5"},
////                {"Retest - Paid", "Paid retest prohibition clearance on first test", "83", 7, "pass", "p4v5"},
////                {"Retest - Paid", "Paid retest prohibition clearance on first test", "83", 8, "pass", "p4v5"},
////                {"Retest - Paid", "Paid retest prohibition clearance on first test", "83", 9, "pass", "p4v5"},
////                {"Retest - Paid", "Paid retest prohibition clearance on first test", "83", 10, "pass", "p4v5"},
////                {"Annual test", "Annual test", "94", 2, "pass", "aav2"},
////                {"Annual test", "Annual test", "94", 3, "pass", "aav3"},
////                {"Annual test", "Annual test", "94", 4, "pass", "aav4"},
////                {"Annual test", "Annual test", "94", 5, "pass", "aav5"},
////                {"Annual test", "Annual test", "94", 6, "pass", "aav5"},
////                {"Annual test", "Annual test", "94", 7, "pass", "aav5"},
////                {"Annual test", "Annual test", "94", 8, "pass", "aav5"},
////                {"Annual test", "Annual test", "94", 9, "pass", "aav5"},
////                {"Annual test", "Annual test", "94", 10, "pass", "aav5"},
////                {"Paid", "Paid annual test retest", "53", 2, "pass", "rpv2"},
////                {"Paid", "Paid annual test retest", "53", 3, "pass", "rpv3"},
////                {"Paid", "Paid annual test retest", "53", 4, "pass", "rpv4"},
////                {"Paid", "Paid annual test retest", "53", 5, "pass", "rpv5"},
////                {"Paid", "Paid annual test retest", "53", 6, "pass", "rpv5"},
////                {"Paid", "Paid annual test retest", "53", 7, "pass", "rpv5"},
////                {"Paid", "Paid annual test retest", "53", 8, "pass", "rpv5"},
////                {"Paid", "Paid annual test retest", "53", 9, "pass", "rpv5"},
////                {"Paid", "Paid annual test retest", "53", 10, "pass", "rpv5"},
////                {"Part paid", "Part paid annual test retest", "54", 2, "pass", "rsv2"},
////                {"Part paid", "Part paid annual test retest", "54", 3, "pass", "rsv3"},
////                {"Part paid", "Part paid annual test retest", "54", 4, "pass", "rsv4"},
////                {"Part paid", "Part paid annual test retest", "54", 5, "pass", "rsv5"},
////                {"Part paid", "Part paid annual test retest", "54", 6, "pass", "rsv5"},
////                {"Part paid", "Part paid annual test retest", "54", 7, "pass", "rsv5"},
////                {"Part paid", "Part paid annual test retest", "54", 8, "pass", "rsv5"},
////                {"Part paid", "Part paid annual test retest", "54", 9, "pass", "rsv5"},
////                {"Part paid", "Part paid annual test retest", "54", 10, "pass", "rsv5"},
////                {"With certification", "Paid prohibition clearance (full inspection with certification)", "70", 2, "pass", "p1v2"},
////                {"With certification", "Paid prohibition clearance (full inspection with certification)", "70", 3, "pass", "p1v3"},
////                {"With certification", "Paid prohibition clearance (full inspection with certification)", "70", 4, "pass", "p1v4"},
////                {"With certification", "Paid prohibition clearance (full inspection with certification)", "70", 5, "pass", "p1v5"},
////                {"With certification", "Paid prohibition clearance (full inspection with certification)", "70", 6, "pass", "p1v5"},
////                {"With certification", "Paid prohibition clearance (full inspection with certification)", "70", 7, "pass", "p1v5"},
////                {"With certification", "Paid prohibition clearance (full inspection with certification)", "70", 8, "pass", "p1v5"},
////                {"With certification", "Paid prohibition clearance (full inspection with certification)", "70", 9, "pass", "p1v5"},
////                {"With certification", "Paid prohibition clearance (full inspection with certification)", "70", 10, "pass", "p1v5"},
////                {"With certification", "Paid prohibition clearance (retest with certification)", "76", 2, "pass", "p3v2"},
////                {"With certification", "Paid prohibition clearance (retest with certification)", "76", 3, "pass", "p3v3"},
////                {"With certification", "Paid prohibition clearance (retest with certification)", "76", 4, "pass", "p3v4"},
////                {"With certification", "Paid prohibition clearance (retest with certification)", "76", 5, "pass", "p3v5"},
////                {"With certification", "Paid prohibition clearance (retest with certification)", "76", 6, "pass", "p3v5"},
////                {"With certification", "Paid prohibition clearance (retest with certification)", "76", 7, "pass", "p3v5"},
////                {"With certification", "Paid prohibition clearance (retest with certification)", "76", 8, "pass", "p3v5"},
////                {"With certification", "Paid prohibition clearance (retest with certification)", "76", 9, "pass", "p3v5"},
////                {"With certification", "Paid prohibition clearance (retest with certification)", "76", 10, "pass", "p3v5"},
////                {"With certification", "Part paid prohibition clearance (retest with certification)", "79", 2, "pass", "p6v2"},
////                {"With certification", "Part paid prohibition clearance (retest with certification)", "79", 3, "pass", "p6v3"},
////                {"With certification", "Part paid prohibition clearance (retest with certification)", "79", 4, "pass", "p6v4"},
////                {"With certification", "Part paid prohibition clearance (retest with certification)", "79", 5, "pass", "p6v5"},
////                {"With certification", "Part paid prohibition clearance (retest with certification)", "79", 6, "pass", "p6v5"},
////                {"With certification", "Part paid prohibition clearance (retest with certification)", "79", 7, "pass", "p6v5"},
////                {"With certification", "Part paid prohibition clearance (retest with certification)", "79", 8, "pass", "p6v5"},
////                {"With certification", "Part paid prohibition clearance (retest with certification)", "79", 9, "pass", "p6v5"},
////                {"With certification", "Part paid prohibition clearance (retest with certification)", "79", 10, "pass", "p6v5"},
//
//                {"First test", "First test", "95", 2, "prs", "ffv2"},
//                {"First test", "First test", "95", 3, "prs", "ffv3"},
//                {"First test", "First test", "95", 4, "prs", "ffv4"},
//                {"First test", "First test", "95", 5, "prs", "ffv5"},
//                {"First test", "First test", "95", 6, "prs", "ffv5"},
//                {"First test", "First test", "95", 7, "prs", "ffv5"},
//                {"First test", "First test", "95", 8, "prs", "ffv5"},
//                {"First test", "First test", "95", 9, "prs", "ffv5"},
//                {"First test", "First test", "95", 10, "prs", "ffv5"},
//                {"Paid", "Paid first test retest", "65", 2, "prs", "rgv2"},
//                {"Paid", "Paid first test retest", "65", 3, "prs", "rgv3"},
//                {"Paid", "Paid first test retest", "65", 4, "prs", "rgv4"},
//                {"Paid", "Paid first test retest", "65", 5, "prs", "rgv5"},
//                {"Paid", "Paid first test retest", "65", 6, "prs", "rgv5"},
//                {"Paid", "Paid first test retest", "65", 7, "prs", "rgv5"},
//                {"Paid", "Paid first test retest", "65", 8, "prs", "rgv5"},
//                {"Paid", "Paid first test retest", "65", 9, "prs", "rgv5"},
//                {"Paid", "Paid first test retest", "65", 10, "prs", "rgv5"},
//                {"Part paid", "Part paid first test retest", "66", 2, "prs", "riv2"},
//                {"Part paid", "Part paid first test retest", "66", 3, "prs", "riv3"},
//                {"Part paid", "Part paid first test retest", "66", 4, "prs", "riv4"},
//                {"Part paid", "Part paid first test retest", "66", 5, "prs", "riv5"},
//                {"Part paid", "Part paid first test retest", "66", 6, "prs", "riv5"},
//                {"Part paid", "Part paid first test retest", "66", 7, "prs", "riv5"},
//                {"Part paid", "Part paid first test retest", "66", 8, "prs", "riv5"},
//                {"Part paid", "Part paid first test retest", "66", 9, "prs", "riv5"},
//                {"Part paid", "Part paid first test retest", "66", 10, "prs", "riv5"},
////                {"Full Inspection/ Fee - With certification", "Paid prohibition clearance on first test (full inspection with certification)", "82", 2, "prs", "p7v2"},
////                {"Full Inspection/ Fee - With certification", "Paid prohibition clearance on first test (full inspection with certification)", "82", 3, "prs", "p7v3"},
////                {"Full Inspection/ Fee - With certification", "Paid prohibition clearance on first test (full inspection with certification)", "82", 4, "prs", "p7v4"},
////                {"Full Inspection/ Fee - With certification", "Paid prohibition clearance on first test (full inspection with certification)", "82", 5, "prs", "p7v5"},
////                {"Full Inspection/ Fee - With certification", "Paid prohibition clearance on first test (full inspection with certification)", "82", 6, "prs", "p7v5"},
////                {"Full Inspection/ Fee - With certification", "Paid prohibition clearance on first test (full inspection with certification)", "82", 7, "prs", "p7v5"},
////                {"Full Inspection/ Fee - With certification", "Paid prohibition clearance on first test (full inspection with certification)", "82", 8, "prs", "p7v5"},
////                {"Full Inspection/ Fee - With certification", "Paid prohibition clearance on first test (full inspection with certification)", "82", 9, "prs", "p7v5"},
////                {"Full Inspection/ Fee - With certification", "Paid prohibition clearance on first test (full inspection with certification)", "82", 10, "prs", "p7v5"},
////                {"Retest - Paid", "Paid retest prohibition clearance on first test", "83", 2, "prs", "p4v2"},
////                {"Retest - Paid", "Paid retest prohibition clearance on first test", "83", 3, "prs", "p4v3"},
////                {"Retest - Paid", "Paid retest prohibition clearance on first test", "83", 4, "prs", "p4v4"},
////                {"Retest - Paid", "Paid retest prohibition clearance on first test", "83", 5, "prs", "p4v5"},
////                {"Retest - Paid", "Paid retest prohibition clearance on first test", "83", 6, "prs", "p4v5"},
////                {"Retest - Paid", "Paid retest prohibition clearance on first test", "83", 7, "prs", "p4v5"},
////                {"Retest - Paid", "Paid retest prohibition clearance on first test", "83", 8, "prs", "p4v5"},
////                {"Retest - Paid", "Paid retest prohibition clearance on first test", "83", 9, "prs", "p4v5"},
////                {"Retest - Paid", "Paid retest prohibition clearance on first test", "83", 10, "prs", "p4v5"},
////                {"Annual test", "Annual test", "94", 2, "prs", "aav2"},
////                {"Annual test", "Annual test", "94", 3, "prs", "aav3"},
////                {"Annual test", "Annual test", "94", 4, "prs", "aav4"},
////                {"Annual test", "Annual test", "94", 5, "prs", "aav5"},
////                {"Annual test", "Annual test", "94", 6, "prs", "aav5"},
////                {"Annual test", "Annual test", "94", 7, "prs", "aav5"},
////                {"Annual test", "Annual test", "94", 8, "prs", "aav5"},
////                {"Annual test", "Annual test", "94", 9, "prs", "aav5"},
////                {"Annual test", "Annual test", "94", 10, "prs", "aav5"},
////                {"Paid", "Paid annual test retest", "53", 2, "prs", "rpv2"},
////                {"Paid", "Paid annual test retest", "53", 3, "prs", "rpv3"},
////                {"Paid", "Paid annual test retest", "53", 4, "prs", "rpv4"},
////                {"Paid", "Paid annual test retest", "53", 5, "prs", "rpv5"},
////                {"Paid", "Paid annual test retest", "53", 6, "prs", "rpv5"},
////                {"Paid", "Paid annual test retest", "53", 7, "prs", "rpv5"},
////                {"Paid", "Paid annual test retest", "53", 8, "prs", "rpv5"},
////                {"Paid", "Paid annual test retest", "53", 9, "prs", "rpv5"},
////                {"Paid", "Paid annual test retest", "53", 10, "prs", "rpv5"},
////                {"Part paid", "Part paid annual test retest", "54", 2, "prs", "rsv2"},
////                {"Part paid", "Part paid annual test retest", "54", 3, "prs", "rsv3"},
////                {"Part paid", "Part paid annual test retest", "54", 4, "prs", "rsv4"},
////                {"Part paid", "Part paid annual test retest", "54", 5, "prs", "rsv5"},
////                {"Part paid", "Part paid annual test retest", "54", 6, "prs", "rsv5"},
////                {"Part paid", "Part paid annual test retest", "54", 7, "prs", "rsv5"},
////                {"Part paid", "Part paid annual test retest", "54", 8, "prs", "rsv5"},
////                {"Part paid", "Part paid annual test retest", "54", 9, "prs", "rsv5"},
////                {"Part paid", "Part paid annual test retest", "54", 10, "prs", "rsv5"},
////                {"With certification", "Paid prohibition clearance (full inspection with certification)", "70", 2, "prs", "p1v2"},
////                {"With certification", "Paid prohibition clearance (full inspection with certification)", "70", 3, "prs", "p1v3"},
////                {"With certification", "Paid prohibition clearance (full inspection with certification)", "70", 4, "prs", "p1v4"},
////                {"With certification", "Paid prohibition clearance (full inspection with certification)", "70", 5, "prs", "p1v5"},
////                {"With certification", "Paid prohibition clearance (full inspection with certification)", "70", 6, "prs", "p1v5"},
////                {"With certification", "Paid prohibition clearance (full inspection with certification)", "70", 7, "prs", "p1v5"},
////                {"With certification", "Paid prohibition clearance (full inspection with certification)", "70", 8, "prs", "p1v5"},
////                {"With certification", "Paid prohibition clearance (full inspection with certification)", "70", 9, "prs", "p1v5"},
////                {"With certification", "Paid prohibition clearance (full inspection with certification)", "70", 10, "prs", "p1v5"},
////                {"With certification", "Paid prohibition clearance (retest with certification)", "76", 2, "prs", "p3v2"},
////                {"With certification", "Paid prohibition clearance (retest with certification)", "76", 3, "prs", "p3v3"},
////                {"With certification", "Paid prohibition clearance (retest with certification)", "76", 4, "prs", "p3v4"},
////                {"With certification", "Paid prohibition clearance (retest with certification)", "76", 5, "prs", "p3v5"},
////                {"With certification", "Paid prohibition clearance (retest with certification)", "76", 6, "prs", "p3v5"},
////                {"With certification", "Paid prohibition clearance (retest with certification)", "76", 7, "prs", "p3v5"},
////                {"With certification", "Paid prohibition clearance (retest with certification)", "76", 8, "prs", "p3v5"},
////                {"With certification", "Paid prohibition clearance (retest with certification)", "76", 9, "prs", "p3v5"},
////                {"With certification", "Paid prohibition clearance (retest with certification)", "76", 10, "prs", "p3v5"},
////                {"With certification", "Part paid prohibition clearance (retest with certification)", "79", 2, "prs", "p6v2"},
////                {"With certification", "Part paid prohibition clearance (retest with certification)", "79", 3, "prs", "p6v3"},
////                {"With certification", "Part paid prohibition clearance (retest with certification)", "79", 4, "prs", "p6v4"},
////                {"With certification", "Part paid prohibition clearance (retest with certification)", "79", 5, "prs", "p6v5"},
////                {"With certification", "Part paid prohibition clearance (retest with certification)", "79", 6, "prs", "p6v5"},
////                {"With certification", "Part paid prohibition clearance (retest with certification)", "79", 7, "prs", "p6v5"},
////                {"With certification", "Part paid prohibition clearance (retest with certification)", "79", 8, "prs", "p6v5"},
////                {"With certification", "Part paid prohibition clearance (retest with certification)", "79", 9, "prs", "p6v5"},
////                {"With certification", "Part paid prohibition clearance (retest with certification)", "79", 10, "prs", "p6v5"},
//        });
//    }

    private String name;
    private String testTypeName;
    private String testTypeId;
    private int noOfAxles;
    private String testResult;
    private String testCode;

    public void setName(String name) {
        this.name = name;
    }

    public void setTestTypeName(String testTypeName) {
        this.testTypeName = testTypeName;
    }

    public void setTestTypeId(String testTypeId) {
        this.testTypeId = testTypeId;
    }

    public void setNoOfAxles(int noOfAxles) {
        this.noOfAxles = noOfAxles;
    }

    public void setTestResult(String testResult) {
        this.testResult = testResult;
    }

    public void setTestCode(String testCode) {
        this.testCode = testCode;
    }

    //    public PostTestResultsExpiryDateLogicHgvFirstTest(String name, String testTypeName, String testTypeId, int noOfAxles, String testResult, String testCode) {
//        this.name = name;
//        this.testTypeName = testTypeName;
//        this.testTypeId = testTypeId;
//        this.noOfAxles = noOfAxles;
//        this.testResult = testResult;
//        this.testCode = testCode;
//    }


    @WithTag("expiry_dates")
    @Title("CVSB-8684 - TC1 - AC1 - HGV First Test - NO Previous Expiry Date - regnDate = null")
    @Test
    public void testResultsFirstTestExpiryHgvNoExpiryDateRegnDateNull() {

        // Read the base test result JSON.
        String testResultRecord = GenericData.readJsonValueFromFile("test-results_post_expiry_date_hgv_8684.json", "$");

        DateTime currentTime = DateTime.now().withZone(DateTimeZone.UTC);
        DateTime submittedTestStartTimestamp = currentTime.minusMinutes(15);
        DateTime submittedTestTypeStartTimestamp = currentTime.minusMinutes(10);
        DateTime submittedTypeEndTimestamp = currentTime.minusMinutes(5);
        DateTime submittedEndTimestamp = currentTime;

        // Create alteration to add one more tech record to in the request body
        String regnDate = null;
        String testStartTimestamp = submittedTestStartTimestamp.toInstant().toString();
        String testTypeStartTimestamp = submittedTestTypeStartTimestamp.toInstant().toString();
        String testTypeEndTimestamp = submittedTypeEndTimestamp.toInstant().toString();
        String testEndTimestamp = submittedEndTimestamp.toInstant().toString();
        String randomVin = GenericData.generateRandomVin();
        String randomTestResultId = UUID.randomUUID().toString();

        String testExpectedDate = submittedEndTimestamp.plusYears(1).dayOfMonth().withMaximumValue().withTimeAtStartOfDay().toInstant().toString();

//        String returned_testTypeEndTimestamp = "";
//        String returned_testExpiryDate = "";
//        String returned_createdAt = "";
//        String returned_testTypeStartTimestamp = "";

//        System.out.println("submittedFirstUseDate: " + firstUseDate);
//        System.out.println("submittedTestStartTimestamp: " + testStartTimestamp);
//        System.out.println("submittedTestTypeStartTimestamp: " + testTypeStartTimestamp);
//        System.out.println("submittedTestTypeEndTimestamp: " + testTypeEndTimestamp);
//        System.out.println("submittedTestEndTimestamp: " + testEndTimestamp);
//        System.out.println("returned_testAnniversaryDate: " + testExpectedDate);

        JsonPathAlteration alterationRegnDate = new JsonPathAlteration("$.regnDate", regnDate, "", "REPLACE");
        JsonPathAlteration alterationTestStartTimestamp = new JsonPathAlteration("$.testStartTimestamp", testStartTimestamp, "", "REPLACE");
        JsonPathAlteration alterationTestEndTimestamp = new JsonPathAlteration("$.testEndTimestamp", testEndTimestamp, "", "REPLACE");
        JsonPathAlteration alterationTestTypeStartTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeStartTimestamp", testTypeStartTimestamp, "", "REPLACE");
        JsonPathAlteration alterationTestTypeEndTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeEndTimestamp", testTypeEndTimestamp, "", "REPLACE");
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationTestResultId = new JsonPathAlteration("$.testResultId", randomTestResultId, "", "REPLACE");
        JsonPathAlteration alterationTestName = new JsonPathAlteration("$.testTypes[0].name", name,"","REPLACE");
        JsonPathAlteration alterationTestTypeId = new JsonPathAlteration("$.testTypes[0].testTypeId", testTypeId,"","REPLACE");
        JsonPathAlteration alterationTestTypeName = new JsonPathAlteration("$.testTypes[0].testTypeName", testTypeName,"","REPLACE");
        JsonPathAlteration alterationTestResult = new JsonPathAlteration("$.testTypes[0].testResult", testResult,"","REPLACE");
        JsonPathAlteration alterationNoOfAxles = new JsonPathAlteration("$.noOfAxles", noOfAxles,"","REPLACE");
        JsonPathAlteration alterationTestExpiryDate = new JsonPathAlteration("$.testTypes[0].testExpiryDate", "", "", "DELETE");
        
        // Collate the list of alterations.
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(
                alterationRegnDate,
                alterationTestStartTimestamp,
                alterationTestEndTimestamp,
                alterationTestTypeStartTimestamp,
                alterationTestTypeEndTimestamp,
                alterationVin,
                alterationTestResultId,
                alterationTestName,
                alterationTestTypeId,
                alterationTestTypeName,
                alterationNoOfAxles,
                alterationTestExpiryDate,
                alterationTestResult
        ));

        // Post the results, together with any alterations, and verify that they are accepted.
        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, alterations);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");

        // Retrieve the created record, and verify that the fields are present.
        testResultsSteps.getTestResults(randomVin);
        testResultsSteps.statusCodeShouldBe(200);

        // Verify testCode field has the expected value
        testResultsSteps.valueForFieldInPathShouldBe("[0].testTypes[0].testCode", testCode);

        // Verify firstUseDate is
        testResultsSteps.valueForFieldInPathShouldBe("[0].regnDate", regnDate);

        // Verify testTypeEndTimestamp is
        testResultsSteps.valueForFieldInPathShouldBe("[0].testTypes[0].testTypeEndTimestamp", testTypeEndTimestamp);

        // Verify testAnniversaryDate field has the expected value
//        testResultsSteps.valueForFieldInPathShouldBe("[0].testTypes[0].testAnniversaryDate", testExpectedDate);

        // Verify testExpiryDate field has the expected value
        testResultsSteps.valueForFieldInPathShouldStartWith("[0].testTypes[0].testExpiryDate", testExpectedDate.substring(0,10));

    }

    @WithTag("expiry_dates")
    @Title("CVSB-8684 - TC1 - AC1 - HGV First Test - NO Previous Expiry Date - regnDate is missing")
    @Test
    public void testResultsFirstTestExpiryHgvNoExpiryDateRegnDateMissing() {

        // Read the base test result JSON.
        String testResultRecord = GenericData.readJsonValueFromFile("test-results_post_expiry_date_hgv_8684.json", "$");

        DateTime currentTime = DateTime.now().withZone(DateTimeZone.UTC);
        DateTime submittedTestStartTimestamp = currentTime.minusMinutes(15);
        DateTime submittedTestTypeStartTimestamp = currentTime.minusMinutes(10);
        DateTime submittedTypeEndTimestamp = currentTime.minusMinutes(5);
        DateTime submittedEndTimestamp = currentTime;

        // Create alteration to add one more tech record to in the request body
        String testStartTimestamp = submittedTestStartTimestamp.toInstant().toString();
        String testTypeStartTimestamp = submittedTestTypeStartTimestamp.toInstant().toString();
        String testTypeEndTimestamp = submittedTypeEndTimestamp.toInstant().toString();
        String testEndTimestamp = submittedEndTimestamp.toInstant().toString();
        String randomVin = GenericData.generateRandomVin();
        String randomTestResultId = UUID.randomUUID().toString();

        String testExpectedDate = submittedEndTimestamp.plusYears(1).dayOfMonth().withMaximumValue().withTimeAtStartOfDay().toInstant().toString();

//        String returned_testTypeEndTimestamp = "";
//        String returned_testExpiryDate = "";
//        String returned_createdAt = "";
//        String returned_testTypeStartTimestamp = "";

//        System.out.println("submittedFirstUseDate: " + firstUseDate);
//        System.out.println("submittedTestStartTimestamp: " + testStartTimestamp);
//        System.out.println("submittedTestTypeStartTimestamp: " + testTypeStartTimestamp);
//        System.out.println("submittedTestTypeEndTimestamp: " + testTypeEndTimestamp);
//        System.out.println("submittedTestEndTimestamp: " + testEndTimestamp);
//        System.out.println("returned_testAnniversaryDate: " + testExpectedDate);

        JsonPathAlteration alterationRegnDate = new JsonPathAlteration("$.regnDate", "", "", "DELETE");
        JsonPathAlteration alterationTestStartTimestamp = new JsonPathAlteration("$.testStartTimestamp", testStartTimestamp, "", "REPLACE");
        JsonPathAlteration alterationTestEndTimestamp = new JsonPathAlteration("$.testEndTimestamp", testEndTimestamp, "", "REPLACE");
        JsonPathAlteration alterationTestTypeStartTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeStartTimestamp", testTypeStartTimestamp, "", "REPLACE");
        JsonPathAlteration alterationTestTypeEndTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeEndTimestamp", testTypeEndTimestamp, "", "REPLACE");
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationTestResultId = new JsonPathAlteration("$.testResultId", randomTestResultId, "", "REPLACE");
        JsonPathAlteration alterationTestName = new JsonPathAlteration("$.testTypes[0].name", name,"","REPLACE");
        JsonPathAlteration alterationTestTypeId = new JsonPathAlteration("$.testTypes[0].testTypeId", testTypeId,"","REPLACE");
        JsonPathAlteration alterationTestTypeName = new JsonPathAlteration("$.testTypes[0].testTypeName", testTypeName,"","REPLACE");
        JsonPathAlteration alterationTestResult = new JsonPathAlteration("$.testTypes[0].testResult", testResult,"","REPLACE");
        JsonPathAlteration alterationNoOfAxles = new JsonPathAlteration("$.noOfAxles", noOfAxles,"","REPLACE");
        JsonPathAlteration alterationTestExpiryDate = new JsonPathAlteration("$.testTypes[0].testExpiryDate", "", "", "DELETE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(
                alterationRegnDate,
                alterationTestStartTimestamp,
                alterationTestEndTimestamp,
                alterationTestTypeStartTimestamp,
                alterationTestTypeEndTimestamp,
                alterationVin,
                alterationTestResultId,
                alterationTestName,
                alterationTestTypeId,
                alterationTestTypeName,
                alterationNoOfAxles,
                alterationTestExpiryDate,
                alterationTestResult
        ));

        // Post the results, together with any alterations, and verify that they are accepted.
        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, alterations);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");

        // Retrieve the created record, and verify that the fields are present.
        testResultsSteps.getTestResults(randomVin);
        testResultsSteps.statusCodeShouldBe(200);

        // Verify testCode field has the expected value
        testResultsSteps.valueForFieldInPathShouldBe("[0].testTypes[0].testCode", testCode);

        // Verify testTypeEndTimestamp is
        testResultsSteps.valueForFieldInPathShouldBe("[0].testTypes[0].testTypeEndTimestamp", testTypeEndTimestamp);

//        // Verify testAnniversaryDate field has the expected value
//        testResultsSteps.valueForFieldInPathShouldBe("[0].testTypes[0].testAnniversaryDate", testExpectedDate);

        // Verify testExpiryDate field has the expected value
        testResultsSteps.valueForFieldInPathShouldStartWith("[0].testTypes[0].testExpiryDate", testExpectedDate.substring(0,10));

    }

    @WithTag("expiry_dates")
    @Title("CVSB-8684 - TC1 - AC1 - HGV First Test - NO Previous Expiry Date - regnDate = Today(-1 year, +1 month)")
    @Test
    public void testResultsFirstTestExpiryHgvNoExpiryDateRegnDateLessThanOneYear() {

        // Read the base test result JSON.
        String testResultRecord = GenericData.readJsonValueFromFile("test-results_post_expiry_date_hgv_8684.json", "$");

        DateTime currentTime = DateTime.now().withZone(DateTimeZone.UTC);
        DateTime submittedTestStartTimestamp = currentTime.minusMinutes(15);
        DateTime submittedTestTypeStartTimestamp = currentTime.minusMinutes(10);
        DateTime submittedTypeEndTimestamp = currentTime.minusMinutes(5);
        DateTime submittedRegnDate = currentTime.minusYears(1).plusMonths(1);
        DateTime submittedEndTimestamp = currentTime;

        // Create alteration to add one more tech record to in the request body
        String testStartTimestamp = submittedTestStartTimestamp.toInstant().toString();
        String testTypeStartTimestamp = submittedTestTypeStartTimestamp.toInstant().toString();
        String testTypeEndTimestamp = submittedTypeEndTimestamp.toInstant().toString();
        String testEndTimestamp = submittedEndTimestamp.toInstant().toString();
        String randomVin = GenericData.generateRandomVin();
        String randomTestResultId = UUID.randomUUID().toString();
        String regnDate = submittedRegnDate.toInstant().toString().substring(0,10);

        String testExpectedDate = submittedRegnDate.dayOfMonth().withMaximumValue().plusYears(2).toInstant().toString();

//        String returned_testTypeEndTimestamp = "";
//        String returned_testExpiryDate = "";
//        String returned_createdAt = "";
//        String returned_testTypeStartTimestamp = "";

//        System.out.println("submittedFirstUseDate: " + firstUseDate);
//        System.out.println("submittedTestStartTimestamp: " + testStartTimestamp);
//        System.out.println("submittedTestTypeStartTimestamp: " + testTypeStartTimestamp);
//        System.out.println("submittedTestTypeEndTimestamp: " + testTypeEndTimestamp);
//        System.out.println("submittedTestEndTimestamp: " + testEndTimestamp);
//        System.out.println("returned_testAnniversaryDate: " + testExpectedDate);

        JsonPathAlteration alterationRegnDate = new JsonPathAlteration("$.regnDate", regnDate, "", "REPLACE");
        JsonPathAlteration alterationTestStartTimestamp = new JsonPathAlteration("$.testStartTimestamp", testStartTimestamp, "", "REPLACE");
        JsonPathAlteration alterationTestEndTimestamp = new JsonPathAlteration("$.testEndTimestamp", testEndTimestamp, "", "REPLACE");
        JsonPathAlteration alterationTestTypeStartTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeStartTimestamp", testTypeStartTimestamp, "", "REPLACE");
        JsonPathAlteration alterationTestTypeEndTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeEndTimestamp", testTypeEndTimestamp, "", "REPLACE");
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationTestResultId = new JsonPathAlteration("$.testResultId", randomTestResultId, "", "REPLACE");
        JsonPathAlteration alterationTestName = new JsonPathAlteration("$.testTypes[0].name", name,"","REPLACE");
        JsonPathAlteration alterationTestTypeId = new JsonPathAlteration("$.testTypes[0].testTypeId", testTypeId,"","REPLACE");
        JsonPathAlteration alterationTestTypeName = new JsonPathAlteration("$.testTypes[0].testTypeName", testTypeName,"","REPLACE");
        JsonPathAlteration alterationTestResult = new JsonPathAlteration("$.testTypes[0].testResult", testResult,"","REPLACE");
        JsonPathAlteration alterationNoOfAxles = new JsonPathAlteration("$.noOfAxles", noOfAxles,"","REPLACE");
        JsonPathAlteration alterationTestExpiryDate = new JsonPathAlteration("$.testTypes[0].testExpiryDate", "", "", "DELETE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(
                alterationRegnDate,
                alterationTestStartTimestamp,
                alterationTestEndTimestamp,
                alterationTestTypeStartTimestamp,
                alterationTestTypeEndTimestamp,
                alterationVin,
                alterationTestResultId,
                alterationTestName,
                alterationTestTypeId,
                alterationTestTypeName,
                alterationNoOfAxles,
                alterationTestExpiryDate,
                alterationTestResult
        ));

        // Post the results, together with any alterations, and verify that they are accepted.
        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, alterations);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");

        // Retrieve the created record, and verify that the fields are present.
        testResultsSteps.getTestResults(randomVin);
        testResultsSteps.statusCodeShouldBe(200);

        // Verify testCode field has the expected value
        testResultsSteps.valueForFieldInPathShouldBe("[0].testTypes[0].testCode", testCode);

        // Verify testTypeEndTimestamp is
        testResultsSteps.valueForFieldInPathShouldBe("[0].testTypes[0].testTypeEndTimestamp", testTypeEndTimestamp);

        // Verify firstUseDate is
        testResultsSteps.valueForFieldInPathShouldBe("[0].regnDate", regnDate);

        // Verify testExpiryDate field has the expected value
        testResultsSteps.valueForFieldInPathShouldStartWith("[0].testTypes[0].testExpiryDate", testExpectedDate.substring(0,10));

    }

    @WithTag("expiry_dates")
    @Title("CVSB-8684 - TC1 - AC1 - HGV First Test - NO Previous Expiry Date - regnDate = Today(-1 year, -1 month)")
    @Test
    public void testResultsFirstTestExpiryHgvNoExpiryDateRegnDateMoreThanOneYear() {

        // Read the base test result JSON.
        String testResultRecord = GenericData.readJsonValueFromFile("test-results_post_expiry_date_hgv_8684.json", "$");

        DateTime currentTime = DateTime.now().withZone(DateTimeZone.UTC);
        DateTime submittedTestStartTimestamp = currentTime.minusMinutes(15);
        DateTime submittedTestTypeStartTimestamp = currentTime.minusMinutes(10);
        DateTime submittedTypeEndTimestamp = currentTime.minusMinutes(5);
        DateTime submittedRegnDate = currentTime.minusYears(1).minusMonths(1);
        DateTime submittedEndTimestamp = currentTime;

        // Create alteration to add one more tech record to in the request body
        String testStartTimestamp = submittedTestStartTimestamp.toInstant().toString();
        String testTypeStartTimestamp = submittedTestTypeStartTimestamp.toInstant().toString();
        String testTypeEndTimestamp = submittedTypeEndTimestamp.toInstant().toString();
        String testEndTimestamp = submittedEndTimestamp.toInstant().toString();
        String randomVin = GenericData.generateRandomVin();
        String randomTestResultId = UUID.randomUUID().toString();
        String regnDate = submittedRegnDate.toInstant().toString().substring(0,10);

        String testExpectedDate = submittedEndTimestamp.dayOfMonth().withMaximumValue().plusYears(1).toInstant().toString();

//        String returned_testTypeEndTimestamp = "";
//        String returned_testExpiryDate = "";
//        String returned_createdAt = "";
//        String returned_testTypeStartTimestamp = "";

//        System.out.println("submittedFirstUseDate: " + firstUseDate);
//        System.out.println("submittedTestStartTimestamp: " + testStartTimestamp);
//        System.out.println("submittedTestTypeStartTimestamp: " + testTypeStartTimestamp);
//        System.out.println("submittedTestTypeEndTimestamp: " + testTypeEndTimestamp);
//        System.out.println("submittedTestEndTimestamp: " + testEndTimestamp);
//        System.out.println("returned_testAnniversaryDate: " + testExpectedDate);

        JsonPathAlteration alterationRegnDate = new JsonPathAlteration("$.regnDate", regnDate, "", "REPLACE");
        JsonPathAlteration alterationTestStartTimestamp = new JsonPathAlteration("$.testStartTimestamp", testStartTimestamp, "", "REPLACE");
        JsonPathAlteration alterationTestEndTimestamp = new JsonPathAlteration("$.testEndTimestamp", testEndTimestamp, "", "REPLACE");
        JsonPathAlteration alterationTestTypeStartTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeStartTimestamp", testTypeStartTimestamp, "", "REPLACE");
        JsonPathAlteration alterationTestTypeEndTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeEndTimestamp", testTypeEndTimestamp, "", "REPLACE");
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationTestResultId = new JsonPathAlteration("$.testResultId", randomTestResultId, "", "REPLACE");
        JsonPathAlteration alterationTestName = new JsonPathAlteration("$.testTypes[0].name", name,"","REPLACE");
        JsonPathAlteration alterationTestTypeId = new JsonPathAlteration("$.testTypes[0].testTypeId", testTypeId,"","REPLACE");
        JsonPathAlteration alterationTestTypeName = new JsonPathAlteration("$.testTypes[0].testTypeName", testTypeName,"","REPLACE");
        JsonPathAlteration alterationTestResult = new JsonPathAlteration("$.testTypes[0].testResult", testResult,"","REPLACE");
        JsonPathAlteration alterationNoOfAxles = new JsonPathAlteration("$.noOfAxles", noOfAxles,"","REPLACE");
        JsonPathAlteration alterationTestExpiryDate = new JsonPathAlteration("$.testTypes[0].testExpiryDate", "", "", "DELETE");

        // Collate the list of alterations.
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(
                alterationRegnDate,
                alterationTestStartTimestamp,
                alterationTestEndTimestamp,
                alterationTestTypeStartTimestamp,
                alterationTestTypeEndTimestamp,
                alterationVin,
                alterationTestResultId,
                alterationTestName,
                alterationTestTypeId,
                alterationTestTypeName,
                alterationNoOfAxles,
                alterationTestExpiryDate,
                alterationTestResult
        ));

        // Post the results, together with any alterations, and verify that they are accepted.
        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, alterations);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");

        // Retrieve the created record, and verify that the fields are present.
        testResultsSteps.getTestResults(randomVin);
        testResultsSteps.statusCodeShouldBe(200);

        // Verify testCode field has the expected value
        testResultsSteps.valueForFieldInPathShouldBe("[0].testTypes[0].testCode", testCode);

        // Verify testTypeEndTimestamp is
        testResultsSteps.valueForFieldInPathShouldBe("[0].testTypes[0].testTypeEndTimestamp", testTypeEndTimestamp);

        // Verify firstUseDate is
        testResultsSteps.valueForFieldInPathShouldBe("[0].regnDate", regnDate);

        // Verify testExpiryDate field has the expected value
        testResultsSteps.valueForFieldInPathShouldStartWith("[0].testTypes[0].testExpiryDate", testExpectedDate.substring(0,10));

    }

}
