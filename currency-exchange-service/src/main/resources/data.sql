CREATE TABLE Exchange_Value ( 
   id INT NOT NULL, 
   currency_from VARCHAR(50) NOT NULL, 
   currency_to VARCHAR(50) NOT NULL, 
   conversion_multiple INT NOT NULL,
   port INT NOT NULL 
);
insert into Exchange_Value(id,currency_from,currency_to,conversion_multiple,port)  
values(10001,'USD', 'INR' ,65,0);  
insert into Exchange_Value(id,currency_from,currency_to,conversion_multiple,port)  
values(10002,'EUR', 'INR' ,75,0);  
insert into Exchange_Value(id,currency_from,currency_to,conversion_multiple,port)  
values(10003,'AUD', 'INR' ,25,0); 