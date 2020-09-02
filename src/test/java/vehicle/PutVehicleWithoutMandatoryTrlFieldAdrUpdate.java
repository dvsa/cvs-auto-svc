package vehicle;

import data.GenericData;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import junit.framework.TestCase;
import model.vehicles.VehicleTechnicalRecordStatus;
import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.junit.annotations.TestData;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import steps.*;
import util.BasePathFilter;
import util.JsonPathAlteration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static io.restassured.RestAssured.given;
import static util.WriterReader.saveUtils;

@Ignore
@RunWith(SerenityParameterizedRunner.class)
public class PutVehicleWithoutMandatoryTrlFieldAdrUpdate extends TestCase {

    static String randomVin;
    static String techRecord;

    @BeforeClass
    public static void createRecord() {
        // TEST SETUP
        // generate random Vin
        randomVin = GenericData.generateRandomVin();
        // generate random Vrm
        String randomVrm = GenericData.generateRandomVrm();
        // read post request body from file
        String postRequestBodyTrl = GenericData.readJsonValueFromFile("technical-records_trl_all_fields.json", "$");
        techRecord = GenericData.readJsonValueFromFile("technical-records_trl_all_fields.json", "$.techRecord[0]");
        // create alteration to change Vin in the post request body with the random generated Vin
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        // create alteration to change primary vrm in the request body with the random generated primary vrm
        JsonPathAlteration alterationVrm = new JsonPathAlteration("$.primaryVrm", randomVrm, "", "REPLACE");

        // initialize the alterations list with both declared alterations
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(alterationVin, alterationVrm));

        String alteredBody = GenericData.applyJsonAlterations(postRequestBodyTrl, alterations);
        Response response = given().filters(new BasePathFilter())
                .contentType(ContentType.JSON)
                .body(alteredBody)
                .log().method().log().uri().log().body()
                .post("/vehicles");

        if (response.getStatusCode() == 401 || response.getStatusCode() == 403) {
            saveUtils();
            response = given().filters(new BasePathFilter())
                    .contentType(ContentType.JSON)
                    .body(alteredBody)
                    .log().method().log().uri().log().body()
                    .post("/vehicles");
        }

        Assert.assertEquals(201, response.getStatusCode());
    }

    @Steps
    VehicleTechnicalRecordsSteps vehicleTechnicalRecordsSteps;

    @TestData
    public static Collection<Object[]> testData(){
        return Arrays.asList(new Object[][]{
                {"$.techRecord[0].manufactureYear"},
                {"$.techRecord[0].noOfAxles"},
                {"$.techRecord[0].brakes.dtpNumber"},
                {"$.techRecord[0].roadFriendly"},
                {"$.techRecord[0].vehicleClass.description"},
                {"$.techRecord[0].vehicleConfiguration"},
                {"$.techRecord[0].euVehicleCategory"},
                {"$.techRecord[0].approvalType"},
                {"$.techRecord[0].make"},
                {"$.techRecord[0].model"},
                {"$.techRecord[0].bodyType.description"},
                {"$.techRecord[0].grossGbWeight"},
                {"$.techRecord[0].grossDesignWeight"},
                {"$.techRecord[0].axles[0].weights.gbWeight"},
                {"$.techRecord[0].axles[0].weights.designWeight"},
                {"$.techRecord[0].axles[0].tyres.tyreCode"},
                {"$.techRecord[0].axles[0].tyres.tyreSize"},
                {"$.techRecord[0].axles[0].tyres.fitmentCode"},
                {"$.techRecord[0].brakes.antilockBrakingSystem"},
                {"$.techRecord[0].dimensions.length"},
                {"$.techRecord[0].dimensions.width"},
                {"$.techRecord[0].frontAxleToRearAxle"},
                {"$.techRecord[0].rearAxleToRearTrl"},
                {"$.techRecord[0].couplingCenterToRearAxleMin"},
                {"$.techRecord[0].couplingCenterToRearAxleMax"},
                {"$.techRecord[0].couplingCenterToRearTrlMin"},
                {"$.techRecord[0].couplingCenterToRearTrlMax"},
                {"$.techRecord[0].centreOfRearmostAxleToRearOfTrl"},
                {"$.techRecord[0].applicantDetails.name"},
                {"$.techRecord[0].applicantDetails.address1"},
                {"$.techRecord[0].applicantDetails.address2"},
                {"$.techRecord[0].applicantDetails.postTown"},
                {"$.techRecord[0].purchaserDetails.name"},
                {"$.techRecord[0].purchaserDetails.address1"},
                {"$.techRecord[0].purchaserDetails.address2"},
                {"$.techRecord[0].purchaserDetails.postTown"},
                {"$.techRecord[0].manufacturerDetails.name"},
                {"$.techRecord[0].manufacturerDetails.address1"},
                {"$.techRecord[0].manufacturerDetails.address2"},
                {"$.techRecord[0].manufacturerDetails.postTown"}
        });
    }

    private final String jsonPath;

    public PutVehicleWithoutMandatoryTrlFieldAdrUpdate(String jsonPath) {
        this.jsonPath = jsonPath;
    }

    @WithTag("Vtm")
    @Title("CVSB-14145 - AC1 - Only the validations on the adrDetails{} level are adhered to")
    public void testValidatePutRequestWithoutMandatoryTrlAttributeAdrUpdate() {
        String putRequestBodyTrl = GenericData.readJsonValueFromFile
                ("technical-records_trl_all_fields_with_adr_details.json", "$");
        String adrDetails = GenericData.readJsonValueFromFile
                ("technical-records_trl_all_fields_with_adr_details.json", "$.techRecord[0].adrDetails");
        JsonPathAlteration removeMandatoryField = new JsonPathAlteration(jsonPath, "", "", "DELETE");
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(removeMandatoryField));
        vehicleTechnicalRecordsSteps.putVehicleTechnicalRecordsForVehicleWithAlterations(randomVin, putRequestBodyTrl, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(200);
        vehicleTechnicalRecordsSteps.getVehicleTechnicalRecordsByStatus(randomVin, VehicleTechnicalRecordStatus.ALL);

        vehicleTechnicalRecordsSteps.validateElementsInResponseContainsJson
                ("[0].techRecord.findAll { it.vehicleType != null }", techRecord);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe
                ("[0].techRecord.findAll { it.statusCode == 'provisional' }.size()", 1);
        vehicleTechnicalRecordsSteps.valueForFieldInPathShouldBe("[0].techRecord.findAll { it.statusCode == 'archived' }.size()",
                Integer.parseInt(vehicleTechnicalRecordsSteps.extractValueFromPath("[0].techRecord.size()").toString()) - 1);
        vehicleTechnicalRecordsSteps.validateElementsInResponseContainsJson
                ("[0].techRecord.findAll { it.adrDetails != null }.adrDetails", adrDetails);
    }
}
