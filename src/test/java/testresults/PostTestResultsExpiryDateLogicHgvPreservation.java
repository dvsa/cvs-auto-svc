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
@WithTag("expiry_dates")
@RunWith(SerenityParameterizedRunner.class)
public class PostTestResultsExpiryDateLogicHgvPreservation {

    @Steps
    TestResultsSteps testResultsSteps;


    @TestData
    public static Collection<Object[]> testData(){
        return Arrays.asList(new Object[][]{

                {"Annual test", "Annual test", "94", 2, "pass",  "aav2", true},
//                {"Annual test", "Annual test", "94", 3, "pass",  "aav3", true},
//                {"Annual test", "Annual test", "94", 4, "pass",  "aav4", true},
//                {"Annual test", "Annual test", "94", 5, "pass",  "aav5", true},
//                {"Annual test", "Annual test", "94", 6, "pass",  "aav5", true},
//                {"Annual test", "Annual test", "94", 7, "pass",  "aav5", true},
//                {"Annual test", "Annual test", "94", 8, "pass",  "aav5", true},
//                {"Annual test", "Annual test", "94", 9, "pass",  "aav5", true},
//                {"Annual test", "Annual test", "94", 10, "pass",  "aav5", true},
//                {"Paid", "Paid annual test retest", "53", 2, "pass",  "rpv2", true},
//                {"Paid", "Paid annual test retest", "53", 3, "pass",  "rpv3", true},
//                {"Paid", "Paid annual test retest", "53", 4, "pass",  "rpv4", true},
//                {"Paid", "Paid annual test retest", "53", 5, "pass",  "rpv5", true},
//                {"Paid", "Paid annual test retest", "53", 6, "pass",  "rpv5", true},
//                {"Paid", "Paid annual test retest", "53", 7, "pass",  "rpv5", true},
//                {"Paid", "Paid annual test retest", "53", 8, "pass",  "rpv5", true},
//                {"Paid", "Paid annual test retest", "53", 9, "pass",  "rpv5", true},
//                {"Paid", "Paid annual test retest", "53", 10, "pass",  "rpv5", true},
//                {"Part paid", "Part paid annual test retest", "54", 2, "pass",  "rsv2", true},
//                {"Part paid", "Part paid annual test retest", "54", 3, "pass",  "rsv3", true},
//                {"Part paid", "Part paid annual test retest", "54", 4, "pass",  "rsv4", true},
//                {"Part paid", "Part paid annual test retest", "54", 5, "pass",  "rsv5", true},
//                {"Part paid", "Part paid annual test retest", "54", 6, "pass",  "rsv5", true},
//                {"Part paid", "Part paid annual test retest", "54", 7, "pass",  "rsv5", true},
//                {"Part paid", "Part paid annual test retest", "54", 8, "pass",  "rsv5", true},
//                {"Part paid", "Part paid annual test retest", "54", 9, "pass",  "rsv5", true},
//                {"Part paid", "Part paid annual test retest", "54", 10, "pass",  "rsv5", true},
//                {"With certification", "Paid prohibition clearance (full inspection with certification)", "70", 2, "pass",  "p1v2", true},
//                {"With certification", "Paid prohibition clearance (full inspection with certification)", "70", 3, "pass",  "p1v3", true},
//                {"With certification", "Paid prohibition clearance (full inspection with certification)", "70", 4, "pass",  "p1v4", true},
//                {"With certification", "Paid prohibition clearance (full inspection with certification)", "70", 5, "pass",  "p1v5", true},
//                {"With certification", "Paid prohibition clearance (full inspection with certification)", "70", 6, "pass",  "p1v5", true},
//                {"With certification", "Paid prohibition clearance (full inspection with certification)", "70", 7, "pass",  "p1v5", true},
//                {"With certification", "Paid prohibition clearance (full inspection with certification)", "70", 8, "pass",  "p1v5", true},
//                {"With certification", "Paid prohibition clearance (full inspection with certification)", "70", 9, "pass",  "p1v5", true},
//                {"With certification", "Paid prohibition clearance (full inspection with certification)", "70", 10, "pass",  "p1v5", true},
//                {"With certification", "Paid prohibition clearance (retest with certification)", "76", 2, "pass",  "p3v2", true},
//                {"With certification", "Paid prohibition clearance (retest with certification)", "76", 3, "pass",  "p3v3", true},
//                {"With certification", "Paid prohibition clearance (retest with certification)", "76", 4, "pass",  "p3v4", true},
//                {"With certification", "Paid prohibition clearance (retest with certification)", "76", 5, "pass",  "p3v5", true},
//                {"With certification", "Paid prohibition clearance (retest with certification)", "76", 6, "pass",  "p3v5", true},
//                {"With certification", "Paid prohibition clearance (retest with certification)", "76", 7, "pass",  "p3v5", true},
//                {"With certification", "Paid prohibition clearance (retest with certification)", "76", 8, "pass",  "p3v5", true},
//                {"With certification", "Paid prohibition clearance (retest with certification)", "76", 9, "pass",  "p3v5", true},
//                {"With certification", "Paid prohibition clearance (retest with certification)", "76", 10, "pass",  "p3v5", true},
//                {"With certification", "Part paid prohibition clearance (retest with certification)", "79", 2, "pass",  "p6v2", true},
//                {"With certification", "Part paid prohibition clearance (retest with certification)", "79", 3, "pass",  "p6v3", true},
//                {"With certification", "Part paid prohibition clearance (retest with certification)", "79", 4, "pass",  "p6v4", true},
//                {"With certification", "Part paid prohibition clearance (retest with certification)", "79", 5, "pass",  "p6v5", true},
//                {"With certification", "Part paid prohibition clearance (retest with certification)", "79", 6, "pass",  "p6v5", true},
//                {"With certification", "Part paid prohibition clearance (retest with certification)", "79", 7, "pass",  "p6v5", true},
//                {"With certification", "Part paid prohibition clearance (retest with certification)", "79", 8, "pass",  "p6v5", true},
//                {"With certification", "Part paid prohibition clearance (retest with certification)", "79", 9, "pass",  "p6v5", true},
//                {"With certification", "Part paid prohibition clearance (retest with certification)", "79", 10, "pass",  "p6v5", true},
//
                {"Annual test", "Annual test", "94", 2, "prs",  "aav2", true},
//                {"Annual test", "Annual test", "94", 3, "prs",  "aav3", true},
//                {"Annual test", "Annual test", "94", 4, "prs",  "aav4", true},
//                {"Annual test", "Annual test", "94", 5, "prs",  "aav5", true},
//                {"Annual test", "Annual test", "94", 6, "prs",  "aav5", true},
//                {"Annual test", "Annual test", "94", 7, "prs",  "aav5", true},
//                {"Annual test", "Annual test", "94", 8, "prs",  "aav5", true},
//                {"Annual test", "Annual test", "94", 9, "prs",  "aav5", true},
//                {"Annual test", "Annual test", "94", 10, "prs",  "aav5", true},
//                {"Paid", "Paid annual test retest", "53", 2, "prs",  "rpv2", true},
//                {"Paid", "Paid annual test retest", "53", 3, "prs",  "rpv3", true},
//                {"Paid", "Paid annual test retest", "53", 4, "prs",  "rpv4", true},
//                {"Paid", "Paid annual test retest", "53", 5, "prs",  "rpv5", true},
//                {"Paid", "Paid annual test retest", "53", 6, "prs",  "rpv5", true},
//                {"Paid", "Paid annual test retest", "53", 7, "prs",  "rpv5", true},
//                {"Paid", "Paid annual test retest", "53", 8, "prs",  "rpv5", true},
//                {"Paid", "Paid annual test retest", "53", 9, "prs",  "rpv5", true},
//                {"Paid", "Paid annual test retest", "53", 10, "prs",  "rpv5", true},
//                {"Part paid", "Part paid annual test retest", "54", 2, "prs",  "rsv2", true},
//                {"Part paid", "Part paid annual test retest", "54", 3, "prs",  "rsv3", true},
//                {"Part paid", "Part paid annual test retest", "54", 4, "prs",  "rsv4", true},
//                {"Part paid", "Part paid annual test retest", "54", 5, "prs",  "rsv5", true},
//                {"Part paid", "Part paid annual test retest", "54", 6, "prs",  "rsv5", true},
//                {"Part paid", "Part paid annual test retest", "54", 7, "prs",  "rsv5", true},
//                {"Part paid", "Part paid annual test retest", "54", 8, "prs",  "rsv5", true},
//                {"Part paid", "Part paid annual test retest", "54", 9, "prs",  "rsv5", true},
//                {"Part paid", "Part paid annual test retest", "54", 10, "prs",  "rsv5", true},
//                {"With certification", "Paid prohibition clearance (full inspection with certification)", "70", 2, "prs",  "p1v2", true},
//                {"With certification", "Paid prohibition clearance (full inspection with certification)", "70", 3, "prs",  "p1v3", true},
//                {"With certification", "Paid prohibition clearance (full inspection with certification)", "70", 4, "prs",  "p1v4", true},
//                {"With certification", "Paid prohibition clearance (full inspection with certification)", "70", 5, "prs",  "p1v5", true},
//                {"With certification", "Paid prohibition clearance (full inspection with certification)", "70", 6, "prs",  "p1v5", true},
//                {"With certification", "Paid prohibition clearance (full inspection with certification)", "70", 7, "prs",  "p1v5", true},
//                {"With certification", "Paid prohibition clearance (full inspection with certification)", "70", 8, "prs",  "p1v5", true},
//                {"With certification", "Paid prohibition clearance (full inspection with certification)", "70", 9, "prs",  "p1v5", true},
//                {"With certification", "Paid prohibition clearance (full inspection with certification)", "70", 10, "prs",  "p1v5", true},
//                {"With certification", "Paid prohibition clearance (retest with certification)", "76", 2, "prs",  "p3v2", true},
//                {"With certification", "Paid prohibition clearance (retest with certification)", "76", 3, "prs",  "p3v3", true},
//                {"With certification", "Paid prohibition clearance (retest with certification)", "76", 4, "prs",  "p3v4", true},
//                {"With certification", "Paid prohibition clearance (retest with certification)", "76", 5, "prs",  "p3v5", true},
//                {"With certification", "Paid prohibition clearance (retest with certification)", "76", 6, "prs",  "p3v5", true},
//                {"With certification", "Paid prohibition clearance (retest with certification)", "76", 7, "prs",  "p3v5", true},
//                {"With certification", "Paid prohibition clearance (retest with certification)", "76", 8, "prs",  "p3v5", true},
//                {"With certification", "Paid prohibition clearance (retest with certification)", "76", 9, "prs",  "p3v5", true},
//                {"With certification", "Paid prohibition clearance (retest with certification)", "76", 10, "prs",  "p3v5", true},
//                {"With certification", "Part paid prohibition clearance (retest with certification)", "79", 2, "prs",  "p6v2", true},
//                {"With certification", "Part paid prohibition clearance (retest with certification)", "79", 3, "prs",  "p6v3", true},
//                {"With certification", "Part paid prohibition clearance (retest with certification)", "79", 4, "prs",  "p6v4", true},
//                {"With certification", "Part paid prohibition clearance (retest with certification)", "79", 5, "prs",  "p6v5", true},
//                {"With certification", "Part paid prohibition clearance (retest with certification)", "79", 6, "prs",  "p6v5", true},
//                {"With certification", "Part paid prohibition clearance (retest with certification)", "79", 7, "prs",  "p6v5", true},
//                {"With certification", "Part paid prohibition clearance (retest with certification)", "79", 8, "prs",  "p6v5", true},
//                {"With certification", "Part paid prohibition clearance (retest with certification)", "79", 9, "prs",  "p6v5", true},
//                {"With certification", "Part paid prohibition clearance (retest with certification)", "79", 10, "prs",  "p6v5", true},


                {"Annual test", "Annual test", "94", 2, "pass",  "aav2", false},
//                {"Annual test", "Annual test", "94", 3, "pass",  "aav3", false},
//                {"Annual test", "Annual test", "94", 4, "pass",  "aav4", false},
//                {"Annual test", "Annual test", "94", 5, "pass",  "aav5", false},
//                {"Annual test", "Annual test", "94", 6, "pass",  "aav5", false},
//                {"Annual test", "Annual test", "94", 7, "pass",  "aav5", false},
//                {"Annual test", "Annual test", "94", 8, "pass",  "aav5", false},
//                {"Annual test", "Annual test", "94", 9, "pass",  "aav5", false},
//                {"Annual test", "Annual test", "94", 10, "pass",  "aav5", false},
//                {"Paid", "Paid annual test retest", "53", 2, "pass",  "rpv2", false},
//                {"Paid", "Paid annual test retest", "53", 3, "pass",  "rpv3", false},
//                {"Paid", "Paid annual test retest", "53", 4, "pass",  "rpv4", false},
//                {"Paid", "Paid annual test retest", "53", 5, "pass",  "rpv5", false},
//                {"Paid", "Paid annual test retest", "53", 6, "pass",  "rpv5", false},
//                {"Paid", "Paid annual test retest", "53", 7, "pass",  "rpv5", false},
//                {"Paid", "Paid annual test retest", "53", 8, "pass",  "rpv5", false},
//                {"Paid", "Paid annual test retest", "53", 9, "pass",  "rpv5", false},
//                {"Paid", "Paid annual test retest", "53", 10, "pass",  "rpv5", false},
//                {"Part paid", "Part paid annual test retest", "54", 2, "pass",  "rsv2", false},
//                {"Part paid", "Part paid annual test retest", "54", 3, "pass",  "rsv3", false},
//                {"Part paid", "Part paid annual test retest", "54", 4, "pass",  "rsv4", false},
//                {"Part paid", "Part paid annual test retest", "54", 5, "pass",  "rsv5", false},
//                {"Part paid", "Part paid annual test retest", "54", 6, "pass",  "rsv5", false},
//                {"Part paid", "Part paid annual test retest", "54", 7, "pass",  "rsv5", false},
//                {"Part paid", "Part paid annual test retest", "54", 8, "pass",  "rsv5", false},
//                {"Part paid", "Part paid annual test retest", "54", 9, "pass",  "rsv5", false},
//                {"Part paid", "Part paid annual test retest", "54", 10, "pass",  "rsv5", false},
//                {"With certification", "Paid prohibition clearance (full inspection with certification)", "70", 2, "pass",  "p1v2", false},
//                {"With certification", "Paid prohibition clearance (full inspection with certification)", "70", 3, "pass",  "p1v3", false},
//                {"With certification", "Paid prohibition clearance (full inspection with certification)", "70", 4, "pass",  "p1v4", false},
//                {"With certification", "Paid prohibition clearance (full inspection with certification)", "70", 5, "pass",  "p1v5", false},
//                {"With certification", "Paid prohibition clearance (full inspection with certification)", "70", 6, "pass",  "p1v5", false},
//                {"With certification", "Paid prohibition clearance (full inspection with certification)", "70", 7, "pass",  "p1v5", false},
//                {"With certification", "Paid prohibition clearance (full inspection with certification)", "70", 8, "pass",  "p1v5", false},
//                {"With certification", "Paid prohibition clearance (full inspection with certification)", "70", 9, "pass",  "p1v5", false},
//                {"With certification", "Paid prohibition clearance (full inspection with certification)", "70", 10, "pass",  "p1v5", false},
//                {"With certification", "Paid prohibition clearance (retest with certification)", "76", 2, "pass",  "p3v2", false},
//                {"With certification", "Paid prohibition clearance (retest with certification)", "76", 3, "pass",  "p3v3", false},
//                {"With certification", "Paid prohibition clearance (retest with certification)", "76", 4, "pass",  "p3v4", false},
//                {"With certification", "Paid prohibition clearance (retest with certification)", "76", 5, "pass",  "p3v5", false},
//                {"With certification", "Paid prohibition clearance (retest with certification)", "76", 6, "pass",  "p3v5", false},
//                {"With certification", "Paid prohibition clearance (retest with certification)", "76", 7, "pass",  "p3v5", false},
//                {"With certification", "Paid prohibition clearance (retest with certification)", "76", 8, "pass",  "p3v5", false},
//                {"With certification", "Paid prohibition clearance (retest with certification)", "76", 9, "pass",  "p3v5", false},
//                {"With certification", "Paid prohibition clearance (retest with certification)", "76", 10, "pass",  "p3v5", false},
//                {"With certification", "Part paid prohibition clearance (retest with certification)", "79", 2, "pass",  "p6v2", false},
//                {"With certification", "Part paid prohibition clearance (retest with certification)", "79", 3, "pass",  "p6v3", false},
//                {"With certification", "Part paid prohibition clearance (retest with certification)", "79", 4, "pass",  "p6v4", false},
//                {"With certification", "Part paid prohibition clearance (retest with certification)", "79", 5, "pass",  "p6v5", false},
//                {"With certification", "Part paid prohibition clearance (retest with certification)", "79", 6, "pass",  "p6v5", false},
//                {"With certification", "Part paid prohibition clearance (retest with certification)", "79", 7, "pass",  "p6v5", false},
//                {"With certification", "Part paid prohibition clearance (retest with certification)", "79", 8, "pass",  "p6v5", false},
//                {"With certification", "Part paid prohibition clearance (retest with certification)", "79", 9, "pass",  "p6v5", false},
//                {"With certification", "Part paid prohibition clearance (retest with certification)", "79", 10, "pass",  "p6v5", false},
//
                {"Annual test", "Annual test", "94", 2, "prs",  "aav2", false},
//                {"Annual test", "Annual test", "94", 3, "prs",  "aav3", false},
//                {"Annual test", "Annual test", "94", 4, "prs",  "aav4", false},
//                {"Annual test", "Annual test", "94", 5, "prs",  "aav5", false},
//                {"Annual test", "Annual test", "94", 6, "prs",  "aav5", false},
//                {"Annual test", "Annual test", "94", 7, "prs",  "aav5", false},
//                {"Annual test", "Annual test", "94", 8, "prs",  "aav5", false},
//                {"Annual test", "Annual test", "94", 9, "prs",  "aav5", false},
//                {"Annual test", "Annual test", "94", 10, "prs",  "aav5", false},
//                {"Paid", "Paid annual test retest", "53", 2, "prs",  "rpv2", false},
//                {"Paid", "Paid annual test retest", "53", 3, "prs",  "rpv3", false},
//                {"Paid", "Paid annual test retest", "53", 4, "prs",  "rpv4", false},
//                {"Paid", "Paid annual test retest", "53", 5, "prs",  "rpv5", false},
//                {"Paid", "Paid annual test retest", "53", 6, "prs",  "rpv5", false},
//                {"Paid", "Paid annual test retest", "53", 7, "prs",  "rpv5", false},
//                {"Paid", "Paid annual test retest", "53", 8, "prs",  "rpv5", false},
//                {"Paid", "Paid annual test retest", "53", 9, "prs",  "rpv5", false},
//                {"Paid", "Paid annual test retest", "53", 10, "prs",  "rpv5", false},
//                {"Part paid", "Part paid annual test retest", "54", 2, "prs",  "rsv2", false},
//                {"Part paid", "Part paid annual test retest", "54", 3, "prs",  "rsv3", false},
//                {"Part paid", "Part paid annual test retest", "54", 4, "prs",  "rsv4", false},
//                {"Part paid", "Part paid annual test retest", "54", 5, "prs",  "rsv5", false},
//                {"Part paid", "Part paid annual test retest", "54", 6, "prs",  "rsv5", false},
//                {"Part paid", "Part paid annual test retest", "54", 7, "prs",  "rsv5", false},
//                {"Part paid", "Part paid annual test retest", "54", 8, "prs",  "rsv5", false},
//                {"Part paid", "Part paid annual test retest", "54", 9, "prs",  "rsv5", false},
//                {"Part paid", "Part paid annual test retest", "54", 10, "prs",  "rsv5", false},
//                {"With certification", "Paid prohibition clearance (full inspection with certification)", "70", 2, "prs",  "p1v2", false},
//                {"With certification", "Paid prohibition clearance (full inspection with certification)", "70", 3, "prs",  "p1v3", false},
//                {"With certification", "Paid prohibition clearance (full inspection with certification)", "70", 4, "prs",  "p1v4", false},
//                {"With certification", "Paid prohibition clearance (full inspection with certification)", "70", 5, "prs",  "p1v5", false},
//                {"With certification", "Paid prohibition clearance (full inspection with certification)", "70", 6, "prs",  "p1v5", false},
//                {"With certification", "Paid prohibition clearance (full inspection with certification)", "70", 7, "prs",  "p1v5", false},
//                {"With certification", "Paid prohibition clearance (full inspection with certification)", "70", 8, "prs",  "p1v5", false},
//                {"With certification", "Paid prohibition clearance (full inspection with certification)", "70", 9, "prs",  "p1v5", false},
//                {"With certification", "Paid prohibition clearance (full inspection with certification)", "70", 10, "prs",  "p1v5", false},
//                {"With certification", "Paid prohibition clearance (retest with certification)", "76", 2, "prs",  "p3v2", false},
//                {"With certification", "Paid prohibition clearance (retest with certification)", "76", 3, "prs",  "p3v3", false},
//                {"With certification", "Paid prohibition clearance (retest with certification)", "76", 4, "prs",  "p3v4", false},
//                {"With certification", "Paid prohibition clearance (retest with certification)", "76", 5, "prs",  "p3v5", false},
//                {"With certification", "Paid prohibition clearance (retest with certification)", "76", 6, "prs",  "p3v5", false},
//                {"With certification", "Paid prohibition clearance (retest with certification)", "76", 7, "prs",  "p3v5", false},
//                {"With certification", "Paid prohibition clearance (retest with certification)", "76", 8, "prs",  "p3v5", false},
//                {"With certification", "Paid prohibition clearance (retest with certification)", "76", 9, "prs",  "p3v5", false},
//                {"With certification", "Paid prohibition clearance (retest with certification)", "76", 10, "prs",  "p3v5", false},
//                {"With certification", "Part paid prohibition clearance (retest with certification)", "79", 2, "prs",  "p6v2", false},
//                {"With certification", "Part paid prohibition clearance (retest with certification)", "79", 3, "prs",  "p6v3", false},
//                {"With certification", "Part paid prohibition clearance (retest with certification)", "79", 4, "prs",  "p6v4", false},
//                {"With certification", "Part paid prohibition clearance (retest with certification)", "79", 5, "prs",  "p6v5", false},
//                {"With certification", "Part paid prohibition clearance (retest with certification)", "79", 6, "prs",  "p6v5", false},
//                {"With certification", "Part paid prohibition clearance (retest with certification)", "79", 7, "prs",  "p6v5", false},
//                {"With certification", "Part paid prohibition clearance (retest with certification)", "79", 8, "prs",  "p6v5", false},
//                {"With certification", "Part paid prohibition clearance (retest with certification)", "79", 9, "prs",  "p6v5", false},
//                {"With certification", "Part paid prohibition clearance (retest with certification)", "79", 10, "prs",  "p6v5", false},
        });
    }

    private String name;
    private String testTypeName;
    private String testTypeId;
    private int noOfAxles;
    private String testResult;
    private String testCode;
    private boolean isAnnualWithCertificate;

    public PostTestResultsExpiryDateLogicHgvPreservation(String name, String testTypeName, String testTypeId, int noOfAxles, String testResult, String testCode, boolean isAnnualWithCertificate) {
        this.name = name;
        this.testTypeName = testTypeName;
        this.testTypeId = testTypeId;
        this.noOfAxles = noOfAxles;
        this.testResult = testResult;
        this.testCode = testCode;
        this.isAnnualWithCertificate = isAnnualWithCertificate;
    }

    @WithTag("expiry_dates")
    @Title("CVSB-8684 - TC1 - AC1 - HGV Preservation Test - NO Previous Expiry Date")
    @Test
    public void testResultsPreservationExpiryHgvNoPreviousExpiryDate() {

        // Read the base test result JSON.
        String testResultRecord = GenericData.readJsonValueFromFile("test-results_expiry_date_hgv_8684.json", "$");

        DateTime currentTime = DateTime.now().withZone(DateTimeZone.UTC);
        DateTime submittedTestStartTimestamp = currentTime.minusMinutes(15);
        DateTime submittedTestTypeStartTimestamp = currentTime.minusMinutes(10);
        DateTime submittedTypeEndTimestamp = currentTime.minus(5);
        DateTime submittedEndTimestamp = currentTime;

        // Create alteration to add one more tech record to in the request body
//        String regnDate = submittedEndTimestamp.toDateTime().minusYears(1).plusDays(dayOffset).toString("yyyy-MM-dd");
        String testStartTimestamp = submittedTestStartTimestamp.toInstant().toString();
        String testTypeStartTimestamp = submittedTestTypeStartTimestamp.toInstant().toString();
        String testTypeEndTimestamp = submittedTypeEndTimestamp.toInstant().toString();
        String testEndTimestamp = submittedTypeEndTimestamp.toInstant().toString();

        String randomVin = GenericData.generateRandomVin();
        String randomTestResultId = RandomStringUtils.randomNumeric(5);

        String testExpectedDate = currentTime.dayOfMonth().withMaximumValue().plusYears(1).toInstant().toString();

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

    @WithTag("expiry_dates")
    @Title("CVSB-8684 - TC1 - AC1 - HGV Annual test WITH PREVIOUS Expiry Date - Previous testExpiryDate = last day of previous month (EXPIRED)")
    @Test
    public void testResultsFirstTestExpiryHgvTodayMinusOneDay() {

        // Read the base INSERT test result JSON.
        String insertedTestResultRecord = GenericData.readJsonValueFromFile("test-results_insert_expiry_date_hgv_8684.json", "$");
        // Read the base POST test result JSON.
        String postTestResultRecord = GenericData.readJsonValueFromFile("test-results_post_expiry_date_hgv_8684.json", "$");

        DateTime currentTimestamp = DateTime.now().withZone(DateTimeZone.UTC);

        //
        String randomVin = GenericData.generateRandomVin();
        String randomTestResultId = RandomStringUtils.randomNumeric(5);

        // Create inserted record.
        DateTime insertedTestStartTimestamp = currentTimestamp.minusYears(1).minusMinutes(15);
        DateTime insertedTestTypeStartTimestamp = currentTimestamp.minusYears(1).minusMinutes(10);
        DateTime insertedLastUpdatedAt = currentTimestamp.minusYears(1).minusMinutes(5);

        DateTime insertedTestAnniversaryDate = currentTimestamp.minusMonths(2).minusDays(1);
        DateTime insertedTestExpiryDate = currentTimestamp.minusMonths(1).dayOfMonth().withMaximumValue();

        DateTime insertedCreatedAt = currentTimestamp.minusYears(1).minusMinutes(5);
        DateTime insertedTestTypeEndTimestamp = currentTimestamp.minusYears(1).minusMinutes(5);
        DateTime insertedTestEndTimestamp = currentTimestamp.minusYears(1);

        String insertableTestStartTimestamp = insertedTestStartTimestamp.toInstant().toString();
        String insertableTestTypeStartTimestamp = insertedTestTypeStartTimestamp.toInstant().toString();
        String insertableLastUpdatedAt = insertedLastUpdatedAt.toInstant().toString();
        String insertableTestAnniversaryDate = insertedTestAnniversaryDate.toInstant().toString();
        String insertableTestExpiryDate = insertedTestExpiryDate.toInstant().toString();
        String insertableCreatedAt = insertedCreatedAt.toInstant().toString();
        String insertableTestTypeEndTimestamp = insertedTestTypeEndTimestamp.toInstant().toString();
        String insertableTestEndTimestamp = insertedTestEndTimestamp.toInstant().toString();

        // Create alteration to add one more tech record to in the inserted data
        JsonPathAlteration alterationInsertVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationInsertTestResultId = new JsonPathAlteration("$.testResultId", RandomStringUtils.randomNumeric(5) , "", "REPLACE");
        JsonPathAlteration alterationInsertTestStartTimestamp = new JsonPathAlteration("$.testStartTimestamp", insertableTestStartTimestamp, "", "REPLACE");
        JsonPathAlteration alterationInsertTestTypeStartTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeStartTimestamp", insertableTestTypeStartTimestamp, "", "REPLACE");
        JsonPathAlteration alterationInsertLastUpdatedAt = new JsonPathAlteration("$.testTypes[0].lastUpdatedAt", insertableLastUpdatedAt, "", "REPLACE");
        JsonPathAlteration alterationInsertTestAnniversaryDate = new JsonPathAlteration("$.testTypes[0].testAnniversaryDate", insertableTestAnniversaryDate, "", "REPLACE");
        JsonPathAlteration alterationInsertTestExpiryDate = new JsonPathAlteration("$.testTypes[0].testExpiryDate", insertableTestExpiryDate, "", "REPLACE");
        JsonPathAlteration alterationInsertCreatedAt = new JsonPathAlteration("$.testTypes[0].createdAt", insertableCreatedAt, "", "REPLACE");
        JsonPathAlteration alterationInsertTestTypeEndTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeEndTimestamp", insertableTestTypeEndTimestamp, "", "REPLACE");
        JsonPathAlteration alterationInsertTestEndTimestamp = new JsonPathAlteration("$.testEndTimestamp", insertableTestEndTimestamp, "", "REPLACE");

        // Collate the list of alterations for the inserted record.
        List<JsonPathAlteration> insertAlterations = new ArrayList<>(Arrays.asList(
                alterationInsertVin,
                alterationInsertTestResultId,
                alterationInsertTestStartTimestamp,
                alterationInsertTestTypeStartTimestamp,
                alterationInsertLastUpdatedAt,
                alterationInsertTestAnniversaryDate,
                alterationInsertTestExpiryDate,
                alterationInsertCreatedAt,
                alterationInsertTestTypeEndTimestamp,
                alterationInsertTestEndTimestamp
        ));

        if(isAnnualWithCertificate){
            insertAlterations.add(new JsonPathAlteration("$.testTypes[0]", "Annual With Certificate", "testTypeClassification", "ADD_FIELD"));
        }

        // Insert the altered record
        String alteredJson = GenericData.applyJsonAlterations(insertedTestResultRecord, insertAlterations);
        testResultsSteps.insertRecordInDynamo(alteredJson, "test-results");

//        System.out.println(" \n######################## INSERTED ########################\n\n");
//        System.out.println(new GsonBuilder().setPrettyPrinting().create().toJson(new JsonParser().parse(alteredJson).getAsJsonObject()));
//        System.out.println(" \n########################    END   ########################\n\n");


        // Create submitted
        DateTime submittedTestStartTimestamp = currentTimestamp.minusMinutes(15);
        DateTime submittedTestTypeStartTimestamp = currentTimestamp.plusMinutes(10);
        DateTime submittedTypeEndTimestamp = currentTimestamp.plusMinutes(5);
        DateTime submittedEndTimestamp = currentTimestamp;

        String testStartTimestamp = submittedTestStartTimestamp.toInstant().toString();
        String testTypeStartTimestamp = submittedTestTypeStartTimestamp.toInstant().toString();
        String testTypeEndTimestamp = submittedTypeEndTimestamp.toInstant().toString();
        String testEndTimestamp = submittedEndTimestamp.toInstant().toString();

        String testExpectedDate = submittedTypeEndTimestamp.dayOfMonth().withMaximumValue().plusYears(1).toInstant().toString();


        // Create alteration to add one more tech record to in the request body
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
        testResultsSteps.postVehicleTestResultsWithAlterations(postTestResultRecord, alterations);

//        System.out.println(" \n######################## POSTED ########################\n\n");
//        System.out.println(new GsonBuilder().setPrettyPrinting().create().toJson(new JsonParser().parse(GenericData.applyJsonAlterations(postTestResultRecord, alterations)).getAsJsonObject()));
//        System.out.println(" \n########################   END  ########################\n\n");

        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");

        // Retrieve the created record, and verify that the fields are present.
        testResultsSteps.getTestResultsBetweenDate(randomVin, submittedTestStartTimestamp.minusMinutes(10).toInstant().toString() , submittedEndTimestamp.plusMinutes(10).toInstant().toString());
        testResultsSteps.statusCodeShouldBe(200);

        // Verify testCode field has the expected value
        testResultsSteps.valueForFieldInPathShouldBe("[0].testTypes[0].testCode", testCode);

        // Verify testExpiryDate field has the expected value
        testResultsSteps.valueForFieldInPathShouldStartWith("[0].testTypes[0].testExpiryDate", testExpectedDate.substring(0,10));

//        // Verify testAnniversaryDate field has the expected value
//        testResultsSteps.valueForFieldInPathShouldStartWith("[0].testTypes[0].testAnniversaryDate", testExpectedDate.substring(0,10));

    }


    @WithTag("expiry_dates")
    @Title("CVSB-8684 - TC1 - AC1 - HGV Annual test WITH PREVIOUS Expiry Date - Previous testExpiryDate = end of this month (EXPIRES THIS MONTH)")
    @Test
    public void testResultsFirstTestExpiryHgvToday() {

        // Read the base INSERT test result JSON.
        String insertedTestResultRecord = GenericData.readJsonValueFromFile("test-results_insert_expiry_date_hgv_8684.json", "$");
        // Read the base POST test result JSON.
        String postTestResultRecord = GenericData.readJsonValueFromFile("test-results_post_expiry_date_hgv_8684.json", "$");

        DateTime currentTimestamp = DateTime.now().withZone(DateTimeZone.UTC);

        //
        String randomVin = GenericData.generateRandomVin();
        String randomTestResultId = RandomStringUtils.randomNumeric(5);

        // Create inserted record.
        DateTime insertedTestStartTimestamp = currentTimestamp.minusYears(1).minusMinutes(15);
        DateTime insertedTestTypeStartTimestamp = currentTimestamp.minusYears(1).minusMinutes(10);
        DateTime insertedLastUpdatedAt = currentTimestamp.minusYears(1).minusMinutes(5);

        DateTime insertedTestAnniversaryDate = currentTimestamp.minusMonths(2).minusDays(1);
        DateTime insertedTestExpiryDate = currentTimestamp.dayOfMonth().withMaximumValue();

        DateTime insertedCreatedAt = currentTimestamp.minusYears(1).minusMinutes(5);
        DateTime insertedTestTypeEndTimestamp = currentTimestamp.minusYears(1).minusMinutes(5);
        DateTime insertedTestEndTimestamp = currentTimestamp.minusYears(1);

        String insertableTestStartTimestamp = insertedTestStartTimestamp.toInstant().toString();
        String insertableTestTypeStartTimestamp = insertedTestTypeStartTimestamp.toInstant().toString();
        String insertableLastUpdatedAt = insertedLastUpdatedAt.toInstant().toString();
        String insertableTestAnniversaryDate = insertedTestAnniversaryDate.toInstant().toString();
        String insertableTestExpiryDate = insertedTestExpiryDate.toInstant().toString();
        String insertableCreatedAt = insertedCreatedAt.toInstant().toString();
        String insertableTestTypeEndTimestamp = insertedTestTypeEndTimestamp.toInstant().toString();
        String insertableTestEndTimestamp = insertedTestEndTimestamp.toInstant().toString();

        // Create alteration to add one more tech record to in the inserted data
        JsonPathAlteration alterationInsertVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationInsertTestResultId = new JsonPathAlteration("$.testResultId", RandomStringUtils.randomNumeric(5) , "", "REPLACE");
        JsonPathAlteration alterationInsertTestStartTimestamp = new JsonPathAlteration("$.testStartTimestamp", insertableTestStartTimestamp, "", "REPLACE");
        JsonPathAlteration alterationInsertTestTypeStartTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeStartTimestamp", insertableTestTypeStartTimestamp, "", "REPLACE");
        JsonPathAlteration alterationInsertLastUpdatedAt = new JsonPathAlteration("$.testTypes[0].lastUpdatedAt", insertableLastUpdatedAt, "", "REPLACE");
        JsonPathAlteration alterationInsertTestAnniversaryDate = new JsonPathAlteration("$.testTypes[0].testAnniversaryDate", insertableTestAnniversaryDate, "", "REPLACE");
        JsonPathAlteration alterationInsertTestExpiryDate = new JsonPathAlteration("$.testTypes[0].testExpiryDate", insertableTestExpiryDate, "", "REPLACE");
        JsonPathAlteration alterationInsertCreatedAt = new JsonPathAlteration("$.testTypes[0].createdAt", insertableCreatedAt, "", "REPLACE");
        JsonPathAlteration alterationInsertTestTypeEndTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeEndTimestamp", insertableTestTypeEndTimestamp, "", "REPLACE");
        JsonPathAlteration alterationInsertTestEndTimestamp = new JsonPathAlteration("$.testEndTimestamp", insertableTestEndTimestamp, "", "REPLACE");

        // Collate the list of alterations for the inserted record.
        List<JsonPathAlteration> insertAlterations = new ArrayList<>(Arrays.asList(
                alterationInsertVin,
                alterationInsertTestResultId,
                alterationInsertTestStartTimestamp,
                alterationInsertTestTypeStartTimestamp,
                alterationInsertLastUpdatedAt,
                alterationInsertTestAnniversaryDate,
                alterationInsertTestExpiryDate,
                alterationInsertCreatedAt,
                alterationInsertTestTypeEndTimestamp,
                alterationInsertTestEndTimestamp
        ));

        if(isAnnualWithCertificate){
            insertAlterations.add(new JsonPathAlteration("$.testTypes[0]", "Annual With Certificate", "testTypeClassification", "ADD_FIELD"));
        }

        // Insert the altered record
        String alteredJson = GenericData.applyJsonAlterations(insertedTestResultRecord, insertAlterations);
        testResultsSteps.insertRecordInDynamo(alteredJson, "test-results");

//        System.out.println(" \n######################## INSERTED ########################\n\n");
//        System.out.println(new GsonBuilder().setPrettyPrinting().create().toJson(new JsonParser().parse(alteredJson).getAsJsonObject()));
//        System.out.println(" \n########################    END   ########################\n\n");


        // Create submitted
        DateTime submittedTestStartTimestamp = currentTimestamp.minusMinutes(15);
        DateTime submittedTestTypeStartTimestamp = currentTimestamp.plusMinutes(10);
        DateTime submittedTypeEndTimestamp = currentTimestamp.plusMinutes(5);
        DateTime submittedEndTimestamp = currentTimestamp;

        String testStartTimestamp = submittedTestStartTimestamp.toInstant().toString();
        String testTypeStartTimestamp = submittedTestTypeStartTimestamp.toInstant().toString();
        String testTypeEndTimestamp = submittedTypeEndTimestamp.toInstant().toString();
        String testEndTimestamp = submittedEndTimestamp.toInstant().toString();

        String testExpectedDate = currentTimestamp.dayOfMonth().withMaximumValue().plusYears(1).toInstant().toString();


        // Create alteration to add one more tech record to in the request body
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
        testResultsSteps.postVehicleTestResultsWithAlterations(postTestResultRecord, alterations);

//        System.out.println(" \n######################## POSTED ########################\n\n");
//        System.out.println(new GsonBuilder().setPrettyPrinting().create().toJson(new JsonParser().parse(GenericData.applyJsonAlterations(postTestResultRecord, alterations)).getAsJsonObject()));
//        System.out.println(" \n########################   END  ########################\n\n");

        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");

        // Retrieve the created record, and verify that the fields are present.
        testResultsSteps.getTestResultsBetweenDate(randomVin, submittedTestStartTimestamp.minusMinutes(10).toInstant().toString() , submittedEndTimestamp.plusMinutes(10).toInstant().toString());
        testResultsSteps.statusCodeShouldBe(200);

        // Verify testCode field has the expected value
        testResultsSteps.valueForFieldInPathShouldBe("[0].testTypes[0].testCode", testCode);

        // Verify testExpiryDate field has the expected value
        testResultsSteps.valueForFieldInPathShouldStartWith("[0].testTypes[0].testExpiryDate", testExpectedDate.substring(0,10));

//        // Verify testAnniversaryDate field has the expected value
//        testResultsSteps.valueForFieldInPathShouldStartWith("[0].testTypes[0].testAnniversaryDate", testExpectedDate.substring(0,10));

    }

    @WithTag("expiry_dates")
    @Title("CVSB-8684 - TC1 - AC1 - HGV Annual test WITH PREVIOUS Expiry Date - Previous testExpiryDate = next month (EXPIRES NEXT MONTH)")
    @Test
    public void testResultsFirstTestExpiryHgvNextMonth() {

        // Read the base INSERT test result JSON.
        String insertedTestResultRecord = GenericData.readJsonValueFromFile("test-results_insert_expiry_date_hgv_8684.json", "$");
        // Read the base POST test result JSON.
        String postTestResultRecord = GenericData.readJsonValueFromFile("test-results_post_expiry_date_hgv_8684.json", "$");

        DateTime currentTimestamp = DateTime.now().withZone(DateTimeZone.UTC);

        //
        String randomVin = GenericData.generateRandomVin();
        String randomTestResultId = RandomStringUtils.randomNumeric(5);

        // Create inserted record.
        DateTime insertedTestStartTimestamp = currentTimestamp.minusYears(1).minusMinutes(15);
        DateTime insertedTestTypeStartTimestamp = currentTimestamp.minusYears(1).minusMinutes(10);
        DateTime insertedLastUpdatedAt = currentTimestamp.minusYears(1).minusMinutes(5);

        DateTime insertedTestAnniversaryDate = currentTimestamp.minusMonths(2).minusDays(1);
        DateTime insertedTestExpiryDate = currentTimestamp.plusMonths(1).dayOfMonth().withMaximumValue();

        DateTime insertedCreatedAt = currentTimestamp.minusYears(1).minusMinutes(5);
        DateTime insertedTestTypeEndTimestamp = currentTimestamp.minusYears(1).minusMinutes(5);
        DateTime insertedTestEndTimestamp = currentTimestamp.minusYears(1);

        String insertableTestStartTimestamp = insertedTestStartTimestamp.toInstant().toString();
        String insertableTestTypeStartTimestamp = insertedTestTypeStartTimestamp.toInstant().toString();
        String insertableLastUpdatedAt = insertedLastUpdatedAt.toInstant().toString();
        String insertableTestAnniversaryDate = insertedTestAnniversaryDate.toInstant().toString();
        String insertableTestExpiryDate = insertedTestExpiryDate.toInstant().toString();
        String insertableCreatedAt = insertedCreatedAt.toInstant().toString();
        String insertableTestTypeEndTimestamp = insertedTestTypeEndTimestamp.toInstant().toString();
        String insertableTestEndTimestamp = insertedTestEndTimestamp.toInstant().toString();

        // Create alteration to add one more tech record to in the inserted data
        JsonPathAlteration alterationInsertVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationInsertTestResultId = new JsonPathAlteration("$.testResultId", RandomStringUtils.randomNumeric(5) , "", "REPLACE");
        JsonPathAlteration alterationInsertTestStartTimestamp = new JsonPathAlteration("$.testStartTimestamp", insertableTestStartTimestamp, "", "REPLACE");
        JsonPathAlteration alterationInsertTestTypeStartTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeStartTimestamp", insertableTestTypeStartTimestamp, "", "REPLACE");
        JsonPathAlteration alterationInsertLastUpdatedAt = new JsonPathAlteration("$.testTypes[0].lastUpdatedAt", insertableLastUpdatedAt, "", "REPLACE");
        JsonPathAlteration alterationInsertTestAnniversaryDate = new JsonPathAlteration("$.testTypes[0].testAnniversaryDate", insertableTestAnniversaryDate, "", "REPLACE");
        JsonPathAlteration alterationInsertTestExpiryDate = new JsonPathAlteration("$.testTypes[0].testExpiryDate", insertableTestExpiryDate, "", "REPLACE");
        JsonPathAlteration alterationInsertCreatedAt = new JsonPathAlteration("$.testTypes[0].createdAt", insertableCreatedAt, "", "REPLACE");
        JsonPathAlteration alterationInsertTestTypeEndTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeEndTimestamp", insertableTestTypeEndTimestamp, "", "REPLACE");
        JsonPathAlteration alterationInsertTestEndTimestamp = new JsonPathAlteration("$.testEndTimestamp", insertableTestEndTimestamp, "", "REPLACE");

        // Collate the list of alterations for the inserted record.
        List<JsonPathAlteration> insertAlterations = new ArrayList<>(Arrays.asList(
                alterationInsertVin,
                alterationInsertTestResultId,
                alterationInsertTestStartTimestamp,
                alterationInsertTestTypeStartTimestamp,
                alterationInsertLastUpdatedAt,
                alterationInsertTestAnniversaryDate,
                alterationInsertTestExpiryDate,
                alterationInsertCreatedAt,
                alterationInsertTestTypeEndTimestamp,
                alterationInsertTestEndTimestamp
        ));

        if(isAnnualWithCertificate){
            insertAlterations.add(new JsonPathAlteration("$.testTypes[0]", "Annual With Certificate", "testTypeClassification", "ADD_FIELD"));
        }

        // Insert the altered record
        String alteredJson = GenericData.applyJsonAlterations(insertedTestResultRecord, insertAlterations);
        testResultsSteps.insertRecordInDynamo(alteredJson, "test-results");

//        System.out.println(" \n######################## INSERTED ########################\n\n");
//        System.out.println(new GsonBuilder().setPrettyPrinting().create().toJson(new JsonParser().parse(alteredJson).getAsJsonObject()));
//        System.out.println(" \n########################    END   ########################\n\n");


        // Create submitted
        DateTime submittedTestStartTimestamp = currentTimestamp.minusMinutes(15);
        DateTime submittedTestTypeStartTimestamp = currentTimestamp.plusMinutes(10);
        DateTime submittedTypeEndTimestamp = currentTimestamp.plusMinutes(5);
        DateTime submittedEndTimestamp = currentTimestamp;

        String testStartTimestamp = submittedTestStartTimestamp.toInstant().toString();
        String testTypeStartTimestamp = submittedTestTypeStartTimestamp.toInstant().toString();
        String testTypeEndTimestamp = submittedTypeEndTimestamp.toInstant().toString();
        String testEndTimestamp = submittedEndTimestamp.toInstant().toString();

        String testExpectedDate = currentTimestamp.plusMonths(1).dayOfMonth().withMaximumValue().plusYears(1).toInstant().toString();


        // Create alteration to add one more tech record to in the request body
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
        testResultsSteps.postVehicleTestResultsWithAlterations(postTestResultRecord, alterations);

//        System.out.println(" \n######################## POSTED ########################\n\n");
//        System.out.println(new GsonBuilder().setPrettyPrinting().create().toJson(new JsonParser().parse(GenericData.applyJsonAlterations(postTestResultRecord, alterations)).getAsJsonObject()));
//        System.out.println(" \n########################   END  ########################\n\n");

        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");

        // Retrieve the created record, and verify that the fields are present.
        testResultsSteps.getTestResultsBetweenDate(randomVin, submittedTestStartTimestamp.minusMinutes(10).toInstant().toString() , submittedEndTimestamp.plusMinutes(10).toInstant().toString());
        testResultsSteps.statusCodeShouldBe(200);

        // Verify testCode field has the expected value
        testResultsSteps.valueForFieldInPathShouldBe("[0].testTypes[0].testCode", testCode);

        // Verify testExpiryDate field has the expected value
        testResultsSteps.valueForFieldInPathShouldStartWith("[0].testTypes[0].testExpiryDate", testExpectedDate.substring(0,10));

//        // Verify testAnniversaryDate field has the expected value
//        testResultsSteps.valueForFieldInPathShouldStartWith("[0].testTypes[0].testAnniversaryDate", testExpectedDate.substring(0,10));

    }

    @WithTag("expiry_dates")
    @Title("CVSB-8684 - TC1 - AC1 - HGV Annual test WITH PREVIOUS Expiry Date - Previous testExpiryDate = two months ahead (EARLY FOR TEST)")
    @Test
    public void testResultsFirstTestExpiryHgvTwoMonthsAhead() {

        // Read the base INSERT test result JSON.
        String insertedTestResultRecord = GenericData.readJsonValueFromFile("test-results_insert_expiry_date_hgv_8684.json", "$");
        // Read the base POST test result JSON.
        String postTestResultRecord = GenericData.readJsonValueFromFile("test-results_post_expiry_date_hgv_8684.json", "$");

        DateTime currentTimestamp = DateTime.now().withZone(DateTimeZone.UTC);

        //
        String randomVin = GenericData.generateRandomVin();
        String randomTestResultId = RandomStringUtils.randomNumeric(5);

        // Create inserted record.
        DateTime insertedTestStartTimestamp = currentTimestamp.minusYears(1).minusMinutes(15);
        DateTime insertedTestTypeStartTimestamp = currentTimestamp.minusYears(1).minusMinutes(10);
        DateTime insertedLastUpdatedAt = currentTimestamp.minusYears(1).minusMinutes(5);

        DateTime insertedTestAnniversaryDate = currentTimestamp.minusMonths(2).minusDays(1);
        DateTime insertedTestExpiryDate = currentTimestamp.plusMonths(2).dayOfMonth().withMaximumValue();

        DateTime insertedCreatedAt = currentTimestamp.minusYears(1).minusMinutes(5);
        DateTime insertedTestTypeEndTimestamp = currentTimestamp.minusYears(1).minusMinutes(5);
        DateTime insertedTestEndTimestamp = currentTimestamp.minusYears(1);

        String insertableTestStartTimestamp = insertedTestStartTimestamp.toInstant().toString();
        String insertableTestTypeStartTimestamp = insertedTestTypeStartTimestamp.toInstant().toString();
        String insertableLastUpdatedAt = insertedLastUpdatedAt.toInstant().toString();
        String insertableTestAnniversaryDate = insertedTestAnniversaryDate.toInstant().toString();
        String insertableTestExpiryDate = insertedTestExpiryDate.toInstant().toString();
        String insertableCreatedAt = insertedCreatedAt.toInstant().toString();
        String insertableTestTypeEndTimestamp = insertedTestTypeEndTimestamp.toInstant().toString();
        String insertableTestEndTimestamp = insertedTestEndTimestamp.toInstant().toString();

        // Create alteration to add one more tech record to in the inserted data
        JsonPathAlteration alterationInsertVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationInsertTestResultId = new JsonPathAlteration("$.testResultId", RandomStringUtils.randomNumeric(5) , "", "REPLACE");
        JsonPathAlteration alterationInsertTestStartTimestamp = new JsonPathAlteration("$.testStartTimestamp", insertableTestStartTimestamp, "", "REPLACE");
        JsonPathAlteration alterationInsertTestTypeStartTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeStartTimestamp", insertableTestTypeStartTimestamp, "", "REPLACE");
        JsonPathAlteration alterationInsertLastUpdatedAt = new JsonPathAlteration("$.testTypes[0].lastUpdatedAt", insertableLastUpdatedAt, "", "REPLACE");
        JsonPathAlteration alterationInsertTestAnniversaryDate = new JsonPathAlteration("$.testTypes[0].testAnniversaryDate", insertableTestAnniversaryDate, "", "REPLACE");
        JsonPathAlteration alterationInsertTestExpiryDate = new JsonPathAlteration("$.testTypes[0].testExpiryDate", insertableTestExpiryDate, "", "REPLACE");
        JsonPathAlteration alterationInsertCreatedAt = new JsonPathAlteration("$.testTypes[0].createdAt", insertableCreatedAt, "", "REPLACE");
        JsonPathAlteration alterationInsertTestTypeEndTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeEndTimestamp", insertableTestTypeEndTimestamp, "", "REPLACE");
        JsonPathAlteration alterationInsertTestEndTimestamp = new JsonPathAlteration("$.testEndTimestamp", insertableTestEndTimestamp, "", "REPLACE");

        // Collate the list of alterations for the inserted record.
        List<JsonPathAlteration> insertAlterations = new ArrayList<>(Arrays.asList(
                alterationInsertVin,
                alterationInsertTestResultId,
                alterationInsertTestStartTimestamp,
                alterationInsertTestTypeStartTimestamp,
                alterationInsertLastUpdatedAt,
                alterationInsertTestAnniversaryDate,
                alterationInsertTestExpiryDate,
                alterationInsertCreatedAt,
                alterationInsertTestTypeEndTimestamp,
                alterationInsertTestEndTimestamp
        ));

        if(isAnnualWithCertificate){
            insertAlterations.add(new JsonPathAlteration("$.testTypes[0]", "Annual With Certificate", "testTypeClassification", "ADD_FIELD"));
        }

        // Insert the altered record
        String alteredJson = GenericData.applyJsonAlterations(insertedTestResultRecord, insertAlterations);
        testResultsSteps.insertRecordInDynamo(alteredJson, "test-results");

//        System.out.println(" \n######################## INSERTED ########################\n\n");
//        System.out.println(new GsonBuilder().setPrettyPrinting().create().toJson(new JsonParser().parse(alteredJson).getAsJsonObject()));
//        System.out.println(" \n########################    END   ########################\n\n");


        // Create submitted
        DateTime submittedTestStartTimestamp = currentTimestamp.minusMinutes(15);
        DateTime submittedTestTypeStartTimestamp = currentTimestamp.plusMinutes(10);
        DateTime submittedTypeEndTimestamp = currentTimestamp.plusMinutes(5);
        DateTime submittedEndTimestamp = currentTimestamp;

        String testStartTimestamp = submittedTestStartTimestamp.toInstant().toString();
        String testTypeStartTimestamp = submittedTestTypeStartTimestamp.toInstant().toString();
        String testTypeEndTimestamp = submittedTypeEndTimestamp.toInstant().toString();
        String testEndTimestamp = submittedEndTimestamp.toInstant().toString();


        String testExpectedDate = currentTimestamp.dayOfMonth().withMaximumValue().plusYears(1).toInstant().toString();


        // Create alteration to add one more tech record to in the request body
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
        testResultsSteps.postVehicleTestResultsWithAlterations(postTestResultRecord, alterations);

//        System.out.println(" \n######################## POSTED ########################\n\n");
//        System.out.println(new GsonBuilder().setPrettyPrinting().create().toJson(new JsonParser().parse(GenericData.applyJsonAlterations(postTestResultRecord, alterations)).getAsJsonObject()));
//        System.out.println(" \n########################   END  ########################\n\n");

        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");

        // Retrieve the created record, and verify that the fields are present.
        testResultsSteps.getTestResultsBetweenDate(randomVin, submittedTestStartTimestamp.minusMinutes(10).toInstant().toString() , submittedEndTimestamp.plusMinutes(10).toInstant().toString());
        testResultsSteps.statusCodeShouldBe(200);

        // Verify testCode field has the expected value
        testResultsSteps.valueForFieldInPathShouldBe("[0].testTypes[0].testCode", testCode);

        // Verify testExpiryDate field has the expected value
        testResultsSteps.valueForFieldInPathShouldStartWith("[0].testTypes[0].testExpiryDate", testExpectedDate.substring(0,10));

//        // Verify testAnniversaryDate field has the expected value
//        testResultsSteps.valueForFieldInPathShouldStartWith("[0].testTypes[0].testAnniversaryDate", testExpectedDate.substring(0,10));

    }

}
