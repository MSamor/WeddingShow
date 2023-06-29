/*
 Navicat Premium Data Transfer

 Source Server         : 150
 Source Server Type    : MySQL
 Source Server Version : 80024
 Source Host           : 150.158.84.131:3306
 Source Schema         : wedding_show

 Target Server Type    : MySQL
 Target Server Version : 80024
 File Encoding         : 65001

 Date: 29/06/2023 13:56:54
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for activity
-- ----------------------------
DROP TABLE IF EXISTS `activity`;
CREATE TABLE `activity`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `activity_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '活动名称',
  `activity_end_date` datetime(0) NULL DEFAULT NULL COMMENT '活动结束时间,开奖时间',
  `win_num` int(0) NULL DEFAULT NULL COMMENT '中奖人数',
  `code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'Lucky draw，抽奖code',
  `activity_desc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '活动描述',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for activity_join
-- ----------------------------
DROP TABLE IF EXISTS `activity_join`;
CREATE TABLE `activity_join`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `uid` int(0) NULL DEFAULT NULL COMMENT '用户id',
  `activity_id` int(0) NULL DEFAULT NULL COMMENT '活动id',
  `date` datetime(0) NULL DEFAULT NULL COMMENT '加入时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for activity_prize
-- ----------------------------
DROP TABLE IF EXISTS `activity_prize`;
CREATE TABLE `activity_prize`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `prize_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '奖品名称',
  `count` int(0) NULL DEFAULT NULL COMMENT '数量',
  `activity_id` int(0) NULL DEFAULT NULL COMMENT '活动id',
  `order` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '排序码',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for activity_win
-- ----------------------------
DROP TABLE IF EXISTS `activity_win`;
CREATE TABLE `activity_win`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `uid` int(0) NULL DEFAULT NULL COMMENT '中奖人id',
  `activity_id` int(0) NULL DEFAULT NULL COMMENT '活动id',
  `activity_prize_id` int(0) NULL DEFAULT NULL COMMENT '奖品id',
  `date` datetime(0) NULL DEFAULT NULL COMMENT '中奖时间',
  `memo` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '额外说明',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for bullet
-- ----------------------------
DROP TABLE IF EXISTS `bullet`;
CREATE TABLE `bullet`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `date` datetime(0) NULL DEFAULT NULL COMMENT '时间',
  `text` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '内容',
  `uid` int(0) NULL DEFAULT NULL COMMENT '用户id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 55 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for image
-- ----------------------------
DROP TABLE IF EXISTS `image`;
CREATE TABLE `image`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `filename` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `file_type` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `file_size` bigint(0) NOT NULL,
  `file_data` longblob NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 25 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '用户uid',
  `openid` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'openid',
  `sign_date` datetime(0) NULL DEFAULT NULL COMMENT '注册时间',
  `avatar_url` varchar(5000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '头像',
  `nick_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '昵称',
  `last_login_date` datetime(0) NULL DEFAULT NULL COMMENT '上次登录时间',
  `gender` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '性别',
  `province` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '省',
  `country` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '国家',
  `city` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '城市',
  `ban` tinyint(1) NULL DEFAULT NULL COMMENT '是否禁用',
  `ban_date` datetime(0) NULL DEFAULT NULL COMMENT '禁用时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

SET FOREIGN_KEY_CHECKS = 1;
