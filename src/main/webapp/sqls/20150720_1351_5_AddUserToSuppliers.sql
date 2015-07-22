ALTER TABLE `letsmed`.`suppliers` 
ADD COLUMN `user_id` INT NULL AFTER `country`,
ADD INDEX `fk_supplier_user_idx` (`user_id` ASC);
ALTER TABLE `letsmed`.`suppliers` 
ADD CONSTRAINT `fk_supplier_user`
  FOREIGN KEY (`user_id`)
  REFERENCES `letsmed`.`users` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;
  
update suppliers set user_id = 4 where id =  2;
update suppliers set user_id = 1 where id = 3;
update suppliers set user_id =1 where id = 4;
update suppliers set user_id =2 where id = 5;
update suppliers set user_id =3 where id = 6;
update suppliers set user_id =1 where id = 7;
update suppliers set user_id =1 where id = 8;
update suppliers set user_id =1 where id = 9;
update suppliers set user_id =2 where id =10;
update suppliers set user_id =1 where id =11;
update suppliers set user_id =1 where id =12;
update suppliers set user_id =1 where id =13;
update suppliers set user_id =2 where id =14;
update suppliers set user_id =1 where id =15;
update suppliers set user_id =3 where id =17;
update suppliers set user_id =4 where id =18;
update suppliers set user_id =1 where id =19;
update suppliers set user_id =3 where id =20;
update suppliers set user_id =3 where id =21;
update suppliers set user_id =1 where id =22;
update suppliers set user_id =4 where id =23;
update suppliers set user_id =4 where id =24;
update suppliers set user_id =4 where id =25;
update suppliers set user_id =3 where id =27;
update suppliers set user_id =2 where id =28;
update suppliers set user_id =3 where id =29;
update suppliers set user_id =2 where id =30;
update suppliers set user_id =1 where id =31;
update suppliers set user_id =4 where id =32;
update suppliers set user_id =4 where id =33;
update suppliers set user_id =4 where id =34;
update suppliers set user_id =4 where id =36;
update suppliers set user_id =4 where id =37;
update suppliers set user_id =4 where id =38;
update suppliers set user_id =4 where id =39;
update suppliers set user_id =4 where id =40;
