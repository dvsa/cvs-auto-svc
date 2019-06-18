package data;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import data.config.DataMapper;
import model.testtypeid.TestTypeById;
import org.apache.commons.exec.environment.EnvironmentUtils;

import java.io.File;
import java.io.IOException;

public class TestTypeByIdData {


    public static TestTypeById buildAnnualCertificatePsvSmallRigid() {

        return DataMapper.getTestTypeById("Annual With Certificate", "psv", "small", "rigid", null);

    }


    public static TestTypeById buildAnnualCertificatePsvLargeArticulated() {

        return DataMapper.getTestTypeById("Annual With Certificate", "psv", "large", "articulated", null);

    }


    public static TestTypeById buildAnnualCertificatePsvLargeRigid() {

        return DataMapper.getTestTypeById("Annual NO CERTIFICATE", "psv", "large", "rigid", null);

    }

    public static TestTypeById buildNotExistingAnnualCertificatePsvSmallArticulated() {

        return DataMapper.getNotExistingTestTypeById("Annual With Certificate", "psv", "small", "articulated", null);

    }

    public static TestTypeById buildNotExistingAnnualCertificatePsvLargeArticulated() {

        return DataMapper.getNotExistingTestTypeById("Annual With Certificate", "psv", "large", "articulated", null);

    }


    public static TestTypeById buildAnnualNoCertificatePsvSmallRigid() {

        return DataMapper.getTestTypeById("Annual NO CERTIFICATE", "psv", "small", "rigid", null);

    }

    public static TestTypeById buildNonAnnualPsvLargeRigid() {

        return DataMapper.getTestTypeById("NON ANNUAL", "psv", "large", "rigid", null);

    }

    public static TestTypeById buildNonAnnualPsvSmallRigidTwoAxles() {

        return DataMapper.getTestTypeById("NON ANNUAL", "psv", "small", "rigid", "2");

    }


}
