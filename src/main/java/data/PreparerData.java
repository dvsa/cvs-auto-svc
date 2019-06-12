package data;

import data.config.DataMapper;
import model.Preparer;
import util.DataLoader;

public class PreparerData {

    public static Preparer buildPreparerData() {

        Preparer preparer = DataMapper.getValue(Preparer.class, "loader/" + DataLoader.getDataLocation() + "/preparers.json");

        return preparer;

    }
}
