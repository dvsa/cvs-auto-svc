package testresults;

import data.TestResultsData;
import model.testresults.TestResults;
import model.testresults.TestResultsGet;
import model.testresults.TestResultsStatus;
import model.testresults.TestTypes;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Test;
import org.junit.runner.RunWith;
import steps.TestResultsSteps;

import java.util.Arrays;

import static util.DataUtil.generateRandomExcludingValues;


@RunWith(SerenityRunner.class)
public class PostTestResultsTestCodeMappingOnTestTypes {

    @Steps
    TestResultsSteps testResultsSteps;

    private TestResults.Builder vehicleSubmittedDataOne = TestResultsData.buildTestResultsSubmittedData();
    private TestResults.Builder vehicleSubmittedDataTwo = TestResultsData.buildTestResultsSubmittedData();

    private void validateSavedData(String... testCodes) {

        testResultsSteps.getTestResults(vehicleSubmittedDataOne.build().getVin(), TestResultsStatus.SUBMITTED);
        testResultsSteps.statusCodeShouldBe(200);
        testResultsSteps.validateTestCode((TestResultsGet) vehicleSubmittedDataOne.build(), testCodes);
    }

    @Title("CVSB-840 / CVSB-3360 - Map the test code with the test type - not a linked test type - Data Set 1")
    @Test
    public void testTestCodeMappingNoLinkedTestTypeDataSet1() {
        // data set: testTypeId:1 ; vehicleType:psv ; vehicleSize:large ; vehicleConfiguration:rigid ; noOfAxles:2 ;
        vehicleSubmittedDataOne.setVin(generateRandomExcludingValues(21, vehicleSubmittedDataOne.build().getVin()))
                .setVrm(generateRandomExcludingValues(7, vehicleSubmittedDataOne.build().getVrm()))
                .setVehicleSize("large")
                .setVehicleType("psv")
                .setVehicleConfiguration(("rigid"))
                .setNoOfAxles(2).build()
                .getTestTypes().get(0).setTestTypeId("1");

        testResultsSteps.postTestResults(vehicleSubmittedDataOne.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedData("aal");
    }

    @Title("CVSB-840 / CVSB-3360 - Map the test code with the test type - not a linked test type - Data Set 2")
    @Test
    public void testTestCodeMappingNoLinkedTestTypeDataSet2() {
        // data set: testTypeId:1 ; vehicleType:psv ; vehicleSize:small ; vehicleConfiguration:rigid ; noOfAxles:3 ;
        vehicleSubmittedDataOne.setVin(generateRandomExcludingValues(21, vehicleSubmittedDataOne.build().getVin()))
                .setVrm(generateRandomExcludingValues(7, vehicleSubmittedDataOne.build().getVrm()))
                .setVehicleSize("small")
                .setVehicleType("psv")
                .setVehicleConfiguration(("rigid"))
                .setNoOfAxles(3).build()
                .getTestTypes().get(0).setTestTypeId("1");

        testResultsSteps.postTestResults(vehicleSubmittedDataOne.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedData("aas");
    }

    @Title("CVSB-840 / CVSB-3360 - Map the test code with the test type - not a linked test type - Data Set 3")
    @Test
    public void testTestCodeMappingNoLinkedTestTypeDataSet3() {
        // data set: testTypeId:1 ; vehicleType:psv ; vehicleSize:large ; vehicleConfiguration:articulated ; noOfAxles:2 ;
        vehicleSubmittedDataOne.setVin(generateRandomExcludingValues(21, vehicleSubmittedDataOne.build().getVin()))
                .setVrm(generateRandomExcludingValues(7, vehicleSubmittedDataOne.build().getVrm()))
                .setVehicleSize("large")
                .setVehicleType("psv")
                .setVehicleConfiguration(("articulated"))
                .setNoOfAxles(2).build()
                .getTestTypes().get(0).setTestTypeId("1");

        testResultsSteps.postTestResults(vehicleSubmittedDataOne.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedData("adl");
    }

    @Title("CVSB-840 / CVSB-3360 - Map the test code with the test type - not a linked test type - Data Set 4")
    @Test
    public void testTestCodeMappingNoLinkedTestTypeDataSet4() {
        // data set: testTypeId:30 ; vehicleType:psv ; vehicleSize:large ; vehicleConfiguration:rigid ; noOfAxles:2 ;
        vehicleSubmittedDataOne.setVin(generateRandomExcludingValues(21, vehicleSubmittedDataOne.build().getVin()))
                .setVrm(generateRandomExcludingValues(7, vehicleSubmittedDataOne.build().getVrm()))
                .setVehicleSize("large")
                .setVehicleType("psv")
                .setVehicleConfiguration(("rigid"))
                .setNoOfAxles(2).build()
                .getTestTypes().get(0).setTestTypeId("30");

        testResultsSteps.postTestResults(vehicleSubmittedDataOne.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedData("qal");
    }

    @Title("CVSB-840 / CVSB-3360 - Map the test code with the test type - not a linked test type - Data Set 5")
    @Test
    public void testTestCodeMappingNoLinkedTestTypeDataSet5() {
        // data set: testTypeId:30 ; vehicleType:psv ; vehicleSize:small ; vehicleConfiguration:rigid ; noOfAxles:3 ;
        vehicleSubmittedDataOne.setVin(generateRandomExcludingValues(21, vehicleSubmittedDataOne.build().getVin()))
                .setVrm(generateRandomExcludingValues(7, vehicleSubmittedDataOne.build().getVrm()))
                .setVehicleSize("small")
                .setVehicleType("psv")
                .setVehicleConfiguration(("rigid"))
                .setNoOfAxles(3).build()
                .getTestTypes().get(0).setTestTypeId("30");

        testResultsSteps.postTestResults(vehicleSubmittedDataOne.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedData("qgs");
    }

    @Title("CVSB-840 / CVSB-3360 - Map the test code with the test type - not a linked test type - Data Set 6")
    @Test
    public void testTestCodeMappingNoLinkedTestTypeDataSet6() {
        // data set: testTypeId:30 ; vehicleType:psv ; vehicleSize:large ; vehicleConfiguration:rigid ; noOfAxles:3 ;
        vehicleSubmittedDataOne.setVin(generateRandomExcludingValues(21, vehicleSubmittedDataOne.build().getVin()))
                .setVrm(generateRandomExcludingValues(7, vehicleSubmittedDataOne.build().getVrm()))
                .setVehicleSize("large")
                .setVehicleType("psv")
                .setVehicleConfiguration(("rigid"))
                .setNoOfAxles(3).build()
                .getTestTypes().get(0).setTestTypeId("30");

        testResultsSteps.postTestResults(vehicleSubmittedDataOne.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedData("qgl");
    }

    @Title("CVSB-840 / CVSB-3360 - Map the test code with the test type - not a linked test type - Data Set 7")
    @Test
    public void testTestCodeMappingNoLinkedTestTypeDataSet7() {
        // data set: testTypeId:39 ; vehicleType:psv ; vehicleSize:large ; vehicleConfiguration:rigid ; noOfAxles:2 ;
        vehicleSubmittedDataOne.setVin(generateRandomExcludingValues(21, vehicleSubmittedDataOne.build().getVin()))
                .setVrm(generateRandomExcludingValues(7, vehicleSubmittedDataOne.build().getVrm()))
                .setVehicleSize("large")
                .setVehicleType("psv")
                .setVehicleConfiguration(("rigid"))
                .setNoOfAxles(2).build()
                .getTestTypes().get(0).setTestTypeId("39");

        testResultsSteps.postTestResults(vehicleSubmittedDataOne.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedData("lbp");
    }

    @Title("CVSB-840 / CVSB-3360 - Map the test code with the test type - not a linked test type - Data Set 8")
    @Test
    public void testTestCodeMappingNoLinkedTestTypeDataSet8() {
        // data set: testTypeId:39 ; vehicleType:psv ; vehicleSize:small ; vehicleConfiguration:rigid ; noOfAxles:3 ;
        vehicleSubmittedDataOne.setVin(generateRandomExcludingValues(21, vehicleSubmittedDataOne.build().getVin()))
                .setVrm(generateRandomExcludingValues(7, vehicleSubmittedDataOne.build().getVrm()))
                .setVehicleSize("small")
                .setVehicleType("psv")
                .setVehicleConfiguration(("rigid"))
                .setNoOfAxles(3).build()
                .getTestTypes().get(0).setTestTypeId("39");

        testResultsSteps.postTestResults(vehicleSubmittedDataOne.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedData("lbp");
    }

    @Title("CVSB-840 / CVSB-3360 - Map the test code with the test type - not a linked test type - Data Set 9")
    @Test
    public void testTestCodeMappingNoLinkedTestTypeDataSet9() {
        // data set: testTypeId:7 ; vehicleType:psv ; vehicleSize:large ; vehicleConfiguration:articulated ; noOfAxles:3 ;
        vehicleSubmittedDataOne.setVin(generateRandomExcludingValues(21, vehicleSubmittedDataOne.build().getVin()))
                .setVrm(generateRandomExcludingValues(7, vehicleSubmittedDataOne.build().getVrm()))
                .setVehicleSize("large")
                .setVehicleType("psv")
                .setVehicleConfiguration(("articulated"))
                .setNoOfAxles(3).build()
                .getTestTypes().get(0).setTestTypeId("7");

        testResultsSteps.postTestResults(vehicleSubmittedDataOne.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedData("rhl");
    }

    @Title("CVSB-840 / CVSB-3364 - AC2 Map the test code with the test type - linked test type with a specific linked test code - Scenario 1 - Data Set 1")
    @Test
    public void testTestCodeMappingSpecificLinkedTestTypeScenario1DataSet1() {
        // scenario 1
        // testTypeOne: testTypeId:1 ; vehicleType:psv ; vehicleSize:large ; vehicleConfiguration:rigid ; noOfAxles:2 ;
        // testTypeTwo: testTypeId:39 ; vehicleType:psv ; vehicleSize:large ; vehicleConfiguration:rigid ; noOfAxles:2 ;
        TestTypes testTypesOne = ((TestTypes) vehicleSubmittedDataOne.getTestTypes().get(0)).setTestTypeId("1");
        TestTypes testTypesTwo = ((TestTypes) vehicleSubmittedDataTwo.getTestTypes().get(0)).setTestTypeId("39");

        vehicleSubmittedDataOne.setVin(generateRandomExcludingValues(21, vehicleSubmittedDataOne.build().getVin()))
                .setTestTypes(Arrays.asList(testTypesOne, testTypesTwo))
                .setVrm(generateRandomExcludingValues(7, vehicleSubmittedDataOne.build().getVrm()))
                .setVehicleType("psv")
                .setNoOfAxles(2)
                .setVehicleSize("large")
                .setVehicleConfiguration(("rigid")).build();

        testResultsSteps.postTestResults(vehicleSubmittedDataOne.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedData("aal", "lcp");
    }

    @Title("CVSB-840 / CVSB-3364 - AC2 Map the test code with the test type - linked test type with a specific linked test code - Scenario 1 - Data Set 2")
    @Test
    public void testTestCodeMappingSpecificLinkedTestTypeScenario1DataSet2() {
        // scenario 1
        // testTypeOne: testTypeId:1 ; vehicleType:psv ; vehicleSize:small ; vehicleConfiguration:rigid ; noOfAxles:3 ;
        // testTypeTwo: testTypeId:39 ; vehicleType:psv ; vehicleSize:small ; vehicleConfiguration:rigid ; noOfAxles:3 ;
        TestTypes testTypesOne = ((TestTypes) vehicleSubmittedDataOne.getTestTypes().get(0)).setTestTypeId("1");
        TestTypes testTypesTwo = ((TestTypes) vehicleSubmittedDataTwo.getTestTypes().get(0)).setTestTypeId("39");

        vehicleSubmittedDataOne.setVin(generateRandomExcludingValues(21, vehicleSubmittedDataOne.build().getVin()))
                .setTestTypes(Arrays.asList(testTypesOne, testTypesTwo))
                .setVrm(generateRandomExcludingValues(7, vehicleSubmittedDataOne.build().getVrm()))
                .setVehicleType("psv")
                .setNoOfAxles(3)
                .setVehicleSize("small")
                .setVehicleConfiguration(("rigid")).build();

        testResultsSteps.postTestResults(vehicleSubmittedDataOne.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedData("aas", "lcp");
    }

    @Title("CVSB-840 / CVSB-3364 - AC2 Map the test code with the test type - linked test type with a specific linked test code - Scenario 1 - Data Set 3")
    @Test
    public void testTestCodeMappingSpecificLinkedTestTypeScenario1DataSet3() {
        // scenario 1
        // testTypeOne: testTypeId:39 ; vehicleType:psv ; vehicleSize:large ; vehicleConfiguration:rigid ; noOfAxles:2 ;
        // testTypeTwo: testTypeId:1 ; vehicleType:psv ; vehicleSize:large ; vehicleConfiguration:rigid ; noOfAxles:2 ;
        TestTypes testTypesOne = ((TestTypes) vehicleSubmittedDataOne.getTestTypes().get(0)).setTestTypeId("39");
        TestTypes testTypesTwo = ((TestTypes) vehicleSubmittedDataTwo.getTestTypes().get(0)).setTestTypeId("1");

        vehicleSubmittedDataOne.setVin(generateRandomExcludingValues(21, vehicleSubmittedDataOne.build().getVin()))
                .setTestTypes(Arrays.asList(testTypesOne, testTypesTwo))
                .setVrm(generateRandomExcludingValues(7, vehicleSubmittedDataOne.build().getVrm()))
                .setVehicleType("psv")
                .setNoOfAxles(2)
                .setVehicleSize("large")
                .setVehicleConfiguration(("rigid")).build();

        testResultsSteps.postTestResults(vehicleSubmittedDataOne.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedData("lcp", "aal");
    }

    @Title("CVSB-840 / CVSB-3364 - AC2 Map the test code with the test type - linked test type with a specific linked test code - Scenario 1 - Data Set 4")
    @Test
    public void testTestCodeMappingSpecificLinkedTestTypeScenario1DataSet4() {
        // scenario 1
        // testTypeOne: testTypeId:39 ; vehicleType:psv ; vehicleSize:small ; vehicleConfiguration:rigid ; noOfAxles:3 ;
        // testTypeTwo: testTypeId:1 ; vehicleType:psv ; vehicleSize:small ; vehicleConfiguration:rigid ; noOfAxles:3 ;
        TestTypes testTypesOne = ((TestTypes) vehicleSubmittedDataOne.getTestTypes().get(0)).setTestTypeId("39");
        TestTypes testTypesTwo = ((TestTypes) vehicleSubmittedDataTwo.getTestTypes().get(0)).setTestTypeId("1");

        vehicleSubmittedDataOne.setVin(generateRandomExcludingValues(21, vehicleSubmittedDataOne.build().getVin()))
                .setTestTypes(Arrays.asList(testTypesOne, testTypesTwo))
                .setVrm(generateRandomExcludingValues(7, vehicleSubmittedDataOne.build().getVrm()))
                .setVehicleType("psv")
                .setNoOfAxles(3)
                .setVehicleSize("small")
                .setVehicleConfiguration(("rigid")).build();

        testResultsSteps.postTestResults(vehicleSubmittedDataOne.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedData("lcp", "aas");
    }

    @Title("CVSB-840 / CVSB-3364 - AC2 Map the test code with the test type - linked test type with a specific linked test code - Scenario 2 - Data Set 1")
    @Test
    public void testTestCodeMappingSpecificLinkedTestTypeScenario2DataSet1() {
        // scenario 2
        // testTypeOne: testTypeId:30 ; vehicleType:psv ; vehicleSize:large ; vehicleConfiguration:rigid ; noOfAxles:2 ;
        // testTypeTwo: testTypeId:39 ; vehicleType:psv ; vehicleSize:large ; vehicleConfiguration:rigid ; noOfAxles:2 ;
        TestTypes testTypesOne = ((TestTypes) vehicleSubmittedDataOne.getTestTypes().get(0)).setTestTypeId("30");
        TestTypes testTypesTwo = ((TestTypes) vehicleSubmittedDataTwo.getTestTypes().get(0)).setTestTypeId("39");

        vehicleSubmittedDataOne.setVin(generateRandomExcludingValues(21, vehicleSubmittedDataOne.build().getVin()))
                .setTestTypes(Arrays.asList(testTypesOne, testTypesTwo))
                .setVrm(generateRandomExcludingValues(7, vehicleSubmittedDataOne.build().getVrm()))
                .setVehicleType("psv")
                .setNoOfAxles(2)
                .setVehicleSize("large")
                .setVehicleConfiguration(("rigid")).build();

        testResultsSteps.postTestResults(vehicleSubmittedDataOne.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedData("qal", "lcp");
    }

    @Title("CVSB-840 / CVSB-3364 - AC2 Map the test code with the test type - linked test type with a specific linked test code - Scenario 2 - Data Set 2")
    @Test
    public void testTestCodeMappingSpecificLinkedTestTypeScenario2DataSet2() {
        // scenario 2
        // testTypeOne: testTypeId:30 ; vehicleType:psv ; vehicleSize:small ; vehicleConfiguration:rigid ; noOfAxles:2 ;
        // testTypeTwo: testTypeId:39 ; vehicleType:psv ; vehicleSize:small ; vehicleConfiguration:rigid ; noOfAxles:2 ;
        TestTypes testTypesOne = ((TestTypes) vehicleSubmittedDataOne.getTestTypes().get(0)).setTestTypeId("30");
        TestTypes testTypesTwo = ((TestTypes) vehicleSubmittedDataTwo.getTestTypes().get(0)).setTestTypeId("39");

        vehicleSubmittedDataOne.setVin(generateRandomExcludingValues(21, vehicleSubmittedDataOne.build().getVin()))
                .setTestTypes(Arrays.asList(testTypesOne, testTypesTwo))
                .setVrm(generateRandomExcludingValues(7, vehicleSubmittedDataOne.build().getVrm()))
                .setVehicleType("psv")
                .setNoOfAxles(2)
                .setVehicleSize("small")
                .setVehicleConfiguration(("rigid")).build();

        testResultsSteps.postTestResults(vehicleSubmittedDataOne.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedData("qas", "lcp");
    }

    @Title("CVSB-840 / CVSB-3364 - AC2 Map the test code with the test type - linked test type with a specific linked test code - Scenario 2 - Data Set 3")
    @Test
    public void testTestCodeMappingSpecificLinkedTestTypeScenario2DataSet3() {
        // scenario 2
        // testTypeOne: testTypeId:39 ; vehicleType:psv ; vehicleSize:large ; vehicleConfiguration:rigid ; noOfAxles:2 ;
        // testTypeTwo: testTypeId:30 ; vehicleType:psv ; vehicleSize:large ; vehicleConfiguration:rigid ; noOfAxles:2 ;
        TestTypes testTypesOne = ((TestTypes) vehicleSubmittedDataOne.getTestTypes().get(0)).setTestTypeId("39");
        TestTypes testTypesTwo = ((TestTypes) vehicleSubmittedDataTwo.getTestTypes().get(0)).setTestTypeId("30");

        vehicleSubmittedDataOne.setVin(generateRandomExcludingValues(21, vehicleSubmittedDataOne.build().getVin()))
                .setTestTypes(Arrays.asList(testTypesOne, testTypesTwo))
                .setVrm(generateRandomExcludingValues(7, vehicleSubmittedDataOne.build().getVrm()))
                .setVehicleType("psv")
                .setNoOfAxles(2)
                .setVehicleSize("large")
                .setVehicleConfiguration(("rigid")).build();

        testResultsSteps.postTestResults(vehicleSubmittedDataOne.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedData("lcp", "qal");
    }

    @Title("CVSB-840 / CVSB-3364 - AC2 Map the test code with the test type - linked test type with a specific linked test code - Scenario 2 - Data Set 4")
    @Test
    public void testTestCodeMappingSpecificLinkedTestTypeScenario2DataSet4() {
        // scenario 2
        // testTypeOne: testTypeId:39 ; vehicleType:psv ; vehicleSize:small ; vehicleConfiguration:rigid ; noOfAxles:2 ;
        // testTypeTwo: testTypeId:30 ; vehicleType:psv ; vehicleSize:small ; vehicleConfiguration:rigid ; noOfAxles:2 ;
        TestTypes testTypesOne = ((TestTypes) vehicleSubmittedDataOne.getTestTypes().get(0)).setTestTypeId("39");
        TestTypes testTypesTwo = ((TestTypes) vehicleSubmittedDataTwo.getTestTypes().get(0)).setTestTypeId("30");

        vehicleSubmittedDataOne.setVin(generateRandomExcludingValues(21, vehicleSubmittedDataOne.build().getVin()))
                .setTestTypes(Arrays.asList(testTypesOne, testTypesTwo))
                .setVrm(generateRandomExcludingValues(7, vehicleSubmittedDataOne.build().getVrm()))
                .setVehicleType("psv")
                .setNoOfAxles(2)
                .setVehicleSize("small")
                .setVehicleConfiguration(("rigid")).build();

        testResultsSteps.postTestResults(vehicleSubmittedDataOne.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedData("lcp", "qas");
    }

    @Title("CVSB-840 / CVSB-3364 - AC2 Map the test code with the test type - linked test type with a specific linked test code - Scenario 3 - Data Set 1")
    @Test
    public void testTestCodeMappingSpecificLinkedTestTypeScenario3DataSet1() {
        // scenario 3
        // testTypeOne: testTypeId:30 ; vehicleType:psv ; vehicleSize:large ; vehicleConfiguration:rigid ; noOfAxles:3 ;
        // testTypeTwo: testTypeId:39 ; vehicleType:psv ; vehicleSize:large ; vehicleConfiguration:rigid ; noOfAxles:3 ;
        TestTypes testTypesOne = ((TestTypes) vehicleSubmittedDataOne.getTestTypes().get(0)).setTestTypeId("30");
        TestTypes testTypesTwo = ((TestTypes) vehicleSubmittedDataTwo.getTestTypes().get(0)).setTestTypeId("39");

        vehicleSubmittedDataOne.setVin(generateRandomExcludingValues(21, vehicleSubmittedDataOne.build().getVin()))
                .setTestTypes(Arrays.asList(testTypesOne, testTypesTwo))
                .setVrm(generateRandomExcludingValues(7, vehicleSubmittedDataOne.build().getVrm()))
                .setVehicleType("psv")
                .setNoOfAxles(3)
                .setVehicleSize("large")
                .setVehicleConfiguration(("rigid")).build();

        testResultsSteps.postTestResults(vehicleSubmittedDataOne.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedData("qgl", "lcp");
    }

    @Title("CVSB-840 / CVSB-3364 - AC2 Map the test code with the test type - linked test type with a specific linked test code - Scenario 3 - Data Set 2")
    @Test
    public void testTestCodeMappingSpecificLinkedTestTypeScenario3DataSet2() {
        // scenario 3
        // testTypeOne: testTypeId:30 ; vehicleType:psv ; vehicleSize:small ; vehicleConfiguration:rigid ; noOfAxles:3 ;
        // testTypeTwo: testTypeId:39 ; vehicleType:psv ; vehicleSize:small ; vehicleConfiguration:rigid ; noOfAxles:3 ;
        TestTypes testTypesOne = ((TestTypes) vehicleSubmittedDataOne.getTestTypes().get(0)).setTestTypeId("30");
        TestTypes testTypesTwo = ((TestTypes) vehicleSubmittedDataTwo.getTestTypes().get(0)).setTestTypeId("39");

        vehicleSubmittedDataOne.setVin(generateRandomExcludingValues(21, vehicleSubmittedDataOne.build().getVin()))
                .setTestTypes(Arrays.asList(testTypesOne, testTypesTwo))
                .setVrm(generateRandomExcludingValues(7, vehicleSubmittedDataOne.build().getVrm()))
                .setVehicleType("psv")
                .setNoOfAxles(3)
                .setVehicleSize("small")
                .setVehicleConfiguration(("rigid")).build();

        testResultsSteps.postTestResults(vehicleSubmittedDataOne.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedData("qgs", "lcp");
    }

    @Title("CVSB-840 / CVSB-3364 - AC2 Map the test code with the test type - linked test type with a specific linked test code - Scenario 3 - Data Set 3")
    @Test
    public void testTestCodeMappingSpecificLinkedTestTypeScenario3DataSet3() {
        // scenario 3
        // testTypeOne: testTypeId:39 ; vehicleType:psv ; vehicleSize:large ; vehicleConfiguration:rigid ; noOfAxles:3 ;
        // testTypeTwo: testTypeId:30 ; vehicleType:psv ; vehicleSize:large ; vehicleConfiguration:rigid ; noOfAxles:3 ;
        TestTypes testTypesOne = ((TestTypes) vehicleSubmittedDataOne.getTestTypes().get(0)).setTestTypeId("39");
        TestTypes testTypesTwo = ((TestTypes) vehicleSubmittedDataTwo.getTestTypes().get(0)).setTestTypeId("30");

        vehicleSubmittedDataOne.setVin(generateRandomExcludingValues(21, vehicleSubmittedDataOne.build().getVin()))
                .setTestTypes(Arrays.asList(testTypesOne, testTypesTwo))
                .setVrm(generateRandomExcludingValues(7, vehicleSubmittedDataOne.build().getVrm()))
                .setVehicleType("psv")
                .setNoOfAxles(3)
                .setVehicleSize("large")
                .setVehicleConfiguration(("rigid")).build();

        testResultsSteps.postTestResults(vehicleSubmittedDataOne.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedData("lcp", "qgl");
    }

    @Title("CVSB-840 / CVSB-3364 - AC2 Map the test code with the test type - linked test type with a specific linked test code - Scenario 3 - Data Set 4")
    @Test
    public void testTestCodeMappingSpecificLinkedTestTypeScenario3DataSet4() {
        // scenario 3
        // testTypeOne: testTypeId:39 ; vehicleType:psv ; vehicleSize:small ; vehicleConfiguration:rigid ; noOfAxles:3 ;
        // testTypeTwo: testTypeId:30 ; vehicleType:psv ; vehicleSize:small ; vehicleConfiguration:rigid ; noOfAxles:3 ;
        TestTypes testTypesOne = ((TestTypes) vehicleSubmittedDataOne.getTestTypes().get(0)).setTestTypeId("39");
        TestTypes testTypesTwo = ((TestTypes) vehicleSubmittedDataTwo.getTestTypes().get(0)).setTestTypeId("30");

        vehicleSubmittedDataOne.setVin(generateRandomExcludingValues(21, vehicleSubmittedDataOne.build().getVin()))
                .setTestTypes(Arrays.asList(testTypesOne, testTypesTwo))
                .setVrm(generateRandomExcludingValues(7, vehicleSubmittedDataOne.build().getVrm()))
                .setVehicleType("psv")
                .setNoOfAxles(3)
                .setVehicleSize("small")
                .setVehicleConfiguration(("rigid")).build();

        testResultsSteps.postTestResults(vehicleSubmittedDataOne.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedData("lcp", "qgs");
    }

    @Title("CVSB-840 / CVSB-3364 - AC2 Map the test code with the test type - linked test type with a specific linked test code - Scenario 4")
    @Test
    public void testTestCodeMappingSpecificLinkedTestTypeScenario4() {
        // scenario 4
        // testTypeOne: testTypeId:39 ; vehicleType:psv ; vehicleSize:small ; vehicleConfiguration:rigid ; noOfAxles:3 ;
        // testTypeTwo: testTypeId:39 ; vehicleType:psv ; vehicleSize:small ; vehicleConfiguration:rigid ; noOfAxles:3 ;
        TestTypes testTypesOne = ((TestTypes) vehicleSubmittedDataOne.getTestTypes().get(0)).setTestTypeId("39");
        TestTypes testTypesTwo = ((TestTypes) vehicleSubmittedDataTwo.getTestTypes().get(0)).setTestTypeId("39");

        vehicleSubmittedDataOne.setVin(generateRandomExcludingValues(21, vehicleSubmittedDataOne.build().getVin()))
                .setTestTypes(Arrays.asList(testTypesOne, testTypesTwo) )
                .setVrm(generateRandomExcludingValues(7, vehicleSubmittedDataOne.build().getVrm()))
                .setVehicleType("psv")
                .setNoOfAxles(3)
                .setVehicleSize("small")
                .setVehicleConfiguration(("rigid")).build();

        testResultsSteps.postTestResults(vehicleSubmittedDataOne.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedData(  "lcp", "lcp");
    }

    @Title("CVSB-840 / CVSB-3368 - AC3 Map the test code with the test type - linked test type without a specific linked test code - Data Set 1")
    @Test
    public void testTestCodeMappingNoSpecificLinkedTestCodeDataSet1() {
        // testTypeOne: testTypeId:1 ; vehicleType:psv ; vehicleSize:large ; vehicleConfiguration:rigid ; noOfAxles:2 ;
        // testTypeTwo: testTypeId:30 ; vehicleType:psv ; vehicleSize:large ; vehicleConfiguration:rigid ; noOfAxles:2 ;
        TestTypes testTypesOne = ((TestTypes) vehicleSubmittedDataOne.getTestTypes().get(0)).setTestTypeId("1");
        TestTypes testTypesTwo = ((TestTypes) vehicleSubmittedDataTwo.getTestTypes().get(0)).setTestTypeId("30");

        vehicleSubmittedDataOne.setVin(generateRandomExcludingValues(21, vehicleSubmittedDataOne.build().getVin()))
                .setTestTypes(Arrays.asList(testTypesOne, testTypesTwo))
                .setVrm(generateRandomExcludingValues(7, vehicleSubmittedDataOne.build().getVrm()))
                .setVehicleType("psv")
                .setNoOfAxles(2)
                .setVehicleSize("large")
                .setVehicleConfiguration(("rigid")).build();

        testResultsSteps.postTestResults(vehicleSubmittedDataOne.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedData("aal", "qal");
    }

    @Title("CVSB-840 / CVSB-3368 - AC3 Map the test code with the test type - linked test type without a specific linked test code - Data Set 2")
    @Test
    public void testTestCodeMappingNoSpecificLinkedTestCodeDataSet2() {
        // testTypeOne: testTypeId:1 ; vehicleType:psv ; vehicleSize:small ; vehicleConfiguration:rigid ; noOfAxles:3 ;
        // testTypeTwo: testTypeId:30 ; vehicleType:psv ; vehicleSize:small ; vehicleConfiguration:rigid ; noOfAxles:3 ;
        TestTypes testTypesOne = ((TestTypes) vehicleSubmittedDataOne.getTestTypes().get(0)).setTestTypeId("1");
        TestTypes testTypesTwo = ((TestTypes) vehicleSubmittedDataTwo.getTestTypes().get(0)).setTestTypeId("30");

        vehicleSubmittedDataOne.setVin(generateRandomExcludingValues(21, vehicleSubmittedDataOne.build().getVin()))
                .setTestTypes(Arrays.asList(testTypesOne, testTypesTwo))
                .setVrm(generateRandomExcludingValues(7, vehicleSubmittedDataOne.build().getVrm()))
                .setVehicleType("psv")
                .setNoOfAxles(3)
                .setVehicleSize("small")
                .setVehicleConfiguration(("rigid")).build();

        testResultsSteps.postTestResults(vehicleSubmittedDataOne.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedData("aas", "qgs");
    }

    @Title("CVSB-840 / CVSB-3368 - AC3 Map the test code with the test type - linked test type without a specific linked test code - Data Set 3")
    @Test
    public void testTestCodeMappingNoSpecificLinkedTestCodeDataSet3() {
        // testTypeOne: testTypeId:1 ; vehicleType:psv ; vehicleSize:large ; vehicleConfiguration:articulated ; noOfAxles:2 ;
        // testTypeTwo: testTypeId:7 ; vehicleType:psv ; vehicleSize:large ; vehicleConfiguration:articulated ; noOfAxles:2 ;
        TestTypes testTypesOne = ((TestTypes) vehicleSubmittedDataOne.getTestTypes().get(0)).setTestTypeId("1");
        TestTypes testTypesTwo = ((TestTypes) vehicleSubmittedDataTwo.getTestTypes().get(0)).setTestTypeId("7");

        vehicleSubmittedDataOne.setVin(generateRandomExcludingValues(21, vehicleSubmittedDataOne.build().getVin()))
                .setTestTypes(Arrays.asList(testTypesOne, testTypesTwo))
                .setVrm(generateRandomExcludingValues(7, vehicleSubmittedDataOne.build().getVrm()))
                .setVehicleType("psv")
                .setNoOfAxles(2)
                .setVehicleSize("large")
                .setVehicleConfiguration(("articulated")).build();

        testResultsSteps.postTestResults(vehicleSubmittedDataOne.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedData("adl", "rhl");
    }

    @Title("CVSB-840 / CVSB-3368 - AC3 Map the test code with the test type - linked test type without a specific linked test code - Data Set 4")
    @Test
    public void testTestCodeMappingNoSpecificLinkedTestCodeDataSet4() {
        // testTypeOne: testTypeId:30 ; vehicleType:psv ; vehicleSize:large ; vehicleConfiguration:rigid ; noOfAxles:2 ;
        // testTypeTwo: testTypeId:1 ; vehicleType:psv ; vehicleSize:large ; vehicleConfiguration:rigid ; noOfAxles:2 ;
        TestTypes testTypesOne = ((TestTypes) vehicleSubmittedDataOne.getTestTypes().get(0)).setTestTypeId("30");
        TestTypes testTypesTwo = ((TestTypes) vehicleSubmittedDataTwo.getTestTypes().get(0)).setTestTypeId("1");

        vehicleSubmittedDataOne.setVin(generateRandomExcludingValues(21, vehicleSubmittedDataOne.build().getVin()))
                .setTestTypes(Arrays.asList(testTypesOne, testTypesTwo))
                .setVrm(generateRandomExcludingValues(7, vehicleSubmittedDataOne.build().getVrm()))
                .setVehicleType("psv")
                .setNoOfAxles(2)
                .setVehicleSize("large")
                .setVehicleConfiguration(("rigid")).build();

        testResultsSteps.postTestResults(vehicleSubmittedDataOne.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedData("qal", "aal");
    }

    @Title("CVSB-840 / CVSB-3368 - AC3 Map the test code with the test type - linked test type without a specific linked test code - Data Set 5")
    @Test
    public void testTestCodeMappingNoSpecificLinkedTestCodeDataSet5() {
        // testTypeOne: testTypeId:30 ; vehicleType:psv ; vehicleSize:small ; vehicleConfiguration:rigid ; noOfAxles:3 ;
        // testTypeTwo: testTypeId:1 ; vehicleType:psv ; vehicleSize:small ; vehicleConfiguration:rigid ; noOfAxles:3 ;
        TestTypes testTypesOne = ((TestTypes) vehicleSubmittedDataOne.getTestTypes().get(0)).setTestTypeId("30");
        TestTypes testTypesTwo = ((TestTypes) vehicleSubmittedDataTwo.getTestTypes().get(0)).setTestTypeId("1");

        vehicleSubmittedDataOne.setVin(generateRandomExcludingValues(21, vehicleSubmittedDataOne.build().getVin()))
                .setTestTypes(Arrays.asList(testTypesOne, testTypesTwo))
                .setVrm(generateRandomExcludingValues(7, vehicleSubmittedDataOne.build().getVrm()))
                .setVehicleType("psv")
                .setNoOfAxles(3)
                .setVehicleSize("small")
                .setVehicleConfiguration(("rigid")).build();

        testResultsSteps.postTestResults(vehicleSubmittedDataOne.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedData("qgs", "aas");
    }

    @Title("CVSB-840 / CVSB-3368 - AC3 Map the test code with the test type - linked test type without a specific linked test code - Data Set 6")
    @Test
    public void testTestCodeMappingNoSpecificLinkedTestCodeDataSet6() {
        // testTypeOne: testTypeId:7 ; vehicleType:psv ; vehicleSize:large ; vehicleConfiguration:articulated ; noOfAxles:2 ;
        // testTypeTwo: testTypeId:1 ; vehicleType:psv ; vehicleSize:large ; vehicleConfiguration:articulated ; noOfAxles:2 ;
        TestTypes testTypesOne = ((TestTypes) vehicleSubmittedDataOne.getTestTypes().get(0)).setTestTypeId("7");
        TestTypes testTypesTwo = ((TestTypes) vehicleSubmittedDataTwo.getTestTypes().get(0)).setTestTypeId("1");

        vehicleSubmittedDataOne.setVin(generateRandomExcludingValues(21, vehicleSubmittedDataOne.build().getVin()))
                .setTestTypes(Arrays.asList(testTypesOne, testTypesTwo))
                .setVrm(generateRandomExcludingValues(7, vehicleSubmittedDataOne.build().getVrm()))
                .setVehicleType("psv")
                .setNoOfAxles(2)
                .setVehicleSize("large")
                .setVehicleConfiguration(("articulated")).build();

        testResultsSteps.postTestResults(vehicleSubmittedDataOne.build());
        testResultsSteps.statusCodeShouldBe(201);
        testResultsSteps.validateData("Test records created");
        validateSavedData("rhl", "adl");
    }

}
