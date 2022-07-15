package testtypes;

import clients.model.*;
import data.TestTypeByIdData;
import model.testtypeid.TestTypeById;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Test;
import org.junit.runner.RunWith;
import steps.TestTypeSteps;

import java.util.Arrays;
import java.util.stream.Collectors;


@RunWith(SerenityRunner.class)
public class GetTestTypesByIdNeg {

    @Steps
    TestTypeSteps testTypeSteps;

    private TestTypeById testTypeById = TestTypeByIdData.buildAnnualCertificatePsvSmallRigid();

    @Title("CVSB-1073 / CVSB-2408 - Query param 'fields' missing")
    @Test
    public void testTypeMissingTestTypeFieldsNotPresent() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(null)
                .setVehicleType(VehicleType.PSV)
                .setVehicleSize(VehicleSize.SMALL)
                .setVehicleConfiguration(VehicleConfiguration.RIGID);

        testTypeSteps.getTestTypesById(testTypeById.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(400);
        testTypeSteps.validateData("Query parameter \\\"fields\\\" is required");
    }

    @Title("CVSB-1073 / CVSB-2409 - Query param 'fields' empty")
    @Test
    public void testTypeMissingTestTypeFieldsEmptyData() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList())
                .setVehicleType(VehicleType.PSV)
                .setVehicleSize(VehicleSize.SMALL)
                .setVehicleConfiguration(VehicleConfiguration.RIGID);

        testTypeSteps.getTestTypesById(testTypeById.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(400);
        testTypeSteps.validateData("Query parameter \\\"fields\\\" is not allowed to be empty");
    }

    @Title("CVSB-1073 / CVSB-2411 - Query param 'fields' containing testTypeClassification, defaultTestCode and invalid test type field")
    @Test
    public void testTypeInvalidTestTypeFieldLinkedTestCode() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.TEST_TYPE_CLASSIFICATION, TestTypeField.DEFAULT_TEST_CODE, TestTypeField.INVALID))
                .setVehicleType(VehicleType.PSV)
                .setVehicleSize(VehicleSize.SMALL)
                .setVehicleConfiguration(VehicleConfiguration.RIGID);

        testTypeSteps.getTestTypesById(testTypeById.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(400);
        testTypeSteps.validateWrongSetOfFields("fails to match the required pattern");
    }

    @Title("CVSB-1073 / CVSB-2413 - Query param 'fields' containing testTypeClassification, linkedTestCode and invalid test type field")
    @Test
    public void testTypeInvalidTestTypeFieldDefaultTestCode() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.TEST_TYPE_CLASSIFICATION, TestTypeField.INVALID, TestTypeField.LINKED_TEST_CODE))
                .setVehicleType(VehicleType.PSV)
                .setVehicleSize(VehicleSize.SMALL)
                .setVehicleConfiguration(VehicleConfiguration.RIGID);

        testTypeSteps.getTestTypesById(testTypeById.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(400);
        testTypeSteps.validateWrongSetOfFields("fails to match the required pattern");
    }

    @Title("CVSB-1073 / CVSB-2414 - Query param 'fields' containing defaultTestCode, linkedTestCode and invalid test type field")
    @Test
    public void testTypeInvalidTestTypeFieldTestTypeClassification() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.INVALID, TestTypeField.DEFAULT_TEST_CODE, TestTypeField.LINKED_TEST_CODE))
                .setVehicleType(VehicleType.PSV)
                .setVehicleSize(VehicleSize.SMALL)
                .setVehicleConfiguration(VehicleConfiguration.RIGID);

        testTypeSteps.getTestTypesById(testTypeById.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(400);
        testTypeSteps.validateWrongSetOfFields("fails to match the required pattern");
    }



    @Title("CVSB-1073 / CVSB-2418 - Query param 'vehicleType' missing")
    @Test
    public void testTypeMissingRequiredFieldVehicleType() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.TEST_TYPE_CLASSIFICATION, TestTypeField.DEFAULT_TEST_CODE, TestTypeField.LINKED_TEST_CODE))
                .setVehicleSize(VehicleSize.SMALL)
                .setVehicleConfiguration(VehicleConfiguration.RIGID);

        testTypeSteps.getTestTypesById(testTypeById.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(400);
        testTypeSteps.validateData("Query parameter \\\"vehicleType\\\" is required");
    }

    @Title("CVSB-1073 / CVSB-2419 - Query param 'vehicleType' empty")
    @Test
    public void testTypeEmptyRequiredFieldVehicleType() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.TEST_TYPE_CLASSIFICATION, TestTypeField.DEFAULT_TEST_CODE, TestTypeField.LINKED_TEST_CODE))
                .setVehicleType(VehicleType.EMPTY)
                .setVehicleSize(VehicleSize.SMALL)
                .setVehicleConfiguration(VehicleConfiguration.RIGID);

        testTypeSteps.getTestTypesById(testTypeById.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(400);
        testTypeSteps.validateData("Query parameter \\\"vehicleType\\\" is not allowed to be empty");
    }

    @Title("CVSB-1073 / CVSB-2420 - Query param 'vehicleSize' missing")
    @Test
    public void testTypeMissingRequiredFieldVehicleSize() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.TEST_TYPE_CLASSIFICATION, TestTypeField.DEFAULT_TEST_CODE, TestTypeField.LINKED_TEST_CODE))
                .setVehicleType(VehicleType.PSV)
                .setVehicleConfiguration(VehicleConfiguration.ARTICULATED);

        testTypeSteps.getTestTypesById(testTypeById.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
    }

    @Title("CVSB-1073 / CVSB-2421 - Query param 'vehicleSize' empty")
    @Test
    public void testTypeEmptyRequiredFieldVehicleSize() {
            TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                    .setFields(Arrays.asList(TestTypeField.TEST_TYPE_CLASSIFICATION, TestTypeField.DEFAULT_TEST_CODE, TestTypeField.LINKED_TEST_CODE))
                    .setVehicleType(VehicleType.PSV)
                    .setVehicleSize(VehicleSize.EMPTY)
                    .setVehicleConfiguration(VehicleConfiguration.RIGID);
            testTypeSteps.getTestTypesById(testTypeById.getId(), testTypeQueryParam);
            testTypeSteps.statusCodeShouldBe(400);
            testTypeSteps.validateData("Query parameter \\\"vehicleSize\\\" is not allowed to be empty");
        }


    @Title("CVSB-1073 / CVSB-2422 - Query param 'vehicleConfiguration' missing")
    @Test
    public void testTypeMissingRequiredFieldVehicleConfiguration() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.TEST_TYPE_CLASSIFICATION, TestTypeField.DEFAULT_TEST_CODE, TestTypeField.LINKED_TEST_CODE))
                .setVehicleType(VehicleType.PSV)
                .setVehicleSize(VehicleSize.SMALL);

        testTypeSteps.getTestTypesById(testTypeById.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);

    }

    @Title("CVSB-1073 / CVSB-2423 - Query param 'vehicleConfiguration' empty")
    @Test
    public void testTypeEmptyRequiredFieldVehicleConfiguration() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.TEST_TYPE_CLASSIFICATION, TestTypeField.DEFAULT_TEST_CODE, TestTypeField.LINKED_TEST_CODE))
                .setVehicleType(VehicleType.PSV)
                .setVehicleSize(VehicleSize.SMALL)
                .setVehicleConfiguration(VehicleConfiguration.EMPTY);

        testTypeSteps.getTestTypesById(testTypeById.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(400);
        testTypeSteps.validateData("Query parameter \\\"vehicleConfiguration\\\" is not allowed to be empty");
    }

    @Title("CVSB-1073 / CVSB-2024 - Query param 'vehicleType' invalid")
    @Test
    public void testTypeInvalidFieldVehicleType() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.TEST_TYPE_CLASSIFICATION, TestTypeField.DEFAULT_TEST_CODE, TestTypeField.LINKED_TEST_CODE))
                .setVehicleType(VehicleType.INVALID)
                .setVehicleSize(VehicleSize.SMALL)
                .setVehicleConfiguration(VehicleConfiguration.RIGID);

        testTypeSteps.getTestTypesById(testTypeById.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(400);
        testTypeSteps.validateData("Query parameter \\\"vehicleType\\\" must be one of [" + buildAvailableValuesOfProperties(VehicleType.class) + "]");
    }

    @Title("CVSB-1073 / CVSB-2025 - Query param 'vehicleSize' invalid")
    @Test
    public void testTypeInvalidFieldVehicleSize() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.TEST_TYPE_CLASSIFICATION, TestTypeField.DEFAULT_TEST_CODE, TestTypeField.LINKED_TEST_CODE))
                .setVehicleType(VehicleType.PSV)
                .setVehicleSize(VehicleSize.INVALID)
                .setVehicleConfiguration(VehicleConfiguration.RIGID);

        testTypeSteps.getTestTypesById(testTypeById.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(400);
        testTypeSteps.validateData("Query parameter \\\"vehicleSize\\\" must be one of [small, large]");
    }

    @Title("CVSB-1073 / CVSB-2026 - Query param 'vehicleConfiguration' invalid")
    @Test
    public void testTypeInvalidFieldVehicleConfiguration() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.TEST_TYPE_CLASSIFICATION, TestTypeField.DEFAULT_TEST_CODE, TestTypeField.LINKED_TEST_CODE))
                .setVehicleType(VehicleType.PSV)
                .setVehicleSize(VehicleSize.SMALL)
                .setVehicleConfiguration(VehicleConfiguration.INVALID);

        testTypeSteps.getTestTypesById(testTypeById.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(400);
        testTypeSteps.validateData("Query parameter \\\"vehicleConfiguration\\\" must be one of [articulated, rigid, centre axle drawbar, semi-car transporter, semi-trailer, low loader, other, drawbar, four-in-line, dolly, full drawbar, null]");
    }



    private <T extends Enum<T> & PropertyValued> String buildAvailableValuesOfProperties(Class<T> classOfEnum) {
        return Arrays.stream(classOfEnum.getEnumConstants()).filter(e -> !("invalid" .equalsIgnoreCase(e.name()) || "empty".equalsIgnoreCase(e.name()))).map(e -> e.getValue()).collect(Collectors.joining(", "));
    }

    @Title("VTA-696 - Get test types Missing parameter value check")
    @Test
    public void testResultsMissingParameter() {
        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.TEST_TYPE_CLASSIFICATION, TestTypeField.DEFAULT_TEST_CODE, TestTypeField.LINKED_TEST_CODE))
                .setVehicleType(VehicleType.PSV)
                .setVehicleSize(VehicleSize.LARGE)
                .setVehicleConfiguration(VehicleConfiguration.ARTICULATED);

        testTypeSteps.getTestTypesById(" ", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(400);
        testTypeSteps.validateResp("\"Missing parameter value.\"");

        testTypeSteps.getTestTypesById("undefined", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(400);
        testTypeSteps.validateResp("\"Missing parameter value.\"");

        testTypeSteps.getTestTypesById("null", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(400);
        testTypeSteps.validateResp("\"Missing parameter value.\"");
    }
}
