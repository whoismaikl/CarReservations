SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `CarRentService` DEFAULT CHARACTER SET utf8 ;
USE `CarRentService`;

DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (

`Id` BIGINT NOT NULL AUTO_INCREMENT,
`Name` CHAR(30) NULL DEFAULT NULL,
`Surname` CHAR(50) NULL DEFAULT NULL,
`CurrentReservationId` BIGINT DEFAULT NULL,
`TimeAdded` datetime,
`UserAdded` CHAR(30) NULL DEFAULT NULL,
`TimeModified` datetime,
`UserModified` CHAR(30) NULL DEFAULT NULL,

PRIMARY KEY (`Id`)
);

DROP TABLE IF EXISTS `cars`;
CREATE TABLE `cars` (

`Id` BIGINT NOT NULL AUTO_INCREMENT,
`Brand` CHAR(30) NULL DEFAULT NULL,
`Model` CHAR(50) NULL DEFAULT NULL,
`RegistrationNumber` CHAR(10) NULL DEFAULT NULL,
`CurrentReservationId` BIGINT,
`TimeAdded` datetime,
`UserAdded` CHAR(30) NULL DEFAULT NULL,
`TimeModified` datetime,
`UserModified` CHAR(30) NULL DEFAULT NULL,

PRIMARY KEY (`Id`)
);

DROP TABLE IF EXISTS `reservations`;
CREATE TABLE `reservations` (

`Id` BIGINT NOT NULL AUTO_INCREMENT,
`CarId` BIGINT,
`UserId` BIGINT,
`ReservedFrom` datetime,
`ReservedTill` datetime,
`TimeAdded` datetime,
`UserAdded` CHAR(30) NULL DEFAULT NULL,
`TimeModified` datetime,
`UserModified` CHAR(30) NULL DEFAULT NULL,

PRIMARY KEY (`Id`)
);

DROP TABLE IF EXISTS `reservationshistory`;
CREATE TABLE `reservationshistory` (

`Id` BIGINT NOT NULL AUTO_INCREMENT,
`ReservationId` BIGINT,
`CarId` BIGINT,
`UserId` BIGINT,
`ReservedFrom` datetime,
`ReservedTill` datetime,
`TimeAdded` datetime,
`UserAdded` CHAR(30) NULL DEFAULT NULL,
`TimeModified` datetime,
`UserModified` CHAR(30) NULL DEFAULT NULL,

PRIMARY KEY (`Id`)
);


ALTER TABLE `reservations` ADD FOREIGN KEY (UserId) REFERENCES `users` (`Id`) ON DELETE CASCADE;
ALTER TABLE `reservations` ADD FOREIGN KEY (CarId) REFERENCES `cars` (`Id`) ON DELETE CASCADE;

ALTER TABLE `reservationshistory` ADD FOREIGN KEY (ReservationId) REFERENCES `reservations` (`Id`) ON DELETE CASCADE;
ALTER TABLE `reservationshistory` ADD FOREIGN KEY (UserId) REFERENCES `users` (`Id`) ON DELETE CASCADE;
ALTER TABLE `reservationshistory` ADD FOREIGN KEY (CarId) REFERENCES `cars` (`Id`) ON DELETE CASCADE;
