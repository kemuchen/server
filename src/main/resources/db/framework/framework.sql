/*
 Navicat MySQL Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80017
 Source Host           : 127.0.0.1:3306
 Source Schema         : framework

 Target Server Type    : MySQL
 Target Server Version : 80017
 File Encoding         : 65001

 Date: 16/09/2020 11:14:32
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_administrative_division
-- ----------------------------
DROP TABLE IF EXISTS `sys_administrative_division`;
CREATE TABLE `sys_administrative_division`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `division_no` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '行政区划号码',
  `division_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '行政区划名称',
  `full_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '全称',
  `latitude` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '精度',
  `longtitude` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '纬度',
  `parent_division_id` int(11) NULL DEFAULT 0 COMMENT '父行政区划id',
  `valid` varchar(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '1' COMMENT '是否有效',
  `create_user` int(11) NULL DEFAULT 0 COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_user` int(11) NULL DEFAULT 0 COMMENT '修改人',
  `modify_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `memo` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 46379 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '行政区划信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_config`;
CREATE TABLE `sys_config`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `config_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '参数名称',
  `config_desc` varchar(24) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '参数说明',
  `config_value` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '参数值',
  `valid` varchar(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '1' COMMENT '是否有效',
  `create_user` int(11) NULL DEFAULT 0 COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_user` int(11) NULL DEFAULT 0 COMMENT '修改人',
  `modify_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `memo` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 24 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '系统参数配置表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `dicttype` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '字典项类别',
  `typename` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '字典项类型名称',
  `dictcode` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '字典项代码',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '字典项代码描述',
  `valid` varchar(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '1' COMMENT '是否有效',
  `create_user` int(11) NULL DEFAULT 0 COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_user` int(11) NULL DEFAULT 0 COMMENT '修改人',
  `modify_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `memo` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 119 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '字典信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_dynamic_dict_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_dynamic_dict_config`;
CREATE TABLE `sys_dynamic_dict_config`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `configid` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '配置id',
  `multsql` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '多记录查询语句',
  `params` varchar(4000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '查询条件参数',
  `valid` varchar(6) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '有效标志',
  `description` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '动态字典描述',
  `create_user` int(11) NULL DEFAULT 0 COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_user` int(11) NULL DEFAULT 0 COMMENT '修改人',
  `modify_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `memo` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '动态字典配置表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '用户名',
  `operation` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '用户操作',
  `method` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '调用方法',
  `params` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '请求参数',
  `operation_time` bigint(20) NULL DEFAULT NULL COMMENT '执行时长',
  `ip` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT 'ip地址',
  `operation_result` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '执行结果',
  `error_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '错误代码',
  `error_desc` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '错误描述',
  `operation_return` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '返回信息',
  `valid` varchar(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '1' COMMENT '是否有效',
  `create_user` int(11) NULL DEFAULT 0 COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_user` int(11) NULL DEFAULT 0 COMMENT '修改人',
  `modify_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `memo` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 464 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '系统日志表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_log
-- ----------------------------
INSERT INTO `sys_log` VALUES (1, '', 'doLogin', 'cn.framework.system.service.impl.user.BusinessUserServiceImplements.doLogin(BusinessUserServiceImplements.java:124)', NULL, 0, '127.0.0.1', 'n.framework.security.exception.AppException\r\n	at cn.framework.system.service.impl.user.BusinessUserServiceImplements.doLogin(BusinessUserServiceImplements.java:124)\r\n	at cn.framework.system.service.impl.user.BusinessUserServiceImplements.login(BusinessUserServiceImplements.java:68)\r\n	at cn.framework.system.web.user.UserMoldPostController.login(UserMoldPostController.java:42)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)\r\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\r\n	at java.lang.reflect.Method.invoke(Method.java:498)\r\n	at org.springframework.web.method.support.InvocableHandlerMethod.doInvoke(InvocableHandlerMethod.java:190)\r\n	at org.springframework.web.method.support.InvocableHandlerMethod.invokeForRequest(InvocableHandlerMethod.java:138)\r\n	at org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod.invokeAndHand', '4001', '用户不存在或密码错误', '{\"code\":\"4001\",\"message\":\"用户不存在或密码错误\"}', '1', 1, '2020-09-15 15:47:30', 0, '2020-09-15 15:47:30', '');
INSERT INTO `sys_log` VALUES (2, '超级管理员', 'doLogin', 'cn.framework.system.service.impl.user.BusinessUserServiceImplements.doLogin(BusinessUserServiceImplements.java:126)', NULL, 0, '127.0.0.1', 'n.framework.security.exception.AppException\r\n	at cn.framework.system.service.impl.user.BusinessUserServiceImplements.doLogin(BusinessUserServiceImplements.java:126)\r\n	at cn.framework.system.service.impl.user.BusinessUserServiceImplements.login(BusinessUserServiceImplements.java:68)\r\n	at cn.framework.system.web.user.UserMoldPostController.login(UserMoldPostController.java:42)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)\r\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\r\n	at java.lang.reflect.Method.invoke(Method.java:498)\r\n	at org.springframework.web.method.support.InvocableHandlerMethod.doInvoke(InvocableHandlerMethod.java:190)\r\n	at org.springframework.web.method.support.InvocableHandlerMethod.invokeForRequest(InvocableHandlerMethod.java:138)\r\n	at org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod.invokeAndHand', '4001', '用户不存在或密码错误', '{\"code\":\"4001\",\"message\":\"用户不存在或密码错误\"}', '1', 1, '2020-09-15 16:06:24', 0, '2020-09-15 16:06:24', '');
INSERT INTO `sys_log` VALUES (3, '超级管理员', 'getRightsByUserId', 'cn.framework.system.service.impl.right.SysRightServiceImplements.getRightsByUserId(SysRightServiceImplements.java:171)', NULL, 0, '127.0.0.1', 'n.framework.security.exception.AppException\r\n	at cn.framework.system.service.impl.right.SysRightServiceImplements.getRightsByUserId(SysRightServiceImplements.java:171)\r\n	at cn.framework.system.service.impl.right.BusinessRightServiceImplements.getRightsByUserId(BusinessRightServiceImplements.java:50)\r\n	at cn.framework.system.web.right.RightMoldGetController.rights(RightMoldGetController.java:44)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)\r\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\r\n	at java.lang.reflect.Method.invoke(Method.java:498)\r\n	at org.springframework.web.method.support.InvocableHandlerMethod.doInvoke(InvocableHandlerMethod.java:190)\r\n	at org.springframework.web.method.support.InvocableHandlerMethod.invokeForRequest(InvocableHandlerMethod.java:138)\r\n	at org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandler', '50000', '系统程序报错', '{\"code\":\"50000\",\"message\":\"未知错误\"}', '1', 1, '2020-09-15 16:23:00', 0, '2020-09-15 16:23:00', '');
INSERT INTO `sys_log` VALUES (4, '超级管理员', 'getRightsByUserId', 'cn.framework.system.service.impl.right.SysRightServiceImplements.getRightsByUserId(SysRightServiceImplements.java:171)', NULL, 0, '127.0.0.1', 'n.framework.security.exception.AppException\r\n	at cn.framework.system.service.impl.right.SysRightServiceImplements.getRightsByUserId(SysRightServiceImplements.java:171)\r\n	at cn.framework.system.service.impl.right.BusinessRightServiceImplements.getRightsByUserId(BusinessRightServiceImplements.java:50)\r\n	at cn.framework.system.web.right.RightMoldGetController.rights(RightMoldGetController.java:44)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)\r\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\r\n	at java.lang.reflect.Method.invoke(Method.java:498)\r\n	at org.springframework.web.method.support.InvocableHandlerMethod.doInvoke(InvocableHandlerMethod.java:190)\r\n	at org.springframework.web.method.support.InvocableHandlerMethod.invokeForRequest(InvocableHandlerMethod.java:138)\r\n	at org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandler', '50000', '系统程序报错', '{\"code\":\"50000\",\"message\":\"未知错误\"}', '1', 1, '2020-09-15 16:25:18', 0, '2020-09-15 16:25:18', '');

-- ----------------------------
-- Table structure for sys_login_history
-- ----------------------------
DROP TABLE IF EXISTS `sys_login_history`;
CREATE TABLE `sys_login_history`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `userid` int(11) NOT NULL COMMENT '用户id',
  `login_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '登录时间',
  `login_rank` int(11) NULL DEFAULT NULL COMMENT '工作班次,对应sys_dirct中SHIFT类型ID',
  `login_ip` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '登录IP',
  `login_result` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '登录结果',
  `token` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '会话token',
  `is_logout` varchar(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '0' COMMENT '是否退出 1-是；0-否',
  `valid` varchar(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '1' COMMENT '是否有效',
  `create_user` int(11) NULL DEFAULT 0 COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_user` int(11) NULL DEFAULT 0 COMMENT '修改人',
  `modify_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `memo` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2359 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '登录历史信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_login_history
-- ----------------------------
INSERT INTO `sys_login_history` VALUES (1, 1, '2020-09-15 15:48:02', NULL, '192.168.1.13', '登录成功!', '60814db9-2abc-4d76-a51e-f6d1bf37efb9', '0', '1', 1, '2020-09-15 15:48:02', 1, '2020-09-15 15:48:02', '');
INSERT INTO `sys_login_history` VALUES (2, 1, '2020-09-15 15:48:13', NULL, '192.168.1.13', '登录成功!', '52b148b0-11fa-4112-8543-b714a29a1232', '0', '1', 1, '2020-09-15 15:48:13', 1, '2020-09-15 15:48:13', '');
INSERT INTO `sys_login_history` VALUES (3, 1, '2020-09-15 15:49:04', NULL, '192.168.1.13', '登录成功!', '339070f7-0648-45e2-a5f7-5391bfd4c38c', '0', '1', 1, '2020-09-15 15:49:04', 1, '2020-09-15 15:49:04', '');
INSERT INTO `sys_login_history` VALUES (4, 1, '2020-09-15 15:49:19', NULL, '192.168.1.13', '登录成功!', 'b8ba0ac4-9088-4d30-a880-6ad40b7bc41a', '0', '1', 1, '2020-09-15 15:49:19', 1, '2020-09-15 15:49:19', '');
INSERT INTO `sys_login_history` VALUES (5, 1, '2020-09-15 15:52:56', NULL, '192.168.1.13', '登录成功!', 'ce2967bd-57e3-488f-a995-3f8713d6b740', '0', '1', 1, '2020-09-15 15:52:56', 1, '2020-09-15 15:52:56', '');
INSERT INTO `sys_login_history` VALUES (6, 1, '2020-09-15 15:53:08', NULL, '192.168.1.13', '登录成功!', '284bf006-2113-4f92-9d39-6fc48d27b5b2', '0', '1', 1, '2020-09-15 15:53:08', 1, '2020-09-15 15:53:08', '');
INSERT INTO `sys_login_history` VALUES (7, 1, '2020-09-15 15:53:14', NULL, '192.168.1.13', '登录成功!', 'ce58c17a-c182-448f-8d51-c3fe92a728c8', '0', '1', 1, '2020-09-15 15:53:14', 1, '2020-09-15 15:53:14', '');
INSERT INTO `sys_login_history` VALUES (8, 1, '2020-09-15 15:56:56', NULL, '192.168.1.13', '登录成功!', 'feb9f208-63a3-4d68-9ab8-e6c7d95d881f', '0', '1', 1, '2020-09-15 15:56:56', 1, '2020-09-15 15:56:56', '');
INSERT INTO `sys_login_history` VALUES (9, 1, '2020-09-15 15:57:39', NULL, '192.168.1.13', '登录成功!', 'c2ceead3-fc9d-4bb1-8287-2870bfd1fbc2', '0', '1', 1, '2020-09-15 15:57:39', 1, '2020-09-15 15:57:39', '');
INSERT INTO `sys_login_history` VALUES (10, 1, '2020-09-15 15:58:38', NULL, '192.168.1.13', '登录成功!', 'f00ff7a9-16c6-4342-91da-3eba2db2a5f1', '0', '1', 1, '2020-09-15 15:58:38', 1, '2020-09-15 15:58:38', '');
INSERT INTO `sys_login_history` VALUES (11, 1, '2020-09-15 16:01:40', NULL, '192.168.1.13', '登录成功!', '362a4370-9864-47c4-acab-7d53fcc5afbe', '0', '1', 1, '2020-09-15 16:01:40', 1, '2020-09-15 16:01:40', '');
INSERT INTO `sys_login_history` VALUES (12, 1, '2020-09-15 16:02:17', NULL, '192.168.1.13', '登录成功!', '27548409-47ec-4263-accb-190d1f8a769f', '0', '1', 1, '2020-09-15 16:02:17', 1, '2020-09-15 16:02:17', '');
INSERT INTO `sys_login_history` VALUES (13, 1, '2020-09-15 16:04:39', NULL, '192.168.1.13', '登录成功!', '4fee88bc-29ce-4fdb-9d7a-9cb3eec016d2', '0', '1', 1, '2020-09-15 16:04:39', 1, '2020-09-15 16:04:39', '');
INSERT INTO `sys_login_history` VALUES (14, 1, '2020-09-15 16:05:06', NULL, '192.168.1.13', '登录成功!', 'd26bdfff-f08b-4f52-9f27-09d8a73bdf91', '0', '1', 1, '2020-09-15 16:05:06', 1, '2020-09-15 16:05:06', '');
INSERT INTO `sys_login_history` VALUES (15, 1, '2020-09-15 16:06:29', NULL, '192.168.1.13', '登录成功!', 'a6348a53-a72b-4af6-8eb3-2e39e3b7fd8f', '0', '1', 1, '2020-09-15 16:06:29', 1, '2020-09-15 16:06:29', '');
INSERT INTO `sys_login_history` VALUES (16, 1, '2020-09-15 16:08:54', NULL, '192.168.1.13', '登录成功!', '949047bb-24eb-4041-8b19-0f07eb644698', '0', '1', 1, '2020-09-15 16:08:54', 1, '2020-09-15 16:08:54', '');
INSERT INTO `sys_login_history` VALUES (17, 1, '2020-09-15 16:18:15', NULL, '192.168.1.13', '登录成功!', '000c6679-197b-4e32-b595-7c795c35f6f6', '0', '1', 1, '2020-09-15 16:18:15', 1, '2020-09-15 16:18:15', '');
INSERT INTO `sys_login_history` VALUES (18, 1, '2020-09-15 16:47:37', NULL, '192.168.1.13', '登录成功!', '3ba138da-341d-4423-82bc-e2504230aab9', '0', '1', 1, '2020-09-15 16:47:37', 1, '2020-09-15 16:47:37', '');
INSERT INTO `sys_login_history` VALUES (19, 1, '2020-09-15 16:55:06', NULL, '192.168.1.13', '登录成功!', '697abde8-5069-4ff5-9aa4-02421f6fb3ab', '0', '1', 1, '2020-09-15 16:55:06', 1, '2020-09-15 16:55:06', '');
INSERT INTO `sys_login_history` VALUES (20, 1, '2020-09-16 09:20:47', NULL, '192.168.1.13', '登录成功!', '9720487f-e20f-4fcb-94c7-4c275fbd0a13', '0', '1', 1, '2020-09-16 09:20:47', 1, '2020-09-16 09:20:47', '');
INSERT INTO `sys_login_history` VALUES (21, 2, '2020-09-16 11:13:02', NULL, '192.168.1.13', '登录成功!', 'c535053a-2209-4a5e-81f3-266e5709a7a9', '0', '1', 2, '2020-09-16 11:13:02', 2, '2020-09-16 11:13:02', '');

-- ----------------------------
-- Table structure for sys_message
-- ----------------------------
DROP TABLE IF EXISTS `sys_message`;
CREATE TABLE `sys_message`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `message_type` varchar(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '消息类别 sys_dict',
  `title` varchar(400) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '消息标题',
  `content` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '消息内容',
  `send_time` datetime(0) NULL DEFAULT NULL COMMENT '消息发送时间',
  `receive_man` int(11) NULL DEFAULT NULL COMMENT '消息接收人 外键，对应sys_user表主键',
  `is_send` varchar(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '0' COMMENT '是否发送',
  `read_time` datetime(0) NULL DEFAULT NULL COMMENT '阅读时间',
  `is_read` varchar(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '0' COMMENT '是否阅读 1-是，0-否',
  `valid` varchar(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '1' COMMENT '是否有效 1-是，0-否',
  `create_user` int(11) NULL DEFAULT 0 COMMENT '创建人 外键 对应sys_user表主键',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_user` int(11) NULL DEFAULT 0 COMMENT '修改人',
  `modify_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `memo` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2279 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '系统消息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_no_rule
-- ----------------------------
DROP TABLE IF EXISTS `sys_no_rule`;
CREATE TABLE `sys_no_rule`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `code` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '单号代码',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '单号描述',
  `rule` varchar(400) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '生成规则',
  `prefix_rule` varchar(400) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '前缀规则',
  `suffix_rule` varchar(400) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '后缀规则',
  `valid` varchar(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '1' COMMENT '是否有效',
  `create_user` int(11) NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_user` int(11) NULL DEFAULT NULL COMMENT '修改人',
  `modify_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `memo` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_right
-- ----------------------------
DROP TABLE IF EXISTS `sys_right`;
CREATE TABLE `sys_right`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键，菜单id',
  `sub_sys_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '子系统类别',
  `right_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '权限名称',
  `right_type` varchar(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '权限类别',
  `right_level` varchar(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '权限级别',
  `right_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '权限链接',
  `icon` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '权限图标',
  `sort_no` int(11) NULL DEFAULT NULL COMMENT '权限排序',
  `authed` varchar(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '是否校验权限',
  `parent_id` int(11) NULL DEFAULT NULL COMMENT 'sys_right表主键',
  `valid` varchar(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '1' COMMENT '是否有效',
  `create_user` int(11) NULL DEFAULT 0 COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_user` int(11) NULL DEFAULT 0 COMMENT '修改人',
  `modify_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `memo` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '权限信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_right
-- ----------------------------
INSERT INTO `sys_right` VALUES (1, 'SYSTEM', '系统管理', '1', '1', '/system', '', 1, '1', 0, '1', 1, '2020-09-15 14:27:25', 1, '2020-09-15 14:27:25', NULL);
INSERT INTO `sys_right` VALUES (2, 'SYSTEM', '用户管理', '1', '2', '/users', NULL, 5, '1', 1, '1', 1, '2020-09-15 14:28:41', 1, '2020-09-15 14:28:41', NULL);
INSERT INTO `sys_right` VALUES (3, 'SYSTEM', '角色管理', '1', '2', '/role', NULL, 10, '1', 1, '1', 1, '2020-09-15 14:29:43', 1, '2020-09-15 14:29:43', NULL);
INSERT INTO `sys_right` VALUES (4, 'SYSTEM', '主页', '0', '1', '/', NULL, 99, '1', 0, '1', 1, '2020-09-15 15:54:43', 1, '2020-09-15 15:54:43', NULL);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '角色名称',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '角色描述',
  `valid` varchar(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '1' COMMENT '是否有效',
  `create_user` int(11) NULL DEFAULT 0 COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_user` int(11) NULL DEFAULT 0 COMMENT '修改人',
  `modify_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `memo` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 27 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '角色信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, 'admin', '超级管理员', '1', 1, '2020-09-15 14:30:56', 1, '2020-09-16 10:20:04', '超级管理员');
INSERT INTO `sys_role` VALUES (2, 'system', '系统管理员', '1', 1, '2020-09-16 10:18:59', 1, '2020-09-16 10:19:10', '测试');

-- ----------------------------
-- Table structure for sys_role2right
-- ----------------------------
DROP TABLE IF EXISTS `sys_role2right`;
CREATE TABLE `sys_role2right`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `roleid` int(11) NOT NULL COMMENT '角色id',
  `rightid` int(11) NOT NULL COMMENT '菜单id',
  `valid` varchar(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '1' COMMENT '是否有效',
  `create_user` int(11) NULL DEFAULT 0 COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_user` int(11) NULL DEFAULT 0 COMMENT '修改人',
  `modify_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `memo` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 186 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '角色权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role2right
-- ----------------------------
INSERT INTO `sys_role2right` VALUES (1, 1, 1, '1', 1, '2020-09-15 14:31:22', 1, '2020-09-15 14:31:22', NULL);
INSERT INTO `sys_role2right` VALUES (2, 1, 2, '1', 1, '2020-09-15 14:31:28', 1, '2020-09-15 14:31:28', NULL);
INSERT INTO `sys_role2right` VALUES (3, 1, 3, '1', 1, '2020-09-15 14:31:33', 1, '2020-09-15 14:31:33', NULL);
INSERT INTO `sys_role2right` VALUES (4, 1, 4, '1', 1, '2020-09-15 16:28:08', 1, '2020-09-16 10:18:09', NULL);

-- ----------------------------
-- Table structure for sys_sequence
-- ----------------------------
DROP TABLE IF EXISTS `sys_sequence`;
CREATE TABLE `sys_sequence`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `sequence` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '序列',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '序列名称',
  `current_value` int(11) NULL DEFAULT NULL COMMENT '序列当前值',
  `increment` int(11) NULL DEFAULT NULL COMMENT '序列增长量',
  `max_value` int(11) NULL DEFAULT NULL COMMENT '序列最大值',
  `circle` varchar(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '是否循环序列',
  `valid` varchar(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '1' COMMENT '是否有效',
  `create_user` int(11) NULL DEFAULT 0 COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_user` int(11) NULL DEFAULT 0 COMMENT '修改人',
  `modify_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `memo` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '序列信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(24) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '用户名',
  `loginid` varchar(24) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '登录id',
  `password` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '登录密码',
  `phone` varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '手机号码',
  `user_type` varchar(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '用户类型',
  `email` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '邮箱',
  `avator` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '头像',
  `status` varchar(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '用户状态',
  `login_fail_count` int(11) NULL DEFAULT NULL COMMENT '登录失败次数',
  `last_login_time` datetime(0) NULL DEFAULT NULL COMMENT '最近一次登录时间',
  `valid` varchar(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '1' COMMENT '是否有效',
  `create_user` int(11) NULL DEFAULT 0 COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_user` int(11) NULL DEFAULT 0 COMMENT '修改人',
  `modify_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `memo` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 35 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '用户信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, '超级管理员', 'admin', '0cc175b9c0f1b6a831c399e269772661', '', '1', NULL, NULL, '1', 0, '2020-09-15 14:24:43', '1', 0, '2020-09-15 14:24:46', 0, '2020-09-15 14:24:46', NULL);
INSERT INTO `sys_user` VALUES (2, '系统管理员', 'system', '0cc175b9c0f1b6a831c399e269772661', NULL, NULL, NULL, NULL, '1', 0, '2020-09-16 10:37:34', '1', 1, '2020-09-16 10:37:34', 1, '2020-09-16 10:37:34', '');
INSERT INTO `sys_user` VALUES (3, '测试', 'test', '0cc175b9c0f1b6a831c399e269772661', NULL, NULL, NULL, NULL, '1', 0, '2020-09-16 10:38:32', '0', 1, '2020-09-16 10:38:32', 1, '2020-09-16 10:41:18', '测试');

-- ----------------------------
-- Table structure for sys_user2role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user2role`;
CREATE TABLE `sys_user2role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `userid` int(11) NOT NULL COMMENT '用户id',
  `roleid` int(11) NOT NULL COMMENT '角色id',
  `valid` varchar(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '1' COMMENT '是否有效',
  `create_user` int(11) NULL DEFAULT 0 COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_user` int(11) NULL DEFAULT 0 COMMENT '修改人',
  `modify_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `memo` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 39 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '用户角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user2role
-- ----------------------------
INSERT INTO `sys_user2role` VALUES (1, 1, 1, '1', 1, '2020-09-15 16:29:07', 1, '2020-09-15 16:29:07', NULL);
INSERT INTO `sys_user2role` VALUES (2, 2, 1, '1', 1, '2020-09-16 11:12:30', 1, '2020-09-16 11:12:30', '');

-- ----------------------------
-- Table structure for table_update_log
-- ----------------------------
DROP TABLE IF EXISTS `table_update_log`;
CREATE TABLE `table_update_log`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `table_name` varchar(24) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '修改表名',
  `table_name_desc` varchar(48) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '表名中文描述',
  `update_table_id` int(11) NULL DEFAULT NULL COMMENT '修改表的主键  外键，对应修改表的主键',
  `valid` varchar(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '1' COMMENT '是否有效',
  `create_user` int(11) NULL DEFAULT 0 COMMENT '创建人 外键 对应sys_user表主键',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_user` int(11) NULL DEFAULT 0 COMMENT '修改人',
  `modify_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `memo` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 26991 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '表记录修改日志' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for table_update_log_detail
-- ----------------------------
DROP TABLE IF EXISTS `table_update_log_detail`;
CREATE TABLE `table_update_log_detail`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `table_update_log_id` int(11) NULL DEFAULT NULL COMMENT '代码操作日志表id 外键，对应code_update_log表主键',
  `field` varchar(24) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '修改字段',
  `field_name` varchar(48) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '字段中文描述',
  `is_dict` varchar(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '是否字典项 1-是，0-否',
  `old_value` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '修改前值',
  `new_value` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '修改后值',
  `old_value_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '修改前字典中文描述',
  `new_value_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '修改后字典中文描述',
  `valid` varchar(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '1' COMMENT '是否有效 1-是，0-否',
  `create_user` int(11) NULL DEFAULT 0 COMMENT '创建人 外键，对应sys_user表主键',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_user` int(11) NULL DEFAULT 0 COMMENT '修改人 外键，对应sys_user表主键',
  `modify_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `memo` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 36853 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '表记录修改明细' ROW_FORMAT = Dynamic;

-- ----------------------------
-- View structure for v_user_right
-- ----------------------------
DROP VIEW IF EXISTS `v_user_right`;
CREATE ALGORITHM = UNDEFINED DEFINER = `root`@`localhost` SQL SECURITY DEFINER VIEW `v_user_right` AS select `a`.`id` AS `userid`,`a`.`username` AS `username`,`a`.`loginid` AS `loginid`,`a`.`password` AS `password`,`a`.`phone` AS `phone`,`a`.`user_type` AS `user_type`,`a`.`email` AS `email`,`a`.`avator` AS `avator`,`a`.`status` AS `status`,`e`.`id` AS `rightid`,`e`.`sub_sys_type` AS `sub_sys_type`,`e`.`parent_id` AS `parent_id`,`e`.`right_name` AS `right_name`,`e`.`right_type` AS `right_type`,`e`.`right_url` AS `right_url`,`e`.`icon` AS `icon`,`e`.`sort_no` AS `sort_no`,`e`.`authed` AS `authed` from ((((`sys_user` `a` join `sys_user2role` `b`) join `sys_role` `c`) join `sys_role2right` `d`) join `sys_right` `e`) where ((`a`.`id` = `b`.`userid`) and (`b`.`roleid` = `c`.`id`) and (`c`.`id` = `d`.`roleid`) and (`d`.`rightid` = `e`.`id`) and (`a`.`valid` = '1') and (`b`.`valid` = '1') and (`c`.`valid` = '1') and (`d`.`valid` = '1') and (`e`.`valid` = '1'));

-- ----------------------------
-- Function structure for currval
-- ----------------------------
DROP FUNCTION IF EXISTS `currval`;
delimiter ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `currval`(seq_name varchar(50)) RETURNS int(11)
    DETERMINISTIC
begin
	declare v_value int; 
	select current_value
	  from sys_sequence
	where sequence = seq_name
	 into v_value; 
	return v_value; 
end
;;
delimiter ;

-- ----------------------------
-- Function structure for nextval
-- ----------------------------
DROP FUNCTION IF EXISTS `nextval`;
delimiter ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `nextval`(seq_name varchar(50)) RETURNS int(11)
    DETERMINISTIC
begin
    declare v_current_value int;
    declare v_max_value int;
    declare v_increment int;
		declare v_circle varchar(6);
    
    -- 查询当前值和最大值
    select current_value, max_value, increment, circle
				from sys_sequence
	 where sequence = seq_name 
				into v_current_value, v_max_value, v_increment, v_circle;
	 
		 if v_current_value + v_increment > v_max_value then
					if v_circle = '1' then
							update sys_sequence
								set current_value = 1
								where sequence = seq_name; 	
					else 
							return 0;
					end if;
		 else
				update sys_sequence
						set current_value = current_value + increment 
						where sequence = seq_name; 
		end if;
	return currval(seq_name); 
end
;;
delimiter ;

-- ----------------------------
-- Function structure for setval
-- ----------------------------
DROP FUNCTION IF EXISTS `setval`;
delimiter ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `setval`(seq_name varchar(50), value integer) RETURNS int(11)
    DETERMINISTIC
begin
	update sys_sequence
	   set current_value = value 
	 where name = seq_name; 
	return currval(seq_name); 
end
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
