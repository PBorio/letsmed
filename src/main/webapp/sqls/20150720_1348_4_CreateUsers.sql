CREATE TABLE `letsmed`.`users` (
  `id` INT NOT NULL,
  `name` VARCHAR(255) NOT NULL,
  `role_id` INT NULL,
  `login` VARCHAR(255) NULL,
  `password` VARCHAR(255) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_user_role_idx` (`role_id` ASC),
  CONSTRAINT `fk_user_role`
    FOREIGN KEY (`role_id`)
    REFERENCES `letsmed`.`roles` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

ALTER TABLE `letsmed`.`users` 
CHANGE COLUMN `id` `id` INT(11) NOT NULL AUTO_INCREMENT ;

INSERT INTO `letsmed`.`users` (`name`) VALUES ('Jie');
INSERT INTO `letsmed`.`users` (`name`) VALUES ('Yang');
INSERT INTO `letsmed`.`users` (`name`) VALUES ('Stella');
INSERT INTO `letsmed`.`users` (`name`) VALUES ('Axel');

UPDATE `letsmed`.`users` SET `login`='jie' WHERE `id`='1';
UPDATE `letsmed`.`users` SET `login`='yang' WHERE `id`='2';
UPDATE `letsmed`.`users` SET `login`='stella' WHERE `id`='3';
UPDATE `letsmed`.`users` SET `login`='axel' WHERE `id`='4';