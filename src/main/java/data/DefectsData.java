package data;

import data.config.BaseData;
import data.config.DataMapper;
import model.defects.Defect;

public class DefectsData {

    public static Defect buildDefectsData() {

        Defect defect = DataMapper.getValue(Defect.class, "loader/" + BaseData.getDataLocation() + "/defect.json");

        return defect;
    }
}
