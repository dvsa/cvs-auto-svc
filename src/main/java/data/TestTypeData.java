package data;

import model.TestType;

import java.util.Arrays;

public class TestTypeData {

    public static TestType buildTestTypeData() {


        TestType levelThreeFirst = new TestType()
                .setId("7")
                .setName("Any PSV")
                .setTestTypeName("Paid retest")
                .setForVehicleType(Arrays.asList("psv"))
                .setForVehicleConfiguration(Arrays.asList("rigid"))
                .setForVehicleSize(Arrays.asList("large", "small"));

        TestType levelThreeSecond = new TestType()
                .setId("8")
                .setName("Class 6A (seatbelt installation check)")
                .setTestTypeName("Paid retest with Class 6A seatbelt installation check")
                .setForVehicleType(Arrays.asList("psv"))
                .setForVehicleConfiguration(Arrays.asList("rigid"))
                .setForVehicleSize(Arrays.asList("small","large"));

        TestType levelThreeThird = new TestType()
                .setId("10")
                .setName("Any PSV")
                .setTestTypeName("Part-paid retest")
                .setForVehicleType(Arrays.asList("psv"))
                .setForVehicleConfiguration(Arrays.asList("rigid","articulated"))
                .setForVehicleSize(Arrays.asList("large","small"));


        TestType levelTwoFirst = new TestType()
                .setId("6")
                .setName("Paid")
                .setForVehicleType(Arrays.asList("psv"))
                .setForVehicleConfiguration(Arrays.asList("rigid","articulated"))
                .setForVehicleSize(Arrays.asList("large","small"))
                .setForVehicleAxles(null)
                .setNextTestTypesOrCategories(Arrays.asList(levelThreeFirst,levelThreeSecond));

        TestType levelTwoSecond = new TestType()
                .setId("9")
                .setName("Part paid")
                .setForVehicleType(Arrays.asList("psv"))
                .setForVehicleConfiguration(Arrays.asList("rigid","articulated"))
                .setForVehicleSize(Arrays.asList("large","small"))
                .setForVehicleAxles(null)
                .setNextTestTypesOrCategories(Arrays.asList(levelThreeThird));

        TestType levelOneFirst = new TestType()
                .setId("5")
                .setName("Retest")
                .setForVehicleType(Arrays.asList("psv"))
                .setForVehicleConfiguration(Arrays.asList("rigid","articulated"))
                .setForVehicleSize(Arrays.asList("large","small"))
                .setForVehicleAxles(null)
                .setNextTestTypesOrCategories(Arrays.asList(levelTwoFirst,levelTwoSecond));



        return levelOneFirst;
    }

}
