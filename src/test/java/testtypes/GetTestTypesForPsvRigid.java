package testtypes;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import steps.TestTypeSteps;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(SerenityRunner.class)
public class GetTestTypesForPsvRigid {

    @Steps
    static TestTypeSteps testTypeSteps;

    private final static List<String> vehicleConfigurationRigidArticulated = new ArrayList<String>() {{
        add("rigid");
        add("articulated");
    }};

    private final static List<String> vehicleConfigurationArticulated = new ArrayList<String>() {{
        add("articulated");
    }};

    /**
     * @param testTypesList        represent the list from the root category to the leaf subcategory
     * @param vehicleConfiguration represent the configuration of the vehicle that can be Articulated or Rigid/Articulated
     */
    public void testTypeForArticulatedPSV(List<String> testTypesList, List<String> vehicleConfiguration) {
        ObjectMapper mapper = new ObjectMapper();
        Response testTypeWithData = testTypeSteps.getTestTypesWithData();
        try {
            JSONArray allTestTypes = new JSONArray(testTypeWithData.asString());
            for (String testTypeElement : testTypesList) {
                boolean containTestType = false;
                for (int i = 0; i < allTestTypes.length(); i++) {
                    JSONObject testType = allTestTypes.getJSONObject(i);
                    if ((testTypeElement.trim()).equals(testType.getString("name"))) {
                        containTestType = true;
                        String forVehicleType = testType.getString("forVehicleType");
                        List<String> forVehicleTypeList = Arrays.asList(mapper.readValue(forVehicleType, String[].class));
                        if (forVehicleTypeList.contains("psv")) {
                            if (!testType.isNull("nextTestTypesOrCategories")) {
                                allTestTypes = testType.getJSONArray("nextTestTypesOrCategories");
                                break;
                            } else {
                                String forVehicleConfiguration = testType.getString("forVehicleConfiguration");
                                List<String> vehicleTypesList = Arrays.asList(mapper.readValue(forVehicleConfiguration, String[].class));
                                for (String vehicleConfigurationElement : vehicleConfiguration) {
                                    assert (vehicleTypesList.contains(vehicleConfigurationElement));
                                }
                            }
                        }

                    }
                }
                assert containTestType;
            }

        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }
    }

    @Title("CVSB - 8232 AC1 - DISPLAYING THE CORRECT TEST TYPES IN THE APP FOR ARTICULATED PSV's(Annual)")
    @Test
    public void getAnnualTestType() {
        List<String> testType = new ArrayList<String>() {{
            add("Annual test");
        }};
        testTypeForArticulatedPSV(testType, vehicleConfigurationArticulated);
    }

    @Title("CVSB - 8232 AC1 - DISPLAYING THE CORRECT TEST TYPES IN THE APP FOR ARTICULATED PSV's(Retest -> Paid -> Any PSV)")
    @Test
    public void getRetestPaidAnyPsvTestType() {
        List<String> testType = new ArrayList<String>() {{
            add("Retest");
            add("Paid");
            add("Any PSV");
        }};
        testTypeForArticulatedPSV(testType, vehicleConfigurationArticulated);
    }

    @Title("CVSB - 8232 AC1 - DISPLAYING THE CORRECT TEST TYPES IN THE APP FOR ARTICULATED PSV's(Retest -> Part Paid -> Any PSV)")
    @Test
    public void getRetestPartPaidAnyPsvTestType() {
        List<String> testType = new ArrayList<String>() {{
            add("Retest");
            add("Part paid");
            add("Any PSV");
        }};
        testTypeForArticulatedPSV(testType, vehicleConfigurationArticulated);
    }

    @Title("CVSB - 8232 AC1 - DISPLAYING THE CORRECT TEST TYPES IN THE APP FOR ARTICULATED PSV's(Class 6A -> Annual test)")
    @Test
    public void getClass6aAnnualTestType() {
        List<String> testType = new ArrayList<String>() {{
            add("Class 6A");
            add("Annual test");
        }};
        testTypeForArticulatedPSV(testType, vehicleConfigurationArticulated);
    }


    @Title("CVSB - 8232 AC1 - DISPLAYING THE CORRECT TEST TYPES IN THE APP FOR ARTICULATED PSV's(Class 6A -> First Test)")
    @Test
    public void getClass6FirstTestType() {
        List<String> testType = new ArrayList<String>() {{
            add("Class 6A");
            add("First test");
        }};
        testTypeForArticulatedPSV(testType, vehicleConfigurationRigidArticulated);
    }

    @Title("CVSB - 8232 AC1 - DISPLAYING THE CORRECT TEST TYPES IN THE APP FOR ARTICULATED PSV's(Retest -> Paid -> Class 6A (Seatbelt installation check))")
    @Test
    public void getRetestPaidClass6aRetest() {
        List<String> testType = new ArrayList<String>() {{
            add("Retest");
            add("Paid");
            add("Class 6A (seatbelt installation check)");
        }};
        testTypeForArticulatedPSV(testType, vehicleConfigurationRigidArticulated);
    }

    @Title("CVSB - 8232 AC1 - DISPLAYING THE CORRECT TEST TYPES IN THE APP FOR ARTICULATED PSV's(Prohibition Clearance -> Any PSV -> Full Inspection/full fee -> Without certification))")
    @Test
    public void getProhibitionClearanceAnyPSVFullInspectionWithoutCertification() {
        List<String> testType = new ArrayList<String>() {{
            add("Prohibition clearance");
            add("Any PSV");
            add("Full inspection/ full fee");
            add("Without certification");
        }};
        testTypeForArticulatedPSV(testType, vehicleConfigurationRigidArticulated);
    }

    @Title("CVSB - 8232 AC1 - DISPLAYING THE CORRECT TEST TYPES IN THE APP FOR ARTICULATED PSV's(Prohibition Clearance -> Any PSV -> Full inspection/full fee -> With certification)")
    @Test
    public void getProhibitionClearanceAnyPSVFullInspectionWithCertification() {
        List<String> testType = new ArrayList<String>() {{
            add("Prohibition clearance");
            add("Any PSV");
            add("Full inspection/ full fee");
            add("With certification");
        }};
        testTypeForArticulatedPSV(testType, vehicleConfigurationRigidArticulated);
    }

    @Title("CVSB - 8232 AC1 - DISPLAYING THE CORRECT TEST TYPES IN THE APP FOR ARTICULATED PSV's(Prohibition Clearance -> Any PSV -> PG9 retest -> Paid -> With certification)")
    @Test
    public void getProhibitionClearanceAnyPSVPG9RetestPaidWithCertification() {
        List<String> testType = new ArrayList<String>() {{
            add("Prohibition clearance");
            add("Any PSV");
            add("PG9 retest");
            add("Paid");
            add("With certification");
        }};
        testTypeForArticulatedPSV(testType, vehicleConfigurationRigidArticulated);
    }

    @Title("CVSB - 8232 AC1 - DISPLAYING THE CORRECT TEST TYPES IN THE APP FOR ARTICULATED PSV's(Prohibition Clearance -> Any PSV -> PG9 retest -> Paid -> Without certification)")
    @Test
    public void getProhibitionClearanceAnyPSVPG9RetestPaidWithoutCertification() {
        List<String> testType = new ArrayList<String>() {{
            add("Prohibition clearance");
            add("Any PSV");
            add("PG9 retest");
            add("Paid");
            add("Without certification");
        }};
        testTypeForArticulatedPSV(testType, vehicleConfigurationRigidArticulated);
    }

    @Title("CVSB - 8232 AC1 - DISPLAYING THE CORRECT TEST TYPES IN THE APP FOR ARTICULATED PSV's(Prohibition Clearance -> Any PSV -> PG9 retest ->Part paid -> Without certification)")
    @Test
    public void getProhibitionClearanceAnyPSVPG9RetestPartPaidWithoutCertification() {
        List<String> testType = new ArrayList<String>() {{
            add("Prohibition clearance");
            add("Any PSV");
            add("PG9 retest");
            add("Part paid");
            add("Without certification");
        }};
        testTypeForArticulatedPSV(testType, vehicleConfigurationRigidArticulated);
    }

    @Title("CVSB - 8232 AC1 - DISPLAYING THE CORRECT TEST TYPES IN THE APP FOR ARTICULATED PSV's(Prohibition Clearance -> Any PSV -> PG9 retest ->Part paid -> With certification)")
    @Test
    public void getProhibitionClearanceAnyPSVPG9RetestPartPaidWithCertification() {
        List<String> testType = new ArrayList<String>() {{
            add("Prohibition clearance");
            add("Any PSV");
            add("PG9 retest");
            add("Part paid");
            add("With certification");
        }};
        testTypeForArticulatedPSV(testType, vehicleConfigurationRigidArticulated);
    }

    @Title("CVSB - 8232 AC1 - DISPLAYING THE CORRECT TEST TYPES IN THE APP FOR ARTICULATED PSV's(Prohibition Clearance -> Any PSV -> Part inspection/ part fee)")
    @Test
    public void getProhibitionClearanceAnyPSVPartInspection() {
        List<String> testType = new ArrayList<String>() {{
            add("Prohibition clearance");
            add("Any PSV");
            add("Part inspection/ part fee");
        }};
        testTypeForArticulatedPSV(testType, vehicleConfigurationRigidArticulated);
    }

    @Title("CVSB - 8232 AC1 - DISPLAYING THE CORRECT TEST TYPES IN THE APP FOR ARTICULATED PSV's(Prohibition Clearance -> Any PSV -> Full inspection/ part fee)")
    @Test
    public void getProhibitionClearanceAnyPSVFullInspection() {
        List<String> testType = new ArrayList<String>() {{
            add("Prohibition clearance");
            add("Any PSV");
            add("Full inspection/ part fee");
        }};
        testTypeForArticulatedPSV(testType, vehicleConfigurationRigidArticulated);
    }

    @Title("CVSB - 8232 AC1 - DISPLAYING THE CORRECT TEST TYPES IN THE APP FOR ARTICULATED PSV's(Prohibition Clearance -> Class 6A (seatbelt installation check) -> PG9 retest)")
    @Test
    public void getProhibitionClearanceClass6aPG9Retest() {
        List<String> testType = new ArrayList<String>() {{
            add("Prohibition clearance");
            add("Class 6A (seatbelt installation check)");
            add("PG9 retest");
        }};
        testTypeForArticulatedPSV(testType, vehicleConfigurationRigidArticulated);
    }

    @Title("CVSB - 8232 AC1 - DISPLAYING THE CORRECT TEST TYPES IN THE APP FOR ARTICULATED PSV's(Prohibition Clearance -> Class 6A (seatbelt installation check) -> Full inspection / full fee)")
    @Test
    public void getProhibitionClearanceClass6aFullInspection() {
        List<String> testType = new ArrayList<String>() {{
            add("Prohibition clearance");
            add("Class 6A (seatbelt installation check)");
            add("Full inspection / full fee");
        }};
        testTypeForArticulatedPSV(testType, vehicleConfigurationRigidArticulated);
    }

    @Title("CVSB - 8232 AC1 - DISPLAYING THE CORRECT TEST TYPES IN THE APP FOR ARTICULATED PSV's(Prohibition Clearance -> Class 6A (No seatbelt installation check) -> PG9 retest)")
    @Test
    public void getProhibitionClearanceClass6aNoSeatbeltPG9Retest() {
        List<String> testType = new ArrayList<String>() {{
            add("Prohibition clearance");
            add("Class 6A (No seatbelt installation check)");
            add("PG9 retest");
        }};
        testTypeForArticulatedPSV(testType, vehicleConfigurationRigidArticulated);
    }


    @Title("CVSB - 8232 AC1 - DISPLAYING THE CORRECT TEST TYPES IN THE APP FOR ARTICULATED PSV's(Voluntary -> Brake test)")
    @Test
    public void getVoluntaryBrakeTestType() {
        List<String> testType = new ArrayList<String>() {{
            add("Voluntary test");
            add("Brake test");
        }};
        testTypeForArticulatedPSV(testType, vehicleConfigurationRigidArticulated);
    }

    @Title("CVSB - 8232 AC1 - DISPLAYING THE CORRECT TEST TYPES IN THE APP FOR ARTICULATED PSV's(Voluntary -> Headlamp aim test)")
    @Test
    public void getVoluntaryHeadlampAimTestType() {
        List<String> testType = new ArrayList<String>() {{
            add("Voluntary test");
            add("Headlamp aim test");
        }};
        testTypeForArticulatedPSV(testType, vehicleConfigurationRigidArticulated);
    }

    @Title("CVSB - 8232 AC1 - DISPLAYING THE CORRECT TEST TYPES IN THE APP FOR ARTICULATED PSV's(Voluntary -> Smoke test)")
    @Test
    public void getVoluntarySmokeTestType() {
        List<String> testType = new ArrayList<String>() {{
            add("Voluntary test");
            add("Smoke test");
        }};
        testTypeForArticulatedPSV(testType, vehicleConfigurationRigidArticulated);
    }

    @Title("CVSB - 8232 AC1 - DISPLAYING THE CORRECT TEST TYPES IN THE APP FOR ARTICULATED PSV's(Voluntary -> Multi-check)")
    @Test
    public void getVoluntaryMultiCheckTestType() {
        List<String> testType = new ArrayList<String>() {{
            add("Voluntary test");
            add("Multi - check");
        }};
        testTypeForArticulatedPSV(testType, vehicleConfigurationRigidArticulated);
    }

    @Title("CVSB - 8232 AC1 - DISPLAYING THE CORRECT TEST TYPES IN THE APP FOR ARTICULATED PSV's(Voluntary -> Speed limiter check)")
    @Test
    public void getVoluntarySpeedLimiterCheckTestType() {
        List<String> testType = new ArrayList<String>() {{
            add("Voluntary test");
            add("Speed limiter check");
        }};

        testTypeForArticulatedPSV(testType, vehicleConfigurationRigidArticulated);
    }

    @Title("CVSB - 8232 AC1 - DISPLAYING THE CORRECT TEST TYPES IN THE APP FOR ARTICULATED PSV's(Voluntary -> Tempo 100)")
    @Test
    public void getVoluntaryTempo100TestType() {
        List<String> testType = new ArrayList<String>() {{
            add("Voluntary test");
            add("Tempo 100");
        }};

        testTypeForArticulatedPSV(testType, vehicleConfigurationRigidArticulated);
    }

    @Title("CVSB - 8232 AC1 - DISPLAYING THE CORRECT TEST TYPES IN THE APP FOR ARTICULATED PSV's(Technical test -> Notifiable alteration)")
    @Test
    public void getTechnicalTestNotifiableAlterationTestType() {
        List<String> testType = new ArrayList<String>() {{
            add("Technical test");
            add("Notifiable alteration");
        }};
        testTypeForArticulatedPSV(testType, vehicleConfigurationRigidArticulated);
    }

    @Title("CVSB - 8232 AC1 - DISPLAYING THE CORRECT TEST TYPES IN THE APP FOR ARTICULATED PSV's(Technical test -> LEC)")
    @Test
    public void getTechnicalTestLecTestType() {
        List<String> testType = new ArrayList<String>() {{
            add("Technical test");
            add("LEC");
        }};
        testTypeForArticulatedPSV(testType, vehicleConfigurationRigidArticulated);
    }
}
