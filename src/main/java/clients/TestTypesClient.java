package clients;

import clients.model.TestTypeQueryParam;
import clients.model.TestTypes;
import data.GenericData;
import exceptions.AutomationException;
import io.restassured.filter.Filter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import util.BasePathFilter;
import util.NoDataPathFilter;

import java.util.Iterator;
import java.util.Random;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;
import static util.WriterReader.saveUtils;

public class TestTypesClient {

    public Response getTestTypesWithData() {
        return getTestTypes(new BasePathFilter());
    }

    public Response getTestTypesWithNoData() {
        return getTestTypes(new NoDataPathFilter());
    }

    private Response getTestTypes(Filter filter) {
        Response response = callGetTestTypes(filter);

        if (response.getStatusCode() == 401 || response.getStatusCode() == 403) {
            saveUtils();
            response = callGetTestTypes(filter);
        }

        return response;

    }

    public JSONObject getRestrictions(String testCode) {
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
                                    Random r = new Random();
                                    int randomNumber = r.nextInt(array.length());
                                    actualRestrictions.put("vehicleSubclass", array.get(randomNumber));
                                }
                                else {
                                    actualRestrictions.put("vehicleSubclass", jsonObject.get(key));
                                }
                                break;
                            case "forVehicleWheels":
                                if (!(jsonObject.get(key).getClass().getName().contentEquals("java.lang.String"))) {
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
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        return actualRestrictions;
    }

    public String getTestTypeIdFromTestCode(String testCode) {
        String testTypeId = null;
        for (TestTypes testType : TestTypes.values()) {
            if (testType.getTestCode().contentEquals(testCode.toLowerCase())) {
                testTypeId = testType.getId();
                break;
            }
        }
        return testTypeId;
    }

    public Response getTestTypes(String id, TestTypeQueryParam testTypeQueryParam) {
        Response response = callTestTypes(id, testTypeQueryParam);

        if (response.getStatusCode() == 401 || response.getStatusCode() == 403) {
            saveUtils();
            response = callTestTypes(id, testTypeQueryParam);
        }

        return response;

    }

    private Response callTestTypes(String id, TestTypeQueryParam testTypeQueryParam) {
        RequestSpecification responseSpec = given().filters(new BasePathFilter())
                .contentType(ContentType.JSON)
                .pathParam("id", id);


        if (testTypeQueryParam.getFields() != null) {
            String testTypeFields = testTypeQueryParam.getFields().stream()
                        .map( n -> n.getField() )
                        .collect( Collectors.joining( "," ) );

            responseSpec.queryParam("fields", testTypeFields);
        }

        if (testTypeQueryParam.getVehicleType() != null) {
            responseSpec.queryParam("vehicleType", testTypeQueryParam.getVehicleType());
        }

        if (testTypeQueryParam.getVehicleSize() != null) {
            responseSpec.queryParam("vehicleSize", testTypeQueryParam.getVehicleSize());
        }

        if (testTypeQueryParam.getVehicleConfiguration() != null) {
            responseSpec.queryParam("vehicleConfiguration", testTypeQueryParam.getVehicleConfiguration());
        }

        if (testTypeQueryParam.getVehicleAxles() != null) {
            responseSpec.queryParam("vehicleAxles", testTypeQueryParam.getVehicleAxles());
        }

        if (testTypeQueryParam.getVehicleClass() != null) {
            responseSpec.queryParam("vehicleClass", testTypeQueryParam.getVehicleClass());
        }

        if (testTypeQueryParam.getVehicleSubClass() != null) {
            responseSpec.queryParam("vehicleSubclass", testTypeQueryParam.getVehicleSubClass());
        }

        if (testTypeQueryParam.getVehicleWheels() != null) {
            responseSpec.queryParam("vehicleWheels", testTypeQueryParam.getVehicleWheels());
        }

        if (testTypeQueryParam.getEuVehicleCategory() != null) {
            responseSpec.queryParam("euVehicleCategory", testTypeQueryParam.getEuVehicleCategory());
        }

        Response response = responseSpec
//                .log().all()
                .log().method().log().uri().log().body()
                .get("/test-types/{id}");

        return response;

    }

    private Response callGetTestTypes(Filter filter) {
        Response response = given().filters(filter)
                .contentType(ContentType.JSON)
//                 .log().all()
            .log().method().log().uri().log().body()
                .get("/test-types");

        return response;

    }

    public String getActualTestCode(String testCode) {
        if (testCode.lastIndexOf("_") != -1) {
            return testCode.split("_")[0];
        }
        else {
            return testCode;
        }
    }

    public String getTestTypeClassificationByTestCode(String testCode) {
        String testTypeId = getTestTypeIdFromTestCode(testCode);

        return GenericData.readJsonValueFromFile("test-type.json", "$..[?(@.id =='" +
                testTypeId + "').testTypeClassification]");
    }
}
