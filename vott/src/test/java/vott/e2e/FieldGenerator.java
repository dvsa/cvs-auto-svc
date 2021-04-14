package vott.e2e;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

public class FieldGenerator {

    private static final int A = 65;
    private static final int Z = 90;
    private static final int ZERO = 48;
    private static final int NINE = 57;

    public String randomVin() {
        int length = ThreadLocalRandom.current().nextInt(5, 21 + 1); // min VIN length = 1, max VIN length = 21

        return randomAlphanumeric(length);
    }

    public String randomSystemNumber() {
        return randomAlphanumeric(16); // system number length = 16, according to pre-existing class GenericData
    }

    public String randomVrm() {
        int length = ThreadLocalRandom.current().nextInt(3, 8 + 1);

        return randomAlphanumeric(length); // min VRM length = 3, max VRM length = 8
    }

    private String randomAlphanumeric(int length) {
        return intStream(alphaNumericCodePoints())
            .limit(length)
            .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
            .toString();
    }

    private int[] alphaNumericCodePoints() {
        int[] alphabetic = alphabeticCodePoints();
        int[] numeric = numericCodePoints();

        int[] alphaNumeric = new int[alphabetic.length + numeric.length];

        System.arraycopy(alphabetic, 0, alphaNumeric, 0, alphabetic.length);
        System.arraycopy(numeric, 0, alphaNumeric, alphabetic.length, numeric.length);

        Arrays.sort(alphaNumeric);

        return alphaNumeric;
    }

    private int[] alphabeticCodePoints() {
        return IntStream.range(A, Z + 1).toArray();
    }

    private int[] numericCodePoints() {
        return IntStream.range(ZERO, NINE + 1).toArray();
    }

    private IntStream intStream(int[] source) {
        return IntStream.generate(() -> source[ThreadLocalRandom.current().nextInt(0, source.length)]);
    }
}
