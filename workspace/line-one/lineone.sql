/*
Navicat MySQL Data Transfer

Source Server         : wuban
Source Server Version : 50521
Source Host           : localhost:3306
Source Database       : lineone

Target Server Type    : MYSQL
Target Server Version : 50521
File Encoding         : 65001

Date: 2012-05-03 10:36:09
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `articles`
-- ----------------------------
DROP TABLE IF EXISTS `articles`;
CREATE TABLE `articles` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(200) DEFAULT NULL,
  `body` text,
  PRIMARY KEY (`id`),
  FULLTEXT KEY `title` (`title`,`body`)
) ENGINE=MyISAM AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of articles
-- ----------------------------
INSERT INTO articles VALUES ('9', 'MySQL Tutorial', 'DBMS stands for DataBase ...');
INSERT INTO articles VALUES ('10', 'How To Use MySQL Well', 'After you went through a ...');
INSERT INTO articles VALUES ('11', 'Optimizing MySQL', 'In this tutorial we will show ...');
INSERT INTO articles VALUES ('12', '1001 MySQL Tricks', '1. Never run mysqld as root. 2. ...');
INSERT INTO articles VALUES ('13', 'MySQL vs. YourSQL', 'In the following database comparison ...');
INSERT INTO articles VALUES ('14', 'MySQL Security', 'When configured properly, MySQL ...');
INSERT INTO articles VALUES ('1', '发到打发打发', '打发打发打发');
INSERT INTO articles VALUES ('2', '地方法', '打发打发');
INSERT INTO articles VALUES ('3', '吴办，多少多少多少', '五班试试电视上');

-- ----------------------------
-- Table structure for `circle`
-- ----------------------------
DROP TABLE IF EXISTS `circle`;
CREATE TABLE `circle` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) NOT NULL,
  `descr` varchar(256) DEFAULT NULL,
  `totalNum` int(11) NOT NULL DEFAULT '0',
  `createTime` datetime NOT NULL,
  `ispublic` bit(1) NOT NULL,
  `ownerid` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of circle
-- ----------------------------

-- ----------------------------
-- Table structure for `circle_lineone`
-- ----------------------------
DROP TABLE IF EXISTS `circle_lineone`;
CREATE TABLE `circle_lineone` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `lineoneid` int(11) NOT NULL,
  `circleid` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of circle_lineone
-- ----------------------------

-- ----------------------------
-- Table structure for `comment`
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` text NOT NULL,
  `createTime` datetime NOT NULL,
  `lineoneid` int(11) NOT NULL,
  `userid` int(11) NOT NULL,
  `commentid` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of comment
-- ----------------------------

-- ----------------------------
-- Table structure for `lineone`
-- ----------------------------
DROP TABLE IF EXISTS `lineone`;
CREATE TABLE `lineone` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` mediumtext NOT NULL,
  `createTime` datetime NOT NULL,
  `userid` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of lineone
-- ----------------------------
INSERT INTO lineone VALUES ('1', '爱爱爱', '2012-05-01 22:55:56', '1');
INSERT INTO lineone VALUES ('2', '啊啊是', '2012-05-01 22:58:54', '1');
INSERT INTO lineone VALUES ('3', '搜索', '2012-05-01 23:11:17', '1');
INSERT INTO lineone VALUES ('4', '搜索', '2012-05-02 09:25:30', '1');
INSERT INTO lineone VALUES ('5', '啊啊', '2012-05-02 09:26:38', '1');
INSERT INTO lineone VALUES ('6', '搜索', '2012-05-02 09:28:18', '1');
INSERT INTO lineone VALUES ('7', '搜索', '2012-05-02 09:29:25', '1');
INSERT INTO lineone VALUES ('8', '的士速递', '2012-05-02 09:30:25', '1');
INSERT INTO lineone VALUES ('9', '撒啊啊是', '2012-05-02 09:32:12', '1');
INSERT INTO lineone VALUES ('10', '生生世世', '2012-05-02 09:36:51', '1');
INSERT INTO lineone VALUES ('11', '生生世世撒飒飒', '2012-05-02 09:37:05', '1');

-- ----------------------------
-- Table structure for `nav`
-- ----------------------------
DROP TABLE IF EXISTS `nav`;
CREATE TABLE `nav` (
  `id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of nav
-- ----------------------------

-- ----------------------------
-- Table structure for `opinion`
-- ----------------------------
DROP TABLE IF EXISTS `opinion`;
CREATE TABLE `opinion` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(512) NOT NULL,
  `createTime` datetime NOT NULL,
  `userid` int(11) NOT NULL,
  `status` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of opinion
-- ----------------------------

-- ----------------------------
-- Table structure for `skin`
-- ----------------------------
DROP TABLE IF EXISTS `skin`;
CREATE TABLE `skin` (
  `id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of skin
-- ----------------------------

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(32) NOT NULL,
  `password` varchar(32) NOT NULL,
  `nick` varchar(32) NOT NULL,
  `email` varchar(32) DEFAULT NULL,
  `phone` varchar(16) DEFAULT NULL,
  `stauts` int(11) NOT NULL DEFAULT '0',
  `pic` varchar(256) DEFAULT NULL,
  `createTime` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO user VALUES ('1', '495081611@qq.com', 'e511d2e27e09c2ea440e6d2f2db6b7f3', '495081611@qq.com', null, null, '0', null, '2012-05-01 13:28:04');

-- ----------------------------
-- Table structure for `user_circle`
-- ----------------------------
DROP TABLE IF EXISTS `user_circle`;
CREATE TABLE `user_circle` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `circleid` int(11) NOT NULL,
  `userid` int(11) NOT NULL,
  `createTime` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_circle
-- ----------------------------
