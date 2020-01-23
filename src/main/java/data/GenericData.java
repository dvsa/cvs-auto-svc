package data;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import data.config.BaseData;
import data.config.DataMapper;
import net.minidev.json.JSONArray;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;
import org.springframework.lang.NonNull;
import util.JsonPathAlteration;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import static net.minidev.json.parser.JSONParser.DEFAULT_PERMISSIVE_MODE;

public class GenericData {

    public static String readJsonBodyFromFile(String fileName) throws IOException {
        ClassLoader classLoader = DataMapper.class.getClassLoader();
        return DataMapper.readFromInputStream(classLoader.getResourceAsStream("loader/" + BaseData.getDataLocation() + "/" + fileName));
    }

    public static String readBytesFromFile(String fileName) {
        String encodedString = "";
        try {
            byte[] input_file = Files.readAllBytes(Paths.get("src/main/resources/loader/" + BaseData.getDataLocation() + "/" + fileName));
            byte[] encodedBytes = Base64.getEncoder().encode(input_file);
            encodedString =  new String(encodedBytes);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return encodedString;
    }

    public static String readJsonValueFromFile(String fileName, String path) {
        ClassLoader classLoader = DataMapper.class.getClassLoader();

        String jsonBody = null;
        try {
            jsonBody = DataMapper.readFromInputStream(classLoader.getResourceAsStream("loader/" + BaseData.getDataLocation() + "/" + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        ObjectMapper mapperObj = new ObjectMapper();
        String jsonResp = null;
        try {
            jsonResp = mapperObj.writeValueAsString(JsonPath.read(jsonBody, path));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonResp;
    }

    public static String getJsonValueFromFile(String fileName, String path) {
        ClassLoader classLoader = DataMapper.class.getClassLoader();

        String jsonBody = null;
        try {
            jsonBody = DataMapper.readFromInputStream(classLoader.getResourceAsStream("loader/" + BaseData.getDataLocation() + "/" + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return JsonPath.read(jsonBody, path).toString();
    }

    public static String applyJsonAlterations(
            @NonNull final String json, @NonNull final List<JsonPathAlteration> alterations) {
        DocumentContext jsonContext = JsonPath.parse(json);
        for (final JsonPathAlteration alteration : alterations) {
            Objects.requireNonNull(alteration.getPath(), "The 'path' is required for any alteration");

            final boolean valueIsJson = alteration.getValue() != null && !alteration.getValue().isEmpty()
                    && ((alteration.getValue().startsWith("{") && alteration.getValue().endsWith("}"))
                    || (alteration.getValue().startsWith("[") && alteration.getValue().endsWith("]")));
            final Object value = valueIsJson ? readJson(alteration.getValue()) : alteration.getValue();

            switch (alteration.getAction()) {
                case "ADD_FIELD":
                    if (alteration.getField() != null) {
                        Objects.requireNonNull(alteration.getValue(), "The 'value' is required for this alteration");

                        jsonContext = jsonContext.put(alteration.getPath(), alteration.getField(), value);
                        break;
                    }
                    // Intentional fall through to ADD_VALUE
                case "ADD_VALUE":
                    Objects.requireNonNull(alteration.getValue(), "The 'value' is required for this alteration");

                    jsonContext = jsonContext.add(alteration.getPath(), value);
                    break;
                case "DELETE":
                    jsonContext = jsonContext.delete(alteration.getPath());
                    break;
                case "REPLACE":
                    Objects.requireNonNull(alteration.getValue(), "The 'value' is required for this alteration");
                    if (value == "true") {
                        jsonContext.set(alteration.getPath(), true);
                    }
                    else if (value == "false") {
                        jsonContext.set(alteration.getPath(), false);
                    }
                    else {
                        jsonContext = jsonContext.set(alteration.getPath(), value);
                    }
                    break;
            }
        }

        return jsonContext.jsonString();
    }

    private static Object readJson(final String json) {
        try {
            final JSONParser parser = new JSONParser(DEFAULT_PERMISSIVE_MODE);

            return parser.parse(json);
        } catch (final ParseException exc) {
            throw new RuntimeException(exc);
        }
    }

    public static String generateRandomVin() {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder builder = new StringBuilder();
        Random rnd = new Random();
        Random length = new Random();
        int vinLength = length.nextInt(21 - 9 + 1) + 9;
        while (builder.length() < vinLength) { // length of the random string.
            int index = (int) (rnd.nextFloat() * chars.length());
            builder.append(chars.charAt(index));
        }
        return builder.toString();
    }

    public static String generateRandomVrm() {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder builder = new StringBuilder();
        Random rnd = new Random();
        Random length = new Random();
        int vinLength = length.nextInt(7 - 6 + 1) + 6;
        while (builder.length() < vinLength) { // length of the random string.
            int index = (int) (rnd.nextFloat() * chars.length());
            builder.append(chars.charAt(index));
        }
        return builder.toString();
    }

    public static String getJsonStringFromJsonPath(String fileName, String path) {
        ClassLoader classLoader = DataMapper.class.getClassLoader();

        String jsonBody = null;
        try {
            jsonBody = DataMapper.readFromInputStream(classLoader.getResourceAsStream("loader/" + BaseData.getDataLocation() + "/" + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        ObjectMapper mapperObj = new ObjectMapper();
        String jsonResp = null;
        try {
            jsonResp = mapperObj.writeValueAsString(JsonPath.read(jsonBody, path));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonResp;
    }

    public static String getJsonStringFromHashMap(HashMap<String, String> hashMap) {
        ObjectMapper mapperObj = new ObjectMapper();
        String jsonResp = null;
        try {
            jsonResp = mapperObj.writeValueAsString(hashMap);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonResp;
    }

    public static String extractStringValueFromJsonString(String jsonString, String jsonPath) {
        return JsonPath.read(jsonString, jsonPath).toString();
    }

    public static JSONArray extractJsonArrayValueFromJsonString(String jsonString, String jsonPath) {
        return JsonPath.read(jsonString, jsonPath);
    }
}
