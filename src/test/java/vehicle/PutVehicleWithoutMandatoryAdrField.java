//package vehicle;
//
//import data.GenericData;
//import io.restassured.http.ContentType;
//import io.restassured.response.Response;
//import junit.framework.TestCase;
//import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
//import net.thucydides.core.annotations.Steps;
//import net.thucydides.core.annotations.Title;
//import net.thucydides.core.annotations.WithTag;
//import net.thucydides.junit.annotations.TestData;
//import org.junit.Assert;
//import org.junit.BeforeClass;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import steps.*;
//import util.BasePathFilter;
//import util.JsonPathAlteration;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Collection;
//import java.util.List;
//
//import static io.restassured.RestAssured.given;
//import static util.WriterReader.saveUtils;
//
//@RunWith(SerenityParameterizedRunner.class)
//public class PutVehicleWithoutMandatoryAdrField extends TestCase {
//
//    static String randomVin;
//
//    @BeforeClass
//    public static void createRecord() {
//        // TEST SETUP
//        // generate random Vin
//        randomVin = GenericData.generateRandomVin();
//        // generate random Vrm
//        String randomVrm = GenericData.generateRandomVrm();
//        // read post request body from file
//        String postRequestBodyHgv = GenericData.readJsonValueFromFile
//                ("technical-records_hgv_all_fields_with_adr_details.json", "$");
//        // create alteration to change Vin in the post request body with the random generated Vin
//        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
//        // create alteration to change primary vrm in the request body with the random generated primary vrm
//        JsonPathAlteration alterationVrm = new JsonPathAlteration("$.primaryVrm", randomVrm, "", "REPLACE");
//
//        // initialize the alterations list with both declared alterations
//        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(alterationVin, alterationVrm));
//
//        String alteredBody = GenericData.applyJsonAlterations(postRequestBodyHgv, alterations);
//        //putRequestBodyHgv = alteredBody;
//        Response response = given().filters(new BasePathFilter())
//                .contentType(ContentType.JSON)
//                .body(alteredBody)
//                .log().method().log().uri().log().body()
//                .post("/vehicles");
//
//        if (response.getStatusCode() == 401 || response.getStatusCode() == 403) {
//            saveUtils();
//            response = given().filters(new BasePathFilter())
//                    .contentType(ContentType.JSON)
//                    .body(alteredBody)
//                    .log().method().log().uri().log().body()
//                    .post("/vehicles");
//        }
//        response.prettyPrint();
//        Assert.assertEquals(201, response.getStatusCode());
//    }
//
//    @Steps
//    VehicleTechnicalRecordsSteps vehicleTechnicalRecordsSteps;
//
//    @TestData
//    public static Collection<Object[]> testData(){
//        return Arrays.asList(new Object[][]{
//                {"$.techRecord[0].adrDetails.vehicleDetails.type"},
//                {"$.techRecord[0].adrDetails.vehicleDetails.approvalDate"},
//                {"$.techRecord[0].adrDetails.batteryListNumber"},
//                {"$.techRecord[0].adrDetails.weight"},
//                {"$.techRecord[0].adrDetails.permittedDangerousGoods"},
//                {"$.techRecord[0].adrDetails.applicantDetails.name"},
//                {"$.techRecord[0].adrDetails.applicantDetails.street"},
//                {"$.techRecord[0].adrDetails.applicantDetails.town"},
//                {"$.techRecord[0].adrDetails.applicantDetails.city"},
//                {"$.techRecord[0].adrDetails.applicantDetails.postcode"},
//                {"$.techRecord[0].adrDetails.tank"},
//                {"$.techRecord[0].adrDetails.tank.tankDetails"},
//                {"$.techRecord[0].adrDetails.tank.tankDetails.tankManufacturer"},
//                {"$.techRecord[0].adrDetails.tank.tankDetails.yearOfManufacture"},
//                {"$.techRecord[0].adrDetails.tank.tankDetails.tankCode"},
//                {"$.techRecord[0].adrDetails.tank.tankDetails.tankManufacturerSerialNo"},
//                {"$.techRecord[0].adrDetails.tank.tankStatement.substancesPermitted"}
//        });
//    }
//
//    private final String jsonPath;
//
//    public PutVehicleWithoutMandatoryAdrField(String jsonPath) {
//        this.jsonPath = jsonPath;
//    }
//
//    @WithTag("Vtm")
//    @Title("CVSB-10155 - AC1 - Attempt to update a vehicle without a mandatory adr field")
//    @Test
//    public void testValidatePutRequestWithoutMandatoryHgvAttribute() {
//        String putRequestBodyHgv = GenericData.readJsonValueFromFile("technical-records-put_hgv_all_fields_with_adr_details.json", "$");
//        JsonPathAlteration restriction = new JsonPathAlteration(jsonPath, "", "", "DELETE");
//        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(restriction));
//        vehicleTechnicalRecordsSteps.putVehicleTechnicalRecordsForVehicleWithAlterations(randomVin, putRequestBodyHgv, alterations);
//        vehicleTechnicalRecordsSteps.statusCodeShouldBe(400);
//    }
//}
