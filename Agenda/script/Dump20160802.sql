CREATE DATABASE  IF NOT EXISTS `agenda` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `agenda`;
-- MySQL dump 10.13  Distrib 5.6.17, for Win32 (x86)
--
-- Host: localhost    Database: agenda
-- ------------------------------------------------------
-- Server version	5.6.19

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
-- Table structure for table `agenda`
--

DROP TABLE IF EXISTS `agenda`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `agenda` (
  `id` int(11) NOT NULL,
  `ds_agenda` varchar(255) DEFAULT NULL,
  `dt_inicial` date DEFAULT NULL,
  `hora_final` time DEFAULT NULL,
  `hora_inicial` time DEFAULT NULL,
  `titulo` varchar(255) DEFAULT NULL,
  `id_instituicao` int(11) DEFAULT NULL,
  `id_local` int(11) DEFAULT NULL,
  `id_setor` int(11) DEFAULT NULL,
  `id_usuario` int(11) DEFAULT NULL,
  `dt_marcacao` datetime DEFAULT NULL,
  `sn_concluido` bit(1) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKhnd3voog5v4jasmobo3m7eehr` (`id_instituicao`),
  KEY `FK3h3ualhw5itdp7iwa73efew7k` (`id_local`),
  KEY `FKafpjncwccu8603nkoo4kmlyxv` (`id_setor`),
  KEY `FKcavn89gam289tegyp711ranxw` (`id_usuario`),
  CONSTRAINT `FK3h3ualhw5itdp7iwa73efew7k` FOREIGN KEY (`id_local`) REFERENCES `local` (`id`),
  CONSTRAINT `FKafpjncwccu8603nkoo4kmlyxv` FOREIGN KEY (`id_setor`) REFERENCES `setor` (`id_setor`),
  CONSTRAINT `FKcavn89gam289tegyp711ranxw` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id`),
  CONSTRAINT `FKhnd3voog5v4jasmobo3m7eehr` FOREIGN KEY (`id_instituicao`) REFERENCES `instituicao` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `agenda_setores`
--

DROP TABLE IF EXISTS `agenda_setores`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `agenda_setores` (
  `id_agenda` int(11) NOT NULL,
  `id_setor` int(11) NOT NULL,
  KEY `FKnibjshcpdsnh9nacreyqhburs` (`id_setor`),
  KEY `FK5i84t0tuhqllcuniomkc9wtep` (`id_agenda`),
  CONSTRAINT `FK5i84t0tuhqllcuniomkc9wtep` FOREIGN KEY (`id_agenda`) REFERENCES `agenda` (`id`),
  CONSTRAINT `FKnibjshcpdsnh9nacreyqhburs` FOREIGN KEY (`id_setor`) REFERENCES `setor` (`id_setor`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `agenda_usuario`
--

DROP TABLE IF EXISTS `agenda_usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `agenda_usuario` (
  `id` int(11) NOT NULL,
  `sn_confirmado` bit(1) NOT NULL,
  `sn_participante` bit(1) NOT NULL,
  `id_agenda` int(11) DEFAULT NULL,
  `id_usuario` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK8l776eeftr57k9rut6yr5u0uu` (`id_agenda`),
  KEY `FKbjv7gqwmspcpheviiqjuu87uk` (`id_usuario`),
  CONSTRAINT `FK8l776eeftr57k9rut6yr5u0uu` FOREIGN KEY (`id_agenda`) REFERENCES `agenda` (`id`),
  CONSTRAINT `FKbjv7gqwmspcpheviiqjuu87uk` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `instituicao`
--

DROP TABLE IF EXISTS `instituicao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `instituicao` (
  `id` int(11) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `nome` varchar(100) NOT NULL,
  `sn_ativo` bit(1) NOT NULL,
  `endereco` varchar(255) DEFAULT NULL,
  `responsavel` varchar(255) DEFAULT NULL,
  `telefone` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `local`
--

DROP TABLE IF EXISTS `local`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `local` (
  `id` int(11) NOT NULL,
  `ds_local` varchar(255) DEFAULT NULL,
  `dt_criacao` datetime DEFAULT NULL,
  `dt_inativacao` datetime DEFAULT NULL,
  `sn_ativo` bit(1) NOT NULL,
  `id_instituicao` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKl9c4ydx0srmqhg3f1o3rsunbb` (`id_instituicao`),
  CONSTRAINT `FKl9c4ydx0srmqhg3f1o3rsunbb` FOREIGN KEY (`id_instituicao`) REFERENCES `instituicao` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `setor`
--

DROP TABLE IF EXISTS `setor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `setor` (
  `id_setor` int(11) NOT NULL,
  `nm_setor` varchar(60) NOT NULL,
  `sn_ativo` bit(1) NOT NULL,
  `id_instituicao` int(11) DEFAULT NULL,
  `id_usuario_responsavel` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_setor`),
  KEY `FKf96lntcpes0hp3wnj2uu2ryai` (`id_instituicao`),
  KEY `FK1ehdttqjep8ig5tolcc3x3gd2` (`id_usuario_responsavel`),
  CONSTRAINT `FK1ehdttqjep8ig5tolcc3x3gd2` FOREIGN KEY (`id_usuario_responsavel`) REFERENCES `usuario` (`id`),
  CONSTRAINT `FKf96lntcpes0hp3wnj2uu2ryai` FOREIGN KEY (`id_instituicao`) REFERENCES `instituicao` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tempo`
--

DROP TABLE IF EXISTS `tempo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tempo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `hora_minuto` varchar(5) DEFAULT NULL,
  `tempo` time DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario` (
  `id` int(11) NOT NULL,
  `dt_nascimento` date DEFAULT NULL,
  `login` varchar(32) NOT NULL,
  `nome` varchar(100) NOT NULL,
  `senha` varchar(512) NOT NULL,
  `id_instituicao` int(11) DEFAULT NULL,
  `id_setor` int(11) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `telefone` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKfunc28qbqbgw2jmw3unxwlhsa` (`id_instituicao`),
  KEY `FKb5hua6r9pg7ijx76tnckw0dvl` (`id_setor`),
  CONSTRAINT `FKb5hua6r9pg7ijx76tnckw0dvl` FOREIGN KEY (`id_setor`) REFERENCES `setor` (`id_setor`),
  CONSTRAINT `FKfunc28qbqbgw2jmw3unxwlhsa` FOREIGN KEY (`id_instituicao`) REFERENCES `instituicao` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `trg_crip_nova_senha` BEFORE INSERT ON `usuario` FOR EACH ROW
	SET NEW.senha = SHA2(new.senha,512); */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `atualiza_usuario` BEFORE UPDATE ON `usuario` FOR EACH ROW
	IF( NEW.SENHA != OLD.SENHA )
	THEN 
		SET NEW.senha = SHA2(new.senha,512);
	END IF */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Dumping events for database 'agenda'
--

--
-- Dumping routines for database 'agenda'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-08-02 23:12:40
