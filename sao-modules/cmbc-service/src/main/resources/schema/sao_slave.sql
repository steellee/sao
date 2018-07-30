/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50720
Source Host           : 127.0.0.1:3306
Source Database       : sao_slave

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2018-07-30 11:47:37
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_config_properties
-- ----------------------------
DROP TABLE IF EXISTS `sys_config_properties`;
CREATE TABLE `sys_config_properties` (
  `id` int(11) NOT NULL,
  `key` varchar(50) NOT NULL,
  `value` varchar(500) NOT NULL,
  `application` varchar(50) NOT NULL,
  `profile` varchar(50) NOT NULL,
  `label` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_config_properties
-- ----------------------------
INSERT INTO `sys_config_properties` VALUES ('1', 'com.sao.message', 'test-stage-master1', 'cmbc-service', 'stage', 'master');
INSERT INTO `sys_config_properties` VALUES ('2', 'com.sao.message', 'test-online-master2', 'config-client', 'online', 'master');
INSERT INTO `sys_config_properties` VALUES ('3', 'com.sao.message', 'test-online-develop3', 'config-client', 'online', 'develop');
INSERT INTO `sys_config_properties` VALUES ('4', 'com.sao.message', 'hello-online-master', 'hello-service', 'online', 'master');
INSERT INTO `sys_config_properties` VALUES ('5', 'com.sao.message', 'hello-online-develop', 'hello-service', 'online', 'develop');

-- ----------------------------
-- Table structure for sys_staff_info
-- ----------------------------
DROP TABLE IF EXISTS `sys_staff_info`;
CREATE TABLE `sys_staff_info` (
  `STAFF_ID` varchar(22) NOT NULL COMMENT '员工ID',
  `STAFF_CODE` varchar(22) NOT NULL COMMENT '员工工号',
  `STAFF_NAME` varchar(30) NOT NULL COMMENT '员工姓名',
  `STAFF_TYPE` varchar(10) DEFAULT NULL COMMENT 'DD员工类别',
  `ANNUAL_VACATION_DAYS` decimal(2,0) DEFAULT NULL COMMENT '年假基数天数',
  `ORGAN_ID` varchar(22) NOT NULL COMMENT '机构编号',
  `ID_CARD` varchar(18) DEFAULT NULL COMMENT '身份证号码',
  `ASSUMED_NAME` varchar(30) DEFAULT NULL COMMENT '别号(别名)',
  `SEX_TYPE` varchar(1) DEFAULT NULL COMMENT 'DD性别',
  `EDUCATION_LEVEL` varchar(1) DEFAULT NULL COMMENT 'DD学历',
  `MAJOR_TYPE` varchar(20) DEFAULT NULL COMMENT 'DD主要专业',
  `BEGIN_WORK_DATE` date DEFAULT NULL COMMENT '参加工作日期',
  `GRADUATE_SCHOOL` varchar(100) DEFAULT NULL COMMENT '毕业院校',
  `MARITAL_STATUS` varchar(1) DEFAULT NULL COMMENT 'E婚姻状况',
  `NATIONALITY` varchar(10) DEFAULT NULL COMMENT 'DD国别',
  `NATIVE_PLACE` varchar(10) DEFAULT NULL COMMENT 'DD籍贯',
  `POST_NO` varchar(6) DEFAULT NULL COMMENT '邮编',
  `ADDRESS` varchar(200) DEFAULT NULL COMMENT '家庭住址',
  `MOBILE_NO` varchar(15) DEFAULT NULL COMMENT '手机号码',
  `LINK_TEL` varchar(30) DEFAULT NULL COMMENT '联系电话',
  `QQ_CODE` varchar(30) DEFAULT NULL COMMENT 'QQ号',
  `WE_CODE` varchar(40) DEFAULT NULL COMMENT '微信号',
  `WORK_TEL` varchar(30) DEFAULT NULL COMMENT '办公电话',
  `ICE_CONTACTOR` varchar(30) DEFAULT NULL COMMENT '紧急联系人',
  `ICE_TEL` varchar(30) DEFAULT NULL COMMENT '紧急联系电话',
  `BIRTHDAY` date DEFAULT NULL COMMENT '出生日期',
  `DUTY_DATE` date DEFAULT NULL COMMENT '入职日期',
  `OUT_DUTY_DATE` date DEFAULT NULL COMMENT '离职日期',
  `POSITION_TYPE` varchar(10) DEFAULT NULL COMMENT 'DD工作岗位',
  `POSITION_LEVEL` varchar(10) DEFAULT NULL COMMENT '职能等级',
  `PHOTOPATH` varchar(200) DEFAULT NULL COMMENT '照片路径',
  `EMAIL` varchar(100) DEFAULT NULL COMMENT '邮箱地址',
  `CREATE_BY` varchar(22) NOT NULL COMMENT '创建人',
  `CREATE_TIME` datetime NOT NULL COMMENT '创建时间',
  `UPDATE_BY` varchar(22) NOT NULL COMMENT '最后修改人',
  `UPDATE_TIME` datetime NOT NULL COMMENT '最后修改时间',
  `STATUS` int(1) NOT NULL DEFAULT '1' COMMENT '在职状态(1: 在职 0:离职)',
  `ENGLISH_NAME` varchar(30) DEFAULT NULL COMMENT '英文名称',
  PRIMARY KEY (`STAFF_ID`),
  KEY `IDX_STAFF_ORGAN` (`ORGAN_ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of sys_staff_info
-- ----------------------------
INSERT INTO `sys_staff_info` VALUES ('1532187074022708978111', '1532187074022708978111', 'admin', null, null, '0', null, null, 'M', null, null, null, null, null, null, null, null, null, null, '', null, null, null, null, null, null, null, null, null, null, '', 'admin4@test.com', '20171123001', '2018-04-02 15:52:53', 'admin', '2018-05-21 11:30:41', '1', 'adminlocal');
INSERT INTO `sys_staff_info` VALUES ('1532187128513664182111', '1532187128513664182111', 'admin', null, null, '0', null, null, 'M', null, null, null, null, null, null, null, null, null, null, '', null, null, null, null, null, null, null, null, null, null, '', 'admin4@test.com', '20171123001', '2018-04-02 15:52:53', 'admin', '2018-05-21 11:30:41', '1', 'adminlocal');
INSERT INTO `sys_staff_info` VALUES ('admin', 'admin', 'admin', null, null, '0', null, null, 'M', null, null, null, null, null, null, null, null, null, null, '', null, null, null, null, null, null, null, null, null, null, '', 'admin4@test.com', '20171123001', '2018-04-02 15:52:53', 'admin', '2018-05-21 11:30:41', '1', 'adminlocal');
