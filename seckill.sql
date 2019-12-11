/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.0.96-community-nt : Database - seckill
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`seckill` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci */;

USE `seckill`;

/*Table structure for table `item` */

DROP TABLE IF EXISTS `item`;

CREATE TABLE `item` (
  `id` int(11) NOT NULL auto_increment,
  `title` varchar(64) collate utf8_unicode_ci NOT NULL default '',
  `price` double(10,0) NOT NULL default '0',
  `description` varchar(500) collate utf8_unicode_ci NOT NULL default '',
  `sales` int(11) NOT NULL default '0',
  `img_url` varchar(255) collate utf8_unicode_ci NOT NULL default '',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `item` */

insert  into `item`(`id`,`title`,`price`,`description`,`sales`,`img_url`) values (1,'iphone111',9999,'12345',103,'123.jpg'),(2,'iphone11',999,'123',200,'12345.jpg'),(8,'HHT',1,'啊',0,'1234.jpg'),(9,'123',123,'123',51,'321.jpg');

/*Table structure for table `item_stock` */

DROP TABLE IF EXISTS `item_stock`;

CREATE TABLE `item_stock` (
  `id` int(11) NOT NULL auto_increment,
  `stock` int(11) NOT NULL default '0',
  `item_id` int(11) NOT NULL default '0',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `item_stock` */

insert  into `item_stock`(`id`,`stock`,`item_id`) values (8,293,1),(9,200,2),(11,123,8),(12,119,9);

/*Table structure for table `order_info` */

DROP TABLE IF EXISTS `order_info`;

CREATE TABLE `order_info` (
  `id` varchar(32) collate utf8_unicode_ci NOT NULL,
  `user_id` int(11) NOT NULL default '0',
  `item_id` int(11) NOT NULL default '0',
  `item_price` double NOT NULL default '0',
  `amount` int(11) NOT NULL default '0',
  `order_price` double NOT NULL default '0',
  `promo_id` int(11) NOT NULL default '0',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `order_info` */

insert  into `order_info`(`id`,`user_id`,`item_id`,`item_price`,`amount`,`order_price`,`promo_id`) values ('2019120700000000',2,1,0,1,0,0),('2019121000000100',3,1,9999,1,9999,0),('2019121000000200',3,1,9999,1,9999,0),('2019121000000300',3,9,123,1,123,0),('2019121000000400',3,9,123,1,123,0),('2019121000000500',3,9,123,1,123,0),('2019121100000600',3,1,9999,1,9999,0),('2019121100000700',3,1,9999,1,9999,0),('2019121100000800',3,9,123,1,123,0),('2019121100000900',3,1,100,1,100,1),('2019121100001000',3,1,9999,1,9999,0);

/*Table structure for table `promo` */

DROP TABLE IF EXISTS `promo`;

CREATE TABLE `promo` (
  `id` int(11) NOT NULL auto_increment,
  `promo_name` varchar(32) collate utf8_unicode_ci NOT NULL default '',
  `start_time` datetime NOT NULL,
  `item_id` int(11) NOT NULL default '0',
  `promo_item_price` double NOT NULL default '0',
  `end_time` datetime NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `promo` */

insert  into `promo`(`id`,`promo_name`,`start_time`,`item_id`,`promo_item_price`,`end_time`) values (1,'iphone111抢购','2019-12-11 08:26:00',1,100,'2019-12-11 08:44:00');

/*Table structure for table `sequence_info` */

DROP TABLE IF EXISTS `sequence_info`;

CREATE TABLE `sequence_info` (
  `name` varchar(255) collate utf8_unicode_ci NOT NULL,
  `current_value` int(11) NOT NULL default '0',
  `step` int(11) NOT NULL default '0',
  PRIMARY KEY  (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `sequence_info` */

insert  into `sequence_info`(`name`,`current_value`,`step`) values ('order_info',11,1);

/*Table structure for table `user_info` */

DROP TABLE IF EXISTS `user_info`;

CREATE TABLE `user_info` (
  `id` int(11) NOT NULL auto_increment,
  `name` varchar(64) collate utf8_unicode_ci NOT NULL default '',
  `gender` tinyint(4) NOT NULL default '0' COMMENT '1代表男，0代表女',
  `age` int(11) NOT NULL default '0',
  `telphone` varchar(11) collate utf8_unicode_ci NOT NULL default '',
  `register_mode` varchar(64) collate utf8_unicode_ci NOT NULL default '' COMMENT 'phone,wechat,alipay',
  `third_party_id` varchar(64) collate utf8_unicode_ci NOT NULL default '',
  PRIMARY KEY  (`id`),
  UNIQUE KEY `telphone_unique_index` (`telphone`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `user_info` */

insert  into `user_info`(`id`,`name`,`gender`,`age`,`telphone`,`register_mode`,`third_party_id`) values (1,'第一个',1,30,'12345678913','phone',''),(2,'admin',1,21,'12345678912','byPhone',''),(3,'zcyf',1,22,'18846444830','byPhone','');

/*Table structure for table `user_password` */

DROP TABLE IF EXISTS `user_password`;

CREATE TABLE `user_password` (
  `id` int(11) NOT NULL auto_increment,
  `encrpt_password` varchar(128) collate utf8_unicode_ci NOT NULL default '',
  `user_id` int(11) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `user_password` */

insert  into `user_password`(`id`,`encrpt_password`,`user_id`) values (1,'abcd',1),(2,'ICy5YqxZB1uWSwcVLSNLcA==',2),(3,'4QrcOUm6Wau+VuBX8g+IPg==',3);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
