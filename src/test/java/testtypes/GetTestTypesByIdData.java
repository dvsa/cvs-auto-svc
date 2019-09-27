package testtypes;

import clients.model.*;
import data.TestTypeByIdData;
import model.testtypeid.TestTypeById;
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
    public void testTypeNoCertificatePsvSmallRigid() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.TEST_TYPE_CLASSIFICATION, TestTypeField.DEFAULT_TEST_CODE, TestTypeField.LINKED_TEST_CODE))
                .setVehicleType(VehicleType.PSV)
                .setVehicleSize(VehicleSize.SMALL)
                .setVehicleConfiguration(VehicleConfiguration.RIGID);

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
                .setVehicleConfiguration(VehicleConfiguration.RIGID);

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
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE))
                .setVehicleType(VehicleType.HGV)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.TWO);

        testTypeSteps.getTestTypesById("40", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBeNull("testTypeClassification");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "aav2");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'aav3' (forVehicleType 'hgv', forVehicleConfiguration 'null', vehicleAxles 3)")
    @Test
    public void testTypeDefaultTestCodeAav3HGVThreeAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE))
                .setVehicleType(VehicleType.HGV)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.THREE);

        testTypeSteps.getTestTypesById("40", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBeNull("testTypeClassification");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "aav3");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'aav4' (forVehicleType 'hgv', forVehicleConfiguration 'null', vehicleAxles 4)")
    @Test
    public void testTypeDefaultTestCodeAav4HGVFourAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE))
                .setVehicleType(VehicleType.HGV)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.FOUR);

        testTypeSteps.getTestTypesById("40", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBeNull("testTypeClassification");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "aav4");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'aav5' (forVehicleType 'hgv', forVehicleConfiguration 'null', vehicleAxles 5)")
    @Test
    public void testTypeDefaultTestCodeAav5HGVFiveAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE))
                .setVehicleType(VehicleType.HGV)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.FIVE);

        testTypeSteps.getTestTypesById("40", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBeNull("testTypeClassification");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "aav5");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'aat1' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 1)")
    @Test
    public void testTypeDefaultTestCodeAat1TRLOneAxle() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.ONE);

        testTypeSteps.getTestTypesById("94", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBeNull("testTypeClassification");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "aat1");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'aat2' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 2)")
    @Test
    public void testTypeDefaultTestCodeAat2TRLTwoAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.TWO);

        testTypeSteps.getTestTypesById("40", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBeNull("testTypeClassification");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "aat2");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'aat3' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 3)")
    @Test
    public void testTypeDefaultTestCodeAat3TRLThreeAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.THREE);

        testTypeSteps.getTestTypesById("40", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBeNull("testTypeClassification");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "aat3");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'aat4' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 4)")
    @Test
    public void testTypeDefaultTestCodeAat4TRLFourAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.FOUR);

        testTypeSteps.getTestTypesById("40", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBeNull("testTypeClassification");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "aat4");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'aat5' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 5)")
    @Test
    public void testTypeDefaultTestCodeAat5TRLFiveAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.FIVE);

        testTypeSteps.getTestTypesById("40", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBeNull("testTypeClassification");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "aat5");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'ffv2' (forVehicleType 'hgv', forVehicleConfiguration 'null', vehicleAxles 2)")
    @Test
    public void testTypeDefaultTestCodeFfv2HGVTwoAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE))
                .setVehicleType(VehicleType.HGV)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.TWO);

        testTypeSteps.getTestTypesById("41", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBeNull("testTypeClassification");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "ffv2");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'ffv3' (forVehicleType 'hgv', forVehicleConfiguration 'null', vehicleAxles 3)")
    @Test
    public void testTypeDefaultTestCodeFfv3HGVThreeAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE))
                .setVehicleType(VehicleType.HGV)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.THREE);

        testTypeSteps.getTestTypesById("41", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBeNull("testTypeClassification");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "ffv3");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'ffv4' (forVehicleType 'hgv', forVehicleConfiguration 'null', vehicleAxles 4)")
    @Test
    public void testTypeDefaultTestCodeFfv4HGVFourAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE))
                .setVehicleType(VehicleType.HGV)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.FOUR);

        testTypeSteps.getTestTypesById("41", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBeNull("testTypeClassification");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "ffv4");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'ffv5' (forVehicleType 'hgv', forVehicleConfiguration 'null', vehicleAxles 5)")
    @Test
    public void testTypeDefaultTestCodeFfv5HGVFiveAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE))
                .setVehicleType(VehicleType.HGV)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.FIVE);

        testTypeSteps.getTestTypesById("41", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBeNull("testTypeClassification");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "ffv5");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'fft1' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 1)")
    @Test
    public void testTypeDefaultTestCodeFft1TRLOneAxle() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.ONE);

        testTypeSteps.getTestTypesById("95", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBeNull("testTypeClassification");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "fft1");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'fft2' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 2)")
    @Test
    public void testTypeDefaultTestCodeFft2HGVTwoAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.TWO);

        testTypeSteps.getTestTypesById("41", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBeNull("testTypeClassification");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "fft2");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'fft3' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 3)")
    @Test
    public void testTypeDefaultTestCodeFft3TRLThreeAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.THREE);

        testTypeSteps.getTestTypesById("41", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBeNull("testTypeClassification");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "fft3");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'fft4' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 4)")
    @Test
    public void testTypeDefaultTestCodeFft4TRLFourAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.FOUR);

        testTypeSteps.getTestTypesById("41", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBeNull("testTypeClassification");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "fft4");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'fft5' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 5)")
    @Test
    public void testTypeDefaultTestCodeFft5TRLFiveAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.FIVE);

        testTypeSteps.getTestTypesById("41", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBeNull("testTypeClassification");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "fft5");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'ldv' (forVehicleType 'hgv', forVehicleConfiguration 'null')")
    @Test
    public void testTypeDefaultTestCodeLdvHGV() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.HGV)
                .setVehicleConfiguration(VehicleConfiguration.NULL);

        testTypeSteps.getTestTypesById("44", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", "Annual With Certificate");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "ldv");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'lev' (forVehicleType 'hgv', forVehicleConfiguration 'null')")
    @Test
    public void testTypeDefaultTestCodeLevHGV() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.HGV)
                .setVehicleConfiguration(VehicleConfiguration.NULL);

        testTypeSteps.getTestTypesById("45", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", "Annual With Certificate");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "lev");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'nfv' (forVehicleType 'hgv', forVehicleConfiguration 'null')")
    @Test
    public void testTypeDefaultTestCodeNfvHGV() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.HGV)
                .setVehicleConfiguration(VehicleConfiguration.NULL);

        testTypeSteps.getTestTypesById("47", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", "Annual NO CERTIFICATE");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "nfv");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'nft' (forVehicleType 'trl', forVehicleConfiguration 'null')")
    @Test
    public void testTypeDefaultTestCodeNftTRL() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL);

        testTypeSteps.getTestTypesById("47", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", "Annual NO CERTIFICATE");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "nft");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'nvv' (forVehicleType 'hgv', forVehicleConfiguration 'null')")
    @Test
    public void testTypeDefaultTestCodeNvvHGV() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.HGV)
                .setVehicleConfiguration(VehicleConfiguration.NULL);

        testTypeSteps.getTestTypesById("48", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", "Annual NO CERTIFICATE");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "nvv");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'nvt' (forVehicleType 'trl', forVehicleConfiguration 'null')")
    @Test
    public void testTypeDefaultTestCodeNvtTRL() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL);

        testTypeSteps.getTestTypesById("48", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", "Annual NO CERTIFICATE");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "nvt");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'tiv' (forVehicleType 'hgv', forVehicleConfiguration 'null')")
    @Test
    public void testTypeDefaultTestCodeTivHGV() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.HGV)
                .setVehicleConfiguration(VehicleConfiguration.NULL);

        testTypeSteps.getTestTypesById("49", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", "Annual NO CERTIFICATE");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "tiv");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'tit' (forVehicleType 'trl', forVehicleConfiguration 'null')")
    @Test
    public void testTypeDefaultTestCodeTitTRL() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL);

        testTypeSteps.getTestTypesById("49", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", "Annual NO CERTIFICATE");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "tit");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'ddv' (forVehicleType 'hgv', forVehicleConfiguration 'null')")
    @Test
    public void testTypeDefaultTestCodeDdvHGV() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE))
                .setVehicleType(VehicleType.HGV)
                .setVehicleConfiguration(VehicleConfiguration.NULL);

        testTypeSteps.getTestTypesById("50", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBeNull("testTypeClassification");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "ddv");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'ddt' (forVehicleType 'trl', forVehicleConfiguration 'null')")
    @Test
    public void testTypeDefaultTestCodeDdtTRL() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL);

        testTypeSteps.getTestTypesById("50", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBeNull("testTypeClassification");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "ddt");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'rpv2' (forVehicleType 'hgv', forVehicleConfiguration 'null', vehicleAxles 2)")
    @Test
    public void testTypeDefaultTestCodeRpv2HGVTwoAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.HGV)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.TWO);

        testTypeSteps.getTestTypesById("53", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", "Annual With Certificate");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "rpv2");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'rpv3' (forVehicleType 'hgv', forVehicleConfiguration 'null', vehicleAxles 3)")
    @Test
    public void testTypeDefaultTestCodeRpv3HGVThreeAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.HGV)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.THREE);

        testTypeSteps.getTestTypesById("53", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", "Annual With Certificate");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "rpv3");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'rpv4' (forVehicleType 'hgv', forVehicleConfiguration 'null', vehicleAxles 4)")
    @Test
    public void testTypeDefaultTestCodeRpv4HGVFourAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.HGV)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.FOUR);

        testTypeSteps.getTestTypesById("53", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", "Annual With Certificate");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "rpv4");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'rpv5' (forVehicleType 'hgv', forVehicleConfiguration 'null', vehicleAxles 5)")
    @Test
    public void testTypeDefaultTestCodeRpv5HGVFiveAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.HGV)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.FIVE);

        testTypeSteps.getTestTypesById("53", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", "Annual With Certificate");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "rpv5");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'rpt1' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 1)")
    @Test
    public void testTypeDefaultTestCodeRpt1TRLOneAxle() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.ONE);

        testTypeSteps.getTestTypesById("98", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", "Annual With Certificate");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "rpt1");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'rpt2' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 2)")
    @Test
    public void testTypeDefaultTestCodeRpt2TRLTwoAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.TWO);

        testTypeSteps.getTestTypesById("53", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", "Annual With Certificate");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "rpt2");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'rpt3' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 3)")
    @Test
    public void testTypeDefaultTestCodeRpt3TRLThreeAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.THREE);

        testTypeSteps.getTestTypesById("53", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", "Annual With Certificate");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "rpt3");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'rpt4' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 4)")
    @Test
    public void testTypeDefaultTestCodeRpt4TRLFourAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.FOUR);

        testTypeSteps.getTestTypesById("53", testTypeQueryParam);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", "Annual With Certificate");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "rpt4");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'rpt5' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 5)")
    @Test
    public void testTypeDefaultTestCodeRpt5TRLFiveAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.FIVE);

        testTypeSteps.getTestTypesById("53", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", "Annual With Certificate");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "rpt5");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'rsv2' (forVehicleType 'hgv', forVehicleConfiguration 'null', vehicleAxles 2)")
    @Test
    public void testTypeDefaultTestCodeRsv2HGVTwoAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.HGV)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.TWO);

        testTypeSteps.getTestTypesById("54", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", "Annual With Certificate");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "rsv2");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'rsv3' (forVehicleType 'hgv', forVehicleConfiguration 'null', vehicleAxles 3)")
    @Test
    public void testTypeDefaultTestCodeRsv3HGVThreeAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.HGV)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.THREE);

        testTypeSteps.getTestTypesById("54", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", "Annual With Certificate");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "rsv3");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'rsv4' (forVehicleType 'hgv', forVehicleConfiguration 'null', vehicleAxles 4)")
    @Test
    public void testTypeDefaultTestCodeRsv4HGVFourAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.HGV)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.FOUR);

        testTypeSteps.getTestTypesById("54", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", "Annual With Certificate");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "rsv4");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'rsv5' (forVehicleType 'hgv', forVehicleConfiguration 'null', vehicleAxles 5)")
    @Test
    public void testTypeDefaultTestCodeRsv5HGVFiveAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.HGV)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.FIVE);

        testTypeSteps.getTestTypesById("54", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", "Annual With Certificate");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "rsv5");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'rst1' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 1)")
    @Test
    public void testTypeDefaultTestCodeRst1TRLOneAxle() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.ONE);

        testTypeSteps.getTestTypesById("99", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", "Annual With Certificate");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "rst1");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'rst2' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 2)")
    @Test
    public void testTypeDefaultTestCodeRst2TRLTwoAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.TWO);

        testTypeSteps.getTestTypesById("54", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", "Annual With Certificate");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "rst2");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'rst3' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 3)")
    @Test
    public void testTypeDefaultTestCodeRst3TRLThreeAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.THREE);

        testTypeSteps.getTestTypesById("54", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", "Annual With Certificate");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "rst3");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'rst4' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 4)")
    @Test
    public void testTypeDefaultTestCodeRst4TRLFourAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.FOUR);

        testTypeSteps.getTestTypesById("54", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", "Annual With Certificate");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "rst4");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'rst5' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 5)")
    @Test
    public void testTypeDefaultTestCodeRst5TRLFiveAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.FIVE);

        testTypeSteps.getTestTypesById("54", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", "Annual With Certificate");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "rst5");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'trv' (forVehicleType 'hgv', forVehicleConfiguration 'null')")
    @Test
    public void testTypeDefaultTestCodeTrvHGV() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.HGV)
                .setVehicleConfiguration(VehicleConfiguration.NULL);

        testTypeSteps.getTestTypesById("56", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", "Annual NO CERTIFICATE");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "trv");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'trt' (forVehicleType 'trl', forVehicleConfiguration 'null')")
    @Test
    public void testTypeDefaultTestCodeTrtTRL() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL);

        testTypeSteps.getTestTypesById("56", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", "Annual NO CERTIFICATE");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "trt");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'rft' (forVehicleType 'trl', forVehicleConfiguration 'null')")
    @Test
    public void testTypeDefaultTestCodeRftTRL() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL);

        testTypeSteps.getTestTypesById("57", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", "Annual NO CERTIFICATE");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "rft");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'arv' (forVehicleType 'hgv', forVehicleConfiguration 'null')")
    @Test
    public void testTypeDefaultTestCodeArvHGV() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.HGV)
                .setVehicleConfiguration(VehicleConfiguration.NULL);

        testTypeSteps.getTestTypesById("59", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBeNull("testTypeClassification");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "arv");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'art' (forVehicleType 'trl', forVehicleConfiguration 'null')")
    @Test
    public void testTypeDefaultTestCodeArtTRL() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL);

        testTypeSteps.getTestTypesById("59", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBeNull("testTypeClassification");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "art");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'drv' (forVehicleType 'hgv', forVehicleConfiguration 'null')")
    @Test
    public void testTypeDefaultTestCodeDrvHGV() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.HGV)
                .setVehicleConfiguration(VehicleConfiguration.NULL);

        testTypeSteps.getTestTypesById("60", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBeNull("testTypeClassification");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "drv");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'drt' (forVehicleType 'trl', forVehicleConfiguration 'null')")
    @Test
    public void testTypeDefaultTestCodeDrtTRL() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL);

        testTypeSteps.getTestTypesById("60", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBeNull("testTypeClassification");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "drt");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'qkv' (forVehicleType 'hgv', forVehicleConfiguration 'null', vehicleAxles 2)")
    @Test
    public void testTypeDefaultTestCodeQkvHGV() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.HGV)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.TWO);

        testTypeSteps.getTestTypesById("62", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", "Annual NO CERTIFICATE");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "qkv");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'qkt' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 1)")
    @Test
    public void testTypeDefaultTestCodeQktTRL() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.ONE);

        testTypeSteps.getTestTypesById("101", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBeNull("testTypeClassification");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "qkt");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'qpv' (forVehicleType 'hgv', forVehicleConfiguration 'null', vehicleAxles 3)")
    @Test
    public void testTypeDefaultTestCodeQpvHGV() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.HGV)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.THREE);

        testTypeSteps.getTestTypesById("62", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", "Annual NO CERTIFICATE");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "qpv");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'qpt' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 2)")
    @Test
    public void testTypeDefaultTestCodeQptTRL() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.TWO);

        testTypeSteps.getTestTypesById("62", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", "Annual NO CERTIFICATE");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "qpt");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'qqv' (forVehicleType 'hgv', forVehicleConfiguration 'null', vehicleAxles 4)")
    @Test
    public void testTypeDefaultTestCodeQqvHGV() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.HGV)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.FOUR);

        testTypeSteps.getTestTypesById("62", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", "Annual NO CERTIFICATE");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "qqv");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'qqt' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 3)")
    @Test
    public void testTypeDefaultTestCodeQqtTRL() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.THREE);

        testTypeSteps.getTestTypesById("62", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", "Annual NO CERTIFICATE");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "qqt");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'ruv' (forVehicleType 'hgv', forVehicleConfiguration 'null')")
    @Test
    public void testTypeDefaultTestCodeRuvHGV() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.HGV)
                .setVehicleConfiguration(VehicleConfiguration.NULL);

        testTypeSteps.getTestTypesById("63", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", "Annual NO CERTIFICATE");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "ruv");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'rut' (forVehicleType 'trl', forVehicleConfiguration 'null')")
    @Test
    public void testTypeDefaultTestCodeRutTRL() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL);

        testTypeSteps.getTestTypesById("63", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", "Annual NO CERTIFICATE");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "rut");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'rgv2' (forVehicleType 'hgv', forVehicleConfiguration 'null', vehicleAxles 2)")
    @Test
    public void testTypeDefaultTestCodeRgv2HGVTwoAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.HGV)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.TWO);

        testTypeSteps.getTestTypesById("65", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", "Annual With Certificate");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "rgv2");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'rgv3' (forVehicleType 'hgv', forVehicleConfiguration 'null', vehicleAxles 3)")
    @Test
    public void testTypeDefaultTestCodeRgv3HGVThreeAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.HGV)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.THREE);

        testTypeSteps.getTestTypesById("65", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", "Annual With Certificate");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "rgv3");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'rgv4' (forVehicleType 'hgv', forVehicleConfiguration 'null', vehicleAxles 4)")
    @Test
    public void testTypeDefaultTestCodeRgv4HGVFourAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.HGV)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.FOUR);

        testTypeSteps.getTestTypesById("65", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", "Annual With Certificate");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "rgv4");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'rgv5' (forVehicleType 'hgv', forVehicleConfiguration 'null', vehicleAxles 5)")
    @Test
    public void testTypeDefaultTestCodeRgv5HGVFiveAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.HGV)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.FIVE);

        testTypeSteps.getTestTypesById("65", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", "Annual With Certificate");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "rgv5");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'rgt1' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 1)")
    @Test
    public void testTypeDefaultTestCodeRgt1TRLOneAxle() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.ONE);

        testTypeSteps.getTestTypesById("103", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", "Annual With Certificate");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "rgt1");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'rgt2' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 2)")
    @Test
    public void testTypeDefaultTestCodeRgt2TRLTwoAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.TWO);

        testTypeSteps.getTestTypesById("65", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", "Annual With Certificate");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "rgt2");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'rgt3' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 3)")
    @Test
    public void testTypeDefaultTestCodeRgt3TRLThreeAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.THREE);

        testTypeSteps.getTestTypesById("65", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", "Annual With Certificate");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "rgt3");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'rgt4' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 4)")
    @Test
    public void testTypeDefaultTestCodeRgt4TRLFourAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.FOUR);

        testTypeSteps.getTestTypesById("65", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", "Annual With Certificate");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "rgt4");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'rgt5' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 5)")
    @Test
    public void testTypeDefaultTestCodeRgt5TRLFiveAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.FIVE);

        testTypeSteps.getTestTypesById("65", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", "Annual With Certificate");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "rgt5");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'riv2' (forVehicleType 'hgv', forVehicleConfiguration 'null', vehicleAxles 2)")
    @Test
    public void testTypeDefaultTestCodeRiv2HGVTwoAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.HGV)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.TWO);

        testTypeSteps.getTestTypesById("66", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", "Annual With Certificate");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "riv2");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'riv3' (forVehicleType 'hgv', forVehicleConfiguration 'null', vehicleAxles 3)")
    @Test
    public void testTypeDefaultTestCodeRiv3HGVThreeAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.HGV)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.THREE);

        testTypeSteps.getTestTypesById("66", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", "Annual With Certificate");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "riv3");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'riv4' (forVehicleType 'hgv', forVehicleConfiguration 'null', vehicleAxles 4)")
    @Test
    public void testTypeDefaultTestCodeRiv4HGVFourAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.HGV)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.FOUR);

        testTypeSteps.getTestTypesById("66", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", "Annual With Certificate");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "riv4");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'riv5' (forVehicleType 'hgv', forVehicleConfiguration 'null', vehicleAxles 5)")
    @Test
    public void testTypeDefaultTestCodeRiv5HGVFiveAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.HGV)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.FIVE);

        testTypeSteps.getTestTypesById("66", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", "Annual With Certificate");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "riv5");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'rit1' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 1)")
    @Test
    public void testTypeDefaultTestCodeRit1TRLOneAxle() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.ONE);

        testTypeSteps.getTestTypesById("104", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", "Annual With Certificate");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "rit1");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'rit2' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 2)")
    @Test
    public void testTypeDefaultTestCodeRit2TRLTwoAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.TWO);

        testTypeSteps.getTestTypesById("66", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", "Annual With Certificate");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "rit2");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'rit3' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 3)")
    @Test
    public void testTypeDefaultTestCodeRit3TRLThreeAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.THREE);

        testTypeSteps.getTestTypesById("66", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", "Annual With Certificate");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "rit3");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'rit4' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 4)")
    @Test
    public void testTypeDefaultTestCodeRit4TRLFourAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.FOUR);

        testTypeSteps.getTestTypesById("66", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", "Annual With Certificate");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "rit4");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'rit5' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 5)")
    @Test
    public void testTypeDefaultTestCodeRit5TRLFiveAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.FIVE);

        testTypeSteps.getTestTypesById("66", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", "Annual With Certificate");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "rit5");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'rht' (forVehicleType 'trl', forVehicleConfiguration 'null')")
    @Test
    public void testTypeDefaultTestCodeRhtTRLFiveAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL);

        testTypeSteps.getTestTypesById("67", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", "Annual With Certificate");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "rht");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'p1v2' (forVehicleType 'hgv', forVehicleConfiguration 'null', vehicleAxles 2)")
    @Test
    public void testTypeDefaultTestCodeP1v2HGVTwoAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.HGV)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.TWO);

        testTypeSteps.getTestTypesById("70", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", "Annual With Certificate");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "p1v2");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'p1v3' (forVehicleType 'hgv', forVehicleConfiguration 'null', vehicleAxles 3)")
    @Test
    public void testTypeDefaultTestCodeP1v3HGVThreeAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.HGV)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.THREE);

        testTypeSteps.getTestTypesById("70", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", "Annual With Certificate");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "p1v3");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'p1v4' (forVehicleType 'hgv', forVehicleConfiguration 'null', vehicleAxles 4)")
    @Test
    public void testTypeDefaultTestCodeP1v4HGVFourAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.HGV)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.FOUR);

        testTypeSteps.getTestTypesById("70", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", "Annual With Certificate");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "p1v4");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'p1v5' (forVehicleType 'hgv', forVehicleConfiguration 'null', vehicleAxles 5)")
    @Test
    public void testTypeDefaultTestCodeP1v5HGVFiveAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.HGV)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.FIVE);

        testTypeSteps.getTestTypesById("70", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", "Annual With Certificate");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "p1v5");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'p1t1' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 1)")
    @Test
    public void testTypeDefaultTestCodeP1t1TRLOneAxle() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.ONE);

        testTypeSteps.getTestTypesById("107", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", "Annual With Certificate");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "p1t1");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'p1t2' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 2)")
    @Test
    public void testTypeDefaultTestCodeP1t2TRLTwoAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.TWO);

        testTypeSteps.getTestTypesById("70", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", "Annual With Certificate");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "p1t2");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'p1t3' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 3)")
    @Test
    public void testTypeDefaultTestCodeP1t3TRLThreeAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.THREE);

        testTypeSteps.getTestTypesById("70", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", "Annual With Certificate");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "p1t3");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'p1t4' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 4)")
    @Test
    public void testTypeDefaultTestCodeP1t4TRLFourAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.FOUR);

        testTypeSteps.getTestTypesById("70", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", "Annual With Certificate");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "p1t4");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'p1t5' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 5)")
    @Test
    public void testTypeDefaultTestCodeP1t5TRLFiveAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.FIVE);

        testTypeSteps.getTestTypesById("70", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", "Annual With Certificate");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "p1t5");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'pbv2' (forVehicleType 'hgv', forVehicleConfiguration 'null', vehicleAxles 2)")
    @Test
    public void testTypeDefaultTestCodePbv2HGVTwoAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.HGV)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.TWO);

        testTypeSteps.getTestTypesById("71", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", "Annual NO CERTIFICATE");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "pbv2");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'pbv3' (forVehicleType 'hgv', forVehicleConfiguration 'null', vehicleAxles 3)")
    @Test
    public void testTypeDefaultTestCodePbv3HGVThreeAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.HGV)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.THREE);

        testTypeSteps.getTestTypesById("71", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", "Annual NO CERTIFICATE");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "pbv3");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'pbv4' (forVehicleType 'hgv', forVehicleConfiguration 'null', vehicleAxles 4)")
    @Test
    public void testTypeDefaultTestCodePbv4HGVFourAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.HGV)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.FOUR);

        testTypeSteps.getTestTypesById("71", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", "Annual NO CERTIFICATE");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "pbv4");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'pbv5' (forVehicleType 'hgv', forVehicleConfiguration 'null', vehicleAxles 5)")
    @Test
    public void testTypeDefaultTestCodePbv5HGVFiveAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.HGV)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.FIVE);

        testTypeSteps.getTestTypesById("71", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", "Annual NO CERTIFICATE");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "pbv5");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'pbt1' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 1)")
    @Test
    public void testTypeDefaultTestCodePbt1TRLOneAxle() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.ONE);

        testTypeSteps.getTestTypesById("108", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", "Annual NO CERTIFICATE");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "pbt1");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'pbt2' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 2)")
    @Test
    public void testTypeDefaultTestCodePbt2TRLTwoAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.TWO);

        testTypeSteps.getTestTypesById("71", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", "Annual NO CERTIFICATE");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "pbt2");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'pbt3' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 3)")
    @Test
    public void testTypeDefaultTestCodePbt3TRLThreeAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.THREE);

        testTypeSteps.getTestTypesById("71", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", "Annual NO CERTIFICATE");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "pbt3");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'pbt4' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 4)")
    @Test
    public void testTypeDefaultTestCodePbt4TRLFourAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.FOUR);

        testTypeSteps.getTestTypesById("71", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", "Annual NO CERTIFICATE");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "pbt4");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'pbt5' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 5)")
    @Test
    public void testTypeDefaultTestCodePbt5TRLFiveAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.FIVE);

        testTypeSteps.getTestTypesById("71", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", "Annual NO CERTIFICATE");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "pbt5");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'pov2' (forVehicleType 'hgv', forVehicleConfiguration 'null', vehicleAxles 2)")
    @Test
    public void testTypeDefaultTestCodePov2HGVTwoAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.HGV)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.TWO);

        testTypeSteps.getTestTypesById("72", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", "Annual NO CERTIFICATE");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "pov2");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'pov3' (forVehicleType 'hgv', forVehicleConfiguration 'null', vehicleAxles 3)")
    @Test
    public void testTypeDefaultTestCodePov3HGVThreeAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.HGV)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.THREE);

        testTypeSteps.getTestTypesById("72", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", "Annual NO CERTIFICATE");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "pov3");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'pov4' (forVehicleType 'hgv', forVehicleConfiguration 'null', vehicleAxles 4)")
    @Test
    public void testTypeDefaultTestCodePov4HGVFourAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.HGV)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.FOUR);

        testTypeSteps.getTestTypesById("72", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", "Annual NO CERTIFICATE");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "pov4");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'pov5' (forVehicleType 'hgv', forVehicleConfiguration 'null', vehicleAxles 5)")
    @Test
    public void testTypeDefaultTestCodePov5HGVFiveAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.HGV)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.FIVE);

        testTypeSteps.getTestTypesById("72", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", "Annual NO CERTIFICATE");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "pov5");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'pot1' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 1)")
    @Test
    public void testTypeDefaultTestCodePot1TRLOneAxle() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.ONE);

        testTypeSteps.getTestTypesById("109", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", "Annual NO CERTIFICATE");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "pot1");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'pot2' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 2)")
    @Test
    public void testTypeDefaultTestCodePot2TRLTwoAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.TWO);

        testTypeSteps.getTestTypesById("72", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", "Annual NO CERTIFICATE");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "pot2");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'pot3' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 3)")
    @Test
    public void testTypeDefaultTestCodePot3TRLThreeAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.THREE);

        testTypeSteps.getTestTypesById("72", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", "Annual NO CERTIFICATE");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "pot3");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'pot4' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 4)")
    @Test
    public void testTypeDefaultTestCodePot4TRLFourAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.FOUR);

        testTypeSteps.getTestTypesById("72", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", "Annual NO CERTIFICATE");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "pot4");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'pot5' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 5)")
    @Test
    public void testTypeDefaultTestCodePot5TRLFiveAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.FIVE);

        testTypeSteps.getTestTypesById("72", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", "Annual NO CERTIFICATE");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "pot5");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'pav2' (forVehicleType 'hgv', forVehicleConfiguration 'null', vehicleAxles 2)")
    @Test
    public void testTypeDefaultTestCodePav2HGVTwoAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.HGV)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.TWO);

        testTypeSteps.getTestTypesById("73", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", "Annual NO CERTIFICATE");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "pav2");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'pav3' (forVehicleType 'hgv', forVehicleConfiguration 'null', vehicleAxles 3)")
    @Test
    public void testTypeDefaultTestCodePav3HGVThreeAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.HGV)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.THREE);

        testTypeSteps.getTestTypesById("73", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", "Annual NO CERTIFICATE");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "pav3");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'pav4' (forVehicleType 'hgv', forVehicleConfiguration 'null', vehicleAxles 4)")
    @Test
    public void testTypeDefaultTestCodePav4HGVFourAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.HGV)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.FOUR);

        testTypeSteps.getTestTypesById("73", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", "Annual NO CERTIFICATE");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "pav4");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'pav5' (forVehicleType 'hgv', forVehicleConfiguration 'null', vehicleAxles 5)")
    @Test
    public void testTypeDefaultTestCodePav5HGVFiveAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.HGV)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.FIVE);

        testTypeSteps.getTestTypesById("73", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", "Annual NO CERTIFICATE");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "pav5");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'pat1' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 1)")
    @Test
    public void testTypeDefaultTestCodePat1TRLOneAxle() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.ONE);

        testTypeSteps.getTestTypesById("110", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", "Annual NO CERTIFICATE");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "pat1");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'pat2' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 2)")
    @Test
    public void testTypeDefaultTestCodePat2TRLTwoAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.TWO);

        testTypeSteps.getTestTypesById("73", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", "Annual NO CERTIFICATE");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "pat2");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'pat3' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 3)")
    @Test
    public void testTypeDefaultTestCodePat3TRLThreeAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.THREE);

        testTypeSteps.getTestTypesById("73", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", "Annual NO CERTIFICATE");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "pat3");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'pat4' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 4)")
    @Test
    public void testTypeDefaultTestCodePat4TRLFourAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.FOUR);

        testTypeSteps.getTestTypesById("73", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", "Annual NO CERTIFICATE");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "pat4");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'pat5' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 5)")
    @Test
    public void testTypeDefaultTestCodePat5TRLFiveAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.FIVE);

        testTypeSteps.getTestTypesById("73", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", "Annual NO CERTIFICATE");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "pat5");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'p3v2' (forVehicleType 'hgv', forVehicleConfiguration 'null', vehicleAxles 2)")
    @Test
    public void testTypeDefaultTestCodeP3v2HGVTwoAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.HGV)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.TWO);

        testTypeSteps.getTestTypesById("76", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", "Annual With Certificate");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "p3v2");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'p3v3' (forVehicleType 'hgv', forVehicleConfiguration 'null', vehicleAxles 3)")
    @Test
    public void testTypeDefaultTestCodeP3v3HGVThreeAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.HGV)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.THREE);

        testTypeSteps.getTestTypesById("76", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", "Annual With Certificate");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "p3v3");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'p3v4' (forVehicleType 'hgv', forVehicleConfiguration 'null', vehicleAxles 4)")
    @Test
    public void testTypeDefaultTestCodeP3v4HGVFourAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.HGV)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.FOUR);

        testTypeSteps.getTestTypesById("76", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", "Annual With Certificate");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "p3v4");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'p3v5' (forVehicleType 'hgv', forVehicleConfiguration 'null', vehicleAxles 5)")
    @Test
    public void testTypeDefaultTestCodeP3v5HGVFiveAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.HGV)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.FIVE);

        testTypeSteps.getTestTypesById("76", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", "Annual With Certificate");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "p3v5");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'p3t1' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 1)")
    @Test
    public void testTypeDefaultTestCodeP3t1TRLOneAxle() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.ONE);

        testTypeSteps.getTestTypesById("113", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", "Annual With Certificate");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "p3t1");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'p3t2' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 2)")
    @Test
    public void testTypeDefaultTestCodeP3t2TRLTwoAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.TWO);

        testTypeSteps.getTestTypesById("76", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", "Annual With Certificate");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "p3t2");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'p3t3' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 3)")
    @Test
    public void testTypeDefaultTestCodeP3t3TRLThreeAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.THREE);

        testTypeSteps.getTestTypesById("76", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", "Annual With Certificate");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "p3t3");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'p3t4' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 4)")
    @Test
    public void testTypeDefaultTestCodeP3t4TRLFourAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.FOUR);

        testTypeSteps.getTestTypesById("76", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", "Annual With Certificate");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "p3t4");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'p3t5' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 5)")
    @Test
    public void testTypeDefaultTestCodeP3t5TRLFiveAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.FIVE);

        testTypeSteps.getTestTypesById("76", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", "Annual With Certificate");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "p3t5");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'pfv2' (forVehicleType 'hgv', forVehicleConfiguration 'null', vehicleAxles 2)")
    @Test
    public void testTypeDefaultTestCodePfv2HGVTwoAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.HGV)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.TWO);

        testTypeSteps.getTestTypesById("77", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", "Annual NO CERTIFICATE");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "pfv2");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'pfv3' (forVehicleType 'hgv', forVehicleConfiguration 'null', vehicleAxles 3)")
    @Test
    public void testTypeDefaultTestCodePfv3HGVThreeAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.HGV)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.THREE);

        testTypeSteps.getTestTypesById("77", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", "Annual NO CERTIFICATE");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "pfv3");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'pfv4' (forVehicleType 'hgv', forVehicleConfiguration 'null', vehicleAxles 4)")
    @Test
    public void testTypeDefaultTestCodePfv4HGVFourAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.HGV)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.FOUR);

        testTypeSteps.getTestTypesById("77", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", "Annual NO CERTIFICATE");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "pfv4");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'pfv5' (forVehicleType 'hgv', forVehicleConfiguration 'null', vehicleAxles 5)")
    @Test
    public void testTypeDefaultTestCodePfv5HGVFiveAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.HGV)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.FIVE);

        testTypeSteps.getTestTypesById("77", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", "Annual NO CERTIFICATE");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "pfv5");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'pft1' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 1)")
    @Test
    public void testTypeDefaultTestCodePft1TRLOneAxle() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.ONE);

        testTypeSteps.getTestTypesById("114", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", "Annual NO CERTIFICATE");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "pft1");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'pft2' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 2)")
    @Test
    public void testTypeDefaultTestCodePft2TRLTwoAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.TWO);

        testTypeSteps.getTestTypesById("77", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", "Annual NO CERTIFICATE");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "pft2");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'pft3' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 3)")
    @Test
    public void testTypeDefaultTestCodePft3TRLThreeAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.THREE);

        testTypeSteps.getTestTypesById("77", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", "Annual NO CERTIFICATE");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "pft3");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'pft4' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 4)")
    @Test
    public void testTypeDefaultTestCodePft4TRLFourAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.FOUR);

        testTypeSteps.getTestTypesById("77", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", "Annual NO CERTIFICATE");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "pft4");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'pft5' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 5)")
    @Test
    public void testTypeDefaultTestCodePft5TRLFiveAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.FIVE);

        testTypeSteps.getTestTypesById("77", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", "Annual NO CERTIFICATE");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "pft5");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'p6v2' (forVehicleType 'hgv', forVehicleConfiguration 'null', vehicleAxles 2)")
    @Test
    public void testTypeDefaultTestCodeP6v2HGVTwoAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.HGV)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.TWO);

        testTypeSteps.getTestTypesById("79", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", "Annual With Certificate");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "p6v2");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'p6v3' (forVehicleType 'hgv', forVehicleConfiguration 'null', vehicleAxles 3)")
    @Test
    public void testTypeDefaultTestCodeP6v3HGVThreeAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.HGV)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.THREE);

        testTypeSteps.getTestTypesById("79", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", "Annual With Certificate");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "p6v3");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'p6v4' (forVehicleType 'hgv', forVehicleConfiguration 'null', vehicleAxles 4)")
    @Test
    public void testTypeDefaultTestCodeP6v4HGVFourAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.HGV)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.FOUR);

        testTypeSteps.getTestTypesById("79", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", "Annual With Certificate");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "p6v4");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'p6v5' (forVehicleType 'hgv', forVehicleConfiguration 'null', vehicleAxles 5)")
    @Test
    public void testTypeDefaultTestCodeP6v5HGVFiveAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.HGV)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.FIVE);

        testTypeSteps.getTestTypesById("79", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", "Annual With Certificate");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "p6v5");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'p6t1' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 1)")
    @Test
    public void testTypeDefaultTestCodeP6t1TRLOneAxle() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.ONE);

        testTypeSteps.getTestTypesById("116", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", "Annual With Certificate");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "p6t1");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'p6t2' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 2)")
    @Test
    public void testTypeDefaultTestCodeP6t2TRLTwoAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.TWO);

        testTypeSteps.getTestTypesById("79", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", "Annual With Certificate");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "p6t2");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'p6t3' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 3)")
    @Test
    public void testTypeDefaultTestCodeP6t3TRLThreeAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.THREE);

        testTypeSteps.getTestTypesById("79", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", "Annual With Certificate");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "p6t3");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'p6t4' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 4)")
    @Test
    public void testTypeDefaultTestCodeP6t4TRLFourAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.FOUR);

        testTypeSteps.getTestTypesById("79", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", "Annual With Certificate");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "p6t4");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'p6t5' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 5)")
    @Test
    public void testTypeDefaultTestCodeP6t5TRLFiveAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.FIVE);

        testTypeSteps.getTestTypesById("79", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", "Annual With Certificate");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "p6t5");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'p2v2' (forVehicleType 'hgv', forVehicleConfiguration 'null', vehicleAxles 2)")
    @Test
    public void testTypeDefaultTestCodeP2v2HGVTwoAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.HGV)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.TWO);

        testTypeSteps.getTestTypesById("80", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", "Annual NO CERTIFICATE");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "p2v2");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'p2v3' (forVehicleType 'hgv', forVehicleConfiguration 'null', vehicleAxles 3)")
    @Test
    public void testTypeDefaultTestCodeP2v3HGVThreeAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.HGV)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.THREE);

        testTypeSteps.getTestTypesById("80", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", "Annual NO CERTIFICATE");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "p2v3");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'p2v4' (forVehicleType 'hgv', forVehicleConfiguration 'null', vehicleAxles 4)")
    @Test
    public void testTypeDefaultTestCodeP2v4HGVFourAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.HGV)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.FOUR);

        testTypeSteps.getTestTypesById("80", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", "Annual NO CERTIFICATE");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "p2v4");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'p2v5' (forVehicleType 'hgv', forVehicleConfiguration 'null', vehicleAxles 5)")
    @Test
    public void testTypeDefaultTestCodeP2v5HGVFiveAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.HGV)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.FIVE);

        testTypeSteps.getTestTypesById("80", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", "Annual NO CERTIFICATE");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "p2v5");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'p2t1' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 1)")
    @Test
    public void testTypeDefaultTestCodeP2t1TRLOneAxle() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.ONE);

        testTypeSteps.getTestTypesById("117", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", "Annual NO CERTIFICATE");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "p2t1");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'p2t2' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 2)")
    @Test
    public void testTypeDefaultTestCodeP2t2TRLTwoAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.TWO);

        testTypeSteps.getTestTypesById("80", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", "Annual NO CERTIFICATE");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "p2t2");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'p2t3' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 3)")
    @Test
    public void testTypeDefaultTestCodeP2t3TRLThreeAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.THREE);

        testTypeSteps.getTestTypesById("80", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", "Annual NO CERTIFICATE");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "p2t3");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'p2t4' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 4)")
    @Test
    public void testTypeDefaultTestCodeP2t4TRLFourAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.FOUR);

        testTypeSteps.getTestTypesById("80", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", "Annual NO CERTIFICATE");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "p2t4");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'p2t5' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 5)")
    @Test
    public void testTypeDefaultTestCodeP2t5TRLFiveAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.FIVE);

        testTypeSteps.getTestTypesById("80", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", "Annual NO CERTIFICATE");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "p2t5");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'p7v2' (forVehicleType 'hgv', forVehicleConfiguration 'null', vehicleAxles 2)")
    @Test
    public void testTypeDefaultTestCodeP7v2HGVTwoAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.HGV)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.TWO);

        testTypeSteps.getTestTypesById("82", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", "Annual With Certificate");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "p7v2");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'p7v3' (forVehicleType 'hgv', forVehicleConfiguration 'null', vehicleAxles 3)")
    @Test
    public void testTypeDefaultTestCodeP7v3HGVThreeAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.HGV)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.THREE);

        testTypeSteps.getTestTypesById("82", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", "Annual With Certificate");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "p7v3");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'p7v4' (forVehicleType 'hgv', forVehicleConfiguration 'null', vehicleAxles 4)")
    @Test
    public void testTypeDefaultTestCodeP7v4HGVFourAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.HGV)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.FOUR);

        testTypeSteps.getTestTypesById("82", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", "Annual With Certificate");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "p7v4");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'p7v5' (forVehicleType 'hgv', forVehicleConfiguration 'null', vehicleAxles 5)")
    @Test
    public void testTypeDefaultTestCodeP7v5HGVFiveAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.HGV)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.FIVE);

        testTypeSteps.getTestTypesById("82", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", "Annual With Certificate");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "p7v5");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'p7t1' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 1)")
    @Test
    public void testTypeDefaultTestCodeP7t1TRLOneAxle() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.ONE);

        testTypeSteps.getTestTypesById("119", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", "Annual With Certificate");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "p7t1");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'p7t2' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 2)")
    @Test
    public void testTypeDefaultTestCodeP7t2TRLTwoAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.TWO);

        testTypeSteps.getTestTypesById("82", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", "Annual With Certificate");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "p7t2");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'p7t3' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 3)")
    @Test
    public void testTypeDefaultTestCodeP7t3TRLThreeAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.THREE);

        testTypeSteps.getTestTypesById("82", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", "Annual With Certificate");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "p7t3");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'p7t4' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 4)")
    @Test
    public void testTypeDefaultTestCodeP7t4TRLFourAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.FOUR);

        testTypeSteps.getTestTypesById("82", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", "Annual With Certificate");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "p7t4");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'p7t5' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 5)")
    @Test
    public void testTypeDefaultTestCodeP7t5TRLFiveAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.FIVE);

        testTypeSteps.getTestTypesById("82", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", "Annual With Certificate");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "p7t5");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'p4v2' (forVehicleType 'hgv', forVehicleConfiguration 'null', vehicleAxles 2)")
    @Test
    public void testTypeDefaultTestCodeP4v2HGVTwoAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.HGV)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.TWO);

        testTypeSteps.getTestTypesById("83", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", "Annual With Certificate");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "p4v2");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'p4v3' (forVehicleType 'hgv', forVehicleConfiguration 'null', vehicleAxles 3)")
    @Test
    public void testTypeDefaultTestCodeP4v3HGVThreeAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.HGV)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.THREE);

        testTypeSteps.getTestTypesById("83", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", "Annual With Certificate");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "p4v3");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'p4v4' (forVehicleType 'hgv', forVehicleConfiguration 'null', vehicleAxles 4)")
    @Test
    public void testTypeDefaultTestCodeP4v4HGVFourAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.HGV)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.FOUR);

        testTypeSteps.getTestTypesById("83", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", "Annual With Certificate");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "p4v4");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'p4v5' (forVehicleType 'hgv', forVehicleConfiguration 'null', vehicleAxles 5)")
    @Test
    public void testTypeDefaultTestCodeP4v5HGVFiveAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.HGV)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.FIVE);

        testTypeSteps.getTestTypesById("83", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", "Annual With Certificate");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "p4v5");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'p4t1' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 1)")
    @Test
    public void testTypeDefaultTestCodeP4t1TRLOneAxle() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.ONE);

        testTypeSteps.getTestTypesById("120", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", "Annual With Certificate");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "p4t1");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'p4t2' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 2)")
    @Test
    public void testTypeDefaultTestCodeP4t2TRLTwoAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.TWO);

        testTypeSteps.getTestTypesById("83", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", "Annual With Certificate");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "p4t2");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'p4t3' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 3)")
    @Test
    public void testTypeDefaultTestCodeP4t3TRLThreeAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.THREE);

        testTypeSteps.getTestTypesById("83", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", "Annual With Certificate");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "p4t3");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'p4t4' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 4)")
    @Test
    public void testTypeDefaultTestCodeP4t4TRLFourAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.FOUR);

        testTypeSteps.getTestTypesById("83", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", "Annual With Certificate");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "p4t4");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'p4t5' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 5)")
    @Test
    public void testTypeDefaultTestCodeP4t5TRLFiveAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.FIVE);

        testTypeSteps.getTestTypesById("83", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", "Annual With Certificate");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "p4t5");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'bib' (forVehicleType 'hgv', forVehicleConfiguration 'null', vehicleAxles 1)")
    @Test
    public void testTypeDefaultTestCodeBibHGVOneAxle() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.HGV)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.ONE);

        testTypeSteps.getTestTypesById("85", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", "NON ANNUAL");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "bib");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'qav2' (forVehicleType 'hgv', forVehicleConfiguration 'null', vehicleAxles 2)")
    @Test
    public void testTypeDefaultTestCodeQav2HGVTwoAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.HGV)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.TWO);

        testTypeSteps.getTestTypesById("85", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", "NON ANNUAL");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "qav2");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'qav3' (forVehicleType 'hgv', forVehicleConfiguration 'null', vehicleAxles 3)")
    @Test
    public void testTypeDefaultTestCodeQav3HGVThreeAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.HGV)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.THREE);

        testTypeSteps.getTestTypesById("85", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", "NON ANNUAL");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "qav3");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'qav4' (forVehicleType 'hgv', forVehicleConfiguration 'null', vehicleAxles 4)")
    @Test
    public void testTypeDefaultTestCodeQav4HGVFourAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.HGV)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.FOUR);

        testTypeSteps.getTestTypesById("85", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", "NON ANNUAL");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "qav4");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'qav5' (forVehicleType 'hgv', forVehicleConfiguration 'null', vehicleAxles 5)")
    @Test
    public void testTypeDefaultTestCodeQav5HGVFiveAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.HGV)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.FIVE);

        testTypeSteps.getTestTypesById("85", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", "NON ANNUAL");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "qav5");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'qat1' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 1)")
    @Test
    public void testTypeDefaultTestCodeQat1TRLOneAxle() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.ONE);

        testTypeSteps.getTestTypesById("85", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", "NON ANNUAL");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "qat1");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'qat2' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 2)")
    @Test
    public void testTypeDefaultTestCodeQat2TRLTwoAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.TWO);

        testTypeSteps.getTestTypesById("85", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", "NON ANNUAL");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "qat2");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'qat3' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 3)")
    @Test
    public void testTypeDefaultTestCodeQat3TRLThreeAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.THREE);

        testTypeSteps.getTestTypesById("85", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", "NON ANNUAL");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "qat3");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'qat4' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 4)")
    @Test
    public void testTypeDefaultTestCodeQat4TRLFourAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.FOUR);

        testTypeSteps.getTestTypesById("85", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", "NON ANNUAL");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "qat4");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'qat5' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 5)")
    @Test
    public void testTypeDefaultTestCodeQat5TRLFiveAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.FIVE);

        testTypeSteps.getTestTypesById("85", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", "NON ANNUAL");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "qat5");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'qbv' (forVehicleType 'hgv', forVehicleConfiguration 'null')")
    @Test
    public void testTypeDefaultTestCodeQbvHGV() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.HGV)
                .setVehicleConfiguration(VehicleConfiguration.NULL);

        testTypeSteps.getTestTypesById("86", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", "NON ANNUAL");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "qbv");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'bid' (forVehicleType 'hgv', forVehicleConfiguration 'null')")
    @Test
    public void testTypeDefaultTestCodeBidHGV() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.HGV)
                .setVehicleConfiguration(VehicleConfiguration.NULL);

        testTypeSteps.getTestTypesById("87", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", "NON ANNUAL");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "bid");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'bid' (forVehicleType 'trl', forVehicleConfiguration 'null')")
    @Test
    public void testTypeDefaultTestCodeBidTRL() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL);

        testTypeSteps.getTestTypesById("87", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", "NON ANNUAL");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "bid");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'bif' (forVehicleType 'hgv', forVehicleConfiguration 'null')")
    @Test
    public void testTypeDefaultTestCodeBifHGV() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.HGV)
                .setVehicleConfiguration(VehicleConfiguration.NULL);

        testTypeSteps.getTestTypesById("88", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", "NON ANNUAL");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "bif");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'bid' (forVehicleType 'qcv', forVehicleConfiguration 'null')")
    @Test
    public void testTypeDefaultTestCodeQcvHGV() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.HGV)
                .setVehicleConfiguration(VehicleConfiguration.NULL);

        testTypeSteps.getTestTypesById("89", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", "NON ANNUAL");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "qcv");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'qdv' (forVehicleType 'hgv', forVehicleConfiguration 'null')")
    @Test
    public void testTypeDefaultTestCodeQdvHGV() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.HGV)
                .setVehicleConfiguration(VehicleConfiguration.NULL);

        testTypeSteps.getTestTypesById("90", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", "NON ANNUAL");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "qdv");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'qjv2' (forVehicleType 'hgv', forVehicleConfiguration 'null', vehicleAxles 2)")
    @Test
    public void testTypeDefaultTestCodeQjv2HGVTwoAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.HGV)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.TWO);

        testTypeSteps.getTestTypesById("91", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", "NON ANNUAL");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "qjv2");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'qjv3' (forVehicleType 'hgv', forVehicleConfiguration 'null', vehicleAxles 3)")
    @Test
    public void testTypeDefaultTestCodeQjv3HGVThreeAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.HGV)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.THREE);

        testTypeSteps.getTestTypesById("91", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", "NON ANNUAL");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "qjv3");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'qjv4' (forVehicleType 'hgv', forVehicleConfiguration 'null', vehicleAxles 4)")
    @Test
    public void testTypeDefaultTestCodeQjv4HGVFourAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.HGV)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.FOUR);

        testTypeSteps.getTestTypesById("91", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", "NON ANNUAL");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "qjv4");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'qjv5' (forVehicleType 'hgv', forVehicleConfiguration 'null', vehicleAxles 5)")
    @Test
    public void testTypeDefaultTestCodeQjv5HGVFiveAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.HGV)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.FIVE);

        testTypeSteps.getTestTypesById("91", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", "NON ANNUAL");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "qjv5");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'qjt1' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 1)")
    @Test
    public void testTypeDefaultTestCodeQjt1TRLOneAxle() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.ONE);

        testTypeSteps.getTestTypesById("122", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", "NON ANNUAL");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "qjt1");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'qjt2' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 2)")
    @Test
    public void testTypeDefaultTestCodeQjt2TRLTwoAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.TWO);

        testTypeSteps.getTestTypesById("91", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", "NON ANNUAL");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "qjt2");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'qjt3' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 3)")
    @Test
    public void testTypeDefaultTestCodeQjt3TRLThreeAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.THREE);

        testTypeSteps.getTestTypesById("91", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", "NON ANNUAL");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "qjt3");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'qjt4' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 4)")
    @Test
    public void testTypeDefaultTestCodeQjt4TRLFourAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.FOUR);

        testTypeSteps.getTestTypesById("91", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", "NON ANNUAL");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "qjt4");
    }

    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'qjt5' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 5)")
    @Test
    public void testTypeDefaultTestCodeQjt5TRLFiveAxles() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE, TestTypeField.TEST_TYPE_CLASSIFICATION))
                .setVehicleType(VehicleType.TRL)
                .setVehicleConfiguration(VehicleConfiguration.NULL)
                .setVehicleAxles(VehicleAxles.FIVE);

        testTypeSteps.getTestTypesById("91", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(200);
        testTypeSteps.valueForFieldInPathShouldBe("testTypeClassification", "NON ANNUAL");
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "qjt5");
    }
}
