package data;

import model.activities.Activities;
import model.activities.ActivitiesGet;
import org.apache.commons.lang3.RandomStringUtils;

public class ActivitiesData {

    public static ActivitiesGet.Builder buildActivitiesData() {

        return new ActivitiesGet.Builder()
                .setActivityType("visit")
                .setTestStationName("string")
                .setTestStationPNumber("string")
                .setTestStationEmail("teststationname@dvsa.gov.uk")
                .setTestStationType("atf")
                .setTesterName("string")
                .setTesterStaffId(RandomStringUtils.randomAlphanumeric(20) + RandomStringUtils.randomNumeric(20));

    }

}
