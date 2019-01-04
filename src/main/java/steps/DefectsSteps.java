package steps;

import clients.DefectsClient;
import io.restassured.response.Response;
import model.defects.Defect;
import net.thucydides.core.annotations.Step;

import java.util.ArrayList;
import java.util.List;

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

    @Step
    public void validateData(Defect defect) {

        response.then().body("imDescription", hasItem(equalTo(defect.getImDescription())));
        response.then().body("imNumber", hasItem(equalTo(defect.getImNumber())));
        response.then().body("forVehicleType", hasItem(contains(defect.getForVehicleType().toArray())));

        response.then().body("additionalInfo.psv.notes", hasItem(defect.getAdditionalInfo().getPsv().getNotes()));
        response.then().body("additionalInfo.psv.location.vertical", hasItem(defect.getAdditionalInfo().getPsv().getLocation().getVertical()));
        response.then().body("additionalInfo.psv.location.horizontal", hasItem(defect.getAdditionalInfo().getPsv().getLocation().getHorizontal()));
        response.then().body("additionalInfo.psv.location.lateral", hasItem(defect.getAdditionalInfo().getPsv().getLocation().getLateral()));
        response.then().body("additionalInfo.psv.location.longitudinal", hasItem(defect.getAdditionalInfo().getPsv().getLocation().getLongitudinal()));
        response.then().body("additionalInfo.psv.location.rowNumber", hasItem(defect.getAdditionalInfo().getPsv().getLocation().getRowNumber()));
        response.then().body("additionalInfo.psv.location.seatNumber", hasItem(defect.getAdditionalInfo().getPsv().getLocation().getSeatNumber()));
        response.then().body("additionalInfo.psv.location.axelNumber", hasItem(nullValue()));



        List<String> itemDescription = new ArrayList<>();
        for (int i = 0; i < defect.getItems().size(); i++) {
            itemDescription.add(defect.getItems().get(i).getItemDescription());
        }
        response.then().body("items.itemDescription", hasItem(contains(itemDescription.toArray())));

        List<Integer> itemNumber = new ArrayList<>();
        for (int i = 0; i < defect.getItems().size(); i++) {
            itemNumber.add(defect.getItems().get(i).getItemNumber());
        }
        response.then().body("items.itemNumber", hasItem(contains(itemNumber.toArray())));

        List<List<String>> vehicleType = new ArrayList<>();
        for (int i = 0; i < defect.getItems().size(); i++) {
            vehicleType.add(defect.getItems().get(i).getForVehicleType());
        }
        response.then().body("items.forVehicleType", hasItem(contains(vehicleType.toArray())));

        List<List<String>> refList = new ArrayList<>();
        for (int j = 0; j < defect.getItems().size(); j++) {
            List<String> currentRefList = new ArrayList<>();
            for (int i = 0; i < defect.getItems().get(j).getDeficiencies().size(); i++) {
                currentRefList.add(defect.getItems().get(j).getDeficiencies().get(i).getRef());
            }
            refList.add(currentRefList);
        }
        response.then().body("items.deficiencies.ref", hasItem(contains(refList.toArray())));

        List<List<String>> deficiencyIdList = new ArrayList<>();
        for (int j = 0; j < defect.getItems().size(); j++) {
            List<String> currentDeficiencyIdList = new ArrayList<>();
            for (int i = 0; i < defect.getItems().get(j).getDeficiencies().size(); i++) {
                currentDeficiencyIdList.add(defect.getItems().get(j).getDeficiencies().get(i).getDeficiencyId());
            }
            deficiencyIdList.add(currentDeficiencyIdList);
        }

        response.then().body("items.deficiencies.deficiencyId", hasItem(contains(deficiencyIdList.toArray())));


        List<List<String>> deficiencySubIdList = new ArrayList<>();
        for (int j = 0; j < defect.getItems().size(); j++) {
            List<String> currentDeficiencySubIdList = new ArrayList<>();
            for (int i = 0; i < defect.getItems().get(j).getDeficiencies().size(); i++) {
                currentDeficiencySubIdList.add(defect.getItems().get(j).getDeficiencies().get(i).getDeficiencySubId());
            }
            deficiencySubIdList.add(currentDeficiencySubIdList);
        }

        response.then().body("items.deficiencies.deficiencySubId", hasItem(contains(deficiencySubIdList.toArray())));

        List<List<String>> deficiencyCategoryList = new ArrayList<>();
        for (int j = 0; j < defect.getItems().size(); j++) {
            List<String> currentDeficiencyCategoryList = new ArrayList<>();
            for (int i = 0; i < defect.getItems().get(j).getDeficiencies().size(); i++) {
                currentDeficiencyCategoryList.add(defect.getItems().get(j).getDeficiencies().get(i).getDeficiencyCategory());
            }
            deficiencyCategoryList.add(currentDeficiencyCategoryList);
        }

        response.then().body("items.deficiencies.deficiencyCategory", hasItem(contains(deficiencyCategoryList.toArray())));



        List<List<String>> deficiencyTextList = new ArrayList<>();
        for (int j = 0; j < defect.getItems().size(); j++) {
            List<String> currentDeficiencyTextList = new ArrayList<>();
            for (int i = 0; i < defect.getItems().get(j).getDeficiencies().size(); i++) {
                currentDeficiencyTextList.add(defect.getItems().get(j).getDeficiencies().get(i).getDeficiencyText());
            }
            deficiencyTextList.add(currentDeficiencyTextList);
        }

        response.then().body("items.deficiencies.deficiencyText", hasItem(contains(deficiencyTextList.toArray())));


        List<List<Boolean>> stdForProhibitionList = new ArrayList<>();
        for (int j = 0; j < defect.getItems().size(); j++) {
            List<Boolean> currentStdForProhibitionList = new ArrayList<>();
            for (int i = 0; i < defect.getItems().get(j).getDeficiencies().size(); i++) {
                currentStdForProhibitionList.add(defect.getItems().get(j).getDeficiencies().get(i).getStdForProhibition());
            }
            stdForProhibitionList.add(currentStdForProhibitionList);
        }

        response.then().body("items.deficiencies.stdForProhibition", hasItem(contains(stdForProhibitionList.toArray())));

        List<List<List<String>>> forVehicleTypeList = new ArrayList<>();
        for (int j = 0; j < defect.getItems().size(); j++) {
            List<List<String>> currentForVehicleTypeList = new ArrayList<>();
            for (int i = 0; i < defect.getItems().get(j).getDeficiencies().size(); i++) {
                currentForVehicleTypeList.add(defect.getItems().get(j).getDeficiencies().get(i).getForVehicleType());
            }
            forVehicleTypeList.add(currentForVehicleTypeList);
        }

        response.then().body("items.deficiencies.forVehicleType", hasItem(contains(forVehicleTypeList.toArray())));
    }

}
