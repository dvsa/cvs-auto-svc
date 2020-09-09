package atf;

import data.GenericData;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import steps.ActivitiesSteps;
import steps.TestResultsSteps;
import steps.TestStationSteps;
import util.*;

import java.text.SimpleDateFormat;
import java.util.*;


@RunWith(SerenityRunner.class)
public class EndVisitCheckAtfEmail {

    @Steps
    ActivitiesSteps activitiesSteps;

    @Steps
    TestStationSteps testStationSteps;

    @Steps
    TestResultsSteps testResultsSteps;

    @Ignore("Untill CVSB-11826 is fixed ")
    @Title("CVSB-10530 - Check email to ATF lands in inbox")
    @Test
    public void endVisit_Check_Atf_Email() {

        String emailAddress = testResultsSteps.getOutlookEmailAddress();
        testStationSteps.updateEmailsForTestStation("20", emailAddress);
        String json = GenericData.readJsonValueFromFile("activities_10530.json", "$");
        String testerStaffId = GenericData.extractValueFromJsonString(json, "$.testerStaffId").toString();
        activitiesSteps.deleteActivitiesForUser(testerStaffId);
        String testerName = "Automation_" + RandomStringUtils.randomAlphanumeric(10);
        String id = String.valueOf(UUID.randomUUID());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        Date currentDateTime = new Date();
        String startTimestamp = sdf.format(currentDateTime);
        String startTime = startTimestamp.substring(11, 19);
        // create alteration to change activity id in the post request body with the random generated id
        JsonPathAlteration alterationId = new JsonPathAlteration("$.id", id, "", "REPLACE");
        // create alteration to change testerName in the post request body with the random generated testerName
        JsonPathAlteration alterationTesterName = new JsonPathAlteration("$.testerName", testerName, "", "REPLACE");
        // create alteration to change startTime in the post request body with the current time
        JsonPathAlteration alterationStartTime = new JsonPathAlteration("$.startTime", startTimestamp, "", "REPLACE");
        // initialize the alterations list with only the alterations for changing activity id
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(alterationId, alterationTesterName,
                alterationStartTime));
        String alteredJson = GenericData.applyJsonAlterations(json, alterations);
        String testStationPNumber = GenericData.extractValueFromJsonString(json, "$.testStationPNumber").toString();
        String testStationName = GenericData.extractValueFromJsonString(json, "$.testStationName").toString();
        activitiesSteps.insertRecordInDynamo(alteredJson, "activities", "id");

        activitiesSteps.putActivitiesEnd(id);
        activitiesSteps.statusCodeShouldBe(204);

        //Verify email is sent to VSA
        WebDriver driver  = activitiesSteps.validateAtfEmail(testerName);

        String pattern = "dd/MM/yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String startDate = simpleDateFormat.format(currentDateTime);
        activitiesSteps.validateAtfEmailDetails(driver, testStationName, testStationPNumber, testerName,
                startDate, startTime);
    }
}
