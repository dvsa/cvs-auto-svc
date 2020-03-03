package util;

import java.util.concurrent.atomic.AtomicBoolean;

public class TypeLoader {

    private static AtomicBoolean wrongAuth = new AtomicBoolean();
    private static AtomicBoolean missingAuth = new AtomicBoolean();

    public static EnvironmentType getType() {
         return EnvironmentType.get(getProfileName());
    }

    private static String getProfileName() {
        return System.getProperty("environment");
    }

    public static boolean isWrongAuth() {
        return wrongAuth.get();
    }

    public static boolean isMissingAuth() {
        return missingAuth.get();
    }

    public static void setWrongAuth() {
        wrongAuth.set(true);
    }

    public static void setRightAuth() {
        wrongAuth.set(false);
        missingAuth.set(false);
    }

    public static void setMissingAuth() {
        missingAuth.set(true);
    }


}
