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
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "aat1");
    }

//    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'aat2' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 2)")
//    @Test
//    public void testTypeDefaultTestCodeTRLTwoAxles() {
//
//        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
//                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE))
//                .setVehicleType(VehicleType.TRL)
//                .setVehicleConfiguration(VehicleConfiguration.NULL)
//                .setVehicleAxles(VehicleAxles.TWO);
//
//        testTypeSteps.getTestTypesById("40", testTypeQueryParam);
//        testTypeSteps.statusCodeShouldBe(200);
//        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "aat2");
//    }
//
//    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'aat3' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 3)")
//    @Test
//    public void testTypeDefaultTestCodeTRLThreeAxles() {
//
//        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
//                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE))
//                .setVehicleType(VehicleType.TRL)
//                .setVehicleConfiguration(VehicleConfiguration.NULL)
//                .setVehicleAxles(VehicleAxles.THREE);
//
//        testTypeSteps.getTestTypesById("40", testTypeQueryParam);
//        testTypeSteps.statusCodeShouldBe(200);
//        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "aat3");
//    }
//
//    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'aat4' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 4)")
//    @Test
//    public void testTypeDefaultTestCodeTRLFourAxles() {
//
//        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
//                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE))
//                .setVehicleType(VehicleType.TRL)
//                .setVehicleConfiguration(VehicleConfiguration.NULL)
//                .setVehicleAxles(VehicleAxles.FOUR);
//
//        testTypeSteps.getTestTypesById("40", testTypeQueryParam);
//        testTypeSteps.statusCodeShouldBe(200);
//        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "aat4");
//    }
//
//    @Title("CVSB-4817 / CVSB-7188 - API Consumer retrieves data default test code 'aat5' (forVehicleType 'trl', forVehicleConfiguration 'null', vehicleAxles 5)")
//    @Test
//    public void testTypeDefaultTestCodeTRLFiveAxles() {
//
//        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
//                .setFields(Arrays.asList(TestTypeField.DEFAULT_TEST_CODE))
//                .setVehicleType(VehicleType.TRL)
//                .setVehicleConfiguration(VehicleConfiguration.NULL)
//                .setVehicleAxles(VehicleAxles.FIVE);
//
//        testTypeSteps.getTestTypesById("40", testTypeQueryParam);
//        testTypeSteps.statusCodeShouldBe(200);
//        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "aat5");
//    }

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
        testTypeSteps.valueForFieldInPathShouldBe("defaultTestCode", "ffv5");
    }

}
