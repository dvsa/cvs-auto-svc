package testtypes;

import clients.model.*;
import data.TestTypeByIdData;
import model.testtypeid.TestTypeById;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import steps.TestTypeSteps;

import java.util.Arrays;

@WithTags(
        {
                @WithTag(type = "TestTypes", name = "All"),
                @WithTag(type = "TestTypes", name = "Positive"),
                @WithTag(type = "Service", name = "One"),

        }
)

@RunWith(SerenityRunner.class)
public class GetTestTypesByIdData {

    @Steps
    TestTypeSteps testTypeSteps;

    private TestTypeById annualCertificatePsvSmallRigid = TestTypeByIdData.buildAnnualCertificatePsvSmallRigid();
    private TestTypeById annualCertificatePsvLargeArticulated = TestTypeByIdData.buildAnnualCertificatePsvLargeArticulated();
    private TestTypeById annualCertificatePsvLargeRigid = TestTypeByIdData.buildAnnualCertificatePsvLargeRigid();
    private TestTypeById annualNoCertificatePsvSmallRigid = TestTypeByIdData.buildAnnualNoCertificatePsvSmallRigid();
    private TestTypeById nonAnnualPsvLargeRigid = TestTypeByIdData.buildNonAnnualPsvLargeRigid();
    private TestTypeById nonAnnualPsvSmallRigidTwoAxles = TestTypeByIdData.buildNonAnnualPsvSmallRigidTwoAxles();


    @Title("CVSB-1073 / CVSB-2403 - API Consumer retrieves data for Test Type Classification 'Annual With Certificate' (forVehicleType 'psv', forVehicleSize 'small', forVehicleConfiguration 'rigid')")
    @Test
    @Issue("CVSB-8484")
    public void testTypeAnnualCertificatePsvSmallRigid() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.TEST_TYPE_CLASSIFICATION, TestTypeField.DEFAULT_TEST_CODE, TestTypeField.LINKED_TEST_CODE))
                .setVehicleType(VehicleType.PSV)
                .setVehicleSize(VehicleSize.SMALL)
                .setVehicleConfiguration(VehicleConfiguration.RIGID);

        testTypeSteps.getTestTypesById(annualCertificatePsvSmallRigid.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.validateData(annualCertificatePsvSmallRigid, testTypeQueryParam.getFields());
    }

    @Title("CVSB-1073 / CVSB-2404 - API Consumer retrieves data for Test Type Classification 'Annual With Certificate' (forVehicleType 'psv', forVehicleSize 'large', forVehicleConfiguration 'articulated')")
    @Test
    @Issue("CVSB-8484")
    public void testTypeAnnualCertificatePsvLargeArticulated() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.TEST_TYPE_CLASSIFICATION, TestTypeField.DEFAULT_TEST_CODE, TestTypeField.LINKED_TEST_CODE))
                .setVehicleType(VehicleType.PSV)
                .setVehicleSize(VehicleSize.LARGE)
                .setVehicleConfiguration(VehicleConfiguration.ARTICULATED);

        testTypeSteps.getTestTypesById(annualCertificatePsvLargeArticulated.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.validateData(annualCertificatePsvLargeArticulated, testTypeQueryParam.getFields());
    }

    @Title("CVSB-1073 / CVSB-2405 - API Consumer retrieves data for Test Type Classification 'Annual With Certificate' (forVehicleType 'psv', forVehicleSize 'large', forVehicleConfiguration 'rigid')")
    @Test
    @Issue("CVSB-8484")
    public void testTypeAnnualCertificatePsvLargeRigid() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.TEST_TYPE_CLASSIFICATION, TestTypeField.DEFAULT_TEST_CODE, TestTypeField.LINKED_TEST_CODE))
                .setVehicleType(VehicleType.PSV)
                .setVehicleSize(VehicleSize.LARGE)
                .setVehicleConfiguration(VehicleConfiguration.RIGID);

        testTypeSteps.getTestTypesById(annualCertificatePsvLargeRigid.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.validateData(annualCertificatePsvLargeRigid, testTypeQueryParam.getFields());
    }

    @Title("CVSB-1073 / CVSB-2406 - API Consumer retrieves data for Test Type Classification 'Annual NO CERTIFICATE' (forVehicleType 'psv', forVehicleSize 'small', forVehicleConfiguration 'rigid')")
    @Test
    @Issue("CVSB-8484")
    public void testTypeNoCertificatePsvSmallRigid() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.TEST_TYPE_CLASSIFICATION, TestTypeField.DEFAULT_TEST_CODE, TestTypeField.LINKED_TEST_CODE))
                .setVehicleType(VehicleType.PSV)
                .setVehicleSize(VehicleSize.SMALL)
                .setVehicleConfiguration(VehicleConfiguration.RIGID)
                .setVehicleAxles(VehicleAxles.NULL);

        testTypeSteps.getTestTypesById(annualNoCertificatePsvSmallRigid.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.validateData(annualNoCertificatePsvSmallRigid, testTypeQueryParam.getFields());
    }

    @Title("CVSB-1073 / CVSB-2407 - API Consumer retrieves data for Test Type Classification 'NON ANNUAL' (forVehicleType 'psv', forVehicleSize 'large', forVehicleConfiguration 'rigid')")
    @Test
    public void testTypeNonAnnualPsvLargeRigid() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.TEST_TYPE_CLASSIFICATION, TestTypeField.DEFAULT_TEST_CODE, TestTypeField.LINKED_TEST_CODE))
                .setVehicleType(VehicleType.PSV)
                .setVehicleSize(VehicleSize.LARGE)
                .setVehicleConfiguration(VehicleConfiguration.RIGID)
                .setVehicleAxles(VehicleAxles.TWO);

        testTypeSteps.getTestTypesById(nonAnnualPsvLargeRigid.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.validateData(nonAnnualPsvLargeRigid, testTypeQueryParam.getFields());
    }

    @Title("CVSB-1073 / CVSB-2835 - API Consumer retrieves data for Test Type Classification 'NON ANNUAL' (forVehicleType 'psv', forVehicleSize 'small', forVehicleConfiguration 'rigid', vehicleAxles 2)")
    @Test
    public void testTypeNonAnnualPsvLargeRigidTwoAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.TEST_TYPE_CLASSIFICATION, TestTypeField.DEFAULT_TEST_CODE, TestTypeField.LINKED_TEST_CODE))
                .setVehicleType(VehicleType.PSV)
                .setVehicleSize(VehicleSize.SMALL)
                .setVehicleConfiguration(VehicleConfiguration.RIGID)
                .setVehicleAxles(VehicleAxles.TWO);

        testTypeSteps.getTestTypesById(nonAnnualPsvSmallRigidTwoAxles.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.validateData(nonAnnualPsvSmallRigidTwoAxles, testTypeQueryParam.getFields());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'aav2' (forVehicleType 'hgv', forVehicleConfiguration 'null', vehicleAxles 2)")
    @Test
    public void testTypeDefaultTestCodeAav2HGVTwoAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.HGV)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.TWO);

        testTypeSteps.getTestTypesById(TestTypes.AAV2.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.AAV2.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.AAV2.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'aav3' (forVehicleType 'hgv', forVehicleConfiguration 'null', vehicleAxles 3)")
    @Test
    public void testTypeDefaultTestCodeAav3HGVThreeAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.HGV)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.THREE);

        testTypeSteps.getTestTypesById(TestTypes.AAV3.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.AAV3.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.AAV3.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'aav4' (forVehicleType 'hgv', forVehicleConfiguration 'null', vehicleAxles 4)")
    @Test
    public void testTypeDefaultTestCodeAav4HGVFourAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.HGV)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.FOUR);

        testTypeSteps.getTestTypesById(TestTypes.AAV4.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.AAV4.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.AAV4.getTestCode());
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", "Annual With Certificate");
        testTypeSteps.valueForFieldInPathShouldBeNull("testTypeClassification");
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", "Annual With Certificate");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "aav4");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'aav5' (forVehicleType 'hgv', forVehicleConfiguration 'null', vehicleAxles 5)")
    @Test
    public void testTypeDefaultTestCodeAav5HGVFiveAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.HGV)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.FIVE);

        testTypeSteps.getTestTypesById(TestTypes.AAV5.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", "Annual With Certificate");
        testTypeSteps.valueForFieldInPathShouldBeNull("testTypeClassification");
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", "Annual With Certificate");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "aav5");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'aat1' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 1)")
    @Test
    public void testTypeDefaultTestCodeAat1TRLOneAxle() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.ONE);

        testTypeSteps.getTestTypesById(TestTypes.AAT1.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.AAT1.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.AAT1.getTestCode());
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", "Annual With Certificate");
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", "Annual With Certificate");
        testTypeSteps.valueForFieldInPathShouldBeNull("testTypeClassification");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "aat1");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'aat2' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 2)")
    @Test
    public void testTypeDefaultTestCodeAat2TRLTwoAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.TWO);

        testTypeSteps.getTestTypesById(TestTypes.AAT2.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.AAT2.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.AAT2.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'aat3' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 3)")
    @Test
    public void testTypeDefaultTestCodeAat3TRLThreeAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.THREE);

        testTypeSteps.getTestTypesById(TestTypes.AAT3.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.AAT3.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.AAT3.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'aat4' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 4)")
    @Test
    public void testTypeDefaultTestCodeAat4TRLFourAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.FOUR);

        testTypeSteps.getTestTypesById(TestTypes.AAT4.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.AAT4.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.AAT4.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'aat5' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 5)")
    @Test
    public void testTypeDefaultTestCodeAat5TRLFiveAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.FIVE);

        testTypeSteps.getTestTypesById(TestTypes.AAT5.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.AAT5.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.AAT5.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'ffv2' (forVehicleType 'hgv', forVehicleConfiguration 'null', vehicleAxles 2)")
    @Test
    public void testTypeDefaultTestCodeFfv2HGVTwoAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.HGV)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.TWO);

        testTypeSteps.getTestTypesById(TestTypes.FFV2.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.FFV2.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.FFV2.getTestCode());
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", "Annual With Certificate");
        testTypeSteps.valueForFieldInPathShouldBeNull("testTypeClassification");
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", "Annual With Certificate");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "ffv2");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'ffv3' (forVehicleType 'hgv', forVehicleConfiguration 'null', vehicleAxles 3)")
    @Test
    public void testTypeDefaultTestCodeFfv3HGVThreeAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.HGV)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.THREE);

        testTypeSteps.getTestTypesById(TestTypes.FFV3.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.FFV3.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.FFV3.getTestCode());
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", "Annual With Certificate");
        testTypeSteps.valueForFieldInPathShouldBeNull("testTypeClassification");
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", "Annual With Certificate");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "ffv3");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'ffv4' (forVehicleType 'hgv', forVehicleConfiguration 'null', vehicleAxles 4)")
    @Test
    public void testTypeDefaultTestCodeFfv4HGVFourAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.HGV)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.FOUR);

        testTypeSteps.getTestTypesById(TestTypes.FFV4.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.FFV4.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.FFV4.getTestCode());
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", "Annual With Certificate");
        testTypeSteps.valueForFieldInPathShouldBeNull("testTypeClassification");
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", "Annual With Certificate");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "ffv4");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'ffv5' (forVehicleType 'hgv', forVehicleConfiguration 'null', vehicleAxles 5)")
    @Test
    public void testTypeDefaultTestCodeFfv5HGVFiveAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.HGV)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.FIVE);

        testTypeSteps.getTestTypesById(TestTypes.FFV5.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.FFV5.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.FFV5.getTestCode());
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", "Annual With Certificate");
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", "Annual With Certificate");
        testTypeSteps.valueForFieldInPathShouldBeNull("testTypeClassification");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "ffv5");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'fft1' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 1)")
    @Test
    public void testTypeDefaultTestCodeFft1TRLOneAxle() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.ONE);

        testTypeSteps.getTestTypesById(TestTypes.FFT1.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.FFT1.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.FFT1.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'fft2' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 2)")
    @Test
    public void testTypeDefaultTestCodeFft2HGVTwoAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.TWO);

        testTypeSteps.getTestTypesById(TestTypes.FFT2.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.FFT2.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.FFT2.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'fft3' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 3)")
    @Test
    public void testTypeDefaultTestCodeFft3TRLThreeAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.THREE);

        testTypeSteps.getTestTypesById(TestTypes.FFT3.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.FFT3.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.FFT3.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'fft4' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 4)")
    @Test
    public void testTypeDefaultTestCodeFft4TRLFourAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.FOUR);

        testTypeSteps.getTestTypesById(TestTypes.FFT4.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.FFT4.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.FFT4.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'fft5' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 5)")
    @Test
    public void testTypeDefaultTestCodeFft5TRLFiveAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.FIVE);

        testTypeSteps.getTestTypesById(TestTypes.FFT5.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.FFT5.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.FFT5.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'ldv' (forVehicleType 'hgv', forVehicleConfiguration 'null')")
    @Test
    public void testTypeDefaultTestCodeLdvHGV() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.HGV)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
				.setVehicleAxles(VehicleAxles.NULL);

        testTypeSteps.getTestTypesById(TestTypes.LDV.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.LDV.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.LDV.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'lev' (forVehicleType 'hgv', forVehicleConfiguration 'null')")
    @Test
    public void testTypeDefaultTestCodeLevHGV() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.HGV)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
				.setVehicleAxles(VehicleAxles.NULL);

        testTypeSteps.getTestTypesById(TestTypes.LEV.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.LEV.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.LEV.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'nfv' (forVehicleType 'hgv', forVehicleConfiguration 'null')")
    @Test
    public void testTypeDefaultTestCodeNfvHGV() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.HGV)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
				.setVehicleAxles(VehicleAxles.NULL);

        testTypeSteps.getTestTypesById(TestTypes.NFV.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.NFV.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.NFV.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'nft' (forVehicleType 'trl', forVehicleConfiguration 'null')")
    @Test
    public void testTypeDefaultTestCodeNftTRL() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
				.setVehicleAxles(VehicleAxles.NULL);

        testTypeSteps.getTestTypesById(TestTypes.NFT.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.NFT.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.NFT.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'nvv' (forVehicleType 'hgv', forVehicleConfiguration 'null')")
    @Test
    public void testTypeDefaultTestCodeNvvHGV() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.HGV)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
				.setVehicleAxles(VehicleAxles.NULL);

        testTypeSteps.getTestTypesById(TestTypes.NVV.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.NVV.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.NVV.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'nvt' (forVehicleType 'trl', forVehicleConfiguration 'null')")
    @Test
    public void testTypeDefaultTestCodeNvtTRL() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
				.setVehicleAxles(VehicleAxles.NULL);

        testTypeSteps.getTestTypesById(TestTypes.NVT.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.NVT.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.NVT.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'tiv' (forVehicleType 'hgv', forVehicleConfiguration 'null')")
    @Test
    public void testTypeDefaultTestCodeTivHGV() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.HGV)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
				.setVehicleAxles(VehicleAxles.NULL);

        testTypeSteps.getTestTypesById(TestTypes.TIV.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.TIV.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.TIV.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'tit' (forVehicleType 'trl', forVehicleConfiguration 'null')")
    @Test
    public void testTypeDefaultTestCodeTitTRL() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
				.setVehicleAxles(VehicleAxles.NULL);

        testTypeSteps.getTestTypesById(TestTypes.TIT.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.TIT.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.TIT.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'ddv' (forVehicleType 'hgv', forVehicleConfiguration 'null')")
    @Test
    public void testTypeDefaultTestCodeDdvHGV() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.HGV)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
				.setVehicleAxles(VehicleAxles.NULL);

        testTypeSteps.getTestTypesById(TestTypes.DDV.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.DDV.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.DDV.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'ddt' (forVehicleType 'trl', forVehicleConfiguration 'null')")
    @Test
    public void testTypeDefaultTestCodeDdtTRL() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
				.setVehicleAxles(VehicleAxles.NULL);

        testTypeSteps.getTestTypesById(TestTypes.DDT.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.DDT.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.DDT.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'rpv2' (forVehicleType 'hgv', forVehicleConfiguration 'null', vehicleAxles 2)")
    @Test
    public void testTypeDefaultTestCodeRpv2HGVTwoAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.HGV)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.TWO);

        testTypeSteps.getTestTypesById(TestTypes.RPV2.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.RPV2.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.RPV2.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'rpv3' (forVehicleType 'hgv', forVehicleConfiguration 'null', vehicleAxles 3)")
    @Test
    public void testTypeDefaultTestCodeRpv3HGVThreeAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.HGV)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.THREE);

        testTypeSteps.getTestTypesById(TestTypes.RPV3.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.RPV3.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.RPV3.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'rpv4' (forVehicleType 'hgv', forVehicleConfiguration 'null', vehicleAxles 4)")
    @Test
    public void testTypeDefaultTestCodeRpv4HGVFourAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.HGV)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.FOUR);

        testTypeSteps.getTestTypesById(TestTypes.RPV4.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.RPV4.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.RPV4.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'rpv5' (forVehicleType 'hgv', forVehicleConfiguration 'null', vehicleAxles 5)")
    @Test
    public void testTypeDefaultTestCodeRpv5HGVFiveAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.HGV)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.FIVE);

        testTypeSteps.getTestTypesById(TestTypes.RPV5.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.RPV5.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.RPV5.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'rpt1' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 1)")
    @Test
    public void testTypeDefaultTestCodeRpt1TRLOneAxle() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.ONE);

        testTypeSteps.getTestTypesById(TestTypes.RPT1.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.RPT1.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.RPT1.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'rpt2' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 2)")
    @Test
    public void testTypeDefaultTestCodeRpt2TRLTwoAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.TWO);

        testTypeSteps.getTestTypesById(TestTypes.RPT2.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.RPT2.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.RPT2.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'rpt3' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 3)")
    @Test
    public void testTypeDefaultTestCodeRpt3TRLThreeAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.THREE);

        testTypeSteps.getTestTypesById(TestTypes.RPT3.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.RPT3.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.RPT3.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'rpt4' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 4)")
    @Test
    public void testTypeDefaultTestCodeRpt4TRLFourAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.FOUR);

        testTypeSteps.getTestTypesById(TestTypes.RPT4.getId(), testTypeQueryParam);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.RPT4.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.RPT4.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'rpt5' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 5)")
    @Test
    public void testTypeDefaultTestCodeRpt5TRLFiveAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.FIVE);

        testTypeSteps.getTestTypesById(TestTypes.RPT5.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.RPT5.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.RPT5.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'rsv2' (forVehicleType 'hgv', forVehicleConfiguration 'null', vehicleAxles 2)")
    @Test
    public void testTypeDefaultTestCodeRsv2HGVTwoAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.HGV)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.TWO);

        testTypeSteps.getTestTypesById(TestTypes.RSV2.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.RSV2.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.RSV2.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'rsv3' (forVehicleType 'hgv', forVehicleConfiguration 'null', vehicleAxles 3)")
    @Test
    public void testTypeDefaultTestCodeRsv3HGVThreeAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.HGV)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.THREE);

        testTypeSteps.getTestTypesById(TestTypes.RSV3.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.RSV3.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.RSV3.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'rsv4' (forVehicleType 'hgv', forVehicleConfiguration 'null', vehicleAxles 4)")
    @Test
    public void testTypeDefaultTestCodeRsv4HGVFourAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.HGV)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.FOUR);

        testTypeSteps.getTestTypesById(TestTypes.RSV4.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.RSV4.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.RSV4.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'rsv5' (forVehicleType 'hgv', forVehicleConfiguration 'null', vehicleAxles 5)")
    @Test
    public void testTypeDefaultTestCodeRsv5HGVFiveAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.HGV)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.FIVE);

        testTypeSteps.getTestTypesById(TestTypes.RSV5.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.RSV5.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.RSV5.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'rst1' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 1)")
    @Test
    public void testTypeDefaultTestCodeRst1TRLOneAxle() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.ONE);

        testTypeSteps.getTestTypesById(TestTypes.RST1.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.RST1.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.RST1.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'rst2' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 2)")
    @Test
    public void testTypeDefaultTestCodeRst2TRLTwoAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.TWO);

        testTypeSteps.getTestTypesById(TestTypes.RST2.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.RST2.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.RST2.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'rst3' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 3)")
    @Test
    public void testTypeDefaultTestCodeRst3TRLThreeAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.THREE);

        testTypeSteps.getTestTypesById(TestTypes.RST3.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.RST3.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.RST3.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'rst4' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 4)")
    @Test
    public void testTypeDefaultTestCodeRst4TRLFourAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.FOUR);

        testTypeSteps.getTestTypesById(TestTypes.RST4.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.RST4.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.RST4.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'rst5' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 5)")
    @Test
    public void testTypeDefaultTestCodeRst5TRLFiveAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.FIVE);

        testTypeSteps.getTestTypesById(TestTypes.RST5.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.RST5.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.RST5.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'trv' (forVehicleType 'hgv', forVehicleConfiguration 'null')")
    @Test
    public void testTypeDefaultTestCodeTrvHGV() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.HGV)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
				.setVehicleAxles(VehicleAxles.NULL);

        testTypeSteps.getTestTypesById(TestTypes.TRV.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.TRV.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.TRV.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'trt' (forVehicleType 'trl', forVehicleConfiguration 'null')")
    @Test
    public void testTypeDefaultTestCodeTrtTRL() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
				.setVehicleAxles(VehicleAxles.NULL);

        testTypeSteps.getTestTypesById(TestTypes.TRT.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.TRT.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.TRT.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'rft' (forVehicleType 'trl', forVehicleConfiguration 'null')")
    @Test
    public void testTypeDefaultTestCodeRftTRL() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
				.setVehicleAxles(VehicleAxles.NULL);

        testTypeSteps.getTestTypesById(TestTypes.RFT.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.RFT.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.RFT.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'arv' (forVehicleType 'hgv', forVehicleConfiguration 'null')")
    @Test
    public void testTypeDefaultTestCodeArvHGV() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.HGV)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
				.setVehicleAxles(VehicleAxles.NULL);

        testTypeSteps.getTestTypesById(TestTypes.ARV.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.ARV.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.ARV.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'art' (forVehicleType 'trl', forVehicleConfiguration 'null')")
    @Test
    public void testTypeDefaultTestCodeArtTRL() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
				.setVehicleAxles(VehicleAxles.NULL);

        testTypeSteps.getTestTypesById(TestTypes.ART.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.ART.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.ART.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'drv' (forVehicleType 'hgv', forVehicleConfiguration 'null')")
    @Test
    public void testTypeDefaultTestCodeDrvHGV() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.HGV)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
				.setVehicleAxles(VehicleAxles.NULL);

        testTypeSteps.getTestTypesById(TestTypes.DRV.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.DRV.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.DRV.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'drt' (forVehicleType 'trl', forVehicleConfiguration 'null')")
    @Test
    public void testTypeDefaultTestCodeDrtTRL() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
				.setVehicleAxles(VehicleAxles.NULL);

        testTypeSteps.getTestTypesById(TestTypes.DRT.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.DRT.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.DRT.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'qkv' (forVehicleType 'hgv', forVehicleConfiguration 'null', vehicleAxles 2)")
    @Test
    public void testTypeDefaultTestCodeQkvHGV() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.HGV)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.TWO);

        testTypeSteps.getTestTypesById(TestTypes.QKV.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.QKV.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.QKV.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'qkt' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 1)")
    @Test
    public void testTypeDefaultTestCodeQktTRL() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.ONE);

        testTypeSteps.getTestTypesById(TestTypes.QKT.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.QKT.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.QKT.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'qpv' (forVehicleType 'hgv', forVehicleConfiguration 'null', vehicleAxles 3)")
    @Test
    public void testTypeDefaultTestCodeQpvHGV() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.HGV)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.THREE);

        testTypeSteps.getTestTypesById(TestTypes.QPV.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.QPV.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.QPV.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'qpt' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 2)")
    @Test
    public void testTypeDefaultTestCodeQptTRL() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.TWO);

        testTypeSteps.getTestTypesById(TestTypes.QPT.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.QPT.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.QPT.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'qqv' (forVehicleType 'hgv', forVehicleConfiguration 'null', vehicleAxles 4)")
    @Test
    public void testTypeDefaultTestCodeQqvHGV() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.HGV)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.FOUR);

        testTypeSteps.getTestTypesById(TestTypes.QQV.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.QQV.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.QQV.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'qqt' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 3)")
    @Test
    public void testTypeDefaultTestCodeQqtTRL() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.THREE);

        testTypeSteps.getTestTypesById(TestTypes.QQT.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.QQT.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.QQT.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'ruv' (forVehicleType 'hgv', forVehicleConfiguration 'null')")
    @Test
    public void testTypeDefaultTestCodeRuvHGV() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.HGV)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
				.setVehicleAxles(VehicleAxles.NULL);

        testTypeSteps.getTestTypesById(TestTypes.RUV.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.RUV.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.RUV.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'rut' (forVehicleType 'trl', forVehicleConfiguration 'null')")
    @Test
    public void testTypeDefaultTestCodeRutTRL() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
				.setVehicleAxles(VehicleAxles.NULL);

        testTypeSteps.getTestTypesById(TestTypes.RUT.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.RUT.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.RUT.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'rgv2' (forVehicleType 'hgv', forVehicleConfiguration 'null', vehicleAxles 2)")
    @Test
    public void testTypeDefaultTestCodeRgv2HGVTwoAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.HGV)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.TWO);

        testTypeSteps.getTestTypesById(TestTypes.RGV2.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.RGV2.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.RGV2.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'rgv3' (forVehicleType 'hgv', forVehicleConfiguration 'null', vehicleAxles 3)")
    @Test
    public void testTypeDefaultTestCodeRgv3HGVThreeAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.HGV)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.THREE);

        testTypeSteps.getTestTypesById(TestTypes.RGV3.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.RGV3.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.RGV3.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'rgv4' (forVehicleType 'hgv', forVehicleConfiguration 'null', vehicleAxles 4)")
    @Test
    public void testTypeDefaultTestCodeRgv4HGVFourAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.HGV)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.FOUR);

        testTypeSteps.getTestTypesById(TestTypes.RGV4.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.RGV4.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.RGV4.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'rgv5' (forVehicleType 'hgv', forVehicleConfiguration 'null', vehicleAxles 5)")
    @Test
    public void testTypeDefaultTestCodeRgv5HGVFiveAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.HGV)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.FIVE);

        testTypeSteps.getTestTypesById(TestTypes.RGV5.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.RGV5.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.RGV5.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'rgt1' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 1)")
    @Test
    public void testTypeDefaultTestCodeRgt1TRLOneAxle() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.ONE);

        testTypeSteps.getTestTypesById(TestTypes.RGT1.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.RGT1.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.RGT1.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'rgt2' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 2)")
    @Test
    public void testTypeDefaultTestCodeRgt2TRLTwoAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.TWO);

        testTypeSteps.getTestTypesById(TestTypes.RGT2.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.RGT2.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.RGT2.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'rgt3' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 3)")
    @Test
    public void testTypeDefaultTestCodeRgt3TRLThreeAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.THREE);

        testTypeSteps.getTestTypesById(TestTypes.RGT3.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.RGT3.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.RGT3.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'rgt4' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 4)")
    @Test
    public void testTypeDefaultTestCodeRgt4TRLFourAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.FOUR);

        testTypeSteps.getTestTypesById(TestTypes.RGT4.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.RGT4.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.RGT4.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'rgt5' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 5)")
    @Test
    public void testTypeDefaultTestCodeRgt5TRLFiveAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.FIVE);

        testTypeSteps.getTestTypesById(TestTypes.RGT5.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.RGT5.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.RGT5.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'riv2' (forVehicleType 'hgv', forVehicleConfiguration 'null', vehicleAxles 2)")
    @Test
    public void testTypeDefaultTestCodeRiv2HGVTwoAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.HGV)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.TWO);

        testTypeSteps.getTestTypesById(TestTypes.RIV2.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.RIV2.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.RIV2.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'riv3' (forVehicleType 'hgv', forVehicleConfiguration 'null', vehicleAxles 3)")
    @Test
    public void testTypeDefaultTestCodeRiv3HGVThreeAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.HGV)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.THREE);

        testTypeSteps.getTestTypesById(TestTypes.RIV3.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.RIV3.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.RIV3.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'riv4' (forVehicleType 'hgv', forVehicleConfiguration 'null', vehicleAxles 4)")
    @Test
    public void testTypeDefaultTestCodeRiv4HGVFourAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.HGV)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.FOUR);

        testTypeSteps.getTestTypesById(TestTypes.RIV4.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.RIV4.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.RIV4.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'riv5' (forVehicleType 'hgv', forVehicleConfiguration 'null', vehicleAxles 5)")
    @Test
    public void testTypeDefaultTestCodeRiv5HGVFiveAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.HGV)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.FIVE);

        testTypeSteps.getTestTypesById(TestTypes.RIV5.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.RIV5.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.RIV5.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'rit1' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 1)")
    @Test
    public void testTypeDefaultTestCodeRit1TRLOneAxle() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.ONE);

        testTypeSteps.getTestTypesById(TestTypes.RIT1.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.RIT1.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.RIT1.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'rit2' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 2)")
    @Test
    public void testTypeDefaultTestCodeRit2TRLTwoAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.TWO);

        testTypeSteps.getTestTypesById(TestTypes.RIT2.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.RIT2.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.RIT2.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'rit3' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 3)")
    @Test
    public void testTypeDefaultTestCodeRit3TRLThreeAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.THREE);

        testTypeSteps.getTestTypesById(TestTypes.RIT3.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.RIT3.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.RIT3.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'rit4' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 4)")
    @Test
    public void testTypeDefaultTestCodeRit4TRLFourAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.FOUR);

        testTypeSteps.getTestTypesById(TestTypes.RIT4.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.RIT4.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.RIT4.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'rit5' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 5)")
    @Test
    public void testTypeDefaultTestCodeRit5TRLFiveAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.FIVE);

        testTypeSteps.getTestTypesById(TestTypes.RIT5.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.RIT5.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.RIT5.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'rht' (forVehicleType 'trl', forVehicleConfiguration 'null')")
    @Test
    public void testTypeDefaultTestCodeRhtTRLFiveAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
				.setVehicleAxles(VehicleAxles.NULL);

        testTypeSteps.getTestTypesById(TestTypes.RHT.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.RHT.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.RHT.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'p1v2' (forVehicleType 'hgv', forVehicleConfiguration 'null', vehicleAxles 2)")
    @Test
    public void testTypeDefaultTestCodeP1v2HGVTwoAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.HGV)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.TWO);

        testTypeSteps.getTestTypesById(TestTypes.P1V2.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.P1V2.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.P1V2.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'p1v3' (forVehicleType 'hgv', forVehicleConfiguration 'null', vehicleAxles 3)")
    @Test
    public void testTypeDefaultTestCodeP1v3HGVThreeAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.HGV)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.THREE);

        testTypeSteps.getTestTypesById(TestTypes.P1V3.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.P1V3.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.P1V3.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'p1v4' (forVehicleType 'hgv', forVehicleConfiguration 'null', vehicleAxles 4)")
    @Test
    public void testTypeDefaultTestCodeP1v4HGVFourAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.HGV)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.FOUR);

        testTypeSteps.getTestTypesById(TestTypes.P1V4.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.P1V4.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.P1V4.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'p1v5' (forVehicleType 'hgv', forVehicleConfiguration 'null', vehicleAxles 5)")
    @Test
    public void testTypeDefaultTestCodeP1v5HGVFiveAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.HGV)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.FIVE);

        testTypeSteps.getTestTypesById(TestTypes.P1V5.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.P1V5.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.P1V5.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'p1t1' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 1)")
    @Test
    public void testTypeDefaultTestCodeP1t1TRLOneAxle() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.ONE);

        testTypeSteps.getTestTypesById(TestTypes.P1T1.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.P1T1.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.P1T1.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'p1t2' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 2)")
    @Test
    public void testTypeDefaultTestCodeP1t2TRLTwoAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.TWO);

        testTypeSteps.getTestTypesById(TestTypes.P1T2.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.P1T2.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.P1T2.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'p1t3' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 3)")
    @Test
    public void testTypeDefaultTestCodeP1t3TRLThreeAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.THREE);

        testTypeSteps.getTestTypesById(TestTypes.P1T3.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.P1T3.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.P1T3.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'p1t4' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 4)")
    @Test
    public void testTypeDefaultTestCodeP1t4TRLFourAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.FOUR);

        testTypeSteps.getTestTypesById(TestTypes.P1T4.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.P1T4.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.P1T4.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'p1t5' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 5)")
    @Test
    public void testTypeDefaultTestCodeP1t5TRLFiveAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.FIVE);

        testTypeSteps.getTestTypesById(TestTypes.P1T5.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.P1T5.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.P1T5.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'pbv2' (forVehicleType 'hgv', forVehicleConfiguration 'null', vehicleAxles 2)")
    @Test
    public void testTypeDefaultTestCodePbv2HGVTwoAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.HGV)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.TWO);

        testTypeSteps.getTestTypesById(TestTypes.PBV2.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.PBV2.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.PBV2.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'pbv3' (forVehicleType 'hgv', forVehicleConfiguration 'null', vehicleAxles 3)")
    @Test
    public void testTypeDefaultTestCodePbv3HGVThreeAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.HGV)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.THREE);

        testTypeSteps.getTestTypesById(TestTypes.PBV3.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.PBV3.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.PBV3.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'pbv4' (forVehicleType 'hgv', forVehicleConfiguration 'null', vehicleAxles 4)")
    @Test
    public void testTypeDefaultTestCodePbv4HGVFourAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.HGV)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.FOUR);

        testTypeSteps.getTestTypesById(TestTypes.PBV4.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.PBV4.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.PBV4.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'pbv5' (forVehicleType 'hgv', forVehicleConfiguration 'null', vehicleAxles 5)")
    @Test
    public void testTypeDefaultTestCodePbv5HGVFiveAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.HGV)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.FIVE);

        testTypeSteps.getTestTypesById(TestTypes.PBV5.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.PBV5.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.PBV5.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'pbt1' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 1)")
    @Test
    public void testTypeDefaultTestCodePbt1TRLOneAxle() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.ONE);

        testTypeSteps.getTestTypesById(TestTypes.PBT1.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.PBT1.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.PBT1.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'pbt2' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 2)")
    @Test
    public void testTypeDefaultTestCodePbt2TRLTwoAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.TWO);

        testTypeSteps.getTestTypesById(TestTypes.PBT2.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.PBT2.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.PBT2.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'pbt3' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 3)")
    @Test
    public void testTypeDefaultTestCodePbt3TRLThreeAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.THREE);

        testTypeSteps.getTestTypesById(TestTypes.PBT3.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.PBT3.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.PBT3.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'pbt4' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 4)")
    @Test
    public void testTypeDefaultTestCodePbt4TRLFourAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.FOUR);

        testTypeSteps.getTestTypesById(TestTypes.PBT4.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.PBT4.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.PBT4.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'pbt5' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 5)")
    @Test
    public void testTypeDefaultTestCodePbt5TRLFiveAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.FIVE);

        testTypeSteps.getTestTypesById(TestTypes.PBT5.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.PBT5.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.PBT5.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'pov2' (forVehicleType 'hgv', forVehicleConfiguration 'null', vehicleAxles 2)")
    @Test
    public void testTypeDefaultTestCodePov2HGVTwoAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.HGV)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.TWO);

        testTypeSteps.getTestTypesById(TestTypes.POV2.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.POV2.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.POV2.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'pov3' (forVehicleType 'hgv', forVehicleConfiguration 'null', vehicleAxles 3)")
    @Test
    public void testTypeDefaultTestCodePov3HGVThreeAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.HGV)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.THREE);

        testTypeSteps.getTestTypesById(TestTypes.POV3.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.POV3.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.POV3.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'pov4' (forVehicleType 'hgv', forVehicleConfiguration 'null', vehicleAxles 4)")
    @Test
    public void testTypeDefaultTestCodePov4HGVFourAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.HGV)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.FOUR);

        testTypeSteps.getTestTypesById(TestTypes.POV4.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.POV4.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.POV4.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'pov5' (forVehicleType 'hgv', forVehicleConfiguration 'null', vehicleAxles 5)")
    @Test
    public void testTypeDefaultTestCodePov5HGVFiveAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.HGV)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.FIVE);

        testTypeSteps.getTestTypesById(TestTypes.POV5.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.POV5.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.POV5.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'pot1' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 1)")
    @Test
    public void testTypeDefaultTestCodePot1TRLOneAxle() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.ONE);

        testTypeSteps.getTestTypesById(TestTypes.POT1.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.POT1.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.POT1.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'pot2' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 2)")
    @Test
    public void testTypeDefaultTestCodePot2TRLTwoAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.TWO);

        testTypeSteps.getTestTypesById(TestTypes.POT2.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.POT2.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.POT2.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'pot3' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 3)")
    @Test
    public void testTypeDefaultTestCodePot3TRLThreeAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.THREE);

        testTypeSteps.getTestTypesById(TestTypes.POT3.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.POT3.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.POT3.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'pot4' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 4)")
    @Test
    public void testTypeDefaultTestCodePot4TRLFourAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.FOUR);

        testTypeSteps.getTestTypesById(TestTypes.POT4.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.POT4.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.POT4.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'pot5' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 5)")
    @Test
    public void testTypeDefaultTestCodePot5TRLFiveAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.FIVE);

        testTypeSteps.getTestTypesById(TestTypes.POT5.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.POT5.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.POT5.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'pav2' (forVehicleType 'hgv', forVehicleConfiguration 'null', vehicleAxles 2)")
    @Test
    public void testTypeDefaultTestCodePav2HGVTwoAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.HGV)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.TWO);

        testTypeSteps.getTestTypesById(TestTypes.PAV2.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.PAV2.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.PAV2.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'pav3' (forVehicleType 'hgv', forVehicleConfiguration 'null', vehicleAxles 3)")
    @Test
    public void testTypeDefaultTestCodePav3HGVThreeAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.HGV)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.THREE);

        testTypeSteps.getTestTypesById(TestTypes.PAV3.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.PAV3.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.PAV3.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'pav4' (forVehicleType 'hgv', forVehicleConfiguration 'null', vehicleAxles 4)")
    @Test
    public void testTypeDefaultTestCodePav4HGVFourAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.HGV)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.FOUR);

        testTypeSteps.getTestTypesById(TestTypes.PAV4.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.PAV4.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.PAV4.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'pav5' (forVehicleType 'hgv', forVehicleConfiguration 'null', vehicleAxles 5)")
    @Test
    public void testTypeDefaultTestCodePav5HGVFiveAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.HGV)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.FIVE);

        testTypeSteps.getTestTypesById(TestTypes.PAV5.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.PAV5.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.PAV5.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'pat1' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 1)")
    @Test
    public void testTypeDefaultTestCodePat1TRLOneAxle() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.ONE);

        testTypeSteps.getTestTypesById(TestTypes.PAT1.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.PAT1.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.PAT1.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'pat2' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 2)")
    @Test
    public void testTypeDefaultTestCodePat2TRLTwoAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.TWO);

        testTypeSteps.getTestTypesById(TestTypes.PAT2.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.PAT2.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.PAT2.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'pat3' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 3)")
    @Test
    public void testTypeDefaultTestCodePat3TRLThreeAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.THREE);

        testTypeSteps.getTestTypesById(TestTypes.PAT3.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.PAT3.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.PAT3.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'pat4' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 4)")
    @Test
    public void testTypeDefaultTestCodePat4TRLFourAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.FOUR);

        testTypeSteps.getTestTypesById(TestTypes.PAT4.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.PAT4.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.PAT4.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'pat5' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 5)")
    @Test
    public void testTypeDefaultTestCodePat5TRLFiveAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.FIVE);

        testTypeSteps.getTestTypesById(TestTypes.PAT5.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.PAT5.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.PAT5.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'p3v2' (forVehicleType 'hgv', forVehicleConfiguration 'null', vehicleAxles 2)")
    @Test
    public void testTypeDefaultTestCodeP3v2HGVTwoAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.HGV)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.TWO);

        testTypeSteps.getTestTypesById(TestTypes.P3V2.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.P3V2.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.P3V2.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'p3v3' (forVehicleType 'hgv', forVehicleConfiguration 'null', vehicleAxles 3)")
    @Test
    public void testTypeDefaultTestCodeP3v3HGVThreeAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.HGV)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.THREE);

        testTypeSteps.getTestTypesById(TestTypes.P3V3.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.P3V3.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.P3V3.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'p3v4' (forVehicleType 'hgv', forVehicleConfiguration 'null', vehicleAxles 4)")
    @Test
    public void testTypeDefaultTestCodeP3v4HGVFourAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.HGV)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.FOUR);

        testTypeSteps.getTestTypesById(TestTypes.P3V4.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.P3V4.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.P3V4.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'p3v5' (forVehicleType 'hgv', forVehicleConfiguration 'null', vehicleAxles 5)")
    @Test
    public void testTypeDefaultTestCodeP3v5HGVFiveAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.HGV)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.FIVE);

        testTypeSteps.getTestTypesById(TestTypes.P3V5.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.P3V5.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.P3V5.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'p3t1' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 1)")
    @Test
    public void testTypeDefaultTestCodeP3t1TRLOneAxle() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.ONE);

        testTypeSteps.getTestTypesById(TestTypes.P3T1.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.P3T1.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.P3T1.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'p3t2' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 2)")
    @Test
    public void testTypeDefaultTestCodeP3t2TRLTwoAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.TWO);

        testTypeSteps.getTestTypesById(TestTypes.P3T2.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.P3T2.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.P3T2.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'p3t3' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 3)")
    @Test
    public void testTypeDefaultTestCodeP3t3TRLThreeAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.THREE);

        testTypeSteps.getTestTypesById(TestTypes.P3T3.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.P3T3.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.P3T3.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'p3t4' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 4)")
    @Test
    public void testTypeDefaultTestCodeP3t4TRLFourAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.FOUR);

        testTypeSteps.getTestTypesById(TestTypes.P3T4.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.P3T4.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.P3T4.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'p3t5' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 5)")
    @Test
    public void testTypeDefaultTestCodeP3t5TRLFiveAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.FIVE);

        testTypeSteps.getTestTypesById(TestTypes.P3T5.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.P3T5.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.P3T5.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'pfv2' (forVehicleType 'hgv', forVehicleConfiguration 'null', vehicleAxles 2)")
    @Test
    public void testTypeDefaultTestCodePfv2HGVTwoAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.HGV)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.TWO);

        testTypeSteps.getTestTypesById(TestTypes.PFV2.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.PFV2.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.PFV2.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'pfv3' (forVehicleType 'hgv', forVehicleConfiguration 'null', vehicleAxles 3)")
    @Test
    public void testTypeDefaultTestCodePfv3HGVThreeAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.HGV)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.THREE);

        testTypeSteps.getTestTypesById(TestTypes.PFV3.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.PFV3.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.PFV3.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'pfv4' (forVehicleType 'hgv', forVehicleConfiguration 'null', vehicleAxles 4)")
    @Test
    public void testTypeDefaultTestCodePfv4HGVFourAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.HGV)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.FOUR);

        testTypeSteps.getTestTypesById(TestTypes.PFV4.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.PFV4.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.PFV4.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'pfv5' (forVehicleType 'hgv', forVehicleConfiguration 'null', vehicleAxles 5)")
    @Test
    public void testTypeDefaultTestCodePfv5HGVFiveAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.HGV)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.FIVE);

        testTypeSteps.getTestTypesById(TestTypes.PFV5.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.PFV5.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.PFV5.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'pft1' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 1)")
    @Test
    public void testTypeDefaultTestCodePft1TRLOneAxle() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.ONE);

        testTypeSteps.getTestTypesById(TestTypes.PFT1.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.PFT1.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.PFT1.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'pft2' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 2)")
    @Test
    public void testTypeDefaultTestCodePft2TRLTwoAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.TWO);

        testTypeSteps.getTestTypesById(TestTypes.PFT2.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.PFT2.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.PFT2.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'pft3' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 3)")
    @Test
    public void testTypeDefaultTestCodePft3TRLThreeAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.THREE);

        testTypeSteps.getTestTypesById(TestTypes.PFT3.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.PFT3.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.PFT3.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'pft4' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 4)")
    @Test
    public void testTypeDefaultTestCodePft4TRLFourAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.FOUR);

        testTypeSteps.getTestTypesById(TestTypes.PFT4.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.PFT4.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.PFT4.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'pft5' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 5)")
    @Test
    public void testTypeDefaultTestCodePft5TRLFiveAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.FIVE);

        testTypeSteps.getTestTypesById(TestTypes.PFT5.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.PFT5.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.PFT5.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'p6v2' (forVehicleType 'hgv', forVehicleConfiguration 'null', vehicleAxles 2)")
    @Test
    public void testTypeDefaultTestCodeP6v2HGVTwoAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.HGV)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.TWO);

        testTypeSteps.getTestTypesById(TestTypes.P6V2.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.P6V2.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.P6V2.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'p6v3' (forVehicleType 'hgv', forVehicleConfiguration 'null', vehicleAxles 3)")
    @Test
    public void testTypeDefaultTestCodeP6v3HGVThreeAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.HGV)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.THREE);

        testTypeSteps.getTestTypesById(TestTypes.P6V3.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.P6V3.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.P6V3.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'p6v4' (forVehicleType 'hgv', forVehicleConfiguration 'null', vehicleAxles 4)")
    @Test
    public void testTypeDefaultTestCodeP6v4HGVFourAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.HGV)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.FOUR);

        testTypeSteps.getTestTypesById(TestTypes.P6V4.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.P6V4.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.P6V4.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'p6v5' (forVehicleType 'hgv', forVehicleConfiguration 'null', vehicleAxles 5)")
    @Test
    public void testTypeDefaultTestCodeP6v5HGVFiveAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.HGV)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.FIVE);

        testTypeSteps.getTestTypesById(TestTypes.P6V5.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.P6V5.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.P6V5.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'p6t1' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 1)")
    @Test
    public void testTypeDefaultTestCodeP6t1TRLOneAxle() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.ONE);

        testTypeSteps.getTestTypesById(TestTypes.P6T1.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.P6T1.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.P6T1.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'p6t2' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 2)")
    @Test
    public void testTypeDefaultTestCodeP6t2TRLTwoAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.TWO);

        testTypeSteps.getTestTypesById(TestTypes.P6T2.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.P6T2.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.P6T2.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'p6t3' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 3)")
    @Test
    public void testTypeDefaultTestCodeP6t3TRLThreeAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.THREE);

        testTypeSteps.getTestTypesById(TestTypes.P6T3.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.P6T3.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.P6T3.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'p6t4' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 4)")
    @Test
    public void testTypeDefaultTestCodeP6t4TRLFourAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.FOUR);

        testTypeSteps.getTestTypesById(TestTypes.P6T4.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.P6T4.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.P6T4.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'p6t5' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 5)")
    @Test
    public void testTypeDefaultTestCodeP6t5TRLFiveAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.FIVE);

        testTypeSteps.getTestTypesById(TestTypes.P6T5.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.P6T5.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.P6T5.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'p2v2' (forVehicleType 'hgv', forVehicleConfiguration 'null', vehicleAxles 2)")
    @Test
    public void testTypeDefaultTestCodeP2v2HGVTwoAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.HGV)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.TWO);

        testTypeSteps.getTestTypesById(TestTypes.P2V2.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.P2V2.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.P2V2.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'p2v3' (forVehicleType 'hgv', forVehicleConfiguration 'null', vehicleAxles 3)")
    @Test
    public void testTypeDefaultTestCodeP2v3HGVThreeAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.HGV)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.THREE);

        testTypeSteps.getTestTypesById(TestTypes.P2V3.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.P2V3.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.P2V3.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'p2v4' (forVehicleType 'hgv', forVehicleConfiguration 'null', vehicleAxles 4)")
    @Test
    public void testTypeDefaultTestCodeP2v4HGVFourAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.HGV)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.FOUR);

        testTypeSteps.getTestTypesById(TestTypes.P2V4.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.P2V4.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.P2V4.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'p2v5' (forVehicleType 'hgv', forVehicleConfiguration 'null', vehicleAxles 5)")
    @Test
    public void testTypeDefaultTestCodeP2v5HGVFiveAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.HGV)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.FIVE);

        testTypeSteps.getTestTypesById(TestTypes.P2V5.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.P2V5.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.P2V5.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'p2t1' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 1)")
    @Test
    public void testTypeDefaultTestCodeP2t1TRLOneAxle() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.ONE);

        testTypeSteps.getTestTypesById(TestTypes.P2T1.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.P2T1.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.P2T1.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'p2t2' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 2)")
    @Test
    public void testTypeDefaultTestCodeP2t2TRLTwoAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.TWO);

        testTypeSteps.getTestTypesById(TestTypes.P2T2.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.P2T2.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.P2T2.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'p2t3' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 3)")
    @Test
    public void testTypeDefaultTestCodeP2t3TRLThreeAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.THREE);

        testTypeSteps.getTestTypesById(TestTypes.P2T3.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.P2T3.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.P2T3.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'p2t4' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 4)")
    @Test
    public void testTypeDefaultTestCodeP2t4TRLFourAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.FOUR);

        testTypeSteps.getTestTypesById(TestTypes.P2T4.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.P2T4.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.P2T4.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'p2t5' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 5)")
    @Test
    public void testTypeDefaultTestCodeP2t5TRLFiveAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.FIVE);

        testTypeSteps.getTestTypesById(TestTypes.P2T5.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.P2T5.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.P2T5.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'p7v2' (forVehicleType 'hgv', forVehicleConfiguration 'null', vehicleAxles 2)")
    @Test
    public void testTypeDefaultTestCodeP7v2HGVTwoAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.HGV)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.TWO);

        testTypeSteps.getTestTypesById(TestTypes.P7V2.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.P7V2.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.P7V2.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'p7v3' (forVehicleType 'hgv', forVehicleConfiguration 'null', vehicleAxles 3)")
    @Test
    public void testTypeDefaultTestCodeP7v3HGVThreeAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.HGV)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.THREE);

        testTypeSteps.getTestTypesById(TestTypes.P7V3.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.P7V3.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.P7V3.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'p7v4' (forVehicleType 'hgv', forVehicleConfiguration 'null', vehicleAxles 4)")
    @Test
    public void testTypeDefaultTestCodeP7v4HGVFourAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.HGV)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.FOUR);

        testTypeSteps.getTestTypesById(TestTypes.P7V4.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.P7V4.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.P7V4.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'p7v5' (forVehicleType 'hgv', forVehicleConfiguration 'null', vehicleAxles 5)")
    @Test
    public void testTypeDefaultTestCodeP7v5HGVFiveAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.HGV)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.FIVE);

        testTypeSteps.getTestTypesById(TestTypes.P7V5.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.P7V5.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.P7V5.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'p7t1' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 1)")
    @Test
    public void testTypeDefaultTestCodeP7t1TRLOneAxle() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.ONE);

        testTypeSteps.getTestTypesById(TestTypes.P7T1.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.P7T1.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.P7T1.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'p7t2' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 2)")
    @Test
    public void testTypeDefaultTestCodeP7t2TRLTwoAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.TWO);

        testTypeSteps.getTestTypesById(TestTypes.P7T2.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.P7T2.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.P7T2.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'p7t3' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 3)")
    @Test
    public void testTypeDefaultTestCodeP7t3TRLThreeAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.THREE);

        testTypeSteps.getTestTypesById(TestTypes.P7T3.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.P7T3.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.P7T3.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'p7t4' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 4)")
    @Test
    public void testTypeDefaultTestCodeP7t4TRLFourAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.FOUR);

        testTypeSteps.getTestTypesById(TestTypes.P7T4.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.P7T4.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.P7T4.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'p7t5' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 5)")
    @Test
    public void testTypeDefaultTestCodeP7t5TRLFiveAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.FIVE);

        testTypeSteps.getTestTypesById(TestTypes.P7T5.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.P7T5.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.P7T5.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'p4v2' (forVehicleType 'hgv', forVehicleConfiguration 'null', vehicleAxles 2)")
    @Test
    public void testTypeDefaultTestCodeP4v2HGVTwoAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.HGV)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.TWO);

        testTypeSteps.getTestTypesById(TestTypes.P4V2.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.P4V2.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.P4V2.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'p4v3' (forVehicleType 'hgv', forVehicleConfiguration 'null', vehicleAxles 3)")
    @Test
    public void testTypeDefaultTestCodeP4v3HGVThreeAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.HGV)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.THREE);

        testTypeSteps.getTestTypesById(TestTypes.P4V3.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.P4V3.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.P4V3.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'p4v4' (forVehicleType 'hgv', forVehicleConfiguration 'null', vehicleAxles 4)")
    @Test
    public void testTypeDefaultTestCodeP4v4HGVFourAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.HGV)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.FOUR);

        testTypeSteps.getTestTypesById(TestTypes.P4V4.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.P4V4.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.P4V4.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'p4v5' (forVehicleType 'hgv', forVehicleConfiguration 'null', vehicleAxles 5)")
    @Test
    public void testTypeDefaultTestCodeP4v5HGVFiveAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.HGV)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.FIVE);

        testTypeSteps.getTestTypesById(TestTypes.P4V5.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.P4V5.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.P4V5.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'p4t1' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 1)")
    @Test
    public void testTypeDefaultTestCodeP4t1TRLOneAxle() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.ONE);

        testTypeSteps.getTestTypesById(TestTypes.P4T1.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.P4T1.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.P4T1.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'p4t2' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 2)")
    @Test
    public void testTypeDefaultTestCodeP4t2TRLTwoAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.TWO);

        testTypeSteps.getTestTypesById(TestTypes.P4T2.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.P4T2.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.P4T2.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'p4t3' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 3)")
    @Test
    public void testTypeDefaultTestCodeP4t3TRLThreeAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.THREE);

        testTypeSteps.getTestTypesById(TestTypes.P4T3.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.P4T3.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.P4T3.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'p4t4' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 4)")
    @Test
    public void testTypeDefaultTestCodeP4t4TRLFourAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.FOUR);

        testTypeSteps.getTestTypesById(TestTypes.P4T4.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.P4T4.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.P4T4.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'p4t5' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 5)")
    @Test
    public void testTypeDefaultTestCodeP4t5TRLFiveAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.FIVE);

        testTypeSteps.getTestTypesById(TestTypes.P4T5.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.P4T5.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.P4T5.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'bib' (forVehicleType 'hgv', forVehicleConfiguration 'null', vehicleAxles 1)")
    @Test
    public void testTypeDefaultTestCodeBibHGVOneAxle() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.HGV)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.ONE);

        testTypeSteps.getTestTypesById(TestTypes.BIB.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.BIB.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.BIB.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'qav2' (forVehicleType 'hgv', forVehicleConfiguration 'null', vehicleAxles 2)")
    @Test
    public void testTypeDefaultTestCodeQav2HGVTwoAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.HGV)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.TWO);

        testTypeSteps.getTestTypesById(TestTypes.QAV2.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.QAV2.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.QAV2.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'qav3' (forVehicleType 'hgv', forVehicleConfiguration 'null', vehicleAxles 3)")
    @Test
    public void testTypeDefaultTestCodeQav3HGVThreeAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.HGV)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.THREE);

        testTypeSteps.getTestTypesById(TestTypes.QAV3.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.QAV3.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.QAV3.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'qav4' (forVehicleType 'hgv', forVehicleConfiguration 'null', vehicleAxles 4)")
    @Test
    public void testTypeDefaultTestCodeQav4HGVFourAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.HGV)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.FOUR);

        testTypeSteps.getTestTypesById(TestTypes.QAV4.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.QAV4.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.QAV4.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'qav5' (forVehicleType 'hgv', forVehicleConfiguration 'null', vehicleAxles 5)")
    @Test
    public void testTypeDefaultTestCodeQav5HGVFiveAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.HGV)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.FIVE);

        testTypeSteps.getTestTypesById(TestTypes.QAV5.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.QAV5.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.QAV5.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'qat1' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 1)")
    @Test
    public void testTypeDefaultTestCodeQat1TRLOneAxle() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.ONE);

        testTypeSteps.getTestTypesById(TestTypes.QAT1.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.QAT1.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.QAT1.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'qat2' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 2)")
    @Test
    public void testTypeDefaultTestCodeQat2TRLTwoAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.TWO);

        testTypeSteps.getTestTypesById(TestTypes.QAT2.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.QAT2.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.QAT2.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'qat3' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 3)")
    @Test
    public void testTypeDefaultTestCodeQat3TRLThreeAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.THREE);

        testTypeSteps.getTestTypesById(TestTypes.QAT3.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.QAT3.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.QAT3.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'qat4' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 4)")
    @Test
    public void testTypeDefaultTestCodeQat4TRLFourAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.FOUR);

        testTypeSteps.getTestTypesById(TestTypes.QAT4.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.QAT4.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.QAT4.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'qat5' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 5)")
    @Test
    public void testTypeDefaultTestCodeQat5TRLFiveAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.FIVE);

        testTypeSteps.getTestTypesById(TestTypes.QAT5.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.QAT5.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.QAT5.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'qbv' (forVehicleType 'hgv', forVehicleConfiguration 'null')")
    @Test
    public void testTypeDefaultTestCodeQbvHGV() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.HGV)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.NULL);

        testTypeSteps.getTestTypesById(TestTypes.QBV.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.QBV.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.QBV.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'bid' (forVehicleType 'hgv', forVehicleConfiguration 'null')")
    @Test
    public void testTypeDefaultTestCodeBidHGV() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.HGV)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.NULL);

        testTypeSteps.getTestTypesById(TestTypes.BID_HGV.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.BID_HGV.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.BID_HGV.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'bid' (forVehicleType 'trl', forVehicleConfiguration 'null')")
    @Test
    public void testTypeDefaultTestCodeBidTRL() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
	            .setVehicleAxles(VehicleAxles.NULL);

        testTypeSteps.getTestTypesById(TestTypes.BID_TRL.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.BID_TRL.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.BID_TRL.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'bif' (forVehicleType 'hgv', forVehicleConfiguration 'null')")
    @Test
    public void testTypeDefaultTestCodeBifHGV() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.HGV)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
			    .setVehicleAxles(VehicleAxles.NULL);

        testTypeSteps.getTestTypesById(TestTypes.BIF_HGV.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.BIF_HGV.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.BIF_HGV.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'qcv' (forVehicleType 'hgv', forVehicleConfiguration 'null')")
    @Test
    public void testTypeDefaultTestCodeQcvHGV() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.HGV)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
				.setVehicleAxles(VehicleAxles.NULL);

        testTypeSteps.getTestTypesById(TestTypes.QCV.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.QCV.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.QCV.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'qdv' (forVehicleType 'hgv', forVehicleConfiguration 'null')")
    @Test
    public void testTypeDefaultTestCodeQdvHGV() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.HGV)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
				.setVehicleAxles(VehicleAxles.NULL);

        testTypeSteps.getTestTypesById(TestTypes.QDV.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.QDV.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.QDV.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'qjv2' (forVehicleType 'hgv', forVehicleConfiguration 'null', vehicleAxles 2)")
    @Test
    public void testTypeDefaultTestCodeQjv2HGVTwoAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.HGV)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.TWO);

        testTypeSteps.getTestTypesById(TestTypes.QJV2.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.QJV2.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.QJV2.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'qjv3' (forVehicleType 'hgv', forVehicleConfiguration 'null', vehicleAxles 3)")
    @Test
    public void testTypeDefaultTestCodeQjv3HGVThreeAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.HGV)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.THREE);

        testTypeSteps.getTestTypesById(TestTypes.QJV3.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.QJV3.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.QJV3.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'qjv4' (forVehicleType 'hgv', forVehicleConfiguration 'null', vehicleAxles 4)")
    @Test
    public void testTypeDefaultTestCodeQjv4HGVFourAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.HGV)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.FOUR);

        testTypeSteps.getTestTypesById(TestTypes.QJV4.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.QJV4.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.QJV4.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'qjv5' (forVehicleType 'hgv', forVehicleConfiguration 'null', vehicleAxles 5)")
    @Test
    public void testTypeDefaultTestCodeQjv5HGVFiveAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.HGV)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.FIVE);

        testTypeSteps.getTestTypesById(TestTypes.QJV5.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.QJV5.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.QJV5.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'qjt1' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 1)")
    @Test
    public void testTypeDefaultTestCodeQjt1TRLOneAxle() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.ONE);

        testTypeSteps.getTestTypesById(TestTypes.QJT1.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.QJT1.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.QJT1.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'qjt2' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 2)")
    @Test
    public void testTypeDefaultTestCodeQjt2TRLTwoAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.TWO);

        testTypeSteps.getTestTypesById(TestTypes.QJT2.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.QJT2.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.QJT2.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'qjt3' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 3)")
    @Test
    public void testTypeDefaultTestCodeQjt3TRLThreeAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.THREE);

        testTypeSteps.getTestTypesById(TestTypes.QJT3.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.QJT3.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.QJT3.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'qjt4' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 4)")
    @Test
    public void testTypeDefaultTestCodeQjt4TRLFourAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.FOUR);

        testTypeSteps.getTestTypesById(TestTypes.QJT4.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.QJT4.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.QJT4.getTestCode());
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'qjt5' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 5)")
    @Test
    public void testTypeDefaultTestCodeQjt5TRLFiveAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.FIVE);

        testTypeSteps.getTestTypesById(TestTypes.QJT5.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", TestTypes.QJT5.getClassification());
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.QJT5.getTestCode());
    }

    @Title("CVSB-7392 / CVSB-8480 - AC2.1 API Consumer sends a valid string value for 'vehicleConfiguration' and additional filtering to return only one record")
    @Test
    public void validateTestTypeValidVehicleConfigurationAdditionalFiltering() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE))
                .setVehicleType(VehicleType.PSV)
                .setVehicleConfiguration(VehicleConfiguration.RIGID)
                .setVehicleSize(VehicleSize.LARGE)
                .setVehicleAxles(VehicleAxles.NULL);

        testTypeSteps.getTestTypesById(TestTypes.AAL.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.AAL.getTestCode());
    }

    @Title("CVSB-7392 / CVSB-8480 - AC2.1 API Consumer sends a valid string value for 'vehicleConfiguration' and no other additional filtering")
    @Test
    @Issue("CVSB-8484")
    public void validateTestTypeValidVehicleConfigurationNoAdditionalFiltering() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE))
                .setVehicleType(VehicleType.PSV)
                .setVehicleConfiguration(VehicleConfiguration.ARTICULATED);

        testTypeSteps.getTestTypesById(TestTypes.ADL.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.ADL.getTestCode());
    }

    @Title("CVSB-7392 / CVSB-8480 - AC2.1 API Consumer sends a random string value for 'vehicleConfiguration'")
    @Test
    public void validateTestTypeInvalidVehicleConfiguration() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE))
                .setVehicleType(VehicleType.PSV)
                .setVehicleConfiguration(VehicleConfiguration.INVALID)
                .setVehicleAxles(VehicleAxles.NULL);

        testTypeSteps.getTestTypesById(TestTypes.AAL.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(404);
        testTypeSteps.validateRawData("\"No resources match the search criteria.\"");
    }

    @Title("CVSB-7392 / CVSB-8480 - AC2.2 API Consumer sends a null value for 'vehicleConfiguration'")
    @Test
    public void validateTestTypeNullVehicleConfiguration() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE))
                .setVehicleType(VehicleType.HGV)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.NULL);

        testTypeSteps.getTestTypesById(TestTypes.QBV.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.QBV.getTestCode());
    }

    @Title("CVSB-7392 / CVSB-8480 - AC2.3 API Consumer does not send the 'vehicleConfiguration' parameter at all")
    @Test
    public void validateTestTypeNoVehicleConfiguration() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.HGV)
                .setVehicleAxles(VehicleAxles.NULL);

        testTypeSteps.getTestTypesById(TestTypes.QBV.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.QBV.getTestCode());
    }

    @Title("CVSB-7392 / CVSB-8480 - AC4 API Consumer retrieves a PSV category or test type with 'forVehicleConfiguration' different than null - OK")
    @Test
    public void validatePSVTestTypeValidVehicleConfiguration() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE))
                .setVehicleType(VehicleType.PSV)
                .setVehicleConfiguration(VehicleConfiguration.RIGID)
                .setVehicleSize(VehicleSize.SMALL)
                .setVehicleAxles(VehicleAxles.NULL);

        testTypeSteps.getTestTypesById(TestTypes.AAS.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.AAS.getTestCode());
    }

    @Title("CVSB-7392 / CVSB-8480 - AC4 API Consumer retrieves a TRL category or test type with 'forVehicleConfiguration' null - OK")
    @Test
    public void validateTRL1TestTypeValidVehicleConfiguration() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.TWO);

        testTypeSteps.getTestTypesById(TestTypes.P1T2.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.P1T2.getTestCode());
    }

    @Title("CVSB-7392 / CVSB-8480 - AC4 API Consumer retrieves a TRL category or test type with 'forVehicleConfiguration' null - OK")
    @Test
    @Issue("CVSB-8484")
    public void validateTRL2TestTypeValidVehicleConfiguration() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL);

        testTypeSteps.getTestTypesById(TestTypes.ART.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", TestTypes.ART.getTestCode());
    }

    @Title("CVSB-7392 / CVSB-8480 - AC5 API Consumer retrieves a category or test type with 'forVehicleConfiguration' different than null - NOT Found")
    @Test
    public void validateTestTypeValidVehicleConfigurationIncompatibleFilters() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE))
                .setVehicleType(VehicleType.PSV)
                .setVehicleConfiguration(VehicleConfiguration.ARTICULATED)
                .setVehicleSize(VehicleSize.SMALL)
                .setVehicleAxles(VehicleAxles.NULL);

        testTypeSteps.getTestTypesById(TestTypes.ADL.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(404);
        testTypeSteps.validateRawData("\"No resources match the search criteria.\"");
    }


}
