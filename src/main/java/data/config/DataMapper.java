package data.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import exceptions.AutomationException;
import model.testtypeid.TestCodes;
import model.testtypeid.TestTypeById;
import model.vehicles.Vehicle;
import model.vehicles.Vrms;
import org.apache.commons.exec.environment.EnvironmentUtils;
import util.DataLoader;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.asList;

public class DataMapper {


    public static TestTypeById getTestTypeById(String testTypeClassification, String forVehicleType, String forVehicleSize, String forVehicleConfiguration, String forVehicleAxles) {

        TestTypeById testTypeByIdReturned = null;

        try {

            ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);


            TestTypeById[] node = objectMapper.readValue(new File(EnvironmentUtils.class.getClassLoader().getResource("loader/" + DataLoader.getDataLocation() + "/test-type-by-id.json").getFile()), TestTypeById[].class);
            List<TestTypeById> list = Arrays.asList(node);

            testTypeByIdReturned = findTestType(list, testTypeClassification, forVehicleType, forVehicleSize, forVehicleConfiguration, forVehicleAxles);
        } catch (IOException e) {
            throw new AutomationException("Error: " + e.getMessage());
        }

        if (testTypeByIdReturned == null) {
            throw new AutomationException("Error finding test type by id. Check file contents: test-type-by-id.json");
        }

        return testTypeByIdReturned;
    }


    public static TestTypeById getNotExistingTestTypeById(String testTypeClassification, String forVehicleType, String forVehicleSize, String forVehicleConfiguration, String forVehicleAxles) {

        TestTypeById testTypeByIdReturned = null;

        try {

            ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);


            TestTypeById[] node = objectMapper.readValue(new File(EnvironmentUtils.class.getClassLoader().getResource("loader/" + DataLoader.getDataLocation() + "/test-type-by-id.json").getFile()), TestTypeById[].class);
            List<TestTypeById> list = Arrays.asList(node);

            testTypeByIdReturned = findNotExistingTestType(list, testTypeClassification, forVehicleType, forVehicleSize, forVehicleConfiguration, forVehicleAxles);
        } catch (IOException e) {
            throw new AutomationException("Error: " + e.getMessage());
        }

        if (testTypeByIdReturned == null) {
            throw new AutomationException("Error finding test type by id. Check file contents: test-type-by-id.json");
        }

        return testTypeByIdReturned;
    }


    private static TestTypeById findNotExistingTestType(List<TestTypeById> listTest, String testTypeClassification, String forVehicleType, String forVehicleSize, String forVehicleConfiguration, String forVehicleAxles) {

        Boolean found = false;

        TestTypeById testTypeByIdReturned = new TestTypeById();
        testTypeLoop:
        for (TestTypeById testTypeById : listTest) {


            if ((testTypeById.getNextTestTypesOrCategories() != null)) {

                testTypeByIdReturned = findNotExistingTestType(testTypeById.getNextTestTypesOrCategories(), testTypeClassification, forVehicleType, forVehicleSize, forVehicleConfiguration, forVehicleAxles);

                if (testTypeByIdReturned != null && testTypeByIdReturned.getId() != null) {
                    return testTypeByIdReturned;
                }

            } else {

                int size = 0;
                for (TestCodes testCodes : testTypeById.getTestCodes()) {
                    size++;
                    if (!(testTypeById.getTestTypeClassification().equalsIgnoreCase(testTypeClassification) &&
                            testCodes.getForVehicleType().equalsIgnoreCase(forVehicleType) &&
                            testCodes.getForVehicleSize().equalsIgnoreCase(forVehicleSize) &&
                            testCodes.getForVehicleConfiguration().equalsIgnoreCase(forVehicleConfiguration))) {


                        if (testTypeById.getTestCodes().size() == size && found == false) {
                            testTypeByIdReturned.setTestTypeClassification(testTypeClassification);
                            testTypeByIdReturned.setId(testTypeById.getId());
                            found = true;
                        }

                    }
                }

            }

            if (found == true) {
                return testTypeByIdReturned;
            }
        }
        return testTypeByIdReturned;
    }

    private static TestTypeById findTestType(List<TestTypeById> listTest, String testTypeClassification, String forVehicleType, String forVehicleSize, String forVehicleConfiguration, String forVehicleAxles) {

        Boolean found = false;

        TestTypeById testTypeByIdReturned = new TestTypeById();
        testTypeLoop:
        for (TestTypeById testTypeById : listTest) {


            if ((testTypeById.getNextTestTypesOrCategories() != null)) {

                testTypeByIdReturned = findTestType(testTypeById.getNextTestTypesOrCategories(), testTypeClassification, forVehicleType, forVehicleSize, forVehicleConfiguration, forVehicleAxles);

                if (testTypeByIdReturned != null && testTypeByIdReturned.getId() != null) {
                    return testTypeByIdReturned;
                }

            } else {

                int size = 0;
                for (TestCodes testCodes : testTypeById.getTestCodes()) {
                    size++;
                    if (testTypeById.getTestTypeClassification().equalsIgnoreCase(testTypeClassification) &&
                            testCodes.getForVehicleType().equalsIgnoreCase(forVehicleType) &&
                            testCodes.getForVehicleSize().equalsIgnoreCase(forVehicleSize) &&
                            testCodes.getForVehicleConfiguration().equalsIgnoreCase(forVehicleConfiguration)) {


                        if (forVehicleAxles != null) {

                            if (testTypeByIdReturned.getDefaultTestCode() == null) {
                                testTypeByIdReturned.setDefaultTestCode(testCodes.getDefaultTestCode());
                                testTypeByIdReturned.setLinkedTestCode(testCodes.getLinkedTestCode());
                            }


                            if (testCodes.getForVehicleAxles().equalsIgnoreCase(forVehicleAxles)) {
                                testTypeByIdReturned.setTestTypeClassification(testTypeClassification);
                                testTypeByIdReturned.setId(testTypeById.getId());
                                testTypeByIdReturned.setDefaultTestCode(testCodes.getDefaultTestCode());
                                testTypeByIdReturned.setLinkedTestCode(testCodes.getLinkedTestCode());
                                found = true;

                            } else if (testTypeById.getTestCodes().size() == size && found == false) {
                                testTypeByIdReturned.setTestTypeClassification(testTypeClassification);
                                testTypeByIdReturned.setId(testTypeById.getId());
                                continue;
                            } else {
                                continue;
                            }
                        } else {
                            testTypeByIdReturned.setTestTypeClassification(testTypeClassification);
                            testTypeByIdReturned.setId(testTypeById.getId());
                            testTypeByIdReturned.setDefaultTestCode(testCodes.getDefaultTestCode());
                            testTypeByIdReturned.setLinkedTestCode(testCodes.getLinkedTestCode());
                            found = true;
                            break;
                        }

                    }
                }

            }

            if (found == true) {
                return testTypeByIdReturned;
            }
        }
        return testTypeByIdReturned;
    }


    public static <T> T getValue(Class<T> cls, String path) {

        ObjectMapper objectMapper = new ObjectMapper();
        T object = null;
        try {
            object = objectMapper.readValue(new File(EnvironmentUtils.class.getClassLoader().getResource(path).getFile()), cls);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (object.getClass().getName().equals(Vehicle.class.getName())) {
            ObjectNode node = null;
            try {
                node = new ObjectMapper().readValue(new File(EnvironmentUtils.class.getClassLoader().getResource(path).getFile()), ObjectNode.class);
            } catch (IOException e) {
                e.printStackTrace();
            }

            ((Vehicle) object).setVrms(asList(new Vrms().setVrm(node.get("primaryVrm").toString().replaceAll("\"", "")).setPrimary(true)));
        }

        return cls.cast(object);

    }
}


