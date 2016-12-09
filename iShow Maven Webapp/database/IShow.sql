/*
SQLyog v10.2 
MySQL - 5.7.12-log : Database - ishow
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`ishow` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `ishow`;

/*Table structure for table `administrator` */

DROP TABLE IF EXISTS `administrator`;

CREATE TABLE `administrator` (
  `admin_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `password` varchar(20) NOT NULL,
  `nickname` varchar(20) NOT NULL,
  PRIMARY KEY (`admin_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `administrator` */

insert  into `administrator`(`admin_id`,`password`,`nickname`) values (1,'123','河林'),(2,'123','森林');

/*Table structure for table `collectionshare` */

DROP TABLE IF EXISTS `collectionshare`;

CREATE TABLE `collectionshare` (
  `share_id` int(10) unsigned NOT NULL,
  `user_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`share_id`,`user_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `collectionshare_ibfk_1` FOREIGN KEY (`share_id`) REFERENCES `share` (`share_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `collectionshare_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `collectionshare` */

insert  into `collectionshare`(`share_id`,`user_id`) values (9,1500013),(1,1500015),(5,1500016),(6,1500016),(1,1500017),(3,1500017),(4,1500017),(5,1500017);

/*Table structure for table `collectionuser` */

DROP TABLE IF EXISTS `collectionuser`;

CREATE TABLE `collectionuser` (
  `user_id` int(10) unsigned NOT NULL,
  `be_collection_user_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`user_id`,`be_collection_user_id`),
  KEY `be_collection_user_id` (`be_collection_user_id`),
  CONSTRAINT `collectionuser_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `collectionuser_ibfk_2` FOREIGN KEY (`be_collection_user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `collectionuser` */

insert  into `collectionuser`(`user_id`,`be_collection_user_id`) values (1500012,1500013),(1500013,1500015),(1500016,1500016),(1500017,1500016),(1500012,1500017),(1500015,1500017);

/*Table structure for table `comment` */

DROP TABLE IF EXISTS `comment`;

CREATE TABLE `comment` (
  `serial_number` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `share_id` int(10) unsigned NOT NULL,
  `user_id` int(10) unsigned NOT NULL,
  `comment_time` datetime NOT NULL,
  `be_commented_id` int(10) unsigned DEFAULT NULL,
  `comment_content` text NOT NULL,
  PRIMARY KEY (`serial_number`),
  KEY `share_id` (`share_id`),
  KEY `user_id` (`user_id`),
  KEY `be_commented_id` (`be_commented_id`),
  CONSTRAINT `comment_ibfk_1` FOREIGN KEY (`share_id`) REFERENCES `share` (`share_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `comment_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `comment_ibfk_3` FOREIGN KEY (`be_commented_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*Data for the table `comment` */

insert  into `comment`(`serial_number`,`share_id`,`user_id`,`comment_time`,`be_commented_id`,`comment_content`) values (1,1,1500013,'2016-11-17 00:28:00',NULL,'good'),(2,2,1500016,'2016-11-17 00:28:35',NULL,'good!!'),(4,1,1500013,'2016-11-18 00:54:39',NULL,'hi'),(5,2,1500016,'2016-11-18 23:38:35',NULL,'hello world'),(6,1,1500016,'2016-11-17 23:39:22',NULL,'hhhh');

/*Table structure for table `keyword` */

DROP TABLE IF EXISTS `keyword`;

CREATE TABLE `keyword` (
  `key` varchar(20) NOT NULL,
  PRIMARY KEY (`key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `keyword` */

insert  into `keyword`(`key`) values ('d'),('f'),('g'),('TMD');

/*Table structure for table `picture` */

DROP TABLE IF EXISTS `picture`;

CREATE TABLE `picture` (
  `serial_number` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `share_id` int(10) unsigned NOT NULL,
  `image_path` varchar(100) NOT NULL,
  PRIMARY KEY (`serial_number`),
  KEY `share_id` (`share_id`),
  CONSTRAINT `picture_ibfk_1` FOREIGN KEY (`share_id`) REFERENCES `share` (`share_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

/*Data for the table `picture` */

insert  into `picture`(`serial_number`,`share_id`,`image_path`) values (1,1,'c:\\'),(2,2,'c:\\'),(3,3,'c:\\'),(4,4,'c:\\'),(5,5,'c:\\'),(6,6,'c:\\'),(7,7,'c:\\'),(8,8,'c:\\'),(9,9,'c:\\'),(10,8,'c:\\mm');

/*Table structure for table `pointpraise` */

DROP TABLE IF EXISTS `pointpraise`;

CREATE TABLE `pointpraise` (
  `share_id` int(10) unsigned NOT NULL,
  `user_id` int(10) unsigned NOT NULL,
  `praise_time` datetime NOT NULL,
  PRIMARY KEY (`share_id`,`user_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `pointpraise_ibfk_1` FOREIGN KEY (`share_id`) REFERENCES `share` (`share_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `pointpraise_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `pointpraise` */

insert  into `pointpraise`(`share_id`,`user_id`,`praise_time`) values (1,1500012,'2016-11-17 00:22:21'),(2,1500013,'2016-11-03 00:25:14'),(3,1500015,'2016-11-15 00:24:58'),(4,1500013,'2016-11-17 00:23:42'),(5,1500013,'2016-11-16 00:22:48'),(6,1500013,'2016-11-23 21:27:19'),(7,1500013,'2016-11-17 00:23:26'),(7,1500016,'2016-11-09 00:24:06'),(8,1500013,'2016-11-23 21:27:19'),(8,1500015,'2016-11-11 00:24:28'),(9,1500016,'2016-11-18 15:08:00');

/*Table structure for table `share` */

DROP TABLE IF EXISTS `share`;

CREATE TABLE `share` (
  `share_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `publisher_id` int(10) unsigned NOT NULL,
  `release_time` datetime NOT NULL,
  `describe` text,
  `point_of_praise` int(11) NOT NULL DEFAULT '0',
  `comment_number` int(11) NOT NULL DEFAULT '0',
  `state` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`share_id`),
  KEY `publisher_id` (`publisher_id`),
  CONSTRAINT `share_ibfk_1` FOREIGN KEY (`publisher_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8;

/*Data for the table `share` */

insert  into `share`(`share_id`,`publisher_id`,`release_time`,`describe`,`point_of_praise`,`comment_number`,`state`) values (1,1500012,'2016-11-12 21:48:54','hello world',1,3,0),(2,1500013,'2016-11-13 19:51:13','hello world',1,2,0),(3,1500016,'2016-11-17 00:16:54','hello world',1,0,0),(4,1500016,'2016-11-16 00:18:04','hello world',1,0,1),(5,1500017,'2016-11-16 00:18:40','hello world',1,0,2),(6,1500015,'2016-11-11 00:19:18','hello world',1,0,1),(7,1500012,'2016-11-12 00:19:57','hello world',2,0,2),(8,1500013,'2016-11-09 00:20:50','hello world',2,0,0),(9,1500015,'2016-11-03 00:21:16','hello world',1,0,0),(10,1500015,'2016-11-03 13:05:37','jjj',0,0,0),(11,1500016,'2016-11-17 13:05:37','jjj',0,0,0),(12,1500016,'2016-11-17 13:05:37','jjj',0,0,0),(13,1500012,'2016-11-12 21:48:54','eeeeeh',0,0,0),(14,1500012,'2016-11-12 21:48:54','eeeee',0,0,0),(15,1500016,'2016-11-17 13:05:37','jjj',0,0,0),(16,1500016,'2016-11-17 13:05:37','jjj',0,0,0),(17,1500016,'2016-11-17 13:05:37','jjjh',0,0,0),(18,1500016,'2016-11-17 13:05:37','jjj',0,0,0),(19,1500016,'2016-11-17 13:05:37','jjj',0,0,0),(20,1500016,'2016-11-17 13:05:37','jjj',0,0,0),(21,1500016,'2016-11-17 13:05:37','jjj',0,0,0),(22,1500016,'2016-11-17 13:05:37','jjj',0,0,0),(23,1500016,'2016-11-17 13:05:37','jjj',0,0,0),(24,1500016,'2016-11-17 13:05:37','jjj',0,0,0),(25,1500016,'2016-11-17 13:05:37','ggg',0,0,0),(26,1500016,'2016-11-17 13:05:37','ggg',0,0,2),(31,1500012,'2016-11-17 21:48:54','8888888',0,0,0),(32,1500012,'2016-11-17 21:48:54','8888888',0,0,0),(33,1500012,'2016-11-17 21:48:54','8888888',0,0,0),(34,1500012,'2016-11-17 21:48:54','8888888',0,0,0),(35,1500012,'2016-11-17 21:48:54','8888888',0,0,0);

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `user_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nickname` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `mailbox` varchar(30) NOT NULL,
  `jurisdiction` int(11) NOT NULL DEFAULT '0',
  `head_portrait` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `mailbox` (`mailbox`)
) ENGINE=InnoDB AUTO_INCREMENT=4294967295 DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`user_id`,`nickname`,`password`,`mailbox`,`jurisdiction`,`head_portrait`) values (1500012,'河林','123456','123456789@qq.com',0,'E:\\应用软件\\SQLyog10.2'),(1500013,'凡孤星','123456','123456787@qq.com',0,NULL),(1500015,'三可木木','123456','123456789@126.com',0,NULL),(1500016,'刘亦菲','123456','11452@163.com',1,'E:\\应用软件\\SQLyog10.2'),(1500017,'赵丽颖','123456','156@163.com',1,NULL),(4294967295,'','','',0,NULL);

/* Trigger structure for table `comment` */

DELIMITER $$

/*!50003 DROP TRIGGER*//*!50032 IF EXISTS */ /*!50003 `trigger_03` */$$

/*!50003 CREATE */ /*!50017 DEFINER = 'root'@'localhost' */ /*!50003 TRIGGER `trigger_03` AFTER INSERT ON `comment` FOR EACH ROW BEGIN
	update `ishow`.`share` set comment_number=comment_number+1
	where new.share_id=`ishow`.`share`.`share_id`;
    END */$$


DELIMITER ;

/* Trigger structure for table `comment` */

DELIMITER $$

/*!50003 DROP TRIGGER*//*!50032 IF EXISTS */ /*!50003 `trigger_04` */$$

/*!50003 CREATE */ /*!50017 DEFINER = 'root'@'localhost' */ /*!50003 TRIGGER `trigger_04` AFTER DELETE ON `comment` FOR EACH ROW BEGIN
	UPDATE `ishow`.`share` SET comment_number=comment_number-1
	WHERE old.share_id=`ishow`.`share`.`share_id`;
    END */$$


DELIMITER ;

/* Trigger structure for table `pointpraise` */

DELIMITER $$

/*!50003 DROP TRIGGER*//*!50032 IF EXISTS */ /*!50003 `trigger_01` */$$

/*!50003 CREATE */ /*!50017 DEFINER = 'root'@'localhost' */ /*!50003 TRIGGER `trigger_01` AFTER INSERT ON `pointpraise` FOR EACH ROW BEGIN
	UPDATE `ishow`.`share` SET point_of_praise=point_of_praise+1
	WHERE new.share_id=`ishow`.`share`.`share_id`;
    END */$$


DELIMITER ;

/* Trigger structure for table `pointpraise` */

DELIMITER $$

/*!50003 DROP TRIGGER*//*!50032 IF EXISTS */ /*!50003 `trigger_02` */$$

/*!50003 CREATE */ /*!50017 DEFINER = 'root'@'localhost' */ /*!50003 TRIGGER `trigger_02` AFTER DELETE ON `pointpraise` FOR EACH ROW BEGIN
	update `ishow`.`share` set point_of_praise=point_of_praise-1
	WHERE old.share_id=`ishow`.`share`.`share_id`;
    END */$$


DELIMITER ;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
