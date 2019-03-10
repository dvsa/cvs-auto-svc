package steps;

import clients.DefectsClient;
import io.restassured.response.Response;
import model.defects.*;
import net.thucydides.core.annotations.Step;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.hamcrest.Matchers.*;

public class DefectsSteps {

    DefectsClient defectsClient = new DefectsClient();
    Response response;

    @Step
    public void getDefectsWithNoData() {
        response = defectsClient.getDefectsWithNoData();
    }


    @Step
    public void callDefectsWithData() {
        response = defectsClient.getDefectsWithData();
    }

    @Step
    public void statusCodeShouldBe(int statusCode) {
        response.then().statusCode(statusCode);
    }

    @Step
    public void validateData(String stringData) {
        response.then().body("", is(stringData));
    }

    public void validateDeficiencyCategoryNotContains(String... datas) {
        for (String data : datas) {
            response.then().body("items.deficiencies.deficiencyCategory", not(hasItem(hasItem(hasItem(data)))));

        }
    }

    @Step
    public void validateData(Defect defect) {

        response.then().body("[0].size()", is(Defect.class.getDeclaredFields().length));

        response.then().body("imDescription", hasItem(equalTo(defect.getImDescription())));
        response.then().body("imNumber", hasItem(equalTo(defect.getImNumber())));
        response.then().body("forVehicleType", hasItem(contains(defect.getForVehicleType().toArray())));

        response.then().body("[0].additionalInfo.size()", is(AdditionalInfo.class.getDeclaredFields().length));
        response.then().body("[0].additionalInfo.psv.size()", is(Psv.class.getDeclaredFields().length));
        response.then().body("additionalInfo.psv.notes", hasItem(defect.getAdditionalInfo().getPsv().getNotes()));
        response.then().body("[0].additionalInfo.psv.location.size()", is(Location.class.getDeclaredFields().length));
        response.then().body("additionalInfo.psv.location.vertical", hasItem(defect.getAdditionalInfo().getPsv().getLocation().getVertical()));
        response.then().body("additionalInfo.psv.location.horizontal", hasItem(defect.getAdditionalInfo().getPsv().getLocation().getHorizontal()));
        response.then().body("additionalInfo.psv.location.lateral", hasItem(defect.getAdditionalInfo().getPsv().getLocation().getLateral()));
        response.then().body("additionalInfo.psv.location.longitudinal", hasItem(defect.getAdditionalInfo().getPsv().getLocation().getLongitudinal()));
        response.then().body("additionalInfo.psv.location.rowNumber", hasItem(defect.getAdditionalInfo().getPsv().getLocation().getRowNumber()));
        response.then().body("additionalInfo.psv.location.seatNumber", hasItem(defect.getAdditionalInfo().getPsv().getLocation().getSeatNumber()));
        response.then().body("additionalInfo.psv.location.axelNumber", hasItem(nullValue()));


        for (int i = 0; i < (Integer) response.jsonPath().get("items.size()"); i++) {
            for (int j = 0; j < (Integer) response.jsonPath().get("items[" + i + "].size()"); j++) {
                response.then().body("items[" + i + "][" + j + "].size()", is(equalTo(Items.class.getDeclaredFields().length)));
                for (int z = 0; z < (Integer) response.jsonPath().get("items[" + i + "][" + j + "].deficiencies.size()"); z++) {
                    response.then().body("items[" + i + "][" + j + "].deficiencies[" + z + "].size()", is(equalTo(Deficiencies.class.getDeclaredFields().length)));
                }
            }
        }

        List<String> itemDescription = defect.getItems().stream().map(Items::getItemDescription).collect(toList());
        response.then().body("items.itemDescription", hasItem(contains(itemDescription.toArray())));

        List<Integer> itemNumber = defect.getItems().stream().map(Items::getItemNumber).collect(toList());
        response.then().body("items.itemNumber", hasItem(contains(itemNumber.toArray())));

        List<List<String>> vehicleType = defect.getItems().stream().map(Items::getForVehicleType).collect(toList());
        response.then().body("items.forVehicleType", hasItem(contains(vehicleType.toArray())));

        List<List<String>> refList = defect.getItems().stream().map(s -> s.getDeficiencies().stream().map(Deficiencies::getRef).collect(toList())).collect(toList());
        response.then().body("items.deficiencies.ref", hasItem(contains(refList.toArray())));

        List<List<String>> deficiencyIdList = defect.getItems().stream().map(s -> s.getDeficiencies().stream().map(Deficiencies::getDeficiencyId).collect(toList())).collect(toList());
        response.then().body("items.deficiencies.deficiencyId", hasItem(contains(deficiencyIdList.toArray())));

        List<List<String>> deficiencySubIdList = defect.getItems().stream().map(s -> s.getDeficiencies().stream().map(Deficiencies::getDeficiencySubId).collect(toList())).collect(toList());
        response.then().body("items.deficiencies.deficiencySubId", hasItem(contains(deficiencySubIdList.toArray())));

        List<List<String>> deficiencyCategoryList = defect.getItems().stream().map(s -> s.getDeficiencies().stream().map(Deficiencies::getDeficiencyCategory).collect(toList())).collect(toList());
        response.then().body("items.deficiencies.deficiencyCategory", hasItem(contains(deficiencyCategoryList.toArray())));


        List<List<String>> deficiencyTextList = defect.getItems().stream().map(s -> s.getDeficiencies().stream().map(Deficiencies::getDeficiencyText).collect(toList())).collect(toList());
        response.then().body("items.deficiencies.deficiencyText", hasItem(contains(deficiencyTextList.toArray())));


        List<List<Boolean>> stdForProhibitionList = defect.getItems().stream().map(s -> s.getDeficiencies().stream().map(Deficiencies::getStdForProhibition).collect(toList())).collect(toList());
        response.then().body("items.deficiencies.stdForProhibition", hasItem(contains(stdForProhibitionList.toArray())));

        List<List<List<String>>> forVehicleTypeList = defect.getItems().stream().map(s -> s.getDeficiencies().stream().map(Deficiencies::getForVehicleType).collect(toList())).collect(toList());
        response.then().body("items.deficiencies.forVehicleType", hasItem(contains(forVehicleTypeList.toArray())));

    }


}
