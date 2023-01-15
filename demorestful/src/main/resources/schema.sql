create or replace table USERS
(
    id bigint not null,
    name varchar(255) not null,
    password varchar(255) not null,
    dob date,
    primary key (id)
); 