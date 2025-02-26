CREATE TABLE IF NOT EXISTS `contacts` (
  `contact_id` int AUTO_INCREMENT  PRIMARY KEY,
  `name` varchar(100) NOT NULL,
  `mobileno` varchar(10) NOT NULL,
  `email` varchar(100) NOT NULL,
  `subject` varchar(100) NOT NULL,
  `message` varchar(500) NOT NULL,
  `status` varchar(10) NOT NULL,
  `created_at` TIMESTAMP NOT NULL,
  `created_by` varchar(50) NOT NULL,
  `updated_at` TIMESTAMP DEFAULT NULL,
  `updated_by` varchar(50) DEFAULT NULL
);
CREATE TABLE IF NOT EXISTS `holidays` (
  `id` int AUTO_INCREMENT  PRIMARY KEY,
  `holiday_day` varchar(20) UNIQUE NOT NULL,
  `type` varchar(10) NOT NULL,
  `reason` varchar(50) NOT NULL,
  `created_at` TIMESTAMP NOT NULL,
  `created_by` varchar(50) NOT NULL,
  `updated_at` TIMESTAMP DEFAULT NULL,
  `updated_by` varchar(50) DEFAULT NULL
);
-- this query will be executed only once when the application is started for `H2` database, this is one way other way is @see{config/DataLoader.java}