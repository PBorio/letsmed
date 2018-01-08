ALTER TABLE `letsmed`.`orders` 
ADD COLUMN `invoiceDate` DATETIME NULL DEFAULT NULL AFTER `deliveryForecast`;
