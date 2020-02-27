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

@WithTag("expiry_dates")
@RunWith(SerenityParameterizedRunner.class)
public class PostTestResultsExpiryDateLogicTrlFirstTest {

    @Steps
    TestResultsSteps testResultsSteps;

    @TestData
    public static Collection<Object[]> testData(){
        return Arrays.asList(new Object[][]{
                {"First test", "First test", "41", 1, "pass", "fft1"},
                {"First test", "First test", "95", 2, "pass", "fft2"},
                {"First test", "First test", "95", 3, "pass", "fft3"},
                {"First test", "First test", "95", 4, "pass", "fft4"},
                {"First test", "First test", "95", 5, "pass", "fft5"},
                {"First test", "First test", "95", 6, "pass", "fft5"},
                {"First test", "First test", "95", 7, "pass", "fft5"},
                {"First test", "First test", "95", 8, "pass", "fft5"},
                {"First test", "First test", "95", 9, "pass", "fft5"},
                {"First test", "First test", "95", 10, "pass", "fft5"},
                {"Paid", "Paid first test retest", "103", 1, "pass", "rgt1"},
                {"Paid", "Paid first test retest", "65", 2, "pass", "rgt2"},
                {"Paid", "Paid first test retest", "65", 3, "pass", "rgt3"},
                {"Paid", "Paid first test retest", "65", 4, "pass", "rgt4"},
                {"Paid", "Paid first test retest", "65", 5, "pass", "rgt5"},
                {"Paid", "Paid first test retest", "65", 6, "pass", "rgt5"},
                {"Paid", "Paid first test retest", "65", 7, "pass", "rgt5"},
                {"Paid", "Paid first test retest", "65", 8, "pass", "rgt5"},
                {"Paid", "Paid first test retest", "65", 9, "pass", "rgt5"},
                {"Paid", "Paid first test retest", "65", 10, "pass", "rgt5"},
                {"Part paid", "Part paid first test retest", "104", 1, "pass", "rit1"},
                {"Part paid", "Part paid first test retest", "66", 2, "pass", "rit2"},
                {"Part paid", "Part paid first test retest", "66", 3, "pass", "rit3"},
                {"Part paid", "Part paid first test retest", "66", 4, "pass", "rit4"},
                {"Part paid", "Part paid first test retest", "66", 5, "pass", "rit5"},
                {"Part paid", "Part paid first test retest", "66", 6, "pass", "rit5"},
                {"Part paid", "Part paid first test retest", "66", 7, "pass", "rit5"},
                {"Part paid", "Part paid first test retest", "66", 8, "pass", "rit5"},
                {"Part paid", "Part paid first test retest", "66", 9, "pass", "rit5"},
                {"Part paid", "Part paid first test retest", "66", 10, "pass", "rit5"},
                {"Free", "Free first test retest", "67", 3, "pass", "rht"},
//                {"Full Inspection/ Fee - With certification", "Paid prohibition clearance on first test (full inspection with certification)", "119", 1, "pass", "p7t1"},
//                {"Full Inspection/ Fee - With certification", "Paid prohibition clearance on first test (full inspection with certification)", "82", 2, "pass", "p7t2"},
//                {"Full Inspection/ Fee - With certification", "Paid prohibition clearance on first test (full inspection with certification)", "82", 3, "pass", "p7t3"},
//                {"Full Inspection/ Fee - With certification", "Paid prohibition clearance on first test (full inspection with certification)", "82", 4, "pass", "p7t4"},
//                {"Full Inspection/ Fee - With certification", "Paid prohibition clearance on first test (full inspection with certification)", "82", 5, "pass", "p7t5"},
//                {"Full Inspection/ Fee - With certification", "Paid prohibition clearance on first test (full inspection with certification)", "82", 6, "pass", "p7t5"},
//                {"Full Inspection/ Fee - With certification", "Paid prohibition clearance on first test (full inspection with certification)", "82", 7, "pass", "p7t5"},
//                {"Full Inspection/ Fee - With certification", "Paid prohibition clearance on first test (full inspection with certification)", "82", 8, "pass", "p7t5"},
//                {"Full Inspection/ Fee - With certification", "Paid prohibition clearance on first test (full inspection with certification)", "82", 9, "pass", "p7t5"},
//                {"Full Inspection/ Fee - With certification", "Paid prohibition clearance on first test (full inspection with certification)", "82", 10, "pass", "p7t5"},
//                {"Retest - Paid", "Paid retest prohibition clearance on first test", "120", 1, "pass", "p4t1"},
//                {"Retest - Paid", "Paid retest prohibition clearance on first test", "83", 2, "pass", "p4t2"},
//                {"Retest - Paid", "Paid retest prohibition clearance on first test", "83", 3, "pass", "p4t3"},
//                {"Retest - Paid", "Paid retest prohibition clearance on first test", "83", 4, "pass", "p4t4"},
//                {"Retest - Paid", "Paid retest prohibition clearance on first test", "83", 5, "pass", "p4t5"},
//                {"Retest - Paid", "Paid retest prohibition clearance on first test", "83", 6, "pass", "p4t5"},
//                {"Retest - Paid", "Paid retest prohibition clearance on first test", "83", 7, "pass", "p4t5"},
//                {"Retest - Paid", "Paid retest prohibition clearance on first test", "83", 8, "pass", "p4t5"},
//                {"Retest - Paid", "Paid retest prohibition clearance on first test", "83", 9, "pass", "p4t5"},
//                {"Retest - Paid", "Paid retest prohibition clearance on first test", "83", 10, "pass", "p4t5"},
//                {"Annual test", "Annual test", "40", 1, "pass", "aat1"},
//                {"Annual test", "Annual test", "94", 2, "pass", "aat2"},
//                {"Annual test", "Annual test", "94", 3, "pass", "aat3"},
//                {"Annual test", "Annual test", "94", 4, "pass", "aat4"},
//                {"Annual test", "Annual test", "94", 5, "pass", "aat5"},
//                {"Annual test", "Annual test", "94", 6, "pass", "aat5"},
//                {"Annual test", "Annual test", "94", 7, "pass", "aat5"},
//                {"Annual test", "Annual test", "94", 8, "pass", "aat5"},
//                {"Annual test", "Annual test", "94", 9, "pass", "aat5"},
//                {"Annual test", "Annual test", "94", 10, "pass", "aat5"},
//                {"Paid", "Paid annual test retest", "98", 1, "pass", "rpt1"},
//                {"Paid", "Paid annual test retest", "53", 2, "pass", "rpt2"},
//                {"Paid", "Paid annual test retest", "53", 3, "pass", "rpt3"},
//                {"Paid", "Paid annual test retest", "53", 4, "pass", "rpt4"},
//                {"Paid", "Paid annual test retest", "53", 5, "pass", "rpt5"},
//                {"Paid", "Paid annual test retest", "53", 6, "pass", "rpt5"},
//                {"Paid", "Paid annual test retest", "53", 7, "pass", "rpt5"},
//                {"Paid", "Paid annual test retest", "53", 8, "pass", "rpt5"},
//                {"Paid", "Paid annual test retest", "53", 9, "pass", "rpt5"},
//                {"Paid", "Paid annual test retest", "53", 10, "pass", "rpt5"},
//                {"Part paid", "Part paid annual test retest", "99", 1, "pass", "rst1"},
//                {"Part paid", "Part paid annual test retest", "54", 2, "pass", "rst2"},
//                {"Part paid", "Part paid annual test retest", "54", 3, "pass", "rst3"},
//                {"Part paid", "Part paid annual test retest", "54", 4, "pass", "rst4"},
//                {"Part paid", "Part paid annual test retest", "54", 5, "pass", "rst5"},
//                {"Part paid", "Part paid annual test retest", "54", 6, "pass", "rst5"},
//                {"Part paid", "Part paid annual test retest", "54", 7, "pass", "rst5"},
//                {"Part paid", "Part paid annual test retest", "54", 8, "pass", "rst5"},
//                {"Part paid", "Part paid annual test retest", "54", 9, "pass", "rst5"},
//                {"Part paid", "Part paid annual test retest", "54", 10, "pass", "rst5"},
//                {"With certification", "Paid prohibition clearance (full inspection with certification)", "107", 1, "pass", "p1t1"},
//                {"With certification", "Paid prohibition clearance (full inspection with certification)", "70", 2, "pass", "p1t2"},
//                {"With certification", "Paid prohibition clearance (full inspection with certification)", "70", 3, "pass", "p1t3"},
//                {"With certification", "Paid prohibition clearance (full inspection with certification)", "70", 4, "pass", "p1t4"},
//                {"With certification", "Paid prohibition clearance (full inspection with certification)", "70", 5, "pass", "p1t5"},
//                {"With certification", "Paid prohibition clearance (full inspection with certification)", "70", 6, "pass", "p1t5"},
//                {"With certification", "Paid prohibition clearance (full inspection with certification)", "70", 7, "pass", "p1t5"},
//                {"With certification", "Paid prohibition clearance (full inspection with certification)", "70", 8, "pass", "p1t5"},
//                {"With certification", "Paid prohibition clearance (full inspection with certification)", "70", 9, "pass", "p1t5"},
//                {"With certification", "Paid prohibition clearance (retest with certification)", "113", 1, "pass", "p3t1"},
//                {"With certification", "Paid prohibition clearance (retest with certification)", "76", 2, "pass", "p3t2"},
//                {"With certification", "Paid prohibition clearance (retest with certification)", "76", 3, "pass", "p3t3"},
//                {"With certification", "Paid prohibition clearance (retest with certification)", "76", 4, "pass", "p3t4"},
//                {"With certification", "Paid prohibition clearance (retest with certification)", "76", 5, "pass", "p3t5"},
//                {"With certification", "Paid prohibition clearance (retest with certification)", "76", 6, "pass", "p3t5"},
//                {"With certification", "Paid prohibition clearance (retest with certification)", "76", 7, "pass", "p3t5"},
//                {"With certification", "Paid prohibition clearance (retest with certification)", "76", 8, "pass", "p3t5"},
//                {"With certification", "Paid prohibition clearance (retest with certification)", "76", 9, "pass", "p3t5"},
//                {"With certification", "Paid prohibition clearance (retest with certification)", "76", 10, "pass", "p3t5"},
//                {"With certification", "Part paid prohibition clearance (retest with certification)", "116", 1, "pass", "p6t1"},
//                {"With certification", "Part paid prohibition clearance (retest with certification)", "79", 2, "pass", "p6t2"},
//                {"With certification", "Part paid prohibition clearance (retest with certification)", "79", 3, "pass", "p6t3"},
//                {"With certification", "Part paid prohibition clearance (retest with certification)", "79", 4, "pass", "p6t4"},
//                {"With certification", "Part paid prohibition clearance (retest with certification)", "79", 5, "pass", "p6t5"},
//                {"With certification", "Part paid prohibition clearance (retest with certification)", "79", 6, "pass", "p6t5"},
//                {"With certification", "Part paid prohibition clearance (retest with certification)", "79", 7, "pass", "p6t5"},
//                {"With certification", "Part paid prohibition clearance (retest with certification)", "79", 8, "pass", "p6t5"},
//                {"With certification", "Part paid prohibition clearance (retest with certification)", "79", 9, "pass", "p6t5"},
//                {"With certification", "Part paid prohibition clearance (retest with certification)", "79", 10, "pass", "p6t5"},

                {"First test", "First test", "41", 1, "prs", "fft1"},
                {"First test", "First test", "95", 2, "prs", "fft2"},
                {"First test", "First test", "95", 3, "prs", "fft3"},
                {"First test", "First test", "95", 4, "prs", "fft4"},
                {"First test", "First test", "95", 5, "prs", "fft5"},
                {"First test", "First test", "95", 6, "prs", "fft5"},
                {"First test", "First test", "95", 7, "prs", "fft5"},
                {"First test", "First test", "95", 8, "prs", "fft5"},
                {"First test", "First test", "95", 9, "prs", "fft5"},
                {"First test", "First test", "95", 10, "prs", "fft5"},
                {"Paid", "Paid first test retest", "103", 1, "prs", "rgt1"},
                {"Paid", "Paid first test retest", "65", 2, "prs", "rgt2"},
                {"Paid", "Paid first test retest", "65", 3, "prs", "rgt3"},
                {"Paid", "Paid first test retest", "65", 4, "prs", "rgt4"},
                {"Paid", "Paid first test retest", "65", 5, "prs", "rgt5"},
                {"Paid", "Paid first test retest", "65", 6, "prs", "rgt5"},
                {"Paid", "Paid first test retest", "65", 7, "prs", "rgt5"},
                {"Paid", "Paid first test retest", "65", 8, "prs", "rgt5"},
                {"Paid", "Paid first test retest", "65", 9, "prs", "rgt5"},
                {"Paid", "Paid first test retest", "65", 10, "prs", "rgt5"},
                {"Part paid", "Part paid first test retest", "104", 1, "prs", "rit1"},
                {"Part paid", "Part paid first test retest", "66", 2, "prs", "rit2"},
                {"Part paid", "Part paid first test retest", "66", 3, "prs", "rit3"},
                {"Part paid", "Part paid first test retest", "66", 4, "prs", "rit4"},
                {"Part paid", "Part paid first test retest", "66", 5, "prs", "rit5"},
                {"Part paid", "Part paid first test retest", "66", 6, "prs", "rit5"},
                {"Part paid", "Part paid first test retest", "66", 7, "prs", "rit5"},
                {"Part paid", "Part paid first test retest", "66", 8, "prs", "rit5"},
                {"Part paid", "Part paid first test retest", "66", 9, "prs", "rit5"},
                {"Part paid", "Part paid first test retest", "66", 10, "prs", "rit5"},
                {"Free", "Free first test retest", "67", 3, "prs", "rht"},
//                {"Full Inspection/ Fee - With certification", "Paid prohibition clearance on first test (full inspection with certification)", "119", 1, "prs", "p7t1"},
//                {"Full Inspection/ Fee - With certification", "Paid prohibition clearance on first test (full inspection with certification)", "82", 2, "prs", "p7t2"},
//                {"Full Inspection/ Fee - With certification", "Paid prohibition clearance on first test (full inspection with certification)", "82", 3, "prs", "p7t3"},
//                {"Full Inspection/ Fee - With certification", "Paid prohibition clearance on first test (full inspection with certification)", "82", 4, "prs", "p7t4"},
//                {"Full Inspection/ Fee - With certification", "Paid prohibition clearance on first test (full inspection with certification)", "82", 5, "prs", "p7t5"},
//                {"Full Inspection/ Fee - With certification", "Paid prohibition clearance on first test (full inspection with certification)", "82", 6, "prs", "p7t5"},
//                {"Full Inspection/ Fee - With certification", "Paid prohibition clearance on first test (full inspection with certification)", "82", 7, "prs", "p7t5"},
//                {"Full Inspection/ Fee - With certification", "Paid prohibition clearance on first test (full inspection with certification)", "82", 8, "prs", "p7t5"},
//                {"Full Inspection/ Fee - With certification", "Paid prohibition clearance on first test (full inspection with certification)", "82", 9, "prs", "p7t5"},
//                {"Full Inspection/ Fee - With certification", "Paid prohibition clearance on first test (full inspection with certification)", "82", 10, "prs", "p7t5"},
//                {"Retest - Paid", "Paid retest prohibition clearance on first test", "120", 1, "prs", "p4t1"},
//                {"Retest - Paid", "Paid retest prohibition clearance on first test", "83", 2, "prs", "p4t2"},
//                {"Retest - Paid", "Paid retest prohibition clearance on first test", "83", 3, "prs", "p4t3"},
//                {"Retest - Paid", "Paid retest prohibition clearance on first test", "83", 4, "prs", "p4t4"},
//                {"Retest - Paid", "Paid retest prohibition clearance on first test", "83", 5, "prs", "p4t5"},
//                {"Retest - Paid", "Paid retest prohibition clearance on first test", "83", 6, "prs", "p4t5"},
//                {"Retest - Paid", "Paid retest prohibition clearance on first test", "83", 7, "prs", "p4t5"},
//                {"Retest - Paid", "Paid retest prohibition clearance on first test", "83", 8, "prs", "p4t5"},
//                {"Retest - Paid", "Paid retest prohibition clearance on first test", "83", 9, "prs", "p4t5"},
//                {"Retest - Paid", "Paid retest prohibition clearance on first test", "83", 10, "prs", "p4t5"},
//                {"Annual test", "Annual test", "40", 1, "prs", "aat1"},
//                {"Annual test", "Annual test", "94", 2, "prs", "aat2"},
//                {"Annual test", "Annual test", "94", 3, "prs", "aat3"},
//                {"Annual test", "Annual test", "94", 4, "prs", "aat4"},
//                {"Annual test", "Annual test", "94", 5, "prs", "aat5"},
//                {"Annual test", "Annual test", "94", 6, "prs", "aat5"},
//                {"Annual test", "Annual test", "94", 7, "prs", "aat5"},
//                {"Annual test", "Annual test", "94", 8, "prs", "aat5"},
//                {"Annual test", "Annual test", "94", 9, "prs", "aat5"},
//                {"Annual test", "Annual test", "94", 10, "prs", "aat5"},
//                {"Paid", "Paid annual test retest", "98", 1, "prs", "rpt1"},
//                {"Paid", "Paid annual test retest", "53", 2, "prs", "rpt2"},
//                {"Paid", "Paid annual test retest", "53", 3, "prs", "rpt3"},
//                {"Paid", "Paid annual test retest", "53", 4, "prs", "rpt4"},
//                {"Paid", "Paid annual test retest", "53", 5, "prs", "rpt5"},
//                {"Paid", "Paid annual test retest", "53", 6, "prs", "rpt5"},
//                {"Paid", "Paid annual test retest", "53", 7, "prs", "rpt5"},
//                {"Paid", "Paid annual test retest", "53", 8, "prs", "rpt5"},
//                {"Paid", "Paid annual test retest", "53", 9, "prs", "rpt5"},
//                {"Paid", "Paid annual test retest", "53", 10, "prs", "rpt5"},
//                {"Part paid", "Part paid annual test retest", "99", 1, "prs", "rst1"},
//                {"Part paid", "Part paid annual test retest", "54", 2, "prs", "rst2"},
//                {"Part paid", "Part paid annual test retest", "54", 3, "prs", "rst3"},
//                {"Part paid", "Part paid annual test retest", "54", 4, "prs", "rst4"},
//                {"Part paid", "Part paid annual test retest", "54", 5, "prs", "rst5"},
//                {"Part paid", "Part paid annual test retest", "54", 6, "prs", "rst5"},
//                {"Part paid", "Part paid annual test retest", "54", 7, "prs", "rst5"},
//                {"Part paid", "Part paid annual test retest", "54", 8, "prs", "rst5"},
//                {"Part paid", "Part paid annual test retest", "54", 9, "prs", "rst5"},
//                {"Part paid", "Part paid annual test retest", "54", 10, "prs", "rst5"},
//                {"With certification", "Paid prohibition clearance (full inspection with certification)", "107", 1, "prs", "p1t1"},
//                {"With certification", "Paid prohibition clearance (full inspection with certification)", "70", 2, "prs", "p1t2"},
//                {"With certification", "Paid prohibition clearance (full inspection with certification)", "70", 3, "prs", "p1t3"},
//                {"With certification", "Paid prohibition clearance (full inspection with certification)", "70", 4, "prs", "p1t4"},
//                {"With certification", "Paid prohibition clearance (full inspection with certification)", "70", 5, "prs", "p1t5"},
//                {"With certification", "Paid prohibition clearance (full inspection with certification)", "70", 6, "prs", "p1t5"},
//                {"With certification", "Paid prohibition clearance (full inspection with certification)", "70", 7, "prs", "p1t5"},
//                {"With certification", "Paid prohibition clearance (full inspection with certification)", "70", 8, "prs", "p1t5"},
//                {"With certification", "Paid prohibition clearance (full inspection with certification)", "70", 9, "prs", "p1t5"},
//                {"With certification", "Paid prohibition clearance (retest with certification)", "113", 1, "prs", "p3t1"},
//                {"With certification", "Paid prohibition clearance (retest with certification)", "76", 2, "prs", "p3t2"},
//                {"With certification", "Paid prohibition clearance (retest with certification)", "76", 3, "prs", "p3t3"},
//                {"With certification", "Paid prohibition clearance (retest with certification)", "76", 4, "prs", "p3t4"},
//                {"With certification", "Paid prohibition clearance (retest with certification)", "76", 5, "prs", "p3t5"},
//                {"With certification", "Paid prohibition clearance (retest with certification)", "76", 6, "prs", "p3t5"},
//                {"With certification", "Paid prohibition clearance (retest with certification)", "76", 7, "prs", "p3t5"},
//                {"With certification", "Paid prohibition clearance (retest with certification)", "76", 8, "prs", "p3t5"},
//                {"With certification", "Paid prohibition clearance (retest with certification)", "76", 9, "prs", "p3t5"},
//                {"With certification", "Paid prohibition clearance (retest with certification)", "76", 10, "prs", "p3t5"},
//                {"With certification", "Part paid prohibition clearance (retest with certification)", "116", 1, "prs", "p6t1"},
//                {"With certification", "Part paid prohibition clearance (retest with certification)", "79", 2, "prs", "p6t2"},
//                {"With certification", "Part paid prohibition clearance (retest with certification)", "79", 3, "prs", "p6t3"},
//                {"With certification", "Part paid prohibition clearance (retest with certification)", "79", 4, "prs", "p6t4"},
//                {"With certification", "Part paid prohibition clearance (retest with certification)", "79", 5, "prs", "p6t5"},
//                {"With certification", "Part paid prohibition clearance (retest with certification)", "79", 6, "prs", "p6t5"},
//                {"With certification", "Part paid prohibition clearance (retest with certification)", "79", 7, "prs", "p6t5"},
//                {"With certification", "Part paid prohibition clearance (retest with certification)", "79", 8, "prs", "p6t5"},
//                {"With certification", "Part paid prohibition clearance (retest with certification)", "79", 9, "prs", "p6t5"},
//                {"With certification", "Part paid prohibition clearance (retest with certification)", "79", 10, "prs", "p6t5"},

        });
    }

    private String name;
    private String testTypeName;
    private String testTypeId;
    private int noOfAxles;
    private String testResult;
    private String testCode;

    //sets the firstUseDate / regnDate one year before the current date, plus/minus one day

    public PostTestResultsExpiryDateLogicTrlFirstTest(String name, String testTypeName, String testTypeId, int noOfAxles, String testResult, String testCode) {
        this.name = name;
        this.testTypeName = testTypeName;
        this.testTypeId = testTypeId;
        this.noOfAxles = noOfAxles;
        this.testResult = testResult;
        this.testCode = testCode;
    }


    @WithTag("expiry_dates")
    @Title("CVSB-8684 - TC1 - AC1 - TRL First Test - NO Previous Expiry Date - firstUseDate = null")
    @Test
    public void testResultsFirstTestExpiryTrlFirstDateNull() {

        // Read the base test result JSON.
        String testResultRecord = GenericData.readJsonValueFromFile("test-results_expiry_date_trl_8684.json", "$");

        DateTime submittedTestStartTimestamp = DateTime.now().withZone(DateTimeZone.UTC);
        DateTime submittedTestTypeStartTimestamp = submittedTestStartTimestamp.plusMinutes(5);
        DateTime submittedTypeEndTimestamp = submittedTestTypeStartTimestamp.plusMinutes(10);
        DateTime submittedEndTimestamp = submittedTypeEndTimestamp.plusMinutes(5);
        DateTime submittedFirstUseDate = submittedTypeEndTimestamp.dayOfMonth().withMaximumValue().toDateTime().minusYears(1).plusMonths(2);

        // Create alteration to add one more tech record to in the request body
        String firstUseDate = null;
        String testStartTimestamp = submittedTestStartTimestamp.toInstant().toString();
        String testTypeStartTimestamp = submittedTestTypeStartTimestamp.toInstant().toString();
        String testTypeEndTimestamp = submittedTypeEndTimestamp.toInstant().toString();
        String testEndTimestamp = submittedEndTimestamp.toInstant().toString();
        String testExpectedDate = submittedTypeEndTimestamp.dayOfMonth().withMaximumValue().plusYears(1).withTimeAtStartOfDay().toInstant().toString();
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

        JsonPathAlteration alterationFirstUseDate = new JsonPathAlteration("$.firstUseDate", firstUseDate, "", "REPLACE");
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
                alterationFirstUseDate,
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
        testResultsSteps.valueForFieldInPathShouldBe("[0].firstUseDate", firstUseDate);

        // Verify testTypeEndTimestamp is
        testResultsSteps.valueForFieldInPathShouldBe("[0].testTypes[0].testTypeEndTimestamp", testTypeEndTimestamp);

        // Verify testAnniversaryDate field has the expected value
        testResultsSteps.valueForFieldInPathShouldBe("[0].testTypes[0].testAnniversaryDate", testExpectedDate);

        // Verify testExpiryDate field has the expected value
        testResultsSteps.valueForFieldInPathShouldBe("[0].testTypes[0].testExpiryDate", testExpectedDate);

    }


    @WithTag("expiry_dates")
    @Title("CVSB-8684 - TC1 - AC1 - TRL First Test - NO Previous Expiry Date - firstUseDate is missing")
    @Test
    public void testResultsFirstTestExpiryTrlFirstDateMissing() {

        // Read the base test result JSON.
        String testResultRecord = GenericData.readJsonValueFromFile("test-results_expiry_date_trl_8684.json", "$");

        DateTime submittedTestStartTimestamp = DateTime.now().withZone(DateTimeZone.UTC);
        DateTime submittedTestTypeStartTimestamp = submittedTestStartTimestamp.plusMinutes(5);
        DateTime submittedTypeEndTimestamp = submittedTestTypeStartTimestamp.plusMinutes(10);
        DateTime submittedEndTimestamp = submittedTypeEndTimestamp.plusMinutes(5);

        // Create alteration to add one more tech record to in the request body
        String testStartTimestamp = submittedTestStartTimestamp.toInstant().toString();
        String testTypeStartTimestamp = submittedTestTypeStartTimestamp.toInstant().toString();
        String testTypeEndTimestamp = submittedTypeEndTimestamp.toInstant().toString();
        String testEndTimestamp = submittedEndTimestamp.toInstant().toString();
        String testExpectedDate = submittedTypeEndTimestamp.dayOfMonth().withMaximumValue().plusYears(1).withTimeAtStartOfDay().toInstant().toString();
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

        JsonPathAlteration alterationFirstUseDate = new JsonPathAlteration("$.firstUseDate", "", "", "DELETE");
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
                alterationFirstUseDate,
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

        // Verify testAnniversaryDate field has the expected value
        testResultsSteps.valueForFieldInPathShouldBe("[0].testTypes[0].testAnniversaryDate", testExpectedDate);

        // Verify testExpiryDate field has the expected value
        testResultsSteps.valueForFieldInPathShouldBe("[0].testTypes[0].testExpiryDate", testExpectedDate);

    }


    @WithTag("expiry_dates")
    @Title("CVSB-8684 - TC1 - AC1 - TRL First Test - NO Previous Expiry Date - firstUseDate = Today(-1 year, +1 month)")
    @Test
    public void testResultFirstTestExpiryTrlFirstUseDateLessThanOneYear() {

        // Read the base test result JSON.
        String testResultRecord = GenericData.readJsonValueFromFile("test-results_expiry_date_trl_8684.json", "$");

        DateTime currentTime = DateTime.now().withZone(DateTimeZone.UTC);
        DateTime submittedTestStartTimestamp = currentTime.minusMinutes(15);
        DateTime submittedTestTypeStartTimestamp = currentTime.minusMinutes(10);
        DateTime submittedTypeEndTimestamp = currentTime.minusMinutes(5);
        DateTime submittedEndTimestamp = currentTime;

        DateTime submittedFirstUseDate = currentTime.toDateTime().minusYears(1).plusMonths(1);

        // Create alteration to add one more tech record to in the request body
        String testStartTimestamp = submittedTestStartTimestamp.toInstant().toString();
        String testFirstUseDateTimestamp = submittedFirstUseDate.toDateTime().toString("yyyy-MM-dd");
        String testTypeStartTimestamp = submittedTestTypeStartTimestamp.toInstant().toString();
        String testTypeEndTimestamp = submittedTypeEndTimestamp.toInstant().toString();
        String testEndTimestamp = submittedEndTimestamp.toInstant().toString();
        String randomVin = GenericData.generateRandomVin();
        String randomTestResultId = RandomStringUtils.randomNumeric(5);

        String testExpectedExpiryDate = submittedFirstUseDate.dayOfMonth().withMaximumValue().plusYears(2).toInstant().toString();

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

        JsonPathAlteration alterationFirstUseDate = new JsonPathAlteration("$.firstUseDate", testFirstUseDateTimestamp, "", "REPLACE");
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
                alterationFirstUseDate,
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

        // Verify testTypeEndTimestamp is
        testResultsSteps.valueForFieldInPathShouldBe("[0].testTypes[0].testTypeEndTimestamp", testTypeEndTimestamp);

        // Verify testAnniversaryDate field has the expected value
        testResultsSteps.valueForFieldInPathShouldStartWith("[0].testTypes[0].testAnniversaryDate", testExpectedExpiryDate.substring(0,10));

        // Verify testExpiryDate field has the expected value
        testResultsSteps.valueForFieldInPathShouldStartWith("[0].testTypes[0].testExpiryDate", testExpectedExpiryDate.substring(0,10));

    }

    @WithTag("expiry_dates")
    @Title("CVSB-8684 - TC1 - AC1 - TRL First Test - NO Previous Expiry Date - firstUseDate = Today(-1 year, -1 month)")
    @Test
    public void testResultsFirstTestExpiryTrlFirstUseDateMoreThanOneYear() {

        // Read the base test result JSON.
        String testResultRecord = GenericData.readJsonValueFromFile("test-results_expiry_date_trl_8684.json", "$");

        DateTime currentTime = DateTime.now().withZone(DateTimeZone.UTC);
        DateTime submittedTestStartTimestamp = currentTime.minusMinutes(15);
        DateTime submittedTestTypeStartTimestamp = currentTime.minusMinutes(10);
        DateTime submittedTypeEndTimestamp = currentTime.minusMinutes(5);
        DateTime submittedEndTimestamp = currentTime;

        DateTime submittedFirstUseDate = currentTime.toDateTime().minusYears(1).minusMonths(1);

        // Create alteration to add one more tech record to in the request body
        String testStartTimestamp = submittedTestStartTimestamp.toInstant().toString();
        String testFirstUseDateTimestamp = submittedFirstUseDate.toDateTime().toString("yyyy-MM-dd");
        String testTypeStartTimestamp = submittedTestTypeStartTimestamp.toInstant().toString();
        String testTypeEndTimestamp = submittedTypeEndTimestamp.toInstant().toString();
        String testEndTimestamp = submittedEndTimestamp.toInstant().toString();
        String randomVin = GenericData.generateRandomVin();
        String randomTestResultId = RandomStringUtils.randomNumeric(5);

        String testExpectedExpiryDate = submittedEndTimestamp.dayOfMonth().withMaximumValue().plusYears(1).toInstant().toString();

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

        JsonPathAlteration alterationFirstUseDate = new JsonPathAlteration("$.firstUseDate", testFirstUseDateTimestamp, "", "REPLACE");
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
                alterationFirstUseDate,
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

        // Verify testTypeEndTimestamp is
        testResultsSteps.valueForFieldInPathShouldBe("[0].testTypes[0].testTypeEndTimestamp", testTypeEndTimestamp);

        // Verify testAnniversaryDate field has the expected value
        testResultsSteps.valueForFieldInPathShouldStartWith("[0].testTypes[0].testAnniversaryDate", testExpectedExpiryDate.substring(0,10));

        // Verify testExpiryDate field has the expected value
        testResultsSteps.valueForFieldInPathShouldStartWith("[0].testTypes[0].testExpiryDate", testExpectedExpiryDate.substring(0,10));

    }

//    @Ignore
//    @WithTag("expiry_dates")
//    @Title("CVSB-8684 - Include expiry date calculation validation in BE test suite - TRL - testTypeEndTimestamp (date only) is less than, but not including, 2 months before (last day in the month of firstUseDate + 1 year)")
//    @Test
//    public void testResultsNoFirstTestExpiryTrlLessThanTwoMonthsOrMoreBefore() {
//
//        // Read the base test result JSON.
//        String testResultRecord = GenericData.readJsonValueFromFile("test-results_expiry_date_trl_8684.json", "$");
//
//        DateTime submittedTestStartTimestamp = DateTime.now().withZone(DateTimeZone.UTC);
//        DateTime submittedTestTypeStartTimestamp = submittedTestStartTimestamp.plusMinutes(5);
//        DateTime submittedTypeEndTimestamp = submittedTestTypeStartTimestamp.plusMinutes(10);
//        DateTime submittedEndTimestamp = submittedTypeEndTimestamp.plusMinutes(5);
//
//        // Create alteration to add one more tech record to in the request body
//        String firstUseDate = submittedTypeEndTimestamp.toDateTime().minusYears(1).plusMonths(2).plusDays(1).toString("yyyy-MM-dd");
//        String testStartTimestamp = submittedTestStartTimestamp.toInstant().toString();
//        String testTypeStartTimestamp = submittedTestTypeStartTimestamp.toInstant().toString();
//        String testTypeEndTimestamp = submittedTypeEndTimestamp.toInstant().toString();
//        String testEndTimestamp = submittedEndTimestamp.toInstant().toString();
//        String testExpectedDate = submittedTestStartTimestamp.dayOfMonth().withMaximumValue().plusYears(1).withTimeAtStartOfDay().toInstant().toString();
//        String randomVin = GenericData.generateRandomVin();
//        String randomTestResultId = RandomStringUtils.randomNumeric(5);
//
//
////        String returned_testTypeEndTimestamp = "";
////        String returned_testExpiryDate = "";
////        String returned_createdAt = "";
////        String returned_testTypeStartTimestamp = "";
//
////        System.out.println("submittedFirstUseDate: " + firstUseDate);
////        System.out.println("submittedTestStartTimestamp: " + testStartTimestamp);
////        System.out.println("submittedTestTypeStartTimestamp: " + testTypeStartTimestamp);
////        System.out.println("submittedTestTypeEndTimestamp: " + testTypeEndTimestamp);
////        System.out.println("submittedTestEndTimestamp: " + testEndTimestamp);
////        System.out.println("returned_testAnniversaryDate: " + testExpectedDate);
//
//        JsonPathAlteration alterationFirstUseDate = new JsonPathAlteration("$.firstUseDate", firstUseDate, "", "REPLACE");
//        JsonPathAlteration alterationTestStartTimestamp = new JsonPathAlteration("$.testStartTimestamp", testStartTimestamp, "", "REPLACE");
//        JsonPathAlteration alterationTestEndTimestamp = new JsonPathAlteration("$.testEndTimestamp", testEndTimestamp, "", "REPLACE");
//        JsonPathAlteration alterationTestTypeStartTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeStartTimestamp", testTypeStartTimestamp, "", "REPLACE");
//        JsonPathAlteration alterationTestTypeEndTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeEndTimestamp", testTypeEndTimestamp, "", "REPLACE");
//        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
//        JsonPathAlteration alterationTestResultId = new JsonPathAlteration("$.testResultId", randomTestResultId, "", "REPLACE");
//
//        JsonPathAlteration alterationTestName = new JsonPathAlteration("$.testTypes[0].name", name,"","REPLACE");
//        JsonPathAlteration alterationTestTypeId = new JsonPathAlteration("$.testTypes[0].testTypeId", testTypeId,"","REPLACE");
//        JsonPathAlteration alterationTestTypeName = new JsonPathAlteration("$.testTypes[0].testTypeName", testTypeName,"","REPLACE");
//        JsonPathAlteration alterationTestResult = new JsonPathAlteration("$.testTypes[0].testResult", testResult,"","REPLACE");
//        JsonPathAlteration alterationNoOfAxles = new JsonPathAlteration("$.noOfAxles", noOfAxles,"","REPLACE");
//        JsonPathAlteration alterationTestExpiryDate = new JsonPathAlteration("$.testTypes[0].testExpiryDate", "", "", "DELETE");
//
//        // Collate the list of alterations.
//        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(
//                alterationFirstUseDate,
//                alterationTestStartTimestamp,
//                alterationTestEndTimestamp,
//                alterationTestTypeStartTimestamp,
//                alterationTestTypeEndTimestamp,
//                alterationVin,
//                alterationTestResultId,
//                alterationTestName,
//                alterationTestTypeId,
//                alterationTestTypeName,
//                alterationNoOfAxles,
//                alterationTestExpiryDate,
//                alterationTestResult
//        ));
//
//        // Post the results, together with any alterations, and verify that they are accepted.
//        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, alterations);
//        testResultsSteps.statusCodeShouldBe(201);
//        testResultsSteps.validateData("Test records created");
//
//        // Retrieve the created record, and verify that the fields are present.
//        testResultsSteps.getTestResults(randomVin);
//        testResultsSteps.statusCodeShouldBe(200);
//
//        // Verify testCode field has the expected value
//        testResultsSteps.valueForFieldInPathShouldBe("[0].testTypes[0].testCode", testCode);
//
//        // Verify firstUseDate is
//        testResultsSteps.valueForFieldInPathShouldBe("[0].firstUseDate", firstUseDate);
//
//        // Verify testTypeEndTimestamp is
//        testResultsSteps.valueForFieldInPathShouldBe("[0].testTypes[0].testTypeEndTimestamp", testTypeEndTimestamp);
//
//        // Verify testAnniversaryDate field has the expected value
//        testResultsSteps.valueForFieldInPathShouldBe("[0].testTypes[0].testAnniversaryDate", testExpectedDate);
//
//        // Verify testExpiryDate field has the expected value
//        testResultsSteps.valueForFieldInPathShouldStartWith("[0].testTypes[0].testExpiryDate", testExpectedDate.substring(0,10));
//
//    }
//
//
//    @Ignore
//    @WithTag("expiry_dates")
//    @Title("CVSB-8684 - Include expiry date calculation validation in BE test suite - TRL - testTypeEndTimestamp (date only) is less than, but not including, 2 months before (last day in the month of firstUseDate + 1 year) AND no existing testExpiryDate")
//    @Test
//    public void testResultsNoFirstTestExpiryTrlLessThanTwoMonthsOrLessBefore() {
//
//        // Read the base test result JSON.
//        String testResultRecord = GenericData.readJsonValueFromFile("test-results_expiry_date_trl_8684.json", "$");
//
//        DateTime submittedTestStartTimestamp = DateTime.now().withZone(DateTimeZone.UTC);
//        DateTime submittedTestTypeStartTimestamp = submittedTestStartTimestamp.plusMinutes(5);
//        DateTime submittedTypeEndTimestamp = submittedTestTypeStartTimestamp.plusMinutes(10);
//        DateTime submittedEndTimestamp = submittedTypeEndTimestamp.plusMinutes(5);
//        DateTime submittedFirstUseDate = submittedTypeEndTimestamp.dayOfMonth().withMaximumValue().toDateTime().minusYears(1).plusMonths(2);
//
//        // Create alteration to add one more tech record to in the request body
//        String firstUseDate = submittedFirstUseDate.toString("yyyy-MM-dd");
//        String testStartTimestamp = submittedTestStartTimestamp.toInstant().toString();
//        String testTypeStartTimestamp = submittedTestTypeStartTimestamp.toInstant().toString();
//        String testTypeEndTimestamp = submittedTypeEndTimestamp.toInstant().toString();
//        String testEndTimestamp = submittedEndTimestamp.toInstant().toString();
//        String testExpectedDate = submittedTestStartTimestamp.dayOfMonth().withMaximumValue().plusYears(1).withTimeAtStartOfDay().toInstant().toString();
//        String randomVin = GenericData.generateRandomVin();
//        String randomTestResultId = RandomStringUtils.randomNumeric(5);
//
//
////        String returned_testTypeEndTimestamp = "";
////        String returned_testExpiryDate = "";
////        String returned_createdAt = "";
////        String returned_testTypeStartTimestamp = "";
//
////        System.out.println("submittedFirstUseDate: " + firstUseDate);
////        System.out.println("submittedTestStartTimestamp: " + testStartTimestamp);
////        System.out.println("submittedTestTypeStartTimestamp: " + testTypeStartTimestamp);
////        System.out.println("submittedTestTypeEndTimestamp: " + testTypeEndTimestamp);
////        System.out.println("submittedTestEndTimestamp: " + testEndTimestamp);
////        System.out.println("returned_testAnniversaryDate: " + testExpectedDate);
//
//        JsonPathAlteration alterationFirstUseDate = new JsonPathAlteration("$.firstUseDate", firstUseDate, "", "REPLACE");
//        JsonPathAlteration alterationTestStartTimestamp = new JsonPathAlteration("$.testStartTimestamp", testStartTimestamp, "", "REPLACE");
//        JsonPathAlteration alterationTestEndTimestamp = new JsonPathAlteration("$.testEndTimestamp", testEndTimestamp, "", "REPLACE");
//        JsonPathAlteration alterationTestTypeStartTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeStartTimestamp", testTypeStartTimestamp, "", "REPLACE");
//        JsonPathAlteration alterationTestTypeEndTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeEndTimestamp", testTypeEndTimestamp, "", "REPLACE");
//        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
//        JsonPathAlteration alterationTestResultId = new JsonPathAlteration("$.testResultId", randomTestResultId, "", "REPLACE");
//
//        JsonPathAlteration alterationTestName = new JsonPathAlteration("$.testTypes[0].name", name,"","REPLACE");
//        JsonPathAlteration alterationTestTypeId = new JsonPathAlteration("$.testTypes[0].testTypeId", testTypeId,"","REPLACE");
//        JsonPathAlteration alterationTestTypeName = new JsonPathAlteration("$.testTypes[0].testTypeName", testTypeName,"","REPLACE");
//        JsonPathAlteration alterationTestResult = new JsonPathAlteration("$.testTypes[0].testResult", testResult,"","REPLACE");
//        JsonPathAlteration alterationNoOfAxles = new JsonPathAlteration("$.noOfAxles", noOfAxles,"","REPLACE");
//        JsonPathAlteration alterationTestExpiryDate = new JsonPathAlteration("$.testTypes[0].testExpiryDate", "", "", "DELETE");
//
//        // Collate the list of alterations.
//        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(
//                alterationFirstUseDate,
//                alterationTestStartTimestamp,
//                alterationTestEndTimestamp,
//                alterationTestTypeStartTimestamp,
//                alterationTestTypeEndTimestamp,
//                alterationVin,
//                alterationTestResultId,
//                alterationTestName,
//                alterationTestTypeId,
//                alterationTestTypeName,
//                alterationNoOfAxles,
//                alterationTestExpiryDate,
//                alterationTestResult
//        ));
//
//        // Post the results, together with any alterations, and verify that they are accepted.
//        testResultsSteps.postVehicleTestResultsWithAlterations(testResultRecord, alterations);
//        testResultsSteps.statusCodeShouldBe(201);
//        testResultsSteps.validateData("Test records created");
//
//        // Retrieve the created record, and verify that the fields are present.
//        testResultsSteps.getTestResults(randomVin);
//        testResultsSteps.statusCodeShouldBe(200);
//
//        // Verify testCode field has the expected value
//        testResultsSteps.valueForFieldInPathShouldBe("[0].testTypes[0].testCode", testCode);
//
//        // Verify firstUseDate is
//        testResultsSteps.valueForFieldInPathShouldBe("[0].firstUseDate", firstUseDate);
//
//        // Verify testTypeEndTimestamp is
//        testResultsSteps.valueForFieldInPathShouldBe("[0].testTypes[0].testTypeEndTimestamp", testTypeEndTimestamp);
//
//        // Verify testAnniversaryDate field has the expected value
//        testResultsSteps.valueForFieldInPathShouldBe("[0].testTypes[0].testAnniversaryDate", testExpectedDate);
//
//        // Verify testExpiryDate field has the expected value
//        testResultsSteps.valueForFieldInPathShouldStartWith("[0].testTypes[0].testExpiryDate", testExpectedDate.substring(0,10));
//
//    }

}
