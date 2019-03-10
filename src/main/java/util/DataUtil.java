package util;

import org.apache.commons.lang3.RandomStringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

public class DataUtil {

    public static String buildDate(String date, long offset) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

        LocalDate localDate = LocalDate.parse(date, formatter).plusYears(offset);
        return localDate.toString();
    }

    public static String buildCurrentDate() {

        LocalDateTime currentDate = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        return currentDate.format(formatter);

    }

    public static String generateRandomExcludingValues(int count, String... values) {

        List<String> list = Arrays.asList(values);
        String value = RandomStringUtils.randomAlphabetic(count);
        if( list.stream().anyMatch(s-> s.equalsIgnoreCase(value))) {
            generateRandomExcludingValues(count,values);
        }

        return value;
    }


    public static void main(String[] args) {
        generateRandomExcludingValues(2,"m","d","c","l","x","v","i");
    }

}
