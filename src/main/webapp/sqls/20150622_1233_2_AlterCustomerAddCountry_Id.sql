ALTER TABLE `letsmed`.`customers` 
CHANGE COLUMN `country` `countryDesc` VARCHAR(255) NULL DEFAULT NULL ,
ADD COLUMN `country_id` INT NULL AFTER `code`;