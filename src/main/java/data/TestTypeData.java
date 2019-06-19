package data;

import data.config.BaseData;
import data.config.DataMapper;
import model.TestType;

public class TestTypeData {

    public static TestType buildTestTypeData() {

        TestType levelOneFirst = DataMapper.getValue(TestType.class, "loader/" + BaseData.getDataLocation() + "/test-type.json");
        return levelOneFirst;

    }

}
