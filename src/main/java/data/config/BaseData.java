package data.config;

import exceptions.AutomationException;
import util.*;

public class BaseData {


    private static Loader loader = new LocalLoaderImpl();

    public static String getDataLocation() {
        return loader.getDataPath();
    }
}
