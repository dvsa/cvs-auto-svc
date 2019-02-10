package data;

import model.vehicles.*;

import java.util.Arrays;

public class VehicleTechRecordsData {

    public static Vehicle buildVehicleTechRecordsCurrentData() {

        Vrms vrms = new Vrms()
                .setVrm("BQ91YHQ")
                .setPrimary(true);


        BrakeForceWheelsNotLocked brakeForceWheelsNotLocked = new BrakeForceWheelsNotLocked()
                .setServiceBrakeForceA(7713)
                .setSecondaryBrakeForceA(3857)
                .setParkingBrakeForceA(2742);

        BrakeForceWheelsUpToHalfLocked brakeForceWheelsUpToHalfLocked = new BrakeForceWheelsUpToHalfLocked()
                .setServiceBrakeForceB(6658)
                .setSecondaryBrakeForceB(3329)
                .setParkingBrakeForceB(2130);


        Brakes brakes = new Brakes()
                .setBrakeCode("171202")
                .setDataTrBrakeOne("None")
                .setDataTrBrakeTwo("None")
                .setDataTrBrakeThree("None")
                .setParkingBrakeMrk(null)
                .setRetarderBrakeOne("exhaust")
                .setRetarderBrakeTwo("exhaust")
                .setBrakeForceWheelsNotLocked(brakeForceWheelsNotLocked)
                .setBrakeForceWheelsUpToHalfLocked(brakeForceWheelsUpToHalfLocked);

        Weights weightsOne = new Weights()
                .setKerbWeight(5018)
                .setLadenWeight(7100)
                .setGbWeight(7100)
                .setDesignWeight(7100);

        Tyres tyresOne = new Tyres()
                .setTyreSize("295/80-22.5")
                .setPlyRating("A")
                .setFitmentCode("single")
                .setDataTrAxles(0)
                .setSpeedCategorySymbol("J")
                .setTyreCode(456);

        Weights weightsTwo = new Weights()
                .setKerbWeight(8297)
                .setLadenWeight(11500)
                .setGbWeight(11500)
                .setDesignWeight(12600);

        Tyres tyresTwo = new Tyres()
                .setTyreSize("295/80-22.5")
                .setPlyRating("A")
                .setFitmentCode("double")
                .setDataTrAxles(0)
                .setSpeedCategorySymbol("J")
                .setTyreCode(456);

        Axles axleOne = new Axles()
                .setAxleNumber(1)
                .setWeights(weightsOne)
                .setTyres(tyresOne);

        Axles axleTwo = new Axles()
                .setAxleNumber(2)
                .setWeights(weightsTwo)
                .setTyres(tyresTwo);

        TechRecord techRecord = new TechRecord()
                .setCreatedAt("2019-01-16T12:24:38.027Z")
                .setLastUpdatedAt("2019-01-16T12:24:38.027Z")
                .setChassisMake("Mercedes")
                .setChassisModel("632,01")
                .setBodyMake("Plaxton")
                .setBodyModel("Tourismo")
                .setBodyType("single Decker")
                .setManufactureDate(2010)
                .setRegnDate("2011-01-05")
                .setCoifDate("2010-12-20")
                .setNtaNumber("7")
                .setConversionRefNo("2")
                .setSeatsLowerDeck(50)
                .setSeatsUpperDeck(0)
                .setStandingCapacity(0)
                .setSpeedRestriction(0)
                .setSpeedLimiterMrk(false)
                .setTachoExemptMrk(false)
                .setDispensations("None")
                .setRemarks("None")
                .setReasonForCreation("COIF")
                .setStatusCode("current")
                .setUnladenWeight(0)
                .setGrossKerbWeight(13315)
                .setGrossLadenWeight(17140)
                .setGrossGbWeight(18000)
                .setGrossDesignWeight(19000)
                .setNoOfAxles(2)
                .setBrakeCode("171202")
                .setVehicleClass("S")
                .setVehicleType("PSV")
                .setVehicleSize("small")
                .setVehicleConfiguration("rigid")
                .setBrakes(brakes)
                .setAxles(Arrays.asList(axleOne, axleTwo));



        return new Vehicle()
                .setVrms(Arrays.asList(vrms))
                .setVim("1B7GG36N12S678410")
                .setTechRecord(Arrays.asList(techRecord));
    }


    public static Vehicle buildVehicleTechRecordsArchivedData() {

        Vrms vrms = new Vrms()
                .setVrm("AA12BCD")
                .setPrimary(true);


        BrakeForceWheelsNotLocked brakeForceWheelsNotLocked = new BrakeForceWheelsNotLocked()
                .setServiceBrakeForceA(7529)
                .setSecondaryBrakeForceA(3764)
                .setParkingBrakeForceA(2677);

        BrakeForceWheelsUpToHalfLocked brakeForceWheelsUpToHalfLocked = new BrakeForceWheelsUpToHalfLocked()
                .setServiceBrakeForceB(5310)
                .setSecondaryBrakeForceB(2655)
                .setParkingBrakeForceB(1699);


        Brakes brakes = new Brakes()
                .setBrakeCode("167202")
                .setDataTrBrakeOne("None")
                .setDataTrBrakeTwo("None")
                .setDataTrBrakeThree("None")
                .setParkingBrakeMrk(null)
                .setRetarderBrakeOne("hydraulic")
                .setRetarderBrakeTwo("hydraulic")
                .setBrakeForceWheelsNotLocked(brakeForceWheelsNotLocked)
                .setBrakeForceWheelsUpToHalfLocked(brakeForceWheelsUpToHalfLocked);

        Weights weightsOne = new Weights()
                .setKerbWeight(3630)
                .setLadenWeight(6014)
                .setGbWeight(6300)
                .setDesignWeight(6500);

        Tyres tyresOne = new Tyres()
                .setTyreSize("11-22.5")
                .setPlyRating("A")
                .setFitmentCode("single")
                .setDataTrAxles(0)
                .setSpeedCategorySymbol("L")
                .setTyreCode(438);

        Weights weightsTwo = new Weights()
                .setKerbWeight(6990)
                .setLadenWeight(10716)
                .setGbWeight(10500)
                .setDesignWeight(11000);

        Tyres tyresTwo = new Tyres()
                .setTyreSize("11-22.5")
                .setPlyRating("A")
                .setFitmentCode("double")
                .setDataTrAxles(0)
                .setSpeedCategorySymbol("L")
                .setTyreCode(438);

        Axles axleOne = new Axles()
                .setAxleNumber(1)
                .setWeights(weightsOne)
                .setTyres(tyresOne);

        Axles axleTwo = new Axles()
                .setAxleNumber(2)
                .setWeights(weightsTwo)
                .setTyres(tyresTwo);

        TechRecord techRecord = new TechRecord()
                .setCreatedAt("2018-04-20T04:20:00.027Z")
                .setLastUpdatedAt("2018-10-01T00:00:00.027Z")
                .setChassisMake("Leyland")
                .setChassisModel("Olympian")
                .setBodyMake("Northen Counties")
                .setBodyModel("Palatine 1")
                .setBodyType("double Decker")
                .setManufactureDate(1996)
                .setRegnDate("1996-10-17")
                .setCoifDate("1996-09-01")
                .setNtaNumber("3")
                .setConversionRefNo("5")
                .setSeatsLowerDeck(29)
                .setSeatsUpperDeck(47)
                .setStandingCapacity(17)
                .setSpeedRestriction(0)
                .setSpeedLimiterMrk(false)
                .setTachoExemptMrk(false)
                .setDispensations("None")
                .setRemarks("MAX GEARED SPEED 60 AXLE 2 LADEN WEIGHT EXCEEDS GB MAX BY C & U REG 78 (5)(B)(II) DRIVERS ASSAULT SCREEN FTD")
                .setReasonForCreation("COIF")
                .setStatusCode("archived")
                .setUnladenWeight(0)
                .setGrossKerbWeight(10620)
                .setGrossLadenWeight(16730)
                .setGrossGbWeight(16800)
                .setGrossDesignWeight(17000)
                .setNoOfAxles(2)
                .setBrakeCode("167202")
                .setVehicleClass("L")
                .setVehicleType("PSV")
                .setVehicleSize("large")
                .setVehicleConfiguration("rigid")
                .setBrakes(brakes)
                .setAxles(Arrays.asList(axleOne, axleTwo));



        return new Vehicle()
                .setVrms(Arrays.asList(vrms))
                .setVim("XMGDE02FS0H012356")
                .setTechRecord(Arrays.asList(techRecord));
    }

    public static Vehicle buildVehicleTechRecordsProvisionalData() {

        Vrms vrms = new Vrms()
                .setVrm("AA34BCD")
                .setPrimary(true);


        BrakeForceWheelsNotLocked brakeForceWheelsNotLocked = new BrakeForceWheelsNotLocked()
                .setServiceBrakeForceA(6084)
                .setSecondaryBrakeForceA(3042)
                .setParkingBrakeForceA(2163);

        BrakeForceWheelsUpToHalfLocked brakeForceWheelsUpToHalfLocked = new BrakeForceWheelsUpToHalfLocked()
                .setServiceBrakeForceB(4570)
                .setSecondaryBrakeForceB(2285)
                .setParkingBrakeForceB(1462);


        Brakes brakes = new Brakes()
                .setBrakeCode("135202")
                .setDataTrBrakeOne("None")
                .setDataTrBrakeTwo("None")
                .setDataTrBrakeThree("None")
                .setParkingBrakeMrk(null)
                .setRetarderBrakeOne("hydraulic")
                .setRetarderBrakeTwo("hydraulic")
                .setBrakeForceWheelsNotLocked(brakeForceWheelsNotLocked)
                .setBrakeForceWheelsUpToHalfLocked(brakeForceWheelsUpToHalfLocked);

        Weights weightsOne = new Weights()
                .setKerbWeight(2760)
                .setLadenWeight(0)
                .setGbWeight(5000)
                .setDesignWeight(5000);

        Tyres tyresOne = new Tyres()
                .setTyreSize("265/70-19.5")
                .setPlyRating("A")
                .setFitmentCode("single")
                .setDataTrAxles(0)
                .setSpeedCategorySymbol("M")
                .setTyreCode(735);

        Weights weightsTwo = new Weights()
                .setKerbWeight(6380)
                .setLadenWeight(0)
                .setGbWeight(9440)
                .setDesignWeight(9440);

        Tyres tyresTwo = new Tyres()
                .setTyreSize("265/70-19.5")
                .setPlyRating("A")
                .setFitmentCode("single")
                .setDataTrAxles(0)
                .setSpeedCategorySymbol("M")
                .setTyreCode(735);

        Axles axleOne = new Axles()
                .setAxleNumber(1)
                .setWeights(weightsOne)
                .setTyres(tyresOne);

        Axles axleTwo = new Axles()
                .setAxleNumber(2)
                .setWeights(weightsTwo)
                .setTyres(tyresTwo);

        TechRecord techRecord = new TechRecord()
                .setCreatedAt("2018-04-20T04:20:00.027Z")
                .setLastUpdatedAt("2018-10-01T00:00:00.027Z")
                .setChassisMake("DAF")
                .setChassisModel("SB200")
                .setBodyMake("Plaxton")
                .setBodyModel("Centro")
                .setBodyType("single Decker")
                .setManufactureDate(2008)
                .setRegnDate("2008-09-24")
                .setCoifDate("2008-04-23")
                .setNtaNumber("1")
                .setConversionRefNo("1")
                .setSeatsLowerDeck(45)
                .setSeatsUpperDeck(0)
                .setStandingCapacity(26)
                .setSpeedRestriction(0)
                .setSpeedLimiterMrk(false)
                .setTachoExemptMrk(false)
                .setDispensations("None")
                .setRemarks("OUTSWING 120MM. 45 & 26 OR 42 SEATS & 1 WHEELCHAIR WITH 26 STANDEES. DDA SCHEDULES 1 & 2. SEAT BELTS FITTED TO ALL FORWARD FACING SEATS 3 POINT 42 IN TOTAL INC DRIVER.")
                .setReasonForCreation("COIF")
                .setStatusCode("provisional")
                .setUnladenWeight(0)
                .setGrossKerbWeight(9140)
                .setGrossLadenWeight(13520)
                .setGrossGbWeight(14440)
                .setGrossDesignWeight(14440)
                .setNoOfAxles(2)
                .setBrakeCode("135202")
                .setVehicleClass("L")
                .setVehicleType("PSV")
                .setVehicleSize("small")
                .setVehicleConfiguration("rigid")
                .setBrakes(brakes)
                .setAxles(Arrays.asList(axleOne, axleTwo));



        return new Vehicle()
                .setVrms(Arrays.asList(vrms))
                .setVim("1B7GG36N12S021430")
                .setTechRecord(Arrays.asList(techRecord));
    }


}
