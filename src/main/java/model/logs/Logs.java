package model.logs;

public class Logs {

    private Integer timestamp;
    private String message;
    private String type;

    public Integer getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public String getType() {
        return type;
    }

    public Logs setTimestamp(Integer timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public Logs setMessage(String message) {
        this.message = message;
        return this;
    }

    public Logs setType(String type) {
        this.type = type;
        return this;
    }

    @Override
    public String toString() {
        return "Log{" +
                "timeStamp='" + timestamp + '\'' +
                ", message='" + message + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
