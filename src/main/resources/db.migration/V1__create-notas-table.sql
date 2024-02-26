CREATE TABLE `notas` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `content` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `notes_type` enum('NOTES','TASK') DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
)