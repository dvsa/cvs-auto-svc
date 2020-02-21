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

@RunWith(SerenityParameterizedRunner.class)
public class PostTestResultsExpiryDateLogicTrlFirstTest {

    @Steps
    TestResultsSteps testResultsSteps;

    @TestData
    public static Collection<Object[]> testData(){
        return Arrays.asList(new Object[][]{
                {"First test", "First test", "41", 1, "pass", "fft1"},
//                {"First test", "First test", "95", "centre axle drawbar", 2, "pass", 1, "fft2"},
//                {"First test", "First test", "95", "centre axle drawbar", 3, "pass", 1, "fft3"},
//                {"First test", "First test", "95", "centre axle drawbar", 4, "pass", 1, "fft4"},
//                {"First test", "First test", "95", "centre axle drawbar", 5, "pass", 1, "fft5"},
//                {"First test", "First test", "95", "centre axle drawbar", 6, "pass", 1, "fft5"},
//                {"First test", "First test", "95", "centre axle drawbar", 7, "pass", 1, "fft5"},
//                {"First test", "First test", "95", "centre axle drawbar", 8, "pass", 1, "fft5"},
//                {"First test", "First test", "95", "centre axle drawbar", 9, "pass", 1, "fft5"},
//                {"First test", "First test", "95", "centre axle drawbar", 10, "pass", 1, "fft5"},
//                {"Paid", "Paid first test retest", "103", "centre axle drawbar", 1, "pass", 1, "rgt1"},
//                {"Paid", "Paid first test retest", "65", "centre axle drawbar", 2, "pass", 1, "rgt2"},
//                {"Paid", "Paid first test retest", "65", "centre axle drawbar", 3, "pass", 1, "rgt3"},
//                {"Paid", "Paid first test retest", "65", "centre axle drawbar", 4, "pass", 1, "rgt4"},
//                {"Paid", "Paid first test retest", "65", "centre axle drawbar", 5, "pass", 1, "rgt5"},
//                {"Paid", "Paid first test retest", "65", "centre axle drawbar", 6, "pass", 1, "rgt5"},
//                {"Paid", "Paid first test retest", "65", "centre axle drawbar", 7, "pass", 1, "rgt5"},
//                {"Paid", "Paid first test retest", "65", "centre axle drawbar", 8, "pass", 1, "rgt5"},
//                {"Paid", "Paid first test retest", "65", "centre axle drawbar", 9, "pass", 1, "rgt5"},
//                {"Paid", "Paid first test retest", "65", "centre axle drawbar", 10, "pass", 1, "rgt5"},
//                {"Part paid", "Part paid first test retest", "104", "centre axle drawbar", 1, "pass", 1, "rit1"},
//                {"Part paid", "Part paid first test retest", "66", "centre axle drawbar", 2, "pass", 1, "rit2"},
//                {"Part paid", "Part paid first test retest", "66", "centre axle drawbar", 3, "pass", 1, "rit3"},
//                {"Part paid", "Part paid first test retest", "66", "centre axle drawbar", 4, "pass", 1, "rit4"},
//                {"Part paid", "Part paid first test retest", "66", "centre axle drawbar", 5, "pass", 1, "rit5"},
//                {"Part paid", "Part paid first test retest", "66", "centre axle drawbar", 6, "pass", 1, "rit5"},
//                {"Part paid", "Part paid first test retest", "66", "centre axle drawbar", 7, "pass", 1, "rit5"},
//                {"Part paid", "Part paid first test retest", "66", "centre axle drawbar", 8, "pass", 1, "rit5"},
//                {"Part paid", "Part paid first test retest", "66", "centre axle drawbar", 9, "pass", 1, "rit5"},
//                {"Part paid", "Part paid first test retest", "66", "centre axle drawbar", 10, "pass", 1, "rit5"},
//                {"Free", "Free first test retest", "67", "centre axle drawbar", 3, "pass", 1, "rht"},
//                {"Full Inspection/ Fee - With certification", "Paid prohibition clearance on first test (full inspection with certification)", "119", "centre axle drawbar", 1, "pass", 1, "p7t1"},
//                {"Full Inspection/ Fee - With certification", "Paid prohibition clearance on first test (full inspection with certification)", "82", "centre axle drawbar", 2, "pass", 1, "p7t2"},
//                {"Full Inspection/ Fee - With certification", "Paid prohibition clearance on first test (full inspection with certification)", "82", "centre axle drawbar", 3, "pass", 1, "p7t3"},
//                {"Full Inspection/ Fee - With certification", "Paid prohibition clearance on first test (full inspection with certification)", "82", "centre axle drawbar", 4, "pass", 1, "p7t4"},
//                {"Full Inspection/ Fee - With certification", "Paid prohibition clearance on first test (full inspection with certification)", "82", "centre axle drawbar", 5, "pass", 1, "p7t5"},
//                {"Full Inspection/ Fee - With certification", "Paid prohibition clearance on first test (full inspection with certification)", "82", "centre axle drawbar", 6, "pass", 1, "p7t5"},
//                {"Full Inspection/ Fee - With certification", "Paid prohibition clearance on first test (full inspection with certification)", "82", "centre axle drawbar", 7, "pass", 1, "p7t5"},
//                {"Full Inspection/ Fee - With certification", "Paid prohibition clearance on first test (full inspection with certification)", "82", "centre axle drawbar", 8, "pass", 1, "p7t5"},
//                {"Full Inspection/ Fee - With certification", "Paid prohibition clearance on first test (full inspection with certification)", "82", "centre axle drawbar", 9, "pass", 1, "p7t5"},
//                {"Full Inspection/ Fee - With certification", "Paid prohibition clearance on first test (full inspection with certification)", "82", "centre axle drawbar", 10, "pass", 1, "p7t5"},
//                {"Retest - Paid", "Paid retest prohibition clearance on first test", "120", "centre axle drawbar", 1, "pass", 1, "p4t1"},
//                {"Retest - Paid", "Paid retest prohibition clearance on first test", "83", "centre axle drawbar", 2, "pass", 1, "p4t2"},
//                {"Retest - Paid", "Paid retest prohibition clearance on first test", "83", "centre axle drawbar", 3, "pass", 1, "p4t3"},
//                {"Retest - Paid", "Paid retest prohibition clearance on first test", "83", "centre axle drawbar", 4, "pass", 1, "p4t4"},
//                {"Retest - Paid", "Paid retest prohibition clearance on first test", "83", "centre axle drawbar", 5, "pass", 1, "p4t5"},
//                {"Retest - Paid", "Paid retest prohibition clearance on first test", "83", "centre axle drawbar", 6, "pass", 1, "p4t5"},
//                {"Retest - Paid", "Paid retest prohibition clearance on first test", "83", "centre axle drawbar", 7, "pass", 1, "p4t5"},
//                {"Retest - Paid", "Paid retest prohibition clearance on first test", "83", "centre axle drawbar", 8, "pass", 1, "p4t5"},
//                {"Retest - Paid", "Paid retest prohibition clearance on first test", "83", "centre axle drawbar", 9, "pass", 1, "p4t5"},
//                {"Retest - Paid", "Paid retest prohibition clearance on first test", "83", "centre axle drawbar", 10, "pass", 1, "p4t5"},
//                {"Annual test", "Annual test", "40", "centre axle drawbar", 1, "pass", 1, "aat1"},
//                {"Annual test", "Annual test", "94", "centre axle drawbar", 2, "pass", 1, "aat2"},
//                {"Annual test", "Annual test", "94", "centre axle drawbar", 3, "pass", 1, "aat3"},
//                {"Annual test", "Annual test", "94", "centre axle drawbar", 4, "pass", 1, "aat4"},
//                {"Annual test", "Annual test", "94", "centre axle drawbar", 5, "pass", 1, "aat5"},
//                {"Annual test", "Annual test", "94", "centre axle drawbar", 6, "pass", 1, "aat5"},
//                {"Annual test", "Annual test", "94", "centre axle drawbar", 7, "pass", 1, "aat5"},
//                {"Annual test", "Annual test", "94", "centre axle drawbar", 8, "pass", 1, "aat5"},
//                {"Annual test", "Annual test", "94", "centre axle drawbar", 9, "pass", 1, "aat5"},
//                {"Annual test", "Annual test", "94", "centre axle drawbar", 10, "pass", 1, "aat5"},
//                {"Paid", "Paid annual test retest", "98", "centre axle drawbar", 1, "pass", 1, "rpt1"},
//                {"Paid", "Paid annual test retest", "53", "centre axle drawbar", 2, "pass", 1, "rpt2"},
//                {"Paid", "Paid annual test retest", "53", "centre axle drawbar", 3, "pass", 1, "rpt3"},
//                {"Paid", "Paid annual test retest", "53", "centre axle drawbar", 4, "pass", 1, "rpt4"},
//                {"Paid", "Paid annual test retest", "53", "centre axle drawbar", 5, "pass", 1, "rpt5"},
//                {"Paid", "Paid annual test retest", "53", "centre axle drawbar", 6, "pass", 1, "rpt5"},
//                {"Paid", "Paid annual test retest", "53", "centre axle drawbar", 7, "pass", 1, "rpt5"},
//                {"Paid", "Paid annual test retest", "53", "centre axle drawbar", 8, "pass", 1, "rpt5"},
//                {"Paid", "Paid annual test retest", "53", "centre axle drawbar", 9, "pass", 1, "rpt5"},
//                {"Paid", "Paid annual test retest", "53", "centre axle drawbar", 10, "pass", 1, "rpt5"},
//                {"Part paid", "Part paid annual test retest", "99", "centre axle drawbar", 1, "pass", 1, "rst1"},
//                {"Part paid", "Part paid annual test retest", "54", "centre axle drawbar", 2, "pass", 1, "rst2"},
//                {"Part paid", "Part paid annual test retest", "54", "centre axle drawbar", 3, "pass", 1, "rst3"},
//                {"Part paid", "Part paid annual test retest", "54", "centre axle drawbar", 4, "pass", 1, "rst4"},
//                {"Part paid", "Part paid annual test retest", "54", "centre axle drawbar", 5, "pass", 1, "rst5"},
//                {"Part paid", "Part paid annual test retest", "54", "centre axle drawbar", 6, "pass", 1, "rst5"},
//                {"Part paid", "Part paid annual test retest", "54", "centre axle drawbar", 7, "pass", 1, "rst5"},
//                {"Part paid", "Part paid annual test retest", "54", "centre axle drawbar", 8, "pass", 1, "rst5"},
//                {"Part paid", "Part paid annual test retest", "54", "centre axle drawbar", 9, "pass", 1, "rst5"},
//                {"Part paid", "Part paid annual test retest", "54", "centre axle drawbar", 10, "pass", 1, "rst5"},
//                {"With certification", "Paid prohibition clearance (full inspection with certification)", "107", "centre axle drawbar", 1, "pass", 1, "p1t1"},
//                {"With certification", "Paid prohibition clearance (full inspection with certification)", "70", "centre axle drawbar", 2, "pass", 1, "p1t2"},
//                {"With certification", "Paid prohibition clearance (full inspection with certification)", "70", "centre axle drawbar", 3, "pass", 1, "p1t3"},
//                {"With certification", "Paid prohibition clearance (full inspection with certification)", "70", "centre axle drawbar", 4, "pass", 1, "p1t4"},
//                {"With certification", "Paid prohibition clearance (full inspection with certification)", "70", "centre axle drawbar", 5, "pass", 1, "p1t5"},
//                {"With certification", "Paid prohibition clearance (full inspection with certification)", "70", "centre axle drawbar", 6, "pass", 1, "p1t5"},
//                {"With certification", "Paid prohibition clearance (full inspection with certification)", "70", "centre axle drawbar", 7, "pass", 1, "p1t5"},
//                {"With certification", "Paid prohibition clearance (full inspection with certification)", "70", "centre axle drawbar", 8, "pass", 1, "p1t5"},
//                {"With certification", "Paid prohibition clearance (full inspection with certification)", "70", "centre axle drawbar", 9, "pass", 1, "p1t5"},
//                {"With certification", "Paid prohibition clearance (retest with certification)", "113", "centre axle drawbar", 1, "pass", 1, "p3t1"},
//                {"With certification", "Paid prohibition clearance (retest with certification)", "76", "centre axle drawbar", 2, "pass", 1, "p3t2"},
//                {"With certification", "Paid prohibition clearance (retest with certification)", "76", "centre axle drawbar", 3, "pass", 1, "p3t3"},
//                {"With certification", "Paid prohibition clearance (retest with certification)", "76", "centre axle drawbar", 4, "pass", 1, "p3t4"},
//                {"With certification", "Paid prohibition clearance (retest with certification)", "76", "centre axle drawbar", 5, "pass", 1, "p3t5"},
//                {"With certification", "Paid prohibition clearance (retest with certification)", "76", "centre axle drawbar", 6, "pass", 1, "p3t5"},
//                {"With certification", "Paid prohibition clearance (retest with certification)", "76", "centre axle drawbar", 7, "pass", 1, "p3t5"},
//                {"With certification", "Paid prohibition clearance (retest with certification)", "76", "centre axle drawbar", 8, "pass", 1, "p3t5"},
//                {"With certification", "Paid prohibition clearance (retest with certification)", "76", "centre axle drawbar", 9, "pass", 1, "p3t5"},
//                {"With certification", "Paid prohibition clearance (retest with certification)", "76", "centre axle drawbar", 10, "pass", 1, "p3t5"},
//                {"With certification", "Part paid prohibition clearance (retest with certification)", "116", "centre axle drawbar", 1, "pass", 1, "p6t1"},
//                {"With certification", "Part paid prohibition clearance (retest with certification)", "79", "centre axle drawbar", 2, "pass", 1, "p6t2"},
//                {"With certification", "Part paid prohibition clearance (retest with certification)", "79", "centre axle drawbar", 3, "pass", 1, "p6t3"},
//                {"With certification", "Part paid prohibition clearance (retest with certification)", "79", "centre axle drawbar", 4, "pass", 1, "p6t4"},
//                {"With certification", "Part paid prohibition clearance (retest with certification)", "79", "centre axle drawbar", 5, "pass", 1, "p6t5"},
//                {"With certification", "Part paid prohibition clearance (retest with certification)", "79", "centre axle drawbar", 6, "pass", 1, "p6t5"},
//                {"With certification", "Part paid prohibition clearance (retest with certification)", "79", "centre axle drawbar", 7, "pass", 1, "p6t5"},
//                {"With certification", "Part paid prohibition clearance (retest with certification)", "79", "centre axle drawbar", 8, "pass", 1, "p6t5"},
//                {"With certification", "Part paid prohibition clearance (retest with certification)", "79", "centre axle drawbar", 9, "pass", 1, "p6t5"},
//                {"With certification", "Part paid prohibition clearance (retest with certification)", "79", "centre axle drawbar", 10, "pass", 1, "p6t5"},
//
                {"First test", "First test", "41", 1, "prs", "fft1"},
//                {"First test", "First test", "95", "centre axle drawbar", 2, "prs", 1, "fft2"},
//                {"First test", "First test", "95", "centre axle drawbar", 3, "prs", 1, "fft3"},
//                {"First test", "First test", "95", "centre axle drawbar", 4, "prs", 1, "fft4"},
//                {"First test", "First test", "95", "centre axle drawbar", 5, "prs", 1, "fft5"},
//                {"First test", "First test", "95", "centre axle drawbar", 6, "prs", 1, "fft5"},
//                {"First test", "First test", "95", "centre axle drawbar", 7, "prs", 1, "fft5"},
//                {"First test", "First test", "95", "centre axle drawbar", 8, "prs", 1, "fft5"},
//                {"First test", "First test", "95", "centre axle drawbar", 9, "prs", 1, "fft5"},
//                {"First test", "First test", "95", "centre axle drawbar", 10, "prs", 1, "fft5"},
//                {"Paid", "Paid first test retest", "103", "centre axle drawbar", 1, "prs", 1, "rgt1"},
//                {"Paid", "Paid first test retest", "65", "centre axle drawbar", 2, "prs", 1, "rgt2"},
//                {"Paid", "Paid first test retest", "65", "centre axle drawbar", 3, "prs", 1, "rgt3"},
//                {"Paid", "Paid first test retest", "65", "centre axle drawbar", 4, "prs", 1, "rgt4"},
//                {"Paid", "Paid first test retest", "65", "centre axle drawbar", 5, "prs", 1, "rgt5"},
//                {"Paid", "Paid first test retest", "65", "centre axle drawbar", 6, "prs", 1, "rgt5"},
//                {"Paid", "Paid first test retest", "65", "centre axle drawbar", 7, "prs", 1, "rgt5"},
//                {"Paid", "Paid first test retest", "65", "centre axle drawbar", 8, "prs", 1, "rgt5"},
//                {"Paid", "Paid first test retest", "65", "centre axle drawbar", 9, "prs", 1, "rgt5"},
//                {"Paid", "Paid first test retest", "65", "centre axle drawbar", 10, "prs", 1, "rgt5"},
//                {"Part paid", "Part paid first test retest", "104", "centre axle drawbar", 1, "prs", 1, "rit1"},
//                {"Part paid", "Part paid first test retest", "66", "centre axle drawbar", 2, "prs", 1, "rit2"},
//                {"Part paid", "Part paid first test retest", "66", "centre axle drawbar", 3, "prs", 1, "rit3"},
//                {"Part paid", "Part paid first test retest", "66", "centre axle drawbar", 4, "prs", 1, "rit4"},
//                {"Part paid", "Part paid first test retest", "66", "centre axle drawbar", 5, "prs", 1, "rit5"},
//                {"Part paid", "Part paid first test retest", "66", "centre axle drawbar", 6, "prs", 1, "rit5"},
//                {"Part paid", "Part paid first test retest", "66", "centre axle drawbar", 7, "prs", 1, "rit5"},
//                {"Part paid", "Part paid first test retest", "66", "centre axle drawbar", 8, "prs", 1, "rit5"},
//                {"Part paid", "Part paid first test retest", "66", "centre axle drawbar", 9, "prs", 1, "rit5"},
//                {"Part paid", "Part paid first test retest", "66", "centre axle drawbar", 10, "prs", 1, "rit5"},
//                {"Free", "Free first test retest", "67", "centre axle drawbar", 3, "prs", 1, "rht"},
//                {"Full Inspection/ Fee - With certification", "Paid prohibition clearance on first test (full inspection with certification)", "119", "centre axle drawbar", 1, "prs", 1, "p7t1"},
//                {"Full Inspection/ Fee - With certification", "Paid prohibition clearance on first test (full inspection with certification)", "82", "centre axle drawbar", 2, "prs", 1, "p7t2"},
//                {"Full Inspection/ Fee - With certification", "Paid prohibition clearance on first test (full inspection with certification)", "82", "centre axle drawbar", 3, "prs", 1, "p7t3"},
//                {"Full Inspection/ Fee - With certification", "Paid prohibition clearance on first test (full inspection with certification)", "82", "centre axle drawbar", 4, "prs", 1, "p7t4"},
//                {"Full Inspection/ Fee - With certification", "Paid prohibition clearance on first test (full inspection with certification)", "82", "centre axle drawbar", 5, "prs", 1, "p7t5"},
//                {"Full Inspection/ Fee - With certification", "Paid prohibition clearance on first test (full inspection with certification)", "82", "centre axle drawbar", 6, "prs", 1, "p7t5"},
//                {"Full Inspection/ Fee - With certification", "Paid prohibition clearance on first test (full inspection with certification)", "82", "centre axle drawbar", 7, "prs", 1, "p7t5"},
//                {"Full Inspection/ Fee - With certification", "Paid prohibition clearance on first test (full inspection with certification)", "82", "centre axle drawbar", 8, "prs", 1, "p7t5"},
//                {"Full Inspection/ Fee - With certification", "Paid prohibition clearance on first test (full inspection with certification)", "82", "centre axle drawbar", 9, "prs", 1, "p7t5"},
//                {"Full Inspection/ Fee - With certification", "Paid prohibition clearance on first test (full inspection with certification)", "82", "centre axle drawbar", 10, "prs", 1, "p7t5"},
//                {"Retest - Paid", "Paid retest prohibition clearance on first test", "120", "centre axle drawbar", 1, "prs", 1, "p4t1"},
//                {"Retest - Paid", "Paid retest prohibition clearance on first test", "83", "centre axle drawbar", 2, "prs", 1, "p4t2"},
//                {"Retest - Paid", "Paid retest prohibition clearance on first test", "83", "centre axle drawbar", 3, "prs", 1, "p4t3"},
//                {"Retest - Paid", "Paid retest prohibition clearance on first test", "83", "centre axle drawbar", 4, "prs", 1, "p4t4"},
//                {"Retest - Paid", "Paid retest prohibition clearance on first test", "83", "centre axle drawbar", 5, "prs", 1, "p4t5"},
//                {"Retest - Paid", "Paid retest prohibition clearance on first test", "83", "centre axle drawbar", 6, "prs", 1, "p4t5"},
//                {"Retest - Paid", "Paid retest prohibition clearance on first test", "83", "centre axle drawbar", 7, "prs", 1, "p4t5"},
//                {"Retest - Paid", "Paid retest prohibition clearance on first test", "83", "centre axle drawbar", 8, "prs", 1, "p4t5"},
//                {"Retest - Paid", "Paid retest prohibition clearance on first test", "83", "centre axle drawbar", 9, "prs", 1, "p4t5"},
//                {"Retest - Paid", "Paid retest prohibition clearance on first test", "83", "centre axle drawbar", 10, "prs", 1, "p4t5"},
//                {"Annual test", "Annual test", "40", "centre axle drawbar", 1, "prs", 1, "aat1"},
//                {"Annual test", "Annual test", "94", "centre axle drawbar", 2, "prs", 1, "aat2"},
//                {"Annual test", "Annual test", "94", "centre axle drawbar", 3, "prs", 1, "aat3"},
//                {"Annual test", "Annual test", "94", "centre axle drawbar", 4, "prs", 1, "aat4"},
//                {"Annual test", "Annual test", "94", "centre axle drawbar", 5, "prs", 1, "aat5"},
//                {"Annual test", "Annual test", "94", "centre axle drawbar", 6, "prs", 1, "aat5"},
//                {"Annual test", "Annual test", "94", "centre axle drawbar", 7, "prs", 1, "aat5"},
//                {"Annual test", "Annual test", "94", "centre axle drawbar", 8, "prs", 1, "aat5"},
//                {"Annual test", "Annual test", "94", "centre axle drawbar", 9, "prs", 1, "aat5"},
//                {"Annual test", "Annual test", "94", "centre axle drawbar", 10, "prs", 1, "aat5"},
//                {"Paid", "Paid annual test retest", "98", "centre axle drawbar", 1, "prs", 1, "rpt1"},
//                {"Paid", "Paid annual test retest", "53", "centre axle drawbar", 2, "prs", 1, "rpt2"},
//                {"Paid", "Paid annual test retest", "53", "centre axle drawbar", 3, "prs", 1, "rpt3"},
//                {"Paid", "Paid annual test retest", "53", "centre axle drawbar", 4, "prs", 1, "rpt4"},
//                {"Paid", "Paid annual test retest", "53", "centre axle drawbar", 5, "prs", 1, "rpt5"},
//                {"Paid", "Paid annual test retest", "53", "centre axle drawbar", 6, "prs", 1, "rpt5"},
//                {"Paid", "Paid annual test retest", "53", "centre axle drawbar", 7, "prs", 1, "rpt5"},
//                {"Paid", "Paid annual test retest", "53", "centre axle drawbar", 8, "prs", 1, "rpt5"},
//                {"Paid", "Paid annual test retest", "53", "centre axle drawbar", 9, "prs", 1, "rpt5"},
//                {"Paid", "Paid annual test retest", "53", "centre axle drawbar", 10, "prs", 1, "rpt5"},
//                {"Part paid", "Part paid annual test retest", "99", "centre axle drawbar", 1, "prs", 1, "rst1"},
//                {"Part paid", "Part paid annual test retest", "54", "centre axle drawbar", 2, "prs", 1, "rst2"},
//                {"Part paid", "Part paid annual test retest", "54", "centre axle drawbar", 3, "prs", 1, "rst3"},
//                {"Part paid", "Part paid annual test retest", "54", "centre axle drawbar", 4, "prs", 1, "rst4"},
//                {"Part paid", "Part paid annual test retest", "54", "centre axle drawbar", 5, "prs", 1, "rst5"},
//                {"Part paid", "Part paid annual test retest", "54", "centre axle drawbar", 6, "prs", 1, "rst5"},
//                {"Part paid", "Part paid annual test retest", "54", "centre axle drawbar", 7, "prs", 1, "rst5"},
//                {"Part paid", "Part paid annual test retest", "54", "centre axle drawbar", 8, "prs", 1, "rst5"},
//                {"Part paid", "Part paid annual test retest", "54", "centre axle drawbar", 9, "prs", 1, "rst5"},
//                {"Part paid", "Part paid annual test retest", "54", "centre axle drawbar", 10, "prs", 1, "rst5"},
//                {"With certification", "Paid prohibition clearance (full inspection with certification)", "107", "centre axle drawbar", 1, "prs", 1, "p1t1"},
//                {"With certification", "Paid prohibition clearance (full inspection with certification)", "70", "centre axle drawbar", 2, "prs", 1, "p1t2"},
//                {"With certification", "Paid prohibition clearance (full inspection with certification)", "70", "centre axle drawbar", 3, "prs", 1, "p1t3"},
//                {"With certification", "Paid prohibition clearance (full inspection with certification)", "70", "centre axle drawbar", 4, "prs", 1, "p1t4"},
//                {"With certification", "Paid prohibition clearance (full inspection with certification)", "70", "centre axle drawbar", 5, "prs", 1, "p1t5"},
//                {"With certification", "Paid prohibition clearance (full inspection with certification)", "70", "centre axle drawbar", 6, "prs", 1, "p1t5"},
//                {"With certification", "Paid prohibition clearance (full inspection with certification)", "70", "centre axle drawbar", 7, "prs", 1, "p1t5"},
//                {"With certification", "Paid prohibition clearance (full inspection with certification)", "70", "centre axle drawbar", 8, "prs", 1, "p1t5"},
//                {"With certification", "Paid prohibition clearance (full inspection with certification)", "70", "centre axle drawbar", 9, "prs", 1, "p1t5"},
//                {"With certification", "Paid prohibition clearance (retest with certification)", "113", "centre axle drawbar", 1, "prs", 1, "p3t1"},
//                {"With certification", "Paid prohibition clearance (retest with certification)", "76", "centre axle drawbar", 2, "prs", 1, "p3t2"},
//                {"With certification", "Paid prohibition clearance (retest with certification)", "76", "centre axle drawbar", 3, "prs", 1, "p3t3"},
//                {"With certification", "Paid prohibition clearance (retest with certification)", "76", "centre axle drawbar", 4, "prs", 1, "p3t4"},
//                {"With certification", "Paid prohibition clearance (retest with certification)", "76", "centre axle drawbar", 5, "prs", 1, "p3t5"},
//                {"With certification", "Paid prohibition clearance (retest with certification)", "76", "centre axle drawbar", 6, "prs", 1, "p3t5"},
//                {"With certification", "Paid prohibition clearance (retest with certification)", "76", "centre axle drawbar", 7, "prs", 1, "p3t5"},
//                {"With certification", "Paid prohibition clearance (retest with certification)", "76", "centre axle drawbar", 8, "prs", 1, "p3t5"},
//                {"With certification", "Paid prohibition clearance (retest with certification)", "76", "centre axle drawbar", 9, "prs", 1, "p3t5"},
//                {"With certification", "Paid prohibition clearance (retest with certification)", "76", "centre axle drawbar", 10, "prs", 1, "p3t5"},
//                {"With certification", "Part paid prohibition clearance (retest with certification)", "116", "centre axle drawbar", 1, "prs", 1, "p6t1"},
//                {"With certification", "Part paid prohibition clearance (retest with certification)", "79", "centre axle drawbar", 2, "prs", 1, "p6t2"},
//                {"With certification", "Part paid prohibition clearance (retest with certification)", "79", "centre axle drawbar", 3, "prs", 1, "p6t3"},
//                {"With certification", "Part paid prohibition clearance (retest with certification)", "79", "centre axle drawbar", 4, "prs", 1, "p6t4"},
//                {"With certification", "Part paid prohibition clearance (retest with certification)", "79", "centre axle drawbar", 5, "prs", 1, "p6t5"},
//                {"With certification", "Part paid prohibition clearance (retest with certification)", "79", "centre axle drawbar", 6, "prs", 1, "p6t5"},
//                {"With certification", "Part paid prohibition clearance (retest with certification)", "79", "centre axle drawbar", 7, "prs", 1, "p6t5"},
//                {"With certification", "Part paid prohibition clearance (retest with certification)", "79", "centre axle drawbar", 8, "prs", 1, "p6t5"},
//                {"With certification", "Part paid prohibition clearance (retest with certification)", "79", "centre axle drawbar", 9, "prs", 1, "p6t5"},
//                {"With certification", "Part paid prohibition clearance (retest with certification)", "79", "centre axle drawbar", 10, "prs", 1, "p6t5"},

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
    @Title("CVSB-8684 - TC1 - AC1 - TRL First Test - NO Previous Expiry Date")
    @Test
    public void testResultsFirstTestExpiryTrlNoExpiryDate() {

        // Read the base test result JSON.
        String testResultRecord = GenericData.readJsonValueFromFile("test-results_expiry_date_trl_8684.json", "$");

        DateTime submittedTestStartTimestamp = DateTime.now().withZone(DateTimeZone.UTC);
        DateTime submittedTestTypeStartTimestamp = submittedTestStartTimestamp.plusMinutes(5);
        DateTime submittedTypeEndTimestamp = submittedTestTypeStartTimestamp.plusMinutes(10);
        DateTime submittedEndTimestamp = submittedTypeEndTimestamp.plusMinutes(5);

        // Create alteration to add one more tech record to in the request body
        String firstUseDate = submittedTypeEndTimestamp.toDateTime().minusYears(1).minusDays(1).toString("yyyy-MM-dd");
        String testStartTimestamp = submittedTestStartTimestamp.toInstant().toString();
        String testTypeStartTimestamp = submittedTestTypeStartTimestamp.toInstant().toString();
        String testTypeEndTimestamp = submittedTypeEndTimestamp.toInstant().toString();
        String testEndTimestamp = submittedEndTimestamp.toInstant().toString();
        String testExpectedDate = submittedEndTimestamp.dayOfMonth().withMaximumValue().plusYears(1).withTimeAtStartOfDay().toInstant().toString();
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
        JsonPathAlteration alterationTestExpiryDate = new JsonPathAlteration("$.testTypes[0].testExpiryDate", "", "", "DELETE");

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
        testResultsSteps.valueForFieldInPathShouldStartWith("[0].testTypes[0].testExpiryDate", testExpectedDate.substring(0,10));

    }

    @WithTag("Expiry_Dates")
    @Title("CVSB-8684 - Include expiry date calculation validation in BE test suite - TRL - testTypeEndTimestamp (date only) is less than, but not including, 2 months before (last day in the month of firstUseDate + 1 year)")
    @Test
    public void testResultsNoFirstTestExpiryTrlLessThanTwoMonthsOrMoreBefore() {

        // Read the base test result JSON.
        String testResultRecord = GenericData.readJsonValueFromFile("test-results_expiry_date_trl_8684.json", "$");

        DateTime submittedTestStartTimestamp = DateTime.now().withZone(DateTimeZone.UTC);
        DateTime submittedTestTypeStartTimestamp = submittedTestStartTimestamp.plusMinutes(5);
        DateTime submittedTypeEndTimestamp = submittedTestTypeStartTimestamp.plusMinutes(10);
        DateTime submittedEndTimestamp = submittedTypeEndTimestamp.plusMinutes(5);

        // Create alteration to add one more tech record to in the request body
        String firstUseDate = submittedTypeEndTimestamp.toDateTime().minusYears(1).plusMonths(2).plusDays(1).toString("yyyy-MM-dd");
        String testStartTimestamp = submittedTestStartTimestamp.toInstant().toString();
        String testTypeStartTimestamp = submittedTestTypeStartTimestamp.toInstant().toString();
        String testTypeEndTimestamp = submittedTypeEndTimestamp.toInstant().toString();
        String testEndTimestamp = submittedEndTimestamp.toInstant().toString();
        String testExpectedDate = submittedTestStartTimestamp.dayOfMonth().withMaximumValue().plusYears(1).withTimeAtStartOfDay().toInstant().toString();
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
        testResultsSteps.valueForFieldInPathShouldStartWith("[0].testTypes[0].testExpiryDate", testExpectedDate.substring(0,10));

    }


    @WithTag("Expiry_Dates")
    @Title("CVSB-8684 - Include expiry date calculation validation in BE test suite - TRL - testTypeEndTimestamp (date only) is less than, but not including, 2 months before (last day in the month of firstUseDate + 1 year) AND no existing testExpiryDate")
    @Test
    public void testResultsNoFirstTestExpiryTrlLessThanTwoMonthsOrLessBefore() {

        // Read the base test result JSON.
        String testResultRecord = GenericData.readJsonValueFromFile("test-results_expiry_date_trl_8684.json", "$");

        DateTime submittedTestStartTimestamp = DateTime.now().withZone(DateTimeZone.UTC);
        DateTime submittedTestTypeStartTimestamp = submittedTestStartTimestamp.plusMinutes(5);
        DateTime submittedTypeEndTimestamp = submittedTestTypeStartTimestamp.plusMinutes(10);
        DateTime submittedEndTimestamp = submittedTypeEndTimestamp.plusMinutes(5);
        DateTime submittedFirstUseDate = submittedTypeEndTimestamp.dayOfMonth().withMaximumValue().toDateTime().minusYears(1).plusMonths(2);

        // Create alteration to add one more tech record to in the request body
        String firstUseDate = submittedFirstUseDate.toString("yyyy-MM-dd");
        String testStartTimestamp = submittedTestStartTimestamp.toInstant().toString();
        String testTypeStartTimestamp = submittedTestTypeStartTimestamp.toInstant().toString();
        String testTypeEndTimestamp = submittedTypeEndTimestamp.toInstant().toString();
        String testEndTimestamp = submittedEndTimestamp.toInstant().toString();
        String testExpectedDate = submittedTestStartTimestamp.dayOfMonth().withMaximumValue().plusYears(1).withTimeAtStartOfDay().toInstant().toString();
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
        testResultsSteps.valueForFieldInPathShouldStartWith("[0].testTypes[0].testExpiryDate", testExpectedDate.substring(0,10));

    }

    @WithTag("Expiry_Dates")
    @Title("CVSB-8684 - Include expiry date calculation validation in BE test suite - TRL - If firstUseDate = null AND no existing testExpiryDate")
    @Test
    public void testResultsNoFirstTestExpiryTrlFirstDateNull() {

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

    @WithTag("Expiry_Dates")
    @Title("CVSB-8684 - Include expiry date calculation validation in BE test suite - TRL - If firstUseDate = null AND no existing testExpiryDate")
    @Test
    public void testResultsNoFirstTestExpiryTrlFirstDateMissing() {

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

    @WithTag("Expiry_Dates")
    @Title("CVSB-8684 - Include expiry date calculation validation in BE test suite - TRL - testExpiryDate is generated")
    @Test
    public void testResultsNoFirstTestExpiryTrlTestExpiryDateGenerated() {

        // Read the base test result JSON.
        String testResultRecord = GenericData.readJsonValueFromFile("test-results_expiry_date_trl_8684.json", "$");

        DateTime submittedTestStartTimestamp = DateTime.now().withZone(DateTimeZone.UTC);
        DateTime submittedTestTypeStartTimestamp = submittedTestStartTimestamp.plusMinutes(5);
        DateTime submittedTypeEndTimestamp = submittedTestTypeStartTimestamp.plusMinutes(10);
        DateTime submittedEndTimestamp = submittedTypeEndTimestamp.plusMinutes(5);
        DateTime submittedTestExpiryDate = submittedTypeEndTimestamp.toDateTime().minusYears(1).plusDays(1);

        // Create alteration to add one more tech record to in the request body
        String testStartTimestamp = submittedTestStartTimestamp.toInstant().toString();
        String testExpiryDateTimestamp = submittedTestExpiryDate.toDateTime().toString("yyyy-MM-dd");
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
        JsonPathAlteration alterationTestExpiryDate = new JsonPathAlteration("$.testTypes[0].testExpiryDate", testExpiryDateTimestamp, "", "REPLACE");

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

}
