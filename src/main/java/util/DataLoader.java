package util;

import exceptions.AutomationException;
import org.apache.commons.exec.environment.EnvironmentUtils;

import java.util.Properties;

public class DataLoader {

    private static Properties properties;
    private static final String FILE_PATH = "conf/environment.properties";

    static {
        try {

            properties = new Properties();
            properties.load(EnvironmentUtils.class.getClassLoader().getResourceAsStream(FILE_PATH));
        } catch (Exception e) {
            e.printStackTrace();
            throw new AutomationException("Could not load environment setup");
        }
    }

    public static String getDataLocation() {
        String variable = System.getProperty("DATA");
        if (variable != null) {
            return variable;
        } else if (properties.getProperty("data.location") != null) {
            return properties.getProperty("data.location");
        }
        else {
            throw new AutomationException("Setup of variable DATA failed, please pass in command or configure variable in POM");
        }
    }

}
