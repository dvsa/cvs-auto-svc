package util;

public class WebDriverBrowsertack {


    public static String getToken() {
        //Token was coming from Browserstack. It's changed and coming from MSAL4J library. But to avoid from any difficulties, we continue to divert the token from this class.
        // Handle other exceptions accordingly
        try {
            return TokenFromMSAL4J.createToken();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;}
}