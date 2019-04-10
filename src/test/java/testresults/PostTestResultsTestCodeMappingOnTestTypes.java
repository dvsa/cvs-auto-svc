package testresults;

import data.TestResultsData;
import model.testresults.*;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.core.annotations.WithTags;
import org.junit.Test;
import org.junit.runner.RunWith;
import steps.TestResultsSteps;

import java.util.Arrays;

import static util.DataUtil.generateRandomExcludingValues;

@WithTags(
        {
                @WithTag(type = "TestResults", name = "All"),
                @WithTag(type = "TestResults", name = "Positive"),
                @WithTag(type = "Service", name = "One"),

        }
)

@RunWith(SerenityRunner.class)
public class PostTestResultsTestCodeMappingOnTestTypes {

    @Steps
    TestResultsSteps testResultsSteps;

    private TestResults.Builder vehicleSubmittedDataOne = TestResultsData.buildTestResultsSubmittedData();
    private TestResults.Builder vehicleSubmittedDataTwo = TestResultsData.buildTestResultsSubmittedData();

    private void validateSavedData(String... testCodes) {

        testResultsSteps.getTestResults(vehicleSubmittedDataOne.build().getVin(), TestResultsStatus.SUBMITTED);
        testResultsSteps.statusCodeShouldBe(200);
//        testResultsSteps.validateData((TestResultsGet) vehicleSubmittedDataOne.build());
        testResultsSteps.validateTestCode((TestResultsGet) vehicleSubmittedDataOne.build(), testCodes);
    }

    @Title("CVSB-840 / CVSB-3360 - Map the test code with the test type - not a linked test type")
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

    @Title("CVSB-840 / CVSB-3364 - AC2 Map the test code with the test type - linked test type with a specific linked test code")
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

    @Title("CVSB-840 / CVSB-3368 - AC3 Map the test code with the test type - linked test type without a specific linked test code")
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
