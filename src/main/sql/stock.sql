/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50544
Source Host           : localhost:3306
Source Database       : seckill

Target Server Type    : MYSQL
Target Server Version : 50544
File Encoding         : 65001

Date: 2017-09-21 09:14:29
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for stock
-- ----------------------------
DROP TABLE IF EXISTS `stock`;
CREATE TABLE `stock` (
  `sk_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '商品库存 id',
  `name` varchar(120) NOT NULL COMMENT '商品名',
  `number` int(11) NOT NULL COMMENT '库存数量',
  `start_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '秒杀开始时间',
  `end_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '秒杀结束时间',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`sk_id`),
  KEY `start_time` (`start_time`),
  KEY `end_time` (`end_time`),
  KEY `create_time` (`create_time`)
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of stock
-- ----------------------------
INSERT INTO `stock` VALUES ('1000', '6000元秒杀Iphone8', '100', '2017-10-01 00:00:06', '2017-10-02 23:59:59', '2017-09-20 23:32:38');
INSERT INTO `stock` VALUES ('1001', '6500元秒杀Iphone8P', '150', '2017-10-01 00:00:06', '2017-10-02 23:59:59', '2017-09-20 23:32:42');
INSERT INTO `stock` VALUES ('1002', '8000元秒杀IphoneX', '200', '2017-10-01 00:00:06', '2017-10-02 23:59:59', '2017-09-20 23:32:46');
INSERT INTO `stock` VALUES ('1003', '2000元秒杀NewIPad', '250', '2017-10-01 00:00:06', '2017-10-02 23:59:59', '2017-09-20 23:33:52');
INSERT INTO `stock` VALUES ('1004', '3000元秒杀IPadPro', '300', '2017-10-01 00:00:06', '2017-10-02 23:59:59', '2017-09-20 23:33:55');
