/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50725
 Source Host           : localhost:3306
 Source Schema         : homework

 Target Server Type    : MySQL
 Target Server Version : 50725
 File Encoding         : 65001

 Date: 05/12/2019 22:32:12
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category`  (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '标题',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '内容描述',
  `summary` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `icon` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '图标',
  `post_count` int(11) UNSIGNED NULL DEFAULT 0 COMMENT '该分类的内容数量',
  `order_num` int(11) NULL DEFAULT NULL COMMENT '排序编码',
  `parent_id` bigint(32) UNSIGNED NULL DEFAULT NULL COMMENT '父级分类的ID',
  `meta_keywords` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'SEO关键字',
  `meta_description` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'SEO描述内容',
  `created` datetime NULL DEFAULT NULL COMMENT '创建日期',
  `modified` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES (1, '首页', NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `category` VALUES (2, '动态', NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `category` VALUES (3, '问答', NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`  (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '评论的内容',
  `parent_id` bigint(32) NULL DEFAULT NULL COMMENT '回复的评论ID',
  `post_id` bigint(32) NOT NULL COMMENT '评论的内容ID',
  `user_id` bigint(32) NOT NULL COMMENT '评论的用户ID',
  `vote_up` int(11) UNSIGNED NOT NULL DEFAULT 0 COMMENT '“顶”的数量',
  `vote_down` int(11) UNSIGNED NOT NULL DEFAULT 0 COMMENT '“踩”的数量',
  `level` tinyint(2) UNSIGNED NOT NULL DEFAULT 0 COMMENT '置顶等级',
  `status` tinyint(2) NULL DEFAULT NULL COMMENT '评论的状态',
  `created` datetime NOT NULL COMMENT '评论的时间',
  `modified` datetime NULL DEFAULT NULL COMMENT '评论的更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for post
-- ----------------------------
DROP TABLE IF EXISTS `post`;
CREATE TABLE `post`  (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `title` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '标题',
  `content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '内容',
  `edit_mode` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '编辑模式：html可视化，markdown ..',
  `category_id` bigint(32) NULL DEFAULT NULL,
  `user_id` bigint(32) NULL DEFAULT NULL COMMENT '用户ID',
  `vote_up` int(11) UNSIGNED NOT NULL DEFAULT 0 COMMENT '支持人数',
  `vote_down` int(11) UNSIGNED NOT NULL DEFAULT 0 COMMENT '反对人数',
  `view_count` int(11) UNSIGNED NOT NULL DEFAULT 0 COMMENT '访问量',
  `comment_count` int(11) NOT NULL DEFAULT 0 COMMENT '评论数量',
  `recommend` tinyint(1) NULL DEFAULT NULL COMMENT '是否为精华',
  `level` tinyint(2) NOT NULL DEFAULT 0 COMMENT '置顶等级',
  `status` tinyint(2) NULL DEFAULT NULL COMMENT '状态',
  `created` datetime NULL DEFAULT NULL COMMENT '创建日期',
  `modified` datetime NULL DEFAULT NULL COMMENT '最后更新日期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of post
-- ----------------------------
INSERT INTO `post` VALUES (1, '这是标题，标题', '内容，这是内容', '0', 1, 1, 0, 0, 140, 0, 0, 1, 0, '2018-10-15 13:27:35', '2018-10-13 16:33:07');
INSERT INTO `post` VALUES (2, '这是标222222222222222222', '内容，这是内容', '0', 2, 2, 0, 0, 67, 99, 1, 1, 0, '2018-10-15 13:27:26', '2018-10-13 16:33:07');
INSERT INTO `post` VALUES (3, '这是标222222222222222222', '内容，这是内容', '0', 2, 2, 0, 0, 67, 99, 1, 1, 0, '2018-10-15 13:27:26', '2018-10-13 16:33:07');
INSERT INTO `post` VALUES (4, '这是标222222222222222222', '内容，这是内容', '0', 2, 2, 0, 0, 67, 99, 1, 1, 0, '2018-10-15 13:27:26', '2018-10-13 16:33:07');
INSERT INTO `post` VALUES (5, '这是标222222222222222222', '内容，这是内容', '0', 2, 2, 0, 0, 67, 99, 1, 1, 0, '2018-10-15 13:27:26', '2018-10-13 16:33:07');
INSERT INTO `post` VALUES (6, '这是标222222222222222222', '内容，这是内容', '0', 2, 2, 0, 0, 67, 99, 1, 1, 0, '2018-10-15 13:27:26', '2018-10-13 16:33:07');
INSERT INTO `post` VALUES (7, '这是标222222222222222222', '内容，这是内容', '0', 2, 1, 0, 0, 67, 99, 1, 1, 0, '2018-10-15 13:27:26', '2018-10-13 16:33:07');
INSERT INTO `post` VALUES (8, '这是标222222222222222222', '内容，这是内容', '0', 2, 1, 0, 0, 67, 99, 1, 1, 0, '2018-10-15 13:27:26', '2018-10-13 16:33:07');
INSERT INTO `post` VALUES (9, '这是标222222222222222222', '内容，这是内容', '0', 2, 1, 0, 0, 67, 99, 1, 1, 0, '2018-10-15 13:27:26', '2018-10-13 16:33:07');
INSERT INTO `post` VALUES (10, '这是标222222222222222222', '内容，这是内容', '0', 2, 1, 0, 0, 67, 99, 1, 1, 0, '2018-10-15 13:27:26', '2018-10-13 16:33:07');
INSERT INTO `post` VALUES (11, '这是标222222222222222222', '内容，这是内容', '0', 2, 1, 0, 0, 67, 99, 1, 1, 0, '2018-10-15 13:27:26', '2018-10-13 16:33:07');
INSERT INTO `post` VALUES (12, '这是标222222222222222222', '内容，这是内容', '0', 2, 1, 0, 0, 67, 99, 1, 1, 0, '2018-10-15 13:27:26', '2018-10-13 16:33:07');

-- ----------------------------
-- Table structure for test_user
-- ----------------------------
DROP TABLE IF EXISTS `test_user`;
CREATE TABLE `test_user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `age` int(11) NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of test_user
-- ----------------------------
INSERT INTO `test_user` VALUES (1, 123, '小明', '123@qq.com');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `username` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '昵称',
  `password` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码',
  `email` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮件',
  `mobile` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '手机电话',
  `point` int(11) UNSIGNED NOT NULL DEFAULT 0 COMMENT '积分',
  `sign` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '个性签名',
  `gender` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '性别',
  `wechat` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '微信号',
  `vip_level` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'vip等级',
  `modified` datetime NULL DEFAULT NULL COMMENT '最后修改时间',
  `birthday` datetime NULL DEFAULT NULL COMMENT '生日',
  `avatar` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '头像',
  `post_count` int(11) UNSIGNED NOT NULL DEFAULT 0 COMMENT '内容数量',
  `comment_count` int(11) UNSIGNED NOT NULL DEFAULT 0 COMMENT '评论数量',
  `status` tinyint(2) NOT NULL DEFAULT 0 COMMENT '状态',
  `lasted` datetime NULL DEFAULT NULL COMMENT '最后的登陆时间',
  `created` datetime NOT NULL COMMENT '创建日期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'xiaoming', '1111', NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 'https://tva1.sinaimg.cn/crop.0.0.118.118.180/5db11ff4gw1e77d3nqrv8j203b03cweg.jpg', 0, 0, 0, NULL, '2018-10-14 18:41:34');
INSERT INTO `user` VALUES (2, 'shuaihua', '7a977024ceae22a499a80d9ea208ab34', 'admin@qq.com', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, '/images/avatar/default.png', 0, 0, 0, '2019-12-05 22:06:05', '2019-07-14 22:24:37');

SET FOREIGN_KEY_CHECKS = 1;
