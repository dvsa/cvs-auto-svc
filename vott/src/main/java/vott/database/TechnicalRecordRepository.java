package vott.database;

import vott.database.connection.ConnectionFactory;
import vott.models.dao.TechnicalRecord;
import vott.sqlgeneration.TableDetails;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TechnicalRecordRepository extends AbstractRepository<TechnicalRecord> {
    public TechnicalRecordRepository(ConnectionFactory connectionFactory) { super(connectionFactory); }

    @Override
    protected TableDetails getTableDetails() {

        TableDetails tableDetails = new TableDetails();

        tableDetails.setTableName("technical_record");
        tableDetails.setColumnNames(new String[] {
                "vehicle_id",
                "recordCompleteness",
                "createdAt",
                "lastUpdatedAt",
                "make_model_id",
                "functionCode",
                "offRoad",
                "numberOfWheelsDriven",
                "emissionsLimit",
                "departmentalVehicleMarker",
                "alterationMarker",
                "vehicle_class_id",
                "variantVersionNumber",
                "grossEecWeight",
                "trainEecWeight",
                "maxTrainEecWeight",
                "applicant_detail_id",
                "purchaser_detail_id",
                "manufacturer_detail_id",
                "manufactureYear",
                "regnDate",
                "firstUseDate",
                "coifDate",
                "ntaNumber",
                "coifSerialNumber",
                "coifCertifierName",
                "approvalType",
                "approvalTypeNumber",
                "variantNumber",
                "conversionRefNo",
                "seatsLowerDeck",
                "seatsUpperDeck",
                "standingCapacity",
                "speedRestriction",
                "speedLimiterMrk",
                "tachoExemptMrk",
                "dispensations",
                "remarks",
                "reasonForCreation",
                "statusCode",
                "unladenWeight",
                "grossKerbWeight",
                "grossLadenWeight",
                "grossGbWeight",
                "grossDesignWeight",
                "trainGbWeight",
                "trainDesignWeight",
                "maxTrainGbWeight",
                "maxTrainDesignWeight",
                "maxLoadOnCoupling",
                "frameDescription",
                "tyreUseCode",
                "roadFriendly",
                "drawbarCouplingFitted",
                "euroStandard",
                "suspensionType",
                "couplingType",
                "length",
                "height",
                "width",
                "frontAxleTo5thWheelMin",
                "frontAxleTo5thWheelMax",
                "frontAxleTo5thWheelCouplingMin",
                "frontAxleTo5thWheelCouplingMax",
                "frontAxleToRearAxle",
                "rearAxleToRearTrl",
                "couplingCenterToRearAxleMin",
                "couplingCenterToRearAxleMax",
                "couplingCenterToRearTrlMin",
                "couplingCenterToRearTrlMax",
                "centreOfRearmostAxleToRearOfTrl",
                "notes",
                "purchaserNotes",
                "manufacturerNotes",
                "noOfAxles",
                "brakeCode",
                "brakes_dtpNumber",
                "brakes_loadSensingValve",
                "brakes_antilockBrakingSystem",
                "createdBy_Id",
                "lastUpdatedBy_Id",
                "updateType",
                "numberOfSeatbelts",
                "seatbeltInstallationApprovalDate",
        });

        return tableDetails;
    }

    @Override
    protected void setParameters(PreparedStatement preparedStatement, TechnicalRecord entity) throws SQLException {
        // 1-indexed
        preparedStatement.setString(1, entity.getVehicleID());
        preparedStatement.setString(2, entity.getRecordCompleteness());
        preparedStatement.setString(3, entity.getCreatedAt());
        preparedStatement.setString(4, entity.getLastUpdatedAt());
        preparedStatement.setString(5, entity.getMakeModelID());
        preparedStatement.setString(6, entity.getFunctionCode());
        preparedStatement.setString(7, entity.getOffRoad());
        preparedStatement.setString(8, entity.getNumberOfWheelsDriven());
        preparedStatement.setString(9, entity.getEmissionsLimit());
        preparedStatement.setString(10, entity.getDepartmentalVehicleMarker());
        preparedStatement.setString(11, entity.getAlterationMarker());
        preparedStatement.setString(12, entity.getVehicleClassID());
        preparedStatement.setString(13, entity.getVariantVersionNumber());
        preparedStatement.setString(14, entity.getGrossEecWeight());
        preparedStatement.setString(15, entity.getTrainEecWeight());
        preparedStatement.setString(16, entity.getMaxTrainEecWeight());
        preparedStatement.setString(17, entity.getApplicantDetailID());
        preparedStatement.setString(18, entity.getPurchaserDetailID());
        preparedStatement.setString(19, entity.getManufacturerDetailID());
        preparedStatement.setString(20, entity.getManufactureYear());
        preparedStatement.setString(21, entity.getRegnDate());
        preparedStatement.setString(22, entity.getFirstUseDate());
        preparedStatement.setString(23, entity.getCoifDate());
        preparedStatement.setString(24, entity.getNtaNumber());
        preparedStatement.setString(25, entity.getCoifSerialNumber());
        preparedStatement.setString(26, entity.getCoifCertifierName());
        preparedStatement.setString(27, entity.getApprovalType());
        preparedStatement.setString(28, entity.getApprovalTypeNumber());
        preparedStatement.setString(29, entity.getVariantNumber());
        preparedStatement.setString(30, entity.getConversionRefNo());
        preparedStatement.setString(31, entity.getSeatsLowerDeck());
        preparedStatement.setString(32, entity.getSeatsUpperDeck());
        preparedStatement.setString(33, entity.getStandingCapacity());
        preparedStatement.setString(34, entity.getSpeedRestriction());
        preparedStatement.setString(35, entity.getSpeedLimiterMrk());
        preparedStatement.setString(36, entity.getTachoExemptMrk());
        preparedStatement.setString(37, entity.getDispensations());
        preparedStatement.setString(38, entity.getRemarks());
        preparedStatement.setString(39, entity.getReasonForCreation());
        preparedStatement.setString(40, entity.getStatusCode());
        preparedStatement.setString(41, entity.getUnladenWeight());
        preparedStatement.setString(42, entity.getGrossKerbWeight());
        preparedStatement.setString(43, entity.getGrossLadenWeight());
        preparedStatement.setString(44, entity.getGrossGbWeight());
        preparedStatement.setString(45, entity.getGrossDesignWeight());
        preparedStatement.setString(46, entity.getTrainGbWeight());
        preparedStatement.setString(47, entity.getTrainDesignWeight());
        preparedStatement.setString(48, entity.getMaxTrainGbWeight());
        preparedStatement.setString(49, entity.getMaxTrainDesignWeight());
        preparedStatement.setString(50, entity.getMaxLoadOnCoupling());
        preparedStatement.setString(51, entity.getFrameDescription());
        preparedStatement.setString(52, entity.getTyreUseCode());
        preparedStatement.setString(53, entity.getRoadFriendly());
        preparedStatement.setString(54, entity.getDrawbarCouplingFitted());
        preparedStatement.setString(55, entity.getEuroStandard());
        preparedStatement.setString(56, entity.getSuspensionType());
        preparedStatement.setString(57, entity.getCouplingType());
        preparedStatement.setString(58, entity.getLength());
        preparedStatement.setString(59, entity.getHeight());
        preparedStatement.setString(60, entity.getWidth());
        preparedStatement.setString(61, entity.getFrontAxleTo5thWheelMin());
        preparedStatement.setString(62, entity.getFrontAxleTo5thWheelMax());
        preparedStatement.setString(63, entity.getFrontAxleTo5thWheelCouplingMin());
        preparedStatement.setString(64, entity.getFrontAxleTo5thWheelCouplingMax());
        preparedStatement.setString(65, entity.getFrontAxleToRearAxle());
        preparedStatement.setString(66, entity.getRearAxleToRearTrl());
        preparedStatement.setString(67, entity.getCouplingCenterToRearAxleMin());
        preparedStatement.setString(68, entity.getCouplingCenterToRearAxleMax());
        preparedStatement.setString(69, entity.getCouplingCenterToRearTrlMin());
        preparedStatement.setString(70, entity.getCouplingCenterToRearTrlMax());
        preparedStatement.setString(71, entity.getCentreOfRearmostAxleToRearOfTrl());
        preparedStatement.setString(72, entity.getNotes());
        preparedStatement.setString(73, entity.getPurchaserNotes());
        preparedStatement.setString(74, entity.getManufacturerNotes());
        preparedStatement.setString(75, entity.getNoOfAxles());
        preparedStatement.setString(76, entity.getBrakeCode());
        preparedStatement.setString(77, entity.getBrakes_dtpNumber());
        preparedStatement.setString(78, entity.getBrakes_loadSensingValve());
        preparedStatement.setString(79, entity.getBrakes_antilockBrakingSystem());
        preparedStatement.setString(80, entity.getCreatedByID());
        preparedStatement.setString(81, entity.getLastUpdatedByID());
        preparedStatement.setString(82, entity.getUpdateType());
        preparedStatement.setString(83, entity.getNumberOfSeatbelts());
        preparedStatement.setString(84, entity.getSeatbeltInstallationApprovalDate());

    }

    @Override
    protected void setParametersFull(PreparedStatement preparedStatement, TechnicalRecord entity) throws SQLException {
        setParameters(preparedStatement, entity);

        preparedStatement.setString(85, entity.getVehicleID());
        preparedStatement.setString(86, entity.getRecordCompleteness());
        preparedStatement.setString(87, entity.getCreatedAt());
        preparedStatement.setString(88, entity.getLastUpdatedAt());
        preparedStatement.setString(89, entity.getMakeModelID());
        preparedStatement.setString(90, entity.getFunctionCode());
        preparedStatement.setString(91, entity.getOffRoad());
        preparedStatement.setString(92, entity.getNumberOfWheelsDriven());
        preparedStatement.setString(93, entity.getEmissionsLimit());
        preparedStatement.setString(94, entity.getDepartmentalVehicleMarker());
        preparedStatement.setString(95, entity.getAlterationMarker());
        preparedStatement.setString(96, entity.getVehicleClassID());
        preparedStatement.setString(97, entity.getVariantVersionNumber());
        preparedStatement.setString(98, entity.getGrossEecWeight());
        preparedStatement.setString(99, entity.getTrainEecWeight());
        preparedStatement.setString(100, entity.getMaxTrainEecWeight());
        preparedStatement.setString(101, entity.getApplicantDetailID());
        preparedStatement.setString(102, entity.getPurchaserDetailID());
        preparedStatement.setString(103, entity.getManufacturerDetailID());
        preparedStatement.setString(104, entity.getManufactureYear());
        preparedStatement.setString(105, entity.getRegnDate());
        preparedStatement.setString(106, entity.getFirstUseDate());
        preparedStatement.setString(107, entity.getCoifDate());
        preparedStatement.setString(108, entity.getNtaNumber());
        preparedStatement.setString(109, entity.getCoifSerialNumber());
        preparedStatement.setString(110, entity.getCoifCertifierName());
        preparedStatement.setString(111, entity.getApprovalType());
        preparedStatement.setString(112, entity.getApprovalTypeNumber());
        preparedStatement.setString(113, entity.getVariantNumber());
        preparedStatement.setString(114, entity.getConversionRefNo());
        preparedStatement.setString(115, entity.getSeatsLowerDeck());
        preparedStatement.setString(116, entity.getSeatsUpperDeck());
        preparedStatement.setString(117, entity.getStandingCapacity());
        preparedStatement.setString(118, entity.getSpeedRestriction());
        preparedStatement.setString(119, entity.getSpeedLimiterMrk());
        preparedStatement.setString(120, entity.getTachoExemptMrk());
        preparedStatement.setString(121, entity.getDispensations());
        preparedStatement.setString(122, entity.getRemarks());
        preparedStatement.setString(123, entity.getReasonForCreation());
        preparedStatement.setString(124, entity.getStatusCode());
        preparedStatement.setString(125, entity.getUnladenWeight());
        preparedStatement.setString(126, entity.getGrossKerbWeight());
        preparedStatement.setString(127, entity.getGrossLadenWeight());
        preparedStatement.setString(128, entity.getGrossGbWeight());
        preparedStatement.setString(129, entity.getGrossDesignWeight());
        preparedStatement.setString(130, entity.getTrainGbWeight());
        preparedStatement.setString(131, entity.getTrainDesignWeight());
        preparedStatement.setString(132, entity.getMaxTrainGbWeight());
        preparedStatement.setString(133, entity.getMaxTrainDesignWeight());
        preparedStatement.setString(134, entity.getMaxLoadOnCoupling());
        preparedStatement.setString(135, entity.getFrameDescription());
        preparedStatement.setString(136, entity.getTyreUseCode());
        preparedStatement.setString(137, entity.getRoadFriendly());
        preparedStatement.setString(138, entity.getDrawbarCouplingFitted());
        preparedStatement.setString(139, entity.getEuroStandard());
        preparedStatement.setString(140, entity.getSuspensionType());
        preparedStatement.setString(141, entity.getCouplingType());
        preparedStatement.setString(142, entity.getLength());
        preparedStatement.setString(143, entity.getHeight());
        preparedStatement.setString(144, entity.getWidth());
        preparedStatement.setString(145, entity.getFrontAxleTo5thWheelMin());
        preparedStatement.setString(146, entity.getFrontAxleTo5thWheelMax());
        preparedStatement.setString(147, entity.getFrontAxleTo5thWheelCouplingMin());
        preparedStatement.setString(148, entity.getFrontAxleTo5thWheelCouplingMax());
        preparedStatement.setString(149, entity.getFrontAxleToRearAxle());
        preparedStatement.setString(150, entity.getRearAxleToRearTrl());
        preparedStatement.setString(151, entity.getCouplingCenterToRearAxleMin());
        preparedStatement.setString(152, entity.getCouplingCenterToRearAxleMax());
        preparedStatement.setString(153, entity.getCouplingCenterToRearTrlMin());
        preparedStatement.setString(154, entity.getCouplingCenterToRearTrlMax());
        preparedStatement.setString(155, entity.getCentreOfRearmostAxleToRearOfTrl());
        preparedStatement.setString(156, entity.getNotes());
        preparedStatement.setString(157, entity.getPurchaserNotes());
        preparedStatement.setString(158, entity.getManufacturerNotes());
        preparedStatement.setString(159, entity.getNoOfAxles());
        preparedStatement.setString(160, entity.getBrakeCode());
        preparedStatement.setString(161, entity.getBrakes_dtpNumber());
        preparedStatement.setString(162, entity.getBrakes_loadSensingValve());
        preparedStatement.setString(163, entity.getBrakes_antilockBrakingSystem());
        preparedStatement.setString(164, entity.getCreatedByID());
        preparedStatement.setString(165, entity.getLastUpdatedByID());
        preparedStatement.setString(166, entity.getUpdateType());
        preparedStatement.setString(167, entity.getNumberOfSeatbelts());
        preparedStatement.setString(168, entity.getSeatbeltInstallationApprovalDate());
    }

    @Override
    protected TechnicalRecord mapToEntity(ResultSet rs) throws SQLException {
        TechnicalRecord tr = new TechnicalRecord();

        tr.setVehicleID(rs.getString("vehicle_id"));
        tr.setRecordCompleteness(rs.getString("recordCompleteness"));
        tr.setCreatedAt(rs.getString("createdAt"));
        tr.setLastUpdatedAt(rs.getString("lastUpdatedAt"));
        tr.setMakeModelID(rs.getString("make_model_id"));
        tr.setFunctionCode(rs.getString("functionCode"));
        tr.setOffRoad(rs.getString("offRoad"));
        tr.setNumberOfWheelsDriven(rs.getString("numberOfWheelsDriven"));
        tr.setEmissionsLimit(rs.getString("emissionsLimit"));
        tr.setDepartmentalVehicleMarker(rs.getString("departmentalVehicleMarker"));
        tr.setAlterationMarker(rs.getString("alterationMarker"));
        tr.setVehicleClassID(rs.getString("vehicle_class_id"));
        tr.setVariantNumber(rs.getString("variantVersionNumber"));
        tr.setGrossEecWeight(rs.getString("grossEecWeight"));
        tr.setTrainEecWeight(rs.getString("trainEecWeight"));
        tr.setMaxTrainEecWeight(rs.getString("maxTrainEecWeight"));
        tr.setApplicantDetailID(rs.getString("applicant_detail_id"));
        tr.setPurchaserDetailID(rs.getString("purchaser_detail_id"));
        tr.setManufacturerDetailID(rs.getString("manufacturer_detail_id"));
        tr.setManufactureYear(rs.getString("manufactureYear"));
        tr.setRegnDate(rs.getString("regnDate"));
        tr.setFirstUseDate(rs.getString("firstUseDate"));
        tr.setCoifDate(rs.getString("coifDate"));
        tr.setNtaNumber(rs.getString("ntaNumber"));
        tr.setCoifSerialNumber(rs.getString("coifSerialNumber"));
        tr.setCoifCertifierName(rs.getString("coifCertifierName"));
        tr.setApprovalType(rs.getString("approvalType"));
        tr.setApprovalTypeNumber(rs.getString("approvalTypeNumber"));
        tr.setVariantNumber(rs.getString("variantNumber"));
        tr.setConversionRefNo(rs.getString("conversionRefNo"));
        tr.setSeatsLowerDeck(rs.getString("seatsLowerDeck"));
        tr.setSeatsUpperDeck(rs.getString("seatsUpperDeck"));
        tr.setStandingCapacity(rs.getString("standingCapacity"));
        tr.setSpeedRestriction(rs.getString("speedRestriction"));
        tr.setSpeedLimiterMrk(rs.getString("speedLimiterMrk"));
        tr.setTachoExemptMrk(rs.getString("tachoExemptMrk"));
        tr.setDispensations(rs.getString("dispensations"));
        tr.setRemarks(rs.getString("remarks"));
        tr.setReasonForCreation(rs.getString("reasonForCreation"));
        tr.setStatusCode(rs.getString("statusCode"));
        tr.setUnladenWeight(rs.getString("unladenWeight"));
        tr.setGrossKerbWeight(rs.getString("grossKerbWeight"));
        tr.setGrossLadenWeight(rs.getString("grossLadenWeight"));
        tr.setGrossGbWeight(rs.getString("grossGbWeight"));
        tr.setGrossDesignWeight(rs.getString("grossDesignWeight"));
        tr.setTrainGbWeight(rs.getString("trainGbWeight"));
        tr.setTrainDesignWeight(rs.getString("trainDesignWeight"));
        tr.setMaxTrainGbWeight(rs.getString("maxTrainGbWeight"));
        tr.setMaxTrainDesignWeight(rs.getString("maxTrainDesignWeight"));
        tr.setMaxLoadOnCoupling(rs.getString("maxLoadOnCoupling"));
        tr.setFrameDescription(rs.getString("frameDescription"));
        tr.setTyreUseCode(rs.getString("tyreUseCode"));
        tr.setRoadFriendly(rs.getString("roadFriendly"));
        tr.setDrawbarCouplingFitted(rs.getString("drawbarCouplingFitted"));
        tr.setEuroStandard(rs.getString("euroStandard"));
        tr.setSuspensionType(rs.getString("suspensionType"));
        tr.setCouplingType(rs.getString("couplingType"));
        tr.setLength(rs.getString("length"));
        tr.setHeight(rs.getString("height"));
        tr.setWidth(rs.getString("width"));
        tr.setFrontAxleTo5thWheelCouplingMin(rs.getString("frontAxleTo5thWheelMin"));
        tr.setFrontAxleTo5thWheelCouplingMax(rs.getString("frontAxleTo5thWheelMax"));
        tr.setFrontAxleTo5thWheelCouplingMin(rs.getString("frontAxleTo5thWheelCouplingMin"));
        tr.setFrontAxleTo5thWheelCouplingMax(rs.getString("frontAxleTo5thWheelCouplingMax"));
        tr.setFrontAxleToRearAxle(rs.getString("frontAxleToRearAxle"));
        tr.setRearAxleToRearTrl(rs.getString("rearAxleToRearTrl"));
        tr.setCouplingCenterToRearAxleMin(rs.getString("couplingCenterToRearAxleMin"));
        tr.setCouplingCenterToRearAxleMax(rs.getString("couplingCenterToRearAxleMax"));
        tr.setCouplingCenterToRearAxleMin(rs.getString("couplingCenterToRearTrlMin"));
        tr.setCouplingCenterToRearAxleMax(rs.getString("couplingCenterToRearTrlMax"));
        tr.setCentreOfRearmostAxleToRearOfTrl(rs.getString("centreOfRearmostAxleToRearOfTrl"));
        tr.setNotes(rs.getString("notes"));
        tr.setPurchaserNotes(rs.getString("purchaserNotes"));
        tr.setManufacturerNotes(rs.getString("manufacturerNotes"));
        tr.setNoOfAxles(rs.getString("noOfAxles"));
        tr.setBrakeCode(rs.getString("brakeCode"));
        tr.setBrakes_dtpNumber(rs.getString("brakes_dtpNumber"));
        tr.setBrakes_loadSensingValve(rs.getString("brakes_loadSensingValve"));
        tr.setBrakes_antilockBrakingSystem(rs.getString("brakes_antilockBrakingSystem"));
        tr.setCreatedByID(rs.getString("createdBy_Id"));
        tr.setLastUpdatedByID(rs.getString("lastUpdatedBy_Id"));
        tr.setUpdateType(rs.getString("updateType"));
        tr.setNumberOfSeatbelts(rs.getString("numberOfSeatbelts"));
        tr.setSeatbeltInstallationApprovalDate(rs.getString("seatbeltInstallationApprovalDate"));

        return tr;
    }
}
