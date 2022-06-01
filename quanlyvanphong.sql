CREATE DATABASE  IF NOT EXISTS `quanlyvanphong` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `quanlyvanphong`;
-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: localhost    Database: quanlyvanphong
-- ------------------------------------------------------
-- Server version	8.0.28

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `congty`
--

DROP TABLE IF EXISTS `congty`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `congty` (
  `id` int NOT NULL AUTO_INCREMENT,
  `ma` varchar(100) NOT NULL,
  `ten` varchar(100) NOT NULL,
  `linhvuchoatdong` varchar(100) NOT NULL,
  `diachitrongtoanha` varchar(20) NOT NULL,
  `sdt` varchar(10) NOT NULL,
  `dientichthue` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `congty`
--

LOCK TABLES `congty` WRITE;
/*!40000 ALTER TABLE `congty` DISABLE KEYS */;
INSERT INTO `congty` VALUES (1,'KwangYang','Cty TNHH công nghiệp Kwang Yang','Xây dựng và vật liệu','Tầng 1 bên trái','0902295126',10),(2,'Leakless','TNHH Việt Nam Leakless','Xe Máy - Phụ Tùng','Tầng 2 bên trái','0902775127',15),(3,'Honest','TNHH Honest Việt Nam','Ô Tô-Sản Xuất & Bán Buôn Phụ Tùng','Tầng 3 bên trái','0572295128',20),(4,'Kishiro','TNHH Kishiro','Kính','Tầng 4 bên trái','0902256129',25),(5,'Summit','Summit Auto Allicance Hà Nội','Xe Máy - Phụ Tùng','Tầng 5 bên trái','0902296396',30),(6,'Toyoda','TNHH Toyoda Giken Việt Nam','Phụ tùng ô tô, xe máy nổi tiếng như Honda, Yamaha, Toyota, NISSAN.........','Tầng 1 bên phải','0902295788',10),(7,'Rhythm','TNHH Rhythm Precision Việt Nam','Khuôn Mẫu','Tầng 2 bên phải','0902295132',15),(8,'GokoSpring','TNHH Goko Spring Việt Nam','Lò Xo-Vòng , Dẹt…','Tầng 3 bên phải','0902295133',20),(9,'Yamazaki','TNHH Kỹ Thuật Yamazaki Việt Nam','Xe Máy-Sản Xuất & Bán Buôn Phụ Tùng','Tầng 4 bên phải','0902295134',25),(10,'Sakura','TNHH Broad Bright Sakura Việt Nam','Xe Máy-Sản Xuất & Bán Buôn Phụ Tùng','Tầng 5 bên phải','0902295135',30);
/*!40000 ALTER TABLE `congty` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dichvu`
--

DROP TABLE IF EXISTS `dichvu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dichvu` (
  `id` int NOT NULL AUTO_INCREMENT,
  `ma` varchar(100) NOT NULL,
  `ten` varchar(100) NOT NULL,
  `loaidichvu` varchar(100) NOT NULL,
  `dongia` double NOT NULL,
  `mota` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dichvu`
--

LOCK TABLES `dichvu` WRITE;
/*!40000 ALTER TABLE `dichvu` DISABLE KEYS */;
INSERT INTO `dichvu` VALUES (1,'DV001','Trông xe','Đảm bảo an ninh, trật tự',1000000,'Hỗ trợ trông xe máy , xe đạp, ô tô'),(2,'DV002','Giám sát người ra vào','Đảm bảo an ninh, trật tự',500000,'Ghi lại lịch sử thời gian người qua người lại để tiện theo dõi'),(3,'DV003','Bảo vệ đồ đạc','Đảm bảo an ninh, trật tự',500000,'Bảo vệ đồ đạc khỏi bị mất/ trộm cắp'),(4,'DV004','Lau dọn','Dịch vụ vệ sinh',500000,'Lau sàn, chùi toilet, lau kính'),(5,'DV005','Ăn uống theo tháng','Dịch vụ ăn uống',20000000,'Hỗ trợ tổ chức ăn uống combo cho công ty trong một tháng'),(6,'DV006','Điện','Vận hành, bảo trì bảo dưỡng',100000,'Cung cấp điện, ánh sáng'),(7,'DV007','Nước','Vận hành, bảo trì bảo dưỡng',200000,'Cung cấp nước uống và nước sinh hoạt'),(8,'DV008','Sửa chữa thiết bị','Vận hành, bảo trì bảo dưỡng',200000,'Hỗ trợ liên lạc với người sửa chữa thiết bị nếu bị hỏng'),(9,'DV009','Mạng wifi','Vận hành, bảo trì bảo dưỡng',500000,'Hỗ trợ cung cấp mạng theo hàng tháng');
/*!40000 ALTER TABLE `dichvu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dichvu_congty`
--

DROP TABLE IF EXISTS `dichvu_congty`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dichvu_congty` (
  `id` int NOT NULL AUTO_INCREMENT,
  `id_dichvu` int NOT NULL,
  `id_congty` int NOT NULL,
  `thangthue` int NOT NULL,
  `namthue` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_dichvu` (`id_dichvu`),
  KEY `id_congty` (`id_congty`),
  CONSTRAINT `dichvu_congty_ibfk_1` FOREIGN KEY (`id_dichvu`) REFERENCES `dichvu` (`id`),
  CONSTRAINT `dichvu_congty_ibfk_2` FOREIGN KEY (`id_congty`) REFERENCES `congty` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=181 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dichvu_congty`
--

LOCK TABLES `dichvu_congty` WRITE;
/*!40000 ALTER TABLE `dichvu_congty` DISABLE KEYS */;
INSERT INTO `dichvu_congty` VALUES (1,1,1,1,2022),(2,2,1,1,2022),(3,3,1,1,2022),(4,4,1,1,2022),(5,5,1,1,2022),(6,3,1,2,2022),(7,4,1,2,2022),(8,5,1,2,2022),(9,6,1,3,2022),(10,7,1,3,2022),(11,8,1,3,2022),(12,9,1,4,2022),(13,1,1,4,2022),(14,4,1,5,2022),(15,7,1,5,2022),(16,9,1,5,2022),(17,4,1,6,2022),(18,5,1,6,2022),(19,6,1,6,2022),(20,7,1,6,2022),(21,4,2,1,2022),(22,5,2,1,2022),(23,3,2,1,2022),(24,4,2,1,2022),(25,5,2,1,2022),(26,6,2,2,2022),(27,7,2,2,2022),(28,8,2,2,2022),(29,9,2,3,2022),(30,1,2,3,2022),(31,4,2,3,2022),(32,7,2,4,2022),(33,9,2,4,2022),(34,4,2,5,2022),(35,5,2,5,2022),(36,6,2,5,2022),(37,5,2,6,2022),(38,6,2,6,2022),(39,7,2,6,2022),(40,8,2,6,2022),(41,6,3,1,2022),(42,7,3,1,2022),(43,8,3,1,2022),(44,9,3,1,2022),(45,1,3,1,2022),(46,4,3,2,2022),(47,7,3,2,2022),(48,9,3,2,2022),(49,4,3,3,2022),(50,5,3,3,2022),(51,6,3,3,2022),(52,7,3,4,2022),(53,4,3,4,2022),(54,5,3,5,2022),(55,3,3,5,2022),(56,4,3,5,2022),(57,5,3,6,2022),(58,6,3,6,2022),(59,7,3,6,2022),(60,4,3,6,2022),(61,5,4,1,2022),(62,6,4,1,2022),(63,5,4,1,2022),(64,6,4,1,2022),(65,7,4,1,2022),(66,8,4,2,2022),(67,6,4,2,2022),(68,7,4,2,2022),(69,8,4,3,2022),(70,9,4,3,2022),(71,1,4,3,2022),(72,4,4,4,2022),(73,7,4,4,2022),(74,9,4,5,2022),(75,4,4,5,2022),(76,5,4,5,2022),(77,6,4,6,2022),(78,7,4,6,2022),(79,4,4,6,2022),(80,4,4,6,2022),(81,5,5,1,2022),(82,6,5,1,2022),(83,5,5,1,2022),(84,6,5,1,2022),(85,7,5,1,2022),(86,8,5,2,2022),(87,6,5,2,2022),(88,7,5,2,2022),(89,8,5,3,2022),(90,9,5,3,2022),(91,1,5,3,2022),(92,4,5,4,2022),(93,7,5,4,2022),(94,9,5,5,2022),(95,4,5,5,2022),(96,5,5,5,2022),(97,6,5,6,2022),(98,7,5,6,2022),(99,4,5,6,2022),(100,4,5,6,2022),(101,5,6,1,2022),(102,6,6,1,2022),(103,5,6,1,2022),(104,6,6,1,2022),(105,7,6,1,2022),(106,8,6,2,2022),(107,6,6,2,2022),(108,7,6,2,2022),(109,8,6,3,2022),(110,9,6,3,2022),(111,1,6,3,2022),(112,4,6,4,2022),(113,7,6,4,2022),(114,9,6,5,2022),(115,4,6,5,2022),(116,5,6,5,2022),(117,6,6,6,2022),(118,7,6,6,2022),(119,4,6,6,2022),(120,8,6,6,2022),(121,9,7,1,2022),(122,1,7,1,2022),(123,4,7,1,2022),(124,7,7,1,2022),(125,9,7,1,2022),(126,4,7,2,2022),(127,5,7,2,2022),(128,6,7,2,2022),(129,7,7,3,2022),(130,4,7,3,2022),(131,4,7,3,2022),(132,5,7,4,2022),(133,6,7,4,2022),(134,5,7,5,2022),(135,6,7,5,2022),(136,7,7,5,2022),(137,8,7,6,2022),(138,8,7,6,2022),(139,9,7,6,2022),(140,1,7,6,2022),(141,4,8,1,2022),(142,7,8,1,2022),(143,9,8,1,2022),(144,4,8,1,2022),(145,5,8,1,2022),(146,6,8,2,2022),(147,7,8,2,2022),(148,4,8,2,2022),(149,4,8,3,2022),(150,5,8,3,2022),(151,6,8,3,2022),(152,5,8,4,2022),(153,6,8,4,2022),(154,7,8,5,2022),(155,8,8,5,2022),(156,6,8,5,2022),(157,5,8,6,2022),(158,6,8,6,2022),(159,7,8,6,2022),(160,8,8,6,2022),(161,8,9,1,2022),(162,9,9,1,2022),(163,1,9,1,2022),(164,4,9,1,2022),(165,7,9,1,2022),(166,9,9,2,2022),(167,4,9,2,2022),(168,5,9,2,2022),(169,6,9,3,2022),(170,7,9,3,2022),(171,4,9,3,2022),(172,4,9,4,2022),(173,5,9,4,2022),(174,6,9,5,2022),(175,6,9,5,2022),(176,5,9,5,2022),(177,6,9,6,2022),(178,7,9,6,2022),(179,8,9,6,2022),(180,8,9,6,2022);
/*!40000 ALTER TABLE `dichvu_congty` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nhanvien_dichvu`
--

DROP TABLE IF EXISTS `nhanvien_dichvu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `nhanvien_dichvu` (
  `id` int NOT NULL AUTO_INCREMENT,
  `id_dichvu` int NOT NULL,
  `id_nhanvientoanha` int NOT NULL,
  `mucluong` double NOT NULL,
  `thanglam` int NOT NULL,
  `namlam` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_dichvu` (`id_dichvu`),
  KEY `nhanvien_dichvu_ibfk_2` (`id_nhanvientoanha`),
  CONSTRAINT `nhanvien_dichvu_ibfk_1` FOREIGN KEY (`id_dichvu`) REFERENCES `dichvu` (`id`),
  CONSTRAINT `nhanvien_dichvu_ibfk_2` FOREIGN KEY (`id_nhanvientoanha`) REFERENCES `nhanvientoanha` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=121 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nhanvien_dichvu`
--

LOCK TABLES `nhanvien_dichvu` WRITE;
/*!40000 ALTER TABLE `nhanvien_dichvu` DISABLE KEYS */;
INSERT INTO `nhanvien_dichvu` VALUES (1,1,1,5000000,1,2022),(2,1,2,5000000,1,2022),(3,1,3,5000000,1,2022),(4,1,4,5000000,1,2022),(5,1,5,5000000,1,2022),(6,1,6,5000000,1,2022),(7,1,7,5000000,1,2022),(8,1,8,5000000,1,2022),(9,1,9,5000000,1,2022),(10,1,10,5000000,1,2022),(11,1,11,5000000,1,2022),(12,1,12,5000000,1,2022),(13,1,13,5000000,1,2022),(14,1,14,5000000,1,2022),(15,1,15,5000000,1,2022),(16,1,16,5000000,1,2022),(17,1,17,5000000,1,2022),(18,1,18,5000000,1,2022),(19,1,19,5000000,1,2022),(20,1,20,5000000,1,2022),(21,2,1,200000,2,2022),(22,2,2,200000,2,2022),(23,2,3,200000,2,2022),(24,2,4,200000,2,2022),(25,2,5,200000,2,2022),(26,2,6,200000,2,2022),(27,2,7,200000,2,2022),(28,2,8,200000,2,2022),(29,2,9,200000,2,2022),(30,2,10,200000,2,2022),(31,2,11,200000,2,2022),(32,2,12,200000,2,2022),(33,2,13,200000,2,2022),(34,2,14,200000,2,2022),(35,2,15,200000,2,2022),(36,2,16,200000,2,2022),(37,2,17,200000,2,2022),(38,2,18,200000,2,2022),(39,2,19,200000,2,2022),(40,2,20,200000,2,2022),(41,3,1,500000,3,2022),(42,3,2,500000,3,2022),(43,3,3,500000,3,2022),(44,3,4,500000,3,2022),(45,3,5,500000,3,2022),(46,3,6,500000,3,2022),(47,3,7,500000,3,2022),(48,3,8,500000,3,2022),(49,3,9,500000,3,2022),(50,3,10,500000,3,2022),(51,3,11,500000,3,2022),(52,3,12,500000,3,2022),(53,3,13,500000,3,2022),(54,3,14,500000,3,2022),(55,3,15,500000,3,2022),(56,3,16,500000,3,2022),(57,3,17,500000,3,2022),(58,3,18,500000,3,2022),(59,3,19,500000,3,2022),(60,3,20,500000,3,2022),(61,4,1,2000000,4,2022),(62,4,2,2000000,4,2022),(63,4,3,2000000,4,2022),(64,4,4,2000000,4,2022),(65,4,5,2000000,4,2022),(66,4,6,2000000,4,2022),(67,4,7,2000000,4,2022),(68,4,8,2000000,4,2022),(69,4,9,2000000,4,2022),(70,4,10,2000000,4,2022),(71,4,11,2000000,4,2022),(72,4,12,2000000,4,2022),(73,4,13,2000000,4,2022),(74,4,14,2000000,4,2022),(75,4,15,2000000,4,2022),(76,4,16,2000000,4,2022),(77,4,17,2000000,4,2022),(78,4,18,2000000,4,2022),(79,4,19,2000000,4,2022),(80,4,20,2000000,4,2022),(81,7,1,100000,5,2022),(82,7,2,100000,5,2022),(83,7,3,100000,5,2022),(84,7,4,100000,5,2022),(85,7,5,100000,5,2022),(86,7,6,100000,5,2022),(87,7,7,100000,5,2022),(88,7,8,100000,5,2022),(89,7,9,100000,5,2022),(90,7,10,100000,5,2022),(91,7,11,100000,5,2022),(92,7,12,100000,5,2022),(93,7,13,100000,5,2022),(94,7,14,100000,5,2022),(95,7,15,100000,5,2022),(96,7,16,100000,5,2022),(97,7,17,100000,5,2022),(98,7,18,100000,5,2022),(99,7,19,100000,5,2022),(100,7,20,100000,5,2022),(101,8,1,300000,6,2022),(102,8,2,300000,6,2022),(103,8,3,300000,6,2022),(104,8,4,300000,6,2022),(105,8,5,300000,6,2022),(106,8,6,300000,6,2022),(107,8,7,300000,6,2022),(108,8,8,300000,6,2022),(109,8,9,300000,6,2022),(110,8,10,300000,6,2022),(111,8,11,300000,6,2022),(112,8,12,300000,6,2022),(113,8,13,300000,6,2022),(114,8,14,300000,6,2022),(115,8,15,300000,6,2022),(116,8,16,300000,6,2022),(117,8,17,300000,6,2022),(118,8,18,300000,6,2022),(119,8,19,300000,6,2022),(120,8,20,300000,6,2022);
/*!40000 ALTER TABLE `nhanvien_dichvu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nhanviencongty`
--

DROP TABLE IF EXISTS `nhanviencongty`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `nhanviencongty` (
  `id` int NOT NULL AUTO_INCREMENT,
  `ma` varchar(100) NOT NULL,
  `ten` varchar(100) NOT NULL,
  `cccd` varchar(20) NOT NULL,
  `ngaysinh` date NOT NULL,
  `sdt` varchar(10) NOT NULL,
  `id_congty` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_congty` (`id_congty`),
  CONSTRAINT `nhanviencongty_ibfk_1` FOREIGN KEY (`id_congty`) REFERENCES `congty` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nhanviencongty`
--

LOCK TABLES `nhanviencongty` WRITE;
/*!40000 ALTER TABLE `nhanviencongty` DISABLE KEYS */;
INSERT INTO `nhanviencongty` VALUES (1,'NVCT001','Trần Quốc Tuấn','125094487','1983-10-01','918343078',1),(2,'NVCT002','Đặng Thị Ngọc Dung','250530556','1983-10-02','903033026',2),(3,'NVCT003','Thái Huệ Phương','111250112','1983-10-03','936080848',3),(4,'NVCT004','Nguyễn Hữu Thọ','331124981','1983-10-04','902326435',4),(5,'NVCT005','Lão Nghị đồng','024017488','1983-10-05','983821577',5),(6,'NVCT006','Võ Thị Vân','020546988','1983-10-06','1223918855',6),(7,'NVCT007','Phan Hồng Phúc','271593786','1983-10-07','932332468',7),(8,'NVCT008','Đặng Thành Điểm','012585148','1983-10-08','913608527',8),(9,'NVCT009','Nguyễn Văn Dũng','025033330','1983-10-09','989214456',9),(10,'NVCT010','Nguyễn Thanh Hải','025033329','1983-10-10','982860434',10),(11,'NVCT011','Đặng Xuân Huy','271455843','1983-10-11','982524322',1),(12,'NVCT012','Nhan Hữu Đức','012174944','1983-10-12','989758419',2),(13,'NVCT013','Trương Sinh','024921429','1983-10-13','903947677',3),(14,'NVCT014','Nguyễn Thị Hoàng Dung','290773388','1983-10-14','988330332',4),(15,'NVCT015','Trần Minh Tuyết Anh','013006599','1983-10-15','909730224',5),(16,'NVCT016','Dương Thị Hoanh','230524322','1983-10-16','904359369',6),(17,'NVCT017','Nguyễn Thị Thuỳ Hương','022669028','1983-10-17','918017845',7),(18,'NVCT018','Tô Minh Cương','022320695','1983-10-18','938836892',8),(19,'NVCT019','Trần Quang Hồng','271337978','1983-10-19','985112288',9),(20,'NVCT020','Lâm Thuỵ Đài Trang','024984077','1983-10-20','918010507',10),(21,'NVCT021','Nguyễn Trường Tập','031192268','1983-10-21','919481633',1),(22,'NVCT022','Nguyễn Thị Ngọc Hân','023479596','1983-10-22','908800896',2),(23,'NVCT023','Nguyễn Văn Nhung','023317686','1983-10-23','982390490',3),(24,'NVCT024','Phạm Thị Huệ','012185445','1983-10-24','903760115',4),(25,'NVCT025','Phạm DĐình Tuấn','023167475','1984-06-01','903937775',5),(26,'NVCT026','Huỳnh Thúc Kháng','334307151','1984-06-02','903725087',6),(27,'NVCT027','Nguyễn Hữu Mười','240693796','1984-06-03','983123108',7),(28,'NVCT028','Anh Tây','271671079','1984-06-04','903907138',8),(29,'NVCT029','Lý Cẩm Đào','030741920','1984-06-05','972596264',9),(30,'NVCT030','Tạ Quang Vũ','230594513','1984-06-06','908048185',10),(31,'NVCT031','Trà Thị Ngọc Trâm','024781863','1984-06-07','903309086',1),(32,'NVCT032','Trương Huệ Anh','023015856','1984-06-08','989619049',2),(33,'NVCT033','Trần Công Lê Vũ','022633672','1984-06-09','908196328',3),(34,'NVCT034','DNTN Rạng Đông','310791978','1984-06-10','988549016',4),(35,'NVCT035','Anh Tuấn','022995177','1984-06-11','908564245',5),(36,'NVCT036','Vũ Tú Quỳnh','290701476','1984-06-12','909044565',6),(37,'NVCT037','Nguyễn Thị Khôi','024514567','1984-06-13','1693328332',7),(38,'NVCT038','Bs Võ Đức Hiếu','022355980','1984-06-14','909663570',8),(39,'NVCT039','Nguyễn Đức Bảo Thu','025036500','1984-06-15','982157610',9),(40,'NVCT040','Lê Thị Thuý Âu','273045763','1984-06-16','903780507',10),(41,'NVCT041','Phạm Dân Cường','023082579','1984-06-17','983919512',1),(42,'NVCT042','Chú Lê Văn Tâm','023997097','1984-06-18','973848884',2),(43,'NVCT043','Hoàng Minh Tâm','023642950','1984-06-19','989002234',3),(44,'NVCT044','Mạc Đăng Lui','300725054','1984-06-20','907722411',4),(45,'NVCT045','Nguyễn Hàn Sinh','300975399','1984-06-21','903163678',5),(46,'NVCT046','Lê Thuượng Phú','024266130','1984-06-22','909918395',6),(47,'NVCT047','Nguyễn Đức Khang','024949244','1984-06-23','933988789',7),(48,'NVCT048','Chị Thảo','301006370','1984-06-24','903523918',8),(49,'NVCT049','Đặng Thị Thiên Nga','362037242','1984-06-25','908626699',9),(50,'NVCT050','Nguyễn Thị Mến','022109987','1984-06-26','989138138',10),(51,'NVCT051','Phạm Thanh Liêm','023307221','1984-06-27','1698289036',1),(52,'NVCT052','Chú Nuôi','023416106','1985-01-01','1662093979',2),(53,'NVCT053','Trần Quốc Dũng','201384900','1985-01-02','989037088',3),(54,'NVCT054','Cô Lý','023582796','1985-01-03','906969668',4),(55,'NVCT055','Ngô Trọng Toàn','151147626','1985-01-04','908250636',5),(56,'NVCT056','Nguyễn Thị Mỹ Hạnh','022358770','1985-01-05','902992892',6),(57,'NVCT057','Chị Oanh','023625330','1985-01-06','903966901',7),(58,'NVCT058','Trần Quang Huynh','023318759','1985-01-07','913103833',8),(59,'NVCT059','Đinh Tấn Tùng','024269743','1985-01-08','903992979',9),(60,'NVCT060','Nguyễn Tuường Vi','022993206','1985-01-09','913749679',10),(61,'NVCT061','Nguyễn Xuân Phong','023886199','1985-01-10','913927845',1),(62,'NVCT062','Ngô Minh Tâm','023205352','1985-01-11','906755575',2),(63,'NVCT063','Vũ Quang Hiệp','351276175','1985-01-12','934028202',3),(64,'NVCT064','Thái Kim Hồng','271642985','1985-01-13','908743889',4),(65,'NVCT065','Anh Tài','250581163','1983-10-21','955383664',5),(66,'NVCT066','PhanThanh Trung','022499987','1983-10-22','903847019',6),(67,'NVCT067','vương Nhất Minh','012546624','1983-10-23','913131779',7),(68,'NVCT068','Anh Anh','023217547','1983-10-24','909575274',8),(69,'NVCT069','Nguyễn Phước Tùng','023446971','1984-06-01','903118284',9),(70,'NVCT070','Nguyễn Bạch Tuyết','023941119','1984-06-02','977666440',10),(71,'NVCT071','Trần Văn Tỏ','024021840','1984-06-03','907302429',1),(72,'NVCT072','Thái Gia Huy','385449229','1984-06-04','918179004',2),(73,'NVCT073','Phạm Nguyễn Thu Loan','023364673','1984-06-05','903158654',3),(74,'NVCT074','Huỳnh Thu Hiền','024554917','1984-06-06','973189241',4),(75,'NVCT075','Trần tú Thanh','021572026','1984-06-07','908215954',5),(76,'NVCT076','Phạm Hưng','023091950','1984-06-08','937290478',6),(77,'NVCT077','Trần Thiện Vĩnh Quân','023085642','1984-06-09','913123008',7),(78,'NVCT078','Trương Hưũ Khanh','025029619','1984-06-10','903987172',8),(79,'NVCT079','Phạm Văn Thắng','197160681','1984-06-11','904353686',9),(80,'NVCT080','Lê Quý Tân','022937339','1984-06-12','988206406',10),(81,'NVCT081','Đường Cẩm Minh','023120054','1984-06-13','913356372',1),(82,'NVCT082','Vĩnh Thuỵ Trường Thuý Vi','024687126','1984-06-14','943685666',2),(83,'NVCT083','huỳnh Thị Ngọc Phương','021575810','1984-06-15','1265723888',3),(84,'NVCT084','Trương Ngọc Hoa','012020511','1984-06-16','905064671',4),(85,'NVCT085','Mã Anh','012436669','1984-06-17','917568738',5),(86,'NVCT086','Trần Hán Đức','142025322','1984-06-18','904045455',6),(87,'NVCT087','Anh Tùng','012152366','1984-06-19','949221616',7),(88,'NVCT088','Võ Anh Tuyền','022576129','1984-06-20','918336996',8),(89,'NVCT089','Trần Ngọc Thu','024052238','1984-06-21','932729994',9),(90,'NVCT090','Lư Văn Kiện Anh','024943674','1984-06-22','932732616',10),(91,'NVCT091','Hồ Ngọc Thạch','010324197','1984-06-23','909009599',1),(92,'NVCT092','Lê Khắc Sinh','023095567','1984-06-24','903658151',2),(93,'NVCT093','Huỳnh Văn Tấn','023095567','1984-06-25','938039969',3),(94,'NVCT094','Anh Tuấn','310804944','1984-06-26','913158539',4),(95,'NVCT095','Đinh Thị Luận','021448760','1984-06-27','986939720',5),(96,'NVCT096','Trần Hoàng Cương','351730824','1985-01-01','946661968',6),(97,'NVCT097','Trương Dân Cường','023969057','1985-01-02','903187182',7),(98,'NVCT098','Võ Quốc Minh','280793833','1985-01-03','989880722',8),(99,'NVCT099','Kiến Trí','020797630','1985-01-04','907148921',9),(100,'NVCT100','Nguyễn Văn Trung Hiếu','B1980302','1985-01-05','907140673',10);
/*!40000 ALTER TABLE `nhanviencongty` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nhanvientoanha`
--

DROP TABLE IF EXISTS `nhanvientoanha`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `nhanvientoanha` (
  `id` int NOT NULL AUTO_INCREMENT,
  `ma` varchar(20) NOT NULL,
  `ten` varchar(100) NOT NULL,
  `cccd` varchar(100) NOT NULL,
  `ngaysinh` date NOT NULL,
  `sdt` varchar(100) NOT NULL,
  `vitri` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nhanvientoanha`
--

LOCK TABLES `nhanvientoanha` WRITE;
/*!40000 ALTER TABLE `nhanvientoanha` DISABLE KEYS */;
INSERT INTO `nhanvientoanha` VALUES (1,'NVTN001','Lâm Thị Trung','134716410','1984-06-06','902495664','Trưởng phòng'),(2,'NVTN002','Nguyễn Kim Hoàng','134716411','1984-06-07','902498928','Trưởng phòng'),(3,'NVTN003','Phan Thị Nhạn','134716412','1984-06-08','902505279','Trưởng phòng'),(4,'NVTN004','Trần Hải Huy Trường','134716413','1984-06-09','902511235','Trưởng phòng'),(5,'NVTN005','Giang Quí Bùi','134716414','1984-06-10','902512512','Trưởng phòng'),(6,'NVTN006','Lăng Chi Tiêu','134716415','1984-06-11','902517888','Nhân viên hỗ trợ và phục vụ'),(7,'NVTN007','Nguyễn Thị Mỹ Kim','134716416','1984-06-12','902520107','Nhân viên hỗ trợ và phục vụ'),(8,'NVTN008','Tăng Kim Cúc','134716417','1984-06-13','902538274','Nhân viên hỗ trợ và phục vụ'),(9,'NVTN009','Hồ Bạch Long','134716418','1984-06-14','902539571','Nhân viên hỗ trợ và phục vụ'),(10,'NVTN010','Phạm THị Kim VÂn','134716419','1984-06-15','902550259','Nhân viên hỗ trợ và phục vụ'),(11,'NVTN011','Lâm Trọng Nghĩa','134716420','1984-06-16','902578790','Nhân viên hỗ trợ và phục vụ'),(12,'NVTN012','Lê Thị Thảo','134716421','1984-06-17','902621954','Nhân viên hỗ trợ và phục vụ'),(13,'NVTN013','Huỳnh Văn Quang','134716422','1984-06-18','902626288','Nhân viên hỗ trợ và phục vụ'),(14,'NVTN014','Phạm Trang Thi','134716423','1984-06-19','902627386','Nhân viên hỗ trợ và phục vụ'),(15,'NVTN015','Đỗ Bích Hương','134716424','1984-06-20','902629325','Nhân viên hỗ trợ và phục vụ'),(16,'NVTN016','Phan Như Hải','134716425','1984-06-21','902629486','Nhân viên hỗ trợ và phục vụ'),(17,'NVTN017','Nguyễn Văn Rảnh','134716426','1984-06-22','902631400','Nhân viên hỗ trợ và phục vụ'),(18,'NVTN018','Nguyễn Huy Quý','134716427','1984-06-23','902631699','Nhân viên hỗ trợ và phục vụ'),(19,'NVTN019','Lê Thị Tỉnh','134716428','1984-06-24','902631790','Nhân viên hỗ trợ và phục vụ'),(20,'NVTN020','Nguyễn Thị Xuân Hưng','134716429','1984-06-25','902633463','Nhân viên hỗ trợ và phục vụ');
/*!40000 ALTER TABLE `nhanvientoanha` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'quanlyvanphong'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-06-01 22:28:18
