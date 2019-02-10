package data;

import model.TestStations;

import java.util.Arrays;

public class TestStationsData {

    public static TestStations buildTestStationData() {
        
        TestStations testStations = new TestStations();

        testStations.setTestStationId("2")
                .setTestStationNumber("84-926821")
                .setTestStationName("Larson, Nader and Okuneva")
                .setTestStationContactNumber("+373 597 232 4326")
                .setTestStationAccessNotes("Praesent id massa id nisl venenatis lacinia. Aenean sit amet justo. Morbi ut odio.\n\nCras mi pede, malesuada in, imperdiet et, commodo vulputate, justo. In blandit ultrices enim. Lorem ipsum dolor sit amet, consectetuer adipiscing elit.\n\nProin interdum mauris non ligula pellentesque ultrices. Phasellus id sapien in sapien iaculis congue. Vivamus metus arcu, adipiscing molestie, hendrerit at, vulputate vitae, nisl.")
                .setTestStationGeneralNotes("Praesent id massa id nisl venenatis lacinia. Aenean sit amet justo. Morbi ut odio.\n\nCras mi pede, malesuada in, imperdiet et, commodo vulputate, justo. In blandit ultrices enim. Lorem ipsum dolor sit amet, consectetuer adipiscing elit.")
                .setTestStationTown("Ciorescu")
                .setTestStationAddress("61969 Texas Trail")
                .setTestStationPostcode("43547-250")
                .setTestStationLongitude("28.8905763")
                .setTestStationLatitude("47.1263592")
                .setTestStationType("atf")
                .setTestStationEmails(Arrays.asList("teststationname@dvsa.gov.uk","teststationname1@dvsa.gov.uk"));



        return testStations;
    }
}
