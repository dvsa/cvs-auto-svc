package data;

import data.config.DataMapper;
import model.TestType;
import util.DataLoader;

public class TestTypeData {

    public static TestType buildTestTypeData() {

        TestType levelOneFirst = DataMapper.getValue(TestType.class, "loader/" + DataLoader.getDataLocation() + "/test-type.json");
        return levelOneFirst;

    }

}
