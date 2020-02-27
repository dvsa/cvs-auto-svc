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
public class PostTestResultsExpiryDateLogicPsv {

    @Steps
    TestResultsSteps testResultsSteps;

    @TestData
    public static Collection<Object[]> testData() {
        return Arrays.asList(new Object[][]{
//                {"Annual test", "Annual test", "1", "large", "rigid", "pass", "aal", "aal", true},
                {"Annual test", "Annual test", "1", "small", "rigid", "pass", "aal", "aas", true},
                {"Annual test", "Annual test", "1", "large", "articulated", "pass", "aal", "adl", true},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "large", "rigid", "pass", "aal", "wdl", true},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "small", "rigid", "pass", "aal", "wds", true},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "large", "rigid", "pass", "aal", "wbl", true},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "small", "rigid", "pass", "aal", "wbs", true},
//                {"Any PSV", "Paid retest", "7", "large", "articulated", "pass", "aal", "rhl", true},
//                {"Any PSV", "Paid retest", "7", "small", "rigid", "pass", "aal", "rps", true},
//                {"Any PSV", "Paid retest", "7", "large", "rigid", "pass", "aal", "rpl", true},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "large", "rigid", "pass", "aal", "whl", true},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "small", "rigid", "pass", "aal", "whs", true},
//                {"Any PSV", "Part-paid retest", "10", "large", "articulated", "pass", "aal", "rgl", true},
//                {"Any PSV", "Part-paid retest", "10", "large", "rigid", "pass", "aal", "rsl", true},
//                {"Any PSV", "Part-paid retest", "10", "small", "rigid", "pass", "aal", "rss", true},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "large", "rigid", "pass", "aal", "p1l", true},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "small", "rigid", "pass", "aal", "p1s", true},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "large", "rigid", "pass", "aal", "p8l", true},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "small", "rigid", "pass", "aal", "p8s", true},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "large", "rigid", "pass", "aal", "p6l", true},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "small", "rigid", "pass", "aal", "p6s", true},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "small", "rigid", "pass", "aal", "wis", true},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "large", "rigid", "pass", "aal", "wil", true},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "large", "rigid", "pass", "aal", "wfl", true},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "small", "rigid", "pass", "aal", "wfs", true},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "large", "rigid", "pass", "aal", "wel", true},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "small", "rigid", "pass", "aal", "wes", true},
//                {"Annual test", "Annual test", "1", "large", "rigid", "pass", "aal", "aal", false},
//                {"Annual test", "Annual test", "1", "small", "rigid", "pass", "aal", "aas", false},
//                {"Annual test", "Annual test", "1", "large", "articulated", "pass", "aal", "adl", false},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "large", "rigid", "pass", "aal", "wdl", false},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "small", "rigid", "pass", "aal", "wds", false},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "large", "rigid", "pass", "aal", "wbl", false},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "small", "rigid", "pass", "aal", "wbs", false},
//                {"Any PSV", "Paid retest", "7", "large", "articulated", "pass", "aal", "rhl", false},
//                {"Any PSV", "Paid retest", "7", "small", "rigid", "pass", "aal", "rps", false},
//                {"Any PSV", "Paid retest", "7", "large", "rigid", "pass", "aal", "rpl", false},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "large", "rigid", "pass", "aal", "whl", false},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "small", "rigid", "pass", "aal", "whs", false},
//                {"Any PSV", "Part-paid retest", "10", "large", "articulated", "pass", "aal", "rgl", false},
//                {"Any PSV", "Part-paid retest", "10", "large", "rigid", "pass", "aal", "rsl", false},
//                {"Any PSV", "Part-paid retest", "10", "small", "rigid", "pass", "aal", "rss", false},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "large", "rigid", "pass", "aal", "p1l", false},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "small", "rigid", "pass", "aal", "p1s", false},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "large", "rigid", "pass", "aal", "p8l", false},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "small", "rigid", "pass", "aal", "p8s", false},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "large", "rigid", "pass", "aal", "p6l", false},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "small", "rigid", "pass", "aal", "p6s", false},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "small", "rigid", "pass", "aal", "wis", false},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "large", "rigid", "pass", "aal", "wil", false},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "large", "rigid", "pass", "aal", "wfl", false},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "small", "rigid", "pass", "aal", "wfs", false},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "large", "rigid", "pass", "aal", "wel", false},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "small", "rigid", "pass", "aal", "wes", false},
//                {"Annual test", "Annual test", "1", "large", "rigid", "prs", "aal", "aal", true},
//                {"Annual test", "Annual test", "1", "small", "rigid", "prs", "aal", "aas", true},
//                {"Annual test", "Annual test", "1", "large", "articulated", "prs", "aal", "adl", true},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "large", "rigid", "prs", "aal", "wdl", true},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "small", "rigid", "prs", "aal", "wds", true},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "large", "rigid", "prs", "aal", "wbl", true},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "small", "rigid", "prs", "aal", "wbs", true},
//                {"Any PSV", "Paid retest", "7", "large", "articulated", "prs", "aal", "rhl", true},
//                {"Any PSV", "Paid retest", "7", "small", "rigid", "prs", "aal", "rps", true},
//                {"Any PSV", "Paid retest", "7", "large", "rigid", "prs", "aal", "rpl", true},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "large", "rigid", "prs", "aal", "whl", true},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "small", "rigid", "prs", "aal", "whs", true},
//                {"Any PSV", "Part-paid retest", "10", "large", "articulated", "prs", "aal", "rgl", true},
//                {"Any PSV", "Part-paid retest", "10", "large", "rigid", "prs", "aal", "rsl", true},
//                {"Any PSV", "Part-paid retest", "10", "small", "rigid", "prs", "aal", "rss", true},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "large", "rigid", "prs", "aal", "p1l", true},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "small", "rigid", "prs", "aal", "p1s", true},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "large", "rigid", "prs", "aal", "p8l", true},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "small", "rigid", "prs", "aal", "p8s", true},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "large", "rigid", "prs", "aal", "p6l", true},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "small", "rigid", "prs", "aal", "p6s", true},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "small", "rigid", "prs", "aal", "wis", true},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "large", "rigid", "prs", "aal", "wil", true},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "large", "rigid", "prs", "aal", "wfl", true},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "small", "rigid", "prs", "aal", "wfs", true},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "large", "rigid", "prs", "aal", "wel", true},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "small", "rigid", "prs", "aal", "wes", true},
//                {"Annual test", "Annual test", "1", "large", "rigid", "prs", "aal", "aal", false},
//                {"Annual test", "Annual test", "1", "small", "rigid", "prs", "aal", "aas", false},
//                {"Annual test", "Annual test", "1", "large", "articulated", "prs", "aal", "adl", false},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "large", "rigid", "prs", "aal", "wdl", false},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "small", "rigid", "prs", "aal", "wds", false},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "large", "rigid", "prs", "aal", "wbl", false},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "small", "rigid", "prs", "aal", "wbs", false},
//                {"Any PSV", "Paid retest", "7", "large", "articulated", "prs", "aal", "rhl", false},
//                {"Any PSV", "Paid retest", "7", "small", "rigid", "prs", "aal", "rps", false},
//                {"Any PSV", "Paid retest", "7", "large", "rigid", "prs", "aal", "rpl", false},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "large", "rigid", "prs", "aal", "whl", false},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "small", "rigid", "prs", "aal", "whs", false},
//                {"Any PSV", "Part-paid retest", "10", "large", "articulated", "prs", "aal", "rgl", false},
//                {"Any PSV", "Part-paid retest", "10", "large", "rigid", "prs", "aal", "rsl", false},
//                {"Any PSV", "Part-paid retest", "10", "small", "rigid", "prs", "aal", "rss", false},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "large", "rigid", "prs", "aal", "p1l", false},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "small", "rigid", "prs", "aal", "p1s", false},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "large", "rigid", "prs", "aal", "p8l", false},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "small", "rigid", "prs", "aal", "p8s", false},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "large", "rigid", "prs", "aal", "p6l", false},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "small", "rigid", "prs", "aal", "p6s", false},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "small", "rigid", "prs", "aal", "wis", false},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "large", "rigid", "prs", "aal", "wil", false},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "large", "rigid", "prs", "aal", "wfl", false},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "small", "rigid", "prs", "aal", "wfs", false},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "large", "rigid", "prs", "aal", "wel", false},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "small", "rigid", "prs", "aal", "wes", false},
//
//                {"Annual test", "Annual test", "1", "large", "rigid", "pass", "aas", "aal", true},
//                {"Annual test", "Annual test", "1", "small", "rigid", "pass", "aas", "aas", true},
//                {"Annual test", "Annual test", "1", "large", "articulated", "pass", "aas", "adl", true},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "large", "rigid", "pass", "aas", "wdl", true},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "small", "rigid", "pass", "aas", "wds", true},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "large", "rigid", "pass", "aas", "wbl", true},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "small", "rigid", "pass", "aas", "wbs", true},
//                {"Any PSV", "Paid retest", "7", "large", "articulated", "pass", "aas", "rhl", true},
//                {"Any PSV", "Paid retest", "7", "small", "rigid", "pass", "aas", "rps", true},
//                {"Any PSV", "Paid retest", "7", "large", "rigid", "pass", "aas", "rpl", true},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "large", "rigid", "pass", "aas", "whl", true},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "small", "rigid", "pass", "aas", "whs", true},
//                {"Any PSV", "Part-paid retest", "10", "large", "articulated", "pass", "aas", "rgl", true},
//                {"Any PSV", "Part-paid retest", "10", "large", "rigid", "pass", "aas", "rsl", true},
//                {"Any PSV", "Part-paid retest", "10", "small", "rigid", "pass", "aas", "rss", true},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "large", "rigid", "pass", "aas", "p1l", true},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "small", "rigid", "pass", "aas", "p1s", true},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "large", "rigid", "pass", "aas", "p8l", true},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "small", "rigid", "pass", "aas", "p8s", true},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "large", "rigid", "pass", "aas", "p6l", true},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "small", "rigid", "pass", "aas", "p6s", true},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "small", "rigid", "pass", "aas", "wis", true},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "large", "rigid", "pass", "aas", "wil", true},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "large", "rigid", "pass", "aas", "wfl", true},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "small", "rigid", "pass", "aas", "wfs", true},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "large", "rigid", "pass", "aas", "wel", true},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "small", "rigid", "pass", "aas", "wes", true},
//                {"Annual test", "Annual test", "1", "large", "rigid", "pass", "aas", "aal", false},
//                {"Annual test", "Annual test", "1", "small", "rigid", "pass", "aas", "aas", false},
//                {"Annual test", "Annual test", "1", "large", "articulated", "pass", "aas", "adl", false},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "large", "rigid", "pass", "aas", "wdl", false},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "small", "rigid", "pass", "aas", "wds", false},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "large", "rigid", "pass", "aas", "wbl", false},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "small", "rigid", "pass", "aas", "wbs", false},
//                {"Any PSV", "Paid retest", "7", "large", "articulated", "pass", "aas", "rhl", false},
//                {"Any PSV", "Paid retest", "7", "small", "rigid", "pass", "aas", "rps", false},
//                {"Any PSV", "Paid retest", "7", "large", "rigid", "pass", "aas", "rpl", false},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "large", "rigid", "pass", "aas", "whl", false},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "small", "rigid", "pass", "aas", "whs", false},
//                {"Any PSV", "Part-paid retest", "10", "large", "articulated", "pass", "aas", "rgl", false},
//                {"Any PSV", "Part-paid retest", "10", "large", "rigid", "pass", "aas", "rsl", false},
//                {"Any PSV", "Part-paid retest", "10", "small", "rigid", "pass", "aas", "rss", false},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "large", "rigid", "pass", "aas", "p1l", false},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "small", "rigid", "pass", "aas", "p1s", false},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "large", "rigid", "pass", "aas", "p8l", false},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "small", "rigid", "pass", "aas", "p8s", false},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "large", "rigid", "pass", "aas", "p6l", false},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "small", "rigid", "pass", "aas", "p6s", false},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "small", "rigid", "pass", "aas", "wis", false},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "large", "rigid", "pass", "aas", "wil", false},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "large", "rigid", "pass", "aas", "wfl", false},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "small", "rigid", "pass", "aas", "wfs", false},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "large", "rigid", "pass", "aas", "wel", false},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "small", "rigid", "pass", "aas", "wes", false},
//                {"Annual test", "Annual test", "1", "large", "rigid", "prs", "aas", "aal", true},
//                {"Annual test", "Annual test", "1", "small", "rigid", "prs", "aas", "aas", true},
//                {"Annual test", "Annual test", "1", "large", "articulated", "prs", "aas", "adl", true},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "large", "rigid", "prs", "aas", "wdl", true},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "small", "rigid", "prs", "aas", "wds", true},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "large", "rigid", "prs", "aas", "wbl", true},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "small", "rigid", "prs", "aas", "wbs", true},
//                {"Any PSV", "Paid retest", "7", "large", "articulated", "prs", "aas", "rhl", true},
//                {"Any PSV", "Paid retest", "7", "small", "rigid", "prs", "aas", "rps", true},
//                {"Any PSV", "Paid retest", "7", "large", "rigid", "prs", "aas", "rpl", true},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "large", "rigid", "prs", "aas", "whl", true},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "small", "rigid", "prs", "aas", "whs", true},
//                {"Any PSV", "Part-paid retest", "10", "large", "articulated", "prs", "aas", "rgl", true},
//                {"Any PSV", "Part-paid retest", "10", "large", "rigid", "prs", "aas", "rsl", true},
//                {"Any PSV", "Part-paid retest", "10", "small", "rigid", "prs", "aas", "rss", true},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "large", "rigid", "prs", "aas", "p1l", true},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "small", "rigid", "prs", "aas", "p1s", true},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "large", "rigid", "prs", "aas", "p8l", true},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "small", "rigid", "prs", "aas", "p8s", true},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "large", "rigid", "prs", "aas", "p6l", true},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "small", "rigid", "prs", "aas", "p6s", true},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "small", "rigid", "prs", "aas", "wis", true},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "large", "rigid", "prs", "aas", "wil", true},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "large", "rigid", "prs", "aas", "wfl", true},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "small", "rigid", "prs", "aas", "wfs", true},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "large", "rigid", "prs", "aas", "wel", true},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "small", "rigid", "prs", "aas", "wes", true},
//                {"Annual test", "Annual test", "1", "large", "rigid", "prs", "aas", "aal", false},
//                {"Annual test", "Annual test", "1", "small", "rigid", "prs", "aas", "aas", false},
//                {"Annual test", "Annual test", "1", "large", "articulated", "prs", "aas", "adl", false},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "large", "rigid", "prs", "aas", "wdl", false},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "small", "rigid", "prs", "aas", "wds", false},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "large", "rigid", "prs", "aas", "wbl", false},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "small", "rigid", "prs", "aas", "wbs", false},
//                {"Any PSV", "Paid retest", "7", "large", "articulated", "prs", "aas", "rhl", false},
//                {"Any PSV", "Paid retest", "7", "small", "rigid", "prs", "aas", "rps", false},
//                {"Any PSV", "Paid retest", "7", "large", "rigid", "prs", "aas", "rpl", false},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "large", "rigid", "prs", "aas", "whl", false},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "small", "rigid", "prs", "aas", "whs", false},
//                {"Any PSV", "Part-paid retest", "10", "large", "articulated", "prs", "aas", "rgl", false},
//                {"Any PSV", "Part-paid retest", "10", "large", "rigid", "prs", "aas", "rsl", false},
//                {"Any PSV", "Part-paid retest", "10", "small", "rigid", "prs", "aas", "rss", false},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "large", "rigid", "prs", "aas", "p1l", false},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "small", "rigid", "prs", "aas", "p1s", false},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "large", "rigid", "prs", "aas", "p8l", false},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "small", "rigid", "prs", "aas", "p8s", false},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "large", "rigid", "prs", "aas", "p6l", false},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "small", "rigid", "prs", "aas", "p6s", false},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "small", "rigid", "prs", "aas", "wis", false},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "large", "rigid", "prs", "aas", "wil", false},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "large", "rigid", "prs", "aas", "wfl", false},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "small", "rigid", "prs", "aas", "wfs", false},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "large", "rigid", "prs", "aas", "wel", false},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "small", "rigid", "prs", "aas", "wes", false},
//
//                {"Annual test", "Annual test", "1", "large", "rigid", "pass", "adl", "aal", true},
//                {"Annual test", "Annual test", "1", "small", "rigid", "pass", "adl", "aas", true},
//                {"Annual test", "Annual test", "1", "large", "articulated", "pass", "adl", "adl", true},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "large", "rigid", "pass", "adl", "wdl", true},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "small", "rigid", "pass", "adl", "wds", true},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "large", "rigid", "pass", "adl", "wbl", true},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "small", "rigid", "pass", "adl", "wbs", true},
//                {"Any PSV", "Paid retest", "7", "large", "articulated", "pass", "adl", "rhl", true},
//                {"Any PSV", "Paid retest", "7", "small", "rigid", "pass", "adl", "rps", true},
//                {"Any PSV", "Paid retest", "7", "large", "rigid", "pass", "adl", "rpl", true},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "large", "rigid", "pass", "adl", "whl", true},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "small", "rigid", "pass", "adl", "whs", true},
//                {"Any PSV", "Part-paid retest", "10", "large", "articulated", "pass", "adl", "rgl", true},
//                {"Any PSV", "Part-paid retest", "10", "large", "rigid", "pass", "adl", "rsl", true},
//                {"Any PSV", "Part-paid retest", "10", "small", "rigid", "pass", "adl", "rss", true},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "large", "rigid", "pass", "adl", "p1l", true},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "small", "rigid", "pass", "adl", "p1s", true},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "large", "rigid", "pass", "adl", "p8l", true},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "small", "rigid", "pass", "adl", "p8s", true},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "large", "rigid", "pass", "adl", "p6l", true},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "small", "rigid", "pass", "adl", "p6s", true},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "small", "rigid", "pass", "adl", "wis", true},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "large", "rigid", "pass", "adl", "wil", true},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "large", "rigid", "pass", "adl", "wfl", true},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "small", "rigid", "pass", "adl", "wfs", true},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "large", "rigid", "pass", "adl", "wel", true},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "small", "rigid", "pass", "adl", "wes", true},
//                {"Annual test", "Annual test", "1", "large", "rigid", "pass", "adl", "aal", false},
//                {"Annual test", "Annual test", "1", "small", "rigid", "pass", "adl", "aas", false},
//                {"Annual test", "Annual test", "1", "large", "articulated", "pass", "adl", "adl", false},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "large", "rigid", "pass", "adl", "wdl", false},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "small", "rigid", "pass", "adl", "wds", false},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "large", "rigid", "pass", "adl", "wbl", false},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "small", "rigid", "pass", "adl", "wbs", false},
//                {"Any PSV", "Paid retest", "7", "large", "articulated", "pass", "adl", "rhl", false},
//                {"Any PSV", "Paid retest", "7", "small", "rigid", "pass", "adl", "rps", false},
//                {"Any PSV", "Paid retest", "7", "large", "rigid", "pass", "adl", "rpl", false},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "large", "rigid", "pass", "adl", "whl", false},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "small", "rigid", "pass", "adl", "whs", false},
//                {"Any PSV", "Part-paid retest", "10", "large", "articulated", "pass", "adl", "rgl", false},
//                {"Any PSV", "Part-paid retest", "10", "large", "rigid", "pass", "adl", "rsl", false},
//                {"Any PSV", "Part-paid retest", "10", "small", "rigid", "pass", "adl", "rss", false},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "large", "rigid", "pass", "adl", "p1l", false},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "small", "rigid", "pass", "adl", "p1s", false},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "large", "rigid", "pass", "adl", "p8l", false},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "small", "rigid", "pass", "adl", "p8s", false},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "large", "rigid", "pass", "adl", "p6l", false},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "small", "rigid", "pass", "adl", "p6s", false},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "small", "rigid", "pass", "adl", "wis", false},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "large", "rigid", "pass", "adl", "wil", false},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "large", "rigid", "pass", "adl", "wfl", false},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "small", "rigid", "pass", "adl", "wfs", false},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "large", "rigid", "pass", "adl", "wel", false},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "small", "rigid", "pass", "adl", "wes", false},
//                {"Annual test", "Annual test", "1", "large", "rigid", "prs", "adl", "aal", true},
//                {"Annual test", "Annual test", "1", "small", "rigid", "prs", "adl", "aas", true},
//                {"Annual test", "Annual test", "1", "large", "articulated", "prs", "adl", "adl", true},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "large", "rigid", "prs", "adl", "wdl", true},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "small", "rigid", "prs", "adl", "wds", true},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "large", "rigid", "prs", "adl", "wbl", true},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "small", "rigid", "prs", "adl", "wbs", true},
//                {"Any PSV", "Paid retest", "7", "large", "articulated", "prs", "adl", "rhl", true},
//                {"Any PSV", "Paid retest", "7", "small", "rigid", "prs", "adl", "rps", true},
//                {"Any PSV", "Paid retest", "7", "large", "rigid", "prs", "adl", "rpl", true},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "large", "rigid", "prs", "adl", "whl", true},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "small", "rigid", "prs", "adl", "whs", true},
//                {"Any PSV", "Part-paid retest", "10", "large", "articulated", "prs", "adl", "rgl", true},
//                {"Any PSV", "Part-paid retest", "10", "large", "rigid", "prs", "adl", "rsl", true},
//                {"Any PSV", "Part-paid retest", "10", "small", "rigid", "prs", "adl", "rss", true},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "large", "rigid", "prs", "adl", "p1l", true},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "small", "rigid", "prs", "adl", "p1s", true},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "large", "rigid", "prs", "adl", "p8l", true},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "small", "rigid", "prs", "adl", "p8s", true},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "large", "rigid", "prs", "adl", "p6l", true},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "small", "rigid", "prs", "adl", "p6s", true},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "small", "rigid", "prs", "adl", "wis", true},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "large", "rigid", "prs", "adl", "wil", true},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "large", "rigid", "prs", "adl", "wfl", true},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "small", "rigid", "prs", "adl", "wfs", true},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "large", "rigid", "prs", "adl", "wel", true},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "small", "rigid", "prs", "adl", "wes", true},
//                {"Annual test", "Annual test", "1", "large", "rigid", "prs", "adl", "aal", false},
//                {"Annual test", "Annual test", "1", "small", "rigid", "prs", "adl", "aas", false},
//                {"Annual test", "Annual test", "1", "large", "articulated", "prs", "adl", "adl", false},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "large", "rigid", "prs", "adl", "wdl", false},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "small", "rigid", "prs", "adl", "wds", false},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "large", "rigid", "prs", "adl", "wbl", false},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "small", "rigid", "prs", "adl", "wbs", false},
//                {"Any PSV", "Paid retest", "7", "large", "articulated", "prs", "adl", "rhl", false},
//                {"Any PSV", "Paid retest", "7", "small", "rigid", "prs", "adl", "rps", false},
//                {"Any PSV", "Paid retest", "7", "large", "rigid", "prs", "adl", "rpl", false},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "large", "rigid", "prs", "adl", "whl", false},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "small", "rigid", "prs", "adl", "whs", false},
//                {"Any PSV", "Part-paid retest", "10", "large", "articulated", "prs", "adl", "rgl", false},
//                {"Any PSV", "Part-paid retest", "10", "large", "rigid", "prs", "adl", "rsl", false},
//                {"Any PSV", "Part-paid retest", "10", "small", "rigid", "prs", "adl", "rss", false},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "large", "rigid", "prs", "adl", "p1l", false},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "small", "rigid", "prs", "adl", "p1s", false},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "large", "rigid", "prs", "adl", "p8l", false},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "small", "rigid", "prs", "adl", "p8s", false},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "large", "rigid", "prs", "adl", "p6l", false},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "small", "rigid", "prs", "adl", "p6s", false},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "small", "rigid", "prs", "adl", "wis", false},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "large", "rigid", "prs", "adl", "wil", false},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "large", "rigid", "prs", "adl", "wfl", false},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "small", "rigid", "prs", "adl", "wfs", false},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "large", "rigid", "prs", "adl", "wel", false},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "small", "rigid", "prs", "adl", "wes", false},
//
//                {"Annual test", "Annual test", "1", "large", "rigid", "pass", "p1l", "aal", true},
//                {"Annual test", "Annual test", "1", "small", "rigid", "pass", "p1l", "aas", true},
//                {"Annual test", "Annual test", "1", "large", "articulated", "pass", "p1l", "adl", true},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "large", "rigid", "pass", "p1l", "wdl", true},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "small", "rigid", "pass", "p1l", "wds", true},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "large", "rigid", "pass", "p1l", "wbl", true},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "small", "rigid", "pass", "p1l", "wbs", true},
//                {"Any PSV", "Paid retest", "7", "large", "articulated", "pass", "p1l", "rhl", true},
//                {"Any PSV", "Paid retest", "7", "small", "rigid", "pass", "p1l", "rps", true},
//                {"Any PSV", "Paid retest", "7", "large", "rigid", "pass", "p1l", "rpl", true},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "large", "rigid", "pass", "p1l", "whl", true},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "small", "rigid", "pass", "p1l", "whs", true},
//                {"Any PSV", "Part-paid retest", "10", "large", "articulated", "pass", "p1l", "rgl", true},
//                {"Any PSV", "Part-paid retest", "10", "large", "rigid", "pass", "p1l", "rsl", true},
//                {"Any PSV", "Part-paid retest", "10", "small", "rigid", "pass", "p1l", "rss", true},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "large", "rigid", "pass", "p1l", "p1l", true},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "small", "rigid", "pass", "p1l", "p1s", true},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "large", "rigid", "pass", "p1l", "p8l", true},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "small", "rigid", "pass", "p1l", "p8s", true},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "large", "rigid", "pass", "p1l", "p6l", true},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "small", "rigid", "pass", "p1l", "p6s", true},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "small", "rigid", "pass", "p1l", "wis", true},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "large", "rigid", "pass", "p1l", "wil", true},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "large", "rigid", "pass", "p1l", "wfl", true},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "small", "rigid", "pass", "p1l", "wfs", true},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "large", "rigid", "pass", "p1l", "wel", true},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "small", "rigid", "pass", "p1l", "wes", true},
//                {"Annual test", "Annual test", "1", "large", "rigid", "pass", "p1l", "aal", false},
//                {"Annual test", "Annual test", "1", "small", "rigid", "pass", "p1l", "aas", false},
//                {"Annual test", "Annual test", "1", "large", "articulated", "pass", "p1l", "adl", false},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "large", "rigid", "pass", "p1l", "wdl", false},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "small", "rigid", "pass", "p1l", "wds", false},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "large", "rigid", "pass", "p1l", "wbl", false},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "small", "rigid", "pass", "p1l", "wbs", false},
//                {"Any PSV", "Paid retest", "7", "large", "articulated", "pass", "p1l", "rhl", false},
//                {"Any PSV", "Paid retest", "7", "small", "rigid", "pass", "p1l", "rps", false},
//                {"Any PSV", "Paid retest", "7", "large", "rigid", "pass", "p1l", "rpl", false},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "large", "rigid", "pass", "p1l", "whl", false},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "small", "rigid", "pass", "p1l", "whs", false},
//                {"Any PSV", "Part-paid retest", "10", "large", "articulated", "pass", "p1l", "rgl", false},
//                {"Any PSV", "Part-paid retest", "10", "large", "rigid", "pass", "p1l", "rsl", false},
//                {"Any PSV", "Part-paid retest", "10", "small", "rigid", "pass", "p1l", "rss", false},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "large", "rigid", "pass", "p1l", "p1l", false},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "small", "rigid", "pass", "p1l", "p1s", false},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "large", "rigid", "pass", "p1l", "p8l", false},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "small", "rigid", "pass", "p1l", "p8s", false},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "large", "rigid", "pass", "p1l", "p6l", false},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "small", "rigid", "pass", "p1l", "p6s", false},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "small", "rigid", "pass", "p1l", "wis", false},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "large", "rigid", "pass", "p1l", "wil", false},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "large", "rigid", "pass", "p1l", "wfl", false},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "small", "rigid", "pass", "p1l", "wfs", false},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "large", "rigid", "pass", "p1l", "wel", false},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "small", "rigid", "pass", "p1l", "wes", false},
//                {"Annual test", "Annual test", "1", "large", "rigid", "prs", "p1l", "aal", true},
//                {"Annual test", "Annual test", "1", "small", "rigid", "prs", "p1l", "aas", true},
//                {"Annual test", "Annual test", "1", "large", "articulated", "prs", "p1l", "adl", true},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "large", "rigid", "prs", "p1l", "wdl", true},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "small", "rigid", "prs", "p1l", "wds", true},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "large", "rigid", "prs", "p1l", "wbl", true},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "small", "rigid", "prs", "p1l", "wbs", true},
//                {"Any PSV", "Paid retest", "7", "large", "articulated", "prs", "p1l", "rhl", true},
//                {"Any PSV", "Paid retest", "7", "small", "rigid", "prs", "p1l", "rps", true},
//                {"Any PSV", "Paid retest", "7", "large", "rigid", "prs", "p1l", "rpl", true},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "large", "rigid", "prs", "p1l", "whl", true},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "small", "rigid", "prs", "p1l", "whs", true},
//                {"Any PSV", "Part-paid retest", "10", "large", "articulated", "prs", "p1l", "rgl", true},
//                {"Any PSV", "Part-paid retest", "10", "large", "rigid", "prs", "p1l", "rsl", true},
//                {"Any PSV", "Part-paid retest", "10", "small", "rigid", "prs", "p1l", "rss", true},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "large", "rigid", "prs", "p1l", "p1l", true},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "small", "rigid", "prs", "p1l", "p1s", true},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "large", "rigid", "prs", "p1l", "p8l", true},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "small", "rigid", "prs", "p1l", "p8s", true},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "large", "rigid", "prs", "p1l", "p6l", true},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "small", "rigid", "prs", "p1l", "p6s", true},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "small", "rigid", "prs", "p1l", "wis", true},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "large", "rigid", "prs", "p1l", "wil", true},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "large", "rigid", "prs", "p1l", "wfl", true},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "small", "rigid", "prs", "p1l", "wfs", true},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "large", "rigid", "prs", "p1l", "wel", true},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "small", "rigid", "prs", "p1l", "wes", true},
//                {"Annual test", "Annual test", "1", "large", "rigid", "prs", "p1l", "aal", false},
//                {"Annual test", "Annual test", "1", "small", "rigid", "prs", "p1l", "aas", false},
//                {"Annual test", "Annual test", "1", "large", "articulated", "prs", "p1l", "adl", false},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "large", "rigid", "prs", "p1l", "wdl", false},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "small", "rigid", "prs", "p1l", "wds", false},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "large", "rigid", "prs", "p1l", "wbl", false},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "small", "rigid", "prs", "p1l", "wbs", false},
//                {"Any PSV", "Paid retest", "7", "large", "articulated", "prs", "p1l", "rhl", false},
//                {"Any PSV", "Paid retest", "7", "small", "rigid", "prs", "p1l", "rps", false},
//                {"Any PSV", "Paid retest", "7", "large", "rigid", "prs", "p1l", "rpl", false},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "large", "rigid", "prs", "p1l", "whl", false},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "small", "rigid", "prs", "p1l", "whs", false},
//                {"Any PSV", "Part-paid retest", "10", "large", "articulated", "prs", "p1l", "rgl", false},
//                {"Any PSV", "Part-paid retest", "10", "large", "rigid", "prs", "p1l", "rsl", false},
//                {"Any PSV", "Part-paid retest", "10", "small", "rigid", "prs", "p1l", "rss", false},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "large", "rigid", "prs", "p1l", "p1l", false},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "small", "rigid", "prs", "p1l", "p1s", false},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "large", "rigid", "prs", "p1l", "p8l", false},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "small", "rigid", "prs", "p1l", "p8s", false},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "large", "rigid", "prs", "p1l", "p6l", false},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "small", "rigid", "prs", "p1l", "p6s", false},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "small", "rigid", "prs", "p1l", "wis", false},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "large", "rigid", "prs", "p1l", "wil", false},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "large", "rigid", "prs", "p1l", "wfl", false},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "small", "rigid", "prs", "p1l", "wfs", false},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "large", "rigid", "prs", "p1l", "wel", false},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "small", "rigid", "prs", "p1l", "wes", false},
//
//                {"Annual test", "Annual test", "1", "large", "rigid", "pass", "p1s", "aal", true},
//                {"Annual test", "Annual test", "1", "small", "rigid", "pass", "p1s", "aas", true},
//                {"Annual test", "Annual test", "1", "large", "articulated", "pass", "p1s", "adl", true},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "large", "rigid", "pass", "p1s", "wdl", true},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "small", "rigid", "pass", "p1s", "wds", true},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "large", "rigid", "pass", "p1s", "wbl", true},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "small", "rigid", "pass", "p1s", "wbs", true},
//                {"Any PSV", "Paid retest", "7", "large", "articulated", "pass", "p1s", "rhl", true},
//                {"Any PSV", "Paid retest", "7", "small", "rigid", "pass", "p1s", "rps", true},
//                {"Any PSV", "Paid retest", "7", "large", "rigid", "pass", "p1s", "rpl", true},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "large", "rigid", "pass", "p1s", "whl", true},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "small", "rigid", "pass", "p1s", "whs", true},
//                {"Any PSV", "Part-paid retest", "10", "large", "articulated", "pass", "p1s", "rgl", true},
//                {"Any PSV", "Part-paid retest", "10", "large", "rigid", "pass", "p1s", "rsl", true},
//                {"Any PSV", "Part-paid retest", "10", "small", "rigid", "pass", "p1s", "rss", true},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "large", "rigid", "pass", "p1s", "p1l", true},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "small", "rigid", "pass", "p1s", "p1s", true},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "large", "rigid", "pass", "p1s", "p8l", true},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "small", "rigid", "pass", "p1s", "p8s", true},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "large", "rigid", "pass", "p1s", "p6l", true},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "small", "rigid", "pass", "p1s", "p6s", true},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "small", "rigid", "pass", "p1s", "wis", true},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "large", "rigid", "pass", "p1s", "wil", true},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "large", "rigid", "pass", "p1s", "wfl", true},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "small", "rigid", "pass", "p1s", "wfs", true},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "large", "rigid", "pass", "p1s", "wel", true},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "small", "rigid", "pass", "p1s", "wes", true},
//                {"Annual test", "Annual test", "1", "large", "rigid", "pass", "p1s", "aal", false},
//                {"Annual test", "Annual test", "1", "small", "rigid", "pass", "p1s", "aas", false},
//                {"Annual test", "Annual test", "1", "large", "articulated", "pass", "p1s", "adl", false},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "large", "rigid", "pass", "p1s", "wdl", false},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "small", "rigid", "pass", "p1s", "wds", false},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "large", "rigid", "pass", "p1s", "wbl", false},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "small", "rigid", "pass", "p1s", "wbs", false},
//                {"Any PSV", "Paid retest", "7", "large", "articulated", "pass", "p1s", "rhl", false},
//                {"Any PSV", "Paid retest", "7", "small", "rigid", "pass", "p1s", "rps", false},
//                {"Any PSV", "Paid retest", "7", "large", "rigid", "pass", "p1s", "rpl", false},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "large", "rigid", "pass", "p1s", "whl", false},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "small", "rigid", "pass", "p1s", "whs", false},
//                {"Any PSV", "Part-paid retest", "10", "large", "articulated", "pass", "p1s", "rgl", false},
//                {"Any PSV", "Part-paid retest", "10", "large", "rigid", "pass", "p1s", "rsl", false},
//                {"Any PSV", "Part-paid retest", "10", "small", "rigid", "pass", "p1s", "rss", false},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "large", "rigid", "pass", "p1s", "p1l", false},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "small", "rigid", "pass", "p1s", "p1s", false},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "large", "rigid", "pass", "p1s", "p8l", false},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "small", "rigid", "pass", "p1s", "p8s", false},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "large", "rigid", "pass", "p1s", "p6l", false},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "small", "rigid", "pass", "p1s", "p6s", false},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "small", "rigid", "pass", "p1s", "wis", false},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "large", "rigid", "pass", "p1s", "wil", false},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "large", "rigid", "pass", "p1s", "wfl", false},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "small", "rigid", "pass", "p1s", "wfs", false},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "large", "rigid", "pass", "p1s", "wel", false},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "small", "rigid", "pass", "p1s", "wes", false},
//                {"Annual test", "Annual test", "1", "large", "rigid", "prs", "p1s", "aal", true},
//                {"Annual test", "Annual test", "1", "small", "rigid", "prs", "p1s", "aas", true},
//                {"Annual test", "Annual test", "1", "large", "articulated", "prs", "p1s", "adl", true},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "large", "rigid", "prs", "p1s", "wdl", true},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "small", "rigid", "prs", "p1s", "wds", true},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "large", "rigid", "prs", "p1s", "wbl", true},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "small", "rigid", "prs", "p1s", "wbs", true},
//                {"Any PSV", "Paid retest", "7", "large", "articulated", "prs", "p1s", "rhl", true},
//                {"Any PSV", "Paid retest", "7", "small", "rigid", "prs", "p1s", "rps", true},
//                {"Any PSV", "Paid retest", "7", "large", "rigid", "prs", "p1s", "rpl", true},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "large", "rigid", "prs", "p1s", "whl", true},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "small", "rigid", "prs", "p1s", "whs", true},
//                {"Any PSV", "Part-paid retest", "10", "large", "articulated", "prs", "p1s", "rgl", true},
//                {"Any PSV", "Part-paid retest", "10", "large", "rigid", "prs", "p1s", "rsl", true},
//                {"Any PSV", "Part-paid retest", "10", "small", "rigid", "prs", "p1s", "rss", true},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "large", "rigid", "prs", "p1s", "p1l", true},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "small", "rigid", "prs", "p1s", "p1s", true},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "large", "rigid", "prs", "p1s", "p8l", true},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "small", "rigid", "prs", "p1s", "p8s", true},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "large", "rigid", "prs", "p1s", "p6l", true},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "small", "rigid", "prs", "p1s", "p6s", true},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "small", "rigid", "prs", "p1s", "wis", true},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "large", "rigid", "prs", "p1s", "wil", true},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "large", "rigid", "prs", "p1s", "wfl", true},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "small", "rigid", "prs", "p1s", "wfs", true},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "large", "rigid", "prs", "p1s", "wel", true},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "small", "rigid", "prs", "p1s", "wes", true},
//                {"Annual test", "Annual test", "1", "large", "rigid", "prs", "p1s", "aal", false},
//                {"Annual test", "Annual test", "1", "small", "rigid", "prs", "p1s", "aas", false},
//                {"Annual test", "Annual test", "1", "large", "articulated", "prs", "p1s", "adl", false},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "large", "rigid", "prs", "p1s", "wdl", false},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "small", "rigid", "prs", "p1s", "wds", false},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "large", "rigid", "prs", "p1s", "wbl", false},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "small", "rigid", "prs", "p1s", "wbs", false},
//                {"Any PSV", "Paid retest", "7", "large", "articulated", "prs", "p1s", "rhl", false},
//                {"Any PSV", "Paid retest", "7", "small", "rigid", "prs", "p1s", "rps", false},
//                {"Any PSV", "Paid retest", "7", "large", "rigid", "prs", "p1s", "rpl", false},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "large", "rigid", "prs", "p1s", "whl", false},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "small", "rigid", "prs", "p1s", "whs", false},
//                {"Any PSV", "Part-paid retest", "10", "large", "articulated", "prs", "p1s", "rgl", false},
//                {"Any PSV", "Part-paid retest", "10", "large", "rigid", "prs", "p1s", "rsl", false},
//                {"Any PSV", "Part-paid retest", "10", "small", "rigid", "prs", "p1s", "rss", false},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "large", "rigid", "prs", "p1s", "p1l", false},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "small", "rigid", "prs", "p1s", "p1s", false},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "large", "rigid", "prs", "p1s", "p8l", false},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "small", "rigid", "prs", "p1s", "p8s", false},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "large", "rigid", "prs", "p1s", "p6l", false},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "small", "rigid", "prs", "p1s", "p6s", false},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "small", "rigid", "prs", "p1s", "wis", false},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "large", "rigid", "prs", "p1s", "wil", false},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "large", "rigid", "prs", "p1s", "wfl", false},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "small", "rigid", "prs", "p1s", "wfs", false},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "large", "rigid", "prs", "p1s", "wel", false},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "small", "rigid", "prs", "p1s", "wes", false},
//
//                {"Annual test", "Annual test", "1", "large", "rigid", "pass", "p6l", "aal", true},
//                {"Annual test", "Annual test", "1", "small", "rigid", "pass", "p6l", "aas", true},
//                {"Annual test", "Annual test", "1", "large", "articulated", "pass", "p6l", "adl", true},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "large", "rigid", "pass", "p6l", "wdl", true},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "small", "rigid", "pass", "p6l", "wds", true},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "large", "rigid", "pass", "p6l", "wbl", true},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "small", "rigid", "pass", "p6l", "wbs", true},
//                {"Any PSV", "Paid retest", "7", "large", "articulated", "pass", "p6l", "rhl", true},
//                {"Any PSV", "Paid retest", "7", "small", "rigid", "pass", "p6l", "rps", true},
//                {"Any PSV", "Paid retest", "7", "large", "rigid", "pass", "p6l", "rpl", true},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "large", "rigid", "pass", "p6l", "whl", true},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "small", "rigid", "pass", "p6l", "whs", true},
//                {"Any PSV", "Part-paid retest", "10", "large", "articulated", "pass", "p6l", "rgl", true},
//                {"Any PSV", "Part-paid retest", "10", "large", "rigid", "pass", "p6l", "rsl", true},
//                {"Any PSV", "Part-paid retest", "10", "small", "rigid", "pass", "p6l", "rss", true},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "large", "rigid", "pass", "p6l", "p1l", true},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "small", "rigid", "pass", "p6l", "p1s", true},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "large", "rigid", "pass", "p6l", "p8l", true},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "small", "rigid", "pass", "p6l", "p8s", true},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "large", "rigid", "pass", "p6l", "p6l", true},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "small", "rigid", "pass", "p6l", "p6s", true},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "small", "rigid", "pass", "p6l", "wis", true},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "large", "rigid", "pass", "p6l", "wil", true},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "large", "rigid", "pass", "p6l", "wfl", true},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "small", "rigid", "pass", "p6l", "wfs", true},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "large", "rigid", "pass", "p6l", "wel", true},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "small", "rigid", "pass", "p6l", "wes", true},
//                {"Annual test", "Annual test", "1", "large", "rigid", "pass", "p6l", "aal", false},
//                {"Annual test", "Annual test", "1", "small", "rigid", "pass", "p6l", "aas", false},
//                {"Annual test", "Annual test", "1", "large", "articulated", "pass", "p6l", "adl", false},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "large", "rigid", "pass", "p6l", "wdl", false},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "small", "rigid", "pass", "p6l", "wds", false},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "large", "rigid", "pass", "p6l", "wbl", false},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "small", "rigid", "pass", "p6l", "wbs", false},
//                {"Any PSV", "Paid retest", "7", "large", "articulated", "pass", "p6l", "rhl", false},
//                {"Any PSV", "Paid retest", "7", "small", "rigid", "pass", "p6l", "rps", false},
//                {"Any PSV", "Paid retest", "7", "large", "rigid", "pass", "p6l", "rpl", false},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "large", "rigid", "pass", "p6l", "whl", false},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "small", "rigid", "pass", "p6l", "whs", false},
//                {"Any PSV", "Part-paid retest", "10", "large", "articulated", "pass", "p6l", "rgl", false},
//                {"Any PSV", "Part-paid retest", "10", "large", "rigid", "pass", "p6l", "rsl", false},
//                {"Any PSV", "Part-paid retest", "10", "small", "rigid", "pass", "p6l", "rss", false},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "large", "rigid", "pass", "p6l", "p1l", false},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "small", "rigid", "pass", "p6l", "p1s", false},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "large", "rigid", "pass", "p6l", "p8l", false},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "small", "rigid", "pass", "p6l", "p8s", false},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "large", "rigid", "pass", "p6l", "p6l", false},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "small", "rigid", "pass", "p6l", "p6s", false},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "small", "rigid", "pass", "p6l", "wis", false},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "large", "rigid", "pass", "p6l", "wil", false},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "large", "rigid", "pass", "p6l", "wfl", false},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "small", "rigid", "pass", "p6l", "wfs", false},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "large", "rigid", "pass", "p6l", "wel", false},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "small", "rigid", "pass", "p6l", "wes", false},
//                {"Annual test", "Annual test", "1", "large", "rigid", "prs", "p6l", "aal", true},
//                {"Annual test", "Annual test", "1", "small", "rigid", "prs", "p6l", "aas", true},
//                {"Annual test", "Annual test", "1", "large", "articulated", "prs", "p6l", "adl", true},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "large", "rigid", "prs", "p6l", "wdl", true},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "small", "rigid", "prs", "p6l", "wds", true},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "large", "rigid", "prs", "p6l", "wbl", true},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "small", "rigid", "prs", "p6l", "wbs", true},
//                {"Any PSV", "Paid retest", "7", "large", "articulated", "prs", "p6l", "rhl", true},
//                {"Any PSV", "Paid retest", "7", "small", "rigid", "prs", "p6l", "rps", true},
//                {"Any PSV", "Paid retest", "7", "large", "rigid", "prs", "p6l", "rpl", true},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "large", "rigid", "prs", "p6l", "whl", true},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "small", "rigid", "prs", "p6l", "whs", true},
//                {"Any PSV", "Part-paid retest", "10", "large", "articulated", "prs", "p6l", "rgl", true},
//                {"Any PSV", "Part-paid retest", "10", "large", "rigid", "prs", "p6l", "rsl", true},
//                {"Any PSV", "Part-paid retest", "10", "small", "rigid", "prs", "p6l", "rss", true},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "large", "rigid", "prs", "p6l", "p1l", true},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "small", "rigid", "prs", "p6l", "p1s", true},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "large", "rigid", "prs", "p6l", "p8l", true},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "small", "rigid", "prs", "p6l", "p8s", true},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "large", "rigid", "prs", "p6l", "p6l", true},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "small", "rigid", "prs", "p6l", "p6s", true},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "small", "rigid", "prs", "p6l", "wis", true},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "large", "rigid", "prs", "p6l", "wil", true},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "large", "rigid", "prs", "p6l", "wfl", true},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "small", "rigid", "prs", "p6l", "wfs", true},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "large", "rigid", "prs", "p6l", "wel", true},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "small", "rigid", "prs", "p6l", "wes", true},
//                {"Annual test", "Annual test", "1", "large", "rigid", "prs", "p6l", "aal", false},
//                {"Annual test", "Annual test", "1", "small", "rigid", "prs", "p6l", "aas", false},
//                {"Annual test", "Annual test", "1", "large", "articulated", "prs", "p6l", "adl", false},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "large", "rigid", "prs", "p6l", "wdl", false},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "small", "rigid", "prs", "p6l", "wds", false},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "large", "rigid", "prs", "p6l", "wbl", false},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "small", "rigid", "prs", "p6l", "wbs", false},
//                {"Any PSV", "Paid retest", "7", "large", "articulated", "prs", "p6l", "rhl", false},
//                {"Any PSV", "Paid retest", "7", "small", "rigid", "prs", "p6l", "rps", false},
//                {"Any PSV", "Paid retest", "7", "large", "rigid", "prs", "p6l", "rpl", false},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "large", "rigid", "prs", "p6l", "whl", false},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "small", "rigid", "prs", "p6l", "whs", false},
//                {"Any PSV", "Part-paid retest", "10", "large", "articulated", "prs", "p6l", "rgl", false},
//                {"Any PSV", "Part-paid retest", "10", "large", "rigid", "prs", "p6l", "rsl", false},
//                {"Any PSV", "Part-paid retest", "10", "small", "rigid", "prs", "p6l", "rss", false},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "large", "rigid", "prs", "p6l", "p1l", false},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "small", "rigid", "prs", "p6l", "p1s", false},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "large", "rigid", "prs", "p6l", "p8l", false},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "small", "rigid", "prs", "p6l", "p8s", false},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "large", "rigid", "prs", "p6l", "p6l", false},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "small", "rigid", "prs", "p6l", "p6s", false},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "small", "rigid", "prs", "p6l", "wis", false},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "large", "rigid", "prs", "p6l", "wil", false},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "large", "rigid", "prs", "p6l", "wfl", false},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "small", "rigid", "prs", "p6l", "wfs", false},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "large", "rigid", "prs", "p6l", "wel", false},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "small", "rigid", "prs", "p6l", "wes", false},
//
//                {"Annual test", "Annual test", "1", "large", "rigid", "pass", "p6s", "aal", true},
//                {"Annual test", "Annual test", "1", "small", "rigid", "pass", "p6s", "aas", true},
//                {"Annual test", "Annual test", "1", "large", "articulated", "pass", "p6s", "adl", true},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "large", "rigid", "pass", "p6s", "wdl", true},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "small", "rigid", "pass", "p6s", "wds", true},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "large", "rigid", "pass", "p6s", "wbl", true},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "small", "rigid", "pass", "p6s", "wbs", true},
//                {"Any PSV", "Paid retest", "7", "large", "articulated", "pass", "p6s", "rhl", true},
//                {"Any PSV", "Paid retest", "7", "small", "rigid", "pass", "p6s", "rps", true},
//                {"Any PSV", "Paid retest", "7", "large", "rigid", "pass", "p6s", "rpl", true},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "large", "rigid", "pass", "p6s", "whl", true},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "small", "rigid", "pass", "p6s", "whs", true},
//                {"Any PSV", "Part-paid retest", "10", "large", "articulated", "pass", "p6s", "rgl", true},
//                {"Any PSV", "Part-paid retest", "10", "large", "rigid", "pass", "p6s", "rsl", true},
//                {"Any PSV", "Part-paid retest", "10", "small", "rigid", "pass", "p6s", "rss", true},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "large", "rigid", "pass", "p6s", "p1l", true},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "small", "rigid", "pass", "p6s", "p1s", true},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "large", "rigid", "pass", "p6s", "p8l", true},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "small", "rigid", "pass", "p6s", "p8s", true},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "large", "rigid", "pass", "p6s", "p6l", true},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "small", "rigid", "pass", "p6s", "p6s", true},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "small", "rigid", "pass", "p6s", "wis", true},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "large", "rigid", "pass", "p6s", "wil", true},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "large", "rigid", "pass", "p6s", "wfl", true},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "small", "rigid", "pass", "p6s", "wfs", true},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "large", "rigid", "pass", "p6s", "wel", true},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "small", "rigid", "pass", "p6s", "wes", true},
//                {"Annual test", "Annual test", "1", "large", "rigid", "pass", "p6s", "aal", false},
//                {"Annual test", "Annual test", "1", "small", "rigid", "pass", "p6s", "aas", false},
//                {"Annual test", "Annual test", "1", "large", "articulated", "pass", "p6s", "adl", false},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "large", "rigid", "pass", "p6s", "wdl", false},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "small", "rigid", "pass", "p6s", "wds", false},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "large", "rigid", "pass", "p6s", "wbl", false},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "small", "rigid", "pass", "p6s", "wbs", false},
//                {"Any PSV", "Paid retest", "7", "large", "articulated", "pass", "p6s", "rhl", false},
//                {"Any PSV", "Paid retest", "7", "small", "rigid", "pass", "p6s", "rps", false},
//                {"Any PSV", "Paid retest", "7", "large", "rigid", "pass", "p6s", "rpl", false},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "large", "rigid", "pass", "p6s", "whl", false},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "small", "rigid", "pass", "p6s", "whs", false},
//                {"Any PSV", "Part-paid retest", "10", "large", "articulated", "pass", "p6s", "rgl", false},
//                {"Any PSV", "Part-paid retest", "10", "large", "rigid", "pass", "p6s", "rsl", false},
//                {"Any PSV", "Part-paid retest", "10", "small", "rigid", "pass", "p6s", "rss", false},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "large", "rigid", "pass", "p6s", "p1l", false},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "small", "rigid", "pass", "p6s", "p1s", false},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "large", "rigid", "pass", "p6s", "p8l", false},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "small", "rigid", "pass", "p6s", "p8s", false},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "large", "rigid", "pass", "p6s", "p6l", false},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "small", "rigid", "pass", "p6s", "p6s", false},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "small", "rigid", "pass", "p6s", "wis", false},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "large", "rigid", "pass", "p6s", "wil", false},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "large", "rigid", "pass", "p6s", "wfl", false},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "small", "rigid", "pass", "p6s", "wfs", false},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "large", "rigid", "pass", "p6s", "wel", false},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "small", "rigid", "pass", "p6s", "wes", false},
//                {"Annual test", "Annual test", "1", "large", "rigid", "prs", "p6s", "aal", true},
//                {"Annual test", "Annual test", "1", "small", "rigid", "prs", "p6s", "aas", true},
//                {"Annual test", "Annual test", "1", "large", "articulated", "prs", "p6s", "adl", true},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "large", "rigid", "prs", "p6s", "wdl", true},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "small", "rigid", "prs", "p6s", "wds", true},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "large", "rigid", "prs", "p6s", "wbl", true},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "small", "rigid", "prs", "p6s", "wbs", true},
//                {"Any PSV", "Paid retest", "7", "large", "articulated", "prs", "p6s", "rhl", true},
//                {"Any PSV", "Paid retest", "7", "small", "rigid", "prs", "p6s", "rps", true},
//                {"Any PSV", "Paid retest", "7", "large", "rigid", "prs", "p6s", "rpl", true},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "large", "rigid", "prs", "p6s", "whl", true},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "small", "rigid", "prs", "p6s", "whs", true},
//                {"Any PSV", "Part-paid retest", "10", "large", "articulated", "prs", "p6s", "rgl", true},
//                {"Any PSV", "Part-paid retest", "10", "large", "rigid", "prs", "p6s", "rsl", true},
//                {"Any PSV", "Part-paid retest", "10", "small", "rigid", "prs", "p6s", "rss", true},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "large", "rigid", "prs", "p6s", "p1l", true},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "small", "rigid", "prs", "p6s", "p1s", true},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "large", "rigid", "prs", "p6s", "p8l", true},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "small", "rigid", "prs", "p6s", "p8s", true},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "large", "rigid", "prs", "p6s", "p6l", true},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "small", "rigid", "prs", "p6s", "p6s", true},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "small", "rigid", "prs", "p6s", "wis", true},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "large", "rigid", "prs", "p6s", "wil", true},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "large", "rigid", "prs", "p6s", "wfl", true},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "small", "rigid", "prs", "p6s", "wfs", true},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "large", "rigid", "prs", "p6s", "wel", true},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "small", "rigid", "prs", "p6s", "wes", true},
//                {"Annual test", "Annual test", "1", "large", "rigid", "prs", "p6s", "aal", false},
//                {"Annual test", "Annual test", "1", "small", "rigid", "prs", "p6s", "aas", false},
//                {"Annual test", "Annual test", "1", "large", "articulated", "prs", "p6s", "adl", false},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "large", "rigid", "prs", "p6s", "wdl", false},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "small", "rigid", "prs", "p6s", "wds", false},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "large", "rigid", "prs", "p6s", "wbl", false},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "small", "rigid", "prs", "p6s", "wbs", false},
//                {"Any PSV", "Paid retest", "7", "large", "articulated", "prs", "p6s", "rhl", false},
//                {"Any PSV", "Paid retest", "7", "small", "rigid", "prs", "p6s", "rps", false},
//                {"Any PSV", "Paid retest", "7", "large", "rigid", "prs", "p6s", "rpl", false},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "large", "rigid", "prs", "p6s", "whl", false},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "small", "rigid", "prs", "p6s", "whs", false},
//                {"Any PSV", "Part-paid retest", "10", "large", "articulated", "prs", "p6s", "rgl", false},
//                {"Any PSV", "Part-paid retest", "10", "large", "rigid", "prs", "p6s", "rsl", false},
//                {"Any PSV", "Part-paid retest", "10", "small", "rigid", "prs", "p6s", "rss", false},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "large", "rigid", "prs", "p6s", "p1l", false},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "small", "rigid", "prs", "p6s", "p1s", false},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "large", "rigid", "prs", "p6s", "p8l", false},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "small", "rigid", "prs", "p6s", "p8s", false},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "large", "rigid", "prs", "p6s", "p6l", false},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "small", "rigid", "prs", "p6s", "p6s", false},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "small", "rigid", "prs", "p6s", "wis", false},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "large", "rigid", "prs", "p6s", "wil", false},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "large", "rigid", "prs", "p6s", "wfl", false},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "small", "rigid", "prs", "p6s", "wfs", false},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "large", "rigid", "prs", "p6s", "wel", false},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "small", "rigid", "prs", "p6s", "wes", false},
//
//                {"Annual test", "Annual test", "1", "large", "rigid", "pass", "p8l", "aal", true},
//                {"Annual test", "Annual test", "1", "small", "rigid", "pass", "p8l", "aas", true},
//                {"Annual test", "Annual test", "1", "large", "articulated", "pass", "p8l", "adl", true},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "large", "rigid", "pass", "p8l", "wdl", true},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "small", "rigid", "pass", "p8l", "wds", true},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "large", "rigid", "pass", "p8l", "wbl", true},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "small", "rigid", "pass", "p8l", "wbs", true},
//                {"Any PSV", "Paid retest", "7", "large", "articulated", "pass", "p8l", "rhl", true},
//                {"Any PSV", "Paid retest", "7", "small", "rigid", "pass", "p8l", "rps", true},
//                {"Any PSV", "Paid retest", "7", "large", "rigid", "pass", "p8l", "rpl", true},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "large", "rigid", "pass", "p8l", "whl", true},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "small", "rigid", "pass", "p8l", "whs", true},
//                {"Any PSV", "Part-paid retest", "10", "large", "articulated", "pass", "p8l", "rgl", true},
//                {"Any PSV", "Part-paid retest", "10", "large", "rigid", "pass", "p8l", "rsl", true},
//                {"Any PSV", "Part-paid retest", "10", "small", "rigid", "pass", "p8l", "rss", true},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "large", "rigid", "pass", "p8l", "p1l", true},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "small", "rigid", "pass", "p8l", "p1s", true},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "large", "rigid", "pass", "p8l", "p8l", true},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "small", "rigid", "pass", "p8l", "p8s", true},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "large", "rigid", "pass", "p8l", "p6l", true},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "small", "rigid", "pass", "p8l", "p6s", true},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "small", "rigid", "pass", "p8l", "wis", true},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "large", "rigid", "pass", "p8l", "wil", true},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "large", "rigid", "pass", "p8l", "wfl", true},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "small", "rigid", "pass", "p8l", "wfs", true},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "large", "rigid", "pass", "p8l", "wel", true},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "small", "rigid", "pass", "p8l", "wes", true},
//                {"Annual test", "Annual test", "1", "large", "rigid", "pass", "p8l", "aal", false},
//                {"Annual test", "Annual test", "1", "small", "rigid", "pass", "p8l", "aas", false},
//                {"Annual test", "Annual test", "1", "large", "articulated", "pass", "p8l", "adl", false},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "large", "rigid", "pass", "p8l", "wdl", false},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "small", "rigid", "pass", "p8l", "wds", false},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "large", "rigid", "pass", "p8l", "wbl", false},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "small", "rigid", "pass", "p8l", "wbs", false},
//                {"Any PSV", "Paid retest", "7", "large", "articulated", "pass", "p8l", "rhl", false},
//                {"Any PSV", "Paid retest", "7", "small", "rigid", "pass", "p8l", "rps", false},
//                {"Any PSV", "Paid retest", "7", "large", "rigid", "pass", "p8l", "rpl", false},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "large", "rigid", "pass", "p8l", "whl", false},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "small", "rigid", "pass", "p8l", "whs", false},
//                {"Any PSV", "Part-paid retest", "10", "large", "articulated", "pass", "p8l", "rgl", false},
//                {"Any PSV", "Part-paid retest", "10", "large", "rigid", "pass", "p8l", "rsl", false},
//                {"Any PSV", "Part-paid retest", "10", "small", "rigid", "pass", "p8l", "rss", false},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "large", "rigid", "pass", "p8l", "p1l", false},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "small", "rigid", "pass", "p8l", "p1s", false},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "large", "rigid", "pass", "p8l", "p8l", false},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "small", "rigid", "pass", "p8l", "p8s", false},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "large", "rigid", "pass", "p8l", "p6l", false},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "small", "rigid", "pass", "p8l", "p6s", false},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "small", "rigid", "pass", "p8l", "wis", false},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "large", "rigid", "pass", "p8l", "wil", false},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "large", "rigid", "pass", "p8l", "wfl", false},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "small", "rigid", "pass", "p8l", "wfs", false},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "large", "rigid", "pass", "p8l", "wel", false},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "small", "rigid", "pass", "p8l", "wes", false},
//                {"Annual test", "Annual test", "1", "large", "rigid", "prs", "p8l", "aal", true},
//                {"Annual test", "Annual test", "1", "small", "rigid", "prs", "p8l", "aas", true},
//                {"Annual test", "Annual test", "1", "large", "articulated", "prs", "p8l", "adl", true},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "large", "rigid", "prs", "p8l", "wdl", true},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "small", "rigid", "prs", "p8l", "wds", true},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "large", "rigid", "prs", "p8l", "wbl", true},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "small", "rigid", "prs", "p8l", "wbs", true},
//                {"Any PSV", "Paid retest", "7", "large", "articulated", "prs", "p8l", "rhl", true},
//                {"Any PSV", "Paid retest", "7", "small", "rigid", "prs", "p8l", "rps", true},
//                {"Any PSV", "Paid retest", "7", "large", "rigid", "prs", "p8l", "rpl", true},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "large", "rigid", "prs", "p8l", "whl", true},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "small", "rigid", "prs", "p8l", "whs", true},
//                {"Any PSV", "Part-paid retest", "10", "large", "articulated", "prs", "p8l", "rgl", true},
//                {"Any PSV", "Part-paid retest", "10", "large", "rigid", "prs", "p8l", "rsl", true},
//                {"Any PSV", "Part-paid retest", "10", "small", "rigid", "prs", "p8l", "rss", true},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "large", "rigid", "prs", "p8l", "p1l", true},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "small", "rigid", "prs", "p8l", "p1s", true},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "large", "rigid", "prs", "p8l", "p8l", true},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "small", "rigid", "prs", "p8l", "p8s", true},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "large", "rigid", "prs", "p8l", "p6l", true},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "small", "rigid", "prs", "p8l", "p6s", true},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "small", "rigid", "prs", "p8l", "wis", true},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "large", "rigid", "prs", "p8l", "wil", true},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "large", "rigid", "prs", "p8l", "wfl", true},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "small", "rigid", "prs", "p8l", "wfs", true},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "large", "rigid", "prs", "p8l", "wel", true},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "small", "rigid", "prs", "p8l", "wes", true},
//                {"Annual test", "Annual test", "1", "large", "rigid", "prs", "p8l", "aal", false},
//                {"Annual test", "Annual test", "1", "small", "rigid", "prs", "p8l", "aas", false},
//                {"Annual test", "Annual test", "1", "large", "articulated", "prs", "p8l", "adl", false},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "large", "rigid", "prs", "p8l", "wdl", false},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "small", "rigid", "prs", "p8l", "wds", false},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "large", "rigid", "prs", "p8l", "wbl", false},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "small", "rigid", "prs", "p8l", "wbs", false},
//                {"Any PSV", "Paid retest", "7", "large", "articulated", "prs", "p8l", "rhl", false},
//                {"Any PSV", "Paid retest", "7", "small", "rigid", "prs", "p8l", "rps", false},
//                {"Any PSV", "Paid retest", "7", "large", "rigid", "prs", "p8l", "rpl", false},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "large", "rigid", "prs", "p8l", "whl", false},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "small", "rigid", "prs", "p8l", "whs", false},
//                {"Any PSV", "Part-paid retest", "10", "large", "articulated", "prs", "p8l", "rgl", false},
//                {"Any PSV", "Part-paid retest", "10", "large", "rigid", "prs", "p8l", "rsl", false},
//                {"Any PSV", "Part-paid retest", "10", "small", "rigid", "prs", "p8l", "rss", false},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "large", "rigid", "prs", "p8l", "p1l", false},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "small", "rigid", "prs", "p8l", "p1s", false},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "large", "rigid", "prs", "p8l", "p8l", false},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "small", "rigid", "prs", "p8l", "p8s", false},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "large", "rigid", "prs", "p8l", "p6l", false},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "small", "rigid", "prs", "p8l", "p6s", false},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "small", "rigid", "prs", "p8l", "wis", false},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "large", "rigid", "prs", "p8l", "wil", false},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "large", "rigid", "prs", "p8l", "wfl", false},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "small", "rigid", "prs", "p8l", "wfs", false},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "large", "rigid", "prs", "p8l", "wel", false},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "small", "rigid", "prs", "p8l", "wes", false},
//
//                {"Annual test", "Annual test", "1", "large", "rigid", "pass", "p8s", "aal", true},
//                {"Annual test", "Annual test", "1", "small", "rigid", "pass", "p8s", "aas", true},
//                {"Annual test", "Annual test", "1", "large", "articulated", "pass", "p8s", "adl", true},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "large", "rigid", "pass", "p8s", "wdl", true},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "small", "rigid", "pass", "p8s", "wds", true},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "large", "rigid", "pass", "p8s", "wbl", true},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "small", "rigid", "pass", "p8s", "wbs", true},
//                {"Any PSV", "Paid retest", "7", "large", "articulated", "pass", "p8s", "rhl", true},
//                {"Any PSV", "Paid retest", "7", "small", "rigid", "pass", "p8s", "rps", true},
//                {"Any PSV", "Paid retest", "7", "large", "rigid", "pass", "p8s", "rpl", true},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "large", "rigid", "pass", "p8s", "whl", true},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "small", "rigid", "pass", "p8s", "whs", true},
//                {"Any PSV", "Part-paid retest", "10", "large", "articulated", "pass", "p8s", "rgl", true},
//                {"Any PSV", "Part-paid retest", "10", "large", "rigid", "pass", "p8s", "rsl", true},
//                {"Any PSV", "Part-paid retest", "10", "small", "rigid", "pass", "p8s", "rss", true},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "large", "rigid", "pass", "p8s", "p1l", true},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "small", "rigid", "pass", "p8s", "p1s", true},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "large", "rigid", "pass", "p8s", "p8l", true},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "small", "rigid", "pass", "p8s", "p8s", true},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "large", "rigid", "pass", "p8s", "p6l", true},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "small", "rigid", "pass", "p8s", "p6s", true},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "small", "rigid", "pass", "p8s", "wis", true},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "large", "rigid", "pass", "p8s", "wil", true},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "large", "rigid", "pass", "p8s", "wfl", true},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "small", "rigid", "pass", "p8s", "wfs", true},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "large", "rigid", "pass", "p8s", "wel", true},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "small", "rigid", "pass", "p8s", "wes", true},
//                {"Annual test", "Annual test", "1", "large", "rigid", "pass", "p8s", "aal", false},
//                {"Annual test", "Annual test", "1", "small", "rigid", "pass", "p8s", "aas", false},
//                {"Annual test", "Annual test", "1", "large", "articulated", "pass", "p8s", "adl", false},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "large", "rigid", "pass", "p8s", "wdl", false},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "small", "rigid", "pass", "p8s", "wds", false},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "large", "rigid", "pass", "p8s", "wbl", false},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "small", "rigid", "pass", "p8s", "wbs", false},
//                {"Any PSV", "Paid retest", "7", "large", "articulated", "pass", "p8s", "rhl", false},
//                {"Any PSV", "Paid retest", "7", "small", "rigid", "pass", "p8s", "rps", false},
//                {"Any PSV", "Paid retest", "7", "large", "rigid", "pass", "p8s", "rpl", false},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "large", "rigid", "pass", "p8s", "whl", false},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "small", "rigid", "pass", "p8s", "whs", false},
//                {"Any PSV", "Part-paid retest", "10", "large", "articulated", "pass", "p8s", "rgl", false},
//                {"Any PSV", "Part-paid retest", "10", "large", "rigid", "pass", "p8s", "rsl", false},
//                {"Any PSV", "Part-paid retest", "10", "small", "rigid", "pass", "p8s", "rss", false},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "large", "rigid", "pass", "p8s", "p1l", false},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "small", "rigid", "pass", "p8s", "p1s", false},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "large", "rigid", "pass", "p8s", "p8l", false},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "small", "rigid", "pass", "p8s", "p8s", false},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "large", "rigid", "pass", "p8s", "p6l", false},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "small", "rigid", "pass", "p8s", "p6s", false},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "small", "rigid", "pass", "p8s", "wis", false},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "large", "rigid", "pass", "p8s", "wil", false},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "large", "rigid", "pass", "p8s", "wfl", false},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "small", "rigid", "pass", "p8s", "wfs", false},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "large", "rigid", "pass", "p8s", "wel", false},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "small", "rigid", "pass", "p8s", "wes", false},
//                {"Annual test", "Annual test", "1", "large", "rigid", "prs", "p8s", "aal", true},
//                {"Annual test", "Annual test", "1", "small", "rigid", "prs", "p8s", "aas", true},
//                {"Annual test", "Annual test", "1", "large", "articulated", "prs", "p8s", "adl", true},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "large", "rigid", "prs", "p8s", "wdl", true},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "small", "rigid", "prs", "p8s", "wds", true},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "large", "rigid", "prs", "p8s", "wbl", true},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "small", "rigid", "prs", "p8s", "wbs", true},
//                {"Any PSV", "Paid retest", "7", "large", "articulated", "prs", "p8s", "rhl", true},
//                {"Any PSV", "Paid retest", "7", "small", "rigid", "prs", "p8s", "rps", true},
//                {"Any PSV", "Paid retest", "7", "large", "rigid", "prs", "p8s", "rpl", true},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "large", "rigid", "prs", "p8s", "whl", true},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "small", "rigid", "prs", "p8s", "whs", true},
//                {"Any PSV", "Part-paid retest", "10", "large", "articulated", "prs", "p8s", "rgl", true},
//                {"Any PSV", "Part-paid retest", "10", "large", "rigid", "prs", "p8s", "rsl", true},
//                {"Any PSV", "Part-paid retest", "10", "small", "rigid", "prs", "p8s", "rss", true},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "large", "rigid", "prs", "p8s", "p1l", true},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "small", "rigid", "prs", "p8s", "p1s", true},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "large", "rigid", "prs", "p8s", "p8l", true},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "small", "rigid", "prs", "p8s", "p8s", true},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "large", "rigid", "prs", "p8s", "p6l", true},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "small", "rigid", "prs", "p8s", "p6s", true},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "small", "rigid", "prs", "p8s", "wis", true},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "large", "rigid", "prs", "p8s", "wil", true},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "large", "rigid", "prs", "p8s", "wfl", true},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "small", "rigid", "prs", "p8s", "wfs", true},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "large", "rigid", "prs", "p8s", "wel", true},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "small", "rigid", "prs", "p8s", "wes", true},
//                {"Annual test", "Annual test", "1", "large", "rigid", "prs", "p8s", "aal", false},
//                {"Annual test", "Annual test", "1", "small", "rigid", "prs", "p8s", "aas", false},
//                {"Annual test", "Annual test", "1", "large", "articulated", "prs", "p8s", "adl", false},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "large", "rigid", "prs", "p8s", "wdl", false},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "small", "rigid", "prs", "p8s", "wds", false},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "large", "rigid", "prs", "p8s", "wbl", false},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "small", "rigid", "prs", "p8s", "wbs", false},
//                {"Any PSV", "Paid retest", "7", "large", "articulated", "prs", "p8s", "rhl", false},
//                {"Any PSV", "Paid retest", "7", "small", "rigid", "prs", "p8s", "rps", false},
//                {"Any PSV", "Paid retest", "7", "large", "rigid", "prs", "p8s", "rpl", false},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "large", "rigid", "prs", "p8s", "whl", false},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "small", "rigid", "prs", "p8s", "whs", false},
//                {"Any PSV", "Part-paid retest", "10", "large", "articulated", "prs", "p8s", "rgl", false},
//                {"Any PSV", "Part-paid retest", "10", "large", "rigid", "prs", "p8s", "rsl", false},
//                {"Any PSV", "Part-paid retest", "10", "small", "rigid", "prs", "p8s", "rss", false},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "large", "rigid", "prs", "p8s", "p1l", false},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "small", "rigid", "prs", "p8s", "p1s", false},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "large", "rigid", "prs", "p8s", "p8l", false},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "small", "rigid", "prs", "p8s", "p8s", false},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "large", "rigid", "prs", "p8s", "p6l", false},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "small", "rigid", "prs", "p8s", "p6s", false},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "small", "rigid", "prs", "p8s", "wis", false},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "large", "rigid", "prs", "p8s", "wil", false},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "large", "rigid", "prs", "p8s", "wfl", false},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "small", "rigid", "prs", "p8s", "wfs", false},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "large", "rigid", "prs", "p8s", "wel", false},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "small", "rigid", "prs", "p8s", "wes", false},
//
//                {"Annual test", "Annual test", "1", "large", "rigid", "pass", "rgl", "aal", true},
//                {"Annual test", "Annual test", "1", "small", "rigid", "pass", "rgl", "aas", true},
//                {"Annual test", "Annual test", "1", "large", "articulated", "pass", "rgl", "adl", true},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "large", "rigid", "pass", "rgl", "wdl", true},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "small", "rigid", "pass", "rgl", "wds", true},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "large", "rigid", "pass", "rgl", "wbl", true},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "small", "rigid", "pass", "rgl", "wbs", true},
//                {"Any PSV", "Paid retest", "7", "large", "articulated", "pass", "rgl", "rhl", true},
//                {"Any PSV", "Paid retest", "7", "small", "rigid", "pass", "rgl", "rps", true},
//                {"Any PSV", "Paid retest", "7", "large", "rigid", "pass", "rgl", "rpl", true},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "large", "rigid", "pass", "rgl", "whl", true},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "small", "rigid", "pass", "rgl", "whs", true},
//                {"Any PSV", "Part-paid retest", "10", "large", "articulated", "pass", "rgl", "rgl", true},
//                {"Any PSV", "Part-paid retest", "10", "large", "rigid", "pass", "rgl", "rsl", true},
//                {"Any PSV", "Part-paid retest", "10", "small", "rigid", "pass", "rgl", "rss", true},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "large", "rigid", "pass", "rgl", "p1l", true},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "small", "rigid", "pass", "rgl", "p1s", true},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "large", "rigid", "pass", "rgl", "p8l", true},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "small", "rigid", "pass", "rgl", "p8s", true},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "large", "rigid", "pass", "rgl", "p6l", true},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "small", "rigid", "pass", "rgl", "p6s", true},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "small", "rigid", "pass", "rgl", "wis", true},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "large", "rigid", "pass", "rgl", "wil", true},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "large", "rigid", "pass", "rgl", "wfl", true},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "small", "rigid", "pass", "rgl", "wfs", true},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "large", "rigid", "pass", "rgl", "wel", true},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "small", "rigid", "pass", "rgl", "wes", true},
//                {"Annual test", "Annual test", "1", "large", "rigid", "pass", "rgl", "aal", false},
//                {"Annual test", "Annual test", "1", "small", "rigid", "pass", "rgl", "aas", false},
//                {"Annual test", "Annual test", "1", "large", "articulated", "pass", "rgl", "adl", false},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "large", "rigid", "pass", "rgl", "wdl", false},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "small", "rigid", "pass", "rgl", "wds", false},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "large", "rigid", "pass", "rgl", "wbl", false},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "small", "rigid", "pass", "rgl", "wbs", false},
//                {"Any PSV", "Paid retest", "7", "large", "articulated", "pass", "rgl", "rhl", false},
//                {"Any PSV", "Paid retest", "7", "small", "rigid", "pass", "rgl", "rps", false},
//                {"Any PSV", "Paid retest", "7", "large", "rigid", "pass", "rgl", "rpl", false},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "large", "rigid", "pass", "rgl", "whl", false},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "small", "rigid", "pass", "rgl", "whs", false},
//                {"Any PSV", "Part-paid retest", "10", "large", "articulated", "pass", "rgl", "rgl", false},
//                {"Any PSV", "Part-paid retest", "10", "large", "rigid", "pass", "rgl", "rsl", false},
//                {"Any PSV", "Part-paid retest", "10", "small", "rigid", "pass", "rgl", "rss", false},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "large", "rigid", "pass", "rgl", "p1l", false},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "small", "rigid", "pass", "rgl", "p1s", false},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "large", "rigid", "pass", "rgl", "p8l", false},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "small", "rigid", "pass", "rgl", "p8s", false},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "large", "rigid", "pass", "rgl", "p6l", false},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "small", "rigid", "pass", "rgl", "p6s", false},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "small", "rigid", "pass", "rgl", "wis", false},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "large", "rigid", "pass", "rgl", "wil", false},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "large", "rigid", "pass", "rgl", "wfl", false},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "small", "rigid", "pass", "rgl", "wfs", false},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "large", "rigid", "pass", "rgl", "wel", false},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "small", "rigid", "pass", "rgl", "wes", false},
//                {"Annual test", "Annual test", "1", "large", "rigid", "prs", "rgl", "aal", true},
//                {"Annual test", "Annual test", "1", "small", "rigid", "prs", "rgl", "aas", true},
//                {"Annual test", "Annual test", "1", "large", "articulated", "prs", "rgl", "adl", true},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "large", "rigid", "prs", "rgl", "wdl", true},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "small", "rigid", "prs", "rgl", "wds", true},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "large", "rigid", "prs", "rgl", "wbl", true},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "small", "rigid", "prs", "rgl", "wbs", true},
//                {"Any PSV", "Paid retest", "7", "large", "articulated", "prs", "rgl", "rhl", true},
//                {"Any PSV", "Paid retest", "7", "small", "rigid", "prs", "rgl", "rps", true},
//                {"Any PSV", "Paid retest", "7", "large", "rigid", "prs", "rgl", "rpl", true},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "large", "rigid", "prs", "rgl", "whl", true},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "small", "rigid", "prs", "rgl", "whs", true},
//                {"Any PSV", "Part-paid retest", "10", "large", "articulated", "prs", "rgl", "rgl", true},
//                {"Any PSV", "Part-paid retest", "10", "large", "rigid", "prs", "rgl", "rsl", true},
//                {"Any PSV", "Part-paid retest", "10", "small", "rigid", "prs", "rgl", "rss", true},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "large", "rigid", "prs", "rgl", "p1l", true},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "small", "rigid", "prs", "rgl", "p1s", true},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "large", "rigid", "prs", "rgl", "p8l", true},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "small", "rigid", "prs", "rgl", "p8s", true},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "large", "rigid", "prs", "rgl", "p6l", true},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "small", "rigid", "prs", "rgl", "p6s", true},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "small", "rigid", "prs", "rgl", "wis", true},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "large", "rigid", "prs", "rgl", "wil", true},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "large", "rigid", "prs", "rgl", "wfl", true},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "small", "rigid", "prs", "rgl", "wfs", true},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "large", "rigid", "prs", "rgl", "wel", true},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "small", "rigid", "prs", "rgl", "wes", true},
//                {"Annual test", "Annual test", "1", "large", "rigid", "prs", "rgl", "aal", false},
//                {"Annual test", "Annual test", "1", "small", "rigid", "prs", "rgl", "aas", false},
//                {"Annual test", "Annual test", "1", "large", "articulated", "prs", "rgl", "adl", false},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "large", "rigid", "prs", "rgl", "wdl", false},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "small", "rigid", "prs", "rgl", "wds", false},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "large", "rigid", "prs", "rgl", "wbl", false},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "small", "rigid", "prs", "rgl", "wbs", false},
//                {"Any PSV", "Paid retest", "7", "large", "articulated", "prs", "rgl", "rhl", false},
//                {"Any PSV", "Paid retest", "7", "small", "rigid", "prs", "rgl", "rps", false},
//                {"Any PSV", "Paid retest", "7", "large", "rigid", "prs", "rgl", "rpl", false},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "large", "rigid", "prs", "rgl", "whl", false},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "small", "rigid", "prs", "rgl", "whs", false},
//                {"Any PSV", "Part-paid retest", "10", "large", "articulated", "prs", "rgl", "rgl", false},
//                {"Any PSV", "Part-paid retest", "10", "large", "rigid", "prs", "rgl", "rsl", false},
//                {"Any PSV", "Part-paid retest", "10", "small", "rigid", "prs", "rgl", "rss", false},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "large", "rigid", "prs", "rgl", "p1l", false},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "small", "rigid", "prs", "rgl", "p1s", false},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "large", "rigid", "prs", "rgl", "p8l", false},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "small", "rigid", "prs", "rgl", "p8s", false},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "large", "rigid", "prs", "rgl", "p6l", false},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "small", "rigid", "prs", "rgl", "p6s", false},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "small", "rigid", "prs", "rgl", "wis", false},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "large", "rigid", "prs", "rgl", "wil", false},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "large", "rigid", "prs", "rgl", "wfl", false},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "small", "rigid", "prs", "rgl", "wfs", false},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "large", "rigid", "prs", "rgl", "wel", false},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "small", "rigid", "prs", "rgl", "wes", false},
//
//                {"Annual test", "Annual test", "1", "large", "rigid", "pass", "rhl", "aal", true},
//                {"Annual test", "Annual test", "1", "small", "rigid", "pass", "rhl", "aas", true},
//                {"Annual test", "Annual test", "1", "large", "articulated", "pass", "rhl", "adl", true},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "large", "rigid", "pass", "rhl", "wdl", true},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "small", "rigid", "pass", "rhl", "wds", true},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "large", "rigid", "pass", "rhl", "wbl", true},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "small", "rigid", "pass", "rhl", "wbs", true},
//                {"Any PSV", "Paid retest", "7", "large", "articulated", "pass", "rhl", "rhl", true},
//                {"Any PSV", "Paid retest", "7", "small", "rigid", "pass", "rhl", "rps", true},
//                {"Any PSV", "Paid retest", "7", "large", "rigid", "pass", "rhl", "rpl", true},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "large", "rigid", "pass", "rhl", "whl", true},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "small", "rigid", "pass", "rhl", "whs", true},
//                {"Any PSV", "Part-paid retest", "10", "large", "articulated", "pass", "rhl", "rgl", true},
//                {"Any PSV", "Part-paid retest", "10", "large", "rigid", "pass", "rhl", "rsl", true},
//                {"Any PSV", "Part-paid retest", "10", "small", "rigid", "pass", "rhl", "rss", true},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "large", "rigid", "pass", "rhl", "p1l", true},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "small", "rigid", "pass", "rhl", "p1s", true},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "large", "rigid", "pass", "rhl", "p8l", true},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "small", "rigid", "pass", "rhl", "p8s", true},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "large", "rigid", "pass", "rhl", "p6l", true},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "small", "rigid", "pass", "rhl", "p6s", true},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "small", "rigid", "pass", "rhl", "wis", true},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "large", "rigid", "pass", "rhl", "wil", true},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "large", "rigid", "pass", "rhl", "wfl", true},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "small", "rigid", "pass", "rhl", "wfs", true},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "large", "rigid", "pass", "rhl", "wel", true},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "small", "rigid", "pass", "rhl", "wes", true},
//                {"Annual test", "Annual test", "1", "large", "rigid", "pass", "rhl", "aal", false},
//                {"Annual test", "Annual test", "1", "small", "rigid", "pass", "rhl", "aas", false},
//                {"Annual test", "Annual test", "1", "large", "articulated", "pass", "rhl", "adl", false},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "large", "rigid", "pass", "rhl", "wdl", false},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "small", "rigid", "pass", "rhl", "wds", false},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "large", "rigid", "pass", "rhl", "wbl", false},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "small", "rigid", "pass", "rhl", "wbs", false},
//                {"Any PSV", "Paid retest", "7", "large", "articulated", "pass", "rhl", "rhl", false},
//                {"Any PSV", "Paid retest", "7", "small", "rigid", "pass", "rhl", "rps", false},
//                {"Any PSV", "Paid retest", "7", "large", "rigid", "pass", "rhl", "rpl", false},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "large", "rigid", "pass", "rhl", "whl", false},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "small", "rigid", "pass", "rhl", "whs", false},
//                {"Any PSV", "Part-paid retest", "10", "large", "articulated", "pass", "rhl", "rgl", false},
//                {"Any PSV", "Part-paid retest", "10", "large", "rigid", "pass", "rhl", "rsl", false},
//                {"Any PSV", "Part-paid retest", "10", "small", "rigid", "pass", "rhl", "rss", false},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "large", "rigid", "pass", "rhl", "p1l", false},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "small", "rigid", "pass", "rhl", "p1s", false},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "large", "rigid", "pass", "rhl", "p8l", false},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "small", "rigid", "pass", "rhl", "p8s", false},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "large", "rigid", "pass", "rhl", "p6l", false},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "small", "rigid", "pass", "rhl", "p6s", false},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "small", "rigid", "pass", "rhl", "wis", false},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "large", "rigid", "pass", "rhl", "wil", false},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "large", "rigid", "pass", "rhl", "wfl", false},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "small", "rigid", "pass", "rhl", "wfs", false},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "large", "rigid", "pass", "rhl", "wel", false},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "small", "rigid", "pass", "rhl", "wes", false},
//                {"Annual test", "Annual test", "1", "large", "rigid", "prs", "rhl", "aal", true},
//                {"Annual test", "Annual test", "1", "small", "rigid", "prs", "rhl", "aas", true},
//                {"Annual test", "Annual test", "1", "large", "articulated", "prs", "rhl", "adl", true},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "large", "rigid", "prs", "rhl", "wdl", true},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "small", "rigid", "prs", "rhl", "wds", true},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "large", "rigid", "prs", "rhl", "wbl", true},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "small", "rigid", "prs", "rhl", "wbs", true},
//                {"Any PSV", "Paid retest", "7", "large", "articulated", "prs", "rhl", "rhl", true},
//                {"Any PSV", "Paid retest", "7", "small", "rigid", "prs", "rhl", "rps", true},
//                {"Any PSV", "Paid retest", "7", "large", "rigid", "prs", "rhl", "rpl", true},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "large", "rigid", "prs", "rhl", "whl", true},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "small", "rigid", "prs", "rhl", "whs", true},
//                {"Any PSV", "Part-paid retest", "10", "large", "articulated", "prs", "rhl", "rgl", true},
//                {"Any PSV", "Part-paid retest", "10", "large", "rigid", "prs", "rhl", "rsl", true},
//                {"Any PSV", "Part-paid retest", "10", "small", "rigid", "prs", "rhl", "rss", true},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "large", "rigid", "prs", "rhl", "p1l", true},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "small", "rigid", "prs", "rhl", "p1s", true},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "large", "rigid", "prs", "rhl", "p8l", true},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "small", "rigid", "prs", "rhl", "p8s", true},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "large", "rigid", "prs", "rhl", "p6l", true},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "small", "rigid", "prs", "rhl", "p6s", true},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "small", "rigid", "prs", "rhl", "wis", true},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "large", "rigid", "prs", "rhl", "wil", true},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "large", "rigid", "prs", "rhl", "wfl", true},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "small", "rigid", "prs", "rhl", "wfs", true},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "large", "rigid", "prs", "rhl", "wel", true},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "small", "rigid", "prs", "rhl", "wes", true},
//                {"Annual test", "Annual test", "1", "large", "rigid", "prs", "rhl", "aal", false},
//                {"Annual test", "Annual test", "1", "small", "rigid", "prs", "rhl", "aas", false},
//                {"Annual test", "Annual test", "1", "large", "articulated", "prs", "rhl", "adl", false},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "large", "rigid", "prs", "rhl", "wdl", false},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "small", "rigid", "prs", "rhl", "wds", false},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "large", "rigid", "prs", "rhl", "wbl", false},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "small", "rigid", "prs", "rhl", "wbs", false},
//                {"Any PSV", "Paid retest", "7", "large", "articulated", "prs", "rhl", "rhl", false},
//                {"Any PSV", "Paid retest", "7", "small", "rigid", "prs", "rhl", "rps", false},
//                {"Any PSV", "Paid retest", "7", "large", "rigid", "prs", "rhl", "rpl", false},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "large", "rigid", "prs", "rhl", "whl", false},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "small", "rigid", "prs", "rhl", "whs", false},
//                {"Any PSV", "Part-paid retest", "10", "large", "articulated", "prs", "rhl", "rgl", false},
//                {"Any PSV", "Part-paid retest", "10", "large", "rigid", "prs", "rhl", "rsl", false},
//                {"Any PSV", "Part-paid retest", "10", "small", "rigid", "prs", "rhl", "rss", false},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "large", "rigid", "prs", "rhl", "p1l", false},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "small", "rigid", "prs", "rhl", "p1s", false},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "large", "rigid", "prs", "rhl", "p8l", false},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "small", "rigid", "prs", "rhl", "p8s", false},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "large", "rigid", "prs", "rhl", "p6l", false},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "small", "rigid", "prs", "rhl", "p6s", false},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "small", "rigid", "prs", "rhl", "wis", false},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "large", "rigid", "prs", "rhl", "wil", false},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "large", "rigid", "prs", "rhl", "wfl", false},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "small", "rigid", "prs", "rhl", "wfs", false},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "large", "rigid", "prs", "rhl", "wel", false},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "small", "rigid", "prs", "rhl", "wes", false},
//
//                {"Annual test", "Annual test", "1", "large", "rigid", "pass", "rpl", "aal", true},
//                {"Annual test", "Annual test", "1", "small", "rigid", "pass", "rpl", "aas", true},
//                {"Annual test", "Annual test", "1", "large", "articulated", "pass", "rpl", "adl", true},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "large", "rigid", "pass", "rpl", "wdl", true},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "small", "rigid", "pass", "rpl", "wds", true},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "large", "rigid", "pass", "rpl", "wbl", true},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "small", "rigid", "pass", "rpl", "wbs", true},
//                {"Any PSV", "Paid retest", "7", "large", "articulated", "pass", "rpl", "rhl", true},
//                {"Any PSV", "Paid retest", "7", "small", "rigid", "pass", "rpl", "rps", true},
//                {"Any PSV", "Paid retest", "7", "large", "rigid", "pass", "rpl", "rpl", true},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "large", "rigid", "pass", "rpl", "whl", true},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "small", "rigid", "pass", "rpl", "whs", true},
//                {"Any PSV", "Part-paid retest", "10", "large", "articulated", "pass", "rpl", "rgl", true},
//                {"Any PSV", "Part-paid retest", "10", "large", "rigid", "pass", "rpl", "rsl", true},
//                {"Any PSV", "Part-paid retest", "10", "small", "rigid", "pass", "rpl", "rss", true},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "large", "rigid", "pass", "rpl", "p1l", true},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "small", "rigid", "pass", "rpl", "p1s", true},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "large", "rigid", "pass", "rpl", "p8l", true},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "small", "rigid", "pass", "rpl", "p8s", true},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "large", "rigid", "pass", "rpl", "p6l", true},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "small", "rigid", "pass", "rpl", "p6s", true},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "small", "rigid", "pass", "rpl", "wis", true},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "large", "rigid", "pass", "rpl", "wil", true},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "large", "rigid", "pass", "rpl", "wfl", true},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "small", "rigid", "pass", "rpl", "wfs", true},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "large", "rigid", "pass", "rpl", "wel", true},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "small", "rigid", "pass", "rpl", "wes", true},
//                {"Annual test", "Annual test", "1", "large", "rigid", "pass", "rpl", "aal", false},
//                {"Annual test", "Annual test", "1", "small", "rigid", "pass", "rpl", "aas", false},
//                {"Annual test", "Annual test", "1", "large", "articulated", "pass", "rpl", "adl", false},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "large", "rigid", "pass", "rpl", "wdl", false},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "small", "rigid", "pass", "rpl", "wds", false},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "large", "rigid", "pass", "rpl", "wbl", false},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "small", "rigid", "pass", "rpl", "wbs", false},
//                {"Any PSV", "Paid retest", "7", "large", "articulated", "pass", "rpl", "rhl", false},
//                {"Any PSV", "Paid retest", "7", "small", "rigid", "pass", "rpl", "rps", false},
//                {"Any PSV", "Paid retest", "7", "large", "rigid", "pass", "rpl", "rpl", false},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "large", "rigid", "pass", "rpl", "whl", false},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "small", "rigid", "pass", "rpl", "whs", false},
//                {"Any PSV", "Part-paid retest", "10", "large", "articulated", "pass", "rpl", "rgl", false},
//                {"Any PSV", "Part-paid retest", "10", "large", "rigid", "pass", "rpl", "rsl", false},
//                {"Any PSV", "Part-paid retest", "10", "small", "rigid", "pass", "rpl", "rss", false},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "large", "rigid", "pass", "rpl", "p1l", false},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "small", "rigid", "pass", "rpl", "p1s", false},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "large", "rigid", "pass", "rpl", "p8l", false},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "small", "rigid", "pass", "rpl", "p8s", false},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "large", "rigid", "pass", "rpl", "p6l", false},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "small", "rigid", "pass", "rpl", "p6s", false},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "small", "rigid", "pass", "rpl", "wis", false},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "large", "rigid", "pass", "rpl", "wil", false},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "large", "rigid", "pass", "rpl", "wfl", false},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "small", "rigid", "pass", "rpl", "wfs", false},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "large", "rigid", "pass", "rpl", "wel", false},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "small", "rigid", "pass", "rpl", "wes", false},
//                {"Annual test", "Annual test", "1", "large", "rigid", "prs", "rpl", "aal", true},
//                {"Annual test", "Annual test", "1", "small", "rigid", "prs", "rpl", "aas", true},
//                {"Annual test", "Annual test", "1", "large", "articulated", "prs", "rpl", "adl", true},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "large", "rigid", "prs", "rpl", "wdl", true},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "small", "rigid", "prs", "rpl", "wds", true},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "large", "rigid", "prs", "rpl", "wbl", true},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "small", "rigid", "prs", "rpl", "wbs", true},
//                {"Any PSV", "Paid retest", "7", "large", "articulated", "prs", "rpl", "rhl", true},
//                {"Any PSV", "Paid retest", "7", "small", "rigid", "prs", "rpl", "rps", true},
//                {"Any PSV", "Paid retest", "7", "large", "rigid", "prs", "rpl", "rpl", true},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "large", "rigid", "prs", "rpl", "whl", true},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "small", "rigid", "prs", "rpl", "whs", true},
//                {"Any PSV", "Part-paid retest", "10", "large", "articulated", "prs", "rpl", "rgl", true},
//                {"Any PSV", "Part-paid retest", "10", "large", "rigid", "prs", "rpl", "rsl", true},
//                {"Any PSV", "Part-paid retest", "10", "small", "rigid", "prs", "rpl", "rss", true},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "large", "rigid", "prs", "rpl", "p1l", true},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "small", "rigid", "prs", "rpl", "p1s", true},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "large", "rigid", "prs", "rpl", "p8l", true},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "small", "rigid", "prs", "rpl", "p8s", true},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "large", "rigid", "prs", "rpl", "p6l", true},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "small", "rigid", "prs", "rpl", "p6s", true},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "small", "rigid", "prs", "rpl", "wis", true},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "large", "rigid", "prs", "rpl", "wil", true},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "large", "rigid", "prs", "rpl", "wfl", true},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "small", "rigid", "prs", "rpl", "wfs", true},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "large", "rigid", "prs", "rpl", "wel", true},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "small", "rigid", "prs", "rpl", "wes", true},
//                {"Annual test", "Annual test", "1", "large", "rigid", "prs", "rpl", "aal", false},
//                {"Annual test", "Annual test", "1", "small", "rigid", "prs", "rpl", "aas", false},
//                {"Annual test", "Annual test", "1", "large", "articulated", "prs", "rpl", "adl", false},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "large", "rigid", "prs", "rpl", "wdl", false},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "small", "rigid", "prs", "rpl", "wds", false},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "large", "rigid", "prs", "rpl", "wbl", false},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "small", "rigid", "prs", "rpl", "wbs", false},
//                {"Any PSV", "Paid retest", "7", "large", "articulated", "prs", "rpl", "rhl", false},
//                {"Any PSV", "Paid retest", "7", "small", "rigid", "prs", "rpl", "rps", false},
//                {"Any PSV", "Paid retest", "7", "large", "rigid", "prs", "rpl", "rpl", false},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "large", "rigid", "prs", "rpl", "whl", false},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "small", "rigid", "prs", "rpl", "whs", false},
//                {"Any PSV", "Part-paid retest", "10", "large", "articulated", "prs", "rpl", "rgl", false},
//                {"Any PSV", "Part-paid retest", "10", "large", "rigid", "prs", "rpl", "rsl", false},
//                {"Any PSV", "Part-paid retest", "10", "small", "rigid", "prs", "rpl", "rss", false},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "large", "rigid", "prs", "rpl", "p1l", false},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "small", "rigid", "prs", "rpl", "p1s", false},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "large", "rigid", "prs", "rpl", "p8l", false},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "small", "rigid", "prs", "rpl", "p8s", false},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "large", "rigid", "prs", "rpl", "p6l", false},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "small", "rigid", "prs", "rpl", "p6s", false},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "small", "rigid", "prs", "rpl", "wis", false},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "large", "rigid", "prs", "rpl", "wil", false},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "large", "rigid", "prs", "rpl", "wfl", false},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "small", "rigid", "prs", "rpl", "wfs", false},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "large", "rigid", "prs", "rpl", "wel", false},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "small", "rigid", "prs", "rpl", "wes", false},
//
//                {"Annual test", "Annual test", "1", "large", "rigid", "pass", "rps", "aal", true},
//                {"Annual test", "Annual test", "1", "small", "rigid", "pass", "rps", "aas", true},
//                {"Annual test", "Annual test", "1", "large", "articulated", "pass", "rps", "adl", true},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "large", "rigid", "pass", "rps", "wdl", true},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "small", "rigid", "pass", "rps", "wds", true},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "large", "rigid", "pass", "rps", "wbl", true},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "small", "rigid", "pass", "rps", "wbs", true},
//                {"Any PSV", "Paid retest", "7", "large", "articulated", "pass", "rps", "rhl", true},
//                {"Any PSV", "Paid retest", "7", "small", "rigid", "pass", "rps", "rps", true},
//                {"Any PSV", "Paid retest", "7", "large", "rigid", "pass", "rps", "rpl", true},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "large", "rigid", "pass", "rps", "whl", true},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "small", "rigid", "pass", "rps", "whs", true},
//                {"Any PSV", "Part-paid retest", "10", "large", "articulated", "pass", "rps", "rgl", true},
//                {"Any PSV", "Part-paid retest", "10", "large", "rigid", "pass", "rps", "rsl", true},
//                {"Any PSV", "Part-paid retest", "10", "small", "rigid", "pass", "rps", "rss", true},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "large", "rigid", "pass", "rps", "p1l", true},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "small", "rigid", "pass", "rps", "p1s", true},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "large", "rigid", "pass", "rps", "p8l", true},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "small", "rigid", "pass", "rps", "p8s", true},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "large", "rigid", "pass", "rps", "p6l", true},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "small", "rigid", "pass", "rps", "p6s", true},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "small", "rigid", "pass", "rps", "wis", true},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "large", "rigid", "pass", "rps", "wil", true},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "large", "rigid", "pass", "rps", "wfl", true},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "small", "rigid", "pass", "rps", "wfs", true},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "large", "rigid", "pass", "rps", "wel", true},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "small", "rigid", "pass", "rps", "wes", true},
//                {"Annual test", "Annual test", "1", "large", "rigid", "pass", "rps", "aal", false},
//                {"Annual test", "Annual test", "1", "small", "rigid", "pass", "rps", "aas", false},
//                {"Annual test", "Annual test", "1", "large", "articulated", "pass", "rps", "adl", false},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "large", "rigid", "pass", "rps", "wdl", false},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "small", "rigid", "pass", "rps", "wds", false},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "large", "rigid", "pass", "rps", "wbl", false},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "small", "rigid", "pass", "rps", "wbs", false},
//                {"Any PSV", "Paid retest", "7", "large", "articulated", "pass", "rps", "rhl", false},
//                {"Any PSV", "Paid retest", "7", "small", "rigid", "pass", "rps", "rps", false},
//                {"Any PSV", "Paid retest", "7", "large", "rigid", "pass", "rps", "rpl", false},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "large", "rigid", "pass", "rps", "whl", false},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "small", "rigid", "pass", "rps", "whs", false},
//                {"Any PSV", "Part-paid retest", "10", "large", "articulated", "pass", "rps", "rgl", false},
//                {"Any PSV", "Part-paid retest", "10", "large", "rigid", "pass", "rps", "rsl", false},
//                {"Any PSV", "Part-paid retest", "10", "small", "rigid", "pass", "rps", "rss", false},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "large", "rigid", "pass", "rps", "p1l", false},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "small", "rigid", "pass", "rps", "p1s", false},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "large", "rigid", "pass", "rps", "p8l", false},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "small", "rigid", "pass", "rps", "p8s", false},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "large", "rigid", "pass", "rps", "p6l", false},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "small", "rigid", "pass", "rps", "p6s", false},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "small", "rigid", "pass", "rps", "wis", false},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "large", "rigid", "pass", "rps", "wil", false},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "large", "rigid", "pass", "rps", "wfl", false},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "small", "rigid", "pass", "rps", "wfs", false},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "large", "rigid", "pass", "rps", "wel", false},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "small", "rigid", "pass", "rps", "wes", false},
//                {"Annual test", "Annual test", "1", "large", "rigid", "prs", "rps", "aal", true},
//                {"Annual test", "Annual test", "1", "small", "rigid", "prs", "rps", "aas", true},
//                {"Annual test", "Annual test", "1", "large", "articulated", "prs", "rps", "adl", true},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "large", "rigid", "prs", "rps", "wdl", true},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "small", "rigid", "prs", "rps", "wds", true},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "large", "rigid", "prs", "rps", "wbl", true},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "small", "rigid", "prs", "rps", "wbs", true},
//                {"Any PSV", "Paid retest", "7", "large", "articulated", "prs", "rps", "rhl", true},
//                {"Any PSV", "Paid retest", "7", "small", "rigid", "prs", "rps", "rps", true},
//                {"Any PSV", "Paid retest", "7", "large", "rigid", "prs", "rps", "rpl", true},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "large", "rigid", "prs", "rps", "whl", true},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "small", "rigid", "prs", "rps", "whs", true},
//                {"Any PSV", "Part-paid retest", "10", "large", "articulated", "prs", "rps", "rgl", true},
//                {"Any PSV", "Part-paid retest", "10", "large", "rigid", "prs", "rps", "rsl", true},
//                {"Any PSV", "Part-paid retest", "10", "small", "rigid", "prs", "rps", "rss", true},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "large", "rigid", "prs", "rps", "p1l", true},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "small", "rigid", "prs", "rps", "p1s", true},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "large", "rigid", "prs", "rps", "p8l", true},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "small", "rigid", "prs", "rps", "p8s", true},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "large", "rigid", "prs", "rps", "p6l", true},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "small", "rigid", "prs", "rps", "p6s", true},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "small", "rigid", "prs", "rps", "wis", true},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "large", "rigid", "prs", "rps", "wil", true},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "large", "rigid", "prs", "rps", "wfl", true},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "small", "rigid", "prs", "rps", "wfs", true},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "large", "rigid", "prs", "rps", "wel", true},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "small", "rigid", "prs", "rps", "wes", true},
//                {"Annual test", "Annual test", "1", "large", "rigid", "prs", "rps", "aal", false},
//                {"Annual test", "Annual test", "1", "small", "rigid", "prs", "rps", "aas", false},
//                {"Annual test", "Annual test", "1", "large", "articulated", "prs", "rps", "adl", false},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "large", "rigid", "prs", "rps", "wdl", false},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "small", "rigid", "prs", "rps", "wds", false},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "large", "rigid", "prs", "rps", "wbl", false},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "small", "rigid", "prs", "rps", "wbs", false},
//                {"Any PSV", "Paid retest", "7", "large", "articulated", "prs", "rps", "rhl", false},
//                {"Any PSV", "Paid retest", "7", "small", "rigid", "prs", "rps", "rps", false},
//                {"Any PSV", "Paid retest", "7", "large", "rigid", "prs", "rps", "rpl", false},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "large", "rigid", "prs", "rps", "whl", false},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "small", "rigid", "prs", "rps", "whs", false},
//                {"Any PSV", "Part-paid retest", "10", "large", "articulated", "prs", "rps", "rgl", false},
//                {"Any PSV", "Part-paid retest", "10", "large", "rigid", "prs", "rps", "rsl", false},
//                {"Any PSV", "Part-paid retest", "10", "small", "rigid", "prs", "rps", "rss", false},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "large", "rigid", "prs", "rps", "p1l", false},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "small", "rigid", "prs", "rps", "p1s", false},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "large", "rigid", "prs", "rps", "p8l", false},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "small", "rigid", "prs", "rps", "p8s", false},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "large", "rigid", "prs", "rps", "p6l", false},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "small", "rigid", "prs", "rps", "p6s", false},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "small", "rigid", "prs", "rps", "wis", false},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "large", "rigid", "prs", "rps", "wil", false},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "large", "rigid", "prs", "rps", "wfl", false},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "small", "rigid", "prs", "rps", "wfs", false},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "large", "rigid", "prs", "rps", "wel", false},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "small", "rigid", "prs", "rps", "wes", false},
//
//                {"Annual test", "Annual test", "1", "large", "rigid", "pass", "rsl", "aal", true},
//                {"Annual test", "Annual test", "1", "small", "rigid", "pass", "rsl", "aas", true},
//                {"Annual test", "Annual test", "1", "large", "articulated", "pass", "rsl", "adl", true},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "large", "rigid", "pass", "rsl", "wdl", true},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "small", "rigid", "pass", "rsl", "wds", true},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "large", "rigid", "pass", "rsl", "wbl", true},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "small", "rigid", "pass", "rsl", "wbs", true},
//                {"Any PSV", "Paid retest", "7", "large", "articulated", "pass", "rsl", "rhl", true},
//                {"Any PSV", "Paid retest", "7", "small", "rigid", "pass", "rsl", "rps", true},
//                {"Any PSV", "Paid retest", "7", "large", "rigid", "pass", "rsl", "rpl", true},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "large", "rigid", "pass", "rsl", "whl", true},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "small", "rigid", "pass", "rsl", "whs", true},
//                {"Any PSV", "Part-paid retest", "10", "large", "articulated", "pass", "rsl", "rgl", true},
//                {"Any PSV", "Part-paid retest", "10", "large", "rigid", "pass", "rsl", "rsl", true},
//                {"Any PSV", "Part-paid retest", "10", "small", "rigid", "pass", "rsl", "rss", true},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "large", "rigid", "pass", "rsl", "p1l", true},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "small", "rigid", "pass", "rsl", "p1s", true},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "large", "rigid", "pass", "rsl", "p8l", true},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "small", "rigid", "pass", "rsl", "p8s", true},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "large", "rigid", "pass", "rsl", "p6l", true},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "small", "rigid", "pass", "rsl", "p6s", true},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "small", "rigid", "pass", "rsl", "wis", true},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "large", "rigid", "pass", "rsl", "wil", true},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "large", "rigid", "pass", "rsl", "wfl", true},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "small", "rigid", "pass", "rsl", "wfs", true},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "large", "rigid", "pass", "rsl", "wel", true},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "small", "rigid", "pass", "rsl", "wes", true},
//                {"Annual test", "Annual test", "1", "large", "rigid", "pass", "rsl", "aal", false},
//                {"Annual test", "Annual test", "1", "small", "rigid", "pass", "rsl", "aas", false},
//                {"Annual test", "Annual test", "1", "large", "articulated", "pass", "rsl", "adl", false},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "large", "rigid", "pass", "rsl", "wdl", false},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "small", "rigid", "pass", "rsl", "wds", false},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "large", "rigid", "pass", "rsl", "wbl", false},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "small", "rigid", "pass", "rsl", "wbs", false},
//                {"Any PSV", "Paid retest", "7", "large", "articulated", "pass", "rsl", "rhl", false},
//                {"Any PSV", "Paid retest", "7", "small", "rigid", "pass", "rsl", "rps", false},
//                {"Any PSV", "Paid retest", "7", "large", "rigid", "pass", "rsl", "rpl", false},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "large", "rigid", "pass", "rsl", "whl", false},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "small", "rigid", "pass", "rsl", "whs", false},
//                {"Any PSV", "Part-paid retest", "10", "large", "articulated", "pass", "rsl", "rgl", false},
//                {"Any PSV", "Part-paid retest", "10", "large", "rigid", "pass", "rsl", "rsl", false},
//                {"Any PSV", "Part-paid retest", "10", "small", "rigid", "pass", "rsl", "rss", false},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "large", "rigid", "pass", "rsl", "p1l", false},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "small", "rigid", "pass", "rsl", "p1s", false},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "large", "rigid", "pass", "rsl", "p8l", false},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "small", "rigid", "pass", "rsl", "p8s", false},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "large", "rigid", "pass", "rsl", "p6l", false},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "small", "rigid", "pass", "rsl", "p6s", false},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "small", "rigid", "pass", "rsl", "wis", false},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "large", "rigid", "pass", "rsl", "wil", false},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "large", "rigid", "pass", "rsl", "wfl", false},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "small", "rigid", "pass", "rsl", "wfs", false},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "large", "rigid", "pass", "rsl", "wel", false},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "small", "rigid", "pass", "rsl", "wes", false},
//                {"Annual test", "Annual test", "1", "large", "rigid", "prs", "rsl", "aal", true},
//                {"Annual test", "Annual test", "1", "small", "rigid", "prs", "rsl", "aas", true},
//                {"Annual test", "Annual test", "1", "large", "articulated", "prs", "rsl", "adl", true},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "large", "rigid", "prs", "rsl", "wdl", true},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "small", "rigid", "prs", "rsl", "wds", true},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "large", "rigid", "prs", "rsl", "wbl", true},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "small", "rigid", "prs", "rsl", "wbs", true},
//                {"Any PSV", "Paid retest", "7", "large", "articulated", "prs", "rsl", "rhl", true},
//                {"Any PSV", "Paid retest", "7", "small", "rigid", "prs", "rsl", "rps", true},
//                {"Any PSV", "Paid retest", "7", "large", "rigid", "prs", "rsl", "rpl", true},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "large", "rigid", "prs", "rsl", "whl", true},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "small", "rigid", "prs", "rsl", "whs", true},
//                {"Any PSV", "Part-paid retest", "10", "large", "articulated", "prs", "rsl", "rgl", true},
//                {"Any PSV", "Part-paid retest", "10", "large", "rigid", "prs", "rsl", "rsl", true},
//                {"Any PSV", "Part-paid retest", "10", "small", "rigid", "prs", "rsl", "rss", true},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "large", "rigid", "prs", "rsl", "p1l", true},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "small", "rigid", "prs", "rsl", "p1s", true},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "large", "rigid", "prs", "rsl", "p8l", true},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "small", "rigid", "prs", "rsl", "p8s", true},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "large", "rigid", "prs", "rsl", "p6l", true},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "small", "rigid", "prs", "rsl", "p6s", true},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "small", "rigid", "prs", "rsl", "wis", true},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "large", "rigid", "prs", "rsl", "wil", true},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "large", "rigid", "prs", "rsl", "wfl", true},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "small", "rigid", "prs", "rsl", "wfs", true},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "large", "rigid", "prs", "rsl", "wel", true},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "small", "rigid", "prs", "rsl", "wes", true},
//                {"Annual test", "Annual test", "1", "large", "rigid", "prs", "rsl", "aal", false},
//                {"Annual test", "Annual test", "1", "small", "rigid", "prs", "rsl", "aas", false},
//                {"Annual test", "Annual test", "1", "large", "articulated", "prs", "rsl", "adl", false},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "large", "rigid", "prs", "rsl", "wdl", false},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "small", "rigid", "prs", "rsl", "wds", false},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "large", "rigid", "prs", "rsl", "wbl", false},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "small", "rigid", "prs", "rsl", "wbs", false},
//                {"Any PSV", "Paid retest", "7", "large", "articulated", "prs", "rsl", "rhl", false},
//                {"Any PSV", "Paid retest", "7", "small", "rigid", "prs", "rsl", "rps", false},
//                {"Any PSV", "Paid retest", "7", "large", "rigid", "prs", "rsl", "rpl", false},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "large", "rigid", "prs", "rsl", "whl", false},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "small", "rigid", "prs", "rsl", "whs", false},
//                {"Any PSV", "Part-paid retest", "10", "large", "articulated", "prs", "rsl", "rgl", false},
//                {"Any PSV", "Part-paid retest", "10", "large", "rigid", "prs", "rsl", "rsl", false},
//                {"Any PSV", "Part-paid retest", "10", "small", "rigid", "prs", "rsl", "rss", false},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "large", "rigid", "prs", "rsl", "p1l", false},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "small", "rigid", "prs", "rsl", "p1s", false},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "large", "rigid", "prs", "rsl", "p8l", false},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "small", "rigid", "prs", "rsl", "p8s", false},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "large", "rigid", "prs", "rsl", "p6l", false},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "small", "rigid", "prs", "rsl", "p6s", false},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "small", "rigid", "prs", "rsl", "wis", false},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "large", "rigid", "prs", "rsl", "wil", false},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "large", "rigid", "prs", "rsl", "wfl", false},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "small", "rigid", "prs", "rsl", "wfs", false},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "large", "rigid", "prs", "rsl", "wel", false},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "small", "rigid", "prs", "rsl", "wes", false},
//
//                {"Annual test", "Annual test", "1", "large", "rigid", "pass", "rss", "aal", true},
//                {"Annual test", "Annual test", "1", "small", "rigid", "pass", "rss", "aas", true},
//                {"Annual test", "Annual test", "1", "large", "articulated", "pass", "rss", "adl", true},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "large", "rigid", "pass", "rss", "wdl", true},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "small", "rigid", "pass", "rss", "wds", true},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "large", "rigid", "pass", "rss", "wbl", true},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "small", "rigid", "pass", "rss", "wbs", true},
//                {"Any PSV", "Paid retest", "7", "large", "articulated", "pass", "rss", "rhl", true},
//                {"Any PSV", "Paid retest", "7", "small", "rigid", "pass", "rss", "rps", true},
//                {"Any PSV", "Paid retest", "7", "large", "rigid", "pass", "rss", "rpl", true},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "large", "rigid", "pass", "rss", "whl", true},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "small", "rigid", "pass", "rss", "whs", true},
//                {"Any PSV", "Part-paid retest", "10", "large", "articulated", "pass", "rss", "rgl", true},
//                {"Any PSV", "Part-paid retest", "10", "large", "rigid", "pass", "rss", "rsl", true},
//                {"Any PSV", "Part-paid retest", "10", "small", "rigid", "pass", "rss", "rss", true},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "large", "rigid", "pass", "rss", "p1l", true},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "small", "rigid", "pass", "rss", "p1s", true},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "large", "rigid", "pass", "rss", "p8l", true},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "small", "rigid", "pass", "rss", "p8s", true},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "large", "rigid", "pass", "rss", "p6l", true},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "small", "rigid", "pass", "rss", "p6s", true},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "small", "rigid", "pass", "rss", "wis", true},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "large", "rigid", "pass", "rss", "wil", true},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "large", "rigid", "pass", "rss", "wfl", true},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "small", "rigid", "pass", "rss", "wfs", true},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "large", "rigid", "pass", "rss", "wel", true},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "small", "rigid", "pass", "rss", "wes", true},
//                {"Annual test", "Annual test", "1", "large", "rigid", "pass", "rss", "aal", false},
//                {"Annual test", "Annual test", "1", "small", "rigid", "pass", "rss", "aas", false},
//                {"Annual test", "Annual test", "1", "large", "articulated", "pass", "rss", "adl", false},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "large", "rigid", "pass", "rss", "wdl", false},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "small", "rigid", "pass", "rss", "wds", false},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "large", "rigid", "pass", "rss", "wbl", false},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "small", "rigid", "pass", "rss", "wbs", false},
//                {"Any PSV", "Paid retest", "7", "large", "articulated", "pass", "rss", "rhl", false},
//                {"Any PSV", "Paid retest", "7", "small", "rigid", "pass", "rss", "rps", false},
//                {"Any PSV", "Paid retest", "7", "large", "rigid", "pass", "rss", "rpl", false},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "large", "rigid", "pass", "rss", "whl", false},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "small", "rigid", "pass", "rss", "whs", false},
//                {"Any PSV", "Part-paid retest", "10", "large", "articulated", "pass", "rss", "rgl", false},
//                {"Any PSV", "Part-paid retest", "10", "large", "rigid", "pass", "rss", "rsl", false},
//                {"Any PSV", "Part-paid retest", "10", "small", "rigid", "pass", "rss", "rss", false},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "large", "rigid", "pass", "rss", "p1l", false},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "small", "rigid", "pass", "rss", "p1s", false},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "large", "rigid", "pass", "rss", "p8l", false},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "small", "rigid", "pass", "rss", "p8s", false},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "large", "rigid", "pass", "rss", "p6l", false},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "small", "rigid", "pass", "rss", "p6s", false},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "small", "rigid", "pass", "rss", "wis", false},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "large", "rigid", "pass", "rss", "wil", false},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "large", "rigid", "pass", "rss", "wfl", false},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "small", "rigid", "pass", "rss", "wfs", false},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "large", "rigid", "pass", "rss", "wel", false},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "small", "rigid", "pass", "rss", "wes", false},
//                {"Annual test", "Annual test", "1", "large", "rigid", "prs", "rss", "aal", true},
//                {"Annual test", "Annual test", "1", "small", "rigid", "prs", "rss", "aas", true},
//                {"Annual test", "Annual test", "1", "large", "articulated", "prs", "rss", "adl", true},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "large", "rigid", "prs", "rss", "wdl", true},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "small", "rigid", "prs", "rss", "wds", true},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "large", "rigid", "prs", "rss", "wbl", true},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "small", "rigid", "prs", "rss", "wbs", true},
//                {"Any PSV", "Paid retest", "7", "large", "articulated", "prs", "rss", "rhl", true},
//                {"Any PSV", "Paid retest", "7", "small", "rigid", "prs", "rss", "rps", true},
//                {"Any PSV", "Paid retest", "7", "large", "rigid", "prs", "rss", "rpl", true},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "large", "rigid", "prs", "rss", "whl", true},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "small", "rigid", "prs", "rss", "whs", true},
//                {"Any PSV", "Part-paid retest", "10", "large", "articulated", "prs", "rss", "rgl", true},
//                {"Any PSV", "Part-paid retest", "10", "large", "rigid", "prs", "rss", "rsl", true},
//                {"Any PSV", "Part-paid retest", "10", "small", "rigid", "prs", "rss", "rss", true},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "large", "rigid", "prs", "rss", "p1l", true},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "small", "rigid", "prs", "rss", "p1s", true},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "large", "rigid", "prs", "rss", "p8l", true},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "small", "rigid", "prs", "rss", "p8s", true},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "large", "rigid", "prs", "rss", "p6l", true},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "small", "rigid", "prs", "rss", "p6s", true},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "small", "rigid", "prs", "rss", "wis", true},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "large", "rigid", "prs", "rss", "wil", true},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "large", "rigid", "prs", "rss", "wfl", true},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "small", "rigid", "prs", "rss", "wfs", true},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "large", "rigid", "prs", "rss", "wel", true},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "small", "rigid", "prs", "rss", "wes", true},
//                {"Annual test", "Annual test", "1", "large", "rigid", "prs", "rss", "aal", false},
//                {"Annual test", "Annual test", "1", "small", "rigid", "prs", "rss", "aas", false},
//                {"Annual test", "Annual test", "1", "large", "articulated", "prs", "rss", "adl", false},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "large", "rigid", "prs", "rss", "wdl", false},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "small", "rigid", "prs", "rss", "wds", false},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "large", "rigid", "prs", "rss", "wbl", false},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "small", "rigid", "prs", "rss", "wbs", false},
//                {"Any PSV", "Paid retest", "7", "large", "articulated", "prs", "rss", "rhl", false},
//                {"Any PSV", "Paid retest", "7", "small", "rigid", "prs", "rss", "rps", false},
//                {"Any PSV", "Paid retest", "7", "large", "rigid", "prs", "rss", "rpl", false},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "large", "rigid", "prs", "rss", "whl", false},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "small", "rigid", "prs", "rss", "whs", false},
//                {"Any PSV", "Part-paid retest", "10", "large", "articulated", "prs", "rss", "rgl", false},
//                {"Any PSV", "Part-paid retest", "10", "large", "rigid", "prs", "rss", "rsl", false},
//                {"Any PSV", "Part-paid retest", "10", "small", "rigid", "prs", "rss", "rss", false},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "large", "rigid", "prs", "rss", "p1l", false},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "small", "rigid", "prs", "rss", "p1s", false},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "large", "rigid", "prs", "rss", "p8l", false},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "small", "rigid", "prs", "rss", "p8s", false},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "large", "rigid", "prs", "rss", "p6l", false},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "small", "rigid", "prs", "rss", "p6s", false},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "small", "rigid", "prs", "rss", "wis", false},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "large", "rigid", "prs", "rss", "wil", false},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "large", "rigid", "prs", "rss", "wfl", false},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "small", "rigid", "prs", "rss", "wfs", false},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "large", "rigid", "prs", "rss", "wel", false},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "small", "rigid", "prs", "rss", "wes", false},
//
//                {"Annual test", "Annual test", "1", "large", "rigid", "pass", "wbl", "aal", true},
//                {"Annual test", "Annual test", "1", "small", "rigid", "pass", "wbl", "aas", true},
//                {"Annual test", "Annual test", "1", "large", "articulated", "pass", "wbl", "adl", true},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "large", "rigid", "pass", "wbl", "wdl", true},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "small", "rigid", "pass", "wbl", "wds", true},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "large", "rigid", "pass", "wbl", "wbl", true},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "small", "rigid", "pass", "wbl", "wbs", true},
//                {"Any PSV", "Paid retest", "7", "large", "articulated", "pass", "wbl", "rhl", true},
//                {"Any PSV", "Paid retest", "7", "small", "rigid", "pass", "wbl", "rps", true},
//                {"Any PSV", "Paid retest", "7", "large", "rigid", "pass", "wbl", "rpl", true},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "large", "rigid", "pass", "wbl", "whl", true},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "small", "rigid", "pass", "wbl", "whs", true},
//                {"Any PSV", "Part-paid retest", "10", "large", "articulated", "pass", "wbl", "rgl", true},
//                {"Any PSV", "Part-paid retest", "10", "large", "rigid", "pass", "wbl", "rsl", true},
//                {"Any PSV", "Part-paid retest", "10", "small", "rigid", "pass", "wbl", "rss", true},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "large", "rigid", "pass", "wbl", "p1l", true},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "small", "rigid", "pass", "wbl", "p1s", true},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "large", "rigid", "pass", "wbl", "p8l", true},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "small", "rigid", "pass", "wbl", "p8s", true},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "large", "rigid", "pass", "wbl", "p6l", true},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "small", "rigid", "pass", "wbl", "p6s", true},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "small", "rigid", "pass", "wbl", "wis", true},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "large", "rigid", "pass", "wbl", "wil", true},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "large", "rigid", "pass", "wbl", "wfl", true},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "small", "rigid", "pass", "wbl", "wfs", true},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "large", "rigid", "pass", "wbl", "wel", true},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "small", "rigid", "pass", "wbl", "wes", true},
//                {"Annual test", "Annual test", "1", "large", "rigid", "pass", "wbl", "aal", false},
//                {"Annual test", "Annual test", "1", "small", "rigid", "pass", "wbl", "aas", false},
//                {"Annual test", "Annual test", "1", "large", "articulated", "pass", "wbl", "adl", false},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "large", "rigid", "pass", "wbl", "wdl", false},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "small", "rigid", "pass", "wbl", "wds", false},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "large", "rigid", "pass", "wbl", "wbl", false},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "small", "rigid", "pass", "wbl", "wbs", false},
//                {"Any PSV", "Paid retest", "7", "large", "articulated", "pass", "wbl", "rhl", false},
//                {"Any PSV", "Paid retest", "7", "small", "rigid", "pass", "wbl", "rps", false},
//                {"Any PSV", "Paid retest", "7", "large", "rigid", "pass", "wbl", "rpl", false},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "large", "rigid", "pass", "wbl", "whl", false},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "small", "rigid", "pass", "wbl", "whs", false},
//                {"Any PSV", "Part-paid retest", "10", "large", "articulated", "pass", "wbl", "rgl", false},
//                {"Any PSV", "Part-paid retest", "10", "large", "rigid", "pass", "wbl", "rsl", false},
//                {"Any PSV", "Part-paid retest", "10", "small", "rigid", "pass", "wbl", "rss", false},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "large", "rigid", "pass", "wbl", "p1l", false},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "small", "rigid", "pass", "wbl", "p1s", false},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "large", "rigid", "pass", "wbl", "p8l", false},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "small", "rigid", "pass", "wbl", "p8s", false},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "large", "rigid", "pass", "wbl", "p6l", false},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "small", "rigid", "pass", "wbl", "p6s", false},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "small", "rigid", "pass", "wbl", "wis", false},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "large", "rigid", "pass", "wbl", "wil", false},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "large", "rigid", "pass", "wbl", "wfl", false},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "small", "rigid", "pass", "wbl", "wfs", false},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "large", "rigid", "pass", "wbl", "wel", false},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "small", "rigid", "pass", "wbl", "wes", false},
//                {"Annual test", "Annual test", "1", "large", "rigid", "prs", "wbl", "aal", true},
//                {"Annual test", "Annual test", "1", "small", "rigid", "prs", "wbl", "aas", true},
//                {"Annual test", "Annual test", "1", "large", "articulated", "prs", "wbl", "adl", true},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "large", "rigid", "prs", "wbl", "wdl", true},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "small", "rigid", "prs", "wbl", "wds", true},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "large", "rigid", "prs", "wbl", "wbl", true},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "small", "rigid", "prs", "wbl", "wbs", true},
//                {"Any PSV", "Paid retest", "7", "large", "articulated", "prs", "wbl", "rhl", true},
//                {"Any PSV", "Paid retest", "7", "small", "rigid", "prs", "wbl", "rps", true},
//                {"Any PSV", "Paid retest", "7", "large", "rigid", "prs", "wbl", "rpl", true},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "large", "rigid", "prs", "wbl", "whl", true},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "small", "rigid", "prs", "wbl", "whs", true},
//                {"Any PSV", "Part-paid retest", "10", "large", "articulated", "prs", "wbl", "rgl", true},
//                {"Any PSV", "Part-paid retest", "10", "large", "rigid", "prs", "wbl", "rsl", true},
//                {"Any PSV", "Part-paid retest", "10", "small", "rigid", "prs", "wbl", "rss", true},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "large", "rigid", "prs", "wbl", "p1l", true},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "small", "rigid", "prs", "wbl", "p1s", true},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "large", "rigid", "prs", "wbl", "p8l", true},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "small", "rigid", "prs", "wbl", "p8s", true},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "large", "rigid", "prs", "wbl", "p6l", true},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "small", "rigid", "prs", "wbl", "p6s", true},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "small", "rigid", "prs", "wbl", "wis", true},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "large", "rigid", "prs", "wbl", "wil", true},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "large", "rigid", "prs", "wbl", "wfl", true},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "small", "rigid", "prs", "wbl", "wfs", true},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "large", "rigid", "prs", "wbl", "wel", true},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "small", "rigid", "prs", "wbl", "wes", true},
//                {"Annual test", "Annual test", "1", "large", "rigid", "prs", "wbl", "aal", false},
//                {"Annual test", "Annual test", "1", "small", "rigid", "prs", "wbl", "aas", false},
//                {"Annual test", "Annual test", "1", "large", "articulated", "prs", "wbl", "adl", false},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "large", "rigid", "prs", "wbl", "wdl", false},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "small", "rigid", "prs", "wbl", "wds", false},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "large", "rigid", "prs", "wbl", "wbl", false},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "small", "rigid", "prs", "wbl", "wbs", false},
//                {"Any PSV", "Paid retest", "7", "large", "articulated", "prs", "wbl", "rhl", false},
//                {"Any PSV", "Paid retest", "7", "small", "rigid", "prs", "wbl", "rps", false},
//                {"Any PSV", "Paid retest", "7", "large", "rigid", "prs", "wbl", "rpl", false},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "large", "rigid", "prs", "wbl", "whl", false},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "small", "rigid", "prs", "wbl", "whs", false},
//                {"Any PSV", "Part-paid retest", "10", "large", "articulated", "prs", "wbl", "rgl", false},
//                {"Any PSV", "Part-paid retest", "10", "large", "rigid", "prs", "wbl", "rsl", false},
//                {"Any PSV", "Part-paid retest", "10", "small", "rigid", "prs", "wbl", "rss", false},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "large", "rigid", "prs", "wbl", "p1l", false},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "small", "rigid", "prs", "wbl", "p1s", false},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "large", "rigid", "prs", "wbl", "p8l", false},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "small", "rigid", "prs", "wbl", "p8s", false},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "large", "rigid", "prs", "wbl", "p6l", false},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "small", "rigid", "prs", "wbl", "p6s", false},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "small", "rigid", "prs", "wbl", "wis", false},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "large", "rigid", "prs", "wbl", "wil", false},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "large", "rigid", "prs", "wbl", "wfl", false},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "small", "rigid", "prs", "wbl", "wfs", false},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "large", "rigid", "prs", "wbl", "wel", false},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "small", "rigid", "prs", "wbl", "wes", false},
//
//                {"Annual test", "Annual test", "1", "large", "rigid", "pass", "wbs", "aal", true},
//                {"Annual test", "Annual test", "1", "small", "rigid", "pass", "wbs", "aas", true},
//                {"Annual test", "Annual test", "1", "large", "articulated", "pass", "wbs", "adl", true},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "large", "rigid", "pass", "wbs", "wdl", true},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "small", "rigid", "pass", "wbs", "wds", true},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "large", "rigid", "pass", "wbs", "wbl", true},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "small", "rigid", "pass", "wbs", "wbs", true},
//                {"Any PSV", "Paid retest", "7", "large", "articulated", "pass", "wbs", "rhl", true},
//                {"Any PSV", "Paid retest", "7", "small", "rigid", "pass", "wbs", "rps", true},
//                {"Any PSV", "Paid retest", "7", "large", "rigid", "pass", "wbs", "rpl", true},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "large", "rigid", "pass", "wbs", "whl", true},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "small", "rigid", "pass", "wbs", "whs", true},
//                {"Any PSV", "Part-paid retest", "10", "large", "articulated", "pass", "wbs", "rgl", true},
//                {"Any PSV", "Part-paid retest", "10", "large", "rigid", "pass", "wbs", "rsl", true},
//                {"Any PSV", "Part-paid retest", "10", "small", "rigid", "pass", "wbs", "rss", true},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "large", "rigid", "pass", "wbs", "p1l", true},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "small", "rigid", "pass", "wbs", "p1s", true},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "large", "rigid", "pass", "wbs", "p8l", true},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "small", "rigid", "pass", "wbs", "p8s", true},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "large", "rigid", "pass", "wbs", "p6l", true},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "small", "rigid", "pass", "wbs", "p6s", true},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "small", "rigid", "pass", "wbs", "wis", true},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "large", "rigid", "pass", "wbs", "wil", true},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "large", "rigid", "pass", "wbs", "wfl", true},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "small", "rigid", "pass", "wbs", "wfs", true},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "large", "rigid", "pass", "wbs", "wel", true},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "small", "rigid", "pass", "wbs", "wes", true},
//                {"Annual test", "Annual test", "1", "large", "rigid", "pass", "wbs", "aal", false},
//                {"Annual test", "Annual test", "1", "small", "rigid", "pass", "wbs", "aas", false},
//                {"Annual test", "Annual test", "1", "large", "articulated", "pass", "wbs", "adl", false},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "large", "rigid", "pass", "wbs", "wdl", false},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "small", "rigid", "pass", "wbs", "wds", false},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "large", "rigid", "pass", "wbs", "wbl", false},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "small", "rigid", "pass", "wbs", "wbs", false},
//                {"Any PSV", "Paid retest", "7", "large", "articulated", "pass", "wbs", "rhl", false},
//                {"Any PSV", "Paid retest", "7", "small", "rigid", "pass", "wbs", "rps", false},
//                {"Any PSV", "Paid retest", "7", "large", "rigid", "pass", "wbs", "rpl", false},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "large", "rigid", "pass", "wbs", "whl", false},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "small", "rigid", "pass", "wbs", "whs", false},
//                {"Any PSV", "Part-paid retest", "10", "large", "articulated", "pass", "wbs", "rgl", false},
//                {"Any PSV", "Part-paid retest", "10", "large", "rigid", "pass", "wbs", "rsl", false},
//                {"Any PSV", "Part-paid retest", "10", "small", "rigid", "pass", "wbs", "rss", false},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "large", "rigid", "pass", "wbs", "p1l", false},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "small", "rigid", "pass", "wbs", "p1s", false},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "large", "rigid", "pass", "wbs", "p8l", false},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "small", "rigid", "pass", "wbs", "p8s", false},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "large", "rigid", "pass", "wbs", "p6l", false},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "small", "rigid", "pass", "wbs", "p6s", false},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "small", "rigid", "pass", "wbs", "wis", false},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "large", "rigid", "pass", "wbs", "wil", false},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "large", "rigid", "pass", "wbs", "wfl", false},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "small", "rigid", "pass", "wbs", "wfs", false},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "large", "rigid", "pass", "wbs", "wel", false},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "small", "rigid", "pass", "wbs", "wes", false},
//                {"Annual test", "Annual test", "1", "large", "rigid", "prs", "wbs", "aal", true},
//                {"Annual test", "Annual test", "1", "small", "rigid", "prs", "wbs", "aas", true},
//                {"Annual test", "Annual test", "1", "large", "articulated", "prs", "wbs", "adl", true},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "large", "rigid", "prs", "wbs", "wdl", true},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "small", "rigid", "prs", "wbs", "wds", true},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "large", "rigid", "prs", "wbs", "wbl", true},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "small", "rigid", "prs", "wbs", "wbs", true},
//                {"Any PSV", "Paid retest", "7", "large", "articulated", "prs", "wbs", "rhl", true},
//                {"Any PSV", "Paid retest", "7", "small", "rigid", "prs", "wbs", "rps", true},
//                {"Any PSV", "Paid retest", "7", "large", "rigid", "prs", "wbs", "rpl", true},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "large", "rigid", "prs", "wbs", "whl", true},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "small", "rigid", "prs", "wbs", "whs", true},
//                {"Any PSV", "Part-paid retest", "10", "large", "articulated", "prs", "wbs", "rgl", true},
//                {"Any PSV", "Part-paid retest", "10", "large", "rigid", "prs", "wbs", "rsl", true},
//                {"Any PSV", "Part-paid retest", "10", "small", "rigid", "prs", "wbs", "rss", true},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "large", "rigid", "prs", "wbs", "p1l", true},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "small", "rigid", "prs", "wbs", "p1s", true},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "large", "rigid", "prs", "wbs", "p8l", true},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "small", "rigid", "prs", "wbs", "p8s", true},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "large", "rigid", "prs", "wbs", "p6l", true},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "small", "rigid", "prs", "wbs", "p6s", true},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "small", "rigid", "prs", "wbs", "wis", true},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "large", "rigid", "prs", "wbs", "wil", true},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "large", "rigid", "prs", "wbs", "wfl", true},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "small", "rigid", "prs", "wbs", "wfs", true},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "large", "rigid", "prs", "wbs", "wel", true},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "small", "rigid", "prs", "wbs", "wes", true},
//                {"Annual test", "Annual test", "1", "large", "rigid", "prs", "wbs", "aal", false},
//                {"Annual test", "Annual test", "1", "small", "rigid", "prs", "wbs", "aas", false},
//                {"Annual test", "Annual test", "1", "large", "articulated", "prs", "wbs", "adl", false},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "large", "rigid", "prs", "wbs", "wdl", false},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "small", "rigid", "prs", "wbs", "wds", false},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "large", "rigid", "prs", "wbs", "wbl", false},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "small", "rigid", "prs", "wbs", "wbs", false},
//                {"Any PSV", "Paid retest", "7", "large", "articulated", "prs", "wbs", "rhl", false},
//                {"Any PSV", "Paid retest", "7", "small", "rigid", "prs", "wbs", "rps", false},
//                {"Any PSV", "Paid retest", "7", "large", "rigid", "prs", "wbs", "rpl", false},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "large", "rigid", "prs", "wbs", "whl", false},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "small", "rigid", "prs", "wbs", "whs", false},
//                {"Any PSV", "Part-paid retest", "10", "large", "articulated", "prs", "wbs", "rgl", false},
//                {"Any PSV", "Part-paid retest", "10", "large", "rigid", "prs", "wbs", "rsl", false},
//                {"Any PSV", "Part-paid retest", "10", "small", "rigid", "prs", "wbs", "rss", false},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "large", "rigid", "prs", "wbs", "p1l", false},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "small", "rigid", "prs", "wbs", "p1s", false},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "large", "rigid", "prs", "wbs", "p8l", false},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "small", "rigid", "prs", "wbs", "p8s", false},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "large", "rigid", "prs", "wbs", "p6l", false},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "small", "rigid", "prs", "wbs", "p6s", false},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "small", "rigid", "prs", "wbs", "wis", false},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "large", "rigid", "prs", "wbs", "wil", false},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "large", "rigid", "prs", "wbs", "wfl", false},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "small", "rigid", "prs", "wbs", "wfs", false},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "large", "rigid", "prs", "wbs", "wel", false},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "small", "rigid", "prs", "wbs", "wes", false},
//
//                {"Annual test", "Annual test", "1", "large", "rigid", "pass", "wdl", "aal", true},
//                {"Annual test", "Annual test", "1", "small", "rigid", "pass", "wdl", "aas", true},
//                {"Annual test", "Annual test", "1", "large", "articulated", "pass", "wdl", "adl", true},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "large", "rigid", "pass", "wdl", "wdl", true},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "small", "rigid", "pass", "wdl", "wds", true},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "large", "rigid", "pass", "wdl", "wbl", true},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "small", "rigid", "pass", "wdl", "wbs", true},
//                {"Any PSV", "Paid retest", "7", "large", "articulated", "pass", "wdl", "rhl", true},
//                {"Any PSV", "Paid retest", "7", "small", "rigid", "pass", "wdl", "rps", true},
//                {"Any PSV", "Paid retest", "7", "large", "rigid", "pass", "wdl", "rpl", true},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "large", "rigid", "pass", "wdl", "whl", true},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "small", "rigid", "pass", "wdl", "whs", true},
//                {"Any PSV", "Part-paid retest", "10", "large", "articulated", "pass", "wdl", "rgl", true},
//                {"Any PSV", "Part-paid retest", "10", "large", "rigid", "pass", "wdl", "rsl", true},
//                {"Any PSV", "Part-paid retest", "10", "small", "rigid", "pass", "wdl", "rss", true},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "large", "rigid", "pass", "wdl", "p1l", true},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "small", "rigid", "pass", "wdl", "p1s", true},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "large", "rigid", "pass", "wdl", "p8l", true},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "small", "rigid", "pass", "wdl", "p8s", true},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "large", "rigid", "pass", "wdl", "p6l", true},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "small", "rigid", "pass", "wdl", "p6s", true},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "small", "rigid", "pass", "wdl", "wis", true},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "large", "rigid", "pass", "wdl", "wil", true},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "large", "rigid", "pass", "wdl", "wfl", true},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "small", "rigid", "pass", "wdl", "wfs", true},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "large", "rigid", "pass", "wdl", "wel", true},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "small", "rigid", "pass", "wdl", "wes", true},
//                {"Annual test", "Annual test", "1", "large", "rigid", "pass", "wdl", "aal", false},
//                {"Annual test", "Annual test", "1", "small", "rigid", "pass", "wdl", "aas", false},
//                {"Annual test", "Annual test", "1", "large", "articulated", "pass", "wdl", "adl", false},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "large", "rigid", "pass", "wdl", "wdl", false},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "small", "rigid", "pass", "wdl", "wds", false},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "large", "rigid", "pass", "wdl", "wbl", false},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "small", "rigid", "pass", "wdl", "wbs", false},
//                {"Any PSV", "Paid retest", "7", "large", "articulated", "pass", "wdl", "rhl", false},
//                {"Any PSV", "Paid retest", "7", "small", "rigid", "pass", "wdl", "rps", false},
//                {"Any PSV", "Paid retest", "7", "large", "rigid", "pass", "wdl", "rpl", false},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "large", "rigid", "pass", "wdl", "whl", false},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "small", "rigid", "pass", "wdl", "whs", false},
//                {"Any PSV", "Part-paid retest", "10", "large", "articulated", "pass", "wdl", "rgl", false},
//                {"Any PSV", "Part-paid retest", "10", "large", "rigid", "pass", "wdl", "rsl", false},
//                {"Any PSV", "Part-paid retest", "10", "small", "rigid", "pass", "wdl", "rss", false},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "large", "rigid", "pass", "wdl", "p1l", false},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "small", "rigid", "pass", "wdl", "p1s", false},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "large", "rigid", "pass", "wdl", "p8l", false},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "small", "rigid", "pass", "wdl", "p8s", false},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "large", "rigid", "pass", "wdl", "p6l", false},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "small", "rigid", "pass", "wdl", "p6s", false},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "small", "rigid", "pass", "wdl", "wis", false},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "large", "rigid", "pass", "wdl", "wil", false},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "large", "rigid", "pass", "wdl", "wfl", false},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "small", "rigid", "pass", "wdl", "wfs", false},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "large", "rigid", "pass", "wdl", "wel", false},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "small", "rigid", "pass", "wdl", "wes", false},
//                {"Annual test", "Annual test", "1", "large", "rigid", "prs", "wdl", "aal", true},
//                {"Annual test", "Annual test", "1", "small", "rigid", "prs", "wdl", "aas", true},
//                {"Annual test", "Annual test", "1", "large", "articulated", "prs", "wdl", "adl", true},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "large", "rigid", "prs", "wdl", "wdl", true},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "small", "rigid", "prs", "wdl", "wds", true},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "large", "rigid", "prs", "wdl", "wbl", true},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "small", "rigid", "prs", "wdl", "wbs", true},
//                {"Any PSV", "Paid retest", "7", "large", "articulated", "prs", "wdl", "rhl", true},
//                {"Any PSV", "Paid retest", "7", "small", "rigid", "prs", "wdl", "rps", true},
//                {"Any PSV", "Paid retest", "7", "large", "rigid", "prs", "wdl", "rpl", true},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "large", "rigid", "prs", "wdl", "whl", true},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "small", "rigid", "prs", "wdl", "whs", true},
//                {"Any PSV", "Part-paid retest", "10", "large", "articulated", "prs", "wdl", "rgl", true},
//                {"Any PSV", "Part-paid retest", "10", "large", "rigid", "prs", "wdl", "rsl", true},
//                {"Any PSV", "Part-paid retest", "10", "small", "rigid", "prs", "wdl", "rss", true},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "large", "rigid", "prs", "wdl", "p1l", true},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "small", "rigid", "prs", "wdl", "p1s", true},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "large", "rigid", "prs", "wdl", "p8l", true},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "small", "rigid", "prs", "wdl", "p8s", true},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "large", "rigid", "prs", "wdl", "p6l", true},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "small", "rigid", "prs", "wdl", "p6s", true},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "small", "rigid", "prs", "wdl", "wis", true},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "large", "rigid", "prs", "wdl", "wil", true},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "large", "rigid", "prs", "wdl", "wfl", true},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "small", "rigid", "prs", "wdl", "wfs", true},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "large", "rigid", "prs", "wdl", "wel", true},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "small", "rigid", "prs", "wdl", "wes", true},
//                {"Annual test", "Annual test", "1", "large", "rigid", "prs", "wdl", "aal", false},
//                {"Annual test", "Annual test", "1", "small", "rigid", "prs", "wdl", "aas", false},
//                {"Annual test", "Annual test", "1", "large", "articulated", "prs", "wdl", "adl", false},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "large", "rigid", "prs", "wdl", "wdl", false},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "small", "rigid", "prs", "wdl", "wds", false},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "large", "rigid", "prs", "wdl", "wbl", false},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "small", "rigid", "prs", "wdl", "wbs", false},
//                {"Any PSV", "Paid retest", "7", "large", "articulated", "prs", "wdl", "rhl", false},
//                {"Any PSV", "Paid retest", "7", "small", "rigid", "prs", "wdl", "rps", false},
//                {"Any PSV", "Paid retest", "7", "large", "rigid", "prs", "wdl", "rpl", false},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "large", "rigid", "prs", "wdl", "whl", false},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "small", "rigid", "prs", "wdl", "whs", false},
//                {"Any PSV", "Part-paid retest", "10", "large", "articulated", "prs", "wdl", "rgl", false},
//                {"Any PSV", "Part-paid retest", "10", "large", "rigid", "prs", "wdl", "rsl", false},
//                {"Any PSV", "Part-paid retest", "10", "small", "rigid", "prs", "wdl", "rss", false},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "large", "rigid", "prs", "wdl", "p1l", false},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "small", "rigid", "prs", "wdl", "p1s", false},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "large", "rigid", "prs", "wdl", "p8l", false},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "small", "rigid", "prs", "wdl", "p8s", false},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "large", "rigid", "prs", "wdl", "p6l", false},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "small", "rigid", "prs", "wdl", "p6s", false},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "small", "rigid", "prs", "wdl", "wis", false},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "large", "rigid", "prs", "wdl", "wil", false},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "large", "rigid", "prs", "wdl", "wfl", false},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "small", "rigid", "prs", "wdl", "wfs", false},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "large", "rigid", "prs", "wdl", "wel", false},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "small", "rigid", "prs", "wdl", "wes", false},
//
//                {"Annual test", "Annual test", "1", "large", "rigid", "pass", "wds", "aal", true},
//                {"Annual test", "Annual test", "1", "small", "rigid", "pass", "wds", "aas", true},
//                {"Annual test", "Annual test", "1", "large", "articulated", "pass", "wds", "adl", true},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "large", "rigid", "pass", "wds", "wdl", true},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "small", "rigid", "pass", "wds", "wds", true},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "large", "rigid", "pass", "wds", "wbl", true},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "small", "rigid", "pass", "wds", "wbs", true},
//                {"Any PSV", "Paid retest", "7", "large", "articulated", "pass", "wds", "rhl", true},
//                {"Any PSV", "Paid retest", "7", "small", "rigid", "pass", "wds", "rps", true},
//                {"Any PSV", "Paid retest", "7", "large", "rigid", "pass", "wds", "rpl", true},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "large", "rigid", "pass", "wds", "whl", true},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "small", "rigid", "pass", "wds", "whs", true},
//                {"Any PSV", "Part-paid retest", "10", "large", "articulated", "pass", "wds", "rgl", true},
//                {"Any PSV", "Part-paid retest", "10", "large", "rigid", "pass", "wds", "rsl", true},
                {"Any PSV", "Part-paid retest", "10", "small", "rigid", "pass", "wds", "rss", true},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "large", "rigid", "pass", "wds", "p1l", true},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "small", "rigid", "pass", "wds", "p1s", true},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "large", "rigid", "pass", "wds", "p8l", true},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "small", "rigid", "pass", "wds", "p8s", true},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "large", "rigid", "pass", "wds", "p6l", true},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "small", "rigid", "pass", "wds", "p6s", true},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "small", "rigid", "pass", "wds", "wis", true},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "large", "rigid", "pass", "wds", "wil", true},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "large", "rigid", "pass", "wds", "wfl", true},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "small", "rigid", "pass", "wds", "wfs", true},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "large", "rigid", "pass", "wds", "wel", true},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "small", "rigid", "pass", "wds", "wes", true},
//                {"Annual test", "Annual test", "1", "large", "rigid", "pass", "wds", "aal", false},
//                {"Annual test", "Annual test", "1", "small", "rigid", "pass", "wds", "aas", false},
//                {"Annual test", "Annual test", "1", "large", "articulated", "pass", "wds", "adl", false},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "large", "rigid", "pass", "wds", "wdl", false},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "small", "rigid", "pass", "wds", "wds", false},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "large", "rigid", "pass", "wds", "wbl", false},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "small", "rigid", "pass", "wds", "wbs", false},
//                {"Any PSV", "Paid retest", "7", "large", "articulated", "pass", "wds", "rhl", false},
//                {"Any PSV", "Paid retest", "7", "small", "rigid", "pass", "wds", "rps", false},
//                {"Any PSV", "Paid retest", "7", "large", "rigid", "pass", "wds", "rpl", false},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "large", "rigid", "pass", "wds", "whl", false},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "small", "rigid", "pass", "wds", "whs", false},
//                {"Any PSV", "Part-paid retest", "10", "large", "articulated", "pass", "wds", "rgl", false},
//                {"Any PSV", "Part-paid retest", "10", "large", "rigid", "pass", "wds", "rsl", false},
//                {"Any PSV", "Part-paid retest", "10", "small", "rigid", "pass", "wds", "rss", false},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "large", "rigid", "pass", "wds", "p1l", false},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "small", "rigid", "pass", "wds", "p1s", false},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "large", "rigid", "pass", "wds", "p8l", false},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "small", "rigid", "pass", "wds", "p8s", false},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "large", "rigid", "pass", "wds", "p6l", false},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "small", "rigid", "pass", "wds", "p6s", false},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "small", "rigid", "pass", "wds", "wis", false},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "large", "rigid", "pass", "wds", "wil", false},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "large", "rigid", "pass", "wds", "wfl", false},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "small", "rigid", "pass", "wds", "wfs", false},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "large", "rigid", "pass", "wds", "wel", false},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "small", "rigid", "pass", "wds", "wes", false},
//                {"Annual test", "Annual test", "1", "large", "rigid", "prs", "wds", "aal", true},
//                {"Annual test", "Annual test", "1", "small", "rigid", "prs", "wds", "aas", true},
//                {"Annual test", "Annual test", "1", "large", "articulated", "prs", "wds", "adl", true},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "large", "rigid", "prs", "wds", "wdl", true},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "small", "rigid", "prs", "wds", "wds", true},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "large", "rigid", "prs", "wds", "wbl", true},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "small", "rigid", "prs", "wds", "wbs", true},
//                {"Any PSV", "Paid retest", "7", "large", "articulated", "prs", "wds", "rhl", true},
//                {"Any PSV", "Paid retest", "7", "small", "rigid", "prs", "wds", "rps", true},
//                {"Any PSV", "Paid retest", "7", "large", "rigid", "prs", "wds", "rpl", true},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "large", "rigid", "prs", "wds", "whl", true},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "small", "rigid", "prs", "wds", "whs", true},
//                {"Any PSV", "Part-paid retest", "10", "large", "articulated", "prs", "wds", "rgl", true},
//                {"Any PSV", "Part-paid retest", "10", "large", "rigid", "prs", "wds", "rsl", true},
//                {"Any PSV", "Part-paid retest", "10", "small", "rigid", "prs", "wds", "rss", true},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "large", "rigid", "prs", "wds", "p1l", true},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "small", "rigid", "prs", "wds", "p1s", true},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "large", "rigid", "prs", "wds", "p8l", true},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "small", "rigid", "prs", "wds", "p8s", true},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "large", "rigid", "prs", "wds", "p6l", true},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "small", "rigid", "prs", "wds", "p6s", true},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "small", "rigid", "prs", "wds", "wis", true},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "large", "rigid", "prs", "wds", "wil", true},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "large", "rigid", "prs", "wds", "wfl", true},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "small", "rigid", "prs", "wds", "wfs", true},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "large", "rigid", "prs", "wds", "wel", true},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "small", "rigid", "prs", "wds", "wes", true},
//                {"Annual test", "Annual test", "1", "large", "rigid", "prs", "wds", "aal", false},
//                {"Annual test", "Annual test", "1", "small", "rigid", "prs", "wds", "aas", false},
//                {"Annual test", "Annual test", "1", "large", "articulated", "prs", "wds", "adl", false},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "large", "rigid", "prs", "wds", "wdl", false},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "small", "rigid", "prs", "wds", "wds", false},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "large", "rigid", "prs", "wds", "wbl", false},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "small", "rigid", "prs", "wds", "wbs", false},
//                {"Any PSV", "Paid retest", "7", "large", "articulated", "prs", "wds", "rhl", false},
//                {"Any PSV", "Paid retest", "7", "small", "rigid", "prs", "wds", "rps", false},
//                {"Any PSV", "Paid retest", "7", "large", "rigid", "prs", "wds", "rpl", false},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "large", "rigid", "prs", "wds", "whl", false},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "small", "rigid", "prs", "wds", "whs", false},
//                {"Any PSV", "Part-paid retest", "10", "large", "articulated", "prs", "wds", "rgl", false},
//                {"Any PSV", "Part-paid retest", "10", "large", "rigid", "prs", "wds", "rsl", false},
//                {"Any PSV", "Part-paid retest", "10", "small", "rigid", "prs", "wds", "rss", false},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "large", "rigid", "prs", "wds", "p1l", false},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "small", "rigid", "prs", "wds", "p1s", false},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "large", "rigid", "prs", "wds", "p8l", false},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "small", "rigid", "prs", "wds", "p8s", false},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "large", "rigid", "prs", "wds", "p6l", false},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "small", "rigid", "prs", "wds", "p6s", false},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "small", "rigid", "prs", "wds", "wis", false},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "large", "rigid", "prs", "wds", "wil", false},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "large", "rigid", "prs", "wds", "wfl", false},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "small", "rigid", "prs", "wds", "wfs", false},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "large", "rigid", "prs", "wds", "wel", false},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "small", "rigid", "prs", "wds", "wes", false},
//
//                {"Annual test", "Annual test", "1", "large", "rigid", "pass", "wel", "aal", true},
//                {"Annual test", "Annual test", "1", "small", "rigid", "pass", "wel", "aas", true},
//                {"Annual test", "Annual test", "1", "large", "articulated", "pass", "wel", "adl", true},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "large", "rigid", "pass", "wel", "wdl", true},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "small", "rigid", "pass", "wel", "wds", true},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "large", "rigid", "pass", "wel", "wbl", true},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "small", "rigid", "pass", "wel", "wbs", true},
//                {"Any PSV", "Paid retest", "7", "large", "articulated", "pass", "wel", "rhl", true},
//                {"Any PSV", "Paid retest", "7", "small", "rigid", "pass", "wel", "rps", true},
//                {"Any PSV", "Paid retest", "7", "large", "rigid", "pass", "wel", "rpl", true},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "large", "rigid", "pass", "wel", "whl", true},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "small", "rigid", "pass", "wel", "whs", true},
//                {"Any PSV", "Part-paid retest", "10", "large", "articulated", "pass", "wel", "rgl", true},
//                {"Any PSV", "Part-paid retest", "10", "large", "rigid", "pass", "wel", "rsl", true},
//                {"Any PSV", "Part-paid retest", "10", "small", "rigid", "pass", "wel", "rss", true},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "large", "rigid", "pass", "wel", "p1l", true},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "small", "rigid", "pass", "wel", "p1s", true},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "large", "rigid", "pass", "wel", "p8l", true},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "small", "rigid", "pass", "wel", "p8s", true},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "large", "rigid", "pass", "wel", "p6l", true},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "small", "rigid", "pass", "wel", "p6s", true},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "small", "rigid", "pass", "wel", "wis", true},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "large", "rigid", "pass", "wel", "wil", true},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "large", "rigid", "pass", "wel", "wfl", true},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "small", "rigid", "pass", "wel", "wfs", true},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "large", "rigid", "pass", "wel", "wel", true},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "small", "rigid", "pass", "wel", "wes", true},
//                {"Annual test", "Annual test", "1", "large", "rigid", "pass", "wel", "aal", false},
//                {"Annual test", "Annual test", "1", "small", "rigid", "pass", "wel", "aas", false},
//                {"Annual test", "Annual test", "1", "large", "articulated", "pass", "wel", "adl", false},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "large", "rigid", "pass", "wel", "wdl", false},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "small", "rigid", "pass", "wel", "wds", false},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "large", "rigid", "pass", "wel", "wbl", false},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "small", "rigid", "pass", "wel", "wbs", false},
//                {"Any PSV", "Paid retest", "7", "large", "articulated", "pass", "wel", "rhl", false},
//                {"Any PSV", "Paid retest", "7", "small", "rigid", "pass", "wel", "rps", false},
//                {"Any PSV", "Paid retest", "7", "large", "rigid", "pass", "wel", "rpl", false},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "large", "rigid", "pass", "wel", "whl", false},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "small", "rigid", "pass", "wel", "whs", false},
//                {"Any PSV", "Part-paid retest", "10", "large", "articulated", "pass", "wel", "rgl", false},
//                {"Any PSV", "Part-paid retest", "10", "large", "rigid", "pass", "wel", "rsl", false},
//                {"Any PSV", "Part-paid retest", "10", "small", "rigid", "pass", "wel", "rss", false},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "large", "rigid", "pass", "wel", "p1l", false},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "small", "rigid", "pass", "wel", "p1s", false},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "large", "rigid", "pass", "wel", "p8l", false},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "small", "rigid", "pass", "wel", "p8s", false},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "large", "rigid", "pass", "wel", "p6l", false},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "small", "rigid", "pass", "wel", "p6s", false},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "small", "rigid", "pass", "wel", "wis", false},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "large", "rigid", "pass", "wel", "wil", false},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "large", "rigid", "pass", "wel", "wfl", false},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "small", "rigid", "pass", "wel", "wfs", false},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "large", "rigid", "pass", "wel", "wel", false},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "small", "rigid", "pass", "wel", "wes", false},
//                {"Annual test", "Annual test", "1", "large", "rigid", "prs", "wel", "aal", true},
//                {"Annual test", "Annual test", "1", "small", "rigid", "prs", "wel", "aas", true},
//                {"Annual test", "Annual test", "1", "large", "articulated", "prs", "wel", "adl", true},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "large", "rigid", "prs", "wel", "wdl", true},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "small", "rigid", "prs", "wel", "wds", true},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "large", "rigid", "prs", "wel", "wbl", true},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "small", "rigid", "prs", "wel", "wbs", true},
//                {"Any PSV", "Paid retest", "7", "large", "articulated", "prs", "wel", "rhl", true},
//                {"Any PSV", "Paid retest", "7", "small", "rigid", "prs", "wel", "rps", true},
//                {"Any PSV", "Paid retest", "7", "large", "rigid", "prs", "wel", "rpl", true},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "large", "rigid", "prs", "wel", "whl", true},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "small", "rigid", "prs", "wel", "whs", true},
//                {"Any PSV", "Part-paid retest", "10", "large", "articulated", "prs", "wel", "rgl", true},
//                {"Any PSV", "Part-paid retest", "10", "large", "rigid", "prs", "wel", "rsl", true},
//                {"Any PSV", "Part-paid retest", "10", "small", "rigid", "prs", "wel", "rss", true},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "large", "rigid", "prs", "wel", "p1l", true},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "small", "rigid", "prs", "wel", "p1s", true},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "large", "rigid", "prs", "wel", "p8l", true},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "small", "rigid", "prs", "wel", "p8s", true},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "large", "rigid", "prs", "wel", "p6l", true},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "small", "rigid", "prs", "wel", "p6s", true},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "small", "rigid", "prs", "wel", "wis", true},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "large", "rigid", "prs", "wel", "wil", true},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "large", "rigid", "prs", "wel", "wfl", true},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "small", "rigid", "prs", "wel", "wfs", true},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "large", "rigid", "prs", "wel", "wel", true},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "small", "rigid", "prs", "wel", "wes", true},
//                {"Annual test", "Annual test", "1", "large", "rigid", "prs", "wel", "aal", false},
//                {"Annual test", "Annual test", "1", "small", "rigid", "prs", "wel", "aas", false},
//                {"Annual test", "Annual test", "1", "large", "articulated", "prs", "wel", "adl", false},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "large", "rigid", "prs", "wel", "wdl", false},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "small", "rigid", "prs", "wel", "wds", false},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "large", "rigid", "prs", "wel", "wbl", false},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "small", "rigid", "prs", "wel", "wbs", false},
//                {"Any PSV", "Paid retest", "7", "large", "articulated", "prs", "wel", "rhl", false},
//                {"Any PSV", "Paid retest", "7", "small", "rigid", "prs", "wel", "rps", false},
//                {"Any PSV", "Paid retest", "7", "large", "rigid", "prs", "wel", "rpl", false},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "large", "rigid", "prs", "wel", "whl", false},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "small", "rigid", "prs", "wel", "whs", false},
//                {"Any PSV", "Part-paid retest", "10", "large", "articulated", "prs", "wel", "rgl", false},
//                {"Any PSV", "Part-paid retest", "10", "large", "rigid", "prs", "wel", "rsl", false},
//                {"Any PSV", "Part-paid retest", "10", "small", "rigid", "prs", "wel", "rss", false},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "large", "rigid", "prs", "wel", "p1l", false},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "small", "rigid", "prs", "wel", "p1s", false},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "large", "rigid", "prs", "wel", "p8l", false},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "small", "rigid", "prs", "wel", "p8s", false},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "large", "rigid", "prs", "wel", "p6l", false},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "small", "rigid", "prs", "wel", "p6s", false},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "small", "rigid", "prs", "wel", "wis", false},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "large", "rigid", "prs", "wel", "wil", false},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "large", "rigid", "prs", "wel", "wfl", false},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "small", "rigid", "prs", "wel", "wfs", false},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "large", "rigid", "prs", "wel", "wel", false},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "small", "rigid", "prs", "wel", "wes", false},
//
//                {"Annual test", "Annual test", "1", "large", "rigid", "pass", "wes", "aal", true},
//                {"Annual test", "Annual test", "1", "small", "rigid", "pass", "wes", "aas", true},
//                {"Annual test", "Annual test", "1", "large", "articulated", "pass", "wes", "adl", true},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "large", "rigid", "pass", "wes", "wdl", true},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "small", "rigid", "pass", "wes", "wds", true},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "large", "rigid", "pass", "wes", "wbl", true},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "small", "rigid", "pass", "wes", "wbs", true},
//                {"Any PSV", "Paid retest", "7", "large", "articulated", "pass", "wes", "rhl", true},
//                {"Any PSV", "Paid retest", "7", "small", "rigid", "pass", "wes", "rps", true},
//                {"Any PSV", "Paid retest", "7", "large", "rigid", "pass", "wes", "rpl", true},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "large", "rigid", "pass", "wes", "whl", true},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "small", "rigid", "pass", "wes", "whs", true},
//                {"Any PSV", "Part-paid retest", "10", "large", "articulated", "pass", "wes", "rgl", true},
//                {"Any PSV", "Part-paid retest", "10", "large", "rigid", "pass", "wes", "rsl", true},
//                {"Any PSV", "Part-paid retest", "10", "small", "rigid", "pass", "wes", "rss", true},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "large", "rigid", "pass", "wes", "p1l", true},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "small", "rigid", "pass", "wes", "p1s", true},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "large", "rigid", "pass", "wes", "p8l", true},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "small", "rigid", "pass", "wes", "p8s", true},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "large", "rigid", "pass", "wes", "p6l", true},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "small", "rigid", "pass", "wes", "p6s", true},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "small", "rigid", "pass", "wes", "wis", true},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "large", "rigid", "pass", "wes", "wil", true},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "large", "rigid", "pass", "wes", "wfl", true},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "small", "rigid", "pass", "wes", "wfs", true},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "large", "rigid", "pass", "wes", "wel", true},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "small", "rigid", "pass", "wes", "wes", true},
//                {"Annual test", "Annual test", "1", "large", "rigid", "pass", "wes", "aal", false},
//                {"Annual test", "Annual test", "1", "small", "rigid", "pass", "wes", "aas", false},
//                {"Annual test", "Annual test", "1", "large", "articulated", "pass", "wes", "adl", false},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "large", "rigid", "pass", "wes", "wdl", false},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "small", "rigid", "pass", "wes", "wds", false},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "large", "rigid", "pass", "wes", "wbl", false},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "small", "rigid", "pass", "wes", "wbs", false},
//                {"Any PSV", "Paid retest", "7", "large", "articulated", "pass", "wes", "rhl", false},
//                {"Any PSV", "Paid retest", "7", "small", "rigid", "pass", "wes", "rps", false},
//                {"Any PSV", "Paid retest", "7", "large", "rigid", "pass", "wes", "rpl", false},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "large", "rigid", "pass", "wes", "whl", false},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "small", "rigid", "pass", "wes", "whs", false},
//                {"Any PSV", "Part-paid retest", "10", "large", "articulated", "pass", "wes", "rgl", false},
//                {"Any PSV", "Part-paid retest", "10", "large", "rigid", "pass", "wes", "rsl", false},
//                {"Any PSV", "Part-paid retest", "10", "small", "rigid", "pass", "wes", "rss", false},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "large", "rigid", "pass", "wes", "p1l", false},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "small", "rigid", "pass", "wes", "p1s", false},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "large", "rigid", "pass", "wes", "p8l", false},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "small", "rigid", "pass", "wes", "p8s", false},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "large", "rigid", "pass", "wes", "p6l", false},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "small", "rigid", "pass", "wes", "p6s", false},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "small", "rigid", "pass", "wes", "wis", false},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "large", "rigid", "pass", "wes", "wil", false},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "large", "rigid", "pass", "wes", "wfl", false},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "small", "rigid", "pass", "wes", "wfs", false},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "large", "rigid", "pass", "wes", "wel", false},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "small", "rigid", "pass", "wes", "wes", false},
//                {"Annual test", "Annual test", "1", "large", "rigid", "prs", "wes", "aal", true},
//                {"Annual test", "Annual test", "1", "small", "rigid", "prs", "wes", "aas", true},
//                {"Annual test", "Annual test", "1", "large", "articulated", "prs", "wes", "adl", true},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "large", "rigid", "prs", "wes", "wdl", true},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "small", "rigid", "prs", "wes", "wds", true},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "large", "rigid", "prs", "wes", "wbl", true},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "small", "rigid", "prs", "wes", "wbs", true},
//                {"Any PSV", "Paid retest", "7", "large", "articulated", "prs", "wes", "rhl", true},
//                {"Any PSV", "Paid retest", "7", "small", "rigid", "prs", "wes", "rps", true},
//                {"Any PSV", "Paid retest", "7", "large", "rigid", "prs", "wes", "rpl", true},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "large", "rigid", "prs", "wes", "whl", true},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "small", "rigid", "prs", "wes", "whs", true},
//                {"Any PSV", "Part-paid retest", "10", "large", "articulated", "prs", "wes", "rgl", true},
//                {"Any PSV", "Part-paid retest", "10", "large", "rigid", "prs", "wes", "rsl", true},
//                {"Any PSV", "Part-paid retest", "10", "small", "rigid", "prs", "wes", "rss", true},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "large", "rigid", "prs", "wes", "p1l", true},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "small", "rigid", "prs", "wes", "p1s", true},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "large", "rigid", "prs", "wes", "p8l", true},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "small", "rigid", "prs", "wes", "p8s", true},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "large", "rigid", "prs", "wes", "p6l", true},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "small", "rigid", "prs", "wes", "p6s", true},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "small", "rigid", "prs", "wes", "wis", true},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "large", "rigid", "prs", "wes", "wil", true},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "large", "rigid", "prs", "wes", "wfl", true},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "small", "rigid", "prs", "wes", "wfs", true},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "large", "rigid", "prs", "wes", "wel", true},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "small", "rigid", "prs", "wes", "wes", true},
//                {"Annual test", "Annual test", "1", "large", "rigid", "prs", "wes", "aal", false},
//                {"Annual test", "Annual test", "1", "small", "rigid", "prs", "wes", "aas", false},
//                {"Annual test", "Annual test", "1", "large", "articulated", "prs", "wes", "adl", false},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "large", "rigid", "prs", "wes", "wdl", false},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "small", "rigid", "prs", "wes", "wds", false},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "large", "rigid", "prs", "wes", "wbl", false},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "small", "rigid", "prs", "wes", "wbs", false},
//                {"Any PSV", "Paid retest", "7", "large", "articulated", "prs", "wes", "rhl", false},
//                {"Any PSV", "Paid retest", "7", "small", "rigid", "prs", "wes", "rps", false},
//                {"Any PSV", "Paid retest", "7", "large", "rigid", "prs", "wes", "rpl", false},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "large", "rigid", "prs", "wes", "whl", false},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "small", "rigid", "prs", "wes", "whs", false},
//                {"Any PSV", "Part-paid retest", "10", "large", "articulated", "prs", "wes", "rgl", false},
//                {"Any PSV", "Part-paid retest", "10", "large", "rigid", "prs", "wes", "rsl", false},
//                {"Any PSV", "Part-paid retest", "10", "small", "rigid", "prs", "wes", "rss", false},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "large", "rigid", "prs", "wes", "p1l", false},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "small", "rigid", "prs", "wes", "p1s", false},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "large", "rigid", "prs", "wes", "p8l", false},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "small", "rigid", "prs", "wes", "p8s", false},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "large", "rigid", "prs", "wes", "p6l", false},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "small", "rigid", "prs", "wes", "p6s", false},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "small", "rigid", "prs", "wes", "wis", false},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "large", "rigid", "prs", "wes", "wil", false},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "large", "rigid", "prs", "wes", "wfl", false},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "small", "rigid", "prs", "wes", "wfs", false},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "large", "rigid", "prs", "wes", "wel", false},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "small", "rigid", "prs", "wes", "wes", false},
//
//                {"Annual test", "Annual test", "1", "large", "rigid", "pass", "wfl", "aal", true},
//                {"Annual test", "Annual test", "1", "small", "rigid", "pass", "wfl", "aas", true},
//                {"Annual test", "Annual test", "1", "large", "articulated", "pass", "wfl", "adl", true},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "large", "rigid", "pass", "wfl", "wdl", true},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "small", "rigid", "pass", "wfl", "wds", true},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "large", "rigid", "pass", "wfl", "wbl", true},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "small", "rigid", "pass", "wfl", "wbs", true},
//                {"Any PSV", "Paid retest", "7", "large", "articulated", "pass", "wfl", "rhl", true},
//                {"Any PSV", "Paid retest", "7", "small", "rigid", "pass", "wfl", "rps", true},
//                {"Any PSV", "Paid retest", "7", "large", "rigid", "pass", "wfl", "rpl", true},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "large", "rigid", "pass", "wfl", "whl", true},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "small", "rigid", "pass", "wfl", "whs", true},
//                {"Any PSV", "Part-paid retest", "10", "large", "articulated", "pass", "wfl", "rgl", true},
//                {"Any PSV", "Part-paid retest", "10", "large", "rigid", "pass", "wfl", "rsl", true},
//                {"Any PSV", "Part-paid retest", "10", "small", "rigid", "pass", "wfl", "rss", true},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "large", "rigid", "pass", "wfl", "p1l", true},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "small", "rigid", "pass", "wfl", "p1s", true},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "large", "rigid", "pass", "wfl", "p8l", true},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "small", "rigid", "pass", "wfl", "p8s", true},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "large", "rigid", "pass", "wfl", "p6l", true},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "small", "rigid", "pass", "wfl", "p6s", true},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "small", "rigid", "pass", "wfl", "wis", true},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "large", "rigid", "pass", "wfl", "wil", true},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "large", "rigid", "pass", "wfl", "wfl", true},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "small", "rigid", "pass", "wfl", "wfs", true},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "large", "rigid", "pass", "wfl", "wel", true},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "small", "rigid", "pass", "wfl", "wes", true},
//                {"Annual test", "Annual test", "1", "large", "rigid", "pass", "wfl", "aal", false},
//                {"Annual test", "Annual test", "1", "small", "rigid", "pass", "wfl", "aas", false},
//                {"Annual test", "Annual test", "1", "large", "articulated", "pass", "wfl", "adl", false},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "large", "rigid", "pass", "wfl", "wdl", false},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "small", "rigid", "pass", "wfl", "wds", false},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "large", "rigid", "pass", "wfl", "wbl", false},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "small", "rigid", "pass", "wfl", "wbs", false},
//                {"Any PSV", "Paid retest", "7", "large", "articulated", "pass", "wfl", "rhl", false},
//                {"Any PSV", "Paid retest", "7", "small", "rigid", "pass", "wfl", "rps", false},
//                {"Any PSV", "Paid retest", "7", "large", "rigid", "pass", "wfl", "rpl", false},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "large", "rigid", "pass", "wfl", "whl", false},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "small", "rigid", "pass", "wfl", "whs", false},
//                {"Any PSV", "Part-paid retest", "10", "large", "articulated", "pass", "wfl", "rgl", false},
//                {"Any PSV", "Part-paid retest", "10", "large", "rigid", "pass", "wfl", "rsl", false},
//                {"Any PSV", "Part-paid retest", "10", "small", "rigid", "pass", "wfl", "rss", false},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "large", "rigid", "pass", "wfl", "p1l", false},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "small", "rigid", "pass", "wfl", "p1s", false},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "large", "rigid", "pass", "wfl", "p8l", false},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "small", "rigid", "pass", "wfl", "p8s", false},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "large", "rigid", "pass", "wfl", "p6l", false},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "small", "rigid", "pass", "wfl", "p6s", false},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "small", "rigid", "pass", "wfl", "wis", false},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "large", "rigid", "pass", "wfl", "wil", false},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "large", "rigid", "pass", "wfl", "wfl", false},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "small", "rigid", "pass", "wfl", "wfs", false},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "large", "rigid", "pass", "wfl", "wel", false},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "small", "rigid", "pass", "wfl", "wes", false},
//                {"Annual test", "Annual test", "1", "large", "rigid", "prs", "wfl", "aal", true},
//                {"Annual test", "Annual test", "1", "small", "rigid", "prs", "wfl", "aas", true},
//                {"Annual test", "Annual test", "1", "large", "articulated", "prs", "wfl", "adl", true},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "large", "rigid", "prs", "wfl", "wdl", true},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "small", "rigid", "prs", "wfl", "wds", true},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "large", "rigid", "prs", "wfl", "wbl", true},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "small", "rigid", "prs", "wfl", "wbs", true},
//                {"Any PSV", "Paid retest", "7", "large", "articulated", "prs", "wfl", "rhl", true},
//                {"Any PSV", "Paid retest", "7", "small", "rigid", "prs", "wfl", "rps", true},
//                {"Any PSV", "Paid retest", "7", "large", "rigid", "prs", "wfl", "rpl", true},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "large", "rigid", "prs", "wfl", "whl", true},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "small", "rigid", "prs", "wfl", "whs", true},
//                {"Any PSV", "Part-paid retest", "10", "large", "articulated", "prs", "wfl", "rgl", true},
//                {"Any PSV", "Part-paid retest", "10", "large", "rigid", "prs", "wfl", "rsl", true},
//                {"Any PSV", "Part-paid retest", "10", "small", "rigid", "prs", "wfl", "rss", true},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "large", "rigid", "prs", "wfl", "p1l", true},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "small", "rigid", "prs", "wfl", "p1s", true},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "large", "rigid", "prs", "wfl", "p8l", true},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "small", "rigid", "prs", "wfl", "p8s", true},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "large", "rigid", "prs", "wfl", "p6l", true},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "small", "rigid", "prs", "wfl", "p6s", true},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "small", "rigid", "prs", "wfl", "wis", true},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "large", "rigid", "prs", "wfl", "wil", true},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "large", "rigid", "prs", "wfl", "wfl", true},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "small", "rigid", "prs", "wfl", "wfs", true},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "large", "rigid", "prs", "wfl", "wel", true},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "small", "rigid", "prs", "wfl", "wes", true},
//                {"Annual test", "Annual test", "1", "large", "rigid", "prs", "wfl", "aal", false},
//                {"Annual test", "Annual test", "1", "small", "rigid", "prs", "wfl", "aas", false},
//                {"Annual test", "Annual test", "1", "large", "articulated", "prs", "wfl", "adl", false},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "large", "rigid", "prs", "wfl", "wdl", false},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "small", "rigid", "prs", "wfl", "wds", false},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "large", "rigid", "prs", "wfl", "wbl", false},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "small", "rigid", "prs", "wfl", "wbs", false},
//                {"Any PSV", "Paid retest", "7", "large", "articulated", "prs", "wfl", "rhl", false},
//                {"Any PSV", "Paid retest", "7", "small", "rigid", "prs", "wfl", "rps", false},
//                {"Any PSV", "Paid retest", "7", "large", "rigid", "prs", "wfl", "rpl", false},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "large", "rigid", "prs", "wfl", "whl", false},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "small", "rigid", "prs", "wfl", "whs", false},
//                {"Any PSV", "Part-paid retest", "10", "large", "articulated", "prs", "wfl", "rgl", false},
//                {"Any PSV", "Part-paid retest", "10", "large", "rigid", "prs", "wfl", "rsl", false},
//                {"Any PSV", "Part-paid retest", "10", "small", "rigid", "prs", "wfl", "rss", false},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "large", "rigid", "prs", "wfl", "p1l", false},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "small", "rigid", "prs", "wfl", "p1s", false},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "large", "rigid", "prs", "wfl", "p8l", false},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "small", "rigid", "prs", "wfl", "p8s", false},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "large", "rigid", "prs", "wfl", "p6l", false},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "small", "rigid", "prs", "wfl", "p6s", false},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "small", "rigid", "prs", "wfl", "wis", false},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "large", "rigid", "prs", "wfl", "wil", false},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "large", "rigid", "prs", "wfl", "wfl", false},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "small", "rigid", "prs", "wfl", "wfs", false},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "large", "rigid", "prs", "wfl", "wel", false},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "small", "rigid", "prs", "wfl", "wes", false},
//
//                {"Annual test", "Annual test", "1", "large", "rigid", "pass", "wfs", "aal", true},
//                {"Annual test", "Annual test", "1", "small", "rigid", "pass", "wfs", "aas", true},
//                {"Annual test", "Annual test", "1", "large", "articulated", "pass", "wfs", "adl", true},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "large", "rigid", "pass", "wfs", "wdl", true},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "small", "rigid", "pass", "wfs", "wds", true},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "large", "rigid", "pass", "wfs", "wbl", true},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "small", "rigid", "pass", "wfs", "wbs", true},
//                {"Any PSV", "Paid retest", "7", "large", "articulated", "pass", "wfs", "rhl", true},
//                {"Any PSV", "Paid retest", "7", "small", "rigid", "pass", "wfs", "rps", true},
//                {"Any PSV", "Paid retest", "7", "large", "rigid", "pass", "wfs", "rpl", true},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "large", "rigid", "pass", "wfs", "whl", true},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "small", "rigid", "pass", "wfs", "whs", true},
//                {"Any PSV", "Part-paid retest", "10", "large", "articulated", "pass", "wfs", "rgl", true},
//                {"Any PSV", "Part-paid retest", "10", "large", "rigid", "pass", "wfs", "rsl", true},
//                {"Any PSV", "Part-paid retest", "10", "small", "rigid", "pass", "wfs", "rss", true},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "large", "rigid", "pass", "wfs", "p1l", true},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "small", "rigid", "pass", "wfs", "p1s", true},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "large", "rigid", "pass", "wfs", "p8l", true},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "small", "rigid", "pass", "wfs", "p8s", true},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "large", "rigid", "pass", "wfs", "p6l", true},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "small", "rigid", "pass", "wfs", "p6s", true},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "small", "rigid", "pass", "wfs", "wis", true},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "large", "rigid", "pass", "wfs", "wil", true},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "large", "rigid", "pass", "wfs", "wfl", true},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "small", "rigid", "pass", "wfs", "wfs", true},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "large", "rigid", "pass", "wfs", "wel", true},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "small", "rigid", "pass", "wfs", "wes", true},
//                {"Annual test", "Annual test", "1", "large", "rigid", "pass", "wfs", "aal", false},
//                {"Annual test", "Annual test", "1", "small", "rigid", "pass", "wfs", "aas", false},
//                {"Annual test", "Annual test", "1", "large", "articulated", "pass", "wfs", "adl", false},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "large", "rigid", "pass", "wfs", "wdl", false},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "small", "rigid", "pass", "wfs", "wds", false},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "large", "rigid", "pass", "wfs", "wbl", false},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "small", "rigid", "pass", "wfs", "wbs", false},
//                {"Any PSV", "Paid retest", "7", "large", "articulated", "pass", "wfs", "rhl", false},
//                {"Any PSV", "Paid retest", "7", "small", "rigid", "pass", "wfs", "rps", false},
//                {"Any PSV", "Paid retest", "7", "large", "rigid", "pass", "wfs", "rpl", false},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "large", "rigid", "pass", "wfs", "whl", false},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "small", "rigid", "pass", "wfs", "whs", false},
//                {"Any PSV", "Part-paid retest", "10", "large", "articulated", "pass", "wfs", "rgl", false},
//                {"Any PSV", "Part-paid retest", "10", "large", "rigid", "pass", "wfs", "rsl", false},
//                {"Any PSV", "Part-paid retest", "10", "small", "rigid", "pass", "wfs", "rss", false},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "large", "rigid", "pass", "wfs", "p1l", false},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "small", "rigid", "pass", "wfs", "p1s", false},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "large", "rigid", "pass", "wfs", "p8l", false},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "small", "rigid", "pass", "wfs", "p8s", false},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "large", "rigid", "pass", "wfs", "p6l", false},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "small", "rigid", "pass", "wfs", "p6s", false},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "small", "rigid", "pass", "wfs", "wis", false},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "large", "rigid", "pass", "wfs", "wil", false},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "large", "rigid", "pass", "wfs", "wfl", false},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "small", "rigid", "pass", "wfs", "wfs", false},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "large", "rigid", "pass", "wfs", "wel", false},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "small", "rigid", "pass", "wfs", "wes", false},
//                {"Annual test", "Annual test", "1", "large", "rigid", "prs", "wfs", "aal", true},
//                {"Annual test", "Annual test", "1", "small", "rigid", "prs", "wfs", "aas", true},
//                {"Annual test", "Annual test", "1", "large", "articulated", "prs", "wfs", "adl", true},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "large", "rigid", "prs", "wfs", "wdl", true},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "small", "rigid", "prs", "wfs", "wds", true},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "large", "rigid", "prs", "wfs", "wbl", true},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "small", "rigid", "prs", "wfs", "wbs", true},
//                {"Any PSV", "Paid retest", "7", "large", "articulated", "prs", "wfs", "rhl", true},
//                {"Any PSV", "Paid retest", "7", "small", "rigid", "prs", "wfs", "rps", true},
//                {"Any PSV", "Paid retest", "7", "large", "rigid", "prs", "wfs", "rpl", true},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "large", "rigid", "prs", "wfs", "whl", true},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "small", "rigid", "prs", "wfs", "whs", true},
//                {"Any PSV", "Part-paid retest", "10", "large", "articulated", "prs", "wfs", "rgl", true},
//                {"Any PSV", "Part-paid retest", "10", "large", "rigid", "prs", "wfs", "rsl", true},
//                {"Any PSV", "Part-paid retest", "10", "small", "rigid", "prs", "wfs", "rss", true},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "large", "rigid", "prs", "wfs", "p1l", true},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "small", "rigid", "prs", "wfs", "p1s", true},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "large", "rigid", "prs", "wfs", "p8l", true},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "small", "rigid", "prs", "wfs", "p8s", true},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "large", "rigid", "prs", "wfs", "p6l", true},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "small", "rigid", "prs", "wfs", "p6s", true},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "small", "rigid", "prs", "wfs", "wis", true},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "large", "rigid", "prs", "wfs", "wil", true},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "large", "rigid", "prs", "wfs", "wfl", true},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "small", "rigid", "prs", "wfs", "wfs", true},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "large", "rigid", "prs", "wfs", "wel", true},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "small", "rigid", "prs", "wfs", "wes", true},
//                {"Annual test", "Annual test", "1", "large", "rigid", "prs", "wfs", "aal", false},
//                {"Annual test", "Annual test", "1", "small", "rigid", "prs", "wfs", "aas", false},
//                {"Annual test", "Annual test", "1", "large", "articulated", "prs", "wfs", "adl", false},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "large", "rigid", "prs", "wfs", "wdl", false},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "small", "rigid", "prs", "wfs", "wds", false},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "large", "rigid", "prs", "wfs", "wbl", false},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "small", "rigid", "prs", "wfs", "wbs", false},
//                {"Any PSV", "Paid retest", "7", "large", "articulated", "prs", "wfs", "rhl", false},
//                {"Any PSV", "Paid retest", "7", "small", "rigid", "prs", "wfs", "rps", false},
//                {"Any PSV", "Paid retest", "7", "large", "rigid", "prs", "wfs", "rpl", false},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "large", "rigid", "prs", "wfs", "whl", false},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "small", "rigid", "prs", "wfs", "whs", false},
//                {"Any PSV", "Part-paid retest", "10", "large", "articulated", "prs", "wfs", "rgl", false},
//                {"Any PSV", "Part-paid retest", "10", "large", "rigid", "prs", "wfs", "rsl", false},
//                {"Any PSV", "Part-paid retest", "10", "small", "rigid", "prs", "wfs", "rss", false},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "large", "rigid", "prs", "wfs", "p1l", false},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "small", "rigid", "prs", "wfs", "p1s", false},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "large", "rigid", "prs", "wfs", "p8l", false},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "small", "rigid", "prs", "wfs", "p8s", false},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "large", "rigid", "prs", "wfs", "p6l", false},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "small", "rigid", "prs", "wfs", "p6s", false},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "small", "rigid", "prs", "wfs", "wis", false},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "large", "rigid", "prs", "wfs", "wil", false},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "large", "rigid", "prs", "wfs", "wfl", false},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "small", "rigid", "prs", "wfs", "wfs", false},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "large", "rigid", "prs", "wfs", "wel", false},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "small", "rigid", "prs", "wfs", "wes", false},
//
//                {"Annual test", "Annual test", "1", "large", "rigid", "pass", "whl", "aal", true},
//                {"Annual test", "Annual test", "1", "small", "rigid", "pass", "whl", "aas", true},
//                {"Annual test", "Annual test", "1", "large", "articulated", "pass", "whl", "adl", true},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "large", "rigid", "pass", "whl", "wdl", true},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "small", "rigid", "pass", "whl", "wds", true},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "large", "rigid", "pass", "whl", "wbl", true},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "small", "rigid", "pass", "whl", "wbs", true},
//                {"Any PSV", "Paid retest", "7", "large", "articulated", "pass", "whl", "rhl", true},
//                {"Any PSV", "Paid retest", "7", "small", "rigid", "pass", "whl", "rps", true},
//                {"Any PSV", "Paid retest", "7", "large", "rigid", "pass", "whl", "rpl", true},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "large", "rigid", "pass", "whl", "whl", true},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "small", "rigid", "pass", "whl", "whs", true},
//                {"Any PSV", "Part-paid retest", "10", "large", "articulated", "pass", "whl", "rgl", true},
//                {"Any PSV", "Part-paid retest", "10", "large", "rigid", "pass", "whl", "rsl", true},
//                {"Any PSV", "Part-paid retest", "10", "small", "rigid", "pass", "whl", "rss", true},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "large", "rigid", "pass", "whl", "p1l", true},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "small", "rigid", "pass", "whl", "p1s", true},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "large", "rigid", "pass", "whl", "p8l", true},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "small", "rigid", "pass", "whl", "p8s", true},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "large", "rigid", "pass", "whl", "p6l", true},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "small", "rigid", "pass", "whl", "p6s", true},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "small", "rigid", "pass", "whl", "wis", true},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "large", "rigid", "pass", "whl", "wil", true},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "large", "rigid", "pass", "whl", "wfl", true},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "small", "rigid", "pass", "whl", "wfs", true},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "large", "rigid", "pass", "whl", "wel", true},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "small", "rigid", "pass", "whl", "wes", true},
//                {"Annual test", "Annual test", "1", "large", "rigid", "pass", "whl", "aal", false},
//                {"Annual test", "Annual test", "1", "small", "rigid", "pass", "whl", "aas", false},
//                {"Annual test", "Annual test", "1", "large", "articulated", "pass", "whl", "adl", false},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "large", "rigid", "pass", "whl", "wdl", false},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "small", "rigid", "pass", "whl", "wds", false},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "large", "rigid", "pass", "whl", "wbl", false},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "small", "rigid", "pass", "whl", "wbs", false},
//                {"Any PSV", "Paid retest", "7", "large", "articulated", "pass", "whl", "rhl", false},
//                {"Any PSV", "Paid retest", "7", "small", "rigid", "pass", "whl", "rps", false},
//                {"Any PSV", "Paid retest", "7", "large", "rigid", "pass", "whl", "rpl", false},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "large", "rigid", "pass", "whl", "whl", false},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "small", "rigid", "pass", "whl", "whs", false},
//                {"Any PSV", "Part-paid retest", "10", "large", "articulated", "pass", "whl", "rgl", false},
//                {"Any PSV", "Part-paid retest", "10", "large", "rigid", "pass", "whl", "rsl", false},
//                {"Any PSV", "Part-paid retest", "10", "small", "rigid", "pass", "whl", "rss", false},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "large", "rigid", "pass", "whl", "p1l", false},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "small", "rigid", "pass", "whl", "p1s", false},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "large", "rigid", "pass", "whl", "p8l", false},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "small", "rigid", "pass", "whl", "p8s", false},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "large", "rigid", "pass", "whl", "p6l", false},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "small", "rigid", "pass", "whl", "p6s", false},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "small", "rigid", "pass", "whl", "wis", false},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "large", "rigid", "pass", "whl", "wil", false},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "large", "rigid", "pass", "whl", "wfl", false},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "small", "rigid", "pass", "whl", "wfs", false},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "large", "rigid", "pass", "whl", "wel", false},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "small", "rigid", "pass", "whl", "wes", false},
//                {"Annual test", "Annual test", "1", "large", "rigid", "prs", "whl", "aal", true},
//                {"Annual test", "Annual test", "1", "small", "rigid", "prs", "whl", "aas", true},
//                {"Annual test", "Annual test", "1", "large", "articulated", "prs", "whl", "adl", true},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "large", "rigid", "prs", "whl", "wdl", true},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "small", "rigid", "prs", "whl", "wds", true},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "large", "rigid", "prs", "whl", "wbl", true},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "small", "rigid", "prs", "whl", "wbs", true},
//                {"Any PSV", "Paid retest", "7", "large", "articulated", "prs", "whl", "rhl", true},
//                {"Any PSV", "Paid retest", "7", "small", "rigid", "prs", "whl", "rps", true},
//                {"Any PSV", "Paid retest", "7", "large", "rigid", "prs", "whl", "rpl", true},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "large", "rigid", "prs", "whl", "whl", true},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "small", "rigid", "prs", "whl", "whs", true},
//                {"Any PSV", "Part-paid retest", "10", "large", "articulated", "prs", "whl", "rgl", true},
//                {"Any PSV", "Part-paid retest", "10", "large", "rigid", "prs", "whl", "rsl", true},
//                {"Any PSV", "Part-paid retest", "10", "small", "rigid", "prs", "whl", "rss", true},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "large", "rigid", "prs", "whl", "p1l", true},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "small", "rigid", "prs", "whl", "p1s", true},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "large", "rigid", "prs", "whl", "p8l", true},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "small", "rigid", "prs", "whl", "p8s", true},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "large", "rigid", "prs", "whl", "p6l", true},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "small", "rigid", "prs", "whl", "p6s", true},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "small", "rigid", "prs", "whl", "wis", true},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "large", "rigid", "prs", "whl", "wil", true},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "large", "rigid", "prs", "whl", "wfl", true},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "small", "rigid", "prs", "whl", "wfs", true},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "large", "rigid", "prs", "whl", "wel", true},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "small", "rigid", "prs", "whl", "wes", true},
//                {"Annual test", "Annual test", "1", "large", "rigid", "prs", "whl", "aal", false},
//                {"Annual test", "Annual test", "1", "small", "rigid", "prs", "whl", "aas", false},
//                {"Annual test", "Annual test", "1", "large", "articulated", "prs", "whl", "adl", false},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "large", "rigid", "prs", "whl", "wdl", false},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "small", "rigid", "prs", "whl", "wds", false},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "large", "rigid", "prs", "whl", "wbl", false},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "small", "rigid", "prs", "whl", "wbs", false},
//                {"Any PSV", "Paid retest", "7", "large", "articulated", "prs", "whl", "rhl", false},
//                {"Any PSV", "Paid retest", "7", "small", "rigid", "prs", "whl", "rps", false},
//                {"Any PSV", "Paid retest", "7", "large", "rigid", "prs", "whl", "rpl", false},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "large", "rigid", "prs", "whl", "whl", false},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "small", "rigid", "prs", "whl", "whs", false},
//                {"Any PSV", "Part-paid retest", "10", "large", "articulated", "prs", "whl", "rgl", false},
//                {"Any PSV", "Part-paid retest", "10", "large", "rigid", "prs", "whl", "rsl", false},
//                {"Any PSV", "Part-paid retest", "10", "small", "rigid", "prs", "whl", "rss", false},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "large", "rigid", "prs", "whl", "p1l", false},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "small", "rigid", "prs", "whl", "p1s", false},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "large", "rigid", "prs", "whl", "p8l", false},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "small", "rigid", "prs", "whl", "p8s", false},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "large", "rigid", "prs", "whl", "p6l", false},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "small", "rigid", "prs", "whl", "p6s", false},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "small", "rigid", "prs", "whl", "wis", false},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "large", "rigid", "prs", "whl", "wil", false},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "large", "rigid", "prs", "whl", "wfl", false},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "small", "rigid", "prs", "whl", "wfs", false},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "large", "rigid", "prs", "whl", "wel", false},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "small", "rigid", "prs", "whl", "wes", false},
//
//                {"Annual test", "Annual test", "1", "large", "rigid", "pass", "whs", "aal", true},
//                {"Annual test", "Annual test", "1", "small", "rigid", "pass", "whs", "aas", true},
//                {"Annual test", "Annual test", "1", "large", "articulated", "pass", "whs", "adl", true},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "large", "rigid", "pass", "whs", "wdl", true},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "small", "rigid", "pass", "whs", "wds", true},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "large", "rigid", "pass", "whs", "wbl", true},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "small", "rigid", "pass", "whs", "wbs", true},
//                {"Any PSV", "Paid retest", "7", "large", "articulated", "pass", "whs", "rhl", true},
//                {"Any PSV", "Paid retest", "7", "small", "rigid", "pass", "whs", "rps", true},
//                {"Any PSV", "Paid retest", "7", "large", "rigid", "pass", "whs", "rpl", true},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "large", "rigid", "pass", "whs", "whl", true},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "small", "rigid", "pass", "whs", "whs", true},
//                {"Any PSV", "Part-paid retest", "10", "large", "articulated", "pass", "whs", "rgl", true},
//                {"Any PSV", "Part-paid retest", "10", "large", "rigid", "pass", "whs", "rsl", true},
//                {"Any PSV", "Part-paid retest", "10", "small", "rigid", "pass", "whs", "rss", true},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "large", "rigid", "pass", "whs", "p1l", true},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "small", "rigid", "pass", "whs", "p1s", true},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "large", "rigid", "pass", "whs", "p8l", true},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "small", "rigid", "pass", "whs", "p8s", true},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "large", "rigid", "pass", "whs", "p6l", true},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "small", "rigid", "pass", "whs", "p6s", true},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "small", "rigid", "pass", "whs", "wis", true},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "large", "rigid", "pass", "whs", "wil", true},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "large", "rigid", "pass", "whs", "wfl", true},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "small", "rigid", "pass", "whs", "wfs", true},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "large", "rigid", "pass", "whs", "wel", true},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "small", "rigid", "pass", "whs", "wes", true},
//                {"Annual test", "Annual test", "1", "large", "rigid", "pass", "whs", "aal", false},
//                {"Annual test", "Annual test", "1", "small", "rigid", "pass", "whs", "aas", false},
//                {"Annual test", "Annual test", "1", "large", "articulated", "pass", "whs", "adl", false},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "large", "rigid", "pass", "whs", "wdl", false},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "small", "rigid", "pass", "whs", "wds", false},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "large", "rigid", "pass", "whs", "wbl", false},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "small", "rigid", "pass", "whs", "wbs", false},
//                {"Any PSV", "Paid retest", "7", "large", "articulated", "pass", "whs", "rhl", false},
//                {"Any PSV", "Paid retest", "7", "small", "rigid", "pass", "whs", "rps", false},
//                {"Any PSV", "Paid retest", "7", "large", "rigid", "pass", "whs", "rpl", false},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "large", "rigid", "pass", "whs", "whl", false},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "small", "rigid", "pass", "whs", "whs", false},
//                {"Any PSV", "Part-paid retest", "10", "large", "articulated", "pass", "whs", "rgl", false},
//                {"Any PSV", "Part-paid retest", "10", "large", "rigid", "pass", "whs", "rsl", false},
//                {"Any PSV", "Part-paid retest", "10", "small", "rigid", "pass", "whs", "rss", false},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "large", "rigid", "pass", "whs", "p1l", false},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "small", "rigid", "pass", "whs", "p1s", false},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "large", "rigid", "pass", "whs", "p8l", false},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "small", "rigid", "pass", "whs", "p8s", false},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "large", "rigid", "pass", "whs", "p6l", false},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "small", "rigid", "pass", "whs", "p6s", false},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "small", "rigid", "pass", "whs", "wis", false},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "large", "rigid", "pass", "whs", "wil", false},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "large", "rigid", "pass", "whs", "wfl", false},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "small", "rigid", "pass", "whs", "wfs", false},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "large", "rigid", "pass", "whs", "wel", false},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "small", "rigid", "pass", "whs", "wes", false},
//                {"Annual test", "Annual test", "1", "large", "rigid", "prs", "whs", "aal", true},
//                {"Annual test", "Annual test", "1", "small", "rigid", "prs", "whs", "aas", true},
//                {"Annual test", "Annual test", "1", "large", "articulated", "prs", "whs", "adl", true},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "large", "rigid", "prs", "whs", "wdl", true},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "small", "rigid", "prs", "whs", "wds", true},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "large", "rigid", "prs", "whs", "wbl", true},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "small", "rigid", "prs", "whs", "wbs", true},
//                {"Any PSV", "Paid retest", "7", "large", "articulated", "prs", "whs", "rhl", true},
//                {"Any PSV", "Paid retest", "7", "small", "rigid", "prs", "whs", "rps", true},
//                {"Any PSV", "Paid retest", "7", "large", "rigid", "prs", "whs", "rpl", true},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "large", "rigid", "prs", "whs", "whl", true},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "small", "rigid", "prs", "whs", "whs", true},
//                {"Any PSV", "Part-paid retest", "10", "large", "articulated", "prs", "whs", "rgl", true},
//                {"Any PSV", "Part-paid retest", "10", "large", "rigid", "prs", "whs", "rsl", true},
//                {"Any PSV", "Part-paid retest", "10", "small", "rigid", "prs", "whs", "rss", true},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "large", "rigid", "prs", "whs", "p1l", true},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "small", "rigid", "prs", "whs", "p1s", true},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "large", "rigid", "prs", "whs", "p8l", true},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "small", "rigid", "prs", "whs", "p8s", true},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "large", "rigid", "prs", "whs", "p6l", true},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "small", "rigid", "prs", "whs", "p6s", true},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "small", "rigid", "prs", "whs", "wis", true},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "large", "rigid", "prs", "whs", "wil", true},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "large", "rigid", "prs", "whs", "wfl", true},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "small", "rigid", "prs", "whs", "wfs", true},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "large", "rigid", "prs", "whs", "wel", true},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "small", "rigid", "prs", "whs", "wes", true},
//                {"Annual test", "Annual test", "1", "large", "rigid", "prs", "whs", "aal", false},
//                {"Annual test", "Annual test", "1", "small", "rigid", "prs", "whs", "aas", false},
//                {"Annual test", "Annual test", "1", "large", "articulated", "prs", "whs", "adl", false},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "large", "rigid", "prs", "whs", "wdl", false},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "small", "rigid", "prs", "whs", "wds", false},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "large", "rigid", "prs", "whs", "wbl", false},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "small", "rigid", "prs", "whs", "wbs", false},
//                {"Any PSV", "Paid retest", "7", "large", "articulated", "prs", "whs", "rhl", false},
//                {"Any PSV", "Paid retest", "7", "small", "rigid", "prs", "whs", "rps", false},
//                {"Any PSV", "Paid retest", "7", "large", "rigid", "prs", "whs", "rpl", false},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "large", "rigid", "prs", "whs", "whl", false},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "small", "rigid", "prs", "whs", "whs", false},
//                {"Any PSV", "Part-paid retest", "10", "large", "articulated", "prs", "whs", "rgl", false},
//                {"Any PSV", "Part-paid retest", "10", "large", "rigid", "prs", "whs", "rsl", false},
//                {"Any PSV", "Part-paid retest", "10", "small", "rigid", "prs", "whs", "rss", false},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "large", "rigid", "prs", "whs", "p1l", false},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "small", "rigid", "prs", "whs", "p1s", false},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "large", "rigid", "prs", "whs", "p8l", false},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "small", "rigid", "prs", "whs", "p8s", false},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "large", "rigid", "prs", "whs", "p6l", false},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "small", "rigid", "prs", "whs", "p6s", false},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "small", "rigid", "prs", "whs", "wis", false},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "large", "rigid", "prs", "whs", "wil", false},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "large", "rigid", "prs", "whs", "wfl", false},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "small", "rigid", "prs", "whs", "wfs", false},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "large", "rigid", "prs", "whs", "wel", false},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "small", "rigid", "prs", "whs", "wes", false},
//
//                {"Annual test", "Annual test", "1", "large", "rigid", "pass", "wil", "aal", true},
//                {"Annual test", "Annual test", "1", "small", "rigid", "pass", "wil", "aas", true},
//                {"Annual test", "Annual test", "1", "large", "articulated", "pass", "wil", "adl", true},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "large", "rigid", "pass", "wil", "wdl", true},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "small", "rigid", "pass", "wil", "wds", true},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "large", "rigid", "pass", "wil", "wbl", true},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "small", "rigid", "pass", "wil", "wbs", true},
//                {"Any PSV", "Paid retest", "7", "large", "articulated", "pass", "wil", "rhl", true},
//                {"Any PSV", "Paid retest", "7", "small", "rigid", "pass", "wil", "rps", true},
//                {"Any PSV", "Paid retest", "7", "large", "rigid", "pass", "wil", "rpl", true},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "large", "rigid", "pass", "wil", "whl", true},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "small", "rigid", "pass", "wil", "whs", true},
//                {"Any PSV", "Part-paid retest", "10", "large", "articulated", "pass", "wil", "rgl", true},
//                {"Any PSV", "Part-paid retest", "10", "large", "rigid", "pass", "wil", "rsl", true},
//                {"Any PSV", "Part-paid retest", "10", "small", "rigid", "pass", "wil", "rss", true},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "large", "rigid", "pass", "wil", "p1l", true},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "small", "rigid", "pass", "wil", "p1s", true},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "large", "rigid", "pass", "wil", "p8l", true},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "small", "rigid", "pass", "wil", "p8s", true},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "large", "rigid", "pass", "wil", "p6l", true},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "small", "rigid", "pass", "wil", "p6s", true},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "small", "rigid", "pass", "wil", "wis", true},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "large", "rigid", "pass", "wil", "wil", true},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "large", "rigid", "pass", "wil", "wfl", true},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "small", "rigid", "pass", "wil", "wfs", true},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "large", "rigid", "pass", "wil", "wel", true},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "small", "rigid", "pass", "wil", "wes", true},
//                {"Annual test", "Annual test", "1", "large", "rigid", "pass", "wil", "aal", false},
//                {"Annual test", "Annual test", "1", "small", "rigid", "pass", "wil", "aas", false},
//                {"Annual test", "Annual test", "1", "large", "articulated", "pass", "wil", "adl", false},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "large", "rigid", "pass", "wil", "wdl", false},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "small", "rigid", "pass", "wil", "wds", false},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "large", "rigid", "pass", "wil", "wbl", false},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "small", "rigid", "pass", "wil", "wbs", false},
//                {"Any PSV", "Paid retest", "7", "large", "articulated", "pass", "wil", "rhl", false},
//                {"Any PSV", "Paid retest", "7", "small", "rigid", "pass", "wil", "rps", false},
//                {"Any PSV", "Paid retest", "7", "large", "rigid", "pass", "wil", "rpl", false},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "large", "rigid", "pass", "wil", "whl", false},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "small", "rigid", "pass", "wil", "whs", false},
//                {"Any PSV", "Part-paid retest", "10", "large", "articulated", "pass", "wil", "rgl", false},
//                {"Any PSV", "Part-paid retest", "10", "large", "rigid", "pass", "wil", "rsl", false},
//                {"Any PSV", "Part-paid retest", "10", "small", "rigid", "pass", "wil", "rss", false},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "large", "rigid", "pass", "wil", "p1l", false},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "small", "rigid", "pass", "wil", "p1s", false},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "large", "rigid", "pass", "wil", "p8l", false},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "small", "rigid", "pass", "wil", "p8s", false},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "large", "rigid", "pass", "wil", "p6l", false},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "small", "rigid", "pass", "wil", "p6s", false},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "small", "rigid", "pass", "wil", "wis", false},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "large", "rigid", "pass", "wil", "wil", false},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "large", "rigid", "pass", "wil", "wfl", false},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "small", "rigid", "pass", "wil", "wfs", false},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "large", "rigid", "pass", "wil", "wel", false},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "small", "rigid", "pass", "wil", "wes", false},
//                {"Annual test", "Annual test", "1", "large", "rigid", "prs", "wil", "aal", true},
//                {"Annual test", "Annual test", "1", "small", "rigid", "prs", "wil", "aas", true},
//                {"Annual test", "Annual test", "1", "large", "articulated", "prs", "wil", "adl", true},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "large", "rigid", "prs", "wil", "wdl", true},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "small", "rigid", "prs", "wil", "wds", true},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "large", "rigid", "prs", "wil", "wbl", true},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "small", "rigid", "prs", "wil", "wbs", true},
//                {"Any PSV", "Paid retest", "7", "large", "articulated", "prs", "wil", "rhl", true},
//                {"Any PSV", "Paid retest", "7", "small", "rigid", "prs", "wil", "rps", true},
//                {"Any PSV", "Paid retest", "7", "large", "rigid", "prs", "wil", "rpl", true},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "large", "rigid", "prs", "wil", "whl", true},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "small", "rigid", "prs", "wil", "whs", true},
//                {"Any PSV", "Part-paid retest", "10", "large", "articulated", "prs", "wil", "rgl", true},
//                {"Any PSV", "Part-paid retest", "10", "large", "rigid", "prs", "wil", "rsl", true},
//                {"Any PSV", "Part-paid retest", "10", "small", "rigid", "prs", "wil", "rss", true},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "large", "rigid", "prs", "wil", "p1l", true},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "small", "rigid", "prs", "wil", "p1s", true},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "large", "rigid", "prs", "wil", "p8l", true},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "small", "rigid", "prs", "wil", "p8s", true},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "large", "rigid", "prs", "wil", "p6l", true},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "small", "rigid", "prs", "wil", "p6s", true},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "small", "rigid", "prs", "wil", "wis", true},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "large", "rigid", "prs", "wil", "wil", true},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "large", "rigid", "prs", "wil", "wfl", true},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "small", "rigid", "prs", "wil", "wfs", true},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "large", "rigid", "prs", "wil", "wel", true},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "small", "rigid", "prs", "wil", "wes", true},
//                {"Annual test", "Annual test", "1", "large", "rigid", "prs", "wil", "aal", false},
//                {"Annual test", "Annual test", "1", "small", "rigid", "prs", "wil", "aas", false},
//                {"Annual test", "Annual test", "1", "large", "articulated", "prs", "wil", "adl", false},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "large", "rigid", "prs", "wil", "wdl", false},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "small", "rigid", "prs", "wil", "wds", false},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "large", "rigid", "prs", "wil", "wbl", false},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "small", "rigid", "prs", "wil", "wbs", false},
//                {"Any PSV", "Paid retest", "7", "large", "articulated", "prs", "wil", "rhl", false},
//                {"Any PSV", "Paid retest", "7", "small", "rigid", "prs", "wil", "rps", false},
//                {"Any PSV", "Paid retest", "7", "large", "rigid", "prs", "wil", "rpl", false},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "large", "rigid", "prs", "wil", "whl", false},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "small", "rigid", "prs", "wil", "whs", false},
//                {"Any PSV", "Part-paid retest", "10", "large", "articulated", "prs", "wil", "rgl", false},
//                {"Any PSV", "Part-paid retest", "10", "large", "rigid", "prs", "wil", "rsl", false},
//                {"Any PSV", "Part-paid retest", "10", "small", "rigid", "prs", "wil", "rss", false},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "large", "rigid", "prs", "wil", "p1l", false},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "small", "rigid", "prs", "wil", "p1s", false},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "large", "rigid", "prs", "wil", "p8l", false},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "small", "rigid", "prs", "wil", "p8s", false},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "large", "rigid", "prs", "wil", "p6l", false},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "small", "rigid", "prs", "wil", "p6s", false},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "small", "rigid", "prs", "wil", "wis", false},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "large", "rigid", "prs", "wil", "wil", false},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "large", "rigid", "prs", "wil", "wfl", false},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "small", "rigid", "prs", "wil", "wfs", false},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "large", "rigid", "prs", "wil", "wel", false},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "small", "rigid", "prs", "wil", "wes", false},
//
//                {"Annual test", "Annual test", "1", "large", "rigid", "pass", "wis", "aal", true},
//                {"Annual test", "Annual test", "1", "small", "rigid", "pass", "wis", "aas", true},
//                {"Annual test", "Annual test", "1", "large", "articulated", "pass", "wis", "adl", true},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "large", "rigid", "pass", "wis", "wdl", true},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "small", "rigid", "pass", "wis", "wds", true},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "large", "rigid", "pass", "wis", "wbl", true},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "small", "rigid", "pass", "wis", "wbs", true},
//                {"Any PSV", "Paid retest", "7", "large", "articulated", "pass", "wis", "rhl", true},
//                {"Any PSV", "Paid retest", "7", "small", "rigid", "pass", "wis", "rps", true},
//                {"Any PSV", "Paid retest", "7", "large", "rigid", "pass", "wis", "rpl", true},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "large", "rigid", "pass", "wis", "whl", true},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "small", "rigid", "pass", "wis", "whs", true},
//                {"Any PSV", "Part-paid retest", "10", "large", "articulated", "pass", "wis", "rgl", true},
//                {"Any PSV", "Part-paid retest", "10", "large", "rigid", "pass", "wis", "rsl", true},
//                {"Any PSV", "Part-paid retest", "10", "small", "rigid", "pass", "wis", "rss", true},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "large", "rigid", "pass", "wis", "p1l", true},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "small", "rigid", "pass", "wis", "p1s", true},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "large", "rigid", "pass", "wis", "p8l", true},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "small", "rigid", "pass", "wis", "p8s", true},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "large", "rigid", "pass", "wis", "p6l", true},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "small", "rigid", "pass", "wis", "p6s", true},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "small", "rigid", "pass", "wis", "wis", true},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "large", "rigid", "pass", "wis", "wil", true},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "large", "rigid", "pass", "wis", "wfl", true},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "small", "rigid", "pass", "wis", "wfs", true},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "large", "rigid", "pass", "wis", "wel", true},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "small", "rigid", "pass", "wis", "wes", true},
//                {"Annual test", "Annual test", "1", "large", "rigid", "pass", "wis", "aal", false},
//                {"Annual test", "Annual test", "1", "small", "rigid", "pass", "wis", "aas", false},
//                {"Annual test", "Annual test", "1", "large", "articulated", "pass", "wis", "adl", false},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "large", "rigid", "pass", "wis", "wdl", false},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "small", "rigid", "pass", "wis", "wds", false},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "large", "rigid", "pass", "wis", "wbl", false},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "small", "rigid", "pass", "wis", "wbs", false},
//                {"Any PSV", "Paid retest", "7", "large", "articulated", "pass", "wis", "rhl", false},
//                {"Any PSV", "Paid retest", "7", "small", "rigid", "pass", "wis", "rps", false},
//                {"Any PSV", "Paid retest", "7", "large", "rigid", "pass", "wis", "rpl", false},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "large", "rigid", "pass", "wis", "whl", false},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "small", "rigid", "pass", "wis", "whs", false},
//                {"Any PSV", "Part-paid retest", "10", "large", "articulated", "pass", "wis", "rgl", false},
//                {"Any PSV", "Part-paid retest", "10", "large", "rigid", "pass", "wis", "rsl", false},
//                {"Any PSV", "Part-paid retest", "10", "small", "rigid", "pass", "wis", "rss", false},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "large", "rigid", "pass", "wis", "p1l", false},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "small", "rigid", "pass", "wis", "p1s", false},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "large", "rigid", "pass", "wis", "p8l", false},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "small", "rigid", "pass", "wis", "p8s", false},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "large", "rigid", "pass", "wis", "p6l", false},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "small", "rigid", "pass", "wis", "p6s", false},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "small", "rigid", "pass", "wis", "wis", false},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "large", "rigid", "pass", "wis", "wil", false},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "large", "rigid", "pass", "wis", "wfl", false},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "small", "rigid", "pass", "wis", "wfs", false},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "large", "rigid", "pass", "wis", "wel", false},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "small", "rigid", "pass", "wis", "wes", false},
//                {"Annual test", "Annual test", "1", "large", "rigid", "prs", "wis", "aal", true},
//                {"Annual test", "Annual test", "1", "small", "rigid", "prs", "wis", "aas", true},
//                {"Annual test", "Annual test", "1", "large", "articulated", "prs", "wis", "adl", true},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "large", "rigid", "prs", "wis", "wdl", true},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "small", "rigid", "prs", "wis", "wds", true},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "large", "rigid", "prs", "wis", "wbl", true},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "small", "rigid", "prs", "wis", "wbs", true},
//                {"Any PSV", "Paid retest", "7", "large", "articulated", "prs", "wis", "rhl", true},
//                {"Any PSV", "Paid retest", "7", "small", "rigid", "prs", "wis", "rps", true},
//                {"Any PSV", "Paid retest", "7", "large", "rigid", "prs", "wis", "rpl", true},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "large", "rigid", "prs", "wis", "whl", true},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "small", "rigid", "prs", "wis", "whs", true},
//                {"Any PSV", "Part-paid retest", "10", "large", "articulated", "prs", "wis", "rgl", true},
//                {"Any PSV", "Part-paid retest", "10", "large", "rigid", "prs", "wis", "rsl", true},
//                {"Any PSV", "Part-paid retest", "10", "small", "rigid", "prs", "wis", "rss", true},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "large", "rigid", "prs", "wis", "p1l", true},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "small", "rigid", "prs", "wis", "p1s", true},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "large", "rigid", "prs", "wis", "p8l", true},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "small", "rigid", "prs", "wis", "p8s", true},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "large", "rigid", "prs", "wis", "p6l", true},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "small", "rigid", "prs", "wis", "p6s", true},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "small", "rigid", "prs", "wis", "wis", true},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "large", "rigid", "prs", "wis", "wil", true},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "large", "rigid", "prs", "wis", "wfl", true},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "small", "rigid", "prs", "wis", "wfs", true},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "large", "rigid", "prs", "wis", "wel", true},
//                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "small", "rigid", "prs", "wis", "wes", true},
//                {"Annual test", "Annual test", "1", "large", "rigid", "prs", "wis", "aal", false},
//                {"Annual test", "Annual test", "1", "small", "rigid", "prs", "wis", "aas", false},
//                {"Annual test", "Annual test", "1", "large", "articulated", "prs", "wis", "adl", false},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "large", "rigid", "prs", "wis", "wdl", false},
//                {"Annual test", "Class 6A seatbelt installation check (annual test)", "3", "small", "rigid", "prs", "wis", "wds", false},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "large", "rigid", "prs", "wis", "wbl", false},
//                {"First test", "Class 6A seatbelt installation check (first test)", "4", "small", "rigid", "prs", "wis", "wbs", false},
//                {"Any PSV", "Paid retest", "7", "large", "articulated", "prs", "wis", "rhl", false},
//                {"Any PSV", "Paid retest", "7", "small", "rigid", "prs", "wis", "rps", false},
//                {"Any PSV", "Paid retest", "7", "large", "rigid", "prs", "wis", "rpl", false},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "large", "rigid", "prs", "wis", "whl", false},
//                {"Class 6A (seatbelt installation check)", "Paid retest with Class 6A seatbelt installation check", "8", "small", "rigid", "prs", "wis", "whs", false},
//                {"Any PSV", "Part-paid retest", "10", "large", "articulated", "prs", "wis", "rgl", false},
//                {"Any PSV", "Part-paid retest", "10", "large", "rigid", "prs", "wis", "rsl", false},
//                {"Any PSV", "Part-paid retest", "10", "small", "rigid", "prs", "wis", "rss", false},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "large", "rigid", "prs", "wis", "p1l", false},
//                {"With certification", "Paid prohibition clearance (full inspection with certificate)", "14", "small", "rigid", "prs", "wis", "p1s", false},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "large", "rigid", "prs", "wis", "p8l", false},
//                {"With certification", "Paid prohibition clearance (retest with certificate)", "18", "small", "rigid", "prs", "wis", "p8s", false},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "large", "rigid", "prs", "wis", "p6l", false},
//                {"With certification", "Part-paid prohibition clearance (retest with certificate)", "21", "small", "rigid", "prs", "wis", "p6s", false},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "small", "rigid", "prs", "wis", "wis", false},
//                {"PG9 retest", "Prohibition clearance (retest with Class 6A seatbelt installation check)", "28", "large", "rigid", "prs", "wis", "wil", false},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "large", "rigid", "prs", "wis", "wfl", false},
//                {"Full inspection / full fee", "Paid prohibition clearance with Class 6A seatbelt installation check (full inspection)", "27", "small", "rigid", "prs", "wis", "wfs", false},
                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "large", "rigid", "prs", "wis", "wel", false},
                {"PG9 retest", "Prohibition clearance (retest without Class 6A seatbelt installation check)", "93", "small", "rigid", "prs", "wis", "wes", false},
        });
    }

    private String name;
    private String testTypeName;
    private String testTypeId;
    private String vehicleSize;
    private String vehicleConfiguration;
    private String testResult;
    private String insertedTestCode;
    private String testCode;
    private boolean isAnnualWithCertificate;

    //sets the firstUseDate / regnDate one year before the current date, plus/minus one day
    private int dayOffset;

    public PostTestResultsExpiryDateLogicPsv(String name, String testTypeName, String testTypeId, String vehicleSize, String vehicleConfiguration, String testResult, String insertedTestCode, String testCode, boolean isAnnualWithCertificate) {
        this.name = name;
        this.testTypeName = testTypeName;
        this.testTypeId = testTypeId;
        this.vehicleSize = vehicleSize;
        this.vehicleConfiguration = vehicleConfiguration;
        this.testResult = testResult;
        this.insertedTestCode = insertedTestCode;
        this.testCode = testCode;
        this.isAnnualWithCertificate = isAnnualWithCertificate;
    }



    @WithTag("expiry_dates")
    @Title("CVSB-8684 - TC1 - AC1 - PSV Annual test NO PREVIOUS Expiry Date")
    @Test
    public void testResultsFirstTestExpiryPsvNoPreviousExpiry() {

        // Read the base test result JSON.
        String testResultRecord = GenericData.readJsonValueFromFile("test-results_post_expiry_date_psv_8684.json", "$");

        DateTime submittedTestStartTimestamp = DateTime.now().withZone(DateTimeZone.UTC);
        DateTime submittedTestTypeStartTimestamp = submittedTestStartTimestamp.plusMinutes(5);
        DateTime submittedTypeEndTimestamp = submittedTestTypeStartTimestamp.plusMinutes(10);
        DateTime submittedEndTimestamp = submittedTypeEndTimestamp.plusMinutes(5);

        // Create alteration to add one more tech record to in the request body
        String firstUseDate = submittedEndTimestamp.toDateTime().minusYears(1).plusDays(dayOffset).toString("yyyy-MM-dd");
        String testStartTimestamp = submittedTestStartTimestamp.toInstant().toString();
        String testTypeStartTimestamp = submittedTestTypeStartTimestamp.toInstant().toString();

        String testTypeEndTimestamp = submittedTypeEndTimestamp.toInstant().toString();
        String testEndTimestamp = submittedTypeEndTimestamp.toInstant().toString();
        String testExpectedDate = submittedEndTimestamp.plusYears(1).minusDays(1).toInstant().toString();
        String randomVin = GenericData.generateRandomVin();
        String randomTestResultId = RandomStringUtils.randomNumeric(5);


//        String returned_testTypeEndTimestamp = "";
//        String returned_testExpiryDate = "";
//        String returned_createdAt = "";
//        String returned_testTypeStartTimestamp = "";

//        System.out.println("submittedFirstUseDate: "+ firstUseDate);
//        System.out.println("submittedTestStartTimestamp: "+ testStartTimestamp);
//        System.out.println("submittedTestTypeStartTimestamp: "+ testTypeStartTimestamp);
//        System.out.println("submittedTestTypeEndTimestamp: "+ testTypeEndTimestamp);
//        System.out.println("submittedTestEndTimestamp: "+ testEndTimestamp);
//        System.out.println("returned_testAnniversaryDate: "+ testExpectedDate);

        JsonPathAlteration alterationFirstUseDate = new JsonPathAlteration("$.firstUseDate", firstUseDate, "", "REPLACE");
        JsonPathAlteration alterationTestStartTimestamp = new JsonPathAlteration("$.testStartTimestamp", testStartTimestamp, "", "REPLACE");
        JsonPathAlteration alterationTestEndTimestamp = new JsonPathAlteration("$.testEndTimestamp", testEndTimestamp, "", "REPLACE");
        JsonPathAlteration alterationTestTypeStartTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeStartTimestamp", testTypeStartTimestamp, "", "REPLACE");
        JsonPathAlteration alterationTestTypeEndTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeEndTimestamp", testTypeEndTimestamp, "", "REPLACE");
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationTestResultId = new JsonPathAlteration("$.testResultId", randomTestResultId, "", "REPLACE");
        JsonPathAlteration alterationTestExpiryDate = new JsonPathAlteration("$.testTypes[0].testExpiryDate", "", "", "DELETE");
        JsonPathAlteration alterationTestName = new JsonPathAlteration("$.testTypes[0].name", name, "", "REPLACE");
        JsonPathAlteration alterationTestTypeId = new JsonPathAlteration("$.testTypes[0].testTypeId", testTypeId, "", "REPLACE");
        JsonPathAlteration alterationTestTypeName = new JsonPathAlteration("$.testTypes[0].testTypeName", testTypeName, "", "REPLACE");
        JsonPathAlteration alterationTestResult = new JsonPathAlteration("$.testTypes[0].testResult", testResult, "", "REPLACE");
        JsonPathAlteration alterationVehicleSize = new JsonPathAlteration("$.vehicleSize", vehicleSize, "", "REPLACE");
        JsonPathAlteration alterationVehicleConfiguration = new JsonPathAlteration("$.vehicleConfiguration", vehicleConfiguration, "", "REPLACE");

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
                alterationVehicleSize,
                alterationVehicleConfiguration,
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

        // Verify testAnniversaryDate field has the expected value
//        testResultsSteps.valueForFieldInPathShouldBe("[0].testTypes[0].testTypeStartTimestamp", expectedTestTypeStartTimestamp);

        // Verify testExpiryDate field has the expected value
//        testResultsSteps.valueForFieldInPathShouldBe("[0].testTypes[0].testExpiryDate", testExpectedDate);
        testResultsSteps.valueForFieldInPathShouldStartWith("[0].testTypes[0].testExpiryDate", testExpectedDate.substring(0, 10));

    }

    @WithTag("expiry_dates")
    @Title("CVSB-8684 - TC1 - AC1 - PSV Annual test WITH PREVIOUS Expiry Date - Previous testExpiryDate = Today - 1 day")
    @Test
    public void testResultsFirstTestExpiryPsvTodayMinusOneDay() {

        // Read the base INSERT test result JSON.
        String insertedTestResultRecord = GenericData.readJsonValueFromFile("test-results_insert_expiry_date_psv_8684.json", "$");
        // Read the base POST test result JSON.
        String postTestResultRecord = GenericData.readJsonValueFromFile("test-results_post_expiry_date_psv_8684.json", "$");

        DateTime currentTimestamp = DateTime.now().withZone(DateTimeZone.UTC);

        //
        String randomVin = GenericData.generateRandomVin();
        String randomTestResultId = RandomStringUtils.randomNumeric(5);

        // Create inserted record.
        DateTime insertedTestStartTimestamp = currentTimestamp.minusYears(1).minusMinutes(15);
        DateTime insertedTestTypeStartTimestamp = currentTimestamp.minusYears(1).minusMinutes(10);
        DateTime insertedLastUpdatedAt = currentTimestamp.minusYears(1).minusMinutes(5);

        DateTime insertedTestAnniversaryDate = currentTimestamp.minusMonths(2).minusDays(1);
        DateTime insertedTestExpiryDate = currentTimestamp.minusDays(1);

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
        JsonPathAlteration alterationInsertTestResultId = new JsonPathAlteration("$.testResultId", RandomStringUtils.randomNumeric(5), "", "REPLACE");
        JsonPathAlteration alterationInsertTestStartTimestamp = new JsonPathAlteration("$.testStartTimestamp", insertableTestStartTimestamp, "", "REPLACE");
        JsonPathAlteration alterationInsertTestTypeStartTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeStartTimestamp", insertableTestTypeStartTimestamp, "", "REPLACE");
        JsonPathAlteration alterationInsertLastUpdatedAt = new JsonPathAlteration("$.testTypes[0].lastUpdatedAt", insertableLastUpdatedAt, "", "REPLACE");
        JsonPathAlteration alterationInsertTestAnniversaryDate = new JsonPathAlteration("$.testTypes[0].testAnniversaryDate", insertableTestAnniversaryDate, "", "REPLACE");
        JsonPathAlteration alterationInsertTestExpiryDate = new JsonPathAlteration("$.testTypes[0].testExpiryDate", insertableTestExpiryDate, "", "REPLACE");
        JsonPathAlteration alterationInsertCreatedAt = new JsonPathAlteration("$.testTypes[0].createdAt", insertableCreatedAt, "", "REPLACE");
        JsonPathAlteration alterationInsertTestTypeEndTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeEndTimestamp", insertableTestTypeEndTimestamp, "", "REPLACE");
        JsonPathAlteration alterationInsertTestEndTimestamp = new JsonPathAlteration("$.testEndTimestamp", insertableTestEndTimestamp, "", "REPLACE");
//        System.out.println("######################### INSERTED TEST CODE: " + insertedTestCode + "  #########################");
        JsonPathAlteration alterationInsertTestCode = new JsonPathAlteration("$.testTypes[0].testCode", insertedTestCode, "", "REPLACE");

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
                alterationInsertTestCode,
                alterationInsertTestEndTimestamp
        ));

        if (isAnnualWithCertificate) {
            insertAlterations.add(new JsonPathAlteration("$.testTypes[0]", "Annual With Certificate", "testTypeClassification", "ADD_FIELD"));
        }

        // Insert the altered record
        String alteredJson = GenericData.applyJsonAlterations(insertedTestResultRecord, insertAlterations);
        testResultsSteps.insertRecordInDynamo(alteredJson, "test-results");

//        System.out.println("\n######################## INSERTED ########################\n\n");
//        System.out.println(new GsonBuilder().setPrettyPrinting().create().toJson(new JsonParser().parse(alteredJson).getAsJsonObject()));
//        System.out.println("\n########################    END   ########################\n\n");


        // Create submitted
        DateTime submittedTestStartTimestamp = currentTimestamp.minusMinutes(15);
        DateTime submittedTestTypeStartTimestamp = currentTimestamp.plusMinutes(10);
        DateTime submittedTypeEndTimestamp = currentTimestamp.plusMinutes(5);
        DateTime submittedEndTimestamp = currentTimestamp;

        String testStartTimestamp = submittedTestStartTimestamp.toInstant().toString();
        String testTypeStartTimestamp = submittedTestTypeStartTimestamp.toInstant().toString();
        String testTypeEndTimestamp = submittedTypeEndTimestamp.toInstant().toString();
        String testEndTimestamp = submittedEndTimestamp.toInstant().toString();

        String testExpectedDate = submittedTypeEndTimestamp.plusYears(1).minusDays(1).toInstant().toString();


        // Create alteration to add one more tech record to in the request body
        JsonPathAlteration alterationTestStartTimestamp = new JsonPathAlteration("$.testStartTimestamp", testStartTimestamp, "", "REPLACE");
        JsonPathAlteration alterationTestEndTimestamp = new JsonPathAlteration("$.testEndTimestamp", testEndTimestamp, "", "REPLACE");
        JsonPathAlteration alterationTestTypeStartTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeStartTimestamp", testTypeStartTimestamp, "", "REPLACE");
        JsonPathAlteration alterationTestTypeEndTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeEndTimestamp", testTypeEndTimestamp, "", "REPLACE");
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationTestResultId = new JsonPathAlteration("$.testResultId", randomTestResultId, "", "REPLACE");

        JsonPathAlteration alterationTestName = new JsonPathAlteration("$.testTypes[0].name", name, "", "REPLACE");
        JsonPathAlteration alterationTestTypeId = new JsonPathAlteration("$.testTypes[0].testTypeId", testTypeId, "", "REPLACE");
        JsonPathAlteration alterationTestTypeName = new JsonPathAlteration("$.testTypes[0].testTypeName", testTypeName, "", "REPLACE");
        JsonPathAlteration alterationTestResult = new JsonPathAlteration("$.testTypes[0].testResult", testResult, "", "REPLACE");
        JsonPathAlteration alterationVehicleSize = new JsonPathAlteration("$.vehicleSize", vehicleSize, "", "REPLACE");
        JsonPathAlteration alterationVehicleConfiguration = new JsonPathAlteration("$.vehicleConfiguration", vehicleConfiguration, "", "REPLACE");

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
                alterationVehicleSize,
                alterationVehicleConfiguration,
                alterationTestResult
        ));

        // Post the results, together with any alterations, and verify that they are accepted.
        testResultsSteps.postVehicleTestResultsWithAlterations(postTestResultRecord, alterations);

//        System.out.println("\n######################## POSTED ########################\n\n");
//        System.out.println(new GsonBuilder().setPrettyPrinting().create().toJson(new JsonParser().parse(GenericData.applyJsonAlterations(postTestResultRecord, alterations)).getAsJsonObject()));
//        System.out.println("\n########################   END  ########################\n\n");

        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");

        // Retrieve the created record, and verify that the fields are present.
        testResultsSteps.getTestResultsBetweenDate(randomVin, submittedTestStartTimestamp.minusMinutes(10).toInstant().toString(), submittedEndTimestamp.plusMinutes(10).toInstant().toString());
        testResultsSteps.statusCodeShouldBe(200);

        // Verify testCode field has the expected value
        testResultsSteps.valueForFieldInPathShouldBe("[0].testTypes[0].testCode", testCode);

        // Verify testExpiryDate field has the expected value
        testResultsSteps.valueForFieldInPathShouldStartWith("[0].testTypes[0].testExpiryDate", testExpectedDate.substring(0, 10));

//        // Verify testAnniversaryDate field has the expected value
//        testResultsSteps.valueForFieldInPathShouldStartWith("[0].testTypes[0].testAnniversaryDate", testExpectedDate.substring(0,10));

    }


    @WithTag("expiry_dates")
    @Title("CVSB-8684 - TC1 - AC1 - PSV Annual test WITH PREVIOUS Expiry Date - Previous testExpiryDate = Today")
    @Test
    public void testResultsFirstTestExpiryPsvToday() {

        // Read the base INSERT test result JSON.
        String insertedTestResultRecord = GenericData.readJsonValueFromFile("test-results_insert_expiry_date_psv_8684.json", "$");
        // Read the base POST test result JSON.
        String postTestResultRecord = GenericData.readJsonValueFromFile("test-results_post_expiry_date_psv_8684.json", "$");

        DateTime currentTimestamp = DateTime.now().withZone(DateTimeZone.UTC);

        //
        String randomVin = GenericData.generateRandomVin();
        String randomTestResultId = RandomStringUtils.randomNumeric(5);

        // Create inserted record.
        DateTime insertedTestStartTimestamp = currentTimestamp.minusYears(1).plusDays(1).minusMinutes(15);
        DateTime insertedTestTypeStartTimestamp = currentTimestamp.minusYears(1).plusDays(1).minusMinutes(10);
        DateTime insertedLastUpdatedAt = currentTimestamp.minusYears(1).plusDays(1).minusMinutes(5);

        DateTime insertedTestAnniversaryDate = currentTimestamp.minusMonths(2);
        DateTime insertedTestExpiryDate = currentTimestamp;

        DateTime insertedCreatedAt = currentTimestamp.minusYears(1).plusDays(1).minusMinutes(5);
        DateTime insertedTestTypeEndTimestamp = currentTimestamp.minusYears(1).plusDays(1).minusMinutes(5);
        DateTime insertedTestEndTimestamp = currentTimestamp.minusYears(1).plusDays(1);

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
        JsonPathAlteration alterationInsertTestResultId = new JsonPathAlteration("$.testResultId", RandomStringUtils.randomNumeric(5), "", "REPLACE");
        JsonPathAlteration alterationInsertTestStartTimestamp = new JsonPathAlteration("$.testStartTimestamp", insertableTestStartTimestamp, "", "REPLACE");
        JsonPathAlteration alterationInsertTestTypeStartTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeStartTimestamp", insertableTestTypeStartTimestamp, "", "REPLACE");
        JsonPathAlteration alterationInsertLastUpdatedAt = new JsonPathAlteration("$.testTypes[0].lastUpdatedAt", insertableLastUpdatedAt, "", "REPLACE");
        JsonPathAlteration alterationInsertTestAnniversaryDate = new JsonPathAlteration("$.testTypes[0].testAnniversaryDate", insertableTestAnniversaryDate, "", "REPLACE");
        JsonPathAlteration alterationInsertTestExpiryDate = new JsonPathAlteration("$.testTypes[0].testExpiryDate", insertableTestExpiryDate, "", "REPLACE");
        JsonPathAlteration alterationInsertCreatedAt = new JsonPathAlteration("$.testTypes[0].createdAt", insertableCreatedAt, "", "REPLACE");
        JsonPathAlteration alterationInsertTestTypeEndTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeEndTimestamp", insertableTestTypeEndTimestamp, "", "REPLACE");
        JsonPathAlteration alterationInsertTestEndTimestamp = new JsonPathAlteration("$.testEndTimestamp", insertableTestEndTimestamp, "", "REPLACE");
        JsonPathAlteration alterationInsertTestCode = new JsonPathAlteration("$.testTypes[0].testCode", insertedTestCode, "", "REPLACE");

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
                alterationInsertTestEndTimestamp,
                alterationInsertTestCode
        ));

        if (isAnnualWithCertificate) {
            insertAlterations.add(new JsonPathAlteration("$.testTypes[0]", "Annual With Certificate", "testTypeClassification", "ADD_FIELD"));
        }

        // Insert the altered record
        String alteredJson = GenericData.applyJsonAlterations(insertedTestResultRecord, insertAlterations);
        testResultsSteps.insertRecordInDynamo(alteredJson, "test-results");

//        System.out.println("\n######################## INSERTED ########################\n\n");
//        System.out.println(new GsonBuilder().setPrettyPrinting().create().toJson(new JsonParser().parse(alteredJson).getAsJsonObject()));
//        System.out.println("\n########################    END   ########################\n\n");


        // Create submitted
        DateTime submittedTestStartTimestamp = currentTimestamp.minusMinutes(15);
        DateTime submittedTestTypeStartTimestamp = currentTimestamp.plusMinutes(10);
        DateTime submittedTypeEndTimestamp = currentTimestamp.plusMinutes(5);
        DateTime submittedEndTimestamp = currentTimestamp;

        String testStartTimestamp = submittedTestStartTimestamp.toInstant().toString();
        String testTypeStartTimestamp = submittedTestTypeStartTimestamp.toInstant().toString();
        String testTypeEndTimestamp = submittedTypeEndTimestamp.toInstant().toString();
        String testEndTimestamp = submittedEndTimestamp.toInstant().toString();

        String testExpectedDate = submittedTypeEndTimestamp.plusYears(1).toInstant().toString();


        // Create alteration to add one more tech record to in the request body
        JsonPathAlteration alterationTestStartTimestamp = new JsonPathAlteration("$.testStartTimestamp", testStartTimestamp, "", "REPLACE");
        JsonPathAlteration alterationTestEndTimestamp = new JsonPathAlteration("$.testEndTimestamp", testEndTimestamp, "", "REPLACE");
        JsonPathAlteration alterationTestTypeStartTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeStartTimestamp", testTypeStartTimestamp, "", "REPLACE");
        JsonPathAlteration alterationTestTypeEndTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeEndTimestamp", testTypeEndTimestamp, "", "REPLACE");
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationTestResultId = new JsonPathAlteration("$.testResultId", randomTestResultId, "", "REPLACE");

        JsonPathAlteration alterationTestName = new JsonPathAlteration("$.testTypes[0].name", name, "", "REPLACE");
        JsonPathAlteration alterationTestTypeId = new JsonPathAlteration("$.testTypes[0].testTypeId", testTypeId, "", "REPLACE");
        JsonPathAlteration alterationTestTypeName = new JsonPathAlteration("$.testTypes[0].testTypeName", testTypeName, "", "REPLACE");
        JsonPathAlteration alterationTestResult = new JsonPathAlteration("$.testTypes[0].testResult", testResult, "", "REPLACE");
        JsonPathAlteration alterationVehicleSize = new JsonPathAlteration("$.vehicleSize", vehicleSize, "", "REPLACE");
        JsonPathAlteration alterationVehicleConfiguration = new JsonPathAlteration("$.vehicleConfiguration", vehicleConfiguration, "", "REPLACE");

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
                alterationVehicleSize,
                alterationVehicleConfiguration,
                alterationTestResult
        ));

        // Post the results, together with any alterations, and verify that they are accepted.
        testResultsSteps.postVehicleTestResultsWithAlterations(postTestResultRecord, alterations);

//        System.out.println("\n######################## POSTED ########################\n\n");
//        System.out.println(new GsonBuilder().setPrettyPrinting().create().toJson(new JsonParser().parse(GenericData.applyJsonAlterations(postTestResultRecord, alterations)).getAsJsonObject()));
//        System.out.println("\n########################   END  ########################\n\n");

        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");

        // Retrieve the created record, and verify that the fields are present.
        testResultsSteps.getTestResultsBetweenDate(randomVin, submittedTestStartTimestamp.minusMinutes(10).toInstant().toString(), submittedEndTimestamp.plusMinutes(10).toInstant().toString());
        testResultsSteps.statusCodeShouldBe(200);

        // Verify testCode field has the expected value
        testResultsSteps.valueForFieldInPathShouldBe("[0].testTypes[0].testCode", testCode);

        // Verify testExpiryDate field has the expected value
        testResultsSteps.valueForFieldInPathShouldStartWith("[0].testTypes[0].testExpiryDate", testExpectedDate.substring(0, 10));

//        // Verify testAnniversaryDate field has the expected value
//        testResultsSteps.valueForFieldInPathShouldStartWith("[0].testTypes[0].testAnniversaryDate", testExpectedDate.substring(0,10));

    }


    @WithTag("expiry_dates")
    @Title("CVSB-8684 - TC1 - AC1 - PSV Annual test WITH PREVIOUS Expiry Date - Previous testExpiryDate = Today + 1 day")
    @Test
    public void testResultsFirstTestExpiryPsvTodayPlusOneDay() {

        // Read the base INSERT test result JSON.
        String insertedTestResultRecord = GenericData.readJsonValueFromFile("test-results_insert_expiry_date_psv_8684.json", "$");
        // Read the base POST test result JSON.
        String postTestResultRecord = GenericData.readJsonValueFromFile("test-results_post_expiry_date_psv_8684.json", "$");

        DateTime currentTimestamp = DateTime.now().withZone(DateTimeZone.UTC);

        //
        String randomVin = GenericData.generateRandomVin();
        String randomTestResultId = RandomStringUtils.randomNumeric(5);

        // Create inserted record.
        DateTime insertedTestStartTimestamp = currentTimestamp.minusYears(1).plusDays(2).minusMinutes(15);
        DateTime insertedTestTypeStartTimestamp = currentTimestamp.minusYears(1).plusDays(2).minusMinutes(10);
        DateTime insertedLastUpdatedAt = currentTimestamp.minusYears(1).plusDays(2).minusMinutes(5);

        DateTime insertedTestAnniversaryDate = currentTimestamp.minusMonths(2).plusDays(1);
        DateTime insertedTestExpiryDate = currentTimestamp.plusDays(1);

        DateTime insertedCreatedAt = currentTimestamp.minusYears(1).plusDays(2).minusMinutes(5);
        DateTime insertedTestTypeEndTimestamp = currentTimestamp.minusYears(1).plusDays(2).minusMinutes(5);
        DateTime insertedTestEndTimestamp = currentTimestamp.minusYears(1).plusDays(2);

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
        JsonPathAlteration alterationInsertTestResultId = new JsonPathAlteration("$.testResultId", RandomStringUtils.randomNumeric(5), "", "REPLACE");
        JsonPathAlteration alterationInsertTestStartTimestamp = new JsonPathAlteration("$.testStartTimestamp", insertableTestStartTimestamp, "", "REPLACE");
        JsonPathAlteration alterationInsertTestTypeStartTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeStartTimestamp", insertableTestTypeStartTimestamp, "", "REPLACE");
        JsonPathAlteration alterationInsertLastUpdatedAt = new JsonPathAlteration("$.testTypes[0].lastUpdatedAt", insertableLastUpdatedAt, "", "REPLACE");
        JsonPathAlteration alterationInsertTestAnniversaryDate = new JsonPathAlteration("$.testTypes[0].testAnniversaryDate", insertableTestAnniversaryDate, "", "REPLACE");
        JsonPathAlteration alterationInsertTestExpiryDate = new JsonPathAlteration("$.testTypes[0].testExpiryDate", insertableTestExpiryDate, "", "REPLACE");
        JsonPathAlteration alterationInsertCreatedAt = new JsonPathAlteration("$.testTypes[0].createdAt", insertableCreatedAt, "", "REPLACE");
        JsonPathAlteration alterationInsertTestTypeEndTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeEndTimestamp", insertableTestTypeEndTimestamp, "", "REPLACE");
        JsonPathAlteration alterationInsertTestEndTimestamp = new JsonPathAlteration("$.testEndTimestamp", insertableTestEndTimestamp, "", "REPLACE");
        JsonPathAlteration alterationInsertTestCode = new JsonPathAlteration("$.testTypes[0].testCode", insertedTestCode, "", "REPLACE");

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
                alterationInsertTestCode,
                alterationInsertTestEndTimestamp
        ));

        if (isAnnualWithCertificate) {
            insertAlterations.add(new JsonPathAlteration("$.testTypes[0]", "Annual With Certificate", "testTypeClassification", "ADD_FIELD"));
        }

        // Insert the altered record
        String alteredJson = GenericData.applyJsonAlterations(insertedTestResultRecord, insertAlterations);
        testResultsSteps.insertRecordInDynamo(alteredJson, "test-results");

//        System.out.println("\n######################## INSERTED ########################\n\n");
//        System.out.println(new GsonBuilder().setPrettyPrinting().create().toJson(new JsonParser().parse(alteredJson).getAsJsonObject()));
//        System.out.println("\n########################    END   ########################\n\n");


        // Create submitted
        DateTime submittedTestStartTimestamp = currentTimestamp.minusMinutes(15);
        DateTime submittedTestTypeStartTimestamp = currentTimestamp.plusMinutes(10);
        DateTime submittedTypeEndTimestamp = currentTimestamp.plusMinutes(5);
        DateTime submittedEndTimestamp = currentTimestamp;

        String testStartTimestamp = submittedTestStartTimestamp.toInstant().toString();
        String testTypeStartTimestamp = submittedTestTypeStartTimestamp.toInstant().toString();
        String testTypeEndTimestamp = submittedTypeEndTimestamp.toInstant().toString();
        String testEndTimestamp = submittedEndTimestamp.toInstant().toString();

        String testExpectedDate = insertedTestExpiryDate.plusYears(1).toInstant().toString();


        // Create alteration to add one more tech record to in the request body
        JsonPathAlteration alterationTestStartTimestamp = new JsonPathAlteration("$.testStartTimestamp", testStartTimestamp, "", "REPLACE");
        JsonPathAlteration alterationTestEndTimestamp = new JsonPathAlteration("$.testEndTimestamp", testEndTimestamp, "", "REPLACE");
        JsonPathAlteration alterationTestTypeStartTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeStartTimestamp", testTypeStartTimestamp, "", "REPLACE");
        JsonPathAlteration alterationTestTypeEndTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeEndTimestamp", testTypeEndTimestamp, "", "REPLACE");
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationTestResultId = new JsonPathAlteration("$.testResultId", randomTestResultId, "", "REPLACE");

        JsonPathAlteration alterationTestName = new JsonPathAlteration("$.testTypes[0].name", name, "", "REPLACE");
        JsonPathAlteration alterationTestTypeId = new JsonPathAlteration("$.testTypes[0].testTypeId", testTypeId, "", "REPLACE");
        JsonPathAlteration alterationTestTypeName = new JsonPathAlteration("$.testTypes[0].testTypeName", testTypeName, "", "REPLACE");
        JsonPathAlteration alterationTestResult = new JsonPathAlteration("$.testTypes[0].testResult", testResult, "", "REPLACE");
        JsonPathAlteration alterationVehicleSize = new JsonPathAlteration("$.vehicleSize", vehicleSize, "", "REPLACE");
        JsonPathAlteration alterationVehicleConfiguration = new JsonPathAlteration("$.vehicleConfiguration", vehicleConfiguration, "", "REPLACE");

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
                alterationVehicleSize,
                alterationVehicleConfiguration,
                alterationTestResult
        ));

        // Post the results, together with any alterations, and verify that they are accepted.
        testResultsSteps.postVehicleTestResultsWithAlterations(postTestResultRecord, alterations);

//        System.out.println("\n######################## POSTED ########################\n\n");
//        System.out.println(new GsonBuilder().setPrettyPrinting().create().toJson(new JsonParser().parse(GenericData.applyJsonAlterations(postTestResultRecord, alterations)).getAsJsonObject()));
//        System.out.println("\n########################   END  ########################\n\n");

        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");

        // Retrieve the created record, and verify that the fields are present.
        testResultsSteps.getTestResultsBetweenDate(randomVin, submittedTestStartTimestamp.minusMinutes(10).toInstant().toString(), submittedEndTimestamp.plusMinutes(10).toInstant().toString());
        testResultsSteps.statusCodeShouldBe(200);

        // Verify testCode field has the expected value
        testResultsSteps.valueForFieldInPathShouldBe("[0].testTypes[0].testCode", testCode);

        // Verify testExpiryDate field has the expected value
        testResultsSteps.valueForFieldInPathShouldStartWith("[0].testTypes[0].testExpiryDate", testExpectedDate.substring(0, 10));

//        // Verify testAnniversaryDate field has the expected value
//        testResultsSteps.valueForFieldInPathShouldStartWith("[0].testTypes[0].testAnniversaryDate", testExpectedDate.substring(0,10));

    }


    @WithTag("expiry_dates")
    @Title("CVSB-8684 - TC1 - AC1 - PSV Annual test WITH PREVIOUS Expiry Date - Previous testExpiryDate = Today + 2 months")
    @Test
    public void testResultsFirstTestExpiryPsvTodayPlusTwoMonths() {

        // Read the base INSERT test result JSON.
        String insertedTestResultRecord = GenericData.readJsonValueFromFile("test-results_insert_expiry_date_psv_8684.json", "$");
        // Read the base POST test result JSON.
        String postTestResultRecord = GenericData.readJsonValueFromFile("test-results_post_expiry_date_psv_8684.json", "$");

        DateTime currentTimestamp = DateTime.now().withZone(DateTimeZone.UTC);

        //
        String randomVin = GenericData.generateRandomVin();
        String randomTestResultId = RandomStringUtils.randomNumeric(5);

        // Create inserted record.
        DateTime insertedTestStartTimestamp = currentTimestamp.minusYears(1).plusMonths(2).plusDays(1).minusMinutes(15);
        DateTime insertedTestTypeStartTimestamp = currentTimestamp.minusYears(1).plusMonths(2).plusDays(1).minusMinutes(10);
        DateTime insertedLastUpdatedAt = currentTimestamp.minusYears(1).plusMonths(2).plusDays(1).minusMinutes(5);

        DateTime insertedTestAnniversaryDate = currentTimestamp;
        DateTime insertedTestExpiryDate = currentTimestamp.plusMonths(2);

        DateTime insertedCreatedAt = currentTimestamp.minusYears(1).plusMonths(2).plusDays(1).minusMinutes(5);
        DateTime insertedTestTypeEndTimestamp = currentTimestamp.minusYears(1).plusMonths(2).plusDays(1).minusMinutes(5);
        DateTime insertedTestEndTimestamp = currentTimestamp.minusYears(1).plusMonths(2).plusDays(1);

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
        JsonPathAlteration alterationInsertTestResultId = new JsonPathAlteration("$.testResultId", RandomStringUtils.randomNumeric(5), "", "REPLACE");
        JsonPathAlteration alterationInsertTestStartTimestamp = new JsonPathAlteration("$.testStartTimestamp", insertableTestStartTimestamp, "", "REPLACE");
        JsonPathAlteration alterationInsertTestTypeStartTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeStartTimestamp", insertableTestTypeStartTimestamp, "", "REPLACE");
        JsonPathAlteration alterationInsertLastUpdatedAt = new JsonPathAlteration("$.testTypes[0].lastUpdatedAt", insertableLastUpdatedAt, "", "REPLACE");
        JsonPathAlteration alterationInsertTestAnniversaryDate = new JsonPathAlteration("$.testTypes[0].testAnniversaryDate", insertableTestAnniversaryDate, "", "REPLACE");
        JsonPathAlteration alterationInsertTestExpiryDate = new JsonPathAlteration("$.testTypes[0].testExpiryDate", insertableTestExpiryDate, "", "REPLACE");
        JsonPathAlteration alterationInsertCreatedAt = new JsonPathAlteration("$.testTypes[0].createdAt", insertableCreatedAt, "", "REPLACE");
        JsonPathAlteration alterationInsertTestTypeEndTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeEndTimestamp", insertableTestTypeEndTimestamp, "", "REPLACE");
        JsonPathAlteration alterationInsertTestEndTimestamp = new JsonPathAlteration("$.testEndTimestamp", insertableTestEndTimestamp, "", "REPLACE");
        JsonPathAlteration alterationInsertTestCode = new JsonPathAlteration("$.testTypes[0].testCode", insertedTestCode, "", "REPLACE");

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
                alterationInsertTestCode,
                alterationInsertTestEndTimestamp
        ));

        if (isAnnualWithCertificate) {
            insertAlterations.add(new JsonPathAlteration("$.testTypes[0]", "Annual With Certificate", "testTypeClassification", "ADD_FIELD"));
        }

        // Insert the altered record
        String alteredJson = GenericData.applyJsonAlterations(insertedTestResultRecord, insertAlterations);
        testResultsSteps.insertRecordInDynamo(alteredJson, "test-results");

//        System.out.println("\n######################## INSERTED ########################\n\n");
//        System.out.println(new GsonBuilder().setPrettyPrinting().create().toJson(new JsonParser().parse(alteredJson).getAsJsonObject()));
//        System.out.println("\n########################    END   ########################\n\n");


        // Create submitted
        DateTime submittedTestStartTimestamp = currentTimestamp.minusMinutes(15);
        DateTime submittedTestTypeStartTimestamp = currentTimestamp.plusMinutes(10);
        DateTime submittedTypeEndTimestamp = currentTimestamp.plusMinutes(5);
        DateTime submittedEndTimestamp = currentTimestamp;

        String testStartTimestamp = submittedTestStartTimestamp.toInstant().toString();
        String testTypeStartTimestamp = submittedTestTypeStartTimestamp.toInstant().toString();
        String testTypeEndTimestamp = submittedTypeEndTimestamp.toInstant().toString();
        String testEndTimestamp = submittedEndTimestamp.toInstant().toString();

        String testExpectedDate = insertedTestExpiryDate.plusYears(1).toInstant().toString();


        // Create alteration to add one more tech record to in the request body
        JsonPathAlteration alterationTestStartTimestamp = new JsonPathAlteration("$.testStartTimestamp", testStartTimestamp, "", "REPLACE");
        JsonPathAlteration alterationTestEndTimestamp = new JsonPathAlteration("$.testEndTimestamp", testEndTimestamp, "", "REPLACE");
        JsonPathAlteration alterationTestTypeStartTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeStartTimestamp", testTypeStartTimestamp, "", "REPLACE");
        JsonPathAlteration alterationTestTypeEndTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeEndTimestamp", testTypeEndTimestamp, "", "REPLACE");
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationTestResultId = new JsonPathAlteration("$.testResultId", randomTestResultId, "", "REPLACE");

        JsonPathAlteration alterationTestName = new JsonPathAlteration("$.testTypes[0].name", name, "", "REPLACE");
        JsonPathAlteration alterationTestTypeId = new JsonPathAlteration("$.testTypes[0].testTypeId", testTypeId, "", "REPLACE");
        JsonPathAlteration alterationTestTypeName = new JsonPathAlteration("$.testTypes[0].testTypeName", testTypeName, "", "REPLACE");
        JsonPathAlteration alterationTestResult = new JsonPathAlteration("$.testTypes[0].testResult", testResult, "", "REPLACE");
        JsonPathAlteration alterationVehicleSize = new JsonPathAlteration("$.vehicleSize", vehicleSize, "", "REPLACE");
        JsonPathAlteration alterationVehicleConfiguration = new JsonPathAlteration("$.vehicleConfiguration", vehicleConfiguration, "", "REPLACE");

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
                alterationVehicleSize,
                alterationVehicleConfiguration,
                alterationTestResult
        ));

        // Post the results, together with any alterations, and verify that they are accepted.
        testResultsSteps.postVehicleTestResultsWithAlterations(postTestResultRecord, alterations);

//        System.out.println("\n######################## POSTED ########################\n\n");
//        System.out.println(new GsonBuilder().setPrettyPrinting().create().toJson(new JsonParser().parse(GenericData.applyJsonAlterations(postTestResultRecord, alterations)).getAsJsonObject()));
//        System.out.println("\n########################   END  ########################\n\n");

        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");

        // Retrieve the created record, and verify that the fields are present.
        testResultsSteps.getTestResultsBetweenDate(randomVin, submittedTestStartTimestamp.minusMinutes(10).toInstant().toString(), submittedEndTimestamp.plusMinutes(10).toInstant().toString());
        testResultsSteps.statusCodeShouldBe(200);

        // Verify testCode field has the expected value
        testResultsSteps.valueForFieldInPathShouldBe("[0].testTypes[0].testCode", testCode);

        // Verify testExpiryDate field has the expected value
        testResultsSteps.valueForFieldInPathShouldStartWith("[0].testTypes[0].testExpiryDate", testExpectedDate.substring(0, 10));

//        // Verify testAnniversaryDate field has the expected value
//        testResultsSteps.valueForFieldInPathShouldStartWith("[0].testTypes[0].testAnniversaryDate", testExpectedDate.substring(0,10));

    }


    @WithTag("expiry_dates")
    @Title("CVSB-8684 - TC1 - AC1 - PSV Annual test WITH PREVIOUS Expiry Date - Previous testExpiryDate = Today + 2 months + 1 day")
    @Test
    public void testResultsFirstTestExpiryPsvTodayPlusTwoMonthsPlusOneDay() {

        // Read the base INSERT test result JSON.
        String insertedTestResultRecord = GenericData.readJsonValueFromFile("test-results_insert_expiry_date_psv_8684.json", "$");
        // Read the base POST test result JSON.
        String postTestResultRecord = GenericData.readJsonValueFromFile("test-results_post_expiry_date_psv_8684.json", "$");

        DateTime currentTimestamp = DateTime.now().withZone(DateTimeZone.UTC);

        //
        String randomVin = GenericData.generateRandomVin();
        String randomTestResultId = RandomStringUtils.randomNumeric(5);

        // Create inserted record.
        DateTime insertedTestStartTimestamp = currentTimestamp.minusYears(1).plusMonths(2).plusDays(2).minusMinutes(15);
        DateTime insertedTestTypeStartTimestamp = currentTimestamp.minusYears(1).plusMonths(2).plusDays(2).minusMinutes(10);
        DateTime insertedLastUpdatedAt = currentTimestamp.minusYears(1).plusMonths(2).plusDays(2).minusMinutes(5);

        DateTime insertedTestAnniversaryDate = currentTimestamp.plusDays(1);
        DateTime insertedTestExpiryDate = currentTimestamp.plusMonths(2).plusDays(1);

        DateTime insertedCreatedAt = currentTimestamp.minusYears(1).plusMonths(2).plusDays(2).minusMinutes(5);
        DateTime insertedTestTypeEndTimestamp = currentTimestamp.minusYears(1).plusMonths(2).plusDays(2).minusMinutes(5);
        DateTime insertedTestEndTimestamp = currentTimestamp.minusYears(1).plusMonths(2).plusDays(2);

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
        JsonPathAlteration alterationInsertTestResultId = new JsonPathAlteration("$.testResultId", RandomStringUtils.randomNumeric(5), "", "REPLACE");
        JsonPathAlteration alterationInsertTestStartTimestamp = new JsonPathAlteration("$.testStartTimestamp", insertableTestStartTimestamp, "", "REPLACE");
        JsonPathAlteration alterationInsertTestTypeStartTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeStartTimestamp", insertableTestTypeStartTimestamp, "", "REPLACE");
        JsonPathAlteration alterationInsertLastUpdatedAt = new JsonPathAlteration("$.testTypes[0].lastUpdatedAt", insertableLastUpdatedAt, "", "REPLACE");
        JsonPathAlteration alterationInsertTestAnniversaryDate = new JsonPathAlteration("$.testTypes[0].testAnniversaryDate", insertableTestAnniversaryDate, "", "REPLACE");
        JsonPathAlteration alterationInsertTestExpiryDate = new JsonPathAlteration("$.testTypes[0].testExpiryDate", insertableTestExpiryDate, "", "REPLACE");
        JsonPathAlteration alterationInsertCreatedAt = new JsonPathAlteration("$.testTypes[0].createdAt", insertableCreatedAt, "", "REPLACE");
        JsonPathAlteration alterationInsertTestTypeEndTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeEndTimestamp", insertableTestTypeEndTimestamp, "", "REPLACE");
        JsonPathAlteration alterationInsertTestEndTimestamp = new JsonPathAlteration("$.testEndTimestamp", insertableTestEndTimestamp, "", "REPLACE");
        JsonPathAlteration alterationInsertTestCode = new JsonPathAlteration("$.testTypes[0].testCode", insertedTestCode, "", "REPLACE");

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
                alterationInsertTestCode,
                alterationInsertTestEndTimestamp
        ));

        if (isAnnualWithCertificate) {
            insertAlterations.add(new JsonPathAlteration("$.testTypes[0]", "Annual With Certificate", "testTypeClassification", "ADD_FIELD"));
        }

        // Insert the altered record
        String alteredJson = GenericData.applyJsonAlterations(insertedTestResultRecord, insertAlterations);
        testResultsSteps.insertRecordInDynamo(alteredJson, "test-results");

//        System.out.println("\n######################## INSERTED ########################\n\n");
//        System.out.println(new GsonBuilder().setPrettyPrinting().create().toJson(new JsonParser().parse(alteredJson).getAsJsonObject()));
//        System.out.println("\n########################    END   ########################\n\n");


        // Create submitted
        DateTime submittedTestStartTimestamp = currentTimestamp.minusMinutes(15);
        DateTime submittedTestTypeStartTimestamp = currentTimestamp.plusMinutes(10);
        DateTime submittedTypeEndTimestamp = currentTimestamp.plusMinutes(5);
        DateTime submittedEndTimestamp = currentTimestamp;

        String testStartTimestamp = submittedTestStartTimestamp.toInstant().toString();
        String testTypeStartTimestamp = submittedTestTypeStartTimestamp.toInstant().toString();
        String testTypeEndTimestamp = submittedTypeEndTimestamp.toInstant().toString();
        String testEndTimestamp = submittedEndTimestamp.toInstant().toString();

        String testExpectedDate = currentTimestamp.plusYears(1).minusDays(1).toInstant().toString();


        // Create alteration to add one more tech record to in the request body
        JsonPathAlteration alterationTestStartTimestamp = new JsonPathAlteration("$.testStartTimestamp", testStartTimestamp, "", "REPLACE");
        JsonPathAlteration alterationTestEndTimestamp = new JsonPathAlteration("$.testEndTimestamp", testEndTimestamp, "", "REPLACE");
        JsonPathAlteration alterationTestTypeStartTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeStartTimestamp", testTypeStartTimestamp, "", "REPLACE");
        JsonPathAlteration alterationTestTypeEndTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeEndTimestamp", testTypeEndTimestamp, "", "REPLACE");
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationTestResultId = new JsonPathAlteration("$.testResultId", randomTestResultId, "", "REPLACE");

        JsonPathAlteration alterationTestName = new JsonPathAlteration("$.testTypes[0].name", name, "", "REPLACE");
        JsonPathAlteration alterationTestTypeId = new JsonPathAlteration("$.testTypes[0].testTypeId", testTypeId, "", "REPLACE");
        JsonPathAlteration alterationTestTypeName = new JsonPathAlteration("$.testTypes[0].testTypeName", testTypeName, "", "REPLACE");
        JsonPathAlteration alterationTestResult = new JsonPathAlteration("$.testTypes[0].testResult", testResult, "", "REPLACE");
        JsonPathAlteration alterationVehicleSize = new JsonPathAlteration("$.vehicleSize", vehicleSize, "", "REPLACE");
        JsonPathAlteration alterationVehicleConfiguration = new JsonPathAlteration("$.vehicleConfiguration", vehicleConfiguration, "", "REPLACE");

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
                alterationVehicleSize,
                alterationVehicleConfiguration,
                alterationTestResult
        ));

        // Post the results, together with any alterations, and verify that they are accepted.
        testResultsSteps.postVehicleTestResultsWithAlterations(postTestResultRecord, alterations);

//        System.out.println("\n######################## POSTED ########################\n\n");
//        System.out.println(new GsonBuilder().setPrettyPrinting().create().toJson(new JsonParser().parse(GenericData.applyJsonAlterations(postTestResultRecord, alterations)).getAsJsonObject()));
//        System.out.println("\n########################   END  ########################\n\n");

        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");

        // Retrieve the created record, and verify that the fields are present.
        testResultsSteps.getTestResultsBetweenDate(randomVin, submittedTestStartTimestamp.minusMinutes(10).toInstant().toString(), submittedEndTimestamp.plusMinutes(10).toInstant().toString());
        testResultsSteps.statusCodeShouldBe(200);

        // Verify testCode field has the expected value
        testResultsSteps.valueForFieldInPathShouldBe("[0].testTypes[0].testCode", testCode);

        // Verify testExpiryDate field has the expected value
        testResultsSteps.valueForFieldInPathShouldStartWith("[0].testTypes[0].testExpiryDate", testExpectedDate.substring(0, 10));

//        // Verify testAnniversaryDate field has the expected value
//        testResultsSteps.valueForFieldInPathShouldStartWith("[0].testTypes[0].testAnniversaryDate", testExpectedDate.substring(0,10));

    }


    @WithTag("expiry_dates")
    @Title("CVSB-8684 - TC1 - AC1 - PSV Annual test WITH PREVIOUS Expiry Date - Previous testExpiryDate = Today + 2 months - 1 day")
    @Test
    public void testResultsFirstTestExpiryPsvTodayPlusTwoMonthsMinusOneDay() {

        // Read the base INSERT test result JSON.
        String insertedTestResultRecord = GenericData.readJsonValueFromFile("test-results_insert_expiry_date_psv_8684.json", "$");
        // Read the base POST test result JSON.
        String postTestResultRecord = GenericData.readJsonValueFromFile("test-results_post_expiry_date_psv_8684.json", "$");

        DateTime currentTimestamp = DateTime.now().withZone(DateTimeZone.UTC);

        //
        String randomVin = GenericData.generateRandomVin();
        String randomTestResultId = RandomStringUtils.randomNumeric(5);

        // Create inserted record.
        DateTime insertedTestStartTimestamp = currentTimestamp.minusYears(1).plusMonths(2).minusMinutes(15);
        DateTime insertedTestTypeStartTimestamp = currentTimestamp.minusYears(1).plusMonths(2).minusMinutes(10);
        DateTime insertedLastUpdatedAt = currentTimestamp.minusYears(1).plusMonths(2).minusMinutes(5);

        DateTime insertedTestAnniversaryDate = currentTimestamp.minusDays(1);
        DateTime insertedTestExpiryDate = currentTimestamp.plusMonths(2).minusDays(1);

        DateTime insertedCreatedAt = currentTimestamp.minusYears(1).plusMonths(2).minusMinutes(5);
        DateTime insertedTestTypeEndTimestamp = currentTimestamp.minusYears(1).plusMonths(2).minusMinutes(5);
        DateTime insertedTestEndTimestamp = currentTimestamp.minusYears(1).plusMonths(2);

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
        JsonPathAlteration alterationInsertTestResultId = new JsonPathAlteration("$.testResultId", RandomStringUtils.randomNumeric(5), "", "REPLACE");
        JsonPathAlteration alterationInsertTestStartTimestamp = new JsonPathAlteration("$.testStartTimestamp", insertableTestStartTimestamp, "", "REPLACE");
        JsonPathAlteration alterationInsertTestTypeStartTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeStartTimestamp", insertableTestTypeStartTimestamp, "", "REPLACE");
        JsonPathAlteration alterationInsertLastUpdatedAt = new JsonPathAlteration("$.testTypes[0].lastUpdatedAt", insertableLastUpdatedAt, "", "REPLACE");
        JsonPathAlteration alterationInsertTestAnniversaryDate = new JsonPathAlteration("$.testTypes[0].testAnniversaryDate", insertableTestAnniversaryDate, "", "REPLACE");
        JsonPathAlteration alterationInsertTestExpiryDate = new JsonPathAlteration("$.testTypes[0].testExpiryDate", insertableTestExpiryDate, "", "REPLACE");
        JsonPathAlteration alterationInsertCreatedAt = new JsonPathAlteration("$.testTypes[0].createdAt", insertableCreatedAt, "", "REPLACE");
        JsonPathAlteration alterationInsertTestTypeEndTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeEndTimestamp", insertableTestTypeEndTimestamp, "", "REPLACE");
        JsonPathAlteration alterationInsertTestEndTimestamp = new JsonPathAlteration("$.testEndTimestamp", insertableTestEndTimestamp, "", "REPLACE");
        JsonPathAlteration alterationInsertTestCode = new JsonPathAlteration("$.testTypes[0].testCode", insertedTestCode, "", "REPLACE");

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
                alterationInsertTestCode,
                alterationInsertTestEndTimestamp
        ));

        if (isAnnualWithCertificate) {
            insertAlterations.add(new JsonPathAlteration("$.testTypes[0]", "Annual With Certificate", "testTypeClassification", "ADD_FIELD"));
        }

        // Insert the altered record
        String alteredJson = GenericData.applyJsonAlterations(insertedTestResultRecord, insertAlterations);
        testResultsSteps.insertRecordInDynamo(alteredJson, "test-results");

//        System.out.println("\n######################## INSERTED ########################\n\n");
//        System.out.println(new GsonBuilder().setPrettyPrinting().create().toJson(new JsonParser().parse(alteredJson).getAsJsonObject()));
//        System.out.println("\n########################    END   ########################\n\n");


        // Create submitted
        DateTime submittedTestStartTimestamp = currentTimestamp.minusMinutes(15);
        DateTime submittedTestTypeStartTimestamp = currentTimestamp.plusMinutes(10);
        DateTime submittedTypeEndTimestamp = currentTimestamp.plusMinutes(5);
        DateTime submittedEndTimestamp = currentTimestamp;

        String testStartTimestamp = submittedTestStartTimestamp.toInstant().toString();
        String testTypeStartTimestamp = submittedTestTypeStartTimestamp.toInstant().toString();
        String testTypeEndTimestamp = submittedTypeEndTimestamp.toInstant().toString();
        String testEndTimestamp = submittedEndTimestamp.toInstant().toString();

        String testExpectedDate = insertedTestExpiryDate.plusYears(1).toInstant().toString();


        // Create alteration to add one more tech record to in the request body
        JsonPathAlteration alterationTestStartTimestamp = new JsonPathAlteration("$.testStartTimestamp", testStartTimestamp, "", "REPLACE");
        JsonPathAlteration alterationTestEndTimestamp = new JsonPathAlteration("$.testEndTimestamp", testEndTimestamp, "", "REPLACE");
        JsonPathAlteration alterationTestTypeStartTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeStartTimestamp", testTypeStartTimestamp, "", "REPLACE");
        JsonPathAlteration alterationTestTypeEndTimestamp = new JsonPathAlteration("$.testTypes[0].testTypeEndTimestamp", testTypeEndTimestamp, "", "REPLACE");
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationTestResultId = new JsonPathAlteration("$.testResultId", randomTestResultId, "", "REPLACE");

        JsonPathAlteration alterationTestName = new JsonPathAlteration("$.testTypes[0].name", name, "", "REPLACE");
        JsonPathAlteration alterationTestTypeId = new JsonPathAlteration("$.testTypes[0].testTypeId", testTypeId, "", "REPLACE");
        JsonPathAlteration alterationTestTypeName = new JsonPathAlteration("$.testTypes[0].testTypeName", testTypeName, "", "REPLACE");
        JsonPathAlteration alterationTestResult = new JsonPathAlteration("$.testTypes[0].testResult", testResult, "", "REPLACE");
        JsonPathAlteration alterationVehicleSize = new JsonPathAlteration("$.vehicleSize", vehicleSize, "", "REPLACE");
        JsonPathAlteration alterationVehicleConfiguration = new JsonPathAlteration("$.vehicleConfiguration", vehicleConfiguration, "", "REPLACE");

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
                alterationVehicleSize,
                alterationVehicleConfiguration,
                alterationTestResult
        ));

        // Post the results, together with any alterations, and verify that they are accepted.
        testResultsSteps.postVehicleTestResultsWithAlterations(postTestResultRecord, alterations);

//        System.out.println("\n######################## POSTED ########################\n\n");
//        System.out.println(new GsonBuilder().setPrettyPrinting().create().toJson(new JsonParser().parse(GenericData.applyJsonAlterations(postTestResultRecord, alterations)).getAsJsonObject()));
//        System.out.println("\n########################   END  ########################\n\n");

        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");

        // Retrieve the created record, and verify that the fields are present.
        testResultsSteps.getTestResultsBetweenDate(randomVin, submittedTestStartTimestamp.minusMinutes(10).toInstant().toString(), submittedEndTimestamp.plusMinutes(10).toInstant().toString());
        testResultsSteps.statusCodeShouldBe(200);

        // Verify testCode field has the expected value
        testResultsSteps.valueForFieldInPathShouldBe("[0].testTypes[0].testCode", testCode);

        // Verify testExpiryDate field has the expected value
        testResultsSteps.valueForFieldInPathShouldStartWith("[0].testTypes[0].testExpiryDate", testExpectedDate.substring(0, 10));

//        // Verify testAnniversaryDate field has the expected value
//        testResultsSteps.valueForFieldInPathShouldStartWith("[0].testTypes[0].testAnniversaryDate", testExpectedDate.substring(0,10));

    }
}
