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
import steps.*;
import util.JsonPathAlteration;
import java.util.*;

@WithTag("In_test")
@RunWith(SerenityParameterizedRunner.class)
public class PutTestResultsUpdateTestCodeTRL {

    @Steps
    VehicleTechnicalRecordsSteps vehicleTechnicalRecordsSteps;

    @Steps
    TestResultsSteps testResultsSteps;

    @Steps
    TestTypeSteps testTypeSteps;

    @TestData
    public static Collection<Object[]> testData(){
        return Arrays.asList(new Object[][]{
                //fft1 (41+ trl)
                {"fft1", "{\"vehicleType\":\"trl\", \"noOfAxles\":1}", "$.testResult.testTypes[0].testTypeId", "40", "aat1"},
                //qjt1 (91 + trl)
                {"qjt1", "{\"vehicleType\":\"trl\", \"noOfAxles\":1}", "$.testResult.testTypes[0].testTypeId", "40", "aat1"},
                //p2t1 (117 + trl)
                {"p2t1", "{\"vehicleType\":\"trl\", \"noOfAxles\":1}", "$.testResult.testTypes[0].testTypeId", "108", "pbt1"},
                //rft (57 + trl + 3 axles)
                {"rft", "{\"vehicleType\":\"trl\", \"noOfAxles\":3}", "$.testResult.testTypes[0].testTypeId", "67", "rht"},
                //pbv2 (71 + hgv + 2 axles)
                {"pbv2", "{\"vehicleType\":\"hgv\", \"noOfAxles\":2}", "$.testResult.testTypes[0].testTypeId", "72", "pov2"},
                {"pbv2", "{\"vehicleType\":\"hgv\", \"noOfAxles\":2}", "$.testResult.vehicleType", "trl", "pbt2"},
                {"pbv2", "{\"vehicleType\":\"hgv\", \"noOfAxles\":2}", "$.testResult.noOfAxles", 4, "pbv4"},
                //lbp (39 + psv + large + rigid)
                {"lbp", "{\"vehicleType\":\"psv\", \"vehicleConfiguration\":\"rigid\"}", "$.testResult.testTypes[0].testTypeId", "1", "aal"},
                //lev (45) -> vehicle is hgv
                {"lev", "{\"vehicleType\":\"hgv\"}", "$.testResult.testTypes[0].testTypeId", "44", "ldv"}
        });
    }

    private final String initialTestCode;
    private final JSONObject restrictions;
    private final String jsonPath;
    private final Object value;
    private final String newTestCode;

    public PutTestResultsUpdateTestCodeTRL(String initialTestCode, String restrictions, String jsonPath, Object value, String newTestCode) throws JSONException {
        this.initialTestCode = initialTestCode;
        this.restrictions = new JSONObject(restrictions);
        this.jsonPath = jsonPath;
        this.value = value;
        this.newTestCode = newTestCode;
    }

    @WithTag("Vtm")
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
