package data;

import data.config.BaseData;
import data.config.DataMapper;
import model.activities.ActivitiesGet;
import org.apache.commons.lang3.RandomStringUtils;

public class ActivitiesData {

    public static ActivitiesGet.Builder buildActivitiesData() {

        ActivitiesGet.Builder activities = DataMapper.getValue(ActivitiesGet.Builder.class, "loader/" + BaseData.getDataLocation() + "/activities.json");

        return activities.setTesterStaffId(RandomStringUtils.randomAlphanumeric(20) + RandomStringUtils.randomNumeric(20));

    }

}
