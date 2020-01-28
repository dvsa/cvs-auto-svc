package defects;

import io.restassured.response.Response;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import steps.DefectsSteps;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(SerenityRunner.class)
public class VerifyDefectsTaxonomy {

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
                                        if (deficiencyObject.getString("deficiencyId").equals(ids.get(2))) {
                                            if (ids.size() > 3) {
                                                if (deficiencyObject.getString("deficiencySubId").equals(ids.get(3))) {
                                                    JSONArray vehicleTypesDefect = deficiencyObject.getJSONArray("forVehicleType");
                                                    if (checkVehicleTypes(vehicleTypesDefect, vehicleTypes))
                                                        found = true;
                                                }
                                            } else {
                                                JSONArray vehicleTypesDefect = deficiencyObject.getJSONArray("forVehicleType");
                                                if (checkVehicleTypes(vehicleTypesDefect, vehicleTypes))
                                                    found = true;
                                            }
                                        } else if (deficiencyObject.getString("deficiencyId").equals("null") && deficiencyObject.getString("deficiencySubId").equals(ids.get(2))) {
                                            JSONArray vehicleTypesDefect = deficiencyObject.getJSONArray("forVehicleType");
                                            if (checkVehicleTypes(vehicleTypesDefect, vehicleTypes))
                                                found = true;
                                        }
                                    } else {
                                        JSONArray vehicleTypesDefect = deficiencyObject.getJSONArray("forVehicleType");
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
    public void testDefect61a() {
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
    @Title("CVSB - 11012 AC1 - Defect taxonomy updates are visible in the app 6.1.b")
    public void testDefects61b() {
        String id = "6.1.b";
        ArrayList<String> defects = new ArrayList<String>() {{
            add("Road Wheels and Hubs");
            add("A tyre retaining ring:");
            add("butting causing the flange to lift more than 1.5mm from the rim and/or not properly fitted.");
        }};
        String deficiencyCategory = "major";
        ArrayList<String> vehicleConfiguration = new ArrayList<String>() {{
            add("psv");
            add("hgv");
            add("trl");
        }};
        getDefects(id, defects, deficiencyCategory, vehicleConfiguration);
    }

    @Test
    @Title("CVSB - 11012 AC1 - Defect taxonomy updates are visible in the app 10.2.i")
    public void testDefects102i() {
        String id = "10.2.i";
        ArrayList<String> defects = new ArrayList<String>() {{
            add("Spare Wheel & Carrier");
            add("A spare wheel:");
            add("insecure, damaged or incorrectly positioned but not so that it is likely to fall from the vehicle.");
        }};
        String deficiencyCategory = "major";
        ArrayList<String> vehicleConfiguration = new ArrayList<String>() {{
            add("psv");
            add("hgv");
            add("trl");
        }};
        getDefects(id, defects, deficiencyCategory, vehicleConfiguration);
    }

    @Test
    @Title("CVSB - 11012 AC1 - Defect taxonomy updates are visible in the app 10.2.ii")
    public void testDefects102iiNoPSV() {
        String id = "10.2.ii";
        ArrayList<String> defects = new ArrayList<String>() {{
            add("Spare Wheel & Carrier");
            add("A spare wheel:");
            add("so insecure or positioned that it is likely to fall from the vehicle.");
        }};
        String deficiencyCategory = "dangerous";
        ArrayList<String> vehicleConfiguration = new ArrayList<String>() {{
            add("hgv");
            add("trl");
        }};
        getDefects(id, defects, deficiencyCategory, vehicleConfiguration);
    }

    @Test
    @Title("CVSB - 11012 AC1 - Defect taxonomy updates are visible in the app 10.2.ii")
    public void testDefects102ii() {
        String id = "10.2.ii";
        ArrayList<String> defects = new ArrayList<String>() {{
            add("Spare Wheel & Carrier");
            add("A spare wheel:");
            add("so insecure or positioned that it is likely to fall from the vehicle or cause damage to the electrical wiring, other vehicle components or passenger luggage, or is likely to injure occupants.");
        }};
        String deficiencyCategory = "dangerous";
        ArrayList<String> vehicleConfiguration = new ArrayList<String>() {{
            add("psv");
        }};
        getDefects(id, defects, deficiencyCategory, vehicleConfiguration);
    }

    @Test
    @Title("CVSB - 11012 AC1 - Defect taxonomy updates are visible in the app 17.1.ii")
    public void testDefects171ii() {
        String id = "17.1.ii";
        ArrayList<String> defects = new ArrayList<String>() {{
            add("Cab Floor and Steps");
            add("A cab floor or internal wheel arch:");
            add("which is so badly deteriorated or insecure it is likely to impair the driver’s control of the vehicle or is likely to cause injury.");
        }};
        String deficiencyCategory = "dangerous";
        ArrayList<String> vehicleConfiguration = new ArrayList<String>() {{
            add("hgv");
        }};
        getDefects(id, defects, deficiencyCategory, vehicleConfiguration);
    }

    @Test
    @Title("CVSB - 11012 AC1 - Defect taxonomy updates are visible in the app 17.1.ii")
    public void testDefects171iiForPSV() {
        String id = "17.1.ii";
        ArrayList<String> defects = new ArrayList<String>() {{
            add("Drivers Accommodation");
            add("A cab floor or internal wheel arch in the driver's area which:");
            add("is so badly deteriorated or insecure it is likely to impair the driver's control of the vehicle or is likely to cause injury..");
        }};
        String deficiencyCategory = "dangerous";
        ArrayList<String> vehicleConfiguration = new ArrayList<String>() {{
            add("psv");
        }};
        getDefects(id, defects, deficiencyCategory, vehicleConfiguration);
    }

    @Test
    @Title("CVSB - 11012 AC1 - Defect taxonomy updates are visible in the app 19.3.e")
    public void testDefects193e() {
        String id = "19.3.e";
        ArrayList<String> defects = new ArrayList<String>() {{
            add("Security of Body, Containers and Crane Support Legs");
            add("A container fastening device:");
            add("not fitted with an effective secondary locking device.");
        }};
        String deficiencyCategory = "major";
        ArrayList<String> vehicleConfiguration = new ArrayList<String>() {{
            add("hgv");
            add("trl");
        }};
        getDefects(id, defects, deficiencyCategory, vehicleConfiguration);
    }

    @Test
    @Title("CVSB - 11012 AC1 - Defect taxonomy updates are visible in the app 24.4.b")
    public void testDefects244b() {
        String id = "24.4.b";
        ArrayList<String> defects = new ArrayList<String>() {{
            add("Accessibility Features");
            add("A power operated lift or ramp (additional requirements):");
            add("with an audible device missing or inoperative. (except an Annex VII done vehicle with a powered lift).");
        }};
        String deficiencyCategory = "major";
        ArrayList<String> vehicleConfiguration = new ArrayList<String>() {{
            add("psv");
        }};
        getDefects(id, defects, deficiencyCategory, vehicleConfiguration);
    }

    @Test
    @Title("CVSB - 11012 AC1 - Defect taxonomy updates are visible in the app 24.9")
    public void testDefect249() {
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
    @Title("CVSB - 11012 AC1 - Defect taxonomy updates are visible in the app 26.3.a")
    public void testDefects263a() {
        String id = "26.3.a";
        ArrayList<String> defects = new ArrayList<String>() {{
            add("Speedometer/Tachograph");
            add("For vehicles required to be fitted with a tachograph:");
            add("tachograph scale not marked in kilometres per hour (There is no requirement for tachographs to be marked in m.p.h.).");
        }};
        String deficiencyCategory = "major";
        ArrayList<String> vehicleConfiguration = new ArrayList<String>() {{
            add("psv");
            add("hgv");
        }};
        getDefects(id, defects, deficiencyCategory, vehicleConfiguration);
    }

    @Test
    @Title("CVSB - 11012 AC1 - Defect taxonomy updates are visible in the app 26.3.i")
    public void testDefects263i() {
        String id = "26.3.i";
        ArrayList<String> defects = new ArrayList<String>() {{
            add("Speedometer/Tachograph");
            add("For vehicles required to be fitted with a tachograph:");
            add("a digital tachograph that displays a ‘K’ factor reading different to that shown on the calibration plaque (there is no allowance/tolerance as with analogue type tachographs).");
        }};
        String deficiencyCategory = "major";
        ArrayList<String> vehicleConfiguration = new ArrayList<String>() {{
            add("psv");
            add("hgv");
        }};
        getDefects(id, defects, deficiencyCategory, vehicleConfiguration);
    }

    @Test
    @Title("CVSB - 11012 AC1 - Defect taxonomy updates are visible in the app 26.4.b")
    public void testDefects264b() {
        String id = "26.4.b";
        ArrayList<String> defects = new ArrayList<String>() {{
            add("Speedometer/Tachograph");
            add("For vehicles not required to be fitted with a tachograph, where a tachograph is fitted in place of a speedometer:");
            add("If a speed limiter is not required: a seal (within the tachograph head) missing, broken, or where a clearly “non mandatory” seal has been fitted in place of an “official” seal. There is no requirement for the gearbox sender unit to be sealed.");
        }};
        String deficiencyCategory = "major";
        ArrayList<String> vehicleConfiguration = new ArrayList<String>() {{
            add("psv");
            add("hgv");
        }};
        getDefects(id, defects, deficiencyCategory, vehicleConfiguration);
    }

    @Test
    @Title("CVSB - 11012 AC1 - Defect taxonomy updates are visible in the app 41.2.a.i")
    public void testDefect412ai() {
        String id = "41.2.a.i";
        ArrayList<String> defects = new ArrayList<String>() {{
            add("Condition of Chassis");
            add("Frame and/or cross member fastenings:");
            add("insecure flitch plates and/or fastenings or welds breaking away.");
        }};
        String deficiencyCategory = "major";
        ArrayList<String> vehicleConfiguration = new ArrayList<String>() {{
            add("psv");
            add("hgv");
            add("trl");
        }};
        getDefects(id, defects, deficiencyCategory, vehicleConfiguration);
    }

    @Ignore("Blocked by CVSB-11688")
    @Test
    @Title("CVSB - 11012 AC1 - Defect taxonomy updates are visible in the app 41.2.a.ii")
    public void testDefect412aii() {
        String id = "41.2.a.ii";
        ArrayList<String> defects = new ArrayList<String>() {{
            add("Condition of Chassis");
            add("Frame and/or cross member fastenings:");
            add("insecure flitch plates and/or fastenings or welds breaking away so that the control of the vehicle is likely to be affected or the load will become insecure.");
        }};
        String deficiencyCategory = "dangerous";
        ArrayList<String> vehicleConfiguration = new ArrayList<String>() {{
            add("hgv");
            add("trl");
        }};
        getDefects(id, defects, deficiencyCategory, vehicleConfiguration);
    }

    @Test
    @Title("CVSB - 11012 AC1 - Defect taxonomy updates are visible in the app 41.2.a.ii")
    public void testDefect412aiiForPSV() {
        String id = "41.2.a.ii";
        ArrayList<String> defects = new ArrayList<String>() {{
            add("Condition of Chassis");
            add("Frame and/or cross member fastenings:");
            add("insecure flitch plates and/or fastenings or welds breaking away so that the control of the vehicle is likely to be affected.");
        }};
        String deficiencyCategory = "dangerous";
        ArrayList<String> vehicleConfiguration = new ArrayList<String>() {{
            add("psv");
        }};
        getDefects(id, defects, deficiencyCategory, vehicleConfiguration);
    }

    @Test
    @Title("CVSB - 11012 AC1 - Defect taxonomy updates are visible in the app 41.1.b.i")
    public void testDefect461bi() {
        String id = "46.1.b.i";
        ArrayList<String> defects = new ArrayList<String>() {{
            add("Exhaust Systems and Nuisance");
            add("An exhaust system:");
            add("leaking.");
        }};
        String deficiencyCategory = "major";
        ArrayList<String> vehicleConfiguration = new ArrayList<String>() {{
            add("psv");
            add("hgv");
        }};
        getDefects(id, defects, deficiencyCategory, vehicleConfiguration);
    }

    @Test
    @Title("CVSB - 11012 AC1 - Defect taxonomy updates are visible in the app 41.1.b.ii")
    public void testDefect461biiForHGV() {
        String id = "46.1.b.ii";
        ArrayList<String> defects = new ArrayList<String>() {{
            add("Exhaust Systems and Nuisance");
            add("An exhaust system:");
            add("leaking and entering the drivers cab.");
        }};
        String deficiencyCategory = "dangerous";
        ArrayList<String> vehicleConfiguration = new ArrayList<String>() {{
            add("hgv");
        }};
        getDefects(id, defects, deficiencyCategory, vehicleConfiguration);
    }

    @Test
    @Title("CVSB - 11012 AC1 - Defect taxonomy updates are visible in the app 41.1.b.ii")
    public void testDefect461biiForPSV() {
        String id = "46.1.b.ii";
        ArrayList<String> defects = new ArrayList<String>() {{
            add("Exhaust Systems and Nuisance");
            add("An exhaust system:");
            add("leaking and entering the drivers cab and/or passenger compartment.");
        }};
        String deficiencyCategory = "dangerous";
        ArrayList<String> vehicleConfiguration = new ArrayList<String>() {{
            add("psv");
        }};
        getDefects(id, defects, deficiencyCategory, vehicleConfiguration);
    }

    @Test
    @Title("CVSB - 11012 AC1 - Defect taxonomy updates are visible in the app 59.9.ii")
    public void testDefect599ii() {
        String id = "59.9.ii";
        ArrayList<String> defects = new ArrayList<String>() {{
            add("Brake Systems and Components");
            add("Unsafe:");
            add("unsafe modification to any part of the braking system and braking performance is affected.");
        }};
        String deficiencyCategory = "dangerous";
        ArrayList<String> vehicleConfiguration = new ArrayList<String>() {{
            add("hgv");
            add("trl");
        }};
        getDefects(id, defects, deficiencyCategory, vehicleConfiguration);
    }

    @Test
    @Title("CVSB - 11012 AC1 - Defect taxonomy updates are visible in the app 63.1.e.i")
    public void testDefect631ei() {
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

    @Test
    @Title("CVSB - 11012 AC1 - Defect taxonomy updates are visible in the app 63.2.a")
    public void testDefect632a() {
        String id = "63.2.a";
        ArrayList<String> defects = new ArrayList<String>() {{
            add("Lamps");
            add("Rear Fog lamp:(in addition to 1 above)");
            add("tell-tale light not fitted, not working or cannot be seen by the driver.");
        }};
        String deficiencyCategory = "major";
        ArrayList<String> vehicleConfiguration = new ArrayList<String>() {{
            add("psv");
            add("hgv");
            add("trl");
        }};
        getDefects(id, defects, deficiencyCategory, vehicleConfiguration);
    }

    @Test
    @Title("CVSB - 11012 AC1 - Defect taxonomy updates are visible in the app 63.2.b")
    public void testDefect632b() {
        String id = "63.2.b";
        ArrayList<String> defects = new ArrayList<String>() {{
            add("Lamps");
            add("Rear Fog lamp:(in addition to 1 above)");
            add("more than two rear fog lamps are fitted.");
        }};
        String deficiencyCategory = "major";
        ArrayList<String> vehicleConfiguration = new ArrayList<String>() {{
            add("psv");
            add("hgv");
            add("trl");
        }};
        getDefects(id, defects, deficiencyCategory, vehicleConfiguration);
    }

    @Test
    @Title("CVSB - 11012 AC1 - Defect taxonomy updates are visible in the app 63.2.c")
    public void testDefect632c() {
        String id = "63.2.c";
        ArrayList<String> defects = new ArrayList<String>() {{
            add("Lamps");
            add("Rear Fog lamp:(in addition to 1 above)");
            add("non obligatory rear fog lamp(s) affected by the operation of the foot brake and or shows the incorrect colour.");
        }};
        String deficiencyCategory = "major";
        ArrayList<String> vehicleConfiguration = new ArrayList<String>() {{
            add("psv");
            add("hgv");
            add("trl");
        }};
        getDefects(id, defects, deficiencyCategory, vehicleConfiguration);
    }

    @Test
    @Title("CVSB - 11012 AC1 - Defect taxonomy updates are visible in the app 63.3")
    public void testDefect633() {
        String id = "63.3";
        ArrayList<String> defects = new ArrayList<String>() {{
            add("Lamps");
            add("No stop lamps (in addition to 1 above)");
            add("show a steady red light when the brakes are applied, or all lamps do not go out when the brakes are released.");
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
    @Title("CVSB - 11012 AC1 - Defect taxonomy updates are visible in the app 63.4.a")
    public void testDefect634a() {
        String id = "63.4.a";
        ArrayList<String> defects = new ArrayList<String>() {{
            add("Lamps");
            add("Headlamp: (in addition to 1 above)");
            add("not forming part of a matched pair.");
        }};
        String deficiencyCategory = "major";
        ArrayList<String> vehicleConfiguration = new ArrayList<String>() {{
            add("psv");
            add("hgv");
        }};
        getDefects(id, defects, deficiencyCategory, vehicleConfiguration);
    }

    @Test
    @Title("CVSB - 11012 AC1 - Defect taxonomy updates are visible in the app 63.4.b")
    public void testDefect634b() {
        String id = "63.4.b";
        ArrayList<String> defects = new ArrayList<String>() {{
            add("Lamps");
            add("Headlamp: (in addition to 1 above)");
            add("not positioned symmetrically in relation to the other lamp.");
        }};
        String deficiencyCategory = "major";
        ArrayList<String> vehicleConfiguration = new ArrayList<String>() {{
            add("psv");
            add("hgv");
        }};
        getDefects(id, defects, deficiencyCategory, vehicleConfiguration);
    }

    @Test
    @Title("CVSB - 11012 AC1 - Defect taxonomy updates are visible in the app 63.4.c")
    public void testDefect634c() {
        String id = "63.4.c";
        ArrayList<String> defects = new ArrayList<String>() {{
            add("Lamps");
            add("Headlamp: (in addition to 1 above)");
            add("one of a matched pair does not show a light of the same intensity and colour as the other.");
        }};
        String deficiencyCategory = "major";
        ArrayList<String> vehicleConfiguration = new ArrayList<String>() {{
            add("psv");
            add("hgv");
        }};
        getDefects(id, defects, deficiencyCategory, vehicleConfiguration);
    }

    @Test
    @Title("CVSB - 11012 AC1 - Defect taxonomy updates are visible in the app 63.4.d")
    public void testDefect63d() {
        String id = "63.4.d";
        ArrayList<String> defects = new ArrayList<String>() {{
            add("Lamps");
            add("Headlamp: (in addition to 1 above)");
            add("a main beam headlamp cannot be switched off by operating one switch which at the same time leaves a pair of dipped beams.");
        }};
        String deficiencyCategory = "major";
        ArrayList<String> vehicleConfiguration = new ArrayList<String>() {{
            add("psv");
            add("hgv");
        }};
        getDefects(id, defects, deficiencyCategory, vehicleConfiguration);
    }

    @Test
    @Title("CVSB - 11012 AC1 - Defect taxonomy updates are visible in the app 63.4.e")
    public void testDefect634e() {
        String id = "63.4.e";
        ArrayList<String> defects = new ArrayList<String>() {{
            add("Lamps");
            add("Headlamp: (in addition to 1 above)");
            add("main beam warning lamp does not illuminate when main beam is selected and extinguish when dipped beam is selected.");
        }};
        String deficiencyCategory = "major";
        ArrayList<String> vehicleConfiguration = new ArrayList<String>() {{
            add("psv");
            add("hgv");
        }};
        getDefects(id, defects, deficiencyCategory, vehicleConfiguration);
    }

    @Test
    @Title("CVSB - 11012 AC1 - Defect taxonomy updates are visible in the app 63.4.f.i")
    public void testDefect634fi() {
        String id = "63.4.f.i";
        ArrayList<String> defects = new ArrayList<String>() {{
            add("Lamps");
            add("Headlamp: (in addition to 1 above)");
            add("cleaning device inoperative.");
        }};
        String deficiencyCategory = "minor";
        ArrayList<String> vehicleConfiguration = new ArrayList<String>() {{
            add("psv");
            add("hgv");
        }};
        getDefects(id, defects, deficiencyCategory, vehicleConfiguration);
    }

    @Test
    @Title("CVSB - 11012 AC1 - Defect taxonomy updates are visible in the app 63.4.f.ii")
    public void testDefect634fii() {
        String id = "63.4.f.ii";
        ArrayList<String> defects = new ArrayList<String>() {{
            add("Lamps");
            add("Headlamp: (in addition to 1 above)");
            add("cleaning device inoperative for LED or  gas discharge (HID) systems.");
        }};
        String deficiencyCategory = "major";
        ArrayList<String> vehicleConfiguration = new ArrayList<String>() {{
            add("psv");
            add("hgv");
        }};
        getDefects(id, defects, deficiencyCategory, vehicleConfiguration);
    }

    @Test
    @Title("CVSB - 11012 AC1 - Defect taxonomy updates are visible in the app 63.5")
    public void testDefect635() {
        String id = "63.5";
        ArrayList<String> defects = new ArrayList<String>() {{
            add("Lamps");
            add("A rear registration plate lamp (in addition to 1 above)");
            add("throws direct light to the rear.");
        }};
        String deficiencyCategory = "major";
        ArrayList<String> vehicleConfiguration = new ArrayList<String>() {{
            add("psv");
            add("hgv");
            add("trl");
        }};
        getDefects(id, defects, deficiencyCategory, vehicleConfiguration);
    }

    @Test
    @Title("CVSB - 11012 AC1 - Defect taxonomy updates are visible in the app 63.6")
    public void testDefects636() {
        String id = "63.6";
        ArrayList<String> defects = new ArrayList<String>() {{
            add("Lamps");
            add("Front fog lamp (in addition to 1 above)");
            add("inoperative.");
        }};
        String deficiencyCategory = "major";
        ArrayList<String> vehicleConfiguration = new ArrayList<String>() {{
            add("psv");
            add("hgv");
        }};
        getDefects(id, defects, deficiencyCategory, vehicleConfiguration);
    }

    @Test
    @Title("CVSB - 11012 AC1 - Defect taxonomy updates are visible in the app 63.7.a")
    public void testDefects637a() {
        String id = "63.7.a";
        ArrayList<String> defects = new ArrayList<String>() {{
            add("Lamps");
            add("Reversing lamp (in addition to 1 above):");
            add("does not operate automatically when reverse gear is selected.");
        }};
        String deficiencyCategory = "major";
        ArrayList<String> vehicleConfiguration = new ArrayList<String>() {{
            add("psv");
            add("hgv");
            add("trl");
        }};
        getDefects(id, defects, deficiencyCategory, vehicleConfiguration);
    }

    @Test
    @Title("CVSB - 11012 AC1 - Defect taxonomy updates are visible in the app 63.8.a")
    public void testDefects638a() {
        String id = "63.8.a";
        ArrayList<String> defects = new ArrayList<String>() {{
            add("Lamps");
            add("Day time running lamp (in addition to 1 above):");
            add("not showing a light of the right colour.");
        }};
        String deficiencyCategory = "minor";
        ArrayList<String> vehicleConfiguration = new ArrayList<String>() {{
            add("psv");
            add("hgv");
        }};
        getDefects(id, defects, deficiencyCategory, vehicleConfiguration);
    }

    @Test
    @Title("CVSB - 11012 AC1 - Defect taxonomy updates are visible in the app 63.8.b")
    public void testDefects638b() {
        String id = "63.8.b";
        ArrayList<String> defects = new ArrayList<String>() {{
            add("Lamps");
            add("Day time running lamp (in addition to 1 above):");
            add("not showing a light of the right colour with red light shown to the front or white light shown to the rear.");
        }};
        String deficiencyCategory = "major";
        ArrayList<String> vehicleConfiguration = new ArrayList<String>() {{
            add("psv");
            add("hgv");
        }};
        getDefects(id, defects, deficiencyCategory, vehicleConfiguration);
    }

    @Test
    @Title("CVSB - 11012 AC1 - Defect taxonomy updates are visible in the app 63.8.c")
    public void testDefects638c() {
        String id = "63.8.c";
        ArrayList<String> defects = new ArrayList<String>() {{
            add("Lamps");
            add("Day time running lamp (in addition to 1 above):");
            add("incorrectly positioned.");
        }};
        String deficiencyCategory = "major";
        ArrayList<String> vehicleConfiguration = new ArrayList<String>() {{
            add("psv");
            add("hgv");
        }};
        getDefects(id, defects, deficiencyCategory, vehicleConfiguration);
    }

    @Test
    @Title("CVSB - 11012 AC1 - Defect taxonomy updates are visible in the app 67.1.a")
    public void testDefects671a() {
        String id = "67.1.a";
        ArrayList<String> defects = new ArrayList<String>() {{
            add("Aim of Headlamps");
            add("In relation to the 0% Horizontal line, the beam horizontal cut-off line is not between the limits listed below.");
            add("Headlamp centres up to and including 850mm high Upper limit: All vehicles. 0.5%  Lower limit: All vehicles  4.0%");
        }};
        String deficiencyCategory = "major";
        ArrayList<String> vehicleConfiguration = new ArrayList<String>() {{
            add("psv");
            add("hgv");
        }};
        getDefects(id, defects, deficiencyCategory, vehicleConfiguration);
    }

    @Test
    @Title("CVSB - 11012 AC1 - Defect taxonomy updates are visible in the app 67.1.b")
    public void testDefects671b() {
        String id = "67.1.b";
        ArrayList<String> defects = new ArrayList<String>() {{
            add("Aim of Headlamps");
            add("In relation to the 0% Horizontal line, the beam horizontal cut-off line is not between the limits listed below.");
            add("Headlamp centres over 850mm high Upper limit: All vehicles. 1.25%  Lower limit: All vehicles 4.0%");
        }};
        String deficiencyCategory = "major";
        ArrayList<String> vehicleConfiguration = new ArrayList<String>() {{
            add("psv");
            add("hgv");
        }};
        getDefects(id, defects, deficiencyCategory, vehicleConfiguration);
    }

    @Test
    @Title("CVSB - 11012 AC1 - Defect taxonomy updates are visible in the app 67.2")
    public void testDefects672() {
        String id = "67.2";
        ArrayList<String> defects = new ArrayList<String>() {{
            add("Aim of Headlamps");
            add("The beam image contains:");
            add("a “Kick up” that is not visible on the screen");
        }};
        String deficiencyCategory = "major";
        ArrayList<String> vehicleConfiguration = new ArrayList<String>() {{
            add("psv");
            add("hgv");
        }};
        getDefects(id, defects, deficiencyCategory, vehicleConfiguration);
    }

    @Test
    @Title("CVSB - 11012 AC1 - Defect taxonomy updates are visible in the app 67.3")
    public void testDefects673() {
        String id = "67.3";
        ArrayList<String> defects = new ArrayList<String>() {{
            add("Aim of Headlamps");
            add("White light:");
            add("shows in the zone formed by the 0% vertical and 0.5% horizontal lines.");
        }};
        String deficiencyCategory = "major";
        ArrayList<String> vehicleConfiguration = new ArrayList<String>() {{
            add("psv");
            add("hgv");
        }};
        getDefects(id, defects, deficiencyCategory, vehicleConfiguration);
    }

    @Test
    @Title("CVSB - 11012 AC1 - Defect taxonomy updates are visible in the app 67.4")
    public void testDefects674() {
        String id = "67.4";
        ArrayList<String> defects = new ArrayList<String>() {{
            add("Aim of Headlamps");
            add("In relation to the 0% Horizontal line, the upper edge of the “Hot Spot” is not between the limits listed below.");
            add("All headlamp heights, Upper limit: All vehicles. 0%, Lower limit: All vehicles 4.0%");
        }};
        String deficiencyCategory = "major";
        ArrayList<String> vehicleConfiguration = new ArrayList<String>() {{
            add("psv");
            add("hgv");
        }};
        getDefects(id, defects, deficiencyCategory, vehicleConfiguration);
    }

    @Test
    @Title("CVSB - 11012 AC1 - Defect taxonomy updates are visible in the app 67.5")
    public void testDefects675() {
        String id = "67.5";
        ArrayList<String> defects = new ArrayList<String>() {{
            add("Aim of Headlamps");
            add("The right hand edge of the “Hot Spot”:");
            add("is to the right of the vertical 0% line, or more than 2% to the left of it.");
        }};
        String deficiencyCategory = "major";
        ArrayList<String> vehicleConfiguration = new ArrayList<String>() {{
            add("psv");
            add("hgv");
        }};
        getDefects(id, defects, deficiencyCategory, vehicleConfiguration);
    }

    @Test
    @Title("CVSB - 11012 AC1 - Defect taxonomy updates are visible in the app 67.6")
    public void testDefects676() {
        String id = "67.6";
        ArrayList<String> defects = new ArrayList<String>() {{
            add("Aim of Headlamps");
            add("A Headlamp");
            add("dips to the right (See note in Applications).");
        }};
        String deficiencyCategory = "major";
        ArrayList<String> vehicleConfiguration = new ArrayList<String>() {{
            add("psv");
            add("hgv");
        }};
        getDefects(id, defects, deficiencyCategory, vehicleConfiguration);
    }

    @Test
    @Title("CVSB - 11012 AC1 - Defect taxonomy updates are visible in the app 67.7.a")
    public void testDefects677a() {
        String id = "67.7.a";
        ArrayList<String> defects = new ArrayList<String>() {{
            add("Aim of Headlamps");
            add("In relation to the 0% Horizontal line, the Centre of the “Hot Spot” is not between the limits listed below.");
            add("Headlamp centres up to and including 850mm high, Upper limit: All vehicles. 0%, Lower limit: All vehicles. 2.0%.");
        }};
        String deficiencyCategory = "major";
        ArrayList<String> vehicleConfiguration = new ArrayList<String>() {{
            add("psv");
            add("hgv");
        }};
        getDefects(id, defects, deficiencyCategory, vehicleConfiguration);
    }

    @Test
    @Title("CVSB - 11012 AC1 - Defect taxonomy updates are visible in the app 67.7.b")
    public void testDefects677b() {
        String id = "67.7.b";
        ArrayList<String> defects = new ArrayList<String>() {{
            add("Aim of Headlamps");
            add("In relation to the 0% Horizontal line, the Centre of the “Hot Spot” is not between the limits listed below.");
            add("Headlamp centres over 850mm high, Upper limit: All vehicles. 0%, Lower limit: All vehicles. 2.75%.");
        }};
        String deficiencyCategory = "major";
        ArrayList<String> vehicleConfiguration = new ArrayList<String>() {{
            add("psv");
            add("hgv");
        }};
        getDefects(id, defects, deficiencyCategory, vehicleConfiguration);
    }

    @Test
    @Title("CVSB - 11012 AC1 - Defect taxonomy updates are visible in the app 67.8")
    public void testDefects678() {
        String id = "67.8";
        ArrayList<String> defects = new ArrayList<String>() {{
            add("Aim of Headlamps");
            add("In any case the centre of the “Hot Spot”");
            add("is to the right of the vertical 0% line or more than 2% to the left of it.");
        }};
        String deficiencyCategory = "major";
        ArrayList<String> vehicleConfiguration = new ArrayList<String>() {{
            add("psv");
            add("hgv");
        }};
        getDefects(id, defects, deficiencyCategory, vehicleConfiguration);
    }

    @Test
    @Title("CVSB - 11012 AC1 - Defect taxonomy updates are visible in the app 67.9")
    public void testDefects679() {
        String id = "67.9";
        ArrayList<String> defects = new ArrayList<String>() {{
            add("Aim of Headlamps");
            add("A Headlamp");
            add("dips to the right (see note in Application).");
        }};
        String deficiencyCategory = "major";
        ArrayList<String> vehicleConfiguration = new ArrayList<String>() {{
            add("psv");
            add("hgv");
        }};
        getDefects(id, defects, deficiencyCategory, vehicleConfiguration);
    }

    @Test
    @Title("CVSB - 11012 AC1 - Defect taxonomy updates are visible in the app 71.1.d.i")
    public void testDefects711di() {
        String id = "71.1.d.i";
        ArrayList<String> defects = new ArrayList<String>() {{
            add("Service Brake Performance");
            add("All Roller Brake Test Machines: With the service brake fully applied:");
            add("there is very little brake effort at any wheel.");
        }};
        String deficiencyCategory = "major";
        ArrayList<String> vehicleConfiguration = new ArrayList<String>() {{
            add("psv");
            add("hgv");
            add("trl");
        }};
        getDefects(id, defects, deficiencyCategory, vehicleConfiguration);
    }

    @Test
    @Title("CVSB - 11012 AC1 - Defect taxonomy updates are visible in the app 71.1.d.ii")
    public void testDefects711dii() {
        String id = "71.1.d.ii";
        ArrayList<String> defects = new ArrayList<String>() {{
            add("Service Brake Performance");
            add("All Roller Brake Test Machines: With the service brake fully applied:");
            add("there is no brake effort at any wheel.");
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
    @Title("CVSB - 11012 AC1 - Defect taxonomy updates are visible in the app 71.1.d.iii")
    public void testDefects711diii() {
        String id = "71.1.d.iii";
        ArrayList<String> defects = new ArrayList<String>() {{
            add("Service Brake Performance");
            add("All Roller Brake Test Machines: With the service brake fully applied:");
            add("braking effort from any wheel on an axle is less than 70% of the brake effort from another wheel on the same axle.");
        }};
        String deficiencyCategory = "major";
        ArrayList<String> vehicleConfiguration = new ArrayList<String>() {{
            add("psv");
            add("hgv");
            add("trl");
        }};
        getDefects(id, defects, deficiencyCategory, vehicleConfiguration);
    }

    @Test
    @Title("CVSB - 11012 AC1 - Defect taxonomy updates are visible in the app 71.1.d.iv")
    public void testDefects711div() {
        String id = "71.1.d.iv";
        ArrayList<String> defects = new ArrayList<String>() {{
            add("Service Brake Performance");
            add("All Roller Brake Test Machines: With the service brake fully applied:");
            add("braking effort from any wheel on an axle is less than 50% of the brake effort from another wheel on the same axle in the case of steered axles.");
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
    @Title("CVSB - 11012 AC1 - Defect taxonomy updates are visible in the app 71.1.d.v")
    public void testDefects711dv() {
        String id = "71.1.d.v";
        ArrayList<String> defects = new ArrayList<String>() {{
            add("Service Brake Performance");
            add("All Roller Brake Test Machines: With the service brake fully applied:");
            add("the specified brake effort is not met.");
        }};
        String deficiencyCategory = "dangerous";
        ArrayList<String> vehicleConfiguration = new ArrayList<String>() {{
            add("psv");
            add("hgv");
            add("trl");
        }};
        getDefects(id, defects, deficiencyCategory, vehicleConfiguration);
    }

}


