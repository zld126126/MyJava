/*
 Navicat Premium Data Transfer

 Source Server         : 本地mysql
 Source Server Type    : MySQL
 Source Server Version : 50536
 Source Host           : localhost:3306
 Source Schema         : dongtech

 Target Server Type    : MySQL
 Target Server Version : 50536
 File Encoding         : 65001

 Date: 07/08/2018 11:00:59
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for permissioninfo
-- ----------------------------
DROP TABLE IF EXISTS `permissioninfo`;
CREATE TABLE `permissioninfo`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `permissionnum` int(11) DEFAULT NULL,
  `permissionexplain` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of permissioninfo
-- ----------------------------
INSERT INTO `permissioninfo` VALUES (1, 1, '超级管理员');
INSERT INTO `permissioninfo` VALUES (2, 2, '管理员');
INSERT INTO `permissioninfo` VALUES (3, 3, '普通权限');

-- ----------------------------
-- Table structure for permissionpath
-- ----------------------------
DROP TABLE IF EXISTS `permissionpath`;
CREATE TABLE `permissionpath`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `permissionnum` int(11) DEFAULT NULL,
  `permissionpath` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of permissionpath
-- ----------------------------
INSERT INTO `permissionpath` VALUES (1, 1, '/menu/roledocument');
INSERT INTO `permissionpath` VALUES (2, 1, '/menu/profiledocument');
INSERT INTO `permissionpath` VALUES (3, 1, '/menu/userdocument');
INSERT INTO `permissionpath` VALUES (4, 1, '/menu/permissiondocument');
INSERT INTO `permissionpath` VALUES (5, 2, '/menu/profiledocument');
INSERT INTO `permissionpath` VALUES (6, 2, '/menu/userdocument');
INSERT INTO `permissionpath` VALUES (7, 3, '/menu/profiledocument');

-- ----------------------------
-- Table structure for userinfo
-- ----------------------------
DROP TABLE IF EXISTS `userinfo`;
CREATE TABLE `userinfo`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `worktime` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `workstatus` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of userinfo
-- ----------------------------
INSERT INTO `userinfo` VALUES (1, 'dongtech', '2016-06-30', '1', '123456', 'zld126126@126.com');
INSERT INTO `userinfo` VALUES (2, 'dong666', '2016-08-30', '1', '666666', 'zld163163@163.com');
INSERT INTO `userinfo` VALUES (3, 'donglong', '2018-03-22', '1', '666666', 'yy@126.com');
INSERT INTO `userinfo` VALUES (4, 'zld126126', '2018-08-02', '0', 'dd931226', 'z@qq.com');
INSERT INTO `userinfo` VALUES (5, 'zld163163', '2018-08-02', '1', 'dd931226', 'xxx@126.com');
INSERT INTO `userinfo` VALUES (7, 'xxx', '2018-08-03', '1', '123456', 'xx@xx.com');
INSERT INTO `userinfo` VALUES (8, 'zld126', '2018-08-03', '0', 'dd931226', 'zld126126@126.com');

-- ----------------------------
-- Table structure for userprofile
-- ----------------------------
DROP TABLE IF EXISTS `userprofile`;
CREATE TABLE `userprofile`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) DEFAULT NULL,
  `useraddress` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `aboutuser` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of userprofile
-- ----------------------------
INSERT INTO `userprofile` VALUES (1, 1, 'China', 26, '一个热爱编程的人');
INSERT INTO `userprofile` VALUES (2, 2, 'Nanjing', 22, '测试攻城狮');

-- ----------------------------
-- Table structure for userrole
-- ----------------------------
DROP TABLE IF EXISTS `userrole`;
CREATE TABLE `userrole`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) DEFAULT NULL,
  `permissionid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of userrole
-- ----------------------------
INSERT INTO `userrole` VALUES (1, 1, 1);
INSERT INTO `userrole` VALUES (2, 2, 1);
INSERT INTO `userrole` VALUES (3, 3, 3);
INSERT INTO `userrole` VALUES (4, 2, 1);
INSERT INTO `userrole` VALUES (5, 2, 3);
INSERT INTO `userrole` VALUES (6, 3, 1);

SET FOREIGN_KEY_CHECKS = 1;
