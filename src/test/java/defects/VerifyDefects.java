package defects;

import io.restassured.response.Response;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import steps.DefectsSteps;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(SerenityRunner.class)
public class VerifyDefects {

    @Steps
    static DefectsSteps defectsSteps;

    private boolean checkVehicleTypes(JSONArray defectVehicleTypes, ArrayList<String> expectedVehicleTypes) throws JSONException {
        ArrayList<String> vTypes = new ArrayList<>();
        for (int l = 0; l < defectVehicleTypes.length(); l++)
            vTypes.add(defectVehicleTypes.getString(l));
        return vTypes.size() == expectedVehicleTypes.size() && vTypes.containsAll(expectedVehicleTypes);
    }


    public void getDefects(String code, ArrayList<String> defectsText, String deficiencyCategory, ArrayList<String> vehicleTypes) {
        Response defectTypes = defectsSteps.callDefectsWithData();
        try {
//            int level = 0;
            List<String> ids = Arrays.asList(code.split("\\."));
            boolean found = false;
            JSONArray allDefects = new JSONArray(defectTypes.asString());
            for (int i = 0; i < allDefects.length(); i++) {
                JSONObject defectObject = allDefects.getJSONObject(i);
                if (defectObject.getInt("imNumber") == Integer.parseInt(ids.get(0)) && defectObject.getString("imDescription").equals(defectsText.get(0))) {
                    JSONArray items = defectObject.getJSONArray("items");
                    for (int k = 0; k < items.length(); k++) {
                        JSONObject item = items.getJSONObject(k);
                        if (item.getInt("itemNumber") == Integer.parseInt(ids.get(1)) && item.getString("itemDescription").equals(defectsText.get(1))) {
                            JSONArray deficiencies = item.getJSONArray("deficiencies");
                            for (int j = 0; j < deficiencies.length(); j++) {
                                JSONObject deficiencyObject = deficiencies.getJSONObject(j);
                                if (deficiencyObject.getString("deficiencyText").equals(defectsText.get(2)) &&
                                        deficiencyObject.getString("deficiencyCategory").equals(deficiencyCategory)) {

                                    if (ids.size() > 2) {
                                        if (deficiencyObject.getString("deficiencyId").equals(ids.get(2)))
                                            if (ids.size() > 3) {
                                                if (deficiencyObject.getString("deficiencySubid").equals(ids.get(3))) {
                                                    JSONArray vehicleTypesDefect = deficiencyObject.getJSONArray("forVehicleType");
                                                    if (checkVehicleTypes(vehicleTypesDefect, vehicleTypes))
                                                        found = true;
                                                }
                                            } else {
                                                JSONArray vehicleTypesDefect = deficiencyObject.getJSONArray("forVehicleType");
                                                if (checkVehicleTypes(vehicleTypesDefect, vehicleTypes))
                                                    found = true;
                                            }
                                    } else {
                                        JSONArray vehicleTypesDefect = defectObject.getJSONArray("forVehicleType");
                                        if (checkVehicleTypes(vehicleTypesDefect, vehicleTypes))
                                            found = true;
                                    }
                                }

                            }

                        }
                    }
                }
            }
            assert found;
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    @Test
    @Title("CVSB - 11012 AC1 - Defect taxonomy updates are visible in the app 6.1.a")
    public void testMethod61a() {
        String id = "6.1.a";
        ArrayList<String> defects = new ArrayList<String>() {{
            add("Road Wheels and Hubs");
            add("A tyre retaining ring:");
            add("fractured or not properly fitted such that detachment is likely.");
        }};

        String deficiencyCategory = "dangerous";

        ArrayList<String> vehicleConfiguration = new ArrayList<String>() {{
            add("psv");
            add("hgv");
            add("trl");
        }};

        getDefects(id, defects, deficiencyCategory, vehicleConfiguration);
    }


    @Test
    @Title("CVSB - 11012 AC1 - Defect taxonomy updates are visible in the app 24.9")
    public void testMethod249() {
        String id = "24.9";
        ArrayList<String> defects = new ArrayList<String>() {{
            add("Accessibility Features");
            add("Communication devices: Lighting specifically intended for wheelchair users to board or alight in safety");
            add("missing, inoperative or deteriorated to the extent that the illumination is significantly reduced.");
        }};

        String deficiencyCategory = "major";

        ArrayList<String> vehicleConfiguration = new ArrayList<String>() {{
            add("psv");
        }};

        getDefects(id, defects, deficiencyCategory, vehicleConfiguration);
    }


    @Test
    @Title("CVSB - 11012 AC1 - Defect taxonomy updates are visible in the app 24.9")
    public void testMethod631ei() {
        String id = "63.1.e.i";
        ArrayList<String> defects = new ArrayList<String>() {{
            add("Lamps");
            add("For all lamps:");
            add("an obligatory lamp not showing a light of the right colour.");
        }};

        String deficiencyCategory = "minor";

        ArrayList<String> vehicleConfiguration = new ArrayList<String>() {{
            add("psv");
            add("hgv");
            add("trl");
        }};

        getDefects(id, defects, deficiencyCategory, vehicleConfiguration);
    }

}


