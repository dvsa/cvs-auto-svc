package testresults;

import data.GenericData;
import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.junit.annotations.TestData;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import steps.TestResultsSteps;
import steps.TestTypeSteps;
import steps.VehicleTechnicalRecordsSteps;
import util.JsonPathAlteration;

import java.util.*;

@RunWith(SerenityParameterizedRunner.class)
public class PutTestResultsUpdateTestCode {

    @Steps
    VehicleTechnicalRecordsSteps vehicleTechnicalRecordsSteps;

    @Steps
    TestResultsSteps testResultsSteps;

    @Steps
    TestTypeSteps testTypeSteps;

    @TestData
    public static Collection<Object[]> testData(){
        return Arrays.asList(new Object[][]{
                //aal (1 + psv + large + rigid)
                {"aal", "$.testResult.testTypes[0].testTypeId", "3", "wdl"},
                {"aal", "$.testResult.vehicleSize", "small", "aas"},
                {"aal", "$.testResult.vehicleConfiguration", "articulated", "adl"},
                //phl (15 + psv + rigid + large)
                {"phl", "$.testResult.testTypes[0].testTypeId", "1", "aal"},
                {"phl", "$.testResult.vehicleSize", "small", "phs"},
                //qal (30 + psv + large + rigid + 2 axles)
                {"qal", "$.testResult.testTypes[0].testTypeId", "1", "adl"},
                {"qal", "$.testResult.vehicleSize", "small", "qas"},
                {"qal", "$.testResult.noOfAxles", 3, "qgl"},
                //qbv (86 + hgv)
                {"qbv", "$.testResult.testTypes[0].testTypeId", "88", "bif"},
                //trv (56 + hgv)
                {"trv", "$.testResult.testTypes[0].testTypeId", "49", "tiv"},
                {"trv", "$.testResult.vehicleType", "trl", "trt"},
                //qpv (62 + hgv + 3 axles)
                {"qpv", "$.testResult.testTypes[0].testTypeId", "122", "qjv3"},
                {"qpv", "$.testResult.vehicleType", "trl", "qqt"},
                {"qpv", "$.testResult.noOfAxles", 4, "qqv"},
                //arv (59 + hgv)
                {"arv", "$.testResult.testTypes[0].testTypeId", "60", "drv"},
                {"arv", "$.testResult.vehicleType", "trl", "art"},
                //qav3 (85 + hgv + 3 axles)
                {"qav3", "$.testResult.testTypes[0].testTypeId", "48", "nvv"},
                {"qav3", "$.testResult.vehicleType", "trl", "qat3"},
                {"qav3", "$.testResult.noOfAxles", 6, "qav5"},
                //p3v2 (76 + hgv + 2 axles)
                {"p3v2", "$.testResult.testTypes[0].testTypeId", "95", "ffv2"},
                {"p3v2", "$.testResult.vehicleType", "trl", "p3t2"},
                {"p3v2", "$.testResult.noOfAxles", 7, "p3v5"},
                //fft1 (41+ trl)
                {"fft1", "$.testResult.testTypes[0].testTypeId", "40", "aat1"},
                //qjt1 (91 + trl)
                {"qjt1", "$.testResult.testTypes[0].testTypeId", "40", "aat1"},
                //p2t1 (117 + trl)
                {"p2t1", "$.testResult.testTypes[0].testTypeId", "108", "pbt1"},
                //rft (57 + trl + 3 axles)
                {"rft", "$.testResult.testTypes[0].testTypeId", "67", "rht"},
                //pbv2 (71 + hgv + 2 axles)
                {"pbv2", "$.testResult.testTypes[0].testTypeId", "72", "pov2"},
                {"pbv2", "$.testResult.vehicleType", "trl", "pbt2"},
                {"pbv2", "$.testResult.noOfAxles", 4, "pbv4"},
                //lbp (39 + psv + large + rigid)
                {"lbp", "$.testResult.testTypes[0].testTypeId", "1", "aal"},
                //lev (45) -> vehicle is hgv
                {"lev", "$.testResult.testTypes[0].testTypeId", "44", "ldv"}
        });
    }

    private final String initialTestCode;
    private final String jsonPath;
    private final Object value;
    private final String newTestCode;

    public PutTestResultsUpdateTestCode(String initialTestId, String jsonPath, Object value, String newTestCode) {
        this.initialTestCode = initialTestId;
        this.jsonPath = jsonPath;
        this.value = value;
        this.newTestCode = newTestCode;
    }

    @WithTag("Vtm")
    @Title("CVSB-12378 - AC8 - PUT request: Test result is updated with a new test code")
    @Test
    public void testValidateTrlAttributesDataTypesAndRestrictions() {

        JSONObject restrictions = testTypeSteps.getRestrictionsByTestCode(testTypeSteps.getActualTestCode(initialTestCode));
        String vehicleType = testTypeSteps.getVehicleType(initialTestCode.split("_")[0]);

        Map<String, Object> testResultAttributes = vehicleTechnicalRecordsSteps.createTechRecord(vehicleType, restrictions);

        String testResultId = testResultsSteps.createTestRecord("submitted", "pass", initialTestCode,
                false, testResultAttributes);
        String systemNumber = testResultAttributes.get("systemNumber").toString();
        String putTestResultPayload = GenericData.readJsonValueFromFile
                ("test-results_put_" + vehicleType + ".json", "$");
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
