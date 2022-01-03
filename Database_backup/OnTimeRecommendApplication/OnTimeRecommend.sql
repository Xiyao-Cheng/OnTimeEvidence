-- MySQL dump 10.13  Distrib 8.0.17, for Win64 (x86_64)
--
-- Host: 192.12.245.162    Database: OnTimeRecommend
-- ------------------------------------------------------
-- Server version	5.7.28-0ubuntu0.18.04.4

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
-- Table structure for table `chain_apps`
--

DROP TABLE IF EXISTS `chain_apps`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `chain_apps` (
  `app_id` int(11) NOT NULL AUTO_INCREMENT,
  `intent_id` varchar(50) DEFAULT NULL,
  `client_id` varchar(50) DEFAULT NULL,
  `rec_id` varchar(50) DEFAULT NULL,
  `suggestions` varchar(500) DEFAULT NULL,
  `user_profile` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`app_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chain_apps`
--

LOCK TABLES `chain_apps` WRITE;
/*!40000 ALTER TABLE `chain_apps` DISABLE KEYS */;
INSERT INTO `chain_apps` VALUES (4,'publication','cyneuro','pub-rec','[\"simulate single neuron model\",\"neuron simulation\",\"Hodgkin-Huxley type of neuron model\",\"neuron simulation on cloud resources\"]','1'),(5,'publication','cyneuro','pub-rec','[\"neuron simulation on cloud resources\"]','2'),(6,'publication','cyneuro','pub-rec','[\"simulate single neuron model\",\"neuron simulation\",\"Hodgkin-Huxley type of neuron model\",\"neuron simulation on cloud resources\"]','3');
/*!40000 ALTER TABLE `chain_apps` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `client_registry`
--

DROP TABLE IF EXISTS `client_registry`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `client_registry` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `client_name` varchar(500) DEFAULT NULL,
  `client_desc` varchar(1000) DEFAULT NULL,
  `client_key` varchar(200) DEFAULT NULL,
  `user_name` varchar(200) DEFAULT NULL,
  `password` varchar(200) DEFAULT NULL,
  `email_address` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `client_registry`
--

LOCK TABLES `client_registry` WRITE;
/*!40000 ALTER TABLE `client_registry` DISABLE KEYS */;
INSERT INTO `client_registry` VALUES (4,'CyNeuro','CyNeuro goal is to spur computational and cyber neuro research at Mizzou and collaborator institutions via: (i) the development of ‘free’ cyber and software big data tools for neuroscience research, and (ii) facilitation of interactions among physical sciences faculty with big data expertise (engineering, math, statistics, physics) and wet-lab, behavioral and clinical neuro faculty with big data ‘needs’.','cyne-26yx5le3xf','admin','[B@7e13844a','cyneuro@gmail.com'),(7,'KBCommons','KBCommons','kbco-2y9rly4s1s','admin','[B@3e47b4a6','kbcommons@gmail.com'),(8,'Covid-19','Covid-19','covi-bkarx2tauu','admin','[B@59613af2','covid19@gmail.com');
/*!40000 ALTER TABLE `client_registry` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `knowledge_base`
--

DROP TABLE IF EXISTS `knowledge_base`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `knowledge_base` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `description` varchar(500) DEFAULT NULL,
  `source_dtl` varchar(100) DEFAULT NULL,
  `domain_name` varchar(45) DEFAULT NULL,
  `start_dt` varchar(45) DEFAULT NULL,
  `end_dt` varchar(45) DEFAULT NULL,
  `rec_id` int(11) DEFAULT NULL,
  `source_url` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `rec_fkey_idx` (`rec_id`),
  CONSTRAINT `rec_fkey` FOREIGN KEY (`rec_id`) REFERENCES `recommend_apps` (`rec_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `knowledge_base`
--

LOCK TABLES `knowledge_base` WRITE;
/*!40000 ALTER TABLE `knowledge_base` DISABLE KEYS */;
INSERT INTO `knowledge_base` VALUES (13,'Topic Model for Knowledge Discovery','Domain-specific Topic Model for Knowledge Discovery in Computational and Data-Intensive Scientific Communities','github','Domain1,Domain2','12-2019','03-2020',21,'https://storage.googleapis.com/mizzou-cyber-range-script/Lab_1/server_1.tar.gz'),(14,'neuro KnowledgeBase Name','KnowledgeBase is generated from data source from PubMed - U.S. National Library of Medicine','mongoDB','neuroscience','03/01/2019','12/01/2019',26,'mongodb+srv://group:group@final-pwrgy.mongodb.net/neuro?retryWrites=true/neuro'),(17,'Scholar Finder Knowledgebase','Scholars details available for neuroscience knowledge from publication and grant datasets','json data from generated knowledge base from publication abstracts','neuroscience','01/01/2019','08/01/2019',29,'https://storage.googleapis.com/on-time-recommend/knowledgebase/scholar_knowledge.tar.gz');
/*!40000 ALTER TABLE `knowledge_base` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rec_orchestrator_client`
--

DROP TABLE IF EXISTS `rec_orchestrator_client`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rec_orchestrator_client` (
  `orch_id` int(11) NOT NULL AUTO_INCREMENT,
  `client_id` int(11) DEFAULT NULL,
  `rec_id` int(11) DEFAULT NULL,
  `queue_execution_status` varchar(45) DEFAULT NULL,
  `queue_start_date` datetime DEFAULT NULL,
  `queue_end_date` datetime DEFAULT NULL,
  PRIMARY KEY (`orch_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rec_orchestrator_client`
--

LOCK TABLES `rec_orchestrator_client` WRITE;
/*!40000 ALTER TABLE `rec_orchestrator_client` DISABLE KEYS */;
INSERT INTO `rec_orchestrator_client` VALUES (1,4,21,'W','2020-05-27 04:28:05',NULL),(2,4,26,NULL,NULL,NULL),(4,4,29,NULL,NULL,NULL),(5,7,21,NULL,NULL,NULL),(6,8,21,NULL,NULL,NULL),(7,7,26,NULL,NULL,NULL),(8,7,29,NULL,NULL,NULL),(9,7,30,NULL,NULL,NULL),(11,7,35,NULL,NULL,NULL),(12,4,30,NULL,NULL,NULL),(13,4,35,NULL,NULL,NULL),(14,8,26,NULL,NULL,NULL);
/*!40000 ALTER TABLE `rec_orchestrator_client` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rec_orchestrator_process`
--

DROP TABLE IF EXISTS `rec_orchestrator_process`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rec_orchestrator_process` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `client_id` int(11) DEFAULT NULL,
  `rec_id` int(11) DEFAULT NULL,
  `process_id` int(11) DEFAULT NULL,
  `input_config` varchar(1000) DEFAULT NULL,
  `seq_queue_number` int(11) DEFAULT NULL,
  `last_executed` datetime DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  `output` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rec_orchestrator_process`
--

LOCK TABLES `rec_orchestrator_process` WRITE;
/*!40000 ALTER TABLE `rec_orchestrator_process` DISABLE KEYS */;
INSERT INTO `rec_orchestrator_process` VALUES (9,4,21,23,'{\"domain\":\"neuro\"}',1,'2020-05-27 04:28:05','W','dataCollection'),(10,4,21,24,'{\"domain\":\"neuro\",\"operation\":\"vocabulary\"}',2,'2020-05-22 06:26:59','N','dataProcess'),(13,7,21,23,'{\"domain\":\"bio\"}',1,NULL,NULL,NULL),(14,7,21,24,'{\"domain\":\"bio\",\"operation\":\"vocabulary\"}',1,NULL,NULL,NULL),(15,7,21,24,'{\"domain\":\"bio\",\"operation\":\"bag-of-words\"}',1,NULL,NULL,NULL),(18,4,21,24,'{\"domain\":\"neuro\",\"operation\":\"bag-of-words\"}',3,'2020-05-22 06:28:14','N','dataProcess'),(19,4,21,24,'{\"domain\":\"neuro\",\"operation\":\"tool\"}',4,'2020-05-22 06:28:46','N','dataProcess'),(20,4,21,24,'{\"domain\":\"neuro\",\"operation\":\"dataset\"}',5,'2020-05-22 06:29:05','N','dataProcess'),(21,4,21,29,'{\"topic_id\":\"20\",\"level\":\"7\"}',6,NULL,'N',NULL);
/*!40000 ALTER TABLE `rec_orchestrator_process` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rec_process`
--

DROP TABLE IF EXISTS `rec_process`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rec_process` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `rec_id` int(11) DEFAULT NULL,
  `process_name` varchar(100) DEFAULT NULL,
  `api_url` varchar(200) DEFAULT NULL,
  `process_details` varchar(1000) DEFAULT NULL,
  `input_paramater` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rec_process`
--

LOCK TABLES `rec_process` WRITE;
/*!40000 ALTER TABLE `rec_process` DISABLE KEYS */;
INSERT INTO `rec_process` VALUES (23,21,'Data Collection','topic-model-data-col-service/','In the data collecting stage, we collect three types of the dataset: a) papers, we collect papers from specific scientific domains. In the current project, we collect papers from two domains: bioinformatics, neuroscience; b) tools, we collect types of tools; c) datasets, we collect types of datasets. To collect papers, we provide scripts to automatically collect papers from websites; to collect tools or datasets, we need some domain knowledge to collect relevant datasets manually.','[{\"type\":\"text\",\"required\":false,\"label\":\"domain\",\"className\":\"form-control\",\"name\":\"domain\",\"access\":false,\"subtype\":\"text\"}]'),(24,21,'Data Processing','topic-model-data-prc-service/','During the data collecting, you have collected paper texts from journals. In the data processing stage, you need to process raw text datasets for a suitable dataset format for the model. In our model, we use bag-of-words as our model input. Hence, for data processing, we need to transform the raw text format into the bag-of-words format. Besides, in the data processing stage, we also need to generate the whole vocabulary, tool-to-doc, and dataset-to-doc tables. And the last thing, you need to generate vocabulary firstly before generating bag-of-words, tool-to-doc, or dataset-to-doc tables.','[{\"type\":\"text\",\"required\":false,\"label\":\"domain\",\"className\":\"form-control\",\"name\":\"domain\",\"access\":false,\"subtype\":\"text\"},{\"type\":\"text\",\"required\":false,\"label\":\"operation\",\"className\":\"form-control\",\"name\":\"operation\",\"access\":false,\"subtype\":\"text\"}]'),(25,21,'Model Parameters Estimation','topic-model-data-model-service/','The DSTM is a probabilistic graphical model with latent variables. In our model, the latent variables are used to describe the patterns among research topics, tools, and datasets, which are unknown to us in the beginning. The goal of parameters estimation is to estimate these latent variables. In the model, we use the Gibbs sampling algorithm to infer these latent patterns.','[{\"type\":\"text\",\"required\":false,\"label\":\"data_source\",\"className\":\"form-control\",\"name\":\"data_source\",\"access\":false,\"subtype\":\"text\"},{\"type\":\"text\",\"required\":false,\"label\":\"mode\",\"className\":\"form-control\",\"name\":\"mode\",\"access\":false,\"subtype\":\"text\"},{\"type\":\"text\",\"required\":false,\"label\":\"run_mode\",\"className\":\"form-control\",\"name\":\"run_mode\",\"access\":false,\"subtype\":\"text\"},{\"type\":\"text\",\"required\":false,\"label\":\"num_iterations\",\"className\":\"form-control\",\"name\":\"num_iterations\",\"access\":false,\"subtype\":\"text\"},{\"type\":\"text\",\"required\":false,\"label\":\"num_topics\",\"className\":\"form-control\",\"name\":\"num_topics\",\"access\":false,\"subtype\":\"text\"},{\"type\":\"text\",\"required\":false,\"label\":\"save\",\"className\":\"form-control\",\"name\":\"save\",\"access\":false,\"subtype\":\"text\"},{\"type\":\"text\",\"required\":false,\"label\":\"model_folder\",\"className\":\"form-control\",\"name\":\"model_folder\",\"access\":false,\"subtype\":\"text\"}]'),(29,21,'Data Filter','topic-model-filter-model-service/','Filtering data','[{\"type\":\"text\",\"required\":false,\"label\":\"Topic\",\"className\":\"form-control\",\"name\":\"topic_id\",\"access\":false,\"subtype\":\"text\"},{\"type\":\"text\",\"required\":false,\"label\":\"Level of Evidance pyramid\",\"className\":\"form-control\",\"name\":\"level\",\"access\":false,\"subtype\":\"text\"}]');
/*!40000 ALTER TABLE `rec_process` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `recommend_apps`
--

DROP TABLE IF EXISTS `recommend_apps`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `recommend_apps` (
  `rec_id` int(11) NOT NULL AUTO_INCREMENT,
  `app_name` varchar(500) DEFAULT NULL,
  `app_des` varchar(500) DEFAULT NULL,
  `app_input_parameter` text,
  `app_output_template` varchar(100) DEFAULT NULL,
  `port` varchar(45) DEFAULT NULL,
  `api_url` varchar(500) DEFAULT NULL,
  `rec_key` varchar(500) DEFAULT NULL,
  `is_deployed` varchar(45) DEFAULT NULL,
  `host_name` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`rec_id`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recommend_apps`
--

LOCK TABLES `recommend_apps` WRITE;
/*!40000 ALTER TABLE `recommend_apps` DISABLE KEYS */;
INSERT INTO `recommend_apps` VALUES (21,'Topic Model Recommender','Topic Recommender is a system that matches user search word with relavant topics and then recommeds user with tools and datasets that relavant to their reaserach intention.','[{\"type\":\"text\",\"required\":false,\"label\":\"Enter Topic Details\",\"className\":\"form-control\",\"name\":\"text\",\"access\":false,\"subtype\":\"text\"}]','Topic-Model_output.html','8080','topic-model-run-model-service/','Topic-Model','Y','192.12.245.167'),(26,'Publication Recommender','Publication Recommender discovers relevant publications for researchers. We have developed a PubMed article recommendation system, which is based on content-based filtering. This filter is for searching for specific authors, PMID, and titles which also suggest relevant related research publications.','[{\"type\":\"select\",\"required\":false,\"label\":\"<font color=\\\"#212529\\\"><span style=\\\"font-size: 16px; background-color: rgb(248, 249, 250);\\\">Input Type</span></font>\",\"className\":\"form-control\",\"name\":\"type\",\"access\":false,\"multiple\":false,\"values\":[{\"label\":\"PMID\",\"value\":\"PMID\",\"selected\":true},{\"label\":\"Title\",\"value\":\"title\"},{\"label\":\"Author\",\"value\":\"authors\"}]},{\"type\":\"text\",\"required\":false,\"placeholder\":\"Please enter \\\"PMID\\\", \\\"Title\\\", or \\\"Author\\\"...\",\"className\":\"form-control\",\"name\":\"keyword\",\"access\":false,\"subtype\":\"text\"}]','Publication-Recommender_output.html','8102','publication-run-model-service/','Publication-Recommender','Y','127.0.0.1'),(29,'Scholar Finder','ScholarFinder successfully finds scholars who are suitable in accomplishing a research task across a scientific domain.','[{\"type\":\"text\",\"required\":false,\"label\":\"Enter Domain Name\",\"className\":\"form-control\",\"name\":\"topicName\",\"access\":false,\"subtype\":\"text\"}]','scholar-finder_output.html','8103','scholar-run-model-service/','scholar-finder','Y','127.0.0.1'),(30,'Cloud solution Template Recommender','Cloud solution Template Recommender suggests cloud solution to user according to user’s requirement. It will also give information about CPU, memory, networking, cost, duration, etc of instances. According to user template selection, system will assign resources for computation.','[{\"type\":\"select\",\"required\":false,\"label\":\"Required OS\",\"className\":\"form-control\",\"name\":\"req_os\",\"access\":false,\"multiple\":false,\"values\":[{\"label\":\"LINUX\",\"value\":\"LINUX\",\"selected\":true},{\"label\":\"RHEL\",\"value\":\"RHEL\"},{\"label\":\"SLES\",\"value\":\"SLES\"},{\"label\":\"WINDOWS\",\"value\":\"WINDOWS\"}]},{\"type\":\"select\",\"required\":false,\"label\":\"No. of cores or vCPUs you need?\",\"className\":\"form-control\",\"name\":\"req_vCPU\",\"access\":false,\"multiple\":false,\"values\":[{\"label\":\"1\",\"value\":\"1\",\"selected\":true},{\"label\":\"2\",\"value\":\"2\"},{\"label\":\"4\",\"value\":\"4\"}]},{\"type\":\"text\",\"required\":false,\"label\":\"<span style=\\\"color: rgb(34, 34, 34); font-family: Consolas, \\\" lucida=\\\"\\\" console\\\",=\\\"\\\" \\\"courier=\\\"\\\" new\\\",=\\\"\\\" monospace;=\\\"\\\" font-size:=\\\"\\\" 12px;=\\\"\\\" white-space:=\\\"\\\" pre-wrap;\\\"=\\\"\\\">Whats the size of your RAM(GB)</span>\",\"className\":\"form-control\",\"name\":\"req_ram\",\"access\":false,\"subtype\":\"text\"},{\"type\":\"text\",\"required\":false,\"label\":\"Required Network\",\"className\":\"form-control\",\"name\":\"req_network\",\"access\":false,\"subtype\":\"text\"},{\"type\":\"text\",\"required\":false,\"label\":\"Required clock speed(GHz)\",\"className\":\"form-control\",\"name\":\"req_clock\",\"access\":false,\"subtype\":\"text\"},{\"type\":\"select\",\"required\":false,\"label\":\"Do you require dedicated GPU?\",\"className\":\"boolean\",\"name\":\"req_gpu\",\"access\":false,\"multiple\":false,\"values\":[{\"label\":\"Yes\",\"value\":\"true\",\"selected\":true},{\"label\":\"No\",\"value\":\"false\"}]},{\"type\":\"text\",\"required\":false,\"label\":\"<span style=\\\"color: rgb(34, 34, 34); font-family: Consolas, \\\" lucida=\\\"\\\" console\\\",=\\\"\\\" \\\"courier=\\\"\\\" new\\\",=\\\"\\\" monospace;=\\\"\\\" font-size:=\\\"\\\" 12px;=\\\"\\\" white-space:=\\\"\\\" pre-wrap;\\\"=\\\"\\\">Storage Size(GB)</span>\",\"className\":\"form-control\",\"name\":\"req_storage\",\"access\":false,\"subtype\":\"text\"},{\"type\":\"select\",\"required\":false,\"label\":\"<span style=\\\"color: rgb(34, 34, 34); font-family: Consolas, \\\" lucida=\\\"\\\" console\\\",=\\\"\\\" \\\"courier=\\\"\\\" new\\\",=\\\"\\\" monospace;=\\\"\\\" font-size:=\\\"\\\" 12px;=\\\"\\\" white-space:=\\\"\\\" pre-wrap;\\\"=\\\"\\\">Do you need SSD Storage?</span>\",\"className\":\"boolean\",\"name\":\"req_ssd\",\"access\":false,\"multiple\":false,\"values\":[{\"label\":\"Yes\",\"value\":\"true\",\"selected\":true},{\"label\":\"No\",\"value\":\"false\"}]}]','cloud-temp-rec_output.html','8080','cloud-temp-run-model-service/',NULL,'Y','198.248.248.136'),(35,'Jupyter Notebook Recommender','This recommender searches through a group of Jupyter Notebooks and picks various ones based on a keyword search.','[{\"type\":\"text\",\"required\":false,\"label\":\"Jupyter Notebook Input\",\"className\":\"form-control\",\"name\":\"text\",\"access\":false,\"subtype\":\"text\"}]','jupyter-notebook-recommender_output.html','9200','jyupter-run-model-service/','jupyter-notebook-recommender','Y','198.248.248.125');
/*!40000 ALTER TABLE `recommend_apps` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_time` date DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `github_id` varchar(45) DEFAULT NULL,
  `avatar_url` varchar(100) DEFAULT NULL,
  `role` varchar(45) DEFAULT NULL,
  `organization` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,NULL,NULL,NULL,'KomalVekaria0309','47259148','https://avatars2.githubusercontent.com/u/47259148?v=4','STUDENT',NULL),(2,NULL,NULL,NULL,'AbhiGITPar','62188346','https://avatars1.githubusercontent.com/u/62188346?v=4','STUDENT',NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_profile`
--

DROP TABLE IF EXISTS `user_profile`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_profile` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(45) DEFAULT NULL,
  `user_response` varchar(1000) DEFAULT NULL,
  `user_quadrant` varchar(45) DEFAULT NULL,
  `usecase` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_profile`
--

LOCK TABLES `user_profile` WRITE;
/*!40000 ALTER TABLE `user_profile` DISABLE KEYS */;
INSERT INTO `user_profile` VALUES (4,'KOMAL NOVICE','{\"basic\":{\"q4\":\"8\",\"q2\":\"8\"},\"neuro\":{\"q1\":\"2\",\"q2\":\"2\",\"q3\":\"2\",\"q4\":\"2\",\"q5\":\"2\",\"q6\":\"2\",\"q7\":\"2\"},\"hpc\":{\"q1\":\"2\",\"q2\":\"2\",\"q3\":\"2\",\"q4\":\"2\",\"q5\":\"2\"}}','1',NULL),(5,'KOMAL HPC','{\"basic\":{\"q4\":\"8\",\"q2\":\"8\"},\"neuro\":{\"q1\":\"2\",\"q2\":\"2\",\"q3\":\"2\",\"q4\":\"2\",\"q5\":\"2\",\"q6\":\"2\",\"q7\":\"2\"},\"hpc\":{\"q1\":\"4\",\"q2\":\"4\",\"q3\":\"4\",\"q4\":\"4\",\"q5\":\"4\"}}','3',NULL),(6,'KOMAL DOMAIN','{\"basic\":{\"q4\":\"8\",\"q2\":\"8\"},\"neuro\":{\"q1\":\"4\",\"q2\":\"8\",\"q3\":\"4\",\"q4\":\"8\",\"q5\":\"8\",\"q6\":\"8\",\"q7\":\"4\"},\"hpc\":{\"q1\":\"2\",\"q2\":\"2\",\"q3\":\"2\",\"q4\":\"2\",\"q5\":\"2\"}}','2',NULL),(10,'SOUMYA PUROHIT NEURO','{\"basic\":{\"q4\":\"8\",\"q2\":\"8\"},\"neuro\":{\"q1\":\"2\",\"q2\":\"0\",\"q3\":\"0\",\"q4\":\"0\",\"q5\":\"0\",\"q6\":\"0\",\"q7\":\"8\"},\"hpc\":{\"q1\":\"8\",\"q2\":\"8\",\"q3\":\"2\",\"q4\":\"8\",\"q5\":\"8\"}}','3',NULL),(11,'SNIGDHA PASHAM NEURO','{\"basic\":{\"q2\":\"2\"},\"neuro\":{\"q1\":\"0\",\"q2\":\"0\",\"q3\":\"0\",\"q4\":\"0\",\"q5\":\"2\",\"q6\":\"2\",\"q7\":\"4\"},\"hpc\":{\"q1\":\"4\",\"q2\":\"2\",\"q3\":\"4\",\"q4\":\"4\",\"q5\":\"4\"}}','1',NULL),(12,'ASHISH PANDEY NEURO','{\"basic\":{\"q4\":\"8\",\"q2\":\"4\"},\"neuro\":{\"q1\":\"0\",\"q2\":\"0\",\"q3\":\"2\",\"q4\":\"0\",\"q5\":\"2\",\"q6\":\"0\",\"q7\":\"4\"},\"hpc\":{\"q1\":\"8\",\"q2\":\"4\",\"q3\":\"4\",\"q4\":\"8\",\"q5\":\"8\"}}','3',NULL),(13,'SAMAIKYA NEURO','{\"basic\":{\"q3\":\"4\",\"q2\":\"4\"},\"neuro\":{\"q1\":\"0\",\"q2\":\"0\",\"q3\":\"0\",\"q4\":\"0\",\"q5\":\"0\",\"q6\":\"0\",\"q7\":\"4\"},\"hpc\":{\"q1\":\"4\",\"q2\":\"2\",\"q3\":\"2\",\"q4\":\"4\",\"q5\":\"4\"}}','1',NULL),(14,'JACK MARAK NEURO','{\"basic\":{\"q1\":\"0\",\"q2\":\"0\"},\"neuro\":{\"q1\":\"0\",\"q2\":\"0\",\"q3\":\"0\",\"q4\":\"0\",\"q5\":\"0\",\"q6\":\"0\",\"q7\":\"4\"},\"hpc\":{\"q1\":\"4\",\"q2\":\"2\",\"q3\":\"0\",\"q4\":\"4\",\"q5\":\"4\"}}','1',NULL),(15,'TYLER BANKS','{\"basic\":{\"q3\":\"4\",\"q2\":\"4\"},\"neuro\":{\"q1\":\"4\",\"q2\":\"8\",\"q3\":\"8\",\"q4\":\"8\",\"q5\":\"4\",\"q6\":\"4\",\"q7\":\"8\"},\"hpc\":{\"q1\":\"8\",\"q2\":\"4\",\"q3\":\"4\",\"q4\":\"8\",\"q5\":\"8\"}}','4',NULL),(16,'SONGJIE WANG NEURO','{\"basic\":{\"q3\":\"4\",\"q2\":\"8\"},\"neuro\":{\"q1\":\"4\",\"q2\":\"4\",\"q3\":\"4\",\"q4\":\"0\",\"q5\":\"2\",\"q6\":\"0\",\"q7\":\"4\"},\"hpc\":{\"q1\":\"8\",\"q2\":\"8\",\"q3\":\"8\",\"q4\":\"4\",\"q5\":\"8\"}}','3',NULL),(17,'LINQUAN LYU NEURO','{\"basic\":{\"q3\":\"4\",\"q2\":\"0\"},\"neuro\":{\"q7\":\"4\",\"q6\":\"4\",\"q5\":\"0\",\"q4\":\"0\",\"q3\":\"0\",\"q2\":\"0\",\"q1\":\"0\"},\"hpc\":{\"q5\":\"4\",\"q4\":\"4\",\"q3\":\"0\",\"q1\":\"8\",\"q2\":\"2\"}}','1',NULL),(18,'RAMYA NEURO','{\"basic\":{\"q2\":\"4\"},\"neuro\":{\"q1\":\"0\",\"q2\":\"0\",\"q3\":\"0\",\"q4\":\"0\",\"q5\":\"2\",\"q6\":\"2\",\"q7\":\"4\"},\"hpc\":{\"q1\":\"4\",\"q2\":\"2\",\"q3\":\"2\",\"q4\":\"2\",\"q5\":\"4\"}}','1',NULL),(19,'ROLAND NEURO','{\"basic\":{\"q2\":\"2\",\"q3\":\"4\"},\"neuro\":{\"q1\":\"0\",\"q2\":\"0\",\"q3\":\"0\",\"q4\":\"0\",\"q5\":\"0\",\"q6\":\"0\",\"q7\":\"4\"},\"hpc\":{\"q1\":\"4\",\"q2\":\"4\",\"q3\":\"0\",\"q4\":\"4\",\"q5\":\"4\"}}','1',NULL),(20,'HANK STAFFORD NEURO','{\"basic\":{\"q3\":\"4\",\"q2\":\"4\"},\"neuro\":{\"q1\":\"4\",\"q2\":\"2\",\"q3\":\"4\",\"q4\":\"4\",\"q5\":\"2\",\"q6\":\"0\",\"q7\":\"2\"},\"hpc\":{\"q1\":\"4\",\"q2\":\"4\",\"q3\":\"2\",\"q4\":\"4\",\"q5\":\"4\"}}','1',NULL),(23,'KOMAL NOVICE BIO','{\"basic\":{\"q4\":\"8\",\"q2\":\"2\"},\"hpc\":{\"q1\":\"2\",\"q2\":\"0\",\"q3\":\"2\",\"q4\":\"0\",\"q5\":\"2\"},\"bio\":{\"q1\":\"2\",\"q2\":\"0\",\"q3\":\"2\",\"q4\":\"2\",\"q5\":\"0\"}}','1','BIO'),(24,'KOMAL HPC BIO','{\"basic\":{\"q4\":\"8\",\"q2\":\"8\"},\"hpc\":{\"q1\":\"8\",\"q2\":\"4\",\"q3\":\"8\",\"q4\":\"4\",\"q5\":\"8\"},\"bio\":{\"q1\":\"2\",\"q2\":\"2\",\"q3\":\"0\",\"q4\":\"0\",\"q5\":\"0\"}}','3','BIO'),(25,'KOMAL DOMAIN BIO','{\"basic\":{\"q4\":\"8\",\"q2\":\"8\"},\"hpc\":{\"q1\":\"2\",\"q2\":\"2\",\"q3\":\"0\",\"q4\":\"0\",\"q5\":\"2\"},\"bio\":{\"q1\":\"8\",\"q2\":\"4\",\"q3\":\"4\",\"q4\":\"8\",\"q5\":\"4\"}}','2','BIO'),(26,'KOMAL BIO','{\"basic\":{\"q4\":\"8\",\"q2\":\"8\"},\"hpc\":{\"q1\":\"8\",\"q2\":\"8\",\"q3\":\"8\",\"q4\":\"8\",\"q5\":\"8\"},\"bio\":{\"q1\":\"8\",\"q2\":\"8\",\"q3\":\"8\",\"q4\":\"8\",\"q5\":\"8\"}}','4','BIO'),(27,'ASHISH PANDEY','{\"basic\":{\"q4\":\"8\",\"q3\":\"4\",\"q2\":\"4\"},\"hpc\":{\"q1\":\"8\",\"q2\":\"4\",\"q3\":\"4\",\"q4\":\"8\",\"q5\":\"8\"},\"bio\":{\"q1\":\"8\",\"q2\":\"4\",\"q3\":\"4\",\"q4\":\"4\",\"q5\":\"4\"}}','4','BIO'),(28,'KOMAL BIO1',NULL,NULL,NULL),(29,'ZHEN LYU','{\"basic\":{\"q4\":\"8\",\"q2\":\"4\"},\"hpc\":{\"q1\":\"4\",\"q2\":\"4\",\"q3\":\"4\",\"q4\":\"4\",\"q5\":\"4\"},\"bio\":{\"q1\":\"8\",\"q2\":\"8\",\"q3\":\"4\",\"q4\":\"4\",\"q5\":\"4\"}}','4','BIO'),(30,'DEMO23',NULL,NULL,NULL),(31,'DEMO',NULL,NULL,NULL),(32,'KBCOMMONS NOVICE',NULL,'1','BIO'),(33,'KBCOMMONS DOMAIN',NULL,'2','BIO'),(34,'CYNEURO NOVICE',NULL,'1',NULL),(35,'CYNEURO DOMAIN',NULL,'2',NULL),(36,'KOMAL DEMO',NULL,NULL,NULL),(37,'KOMAL NOVICE',NULL,NULL,'NULL'),(38,'KOMAL NOVICE','{\"basic\":{\"q3\":\"4\",\"q2\":\"4\"},\"hpc\":{\"q1\":\"0\",\"q2\":\"0\",\"q3\":\"0\",\"q4\":\"0\",\"q5\":\"0\"},\"neuro\":{\"q1\":\"0\",\"q2\":\"0\",\"q3\":\"0\",\"q4\":\"0\",\"q5\":\"0\",\"q6\":\"0\",\"q7\":\"0\"}}','1','NEURO');
/*!40000 ALTER TABLE `user_profile` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-05-26 23:28:11
