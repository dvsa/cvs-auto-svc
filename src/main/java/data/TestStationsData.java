package data;

import data.config.DataMapper;
import model.TestStations;
import util.DataLoader;

public class TestStationsData {

    public static TestStations buildTestStationData() {

        TestStations testStations = DataMapper.getValue(TestStations.class, "loader/" + DataLoader.getDataLocation() + "/test-stations.json");

        return testStations;
    }
}
