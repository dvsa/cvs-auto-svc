package util;

public class TestNumberUtils {
    public enum Alphabet {
        A("1"),
        B("2"),
        C("3"),
        D("4"),
        E("5"),
        F("6"),
        G("7"),
        H("8"),
        I("9"),
        J("10"),
        K("11"),
        L("12"),
        M("13"),
        N("14"),
        O("15"),
        P("16"),
        Q("17"),
        R("18"),
        S("19"),
        T("20"),
        U("21"),
        V("22"),
        W("23"),
        X("24"),
        Y("25"),
        Z("26");

        private String value;

        Alphabet(String _value) {
            this.value = _value;
        }

        public String getValue() {
            return this.value;
        }
    }

    public static boolean isTestNumberChecksumValid(String testNumber) {
        Character firstChar = testNumber.charAt(0);
        Character secondChar = testNumber.charAt(1);
        Character thirdChar = testNumber.charAt(2);
        Character fourthChar = testNumber.charAt(3);
        Character fifthChar = testNumber.charAt(4);
        Character sixthChar = testNumber.charAt(5);
        Character seventhChar = testNumber.charAt(6);
        Character eighthChar = testNumber.charAt(7);
        Character ninthChar = testNumber.charAt(8);

        Integer firstDigit = Integer.parseInt(Alphabet.valueOf(firstChar.toString()).getValue());
        Integer secondDigit = Integer.parseInt(secondChar.toString());
        Integer thirdDigit = Integer.parseInt(thirdChar.toString()) * 3;
        Integer fourthDigit = Integer.parseInt(Alphabet.valueOf(fourthChar.toString()).getValue());
        Integer fifthDigit = Integer.parseInt(fifthChar.toString());
        Integer sixthDigit = Integer.parseInt(sixthChar.toString()) * 3;
        Integer seventhDigit = Integer.parseInt(seventhChar.toString());

        Integer sum = firstDigit + secondDigit + thirdDigit + fourthDigit + fifthDigit + sixthDigit + seventhDigit;
        String actualLastTwoDigits = eighthChar.toString() + ninthChar.toString();
        String expectedLastTwoDigits;

        if (sum >= 100) {
            expectedLastTwoDigits = String.valueOf(sum).substring(0, 2);
        } else if (sum < 10) {
            expectedLastTwoDigits = "0" + sum;
        } else expectedLastTwoDigits = String.valueOf(sum);

        if (actualLastTwoDigits.equals(expectedLastTwoDigits)) {
            return true;
        } else {
            return false;
        }
    }
}
