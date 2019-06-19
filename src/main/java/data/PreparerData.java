package data;

import data.config.BaseData;
import data.config.DataMapper;
import model.Preparer;

public class PreparerData {

    public static Preparer buildPreparerData() {

        Preparer preparer = DataMapper.getValue(Preparer.class, "loader/" + BaseData.getDataLocation() + "/preparers.json");

        return preparer;

    }
}
