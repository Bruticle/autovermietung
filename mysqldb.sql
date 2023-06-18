CREATE DATABASE autovermietung;
USE autovermietung;

CREATE TABLE employee(
    employee_id INT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(20),
    last_name VARCHAR(20),
    address_id INT,
    email VARCHAR(40),
    phone_number_1 VARCHAR(20),
    roll VARCHAR(20),
    username VARCHAR(15),
    pass VARCHAR(40)
    
);


CREATE TABLE customer(
    customer_id INT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(20),
    last_name VARCHAR(20),
    address_id INT,
    email VARCHAR(40),
    phone_number_1 VARCHAR(20),
    phone_number_2 VARCHAR(20),
    driving_license_number VARCHAR(20),
    payment_information_id INT
);



CREATE TABLE car(
    car_id INT PRIMARY KEY AUTO_INCREMENT,
    car_name VARCHAR(40),
    car_make VARCHAR(40),
    car_model VARCHAR(40),
    license_number VARCHAR(20),
    car_year VARCHAR(4),
    available BOOL,
    max_person INT,
    address_id INT,
    price_pro_km DOUBLE,
    insurance_comp_name VARCHAR(20),
    insurance_comp_phone_number VARCHAR(20),
    current_km_status DOUBLE,
    next_maintenance DATE,
    fuel_indicator DOUBLE

);


CREATE TABLE payment_information(
    payment_information_id INT PRIMARY KEY AUTO_INCREMENT,
    card_type VARCHAR(20),
    card_number VARCHAR(20),
    expiry_date VARCHAR(20),
    car_code INT
);

CREATE TABLE car_address(
    address_id INT PRIMARY KEY,
    country VARCHAR(20),
    district VARCHAR(20),
    street VARCHAR(20),
    zip_code VARCHAR(5),
    number_parking_spot INT    
   
);

CREATE TABLE person_address(
    address_id INT PRIMARY KEY ,
    country VARCHAR(20),
    district VARCHAR(20),
    street VARCHAR(20),
    zip_code VARCHAR(5),
    home_number VARCHAR(10)
   
);



ALTER TABLE employee ADD FOREIGN KEY(address_id) REFERENCES person_address(address_id) ON DELETE SET NULL;
ALTER TABLE car ADD FOREIGN KEY(address_id) REFERENCES car_address(address_id) ON DELETE SET NULL;
ALTER TABLE customer ADD FOREIGN KEY(payment_information_id) REFERENCES payment_information(payment_information_id) ON DELETE SET NULL;








CREATE TABLE lease ( 
    lease_id INT PRIMARY KEY AUTO_INCREMENT,
    customer_id INT,
    car_id INT,
    start_date DATE,
    end_date DATE,
    price DOUBLE,
    duration INT,
    start_km DOUBLE,
    end_km DOUBLE,
    price_pro_km DOUBLE,
    insurance_costs DOUBLE,
    closed BOOL,
    employee_id INT,
    bill_id INT,
    FOREIGN KEY(customer_id) REFERENCES customer(customer_id) ON DELETE SET NULL,
    FOREIGN KEY(car_id) REFERENCES car(car_id) ON DELETE SET NULL,
    FOREIGN KEY(employee_id) REFERENCES employee(employee_id) ON DELETE SET NULL

);




CREATE TABLE bill ( 
    bill_id INT PRIMARY KEY AUTO_INCREMENT,
    customer_id INT,
    car_id INT,
    date DATE,
    price DOUBLE,
    paid BOOL,
    payment_information_id INT,
    lease_id INT,
    FOREIGN KEY(customer_id) REFERENCES customer(customer_id) ON DELETE SET NULL,
    FOREIGN KEY(car_id) REFERENCES car(car_id) ON DELETE SET NULL,
    FOREIGN KEY(payment_information_id) REFERENCES payment_information(payment_information_id) ON DELETE SET NULL,
    FOREIGN KEY(lease_id) REFERENCES lease(lease_id) ON DELETE SET NULL

);


ALTER TABLE lease ADD FOREIGN KEY(bill_id) REFERENCES bill(bill_id) ON DELETE SET NULL;


CREATE TABLE works_with(
    employee_id INT,
    customer_id INT,
    PRIMARY KEY(employee_id,customer_id),
    FOREIGN KEY(employee_id) REFERENCES employee(employee_id) ON DELETE CASCADE,
    FOREIGN KEY(customer_id) REFERENCES customer(customer_id) ON DELETE CASCADE
);



CREATE TABLE rents(
    car_id INT,
    customer_id INT,
    PRIMARY KEY(car_id,customer_id),
    FOREIGN KEY(car_id) REFERENCES car(car_id) ON DELETE CASCADE,
    FOREIGN KEY(customer_id) REFERENCES customer(customer_id) ON DELETE CASCADE
);


CREATE TABLE signs(
    lease_id INT,
    customer_id INT,
    PRIMARY KEY(lease_id,customer_id),
    FOREIGN KEY(lease_id) REFERENCES lease(lease_id) ON DELETE CASCADE,
    FOREIGN KEY(customer_id) REFERENCES customer(customer_id) ON DELETE CASCADE
);




CREATE TABLE validates(
    employee_id INT,
    lease_id INT,
    PRIMARY KEY(employee_id,lease_id),
    FOREIGN KEY(employee_id) REFERENCES employee(employee_id) ON DELETE CASCADE,
    FOREIGN KEY(lease_id) REFERENCES lease(lease_id) ON DELETE CASCADE
);



INSERT INTO employee VALUES(NULL,'Deniz','Özdemirli',NULL,'denizozdemirli@outlook.com','05385427349','manager','02deno','password123');
INSERT INTO employee VALUES(NULL,'Alper Samet','Kabucu',NULL,'alper@outlook.com','05385427350','employee','alper','123456798');
INSERT INTO employee VALUES(NULL,'Mahmut','Armut',NULL,'armut@outlook.com','05385427351','employee','birne','pear123');


INSERT INTO person_address VALUES(1,'Türkiye','Şişli','Lalaşahin Sokak','34001','44');
INSERT INTO person_address VALUES(2,'Türkiye','Bakırköy','Kennedy Sokak','34002','25');
INSERT INTO person_address VALUES(3,'Türkiye','Zeytinburnu','Kazlıçeşme Sokak','34003','17');
INSERT INTO person_address VALUES(4,'US','New York','Fifth Avenue','34001','44');


UPDATE employee SET address_id = 1 WHERE first_name = 'Deniz'  AND last_name = 'Özdemirli';
UPDATE employee SET address_id = 2 WHERE first_name = 'Alper Samet'  AND last_name = 'Kabucu';
UPDATE employee SET address_id = 3 WHERE first_name = 'Mahmut'  AND last_name = 'Armut';


INSERT INTO customer VALUES(NULL,'Logan','Roy',NULL,'loganroy@gmail.com','05421234567','05421234568','MH12 20211234567',NULL);
INSERT INTO customer VALUES(NULL,'Kendall','Roy',NULL,'kendallroy@gmail.com','05321234567','05321234568','MH12 20211234568',NULL);
INSERT INTO customer VALUES(3,'Roman','Roy',NULL,'romanroy@gmail.com','05401234567','05401234568','MH12 20211234569',NULL);


UPDATE customer SET address_id = 4 WHERE last_name = 'Roy';

INSERT INTO payment_information VALUES(NULL,1,'MasterCard','5555555555554444','2029-07-02',345);
INSERT INTO payment_information VALUES(NULL,2,'Visa','4111111111111111','2030-04-01',123);
INSERT INTO payment_information VALUES(NULL,3,'American Express','378282246310005','2028-03-11',678);

UPDATE customer SET payment_information_id = 1 WHERE first_name = 'Logan'  AND last_name = 'Roy';
UPDATE customer SET payment_information_id = 2 WHERE first_name = 'Kendall'  AND last_name = 'Roy';
UPDATE customer SET payment_information_id = 3 WHERE first_name = 'Roman'  AND last_name = 'Roy';

INSERT INTO car VALUES(NULL,'Nissan Altima','Nissan','Altima','55FB1907','1992',TRUE,5,NULL,4.98,'Geico','05467786730',30.0,'2022-09-11',0.75);
INSERT INTO car VALUES(NULL,'Honda Civic','Honda','Civic','61GS1905','2018',TRUE,4,NULL,3.35,'Nationwide','05467786731',45.0,'2022-12-20',0.50);
INSERT INTO car VALUES(NULL,'Ford Mustang','Ford','Mustang','31SJ1969','2015',TRUE,6,NULL,4.10,'Travelers','05467786736',70.8,'2023-03-09',0.25);


INSERT INTO car_address VALUES(1,'Türkiye','Beşiktaş','Barbaros Bulvarı','34005','98');
INSERT INTO car_address VALUES(2,'Türkiye','Kadıköy','Moda','34007','101');
INSERT INTO car_address VALUES(3,'Türkiye','Taksim','İstiklal','34008','2');


UPDATE car SET address_id = 1 WHERE car_name = 'Nissan Altima';
UPDATE car SET address_id = 2 WHERE car_name = 'Honda Civic';
UPDATE car SET address_id = 3 WHERE car_name = 'Ford Mustang';


INSERT INTO lease VALUES(NULL,1,1,'2022-03-11','2022-03-15',122.09,5,31.0,61.5,4.98,20,TRUE,2,NULL);
INSERT INTO lease VALUES(NULL,2,3,'2022-05-23','2022-03-28',192.86,6,45.0,96.6,3.35,20,TRUE,3,NULL);
INSERT INTO lease VALUES(NULL,3,2,'2022-01-01','2022-01-11',573.09,11,70.8,205.7,4.10,20,TRUE,2,NULL);




INSERT INTO bill VALUES(NULL,1,1,'2022-03-15',122.09,TRUE,1,1);
INSERT INTO bill VALUES(NULL,2,3,'2022-01-11',573.09,TRUE,2,2);
INSERT INTO bill VALUES(NULL,3,2,'2022-03-28',192.86,TRUE,3,3);


INSERT INTO works_with VALUES(1,2);
INSERT INTO works_with VALUES(2,3);
INSERT INTO works_with VALUES(3,2);

INSERT INTO rents VALUES(1,1);
INSERT INTO rents VALUES(3,2);
INSERT INTO rents VALUES(2,3);


INSERT INTO signs VALUES(1,1);
INSERT INTO signs VALUES(2,2);
INSERT INTO signs VALUES(3,3);


INSERT INTO validates VALUES(2,1);
INSERT INTO validates VALUES(3,2);
INSERT INTO validates VALUES(2,3);


SELECT * FROM employee;
SELECT * FROM person_address;
DELETE FROM person_address WHERE address_id =5;
SELECT * FROM customer;
SELECT * FROM payment_information;
DELETE FROM car WHERE car_id = 2;
SELECT * FROM car;
SELECT * FROM car_address;
DELETE FROM lease;
DELETE FROM lease WHERE lease_id = 2;
SELECT * FROM lease;
SELECT * FROM bill;
SELECT address_id FROM person_address WHERE street = 'Lalaşahin Sokak';