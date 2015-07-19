/*
SQLyog Ultimate v11.24 (32 bit)
MySQL - 6.0.3-alpha-community : Database - game
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`game` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `xdfgame`;

/*Table structure for table `t_bag` */

DROP TABLE IF EXISTS `t_bag`;

CREATE TABLE `t_bag` (
  `ID` varchar(100) NOT NULL,
  `USER_ID` varchar(100) DEFAULT NULL,
  `NUM_N` int(11) DEFAULT NULL,
  `SCHOOL_BAG_ID` varchar(100) DEFAULT NULL,
  `TYPE_N` int(10) DEFAULT NULL,
  `YESORNO` int(10) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_Reference_1` (`USER_ID`),
  KEY `FK_Reference_10` (`SCHOOL_BAG_ID`),
  KEY `BAGUSERIDINDEX` (`USER_ID`),
  KEY `BAGSCHOOLBAGIDINDEX` (`SCHOOL_BAG_ID`),
  CONSTRAINT `FK_Reference_1` FOREIGN KEY (`USER_ID`) REFERENCES `t_user` (`ID`),
  CONSTRAINT `FK_Reference_10` FOREIGN KEY (`SCHOOL_BAG_ID`) REFERENCES `t_school_bag` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='红包表';

/*Data for the table `t_bag` */

/*Table structure for table `t_baglog` */

DROP TABLE IF EXISTS `t_baglog`;

CREATE TABLE `t_baglog` (
  `ID` varchar(100) NOT NULL,
  `BAG_ID` varchar(100) DEFAULT NULL,
  `USER_ID` varchar(100) DEFAULT NULL,
  `MONEY_D` int(100) DEFAULT NULL,
  `CONTENT_V` varchar(50) DEFAULT NULL,
  `DATE_D` varchar(100) DEFAULT NULL,
  `TYPE_N` int(11) DEFAULT NULL,
  `SCHOOL_BAG_ID` varchar(100) DEFAULT NULL,
  `VOUCHER_ID` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_Reference_7` (`BAG_ID`),
  KEY `FK_Reference_8` (`USER_ID`),
  KEY `FK_Reference_9` (`MONEY_D`),
  KEY `BAGLOGUSERIDINDEX` (`USER_ID`),
  KEY `BAGLOGBAGIDINDEX` (`BAG_ID`),
  CONSTRAINT `FK_Reference_7` FOREIGN KEY (`BAG_ID`) REFERENCES `t_bag` (`ID`),
  CONSTRAINT `FK_Reference_8` FOREIGN KEY (`USER_ID`) REFERENCES `t_user` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='红包日志表';

/*Data for the table `t_baglog` */

/*Table structure for table `t_bingo` */

DROP TABLE IF EXISTS `t_bingo`;

CREATE TABLE `t_bingo` (
  `ID` varchar(100) NOT NULL,
  `MONEY_N` int(11) DEFAULT NULL,
  `NUM_N` int(11) DEFAULT NULL,
  `SCHOOL_ID` varchar(100) DEFAULT NULL,
  `PRECENT_N` int(100) DEFAULT NULL,
  `TYPE_N` int(100) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_Reference_11` (`SCHOOL_ID`),
  CONSTRAINT `FK_Reference_11` FOREIGN KEY (`SCHOOL_ID`) REFERENCES `t_school` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='概率表';

/*Data for the table `t_bingo` */

insert  into `t_bingo`(`ID`,`MONEY_N`,`NUM_N`,`SCHOOL_ID`,`PRECENT_N`,`TYPE_N`) values ('1',10,477,'1',170,0),('10',365,759,'1',1,0),('100',365,724,'10',1,0),('101',10,753,'11',170,0),('102',23,756,'11',100,0),('103',37,777,'11',190,0),('104',51,771,'11',170,0),('105',64,742,'11',160,0),('106',79,750,'11',150,0),('107',91,759,'11',30,0),('108',155,750,'11',20,0),('109',199,727,'11',9,0),('11',10,742,'2',170,0),('110',365,727,'11',1,0),('111',10,748,'12',170,0),('112',23,703,'12',100,0),('113',37,706,'12',190,0),('114',51,777,'12',170,0),('115',64,736,'12',160,0),('116',79,756,'12',150,0),('117',91,733,'12',30,0),('118',155,724,'12',20,0),('119',199,739,'12',9,0),('12',23,727,'2',100,0),('120',365,718,'12',1,0),('121',10,759,'13',170,0),('122',23,745,'13',100,0),('123',37,677,'13',190,0),('124',51,709,'13',170,0),('125',64,703,'13',160,0),('126',79,736,'13',150,0),('127',91,727,'13',30,0),('128',155,712,'13',20,0),('129',199,700,'13',9,0),('13',37,753,'2',190,0),('130',365,730,'13',1,0),('131',10,786,'14',170,0),('132',23,689,'14',100,0),('133',37,683,'14',190,0),('134',51,692,'14',170,0),('135',64,795,'14',160,0),('136',79,733,'14',150,0),('137',91,768,'14',30,0),('138',155,718,'14',20,0),('139',199,756,'14',9,0),('14',51,759,'2',170,0),('140',365,698,'14',1,0),('141',10,421,'15',170,0),('142',23,512,'15',100,0),('143',37,465,'15',190,0),('144',51,506,'15',170,0),('145',64,465,'15',160,0),('146',79,456,'15',150,0),('147',91,495,'15',30,0),('148',155,436,'15',20,0),('149',199,745,'15',9,0),('15',64,759,'2',160,0),('150',365,736,'15',1,0),('151',10,877,'16',170,0),('152',23,780,'16',100,0),('153',37,836,'16',190,0),('154',51,386,'16',170,0),('155',64,506,'16',160,0),('156',79,718,'16',150,0),('157',91,771,'16',30,0),('158',155,733,'16',20,0),('159',199,736,'16',9,0),('16',79,727,'2',150,0),('160',365,750,'16',1,0),('161',10,421,'17',170,0),('162',23,518,'17',100,0),('163',37,489,'17',190,0),('164',51,742,'17',170,0),('165',64,712,'17',160,0),('166',79,724,'17',150,0),('167',91,689,'17',30,0),('168',155,724,'17',20,0),('169',199,689,'17',9,0),('17',91,753,'2',30,0),('170',365,742,'17',1,0),('171',10,468,'18',170,0),('172',23,442,'18',100,0),('173',37,442,'18',190,0),('174',51,748,'18',170,0),('175',64,727,'18',160,0),('176',79,683,'18',150,0),('177',91,503,'18',30,0),('178',155,683,'18',20,0),('179',199,756,'18',9,0),('18',155,724,'2',20,0),('180',365,712,'18',1,0),('181',10,477,'19',170,0),('182',23,724,'19',100,0),('183',37,380,'19',190,0),('184',51,486,'19',170,0),('185',64,500,'19',160,0),('186',79,489,'19',150,0),('187',91,527,'19',30,0),('188',155,739,'19',20,0),('189',199,721,'19',9,0),('19',199,774,'2',9,0),('190',365,724,'19',1,0),('191',10,1000,'20',170,NULL),('192',23,1000,'20',100,NULL),('193',37,1000,'20',190,NULL),('194',51,1000,'20',170,NULL),('195',64,1000,'20',160,NULL),('196',79,1000,'20',150,NULL),('197',91,1000,'20',30,NULL),('198',155,1000,'20',20,NULL),('199',199,1000,'20',9,NULL),('2',23,0,'1',100,0),('20',365,739,'2',1,0),('200',365,1000,'20',1,NULL),('21',10,771,'3',170,0),('22',23,724,'3',100,0),('23',37,736,'3',190,0),('24',51,765,'3',170,0),('25',64,703,'3',160,0),('26',79,739,'3',150,0),('27',91,739,'3',30,0),('28',155,765,'3',20,0),('29',199,733,'3',9,0),('3',37,730,'1',190,0),('30',365,692,'3',1,0),('31',10,733,'4',170,0),('32',23,724,'4',100,0),('33',37,733,'4',190,0),('34',51,709,'4',170,0),('35',64,730,'4',160,0),('36',79,750,'4',150,0),('37',91,753,'4',30,0),('38',155,730,'4',20,0),('39',199,730,'4',9,0),('4',51,745,'1',170,0),('40',365,780,'4',1,0),('41',10,765,'5',170,0),('42',23,762,'5',100,0),('43',37,759,'5',190,0),('44',51,780,'5',170,0),('45',64,718,'5',160,0),('46',79,759,'5',150,0),('47',91,718,'5',30,0),('48',155,780,'5',20,0),('49',199,736,'5',9,0),('5',64,245,'1',160,0),('50',365,748,'5',1,0),('51',10,742,'6',170,0),('52',23,753,'6',100,0),('53',37,724,'6',190,0),('54',51,733,'6',170,0),('55',64,700,'6',160,0),('56',79,727,'6',150,0),('57',91,748,'6',30,0),('58',155,727,'6',20,0),('59',199,686,'6',9,0),('6',79,765,'1',150,0),('60',365,753,'6',1,0),('61',10,759,'7',170,0),('62',23,750,'7',100,0),('63',37,715,'7',190,0),('64',51,718,'7',170,0),('65',64,727,'7',160,0),('66',79,674,'7',150,0),('67',91,742,'7',30,0),('68',155,706,'7',20,0),('69',199,718,'7',9,0),('7',91,750,'1',30,0),('70',365,1000,'7',1,0),('71',10,783,'8',170,0),('72',23,730,'8',100,0),('73',37,759,'8',190,0),('74',51,715,'8',170,0),('75',64,780,'8',160,0),('76',79,768,'8',150,0),('77',91,733,'8',30,0),('78',155,665,'8',20,0),('79',199,712,'8',9,0),('8',155,742,'1',20,0),('80',365,709,'8',1,0),('81',10,777,'9',170,0),('82',23,721,'9',100,0),('83',37,724,'9',190,0),('84',51,721,'9',170,0),('85',64,762,'9',160,0),('86',79,742,'9',150,0),('87',91,786,'9',30,0),('88',155,659,'9',20,0),('89',199,700,'9',9,0),('9',199,698,'1',9,0),('90',365,733,'9',1,0),('91',10,712,'10',170,0),('92',23,724,'10',100,0),('93',37,748,'10',190,0),('94',51,750,'10',170,0),('95',64,739,'10',160,0),('96',79,748,'10',150,0),('97',91,689,'10',30,0),('98',155,727,'10',20,0),('99',199,0,'10',9,0);

/*Table structure for table `t_city` */

DROP TABLE IF EXISTS `t_city`;

CREATE TABLE `t_city` (
  `id` varchar(64) NOT NULL,
  `chinese` varchar(255) DEFAULT NULL,
  `spelling` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_city` */

insert  into `t_city`(`id`,`chinese`,`spelling`) values ('1','北京','beijing'),('10','大连','dalian'),('11','郑州','zhengzhou'),('12','天津','tianjin'),('13','合肥','hefei'),('14','杭州','hangzhou'),('15','无锡','wuxi'),('16','唐山','tangshan'),('17','兰州','lanzhou'),('18','徐州','xuzhou'),('19','呼和浩特','huhehaote'),('2','上海','shanghai'),('3','广州','guangzhou'),('4','武汉','wuhan'),('5','南昌','nanchang'),('6','成都','chengdu'),('7','长沙','changsha'),('8','福州','fuzhou'),('9','南京','nanjing');

/*Table structure for table `t_hudong` */

DROP TABLE IF EXISTS `t_hudong`;

CREATE TABLE `t_hudong` (
  `ID` varchar(100) NOT NULL,
  `HD_NUMS` bigint(20) DEFAULT NULL,
  `SHARE_NUMS` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='互动次数表';

/*Data for the table `t_hudong` */

insert  into `t_hudong`(`ID`,`HD_NUMS`,`SHARE_NUMS`) values ('1',0,0);

/*Table structure for table `t_school` */

DROP TABLE IF EXISTS `t_school`;

CREATE TABLE `t_school` (
  `ID` varchar(100) NOT NULL,
  `NAME_V` varchar(20) DEFAULT NULL,
  `CITY` varchar(20) DEFAULT NULL,
  `SECRET` varchar(20) DEFAULT NULL,
  `URL_N` varchar(500) DEFAULT NULL,
  `hd_num` int(11) DEFAULT NULL,
  `share_num` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='学校表';

/*Data for the table `t_school` */

insert  into `t_school`(`ID`,`NAME_V`,`CITY`,`SECRET`,`URL_N`,`hd_num`,`share_num`) values ('1','北京新东方','北京','116bj','https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxaaa7f82325dc4178&scope=snsapi_base&response_type=CODE&state=xdf&redirect_uri=http%3A%2F%227.54.235.167%2Fxdfgame%2Foauth%2Fxdfbag?bagId=4028028149f0881b0149f094e0b2000d%26city=beijing#wechat_redirect',NULL,0),('10','大连新东方','大连','116dl','https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxaaa7f82325dc4178&scope=snsapi_base&response_type=CODE&state=xdf&redirect_uri=http%3A%2F%2Fweixin.hd.xdf.cn%2Fxdfgame%2Foauth%2Fxdfbag?bagId=4028028149f0881b0149f0a2cb080078%26city=dalian#wechat_redirect',NULL,0),('11','郑州新东方','郑州','116zz','https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxaaa7f82325dc4178&scope=snsapi_base&response_type=CODE&state=xdf&redirect_uri=http%3A%2F%2Fweixin.hd.xdf.cn%2Fxdfgame%2Foauth%2Fxdfbag?bagId=4028028149f0881b0149f0a4a56b0084%26city=zhengzhou#wechat_redirect',NULL,0),('12','天津新东方','天津','116tj','https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxaaa7f82325dc4178&scope=snsapi_base&response_type=CODE&state=xdf&redirect_uri=http%3A%2F%2Fweixin.hd.xdf.cn%2Fxdfgame%2Foauth%2Fxdfbag?bagId=4028028149f0881b0149f0a572e50090%26city=tianjin#wechat_redirect',NULL,0),('13','合肥新东方','合肥','116hf','https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxaaa7f82325dc4178&scope=snsapi_base&response_type=CODE&state=xdf&redirect_uri=http%3A%2F%2Fweixin.hd.xdf.cn%2Fxdfgame%2Foauth%2Fxdfbag?bagId=4028028149f0881b0149f0a6bb60009c%26city=hefei#wechat_redirect',NULL,0),('14','杭州新东方','杭州','116hz','https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxaaa7f82325dc4178&scope=snsapi_base&response_type=CODE&state=xdf&redirect_uri=http%3A%2F%2Fweixin.hd.xdf.cn%2Fxdfgame%2Foauth%2Fxdfbag?bagId=4028028149f0881b0149f0a7d5f800a8%26city=hangzhou#wechat_redirect',NULL,0),('15','无锡新东方','无锡','116wx','https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxaaa7f82325dc4178&scope=snsapi_base&response_type=CODE&state=xdf&redirect_uri=http%3A%2F%2Fweixin.hd.xdf.cn%2Fxdfgame%2Foauth%2Fxdfbag?bagId=4028028149f0881b0149f0af6b6000e4%26city=wuxi#wechat_redirect',NULL,0),('16','唐山新东方','唐山','116ts','https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxaaa7f82325dc4178&scope=snsapi_base&response_type=CODE&state=xdf&redirect_uri=http%3A%2F%2Fweixin.hd.xdf.cn%2Fxdfgame%2Foauth%2Fxdfbag?bagId=4028028149f0881b0149f0a8dc9800b4%26city=tangshan#wechat_redirect',NULL,0),('17','兰州新东方','兰州','116lz','https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxaaa7f82325dc4178&scope=snsapi_base&response_type=CODE&state=xdf&redirect_uri=http%3A%2F%2Fweixin.hd.xdf.cn%2Fxdfgame%2Foauth%2Fxdfbag?bagId=4028028149f0881b0149f0aa4dde00c0%26city=lanzhou#wechat_redirect',NULL,0),('18','徐州新东方','徐州','116xz','https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxaaa7f82325dc4178&scope=snsapi_base&response_type=CODE&state=xdf&redirect_uri=http%3A%2F%2Fweixin.hd.xdf.cn%2Fxdfgame%2Foauth%2Fxdfbag?bagId=4028028149f0881b0149f0ac156200cc%26city=xuzhou#wechat_redirect',NULL,0),('19','呼和浩特新东方','呼和浩特','116hh','https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxaaa7f82325dc4178&scope=snsapi_base&response_type=CODE&state=xdf&redirect_uri=http%3A%2F%2Fweixin.hd.xdf.cn%2Fxdfgame%2Foauth%2Fxdfbag?bagId=4028028149f0881b0149f0ad2fc700d8%26city=huhehaote#wechat_redirect',NULL,0),('2','上海新东方','上海','116sh','https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxaaa7f82325dc4178&scope=snsapi_base&response_type=CODE&state=xdf&redirect_uri=http%3A%2F%2Fweixin.hd.xdf.cn%2Fxdfgame%2Foauth%2Fxdfbag?bagId=4028028149f0881b0149f09760270019%26city=shanghai#wechat_redirect',NULL,0),('20','南宁新东方','南宁','116nn','https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxaaa7f82325dc4178&scope=snsapi_base&response_type=CODE&state=xdf&redirect_uri=http%3A%2F%2Fweixin.hd.xdf.cn%2Fxdfgame%2Foauth%2Fxdfbag?bagId=4028028149f0881b0149f0a1b625006c%26city=nanjing#wechat_redirect',NULL,0),('3','广州新东方','广州','116gz','https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxaaa7f82325dc4178&scope=snsapi_base&response_type=CODE&state=xdf&redirect_uri=http%3A%2F%2Fweixin.hd.xdf.cn%2Fxdfgame%2Foauth%2Fxdfbag?bagId=4028028149f0881b0149f099922e0025%26city=guangzhou#wechat_redirect',NULL,0),('4','武汉新东方','武汉','116wh','https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxaaa7f82325dc4178&scope=snsapi_base&response_type=CODE&state=xdf&redirect_uri=http%3A%2F%2Fweixin.hd.xdf.cn%2Fxdfgame%2Foauth%2Fxdfbag?bagId=4028028149f0881b0149f09ac8d80031%26city=wuhan#wechat_redirect',NULL,0),('5','南昌新东方','南昌','116nc','https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxaaa7f82325dc4178&scope=snsapi_base&response_type=CODE&state=xdf&redirect_uri=http%3A%2F%2Fweixin.hd.xdf.cn%2Fxdfgame%2Foauth%2Fxdfbag?bagId=4028028149f0881b0149f09d03cf003d%26city=nanchang#wechat_redirect',NULL,0),('6','成都新东方','成都','116cd','https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxaaa7f82325dc4178&scope=snsapi_base&response_type=CODE&state=xdf&redirect_uri=http%3A%2F%2Fweixin.hd.xdf.cn%2Fxdfgame%2Foauth%2Fxdfbag?bagId=4028028149f0881b0149f09e7cf90049%26city=chengdu#wechat_redirect',NULL,0),('7','长沙新东方','长沙','116cs','https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxaaa7f82325dc4178&scope=snsapi_base&response_type=CODE&state=xdf&redirect_uri=http%3A%2F%2Fweixin.hd.xdf.cn%2Fxdfgame%2Foauth%2Fxdfbag?bagId=4028028149f0881b0149f09f85070054%26city=changsha#wechat_redirect',NULL,0),('8','福州新东方','福州','116fz','https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxaaa7f82325dc4178&scope=snsapi_base&response_type=CODE&state=xdf&redirect_uri=http%3A%2F%2Fweixin.hd.xdf.cn%2Fxdfgame%2Foauth%2Fxdfbag?bagId=4028028149f0881b0149f0a0a6ea0060%26city=fuzhou#wechat_redirect',NULL,0),('9','南京新东方','南京','116nj','https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxaaa7f82325dc4178&scope=snsapi_base&response_type=CODE&state=xdf&redirect_uri=http%3A%2F%2Fweixin.hd.xdf.cn%2Fxdfgame%2Foauth%2Fxdfbag?bagId=4028028149f0881b0149f0a1b625006c%26city=nanjing#wechat_redirect',NULL,0);

/*Table structure for table `t_school_bag` */

DROP TABLE IF EXISTS `t_school_bag`;

CREATE TABLE `t_school_bag` (
  `ID` varchar(100) NOT NULL,
  `USER_ID` varchar(100) DEFAULT NULL,
  `HEAD_IMAGE` varchar(50) DEFAULT NULL,
  `MSG_V` varchar(100) DEFAULT NULL,
  `BGTYPE` varchar(100) DEFAULT NULL,
  `NUM_N` int(11) DEFAULT NULL,
  `SCHOOL_ID` varchar(100) DEFAULT NULL,
  `DATE_N` varchar(100) DEFAULT NULL,
  `moban` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_Reference_6` (`SCHOOL_ID`),
  CONSTRAINT `FK_Reference_6` FOREIGN KEY (`SCHOOL_ID`) REFERENCES `t_school` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='学校红包表';

/*Data for the table `t_school_bag` */

/*Table structure for table `t_school_bingo` */

DROP TABLE IF EXISTS `t_school_bingo`;

CREATE TABLE `t_school_bingo` (
  `ID` varchar(100) NOT NULL,
  `SCHOOL_ID` varchar(100) DEFAULT NULL,
  `SCHOOL_BAG_ID` varchar(100) DEFAULT NULL,
  `MONEY_N` int(11) DEFAULT NULL,
  `NUM_N` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_Reference_12` (`SCHOOL_ID`),
  KEY `FK_Reference_13` (`SCHOOL_BAG_ID`),
  CONSTRAINT `FK_Reference_12` FOREIGN KEY (`SCHOOL_ID`) REFERENCES `t_school` (`ID`),
  CONSTRAINT `FK_Reference_13` FOREIGN KEY (`SCHOOL_BAG_ID`) REFERENCES `t_school_bag` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='学校概率表';

/*Data for the table `t_school_bingo` */

/*Table structure for table `t_suggest` */

DROP TABLE IF EXISTS `t_suggest`;

CREATE TABLE `t_suggest` (
  `ID` varchar(100) NOT NULL,
  `USER_ID` varchar(100) DEFAULT NULL,
  `CONTENT_V` varchar(100) DEFAULT NULL,
  `DATE_D` varchar(50) DEFAULT NULL,
  `DATE_STRD` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='投诉建议表';

/*Data for the table `t_suggest` */

/*Table structure for table `t_temp` */

DROP TABLE IF EXISTS `t_temp`;

CREATE TABLE `t_temp` (
  `ID` varchar(100) NOT NULL,
  `BAG_ID` varchar(100) DEFAULT NULL,
  `USER_ID` varchar(100) DEFAULT NULL,
  `VOUCHER_ID` varchar(100) DEFAULT NULL,
  `TYPE_N` int(11) DEFAULT NULL,
  `CITY_ID` varchar(100) DEFAULT NULL,
  `MONEY_D` int(11) DEFAULT NULL,
  `openid` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='临时表';

/*Data for the table `t_temp` */

/*Table structure for table `t_user` */

DROP TABLE IF EXISTS `t_user`;

CREATE TABLE `t_user` (
  `ID` varchar(100) NOT NULL,
  `NAME_V` varchar(100) DEFAULT NULL,
  `HEAD_IMAGE` varchar(250) DEFAULT NULL,
  `OPENID` varchar(100) DEFAULT NULL,
  `CITY` varchar(20) DEFAULT NULL,
  `LOTTERY_DATE` varchar(50) DEFAULT NULL,
  `DATE_NUM` int(11) DEFAULT NULL,
  `STATUS_V` varchar(10) DEFAULT NULL,
  `SCHOOL_ID` varchar(100) DEFAULT NULL,
  `SHARE_NUM` int(11) DEFAULT NULL,
  `SHARE_DATE` varchar(50) DEFAULT NULL,
  `POSITION` int(10) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_Reference_2` (`SCHOOL_ID`),
  KEY `USEROPENISINDEX` (`OPENID`),
  CONSTRAINT `FK_Reference_2` FOREIGN KEY (`SCHOOL_ID`) REFERENCES `t_school` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

/*Data for the table `t_user` */

/*Table structure for table `t_voucher` */

DROP TABLE IF EXISTS `t_voucher`;

CREATE TABLE `t_voucher` (
  `ID` varchar(100) NOT NULL,
  `CODE_V` varchar(50) DEFAULT NULL,
  `MONEY_N` int(11) DEFAULT NULL,
  `STATUS_N` int(11) DEFAULT NULL,
  `TYPE_N` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `VOUCHERMONEYINDEX` (`MONEY_N`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='兑换码表';

/*Data for the table `t_voucher` */

DROP TABLE IF EXISTS `T_URL`;

CREATE TABLE `T_URL` (
  `ID` varchar(100) NOT NULL,
  `url_n` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='红包url';


/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
