package steps;

import clients.TestTypesClient;
import io.restassured.response.Response;
import model.TestType;
import net.thucydides.core.annotations.Step;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.hamcrest.Matchers.*;

public class TestTypeSteps {

    TestTypesClient testTypesClient = new TestTypesClient();
    Response response;

    @Step
    public void getTestTypesWithNoData() {
        response = testTypesClient.getDefectsWithNoData();
    }

    @Step
    public void getTestTypesWithData() {
        response = testTypesClient.getDefectsWithData();
    }

    @Step
    public void statusCodeShouldBe(int statusCode) {
        response.then().statusCode(statusCode);
    }

    @Step
    public void validateData(String stringData) {
        response.then().body("", is(stringData));
    }

    @Step
    public void validateData(TestType testType) {

        validateLevel1Data(testType);
        validateLevel2Data(testType);
        validateLevel3Data(testType);

    }

    @Step
    private void validateLevel1Data(TestType testType) {
        response.then().body("id", hasItem(equalTo(testType.getId())));
        response.then().body("name", hasItem(equalTo(testType.getName())));
        response.then().body("forVehicleType", hasItem(contains(testType.getForVehicleType().toArray())));
        response.then().body("forVehicleConfiguration", hasItem(contains(testType.getForVehicleConfiguration().toArray())));
        response.then().body("forVehicleSize", hasItem(contains(testType.getForVehicleSize().toArray())));
    }

    @Step
    private void validateLevel2Data(TestType testType) {

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
    private void validateLevel3Data(TestType testType){

        List<List<String>> namesList = testType.getNextTestTypesOrCategories().stream().map(s -> s.getNextTestTypesOrCategories().stream().map(TestType::getName).collect(toList())).collect(toList());
        namesList.forEach(names -> response.then().body("nextTestTypesOrCategories.nextTestTypesOrCategories.name", hasItem(hasItem(contains(names.toArray())))));


        List<List<String>> idsList = testType.getNextTestTypesOrCategories().stream().map(s -> s.getNextTestTypesOrCategories().stream().map(TestType::getId).collect(toList())).collect(toList());
        idsList.forEach(ids -> response.then().body("nextTestTypesOrCategories.nextTestTypesOrCategories.id", hasItem(hasItem(contains(ids.toArray())))));

        List<List<List<String>>> forVehicleTypesListOfList = testType.getNextTestTypesOrCategories().stream().map(s -> s.getNextTestTypesOrCategories().stream().map(TestType::getForVehicleType).collect(toList())).collect(toList());
        forVehicleTypesListOfList.forEach(forVehicleTypesList -> response.then().body("nextTestTypesOrCategories.nextTestTypesOrCategories.forVehicleType", hasItem(hasItem(contains(forVehicleTypesList.toArray())))));


        List<List<List<String>>> forVehicleConfigurationListOfList = testType.getNextTestTypesOrCategories().stream().map(s -> s.getNextTestTypesOrCategories().stream().map(TestType::getForVehicleConfiguration).collect(toList())).collect(toList());
        forVehicleConfigurationListOfList.forEach(forVehicleConfigurationList -> response.then().body("nextTestTypesOrCategories.nextTestTypesOrCategories.forVehicleConfiguration", hasItem(hasItem(contains(forVehicleConfigurationList.toArray())))));

        List<List<List<String>>> forVehicleSizeListOfList = testType.getNextTestTypesOrCategories().stream().map(s -> s.getNextTestTypesOrCategories().stream().map(TestType::getForVehicleSize).collect(toList())).collect(toList());
        forVehicleSizeListOfList.forEach(forVehicleSizesList -> response.then().body("nextTestTypesOrCategories.nextTestTypesOrCategories.forVehicleSize", hasItem(hasItem(contains(forVehicleSizesList.toArray())))));

    }

}
