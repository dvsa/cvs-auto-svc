package vehicle;

import data.GenericData;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.junit.annotations.TestData;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import org.junit.BeforeClass;
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


@RunWith(SerenityParameterizedRunner.class)
public class PutVehicleAdrFieldRestrictions {

    static String randomVin;

    @BeforeClass
    public static void createRecord() {
        // TEST SETUP
        // generate random Vin
        randomVin = GenericData.generateRandomVin();
        // generate random Vrm
        String randomVrm = GenericData.generateRandomVrm();
        // read post request body from file
        String postRequestBodyHgv = GenericData.readJsonValueFromFile
                ("technical-records_hgv_all_fields_with_adr_details.json", "$");
        // create alteration to change Vin in the post request body with the random generated Vin
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        // create alteration to change primary vrm in the request body with the random generated primary vrm
        JsonPathAlteration alterationVrm = new JsonPathAlteration("$.primaryVrm", randomVrm, "", "REPLACE");

        // initialize the alterations list with both declared alterations
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(alterationVin, alterationVrm));

        String alteredBody = GenericData.applyJsonAlterations(postRequestBodyHgv, alterations);
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
        response.prettyPrint();
        Assert.assertEquals(201, response.getStatusCode());
    }

    @Steps
    VehicleTechnicalRecordsSteps vehicleTechnicalRecordsSteps;

    @TestData
    public static Collection<Object[]> testData(){
        return Arrays.asList(new Object[][]{
                {"$.techRecord[0].adrDetails.vehicleDetails.type", false},
                {"$.techRecord[0].adrDetails.vehicleDetails.approvalDate", "21/08/1987"},
                {"$.techRecord[0].adrDetails.vehicleDetails.approvalDate", false},
                {"$.techRecord[0].adrDetails.listStatementApplicable", 100},
                {"$.techRecord[0].adrDetails.batteryListNumber", RandomStringUtils.randomAlphanumeric(9)},
                {"$.techRecord[0].adrDetails.batteryListNumber", 100},
                {"$.techRecord[0].adrDetails.declarationsSeen", 100},
                {"$.techRecord[0].adrDetails.brakeDeclarationsSeen", 100},
                {"$.techRecord[0].adrDetails.brakeDeclarationIssuer", 100},
                {"$.techRecord[0].adrDetails.brakeEndurance", 100},
                {"$.techRecord[0].adrDetails.weight", RandomStringUtils.randomAlphanumeric(9)},
                {"$.techRecord[0].adrDetails.weight", 100},
                {"$.techRecord[0].adrDetails.compatibilityGroupJ", 100},
                {"$.techRecord[0].adrDetails.documents[0]", 100},
                {"$.techRecord[0].adrDetails.permittedDangerousGoods[0]", false},
                //TODO uncomment when clarified whether this value should be limited to 1024 characters or not
//                {"$.techRecord[0].adrDetails.additionalExaminerNotes", RandomStringUtils.randomAlphanumeric(1025)},
                {"$.techRecord[0].adrDetails.additionalExaminerNotes", 100},
                {"$.techRecord[0].adrDetails.applicantDetails.name", RandomStringUtils.randomAlphanumeric(151)},
                {"$.techRecord[0].adrDetails.applicantDetails.name", 100},
                {"$.techRecord[0].adrDetails.applicantDetails.street", RandomStringUtils.randomAlphanumeric(151)},
                {"$.techRecord[0].adrDetails.applicantDetails.street", 100},
                {"$.techRecord[0].adrDetails.applicantDetails.town", RandomStringUtils.randomAlphanumeric(101)},
                {"$.techRecord[0].adrDetails.applicantDetails.town", 100},
                {"$.techRecord[0].adrDetails.applicantDetails.city", RandomStringUtils.randomAlphanumeric(101)},
                {"$.techRecord[0].adrDetails.applicantDetails.city", 100},
                {"$.techRecord[0].adrDetails.applicantDetails.postcode", RandomStringUtils.randomAlphanumeric(26)},
                {"$.techRecord[0].adrDetails.applicantDetails.postcode", 100},
                {"$.techRecord[0].adrDetails.memosApply[0]", 100},
                {"$.techRecord[0].adrDetails.additionalNotes.number[0]", 100},
                {"$.techRecord[0].adrDetails.additionalNotes.guidanceNotes[0]", 100},
                {"$.techRecord[0].adrDetails.adrTypeApprovalNo", 100},
                {"$.techRecord[0].adrDetails.adrCertificateNotes", RandomStringUtils.randomAlphanumeric(1501)},
                {"$.techRecord[0].adrDetails.tank.tankDetails.tankManufacturer", RandomStringUtils.randomAlphanumeric(71)},
                {"$.techRecord[0].adrDetails.tank.tankDetails.tankManufacturer", 10000},
                {"$.techRecord[0].adrDetails.tank.tankDetails.yearOfManufacture", 10000},
                {"$.techRecord[0].adrDetails.tank.tankDetails.yearOfManufacture", RandomStringUtils.randomAlphanumeric(71)},
                {"$.techRecord[0].adrDetails.tank.tankDetails.tankCode", RandomStringUtils.randomAlphanumeric(31)},
                {"$.techRecord[0].adrDetails.tank.tankDetails.tankCode", 10000},
                {"$.techRecord[0].adrDetails.tank.tankDetails.specialProvisions", RandomStringUtils.randomAlphanumeric(1025)},
                {"$.techRecord[0].adrDetails.tank.tankDetails.specialProvisions", 10000},
                {"$.techRecord[0].adrDetails.tank.tankDetails.tankManufacturerSerialNo", RandomStringUtils.randomAlphanumeric(51)},
                {"$.techRecord[0].adrDetails.tank.tankDetails.tankManufacturerSerialNo", 10000},
                {"$.techRecord[0].adrDetails.tank.tankDetails.tankTypeAppNo", RandomStringUtils.randomAlphanumeric(31)},
                {"$.techRecord[0].adrDetails.tank.tankDetails.tankTypeAppNo", 10000},
                {"$.techRecord[0].adrDetails.tank.tankDetails.tc2Details.tc2Type", 10000},
                {"$.techRecord[0].adrDetails.tank.tankDetails.tc2Details.tc2IntermediateApprovalNo", RandomStringUtils.randomAlphanumeric(71)},
                {"$.techRecord[0].adrDetails.tank.tankDetails.tc2Details.tc2IntermediateApprovalNo", 10000},
                {"$.techRecord[0].adrDetails.tank.tankDetails.tc2Details.tc2IntermediateExpiryDate", "21/08/1987"},
                {"$.techRecord[0].adrDetails.tank.tankDetails.tc2Details.tc2IntermediateExpiryDate", false},
                {"$.techRecord[0].adrDetails.tank.tankDetails.tc3Details[0].tc3Type", RandomStringUtils.randomAlphanumeric(10)},
                {"$.techRecord[0].adrDetails.tank.tankDetails.tc3Details[0].tc3Type", 10},
                {"$.techRecord[0].adrDetails.tank.tankDetails.tc3Details[0].tc3PeriodicNumber", RandomStringUtils.randomAlphanumeric(76)},
                {"$.techRecord[0].adrDetails.tank.tankDetails.tc3Details[0].tc3PeriodicNumber", 10000},
                {"$.techRecord[0].adrDetails.tank.tankDetails.tc3Details[0].tc3PeriodicExpiryDate", "21/08/1987"},
                {"$.techRecord[0].adrDetails.tank.tankDetails.tc3Details[0].tc3PeriodicExpiryDate", false},
                {"$.techRecord[0].adrDetails.tank.tankStatement.substancesPermitted", 10000},
                {"$.techRecord[0].adrDetails.tank.tankStatement.statement", RandomStringUtils.randomAlphanumeric(1501)},
                {"$.techRecord[0].adrDetails.tank.tankStatement.statement", false},
                {"$.techRecord[0].adrDetails.tank.tankStatement.productListRefNo", 10000},
                {"$.techRecord[0].adrDetails.tank.tankStatement.productListUnNo[0]", 10000},
                {"$.techRecord[0].adrDetails.tank.tankStatement.productList", RandomStringUtils.randomAlphanumeric(1501)},
                {"$.techRecord[0].adrDetails.tank.tankStatement.productList", 10000}
        });
    }

    private final String jsonPath;
    private final Object value;

    public PutVehicleAdrFieldRestrictions(String jsonPath, Object value) {
        this.jsonPath = jsonPath;
        this.value = value;
    }

    @WithTag("Vtm")
    @Title("CVSB-10155 - AC1 - Attempt to update a vehicle with unexpected values for an ADR field that accepts only specific values")
    @Test
    public void testValidateAdrAttributesDataTypesAndRestrictions() {

        String putRequestBodyHgv = GenericData.readJsonValueFromFile("technical-records-put_hgv_all_fields_with_adr_details.json", "$");
        String systemNumber = vehicleTechnicalRecordsSteps.getSystemNumberUsingVin(randomVin);
        JsonPathAlteration restriction = new JsonPathAlteration(jsonPath, value, "", "REPLACE");
        JsonPathAlteration alterationAStatusCode = new JsonPathAlteration("$.techRecord[0]", "provisional","statusCode","ADD_FIELD");
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(restriction, alterationAStatusCode));
        vehicleTechnicalRecordsSteps.putVehicleTechnicalRecordsForVehicleWithAlterations(systemNumber, putRequestBodyHgv, alterations);
        vehicleTechnicalRecordsSteps.statusCodeShouldBe(400);
    }
}
