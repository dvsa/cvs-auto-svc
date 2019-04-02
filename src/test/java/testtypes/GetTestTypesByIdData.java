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
                @WithTag(type = "TestTypes", name = "Positive"),
                @WithTag(type = "Service", name = "Local"),

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
        testTypeSteps.statusCodeShouldBe(400);
        testTypeSteps.validateData(nonAnnualPsvSmallRigidTwoAxles, testTypeQueryParam.getFields());
    }
}
