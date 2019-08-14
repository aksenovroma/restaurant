-- MySQL dump 10.13  Distrib 5.7.27, for Linux (x86_64)
--
-- Host: 127.0.0.1    Database: restaurant
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
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dish`
--

LOCK TABLES `dish` WRITE;
/*!40000 ALTER TABLE `dish` DISABLE KEYS */;
INSERT INTO `dish` VALUES (15,'Морской сет',11,670,'https://avatanplus.com/files/resources/original/575c3d75994521554050137c.png','Тарелка свежих морепродуктов'),(16,'Мясной сет',10.5,700,'http://mixcaffe.su/img/fotki/6.png','Мясо, приготовленное на углях'),(17,'Фасоль с грибами',4,320,'https://fasol.tv/upload/iblock/571/571a203a7d50e585b165a07bad5a5310.png','Обжареные фасоль с шампиньонами'),(18,'Окрошка',3.5,350,'http://kurochka-r.ru/upload/resize_cache/iblock/6d2/450_450_1/6d2d2dacadba339b5121c254d74c4529.png','Свежая окрошка на кефире'),(19,'Куриный бульон',2.5,300,'http://kurochka-r.ru/upload/resize_cache/iblock/b88/450_450_1/b88feef7bfdf47928c37f93e8e9846ad.png','Куриный бульон с яйцом и зеленью'),(20,'Солянка',3.2,250,'http://kurochka-r.ru/upload/resize_cache/iblock/a5b/450_450_1/a5b595facf661119d8897f84ce75e46a.png','С маслинами и сметаной'),(21,'Овощной суп',2.7,320,'http://www.karotes.lv/content/img/zupas/__500/vistas_galas_zupa.png','С пастой и рисом'),(22,'Овощное ассорти',2.4,300,'https://vivaitalia.com.ua/wp-content/uploads/2017/02/92854-405-336-s_2.png','Помидоры, перец, огурцы и зелень'),(24,'Чизкейк',2.4,100,'https://img.pngio.com/gluten-free-wow-factor-desserts-png-desserts-900_900.png','С лесными ягодами'),(25,'Торт \"Клипарт\"',2.6,110,'https://i-love-png.com/images/adj_7_gfchocalmtortebabycake.png','С шоколадным кремом'),(27,'Кекс',2,80,'https://cdn.pixabay.com/photo/2017/09/07/11/25/cupcake-2724786_960_720.png','С заварным кремом'),(28,'Суфле',2.5,120,'https://nicolukas.com/media/catalog/product/cache/1/image/9df78eab33525d08d6e5fb8d27136e95/m/i/mini_agraz_1.png','С малиной и фруктовым желе'),(29,'Морс',1.5,300,'http://burgervsem.ru/media/plg_system_vmmagiczoom/magictoolbox_cache/80543c918ed05ba09145ba56f4b3651f/8/2/823/thumb500x500/3671620082/mors1.png','Из натуральной клюквы'),(30,'Сок \"Мультифрукт\"',2,300,'http://www.dobry.ru/assets-new/images/products/multifruit/image-2.png','С фруктовой мякотью');
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
INSERT INTO `dish_category` VALUES (15,'appetizer'),(16,'appetizer'),(17,'appetizer'),(18,'soup'),(19,'soup'),(20,'soup'),(21,'soup'),(22,'appetizer'),(24,'dessert'),(25,'dessert'),(27,'dessert'),(28,'dessert'),(29,'drink'),(30,'drink');
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
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order`
--

LOCK TABLES `order` WRITE;
/*!40000 ALTER TABLE `order` DISABLE KEYS */;
INSERT INTO `order` VALUES (43,13,10,16.5,450,'Sun Aug 04 01:23:16 MSK 2019','Сурганова 37 к 2'),(44,13,11,21.5,1280,'Sun Aug 04 01:23:35 MSK 2019','Мира 15'),(45,15,11,11.5,700,'Sun Aug 04 01:24:34 MSK 2019','Василевцы 13'),(46,13,11,9.5,200,'Sun Aug 04 19:22:17 MSK 2019','Мира 16'),(47,15,1,23.7,1730,'Wed Aug 14 12:24:43 MSK 2019','ул Василевцы 12 кв 34'),(48,21,1,17.1,1130,'Wed Aug 14 12:33:14 MSK 2019','ул Еронько 11 кв 63'),(49,21,1,4.9,420,'Wed Aug 14 12:33:56 MSK 2019','ул Еронько 11 кв 63');
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
INSERT INTO `order_detail` VALUES (47,16),(47,17),(47,20),(47,27),(47,27),(47,30),(48,16),(48,17),(48,25),(49,22),(49,28);
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
INSERT INTO `order_state` VALUES (43,'accepted'),(44,'accepted'),(45,'accepted'),(46,'accepted'),(47,'not_accepted'),(48,'not_accepted'),(49,'not_accepted');
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
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'empty','empty','empty','empty'),(2,'admin','admin','admin','https://www.multipeers.itpeers.com/wp-content/uploads/2017/02/icone-2.png'),(10,'Миша','misha@mail.ru','ytrewq','link5'),(11,'Рома','romashka@mail.ru','qwerty','https://www.greenmountainenergy.com/wp-content/uploads/2017/05/my-account-nav-cta@2x.png'),(12,'Дима','dima@mail.ru','123','https://www.greenmountainenergy.com/wp-content/uploads/2017/05/my-account-nav-cta@2x.png'),(13,'Маша','ria@mail.ru','123','https://www.greenmountainenergy.com/wp-content/uploads/2017/05/my-account-nav-cta@2x.png'),(14,'Хрысціна','hris@mail.ru','123','https://www.greenmountainenergy.com/wp-content/uploads/2017/05/my-account-nav-cta@2x.png'),(15,'Андрей','dron@mail.ru','123','https://www.greenmountainenergy.com/wp-content/uploads/2017/05/my-account-nav-cta@2x.png'),(17,'Пётр','petr@mail.ru','111','https://www.greenmountainenergy.com/wp-content/uploads/2017/05/my-account-nav-cta@2x.png'),(20,'Oleg','oleg@gmail.com','oleg','https://www.greenmountainenergy.com/wp-content/uploads/2017/05/my-account-nav-cta@2x.png'),(21,'Галя','galya@mail.by','qwerty','https://www.greenmountainenergy.com/wp-content/uploads/2017/05/my-account-nav-cta@2x.png');
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
INSERT INTO `user_role` VALUES (1,'courier'),(2,'admin'),(10,'courier'),(11,'courier'),(12,'client'),(13,'client'),(14,'admin'),(15,'client'),(17,'client'),(20,'client'),(21,'client');
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

-- Dump completed on 2019-08-14 17:49:38
