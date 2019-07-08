package data;

import data.config.BaseData;
import data.config.DataMapper;
import model.activities.ActivitiesGet;
import model.activities.ActivitiesPost;
import model.activities.ActivitiesPut;
import org.apache.commons.lang3.RandomStringUtils;

public class ActivitiesData {

    public static ActivitiesGet.Builder buildActivitiesIdData() {

        ActivitiesGet.Builder activities = DataMapper.getValue(ActivitiesGet.Builder.class, "loader/" + BaseData.getDataLocation() + "/activities.json");

        return activities.setTesterStaffId(RandomStringUtils.randomAlphanumeric(20) + RandomStringUtils.randomNumeric(20));

    }

    public static ActivitiesPost.Builder buildActivitiesParentIdData() {

        ActivitiesPost.Builder activities = DataMapper.getValue(ActivitiesPost.Builder.class, "loader/" + BaseData.getDataLocation() + "/activities_parent_id.json");
        activities.setEndTime("2018-02-13").setStartTime("2018-02-13");
        return activities.setTesterStaffId(RandomStringUtils.randomAlphanumeric(20) + RandomStringUtils.randomNumeric(20));

    }

    public static ActivitiesPut.Builder buildActivitiesUpdateData() {

        ActivitiesPut.Builder activities = DataMapper.getValue(ActivitiesPut.Builder.class, "loader/" + BaseData.getDataLocation() + "/activities_update.json");
        return activities;

    }

}
