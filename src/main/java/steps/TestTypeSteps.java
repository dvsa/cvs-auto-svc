package steps;

import clients.TestTypesClient;
import clients.model.TestTypeField;
import clients.model.TestTypeQueryParam;
import exceptions.AutomationException;
import io.restassured.response.Response;
import model.TestType;
import model.testtypeid.TestTypeById;
import net.thucydides.core.annotations.Step;
import org.json.JSONObject;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import static java.util.stream.Collectors.toList;
import static org.hamcrest.Matchers.*;

public class TestTypeSteps {

    TestTypesClient testTypesClient = new TestTypesClient();
    Response response;

    @Step
    public void getTestTypesWithNoData() {
        response = testTypesClient.getTestTypesWithNoData();
    }

    @Step
    public Response getTestTypesWithData() {
        response = testTypesClient.getTestTypesWithData();
        return response;
    }

    @Step
    public JSONObject getRestrictionsByTestCode(String testCode) {
        return testTypesClient.getRestrictions(testCode);
    }

    @Step
    public String getTestTypeClassificationByTestCode(String testCode) {
        return testTypesClient.getTestTypeClassificationByTestCode(testCode);
    }

    @Step
    public void getTestTypesById(String id, TestTypeQueryParam testTypeQueryParam) {
        response = testTypesClient.getTestTypes(id, testTypeQueryParam);
    }

    @Step
    public void statusCodeShouldBe(int statusCode) {
        //response.then().log().status().log().body().statusCode(statusCode);
        response.then().log().all().statusCode(statusCode);
    }

    @Step
    public void valueForFieldInPathShouldBe(String path, Object expectedValue) {
        response.then().body(path, equalTo(expectedValue));
    }

    @Step
    public void valueForFieldInPathShouldBeNull(String path) {
        response.then().body(path, isEmptyOrNullString());
    }

    @Step
    public void fieldInPathShouldExist(String parentElementPath, String key) {
        response.then().body(parentElementPath,hasKey(key));
    }

    @Step
    public void validateData(String stringData) {
        response.then().body(is("\"" + stringData + "\""));
    }

    @Step
    public void validateRawData(String stringData) {
        response.then().body(is(stringData));
    }
    @Step
    public void validateWrongSetOfFields(String stringData) {

        response.then().body(containsString(stringData));

        for (TestTypeField testTypeField : TestTypeField.values()) {
            response.then().body(containsString(testTypeField.getCurrentField()));
        }
    }

    @Step
    public void validateData(TestType testType) {

        String index;

        if (response.jsonPath().get("find { it.name == '" + testType.getName() + "'}.id") != null) {
            index = response.jsonPath().get("findIndexOf { it.name == '" + testType.getName() + "'}").toString();
        } else {
            throw new AutomationException("Test Type " + testType.getName() + " was not found ! Please check data.");
        }

        validateLevel1Data(testType, index);
        validateLevel2Data(testType, index);
        validateLevel3Data(testType, index);

    }

    @Step
    public void validateData(TestTypeById testTypeById, List<TestTypeField> testTypeFields) {

        int expectedResponseSize = 1;


        response.then().body("id", equalTo(testTypeById.getId()));


        if (testTypeFields != null) {
            expectedResponseSize = expectedResponseSize + testTypeFields.size();
            for (TestTypeField testTypeField : testTypeFields) {
                switch (testTypeField) {
                    case DEFAULT_TEST_CODE:
                        response.then().body(testTypeField.getField(), equalTo(testTypeById.getDefaultTestCode()));
                        break;
                    case TEST_TYPE_CLASSIFICATION:
                        response.then().body(testTypeField.getField(), equalTo(testTypeById.getTestTypeClassification()));
                        break;
                    case LINKED_TEST_CODE:
                        response.then().body(testTypeField.getField(), equalTo(testTypeById.getLinkedTestCode()));
                        break;
                    case INVALID:
                        response.then().body("$", not(hasKey(testTypeField.getField())));
                        expectedResponseSize--;
                        break;
                    default:
                        throw new AutomationException("Field is" + testTypeField.getField() + " not present in automation please update step");

                }
            }
        }

        response.then().body("size()", equalTo(expectedResponseSize));

    }

    @Step
    public void validateTestTypeDataNotExisting() {

        for (TestTypeField testTypeField : TestTypeField.class.getEnumConstants()) {
            if (!"invalid" .equalsIgnoreCase(testTypeField.name())) {
                response.then().body("$", not(hasKey(testTypeField.getField())));
            }
        }

    }

    @Step
    private void validateLevel1Data(TestType testType, String index) {

        response.then().body("[" + index + "].size()", is(TestType.class.getDeclaredFields().length - 1));

        response.then().body("id", hasItem(equalTo(testType.getId())));
        response.then().body("name", hasItem(equalTo(testType.getName())));
        response.then().body("forVehicleType", hasItem(contains(testType.getForVehicleType().toArray())));
        response.then().body("forVehicleConfiguration", hasItem(contains(testType.getForVehicleConfiguration().toArray())));
        response.then().body("forVehicleSize", hasItem(contains(testType.getForVehicleSize().toArray())));
    }

    @Step
    private void validateLevel2Data(TestType testType, String index) {

        response.then().body("[" + index + "].nextTestTypesOrCategories[0].size()", is(TestType.class.getDeclaredFields().length - 1));

        List<String> ids = testType.getNextTestTypesOrCategories().stream().map(TestType::getId).collect(toList());
        List<String> names = testType.getNextTestTypesOrCategories().stream().map(TestType::getName).collect(toList());
        List<List<String>> vehicleConfigurations = testType.getNextTestTypesOrCategories().stream().map(TestType::getForVehicleConfiguration).collect(toList());
        List<List<String>> vehicleSize = testType.getNextTestTypesOrCategories().stream().map(TestType::getForVehicleSize).collect(toList());
        List<List<String>> vehicleType = testType.getNextTestTypesOrCategories().stream().map(TestType::getForVehicleType).collect(toList());

        response.then().body("nextTestTypesOrCategories.id", hasItem(contains(ids.toArray())));
        response.then().body("nextTestTypesOrCategories.name", hasItem(contains(names.toArray())));
        response.then().body("nextTestTypesOrCategories.forVehicleType", hasItem(contains(vehicleType.toArray())));
        response.then().body("nextTestTypesOrCategories.forVehicleConfiguration", hasItem(contains(vehicleConfigurations.toArray())));
        response.then().body("nextTestTypesOrCategories.forVehicleSize", hasItem(contains(vehicleSize.toArray())));


    }


    @Step
    private void validateLevel3Data(TestType testType, String index) {

        List<List<String>> namesList = testType.getNextTestTypesOrCategories().stream().map(s -> s.getNextTestTypesOrCategories().stream().map(TestType::getName).collect(toList())).collect(toList());
        namesList.forEach(names -> response.then().body("nextTestTypesOrCategories.nextTestTypesOrCategories.name", hasItem(hasItem(contains(names.toArray())))));

        List<List<String>> testTypeNameList = testType.getNextTestTypesOrCategories().stream().map(s -> s.getNextTestTypesOrCategories().stream().map(TestType::getTestTypeName).collect(toList())).collect(toList());
        namesList.forEach(names -> response.then().body("nextTestTypesOrCategories.nextTestTypesOrCategories.testTypeName", hasItem(contains(testTypeNameList.toArray()))));

        List<List<String>> idsList = testType.getNextTestTypesOrCategories().stream().map(s -> s.getNextTestTypesOrCategories().stream().map(TestType::getId).collect(toList())).collect(toList());
        idsList.forEach(ids -> response.then().body("nextTestTypesOrCategories.nextTestTypesOrCategories.id", hasItem(hasItem(contains(ids.toArray())))));

        List<List<List<String>>> forVehicleTypesListOfList = testType.getNextTestTypesOrCategories().stream().map(s -> s.getNextTestTypesOrCategories().stream().map(TestType::getForVehicleType).collect(toList())).collect(toList());
        forVehicleTypesListOfList.forEach(forVehicleTypesList -> response.then().body("nextTestTypesOrCategories.nextTestTypesOrCategories.forVehicleType", hasItem(hasItem(contains(forVehicleTypesList.toArray())))));


        List<List<List<String>>> forVehicleConfigurationListOfList = testType.getNextTestTypesOrCategories().stream().map(s -> s.getNextTestTypesOrCategories().stream().map(TestType::getForVehicleConfiguration).collect(toList())).collect(toList());
        forVehicleConfigurationListOfList.forEach(forVehicleConfigurationList -> response.then().body("nextTestTypesOrCategories.nextTestTypesOrCategories.forVehicleConfiguration", hasItem(hasItem(contains(forVehicleConfigurationList.toArray())))));

        List<List<List<String>>> forVehicleSizeListOfList = testType.getNextTestTypesOrCategories().stream().map(s -> s.getNextTestTypesOrCategories().stream().map(TestType::getForVehicleSize).collect(toList())).collect(toList());
        forVehicleSizeListOfList.forEach(forVehicleSizesList -> response.then().body("nextTestTypesOrCategories.nextTestTypesOrCategories.forVehicleSize", hasItem(hasItem(contains(forVehicleSizesList.toArray())))));

    }

    @Step
    public void validateData(String key, String value) {
        assertThat(response.then().body("$", hasEntry(key,value)));
    }

    public String getActualTestCode(String testCode) {
        return testTypesClient.getActualTestCode(testCode);
    }
}
