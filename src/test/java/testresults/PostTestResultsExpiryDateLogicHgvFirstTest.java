package testresults;

import data.GenericData;
import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.junit.annotations.TestData;
import org.apache.commons.lang3.RandomStringUtils;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import steps.TestResultsSteps;
import util.JsonPathAlteration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Ignore
@RunWith(SerenityParameterizedRunner.class)
public class PostTestResultsExpiryDateLogicHgvFirstTest {

    @Steps
    TestResultsSteps testResultsSteps;

    @TestData
    public static Collection<Object[]> testData(){
        return Arrays.asList(new Object[][]{
                {"First test", "First test", "95", 2, "pass", -1, "ffv2"},
//                {"First test", "First test", "95", 3, "pass", -1, "ffv3"},
//                {"First test", "First test", "95", 4, "pass", -1, "ffv4"},
//                {"First test", "First test", "95", 5, "pass", -1, "ffv5"},
//                {"First test", "First test", "95", 6, "pass", -1, "ffv5"},
//                {"First test", "First test", "95", 7, "pass", -1, "ffv5"},
//                {"First test", "First test", "95", 8, "pass", -1, "ffv5"},
//                {"First test", "First test", "95", 9, "pass", -1, "ffv5"},
//                {"First test", "First test", "95", 10, "pass", -1, "ffv5"},
//                {"Paid", "Paid first test retest", "65", 2, "pass", -1, "rgv2"},
//                {"Paid", "Paid first test retest", "65", 3, "pass", -1, "rgv3"},
//                {"Paid", "Paid first test retest", "65", 4, "pass", -1, "rgv4"},
//                {"Paid", "Paid first test retest", "65", 5, "pass", -1, "rgv5"},
//                {"Paid", "Paid first test retest", "65", 6, "pass", -1, "rgv5"},
//                {"Paid", "Paid first test retest", "65", 7, "pass", -1, "rgv5"},
//                {"Paid", "Paid first test retest", "65", 8, "pass", -1, "rgv5"},
//                {"Paid", "Paid first test retest", "65", 9, "pass", -1, "rgv5"},
//                {"Paid", "Paid first test retest", "65", 10, "pass", -1, "rgv5"},
//                {"Part paid", "Part paid first test retest", "66", 2, "pass", -1, "riv2"},
//                {"Part paid", "Part paid first test retest", "66", 3, "pass", -1, "riv3"},
//                {"Part paid", "Part paid first test retest", "66", 4, "pass", -1, "riv4"},
//                {"Part paid", "Part paid first test retest", "66", 5, "pass", -1, "riv5"},
//                {"Part paid", "Part paid first test retest", "66", 6, "pass", -1, "riv5"},
//                {"Part paid", "Part paid first test retest", "66", 7, "pass", -1, "riv5"},
//                {"Part paid", "Part paid first test retest", "66", 8, "pass", -1, "riv5"},
//                {"Part paid", "Part paid first test retest", "66", 9, "pass", -1, "riv5"},
//                {"Part paid", "Part paid first test retest", "66", 10, "pass", -1, "riv5"},
//                {"Full Inspection/ Fee - With certification", "Paid prohibition clearance on first test (full inspection with certification)", "82", 2, "pass", -1, "p7v2"},
//                {"Full Inspection/ Fee - With certification", "Paid prohibition clearance on first test (full inspection with certification)", "82", 3, "pass", -1, "p7v3"},
//                {"Full Inspection/ Fee - With certification", "Paid prohibition clearance on first test (full inspection with certification)", "82", 4, "pass", -1, "p7v4"},
//                {"Full Inspection/ Fee - With certification", "Paid prohibition clearance on first test (full inspection with certification)", "82", 5, "pass", -1, "p7v5"},
//                {"Full Inspection/ Fee - With certification", "Paid prohibition clearance on first test (full inspection with certification)", "82", 6, "pass", -1, "p7v5"},
//                {"Full Inspection/ Fee - With certification", "Paid prohibition clearance on first test (full inspection with certification)", "82", 7, "pass", -1, "p7v5"},
//                {"Full Inspection/ Fee - With certification", "Paid prohibition clearance on first test (full inspection with certification)", "82", 8, "pass", -1, "p7v5"},
//                {"Full Inspection/ Fee - With certification", "Paid prohibition clearance on first test (full inspection with certification)", "82", 9, "pass", -1, "p7v5"},
//                {"Full Inspection/ Fee - With certification", "Paid prohibition clearance on first test (full inspection with certification)", "82", 10, "pass", -1, "p7v5"},
//                {"Retest - Paid", "Paid retest prohibition clearance on first test", "83", 2, "pass", -1, "p4v2"},
//                {"Retest - Paid", "Paid retest prohibition clearance on first test", "83", 3, "pass", -1, "p4v3"},
//                {"Retest - Paid", "Paid retest prohibition clearance on first test", "83", 4, "pass", -1, "p4v4"},
//                {"Retest - Paid", "Paid retest prohibition clearance on first test", "83", 5, "pass", -1, "p4v5"},
//                {"Retest - Paid", "Paid retest prohibition clearance on first test", "83", 6, "pass", -1, "p4v5"},
//                {"Retest - Paid", "Paid retest prohibition clearance on first test", "83", 7, "pass", -1, "p4v5"},
//                {"Retest - Paid", "Paid retest prohibition clearance on first test", "83", 8, "pass", -1, "p4v5"},
//                {"Retest - Paid", "Paid retest prohibition clearance on first test", "83", 9, "pass", -1, "p4v5"},
//                {"Retest - Paid", "Paid retest prohibition clearance on first test", "83", 10, "pass", -1, "p4v5"},
//                {"Annual test", "Annual test", "94", 2, "pass", -1, "aav2"},
//                {"Annual test", "Annual test", "94", 3, "pass", -1, "aav3"},
//                {"Annual test", "Annual test", "94", 4, "pass", -1, "aav4"},
//                {"Annual test", "Annual test", "94", 5, "pass", -1, "aav5"},
//                {"Annual test", "Annual test", "94", 6, "pass", -1, "aav5"},
//                {"Annual test", "Annual test", "94", 7, "pass", -1, "aav5"},
//                {"Annual test", "Annual test", "94", 8, "pass", -1, "aav5"},
//                {"Annual test", "Annual test", "94", 9, "pass", -1, "aav5"},
//                {"Annual test", "Annual test", "94", 10, "pass", -1, "aav5"},
//                {"Paid", "Paid annual test retest", "53", 2, "pass", -1, "rpv2"},
//                {"Paid", "Paid annual test retest", "53", 3, "pass", -1, "rpv3"},
//                {"Paid", "Paid annual test retest", "53", 4, "pass", -1, "rpv4"},
//                {"Paid", "Paid annual test retest", "53", 5, "pass", -1, "rpv5"},
//                {"Paid", "Paid annual test retest", "53", 6, "pass", -1, "rpv5"},
//                {"Paid", "Paid annual test retest", "53", 7, "pass", -1, "rpv5"},
//                {"Paid", "Paid annual test retest", "53", 8, "pass", -1, "rpv5"},
//                {"Paid", "Paid annual test retest", "53", 9, "pass", -1, "rpv5"},
//                {"Paid", "Paid annual test retest", "53", 10, "pass", -1, "rpv5"},
//                {"Part paid", "Part paid annual test retest", "54", 2, "pass", -1, "rsv2"},
//                {"Part paid", "Part paid annual test retest", "54", 3, "pass", -1, "rsv3"},
//                {"Part paid", "Part paid annual test retest", "54", 4, "pass", -1, "rsv4"},
//                {"Part paid", "Part paid annual test retest", "54", 5, "pass", -1, "rsv5"},
//                {"Part paid", "Part paid annual test retest", "54", 6, "pass", -1, "rsv5"},
//                {"Part paid", "Part paid annual test retest", "54", 7, "pass", -1, "rsv5"},
//                {"Part paid", "Part paid annual test retest", "54", 8, "pass", -1, "rsv5"},
//                {"Part paid", "Part paid annual test retest", "54", 9, "pass", -1, "rsv5"},
//                {"Part paid", "Part paid annual test retest", "54", 10, "pass", -1, "rsv5"},
//                {"With certification", "Paid prohibition clearance (full inspection with certification)", "70", 2, "pass", -1, "p1v2"},
//                {"With certification", "Paid prohibition clearance (full inspection with certification)", "70", 3, "pass", -1, "p1v3"},
//                {"With certification", "Paid prohibition clearance (full inspection with certification)", "70", 4, "pass", -1, "p1v4"},
//                {"With certification", "Paid prohibition clearance (full inspection with certification)", "70", 5, "pass", -1, "p1v5"},
//                {"With certification", "Paid prohibition clearance (full inspection with certification)", "70", 6, "pass", -1, "p1v5"},
//                {"With certification", "Paid prohibition clearance (full inspection with certification)", "70", 7, "pass", -1, "p1v5"},
//                {"With certification", "Paid prohibition clearance (full inspection with certification)", "70", 8, "pass", -1, "p1v5"},
//                {"With certification", "Paid prohibition clearance (full inspection with certification)", "70", 9, "pass", -1, "p1v5"},
//                {"With certification", "Paid prohibition clearance (full inspection with certification)", "70", 10, "pass", -1, "p1v5"},
//                {"With certification", "Paid prohibition clearance (retest with certification)", "76", 2, "pass", -1, "p3v2"},
//                {"With certification", "Paid prohibition clearance (retest with certification)", "76", 3, "pass", -1, "p3v3"},
//                {"With certification", "Paid prohibition clearance (retest with certification)", "76", 4, "pass", -1, "p3v4"},
//                {"With certification", "Paid prohibition clearance (retest with certification)", "76", 5, "pass", -1, "p3v5"},
//                {"With certification", "Paid prohibition clearance (retest with certification)", "76", 6, "pass", -1, "p3v5"},
//                {"With certification", "Paid prohibition clearance (retest with certification)", "76", 7, "pass", -1, "p3v5"},
//                {"With certification", "Paid prohibition clearance (retest with certification)", "76", 8, "pass", -1, "p3v5"},
//                {"With certification", "Paid prohibition clearance (retest with certification)", "76", 9, "pass", -1, "p3v5"},
//                {"With certification", "Paid prohibition clearance (retest with certification)", "76", 10, "pass", -1, "p3v5"},
//                {"With certification", "Part paid prohibition clearance (retest with certification)", "79", 2, "pass", -1, "p6v2"},
//                {"With certification", "Part paid prohibition clearance (retest with certification)", "79", 3, "pass", -1, "p6v3"},
//                {"With certification", "Part paid prohibition clearance (retest with certification)", "79", 4, "pass", -1, "p6v4"},
//                {"With certification", "Part paid prohibition clearance (retest with certification)", "79", 5, "pass", -1, "p6v5"},
//                {"With certification", "Part paid prohibition clearance (retest with certification)", "79", 6, "pass", -1, "p6v5"},
//                {"With certification", "Part paid prohibition clearance (retest with certification)", "79", 7, "pass", -1, "p6v5"},
//                {"With certification", "Part paid prohibition clearance (retest with certification)", "79", 8, "pass", -1, "p6v5"},
//                {"With certification", "Part paid prohibition clearance (retest with certification)", "79", 9, "pass", -1, "p6v5"},
//                {"With certification", "Part paid prohibition clearance (retest with certification)", "79", 10, "pass", -1, "p6v5"},
//
//                {"First test", "First test", "95", 2, "prs", -1, "ffv2"},
//                {"First test", "First test", "95", 3, "prs", -1, "ffv3"},
//                {"First test", "First test", "95", 4, "prs", -1, "ffv4"},
//                {"First test", "First test", "95", 5, "prs", -1, "ffv5"},
//                {"First test", "First test", "95", 6, "prs", -1, "ffv5"},
//                {"First test", "First test", "95", 7, "prs", -1, "ffv5"},
//                {"First test", "First test", "95", 8, "prs", -1, "ffv5"},
//                {"First test", "First test", "95", 9, "prs", -1, "ffv5"},
//                {"First test", "First test", "95", 10, "prs", -1, "ffv5"},
//                {"Paid", "Paid first test retest", "65", 2, "prs", -1, "rgv2"},
//                {"Paid", "Paid first test retest", "65", 3, "prs", -1, "rgv3"},
//                {"Paid", "Paid first test retest", "65", 4, "prs", -1, "rgv4"},
//                {"Paid", "Paid first test retest", "65", 5, "prs", -1, "rgv5"},
//                {"Paid", "Paid first test retest", "65", 6, "prs", -1, "rgv5"},
//                {"Paid", "Paid first test retest", "65", 7, "prs", -1, "rgv5"},
//                {"Paid", "Paid first test retest", "65", 8, "prs", -1, "rgv5"},
//                {"Paid", "Paid first test retest", "65", 9, "prs", -1, "rgv5"},
//                {"Paid", "Paid first test retest", "65", 10, "prs", -1, "rgv5"},
//                {"Part paid", "Part paid first test retest", "66", 2, "prs", -1, "riv2"},
//                {"Part paid", "Part paid first test retest", "66", 3, "prs", -1, "riv3"},
//                {"Part paid", "Part paid first test retest", "66", 4, "prs", -1, "riv4"},
//                {"Part paid", "Part paid first test retest", "66", 5, "prs", -1, "riv5"},
//                {"Part paid", "Part paid first test retest", "66", 6, "prs", -1, "riv5"},
//                {"Part paid", "Part paid first test retest", "66", 7, "prs", -1, "riv5"},
//                {"Part paid", "Part paid first test retest", "66", 8, "prs", -1, "riv5"},
//                {"Part paid", "Part paid first test retest", "66", 9, "prs", -1, "riv5"},
//                {"Part paid", "Part paid first test retest", "66", 10, "prs", -1, "riv5"},
//                {"Full Inspection/ Fee - With certification", "Paid prohibition clearance on first test (full inspection with certification)", "82", 2, "prs", -1, "p7v2"},
//                {"Full Inspection/ Fee - With certification", "Paid prohibition clearance on first test (full inspection with certification)", "82", 3, "prs", -1, "p7v3"},
//                {"Full Inspection/ Fee - With certification", "Paid prohibition clearance on first test (full inspection with certification)", "82", 4, "prs", -1, "p7v4"},
//                {"Full Inspection/ Fee - With certification", "Paid prohibition clearance on first test (full inspection with certification)", "82", 5, "prs", -1, "p7v5"},
//                {"Full Inspection/ Fee - With certification", "Paid prohibition clearance on first test (full inspection with certification)", "82", 6, "prs", -1, "p7v5"},
//                {"Full Inspection/ Fee - With certification", "Paid prohibition clearance on first test (full inspection with certification)", "82", 7, "prs", -1, "p7v5"},
//                {"Full Inspection/ Fee - With certification", "Paid prohibition clearance on first test (full inspection with certification)", "82", 8, "prs", -1, "p7v5"},
//                {"Full Inspection/ Fee - With certification", "Paid prohibition clearance on first test (full inspection with certification)", "82", 9, "prs", -1, "p7v5"},
//                {"Full Inspection/ Fee - With certification", "Paid prohibition clearance on first test (full inspection with certification)", "82", 10, "prs", -1, "p7v5"},
//                {"Retest - Paid", "Paid retest prohibition clearance on first test", "83", 2, "prs", -1, "p4v2"},
//                {"Retest - Paid", "Paid retest prohibition clearance on first test", "83", 3, "prs", -1, "p4v3"},
//                {"Retest - Paid", "Paid retest prohibition clearance on first test", "83", 4, "prs", -1, "p4v4"},
//                {"Retest - Paid", "Paid retest prohibition clearance on first test", "83", 5, "prs", -1, "p4v5"},
//                {"Retest - Paid", "Paid retest prohibition clearance on first test", "83", 6, "prs", -1, "p4v5"},
//                {"Retest - Paid", "Paid retest prohibition clearance on first test", "83", 7, "prs", -1, "p4v5"},
//                {"Retest - Paid", "Paid retest prohibition clearance on first test", "83", 8, "prs", -1, "p4v5"},
//                {"Retest - Paid", "Paid retest prohibition clearance on first test", "83", 9, "prs", -1, "p4v5"},
//                {"Retest - Paid", "Paid retest prohibition clearance on first test", "83", 10, "prs", -1, "p4v5"},
//                {"Annual test", "Annual test", "94", 2, "prs", -1, "aav2"},
//                {"Annual test", "Annual test", "94", 3, "prs", -1, "aav3"},
//                {"Annual test", "Annual test", "94", 4, "prs", -1, "aav4"},
//                {"Annual test", "Annual test", "94", 5, "prs", -1, "aav5"},
//                {"Annual test", "Annual test", "94", 6, "prs", -1, "aav5"},
//                {"Annual test", "Annual test", "94", 7, "prs", -1, "aav5"},
//                {"Annual test", "Annual test", "94", 8, "prs", -1, "aav5"},
//                {"Annual test", "Annual test", "94", 9, "prs", -1, "aav5"},
//                {"Annual test", "Annual test", "94", 10, "prs", -1, "aav5"},
//                {"Paid", "Paid annual test retest", "53", 2, "prs", -1, "rpv2"},
//                {"Paid", "Paid annual test retest", "53", 3, "prs", -1, "rpv3"},
//                {"Paid", "Paid annual test retest", "53", 4, "prs", -1, "rpv4"},
//                {"Paid", "Paid annual test retest", "53", 5, "prs", -1, "rpv5"},
//                {"Paid", "Paid annual test retest", "53", 6, "prs", -1, "rpv5"},
//                {"Paid", "Paid annual test retest", "53", 7, "prs", -1, "rpv5"},
//                {"Paid", "Paid annual test retest", "53", 8, "prs", -1, "rpv5"},
//                {"Paid", "Paid annual test retest", "53", 9, "prs", -1, "rpv5"},
//                {"Paid", "Paid annual test retest", "53", 10, "prs", -1, "rpv5"},
//                {"Part paid", "Part paid annual test retest", "54", 2, "prs", -1, "rsv2"},
//                {"Part paid", "Part paid annual test retest", "54", 3, "prs", -1, "rsv3"},
//                {"Part paid", "Part paid annual test retest", "54", 4, "prs", -1, "rsv4"},
//                {"Part paid", "Part paid annual test retest", "54", 5, "prs", -1, "rsv5"},
//                {"Part paid", "Part paid annual test retest", "54", 6, "prs", -1, "rsv5"},
//                {"Part paid", "Part paid annual test retest", "54", 7, "prs", -1, "rsv5"},
//                {"Part paid", "Part paid annual test retest", "54", 8, "prs", -1, "rsv5"},
//                {"Part paid", "Part paid annual test retest", "54", 9, "prs", -1, "rsv5"},
//                {"Part paid", "Part paid annual test retest", "54", 10, "prs", -1, "rsv5"},
//                {"With certification", "Paid prohibition clearance (full inspection with certification)", "70", 2, "prs", -1, "p1v2"},
//                {"With certification", "Paid prohibition clearance (full inspection with certification)", "70", 3, "prs", -1, "p1v3"},
//                {"With certification", "Paid prohibition clearance (full inspection with certification)", "70", 4, "prs", -1, "p1v4"},
//                {"With certification", "Paid prohibition clearance (full inspection with certification)", "70", 5, "prs", -1, "p1v5"},
//                {"With certification", "Paid prohibition clearance (full inspection with certification)", "70", 6, "prs", -1, "p1v5"},
//                {"With certification", "Paid prohibition clearance (full inspection with certification)", "70", 7, "prs", -1, "p1v5"},
//                {"With certification", "Paid prohibition clearance (full inspection with certification)", "70", 8, "prs", -1, "p1v5"},
//                {"With certification", "Paid prohibition clearance (full inspection with certification)", "70", 9, "prs", -1, "p1v5"},
//                {"With certification", "Paid prohibition clearance (full inspection with certification)", "70", 10, "prs", -1, "p1v5"},
//                {"With certification", "Paid prohibition clearance (retest with certification)", "76", 2, "prs", -1, "p3v2"},
//                {"With certification", "Paid prohibition clearance (retest with certification)", "76", 3, "prs", -1, "p3v3"},
//                {"With certification", "Paid prohibition clearance (retest with certification)", "76", 4, "prs", -1, "p3v4"},
//                {"With certification", "Paid prohibition clearance (retest with certification)", "76", 5, "prs", -1, "p3v5"},
//                {"With certification", "Paid prohibition clearance (retest with certification)", "76", 6, "prs", -1, "p3v5"},
//                {"With certification", "Paid prohibition clearance (retest with certification)", "76", 7, "prs", -1, "p3v5"},
//                {"With certification", "Paid prohibition clearance (retest with certification)", "76", 8, "prs", -1, "p3v5"},
//                {"With certification", "Paid prohibition clearance (retest with certification)", "76", 9, "prs", -1, "p3v5"},
//                {"With certification", "Paid prohibition clearance (retest with certification)", "76", 10, "prs", -1, "p3v5"},
//                {"With certification", "Part paid prohibition clearance (retest with certification)", "79", 2, "prs", -1, "p6v2"},
//                {"With certification", "Part paid prohibition clearance (retest with certification)", "79", 3, "prs", -1, "p6v3"},
//                {"With certification", "Part paid prohibition clearance (retest with certification)", "79", 4, "prs", -1, "p6v4"},
//                {"With certification", "Part paid prohibition clearance (retest with certification)", "79", 5, "prs", -1, "p6v5"},
//                {"With certification", "Part paid prohibition clearance (retest with certification)", "79", 6, "prs", -1, "p6v5"},
//                {"With certification", "Part paid prohibition clearance (retest with certification)", "79", 7, "prs", -1, "p6v5"},
//                {"With certification", "Part paid prohibition clearance (retest with certification)", "79", 8, "prs", -1, "p6v5"},
//                {"With certification", "Part paid prohibition clearance (retest with certification)", "79", 9, "prs", -1, "p6v5"},
//                {"With certification", "Part paid prohibition clearance (retest with certification)", "79", 10, "prs", -1, "p6v5"},
        });
    }

    private String name;
    private String testTypeName;
    private String testTypeId;
    private int noOfAxles;
    private String testResult;
    private String testCode;

    //sets the firstUseDate / regnDate one year before the current date, plus/minus one day
    private int dayOffset;

    public PostTestResultsExpiryDateLogicHgvFirstTest(String name, String testTypeName, String testTypeId, int noOfAxles, String testResult, int dayOffset, String testCode) {
        this.name = name;
        this.testTypeName = testTypeName;
        this.testTypeId = testTypeId;
        this.noOfAxles = noOfAxles;
        this.testResult = testResult;
        this.dayOffset = dayOffset;
        this.testCode = testCode;
    }

//    @WithTag("Expiry_Dates")
    @Title("CVSB-9187 - TC1 - AC1 - FIRST TEST/FIRST TEST RETEST CONDUCTED AFTER THE ANNIVERSARY DATE - TRL")
    @Test
    public void testResultsFirstTestExpiryTrl() {

        // Read the base test result JSON.
        String testResultRecord = GenericData.readJsonValueFromFile("test-results_expiry_date_hgv_8684.json", "$");

        DateTime submittedTestStartTimestamp = DateTime.now().withZone(DateTimeZone.UTC);
        DateTime submittedTestTypeStartTimestamp = submittedTestStartTimestamp.plusMinutes(5);
        DateTime submittedTypeEndTimestamp = submittedTestTypeStartTimestamp.plusMinutes(10);
        DateTime submittedEndTimestamp = submittedTypeEndTimestamp.plusMinutes(5);

        // Create alteration to add one more tech record to in the request body
        String regnDate = submittedEndTimestamp.toDateTime().minusYears(1).plusDays(dayOffset).toString("yyyy-MM-dd");
        String testStartTimestamp = submittedTestStartTimestamp.toInstant().toString();
        String testTypeStartTimestamp = submittedTestTypeStartTimestamp.toInstant().toString();

        String testTypeEndTimestamp = submittedTypeEndTimestamp.toInstant().toString();
        String testEndTimestamp = submittedTypeEndTimestamp.toInstant().toString();
        String testExpectedDate = submittedEndTimestamp.dayOfMonth().withMaximumValue().plusYears(1).toInstant().toString();
        String randomVin = GenericData.generateRandomVin();
        String randomTestResultId = RandomStringUtils.randomNumeric(5);


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

        // Verify testAnniversaryDate field has the expected value
//        testResultsSteps.valueForFieldInPathShouldBe("[0].testTypes[0].testTypeStartTimestamp", expectedTestTypeStartTimestamp);

        // Verify testExpiryDate field has the expected value
//        testResultsSteps.valueForFieldInPathShouldBe("[0].testTypes[0].testExpiryDate", testExpectedDate);
        testResultsSteps.valueForFieldInPathShouldStartWith("[0].testTypes[0].testExpiryDate", testExpectedDate.substring(0,10));

    }



}
