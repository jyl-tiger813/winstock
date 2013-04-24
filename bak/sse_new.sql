/*
SQLyog Ultimate v8.4 
MySQL - 5.5.11 : Database - sse
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`sse` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `sse`;

/*Table structure for table `cease` */

DROP TABLE IF EXISTS `cease`;

CREATE TABLE `cease` (
  `changeTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`changeTime`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Table structure for table `employees` */

DROP TABLE IF EXISTS `employees`;

CREATE TABLE `employees` (
  `id` int(11) NOT NULL,
  `fname` varchar(30) DEFAULT NULL,
  `lname` varchar(30) DEFAULT NULL,
  `hired` date NOT NULL DEFAULT '1970-01-01',
  `separated` date NOT NULL DEFAULT '9999-12-31',
  `job_code` int(11) DEFAULT NULL,
  `store_id` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8
/*!50100 PARTITION BY LIST (store_id)
(PARTITION pNorth VALUES IN (3,5,6,9,17) ENGINE = MyISAM,
 PARTITION pEast VALUES IN (1,2,10,11,19,20) ENGINE = MyISAM,
 PARTITION pWest VALUES IN (4,12,13,14,18) ENGINE = MyISAM,
 PARTITION pCentral VALUES IN (7,8,15,16) ENGINE = MyISAM) */;

/*Table structure for table `index_vr_related_data` */

DROP TABLE IF EXISTS `index_vr_related_data`;

CREATE TABLE `index_vr_related_data` (
  `index_id` int(11) DEFAULT NULL,
  `stock_code` varchar(20) DEFAULT NULL,
  `change_ratio` double DEFAULT NULL COMMENT '今天昨天收盘变化率',
  `ave_change_ratio` double DEFAULT NULL COMMENT '今天昨天均价变化率',
  `change_ratio_close_begin` double DEFAULT NULL COMMENT '今天收盘 开盘',
  `change_ratio_avg_close` double DEFAULT NULL COMMENT '今天均价收盘',
  `stock_code_int` int(10) DEFAULT NULL,
  `count_days` int(11) DEFAULT NULL,
  `trade_date` datetime DEFAULT NULL,
  `index_value` double DEFAULT NULL,
  `ts` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  UNIQUE KEY `index_id_count_days_stock_code_trade_date` (`index_id`,`stock_code`,`count_days`,`trade_date`),
  KEY `NewIndex1` (`index_id`),
  KEY `NewIndex2` (`stock_code`),
  KEY `NewIndex3` (`trade_date`),
  KEY `NewIndex4` (`index_id`,`count_days`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 DELAY_KEY_WRITE=1;

/*Table structure for table `index_vr_related_data1_10` */

DROP TABLE IF EXISTS `index_vr_related_data1_10`;

CREATE TABLE `index_vr_related_data1_10` (
  `index_id` int(11) DEFAULT NULL,
  `stock_code` varchar(20) DEFAULT NULL,
  `change_ratio` double DEFAULT NULL COMMENT '?????????',
  `ave_change_ratio` double DEFAULT NULL COMMENT '?????????',
  `change_ratio_close_begin` double DEFAULT NULL COMMENT '???? ??',
  `change_ratio_avg_close` double DEFAULT NULL COMMENT '??????',
  `stock_code_int` int(10) DEFAULT NULL,
  `count_days` int(11) DEFAULT NULL,
  `trade_date` datetime DEFAULT NULL,
  `index_value` double DEFAULT NULL,
  `ts` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  UNIQUE KEY `index_id_count_days_stock_code_trade_date` (`index_id`,`stock_code`,`count_days`,`trade_date`),
  KEY `NewIndex1` (`index_id`),
  KEY `NewIndex2` (`stock_code`),
  KEY `NewIndex3` (`trade_date`),
  KEY `NewIndex4` (`index_id`,`count_days`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 DELAY_KEY_WRITE=1;

/*Table structure for table `index_vr_related_data1_21` */

DROP TABLE IF EXISTS `index_vr_related_data1_21`;

CREATE TABLE `index_vr_related_data1_21` (
  `index_id` int(11) DEFAULT NULL,
  `stock_code` varchar(20) DEFAULT NULL,
  `change_ratio` double DEFAULT NULL COMMENT '?????????',
  `ave_change_ratio` double DEFAULT NULL COMMENT '?????????',
  `change_ratio_close_begin` double DEFAULT NULL COMMENT '???? ??',
  `change_ratio_avg_close` double DEFAULT NULL COMMENT '??????',
  `stock_code_int` int(10) DEFAULT NULL,
  `count_days` int(11) DEFAULT NULL,
  `trade_date` datetime DEFAULT NULL,
  `index_value` double DEFAULT NULL,
  `ts` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  UNIQUE KEY `index_id_count_days_stock_code_trade_date` (`index_id`,`stock_code`,`count_days`,`trade_date`),
  KEY `NewIndex1` (`index_id`),
  KEY `NewIndex2` (`stock_code`),
  KEY `NewIndex3` (`trade_date`),
  KEY `NewIndex4` (`index_id`,`count_days`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 DELAY_KEY_WRITE=1;

/*Table structure for table `index_vr_related_data1_5` */

DROP TABLE IF EXISTS `index_vr_related_data1_5`;

CREATE TABLE `index_vr_related_data1_5` (
  `index_id` int(11) DEFAULT NULL,
  `stock_code` varchar(20) DEFAULT NULL,
  `change_ratio` double DEFAULT NULL COMMENT '?????????',
  `ave_change_ratio` double DEFAULT NULL COMMENT '?????????',
  `change_ratio_close_begin` double DEFAULT NULL COMMENT '???? ??',
  `change_ratio_avg_close` double DEFAULT NULL COMMENT '??????',
  `stock_code_int` int(10) DEFAULT NULL,
  `count_days` int(11) DEFAULT NULL,
  `trade_date` datetime DEFAULT NULL,
  `index_value` double DEFAULT NULL,
  `ts` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  UNIQUE KEY `index_id_count_days_stock_code_trade_date` (`index_id`,`stock_code`,`count_days`,`trade_date`),
  KEY `NewIndex1` (`index_id`),
  KEY `NewIndex2` (`stock_code`),
  KEY `NewIndex3` (`trade_date`),
  KEY `NewIndex4` (`index_id`,`count_days`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 DELAY_KEY_WRITE=1;

/*Table structure for table `index_vr_related_data2_10` */

DROP TABLE IF EXISTS `index_vr_related_data2_10`;

CREATE TABLE `index_vr_related_data2_10` (
  `index_id` int(11) DEFAULT NULL,
  `stock_code` varchar(20) DEFAULT NULL,
  `change_ratio` double DEFAULT NULL COMMENT '?????????',
  `ave_change_ratio` double DEFAULT NULL COMMENT '?????????',
  `change_ratio_close_begin` double DEFAULT NULL COMMENT '???? ??',
  `change_ratio_avg_close` double DEFAULT NULL COMMENT '??????',
  `stock_code_int` int(10) DEFAULT NULL,
  `count_days` int(11) DEFAULT NULL,
  `trade_date` datetime DEFAULT NULL,
  `index_value` double DEFAULT NULL,
  `ts` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  UNIQUE KEY `index_id_count_days_stock_code_trade_date` (`index_id`,`stock_code`,`count_days`,`trade_date`),
  KEY `NewIndex1` (`index_id`),
  KEY `NewIndex2` (`stock_code`),
  KEY `NewIndex3` (`trade_date`),
  KEY `NewIndex4` (`index_id`,`count_days`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 DELAY_KEY_WRITE=1;

/*Table structure for table `index_vr_related_data2_21` */

DROP TABLE IF EXISTS `index_vr_related_data2_21`;

CREATE TABLE `index_vr_related_data2_21` (
  `index_id` int(11) DEFAULT NULL,
  `stock_code` varchar(20) DEFAULT NULL,
  `change_ratio` double DEFAULT NULL COMMENT '?????????',
  `ave_change_ratio` double DEFAULT NULL COMMENT '?????????',
  `change_ratio_close_begin` double DEFAULT NULL COMMENT '???? ??',
  `change_ratio_avg_close` double DEFAULT NULL COMMENT '??????',
  `stock_code_int` int(10) DEFAULT NULL,
  `count_days` int(11) DEFAULT NULL,
  `trade_date` datetime DEFAULT NULL,
  `index_value` double DEFAULT NULL,
  `ts` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  UNIQUE KEY `index_id_count_days_stock_code_trade_date` (`index_id`,`stock_code`,`count_days`,`trade_date`),
  KEY `NewIndex1` (`index_id`),
  KEY `NewIndex2` (`stock_code`),
  KEY `NewIndex3` (`trade_date`),
  KEY `NewIndex4` (`index_id`,`count_days`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 DELAY_KEY_WRITE=1;

/*Table structure for table `index_vr_related_data2_5` */

DROP TABLE IF EXISTS `index_vr_related_data2_5`;

CREATE TABLE `index_vr_related_data2_5` (
  `index_id` int(11) DEFAULT NULL,
  `stock_code` varchar(20) DEFAULT NULL,
  `change_ratio` double DEFAULT NULL COMMENT '?????????',
  `ave_change_ratio` double DEFAULT NULL COMMENT '?????????',
  `change_ratio_close_begin` double DEFAULT NULL COMMENT '???? ??',
  `change_ratio_avg_close` double DEFAULT NULL COMMENT '??????',
  `stock_code_int` int(10) DEFAULT NULL,
  `count_days` int(11) DEFAULT NULL,
  `trade_date` datetime DEFAULT NULL,
  `index_value` double DEFAULT NULL,
  `ts` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  UNIQUE KEY `index_id_count_days_stock_code_trade_date` (`index_id`,`stock_code`,`count_days`,`trade_date`),
  KEY `NewIndex1` (`index_id`),
  KEY `NewIndex2` (`stock_code`),
  KEY `NewIndex3` (`trade_date`),
  KEY `NewIndex4` (`index_id`,`count_days`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 DELAY_KEY_WRITE=1;

/*Table structure for table `index_vr_related_data3_10` */

DROP TABLE IF EXISTS `index_vr_related_data3_10`;

CREATE TABLE `index_vr_related_data3_10` (
  `index_id` int(11) DEFAULT NULL,
  `stock_code` varchar(20) DEFAULT NULL,
  `change_ratio` double DEFAULT NULL COMMENT '?????????',
  `ave_change_ratio` double DEFAULT NULL COMMENT '?????????',
  `change_ratio_close_begin` double DEFAULT NULL COMMENT '???? ??',
  `change_ratio_avg_close` double DEFAULT NULL COMMENT '??????',
  `stock_code_int` int(10) DEFAULT NULL,
  `count_days` int(11) DEFAULT NULL,
  `trade_date` datetime DEFAULT NULL,
  `index_value` double DEFAULT NULL,
  `ts` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  UNIQUE KEY `index_id_count_days_stock_code_trade_date` (`index_id`,`stock_code`,`count_days`,`trade_date`),
  KEY `NewIndex1` (`index_id`),
  KEY `NewIndex2` (`stock_code`),
  KEY `NewIndex3` (`trade_date`),
  KEY `NewIndex4` (`index_id`,`count_days`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 DELAY_KEY_WRITE=1;

/*Table structure for table `index_vr_related_data3_21` */

DROP TABLE IF EXISTS `index_vr_related_data3_21`;

CREATE TABLE `index_vr_related_data3_21` (
  `index_id` int(11) DEFAULT NULL,
  `stock_code` varchar(20) DEFAULT NULL,
  `change_ratio` double DEFAULT NULL COMMENT '?????????',
  `ave_change_ratio` double DEFAULT NULL COMMENT '?????????',
  `change_ratio_close_begin` double DEFAULT NULL COMMENT '???? ??',
  `change_ratio_avg_close` double DEFAULT NULL COMMENT '??????',
  `stock_code_int` int(10) DEFAULT NULL,
  `count_days` int(11) DEFAULT NULL,
  `trade_date` datetime DEFAULT NULL,
  `index_value` double DEFAULT NULL,
  `ts` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  UNIQUE KEY `index_id_count_days_stock_code_trade_date` (`index_id`,`stock_code`,`count_days`,`trade_date`),
  KEY `NewIndex1` (`index_id`),
  KEY `NewIndex2` (`stock_code`),
  KEY `NewIndex3` (`trade_date`),
  KEY `NewIndex4` (`index_id`,`count_days`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 DELAY_KEY_WRITE=1;

/*Table structure for table `index_vr_related_data3_5` */

DROP TABLE IF EXISTS `index_vr_related_data3_5`;

CREATE TABLE `index_vr_related_data3_5` (
  `index_id` int(11) DEFAULT NULL,
  `stock_code` varchar(20) DEFAULT NULL,
  `change_ratio` double DEFAULT NULL COMMENT '?????????',
  `ave_change_ratio` double DEFAULT NULL COMMENT '?????????',
  `change_ratio_close_begin` double DEFAULT NULL COMMENT '???? ??',
  `change_ratio_avg_close` double DEFAULT NULL COMMENT '??????',
  `stock_code_int` int(10) DEFAULT NULL,
  `count_days` int(11) DEFAULT NULL,
  `trade_date` datetime DEFAULT NULL,
  `index_value` double DEFAULT NULL,
  `ts` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  UNIQUE KEY `index_id_count_days_stock_code_trade_date` (`index_id`,`stock_code`,`count_days`,`trade_date`),
  KEY `NewIndex1` (`index_id`),
  KEY `NewIndex2` (`stock_code`),
  KEY `NewIndex3` (`trade_date`),
  KEY `NewIndex4` (`index_id`,`count_days`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 DELAY_KEY_WRITE=1;

/*Table structure for table `index_vr_related_data4_10` */

DROP TABLE IF EXISTS `index_vr_related_data4_10`;

CREATE TABLE `index_vr_related_data4_10` (
  `index_id` int(11) DEFAULT NULL,
  `stock_code` varchar(20) DEFAULT NULL,
  `change_ratio` double DEFAULT NULL COMMENT '?????????',
  `ave_change_ratio` double DEFAULT NULL COMMENT '?????????',
  `change_ratio_close_begin` double DEFAULT NULL COMMENT '???? ??',
  `change_ratio_avg_close` double DEFAULT NULL COMMENT '??????',
  `stock_code_int` int(10) DEFAULT NULL,
  `count_days` int(11) DEFAULT NULL,
  `trade_date` datetime DEFAULT NULL,
  `index_value` double DEFAULT NULL,
  `ts` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  UNIQUE KEY `index_id_count_days_stock_code_trade_date` (`index_id`,`stock_code`,`count_days`,`trade_date`),
  KEY `NewIndex1` (`index_id`),
  KEY `NewIndex2` (`stock_code`),
  KEY `NewIndex3` (`trade_date`),
  KEY `NewIndex4` (`index_id`,`count_days`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 DELAY_KEY_WRITE=1;

/*Table structure for table `index_vr_related_data4_10_pirce_volumn_related_index` */

DROP TABLE IF EXISTS `index_vr_related_data4_10_pirce_volumn_related_index`;

CREATE TABLE `index_vr_related_data4_10_pirce_volumn_related_index` (
  `index_value` double DEFAULT NULL,
  `id` bigint(20) NOT NULL DEFAULT '0',
  `stock_code` varchar(30) DEFAULT NULL,
  `trade_date` datetime DEFAULT NULL,
  `avg_2close_ratio_bef_1` double DEFAULT NULL,
  `avg_2close_ratio_bef_3` double DEFAULT NULL COMMENT '均价与市价（前一天收盘价与今日收盘价均值）偏差累计（比率累计）',
  `avg_2close_ratio_bef_5` double DEFAULT NULL,
  `avg_2close_ratio_bef_10` double DEFAULT NULL,
  `avg_2close_ratio_bef_20` double DEFAULT NULL,
  `avg_2close_ratio_bef_60` double DEFAULT NULL,
  `avg_2close_ratio_bef_120` double DEFAULT NULL,
  `avg_cost_bef_1_today_ratio` double DEFAULT NULL,
  `avg_cost_bef_3_today_ratio` double DEFAULT NULL COMMENT '当前价格 与 前3天均价变动率',
  `avg_cost_bef_5_today_ratio` double DEFAULT NULL,
  `avg_cost_bef_10_today_ratio` double DEFAULT NULL,
  `avg_cost_bef_20_today_ratio` double DEFAULT NULL,
  `avg_cost_bef_60_today_ratio` double DEFAULT NULL,
  `avg_cost_bef_120_today_ratio` double DEFAULT NULL,
  `avg_cost_after_1_today_ratio` double DEFAULT NULL,
  `avg_cost_after_3_today_ratio` double DEFAULT NULL COMMENT '后三日均价 与 当前成交均价变动率',
  `avg_cost_after_5_today_ratio` double DEFAULT NULL,
  `avg_cost_after_10_today_ratio` double DEFAULT NULL,
  `avg_cost_after_20_today_ratio` double DEFAULT NULL,
  `avg_cost_after_60_today_ratio` double DEFAULT NULL,
  `avg_cost_after_120_today_ratio` double DEFAULT NULL,
  `view_real_avg_ratio_before_1` double DEFAULT NULL,
  `view_real_avg_ratio_before_3` double DEFAULT NULL,
  `view_real_avg_ratio_before_5` double DEFAULT NULL,
  `view_real_avg_ratio_before_10` double DEFAULT NULL,
  `view_real_avg_ratio_before_20` double DEFAULT NULL,
  `view_real_avg_ratio_before_60` double DEFAULT NULL,
  `view_real_avg_ratio_before_120` double DEFAULT NULL,
  `ts` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  UNIQUE KEY `NewIndex1` (`stock_code`,`trade_date`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Table structure for table `index_vr_related_data4_21` */

DROP TABLE IF EXISTS `index_vr_related_data4_21`;

CREATE TABLE `index_vr_related_data4_21` (
  `index_id` int(11) DEFAULT NULL,
  `stock_code` varchar(20) DEFAULT NULL,
  `change_ratio` double DEFAULT NULL COMMENT '?????????',
  `ave_change_ratio` double DEFAULT NULL COMMENT '?????????',
  `change_ratio_close_begin` double DEFAULT NULL COMMENT '???? ??',
  `change_ratio_avg_close` double DEFAULT NULL COMMENT '??????',
  `stock_code_int` int(10) DEFAULT NULL,
  `count_days` int(11) DEFAULT NULL,
  `trade_date` datetime DEFAULT NULL,
  `index_value` double DEFAULT NULL,
  `ts` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  UNIQUE KEY `index_id_count_days_stock_code_trade_date` (`index_id`,`stock_code`,`count_days`,`trade_date`),
  KEY `NewIndex1` (`index_id`),
  KEY `NewIndex2` (`stock_code`),
  KEY `NewIndex3` (`trade_date`),
  KEY `NewIndex4` (`index_id`,`count_days`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 DELAY_KEY_WRITE=1;

/*Table structure for table `index_vr_related_data4_5` */

DROP TABLE IF EXISTS `index_vr_related_data4_5`;

CREATE TABLE `index_vr_related_data4_5` (
  `index_id` int(11) DEFAULT NULL,
  `stock_code` varchar(20) DEFAULT NULL,
  `change_ratio` double DEFAULT NULL COMMENT '?????????',
  `ave_change_ratio` double DEFAULT NULL COMMENT '?????????',
  `change_ratio_close_begin` double DEFAULT NULL COMMENT '???? ??',
  `change_ratio_avg_close` double DEFAULT NULL COMMENT '??????',
  `stock_code_int` int(10) DEFAULT NULL,
  `count_days` int(11) DEFAULT NULL,
  `trade_date` datetime DEFAULT NULL,
  `index_value` double DEFAULT NULL,
  `ts` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  UNIQUE KEY `index_id_count_days_stock_code_trade_date` (`index_id`,`stock_code`,`count_days`,`trade_date`),
  KEY `NewIndex1` (`index_id`),
  KEY `NewIndex2` (`stock_code`),
  KEY `NewIndex3` (`trade_date`),
  KEY `NewIndex4` (`index_id`,`count_days`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 DELAY_KEY_WRITE=1;

/*Table structure for table `lack` */

DROP TABLE IF EXISTS `lack`;

CREATE TABLE `lack` (
  `changeTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`changeTime`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Table structure for table `pirce_volumn_related_index` */

DROP TABLE IF EXISTS `pirce_volumn_related_index`;

CREATE TABLE `pirce_volumn_related_index` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `stock_code` varchar(30) DEFAULT NULL,
  `trade_date` datetime DEFAULT NULL,
  `avg_2close_ratio_bef_1` double DEFAULT NULL,
  `avg_2close_ratio_bef_3` double DEFAULT NULL COMMENT '均价与市价（前一天收盘价与今日收盘价均值）偏差累计（比率累计）',
  `avg_2close_ratio_bef_5` double DEFAULT NULL,
  `avg_2close_ratio_bef_10` double DEFAULT NULL,
  `avg_2close_ratio_bef_20` double DEFAULT NULL,
  `avg_2close_ratio_bef_60` double DEFAULT NULL,
  `avg_2close_ratio_bef_120` double DEFAULT NULL,
  `avg_cost_bef_1_today_ratio` double DEFAULT NULL,
  `avg_cost_bef_3_today_ratio` double DEFAULT NULL COMMENT '当前价格 与 前3天均价变动率',
  `avg_cost_bef_5_today_ratio` double DEFAULT NULL,
  `avg_cost_bef_10_today_ratio` double DEFAULT NULL,
  `avg_cost_bef_20_today_ratio` double DEFAULT NULL,
  `avg_cost_bef_60_today_ratio` double DEFAULT NULL,
  `avg_cost_bef_120_today_ratio` double DEFAULT NULL,
  `avg_cost_after_1_today_ratio` double DEFAULT NULL,
  `avg_cost_after_3_today_ratio` double DEFAULT NULL COMMENT '后三日均价 与 当前成交均价变动率',
  `avg_cost_after_5_today_ratio` double DEFAULT NULL,
  `avg_cost_after_10_today_ratio` double DEFAULT NULL,
  `avg_cost_after_20_today_ratio` double DEFAULT NULL,
  `avg_cost_after_60_today_ratio` double DEFAULT NULL,
  `avg_cost_after_120_today_ratio` double DEFAULT NULL,
  `view_real_avg_ratio_before_1` double DEFAULT NULL,
  `view_real_avg_ratio_before_3` double DEFAULT NULL,
  `view_real_avg_ratio_before_5` double DEFAULT NULL,
  `view_real_avg_ratio_before_10` double DEFAULT NULL,
  `view_real_avg_ratio_before_20` double DEFAULT NULL,
  `view_real_avg_ratio_before_60` double DEFAULT NULL,
  `view_real_avg_ratio_before_120` double DEFAULT NULL,
  `volumeReduceChange_30_60` double DEFAULT NULL,
  `volumeReduceChange_50_100` double DEFAULT NULL,
  `volumeReduceChange_100_300` double DEFAULT NULL,
  `ts` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `Index_1` (`stock_code`,`trade_date`)
) ENGINE=MyISAM AUTO_INCREMENT=751596 DEFAULT CHARSET=utf8 COMMENT='价量指标';

/*Table structure for table `record` */

DROP TABLE IF EXISTS `record`;

CREATE TABLE `record` (
  `changeTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `maketTotal` float DEFAULT NULL,
  `circulateTotal` float DEFAULT NULL,
  `volumn` float DEFAULT NULL,
  `amount` float DEFAULT NULL,
  `hundredShare` float DEFAULT NULL,
  `perGet` float DEFAULT NULL,
  `changeRatio` float DEFAULT NULL,
  `amountPerChange` float DEFAULT NULL,
  PRIMARY KEY (`changeTime`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Table structure for table `stock_trade_daily_detail` */

DROP TABLE IF EXISTS `stock_trade_daily_detail`;

CREATE TABLE `stock_trade_daily_detail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `stock_code` varchar(30) DEFAULT NULL,
  `trade_date` datetime DEFAULT NULL,
  `closed_yes` float DEFAULT NULL,
  `begin_today` float DEFAULT NULL,
  `close_today` float DEFAULT NULL,
  `amount` double DEFAULT NULL,
  `volumn` bigint(20) DEFAULT NULL,
  `maxprice` float DEFAULT NULL,
  `minprice` float DEFAULT NULL,
  `ts` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `Index_1` (`stock_code`,`trade_date`),
  KEY `NewIndex1` (`stock_code`),
  KEY `NewIndex2` (`trade_date`)
) ENGINE=MyISAM AUTO_INCREMENT=4811108 DEFAULT CHARSET=utf8;

/*Table structure for table `stocknames` */

DROP TABLE IF EXISTS `stocknames`;

CREATE TABLE `stocknames` (
  `code_id` varchar(15) DEFAULT NULL,
  `stock_name` varchar(50) DEFAULT NULL,
  `isdealed` int(5) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Table structure for table `ts` */

DROP TABLE IF EXISTS `ts`;

CREATE TABLE `ts` (
  `id` int(11) DEFAULT NULL,
  `purchased` date DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8
/*!50100 PARTITION BY RANGE (YEAR(purchased))
SUBPARTITION BY HASH (TO_DAYS(purchased))
SUBPARTITIONS 2
(PARTITION p0 VALUES LESS THAN (1990) ENGINE = MyISAM,
 PARTITION p1 VALUES LESS THAN (2000) ENGINE = MyISAM,
 PARTITION p2 VALUES LESS THAN MAXVALUE ENGINE = MyISAM) */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
