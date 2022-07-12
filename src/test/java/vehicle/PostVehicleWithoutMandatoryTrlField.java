//package vehicle;
//
//import data.GenericData;
//import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
//import net.thucydides.core.annotations.Steps;
//import net.thucydides.core.annotations.Title;
//import net.thucydides.core.annotations.WithTag;
//import net.thucydides.junit.annotations.TestData;
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
//public class PostVehicleWithoutMandatoryTrlField {
//
//    @Steps
//    VehicleTechnicalRecordsSteps vehicleTechnicalRecordsSteps;
//
//    @TestData
//    public static Collection<Object[]> testData(){
//        return Arrays.asList(new Object[][]{
//                {"$.vin"},
//                {"$.techRecord[0].vehicleType"},
//                {"$.techRecord[0].manufactureYear"},
//                {"$.techRecord[0].noOfAxles"},
//                {"$.techRecord[0].brakes.dtpNumber"},
//                {"$.techRecord[0].roadFriendly" },
//                {"$.techRecord[0].vehicleClass.description" },
//                {"$.techRecord[0].vehicleConfiguration"},
//                {"$.techRecord[0].euVehicleCategory"},
//                {"$.techRecord[0].approvalType"},
//                {"$.techRecord[0].make"},
//                {"$.techRecord[0].model"},
//                {"$.techRecord[0].bodyType.description"},
//                {"$.techRecord[0].grossGbWeight"},
//                {"$.techRecord[0].grossDesignWeight"},
//                {"$.techRecord[0].axles[0].weights.gbWeight"},
//                {"$.techRecord[0].axles[0].weights.designWeight"},
//                {"$.techRecord[0].axles[0].tyres.tyreCode"},
//                {"$.techRecord[0].axles[0].tyres.tyreSize"},
//                {"$.techRecord[0].axles[0].tyres.fitmentCode"},
//                {"$.techRecord[0].brakes.antilockBrakingSystem"},
//                {"$.techRecord[0].dimensions.length"},
//                {"$.techRecord[0].dimensions.width"},
//                {"$.techRecord[0].frontAxleToRearAxle"},
//                {"$.techRecord[0].rearAxleToRearTrl"},
//                {"$.techRecord[0].couplingCenterToRearAxleMin"},
//                {"$.techRecord[0].couplingCenterToRearAxleMax"},
//                {"$.techRecord[0].couplingCenterToRearTrlMin"},
//                {"$.techRecord[0].couplingCenterToRearTrlMax"},
//                {"$.techRecord[0].centreOfRearmostAxleToRearOfTrl"},
//                {"$.techRecord[0].applicantDetails.name"},
//                {"$.techRecord[0].applicantDetails.address1"},
//                {"$.techRecord[0].applicantDetails.address2"},
//                {"$.techRecord[0].applicantDetails.postTown"},
//                {"$.techRecord[0].purchaserDetails.name"},
//                {"$.techRecord[0].purchaserDetails.address1"},
//                {"$.techRecord[0].purchaserDetails.address2"},
//                {"$.techRecord[0].purchaserDetails.postTown"},
//                {"$.techRecord[0].manufacturerDetails.name"},
//                {"$.techRecord[0].manufacturerDetails.address1"},
//                {"$.techRecord[0].manufacturerDetails.address2"},
//                {"$.techRecord[0].manufacturerDetails.postTown"},
//                {"$.techRecord[0].reasonForCreation"}
//        });
//    }
//
//    private final String jsonPath;
//
//    public PostVehicleWithoutMandatoryTrlField(String jsonPath) {
//        this.jsonPath = jsonPath;
//    }
//
//    @WithTag("Vtm")
//    @Title("CVSB - 10246 - AC1 - Attempt to create a new vehicle without a mandatory field")
//    @Test
//    public void testValidateRequestWithoutMandatoryTrlField() {
//        // TEST SETUP
//        // generate random Vin
//        String randomVin = GenericData.generateRandomVin();
//        // generate random Vin
//        String randomSystemNumber = GenericData.generateRandomSystemNumber();
//        // generate random Vrm
//        String randomVrm = GenericData.generateRandomVrm();
//        // read post request body from file
//        String postRequestBodyHgv = GenericData.readJsonValueFromFile("technical-records_trl_all_fields.json", "$");
//        // create alteration to change Vin in the post request body with the random generated Vin
//        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
//        // create alteration to change systemNumber in the post request body with the random generated systemNumber
//        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$.systemNumber", randomSystemNumber, "", "REPLACE");
//        // create alteration to change primary vrm in the request body with the random generated primary vrm
//        JsonPathAlteration alterationVrm = new JsonPathAlteration("$.primaryVrm", randomVrm, "", "REPLACE");
//
//        // initialize the alterations list with both declared alterations
//        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(alterationVin, alterationVrm, alterationSystemNumber));
//
//
//        // validate 400 response when making POST request without a mandatory field
//        JsonPathAlteration restriction = new JsonPathAlteration(jsonPath, "", "", "DELETE");
//        alterations.add(restriction);
//        vehicleTechnicalRecordsSteps.postVehicleTechnicalRecordsWithAlterations(postRequestBodyHgv, alterations);
//        vehicleTechnicalRecordsSteps.statusCodeShouldBe(400);
//    }
//}
