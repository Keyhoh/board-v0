create table accounts
(
    username varchar(64) not null primary key,
    password varchar(64) not null,
    enabled  boolean     not null
);