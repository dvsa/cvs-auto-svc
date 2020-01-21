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

    @Title("CVSB - 8232 AC1 - DISPLAYING THE CORRECT TEST TYPES IN THE APP FOR ARTICULATED PSV's(Annual)")
    @Test
    public void getAnnualTestType(){
        List<String> testType = new ArrayList<String>(){{
            add("articulated");
            add("rigid");
        }};
        List<String> vehicleConfiguration = new ArrayList<String>(){{
            add("Annual test");
        }};
        testTypeForArticulatedPSV(testType,vehicleConfiguration);
    }

    @Title("CVSB - 8232 AC1 - DISPLAYING THE CORRECT TEST TYPES IN THE APP FOR ARTICULATED PSV's(Retest -> Paid -> Any PSV)")
    @Test
    public void getRetestPaidAnyPsvTestType(){
        List<String> testType = new ArrayList<String>(){{
            add("articulated");
            add("rigid");
        }};
        List<String> vehicleConfiguration = new ArrayList<String>(){{
            add("Retest");
            add("Paid");
            add("Any PSV");
        }};
        testTypeForArticulatedPSV(testType,vehicleConfiguration);
    }

    @Title("CVSB - 8232 AC1 - DISPLAYING THE CORRECT TEST TYPES IN THE APP FOR ARTICULATED PSV's(Retest -> Part Paid -> Any PSV)")
    @Test
    public void getRetestPartPaidAnyPsvTestType(){
        List<String> testType = new ArrayList<String>(){{
            add("articulated");
            add("rigid");
        }};
        List<String> vehicleConfiguration = new ArrayList<String>(){{
            add("Retest");
            add("Part Paid");
            add("Any PSV");
        }};
        testTypeForArticulatedPSV(testType,vehicleConfiguration);
    }

    @Title("CVSB - 8232 AC1 - DISPLAYING THE CORRECT TEST TYPES IN THE APP FOR ARTICULATED PSV's(Class 6A -> Annual test)")
    @Test
    public void getClass6aAnnualTestType(){
        List<String> testType = new ArrayList<String>(){{
            add("articulated");
            add("rigid");
        }};
        List<String> vehicleConfiguration = new ArrayList<String>(){{
            add("Class 6A");
            add("Annual test");
        }};
        testTypeForArticulatedPSV(testType,vehicleConfiguration);
    }

    @Title("CVSB - 8232 AC1 - DISPLAYING THE CORRECT TEST TYPES IN THE APP FOR ARTICULATED PSV's(Voluntary -> Brake test)")
    @Test
    public void getVoluntaryBrakeTestType(){
        List<String> testType = new ArrayList<String>(){{
            add("articulated");
            add("rigid");
        }};
        List<String> vehicleConfiguration = new ArrayList<String>(){{
            add("Voluntary");
            add("Brake test");
        }};
        testTypeForArticulatedPSV(testType,vehicleConfiguration);
    }

    @Title("CVSB - 8232 AC1 - DISPLAYING THE CORRECT TEST TYPES IN THE APP FOR ARTICULATED PSV's(Voluntary -> Headlamp aim test)")
    @Test
    public void getVoluntaryHeadlampAimTestType(){
        List<String> testType = new ArrayList<String>(){{
            add("articulated");
            add("rigid");
        }};
        List<String> vehicleConfiguration = new ArrayList<String>(){{
            add("Voluntary");
            add("Headlamp aim test");
        }};
        testTypeForArticulatedPSV(testType,vehicleConfiguration);
    }

    @Title("CVSB - 8232 AC1 - DISPLAYING THE CORRECT TEST TYPES IN THE APP FOR ARTICULATED PSV's(Voluntary -> Smoke test)")
    @Test
    public void getVoluntarySmokeTestType(){
        List<String> testType = new ArrayList<String>(){{
            add("articulated");
            add("rigid");
        }};
        List<String> vehicleConfiguration = new ArrayList<String>(){{
            add("Voluntary");
            add("Smoke test");
        }};
        testTypeForArticulatedPSV(testType,vehicleConfiguration);
    }

    @Title("CVSB - 8232 AC1 - DISPLAYING THE CORRECT TEST TYPES IN THE APP FOR ARTICULATED PSV's(Voluntary -> Multi-check)")
    @Test
    public void getVoluntaryMultiCheckTestType(){
        List<String> testType = new ArrayList<String>(){{
            add("articulated");
            add("rigid");
        }};
        List<String> vehicleConfiguration = new ArrayList<String>(){{
            add("Voluntary");
            add("Multi-check");
        }};
        testTypeForArticulatedPSV(testType,vehicleConfiguration);
    }

    @Title("CVSB - 8232 AC1 - DISPLAYING THE CORRECT TEST TYPES IN THE APP FOR ARTICULATED PSV's(Voluntary -> Speed limiter check)")
    @Test
    public void getVoluntarySpeedLimiterCheckTestType(){
        List<String> testType = new ArrayList<String>(){{
            add("articulated");
            add("rigid");
        }};
        List<String> vehicleConfiguration = new ArrayList<String>(){{
            add("Voluntary");
            add("Speed limiter check");
        }};
        testTypeForArticulatedPSV(testType,vehicleConfiguration);
    }

    @Title("CVSB - 8232 AC1 - DISPLAYING THE CORRECT TEST TYPES IN THE APP FOR ARTICULATED PSV's(Voluntary -> Tempo 100)")
    @Test
    public void getVoluntaryTempo100TestType(){
        List<String> testType = new ArrayList<String>(){{
            add("articulated");
            add("rigid");
        }};
        List<String> vehicleConfiguration = new ArrayList<String>(){{
            add("Voluntary");
            add("Tempo 100");
        }};
        testTypeForArticulatedPSV(testType,vehicleConfiguration);
    }

    @Title("CVSB - 8232 AC1 - DISPLAYING THE CORRECT TEST TYPES IN THE APP FOR ARTICULATED PSV's(Technical test -> Notifiable alteration)")
    @Test
    public void getTechnicalTestNotifiableAlterationTestType(){
        List<String> testType = new ArrayList<String>(){{
            add("articulated");
            add("rigid");
        }};
        List<String> vehicleConfiguration = new ArrayList<String>(){{
            add("Technical test");
            add("Notifiable alteration");
        }};
        testTypeForArticulatedPSV(testType,vehicleConfiguration);
    }

    @Title("CVSB - 8232 AC1 - DISPLAYING THE CORRECT TEST TYPES IN THE APP FOR ARTICULATED PSV's(Technical test -> LEC -> With linked test)")
    @Test
    public void getTechnicalTestLecWithLinkedTestType(){
        List<String> testType = new ArrayList<String>(){{
            add("articulated");
            add("rigid");
        }};
        List<String> vehicleConfiguration = new ArrayList<String>(){{
            add("Technical test");
            add("LEC");
            add("With linked test");
        }};
        testTypeForArticulatedPSV(testType,vehicleConfiguration);
    }

    @Title("CVSB - 8232 AC1 - DISPLAYING THE CORRECT TEST TYPES IN THE APP FOR ARTICULATED PSV's(Technical test -> LEC -> Without linked test)")
    @Test
    public void getTechnicalTestLecWithoutLinkedTestType(){
        List<String> testType = new ArrayList<String>(){{
            add("articulated");
            add("rigid");
        }};
        List<String> vehicleConfiguration = new ArrayList<String>(){{
            add("Technical test");
            add("LEC");
            add("Without linked test");
        }};
        testTypeForArticulatedPSV(testType,vehicleConfiguration);
    }


    public void testTypeForArticulatedPSV(List<String> testTypesList, List<String> vehicleConfiguration){
        ObjectMapper mapper = new ObjectMapper();
        Response testTypeWithData = testTypeSteps.getTestTypesWithData();
        try {
            JSONArray allTestTypes = new JSONArray(testTypeWithData.asString());
            for(String testTypeElement : testTypesList) {
                for (int i = 0; i < allTestTypes.length(); i++) {
                    JSONObject testType = allTestTypes.getJSONObject(i);
                    if(testTypeElement.equals(testType.getString("name"))){
                        String forVehicleType = testType.getString("forVehicleType");
                        List<String> forVehicleTypeList = Arrays.asList(mapper.readValue(forVehicleType, String[].class));
                        if(forVehicleTypeList.contains("psv")) {
                            if (!testType.isNull("nextTestTypesOrCategories")) {
                                allTestTypes = testType.getJSONArray("nextTestTypesOrCategories");
                                break;
                            }else{
                                String forVehicleConfiguration = testType.getString("forVehicleConfiguration");
                                List<String> vehicleTypesList = Arrays.asList(mapper.readValue(forVehicleConfiguration, String[].class));
                                for(String vehicleConfigurationElement : vehicleConfiguration){
                                    assert (vehicleTypesList.contains(vehicleConfigurationElement));
                                }
                            }
                        }
                    }
                }
            }

        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }
    }

}
