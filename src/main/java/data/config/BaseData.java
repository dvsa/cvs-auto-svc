package data.config;

import exceptions.AutomationException;
import util.*;

public class BaseData {


    private static Loader loader;

    static {
        EnvironmentType envType = TypeLoader.getType();

        switch (envType) {
            case CI_DEVELOP:
                loader = new CIDevelopLoaderImpl();
                break;
            case LOCAL:
                loader = new LocalLoaderImpl();
                break;
            default:
                throw new AutomationException("Environment configuration not found");
        }
    }

    public static String getDataLocation() {
        return loader.getDataPath();
    }
}
