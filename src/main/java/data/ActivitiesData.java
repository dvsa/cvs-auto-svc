package data;

import model.Activities;
import org.apache.commons.lang3.RandomStringUtils;

public class ActivitiesData {

    public static Activities buildActivitiesData() {

        return new Activities()
                .setActivityType("visit")
                .setTestStationName("string")
                .setTestStationPNumber("string")
                .setTestStationEmail("teststationname@dvsa.gov.uk")
                .setTestStationType("atf")
                .setTesterName("string")
                .setTesterStaffId(RandomStringUtils.randomAlphanumeric(20) + RandomStringUtils.randomNumeric(20));

    }

}
