-- MySQL dump 10.13  Distrib 8.0.12, for Win64 (x86_64)
--
-- Host: localhost    Database: bancosecsaude
-- ------------------------------------------------------
-- Server version	8.0.12

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `tbl_animais`
--

DROP TABLE IF EXISTS `tbl_animais`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tbl_animais` (
  `idanimais` int(11) DEFAULT NULL,
  `especie` varchar(80) DEFAULT NULL,
  `quantidade` varchar(45) DEFAULT NULL,
  `dataanimais` date DEFAULT NULL,
  KEY `idanimais` (`idanimais`),
  CONSTRAINT `idanimais` FOREIGN KEY (`idanimais`) REFERENCES `tbl_pessoas` (`idpessoas`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_animais`
--

LOCK TABLES `tbl_animais` WRITE;
/*!40000 ALTER TABLE `tbl_animais` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_animais` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_perguntas`
--

DROP TABLE IF EXISTS `tbl_perguntas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tbl_perguntas` (
  `idperguntas` int(11) DEFAULT NULL,
  `cisterna` tinyint(1) DEFAULT NULL,
  `cisternaconsumo` tinyint(1) DEFAULT NULL,
  `cxdagua` tinyint(1) DEFAULT NULL,
  `tampada` tinyint(1) DEFAULT NULL,
  `pcartesiano` tinyint(1) DEFAULT NULL,
  `pococonsumo` tinyint(1) DEFAULT NULL,
  `fseptica` tinyint(1) DEFAULT NULL,
  `animais` tinyint(1) DEFAULT NULL,
  `capacidade` int(11) DEFAULT NULL,
  `datapergunta` date NOT NULL,
  PRIMARY KEY (`datapergunta`),
  KEY `idperguntas` (`idperguntas`),
  CONSTRAINT `idperguntas` FOREIGN KEY (`idperguntas`) REFERENCES `tbl_pessoas` (`idpessoas`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_perguntas`
--

LOCK TABLES `tbl_perguntas` WRITE;
/*!40000 ALTER TABLE `tbl_perguntas` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_perguntas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_pessoas`
--

DROP TABLE IF EXISTS `tbl_pessoas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tbl_pessoas` (
  `idpessoas` int(11) NOT NULL AUTO_INCREMENT,
  `numero` varchar(80) DEFAULT NULL,
  `nome` varchar(80) DEFAULT NULL,
  `endereco` varchar(80) DEFAULT NULL,
  `bairro` varchar(80) DEFAULT NULL,
  `cidade` varchar(80) DEFAULT NULL,
  `regiao` varchar(80) DEFAULT NULL,
  PRIMARY KEY (`idpessoas`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_pessoas`
--

LOCK TABLES `tbl_pessoas` WRITE;
/*!40000 ALTER TABLE `tbl_pessoas` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_pessoas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'bancosecsaude'
--

--
-- Dumping routines for database 'bancosecsaude'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-02-19 19:06:02
