package util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DataUtil {

    public static String buildDate(String date, long offset) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

        LocalDate localDate = LocalDate.parse(date, formatter).plusYears(offset);
        return localDate.toString();
    }
}
