package data;

import data.config.BaseData;
import data.config.DataMapper;
import model.TestStations;

public class TestStationsData {

    public static TestStations buildTestStationData() {

        TestStations testStations = DataMapper.getValue(TestStations.class, "loader/" + BaseData.getDataLocation() + "/test-stations.json");

        return testStations;
    }
}
