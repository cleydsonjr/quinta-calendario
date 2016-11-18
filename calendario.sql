CREATE DATABASE  IF NOT EXISTS `calendario` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `calendario`;
-- MySQL dump 10.13  Distrib 5.7.16, for Linux (x86_64)
--
-- Host: localhost    Database: calendario
-- ------------------------------------------------------
-- Server version	5.7.13-0ubuntu0.16.04.2

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
-- Table structure for table `eventos`
--

DROP TABLE IF EXISTS `eventos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `eventos` (
  `id_eventos` int(11) NOT NULL,
  `nome_evento` varchar(45) NOT NULL,
  `id_institutos` int(11) NOT NULL,
  `id_regionais` int(11) NOT NULL,
  `data_evento` varchar(15) NOT NULL,
  `data_evt_cadastrado` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_eventos`),
  KEY `fk_eventos_1_idx` (`id_institutos`),
  KEY `fk_eventos_2_idx` (`id_regionais`),
  CONSTRAINT `fk_eventos_1` FOREIGN KEY (`id_institutos`) REFERENCES `institutos` (`id_institutos`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_eventos_2` FOREIGN KEY (`id_regionais`) REFERENCES `regionais` (`id_regionais`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `eventos`
--

LOCK TABLES `eventos` WRITE;
/*!40000 ALTER TABLE `eventos` DISABLE KEYS */;
/*!40000 ALTER TABLE `eventos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `institutos`
--

DROP TABLE IF EXISTS `institutos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `institutos` (
  `id_institutos` int(11) NOT NULL,
  `nome_regional` varchar(20) DEFAULT NULL,
  `id_regionais` int(11) NOT NULL,
  PRIMARY KEY (`id_institutos`),
  KEY `fk_institutos_1_idx` (`id_regionais`),
  CONSTRAINT `fk_institutos_1` FOREIGN KEY (`id_regionais`) REFERENCES `regionais` (`id_regionais`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `institutos`
--

LOCK TABLES `institutos` WRITE;
/*!40000 ALTER TABLE `institutos` DISABLE KEYS */;
/*!40000 ALTER TABLE `institutos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `regionais`
--

DROP TABLE IF EXISTS `regionais`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `regionais` (
  `id_regionais` int(11) NOT NULL,
  `nome_regional` varchar(20) NOT NULL,
  PRIMARY KEY (`id_regionais`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `regionais`
--

LOCK TABLES `regionais` WRITE;
/*!40000 ALTER TABLE `regionais` DISABLE KEYS */;
/*!40000 ALTER TABLE `regionais` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-11-18 17:54:27
