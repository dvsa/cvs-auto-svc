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

    public static String computeTestNumber(String CVSID, String certLetter, String sequenceNumber) {
        if(sequenceNumber.length()<3) sequenceNumber = sequenceNumber.length()==1? "00"+sequenceNumber : "0"+sequenceNumber;
        Character firstChar = CVSID.charAt(0);
        Character secondChar = CVSID.charAt(1);
        Character thirdChar = CVSID.charAt(2);
        Character fourthChar = certLetter.charAt(0);
        Character fifthChar = sequenceNumber.charAt(0);
        Character sixthChar = sequenceNumber.charAt(1);
        Character seventhChar = sequenceNumber.charAt(2);

        Integer firstDigit = Integer.parseInt(Alphabet.valueOf(firstChar.toString()).getValue());
        Integer secondDigit = Integer.parseInt(secondChar.toString());
        Integer thirdDigit = Integer.parseInt(thirdChar.toString()) * 3;
        Integer fourthDigit = Integer.parseInt(Alphabet.valueOf(fourthChar.toString()).getValue());
        Integer fifthDigit = Integer.parseInt(fifthChar.toString());
        Integer sixthDigit = Integer.parseInt(sixthChar.toString()) * 3;
        Integer seventhDigit = Integer.parseInt(seventhChar.toString());

        Integer sum = firstDigit + secondDigit + thirdDigit + fourthDigit + fifthDigit + sixthDigit + seventhDigit;
        String lastTwoDigits;

        if (sum >= 100) {
            lastTwoDigits = String.valueOf(sum).substring(0, 2);
        } else if (sum < 10) {
            lastTwoDigits = "0" + sum;
        } else lastTwoDigits = String.valueOf(sum);

        return CVSID + certLetter + sequenceNumber + lastTwoDigits;
    }

    public static String computeNextTestNumber(String CVSID, String certLetter, String sequenceNumber) {
        if (!certLetter.equals("Z") && !sequenceNumber.equals("999")) {
            Integer seqNumber = Integer.valueOf(sequenceNumber) + 1;
            return computeTestNumber(CVSID, certLetter, seqNumber.toString());
        } else if (!certLetter.equals("Z") && sequenceNumber.equals("999")) {
            String nextCertLetter = "";
            Alphabet[] a = Alphabet.values();
            for (int i = 0; i < a.length; i++) {
                if ((a[i].toString()).equals(certLetter)) {
                    nextCertLetter = a[i + 1].toString();
                }
            }
            return computeTestNumber(CVSID, nextCertLetter, "001");
        } else if (certLetter.equals("Z") && sequenceNumber.equals("999")) {
            return computeTestNumber(CVSID, "A", "001");
        } else return null;
    }
}
