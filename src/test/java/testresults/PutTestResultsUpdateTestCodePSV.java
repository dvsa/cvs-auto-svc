//package testresults;
//
//import data.GenericData;
//import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
//import net.thucydides.core.annotations.Steps;
//import net.thucydides.core.annotations.Title;
//import net.thucydides.core.annotations.WithTag;
//import net.thucydides.junit.annotations.TestData;
//import org.json.JSONException;
//import org.json.JSONObject;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import steps.*;
//import util.JsonPathAlteration;
//import java.util.*;
//
//
//@RunWith(SerenityParameterizedRunner.class)
//public class PutTestResultsUpdateTestCodePSV {
//
//    @Steps
//    VehicleTechnicalRecordsSteps vehicleTechnicalRecordsSteps;
//
//    @Steps
//    TestResultsSteps testResultsSteps;
//
//    @Steps
//    TestTypeSteps testTypeSteps;
//
//    @TestData
//    public static Collection<Object[]> testData(){
//        return Arrays.asList(new Object[][]{
//                //aal (1 + psv + large + rigid)
//                {"aal", "{\"vehicleType\":\"psv\", \"vehicleSize\":\"large\",\"vehicleConfiguration\":\"rigid\"}", "$.testResult.testTypes[0].testTypeId", "3", "wdl"},
//                {"aal", "{\"vehicleType\":\"psv\", \"vehicleSize\":\"large\",\"vehicleConfiguration\":\"rigid\"}", "$.testResult.vehicleSize", "small", "aas"},
//                {"aal", "{\"vehicleType\":\"psv\", \"vehicleSize\":\"large\",\"vehicleConfiguration\":\"rigid\"}", "$.testResult.vehicleConfiguration", "articulated", "adl"},
//                //phl (15 + psv + rigid + large)
//                {"phl", "{\"vehicleType\":\"psv\", \"vehicleSize\":\"large\",\"vehicleConfiguration\":\"rigid\"}", "$.testResult.testTypes[0].testTypeId", "1", "aal"},
//                {"phl", "{\"vehicleType\":\"psv\", \"vehicleSize\":\"large\",\"vehicleConfiguration\":\"rigid\"}", "$.testResult.vehicleSize", "small", "phs"},
//                //qal (30 + psv + large + rigid + 2 axles)
//                {"qal", "{\"vehicleType\":\"psv\", \"noOfAxles\":2, \"vehicleSize\":\"large\",\"vehicleConfiguration\":\"articulated\"}", "$.testResult.testTypes[0].testTypeId", "1", "adl"},
//                {"qal", "{\"vehicleType\":\"psv\", \"noOfAxles\":2, \"vehicleSize\":\"large\",\"vehicleConfiguration\":\"articulated\"}", "$.testResult.vehicleSize", "small", "qas"},
//                {"qal", "{\"vehicleType\":\"psv\", \"noOfAxles\":2, \"vehicleSize\":\"large\",\"vehicleConfiguration\":\"articulated\"}", "$.testResult.noOfAxles", 3, "qgl"},
//                //qbv (86 + hgv)
//                {"qbv", "{\"vehicleType\":\"hgv\"}", "$.testResult.testTypes[0].testTypeId", "88", "bif"},
//                //trv (56 + hgv)
//                {"trv", "{\"vehicleType\":\"hgv\"}", "$.testResult.testTypes[0].testTypeId", "49", "tiv"},
//                {"trv", "{\"vehicleType\":\"hgv\"}", "$.testResult.vehicleType", "trl", "trt"},
//
//        });
//    }
//
//    private final String initialTestCode;
//    private final JSONObject restrictions;
//    private final String jsonPath;
//    private final Object value;
//    private final String newTestCode;
//
//    public PutTestResultsUpdateTestCodePSV(String initialTestCode, String restrictions, String jsonPath, Object value, String newTestCode) throws JSONException {
//        this.initialTestCode = initialTestCode;
//        this.restrictions = new JSONObject(restrictions);
//        this.jsonPath = jsonPath;
//        this.value = value;
//        this.newTestCode = newTestCode;
//    }
//
//    @WithTag("Vtm")
//    @Title("CVSB-12378 - AC8 - PUT request: Test result is updated with a new test code")
//    @Test
//    public void testValidateTrlAttributesDataTypesAndRestrictions() {
//
//        Map<String, Object> testResultAttributes = vehicleTechnicalRecordsSteps.createTechRecord(restrictions);
//
//        String testResultId = testResultsSteps.createTestRecord("submitted", "pass", initialTestCode,
//                false, testResultAttributes);
//        String systemNumber = testResultAttributes.get("systemNumber").toString();
//        String putTestResultPayload = GenericData.readJsonValueFromFile
//                ("test-results_put_" + testResultAttributes.get("vehicleType").toString() + ".json", "$");
//        String getTestResults = testResultsSteps.getTestResultsNo404(systemNumber, 5).asString();
//        String testResultObject = GenericData.getJsonObjectInPath(getTestResults, "$[0]");
//        // create alteration to replace the testResult object in the put request payload
//        JsonPathAlteration alterationTestResultObject = new JsonPathAlteration("$.testResult", testResultObject,
//                "","REPLACE");
//        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(alterationTestResultObject));
//        // add json path alteration for adding the testResultId to the payload for the put request
//        JsonPathAlteration alterationAddTestResultId = new JsonPathAlteration("$.testResult", testResultId, "testResultId",
//                "ADD_FIELD");
//        // add json path alteration for changing and attribute that triggers the changing of the testCode attribute
//        JsonPathAlteration alterationTriggerTestCodeChange = new JsonPathAlteration(jsonPath, value, "",
//                "REPLACE");
//        alterations.add(alterationAddTestResultId);
//        alterations.add(alterationTriggerTestCodeChange);
//
//        testResultsSteps.putTestResultsWithAlterationsNo400(systemNumber, putTestResultPayload, alterations);
//        testResultsSteps.statusCodeShouldBe(200);
//        testResultsSteps.valueForFieldInPathShouldBe("testTypes[0].testCode", testTypeSteps.getActualTestCode(newTestCode));
//
//    }
//}
