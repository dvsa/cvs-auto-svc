package data;

import model.vehicles.*;

import java.util.Arrays;

public class VehicleTechnicalRecordsData {

    public static Vehicle buildVehicleTechnicalRecordsData() {

        Vrms vrms = new Vrms()
                .setVrm("BQ91YHQ")
                .setPrimary(true);


        BrakeForceWheelsNotLocked brakeForceWheelsNotLocked = new BrakeForceWheelsNotLocked()
                .setServiceBrakeForceA(53671)
                .setSecondaryBrakeForceA(45199)
                .setParkingBrakeForceA(83665);

        BrakeForceWheelsUpToHalfLocked brakeForceWheelsUpToHalfLocked = new BrakeForceWheelsUpToHalfLocked()
                .setServiceBrakeForceB(6739)
                .setSecondaryBrakeForceB(17115)
                .setParkingBrakeForceB(0);


        Brakes brakes = new Brakes()
                .setBrakeCode("1jU6")
                .setDataTrBrakeOne("oaCS4aZ5UGGZT")
                .setDataTrBrakeTwo("bmsZLJjBZFRMu3")
                .setDataTrBrakeThree("KeQHRwq9")
                .setParkingBrakeMrk("Y")
                .setRetarderBrakeOne("friction")
                .setRetarderBrakeTwo("exhaust")
                .setBrakeForceWheelsNotLocked(brakeForceWheelsNotLocked)
                .setBrakeForceWheelsUpToHalfLocked(brakeForceWheelsUpToHalfLocked);

        Weights weights = new Weights()
                .setKerbWeight(53850)
                .setLadenWeight(6383)
                .setGbWeight(40343)
                .setDesignWeight(30923);

        Tyres tyres = new Tyres()
                .setTyreSize("4IHi7")
                .setPlyRating("E9")
                .setFitmentCode("single")
                .setDataTrPsvAxles(416)
                .setSpeedCategorySymbol("J")
                .setTyreCode(1355);

        Axles axles = new Axles()
                .setAxleNumber(24075)
                .setWeights(weights)
                .setTyres(tyres);

        TechRecord techRecordCurrent = new TechRecord()
                .setChassisMake("Zentix")
                .setChassisModel("non")
                .setBodyMake("Unisure")
                .setBodyModel("do")
                .setBodyType("other")
                .setManufactureDate(3225)
                .setRegnDate("2006-07-01")
                .setCoifDate("1991-12-07")
                .setNtaNumber("pP")
                .setConversionRefNo("S5J9z")
                .setSeatsLowerDeck(986)
                .setSeatsUpperDeck(57)
                .setStandingCapacity(251)
                .setSpeedRestriction(29)
                .setSpeedLimiterMrk(true)
                .setTachoExemptMrk(true)
                .setDispensations("3UZnR")
                .setRemarks("bof1ikLSURNJi0oxHdAmyo1")
                .setReasonForCreation("DFGIscm7")
                .setStatusCode("current")
                .setUnladenWeight(0)
                .setGrossKerbWeight(20445)
                .setGrossLadenWeight(82879)
                .setGrossGbWeight(85025)
                .setGrossDesignWeight(71125)
                .setGrossUnladenWeight(0)
                .setNoOfAxles(43)
                .setBrakeCode("o")
                .setVehicleClass("2 (MotorBikes over 200cc or with a sidecar)")
                .setVehicleType("PSV")
                .setVehicleSize("small")
                .setVehicleConfiguration("rigid")
                .setBrakes(brakes)
                .setAxles(Arrays.asList(axles));


        BrakeForceWheelsNotLocked brakeForceWheelsNotLockedArchived = new BrakeForceWheelsNotLocked()
                .setServiceBrakeForceA(44482)
                .setSecondaryBrakeForceA(65448)
                .setParkingBrakeForceA(32627);

        BrakeForceWheelsUpToHalfLocked brakeForceWheelsUpToHalfLockedArchived = new BrakeForceWheelsUpToHalfLocked()
                .setServiceBrakeForceB(47735)
                .setSecondaryBrakeForceB(15716)
                .setParkingBrakeForceB(0);


        Brakes brakesArchived = new Brakes()
                .setBrakeCode("OwPJ")
                .setDataTrBrakeOne("xClTahe7T")
                .setDataTrBrakeTwo("hejoc56QyqD1TaaiR")
                .setDataTrBrakeThree("wy1ON253")
                .setParkingBrakeMrk("Y")
                .setRetarderBrakeOne("retarder")
                .setRetarderBrakeTwo("electric")
                .setBrakeForceWheelsNotLocked(brakeForceWheelsNotLockedArchived)
                .setBrakeForceWheelsUpToHalfLocked(brakeForceWheelsUpToHalfLockedArchived);

        Weights weightsArchived = new Weights()
                .setKerbWeight(22947)
                .setLadenWeight(34114)
                .setGbWeight(54929)
                .setDesignWeight(51869);

        Tyres tyresArchived = new Tyres()
                .setTyreSize("4Uq")
                .setPlyRating("lN")
                .setFitmentCode("double")
                .setDataTrPsvAxles(477)
                .setSpeedCategorySymbol("M")
                .setTyreCode(268);

        Axles axlesArchived = new Axles()
                .setAxleNumber(82242)
                .setWeights(weightsArchived)
                .setTyres(tyresArchived);


        TechRecord techRecordArchived = new TechRecord()
                .setChassisMake("Spherix")
                .setChassisModel("sunt")
                .setBodyMake("Limage")
                .setBodyModel("reprehenderit")
                .setBodyType("articulated")
                .setManufactureDate(7335)
                .setRegnDate("1975-08-04")
                .setCoifDate("1997-08-07")
                .setNtaNumber("99jw")
                .setConversionRefNo("j5JG")
                .setSeatsLowerDeck(481)
                .setSeatsUpperDeck(0)
                .setStandingCapacity(435)
                .setSpeedRestriction(66)
                .setSpeedLimiterMrk(false)
                .setTachoExemptMrk(false)
                .setDispensations("gqlNnCQbjo0pTvxbQLlW")
                .setRemarks("epl3IVUcF5RQsr")
                .setReasonForCreation("MQwLaw")
                .setStatusCode("archived")
                .setUnladenWeight(0)
                .setGrossKerbWeight(24118)
                .setGrossLadenWeight(60201)
                .setGrossGbWeight(80606)
                .setGrossDesignWeight(58359)
                .setGrossUnladenWeight(0)
                .setNoOfAxles(80)
                .setBrakeCode("AaNF")
                .setVehicleClass("2 (MotorBikes over 200cc or with a sidecar)")
                .setVehicleType("PSV")
                .setVehicleSize("small")
                .setVehicleConfiguration("rigid")
                .setBrakes(brakesArchived)
                .setAxles(Arrays.asList(axlesArchived));


        BrakeForceWheelsNotLocked brakeForceWheelsNotLockedProvisional = new BrakeForceWheelsNotLocked()
                .setServiceBrakeForceA(97178)
                .setSecondaryBrakeForceA(2045)
                .setParkingBrakeForceA(34313);

        BrakeForceWheelsUpToHalfLocked brakeForceWheelsUpToHalfLockedProvisional = new BrakeForceWheelsUpToHalfLocked()
                .setServiceBrakeForceB(61506)
                .setSecondaryBrakeForceB(30288)
                .setParkingBrakeForceB(0);


        Brakes brakesProvisional = new Brakes()
                .setBrakeCode("D")
                .setDataTrBrakeOne("OmwAMv")
                .setDataTrBrakeTwo("r7WieoPyoGrYF")
                .setDataTrBrakeThree("D7M6YCcdrSAF")
                .setParkingBrakeMrk("Y")
                .setRetarderBrakeOne("hydraulic")
                .setRetarderBrakeTwo("hydraulic")
                .setBrakeForceWheelsNotLocked(brakeForceWheelsNotLockedProvisional)
                .setBrakeForceWheelsUpToHalfLocked(brakeForceWheelsUpToHalfLockedProvisional);

        Weights weightsProvisional = new Weights()
                .setKerbWeight(99043)
                .setLadenWeight(67698)
                .setGbWeight(32303)
                .setDesignWeight(28468);

        Tyres tyresProvisional = new Tyres()
                .setTyreSize("q")
                .setPlyRating("Ia")
                .setFitmentCode("double")
                .setDataTrPsvAxles(19)
                .setSpeedCategorySymbol("F")
                .setTyreCode(6122);

        Axles axlesProvisional = new Axles()
                .setAxleNumber(73099)
                .setWeights(weightsProvisional)
                .setTyres(tyresProvisional);


        TechRecord techRecordProvisional = new TechRecord()
                .setChassisMake("Xinware")
                .setChassisModel("ut")
                .setBodyMake("Neteria")
                .setBodyModel("commodo")
                .setBodyType("articulated")
                .setManufactureDate(4168)
                .setRegnDate("2016-02-06")
                .setCoifDate("1986-06-05")
                .setNtaNumber("b")
                .setConversionRefNo("g")
                .setSeatsLowerDeck(514)
                .setSeatsUpperDeck(0)
                .setStandingCapacity(467)
                .setSpeedRestriction(52)
                .setSpeedLimiterMrk(false)
                .setTachoExemptMrk(true)
                .setDispensations("kf2fi1yrIHmk")
                .setRemarks("6YAipKiPKACLYoi0lZK0k0N6jM2cB19RfeN1LXUlXzKgVDiPJiKjyaFn")
                .setReasonForCreation("nKkeXRGBd0I57y")
                .setStatusCode("provisional")
                .setUnladenWeight(0)
                .setGrossKerbWeight(88967)
                .setGrossLadenWeight(54842)
                .setGrossGbWeight(91464)
                .setGrossDesignWeight(97689)
                .setGrossUnladenWeight(0)
                .setNoOfAxles(84)
                .setBrakeCode("6E")
                .setVehicleClass("2 (MotorBikes over 200cc or with a sidecar)")
                .setVehicleType("PSV")
                .setVehicleSize("small")
                .setVehicleConfiguration("rigid")
                .setBrakes(brakesProvisional)
                .setAxles(Arrays.asList(axlesProvisional));


        return new Vehicle()
                .setVrms(Arrays.asList(vrms))
                .setVim("1B7GG36N12S678410")
                .setTechRecord(Arrays.asList(techRecordCurrent, techRecordArchived, techRecordProvisional ));
    }

}
