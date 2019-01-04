package data;

import model.TestType;

import java.util.Arrays;

public class TestTypeData {

    public static TestType buildTestTypeData() {


        TestType levelThreeFirst = new TestType()
                .setId("7")
                .setName("Public Service Vehicle Annual Testing")
                .setForVehicleType(Arrays.asList("psv"))
                .setForVehicleConfiguration(Arrays.asList("rigid"))
                .setForVehicleSize(Arrays.asList("large"));

        TestType levelThreeSecond = new TestType()
                .setId("8")
                .setName("Public Service Vehicle Annual Testing")
                .setForVehicleType(Arrays.asList("psv"))
                .setForVehicleConfiguration(Arrays.asList("rigid"))
                .setForVehicleSize(Arrays.asList("small"));

        TestType levelThreeThird = new TestType()
                .setId("10")
                .setName("Public Service Vehicle Annual Testing")
                .setForVehicleType(Arrays.asList("psv"))
                .setForVehicleConfiguration(Arrays.asList("rigid"))
                .setForVehicleSize(Arrays.asList("large"));


        TestType levelTwoFirst = new TestType()
                .setId("6")
                .setName("ANNUAL")
                .setForVehicleType(Arrays.asList("psv"))
                .setForVehicleConfiguration(Arrays.asList("rigid"))
                .setForVehicleSize(Arrays.asList("small","large"))
                .setNextTestTypesOrCategories(Arrays.asList(levelThreeFirst,levelThreeSecond));

        TestType levelTwoSecond = new TestType()
                .setId("9")
                .setName("FIRST TEST")
                .setForVehicleType(Arrays.asList("psv"))
                .setForVehicleConfiguration(Arrays.asList("rigid"))
                .setForVehicleSize(Arrays.asList("large"))
                .setNextTestTypesOrCategories(Arrays.asList(levelThreeThird));

        TestType levelOneFirst = new TestType()
                .setId("5")
                .setName("Class 6A (Seatbelt Installation Check)")
                .setForVehicleType(Arrays.asList("psv"))
                .setForVehicleConfiguration(Arrays.asList("rigid"))
                .setForVehicleSize(Arrays.asList("small","large"))
                .setNextTestTypesOrCategories(Arrays.asList(levelTwoFirst,levelTwoSecond));



        return levelOneFirst;
    }

}
