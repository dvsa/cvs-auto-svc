package util;

import org.apache.commons.lang3.RandomStringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DataUtil {


    public static String buildDate(String date, long offsetYears,long offsetMounts, long offsetDays) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

        LocalDate localDate = LocalDate.parse(date, formatter).plusYears(offsetYears).plusMonths(offsetMounts).plusDays(offsetDays);
        return localDate.toString();
    }

    public static String buildDate(String date, long offsetYears, long offsetDays) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

        LocalDate localDate = LocalDate.parse(date, formatter).plusYears(offsetYears).plusDays(offsetDays);
        return localDate.toString();
    }

    public static String buildDate(String date, long offsetYears) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

        LocalDate localDate = LocalDate.parse(date, formatter).plusYears(offsetYears);
        return localDate.toString();
    }

    public static String buildCurrentDateTime() {

        LocalDateTime currentDate = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        return currentDate.format(formatter);

    }


    public static String buildCurrentDateTime(long days) {

        LocalDateTime currentDate = LocalDateTime.now().plusDays(days);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        return currentDate.format(formatter);

    }

    public static String generateRandomExcludingValues(int count, String... values) {

        String value = RandomStringUtils.randomAlphabetic(count);
        for (String currentListValue : values) {
            if (value.equalsIgnoreCase(currentListValue)) {
                value = generateRandomExcludingValues(count,values);
            }
        }

        return value;
    }


}
