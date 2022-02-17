package testresults;

import data.GenericData;
import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.junit.annotations.TestData;
import org.junit.Test;
import org.junit.runner.RunWith;
import steps.TestResultsSteps;
import util.JsonPathAlteration;
import org.apache.http.HttpStatus;
import java.util.*;


@RunWith(SerenityParameterizedRunner.class)
public class PutTestResultsFieldsNotApplicable {

    @Steps
    TestResultsSteps testResultsSteps;

    @TestData
    public static Collection<Object[]> testData(){
        return Arrays.asList(new Object[][]{
                {"$.testResult.testTypes[0].testTypeId", "1"},
                {"$.testResult.testTypes[0].testTypeId", "3"},
                {"$.testResult.testTypes[0].testTypeId", "4"},
                {"$.testResult.testTypes[0].testTypeId", "7"},
                {"$.testResult.testTypes[0].testTypeId", "8"},
                {"$.testResult.testTypes[0].testTypeId", "10"},
                {"$.testResult.testTypes[0].testTypeId", "14"},
                {"$.testResult.testTypes[0].testTypeId", "18"},
                {"$.testResult.testTypes[0].testTypeId", "21"},
                {"$.testResult.testTypes[0].testTypeId", "27"},
                {"$.testResult.testTypes[0].testTypeId", "28"},
                {"$.testResult.testTypes[0].testTypeId", "93"},
                {"$.testResult.testTypes[0].testTypeId", "15"},
                {"$.testResult.testTypes[0].testTypeId", "16"},
                {"$.testResult.testTypes[0].testTypeId", "23"},
                {"$.testResult.testTypes[0].testTypeId", "19"},
                {"$.testResult.testTypes[0].testTypeId", "22"},
                {"$.testResult.testTypes[0].testTypeId", "38"},
                {"$.testResult.testTypes[0].testTypeId", "30"},
                {"$.testResult.testTypes[0].testTypeId", "33"},
                {"$.testResult.testTypes[0].testTypeId", "34"},
                {"$.testResult.testTypes[0].testTypeId", "32"},
                {"$.testResult.testTypes[0].testTypeId", "31"},
                {"$.testResult.testTypes[0].testTypeId", "100"},
                {"$.testResult.testTypes[0].testTypeId", "121"},
                {"$.testResult.testTypes[0].testTypeId", "36"},
                {"$.testResult.testTypes[0].testTypeId", "86"},
                {"$.testResult.testTypes[0].testTypeId", "88"},
                {"$.testResult.testTypes[0].testTypeId", "89"},
                {"$.testResult.testTypes[0].testTypeId", "90"},
                {"$.testResult.testTypes[0].testTypeId", "56"},
                {"$.testResult.testTypes[0].testTypeId", "59"},
                {"$.testResult.testTypes[0].testTypeId", "60"},
                {"$.testResult.testTypes[0].testTypeId", "87"},
                {"$.testResult.testTypes[0].testTypeId", "47"},
                {"$.testResult.testTypes[0].testTypeId", "48"},
                {"$.testResult.testTypes[0].testTypeId", "49"},
                {"$.testResult.testTypes[0].testTypeId", "50"},
                {"$.testResult.testTypes[0].testTypeId", "85"},
                {"$.testResult.testTypes[0].testTypeId", "76"},
                {"$.testResult.testTypes[0].testTypeId", "62"},
                {"$.testResult.testTypes[0].testTypeId", "95"},
                {"$.testResult.testTypes[0].testTypeId", "94"},
                {"$.testResult.testTypes[0].testTypeId", "53"},
                {"$.testResult.testTypes[0].testTypeId", "54"},
                {"$.testResult.testTypes[0].testTypeId", "63"},
                {"$.testResult.testTypes[0].testTypeId", "65"},
                {"$.testResult.testTypes[0].testTypeId", "66"},
                {"$.testResult.testTypes[0].testTypeId", "70"},
                {"$.testResult.testTypes[0].testTypeId", "79"},
                {"$.testResult.testTypes[0].testTypeId", "82"},
                {"$.testResult.testTypes[0].testTypeId", "83"},
                {"$.testResult.testTypes[0].testTypeId", "122"},
                {"$.testResult.testTypes[0].testTypeId", "41"},
                {"$.testResult.testTypes[0].testTypeId", "40"},
                {"$.testResult.testTypes[0].testTypeId", "98"},
                {"$.testResult.testTypes[0].testTypeId", "99"},
                {"$.testResult.testTypes[0].testTypeId", "101"},
                {"$.testResult.testTypes[0].testTypeId", "103"},
                {"$.testResult.testTypes[0].testTypeId", "104"},
                {"$.testResult.testTypes[0].testTypeId", "67"},
                {"$.testResult.testTypes[0].testTypeId", "107"},
                {"$.testResult.testTypes[0].testTypeId", "113"},
                {"$.testResult.testTypes[0].testTypeId", "116"},
                {"$.testResult.testTypes[0].testTypeId", "119"},
                {"$.testResult.testTypes[0].testTypeId", "120"},
                {"$.testResult.testTypes[0].testTypeId", "91"},
                {"$.testResult.testTypes[0].testTypeId", "117"},
                {"$.testResult.testTypes[0].testTypeId", "108"},
                {"$.testResult.testTypes[0].testTypeId", "109"},
                {"$.testResult.testTypes[0].testTypeId", "110"},
                {"$.testResult.testTypes[0].testTypeId", "114"},
                {"$.testResult.testTypes[0].testTypeId", "57"},
                {"$.testResult.testTypes[0].testTypeId", "71"},
                {"$.testResult.testTypes[0].testTypeId", "72"},
                {"$.testResult.testTypes[0].testTypeId", "73"},
                {"$.testResult.testTypes[0].testTypeId", "77"},
                {"$.testResult.testTypes[0].testTypeId", "80"},
                {"$.testResult.testTypes[0].testTypeId", "39"},
                {"$.testResult.testTypes[0].testTypeId", "45"},
                {"$.testResult.testTypes[0].testTypeId", "44"},
        });
    }

    private final String jsonPath;
    private final Object value;

    public PutTestResultsFieldsNotApplicable(String jsonPath, Object value) {
        this.jsonPath = jsonPath;
        this.value = value;
    }

    @WithTag("Vtm")
    @Title("CVSB-11504 - TC - AC1 PUT: Attempt to update a test record with a not applicable field")
    @Test
    public void putTestResultsWithNotApplicableField(){

        // Read the request payload
        String jsonFileName = "test-results_put_payload_11504.json";
        String putRequestBody = GenericData.updateJson(jsonFileName,true);

        // Generate random vin, systemNumber and TestResultID
        String randomSystemNumber = GenericData.generateRandomSystemNumber();
        String randomVin = GenericData.generateRandomVin();
        String randomTestResultId = UUID.randomUUID().toString();

        //Create alterations for random vin, systemNumber and TestResultID TestTypeId
        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$.testResult.systemNumber", randomSystemNumber, "", "REPLACE");
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.testResult.vin", randomVin, "", "REPLACE");
        JsonPathAlteration alterationTestResultId = new JsonPathAlteration("$.testResult.testResultId", randomTestResultId, "", "REPLACE");
        JsonPathAlteration restriction = new JsonPathAlteration(jsonPath, value, "", "REPLACE");

        // Collate all alterations
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(
                alterationSystemNumber,
                alterationVin,
                alterationTestResultId,
                restriction));

        //Put the request payload with alterations
        testResultsSteps.putTestResultsWithAlterations(randomSystemNumber,putRequestBody,alterations);
        testResultsSteps.statusCodeShouldBe(HttpStatus.SC_BAD_REQUEST);
    }
}
