/*
Navicat MySQL Data Transfer

Source Server         : myDB
Source Server Version : 50536
Source Host           : localhost:3306
Source Database       : mybatisplus

Target Server Type    : MYSQL
Target Server Version : 50536
File Encoding         : 65001

Date: 2019-02-17 22:06:42
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `userinfo`
-- ----------------------------
DROP TABLE IF EXISTS `userinfo`;
CREATE TABLE `userinfo` (
  `id` int(11) NOT NULL DEFAULT '0',
  `name` varchar(255) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of userinfo
-- ----------------------------
INSERT INTO `userinfo` VALUES ('1', 'dong', '25', 'dong@dong.com', 'china');
INSERT INTO `userinfo` VALUES ('2', 'liming', '15', 'liming@liming.com', 'japan');
INSERT INTO `userinfo` VALUES ('3', 'xiaohong', '20', 'xiaohong@xiaohong.com', 'usa');
