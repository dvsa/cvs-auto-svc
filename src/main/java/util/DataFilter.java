package util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class DataFilter {

    public static List<String> getElementsToRemove(Class<?> clazz) {
        List<String> fields = new ArrayList<>();
        for (Field classField : clazz.getDeclaredFields()) {
            if (classField.isAnnotationPresent(PropertyNotPresent.class)) {
                fields.add(classField.getName());
            }
        }
        return fields;
    }
}
