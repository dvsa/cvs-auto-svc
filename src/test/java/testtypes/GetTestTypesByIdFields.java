package testtypes;

import clients.model.*;
import data.TestTypeByIdData;
import model.TestTypeById;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.core.annotations.WithTags;
import org.junit.Test;
import org.junit.runner.RunWith;
import steps.TestTypeSteps;

import java.util.Arrays;

@WithTags(
        {
                @WithTag(type = "TestTypes", name = "All"),
                @WithTag(type = "TestTypes", name = "Negative"),
                @WithTag(type = "Suite", name = "Negative"),

        }
)

@RunWith(SerenityRunner.class)
public class GetTestTypesByIdFields {

    @Steps
    TestTypeSteps testTypeSteps;

    private TestTypeById testTypeById = TestTypeByIdData.buildAnnualCertificatePsvSmallRigid();


    @Title("CVSB-1073 / CVSB-2205 - AC4 API Consumer retrieve test types attributes ('testTypeClassification' 'defaultTestCode' and 'linkedTestCode' attributes) based on the testId")
    @Test
    public void testTypeALlTestTypeFields() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.TEST_TYPE_CLASSIFICATION,TestTypeField.DEFAULT_TEST_CODE, TestTypeField.LINKED_TEST_CODE))
                .setVehicleType(VehicleType.PSV)
                .setVehicleSize(VehicleSize.SMALL)
                .setVehicleConfiguration(VehicleConfiguration.RIGID);

        testTypeSteps.getTestTypesById(testTypeById.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.validateData(testTypeById,testTypeQueryParam.getFields());
    }

    @Title("CVSB-1073 / CVSB-2397 - API Consumer retrieve test types attributes ('testTypeClassification' attribute) based on the testId")
    @Test
    public void testTypeALlTestTypeClassification() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.PSV)
                .setVehicleSize(VehicleSize.SMALL)
                .setVehicleConfiguration(VehicleConfiguration.RIGID);

        testTypeSteps.getTestTypesById(testTypeById.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.validateData(testTypeById,testTypeQueryParam.getFields());
    }

    @Title("CVSB-1073 / CVSB-2398 - API Consumer retrieve test types attributes ( ‘defaultTestCode’ attribute) based on the testId")
    @Test
    public void testTypeTestTypeDefaultTestCode() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE))
                .setVehicleType(VehicleType.PSV)
                .setVehicleSize(VehicleSize.SMALL)
                .setVehicleConfiguration(VehicleConfiguration.RIGID);

        testTypeSteps.getTestTypesById(testTypeById.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.validateData(testTypeById,testTypeQueryParam.getFields());
    }

    @Title("CVSB-1073 / CVSB-2399 - API Consumer retrieve test types attributes ('linkedTestCode' attribute) based on the testId")
    @Test
    public void testTypeTestTypeLinkedTestCode() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.LINKED_TEST_CODE))
                .setVehicleType(VehicleType.PSV)
                .setVehicleSize(VehicleSize.SMALL)
                .setVehicleConfiguration(VehicleConfiguration.RIGID);

        testTypeSteps.getTestTypesById(testTypeById.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.validateData(testTypeById,testTypeQueryParam.getFields());
    }

    @Title("CVSB-1073 / CVSB-2400 - API Consumer retrieve test types attributes ('testTypeClassification' and 'defaultTestCode' attributes) based on the testId")
    @Test
    public void testTypeTestTypeClassificationAndDefaultTestCode() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.TEST_TYPE_CLASSIFICATION,TestTypeField.DEFAULT_TEST_CODE))
                .setVehicleType(VehicleType.PSV)
                .setVehicleSize(VehicleSize.SMALL)
                .setVehicleConfiguration(VehicleConfiguration.RIGID);

        testTypeSteps.getTestTypesById(testTypeById.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.validateData(testTypeById,testTypeQueryParam.getFields());
    }

    @Title("CVSB-1073 / CVSB-2401 - API Consumer retrieve test types attributes ('testTypeClassification' and 'linkedTestCode' attributes) based on the testId")
    @Test
    public void testTypeTestTypeClassificationAndLinkedTestCode() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.TEST_TYPE_CLASSIFICATION,TestTypeField.LINKED_TEST_CODE))
                .setVehicleType(VehicleType.PSV)
                .setVehicleSize(VehicleSize.SMALL)
                .setVehicleConfiguration(VehicleConfiguration.RIGID);

        testTypeSteps.getTestTypesById(testTypeById.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.validateData(testTypeById,testTypeQueryParam.getFields());
    }

    @Title("CVSB-1073 / CVSB-2402 - API Consumer retrieve test types attributes ('defaultTestCode' and 'linkedTestCode' attributes) based on the testId")
    @Test
    public void testTypeDefaultTestCodeAndLinkedTestCode() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE,TestTypeField.LINKED_TEST_CODE))
                .setVehicleType(VehicleType.PSV)
                .setVehicleSize(VehicleSize.SMALL)
                .setVehicleConfiguration(VehicleConfiguration.RIGID);

        testTypeSteps.getTestTypesById(testTypeById.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.validateData(testTypeById,testTypeQueryParam.getFields());
    }


}
