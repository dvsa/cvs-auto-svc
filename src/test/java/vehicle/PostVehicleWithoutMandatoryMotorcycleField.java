//package vehicle;
//
//import data.GenericData;
//import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
//import net.thucydides.core.annotations.Steps;
//import net.thucydides.core.annotations.Title;
//import net.thucydides.core.annotations.WithTag;
//import net.thucydides.junit.annotations.TestData;
//import org.apache.http.HttpStatus;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import steps.*;
//import util.JsonPathAlteration;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Collection;
//import java.util.List;
//
//@RunWith(SerenityParameterizedRunner.class)
//public class PostVehicleWithoutMandatoryMotorcycleField {
//
//    @Steps
//    VehicleTechnicalRecordsSteps vehicleTechnicalRecordsSteps;
//
//    @TestData
//    public static Collection<Object[]> testData(){
//        return Arrays.asList(new Object[][]{
//                {"$.vin"},
//                {"$.primaryVrm"},
//                {"$.techRecord[0].vehicleType"},
//                {"$.techRecord[0].noOfAxles"},
//                {"$.techRecord[0].vehicleClass.description"},
//                {"$.techRecord[0].numberOfWheelsDriven"},
//                {"$.techRecord[0].reasonForCreation"},
//        });
//    }
//
//    private final String jsonPath;
//
//    public PostVehicleWithoutMandatoryMotorcycleField(String jsonPath) {
//        this.jsonPath = jsonPath;
//    }
//
//    @WithTag("Vtm")
//    @Title("CVSB-10328 - AC2. POST: Attempt to create a new vehicle without a mandatory field - MOTORCYCLE")
//    @Test
//    public void testPostValidateRequestWithoutMandatoryMotorcycleField() {
//        // TEST SETUP
//        // generate random Vin
//        String randomVin = GenericData.generateRandomVin();
//        // generate random Vin
//        String randomSystemNumber = GenericData.generateRandomSystemNumber();
//        // generate random Vrm
//        String randomVrm = GenericData.generateRandomVrm();
//        // read post request body from file
//        String postRequestBody = GenericData.readJsonValueFromFile("technical-records_post_payload_motorcycle_10328.json", "$");
//        // create alteration to change Vin in the post request body with the random generated Vin
//        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
//        // create alteration to change systemNumber in the post request body with the random generated systemNumber
//        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$.systemNumber", randomSystemNumber, "", "REPLACE");
//        // create alteration to change primary vrm in the request body with the random generated primary vrm
//        JsonPathAlteration alterationVrm = new JsonPathAlteration("$.primaryVrm", randomVrm, "", "REPLACE");
//
//        // initialize the alterations list with both declared alterations
//        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(alterationVin, alterationVrm, alterationSystemNumber));
//        JsonPathAlteration restriction = new JsonPathAlteration(jsonPath, "", "", "DELETE");
//        alterations.add(restriction);
//        // validate 400 response when making POST request without a mandatory field
//        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBody, alterations);
//        vehicleTechnicalRecordsSteps.statusCodeShouldBe(HttpStatus.SC_BAD_REQUEST);
//    }
//}
