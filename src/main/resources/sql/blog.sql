/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.7.24 : Database - myblog
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`myblog` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `myblog`;

/*Table structure for table `blog` */

DROP TABLE IF EXISTS `blog`;

CREATE TABLE `blog` (
  `bid` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) DEFAULT NULL,
  `btid` int(11) NOT NULL,
  `title` text,
  `titleHtml` text,
  `titleImg` text,
  `body` text,
  `bodyHtml` text,
  `createDatetime` datetime DEFAULT NULL,
  `deleted` int(11) DEFAULT '0',
  PRIMARY KEY (`bid`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

/*Data for the table `blog` */

/*Table structure for table `blog_type` */

DROP TABLE IF EXISTS `blog_type`;

CREATE TABLE `blog_type` (
  `btid` int(11) NOT NULL AUTO_INCREMENT,
  `blogType` varchar(50) DEFAULT NULL,
  `createDatetime` datetime DEFAULT NULL,
  `blogCount` int(11) DEFAULT '0' COMMENT '该类型博客文章数',
  `del` int(11) DEFAULT '0',
  PRIMARY KEY (`btid`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

/*Data for the table `blog_type` */


/*Table structure for table `leaving_messing` */

DROP TABLE IF EXISTS `leaving_messing`;

CREATE TABLE `leaving_messing` (
  `leid` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) DEFAULT NULL,
  `username` varchar(20) DEFAULT NULL,
  `message` varchar(300) DEFAULT NULL,
  `createdatetime` datetime DEFAULT NULL,
  PRIMARY KEY (`leid`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

/*Data for the table `leaving_messing` */


/*Table structure for table `popularity` */

DROP TABLE IF EXISTS `popularity`;

CREATE TABLE `popularity` (
  `poid` int(11) NOT NULL AUTO_INCREMENT,
  `bid` int(11) DEFAULT NULL,
  `createDatetime` datetime DEFAULT NULL,
  PRIMARY KEY (`poid`)
) ENGINE=InnoDB AUTO_INCREMENT=116 DEFAULT CHARSET=utf8;

/*Data for the table `popularity` */


/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `uid` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) DEFAULT NULL,
  `pwd` varchar(20) DEFAULT NULL,
  `imgUrl` varchar(50) DEFAULT NULL,
  `email` varchar(20) DEFAULT NULL,
  `isup` int(11) DEFAULT NULL,
  `createUpDatetime` datetime DEFAULT NULL COMMENT '成为up主的时间',
  `txlodcount` int(11) DEFAULT '0',
  PRIMARY KEY (`uid`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;


