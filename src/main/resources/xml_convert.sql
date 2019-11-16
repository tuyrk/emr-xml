/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : xml_convert

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2018-05-09 20:43:07
*/

CREATE DATABASE IF NOT EXISTS `emr_xml`;
USE `emr_xml`;
SET NAMES utf8;

SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for hospitalization.advice
-- ----------------------------
DROP TABLE IF EXISTS `hospitalization.advice`;
CREATE TABLE `hospitalization.advice` (
  `id`        VARCHAR(20) COLLATE utf8_unicode_ci NOT NULL,
  `director`  VARCHAR(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `deputy`    VARCHAR(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `attending` VARCHAR(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `advanced`  VARCHAR(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `inpatient` VARCHAR(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `nurse`     VARCHAR(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `records`   VARCHAR(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `time`      DATETIME                            DEFAULT NULL,
  `route`     VARCHAR(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COLLATE = utf8_unicode_ci;

-- ----------------------------
-- Table structure for hospitalization.details
-- ----------------------------
DROP TABLE IF EXISTS `hospitalization.details`;
CREATE TABLE `hospitalization.details` (
  `id`            VARCHAR(20) COLLATE utf8_unicode_ci NOT NULL,
  `hospital`      VARCHAR(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `route`         VARCHAR(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `type`          VARCHAR(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `inTime`        DATETIME                            DEFAULT NULL,
  `outTime`       DATETIME                            DEFAULT NULL,
  `inCategory`    VARCHAR(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `outCategory`   VARCHAR(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `transCategory` VARCHAR(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `day`           INT(11)                             DEFAULT NULL,
  `diagnosis`     VARCHAR(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `fourDiagnosis` VARCHAR(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `syndromeType`  VARCHAR(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `treatment`     VARCHAR(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `diseaseBit`    VARCHAR(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `disease`       VARCHAR(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `chinese`       VARCHAR(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `western`       VARCHAR(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `clinical`      VARCHAR(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COLLATE = utf8_unicode_ci;

-- ----------------------------
-- Table structure for hospitalization.userinfo
-- ----------------------------
DROP TABLE IF EXISTS `hospitalization.userinfo`;
CREATE TABLE `hospitalization.userinfo` (
  `id`        VARCHAR(20) COLLATE utf8_unicode_ci NOT NULL,
  `name`      VARCHAR(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `sex`       VARCHAR(1) COLLATE utf8_unicode_ci  DEFAULT NULL,
  `birth`     DATETIME                            DEFAULT NULL,
  `age`       INT(11)                             DEFAULT NULL,
  `position`  VARCHAR(40) COLLATE utf8_unicode_ci DEFAULT NULL,
  `phone`     VARCHAR(15) COLLATE utf8_unicode_ci DEFAULT NULL,
  `count`     INT(11)                             DEFAULT NULL,
  `marriage`  VARCHAR(3) COLLATE utf8_unicode_ci  DEFAULT NULL,
  `nation`    VARCHAR(3) COLLATE utf8_unicode_ci  DEFAULT NULL,
  `idNumber`  VARCHAR(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `domicile`  VARCHAR(40) COLLATE utf8_unicode_ci DEFAULT NULL,
  `homeplace` VARCHAR(40) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COLLATE = utf8_unicode_ci;

-- ----------------------------
-- Table structure for outpatient.details
-- ----------------------------
DROP TABLE IF EXISTS `outpatient.details`;
CREATE TABLE `outpatient.details` (
  `id`            VARCHAR(20) COLLATE utf8_unicode_ci NOT NULL,
  `department`    VARCHAR(20) COLLATE utf8_unicode_ci  DEFAULT NULL,
  `time`          DATETIME                             DEFAULT NULL,
  `fourDiagnosis` VARCHAR(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `diagnosis`     VARCHAR(20) COLLATE utf8_unicode_ci  DEFAULT NULL,
  `syndromeType`  VARCHAR(20) COLLATE utf8_unicode_ci  DEFAULT NULL,
  `allergy`       VARCHAR(20) COLLATE utf8_unicode_ci  DEFAULT NULL,
  `treatment`     VARCHAR(20) COLLATE utf8_unicode_ci  DEFAULT NULL,
  `diseaseBit`    VARCHAR(20) COLLATE utf8_unicode_ci  DEFAULT NULL,
  `disease`       VARCHAR(20) COLLATE utf8_unicode_ci  DEFAULT NULL,
  `chinese`       VARCHAR(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COLLATE = utf8_unicode_ci;

-- ----------------------------
-- Table structure for outpatient.userinfo
-- ----------------------------
DROP TABLE IF EXISTS `outpatient.userinfo`;
CREATE TABLE `outpatient.userinfo` (
  `id`       VARCHAR(20) COLLATE utf8_unicode_ci NOT NULL,
  `name`     VARCHAR(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `sex`      VARCHAR(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `age`      INT(11)                             DEFAULT NULL,
  `phone`    VARCHAR(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `idNumber` VARCHAR(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `address`  VARCHAR(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COLLATE = utf8_unicode_ci;
