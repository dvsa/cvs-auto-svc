package util;

public class InstanceToken {


    public static String getToken() {
        // Handle other exceptions accordingly
        try {
            return TokenFromMSAL4J.createToken();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;}
}