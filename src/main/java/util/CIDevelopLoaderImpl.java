package util;

import exceptions.AutomationException;

public class CIDevelopLoaderImpl implements Loader {

    @Override
    public String getBasePathUrl() {
        String variable = System.getProperty("DEVELOP_BASE_PATH");
        if (variable != null) {
            return variable;
        } else {
            throw new AutomationException("Setup of variable DEVELOP_BASE_PATH failed, please pass in command or configure variable");
        }
    }

    @Override
    public String getApiKeys() {
        String variable = System.getProperty("DEVELOP_BASE_PATH");
        if (variable != null) {
            return variable;
        } else {
            throw new AutomationException("Setup of variable DEVELOP_BASE_PATH failed, please pass in command or configure variable");
        }
    }

    @Override
    public String getNoDataBasePathUrl() {
        String variable =  System.getProperty("DEVELOP_BASE_PATH_NO_DATA");
        if (variable != null) {
            return variable;
        } else {
            throw new AutomationException("Setup of variable DEVELOP_BASE_PATH_NO_DATA failed, please pass in command or configure variable");
        }
    }

    @Override
    public String getMicrosoftonlineUrl() {
        String variable =  System.getProperty("MICROSOFT_URL");
        if (variable != null) {
            return variable;
        } else {
            throw new AutomationException("Setup of variable MICROSOFT_URL failed, please pass in command or configure variable");
        }
    }

    @Override
    public String getMicrosoftonlineUserName() {
        String variable =  System.getProperty("MICROSOFT_USERNAME");
        if (variable != null) {
            return variable;
        } else {
            throw new AutomationException("Setup of variable MICROSOFT_USERNAME failed, please pass in command or configure variable");
        }
    }

    @Override
    public String getMicrosoftonlinePass() {
        String variable =  System.getProperty("MICROSOFT_PASS");
        if (variable != null) {
            return variable;
        } else {
            throw new AutomationException("Setup of variable MICROSOFT_PASS failed, please pass in command or configure variable");
        }
    }

    @Override
    public String getEmailUserName() {
        String variable =  System.getProperty("EMAIL_USERNAME");
        if (variable != null) {
            return variable;
        } else {
            throw new AutomationException("Setup of variable EMAIL_USERNAME failed, please pass in command or configure variable");
        }
    }

    @Override
    public String getEmailPass() {
        String variable =  System.getProperty("EMAIL_PASS");
        if (variable != null) {
            return variable;
        } else {
            throw new AutomationException("Setup of variable EMAIL_PASS failed, please pass in command or configure variable");
        }
    }

    @Override
    public String getUsername() {
        String variable =  System.getenv("BROWSERSTACK_USERNAME");
        if (variable != null) {
            return variable;
        } else {
            throw new AutomationException("Setup of variable BROWSERSTACK_USERNAME failed, please pass in command or configure variable");
        }
    }

    @Override
    public String getPassword() {
        String variable =  System.getenv("BROWSERSTACK_ACCESS_KEY");
        if (variable != null) {
            return variable;
        } else {
            throw new AutomationException("Setup of variable BROWSERSTACK_ACCESS_KEY failed, please pass in command or configure variable");
        }
    }

    @Override
    public String getDataPath() {
        String variable =  System.getProperty("DATA");
        if (variable != null) {
            return variable;
        } else {
            throw new AutomationException("Setup of variable DATA, default or explicit failed, please pass in command or configure variable");
        }
    }

    @Override
    public String getS3Bucket() {
        System.out.println("Using CI settings...");
        String variable =  System.getProperty("BUCKET");
        if (variable != null) {
            return variable;
        } else {
            throw new AutomationException("Setup of variable DATA, default or explicit failed, please pass in command or configure variable");
        }
    }

    @Override
    public String getBranchName() {
        String variable =  System.getProperty("BRANCH");
        if (variable != null) {
            return variable;
        } else {
            throw new AutomationException("Setup of variable DATA, default or explicit failed, please pass in command or configure variable");
        }
    }
}
