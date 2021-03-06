SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema DiscoverAC
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema DiscoverAC
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `DiscoverAC` DEFAULT CHARACTER SET utf8 COLLATE utf8_bin ;
USE `DiscoverAC` ;

-- -----------------------------------------------------
-- Table `DiscoverAC`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `DiscoverAC`.`user` ;

CREATE TABLE IF NOT EXISTS `DiscoverAC`.`user` (
  `userId` VARCHAR(45) NOT NULL,
  `userName` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `firstName` VARCHAR(45) NOT NULL,
  `lastName` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `role` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`userId`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `DiscoverAC`.`bulletin`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `DiscoverAC`.`bulletin` ;

CREATE TABLE IF NOT EXISTS `DiscoverAC`.`bulletin` (
  `bulletinId` VARCHAR(45) NOT NULL,
  `bulletinName` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`bulletinId`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `DiscoverAC`.`post`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `DiscoverAC`.`post` ;

CREATE TABLE IF NOT EXISTS `DiscoverAC`.`post` (
  `postId` VARCHAR(45) NOT NULL,
  `title` VARCHAR(45) NOT NULL,
  `description` TEXT NOT NULL,
  `url` TEXT NOT NULL,
  `postDate` DATE NOT NULL,
  `bulletinId` VARCHAR(45) NOT NULL,
  `authorId` VARCHAR(45) NOT NULL,
  `postcol` VARCHAR(45) NULL,
  PRIMARY KEY (`postId`),
  INDEX `fk_post_bulletin_idx` (`bulletinId` ASC) VISIBLE,
  INDEX `fk_post_user_idx` (`authorId` ASC) INVISIBLE,
  CONSTRAINT `fk_post_bulletin`
    FOREIGN KEY (`bulletinId`)
    REFERENCES `DiscoverAC`.`bulletin` (`bulletinId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_post_user`
    FOREIGN KEY (`authorId`)
    REFERENCES `DiscoverAC`.`user` (`userId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
