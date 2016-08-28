-- MySQL dump 10.13  Distrib 5.5.3-m3, for Win32 (x86)
--
-- Host: localhost    Database: hms
-- ------------------------------------------------------
-- Server version	5.5.3-m3-community

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
-- Table structure for table `doctor`
--

DROP TABLE IF EXISTS `doctor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `doctor` (
  `dr_id` int(11) NOT NULL AUTO_INCREMENT,
  `dr_name` varchar(255) DEFAULT NULL,
  `contact` varchar(255) DEFAULT NULL,
  `degree` varchar(255) DEFAULT NULL,
  `sp` varchar(255) DEFAULT NULL,
  `fees` int(11) DEFAULT NULL,
  `mail_id` varchar(255) DEFAULT NULL,
  `timings` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`dr_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `doctor`
--

LOCK TABLES `doctor` WRITE;
/*!40000 ALTER TABLE `doctor` DISABLE KEYS */;
INSERT INTO `doctor` VALUES (1,'R.K. GUPTA','9414244333','M.B.B.S','Physician',150,'rk@gmail.com','9:00Am to 11:00Am'),(2,'K.K. SHARMA','9414344555','M.B.B.S','Orthopaedic',400,'kk@gmail.com','9:00Am to 11:00Am'),(3,'M.K Sharma','9414244777','B.D.S','Paediatrician',400,'mk@gmail.com','9:00 am');
/*!40000 ALTER TABLE `doctor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ipdmedicine`
--

DROP TABLE IF EXISTS `ipdmedicine`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ipdmedicine` (
  `date` varchar(255) DEFAULT NULL,
  `ipd_no` int(11) DEFAULT NULL,
  `md_cat` varchar(255) DEFAULT NULL,
  `md_name` varchar(255) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `amount` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ipdmedicine`
--

LOCK TABLES `ipdmedicine` WRITE;
/*!40000 ALTER TABLE `ipdmedicine` DISABLE KEYS */;
INSERT INTO `ipdmedicine` VALUES ('Tue Jul 19 11:27:00 IST 2016',1,'1','1',5,10),('Tue Jul 19 11:36:53 IST 2016',1,'2','2',10,10);
/*!40000 ALTER TABLE `ipdmedicine` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ipdpatient`
--

DROP TABLE IF EXISTS `ipdpatient`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ipdpatient` (
  `ipd_no` int(11) NOT NULL AUTO_INCREMENT,
  `pat_id` int(11) DEFAULT NULL,
  `doa` varchar(255) DEFAULT NULL,
  `dod` varchar(255) DEFAULT NULL,
  `room_number` int(11) DEFAULT NULL,
  `procharges` int(11) DEFAULT NULL,
  `drcharges` int(11) DEFAULT NULL,
  `medcharges` int(11) DEFAULT NULL,
  `othercharges` int(11) DEFAULT NULL,
  `total` int(11) DEFAULT NULL,
  `adpay` int(11) DEFAULT NULL,
  `net` int(11) DEFAULT NULL,
  PRIMARY KEY (`ipd_no`),
  KEY `pat_id` (`pat_id`),
  CONSTRAINT `ipdpatient_ibfk_1` FOREIGN KEY (`pat_id`) REFERENCES `patient` (`pat_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ipdpatient`
--

LOCK TABLES `ipdpatient` WRITE;
/*!40000 ALTER TABLE `ipdpatient` DISABLE KEYS */;
INSERT INTO `ipdpatient` VALUES (1,1,'19.07.2016','Not Discharged Yet',101,0,0,0,0,0,1000,0);
/*!40000 ALTER TABLE `ipdpatient` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ipdprocedure`
--

DROP TABLE IF EXISTS `ipdprocedure`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ipdprocedure` (
  `date` varchar(255) DEFAULT NULL,
  `ipd_no` int(11) DEFAULT NULL,
  `p_id` varchar(255) DEFAULT NULL,
  `p_name` varchar(255) DEFAULT NULL,
  `amount` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ipdprocedure`
--

LOCK TABLES `ipdprocedure` WRITE;
/*!40000 ALTER TABLE `ipdprocedure` DISABLE KEYS */;
INSERT INTO `ipdprocedure` VALUES ('Tue Jul 19 10:49:15 IST 2016',1,'1','1',120),('Tue Jul 19 11:26:13 IST 2016',1,'1','1',120);
/*!40000 ALTER TABLE `ipdprocedure` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mcategory`
--

DROP TABLE IF EXISTS `mcategory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mcategory` (
  `ct_id` int(11) NOT NULL AUTO_INCREMENT,
  `ct_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ct_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mcategory`
--

LOCK TABLES `mcategory` WRITE;
/*!40000 ALTER TABLE `mcategory` DISABLE KEYS */;
INSERT INTO `mcategory` VALUES (1,'CAPSULES'),(2,'TABLETS'),(3,'SYRUP');
/*!40000 ALTER TABLE `mcategory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `med`
--

DROP TABLE IF EXISTS `med`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `med` (
  `med_id` int(11) NOT NULL,
  `med_name` varchar(255) DEFAULT NULL,
  `med_mrp` int(11) DEFAULT NULL,
  `medcat_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`med_id`),
  KEY `medcat_id` (`medcat_id`),
  CONSTRAINT `med_ibfk_1` FOREIGN KEY (`medcat_id`) REFERENCES `medcat` (`medcat_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `med`
--

LOCK TABLES `med` WRITE;
/*!40000 ALTER TABLE `med` DISABLE KEYS */;
/*!40000 ALTER TABLE `med` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `medcat`
--

DROP TABLE IF EXISTS `medcat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `medcat` (
  `medcat_id` int(11) NOT NULL,
  `medcat_name` varchar(255) DEFAULT NULL,
  `med_count` int(11) DEFAULT NULL,
  PRIMARY KEY (`medcat_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `medcat`
--

LOCK TABLES `medcat` WRITE;
/*!40000 ALTER TABLE `medcat` DISABLE KEYS */;
/*!40000 ALTER TABLE `medcat` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `medicine`
--

DROP TABLE IF EXISTS `medicine`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `medicine` (
  `md_id` int(11) NOT NULL AUTO_INCREMENT,
  `md_name` varchar(255) DEFAULT NULL,
  `md_cat` varchar(255) DEFAULT NULL,
  `unit` varchar(255) DEFAULT NULL,
  `price` int(11) DEFAULT NULL,
  `ct_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`md_id`),
  KEY `ct_id` (`ct_id`),
  CONSTRAINT `medicine_ibfk_1` FOREIGN KEY (`ct_id`) REFERENCES `mcategory` (`ct_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `medicine`
--

LOCK TABLES `medicine` WRITE;
/*!40000 ALTER TABLE `medicine` DISABLE KEYS */;
INSERT INTO `medicine` VALUES (1,'BECASULE','CAPSULES','pcs',2,1),(2,'AVIL','TABLETS','pcs',1,2);
/*!40000 ALTER TABLE `medicine` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `opd`
--

DROP TABLE IF EXISTS `opd`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `opd` (
  `reg_no` int(11) NOT NULL AUTO_INCREMENT,
  `reg_date` datetime DEFAULT NULL,
  `pat_id` int(11) DEFAULT NULL,
  `amount` int(11) DEFAULT NULL,
  `dr_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`reg_no`),
  KEY `dr_id` (`dr_id`),
  KEY `pat_id` (`pat_id`),
  CONSTRAINT `opd_ibfk_1` FOREIGN KEY (`dr_id`) REFERENCES `doctor` (`dr_id`),
  CONSTRAINT `opd_ibfk_2` FOREIGN KEY (`pat_id`) REFERENCES `patient` (`pat_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `opd`
--

LOCK TABLES `opd` WRITE;
/*!40000 ALTER TABLE `opd` DISABLE KEYS */;
INSERT INTO `opd` VALUES (1,'2019-07-20 16:00:00',2,400,2),(2,'2019-07-20 16:00:00',1,150,1);
/*!40000 ALTER TABLE `opd` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `patient`
--

DROP TABLE IF EXISTS `patient`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `patient` (
  `pat_id` int(11) NOT NULL AUTO_INCREMENT,
  `pat_name` varchar(255) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `father_name` varchar(255) DEFAULT NULL,
  `gender` enum('Male','Female') DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `contact` varchar(255) DEFAULT NULL,
  `mail_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`pat_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patient`
--

LOCK TABLES `patient` WRITE;
/*!40000 ALTER TABLE `patient` DISABLE KEYS */;
INSERT INTO `patient` VALUES (1,'Rakesh Kumar Gupta',21,'Vinay Gupta','Male','123','9414244777','r@gmail.com'),(2,'Vikas Kumar Khanna',26,'Gaurav Khanna','Male','123	','9414244888','vk@gmail.com');
/*!40000 ALTER TABLE `patient` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pcat`
--

DROP TABLE IF EXISTS `pcat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pcat` (
  `pcat_id` int(11) NOT NULL AUTO_INCREMENT,
  `pcat_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`pcat_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pcat`
--

LOCK TABLES `pcat` WRITE;
/*!40000 ALTER TABLE `pcat` DISABLE KEYS */;
INSERT INTO `pcat` VALUES (1,'X-RAY'),(2,'BLOOD TEST'),(3,'OPERATION');
/*!40000 ALTER TABLE `pcat` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pprocedure`
--

DROP TABLE IF EXISTS `pprocedure`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pprocedure` (
  `p_id` int(11) NOT NULL AUTO_INCREMENT,
  `p_name` varchar(255) DEFAULT NULL,
  `price` int(11) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `pcat_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`p_id`),
  KEY `pcat_id` (`pcat_id`),
  CONSTRAINT `pprocedure_ibfk_1` FOREIGN KEY (`pcat_id`) REFERENCES `pcat` (`pcat_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pprocedure`
--

LOCK TABLES `pprocedure` WRITE;
/*!40000 ALTER TABLE `pprocedure` DISABLE KEYS */;
INSERT INTO `pprocedure` VALUES (1,'FOOT X-RAY',120,'AAA',1),(2,'RBC',150,'AAA',2),(3,'LEG X-RAY',250,'aaa',1),(4,'Blood Group',50,'aa',2);
/*!40000 ALTER TABLE `pprocedure` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `room`
--

DROP TABLE IF EXISTS `room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `room` (
  `room_id` int(11) NOT NULL AUTO_INCREMENT,
  `room_number` int(11) DEFAULT NULL,
  `room_category` varchar(255) DEFAULT NULL,
  `room_count` int(11) DEFAULT NULL,
  `room_description` varchar(255) DEFAULT NULL,
  `room_charges` int(11) DEFAULT NULL,
  PRIMARY KEY (`room_id`),
  UNIQUE KEY `room_number` (`room_number`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room`
--

LOCK TABLES `room` WRITE;
/*!40000 ALTER TABLE `room` DISABLE KEYS */;
INSERT INTO `room` VALUES (1,101,'DELUXE ROOM',1,'LED,TV',1000),(2,102,'DELUXE ROOM',1,'LET,TV,FRIDGE',1000),(3,201,'GENERAL WARD',10,'TV',250);
/*!40000 ALTER TABLE `room` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roomcat`
--

DROP TABLE IF EXISTS `roomcat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `roomcat` (
  `roomcat_id` int(11) NOT NULL AUTO_INCREMENT,
  `roomcat_name` varchar(255) DEFAULT NULL,
  `room_count` int(11) DEFAULT NULL,
  `room_charge` int(11) DEFAULT NULL,
  PRIMARY KEY (`roomcat_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roomcat`
--

LOCK TABLES `roomcat` WRITE;
/*!40000 ALTER TABLE `roomcat` DISABLE KEYS */;
INSERT INTO `roomcat` VALUES (1,'DELUXE ROOM',10,1000),(2,'GENERAL WARD',2,250);
/*!40000 ALTER TABLE `roomcat` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `user_id` varchar(255) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `usertype` enum('admin','doctor','operator') DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES ('kb','12345678','admin');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-07-20 10:12:30
