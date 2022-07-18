package testresults;

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
public class PutTestResultsFieldRestrictions {

    static String randomVin;

    @BeforeClass
    public static void createRecord() {
        // TEST SETUP
        // generate random Vin
        randomVin = GenericData.generateRandomVin();
        // generate random Vrm
        String randomVrm = GenericData.generateRandomVrm();
        // read post request body from file
        String postRequestBody = GenericData.readJsonValueFromFile("test-results_duplicate_chassis_10752.json", "$");
        // create alteration to change Vin in the post request body with the random generated Vin
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin, "", "REPLACE");
        // create alteration to change primary vrm in the request body with the random generated primary vrm
        JsonPathAlteration alterationVrm = new JsonPathAlteration("$.vrm", randomVrm, "", "REPLACE");

        // initialize the alterations list with both declared alterations
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(alterationVin, alterationVrm));

        String alteredBody = GenericData.applyJsonAlterations(postRequestBody, alterations);
        Response response = given().filters(new BasePathFilter())
                .contentType(ContentType.JSON)
                .body(alteredBody)
                .log().method().log().uri().log().body()
                .post("/test-results");

        if (response.getStatusCode() == 401 || response.getStatusCode() == 403) {
            saveUtils();
            response = given().filters(new BasePathFilter())
                    .contentType(ContentType.JSON)
                    .body(alteredBody)
                    .log().method().log().uri().log().body()
                    .post("/test-results");
        }

        Assert.assertEquals(201, response.getStatusCode());
    }

    @Steps
    TestResultsSteps testResultsSteps;

    @TestData
    public static Collection<Object[]> testData(){
        return Arrays.asList(new Object[][]{
                {"$.vin", RandomStringUtils.randomAlphanumeric(22)},
                {"$.vin", ""},
                {"$.vin", 100},
                {"$.vrm", RandomStringUtils.randomAlphanumeric(9)},
                {"$.vrm", ""},
                {"$.vrm", 100},
                {"$.countryOfRegistration", 100},
                {"$.euVehicleCategory",RandomStringUtils.randomAlphanumeric(10)},
                {"$.euVehicleCategory",100},
                {"$.odometerReading" , "String"},
                {"$.odometerReadingUnits",RandomStringUtils.randomAlphanumeric(10)},
                {"$.odometerReadingUnits",100},
                {"$.preparerId", 999},
                {"$.preparerName", 999},
                {"$.testTypes[0].testResult", 100},
                {"$.testTypes[0].testResult", RandomStringUtils.randomAlphanumeric(10)},
                {"$.testTypes[0].reasonForAbandoning", 100},
                {"$.testTypes[0].additionalCommentsForAbandon", 100},
                {"$.testTypes[0].additionalCommentsForAbandon", RandomStringUtils.randomAlphanumeric(501)},
                {"$.testTypes[0].additionalCommentsForAbandon", ""},
                {"$.testTypes[0].certificateNumber",100},
                {"$.testTypes[0].testExpiryDate","21/08/1987"},
                {"$.testTypes[0].testExpiryDate",false},
                {"$.testTypes[0].testTypeStartTimestamp", 100},
                {"$.testTypes[0].testTypeEndTimestamp", 100},
                {"$.testTypes[0].prohibitionIssued", 100},
                {"$.testTypes[0].emissionStandard" , 100},
                {"$.testTypes[0].emissionStandard" , RandomStringUtils.randomAlphanumeric(10)},
                {"$.testTypes[0].smokeTestKLimitApplied", 100},
                {"$.testTypes[0].smokeTestKLimitApplied", RandomStringUtils.randomAlphanumeric(101)},
                {"$.testTypes[0].fuelType", 100},
                {"$.testTypes[0].fuelType", RandomStringUtils.randomAlphanumeric(10)},
                {"$.testTypes[0].modType.code", 100},
                {"$.testTypes[0].modType.code", RandomStringUtils.randomAlphanumeric(4)},
                {"$.testTypes[0].modType.description", 100},
                {"$.testTypes[0].modType.description", RandomStringUtils.randomAlphanumeric(10)},
                {"$.testTypes[0].modificationTypeUsed", 100},
                {"$.testTypes[0].modificationTypeUsed", RandomStringUtils.randomAlphanumeric(101)},
                {"$.testTypes[0].particulateTrapFitted", 100},
                {"$.testTypes[0].particulateTrapFitted", RandomStringUtils.randomAlphanumeric(101)},
                {"$.testTypes[0].particulateTrapSerialNumber", 100},
                {"$.testTypes[0].particulateTrapSerialNumber", RandomStringUtils.randomAlphanumeric(101)},
                {"$.testStationName",100},
                {"$.testStationName",RandomStringUtils.randomAlphanumeric(1000)},
                {"$.testStationPNumber", 100},
                {"$.testStationPNumber", RandomStringUtils.randomAlphanumeric(21)},
                {"$.testStationType", 100},
                {"$.testStationType", RandomStringUtils.randomAlphanumeric(10)},
                {"$.testerName", 100},
                {"$.testerName", RandomStringUtils.randomAlphanumeric(61)},
                {"$.testerEmailAddress", 100},
                {"$.testerEmailAddress", RandomStringUtils.randomAlphanumeric(61)},

        });

    }

    private final String jsonPath;
    private final Object value;

    public PutTestResultsFieldRestrictions(String jsonPath, Object value) {
        this.jsonPath = jsonPath;
        this.value = value;
    }

    @WithTag("Vtm")
    @Title("CVSB-10284 - AC2. PUT: Attempt to update a test record with invalid values  " +
            "AC3. PUT: Attempt to update a test record, using a field which has a field length outside of the min/max length for that field")
    @Test
    public void testValidateHgvAttributesDataTypesAndRestrictions() {
        String putRequestBody = GenericData.readJsonValueFromFile("test-results_duplicate_chassis_10752.json", "$");
        JsonPathAlteration restriction = new JsonPathAlteration(jsonPath, value, "", "REPLACE");
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(restriction));
        testResultsSteps.putTestResultsWithAlterations(randomVin,putRequestBody,alterations);
        testResultsSteps.statusCodeShouldBe(400);
    }
}
