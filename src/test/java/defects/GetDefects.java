package defects;

import io.restassured.response.Response;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Test;
import org.junit.runner.RunWith;
import steps.DefectsSteps;



@RunWith(SerenityRunner.class)
public class GetDefects {

    @Steps
    DefectsSteps defectsSteps;

    // Bad Test Design
    @Title("CVSB-279 / CVSB-740 - AC1 - API Consumer retrieve all the defects reference data")
    @Test
    public void defectsReferenceData() throws Exception {
        Response resp = defectsSteps.callDefectsWithData();
        defectsSteps.statusCodeShouldBe(200);

        /*Gson gson = new Gson();
        Type type = new TypeToken<List<Map<String, Object>>>(){}.getType();

        // Load in the expected defects json from the sample file.
        ObjectMapper mapper = new ObjectMapper();
        File from = new File("src/main/resources/loader/" + BaseData.getDataLocation() + "/defect.json");
        JsonNode masterJSON = mapper.readTree(from);
        List<Map<String, Object>> leftMap = gson.fromJson(masterJSON.toString(), type);

        // Extract the response json from the endpoint call.
        String print = resp.getBody().prettyPrint();
        List<Map<String, Object>> rightMap = gson.fromJson(print, type);

        // Verify that the two lists are of the same size (json nodes).
        assert(leftMap.size() == rightMap.size());

        // Iterate through each node in the response json and verify that it is present in the expected response.
        for (int i = 0; i < rightMap.size(); i++) {
            assert(leftMap.contains(rightMap.get(i)));
        }*/
    }

    @Title("CVSB-1906 / CVSB-2554 - API Consumer does not get 'advisory' and 'prs' data for deficiencyCategory")
    @Test
    public void defectsReferenceData2() {
        defectsSteps.callDefectsWithData();
        defectsSteps.statusCodeShouldBe(200);
        defectsSteps.validateDeficiencyCategoryNotContains("advisory", "prs");
    }

}

