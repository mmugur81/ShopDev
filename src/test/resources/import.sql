INSERT INTO users 
  (id, created, updated, email, status, first_name, last_name, password)
VALUES
  (1,'2016-08-16 18:03:03','2016-08-16 18:03:03','ceva@nimic.lol','Pending',NULL,NULL,NULL),
  (2,'2016-09-14 20:33:02','2016-09-14 20:33:02','mmugur81@gmail.com','Active','Mugurel','Mirica','$2a$10$tpMq18uWC1kVsKFA3mwTLeVlZMfYUWoSiSmbY4Tf0wPwNdWk6GfPK');

INSERT INTO user_roles VALUES
  (1,'2016-09-14 20:33:02','2016-09-14 20:33:02','USER',2),
  (2,'2016-09-14 20:33:02','2016-09-14 20:33:02','ADMIN',2);

INSERT INTO categories VALUES
  (1,'2016-08-16 18:03:03','2016-08-16 18:03:03','Laptop-uri',NULL,NULL),
  (2,'2016-08-16 18:03:03','2016-08-16 18:03:03','Telefoane',NULL,NULL),
  (3,'2016-08-16 18:03:03','2016-08-16 18:03:03','Samsung',2,NULL),
  (4,'2016-08-16 18:03:03','2016-08-16 18:03:03','Apple',2,NULL),
  (5,'2016-08-16 18:03:03','2016-08-16 18:03:03','S1',3,NULL),
  (6,'2016-08-16 18:03:03','2016-08-16 18:03:03','S2',3,NULL),
  (7,'2016-08-16 18:03:03','2016-08-16 18:03:03','S3',3,NULL),
  (8,'2016-08-16 18:03:03','2016-08-16 18:03:03','S4',3,NULL);

INSERT INTO products
  (id, created, updated, name, description, currency, price, id_category, id_user)
VALUES
  (1,'2016-09-14 20:45:35','2016-09-14 20:45:35','S1 512 MB'       ,'','RON',1500,5,2),
  (2,'2016-09-14 20:45:55','2016-09-14 20:45:55','S2 1GB'          ,'','RON',2000,6,2),
  (3,'2016-09-14 20:47:19','2016-09-14 20:47:19','Gaming Alien LPT','','EUR',2500,1,2);

INSERT INTO orders VALUES (1,'2016-09-14 20:53:14','2016-09-14 20:53:14',NULL,NULL,false,'Pending','RON',3500,2);

INSERT INTO order_items VALUES (1,1,'RON',1500,1,1),(2,2,'RON',2000,1,2);
