package data;

import model.defects.*;

import java.util.Arrays;
import java.util.Collections;

public class DefectsData {

    public static Defect buildDefectsData() {

        Location location = new Location()
                .setVertical(Arrays.asList("upper"))
                .setHorizontal(Collections.emptyList())
                .setLateral(Arrays.asList("nearside"))
                .setLongitudinal(Collections.emptyList())
                .setRowNumber(Arrays.asList(2))
                .setSeatNumber(Arrays.asList(2))
                .setAxelNumber(Collections.emptyList());

        Psv psv = new Psv()
                .setLocation(location)
                .setNotes(Boolean.TRUE);

        AdditionalInfo additionalInfo = new AdditionalInfo()
                .setPsv(psv)
                .setHgv(new Hgv())
                .setTrl(new Trl());

        Deficiencies firstItemDeficiencyOne = new Deficiencies()
                .setRef("3.1.a")
                .setDeficiencyId("a")
                .setDeficiencySubId(null)
                .setDeficiencyCategory("Major")
                .setDeficiencyText("missing.")
                .setStdForProhibition(Boolean.FALSE)
                .setForVehicleType(Arrays.asList("psv", "hgv"));

        Deficiencies firstItemDeficiencyTwo = new Deficiencies()
                .setRef("3.1.b")
                .setDeficiencyId("b")
                .setDeficiencySubId(null)
                .setDeficiencyCategory("Major")
                .setDeficiencyText("of an incorrect type.")
                .setStdForProhibition(Boolean.FALSE)
                .setForVehicleType(Arrays.asList("psv", "hgv"));

        Deficiencies secondItemDeficiencyOne = new Deficiencies()
                .setRef("3.2.a.i")
                .setDeficiencyId("a")
                .setDeficiencySubId("i")
                .setDeficiencyCategory("Major")
                .setDeficiencyText("with excessive corrosion, serious deterioration or a fracture in a load bearing member of the vehicle structure within 30cm of the anchorage (where a seat belt is attached to a seat frame this will apply to all seat mounting points).")
                .setStdForProhibition(Boolean.FALSE)
                .setForVehicleType(Arrays.asList("psv", "hgv"));

        Deficiencies secondItemDeficiencyTwo = new Deficiencies()
                .setRef("3.2.a.ii")
                .setDeficiencyId("a")
                .setDeficiencySubId("ii")
                .setDeficiencyCategory("Dangerous")
                .setDeficiencyText("with excessive corrosion, serious deterioration or a fracture in a load bearing member of the vehicle structure within 30cm of the anchorage (where a seat belt is attached to a seat frame this will apply to all seat mounting points) and is likely to detach.")
                .setStdForProhibition(Boolean.TRUE)
                .setForVehicleType(Arrays.asList("psv", "hgv"));

        Deficiencies secondItemDeficiencyThree = new Deficiencies()
                .setRef("3.2.b")
                .setDeficiencyId("b")
                .setDeficiencySubId(null)
                .setDeficiencyCategory("Major")
                .setDeficiencyText("a seat belt not securely fixed to the seat or to the vehicle structure.")
                .setStdForProhibition(Boolean.FALSE)
                .setForVehicleType(Arrays.asList("psv", "hgv"));

        Deficiencies thirdItemDeficiencyOne = new Deficiencies()
                .setRef("3.3.a")
                .setDeficiencyId("a")
                .setDeficiencySubId(null)
                .setDeficiencyCategory("Major")
                .setDeficiencyText("locking mechanism of a seat belt does not secure or release as intended.")
                .setStdForProhibition(Boolean.FALSE)
                .setForVehicleType(Arrays.asList("psv", "hgv"));

        Deficiencies thirdItemDeficiencyTwo = new Deficiencies()
                .setRef("3.3.b")
                .setDeficiencyId("b")
                .setDeficiencySubId(null)
                .setDeficiencyCategory("Major")
                .setDeficiencyText("an attachment or adjustment fitting fractured, badly deteriorated or not operating effectively.")
                .setStdForProhibition(Boolean.FALSE)
                .setForVehicleType(Arrays.asList("psv", "hgv"));

        Deficiencies thirdItemDeficiencyThree = new Deficiencies()
                .setRef("3.3.c")
                .setDeficiencyId("c")
                .setDeficiencySubId(null)
                .setDeficiencyCategory("Major")
                .setDeficiencyText("corrosion or deterioration of a flexible stalk likely to lead to failure under load.")
                .setStdForProhibition(Boolean.FALSE)
                .setForVehicleType(Arrays.asList("psv", "hgv"));

        Deficiencies thirdItemDeficiencyFour = new Deficiencies()
                .setRef("3.3.d")
                .setDeficiencyId("d")
                .setDeficiencySubId(null)
                .setDeficiencyCategory("Major")
                .setDeficiencyText("broken flexible stalk strands")
                .setStdForProhibition(Boolean.FALSE)
                .setForVehicleType(Arrays.asList("psv", "hgv"));

        Deficiencies thirdItemDeficiencyFive = new Deficiencies()
                .setRef("3.3.e")
                .setDeficiencyId("e")
                .setDeficiencySubId(null)
                .setDeficiencyCategory("Major")
                .setDeficiencyText("a retracting mechanism that does not retract the webbing sufficiently to remove all of the slack from the belt with the locking mechanism fastened and the seat unoccupied.")
                .setStdForProhibition(Boolean.FALSE)
                .setForVehicleType(Arrays.asList("psv", "hgv"));

        Deficiencies fourthItemDeficiencyOne = new Deficiencies()
                .setRef("3.4.a.i")
                .setDeficiencyId("a")
                .setDeficiencySubId("i")
                .setDeficiencyCategory("Minor")
                .setDeficiencyText("a cut or damage or fluffing or fraying, which is not sufficient to obstruct correct operation of the belt or which has not clearly weakened the webbing.")
                .setStdForProhibition(Boolean.FALSE)
                .setForVehicleType(Arrays.asList("psv", "hgv"));

        Deficiencies fourthItemDeficiencyTwo = new Deficiencies()
                .setRef("3.4.a.ii")
                .setDeficiencyId("a")
                .setDeficiencySubId("ii")
                .setDeficiencyCategory("Major")
                .setDeficiencyText("a cut or damage or fluffing or fraying or overstretching sufficient to obstruct correct operation of the belt or significantly weaken the webbing.")
                .setStdForProhibition(Boolean.FALSE)
                .setForVehicleType(Arrays.asList("psv", "hgv"));

        Deficiencies fourthItemDeficiencyThree = new Deficiencies()
                .setRef("3.4.b")
                .setDeficiencyId("a")
                .setDeficiencySubId("ii")
                .setDeficiencyCategory("Major")
                .setDeficiencyText("stitching badly frayed, insecure, incomplete or repaired.")
                .setStdForProhibition(Boolean.FALSE)
                .setForVehicleType(Arrays.asList("psv", "hgv"));

        Deficiencies fifthItemDeficiencyOne = new Deficiencies()
                .setRef("3.5")
                .setDeficiencyId(null)
                .setDeficiencySubId(null)
                .setDeficiencyCategory("Major")
                .setDeficiencyText("such that failure is likely.")
                .setStdForProhibition(Boolean.FALSE)
                .setForVehicleType(Arrays.asList("psv", "hgv"));

        Deficiencies sixthItemDeficiencyOne = new Deficiencies()
                .setRef("3.6.a")
                .setDeficiencyId("a")
                .setDeficiencySubId(null)
                .setDeficiencyCategory("Dangerous")
                .setDeficiencyText("insecure.")
                .setStdForProhibition(Boolean.TRUE)
                .setForVehicleType(Arrays.asList("psv", "hgv"));

        Deficiencies sixthItemDeficiencyTwo = new Deficiencies()
                .setRef("3.6.b")
                .setDeficiencyId("b")
                .setDeficiencySubId(null)
                .setDeficiencyCategory("Major")
                .setDeficiencyText("with a cracked or fractured leg or frame.")
                .setStdForProhibition(Boolean.FALSE)
                .setForVehicleType(Arrays.asList("psv", "hgv"));

        Deficiencies seventhItemDeficiencyOne = new Deficiencies()
                .setRef("3.7.a")
                .setDeficiencyId("a")
                .setDeficiencySubId(null)
                .setDeficiencyCategory("Major")
                .setDeficiencyText("load limiter or pretensioner obviously missing where fitted as original equipment.")
                .setStdForProhibition(Boolean.FALSE)
                .setForVehicleType(Arrays.asList("psv", "hgv"));

        Deficiencies seventhItemDeficiencyTwo = new Deficiencies()
                .setRef("3.7.b")
                .setDeficiencyId("b")
                .setDeficiencySubId(null)
                .setDeficiencyCategory("Major")
                .setDeficiencyText("pretensioner or a ‘folded type’ webbing load limiter obviously deployed.")
                .setStdForProhibition(Boolean.FALSE)
                .setForVehicleType(Arrays.asList("psv", "hgv"));

        Deficiencies eightItemDeficiencyOne = new Deficiencies()
                .setRef("3.8.a")
                .setDeficiencyId("a")
                .setDeficiencySubId(null)
                .setDeficiencyCategory("Major")
                .setDeficiencyText("missing.")
                .setStdForProhibition(Boolean.FALSE)
                .setForVehicleType(Arrays.asList("psv", "hgv"));

        Deficiencies eightItemDeficiencyTwo = new Deficiencies()
                .setRef("3.8.b")
                .setDeficiencyId("b")
                .setDeficiencySubId(null)
                .setDeficiencyCategory("Major")
                .setDeficiencyText("deployed or disconnected.")
                .setStdForProhibition(Boolean.FALSE)
                .setForVehicleType(Arrays.asList("psv", "hgv"));


        Deficiencies ninthItemDeficiencyOne = new Deficiencies()
                .setRef("3.9")
                .setDeficiencyId(null)
                .setDeficiencySubId(null)
                .setDeficiencyCategory("Major")
                .setDeficiencyText("indicates any kind of failure of the system.")
                .setStdForProhibition(Boolean.FALSE)
                .setForVehicleType(Arrays.asList("psv", "hgv"));

        Items firstItem = new Items()
                .setItemNumber(1)
                .setItemDescription("Obligatory Seat Belt:")
                .setForVehicleType(Arrays.asList("psv", "hgv"))
                .setDeficiencies(Arrays.asList(firstItemDeficiencyOne, firstItemDeficiencyTwo));

        Items secondItem = new Items()
                .setItemNumber(2)
                .setItemDescription("Anchorages:")
                .setForVehicleType(Arrays.asList("psv", "hgv"))
                .setDeficiencies(Arrays.asList(secondItemDeficiencyOne, secondItemDeficiencyTwo, secondItemDeficiencyThree));

        Items thirdItem = new Items()
                .setItemNumber(3)
                .setItemDescription("Locking Mechanism, Stalks, Retracting Mechanism and Fittings:")
                .setForVehicleType(Arrays.asList("psv", "hgv"))
                .setDeficiencies(Arrays.asList(thirdItemDeficiencyOne, thirdItemDeficiencyTwo, thirdItemDeficiencyThree, thirdItemDeficiencyFour, thirdItemDeficiencyFive));

        Items forthItem = new Items()
                .setItemNumber(4)
                .setItemDescription("Condition of Webbing:")
                .setForVehicleType(Arrays.asList("psv", "hgv"))
                .setDeficiencies(Arrays.asList(fourthItemDeficiencyOne, fourthItemDeficiencyTwo, fourthItemDeficiencyThree));

        Items fifthItem = new Items()
                .setItemNumber(5)
                .setItemDescription("Obvious signs of structural weakness in a Seat belt; fitting, guide, stalk or pivot")
                .setForVehicleType(Arrays.asList("psv", "hgv"))
                .setDeficiencies(Arrays.asList(fifthItemDeficiencyOne));

        Items sixthItem = new Items()
                .setItemNumber(6)
                .setItemDescription("Seats with seat belts attached to them:")
                .setForVehicleType(Arrays.asList("psv", "hgv"))
                .setDeficiencies(Arrays.asList(sixthItemDeficiencyOne, sixthItemDeficiencyTwo));

        Items seventhItem = new Items()
                .setItemNumber(7)
                .setItemDescription("A seat belt:")
                .setForVehicleType(Arrays.asList("psv", "hgv"))
                .setDeficiencies(Arrays.asList(seventhItemDeficiencyOne, seventhItemDeficiencyTwo));

        Items eightItem = new Items()
                .setItemNumber(8)
                .setItemDescription("An airbag:")
                .setForVehicleType(Arrays.asList("psv", "hgv"))
                .setDeficiencies(Arrays.asList(eightItemDeficiencyOne, eightItemDeficiencyTwo));

        Items ninthItem = new Items()
                .setItemNumber(9)
                .setItemDescription("The SRS warning lamp")
                .setForVehicleType(Arrays.asList("psv", "hgv"))
                .setDeficiencies(Arrays.asList(ninthItemDeficiencyOne));


        Defect defect = new Defect()
                .setImNumber(3)
                .setImDescription("Seat Belts & Supplementary Restraint Systems")
                .setForVehicleType(Arrays.asList("psv"))
                .setAdditionalInfo(additionalInfo)
                .setItems(Arrays.asList(firstItem, secondItem, thirdItem, forthItem, fifthItem, sixthItem, seventhItem, eightItem, ninthItem));

        return defect;
    }
}
