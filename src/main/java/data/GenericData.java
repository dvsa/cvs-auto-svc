package data;

import clients.model.TestTypes;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import data.config.BaseData;
import data.config.DataMapper;
import exceptions.AutomationException;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;
import net.thucydides.core.annotations.Steps;
import org.apache.commons.lang3.RandomStringUtils;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;
import org.springframework.lang.NonNull;
import steps.TestResultsSteps;
import util.JsonPathAlteration;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import static net.minidev.json.parser.JSONParser.DEFAULT_PERMISSIVE_MODE;

public class GenericData {

    public static String readJsonBodyFromFile(String fileName) throws IOException {
        ClassLoader classLoader = DataMapper.class.getClassLoader();
        return DataMapper.readFromInputStream(classLoader.getResourceAsStream("src/main/resources/loader/" + fileName));
    }

    public static String readBytesFromFile(String fileName) {
        String encodedString = "";
        try {
            byte[] input_file = Files.readAllBytes(Paths.get("src/main/resources/loader/" + fileName));
            byte[] encodedBytes = Base64.getEncoder().encode(input_file);
            encodedString =  new String(encodedBytes);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return encodedString;
    }

    public static ArrayList<String> readFile(String fileName) throws IOException {
        File file = new File(Paths.get("src/main/resources/loader/" + BaseData.getDataLocation() + "/" + fileName).toString());    //creates a new file instance
        FileReader fr = new FileReader(file);   //reads the file

        BufferedReader br = new BufferedReader(fr);  //creates a buffering character input stream
        String line;
        ArrayList<String> list = new ArrayList<>();
        while((line=br.readLine())!=null)
        {
            list.add(line);
        }

        return list;
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
        String jsonResp = "";
        try {
            jsonResp = mapperObj.writeValueAsString(JsonPath.read(jsonBody, path));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        if (jsonResp.startsWith("\"") && jsonResp.endsWith("\"")) {
            return jsonResp.substring(1, jsonResp.length() - 1);
        }
        else if (jsonResp.startsWith("[\"") && jsonResp.endsWith("\"]") && (!jsonResp.contains("{")) && (!(jsonResp.contains(",")))) {
            return jsonResp.substring(2, jsonResp.length() - 2);
        }
        else {
            return jsonResp;
        }
    }

    public static String getJsonObjectInPath(String json, String path) {
        ObjectMapper mapperObj = new ObjectMapper();
        String jsonResp = null;
        try {
            jsonResp = mapperObj.writeValueAsString(JsonPath.read(json, path));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        if (jsonResp.startsWith("\"") && jsonResp.endsWith("\"")) {
            return jsonResp.substring(1, jsonResp.length()-1);
        }
        else {
            return jsonResp;
        }
    }

    public static String getJsonValueFromFile(String fileName, String path) {
        ClassLoader classLoader = DataMapper.class.getClassLoader();

        String jsonBody = null;
        try {
            jsonBody = DataMapper.readFromInputStream(classLoader.getResourceAsStream("loader/" + fileName));
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

            final boolean valueIsJson = alteration.getValue() != null &&
                    alteration.getValue().getClass().getName().equals("java.lang.String")
                    && !alteration.getValue().toString().isEmpty()
                    && ((alteration.getValue().toString().startsWith("{") && alteration.getValue().toString().endsWith("}"))
                    || (alteration.getValue().toString().contentEquals("[]")));
            Object value = valueIsJson ? readJson(alteration.getValue().toString()) : alteration.getValue();
            List<String> array = new ArrayList<>();
            if (alteration.getValue() != null && alteration.getValue().toString().startsWith("[") && alteration.getValue().toString().endsWith("]") &&
                    !(alteration.getValue().toString().contentEquals("[]"))) {
                JSONArray jsonArray = (JSONArray) value;
                assert jsonArray != null;
                for(int i = 0; i < jsonArray.length(); i++){
                    try {
                        array.add(jsonArray.get(i).toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                value = array;
            }

            switch (alteration.getAction()) {
                case "ADD_FIELD":
                    if (alteration.getField() != null) {
                        jsonContext = jsonContext.put(alteration.getPath(), alteration.getField(), value);
                        break;
                    }
                    // Intentional fall through to ADD_VALUE
                case "ADD_VALUE":
                    jsonContext = jsonContext.add(alteration.getPath(), value);
                    break;
                case "DELETE":
                    jsonContext = jsonContext.delete(alteration.getPath());
                    break;
                case "REPLACE":
                    System.out.println("replacing the value in path: " + alteration.getPath() + " with value: " + value);
                    jsonContext = jsonContext.set(alteration.getPath(), value);
                    break;
            }
        }

        return jsonContext.jsonString();
    }

    public static Object extractValueFromJsonString(String jsonString, String jsonPath) {
        return JsonPath.read(jsonString, jsonPath);
    }

    public static String extractStringValueFromJsonString(String jsonString, String jsonPath) {
        return JsonPath.read(jsonString, jsonPath);
    }

    public static int extractIntegerValueFromJsonString(String jsonString, String jsonPath) {
        return JsonPath.read(jsonString, jsonPath);
    }

    public static Boolean extractBooleanValueFromJsonString(String jsonString, String jsonPath) {
        return JsonPath.read(jsonString, jsonPath);
    }

    public static ArrayList<String> extractArrayListStringFromJsonString(String jsonString, String jsonPath) {
        return JsonPath.read(jsonString, jsonPath);
    }

    public static ArrayList<Integer> extractArrayListIntegerFromJsonString(String jsonString, String jsonPath) {
        return JsonPath.read(jsonString, jsonPath);
    }

    public static JSONArray extractJsonArrayValueFromJsonString(String jsonString, String jsonPath) {
        return JsonPath.read(jsonString, jsonPath);
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
        return RandomStringUtils.randomAlphanumeric(new Random().nextInt(13) + 3).toUpperCase() + RandomStringUtils.randomNumeric(6);
    }

    public static String generateRandomSystemNumber() {
        return RandomStringUtils.randomAlphanumeric(16).toUpperCase();
    }

    public static String generateRandomVinForVehicleType(String vehicleType) {
        return vehicleType.toUpperCase() + "_" + RandomStringUtils.randomAlphanumeric(new Random().nextInt(5) + 6).toUpperCase();
    }

    public static String generateRandomVrm() {
        return RandomStringUtils.randomAlphanumeric(new Random().nextInt(6) + 3).toUpperCase();
    }

    public static String generateRandomVinForTrailerRegistration() {
        return RandomStringUtils.randomAlphabetic(17).toUpperCase();
    }

    public static String generateRandomTrailerId() {
        return RandomStringUtils.randomAlphabetic(1).toUpperCase() + RandomStringUtils.randomNumeric(6);
    }

    public static String generateRandomVrmForEmailValidations() {
        return "AUT" + RandomStringUtils.randomAlphanumeric(5).toUpperCase();
    }

    public static String getJsonStringFromJsonPath(String fileName, String path) {
        ClassLoader classLoader = DataMapper.class.getClassLoader();

        String jsonBody = null;
        try {
            jsonBody = DataMapper.readFromInputStream(classLoader.getResourceAsStream("loader/" + fileName));
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

    public static String getValueFromJsonPath(String jsonBody, String path) {
        ObjectMapper mapperObj = new ObjectMapper();
        String jsonResp = null;
        try {
            jsonResp = mapperObj.writeValueAsString(JsonPath.read(jsonBody, path));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        if (jsonResp.startsWith("\"") && jsonResp.endsWith("\"")) {
            return jsonResp.substring(1, jsonResp.length()-1);
        }
        else {
            return jsonResp;
        }
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

    public static String getPartialVinFromVin(String randomVin) {
        if (randomVin.length() >= 6) {
            return randomVin.substring(randomVin.length()-6);
        }
        else {
            return randomVin;
        }
    }

    public static ArrayList<String> getJsonStringListFromHashMapArray(ArrayList<HashMap<String, String>> arrayList) {
        ArrayList<String> jsonResp = new ArrayList<>();
        for (HashMap<String, String> hashMap : arrayList) {
            ObjectMapper mapperObj = new ObjectMapper();
            try {
                jsonResp.add(mapperObj.writeValueAsString(hashMap));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        return jsonResp;
    }

    public static String getTestTypeIdFromTestCode(String testCode) {
        String testTypeId = null;
        for (TestTypes testType : TestTypes.values()) {
            if (testType.getTestCode().contentEquals(testCode.toLowerCase())) {
                testTypeId = testType.getId();
                break;
            }
        }
        return testTypeId;
    }

    public static String getTestTypeClassificationByTestCode(String testCode) {
        String testTypeId = getTestTypeIdFromTestCode(testCode);

        return GenericData.readJsonValueFromFile("test-type.json", "$..[?(@.id =='" +
                testTypeId + "')].testTypeClassification");
    }

    public static JSONObject getRestrictions(String testCode) {
        String testTypeProperties;
        if (testCode.lastIndexOf("_") != -1) {
            testTypeProperties = GenericData.readJsonValueFromFile("test-type.json", "$..[?(@.defaultTestCode == '" +
                    testCode.split("_")[0] + "' && @.forVehicleType == '" + testCode.split("_")[1] + "')]");
        }
        else {
            if (testCode.contentEquals("lcp")) {
                testTypeProperties = GenericData.readJsonValueFromFile("test-type.json", "$..[?(@.linkedTestCode == '" +
                        testCode + "')]");
            }
            else {
                testTypeProperties = GenericData.readJsonValueFromFile("test-type.json", "$..[?(@.defaultTestCode == '" +
                        testCode + "')]");
            }
        }

        String restrictions = GenericData.getJsonObjectInPath(testTypeProperties, "$[0]");
        JSONObject jsonObject;
        try {
            jsonObject = new JSONObject(restrictions);
        } catch (JSONException err) {
            throw new AutomationException("'" + restrictions + "' is not a valid JSON string");
        }
        Iterator keys = jsonObject.keys();
        JSONObject actualRestrictions = new JSONObject();
        while(keys.hasNext()) {
            String key = keys.next().toString();
            if (!(key.contentEquals("defaultTestCode")) && !(key.contentEquals("linkedTestCode"))) {
                try {
                    if (!jsonObject.get(key).toString().contentEquals("null")) {
                        switch (key) {
                            case "forVehicleType":
                                if (!(jsonObject.get(key).getClass().getName().contentEquals("java.lang.String"))) {
                                    JSONArray array = (JSONArray) jsonObject.get(key);
                                    Random r = new Random();
                                    int randomNumber = r.nextInt(array.length());
                                    actualRestrictions.put("vehicleType", array.get(randomNumber));
                                }
                                else {
                                    actualRestrictions.put("vehicleType", jsonObject.get(key));
                                }
                                break;
                            case "forVehicleSize":
                                if (!(jsonObject.get(key).getClass().getName().contentEquals("java.lang.String"))) {
                                    JSONArray array = (JSONArray) jsonObject.get(key);
                                    Random r = new Random();
                                    int randomNumber = r.nextInt(array.length());
                                    actualRestrictions.put("vehicleSize", array.get(randomNumber));
                                }
                                else {
                                    actualRestrictions.put("vehicleSize", jsonObject.get(key));
                                }
                                break;
                            case "forVehicleConfiguration":
                                if (!(jsonObject.get(key).getClass().getName().contentEquals("java.lang.String"))) {
                                    JSONArray array = (JSONArray) jsonObject.get(key);
                                    Random r = new Random();
                                    int randomNumber = r.nextInt(array.length());
                                    actualRestrictions.put("vehicleConfiguration", array.get(randomNumber));
                                }
                                else {
                                    actualRestrictions.put("vehicleConfiguration", jsonObject.get(key));
                                }
                                break;
                            case "forVehicleAxles":
                                if (!(jsonObject.get(key).getClass().getName().contentEquals("java.lang.Integer"))) {
                                    JSONArray array = (JSONArray) jsonObject.get(key);
                                    Random r = new Random();
                                    int randomNumber = r.nextInt(array.length());
                                    actualRestrictions.put("noOfAxles", array.get(randomNumber));
                                }
                                else {
                                    actualRestrictions.put("noOfAxles", jsonObject.get(key));
                                }
                                break;
                            case "forEuVehicleCategory":
                                if (!(jsonObject.get(key).getClass().getName().contentEquals("java.lang.String"))) {
                                    JSONArray array = (JSONArray) jsonObject.get(key);
                                    Random r = new Random();
                                    int randomNumber = r.nextInt(array.length());
                                    actualRestrictions.put("euVehicleCategory", array.get(randomNumber));
                                }
                                else {
                                    actualRestrictions.put("euVehicleCategory", jsonObject.get(key));
                                }
                                break;
                            case "forVehicleClass":
                                if (!(jsonObject.get(key).getClass().getName().contentEquals("java.lang.String"))) {
                                    JSONArray array = (JSONArray) jsonObject.get(key);
                                    Random r = new Random();
                                    int randomNumber = r.nextInt(array.length());
                                    actualRestrictions.put("vehicleClass", array.get(randomNumber));
                                }
                                else {
                                    actualRestrictions.put("vehicleClass", jsonObject.get(key));
                                }
                                break;
                            case "forVehicleSubclass":
                                if (!(jsonObject.get(key).getClass().getName().contentEquals("java.lang.String"))) {
                                    JSONArray array = (JSONArray) jsonObject.get(key);
                                    actualRestrictions.put("vehicleSubclass", array);
                                }
                                else {
                                    actualRestrictions.put("vehicleSubclass", jsonObject.get(key));
                                }
                                break;
                            case "forVehicleWheels":
                                if (!(jsonObject.get(key).getClass().getName().contentEquals("java.lang.Integer"))) {
                                    JSONArray array = (JSONArray) jsonObject.get(key);
                                    Random r = new Random();
                                    int randomNumber = r.nextInt(array.length());
                                    actualRestrictions.put("numberOfWheelsDriven", array.get(randomNumber));
                                }
                                else {
                                    actualRestrictions.put("numberOfWheelsDriven", jsonObject.get(key));
                                }
                                break;
                        }
                    }
                    else {
                        if ((key.contentEquals("forVehicleConfiguration")) &&
                                (jsonObject.get("forVehicleType").getClass().getName().contentEquals("java.lang.String")) &&
                                (jsonObject.get("forVehicleType").toString().contentEquals("psv"))) {
                            String[] configurations = {"rigid", "articulated"};
                            Random r = new Random();
                            int randomNumber = r.nextInt(configurations.length);
                            actualRestrictions.put("vehicleConfiguration", configurations[randomNumber]);
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        return actualRestrictions;
    }

    public static String updateJson(TestResultsSteps testResultsSteps, String jsonFileName, String $) {
        // read post request body from file
        String body = GenericData.readJsonValueFromFile(jsonFileName,"$");

        //generate datetime
        DateTime currentTimestamp = DateTime.now().withZone(DateTimeZone.UTC);
        DateTime testStartTimestamp = currentTimestamp.minusDays(1).minusHours(2);
        DateTime endTimestamp = currentTimestamp.minusDays(1).minusHours(1);


        String startTime = testStartTimestamp.toInstant().toString();
        String endTime = endTimestamp.toInstant().toString();

        // create alterations
        JsonPathAlteration alterationStartTime = new JsonPathAlteration("$.testStartTimestamp",startTime ,"","REPLACE");
        JsonPathAlteration alterationEndTime = new JsonPathAlteration("$.testEndTimestamp", endTime,"","REPLACE");
        JsonPathAlteration alterationTestStartTime = new JsonPathAlteration("$.testTypes[0].testTypeStartTimestamp", startTime,"","REPLACE");
        JsonPathAlteration alterationTestEndTime = new JsonPathAlteration("$.testTypes[0].testTypeEndTimestamp", endTime,"","REPLACE");
        JsonPathAlteration alterationTestStartTime2 = new JsonPathAlteration("$.testTypes[1].testTypeStartTimestamp", endTime,"","REPLACE");
        JsonPathAlteration alterationTestEndTime2 = new JsonPathAlteration("$.testTypes[1].testTypeEndTimestamp", endTime,"","REPLACE");


        // initialize the alterations list with both declared alteration
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(
                alterationStartTime,
                alterationEndTime,
                alterationTestStartTime,
                alterationTestEndTime,
                alterationTestStartTime2,
                alterationTestEndTime2));

        return testResultsSteps.applyJsonAlterations(body, alterations);

    }
}
