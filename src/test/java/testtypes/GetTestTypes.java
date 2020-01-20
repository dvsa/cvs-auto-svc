package testtypes;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import data.config.BaseData;
import io.restassured.response.Response;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import steps.TestTypeSteps;

import java.io.File;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;


@RunWith(SerenityRunner.class)
public class GetTestTypes {

    @Steps
    TestTypeSteps testTypeSteps;

//    @Ignore("Bad test design - requires maintenance for every change in taxonomy")
    @Title("CVSB-579 / CVSB-746 - CVSB-996 / CVSB-2391 AC1 - API Consumer retrieve all the test types and test codes reference data")
    public void testTypesReferenceData() throws Exception {
        Response resp = testTypeSteps.getTestTypesWithData();
        testTypeSteps.statusCodeShouldBe(200);

//        Gson gson = new Gson();
//        Type type = new TypeToken<List<Map<String, Object>>>(){}.getType();
//
//        // Load in the expected defects json from the sample file.
//        ObjectMapper mapper = new ObjectMapper();
//        File from = new File("src/main/resources/loader/" + BaseData.getDataLocation() + "/test-type.json");
//        JsonNode masterJSON = mapper.readTree(from);
//        List<Map<String, Object>> leftMap = gson.fromJson(masterJSON.toString(), type);
//
//        // Extract the response json from the endpoint call.
//        String print = resp.getBody().prettyPrint();
//        List<Map<String, Object>> rightMap = gson.fromJson(print, type);
//
//        // Verify that the two lists are of the same size (json nodes).
//        assert(leftMap.size() == rightMap.size());
//
//        // Iterate through each node in the response json and verify that it is present in the expected response.
//        for (int i = 0; i < rightMap.size(); i++) {
//            assert(leftMap.contains(rightMap.get(i)));
//        }
    }

    @Title("CVSB-1073 / CVSB-2206 - AC5 The endpoint to retrieve all the test types reference data does not return the 'testTypeClassification' 'defaultTestCode' and 'linkedTestCode' attributes")
    @Test
    public void testTypesAttributesFromTestTypeIdNotPresent() {
        testTypeSteps.getTestTypesWithData();
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.validateTestTypeDataNotExisting();
    }

}
