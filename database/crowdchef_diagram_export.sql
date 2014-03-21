SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

DROP SCHEMA IF EXISTS `crowdchef` ;
CREATE SCHEMA IF NOT EXISTS `crowdchef` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `crowdchef` ;

-- -----------------------------------------------------
-- Table `crowdchef`.`app_user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `crowdchef`.`app_user` ;

CREATE TABLE IF NOT EXISTS `crowdchef`.`app_user` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(16) NOT NULL,
  `password` VARCHAR(32) NOT NULL,
  `create_time` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC));


-- -----------------------------------------------------
-- Table `crowdchef`.`recipe`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `crowdchef`.`recipe` ;

CREATE TABLE IF NOT EXISTS `crowdchef`.`recipe` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NULL,
  `description` VARCHAR(255) NULL,
  `directions` VARCHAR(255) NULL,
  `tags` VARCHAR(255) NULL,
  `url` VARCHAR(255) NULL,
  `image` BLOB NULL,
  `image_url` VARCHAR(255) NULL,
  `create_time` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  `user_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_recipe_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_recipe_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `crowdchef`.`app_user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `crowdchef`.`ingredient`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `crowdchef`.`ingredient` ;

CREATE TABLE IF NOT EXISTS `crowdchef`.`ingredient` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `recipe_id` BIGINT NOT NULL,
  `name` VARCHAR(100) NULL,
  `description` VARCHAR(255) NULL,
  `quantity` VARCHAR(45) NULL,
  `ord` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_ingredient_recipe1_idx` (`recipe_id` ASC),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC),
  CONSTRAINT `fk_ingredient_recipe1`
    FOREIGN KEY (`recipe_id`)
    REFERENCES `crowdchef`.`recipe` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `crowdchef`.`location`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `crowdchef`.`location` ;

CREATE TABLE IF NOT EXISTS `crowdchef`.`location` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `store` VARCHAR(100) NULL,
  `store_url` VARCHAR(255) NULL,
  `geo_location` VARCHAR(45) NULL,
  `city` VARCHAR(45) NULL,
  `country` VARCHAR(45) NULL,
  `create_time` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  `user_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_location_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_location_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `crowdchef`.`app_user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `crowdchef`.`reviews`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `crowdchef`.`reviews` ;

CREATE TABLE IF NOT EXISTS `crowdchef`.`reviews` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `recipe_id` BIGINT NOT NULL,
  `description` VARCHAR(255) NULL,
  `create_time` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  `user_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_reviews_recipe1_idx` (`recipe_id` ASC),
  INDEX `fk_reviews_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_reviews_recipe1`
    FOREIGN KEY (`recipe_id`)
    REFERENCES `crowdchef`.`recipe` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_reviews_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `crowdchef`.`app_user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `crowdchef`.`taste_category`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `crowdchef`.`taste_category` ;

CREATE TABLE IF NOT EXISTS `crowdchef`.`taste_category` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `type` VARCHAR(1) NULL,
  `name` VARCHAR(45) NULL,
  `ord` INT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `crowdchef`.`taste_score`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `crowdchef`.`taste_score` ;

CREATE TABLE IF NOT EXISTS `crowdchef`.`taste_score` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `taste_category_id` BIGINT NOT NULL,
  `recipe_id` BIGINT NOT NULL,
  `score` INT NULL,
  `create_time` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  `user_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_taste_score_recipe1_idx` (`recipe_id` ASC),
  INDEX `fk_taste_score_user1_idx` (`user_id` ASC),
  INDEX `fk_taste_score_taste_category1_idx` (`taste_category_id` ASC),
  CONSTRAINT `fk_taste_score_recipe1`
    FOREIGN KEY (`recipe_id`)
    REFERENCES `crowdchef`.`recipe` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_taste_score_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `crowdchef`.`app_user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_taste_score_taste_category1`
    FOREIGN KEY (`taste_category_id`)
    REFERENCES `crowdchef`.`taste_category` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `crowdchef`.`wine_instance`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `crowdchef`.`wine_instance` ;

CREATE TABLE IF NOT EXISTS `crowdchef`.`wine_instance` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `brand` VARCHAR(45) NULL,
  `name` VARCHAR(100) NULL,
  `description` VARCHAR(255) NULL,
  `create_time` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  `user_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_wine_instance_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_wine_instance_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `crowdchef`.`app_user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `crowdchef`.`ingr_instance`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `crowdchef`.`ingr_instance` ;

CREATE TABLE IF NOT EXISTS `crowdchef`.`ingr_instance` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `brand` VARCHAR(45) NULL,
  `name` VARCHAR(100) NULL,
  `description` VARCHAR(255) NULL,
  `create_time` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  `user_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_ingr_instance_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_ingr_instance_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `crowdchef`.`app_user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `crowdchef`.`ingredient_has_ingr_instance`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `crowdchef`.`ingredient_has_ingr_instance` ;

CREATE TABLE IF NOT EXISTS `crowdchef`.`ingredient_has_ingr_instance` (
  `Ingredient_id` BIGINT NOT NULL,
  `IngredientInstance_id` BIGINT NOT NULL,
  `create_time` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  `user_id` BIGINT NOT NULL,
  PRIMARY KEY (`Ingredient_id`, `IngredientInstance_id`),
  INDEX `fk_Ingredient_has_IngredientInstance_IngredientInstance1_idx` (`IngredientInstance_id` ASC),
  INDEX `fk_Ingredient_has_IngredientInstance_Ingredient1_idx` (`Ingredient_id` ASC),
  INDEX `fk_ingredient_has_ingr_instance_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_Ingredient_has_IngredientInstance_Ingredient1`
    FOREIGN KEY (`Ingredient_id`)
    REFERENCES `crowdchef`.`ingredient` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Ingredient_has_IngredientInstance_IngredientInstance1`
    FOREIGN KEY (`IngredientInstance_id`)
    REFERENCES `crowdchef`.`ingr_instance` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_ingredient_has_ingr_instance_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `crowdchef`.`app_user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `crowdchef`.`ingr_instance_ratings`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `crowdchef`.`ingr_instance_ratings` ;

CREATE TABLE IF NOT EXISTS `crowdchef`.`ingr_instance_ratings` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `ingredient_has_ingr_instance_Ingredient_id` BIGINT NOT NULL,
  `ingredient_has_ingr_instance_IngredientInstance_id` BIGINT NOT NULL,
  `create_time` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  `user_id` BIGINT NOT NULL,
  INDEX `fk_ingr_instance_ratings_ingredient_has_ingr_instance1_idx` (`ingredient_has_ingr_instance_Ingredient_id` ASC, `ingredient_has_ingr_instance_IngredientInstance_id` ASC),
  PRIMARY KEY (`id`),
  INDEX `fk_ingr_instance_ratings_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_ingr_instance_ratings_ingredient_has_ingr_instance1`
    FOREIGN KEY (`ingredient_has_ingr_instance_Ingredient_id` , `ingredient_has_ingr_instance_IngredientInstance_id`)
    REFERENCES `crowdchef`.`ingredient_has_ingr_instance` (`Ingredient_id` , `IngredientInstance_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_ingr_instance_ratings_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `crowdchef`.`app_user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `crowdchef`.`wine`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `crowdchef`.`wine` ;

CREATE TABLE IF NOT EXISTS `crowdchef`.`wine` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `recipe_id` BIGINT NOT NULL,
  `name` VARCHAR(100) NULL,
  `description` VARCHAR(255) NULL,
  `create_time` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  `user_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_wine_recipe1_idx` (`recipe_id` ASC),
  INDEX `fk_wine_user1_idx` (`user_id` ASC),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC),
  CONSTRAINT `fk_wine_recipe1`
    FOREIGN KEY (`recipe_id`)
    REFERENCES `crowdchef`.`recipe` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_wine_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `crowdchef`.`app_user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `crowdchef`.`wine_has_wine_instance`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `crowdchef`.`wine_has_wine_instance` ;

CREATE TABLE IF NOT EXISTS `crowdchef`.`wine_has_wine_instance` (
  `wine_id` BIGINT NOT NULL,
  `wine_instance_id` BIGINT NOT NULL,
  `create_time` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  `user_id` BIGINT NOT NULL,
  PRIMARY KEY (`wine_id`, `wine_instance_id`),
  INDEX `fk_wine_has_wine_instance_wine_instance1_idx` (`wine_instance_id` ASC),
  INDEX `fk_wine_has_wine_instance_wine1_idx` (`wine_id` ASC),
  INDEX `fk_wine_has_wine_instance_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_wine_has_wine_instance_wine1`
    FOREIGN KEY (`wine_id`)
    REFERENCES `crowdchef`.`wine` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_wine_has_wine_instance_wine_instance1`
    FOREIGN KEY (`wine_instance_id`)
    REFERENCES `crowdchef`.`wine_instance` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_wine_has_wine_instance_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `crowdchef`.`app_user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `crowdchef`.`wine_instance_has_location`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `crowdchef`.`wine_instance_has_location` ;

CREATE TABLE IF NOT EXISTS `crowdchef`.`wine_instance_has_location` (
  `wine_instance_id` BIGINT NOT NULL,
  `location_id` INT NOT NULL,
  `create_time` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  `user_id` BIGINT NOT NULL,
  PRIMARY KEY (`wine_instance_id`, `location_id`),
  INDEX `fk_wine_instance_has_location_location1_idx` (`location_id` ASC),
  INDEX `fk_wine_instance_has_location_wine_instance1_idx` (`wine_instance_id` ASC),
  INDEX `fk_wine_instance_has_location_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_wine_instance_has_location_wine_instance1`
    FOREIGN KEY (`wine_instance_id`)
    REFERENCES `crowdchef`.`wine_instance` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_wine_instance_has_location_location1`
    FOREIGN KEY (`location_id`)
    REFERENCES `crowdchef`.`location` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_wine_instance_has_location_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `crowdchef`.`app_user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `crowdchef`.`wine_instance_ratings`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `crowdchef`.`wine_instance_ratings` ;

CREATE TABLE IF NOT EXISTS `crowdchef`.`wine_instance_ratings` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `wine_has_wine_instance_wine_id` BIGINT NOT NULL,
  `wine_has_wine_instance_wine_instance_id` BIGINT NOT NULL,
  `create_time` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  `user_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`, `wine_has_wine_instance_wine_id`, `wine_has_wine_instance_wine_instance_id`),
  INDEX `fk_wine_instance_ratings_wine_has_wine_instance1_idx` (`wine_has_wine_instance_wine_id` ASC, `wine_has_wine_instance_wine_instance_id` ASC),
  INDEX `fk_wine_instance_ratings_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_wine_instance_ratings_wine_has_wine_instance1`
    FOREIGN KEY (`wine_has_wine_instance_wine_id` , `wine_has_wine_instance_wine_instance_id`)
    REFERENCES `crowdchef`.`wine_has_wine_instance` (`wine_id` , `wine_instance_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_wine_instance_ratings_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `crowdchef`.`app_user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `crowdchef`.`ingr_instance_has_location`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `crowdchef`.`ingr_instance_has_location` ;

CREATE TABLE IF NOT EXISTS `crowdchef`.`ingr_instance_has_location` (
  `ingr_instance_id` BIGINT NOT NULL,
  `location_id` INT NOT NULL,
  `create_time` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  `user_id` BIGINT NOT NULL,
  PRIMARY KEY (`ingr_instance_id`, `location_id`),
  INDEX `fk_ingr_instance_has_location_location1_idx` (`location_id` ASC),
  INDEX `fk_ingr_instance_has_location_ingr_instance1_idx` (`ingr_instance_id` ASC),
  INDEX `fk_ingr_instance_has_location_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_ingr_instance_has_location_ingr_instance1`
    FOREIGN KEY (`ingr_instance_id`)
    REFERENCES `crowdchef`.`ingr_instance` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_ingr_instance_has_location_location1`
    FOREIGN KEY (`location_id`)
    REFERENCES `crowdchef`.`location` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_ingr_instance_has_location_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `crowdchef`.`app_user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `crowdchef`.`user_info`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `crowdchef`.`user_info` ;

CREATE TABLE IF NOT EXISTS `crowdchef`.`user_info` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `user_id` BIGINT NOT NULL,
  `email` VARCHAR(255) NULL,
  `address` VARCHAR(255) NULL,
  `city` VARCHAR(45) NULL,
  `country` VARCHAR(45) NULL,
  INDEX `fk_user_info_user1_idx` (`user_id` ASC),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC),
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_user_info_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `crowdchef`.`app_user` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `crowdchef`.`badge`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `crowdchef`.`badge` ;

CREATE TABLE IF NOT EXISTS `crowdchef`.`badge` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `description` VARCHAR(255) NULL,
  `image` BLOB NULL,
  `ord` INT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `crowdchef`.`user_has_badge`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `crowdchef`.`user_has_badge` ;

CREATE TABLE IF NOT EXISTS `crowdchef`.`user_has_badge` (
  `user_id` BIGINT NOT NULL,
  `badge_id` BIGINT NOT NULL,
  `create_time` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`user_id`, `badge_id`),
  INDEX `fk_user_has_badge_badge1_idx` (`badge_id` ASC),
  INDEX `fk_user_has_badge_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_user_has_badge_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `crowdchef`.`app_user` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_user_has_badge_badge1`
    FOREIGN KEY (`badge_id`)
    REFERENCES `crowdchef`.`badge` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);


-- -----------------------------------------------------
-- Table `crowdchef`.`recipe_rating`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `crowdchef`.`recipe_rating` ;

CREATE TABLE IF NOT EXISTS `crowdchef`.`recipe_rating` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `recipe_id` BIGINT NOT NULL,
  `rating` INT NOT NULL,
  `user_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_recipe_rating_recipe1_idx` (`recipe_id` ASC),
  INDEX `fk_recipe_rating_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_recipe_rating_recipe1`
    FOREIGN KEY (`recipe_id`)
    REFERENCES `crowdchef`.`recipe` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_recipe_rating_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `crowdchef`.`app_user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
