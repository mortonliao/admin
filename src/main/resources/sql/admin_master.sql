/*
Navicat MySQL Data Transfer

Source Server         : demo
Source Server Version : 50711
Source Host           : localhost:3306
Source Database       : admin_master

Target Server Type    : MYSQL
Target Server Version : 50711
File Encoding         : 65001

Date: 2017-05-15 17:04:47
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_authority
-- ----------------------------
DROP TABLE IF EXISTS `t_authority`;
CREATE TABLE `t_authority` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `name` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '名称',
  `type` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '类型(menu-菜单，operation-操作，element-元素)',
  `code` varchar(100) COLLATE utf8_unicode_ci NOT NULL COMMENT '标识',
  `status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '状态（0-正常，1-无效）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of t_authority
-- ----------------------------
INSERT INTO `t_authority` VALUES ('1', '会员管理', 'menu', 'user:manager', '0');
INSERT INTO `t_authority` VALUES ('2', '会员中心', 'menu', 'user:center', '0');
INSERT INTO `t_authority` VALUES ('3', '系统管理', 'menu', 'sys:manager', '0');
INSERT INTO `t_authority` VALUES ('4', '报表管理', 'menu', 'report:manager', '0');

-- ----------------------------
-- Table structure for t_authority_fn_operation
-- ----------------------------
DROP TABLE IF EXISTS `t_authority_fn_operation`;
CREATE TABLE `t_authority_fn_operation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `authority_id` int(11) NOT NULL,
  `fn_operation_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `authority_id` (`authority_id`),
  KEY `fn_operation_id` (`fn_operation_id`),
  CONSTRAINT `t_authority_fn_operation_ibfk_1` FOREIGN KEY (`authority_id`) REFERENCES `t_authority` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `t_authority_fn_operation_ibfk_2` FOREIGN KEY (`fn_operation_id`) REFERENCES `t_fn_operation` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of t_authority_fn_operation
-- ----------------------------

-- ----------------------------
-- Table structure for t_authority_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_authority_menu`;
CREATE TABLE `t_authority_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `authority_id` int(11) NOT NULL,
  `menu_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `authority_id` (`authority_id`),
  KEY `menu_id` (`menu_id`),
  CONSTRAINT `t_authority_menu_ibfk_1` FOREIGN KEY (`authority_id`) REFERENCES `t_authority` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `t_authority_menu_ibfk_2` FOREIGN KEY (`menu_id`) REFERENCES `t_menu` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of t_authority_menu
-- ----------------------------
INSERT INTO `t_authority_menu` VALUES ('1', '1', '1');
INSERT INTO `t_authority_menu` VALUES ('2', '2', '5');
INSERT INTO `t_authority_menu` VALUES ('3', '3', '2');
INSERT INTO `t_authority_menu` VALUES ('4', '4', '3');

-- ----------------------------
-- Table structure for t_authority_page_element
-- ----------------------------
DROP TABLE IF EXISTS `t_authority_page_element`;
CREATE TABLE `t_authority_page_element` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `authority_id` int(11) NOT NULL,
  `page_element_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `authority_id` (`authority_id`),
  KEY `page_element_id` (`page_element_id`),
  CONSTRAINT `t_authority_page_element_ibfk_1` FOREIGN KEY (`authority_id`) REFERENCES `t_authority` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `t_authority_page_element_ibfk_2` FOREIGN KEY (`page_element_id`) REFERENCES `t_page_element` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of t_authority_page_element
-- ----------------------------

-- ----------------------------
-- Table structure for t_fn_operation
-- ----------------------------
DROP TABLE IF EXISTS `t_fn_operation`;
CREATE TABLE `t_fn_operation` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `pid` int(11) NOT NULL COMMENT '父编号',
  `name` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '名称',
  `code` varchar(100) COLLATE utf8_unicode_ci NOT NULL COMMENT '标识',
  `path` varchar(200) COLLATE utf8_unicode_ci NOT NULL COMMENT '路径',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of t_fn_operation
-- ----------------------------

-- ----------------------------
-- Table structure for t_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_menu`;
CREATE TABLE `t_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `pid` int(11) NOT NULL COMMENT '父编号',
  `name` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '名称',
  `url` varchar(150) COLLATE utf8_unicode_ci NOT NULL COMMENT '地址',
  `status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '状态（0-邮箱，1-无效）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of t_menu
-- ----------------------------
INSERT INTO `t_menu` VALUES ('1', '0', '会员管理', '/', '0');
INSERT INTO `t_menu` VALUES ('2', '0', '系统管理', '/', '0');
INSERT INTO `t_menu` VALUES ('3', '0', '报表管理', '/', '0');
INSERT INTO `t_menu` VALUES ('4', '0', '监控管理', '/', '0');
INSERT INTO `t_menu` VALUES ('5', '1', '会员中心', '/', '0');
INSERT INTO `t_menu` VALUES ('6', '1', '会员审核', '/', '0');
INSERT INTO `t_menu` VALUES ('7', '5', '所有会员', '/user/all/list', '0');
INSERT INTO `t_menu` VALUES ('8', '5', '在线会员', '/user/activity/list', '0');
INSERT INTO `t_menu` VALUES ('9', '5', '异常会员', '/user/exception/list', '0');
INSERT INTO `t_menu` VALUES ('10', '2', '系统字典', '/sys/dictionary/list', '0');
INSERT INTO `t_menu` VALUES ('11', '2', '操作日志', '/sys/operation/log/list', '0');

-- ----------------------------
-- Table structure for t_page_element
-- ----------------------------
DROP TABLE IF EXISTS `t_page_element`;
CREATE TABLE `t_page_element` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `code` varchar(100) COLLATE utf8_unicode_ci NOT NULL COMMENT '标识',
  `name` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '名称',
  `status` tinyint(4) DEFAULT '0' COMMENT '状态（0-正常，1-无效）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of t_page_element
-- ----------------------------

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `name` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '名称',
  `describe` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '描述',
  `status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '状态（0-正常，1-无效）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES ('1', 'supadmin', '超级管理员', '0');
INSERT INTO `t_role` VALUES ('2', 'admin', '管理员', '0');
INSERT INTO `t_role` VALUES ('3', 'manager', '经理', '0');

-- ----------------------------
-- Table structure for t_role_authority
-- ----------------------------
DROP TABLE IF EXISTS `t_role_authority`;
CREATE TABLE `t_role_authority` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `role_id` int(11) NOT NULL COMMENT '角色编号',
  `authority_id` int(11) NOT NULL COMMENT '权限编号',
  PRIMARY KEY (`id`),
  KEY `authority_id2` (`authority_id`),
  KEY `role_id2` (`role_id`),
  CONSTRAINT `authority_id2` FOREIGN KEY (`authority_id`) REFERENCES `t_authority` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `role_id2` FOREIGN KEY (`role_id`) REFERENCES `t_role` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of t_role_authority
-- ----------------------------
INSERT INTO `t_role_authority` VALUES ('1', '1', '1');
INSERT INTO `t_role_authority` VALUES ('2', '1', '2');
INSERT INTO `t_role_authority` VALUES ('3', '1', '3');
INSERT INTO `t_role_authority` VALUES ('4', '1', '4');
INSERT INTO `t_role_authority` VALUES ('5', '2', '1');
INSERT INTO `t_role_authority` VALUES ('6', '2', '2');
INSERT INTO `t_role_authority` VALUES ('7', '2', '3');
INSERT INTO `t_role_authority` VALUES ('8', '3', '1');
INSERT INTO `t_role_authority` VALUES ('9', '3', '3');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `name` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '用户名',
  `username` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '登陆账号名',
  `password` varchar(100) COLLATE utf8_unicode_ci NOT NULL COMMENT '密码',
  `salt` varchar(100) COLLATE utf8_unicode_ci NOT NULL COMMENT '密码盐',
  `email` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(11) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '手机号',
  `status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '状态（0-正常，1-无效）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', '张三丰', 'zhangsanfeng', '111111', '1', 'zsf@email.com', '', '0');
INSERT INTO `t_user` VALUES ('2', '令狐冲', 'linghuchong', '111111', '1', 'lhc@emal.com', null, '0');
INSERT INTO `t_user` VALUES ('3', '小龙女', 'xiaolongnv', '111111', '1', 'xln@email.com', null, '0');
INSERT INTO `t_user` VALUES ('4', '杨过', 'yangguo', '111111', '1', 'yg@enail.com', null, '0');
INSERT INTO `t_user` VALUES ('5', '李连杰', 'lilianjie', '111111', '1', 'llj@email.com', null, '0');
INSERT INTO `t_user` VALUES ('6', '郭靖', 'guojing', '111111', '1', 'gj@email.com', null, '0');
INSERT INTO `t_user` VALUES ('7', '黄蓉', 'huangrong', '111111', '1', 'hr@email.com', null, '0');
INSERT INTO `t_user` VALUES ('8', '张无忌', 'zhangwuji', '111111', '1', 'zwj@email.com', null, '0');
INSERT INTO `t_user` VALUES ('9', '金庸', 'jinyong', '111111', '1', 'jy@email.com', null, '0');
INSERT INTO `t_user` VALUES ('10', '风清扬', 'fengqingyang', '111111', '1', 'fqy@email.com', null, '0');

-- ----------------------------
-- Table structure for t_user_group
-- ----------------------------
DROP TABLE IF EXISTS `t_user_group`;
CREATE TABLE `t_user_group` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `name` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '名称',
  `pid` int(11) NOT NULL COMMENT '上级编号',
  `describe` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '描述',
  `status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '状态（0-正常，1-无效）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of t_user_group
-- ----------------------------
INSERT INTO `t_user_group` VALUES ('1', '董事会', '0', '', '0');
INSERT INTO `t_user_group` VALUES ('2', '总经理', '1', null, '0');
INSERT INTO `t_user_group` VALUES ('3', '部门经理', '2', null, '0');
INSERT INTO `t_user_group` VALUES ('4', '主管', '3', null, '0');
INSERT INTO `t_user_group` VALUES ('5', '专员', '4', null, '0');

-- ----------------------------
-- Table structure for t_user_group_role
-- ----------------------------
DROP TABLE IF EXISTS `t_user_group_role`;
CREATE TABLE `t_user_group_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_group_id` int(11) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_group_id1` (`user_group_id`),
  KEY `role_id1` (`role_id`),
  CONSTRAINT `role_id1` FOREIGN KEY (`role_id`) REFERENCES `t_role` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `user_group_id1` FOREIGN KEY (`user_group_id`) REFERENCES `t_user_group` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of t_user_group_role
-- ----------------------------

-- ----------------------------
-- Table structure for t_user_group_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user_group_user`;
CREATE TABLE `t_user_group_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `user_group_id` int(11) NOT NULL COMMENT '用户组编号',
  `user_id` int(11) NOT NULL COMMENT '用户编号',
  `status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '状态（0-正常，1-无效）',
  PRIMARY KEY (`id`),
  KEY `user_group_id` (`user_group_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `user_group_id` FOREIGN KEY (`user_group_id`) REFERENCES `t_user_group` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `user_id` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of t_user_group_user
-- ----------------------------
INSERT INTO `t_user_group_user` VALUES ('1', '1', '9', '0');
INSERT INTO `t_user_group_user` VALUES ('3', '1', '10', '0');
INSERT INTO `t_user_group_user` VALUES ('4', '2', '5', '0');
INSERT INTO `t_user_group_user` VALUES ('5', '3', '1', '0');
INSERT INTO `t_user_group_user` VALUES ('6', '3', '6', '0');
INSERT INTO `t_user_group_user` VALUES ('7', '4', '7', '0');
INSERT INTO `t_user_group_user` VALUES ('8', '4', '2', '0');
INSERT INTO `t_user_group_user` VALUES ('9', '5', '3', '0');
INSERT INTO `t_user_group_user` VALUES ('10', '5', '4', '0');
INSERT INTO `t_user_group_user` VALUES ('11', '5', '8', '0');
