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

    public static boolean isWrongAtuh() {
        return wrongAuth.get();
    }

    public static boolean isMissingtuh() {
        return missingAuth.get();
    }

    public static void setWrongAtuh() {
        wrongAuth.set(true);
    }

    public static void setRightAtuh() {
        wrongAuth.set(false);
        missingAuth.set(false);
    }

    public static void setMissingAtuh() {
        missingAuth.set(true);
    }


}
