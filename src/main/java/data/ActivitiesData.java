package data;

import data.config.DataMapper;
import model.activities.ActivitiesGet;
import org.apache.commons.lang3.RandomStringUtils;
import util.DataLoader;

public class ActivitiesData {

    public static ActivitiesGet.Builder buildActivitiesData() {

        ActivitiesGet.Builder activities = DataMapper.getValue(ActivitiesGet.Builder.class, "loader/" + DataLoader.getDataLocation() + "/activities.json");

        return activities.setTesterStaffId(RandomStringUtils.randomAlphanumeric(20) + RandomStringUtils.randomNumeric(20));

    }

}
