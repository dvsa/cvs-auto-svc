package data;

import data.config.DataMapper;
import model.defects.*;
import util.DataLoader;

public class DefectsData {

    public static Defect buildDefectsData() {

        Defect defect = DataMapper.getValue(Defect.class, "loader/" + DataLoader.getDataLocation() + "/defect.json");

        return defect;
    }
}
