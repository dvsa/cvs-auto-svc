SET @OLD_UNIQUE_CHECKS = @@UNIQUE_CHECKS, UNIQUE_CHECKS = 0;
SET @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS = 0;
SET @OLD_SQL_MODE = @@SQL_MODE, SQL_MODE =
        'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema vott_db
-- -----------------------------------------------------

DROP DATABASE IF EXISTS `vott_db`;

CREATE DATABASE IF NOT EXISTS `vott_db` DEFAULT CHARACTER SET utf8;
USE `vott_db`;

-- -----------------------------------------------------
-- Table `vott_db`.`vehicle`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `vott_db`.`vehicle`
(
    `id`             INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `system_number`  VARCHAR(30)  NOT NULL,
    `vin`            VARCHAR(21),
    `vrm_trm`        VARCHAR(9)   NULL,
    `trailer_id`     VARCHAR(8)   NULL,
    `effective_from` DATE         NOT NULL,
    `effect_to`      DATE         NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `idx_system_number_uq` (`system_number` ASC)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `vott_db`.`make_model`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `vott_db`.`make_model`
(
    `id`                   INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `make`                 VARCHAR(30)  NULL,
    `model`                VARCHAR(30)  NULL,
    `chassisMake`          VARCHAR(20),
    `chassisModel`         VARCHAR(20),
    `bodyMake`             VARCHAR(20),
    `bodyModel`            VARCHAR(20),
    `modelLiteral`         VARCHAR(30),
    `bodyTypeCode`         CHAR(1),
    `bodyTypeDescription`  VARCHAR(17),
    `fuelPropulsionSystem` VARCHAR(5),
    `approvalType`         VARCHAR(3),
    `approvalTypeNumber`   VARCHAR(25),
    `variantNumber`        VARCHAR(25),
    `fingerprint`          VARCHAR(32)  NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `idx_fingerprint_uq` (`fingerprint` ASC)
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `vott_db`.`vehicle_class`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `vott_db`.`vehicle_class`
(
    `id`                   INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `code`                 CHAR(1)      NULL,
    `description`          VARCHAR(46)  NULL,
    `vehicleType`          VARCHAR(10)  NULL,
    `vehicleSize`          VARCHAR(5)   NULL,
    `vehicleConfiguration` VARCHAR(20)  NULL,
    `euVehicleCategory`    VARCHAR(5)   NULL,
    `fingerprint`          VARCHAR(32)  NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `idx_fingerprint_uq` (`fingerprint` ASC)
)
    ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `vott_db`.`vehicle_subclass`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `vott_db`.`vehicle_subclass`
(
    `id`               INT         NOT NULL AUTO_INCREMENT,
    `vehicle_class_id` INT UNSIGNED,
    `subclass`         VARCHAR(199),
    `fingerprint`      VARCHAR(32) NOT NULL,
    PRIMARY KEY (`id`),

    UNIQUE INDEX `idx_fingerprint_uq` (`fingerprint` ASC),
    FOREIGN KEY (`vehicle_class_id`)
        REFERENCES `vott_db`.`vehicle_class` (`id`)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `vott_db`.`identity`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `vott_db`.`identity`
(
    `id`          INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `identityId`  VARCHAR(199),
    `name`        VARCHAR(199),
    `fingerprint` VARCHAR(32)  NOT NULL,
    PRIMARY KEY (`id`),

    UNIQUE INDEX `idx_fingerprint_uq` (`fingerprint` ASC),
    INDEX `idx_name` (`name` ASC)
)
    ENGINE = InnoDB;



-- -- -----------------------------------------------------
-- -- Table `vott_db`.`contact_details`
-- -- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `vott_db`.`contact_details`
(
    `id`              INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `name`            VARCHAR(150),
    `address1`        VARCHAR(60),
    `address2`        VARCHAR(60),
    `postTown`        VARCHAR(60),
    `address3`        VARCHAR(60),
    `postCode`        VARCHAR(12),
    `emailAddress`    VARCHAR(255),
    `telephoneNumber` VARCHAR(25),
    `faxNumber`       VARCHAR(25),
    `notes`           VARCHAR(1024),
    `fingerprint`     VARCHAR(32)  NOT NULL,
    PRIMARY KEY (`id`),

    UNIQUE INDEX `idx_fingerprint_uq` (`fingerprint` ASC)
)
    ENGINE = InnoDB;

-- -- -----------------------------------------------------
-- -- Table `vott_db`.``
-- -- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `vott_db`.`brakes`
(
    `id`                    INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `brakeCodeOriginal`     VARCHAR(3),
    `brakeCode`             VARCHAR(6),
    `dataTrBrakeOne`        VARCHAR(60),
    `dataTrBrakeTwo`        VARCHAR(60),
    `dataTrBrakeThree`      VARCHAR(60),
    `retarderBrakeOne`      VARCHAR(9),
    `retarderBrakeTwo`      VARCHAR(9),
    `dtpNumber`             VARCHAR(6),
    `loadSensingValve`      TINYINT(1),
    `antilockBrakingSystem` TINYINT(1),
    `serviceBrakeForceA`    MEDIUMINT UNSIGNED,
    `secondaryBrakeForceA`  MEDIUMINT UNSIGNED,
    `parkingBrakeForceA`    MEDIUMINT UNSIGNED,
    `serviceBrakeForceB`    MEDIUMINT UNSIGNED,
    `secondaryBrakeForceB`  MEDIUMINT UNSIGNED,
    `parkingBrakeForceB`    MEDIUMINT UNSIGNED,
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `vott_db`.`technical_record`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `vott_db`.`technical_record`
(
    `id`                               INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `vehicle_id`                       INT UNSIGNED NOT NULL,
    `recordCompleteness`               VARCHAR(8)   NULL,
    `createdAt`                        DATETIME     NULL,
    `lastUpdatedAt`                    DATETIME     NULL,
    `make_model_id`                    INT UNSIGNED NULL,
    `functionCode`                     CHAR(1)      NULL,
    `offRoad`                          TINYINT(1)   NULL,
    `numberOfWheelsDriven`             INT          NULL,
    `emissionsLimit`                   VARCHAR(45)  NULL,
    `departmentalVehicleMarker`        TINYINT(1)   NULL,
    `alterationMarker`                 TINYINT(1),
    `vehicle_class_id`                 INT UNSIGNED NULL,
    `technical_recordcol`              VARCHAR(45)  NULL,
    `variantVersionNumber`             VARCHAR(35),
    `grossEecWeight`                   MEDIUMINT UNSIGNED,
    `trainEecWeight`                   MEDIUMINT UNSIGNED,
    `maxTrainEecWeight`                MEDIUMINT UNSIGNED,
    `applicant_detail_id`              INT UNSIGNED,
    `purchaser_detail_id`              INT UNSIGNED,
    `manufacturer_detail_id`           INT UNSIGNED,
    `manufactureYear`                  YEAR,
    `regnDate`                         DATE,
    `firstUseDate`                     DATE,
    `coifDate`                         DATE,
    `ntaNumber`                        VARCHAR(40),
    `coifSerialNumber`                 VARCHAR(8),
    `coifCertifierName`                VARCHAR(20),
    `conversionRefNo`                  VARCHAR(10),
    `seatsLowerDeck`                   SMALLINT UNSIGNED,
    `seatsUpperDeck`                   TINYINT UNSIGNED,
    `standingCapacity`                 SMALLINT UNSIGNED,
    `speedRestriction`                 TINYINT UNSIGNED,
    `speedLimiterMrk`                  TINYINT(1),
    `tachoExemptMrk`                   TINYINT(1),
    `dispensations`                    VARCHAR(160),
    `remarks`                          VARCHAR(1024),
    `reasonForCreation`                VARCHAR(100),
    `statusCode`                       VARCHAR(11),
    `unladenWeight`                    MEDIUMINT UNSIGNED,
    `grossKerbWeight`                  MEDIUMINT UNSIGNED,
    `grossLadenWeight`                 MEDIUMINT UNSIGNED,
    `grossGbWeight`                    MEDIUMINT UNSIGNED,
    `grossDesignWeight`                MEDIUMINT UNSIGNED,
    `trainGbWeight`                    MEDIUMINT UNSIGNED,
    `trainDesignWeight`                MEDIUMINT UNSIGNED,
    `maxTrainGbWeight`                 MEDIUMINT UNSIGNED,
    `maxTrainDesignWeight`             MEDIUMINT UNSIGNED,
    `maxLoadOnCoupling`                MEDIUMINT UNSIGNED,
    `frameDescription`                 VARCHAR(15),
    `tyreUseCode`                      VARCHAR(2),
    `roadFriendly`                     TINYINT(1),
    `drawbarCouplingFitted`            TINYINT(1),
    `euroStandard`                     VARCHAR(2),
    `suspensionType`                   CHAR(1),
    `couplingType`                     CHAR(1),
    `length`                           MEDIUMINT UNSIGNED,
    `height`                           MEDIUMINT UNSIGNED,
    `width`                            MEDIUMINT UNSIGNED,
    `frontAxleTo5thWheelMin`           MEDIUMINT UNSIGNED,
    `frontAxleTo5thWheelMax`           MEDIUMINT UNSIGNED,
    `frontAxleTo5thWheelCouplingMin`   MEDIUMINT UNSIGNED,
    `frontAxleTo5thWheelCouplingMax`   MEDIUMINT UNSIGNED,
    `frontAxleToRearAxle`              MEDIUMINT UNSIGNED,
    `rearAxleToRearTrl`                MEDIUMINT UNSIGNED,
    `couplingCenterToRearAxleMin`      MEDIUMINT UNSIGNED,
    `couplingCenterToRearAxleMax`      MEDIUMINT UNSIGNED,
    `couplingCenterToRearTrlMin`       MEDIUMINT UNSIGNED,
    `couplingCenterToRearTrlMax`       MEDIUMINT UNSIGNED,
    `centreOfRearmostAxleToRearOfTrl`  MEDIUMINT UNSIGNED,
    `notes`                            VARCHAR(199),
    `noOfAxles`                        TINYINT(1),
    `brakeCode`                        VARCHAR(6),
    `createdBy_Id`                     INT UNSIGNED,
    `lastUpdatedBy_Id`                 INT UNSIGNED,
    `updateType`                       VARCHAR(16),
    `numberOfSeatbelts`                VARCHAR(99),
    `seatbeltInstallationApprovalDate` DATE,
    `brakes_id`                        INT UNSIGNED,
    PRIMARY KEY (`id`),

    FOREIGN KEY (`vehicle_id`)
        REFERENCES `vott_db`.`vehicle` (`id`)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION,

    FOREIGN KEY (`applicant_detail_id`)
        REFERENCES `vott_db`.`contact_details` (`id`)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION,

    FOREIGN KEY (`purchaser_detail_id`)
        REFERENCES `vott_db`.`contact_details` (`id`)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION,

    FOREIGN KEY (`manufacturer_detail_id`)
        REFERENCES `vott_db`.`contact_details` (`id`)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION,

    FOREIGN KEY (`make_model_id`)
        REFERENCES `vott_db`.`make_model` (`id`)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION,

    FOREIGN KEY (`vehicle_class_id`)
        REFERENCES `vott_db`.`vehicle_class` (`id`)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION,

    FOREIGN KEY (`createdBy_Id`)
        REFERENCES `vott_db`.`identity` (`id`)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION,

    FOREIGN KEY (`lastUpdatedBy_Id`)
        REFERENCES `vott_db`.`identity` (`id`)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION,

    FOREIGN KEY (`brakes_id`)
        REFERENCES `vott_db`.`brakes` (`id`)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION,

    INDEX `idx_vehicle_id` (`vehicle_id` ASC),
    INDEX `idx_make_model_id` (`make_model_id` ASC),
    INDEX `idx_vehicle_class_id` (`vehicle_class_id` ASC),
    INDEX `idx_createdBy_Id` (`createdBy_Id` ASC),
    INDEX `idx_lastUpdatedBy_Id` (`lastUpdatedBy_Id` ASC)
)
    ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `vott_db`.`axle_spacing`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `vott_db`.`axle_spacing`
(
    `id`                  INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `technical_record_id` INT UNSIGNED,
    `axles`               VARCHAR(199),
    `value`               MEDIUMINT UNSIGNED,
    PRIMARY KEY (`id`),

    FOREIGN KEY (`technical_record_id`)
        REFERENCES `vott_db`.`technical_record` (`id`)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION,

    INDEX `idx_technical_record_id` (`technical_record_id` ASC)
)
    ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `vott_db`.`microfilm`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `vott_db`.`microfilm`
(
    `id`                    INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `technical_record_id`   INT UNSIGNED,
    `microfilmDocumentType` VARCHAR(31),
    `microfilmRollNumber`   VARCHAR(5),
    `microfilmSerialNumber` VARCHAR(4),
    PRIMARY KEY (`id`),

    FOREIGN KEY (`technical_record_id`)
        REFERENCES `vott_db`.`technical_record` (`id`)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION,

    INDEX `idx_technical_record_id` (`technical_record_id` ASC)
)
    ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `vott_db`.`plate`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `vott_db`.`plate`
(
    `id`                  INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `technical_record_id` INT UNSIGNED,
    `plateSerialNumber`   VARCHAR(12),
    `plateIssueDate`      DATE,
    `plateReasonForIssue` VARCHAR(16),
    `plateIssuer`         VARCHAR(150),
    PRIMARY KEY (`id`),
    FOREIGN KEY (`technical_record_id`)
        REFERENCES `vott_db`.`technical_record` (`id`)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION,

    INDEX `idx_technical_record_id` (`technical_record_id` ASC)
)
    ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `vott_db`.`fuel_emission`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `vott_db`.`fuel_emission`
(
    `id`               INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `modTypeCode`      CHAR(1)      NULL,
    `description`      VARCHAR(32)  NULL,
    `emissionStandard` VARCHAR(21)  NULL,
    `fuelType`         VARCHAR(13)  NULL,
    `fingerprint`      VARCHAR(32)  NOT NULL,
    PRIMARY KEY (`id`),

    UNIQUE INDEX `idx_fingerprint_uq` (`fingerprint` ASC)
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `vott_db`.`test_station`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `vott_db`.`test_station`
(
    `id`          INT UNSIGNED  NOT NULL AUTO_INCREMENT,
    `pNumber`     VARCHAR(20)   NULL,
    `name`        VARCHAR(1000) NULL,
    `type`        VARCHAR(4)    NULL,
    `fingerprint` VARCHAR(32)   NOT NULL,
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `vott_db`.`tester`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `vott_db`.`tester`
(
    `id`            INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `staff_id`      VARCHAR(9)   NULL,
    `name`          VARCHAR(60)  NULL,
    `email_address` VARCHAR(254) NULL,
    `fingerprint`   VARCHAR(32)  NOT NULL,
    PRIMARY KEY (`id`),

    UNIQUE INDEX `idx_fingerprint_uq` (`fingerprint` ASC)
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `vott_db`.`preparer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `vott_db`.`preparer`
(
    `id`          INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `preparerId`  VARCHAR(9)   NULL,
    `name`        VARCHAR(60)  NULL,
    `fingerprint` VARCHAR(32)  NOT NULL,
    PRIMARY KEY (`id`),

    UNIQUE INDEX `idx_fingerprint_uq` (`fingerprint` ASC)
)
    ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `vott_db`.`test_type`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `vott_db`.`test_type`
(
    `id`                     INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `testTypeClassification` VARCHAR(23),
    `testTypeName`           VARCHAR(199),
    `fingerprint`            VARCHAR(32)  NOT NULL,
    PRIMARY KEY (`id`),

    UNIQUE INDEX `idx_fingerprint_uq` (`fingerprint` ASC)
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `vott_db`.`test_record`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `vott_db`.`test_record`
(
    `id`                                INT UNSIGNED     NOT NULL AUTO_INCREMENT,
    `technical_record_id`               INT UNSIGNED     NOT NULL,
    `vehicle_id`                        INT UNSIGNED     NOT NULL,
    `fuel_emission_id`                  INT UNSIGNED     NULL,
    `test_station_id`                   INT UNSIGNED     NOT NULL,
    `tester_id`                         INT UNSIGNED     NOT NULL,
    `preparer_id`                       INT UNSIGNED     NOT NULL,
    `vehicle_class_id`                  INT UNSIGNED     NOT NULL,
    `test_type_id`                      INT UNSIGNED     NOT NULL,
    `testStatus`                        VARCHAR(9),
    `reasonForCancellation`             VARCHAR(500)     NULL,
    `numberOfSeats`                     INT              NULL,
    `odometerReading`                   INT UNSIGNED     NULL,
    `odometerReadingUnits`              VARCHAR(10)      NULL,
    `countryOfRegistration`             VARCHAR(56)      NULL,
    `noOfAxles`                         TINYINT UNSIGNED NULL,
    `regnDate`                          DATE             NULL,
    `firstUseDate`                      DATE             NULL,
    `createdAt`                         DATETIME         NULL,
    `lastUpdatedAt`                     DATETIME         NULL,
    `testCode`                          VARCHAR(3)       NULL,
    `testNumber`                        VARCHAR(45)      NULL,
    `certificateNumber`                 VARCHAR(45)      NULL,
    `secondaryCertificateNumber`        VARCHAR(199)     NULL,
    `certificateLink`                   VARCHAR(45)      NULL,
    `testExpiryDate`                    DATE             NULL,
    `testAnniversaryDate`               DATE             NULL,
    `testTypeStartTimestamp`            DATETIME         NULL,
    `testTypeEndTimestamp`              DATETIME         NULL,
    `numberOfSeatbeltsFitted`           TINYINT UNSIGNED NULL,
    `lastSeatbeltInstallationCheckDate` DATE             NULL,
    `seatbeltInstallationCheckDate`     VARCHAR(45)      NULL,
    `testResult`                        VARCHAR(9)       NULL,
    `reasonForAbandoning`               VARCHAR(45)      NULL,
    `additionalNotesRecorded`           VARCHAR(500)     NULL,
    `additionalCommentsForAbandon`      VARCHAR(500)     NULL,
    `particulateTrapFitted`             VARCHAR(100)     NULL,
    `particulateTrapSerialNumber`       VARCHAR(100)     NULL,
    `modificationTypeUsed`              VARCHAR(100)     NULL,
    `smokeTestKLimitApplied`            VARCHAR(100)     NULL,
    `createdBy_Id`                      INT UNSIGNED,
    `lastUpdatedBy_Id`                  INT UNSIGNED,

    PRIMARY KEY (`id`),
    INDEX `idx_technical_record_id` (`technical_record_id` ASC),
    INDEX `idx_vehicle_id` (`vehicle_id` ASC),
    INDEX `idx_fuel_emission_id` (`fuel_emission_id` ASC),
    INDEX `idx_test_station_id` (`test_station_id` ASC),
    INDEX `idx_tester_id` (`tester_id` ASC),
    INDEX `idx_vehicle_class_id` (`vehicle_class_id` ASC),
    INDEX `idx_preparer_id` (`preparer_id` ASC),

    FOREIGN KEY (`technical_record_id`)
        REFERENCES `vott_db`.`technical_record` (`id`)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION,

    FOREIGN KEY (`vehicle_id`)
        REFERENCES `vott_db`.`vehicle` (`id`)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION,

    FOREIGN KEY (`fuel_emission_id`)
        REFERENCES `vott_db`.`fuel_emission` (`id`)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION,

    FOREIGN KEY (`test_station_id`)
        REFERENCES `vott_db`.`test_station` (`id`)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION,

    FOREIGN KEY (`tester_id`)
        REFERENCES `vott_db`.`tester` (`id`)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION,

    FOREIGN KEY (`vehicle_class_id`)
        REFERENCES `vott_db`.`vehicle_class` (`id`)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION,

    FOREIGN KEY (`test_type_id`)
        REFERENCES `vott_db`.`test_type` (`id`)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION,

    FOREIGN KEY (`preparer_id`)
        REFERENCES `vott_db`.`preparer` (`id`)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION,

    FOREIGN KEY (`createdBy_Id`)
        REFERENCES `vott_db`.`identity` (`id`)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION,

    FOREIGN KEY (`lastUpdatedBy_Id`)
        REFERENCES `vott_db`.`identity` (`id`)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
)
    ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `vott_db`.`custom_defect`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `vott_db`.`custom_defect`
(
    `id`              INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `test_record_id`  INT UNSIGNED NOT NULL,
    `referenceNumber` VARCHAR(10),
    `defectName`      VARCHAR(200),
    `defectNotes`     VARCHAR(200),
    PRIMARY KEY (`id`),
    INDEX `idx_technical_record_id` (`test_record_id` ASC),

    FOREIGN KEY (`test_record_id`)
        REFERENCES `vott_db`.`test_record` (`id`)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
)
    ENGINE = InnoDB;
-- -----------------------------------------------------
-- Table `vott_db`.`axles`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `vott_db`.`axles`
(
    `id`                  INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `technical_record_id` INT UNSIGNED NOT NULL,
    `axleNumber`          INT          NOT NULL,
    `parkingBrakeMrk`     TINYINT(1)   NULL,
    `kerbWeight`          INT UNSIGNED NULL,
    `ladenWeight`         INT UNSIGNED NULL,
    `gbWeight`            INT UNSIGNED NULL,
    `eecWeight`           INT UNSIGNED NULL,
    `designWeight`        INT UNSIGNED NULL,
    `tyreSize`            VARCHAR(12)  NULL,
    `plyRating`           VARCHAR(2)   NULL,
    `fitmentCode`         VARCHAR(6)   NULL,
    `dataTrAxles`         VARCHAR(45)  NULL,
    `speedCategorySymbol` VARCHAR(2)   NULL,
    `tyreCode`            INT UNSIGNED NULL,
    `brakeActuator`       INT UNSIGNED NULL,
    `leverLength`         INT UNSIGNED NULL,
    `springBrakeParking`  INT UNSIGNED NULL,
    PRIMARY KEY (`id`),
    INDEX `idx_technical_record_id` (`technical_record_id` ASC),

    FOREIGN KEY (`technical_record_id`)
        REFERENCES `vott_db`.`technical_record` (`id`)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `vott_db`.`defects`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `vott_db`.`defects`
(
    `id`              INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `imNumber`        INT UNSIGNED NULL,
    `imDescription`   VARCHAR(200) NULL,
    `itemNumber`      INT UNSIGNED NULL,
    `itemDescription` VARCHAR(200) NULL,
    `deficiencyRef`   VARCHAR(200) NULL,
    `deficiencyId`    CHAR(1)      NULL,
    `deficiencySubId` VARCHAR(7)   NULL,
    `fingerprint`     VARCHAR(32)  NOT NULL,
    PRIMARY KEY (`id`),

    UNIQUE INDEX `idx_fingerprint_uq` (`fingerprint` ASC)
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `vott_db`.`location`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `vott_db`.`location`
(
    `id`           INT UNSIGNED     NOT NULL AUTO_INCREMENT,
    `vertical`     VARCHAR(5)       NULL,
    `horizontal`   VARCHAR(5)       NULL,
    `lateral`      VARCHAR(8)       NULL,
    `longitudinal` VARCHAR(5)       NULL,
    `rowNumber`    TINYINT UNSIGNED NULL,
    `seatNumber`   TINYINT UNSIGNED NULL,
    `axleNumber`   TINYINT UNSIGNED NULL,
    `fingerprint`  VARCHAR(32)      NOT NULL,
    PRIMARY KEY (`id`),

    UNIQUE INDEX `idx_fingerprint_uq` (`fingerprint` ASC)
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `vott_db`.`test_defect`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `vott_db`.`test_defect`
(
    `id`                 INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `test_record_id`     INT UNSIGNED NULL,
    `defect_id`          INT UNSIGNED NULL,
    `location_id`        INT UNSIGNED NULL,
    `notes`              VARCHAR(500) NULL,
    `deficiencyCategory` VARCHAR(9)   NULL,
    `deficiencyText`     VARCHAR(45)  NULL,
    `stdForProhibition`  TINYINT(1)   NULL,
    `prs`                TINYINT(1)   NULL,
    `prohibitionIssued`  TINYINT(1)   NULL,
    PRIMARY KEY (`id`),
    INDEX `idx_test_record_id` (`test_record_id` ASC),
    INDEX `idx_defect_id` (`defect_id` ASC),
    INDEX `idx_location_id` (`location_id` ASC),

    FOREIGN KEY (`test_record_id`)
        REFERENCES `vott_db`.`test_record` (`id`)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION,

    FOREIGN KEY (`defect_id`)
        REFERENCES `vott_db`.`defects` (`id`)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION,

    FOREIGN KEY (`location_id`)
        REFERENCES `vott_db`.`location` (`id`)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `vott_db`.`vehicle_history`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `vott_db`.`vehicle_history`
(
    `id`             INT UNSIGNED NOT NULL,
    `timestamp`      DATETIME     NOT NULL,
    `system_number`  VARCHAR(30)  NOT NULL,
    `vrm_trm`        VARCHAR(9)   NULL,
    `trailer_id`     VARCHAR(8)   NULL,
    `effective_from` DATE         NOT NULL,
    `effect_to`      DATE         NULL,
    PRIMARY KEY (`id`, `timestamp`),
    UNIQUE INDEX `idx_system_number_uq` (`system_number` ASC),

    FOREIGN KEY (`id`)
        REFERENCES `vott_db`.`vehicle` (`id`)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `vott_db`.`adr`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `vott_db`.`adr`
(
    `id`                        INT UNSIGNED  NOT NULL AUTO_INCREMENT,
    `technical_record_id`       INT UNSIGNED  NOT NULL,
    `type`                      VARCHAR(45)   NULL,
    `approvalDate`              DATE          NULL,
    `listStatementApplicable`   TINYINT(1)    NULL,
    `batteryListNumber`         VARCHAR(8)    NULL,
    `declarationsSeen`          TINYINT(1)    NULL,
    `brakeDeclarationsSeen`     TINYINT(1)    NULL,
    `brakeDeclarationIssuer`    TINYINT(1)    NULL,
    `brakeEndurance`            TINYINT(1)    NULL,
    `weight`                    VARCHAR(8)    NULL,
    `compatibilityGroupJ`       TINYINT(1)    NULL,
    `additionalExaminerNotes`   VARCHAR(1024) NULL,
    `applicantDetailsName`      VARCHAR(150)  NULL,
    `street`                    VARCHAR(150)  NULL,
    `town`                      VARCHAR(100)  NULL,
    `city`                      VARCHAR(100)  NULL,
    `postcode`                  VARCHAR(25)   NULL,
    `memosApply`                VARCHAR(45)   NULL,
    `adrTypeApprovalNo`         VARCHAR(45)   NULL,
    `adrCertificateNotes`       VARCHAR(1500) NULL,
    `tankManufacturer`          VARCHAR(70)   NULL,
    `yearOfManufacture`         DATE          NULL,
    `tankCode`                  VARCHAR(30)   NULL,
    `specialProvisions`         VARCHAR(1024) NULL,
    `tankManufacturerSerialNo`  VARCHAR(50)   NULL,
    `tankTypeAppNo`             VARCHAR(30)   NULL,
    `tc2Type`                   VARCHAR(45)   NULL,
    `tc2IntermediateApprovalNo` VARCHAR(70)   NULL,
    `tc2IntermediateExpiryDate` DATE          NULL,
    `substancesPermitted`       VARCHAR(45)   NULL,
    `statement`                 VARCHAR(1500) NULL,
    `productListRefNo`          VARCHAR(45)   NULL,
    `productList`               VARCHAR(1500) NULL,
    PRIMARY KEY (`id`),

    FOREIGN KEY (`technical_record_id`)
        REFERENCES `vott_db`.`technical_record` (`id`)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION,
    INDEX `idx_fk_technical_record_id` (`technical_record_id` ASC)
)
    ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `vott_db`.`additional_notes_number`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `vott_db`.`additional_notes_number`
(
    `id`     INT UNSIGNED NOT NULL,
    `number` VARCHAR(199) NOT NULL,
    PRIMARY KEY (`id`),

    FOREIGN KEY (`id`)
        REFERENCES `vott_db`.`adr` (`id`)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `vott_db`.`additional_notes_guidance`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `vott_db`.`additional_notes_number`
(
    `id`            INT UNSIGNED NOT NULL,
    `guidanceNotes` VARCHAR(199) NOT NULL,
    PRIMARY KEY (`id`),

    FOREIGN KEY (`id`)
        REFERENCES `vott_db`.`adr` (`id`)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
)
    ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `vott_db`.`dangerous_goods`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `vott_db`.`dangerous_goods`
(
    `id`          INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `name`        VARCHAR(32)  NULL,
    `fingerprint` VARCHAR(32)  NOT NULL,
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `vott_db`.`permitted_dangerous_goods`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `vott_db`.`permitted_dangerous_goods`
(
    `id`                 INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `adr_id`             INT UNSIGNED NOT NULL,
    `dangerous_goods_id` INT UNSIGNED NOT NULL,
    PRIMARY KEY (`id`),
    INDEX `idx_adr_id` (`adr_id` ASC),
    INDEX `idx_dangerous_goods_id` (`dangerous_goods_id` ASC),

    FOREIGN KEY (`adr_id`)
        REFERENCES `vott_db`.`adr` (`id`)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION,

    FOREIGN KEY (`dangerous_goods_id`)
        REFERENCES `vott_db`.`dangerous_goods` (`id`)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `vott_db`.`productListUnNo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `vott_db`.`productListUnNo`
(
    `id`          INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `name`        VARCHAR(45)  NULL,
    `fingerprint` VARCHAR(32)  NOT NULL,
    PRIMARY KEY (`id`),

    UNIQUE INDEX `idx_fingerprint_uq` (`fingerprint` ASC)
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `vott_db`.`adr_productListUnNo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `vott_db`.`adr_productListUnNo`
(
    `id`                 INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `adr_id`             INT UNSIGNED NULL,
    `productListUnNo_id` INT UNSIGNED NULL,
    PRIMARY KEY (`id`),
    INDEX `idx_productListUnNo` (`productListUnNo_id` ASC),
    INDEX `idx_adr` (`adr_id` ASC),

    FOREIGN KEY (`productListUnNo_id`)
        REFERENCES `vott_db`.`productListUnNo` (`id`)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION,

    FOREIGN KEY (`adr_id`)
        REFERENCES `vott_db`.`adr` (`id`)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
)
    ENGINE = InnoDB;

USE `vott_db`;

DELIMITER $$

-- -----------------------------------------------------
-- function f_upsert_{} Fingerprint Stored Procedure(-s)
-- -----------------------------------------------------

CREATE FUNCTION `f_upsert_contact_details`(in_name VARCHAR(150), in_address1 VARCHAR(60), in_address2 VARCHAR(60),
                                           in_postTown VARCHAR(60), in_address3 VARCHAR(60), in_postCode VARCHAR(12),
                                           in_emailAddress VARCHAR(255), in_telephoneNumber VARCHAR(25),
                                           in_faxNumber VARCHAR(25), in_notes VARCHAR(1024))
    RETURNS INT DETERMINISTIC
BEGIN
    DECLARE fp_id INT UNSIGNED;
    SELECT id
    INTO fp_id
    FROM contact_details
    WHERE fingerprint = md5(
            CONCAT_WS(' ', in_name, in_address1, in_address2, in_postTown, in_address3, in_postCode, in_emailAddress,
                      in_telephoneNumber, in_faxNumber, in_notes));
    IF fp_id IS NULL THEN
        INSERT INTO contact_details (name, address1, address2, postTown, address3, postCode, emailAddress,
                                     telephoneNumber, faxNumber, notes, fingerprint)
        VALUES (in_name, in_address1, in_address2, in_postTown, in_address3, in_postCode, in_emailAddress,
                in_telephoneNumber, in_faxNumber, in_notes, md5(
                        CONCAT_WS(' ', in_name, in_address1, in_address2, in_postTown, in_address3, in_postCode,
                                  in_emailAddress, in_telephoneNumber, in_faxNumber)));
        SELECT LAST_INSERT_ID() INTO fp_id;
    ELSE
        UPDATE contact_details SET notes = in_notes WHERE id = fp_id;
    END IF;
    RETURN fp_id;
END
$$
CREATE FUNCTION `f_upsert_dangerous_goods`(in_name VARCHAR(32))
    RETURNS INT DETERMINISTIC
BEGIN
    DECLARE fp_id INT UNSIGNED;
    SELECT id
    INTO fp_id
    FROM dangerous_goods
    WHERE fingerprint = md5(CONCAT_WS('|', in_name));
    IF fp_id IS NULL THEN
        INSERT INTO dangerous_goods (name, fingerprint)
        VALUES (in_name, md5(CONCAT_WS('|', in_name)));
        SELECT LAST_INSERT_ID() INTO fp_id;
    END IF;
    RETURN fp_id;
END
$$
CREATE FUNCTION `f_upsert_defects`(in_imNumber INT(10) UNSIGNED, in_imDescription VARCHAR(200),
                                   in_itemNumber INT(10) UNSIGNED, in_itemDescription VARCHAR(200),
                                   in_deficiencyRef VARCHAR(200), in_deficiencyId CHAR(1),
                                   in_deficiencySubId VARCHAR(7))
    RETURNS INT DETERMINISTIC
BEGIN
    DECLARE fp_id INT UNSIGNED;
    SELECT id
    INTO fp_id
    FROM defects
    WHERE fingerprint = md5(
            CONCAT_WS('|', in_imNumber, in_imDescription, in_itemNumber, in_itemDescription, in_deficiencyRef,
                      in_deficiencyId, in_deficiencySubId));
    IF fp_id IS NULL THEN
        INSERT INTO defects (imNumber, imDescription, itemNumber, itemDescription, deficiencyRef, deficiencyId,
                             deficiencySubId, fingerprint)
        VALUES (in_imNumber, in_imDescription, in_itemNumber, in_itemDescription, in_deficiencyRef, in_deficiencyId,
                in_deficiencySubId, md5(CONCAT_WS('|', in_imNumber, in_imDescription, in_itemNumber, in_itemDescription,
                                                  in_deficiencyRef, in_deficiencyId, in_deficiencySubId)));
        SELECT LAST_INSERT_ID() INTO fp_id;
    END IF;
    RETURN fp_id;
END
$$
CREATE FUNCTION `f_upsert_fuel_emission`(in_modTypeCode CHAR(1), in_description VARCHAR(32),
                                         in_emissionStandard VARCHAR(21), in_fuelType VARCHAR(13))
    RETURNS INT DETERMINISTIC
BEGIN
    DECLARE fp_id INT UNSIGNED;
    SELECT id
    INTO fp_id
    FROM fuel_emission
    WHERE fingerprint = md5(CONCAT_WS('|', in_modTypeCode, in_description, in_emissionStandard, in_fuelType));
    IF fp_id IS NULL THEN
        INSERT INTO fuel_emission (modTypeCode, description, emissionStandard, fuelType, fingerprint)
        VALUES (in_modTypeCode, in_description, in_emissionStandard, in_fuelType,
                md5(CONCAT_WS('|', in_modTypeCode, in_description, in_emissionStandard, in_fuelType)));
        SELECT LAST_INSERT_ID() INTO fp_id;
    END IF;
    RETURN fp_id;
END
$$
CREATE FUNCTION `f_upsert_identity`(in_identityId VARCHAR(199), in_name VARCHAR(199))
    RETURNS INT DETERMINISTIC
BEGIN
    DECLARE fp_id INT UNSIGNED;
    SELECT id
    INTO fp_id
    FROM identity
    WHERE fingerprint = md5(CONCAT_WS('|', in_identityId, in_name));
    IF fp_id IS NULL THEN
        INSERT INTO identity (identityId, name, fingerprint)
        VALUES (in_identityId, in_name, md5(CONCAT_WS('|', in_identityId, in_name)));
        SELECT LAST_INSERT_ID() INTO fp_id;
    END IF;
    RETURN fp_id;
END
$$
CREATE FUNCTION `f_upsert_location`(in_vertical VARCHAR(5), in_horizontal VARCHAR(5), in_lateral VARCHAR(8),
                                    in_longitudinal VARCHAR(5), in_rowNumber TINYINT(3) UNSIGNED,
                                    in_seatNumber TINYINT(3) UNSIGNED, in_axleNumber TINYINT(3) UNSIGNED)
    RETURNS INT DETERMINISTIC
BEGIN
    DECLARE fp_id INT UNSIGNED;
    SELECT id
    INTO fp_id
    FROM location
    WHERE fingerprint = md5(
            CONCAT_WS('|', in_vertical, in_horizontal, in_lateral, in_longitudinal, in_rowNumber, in_seatNumber,
                      in_axleNumber));
    IF fp_id IS NULL THEN
        INSERT INTO location (vertical, horizontal, lateral, longitudinal, rowNumber, seatNumber, axleNumber,
                              fingerprint)
        VALUES (in_vertical, in_horizontal, in_lateral, in_longitudinal, in_rowNumber, in_seatNumber, in_axleNumber,
                md5(CONCAT_WS('|', in_vertical, in_horizontal, in_lateral, in_longitudinal, in_rowNumber, in_seatNumber,
                              in_axleNumber)));
        SELECT LAST_INSERT_ID() INTO fp_id;
    END IF;
    RETURN fp_id;
END
$$
CREATE FUNCTION `f_upsert_make_model`(in_make VARCHAR(30), in_model VARCHAR(30), in_chassisMake VARCHAR(20),
                                      in_chassisModel VARCHAR(20), in_bodyMake VARCHAR(20), in_bodyModel VARCHAR(20),
                                      in_modelLiteral VARCHAR(30), in_bodyTypeCode CHAR(1),
                                      in_bodyTypeDescription VARCHAR(17), in_fuelPropulsionSystem VARCHAR(5),
                                      in_approvalType VARCHAR(3), in_approvalTypeNumber VARCHAR(25),
                                      in_variantNumber VARCHAR(25))
    RETURNS INT DETERMINISTIC
BEGIN
    DECLARE fp_id INT UNSIGNED;
    SELECT id
    INTO fp_id
    FROM make_model
    WHERE fingerprint = md5(
            CONCAT_WS('|', in_make, in_model, in_chassisMake, in_chassisModel, in_bodyMake, in_bodyModel,
                      in_modelLiteral, in_bodyTypeCode, in_bodyTypeDescription, in_fuelPropulsionSystem,
                      in_approvalType, in_approvalTypeNumber, in_variantNumber));
    IF fp_id IS NULL THEN
        INSERT INTO make_model (make, model, chassisMake, chassisModel, bodyMake, bodyModel, modelLiteral, bodyTypeCode,
                                bodyTypeDescription, fuelPropulsionSystem, approvalType, approvalTypeNumber,
                                variantNumber, fingerprint)
        VALUES (in_make, in_model, in_chassisMake, in_chassisModel, in_bodyMake, in_bodyModel, in_modelLiteral,
                in_bodyTypeCode, in_bodyTypeDescription, in_fuelPropulsionSystem, in_approvalType,
                in_approvalTypeNumber, in_variantNumber, md5(
                        CONCAT_WS('|', in_make, in_model, in_chassisMake, in_chassisModel, in_bodyMake, in_bodyModel,
                                  in_modelLiteral, in_bodyTypeCode, in_bodyTypeDescription, in_fuelPropulsionSystem,
                                  in_approvalType, in_approvalTypeNumber, in_variantNumber)));
        SELECT LAST_INSERT_ID() INTO fp_id;
    END IF;
    RETURN fp_id;
END
$$
CREATE FUNCTION `f_upsert_preparer`(in_preparerId VARCHAR(9), in_name VARCHAR(60))
    RETURNS INT DETERMINISTIC
BEGIN
    DECLARE fp_id INT UNSIGNED;
    SELECT id
    INTO fp_id
    FROM preparer
    WHERE fingerprint = md5(CONCAT_WS('|', in_preparerId, in_name));
    IF fp_id IS NULL THEN
        INSERT INTO preparer (preparerId, name, fingerprint)
        VALUES (in_preparerId, in_name, md5(CONCAT_WS('|', in_preparerId, in_name)));
        SELECT LAST_INSERT_ID() INTO fp_id;
    END IF;
    RETURN fp_id;
END
$$
CREATE FUNCTION `f_upsert_productListUnNo`(in_name VARCHAR(45))
    RETURNS INT DETERMINISTIC
BEGIN
    DECLARE fp_id INT UNSIGNED;
    SELECT id
    INTO fp_id
    FROM productListUnNo
    WHERE fingerprint = md5(CONCAT_WS('|', in_name));
    IF fp_id IS NULL THEN
        INSERT INTO productListUnNo (name, fingerprint)
        VALUES (in_name, md5(CONCAT_WS('|', in_name)));
        SELECT LAST_INSERT_ID() INTO fp_id;
    END IF;
    RETURN fp_id;
END
$$
CREATE FUNCTION `f_upsert_tester`(in_name VARCHAR(60), in_email_address VARCHAR(254))
    RETURNS INT DETERMINISTIC
BEGIN
    DECLARE fp_id INT UNSIGNED;
    SELECT id
    INTO fp_id
    FROM tester
    WHERE fingerprint = md5(CONCAT_WS('|', in_name, in_email_address));
    IF fp_id IS NULL THEN
        INSERT INTO tester (name, email_address, fingerprint)
        VALUES (in_name, in_email_address, md5(CONCAT_WS('|', in_name, in_email_address)));
        SELECT LAST_INSERT_ID() INTO fp_id;
    END IF;
    RETURN fp_id;
END
$$
CREATE FUNCTION `f_upsert_test_station`(in_pNumber VARCHAR(20), in_name VARCHAR(1000), in_type VARCHAR(4))
    RETURNS INT DETERMINISTIC
BEGIN
    DECLARE fp_id INT UNSIGNED;
    SELECT id
    INTO fp_id
    FROM test_station
    WHERE fingerprint = md5(CONCAT_WS('|', in_pNumber, in_name, in_type));
    IF fp_id IS NULL THEN
        INSERT INTO test_station (pNumber, name, type, fingerprint)
        VALUES (in_pNumber, in_name, in_type, md5(CONCAT_WS('|', in_pNumber, in_name, in_type)));
        SELECT LAST_INSERT_ID() INTO fp_id;
    END IF;
    RETURN fp_id;
END
$$
CREATE FUNCTION `f_upsert_test_type`(in_testTypeClassification VARCHAR(23), in_testTypeName VARCHAR(199))
    RETURNS INT DETERMINISTIC
BEGIN
    DECLARE fp_id INT UNSIGNED;
    SELECT id
    INTO fp_id
    FROM test_type
    WHERE fingerprint = md5(CONCAT_WS('|', in_testTypeClassification, in_testTypeName));
    IF fp_id IS NULL THEN
        INSERT INTO test_type (testTypeClassification, testTypeName, fingerprint)
        VALUES (in_testTypeClassification, in_testTypeName,
                md5(CONCAT_WS('|', in_testTypeClassification, in_testTypeName)));
        SELECT LAST_INSERT_ID() INTO fp_id;
    END IF;
    RETURN fp_id;
END
$$
CREATE FUNCTION `f_upsert_vehicle_class`(in_code CHAR(1), in_description VARCHAR(46), in_vehicleType VARCHAR(10),
                                         in_vehicleSize VARCHAR(5), in_vehicleConfiguration VARCHAR(20),
                                         in_euVehicleCategory VARCHAR(5))
    RETURNS INT DETERMINISTIC
BEGIN
    DECLARE fp_id INT UNSIGNED;
    SELECT id
    INTO fp_id
    FROM vehicle_class
    WHERE fingerprint = md5(
            CONCAT_WS('|', in_code, in_description, in_vehicleType, in_vehicleSize, in_vehicleConfiguration,
                      in_euVehicleCategory));
    IF fp_id IS NULL THEN
        INSERT INTO vehicle_class (code, description, vehicleType, vehicleSize, vehicleConfiguration, euVehicleCategory,
                                   fingerprint)
        VALUES (in_code, in_description, in_vehicleType, in_vehicleSize, in_vehicleConfiguration, in_euVehicleCategory,
                md5(CONCAT_WS('|', in_code, in_description, in_vehicleType, in_vehicleSize, in_vehicleConfiguration,
                              in_euVehicleCategory)));
        SELECT LAST_INSERT_ID() INTO fp_id;
    END IF;
    RETURN fp_id;
END
$$
CREATE FUNCTION `f_upsert_vehicle_subclass`(in_subclass VARCHAR(199))
    RETURNS INT DETERMINISTIC
BEGIN
    DECLARE fp_id INT UNSIGNED;
    SELECT id
    INTO fp_id
    FROM vehicle_subclass
    WHERE fingerprint = md5(CONCAT_WS('|', in_subclass));
    IF fp_id IS NULL THEN
        INSERT INTO vehicle_subclass (subclass, fingerprint)
        VALUES (in_subclass, md5(CONCAT_WS('|', in_subclass)));
        SELECT LAST_INSERT_ID() INTO fp_id;
    END IF;
    RETURN fp_id;
END
$$

DELIMITER ;

SET SQL_MODE = @OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS = @OLD_UNIQUE_CHECKS;