package testtypes;

import clients.model.*;
import data.TestTypeByIdData;
import model.testtypeid.TestTypeById;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import steps.TestTypeSteps;

import java.util.Arrays;


@RunWith(SerenityRunner.class)
public class GetTestTypesByIdNoData {

    @Steps
    TestTypeSteps testTypeSteps;

    private TestTypeById notExistingAnnualCertificatePsvSmallArticulated = TestTypeByIdData.buildNotExistingAnnualCertificatePsvSmallArticulated();
    private TestTypeById notExistingAnnualCertificatePsvLargeArticulated = TestTypeByIdData.buildNotExistingAnnualCertificatePsvLargeArticulated();


    @Title("CVSB-1073 / CVSB-2427 - Test Type Id not existing")
    @Test
    public void testNotExistingId() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.TEST_TYPE_CLASSIFICATION, TestTypeField.DEFAULT_TEST_CODE, TestTypeField.LINKED_TEST_CODE))
                .setVehicleType(VehicleType.PSV)
                .setVehicleSize(VehicleSize.SMALL)
                .setVehicleConfiguration(VehicleConfiguration.RIGID);

        testTypeSteps.getTestTypesById(String.valueOf(RandomUtils.nextInt(1500, 2000)), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(404);
        testTypeSteps.validateData("No resources match the search criteria.");
    }


    @Title("CVSB-1073 / CVSB-2428 - Search for not existing Test Codes (forVehicleType 'psv', forVehicleSize 'small', forVehicleConfiguration 'rigid') for existing Test Type Classification")
    @Test
    public void testNotExistingAnnualCertificatePsvSmallArticulated() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.TEST_TYPE_CLASSIFICATION, TestTypeField.DEFAULT_TEST_CODE, TestTypeField.LINKED_TEST_CODE))
                .setVehicleType(VehicleType.PSV)
                .setVehicleSize(VehicleSize.SMALL)
                .setVehicleConfiguration(VehicleConfiguration.ARTICULATED);

        testTypeSteps.getTestTypesById(notExistingAnnualCertificatePsvSmallArticulated.getId(), testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(404);
        testTypeSteps.validateData("No resources match the search criteria.");
    }

    @Title("CVSB-1073 / CVSB-2429 - Search for not existing Test Codes (forVehicleType 'psv', forVehicleSize 'large', forVehicleConfiguration 'articulated') for existing Test Type Classification")
    @Test
    public void testNotExistingAnnualCertificatePsvLargeArticulated() {

        TestTypeQueryParam testTypeQueryParam = new TestTypeQueryParam()
                .setFields(Arrays.asList(TestTypeField.TEST_TYPE_CLASSIFICATION, TestTypeField.DEFAULT_TEST_CODE, TestTypeField.LINKED_TEST_CODE))
                .setVehicleType(VehicleType.PSV)
                .setVehicleSize(VehicleSize.LARGE)
                .setVehicleConfiguration(VehicleConfiguration.ARTICULATED);

        //testTypeSteps.getTestTypesById(notExistingAnnualCertificatePsvLargeArticulated.getId(), testTypeQueryParam);
        testTypeSteps.getTestTypesById("94", testTypeQueryParam);
        testTypeSteps.statusCodeShouldBe(404);
        testTypeSteps.validateData("No resources match the search criteria.");
    }


}
