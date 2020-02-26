package testresults;

import data.GenericData;
import data.TestResultsData;
import model.testresults.TestResults;
import model.testresults.TestResultsGet;
import model.testresults.TestResultsStatus;
import model.testresults.TestTypes;
import model.vehicles.VehicleTechnicalRecordStatus;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Test;
import org.junit.runner.RunWith;
import steps.TestResultsSteps;
import util.JsonPathAlteration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static util.DataUtil.generateRandomExcludingValues;


@RunWith(SerenityRunner.class)
public class PostTestResultsTestCodeMappingOnTestTypes {

    @Steps
    TestResultsSteps testResultsSteps;

    private TestResults.Builder vehicleSubmittedDataOne = TestResultsData.buildTestResultsSubmittedData();
    private TestResults.Builder vehicleSubmittedDataTwo = TestResultsData.buildTestResultsSubmittedData();

    private void validateSavedData(String... testCodes) {

        testResultsSteps.getTestResults(vehicleSubmittedDataOne.build().getSystemNumber(), TestResultsStatus.SUBMITTED);
        testResultsSteps.statusCodeShouldBe(200);
        testResultsSteps.validateTestCode((TestResultsGet) vehicleSubmittedDataOne.build(), testCodes);
    }

    @Title("CVSB-840 / CVSB-3360 - Map the test code with the test type - not a linked test type - Data Set 1")
    @Test
    public void testTestCodeMappingNoLinkedTestTypeDataSet1() {
        // TEST SETUP
        //generate random system number
        String randomSystemNumber = GenericData.generateRandomSystemNumber();
        //generate random Vin
        String randomVin = GenericData.generateRandomVin();
        //generate random Vrm
        String randomVrm = GenericData.generateRandomVrm();
        // read post request body from file
        String postRequestBody = GenericData.readJsonValueFromFile("test-results_submitted_post.json","$");
        // create alteration to change system number in the request body with the random system number
        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$.systemNumber", randomSystemNumber,"","REPLACE");
        // create alteration to change Vin in the request body with the random generated Vin
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin,"","REPLACE");
        // create alteration to change primary vrm in the request body with the random generated primary vrm
        JsonPathAlteration alterationVrm = new JsonPathAlteration("$.primaryVrm", randomVrm,"","REPLACE");
        // create alteration to change vehicle size in the request body
        JsonPathAlteration alterationVehicleSize = new JsonPathAlteration("$.vehicleSize", "large","","REPLACE");
        // create alteration to change vehicle type in the request body
        JsonPathAlteration alterationVehicleType = new JsonPathAlteration("$.vehicleType", "psv","","REPLACE");
        // create alteration to change vehicle configuration in the request body
        JsonPathAlteration alterationVehicleConfiguration = new JsonPathAlteration("$.vehicleConfiguration", "rigid","","REPLACE");
        // create alteration to change no of axles in the request body
        JsonPathAlteration alterationNoOfAxles = new JsonPathAlteration("$.noOfAxles", 2,"","REPLACE");
        // create alteration to change test type in the request body
        JsonPathAlteration alterationTestType = new JsonPathAlteration("$.testTypes[0].testTypeId", "1","","REPLACE");
        // initialize the alterations list with both declared alteration
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(alterationVin,
                alterationVrm,
                alterationSystemNumber,
                alterationVehicleSize,
                alterationVehicleType,
                alterationVehicleConfiguration,
                alterationNoOfAxles,
                alterationTestType
        ));

        testResultsSteps.postVehicleTestResultsWithAlterations(postRequestBody, alterations);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.getTestResults(randomSystemNumber);
        testResultsSteps.valueForFieldInPathShouldBe("[0].testTypes[0].testCode", "aal");
    }

    @Title("CVSB-840 / CVSB-3360 - Map the test code with the test type - not a linked test type - Data Set 2")
    @Test
    public void testTestCodeMappingNoLinkedTestTypeDataSet2() {
        // TEST SETUP
        //generate random system number
        String randomSystemNumber = GenericData.generateRandomSystemNumber();
        //generate random Vin
        String randomVin = GenericData.generateRandomVin();
        //generate random Vrm
        String randomVrm = GenericData.generateRandomVrm();
        // read post request body from file
        String postRequestBody = GenericData.readJsonValueFromFile("test-results_submitted_post.json","$");
        // create alteration to change system number in the request body with the random system number
        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$.systemNumber", randomSystemNumber,"","REPLACE");
        // create alteration to change Vin in the request body with the random generated Vin
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin,"","REPLACE");
        // create alteration to change primary vrm in the request body with the random generated primary vrm
        JsonPathAlteration alterationVrm = new JsonPathAlteration("$.primaryVrm", randomVrm,"","REPLACE");
        // create alteration to change vehicle size in the request body
        JsonPathAlteration alterationVehicleSize = new JsonPathAlteration("$.vehicleSize", "small","","REPLACE");
        // create alteration to change vehicle type in the request body
        JsonPathAlteration alterationVehicleType = new JsonPathAlteration("$.vehicleType", "psv","","REPLACE");
        // create alteration to change vehicle configuration in the request body
        JsonPathAlteration alterationVehicleConfiguration = new JsonPathAlteration("$.vehicleConfiguration", "rigid","","REPLACE");
        // create alteration to change no of axles in the request body
        JsonPathAlteration alterationNoOfAxles = new JsonPathAlteration("$.noOfAxles", 3,"","REPLACE");
        // create alteration to change test type in the request body
        JsonPathAlteration alterationTestType = new JsonPathAlteration("$.testTypes[0].testTypeId", "1","","REPLACE");
        // initialize the alterations list with both declared alteration
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(alterationVin,
                alterationVrm,
                alterationSystemNumber,
                alterationVehicleSize,
                alterationVehicleType,
                alterationVehicleConfiguration,
                alterationNoOfAxles,
                alterationTestType
        ));

        testResultsSteps.postVehicleTestResultsWithAlterations(postRequestBody, alterations);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.getTestResults(randomSystemNumber);
        testResultsSteps.valueForFieldInPathShouldBe("[0].testTypes[0].testCode", "aas");
    }

    @Title("CVSB-840 / CVSB-3360 - Map the test code with the test type - not a linked test type - Data Set 3")
    @Test
    public void testTestCodeMappingNoLinkedTestTypeDataSet3() {
        // TEST SETUP
        //generate random system number
        String randomSystemNumber = GenericData.generateRandomSystemNumber();
        //generate random Vin
        String randomVin = GenericData.generateRandomVin();
        //generate random Vrm
        String randomVrm = GenericData.generateRandomVrm();
        // read post request body from file
        String postRequestBody = GenericData.readJsonValueFromFile("test-results_submitted_post.json","$");
        // create alteration to change system number in the request body with the random system number
        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$.systemNumber", randomSystemNumber,"","REPLACE");
        // create alteration to change Vin in the request body with the random generated Vin
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin,"","REPLACE");
        // create alteration to change primary vrm in the request body with the random generated primary vrm
        JsonPathAlteration alterationVrm = new JsonPathAlteration("$.primaryVrm", randomVrm,"","REPLACE");
        // create alteration to change vehicle size in the request body
        JsonPathAlteration alterationVehicleSize = new JsonPathAlteration("$.vehicleSize", "large","","REPLACE");
        // create alteration to change vehicle type in the request body
        JsonPathAlteration alterationVehicleType = new JsonPathAlteration("$.vehicleType", "psv","","REPLACE");
        // create alteration to change vehicle configuration in the request body
        JsonPathAlteration alterationVehicleConfiguration = new JsonPathAlteration("$.vehicleConfiguration", "articulated","","REPLACE");
        // create alteration to change no of axles in the request body
        JsonPathAlteration alterationNoOfAxles = new JsonPathAlteration("$.noOfAxles", 2,"","REPLACE");
        // create alteration to change test type in the request body
        JsonPathAlteration alterationTestType = new JsonPathAlteration("$.testTypes[0].testTypeId", "1","","REPLACE");
        // initialize the alterations list with both declared alteration
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(alterationVin,
                alterationVrm,
                alterationSystemNumber,
                alterationVehicleSize,
                alterationVehicleType,
                alterationVehicleConfiguration,
                alterationNoOfAxles,
                alterationTestType
        ));

        testResultsSteps.postVehicleTestResultsWithAlterations(postRequestBody, alterations);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.getTestResults(randomSystemNumber);
        testResultsSteps.valueForFieldInPathShouldBe("[0].testTypes[0].testCode", "adl");
    }

    @Title("CVSB-840 / CVSB-3360 - Map the test code with the test type - not a linked test type - Data Set 4")
    @Test
    public void testTestCodeMappingNoLinkedTestTypeDataSet4() {
        // TEST SETUP
        //generate random system number
        String randomSystemNumber = GenericData.generateRandomSystemNumber();
        //generate random Vin
        String randomVin = GenericData.generateRandomVin();
        //generate random Vrm
        String randomVrm = GenericData.generateRandomVrm();
        // read post request body from file
        String postRequestBody = GenericData.readJsonValueFromFile("test-results_submitted_post.json","$");
        // create alteration to change system number in the request body with the random system number
        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$.systemNumber", randomSystemNumber,"","REPLACE");
        // create alteration to change Vin in the request body with the random generated Vin
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin,"","REPLACE");
        // create alteration to change primary vrm in the request body with the random generated primary vrm
        JsonPathAlteration alterationVrm = new JsonPathAlteration("$.primaryVrm", randomVrm,"","REPLACE");
        // create alteration to change vehicle size in the request body
        JsonPathAlteration alterationVehicleSize = new JsonPathAlteration("$.vehicleSize", "large","","REPLACE");
        // create alteration to change vehicle type in the request body
        JsonPathAlteration alterationVehicleType = new JsonPathAlteration("$.vehicleType", "psv","","REPLACE");
        // create alteration to change vehicle configuration in the request body
        JsonPathAlteration alterationVehicleConfiguration = new JsonPathAlteration("$.vehicleConfiguration", "rigid","","REPLACE");
        // create alteration to change no of axles in the request body
        JsonPathAlteration alterationNoOfAxles = new JsonPathAlteration("$.noOfAxles", 2,"","REPLACE");
        // create alteration to change test type in the request body
        JsonPathAlteration alterationTestType = new JsonPathAlteration("$.testTypes[0].testTypeId", "30","","REPLACE");
        // initialize the alterations list with both declared alteration
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(alterationVin,
                alterationVrm,
                alterationSystemNumber,
                alterationVehicleSize,
                alterationVehicleType,
                alterationVehicleConfiguration,
                alterationNoOfAxles,
                alterationTestType
        ));

        testResultsSteps.postVehicleTestResultsWithAlterations(postRequestBody, alterations);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.getTestResults(randomSystemNumber);
        testResultsSteps.valueForFieldInPathShouldBe("[0].testTypes[0].testCode", "qal");
    }

    @Title("CVSB-840 / CVSB-3360 - Map the test code with the test type - not a linked test type - Data Set 5")
    @Test
    public void testTestCodeMappingNoLinkedTestTypeDataSet5() {
        // TEST SETUP
        //generate random system number
        String randomSystemNumber = GenericData.generateRandomSystemNumber();
        //generate random Vin
        String randomVin = GenericData.generateRandomVin();
        //generate random Vrm
        String randomVrm = GenericData.generateRandomVrm();
        // read post request body from file
        String postRequestBody = GenericData.readJsonValueFromFile("test-results_submitted_post.json","$");
        // create alteration to change system number in the request body with the random system number
        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$.systemNumber", randomSystemNumber,"","REPLACE");
        // create alteration to change Vin in the request body with the random generated Vin
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin,"","REPLACE");
        // create alteration to change primary vrm in the request body with the random generated primary vrm
        JsonPathAlteration alterationVrm = new JsonPathAlteration("$.primaryVrm", randomVrm,"","REPLACE");
        // create alteration to change vehicle size in the request body
        JsonPathAlteration alterationVehicleSize = new JsonPathAlteration("$.vehicleSize", "small","","REPLACE");
        // create alteration to change vehicle type in the request body
        JsonPathAlteration alterationVehicleType = new JsonPathAlteration("$.vehicleType", "psv","","REPLACE");
        // create alteration to change vehicle configuration in the request body
        JsonPathAlteration alterationVehicleConfiguration = new JsonPathAlteration("$.vehicleConfiguration", "rigid","","REPLACE");
        // create alteration to change no of axles in the request body
        JsonPathAlteration alterationNoOfAxles = new JsonPathAlteration("$.noOfAxles", 3,"","REPLACE");
        // create alteration to change test type in the request body
        JsonPathAlteration alterationTestType = new JsonPathAlteration("$.testTypes[0].testTypeId", "30","","REPLACE");
        // initialize the alterations list with both declared alteration
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(alterationVin,
                alterationVrm,
                alterationSystemNumber,
                alterationVehicleSize,
                alterationVehicleType,
                alterationVehicleConfiguration,
                alterationNoOfAxles,
                alterationTestType
        ));

        testResultsSteps.postVehicleTestResultsWithAlterations(postRequestBody, alterations);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.getTestResults(randomSystemNumber);
        testResultsSteps.valueForFieldInPathShouldBe("[0].testTypes[0].testCode", "qgs");
    }

    @Title("CVSB-840 / CVSB-3360 - Map the test code with the test type - not a linked test type - Data Set 6")
    @Test
    public void testTestCodeMappingNoLinkedTestTypeDataSet6() {
        // TEST SETUP
        //generate random system number
        String randomSystemNumber = GenericData.generateRandomSystemNumber();
        //generate random Vin
        String randomVin = GenericData.generateRandomVin();
        //generate random Vrm
        String randomVrm = GenericData.generateRandomVrm();
        // read post request body from file
        String postRequestBody = GenericData.readJsonValueFromFile("test-results_submitted_post.json","$");
        // create alteration to change system number in the request body with the random system number
        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$.systemNumber", randomSystemNumber,"","REPLACE");
        // create alteration to change Vin in the request body with the random generated Vin
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin,"","REPLACE");
        // create alteration to change primary vrm in the request body with the random generated primary vrm
        JsonPathAlteration alterationVrm = new JsonPathAlteration("$.primaryVrm", randomVrm,"","REPLACE");
        // create alteration to change vehicle size in the request body
        JsonPathAlteration alterationVehicleSize = new JsonPathAlteration("$.vehicleSize", "large","","REPLACE");
        // create alteration to change vehicle type in the request body
        JsonPathAlteration alterationVehicleType = new JsonPathAlteration("$.vehicleType", "psv","","REPLACE");
        // create alteration to change vehicle configuration in the request body
        JsonPathAlteration alterationVehicleConfiguration = new JsonPathAlteration("$.vehicleConfiguration", "rigid","","REPLACE");
        // create alteration to change no of axles in the request body
        JsonPathAlteration alterationNoOfAxles = new JsonPathAlteration("$.noOfAxles", 3,"","REPLACE");
        // create alteration to change test type in the request body
        JsonPathAlteration alterationTestType = new JsonPathAlteration("$.testTypes[0].testTypeId", "30","","REPLACE");
        // initialize the alterations list with both declared alteration
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(alterationVin,
                alterationVrm,
                alterationSystemNumber,
                alterationVehicleSize,
                alterationVehicleType,
                alterationVehicleConfiguration,
                alterationNoOfAxles,
                alterationTestType
        ));

        testResultsSteps.postVehicleTestResultsWithAlterations(postRequestBody, alterations);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.getTestResults(randomSystemNumber);
        testResultsSteps.valueForFieldInPathShouldBe("[0].testTypes[0].testCode", "qgl");
    }

    @Title("CVSB-840 / CVSB-3360 - Map the test code with the test type - not a linked test type - Data Set 7")
    @Test
    public void testTestCodeMappingNoLinkedTestTypeDataSet7() {
        // TEST SETUP
        //generate random system number
        String randomSystemNumber = GenericData.generateRandomSystemNumber();
        //generate random Vin
        String randomVin = GenericData.generateRandomVin();
        //generate random Vrm
        String randomVrm = GenericData.generateRandomVrm();
        // read post request body from file
        String postRequestBody = GenericData.readJsonValueFromFile("test-results_submitted_post.json","$");
        // create alteration to change system number in the request body with the random system number
        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$.systemNumber", randomSystemNumber,"","REPLACE");
        // create alteration to change Vin in the request body with the random generated Vin
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin,"","REPLACE");
        // create alteration to change primary vrm in the request body with the random generated primary vrm
        JsonPathAlteration alterationVrm = new JsonPathAlteration("$.primaryVrm", randomVrm,"","REPLACE");
        // create alteration to change vehicle size in the request body
        JsonPathAlteration alterationVehicleSize = new JsonPathAlteration("$.vehicleSize", "large","","REPLACE");
        // create alteration to change vehicle type in the request body
        JsonPathAlteration alterationVehicleType = new JsonPathAlteration("$.vehicleType", "psv","","REPLACE");
        // create alteration to change vehicle configuration in the request body
        JsonPathAlteration alterationVehicleConfiguration = new JsonPathAlteration("$.vehicleConfiguration", "rigid","","REPLACE");
        // create alteration to change no of axles in the request body
        JsonPathAlteration alterationNoOfAxles = new JsonPathAlteration("$.noOfAxles", 2,"","REPLACE");
        // create alteration to change test type in the request body
        JsonPathAlteration alterationTestType = new JsonPathAlteration("$.testTypes[0].testTypeId", "39","","REPLACE");
        // initialize the alterations list with both declared alteration
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(alterationVin,
                alterationVrm,
                alterationSystemNumber,
                alterationVehicleSize,
                alterationVehicleType,
                alterationVehicleConfiguration,
                alterationNoOfAxles,
                alterationTestType
        ));

        testResultsSteps.postVehicleTestResultsWithAlterations(postRequestBody, alterations);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.getTestResults(randomSystemNumber);
        testResultsSteps.valueForFieldInPathShouldBe("[0].testTypes[0].testCode", "lbp");
    }

    @Title("CVSB-840 / CVSB-3360 - Map the test code with the test type - not a linked test type - Data Set 8")
    @Test
    public void testTestCodeMappingNoLinkedTestTypeDataSet8() {
        // TEST SETUP
        //generate random system number
        String randomSystemNumber = GenericData.generateRandomSystemNumber();
        //generate random Vin
        String randomVin = GenericData.generateRandomVin();
        //generate random Vrm
        String randomVrm = GenericData.generateRandomVrm();
        // read post request body from file
        String postRequestBody = GenericData.readJsonValueFromFile("test-results_submitted_post.json","$");
        // create alteration to change system number in the request body with the random system number
        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$.systemNumber", randomSystemNumber,"","REPLACE");
        // create alteration to change Vin in the request body with the random generated Vin
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin,"","REPLACE");
        // create alteration to change primary vrm in the request body with the random generated primary vrm
        JsonPathAlteration alterationVrm = new JsonPathAlteration("$.primaryVrm", randomVrm,"","REPLACE");
        // create alteration to change vehicle size in the request body
        JsonPathAlteration alterationVehicleSize = new JsonPathAlteration("$.vehicleSize", "small","","REPLACE");
        // create alteration to change vehicle type in the request body
        JsonPathAlteration alterationVehicleType = new JsonPathAlteration("$.vehicleType", "psv","","REPLACE");
        // create alteration to change vehicle configuration in the request body
        JsonPathAlteration alterationVehicleConfiguration = new JsonPathAlteration("$.vehicleConfiguration", "rigid","","REPLACE");
        // create alteration to change no of axles in the request body
        JsonPathAlteration alterationNoOfAxles = new JsonPathAlteration("$.noOfAxles", 3,"","REPLACE");
        // create alteration to change test type in the request body
        JsonPathAlteration alterationTestType = new JsonPathAlteration("$.testTypes[0].testTypeId", "39","","REPLACE");
        // initialize the alterations list with both declared alteration
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(alterationVin,
                alterationVrm,
                alterationSystemNumber,
                alterationVehicleSize,
                alterationVehicleType,
                alterationVehicleConfiguration,
                alterationNoOfAxles,
                alterationTestType
        ));

        testResultsSteps.postVehicleTestResultsWithAlterations(postRequestBody, alterations);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.getTestResults(randomSystemNumber);
        testResultsSteps.valueForFieldInPathShouldBe("[0].testTypes[0].testCode", "lbp");
    }

    @Title("CVSB-840 / CVSB-3360 - Map the test code with the test type - not a linked test type - Data Set 9")
    @Test
    public void testTestCodeMappingNoLinkedTestTypeDataSet9() {
        // TEST SETUP
        //generate random system number
        String randomSystemNumber = GenericData.generateRandomSystemNumber();
        //generate random Vin
        String randomVin = GenericData.generateRandomVin();
        //generate random Vrm
        String randomVrm = GenericData.generateRandomVrm();
        // read post request body from file
        String postRequestBody = GenericData.readJsonValueFromFile("test-results_submitted_post.json","$");
        // create alteration to change system number in the request body with the random system number
        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$.systemNumber", randomSystemNumber,"","REPLACE");
        // create alteration to change Vin in the request body with the random generated Vin
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin,"","REPLACE");
        // create alteration to change primary vrm in the request body with the random generated primary vrm
        JsonPathAlteration alterationVrm = new JsonPathAlteration("$.primaryVrm", randomVrm,"","REPLACE");
        // create alteration to change vehicle size in the request body
        JsonPathAlteration alterationVehicleSize = new JsonPathAlteration("$.vehicleSize", "large","","REPLACE");
        // create alteration to change vehicle type in the request body
        JsonPathAlteration alterationVehicleType = new JsonPathAlteration("$.vehicleType", "psv","","REPLACE");
        // create alteration to change vehicle configuration in the request body
        JsonPathAlteration alterationVehicleConfiguration = new JsonPathAlteration("$.vehicleConfiguration", "articulated","","REPLACE");
        // create alteration to change no of axles in the request body
        JsonPathAlteration alterationNoOfAxles = new JsonPathAlteration("$.noOfAxles", 3,"","REPLACE");
        // create alteration to change test type in the request body
        JsonPathAlteration alterationTestType = new JsonPathAlteration("$.testTypes[0].testTypeId", "7","","REPLACE");
        // initialize the alterations list with both declared alteration
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(alterationVin,
                alterationVrm,
                alterationSystemNumber,
                alterationVehicleSize,
                alterationVehicleType,
                alterationVehicleConfiguration,
                alterationNoOfAxles,
                alterationTestType
        ));

        testResultsSteps.postVehicleTestResultsWithAlterations(postRequestBody, alterations);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.getTestResults(randomSystemNumber);
        testResultsSteps.valueForFieldInPathShouldBe("[0].testTypes[0].testCode", "rhl");
    }

    @Title("CVSB-840 / CVSB-3364 - AC2 Map the test code with the test type - linked test type with a specific linked test code - Scenario 1 - Data Set 1")
    @Test
    public void testTestCodeMappingSpecificLinkedTestTypeScenario1DataSet1() {
        // TEST SETUP
        //generate random system number
        String randomSystemNumber = GenericData.generateRandomSystemNumber();
        //generate random Vin
        String randomVin = GenericData.generateRandomVin();
        //generate random Vrm
        String randomVrm = GenericData.generateRandomVrm();
        // read post request body from file
        String postRequestBody = GenericData.readJsonValueFromFile("test-results_submitted_post.json","$");
        String testType = GenericData.readJsonValueFromFile("test-results_submitted_post.json","$.testTypes[0]");
        // create alteration to change system number in the request body with the random system number
        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$.systemNumber", randomSystemNumber,"","REPLACE");
        // create alteration to change Vin in the request body with the random generated Vin
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin,"","REPLACE");
        // create alteration to change primary vrm in the request body with the random generated primary vrm
        JsonPathAlteration alterationVrm = new JsonPathAlteration("$.primaryVrm", randomVrm,"","REPLACE");
        // create alteration to change vehicle size in the request body
        JsonPathAlteration alterationVehicleSize = new JsonPathAlteration("$.vehicleSize", "large","","REPLACE");
        // create alteration to change vehicle type in the request body
        JsonPathAlteration alterationVehicleType = new JsonPathAlteration("$.vehicleType", "psv","","REPLACE");
        // create alteration to change vehicle configuration in the request body
        JsonPathAlteration alterationVehicleConfiguration = new JsonPathAlteration("$.vehicleConfiguration", "rigid","","REPLACE");
        // create alteration to change no of axles in the request body
        JsonPathAlteration alterationNoOfAxles = new JsonPathAlteration("$.noOfAxles", 2,"","REPLACE");
        // create alteration to change test type in the request body
        JsonPathAlteration alterationTestType1 = new JsonPathAlteration("$.testTypes[0].testTypeId", "1","","REPLACE");
        // create alteration to add test type in the request body
        JsonPathAlteration alterationAddTestType = new JsonPathAlteration("$.testTypes", testType,"","ADD_VALUE");
        // create alteration to change second test type in the request body
        JsonPathAlteration alterationTestType2 = new JsonPathAlteration("$.testTypes[1].testTypeId", "39","","REPLACE");
        // initialize the alterations list with both declared alteration
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(alterationVin,
                alterationVrm,
                alterationSystemNumber,
                alterationVehicleSize,
                alterationVehicleType,
                alterationVehicleConfiguration,
                alterationNoOfAxles,
                alterationTestType1,
                alterationAddTestType,
                alterationTestType2
        ));

        testResultsSteps.postVehicleTestResultsWithAlterations(postRequestBody, alterations);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.getTestResults(randomSystemNumber);
        testResultsSteps.valueForFieldInPathShouldBe("[0].testTypes[0].testCode", "aal");
        testResultsSteps.valueForFieldInPathShouldBe("[0].testTypes[1].testCode", "lcp");
    }

    @Title("CVSB-840 / CVSB-3364 - AC2 Map the test code with the test type - linked test type with a specific linked test code - Scenario 1 - Data Set 2")
    @Test
    public void testTestCodeMappingSpecificLinkedTestTypeScenario1DataSet2() {
        // TEST SETUP
        //generate random system number
        String randomSystemNumber = GenericData.generateRandomSystemNumber();
        //generate random Vin
        String randomVin = GenericData.generateRandomVin();
        //generate random Vrm
        String randomVrm = GenericData.generateRandomVrm();
        // read post request body from file
        String postRequestBody = GenericData.readJsonValueFromFile("test-results_submitted_post.json","$");
        String testType = GenericData.readJsonValueFromFile("test-results_submitted_post.json","$.testTypes[0]");
        // create alteration to change system number in the request body with the random system number
        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$.systemNumber", randomSystemNumber,"","REPLACE");
        // create alteration to change Vin in the request body with the random generated Vin
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin,"","REPLACE");
        // create alteration to change primary vrm in the request body with the random generated primary vrm
        JsonPathAlteration alterationVrm = new JsonPathAlteration("$.primaryVrm", randomVrm,"","REPLACE");
        // create alteration to change vehicle size in the request body
        JsonPathAlteration alterationVehicleSize = new JsonPathAlteration("$.vehicleSize", "small","","REPLACE");
        // create alteration to change vehicle type in the request body
        JsonPathAlteration alterationVehicleType = new JsonPathAlteration("$.vehicleType", "psv","","REPLACE");
        // create alteration to change vehicle configuration in the request body
        JsonPathAlteration alterationVehicleConfiguration = new JsonPathAlteration("$.vehicleConfiguration", "rigid","","REPLACE");
        // create alteration to change no of axles in the request body
        JsonPathAlteration alterationNoOfAxles = new JsonPathAlteration("$.noOfAxles", 3,"","REPLACE");
        // create alteration to change test type in the request body
        JsonPathAlteration alterationTestType1 = new JsonPathAlteration("$.testTypes[0].testTypeId", "1","","REPLACE");
        // create alteration to add test type in the request body
        JsonPathAlteration alterationAddTestType = new JsonPathAlteration("$.testTypes", testType,"","ADD_VALUE");
        // create alteration to change second test type in the request body
        JsonPathAlteration alterationTestType2 = new JsonPathAlteration("$.testTypes[1].testTypeId", "39","","REPLACE");
        // initialize the alterations list with both declared alteration
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(alterationVin,
                alterationVrm,
                alterationSystemNumber,
                alterationVehicleSize,
                alterationVehicleType,
                alterationVehicleConfiguration,
                alterationNoOfAxles,
                alterationTestType1,
                alterationAddTestType,
                alterationTestType2
        ));

        testResultsSteps.postVehicleTestResultsWithAlterations(postRequestBody, alterations);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.getTestResults(randomSystemNumber);
        testResultsSteps.valueForFieldInPathShouldBe("[0].testTypes[0].testCode", "aas");
        testResultsSteps.valueForFieldInPathShouldBe("[0].testTypes[1].testCode", "lcp");
    }

    @Title("CVSB-840 / CVSB-3364 - AC2 Map the test code with the test type - linked test type with a specific linked test code - Scenario 1 - Data Set 3")
    @Test
    public void testTestCodeMappingSpecificLinkedTestTypeScenario1DataSet3() {
        // TEST SETUP
        //generate random system number
        String randomSystemNumber = GenericData.generateRandomSystemNumber();
        //generate random Vin
        String randomVin = GenericData.generateRandomVin();
        //generate random Vrm
        String randomVrm = GenericData.generateRandomVrm();
        // read post request body from file
        String postRequestBody = GenericData.readJsonValueFromFile("test-results_submitted_post.json","$");
        String testType = GenericData.readJsonValueFromFile("test-results_submitted_post.json","$.testTypes[0]");
        // create alteration to change system number in the request body with the random system number
        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$.systemNumber", randomSystemNumber,"","REPLACE");
        // create alteration to change Vin in the request body with the random generated Vin
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin,"","REPLACE");
        // create alteration to change primary vrm in the request body with the random generated primary vrm
        JsonPathAlteration alterationVrm = new JsonPathAlteration("$.primaryVrm", randomVrm,"","REPLACE");
        // create alteration to change vehicle size in the request body
        JsonPathAlteration alterationVehicleSize = new JsonPathAlteration("$.vehicleSize", "large","","REPLACE");
        // create alteration to change vehicle type in the request body
        JsonPathAlteration alterationVehicleType = new JsonPathAlteration("$.vehicleType", "psv","","REPLACE");
        // create alteration to change vehicle configuration in the request body
        JsonPathAlteration alterationVehicleConfiguration = new JsonPathAlteration("$.vehicleConfiguration", "rigid","","REPLACE");
        // create alteration to change no of axles in the request body
        JsonPathAlteration alterationNoOfAxles = new JsonPathAlteration("$.noOfAxles", 2,"","REPLACE");
        // create alteration to change test type in the request body
        JsonPathAlteration alterationTestType1 = new JsonPathAlteration("$.testTypes[0].testTypeId", "39","","REPLACE");
        // create alteration to add test type in the request body
        JsonPathAlteration alterationAddTestType = new JsonPathAlteration("$.testTypes", testType,"","ADD_VALUE");
        // create alteration to change second test type in the request body
        JsonPathAlteration alterationTestType2 = new JsonPathAlteration("$.testTypes[1].testTypeId", "1","","REPLACE");
        // initialize the alterations list with both declared alteration
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(alterationVin,
                alterationVrm,
                alterationSystemNumber,
                alterationVehicleSize,
                alterationVehicleType,
                alterationVehicleConfiguration,
                alterationNoOfAxles,
                alterationTestType1,
                alterationAddTestType,
                alterationTestType2
        ));

        testResultsSteps.postVehicleTestResultsWithAlterations(postRequestBody, alterations);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.getTestResults(randomSystemNumber);
        testResultsSteps.valueForFieldInPathShouldBe("[0].testTypes[0].testCode", "lcp");
        testResultsSteps.valueForFieldInPathShouldBe("[0].testTypes[1].testCode", "aal");
    }

    @Title("CVSB-840 / CVSB-3364 - AC2 Map the test code with the test type - linked test type with a specific linked test code - Scenario 1 - Data Set 4")
    @Test
    public void testTestCodeMappingSpecificLinkedTestTypeScenario1DataSet4() {
        // TEST SETUP
        //generate random system number
        String randomSystemNumber = GenericData.generateRandomSystemNumber();
        //generate random Vin
        String randomVin = GenericData.generateRandomVin();
        //generate random Vrm
        String randomVrm = GenericData.generateRandomVrm();
        // read post request body from file
        String postRequestBody = GenericData.readJsonValueFromFile("test-results_submitted_post.json","$");
        String testType = GenericData.readJsonValueFromFile("test-results_submitted_post.json","$.testTypes[0]");
        // create alteration to change system number in the request body with the random system number
        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$.systemNumber", randomSystemNumber,"","REPLACE");
        // create alteration to change Vin in the request body with the random generated Vin
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin,"","REPLACE");
        // create alteration to change primary vrm in the request body with the random generated primary vrm
        JsonPathAlteration alterationVrm = new JsonPathAlteration("$.primaryVrm", randomVrm,"","REPLACE");
        // create alteration to change vehicle size in the request body
        JsonPathAlteration alterationVehicleSize = new JsonPathAlteration("$.vehicleSize", "small","","REPLACE");
        // create alteration to change vehicle type in the request body
        JsonPathAlteration alterationVehicleType = new JsonPathAlteration("$.vehicleType", "psv","","REPLACE");
        // create alteration to change vehicle configuration in the request body
        JsonPathAlteration alterationVehicleConfiguration = new JsonPathAlteration("$.vehicleConfiguration", "rigid","","REPLACE");
        // create alteration to change no of axles in the request body
        JsonPathAlteration alterationNoOfAxles = new JsonPathAlteration("$.noOfAxles", 3,"","REPLACE");
        // create alteration to change test type in the request body
        JsonPathAlteration alterationTestType1 = new JsonPathAlteration("$.testTypes[0].testTypeId", "39","","REPLACE");
        // create alteration to add test type in the request body
        JsonPathAlteration alterationAddTestType = new JsonPathAlteration("$.testTypes", testType,"","ADD_VALUE");
        // create alteration to change second test type in the request body
        JsonPathAlteration alterationTestType2 = new JsonPathAlteration("$.testTypes[1].testTypeId", "1","","REPLACE");
        // initialize the alterations list with both declared alteration
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(alterationVin,
                alterationVrm,
                alterationSystemNumber,
                alterationVehicleSize,
                alterationVehicleType,
                alterationVehicleConfiguration,
                alterationNoOfAxles,
                alterationTestType1,
                alterationAddTestType,
                alterationTestType2
        ));

        testResultsSteps.postVehicleTestResultsWithAlterations(postRequestBody, alterations);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.getTestResults(randomSystemNumber);
        testResultsSteps.valueForFieldInPathShouldBe("[0].testTypes[0].testCode", "lcp");
        testResultsSteps.valueForFieldInPathShouldBe("[0].testTypes[1].testCode", "aas");
    }

    @Title("CVSB-840 / CVSB-3364 - AC2 Map the test code with the test type - linked test type with a specific linked test code - Scenario 2 - Data Set 1")
    @Test
    public void testTestCodeMappingSpecificLinkedTestTypeScenario2DataSet1() {
        // TEST SETUP
        //generate random system number
        String randomSystemNumber = GenericData.generateRandomSystemNumber();
        //generate random Vin
        String randomVin = GenericData.generateRandomVin();
        //generate random Vrm
        String randomVrm = GenericData.generateRandomVrm();
        // read post request body from file
        String postRequestBody = GenericData.readJsonValueFromFile("test-results_submitted_post.json","$");
        String testType = GenericData.readJsonValueFromFile("test-results_submitted_post.json","$.testTypes[0]");
        // create alteration to change system number in the request body with the random system number
        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$.systemNumber", randomSystemNumber,"","REPLACE");
        // create alteration to change Vin in the request body with the random generated Vin
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin,"","REPLACE");
        // create alteration to change primary vrm in the request body with the random generated primary vrm
        JsonPathAlteration alterationVrm = new JsonPathAlteration("$.primaryVrm", randomVrm,"","REPLACE");
        // create alteration to change vehicle size in the request body
        JsonPathAlteration alterationVehicleSize = new JsonPathAlteration("$.vehicleSize", "large","","REPLACE");
        // create alteration to change vehicle type in the request body
        JsonPathAlteration alterationVehicleType = new JsonPathAlteration("$.vehicleType", "psv","","REPLACE");
        // create alteration to change vehicle configuration in the request body
        JsonPathAlteration alterationVehicleConfiguration = new JsonPathAlteration("$.vehicleConfiguration", "rigid","","REPLACE");
        // create alteration to change no of axles in the request body
        JsonPathAlteration alterationNoOfAxles = new JsonPathAlteration("$.noOfAxles", 2,"","REPLACE");
        // create alteration to change test type in the request body
        JsonPathAlteration alterationTestType1 = new JsonPathAlteration("$.testTypes[0].testTypeId", "30","","REPLACE");
        // create alteration to add test type in the request body
        JsonPathAlteration alterationAddTestType = new JsonPathAlteration("$.testTypes", testType,"","ADD_VALUE");
        // create alteration to change second test type in the request body
        JsonPathAlteration alterationTestType2 = new JsonPathAlteration("$.testTypes[1].testTypeId", "39","","REPLACE");
        // initialize the alterations list with both declared alteration
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(alterationVin,
                alterationVrm,
                alterationSystemNumber,
                alterationVehicleSize,
                alterationVehicleType,
                alterationVehicleConfiguration,
                alterationNoOfAxles,
                alterationTestType1,
                alterationAddTestType,
                alterationTestType2
        ));

        testResultsSteps.postVehicleTestResultsWithAlterations(postRequestBody, alterations);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.getTestResults(randomSystemNumber);
        testResultsSteps.valueForFieldInPathShouldBe("[0].testTypes[0].testCode", "qal");
        testResultsSteps.valueForFieldInPathShouldBe("[0].testTypes[1].testCode", "lcp");
    }

    @Title("CVSB-840 / CVSB-3364 - AC2 Map the test code with the test type - linked test type with a specific linked test code - Scenario 2 - Data Set 2")
    @Test
    public void testTestCodeMappingSpecificLinkedTestTypeScenario2DataSet2() {
        // TEST SETUP
        //generate random system number
        String randomSystemNumber = GenericData.generateRandomSystemNumber();
        //generate random Vin
        String randomVin = GenericData.generateRandomVin();
        //generate random Vrm
        String randomVrm = GenericData.generateRandomVrm();
        // read post request body from file
        String postRequestBody = GenericData.readJsonValueFromFile("test-results_submitted_post.json","$");
        String testType = GenericData.readJsonValueFromFile("test-results_submitted_post.json","$.testTypes[0]");
        // create alteration to change system number in the request body with the random system number
        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$.systemNumber", randomSystemNumber,"","REPLACE");
        // create alteration to change Vin in the request body with the random generated Vin
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin,"","REPLACE");
        // create alteration to change primary vrm in the request body with the random generated primary vrm
        JsonPathAlteration alterationVrm = new JsonPathAlteration("$.primaryVrm", randomVrm,"","REPLACE");
        // create alteration to change vehicle size in the request body
        JsonPathAlteration alterationVehicleSize = new JsonPathAlteration("$.vehicleSize", "small","","REPLACE");
        // create alteration to change vehicle type in the request body
        JsonPathAlteration alterationVehicleType = new JsonPathAlteration("$.vehicleType", "psv","","REPLACE");
        // create alteration to change vehicle configuration in the request body
        JsonPathAlteration alterationVehicleConfiguration = new JsonPathAlteration("$.vehicleConfiguration", "rigid","","REPLACE");
        // create alteration to change no of axles in the request body
        JsonPathAlteration alterationNoOfAxles = new JsonPathAlteration("$.noOfAxles", 2,"","REPLACE");
        // create alteration to change test type in the request body
        JsonPathAlteration alterationTestType1 = new JsonPathAlteration("$.testTypes[0].testTypeId", "30","","REPLACE");
        // create alteration to add test type in the request body
        JsonPathAlteration alterationAddTestType = new JsonPathAlteration("$.testTypes", testType,"","ADD_VALUE");
        // create alteration to change second test type in the request body
        JsonPathAlteration alterationTestType2 = new JsonPathAlteration("$.testTypes[1].testTypeId", "39","","REPLACE");
        // initialize the alterations list with both declared alteration
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(alterationVin,
                alterationVrm,
                alterationSystemNumber,
                alterationVehicleSize,
                alterationVehicleType,
                alterationVehicleConfiguration,
                alterationNoOfAxles,
                alterationTestType1,
                alterationAddTestType,
                alterationTestType2
        ));

        testResultsSteps.postVehicleTestResultsWithAlterations(postRequestBody, alterations);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.getTestResults(randomSystemNumber);
        testResultsSteps.valueForFieldInPathShouldBe("[0].testTypes[0].testCode", "qas");
        testResultsSteps.valueForFieldInPathShouldBe("[0].testTypes[1].testCode", "lcp");
    }

    @Title("CVSB-840 / CVSB-3364 - AC2 Map the test code with the test type - linked test type with a specific linked test code - Scenario 2 - Data Set 3")
    @Test
    public void testTestCodeMappingSpecificLinkedTestTypeScenario2DataSet3() {
        // TEST SETUP
        //generate random system number
        String randomSystemNumber = GenericData.generateRandomSystemNumber();
        //generate random Vin
        String randomVin = GenericData.generateRandomVin();
        //generate random Vrm
        String randomVrm = GenericData.generateRandomVrm();
        // read post request body from file
        String postRequestBody = GenericData.readJsonValueFromFile("test-results_submitted_post.json","$");
        String testType = GenericData.readJsonValueFromFile("test-results_submitted_post.json","$.testTypes[0]");
        // create alteration to change system number in the request body with the random system number
        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$.systemNumber", randomSystemNumber,"","REPLACE");
        // create alteration to change Vin in the request body with the random generated Vin
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin,"","REPLACE");
        // create alteration to change primary vrm in the request body with the random generated primary vrm
        JsonPathAlteration alterationVrm = new JsonPathAlteration("$.primaryVrm", randomVrm,"","REPLACE");
        // create alteration to change vehicle size in the request body
        JsonPathAlteration alterationVehicleSize = new JsonPathAlteration("$.vehicleSize", "large","","REPLACE");
        // create alteration to change vehicle type in the request body
        JsonPathAlteration alterationVehicleType = new JsonPathAlteration("$.vehicleType", "psv","","REPLACE");
        // create alteration to change vehicle configuration in the request body
        JsonPathAlteration alterationVehicleConfiguration = new JsonPathAlteration("$.vehicleConfiguration", "rigid","","REPLACE");
        // create alteration to change no of axles in the request body
        JsonPathAlteration alterationNoOfAxles = new JsonPathAlteration("$.noOfAxles", 2,"","REPLACE");
        // create alteration to change test type in the request body
        JsonPathAlteration alterationTestType1 = new JsonPathAlteration("$.testTypes[0].testTypeId", "39","","REPLACE");
        // create alteration to add test type in the request body
        JsonPathAlteration alterationAddTestType = new JsonPathAlteration("$.testTypes", testType,"","ADD_VALUE");
        // create alteration to change second test type in the request body
        JsonPathAlteration alterationTestType2 = new JsonPathAlteration("$.testTypes[1].testTypeId", "30","","REPLACE");
        // initialize the alterations list with both declared alteration
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(alterationVin,
                alterationVrm,
                alterationSystemNumber,
                alterationVehicleSize,
                alterationVehicleType,
                alterationVehicleConfiguration,
                alterationNoOfAxles,
                alterationTestType1,
                alterationAddTestType,
                alterationTestType2
        ));

        testResultsSteps.postVehicleTestResultsWithAlterations(postRequestBody, alterations);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.getTestResults(randomSystemNumber);
        testResultsSteps.valueForFieldInPathShouldBe("[0].testTypes[0].testCode", "lcp");
        testResultsSteps.valueForFieldInPathShouldBe("[0].testTypes[1].testCode", "qal");
    }

    @Title("CVSB-840 / CVSB-3364 - AC2 Map the test code with the test type - linked test type with a specific linked test code - Scenario 2 - Data Set 4")
    @Test
    public void testTestCodeMappingSpecificLinkedTestTypeScenario2DataSet4() {
//        // scenario 2
//        // testTypeOne: testTypeId:39 ; vehicleType:psv ; vehicleSize:small ; vehicleConfiguration:rigid ; noOfAxles:2 ;
//        // testTypeTwo: testTypeId:30 ; vehicleType:psv ; vehicleSize:small ; vehicleConfiguration:rigid ; noOfAxles:2 ;
//        TestTypes testTypesOne = ((TestTypes) vehicleSubmittedDataOne.getTestTypes().get(0)).setTestTypeId("39");
//        TestTypes testTypesTwo = ((TestTypes) vehicleSubmittedDataTwo.getTestTypes().get(0)).setTestTypeId("30");
//
//        vehicleSubmittedDataOne.setVin(generateRandomExcludingValues(21, vehicleSubmittedDataOne.build().getVin()))
//                .setTestTypes(Arrays.asList(testTypesOne, testTypesTwo))
//                .setVrm(generateRandomExcludingValues(7, vehicleSubmittedDataOne.build().getVrm()))
//                .setVehicleType("psv")
//                .setNoOfAxles(2)
//                .setVehicleSize("small")
//                .setVehicleConfiguration(("rigid")).build();
//
//        testResultsSteps.postTestResults(vehicleSubmittedDataOne.build());
//        testResultsSteps.statusCodeShouldBe(201);
//        testResultsSteps.validateData("Test records created");
//        validateSavedData("lcp", "qas");

        // TEST SETUP
        //generate random system number
        String randomSystemNumber = GenericData.generateRandomSystemNumber();
        //generate random Vin
        String randomVin = GenericData.generateRandomVin();
        //generate random Vrm
        String randomVrm = GenericData.generateRandomVrm();
        // read post request body from file
        String postRequestBody = GenericData.readJsonValueFromFile("test-results_submitted_post.json","$");
        String testType = GenericData.readJsonValueFromFile("test-results_submitted_post.json","$.testTypes[0]");
        // create alteration to change system number in the request body with the random system number
        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$.systemNumber", randomSystemNumber,"","REPLACE");
        // create alteration to change Vin in the request body with the random generated Vin
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin,"","REPLACE");
        // create alteration to change primary vrm in the request body with the random generated primary vrm
        JsonPathAlteration alterationVrm = new JsonPathAlteration("$.primaryVrm", randomVrm,"","REPLACE");
        // create alteration to change vehicle size in the request body
        JsonPathAlteration alterationVehicleSize = new JsonPathAlteration("$.vehicleSize", "small","","REPLACE");
        // create alteration to change vehicle type in the request body
        JsonPathAlteration alterationVehicleType = new JsonPathAlteration("$.vehicleType", "psv","","REPLACE");
        // create alteration to change vehicle configuration in the request body
        JsonPathAlteration alterationVehicleConfiguration = new JsonPathAlteration("$.vehicleConfiguration", "rigid","","REPLACE");
        // create alteration to change no of axles in the request body
        JsonPathAlteration alterationNoOfAxles = new JsonPathAlteration("$.noOfAxles", 2,"","REPLACE");
        // create alteration to change test type in the request body
        JsonPathAlteration alterationTestType1 = new JsonPathAlteration("$.testTypes[0].testTypeId", "39","","REPLACE");
        // create alteration to add test type in the request body
        JsonPathAlteration alterationAddTestType = new JsonPathAlteration("$.testTypes", testType,"","ADD_VALUE");
        // create alteration to change second test type in the request body
        JsonPathAlteration alterationTestType2 = new JsonPathAlteration("$.testTypes[1].testTypeId", "30","","REPLACE");
        // initialize the alterations list with both declared alteration
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(alterationVin,
                alterationVrm,
                alterationSystemNumber,
                alterationVehicleSize,
                alterationVehicleType,
                alterationVehicleConfiguration,
                alterationNoOfAxles,
                alterationTestType1,
                alterationAddTestType,
                alterationTestType2
        ));

        testResultsSteps.postVehicleTestResultsWithAlterations(postRequestBody, alterations);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.getTestResults(randomSystemNumber);
        testResultsSteps.valueForFieldInPathShouldBe("[0].testTypes[0].testCode", "lcp");
        testResultsSteps.valueForFieldInPathShouldBe("[0].testTypes[1].testCode", "qas");
    }

    @Title("CVSB-840 / CVSB-3364 - AC2 Map the test code with the test type - linked test type with a specific linked test code - Scenario 3 - Data Set 1")
    @Test
    public void testTestCodeMappingSpecificLinkedTestTypeScenario3DataSet1() {
        // TEST SETUP
        //generate random system number
        String randomSystemNumber = GenericData.generateRandomSystemNumber();
        //generate random Vin
        String randomVin = GenericData.generateRandomVin();
        //generate random Vrm
        String randomVrm = GenericData.generateRandomVrm();
        // read post request body from file
        String postRequestBody = GenericData.readJsonValueFromFile("test-results_submitted_post.json","$");
        String testType = GenericData.readJsonValueFromFile("test-results_submitted_post.json","$.testTypes[0]");
        // create alteration to change system number in the request body with the random system number
        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$.systemNumber", randomSystemNumber,"","REPLACE");
        // create alteration to change Vin in the request body with the random generated Vin
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin,"","REPLACE");
        // create alteration to change primary vrm in the request body with the random generated primary vrm
        JsonPathAlteration alterationVrm = new JsonPathAlteration("$.primaryVrm", randomVrm,"","REPLACE");
        // create alteration to change vehicle size in the request body
        JsonPathAlteration alterationVehicleSize = new JsonPathAlteration("$.vehicleSize", "large","","REPLACE");
        // create alteration to change vehicle type in the request body
        JsonPathAlteration alterationVehicleType = new JsonPathAlteration("$.vehicleType", "psv","","REPLACE");
        // create alteration to change vehicle configuration in the request body
        JsonPathAlteration alterationVehicleConfiguration = new JsonPathAlteration("$.vehicleConfiguration", "rigid","","REPLACE");
        // create alteration to change no of axles in the request body
        JsonPathAlteration alterationNoOfAxles = new JsonPathAlteration("$.noOfAxles", 3,"","REPLACE");
        // create alteration to change test type in the request body
        JsonPathAlteration alterationTestType1 = new JsonPathAlteration("$.testTypes[0].testTypeId", "30","","REPLACE");
        // create alteration to add test type in the request body
        JsonPathAlteration alterationAddTestType = new JsonPathAlteration("$.testTypes", testType,"","ADD_VALUE");
        // create alteration to change second test type in the request body
        JsonPathAlteration alterationTestType2 = new JsonPathAlteration("$.testTypes[1].testTypeId", "39","","REPLACE");
        // initialize the alterations list with both declared alteration
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(alterationVin,
                alterationVrm,
                alterationSystemNumber,
                alterationVehicleSize,
                alterationVehicleType,
                alterationVehicleConfiguration,
                alterationNoOfAxles,
                alterationTestType1,
                alterationAddTestType,
                alterationTestType2
        ));

        testResultsSteps.postVehicleTestResultsWithAlterations(postRequestBody, alterations);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.getTestResults(randomSystemNumber);
        testResultsSteps.valueForFieldInPathShouldBe("[0].testTypes[0].testCode", "qgl");
        testResultsSteps.valueForFieldInPathShouldBe("[0].testTypes[1].testCode", "lcp");
    }

    @Title("CVSB-840 / CVSB-3364 - AC2 Map the test code with the test type - linked test type with a specific linked test code - Scenario 3 - Data Set 2")
    @Test
    public void testTestCodeMappingSpecificLinkedTestTypeScenario3DataSet2() {
        // TEST SETUP
        //generate random system number
        String randomSystemNumber = GenericData.generateRandomSystemNumber();
        //generate random Vin
        String randomVin = GenericData.generateRandomVin();
        //generate random Vrm
        String randomVrm = GenericData.generateRandomVrm();
        // read post request body from file
        String postRequestBody = GenericData.readJsonValueFromFile("test-results_submitted_post.json","$");
        String testType = GenericData.readJsonValueFromFile("test-results_submitted_post.json","$.testTypes[0]");
        // create alteration to change system number in the request body with the random system number
        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$.systemNumber", randomSystemNumber,"","REPLACE");
        // create alteration to change Vin in the request body with the random generated Vin
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin,"","REPLACE");
        // create alteration to change primary vrm in the request body with the random generated primary vrm
        JsonPathAlteration alterationVrm = new JsonPathAlteration("$.primaryVrm", randomVrm,"","REPLACE");
        // create alteration to change vehicle size in the request body
        JsonPathAlteration alterationVehicleSize = new JsonPathAlteration("$.vehicleSize", "small","","REPLACE");
        // create alteration to change vehicle type in the request body
        JsonPathAlteration alterationVehicleType = new JsonPathAlteration("$.vehicleType", "psv","","REPLACE");
        // create alteration to change vehicle configuration in the request body
        JsonPathAlteration alterationVehicleConfiguration = new JsonPathAlteration("$.vehicleConfiguration", "rigid","","REPLACE");
        // create alteration to change no of axles in the request body
        JsonPathAlteration alterationNoOfAxles = new JsonPathAlteration("$.noOfAxles", 3,"","REPLACE");
        // create alteration to change test type in the request body
        JsonPathAlteration alterationTestType1 = new JsonPathAlteration("$.testTypes[0].testTypeId", "30","","REPLACE");
        // create alteration to add test type in the request body
        JsonPathAlteration alterationAddTestType = new JsonPathAlteration("$.testTypes", testType,"","ADD_VALUE");
        // create alteration to change second test type in the request body
        JsonPathAlteration alterationTestType2 = new JsonPathAlteration("$.testTypes[1].testTypeId", "39","","REPLACE");
        // initialize the alterations list with both declared alteration
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(alterationVin,
                alterationVrm,
                alterationSystemNumber,
                alterationVehicleSize,
                alterationVehicleType,
                alterationVehicleConfiguration,
                alterationNoOfAxles,
                alterationTestType1,
                alterationAddTestType,
                alterationTestType2
        ));

        testResultsSteps.postVehicleTestResultsWithAlterations(postRequestBody, alterations);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.getTestResults(randomSystemNumber);
        testResultsSteps.valueForFieldInPathShouldBe("[0].testTypes[0].testCode", "qgs");
        testResultsSteps.valueForFieldInPathShouldBe("[0].testTypes[1].testCode", "lcp");
    }

    @Title("CVSB-840 / CVSB-3364 - AC2 Map the test code with the test type - linked test type with a specific linked test code - Scenario 3 - Data Set 3")
    @Test
    public void testTestCodeMappingSpecificLinkedTestTypeScenario3DataSet3() {
        // TEST SETUP
        //generate random system number
        String randomSystemNumber = GenericData.generateRandomSystemNumber();
        //generate random Vin
        String randomVin = GenericData.generateRandomVin();
        //generate random Vrm
        String randomVrm = GenericData.generateRandomVrm();
        // read post request body from file
        String postRequestBody = GenericData.readJsonValueFromFile("test-results_submitted_post.json","$");
        String testType = GenericData.readJsonValueFromFile("test-results_submitted_post.json","$.testTypes[0]");
        // create alteration to change system number in the request body with the random system number
        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$.systemNumber", randomSystemNumber,"","REPLACE");
        // create alteration to change Vin in the request body with the random generated Vin
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin,"","REPLACE");
        // create alteration to change primary vrm in the request body with the random generated primary vrm
        JsonPathAlteration alterationVrm = new JsonPathAlteration("$.primaryVrm", randomVrm,"","REPLACE");
        // create alteration to change vehicle size in the request body
        JsonPathAlteration alterationVehicleSize = new JsonPathAlteration("$.vehicleSize", "large","","REPLACE");
        // create alteration to change vehicle type in the request body
        JsonPathAlteration alterationVehicleType = new JsonPathAlteration("$.vehicleType", "psv","","REPLACE");
        // create alteration to change vehicle configuration in the request body
        JsonPathAlteration alterationVehicleConfiguration = new JsonPathAlteration("$.vehicleConfiguration", "rigid","","REPLACE");
        // create alteration to change no of axles in the request body
        JsonPathAlteration alterationNoOfAxles = new JsonPathAlteration("$.noOfAxles", 3,"","REPLACE");
        // create alteration to change test type in the request body
        JsonPathAlteration alterationTestType1 = new JsonPathAlteration("$.testTypes[0].testTypeId", "39","","REPLACE");
        // create alteration to add test type in the request body
        JsonPathAlteration alterationAddTestType = new JsonPathAlteration("$.testTypes", testType,"","ADD_VALUE");
        // create alteration to change second test type in the request body
        JsonPathAlteration alterationTestType2 = new JsonPathAlteration("$.testTypes[1].testTypeId", "30","","REPLACE");
        // initialize the alterations list with both declared alteration
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(alterationVin,
                alterationVrm,
                alterationSystemNumber,
                alterationVehicleSize,
                alterationVehicleType,
                alterationVehicleConfiguration,
                alterationNoOfAxles,
                alterationTestType1,
                alterationAddTestType,
                alterationTestType2
        ));

        testResultsSteps.postVehicleTestResultsWithAlterations(postRequestBody, alterations);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.getTestResults(randomSystemNumber);
        testResultsSteps.valueForFieldInPathShouldBe("[0].testTypes[0].testCode", "lcp");
        testResultsSteps.valueForFieldInPathShouldBe("[0].testTypes[1].testCode", "qgl");
    }

    @Title("CVSB-840 / CVSB-3364 - AC2 Map the test code with the test type - linked test type with a specific linked test code - Scenario 3 - Data Set 4")
    @Test
    public void testTestCodeMappingSpecificLinkedTestTypeScenario3DataSet4() {
        // TEST SETUP
        //generate random system number
        String randomSystemNumber = GenericData.generateRandomSystemNumber();
        //generate random Vin
        String randomVin = GenericData.generateRandomVin();
        //generate random Vrm
        String randomVrm = GenericData.generateRandomVrm();
        // read post request body from file
        String postRequestBody = GenericData.readJsonValueFromFile("test-results_submitted_post.json","$");
        String testType = GenericData.readJsonValueFromFile("test-results_submitted_post.json","$.testTypes[0]");
        // create alteration to change system number in the request body with the random system number
        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$.systemNumber", randomSystemNumber,"","REPLACE");
        // create alteration to change Vin in the request body with the random generated Vin
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin,"","REPLACE");
        // create alteration to change primary vrm in the request body with the random generated primary vrm
        JsonPathAlteration alterationVrm = new JsonPathAlteration("$.primaryVrm", randomVrm,"","REPLACE");
        // create alteration to change vehicle size in the request body
        JsonPathAlteration alterationVehicleSize = new JsonPathAlteration("$.vehicleSize", "small","","REPLACE");
        // create alteration to change vehicle type in the request body
        JsonPathAlteration alterationVehicleType = new JsonPathAlteration("$.vehicleType", "psv","","REPLACE");
        // create alteration to change vehicle configuration in the request body
        JsonPathAlteration alterationVehicleConfiguration = new JsonPathAlteration("$.vehicleConfiguration", "rigid","","REPLACE");
        // create alteration to change no of axles in the request body
        JsonPathAlteration alterationNoOfAxles = new JsonPathAlteration("$.noOfAxles", 3,"","REPLACE");
        // create alteration to change test type in the request body
        JsonPathAlteration alterationTestType1 = new JsonPathAlteration("$.testTypes[0].testTypeId", "39","","REPLACE");
        // create alteration to add test type in the request body
        JsonPathAlteration alterationAddTestType = new JsonPathAlteration("$.testTypes", testType,"","ADD_VALUE");
        // create alteration to change second test type in the request body
        JsonPathAlteration alterationTestType2 = new JsonPathAlteration("$.testTypes[1].testTypeId", "30","","REPLACE");
        // initialize the alterations list with both declared alteration
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(alterationVin,
                alterationVrm,
                alterationSystemNumber,
                alterationVehicleSize,
                alterationVehicleType,
                alterationVehicleConfiguration,
                alterationNoOfAxles,
                alterationTestType1,
                alterationAddTestType,
                alterationTestType2
        ));

        testResultsSteps.postVehicleTestResultsWithAlterations(postRequestBody, alterations);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.getTestResults(randomSystemNumber);
        testResultsSteps.valueForFieldInPathShouldBe("[0].testTypes[0].testCode", "lcp");
        testResultsSteps.valueForFieldInPathShouldBe("[0].testTypes[1].testCode", "qgs");
    }

    @Title("CVSB-840 / CVSB-3364 - AC2 Map the test code with the test type - linked test type with a specific linked test code - Scenario 4")
    @Test
    public void testTestCodeMappingSpecificLinkedTestTypeScenario4() {
        // TEST SETUP
        //generate random system number
        String randomSystemNumber = GenericData.generateRandomSystemNumber();
        //generate random Vin
        String randomVin = GenericData.generateRandomVin();
        //generate random Vrm
        String randomVrm = GenericData.generateRandomVrm();
        // read post request body from file
        String postRequestBody = GenericData.readJsonValueFromFile("test-results_submitted_post.json","$");
        String testType = GenericData.readJsonValueFromFile("test-results_submitted_post.json","$.testTypes[0]");
        // create alteration to change system number in the request body with the random system number
        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$.systemNumber", randomSystemNumber,"","REPLACE");
        // create alteration to change Vin in the request body with the random generated Vin
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin,"","REPLACE");
        // create alteration to change primary vrm in the request body with the random generated primary vrm
        JsonPathAlteration alterationVrm = new JsonPathAlteration("$.primaryVrm", randomVrm,"","REPLACE");
        // create alteration to change vehicle size in the request body
        JsonPathAlteration alterationVehicleSize = new JsonPathAlteration("$.vehicleSize", "small","","REPLACE");
        // create alteration to change vehicle type in the request body
        JsonPathAlteration alterationVehicleType = new JsonPathAlteration("$.vehicleType", "psv","","REPLACE");
        // create alteration to change vehicle configuration in the request body
        JsonPathAlteration alterationVehicleConfiguration = new JsonPathAlteration("$.vehicleConfiguration", "rigid","","REPLACE");
        // create alteration to change no of axles in the request body
        JsonPathAlteration alterationNoOfAxles = new JsonPathAlteration("$.noOfAxles", 3,"","REPLACE");
        // create alteration to change test type in the request body
        JsonPathAlteration alterationTestType1 = new JsonPathAlteration("$.testTypes[0].testTypeId", "39","","REPLACE");
        // create alteration to add test type in the request body
        JsonPathAlteration alterationAddTestType = new JsonPathAlteration("$.testTypes", testType,"","ADD_VALUE");
        // create alteration to change second test type in the request body
        JsonPathAlteration alterationTestType2 = new JsonPathAlteration("$.testTypes[1].testTypeId", "39","","REPLACE");
        // initialize the alterations list with both declared alteration
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(alterationVin,
                alterationVrm,
                alterationSystemNumber,
                alterationVehicleSize,
                alterationVehicleType,
                alterationVehicleConfiguration,
                alterationNoOfAxles,
                alterationTestType1,
                alterationAddTestType,
                alterationTestType2
        ));

        testResultsSteps.postVehicleTestResultsWithAlterations(postRequestBody, alterations);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.getTestResults(randomSystemNumber);
        testResultsSteps.valueForFieldInPathShouldBe("[0].testTypes[0].testCode", "lcp");
        testResultsSteps.valueForFieldInPathShouldBe("[0].testTypes[1].testCode", "lcp");
    }

    @Title("CVSB-840 / CVSB-3368 - AC3 Map the test code with the test type - linked test type without a specific linked test code - Data Set 1")
    @Test
    public void testTestCodeMappingNoSpecificLinkedTestCodeDataSet1() {
        // TEST SETUP
        //generate random system number
        String randomSystemNumber = GenericData.generateRandomSystemNumber();
        //generate random Vin
        String randomVin = GenericData.generateRandomVin();
        //generate random Vrm
        String randomVrm = GenericData.generateRandomVrm();
        // read post request body from file
        String postRequestBody = GenericData.readJsonValueFromFile("test-results_submitted_post.json","$");
        String testType = GenericData.readJsonValueFromFile("test-results_submitted_post.json","$.testTypes[0]");
        // create alteration to change system number in the request body with the random system number
        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$.systemNumber", randomSystemNumber,"","REPLACE");
        // create alteration to change Vin in the request body with the random generated Vin
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin,"","REPLACE");
        // create alteration to change primary vrm in the request body with the random generated primary vrm
        JsonPathAlteration alterationVrm = new JsonPathAlteration("$.primaryVrm", randomVrm,"","REPLACE");
        // create alteration to change vehicle size in the request body
        JsonPathAlteration alterationVehicleSize = new JsonPathAlteration("$.vehicleSize", "large","","REPLACE");
        // create alteration to change vehicle type in the request body
        JsonPathAlteration alterationVehicleType = new JsonPathAlteration("$.vehicleType", "psv","","REPLACE");
        // create alteration to change vehicle configuration in the request body
        JsonPathAlteration alterationVehicleConfiguration = new JsonPathAlteration("$.vehicleConfiguration", "rigid","","REPLACE");
        // create alteration to change no of axles in the request body
        JsonPathAlteration alterationNoOfAxles = new JsonPathAlteration("$.noOfAxles", 2,"","REPLACE");
        // create alteration to change test type in the request body
        JsonPathAlteration alterationTestType1 = new JsonPathAlteration("$.testTypes[0].testTypeId", "1","","REPLACE");
        // create alteration to add test type in the request body
        JsonPathAlteration alterationAddTestType = new JsonPathAlteration("$.testTypes", testType,"","ADD_VALUE");
        // create alteration to change second test type in the request body
        JsonPathAlteration alterationTestType2 = new JsonPathAlteration("$.testTypes[1].testTypeId", "30","","REPLACE");
        // initialize the alterations list with both declared alteration
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(alterationVin,
                alterationVrm,
                alterationSystemNumber,
                alterationVehicleSize,
                alterationVehicleType,
                alterationVehicleConfiguration,
                alterationNoOfAxles,
                alterationTestType1,
                alterationAddTestType,
                alterationTestType2
        ));

        testResultsSteps.postVehicleTestResultsWithAlterations(postRequestBody, alterations);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.getTestResults(randomSystemNumber);
        testResultsSteps.valueForFieldInPathShouldBe("[0].testTypes[0].testCode", "aal");
        testResultsSteps.valueForFieldInPathShouldBe("[0].testTypes[1].testCode", "qal");
    }

    @Title("CVSB-840 / CVSB-3368 - AC3 Map the test code with the test type - linked test type without a specific linked test code - Data Set 2")
    @Test
    public void testTestCodeMappingNoSpecificLinkedTestCodeDataSet2() {
//        // testTypeOne: testTypeId:1 ; vehicleType:psv ; vehicleSize:small ; vehicleConfiguration:rigid ; noOfAxles:3 ;
//        // testTypeTwo: testTypeId:30 ; vehicleType:psv ; vehicleSize:small ; vehicleConfiguration:rigid ; noOfAxles:3 ;
//        TestTypes testTypesOne = ((TestTypes) vehicleSubmittedDataOne.getTestTypes().get(0)).setTestTypeId("1");
//        TestTypes testTypesTwo = ((TestTypes) vehicleSubmittedDataTwo.getTestTypes().get(0)).setTestTypeId("30");
//
//        vehicleSubmittedDataOne.setVin(generateRandomExcludingValues(21, vehicleSubmittedDataOne.build().getVin()))
//                .setTestTypes(Arrays.asList(testTypesOne, testTypesTwo))
//                .setVrm(generateRandomExcludingValues(7, vehicleSubmittedDataOne.build().getVrm()))
//                .setVehicleType("psv")
//                .setNoOfAxles(3)
//                .setVehicleSize("small")
//                .setVehicleConfiguration(("rigid")).build();
//
//        testResultsSteps.postTestResults(vehicleSubmittedDataOne.build());
//        testResultsSteps.statusCodeShouldBe(201);
//        testResultsSteps.validateData("Test records created");
//        validateSavedData("aas", "qgs");

        // TEST SETUP
        //generate random system number
        String randomSystemNumber = GenericData.generateRandomSystemNumber();
        //generate random Vin
        String randomVin = GenericData.generateRandomVin();
        //generate random Vrm
        String randomVrm = GenericData.generateRandomVrm();
        // read post request body from file
        String postRequestBody = GenericData.readJsonValueFromFile("test-results_submitted_post.json","$");
        String testType = GenericData.readJsonValueFromFile("test-results_submitted_post.json","$.testTypes[0]");
        // create alteration to change system number in the request body with the random system number
        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$.systemNumber", randomSystemNumber,"","REPLACE");
        // create alteration to change Vin in the request body with the random generated Vin
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin,"","REPLACE");
        // create alteration to change primary vrm in the request body with the random generated primary vrm
        JsonPathAlteration alterationVrm = new JsonPathAlteration("$.primaryVrm", randomVrm,"","REPLACE");
        // create alteration to change vehicle size in the request body
        JsonPathAlteration alterationVehicleSize = new JsonPathAlteration("$.vehicleSize", "small","","REPLACE");
        // create alteration to change vehicle type in the request body
        JsonPathAlteration alterationVehicleType = new JsonPathAlteration("$.vehicleType", "psv","","REPLACE");
        // create alteration to change vehicle configuration in the request body
        JsonPathAlteration alterationVehicleConfiguration = new JsonPathAlteration("$.vehicleConfiguration", "rigid","","REPLACE");
        // create alteration to change no of axles in the request body
        JsonPathAlteration alterationNoOfAxles = new JsonPathAlteration("$.noOfAxles", 3,"","REPLACE");
        // create alteration to change test type in the request body
        JsonPathAlteration alterationTestType1 = new JsonPathAlteration("$.testTypes[0].testTypeId", "1","","REPLACE");
        // create alteration to add test type in the request body
        JsonPathAlteration alterationAddTestType = new JsonPathAlteration("$.testTypes", testType,"","ADD_VALUE");
        // create alteration to change second test type in the request body
        JsonPathAlteration alterationTestType2 = new JsonPathAlteration("$.testTypes[1].testTypeId", "30","","REPLACE");
        // initialize the alterations list with both declared alteration
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(alterationVin,
                alterationVrm,
                alterationSystemNumber,
                alterationVehicleSize,
                alterationVehicleType,
                alterationVehicleConfiguration,
                alterationNoOfAxles,
                alterationTestType1,
                alterationAddTestType,
                alterationTestType2
        ));

        testResultsSteps.postVehicleTestResultsWithAlterations(postRequestBody, alterations);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.getTestResults(randomSystemNumber);
        testResultsSteps.valueForFieldInPathShouldBe("[0].testTypes[0].testCode", "aas");
        testResultsSteps.valueForFieldInPathShouldBe("[0].testTypes[1].testCode", "qgs");
    }

    @Title("CVSB-840 / CVSB-3368 - AC3 Map the test code with the test type - linked test type without a specific linked test code - Data Set 3")
    @Test
    public void testTestCodeMappingNoSpecificLinkedTestCodeDataSet3() {
        // TEST SETUP
        //generate random system number
        String randomSystemNumber = GenericData.generateRandomSystemNumber();
        //generate random Vin
        String randomVin = GenericData.generateRandomVin();
        //generate random Vrm
        String randomVrm = GenericData.generateRandomVrm();
        // read post request body from file
        String postRequestBody = GenericData.readJsonValueFromFile("test-results_submitted_post.json","$");
        String testType = GenericData.readJsonValueFromFile("test-results_submitted_post.json","$.testTypes[0]");
        // create alteration to change system number in the request body with the random system number
        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$.systemNumber", randomSystemNumber,"","REPLACE");
        // create alteration to change Vin in the request body with the random generated Vin
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin,"","REPLACE");
        // create alteration to change primary vrm in the request body with the random generated primary vrm
        JsonPathAlteration alterationVrm = new JsonPathAlteration("$.primaryVrm", randomVrm,"","REPLACE");
        // create alteration to change vehicle size in the request body
        JsonPathAlteration alterationVehicleSize = new JsonPathAlteration("$.vehicleSize", "large","","REPLACE");
        // create alteration to change vehicle type in the request body
        JsonPathAlteration alterationVehicleType = new JsonPathAlteration("$.vehicleType", "psv","","REPLACE");
        // create alteration to change vehicle configuration in the request body
        JsonPathAlteration alterationVehicleConfiguration = new JsonPathAlteration("$.vehicleConfiguration", "articulated","","REPLACE");
        // create alteration to change no of axles in the request body
        JsonPathAlteration alterationNoOfAxles = new JsonPathAlteration("$.noOfAxles", 2,"","REPLACE");
        // create alteration to change test type in the request body
        JsonPathAlteration alterationTestType1 = new JsonPathAlteration("$.testTypes[0].testTypeId", "1","","REPLACE");
        // create alteration to add test type in the request body
        JsonPathAlteration alterationAddTestType = new JsonPathAlteration("$.testTypes", testType,"","ADD_VALUE");
        // create alteration to change second test type in the request body
        JsonPathAlteration alterationTestType2 = new JsonPathAlteration("$.testTypes[1].testTypeId", "7","","REPLACE");
        // initialize the alterations list with both declared alteration
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(alterationVin,
                alterationVrm,
                alterationSystemNumber,
                alterationVehicleSize,
                alterationVehicleType,
                alterationVehicleConfiguration,
                alterationNoOfAxles,
                alterationTestType1,
                alterationAddTestType,
                alterationTestType2
        ));

        testResultsSteps.postVehicleTestResultsWithAlterations(postRequestBody, alterations);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.getTestResults(randomSystemNumber);
        testResultsSteps.valueForFieldInPathShouldBe("[0].testTypes[0].testCode", "adl");
        testResultsSteps.valueForFieldInPathShouldBe("[0].testTypes[1].testCode", "rhl");
    }

    @Title("CVSB-840 / CVSB-3368 - AC3 Map the test code with the test type - linked test type without a specific linked test code - Data Set 4")
    @Test
    public void testTestCodeMappingNoSpecificLinkedTestCodeDataSet4() {
        // TEST SETUP
        //generate random system number
        String randomSystemNumber = GenericData.generateRandomSystemNumber();
        //generate random Vin
        String randomVin = GenericData.generateRandomVin();
        //generate random Vrm
        String randomVrm = GenericData.generateRandomVrm();
        // read post request body from file
        String postRequestBody = GenericData.readJsonValueFromFile("test-results_submitted_post.json","$");
        String testType = GenericData.readJsonValueFromFile("test-results_submitted_post.json","$.testTypes[0]");
        // create alteration to change system number in the request body with the random system number
        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$.systemNumber", randomSystemNumber,"","REPLACE");
        // create alteration to change Vin in the request body with the random generated Vin
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin,"","REPLACE");
        // create alteration to change primary vrm in the request body with the random generated primary vrm
        JsonPathAlteration alterationVrm = new JsonPathAlteration("$.primaryVrm", randomVrm,"","REPLACE");
        // create alteration to change vehicle size in the request body
        JsonPathAlteration alterationVehicleSize = new JsonPathAlteration("$.vehicleSize", "large","","REPLACE");
        // create alteration to change vehicle type in the request body
        JsonPathAlteration alterationVehicleType = new JsonPathAlteration("$.vehicleType", "psv","","REPLACE");
        // create alteration to change vehicle configuration in the request body
        JsonPathAlteration alterationVehicleConfiguration = new JsonPathAlteration("$.vehicleConfiguration", "rigid","","REPLACE");
        // create alteration to change no of axles in the request body
        JsonPathAlteration alterationNoOfAxles = new JsonPathAlteration("$.noOfAxles", 2,"","REPLACE");
        // create alteration to change test type in the request body
        JsonPathAlteration alterationTestType1 = new JsonPathAlteration("$.testTypes[0].testTypeId", "30","","REPLACE");
        // create alteration to add test type in the request body
        JsonPathAlteration alterationAddTestType = new JsonPathAlteration("$.testTypes", testType,"","ADD_VALUE");
        // create alteration to change second test type in the request body
        JsonPathAlteration alterationTestType2 = new JsonPathAlteration("$.testTypes[1].testTypeId", "1","","REPLACE");
        // initialize the alterations list with both declared alteration
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(alterationVin,
                alterationVrm,
                alterationSystemNumber,
                alterationVehicleSize,
                alterationVehicleType,
                alterationVehicleConfiguration,
                alterationNoOfAxles,
                alterationTestType1,
                alterationAddTestType,
                alterationTestType2
        ));

        testResultsSteps.postVehicleTestResultsWithAlterations(postRequestBody, alterations);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.getTestResults(randomSystemNumber);
        testResultsSteps.valueForFieldInPathShouldBe("[0].testTypes[0].testCode", "qal");
        testResultsSteps.valueForFieldInPathShouldBe("[0].testTypes[1].testCode", "aal");
    }

    @Title("CVSB-840 / CVSB-3368 - AC3 Map the test code with the test type - linked test type without a specific linked test code - Data Set 5")
    @Test
    public void testTestCodeMappingNoSpecificLinkedTestCodeDataSet5() {
        // TEST SETUP
        //generate random system number
        String randomSystemNumber = GenericData.generateRandomSystemNumber();
        //generate random Vin
        String randomVin = GenericData.generateRandomVin();
        //generate random Vrm
        String randomVrm = GenericData.generateRandomVrm();
        // read post request body from file
        String postRequestBody = GenericData.readJsonValueFromFile("test-results_submitted_post.json","$");
        String testType = GenericData.readJsonValueFromFile("test-results_submitted_post.json","$.testTypes[0]");
        // create alteration to change system number in the request body with the random system number
        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$.systemNumber", randomSystemNumber,"","REPLACE");
        // create alteration to change Vin in the request body with the random generated Vin
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin,"","REPLACE");
        // create alteration to change primary vrm in the request body with the random generated primary vrm
        JsonPathAlteration alterationVrm = new JsonPathAlteration("$.primaryVrm", randomVrm,"","REPLACE");
        // create alteration to change vehicle size in the request body
        JsonPathAlteration alterationVehicleSize = new JsonPathAlteration("$.vehicleSize", "small","","REPLACE");
        // create alteration to change vehicle type in the request body
        JsonPathAlteration alterationVehicleType = new JsonPathAlteration("$.vehicleType", "psv","","REPLACE");
        // create alteration to change vehicle configuration in the request body
        JsonPathAlteration alterationVehicleConfiguration = new JsonPathAlteration("$.vehicleConfiguration", "rigid","","REPLACE");
        // create alteration to change no of axles in the request body
        JsonPathAlteration alterationNoOfAxles = new JsonPathAlteration("$.noOfAxles", 3,"","REPLACE");
        // create alteration to change test type in the request body
        JsonPathAlteration alterationTestType1 = new JsonPathAlteration("$.testTypes[0].testTypeId", "30","","REPLACE");
        // create alteration to add test type in the request body
        JsonPathAlteration alterationAddTestType = new JsonPathAlteration("$.testTypes", testType,"","ADD_VALUE");
        // create alteration to change second test type in the request body
        JsonPathAlteration alterationTestType2 = new JsonPathAlteration("$.testTypes[1].testTypeId", "1","","REPLACE");
        // initialize the alterations list with both declared alteration
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(alterationVin,
                alterationVrm,
                alterationSystemNumber,
                alterationVehicleSize,
                alterationVehicleType,
                alterationVehicleConfiguration,
                alterationNoOfAxles,
                alterationTestType1,
                alterationAddTestType,
                alterationTestType2
        ));

        testResultsSteps.postVehicleTestResultsWithAlterations(postRequestBody, alterations);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.getTestResults(randomSystemNumber);
        testResultsSteps.valueForFieldInPathShouldBe("[0].testTypes[0].testCode", "qgs");
        testResultsSteps.valueForFieldInPathShouldBe("[0].testTypes[1].testCode", "aas");
    }

    @Title("CVSB-840 / CVSB-3368 - AC3 Map the test code with the test type - linked test type without a specific linked test code - Data Set 6")
    @Test
    public void testTestCodeMappingNoSpecificLinkedTestCodeDataSet6() {
        // TEST SETUP
        //generate random system number
        String randomSystemNumber = GenericData.generateRandomSystemNumber();
        //generate random Vin
        String randomVin = GenericData.generateRandomVin();
        //generate random Vrm
        String randomVrm = GenericData.generateRandomVrm();
        // read post request body from file
        String postRequestBody = GenericData.readJsonValueFromFile("test-results_submitted_post.json","$");
        String testType = GenericData.readJsonValueFromFile("test-results_submitted_post.json","$.testTypes[0]");
        // create alteration to change system number in the request body with the random system number
        JsonPathAlteration alterationSystemNumber = new JsonPathAlteration("$.systemNumber", randomSystemNumber,"","REPLACE");
        // create alteration to change Vin in the request body with the random generated Vin
        JsonPathAlteration alterationVin = new JsonPathAlteration("$.vin", randomVin,"","REPLACE");
        // create alteration to change primary vrm in the request body with the random generated primary vrm
        JsonPathAlteration alterationVrm = new JsonPathAlteration("$.primaryVrm", randomVrm,"","REPLACE");
        // create alteration to change vehicle size in the request body
        JsonPathAlteration alterationVehicleSize = new JsonPathAlteration("$.vehicleSize", "large","","REPLACE");
        // create alteration to change vehicle type in the request body
        JsonPathAlteration alterationVehicleType = new JsonPathAlteration("$.vehicleType", "psv","","REPLACE");
        // create alteration to change vehicle configuration in the request body
        JsonPathAlteration alterationVehicleConfiguration = new JsonPathAlteration("$.vehicleConfiguration", "articulated","","REPLACE");
        // create alteration to change no of axles in the request body
        JsonPathAlteration alterationNoOfAxles = new JsonPathAlteration("$.noOfAxles", 2,"","REPLACE");
        // create alteration to change test type in the request body
        JsonPathAlteration alterationTestType1 = new JsonPathAlteration("$.testTypes[0].testTypeId", "7","","REPLACE");
        // create alteration to add test type in the request body
        JsonPathAlteration alterationAddTestType = new JsonPathAlteration("$.testTypes", testType,"","ADD_VALUE");
        // create alteration to change second test type in the request body
        JsonPathAlteration alterationTestType2 = new JsonPathAlteration("$.testTypes[1].testTypeId", "1","","REPLACE");
        // initialize the alterations list with both declared alteration
        List<JsonPathAlteration> alterations = new ArrayList<>(Arrays.asList(alterationVin,
                alterationVrm,
                alterationSystemNumber,
                alterationVehicleSize,
                alterationVehicleType,
                alterationVehicleConfiguration,
                alterationNoOfAxles,
                alterationTestType1,
                alterationAddTestType,
                alterationTestType2
        ));

        testResultsSteps.postVehicleTestResultsWithAlterations(postRequestBody, alterations);
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.getTestResults(randomSystemNumber);
        testResultsSteps.valueForFieldInPathShouldBe("[0].testTypes[0].testCode", "rhl");
        testResultsSteps.valueForFieldInPathShouldBe("[0].testTypes[1].testCode", "adl");
    }

}
