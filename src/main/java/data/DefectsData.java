package data;

import model.defects.*;

import java.util.Arrays;
import java.util.Collections;

public class DefectsData {

    public static Defect buildDefectsData() {

        Location location = new Location()
                .setVertical(Arrays.asList("upper", "lower"))
                .setHorizontal(null)
                .setLateral(Arrays.asList("nearside", "offside", "centre"))
                .setLongitudinal(null)
                .setRowNumber(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20))
                .setSeatNumber(Arrays.asList(1,2,3,4,5,6))
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
                .setDeficiencyCategory("major")
                .setDeficiencyText("missing.")
                .setStdForProhibition(Boolean.FALSE)
                .setForVehicleType(Arrays.asList("psv", "hgv"));

        Deficiencies firstItemDeficiencyTwo = new Deficiencies()
                .setRef("3.1.b")
                .setDeficiencyId("b")
                .setDeficiencySubId(null)
                .setDeficiencyCategory("major")
                .setDeficiencyText("of an incorrect type.")
                .setStdForProhibition(Boolean.FALSE)
                .setForVehicleType(Arrays.asList("psv", "hgv"));

        Deficiencies secondItemDeficiencyOne = new Deficiencies()
                .setRef("3.2.a.i")
                .setDeficiencyId("a")
                .setDeficiencySubId("i")
                .setDeficiencyCategory("major")
                .setDeficiencyText("with excessive corrosion, serious deterioration or a fracture in a load bearing member of the vehicle structure within 30cm of the anchorage (where a seat belt is attached to a seat frame this will apply to all seat mounting points).")
                .setStdForProhibition(Boolean.FALSE)
                .setForVehicleType(Arrays.asList("psv", "hgv"));

        Deficiencies secondItemDeficiencyTwo = new Deficiencies()
                .setRef("3.2.a.ii")
                .setDeficiencyId("a")
                .setDeficiencySubId("ii")
                .setDeficiencyCategory("dangerous")
                .setDeficiencyText("with excessive corrosion, serious deterioration or a fracture in a load bearing member of the vehicle structure within 30cm of the anchorage (where a seat belt is attached to a seat frame this will apply to all seat mounting points) and is likely to detach.")
                .setStdForProhibition(Boolean.TRUE)
                .setForVehicleType(Arrays.asList("psv", "hgv"));

        Deficiencies secondItemDeficiencyThree = new Deficiencies()
                .setRef("3.2.b")
                .setDeficiencyId("b")
                .setDeficiencySubId(null)
                .setDeficiencyCategory("major")
                .setDeficiencyText("a seat belt not securely fixed to the seat or to the vehicle structure.")
                .setStdForProhibition(Boolean.FALSE)
                .setForVehicleType(Arrays.asList("psv", "hgv"));

        Deficiencies thirdItemDeficiencyOne = new Deficiencies()
                .setRef("3.3.a")
                .setDeficiencyId("a")
                .setDeficiencySubId(null)
                .setDeficiencyCategory("major")
                .setDeficiencyText("locking mechanism of a seat belt does not secure or release as intended.")
                .setStdForProhibition(Boolean.FALSE)
                .setForVehicleType(Arrays.asList("psv", "hgv"));

        Deficiencies thirdItemDeficiencyTwo = new Deficiencies()
                .setRef("3.3.b")
                .setDeficiencyId("b")
                .setDeficiencySubId(null)
                .setDeficiencyCategory("major")
                .setDeficiencyText("an attachment or adjustment fitting fractured, badly deteriorated or not operating effectively.")
                .setStdForProhibition(Boolean.FALSE)
                .setForVehicleType(Arrays.asList("psv", "hgv"));

        Deficiencies thirdItemDeficiencyThree = new Deficiencies()
                .setRef("3.3.c")
                .setDeficiencyId("c")
                .setDeficiencySubId(null)
                .setDeficiencyCategory("major")
                .setDeficiencyText("corrosion or deterioration of a flexible stalk likely to lead to failure under load.")
                .setStdForProhibition(Boolean.FALSE)
                .setForVehicleType(Arrays.asList("psv", "hgv"));

        Deficiencies thirdItemDeficiencyFour = new Deficiencies()
                .setRef("3.3.d")
                .setDeficiencyId("d")
                .setDeficiencySubId(null)
                .setDeficiencyCategory("major")
                .setDeficiencyText("broken flexible stalk strands")
                .setStdForProhibition(Boolean.FALSE)
                .setForVehicleType(Arrays.asList("psv", "hgv"));

        Deficiencies thirdItemDeficiencyFive = new Deficiencies()
                .setRef("3.3.e")
                .setDeficiencyId("e")
                .setDeficiencySubId(null)
                .setDeficiencyCategory("major")
                .setDeficiencyText("a retracting mechanism that does not retract the webbing sufficiently to remove all of the slack from the belt with the locking mechanism fastened and the seat unoccupied.")
                .setStdForProhibition(Boolean.FALSE)
                .setForVehicleType(Arrays.asList("psv", "hgv"));

        Deficiencies fourthItemDeficiencyOne = new Deficiencies()
                .setRef("3.4.a.i")
                .setDeficiencyId("a")
                .setDeficiencySubId("i")
                .setDeficiencyCategory("minor")
                .setDeficiencyText("a cut or damage or fluffing or fraying, which is not sufficient to obstruct correct operation of the belt or which has not clearly weakened the webbing.")
                .setStdForProhibition(Boolean.FALSE)
                .setForVehicleType(Arrays.asList("psv", "hgv"));

        Deficiencies fourthItemDeficiencyTwo = new Deficiencies()
                .setRef("3.4.a.ii")
                .setDeficiencyId("a")
                .setDeficiencySubId("ii")
                .setDeficiencyCategory("major")
                .setDeficiencyText("a cut or damage or fluffing or fraying or overstretching sufficient to obstruct correct operation of the belt or significantly weaken the webbing.")
                .setStdForProhibition(Boolean.FALSE)
                .setForVehicleType(Arrays.asList("psv", "hgv"));

        Deficiencies fourthItemDeficiencyThree = new Deficiencies()
                .setRef("3.4.b")
                .setDeficiencyId("b")
                .setDeficiencySubId(null)
                .setDeficiencyCategory("major")
                .setDeficiencyText("stitching badly frayed, insecure, incomplete or repaired.")
                .setStdForProhibition(Boolean.FALSE)
                .setForVehicleType(Arrays.asList("psv", "hgv"));

        Deficiencies fourthItemDeficiencyFour = new Deficiencies()
                .setRef("3.4.c")
                .setDeficiencyId("c")
                .setDeficiencySubId(null)
                .setDeficiencyCategory("minor")
                .setDeficiencyText("so dirty that it is likely to soil passengers’ clothing.")
                .setStdForProhibition(Boolean.FALSE)
                .setForVehicleType(Arrays.asList("psv"));

        Deficiencies fifthItemDeficiencyOne = new Deficiencies()
                .setRef("3.5")
                .setDeficiencyId(null)
                .setDeficiencySubId(null)
                .setDeficiencyCategory("major")
                .setDeficiencyText("such that failure is likely.")
                .setStdForProhibition(Boolean.FALSE)
                .setForVehicleType(Arrays.asList("psv", "hgv"));

        Deficiencies sixthItemDeficiencyOne = new Deficiencies()
                .setRef("3.6.a")
                .setDeficiencyId("a")
                .setDeficiencySubId(null)
                .setDeficiencyCategory("dangerous")
                .setDeficiencyText("insecure.")
                .setStdForProhibition(Boolean.TRUE)
                .setForVehicleType(Arrays.asList("psv", "hgv"));

        Deficiencies sixthItemDeficiencyTwo = new Deficiencies()
                .setRef("3.6.b")
                .setDeficiencyId("b")
                .setDeficiencySubId(null)
                .setDeficiencyCategory("major")
                .setDeficiencyText("with a cracked or fractured leg or frame.")
                .setStdForProhibition(Boolean.FALSE)
                .setForVehicleType(Arrays.asList("psv", "hgv"));

        Deficiencies seventhItemDeficiencyOne = new Deficiencies()
                .setRef("3.7.a")
                .setDeficiencyId("a")
                .setDeficiencySubId(null)
                .setDeficiencyCategory("major")
                .setDeficiencyText("load limiter or pretensioner obviously missing where fitted as original equipment.")
                .setStdForProhibition(Boolean.FALSE)
                .setForVehicleType(Arrays.asList("psv", "hgv"));

        Deficiencies seventhItemDeficiencyTwo = new Deficiencies()
                .setRef("3.7.b")
                .setDeficiencyId("b")
                .setDeficiencySubId(null)
                .setDeficiencyCategory("major")
                .setDeficiencyText("pretensioner or a ‘folded type’ webbing load limiter obviously deployed.")
                .setStdForProhibition(Boolean.FALSE)
                .setForVehicleType(Arrays.asList("psv", "hgv"));

        Deficiencies eightItemDeficiencyOne = new Deficiencies()
                .setRef("3.8.a")
                .setDeficiencyId("a")
                .setDeficiencySubId(null)
                .setDeficiencyCategory("major")
                .setDeficiencyText("missing.")
                .setStdForProhibition(Boolean.FALSE)
                .setForVehicleType(Arrays.asList("psv", "hgv"));

        Deficiencies eightItemDeficiencyTwo = new Deficiencies()
                .setRef("3.8.b")
                .setDeficiencyId("b")
                .setDeficiencySubId(null)
                .setDeficiencyCategory("major")
                .setDeficiencyText("deployed or disconnected.")
                .setStdForProhibition(Boolean.FALSE)
                .setForVehicleType(Arrays.asList("psv", "hgv"));


        Deficiencies ninthItemDeficiencyOne = new Deficiencies()
                .setRef("3.9")
                .setDeficiencyId(null)
                .setDeficiencySubId(null)
                .setDeficiencyCategory("major")
                .setDeficiencyText("indicates any kind of failure of the system.")
                .setStdForProhibition(Boolean.FALSE)
                .setForVehicleType(Arrays.asList("psv", "hgv"));

        Deficiencies tenthItemDeficiencyOne = new Deficiencies()
                .setRef("3.10.a")
                .setDeficiencyId("a")
                .setDeficiencySubId(null)
                .setDeficiencyCategory("major")
                .setDeficiencyText("any obvious installation defect found during the inspection.")
                .setStdForProhibition(Boolean.FALSE)
                .setForVehicleType(Arrays.asList("psv"));

        Deficiencies eleventhItemDeficiencyOne = new Deficiencies()
                .setRef("3.11.a")
                .setDeficiencyId("a")
                .setDeficiencySubId(null)
                .setDeficiencyCategory("major")
                .setDeficiencyText("evidence that original webbing has been cut and/or reworked (e.g. belts knotted, fraying or fluffing removed/sealed by burning etc.).")
                .setStdForProhibition(Boolean.FALSE)
                .setForVehicleType(Arrays.asList("psv"));


        Deficiencies eleventhItemDeficiencyTwo = new Deficiencies()
                .setRef("3.11.b")
                .setDeficiencyId("b")
                .setDeficiencySubId(null)
                .setDeficiencyCategory("major")
                .setDeficiencyText("any part of the installation which has a sharp edge which could or is likely to cut or abrade the webbing.")
                .setStdForProhibition(Boolean.FALSE)
                .setForVehicleType(Arrays.asList("psv"));

        Deficiencies eleventhItemDeficiencyThree = new Deficiencies()
                .setRef("3.11.c")
                .setDeficiencyId("c")
                .setDeficiencySubId(null)
                .setDeficiencyCategory("major")
                .setDeficiencyText("a directly attached anchorage not secured by standard seat belt mounting bolts and washers as detailed in paragraph D.")
                .setStdForProhibition(Boolean.FALSE)
                .setForVehicleType(Arrays.asList("psv"));

        Deficiencies eleventhItemDeficiencyFour = new Deficiencies()
                .setRef("3.11.d")
                .setDeficiencyId("d")
                .setDeficiencySubId(null)
                .setDeficiencyCategory("major")
                .setDeficiencyText("an anchorage insecure.")
                .setStdForProhibition(Boolean.FALSE)
                .setForVehicleType(Arrays.asList("psv"));

        Deficiencies eleventhItemDeficiencyFive = new Deficiencies()
                .setRef("3.11.e")
                .setDeficiencyId("e")
                .setDeficiencySubId(null)
                .setDeficiencyCategory("major")
                .setDeficiencyText("a tubular seat frame that has been drilled for the purpose of attaching a seat belt.")
                .setStdForProhibition(Boolean.FALSE)
                .setForVehicleType(Arrays.asList("psv"));

        Deficiencies eleventhItemDeficiencySix = new Deficiencies()
                .setRef("3.11.f")
                .setDeficiencyId("f")
                .setDeficiencySubId(null)
                .setDeficiencyCategory("major")
                .setDeficiencyText("a directly attached anchorage not attached to a load bearing member or without suitable reinforcement.")
                .setStdForProhibition(Boolean.FALSE)
                .setForVehicleType(Arrays.asList("psv"));

        Deficiencies eleventhItemDeficiencySeven = new Deficiencies()
                .setRef("3.11.g")
                .setDeficiencyId("g")
                .setDeficiencySubId(null)
                .setDeficiencyCategory("major")
                .setDeficiencyText("retro-fitted three point belt which is not mounted on a suitable structure.")
                .setStdForProhibition(Boolean.FALSE)
                .setForVehicleType(Arrays.asList("psv"));


        Deficiencies eleventhItemDeficiencyEight = new Deficiencies()
                .setRef("3.11.h")
                .setDeficiencyId("h")
                .setDeficiencySubId(null)
                .setDeficiencyCategory("major")
                .setDeficiencyText("tubular frame legs or tubular “H” pattern legs which have not been reinforced with buttressing and diagonal bracing, or buttressing where a floor mounted belt is fitted close to a seat leg.")
                .setStdForProhibition(Boolean.FALSE)
                .setForVehicleType(Arrays.asList("psv"));

        Deficiencies eleventhItemDeficiencyNine = new Deficiencies()
                .setRef("3.11.i")
                .setDeficiencyId("i")
                .setDeficiencySubId(null)
                .setDeficiencyCategory("major")
                .setDeficiencyText("belt fitted to a seat which has not been suitably reinforced or modified.")
                .setStdForProhibition(Boolean.FALSE)
                .setForVehicleType(Arrays.asList("psv"));

        Deficiencies eleventhItemDeficiencyTen = new Deficiencies()
                .setRef("3.11.j")
                .setDeficiencyId("j")
                .setDeficiencySubId(null)
                .setDeficiencyCategory("major")
                .setDeficiencyText("without suitable padding as detailed in paragraph L.")
                .setStdForProhibition(Boolean.FALSE)
                .setForVehicleType(Arrays.asList("psv"));

        Deficiencies eleventhItemDeficiencyEleven = new Deficiencies()
                .setRef("3.11.k")
                .setDeficiencyId("k")
                .setDeficiencySubId(null)
                .setDeficiencyCategory("major")
                .setDeficiencyText("lower anchorages less than 320 mm apart.")
                .setStdForProhibition(Boolean.FALSE)
                .setForVehicleType(Arrays.asList("psv"));

        Deficiencies eleventhItemDeficiencyTwelve = new Deficiencies()
                .setRef("3.11.l")
                .setDeficiencyId("l")
                .setDeficiencySubId(null)
                .setDeficiencyCategory("major")
                .setDeficiencyText("in such a position that loading the belt causes the cushion to be raised or significantly compressed thus allowing the occupant to effectively move forward.")
                .setStdForProhibition(Boolean.FALSE)
                .setForVehicleType(Arrays.asList("psv"));

        Deficiencies eleventhItemDeficiencyThirteen = new Deficiencies()
                .setRef("3.11.m")
                .setDeficiencyId("m")
                .setDeficiencySubId(null)
                .setDeficiencyCategory("major")
                .setDeficiencyText("an anchorage attached to the floor without reinforcement plates of a suitable size and contour.")
                .setStdForProhibition(Boolean.FALSE)
                .setForVehicleType(Arrays.asList("psv"));

        Deficiencies eleventhItemDeficiencyFourteen = new Deficiencies()
                .setRef("3.11.n")
                .setDeficiencyId("n")
                .setDeficiencySubId(null)
                .setDeficiencyCategory("major")
                .setDeficiencyText("with load spreading washer(s) missing from anchorage bolt.")
                .setStdForProhibition(Boolean.FALSE)
                .setForVehicleType(Arrays.asList("psv"));

        Deficiencies eleventhItemDeficiencyFifteen = new Deficiencies()
                .setRef("3.11.o")
                .setDeficiencyId("o")
                .setDeficiencySubId(null)
                .setDeficiencyCategory("major")
                .setDeficiencyText("claw type seat mounting with inadequate means of securing claw.")
                .setStdForProhibition(Boolean.FALSE)
                .setForVehicleType(Arrays.asList("psv"));

        Deficiencies eleventhItemDeficiencySixteen = new Deficiencies()
                .setRef("3.11.p")
                .setDeficiencyId("p")
                .setDeficiencySubId(null)
                .setDeficiencyCategory("major")
                .setDeficiencyText("on a seat fitted to a flat rail the bolt does not pass through the leg, rail, floor and a suitable structural member or the floor has not been suitably reinforced.")
                .setStdForProhibition(Boolean.FALSE)
                .setForVehicleType(Arrays.asList("psv"));

        Deficiencies eleventhItemDeficiencySeventeen = new Deficiencies()
                .setRef("3.11.q")
                .setDeficiencyId("q")
                .setDeficiencySubId(null)
                .setDeficiencyCategory("major")
                .setDeficiencyText("tracking for securing seats and wheelchairs insecure.")
                .setStdForProhibition(Boolean.FALSE)
                .setForVehicleType(Arrays.asList("psv"));

        Deficiencies eleventhItemDeficiencyEighteen = new Deficiencies()
                .setRef("3.11.r")
                .setDeficiencyId("r")
                .setDeficiencySubId(null)
                .setDeficiencyCategory("major")
                .setDeficiencyText("free movement for a looped belt more than 25mm at the anchorage.")
                .setStdForProhibition(Boolean.FALSE)
                .setForVehicleType(Arrays.asList("psv"));

        Deficiencies eleventhItemDeficiencyNineteen = new Deficiencies()
                .setRef("3.11.s")
                .setDeficiencyId("s")
                .setDeficiencySubId(null)
                .setDeficiencyCategory("major")
                .setDeficiencyText("upper anchorage of three point belt less than 475 mm above uncompressed seat cushion measured parallel to the seat back.")
                .setStdForProhibition(Boolean.FALSE)
                .setForVehicleType(Arrays.asList("psv"));

        Deficiencies eleventhItemDeficiencyTwenty = new Deficiencies()
                .setRef("3.11.t")
                .setDeficiencyId("t")
                .setDeficiencySubId(null)
                .setDeficiencyCategory("major")
                .setDeficiencyText("upper anchorage of three point belt(s) less than 110 mm from centre line of seat.")
                .setStdForProhibition(Boolean.FALSE)
                .setForVehicleType(Arrays.asList("psv"));

        Deficiencies eleventhItemDeficiencyTwentyOne = new Deficiencies()
                .setRef("3.11.u")
                .setDeficiencyId("u")
                .setDeficiencySubId(null)
                .setDeficiencyCategory("major")
                .setDeficiencyText("incorrect positioning of a lap belt or lap section of a three point belt. i.e. the belt lies across the stomach or forward of the top quarter of the thigh.")
                .setStdForProhibition(Boolean.FALSE)
                .setForVehicleType(Arrays.asList("psv"));

        Deficiencies eleventhItemDeficiencyTwentyTwo = new Deficiencies()
                .setRef("3.11.v")
                .setDeficiencyId("v")
                .setDeficiencySubId(null)
                .setDeficiencyCategory("major")
                .setDeficiencyText("a seat belt component fitted to a seat significantly intrudes into a gangway and is likely to cause injury to a passenger.")
                .setStdForProhibition(Boolean.FALSE)
                .setForVehicleType(Arrays.asList("psv"));

        Deficiencies eleventhItemDeficiencyTwentyThree = new Deficiencies()
                .setRef("3.11.w")
                .setDeficiencyId("w")
                .setDeficiencySubId(null)
                .setDeficiencyCategory("major")
                .setDeficiencyText("for vehicles subject to enhanced seat belt requirements no evidence that seat belt anchorages are likely to meet the strength requirements of EC directive 76/115/EC as amended by directive 96/38/EC.")
                .setStdForProhibition(Boolean.FALSE)
                .setForVehicleType(Arrays.asList("psv"));


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
                .setDeficiencies(Arrays.asList(fourthItemDeficiencyOne, fourthItemDeficiencyTwo, fourthItemDeficiencyThree, fourthItemDeficiencyFour));

        Items fifthItem = new Items()
                .setItemNumber(5)
                .setItemDescription("Obvious signs of structural weakness in a Seat belt; fitting, guide,stalk or pivot")
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

        Items tenthItem = new Items()
                .setItemNumber(10)
                .setItemDescription("Installation defect found during annual test:")
                .setForVehicleType(Arrays.asList("psv"))
                .setDeficiencies(Arrays.asList(tenthItemDeficiencyOne));

        Items eleventhItem = new Items()
                .setItemNumber(11)
                .setItemDescription("Installation inspection:")
                .setForVehicleType(Arrays.asList("psv"))
                .setDeficiencies(Arrays.asList(eleventhItemDeficiencyOne, eleventhItemDeficiencyTwo, eleventhItemDeficiencyThree, eleventhItemDeficiencyFour,eleventhItemDeficiencyFive, eleventhItemDeficiencySix, eleventhItemDeficiencySeven, eleventhItemDeficiencyEight, eleventhItemDeficiencyNine, eleventhItemDeficiencyTen,
                        eleventhItemDeficiencyEleven, eleventhItemDeficiencyTwelve, eleventhItemDeficiencyThirteen, eleventhItemDeficiencyFourteen, eleventhItemDeficiencyFifteen, eleventhItemDeficiencySixteen, eleventhItemDeficiencySeventeen, eleventhItemDeficiencyEighteen, eleventhItemDeficiencyNineteen,
                        eleventhItemDeficiencyTwenty, eleventhItemDeficiencyTwentyOne, eleventhItemDeficiencyTwentyTwo, eleventhItemDeficiencyTwentyThree));


        Defect defect = new Defect()
                .setImNumber(3)
                .setImDescription("Seat Belts & Supplementary Restraint Systems")
                .setForVehicleType(Arrays.asList("psv", "hgv"))
                .setAdditionalInfo(additionalInfo)
                .setItems(Arrays.asList(firstItem, secondItem, thirdItem, forthItem, fifthItem, sixthItem, seventhItem, eightItem, ninthItem, tenthItem, eleventhItem));

        return defect;
    }
}
