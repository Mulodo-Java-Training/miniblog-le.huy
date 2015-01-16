-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema miniblog
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema miniblog
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `miniblog` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `miniblog` ;

-- -----------------------------------------------------
-- Table `miniblog`.`users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `miniblog`.`users` ;

CREATE TABLE IF NOT EXISTS `miniblog`.`users` (
  `id` INT(16) UNSIGNED NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(32) CHARACTER SET 'utf8' NOT NULL,
  `password` VARCHAR(72) NOT NULL,
  `lastname` VARCHAR(32) NOT NULL,
  `firstname` VARCHAR(32) CHARACTER SET 'utf8' NOT NULL,
  `email` VARCHAR(254) NOT NULL,
  `last_login` TIMESTAMP(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `login_hash` VARCHAR(256) NOT NULL,
  `create_at` TIMESTAMP(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modified_at` TIMESTAMP(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  INDEX `username` (`username` ASC),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `miniblog`.`posts`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `miniblog`.`posts` ;

CREATE TABLE IF NOT EXISTS `miniblog`.`posts` (
  `id` INT(64) UNSIGNED NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(100) NOT NULL,
  `content` VARCHAR(2048) CHARACTER SET 'utf8' NOT NULL,
  `created_at` TIMESTAMP(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modified_at` TIMESTAMP(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `status` TINYINT(1) NOT NULL,
  `users_id` INT(16) UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_posts_users1_idx` (`users_id` ASC),
  UNIQUE INDEX `title_UNIQUE` (`title` ASC),
  CONSTRAINT `fk_posts_users1`
    FOREIGN KEY (`users_id`)
    REFERENCES `miniblog`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `miniblog`.`comments`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `miniblog`.`comments` ;

CREATE TABLE IF NOT EXISTS `miniblog`.`comments` (
  `id` INT(64) UNSIGNED NOT NULL AUTO_INCREMENT,
  `comment` VARCHAR(254) CHARACTER SET 'latin5' COLLATE 'latin5_bin' NOT NULL,
  `posts_id` INT(64) UNSIGNED NOT NULL,
  `users_id` INT(16) UNSIGNED NOT NULL,
  `create_at` TIMESTAMP(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modified_at` TIMESTAMP(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  INDEX `fk_comments_posts1_idx` (`posts_id` ASC),
  INDEX `fk_comments_users1_idx` (`users_id` ASC),
  CONSTRAINT `fk_comments_posts1`
    FOREIGN KEY (`posts_id`)
    REFERENCES `miniblog`.`posts` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_comments_users1`
    FOREIGN KEY (`users_id`)
    REFERENCES `miniblog`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

USE `miniblog`;

DELIMITER $$

USE `miniblog`$$
DROP TRIGGER IF EXISTS `miniblog`.`users_BEFORE_UPDATE` $$
USE `miniblog`$$
CREATE DEFINER = CURRENT_USER TRIGGER `miniblog`.`users_BEFORE_UPDATE` BEFORE UPDATE ON `users` FOR EACH ROW BEGIN
    IF OLD.password != NEW.password THEN
     SET NEW.modified_at = NOW();
    END IF;
    IF OLD.lastname != NEW.lastname THEN
     SET NEW.modified_at = NOW();
    END IF;
    IF OLD.firstname != NEW.firstname THEN
     SET NEW.modified_at = NOW();
    END IF;
END;$$


USE `miniblog`$$
DROP TRIGGER IF EXISTS `miniblog`.`posts_BEFORE_UPDATE` $$
USE `miniblog`$$
CREATE DEFINER = CURRENT_USER TRIGGER `miniblog`.`posts_BEFORE_UPDATE` BEFORE UPDATE ON `posts` FOR EACH ROW BEGIN
    IF OLD.content != NEW.content THEN
     SET NEW.modified_at = NOW();
    END IF;
    IF OLD.title != NEW.title THEN
     SET NEW.modified_at = NOW();
    END IF;
    IF OLD.status != NEW.status THEN
     SET NEW.modified_at = NOW();
    END IF;
END;
    $$


USE `miniblog`$$
DROP TRIGGER IF EXISTS `miniblog`.`comments_BEFORE_UPDATE` $$
USE `miniblog`$$
CREATE DEFINER = CURRENT_USER TRIGGER `miniblog`.`comments_BEFORE_UPDATE` BEFORE UPDATE ON `comments` FOR EACH ROW  BEGIN
    IF OLD.comment != NEW.comment THEN
     SET NEW.modified_at = NOW();
    END IF;
END;
    $$


DELIMITER ;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
