CREATE DATABASE IF NOT EXISTS `booking`;

USE `booking`;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `mobile` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ;

--
-- Table structure for table `movies`
--

DROP TABLE IF EXISTS `movies`;

CREATE TABLE `movies` (
  `id` int NOT NULL AUTO_INCREMENT,
  `language` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `release_date` date NOT NULL,
  PRIMARY KEY (`id`)
) ;


--
-- Table structure for table `theaters`
--

DROP TABLE IF EXISTS `theaters`;

CREATE TABLE `theaters` (
  `id` int NOT NULL AUTO_INCREMENT,
  `address` varchar(255) NOT NULL,
  `city` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ;

--
-- Table structure for table `theater_seats`
--

DROP TABLE IF EXISTS `theater_seats`;

CREATE TABLE `theater_seats` (
  `id` int NOT NULL AUTO_INCREMENT,
  `rate` int NOT NULL,
  `seat_number` varchar(255) NOT NULL,
  `theater_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKtheater_id` (`theater_id`),
  CONSTRAINT `FKtheater_id` FOREIGN KEY (`theater_id`) REFERENCES `theaters` (`id`)
) ;

--
-- Table structure for table `shows`
--

DROP TABLE IF EXISTS `shows`;

CREATE TABLE `shows` (
  `id` int NOT NULL AUTO_INCREMENT,
  `created_at` datetime DEFAULT NULL,
  `rate_multiplier` float(2,1) NOT NULL DEFAULT '1.0',
  `show_date` date NOT NULL,
  `show_time` time NOT NULL,
  `updated_at` datetime DEFAULT NULL,
  `movie_id` int DEFAULT NULL,
  `theater_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKmovie_id` (`movie_id`),
  KEY `FK2theater_id` (`theater_id`),
  CONSTRAINT `FK2theater_id` FOREIGN KEY (`theater_id`) REFERENCES `theaters` (`id`),
  CONSTRAINT `FKmovie_id` FOREIGN KEY (`movie_id`) REFERENCES `movies` (`id`)
) ;


--
-- Table structure for table `tickets`
--

DROP TABLE IF EXISTS `tickets`;

CREATE TABLE `tickets` (
  `id` int NOT NULL AUTO_INCREMENT,
  `alloted_seats` varchar(255) NOT NULL,
  `amount` double NOT NULL,
  `payment_status` boolean NOT NULL DEFAULT 0,
  `booked_at` datetime NOT NULL,
  `show_id` int(20) DEFAULT NULL,
  `user_id` int(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKshow_id` (`show_id`),
  KEY `FKuser_id` (`user_id`),
  CONSTRAINT `FKuser_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKshow_id` FOREIGN KEY (`show_id`) REFERENCES `shows` (`id`)
) ;

--
-- Table structure for table `show_seats`
--

DROP TABLE IF EXISTS `show_seats`;

CREATE TABLE `show_seats` (
  `id` int NOT NULL AUTO_INCREMENT,
  `is_booked` boolean NOT NULL DEFAULT 0,
  `booked_at` datetime DEFAULT NULL,
  `rate` int NOT NULL,
  `seat_number` varchar(255) NOT NULL,
  `seat_type` varchar(255) NOT NULL,
  `show_id` int DEFAULT NULL,
  `ticket_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK1show_id` (`show_id`),
  KEY `FKticket_id` (`ticket_id`),
  CONSTRAINT `FK1show_id` FOREIGN KEY (`show_id`) REFERENCES `shows` (`id`),
  CONSTRAINT `FKticket_id` FOREIGN KEY (`ticket_id`) REFERENCES `tickets` (`id`)
);
