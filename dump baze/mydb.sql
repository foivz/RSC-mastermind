-- MySQL dump 10.13  Distrib 5.5.38, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: mydb
-- ------------------------------------------------------
-- Server version	5.5.38-0ubuntu0.14.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin` (
  `idadmin` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `firstname` varchar(45) NOT NULL,
  `lastname` varchar(45) NOT NULL,
  `phone` varchar(45) NOT NULL,
  `station_idstation` int(11) NOT NULL,
  `admin_role_idadmin_role` int(11) NOT NULL,
  `locked` int(11) DEFAULT '0',
  `lock_count` int(11) DEFAULT '0',
  `pass` varchar(255) NOT NULL DEFAULT '0',
  PRIMARY KEY (`idadmin`,`station_idstation`,`admin_role_idadmin_role`),
  UNIQUE KEY `username_UNIQUE` (`username`),
  KEY `fk_admin_station1_idx` (`station_idstation`),
  KEY `fk_admin_admin_role1_idx` (`admin_role_idadmin_role`),
  CONSTRAINT `fk_admin_admin_role1` FOREIGN KEY (`admin_role_idadmin_role`) REFERENCES `admin_role` (`idadmin_role`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_admin_station1` FOREIGN KEY (`station_idstation`) REFERENCES `station` (`idstation`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES (1,'mpavic1','Mario','PaviÄ‡','0997332833',1,1,0,0,'0'),(4,'marjuric','Mario','JuriÄ‡','0922747662',1,1,0,0,'098f6bcd4621d373cade4e832627b4f6'),(5,'','','','',1,1,0,0,'d41d8cd98f00b204e9800998ecf8427e');
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `admin_role`
--

DROP TABLE IF EXISTS `admin_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin_role` (
  `idadmin_role` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`idadmin_role`),
  UNIQUE KEY `idadmin_role_UNIQUE` (`idadmin_role`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin_role`
--

LOCK TABLES `admin_role` WRITE;
/*!40000 ALTER TABLE `admin_role` DISABLE KEYS */;
INSERT INTO `admin_role` VALUES (1,'superadmin'),(2,'admin');
/*!40000 ALTER TABLE `admin_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `blood_bank`
--

DROP TABLE IF EXISTS `blood_bank`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `blood_bank` (
  `idblood_bank` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(45) NOT NULL,
  `status` float NOT NULL,
  `station_idstation` int(11) NOT NULL,
  PRIMARY KEY (`idblood_bank`,`station_idstation`),
  UNIQUE KEY `idblood_bank_UNIQUE` (`idblood_bank`),
  KEY `fk_blood_bank_station1_idx` (`station_idstation`),
  CONSTRAINT `fk_blood_bank_station1` FOREIGN KEY (`station_idstation`) REFERENCES `station` (`idstation`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `blood_bank`
--

LOCK TABLES `blood_bank` WRITE;
/*!40000 ALTER TABLE `blood_bank` DISABLE KEYS */;
INSERT INTO `blood_bank` VALUES (1,'a',5,1);
/*!40000 ALTER TABLE `blood_bank` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `donation`
--

DROP TABLE IF EXISTS `donation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `donation` (
  `iddonation` int(11) NOT NULL AUTO_INCREMENT,
  `time` datetime NOT NULL,
  `station_idstation` int(11) NOT NULL,
  `user_iduser` int(11) NOT NULL,
  PRIMARY KEY (`iddonation`,`station_idstation`,`user_iduser`),
  KEY `fk_donation_station1_idx` (`station_idstation`),
  KEY `fk_donation_user1_idx` (`user_iduser`),
  CONSTRAINT `fk_donation_station1` FOREIGN KEY (`station_idstation`) REFERENCES `station` (`idstation`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_donation_user1` FOREIGN KEY (`user_iduser`) REFERENCES `user` (`iduser`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `donation`
--

LOCK TABLES `donation` WRITE;
/*!40000 ALTER TABLE `donation` DISABLE KEYS */;
INSERT INTO `donation` VALUES (1,'2014-12-12 12:12:12',1,1),(2,'2014-11-11 11:11:11',1,1);
/*!40000 ALTER TABLE `donation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `invited_user`
--

DROP TABLE IF EXISTS `invited_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `invited_user` (
  `idinvited_user` int(11) NOT NULL AUTO_INCREMENT,
  `phone` varchar(45) DEFAULT NULL,
  `active` tinyint(1) DEFAULT NULL,
  `user_iduser` int(11) NOT NULL,
  PRIMARY KEY (`idinvited_user`,`user_iduser`),
  KEY `fk_invited_user_user1_idx` (`user_iduser`),
  CONSTRAINT `fk_invited_user_user1` FOREIGN KEY (`user_iduser`) REFERENCES `user` (`iduser`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `invited_user`
--

LOCK TABLES `invited_user` WRITE;
/*!40000 ALTER TABLE `invited_user` DISABLE KEYS */;
/*!40000 ALTER TABLE `invited_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `station`
--

DROP TABLE IF EXISTS `station`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `station` (
  `idstation` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `adress` varchar(45) NOT NULL,
  `phone` varchar(45) NOT NULL,
  `lat` varchar(45) DEFAULT NULL,
  `long` varchar(45) DEFAULT NULL,
  `city` varchar(45) NOT NULL,
  `low` float NOT NULL,
  PRIMARY KEY (`idstation`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `station`
--

LOCK TABLES `station` WRITE;
/*!40000 ALTER TABLE `station` DISABLE KEYS */;
INSERT INTO `station` VALUES (1,'KBC Zagreb','Rebro 3','012837233','12.323','14.3232','Zagreb',10),(2,'KBC Osijek','ZagrebaÄka 1','053123323','12',NULL,'Zagreb',10);
/*!40000 ALTER TABLE `station` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `iduser` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(45) NOT NULL,
  `pass` varchar(45) NOT NULL,
  `phone` varchar(45) NOT NULL,
  `firstname` varchar(45) NOT NULL,
  `lastname` varchar(45) NOT NULL,
  `lat` float DEFAULT NULL,
  `long` float DEFAULT NULL,
  `locked` tinyint(1) NOT NULL DEFAULT '0',
  `lock_count` int(11) NOT NULL DEFAULT '0',
  `active` tinyint(1) NOT NULL DEFAULT '1',
  `score` int(11) DEFAULT '0',
  `gender` varchar(45) NOT NULL,
  `weight` int(11) NOT NULL,
  `birth_year` year(4) NOT NULL,
  `user_data_iduser_data` int(11) NOT NULL DEFAULT '0',
  `api_key` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`iduser`,`user_data_iduser_data`),
  UNIQUE KEY `iduser_UNIQUE` (`iduser`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  KEY `fk_user_user_data1_idx` (`user_data_iduser_data`),
  CONSTRAINT `fk_user_user_data1` FOREIGN KEY (`user_data_iduser_data`) REFERENCES `user_data` (`iduser_data`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'aaa@aaa.com','test','123','aaa','aaa',12.1231,12.67,0,1,1,0,'m',12,1990,1,'23edsfgafdhfghsfgh'),(2,'aaa@aaa.com1','098f6bcd4621d373cade4e832627b4f6','3254364543','asdgfdhffsdgsf','asdfdsgfhg345',NULL,NULL,0,0,1,0,'m',78,1990,2,'e8ca8559a6efff3442dade982643b994'),(3,'aaa@aaa.com1232323','098f6bcd4621d373cade4e832627b4f6','3254364543','asdgfdhffsdgsf','asdfdsgfhg345',NULL,NULL,0,0,1,0,'m',78,1990,3,'ef5292892783fcc252ab60a4f0f377bf'),(4,'test6@gmail.com','098f6bcd4621d373cade4e832627b4f6','0300303','test','test',NULL,NULL,0,0,1,0,'M',56,1911,4,'ff4f6af26f7a41c6fb3d8b7edc5b6190'),(5,'moja@gmail.com','098f6bcd4621d373cade4e832627b4f6','99999','test','test',NULL,NULL,0,0,1,0,'M',77,0000,5,'464c4786054e67014f0e9e0cbd0e3197'),(6,'testni@gmail.com','098f6bcd4621d373cade4e832627b4f6','34343434','test','test',NULL,NULL,0,0,1,0,'M',55,0000,6,'901c22eec43cb99423d350ee3ff8bbba'),(7,'marko@gmail.com','098f6bcd4621d373cade4e832627b4f6','04234345','Marko','Horvat',NULL,NULL,0,0,1,0,'M',78,1990,7,'5832c424c15b9bd367b1c3d5c5cd928a');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_data`
--

DROP TABLE IF EXISTS `user_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_data` (
  `iduser_data` int(11) NOT NULL AUTO_INCREMENT,
  `blood_type` varchar(45) NOT NULL,
  `piercing` tinyint(1) NOT NULL,
  `tattoo` tinyint(1) NOT NULL,
  `sickness` tinyint(1) NOT NULL,
  PRIMARY KEY (`iduser_data`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_data`
--

LOCK TABLES `user_data` WRITE;
/*!40000 ALTER TABLE `user_data` DISABLE KEYS */;
INSERT INTO `user_data` VALUES (1,'A',0,0,0),(2,'a',0,0,0),(3,'a',0,0,0),(4,'0+',0,0,0),(5,'A-',0,0,0),(6,'0+',0,0,0),(7,'AB+',0,0,0);
/*!40000 ALTER TABLE `user_data` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'mydb'
--

--
-- Dumping routines for database 'mydb'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-11-23  0:36:40
