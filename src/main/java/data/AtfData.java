package data;

import model.Atf;

public class AtfData {

    public static Atf buildAtfData() {
        Atf atf = new Atf();

        atf.setAtfId("2")
                .setAtfNumber("84-926821")
                .setAtfName("Larson, Nader and Okuneva")
                .setAtfContactNumber("+373 597 232 4326")
                .setAtfAccessNotes("Praesent id massa id nisl venenatis lacinia. Aenean sit amet justo. Morbi ut odio.\n\nCras mi pede, malesuada in, imperdiet et, commodo vulputate, justo. In blandit ultrices enim. Lorem ipsum dolor sit amet, consectetuer adipiscing elit.\n\nProin interdum mauris non ligula pellentesque ultrices. Phasellus id sapien in sapien iaculis congue. Vivamus metus arcu, adipiscing molestie, hendrerit at, vulputate vitae, nisl.")
                .setAtfGeneralNotes("Praesent id massa id nisl venenatis lacinia. Aenean sit amet justo. Morbi ut odio.\n\nCras mi pede, malesuada in, imperdiet et, commodo vulputate, justo. In blandit ultrices enim. Lorem ipsum dolor sit amet, consectetuer adipiscing elit.")
                .setAtfTown("Ciorescu")
                .setAtfAddress("61969 Texas Trail")
                .setAtfPostcode("43547-250")
                .setAtfLongitude("28.8905763")
                .setAtfLatitude("47.1263592")
                .setAtfType("ATF");


        return atf;
    }
}
