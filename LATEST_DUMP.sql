-- MySQL dump 10.13  Distrib 5.7.21, for osx10.12 (x86_64)
--
-- Host: localhost    Database: milestones
-- ------------------------------------------------------
-- Server version	5.7.21

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
-- Table structure for table `links`
--

DROP TABLE IF EXISTS `links`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `links` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `email` varchar(65) NOT NULL DEFAULT '',
  `urlHash` varchar(20) NOT NULL DEFAULT '',
  `dateCreated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `dateLastAccessed` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `projectID` int(11) unsigned NOT NULL,
  PRIMARY KEY (`email`,`projectID`),
  UNIQUE KEY `id` (`id`),
  KEY `projectID` (`projectID`),
  CONSTRAINT `links_ibfk_1` FOREIGN KEY (`projectID`) REFERENCES `projects` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=64 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `links`
--

LOCK TABLES `links` WRITE;
/*!40000 ALTER TABLE `links` DISABLE KEYS */;
/*!40000 ALTER TABLE `links` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `milestones`
--

DROP TABLE IF EXISTS `milestones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `milestones` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL DEFAULT '',
  `description` text,
  `dateCreated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `dateModified` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `dueDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `dateCompleted` timestamp NULL DEFAULT NULL,
  `projectID` int(11) unsigned NOT NULL,
  PRIMARY KEY (`name`,`projectID`),
  UNIQUE KEY `id` (`id`),
  KEY `projectID` (`projectID`),
  CONSTRAINT `milestones_ibfk_1` FOREIGN KEY (`projectID`) REFERENCES `projects` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `milestones`
--

LOCK TABLES `milestones` WRITE;
/*!40000 ALTER TABLE `milestones` DISABLE KEYS */;
INSERT INTO `milestones` VALUES (35,'Course Work 1','Moments its musical age explain. But extremity sex now education concluded earnestly her continual. Oh furniture acuteness suspected continual ye something frankness. Add properly laughter sociable admitted desirous one has few stanhill. Opinion regular in perhaps another enjoyed no engaged he at. It conveying he continual ye suspected as necessary. Separate met packages shy for kindness. ','2018-04-29 21:54:55','2018-04-29 21:54:55','2018-06-20 02:54:00',NULL,18),(36,'Coursework 2','Folly words widow one downs few age every seven. If miss part by fact he park just shew. Discovered had get considered projection who favourable. Necessary up knowledge it tolerably. Unwilling departure education is be dashwoods or an. Use off agreeable law unwilling sir deficient curiosity instantly. Easy mind life fact with see has bore ten. Parish any chatty can elinor direct for former. Up as meant widow equal an share least. ','2018-04-29 21:55:14','2018-04-29 21:55:14','2018-07-26 02:55:00',NULL,18),(39,'Gannt Chart','He moonlight difficult engrossed an it sportsmen. Interested has all devonshire difficulty gay assistance joy. Unaffected at ye of compliment alteration to. Place voice no arise along to. Parlors waiting so against me no. Wishing calling are warrant settled was luckily. Express besides it present if at an opinion visitor. \r\n','2018-04-29 21:57:50','2018-04-29 21:57:55','2018-04-29 15:57:00','2018-04-29 21:57:55',19),(34,'Preliminary Styles','Attention he extremity unwilling on otherwise. Conviction up partiality as delightful is discovered. Yet jennings resolved disposed exertion you off. Left did fond drew fat head poor. So if he into shot half many long. China fully him every fat was world grave. \r\n','2018-04-29 21:53:09','2018-04-29 21:53:09','2018-05-24 02:53:00',NULL,17),(37,'Requirement Specification','Folly words widow one downs few age every seven. If miss part by fact he park just shew. Discovered had get considered projection who favourable. Necessary up knowledge it tolerably. Unwilling departure education is be dashwoods or an. Use off agreeable law unwilling sir deficient curiosity instantly. Easy mind life fact with see has bore ten. Parish any chatty can elinor direct for former. Up as meant widow equal an share least. ','2018-04-29 21:56:55','2018-04-29 21:56:55','2018-05-02 02:56:00',NULL,19),(38,'Risk Analysis','Examine she brother prudent add day ham. Far stairs now coming bed oppose hunted become his. You zealously departure had procuring suspicion. Books whose front would purse if be do decay. Quitting you way formerly disposed perceive ladyship are. Common turned boy direct and yet. ','2018-04-29 21:57:12','2018-04-29 21:57:12','2018-05-29 02:57:00',NULL,19),(33,'Visual Mockups','Compliment interested discretion estimating on stimulated apartments oh. Dear so sing when in find read of call. As distrusts behaviour abilities defective is. Never at water me might. On formed merits hunted unable merely by mr whence or. Possession the unpleasing simplicity her uncommonly. ','2018-04-29 21:52:51','2018-04-29 21:52:51','2018-05-09 02:52:00',NULL,17),(32,'Wireframes','Insipidity the sufficient discretion imprudence resolution sir him decisively. Proceed how any engaged visitor. Explained propriety off out perpetual his you. Feel sold off felt nay rose met you. We so entreaties cultivated astonished is. Was sister for few longer mrs sudden talent become. Done may bore quit evil old mile. If likely am of beauty tastes. ','2018-04-29 21:52:28','2018-04-29 21:53:45','2018-05-02 03:59:00',NULL,17);
/*!40000 ALTER TABLE `milestones` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `projects`
--

DROP TABLE IF EXISTS `projects`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `projects` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(65) NOT NULL DEFAULT '',
  `description` text,
  `dateCreated` datetime NOT NULL,
  `dateModified` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP,
  `userID` int(11) unsigned NOT NULL,
  PRIMARY KEY (`name`,`userID`),
  UNIQUE KEY `id` (`id`),
  KEY `userID` (`userID`),
  CONSTRAINT `projects_ibfk_1` FOREIGN KEY (`userID`) REFERENCES `users` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `projects`
--

LOCK TABLES `projects` WRITE;
/*!40000 ALTER TABLE `projects` DISABLE KEYS */;
INSERT INTO `projects` VALUES (19,'IT Project Management 1','Moments its musical age explain. But extremity sex now education concluded earnestly her continual. Oh furniture acuteness suspected continual ye something frankness. Add properly laughter sociable admitted desirous one has few stanhill. Opinion regular in perhaps another enjoyed no engaged he at. It conveying he continual ye suspected as necessary. Separate met packages shy for kindness. ','2018-04-29 22:56:04','2018-04-29 22:56:04',38),(18,'Systems Programming','The degree development team has worked closely with The Tech Partnership, the Sector Skills Council for Business and Information Technology, and top graduate employers to ensure the learning outcomes of this degree meet the competencies required by industry.','2018-04-29 22:54:09','2018-04-29 22:54:09',37),(17,'Web Design Technologies','Milestones for Web Design Technologies module at University','2018-04-29 22:51:27','2018-04-29 22:51:27',37);
/*!40000 ALTER TABLE `projects` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `fname` varchar(100) NOT NULL DEFAULT '',
  `lname` varchar(100) NOT NULL DEFAULT '',
  `email` varchar(255) NOT NULL DEFAULT '',
  `password` varchar(255) NOT NULL DEFAULT '',
  `dateCreated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `dateLoggedIn` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (37,'John','Smith','john@smith.com','1000:b52833880889766f440af535234766843f071a07513b52d8:58bab11214fa705e5933899c279470afe1ce193cb0a87771','2018-04-29 21:51:05',NULL),(38,'Paddy','McGuinness','paddy@takemeout.com','1000:43806734907acae0c2d04ebe992d9b8c8a316c7eae9234c7:f7ba5b1c11fa979f71f0cdba70d9be2fbbc5accdb343d8f8','2018-04-29 21:55:48',NULL);
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

-- Dump completed on 2018-04-29 22:59:20
