package clients.model;

public enum TestTypes {
    AAV2("94", "aav2", TestTypeClassification.CERTIFICATE.getValue()),
    AAV3("94", "aav3", TestTypeClassification.CERTIFICATE.getValue()),
    AAV4("94", "aav4", TestTypeClassification.CERTIFICATE.getValue()),
    AAV5("94", "aav5", TestTypeClassification.CERTIFICATE.getValue()),
    AAT1("40", "aat1", TestTypeClassification.CERTIFICATE.getValue()),
    AAT2("94", "aat2", TestTypeClassification.CERTIFICATE.getValue()),
    AAT3("94", "aat3", TestTypeClassification.CERTIFICATE.getValue()),
    AAT4("94", "aat4", TestTypeClassification.CERTIFICATE.getValue()),
    AAT5("94", "aat5", TestTypeClassification.CERTIFICATE.getValue()),
//    commented test types are not yet added in the test-types.json file
//    TGV2("40", "tgv2", TestTypeClassification.CERTIFICATE.getValue()),
//    TGV3("40", "tgv3", TestTypeClassification.CERTIFICATE.getValue()),
//    TGV4("40", "tgv4", TestTypeClassification.CERTIFICATE.getValue()),
    FFV2("95", "ffv2", TestTypeClassification.CERTIFICATE.getValue()),
    FFV3("95", "ffv3", TestTypeClassification.CERTIFICATE.getValue()),
    FFV4("95", "ffv4", TestTypeClassification.CERTIFICATE.getValue()),
    FFV5("95", "ffv5", TestTypeClassification.CERTIFICATE.getValue()),
    FFT1("41", "fft1", TestTypeClassification.CERTIFICATE.getValue()),
    FFT2("95", "fft2", TestTypeClassification.CERTIFICATE.getValue()),
    FFT3("95", "fft3", TestTypeClassification.CERTIFICATE.getValue()),
    FFT4("95", "fft4", TestTypeClassification.CERTIFICATE.getValue()),
    FFT5("95", "fft5", TestTypeClassification.CERTIFICATE.getValue()),
//    FGV2("40", "fgv2", TestTypeClassification.CERTIFICATE.getValue()),
//    FGV3("40", "fgv3", TestTypeClassification.CERTIFICATE.getValue()),
//    FGV4("40", "fgv4", TestTypeClassification.CERTIFICATE.getValue()),
    LDV("44", "ldv", TestTypeClassification.NO_CERTIFICATE.getValue()),
    LEV("45", "lev", TestTypeClassification.NO_CERTIFICATE.getValue()),
    NFV("47", "nfv", TestTypeClassification.NO_CERTIFICATE.getValue()),
    NFT("47", "nft", TestTypeClassification.NO_CERTIFICATE.getValue()),
    NVV("48", "nvv", TestTypeClassification.NO_CERTIFICATE.getValue()),
    NVT("48", "nvt", TestTypeClassification.NO_CERTIFICATE.getValue()),
    TIV("49", "tiv", TestTypeClassification.NO_CERTIFICATE.getValue()),
    TIT("49", "tit", TestTypeClassification.NO_CERTIFICATE.getValue()),
    DDV("50", "ddv", TestTypeClassification.NO_CERTIFICATE.getValue()),
    DDT("50", "ddt", TestTypeClassification.NO_CERTIFICATE.getValue()),
    RPV2("53", "rpv2", TestTypeClassification.CERTIFICATE.getValue()),
    RPV3("53", "rpv3", TestTypeClassification.CERTIFICATE.getValue()),
    RPV4("53", "rpv4", TestTypeClassification.CERTIFICATE.getValue()),
    RPV5("53", "rpv5", TestTypeClassification.CERTIFICATE.getValue()),
    RPT1("98", "rpt1", TestTypeClassification.CERTIFICATE.getValue()),
    RPT2("53", "rpt2", TestTypeClassification.CERTIFICATE.getValue()),
    RPT3("53", "rpt3", TestTypeClassification.CERTIFICATE.getValue()),
    RPT4("53", "rpt4", TestTypeClassification.CERTIFICATE.getValue()),
    RPT5("53", "rpt5", TestTypeClassification.CERTIFICATE.getValue()),
//    PGV2("40", "pgv2", TestTypeClassification.CERTIFICATE.getValue()),
//    PGV3("40", "pgv3", TestTypeClassification.CERTIFICATE.getValue()),
//    PGV4("40", "pgv4", TestTypeClassification.CERTIFICATE.getValue()),
    RSV2("54", "rsv2", TestTypeClassification.CERTIFICATE.getValue()),
    RSV3("54", "rsv3", TestTypeClassification.CERTIFICATE.getValue()),
    RSV4("54", "rsv4", TestTypeClassification.CERTIFICATE.getValue()),
    RSV5("54", "rsv5", TestTypeClassification.CERTIFICATE.getValue()),
    RST1("99", "rst1", TestTypeClassification.CERTIFICATE.getValue()),
    RST2("54", "rst2", TestTypeClassification.CERTIFICATE.getValue()),
    RST3("54", "rst3", TestTypeClassification.CERTIFICATE.getValue()),
    RST4("54", "rst4", TestTypeClassification.CERTIFICATE.getValue()),
    RST5("54", "rst5", TestTypeClassification.CERTIFICATE.getValue()),
//    PTV2("40", "tgv2", TestTypeClassification.CERTIFICATE.getValue()),
//    PTV3("40", "tgv3", TestTypeClassification.CERTIFICATE.getValue()),
//    PTV4("40", "tgv4", TestTypeClassification.CERTIFICATE.getValue()),
    TRV("56", "trv", TestTypeClassification.NO_CERTIFICATE.getValue()),
    TRT("56", "trt", TestTypeClassification.NO_CERTIFICATE.getValue()),
    RFT("57", "rft", TestTypeClassification.NO_CERTIFICATE.getValue()),
    ARV("59", "arv", TestTypeClassification.NO_CERTIFICATE.getValue()),
    ART("59", "art", TestTypeClassification.NO_CERTIFICATE.getValue()),
    DRV("60", "drv", TestTypeClassification.NO_CERTIFICATE.getValue()),
    DRT("60", "drt", TestTypeClassification.NO_CERTIFICATE.getValue()),
    QKV("62", "qkv", TestTypeClassification.NO_CERTIFICATE.getValue()),
    MDA("100", "mda", TestTypeClassification.NO_CERTIFICATE.getValue()),
    MDU("121", "mdu", TestTypeClassification.NO_CERTIFICATE.getValue()),
    QKT("101", "qkt", TestTypeClassification.NO_CERTIFICATE.getValue()),
    QPV("62", "qpv", TestTypeClassification.NO_CERTIFICATE.getValue()),
    QPT("62", "qpt", TestTypeClassification.NO_CERTIFICATE.getValue()),
    QQV("62", "qqv", TestTypeClassification.NO_CERTIFICATE.getValue()),
    QQT("62", "qqt", TestTypeClassification.NO_CERTIFICATE.getValue()),
    RUV("63", "ruv", TestTypeClassification.NO_CERTIFICATE.getValue()),
    RUT("63", "rut", TestTypeClassification.NO_CERTIFICATE.getValue()),
    RGV2("65", "rgv2", TestTypeClassification.CERTIFICATE.getValue()),
    RGV3("65", "rgv3", TestTypeClassification.CERTIFICATE.getValue()),
    RGV4("65", "rgv4", TestTypeClassification.CERTIFICATE.getValue()),
    RGV5("65", "rgv5", TestTypeClassification.CERTIFICATE.getValue()),
    RGT1("103", "rgt1", TestTypeClassification.CERTIFICATE.getValue()),
    RGT2("65", "rgt2", TestTypeClassification.CERTIFICATE.getValue()),
    RGT3("65", "rgt3", TestTypeClassification.CERTIFICATE.getValue()),
    RGT4("65", "rgt4", TestTypeClassification.CERTIFICATE.getValue()),
    RGT5("65", "rgt5", TestTypeClassification.CERTIFICATE.getValue()),
    RIV2("66", "riv2", TestTypeClassification.CERTIFICATE.getValue()),
    RIV3("66", "riv3", TestTypeClassification.CERTIFICATE.getValue()),
    RIV4("66", "riv4", TestTypeClassification.CERTIFICATE.getValue()),
    RIV5("66", "riv5", TestTypeClassification.CERTIFICATE.getValue()),
    RIT1("104", "rit1", TestTypeClassification.CERTIFICATE.getValue()),
    RIT2("66", "rit2", TestTypeClassification.CERTIFICATE.getValue()),
    RIT3("66", "rit3", TestTypeClassification.CERTIFICATE.getValue()),
    RIT4("66", "rit4", TestTypeClassification.CERTIFICATE.getValue()),
    RIT5("66", "rit5", TestTypeClassification.CERTIFICATE.getValue()),
    RHT("67", "rht", TestTypeClassification.CERTIFICATE.getValue()),
    P1V2("70", "p1v2", TestTypeClassification.CERTIFICATE.getValue()),
    P1V3("70", "p1v3", TestTypeClassification.CERTIFICATE.getValue()),
    P1V4("70", "p1v4", TestTypeClassification.CERTIFICATE.getValue()),
    P1V5("70", "p1v5", TestTypeClassification.CERTIFICATE.getValue()),
    P1T1("107", "p1t1", TestTypeClassification.CERTIFICATE.getValue()),
    P1T2("70", "p1t2", TestTypeClassification.CERTIFICATE.getValue()),
    P1T3("70", "p1t3", TestTypeClassification.CERTIFICATE.getValue()),
    P1T4("70", "p1t4", TestTypeClassification.CERTIFICATE.getValue()),
    P1T5("70", "p1t5", TestTypeClassification.CERTIFICATE.getValue()),
    PBV2("71", "pbv2", TestTypeClassification.NO_CERTIFICATE.getValue()),
    PBV3("71", "pbv3", TestTypeClassification.NO_CERTIFICATE.getValue()),
    PBV4("71", "pbv4", TestTypeClassification.NO_CERTIFICATE.getValue()),
    PBV5("71", "pbv5", TestTypeClassification.NO_CERTIFICATE.getValue()),
    PBT1("108", "pbt1", TestTypeClassification.NO_CERTIFICATE.getValue()),
    PBT2("71", "pbt2", TestTypeClassification.NO_CERTIFICATE.getValue()),
    PBT3("71", "pbt3", TestTypeClassification.NO_CERTIFICATE.getValue()),
    PBT4("71", "pbt4", TestTypeClassification.NO_CERTIFICATE.getValue()),
    PBT5("71", "pbt5", TestTypeClassification.NO_CERTIFICATE.getValue()),
    POV2("72", "pov2", TestTypeClassification.NO_CERTIFICATE.getValue()),
    POV3("72", "pov3", TestTypeClassification.NO_CERTIFICATE.getValue()),
    POV4("72", "pov4", TestTypeClassification.NO_CERTIFICATE.getValue()),
    POV5("72", "pov5", TestTypeClassification.NO_CERTIFICATE.getValue()),
    POT1("109", "pot1", TestTypeClassification.NO_CERTIFICATE.getValue()),
    POT2("72", "pot2", TestTypeClassification.NO_CERTIFICATE.getValue()),
    POT3("72", "pot3", TestTypeClassification.NO_CERTIFICATE.getValue()),
    POT4("72", "pot4", TestTypeClassification.NO_CERTIFICATE.getValue()),
    POT5("72", "pot5", TestTypeClassification.NO_CERTIFICATE.getValue()),
    PAV2("73", "pav2", TestTypeClassification.NO_CERTIFICATE.getValue()),
    PAV3("73", "pav3", TestTypeClassification.NO_CERTIFICATE.getValue()),
    PAV4("73", "pav4", TestTypeClassification.NO_CERTIFICATE.getValue()),
    PAV5("73", "pav5", TestTypeClassification.NO_CERTIFICATE.getValue()),
    PAT1("110", "pat1", TestTypeClassification.NO_CERTIFICATE.getValue()),
    PAT2("73", "pat2", TestTypeClassification.NO_CERTIFICATE.getValue()),
    PAT3("73", "pat3", TestTypeClassification.NO_CERTIFICATE.getValue()),
    PAT4("73", "pat4", TestTypeClassification.NO_CERTIFICATE.getValue()),
    PAT5("73", "pat5", TestTypeClassification.NO_CERTIFICATE.getValue()),
    P3V2("76", "p3v2", TestTypeClassification.CERTIFICATE.getValue()),
    P3V3("76", "p3v3", TestTypeClassification.CERTIFICATE.getValue()),
    P3V4("76", "p3v4", TestTypeClassification.CERTIFICATE.getValue()),
    P3V5("76", "p3v5", TestTypeClassification.CERTIFICATE.getValue()),
    P3T1("113", "p3t1", TestTypeClassification.CERTIFICATE.getValue()),
    P3T2("76", "p3t2", TestTypeClassification.CERTIFICATE.getValue()),
    P3T3("76", "p3t3", TestTypeClassification.CERTIFICATE.getValue()),
    P3T4("76", "p3t4", TestTypeClassification.CERTIFICATE.getValue()),
    P3T5("76", "p3t5", TestTypeClassification.CERTIFICATE.getValue()),
    PFV2("77", "pfv2", TestTypeClassification.NO_CERTIFICATE.getValue()),
    PFV3("77", "pfv3", TestTypeClassification.NO_CERTIFICATE.getValue()),
    PFV4("77", "pfv4", TestTypeClassification.NO_CERTIFICATE.getValue()),
    PFV5("77", "pfv5", TestTypeClassification.NO_CERTIFICATE.getValue()),
    PFT1("114", "pft1", TestTypeClassification.NO_CERTIFICATE.getValue()),
    PFT2("77", "pft2", TestTypeClassification.NO_CERTIFICATE.getValue()),
    PFT3("77", "pft3", TestTypeClassification.NO_CERTIFICATE.getValue()),
    PFT4("77", "pft4", TestTypeClassification.NO_CERTIFICATE.getValue()),
    PFT5("77", "pft5", TestTypeClassification.NO_CERTIFICATE.getValue()),
    P6V2("79", "p6v2", TestTypeClassification.CERTIFICATE.getValue()),
    P6V3("79", "p6v3", TestTypeClassification.CERTIFICATE.getValue()),
    P6V4("79", "p6v4", TestTypeClassification.CERTIFICATE.getValue()),
    P6V5("79", "p6v5", TestTypeClassification.CERTIFICATE.getValue()),
    P6T1("116", "p6t1", TestTypeClassification.CERTIFICATE.getValue()),
    P6T2("79", "p6t2", TestTypeClassification.CERTIFICATE.getValue()),
    P6T3("79", "p6t3", TestTypeClassification.CERTIFICATE.getValue()),
    P6T4("79", "p6t4", TestTypeClassification.CERTIFICATE.getValue()),
    P6T5("79", "p6t5", TestTypeClassification.CERTIFICATE.getValue()),
    P2V2("80", "p2v2", TestTypeClassification.NO_CERTIFICATE.getValue()),
    P2V3("80", "p2v3", TestTypeClassification.NO_CERTIFICATE.getValue()),
    P2V4("80", "p2v4", TestTypeClassification.NO_CERTIFICATE.getValue()),
    P2V5("80", "p2v5", TestTypeClassification.NO_CERTIFICATE.getValue()),
    P2T1("117", "p2t1", TestTypeClassification.NO_CERTIFICATE.getValue()),
    P2T2("80", "p2t2", TestTypeClassification.NO_CERTIFICATE.getValue()),
    P2T3("80", "p2t3", TestTypeClassification.NO_CERTIFICATE.getValue()),
    P2T4("80", "p2t4", TestTypeClassification.NO_CERTIFICATE.getValue()),
    P2T5("80", "p2t5", TestTypeClassification.NO_CERTIFICATE.getValue()),
    P7V2("82", "p7v2", TestTypeClassification.CERTIFICATE.getValue()),
    P7V3("82", "p7v3", TestTypeClassification.CERTIFICATE.getValue()),
    P7V4("82", "p7v4", TestTypeClassification.CERTIFICATE.getValue()),
    P7V5("82", "p7v5", TestTypeClassification.CERTIFICATE.getValue()),
    P7T1("119", "p7t1", TestTypeClassification.CERTIFICATE.getValue()),
    P7T2("82", "p7t2", TestTypeClassification.CERTIFICATE.getValue()),
    P7T3("82", "p7t3", TestTypeClassification.CERTIFICATE.getValue()),
    P7T4("82", "p7t4", TestTypeClassification.CERTIFICATE.getValue()),
    P7T5("82", "p7t5", TestTypeClassification.CERTIFICATE.getValue()),
    P4V2("83", "p4v2", TestTypeClassification.CERTIFICATE.getValue()),
    P4V3("83", "p4v3", TestTypeClassification.CERTIFICATE.getValue()),
    P4V4("83", "p4v4", TestTypeClassification.CERTIFICATE.getValue()),
    P4V5("83", "p4v5", TestTypeClassification.CERTIFICATE.getValue()),
    P4T1("120", "p4t1", TestTypeClassification.CERTIFICATE.getValue()),
    P4T2("83", "p4t2", TestTypeClassification.CERTIFICATE.getValue()),
    P4T3("83", "p4t3", TestTypeClassification.CERTIFICATE.getValue()),
    P4T4("83", "p4t4", TestTypeClassification.CERTIFICATE.getValue()),
    P4T5("83", "p4t5", TestTypeClassification.CERTIFICATE.getValue()),
    BIB("85", "bib", TestTypeClassification.NO_CERTIFICATE.getValue()),
    QAV2("85", "qav2", TestTypeClassification.NO_CERTIFICATE.getValue()),
    QAV3("85", "qav3", TestTypeClassification.NO_CERTIFICATE.getValue()),
    QAV4("85", "qav4", TestTypeClassification.NO_CERTIFICATE.getValue()),
    QAV5("85", "qav5", TestTypeClassification.NO_CERTIFICATE.getValue()),
    QAT1("85", "qat1", TestTypeClassification.NO_CERTIFICATE.getValue()),
    QAT2("85", "qat2", TestTypeClassification.NO_CERTIFICATE.getValue()),
    QAT3("85", "qat3", TestTypeClassification.NO_CERTIFICATE.getValue()),
    QAT4("85", "qat4", TestTypeClassification.NO_CERTIFICATE.getValue()),
    QAT5("85", "qat5", TestTypeClassification.NO_CERTIFICATE.getValue()),
    QBV("86", "qbv", TestTypeClassification.NO_CERTIFICATE.getValue()),
    BID_HGV("87", "bid", TestTypeClassification.NO_CERTIFICATE.getValue()),
    BID_TRL("87", "bid", TestTypeClassification.NO_CERTIFICATE.getValue()),
    BIF_HGV("88", "bif", TestTypeClassification.NO_CERTIFICATE.getValue()),
    QCV("89", "qcv", TestTypeClassification.NO_CERTIFICATE.getValue()),
    QDV("90", "qdv", TestTypeClassification.NO_CERTIFICATE.getValue()),
    QJV2("122", "qjv2", TestTypeClassification.NO_CERTIFICATE.getValue()),
    QJV3("122", "qjv3", TestTypeClassification.NO_CERTIFICATE.getValue()),
    QJV4("122", "qjv4", TestTypeClassification.NO_CERTIFICATE.getValue()),
    QJV5("122", "qjv5", TestTypeClassification.NO_CERTIFICATE.getValue()),
    QJT1("91", "qjt1", TestTypeClassification.NO_CERTIFICATE.getValue()),
    QJT2("122", "qjt2", TestTypeClassification.NO_CERTIFICATE.getValue()),
    QJT3("122", "qjt3", TestTypeClassification.NO_CERTIFICATE.getValue()),
    QJT4("122", "qjt4", TestTypeClassification.NO_CERTIFICATE.getValue()),
    QJT5("122", "qjt5", TestTypeClassification.NO_CERTIFICATE.getValue()),
    AAL("1", "aal", TestTypeClassification.NO_CERTIFICATE.getValue()),
    AAS("1", "aas", TestTypeClassification.NO_CERTIFICATE.getValue()),
    ADL("1", "adl", TestTypeClassification.NO_CERTIFICATE.getValue()),
    WDL("3", "wdl", TestTypeClassification.NO_CERTIFICATE.getValue()),
    WDS("3", "wds", TestTypeClassification.NO_CERTIFICATE.getValue()),
    WBL("4", "wbl", TestTypeClassification.NO_CERTIFICATE.getValue()),
    WBS("4", "wbs", TestTypeClassification.NO_CERTIFICATE.getValue()),
    RHL("7", "rhl", TestTypeClassification.NO_CERTIFICATE.getValue()),
    RPL("7", "rpl", TestTypeClassification.NO_CERTIFICATE.getValue()),
    RPS("7", "rps", TestTypeClassification.NO_CERTIFICATE.getValue()),
    WHL("8", "whl", TestTypeClassification.NO_CERTIFICATE.getValue()),
    WHS("8", "whs", TestTypeClassification.NO_CERTIFICATE.getValue()),
    RGL("10", "rgl", TestTypeClassification.NO_CERTIFICATE.getValue()),
    RSL("10", "rsl", TestTypeClassification.NO_CERTIFICATE.getValue()),
    RSS("10", "rss", TestTypeClassification.NO_CERTIFICATE.getValue()),
    PHL("15", "phl", TestTypeClassification.NO_CERTIFICATE.getValue()),
    PHS("15", "phs", TestTypeClassification.NO_CERTIFICATE.getValue()),
    P1L("14", "p1l", TestTypeClassification.NO_CERTIFICATE.getValue()),
    P1S("14", "p1s", TestTypeClassification.NO_CERTIFICATE.getValue()),
    P8L("18", "p8l", TestTypeClassification.NO_CERTIFICATE.getValue()),
    P8S("18", "p8s", TestTypeClassification.NO_CERTIFICATE.getValue()),
    PML("19", "pml", TestTypeClassification.NO_CERTIFICATE.getValue()),
    PMS("19", "pms", TestTypeClassification.NO_CERTIFICATE.getValue()),
    P6L("21", "p6l", TestTypeClassification.NO_CERTIFICATE.getValue()),
    P6S("21", "p6s", TestTypeClassification.NO_CERTIFICATE.getValue()),
    P2L("22", "p2l", TestTypeClassification.NO_CERTIFICATE.getValue()),
    P2S("22", "p2s", TestTypeClassification.NO_CERTIFICATE.getValue()),
//    P3L("14", "p3l", TestTypeClassification.NO_CERTIFICATE.getValue()),
    PGL("23", "pgl", TestTypeClassification.NO_CERTIFICATE.getValue()),
    PGS("23", "pgs", TestTypeClassification.NO_CERTIFICATE.getValue()),
    PRL("16", "prl", TestTypeClassification.NO_CERTIFICATE.getValue()),
    PRS("16", "prs", TestTypeClassification.NO_CERTIFICATE.getValue()),
    WIL("28", "wil", TestTypeClassification.NO_CERTIFICATE.getValue()),
    WIS("28", "wis", TestTypeClassification.NO_CERTIFICATE.getValue()),
    WEL("93", "wfl", TestTypeClassification.NO_CERTIFICATE.getValue()),
    WES("93", "wfs", TestTypeClassification.NO_CERTIFICATE.getValue()),
    QAL("30", "qal", TestTypeClassification.NO_CERTIFICATE.getValue()),
    QAS("30", "qas", TestTypeClassification.NO_CERTIFICATE.getValue()),
    QGL("30", "qgl", TestTypeClassification.NO_CERTIFICATE.getValue()),
    QGS("30", "qgs", TestTypeClassification.NO_CERTIFICATE.getValue()),
    QDL("31", "qdl", TestTypeClassification.NO_CERTIFICATE.getValue()),
    QDS("31", "qds", TestTypeClassification.NO_CERTIFICATE.getValue()),
    QCL("32", "qcL", TestTypeClassification.NO_CERTIFICATE.getValue()),
    QCS("32", "qcs", TestTypeClassification.NO_CERTIFICATE.getValue()),
    QBL("33", "qbl", TestTypeClassification.NO_CERTIFICATE.getValue()),
    QBS("33", "qbs", TestTypeClassification.NO_CERTIFICATE.getValue()),
    BIF_PSV("34", "bif", TestTypeClassification.NO_CERTIFICATE.getValue()),
//    MDA("35", "mda", TestTypeClassification.NO_CERTIFICATE.getValue()),
//    MDU("35", "mdu", TestTypeClassification.NO_CERTIFICATE.getValue()),
    TEL("36", "tel", TestTypeClassification.NO_CERTIFICATE.getValue()),
    TES("36", "tes", TestTypeClassification.NO_CERTIFICATE.getValue()),
    NFL("38", "nfl", TestTypeClassification.NO_CERTIFICATE.getValue()),
    NFS("38", "nfs", TestTypeClassification.NO_CERTIFICATE.getValue()),
//    LCP("39", "lcp", TestTypeClassification.NO_CERTIFICATE.getValue()),
    LBP("39", "lbp", TestTypeClassification.NO_CERTIFICATE.getValue());





    private final String id;
    private final String testCode;
    private final String classification;

    TestTypes(String id, String testCode, String classification) {
        this.id = id;
        this.testCode = testCode;
        this.classification = classification;
    }

    public String getTestCode() {
        return testCode;
    }

    public String getClassification() {
        return classification;
    }

    public String getId() {
        return id;
    }
}