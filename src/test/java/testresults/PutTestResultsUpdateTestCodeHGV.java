package testresults;

import data.GenericData;
import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.junit.annotations.TestData;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import steps.TestResultsSteps;
import steps.TestTypeSteps;
import steps.VehicleTechnicalRecordsSteps;
import util.JsonPathAlteration;
import java.util.*;


@RunWith(SerenityParameterizedRunner.class)
public class PutTestResultsUpdateTestCodeHGV {

    @Steps
    VehicleTechnicalRecordsSteps vehicleTechnicalRecordsSteps;

    @Steps
    TestResultsSteps testResultsSteps;

    @Steps
    TestTypeSteps testTypeSteps;

    @TestData
    public static Collection<Object[]> testData(){
        return Arrays.asList(new Object[][]{
                //qpv (62 + hgv + 3 axles)
                {"qpv", "{\"vehicleType\":\"hgv\", \"noOfAxles\":3}", "$.testResult.testTypes[0].testTypeId", "122", "qjv3"},
                {"qpv", "{\"vehicleType\":\"hgv\", \"noOfAxles\":3}", "$.testResult.vehicleType", "trl", "qqt"},
                {"qpv", "{\"vehicleType\":\"hgv\", \"noOfAxles\":3}", "$.testResult.noOfAxles", 4, "qqv"},
                //arv (59 + hgv)
                {"arv", "{\"vehicleType\":\"hgv\"}", "$.testResult.testTypes[0].testTypeId", "60", "drv"},
                {"arv", "{\"vehicleType\":\"hgv\"}", "$.testResult.vehicleType", "trl", "art"},
                //qav3 (85 + hgv + 3 axles)
                {"qav3", "{\"vehicleType\":\"hgv\", \"noOfAxles\":3}", "$.testResult.testTypes[0].testTypeId", "48", "nvv"},
                {"qav3", "{\"vehicleType\":\"hgv\", \"noOfAxles\":3}", "$.testResult.vehicleType", "trl", "qat3"},
                {"qav3", "{\"vehicleType\":\"hgv\", \"noOfAxles\":3}", "$.testResult.noOfAxles", 6, "qav5"},
                //p3v2 (76 + hgv + 2 axles)
                {"p3v2", "{\"vehicleType\":\"hgv\", \"noOfAxles\":2}", "$.testResult.testTypes[0].testTypeId", "95", "ffv2"},
                {"p3v2", "{\"vehicleType\":\"hgv\", \"noOfAxles\":2}", "$.testResult.vehicleType", "trl", "p3t2"},
                {"p3v2", "{\"vehicleType\":\"hgv\", \"noOfAxles\":2}", "$.testResult.noOfAxles", 7, "p3v5"},

        });
    }

    private final String initialTestCode;
    private final JSONObject restrictions;
    private final String jsonPath;
    private final Object value;
    private final String newTestCode;

    public PutTestResultsUpdateTestCodeHGV(String initialTestCode, String restrictions, String jsonPath, Object value, String newTestCode) throws JSONException {
        this.initialTestCode = initialTestCode;
        this.restrictions = new JSONObject(restrictions);
        this.jsonPath = jsonPath;
        this.value = value;
        this.newTestCode = newTestCode;
    }

    //@WithTag("Vtm")
    @WithTag("In_test")
    @Title("CVSB-12378 - AC8 - PUT request: Test result is updated with a new test code")
    @Test
    public void testValidateTrlAttributesDataTypesAndRestrictions() {

        Map<String, Object> testResultAttributes = vehicleTechnicalRecordsSteps.createTechRecord(restrictions);

        String testResultId = testResultsSteps.createTestRecord("submitted", "pass", initialTestCode,
                false, testResultAttributes);
        String systemNumber = testResultAttributes.get("systemNumber").toString();
        String putTestResultPayload = GenericData.readJsonValueFromFile
                ("test-results_put_" + testResultAttributes.get("vehicleType").toString() + ".json", "$");
        String getTestResults = testResultsSteps.getTestResultsNo404(systemNumber, 5).asString();
        String testResultObject = GenericData.getJsonObjectInPath(getTestResults, "$[0]");
        // create alteration to replace the testResult object in the put request payload
        JsonPathAlteration alterationTestResultObject = new JsonPathAlteration("$.testResult", testResultObject,
                "","REPLACE");
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(alterationTestResultObject));
        // add json path alteration for adding the testResultId to the payload for the put request
        JsonPathAlteration alterationAddTestResultId = new JsonPathAlteration("$.testResult", testResultId, "testResultId",
                "ADD_FIELD");
        // add json path alteration for changing and attribute that triggers the changing of the testCode attribute
        JsonPathAlteration alterationTriggerTestCodeChange = new JsonPathAlteration(jsonPath, value, "",
                "REPLACE");
        alterations.add(alterationAddTestResultId);
        alterations.add(alterationTriggerTestCodeChange);

        testResultsSteps.putTestResultsWithAlterationsNo400(systemNumber, putTestResultPayload, alterations);
        testResultsSteps.statusCodeShouldBe(200);
        testResultsSteps.valueForFieldInPathShouldBe("testTypes[0].testCode", testTypeSteps.getActualTestCode(newTestCode));

    }
}
