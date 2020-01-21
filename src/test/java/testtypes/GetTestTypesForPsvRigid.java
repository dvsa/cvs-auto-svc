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
        List<String> testType = new ArrayList<>();
        List<String> vehicleConfiguration = new ArrayList<>();
        testType.add("Annual test");
        vehicleConfiguration.add("articulated");
        vehicleConfiguration.add("rigid");
        testTypeForArticulatedPSV(testType,vehicleConfiguration);
    }

    @Title("CVSB - 8232 AC1 - DISPLAYING THE CORRECT TEST TYPES IN THE APP FOR ARTICULATED PSV's(Retest -> Paid -> Any PSV)")
    @Test
    public void getRetestPaidAnyPsvTestType(){
        List<String> testType = new ArrayList<>();
        List<String> vehicleConfiguration = new ArrayList<>();
        testType.add("Retest");
        testType.add("Paid");
        testType.add("Any PSV");
        vehicleConfiguration.add("articulated");
        vehicleConfiguration.add("rigid");
        testTypeForArticulatedPSV(testType,vehicleConfiguration);
    }

    @Title("CVSB - 8232 AC1 - DISPLAYING THE CORRECT TEST TYPES IN THE APP FOR ARTICULATED PSV's(Retest -> Part Paid -> Any PSV)")
    @Test
    public void getRetestPartPaidAnyPsvTestType(){
        List<String> testType = new ArrayList<>();
        List<String> vehicleConfiguration = new ArrayList<>();
        testType.add("Retest");
        testType.add("Part Paid");
        testType.add("Any PSV");
        vehicleConfiguration.add("articulated");
        vehicleConfiguration.add("rigid");
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




    @Title("CVSB - 8232 AC1 - DISPLAYING THE CORRECT TEST TYPES IN THE APP FOR ARTICULATED PSV's(Class 6A -> Annual test)")
    @Test
    public void getClass6aAnnualTestType(){
        List<String> testType = new ArrayList<>();
        List<String> vehicleConfiguration = new ArrayList<>();
        testType.add("Class 6A");
        testType.add("Annual test");
        vehicleConfiguration.add("articulated");
        vehicleConfiguration.add("rigid");
        testTypeForArticulatedPSV(testType,vehicleConfiguration);
    }

}
