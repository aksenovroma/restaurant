-- MySQL dump 10.13  Distrib 5.7.27, for Linux (x86_64)
--
-- Host: localhost    Database: restaurant
-- ------------------------------------------------------
-- Server version	5.7.27-0ubuntu0.19.04.1

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
-- Table structure for table `dish`
--

DROP TABLE IF EXISTS `dish`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dish` (
  `iddish` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `price` double NOT NULL,
  `weight` double NOT NULL,
  `photo` varchar(255) NOT NULL,
  `description` text NOT NULL,
  PRIMARY KEY (`iddish`),
  UNIQUE KEY `dish_name_uindex` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dish`
--

LOCK TABLES `dish` WRITE;
/*!40000 ALTER TABLE `dish` DISABLE KEYS */;
INSERT INTO `dish` VALUES (5,'Чизболлы',7,250,'https://www.menu.by/resources/default/img/restaurant_products/small/1537278003-6014.jpeg','сырно-ветчинные шарики в панировке'),(6,'Моцарелла фри',9.5,200,'https://www.menu.by/resources/default/img/restaurant_products/small/1537278199-1376.jpeg','палочки Моцареллы'),(7,'Салат Венский',6,260,'https://www.menu.by/resources/default/img/restaurant_products/small/1537278573-4237.jpeg','салат из картофеля с сосисками и зеленью'),(8,'Суп с венскими колбасками\n',6,260,'https://www.menu.by/resources/default/img/restaurant_products/small/1537278729-0114.jpeg','сытный суп с кусочками венских колбасок'),(9,'Мини штрудели',8,115,'https://www.menu.by/resources/default/img/restaurant_products/small/1537281466-3874.jpeg','жареные во фритюре минипирожки'),(10,'Торт Шварцвальд\n',6,170,'https://www.menu.by/resources/default/img/restaurant_products/small/1537281610-1126.jpeg','шоколадный торт с вишневым ликером'),(11,'Кока-кола',2,500,'https://edaedet.ru/upload/iblock/29e/29ea7fc0e3d1bb35ca90016040ede84e.jpg','прохладный напиток'),(14,'Морс',3.5,500,'https://teremok.ru/upload/iblock/e87/c3a61684-bb10-11e8-af7c-001517db825c.png','из свежей клюквы');
/*!40000 ALTER TABLE `dish` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dish_category`
--

DROP TABLE IF EXISTS `dish_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dish_category` (
  `iddish` int(11) NOT NULL,
  `category` varchar(45) NOT NULL,
  PRIMARY KEY (`iddish`),
  CONSTRAINT `fk_dish_category_1` FOREIGN KEY (`iddish`) REFERENCES `dish` (`iddish`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dish_category`
--

LOCK TABLES `dish_category` WRITE;
/*!40000 ALTER TABLE `dish_category` DISABLE KEYS */;
INSERT INTO `dish_category` VALUES (5,'appetizer'),(6,'appetizer'),(7,'appetizer'),(8,'soup'),(9,'dessert'),(10,'dessert'),(11,'drink'),(14,'drink');
/*!40000 ALTER TABLE `dish_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order`
--

DROP TABLE IF EXISTS `order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order` (
  `idorder` int(11) NOT NULL AUTO_INCREMENT,
  `idclient` int(11) NOT NULL,
  `idcourier` int(11) NOT NULL,
  `totalprice` double NOT NULL,
  `totalweight` double NOT NULL,
  `time` varchar(45) NOT NULL,
  `address` varchar(255) NOT NULL,
  PRIMARY KEY (`idorder`),
  KEY `fk_order_1_idx` (`idclient`),
  KEY `fk_order_2_idx` (`idcourier`),
  CONSTRAINT `fk_order_1` FOREIGN KEY (`idclient`) REFERENCES `user` (`iduser`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_order_2` FOREIGN KEY (`idcourier`) REFERENCES `user` (`iduser`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order`
--

LOCK TABLES `order` WRITE;
/*!40000 ALTER TABLE `order` DISABLE KEYS */;
INSERT INTO `order` VALUES (43,13,10,16.5,450,'Sun Aug 04 01:23:16 MSK 2019','Сурганова 37 к 2'),(44,13,11,21.5,1280,'Sun Aug 04 01:23:35 MSK 2019','Мира 15'),(45,15,11,11.5,700,'Sun Aug 04 01:24:34 MSK 2019','Василевцы 13'),(46,13,11,9.5,200,'Sun Aug 04 19:22:17 MSK 2019','Мира 16');
/*!40000 ALTER TABLE `order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_detail`
--

DROP TABLE IF EXISTS `order_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order_detail` (
  `idorder` int(11) NOT NULL,
  `iddish` int(11) NOT NULL,
  KEY `fk_order_detail_1_idx` (`idorder`),
  KEY `fk_order_detail_2_idx` (`iddish`),
  CONSTRAINT `fk_order_detail_1` FOREIGN KEY (`idorder`) REFERENCES `order` (`idorder`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_order_detail_2` FOREIGN KEY (`iddish`) REFERENCES `dish` (`iddish`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_detail`
--

LOCK TABLES `order_detail` WRITE;
/*!40000 ALTER TABLE `order_detail` DISABLE KEYS */;
INSERT INTO `order_detail` VALUES (43,5),(43,6),(44,7),(44,7),(44,7),(44,14),(45,6),(45,11),(46,6);
/*!40000 ALTER TABLE `order_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_state`
--

DROP TABLE IF EXISTS `order_state`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order_state` (
  `idorder` int(11) NOT NULL,
  `state` varchar(45) NOT NULL,
  PRIMARY KEY (`idorder`),
  CONSTRAINT `fk_order_status_1` FOREIGN KEY (`idorder`) REFERENCES `order` (`idorder`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_state`
--

LOCK TABLES `order_state` WRITE;
/*!40000 ALTER TABLE `order_state` DISABLE KEYS */;
INSERT INTO `order_state` VALUES (43,'accepted'),(44,'accepted'),(45,'accepted'),(46,'accepted');
/*!40000 ALTER TABLE `order_state` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `iduser` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `login` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `photo` text NOT NULL,
  PRIMARY KEY (`iduser`),
  UNIQUE KEY `login_UNIQUE` (`login`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'empty','empty','empty','empty'),(2,'admin','admin','admin','https://www.multipeers.itpeers.com/wp-content/uploads/2017/02/icone-2.png'),(10,'Миша','misha@mail.ru','ytrewq','link5'),(11,'Рома','romashka@mail.ru','qwerty','https://www.greenmountainenergy.com/wp-content/uploads/2017/05/my-account-nav-cta@2x.png'),(12,'Дима','dima@mail.ru','123','https://www.greenmountainenergy.com/wp-content/uploads/2017/05/my-account-nav-cta@2x.png'),(13,'Маша','ria@mail.ru','123','https://www.greenmountainenergy.com/wp-content/uploads/2017/05/my-account-nav-cta@2x.png'),(14,'Хрысціна','hris@mail.ru','123','https://www.greenmountainenergy.com/wp-content/uploads/2017/05/my-account-nav-cta@2x.png'),(15,'Андрей','dron@mail.ru','123','https://www.greenmountainenergy.com/wp-content/uploads/2017/05/my-account-nav-cta@2x.png'),(16,'Рома','romochka-aksenov2014@mail.ru','1234567890йцукен','https://www.greenmountainenergy.com/wp-content/uploads/2017/05/my-account-nav-cta@2x.png'),(17,'Пётр','petr@mail.ru','111','https://www.greenmountainenergy.com/wp-content/uploads/2017/05/my-account-nav-cta@2x.png');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_role` (
  `iduser` int(11) NOT NULL,
  `role` varchar(45) NOT NULL,
  PRIMARY KEY (`iduser`),
  CONSTRAINT `fk_user_role_1` FOREIGN KEY (`iduser`) REFERENCES `user` (`iduser`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES (1,'courier'),(2,'admin'),(10,'courier'),(11,'courier'),(12,'client'),(13,'client'),(14,'admin'),(15,'client'),(16,'client'),(17,'client');
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-08-13 12:05:35
