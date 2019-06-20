package data;

import model.logs.Logs;


public class LogsData {
    public static Logs buildLogsData() {
        Logs logs = new Logs().setMessage("string").setTimestamp(5).setType("string");
        return logs;
    }
}
