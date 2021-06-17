package util;

import exceptions.AutomationException;
import org.apache.commons.exec.environment.EnvironmentUtils;

import java.util.Properties;

public class LocalLoaderImpl implements Loader {

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

    @Override
    public String getBasePathUrl() {
        return properties.getProperty("base.path.url");
    }

    @Override
    public String getApiKeys() {
        return properties.getProperty("api.keys");
    }

    @Override
    public String getNoDataBasePathUrl() {
        return properties.getProperty("no.data.base.path.url"); }

    @Override
    public String getMicrosoftonlineUrl() {
        return properties.getProperty("microsoftonline.url"); }

    @Override
    public String getMicrosoftonlineUserName() {
        return properties.getProperty("microsoftonline.username"); }

    @Override
    public String getMicrosoftonlinePass() {
        return properties.getProperty("microsoftonline.pass"); }

    @Override
    public String getEmailUserName() {
        return properties.getProperty("email.username"); }

    @Override
    public String getEmailPass() {
        return properties.getProperty("email.pass"); }

    @Override
    public String getUsername() {
        return properties.getProperty("browserstack.username");
    }

    @Override
    public String getPassword() {
        return properties.getProperty("browserstack.password");
    }

    @Override
    public String getDataPath() {
        return properties.getProperty("data.location");
    }

    @Override
    public String getS3Bucket() {
        System.out.println("Using local settings....");
        return properties.getProperty("s3.bucket");
    }

    @Override
    public String getBranchName() {
        return properties.getProperty("branch.name");
    }

    @Override
    public  String getAppTokenUrl() {
        return properties.getProperty("app.token.url");
    }

    @Override
    public  String getAppClientId() {
        return properties.getProperty("app.client.id");
    }

    @Override
    public  String getAppClientSecret() {
        return properties.getProperty("app.client.secret");
    }

    @Override
    public  String getAppScope() {
        return properties.getProperty("app.scope");
    }


}
