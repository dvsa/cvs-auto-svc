package steps;

import clients.TestTypesClient;
import io.restassured.response.Response;
import model.TestType;
import net.thucydides.core.annotations.Step;

import java.util.ArrayList;
import java.util.List;

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
        List<String> ids = new ArrayList<>();
        for (int i = 0; i < testType.getNextTestTypesOrCategories().size(); i++) {
            ids.add(testType.getNextTestTypesOrCategories().get(i).getId());
        }

        List<String> names = new ArrayList<>();
        for (int i = 0; i < testType.getNextTestTypesOrCategories().size(); i++) {
            names.add(testType.getNextTestTypesOrCategories().get(i).getName());
        }

        List<List<String>> vehicleConfigurations = new ArrayList<>();
        for (int i = 0; i < testType.getNextTestTypesOrCategories().size(); i++) {
            vehicleConfigurations.add(testType.getNextTestTypesOrCategories().get(i).getForVehicleConfiguration());
        }

        List<List<String>> vehicleSize = new ArrayList<>();
        for (int i = 0; i < testType.getNextTestTypesOrCategories().size(); i++) {
            vehicleSize.add(testType.getNextTestTypesOrCategories().get(i).getForVehicleSize());
        }

        List<List<String>> vehicleType = new ArrayList<>();
        for (int i = 0; i < testType.getNextTestTypesOrCategories().size(); i++) {
            vehicleType.add(testType.getNextTestTypesOrCategories().get(i).getForVehicleType());
        }

        response.then().body("nextTestTypesOrCategories.id", hasItem(contains(ids.toArray())));
        response.then().body("nextTestTypesOrCategories.name", hasItem(contains(names.toArray())));
        response.then().body("nextTestTypesOrCategories.forVehicleType", hasItem(contains(vehicleType.toArray())));
        response.then().body("nextTestTypesOrCategories.forVehicleConfiguration", hasItem(contains(vehicleConfigurations.toArray())));
        response.then().body("nextTestTypesOrCategories.forVehicleSize", hasItem(contains(vehicleSize.toArray())));


    }


    @Step
    private void validateLevel3Data(TestType testType){
        List<List<String>> namesList = new ArrayList<>();
        for (int j = 0; j < testType.getNextTestTypesOrCategories().size(); j++) {
            List<String> currentNames = new ArrayList<>();
            for (int i = 0; i < testType.getNextTestTypesOrCategories().get(j).getNextTestTypesOrCategories().size(); i++) {
                currentNames.add(testType.getNextTestTypesOrCategories().get(j).getNextTestTypesOrCategories().get(i).getName());
            }
            namesList.add(currentNames);
        }

        for (List<String> names : namesList) {
            response.then().body("nextTestTypesOrCategories.nextTestTypesOrCategories.name", hasItem(hasItem(contains(names.toArray()))));
        }

        List<List<String>> idsList = new ArrayList<>();
        for (int j = 0; j < testType.getNextTestTypesOrCategories().size(); j++) {
            List<String> currentIds = new ArrayList<>();
            for (int i = 0; i < testType.getNextTestTypesOrCategories().get(j).getNextTestTypesOrCategories().size(); i++) {
                currentIds.add(testType.getNextTestTypesOrCategories().get(j).getNextTestTypesOrCategories().get(i).getId());
            }
            idsList.add(currentIds);
        }

        for (List<String> ids : idsList) {
            response.then().body("nextTestTypesOrCategories.nextTestTypesOrCategories.id", hasItem(hasItem(contains(ids.toArray()))));
        }



        List<List<List<String>>> forVehicleTypesListOfList = new ArrayList<>();
        for (int j = 0; j < testType.getNextTestTypesOrCategories().size(); j++) {
            List<List<String>> currentVehicleTypesList = new ArrayList<>();
            for (int i = 0; i < testType.getNextTestTypesOrCategories().get(j).getNextTestTypesOrCategories().size(); i++) {
                currentVehicleTypesList.add(testType.getNextTestTypesOrCategories().get(j).getNextTestTypesOrCategories().get(i).getForVehicleType());
            }
            forVehicleTypesListOfList.add(currentVehicleTypesList);
        }

        for (List<List<String>> forVehicleTypesList : forVehicleTypesListOfList) {
            response.then().body("nextTestTypesOrCategories.nextTestTypesOrCategories.forVehicleType", hasItem(hasItem(contains(forVehicleTypesList.toArray()))));
        }


        List<List<List<String>>> forVehicleConfigurationListOfList = new ArrayList<>();
        for (int j = 0; j < testType.getNextTestTypesOrCategories().size(); j++) {
            List<List<String>> currentVehicleConfigurationList = new ArrayList<>();
            for (int i = 0; i < testType.getNextTestTypesOrCategories().get(j).getNextTestTypesOrCategories().size(); i++) {
                currentVehicleConfigurationList.add(testType.getNextTestTypesOrCategories().get(j).getNextTestTypesOrCategories().get(i).getForVehicleConfiguration());
            }
            forVehicleConfigurationListOfList.add(currentVehicleConfigurationList);
        }

        for (List<List<String>> forVehicleConfigurationList : forVehicleConfigurationListOfList) {
            response.then().body("nextTestTypesOrCategories.nextTestTypesOrCategories.forVehicleConfiguration", hasItem(hasItem(contains(forVehicleConfigurationList.toArray()))));
        }

        List<List<List<String>>> forVehicleSizeListOfList = new ArrayList<>();
        for (int j = 0; j < testType.getNextTestTypesOrCategories().size(); j++) {
            List<List<String>> currentVehicleSizeList = new ArrayList<>();
            for (int i = 0; i < testType.getNextTestTypesOrCategories().get(j).getNextTestTypesOrCategories().size(); i++) {
                currentVehicleSizeList.add(testType.getNextTestTypesOrCategories().get(j).getNextTestTypesOrCategories().get(i).getForVehicleSize());
            }
            forVehicleSizeListOfList.add(currentVehicleSizeList);
        }

        for (List<List<String>> forVehicleSizesList : forVehicleSizeListOfList) {
            response.then().body("nextTestTypesOrCategories.nextTestTypesOrCategories.forVehicleSize", hasItem(hasItem(contains(forVehicleSizesList.toArray()))));
        }
    }

}
