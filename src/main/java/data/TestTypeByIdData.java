package data;

import model.TestTypeById;

public class TestTypeByIdData {

    public static TestTypeById buildAnnualCertificatePsvSmallRigid() {

        TestTypeById testTypeById = new TestTypeById();

        testTypeById.setId("10")
                .setTestTypeClassification("Annual With Certificate")
                .setDefaultTestCode("rss")
                .setLinkedTestCode(null);

        return testTypeById;
    }


    public static TestTypeById buildAnnualCertificatePsvLargeArticulated() {

        TestTypeById testTypeById = new TestTypeById();

        testTypeById.setId("10")
                .setTestTypeClassification("Annual With Certificate")
                .setDefaultTestCode("rgl")
                .setLinkedTestCode(null);

        return testTypeById;
    }


    public static TestTypeById buildAnnualCertificatePsvLargeRigid() {

        TestTypeById testTypeById = new TestTypeById();

        testTypeById.setId("10")
                .setTestTypeClassification("Annual With Certificate")
                .setDefaultTestCode("rsl")
                .setLinkedTestCode(null);

        return testTypeById;
    }

    public static TestTypeById buildNotExistingAnnualCertificatePsvSmallArticulated() {

        TestTypeById testTypeById = new TestTypeById();

        testTypeById.setId("10")
                .setTestTypeClassification("Annual With Certificate");

        return testTypeById;
    }

    public static TestTypeById buildNotExistingAnnualCertificatePsvLargeArticulated() {

        TestTypeById testTypeById = new TestTypeById();

        testTypeById.setId("8")
                .setTestTypeClassification("Annual With Certificate");

        return testTypeById;
    }




    public static TestTypeById buildAnnualNoCertificatePsvSmallRigid() {

        TestTypeById testTypeById = new TestTypeById();

        testTypeById.setId("15")
                .setTestTypeClassification("Annual NO CERTIFICATE")
                .setDefaultTestCode("phs")
                .setLinkedTestCode(null);

        return testTypeById;
    }

    public static TestTypeById buildNonAnnualPsvLargeRigid() {

        TestTypeById testTypeById = new TestTypeById();

        testTypeById.setId("31")
                .setTestTypeClassification("NON ANNUAL")
                .setDefaultTestCode("qdl")
                .setLinkedTestCode(null);

        return testTypeById;
    }


}
