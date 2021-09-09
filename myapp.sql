/*
 Navicat Premium Data Transfer

 Source Server         : Mysql
 Source Server Type    : MySQL
 Source Server Version : 50726
 Source Host           : localhost:8889
 Source Schema         : myapp

 Target Server Type    : MySQL
 Target Server Version : 50726
 File Encoding         : 65001

 Date: 04/01/2021 19:49:50
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for bill
-- ----------------------------
DROP TABLE IF EXISTS `bill`;
CREATE TABLE `bill` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `product` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `total` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `state` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  `imgurl` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `cate` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of bill
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for cate
-- ----------------------------
DROP TABLE IF EXISTS `cate`;
CREATE TABLE `cate` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `info` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `updatetime` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of cate
-- ----------------------------
BEGIN;
INSERT INTO `cate` VALUES (1, '一食堂', '一食堂', NULL);
INSERT INTO `cate` VALUES (2, '二食堂', '二食堂', NULL);
INSERT INTO `cate` VALUES (3, '三食堂', '三食堂', NULL);
INSERT INTO `cate` VALUES (4, '音乐食堂', '音乐食堂', NULL);
COMMIT;

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `comment` longtext COLLATE utf8_unicode_ci,
  `updatetime` timestamp NULL DEFAULT NULL,
  `productid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of comment
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `info` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `imgurl` varchar(1024) COLLATE utf8_unicode_ci DEFAULT NULL,
  `price` float DEFAULT NULL,
  `cate` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of product
-- ----------------------------
BEGIN;
INSERT INTO `product` VALUES (1, '冲绳黑糖拿铁', '瑞幸咖啡', 'uploads/topic/20201218/9385c9a7d5875f0209caad69d527bcf0.png', 28, '三食堂');
INSERT INTO `product` VALUES (2, '五谷番茄粉', '五谷渔粉', 'uploads/topic/20201218/de9106810e56ee1c5407c9947f3b8ab4.png', 13, '一食堂');
INSERT INTO `product` VALUES (3, '黑胡椒芝士焗面', '食惠烤肉拌饭', 'uploads/topic/20201218/2e554dfce10dcabb5cf31d4934a1e9c6.png', 13, '音乐食堂');
INSERT INTO `product` VALUES (4, '奥尔良鸡排意面', '福鼎一哥', 'uploads/topic/20201218/de914fac930fc84abf93db7c558aa8bb.png', 15, '二食堂');
INSERT INTO `product` VALUES (5, '鲜虾花甲粉', '五谷渔粉', 'uploads/topic/20201218/03a4ded488a7a760f770c4ea0f2fc377.png', 14, '一食堂');
INSERT INTO `product` VALUES (6, '满分buff拿铁套餐', '瑞幸咖啡', 'uploads/topic/20201218/02e557b3bf3d79672e1634db3c6f260a.png', 40, '三食堂');
INSERT INTO `product` VALUES (7, '五谷招牌渔粉', '五谷渔粉', 'uploads/topic/20201218/9fe43675a4d1ac5a5569d424c3090613.png', 13, '一食堂');
INSERT INTO `product` VALUES (8, '老坛酸菜无骨烤鱼饭', '无骨烤鱼饭', 'uploads/topic/20201218/30a9a98765375556e6fa9f76d86a0050.png', 13.99, '三食堂');
INSERT INTO `product` VALUES (9, '香草拿铁', '瑞幸咖啡', 'uploads/topic/20201218/21aa875986b1b071c747c37f3fefe133.png', 28, '三食堂');
INSERT INTO `product` VALUES (10, '金牌香辣无骨烤鱼饭', '无骨烤鱼饭', 'uploads/topic/20201218/06e411e6de97d6d0b8c703d676779ced.png', 13.99, '三食堂');
INSERT INTO `product` VALUES (11, '咖喱鸡芝士焗饭', '食惠烤肉拌饭', 'uploads/topic/20201218/1a5d03501c21497a480bbd585f5c34e6.png', 14, '音乐食堂');
INSERT INTO `product` VALUES (12, '大份福鼎肉片', '福鼎一哥', 'uploads/topic/20201218/257f79cb44c43db86a1f6cca78bfe25d.png', 10, '二食堂');
INSERT INTO `product` VALUES (13, '浓郁酱香无骨烤鱼饭', '无骨烤鱼饭', 'uploads/topic/20201218/16846100ecf06c41b2189ae8c31f81e3.png', 13.99, '三食堂');
INSERT INTO `product` VALUES (14, '麻辣牛肉酱芝士焗面', '食惠烤肉拌饭', 'uploads/topic/20201218/88c533c99bfad8d3bebbec4180c6597d.png', 15, '音乐食堂');
INSERT INTO `product` VALUES (15, '特色金汤无骨烤鱼饭', '无骨烤鱼饭', 'uploads/topic/20201218/c3470790ecc602622462ae668b6453c7.png', 13.99, '三食堂');
INSERT INTO `product` VALUES (16, '拿铁', '瑞幸咖啡', 'uploads/topic/20201218/35e3d2b4699d9c429b0d2db5c1ead211.png', 25, '三食堂');
INSERT INTO `product` VALUES (17, '福鼎肉片拌泡面', '福鼎一哥', 'uploads/topic/20201218/d8d677fc5d82e3dab991b5dfd1519a1a.png', 12, '二食堂');
INSERT INTO `product` VALUES (18, '陨石拿铁', '瑞幸咖啡', 'uploads/topic/20201218/756746763113d2adf38a9cdb2c1fdbbf.png', 28, '三食堂');
INSERT INTO `product` VALUES (19, '素椒杂酱面', '食惠烤肉拌饭', 'uploads/topic/20201218/d214a9241225ef115cba5f9eca48dade.png', 13, '音乐食堂');
INSERT INTO `product` VALUES (20, '原味花甲粉', '五谷渔粉', 'uploads/topic/20201218/2fc773db0676011187a6f33927bdefe4.png', 11, '一食堂');
INSERT INTO `product` VALUES (21, '甜心番茄无骨烤鱼饭', '无骨烤鱼饭', 'uploads/topic/20201218/09ddd49588acd80b71632b9b77e5a7bf.png', 13.99, '三食堂');
INSERT INTO `product` VALUES (22, '鱿鱼花甲粉', '五谷渔粉', 'uploads/topic/20201218/89d355c5dd456faf3144931568fd0cde.png', 13, '一食堂');
COMMIT;

-- ----------------------------
-- Table structure for setting
-- ----------------------------
DROP TABLE IF EXISTS `setting`;
CREATE TABLE `setting` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `imgurl` longtext COLLATE utf8_unicode_ci,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of setting
-- ----------------------------
BEGIN;
INSERT INTO `setting` VALUES (1, 'uploads/topic/20210102/b602915cd8be86f96440a9de8ed709e4.png');
INSERT INTO `setting` VALUES (2, 'uploads/topic/20201218/5db15fee5e2e34939e526ebc72548a74.png');
INSERT INTO `setting` VALUES (3, 'uploads/topic/20210102/a65c054568efe713e5695913575be7da.png');
INSERT INTO `setting` VALUES (4, 'uploads/topic/20201218/ab5389f9417e54e0e6925430e50905ec.png');
COMMIT;

-- ----------------------------
-- Table structure for tp_admin
-- ----------------------------
DROP TABLE IF EXISTS `tp_admin`;
CREATE TABLE `tp_admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of tp_admin
-- ----------------------------
BEGIN;
INSERT INTO `tp_admin` VALUES (1, 'cpadmin', '$2y$10$wQj2uILOk9ssoii6StwdFuKOsaazMIgWsTepMQ4pAZuGDxGuZ6eGK');
COMMIT;

-- ----------------------------
-- Table structure for tp_identify
-- ----------------------------
DROP TABLE IF EXISTS `tp_identify`;
CREATE TABLE `tp_identify` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of tp_identify
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for tp_mail
-- ----------------------------
DROP TABLE IF EXISTS `tp_mail`;
CREATE TABLE `tp_mail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of tp_mail
-- ----------------------------
BEGIN;
INSERT INTO `tp_mail` VALUES (1, 'admin@qq.com');
COMMIT;

-- ----------------------------
-- Table structure for tp_notify
-- ----------------------------
DROP TABLE IF EXISTS `tp_notify`;
CREATE TABLE `tp_notify` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `info` longtext CHARACTER SET utf8,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of tp_notify
-- ----------------------------
BEGIN;
INSERT INTO `tp_notify` VALUES (1, '最终解释权归闽生活平台所有');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
