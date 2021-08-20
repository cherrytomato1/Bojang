-- MySQL dump 10.19  Distrib 10.3.31-MariaDB, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: bojang_db
-- ------------------------------------------------------
-- Server version	10.3.31-MariaDB-0ubuntu0.20.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `auth_user`
--

DROP TABLE IF EXISTS `auth_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `auth_user` (
  `id` varchar(255) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `email_verified` bit(1) DEFAULT NULL,
  `image_url` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `provider` int(11) DEFAULT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK7u6ki3561f3y0a91kkd8mfebv` (`user_id`),
  CONSTRAINT `FK7u6ki3561f3y0a91kkd8mfebv` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auth_user`
--

LOCK TABLES `auth_user` WRITE;
/*!40000 ALTER TABLE `auth_user` DISABLE KEYS */;
INSERT INTO `auth_user` VALUES ('1822497665',NULL,NULL,'http://k.kakaocdn.net/dn/dpk9l1/btqmGhA2lKL/Oz0wDuJn1YV2DIn92f6DVK/img_640x640.jpg','김민기',NULL,1,'S65709b314438'),('1840264490',NULL,NULL,'http://k.kakaocdn.net/dn/dpk9l1/btqmGhA2lKL/Oz0wDuJn1YV2DIn92f6DVK/img_640x640.jpg','권도엽',NULL,1,'Sb7bf7830e855'),('1842743741',NULL,NULL,'http://k.kakaocdn.net/dn/bE7Qb3/btqYkbDKfN3/gVVPyOxxatfLGuRyE01Kf1/img_640x640.jpg','도태욱',NULL,1,'Sa1dcad878a4d'),('1856932656',NULL,NULL,'http://k.kakaocdn.net/dn/dpk9l1/btqmGhA2lKL/Oz0wDuJn1YV2DIn92f6DVK/img_640x640.jpg','보장상인',NULL,1,'S743d189725ad'),('1856959692',NULL,NULL,'http://k.kakaocdn.net/dn/dpk9l1/btqmGhA2lKL/Oz0wDuJn1YV2DIn92f6DVK/img_640x640.jpg','bojang_pickup',NULL,1,'S4aa7e52d0341');
/*!40000 ALTER TABLE `auth_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bank_type`
--

DROP TABLE IF EXISTS `bank_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bank_type` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bank_type`
--

LOCK TABLES `bank_type` WRITE;
/*!40000 ALTER TABLE `bank_type` DISABLE KEYS */;
INSERT INTO `bank_type` VALUES (1,'국민은행'),(2,'신한은행'),(3,'우리은행'),(4,'하나은행'),(5,'기업은행');
/*!40000 ALTER TABLE `bank_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `basket`
--

DROP TABLE IF EXISTS `basket`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `basket` (
  `id` varchar(255) NOT NULL,
  `amount` bigint(20) DEFAULT NULL,
  `item_id` varchar(255) DEFAULT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKfjq88srd4ni0ea5jmr42c3iob` (`item_id`),
  KEY `FKfp7yinn3dh4sy1ia364xp3d9g` (`user_id`),
  CONSTRAINT `FKfjq88srd4ni0ea5jmr42c3iob` FOREIGN KEY (`item_id`) REFERENCES `item` (`id`),
  CONSTRAINT `FKfp7yinn3dh4sy1ia364xp3d9g` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `basket`
--

LOCK TABLES `basket` WRITE;
/*!40000 ALTER TABLE `basket` DISABLE KEYS */;
/*!40000 ALTER TABLE `basket` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `favorite_store`
--

DROP TABLE IF EXISTS `favorite_store`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `favorite_store` (
  `id` varchar(255) NOT NULL,
  `store_id` varchar(255) DEFAULT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKr6bk7q3ru7y3dqcis9exkpxef` (`store_id`),
  KEY `FKly8cfqsjue6ojuoi7tbmr8v9g` (`user_id`),
  CONSTRAINT `FKly8cfqsjue6ojuoi7tbmr8v9g` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKr6bk7q3ru7y3dqcis9exkpxef` FOREIGN KEY (`store_id`) REFERENCES `store` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `favorite_store`
--

LOCK TABLES `favorite_store` WRITE;
/*!40000 ALTER TABLE `favorite_store` DISABLE KEYS */;
/*!40000 ALTER TABLE `favorite_store` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `item`
--

DROP TABLE IF EXISTS `item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `item` (
  `id` varchar(255) NOT NULL,
  `content` varchar(255) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `on_sale` bit(1) DEFAULT NULL,
  `price` bigint(20) DEFAULT NULL,
  `register_time` datetime DEFAULT NULL,
  `item_type_id` bigint(20) DEFAULT NULL,
  `store_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKh7kyk389qj2m5chaa0njsq601` (`item_type_id`),
  KEY `FKi0c87m5jy5qxw8orrf2pugs6h` (`store_id`),
  CONSTRAINT `FKh7kyk389qj2m5chaa0njsq601` FOREIGN KEY (`item_type_id`) REFERENCES `item_type` (`id`),
  CONSTRAINT `FKi0c87m5jy5qxw8orrf2pugs6h` FOREIGN KEY (`store_id`) REFERENCES `store` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item`
--

LOCK TABLES `item` WRITE;
/*!40000 ALTER TABLE `item` DISABLE KEYS */;
INSERT INTO `item` VALUES ('S0212fd45e229','산지직송 단단한 방울토마토 2kg','2ceed153-ed23-45ad-acd1-f8dac593fd3b_Item_default.png','방울 토마토 2kg','',15000,'2021-08-18 21:45:55',3,'S7429354f7aa2');
/*!40000 ALTER TABLE `item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `item_type`
--

DROP TABLE IF EXISTS `item_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `item_type` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item_type`
--

LOCK TABLES `item_type` WRITE;
/*!40000 ALTER TABLE `item_type` DISABLE KEYS */;
INSERT INTO `item_type` VALUES (1,'축산'),(2,'수산'),(3,'농산물'),(4,'반찬'),(5,'김치'),(6,'가공식품');
/*!40000 ALTER TABLE `item_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `market`
--

DROP TABLE IF EXISTS `market`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `market` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `market`
--

LOCK TABLES `market` WRITE;
/*!40000 ALTER TABLE `market` DISABLE KEYS */;
INSERT INTO `market` VALUES (1,'싸피시장'),(2,'도깨비시장'),(3,'별빛남문시장'),(4,'봉천제일시장'),(5,'민락골목시장');
/*!40000 ALTER TABLE `market` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_info`
--

DROP TABLE IF EXISTS `order_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order_info` (
  `id` varchar(255) NOT NULL,
  `price` int(11) DEFAULT NULL,
  `register_time` datetime DEFAULT NULL,
  `market_id` bigint(20) DEFAULT NULL,
  `order_status_id` bigint(20) DEFAULT NULL,
  `pay_type_id` bigint(20) DEFAULT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKnqccn36bb9pwxgd7af9wrd2e6` (`market_id`),
  KEY `FK3ddcvc1kmdmd6h1gtaeph9aeu` (`order_status_id`),
  KEY `FKdlq63m9ugnre7qh9wxvo0b6sj` (`pay_type_id`),
  KEY `FKtlpgba4g9kxg3t6s6bi5jw4v7` (`user_id`),
  CONSTRAINT `FK3ddcvc1kmdmd6h1gtaeph9aeu` FOREIGN KEY (`order_status_id`) REFERENCES `order_status` (`id`),
  CONSTRAINT `FKdlq63m9ugnre7qh9wxvo0b6sj` FOREIGN KEY (`pay_type_id`) REFERENCES `pay_type` (`id`),
  CONSTRAINT `FKnqccn36bb9pwxgd7af9wrd2e6` FOREIGN KEY (`market_id`) REFERENCES `market` (`id`),
  CONSTRAINT `FKtlpgba4g9kxg3t6s6bi5jw4v7` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_info`
--

LOCK TABLES `order_info` WRITE;
/*!40000 ALTER TABLE `order_info` DISABLE KEYS */;
INSERT INTO `order_info` VALUES ('S210818043215',30000,'2021-08-18 22:33:04',1,1,1,'Sb7bf7830e855');
/*!40000 ALTER TABLE `order_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_item`
--

DROP TABLE IF EXISTS `order_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order_item` (
  `id` varchar(255) NOT NULL,
  `amount` int(11) DEFAULT NULL,
  `comment` varchar(255) DEFAULT NULL,
  `pick_status` bit(1) DEFAULT NULL,
  `item_id` varchar(255) DEFAULT NULL,
  `order_info_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKija6hjjiit8dprnmvtvgdp6ru` (`item_id`),
  KEY `FK1xueo8n0m76dmyi0e9npltsl8` (`order_info_id`),
  CONSTRAINT `FK1xueo8n0m76dmyi0e9npltsl8` FOREIGN KEY (`order_info_id`) REFERENCES `order_info` (`id`),
  CONSTRAINT `FKija6hjjiit8dprnmvtvgdp6ru` FOREIGN KEY (`item_id`) REFERENCES `item` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_item`
--

LOCK TABLES `order_item` WRITE;
/*!40000 ALTER TABLE `order_item` DISABLE KEYS */;
INSERT INTO `order_item` VALUES ('S81d127b54295',2,'싱싱한 방울토마토 부탁드려요~!','\0','S0212fd45e229','S210818043215');
/*!40000 ALTER TABLE `order_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_status`
--

DROP TABLE IF EXISTS `order_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order_status` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_status`
--

LOCK TABLES `order_status` WRITE;
/*!40000 ALTER TABLE `order_status` DISABLE KEYS */;
INSERT INTO `order_status` VALUES (1,'결제 완료'),(2,'픽업 완료'),(3,'인수 완료');
/*!40000 ALTER TABLE `order_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pay_type`
--

DROP TABLE IF EXISTS `pay_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pay_type` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pay_type`
--

LOCK TABLES `pay_type` WRITE;
/*!40000 ALTER TABLE `pay_type` DISABLE KEYS */;
INSERT INTO `pay_type` VALUES (1,'현금'),(2,'카드');
/*!40000 ALTER TABLE `pay_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sales`
--

DROP TABLE IF EXISTS `sales`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sales` (
  `id` varchar(255) NOT NULL,
  `register_time` date NOT NULL,
  `sum` int(11) NOT NULL,
  `store_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKt9e650iv592u855bwpbfcy459` (`store_id`),
  CONSTRAINT `FKt9e650iv592u855bwpbfcy459` FOREIGN KEY (`store_id`) REFERENCES `store` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sales`
--

LOCK TABLES `sales` WRITE;
/*!40000 ALTER TABLE `sales` DISABLE KEYS */;
INSERT INTO `sales` VALUES ('Sd69f0b58f94b','2021-08-18',184300,'S7429354f7aa2');
/*!40000 ALTER TABLE `sales` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `store`
--

DROP TABLE IF EXISTS `store`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `store` (
  `id` varchar(255) NOT NULL,
  `address` varchar(200) DEFAULT NULL,
  `comment` varchar(200) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `name` varchar(20) NOT NULL,
  `section` int(11) NOT NULL,
  `market_id` bigint(20) DEFAULT NULL,
  `store_type_id` bigint(20) DEFAULT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKr8sqanx5snrn03vrwnonlklfk` (`market_id`),
  KEY `FKg7wk2yfmvuvjs1hxsfr1ipjeg` (`store_type_id`),
  KEY `FKn82wpcqrb21yddap4s3ttwnxj` (`user_id`),
  CONSTRAINT `FKg7wk2yfmvuvjs1hxsfr1ipjeg` FOREIGN KEY (`store_type_id`) REFERENCES `store_type` (`id`),
  CONSTRAINT `FKn82wpcqrb21yddap4s3ttwnxj` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKr8sqanx5snrn03vrwnonlklfk` FOREIGN KEY (`market_id`) REFERENCES `market` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `store`
--

LOCK TABLES `store` WRITE;
/*!40000 ALTER TABLE `store` DISABLE KEYS */;
INSERT INTO `store` VALUES ('S023f1ee75a47','싸피시 싸피동 400-321','싱싱한 야채와 과일 팔아요~','71dcd7cb-370d-40a3-92f1-930ab0e99cc5_store_default.jpg','중부야채직판장',4,1,1,NULL),('S0eb44e686443','싸피시 싸피동 202-121','질 좋고 싱싱한 수산물들을 다양하게 판매하고 있습니다. 항상 정성을 담아 좋은 상품을 보내드리니 앞으로 많은 이용부탁드립니다~ 감사합니다!','71dcd7cb-370d-40a3-92f1-930ab0e99cc5_store_default.jpg','경남수산',2,1,3,NULL),('S14aea63ae505','싸피시 싸피동 200-121','싱싱한 야채와 과일 팔아요~','71dcd7cb-370d-40a3-92f1-930ab0e99cc5_store_default.jpg','산새청과',2,1,1,NULL),('S15a9a2fd4fbc','싸피시 싸피동 202-121','신선하고 질 좋은 축산물만을 엄선하여 판매하고 있습니다.','71dcd7cb-370d-40a3-92f1-930ab0e99cc5_store_default.jpg','도드람한돈한우',2,1,2,NULL),('S1749b9b581f2','싸피시 싸피동 606-521','질 좋고 싱싱한 수산물들을 다양하게 판매하고 있습니다. 항상 정성을 담아 좋은 상품을 보내드리니 앞으로 많은 이용부탁드립니다~ 감사합니다!','71dcd7cb-370d-40a3-92f1-930ab0e99cc5_store_default.jpg','영화해물',6,1,3,NULL),('S21cc66097b6d','싸피시 싸피동 100-021','싱싱한 야채와 과일 팔아요~','71dcd7cb-370d-40a3-92f1-930ab0e99cc5_store_default.jpg','복뎅이네과일야채',1,1,1,NULL),('S29da4334f74c','싸피시 싸피동 404-321','신선하고 질 좋은 축산물만을 엄선하여 판매하고 있습니다.','71dcd7cb-370d-40a3-92f1-930ab0e99cc5_store_default.jpg','아빠정육점',4,1,2,NULL),('S305fe82a1725','싸피시 싸피동 424-321','항상 정성을 담아 좋은 상품을 보내드리니 앞으로 많은 이용부탁드립니다~ 감사합니다!','71dcd7cb-370d-40a3-92f1-930ab0e99cc5_store_default.jpg','신성기물',4,1,6,NULL),('S4020841eb515','싸피시 싸피동 505-421','항상 정성을 담아 좋은 상품을 보내드리니 앞으로 많은 이용부탁드립니다~ 감사합니다!','71dcd7cb-370d-40a3-92f1-930ab0e99cc5_store_default.jpg','경진김',5,1,4,NULL),('S435b29340635','싸피시 싸피동 600-521','싱싱한 야채와 과일 팔아요~','71dcd7cb-370d-40a3-92f1-930ab0e99cc5_store_default.jpg','다온청과',6,1,1,NULL),('S4d9d135e9712','싸피시 싸피동 404-321','항상 정성을 담아 좋은 상품을 보내드리니 앞으로 많은 이용부탁드립니다~ 감사합니다!','71dcd7cb-370d-40a3-92f1-930ab0e99cc5_store_default.jpg','부산어묵',4,1,4,NULL),('S54f5b3868d4e','싸피시 싸피동 107-021','항상 정성을 담아 좋은 상품을 보내드리니 앞으로 많은 이용부탁드립니다~ 감사합니다!','71dcd7cb-370d-40a3-92f1-930ab0e99cc5_store_default.jpg','충남상회',1,1,7,NULL),('S56b8ef9af2b5','싸피시 싸피동 303-221','신선하고 질 좋은 축산물만을 엄선하여 판매하고 있습니다.','71dcd7cb-370d-40a3-92f1-930ab0e99cc5_store_default.jpg','명품한우',3,1,2,NULL),('S5c322ca1293c','싸피시 싸피동 101-021','항상 정성을 담아 좋은 상품을 보내드리니 앞으로 많은 이용부탁드립니다~ 감사합니다!','71dcd7cb-370d-40a3-92f1-930ab0e99cc5_store_default.jpg','두부마을',1,1,4,NULL),('S5c98dc6e7e8e','싸피시 싸피동 505-421','질 좋고 싱싱한 수산물들을 다양하게 판매하고 있습니다. 항상 정성을 담아 좋은 상품을 보내드리니 앞으로 많은 이용부탁드립니다~ 감사합니다!','71dcd7cb-370d-40a3-92f1-930ab0e99cc5_store_default.jpg','지동수산',5,1,3,NULL),('S5f58852b4332','싸피시 싸피동 300-221','싱싱한 야채와 과일 팔아요~','71dcd7cb-370d-40a3-92f1-930ab0e99cc5_store_default.jpg','고향청과',3,1,1,NULL),('S6bc9c24e3b8a','싸피시 싸피동 318-221','항상 정성을 담아 좋은 상품을 보내드리니 앞으로 많은 이용부탁드립니다~ 감사합니다!','71dcd7cb-370d-40a3-92f1-930ab0e99cc5_store_default.jpg','백마전기',3,1,6,NULL),('S6c17df1e0283','싸피시 싸피동 505-421','신선하고 질 좋은 축산물만을 엄선하여 판매하고 있습니다.','71dcd7cb-370d-40a3-92f1-930ab0e99cc5_store_default.jpg','총각네정육점',5,1,2,NULL),('S7429354f7aa2','싸피시 싸피동 101-021','질 좋고 싱싱한 수산물들을 다양하게 판매하고 있습니다. 항상 정성을 담아 좋은 상품을 보내드리니 앞으로 많은 이용부탁드립니다~ 감사합니다!','71dcd7cb-370d-40a3-92f1-930ab0e99cc5_store_default.jpg','나래수산',1,1,3,'S65709b314438'),('S7605623ae9f8','싸피시 싸피동 212-121','항상 정성을 담아 좋은 상품을 보내드리니 앞으로 많은 이용부탁드립니다~ 감사합니다!','71dcd7cb-370d-40a3-92f1-930ab0e99cc5_store_default.jpg','지동기물',2,1,6,NULL),('S7c3097cdaabf','싸피시 싸피동 606-521','항상 정성을 담아 좋은 상품을 보내드리니 앞으로 많은 이용부탁드립니다~ 감사합니다!','71dcd7cb-370d-40a3-92f1-930ab0e99cc5_store_default.jpg','빵심이네',6,1,4,NULL),('S8512327b4dc5','싸피시 싸피동 208-121','항상 정성을 담아 좋은 상품을 보내드리니 앞으로 많은 이용부탁드립니다~ 감사합니다!','71dcd7cb-370d-40a3-92f1-930ab0e99cc5_store_default.jpg','해성양행',2,1,5,NULL),('S988580ae1657','싸피시 싸피동 321-221','항상 정성을 담아 좋은 상품을 보내드리니 앞으로 많은 이용부탁드립니다~ 감사합니다!','71dcd7cb-370d-40a3-92f1-930ab0e99cc5_store_default.jpg','남문상회',3,1,7,NULL),('S9a6feb281ffc','싸피시 싸피동 104-021','항상 정성을 담아 좋은 상품을 보내드리니 앞으로 많은 이용부탁드립니다~ 감사합니다!','71dcd7cb-370d-40a3-92f1-930ab0e99cc5_store_default.jpg','X세대신발',1,1,5,NULL),('S9ac42f7f8755','싸피시 싸피동 404-321','질 좋고 싱싱한 수산물들을 다양하게 판매하고 있습니다. 항상 정성을 담아 좋은 상품을 보내드리니 앞으로 많은 이용부탁드립니다~ 감사합니다!','71dcd7cb-370d-40a3-92f1-930ab0e99cc5_store_default.jpg','싱싱수산',4,1,3,NULL),('S9cfcbe413369','싸피시 싸피동 535-421','항상 정성을 담아 좋은 상품을 보내드리니 앞으로 많은 이용부탁드립니다~ 감사합니다!','71dcd7cb-370d-40a3-92f1-930ab0e99cc5_store_default.jpg','남문뻥튀기',5,1,7,NULL),('Sa0c80638cb13','싸피시 싸피동 101-021','신선하고 질 좋은 축산물만을 엄선하여 판매하고 있습니다.','71dcd7cb-370d-40a3-92f1-930ab0e99cc5_store_default.jpg','명품정육점',1,1,2,NULL),('Sa0eab2d52293','싸피시 싸피동 428-321','항상 정성을 담아 좋은 상품을 보내드리니 앞으로 많은 이용부탁드립니다~ 감사합니다!','71dcd7cb-370d-40a3-92f1-930ab0e99cc5_store_default.jpg','월계슈퍼',4,1,7,NULL),('Sa9bcedc69e65','싸피시 싸피동 106-021','항상 정성을 담아 좋은 상품을 보내드리니 앞으로 많은 이용부탁드립니다~ 감사합니다!','71dcd7cb-370d-40a3-92f1-930ab0e99cc5_store_default.jpg','태양만물',1,1,6,NULL),('Saacebc6e42bd','싸피시 싸피동 500-421','싱싱한 야채와 과일 팔아요~','71dcd7cb-370d-40a3-92f1-930ab0e99cc5_store_default.jpg','강원야채',5,1,1,NULL),('Saeb475fdead5','싸피시 싸피동 642-521','항상 정성을 담아 좋은 상품을 보내드리니 앞으로 많은 이용부탁드립니다~ 감사합니다!','71dcd7cb-370d-40a3-92f1-930ab0e99cc5_store_default.jpg','완도상회',6,1,7,NULL),('Sb15efccc92bd','싸피시 싸피동 214-121','항상 정성을 담아 좋은 상품을 보내드리니 앞으로 많은 이용부탁드립니다~ 감사합니다!','71dcd7cb-370d-40a3-92f1-930ab0e99cc5_store_default.jpg','남해건어물',2,1,7,NULL),('Sb34c2284ab45','싸피시 싸피동 520-421','항상 정성을 담아 좋은 상품을 보내드리니 앞으로 많은 이용부탁드립니다~ 감사합니다!','71dcd7cb-370d-40a3-92f1-930ab0e99cc5_store_default.jpg','승주패션',5,1,5,NULL),('Sb796c01637cb','싸피시 싸피동 303-221','질 좋고 싱싱한 수산물들을 다양하게 판매하고 있습니다. 항상 정성을 담아 좋은 상품을 보내드리니 앞으로 많은 이용부탁드립니다~ 감사합니다!','71dcd7cb-370d-40a3-92f1-930ab0e99cc5_store_default.jpg','어사또수산',3,1,3,NULL),('Sc3e00111ea2b','싸피시 싸피동 312-221','항상 정성을 담아 좋은 상품을 보내드리니 앞으로 많은 이용부탁드립니다~ 감사합니다!','71dcd7cb-370d-40a3-92f1-930ab0e99cc5_store_default.jpg','용궁양품',3,1,5,NULL),('Sd1e5b8eba01f','싸피시 싸피동 416-321','항상 정성을 담아 좋은 상품을 보내드리니 앞으로 많은 이용부탁드립니다~ 감사합니다!','71dcd7cb-370d-40a3-92f1-930ab0e99cc5_store_default.jpg','상신쇼핑',4,1,5,NULL),('Sd907aff14370','싸피시 싸피동 202-121','항상 정성을 담아 좋은 상품을 보내드리니 앞으로 많은 이용부탁드립니다~ 감사합니다!','71dcd7cb-370d-40a3-92f1-930ab0e99cc5_store_default.jpg','오복떡집',2,1,4,NULL),('Sec0be318bdf9','싸피시 싸피동 606-521','신선하고 질 좋은 축산물만을 엄선하여 판매하고 있습니다.','71dcd7cb-370d-40a3-92f1-930ab0e99cc5_store_default.jpg','으뜸정육',6,1,2,NULL),('Sed5352ecdde7','싸피시 싸피동 303-221','항상 정성을 담아 좋은 상품을 보내드리니 앞으로 많은 이용부탁드립니다~ 감사합니다!','71dcd7cb-370d-40a3-92f1-930ab0e99cc5_store_default.jpg','탱탱치킨',3,1,4,NULL);
/*!40000 ALTER TABLE `store` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `store_live`
--

DROP TABLE IF EXISTS `store_live`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `store_live` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `store_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKpplwwx43796to1vhp8tt59ock` (`store_id`),
  CONSTRAINT `FKpplwwx43796to1vhp8tt59ock` FOREIGN KEY (`store_id`) REFERENCES `store` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `store_live`
--

LOCK TABLES `store_live` WRITE;
/*!40000 ALTER TABLE `store_live` DISABLE KEYS */;
/*!40000 ALTER TABLE `store_live` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `store_type`
--

DROP TABLE IF EXISTS `store_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `store_type` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `store_type`
--

LOCK TABLES `store_type` WRITE;
/*!40000 ALTER TABLE `store_type` DISABLE KEYS */;
INSERT INTO `store_type` VALUES (1,'농산물'),(2,'축산물'),(3,'수산물'),(4,'가공식품'),(5,'의류신발'),(6,'가정용품'),(7,'기타');
/*!40000 ALTER TABLE `store_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` varchar(255) NOT NULL,
  `account_number` varchar(200) DEFAULT NULL,
  `name` varchar(15) NOT NULL,
  `phone_number` varchar(15) DEFAULT NULL,
  `register_time` datetime DEFAULT NULL,
  `bank_type_id` bigint(20) DEFAULT NULL,
  `market_id` bigint(20) DEFAULT NULL,
  `user_type_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK29q8vn2k6ytyeqr9exlbcs48c` (`bank_type_id`),
  KEY `FKqq1xiq8ltnhm5ilv46lclooke` (`market_id`),
  KEY `FKlrk9xrdps0emd6d5rx5x3ib6h` (`user_type_id`),
  CONSTRAINT `FK29q8vn2k6ytyeqr9exlbcs48c` FOREIGN KEY (`bank_type_id`) REFERENCES `bank_type` (`id`),
  CONSTRAINT `FKlrk9xrdps0emd6d5rx5x3ib6h` FOREIGN KEY (`user_type_id`) REFERENCES `user_type` (`id`),
  CONSTRAINT `FKqq1xiq8ltnhm5ilv46lclooke` FOREIGN KEY (`market_id`) REFERENCES `market` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('S4aa7e52d0341',NULL,'bojang_pickup',NULL,'2021-08-19 17:47:44',NULL,1,3),('S65709b314438',NULL,'김민기','010-7673-7554','2021-08-18 20:43:32',NULL,NULL,2),('S743d189725ad',NULL,'보장상인',NULL,'2021-08-19 17:26:59',NULL,NULL,2),('Sa1dcad878a4d',NULL,'도태욱',NULL,'2021-08-20 10:48:45',NULL,NULL,NULL),('Sb7bf7830e855',NULL,'권도엽',NULL,'2021-08-18 20:44:05',NULL,1,3);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_type`
--

DROP TABLE IF EXISTS `user_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_type` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_type`
--

LOCK TABLES `user_type` WRITE;
/*!40000 ALTER TABLE `user_type` DISABLE KEYS */;
INSERT INTO `user_type` VALUES (1,'손님'),(2,'상인'),(3,'픽업매니저');
/*!40000 ALTER TABLE `user_type` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-08-20 11:08:02
