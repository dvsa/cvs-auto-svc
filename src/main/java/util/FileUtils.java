package util;

import java.io.Serializable;

public class FileUtils implements Serializable {
    private String token;

    public FileUtils(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
