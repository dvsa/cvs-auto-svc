package atf;

import data.GenericData;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import net.thucydides.core.annotations.WithTag;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import steps.ActivitiesSteps;
import util.*;

import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SerenityRunner.class)
public class EndVisitCheckAtfEmail {

    @Steps
    ActivitiesSteps activitiesSteps;

    @WithTag("In_Test")
    @Title("CVSB-10530 - Check email to ATF lands in inbox")
    @Test
    public void endVisit_Check_Atf_Email() {

        String testerName = RandomStringUtils.randomAlphanumeric(16);
        String id = String.valueOf(UUID.randomUUID());
        String json = GenericData.readJsonValueFromFile("activities_10530.json", "$");
        // create alteration to change activity id in the post request body with the random generated id
        JsonPathAlteration alterationId = new JsonPathAlteration("$.id", id, "", "REPLACE");
        // create alteration to change testerName in the post request body with the random generated testerName
        JsonPathAlteration alterationTesterName = new JsonPathAlteration("$.testerName", testerName, "", "REPLACE");
        // initialize the alterations list with only the alterations for changing activity id
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(alterationId, alterationTesterName));
        String alteredJson = GenericData.applyJsonAlterations(json, alterations);
        String testStationPNumber = GenericData.extractValueFromJsonString(json, "$.testStationPNumber").toString();
        activitiesSteps.insertRecordInDynamo(alteredJson, "test-activities", "id");

        activitiesSteps.putActivitiesEnd(id);

        //Verify email is sent to VSA
        WebDriver driver  = activitiesSteps.validateAtfEmail(testerName);

        String pattern = "dd/MM/yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(new Date());

        System.out.println("Checking the email title details are correct");
        String actualString = driver.findElement(By.cssSelector("div[role='main']>div:nth-of-type(1)")).getText();
        String expectedString = " DVSA Commercial Vehicle Service – Site Activity Report: " +
                testStationPNumber + " - " + testerName + " - " + date;
        assertEquals(expectedString, actualString);

        System.out.println("Checking the details in the email are correct");
//        String actualString1 = driver.findElement(By.cssSelector("div[role='main']>div:nth-of-type(2)")).getText();
//        String expectedString1 = "Please see the link below to access the test certificate for vehicle(s) " + randomVrm + " conducted on " + date;
//        assertTrue(actualString1.contains(expectedString1));

        driver.close();
        driver.quit();
    }
}
