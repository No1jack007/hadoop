/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50528
Source Host           : 127.0.0.1:3306
Source Database       : bigdata

Target Server Type    : MYSQL
Target Server Version : 50528
File Encoding         : 65001

Date: 2019-06-11 21:45:26
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for location_info
-- ----------------------------
DROP TABLE IF EXISTS `location_info`;
CREATE TABLE `location_info` (
  `location` varchar(255) DEFAULT NULL,
  `counts` int(11) DEFAULT NULL,
  `access_date` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of location_info
-- ----------------------------
