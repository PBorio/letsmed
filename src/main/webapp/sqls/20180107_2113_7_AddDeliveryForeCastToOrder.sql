ALTER TABLE `letsmed`.`orders` 
ADD COLUMN `deliveryForecast` VARCHAR(45) NULL DEFAULT NULL AFTER `commision`;
