CREATE DATABASE  IF NOT EXISTS `alepos` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `alepos`;
-- MySQL dump 10.13  Distrib 5.6.13, for Win32 (x86)
--
-- Host: localhost    Database: alepos
-- ------------------------------------------------------
-- Server version	5.6.15

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
-- Table structure for table `bill`
--

DROP TABLE IF EXISTS `bill`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bill` (
  `bill_id` int(11) NOT NULL,
  `item_total_amount` decimal(19,4) NOT NULL,
  `service_charge` decimal(19,4) DEFAULT NULL,
  `vat` decimal(19,4) DEFAULT NULL,
  `bill_discount` decimal(19,4) DEFAULT NULL,
  `complimentary_amount` decimal(10,0) DEFAULT '0',
  `bill_total` decimal(19,4) NOT NULL,
  `total_received` decimal(19,4) NOT NULL,
  `customer_id` int(11) DEFAULT NULL,
  `payment_type` tinyint(1) NOT NULL,
  `bill_datetime` date NOT NULL,
  `void` tinyint(1) DEFAULT '0',
  `department_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `complimentary_flag` tinyint(1) DEFAULT '0',
  `repay` tinyint(1) DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bill`
--

LOCK TABLES `bill` WRITE;
/*!40000 ALTER TABLE `bill` DISABLE KEYS */;
/*!40000 ALTER TABLE `bill` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bill_complimentary`
--

DROP TABLE IF EXISTS `bill_complimentary`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bill_complimentary` (
  `bill_id` int(11) NOT NULL,
  `complimentary_id` int(11) NOT NULL,
  `bill_complimentary_id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`bill_complimentary_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bill_complimentary`
--

LOCK TABLES `bill_complimentary` WRITE;
/*!40000 ALTER TABLE `bill_complimentary` DISABLE KEYS */;
/*!40000 ALTER TABLE `bill_complimentary` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bill_item_info`
--

DROP TABLE IF EXISTS `bill_item_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bill_item_info` (
  `bill_id` int(11) NOT NULL,
  `menu_id` int(11) DEFAULT NULL,
  `quantity` decimal(19,4) DEFAULT NULL,
  `complimentary_type` tinyint(1) DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bill_item_info`
--

LOCK TABLES `bill_item_info` WRITE;
/*!40000 ALTER TABLE `bill_item_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `bill_item_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `center_store_info`
--

DROP TABLE IF EXISTS `center_store_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `center_store_info` (
  `store_id` int(11) NOT NULL AUTO_INCREMENT,
  `store_name` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`store_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `center_store_info`
--

LOCK TABLES `center_store_info` WRITE;
/*!40000 ALTER TABLE `center_store_info` DISABLE KEYS */;
INSERT INTO `center_store_info` VALUES (1,'centerstore');
/*!40000 ALTER TABLE `center_store_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `centerstore_record`
--

DROP TABLE IF EXISTS `centerstore_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `centerstore_record` (
  `centerstore_record_id` int(11) NOT NULL,
  `item_id` int(11) NOT NULL,
  `bf` int(11) NOT NULL,
  `issue_to_res` int(11) DEFAULT NULL,
  `issue_return` int(11) DEFAULT NULL,
  `purchase_return` int(11) DEFAULT NULL,
  `lot_add` int(11) DEFAULT NULL,
  `cf` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `centerstore_record`
--

LOCK TABLES `centerstore_record` WRITE;
/*!40000 ALTER TABLE `centerstore_record` DISABLE KEYS */;
/*!40000 ALTER TABLE `centerstore_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `centerstore_stock`
--

DROP TABLE IF EXISTS `centerstore_stock`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `centerstore_stock` (
  `item_id` int(11) NOT NULL AUTO_INCREMENT,
  `item_name` varchar(200) NOT NULL,
  `category_id` int(11) NOT NULL,
  `unit_id` int(11) NOT NULL,
  `item_buy_rate` decimal(19,4) DEFAULT NULL,
  `item_threshold` int(11) DEFAULT NULL,
  `item_expiry_date` date DEFAULT NULL,
  `item_entry_date` datetime NOT NULL,
  `distributor_id` int(11) DEFAULT NULL,
  `total_qty` double DEFAULT NULL,
  PRIMARY KEY (`item_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `centerstore_stock`
--

LOCK TABLES `centerstore_stock` WRITE;
/*!40000 ALTER TABLE `centerstore_stock` DISABLE KEYS */;
INSERT INTO `centerstore_stock` VALUES (1,'Juice',1,1,100.0000,0,'2014-04-21','2014-04-21 20:15:15',1,50000);
/*!40000 ALTER TABLE `centerstore_stock` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `client_company`
--

DROP TABLE IF EXISTS `client_company`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `client_company` (
  `company_id` int(11) NOT NULL,
  `company_name` varchar(200) DEFAULT NULL,
  `company_address` varchar(200) NOT NULL,
  `company_phone` varchar(100) DEFAULT NULL,
  `company_email` varchar(100) DEFAULT NULL,
  `company_panno` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `client_company`
--

LOCK TABLES `client_company` WRITE;
/*!40000 ALTER TABLE `client_company` DISABLE KEYS */;
/*!40000 ALTER TABLE `client_company` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `company_info`
--

DROP TABLE IF EXISTS `company_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `company_info` (
  `company_name` varchar(510) NOT NULL,
  `company_address` varchar(510) NOT NULL,
  `bill_greet` varchar(200) DEFAULT NULL,
  `phone` varchar(100) NOT NULL,
  `fax` varchar(100) DEFAULT NULL,
  `website` varchar(200) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `pan_no` bigint(20) DEFAULT NULL,
  `register` tinyint(3) unsigned DEFAULT '0',
  `company_logo` longtext,
  `company_svc` double DEFAULT '0',
  `company_vat` double DEFAULT '0',
  `company_slogan` longtext,
  `company_logo_image` mediumblob,
  `register_code` varchar(45) DEFAULT NULL,
  `serial_code` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `company_info`
--

LOCK TABLES `company_info` WRITE;
/*!40000 ALTER TABLE `company_info` DISABLE KEYS */;
INSERT INTO `company_info` VALUES ('Namaste Air NanDos','Lakeside','','1','','','',1123,1,'',0,0,'',NULL,NULL,NULL);
/*!40000 ALTER TABLE `company_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `complimentary_info`
--

DROP TABLE IF EXISTS `complimentary_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `complimentary_info` (
  `complimentary_id` int(11) NOT NULL AUTO_INCREMENT,
  `complimentary_reason` text,
  PRIMARY KEY (`complimentary_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `complimentary_info`
--

LOCK TABLES `complimentary_info` WRITE;
/*!40000 ALTER TABLE `complimentary_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `complimentary_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer_info`
--

DROP TABLE IF EXISTS `customer_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customer_info` (
  `customer_id` int(11) NOT NULL AUTO_INCREMENT,
  `customer_name` varchar(200) NOT NULL,
  `customer_address` varchar(200) DEFAULT NULL,
  `customer_phone` varchar(100) DEFAULT NULL,
  `customer_type` varchar(100) DEFAULT NULL,
  `total_amount` decimal(19,4) DEFAULT NULL,
  `sales_discount` decimal(19,4) DEFAULT NULL,
  `grand_total` decimal(19,4) DEFAULT NULL,
  PRIMARY KEY (`customer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer_info`
--

LOCK TABLES `customer_info` WRITE;
/*!40000 ALTER TABLE `customer_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `customer_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `department_info`
--

DROP TABLE IF EXISTS `department_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `department_info` (
  `department_id` int(11) NOT NULL AUTO_INCREMENT,
  `department_name` varchar(45) NOT NULL,
  `default_printer` varchar(100) DEFAULT NULL,
  `order_printer` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`department_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `department_info`
--

LOCK TABLES `department_info` WRITE;
/*!40000 ALTER TABLE `department_info` DISABLE KEYS */;
INSERT INTO `department_info` VALUES (1,'Resturant','Brother DCP-J315W PrinterBrother DCP-J315W Printer(Default)','EPSON LQ-300+II ESC/P2');
/*!40000 ALTER TABLE `department_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `department_store_stock`
--

DROP TABLE IF EXISTS `department_store_stock`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `department_store_stock` (
  `department_item_id` int(11) NOT NULL AUTO_INCREMENT,
  `item_id` int(11) NOT NULL,
  `unit_id` int(11) NOT NULL,
  `total_qty` double NOT NULL,
  `item_threshold` double DEFAULT NULL,
  `item_expiry_date` date DEFAULT NULL,
  `department_id` int(11) NOT NULL,
  PRIMARY KEY (`department_item_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `department_store_stock`
--

LOCK TABLES `department_store_stock` WRITE;
/*!40000 ALTER TABLE `department_store_stock` DISABLE KEYS */;
INSERT INTO `department_store_stock` VALUES (1,1,1,50000,NULL,'2014-04-21',1);
/*!40000 ALTER TABLE `department_store_stock` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `department_user`
--

DROP TABLE IF EXISTS `department_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `department_user` (
  `department_user_id` int(11) NOT NULL AUTO_INCREMENT,
  `department_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`department_user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `department_user`
--

LOCK TABLES `department_user` WRITE;
/*!40000 ALTER TABLE `department_user` DISABLE KEYS */;
INSERT INTO `department_user` VALUES (1,1,1);
/*!40000 ALTER TABLE `department_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `designation_info`
--

DROP TABLE IF EXISTS `designation_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `designation_info` (
  `designation_id` int(11) NOT NULL AUTO_INCREMENT,
  `designation_title` varchar(100) NOT NULL,
  `designation_description` text,
  `waiter_flag` bit(1) DEFAULT NULL,
  PRIMARY KEY (`designation_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `designation_info`
--

LOCK TABLES `designation_info` WRITE;
/*!40000 ALTER TABLE `designation_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `designation_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `distributor`
--

DROP TABLE IF EXISTS `distributor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `distributor` (
  `distributor_id` int(11) NOT NULL AUTO_INCREMENT,
  `distributor_name` varchar(100) NOT NULL,
  `distributor_address` varchar(200) DEFAULT NULL,
  `distributor_phone` varchar(30) DEFAULT NULL,
  `distributor_email` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`distributor_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `distributor`
--

LOCK TABLES `distributor` WRITE;
/*!40000 ALTER TABLE `distributor` DISABLE KEYS */;
INSERT INTO `distributor` VALUES (1,'DREAMSYS','AD','ad',NULL);
/*!40000 ALTER TABLE `distributor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `generate_billid`
--

DROP TABLE IF EXISTS `generate_billid`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `generate_billid` (
  `autoinc_billid` int(11) NOT NULL AUTO_INCREMENT,
  `bill_status` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`autoinc_billid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `generate_billid`
--

LOCK TABLES `generate_billid` WRITE;
/*!40000 ALTER TABLE `generate_billid` DISABLE KEYS */;
INSERT INTO `generate_billid` VALUES (1,0);
/*!40000 ALTER TABLE `generate_billid` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `generate_orderid`
--

DROP TABLE IF EXISTS `generate_orderid`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `generate_orderid` (
  `autoinc_orderid` int(11) NOT NULL AUTO_INCREMENT,
  `order_status` tinyint(1) NOT NULL,
  PRIMARY KEY (`autoinc_orderid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `generate_orderid`
--

LOCK TABLES `generate_orderid` WRITE;
/*!40000 ALTER TABLE `generate_orderid` DISABLE KEYS */;
/*!40000 ALTER TABLE `generate_orderid` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hybrid_menu`
--

DROP TABLE IF EXISTS `hybrid_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hybrid_menu` (
  `department_item_id` int(11) NOT NULL,
  `quantity` decimal(19,4) NOT NULL,
  `unit_id` int(11) NOT NULL,
  `parent_menu_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hybrid_menu`
--

LOCK TABLES `hybrid_menu` WRITE;
/*!40000 ALTER TABLE `hybrid_menu` DISABLE KEYS */;
/*!40000 ALTER TABLE `hybrid_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `issue`
--

DROP TABLE IF EXISTS `issue`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `issue` (
  `issue_id` int(11) NOT NULL AUTO_INCREMENT,
  `department_item_id` int(11) NOT NULL,
  `quantity` double DEFAULT NULL,
  `unit_id` int(11) DEFAULT NULL,
  `issue_from` int(11) NOT NULL,
  `issue_to` varchar(50) DEFAULT NULL,
  `issue_date` date NOT NULL,
  PRIMARY KEY (`issue_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `issue`
--

LOCK TABLES `issue` WRITE;
/*!40000 ALTER TABLE `issue` DISABLE KEYS */;
INSERT INTO `issue` VALUES (1,1,50,1,1,'1','2014-04-21');
/*!40000 ALTER TABLE `issue` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `issue_return`
--

DROP TABLE IF EXISTS `issue_return`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `issue_return` (
  `issue_return_id` int(11) NOT NULL AUTO_INCREMENT,
  `issue_id` int(11) NOT NULL,
  `quantity` double NOT NULL,
  `unit_id` int(11) NOT NULL,
  `return_reason` longtext,
  `return_date` datetime NOT NULL,
  PRIMARY KEY (`issue_return_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `issue_return`
--

LOCK TABLES `issue_return` WRITE;
/*!40000 ALTER TABLE `issue_return` DISABLE KEYS */;
/*!40000 ALTER TABLE `issue_return` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `item_category`
--

DROP TABLE IF EXISTS `item_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `item_category` (
  `category_id` int(11) NOT NULL AUTO_INCREMENT,
  `category_name` varchar(100) NOT NULL,
  `parent` varchar(100) NOT NULL,
  PRIMARY KEY (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item_category`
--

LOCK TABLES `item_category` WRITE;
/*!40000 ALTER TABLE `item_category` DISABLE KEYS */;
INSERT INTO `item_category` VALUES (1,'KHAJA','1');
/*!40000 ALTER TABLE `item_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `item_list`
--

DROP TABLE IF EXISTS `item_list`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `item_list` (
  `item_id` int(11) NOT NULL,
  `item_name` varchar(100) NOT NULL,
  `category_id` int(11) NOT NULL,
  `unit_id` int(11) NOT NULL,
  `item_buy_sale` double DEFAULT NULL,
  `item_sale_rate` double NOT NULL,
  `item_quantity` int(11) DEFAULT NULL,
  `item_available` tinyint(3) unsigned NOT NULL,
  `item_limit` int(11) DEFAULT NULL,
  `kot_bot` tinyint(3) unsigned NOT NULL,
  `distributor_id` int(11) DEFAULT NULL,
  `item_expire_date` date DEFAULT NULL,
  `item_entry_date` datetime NOT NULL,
  `item_image` varchar(200) DEFAULT NULL,
  `item_imagepath` varchar(510) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item_list`
--

LOCK TABLES `item_list` WRITE;
/*!40000 ALTER TABLE `item_list` DISABLE KEYS */;
/*!40000 ALTER TABLE `item_list` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `item_lot`
--

DROP TABLE IF EXISTS `item_lot`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `item_lot` (
  `lot_id` int(11) NOT NULL AUTO_INCREMENT,
  `item_id` int(11) NOT NULL,
  `item_quantity` int(11) NOT NULL,
  `item_expire_date` date DEFAULT NULL,
  `item_buy_rate` decimal(19,4) DEFAULT NULL,
  `item_sale_rate` decimal(19,4) NOT NULL,
  `item_log_entrydate` date NOT NULL,
  PRIMARY KEY (`lot_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item_lot`
--

LOCK TABLES `item_lot` WRITE;
/*!40000 ALTER TABLE `item_lot` DISABLE KEYS */;
/*!40000 ALTER TABLE `item_lot` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `item_sub_category`
--

DROP TABLE IF EXISTS `item_sub_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `item_sub_category` (
  `sub_category_id` int(11) NOT NULL AUTO_INCREMENT,
  `sub_category_name` varchar(100) NOT NULL,
  `category_id` int(11) NOT NULL,
  PRIMARY KEY (`sub_category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item_sub_category`
--

LOCK TABLES `item_sub_category` WRITE;
/*!40000 ALTER TABLE `item_sub_category` DISABLE KEYS */;
INSERT INTO `item_sub_category` VALUES (1,'NASTA',1);
/*!40000 ALTER TABLE `item_sub_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `item_unit`
--

DROP TABLE IF EXISTS `item_unit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `item_unit` (
  `unit_id` int(11) NOT NULL AUTO_INCREMENT,
  `unit_name` varchar(100) NOT NULL,
  `unit_relative_quantity` double NOT NULL,
  `unit_type` varchar(50) NOT NULL,
  PRIMARY KEY (`unit_id`),
  UNIQUE KEY `IX_item_unit` (`unit_id`),
  UNIQUE KEY `IX_unit_name` (`unit_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item_unit`
--

LOCK TABLES `item_unit` WRITE;
/*!40000 ALTER TABLE `item_unit` DISABLE KEYS */;
INSERT INTO `item_unit` VALUES (1,'1lt bootle',1000,'ml');
/*!40000 ALTER TABLE `item_unit` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `menu`
--

DROP TABLE IF EXISTS `menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `menu` (
  `menu_id` int(11) NOT NULL AUTO_INCREMENT,
  `menu_name` varchar(100) NOT NULL,
  `item_type` tinyint(1) NOT NULL,
  `department_item_id` int(11) DEFAULT NULL,
  `unit_id` int(11) NOT NULL,
  `quantity` decimal(19,4) NOT NULL,
  `category_id` int(11) DEFAULT NULL,
  `retail_price` decimal(19,4) NOT NULL,
  `wholesale_price` decimal(19,4) DEFAULT NULL,
  `image_path` longtext,
  `date` date DEFAULT NULL,
  `hybrid_type` tinyint(1) DEFAULT NULL,
  `department_id` int(11) NOT NULL,
  `menu_image` mediumblob,
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `menu`
--

LOCK TABLES `menu` WRITE;
/*!40000 ALTER TABLE `menu` DISABLE KEYS */;
/*!40000 ALTER TABLE `menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_bill`
--

DROP TABLE IF EXISTS `order_bill`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order_bill` (
  `order_bill_id` int(11) NOT NULL AUTO_INCREMENT,
  `order_id` int(11) NOT NULL,
  `bill_id` varchar(45) NOT NULL,
  PRIMARY KEY (`order_bill_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_bill`
--

LOCK TABLES `order_bill` WRITE;
/*!40000 ALTER TABLE `order_bill` DISABLE KEYS */;
/*!40000 ALTER TABLE `order_bill` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_item_list`
--

DROP TABLE IF EXISTS `order_item_list`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order_item_list` (
  `order_id` int(11) NOT NULL,
  `menu_id` int(11) NOT NULL,
  `quantity` decimal(19,4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_item_list`
--

LOCK TABLES `order_item_list` WRITE;
/*!40000 ALTER TABLE `order_item_list` DISABLE KEYS */;
/*!40000 ALTER TABLE `order_item_list` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_list`
--

DROP TABLE IF EXISTS `order_list`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order_list` (
  `autoinc_id` int(11) NOT NULL AUTO_INCREMENT,
  `order_id` int(11) NOT NULL,
  `table_id` int(11) DEFAULT NULL,
  `customer_id` int(11) DEFAULT NULL,
  `waiter_id` int(11) DEFAULT NULL,
  `total_amount` decimal(19,4) NOT NULL,
  `date` date DEFAULT NULL,
  `paid` tinyint(1) NOT NULL DEFAULT '0',
  `bill_id` int(11) DEFAULT NULL,
  `user_id` int(11) NOT NULL,
  `department_id` int(11) NOT NULL,
  PRIMARY KEY (`autoinc_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_list`
--

LOCK TABLES `order_list` WRITE;
/*!40000 ALTER TABLE `order_list` DISABLE KEYS */;
/*!40000 ALTER TABLE `order_list` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payment`
--

DROP TABLE IF EXISTS `payment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `payment` (
  `payment_id` int(11) NOT NULL,
  `bill_id` int(11) NOT NULL,
  `kot_bot` tinyint(3) unsigned NOT NULL,
  `payment_type` char(20) NOT NULL,
  `payment_date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment`
--

LOCK TABLES `payment` WRITE;
/*!40000 ALTER TABLE `payment` DISABLE KEYS */;
/*!40000 ALTER TABLE `payment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pointofsale`
--

DROP TABLE IF EXISTS `pointofsale`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pointofsale` (
  `item` char(20) DEFAULT NULL,
  `price` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pointofsale`
--

LOCK TABLES `pointofsale` WRITE;
/*!40000 ALTER TABLE `pointofsale` DISABLE KEYS */;
/*!40000 ALTER TABLE `pointofsale` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `purchase`
--

DROP TABLE IF EXISTS `purchase`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `purchase` (
  `purchase_id` int(11) NOT NULL AUTO_INCREMENT,
  `item_id` int(11) NOT NULL,
  `unit_id` int(11) NOT NULL,
  `buy_rate` decimal(19,4) NOT NULL,
  `quantity` double NOT NULL,
  `total_amount` decimal(19,4) NOT NULL,
  `purchase_date` datetime NOT NULL,
  `distributor_id` int(11) NOT NULL,
  PRIMARY KEY (`purchase_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `purchase`
--

LOCK TABLES `purchase` WRITE;
/*!40000 ALTER TABLE `purchase` DISABLE KEYS */;
INSERT INTO `purchase` VALUES (1,1,1,100.0000,100,10000.0000,'2014-04-21 20:15:15',1);
/*!40000 ALTER TABLE `purchase` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `purchase_return`
--

DROP TABLE IF EXISTS `purchase_return`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `purchase_return` (
  `purchase_return_id` int(11) NOT NULL AUTO_INCREMENT,
  `purchase_id` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  `return_reason` longtext,
  `distributor_id` int(11) DEFAULT NULL,
  `unit_id` int(11) NOT NULL,
  `return_date` date DEFAULT NULL,
  `amount` decimal(19,4) DEFAULT NULL,
  PRIMARY KEY (`purchase_return_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `purchase_return`
--

LOCK TABLES `purchase_return` WRITE;
/*!40000 ALTER TABLE `purchase_return` DISABLE KEYS */;
/*!40000 ALTER TABLE `purchase_return` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sales_item`
--

DROP TABLE IF EXISTS `sales_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sales_item` (
  `sales_id` int(11) NOT NULL,
  `item_id` int(11) NOT NULL,
  `item_name` varchar(100) NOT NULL,
  `category_id` int(11) NOT NULL,
  `item_unit` int(11) NOT NULL,
  `item_sale_rate` decimal(19,4) NOT NULL,
  `customer_id` int(11) DEFAULT NULL,
  `item_sub_total` decimal(19,4) NOT NULL,
  `item_discount` decimal(19,4) DEFAULT NULL,
  `item_total` decimal(19,4) NOT NULL,
  `item_sale_time` datetime NOT NULL,
  `kot_bot` tinyint(3) unsigned NOT NULL,
  `table_id` int(11) NOT NULL,
  `order_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sales_item`
--

LOCK TABLES `sales_item` WRITE;
/*!40000 ALTER TABLE `sales_item` DISABLE KEYS */;
/*!40000 ALTER TABLE `sales_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_menu_info`
--

DROP TABLE IF EXISTS `sys_menu_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_menu_info` (
  `jmenu_id` int(11) NOT NULL,
  `jmenu_name` varchar(50) NOT NULL,
  PRIMARY KEY (`jmenu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_menu_info`
--

LOCK TABLES `sys_menu_info` WRITE;
/*!40000 ALTER TABLE `sys_menu_info` DISABLE KEYS */;
INSERT INTO `sys_menu_info` VALUES (1,'Company Setup'),(2,'CenterStore Setup'),(3,'CenterStore'),(4,'Terminal Setup'),(5,'Terminal'),(6,'CenterStore Report'),(7,'Terminal Report'),(8,'System Date'),(9,'System Config');
/*!40000 ALTER TABLE `sys_menu_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_menubar_setup`
--

DROP TABLE IF EXISTS `sys_menubar_setup`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_menubar_setup` (
  `UserId` int(11) NOT NULL,
  `1` tinyint(1) DEFAULT NULL,
  `2` tinyint(1) DEFAULT NULL,
  `3` tinyint(1) DEFAULT NULL,
  `4` tinyint(1) DEFAULT NULL,
  `5` tinyint(1) DEFAULT NULL,
  `6` tinyint(1) DEFAULT NULL,
  `7` tinyint(1) DEFAULT NULL,
  `8` tinyint(1) DEFAULT '0',
  `9` tinyint(1) DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_menubar_setup`
--

LOCK TABLES `sys_menubar_setup` WRITE;
/*!40000 ALTER TABLE `sys_menubar_setup` DISABLE KEYS */;
INSERT INTO `sys_menubar_setup` VALUES (1,1,1,1,1,1,1,1,1,1),(4,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0),(5,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0),(6,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0),(3,0,0,0,1,1,0,0,0,0),(10,1,0,0,1,1,0,1,1,0),(11,0,0,0,0,0,0,0,0,0),(14,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0),(8,1,1,1,1,1,1,1,1,1),(15,1,1,1,0,1,1,1,0,0),(16,0,1,1,0,0,1,0,0,0),(17,0,1,1,0,0,1,0,0,0);
/*!40000 ALTER TABLE `sys_menubar_setup` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_menuitem_info`
--

DROP TABLE IF EXISTS `sys_menuitem_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_menuitem_info` (
  `menuitem_id` int(11) NOT NULL AUTO_INCREMENT,
  `menuitem_name` varchar(50) NOT NULL,
  `menuitem_actioncommand` varchar(50) DEFAULT NULL,
  `menu_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`menuitem_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_menuitem_info`
--

LOCK TABLES `sys_menuitem_info` WRITE;
/*!40000 ALTER TABLE `sys_menuitem_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_menuitem_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_user_info`
--

DROP TABLE IF EXISTS `sys_user_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_user_info` (
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `userid` int(11) NOT NULL AUTO_INCREMENT,
  `admin` tinyint(1) NOT NULL DEFAULT '0',
  `super_admin` tinyint(1) NOT NULL DEFAULT '0',
  `user_info` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user_info`
--

LOCK TABLES `sys_user_info` WRITE;
/*!40000 ALTER TABLE `sys_user_info` DISABLE KEYS */;
INSERT INTO `sys_user_info` VALUES ('admin','28Xm3ZNKtzDPhelgAxL+iA==',1,1,0,NULL);
/*!40000 ALTER TABLE `sys_user_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sysdiagrams`
--

DROP TABLE IF EXISTS `sysdiagrams`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sysdiagrams` (
  `name` varchar(160) NOT NULL,
  `principal_id` int(11) NOT NULL,
  `diagram_id` int(11) NOT NULL AUTO_INCREMENT,
  `version` int(11) DEFAULT NULL,
  `definition` longblob,
  PRIMARY KEY (`diagram_id`),
  UNIQUE KEY `UK_principal_name` (`principal_id`,`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sysdiagrams`
--

LOCK TABLES `sysdiagrams` WRITE;
/*!40000 ALTER TABLE `sysdiagrams` DISABLE KEYS */;
/*!40000 ALTER TABLE `sysdiagrams` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `system_date`
--

DROP TABLE IF EXISTS `system_date`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `system_date` (
  `system_date_id` int(11) NOT NULL AUTO_INCREMENT,
  `date` date NOT NULL,
  `open_status` bit(1) NOT NULL DEFAULT b'0',
  `close_status` bit(1) DEFAULT b'0',
  PRIMARY KEY (`system_date_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `system_date`
--

LOCK TABLES `system_date` WRITE;
/*!40000 ALTER TABLE `system_date` DISABLE KEYS */;
/*!40000 ALTER TABLE `system_date` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `table_info`
--

DROP TABLE IF EXISTS `table_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `table_info` (
  `table_id` int(11) NOT NULL AUTO_INCREMENT,
  `table_name` varchar(100) NOT NULL,
  `parent_tablegroup_id` int(11) NOT NULL,
  `table_status` tinyint(1) DEFAULT '0',
  `table_availability` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`table_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `table_info`
--

LOCK TABLES `table_info` WRITE;
/*!40000 ALTER TABLE `table_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `table_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tablegroup`
--

DROP TABLE IF EXISTS `tablegroup`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tablegroup` (
  `tablegroup_id` int(11) NOT NULL AUTO_INCREMENT,
  `tablegroup_name` varchar(100) NOT NULL,
  `tablegroup_rate` decimal(19,4) DEFAULT NULL,
  PRIMARY KEY (`tablegroup_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tablegroup`
--

LOCK TABLES `tablegroup` WRITE;
/*!40000 ALTER TABLE `tablegroup` DISABLE KEYS */;
/*!40000 ALTER TABLE `tablegroup` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `temp_order_table`
--

DROP TABLE IF EXISTS `temp_order_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `temp_order_table` (
  `order_id` int(11) NOT NULL,
  `table_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `temp_order_table`
--

LOCK TABLES `temp_order_table` WRITE;
/*!40000 ALTER TABLE `temp_order_table` DISABLE KEYS */;
/*!40000 ALTER TABLE `temp_order_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `waiter_info`
--

DROP TABLE IF EXISTS `waiter_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `waiter_info` (
  `waiter_id` int(11) NOT NULL AUTO_INCREMENT,
  `waiter_name` varchar(100) NOT NULL,
  `waiter_phone` varchar(50) DEFAULT NULL,
  `waiter_address` varchar(200) DEFAULT NULL,
  `designation_id` int(11) NOT NULL,
  PRIMARY KEY (`waiter_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `waiter_info`
--

LOCK TABLES `waiter_info` WRITE;
/*!40000 ALTER TABLE `waiter_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `waiter_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wastage`
--

DROP TABLE IF EXISTS `wastage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wastage` (
  `wastage_id` int(11) NOT NULL AUTO_INCREMENT,
  `id` int(11) NOT NULL,
  `quantity` double NOT NULL,
  `amount` double NOT NULL,
  `reason` text NOT NULL,
  `staff_id` int(11) DEFAULT NULL,
  `department_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `date` date NOT NULL,
  `menu_type_flag` tinyint(4) NOT NULL,
  `unit_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`wastage_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wastage`
--

LOCK TABLES `wastage` WRITE;
/*!40000 ALTER TABLE `wastage` DISABLE KEYS */;
/*!40000 ALTER TABLE `wastage` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-04-28 17:55:09
